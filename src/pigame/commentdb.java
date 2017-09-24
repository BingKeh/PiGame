package pigame;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.eclipse.jdt.internal.compiler.util.Sorting;

public class commentdb {
	public static final String TABLE_NAME = "work_comment";
	public static final String COLUMN_ID = "idwork_comment";
	public static final String COLUMN_WORK_ID = "work_id";
	public static final String COLUMN_USER_ID = "user_id";
	public static final String COLUMN_COMMENT = "comment";
	public static final String COLUMN_DATE = "comment_date";
	
	private static final String INSERT_COMMENT = "INSERT INTO " + TABLE_NAME + "("
			+ COLUMN_WORK_ID + ", " + COLUMN_USER_ID + ", " + COLUMN_COMMENT
			+ ") VALUES(?, ?, ?)";
	private static final String SELECT_COMMENT = "SELECT * FROM " + TABLE_NAME + " join "
			+ userdb.TABLE_NAME + " on " + TABLE_NAME + "." + COLUMN_USER_ID + " = " + userdb.TABLE_NAME
			+ "." + userdb.COLUMN_ID + " WHERE " + COLUMN_WORK_ID + " = ? ";
	
	private Connection conn;
	
	public commentdb(Connection conn) {
		this.conn = conn;
	}
	
	public boolean put_comment(int work_id, int user_id, String comment) throws SQLException {
		PreparedStatement preparedStatement = this.conn.prepareStatement(INSERT_COMMENT);
		preparedStatement.setInt(1, work_id);
		preparedStatement.setInt(2, user_id);
		preparedStatement.setString(3, comment);
		int rows = preparedStatement.executeUpdate();
		if (rows == 1) {
			return true;
		}
		return false;
	}	
	 
	public List<HashMap<String, String>> get_comment(int work_id) throws SQLException {
		List<HashMap<String, String>> list = new ArrayList<>();
		PreparedStatement preparedStatement = this.conn.prepareStatement(SELECT_COMMENT);
		preparedStatement.setInt(1, work_id);
		ResultSet rSet = preparedStatement.executeQuery();
		if (rSet.next()) {
			do {
				 HashMap<String, String> data = new HashMap<>();
				 data.put(COLUMN_WORK_ID, rSet.getInt(COLUMN_WORK_ID) + "");
				 data.put(COLUMN_DATE, rSet.getString(COLUMN_DATE));
				 data.put(COLUMN_COMMENT, rSet.getString(COLUMN_COMMENT));
				 data.put(COLUMN_USER_ID, rSet.getInt(COLUMN_USER_ID) + "");
				 data.put(userdb.COLUMN_NICK, rSet.getString(userdb.COLUMN_NICK));
				 list.add(data);
			} while(rSet.next());
			return list;
		}
		return null;
	}
	
}
