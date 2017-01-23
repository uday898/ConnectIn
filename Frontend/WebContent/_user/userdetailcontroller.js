app.controller('UserDetailController',function($routeParams,$scope,UserService){
	
	var username=$routeParams.username
	$scope.user1={}
	//instead of writing function and calling explicitly, we can call the service function directly
	$scope.user1=
		UserService.getUserDetail(username) //calling service function directly
		.then(function(response){
			console.log(response.data);
			console.log(response.status)
			$scope.user1=response.data;
		
		},function(response){
			console.log(response.status)
		})
	
		
	
	
})