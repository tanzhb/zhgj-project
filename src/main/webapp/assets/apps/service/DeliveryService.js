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
    		
    		//编辑订单物料
    		editDeliveryMateriel:editDeliveryMateriel,
    		
    		//查询仓库地址
    		selectAddress:selectAddress,
    		
    		//保存基本信息
    		saveBasicInfo:saveBasicInfo,
    		
    		//编辑基本信息
    		editBasicInfo:editBasicInfo,
    		
    		//查询发货详情
    		getDeliveryInfo:getDeliveryInfo,
    		
    		//确认发货
    		goDelivery:goDelivery,
    		
    		//删除发货信息
    		deleteDeliveryS:deleteDeliveryS,
    		
    };

    return factory;

    
  //保存订单物料
    function saveDeliveryMateriel (deliveryMateriel){
		var deferred = $q.defer();
		$.post("rest/delivery/saveDeliveryMateriel", {
			batchNum:deliveryMateriel.batchNum,
			manufactureDate:deliveryMateriel.manufactureDate,
			deliverCount:deliveryMateriel.deliverCount,
			remark:deliveryMateriel.remark,
			deliverSerial:deliveryMateriel.deliverSerial,
			orderMaterielSerial:deliveryMateriel.orderMaterielSerialNum//传整个表单数据  
		}
		).then(function success(result) {
			deferred.resolve(result);//请求成功
		}, function error(err) {
			deferred.reject(err);//请求失败
		});
		return deferred.promise;//返回承诺
	}
    
    
    //编辑订单物料
    function editDeliveryMateriel (deliveryMateriel){
		var deferred = $q.defer();
		$.post("rest/delivery/editDeliveryMateriel", {
			serialNum:deliveryMateriel.serialNum,
			batchNum:deliveryMateriel.batchNum,
			deliverSerial:deliveryMateriel.deliverSerial,
			manufactureDate:deliveryMateriel.manufactureDate,
			deliverCount:deliveryMateriel.deliverCount,
			remark:deliveryMateriel.remark,
			orderMaterielSerial:deliveryMateriel.orderMaterielSerialNum//传整个表单数据  
		}
		).then(function success(result) {
			deferred.resolve(result);//请求成功
		}, function error(err) {
			deferred.reject(err);//请求失败
		});
		return deferred.promise;//返回承诺
	}
    
    //保存基本信息
    function saveBasicInfo($scope){
    	debugger
    	var deferred = $q.defer();
		$.post("rest/delivery/saveBasicInfo", {
			orderSerial:$scope.orderSerial,
			warehouseSerial:$scope.delivery.warehouseSerial,
			deliverNum:$scope.delivery.deliverNum,
			supplyComId:$scope.delivery.supplyComId,
			shipper:$scope.delivery.shipper,
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
			
			transportType:$scope.deliveryTransport.transportType,
			transport:$scope.deliveryTransport.transport,
			port:$scope.deliveryTransport.port,
			shipNumber:$scope.deliveryTransport.shipNumber,
			playArrivalDate:$scope.deliveryTransport.playArrivalDate,
			playWarehouseDate:$scope.deliveryTransport.playWarehouseDate,
			transportRemark:$scope.deliveryTransport.remark,
			transportContact:$scope.deliveryTransport.contact,
			transportContactNum:$scope.deliveryTransport.contactNum,
			
			takeDeliveryWarehouseSerial:$scope.takeDelivery.warehouseSerial,
			takeDeliverDate:$scope.takeDelivery.takeDeliverDate,
			takeDeliveryReceiver:$scope.takeDelivery.receiver,
			takeDeliveryContactNum:$scope.takeDelivery.contactNum,
			takeDeliveryRemark:$scope.takeDelivery.remark
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
    	debugger
    	var deferred = $q.defer();
		$.post("rest/delivery/editBasicInfo", {
			serialNum:$scope.delivery.serialNum,
			orderSerial:$scope.delivery.orderSerial,
			warehouseSerial:$scope.delivery.warehouseSerial,
			deliverNum:$scope.delivery.deliverNum,
			supplyComId:$scope.delivery.supplyComId,
			shipper:$scope.delivery.shipper,
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
			takeDeliveryRemark:$scope.delivery.takeDeliveryRemark
		}
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
    function getWarehouseList(){
        var deferred = $q.defer();  
        $.get("rest/delivery/getWarehouseList").success(function (data) { 
            // 如果连接成功，延时返回给调用者  
            deferred.resolve(data);
            
        })  
            .error(function () {  
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
    
}]);

