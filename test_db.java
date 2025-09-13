import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;

public class test_db {
    public static void main(String[] args) {
        try {
            // H2 Database connection
            Class.forName("org.h2.Driver");
            String url = "jdbc:h2:mem:hospital;DB_CLOSE_DELAY=-1;MODE=MySQL";
            String user = "sa";
            String pass = "";
            Connection conn = DriverManager.getConnection(url, user, pass);
            
            // Create tables
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
            
            conn.createStatement().execute(createDoctorsTable);
            System.out.println("Table created successfully!");
            
            // Insert sample doctor
            String insertDoctor = "INSERT INTO doctors (fullName, dob, qualification, specialist, email, mobNo, password) VALUES " +
                "('Dr. John Smith', '1980-01-15', 'MD Cardiology', 'Cardiology', 'john.smith@hospital.com', '1234567890', 'password123')";
            
            conn.createStatement().execute(insertDoctor);
            System.out.println("Sample doctor inserted successfully!");
            
            // Test login
            String selectDoctor = "SELECT * FROM doctors WHERE email = ? AND password = ?";
            java.sql.PreparedStatement stmt = conn.prepareStatement(selectDoctor);
            stmt.setString(1, "john.smith@hospital.com");
            stmt.setString(2, "password123");
            
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                System.out.println("Login test successful! Doctor found: " + rs.getString("fullName"));
            } else {
                System.out.println("Login test failed! Doctor not found.");
            }
            
            conn.close();
            System.out.println("Database test completed successfully!");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

