package com.company;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//-------------------------------
import com.utils.DatabaseConnection;

public class CompanyRepository {

    public List<Company> findAll(){
        List<Company> companies =new ArrayList<>();
        String sql = "select * from companies";
        try (Connection conn = DatabaseConnection.getConn();
              PreparedStatement stmt = conn.prepareStatement(sql);
              ResultSet rs = stmt.executeQuery()){

            while (rs.next()) {
                Company company = new Company();
                company.setCompany_id(rs.getInt("company_id"));
                company.setCompany_name(rs.getString("company_name"));
                company.setCompany_address(rs.getString("company_address"));
                companies.add(company);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return companies;
    }
    public void save(Company company){
        String sql = "INSERT INTO companies (company_id,company_name, company_address) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConn();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, company.getCompany_id());
            stmt.setString(2, company.getCompany_name());
            stmt.setString(3, company.getCompany_address());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


}
