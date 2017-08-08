'use strict';

angular.module('MetronicApp').factory('PriceService', ['$rootScope', '$http', '$q', function($rootScope, $http, $q){
    var factory = {
        savePrice: savePrice,
        delPrices:delPrices,
        /*selectByWarehouseName:selectByWarehouseName,*/
        selectBySerialNum:selectBySerialNum
    };

    return factory;
    //保存价格
    function savePrice(price){
        var deferred = $q.defer();  
debugger;
        $http.post($rootScope.basePath + "/rest/price/savePriceInfo", price).success(function (data) {  
            // 如果连接成功，延时返回给调用者  
        	debugger;
            deferred.resolve(data);  
        })  
            .error(function () {  
                deferred.reject('连接服务器出错！');  
            })  
        return deferred.promise;  
          
    };
    
    //删除价格
    function delPrices(serialNums){
        var deferred = $q.defer();  

        $http.post($rootScope.basePath + "/rest/price/deletePrice", serialNums).success(function (data) {  
            // 如果连接成功，延时返回给调用者  
            deferred.resolve(data);  
        })  
            .error(function () {  
                deferred.reject('连接服务器出错！');  
            })  
        return deferred.promise;  
          
    };
    
   /* //某仓库名是否存在
    function selectByWarehouseName(warehouseName){
        var deferred = $q.defer();  
        $http.get($rootScope.basePath + "/rest/warehouse/selectByWarehousename", warehouseName).success(function (data) {  
            // 如果连接成功，延时返回给调用者  
            deferred.resolve(data);  
        })  
            .error(function () {  
                deferred.reject('连接服务器出错！');  
            })  
        return deferred.promise;  
          
    };*/
    //通过价格serialNum查找价格
    function selectBySerialNum(serialNum){
        var deferred = $q.defer();  
        $http.post($rootScope.basePath + "/rest/price/viewPriceDetail", serialNum).success(function (data) { 
            // 如果连接成功，延时返回给调用者 
            deferred.resolve(data);  
        })  
            .error(function () {  
                deferred.reject('连接服务器出错！');  
            })  
        return deferred.promise;  
          
    };

    

}]);
