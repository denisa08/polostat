angular.module('M6')
// Creating the Angular Controller
.controller('RegisterController', function($http, $scope, $localStorage,$state) {
	$scope.submit = function() {

   $http.post('register',$scope.appUser, {
      cache: false
    }).then(successCallback, errorCallback);

	};

	  function successCallback(response) {
	             $scope.appUser = null;
          			$scope.confirmPassword = null;
          			$scope.register.$setPristine();
          			$scope.message = "Registration successfull !";
          			event.preventDefault();
                    $state.go('login');
	  }

	    function errorCallback(response) {
      	           $scope.message = response.data.message;
      	  }

});



