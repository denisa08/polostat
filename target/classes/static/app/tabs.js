var app = angular.module('store-directives', []);




app.directive("prices", function() {
  return {
    restrict: "E",
    templateUrl: "app/views/prices.html"
  };
});

app.directive("volumes", function() {
  return {
    restrict: "E",
    templateUrl: "app/views/volumes.html"
  };
});



app.directive("cryptoTabs", function() {
  return {
    restrict: "E",

    templateUrl: "app/views/crypto-tabs.html",
    controller: function() {
      this.tab = 1;

      this.isSet = function(checkTab) {
        return this.tab === checkTab;
      };

      this.setTab = function(activeTab) {
        this.tab = activeTab;
      };
    },
    controllerAs: "tab"
  };
});
