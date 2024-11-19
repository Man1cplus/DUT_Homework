package com.job;

import com.company.Company;

public class Job {
    private int job_id;//工作编号
    private String job_name;//工作名称
    private String job_description;//工作描述
    private Company job_company;//工作所在公司

    public int getJob_id() {
        return job_id;
    }
    public void setJob_id(int job_id) {
        this.job_id = job_id;
    }
    public String getJob_name() {
        return job_name;
    }
    public void setJob_name(String job_name) {
        this.job_name = job_name;
    }
    public String getJob_description() {
        return job_description;
    }
    public void setJob_description(String job_description) {
        this.job_description = job_description;

    }
    public Company getJob_company() {
        return job_company;
    }
    public void setJob_company(Company job_company) {
        this.job_company = job_company;
    }
}
