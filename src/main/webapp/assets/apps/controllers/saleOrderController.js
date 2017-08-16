/* Setup general page controller */
angular.module('MetronicApp').controller('saleOrderController', ['$rootScope', '$scope', 'settings','saleOrderService',
    '$state',"$stateParams",'$compile','$location', function($rootScope, $scope, settings,saleOrderService,$state,$stateParams,$compile,$location) {
    $scope.$on('$viewContentLoaded', function() {   
    	// initialize core components
    	App.initAjax();

    	// set default layout mode
    	$rootScope.settings.layout.pageContentWhite = true;
        $rootScope.settings.layout.pageBodySolid = false;
        $rootScope.settings.layout.pageSidebarClosed = false;
        if($state.current.name=="saleOrder"){
        	loadMainTable();//加载订单列表
        	}else{
            	$('.date-picker').datepicker({
    				rtl: App.isRTL(),
    				orientation: "left",
    				autoclose: true,
    				dateFormat:"yyyy-mm-dd",
    				language: "zh-CN"
            	})
            	//初始化日期控件
            	     	
            	$scope.opration = {};
            	//加载数据
            	if($stateParams.serialNum){
            		$scope.opration = '修改';
            		$scope.getSaleOrderInfo($stateParams.serialNum)
            	}else{
            		$scope.opration = '新增';
            	}
            	
            	if($stateParams.view==1){//切换为查看
            		$scope.saleOrderInput = true;
    		    	$scope.saleOrderShow = true;
       		    	$scope.opration = '查看';
    		    }
            	
            	validateInit();//加载表单验证控件 	 
            
        	}
    });
    
    $scope.save  = function() {
    	if($('#form_sample_1').valid()){
    		if($scope.saleOrder.orderDate=='') {//日期为空的处理
    			$scope.saleOrder.orderDate=null;
    		}

    		//保存数据处理
//    		$scope.saleOrder.parentSaleOrder=null;
//    		$scope.saleOrder.createTime=null;
//    		$scope.saleOrder.updateTime=null;
    		//**********//

    		saleOrderService.save($scope.saleOrder).then(
       		     function(data){
       		    	toastr.success('数据保存成功！');
       		    	$location.search({serialNum:data.serialNum,view:1});
       		    	$scope.saleOrderInput = true;
       			    $scope.saleOrderShow = true;
       		     },
       		     function(error){
       		         $scope.error = error;
       		         toastr.error('数据保存出错！');
       		     }
       		 );
    	}
    	
    }; 	
    
    $scope.cancel  = function() {//取消编辑
    	if($scope.saleOrder.serialNum==null || $scope.saleOrder.serialNum=='') {//如果是取消新增，返回列表页面
    		$state.go("saleOrder");
    		return;
		}
    	$scope.getSaleOrderInfo($scope.saleOrder.serialNum);
    	$scope.saleOrderInput = true;
	    $scope.saleOrderShow = true;
    };
    
    $scope.edit  = function() {//进入编辑
    	$scope.saleOrderInput = false;
	    $scope.saleOrderShow = false;
    };
    
    var table;
    var tableAjaxUrl = "rest/order/findSaleOrderList";
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
                    //infoFiltered: "(filtered1 from _MAX_ total entries)",
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
/*                fixedHeader: {//固定表头、表底
                    header: !0,
                    footer: !0,
                    headerOffset: a
                },*/
                order: [[1, "asc"]],//默认排序列及排序方式
                searching: true,//是否过滤检索
                ordering:  true,//是否排序
                lengthMenu: [[5, 10, 15, 30, -1], [5, 10, 15, 30, "All"]],
                pageLength: 5,//每页显示数量
                processing: true,//loading等待框
//                serverSide: true,
                ajax: tableAjaxUrl,//加载数据中
                "aoColumns": [
                              { mData: 'serialNum' },
                              { mData: 'orderNum' },
                              { mData: 'buyComId' },
                              { mData: null },
                              { mData: null },
                              { mData: 'deliveryMode' },
                              { mData: 'serviceModel' },
                              { mData: null },
                              { mData: null },
                              { mData: 'orderDate' }

                        ],
               'aoColumnDefs' : [ {
							'targets' : 0,
							'searchable' : false,
							'orderable' : false,
							'render' : function(data,
									type, full, meta) {
								return '<input type="checkbox" id="'+data+'" ng-click="getSaleOrderInfo_(\''+data+'\')" name="serialNum[]" value="'
													+ $('<div/>')
													.text(
															data)
													.html()
											+ '">';
							},
							"createdCell": function (td, cellData, rowData, row, col) {
								 $compile(td)($scope);
						       }
						} ]

            }).on('order.dt',
            function() {
                console.log('排序');
            })
            
            
            
            
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
        };
        
        
        
        //弹出确认删除模态框
        $scope.deleteSaleOrder = function() {
			var ids = '';
			// Iterate over all checkboxes in the table
			table.$('input[type="checkbox"]').each(
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
			if(ids==''){
    			toastr.warning('未选择订单！');return;
    		}else{
    			$('#delSaleOrderModal').modal('show');// 弹出删除确认模态框
    		}
			
		};
		
		$scope.editSaleOrder  = function() {//进入编辑页面
        	var ids = '';
    		// Iterate over all checkboxes in the table
    		table.$('input[type="checkbox"]').each(
    				function() {
    					// If checkbox exist in DOM
    					if ($.contains(document, this)) {
    						// If checkbox is checked
    						if (this.checked) {
    							// 将选中数据id放入ids中
    							if (ids == '') {
    								ids = this.value;
    							} else{
    								ids = "more"
    							}
    						}
    					}
    				});
    		if(ids==''){
    			toastr.warning('请选择一个订单！');return;
    		}else if(ids=='more'){
    			toastr.warning('只能选择一个订单！');return;
    		}
    		
    		$state.go("addSaleOrder",{serialNum:ids});
        };
        
     // 删除开始***************************************
		$scope.del = function() {
			var ids = '';
			// Iterate over all checkboxes in the table
			table.$('input[type="checkbox"]').each(
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
			saleOrderService
					.delSaleOrder(ids)
					.then(
							function(data) {
								$('#delSaleOrderModal').modal(
										'hide');// 删除成功后关闭模态框
								$(".modal-backdrop").remove();
								/*table.ajax.reload(); // 重新加载datatables数据*/
								toastr.success('数据删除成功！');
								 $state.go('saleOrder',{},{reload:true});
								 
							},
							function(errResponse) {
								toastr.error('数据删除失败！');
								console
										.error('Error while deleting Users');
							}

					);
		};
		// 删除结束***************************************
        
		
		
		var validateInit = function() {
        	var e = $("#form_sample_1"),
	        r = $(".alert-danger", e),
	        i = $(".alert-success", e);
	        e.validate({
	            errorElement: "span",
	            errorClass: "help-block help-block-error",
	            focusInvalid: !1,
	            ignore: "",
	            messages: {
	            	orderNum:{required:"销售订单号不能为空！"},
	            	orderType:{required:"销售类型不能为空！"},
	            	buyComId:{required:"采购方不能为空！"},
	            	serviceModel:{required:"服务模式不能为空！"},
	            	settlementClause:{required:"结算条款不能为空！"},
	            	deliveryMode:{required:"提货方式不能为空！"}
            		
	            },
            	rules: {orderNum: {required: !0,maxlength: 20},
            		orderType: {required: !0,maxlength: 20},
            		buyComId: {required: !0,maxlength: 20},
            		serviceModel: {required: !0,maxlength: 20},
            		settlementClause: {required: !0,maxlength: 20},
            		deliveryMode: {required: !0,maxlength: 20}
            			},
            		invalidHandler: function(e, t) {
                    i.hide(), r.show(), App.scrollTo(r, -200)
                },
	            invalidHandler: function(e, t) {
	                i.hide(),
	                r.show(),
	                App.scrollTo(r, -200)
	            },
	            errorPlacement: function(e, r) {
	                r.is(":checkbox") ? e.insertAfter(r.closest(".md-checkbox-list, .md-checkbox-inline, .checkbox-list, .checkbox-inline")) : r.is(":radio") ? e.insertAfter(r.closest(".md-radio-list, .md-radio-inline, .radio-list,.radio-inline")) : e.insertAfter(r)
	            },
	            highlight: function(e) {
	                $(e).closest(".form-group").addClass("has-error")
	            },
	            unhighlight: function(e) {
	                $(e).closest(".form-group").removeClass("has-error")
	            },
	            success: function(e) {
	                e.closest(".form-group").removeClass("has-error")
	            },
	            submitHandler: function(e) {
	                i.show(),
	                r.hide()
	            }})
        };
        
        
        /**
         * 获取订单信息
         */	
        $scope.getSaleOrderInfo  = function(serialNum) {
        	saleOrderService.getSaleOrderInfo(serialNum).then(
          		     function(data){
          		    	$scope.saleOrder=data.orderInfo;
          		     },
          		     function(error){
          		         $scope.error = error;
          		     }
          		 );
        	
        }; 
}]);
