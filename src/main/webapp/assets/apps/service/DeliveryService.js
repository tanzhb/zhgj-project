'use strict';

angular.module('MetronicApp').factory('DeliveryService', ['$rootScope', '$http', '$q','$filter', function($rootScope, $http, $q,$filter){

    var factory = {
    		//获取订单物料信息
    		getSaleOrderInfo: getSaleOrderInfo,
    		
    		//查询仓库集合
    		getWarehouseList:getWarehouseList,
    		
    		downLoad:downLoad,
    		
    		uploadExcel:uploadExcel,
    		
    		//保存订单物料
    		saveDeliveryMateriel:saveDeliveryMateriel,
    		
    		//保存全部订单物料
    		saveAllDeliveryMateriel:saveAllDeliveryMateriel,
    		
    		//编辑订单物料
    		editDeliveryMateriel:editDeliveryMateriel,
    		
    		//查询仓库地址
    		selectAddress:selectAddress,
    		
    		//保存基本信息
    		saveBasicInfo:saveBasicInfo,
    		
    		//编辑基本信息
    		editBasicInfo:editBasicInfo,
    		
    		//调整申请
    		apApplyAngain:apApplyAngain,
    		
    		//查询发货详情
    		getDeliveryInfo:getDeliveryInfo,
    		
    		//确认发货
    		goDelivery:goDelivery,
    		
    		//删除发货信息
    		deleteDeliveryS:deleteDeliveryS,
    		
    		deleteStockOutInfo:deleteStockOutInfo,
    		
    		//流程申请
    		applyAp:applyAp,
    		
    		//加载审批页面信息
    		getAuditInfos:getAuditInfos,
    		
    		
    		getSupplyComId:getSupplyComId,
    		
    		
    		batchGetMaterielInfo:batchGetMaterielInfo,
    		
    		
    		saveFile:saveFile,
    		
    		
    		getAttachFileInfo:getAttachFileInfo,
    		findStockOutSerialNum:findStockOutSerialNum,
    		goTakeDelivery:goTakeDelivery,//确认收货
    		//查询联系地址
    		getCompanyaddressList:getCompanyaddressList,
    		//获取进行中的发货单
    		getDoingDelivery:getDoingDelivery,
    		startDeliveryPlanProcess:startDeliveryPlanProcess
    };

    return factory;
    
    
  //获取附件
    function getAttachFileInfo(serialNum) {
    	
   		var deferred = $q.defer();
    		
   		/*$http.post("rest/delivery/getAttachFileInfo",data
   		).success(function (data) {
               // 如果连接成功，延时返回给调用者
               deferred.resolve(data);
           }).error(function () {
               deferred.reject('连接服务器出错！');
           })*/
           
        $http({    
            method: "POST",    
            url: "rest/delivery/getAttachFileInfo",    
            params: {  
            	serialNum: serialNum,  
            }  
        }).success(function (data) {
            // 如果连接成功，延时返回给调用者
            deferred.resolve(data);
        }).error(function () {
            deferred.reject('连接服务器出错！');
        })
   		
   		return deferred.promise;
   	};
   	
   	
    //保存附件
    function saveFile(File) {
   		var deferred = $q.defer();
   		var params = {};
   		params = JSON.stringify(File);
   		$http.post("rest/pay/saveFile", params
   		).success(function (data) {
               // 如果连接成功，延时返回给调用者
               deferred.resolve(data);
           }).error(function () {
               deferred.reject('连接服务器出错！');
           })
   		return deferred.promise;
   	};
    
    
    function batchGetMaterielInfo(ids){
    	
    	var deferred = $q.defer();  
        $http.post(ctx + "rest/delivery/batchGetMaterielInfo",ids).success(function (data) {  
        	
        	// 如果连接成功，延时返回给调用者  
            deferred.resolve(data);  
        })  
            .error(function () {  
                deferred.reject('连接服务器出错！');  
            })  
        return deferred.promise;
    }
    
    
    function getSupplyComId() {
        var deferred = $q.defer();  
        $http.post(ctx + "rest/delivery/getSupplyComId/").success(function (data) {  
        	// 如果连接成功，延时返回给调用者  
            deferred.resolve(data);  
        })  
            .error(function () {  
                deferred.reject('连接服务器出错！');  
            })  
        return deferred.promise;  
    }

    
    function applyAp(reason, serialNum) {
    	var contact = {};
    	contact.reason = reason; 
    	contact.serialNum = serialNum;
    	//var str = "{\"reason\":reason,\"serialNum\":serialNum}";
        var deferred = $q.defer();  
        $http.post(ctx + "/rest/delivery/apply",  JSON.stringify(contact)).success(function (data) {  
        	
        	// 如果连接成功，延时返回给调用者  
            deferred.resolve(data);  
        })  
            .error(function () {  
                deferred.reject('连接服务器出错！');  
            })  
        return deferred.promise;  
    }
    
    function getAuditInfos(ids) {
        var deferred = $q.defer();  
        $http.post(ctx + "rest/delivery/toApproval/" + ids).success(function (data) {  
        	
        	// 如果连接成功，延时返回给调用者  
            deferred.resolve(data);  
        })  
            .error(function () {  
                deferred.reject('连接服务器出错！');  
            })  
        return deferred.promise;  
    }
    
    
  //保存订单物料   saveAllDeliveryMateriel
    function saveDeliveryMateriel (deliveryMateriel){
    	
		var deferred = $q.defer();
		$.post("rest/delivery/saveDeliveryMateriel", {
			attachFile:deliveryMateriel.attachFile,
			manufactureDate:deliveryMateriel.manufactureDate,
			deliverCount:deliveryMateriel.deliverCount,
			remark:deliveryMateriel.remark,
			deliverSerial:deliveryMateriel.deliverSerial,
			orderMaterielSerial:deliveryMateriel.orderMaterielSerialNum,//传整个表单数据  
			supplyMaterielSerial:deliveryMateriel.supplyMaterielSerial,
		}
		).then(function success(result) {
			deferred.resolve(result);//请求成功
		}, function error(err) {
			deferred.reject(err);//请求失败
		});
		return deferred.promise;//返回承诺
	}
 function saveAllDeliveryMateriel (deliveryMateriel){
    	
		var deferred = $q.defer();
		var params = {};
		params = JSON.stringify(deliveryMateriel);
		$http.post("rest/delivery/saveAllDeliveryMateriel", 
				params//传整个表单数据  
		).then(function success(result) {
			deferred.resolve(result);//请求成功
		}, function error(err) {
			deferred.reject(err);//请求失败
		});
		return deferred.promise;//返回承诺
	}
    
    //编辑订单物料
    function editDeliveryMateriel (params){
		var deferred = $q.defer();
		$.post("rest/delivery/editDeliveryMateriel", 
		{params:JSON.stringify(params)}
		).then(function success(result) {
			deferred.resolve(result);//请求成功
		}, function error(err) {
			deferred.reject(err);//请求失败
		});
		return deferred.promise;//返回承诺
	}
    
    //保存基本信息
    function saveBasicInfo($scope,type){
    	var deferred = $q.defer();
    	debugger;
		$.post("rest/delivery/saveBasicInfo", {
			orderSerial:$scope.orderSerial==null?$scope.delivery.orderSerial:$scope.orderSerial,
			/*orderSerial:$scope.delivery.orderSerial,*/
			warehouseSerial:$scope.delivery.warehouseSerial,
			deliveryAddress:$scope.warehouseAddress,
			deliverNum:$scope.delivery.deliverNum,
			deliverType:$scope.delivery.deliverType,
			shipper:$scope.shipper,
			approvalDate:$scope.deliver.approvalDate,
			receiver:$scope.delivery.receiver,
			maker:$scope.deliver.maker,
			remark:$scope.deliver.remark,
			deliverDate:$scope.deliver.deliverDate,
			packageCount:$scope.delivery.packageCount,
			packageType:$scope.deliver.packageType,
			packageSpecifications:$scope.delivery.packageSpecifications,
			materielWeight:$scope.delivery.materielWeight,
			serviceMoney:$scope.delivery.serviceMoney,
			deliverer:$scope.deliver.deliverer,
			contactNum:$scope.delivery.contactNum,
			deliverRemark:$scope.delivery.deliverRemark,
			serialNum:$scope.delivery.serialNum==undefined?null:$scope.delivery.serialNum,
					status:$scope.delivery.status==undefined?null:$scope.delivery.status,
					type:type,
			
			transportType:$scope.transportType,
			transport:$scope.deliveryTransport.transport,
			port:$scope.deliveryTransport.port,
			shipNumber:$scope.deliveryTransport.shipNumber,
			transportRemark:$scope.deliveryTransport.remark,
			transportContact:$scope.deliveryTransport.contact,
			transportContactNum:$scope.deliveryTransport.contactNum,
			deliverSerial:$scope.delivery.serialNum,
			deliveryTransportSerialNum:$scope.delivery.transportserialNum==undefined?null:$scope.delivery.transportserialNum,
		
			takeDeliveryWarehouseSerial:$scope.takeDelivery.warehouseSerial,
			takeAddress:$scope.takeDeliveryWarehouseAddress,
			takeDeliverDate:$scope.takeDelivery.takeDeliverDate,
			takeDeliveryReceiver:$scope.takeDelivery.receiver,
			takeDeliveryContactNum:$scope.takeDelivery.contactNum,
			takeDeliveryRemark:$scope.takeDelivery.remark,
			takeDeliveryVOSerialNum:$scope.delivery.takeDeliverSerialNum==undefined?null:$scope.delivery.takeDeliverSerialNum
		}
		).then(function success(result) {
			deferred.resolve(result);//请求成功
		}, function error(err) {
			deferred.reject(err);//请求失败
		});
		return deferred.promise;//返回承诺
    }
    
    
    //编辑基本信息
    function editBasicInfo($scope){
    	var deferred = $q.defer();
		$.post("rest/delivery/editBasicInfo", {
			serialNum:$scope.delivery.serialNum,
			orderSerial:$scope.delivery.orderSerial,
			warehouseSerial:$scope.delivery.warehouseSerial,
			deliveryAddress:$scope.delivery.deliveryAddress,
			deliverNum:$scope.delivery.deliverNum,
			shipper:$scope.delivery.shipper,
			receiver:$scope.delivery.receiver,
			maker:$scope.delivery.maker,
			approval:$scope.delivery.approval,
			approvalDate:$scope.delivery.approvalDate,
			remark:$scope.delivery.remark,
			deliverDate:$scope.delivery.deliverDate,
			packageCount:$scope.delivery.packageCount,
			packageType:$scope.delivery.packageType,
			packageSpecifications:$scope.delivery.packageSpecifications,
			materielWeight:$scope.delivery.materielWeight,
			serviceMoney:$scope.delivery.serviceMoney,
			deliverer:$scope.delivery.deliverer,
			contactNum:$scope.delivery.contactNum,
			deliverRemark:$scope.delivery.deliverRemark,
			
			transportserialNum:$scope.delivery.transportserialNum,
			transportType:$scope.transportType,
			transport:$scope.delivery.transport,
			port:$scope.delivery.port,
			shipNumber:$scope.delivery.shipNumber,
			playArrivalDate:$scope.delivery.playArrivalDate,
			playWarehouseDate:$scope.delivery.playWarehouseDate,
			transportRemark:$scope.delivery.transportRemark,
			transportContact:$scope.delivery.transportContact,
			transportContactNum:$scope.delivery.contactNum,
			
			takeDeliverSerialNum:$scope.delivery.takeDeliverSerialNum,
			takeDeliveryWarehouseSerial:$scope.delivery.takeWarehouseSerial,
			takeAddress:$scope.delivery.takeAddress,
			takeDeliverDate:$scope.delivery.takeDeliverDate,
			takeDeliveryReceiver:$scope.delivery.takeDeliveryReceiver,
			takeDeliveryContactNum:$scope.delivery.takeDeliveryContactNum,
			takeDeliveryRemark:$scope.delivery.takeDeliveryRemark,
			reason:$scope.delivery.reason,
			processInstanceId:$scope.delivery.processInstanceId,
			userId:$scope.delivery.userId
		}
		).then(function success(result) {
			deferred.resolve(result);//请求成功
		}, function error(err) {
			deferred.reject(err);//请求失败
		});
		return deferred.promise;//返回承诺
    }
    
    
    
    function apApplyAngain($scope){
    	
    	var deferred = $q.defer();
		$.post("rest/delivery/modifyApplyAp", {
			serialNum:$scope.delivery.serialNum,
			orderSerial:$scope.delivery.orderSerial,
			warehouseSerial:$scope.delivery.warehouseSerial,
			deliverNum:$scope.delivery.deliverNum,
			receiver:$scope.delivery.receiver,
			maker:$scope.delivery.maker,
			makeDate:$scope.delivery.makeDate,
			approval:$scope.delivery.approval,
			approvalDate:$scope.delivery.approvalDate,
			remark:$scope.delivery.remark,
			deliverDate:$scope.delivery.deliverDate,
			materielCount:$scope.delivery.materielCount,
			packageCount:$scope.delivery.packageCount,
			packageType:$scope.delivery.packageType,
			packageSpecifications:$scope.delivery.packageSpecifications,
			materielWeight:$scope.delivery.materielWeight,
			serviceMoney:$scope.delivery.serviceMoney,
			deliverer:$scope.delivery.deliverer,
			contactNum:$scope.delivery.contactNum,
			deliverRemark:$scope.delivery.deliverRemark,
			
			transportserialNum:$scope.delivery.transportserialNum,
			transportType:$scope.delivery.transportType,
			transport:$scope.delivery.transport,
			port:$scope.delivery.port,
			shipNumber:$scope.delivery.shipNumber,
			playArrivalDate:$scope.delivery.playArrivalDate,
			playWarehouseDate:$scope.delivery.playWarehouseDate,
			transportRemark:$scope.delivery.transportRemark,
			transportContact:$scope.delivery.transportContact,
			transportContactNum:$scope.delivery.contactNum,
			
			takeDeliverSerialNum:$scope.delivery.takeDeliverSerialNum,
			takeDeliveryWarehouseSerial:$scope.delivery.takeWarehouseSerial,
			takeDeliverDate:$scope.delivery.takeDeliverDate,
			takeDeliveryReceiver:$scope.delivery.takeDeliveryReceiver,
			takeDeliveryContactNum:$scope.delivery.takeDeliveryContactNum,
			takeDeliveryRemark:$scope.delivery.takeDeliveryRemark,
			reason:$scope.delivery.reason,
			taskId:$("#taskId").val(), 
			reApply:true
		}
		/*params:{'taskId':$("#taskId").val(), 'reApply':true},*/
		).then(function success(result) {
			deferred.resolve(result);//请求成功
		}, function error(err) {
			deferred.reject(err);//请求失败
		});
		return deferred.promise;//返回承诺
    }
    
    
    //查询仓库地址
    function selectAddress (warehouseSerial){
    	var deferred = $q.defer();
		$.post("rest/delivery/selectAddress", {
			warehouseSerial:warehouseSerial,
		}
		).then(function success(result) {
			deferred.resolve(result);//请求成功
		}, function error(err) {
			deferred.reject(err);//请求失败
		});
		return deferred.promise;//返回承诺
    }
    
	
    function downLoad(name){
    	$http.get($rootScope.basePath + "/rest/contract/resourceDownload",  {filename:name}).success(function (data) {  
        })
    }
    
    /**
     * 导入
     */
    function uploadExcel(){
		var deferred = $q.defer();
		var fd = new FormData();
        var file = document.querySelector('input[type=file]').files[0];
        fd.append('excelFile', file);
		$http.post("rest/contract/contractImport",  
				fd,{headers:{'Content-Type': undefined},transformRequest: angular.identity}
		).then(function success(result) {
			deferred.resolve(result);//请求成功
		}, function error(err) {
			deferred.reject(err);//请求失败
		});
		return deferred.promise;//返回承诺
	}
    
  //删除发货信息
    function deleteDeliveryS(ids){
        var deferred = $q.defer();  

        $http.post($rootScope.basePath + "/rest/delivery/deleteDeliveryS", ids).success(function (data) {  
            // 如果连接成功，延时返回给调用者  
            deferred.resolve(data);  
        })  
            .error(function () {  
                deferred.reject('连接服务器出错！');  
            })  
        return deferred.promise;  
          
    };
    
  //通过仓库集合
    function getWarehouseList(judgeString,comId){
        var deferred = $q.defer();  
      /*  $.post("rest/delivery/getWarehouseList",judgeString).success(function (data) { 
            // 如果连接成功，延时返回给调用者  
            deferred.resolve(data);
            
        })  
            .error(function () {  
                deferred.reject('连接服务器出错！');  
            })  */
        $http({    
            method: "POST",    
            url: "rest/delivery/getWarehouseList",    
            params: {  
            	judgeString: judgeString,  
            	comId:comId,
            }  
        }).success(function (data) {
            // 如果连接成功，延时返回给调用者
            deferred.resolve(data);
        }).error(function () {
            deferred.reject('连接服务器出错！');
        })
   		
        return deferred.promise;  
          
    };
    
    
    //查询订单信息
    function getSaleOrderInfo(serialNum){
        var deferred = $q.defer();  

        $http.get("rest/delivery/getSaleOrderInfo", {params:{serialNum:serialNum}})
        .success(function (data) {  
            // 如果连接成功，延时返回给调用者  
            deferred.resolve(data);  
        })  
            .error(function () {  
                deferred.reject('连接服务器出错！');  
            })  
        return deferred.promise;  
          
    };
    
    
    //查询发货详情
    function getDeliveryInfo(serialNumEdit){
    	var deferred = $q.defer();  

        $http.get("rest/delivery/getDeliveryInfo", {params:{serialNum:serialNumEdit}})
        .success(function (data) {  
            // 如果连接成功，延时返回给调用者  
            deferred.resolve(data);  
        }).error(function () {  
            deferred.reject('连接服务器出错！');  
        })  
        return deferred.promise; 
    }
    
    
    //确认发货
    function goDelivery(serialNum){
    	var deferred = $q.defer();  

        $http.get("rest/delivery/goDelivery", {params:{serialNum:serialNum}})
        .success(function (data) {  
            // 如果连接成功，延时返回给调用者  
            deferred.resolve(data);  
        }).error(function () {  
            deferred.reject('连接服务器出错！');  
        })  
        return deferred.promise; 
    }
    
    /**
	 * 批量删除出库信息
	 */
	function deleteStockOutInfo(serialNums){
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
    

	/**
	 * 查找发货对应的出库记录
	 */
	function findStockOutSerialNum(serialNum){
		var deferred = $q.defer();
		$http.post("rest/takeDelivery/findStockOutSerialNum",  
		   serialNum//传发货流水号
		).then(function success(result) {
			deferred.resolve(result);//请求成功
		}, function error(err) {
			deferred.reject(err);//请求失败
		});
		return deferred.promise;//返回承诺
	}
    //确认收货
    function goTakeDelivery(serialNum){
    	var deferred = $q.defer();  

        $http.get("rest/delivery/goTakeDelivery", {params:{serialNum:serialNum}})
        .success(function (data) {  
            // 如果连接成功，延时返回给调用者  
            deferred.resolve(data);  
        }).error(function () {  
            deferred.reject('连接服务器出错！');  
        })  
        return deferred.promise; 
    }
  //通过企业id找联系地址集合
    function getCompanyaddressList(comId){
        var deferred = $q.defer();  
       /* $http.post("rest/delivery/getCompanyaddress",  
        		comId//传企业comId
     		).then(function success(result) {
     			deferred.resolve(result);//请求成功
     		}, function error(err) {
     			deferred.reject(err);//请求失败
     		});
     		return deferred.promise;//返回承诺
          */
        $http({    
            method: "POST",    
            url: "rest/delivery/getCompanyaddress",
            params: {  
            	comId:comId
            }  
        }).success(function (data) {
            // 如果连接成功，延时返回给调用者
            deferred.resolve(data);
        }).error(function () {
            deferred.reject('连接服务器出错！');
        })
   		
        return deferred.promise;  
    }
    
    
    /**
	 * 查找进行中发货单
	 */
    function getDoingDelivery(orderSerialNum){
		var deferred = $q.defer();
		$http.post("rest/delivery/getDoingDelivery",  
				orderSerialNum//传收货流水号
		).then(function success(result) {
			deferred.resolve(result);//请求成功
		}, function error(err) {
			deferred.reject(err);//请求失败
		});
		return deferred.promise;//返回承诺
	}
    
	//启动发货计划流程
    function startDeliveryPlanProcess(delivery) {
		var deferred = $q.defer();
		$http.post("rest/delivery/startDeliveryPlanProcess", delivery
		).success(function (data) {
            // 如果连接成功，延时返回给调用者
            deferred.resolve(data);
        }).error(function () {
            deferred.reject('连接服务器出错！');
        })
		return deferred.promise;
	}
}]);

