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
	 * 检查唯一性
	 */
	this.checkComNumIsExist = function (comId,comNum){
		var deferred = $q.defer();
		$http.get("rest/company/checkComNumIsExist", {  
			params:{comId:comId,comNum:comNum}//传整个表单数据  
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
		$http.post("rest/company/saveCompany", 
			company//传整个表单数据  
		).then(function success(result) {
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
		$http.post("rest/company/deleteCompanyBatch",  
			comIds//传整个表单数据  
		).then(function success(result) {
			deferred.resolve(result);//请求成功
		}, function error(err) {
			deferred.reject(err);//请求失败
		});
		return deferred.promise;//返回承诺
	}
	
	this.exportCompany = function(){
		$http({
			url:'rest/company/exportCompany',
			method:'GET'
			}).success(function(data,header,config,status){
			//响应成功

			}).error(function(data,header,config,status){
			//处理响应失败
			});
	}
	
	this.uploadExcel = function(params){
		var deferred = $q.defer();
		var fd = new FormData();
        var file = document.querySelector('input[type=file]').files[0];
        fd.append('excelFile', file);
		$http.post("rest/company/companyImport",  
				fd,{headers:{'Content-Type': undefined},transformRequest: angular.identity}
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
		$http.post("rest/company/saveCompanyContact", 
    		companyContact//传整个表单数据  
		).then(function success(result) {
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
	 * 保存联系地址
	 */
	this.saveCompanyAddress = function (companyAddress){
		var deferred = $q.defer();
		$http.post("rest/company/saveCompanyAddress", 
				companyAddress//传整个表单数据  
		).then(function success(result) {
			deferred.resolve(result);//请求成功
		}, function error(err) {
			deferred.reject(err);//请求失败
		});
		return deferred.promise;//返回承诺
	}
	
	
	/**
	 * 删除联系地址
	 */
	this.deleteCompanyAddress = function (serialNum){
		var deferred = $q.defer();
		$http.get("rest/company/deleteCompanyAddress",{  
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
		$http.post("rest/company/saveCompanyFinance", 
			companyFinance//传整个表单数据  
		).then(function success(result) {
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
	/**
	 * 保存管理信息
	 */
	this.saveCompanyManage = function (companyManage){
		var deferred = $q.defer();
		$http.post("rest/company/saveCompanyManage", 
				companyManage//传整个表单数据  
		).then(function success(result) {
			deferred.resolve(result);//请求成功
		}, function error(err) {
			deferred.reject(err);//请求失败
		});
		return deferred.promise;//返回承诺
	}
	/**
	 * 保存管理信息
	 */
	this.saveCompanyRelation = function (companyRelation,comId){
		var deferred = $q.defer();
	var params1 = {};
	params1 = JSON.stringify(companyRelation);//{params:{comId:'1',params:params1}}
	$http.post("rest/company/saveCompanyRelation",  params1
	).success(function (data) {
	    // 如果连接成功，延时返回给调用者
	    deferred.resolve(data);
	}).error(function () {
	    deferred.reject('连接服务器出错！');
	})
	return deferred.promise;
		}

}]); 
