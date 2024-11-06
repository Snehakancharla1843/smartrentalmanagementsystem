import java.sql.*;

public class User {
    // Register a new user
    public static void registerUser(Connection conn, String username, String password) throws SQLException {
        String query = "INSERT INTO users (username, password) VALUES (?, ?)";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, username);
        stmt.setString(2, password);
        try {
            stmt.executeUpdate();
            System.out.println("User registered successfully!");
        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {  // Duplicate entry error code for MySQL
                System.out.println("Username already exists. Please try a different username.");
            } else {
                throw e;
            }
        }
    }

    // Login a user
    public static boolean loginUser(Connection conn, String username, String password) throws SQLException {
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, username);
        stmt.setString(2, password);
        ResultSet rs = stmt.executeQuery();
        return rs.next();  // Returns true if a matching record is found
    }
}
