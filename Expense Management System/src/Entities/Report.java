package Entities;

import Views.Connections;
import Views.EMSConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Report {
    private int id;
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    private double total_amount;
    private String ApprovalStatus;
    private String description;
    private int user_id;


    public void generate_id() {
        Connections con = new EMSConnection();
        try {
            Connection connection = con.getConnection();
            PreparedStatement st = connection.prepareStatement("SELECT MAX(id) AS last_id FROM REPORTS");
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                setId(rs.getInt("last_id") + 1);
            } else {
                setId(1);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void setId(int id) {
        this.id = id;

    }


    public int getId() {
        return id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setTotal_amount(double total_amount) {
        this.total_amount = total_amount;
    }

    public double getTotal_amount() {
        return total_amount;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setApprovalStatus(String ApprovalStatus) {
        this.ApprovalStatus = ApprovalStatus;
    }

    public String getApprovalStatus() {
        return ApprovalStatus;
    }
}
