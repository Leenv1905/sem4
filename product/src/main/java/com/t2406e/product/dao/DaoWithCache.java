//package com.t2406e.product.dao;
//
//import com.t2406e.product.model.Product;
//import com.t2406e.product.util.DBConnection;
//
//import java.sql.*;
//import java.util.*;
//import java.util.concurrent.ConcurrentHashMap;
//
//public class ProductDAO {
//
//    // Cache sản phẩm theo id
//    private static final ConcurrentHashMap<Integer, Product> productCache = new ConcurrentHashMap<>();
//
//    /* ================= INSERT ================= */
//    public boolean insert(Product product) {
//        String sql = "INSERT INTO products(name, price, quantity, description, image) VALUES (?, ?, ?, ?, ?)";
//        try (Connection conn = DBConnection.getConnection();
//             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
//
//            ps.setString(1, product.getName());
//            ps.setDouble(2, product.getPrice());
//            ps.setInt(3, product.getQuantity());
//            ps.setString(4, product.getDescription());
//            ps.setString(5, product.getImage());
//
//            int rows = ps.executeUpdate();
//            if (rows > 0) {
//                ResultSet rs = ps.getGeneratedKeys();
//                if (rs.next()) {
//                    product.setId(rs.getInt(1));
//                }
//                // cập nhật cache
//                productCache.put(product.getId(), product);
//                return true;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
//
//    /* ================= UPDATE ================= */
//    public boolean update(Product product) {
//        String sql = """
//            UPDATE products
//            SET name = ?, price = ?, quantity = ?, description = ?, image = ?
//            WHERE id = ?
//        """;
//        try (Connection conn = DBConnection.getConnection();
//             PreparedStatement ps = conn.prepareStatement(sql)) {
//
//            ps.setString(1, product.getName());
//            ps.setDouble(2, product.getPrice());
//            ps.setInt(3, product.getQuantity());
//            ps.setString(4, product.getDescription());
//            ps.setString(5, product.getImage());
//            ps.setInt(6, product.getId());
//
//            int rows = ps.executeUpdate();
//            if (rows > 0) {
//                // cập nhật cache
//                productCache.put(product.getId(), product);
//                return true;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
//
//    /* ================= SAVE (UPSERT) ================= */
//    public boolean save(Product product) {
//        String sql = """
//            INSERT INTO products(id, name, price, quantity, description, image)
//            VALUES (?, ?, ?, ?, ?, ?)
//            ON DUPLICATE KEY UPDATE
//                name = VALUES(name),
//                price = VALUES(price),
//                quantity = VALUES(quantity),
//                description = VALUES(description),
//                image = VALUES(image)
//        """;
//        try (Connection conn = DBConnection.getConnection();
//             PreparedStatement ps = conn.prepareStatement(sql)) {
//
//            ps.setInt(1, product.getId());
//            ps.setString(2, product.getName());
//            ps.setDouble(3, product.getPrice());
//            ps.setInt(4, product.getQuantity());
//            ps.setString(5, product.getDescription());
//            ps.setString(6, product.getImage());
//
//            int rows = ps.executeUpdate();
//            if (rows > 0) {
//                // cập nhật cache
//                productCache.put(product.getId(), product);
//                return true;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
//
//    /* ================= GET ALL ================= */
//    public List<Product> getAll() {
//        List<Product> list = new ArrayList<>();
//        String sql = "SELECT * FROM products";
//        try (Connection conn = DBConnection.getConnection();
//             PreparedStatement ps = conn.prepareStatement(sql);
//             ResultSet rs = ps.executeQuery()) {
//
//            while (rs.next()) {
//                Product p = mapResultSetToProduct(rs);
//                list.add(p);
//                // đồng bộ cache
//                productCache.put(p.getId(), p);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return list;
//    }
//
//    /* ================= GET BY ID ================= */
//    public Product getById(int id) {
//        // Ưu tiên đọc từ cache
//        Product cached = productCache.get(id);
//        if (cached != null) {
//            return cached;
//        }
//
//        String sql = "SELECT * FROM products WHERE id = ?";
//        try (Connection conn = DBConnection.getConnection();
//             PreparedStatement ps = conn.prepareStatement(sql)) {
//
//            ps.setInt(1, id);
//            ResultSet rs = ps.executeQuery();
//            if (rs.next()) {
//                Product p = mapResultSetToProduct(rs);
//                productCache.put(id, p); // cập nhật cache
//                return p;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    /* ================= DELETE ================= */
//    public boolean delete(int id) {
//        String sql = "DELETE FROM products WHERE id = ?";
//        try (Connection conn = DBConnection.getConnection();
//             PreparedStatement ps = conn.prepareStatement(sql)) {
//
//            ps.setInt(1, id);
//            int rows = ps.executeUpdate();
//            if (rows > 0) {
//                // xóa khỏi cache
//                productCache.remove(id);
//                return true;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
//
//    /* ================= MAP RESULT ================= */
//    private Product mapResultSetToProduct(ResultSet rs) throws SQLException {
//        Product p = new Product();
//        p.setId(rs.getInt("id"));
//        p.setName(rs.getString("name"));
//        p.setPrice(rs.getDouble("price"));
//        p.setQuantity(rs.getInt("quantity"));
//        p.setDescription(rs.getString("description"));
//        p.setImage(rs.getString("image"));
//        return p;
//    }
//}

