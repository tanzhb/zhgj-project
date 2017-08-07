
    /* Setup blank page controller */
    angular.module('MetronicApp').controller('WarehouseController', ['$rootScope', '$scope','$http', '$state','settings', function($rootScope, $scope,$http, $state,settings) {
        $scope.$on('$viewContentLoaded', function() {  
        	debugger;
        	/*var record=$stateParams.record;*/
            // initialize core components
        	/* handle = new pageHandle();*/
            App.initAjax();
           /* $http.post('rest/warehouse/getWarehouseList').then(function(result){//加载仓库数据
                $scope.wareList = result.data.result;
            });*/
            
            
            //$scope.getRecord=$stateParams.getRecord;
            // set default layout mode
            $rootScope.settings.layout.pageContentWhite = true;
            $rootScope.settings.layout.pageBodySolid = false;
            $rootScope.settings.layout.pageSidebarClosed = false;
        });
     // 构建datatables开始***************************************
		var a = 0;
		debugger;
		App.getViewPort().width < App
				.getResponsiveBreakpoint("md") ? $(
				".page-header").hasClass(
				"page-header-fixed-mobile")
				&& (a = $(".page-header").outerHeight(!0))
				: $(".page-header").hasClass(
						"navbar-fixed-top") ? a = $(
						".page-header").outerHeight(!0)
						: $("body").hasClass(
								"page-header-fixed")
								&& (a = 64);

		var table = $("#sample_2")
				.DataTable(
						{
							language : {
								aria : {
									sortAscending : ": activate to sort column ascending",
									sortDescending : ": activate to sort column descending"
								},
								emptyTable : "空表",
								info : "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
								infoEmpty : "没有数据",
								infoFiltered : "(从 _MAX_ 条数据中检索)",
								lengthMenu : "每页显示 _MENU_ 条数据",
								search : "查询:",
								zeroRecords : "抱歉， 没有找到！",
								paginate : {
									"sFirst" : "首页",
									"sPrevious" : "前一页",
									"sNext" : "后一页",
									"sLast" : "尾页"
								}
							},
							fixedHeader : {// 固定表头、表底
								header : !0,
								footer : !0,
								headerOffset : a
							},
							// select: true,行多选
							order : [ [ 1, "asc" ] ],// 默认排序列及排序方式
							bRetrieve : true,
							// searching: true,//是否过滤检索
							// ordering: true,//是否排序
							
							lengthMenu : [
									[ 5, 10, 15, 30, -1 ],
									[ 5, 10, 15, 30, "All" ] ],
							pageLength : 10,// 每页显示数量
							processing : true,// loading等待框
							// serverSide: true,
						
							ajax : $rootScope.basePath
									+ "/rest/warehouse/getWarehouseList",// 加载数据中仓库表数据
							"aoColumns" : [
							{
								mData : 'serialNum'
							}, {
								mData : 'warehouseNum'
							}, {
								mData : 'warehouseName'
							}, {
								mData : 'address'
							}, {
								mData : 'area'
							}, {
								mData : 'warehouseName'
							}, {
								mData : 'warehouseType'
							}, {
								mData : 'owner'
							}  ],
							'aoColumnDefs' : [ {
								'targets' : 0,
								'searchable' : false,
								'orderable' : false,
								'className' : 'dt-body-center',
								'render' : function(data,
										type, full, meta) {
									return '<input type="checkbox" name="serialNum[]" value="'
											+ $('<div/>')
													.text(
															data)
													.html()
											+ '">';
								}
							} ],

						})
		// 构建datatables结束***************************************

		// 添加checkbox功能***************************************
		// Handle click on "Select all" control
		$('#example-select-all').on(
				'click',
				function() {
					// Check/uncheck all checkboxes in the
					// table
					var rows = table.rows({
						'search' : 'applied'
					}).nodes();
					$('input[type="checkbox"]', rows).prop(
							'checked', this.checked);
				});

		// Handle click on checkbox to set state of "Select
		// all" control
		$('#sample_2 tbody')
				.on(
						'change',
						'input[type="checkbox"]',
						function() {
							// If checkbox is not checked
							if (!this.checked) {
								var el = $(
										'#example-select-all')
										.get(0);
								// If "Select all" control
								// is checked and has
								// 'indeterminate' property
								if (el
										&& el.checked
										&& ('indeterminate' in el)) {
									// Set visual state of
									// "Select all" control
									// as 'indeterminate'
									el.indeterminate = true;
								}
							}
						});
		// 添加checkbox功能
		// ***************************************
         $scope.saveWarehouse=function(){
    	    $http.get("rest/warehouse/saveWarehouseInfo", {  
    			params:$scope.warehouse//传整个表单数据  
    		}).then(function success(result) {
    			debugger;
    			 $state.go('warehouse',{},{reload:true}); 
    	    }, function error(err) {
    	        
    	    });
    	 }
         $scope.editWarehouse=function(record){
        	 $state.go('addWarehouse',{},{reload:true}); 
    	 }
      
        
        $scope.deleteWarehouse=function(record){
    	    $http.get("rest/warehouse/deleteWarehouseInfo", {  
    			params:record//  
    		}).then(function success(result) {
    			 $state.go('warehouse',{},{reload:true}); 
    	    }, function error(err) {
    	        
    	    });
    	 }
        
        /**
 		 * 保存
 		 *//*
        $scope.saveWarehouse=function () { 
        	debugger;
        	handle.blockUI();
        	var promise = WarehouseService.saveWarehouse($scope.warehouse);
        	promise.then(function(data){
        		$(".modal-backdrop").remove();
        		handle.showMesssage("success","保存成功","提示");
        		handle.unblockUI();
        		 $state.go('warehouse',{},{reload:true}); 
            },function(data){
               //调用承诺接口reject();
         });
        	
        }; */
        
      /*  *//**
         * 编辑
         *//*
        $scope.editWarehouse=function (record) {
        	debugger;
        		var promise = WarehouseService.editWarehouse(record.serialNum);
        		promise.then(function(data){
        			 $("#basic").modal('show');
 	    			$scope.warehouse = data.data;
 	            },function(data){
 	               //调用承诺接口reject();
 	            });
        	
        };  */
    }]);
