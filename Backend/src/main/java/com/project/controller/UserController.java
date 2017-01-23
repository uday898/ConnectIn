package com.project.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.dao.FileUploadDAO;
import com.project.dao.UserDao;
import com.project.model.User;

import com.project.model.Error;



@RestController
public class UserController {
Logger logger=LoggerFactory.getLogger(this.getClass());
@Autowired
private UserDao userDao;
@Autowired
private FileUploadDAO fileUploadDao;

@RequestMapping(value="/login",method=RequestMethod.POST)
public ResponseEntity<?> login(@RequestBody User user,HttpSession session ){
	logger.debug("Entering USERCONTROLLER : LOGIN");
	logger.debug("USERNAME:" + user.getUsername() + " PASSWORD " + user.getPassword() );
	User validUser=userDao.authenticate(user);
	if(validUser==null){
		logger.debug("validUser is null");
		Error error=new Error(1,"Username and password doesnt exists...");
		return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED); //401
	}
	else{
		
		session.setAttribute("user", validUser);
		validUser.setOnline(true);
		userDao.updateUser(validUser); 
		logger.debug("validUser is not null");
		
		return new ResponseEntity<User>(validUser,HttpStatus.OK);//200
	}
}

 
@RequestMapping(value="/register",method=RequestMethod.POST)
public ResponseEntity<?> registerUser(@RequestBody User user){

	try{
	logger.debug("USERCONTROLLER=>REGISTER " + user);
	user.setStatus(true);
	user.setOnline(false);
	User savedUser=userDao.registerUser(user);
	logger.debug("User Id generated is " + savedUser.getId());
	if(savedUser.getId()==0){
		Error error=new Error(2,"Couldnt insert user details ");
		return new ResponseEntity<Error>(error , HttpStatus.CONFLICT);
	}
	else
		return new ResponseEntity<User>(savedUser,HttpStatus.OK);
	}catch(Exception e){
		e.printStackTrace();
		Error error=new Error(2,"Couldnt insert user details. Cannot have null/duplicate values " + e.getMessage());
		return new ResponseEntity<Error>(error , HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
@RequestMapping(value="/logout",method=RequestMethod.PUT)
public ResponseEntity<?> logout(HttpSession session){
	User user=(User)session.getAttribute("user");
	if(user!=null){
		user.setOnline(false);
		userDao.updateUser(user);
		
	}
	session.removeAttribute("user");
	session.invalidate();
	return new ResponseEntity<Void>(HttpStatus.OK);
}

@RequestMapping(value="/getUsers",method=RequestMethod.GET)
public ResponseEntity<?> getAllUsers(HttpSession session){
	User user=(User)session.getAttribute("user");
	if(user==null)
	return new	ResponseEntity<Error>(new Error(1,"Unauthorized user"),HttpStatus.UNAUTHORIZED);
	else
	{
		List<User> users=userDao.getAllUsers(user);
		for(User u:users)
			System.out.println("IsONline " + u.isOnline());
		return new ResponseEntity<List<User>>(users,HttpStatus.OK);
	}
}


@RequestMapping(value="/pendingUsers",method=RequestMethod.GET)
public ResponseEntity<?> getSentUsers(HttpSession session){
	User user=(User)session.getAttribute("user");
	if(user==null)
	return new	ResponseEntity<Error>(new Error(1,"Unauthorized user"),HttpStatus.UNAUTHORIZED);
	else
	{
		List<User> users=userDao.getSentUsers(user);
		for(User u:users)
			System.out.println("IsONline " + u.isOnline());
		return new ResponseEntity<List<User>>(users,HttpStatus.OK);
	}
}


@RequestMapping(value = "/profile/{username}", method = RequestMethod.GET)
public ResponseEntity<?> getuserdetails(@PathVariable(value="username") String username,HttpSession session){
	User user=(User)session.getAttribute("user");
	if(user==null){
		Error error=new Error(1,"Unauthroized user");
		return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
	}
	else{
    User user1=userDao.userdetails(username);
	return new ResponseEntity<User>(user1,HttpStatus.OK);
}}



}