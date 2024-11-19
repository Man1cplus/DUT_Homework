package com.applicant;

import java.util.List;
import com.interview.Interview;
import com.interview.InterviewRepository;

public class ApplicantService {
    private final ApplicantRepository repository;

    public ApplicantService(ApplicantRepository repository) {
        this.repository = repository;
    }

    public List<Applicant> getApplicants() {
        return repository.findAll();
    }
    public void addApplicant(Applicant applicant) {
        repository.save(applicant);
    }
    public List<Interview> getInterviewDetailsForApplicant(int applicantId) {
        return InterviewRepository.findByApplicantId(applicantId);
    }
}
