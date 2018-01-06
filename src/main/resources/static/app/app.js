var app = angular.module('M6', ['ui.router','ngResource','ngAnimate','store-directives','ngMaterial','ngStorage','ui.bootstrap']);

// the following method will run at the time of initializing the module. That
// means it will run only one time.
app.run(function(AuthService, $rootScope, $state,$localStorage) {
	// For implementing the authentication with ui-router we need to listen the
	// state change. For every state change the ui-router module will broadcast
	// the '$stateChangeStart'.
	$rootScope.$on('$stateChangeStart', function(event, toState, toParams, fromState, fromParams,$localStorage,$scope) {

	  var user = JSON.parse(localStorage.getItem("user"));
		// checking the user is logged in or not
		if (user==null) {

 			// To avoiding the infinite looping of state change we have to add a
			// if condition.
			if (toState.name != 'login' && toState.name != 'register'&& toState.name != 'price') {
				event.preventDefault();
				$state.go('login');
			}
		} else {
			// checking the user is authorized to view the states
			if (toState.data && toState.data.role) {
				var hasAccess = false;
				for (var i = 0; i < user.roles.length; i++) {
					var role = user.roles[i];
					if (toState.data.role == role) {
						hasAccess = true;
					 }
					 if (role=='DENIS') {
                     	$rootScope.userAdmin=true;
                     }
				}
				if (!hasAccess) {
					event.preventDefault();
					$state.go('access-denied');
				}

			}
		}
	});
});


app.controller("TabOne", function($scope) {
  $scope.title = "Count Upwards";
});

