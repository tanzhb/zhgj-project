

angular.module('MetronicApp').factory('InvoiceService', ['$rootScope', '$http', '$q', function($rootScope, $http, $q){

    var factory = {
    		saveInvoice:saveInvoice,
    		delInvoices:delInvoices,
            selectDetailBySerialNum:selectDetailBySerialNum,
            getOrderInfoBySerialNum:getOrderInfoBySerialNum,
            saveInvoiceBillingRecord:saveInvoiceBillingRecord,
            startInvoiceProcess:startInvoiceProcess,
            getAuditInfos:getAuditInfos,
            judgeIsApproval:judgeIsApproval
    };

    return factory;
   
    //保存发票信息
    function saveInvoice(invoice){
        var deferred = $q.defer();  
debugger;
        $http.post($rootScope.basePath + "/rest/invoice/saveInvoiceInfo",  JSON.stringify(invoice)).success(function (data) {  
            // 如果连接成功，延时返回给调用者  
        	debugger;
            deferred.resolve(data);  
        })  
            .error(function () {  
                deferred.reject('连接服务器出错！');  
            })  
        return deferred.promise;  
          
    };
    
    //删除发票记录
    function delInvoices(serialNums){
        var deferred = $q.defer();  

        $http.post($rootScope.basePath + "/rest/invoice/deleteInvoice", serialNums).success(function (data) {  
            // 如果连接成功，延时返回给调用者  
            deferred.resolve(data);  
        })  
            .error(function () {  
                deferred.reject('连接服务器出错！');  
            })  
        return deferred.promise;  
          
    };
    
    
    //通过发票serialNum查找发票记录
    function selectDetailBySerialNum(serialNum){//getClauseSettlementBySerialNum
        var deferred = $q.defer();  
        $http.post($rootScope.basePath + "/rest/invoice/invoiceView", serialNum).success(function (data) { 
            // 如果连接成功，延时返回给调用者 
            deferred.resolve(data);  
        })  
            .error(function () {  
                deferred.reject('连接服务器出错！');  
            })  
        return deferred.promise;  
          
    };
    //通过订单serialNum查找订单结算明细条款
    function getOrderInfoBySerialNum(serialNum){//getClauseSettlementBySerialNum
        var deferred = $q.defer();  
        debugger;
        $http.post($rootScope.basePath + "/rest/invoice/getOrderInfoBySerialNum", serialNum).success(function (data) { 
            // 如果连接成功，延时返回给调用者 
            deferred.resolve(data);  
        })  
            .error(function () {  
                deferred.reject('连接服务器出错！');  
            })  
        return deferred.promise;  
          
    };
  //保存开票/收票信息
    function saveInvoiceBillingRecord(invoiceBillingRecord){
        var deferred = $q.defer();  
debugger;
        $http.post($rootScope.basePath + "/rest/invoice/saveInvoiceBillingRecordInfo", invoiceBillingRecord).success(function (data) {  
            // 如果连接成功，延时返回给调用者  
        	debugger;
            deferred.resolve(data);  
        })  
            .error(function () {  
                deferred.reject('连接服务器出错！');  
            })  
        return deferred.promise;  
          
    };
    function startInvoiceProcess (invoiceInfo){
		var deferred = $q.defer();
		$http.post("rest/invoice/startInvoiceProcess", invoiceInfo
		).success(function (data) {
            // 如果连接成功，延时返回给调用者
            deferred.resolve(data);
        }).error(function () {
            deferred.reject('连接服务器出错！');
        })
		return deferred.promise;
	}
	 function getAuditInfos (ids) {
				        var deferred = $q.defer();  
				        $http.post(ctx + "rest/invoice/toApproval/" + ids).success(function (data) {  
				        	// 如果连接成功，延时返回给调用者  
				            deferred.resolve(data);  
				        })  
				            .error(function () {  
				                deferred.reject('连接服务器出错！');  
				            })  
				        return deferred.promise;  
				    }
	 function judgeIsApproval (serialNum) {
		 var deferred = $q.defer();  
		 debugger;
		         $http.post($rootScope.basePath + "/rest/invoice/judgeIsApproval", serialNum).success(function (data) {  
		             // 如果连接成功，延时返回给调用者  
		         	debugger;
		             deferred.resolve(data);  
		         })  
		             .error(function () {  
		                 deferred.reject('连接服务器出错！');  
		             })  
		         return deferred.promise;  
	    }
}]);
