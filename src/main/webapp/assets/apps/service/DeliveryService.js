'use strict';

angular.module('MetronicApp').factory('DeliveryService', ['$rootScope', '$http', '$q','$filter', function($rootScope, $http, $q,$filter){
    var REST_SERVICE_URI = $rootScope.basePath + '/rest/contract/findAllUserContract';

    var factory = {
    		//查询列表
    		fetchAllUserContract: fetchAllUserContract,
    		//添加
    		getSaleOrderInfo: getSaleOrderInfo,
    		//删除
    		delUserContract:delUserContract,
    		//单个查找
    		getWarehouseList:getWarehouseList,
    		
    		downLoad:downLoad,
    		
    		uploadExcel:uploadExcel,
    		
    		saveDeliveryMateriel:saveDeliveryMateriel,
    		
    		selectAddress:selectAddress,
    		
    		saveBasicInfo:saveBasicInfo,
    		
    		getDeliveryInfo:getDeliveryInfo
    };

    return factory;

    function fetchAllUserContract() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI)
            .then(
            function (response) {
            	/*for(var i=0;i<response.data.data.length;i++){
            		var myJsDate=$filter('date')(response.data.data[i].startDate,'MM/dd/yyyy');
                    response.data.data[i].startDate=myJsDate;	
            	}*/
            	deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching Users');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
  //保存订单物料
    function saveDeliveryMateriel (deliveryMateriel){
		var deferred = $q.defer();
		$.post("rest/delivery/saveDeliveryMateriel", {
			batchNum:deliveryMateriel.batchNum,
			manufactureDate:deliveryMateriel.manufactureDate,
			deliverCount:deliveryMateriel.deliverCount,
			deliverRemark:deliveryMateriel.deliverRemark,
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
    
  //删除用户
    function delUserContract(ids){
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
    
  //通过用户id查找用户
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
    
    
    function getDeliveryInfo(serialNum){
    	var deferred = $q.defer();  

        $http.get("rest/delivery/getDeliveryInfo", {params:{serialNum:serialNum}})
        .success(function (data) {  
            // 如果连接成功，延时返回给调用者  
            deferred.resolve(data);  
        }).error(function () {  
            deferred.reject('连接服务器出错！');  
        })  
        return deferred.promise; 
    }
    
}]);

