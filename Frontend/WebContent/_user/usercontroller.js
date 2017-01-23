app.controller('UserController',function($scope,$rootScope,$cookieStore,$location,UserService){
	$scope.user={id:'',username:'',password:'',email:'',role:'',isOnline:'',enabled:'',palce:''};
	$scope.message;
	
	$scope.submit=function(){
		console.log('Entering - submit function in usercontroller')
		UserService.authenticate($scope.user)
		.then(function(response){
				$scope.user=response.data;
				$rootScope.currentUser=$scope.user;
				
				$cookieStore.put('currentUser',$rootScope.currentUser)
				console.log('currentUser in rootScope ' + $rootScope.currentUser.id)
				$location.path("/home");
		},
		function(response){//invalid user name and password - failure 
			 console.log('invalid username and password')
			  $scope.message="Invalid Username and Password";
			  $location.path("/login");
		})
	}
	
	$scope.registerUser=function(){
		console.log('entering registerUser')
		UserService.registerUser($scope.user)
		.then(function(response){ //success 
			//response.data => user object
			console.log("registration success" + response.status)
			$scope.message="Registration successfull.. login using username and password.."
			$location.path("/login");
		},function(response){//failure
			console.log("registration failed" + response.status)
			//response.data => error object
			console.log(response.data)
			$scope.errorMessage="Registration failed...." + response.data.message
			$location.path("/register")
		})
	}
	
	$scope.friendRequest=function(username){
		
		console.log('friendrequest function')
		UserService.friendRequest(username)
		.then(function(response){
			console.log(response.status);
			getAllUsers();
			getSentUsers();
			$location.path('/getAllUsers')
		},
		function(response){
			console.log(response.status);
		}
		)
	}
	
	
	
	function getAllUsers(){
		console.log('entering get all users ')
		UserService.getAllUsers()
		.then(function(response){
		console.log(response.status)
		console.log(response.data)
		$scope.users=response.data
		},function(response){
			console.log(response.status)
		}
		)
	}
	getAllUsers()
	
	 
	

	function getSentUsers(){
		console.log('entering Request Sent users ')
		UserService.getSentUsers()
		.then(function(response){
		console.log(response.status)
		console.log(response.data)
		$scope.sentusers=response.data
		},function(response){
			console.log(response.status)
		}
		)
	}
	getSentUsers()
	
	
	
})