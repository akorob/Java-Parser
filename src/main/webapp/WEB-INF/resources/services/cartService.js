/**
 * Created by Andrey on 05.01.2015.
 */
'use strict'
app.service('cartService',['$http' ,'storageService','$rootScope',function ($http, storageService, $rootScope){
    var cartLiveTime = 20*60*1000;//20 min for test

    this.get = function () {
        var cart = storageService.getCart();
        if (!cart) return null;
        var age = (new Date()).getTime() - new Date(cart.createTime).getTime();
        if (age > cartLiveTime) {
            storageService.setCart(null);
            return null;
        }
        return cart;
    };

    this.getSize = function () {
        var cart = storageService.getCart();
        if (!cart) return 0;
        return cart.items.length;
    };

    this.setQuantity = function(productId, quantity){
        var cart = storageService.getCart();
        cart.items.forEach(function(entry){
            if (entry.productId==productId){
                entry.quantity = quantity;
            }
        })
        storageService.setCart(cart);
    }



    this.set = function (value) {
        $rootScope.$broadcast('cartChange', value.items.length);
        storageService.setCart(value);
    };

    this.remove = function (productId) {
        var cart = storageService.getCart();
        var list = [];
        cart.items.forEach(function(entry){
            if (entry.productId!=productId){
                list.push(entry);
            }
        })
        if (list.length==0){
            $rootScope.$broadcast('cartChange', 0);
            storageService.setCart(null);
            return;
        }
        $rootScope.$broadcast('cartChange', list.length);
        storageService.setCart({createTime:new Date(),items: list});
    };

    this.addNewItem = function(preorder){
        var cart = storageService.getCart();
        if (!cart) {
            preorder.id=1;
            cart={createTime:new Date(),items: [preorder]};
        } else{
            var existItems = cart.items;
            var isExist = false;
            existItems.forEach(function(entry){
                if (entry.productId==preorder.productId){
                    entry.quantity+=preorder.quantity;
                    isExist=true;
                }
            });
            if(!isExist){
                preorder.id=cart.items.length;
                cart.items.push(preorder);
                cart.createTime = new Date();
            }
        }

        $rootScope.$broadcast('cartChange', cart.items.length);
        storageService.setCart(cart);
    }
}]);

