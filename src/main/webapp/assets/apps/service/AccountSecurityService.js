'use strict';

angular.module('MetronicApp').factory('AccountSecurityService', ['$rootScope', '$http', '$q','$filter', function($rootScope, $http, $q,$filter){

    var factory = {
    		//查找信息
    		getUserInfo:getUserInfo,
    		
    		updateEmail:updateEmail,
    		
    		updatePhone:updatePhone,
    };

    return factory;
    
  //通过用户id查找用户
    function getUserInfo(){
        var deferred = $q.defer();  

        $.get("rest/user/getUserInfo").success(function (data) { 
            // 如果连接成功，延时返回给调用者  
            deferred.resolve(data);
            
        })  
            .error(function () {  
                deferred.reject('连接服务器出错！');  
            })  
        return deferred.promise;  
    };
    
  //修改邮箱
    function updateEmail(changeEmail){
        var deferred = $q.defer();  
        $http.post($rootScope.basePath + "/rest/user/changeEmail", changeEmail).success(function (data) {  
            // 如果连接成功，延时返回给调用者  
            deferred.resolve(data);  
        })  
            .error(function () {  
                deferred.reject('连接服务器出错！');  
            })  
        return deferred.promise;  
          
    };
    
  //修改邮箱
    function updatePhone(changePhone){
        var deferred = $q.defer();  
        $http.post($rootScope.basePath + "/rest/user/changePhone", changePhone).success(function (data) {  
            // 如果连接成功，延时返回给调用者  
            deferred.resolve(data);  
        })  
            .error(function () {  
                deferred.reject('连接服务器出错！');  
            })  
        return deferred.promise;  
          
    };
    
}]);

