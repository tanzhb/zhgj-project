'use strict';

angular.module('MetronicApp').factory('WarehouseService', ['$rootScope', '$http', '$q', function($rootScope, $http, $q){
    var REST_SERVICE_URI = $rootScope.basePath + '/rest/warehouseposition/viewWarehousepositionList';

    var factory = {
        getAllWarehousepositions: getAllWarehousepositions,
        saveWarehouse: saveWarehouse,
        delWarehouses:delWarehouses,
        selectByWarehouseName:selectByWarehouseName,
        selectBySerialNum:selectBySerialNum,
        uploadExcel:uploadExcel,
        saveWarehousePosition:saveWarehousePosition,
        saveAllWarehousePosition:saveAllWarehousePosition,
        deleteWarehousePosition:deleteWarehousePosition
    };

    return factory;
    //查询所有仓库区置
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
    function  uploadExcel(params){
		var deferred = $q.defer();
		var fd = new FormData();
        var file = document.querySelector('input[type=file]').files[0];
        fd.append('excelFile', file);
		$http.post("rest/warehouse/warehouseImport",  
				fd,{headers:{'Content-Type': undefined},transformRequest: angular.identity}
		).then(function success(result) {
			deferred.resolve(result);//请求成功
		}, function error(err) {
			deferred.reject(err);//请求失败
		});
		return deferred.promise;//返回承诺
	}
    /**
	 * 保存仓库区位信息
	 */
    function saveWarehousePosition(warehouseposition){
		var deferred = $q.defer();
		/*var params = {};  
	    params = JSON.stringify(warehousepositions); */
		debugger;
		$http.post("rest/warehouse/saveWarehousePositionInfo",  
				warehouseposition//传整个表单数据  
    	).then(function success(result) {
            deferred.resolve(result);//请求成功
        }, function error(err) {
            deferred.reject(err);//请求失败
        });
        return deferred.promise;//返回承诺
	};
	 /**
	 * 保存所有仓库区位信息
	 */
    function saveAllWarehousePosition(warehousepositions){
		var deferred = $q.defer();
		var params = {};  
	    params = JSON.stringify(warehousepositions); 
		debugger;
		$http.post("rest/warehouse/saveAllWarehousePositionInfo",  
				params//传整个表单数据  
    	).then(function success(result) {
            deferred.resolve(result);//请求成功
        }, function error(err) {
            deferred.reject(err);//请求失败
        });
        return deferred.promise;//返回承诺
	};
	/**
	 * 删除仓库区位信息
	 */
	 function   deleteWarehousePosition(serialNum){
		var deferred = $q.defer();
		 $http.post("rest/warehouse/deleteWarehousePosition", 
			 	serialNum//传整个表单数据  
	        ).then(function success(result) {
                deferred.resolve(result);//请求成功
            }, function error(err) {
                deferred.reject(err);//请求失败
            });
            return deferred.promise;//返回承诺
	}

}]);
