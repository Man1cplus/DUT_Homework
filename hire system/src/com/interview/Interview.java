package com.interview;

import com.applicant.Applicant;
import com.job.Job;

public class Interview {
    private int interview_id;//本次面试编号
    private Applicant applicant_interview;//申请人面试
    private Job job_interview;//本次面试的工作
    private String interview_notes;//面试备注
    private boolean interview_isHired;//是否录用

    public int getId(){
        return interview_id;
    }

    public void setId(int id){
        this.interview_id =id;
    }

    public Applicant getApplicant_interview(){
        return applicant_interview;
    }
    public void setApplicant_interview(Applicant applicant_interview){
        this.applicant_interview = applicant_interview;
    }

    public Job getJob_interview(){
        return job_interview;
    }

    public void setJob_interview(Job job_interview){
        this.job_interview = job_interview;
    }

    public String getInterview_notes(){
        return interview_notes;
    }

    public void setInterview_notes(String interview_notes){
        this.interview_notes = interview_notes;
    }

    public boolean isInterview_isHired(){
        return interview_isHired;
    }

    public void setInterview_isHired(boolean interview_isHired){
        this.interview_isHired = interview_isHired;
    }

}
