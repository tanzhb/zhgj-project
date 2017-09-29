/**
 * 
 */

angular.module('MetronicApp').service('commonService',['$http','$q',function($http,$q) {
	
	
	/**
	 * 初始化供应商
	 */
	this.initSuppliers = function (){
		var deferred = $q.defer();
		$http.get("rest/company/getSuppliers")
		.then(function success(result) {
			deferred.resolve(result);//请求成功
		}, function error(err) {
			deferred.reject(err);//请求失败
		});
		return deferred.promise;//返回承诺
	}
	
	/**
	 * 初始化采购商
	 */
	this.initCustomers = function (){
		var deferred = $q.defer();
		$http.get("rest/company/getCustomers")
		.then(function success(result) {
            deferred.resolve(result);//请求成功
        }, function error(err) {
            deferred.reject(err);//请求失败
        });
        return deferred.promise;//返回承诺
	}
	
	/**
	 * 获取当前登录人信息
	 */
	this.getCurrentUser = function (){
		var deferred = $q.defer();
		$http.get("rest/company/getCurrentUser")
		.then(function success(result) {
            deferred.resolve(result);//请求成功
        }, function error(err) {
            deferred.reject(err);//请求失败
        });
        return deferred.promise;//返回承诺
	}
	
	/**
	 * 获取订单编号
	 */
	this.getOrderNum = function (){
		var deferred = $q.defer();
		$http.get("rest/company/getOrderNum")
		.then(function success(result) {
			deferred.resolve(result);//请求成功
		}, function error(err) {
			deferred.reject(err);//请求失败
		});
		return deferred.promise;//返回承诺
	}

	
	
	


}]); 
