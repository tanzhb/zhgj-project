

angular.module('MetronicApp').factory('StockInOutService', ['$rootScope', '$http', '$q', function($rootScope, $http, $q){

    var factory = {
    		saveStockInOutCheck:saveStockInOutCheck,
    		delStockInOutChecks:delStockInOutChecks,
            uploadExcel:uploadExcel,
            selectDetailBySerialNum:selectDetailBySerialNum,
            judgeIsExistBySerialNum:judgeIsExistBySerialNum,
            getMaterialBySerialNum:getMaterialBySerialNum,
            updateStockInOutCheckStatus:updateStockInOutCheckStatus
    };

    return factory;
   
    //保存出入库记录
    function saveStockInOutCheck(stockInOutCheck){
        var deferred = $q.defer();  
debugger;
        $http.post($rootScope.basePath + "/rest/stockInOut/saveStockInOutCheckInfo", stockInOutCheck).success(function (data) {  
            // 如果连接成功，延时返回给调用者  
        	debugger;
            deferred.resolve(data);  
        })  
            .error(function () {  
                deferred.reject('连接服务器出错！');  
            })  
        return deferred.promise;  
          
    };
    
    //删除出入库记录
    function delStockInOutChecks(serialNums){
        var deferred = $q.defer();  

        $http.post($rootScope.basePath + "/rest/stockInOut/deleteStockInOutCheck", serialNums).success(function (data) {  
            // 如果连接成功，延时返回给调用者  
            deferred.resolve(data);  
        })  
            .error(function () {  
                deferred.reject('连接服务器出错！');  
            })  
        return deferred.promise;  
          
    };
    
    
    //通过出入库serialNum查找出入库记录
    function selectDetailBySerialNum(serialNum){
        var deferred = $q.defer();  
        $http.post($rootScope.basePath + "/rest/stockInOut/stockInOutCheckView", serialNum).success(function (data) { 
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
		$http.post("rest/stockInOut/stockInOutCheckImport",  
				fd,{headers:{'Content-Type': undefined},transformRequest: angular.identity}
		).then(function success(result) {
			deferred.resolve(result);//请求成功
		}, function error(err) {
			deferred.reject(err);//请求失败
		});
		return deferred.promise;//返回承诺
	}
    //通过发货单/收货单流水号判断是否已有关联的出入库检测存在
    function judgeIsExistBySerialNum(serialNum){
        var deferred = $q.defer();  
        $http.post($rootScope.basePath + "/rest/stockInOut/stockInOutCheckIsExist", serialNum).success(function (data) {  
            // 如果连接成功，延时返回给调用者  
            deferred.resolve(data);  
        })  
            .error(function () {  
                deferred.reject('连接服务器出错！');  
            })  
        return deferred.promise;  
          
    };
    function getMaterialBySerialNum(serialNum){
        var deferred = $q.defer();  
        $http.post($rootScope.basePath + "/rest/stockInOut/getMaterialBySerialNum", serialNum).success(function (data) { 
            // 如果连接成功，延时返回给调用者 
            deferred.resolve(data);  
        })  
            .error(function () {  
                deferred.reject('连接服务器出错！');  
            })  
        return deferred.promise;  
          
    };
    //确认检验
    function updateStockInOutCheckStatus(serialNum){
        var deferred = $q.defer();  
debugger;
        $http.post($rootScope.basePath + "/rest/stockInOut/updateStockInOutCheckStatus", serialNum).success(function (data) {  
            // 如果连接成功，延时返回给调用者  
        	debugger;
            deferred.resolve(data);  
        })  
            .error(function () {  
                deferred.reject('连接服务器出错！');  
            })  
        return deferred.promise;  
          
    };
}]);