app.controller('mainController', function($scope, $http, $timeout,$localStorage,$location, $rootScope) {
  $scope.format = 'M/d/yy h:mm:ss a';
  $scope.lastUpdate = '';
  $scope.sortTypeVolume = ['thirtyChangedVolume']; // set the default sort type
  $scope.sortType = ['thirtyChanged'];
  $scope.sortReverse = true; // set the default sort order
  $scope.searchFish = ''; // set the default search/filter term
  $scope.currentPage = 4;
  $scope.itemsPerPage = 10;
  $scope.maxSize = 5; //Number of pager buttons to show
  $rootScope.userAdmin=false;
   $scope.prop = {
  "type": "select",
  "name": "Service",
  "value": "10",
  "values": [ "10", "20", "30", "50","100","200","250"]
};
$scope.page = 1;
$scope.bestpairs=[];

 $http.get('pairs', {
    cache: true
  }).then(successCallback, errorCallback);

  $scope.getData = function() {
    $http.get('pairs', {
      cache: false
    }).then(successCallback, errorCallback);
  };


$scope.isRocketV = function(pair) {

if (pair.thirtyChangedVolume>5&&
    pair.hourChangedVolume>7&&
    pair.threeHourChangedVolume>7&&
    pair.sixHourChangedVolume>0){
    pair.rocketV=1;
    return "app/rocket.gif";
    }

if (pair.thirtyChangedVolume<0&&
    pair.hourChangedVolume<0&&
    pair.threeHourChangedVolume<0&&
    pair.sixHourChangedVolume<0){
    pair.rocketV=-1;

    return "app/down.gif";
    }
    pair.rocketV=0;
return "app/think.gif";


};

$scope.isRocketP = function(pair) {

if (pair.thirtyChanged>5&&
    pair.hourChanged>10&&
    pair.threeHourChanged>10&&
    pair.sixHourChanged>0){
    pair.rocketP=1;
    return "app/rocket.gif";
    }

if (pair.thirtyChanged<0&&
    pair.hourChanged<0&&
    pair.threeHourChanged<0&&
    pair.sixHourChanged<0){
    pair.rocketP=-1;

    return "app/down.gif";
    }
    pair.rocketP=0;
return "app/think.gif";


};

  function successCallback(response) {

    $scope.bestpairs=[];


    if ($scope.pairs == null) {
        $scope.pairs = [];
      $scope.pairs=angular.copy(response.data);
      $scope.totalItems = $scope.pairs.length;


angular.forEach($scope.pairs, function(item,index){

  var name = $scope.pairs[index].name;
  var star = localStorage.getItem(name);
  item["star"]=toBoolean(star);

  if (item["oneChangedVolume"]>3&&item["fiveChangedVolume"]>3&&item["tenChangedVolume"]>0){
    $scope.bestpairs.push(item);
  }

})
  }

    else{

angular.forEach($scope.pairs, function(item,index){
//price change
var last = angular.equals(item["last"], response.data[index].last);
var resultOneChanged = angular.equals(item["oneChanged"], response.data[index].oneChanged);
var resultFiveChanged = angular.equals(item["fiveChanged"], response.data[index].fiveChanged);
var resultThirtyChanged = angular.equals(item["thirtyChanged"], response.data[index].thirtyChanged);
var resultHourChanged = angular.equals(item["hourChanged"], response.data[index].hourChanged);
var resultThreeHourChanged = angular.equals(item["threeHourChanged"], response.data[index].threeHourChanged);
var resultSixHourChanged = angular.equals(item["sixHourChanged"], response.data[index].sixHourChanged);
var resultTwentyHourChanged = angular.equals(item["twentyHourChanged"], response.data[index].twentyHourChanged);
var resultAllTimeChanged= angular.equals(item["allTimeChanged"], response.data[index].allTimeChanged);
// volume change
var resultOneChangedVolume = angular.equals(item["oneChangedVolume"], response.data[index].oneChangedVolume);
var resultFiveChangedVolume = angular.equals(item["fiveChangedVolume"], response.data[index].fiveChangedVolume);
var resultThirtyChangedVolume = angular.equals(item["thirtyChangedVolume"], response.data[index].thirtyChangedVolume);
var resultHourChangedVolume = angular.equals(item["hourChangedVolume"], response.data[index].hourChangedVolume);
var resultThreeHourChangedVolume = angular.equals(item["threeHourChangedVolume"], response.data[index].threeHourChangedVolume);
var resultSixHourChangedVolume = angular.equals(item["sixHourChangedVolume"], response.data[index].sixHourChangedVolume);
var resultTwentyHourChangedVolume = angular.equals(item["twentyHourChangedVolume"], response.data[index].twentyHourChangedVolume);
var resultAllTimeChangedVolume= angular.equals(item["allTimeChangedVolume"], response.data[index].allTimeChangedVolume);


if (!last){
   item["last"]=response.data[index].last;
  }


if (!resultOneChanged){
   item["oneChanged"]=response.data[index].oneChanged;
  }

if (!resultFiveChanged){
     item["fiveChanged"]=response.data[index].fiveChanged;
  }
if (!resultThirtyChanged){
    item["thirtyChanged"]=response.data[index].thirtyChanged;
  }

if (!resultHourChanged){
  item["hourChanged"]=response.data[index].hourChanged;
  }

if (!resultThreeHourChanged){
  item["threeHourChanged"]=response.data[index].threeHourChanged;
  }

if (!resultSixHourChanged){
  item["sixHourChanged"]=response.data[index].sixHourChanged;
  }

if (!resultTwentyHourChanged){
  item["twentyHourChanged"]=response.data[index].twentyHourChanged;
  }

if (!resultAllTimeChanged){
  item["allTimeChanged"]=response.data[index].allTimeChanged;
  }


//if change value
if (!resultOneChangedVolume){
   item["oneChangedVolume"]=response.data[index].oneChangedVolume;
  }

if (!resultFiveChangedVolume){
     item["fiveChangedVolume"]=response.data[index].fiveChangedVolume;
  }
if (!resultThirtyChangedVolume){
    item["thirtyChangedVolume"]=response.data[index].thirtyChangedVolume;
  }

if (!resultHourChangedVolume){
  item["hourChangedVolume"]=response.data[index].hourChangedVolume;
  }

if (!resultThreeHourChangedVolume){
  item["threeHourChangedVolume"]=response.data[index].threeHourChangedVolume;
  }

if (!resultSixHourChangedVolume){
  item["sixHourChangedVolume"]=response.data[index].sixHourChangedVolume;
  }

if (!resultTwentyHourChangedVolume){
  item["twentyHourChangedVolume"]=response.data[index].twentyHourChangedVolume;
  }

if (!resultAllTimeChangedVolume){
  item["allTimeChangedVolume"]=response.data[index].allTimeChangedVolume;
  }


    if (item["oneChangedVolume"]>3&&item["fiveChangedVolume"]>3&&item["tenChangedVolume"]>0){
      $scope.bestpairs.push(item);
    }


    var star = localStorage.getItem(item["name"]);
    item["star"]=toBoolean(star);




 })
  }
   $scope.lastUpdate = new Date().toTimeString().split(' ')[0];
   $scope.displayItems = $scope.pairs.slice(0, $scope.itemsPerPage);
	 $scope.pageChanged = function() {
   	  var startPos = ($scope.page - 1) *  $scope.itemsPerPage;
   	  //$scope.displayItems = $scope.totalItems.slice(startPos, startPos + 3);
   	  console.log($scope.page);
   	};



      $scope.setPage = function (pageNo) {
        $scope.currentPage = pageNo;
      };



    $scope.setItemsPerPage = function(num) {
      $scope.itemsPerPage = num;
      $scope.currentPage = 1; //reset to first page
    }

  }

function errorCallback(error) {
    //error code
  }

  function toBoolean(str) {
      if (typeof str === 'undefined' || str === null) {
          return false;
      } else if (typeof str === 'string') {
          switch (str.toLowerCase()) {
          case 'false':
          case 'no':
          case '0':
          case "":
              return false;
          default:
              return true;
          }
      } else if (typeof str === 'number') {
          return str !== 0
      }
      else {return true;}
  }


// Function to replicate setInterval using $timeout service.
  $scope.intervalFunction = function() {
    $timeout(function() {
      $scope.getData();
      $scope.intervalFunction();
    }, 20000)
  };

  // Kick off the interval
  $scope.intervalFunction();


  $scope.like = {};
      $scope.like.votes = 0;
      $scope.doVote = function(pair) {


var star =  localStorage.getItem(pair.name);
if (toBoolean(star)==false){
  localStorage.setItem(pair.name, true);
  pair.star=true;
}
else{
  localStorage.setItem(pair.name, false);
  pair.star=false;

}

    }






});


app.directive('animateOnChange', function($timeout, $animate) {
  return function(scope, element, attr) {
    scope.$watch(attr.animateOnChange, function(nv, ov) {


      var c = '';
      if (nv < 0) {
        c = 'change';
      } else {
        c = 'change-up';
      }

      $animate.addClass(element, c).then(function() {
        $timeout(function() {
          $animate.removeClass(element, c)
        });
      });



    });
  };
});
