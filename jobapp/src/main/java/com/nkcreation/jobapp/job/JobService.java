package com.nkcreation.jobapp.job;

import java.util.List;

public interface JobService {

    List<Job> findAll();
    void createJob(Job job);

    Job findById(Long id);

    boolean deleteJob(Long id);

    boolean updateJob(Long id, Job job);
}