//Đọc theo id (getById): ưu tiên lấy từ cache, nếu chưa có thì đọc DB rồi put vào cache.
//Đọc tất cả (getAll): luôn đọc DB, đồng bộ cache.
//Thêm (insert), sửa (update), save (save): sau khi DB thành công thì cập nhật cache.
//Xóa (delete): sau khi DB thành công thì remove khỏi cache.

//1. Cấu trúc cache
//        java
//// Khai báo trong ProductDAO
//private static final ConcurrentHashMap<Integer, Product> productCache = new ConcurrentHashMap<>();
//Key: id sản phẩm
//
//Value: đối tượng Product
//
//2. Khi nào đọc từ cache
//getById(id):
//
//Nếu productCache đã có key = id → trả về ngay Product từ cache.
//
//Nếu chưa có → truy vấn DB, sau đó put vào cache.
//
//Ví dụ:
//
//java
//public Product getById(int id) {
//    Product cached = productCache.get(id);
//    if (cached != null) {
//        return cached;
//    }
//    // Nếu chưa có trong cache → đọc DB
//    Product product = null;
//    try (Connection conn = DBConnection.getConnection();
//         PreparedStatement ps = conn.prepareStatement("SELECT * FROM products WHERE id=?")) {
//        ps.setInt(1, id);
//        ResultSet rs = ps.executeQuery();
//        if (rs.next()) {
//            product = mapResultSetToProduct(rs);
//            productCache.put(id, product); // cập nhật cache
//        }
//    } catch (Exception e) {
//        e.printStackTrace();
//    }
//    return product;
//}
//3. Khi nào đọc DB
//getAll(): thường vẫn đọc DB để đảm bảo danh sách mới nhất. Sau khi đọc, có thể đồng bộ cache:
//
//Với mỗi sản phẩm lấy được từ DB, put vào cache.
//
//getById(id): chỉ đọc DB nếu cache chưa có hoặc dữ liệu trong cache đã stale (nếu bạn muốn thêm cơ chế hết hạn).
//
//        4. Khi nào cập nhật cache
//insert(product): sau khi insert DB thành công → put vào cache với id mới.
//
//update(product): sau khi update DB thành công → replace trong cache.
//
//delete(id): sau khi delete DB thành công → remove khỏi cache.
//
//save(product): sau khi upsert DB thành công → put/replace trong cache.
//
//5. Tóm tắt luồng
//Đọc sản phẩm theo id: ưu tiên cache → nếu chưa có thì đọc DB rồi cache lại.
//
//Đọc toàn bộ sản phẩm: đọc DB, đồng bộ cache.
//
//Thêm / sửa / xóa: thao tác DB trước, nếu thành công thì cập nhật cache tương ứng.
//
//        6. Lợi ích
//Giảm số lần truy vấn DB khi nhiều lần đọc cùng một sản phẩm.
//
//ConcurrentHashMap an toàn khi nhiều thread truy cập (Servlet container có nhiều request đồng thời).
