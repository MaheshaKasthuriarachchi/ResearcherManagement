package model;

import java.sql.*;

public class Researcher {
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/researcher_management", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertReseacher(String reseacherFname, String researcherLname,
			String researcherEmail, String researcherPhoneNo, String researcherSpecialization, String researchTitle) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}

			// create a prepared statement
			String query = " insert into researchers (`researcherID`, `researcherFname`, `researcherLname`, `researcherEmail`, `researcherPhoneNo`, `researcherSpecialization`, `researchTitle`)"
					+ " values (?, ?, ?, ?, ?,?,?)";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, 0);
			
			preparedStmt.setString(2, reseacherFname);
			preparedStmt.setString(3, researcherLname);
			preparedStmt.setString(4, researcherEmail);
			preparedStmt.setInt(5, Integer.parseInt(researcherPhoneNo));
			preparedStmt.setString(6, researcherSpecialization);
			preparedStmt.setString(7, researchTitle);
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the reseacher.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readReseachers() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Researcher ID</th><th>First Name</th>"
					+ "<th>Last Name</th><th>Email</th>" + "<th>Phone Number</th>"
					+ "<th>Specialization</th><th>Research Title</th>" + "<th>Update</th><th>Remove</th></tr>";

			String query = "select * from researchers";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String reseacherID = Integer.toString(rs.getInt("reseacherID"));
				String researcherFname = rs.getString("researcherFname");
				String reseacherLName = rs.getString("reseacherLName");
				String researcherEmail = rs.getString("researcherEmail");
				String researcherPhoneNo = Integer.toString(rs.getInt("researcherPhoneNo"));
				String researcherSpecialization = rs.getString("researcherSpecialization");
				String researchTitle = rs.getString("researchTitle");
				// Add into the html table
				output += "<tr><td>" + researcherFname + "</td>";
				output += "<td>" + reseacherLName + "</td>";
				output += "<td>" + researcherEmail + "</td>";
				output += "<td>" + researcherPhoneNo + "</td>";
				output += "<td>" + researcherSpecialization + "</td>";
				output += "<td>" + researchTitle + "</td>";
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"
						+ "<td><form method='post' action='reseachers.jsp'>"
						+ "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"
						+ "<input name='reseacherID' type='hidden' value='" + reseacherID + "'>" + "</form></td></tr>";
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the reseachers.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updateReseacher(String researcherID, String researcherFname, String researcherLname,
			String researcherEmail, String researcherPhoneNo, String researcherSpecialization, String researchTitle) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			// create a prepared statement
			String query = "UPDATE researchers SET researcherFname=?,researcherLname=?,researcherEmail=?,researcherPhoneNo=?,researcherSpecialization=?,researchTitle=?WHERE reseacherID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, researcherFname);
			preparedStmt.setString(2, researcherLname);
			preparedStmt.setString(3, researcherEmail);
			preparedStmt.setInt(4, Integer.parseInt(researcherPhoneNo));
			preparedStmt.setString(5, researcherSpecialization);
			preparedStmt.setString(6, researchTitle);
			preparedStmt.setInt(7, Integer.parseInt(researcherID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the reseacher.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deleteReseacher(String reseacherID) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from researchers where reseacherID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(reseacherID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the reseacher.";
			System.err.println(e.getMessage());
		}
		return output;
	}
}
