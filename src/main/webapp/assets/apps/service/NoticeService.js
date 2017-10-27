/**
 * 
 */

angular.module('MetronicApp').service('noticeService',['$http','$q',function($http,$q) {
	
	/**
	 * 获取企业信息数据
	 */
	this.getNoticeInfo = function (serialNum){
		var deferred = $q.defer();
		$http.get("rest/notice/getNoticeInfo", {  
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
	this.saveNotice = function (notice){
		var deferred = $q.defer();
		var postCfg = {
			    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
			    transformRequest: function (data) {
			        return $.param(data);
			    }
			};
		$http.post("rest/notice/saveNotice", 
				notice,postCfg//传整个表单数据  
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
	this.deleteNoticeBatch = function(serialNums){
		var deferred = $q.defer();
		$http.post("rest/notice/deleteNoticeBatch",  
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
	this.createTable = function(pageSize,pageIndex,params){
		var deferred = $q.defer();
		var notice = {};
		notice.pageSize = pageSize;
		notice.pageIndex = pageIndex;
		var postCfg = {
			    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
			    transformRequest: function (data) {
			        return $.param(data);
			    }
			};
		$http.post("rest/notice/myNoticeList",   
				notice,postCfg
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
	this.deleteMyNotice = function(serialNum){
		var deferred = $q.defer();
		$http.post("rest/notice/deleteMyNotice",   
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
	this.readMyNotice = function(serialNum){
		var deferred = $q.defer();
		$http.post("rest/notice/readMyNotice",   
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
	this.applyNotice = function(notice){
		var deferred = $q.defer();
		var postCfg = {
			    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
			    transformRequest: function (data) {
			        return $.param(data);
			    }
			};
		$http.post("rest/notice/applyNotice",   
				notice,postCfg
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
	this.getAuditInfos = function(id){
		var deferred = $q.defer();
		$http.post(ctx+"rest/notice/toApproval/" + id
		).success(function (data) {  
        	
        	// 如果连接成功，延时返回给调用者  
            deferred.resolve(data);  
        })  
            .error(function () {  
                deferred.reject('连接服务器出错！');  
            })  
        return deferred.promise;  
	}
	
	 //最新公告数
    this.getNoticeCount = function(){
        var deferred = $q.defer();  

        $.get("rest/notice/newNoticeCount").success(function (data) { 
            // 如果连接成功，延时返回给调用者  
            deferred.resolve(data);
            
        }).error(function () {  
                deferred.reject('连接服务器出错！');  
        })  
        return deferred.promise;  
    };


}]); 
