package pigame;

import java.sql.*;

public class dbUtil {
	private String driveName;
	private String userName;
	private String userPassword;
	private String dbName;
	private String url;
	private Connection con;
	private String host;
	
	public dbUtil() {
		this.driveName = "com.mysql.jdbc.Driver";
		this.userName = "bing";
		this.userPassword = "12233345";
		this.dbName = "pigame";
		this.host = "localhost";
		this.url = "jdbc:mysql://" + host + "/"+dbName+"?user="+userName+"&password="+userPassword + "&useUnicode=true&autoReconnect=true&characterEncoding=utf8";
	}
	
	public boolean tryCon() {	
		try {
			Class.forName(driveName).newInstance();
			System.out.println(url);
			this.con = DriverManager.getConnection(url);
			
			if (this.con != null) {
				System.out.println("Connect to MySQL OK!");
				return true;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}
	
	public Connection getConnection() {
		if (this.con != null) {
			return this.con;
		}
		else {
			this.tryCon();
			return this.con;
		}
	}
}
