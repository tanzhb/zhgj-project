

angular.module('MetronicApp').factory('InvoiceService', ['$rootScope', '$http', '$q', function($rootScope, $http, $q){

    var factory = {
    		saveInvoice:saveInvoice,
    		delInvoices:delInvoices,
            selectDetailBySerialNum:selectDetailBySerialNum
    };

    return factory;
   
    //保存发票信息
    function saveInvoice(invoice){
        var deferred = $q.defer();  
debugger;
        $http.post($rootScope.basePath + "/rest/invoice/saveInvoiceInfo", invoice).success(function (data) {  
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
    function selectDetailBySerialNum(serialNum){
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
   
}]);
