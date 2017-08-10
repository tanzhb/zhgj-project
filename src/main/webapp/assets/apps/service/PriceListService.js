'use strict';

angular.module('MetronicApp').factory('priceListService', ['$rootScope', '$http', '$q', function($rootScope, $http, $q){
    var factory = {
        savePriceList: savePriceList,
        delPriceLists:delPriceLists,
        /*selectByWarehouseName:selectByWarehouseName,*/
        selectBySerialNum:selectBySerialNum
    };

    return factory;
    //保存价格
    function savePriceList(priceList){
        var deferred = $q.defer();  
        console.log("baocun");
        debugger;
        $http.post($rootScope.basePath+"/rest/priceList/savePriceListInfo", priceList).success(function (data) {  
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
    function delPriceLists(serialNums){
        var deferred = $q.defer();  

        $http.post("rest/priceList/deletePriceList", serialNums).success(function (data) {  
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
        $http.post($rootScope.basePath + "/rest/priceList/viewPriceListDetail", serialNum).success(function (data) { 
            // 如果连接成功，延时返回给调用者 
            deferred.resolve(data);  
        })  
            .error(function () {  
                deferred.reject('连接服务器出错！');  
            })  
        return deferred.promise;  
          
    };

    

}]);
