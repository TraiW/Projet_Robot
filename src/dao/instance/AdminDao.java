package dao.instance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import model.AdminModelBean;

public class AdminDao {
	private Connection connection;
	private String dB_HOST;
	private String dB_PORT;
	private String dB_NAME;
	private String dB_USER;
	private String dB_PWD;
	//private String loginAdmin="Admin";
	//private String pwdAdmin="Admin";
	
	public AdminDao(String DB_HOST,String DB_PORT, String DB_NAME,String DB_USER,String DB_PWD) {
		dB_HOST = DB_HOST;
		dB_PORT = DB_PORT;
		dB_NAME = DB_NAME;
		dB_USER = DB_USER;
		dB_PWD = DB_PWD;
	}
	public void addAdmin(AdminModelBean admin) {
		// Création de la requête
		try {
			// create connection
			connection = java.sql.DriverManager.getConnection("jdbc:mysql://" + dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD);
			
			//TODO A l’image de DB.java créer une réquète permettant d’ajout
			//l’admin à la base de données, ATTENTION, utiliser cette fois–ci
			//les PrepareStatement
			
			PreparedStatement querySt=connection.prepareStatement("INSERT INTO binome10.utilisateurs (lastname, surname, age, login, pwd) VALUES(?, ?, ?, ?, ?)");
			
			querySt.setString(1,admin.getSurname());
			querySt.setString(2,admin.getLastname());
			querySt.setInt(3,admin.getAge());
			querySt.setString(4,admin.getLogin());
			querySt.setString(5,admin.getPwd());
			
			querySt.executeUpdate();
			querySt.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public ArrayList<AdminModelBean> getAllAdmin(){
		//return value
		ArrayList<AdminModelBean> adminList=new ArrayList<AdminModelBean>();
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
				AdminModelBean admin= new AdminModelBean();
				admin.setLastname(rs.getString("lastname"));
				admin.setSurname(rs.getString("surname"));
				admin.setAge(rs.getInt("age"));
				admin.setLogin(rs.getString("login"));
				admin.setPwd(rs.getString("pwd"));
				adminList.add(admin);
			}
			rs.close();
			query.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return adminList;
	}
	public AdminModelBean checkAdmin(String login, String pwd) {
		AdminModelBean admin = null;
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
				
					admin=new AdminModelBean();
					admin.setLastname(rs.getString("lastname"));
					admin.setSurname(rs.getString("surname"));
					admin.setAge(rs.getInt("age"));
					admin.setLogin(rs.getString("login"));
					admin.setPwd(rs.getString("pwd"));
					
				}
				
			}
			rs.close();
			query.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return admin;
	}
}