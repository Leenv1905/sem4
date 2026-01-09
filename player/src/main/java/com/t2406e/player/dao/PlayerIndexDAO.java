package com.t2406e.player.dao;

import com.t2406e.player.model.PlayerView;
import com.t2406e.player.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PlayerIndexDAO {

    public void insert(int playerId, int indexId, float value) throws Exception {
        String sql = "INSERT INTO player_index(player_id,index_id,value) VALUES (?,?,?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, playerId);
            ps.setInt(2, indexId);
            ps.setFloat(3, value);
            ps.executeUpdate();
        }
    }

    public void deleteByPlayerId(int playerId) throws Exception {
        String sql = "DELETE FROM player_index WHERE player_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, playerId);
            ps.executeUpdate();
        }
    }

    public PlayerView findById(int id) {
        String sql = """
        SELECT 
            pi.id, p.player_id, p.name, p.age,
            i.index_id, i.name, pi.value
        FROM player_index pi
        JOIN player p ON pi.player_id = p.player_id
        JOIN indexer i ON pi.index_id = i.index_id
        WHERE pi.id = ?
    """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                PlayerView pv = new PlayerView();
                pv.setPlayerIndexId(rs.getInt("id"));
                pv.setPlayerId(rs.getInt("player_id"));
                pv.setPlayerName(rs.getString("name"));
                pv.setAge(rs.getString("age"));
                pv.setIndexId(rs.getInt("index_id"));
                pv.setIndexName(rs.getString("name"));
                pv.setValue(rs.getFloat("value"));
                return pv;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(int piId, int indexId, float value) throws Exception {
        String sql = """
        UPDATE player_index
        SET index_id = ?, value = ?
        WHERE id = ?
    """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, indexId);
            ps.setFloat(2, value);
            ps.setInt(3, piId);
            ps.executeUpdate();
        }
    }

    public void deleteById(int id) throws Exception {
        String sql = "DELETE FROM player_index WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

}
