package com.project.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.model.Friend;
@Repository
public class FriendDaoImpl implements FriendDao{
	@Autowired
private SessionFactory sessionFactory;
	@Override
	public List<Friend> getAllFriends(String username) {
      Session session=sessionFactory.openSession();
      Query query=session.createQuery("from Friend where (to_id=? or from_id=?) and status=?");
      query.setString(0, username);
      query.setString(1, username);
      query.setCharacter(2, 'A');
      List friends=query.list();
      session.close();
      return friends;
	}
	@Override
	public void sendFriendRequest(String from,String to) {
		Session session=sessionFactory.openSession();
		Friend friend=new Friend();
		friend.setFromId(from);
		friend.setToId(to);
		friend.setStatus('P');
		session.save(friend);
		session.flush();
		session.close();
	      
	}
	@Override
	public List<Friend> getPendingRequest(String username) {
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from Friend where toId=? and status=?");
		query.setString(0, username);
		query.setCharacter(1, 'P');
		List<Friend> pendingRequest=query.list();
		session.close();
		return pendingRequest;
	}
	@Override
	public void updatePendingRequest(char friendStatus, String fromId, String toId) {
		Session session=sessionFactory.openSession();
		if(friendStatus=='D'){
			Query query=session.createQuery("delete from Friend where fromId=? and toId=?");
			query.setString(0, fromId);
			query.setString(1, toId);
			int count=query.executeUpdate();
			System.out.println("Number of records updated " + count);
			session.flush();
			session.close();
			
		}
		else{
		Query query=session.createQuery("update Friend set status=? where fromId=? and toId=?");
		query.setCharacter(0, friendStatus);
		query.setString(1, fromId);
		query.setString(2, toId);
		int count=query.executeUpdate();
		System.out.println("Number of records updated " + count);
		session.flush();
		session.close();
		}
		}
	@Override
	public void updatePendingf(char friendStatus, String fromId, String username) {
		Session session=sessionFactory.openSession();
		if(friendStatus=='D'){
			Query query=session.createQuery("delete from Friend where fromId=? and toId=?");
			query.setString(0, fromId);
			query.setString(1, username);
			int count=query.executeUpdate();
			System.out.println("Number of records updated " + count);
			session.flush();
			session.close();
			
		}else
		{

			Query query=session.createQuery("update Friend set status=? where fromId=? and toId=?");
			query.setCharacter(0, friendStatus);
			query.setString(1, fromId);
			query.setString(2, username);
			int count=query.executeUpdate();
			System.out.println("Number of records updated " + count);
			session.flush();
			session.close();
		}	
		
		
	}
	@Override
	public void updatePendingt(char friendStatus, String toId, String username) {
		
		Session session=sessionFactory.openSession();
		if(friendStatus=='D'){
			Query query=session.createQuery("delete from Friend where fromId=? and toId=?");
			query.setString(0, username);
			query.setString(1, toId);
			int count=query.executeUpdate();
			System.out.println("Number of records updated " + count);
			session.flush();
			session.close();
			
		}
		else{

		Query query=session.createQuery("update Friend set status=? where fromId=? and toId=?");
		query.setCharacter(0, friendStatus);
		query.setString(1, username);
		query.setString(2, toId);
		int count=query.executeUpdate();
		System.out.println("Number of records updated " + count);
		session.flush();
		session.close();
		}
	}
	@Override
	public List<Friend> getbFriends(String username) {
		 Session session=sessionFactory.openSession();
	      Query query=session.createQuery("from Friend where (to_id=? or from_id=?) and status=?");
	      query.setString(0, username);
	      query.setString(1, username);
	      query.setCharacter(2, 'B');
	      List friends=query.list();
	      session.close();
	      return friends;
	
	}
	
	@Override
	public int countrequests(String toId) {
		Session session=sessionFactory.openSession();
		
		Query query=session.createQuery("select count(toId) from WishList where toId='"+toId+"' and status=?");
        query.setCharacter(0,'P');
		int count=0;
		 count=Integer.parseInt(query.list().get(0).toString());
	System.out.println("count of the function:"+count);
		session.flush();
		session.close();
		return count;
	
	}
	

}
