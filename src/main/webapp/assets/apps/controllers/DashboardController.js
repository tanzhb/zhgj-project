var dashModule  = angular.module('MetronicApp');


dashModule.controller('DashboardController', function($rootScope, $scope, $state, $http, $timeout,$q) {
    $scope.$on('$viewContentLoaded', function() {   
        // initialize core components
        App.initAjax();
    });

    // set sidebar closed and body solid layout mode
    $rootScope.settings.layout.pageContentWhite = true;
    $rootScope.settings.layout.pageBodySolid = false;
    $rootScope.settings.layout.pageSidebarClosed = false;
    

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
				}else{
					workflowName = "未命名";
				}
				
				map['template'] = "<li><div class='col-md-7'>" +
						"<a ui-sref='"+workflowType+"({tabHref:1})'>" +//tabHref:1将tab指向“待办列表”
								"<span>"+workflowName+"："+title+"</span></a></div>" +
						"<div class='col-md-5'><div class='date'>" + timeStamp2String(creatTime) + "</div></div></li>";
				list.push(map);
			}
		}else{
			var map = {};
			map['template'] = "<div>无待办事项！</div>"
				list.push(map);
		}
		$scope.dbItems = list;
     });
    
 
    
    //已办列表
	$http.get(ctx + "/rest/processAction/endTask/" + 'All').success( function(result) {
		var list = [];
		if(result != null && result.data != null && result.data.length > 0){
			for (var i=0;i<result.data.length;i++){
				var map = {};
				var title = result.data[i].title;
				var endTime = result.data[i].endTime;
				var workflowType = result.data[i].businessType;
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
				}else{
					workflowName = "未命名";
				}
				map['template'] = "<li><div class='col-md-7'>" +
						"<a ui-sref='"+workflowType+"({tabHref:2})'>" +//tabHref:1将tab指向“已办列表”
						"<span>"+workflowName+"："+title+"</span></a></div>" +
						"<div class='col-md-5'><div class='date'>" + timeStamp2String(endTime) + "</div></div></li>";
				list.push(map);
			}
		}else{
			var map = {};
			map['template'] = "<div>无已办事项！</div>"
				list.push(map);
		}
		$scope.ybItems = list;
     });
	 //公告
	$http.post(ctx + "/rest/notice/myNoticeList").success( function(result) {
		var promise =createTable(5,1,true,null);
		promise.then(function(data){
			$scope.noticeList = data.data.result;
			
        },function(data){
           //调用承诺接口reject();
     });
		
     });
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
	            deferred.resolve(result);//请求成功
	        }, function error(err) {
	            deferred.reject(err);//请求失败
	        });
			return deferred.promise;//返回承诺
		    }
	  
	 //业务消息
	$http.post(ctx + "/rest/message/businessMessageList").success( function(result) {
		var promise =createTableMessage(5,1,true,null);
		promise.then(function(data){
			$scope.messageList = data.data.result;
			
        },function(data){
           //调用承诺接口reject();
     });
		
     });
	 function createTableMessage(pageSize,pageIndex,init,params){
			var deferred = $q.defer();
			var message = {};
			message.pageSize = pageSize;
			message.pageIndex = pageIndex;
			$http.post("rest/message/businessMessageList",   
					message
	    	).then(function success(result) {
	            deferred.resolve(result);//请求成功
	        }, function error(err) {
	            deferred.reject(err);//请求失败
	        });
			return deferred.promise;//返回承诺
		    }
});

function todo() {
	window.location.href=ctx + "/rest/page/addVacation";
	 // $state.go("rest/page/addVacation");
};