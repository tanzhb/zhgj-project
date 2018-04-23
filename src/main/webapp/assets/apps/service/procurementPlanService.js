angular.module('MetronicApp').service('procurementPlanService',
		[ '$http', '$q', function($http, $q) {
			return {
				//保存订单
				save : function(procurementPlanInfo) {
					var deferred = $q.defer();
					$http.post("rest/procurementPlan/saveProcurementPlan", procurementPlanInfo
					).success(function (data) {
		                // 如果连接成功，延时返回给调用者
		                deferred.resolve(data);
		            }).error(function () {
	                    deferred.reject('连接服务器出错！');
	                })
					return deferred.promise;
				},//客户端提交订单
				submitProcurementPlan : function(procurementPlanInfo) {
					var deferred = $q.defer();
					$http.post("rest/procurementPlan/submitProcurementPlan", procurementPlanInfo
					).success(function (data) {
		                // 如果连接成功，延时返回给调用者
		                deferred.resolve(data);
		            }).error(function () {
	                    deferred.reject('连接服务器出错！');
	                })
					return deferred.promise;
				},//接受客户订单
				acceptSubmit : function(procurementPlanInfo) {
					var deferred = $q.defer();
					$http.post("rest/procurementPlan/acceptSubmit", procurementPlanInfo
					).success(function (data) {
		                // 如果连接成功，延时返回给调用者
		                deferred.resolve(data);
		            }).error(function () {
	                    deferred.reject('连接服务器出错！');
	                })
					return deferred.promise;
				},//供应商确认订单
				supplyConfirmed : function(procurementPlanInfo) {
					var deferred = $q.defer();
					$http.post("rest/procurementPlan/supplyConfirmed", procurementPlanInfo
					).success(function (data) {
		                // 如果连接成功，延时返回给调用者
		                deferred.resolve(data);
		            }).error(function () {
	                    deferred.reject('连接服务器出错！');
	                })
					return deferred.promise;
				},//供应商确认框架协议
				supplyConfirmedFrame : function(procurementPlanInfo) {
					var deferred = $q.defer();
					$http.post("rest/procurementPlan/supplyConfirmedFrame", procurementPlanInfo
					).success(function (data) {
		                // 如果连接成功，延时返回给调用者
		                deferred.resolve(data);
		            }).error(function () {
	                    deferred.reject('连接服务器出错！');
	                })
					return deferred.promise;
				},
				//平台提交订单
				pingTaiSubmit : function(procurementPlanInfo) {
					var deferred = $q.defer();
					$http.post("rest/procurementPlan/pingTaiSubmit", procurementPlanInfo
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
					$http.post("rest/procurementPlan/pingTaiSubmitFrame", buyFrame
					).success(function (data) {
		                // 如果连接成功，延时返回给调用者
		                deferred.resolve(data);
		            }).error(function () {
	                    deferred.reject('连接服务器出错！');
	                })
					return deferred.promise;
				},
				//销售订单分解
				procurementPlanGenerateBuy : function(serialNum) {
					var deferred = $q.defer();
					$http.get("rest/procurementPlan/procurementPlanGenerateBuy", {params:{serialNum:serialNum}}
					).success(function (data) {
		                // 如果连接成功，延时返回给调用者
		                deferred.resolve(data);
		            }).error(function () {
	                    deferred.reject('连接服务器出错！');
	                })
					return deferred.promise;
				},
				//复制订单
				copyProcurementPlan : function(serialNum) {
					var deferred = $q.defer();
					$http.get("rest/procurementPlan/copyProcurementPlanInfo", {params:{serialNum:serialNum}}
					).success(function (data) {
		                // 如果连接成功，延时返回给调用者
		                deferred.resolve(data);
		            }).error(function () {
	                    deferred.reject('连接服务器出错！');
	                })
					return deferred.promise;
				},
				//检查编号
				checkNum : function(procurementPlanInfo) {
					var deferred = $q.defer();
					$http.post("rest/procurementPlan/checkNum", procurementPlanInfo
					).success(function (data) {
		                // 如果连接成功，延时返回给调用者
		                deferred.resolve(data);
		            }).error(function () {
	                    deferred.reject('连接服务器出错！');
	                })
					return deferred.promise;
				},
				//供应商接收订单
				recive : function(procurementPlanInfo) {
					var deferred = $q.defer();
					$http.post("rest/procurementPlan/reciveProcurementPlan", procurementPlanInfo
					).success(function (data) {
		                // 如果连接成功，延时返回给调用者
		                deferred.resolve(data);
		            }).error(function () {
	                    deferred.reject('连接服务器出错！');
	                })
					return deferred.promise;
				},
				//供应商接收框架协议
				reciveFrame : function(procurementPlanInfo) {
					var deferred = $q.defer();
					$http.post("rest/procurementPlan/reciveFrame", procurementPlanInfo
					).success(function (data) {
		                // 如果连接成功，延时返回给调用者
		                deferred.resolve(data);
		            }).error(function () {
	                    deferred.reject('连接服务器出错！');
	                })
					return deferred.promise;
				},
				
				//删除订单
			    delProcurementPlan : function(ids){
			        var deferred = $q.defer();  
	
			        $http.post("rest/procurementPlan/deleteProcurementPlans", ids)
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
			    getProcurementPlanInfo : function(serialNum){
			        var deferred = $q.defer();  
			        $http.get("rest/procurementPlan/getProcurementPlanInfo", {params:{serialNum:serialNum}})
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
			        $http.get("rest/procurementPlan/getFrameInfo", {params:{serialNum:serialNum}})
			        .success(function (data) {  
			            // 如果连接成功，延时返回给调用者  
			            deferred.resolve(data);  
			        })  
			            .error(function () {  
			                deferred.reject('连接服务器出错！');  
			            })  
			        return deferred.promise;  
			          
			    },//保存所有采购清单物料
			    saveAllProcurementPlanMateriel : function (procurementPlanMateriel){
					var deferred = $q.defer();
					var params = {};
					params = JSON.stringify(procurementPlanMateriel);
					$http.post("rest/procurementPlan/saveAllProcurementPlanMateriel", 
							params//传整个表单数据  
					).then(function success(result) {
						deferred.resolve(result);//请求成功
					}, function error(err) {
						deferred.reject(err);//请求失败
					});
					return deferred.promise;//返回承诺
				},//
				updateDemandMateriel : function(ids){
			        var deferred = $q.defer();  
			        $http.post("rest/procurementPlan/updateDemandMateriels", ids)
			        .success(function (data) {  
			            // 如果连接成功，延时返回给调用者  
			            deferred.resolve(data);  
			        })  
			            .error(function () {  
			                deferred.reject('连接服务器出错！');  
			            })  
			        return deferred.promise;  
			    },
				releaseProcurementPlanMateriel : function (procurementPlanMateriel){
					var deferred = $q.defer();
					var params = {};
					params = JSON.stringify(procurementPlanMateriel);
					$http.post("rest/procurementPlan/releaseProcurementPlanMateriel", 
							params//传整个表单数据  
					).then(function success(result) {
						deferred.resolve(result);//请求成功
					}, function error(err) {
						deferred.reject(err);//请求失败
					});
					return deferred.promise;//返回承诺
				},
				//保存采购清单订单物料
			    saveProcurementPlanMateriel : function (procurementPlanMateriel){
					var deferred = $q.defer();
					$http.post("rest/procurementPlan/saveProcurementPlanMateriel", 
							procurementPlanMateriel//传整个表单数据  
					).then(function success(result) {
						deferred.resolve(result);//请求成功
					}, function error(err) {
						deferred.reject(err);//请求失败
					});
					return deferred.promise;//返回承诺
				},
				//保存单个需求物料
			    saveDemandMateriel : function (demandMateriel){
					var deferred = $q.defer();
					$http.post("rest/procurementPlan/saveDemandMateriel", 
							demandMateriel//传整个表单数据  
					).then(function success(result) {
						deferred.resolve(result);//请求成功
					}, function error(err) {
						deferred.reject(err);//请求失败
					});
					return deferred.promise;//返回承诺
				},
				//删除采购清单物料
				deleteProcurementPlanMateriel : function(serialNum){
					var deferred = $q.defer();
					 $http.post("rest/procurementPlan/deleteProcurementPlanMateriel", 
						 	serialNum//传整个表单数据  
				        ).then(function success(result) {
			                deferred.resolve(result);//请求成功
			            }, function error(err) {
			                deferred.reject(err);//请求失败
			            });
			            return deferred.promise;//返回承诺
				},//删除需求物料
				deleteDemandMateriel : function(serialNum){
					var deferred = $q.defer();
					 $http.post("rest/procurementPlan/deleteDemandMateriel", 
						 	serialNum//传整个表单数据  
				        ).then(function success(result) {
			                deferred.resolve(result);//请求成功
			            }, function error(err) {
			                deferred.reject(err);//请求失败
			            });
			            return deferred.promise;//返回承诺
				},
				 saveAllDemandMateriel : function (demandMateriel){
						var deferred = $q.defer();
						var params = {};
						params = JSON.stringify(demandMateriel);
						$http.post("rest/procurementPlan/saveAllDemandMateriel", 
								params//传整个表单数据  
						).then(function success(result) {
							deferred.resolve(result);//请求成功
						}, function error(err) {
							deferred.reject(err);//请求失败
						});
						return deferred.promise;//返回承诺
					},//订单保存合同
				saveContract : function (contract){
					var deferred = $q.defer();
					$http.post("rest/procurementPlan/saveContract", 
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
					$http.post("rest/procurementPlan/saveFrame", 
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
					$http.post("rest/procurementPlan/saveClauseAfterSales", 
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
					$http.post("rest/procurementPlan/saveClauseDelivery", 
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
					$http.post("rest/procurementPlan/saveClauseCheckAccept", 
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
					$http.post("rest/procurementPlan/saveClauseAdvance", 
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
					$http.post("rest/procurementPlan/saveClauseSettlement", 
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
					$http.post("rest/procurementPlan/saveClauseSettlementDetail", 
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
					$http.post("rest/procurementPlan/saveFile", params
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
					$http.post("rest/procurementPlan/saveFrameFile", params
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
					$http.post("rest/procurementPlan/saveClauseFramework", params
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
					$http.post("rest/procurementPlan/procurementPlanImport",  
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
					$http.post("rest/procurementPlan/procurementPlanImport",  
							fd,{headers:{'Content-Type': undefined},transformRequest: angular.identity}
					).then(function success(result) {
						deferred.resolve(result);//请求成功
					}, function error(err) {
						deferred.reject(err);//请求失败
					});
					return deferred.promise;//返回承诺
				},//启动采购订单流程
			/*	startProcurementPlanProcess : function(procurementPlanInfo) {
					var deferred = $q.defer();
					$http.post("rest/procurementPlan/startProcurementPlanProcess", procurementPlanInfo
					).success(function (data) {
		                // 如果连接成功，延时返回给调用者
		                deferred.resolve(data);
		            }).error(function () {
	                    deferred.reject('连接服务器出错！');
	                })
					return deferred.promise;
				},*///启动销售订单流程
				startSaleProcurementPlanProcess : function(procurementPlanInfo) {
					var deferred = $q.defer();
					$http.post("rest/procurementPlan/startSaleProcurementPlanProcess", procurementPlanInfo
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
					$http.post("rest/procurementPlan/startBuyFrameProcess", buyFrame
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
					$http.post("rest/procurementPlan/startSaleFrameProcess", saleFrame
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
				        $http.post(ctx + "rest/procurementPlan/toApproval/" + ids).success(function (data) {  
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
				        $http.post(ctx + "rest/procurementPlan/toFrameApproval/" + ids).success(function (data) {  
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
						        $http.get("rest/procurementPlan/findDefaultFrame", {params:{type:type,selectFor:selectFor,comId:comId}})
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
						    userCancelProcurementPlanApply : function(processInstanceId) {
								var deferred = $q.defer();
								$http.post("rest/procurementPlan/userCancelProcurementPlanApply", processInstanceId
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
								$http.post("rest/procurementPlan/userCancelFrameApply", processInstanceId
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
								$http.post("rest/procurementPlan/getUnitPrice", JSON.stringify(params)
								).success(function (data) {
					                // 如果连接成功，延时返回给调用者
					                deferred.resolve(data);
					            }).error(function () {
				                    deferred.reject('连接服务器出错！');
				                })
								return deferred.promise;
							},
							getProcurementPlanMateriels:function(BOMSerialNum) {//获取订单物料价格
								var deferred = $q.defer();  
						        $http.get("rest/procurementPlan/getProcurementPlanMateriels", {params:{serialNum:BOMSerialNum}})
						        .success(function (data) {  
						            // 如果连接成功，延时返回给调用者  
						            deferred.resolve(data);  
						        }).error(function () {  
						            deferred.reject('连接服务器出错！');  
						        })  
						        return deferred.promise; 
							},//启动采购计划流程
							startProcurementPlanProcess : function(orderInfo) {
								var deferred = $q.defer();
								$http.post(ctx+"rest/procurementPlan/startBuyPlanProcess", orderInfo
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
