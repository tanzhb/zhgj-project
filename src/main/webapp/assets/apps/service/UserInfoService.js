'use strict';

angular.module('MetronicApp').factory('UserInfoService', ['$rootScope', '$http', '$q','$filter', function($rootScope, $http, $q,$filter){

    var factory = {
    		//查找信息
    		getUserInfo:getUserInfo,
    		getMessageCount:getMessageCount,
    		getNoticeCount:getNoticeCount
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
    
    //最新消息数
    function getMessageCount(){
        var deferred = $q.defer();  

        $.get("rest/message/newMessageCount").success(function (data) { 
            // 如果连接成功，延时返回给调用者  
            deferred.resolve(data);
            
        })  
            .error(function () {  
                deferred.reject('连接服务器出错！');  
            })  
        return deferred.promise;  
    };
    
    //最新公告数
    function getNoticeCount(){
        var deferred = $q.defer();  

        $.get("rest/notice/newNoticeCount").success(function (data) { 
            // 如果连接成功，延时返回给调用者  
            deferred.resolve(data);
            
        })  
            .error(function () {  
                deferred.reject('连接服务器出错！');  
            })  
        return deferred.promise;  
    };
    
}]);

