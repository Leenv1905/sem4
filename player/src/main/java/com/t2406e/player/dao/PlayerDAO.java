package com.t2406e.player.dao;

import com.t2406e.player.model.Player;
import com.t2406e.player.model.PlayerView;
import com.t2406e.player.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerDAO {

    public int insert(Player p) throws SQLException {
        String sql = """
        INSERT INTO player(name, full_name, age, index_id)
        VALUES (?,?,?,?)
    """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps =
                     conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, p.getName());
            ps.setString(2, p.getFullName());
            ps.setString(3, p.getAge());
            ps.setInt(4, 1); // dummy

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1); // player_id
            }
        }
        return 0;
    }


    public List<Player> findAll() {
        List<Player> list = new ArrayList<>();
        String sql = "SELECT * FROM player";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Player p = new Player();
                p.setPlayerId(rs.getInt("player_id"));
                p.setName(rs.getString("name"));
                p.setFullName(rs.getString("full_name"));
                p.setAge(rs.getString("age"));
                list.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM player WHERE player_id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public Player findById(int id) {
        String sql = "SELECT * FROM player WHERE player_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Player p = new Player();
                p.setPlayerId(rs.getInt("player_id"));
                p.setName(rs.getString("name"));
                p.setFullName(rs.getString("full_name"));
                p.setAge(rs.getString("age"));
                return p;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(Player p) throws SQLException {
        String sql = """
        UPDATE player
        SET name=?, full_name=?, age=?
        WHERE player_id=?
    """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, p.getName());
            ps.setString(2, p.getFullName());
            ps.setString(3, p.getAge());
            ps.setInt(4, p.getPlayerId());
            ps.executeUpdate();
        }
    }

    public void updatePlayer(int playerId, String name, String fullName, String age)
            throws Exception {

        String sql = """
        UPDATE player
        SET name = ?, full_name = ?, age = ?
        WHERE player_id = ?
    """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setString(2, fullName);
            ps.setString(3, age);
            ps.setInt(4, playerId);
            ps.executeUpdate();
        }
    }

    // Phương thức này hiển thị dữ liệu có liên kết bảng lấy dữ liệu từ PlayerView
public List<PlayerView> findAllWithIndex() {
    List<PlayerView> list = new ArrayList<>();

    String sql = """
    SELECT
        pi.id            AS pi_id,
        p.player_id      AS p_id,
        p.name,
        p.full_name,
        p.age,
        i.index_id,
        i.name           AS index_name,
        pi.value
    FROM player_index pi
    JOIN player p ON pi.player_id = p.player_id
    JOIN indexer i ON pi.index_id = i.index_id
""";


    try (Connection conn = DBConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            PlayerView pv = new PlayerView();
            pv.setPlayerIndexId(rs.getInt("pi_id"));
            pv.setPlayerId(rs.getInt("p_id"));
            pv.setPlayerName(rs.getString("name"));
            pv.setFullName(rs.getString("full_name"));
            pv.setAge(rs.getString("age"));
            pv.setIndexId(rs.getInt("index_id"));
            pv.setIndexName(rs.getString("index_name"));
            pv.setValue(rs.getFloat("value"));
            list.add(pv);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}



}
