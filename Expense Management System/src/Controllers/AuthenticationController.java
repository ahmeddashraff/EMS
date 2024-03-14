package Controllers;

import Entities.User;

import Views.Connections;
import Views.EMSConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class AuthenticationController {
    
    private static User user;
    Connections con = new EMSConnection();

    public String login(String username, String password, String role) {
        try {
            Connection conn = con.getConnection();
            PreparedStatement st =
                conn.prepareStatement("SELECT * FROM USERS WHERE username=? AND password=? AND role=?");
            st.setString(1, username);
            st.setString(2, password);
            st.setString(3, role);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setId(Integer.parseInt(rs.getString("ID")));
                user.setUsername(rs.getString("USERNAME"));
                user.setRole(rs.getString("ROLE"));
                JOptionPane.showMessageDialog(null, "Login successful!");
                return "success";
            } else {
                JOptionPane.showMessageDialog(null, "Invalid username or password");
                return "fail";
            }
        } catch (SQLException e) {
            return "fail";
        }
    }

    public void register(String username, String password) {
        try {
            Connection conn = con.getConnection();

            PreparedStatement st = conn.prepareStatement("INSERT INTO USERS (username, password) VALUES (?, ?)");
            st.setString(1, username);
            st.setString(2, password);

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                // Registration successful
                JOptionPane.showMessageDialog(null, "Registration successful!");
            } else {
                // Registration failed
                JOptionPane.showMessageDialog(null, "Registration failed");
            }
            // JOptionPane.showMessageDialog(null, "RECORD deleted  !!!!!!!!!!!!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error:" + e.getMessage());
        }
    }
    public static User getUser() {
        return user;
    }
}

