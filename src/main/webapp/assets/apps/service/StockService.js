

angular.module('MetronicApp').factory('StockService', ['$rootScope', '$http', '$q', function($rootScope, $http, $q){

    var factory = {
        saveStock:saveStock,
        delStocks:delStocks,
        selectBySerialNum:selectBySerialNum,
        uploadExcel:uploadExcel,
        selectDetailBySerialNum:selectDetailBySerialNum,
        saveStockForSupply:saveStockForSupply,
        selectDetailBySerialNumForSupply:selectDetailBySerialNumForSupply,
        delStocksForSupply:delStocksForSupply
        
    };

    return factory;
   
    //保存库存
    function saveStock(stock){
        var deferred = $q.defer();  
debugger;
        $http.post($rootScope.basePath + "/rest/stock/saveStockInfo", stock).success(function (data) {  
            // 如果连接成功，延时返回给调用者  
        	debugger;
            deferred.resolve(data);  
        })  
            .error(function () {  
                deferred.reject('连接服务器出错！');  
            })  
        return deferred.promise;  
          
    };
    //保存供应商库存
    function saveStockForSupply(stockSupplyRecord){
        var deferred = $q.defer();  
debugger;
        $http.post($rootScope.basePath + "/rest/stock/saveStockSupplyInfo", stockSupplyRecord).success(function (data) {  
            // 如果连接成功，延时返回给调用者  
        	debugger;
            deferred.resolve(data);  
        })  
            .error(function () {  
                deferred.reject('连接服务器出错！');  
            })  
        return deferred.promise;  
          
    };
    //删除供应商库存
    function delStocksForSupply(serialNums){
        var deferred = $q.defer();  

        $http.post($rootScope.basePath + "/rest/stock/delStocksForSupply", serialNums).success(function (data) {  
            // 如果连接成功，延时返回给调用者  
            deferred.resolve(data);  
        })  
            .error(function () {  
                deferred.reject('连接服务器出错！');  
            })  
        return deferred.promise;  
          
    };
    //删除库存
    function delStocks(serialNums){
        var deferred = $q.defer();  

        $http.post($rootScope.basePath + "/rest/stock/deleteStock", serialNums).success(function (data) {  
            // 如果连接成功，延时返回给调用者  
            deferred.resolve(data);  
        })  
            .error(function () {  
                deferred.reject('连接服务器出错！');  
            })  
        return deferred.promise;  
          
    };
    
    //通过库存serialNum查找库存
    function selectBySerialNum(serialNum){
        var deferred = $q.defer();  
        $http.post($rootScope.basePath + "/rest/stock/viewStock", serialNum).success(function (data) { 
            // 如果连接成功，延时返回给调用者 
            deferred.resolve(data);  
        })  
            .error(function () {  
                deferred.reject('连接服务器出错！');  
            })  
        return deferred.promise;  
          
    };
    function  uploadExcel(params){//导入库存
		var deferred = $q.defer();
		var fd = new FormData();
        var file = document.querySelector('input[type=file]').files[0];
        fd.append('excelFile', file);
		$http.post("rest/stock/stockImport",  
				fd,{headers:{'Content-Type': undefined},transformRequest: angular.identity}
		).then(function success(result) {
			deferred.resolve(result);//请求成功
		}, function error(err) {
			deferred.reject(err);//请求失败
		});
		return deferred.promise;//返回承诺
	}
    //通过库存serialNum库存详情页面信息
    function selectDetailBySerialNum(serialNum){
        var deferred = $q.defer();  
        $http.post($rootScope.basePath + "/rest/stock/viewStockDetail", serialNum).success(function (data) { 
            // 如果连接成功，延时返回给调用者 
            deferred.resolve(data);  
        })  
            .error(function () {  
                deferred.reject('连接服务器出错！');  
            })  
        return deferred.promise;  
          
    };
  //通过供应商库存serialNum库存详情页面信息
    function selectDetailBySerialNumForSupply(serialNum){
        var deferred = $q.defer();  
        $http.post($rootScope.basePath + "/rest/stock/viewStockSupplyDetail", serialNum).success(function (data) { 
            // 如果连接成功，延时返回给调用者 
            deferred.resolve(data);  
        })  
            .error(function () {  
                deferred.reject('连接服务器出错！');  
            })  
        return deferred.promise;  
          
    };
}]);
