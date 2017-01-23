app.factory('FriendService',function($http){
	var BASE_URL ="http://localhost:2017/Backend"
	var friendService=this;
	
	friendService.getAllFriends=function(){
		console.log('service --- friendRequest');
		return $http.get(BASE_URL + "/getAllFriends");
	}
	
	
	friendService.getbFriends=function(){
		console.log('service --- friendRequest');
		return $http.get(BASE_URL + "/getbFriends");
	}
	
	friendService.pendingRequest=function(){
		console.log('service --- pending request');
		return $http.get(BASE_URL + "/pendingRequest")
	}
	friendService.updateFriendRequest=function(friendStatus,fromId){
		console.log('service - update friend request')
		return $http.put(BASE_URL + "/updateFriendRequest/" + friendStatus +"/"+fromId)
	}
	
	friendService.updateFriendf=function(friendStatus,fromId){
		console.log('service - update friend request')
		return $http.put(BASE_URL + "/updateFriendf/" + friendStatus +"/"+fromId)
	}
	friendService.updateFriendt=function(friendStatus,toId){
		console.log('service - update friend request')
		return $http.put(BASE_URL + "/updateFriendt/" + friendStatus +"/"+toId)
	}
	return friendService;
})