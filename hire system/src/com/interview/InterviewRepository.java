package com.interview;

import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.utils.DatabaseConnection;
import com.applicant.Applicant;
import com.job.Job;

public class InterviewRepository {

    public List<Interview> findAll(){
        List<Interview> interviews = new ArrayList<>();
        String sql = "SELECT * FROM interviews";

        try (Connection conn = DatabaseConnection.getConn();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Interview interview = new Interview();
                interview.setId(rs.getInt("interview_id"));
                // Assuming that you have methods to get applicant and job by their IDs
                Applicant applicant = getApplicantById(rs.getInt("applicant_interview_id"));
                Job job = getJobById(rs.getInt("job_interview_id"));
                interview.setApplicant_interview(applicant);
                interview.setJob_interview(job);
                interview.setInterview_notes(rs.getString("interview_notes"));
                interview.setInterview_isHired(rs.getBoolean("interview_ishired"));
                interviews.add(interview);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return interviews;
    }
    public void save(Interview interview){
        String sql = "INSERT INTO interviews (interview_id,applicant_interview_id, job_interview_id, interview_notes, interview_ishired) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConn();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, interview.getId());
            stmt.setInt(2, interview.getApplicant_interview().getApplicant_id());
            stmt.setInt(3, interview.getJob_interview().getJob_id());
            stmt.setString(4, interview.getInterview_notes());
            stmt.setBoolean(5, interview.isInterview_isHired());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static Applicant getApplicantById(int id) {
        // Implement the logic to get an applicant by ID from the database
        String sql = "SELECT * FROM applicants WHERE applicant_id = ?";
        Applicant applicant = null;

        try (Connection conn = DatabaseConnection.getConn();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    applicant = new Applicant();
                    applicant.setApplicant_id(rs.getInt("applicant_id"));
                    applicant.setApplicant_name(rs.getString("applicant_name"));
                    // Set other fields as needed
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return applicant;
    }

    private static Job getJobById(int id) {
        // Implement the logic to get a job by ID from the database
        String sql = "SELECT * FROM jobs WHERE job_id = ?";
        Job job = null;

        try (Connection conn = DatabaseConnection.getConn();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    job = new Job();
                    job.setJob_id(rs.getInt("job_id"));
                    job.setJob_name(rs.getString("job_name"));
                    job.setJob_description(rs.getString("job_description"));
                    // Set other fields as needed
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return job;
    }
    public static List<Interview> findByApplicantId(int applicantId) {
        List<Interview> interviews = new ArrayList<>();
        String sql = "SELECT * FROM interviews WHERE applicant_interview_id = ?";
        try (Connection conn = DatabaseConnection.getConn();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, applicantId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Interview interview = new Interview();
                    interview.setId(rs.getInt("interview_id"));
                    interview.setInterview_notes(rs.getString("interview_notes"));
                    interview.setInterview_isHired(rs.getBoolean("interview_ishired"));
                    Applicant applicant = getApplicantById(rs.getInt("applicant_interview_id"));
                    interview.setApplicant_interview(applicant);
                    Job job = getJobById(rs.getInt("job_interview_id"));
                    interview.setJob_interview(job);
                    interviews.add(interview);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return interviews;
    }
}
