package com.job;

import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.utils.DatabaseConnection;
import com.company.Company;

public class JobRepository {

    public List<Job> findAll() {
        List<Job> jobs=new ArrayList<>();
        String sql = "SELECT * FROM jobs";
        try (Connection conn = DatabaseConnection.getConn();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Job job = new Job();
                Company company = getCompanyById(rs.getInt("job_company_id"));
                job.setJob_company(company);
                job.setJob_id(rs.getInt("job_id"));
                job.setJob_name(rs.getString("job_name"));
                job.setJob_description(rs.getString("job_description"));
                jobs.add(job);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jobs;
    }
    public void save(Job job) {
        String sql = "INSERT INTO jobs (job_company_id,job_id, job_name,job_description) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConn();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, job.getJob_company().getCompany_id());
            stmt.setInt(2, job.getJob_id());
            stmt.setString(3, job.getJob_name());
            stmt.setString(4, job.getJob_description());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Company getCompanyById(int companyId) {
        Company company = null;
        String sql = "SELECT * FROM companies WHERE company_id = ?";

        try (Connection conn = DatabaseConnection.getConn();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, companyId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    company = new Company();
                    company.setCompany_id(rs.getInt("company_id"));
                    company.setCompany_name(rs.getString("company_name"));
                    company.setCompany_address(rs.getString("company_address"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return company;
    }
}
