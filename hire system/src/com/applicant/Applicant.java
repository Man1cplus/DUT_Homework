package com.applicant;

public class Applicant {
    private int applicant_id;//申请人编号
    private String applicant_name;//申请人姓名
    private String applicant_email;//申请人邮箱
    private String applicant_resume;//申请人简历

    public int getApplicant_id() {
        return applicant_id;
    }
    public void setApplicant_id(int applicant_id) {
        this.applicant_id = applicant_id;
    }
    public String getApplicant_name() {
        return applicant_name;
    }
    public void setApplicant_name(String applicant_name) {
        this.applicant_name = applicant_name;
    }
    public String getApplicant_email() {
        return applicant_email;
    }
    public void setApplicant_email(String applicant_email) {
        this.applicant_email = applicant_email;
    }
    public String getApplicant_resume() {
        return applicant_resume;
    }
    public void setApplicant_resume(String applicant_resume) {
        this.applicant_resume = applicant_resume;
    }
}
