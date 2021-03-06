'use strict';

angular.module('MetronicApp').factory('ReceiveMemoService', ['$rootScope', '$http', '$q','$filter', function($rootScope, $http, $q,$filter){
    var REST_SERVICE_URI = $rootScope.basePath + '/rest/contract/findAllUserContract';

    var factory = {
    		//删除
    		delReceiveMemo:delReceiveMemo,
    		//单个查找
    		selectReceiveMemo:selectReceiveMemo,
    		saveVerificateData:saveVerificateData//保存核销记录
    		
    };

    return factory;

    
  //保存附件
 function saveFile(File) {
		var deferred = $q.defer();
		var params = {};
		params = JSON.stringify(File);
		$http.post("rest/pay/saveFile", params
		).success(function (data) {
            // 如果连接成功，延时返回给调用者
            deferred.resolve(data);
        }).error(function () {
            deferred.reject('连接服务器出错！');
        })
		return deferred.promise;
	};
	
	
	 //更新附件
	 function updateFile(File) {
			var deferred = $q.defer();
			var params = {};
			params = JSON.stringify(File);
			$http.post("rest/pay/updateFile", params
			).success(function (data) {
	            // 如果连接成功，延时返回给调用者
	            deferred.resolve(data);
	        }).error(function () {
	            deferred.reject('连接服务器出错！');
	        })
			return deferred.promise;
		};
    
    
  //删除付款
    function delReceiveMemo(ids){
        var deferred = $q.defer();  

        $http.post($rootScope.basePath + "/rest/pay/delReceiveMemo", ids).success(function (data) {  
            // 如果连接成功，延时返回给调用者  
            deferred.resolve(data);  
        })  
            .error(function () {  
                deferred.reject('连接服务器出错！');  
            })  
        return deferred.promise;  
          
    };
    
    
    //确认收款
    function confirmGatheringMoney(serialNum){
    	var deferred = $q.defer();  
        $http.post($rootScope.basePath + "/rest/pay/confirmGatheringMoney", serialNum).success(function (data) {  
            // 如果连接成功，延时返回给调用者  
            deferred.resolve(data);  
        })  
            .error(function () {  
                deferred.reject('连接服务器出错！');  
            })  
        return deferred.promise; 
    	
    }
    
  //通过用户id查找收款水单
    function selectReceiveMemo(serialNum){
        var deferred = $q.defer();  

        $.get("rest/pay/selectReceiveMemo", {serialNum:serialNum}).success(function (data) { 
            // 如果连接成功，延时返回给调用者  
            deferred.resolve(data);
            
        })  
            .error(function () {  
                deferred.reject('连接服务器出错！');  
            })  
        return deferred.promise;  
          
    };
    
    //查询采购订单信息
    function getSaleOrderInfo(serialNum){
        var deferred = $q.defer();  

        $http.get("rest/pay/getSaleOrderInfo", {params:{serialNum:serialNum}})
        .success(function (data) {  
            // 如果连接成功，延时返回给调用者  
            deferred.resolve(data);  
        })  
            .error(function () {  
                deferred.reject('连接服务器出错！');  
            })  
        return deferred.promise;  
          
    };
    
    //查询结算条款详情
    function selectClauseDetail(serialNum){
    	 var deferred = $q.defer();  
         $http.get("rest/pay/selectClauseDetail", {params:{serialNum:serialNum}})
         .success(function (data) {  
             // 如果连接成功，延时返回给调用者  
             deferred.resolve(data);  
         })  
             .error(function () {  
                 deferred.reject('连接服务器出错！');  
             })  
         return deferred.promise; 
    }
    
  //付款节点是“合同签订”时查询日期 
    function selectDateTypeContract(serialNum){
    	var deferred = $q.defer();  

        $.get("rest/pay/selectDateTypeContract", {serialNum:serialNum}).success(function (data) { 
            // 如果连接成功，延时返回给调用者  
            deferred.resolve(data);
        })  
            .error(function () {  
                deferred.reject('连接服务器出错！');  
            })  
        return deferred.promise;  
    	
    };
    
  //付款节点是“提货前”时查询日期 
    function selectDateTypeDelivery(serialNum){
    	var deferred = $q.defer();  

        $.get("rest/pay/selectDateTypeDelivery", {serialNum:serialNum}).success(function (data) { 
            // 如果连接成功，延时返回给调用者  
            deferred.resolve(data);
        })  
            .error(function () {  
                deferred.reject('连接服务器出错！');  
            })  
        return deferred.promise;  
    }
    
  //付款节点是“到货后”时查询日期 
    function selectDateTypeTakeDelivery(serialNum){
    	var deferred = $q.defer();  

        $.get("rest/pay/selectDateTypeTakeDelivery", {serialNum:serialNum}).success(function (data) { 
            // 如果连接成功，延时返回给调用者  
            deferred.resolve(data);
        })  
            .error(function () {  
                deferred.reject('连接服务器出错！');  
            })  
        return deferred.promise;  
    }
    /**
	 * 保存出库信息
	 */
    function saveVerificateData (params){
		var deferred = $q.defer();
		$http.post("rest/pay/saveVerificateData", 
				JSON.stringify(params)//传整个表单数据  
		).then(function success(result) {
			deferred.resolve(result);//请求成功
		}, function error(err) {
			deferred.reject(err);//请求失败
		});
		return deferred.promise;//返回承诺
	}
    
}]);

