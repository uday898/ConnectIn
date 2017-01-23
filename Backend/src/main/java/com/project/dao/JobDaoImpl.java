package com.project.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.model.Job;


@Repository
public class JobDaoImpl implements JobDao {
	@Autowired
private SessionFactory sessionFactory;
	
	@Override
	public void postJob(Job job) {
		Session session=sessionFactory.openSession();
		session.save(job);
		session.flush();
		session.close();

	}

	@Override
	public List<Job> getAllJobs() {
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from Job");
		List<Job> jobs=query.list();
		session.close();
		return jobs;
	}
	
	
	public Job getJobById(int jobId) {
		Session session=sessionFactory.openSession();
		//select * from job where id=2
		Job job=(Job)session.get(Job.class, jobId);
		session.close();
		return job;
	}

}