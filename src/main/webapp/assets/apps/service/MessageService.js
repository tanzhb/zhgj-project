/**
 * 
 */

angular.module('MetronicApp').service('messageService',['$http','$q',function($http,$q) {
	
	/**
	 * 获取企业信息数据
	 */
	this.getMessageInfo = function (serialNum){
		var deferred = $q.defer();
		$http.get("rest/message/getMessageInfo", {  
			params:{serialNum:serialNum}//传整个表单数据  
    	}).then(function success(result) {
            deferred.resolve(result);//请求成功
        }, function error(err) {
            deferred.reject(err);//请求失败
        });
        return deferred.promise;//返回承诺
	}
	
	
	/**
	 * 保存
	 */
	this.saveMessage = function (message){
		var deferred = $q.defer();
		$http.post("rest/message/saveMessage", 
			message//传整个表单数据  
		).then(function success(result) {
			deferred.resolve(result);//请求成功
		}, function error(err) {
			deferred.reject(err);//请求失败
		});
		return deferred.promise;//返回承诺
	}
	

	
	/**
	 * 批量删除
	 */
	this.deleteMessageBatch = function(serialNums){
		var deferred = $q.defer();
		$http.post("rest/message/deleteMessageBatch",  
				serialNums//传整个表单数据  
		).then(function success(result) {
			deferred.resolve(result);//请求成功
		}, function error(err) {
			deferred.reject(err);//请求失败
		});
		return deferred.promise;//返回承诺
	}
	
	/**
	 * 获取列表数据
	 */
	this.createBusinessMessageTable = function(pageSize,pageIndex,params){
		var deferred = $q.defer();
		var message = {};
		message.pageSize = pageSize;
		message.pageIndex = pageIndex;
		$http.post("rest/message/businessMessageList",   
				message
    	).then(function success(result) {
            deferred.resolve(result);//请求成功
        }, function error(err) {
            deferred.reject(err);//请求失败
        });
		return deferred.promise;//返回承诺
	}
	
	/**
	 * 获取列表数据
	 */
	this.createSystemMessageTable = function(pageSize,pageIndex,params){
		var deferred = $q.defer();
		var message = {};
		message.pageSize = pageSize;
		message.pageIndex = pageIndex;
		$http.post("rest/message/systemMessageList",   
				message
		).then(function success(result) {
			deferred.resolve(result);//请求成功
		}, function error(err) {
			deferred.reject(err);//请求失败
		});
		return deferred.promise;//返回承诺
	}
	
	/**
	 * 获取列表数据
	 */
	this.deleteMyMessage = function(serialNum){
		var deferred = $q.defer();
		$http.post("rest/message/deleteMyMessage",   
				serialNum
		).then(function success(result) {
			deferred.resolve(result);//请求成功
		}, function error(err) {
			deferred.reject(err);//请求失败
		});
		return deferred.promise;//返回承诺
	}
	
	/**
	 * 获取列表数据
	 */
	this.readMessage = function(serialNum){
		var deferred = $q.defer();
		$http.post("rest/message/readMessage",   
				serialNum
		).then(function success(result) {
			deferred.resolve(result);//请求成功
		}, function error(err) {
			deferred.reject(err);//请求失败
		});
		return deferred.promise;//返回承诺
	}
	
	/**
	 * 获取系统消息
	 */
	this.systemMessageSize = function(){
		var deferred = $q.defer();
		$.get(ctx + "/rest/message/systemMessageSize").success(function (data) {
	        // 如果连接成功，延时返回给调用者  
	        deferred.resolve(data);
	    }).error(function () {  
	        deferred.reject('连接服务器出错！');  
	    })
	    return deferred.promise;
	}
	
	/**
	 * 获取业务提醒
	 */
	this.businessMessageSize = function(){
		var deferred = $q.defer();
		$.get(ctx + "/rest/message/businessMessageSize").success(function (data) {
	        // 如果连接成功，延时返回给调用者  
	        deferred.resolve(data);
	    }).error(function () {  
	        deferred.reject('连接服务器出错！');  
	    })
	    return deferred.promise;
	}
	



}]); 
