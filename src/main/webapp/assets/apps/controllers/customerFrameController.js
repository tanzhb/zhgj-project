/* Setup general page controller */
angular.module('MetronicApp').controller('customerFrameController', ['$rootScope', '$scope', 'settings','orderService','$filter',
    '$state',"$stateParams",'$compile','$location','materielService','FileUploader', function($rootScope, $scope, settings,orderService,$filter,$state,$stateParams,$compile,$location,materielService,FileUploader) {
    $scope.$on('$viewContentLoaded', function() {   
    	// initialize core components
    	App.initAjax();
    	handle = new pageHandle();
    	// set default layout mode
    	$rootScope.settings.layout.pageContentWhite = true;
        $rootScope.settings.layout.pageBodySolid = false;
        $rootScope.settings.layout.pageSidebarClosed = false;
        if($state.current.name=="customerFrame"){
        	loadMainTable();// 加载框架协议列表(普通框架协议)
//        	loadMainFramTable();// 框架框架协议列表
//        	loadTakeDelieryTable();// 收货计划列表
        	//***************************************流程处理相关start
        	var dbtable;//待办table
			var endTaskTable;//已办table
			
			if($stateParams.tabHref == '1'){//首页待办列表传过来的参数
				$('#frameTab a[href="#daiban"]').tab('show');
				showDbTable();
			}else if($stateParams.tabHref == '2'){//首页已办列表传过来的参数
				$('#frameTab a[href="#yiban"]').tab('show');
				showYbTable();
			}else{//从菜单进入
				$('#frameTab a[href="#apply"]').tab('show');
			}
			
			
			

			// 请假申请
			$scope.toApply = function() {
				$('#frameTab a[href="#apply"]').tab('show');
			};
			// 待办流程
			$scope.toDaiban = function() {
				$('#frameTab a[href="#daiban"]').tab('show');

				// 构建datatables开始***************************************
				dbtable = showDbTable();								
				// 构建datatables结束***************************************

			};
			// 已办流程
			$scope.toYiban = function() {
				$('#frameTab a[href="#yiban"]').tab('show');
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
            		
            		$scope.cancelContract();
            		$scope.cancelClauseSettlement();
            		$scope.cancelClauseAdvance();
            		$scope.cancelClauseDelivery();
            		$scope.cancelClauseCheckAccept();
            		$scope.cancelClauseFramework();
       		    	$scope.cancelClauseAfterSales();
   	       		    $scope.cancelFile();
            		$scope.getSaleFrameInfo($stateParams.serialNum,$stateParams.taskId, $stateParams.comments,$stateParams.processInstanceId)
            	}else{
            		$scope.opration = '新增';
            		
            		$scope.saleFrame={};
            		$rootScope.setNumCode("CA",function(newCode){//
             			$scope.saleFrame.contractNum= newCode;//合同编号
             		});
            		$scope.saleFrame.contractType="销售框架";
            		$scope.clauseSettlement = {};
            		$scope.clauseSettlement.otherAmount = 0;
            		$scope.saleFrame.seller ="中航能科（上海）能源科技有限公司";
            		dateSelectSetting();//日期选择限制
            		// 加载数据
            		initCustomers();
                	initWarehouse();
                	//合同内容
                	$scope.saleFrame.contractContent = '111100';
                	$scope.initContractContent();
            	}
            	$scope.noShow = true;
            	/*
            	if($stateParams.view==1){// 框架协议切换为查看
            		$scope.saleFrameInput = true;
    		    	$scope.saleFrameShow = true;
       		    	$scope.opration = '查看';
    		    }
            	if($stateParams.view=='all'){// 框架协议全体切换为查看
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
    		    }*/
            	
//            	validateInit();// 加载表单验证控件
            	
            	validateContractInit();// 加载合同表单验证控件
            	
            	validateClauseAdvanceInit();// 加载垫资条款表单验证
            	validateClauseDeliveryInit();// 加载交付条款表单验证
            	validateClauseCheckAcceptInit();// 加载验收条款表单验证
            	validateClauseAfterSalesInit();// 加载售后条款表单验证
            	validateClauseSettlementInit();// 加载结算条款表单验证
            	validateCSDInit();// 加载结算条款明细表单验证
            	validateFileInit();//加载框架协议附件表单验证
            	validateClauseFrameworkInit();// 加载框架条款表单验证
            	
            	
//            	setTimeout($scope.autoSave, 300000);//5分钟框架协议自动保存一次
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
	        },
	        error : function(data) {
	        	toastr.error('连接服务器出错,请登录重试！');
	        }
	     });
	}
	
	//审批通过
	$scope.framePass = function() {
	    var mydata={"processInstanceId":$("#processInstanceId").val(),"frameId":$scope.saleFrame.id,"content":$("#content").val(),
				"completeFlag":true};
	    var _url = ctx + "rest/order/complateFrame/" + $("#taskId").val();
	    doOrder(_url, mydata, 'audit');
	};
	//审批不通过
	$scope.frameUnPass = function() {
		var mydata={"processInstanceId":$("#processInstanceId").val(),"frameId":$scope.saleFrame.id,"content":$("#content").val(),
				"completeFlag":false};
		var _url = ctx + "rest/order/complateFrame/" + $("#taskId").val();
		doFrame(_url, mydata, 'audit');
	};
	
	//重新申请
	$scope.replyFrame = function() {
	    var mydata={"processInstanceId":$("#processInstanceId").val(),
				"reApply":true,"frameId":$scope.saleFrame.id,"reason":$scope.saleFrame.remark,"frameType":'saleFrame'};
		var _url = ctx + "rest/order/modifyFrame/" + $("#taskId").val();
		doFrame(_url, mydata, 'modify');
	};
	//取消申请
	$scope.cancelApply = function() {
	     var mydata={"processInstanceId":$("#processInstanceId").val(),
				"reApply":false,"frameId":$scope.saleFrame.id,"reason":$scope.saleFrame.remark,"frameType":'saleFrame'};
		var _url = ctx + "rest/order/modifyFrame/" + $("#taskId").val();
		doFrame(_url, mydata, 'modify' );
	};
	$scope.changeFlag = true
/*    $scope.repeatDone = function(scope){
    	$scope.changeFlag = false;
    	var date1= scope._orderMateriel.deliveryDate;
    	var date2= scope._orderMateriel.lastDeliveryDate;
    	var date3= scope.saleFrame.orderDate;
    	$scope.datepickerInit();
    	if(scope._orderMateriel){
    		scope._orderMateriel.deliveryDate = date1;
    		scope._orderMateriel.lastDeliveryDate = date2;
    	}
    	$scope.changeFlag = true;
    	scope.saleFrame.orderDate = date3;
   };*/
   
   $scope.repeatMaterielList = function(scope){
	   searchMaterielList();//框架协议物料可检索化
  };

   
   $scope.renderDone = function(){};
   
   $scope.datepickerInit = function(scope){
	   $('.date-picker').datepicker({
			rtl: App.isRTL(),
			orientation: "bottom",
			autoclose: true,
			dateFormat:"yyyy-mm-dd",
			language: "zh-CN"
   	})
  };
   
    $scope.save  = function() {
    	if($('#form_contract').valid()){//

    		$rootScope.judgeIsExist("contract",$scope.saleFrame.orderNum, $scope.saleFrame.id,function(result){
    			var 	isExist = result;
    		if(isExist){
    			toastr.error('框架协议编号重复！');
    			return;
    		}else{
    			//如果平台修改了双方已确认的框架协议，需重新提交
    			if(!isNull($scope.saleFrame.id)&&$scope.saleFrame.status =='1'){
    				$scope.saleFrame.status = 0;
    			}
    			orderService.saveFrame($scope.saleFrame).then(
	   	       		     function(data){
	   	       		    	toastr.success('数据保存成功！');
	   	       		    	$scope.saleFrame = data.data;
			   	       		$scope.saleFrameInput = true;
		      			    $scope.saleFrameShow = true;
	   	       		     },
	   	       		     function(error){
	   	       		    	toastr.error('数据保存出错！');
	   	       		         $scope.error = error;
	   	       		     }
	   	       		 );
    		}
    		
    		});
    	}
    	
    }; 	
    
    $scope.autoSave  = function() {};
    
    $scope.cancel  = function() {// 取消编辑
    	if($scope.saleFrame.id==null || $scope.saleFrame.id=='') {// 如果是取消新增，返回列表页面
    		$state.go("saleFrame");
    		return;
		}
    	$scope.getSaleFrameInfo($scope.saleFrame.id,$stateParams.taskId, $stateParams.comments,$stateParams.processInstanceId);
    	$scope.cancelFrame();
    	
    };
    $scope.cancelFrame  = function() {// 取消编辑框架协议信息
    	$scope.saleFrameInput = true;
	    $scope.saleFrameShow = true;
    };
    
    $scope.edit  = function() {// 进入编辑
    	$scope.saleFrameInput = false;
	    $scope.saleFrameShow = false;
    };
    
    $scope.viewSaleFrame = function(serialNum){
    	$state.go("viewSaleFrame",{serialNum:serialNum});
    }
    $scope.goContract = function(serialNum){
    	$state.go("userContract",{});
    }
    $scope.viewGraphTrace = function(processInstanceId){
    	graphTrace(processInstanceId,ctx);
    }
    
    var table;
    var tableAjaxUrl = "rest/order/findFrameList?type=sale&selectFor=customerFrame";
    var loadMainTable = function() {
            a = 0;
            App.getViewPort().width < App.getResponsiveBreakpoint("md") ? $(".page-header").hasClass("page-header-fixed-mobile") && (a = $(".page-header").outerHeight(!0)) : $(".page-header").hasClass("navbar-fixed-top") ? a = $(".page-header").outerHeight(!0) : $("body").hasClass("page-header-fixed") && (a = 64);
            table = $("#sample_2")
			.DataTable({
                language: {
                    aria: {
                        sortAscending: ": 以升序排列此列",
                        sortDescending: ": 以降序排列此列"
                    },
                    emptyTable: "空表",
                    info: "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
                    infoEmpty: "没有数据",
                    // infoFiltered: "(filtered1 from _MAX_ total entries)",
                    lengthMenu: "每页显示 _MENU_ 条数据",
                    search: "查询:",processing:"加载中...",infoFiltered: "（从 _MAX_ 项数据中筛选）",
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
                order: [[11, "asc"],[1, "desc"]],// 默认排序列及排序方式
                searching: true,// 是否过滤检索
                ordering:  true,// 是否排序
                lengthMenu: [[5, 10, 15, 30, -1], [5, 10, 15, 30, "All"]],
                pageLength: 5,// 每页显示数量
                processing: true,// loading等待框
// serverSide: true,
                ajax: tableAjaxUrl,// 加载数据中
                textAlign: 'center',
                "aoColumns": [
                              { mData: 'id'},
                              { mData: 'contractNum' },
                              { mData: 'comName' },
                              { mData: 'contractType' },
                              { mData: 'contractNum' },
                              { mData: 'startDate' },
                              { mData: 'endDate' },
                              { mData: 'contractNum' },
                              { mData: 'creator' },
                              { mData: 'versionNO' },
                              { mData: 'status' },
                              /*{ mData: 'status' },*/
                              { bVisible: false }
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
								return clickhtm = '<a href="javascript:void(0);" ng-click="viewSaleFrame(\''+row.id+'\')">'+data+'</a>'
								
							},
							"createdCell": function (td, cellData, rowData, row, col) {
								 $compile(td)($scope);
						       }
						}, {
							'targets' : 10,
							'render' : function(data,
									type, row, meta) {
								var clickhtm = ''
								if(row.status==0){
									return clickhtm + '<span   >待申请</span>';
								}else if(row.status==1){
									if(row.processBase!=""&&row.processBase!=null){
	                        			if(row.processBase.status=="PENDING"||row.processBase.status=="WAITING_FOR_APPROVAL"){
											return clickhtm + '<span  style="color:#fcb95b">审核中</span>';
										}else if(row.processBase.status=="APPROVAL_SUCCESS"){
											return clickhtm + '<span   style="color:#fcb95b">待签合同</span>';
										}else if(row.processBase.status=="APPROVAL_FAILED"){
											return clickhtm + '<span   style="color:red">未通过</span>';
										}else{
											return clickhtm + '<span >待申请</span>';
										}
	                        		}else{
	                        			return clickhtm + '<span >未审批</span>';
	                        		}
									return clickhtm + '<span   style="color:#fcb95b">待审批</span>';
								}else if(row.status==2){
									return clickhtm + '<span   style="color:green">已签合同</span>';
								}else if(row.status==3){
									return clickhtm + '<span   style="color:#fcb95b">待签合同</span>';
								}else{
									if(row.processBase!=""&&row.processBase!=null){
	                        			if(row.processBase.status=="PENDING"||row.processBase.status=="WAITING_FOR_APPROVAL"){
											return clickhtm + '<span  style="color:#fcb95b">审核中</span>';
										}else if(row.processBase.status=="APPROVAL_SUCCESS"){
											
										}else if(row.processBase.status=="APPROVAL_FAILED"){
											return clickhtm + '<span   style="color:red">未通过</span>';
										}else{
											return clickhtm + '<span >未发布</span>';
										}
	                        		}else{
	                        			return clickhtm + '';
	                        		}
									
								}
							}
						},/*{
							'targets' : 11,
							'render' : function(data,
									type, row, meta) {
								var clickhtm = ''
								if(row.status==0){
									return clickhtm + '<a href="javascript:void(0);" ng-click="submitSaleFrameApply(\''+row.id+'\')">申请</a>'
								}else if(row.status==1){
									if(row.processBase!=""&&row.processBase!=null){
	                        			if(row.processBase.status=="PENDING"||row.processBase.status=="WAITING_FOR_APPROVAL"){
	                        				return clickhtm + '';
										}else if(row.processBase.status=="APPROVAL_SUCCESS"){
											return clickhtm + '<a href="javascript:void(0);" ng-click="signContract(\''+row.id+'\',\''+row.comName+'\')">签订</a>'
										}else if(row.processBase.status=="APPROVAL_FAILED"){
											return clickhtm + '';
										}else{
											return clickhtm + '<a href="javascript:void(0);" ng-click="submitSaleFrameApply(\''+row.id+'\')">申请</a>'
										}
	                        		}else{
	                        			return clickhtm + '';
	                        		}
									return clickhtm + '<a href="javascript:void(0);" ng-click="submitSaleFrameApply(\''+row.id+'\')">申请</a>'
								}else if(row.status==2){
									return clickhtm + '';
								}else if(row.status==3){
									return clickhtm + '<a href="javascript:void(0);" ng-click="signContract(\''+row.id+'\',\''+row.comName+'\')">签订</a>'
								}else{
									return clickhtm + '';
								}
							},
							"createdCell": function (td, cellData, rowData, row, col) {
								 $compile(td)($scope);
						       }
						},*/ {
							'targets' : 11,
							'render' : function(data,
									type, row, meta) {
								var renderRow = meta.settings.aoData[meta.row];
								return 1 ;
//								return returnMin(
//											returnMin(
//													diySortFlag(renderRow.anCells[1].textContent),diySortFlag(renderRow.anCells[3].textContent)
//													),
//											diySortFlag(renderRow.anCells[4].textContent)
//											)
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
        

        // 弹出确认删除模态框
        $scope.deleteSaleFrame = function() {
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
    			toastr.warning('未选择框架协议！');return;
    		}else{
    			$('#delSaleFrameModal').modal('show');// 弹出删除确认模态框
    		}
			
		};
		
		$scope.editSaleFrame  = function() {// 进入编辑页面
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
    			toastr.warning('请选择一个框架协议！');return;
    		}else if(ids=='more'){
    			toastr.warning('只能选择一个框架协议！');return;
    		}*/
    		if(table.rows('.active').data().length != 1){
    			showToastr('toast-top-center', 'warning', '请选择一个框架协议！')
    		}else{
    			var processBase = table.row('.active').data().processBase;
    			if(processBase != null){
    				showToastr('toast-top-center', 'warning', '该框架协议已发起流程审批，不能修改！')
    			}/*else if(table.row('.active').data().status==1){
    				showToastr('toast-top-center', 'warning', '该框架协议已确认，不能修改！')
    			}*/else $state.go('addSaleFrame',{serialNum:table.row('.active').data().id});
    		}
    		
        };
        
        $scope.copyFrame  = function() {
    		if(table.rows('.active').data().length != 1){
    			showToastr('toast-top-center', 'warning', '请选择一个框架协议！')
    		}else{
    			handle.blockUI();
    			orderService
				.copyFrame(table.row('.active').data().id)
				.then(
						function(data) {
							handle.unblockUI();
							toastr.success('框架协议复制成功！');
							 $state.go('saleFrame',{},{reload:true});
							 
						},
						function(errResponse) {
							handle.unblockUI();
							toastr.error('数据复制失败！');
							console
									.error('Error while deleting Users');
						}

				);
    		}
    		
        };
     // 弹出确认删除模态框
        $scope.deleteBuyFramFrame = function() {
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
    			toastr.warning('未选择框架协议！');return;
    		}else{
    			$('#delSaleFrameModal').modal('show');// 弹出删除确认模态框
    		}
			
		};
		
		$scope.editBuyFramFrame  = function() {// 进入编辑页面
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
    			toastr.warning('请选择一个框架协议！');return;
    		}else if(ids=='more'){
    			toastr.warning('只能选择一个框架协议！');return;
    		}
    		
    		$state.go("addSaleFrame",{serialNum:ids});
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
					.delFrame(ids)
					.then(
							function(data) {
								$('#delSaleFrameModal').modal(
										'hide');// 删除成功后关闭模态框
								$(".modal-backdrop").remove();
								/* table.ajax.reload(); // 重新加载datatables数据 */
								toastr.success('数据删除成功！');
								 $state.go('saleFrame',{},{reload:true});
								 
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
	            	orderNum:{required:"采购框架协议号不能为空！"},
	            	orderType:{required:"采购类型不能为空！"},
	            	buyComId:{required:"采购商不能为空！"},
	            	serviceModel:{required:"服务模式不能为空！"},
	            	settlementClause:{required:"结算条款不能为空！"},
	            	deliveryMode:{required:"提货方式不能为空！"},
	            	rate:{required:"税率不能为空！"},
	            	currency:{required:"币种不能为空！"},
	            	maker:{required:"制单人不能为空！"},
	            	seller:{required:"采购商不能为空！"},
	            	orderDate:{required:"下单日期不能为空！"}
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
            		currency: {required: !0,maxlength: 20},
            		orderDate: {required: !0}
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
		 * 获取框架协议信息
		 */	
        $scope.getSaleFrameInfo  = function(serialNum,taskId,comments,processInstanceId) {
        	orderService.getFrameInfo(serialNum).then(
          		     function(data){//加载页面对象
          		    	$scope.saleFrame=data.contract;
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
          		    	if(isNull($scope.clauseSettlement.otherAmount)){
          		    		$scope.clauseSettlement.otherAmount = 0;
          		    	}
          		    	
          		    	if($scope.saleFrame.status==1){//已提交的不能做提交
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
          		    	
          		    	if(!isNull($scope.saleFrame)){
          		    		var myJsDate=$filter('date')($scope.saleFrame.startDate,'yyyy-MM-dd');
        					$scope.saleFrame.startDate=myJsDate;
        					
        					var myJsDate1=$filter('date')($scope.saleFrame.endDate,'yyyy-MM-dd');
        					$scope.saleFrame.endDate=myJsDate1;
        					
        					var myJsDate2=$filter('date')($scope.saleFrame.signDate,'yyyy-MM-dd');
        					$scope.saleFrame.signDate=myJsDate2;
        					
        					dateSelectSetting();//日期选择限制
             		    	if(!isNull($scope.saleFrame.signDate)){
             		    		$("#startDate").datepicker('setStartDate',$scope.saleFrame.signDate);
         			        	$("#endDate").datepicker('setStartDate',$scope.saleFrame.signDate);
             		    	}
             		        
             		    	if(!isNull($scope.saleFrame.startDate)){
             			        	$("#signDate").datepicker('setEndDate',$scope.saleFrame.startDate);
             			        	$("#endDate").datepicker('setStartDate',$scope.saleFrame.startDate);
             			    }  
             		    	if(!isNull($scope.saleFrame.signDate)){
             			        	$("#signDate").datepicker('setEndDate',$scope.saleFrame.signDate);
             			        	$("#startDate").datepicker('setEndDate',$scope.saleFrame.signDate);
             				} 
/*        					if($scope.saleFrame.contractType=="框架合同"){
        						$scope.showClauseFramework();
              	        	}*/
          		    	}else{
          		    		$scope.saleFrame = {};
          		    	}
          		    	
//          		    	$scope.copyMateriels = angular.copy($scope.orderMateriel);
          		    	
          		    	// 加载数据
          		    	initCustomers();
                    	initWarehouse();
                    	
          		    	$("#serialNum").val(serialNum);//赋值给隐藏input，通过和不通过时调用
    					$("#taskId").val(taskId);//赋值给隐藏input，通过和不通过时调用
    					$("#processInstanceId").val(processInstanceId);//赋值给隐藏input，通过和不通过时调用
    					
    					if(comments == ""||comments == null){
    						$("#comment_audit").html( "<tr><td colspan='4' align='center'>无内容</td></tr>");
    					}else $("#comment_audit").html(comments);
    					
    					//初始化合同内容
                    	if(isNull($scope.saleFrame.contractContent)){
                    		$scope.saleFrame.contractContent = '111100';
                    	}
                    	$scope.initContractContent();
          		     },
          		     function(error){
          		         $scope.error = error;
          		     }
          		 );
        	
        }; 
        
        
        /** *************框架协议物料操作 start*************** */
        
        var selectMateriel = function() {};

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
            
   			
   			
        $scope.addOrderMateriel = function (type,index){}
    	
        $scope.copyMateriels = {};
    	$scope.confirmSelect = function(){}
    	

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
    	 
	       
	       $scope.saveAllOrderMateriel  = function() {//保存所有物料
  	   	    	if($scope.saleFrame.serialNum==null||$scope.saleFrame.serialNum=='') {// 框架协议信息为空的处理
  	   	    		toastr.error('请先保存框架协议信息！');return
  	    		}
  	   	   for(var i=0;i<$scope.orderMateriel.length;i++){
  	    		if(isNull($scope.orderMateriel[i].orderRateUnit)||isNull($scope.orderMateriel[i].orderUnitPrice)){
  	    		toastr.warning('含税价格或不含税价格必填！');return
  	    		}
  	    		}
  	   	    	if($('#form_sample_5').valid()){
  	   	    	orderService.saveAllOrderMateriel($scope.orderMateriel).then(
  	   	       		     function(data){
  	   	       		    	toastr.success('数据保存成功！');
  	   	       		    	$scope.cancelAllOrderMateriel();
  	   	       		    		//保存结算条款
			  	   	       		if(isNull($scope.clauseSettlement)){// 结算条款为空的处理
				  	   	    		return
				  	   			}
			  	   	       		//更新框架协议金额数据
	  	   		        		$scope.updateOrderAmount();	
				  	   	    	if($('#form_clauseSettlement').valid()){
				  	   	    		$scope.clauseSettlement.contractSerial = $scope.saleFrame.id;
				  	   	    		$scope.clauseSettlementDetail = $scope.clauseSettlement.CSD;
				  	   	    		$scope.clauseSettlement.materielAmount = $scope.totalAmount();
				  	   	  	        $scope.clauseSettlement.rateAmount = $scope.totalRateAndCustomsAmount();
				  	   	  	        $scope.clauseSettlement.rateAndAmount = $scope.totalRateAndAmount();
				  	   	  	        $scope.clauseSettlement.orderAmount = $scope.totalOrderAmount().toFixed(2);
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
				  	   	          		        		    	$scope.cancelClauseSettlement();
				  	   	          		        		    	$scope.clauseSettlement.CSD = data.data;
				  	   	          		        		     },
				  	   	          		        		     function(error){
				  	   	          		        		    	toastr.error('数据保存出错！');
				  	   	          		        		         $scope.error = error;
				  	   	          		        		     }
				  	   	          		        		 );
				  	   	       		    		}else{
				  	   	          		    		$scope.cancelClauseSettlement()
				  	   	       		    		}
				  	   	       		    	}else{
				  	   	      		    		$scope.clauseSettlement = {}
				  	   	      		    		$scope.cancelClauseSettlement()
				  	   	      		    	}
				  	   	       		     },
				  	   	       		     function(error){
				  	   	       		    	toastr.error('数据保存出错！');
				  	   	       		         $scope.error = error;
				  	   	       		     }
				  	   	       		 );
				  	   	    	}
  	   	       		     },
  	   	       		     function(error){
  	   	       		    	toastr.error('数据保存出错！');
  	   	       		         $scope.error = error;
  	   	       		     }
  	   	       		 );
  	   	    	}
  	   	    	
  	   	    };
    	 /**
			 * 保存采购框架协议物料信息
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
						
				if($scope.saleFrame.id==null||$scope.saleFrame.id=='') {// 框架协议信息为空的处理
  	   	    		toastr.error('请先保存框架协议信息！');return
  	    		}
				delete orderMateriel.materiel;
				delete orderMateriel.supplyMateriel;
				delete orderMateriel.supply;
				if(isNull(orderMateriel.orderRateUnit)||isNull(orderMateriel.orderUnitPrice)){
 	   	    		toastr.warning('含税价格或不含税价格必填！');return
 	   	    		}
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
	        		if(!isNull(materiel.serialNum)&&materiel.serialNum == $scope.copyMateriels[i].serialNum){ // 如果是以保存的物料，回滚
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
	        
	        /**
			 * 撤销所有物料编辑
			 */
	        $scope.cancelAllOrderMateriel=function () {
	        	$scope.orderMaterielInput = true;
	        	$scope.orderMaterielShow = true;
	        	for(var i=0;i<$scope.orderMateriel.length;i++){
	        		$scope["orderMaterielInput"+i] = true;
					$scope["orderMaterielShow"+i] = true;
	        	}
	        }; 
	        
	        /**
			 * 打开所有物料编辑
			 */
	        $scope.editAllOrderMateriel=function () {
	        	$scope.orderMaterielInput = false;
	        	$scope.orderMaterielShow = false;
	        	for(var i=0;i<$scope.orderMateriel.length;i++){
	        		$scope["orderMaterielInput"+i] = false;
					$scope["orderMaterielShow"+i] = false;
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
				if(!isNull($scope.orderMateriel[index].lastDeliveryDate)&&$scope.orderMateriel[index].deliveryDate>$scope.orderMateriel[index].lastDeliveryDate){
		    		toastr.warning('交付日期不能大于最晚交付日期  ！');
		    		$scope.orderMateriel[index].deliveryDate=null;
		    		return;
		    	}
				if(index==0&&$scope.changeFlag){
					for(var i=1;i<$scope.orderMateriel.length;i++){
						 if($scope["orderMaterielInput"+i] != true/*&&isNull($scope.orderMateriel[i].deliveryAddress)*/){
							 $scope.orderMateriel[i].deliveryDate = orderMateriel.deliveryDate;
						 }
					 }
				}
			}
			
			$scope.setAllLastDeliveryDate = function(orderMateriel,index){
				if(!isNull($scope.orderMateriel[index].deliveryDate)&&$scope.orderMateriel[index].deliveryDate>$scope.orderMateriel[index].lastDeliveryDate){
		    		toastr.warning('最晚交付日期不能小于交付日期  ！');
		    		$scope.orderMateriel[index].lastDeliveryDate=null;
		    		return;
		    	}
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
			 * 编辑采购框架协议物料
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
	    	//修改代发货
			$scope.takeDeliveryEdit = function() {		
				if(TakeDelieryTable.rows('.active').data().length != 1){
					showToastr('toast-top-center', 'warning', '请选择一条数据进行修改！')
				}else{
					
					if(TakeDelieryTable.row('.active').data().status == '0'){
						//$state.go("takeDeliveryView",{serialNum:serialNum,oprateType:'forSaleFrame'});
						$state.go('takeDeliveryAdd',{serialNum:TakeDelieryTable.row('.active').data().takeDelivery.serialNum,oprateType:"forSaleFrame",type:'edit'});
					}else showToastr('toast-top-center', 'warning', '已确认发货不能修改');
				} 
			};
			   /**
	         * 批量删除收货计划
	         */
	        $scope.takeDeliveryDelete = function () {
	        	var id_count = $('#takeDeliveryTable input[name="serialNum"]:checked').length;
				if(id_count!=1){
					toastr.warning("只能选择一条记录");
					return;
				}
				if(TakeDelieryTable.row('.active').data().status == '0'){
	        	handle.confirm("确定删除吗？",function(){
	        		var ids = '';
					// Iterate over all checkboxes in the table
	        		$('#takeDeliveryTable input[name="serialNum"]').each(
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
	        		var promise = takeDeliveryService.deleteTakeDelivery(ids);
	        		promise.then(function(data){
	        			toastr.success("删除成功");
	        			handle.unblockUI();
	        			//createTable(5,1,true,$scope.params);
	        			TakeDelieryTable.ajax.reload(); // 重新加载datatables数据
	        			/*$state.go('company',{},{reload:true}); */
	        		},function(data){
	        			//调用承诺接口reject();
	        		});
	        		
	        	});
				}else showToastr('toast-top-center', 'warning', '已确认发货不能删除');
	        };
	        
	    	//确认代发货
			$scope.jumpToConfirm = function() {		
				if(TakeDelieryTable.rows('.active').data().length != 1){
					showToastr('toast-top-center', 'warning', '请选择一条数据进行确认！')
				}else{
					
					if(TakeDelieryTable.row('.active').data().status == '0'){
						$state.go('takeDeliveryView',{serialNum:TakeDelieryTable.row('.active').data().takeDelivery.serialNum,oprateType:"forSaleFrame"});
						//$state.go('takeDeliveryView',{serialNum:TakeDelieryTable.row('.active').data().serialNum,oprateType:"forSaleFrame"});
					}else showToastr('toast-top-center', 'warning', '已确认代发货')
				} 
			};
	        
	        /**
			 * 删除
			 */
	        $scope.deleteOrderMateriel=function (materiel) {
	        	handle.confirm("确定删除吗？",function(){
	        		if($scope.orderMateriel.length > 0){
	        			for(var i=0;i<$scope.orderMateriel.length;i++){
	        				for(var j=i;j+1<$scope.orderMateriel.length;j++){
        						$scope["orderMaterielInput"+j] = $scope["orderMaterielInput"+(j+1)];
	    	        			$scope["orderMaterielShow"+j] = $scope["orderMaterielShow"+(j+1)];
        					}
	        				if(materiel == $scope.orderMateriel[i]){
	        					$scope.orderMateriel.splice(i,1);
	        				}
	        			}
	        		}
	        		if(!isNull(materiel.serialNum)){
	        			handle.blockUI();
	        			var promise = orderService.deleteOrderMateriel(materiel.serialNum);
		        		promise.then(function(data){
		        			handle.unblockUI(); 
		        			if(data.data == "1"){
		        				toastr.success("删除成功");
		        			}else{
		        				toastr.error("删除失败！请联系管理员");
				            	console.log(data);
		        			}
		        			
		 	            },function(data){
		 	               // 调用承诺接口reject();
		 	            	toastr.error("删除失败！请联系管理员");
			            	console.log(data);
			            	handle.unblockUI(); 
		 	            });
	        		}
	        	});
			   
	        };
    	 /** *************框架协议物料操作 end*************** */
    	 
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
				if(!isNull($scope.saleFrame.electronicContract)){
					return true;    
				}else{
					return false;  
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
		            	buyComId:{required:"采购商不能为空！"},
		            	seller:{required:"供应商不能为空！"},
		            	contractNum:{required:"合同编号不能为空！",rangelength:jQuery.validator.format("合同编号位数必须在{0}到{1}字符之间！")},
		            	startDate:{required:"开始日期不能为空！"},
		            	endDate:{required:"结束日期不能为空！"},
		            	electronicContract:{noFileFlag:"电子合同不能为空！"}
		            },
	            	rules: {
	            		buyComId: {required: !0,maxlength: 20},
		            	seller:{required: !0,maxlength: 20},
	            		contractNum:{required:true,
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
    	 
//		        $scope.saveContract  = function() {// 保存合同信息
//		   	    	if($scope.saleFrame.id==null||$scope.saleFrame.id=='') {// 框架协议信息为空的处理
//		   	    		toastr.error('请先保存框架协议信息！');return
//		   			}
//		   	    	if($('#form_contract').valid()){
//		   	    		$scope.saleFrame.orderSerial = $scope.saleFrame.id;
//		   	    		$scope.saleFrame.comId = $scope.saleFrame.buyComId;
//		   	    		orderService.saveContract($scope.saleFrame).then(
//		   	       		     function(data){
//		   	       		    	toastr.success('数据保存成功！');
//		   	       		    	$scope.saleFrame = data.data;
//		   	       		    	if(!isNull(data.data)){
//		          		    		var myJsDate=$filter('date')(data.data.startDate,'yyyy-MM-dd');
//		        					$scope.saleFrame.startDate=myJsDate;
//		        					
//		        					var myJsDate1=$filter('date')(data.data.endDate,'yyyy-MM-dd');
//		        					$scope.saleFrame.endDate=myJsDate1;
//		        					
//		        					var myJsDate2=$filter('date')(data.data.signDate,'yyyy-MM-dd');
//		        					$scope.saleFrame.signDate=myJsDate2;
//		          		    	}
//		   	       		    	$scope.cancelContract();
//		   	       		     },
//		   	       		     function(error){
//		   	       		    	toastr.error('数据保存出错！');
//		   	       		         $scope.error = error;
//		   	       		     }
//		   	       		 );
//		   	    	}
//		   	    	
//		   	    }; 	
		   	    
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
			  		  			$scope.saleFrame[uploadSelectIndex] = response.filename;
			  		  		}else{//框架协议附件
			  		  			$scope.file[uploadSelectIndex].file = response.filename;
			  		  		}
			  		  }else{
			  			  toastr.error("上传失败！");
			  			if(uploadSelectIndex=='electronicContract'||uploadSelectIndex=='signContract'){//合同附件
		  		  			$scope.saleFrame[uploadSelectIndex] = response.filename;
		  		  		}else{//框架协议附件
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
		  		  			$scope.saleFrame[index] = "";
		  		  		}else{//框架协议附件
		  		  			$scope.file[index].file = "";
		  		  		}
			       }
/** ***************合同信息end******************** */
/** ***************结算条款start******************** */
 //获取货币符号
$scope.getCurrencySymbol = function(){
	if(isNull($scope.saleFrame)||isNull($scope.saleFrame.currency)){
		return '';
	}else{
		if($scope.saleFrame.currency=='人民币'){
			return '￥';
		}else if($scope.saleFrame.currency=='美元'){
			return '$';
		}else if($scope.saleFrame.currency=='欧元'){
			return '€';
		}else if($scope.saleFrame.currency=='英镑'){
			return '￡';
		}else{
			return '';
		}
	} 
}
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
    	if($scope.saleFrame.id==null||$scope.saleFrame.id=='') {// 合同信息为空的处理
    		toastr.error('请先保存框架协议信息！');return
		}
    	
    	if(!$scope.saleFrameInput){
    		toastr.error('框架协议处于编辑状态，请处理！');return
    	}
    	
    	if(!isNull($scope.orderMateriel)&&$scope.orderMateriel.length>0){
			for(var i = 0;i < $scope.orderMateriel.length;i++){// data.data为选择的供应物料
				if(!$scope["orderMaterielInput"+i]){
					toastr.error('有框架协议物料处于编辑状态，请处理！');return
				}
			}
		}
    	
    	if(isNull($scope.clauseSettlement)){// 结算条款为空的处理
    		$scope.clauseSettlement = {}
		}
    	if(/*$('#form_clauseSettlement').valid()*/1==1){
    		$scope.clauseSettlement.contractSerial = $scope.saleFrame.id;
    		$scope.clauseSettlementDetail = $scope.clauseSettlement.CSD;
    		$scope.clauseSettlement.materielAmount = $scope.totalAmount();
  	        $scope.clauseSettlement.rateAmount = $scope.totalRateAndCustomsAmount();
  	        $scope.clauseSettlement.rateAndAmount = $scope.totalRateAndAmount();
  	        $scope.clauseSettlement.orderAmount = $scope.totalOrderAmount().toFixed(2);
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
       		    	//更新框架协议金额数据
//	        		$scope.updateOrderAmount();	
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
   	    	if($scope.contract.id==null||$scope.saleFrame.id=='') {// 合同信息为空的处理
   	    		toastr.error('请先保存框架协议信息！');return
   			}
   	    	if(isNull($scope.clauseAdvance)){// 垫资条款为空的处理
 	    		toastr.error('请填写垫资条款后保存！');return
 			}
   	    	if($('#form_clauseAdvance').valid()){
   	    		$scope.clauseAdvance.contractSerial = $scope.saleFrame.id;
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
   	   	    	if($scope.saleFrame.id==null||$scope.saleFrame.id=='') {// 合同信息为空的处理
   	   	    		toastr.error('请先保存框架协议信息！');return
   	   			}
   	   	    	if(isNull($scope.clauseDelivery)){// 交付条款为空的处理
   	   	    		toastr.error('请填写交付条款后保存！');return
   	   			}
   	   	    	if($('#form_clauseDelivery').valid()){
   	   	    		$scope.clauseDelivery.contractSerial = $scope.saleFrame.id;
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
		   	   	      messages: {
			     
		   	   	    	  checkType:{required:"检验类型不能为空！"},
		   	   	    	  checkParty:{required:"检验方不能为空！"}
			            },
		          	rules: {
		          			checkType:{required:true},
		          			checkParty:{required:true}
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
   	   	 
   	   	        $scope.saveClauseCheckAccept  = function() {// 保存验收条款
   	   	   	    	if($scope.saleFrame.id==null||$scope.saleFrame.id=='') {// 合同信息为空的处理
   	   	   	    		toastr.error('请先保存框架协议信息！');return
   	   	   			}
		   	   	   	if(isNull($scope.clauseCheckAccept)){// 验收条款为空的处理
		   	    		toastr.error('请填写验收条款后保存！');return
		   			}
   	   	   	    	if($('#form_clauseCheckAccept').valid()){
   	   	   	    		$scope.clauseCheckAccept.contractSerial = $scope.saleFrame.id;
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
   	   	    	if($scope.saleFrame.id==null||$scope.saleFrame.id=='') {// 合同信息为空的处理
   	   	    		toastr.error('请先保存框架协议信息！');return
   	   			}
   	   	    	if(isNull($scope.clauseAfterSales)){// 售后条款为空的处理
	 	    		toastr.error('请填写售后条款后保存！');return
	 			}
   	   	    	if($('#form_clauseAfterSales').valid()){
   	   	    		$scope.clauseAfterSales.contractSerial = $scope.saleFrame.id;
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
   	   	    	if($scope.saleFrame.id==null||$scope.saleFrame.id=='') {// 框架协议信息为空的处理
   	   	    		toastr.error('请先保存框架协议信息！');return
   	    		}
   	   	    	if($('#form_sample_4').valid()){
   	   	    	orderService.saveFrameFile($scope.file).then(
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
		   	   	  if($scope.saleFrame.id==null||$scope.saleFrame.id=='') {// 框架协议信息为空的处理
		 	    		toastr.error('请先保存框架协议信息！');return
		 			}else{
   	   		    	   if($scope.file){}else{$scope.file =[{}]}
   	   		    	   $scope.file[_fileIndex] = {};
   	   		    	   $scope.file[_fileIndex].contractSerial = $scope.saleFrame.id;
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
	    	if($scope.saleFrame.id==null||$scope.saleFrame.id=='') {// 合同信息为空的处理
	   	    		toastr.error('请先保存框架协议信息！');return
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
   	    		toastr.error('请先保存框架协议信息！');return
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
	      //********框架协议提交start****************//
	        $scope.cancelPage  = function() {// 取消编辑
	        	$state.go("saleFrame");
	        };
	        /*$scope.submitPage  = function() {
	        	$scope.submitFrame = {}
	        	$scope.submitFrame.id = $scope.saleFrame.id;
	        	$scope.submitFrame.remark = $scope.saleFrame.remark;
	        	$scope.submitFrame.status = 1;
	        	$scope.saleFrame.status = 1;
	        	orderService.save($scope.submitFrame).then(
	          		     function(data){
	          		    	$scope.saleFrame.orderSerial = data.serialNum;
	          		    	if(isNull($scope.saleFrame.contractNum)){
	           		    		$scope.saleFrame.contractNum = $scope.saleFrame.orderNum;
	           		    	}
	    	   	    		$scope.saleFrame.comId = $scope.saleFrame.supplyComId;
	    	   	    		orderService.saveContract($scope.saleFrame).then(
	    	   	       		     function(data){
	    	   	       		    	toastr.success('数据保存成功！');
	    	   	       		    	$scope.saleFrame = data.data;
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
	        };*/
	        
	        $scope.cancelOrderStatus  = function() {//隐藏编辑备注及提交
	        	$scope.orderStatusShow = true;
	        	$scope.orderStatusInput = true;
		    };
		    //********框架协议提交end****************//
		    
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
		       
		       
		       $scope.exportSaleFrame = function(){
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
		     //********框架协议物料合计，结算条款start****************//
		       $scope.totalCount  = function() {//框架协议物料数量
			       	if($scope.orderMateriel){
			       		return $scope.orderMateriel.length;
			       	}else{
			       		return 0;
			       	}
		       };
		       
		       $scope.totalMaterielCount  = function(scope) {//框架协议物料总数量
		    	   if($scope.orderMateriel){
		    		    var total = 0 ; 
			       		for(var i=0;i<$scope.orderMateriel.length;i++){
			       			if(!isNull($scope.orderMateriel[i].amount)){
			       				total = total + Number($scope.orderMateriel[i].amount);
			       			}
			       			
			       		}
			       		return total
			       	}else{
			       		return 0;
			       	}
		       };
		       
		       $scope.totalOrderAmount  = function(scope) {//框架协议金额（外贸：商品金额+其他金额，内贸：价税合计（商品金额+税额含关税）+ 其他金额）
		    	   if(isNull($scope.clauseSettlement)||isNull($scope.clauseSettlement.otherAmount)){
		    		   if(!isNull($scope.saleFrame)&&$scope.saleFrame.tradeType =='外贸'){
		    			   return Number($scope.totalAmount());
		    		   }else{
		    			   return Number($scope.totalAmount()) + Number($scope.totalRateAndCustomsAmount());
		    		   }
			       		
			       	}else{
			       	   if(!isNull($scope.saleFrame)&&$scope.saleFrame.tradeType =='外贸'){
		    			   return Number($scope.totalAmount()) + Number($scope.clauseSettlement.otherAmount)
		    		   }else{
		    			   return Number($scope.totalAmount()) + Number($scope.totalRateAndCustomsAmount()) + Number($scope.clauseSettlement.otherAmount);
		    		   }
			       	}
		       };
		       
		       $scope.arithmeticAllDeliveryAmount  = function() {//计算所有节点支付金额
		    	 //计算各节点金额
		    	   if(!isNull($scope.clauseSettlement)&&!isNull($scope.clauseSettlement.CSD)){
		    		   	for(var i=0;i<$scope.clauseSettlement.CSD.length;i++){
			    		   $scope.arithmeticDeliveryAmount($scope.clauseSettlement.CSD[i])
			       		}
		    	   }
		      };
		       $scope.arithmeticDeliveryAmount  = function(CSD) {//计算支付金额
		          	if($scope._totalRate()>100){
		          		CSD.deliveryRate = CSD.deliveryRate - $scope._totalRate() + 100
		          	}
		          	if(CSD.deliveryRate){
		          		CSD.deliveryAmount =  ($scope.totalOrderAmount()*CSD.deliveryRate/100).toFixed(2);
		          	}
/*		       	scope._CSD.billingAmount = (Number($scope._totaldeliveryAmount()) - Number($scope._totalbillingAmount()) + Number(scope._CSD.billingAmount)).toFixed(2);
		      		scope._CSD.unbilledAmount = 0 ;*/
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
		       $scope.totalRateAmount  = function(scope) {//框架协议税额
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
		       $scope.totalCustomsRateAmount  = function(scope) {//框架协议关税
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
		       
		       $scope.totalRateAndCustomsAmount  = function(scope) {//框架协议税额(含关税)
		    	   if(!isNull($scope.saleFrame)&&$scope.saleFrame.tradeType =='外贸'){//税额+关税
	    		    	return Number($scope.totalRateAmount()) + Number($scope.totalCustomsRateAmount());
			    	}else{
			    		return $scope.totalRateAmount()
			    	}
		       };
		       
		       $scope.totalRateAndAmount  = function(scope) {//求和框架协议价税合计
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
			       	if(scope.orderUnitPrice&&$scope.saleFrame.rate){
			       		return (scope.orderUnitPrice*($scope.saleFrame.rate/100+1)).toFixed(4);
			       	}else{
			       		return 0;
			       	}
		       };
		       
		       $scope.arithmeticAmount  = function(scope) {//计算金额
			       	if(scope.orderUnitPrice&&scope.amount){
			       		return (scope.orderUnitPrice*scope.amount).toFixed(9);
			       	}else{
			       		return 0;
			       	}
		       };
		       $scope.arithmeticRateAmount  = function(scope) {//计算税额
			       	if($scope.saleFrame.rate){
			       		return ($scope.arithmeticAmount(scope)*$scope.saleFrame.rate/100).toFixed(9);
			       	}else{
			       		return 0;
			       	}
		       };
		       
		       $scope.arithmeticCustomsRateAmount  = function(scope) {//计算关税
			       	if(scope.customsRate){
			       		return ($scope.arithmeticAmount(scope)*scope.customsRate/100).toFixed(9);
			       	}else{
			       		return 0;
			       	}
		       };
		       
		       $scope.arithmeticRateAndAmount  = function(scope) {//计算价税合计
		    	   if(!isNull($scope.saleFrame)&&$scope.saleFrame.tradeType =='外贸'){//税额+关税
		    		   return Number($scope.arithmeticAmount(scope))+Number($scope.arithmeticRateAmount(scope))+Number($scope.arithmeticCustomsRateAmount(scope));
			    	}else{
			    		return Number($scope.arithmeticAmount(scope))+Number($scope.arithmeticRateAmount(scope));
			    	}
		       };
		       
		       
		       $scope._arithmeticRateUnit  = function(_orderMateriel) {//计算含税采购单价
			       	if(_orderMateriel.orderUnitPrice&&$scope.saleFrame.rate){
			       		_orderMateriel.orderRateUnit  =  (_orderMateriel.orderUnitPrice*($scope.saleFrame.rate/100+1)).toFixed(4);
			       	}else{
			       		_orderMateriel.orderRateUnit  =   0;
			       	}
		       };
		       
		       $scope._arithmeticUnitPrice  = function(_orderMateriel) {//计算不含税采购单价
			       	if(_orderMateriel.orderRateUnit&&$scope.saleFrame.rate){
			       		_orderMateriel.orderUnitPrice  =  (_orderMateriel.orderRateUnit/($scope.saleFrame.rate/100+1)).toFixed(9);
			       	}else{
			       		_orderMateriel.orderUnitPrice  =   0;
			       	}
		       };
		       
		       $scope._arithmeticAmount  = function(scope) {//计算金额
			       	if(scope._orderMateriel.orderUnitPrice&&scope._orderMateriel.amount){
			       		return (scope._orderMateriel.orderUnitPrice*scope._orderMateriel.amount).toFixed(9);
			       	}else{
			       		return 0;
			       	}
		       };
		       $scope._arithmeticRateAmount  = function(scope) {//计算税额
			       	if($scope.saleFrame.rate){
			       		return ($scope._arithmeticAmount(scope)*$scope.saleFrame.rate/100).toFixed(9);
			       	}else{
			       		return 0;
			       	}
		       };
		       
		       $scope._arithmeticCustomsRateAmount  = function(scope) {//计算关税
			       	if(scope._orderMateriel.customsRate){
			       		return ($scope._arithmeticAmount(scope)*scope._orderMateriel.customsRate/100).toFixed(9);
			       	}else{
			       		return 0;
			       	}
		       };
		       
		       $scope._arithmeticRateAndAmount  = function(scope) {//计算价税合计（商品金额+税额）
		    	   if(!isNull($scope.saleFrame)&&$scope.saleFrame.tradeType =='外贸'){//税额+关税
		    		   return (Number($scope._arithmeticAmount(scope))+Number($scope._arithmeticRateAmount(scope))+Number($scope._arithmeticCustomsRateAmount(scope))).toFixed(9);
			    	}else{
			    		return (Number($scope._arithmeticAmount(scope))+Number($scope._arithmeticRateAmount(scope))).toFixed(9);
			    	}
			       	
		       };
		       $scope.format2Thousands = function(formatV){  
		    	   formatV = Number(formatV).toFixed(2);
                   var array=new Array();  
                   array=formatV.split(".");  
                   var re=/(-?\d+)(\d{3})/;  
                   while(re.test(array[0])){  
                       array[0]=array[0].replace(re,"$1,$2")  
                   }  
                   var returnV=array[0];  
                   for(var i=1;i<array.length;i++){  
                       returnV+="."+array[i];  
                   }  
                   return returnV;
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
			    	 
			    	//保证小数点后只有9位
			    	 obj[attr] = obj[attr].replace(/([0-9]+\.[0-9]{9})[0-9]*/,"$1");
		    	 }
		       
		       $scope.clearNoNum = function(obj,attr){
			    	 //把非数字的都替换掉
			    	 obj[attr] = obj[attr].replace(/[^\d]/g,"");
		    	 }

		       
		     //更新框架协议金额数据
		     $scope.updateOrderAmount = function(obj,attr){}
		     
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
		     //********框架协议物料合计，结算条款end****************//
		       
		     //********审批流程start****************//
		       $scope.submitSaleFrameApply  = function(serialNum) {// 进入申请审批页面
		    	   if(!isNull(serialNum)){//列表操作栏按钮进入审批申请
		    			$state.go('submitSaleFrameApply',{serialNum:serialNum});
		    		}else if(!isNull($scope.saleFrame)&&!isNull($scope.saleFrame.id)){//详情页面进入审批
		    			var processBase = $scope.saleFrame.processBase;
		    			if(processBase != null){
		    				showToastr('toast-top-center', 'warning', '该框架协议已发起流程审批，不能再次申请！')
		    			}else $state.go('submitSaleFrameApply',{serialNum:$scope.saleFrame.id});
		    		}else if(table.rows('.active').data().length != 1){//列表选择进入审批申请
		    			showToastr('toast-top-center', 'warning', '请选择一条任务进行流程申请！')
		    		}else{
		    			var processBase = table.row('.active').data().processBase;
		    			if(processBase != null){
		    				showToastr('toast-top-center', 'warning', '该框架协议已发起流程审批，不能再次申请！')
		    			}else $state.go('submitSaleFrameApply',{serialNum:table.row('.active').data().id});
		    		}     	
		        };
		        
		        
		        $scope.confirmSaleFrameApply  = function() {// 进入提交申请
		        	$scope.submitFrame = {}
		        	$scope.submitFrame.id = $scope.saleFrame.id;
		        	$scope.submitFrame.remark = $scope.remark;
//		        	$scope.submitFrame.orderNum = $scope.saleFrame.orderNum;
//		        	$scope.submitFrame.tradeType = $scope.saleFrame.tradeType;
		        	//启动流程
		        	orderService.startSaleFrameProcess($scope.submitFrame).then(
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
		    			if($scope.getContentStatus($scope.saleFrame.contractContent,i)==1){
		    				$("#tab_1_"+i+"Id").addClass("active");
		    				$scope["tab_1_"+i+"Hide"] = false
		    			}else{
		    				$("#tab_1_"+i+"Id").removeClass("active");
		    				$scope["tab_1_"+i+"Hide"] = true
		    				if($state.current.name=="viewSaleFrame"
	          		    		||$state.current.name=="submitSaleFrameApply"
	          		    			||$state.current.name=="approvalSaleFrameApply"){//查看不展示
		    					$scope["tab_1_"+i+"label"] = true
	          		    	}
		    			}
		    		}
		    	}
		    	
		    	$scope.changeContentStatus = function(index){
		    		if($scope.getContentStatus($scope.saleFrame.contractContent,index)==1){
		    			$scope.saleFrame.contractContent = $scope.changeStr($scope.saleFrame.contractContent,index,0);
		    			/*$("#tab_1_"+index+"Id").removeClass("active");*/
	    				$scope["tab_1_"+index+"Hide"] = true
		    		}else{
		    			$scope.saleFrame.contractContent = $scope.changeStr($scope.saleFrame.contractContent,index,1);
		    			/*$("#tab_1_"+index+"Id").addClass("active");*/
	    				$scope["tab_1_"+index+"Hide"] = false
		    		}
		    	}
		    	
		    	//********合同内容操作end ****************//   
		      //********审批流程列表****************//
		        function showDbTable(){
		        	
		        	var dbtable = $("#dbTable")
		        	.DataTable(
		        			{
		        				language : {
		        					aria : {
		        						sortAscending : ": 以升序排列此列",
		        						sortDescending : ": 以降序排列此列"
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
		        								dbtable.$('input[type="checkbox"]').each(function() {
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
		        									if(dbtable.row('.active').data().assign == ''){
		    											showToastr('toast-top-center', 'warning', '此任务您还没有签收，请【签收】任务后再处理任务！')
		    											return;
		    										}
		        									orderService
		        									.getFrameAuditInfos(ids)
													.then(
															function(result) {													
		        												var comments = ""//添加评论
			        												for (var i=0;i<result.commentList.length;i++){
			        													comments += "<tr><td>" + result.commentList[i].userName  + "</td><td>" 
			        													+ (result.commentList[i].position==null?'':result.commentList[i].position) + "</td><td>"  
			        													+ timeStamp2String(result.commentList[i].time) + "</td><td>" + result.commentList[i].content + "</td></tr>";														
			        												}
			        												if(result.actionType == 'audit'){//审批流程
			        													$state.go('approvalSaleFrameApply',{serialNum:result.contract.id, taskId:ids, comments:comments,processInstanceId:result.contract.processInstanceId});
			        												}else{
			        													$state.go('editSaleFrameApply',{serialNum:result.contract.id, taskId:ids, comments:comments,processInstanceId:result.contract.processInstanceId});
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
		        								dbtable.$('input[type="checkbox"]').each(function() {
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
		        						}/*,
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
		        						}*/ ],
		        				dom : "<'row' <'col-md-12'B>><'row'<'col-md-6 col-sm-12'l><'col-md-6 col-sm-12'f>r><'table-scrollable't><'row'<'col-md-5 col-sm-12'i><'col-md-7 col-sm-12'p>>",
		        				order : [ [ 6, "asc" ] ],// 默认排序列及排序方式

		        				bRetrieve : true,
		        				lengthMenu : [
		        						[ 5, 10, 15, 30, -1 ],
		        						[ 5, 10, 15, 30,
		        								"All" ] ],
		        				pageLength : 10,// 每页显示数量
		        				processing : true,// loading等待框

		        				ajax : ctx
		        						+ "/rest/processAction/todoTask/" + 'saleFrame',// 加载待办列表数据

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
	        
		        			return dbtable;
		        	
		        	
		        }

		        function showYbTable(){
		        	var endTaskTable = $("#endTaskTable").DataTable(
		        			{
		        				language : {
		        					aria : {
		        						sortAscending : ": 以升序排列此列",
		        						sortDescending : ": 以降序排列此列"
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
		        				order : [ [ 5, "desc" ] ],// 默认排序列及排序方式
		        				bRetrieve : true,
		        				lengthMenu : [
		        						[ 5, 10, 15, 30, -1 ],
		        						[ 5, 10, 15, 30,
		        								"All" ] ],
		        				pageLength : 10,// 每页显示数量
		        				processing : true,// loading等待框

		        				ajax : ctx
		        						+ "/rest/processAction/endTask/"+'saleFrame',// 加载已办列表数据

		        				"aoColumns" : [
		        						{
		        							mData : 'businessType',
		        							mRender : function(
		        									data) {
		        								return "框架协议申请";
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
		        							mData : 'currentPointUserName',
		        							mRender : function(
		        									data,
		        									type,
		        									row,
		        									meta) {
		        								if(data != null){
		        		                			return data;
		        		                		}else{
		        		                			return "";
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
		        							mData : 'deleteReason',
	        								mRender : function(
		        									data) {
		        								if (data != null) {
		        									return data
		        								} else
		        									return '';
		        							}
		        						},
		        						{
		        							mData : 'version',
	        								mRender : function(
		        									data) {
		        								if (data != null) {
		        									return data
		        								} else
		        									return '';
		        							}
		        						},
		        						{
		        							mData : 'revoke',
		        							mRender : function(data,type,row,meta) {
		        								if(isNull(row.version)){
		        									return ''
		        									/*return "<a href='javascript:void(0);' ng-click=\"userCancelOrderApply('"+row.processInstanceId+"')\">取消</a>";*/
		        								}else{
		        									return "<a href='javascript:void(0);' onclick=\"revoke('"+row.taskId+"','"+row.processInstanceId+"','endTaskTable')\">撤销</a>";
		        								}
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
				 * 加载客户数据
				 * */
		    	var initCustomers = function(){
					var promise = orderService.initCustomers();
			        	promise.then(function(data){
			        		$scope.customers = data.data;
			        		setTimeout(function () {
			        			$("#buyComId").selectpicker({
			                        showSubtext: true,
			                        size : 5
			                    });
			        			$('#buyComId').selectpicker('refresh');//刷新插件
			        			
			                }, 100);
			        		
			        	},function(data){
			        		//调用承诺接口reject();
			        	});
				}
	$scope.addCompany = function(){
		$state.go("companyAdd");
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
                            sortAscending: ": 以升序排列此列",
                            sortDescending: ": 以降序排列此列"
                        },
                        emptyTable: "空表",
                        info: "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
                        infoEmpty: "没有数据",
                        // infoFiltered: "(filtered1 from _MAX_ total
							// entries)",
                        lengthMenu: "每页显示 _MENU_ 条数据",
                        search: "查询:",processing:"加载中...",infoFiltered: "（从 _MAX_ 项数据中筛选）",
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
	 
	 
	 /** *************框架协议物料明细可检索化  start*************** */
	 $scope.pageIndex = 1; //记录当前页
	 $scope.pageSize = '10'; //每页的记录数
	 $scope.totalPage = '1'; //记录总页数
	 $scope.dispalyOrderMateriel = [];//页面显示结果
	 $scope.filterOrderMateriel = [];//查询筛选结果
	 
	 $scope.createFilterList = function(){
		 $scope.filterOrderMateriel = [];
		if($scope.orderMateriel.length>0&&$scope.queryStr&&!isNull($scope.queryStr)){
			for(var i = 0;i < $scope.orderMateriel.length;i++){// data.data为选择的标准物料
				if(!isNull(($scope.orderMateriel)[i].materiel.materielNum)&&($scope.orderMateriel)[i].materiel.materielNum.indexOf($scope.queryStr)>=0){
					$scope.filterOrderMateriel.push(angular.copy(($scope.orderMateriel)[i]));
				}else if(!isNull(($scope.orderMateriel)[i].materiel.materielName)&&($scope.orderMateriel)[i].materiel.materielName.indexOf($scope.queryStr)>=0){
					$scope.filterOrderMateriel.push(angular.copy(($scope.orderMateriel)[i]));
				}else if(!isNull(($scope.orderMateriel)[i].materiel.specifications)&&($scope.orderMateriel)[i].materiel.specifications.indexOf($scope.queryStr)>=0){
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
	 
	/** *************框架协议物料明细可检索化  end*************** */
          
	 /***选择收货列表初始化START***/
     var TakeDelieryTable;
     var loadTakeDelieryTable = function() {
              a = 0;
              App.getViewPort().width < App.getResponsiveBreakpoint("md") ? $(".page-header").hasClass("page-header-fixed-mobile") && (a = $(".page-header").outerHeight(!0)) : $(".page-header").hasClass("navbar-fixed-top") ? a = $(".page-header").outerHeight(!0) : $("body").hasClass("page-header-fixed") && (a = 64);
              TakeDelieryTable = $("#takeDeliveryTable").DataTable({
                  language: {
                      aria: {
                          sortAscending: ": 以升序排列此列",
                          sortDescending: ": 以降序排列此列"
                      },
                      emptyTable: "空表",
                      info: "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
                      infoEmpty: "没有数据",
                      //infoFiltered: "(filtered1 from _MAX_ total entries)",
                      lengthMenu: "每页显示 _MENU_ 条数据",
                      search: "查询:",processing:"加载中...",infoFiltered: "（从 _MAX_ 项数据中筛选）",
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
                  order: [[1, "desc"]],//默认排序列及排序方式
                  bRetrieve : true,
					'scrollX': false,
					  buttons: [
				                {
				                	 extend: "print",
					                 className: "btn dark btn-outline"
				                }
				            ],
                  searching: true,//是否过滤检索
                  ordering:  true,//是否排序
                  lengthMenu: [[5, 10, 15, 30, -1], [5, 10, 15, 30, "All"]],
                  pageLength: 10,//每页显示数量
                  processing: true,//loading等待框
                  bRetrieve : true,
//                  serverSide: true,
                 ajax: "rest/takeDelivery/takeDeliveryList",//加载数据中
                  /*ajax :{ "url":$rootScope.basePath
						+ "/rest/takeDelivery/takeDeliveryList",// 加载数据中user表数据    
						"contentType": "application/json",
					    "type": "POST",
					    "data": function ( d ) {
					      return JSON.stringify( d );
					    }},*/
                  "aoColumns": [
                                { mData: 'takeDelivery.serialNum' },
                              /*  { mData: 'takeDelivery.takeDeliverNum' },*/
                                { mData: 'deliverNum' },
                                { mData: 'orderNum' },
                                { mData: 'shipper' },
                                //{ mData: 'materielCount' },物料条目数
		                          { mData: 'materielTotalCount' },//物料总数
                                { mData: 'packageType' },
                                { mData: 'deliverAddress' },
                                { mData: 'deliverDate' },
                                { mData: 'deliveryTransport' },
                                { mData: 'status' },
                                { mData: 'status' }
                          ],
                 'aoColumnDefs' : [ {
  							'targets' : 0,
  							'searchable' : false,
  							'orderable' : false,
  							'className' : 'dt-body-center',
  							'render' : function(data,
  									type, row, meta) {
	  	  								/*return '<input  type="checkbox" id='+data+'   name="serialNum" value="'
											+ $('<div/>')
													.text(
															data)
													.html()
											+ '">';*/
	  	  							return '<label class="mt-checkbox mt-checkbox-single mt-checkbox-outline">'+
                                   '<input type="checkbox"  name="serialNum" class="checkboxes"  id="'+data+'" value="'+data+'" data-set="#takeDeliveryTable .checkboxes" />'+
                                   '<span></span>'+
                               '</label>';
	
  							},
  							"createdCell": function (td, cellData, rowData, row, col) {
  								 $compile(td)($scope);
  						       }
  						},{
  							'targets' : 1,
  							'render' : function(data,
  									type, row, meta) {
  										if(data==null){
  											data="未收货";
  										}
	  	  								return '<a href="javascript:void(0);" ng-click="takeDeliveryView(\''+row.takeDelivery.serialNum+'\')">'+data+'</a>';
	
  							},
  							"createdCell": function (td, cellData, rowData, row, col) {
  								 $compile(td)($scope);
  						       }
  						},{
  							'targets' : 2,
  							'render' : function(data,
  									type, row, meta) {
  									if(!isNull(data)){
  										return data;
  									}
	  								return row.docNum;
	
  							}
  						},{
  							'targets' :8,
  							'render' : function(data,
  									type, row, meta) {
  								if(!isNull(data)){
										return data.transportType;
									}
	  								return '';
	
  							}
  						},{
  							'targets' : 9,
  							'searchable' : false,
  							'orderable' : false,
  							'className' : 'dt-body-center',
  							'render' : function(data,
  									type, row, meta) {
  									if(data=="PENDING"||data=="WAITING_FOR_APPROVAL"){
  										return '<span  class="label label-sm label-warning ng-scope">审核中</span>';
  									}else if(data=="1"){
  										return '<span  class="label label-sm label-info ng-scope">待检验</span>';
  									}else if(data=="APPROVAL_FAILED"){
  										return '<span  class="label label-sm label-danger ng-scope">未通过</span>';
  									}else if(data=="2"){
  										return '<span  class="label label-sm label-warning ng-scope">已取消</span>';
  									}else if(data=="3"){
  										return '<span  class="label label-sm label-warning ng-scope">待入库</span>';
  									}else if(data=="4"){
  										return '<span  class="label label-sm label-success ng-scope">已完成</span>';
  									}else if(data=="0"){
  										return '<span  class="label label-sm label-success ng-scope">待发货</span>';
  									}else if(data=="5"){
  										return '<span  class="label label-sm label-success ng-scope">已报关</span>';
  									}else if(data=="6"){
  										return '<span  class="label label-sm label-success ng-scope">待报关</span>';
  									}else if(data=="7"){
  										return '<span  class="label label-sm label-success ng-scope">待清关</span>';
  									}else if(data=="8"){
  										return '<span  class="label label-sm label-success ng-scope">已清关</span>';
  									}
  							}
  						},{
  							'targets' :10,
  							'render' : function(data,
  									type, row, meta) {
	  								return '';
	
  							}
  						}]

              }).on('order.dt',
              function() {
                  console.log('排序');
              }).on('page.dt', 
              function () {
            	  console.log('翻页');
	          }).on('draw.dt',function() {
	        	//  checkedIdHandler();
	          });
              
              $("#takeDeliveryTable").find(".group-checkable").change(function() {
		            var e = jQuery(this).attr("data-set"),
		            t = jQuery(this).is(":checked");
		            jQuery(e).each(function() {
		                t ? ($(this).prop("checked", !0), $(this).parents("tr").addClass("active")) : ($(this).prop("checked", !1), $(this).parents("tr").removeClass("active"))
		            })
		        }),
		        $("#takeDeliveryTable").on("change", "tbody tr .checkboxes",
		        function() {
		            $(this).parents("tr").toggleClass("active")
		        });
          };
          /***收货列表初始化END***/  
	        /**
	        * 导出收货计划
	        */
	       $scope.exportTakeDelivery = function(){
		    	 window.location.href=$rootScope.basePath+"/rest/takeDelivery/exportTakeDelivery";
		   }
	       /**
	         * 查看收货详情
	         */
	        $scope.takeDeliveryView = function(serialNum){
	        	$state.go("takeDeliveryView",{serialNum:serialNum,oprateType:'forSaleFrame'});
	        }
	        
	        
	        $scope.pingTaiSubmitFrame  = function(serialNum) {// 平台提交给供应商
	        	
	        	$scope.submitFrame = {}
	        	if(!isNull(serialNum)){//列表操作栏按钮提交
	        		$scope.submitFrame.id = serialNum;
	        		$scope.submitFrame.status = 66;
	        	}else{//详情页面按钮提交
	        		$scope.submitFrame.id = $scope.saleFrame.id;
	        		$scope.submitFrame.status = 66;
		        	$scope.saleFrame.status = 66;
	        	}
	        	
	        	orderService.pingTaiSubmitFrame($scope.submitFrame).then(
	          		     function(data){
	          		    	if(!isNull(serialNum)){//列表操作栏按钮提交
	          		    		toastr.info('框架协议提交成功！');
	          		    		$state.go('saleFrame',{},{reload:true});
	        	        	}else{//详情页面按钮提交
	        	        		toastr.info('框架协议提交成功！');
	        	        	}
	          		    	
	          		     },
	          		     function(error){
	          		         $scope.error = error;
	          		         toastr.error('数据保存出错！');
	          		     }
	          		 );
	        };
	        
	        $scope.pingTaiConfirmedFrame = function(serialNum){
	        	$scope.submitFrame = {}
	        	if(!isNull(serialNum)){//列表操作栏按钮确认
	        		$scope.submitFrame.id = serialNum;
	        		$scope.submitFrame.status = 1;
	        	}else{//详情页面按钮确认
	        		$scope.submitFrame.id = $scope.saleFrame.id;
	        		$scope.submitFrame.status = 1;
		        	$scope.saleFrame.status = 1;
	        	}
	        	
	        	orderService.reciveFrame($scope.submitFrame).then(
	          		     function(data){
	          		    	if(!isNull(serialNum)){//列表操作栏按钮确认
	          		    		toastr.success('框架协议确认成功！！');
	          		    		$state.go('saleFrame',{},{reload:true});
	        	        	}else{//详情页面按钮确认
	        	        		toastr.success('框架协议确认成功！！');
	        	        	}
	          		     },
	          		     function(error){
	          		         $scope.error = error;
	          		         toastr.error('数据保存出错！');
	          		     }
	          		 );
	        }
	        
	        /** ************关联销售框架协议 start*************** */
	        $scope.selectOrder = function() {
				 $('#saleOrderInfo').modal('show');// 删除成功后关闭模态框
				 loadMainTable1();
			 };
	        
	        // 确认选择开始***************************************
	        var ids = '';
//			 $scope.confirmSelectOrder = function() {
//				 var ids = '';
//				 table1.$('input[type="radio"]').each(function() {
//						if ($.contains(document, this)) {											
//							if (this.checked) {
//								// 将选中数据id放入ids中
//								if (ids == '') {
//									ids = this.value;
//								} else
//									ids = ids + ','
//											+ this.value;
//							}
//						}
//					});
//
//				 if(ids!=''){
//					 $scope.saleFrame.orderSerial = ids;
//				 }
//				 
//				 $('#saleOrderInfo').modal('hide');// 删除成功后关闭模态框
//
//			 };
	        
	        var table1;
//	 	    var loadMainTable1 = function() {
//	 	            a = 0;
//	 	            App.getViewPort().width < App.getResponsiveBreakpoint("md") ? $(".page-header").hasClass("page-header-fixed-mobile") && (a = $(".page-header").outerHeight(!0)) : $(".page-header").hasClass("navbar-fixed-top") ? a = $(".page-header").outerHeight(!0) : $("body").hasClass("page-header-fixed") && (a = 64);
//	 	            if(!isNull(table1)) return;
//	 	            table1 = $("#sample_21")
//	 				.DataTable({
//	 	                language: {
//	 	                    aria: {
//	 	                        sortAscending: ": 以升序排列此列",
//	 	                        sortDescending: ": 以降序排列此列"
//	 	                    },
//	 	                    emptyTable: "空表",
//	 	                    info: "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
//	 	                    infoEmpty: "没有数据",
//	 	                    //infoFiltered: "(filtered1 from _MAX_ total entries)",
//	 	                    lengthMenu: "每页显示 _MENU_ 条数据",
//	 	                    search: "查询:",processing:"加载中...",infoFiltered: "（从 _MAX_ 项数据中筛选）",
//	 	                    zeroRecords: "抱歉， 没有找到！",
//	 	                    paginate: {
//	 	                        "sFirst": "首页",
//	 	                        "sPrevious": "前一页",
//	 	                        "sNext": "后一页",
//	 	                        "sLast": "尾页"
//	 	                     }
//	 	                },
//	 	/*                fixedHeader: {//固定表头、表底
//	 	                    header: !0,
//	 	                    footer: !0,
//	 	                    headerOffset: a
//	 	                },*/
//	 	                order: [[1, "desc"]],//默认排序列及排序方式
//	 	                searching: true,//是否过滤检索
//	 	                ordering:  true,//是否排序
//	 	                lengthMenu: [[5, 10, 15, 30, -1], [5, 10, 15, 30, "All"]],
//	 	                pageLength: 5,//每页显示数量
//	 	                processing: true,//loading等待框
////	 	                serverSide: true,
//	 	                ajax:"rest/order/findOrderList?type=sale&selectFor=platformOrder",//加载数据中
//	 	                "aoColumns": [
//	 	                              { mData: 'serialNum' },
//	                               { mData: 'orderNum' },
//	                               { mData: 'buyName' },
//	                               { mData: 'materielCount' },
//	                               { mData: 'orderAmount' },
//	                               /*{ mData: 'deliveryMode' },*/
//	                               { mData: 'orderType' },
//	                               /*{ mData: 'saleApplySerial' },*/
//	                               { mData: 'orderSerial' },
//	                               { mData: 'orderDate' }
//
//	 	                        ],
//	 	               'aoColumnDefs' : [ {
//	 								'targets' : 0,
//	 								'searchable' : false,
//	 								'orderable' : false,
//	 								'render' : function(data,
//	 										type, row, meta) {
//	 									return "<label class='mt-radio mt-radio-single mt-radio-outline'>" +
//										"<input type='radio' name='serialNum' class='checkboxes' value="+ row.orderNum +" />" +
//										"<span></span></label>";
//	 								},
//	 								"createdCell": function (td, cellData, rowData, row, col) {
//	 									 $compile(td)($scope);
//	 							       }
//	 							},{
//	 								'targets' : 2,
//	 								'searchable' : false,
//	 								'orderable' : false,
//	 								'render' : function(data,
//	 										type, full, meta) {
//	 									if(isNull(data)){
//	 										return '中航能科（上海）能源科技有限公司'
//	 									}else{
//	 										return data;
//	 									}
//	 								},
//	 								"createdCell": function (td, cellData, rowData, row, col) {
//	 									 $compile(td)($scope);
//	 							       }
//	 							} ]
//
//	 	            }).on('order.dt',
//	 	            function() {
//	 	                console.log('排序');
//	 	            })
//	 	        };
	 	   
	 	       /** *************关联销售框架协议  end*************** */ 
	 	       //从框架协议签订合同
	 	       $scope.signContract= function(ids,comId) {
	 	    	  $state.go('saleOrderSign',{id:ids,comId:comId,type:"buy"});
	 	       }

	 	       
}]);


