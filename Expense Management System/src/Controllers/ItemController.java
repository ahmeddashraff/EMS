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

public class ItemController {

    private Item item;
    Connections con = new EMSConnection();

    public ItemController() {
    } 
    public ItemController(Item item) {
        this.item = item;
    }

    public void create() {
        try {
            item.generate_id();
            Connection conn = con.getConnection();
            PreparedStatement st = conn.prepareStatement("INSERT INTO ITEMS VALUES (?, ?, ?, ?, ?, ?)");
            st.setInt(1, item.getId());
            st.setDouble(2, item.getAmount());
            st.setString(3, item.getName());
            st.setInt(4, item.getReport_id());
            st.setInt(5, item.getCategory_id());
            st.setString(6, "ayhaga");

            System.out.println("Executing query: " + st.toString());

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
//                JOptionPane.showMessageDialog(null, "Item created successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "Item creation failed");
            }


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Item creation failed: " + e.getMessage());
        }
    }

    public ArrayList<Item> getAllItemsByReportId(int report_id) {
        ArrayList<Item> items = new ArrayList<>();
        try {
            Connection conn = con.getConnection();
            PreparedStatement st = conn.prepareStatement("SELECT * FROM ITEMS WHERE REPORT_ID = ?");
            st.setInt(1, report_id);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                item=new Item();
                item.setName(rs.getString("NAME"));
                item.setAmount(rs.getDouble("AMOUNT"));
                item.setCategory_id(rs.getInt("CATEGORY_ID"));
                item.setId(rs.getInt("ID"));

                items.add(item);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error occurred while fetching report: " + e.getMessage());
        }
        return items;
    }
    
    
    public Item getItemById(int id) {
        try {
            Connection conn = con.getConnection();
            PreparedStatement st = conn.prepareStatement("SELECT * FROM ITEMS WHERE ID = ?");
            st.setInt(1, id);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                item = new Item();
                item.setName(rs.getString("NAME"));
                item.setAmount(rs.getDouble("AMOUNT"));
                item.setCategory_id(rs.getInt("CATEGORY_ID"));
                item.setId(rs.getInt("ID"));

//                JOptionPane.showMessageDialog(null, "Item found successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "No Item found with ID: " + id);
                return null;
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error occurred while fetching Item: " + e.getMessage());
            return null;
        }
        return item;
    }
    
    public boolean delete(int id) {
        try {
            Connection conn = con.getConnection();
            PreparedStatement st = conn.prepareStatement("DELETE FROM ITEMS WHERE ID = ?");
            st.setInt(1, id);

            System.out.println("Executing query: " + st.toString());

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Item deleted successfully!");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Item not found");
                return false;
            }


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Item deletion failed: " + e.getMessage());
            return false;
        }
    }
    
    
    
    
    public void deleteByReportId(int id) {
        try {
            Connection conn = con.getConnection();
            PreparedStatement st = conn.prepareStatement("DELETE FROM ITEMS WHERE REPORT_ID = ?");
            st.setInt(1, id);

            System.out.println("Executing query: " + st.toString());

            st.executeUpdate();

          

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Item creation failed: " + e.getMessage());
        }
    }
}
