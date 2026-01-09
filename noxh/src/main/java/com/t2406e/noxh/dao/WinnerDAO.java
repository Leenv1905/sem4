package com.t2406e.noxh.dao;

import com.t2406e.noxh.model.Winner;
import com.t2406e.noxh.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.t2406e.noxh.model.WinnerDetail;


public class WinnerDAO {

    // Lưu 1 winner
    public void insert(Winner winner) {
        String sql = "INSERT INTO winner (applicant_id) VALUES (?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, winner.getApplicantId());
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Lấy danh sách winner
    public List<Winner> findAll() {
        List<Winner> list = new ArrayList<>();
        String sql = "SELECT * FROM winner";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Winner w = new Winner();
                w.setId(rs.getInt("id"));
                w.setApplicantId(rs.getInt("applicant_id"));
                list.add(w);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // Kiểm tra đã bốc thăm chưa
    public boolean hasWinner() {
        String sql = "SELECT COUNT(*) FROM winner";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public List<WinnerDetail> findWinnerDetails() {
        List<WinnerDetail> list = new ArrayList<>();

        String sql = """
        SELECT w.applicant_id, a.name, a.status
        FROM winner w
        JOIN applicant a ON w.applicant_id = a.id
        ORDER BY w.id
    """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                WinnerDetail wd = new WinnerDetail();
                wd.setApplicantId(rs.getInt("applicant_id"));
                wd.setName(rs.getString("name"));
                wd.setStatus(rs.getInt("status") == 1);
                list.add(wd);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // Xóa toàn bộ kết quả bốc thăm để cho bốc lại
    public boolean deleteAll() {
        String sql = "DELETE FROM winner";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            return ps.executeUpdate() >= 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


}
