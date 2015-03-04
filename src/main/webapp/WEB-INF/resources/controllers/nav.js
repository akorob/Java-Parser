"use strict";
app.controller("NavController", ["$scope", "$rootScope", "$location","Restangular",
    function ($scope, $rootScope, $location, rest) {
        log("NavController");
        $scope.inProcess = false;
        $scope.data = {found:0};
        var site = "http://www.zoo-zoo.ru/"

        var pet = rest.all('pet');
        $scope.startParsing = function(){
            $scope.inProcess = true;
            pet.customPOST(site).then(function(data){
                $scope.data.found = data;
                $scope.inProcess = false;
            },
            function(){
                $scope.inProcess = false;
            })
        }

}]);