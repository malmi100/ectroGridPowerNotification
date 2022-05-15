package com;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Notification {

	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/notification_records?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertNotification(String description,String adminName,String nDate,String zone) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into notification(`NotificationID`,`description`,`adminName`,`nDate`,`zone`)" + " values (?,?,?,?,?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, description);
			preparedStmt.setString(3, adminName);
			preparedStmt.setString(4, nDate);
			preparedStmt.setString(5, zone);
		

			// execute the statement 
			preparedStmt.execute();
			con.close();
			output = "Notification Inserted successfully!";
		} catch (Exception e) {
			output = "Error while inserting  Notification Details!";
			System.err.println(e.getMessage());
		}
		return output;
	}

	
	public String readNotification() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading!";
			}
			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr> <th>Notification ID</th><th>Description</th><th>Admin Name</th><th>Date</th><th>Zone</th> </tr>";
			String query = "select * from notification";
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String NotificationID = Integer.toString(rs.getInt("NotificationID"));
				String description = rs.getString("description");
				String adminName = rs.getString("adminName");
				String nDate = rs.getString("nDate");
				String zone = rs.getString("zone");
				
				// Add into the html table 
				output += "<tr><td><input id=\'hidNotificationIDUpdate\' name=\'hidNotificationIDUpdate\' type=\'hidden\' value=\'" + NotificationID + "'>" 
						+ description + "</td>"; 
				output += "<td>" + adminName + "</td>";
				output += "<td>" + nDate + "</td>";
				output += "<td>" + zone + "</td>";
				
				// buttons     
				output +="<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"       
						+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-userid='" + NotificationID + "'>" + "</td></tr>"; 
			
		
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the Notification Details!";
			System.err.println(e.getMessage());
		}
		return output;
	}

	
	public String updateNotification(String NotificationID,String description,String adminName,String nDate,String zone) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for updating!";
			}

			// create a prepared statement
			String query = "UPDATE notification SET description=?,adminName=?,nDate=?,zone=? WHERE NotificationID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values

			preparedStmt.setString(1, description);
			preparedStmt.setString(2, adminName);
			preparedStmt.setString(3, nDate);
			preparedStmt.setString(4, zone);
			preparedStmt.setInt(5, Integer.parseInt(NotificationID));

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Notification Updated successfully!";
		} catch (Exception e) {
			output = "Error while updating!";
			System.err.println(e.getMessage());
		}

		return output;
	}

	
	public String deleteNotification(String NotificationID) {

		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for deleting!";
			}

			// create a prepared statement
			String query = "delete from notification where NotificationID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, Integer.parseInt(NotificationID));

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Deleted successfully!";
		} catch (Exception e) {
			output = "Error while deleting!";
			System.err.println(e.getMessage());
		}

		return output;
	}

}