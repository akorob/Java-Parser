/**
 * Created by dev on 12.01.2015.
 */
app.directive("fbClick", function($interval) {
    return {
        restrict: "A",        // directive is an Element (not Attribute)
        scope: {              // set up directive's isolated scope
            name: "@",          // name var passed by value (string, one-way)
            alter: "@",
            icon:"@"
        },
        template:             // replacement HTML (can use our scope vars here)
        "<i class={{iconName}} ></i>&nbsp;{{buttonName}}",
        replace: false,        // replace original markup with template
        transclude: true,    //  copy original HTML content
        link: function (scope, element, attrs, controller) {
            scope.buttonName = scope.name;
            scope.iconName = scope.icon;

            element.bind('click', function() {
                scope.$apply(function() {
                    scope.buttonName = scope.alter;
                    scope.iconName="";
                });
                $interval(function(){
                    scope.buttonName = scope.name;
                    scope.iconName = scope.icon;
                },500,1);
            });
        }
    }
});
