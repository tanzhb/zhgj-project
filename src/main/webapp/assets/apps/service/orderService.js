angular.module('MetronicApp').service('orderService',
		[ '$http', '$q', function($http, $q) {
			return {
				//保存销售订单
				save : function(orderInfo) {
					var deferred = $q.defer();
					$http.post("rest/order/saveOrder", orderInfo
					).success(function (data) {
		                // 如果连接成功，延时返回给调用者
		                deferred.resolve(data);
		            }).error(function () {
	                    deferred.reject('连接服务器出错！');
	                })
					return deferred.promise;
				},//删除订单
			    delOrder : function(ids){
			        var deferred = $q.defer();  
	
			        $http.post("rest/order/deleteOrders", ids)
			        .success(function (data) {  
			            // 如果连接成功，延时返回给调用者  
			            deferred.resolve(data);  
			        })  
			            .error(function () {  
			                deferred.reject('连接服务器出错！');  
			            })  
			        return deferred.promise;  
			    },//获取订单信息
			    getOrderInfo : function(serialNum){
			        var deferred = $q.defer();  

			        $http.get("rest/order/getOrderInfo", {params:{serialNum:serialNum}})
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
				},//订单保存合同
				saveContract : function (contract){
					var deferred = $q.defer();
					$http.post("rest/order/saveContract", 
							contract//传整个表单数据  
					).then(function success(result) {
						deferred.resolve(result);//请求成功
					}, function error(err) {
						deferred.reject(err);//请求失败
					});
					return deferred.promise;//返回承诺
				},//保存售后条款
				saveClauseAfterSales : function (clauseAfterSales){
					var deferred = $q.defer();
					$http.post("rest/order/saveClauseAfterSales", 
							clauseAfterSales//传整个表单数据  
					).then(function success(result) {
						deferred.resolve(result);//请求成功
					}, function error(err) {
						deferred.reject(err);//请求失败
					});
					return deferred.promise;//返回承诺
				},//保存交付条款
				saveClauseDelivery : function (clauseDelivery){
					var deferred = $q.defer();
					$http.post("rest/order/saveClauseDelivery", 
							clauseDelivery//传整个表单数据  
					).then(function success(result) {
						deferred.resolve(result);//请求成功
					}, function error(err) {
						deferred.reject(err);//请求失败
					});
					return deferred.promise;//返回承诺
				},//保存验收条款
				saveClauseCheckAccept : function (clauseCheckAccept){
					var deferred = $q.defer();
					$http.post("rest/order/saveClauseCheckAccept", 
							clauseCheckAccept//传整个表单数据  
					).then(function success(result) {
						deferred.resolve(result);//请求成功
					}, function error(err) {
						deferred.reject(err);//请求失败
					});
					return deferred.promise;//返回承诺
				},//保存垫资条款
				saveClauseAdvance : function (clauseAdvance){
					var deferred = $q.defer();
					$http.post("rest/order/saveClauseAdvance", 
							clauseAdvance//传整个表单数据  
					).then(function success(result) {
						deferred.resolve(result);//请求成功
					}, function error(err) {
						deferred.reject(err);//请求失败
					});
					return deferred.promise;//返回承诺
				},//保存结算条款
				saveClauseSettlement : function (clauseSettlement){
					var deferred = $q.defer();
					$http.post("rest/order/saveClauseSettlement", 
							clauseSettlement//传整个表单数据  
					).then(function success(result) {
						deferred.resolve(result);//请求成功
					}, function error(err) {
						deferred.reject(err);//请求失败
					});
					return deferred.promise;//返回承诺
				},//保存结算条款明细
				saveClauseSettlementDetail : function (clauseSettlementDetail){
					var deferred = $q.defer();
					var params = {};
					params = JSON.stringify(clauseSettlementDetail);
					$http.post("rest/order/saveClauseSettlementDetail", 
							params//传整个表单数据  
					).then(function success(result) {
						deferred.resolve(result);//请求成功
					}, function error(err) {
						deferred.reject(err);//请求失败
					});
					return deferred.promise;//返回承诺
				},//保存附件
				saveFile : function(File) {
					var deferred = $q.defer();
					var params = {};
					params = JSON.stringify(File);
					$http.post("rest/order/saveFile", params
					).success(function (data) {
		                // 如果连接成功，延时返回给调用者
		                deferred.resolve(data);
		            }).error(function () {
	                    deferred.reject('连接服务器出错！');
	                })
					return deferred.promise;
				},//保存框架条款
				saveClauseFramework : function(ClauseFramework) {
					var deferred = $q.defer();
					var params = {};
					params = JSON.stringify(ClauseFramework);
					$http.post("rest/order/saveClauseFramework", params
					).success(function (data) {
		                // 如果连接成功，延时返回给调用者
		                deferred.resolve(data);
		            }).error(function () {
	                    deferred.reject('连接服务器出错！');
	                })
					return deferred.promise;
				},//订单导入
				uploadExcel : function(params){
					var deferred = $q.defer();
					var fd = new FormData();
			        var file = document.querySelector('input[type=file]').files[0];
			        fd.append('excelFile', file);
					$http.post("rest/order/orderImport",  
							fd,{headers:{'Content-Type': undefined},transformRequest: angular.identity}
					).then(function success(result) {
						deferred.resolve(result);//请求成功
					}, function error(err) {
						deferred.reject(err);//请求失败
					});
					return deferred.promise;//返回承诺
				}
			}
		} ]);
