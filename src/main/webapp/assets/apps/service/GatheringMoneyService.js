'use strict';

angular.module('MetronicApp').factory('GatheringMoneyService', ['$rootScope', '$http', '$q','$filter', function($rootScope, $http, $q,$filter){

    var factory = {
    		//获取采购订单信息
    		getSaleOrderInfo: getSaleOrderInfo,
    		
    		//查询结算条款详情
    		selectClauseDetail:selectClauseDetail,
    		
    		//删除
    		delPaymentRecord:delPaymentRecord,
    		//单个查找
    		selectPay:selectPay,
    };

    return factory;

    
    
  //删除付款
    function delPaymentRecord(ids){
        var deferred = $q.defer();  

        $http.post($rootScope.basePath + "/rest/pay/delPaymentRecord", ids).success(function (data) {  
            // 如果连接成功，延时返回给调用者  
            deferred.resolve(data);  
        })  
            .error(function () {  
                deferred.reject('连接服务器出错！');  
            })  
        return deferred.promise;  
          
    };
    
  //通过用户id查找付款
    function selectPay(serialNum){
        var deferred = $q.defer();  

        $.get("rest/pay/selectPayById", {serialNum:serialNum}).success(function (data) { 
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
    
}]);
