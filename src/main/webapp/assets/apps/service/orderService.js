angular.module('MetronicApp').service('orderService',
		[ '$http', '$q', function($http, $q) {
			return {
				//保存订单
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
				},//客户端提交订单
				submitOrder : function(orderInfo) {
					var deferred = $q.defer();
					$http.post("rest/order/submitOrder", orderInfo
					).success(function (data) {
		                // 如果连接成功，延时返回给调用者
		                deferred.resolve(data);
		            }).error(function () {
	                    deferred.reject('连接服务器出错！');
	                })
					return deferred.promise;
				},//接受客户订单
				acceptSubmit : function(orderInfo) {
					var deferred = $q.defer();
					$http.post("rest/order/acceptSubmit", orderInfo
					).success(function (data) {
		                // 如果连接成功，延时返回给调用者
		                deferred.resolve(data);
		            }).error(function () {
	                    deferred.reject('连接服务器出错！');
	                })
					return deferred.promise;
				},//供应商确认订单
				supplyConfirmed : function(orderInfo) {
					var deferred = $q.defer();
					$http.post("rest/order/supplyConfirmed", orderInfo
					).success(function (data) {
		                // 如果连接成功，延时返回给调用者
		                deferred.resolve(data);
		            }).error(function () {
	                    deferred.reject('连接服务器出错！');
	                })
					return deferred.promise;
				},//供应商确认框架协议
				supplyConfirmedFrame : function(orderInfo) {
					var deferred = $q.defer();
					$http.post("rest/order/supplyConfirmedFrame", orderInfo
					).success(function (data) {
		                // 如果连接成功，延时返回给调用者
		                deferred.resolve(data);
		            }).error(function () {
	                    deferred.reject('连接服务器出错！');
	                })
					return deferred.promise;
				},
				//平台提交订单
				pingTaiSubmit : function(orderInfo) {
					var deferred = $q.defer();
					$http.post("rest/order/pingTaiSubmit", orderInfo
					).success(function (data) {
		                // 如果连接成功，延时返回给调用者
		                deferred.resolve(data);
		            }).error(function () {
	                    deferred.reject('连接服务器出错！');
	                })
					return deferred.promise;
				},//平台提交框架协议
				pingTaiSubmitFrame : function(buyFrame) {
					var deferred = $q.defer();
					$http.post("rest/order/pingTaiSubmitFrame", buyFrame
					).success(function (data) {
		                // 如果连接成功，延时返回给调用者
		                deferred.resolve(data);
		            }).error(function () {
	                    deferred.reject('连接服务器出错！');
	                })
					return deferred.promise;
				},
				//销售订单分解
				saleGenerateBuy : function(serialNum) {
					var deferred = $q.defer();
					$http.get("rest/order/saleGenerateBuy", {params:{serialNum:serialNum}}
					).success(function (data) {
		                // 如果连接成功，延时返回给调用者
		                deferred.resolve(data);
		            }).error(function () {
	                    deferred.reject('连接服务器出错！');
	                })
					return deferred.promise;
				},
				//复制订单
				copyOrder : function(serialNum) {
					var deferred = $q.defer();
					$http.get("rest/order/copyOrderInfo", {params:{serialNum:serialNum}}
					).success(function (data) {
		                // 如果连接成功，延时返回给调用者
		                deferred.resolve(data);
		            }).error(function () {
	                    deferred.reject('连接服务器出错！');
	                })
					return deferred.promise;
				},
				//检查编号
				checkNum : function(orderInfo) {
					var deferred = $q.defer();
					$http.post("rest/order/checkNum", orderInfo
					).success(function (data) {
		                // 如果连接成功，延时返回给调用者
		                deferred.resolve(data);
		            }).error(function () {
	                    deferred.reject('连接服务器出错！');
	                })
					return deferred.promise;
				},
				//供应商接收订单
				recive : function(orderInfo) {
					var deferred = $q.defer();
					$http.post("rest/order/reciveOrder", orderInfo
					).success(function (data) {
		                // 如果连接成功，延时返回给调用者
		                deferred.resolve(data);
		            }).error(function () {
	                    deferred.reject('连接服务器出错！');
	                })
					return deferred.promise;
				},
				//供应商接收框架协议
				reciveFrame : function(orderInfo) {
					var deferred = $q.defer();
					$http.post("rest/order/reciveFrame", orderInfo
					).success(function (data) {
		                // 如果连接成功，延时返回给调用者
		                deferred.resolve(data);
		            }).error(function () {
	                    deferred.reject('连接服务器出错！');
	                })
					return deferred.promise;
				},
				
				//删除订单
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
			    },
				//删除框架
			    delFrame : function(ids){
			        var deferred = $q.defer();  
	
			        $http.post("rest/contract/deleteUserContractS", ids)
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
			          
			    },//获取框架协议信息
			    getFrameInfo : function(serialNum){
			        var deferred = $q.defer();  
			        $http.get("rest/order/getFrameInfo", {params:{serialNum:serialNum}})
			        .success(function (data) {  
			            // 如果连接成功，延时返回给调用者  
			            deferred.resolve(data);  
			        })  
			            .error(function () {  
			                deferred.reject('连接服务器出错！');  
			            })  
			        return deferred.promise;  
			          
			    },//保存所有订单物料
			    saveAllOrderMateriel : function (orderMateriel){
					var deferred = $q.defer();
					var params = {};
					params = JSON.stringify(orderMateriel);
					$http.post("rest/order/saveAllOrderMateriel", 
							params//传整个表单数据  
					).then(function success(result) {
						deferred.resolve(result);//请求成功
					}, function error(err) {
						deferred.reject(err);//请求失败
					});
					return deferred.promise;//返回承诺
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
				},//保存框架协议
				saveFrame : function (contract){
					var deferred = $q.defer();
					$http.post("rest/order/saveFrame", 
							contract//传整个表单数据  
					).then(function success(result) {
						deferred.resolve(result);//请求成功
					}, function error(err) {
						deferred.reject(err);//请求失败
					});
					return deferred.promise;//返回承诺
				},
				//保存售后条款
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
				},//保存合同附件
				saveFrameFile : function(File) {
					var deferred = $q.defer();
					var params = {};
					params = JSON.stringify(File);
					$http.post("rest/order/saveFrameFile", params
					).success(function (data) {
		                // 如果连接成功，延时返回给调用者
		                deferred.resolve(data);
		            }).error(function () {
	                    deferred.reject('连接服务器出错！');
	                })
					return deferred.promise;
				},
				//保存框架条款
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
				},//销售订单导入
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
				},//采购订单导入
				buyUploadExcel : function(params){
					var deferred = $q.defer();
					var fd = new FormData();
			        var file = document.querySelector('input[type=file]').files[0];
			        fd.append('excelFile', file);
					$http.post("rest/order/buyOrderImport",  
							fd,{headers:{'Content-Type': undefined},transformRequest: angular.identity}
					).then(function success(result) {
						deferred.resolve(result);//请求成功
					}, function error(err) {
						deferred.reject(err);//请求失败
					});
					return deferred.promise;//返回承诺
				},//启动采购订单流程
				startBuyOrderProcess : function(orderInfo) {
					var deferred = $q.defer();
					$http.post("rest/order/startBuyOrderProcess", orderInfo
					).success(function (data) {
		                // 如果连接成功，延时返回给调用者
		                deferred.resolve(data);
		            }).error(function () {
	                    deferred.reject('连接服务器出错！');
	                })
					return deferred.promise;
				},//启动销售订单流程
				startSaleOrderProcess : function(orderInfo) {
					var deferred = $q.defer();
					$http.post("rest/order/startSaleOrderProcess", orderInfo
					).success(function (data) {
		                // 如果连接成功，延时返回给调用者
		                deferred.resolve(data);
		            }).error(function () {
	                    deferred.reject('连接服务器出错！');
	                })
					return deferred.promise;
				},//启动采购框架流程
				startBuyFrameProcess : function(buyFrame) {
					var deferred = $q.defer();
					$http.post("rest/order/startBuyFrameProcess", buyFrame
					).success(function (data) {
		                // 如果连接成功，延时返回给调用者
		                deferred.resolve(data);
		            }).error(function () {
	                    deferred.reject('连接服务器出错！');
	                })
					return deferred.promise;
				},//启动销售框架流程
				startSaleFrameProcess : function(saleFrame) {
					var deferred = $q.defer();
					$http.post("rest/order/startSaleFrameProcess", saleFrame
					).success(function (data) {
		                // 如果连接成功，延时返回给调用者
		                deferred.resolve(data);
		            }).error(function () {
	                    deferred.reject('连接服务器出错！');
	                })
					return deferred.promise;
				},//获取供应商列表
				  initSuppliers : function (){
					  var deferred = $q.defer();
						$http.get("rest/company/getSuppliers")
						.then(function success(result) {
							deferred.resolve(result);//请求成功
						}, function error(err) {
							deferred.reject(err);//请求失败
						});
						return deferred.promise;//返回承诺  
				  },//获取客户列表
				  initCustomers : function (){
					  var deferred = $q.defer();
						$http.get("rest/company/getCustomers")
						.then(function success(result) {
				            deferred.resolve(result);//请求成功
				        }, function error(err) {
				            deferred.reject(err);//请求失败
				        });
				        return deferred.promise;//返回承诺
				  },//初始化仓库
				  initWarehouse : function (){
						var deferred = $q.defer();
						$http.get("rest/takeDelivery/initWarehouse")
						.then(function success(result) {
							deferred.resolve(result);//请求成功
						}, function error(err) {
							deferred.reject(err);//请求失败
						});
						return deferred.promise;//返回承诺
					},
				  getAuditInfos : function (ids) {
				        var deferred = $q.defer();  
				        $http.post(ctx + "rest/order/toApproval/" + ids).success(function (data) {  
				        	// 如果连接成功，延时返回给调用者  
				            deferred.resolve(data);  
				        })  
				            .error(function () {  
				                deferred.reject('连接服务器出错！');  
				            })  
				        return deferred.promise;  
				    },//获取框架协议审批信息
				  getFrameAuditInfos : function (ids) {
				        var deferred = $q.defer();  
				        $http.post(ctx + "rest/order/toFrameApproval/" + ids).success(function (data) {  
				        	// 如果连接成功，延时返回给调用者  
				            deferred.resolve(data);  
				        })  
				            .error(function () {  
				                deferred.reject('连接服务器出错！');  
				            })  
				        return deferred.promise;  
				    },
				    
				    
				    getComId : function() {
				        var deferred = $q.defer();  
				        $http.post(ctx + "rest/delivery/getSupplyComId/").success(function (data) {  
				        	// 如果连接成功，延时返回给调用者  
				            deferred.resolve(data);  
				        })  
				            .error(function () {  
				                deferred.reject('连接服务器出错！');  
				            })  
				        return deferred.promise;  
				    },
				    initAllComs : function (){//所有公司
						  var deferred = $q.defer();
							$http.get("rest/company/getAllComs")
							.then(function success(result) {
								deferred.resolve(result);//请求成功
							}, function error(err) {
								deferred.reject(err);//请求失败
							});
							return deferred.promise;//返回承诺  
					  },
					  initPtWarehouseAddress : function (){
							var deferred = $q.defer();
							$http.get("rest/company/initPtWarehouseAddress")
							.then(function success(result) {
								deferred.resolve(result);//请求成功
							}, function error(err) {
								deferred.reject(err);//请求失败
							});
							return deferred.promise;//返回承诺
						},
						initComFinances : function (comId){
							  var deferred = $q.defer();
								$http.post("rest/company/getComFinances",comId)
								.then(function success(result) {
						            deferred.resolve(result);//请求成功
						        }, function error(err) {
						            deferred.reject(err);//请求失败
						        });
						        return deferred.promise;//返回承诺
						  },
						  //获取默认框架
						  findDefaultFrame : function(type,selectFor,comId){
						        var deferred = $q.defer();  
						        $http.get("rest/order/findDefaultFrame", {params:{type:type,selectFor:selectFor,comId:comId}})
						        .success(function (data) {  
						            // 如果连接成功，延时返回给调用者  
						            deferred.resolve(data);  
						        })  
						            .error(function () {  
						                deferred.reject('连接服务器出错！');  
						            })  
						        return deferred.promise;  
						          
						    },
							//用户取消订单申请
						    userCancelOrderApply : function(processInstanceId) {
								var deferred = $q.defer();
								$http.post("rest/order/userCancelOrderApply", processInstanceId
								).success(function (data) {
					                // 如果连接成功，延时返回给调用者
					                deferred.resolve(data);
					            }).error(function () {
				                    deferred.reject('连接服务器出错！');
				                })
								return deferred.promise;
							},
							//用户取消框架申请
						    userCancelFrameApply : function(processInstanceId) {
								var deferred = $q.defer();
								$http.post("rest/order/userCancelFrameApply", processInstanceId
								).success(function (data) {
					                // 如果连接成功，延时返回给调用者
					                deferred.resolve(data);
					            }).error(function () {
				                    deferred.reject('连接服务器出错！');
				                })
								return deferred.promise;
							},
							getUnitPrice:function(params) {//获取订单物料价格
								var deferred = $q.defer();
								$http.post("rest/order/getUnitPrice", JSON.stringify(params)
								).success(function (data) {
					                // 如果连接成功，延时返回给调用者
					                deferred.resolve(data);
					            }).error(function () {
				                    deferred.reject('连接服务器出错！');
				                })
								return deferred.promise;
							}
		}
		} ]);
