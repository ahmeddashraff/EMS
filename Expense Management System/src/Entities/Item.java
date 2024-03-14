package Entities;

import Views.Connections;
import Views.EMSConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Item {
    private int id;
    private double amount;
    private String name;
    private int report_id;
    private int category_id;
    private String reciept_img;

    public void generate_id() {
        Connections con = new EMSConnection();
        try {
            Connection connection = con.getConnection();
            PreparedStatement st = connection.prepareStatement("SELECT MAX(id) AS last_id FROM ITEMS");
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

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setReport_id(int report_id) {
        this.report_id = report_id;
    }

    public int getReport_id() {
        return report_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setReciept_img(String reciept_img) {
        this.reciept_img = reciept_img;
    }

    public String getReciept_img() {
        return reciept_img;
    }

}
