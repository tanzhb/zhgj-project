'use strict';

angular.module('MetronicApp').factory('PurchaseForecastService', ['$rootScope', '$http', '$q','$filter', function($rootScope, $http, $q,$filter){
    var REST_SERVICE_URI = $rootScope.basePath + '/rest/purchaseForecast/findAllPurchaseForecast';

    var factory = {
    		//查询列表
    		fetchAllPurchaseForecast: fetchAllPurchaseForecast,
    		//添加
    		/*saveUserContract: saveUserContract,*/
    		savePurchaseForecast:savePurchaseForecast,
    		//删除
    		delPurchaseForecast:delPurchaseForecast
    		/*//单个查找
    		selectPurchaseForecast:selectPurchaseForecast,
    		
    		downLoad:downLoad,
    		
    		uploadExcel:uploadExcel*/
    };

    return factory;

    function fetchAllPurchaseForecast() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI)
            .then(
            function (response) {
            	deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching Users');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    
   /* function downLoad(name){
    	$http.get($rootScope.basePath + "/rest/contract/resourceDownload",  {filename:name}).success(function (data) {  
        })
    }*/
    
    /**
     * 导入
     */
   /* function uploadExcel(){
		var deferred = $q.defer();
		var fd = new FormData();
        var file = document.querySelector('input[type=file]').files[0];
        fd.append('excelFile', file);
		$http.post("rest/contract/contractImport",  
				fd,{headers:{'Content-Type': undefined},transformRequest: angular.identity}
		).then(function success(result) {
			deferred.resolve(result);//请求成功
		}, function error(err) {
			deferred.reject(err);//请求失败
		});
		return deferred.promise;//返回承诺
	}*/
    
  //删除采购预测
    function delPurchaseForecast(ids){
        var deferred = $q.defer();  

        $http.post($rootScope.basePath + "/rest/purchaseForecast/deletePurchaseForecast", ids).success(function (data) {  
            // 如果连接成功，延时返回给调用者  
            deferred.resolve(data);  
        })  
            .error(function () {  
                deferred.reject('连接服务器出错！');  
            })  
        return deferred.promise;  
          
    };
    //保存选中的采购预测生成采购计划
    function savePurchaseForecast(ids){
        var deferred = $q.defer();  

        $http.post($rootScope.basePath + "/rest/purchaseForecast/savePurchaseForecast", ids).success(function (data) {  
            // 如果连接成功，延时返回给调用者  
            deferred.resolve(data);  
        })  
            .error(function () {  
                deferred.reject('连接服务器出错！');  
            })  
        return deferred.promise;  
          
    };   
    /* //通过用户id查找用户
    function selectPurchaseForecast(ids){
        var deferred = $q.defer();  

        $.get("rest/contract/selectConbtractById", {ids:ids}).success(function (data) { 
            // 如果连接成功，延时返回给调用者  
            deferred.resolve(data);
            
        })  
            .error(function () {  
                deferred.reject('连接服务器出错！');  
            })  
        return deferred.promise;  
          
    };*/
    
}]);

