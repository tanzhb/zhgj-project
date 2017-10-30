/* Setup general page controller */
angular.module('MetronicApp').controller('statementController', ['$rootScope', '$scope', 'settings','statementService','$filter',
    '$state',"$stateParams",'$compile','$location', function($rootScope, $scope, settings,statementService,$filter,$state,$stateParams,$compile,$location) {
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
        	if($state.current.name=="buyStatement"){      
	        	
	        	loadBuyMainTable();// 加载采购商对账单列表
	        	//var type = handle.getCookie("d_type");
	 			/*if(type=="buyStatement"){
		 				$('#statement_tab a:last').parent().addClass('active');
		 				$('#statement_tab a:first').parent().removeClass('active');
		 				$("#tab_15_2").addClass("active");
		 				$("#tab_15_1").removeClass("active");
		 		}else{
		 				$('#statement_tab a:first').parent().addClass('active');
		 				$('#statement_tab a:last').parent().removeClass('active');
		 				$("#tab_15_1").addClass("active");
		 				$("#tab_15_2").removeClass("active");
		 		}*/
        	}else if($state.current.name=="supplyStatement"){
        		loadSupplyMainTable();// 加载供应商对账单列表
        	}else if($state.current.name=="addBuyStatement"){
        		showdatepicker();
        		supply_validateInit();
        		initCustomers();    
        	}else if($state.current.name=="addSupplyStatement"){
        		showdatepicker();
        		buy_validateInit();// 加载表单验证控件
        		initSuppliers();
        	}else if($state.current.name=="viewBuyStatement"){
        		$scope.getStatement($stateParams.serialNum);
        	}else if($state.current.name=="viewSupplyStatement"){
        		$scope.getStatement($stateParams.serialNum);
            	/*$scope.opration = {};
            	$scope.serialNums = [];
            	// 加载数据
            	if($stateParams.serialNum){
            		$scope.opration = '修改';
            		$scope.getStatement($stateParams.serialNum)
            	}else{
            		$scope.opration = '新增';
            		
            		$scope.statementMateriel={};
            	}
            	$scope.noShow = true;
            	if($stateParams.view==1){// 对账单切换为查看
            		$scope.statementInput = true;
    		    	$scope.statementShow = true;
       		    	$scope.opration = '查看';
    		    }*/
            	
            	
        	}
    });
    
    var showdatepicker = function() {  
        $("#statementDate").datepicker({       
            orientation: "auto bottom",
			autoclose: true,
			language: "zh-CN"
        }).on('hide', function(ev){
    	  $('#statementDate').datepicker('setDate', handle.getLastMonthDay(ev.date));
    	  $scope.getOrderRecords();
    });  
    }  
    
    $scope.createSelect = function(c_id){
    	var obj = $('.'+c_id);
    	var _select = $compile(obj)($scope);
    	var select = $(_select).selectpicker();
    }
    
    /**
	 * 加载供应商数据
	 */
	var initSuppliers = function(){
	var promise = statementService.initSuppliers();
	promise.then(function(data){
		$scope.suppliers = data.data;
	},function(data){
		//调用承诺接口reject();
	});
	}
	
	var initCustomers = function(){
		var promise = statementService.initCustomers();
		promise.then(function(data){
			$scope.customers = data.data;
		},function(data){
			//调用承诺接口reject();
		});
	}
    
	$scope.getOrderRecords = function(){
		var statementDate = $scope.statement.statementDate;
		var supplyComId = $scope.statement.supplyComId;
		var buyComId = $scope.statement.buyComId;
		var comId='';
		if(!isNull(supplyComId)){
			comId = supplyComId;
		}else{
			supplyComId = null;
		}
		if(!isNull(buyComId)){
			comId = buyComId;
		}else{
			buyComId = null;
		}
		console.log(statementDate+"------------->"+comId);
		if(!isNull(statementDate)&&!isNull(comId)){
			//statementService.getOrderAndPaymentRecords(supplyComId,statementDate);
			statementService.getOrderAndPaymentRecords(supplyComId,buyComId,statementDate).then(
         		     function(data){//加载页面对象
         		    	 if(data.data.isExist==true){
         		    		 toastr.warning("对账单已存在");
         		    		$scope.orderList=[];
             		    	$scope.paymentList=[];
             		    	$scope.alreadyPaymentList=[];
             		    	$scope.statement.beginShouldPay=0;
             		    	getTotal();
         		    	 }else{
         		    		$scope.orderList=data.data.orderList;
             		    	$scope.paymentList=data.data.paymentList;
             		    	$scope.alreadyPaymentList=data.data.alreadyPaymentList;
             		    	$scope.statement.beginShouldPay=data.data.endShouldPay;
             		    	getTotal();
         		    	 }
         		     },
         		     function(error){
         		         $scope.error = error;
         		     }
         		 );
		}else{
			
		}
	}
	
	var getTotal = function(){
			
		    getOrderData();
	    	$scope.statement.nowShouldPay = $scope.paymentMoney + $scope.statement.beginShouldPay;
	    	$scope.statement.nowAlreadyPay = $scope.totalPaymentAmount;
	    	$scope.statement.endShouldPay = $scope.totalUnPaymentMoney + $scope.statement.beginShouldPay;
	    	$scope.statement.overTimeAmout = 0;
	    	$scope.statement.serviceAmount = 0;
	}
	
	var getOrderData = function(){
		
		$scope.totalMoney=0;
    	$scope.paymentMoney=0;
    	$scope.totalPaymentAmount=0;
    	$scope.totalUnPaymentMoney=0;
    	$scope.overDueMoney=0;
    	$scope.totalServiceMoney=0;
    	$scope.totalReadyAmount=0;
    	$scope.totalUnReadyAmount=0;
    	$scope.paymentTotal=0;
    	$scope.alreadyPaymentTotal=0;
    	for(var i in $scope.orderList){
    		$scope.totalMoney += Number($scope.orderList[i].totalMoney);
    		$scope.paymentMoney += Number($scope.orderList[i].paymentMoney);
    		$scope.totalPaymentAmount += Number($scope.orderList[i].totalPaymentAmount);
    		$scope.totalUnPaymentMoney += Number($scope.orderList[i].totalUnPaymentMoney);
    		$scope.overDueMoney += Number($scope.orderList[i].overDueMoney);
    		$scope.totalServiceMoney += Number($scope.orderList[i].totalServiceMoney);
    		$scope.totalReadyAmount += Number($scope.orderList[i].totalReadyAmount);
    		$scope.totalUnReadyAmount += Number($scope.orderList[i].totalUnReadyAmount);
    	}
    	
    	for(var i in $scope.paymentList){
    		$scope.paymentTotal += Number($scope.paymentList[i].paymentAmount);
    	}
    	for(var i in $scope.alreadyPaymentList){
    		$scope.alreadyPaymentTotal += Number($scope.alreadyPaymentList[i].paymentAmount);
    	}
	}
   
	//供应商对账单
    $scope.saveSupplyStatement  = function() {
    	if($('#form_sample_1').valid()){
    		$scope.statement.paymentAmount = $scope.statement.nowAlreadyPay;
    		$scope.statement.totalAmount = $scope.statement.nowAlreadyPay;
    		$scope.statement.deliveryAmount = $scope.statement.nowAlreadyPay;
    		statementService.save($scope.statement).then(
       		     function(data){
       		    	toastr.success('数据保存成功！');
       		    	$state.go("supplyStatement");
       		    	//$location.search({serialNum:data.serialNum,view:1});
       		    	//$scope.statementInput = true;
       			    //$scope.statementShow = true;
       		     },
       		     function(error){
       		         $scope.error = error;
       		         toastr.error('数据保存出错！');
       		     }
       		 );
    	}
    }; 	
    
    $scope.cancelSupplyStatement  = function() {// 取消编辑
    	if($scope.statement.serialNum==null || $scope.statement.serialNum=='') {// 如果是取消新增，返回列表页面
    		$state.go("supplyStatement");
    		return;
		}
    	$scope.getStatement($scope.statement.serialNum);
    	$scope.cancelStatement();
    	
    };
    
    //采购商对账单
    $scope.saveBuyStatement  = function() {
    	if($('#form_sample_2').valid()){
    		$scope.statement.paymentAmount = $scope.statement.nowAlreadyPay;
    		$scope.statement.totalAmount = $scope.statement.nowAlreadyPay;
    		$scope.statement.deliveryAmount = $scope.statement.nowAlreadyPay;
    		statementService.save($scope.statement).then(
    				function(data){
    					toastr.success('数据保存成功！');
    					$state.go("buyStatement");
    					//$location.search({serialNum:data.serialNum,view:1});
    					//$scope.statementInput = true;
    					//$scope.statementShow = true;
    				},
    				function(error){
    					$scope.error = error;
    					toastr.error('数据保存出错！');
    				}
    		);
    	}
    }; 	
    
    $scope.cancelBuyStatement  = function() {// 取消编辑
    	if($scope.statement.serialNum==null || $scope.statement.serialNum=='') {// 如果是取消新增，返回列表页面
    		$state.go("buyStatement");
    		return;
    	}
    	$scope.getStatement($scope.statement.serialNum);
    	$scope.cancelStatement();
    	
    };
    
    $scope.viewSupplyStatement = function(serialNum){
    	$state.go("viewSupplyStatement",{serialNum:serialNum});
    }
    
    $scope.viewBuyStatement = function(serialNum){
    	$state.go("viewBuyStatement",{serialNum:serialNum});
    }
    
    var buyTable;
    var loadBuyMainTable = function() {
            a = 0;
            App.getViewPort().width < App.getResponsiveBreakpoint("md") ? $(".page-header").hasClass("page-header-fixed-mobile") && (a = $(".page-header").outerHeight(!0)) : $(".page-header").hasClass("navbar-fixed-top") ? a = $(".page-header").outerHeight(!0) : $("body").hasClass("page-header-fixed") && (a = 64);
            buyTable = $("#sample_2")
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
                order: [[1, "desc"]],// 默认排序列及排序方式
                ordering:  true,//是否排序
                lengthMenu: [[5, 10, 15, 30, -1], [5, 10, 15, 30, "All"]],
                pageLength: 5,// 每页显示数量
                processing: true,// loading等待框
// serverSide: true,
                ajax: 'rest/statement/findStatementList?type=buy',// 加载数据中
                "aoColumns": [
                              { mData: 'serialNum' },
                              { mData: 'statementNum' },
                              { mData: 'statementDate' },
                              { mData: 'buyName' },
                              { mData: 'beginShouldPay' },
                              { mData: 'nowShouldPay' },
                              { mData: 'nowAlreadyPay' },
                              { mData: 'endShouldPay' },
                              { mData: 'overTimeAmout' },
                              { mData: 'serviceAmount' },
                              { mData: 'status' }
                        ],
               'aoColumnDefs' : [ {
							'targets' : 0,
							'searchable' : false,
							'orderable' : false,
							'className' : 'dt-body-center',
							'render' : function(data,
									type, full, meta) {
//								return '<input type="checkbox" id="'+data+'" ng-click="getStatement_(\''+data+'\')" name="serialNum[]" value="'
//													+ $('<div/>')
//													.text(
//															data)
//													.html()
//											+ '">';
								return '<label class="mt-checkbox mt-checkbox-single mt-checkbox-outline">'+
                                '<input type="checkbox" data-check="false" class="checkboxes"  id="'+data+'" value="'+data+'" data-set="#sample_2 .checkboxes" />'+
                                '<span></span></label>';
							},
							"createdCell": function (td, cellData, rowData, row, col) {
								 $compile(td)($scope);
						       }
						},{
							'targets' : 1,
							'render' : function(data,
									type, row, meta) {
								return '<a href="javascript:void(0);" ng-click="viewBuyStatement(\''+row.serialNum+'\')">'+data+'</a>';
							},
							"createdCell": function (td, cellData, rowData, row, col) {
								 $compile(td)($scope);
						       }
						},{
							'targets' : 4,
							'render' : function(data,
									type, row, meta) {
								return $filter('currency')(data,'￥');
							}
						},{
							'targets' : 5,
							'render' : function(data,
									type, row, meta) {
								return $filter('currency')(data,'￥');
							}
						},{
							'targets' : 6,
							'render' : function(data,
									type, row, meta) {
								return $filter('currency')(data,'￥');
							}
						},{
							'targets' : 7,
							'render' : function(data,
									type, row, meta) {
								return $filter('currency')(data,'￥');
							}
						},{
							'targets' : 8,
							'render' : function(data,
									type, row, meta) {
								return $filter('currency')(0,'￥');
							}
						},{
							'targets' : 9,
							'render' : function(data,
									type, row, meta) {
								return $filter('currency')(0,'￥');
							}
						},{
							'targets' : 10,
							'render' : function(data,
									type, row, meta) {
								return '待接收';
							}
						}  ]

            }).on('statement.dt',
            function() {
                console.log('排序');
            })
            
             $("#sample_2").find(".group-checkable").change(function() {
	            var e = jQuery(this).attr("data-set"),
	            t = jQuery(this).is(":checked");
	            jQuery(e).each(function() {
	                t ? ($(this).prop("checked", !0), $(this).parents("tr").addClass("active")) : ($(this).prop("checked", !1), $(this).parents("tr").removeClass("active"))
	            })
	        }),
	        $("#sample_2").on("change", "tbody tr .checkboxes",
	        function() {
	            $(this).parents("tr").toggleClass("active")
	        });
            
            
            // 添加checkbox功能***************************************
			// Handle click on "Select all" control
			$('#example-select-buy').on(
					'click',
					function() {
						// Check/uncheck all checkboxes in the
						// buyTable
						var rows = buyTable.rows({
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
											'#example-select-buy')
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
        
        
        var supplyTable;
        var loadSupplyMainTable = function() {
                a = 0;
                App.getViewPort().width < App.getResponsiveBreakpoint("md") ? $(".page-header").hasClass("page-header-fixed-mobile") && (a = $(".page-header").outerHeight(!0)) : $(".page-header").hasClass("navbar-fixed-top") ? a = $(".page-header").outerHeight(!0) : $("body").hasClass("page-header-fixed") && (a = 64);
                supplyTable = $("#sample_1")
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
                    order: [[1, "desc"]],// 默认排序列及排序方式
                    ordering:  true,//是否排序
                    searching: true,// 是否过滤检索
                    lengthMenu: [[5, 10, 15, 30, -1], [5, 10, 15, 30, "All"]],
                    pageLength: 5,// 每页显示数量
                    processing: true,// loading等待框
    // serverSide: true,
                    ajax: 'rest/statement/findStatementList?type=supply',// 加载数据中
                    "aoColumns": [
                                  { mData: 'serialNum' },
                              { mData: 'statementNum' },
                              { mData: 'statementDate' },
                              { mData: 'supplyName' },
                              { mData: 'beginShouldPay' },
                              { mData: 'nowShouldPay' },
                              { mData: 'nowAlreadyPay' },
                              { mData: 'endShouldPay' },
                              { mData: 'overTimeAmout' },
                              { mData: 'serviceAmount' },
                              { mData: 'status' }
                            ],
                   'aoColumnDefs' : [ {
    							'targets' : 0,
    							'searchable' : false,
    							'orderable' : false,
    							'className' : 'dt-body-center',
    							'render' : function(data,
    									type, full, meta) {
//    								return '<input type="checkbox" id="'+data+'" ng-click="getStatement_(\''+data+'\')" name="serialNum[]" value="'
//    													+ $('<div/>')
//    													.text(
//    															data)
//    													.html()
//    											+ '">';
    								return '<label class="mt-checkbox mt-checkbox-single mt-checkbox-outline">'+
                                    '<input type="checkbox" data-check="false" class="checkboxes"  id="'+data+'" value="'+data+'" data-set="#sample_1 .checkboxes" />'+
                                    '<span></span></label>';
    							},
    							"createdCell": function (td, cellData, rowData, row, col) {
    								 $compile(td)($scope);
    						       }
    						},{
    							'targets' : 1,
    							'render' : function(data,
    									type, row, meta) {
    								return '<a href="javascript:void(0);" ng-click="viewSupplyStatement(\''+row.serialNum+'\')">'+data+'</a>';
    							},
    							"createdCell": function (td, cellData, rowData, row, col) {
    								 $compile(td)($scope);
    						       }
    						},{
    							'targets' : 4,
    							'render' : function(data,
    									type, row, meta) {
    								return $filter('currency')(data,'￥');
    							}
    						},{
    							'targets' : 5,
    							'render' : function(data,
    									type, row, meta) {
    								return $filter('currency')(data,'￥');
    							}
    						},{
    							'targets' : 6,
    							'render' : function(data,
    									type, row, meta) {
    								return $filter('currency')(data,'￥');
    							}
    						},{
    							'targets' : 7,
    							'render' : function(data,
    									type, row, meta) {
    								return $filter('currency')(data,'￥');
    								}
    						},{
    							'targets' : 8,
    							'render' : function(data,
    									type, row, meta) {
    								return $filter('currency')(0,'￥');
    							}
    						},{
    							'targets' : 9,
    							'render' : function(data,
    									type, row, meta) {
    								return $filter('currency')(0,'￥');
    							}
    						},{
    							'targets' : 10,
    							'render' : function(data,
    									type, row, meta) {
    								return "待接收";
    							}
    						}]

                }).on('statement.dt',
                function() {
                    console.log('排序');
                })
                
                 $("#sample_1").find(".group-checkable").change(function() {
		            var e = jQuery(this).attr("data-set"),
		            t = jQuery(this).is(":checked");
		            jQuery(e).each(function() {
		                t ? ($(this).prop("checked", !0), $(this).parents("tr").addClass("active")) : ($(this).prop("checked", !1), $(this).parents("tr").removeClass("active"))
		            })
		        }),
		        $("#sample_1").on("change", "tbody tr .checkboxes",
		        function() {
		            $(this).parents("tr").toggleClass("active")
		        });
                
                
                // 添加checkbox功能***************************************
    			// Handle click on "Select all" control
    			$('#example-select-supply').on(
    					'click',
    					function() {
    						// Check/uncheck all checkboxes in the
    						// supplyTable
    						var rows = supplyTable.rows({
    							'search' : 'applied'
    						}).nodes();
    						$('input[type="checkbox"]', rows).prop(
    								'checked', this.checked);
    					});
    	
    			// Handle click on checkbox to set state of "Select
    			// all" control
    			$('#sample_1 tbody')
    					.on(
    							'change',
    							'input[type="checkbox"]',
    							function() {
    								// If checkbox is not checked
    								if (!this.checked) {
    									var el = $(
    											'#example-select-supply')
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
        // 弹出确认删除模态框
        $scope.deleteSupplyStatement = function() {
			var ids = '';
			$scope.deleteType = 'supply';
			// Iterate over all checkboxes in the supplyTable
			supplyTable.$('input[type="checkbox"]').each(
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
    			toastr.warning('未选择对账单！');return;
    		}else{
    			$('#delStatementModal').modal('show');// 弹出删除确认模态框
    		}
			
		};
		
		
        // 弹出确认删除模态框
        $scope.deleteBuyStatement = function() {
			var ids = '';
			$scope.deleteType = 'buy';
			// Iterate over all checkboxes in the supplyTable
			buyTable.$('input[type="checkbox"]').each(
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
    			toastr.warning('未选择对账单！');return;
    		}else{
    			$('#delStatementModal').modal('show');// 弹出删除确认模态框
    		}
			
		};
		
		$scope.editStatement  = function() {// 进入编辑页面
        	var ids = '';
    		// Iterate over all checkboxes in the supplyTable
    		supplyTable.$('input[type="checkbox"]').each(
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
    			toastr.warning('请选择一个对账单！');return;
    		}else if(ids=='more'){
    			toastr.warning('只能选择一个对账单！');return;
    		}
    		
    		$state.go("addStatement",{serialNum:ids});
        };
        
     // 删除开始***************************************
		$scope.del = function() {
			var ids = '';
			if($scope.deleteType=='supply'){
				// Iterate over all checkboxes in the supplyTable
				supplyTable.$('input[type="checkbox"]').each(
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
			}else{
				// Iterate over all checkboxes in the buyTable
				buyTable.$('input[type="checkbox"]').each(
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
			}
			
			statementService
					.delStatement(ids)
					.then(
							function(data) {
								$('#delStatementModal').modal(
										'hide');// 删除成功后关闭模态框
								$(".modal-backdrop").remove();
								/* supplyTable.ajax.reload(); // 重新加载datatables数据 */
								toastr.success('数据删除成功！');
								if($scope.deleteType=='supply'){
									supplyTable.ajax.reload();
								}else{
									buyTable.ajax.reload();
								}
							},
							function(errResponse) {
								toastr.error('数据删除失败！');
								console
										.error('Error while deleting Users');
							}

					);
		};
		// 删除结束***************************************
        
		
		$('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
	        // 获取已激活的标签页的名称
	        var activeTab = $(e.target).text(); 
	        // 获取前一个激活的标签页的名称
	       // var previousTab = $(e.relatedTarget).text(); 
	        var absurl = $location.absUrl();
//	        $("#tip").text(activeTab);
	        if(activeTab=="客户对账单"){
	        	handle.addCookie("d_type","buyStatement",24);
	        }else{
	        	handle.addCookie("d_type","supplyStatement",24);
	        }
	     });
		
		var buy_validateInit = function() {
        	var e = $("#form_sample_1"),
	        r = $(".alert-danger", e),
	        i = $(".alert-success", e);
	        e.validate({
	            errorElement: "span",
	            errorClass: "help-block help-block-error",
	            focusInvalid: !1,
	            ignore: "",
	            messages: {
	            	statementNum:{required:"对账单号不能为空！"},
	            	supplyComId:{required:"供应商不能为空！"},
	            	statementDate:{required:"对账日期不能为空！"},
	            	maker:{required:"制单人不能为空！"},
	            	makeDate:{required:"制单日期不能为空！"}
	            },
            	rules: {statementNum: {required: !0,maxlength: 20},
            		supplyComId: {required: !0,maxlength: 20},
            		statementDate: {required: !0,maxlength: 20},
            		maker: {required: !0,maxlength: 20},
            		makeDate: {required: !0,maxlength: 20}
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
        
        var supply_validateInit = function() {
        	var e = $("#form_sample_2"),
        	r = $(".alert-danger", e),
        	i = $(".alert-success", e);
        	e.validate({
        		errorElement: "span",
        		errorClass: "help-block help-block-error",
        		focusInvalid: !1,
        		ignore: "",
        		messages: {
        			statementNum:{required:"对账单号不能为空！"},
        			buyComId:{required:"采购商不能为空！"},
        			statementDate:{required:"对账日期不能为空！"},
        			maker:{required:"制单人不能为空！"},
        			makeDate:{required:"制单日期不能为空！"}
        		},
        		rules: {statementNum: {required: !0,maxlength: 20},
        			buyComId: {required: !0,maxlength: 40},
        			statementDate: {required: !0,maxlength: 20},
        			maker: {required: !0,maxlength: 20},
        			makeDate: {required: !0,maxlength: 20}
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
		 * 获取对账单信息
		 */	
        $scope.getStatement  = function(serialNum) {
        	statementService.getStatement(serialNum).then(
          		     function(data){//加载页面对象
          		    	$scope.statement=data.statement;
          		    	$scope.orderList=data.orderList;
         		    	$scope.paymentList=data.paymentList;
         		    	$scope.alreadyPaymentList=data.alreadyPaymentList;
         		    	getOrderData();
          		     },
          		     function(error){
          		         $scope.error = error;
          		     }
          		 );
        	
        }; 
		    
		    //********导入导出start****************//
	 	      /**
		        * 下载EXCEL模板
		        */
		       $scope.downloadImportTemp = function(){
		    	   window.location.href=$rootScope.basePath+"/rest/statement/downloadImportTemp?type=buy";
		       }
		       
		       /**
		        * 上传EXCEL
		        */
		       $scope.uploadExcel = function(){
		    	    var file = document.querySelector('input[type=file]').files[0];
		    	    if(handle.isNull(file)){
		    	    	toastr.warning("请选择Excel文件！");
		    	    }
		    	    console.log(file.name);
		    	    var type = file.name.substring(file.name.lastIndexOf("."));
		    	   if(type != ".xls"){
		    		   toastr.warning("文件格式不正确，需要xls类型的Excel文档");
		    		   return;
		    	   }
		    	   	handle.blockUI("正在导入中，请不要进行其他操作"); 
		    	   	var promise = statementService.buyUploadExcel();
	       			promise.then(function(data){
	       				handle.unblockUI(); 
	       				if(data.data.data=="success"){
	       					toastr.success("导入成功");
	       					supplyTable.ajax.reload();
	       				}else{
	       					toastr.error(data.data.data);
	       				}
	       				$('#import').modal('hide'); 
		            },function(data){
		               //调用承诺接口reject();
		            	toastr.error("操作失败");
		            	$('#import').modal('hide'); 
		            });
		    	   
		       }
		       $('#import').on('hide.bs.modal', function (e) { 
		    	   $("#resetFile").trigger("click");
		       })
		       
		       
		       $scope.exportStatement = function(type){
			    	 handle.blockUI("正在导出数据，请稍后"); 
			    	 window.location.href=$rootScope.basePath+"/rest/statement/exportStatement?type="+type;
			    	 handle.unblockUI(); 
			   }
		       //********导入导出end****************//
    	 
}]);
