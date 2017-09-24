package pigame;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.print.attribute.standard.RequestingUserName;


public class userdb {
	public static final String TABLE_NAME = "user_info";
	public static final String COLUMN_ID = "user_id";
	public static final String COLUMN_NICK = "user_nickname";
	public static final String COLUMN_EMAIL = "user_email";
	public static final String COLUMN_PWD_MD5 = "user_pwd_md5";
	public static final String COLUMN_TOKEN = "token";
	public static final String COLUMN_BALANCE = "balance";
	
	
	private static String SELECT_USER = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_NICK + " = ? and " + COLUMN_PWD_MD5 + " = ? "; 
	private static String INSERT_USER = "INSERT INTO " + TABLE_NAME + " ( " + COLUMN_NICK + ", " + COLUMN_PWD_MD5 + ", " + COLUMN_EMAIL
				+ " ) VALUES ( ?, md5(?), ? )"; 
	private static String UPDATE_TOKEN = "UPDATE " + TABLE_NAME + " SET " + COLUMN_TOKEN + " = ? " + " WHERE " + COLUMN_NICK + " = ? "; 
	public static final String GET_ID = "SELECT " + COLUMN_ID + " FROM " + TABLE_NAME + " WHERE " + COLUMN_NICK + " = ?";
	
	public static final String UPDATE_BALANCE = "UPDATE " + TABLE_NAME + " SET " + COLUMN_BALANCE + " = ? WHERE " + COLUMN_ID + " = ?" ;
	public static final String SELECT_BALANCE = "SELECT " + COLUMN_BALANCE + " FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + " = ? ";;
	
	private Connection connection;
	public userdb(Connection connection) {
		this.connection = connection;
	}
	
	
	public boolean do_login(String usr, String pwd) throws NoSuchAlgorithmException, SQLException{
		String pwd_md5 = MyUtil.getMD5(pwd);
		PreparedStatement preparedStatement = this.connection.prepareStatement(SELECT_USER);
		preparedStatement.setString(1, usr);
		preparedStatement.setString(2, pwd_md5);
		System.out.println(preparedStatement.toString());
		ResultSet rSet = preparedStatement.executeQuery();
		if (rSet.next()) {
			rSet.close();
			preparedStatement.close();
			return true;
		}
		rSet.close();
		preparedStatement.close();
		return false;
	}
	
	public boolean put_token(String token, String usr) throws SQLException {
		PreparedStatement preparedStatement = this.connection.prepareStatement(UPDATE_TOKEN);
		preparedStatement.setString(1, token);
		preparedStatement.setString(2, usr);
		System.out.println(preparedStatement.toString());
		int rows = preparedStatement.executeUpdate();
		if (rows == 1) {
			return true;
		}
		return false;
	}
	
	public boolean do_register(String usr, String usr_pwd, String usr_email) throws SQLException {
		PreparedStatement preparedStatement = this.connection.prepareStatement(INSERT_USER);
		preparedStatement.setString(1, usr);
		preparedStatement.setString(2, usr_pwd);
		preparedStatement.setString(3, usr_email);
		int rows = preparedStatement.executeUpdate();
		if (rows == 1) {
			return true;
		}
		return false;
	}
	
	public boolean do_logout(String usr) throws SQLException {
		PreparedStatement preparedStatement = this.connection.prepareStatement(UPDATE_TOKEN);
		preparedStatement.setString(1, null);
		preparedStatement.setString(2, usr);
		int rows = preparedStatement.executeUpdate();
		if (rows == 1) {
			return true;
		}
		return false;
	}
	
	public int get_id(String usr) throws SQLException {
		PreparedStatement preparedStatement = this.connection.prepareStatement(GET_ID);
		preparedStatement.setString(1, usr);
		ResultSet rSet = preparedStatement.executeQuery();
		if (rSet.next()) {
			return rSet.getInt(COLUMN_ID);
		}
		return -1;
	}
	
	public boolean buy_skin(int usr_id, int cost) throws SQLException {
		PreparedStatement preparedStatement = this.connection.prepareStatement(SELECT_BALANCE);
		preparedStatement.setInt(1, usr_id);
		ResultSet rSet = preparedStatement.executeQuery();
		if (rSet.next()) {
			int balance = rSet.getInt(COLUMN_BALANCE);
			if (balance >= cost) {
				preparedStatement = this.connection.prepareStatement(UPDATE_BALANCE);
				preparedStatement.setInt(1, balance - cost);
				preparedStatement.setInt(2, usr_id);
				int rows = preparedStatement.executeUpdate();
				if (rows == 1) {
					return true;
				}
			}
		}
		return false;
	}
}
