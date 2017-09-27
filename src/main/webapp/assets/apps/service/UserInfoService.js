'use strict';

angular.module('MetronicApp').factory('UserInfoService', ['$rootScope', '$http', '$q','$filter', function($rootScope, $http, $q,$filter){

    var factory = {
    		//查找信息
    		getUserInfo:getUserInfo,
    };

    return factory;
    
  //通过用户id查找用户
    function getUserInfo(){
        var deferred = $q.defer();  

        $.get("rest/user/getUserInfo",).success(function (data) { 
            // 如果连接成功，延时返回给调用者  
            deferred.resolve(data);
            
        })  
            .error(function () {  
                deferred.reject('连接服务器出错！');  
            })  
        return deferred.promise;  
    };
    
}]);

