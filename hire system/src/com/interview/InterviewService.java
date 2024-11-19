package com.interview;

import java.util.List;

public class InterviewService {
    private final InterviewRepository repository;
    public InterviewService(InterviewRepository repository) {
        this.repository = repository;
    }
    public List<Interview> getAllInterviews() {
        return repository.findAll();
    }
    public void addInterview(Interview interview) {
        repository.save(interview);
    }
}
