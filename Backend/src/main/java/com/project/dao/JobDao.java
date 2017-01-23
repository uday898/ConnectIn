package com.project.dao;

import java.util.List;

import com.project.model.Job;

public interface JobDao {
void postJob(Job job);
List<Job> getAllJobs();
public Job getJobById(int jobId);
}
