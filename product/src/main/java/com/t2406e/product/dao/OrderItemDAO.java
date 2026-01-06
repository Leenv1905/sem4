package com.t2406e.product.dao;

import com.t2406e.product.model.OrderItem;
import com.t2406e.product.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderItemDAO {
//Transactional giảm thiểu việc Có order mà không có item
//    Trừ kho nhưng không có order
    // Nếu lỗi sẽ ROLLBACK TOÀN BỘ
    public void addItem(Connection conn, int orderId,
                        int productId, double price, int quantity)
            throws SQLException {

        String sql = """
            INSERT INTO order_items(order_id, product_id, price, quantity)
            VALUES (?, ?, ?, ?)
        """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, orderId);
            ps.setInt(2, productId);
            ps.setDouble(3, price);
            ps.setInt(4, quantity);
            ps.executeUpdate();
        }
    }
    /* ===== LẤY ITEM ĐỂ HOÀN KHO ===== */
    public Map<Integer, Integer> getItemsForRestore(Connection conn, int orderId)
            throws SQLException {

        String sql = """
            SELECT product_id, quantity
            FROM order_items
            WHERE order_id = ?
        """;

        Map<Integer, Integer> map = new HashMap<>();

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                map.put(rs.getInt("product_id"), rs.getInt("quantity"));
            }
        }
        return map;
    }

    public void deleteByOrderId(Connection conn, int orderId)
            throws SQLException {

        try (PreparedStatement ps =
                     conn.prepareStatement("DELETE FROM order_items WHERE order_id = ?")) {
            ps.setInt(1, orderId);
            ps.executeUpdate();
        }
    }

    public List<OrderItem> getByOrderId(int orderId) throws SQLException {

        String sql = """
            SELECT id, order_id, product_id, price, quantity
            FROM order_items
            WHERE order_id = ?
        """;

        List<OrderItem> list = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                OrderItem item = new OrderItem();
                item.setId(rs.getInt("id"));
                item.setOrderId(rs.getInt("order_id"));
                item.setProductId(rs.getInt("product_id"));
                item.setPrice(rs.getDouble("price"));
                item.setQuantity(rs.getInt("quantity"));
                list.add(item);
            }
        }
        return list;
    }
}
