package com.t2406e.player.dao;

import com.t2406e.player.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;

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

}
