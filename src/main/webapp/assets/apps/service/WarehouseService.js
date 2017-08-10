'use strict';

angular.module('MetronicApp').factory('WarehouseService', ['$rootScope', '$http', '$q', function($rootScope, $http, $q){
    var REST_SERVICE_URI = $rootScope.basePath + '/rest/warehouseposition/viewWarehousepositionList';

    var factory = {
        getAllWarehousepositions: getAllWarehousepositions,
        saveWarehouse: saveWarehouse,
        delWarehouses:delWarehouses,
        selectByWarehouseName:selectByWarehouseName,
        selectBySerialNum:selectBySerialNum
    };

    return factory;
    //查询所有仓库位置
    function getAllWarehousepositions() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI)
            .then(
            function (response) {
            	debugger;
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('连接服务器出错');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    //保存仓库
    function saveWarehouse(warehouse){
        var deferred = $q.defer();  
debugger;
        $http.post($rootScope.basePath + "/rest/warehouse/saveWarehouseInfo", warehouse).success(function (data) {  
            // 如果连接成功，延时返回给调用者  
        	debugger;
            deferred.resolve(data);  
        })  
            .error(function () {  
                deferred.reject('连接服务器出错！');  
            })  
        return deferred.promise;  
          
    };
    
    //删除仓库
    function delWarehouses(serialNums){
        var deferred = $q.defer();  

        $http.post($rootScope.basePath + "/rest/warehouse/deleteWarehouse", serialNums).success(function (data) {  
            // 如果连接成功，延时返回给调用者  
            deferred.resolve(data);  
        })  
            .error(function () {  
                deferred.reject('连接服务器出错！');  
            })  
        return deferred.promise;  
          
    };
    
    //某仓库名是否存在
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
          
    };
    //通过仓库serialNum查找仓库
    function selectBySerialNum(serialNum){
        var deferred = $q.defer();  
        $http.post($rootScope.basePath + "/rest/warehouse/viewWarehouseDetail", serialNum).success(function (data) { 
            // 如果连接成功，延时返回给调用者 
            deferred.resolve(data);  
        })  
            .error(function () {  
                deferred.reject('连接服务器出错！');  
            })  
        return deferred.promise;  
          
    };

    

}]);