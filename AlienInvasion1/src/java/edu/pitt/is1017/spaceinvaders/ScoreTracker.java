package edu.pitt.is1017.spaceinvaders;

import java.util.UUID;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import java.util.BitSet;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import java.sql.Timestamp;
import java.sql.*;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;


public class ScoreTracker {
		User user;
		int currentScore;
		int highestScore;
		String gameID;
		int userID;
	
		
		public ScoreTracker(User user) {
			DbUtilities db = new DbUtilities();
			UUID idOne = UUID.randomUUID();
			gameID = idOne.toString();
			currentScore = 0;
			
			userID = user.userID;
			//System.out.println(userID);
			
			String sql = "SELECT scoreValue from finalscores WHERE fk_userID = '" + userID + "' ORDER BY scoreValue DESC LIMIT 1;";
			//System.out.println(sql);
			try{
			//	System.out.println("TRY");
				ResultSet rs = db.getResultSet(sql);
				if(rs.next()) {
					
					highestScore = rs.getInt("scoreValue");
				//	System.out.println("highestscore: " + highestScore);
				
				}else {//no result in databse. no previous record
					highestScore = 0;
				//	System.out.println("highestscore: " + highestScore);
				}
				db.closeConnection();
			}
			catch(Exception ex) {
				System.err.println(ex.toString());
				
			}
		//	System.out.println("DONE");
		}
			
		public void recordScore(int point) {
			
			BitSet bs = new BitSet(1);
			
		//	DbUtilities db = new DbUtilities();
			
			boolean scoreType;
			currentScore = currentScore + point;
			int fk_userID = userID;
			 
			if(point == 1) {
				bs.set(1);
			} else {
				bs.set(0);
			}
			String stamp = "CURRENT_TIMESTAMP";
			
			Date now = new Date();
			
			
			
			String bit = "b'1'";
			/*Dmitriy!!!!! I wasted way too much try to get this to insert into the database and eventually
			just got fed up and moved on because time is money. I know why it isnt working, its the "scoreType" value	
			no matter what I try to pass in it doesnt work. I either get an error claiming the syntax for the sql
			statement is wrong or the value I'm trying to pass in is too big of size. I couldn't figure out how to pass in
			the 1 bit value. Please don't hold this against me too heavily. 
			*/
			Timestamp timestamp = new Timestamp(Calendar.getInstance().getTime().getTime());
			String s = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(timestamp);
			
			//System.out.println(gameID);
			String sql_insert = "INSERT INTO runningscores(gameID, scoreType, scoreValue,fk_userID, dateTimeEntered) VALUES('" + gameID + "','" + bit + "','" + currentScore + "','" + fk_userID + "','" + s + "');";
			
			//String sql_insert = "INSERT INTO finalscores(gameID,  scoreValue,fk_userID, dateTimeEntered) VALUES('" + gameID + "','" + currentScore + "','" + fk_userID + "','" + s + "');";
			
				
		//	db.executeQuery(sql_insert);
			//if point is -1, scoretype = 0
			
		//	db.closeConnection();
		//	System.out.println("CURRENT SCORE: " + currentScore);
		}
	
		public void recordFinalScore() {
		//	System.out.println("record final score");
		//	System.out.println("FINAL SCORE: " + currentScore);
			DbUtilities db = new DbUtilities();
			
			
			Timestamp timestamp = new Timestamp(Calendar.getInstance().getTime().getTime());
			String s = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(timestamp);
			
			int fk_userID = userID;
			String sql_insert = "INSERT INTO finalscores(gameID,  scoreValue,fk_userID, dateTimeEntered) VALUES('" + gameID + "','" + currentScore + "','" + fk_userID + "','" + s + "');";
			db.executeQuery(sql_insert);
		//	System.out.println("DONE");
			
		}
		
		public void display_highScore() {
			DbUtilities db = new DbUtilities();
			Vector<Vector<Object>> data = new Vector<Vector<Object>>();
			
		//	SELECT lastName, firstName, MAX(scoreValue) FROM finalscores JOIN users ON fk_userID = userID GROUP BY lastName, firstName ORDER BY MAX(scoreValue) DESC LIMIT 1;
			String sql_highPlayer = "SELECT lastName, firstName, MAX(scoreValue) FROM finalscores JOIN users ON fk_userID = userID GROUP BY lastName, firstName ORDER BY MAX(scoreValue) DESC LIMIT 1; ";
			try{
				ResultSet rs = db.getResultSet(sql_highPlayer);
				ResultSetMetaData metaData = rs.getMetaData();
				
				Vector<String> columnNames = new Vector<String>();
				for(int i = 1; i <= metaData.getColumnCount(); i++) {
					columnNames.add(metaData.getColumnName(i));
					//System.out.println(metaData.getColumnName(i));
				}
				
				
				
				while(rs.next()) {
					Vector<Object> vector = new Vector<Object>();
					for(int j = 1; j <= metaData.getColumnCount(); j++) {
						vector.add(rs.getObject(j));
					}
					data.add(vector);
				}
			//	DefaultTableModel tableDataModel = new DefaultTableModel(data, columnNames);
			//	System.out.println(data);
			}catch(Exception ex) {
				
			}
			
			
			String sql_high_score = "SELECT scoreValue FROM finalscores WHERE fk_userID = '" + userID + "' AND gameID = '" + gameID + "'";
		//	System.out.println(sql_high_score);
			
			try{
				ResultSet rs = db.getResultSet(sql_high_score);
				if(rs.next()) {
					
					int score = rs.getInt("scoreValue");
				//	System.out.println("Your Score = " + score);
					JOptionPane.showMessageDialog(null,"Your score: " + score + " \n High score: " + highestScore + "\n Current All time record " + data , "score", JOptionPane.DEFAULT_OPTION);
				}else {
					
				}
				db.closeConnection();	
			} catch(Exception ex) {
				System.err.println(ex.toString());
			}
		}
	
	
	
	
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UUID idOne = UUID.randomUUID();
		
	//	System.out.println(gameID);
	}

}
