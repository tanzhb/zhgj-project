var dashModule  = angular.module('MetronicApp');


dashModule.controller('DashboardController', ['$rootScope', '$scope', '$state', '$http', '$timeout','$q','messageService','noticeService', function($rootScope, $scope, $state, $http, $timeout,$q,messageService,noticeService) {
    $scope.$on('$viewContentLoaded', function() {   
        // initialize core components
        App.initAjax();
        handle = new pageHandle();
        createTableMessage(50,1,true,null);
        createTable(50,1,true,null);
        createTableUnReadMessage(50,1,true,null);
    });

    // set sidebar closed and body solid layout mode
    $rootScope.settings.layout.pageContentWhite = true;
    $rootScope.settings.layout.pageBodySolid = false;
    $rootScope.settings.layout.pageSidebarClosed = false;
    
    $scope.delHtmlTag = function(str){
		str = str.replace("马上处理","").replace("查看","");
		return delHtmlTag(str);
	}
    
    $scope.messageView = function(serialNum,objSerial,actionName){
		readMessage(serialNum);
		if(actionName=="applyBuyOrder"||actionName=="refuseBuyOrder"){
			$state.go("buyOrder",{tabHref:'1'});
		}else if(actionName=="confirmBuyOrder"||actionName=="supplyOrder"){
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
//			$state.go("supplyOrder",{tabHref:'2',serialNum:objSerial});
			$state.go('viewDelivery',{serialNum:objSerial,oprateType:"forSupplyOrder"});
		}else if(actionName=="buyApply"){
			$state.go("procurementPlan");
		}
	}
    $scope.changeReadFlg = function(serialNum,readFlg){
    	var promise = messageService.changeReadFlg(serialNum);
		promise.then(function(data){
			if(data.data==0){//修改readFlg成功
				if($("#"+serialNum).html()=='已读'){
					$("#"+serialNum).html("未读");
					$("#"+serialNum).css("color","#f36a5a");
				}else{
					$("#"+serialNum).html("已读");
					$("#"+serialNum).css("color","gray");
				}
				location.reload();//刷新获取最新消息数量
			}else if(data.data==1){//修改readFlg失败
				
			}
		},function(data){
			//调用承诺接口reject();
		});
	}
	var readMessage = function(serialNum){
		var promise = messageService.readMessage(serialNum);
		promise.then(function(data){
			
		},function(data){
			//调用承诺接口reject();
		});
	}
	
	$scope.deleteMyNotice = function(serialNum){
    	handle.confirm("确定删除吗？",function(){
    		handle.blockUI();
    		var promise = noticeService.deleteMyNotice(serialNum);
    		promise.then(function(data){
    			toastr.success("删除成功");
    			handle.unblockUI();
    			createTable(5,1,true,null); // 重新加载datatables数据
    		},function(data){
    			//调用承诺接口reject();
    		});
    		
    	});
	}
	
	$scope.myNoticeView = function(serialNum){
		var promise = noticeService.readMyNotice(serialNum);
		promise.then(function(data){
		},function(data){
			//调用承诺接口reject();
		});
		$state.go("noticeView",{serialNum:serialNum});
	}

    //待办列表 	
	$http.get(ctx + "/rest/processAction/todoTask/" + 'All').success( function(result) {
		var list = [];
		if(result != null && result.data != null && result.data.length > 0){
			for (var i=0;i<result.data.length;i++){
				var map = {};
				var title = result.data[i].title;
				var creatTime = result.data[i].createTime;
				var workflowType = result.data[i].businessType;
				var assign = result.data[i].assign;
				var taskId = result.data[i].taskId;
				var processInstanceId = result.data[i].processInstanceId;
				var workflowName = "";
				if(workflowType == 'vacation'){
					workflowName = "请假流程";
				}else if(workflowType == 'accountPayable'){
					workflowName = "应付款流程";
					workflowType="paymentRecordC";
				}else if(workflowType == 'buyOrder'){
					workflowName = "采购订单流程";
				}else if(workflowType == 'saleOrder'){
					workflowName = "销售订单流程";
				}else if(workflowType == 'takeDelivery'){
					workflowName = "收货流程";
				}else if(workflowType == 'myNotice'){
					workflowName = "公告流程";
				}else if(workflowType == 'buyFrame'){
					workflowName = "采购框架流程";
				}else if(workflowType == 'saleFrame'){
					workflowName = "销售框架流程";
				}else if(workflowType == 'delivery'){
					workflowName = "销售订单发货计划流程";
				}else if(workflowType == 'buyApply'){
					workflowName = "采购计划流程";
				}else if(workflowType == 'buyPrice'){
					workflowName = "采购价格流程";
				}else if(workflowType == 'salePrice'){
					workflowName = "销售价格流程";
				}else if(workflowType == 'outInvoice'){
					workflowName = "销项票流程";
				}else{
					workflowName = "未命名";
				}
				
				map['template'] = "<li style='background-color:#fff'><div class='col-md-7'>" +
						"<a ui-sref='"+workflowType+"({tabHref:1})'>" +//tabHref:1将tab指向“待办列表”
								"<span>"+workflowName+"："+title+"</span></a></div>" +
						"<div class='col-md-5'><div class='date'>" + timeStamp2String(creatTime) + "</div></div></li>";
				
				if(workflowType == 'delivery'){
					map['template'] = "<li style='background-color:#fff'><div class='col-md-7'>" +
					"<a ui-sref='saleOrder({tabHref:3})'>" +//tabHref:1将tab指向“待办列表”
							"<span>"+workflowName+"："+title+"</span></a></div>" +
					"<div class='col-md-5'><div class='date'>" + timeStamp2String(creatTime) + "</div></div></li>";
				}
				if(workflowType == 'buyApply'){
					map['template'] = "<li style='background-color:#fff'><div class='col-md-7'>" +
					"<a ui-sref='procurementPlan({tabHref:1})'>" +//tabHref:1将tab指向“待办列表”
							"<span>"+workflowName+"："+title+"</span></a></div>" +
					"<div class='col-md-5'><div class='date'>" + timeStamp2String(creatTime) + "</div></div></li>";
				}
				if(workflowType == 'buyPrice'){
					map['template'] = "<li style='background-color:#fff'><div class='col-md-7'>" +
					"<a ui-sref='priceList({tabHref:1})'>" +//tabHref:1将tab指向“采购价格待办列表”
							"<span>"+workflowName+"："+title+"</span></a></div>" +
					"<div class='col-md-5'><div class='date'>" + timeStamp2String(creatTime) + "</div></div></li>";
				}
				if(workflowType == 'salePrice'){
					map['template'] = "<li style='background-color:#fff'><div class='col-md-7'>" +
					"<a ui-sref='priceList({tabHref:2})'>" +//tabHref:1将tab指向“销售价格待办列表”
							"<span>"+workflowName+"："+title+"</span></a></div>" +
					"<div class='col-md-5'><div class='date'>" + timeStamp2String(creatTime) + "</div></div></li>";
				}
				if(workflowType == 'outInvoice'){
					map['template'] = "<li style='background-color:#fff'><div class='col-md-7'>" +
					"<a ui-sref='invoice({tabHref:1})'>" +//tabHref:1将tab指向“销售价格待办列表”
							"<span>"+workflowName+"："+title+"</span></a></div>" +
					"<div class='col-md-5'><div class='date'>" + timeStamp2String(creatTime) + "</div></div></li>";
				}
				list.push(map);
			}
		}else{
			var map = {};
//			map['template'] = "<div>无待办事项！</div>"
				list.push(map);
		}
		$scope.dbItems = list;
     });
    
 
	$scope.displayYbItemCount = 10;
	$scope.addDisplayYbItemCount  = function(){
		$scope.displayYbItemCount = $scope.displayYbItemCount+10;
	}
	
    //已办列表
	$scope.findEndTask  = function(serialNum){
		$scope.displayYbItemCount = 10;
		handle.blockUI();
	$http.get(ctx + "/rest/processAction/endTask/" + 'All').then(function success(result) {
		handle.unblockUI();
		var list = [];
		if(result != null && result.data != null && result.data.data != null && result.data.data.length > 0){
			for (var i=0;i<result.data.data.length;i++){
				var map = {};
				var title = result.data.data[i].title;
				var creatTime = result.data.data[i].createTime;
				var endTime = result.data.data[i].endTime;
				var workflowType = result.data.data[i].businessType;
				var workflowName = "";
				if(workflowType == 'vacation'){
					workflowName = "请假流程";
				}else if(workflowType == 'accountPayable'){
					workflowName = "应付款流程";
					workflowType="paymentRecordC";
				}else if(workflowType == 'buyOrder'){
					workflowName = "采购订单流程";
				}else if(workflowType == 'saleOrder'){
					workflowName = "销售订单流程";
				}else if(workflowType == 'takeDelivery'){
					workflowName = "收货流程";
				}else if(workflowType == 'myNotice'){
					workflowName = "公告流程";
				}else if(workflowType == 'buyFrame'){
					workflowName = "采购框架流程";
				}else if(workflowType == 'saleFrame'){
					workflowName = "销售框架流程";
				}else if(workflowType == 'delivery'){
					workflowName = "销售订单发货计划流程";
				}else if(workflowType == 'buyApply'){
					workflowName = "采购计划流程";
				}else if(workflowType == 'buyPrice'){
					workflowName = "采购价格流程";
				}else if(workflowType == 'salePrice'){
					workflowName = "销售价格流程";
				}else if(workflowType == 'outInvoice'){
					workflowName = "销项票流程";
				}else{
					workflowName = "未命名";
				}
				map['template'] = "<li style='background-color:#fff'><div class='col-md-7'>" +
						"<a ui-sref='"+workflowType+"({tabHref:2})'>" +//tabHref:1将tab指向“已办列表”
						"<span>"+workflowName+"："+title+"</span></a></div>" +
						"<div class='col-md-5'><div class='date'>" + timeStamp2String(endTime) + "</div></div></li>";
				
				if(workflowType == 'delivery'){
					map['template'] = "<li style='background-color:#fff'><div class='col-md-7'>" +
					"<a ui-sref='saleOrder({tabHref:4})'>" +//tabHref:1将tab指向“待办列表”
							"<span>"+workflowName+"："+title+"</span></a></div>" +
					"<div class='col-md-5'><div class='date'>" + timeStamp2String(endTime) + "</div></div></li>";
				}
				if(workflowType == 'buyApply'){
					map['template'] = "<li style='background-color:#fff'><div class='col-md-7'>" +
					"<a ui-sref='procurementPlan({tabHref:1})'>" +//tabHref:1将tab指向“待办列表”
							"<span>"+workflowName+"："+title+"</span></a></div>" +
					"<div class='col-md-5'><div class='date'>" + timeStamp2String(endTime) + "</div></div></li>";
				}
				if(workflowType == 'buyPrice'){
					map['template'] = "<li style='background-color:#fff'><div class='col-md-7'>" +
					"<a ui-sref='priceList({tabHref:1})'>" +//tabHref:1将tab指向“采购价格待办列表”
							"<span>"+workflowName+"："+title+"</span></a></div>" +
					"<div class='col-md-5'><div class='date'>" + timeStamp2String(endTime) + "</div></div></li>";
				}
				if(workflowType == 'salePrice'){
					map['template'] = "<li style='background-color:#fff'><div class='col-md-7'>" +
					"<a ui-sref='priceList({tabHref:2})'>" +//tabHref:1将tab指向“销售价格待办列表”
							"<span>"+workflowName+"："+title+"</span></a></div>" +
					"<div class='col-md-5'><div class='date'>" + timeStamp2String(endTime) + "</div></div></li>";
				}
				if(workflowType == 'outInvoice'){
					map['template'] = "<li style='background-color:#fff'><div class='col-md-7'>" +
					"<a ui-sref='invoice({tabHref:2})'>" +//tabHref:1将tab指向“销售价格待办列表”
							"<span>"+workflowName+"："+title+"</span></a></div>" +
					"<div class='col-md-5'><div class='date'>" + timeStamp2String(endTime) + "</div></div></li>";
				}
				list.push(map);
			}
		}else{
			var map = {};
			map['template'] = "<div>无已办事项！</div>"
				list.push(map);
		}
		$scope.ybItems = list;
     }, function error(err) {
    	 handle.unblockUI();
    	 toastr.error("请求出错！");
		});
	
	}
	 //公告
/*	$http.post(ctx + "/rest/notice/myNoticeList").success( function(result) {
		var promise =createTable(5,1,true,null);
		promise.then(function(data){
			$scope.noticeList = data.data.result;
			
        },function(data){
           //调用承诺接口reject();
     });
		
     });*/
	   function createTable(pageSize,pageIndex,init,params){
		   var deferred = $q.defer();
			var notice = {};
			notice.pageSize = pageSize;
			notice.pageIndex = pageIndex;
			var postCfg = {
				    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
				    transformRequest: function (data) {
				        return $.param(data);
				    }
				};
			$http.post("rest/notice/myNoticeList",   
					notice,postCfg
	    	).then(function success(result) {
	    		$scope.noticeList = result.data.result;//请求成功
	        }, function error(err) {
	            deferred.reject(err);//请求失败
	        });
			return deferred.promise;//返回承诺
		    }
	  
/*	 //业务消息
	$http.post(ctx + "/rest/message/businessMessageList").success( function(result) {
		var promise =createTableMessage(5,1,true,null);
		promise.then(function(data){
			$scope.messageList = data.data.result;
			
        },function(data){
           //调用承诺接口reject();
     });
		
     });*/
	   
	 function createTableMessage(pageSize,pageIndex,init,params){
			var deferred = $q.defer();
			var message = {};
			message.pageSize = pageSize;
			message.pageIndex = pageIndex;
			$http.post("rest/message/businessMessageList",   
					message
	    	).then(function success(result) {
	    		$scope.messageList = result.data.result;//请求成功
	        }, function error(err) {
	            deferred.reject(err);//请求失败
	        });
			return deferred.promise;//返回承诺
		    }
	 function createTableUnReadMessage(pageSize,pageIndex,init,params){
			var deferred = $q.defer();
			var message = {};
			message.pageSize = pageSize;
			message.pageIndex = pageIndex;
			$http.post("rest/message/unReadBusinessMessageList",   
					message
	    	).then(function success(result) {
	    		$scope.unReadMessageList = result.data.result;//请求成功
	        }, function error(err) {
	            deferred.reject(err);//请求失败
	        });
			return deferred.promise;//返回承诺
		    }
}]);

function todo() {
	window.location.href=ctx + "/rest/page/addVacation";
	 // $state.go("rest/page/addVacation");
};
