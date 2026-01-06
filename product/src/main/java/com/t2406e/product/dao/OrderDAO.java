package com.t2406e.product.dao;

import com.t2406e.product.model.Order;
import com.t2406e.product.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {
//    Chỉ dùng 1 Connection
//    setAutoCommit(false)
//    commit() khi thành công
//    rollback() khi lỗi
//    DAO không tự mở connection trong transaction
    public int createOrder(Connection conn, int userId, double totalPrice)
            throws SQLException {

        String sql = """
            INSERT INTO orders(user_id, total_price, status)
            VALUES (?, ?, 'NEW')
        """;

        try (PreparedStatement ps =
                     conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, userId);
            ps.setDouble(2, totalPrice);
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        throw new SQLException("Không tạo được order");
    }
    /* ===== USER: LỊCH SỬ ĐƠN ===== */
    public List<Order> getByUserId(int userId) throws SQLException {
        String sql = """
            SELECT * FROM orders
            WHERE user_id = ?
            ORDER BY created_at DESC
        """;

        List<Order> list = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Order o = map(rs);
                list.add(o);
            }
        }
        return list;
    }

    /* ===== UPDATE STATUS ===== */
    public void updateStatus(Connection conn, int orderId, String status)
            throws SQLException {

        String sql = "UPDATE orders SET status = ? WHERE id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, status);
            ps.setInt(2, orderId);
            ps.executeUpdate();
        }
    }

    /* ===== DELETE ORDER ===== */
    public void delete(Connection conn, int orderId) throws SQLException {
        try (PreparedStatement ps =
                     conn.prepareStatement("DELETE FROM orders WHERE id = ?")) {
            ps.setInt(1, orderId);
            ps.executeUpdate();
        }
    }
// LẤY ĐƠN HÀNG THEO ID NGƯỜI DÙNG
    public List<Order> getOrderByUserId(int userId) throws SQLException {
        String sql = """
        SELECT * FROM orders
        WHERE user_id = ?
        ORDER BY created_at DESC
    """;

        List<Order> list = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Order o = new Order();
                o.setId(rs.getInt("id"));
                o.setUserId(rs.getInt("user_id"));
                o.setTotalPrice(rs.getDouble("total_price"));
                o.setStatus(rs.getString("status"));
                o.setCreatedAt(rs.getTimestamp("created_at"));
                list.add(o);
            }
        }
        return list;
    }

    // LẤY TẤT CẢ ĐƠN HÀNG CHO ADMIN XEM
    public List<Order> getAll() throws SQLException {
        List<Order> list = new ArrayList<>();
        String sql = "SELECT * FROM orders ORDER BY created_at DESC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Order o = new Order();
                o.setId(rs.getInt("id"));
                o.setUserId(rs.getInt("user_id"));
                o.setTotalPrice(rs.getDouble("total_price"));
                o.setStatus(rs.getString("status"));
                o.setCreatedAt(rs.getTimestamp("created_at"));
                list.add(o);
            }
        }
        return list;
    }



    private Order map(ResultSet rs) throws SQLException {
        Order o = new Order();
        o.setId(rs.getInt("id"));
        o.setUserId(rs.getInt("user_id"));
        o.setTotalPrice(rs.getDouble("total_price"));
        o.setStatus(rs.getString("status"));
        o.setCreatedAt(rs.getTimestamp("created_at"));
        return o;
    }
}
