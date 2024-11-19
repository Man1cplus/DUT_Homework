package com.applicant;

import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.utils.DatabaseConnection;

public class ApplicantRepository {

    public List<Applicant> findAll(){
        List<Applicant> applicants = new ArrayList<>();
        String sql = "SELECT * FROM applicants";

        try (Connection conn = DatabaseConnection.getConn();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Applicant applicant = new Applicant();
                applicant.setApplicant_id(rs.getInt("applicant_id"));
                applicant.setApplicant_name(rs.getString("applicant_name"));
                applicant.setApplicant_email(rs.getString("applicant_email"));
                applicant.setApplicant_resume(rs.getString("applicant_resume"));
                applicants.add(applicant);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return applicants;
    }

    public void save(Applicant applicant){
        String sql = "INSERT INTO applicants (applicant_id,applicant_name, applicant_email, applicant_resume) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConn();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, applicant.getApplicant_id());
            stmt.setString(2, applicant.getApplicant_name());
            stmt.setString(3, applicant.getApplicant_email());
            stmt.setString(4, applicant.getApplicant_resume());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
