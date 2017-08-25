/**
 * 
 */

angular.module('MetronicApp').service('takeDeliveryService',['$http','$q',function($http,$q) {
	
	
	this.initTakeDelviery = function(){
		var deferred = $q.defer();
		$http.get("rest/takeDelivery/initTakeDelviery")
		.then(function success(result) {
            deferred.resolve(result);//请求成功
        }, function error(err) {
            deferred.reject(err);//请求失败
        });
        return deferred.promise;//返回承诺
	}
	
	/**
	 * 初始化订单
	 */
	this.initOrders = function (comId){
		var deferred = $q.defer();
		$http.get("rest/takeDelivery/getOrders")
		.then(function success(result) {
            deferred.resolve(result);//请求成功
        }, function error(err) {
            deferred.reject(err);//请求失败
        });
        return deferred.promise;//返回承诺
	}
	
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
	 * 初始化仓库
	 */
	this.initWarehouse = function (comId){
		var deferred = $q.defer();
		$http.get("rest/takeDelivery/initWarehouse")
		.then(function success(result) {
			deferred.resolve(result);//请求成功
		}, function error(err) {
			deferred.reject(err);//请求失败
		});
		return deferred.promise;//返回承诺
	}
	
	
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
	
	
	this.chooseMateriels = function(ids){
		var deferred = $q.defer();
		$http.post("rest/demandPlan/chooseMateriel",
			ids//传整个表单数据  
    	).then(function success(result) {
            deferred.resolve(result);//请求成功
        }, function error(err) {
            deferred.reject(err);//请求失败
        });
        return deferred.promise;//返回承诺
	}
	
	/**
	 * 保存需求计划
	 */
	this.saveTakeDelivery = function (params){
		var deferred = $q.defer();
		$http.post("rest/takeDelivery/saveTakeDelivery", 
				JSON.stringify(params)//传整个表单数据  
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
	this.viewDemandPlan = function(serialNum){
		var deferred = $q.defer();
		$http.get("rest/demandPlan/viewDemandPlan", {  
    		params:{serialNum:serialNum}//传整个表单数据  
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
	this.demandPlanInfo = function(serialNum){
		var deferred = $q.defer();
		$http.post("rest/demandPlan/demandPlanInfo", 
			serialNum//传整个表单数据  
		).then(function success(result) {
			deferred.resolve(result);//请求成功
		}, function error(err) {
			deferred.reject(err);//请求失败
		});
		return deferred.promise;//返回承诺
		
	}

	
	/**
	 * 保存物料
	 */
	this.saveDemandPlanMateriel = function (materiel){
		var deferred = $q.defer();
		$http.post("rest/demandPlan/saveDemandPlanMateriel", 
				materiel//传整个表单数据  
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
	this.editDemandPlanMateriel = function(serialNum){
		var deferred = $q.defer();
		$http.get("rest/demandPlan/viewDemandPlanMateriel", {  
    		params:{serialNum:serialNum,cache:false}//传整个表单数据  
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
	this.deleteDemandPlanMateriel = function(serialNum){
		var deferred = $q.defer();
		 $http.post("rest/demandPlan/deleteDemandPlanMateriel", 
			 	serialNum//传整个表单数据  
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
	this.deleteTakeDelivery = function(serialNums){
		var deferred = $q.defer();
		$http.post("rest/takeDelivery/deleteTakeDelivery",  
		   serialNums//传整个表单数据  
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
		$http.post("rest/demandPlan/demandPlanImport",  
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
		var demandPlan = {};
		demandPlan.pageSize = pageSize;
		demandPlan.pageIndex = pageIndex;
		if(params!=undefined){
			demandPlan.searchKey = params.searchKey; 
		}
		$http.post("rest/demandPlan/demandPlanList",   
			demandPlan
    	).then(function success(result) {
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



}]); 
