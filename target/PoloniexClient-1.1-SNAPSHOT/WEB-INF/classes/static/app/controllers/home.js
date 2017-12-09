angular.module('M6')
// Creating the Angular Controller
.controller('HomeController', function($http, $scope, $localStorage) {
	$scope.user =  JSON.parse(localStorage.getItem("user"));
});
