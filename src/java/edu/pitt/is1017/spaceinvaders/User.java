package edu.pitt.is1017.spaceinvaders;

import java.sql.ResultSet;

import javax.swing.JOptionPane;


public class User {
	int userID;
	String lastName;
	String firstName;
	String user_email;
	String user_pass;
	boolean loggedIn = false;
	
	
	public User(int userID) {//already exists in the database
		
		
		DbUtilities db = new DbUtilities();
		String sql = "SELECT  * FROM users WHERE userID = '" + userID + "';";
		//System.out.println(sql);
		
		try {
			ResultSet rs = db.getResultSet(sql);
			if(rs.next()) {
				System.out.println("TRUE");
				userID = rs.getInt("userID");
				lastName = rs.getString("lastName");
				firstName = rs.getString("firstName");
				user_email = rs.getString("email");
				user_pass = rs.getString("password");
				loggedIn = true;
			}
			else {
				System.out.println("FALSE");
			}
			db.closeConnection();
		}
		catch(Exception ex) {
			System.err.println(ex.toString());
		
	}
	}
	
	public User(String email, String password) {//checks this against the database
		DbUtilities db = new DbUtilities();
		String sql = "SELECT  * FROM users WHERE email = '" + email + "' AND password = MD5('" + password +"');";
		System.out.println(sql);
		
		try {
			ResultSet rs = db.getResultSet(sql);
			if(rs.next()) {
                               // System.exit(0);
				System.out.println("TRUE");
				userID = rs.getInt("userID");
				lastName = rs.getString("lastName");
				firstName = rs.getString("firstName");
				user_email = rs.getString("email");
				user_pass = password;
				loggedIn = true;
			//	JOptionPane.showMessageDialog(null,"SUCCESS","USER LOGGED IN", JOptionPane.ERROR_MESSAGE);
			}
			else {
				JOptionPane.showMessageDialog(null,"error_no_match","NO MATCH IN THE DATABASE!", JOptionPane.ERROR_MESSAGE);
                                
			}
			db.closeConnection();
		}
		catch(Exception ex) {
			//handle here
		
		}
		db.closeConnection();
	}	
	
	public User(String _lastName, String _firstName, String _email, String _password) {
		DbUtilities db = new DbUtilities();
		String sql = "SELECT  * FROM users WHERE email = '" + _email + "' AND password = MD5('" + _password +"');";
		//System.out.println(sql);
		try {
			ResultSet rs = db.getResultSet(sql);
			if(rs.next()) {
				JOptionPane.showMessageDialog(null,"error_duplicate","MATCH IN THE DATABASE!", JOptionPane.ERROR_MESSAGE);
			} else {
				lastName = _lastName;
				firstName = _firstName;
				user_email = _email;
				user_pass = _password;
				loggedIn = true;
				
				String sql_insert = "INSERT INTO users(lastName, firstName, email, password)";
				sql_insert = sql_insert + "VALUES('" + _lastName + "','" + _firstName + "','" + _email + "', MD5('" + _password + "'));";
				
				db.executeQuery(sql_insert);//execute query inserts user into database
				
				String sql_ID = "SELECT  userID FROM users WHERE email = '" + _email + "' AND password = MD5('" + _password +"');";
				try{
					rs = db.getResultSet(sql_ID);
					if(rs.next()) {
						
						JOptionPane.showMessageDialog(null,"SUCCESS","NEW USER REGISTERED", JOptionPane.ERROR_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null,"error_no_match","NO MATCH IN THE DATABASE!", JOptionPane.ERROR_MESSAGE);
					}
					
				}
				catch(Exception ex) {
					
				}
			}
		

			db.closeConnection();	
		}
		catch(Exception ex) {
			
		}
		
		
	//	String sql_insert = "INSERT * INTO users(lastName, firstName, email, password)";
	//	sql_insert = sql_insert + "VALUES('" + lastName + "','" + firstName + "','" + email + "', MD5('" + password + "'));";
		
	}
	
	public void saveUserInfo() {//takes the class properties and puts them into the database
		DbUtilities db = new DbUtilities();
		
		//check if anything is empty first
		if((lastName != null && !lastName.isEmpty()) || (firstName != null && !firstName.isEmpty()) || (user_email != null && !user_email.isEmpty())) {
			//if nothing is empty
			String sql_update = "UPDATE users SET (userID, lastName, firstName, email, password)";
			sql_update = sql_update + "VALUES('" + userID + "','" + lastName  + "','" + firstName  + "','" + user_email  + "', MD5('" + user_pass + "')) WHERE userID = ' + userID + ';";
			
			db.executeQuery(sql_update);
			
					
		} else {
			System.out.println("ERROR: SHIT WENT WRONG");
		}
		db.closeConnection();
	}


	//public static void main(String[] args) {
	//	User Topsy = new User("topsy","kretts","test1@pitt.edu", "test");
	//}
}	
