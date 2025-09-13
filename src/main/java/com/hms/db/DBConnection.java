package com.hms.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	private static Connection conn;
	
	public static Connection getConn() {
		
		try {
			// Check if MySQL is available first, fallback to H2
			String dbType = System.getenv().getOrDefault("DB_TYPE", "h2");
			
			if ("mysql".equalsIgnoreCase(dbType)) {
				// MySQL connection
				Class.forName("com.mysql.cj.jdbc.Driver");
				String url = System.getenv().getOrDefault("DB_URL", "jdbc:mysql://localhost:3306/hospital");
				String user = System.getenv().getOrDefault("DB_USER", "root");
				String pass = System.getenv().getOrDefault("DB_PASS", "Raju@1229");
				conn = DriverManager.getConnection(url, user, pass);
			} else {
				// H2 Database connection (embedded)
				Class.forName("org.h2.Driver");
				String url = "jdbc:h2:mem:hospital;DB_CLOSE_DELAY=-1;MODE=MySQL;INIT=CREATE SCHEMA IF NOT EXISTS hospital";
				String user = "sa";
				String pass = "";
				conn = DriverManager.getConnection(url, user, pass);
				
				// Create tables if they don't exist
				createTablesIfNotExist(conn);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
		return conn;
	}
	
	private static void createTablesIfNotExist(Connection conn) throws SQLException {
		String createUsersTable = "CREATE TABLE IF NOT EXISTS users (" +
			"id INT AUTO_INCREMENT PRIMARY KEY," +
			"fullName VARCHAR(100) NOT NULL," +
			"email VARCHAR(100) UNIQUE NOT NULL," +
			"password VARCHAR(100) NOT NULL" +
			")";
		
		String createSpecialistsTable = "CREATE TABLE IF NOT EXISTS specialists (" +
			"id INT AUTO_INCREMENT PRIMARY KEY," +
			"specialistName VARCHAR(100) NOT NULL" +
			")";
		
		String createDoctorsTable = "CREATE TABLE IF NOT EXISTS doctors (" +
			"id INT AUTO_INCREMENT PRIMARY KEY," +
			"fullName VARCHAR(100) NOT NULL," +
			"dob VARCHAR(100) NOT NULL," +
			"qualification VARCHAR(100) NOT NULL," +
			"specialist VARCHAR(100) NOT NULL," +
			"email VARCHAR(100) UNIQUE NOT NULL," +
			"mobNo VARCHAR(100) NOT NULL," +
			"password VARCHAR(100) NOT NULL" +
			")";
		
		String createAppointmentsTable = "CREATE TABLE IF NOT EXISTS appointments (" +
			"id INT AUTO_INCREMENT PRIMARY KEY," +
			"userId INT NOT NULL," +
			"fullName VARCHAR(100) NOT NULL," +
			"gender VARCHAR(100) NOT NULL," +
			"age VARCHAR(100) NOT NULL," +
			"appointmentDate VARCHAR(100) NOT NULL," +
			"email VARCHAR(100) NOT NULL," +
			"phone VARCHAR(100) NOT NULL," +
			"diseases VARCHAR(100) NOT NULL," +
			"doctorId INT NOT NULL," +
			"address VARCHAR(100) NOT NULL," +
			"status VARCHAR(100) NOT NULL" +
			")";
		
		// Execute table creation
		conn.createStatement().execute(createUsersTable);
		conn.createStatement().execute(createSpecialistsTable);
		conn.createStatement().execute(createDoctorsTable);
		conn.createStatement().execute(createAppointmentsTable);
		
		// Insert some sample data
		insertSampleData(conn);
	}
	
	private static void insertSampleData(Connection conn) throws SQLException {
		// Insert sample specialists
		String[] specialists = {"Cardiology", "Dermatology", "Neurology", "Pediatrics", "Orthopedics"};
		for (String specialist : specialists) {
			try {
				String insertSpecialist = "INSERT INTO specialists (specialistName) VALUES ('" + specialist + "')";
				conn.createStatement().execute(insertSpecialist);
			} catch (SQLException e) {
				// Ignore if data already exists
			}
		}
		
		// Insert sample doctor
		try {
			String insertDoctor = "INSERT INTO doctors (fullName, dob, qualification, specialist, email, mobNo, password) VALUES " +
				"('Dr. John Smith', '1980-01-15', 'MD Cardiology', 'Cardiology', 'doctor@gmail.com', '1234567890', 'doctor')";
			conn.createStatement().execute(insertDoctor);
		} catch (SQLException e) {
			// Ignore if data already exists
		}
		
		// Insert sample user
		try {
			String insertUser = "INSERT INTO users (fullName, email, password) VALUES " +
				"('John Doe', 'user@gmail.com', 'user')";
			conn.createStatement().execute(insertUser);
		} catch (SQLException e) {
			// Ignore if data already exists
		}
	}
}
