app.controller('FriendController',function($scope,$location,FriendService){
	
	$scope.friends=[];
	$scope.pendingRequest=[]
	$scope.friends= 
		FriendService.getAllFriends()
		.then(function(response){
			console.log('get all Friends controller')
			console.log(response.status)
			$scope.friends= response.data;
		},
		function(response){
			console.log(response.data)
		})
	
		$scope.blocked= 
		FriendService.getbFriends()
		.then(function(response){
			console.log('get all Friends controller')
			console.log(response.status)
			$scope.blocked= response.data;
		},
		function(response){
			console.log(response.data)
		})
		
		
	$scope.pendingRequest=
		FriendService.pendingRequest()
		.then(function(response){
			console.log('PENDING REQUEST')
			console.log(response.status);
			$scope.pendingRequest= response.data
			console.log($scope.pendingRequest)
			
		},function(response){
			console.log(response.status)
		})
		
		
		$scope.updateFriendf=function(fromId,friendStatus){
		
		FriendService.updateFriendf(friendStatus,fromId)
		.then(function(response){
			console.log(response.status)
			$location.path('/getAllUsers')
			
		},function(response){
			console.log(response.log)
		})
	}
		
	$scope.updateFriendt=function(toId,friendStatus){
	
		FriendService.updateFriendt(friendStatus,toId)
		.then(function(response){
			console.log(response.status)
						$location.path('/getAllUsers')
		},function(response){
			console.log(response.log)
		})
	}	
	
	$scope.updatePendingRequest=function(fromId,friendStatus){
		
		FriendService.updateFriendRequest(friendStatus,fromId)
		.then(function(response){
			console.log(response.status)
			if(friendStatus=='A'){
			alert('you have accepted the friend request. You and ' + fromId + " are friends");
			$location.path('/friendsList')
			}
			
		},function(response){
			console.log(response.log)
		})
	}
})