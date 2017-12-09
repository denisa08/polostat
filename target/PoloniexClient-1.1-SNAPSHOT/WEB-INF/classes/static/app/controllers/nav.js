angular.module('M6')
// Creating the Angular Controller
.controller('NavController', function($http, $scope, $localStorage, $state, $rootScope) {
	$scope.user = JSON.parse(localStorage.getItem("user"));


	$scope.$on('LoginSuccessful', function() {
		$scope.user = JSON.parse(localStorage.getItem("user"));

	});
	$scope.$on('LogoutSuccessful', function() {
		$scope.user = null;
	});
	$scope.logout = function() {
	localStorage.setItem("user",null);
        $rootScope.$broadcast('LogoutSuccessful');
		$state.go('login');
	};
});
