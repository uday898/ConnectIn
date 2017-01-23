package com.project.dao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.project.model.UploadFile;


@Repository
public class FileUploadDaoImpl implements FileUploadDAO {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public void save(UploadFile uploadFile) {
		Session session=sessionFactory.openSession();
		session.saveOrUpdate(uploadFile);
		session.flush();
		session.close();
	}

	@Override
	public UploadFile getFile(String username) {
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from UploadFile where username=?");
		query.setParameter(0, username);
        UploadFile uploadFile=(UploadFile)query.uniqueResult();
		session.close();
		return uploadFile;
	}

}