package com.t2406e.javaee.dao;

import com.t2406e.javaee.model.Product;
import com.t2406e.javaee.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    /* ================= CREATE ================= */
    public boolean insert(Product product) {
        String sql = "INSERT INTO products(name, price, quantity, description, image) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, product.getName());
            ps.setDouble(2, product.getPrice());
            ps.setInt(3, product.getQuantity());
            ps.setString(4, product.getDescription());
            ps.setString(5, product.getImage());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /* ================= READ ALL ================= */
    public List<Product> getAll() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM products";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Product p = mapResultSetToProduct(rs);
                list.add(p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /* ================= READ BY ID ================= */
    public Product getById(int id) {
        String sql = "SELECT * FROM products WHERE id = ?";
        Product product = null;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                product = mapResultSetToProduct(rs);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return product;
    }

    /* ================= UPDATE ================= */
    public boolean update(Product product) {
        String sql = """
            UPDATE products 
            SET name = ?, price = ?, quantity = ?, description = ?, image = ?
            WHERE id = ?
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, product.getName());
            ps.setDouble(2, product.getPrice());
            ps.setInt(3, product.getQuantity());
            ps.setString(4, product.getDescription());
            ps.setString(5, product.getImage());
            ps.setInt(6, product.getId());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /* ================= DELETE ================= */
    public boolean delete(int id) {
        String sql = "DELETE FROM products WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /* ================= MAP RESULT ================= */
    private Product mapResultSetToProduct(ResultSet rs) throws SQLException {
        Product p = new Product();
        p.setId(rs.getInt("id"));
        p.setName(rs.getString("name"));
        p.setPrice(rs.getDouble("price"));
        p.setQuantity(rs.getInt("quantity"));
        p.setDescription(rs.getString("description"));
        p.setImage(rs.getString("image"));
        return p;
    }
}
