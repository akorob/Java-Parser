"use strict"
app.service("storageService",["localStorageService", function (storage) {
    //init
    var e = {
        product:   "pr_",
        page:      "pa_",
        cart:      "ca_",
        compare:   "co_",
    };

    var storageEmulation = {};
    var isStorage = storage.isSupported;
    var isCookie = storage.cookie.isSupported;

    function storageSet (n, key, value){
        if(isStorage){
            storage.set(n + key, value);
        } else {
            if(n == e.cart && isCookie){
                storage.set(n + key, value);
                return;
            }
            storageEmulation[n + key] = value;
        }
    }
    function storageGet (n, key){
        if(isStorage){
            return storage.get(n + key);
        } else {
            if(n == e.cart && isCookie){
                return storage.get(n + key);
            }
            return storageEmulation[n + key] || null;
        }
    }

    //public

    /*
        //TODO: implement at app bootstrap
        this.dropCache      = function ()           { storage.clearAll(); };
        this.getVersion     = function ()           { return storageGet(e.version, 0); };
        this.setVersion     = function (val)        { storageSet(e.version, 0, val); };
    */

    this.getProduct     = function (key)        { return storageGet(e.product, key); };
    this.setProduct     = function (key, val)   { storageSet(e.product, key, val); };

    this.getPage        = function (key)        { return storageGet(e.page, key); };
    this.setPage        = function (key, val)   { storageSet(e.page, key, val); };

    this.getCompare     = function ()           { return storageGet(e.compare, 0); };
    this.setCompare     = function (val)        { storageSet(e.compare, 0, val); };

    this.getCart        = function ()           { return storageGet(e.cart, 0); };
    this.setCart        = function (val)        { storageSet(e.cart, 0, val); };

}]);