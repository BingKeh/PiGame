package pigame;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.monitor.FileAlterationListener;

import com.google.common.collect.Sets;
import com.sun.javafx.collections.MappingChange.Map;


public class articledb {
	public static final String TABLE_NAME = "user_work";
	public static final String COLUMN_ID = "iduser_work";
	public static final String COLUMN_USER = "user_id";
	public static final String COLUMN_DATE = "work_date";
	public static final String COLUMN_TYPE = "work_type";
	public static final String COLUMN_URL = "work_url";
	public static final String COLUMN_HEADING = "work_heading";
	public static final String COLUMN_COVER = "work_cover";
	public static final String COLUMN_STATUS = "status";
	
	
	public static final String INSERT_WORK = "INSERT INTO " + TABLE_NAME + "( " + COLUMN_USER 
			+ ", " + COLUMN_TYPE + ", " + COLUMN_URL + "," + COLUMN_HEADING + ", " + COLUMN_COVER +" ) VALUES(?, ?, ?, ?, ?)";  
	public static final String SELECT_WORK = "SELECT * FROM "  + TABLE_NAME  + " join "
			+ userdb.TABLE_NAME + " on " + TABLE_NAME + "." + COLUMN_USER + " = "
			+ userdb.TABLE_NAME + "." + userdb.COLUMN_ID + " WHERE " + COLUMN_STATUS + " =  1 ";
	public static final String UPDATE_STATUS = "UPDATE " + TABLE_NAME + " SET " + COLUMN_STATUS + " = 0 WHERE " + COLUMN_ID + " = ? ";
	
	private Connection conn;
	
	public articledb(Connection conn) {
		this.conn = conn;
	}
	
	
	public boolean putWork(int id, String type, String url, String heading, String cover) throws SQLException {
		PreparedStatement preparedStatement = this.conn.prepareStatement(INSERT_WORK);
		preparedStatement.setInt(1, id);
		preparedStatement.setString(2, type);
		preparedStatement.setString(3, url);
		preparedStatement.setString(4, heading);
		preparedStatement.setString(5, cover);
		int rows = preparedStatement.executeUpdate();
		if (rows == 1) {
			return true;
		}
		return false;
	}
	
	public List<HashMap<String, String>> getWork(int rows, String realPath) throws SQLException {
		List<HashMap<String, String>> list = new ArrayList<>();
		PreparedStatement preparedStatement = this.conn.prepareStatement(SELECT_WORK);
		ResultSet rSet = preparedStatement.executeQuery();
		if (rSet.next()) { 
			do {
				HashMap<String, String> data = new HashMap<>();
				int usr_id = rSet.getInt(COLUMN_USER);
				String url = rSet.getString(COLUMN_URL).replace(realPath, "").replaceAll("\\\\", "/").substring(1);
				String usr = rSet.getString(userdb.COLUMN_NICK);
				String date = rSet.getString(COLUMN_DATE);
				String heading = rSet.getString(COLUMN_HEADING);
				String cover = rSet.getString(COLUMN_COVER).replace(realPath, "").replaceAll("\\\\", "/").substring(1);
				int work_id = rSet.getInt(COLUMN_ID);
				data.put(COLUMN_URL, url);
				data.put(userdb.COLUMN_NICK, usr);
				data.put(COLUMN_DATE, date);
				data.put(COLUMN_HEADING, heading);
				data.put(COLUMN_COVER, cover);
				data.put(COLUMN_ID, work_id + "");
				String avatar = "img/avatar/" + usr_id + "-avatar.png";
				data.put(COLUMN_USER, avatar);
				System.out.println("The uid is " + usr_id);
				list.add(data);
			} while(rSet.next());
			return list;
		}
		return null;
	}
	
	
	public boolean black_room(int work_id) throws SQLException {
		PreparedStatement preparedStatement = this.conn.prepareStatement(UPDATE_STATUS);
		preparedStatement.setInt(1, work_id);
		int rows = preparedStatement.executeUpdate();
		if (rows == 1) {
			return true;
		}
		return false;
	}
	
	
}
