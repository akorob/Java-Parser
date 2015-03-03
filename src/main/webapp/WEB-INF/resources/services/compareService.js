/**
 * Created by dev on 09.01.2015.
 */
'use strict'
app.service('compareService',['$http' ,'storageService','$rootScope',function ($http, storageService, $rootScope){
    var liveTime = 20*60*1000;//20 min for test

    this.get = function () {
        var compare = storageService.getCompare();
        if (!compare) return null;
        var age = (new Date()).getTime() - new Date(compare.createTime).getTime();
        if (age > liveTime) {
            $rootScope.$broadcast("compareChange",0 );
            storageService.setCompare(null);
            return null;
        }
        return compare.items;
    };

    this.getSize = function () {
        var compare = storageService.getCompare();
        if (!compare) return 0;
        return compare.items.length;
    };



    this.set = function (value) {
        storageService.setCompare(value);
    };

    this.remove = function (productId) {
        var compare = storageService.getCompare();
        var list = [];
        compare.items.forEach(function(entry){
            if (entry != productId){
                list.push(entry);
            }
        })
        if (list.length==0){
            storageService.setCompare(null);
            $rootScope.$broadcast("compareChange",0 );
            return;
        }
        $rootScope.$broadcast("compareChange",list.length );
        storageService.setCompare({createTime:new Date(),items: list});
    };

    this.addNewItem = function(productId){
        var compare = storageService.getCompare();
        if (!compare) {
            compare={createTime:new Date(),items: [productId]};

        } else{
            var existItems = compare.items;
            var isExist = false;
            existItems.forEach(function(entry){
                if (entry==productId){
                    isExist=true;
                }
            })
            if(!isExist){
                compare.items.push(productId);
            }
            compare.createTime = new Date();
        }
        storageService.setCompare(compare);
        $rootScope.$broadcast("compareChange",compare.items.length );
    }
}]);

