'use strict';

angular.module('MetronicApp').factory('ContractService', ['$rootScope', '$http', '$q','$filter', function($rootScope, $http, $q,$filter){
    var REST_SERVICE_URI = $rootScope.basePath + '/rest/user/findAllUserContract';

    var factory = {
    		fetchAllUserContract: fetchAllUserContract,
    		saveUser: saveUser,
    		delUsers:delUsers,
    		selectUserContract:selectUserContract
    };

    return factory;

    function fetchAllUserContract() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI)
            .then(
            function (response) {
            	for(var i=0;i<response.data.data.length;i++){
            		var myJsDate=$filter('date')(response.data.data[i].startDate,'MM/dd/yyyy');
                    response.data.data[i].startDate=myJsDate;	
            	}
            	deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching Users');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function saveUser(contractVO){
    	debugger
        var deferred = $q.defer();  

        /*$http.post($rootScope.basePath + "/rest/user/saveUserContract", contractVO)*/
    	$.post("rest/user/saveUserContract", {
    		id:contractVO.id,
			comId:contractVO.comId,
			contractNum:contractVO.contractNum,
			contractType:contractVO.contractType,
			serviceModel:contractVO.serviceModel,
			settlementClause:contractVO.settlementClause,
			startDate:$("#startDate").val(),
			endDate:$("#endDate").val(),
			signDate:$("#signDate").val(),
			signer:contractVO.signer,
			remark:contractVO.remark//传整个表单数据
        }).success(function (data) {  
		            // 如果连接成功，延时返回给调用者  
		            deferred.resolve(data);  
		        }) 
        .error(function () {  
                deferred.reject('连接服务器出错！');  
            })
        return deferred.promise;  
          
    };
    
  //删除用户
    function delUsers(ids){
        var deferred = $q.defer();  

        $http.post($rootScope.basePath + "/rest/user/deleteUserContractS", ids).success(function (data) {  
            // 如果连接成功，延时返回给调用者  
            deferred.resolve(data);  
        })  
            .error(function () {  
                deferred.reject('连接服务器出错！');  
            })  
        return deferred.promise;  
          
    };
    
  //通过用户id查找用户
    function selectUserContract(ids){
        var deferred = $q.defer();  

        $http.post($rootScope.basePath + "/rest/user/selectConbtractById", ids).success(function (data) { 
            // 如果连接成功，延时返回给调用者  
            deferred.resolve(data);
            
        })  
            .error(function () {  
                deferred.reject('连接服务器出错！');  
            })  
        return deferred.promise;  
          
    };
    
}]);

