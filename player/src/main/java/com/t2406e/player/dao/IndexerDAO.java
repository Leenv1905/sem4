package com.t2406e.player.dao;

import com.t2406e.player.model.Indexer;
import com.t2406e.player.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IndexerDAO {

    public List<Indexer> findAll() {
        List<Indexer> list = new ArrayList<>();
        String sql = "SELECT * FROM indexer";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Indexer i = new Indexer();
                i.setIndexId(rs.getInt("index_id"));
                i.setName(rs.getString("name"));
                i.setValueMin(rs.getFloat("valueMin"));
                i.setValueMax(rs.getFloat("valueMax"));
                list.add(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
