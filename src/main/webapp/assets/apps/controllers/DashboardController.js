var dashModule  = angular.module('MetronicApp');
//当页面中追加元素时，若元素中含有angularjs元素，则需要compile才能生效
angular.module('MetronicApp')
.directive('compile', function ($compile) {
    return function (scope, element, attrs) {
        scope.$watch(
          function (scope) {
               
              return scope.$eval(attrs.compile);
          },
          function (value) {
              element.html(value);
              $compile(element.contents())(scope);
          }
        );
    };
});

dashModule.controller('DashboardController', function($rootScope, $scope, $state, $http, $timeout) {
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
				}else{
					workflowName = "未命名";
				}
				
				map['template'] = "<li><div class='col-md-9'>" +
						"<a ui-sref='"+workflowType+"({tabHref:1})'>" +//tabHref:1将tab指向“待办列表”
								"<span>"+workflowName+"："+title+"</span></a></div>" +
						"<div class='col-md-3'><div class='date'>" + timeStamp2String(creatTime) + "</div></div></li>";
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
				}else{
					workflowName = "未命名";
				}
				map['template'] = "<li><div class='col-md-9'>" +
						"<a ui-sref='"+workflowType+"({tabHref:2})'>" +//tabHref:1将tab指向“已办列表”
						"<span>"+workflowName+"："+title+"</span></a></div>" +
						"<div class='col-md-3'><div class='date'>" + timeStamp2String(endTime) + "</div></div></li>";
				list.push(map);
			}
		}else{
			var map = {};
			map['template'] = "<div>无已办事项！</div>"
				list.push(map);
		}
		$scope.ybItems = list;
     });
	

});

function todo() {
	window.location.href=ctx + "/rest/page/addVacation";
	 // $state.go("rest/page/addVacation");
};