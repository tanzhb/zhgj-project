'use strict';

angular.module('MetronicApp').factory('CompanyInfoService', ['$rootScope', '$http', '$q','$filter', function($rootScope, $http, $q,$filter){

    var factory = {
    		//查找信息
    		getCompanyInfo:getCompanyInfo,
    		
    		updateCompanyInfo:updateCompanyInfo,
    };

    return factory;
    
  //通过用户id查找用户
    function getCompanyInfo(){
        var deferred = $q.defer();  

        $.get("rest/user/getCompanyInfo",).success(function (data) { 
            // 如果连接成功，延时返回给调用者  
            deferred.resolve(data);
            
        })  
            .error(function () {  
                deferred.reject('连接服务器出错！');  
            })  
        return deferred.promise;  
    };
    
    
  //通过用户id查找用户
    function updateCompanyInfo($scope){
        var deferred = $q.defer();  
        $.post("rest/user/updateCompanyInfo",
        	{
        	comId:$scope.company.comId,
        	comNum:$scope.company.comNum,
        	comName:$scope.company.comName,
        	comType:$scope.company.comType,
        	abbreviation:$scope.company.abbreviation,
        	businessNature:$scope.company.businessNature,
        	comNature:$scope.company.comNature,
        	businessType:$scope.company.businessType,
        	registeredCapital:$scope.company.registeredCapital,
        	legalPerson:$scope.company.legalPerson,
        	address:$scope.company.address,
        	taxpayeNumber:$scope.company.taxpayeNumber,
        	tel:$scope.company.tel,
        	remark:$scope.company.remark,
        	}	
        ).success(function (data) { 
            // 如果连接成功，延时返回给调用者  
            deferred.resolve(data);
            
        })  
            .error(function () {  
                deferred.reject('连接服务器出错！');  
            })  
        return deferred.promise;  
    };
    
}]);

