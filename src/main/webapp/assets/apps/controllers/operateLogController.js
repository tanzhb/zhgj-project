/* Setup general page controller */
angular.module('MetronicApp').controller('operateLogController', ['$rootScope', '$scope', 'settings','$filter',
    '$state',"$stateParams",'$compile','$location', function($rootScope, $scope, settings,$filter,$state,$stateParams,$compile,$location) {
    $scope.$on('$viewContentLoaded', function() {   
    	// initialize core components
    	App.initAjax();
    	handle = new pageHandle();
    	// $('[data-toggle="tooltip"]').tooltip();
    	// set default layout mode
    	$rootScope.settings.layout.pageContentWhite = true;
        $rootScope.settings.layout.pageBodySolid = false;
        $rootScope.settings.layout.pageSidebarClosed = false;
        handle.datePickersInit("auto bottom");
        $scope.resetSearchForm();
        loadMainTable();// 加载采购商对账单列表
    });
    
    
    var table;
    var loadMainTable = function() {
            a = 0;
            App.getViewPort().width < App.getResponsiveBreakpoint("md") ? $(".page-header").hasClass("page-header-fixed-mobile") && (a = $(".page-header").outerHeight(!0)) : $(".page-header").hasClass("navbar-fixed-top") ? a = $(".page-header").outerHeight(!0) : $("body").hasClass("page-header-fixed") && (a = 64);
            table = $("#sample_2")
			.DataTable({
                language: {
                    aria: {
                        sortAscending: ": activate to sort column ascending",
                        sortDescending: ": activate to sort column descending"
                    },
                    emptyTable: "空表",
                    info: "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
                    infoEmpty: "没有数据",
                    // infoFiltered: "(filtered1 from _MAX_ total entries)",
                    lengthMenu: "每页显示 _MENU_ 条数据",
                    search: "查询:",
                    zeroRecords: "抱歉， 没有找到！",
                    paginate: {
                        "sFirst": "首页",
                        "sPrevious": "前一页",
                        "sNext": "后一页",
                        "sLast": "尾页"
                     }
                },
/*
 * fixedHeader: {//固定表头、表底 header: !0, footer: !0, headerOffset: a },
 */
                searching: true,// 是否过滤检索
                order: [[2, "desc"]],// 默认排序列及排序方式
                ordering:  true,//是否排序
                lengthMenu: [[5, 10, 15, 30, -1], [5, 10, 15, 30, "All"]],
                pageLength: 10,// 每页显示数量
                processing: true,// loading等待框
// serverSide: true,
                ajax: 'rest/operateLog/findOperateLogList?type=user&startTime='+$scope.startTime+"&endTime="+$scope.endTime,// 加载数据中
                ajax:{
             	   "url": "rest/operateLog/findOperateLogList",
             	   "type": "post",
             	   "contentType": "application/json",
             	   "data": function ( d ) {
             		   d.params = JSON.stringify($scope.params);
             	    }
                },
                "aoColumns": [
                              { mData: 'operationDesc' },
                              { mData: 'requestIp' },
                              {
									mData : 'operationTime',
        							mRender : function(
        									data) {
        								if (data != null) {
        									return timeStamp2String(data);
        								} else
        									return '';
        							}
							}
                        ]

            }).on('operateLog.dt',
            function() {
                console.log('排序');
            })

            

        };
	    function addDate(date, days) {
			var d = new Date(date);
			d.setDate(d.getDate() + days);
			/*var month = d.getMonth() + 1;
			var day = d.getDate();
			if (month < 10) {
				month = "0" + month;
			}
			if (day < 10) {
				day = "0" + day;
			}
			var val = d.getFullYear() + "" + month + ""
					+ day;*/
			return d;
		}

        /**
         * 搜索
         */
        $scope.search=function () {
        	$scope.params = {};
        	$scope.params.startTime = $scope.startTime;
        	$scope.params.endTime = $scope.endTime;
        	table.settings()[0].ajax.data.params =  JSON.stringify($scope.params);
        	table.ajax.reload();
        };  
        
        /**
         * 重置搜索条件
         */
        $scope.resetSearchForm = function (){
        	$scope.startTime  = timeStamp2ShortString(addDate(new Date(),-7));
        	$scope.endTime = timeStamp2ShortString(new Date());
        	$scope.params = {};
        	$scope.params.startTime = $scope.startTime;
        	$scope.params.endTime = $scope.endTime;
        };
		

					

    	 
}]);
