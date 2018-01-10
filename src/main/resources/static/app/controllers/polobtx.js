angular.module('M6')
// Creating the Angular Controller
.controller('PoloBTXPairs', function($http, $scope,$timeout, $localStorage, $state, $rootScope) {

  $scope.bestpairs=[];
  $scope.format = 'M/d/yy h:mm:ss a';
  $scope.lastArbUpdate = '';



  $http.get('polobtx', {
     cache: true
   }).then(successCallback, errorCallback);

   $scope.getArbData = function() {
     $http.get('polobtx', {
       cache: false
     }).then(successCallback, errorCallback);
   };





   function successCallback(response) {
    $scope.polobtxpairs = [];
    $scope.sortBTXReverse = true; // set the default sort order
    $scope.sortBTXType = ['priceDiff'];
    $scope.polobtxpairs=angular.copy(response.data);
    $scope.totalItems = $scope.polobtxpairs.length;
    $scope.lastArbUpdate = new Date().toTimeString().split(' ')[0];
   }

 function errorCallback(error) {
     //error code
   }



//каждые 3 минуты обновляем арбитраж
   $scope.intervalArbFunction = function() {
    $timeout(function() {
      $scope.getArbData();
      $scope.intervalArbFunction();
    }, 180000)
  };

  // Kick off the interval
  $scope.intervalArbFunction();







});
