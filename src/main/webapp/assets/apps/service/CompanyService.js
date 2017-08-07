/**
 * 
 */

angular.module('MetronicApp').service('companyService',['$http','$q',function($http,$q) {
	
	/**
	 * 保存
	 */
	this.saveCompany = function (company){
		var deferred = $q.defer();
		$http.get("rest/company/saveCompany", {  
    		params:company//传整个表单数据  
    	}).then(function success(result) {
            deferred.resolve(result);//请求成功
        }, function error(err) {
            deferred.reject(err);//请求失败
        });
        return deferred.promise;//返回承诺
	}
	
	/**
	 * 编辑
	 */
	this.editCompany = function(comId){
		var deferred = $q.defer();
		$http.get("rest/company/viewCompany", {  
    		params:{comId:comId,cache:false}//传整个表单数据  
    	}).then(function success(result) {
            deferred.resolve(result);//请求成功
        }, function error(err) {
            deferred.reject(err);//请求失败
        });
        return deferred.promise;//返回承诺
    	
	}
	
	/**
	 * 删除
	 */
	this.deleteCompany = function(comId){
		var deferred = $q.defer();
		 $http.get("rest/company/deleteCompany", {  
	        	params:{comId:comId,cache:false}//传整个表单数据  
	        }).then(function success(result) {
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
		var company = {};
		debugger;
		company.pageSize = pageSize;
		company.pageIndex = pageIndex;
		if(params!=undefined){
			company.searchKey = params.searchKey; 
		}
		$http.get("rest/company/companyList", {  
			params:company
    	}).then(function success(result) {
            deferred.resolve(result);//请求成功
        }, function error(err) {
            deferred.reject(err);//请求失败
        });
		return deferred.promise;//返回承诺
	}
	
	
	/**
	 * 保存企业资质
	 */
	this.saveCompanyQualification = function (companyQualifications){
		var deferred = $q.defer();
		var params = {};  
	    params = JSON.stringify(companyQualifications); 
		$http.post("rest/company/saveCompanyQualification",  
    		params//传整个表单数据  
    	).then(function success(result) {
            deferred.resolve(result);//请求成功
        }, function error(err) {
            deferred.reject(err);//请求失败
        });
        return deferred.promise;//返回承诺
	}
	
	/**
	 * 编辑企业资质
	 */
	this.editCompanyQualification = function(serialNum){
		var deferred = $q.defer();
		$http.get("rest/company/viewCompanyQualification", {  
    		params:{serialNum:serialNum,cache:false}//传整个表单数据  
    	}).then(function success(result) {
            deferred.resolve(result);//请求成功
        }, function error(err) {
            deferred.reject(err);//请求失败
        });
        return deferred.promise;//返回承诺
    	
	}
	
	/**
	 * 删除企业资质
	 */
	this.deleteCompanyQualification = function(serialNum){
		var deferred = $q.defer();
		 $http.get("rest/company/deleteCompanyQualification", {  
	        	params:{serialNum:serialNum,cache:false}//传整个表单数据  
	        }).then(function success(result) {
                deferred.resolve(result);//请求成功
            }, function error(err) {
                deferred.reject(err);//请求失败
            });
            return deferred.promise;//返回承诺
	}


}]); 
