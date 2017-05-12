package dao.instance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.AdminUserModelBean;


public class UserDao {
	private Connection connection;
	private String dB_HOST;
	private String dB_PORT;
	private String dB_NAME;
	private String dB_USER;
	private String dB_PWD;
		
	public UserDao(String DB_HOST,String DB_PORT, String DB_NAME,String DB_USER,String DB_PWD) {
		dB_HOST = DB_HOST;
		dB_PORT = DB_PORT;
		dB_NAME = DB_NAME;
		dB_USER = DB_USER;
		dB_PWD = DB_PWD;
	}
	
	public AdminUserModelBean checkUser(String login, String pwd) {
		AdminUserModelBean user = null;
		java.sql.Statement query;
		try {
			// create connection
			connection = java.sql.DriverManager.getConnection("jdbc:mysql://"+ dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD);
			query=connection.createStatement();
			 
            PreparedStatement querySt= connection.prepareStatement("SELECT * FROM utilisateurs WHERE login LIKE ? AND pwd LIKE ?;");
            querySt.setString(1,login);
            querySt.setString(2,pwd);
            java.sql.ResultSet rs = querySt.executeQuery();
			while(rs.next())
			{
				if(rs.getString("login")==login && rs.getString("pwd")==pwd);
				{
				
					user=new AdminUserModelBean();
					user.setLastname(rs.getString("lastname"));
					user.setSurname(rs.getString("surname"));
					user.setAge(rs.getInt("age"));
					user.setLogin(rs.getString("login"));
					user.setPwd(rs.getString("pwd"));
					
				}
				
			}
			rs.close();
			query.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
}