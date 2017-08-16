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
			          
			    }
			}
		} ]);
