/**
 * 
 */

angular.module('MetronicApp').service('companyService',['$http','$q',function($http,$q) {
	
	/**
	 * 获取企业信息数据
	 */
	this.getCompanyInfo = function (comId){
		var deferred = $q.defer();
		$http.get("rest/company/getCompanyInfo", {  
			params:{comId:comId}//传整个表单数据  
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
	 * 批量删除
	 */
	this.deleteCompanyBatch = function(comIds){
		var deferred = $q.defer();
		$http.get("rest/company/deleteCompanyBatch", {  
			params:{comIds:comIds,cache:false}//传整个表单数据  
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
	
	/**
	 * 保存联系人
	 */
	this.saveCompanyContact = function (companyContact){
		var deferred = $q.defer();
		$http.get("rest/company/saveCompanyContact",{  
    		params:companyContact//传整个表单数据  
		}).then(function success(result) {
            deferred.resolve(result);//请求成功
        }, function error(err) {
            deferred.reject(err);//请求失败
        });
        return deferred.promise;//返回承诺
	}
	
	
	/**
	 * 删除联系人
	 */
	this.deleteCompanyContact = function (serialNum){
		var deferred = $q.defer();
		$http.get("rest/company/deleteCompanyContact",{  
			params:{serialNum:serialNum}//传整个表单数据  
		}).then(function success(result) {
			deferred.resolve(result);//请求成功
		}, function error(err) {
			deferred.reject(err);//请求失败
		});
		return deferred.promise;//返回承诺
	}
	
	/**
	 * 保存财务信息
	 */
	this.saveCompanyFinance = function (companyFinance){
		var deferred = $q.defer();
		$http.get("rest/company/saveCompanyFinance",{  
			params:companyFinance//传整个表单数据  
		}).then(function success(result) {
			deferred.resolve(result);//请求成功
		}, function error(err) {
			deferred.reject(err);//请求失败
		});
		return deferred.promise;//返回承诺
	}
	
	/**
	 * 删除财务信息
	 */
	this.deleteCompanyFinance = function (serialNum){
		var deferred = $q.defer();
		$http.get("rest/company/deleteCompanyFinance",{  
			params:{serialNum:serialNum}//传整个表单数据  
		}).then(function success(result) {
			deferred.resolve(result);//请求成功
		}, function error(err) {
			deferred.reject(err);//请求失败
		});
		return deferred.promise;//返回承诺
	}



}]); 
