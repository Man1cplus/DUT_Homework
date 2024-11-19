package com.job;

import java.util.List;

public class JobService {
    private final JobRepository repository;

    public JobService(JobRepository repository) {
        this.repository = repository;
    }
    public List<Job> getAllJobs(){
        return repository.findAll();
    }
    public void addJob(Job job){
        repository.save(job);
    }
}
