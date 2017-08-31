'use strict';

angular.module('MetronicApp').factory('AddVacationService', ['$rootScope', '$http', '$q', function($rootScope, $http, $q){
    
	   var factory = {
			   applyVacation:applyVacation,//发起申请
			   toApproval:toApproval//办理流程
		    };

		    return factory;
		    
		    function applyVacation(vacation) {
		        var deferred = $q.defer();  
		        $http.post($rootScope.basePath + "/rest/vacationAction/doAdd", vacation).success(function (data) {  
		        	
		        	// 如果连接成功，延时返回给调用者  
		            deferred.resolve(data);  
		        })  
		            .error(function () {  
		                deferred.reject('连接服务器出错！');  
		            })  
		        return deferred.promise;  
		    }
		    
		    function toApproval(taskId) {
		        var deferred = $q.defer();  
		        $http.get($rootScope.basePath + "/rest/vacationAction/toApproval/"+taskId).success(function (data) {  
		        	alert(data);
		        	// 如果连接成功，延时返回给调用者  
		            deferred.resolve(data);  
		        }).error(function (e) {
		        		console.log(e);
		                deferred.reject('连接服务器出错！');  
		            })  
		        return deferred.promise;  
		    }
}]);