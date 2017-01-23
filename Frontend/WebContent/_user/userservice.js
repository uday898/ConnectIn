app.factory('UserService',function($http){
	var userService=this;
	var BASE_URL="http://localhost:2017/Backend"
	userService.authenticate=function(user){
		return $http.post(BASE_URL + "/login",user);
	}
	
	userService.registerUser=function(user){
		return $http.post("http://localhost:2017/Backend/register",user) 
	}
	
	userService.logout=function(){
		console.log('entering logout service')
		return $http.put(BASE_URL + "/logout")
	};
	
	userService.getAllUsers=function(){
		console.log('entering getallusers in user service')
		return $http.get(BASE_URL +"/getUsers")
	}
	
	userService.getSentUsers=function(){
		console.log('entering Pending in user service')
		return $http.get(BASE_URL +"/pendingUsers")
	}
	
	userService.friendRequest=function(username){
		console.log('service --- friendRequest');
		return $http.post(BASE_URL+ '/friendRequest',username);
	}
	
	userService.getUserDetail=function(username){
		console.log('getUserDetails----service')
		return $http.get("http://localhost:2017/Backend/profile/"+ username)
	}
	
	
	
	
	return userService;
	
})