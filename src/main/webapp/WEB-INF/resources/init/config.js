/**
 * Created by Andrey on 26.12.2014.
 */
'use strict'

app.config([
    '$routeProvider', '$httpProvider', '$locationProvider', 'cfpLoadingBarProvider','RestangularProvider',
    function($routeProvider, $httpProvider,  $locationProvider, cfpLoadingBarProvider, restangularProvider) {

    // ======= Loading Bar configuration ========
    cfpLoadingBarProvider.latencyThreshold = 700;
    // ======= rest configuration =============
    restangularProvider.setBaseUrl('/api/');
    // ======= router configuration =============
    $locationProvider.html5Mode(true);
    $routeProvider
        .when('/', {
            controller:'MainController',
            templateUrl: 'resources/partials/main.html'
        })
        .when('/pets', {
            controller: 'PetsController',
            templateUrl: 'resources/partials/pets.html'
        })
        .otherwise({ redirectTo : "/"})

    ;
}]);