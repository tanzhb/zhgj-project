/* Setup general page controller */
angular.module('MetronicApp').controller('buyOrderController', ['$rootScope', '$scope', 'settings','orderService','$filter',
    '$state',"$stateParams",'$compile','$location','materielService','FileUploader', function($rootScope, $scope, settings,orderService,$filter,$state,$stateParams,$compile,$location,materielService,FileUploader) {
    $scope.$on('$viewContentLoaded', function() {   
    	// initialize core components
    	App.initAjax();
    	handle = new pageHandle();
    	// set default layout mode
    	$rootScope.settings.layout.pageContentWhite = true;
        $rootScope.settings.layout.pageBodySolid = false;
        $rootScope.settings.layout.pageSidebarClosed = false;
        if($state.current.name=="buyOrder"){
        	loadMainTable();// 加载订单列表(普通订单)
        	loadMainFramTable();// 框架订单列表
        	
        	//***************************************流程处理相关start
        	var dbtable;//待办table
			var endTaskTable;//已办table
			
			if($stateParams.tabHref == '1'){//首页待办列表传过来的参数
				$('#orderTab a[href="#daiban"]').tab('show');
				showDbTable();
			}else if($stateParams.tabHref == '2'){//首页已办列表传过来的参数
				$('#orderTab a[href="#yiban"]').tab('show');
				showYbTable();
			}else{//从菜单进入
				$('#orderTab a[href="#apply"]').tab('show');
			}
			
			
			

			// 请假申请
			$scope.toApply = function() {
				$('#orderTab a[href="#apply"]').tab('show');
			};
			// 待办流程
			$scope.toDaiban = function() {
				$('#orderTab a[href="#daiban"]').tab('show');

				// 构建datatables开始***************************************
				dbtable = showDbTable();								
				// 构建datatables结束***************************************

			};
			// 已办流程
			$scope.toYiban = function() {
				$('#orderTab a[href="#yiban"]').tab('show');
				endTaskTable = showYbTable();
			};
			
			// 关闭审批窗口
			$scope.closeAuditDialogue = function() {
				$('#auditOrderModal').modal("hide");
			};
			
			// 关闭更改申请窗口 
			$scope.closeModifyDialogue = function() {
				$('#modifyOrderModal').modal("hide");
			};
			
			//初始化审批表单
			function approvalFormInit( taskDefinitionKey, businessType, taskId ) {
				
			}
			//***************************************流程处理相关end
        	}else{
        		$scope.datepickerInit();
            	// 初始化日期控件
            	     	
            	$scope.opration = {};
            	$scope.serialNums = [];
            	
            	
            	if($stateParams.serialNum){
            		$scope.opration = '修改';
            		$scope.getBuyOrderInfo($stateParams.serialNum,$stateParams.taskId, $stateParams.comments,$stateParams.processInstanceId)
            	}else{
            		$scope.opration = '新增';
            		$scope.orderMateriel=[];
            		$scope.buyOrder={};
            		$scope.contract={};
            		$scope.contract.contractType="采购合同";
            		$scope.buyOrder.orderType="贸易采购";
            		$scope.buyOrder.tradeType="内贸";
            		/*$scope.buyOrder.currency="人民币";*/
            		$scope.clauseSettlement = {};
            		$scope.buyOrder.seller ="中航能科（上海）能源科技有限公司";
            		$scope.buyOrder.rate = 17;
            		dateSelectSetting();//日期选择限制
            		// 加载数据
                	initSuppliers();
                	initWarehouse();
                	//合同内容
                	$scope.buyOrder.contractContent = '111100';
                	$scope.initContractContent();
            	}
            	
            	$scope.noShow = true;
            	if($stateParams.view==1){// 订单切换为查看
            		$scope.buyOrderInput = true;
    		    	$scope.buyOrderShow = true;
       		    	$scope.opration = '查看';
    		    }
            	if($stateParams.view=='all'){// 订单全体切换为查看
            		$scope.cancelOrder();
            		$scope.cancelOrderMateriel();
            		$scope.cancelContract();
            		$scope.cancelClauseSettlement();
            		$scope.cancelClauseAdvance();
            		$scope.cancelClauseDelivery();
            		$scope.cancelClauseCheckAccept();
            		$scope.cancelClauseFramework();
       		    	$scope.cancelClauseAfterSales();
   	       		    $scope.cancelFile();
            		
//            		$scope.cancelOrderStatus();
            		
            		$scope.noShow = false;
       		    	$scope.opration = '查看';
    		    }
            	
            	validateInit();// 加载表单验证控件
            	
/*            	validateContractInit();// 加载合同表单验证控件
*/            	
            	validateClauseAdvanceInit();// 加载垫资条款表单验证
            	validateClauseDeliveryInit();// 加载交付条款表单验证
            	validateClauseCheckAcceptInit();// 加载验收条款表单验证
            	validateClauseAfterSalesInit();// 加载售后条款表单验证
            	validateClauseSettlementInit();// 加载结算条款表单验证
            	validateCSDInit();// 加载结算条款明细表单验证
            	validateFileInit();//加载订单附件表单验证
            	validateClauseFrameworkInit();// 加载框架条款表单验证
        	}
    });
    
    function doOrder(_url, mydata, modal){
    	$.ajax( {
	        url : _url,
	        dataType:"text",
	        type: 'POST',
	        data : mydata,
	        success : function(data) {
	        	showToastr('toast-bottom-right', 'success', data);
	        	$scope.cancelPage();
	        }
	     });
	}
	
	//审批通过
	$scope.orderPass = function() {
	    var mydata={"processInstanceId":$("#processInstanceId").val(),"orderId":$scope.buyOrder.serialNum,"content":$("#content").val(),
				"completeFlag":true};
	    var _url = ctx + "rest/order/complate/" + $("#taskId").val();
	    doOrder(_url, mydata, 'audit');
	};
	//审批不通过
	$scope.orderUnPass = function() {
		var mydata={"processInstanceId":$("#processInstanceId").val(),"orderId":$scope.buyOrder.serialNum,"content":$("#content").val(),
				"completeFlag":false};
		var _url = ctx + "rest/order/complate/" + $("#taskId").val();
		doOrder(_url, mydata, 'audit');
	};
	
	//重新申请
	$scope.replyOrder = function() {
	    var mydata={"processInstanceId":$("#processInstanceId").val(),
				"reApply":true,"orderId":$scope.buyOrder.serialNum,"reason":$scope.buyOrder.remark,"orderType":'buyOrder'};
		var _url = ctx + "rest/order/modifyOrder/" + $("#taskId").val();
		doOrder(_url, mydata, 'modify');
	};
	//取消申请
	$scope.cancelApply = function() {
	     var mydata={"processInstanceId":$("#processInstanceId").val(),
				"reApply":false,"orderId":$scope.buyOrder.serialNum,"reason":$scope.buyOrder.remark,"orderType":'buyOrder'};
		var _url = ctx + "rest/order/modifyOrder/" + $("#taskId").val();
		doOrder(_url, mydata, 'modify' );
	};
	$scope.changeFlag = true
    $scope.repeatDone = function(scope){
    	$scope.changeFlag = false;
    	var date1= scope._orderMateriel.deliveryDate;
    	var date2= scope._orderMateriel.lastDeliveryDate;
    	/*var date3= scope.buyOrder.orderDate;*/
    	$scope.datepickerInit();
    	if(scope._orderMateriel){
    		scope._orderMateriel.deliveryDate = date1;
    		scope._orderMateriel.lastDeliveryDate = date2;
    	}
    	$scope.changeFlag = true;
    	/*scope.buyOrder.orderDate = date3;*/
   };
   
   $scope.repeatMaterielList = function(scope){
	   searchMaterielList();//订单物料可检索化
  };

   
   $scope.renderDone = function(){
   	var date3= $scope.buyOrder.orderDate;
   	var date4= $scope.buyOrder.makeDate;
   	$scope.datepickerInit();
   	$scope.buyOrder.orderDate = date3;
   	$scope.buyOrder.makeDate = date4;
  };
   
   $scope.datepickerInit = function(scope){
	   $('.date-picker').datepicker({
			rtl: App.isRTL(),
			orientation: "left",
			autoclose: true,
			dateFormat:"yyyy-mm-dd",
			language: "zh-CN"
   	})
  };
   
    $scope.save  = function() {
    	if($('#form_sample_1').valid()){
    		if($scope.buyOrder.orderDate=='') {// 日期为空的处理
    			$scope.buyOrder.orderDate=null;
    		}

    		// 保存数据处理
// $scope.buyOrder.parentBuyOrder=null;
// $scope.buyOrder.createTime=null;
// $scope.buyOrder.updateTime=null;
    		// **********//

    		orderService.save($scope.buyOrder).then(
       		     function(data){
       		    	$scope.buyOrder = data;
       		    	$scope.contract.orderSerial = data.serialNum;
       		    	if(isNull($scope.contract.contractNum)){
       		    		$scope.contract.contractNum = $scope.buyOrder.orderNum;
       		    	}
	   	    		
       		    	$scope.contract.comId = $scope.buyOrder.supplyComId;
	   	    		$scope.contract.signDate = $scope.buyOrder.orderDate;
	   	    		orderService.saveContract($scope.contract).then(
	   	       		     function(data){
	   	       		    	toastr.success('数据保存成功！');
	   	       		    	$scope.contract = data.data;
	   	       		     },
	   	       		     function(error){
	   	       		    	toastr.error('数据保存出错！');
	   	       		         $scope.error = error;
	   	       		     }
	   	       		 );
       		    	/*$location.search({serialNum:data.serialNum,view:1});*/
       		    	$scope.buyOrderInput = true;
       			    $scope.buyOrderShow = true;
       		     },
       		     function(error){
       		         $scope.error = error;
       		         toastr.error('数据保存出错！');
       		     }
       		 );
    	}
    	
    }; 	
    
    $scope.cancel  = function() {// 取消编辑
    	if($scope.buyOrder.serialNum==null || $scope.buyOrder.serialNum=='') {// 如果是取消新增，返回列表页面
    		$state.go("buyOrder");
    		return;
		}
    	$scope.getBuyOrderInfo($scope.buyOrder.serialNum,$stateParams.taskId, $stateParams.comments,$stateParams.processInstanceId);
    	$scope.cancelOrder();
    	
    };
    $scope.cancelOrder  = function() {// 取消编辑订单信息
    	$scope.buyOrderInput = true;
	    $scope.buyOrderShow = true;
    };
    
    $scope.edit  = function() {// 进入编辑
    	$scope.buyOrderInput = false;
	    $scope.buyOrderShow = false;
    };
    
    $scope.viewBuyOrder = function(serialNum){
    	$state.go("viewBuyOrder",{serialNum:serialNum});
    }
    
    var table;
    var tableAjaxUrl = "rest/order/findOrderList?type=buy";
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
                order: [[9, "desc"]],// 默认排序列及排序方式
                searching: true,// 是否过滤检索
                ordering:  true,// 是否排序
                lengthMenu: [[5, 10, 15, 30, -1], [5, 10, 15, 30, "All"]],
                pageLength: 5,// 每页显示数量
                processing: true,// loading等待框
// serverSide: true,
                ajax: tableAjaxUrl,// 加载数据中
                textAlign: 'center',
                "aoColumns": [
                              { mData: 'serialNum'},
                              { mData: 'orderNum' },
                              { mData: 'supplyName' },
                              { mData: 'materielCount' },
                              { mData: 'orderAmount' },
                              { mData: 'deliveryMode' },
                              { mData: 'orderType' },
                              { mData: 'saleApplySerial' },
                              { mData: 'orderSerial' },
                              { mData: 'orderDate' }/*,
                              { mData: 'processBase',
	                            	mRender:function(data,
	    									type, row, meta){
	                            		if(data!=""&&data!=null){
	                            			if(data.status=="PENDING"||data.status=="WAITING_FOR_APPROVAL"){
	    										return '<span  class="label label-sm label-warning ng-scope">审核中</span>';
	    									}else if(data.status=="APPROVAL_SUCCESS"){
	    										if(row.status==1){
	    											return '<span  class="label label-sm label-success ng-scope">待接收</span>';
	    										}else if(row.status==2){
	    											return '<span  class="label label-sm label-success ng-scope">已接收</span>';
	    										}else if(row.status==3){
	    											return '<span  class="label label-sm label-success ng-scope">待收货</span>';
	    										}else if(row.status==4){
	    											return '<span  class="label label-sm label-success ng-scope">部分收货</span>';
	    										}else if(row.status==5){
	    											return '<span  class="label label-sm label-success ng-scope">待检验</span>';
	    										}else if(row.status==6){
	    											return '<span  class="label label-sm label-success ng-scope">待入库</span>';
	    										}else if(row.status==7){
	    											return '<span  class="label label-sm label-success ng-scope">部分入库</span>';
	    										}else if(row.status==8){
	    											return '<span  class="label label-sm label-success ng-scope">待收票</span>';
	    										}else if(row.status==9){
	    											return '<span  class="label label-sm label-success ng-scope">部分开票</span>';
	    										}else if(row.status==10){
	    											return '<span  class="label label-sm label-success ng-scope">待付款</span>';
	    										}else if(row.status==11){
	    											return '<span  class="label label-sm label-success ng-scope">部分付款</span>';
	    										}else if(row.status==12){
	    											return '<span  class="label label-sm label-success ng-scope">已完成</span>';
	    										}else if(row.status==13){
	    											return '<span  class="label label-sm label-success ng-scope">已取消</span>';
	    										}else{
	    											return '<span  class="label label-sm label-success ng-scope">待接收</span>';
	    										}
	    										
	    									}else if(data.status=="APPROVAL_FAILED"){
	    										return '<span  class="label label-sm label-danger ng-scope">未通过</span>';
	    									}else{
	    										return '<span  class="label label-sm label-info ng-scope">未审批</span>';
	    									}
	                            		}else{
	                            			return '<span  class="label label-sm label-info ng-scope">未审批</span>';
	                            		}
	                            	}
	                            }*/

                        ],
               'aoColumnDefs' : [ {
							'targets' : 0,
							'searchable' : false,
							'orderable' : false,
							'render' : function(data,
									type, full, meta) {
								return "<label class='mt-checkbox mt-checkbox-single mt-checkbox-outline'>" +
								"<input type='checkbox' class='checkboxes' value="+ data +" />" +
								"<span></span></label>";
							},
							"createdCell": function (td, cellData, rowData, row, col) {
								 $compile(td)($scope);
						       }
						},{
							'targets' : 1,
							'render' : function(data,
									type, row, meta) {
								var clickhtm = '<a href="javascript:void(0);" ng-click="viewBuyOrder(\''+row.serialNum+'\')">'+data+'</a></br>'
								if(row.processBase!=""&&row.processBase!=null){
                        			if(row.processBase.status=="PENDING"||row.processBase.status=="WAITING_FOR_APPROVAL"){
										return clickhtm + '<span ng-click="viewOrderLog(\''+row.serialNum+'\')" style="color:#fcb95b">审核中</span>';
									}else if(row.processBase.status=="APPROVAL_SUCCESS"){
										if(row.status==1){
											return clickhtm + '<span ng-click="viewOrderLog(\''+row.serialNum+'\')"  style="color:#fcb95b">待确认</span>';
										}else if(row.status==2){
											return clickhtm + '<span  ng-click="viewOrderLog(\''+row.serialNum+'\')" style="color:green">已确认</span>';
										}else if(row.status==3){
											return clickhtm + '<span  ng-click="viewOrderLog(\''+row.serialNum+'\')" style="color:#fcb95b">待签合同</span>';
										}else{
											return clickhtm + '<span  ng-click="viewOrderLog(\''+row.serialNum+'\')" style="color:green">已确认</span>';
										}
									}else if(row.processBase.status=="APPROVAL_FAILED"){
										return clickhtm + '<span  ng-click="viewOrderLog(\''+row.serialNum+'\')" style="color:red">未通过</span>';
									}else{
										return clickhtm + '<span ng-click="viewOrderLog(\''+row.serialNum+'\')">未发布</span>';
									}
                        		}else{
                        			return clickhtm + '<span ng-click="viewOrderLog(\''+row.serialNum+'\')">未发布</span>';
                        		}
								
							},
							"createdCell": function (td, cellData, rowData, row, col) {
								 $compile(td)($scope);
						       }
						},{
							'targets' : 2,
							'render' : function(data,
									type, row, meta) {
								var htm = (data==null?'':data)+'</br>'
                    			if(row.deliverStatus=="0"){
                    				return htm + '<span >未开始</span>';
								}else if(row.deliverStatus=="1"){
                    				return htm + '<span style="color:green" ng-click="viewDeliverLog(\''+row.serialNum+'\')">已发货</span>';
								}else if(row.deliverStatus=="2"){
                    				return htm + '<span style="color:green" ng-click="viewDeliverLog(\''+row.serialNum+'\')">已收货</span>';
								}else if(row.deliverStatus=="3"){
                    				return htm + '<span style="color:green" ng-click="viewDeliverLog(\''+row.serialNum+'\')">已检验</span>';
								}else if(row.deliverStatus=="4"){
                    				return htm + '<span style="color:green" ng-click="viewDeliverLog(\''+row.serialNum+'\')">已出库</span>';
								}else if(row.deliverStatus=="5"){
                    				return htm + '<span style="color:green" ng-click="viewDeliverLog(\''+row.serialNum+'\')">已入库</span>';
								}else{
									return htm + '<span>未开始</span>';
								}
							},
							"createdCell": function (td, cellData, rowData, row, col) {
								 $compile(td)($scope);
						       }
						},{
							'targets' : 4,
							'render' : function(data,
									type, row, meta) {
								var htm = (data==null?'':data)+'</br>'

                    			if(row.payStatus=="0"){
                    				return htm + '<span >未付款</span>';
								}else if(row.payStatus=="1"){
                    				return htm + '<span style="color:green" ng-click="viewPayLog(\''+row.serialNum+'\')">已付款</span>';
								}else if(row.payStatus=="2"){
                    				return htm + '<span style="color:green" ng-click="viewPayLog(\''+row.serialNum+'\')">已收款</span>';
								}else{
									return htm + '<span >未付款</span>';
								}
							},
							"createdCell": function (td, cellData, rowData, row, col) {
								 $compile(td)($scope);
						       }
						}, {
							'targets' : 7,
							'render' : function(data,
									type, row, meta) {
								if(isNull(row.contract)){
									return ""
								}else{
									return row.contract.contractNum
								}
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
	        })
        };
        
        var framTable;
        var framTableAjaxUrl = "rest/order/findOrderList?type=buy&&fram=1";
        var loadMainFramTable = function() {
                a = 0;
                App.getViewPort().width < App.getResponsiveBreakpoint("md") ? $(".page-header").hasClass("page-header-fixed-mobile") && (a = $(".page-header").outerHeight(!0)) : $(".page-header").hasClass("navbar-fixed-top") ? a = $(".page-header").outerHeight(!0) : $("body").hasClass("page-header-fixed") && (a = 64);
                framTable = $("#sample_3")
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
                    order: [[1, "asc"]],// 默认排序列及排序方式
                    searching: true,// 是否过滤检索
                    ordering:  true,// 是否排序
                    lengthMenu: [[5, 10, 15, 30, -1], [5, 10, 15, 30, "All"]],
                    pageLength: 5,// 每页显示数量
                    processing: true,// loading等待框
    // serverSide: true,
                    ajax: framTableAjaxUrl,// 加载数据中
                    "aoColumns": [
                                  { mData: 'serialNum' },
                                  { mData: 'orderNum' },
                                  { mData: 'supplyName' },
                                  { mData: 'materielCount' },
                                  { mData: 'orderAmount' },
                                  { mData: 'deliveryMode' },
                                  { mData: 'orderType' },
                                  { mData: 'saleApplySerial' },
                                  { mData: 'orderSerial' },
                                  { mData: 'orderDate' }

                            ],
                   'aoColumnDefs' : [ {
    							'targets' : 0,
    							'searchable' : false,
    							'orderable' : false,
    							'render' : function(data,
    									type, full, meta) {
    								return "<label class='mt-checkbox mt-checkbox-single mt-checkbox-outline'>" +
    								"<input type='checkbox' class='checkboxes' value="+ data +" />" +
    								"<span></span></label>";
    							},
    							"createdCell": function (td, cellData, rowData, row, col) {
    								 $compile(td)($scope);
    						       }
    						}, {
								'targets' : 7,
								'render' : function(data,
										type, row, meta) {
									if(isNull(row.contract)){
										return ""
									}else{
										return row.contract.contractNum
									}
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
    						var rows = framTable.rows({
    							'search' : 'applied'
    						}).nodes();
    						$('input[type="checkbox"]', rows).prop(
    								'checked', this.checked);
    					});
    	
    			// Handle click on checkbox to set state of "Select
    			// all" control
    			$('#sample_3 tbody')
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
    			// ***************************************
    			$("#sample_3").find(".group-checkable").change(function() {
    	            var e = jQuery(this).attr("data-set"),
    	            t = jQuery(this).is(":checked");
    	            jQuery(e).each(function() {
    	                t ? ($(this).prop("checked", !0), $(this).parents("tr").addClass("active")) : ($(this).prop("checked", !1), $(this).parents("tr").removeClass("active"))
    	            })
    	        }),
    	        $("#sample_3").on("change", "tbody tr .checkboxes",
    	        function() {
    	            $(this).parents("tr").toggleClass("active")
    	        })
            };
        
        // 弹出确认删除模态框
        $scope.deleteBuyOrder = function() {
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
    			$('#delBuyOrderModal').modal('show');// 弹出删除确认模态框
    		}
			
		};
		
		$scope.editBuyOrder  = function() {// 进入编辑页面
        	/*var ids = '';
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
    		}*/
    		if(table.rows('.active').data().length != 1){
    			showToastr('toast-top-center', 'warning', '请选择一个订单！')
    		}else{
    			var processBase = table.row('.active').data().processBase;
    			if(processBase != null){
    				showToastr('toast-top-center', 'warning', '该订单已发起流程审批，不能修改！')
    			}else $state.go('addBuyOrder',{serialNum:table.row('.active').data().serialNum});
    		}
    		
        };
        
        
     // 弹出确认删除模态框
        $scope.deleteBuyFramOrder = function() {
			var ids = '';
			$scope.deleteType = 'fram';
			// Iterate over all checkboxes in the table
			framTable.$('input[type="checkbox"]').each(
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
    			$('#delBuyOrderModal').modal('show');// 弹出删除确认模态框
    		}
			
		};
		
		$scope.editBuyFramOrder  = function() {// 进入编辑页面
        	var ids = '';
    		// Iterate over all checkboxes in the table
    		framTable.$('input[type="checkbox"]').each(
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
    		
    		$state.go("addBuyOrder",{serialNum:ids});
        };
        
     // 删除开始***************************************
		$scope.del = function() {
			var ids = '';
			// Iterate over all checkboxes in the table
			if($scope.deleteType == 'fram'){
				framTable.$('input[type="checkbox"]').each(
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
			}
			
			orderService
					.delOrder(ids)
					.then(
							function(data) {
								$('#delBuyOrderModal').modal(
										'hide');// 删除成功后关闭模态框
								$(".modal-backdrop").remove();
								/* table.ajax.reload(); // 重新加载datatables数据 */
								toastr.success('数据删除成功！');
								 $state.go('buyOrder',{},{reload:true});
								 
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
	            	orderNum:{required:"采购订单号不能为空！"},
	            	orderType:{required:"采购类型不能为空！"},
	            	supplyComId:{required:"卖方不能为空！"},
	            	serviceModel:{required:"服务模式不能为空！"},
	            	settlementClause:{required:"结算条款不能为空！"},
	            	deliveryMode:{required:"提货方式不能为空！"},
	            	rate:{required:"税率不能为空！"},
	            	currency:{required:"币种不能为空！"},
	            	maker:{required:"制单人不能为空！"},
	            	seller:{required:"买方不能为空！"}
	            },
            	rules: {orderNum: {required: !0,maxlength: 20},
            		orderType: {required: !0,maxlength: 20},
            		buyComId: {required: !0,maxlength: 20},
            		serviceModel: {required: !0,maxlength: 20},
            		settlementClause: {required: !0,maxlength: 20},
            		deliveryMode: {required: !0,maxlength: 20},
            		rate: {required: !0,maxlength: 20},
            		maker: {required: !0,maxlength: 20},
	            	seller:{required: !0,maxlength: 20},
            		currency: {required: !0,maxlength: 20}
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
        $scope.getBuyOrderInfo  = function(serialNum,taskId,comments,processInstanceId) {
        	orderService.getOrderInfo(serialNum).then(
          		     function(data){//加载页面对象
          		    	$scope.buyOrder=data.orderInfo;
          		    	$scope.orderMateriel=data.orderMateriel;
          		    	
          		    	if($state.current.name=="viewBuyOrder"){//查看页面构造物料查询分页
          		    		$scope.queryForPage();
          		    	}
          		    	
          		    	$scope.contract=data.contract;
          		    	$scope.clauseAfterSales=data.clauseAfterSales;
          		    	$scope.clauseAdvance=data.clauseAdvance;
          		    	$scope.clauseCheckAccept=data.clauseCheckAccept;
          		    	$scope.clauseDelivery=data.clauseDelivery;
          		    	$scope.clauseSettlement=data.clauseSettlement;
          		    	if(!isNull(data.clauseSettlement)){
          		    		$scope.clauseSettlement.CSD = [{}]
          		    		$scope.clauseSettlement.CSD=data.clauseSettlement.clauseSettlementDetails;
          		    		if(!isNull(data.clauseSettlement.clauseSettlementDetails)){
          		    			_index = data.clauseSettlement.clauseSettlementDetails.length;
          		    		}
          		    	}else{
          		    		$scope.clauseSettlement = {}
          		    	}
          		    	
          		    	if($scope.buyOrder.status==1){//已提交的不能做提交
//          		    		$scope.cancelOrderStatus();
          		    	}
          		    	
          		    	if(!isNull(data.file)){
     	        			$scope.file = data.file;
     	        			_fileIndex = $scope.file.length;
     	        		}
          		    	if(!isNull(data.ClauseFramework)){
     	        			$scope.ClauseFramework = data.ClauseFramework;
     	        			_indexClauseFramework = $scope.ClauseFramework.length;
     	        		}
          		    	
          		    	if(!isNull($scope.contract)){
          		    		var myJsDate=$filter('date')($scope.contract.startDate,'yyyy-MM-dd');
        					$scope.contract.startDate=myJsDate;
        					
        					var myJsDate1=$filter('date')($scope.contract.endDate,'yyyy-MM-dd');
        					$scope.contract.endDate=myJsDate1;
        					
        					var myJsDate2=$filter('date')($scope.contract.signDate,'yyyy-MM-dd');
        					$scope.contract.signDate=myJsDate2;
        					
        					dateSelectSetting();//日期选择限制
             		    	if(!isNull($scope.contract.signDate)){
             		    		$("#startDate").datepicker('setStartDate',$scope.contract.signDate);
         			        	$("#endDate").datepicker('setStartDate',$scope.contract.signDate);
             		    	}
             		        
             		    	if(!isNull($scope.contract.startDate)){
             			        	$("#signDate").datepicker('setEndDate',$scope.contract.startDate);
             			        	$("#endDate").datepicker('setStartDate',$scope.contract.startDate);
             			    }  
             		    	if(!isNull($scope.contract.signDate)){
             			        	$("#signDate").datepicker('setEndDate',$scope.contract.signDate);
             			        	$("#startDate").datepicker('setEndDate',$scope.contract.signDate);
             				} 
        					if($scope.contract.contractType=="框架合同"){
        						$scope.showClauseFramework();
              	        	}
          		    	}else{
          		    		$scope.contract = {};
          		    	}
          		    	
          		    	$scope.copyMateriels = angular.copy($scope.orderMateriel);
          		    	
          		    	// 加载数据
                    	initSuppliers();
                    	initWarehouse();
                    	
          		    	$("#serialNum").val(serialNum);//赋值给隐藏input，通过和不通过时调用
    					$("#taskId").val(taskId);//赋值给隐藏input，通过和不通过时调用
    					$("#processInstanceId").val(processInstanceId);//赋值给隐藏input，通过和不通过时调用
    					
    					if(comments == ""||comments == null){
    						$("#comment_audit").html( "<tr><td colspan='3' align='center'>无内容</td></tr>");
    					}else $("#comment_audit").html(comments);
    					
    					//初始化合同内容
                    	if(isNull($scope.buyOrder.contractContent)){
                    		$scope.buyOrder.contractContent = '111100';
                    	}
                    	$scope.initContractContent();
          		     },
          		     function(error){
          		         $scope.error = error;
          		     }
          		 );
        	
        }; 
        
        
        /** *************订单物料操作 start*************** */
        
        var selectMateriel = function() {
                 a = 0;
                 App.getViewPort().width < App.getResponsiveBreakpoint("md") ? $(".page-header").hasClass("page-header-fixed-mobile") && (a = $(".page-header").outerHeight(!0)) : $(".page-header").hasClass("navbar-fixed-top") ? a = $(".page-header").outerHeight(!0) : $("body").hasClass("page-header-fixed") && (a = 64);
                 table = $("#select_sample_2").DataTable({
                     language: {
                         aria: {
                             sortAscending: ": activate to sort column ascending",
                             sortDescending: ": activate to sort column descending"
                         },
                         emptyTable: "空表",
                         info: "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
                         infoEmpty: "没有数据",
                         // infoFiltered: "(filtered1 from _MAX_ total
							// entries)",
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
                     order: [[1, "asc"]],// 默认排序列及排序方式
                     searching: true,// 是否过滤检索
                     ordering:  true,// 是否排序
                     lengthMenu: [[5, 10, 15, 30, -1], [5, 10, 15, 30, "All"]],
                     pageLength: 5,// 每页显示数量
                     processing: true,// loading等待框
// serverSide: true,
                     ajax: "rest/materiel/findMaterielList?isLatestVersion=1",// 加载数据中
                     "aoColumns": [
                                   { mData: 'serialNum' },
                                   { mData: 'materielNum' },
                                   { mData: 'materielName' },
                                   { mData: 'specifications' },
                                   { mData: 'unit' }
                             ],
                    'aoColumnDefs' : [ {
     							'targets' : 0,
     							'searchable' : false,
     							'orderable' : false,
     							
     							'render' : function(data,
     									type, row, meta) {
     								if($scope.modalType=='single'){
     	  								return '<input type="radio" id="'+ row.serialNum +'" ng-click="getCheckedIds(\''+data+'\','+meta.row+')" name="serialNum" value="'
   										+ $('<div/>')
   												.text(
   														row.serialNum)
   												.html()
   										+ '">';

     								}else{
     	  								/*return '<input type="checkbox" data-checked=false id="'+ row.serialNum +'" ng-click="getCheckedIds(\''+data+'\','+meta.row+')" name="material_serial" value="'
   										+ $('<div/>')
   												.text(
   														row.serialNum)
   												.html()
   										+ '">';*/
     	  								return "<label class='mt-checkbox mt-checkbox-single mt-checkbox-outline'>" +
     									"<input type='checkbox' class='checkboxes' data-checked=false  id='"+ row.serialNum +"' ng-click='getCheckedIds(\""+data+"\","+meta.row+")' name='material_serial' value="+ row.serialNum +" />" +
     									"<span></span></label>";

     								}
     								
     							},
     							"createdCell": function (td, cellData, rowData, row, col) {
     								 $compile(td)($scope);
     						       }
     						},{
     							'targets' : 1,
     							'render' : function(data,
     									type, row, meta) {
     								var ClauseFrameworkIcon='';// ClauseFramework图标
     								if(row.isCSD==1){
     									ClauseFrameworkIcon = '<span class="label label-sm label-success">B</span> '
     								}
     								return ClauseFrameworkIcon + data;
     							}

     						}]

                 }).on('order.dt',
                         function() {
                     console.log('排序');
                 }).on('page.dt', 
                 function () {
               	  console.log('翻页');
   	          }).on('draw.dt',function() {
   	        	  checkedIdHandler();
   	          });
                 //全选操作
                 $("#select_sample_2").find(".group-checkable").change(function() {
      	            var e = jQuery(this).attr("data-set"),
      	            t = jQuery(this).is(":checked");
      	            jQuery(e).each(function() {
      	                t ? ($(this).prop("checked", !0), $(this).parents("tr").addClass("active")) : ($(this).prop("checked", !1), $(this).parents("tr").removeClass("active"))
      	            })
      	        }),
      	        $("#select_sample_2").on("change", "tbody tr .checkboxes",
      	        function() {
      	            $(this).parents("tr").toggleClass("active")
      	        })
             };

             /**
      		 * checkbox点击事件（生成选中内容）
      		 */
      		$scope.getCheckedIds = function(serialNum,index){
      			var data={};
      			data.serialNum = serialNum;
      			data.materiel = table.row(index).data(); //获取一行数据
      			data.materiel.materielSerial = data.materiel.serialNum; //为保存操作做准备，新增物料serialNum为空

      			if($("#"+serialNum).data("radio")==true){ //修改物料弹出框
      				$scope.serialNums = []; //清空选中数组
      				$scope.serialNums.push(data);
      				$scope.selectedMaterielHide = true; //不显示已选物料
      				return;
      			}
      			if($("#"+serialNum).data("checked")||$("#"+serialNum).data("checked")==undefined){
      				for(var i=0;i<$scope.serialNums.length;i++){
      					if($scope.serialNums[i].serialNum==serialNum){
      						$scope.serialNums.splice(i,1);
      						$("#"+serialNum).attr("checked",false);
      						$("#"+serialNum).data("checked",false);
      						break;
      					}
      					
      				}
      				
      			}else{
      				$scope.serialNums.push(data);
      				$("#"+serialNum).data("checked",true);
      				$("#"+serialNum).attr("checked",true);
      			}
      			
      		}
      		
              /**
  	    	 * 更换供应物料流水号
  	    	 */
  	    	$scope.changeSelectValue = function(id,obj){
  	    		if($("#"+obj).data("checked") == false){
  	    			$("#"+obj).val($("#"+id).val());
  	    		}else{
  	    			for(var i=0;i<$scope.serialNums.length;i++){
  	    				if($scope.serialNums[i].serialNum==obj){
  	    					$scope.serialNums[i].materiel.supplyMaterielSerial = $("#"+id).val();
  	    				}
  	    			}
  	    		}

  	    	}
      		/**
      		 * 遍历checkbox,检查并处理已取消的元素
      		 */
      		function checkedIdHandler(){
      			//获取选中物料ID
      			table.$('input[name="material_serial"]').each(function() { //遍历当前页的物料信息
      					if ($.contains(document, this)) {
      						if (this.checked) {
      							if($scope.serialNums.length>0){
      								var flag = false;
      								for(var i=0;i<$scope.serialNums.length;i++){
      									if($scope.serialNums[i].serialNum == $(this).attr("id")){
      										flag=true;
      										break;
      									}
      									if(i==$scope.serialNums.length-1&& flag==false){//不在选中数组内，checkbox清除选中状态
      										$(this).attr("checked",false);
      										$(this).data("checked",false);
      									}
      								}
      							}else if($scope.serialNums.length==0){//没有被选中的物料
      								$(this).attr("checked",false);
      								$(this).data("checked",false);
      							}
      						}
      					}
      			});
      		}
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
			$('#select_sample_2 tbody')
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
            
   			
   			
        $scope.addOrderMateriel = function (type,index){
    		if(type=="single"){
    			$scope.modalType = type;
    			$scope.materielSelectedIndex = index;
    			if(table){
    				table.ajax.reload();
    			}else{
    				selectMateriel();
    			}
    			
    			$("#basicMaterielInfo").modal("show");
    		}else{
    			$scope.modalType = 'multiple';
    			if(table){
    				table.ajax.reload();
    			}else{
    				selectMateriel();
    			}
    			if(!isNull($scope.buyOrder)&&!isNull($scope.buyOrder.serialNum)){
	    			$("#basicMaterielInfo").modal("show");
	    		}else{
	    			toastr.warning("请先保存基本信息！");
	    		}
    		}
    		
			
		}
    	
        $scope.copyMateriels = {};
    	$scope.confirmSelect = function(){
    		if($scope.modalType=='single'){
    			var id_count = table.$('input[name="serialNum"]:checked').length;
    			if(id_count==0){
					toastr.warning("请选择物料");
					return;
				}
    			var id =  $('input[name="serialNum"]:checked').val(); 
    			var promise = materielService.chooseMateriels(id);
        		promise.then(function(data){
        			if($scope.materielSelectedIndex != undefined){
        				$scope.orderMateriel[$scope.materielSelectedIndex].materielSerial = data.data[0].serialNum;
        				$scope.orderMateriel[$scope.materielSelectedIndex].materiel = data.data[0];
        			}
        			$("#basicMaterielInfo").modal("hide");
        		},function(data){
        			// 调用承诺接口reject();
        		});
    			return;
    		}
//    		var id_count = table.$('input[type="checkbox"]:checked').length;
//			if(id_count==0){
//				toastr.warning("请选择物料");
//				return;
//			}
    		if($scope.serialNums.length==0){ //判断是否选择了物料
				toastr.warning("请选择物料");
				return;
			}
//        		var ids = '';
//				// Iterate over all checkboxes in the table
//				table.$('input[type="checkbox"]').each(
//						function() {
//							// If checkbox exist in DOM
//							if ($.contains(document, this)) {
//								// If checkbox is checked
//								if (this.checked) {
//									// 将选中数据id放入ids中
//									if (ids == '') {
//										ids = this.value;
//									} else
//										ids = ids + ','
//												+ this.value;
//								}
//							}
//						});
	    		//--------批量增加物料信息START--------------
    			var ids = '';
				for(var i=0;i<$scope.serialNums.length;i++){
					if (ids == '') {
						ids = $scope.serialNums[i].materiel.serialNum;
					} else{
						ids = ids + ',' + $scope.serialNums[i].materiel.serialNum;
					}
				}
        		handle.blockUI();
        		var promise = materielService.chooseBasicMateriels(ids);
        		promise.then(function(data){
        			toastr.success("添加成功！");
        			handle.unblockUI();
        			if($scope.orderMateriel.length==0){
        				for(var i = 0;i < data.data.length;i++){// data.data为选择的标准物料
        					$scope.tempMateriel = {};
        					$scope.tempMateriel.materiel = (data.data)[i];
        					$scope.tempMateriel.orderSerial = $scope.buyOrder.serialNum;
        					$scope.tempMateriel.materielSerial = (data.data)[i].serialNum;
        					$scope.orderMateriel.push($scope.tempMateriel);
        					$scope["orderMaterielInput"+i] = false;
        					$scope["orderMaterielShow"+i] = false;
        				}
        			}else{
        				for(var i = 0;i < data.data.length;i++){// data.data为选择的标准物料
        					$scope.tempMateriel = {};
        					$scope.tempMateriel.materiel = (data.data)[i];
        					$scope.tempMateriel.orderSerial = $scope.buyOrder.serialNum;
        					$scope.tempMateriel.materielSerial = (data.data)[i].serialNum;
        					$scope.orderMateriel.push($scope.tempMateriel);
	        				$scope["orderMaterielInput"+(length+i)] = false;
							$scope["orderMaterielShow"+(length+i)] = false;
							/*$scope["orderMaterielInput" + ($scope.orderMateriel.length-1)] = true;
							$scope["orderMaterielShow" + ($scope.orderMateriel.length-1)] = true;*/
		        		}
        			}
        			$scope.copyMateriels = angular.copy($scope.orderMateriel);
        			$("#basicMaterielInfo").modal("hide");
        		},function(data){
        			// 调用承诺接口reject();
        		});
    	}
    	

     	 //关闭物料列表时，清除选中状态START--------------
    	 $('#basicMaterielInfo').on('hide.bs.modal', function (e) { 
    		 clearChecked();
    		 $scope.serialNums=[];
	     })
    	
    	function clearChecked(){
    		table.$('input[type="checkbox"]').each(
					function() {
						// If checkbox exist in DOM
						if ($.contains(document, this)) {
							// If checkbox is checked
							this.checked = false;
						}
			});
    	} 
    	 
    	 /**
			 * 显示编辑、删除操作
			 */
	       $scope.showOperation = function(type,index){
	    	   if(type=='orderMateriel'){
	    		   call =  "operation_o"+index;
	    	   }
	    	   if(type=='file'){
	    		   call =  "operation_f"+index;
	    	   }
	    	   if(type=='csd'){
	    		   call =  "operation_csd"+index;
	    	   }
	    	   if(type=='ClauseFramework'){
	    		   call =  "operation_b"+index;
	    	   }
	    	   
	    	   $scope[call] = true;
	       };
	       
	       /**
			 * 隐藏编辑、删除操作
			 */
	       $scope.hideOperation = function(type,index){
	    	   if(type=='orderMateriel'){
	    		   call =  "operation_o"+index;
	    	   }
	    	   if(type=='file'){
	    		   call =  "operation_f"+index;
	    	   }
	    	   if(type=='csd'){
	    		   call =  "operation_csd"+index;
	    	   }
	    	   if(type=='ClauseFramework'){
	    		   call =  "operation_b"+index;
	    	   }
	    	   $scope[call]= false;
	       };
    	 
    	 /**
			 * 保存采购订单物料信息
			 */
			$scope.saveOrderMateriel = function(orderMateriel,index) {
/*
 * var orderMateriel = {}; orderMateriel.deliveryAddress =
 * supplyMateriel.deliveryAddress; orderMateriel.deliveryDate =
 * supplyMateriel.deliveryDate; orderMateriel.lastDeliveryDate =
 * supplyMateriel.lastDeliveryDate; orderMateriel.materielSerial =
 * supplyMateriel.materielSerial; orderMateriel.orderSerial =
 * supplyMateriel.orderSerial; orderMateriel.orderUnitPrice =
 * supplyMateriel.orderUnitPrice; orderMateriel.supplyComId =
 * supplyMateriel.supplyComId; orderMateriel.supplyMaterielSerial =
 * supplyMateriel.supplyMaterielSerial;
 */
						
				delete orderMateriel.materiel;
				delete orderMateriel.supplyMateriel;
				delete orderMateriel.supply;
				
				var promise = orderService
				.saveOrderMateriel(orderMateriel);
				promise.then(function(data) {
					if (!handle.isNull(data.data)) {
						$(".modal-backdrop").remove();
						toastr.success("保存成功");
						handle.unblockUI();
						// var company = data.data;
						// $state.go('companyAdd',company,{reload:true});
						$scope.orderMateriel[index] = data.data;
						$scope.copyMateriels[index] = data.data;
/*						console.log(data.data);*/
						$scope["orderMaterielInput"+index] = true;
						$scope["orderMaterielShow"+index] = true;
						$(".alert-danger").hide();
					} else {
						$(".modal-backdrop").remove();
						handle.unblockUI();
						toastr.error("保存失败！请联系管理员");
						console.log(data);
					}
					
				}, function(data) {
					// 调用承诺接口reject();
					$(".modal-backdrop").remove();
					handle.unblockUI();
					toastr.error("保存失败！请联系管理员");
					console.log(data);
				});
			}; 
			
	        /**
			 * 撤销物料编辑
			 */
	        $scope.cancelOrderMateriel=function (materiel,index) {
	        	// .show_materiels = false;
	        	$scope["orderMaterielInput"+index] = true;
				$scope["orderMaterielShow"+index] = true;
	        	for(var i=0;i<$scope.copyMateriels.length;i++){
	        		if(materiel.serialNum == $scope.copyMateriels[i].serialNum){ // 如果是以保存的物料，回滚
	        			$scope.orderMateriel[$scope.orderMateriel.indexOf(materiel)] = $scope.copyMateriels[i];
						break;
	        		}
	        		
	        		/*if(i==$scope.copyMateriels.length-1){ // 如果是已选择但未保存的物料，清空
	        			$scope.orderMateriel[$scope.orderMateriel.indexOf(materiel)].deliveryDate = "";
	        			$scope.orderMateriel[$scope.orderMateriel.indexOf(materiel)].deliveryAddress = "";
	        			$scope.orderMateriel[$scope.orderMateriel.indexOf(materiel)].lastDeliveryDate = "";
	        			$scope.orderMateriel[$scope.orderMateriel.indexOf(materiel)].amount = "";
	        			$scope.orderMateriel[$scope.orderMateriel.indexOf(materiel)].orderUnitPrice = "";
	        		}*/
	        	}
	        };  
	        

	        
	        //选择第一个，设置后面的数据
			$scope.setAllDeliveryAddress = function(orderMateriel){
				 for(var i=1;i<$scope.orderMateriel.length;i++){
					 if($scope["orderMaterielInput"+i] != true/*&&isNull($scope.orderMateriel[i].deliveryAddress)*/){
						 $scope.orderMateriel[i].deliveryAddress = orderMateriel.deliveryAddress;
					 }
				 }
			}
			
			$scope.setAllDeliveryDate = function(orderMateriel,index){
				if(index==0&&$scope.changeFlag){
					for(var i=1;i<$scope.orderMateriel.length;i++){
						 if($scope["orderMaterielInput"+i] != true/*&&isNull($scope.orderMateriel[i].deliveryAddress)*/){
							 $scope.orderMateriel[i].deliveryDate = orderMateriel.deliveryDate;
						 }
					 }
				}
			}
			
			$scope.setAllLastDeliveryDate = function(orderMateriel,index){
				if(index==0&&$scope.changeFlag){ 
					for(var i=1;i<$scope.orderMateriel.length;i++){
						 if($scope["orderMaterielInput"+i] != true/*&&isNull($scope.orderMateriel[i].deliveryAddress)*/){
							 $scope.orderMateriel[i].lastDeliveryDate = orderMateriel.lastDeliveryDate;
						 }
					 }
				}
			}
			 

			function indexOf(arr, item) {
				for (var i = 0; i < arr.length; i++) {
						if (arr[i] === item)
							return i;
						else
							return -1;
				}
			}
	        
	        /**
			 * 编辑采购订单物料
			 */
	        $scope.editOrderMateriel=function (materiel) {
	        	// .show_materiels = false;
	        	for(var i=0;i<$scope.orderMateriel.length;i++){
	        		if(materiel.serialNum == $scope.orderMateriel[i].serialNum){
	        			$scope["orderMaterielInput"+i] = false;
	        			$scope["orderMaterielShow"+i] = false;
	        		}
	        	}
	        	
	        };  
	        
	        /**
			 * 删除
			 */
	        $scope.deleteOrderMateriel=function (materiel) {
	        	handle.confirm("确定删除吗？",function(){
	        		handle.blockUI();
	        		if($scope.orderMateriel.length > 0){
	        			for(var i=0;i<$scope.orderMateriel.length;i++){
	        				if(materiel == $scope.orderMateriel[i]){
	        					$scope.orderMateriel.splice(i,1);
	        				}
	        			}
	        		}
	        		if(!isNull(materiel.serialNum)){
	        			var promise = orderService.deleteOrderMateriel(materiel.serialNum);
		        		promise.then(function(data){
		        			if(data.data == "1"){
		        				toastr.success("删除成功");
			        			handle.unblockUI(); 
		        			}else{
		        				toastr.error("删除失败！请联系管理员");
				            	console.log(data);
		        			}
		        			
		 	            },function(data){
		 	               // 调用承诺接口reject();
		 	            	toastr.error("删除失败！请联系管理员");
			            	console.log(data);
		 	            });
	        		}
	        	});
			   
	        };
    	 /** *************订单物料操作 end*************** */
    	 
    	 /** ***************合同信息start******************** */
	      //日期选择限制
	        var dateSelectSetting = function() { //签订日期变化
	        	startDateSetting();
	        	endDateSetting();
	        	signDateSetting();
		    }  
	        
	        var signDateSetting = function() { //签订日期变化
		        $("#signDate").datepicker().on('changeDate', function(ev){
		        	$("#startDate").datepicker('setStartDate',timeStamp2ShortString(ev.date));
		        	$("#endDate").datepicker('setStartDate',timeStamp2ShortString(ev.date));
		        });  
		    }  
	        
	        var startDateSetting = function() { //开始日期变化
		        $("#startDate").datepicker().on('changeDate', function(ev){
		        	$("#signDate").datepicker('setEndDate',timeStamp2ShortString(ev.date));
		        	$("#endDate").datepicker('setStartDate',timeStamp2ShortString(ev.date));
		        });  
		    }  
			 var endDateSetting = function() {   //结束日期变化
		        $("#endDate").datepicker().on('changeDate', function(ev){
		        	$("#signDate").datepicker('setEndDate',timeStamp2ShortString(ev.date));
		        	$("#startDate").datepicker('setEndDate',timeStamp2ShortString(ev.date));
		        });  
			} 
			 
			jQuery.validator.addMethod("noFileFlag", function(value, element) {  
				if($("#noFileFlag").length>0){
					return false;    
				}else{
					return true;  
				}
			}, "文件不能为空"); 
	        var validateContractInit = function() {
	        	var e = $("#form_contract"),
		        r = $(".alert-danger", e),
		        i = $(".alert-success", e);
		        e.validate({
		            errorElement: "span",
		            errorClass: "help-block help-block-error",
		            focusInvalid: !1,
		            ignore: "",
		            messages: {
		            	contractNum:{required:"合同编号不能为空！",rangelength:jQuery.validator.format("合同编号位数必须在{0}到{1}字符之间！")},
		            	startDate:{required:"开始日期不能为空！"},
		            	endDate:{required:"结束日期不能为空！"},
		            	electronicContract:{noFileFlag:"合同附件不能为空！"}
		            },
	            	rules: {contractNum:{required:true,
			                	rangelength:[3,12]
				                },
				                startDate:{required:true,
				                },
				                endDate:{required:true,
				                },
				                electronicContract:{noFileFlag:true
				                }
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
    	 
		        $scope.saveContract  = function() {// 保存合同信息
		   	    	if($scope.buyOrder.serialNum==null||$scope.buyOrder.serialNum=='') {// 订单信息为空的处理
		   	    		toastr.error('请先保存订单信息！');return
		   			}
		   	    	if($('#form_contract').valid()){
		   	    		$scope.contract.orderSerial = $scope.buyOrder.serialNum;
		   	    		$scope.contract.comId = $scope.buyOrder.buyComId;
		   	    		orderService.saveContract($scope.contract).then(
		   	       		     function(data){
		   	       		    	toastr.success('数据保存成功！');
		   	       		    	$scope.contract = data.data;
		   	       		    	if(!isNull(data.data)){
		          		    		var myJsDate=$filter('date')(data.data.startDate,'yyyy-MM-dd');
		        					$scope.contract.startDate=myJsDate;
		        					
		        					var myJsDate1=$filter('date')(data.data.endDate,'yyyy-MM-dd');
		        					$scope.contract.endDate=myJsDate1;
		        					
		        					var myJsDate2=$filter('date')(data.data.signDate,'yyyy-MM-dd');
		        					$scope.contract.signDate=myJsDate2;
		          		    	}
		   	       		    	$scope.cancelContract();
		   	       		     },
		   	       		     function(error){
		   	       		    	toastr.error('数据保存出错！');
		   	       		         $scope.error = error;
		   	       		     }
		   	       		 );
		   	    	}
		   	    	
		   	    }; 	
		   	    
		   	    $scope.cancelContract  = function() {// 取消编辑合同信息
		   	    	$scope.contractInput = true;
		   		    $scope.contractShow = true;
		   	    };
		   	    
		   	    $scope.editContract  = function() {// 进入编辑合同信息
		   	    	$scope.contractInput = false;
		   		    $scope.contractShow = false;
		   	    };
		   	    
		   	// 创建对象
			  	  var uploader = $scope.uploader = new FileUploader({url:'rest/fileOperate/uploadSingleFile'});
			  	 
			  	  uploader.onAfterAddingFile = function(item){
			  		  if(item.file.size>10000000){
			  			  // toastr.warning("文件大小超过10M！");
			  			  uploader.cancelAll();
			  		  }
			  	  }
			  	  // 添加文件到上传队列后
			  	  uploader.onCompleteAll = function () {
			  		  uploader.clearQueue();
			  	  };
			  	  // 上传成功
			  	  uploader.onSuccessItem = function (fileItem,response, status, headers) {
			  		  if (status == 200){ 
			  			  if(response==""){
			  				  toastr.error("上传失败！");
			  				  return;
			  			  }
			  		  		toastr.success("上传成功！");
			  		  		if(uploadSelectIndex=='electronicContract'||uploadSelectIndex=='signContract'){//合同附件
			  		  			$scope.contract[uploadSelectIndex] = response.filename;
			  		  		}else{//订单附件
			  		  			$scope.file[uploadSelectIndex].file = response.filename;
			  		  		}
			  		  }else{
			  			  toastr.error("上传失败！");
			  			if(uploadSelectIndex=='electronicContract'||uploadSelectIndex=='signContract'){//合同附件
		  		  			$scope.contract[uploadSelectIndex] = response.filename;
		  		  		}else{//订单附件
		  		  			$scope.file[uploadSelectIndex].file = response.filename;
		  		  		}
			  		  }
			  		};
			  	  // 上传失败
			  	  uploader.onErrorItem = function (fileItem, response, status, headers) {
			  			toastr.error("上传失败！");
			  	  };
			  	  

			       var uploadSelectIndex;
			  	  $scope.uploadFile = function(index){
			  		uploadSelectIndex = index;
			  	  }
			  	  
			  	  $scope.up = function(file){
			  		  uploader.clearQueue();
			  		  uploader.addToQueue(file);
			  		  uploader.uploadAll();
			  	  }
			       $scope.downloadFile = function(obj){
			    	   if(!handle.isNull(obj)){
			    		   window.location.href= $rootScope.basePath+"/rest/fileOperate/downloadFile?fileName="+encodeURI(encodeURI(obj));
			    	   }else{
			    		   toastr.error("下载失败!");
			    	   }
			       }
			       
			       $scope.removefile = function(index){
			    	   if(index=='electronicContract'||index=='signContract'){//合同附件
		  		  			$scope.contract[index] = "";
		  		  		}else{//订单附件
		  		  			$scope.file[index].file = "";
		  		  		}
			       }
/** ***************合同信息end******************** */
/** ***************结算条款start******************** */
var validateClauseSettlementInit = function() {// 结算条款表单验证
var e = $("#form_clauseSettlement"),
   r = $(".alert-danger", e),
   i = $(".alert-success", e);
   e.validate({
       errorElement: "span",
       errorClass: "help-block help-block-error",
       focusInvalid: !1,
       ignore: "",
       messages: {},
   	rules: {},
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

   $scope.saveClauseSettlement  = function() {// 保存结算条款
    	if($scope.contract.id==null||$scope.contract.id=='') {// 合同信息为空的处理
    		toastr.error('请先保存订单信息！');return
		}
    	
    	if(!$scope.buyOrderInput){
    		toastr.error('订单处于编辑状态，请处理！');return
    	}
    	
    	if($scope.orderMateriel.length>0){
			for(var i = 0;i < $scope.orderMateriel.length;i++){// data.data为选择的供应物料
				if(!$scope["orderMaterielInput"+i]){
					toastr.error('有订单物料处于编辑状态，请处理！');return
				}
			}
		}
    	
    	if(isNull($scope.clauseSettlement)){// 结算条款为空的处理
    		toastr.error('请填写结算条款后保存！');return
		}
    	if($('#form_clauseSettlement').valid()){
    		$scope.clauseSettlement.contractSerial = $scope.contract.id;
    		$scope.clauseSettlementDetail = $scope.clauseSettlement.CSD;
    		$scope.clauseSettlement.materielAmount = $scope.totalAmount();
  	        $scope.clauseSettlement.rateAmount = $scope.totalRateAndCustomsAmount();
  	        $scope.clauseSettlement.rateAndAmount = $scope.totalRateAndAmount();
  	        $scope.clauseSettlement.orderAmount = $scope.totalOrderAmount();
    		delete $scope.clauseSettlement.CSD;
    		orderService.saveClauseSettlement($scope.clauseSettlement).then(//保存结算条款
       		     function(data){
       		    	$scope.clauseSettlement = data.data;
       		    	if(!isNull(data.data)){
       		    		if(!isNull($scope.clauseSettlementDetail)){
       		    			for(var i=0;i<$scope.clauseSettlementDetail.length;i++){
          		    			$scope.clauseSettlementDetail[i].clauseSettlementSerial = data.data.serialNum;
          		    		}
          		    		orderService.saveClauseSettlementDetail($scope.clauseSettlementDetail).then(//保存结算条款明细
          		        		     function(data){
          		        		    	toastr.success('数据保存成功！');
          		        		    	$scope.cancelClauseSettlement();
          		        		    	$scope.clauseSettlement.CSD = data.data;
          		        		     },
          		        		     function(error){
          		        		    	toastr.error('数据保存出错！');
          		        		         $scope.error = error;
          		        		     }
          		        		 );
       		    		}else{
       		    			toastr.success('数据保存成功！');
          		    		$scope.cancelClauseSettlement()
       		    		}
       		    	//更新订单金额数据
	        		$scope.updateOrderAmount();	
       		    	}else{
      		    		$scope.clauseSettlement = {}
      		    		toastr.success('数据保存成功！');
      		    		$scope.cancelClauseSettlement()
      		    	}
       		     },
       		     function(error){
       		    	toastr.error('数据保存出错！');
       		         $scope.error = error;
       		     }
       		 );
    	}
    	
    }; 	
    
    $scope.cancelClauseSettlement  = function() {// 取消编辑结算条款
    	$scope.clauseSettlementInput = true;
	    $scope.clauseSettlementShow = true;
    };
    
    $scope.editClauseSettlement  = function() {// 进入编辑结算条款
    	$scope.clauseSettlementInput = false;
	    $scope.clauseSettlementShow = false;
    };
		   	   
    
    var _index = 0;
    /**
      * ClauseFramework新增一行
      */
    $scope.addCSD = function(){
    	if($scope.clauseSettlement.CSD){}else{$scope.clauseSettlement.CSD =[{}]}
 	    
    	$scope.clauseSettlement.CSD[_index] = {};
 	    $scope.clauseSettlement.CSD[_index].deliveryRate = 100 - $scope._totalRate();
 	    $scope.clauseSettlement.CSD[_index].deliveryAmount = ($scope.totalOrderAmount()*$scope.clauseSettlement.CSD[_index].deliveryRate/100).toFixed(2);
 	    $scope.clauseSettlement.CSD[_index].billingAmount =  Number($scope._totaldeliveryAmount()) - Number($scope._totalbillingAmount());
 	    $scope.clauseSettlement.CSD[_index].unbilledAmount = 0;
 	    
 	   _index++;
    };
    
    
    $scope._totalRate  = function() {//计算总的支付比例
       	var totalRate = 0;
    	for(var i=0;i<$scope.clauseSettlement.CSD.length;i++){
    		if($scope.clauseSettlement.CSD[i].deliveryRate){
    			totalRate = totalRate + Number($scope.clauseSettlement.CSD[i].deliveryRate);
    		}
       	}
    	return totalRate;
   };
   
   
  $scope._totalUnbilledAmount  = function() {//计算所有未开票金额
      	var totalUnbilledAmount = 0;
   	for(var i=0;i<$scope.clauseSettlement.CSD.length;i++){
   		if($scope.clauseSettlement.CSD[i].unbilledAmount){
   			totalUnbilledAmount = totalUnbilledAmount + Number($scope.clauseSettlement.CSD[i].unbilledAmount);
   		}
      	}
   	return totalUnbilledAmount;
  };
  
  $scope._totalbillingAmount  = function() {//计算所有开票金额
    	var totalbillingAmount = 0;
 	for(var i=0;i<$scope.clauseSettlement.CSD.length;i++){
 		if($scope.clauseSettlement.CSD[i].billingAmount){
 			totalbillingAmount = totalbillingAmount + Number($scope.clauseSettlement.CSD[i].billingAmount);
 		}
    	}
 	return totalbillingAmount;
};
$scope._totaldeliveryAmount  = function() {//计算所有支付金额
	var totaldeliveryAmount = 0;
	for(var i=0;i<$scope.clauseSettlement.CSD.length;i++){
		if($scope.clauseSettlement.CSD[i].deliveryAmount){
			totaldeliveryAmount = totaldeliveryAmount + Number($scope.clauseSettlement.CSD[i].deliveryAmount);
		}
	}
	return totaldeliveryAmount;
};
   
   $scope._arithmeticRate  = function(scope) {//计算支付比例
      	
	   scope._CSD.deliveryRate =  ((scope._CSD.deliveryAmount/$scope.totalOrderAmount())*100).toFixed(2);
	   
	   if($scope._totalRate()>100){
      		scope._CSD.deliveryRate = scope._CSD.deliveryRate - $scope._totalRate() + 100
      	}
   	
	   $scope._arithmeticDeliveryAmount(scope);
  };
  
    $scope._arithmeticDeliveryAmount  = function(scope) {//计算支付金额
       	if($scope._totalRate()>100){
       		scope._CSD.deliveryRate = scope._CSD.deliveryRate - $scope._totalRate() + 100
       	}
    	
    	if(scope._CSD.deliveryRate){
       		scope._CSD.deliveryAmount =  ($scope.totalOrderAmount()*scope._CSD.deliveryRate/100).toFixed(2);
       	}
    	scope._CSD.billingAmount = (Number($scope._totaldeliveryAmount()) - Number($scope._totalbillingAmount()) + Number(scope._CSD.billingAmount)).toFixed(2);
   		scope._CSD.unbilledAmount = 0 ;
   };
   
   $scope._arithmeticUnbilledAmount  = function(scope) {//计算未开金额
       	if(scope._CSD.billingAmount&&scope._CSD.deliveryAmount){
       		scope._CSD.unbilledAmount =  (Number($scope._totaldeliveryAmount()) - Number($scope._totalbillingAmount())).toFixed(2);
       	}
       	if(scope._CSD.unbilledAmount<0){
       		scope._CSD.billingAmount = (Number($scope._totaldeliveryAmount()) - Number($scope._totalbillingAmount()) + Number(scope._CSD.billingAmount)).toFixed(2);
       		scope._CSD.unbilledAmount = 0 ;
       	}
   };
   
   $scope._arithmeticBilledAmount  = function(scope) {//计算已开金额
      	if(scope._CSD.unbilledAmount&&scope._CSD.deliveryAmount){
      		scope._CSD.billingAmount =  (Number(scope._CSD.deliveryAmount) - Number(scope._CSD.unbilledAmount)).toFixed(2);
      	}
      	if(scope._CSD.billingAmount<0){
      		scope._CSD.unbilledAmount = scope._CSD.deliveryAmount;
      		scope._CSD.billingAmount = 0 ;
      	}
  };
   
   
    /**
     * ClauseFramework删除一行
     */
    $scope.deleteCSD = function(index){
 	   $scope.clauseSettlement.CSD.splice(index,1);
 	   _index--;
    };
    
    
   var validateCSDInit = function() {
     	var e = $("#form_sample_3");
	        r = $(".alert-danger", e),
	        i = $(".alert-success", e);
	        e.validate({
	            errorElement: "span",
	            errorClass: "help-block help-block-error",
	            focusInvalid: !1,
	            ignore: "",
	            messages: {},
         	rules: {},
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

/** ***************结算条款end******************** */
/** ***************垫资条款start******************** */
    var validateClauseAdvanceInit = function() {// 垫资条款表单验证
    	var e = $("#form_clauseAdvance"),
        r = $(".alert-danger", e),
        i = $(".alert-success", e);
        e.validate({
            errorElement: "span",
            errorClass: "help-block help-block-error",
            focusInvalid: !1,
            ignore: "",
            messages: {},
        	rules: {},
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
 
        $scope.saveClauseAdvance  = function() {// 保存垫资条款
   	    	if($scope.contract.id==null||$scope.contract.id=='') {// 合同信息为空的处理
   	    		toastr.error('请先保存订单信息！');return
   			}
   	    	if(isNull($scope.clauseAdvance)){// 垫资条款为空的处理
 	    		toastr.error('请填写垫资条款后保存！');return
 			}
   	    	if($('#form_clauseAdvance').valid()){
   	    		$scope.clauseAdvance.contractSerial = $scope.contract.id;
   	    		orderService.saveClauseAdvance($scope.clauseAdvance).then(
   	       		     function(data){
   	       		    	toastr.success('数据保存成功！');
   	       		    	$scope.clauseAdvance = data.data;
   	       		    	$scope.cancelClauseAdvance();
   	       		     },
   	       		     function(error){
   	       		    	toastr.error('数据保存出错！');
   	       		         $scope.error = error;
   	       		     }
   	       		 );
   	    	}
   	    	
   	    }; 	
   	    
   	    $scope.cancelClauseAdvance  = function() {// 取消编辑垫资条款
   	    	$scope.clauseAdvanceInput = true;
   		    $scope.clauseAdvanceShow = true;
   	    };
   	    
   	    $scope.editClauseAdvance  = function() {// 进入编辑垫资条款
   	    	$scope.clauseAdvanceInput = false;
   		    $scope.clauseAdvanceShow = false;
   	    };
				   	    
/** ***************垫资条款end******************** */
/** ***************交付条款start******************** */
   	    var validateClauseDeliveryInit = function() {// 交付条款表单验证
   	    	var e = $("#form_clauseDelivery"),
   	        r = $(".alert-danger", e),
   	        i = $(".alert-success", e);
   	        e.validate({
   	            errorElement: "span",
   	            errorClass: "help-block help-block-error",
   	            focusInvalid: !1,
   	            ignore: "",
   	      messages: {
          	deliveryMode:{required:"提货方式不能为空！"}
          },
      	rules: {
      		deliveryMode: {required: !0,maxlength: 20}
      			},
      		invalidHandler: function(e, t) {
              i.hide(), r.show(), App.scrollTo(r, -200)
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
   	 
   	        $scope.saveClauseDelivery  = function() {// 保存交付条款
   	   	    	if($scope.contract.id==null||$scope.contract.id=='') {// 合同信息为空的处理
   	   	    		toastr.error('请先保存订单信息！');return
   	   			}
   	   	    	if(isNull($scope.clauseDelivery)){// 交付条款为空的处理
   	   	    		toastr.error('请填写交付条款后保存！');return
   	   			}
   	   	    	if($('#form_clauseDelivery').valid()){
   	   	    		$scope.clauseDelivery.contractSerial = $scope.contract.id;
   	   	    		orderService.saveClauseDelivery($scope.clauseDelivery).then(
   	   	       		     function(data){
   	   	       		    	toastr.success('数据保存成功！');
   	   	       		    	$scope.clauseDelivery = data.data;
   	   	       		    	$scope.cancelClauseDelivery();
   	   	       		     },
   	   	       		     function(error){
   	   	       		    	toastr.error('数据保存出错！');
   	   	       		         $scope.error = error;
   	   	       		     }
   	   	       		 );
   	   	    	}
   	   	    	
   	   	    }; 	
   	   	    
   	   	    $scope.cancelClauseDelivery  = function() {// 取消编辑交付条款
   	   	    	$scope.clauseDeliveryInput = true;
   	   		    $scope.clauseDeliveryShow = true;
   	   	    };
   	   	    
   	   	    $scope.editClauseDelivery  = function() {// 进入编辑交付条款
   	   	    	$scope.clauseDeliveryInput = false;
   	   		    $scope.clauseDeliveryShow = false;
   	   	    };
   					   	    
/** ***************交付条款end******************** */
/** ***************验收条款start******************** */
   	   	    var validateClauseCheckAcceptInit = function() {// 验收条款表单验证
   	   	    	var e = $("#form_clauseCheckAccept"),
   	   	        r = $(".alert-danger", e),
   	   	        i = $(".alert-success", e);
   	   	        e.validate({
   	   	            errorElement: "span",
   	   	            errorClass: "help-block help-block-error",
   	   	            focusInvalid: !1,
   	   	            ignore: "",
   	   	            messages: {},
   	   	        	rules: {},
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
   	   	 
   	   	        $scope.saveClauseCheckAccept  = function() {// 保存验收条款
   	   	   	    	if($scope.contract.id==null||$scope.contract.id=='') {// 合同信息为空的处理
   	   	   	    		toastr.error('请先保存订单信息！');return
   	   	   			}
		   	   	   	if(isNull($scope.clauseCheckAccept)){// 验收条款为空的处理
		   	    		toastr.error('请填写验收条款后保存！');return
		   			}
   	   	   	    	if($('#form_clauseCheckAccept').valid()){
   	   	   	    		$scope.clauseCheckAccept.contractSerial = $scope.contract.id;
   	   	   	    		orderService.saveClauseCheckAccept($scope.clauseCheckAccept).then(
   	   	   	       		     function(data){
   	   	   	       		    	toastr.success('数据保存成功！');
   	   	   	       		    	$scope.clauseCheckAccept = data.data;
   	   	   	       		    	$scope.cancelClauseCheckAccept();
   	   	   	       		     },
   	   	   	       		     function(error){
   	   	   	       		    	toastr.error('数据保存出错！');
   	   	   	       		         $scope.error = error;
   	   	   	       		     }
   	   	   	       		 );
   	   	   	    	}
   	   	   	    	
   	   	   	    }; 	
   	   	   	    
   	   	   	    $scope.cancelClauseCheckAccept  = function() {// 取消编辑验收条款
   	   	   	    	$scope.clauseCheckAcceptInput = true;
   	   	   		    $scope.clauseCheckAcceptShow = true;
   	   	   	    };
   	   	   	    
   	   	   	    $scope.editClauseCheckAccept  = function() {// 进入编辑验收条款
   	   	   	    	$scope.clauseCheckAcceptInput = false;
   	   	   		    $scope.clauseCheckAcceptShow = false;
   	   	   	    };
   	   					   	    
/** ***************验收条款end******************** */
/** ***************售后条款start******************** */
   	    var validateClauseAfterSalesInit = function() {// 售后条款表单验证
   	    	var e = $("#form_clauseAfterSales"),
   	        r = $(".alert-danger", e),
   	        i = $(".alert-success", e);
   	        e.validate({
   	            errorElement: "span",
   	            errorClass: "help-block help-block-error",
   	            focusInvalid: !1,
   	            ignore: "",
   	            messages: {},
   	        	rules: {},
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
   	 
   	        $scope.saveClauseAfterSales  = function() {// 保存售后条款
   	   	    	if($scope.contract.id==null||$scope.contract.id=='') {// 合同信息为空的处理
   	   	    		toastr.error('请先保存订单信息！');return
   	   			}
   	   	    	if(isNull($scope.clauseAfterSales)){// 售后条款为空的处理
	 	    		toastr.error('请填写售后条款后保存！');return
	 			}
   	   	    	if($('#form_clauseAfterSales').valid()){
   	   	    		$scope.clauseAfterSales.contractSerial = $scope.contract.id;
   	   	    		orderService.saveClauseAfterSales($scope.clauseAfterSales).then(
   	   	       		     function(data){
   	   	       		    	toastr.success('数据保存成功！');
	   	   	       		    $scope.clauseAfterSales = data.data;
   	   	       		    	$scope.cancelClauseAfterSales();
   	   	       		     },
   	   	       		     function(error){
   	   	       		    	toastr.error('数据保存出错！');
   	   	       		         $scope.error = error;
   	   	       		     }
   	   	       		 );
   	   	    	}
   	   	    	
   	   	    }; 	
   	   	    
   	   	    $scope.cancelClauseAfterSales  = function() {// 取消编辑售后条款
   	   	    	$scope.clauseAfterSalesInput = true;
   	   		    $scope.clauseAfterSalesShow = true;
   	   	    };
   	   	    
   	   	    $scope.editClauseAfterSales  = function() {// 进入编辑售后条款
   	   	    	$scope.clauseAfterSalesInput = false;
   	   		    $scope.clauseAfterSalesShow = false;
   	   	    };
   					   	    
 /** ***************售后条款end******************** */
   	   	    
   	   	 //********附件  start****************//
   	   		var _fileIndex = 0;
   	   	    $scope.saveFile  = function() {//保存File信息
   	   	    	if($scope.buyOrder.serialNum==null||$scope.buyOrder.serialNum=='') {// 订单信息为空的处理
   	   	    		toastr.error('请先保存订单信息！');return
   	    		}
   	   	    	if($('#form_sample_4').valid()){
   	   	    	orderService.saveFile($scope.file).then(
   	   	       		     function(data){
   	   	       		    	toastr.success('数据保存成功！');
   	   	       		    	$scope.cancelFile();
   	   	       		    	
   	   	       		     },
   	   	       		     function(error){
   	   	       		    	toastr.error('数据保存出错！');
   	   	       		         $scope.error = error;
   	   	       		     }
   	   	       		 );
   	   	    	}
   	   	    	
   	   	    }; 	
   	   	    
   	   	    $scope.cancelFile  = function() {//取消编辑File信息
   	   	    	$scope.fileInfoInput = true;
   	   		    $scope.fileInfoShow = true;
   	   	    };
   	   	    
   	   	    $scope.editFile  = function() {//进入编辑File信息
   	   	    	$scope.fileInfoInput = false;
   	   		    $scope.fileInfoShow = false;
   	   	    };
   	   	    /**
   	 	        * File新增一行
   	 	        */
   	   	    $scope.addFile = function(){
		   	   	  if($scope.buyOrder.serialNum==null||$scope.buyOrder.serialNum=='') {// 订单信息为空的处理
		 	    		toastr.error('请先保存订单信息！');return
		 			}else{
   	   		    	   if($scope.file){}else{$scope.file =[{}]}
   	   		    	   $scope.file[_fileIndex] = {};
   	   		    	   $scope.file[_fileIndex].orderSerial = $scope.buyOrder.serialNum;
   	   		    	   _fileIndex++;
   	   		       }
   	   	    };
   	   	    
   	   	    /**
   		        * File删除一行
   		        */
   		       $scope.deleteFile = function(index){
   		    	   $scope.file.splice(index,1);
   		    	   _fileIndex--;
   		       };
   		       
   		       
   		      var validateFileInit = function() {
   		        	var e = $("#form_sample_4");
   			        r = $(".alert-danger", e),
   			        i = $(".alert-success", e);
   			        e.validate({
   			            errorElement: "span",
   			            errorClass: "help-block help-block-error",
   			            focusInvalid: !1,
   			            ignore: "",
   			            messages: {
   			            },
   		            	rules: {
   		            			
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

   		       $scope.downloadOrderFile = function(obj){
   		    	   if(!handle.isNull(obj)){
   		    		   window.location.href= $rootScope.basePath+"/rest/fileOperate/downloadFile?fileName="+encodeURI(encodeURI(obj.file));
   		    	   }else{
   		    		   toastr.error("下载失败!");
   		    	   }
   		       }
   		       
   		      
   		        
   	   	  //********附件  end****************//
   		  //********框架条款 start****************//
   		    $scope.hidnClauseFramework  = function() {//隐藏框架条款
   	   	    	$scope.ClauseFrameworkShow = false;
   	   	    };
   	   	    
   	   	    $scope.showClauseFramework  = function() {//显示框架条款
   	   	    	$scope.ClauseFrameworkShow = true;
   	   	    };
   	   	    
   	   	var _indexClauseFramework = 0;
	    $scope.saveClauseFramework  = function() {//保存ClauseFramework信息
	    	if($scope.contract.id==null||$scope.contract.id=='') {// 合同信息为空的处理
	   	    		toastr.error('请先保存订单信息！');return
	   			}
	    	if($('#form_sample_framework').valid()){
	    		orderService.saveClauseFramework($scope.ClauseFramework).then(
	       		     function(data){
	       		    	toastr.success('数据保存成功！');
//	       		    	if(!isNull(data.ClauseFramework)){
//	 	        			$scope.ClauseFramework = data.ClauseFramework;
//	 	        			_indexClauseFramework = $scope.ClauseFramework.length-1;
//	 	        		}
	       		    	$scope.cancelClauseFramework();
	       		     },
	       		     function(error){
	       		    	toastr.error('数据保存出错！');
	       		         $scope.error = error;
	       		     }
	       		 );
	    	}
	    	
	    }; 	
	    
	    $scope.cancelClauseFramework  = function() {//取消编辑ClauseFramework信息
	    	$scope.ClauseFrameworkInfoInput = true;
		    $scope.ClauseFrameworkInfoShow = true;
	    };
	    
	    $scope.editClauseFramework  = function() {//进入编辑ClauseFramework信息
	    	$scope.ClauseFrameworkInfoInput = false;
		    $scope.ClauseFrameworkInfoShow = false;
	    };
	    /**
	        * ClauseFramework新增一行
	        */
	    $scope.addClauseFramework = function(){
	    	if($scope.contract.id==null||$scope.contract.id=='') {// 合同信息为空的处理
   	    		toastr.error('请先保存订单信息！');return
   			}else{
		    	   if($scope.ClauseFramework){}else{$scope.ClauseFramework =[{}]}
	   		    	   $scope.ClauseFramework[_indexClauseFramework] = {};
	   		    	   $scope.ClauseFramework[_indexClauseFramework].contractSerial = $scope.contract.id;
	   		    	_indexClauseFramework++;
		       }
	    };
	    
	    /**
	        * ClauseFramework删除一行
	        */
	       $scope.deleteClauseFramework = function(index){
	    	   $scope.ClauseFramework.splice(index,1);
	    	   _indexClauseFramework--;
	       };
	       
	       
	       
	      var validateClauseFrameworkInit = function() {
	        	var e = $("#form_sample_framework");
		        r = $(".alert-danger", e),
		        i = $(".alert-success", e);
		        e.validate({
		            errorElement: "span",
		            errorClass: "help-block help-block-error",
		            focusInvalid: !1,
		            ignore: "",
		            messages: {},
	            	rules: {},
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
   		  //********框架条款  end****************//
	      //********订单提交start****************//
	        $scope.cancelPage  = function() {// 取消编辑
	        	$state.go("buyOrder");
	        };
	        $scope.submitPage  = function() {// 提交审核
	        	$scope.submitOrder = {}
	        	$scope.submitOrder.serialNum = $scope.buyOrder.serialNum;
	        	$scope.submitOrder.remark = $scope.buyOrder.remark;
	        	$scope.submitOrder.status = 1;
	        	$scope.buyOrder.status = 1;
	        	orderService.save($scope.submitOrder).then(
	          		     function(data){
	          		    	$scope.contract.orderSerial = data.serialNum;
	          		    	if(isNull($scope.contract.contractNum)){
	           		    		$scope.contract.contractNum = $scope.buyOrder.orderNum;
	           		    	}
	    	   	    		$scope.contract.comId = $scope.buyOrder.supplyComId;
	    	   	    		orderService.saveContract($scope.contract).then(
	    	   	       		     function(data){
	    	   	       		    	toastr.success('数据保存成功！');
	    	   	       		    	$scope.contract = data.data;
	    	   	       		     },
	    	   	       		     function(error){
	    	   	       		    	toastr.error('数据保存出错！');
	    	   	       		         $scope.error = error;
	    	   	       		     }
	    	   	       		 );
	          		    	$scope.cancelOrderStatus();
//	          		    	$location.search({serialNum:data.serialNum,view:'all'});
	          		     },
	          		     function(error){
	          		         $scope.error = error;
	          		         toastr.error('数据保存出错！');
	          		     }
	          		 );
	        };
	        
	        $scope.cancelOrderStatus  = function() {//隐藏编辑备注及提交
	        	$scope.orderStatusShow = true;
	        	$scope.orderStatusInput = true;
		    };
		    //********订单提交end****************//
		    
		    //********导入导出start****************//
	 	      /**
		        * 下载EXCEL模板
		        */
		       $scope.downloadImportTemp = function(){
		    	   window.location.href=$rootScope.basePath+"/rest/order/downloadImportTemp?type=buy";
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
		    	   	var promise = orderService.buyUploadExcel();
	       			promise.then(function(data){
	       				handle.unblockUI(); 
	       				if(data.data.data=="success"){
	       					toastr.success("导入成功");
	       					table.ajax.reload();
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
		       
		       
		       $scope.exportBuyOrder = function(){
			    	 handle.blockUI("正在导出数据，请稍后"); 
			    	 window.location.href=$rootScope.basePath+"/rest/order/exportOrder?type=buy";
			    	 handle.unblockUI(); 
			   }
		       $scope.exportBuyFramOrder = function(){
			    	 handle.blockUI("正在导出数据，请稍后"); 
			    	 window.location.href=$rootScope.basePath+"/rest/order/exportOrder?type=buy&&fram=1";
			    	 handle.unblockUI(); 
			   }
		       //********导入导出end****************//
		     //********订单物料合计，结算条款start****************//
		       $scope.totalCount  = function() {//订单物料数量
			       	if($scope.orderMateriel){
			       		return $scope.orderMateriel.length;
			       	}else{
			       		return 0;
			       	}
		       };
		       
		       $scope.totalOrderAmount  = function(scope) {//订单金额（外贸：商品金额+其他金额，内贸：价税合计（商品金额+税额含关税）+ 其他金额）
		    	   if(isNull($scope.clauseSettlement)||isNull($scope.clauseSettlement.otherAmount)){
		    		   if(!isNull($scope.buyOrder)&&$scope.buyOrder.tradeType =='外贸'){
		    			   return Number($scope.totalAmount());
		    		   }else{
		    			   return Number($scope.totalAmount()) + Number($scope.totalRateAndCustomsAmount());
		    		   }
			       		
			       	}else{
			       	   if(!isNull($scope.buyOrder)&&$scope.buyOrder.tradeType =='外贸'){
		    			   return Number($scope.totalAmount()) + Number($scope.clauseSettlement.otherAmount)
		    		   }else{
		    			   return Number($scope.totalAmount()) + Number($scope.totalRateAndCustomsAmount()) + Number($scope.clauseSettlement.otherAmount);
		    		   }
			       	}
		       };
		       
		       $scope.totalAmount  = function(scope) {//商品金额
		    	   if($scope.orderMateriel){
		    		    var total = 0 ; 
			       		for(var i=0;i<$scope.orderMateriel.length;i++){
			       			total = total + Number($scope.arithmeticAmount($scope.orderMateriel[i]));
			       		}
			       		return total.toFixed(2)
			       	}else{
			       		return 0;
			       	}
		       };
		       $scope.totalRateAmount  = function(scope) {//订单税额
		    	   if($scope.orderMateriel){
		    		    var total = 0 ; 
		    		    for(var i=0;i<$scope.orderMateriel.length;i++){
			       			total = total + Number($scope.arithmeticRateAmount($scope.orderMateriel[i]));
			       		}
			       		return total.toFixed(2)
			       	}else{
			       		return 0;
			       	}
		       };
		       $scope.totalCustomsRateAmount  = function(scope) {//订单关税
		    	   if($scope.orderMateriel){
		    		    var total = 0 ; 
		    		    for(var i=0;i<$scope.orderMateriel.length;i++){
			       			total = total + Number($scope.arithmeticCustomsRateAmount($scope.orderMateriel[i]));
			       		}
			       		return total
			       	}else{
			       		return 0;
			       	}
		       };
		       
		       $scope.totalRateAndCustomsAmount  = function(scope) {//订单税额(含关税)
		    	   if(!isNull($scope.buyOrder)&&$scope.buyOrder.tradeType =='外贸'){//税额+关税
	    		    	return Number($scope.totalRateAmount()) + Number($scope.totalCustomsRateAmount());
			    	}else{
			    		return $scope.totalRateAmount()
			    	}
		       };
		       
		       $scope.totalRateAndAmount  = function(scope) {//求和订单价税合计
		    	   if($scope.orderMateriel){
		    		    var total = 0 ; 
			       		for(var i=0;i<$scope.orderMateriel.length;i++){
			       			total = total + Number($scope.arithmeticRateAndAmount($scope.orderMateriel[i]));
			       		}
			       		return total.toFixed(2)
			       	}else{
			       		return 0;
			       	}
		       };
		       
		       $scope.arithmeticRateUnit  = function(scope) {//计算含税采购单价
			       	if(scope.orderUnitPrice&&$scope.buyOrder.rate){
			       		return (scope.orderUnitPrice*($scope.buyOrder.rate/100+1)).toFixed(4);
			       	}else{
			       		return 0;
			       	}
		       };
		       
		       $scope.arithmeticAmount  = function(scope) {//计算金额
			       	if(scope.orderUnitPrice&&scope.amount){
			       		return (scope.orderUnitPrice*scope.amount).toFixed(2);
			       	}else{
			       		return 0;
			       	}
		       };
		       $scope.arithmeticRateAmount  = function(scope) {//计算税额
			       	if($scope.buyOrder.rate){
			       		return ($scope.arithmeticAmount(scope)*$scope.buyOrder.rate/100).toFixed(2);
			       	}else{
			       		return 0;
			       	}
		       };
		       
		       $scope.arithmeticCustomsRateAmount  = function(scope) {//计算关税
			       	if(scope.customsRate){
			       		return ($scope.arithmeticAmount(scope)*scope.customsRate/100).toFixed(2);
			       	}else{
			       		return 0;
			       	}
		       };
		       
		       $scope.arithmeticRateAndAmount  = function(scope) {//计算价税合计
		    	   if(!isNull($scope.buyOrder)&&$scope.buyOrder.tradeType =='外贸'){//税额+关税
		    		   return Number($scope.arithmeticAmount(scope))+Number($scope.arithmeticRateAmount(scope))+Number($scope.arithmeticCustomsRateAmount(scope));
			    	}else{
			    		return Number($scope.arithmeticAmount(scope))+Number($scope.arithmeticRateAmount(scope));
			    	}
		       };
		       
		       
		       $scope._arithmeticRateUnit  = function(_orderMateriel) {//计算含税采购单价
			       	if(_orderMateriel.orderUnitPrice&&$scope.buyOrder.rate){
			       		_orderMateriel.orderRateUnit  =  (_orderMateriel.orderUnitPrice*($scope.buyOrder.rate/100+1)).toFixed(4);
			       	}else{
			       		_orderMateriel.orderRateUnit  =   0;
			       	}
		       };
		       
		       $scope._arithmeticUnitPrice  = function(_orderMateriel) {//计算不含税采购单价
			       	if(_orderMateriel.orderRateUnit&&$scope.buyOrder.rate){
			       		_orderMateriel.orderUnitPrice  =  (_orderMateriel.orderRateUnit/($scope.buyOrder.rate/100+1)).toFixed(4);
			       	}else{
			       		_orderMateriel.orderUnitPrice  =   0;
			       	}
		       };
		       
		       $scope._arithmeticAmount  = function(scope) {//计算金额
			       	if(scope._orderMateriel.orderUnitPrice&&scope._orderMateriel.amount){
			       		return (scope._orderMateriel.orderUnitPrice*scope._orderMateriel.amount).toFixed(2);
			       	}else{
			       		return 0;
			       	}
		       };
		       $scope._arithmeticRateAmount  = function(scope) {//计算税额
			       	if($scope.buyOrder.rate){
			       		return ($scope._arithmeticAmount(scope)*$scope.buyOrder.rate/100).toFixed(2);
			       	}else{
			       		return 0;
			       	}
		       };
		       
		       $scope._arithmeticCustomsRateAmount  = function(scope) {//计算关税
			       	if(scope._orderMateriel.customsRate){
			       		return ($scope._arithmeticAmount(scope)*scope._orderMateriel.customsRate/100).toFixed(2);
			       	}else{
			       		return 0;
			       	}
		       };
		       
		       $scope._arithmeticRateAndAmount  = function(scope) {//计算价税合计（商品金额+税额）
		    	   if(!isNull($scope.buyOrder)&&$scope.buyOrder.tradeType =='外贸'){//税额+关税
		    		   return Number($scope._arithmeticAmount(scope))+Number($scope._arithmeticRateAmount(scope))+Number($scope._arithmeticCustomsRateAmount(scope));
			    	}else{
			    		return Number($scope._arithmeticAmount(scope))+Number($scope._arithmeticRateAmount(scope));
			    	}
			       	
		       };
		       
		       $scope.clearNoNumPoint = function(obj,attr){
			    	 //先把非数字的都替换掉，除了数字和.
			    	 obj[attr] = obj[attr].replace(/[^\d.]/g,"");
			    	 //必须保证第一个为数字而不是.
			    	 obj[attr] = obj[attr].replace(/^\./g,"");
			    	 //保证只有出现一个.而没有多个.
			    	 obj[attr] = obj[attr].replace(/\.{2,}/g,"");
			    	 //保证.只出现一次，而不能出现两次以上
			    	 obj[attr] = obj[attr].replace(".","$#$").replace(/\./g,"").replace("$#$",".");
		    	 }
		       
		       $scope.clearNoNum = function(obj,attr){
			    	 //把非数字的都替换掉
			    	 obj[attr] = obj[attr].replace(/[^\d]/g,"");
		    	 }

		       
		     //更新订单金额数据
		     $scope.updateOrderAmount = function(obj,attr){
		    	$scope.submitOrder = {}
   	        	$scope.submitOrder.serialNum = $scope.buyOrder.serialNum;
   	        	$scope.submitOrder.materielCount = $scope.totalCount();
   	        	$scope.submitOrder.materielAmount = $scope.totalAmount();
      	        $scope.submitOrder.rateAmount = $scope.totalRateAndCustomsAmount();
      	        $scope.submitOrder.rateAndAmount = $scope.totalRateAndAmount();
      	        $scope.submitOrder.otherAmount = $scope.clauseSettlement.otherAmount;
      	        $scope.submitOrder.orderAmount = $scope.totalOrderAmount();
	    	    orderService.save($scope.submitOrder).then(
          		     function(data){
          		    	
          		     },
          		     function(error){
          		         $scope.error = error;
          		         toastr.error('数据保存出错！');
          		     }
          		 );
		      }
		     
		     /*$scope._arithmeticDeliveryAmount  = function(scope) {//计算支付金额
			       	if(scope._CSD.deliveryRate){
			       		scope._CSD.deliveryAmount =  ($scope.totalOrderAmount()*scope._CSD.deliveryRate/100).toFixed(2);
			       	}
			       	$scope._arithmeticUnbilledAmount(scope);
		       };
		       
		       $scope._arithmeticUnbilledAmount  = function(scope) {//计算未开金额
			       	if(scope._CSD.billingAmount&&scope._CSD.deliveryAmount){
			       		scope._CSD.unbilledAmount =  (Number(scope._CSD.deliveryAmount) - Number(scope._CSD.billingAmount)).toFixed(2);
			       	}
		       };*/
		     //********订单物料合计，结算条款end****************//
		       
		     //********审批流程start****************//
		       $scope.submitBuyApply  = function() {// 进入申请审批页面
		        	if(table.rows('.active').data().length != 1){
		    			showToastr('toast-top-center', 'warning', '请选择一条任务进行流程申请！')
		    		}else{
		    			var processBase = table.row('.active').data().processBase;
		    			if(processBase != null){
		    				showToastr('toast-top-center', 'warning', '该订单已发起流程审批，不能再次申请！')
		    			}else $state.go('submitBuyApply',{serialNum:table.row('.active').data().serialNum});
		    		}     	
		        };
		        
		        
		        $scope.confirmBuyApply  = function() {// 进入提交申请
		        	$scope.submitOrder = {}
		        	$scope.submitOrder.serialNum = $scope.buyOrder.serialNum;
		        	$scope.submitOrder.remark = $scope.buyOrder.remark;
		        	$scope.submitOrder.orderNum = $scope.buyOrder.orderNum;
		        	//启动流程
		        	orderService.startBuyOrderProcess($scope.submitOrder).then(
		          		     function(data){
		          		    	if(data == "1"){
			        				toastr.success("提交申请成功！");
			        				$scope.cancelPage();
			        			}else{
			        				toastr.error("提交申请失败！");
					            	console.log(data);
			        			}
		          		     },
		          		     function(error){
		          		         $scope.error = error;
		          		         toastr.error('数据保存出错！');
		          		     }
		          		 );
		    		
		        };
		      //********合同内容操作start****************//  
		        $scope.getContentStatus = function(Str,index){
		    		if(!isNull(Str)&&index>=1){
		    			return Str.substring(index-1,index)
		    		}else{
		    			return ''
		    		}
		    	}
		        $scope.changeStr = function(allstr,index,changeStr){ //allstr:原始字符串，start,开始位置,end：结束位  置,str：要改变的字，changeStr:改变后的字
		        	 return allstr.substring(0,index-1)+changeStr+allstr.substring(index,allstr.length); 
		        }
		        
		        
		        /**
		    	 * 初始化选中状态
		    	 */
		    	$scope.initContractContent = function(){
		    		for(var i=1;i<=6;i++){
		    			if($scope.getContentStatus($scope.buyOrder.contractContent,i)==1){
		    				$("#tab_1_"+i+"Id").addClass("active");
		    				$scope["tab_1_"+i+"Hide"] = false
		    			}else{
		    				$("#tab_1_"+i+"Id").removeClass("active");
		    				$scope["tab_1_"+i+"Hide"] = true
		    			}
		    		}
		    	}
		    	
		    	$scope.changeContentStatus = function(index){
		    		if($scope.getContentStatus($scope.buyOrder.contractContent,index)==1){
		    			$scope.buyOrder.contractContent = $scope.changeStr($scope.buyOrder.contractContent,index,0);
		    			/*$("#tab_1_"+index+"Id").removeClass("active");*/
	    				$scope["tab_1_"+index+"Hide"] = true
		    		}else{
		    			$scope.buyOrder.contractContent = $scope.changeStr($scope.buyOrder.contractContent,index,1);
		    			/*$("#tab_1_"+index+"Id").addClass("active");*/
	    				$scope["tab_1_"+index+"Hide"] = false
		    		}
		    	}
		    	
		    	//********合同内容操作end ****************//   
		      //********审批流程列表****************//
		        function showDbTable(){
		        	
		        	var table = $("#dbTable")
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

		        				buttons : [
		        						{
		        							text : "办理",
		        							className : "btn default",
		        							action: function(e, dt, node, config) { 
		        								
		        								var ids = '';
		        								table.$('input[type="checkbox"]').each(function() {
		        									if ($.contains(document, this)) {											
		        										if (this.checked) {
		        											// 将选中数据id放入ids中
		        											if (ids == '') {
		        												ids = this.value;
		        											} else
		        												ids = 'more';
		        										}
		        									}
		        								});
		        								
		        								
		        								if(ids==''){
		        									toastr.warning('请选择一个办理！');return;
		        								}else if(ids=='more'){
		        									toastr.warning('只能选择一个办理！');return;
		        								} else {
		        									if(table.row('.active').data().assign == ''){
		    											showToastr('toast-top-center', 'warning', '此任务您还没有签收，请【签收】任务后再处理任务！')
		    											return;
		    										}
		        									orderService
		        									.getAuditInfos(ids)
													.then(
															function(result) {													
		        												var comments = ""//添加评论
			        												for (var i=0;i<result.commentList.length;i++){
			        													comments += "<tr><td>" + result.commentList[i].userName + "</td><td>" 
			        													+ timeStamp2String(result.commentList[i].time) + "</td><td>" + result.commentList[i].content + "</td></tr>";														
			        												}
			        												if(result.actionType == 'audit'){//审批流程
			        													$state.go('approvalBuyApply',{serialNum:result.orderInfo.serialNum, taskId:ids, comments:comments,processInstanceId:result.orderInfo.processInstanceId});
			        												}else{
			        													$state.go('editBuyApply',{serialNum:result.orderInfo.serialNum, taskId:ids, comments:comments,processInstanceId:result.orderInfo.processInstanceId});
			        												}
			        											},
															function(errResponse) {
			        												toastr.warning("办理失败！");
			        												console.error('Error while apply ap');
			        											}
			
													);
		        								}
		        							}
		        						},
		        						{
		        							text : "签收",
		        							className : "btn default",
		        							action: function(e, dt, node, config) { 
		        								var ids = '';
		        								table.$('input[type="checkbox"]').each(function() {
		        									if ($.contains(document, this)) {											
		        										if (this.checked) {
		        											// 将选中数据id放入ids中
		        											if (ids == '') {
		        												ids = this.value;
		        											} else
		        												ids = 'more';
		        										}
		        									}
		        								});
		        								
		        								if(ids==''){
		        									toastr.warning('请选择一个签收！');return;
		        								}else if(ids=='more'){
		        									toastr.warning('只能选择一个签收！');return;
		        								} else {
		        									claimTask(ids, 'dbTable');
		        								}
		        								
		        							}
		        						},
		        						{
		        							text : "转办",
		        							className : "btn default"
		        						},
		        						{
		        							text : "委派",
		        							className : "btn default"
		        						},
		        						{
		        							text : "跳转",
		        							className : "btn default"
		        						} ],
		        				dom : "<'row' <'col-md-12'B>><'row'<'col-md-6 col-sm-12'l><'col-md-6 col-sm-12'f>r><'table-scrollable't><'row'<'col-md-5 col-sm-12'i><'col-md-7 col-sm-12'p>>",
		        				order : [ [ 1, "asc" ] ],// 默认排序列及排序方式

		        				bRetrieve : true,
		        				lengthMenu : [
		        						[ 5, 10, 15, 30, -1 ],
		        						[ 5, 10, 15, 30,
		        								"All" ] ],
		        				pageLength : 10,// 每页显示数量
		        				processing : true,// loading等待框

		        				ajax : ctx
		        						+ "/rest/processAction/todoTask/" + 'buyOrder',// 加载待办列表数据

		        				"aoColumns" : [
		        					              { mData: 'taskId'},
		        									{
		        										mData : 'assign',
		        										mRender : function(
		        												data) {
		        											if (data == '') {
		        												return "待签收";
		        											} else {
		        												return "待办理";
		        											}
		        										}
		        									},
		        									{
		        										mData : 'userName'
		        									},
		        									{
		        										mData : 'title'
		        									},
		        									{
		        										mData : 'taskName',
		        										mRender : function(
		        												data,
		        												type,
		        												row,
		        												meta) {
		        											return "<a class='trace' onclick=\"graphTrace('"
		        													+ row.processInstanceId + "','" + ctx 
		        													+ "')\" id='diagram' href='javascript:;' pid='"
		        													+ row.id
		        													+ "' pdid='"
		        													+ row.processDefinitionId
		        													+ "' title='see'>"
		        													+ data
		        													+ "</a>";
		        										}
		        									},
		        									{
		        										mData : 'owner',
		        										mRender : function(
		        												data,
		        												type,
		        												row,
		        												meta) {
		        											if (data != ''
		        													&& data != row.assign) {
		        												return row.assign
		        														+ " (原执行人："
		        														+ data
		        														+ ")";
		        											} else {
		        												return row.assign;
		        											}
		        										}
		        									},
		        									{
		        										mData : 'createTime',
		        										mRender : function(
		        												data) {
		        											if (data != null) {
		        												return timeStamp2String(data);
		        											} else
		        												return '';
		        										}
		        									},
		        									{
		        										mData : 'suspended',
		        										mRender : function(
		        												data) {
		        											if (data) {
		        												return "已挂起";
		        											} else {
		        												return "正常";
		        											}
		        										}
		        									} ],
		        						'aoColumnDefs': [ {
		        	                    	'targets' : 0,
		        	                    	'searchable' : false,
		        	                    	'orderable' : false,
		        	                    	'className' : 'dt-body-center',
		        	                    	'render' : function(data,type, full, meta) {
		        								return "<label class='mt-checkbox mt-checkbox-single mt-checkbox-outline'>" +
		        								"<input type='checkbox' class='checkboxes' value="+ data +" />" +
		        								"<span></span></label>";
		        							
		        	                    	}
		        	                    } 
		        	                    ]

		        			})
		        			
		        			
		        			$("#dbTable").find(".group-checkable").change(function() {
					            var e = jQuery(this).attr("data-set"),
					            t = jQuery(this).is(":checked");
					            jQuery(e).each(function() {
					                t ? ($(this).prop("checked", !0), $(this).parents("tr").addClass("active")) : ($(this).prop("checked", !1), $(this).parents("tr").removeClass("active"))
					            })
					        }),
					        $("#dbTable").on("change", "tbody tr .checkboxes",
					        function() {
					            $(this).parents("tr").toggleClass("active")
					        })
	        
		        			return table;
		        	
		        	
		        }

		        function showYbTable(){
		        	var endTaskTable = $("#endTaskTable").DataTable(
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
		        				order : [ [ 1, "asc" ] ],// 默认排序列及排序方式
		        				bRetrieve : true,
		        				lengthMenu : [
		        						[ 5, 10, 15, 30, -1 ],
		        						[ 5, 10, 15, 30,
		        								"All" ] ],
		        				pageLength : 10,// 每页显示数量
		        				processing : true,// loading等待框

		        				ajax : ctx
		        						+ "/rest/processAction/endTask/"+'buyOrder',// 加载已办列表数据

		        				"aoColumns" : [
		        						{
		        							mData : 'businessType',
		        							mRender : function(
		        									data) {
		        								return "订单申请";
		        							}
		        						},
		        						{
		        							mData : 'userName'
		        						},
		        						{
		        							mData : 'title'
		        						},
		        						{
		        							mData : 'startTime',
		        							mRender : function(
		        									data,
		        									type,
		        									row,
		        									meta) {
		        								return timeStamp2String(data);
		        							}
		        						},
		        						{
		        							mData : 'claimTime',
		        							mRender : function(
		        									data,
		        									type,
		        									row,
		        									meta) {
		        								if(data != null){
		        		                			return timeStamp2String(data);
		        		                		}else{
		        		                			return "无需签收";
		        		                		}
		        							}
		        						},
		        						{
		        							mData : 'endTime',
		        							mRender : function(
		        									data) {
		        								if (data != null) {
		        									return timeStamp2String(data);
		        								} else
		        									return '';
		        							}
		        						},
		        						{
		        							mData : 'deleteReason'
		        						},
		        						{
		        							mData : 'version'
		        						},
		        						{
		        							mData : 'revoke',
		        							mRender : function(data,type,row,meta) {
		        								return "<a href='javascript:void(0);' onclick=\"revoke('"+row.taskId+"','"+row.processInstanceId+"','endTaskTable')\">撤销</a>";
		        							}
		        						}
		        						]

		        			})
		         return endTaskTable;
		        }

		        function handleTask(assign, taskId, processInstanceId){
		        	
		        	if(assign == ''){
		        		toastr.warning("此任务您还没有签收，请【签收】任务后再处理任务！！");
		        	}else{
		        		
		        	}
		        	
		        	
		        	
		        }
		      //********审批流程end****************//  
		        
    /**
	 * 加载供应商数据
	 */
	var initSuppliers = function(){
		var promise = orderService.initSuppliers();
        	promise.then(function(data){
        		$scope.suppliers = data.data;
        		setTimeout(function () {
        			$("#supplyComId").selectpicker({
                        showSubtext: true
                    });
        			$('#supplyComId').selectpicker('refresh');//刷新插件
        			
                }, 100);
        		
        	},function(data){
        		//调用承诺接口reject();
        	});
	}
	
	/**
	 * 加载仓库数据
	 */
	var initWarehouse = function(){
	var promise = orderService.initWarehouse();
	promise.then(function(data){
		$scope.warehouses = data.data;
	},function(data){
		//调用承诺接口reject();
	});
	}
	
	/***************日志表格 start************************/
	var logTable 
	$scope.viewOrderLog = function (serialNum){
		$("#operateLogInfo").modal("show");
		if(logTable){
			logTable.ajax.url(ctx+"/rest/order/findOrderLog?serialNum=" + serialNum).load()
		}else{
			showLogTable("/rest/order/findOrderLog?serialNum=" + serialNum);
		}
	}
	
	$scope.viewDeliverLog = function (serialNum){
		$("#operateLogInfo").modal("show");
		if(logTable){
			logTable.ajax.url(ctx+"/rest/order/findDeliverLog?serialNum=" + serialNum).load()
		}else{
			showLogTable("/rest/order/findDeliverLog?serialNum=" + serialNum);
		}
	}
	
	$scope.viewPayLog = function (serialNum){
		$("#operateLogInfo").modal("show");
		if(logTable){
			logTable.ajax.url(ctx+"/rest/order/findPayLog?serialNum=" + serialNum).load()
		}else{
			showLogTable("/rest/order/findPayLog?serialNum=" + serialNum);
		}
	}
	
	
	 function showLogTable(url){
		logTable = $("#select_operateLog")
     	.DataTable(
     			{
     				language: {
                        aria: {
                            sortAscending: ": activate to sort column ascending",
                            sortDescending: ": activate to sort column descending"
                        },
                        emptyTable: "空表",
                        info: "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
                        infoEmpty: "没有数据",
                        // infoFiltered: "(filtered1 from _MAX_ total
							// entries)",
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
     				order : [ [ 2, "asc" ] ],// 默认排序列及排序方式

     				bRetrieve : true,
     				lengthMenu : [
     						[ 5, 10, 15, 30, -1 ],
     						[ 5, 10, 15, 30,
     								"All" ] ],
     				pageLength : 10,// 每页显示数量
     				processing : true,// loading等待框

     				ajax : ctx+url,// 加载待办列表数据

     				"aoColumns" : [
     									{
     										mData : 'operationDesc'
     									},
     									{
     										mData : 'operator'
     									},
     									{
     										mData : 'operationTime',
		        							mRender : function(
		        									data) {
		        								if (data != null) {
		        									return timeStamp2String(data);
		        								} else
		        									return '';
		        							}
     									},
     									{
     										mData : 'remark'
     									}]
     			})
     }
	 
	 
	 /***************日志表格 end************************/
	 
	 
	 /** *************订单物料明细可检索化  start*************** */
	 $scope.pageIndex = 1; //记录当前页
	 $scope.pageSize = '10'; //每页的记录数
	 $scope.totalPage = '1'; //记录总页数
	 $scope.dispalyOrderMateriel = [];//页面显示结果
	 $scope.filterOrderMateriel = [];//查询筛选结果
	 
	 $scope.createFilterList = function(){
		 $scope.filterOrderMateriel = [];
		if($scope.orderMateriel.length>0&&$scope.queryStr&&!isNull($scope.queryStr)){
			for(var i = 0;i < $scope.orderMateriel.length;i++){// data.data为选择的标准物料
				if(($scope.orderMateriel)[i].materiel.materielNum.indexOf($scope.queryStr)>=0){
					$scope.filterOrderMateriel.push(angular.copy(($scope.orderMateriel)[i]));
				}else if(($scope.orderMateriel)[i].materiel.materielName.indexOf($scope.queryStr)>=0){
					$scope.filterOrderMateriel.push(angular.copy(($scope.orderMateriel)[i]));
				}else if(($scope.orderMateriel)[i].materiel.specifications.indexOf($scope.queryStr)>=0){
					$scope.filterOrderMateriel.push(angular.copy(($scope.orderMateriel)[i]));
				}
			}
		}else{
			$scope.filterOrderMateriel = angular.copy($scope.orderMateriel);
		}
		
	 };
	 
	 $scope.createDispalyList = function(){
		 $scope.dispalyOrderMateriel = $scope.filterOrderMateriel.slice(
				 ($scope.pageIndex-1)*$scope.pageSize,
				 $scope.pageIndex*$scope.pageSize);
		 
		 $scope.totalPage = Math.ceil($scope.filterOrderMateriel.length/$scope.pageSize);
	 };
	 
	 $scope.queryForPage = function(){
		 $scope.createFilterList();
		 $scope.pageIndex = 1; //设置为第一页
		 $scope.createDispalyList();
	 };
	 
	 $scope.link2ThisPage = function(index){
		 $scope.pageIndex = index;
		 $scope.createDispalyList();
	 }
	 
	 $scope.link2PreviousPage = function(){
		 $scope.pageIndex--;
		 $scope.createDispalyList();
	 }
	 
	 $scope.link2NextPage = function(){
		 $scope.pageIndex++;
		 $scope.createDispalyList();
	 }
	 
	/** *************订单物料明细可检索化  end*************** */
          
          
          
          
}]);


