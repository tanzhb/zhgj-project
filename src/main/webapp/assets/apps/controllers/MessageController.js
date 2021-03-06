/**
 * 
 */

angular.module('MetronicApp').controller('MessageController',['$rootScope','$scope','$state','$http','messageService','$location','$compile','$stateParams', '$q',
                                                             function($rootScope,$scope,$state,$http,messageService,$location,$compile,$stateParams,$q) {
	 $scope.$on('$viewContentLoaded', function() {   
	    	// initialize core components
		    handle = new pageHandle();
	    	App.initAjax();
	    		var type = handle.getCookie("d_type");
	 			if(type=="systemMessage"){
	 				$('#message_tab a:last').parent().addClass('active');
	 				$('#message_tab a:first').parent().removeClass('active');
	 				$("#portlet_tab2_2").addClass("active");
	 				$("#portlet_tab2_1").removeClass("active");
		 			$scope.systemMessageList();
		 		}else{
		 			$('#message_tab a:first').parent().addClass('active');
	 				$('#message_tab a:last').parent().removeClass('active');
	 				$("#portlet_tab2_1").addClass("active");
	 				$("#portlet_tab2_2").removeClass("active");
	 				
		 			$scope.businessMessageList();
		 		}	
	 			getMessageCount();
	    	
	    	// set default layout mode
	    	$rootScope.settings.layout.pageContentWhite = true;
	        $rootScope.settings.layout.pageBodySolid = false;
	        $rootScope.settings.layout.pageSidebarClosed = false;
	       
	    	
	 });
	 

	 		$scope.businessMessageList = function(){
	 			createBusinessMessageTable(10,1,true,null);
	 		}
	 		$scope.systemMessageList = function(){
	 			createSystemMessageTable(10,1,true,null);
	 		}
	 		
	 		function getMessageCount(){
	 			 var promise = messageService.systemMessageSize();
	 			 promise.then(function(data){
	 				$scope.systemMessageSize = data; 
	 			 },function(data){
	 				 //调用承诺接口reject();
	 			 });
	 			 var promise2 = messageService.businessMessageSize();
	 			 promise2.then(function(data){
	 				 $scope.businessMessageSize = data; 
	 			 },function(data){
	 				 //调用承诺接口reject();
	 			 });
			}
	 		
	 		 $('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
			        // 获取已激活的标签页的名称
			        var activeTab = $(e.target).text(); 
			        // 获取前一个激活的标签页的名称
			       // var previousTab = $(e.relatedTarget).text(); 
			        var absurl = $location.absUrl();
			        $("#tip").text(activeTab);
			        if(activeTab=="系统消息"){
			        	handle.addCookie("d_type","systemMessage",24);
			        }else if(activeTab=="业务提醒"){
			        	handle.addCookie("d_type","businessMessage",24);
			        }
			});
	 
	 
	 		 /**
	 		  * 创建业务提醒列表
	 		  */
	 		 function createBusinessMessageTable(pageSize,pageIndex,init,params){
	 			 //初始化表格数据
	 			 handle.blockUI(null,"#businessMessage");
	 			 var promise = messageService.createBusinessMessageTable(pageSize,pageIndex,params);
	 			 promise.then(function(data){
	 				 $scope.messageList = data.data.result;
	 				 data.data.params=params;
	 				 handle.createPage("#businessMessage",data.data,"rest/message/businessMessageList",createBusinessMessageTable,init);
	 			 },function(data){
	 				 //调用承诺接口reject();
	 			 });
	 		 }
	 		 
	 		 /**
	 		  * 创建系统消息列表
	 		  */
	 		 function createSystemMessageTable(pageSize,pageIndex,init,params){
	 			 //初始化表格数据
	 			 handle.blockUI(null,"#systemMessage");
	 			 var promise = messageService.createSystemMessageTable(pageSize,pageIndex,params);
	 			 promise.then(function(data){
	 				 $scope.messageList = data.data.result;
	 				 data.data.params=params;
	 				 handle.createPage("#systemMessage",data.data,"rest/message/systemMessageList",createSystemMessageTable,init);
	 			 },function(data){
	 				 //调用承诺接口reject();
	 			 });
	 		 }

			
		    /**
	         * 批量删除
	         */
	        $scope.deleteMessageBatch=function () {
	        	var id_count = table.$('input[name="serialNum"]:checked').length;
				if(id_count==0){
					toastr.warning("请选择您要删除的记录");
					return;
				}
	        	handle.confirm("确定删除吗？",function(){
	        		var ids = '';
					// Iterate over all checkboxes in the table
					table.$('input[name="serialNum"]').each(
							function() {
								// If checkbox exist in DOM
								if ($.contains(document, this)) {
									// If checkbox is checked
									if (this.checked) {
										// 将选中数据id放入ids中
										if (ids == '') {
											ids = this.value;
										} else
											ids = ids + ','
													+ this.value;
									}
								}
							});
	        		handle.blockUI();
	        		var promise = messageService.deleteMessageBatch(ids);
	        		promise.then(function(data){
	        			toastr.success("删除成功");
	        			handle.unblockUI();
	        			table.ajax.reload(); // 重新加载datatables数据
	        			/*$state.go('company',{},{reload:true}); */
	        		},function(data){
	        			//调用承诺接口reject();
	        		});
	        		
	        	});
	        	
	        };


			$scope.delHtmlTag = function(str){
				str = str.replace("马上处理","").replace("查看","");
				return delHtmlTag(str);
			}
			
			
			$scope.deleteMyMessage = function(serialNum){
	        	handle.confirm("确定删除吗？",function(){
	        		handle.blockUI();
	        		var promise = messageService.deleteMyMessage(serialNum);
	        		promise.then(function(data){
	        			toastr.success("删除成功");
	        			handle.unblockUI();
	        			createTable(10,1,true,null); // 重新加载datatables数据
	        		},function(data){
	        			//调用承诺接口reject();
	        		});
	        		
	        	});
			}
			
			$scope.messageView = function(serialNum,objSerial,actionName){
				readMessage(serialNum);
			
				if(actionName=="applyBuyOrder"||actionName=="refuseBuyOrder"){
					$state.go("buyOrder",{tabHref:'1'});
				}else if(actionName=="confirmBuyOrder"||actionName=="supplyOrder"||actionName=="noticeSupply"){
					$state.go("supplyOrder");
				}else if(actionName=="beConfirmBuyOrder"||actionName=="agreeBuyOrder"){
					$state.go("buyOrder");
				}else if(actionName=="takeDelivery"
					||actionName=="outCheckToSale"
					||actionName=="inCheckToSale"
					||actionName=="outToSale"
					||actionName=="inToSale"||actionName=="outToSaleGroup"){
					$state.go("delivery");
				}else if(actionName=="delivery"
					||actionName=="outCheckToBuy"
					||actionName=="inCheckToBuy"
					||actionName=="outToBuy"
					||actionName=="inToBuy"
					||actionName=="in2stock"||actionName=="inToBuyToSale"){
					$state.go("takeDelivery");
				}else if(actionName=="shoukuan"){
					$state.go("gatheringMoneyRecord");
				}else if(actionName=="inToBuyToSale"||actionName=="outToSaleGroup"||actionName=="beReceiveSaleOrder"){
					$state.go("saleOrder");
				}else if(actionName=="sale2paln"){
					$state.go("procurementPlan");
				}else if(actionName=="paln2buy"){
					$state.go("buyOrder");
				}else if(actionName=="in2WaitCheck"){
					$state.go("stockInOutCheck");
				}else if(actionName=="clearance"){
					$state.go("customsClearanceForm");
				}else if(actionName=="declaration"){
					$state.go("customsDeclarationForm");
				}else if(actionName=="beConfirmPayMemoRecord"){
					$state.go("paymentRecordC");
				}else if(actionName=="demandplan2Promanager"){
					$state.go("demandPlan");
				}else if(actionName=="noticeSupply"){
					$state.go("supplyOrder",{tabHref:'2',serialNum:objSerial});
				}else if(actionName=="buyApply"){
					$state.go("procurementPlan");
				}else if(actionName=="agreeBuyApply"){
					$state.go("saleForecast");
				}
				
			}
			
			var readMessage = function(serialNum){
				var promise = messageService.readMessage(serialNum);
        		promise.then(function(data){
        			
        		},function(data){
        			//调用承诺接口reject();
        		});
			}

}]); 
