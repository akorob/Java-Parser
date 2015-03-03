"use strict"
app.service("dataService",["$http", "storageService", "$q" , "initData", function ($http, storage, $q, initData) {
    //init
    var entities = {
        favorite:       "/api/favorite",
        product:        "/api/product",
        products:       "/api/products",
        review:         "/api/review",
        page:           "/api/page"
    }


    //func
    function http(entity, param){
        var d = $q.defer();
        var item;

        //cache check
        if (entity == entities.product) {
            var item = storage.getProduct(param);
            if (item && initData.productVersions[param] == item.version) {
                d.resolve(item);
                return d.promise;
            }
        };


        $http.get(entity + (param ? "/" + param : "")).success(function(data, status, headers, config){
            if (entity == entities.product ) {
                storage.setProduct(data.id, data)
            };

            d.resolve(data);
        }).error(function () {d.reject();});
        return d.promise;
    };



    //public
    this.favorite   = function(){           return http(entities.favorite);             };
    this.product    = function(productId){  return http(entities.product,   productId); };
    this.products   = function(categoryId){ return http(entities.products,  categoryId);};
    this.review     = function(productId){  return http(entities.review,    productId); };
    this.page       = function(pageId){     return http(entities.page,      pageId);    };

}]);