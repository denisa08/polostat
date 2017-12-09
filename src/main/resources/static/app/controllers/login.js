angular.module('M6')
// Creating the Angular Controller
.controller('LoginController', function($http, $scope, $state, $localStorage, $rootScope) {
	// method for login
	$scope.login = function() {
  $http({
            url: 'authenticate',
            method: "POST",
            data: { 'username' : $scope.username,'password' : $scope.password }
        })
        .then(function(res) {
             	$scope.password = null;
                    // checking if the token is available in the response
                    if (res.data.token) {
                    $scope.message = '';
                    // setting the Authorization Bearer token with JWT token
                     $http.defaults.headers.common['Authorization'] = 'Bearer ' + res.data.token;
                     // setting the user in AuthService

                      localStorage.setItem("user",JSON.stringify(res.data.user));
                      $rootScope.$broadcast('LoginSuccessful');
                       // going to the home page
                     $state.go('home');
                      } else {
                      // if the token is not present in the response then the
                      // authentication was not successful. Setting the error message.
                      $scope.message = 'Authetication Failed !';
                      localStorage.setItem("user",null);
                     	}
        },
        function(response) { // optional
                			$scope.message = 'Authetication Failed !';
                			 localStorage.setItem("user",null);


        });



     };




});
