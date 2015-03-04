"use strict";
app.controller("NavController", ["$scope", "$rootScope", "$location","Restangular",
    function ($scope, $rootScope, $location, rest) {
        log("NavController");
        $scope.inProcess = false;
        $scope.data = {found:0};

        var pet = rest.one('pet');



        pet.get({param:"getCount"}).then(function(data){
            $scope.data.found = data;
        });



        $scope.startParsing = function(){
            $location.path("/");
            $scope.inProcess = true;
            pet.customPOST().then(function(data){
                $scope.data.found = data;
                $scope.inProcess = false;
            },
            function(){
                $scope.inProcess = false;
            })
        }

}]);