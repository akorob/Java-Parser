'use strict';
app.controller('PetsController', ['$scope', '$rootScope', 'Restangular',
    function ($scope, $rootScope, rest) {
        log("PetsController");
        $scope.pets = [];
        $scope.totalItems = 0;
        $scope.itemsPerPage = 10;
        $scope.currentPage = 1;
        var allPets = [];
        $scope.categories = [];

        $scope.pageChanged = function() {
            log('Page changed to: ' + $scope.currentPage);
            setPage($scope.currentPage);
        };


        var cat = rest.all('category');
        cat.getList().then(function(data){
            $scope.categories = data;
        });


        var pets = rest.one('pet');
        pets.getList().then(function(data){
           allPets = data;
           setPage(1);
        });


        $scope.selectCategory = function(categoryId){
            log(categoryId);
            pets.getList(categoryId).then(function(data){
                allPets = data;
                setPage(1);
            });
        };





        function setPage(page){
            $scope.totalItems = allPets.length;
            var startItem = ($scope.currentPage - 1) * $scope.itemsPerPage;
            var endItem = $scope.currentPage  * $scope.itemsPerPage;
            if (endItem > $scope.totalItems ){
                endItem = $scope.totalItems;
            }
            var list = [];
            for (var i = startItem; i < endItem; i++){
                list.push(allPets[i]);
            }
            $scope.pets = list;
        }













    }


]);