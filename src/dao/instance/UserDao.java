package dao.instance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import model.UserModelBean;

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
	public void addUser(UserModelBean user) {
		// Création de la requête
		try {
			// create connection
			connection = java.sql.DriverManager.getConnection("jdbc:mysql://" + dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD);
			
			//TODO A l’image de DB.java créer une réquète permettant d’ajout
			//l’utilisateur à la base de données, ATTENTION, utiliser cette fois–ci
			//les PrepareStatement
			
			PreparedStatement querySt=connection.prepareStatement("INSERT INTO binome10.utilisateurs (lastname, surname, age, login, pwd) VALUES(?, ?, ?, ?, ?)");
			
			querySt.setString(1,user.getSurname());
			querySt.setString(2,user.getLastname());
			querySt.setInt(3,user.getAge());
			querySt.setString(4,user.getLogin());
			querySt.setString(5,user.getPwd());
			
			querySt.executeUpdate();
			querySt.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public ArrayList<UserModelBean> getAllUser(){
		//return value
		ArrayList<UserModelBean> userList=new ArrayList<UserModelBean>();
		java.sql.Statement query;
		try {
			// create connection
			connection = java.sql.DriverManager.getConnection("jdbc:mysql://"+ dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD);
			
			//TODO A l’image de DB.java créer une réquète permettant de récupérer
			//l’ensemble des utilisateurs contenu dans la base et de les placer dans une liste
			query=connection.createStatement();
			java.sql.ResultSet rs=query.executeQuery("SELECT * FROM utilisateurs");
			while(rs.next())
			{
				UserModelBean user= new UserModelBean();
				user.setLastname(rs.getString("lastname"));
	            user.setSurname(rs.getString("surname"));
	            user.setAge(rs.getInt("age"));
	            user.setLogin(rs.getString("login"));
	            user.setPwd(rs.getString("pwd"));
	            userList.add(user);
			}
			rs.close();
			query.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userList;
	}
	public UserModelBean checkUser(String login, String pwd) {
		UserModelBean user = null;
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
					user=new UserModelBean();
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