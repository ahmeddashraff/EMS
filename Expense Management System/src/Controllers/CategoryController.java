package Controllers;

import Entities.Report;

import Views.Connections;
import Views.EMSConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class CategoryController {

    public static ArrayList<String> getAllCategories() {
        try {
            Connections con = new EMSConnection();
            Connection conn = con.getConnection();
            ArrayList<String> categories = new ArrayList<>();
            PreparedStatement st = conn.prepareStatement("SELECT * FROM CATEGORIES");
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                categories.add(rs.getString("NAME"));
            }
            return categories;
        } catch (SQLException e) {
            e.printStackTrace();
            return null; //
        }
    }
}
