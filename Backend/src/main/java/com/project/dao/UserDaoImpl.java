package com.project.dao;

import com.project.model.User;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class UserDaoImpl implements UserDao{
	@Autowired
private SessionFactory sessionFactory;
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	@Override
	public User authenticate(User user) {
		Session session=sessionFactory.openSession();
		Query query=session.createQuery(
		"from User where username=?  and password=?");
		//select * from User where username='smith' and password='123'
		query.setString(0, user.getUsername());
		query.setString(1, user.getPassword());
		User validUser=(User)query.uniqueResult();
		session.close();
		return validUser;
		
	}
	@Override
	public void updateUser(User user) {
		Session session=sessionFactory.openSession();
		User existingUser=(User)session.get(User.class, user.getId());
		//update online status as true
		existingUser.setOnline(user.isOnline()); 
		
		session.update(existingUser);
		session.flush();
		session.close();
	}
	@Override
	public User registerUser(User user) {
		
		Session session=sessionFactory.openSession();
		session.save(user);
		session.flush();
		session.close();
		return user;
	}
	
	@Override
	public List<User> getAllUsers(User user) {
		Session session=sessionFactory.openSession();
		SQLQuery query=session.createSQLQuery("select * from table_user where username in (select username from table_user where username!=? minus(select to_id from friend where from_id=? union select from_id from friend where to_id=?))");
		query.setString(0, user.getUsername());
		query.setString(1, user.getUsername());
		query.setString(2, user.getUsername());
		query.addEntity(User.class);
		List<User> users=query.list();
		System.out.println(users);
		session.close();
		return users;
	}
	@Override
	public List<User> getSentUsers(User user) {
		Session session=sessionFactory.openSession();
		SQLQuery query=session.createSQLQuery("select * from table_user where username in (select to_id from friend where from_id=? and status='P')");
		query.setString(0, user.getUsername());
		query.addEntity(User.class);
		List<User> users=query.list();
		System.out.println(users);
		session.close();
		return users;
		
		
	}
	@Override
	public User userdetails(String username) {
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from User where username=?");
		query.setString(0, username);
		User user = (User) query.list().get(0);
		System.out.println(user);
		session.close();
		return user;
	}

}
