/**
 * 
 */

angular.module('MetronicApp').service('takeDeliveryService',['$http','$q',function($http,$q) {
	
	this.getTakeDeliveryInfo = function(serialNum){
		var deferred = $q.defer();
		$http.get("rest/takeDelivery/getTakeDeliveryInfo", {  
			params:{serialNum:serialNum}//传整个表单数据  
		})
		.then(function success(result) {
            deferred.resolve(result);//请求成功
        }, function error(err) {
            deferred.reject(err);//请求失败
        });
        return deferred.promise;//返回承诺
	}
	
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
	 * 初始化客户
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
	 * 保存收货计划
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
	 * 保存收货计划
	 */
	this.applyTakeDelivery = function (params){
		var deferred = $q.defer();
		$http.post("rest/takeDelivery/applyTakeDelivery", 
				JSON.stringify(params)//传整个表单数据  
		).then(function success(result) {
			deferred.resolve(result);//请求成功
		}, function error(err) {
			deferred.reject(err);//请求失败
		});
		return deferred.promise;//返回承诺
	}
	
	/**
	 * 收货确认
	 */
	this.saveConfirmTakeDelivery = function (params){
		var deferred = $q.defer();
		$http.post("rest/takeDelivery/confirmTakeDelivery", 
				JSON.stringify(params)//传整个表单数据  
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
	
	this.getTakeDeliveryMaterielList = function(deliverySerial){
		var deferred = $q.defer();
		$http.get("rest/takeDelivery/getTakeDeliveryMaterielList",{ 
			params:{serialNum:deliverySerial}	//传整个表单数据  
		}).then(function success(result) {
			deferred.resolve(result);//请求成功
		}, function error(err) {
			deferred.reject(err);//请求失败
		});
		return deferred.promise;//返回承诺
	}
	
	this.getTakeDeliveryMaterielListForStockIn = function(deliverySerial){
		var deferred = $q.defer();
		$http.get("rest/takeDelivery/getTakeDeliveryMaterielListForStockIn",{ 
			params:{serialNum:deliverySerial}	//传整个表单数据  
		}).then(function success(result) {
			deferred.resolve(result);//请求成功
		}, function error(err) {
			deferred.reject(err);//请求失败
		});
		return deferred.promise;//返回承诺
	}

	this.getPositions = function(warehouseSerial){
		var deferred = $q.defer();
		$http.get("rest/takeDelivery/getPositions",{ 
			params:{warehouseSerial:warehouseSerial}	//传整个表单数据  
		}).then(function success(result) {
			deferred.resolve(result);//请求成功
		}, function error(err) {
			deferred.reject(err);//请求失败
		});
		return deferred.promise;//返回承诺
	}
	
	
	/**
	 * 保存入库信息
	 */
	this.saveStockInData = function (params){
		var deferred = $q.defer();
		$http.post("rest/takeDelivery/saveStockInData", 
				JSON.stringify(params)//传整个表单数据  
		).then(function success(result) {
			deferred.resolve(result);//请求成功
		}, function error(err) {
			deferred.reject(err);//请求失败
		});
		return deferred.promise;//返回承诺
	}
	
	/**
	 * 保存出库信息
	 */
	this.saveStockOutData = function (params){
		var deferred = $q.defer();
		$http.post("rest/takeDelivery/saveStockOutData", 
				JSON.stringify(params)//传整个表单数据  
		).then(function success(result) {
			deferred.resolve(result);//请求成功
		}, function error(err) {
			deferred.reject(err);//请求失败
		});
		return deferred.promise;//返回承诺
	}
	
	/**
	 * 查看入库信息
	 */
	this.getStockInInfo = function (serialNum){
		var deferred = $q.defer();
		$http.get("rest/takeDelivery/getStockInInfo",{ 
				params:{serialNum:serialNum}//传整个表单数据  
		}).then(function success(result) {
			deferred.resolve(result);//请求成功
		}, function error(err) {
			deferred.reject(err);//请求失败
		});
		return deferred.promise;//返回承诺
	}
	
	/**
	 * 查看入库信息
	 */
	this.getStockOutInfo = function (serialNum){
		var deferred = $q.defer();
		$http.get("rest/takeDelivery/getStockOutInfo",{ 
			params:{serialNum:serialNum}//传整个表单数据  
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
	this.deleteStockInInfo = function(serialNums){
		var deferred = $q.defer();
		$http.post("rest/takeDelivery/deleteStockInInfo",  
		   serialNums//传整个表单数据  
		).then(function success(result) {
			deferred.resolve(result);//请求成功
		}, function error(err) {
			deferred.reject(err);//请求失败
		});
		return deferred.promise;//返回承诺
	}
	
	this.getAuditInfos = function(ids) {
        var deferred = $q.defer();  
        $http.post(ctx + "rest/takeDelivery/toApproval/" + ids).success(function (data) {  
        	
        	// 如果连接成功，延时返回给调用者  
            deferred.resolve(data);  
        })  
            .error(function () {  
                deferred.reject('连接服务器出错！');  
            })  
        return deferred.promise;  
    }

	


}]); 
