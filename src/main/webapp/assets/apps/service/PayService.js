'use strict';

angular.module('MetronicApp').factory('PayService', ['$rootScope', '$http', '$q','$filter', function($rootScope, $http, $q,$filter){
    var REST_SERVICE_URI = $rootScope.basePath + '/rest/contract/findAllUserContract';

    var factory = {
    		//查询列表
    		fetchAllUserContract: fetchAllUserContract,
    		//添加
    		saveUserContract: saveUserContract,
    		//删除
    		delUserContract:delUserContract,
    		//单个查找
    		selectUserContract:selectUserContract,
    		
    		downLoad:downLoad,
    		
    		uploadExcel:uploadExcel
    };

    return factory;

    function fetchAllUserContract() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI)
            .then(
            function (response) {
            	/*for(var i=0;i<response.data.data.length;i++){
            		var myJsDate=$filter('date')(response.data.data[i].startDate,'MM/dd/yyyy');
                    response.data.data[i].startDate=myJsDate;	
            	}*/
            	deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching Users');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function saveUserContract($scope){
    	
        var deferred = $q.defer();  
    	$.post("rest/contract/saveUserContract", {
    		id:$scope.contractVO.id,
			comId:$scope.contractVO.comId,
			contractNum:$scope.contractVO.contractNum,
			contractType:$scope.contractVO.contractType,
			serviceModel:$scopecontractVO.serviceModel,
			settlementClause:$scope.contractVO.settlementClause,
			startDate:$("#startDate").val(),
			endDate:$("#endDate").val(),
			signDate:$("#signDate").val(),
			signer:$scopecontractVO.signer,
			remark:$scope.contractVO.remark,
			file:$scope.myForm.file//传整个表单数据
        }).success(function (data) {  
		            // 如果连接成功，延时返回给调用者  
		            deferred.resolve(data);  
		        }) 
        .error(function () {  
                deferred.reject('连接服务器出错！');  
            })
        return deferred.promise;  
          
    };
    
    function downLoad(name){
    	$http.get($rootScope.basePath + "/rest/contract/resourceDownload",  {filename:name}).success(function (data) {  
        })
    }
    
    /**
     * 导入
     */
    function uploadExcel(){
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
	}
    
  //删除用户
    function delUserContract(ids){
        var deferred = $q.defer();  

        $http.post($rootScope.basePath + "/rest/contract/deleteUserContractS", ids).success(function (data) {  
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

        $.get("rest/contract/selectConbtractById", {ids:ids}).success(function (data) { 
            // 如果连接成功，延时返回给调用者  
            deferred.resolve(data);
            
        })  
            .error(function () {  
                deferred.reject('连接服务器出错！');  
            })  
        return deferred.promise;  
          
    };
    
}]);

