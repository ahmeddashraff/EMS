package Controllers;

import Entities.Item;
import Entities.Report;

import Views.Connections;
import Views.EMSConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class ReportController {

    private Report report;
    Connections con = new EMSConnection();

    public ReportController() {
    }

    public ReportController(Report report) {
        this.report = report;
    }


    public void create() {
        try {
            Connection conn = con.getConnection();
            report.generate_id();
            PreparedStatement st = conn.prepareStatement("INSERT INTO REPORTS VALUES (?, ?, ?, ?, ?, ?)");
            st.setInt(1, report.getId());
            st.setDouble(2, report.getTotal_amount());
            st.setString(3, "pending");
            st.setInt(4, report.getUser_id());
            st.setString(5, report.getDescription());
            st.setString(6, report.getName());

            System.out.println("Executing query: " + st.toString());

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Report created successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "Report creation failed");
            }


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Report creation failed");
        }
    }

    public void update() {
        try {
            Connection conn = con.getConnection();
            PreparedStatement st =
                conn.prepareStatement("UPDATE REPORTS SET NAME = ?, DESCRIPTION = ?, TOTAL_AMOUNT = ? WHERE ID = ?");
            st.setDouble(3, report.getTotal_amount());
            st.setString(2, report.getDescription());
            st.setString(1, report.getName());
            st.setInt(4, report.getId());
            st.executeUpdate();
            JOptionPane.showMessageDialog(null, "Updated!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "cannot be updated!");

        }

    }

    public Report getReportById(int id) {
        try {
            Connection conn = con.getConnection();
            PreparedStatement st = conn.prepareStatement("SELECT * FROM REPORTS WHERE ID = ? AND USER_ID = ?");
            st.setInt(1, id);
            st.setInt(2, AuthenticationController.getUser().getId());

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                report = new Report();
                report.setId(rs.getInt("ID"));
                report.setUser_id(rs.getInt("USER_ID"));
                report.setTotal_amount(rs.getDouble("TOTAL_AMOUNT"));
                report.setDescription(rs.getString("DESCRIPTION"));
                report.setName(rs.getString("NAME"));
                report.setApprovalStatus(rs.getString("APPROVAL_STATUS"));

                JOptionPane.showMessageDialog(null, "Report found successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "No report found with ID: " + id);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error occurred while fetching report: " + e.getMessage());
        }
        return report;
    }

    public ArrayList<Report> getAllEmployeeReports() {
        try {
            ArrayList<Report> reports = new ArrayList<>();
            Connection conn = con.getConnection();
            PreparedStatement st = conn.prepareStatement("SELECT * FROM REPORTS WHERE USER_ID = ?");
            st.setInt(1, AuthenticationController.getUser().getId());

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                report = new Report();
                report.setId(rs.getInt("ID"));
                report.setUser_id(rs.getInt("USER_ID"));
                report.setTotal_amount(rs.getDouble("TOTAL_AMOUNT"));
                report.setDescription(rs.getString("DESCRIPTION"));
                report.setName(rs.getString("NAME"));
                report.setApprovalStatus(rs.getString("APPROVAL_STATUS"));
                reports.add(report);
            }
            return reports;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error occurred while fetching report: " + e.getMessage());
            return null;
        }
    }
    
    public void delete(int id) {
        try {
            Connection conn = con.getConnection();
            PreparedStatement st = conn.prepareStatement("DELETE FROM REPORTS WHERE ID = ?");
            st.setInt(1, id);

            System.out.println("Executing query: " + st.toString());

            int rowsAffected = st.executeUpdate();
            
            if (rowsAffected > 0) {
                new ItemController().deleteByReportId(id);
                JOptionPane.showMessageDialog(null, "Report deleted successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "Report not found");
            }


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Report creation failed: " + e.getMessage());
        }
    }

}
