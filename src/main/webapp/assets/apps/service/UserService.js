'use strict';

angular.module('MetronicApp').factory('UserService', ['$rootScope', '$http', '$q', function($rootScope, $http, $q){
    var REST_SERVICE_URI = $rootScope.basePath + '/rest/user/findAllUsers';

    var factory = {
        fetchAllUsers: fetchAllUsers,
        saveUser: saveUser,
        delUsers:delUsers,
        selectByUsername:selectByUsername,
        selectById:selectById,
        updatePass:updatePass,
        solrSearch:solrSearch,
        getUserInfo:getUserInfo,
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
    
    
    //查询所有用户
    function fetchAllUsers() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching Users');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    //保存用户
    function saveUser(user){
    	if(user.createTime != null) user.createTime = Date.parse(new Date(user.createTime));//date格式转换成timestamp格式
    	if(user.updateTime != null) user.updateTime = Date.parse(new Date(user.updateTime));
        var deferred = $q.defer();  
        $http.post($rootScope.basePath + "/rest/user/addUser", user).success(function (data) {  
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

        $http.post($rootScope.basePath + "/rest/user/deleteUsers", ids).success(function (data) {  
            // 如果连接成功，延时返回给调用者  
            deferred.resolve(data);  
        })  
            .error(function () {  
                deferred.reject('连接服务器出错！');  
            })  
        return deferred.promise;  
          
    };
    
    //某用户名是否存在
    function selectByUsername(user){
        var deferred = $q.defer();  

        $http.get($rootScope.basePath + "/rest/user/selectByUsername", user).success(function (data) {  
            // 如果连接成功，延时返回给调用者  
            deferred.resolve(data);  
        })  
            .error(function () {  
                deferred.reject('连接服务器出错！');  
            })  
        return deferred.promise;  
          
    };
    //通过用户id查找用户
    function selectById(ids){
        var deferred = $q.defer();  

        $http.post($rootScope.basePath + "/rest/user/selectById", ids).success(function (data) { 
            // 如果连接成功，延时返回给调用者  
            deferred.resolve(data);  
        })  
            .error(function () {  
                deferred.reject('连接服务器出错！');  
            })  
        return deferred.promise;  
          
    };
    
    //修改密码
    function updatePass(changePassword){
        var deferred = $q.defer();  
        $http.post($rootScope.basePath + "/rest/user/updatePass", changePassword).success(function (data) {  
            // 如果连接成功，延时返回给调用者  
            deferred.resolve(data);  
        })  
            .error(function () {  
                deferred.reject('连接服务器出错！');  
            })  
        return deferred.promise;  
          
    };
    
    //全文检索
    function solrSearch(queryStr, searchType, start, rows) {
        var deferred = $q.defer();
        $http.post(ctx + "rest/search/queryPage", {queryStr:queryStr,searchType:searchType,start:start,rows:rows})
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while solr search');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

    

}]);