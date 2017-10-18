/**
 * 
 */

angular.module('MetronicApp').service('demandPlanService',['$http','$q',function($http,$q) {
	
	/**
	 * 初始化客户
	 */
	this.initCustomers = function (comId){
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
	this.saveDemandPlan = function (demandPlan){
		var deferred = $q.defer();
		$http.post("rest/demandPlan/saveDemandPlan", 
			demandPlan//传整个表单数据  
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
	this.deleteDemandPlan = function(serialNums){
		var deferred = $q.defer();
		$http.post("rest/demandPlan/deleteDemandPlan",  
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
	 * 搜索需求计划
	 */
	this.searchDemandPlan = function(search){
		var deferred = $q.defer();
		$http.post("rest/demandPlan/searchDemandPlan",   
				search
		).then(function success(result) {
			deferred.resolve(result);//请求成功
		}, function error(err) {
			deferred.reject(err);//请求失败
		});
		return deferred.promise;//返回承诺
	}
	


}]); 
