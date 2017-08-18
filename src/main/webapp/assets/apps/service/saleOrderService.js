angular.module('MetronicApp').service('saleOrderService',
		[ '$http', '$q', function($http, $q) {
			return {
				//保存销售订单
				save : function(orderInfo) {
					var deferred = $q.defer();
					$http.post("rest/order/saveSaleOrder", orderInfo
					).success(function (data) {
		                // 如果连接成功，延时返回给调用者
		                deferred.resolve(data);
		            }).error(function () {
	                    deferred.reject('连接服务器出错！');
	                })
					return deferred.promise;
				},//删除订单
			    delSaleOrder : function(ids){
			        var deferred = $q.defer();  
	
			        $http.post("rest/order/deleteSaleOrders", ids)
			        .success(function (data) {  
			            // 如果连接成功，延时返回给调用者  
			            deferred.resolve(data);  
			        })  
			            .error(function () {  
			                deferred.reject('连接服务器出错！');  
			            })  
			        return deferred.promise;  
			    },//获取订单信息
			    getSaleOrderInfo : function(serialNum){
			        var deferred = $q.defer();  

			        $http.get("rest/order/getSaleOrderInfo", {params:{serialNum:serialNum}})
			        .success(function (data) {  
			            // 如果连接成功，延时返回给调用者  
			            deferred.resolve(data);  
			        })  
			            .error(function () {  
			                deferred.reject('连接服务器出错！');  
			            })  
			        return deferred.promise;  
			          
			    },//保存订单物料
			    saveOrderMateriel : function (orderMateriel){
					var deferred = $q.defer();
					$http.post("rest/order/saveOrderMateriel", 
							orderMateriel//传整个表单数据  
					).then(function success(result) {
						deferred.resolve(result);//请求成功
					}, function error(err) {
						deferred.reject(err);//请求失败
					});
					return deferred.promise;//返回承诺
				},//删除订单物料
				deleteOrderMateriel : function(serialNum){
					var deferred = $q.defer();
					 $http.post("rest/order/deleteOrderMateriel", 
						 	serialNum//传整个表单数据  
				        ).then(function success(result) {
			                deferred.resolve(result);//请求成功
			            }, function error(err) {
			                deferred.reject(err);//请求失败
			            });
			            return deferred.promise;//返回承诺
				}
			}
		} ]);
