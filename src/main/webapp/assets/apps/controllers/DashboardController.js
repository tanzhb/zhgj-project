angular.module('MetronicApp').controller('DashboardController', function($rootScope, $scope, $state, $http, $timeout) {
    $scope.$on('$viewContentLoaded', function() {   
        // initialize core components
        App.initAjax();
    });

    // set sidebar closed and body solid layout mode
    $rootScope.settings.layout.pageContentWhite = true;
    $rootScope.settings.layout.pageBodySolid = false;
    $rootScope.settings.layout.pageSidebarClosed = false;
    
    daibanList();//加载待办列表
    yibanList();//加载已办列表
	
    $scope.todo = function () {
    	alert();
    	 // $state.go("rest/page/addVacation");
    };
	
//	$('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
//	      // 获取已激活的标签页的名称
//	      var activeTab = $(e.target).text(); 
//	      if(1==1){
//	    	  alert(1);
////	    	  daibanList();
//	      }else if(2==2){
//	    	  alert(2);
////	    	  yibanList();
//	      }
//	});
    
    
    //待办列表
    function daibanList(){
    	$.ajax({
        	url:ctx + "/rest/processAction/todoTask/",
    		type: 'get',
    		dataType: 'json',
    		success:function(result){	
    			var html = "";
    			if(result != null && result.data != null && result.data.length > 0){
    				for (var i=0;i<result.data.length;i++){
    					var title = result.data[i].title;
    					var creatTime = result.data[i].createTime;
    					var workflowType = result.data[i].businessType;
    					var workflowName = "";
    					if(workflowType == 'vacation'){
    						workflowName = "请假流程";
    					}
    					html += "<li><a href='javascript:;' ng-click=\"todo()\">" +
    							"<div class='col1'><div class='cont'><div class='cont-col1'><div class='label label-sm label-success'>" +
    							"<i class='fa fa-bell-o'></i></div></div><div class='cont-col2'><div class='desc'>" + workflowName + "：" + title + "</div>" +
    							"</div></div></div><div class='col2'><div class='date'>" + timeStamp2String2(creatTime) + "</div></div></a></li>";
    				}
    			}else{
    				html = "无待办事项！"
    			}
    			$("#daiban").html(html);
    		}
    	})
    }
    //已办列表
    function yibanList(){
    	$.ajax({
        	url:ctx + "/rest/processAction/endTask/",
    		type: 'get',
    		dataType: 'json',
    		success:function(result){	
    			var html = "";
    			if(result != null && result.data != null && result.data.length > 0){
    				for (var i=0;i<result.data.length;i++){
    					var title = result.data[i].title;
    					var endTime = result.data[i].endTime;
    					var workflowType = result.data[i].businessType;
    					var workflowName = "";
    					if(workflowType == 'vacation'){
    						workflowName = "请假流程";
    					}
    					html += "<li><div class='col1'><div class='cont'><div class='cont-col1'><div class='label label-sm label-success'>" +
    							"<i class='fa fa-bell-o'></i></div></div><div class='cont-col2'><div class='desc'>" + workflowName + "：" + title + "</div>" +
    							"</div></div></div><div class='col2'><div class='date'>" + timeStamp2String2(endTime) + "</div></div></li>";
    				}
    			}else{
    				html = "无已办事项！"
    			}
    			$("#yiban").html(html);
    		}
    	})
    }
	
	
    
});