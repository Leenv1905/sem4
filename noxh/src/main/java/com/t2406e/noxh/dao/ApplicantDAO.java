package com.t2406e.noxh.dao;

import com.t2406e.noxh.model.Applicant;
import com.t2406e.noxh.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ApplicantDAO {

    // CREATE
    public boolean insert(Applicant applicant) {
        String sql = "INSERT INTO applicant(name, status) VALUES (?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, applicant.getName());
            ps.setBoolean(2, applicant.isStatus());
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // READ ALL
    public List<Applicant> findAll() {
        List<Applicant> list = new ArrayList<>();
        String sql = "SELECT * FROM applicant";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Applicant a = new Applicant();
                a.setId(rs.getInt("id"));
                a.setName(rs.getString("name"));
                a.setStatus(rs.getBoolean("status"));
                list.add(a);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // READ BY ID
    public Applicant findById(int id) {
        String sql = "SELECT * FROM applicant WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Applicant a = new Applicant();
                a.setId(rs.getInt("id"));
                a.setName(rs.getString("name"));
                a.setStatus(rs.getBoolean("status"));
                return a;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // UPDATE
    public boolean update(Applicant applicant) {
        String sql = "UPDATE applicant SET name = ?, status = ? WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, applicant.getName());
            ps.setBoolean(2, applicant.isStatus());
            ps.setInt(3, applicant.getId());
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // DELETE
    public boolean delete(int id) {
        String sql = "DELETE FROM applicant WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Lấy danh sách applicant hợp lệ để bốc thăm
    public List<Applicant> findValidApplicants() {
        List<Applicant> list = new ArrayList<>();
        String sql = "SELECT * FROM applicant WHERE status = 1";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Applicant a = new Applicant();
                a.setId(rs.getInt("id"));
                a.setName(rs.getString("name"));
                a.setStatus(true);
                list.add(a);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
