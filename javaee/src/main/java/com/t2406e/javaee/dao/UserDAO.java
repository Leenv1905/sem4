package com.t2406e.javaee.dao;


import com.t2406e.javaee.model.User;
import com.t2406e.javaee.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    public User validateLogin(String username, String password) {
        String sql = "SELECT id, username FROM account WHERE username = ? AND password = ?";


        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("id");
                    String uname = rs.getString("username");
                    // không trả password ra model
                    return new User(id, uname);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // demo: in lỗi ra console
        }

        return null; // không tìm thấy user
    }
}
