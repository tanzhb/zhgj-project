/* Setup general page controller */
angular.module('MetronicApp').controller('procurementPlanController', ['$rootScope', '$scope', 'settings','procurementPlanService','DeliveryService','$filter',
    '$state',"$stateParams",'$compile','$location','materielService','FileUploader', function($rootScope, $scope, settings,procurementPlanService,DeliveryService,$filter,$state,$stateParams,$compile,$location,materielService,FileUploader) {
    $scope.$on('$viewContentLoaded', function() {   
    	// initialize core components
    	App.initAjax();
    	handle = new pageHandle();
    	// set default layout mode
    	$rootScope.settings.layout.pageContentWhite = true;
        $rootScope.settings.layout.pageBodySolid = false;
        $rootScope.settings.layout.pageSidebarClosed = false;
        if($state.current.name=="procurementPlan"){
        	loadMainTable();// 加载订单列表(普通订单)
//        	loadMainFramTable();// 框架订单列表
//        	loadTakeDelieryTable();// 收货计划列表
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
				$('#auditProcurementPlanModal').modal("hide");
			};
			
			// 关闭更改申请窗口 
			$scope.closeModifyDialogue = function() {
				$('#modifyProcurementPlanModal').modal("hide");
			};
			
			//初始化审批表单
			function approvalFormInit( taskDefinitionKey, businessType, taskId ) {
				
			}
			//***************************************流程处理相关end
        	}else {
        		$scope.datepickerInit();
            	// 初始化日期控件
            	     	
            	$scope.opration = {};
            	$scope.serialNums = [];
            	
            	
            	if($stateParams.serialNum){//修改
            		$scope.opration = '修改';
            		
            		$scope.getProcurementPlanInfo($stateParams.serialNum,$stateParams.taskId, $stateParams.comments,$stateParams.processInstanceId)
            	}else {
            		if(isNull($stateParams.newSerialNum)){
            		$scope.opration = '新增';
            		$scope.procurementPlanMateriel=[];
            		$scope.procurementPlan={};
            		$scope.procurementPlan.procurementPlanNum = '';
            		$rootScope.setNumCode("PL",function(newCode){
            			$scope.procurementPlan.procurementPlanNum = newCode;
            		});
            		}else{
            			$scope.opration = '修改';
            			//新建的采购计划修改
            			$scope.getProcurementPlanInfoForNew($stateParams.serialNum,$stateParams.taskId, $stateParams.comments,$stateParams.processInstanceId);
            		}
            		loadZizhuSaleTable();//加载自主销售订单,在新增或者修改用户自建采购计划
            		// 加载数据
                	initSuppliers();
                	initWarehouse();
                	initPtWarehouseAddress();

            	}
            	$scope.noShow = true;
            	/*
            	if($stateParams.view==1){// 订单切换为查看
            		$scope.procurementPlanInput = true;
    		    	$scope.procurementPlanShow = true;
       		    	$scope.opration = '查看';
    		    }
            	if($stateParams.view=='all'){// 订单全体切换为查看
            		$scope.cancelProcurementPlan();
            		$scope.cancelProcurementPlanMateriel();
            		$scope.cancelContract();
            		$scope.cancelClauseSettlement();
            		$scope.cancelClauseAdvance();
            		$scope.cancelClauseDelivery();
            		$scope.cancelClauseCheckAccept();
            		$scope.cancelClauseFramework();
       		    	$scope.cancelClauseAfterSales();
   	       		    $scope.cancelFile();
            		
//            		$scope.cancelProcurementPlanStatus();
            		
            		$scope.noShow = false;
       		    	$scope.opration = '查看';
    		    }*/
            	
            	validateInit();// 加载表单验证控
            	
        	}
    });
    
    
	$scope.changeFlag = true
    $scope.repeatDone = function(scope){
    	$scope.changeFlag = false;
    	var date1= scope._procurementPlanMateriel.deliveryDate;
    	var date2= scope._procurementPlanMateriel.lastDeliveryDate;
    	/*var date3= scope.procurementPlan.orderDate;*/
    	$scope.datepickerInit();
    	if(scope._procurementPlanMateriel){
    		scope._procurementPlanMateriel.deliveryDate = date1;
    		scope._procurementPlanMateriel.lastDeliveryDate = date2;
    	}
    	$scope.changeFlag = true;
    	/*scope.procurementPlan.orderDate = date3;*/
   };
   
   $scope.repeatMaterielList = function(scope){
	   searchMaterielList();//订单物料可检索化
  };

   
   $scope.renderDone = function(){
   	var date3= $scope.procurementPlan.orderDate;
   	var date4= $scope.procurementPlan.makeDate;
/*   	var date5 = null;
   	var date6 = null
   	if(!isNull($scope.clauseCheckAccept.playCheckDate)){
   		date5= $scope.clauseCheckAccept.playCheckDate
   	}
   	if(!isNull($scope.contract.signDate)){
   		date6= $scope.contract.signDate
   	}*/
   	
   	$scope.datepickerInit();
   	$scope.procurementPlan.orderDate = date3;
   	$scope.procurementPlan.makeDate = date4;
/*   	$scope.clauseCheckAccept.playCheckDate = date5;
   	$scope.contract.signDate = date6*/
  };
   
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
    	if($('#form_sample_1').valid()){//
    		if($scope.procurementPlan.orderDate=='') {// 日期为空的处理
    			$scope.procurementPlan.orderDate=null;
    		}

    		$rootScope.judgeIsExist("procurementPlan",$scope.procurementPlan.procurementPlanNum, $scope.procurementPlan.serialNum,function(result){
    			var 	isExist = result;
    		if(isExist){
    			toastr.error('采购计划单号重复！');
    			return;
    		}else{

    		procurementPlanService.save($scope.procurementPlan).then(
	        		     function(data){
	        		    	$scope.procurementPlan = data;
	        		    	$scope.procurementPlanInput = true;
	        			    $scope.procurementPlanShow = true;
	        		     },
	        		     function(error){
	        		         $scope.error = error;
	        		         toastr.error('数据保存出错！');
	        		     }
	        		 );
    		}
    		
    		});
    	}
    	
    }; 	
    
    $scope.autoSave  = function() {};
    
    $scope.cancelPage  = function() {// 取消编辑
    	$state.go("procurementPlan");
    };
    
    $scope.cancel  = function() {// 取消编辑
    	if($scope.procurementPlan.serialNum==null || $scope.procurementPlan.serialNum=='') {// 如果是取消新增，返回列表页面
    		$state.go("procurementPlan");
    		return;
		}
    	$scope.getProcurementPlanInfo($scope.procurementPlan.serialNum,$stateParams.taskId, $stateParams.comments,$stateParams.processInstanceId);
    	$scope.cancelProcurementPlan();
    	
    };
    $scope.cancelProcurementPlan  = function() {// 取消编辑订单信息
    	$scope.procurementPlanInput = true;
	    $scope.procurementPlanShow = true;
    };
    
    $scope.edit  = function() {// 进入编辑
    	$scope.procurementPlanInput = false;
	    $scope.procurementPlanShow = false;
    };
    
    $scope.viewProcurementPlan = function(serialNum){
    	$state.go("viewProcurementPlan",{serialNum:serialNum});
    }
    $scope.goContract = function(serialNum){
    	$state.go("userContract",{});
    }
    $scope.viewGraphTrace = function(processInstanceId){
    	graphTrace(processInstanceId,ctx);
    }
    
    var table;
    var tableAjaxUrl = "rest/procurementPlan/findProcurementPlanList?type=buy";
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
                order: [[1, "desc"]],// 默认排序列及排序方式
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
                              { mData: 'procurementPlanNum' },
                              { mData: 'saleOrder' },
                              { mData: 'saleOrder' },
                              { mData: 'saleOrder' },
                              { mData: 'saleOrder' },
                              { mData: 'buyDate' },
                              { mData: 'buyCount' },
                              { mData: 'status' },
                              { mData: 'status' }
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
								return '<a href="javascript:void(0);" ng-click="viewProcurementPlan(\''+row.serialNum+'\')">'+data+'</a>'
							},
							"createdCell": function (td, cellData, rowData, row, col) {
								 $compile(td)($scope);
						       }
						},{
							'targets' : 2,
							'render' : function(data,
									type, row, meta) {
								if(isNull(data)){
									return '---';
								}else{
									return data.orderNum;
								}
							},
							"createdCell": function (td, cellData, rowData, row, col) {
								 $compile(td)($scope);
						       }
						},{
							'targets' : 3,
							'render' : function(data,
									type, row, meta) {
								if(isNull(data)){
									return '---';
								}else{
									return data.orderDate;
								}
							},
							"createdCell": function (td, cellData, rowData, row, col) {
								 $compile(td)($scope);
						       }
						},{
							'targets' : 4,
							'render' : function(data,
									type, row, meta) {
								if(isNull(data)){
									return '---';
								}else{
									return data.buyName;
								}
							},
							"createdCell": function (td, cellData, rowData, row, col) {
								 $compile(td)($scope);
						       }
						},{
							'targets' : 5,
							'render' : function(data,
									type, row, meta) {
								if(isNull(data)){
									return '---';
								}else{
									return data.materielCount;
								}
							},
							"createdCell": function (td, cellData, rowData, row, col) {
								 $compile(td)($scope);
						       }
						},{
							'targets' : 8,
							'className' : 'dt-body-center',
							'render' : function(data,
									type, full, meta) {
								if(data==1){
									return '<span style="color:green">已完成</span>'
								}else{
									return '<span style="color:red">待采购</span>'
								}
							}
						},{
							'targets' : 9,
							'className' : 'dt-body-center',
							'render' : function(data,
									type, row, meta) {
								if(data==1){
									return ''
								}else{
									return '<a href="javascript:void(0);" ng-click="viewProcurementPlan(\''+row.serialNum+'\')">发布采购</a><br/>'
								}
							},
							"createdCell": function (td, cellData, rowData, row, col) {
								 $compile(td)($scope);
						       }
						}]

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
        $scope.deleteProcurementPlan = function() {
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
    			$('#delProcurementPlanModal').modal('show');// 弹出删除确认模态框
    		}
			
		};
		
		$scope.editProcurementPlan  = function() {// 进入编辑页面
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
    			}/*else if(table.row('.active').data().status==1){
    				showToastr('toast-top-center', 'warning', '该订单已确认，不能修改！')
    			}*/else $state.go('addProcurementPlan',{serialNum:table.row('.active').data().serialNum});
    		}
    		
        };
        
        $scope.copyProcurementPlan  = function() {
    		if(table.rows('.active').data().length != 1){
    			showToastr('toast-top-center', 'warning', '请选择一个订单！')
    		}else{
    			handle.blockUI();
    			procurementPlanService
				.copyProcurementPlan(table.row('.active').data().serialNum)
				.then(
						function(data) {
							handle.unblockUI();
							toastr.success('订单复制成功！');
							 $state.go('procurementPlan',{},{reload:true});
							 
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
        $scope.deleteBuyFramProcurementPlan = function() {
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
    			$('#delProcurementPlanModal').modal('show');// 弹出删除确认模态框
    		}
			
		};
		
		$scope.editBuyFramProcurementPlan  = function() {// 进入编辑页面
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
    		
    		$state.go("addProcurementPlan",{serialNum:ids});
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
			
			procurementPlanService
					.delProcurementPlan(ids)
					.then(
							function(data) {
								$('#delProcurementPlanModal').modal(
										'hide');// 删除成功后关闭模态框
								$(".modal-backdrop").remove();
								/* table.ajax.reload(); // 重新加载datatables数据 */
								toastr.success('数据删除成功！');
								 $state.go('procurementPlan',{},{reload:true});
								 
							},
							function(errResponse) {
								toastr.error('数据删除失败！');
								console
										.error('Error while deleting Users');
							}

					);
		};
		// 删除结束***************************************
        
		jQuery.validator.addMethod("noFrameFlag", function(value, element) {  
			if($scope.contract.contractType!='采购订单'){
				return true;
			}else{
				if(isNull($scope.procurementPlan.frame)){
					return false;    
				}else{
					return true;  
				}
			}
			
		}, "框架协议不能为空"); 
		
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
	            	supplyComId:{required:"供应商不能为空！"},
	            	serviceModel:{required:"服务模式不能为空！"},
	            	settlementClause:{required:"结算条款不能为空！"},
	            	deliveryMode:{required:"提货方式不能为空！"},
	            	rate:{required:"税率不能为空！"},
	            	currency:{required:"币种不能为空！"},
	            	maker:{required:"制单人不能为空！"},
	            	seller:{required:"采购商不能为空！"},
	            	orderDate:{required:"下单日期不能为空！"},
	            	frameNum:{noFrameFlag:"框架协议不能为空！"}
	            },
            	rules: {orderNum: {required: !0,maxlength: 20},
            		orderType: {required: !0,maxlength: 20},
            		supplyComId: {required: !0,maxlength: 20},
            		serviceModel: {required: !0,maxlength: 20},
            		settlementClause: {required: !0,maxlength: 20},
            		deliveryMode: {required: !0,maxlength: 20},
            		rate: {required: !0,maxlength: 20},
            		maker: {required: !0,maxlength: 20},
	            	seller:{required: !0,maxlength: 20},
            		currency: {required: !0,maxlength: 20},
            		orderDate: {required: !0},
            		frameNum:{noFrameFlag:true}},
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
        $scope.getProcurementPlanInfo  = function(serialNum,taskId,comments,processInstanceId) {
        	procurementPlanService.getProcurementPlanInfo(serialNum).then(
          		     function(data){//加载页面对象
          		    	$scope.procurementPlan=data.procurementPlan;
          		    	$scope.procurementPlanMateriel=data.procurementPlanMateriel;
          		    	$scope.cancelAllProcurementPlanMateriel();
          		    	if($state.current.name=="viewProcurementPlan"){//查看页面构造物料查询分页
          		    		$scope.queryForPage();
          		    	}
          		    	
          		    	$scope.copyMateriels = angular.copy($scope.procurementPlanMateriel);
          		    	
          		    	// 加载数据
                    	initSuppliers();
                    	initWarehouse();
                    	initPtWarehouseAddress();
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
     /*
		 * fixedHeader: {//固定表头、表底 header: !0, footer: !0, headerOffset: a },
		 */
                     order: [[1, "desc"]],// 默认排序列及排序方式
                     searching: true,// 是否过滤检索
                     ordering:  true,// 是否排序
                     lengthMenu: [[5, 10, 15, 30, -1], [5, 10, 15, 30, "All"]],
                     pageLength: 5,// 每页显示数量
                     processing: true,// loading等待框
// serverSide: true,
                    /* ajax: "rest/materiel/findMaterielList?isLatestVersion=1&supplyComId="+$scope.procurementPlan.supplyComId,// 加载数据中*/  
                     ajax: "rest/materiel/findMaterielList?isLatestVersion=1",
                   "aoColumns": [
                                   { mData: 'serialNum' },
                                   { mData: 'materielNum' },
                                   { mData: 'materielName' },
                                   { mData: 'specifications' },
                                   { mData: 'unit' }/*,
                                   { mData: 'stockCount' }*/
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
            
   			
   			
        $scope.addDemandMateriel = function (type,index){
        	if(!isNull($scope.procurementPlan)&&!isNull($scope.procurementPlan.serialNum)){
        		if(table){
    				table.ajax.url(ctx+"/rest/materiel/findMaterielList?isLatestVersion=1").load();
    			}else{
    				selectMateriel();
    			}
    			$("#basicMaterielInfo").modal("show");
    		}else{
    			toastr.warning("请先保存基本信息!");
    			return;
    		}
		}
        $scope.chooseDemandMateriel=function(){//选中需求物料分解
        	var id_count = $('input[type="checkbox"][class="group-checkable1"]:checked').length;
        	var ids='';//全部选中的需求物料流水
        	var bom_ids='';//全部选中的需求物料流水中的bom物料流水
        	if(id_count==0){
        		toastr.warning("请先选择需求物料！");
				return;
			}
        	handle.blockUI();
        	$('input[type="checkbox"][class="group-checkable1"]').each(
					function() {
						// If checkbox exist in DOM
						if ($.contains(document, this)) {
							// If checkbox is checked
							if (this.checked) {
								// 将选中数据id放入ids中
								if (ids == '') {
									ids = this.id;
								} else{
									ids = ids + ','
									+ this.id;
								}
								var  param={};
								bom_ids = this.id;
								if(this.value!=1){
									$rootScope.getSerialNum(function(newCode){
										param.serialNum = newCode;//设置非bom物料产生采购清单物料流水
									});
										param.singleDose = 1;
										param.materielNum=$scope.demandMateriel[this.name].materielNum;
										param.materielName=$scope.demandMateriel[this.name].materielName;
										param.specifications=$scope.demandMateriel[this.name].specifications;
										param.unit=$scope.demandMateriel[this.name].unit;
										param.sets=$scope.demandMateriel[this.name].planCount;//设置台套
										param.deliveryDate=$scope.demandMateriel[this.name].deliveryDate;
										param.deliveryAddress=$scope.demandMateriel[this.name].deliveryAddress;
										param.buyCount=$scope.demandMateriel[this.name].planCount;//设置初始采购数量
										param.demandMaterielSerial=$scope.demandMateriel[this.name].serialNum;//设置需求物料流水
										param.procurementPlanSerial=$scope.procurementPlan.serialNum;//设置采购计划流水
										$scope.procurementPlanMateriel.push(param);//采购清单物料
									if($scope.procurementPlanMateriels){
										$scope.procurementPlanMateriels.push(param);//采购清单物料
									}else{
										$scope.procurementPlanMateriels=[];
										$scope.procurementPlanMateriels.push(param);
									}
								}else{
									if (bom_ids == '') {
										bom_ids = this.id;
									} else{
										bom_ids = bom_ids + ','+ this.id;
									}
								}
									
							}
						}
					});
        	if(bom_ids!=''){
        		$scope.decomposeMateriel(bom_ids);//分解选中BOM物料
        	}
        	
        }
        $scope.decomposeMateriel= function(ids){//单个或多个bom物料分解
        	var promise = procurementPlanService.getProcurementPlanMateriels(ids);
    		promise.then(function(data){
    			handle.unblockUI();
    			var procurementPlanMateriels=data.procurementPlanMateriels;
    		},function(data){
    			// 调用承诺接口reject();
    		});
			return;
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
        				$scope.procurementPlanMateriel[$scope.materielSelectedIndex].materielSerial = data.data[0].serialNum;
        				$scope.procurementPlanMateriel[$scope.materielSelectedIndex].materiel = data.data[0];
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
        			if($scope.procurementPlanMateriel.length==0){
        				for(var i = 0;i < data.data.length;i++){// data.data为选择的标准物料
        					$scope.tempMateriel = {};
        					$scope.tempMateriel.materiel = (data.data)[i];
        					$scope.tempMateriel.procurementPlanSerial = $scope.procurementPlan.serialNum;
        					$scope.tempMateriel.materielSerial = (data.data)[i].serialNum;
        					$scope.procurementPlanMateriel.push($scope.tempMateriel);
        					$scope["procurementPlanMaterielInput"+i] = false;
        					$scope["procurementPlanMaterielShow"+i] = false;
        				}
        			}else{
        				for(var i = 0;i < data.data.length;i++){// data.data为选择的标准物料
        					$scope.tempMateriel = {};
        					$scope.tempMateriel.materiel = (data.data)[i];
        					$scope.tempMateriel.procurementPlanSerial = $scope.procurementPlan.serialNum;
        					$scope.tempMateriel.materielSerial = (data.data)[i].serialNum;
        					$scope.procurementPlanMateriel.push($scope.tempMateriel);
	        				$scope["procurementPlanMaterielInput"+(length+i)] = false;
							$scope["procurementPlanMaterielShow"+(length+i)] = false;
							/*$scope["procurementPlanMaterielInput" + ($scope.procurementPlanMateriel.length-1)] = true;
							$scope["procurementPlanMaterielShow" + ($scope.procurementPlanMateriel.length-1)] = true;*/
		        		}
        			}
        			$scope.copyMateriels = angular.copy($scope.procurementPlanMateriel);
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
	    	   if(type=='procurementPlanMateriel'){
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
	    	   if(type=='procurementPlanMateriel'){
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
    	 
	       
	       $scope.saveAllProcurementPlanMateriel  = function() {//保存所有物料
  	   	    	if($scope.procurementPlan.serialNum==null||$scope.procurementPlan.serialNum=='') {// 订单信息为空的处理
  	   	    		toastr.error('请先保存采购计划信息！');return
  	    		}
  	   	    	if($('#form_sample_5').valid()){
  	   	    	procurementPlanService.saveAllProcurementPlanMateriel($scope.procurementPlanMateriel).then(
  	   	       		     function(data){
  	   	       		    	toastr.success('数据保存成功！');
  	   	       		    	$scope.cancelAllProcurementPlanMateriel();
			  	   	       		//更新采购计划数量数据
	  	   		        	$scope.updateProcurementPlanAmount();	
				  	   	    
  	   	       		     },
  	   	       		     function(error){
  	   	       		    	toastr.error('数据保存出错！');
  	   	       		         $scope.error = error;
  	   	       		     }
  	   	       		 );
  	   	    	}
  	   	    	
  	   	    };
    	 /**
			 * 保存采购订单物料信息
			 */
			$scope.saveProcurementPlanMateriel = function(procurementPlanMateriel,index) {
/*
 * var procurementPlanMateriel = {}; procurementPlanMateriel.deliveryAddress =
 * supplyMateriel.deliveryAddress; procurementPlanMateriel.deliveryDate =
 * supplyMateriel.deliveryDate; procurementPlanMateriel.lastDeliveryDate =
 * supplyMateriel.lastDeliveryDate; procurementPlanMateriel.materielSerial =
 * supplyMateriel.materielSerial; procurementPlanMateriel.orderSerial =
 * supplyMateriel.orderSerial; procurementPlanMateriel.orderUnitPrice =
 * supplyMateriel.orderUnitPrice; procurementPlanMateriel.supplyComId =
 * supplyMateriel.supplyComId; procurementPlanMateriel.supplyMaterielSerial =
 * supplyMateriel.supplyMaterielSerial;
 */
						
				if($scope.procurementPlan.serialNum==null||$scope.procurementPlan.serialNum=='') {// 订单信息为空的处理
  	   	    		toastr.error('请先保存订单信息！');return
  	    		}
				delete procurementPlanMateriel.materiel;
				delete procurementPlanMateriel.supplyMateriel;
				delete procurementPlanMateriel.supply;
				
				var promise = procurementPlanService
				.saveProcurementPlanMateriel(procurementPlanMateriel);
				promise.then(function(data) {
					if (!handle.isNull(data.data)) {
						$(".modal-backdrop").remove();
						toastr.success("保存成功");
						handle.unblockUI();
						// var company = data.data;
						// $state.go('companyAdd',company,{reload:true});
						$scope.procurementPlanMateriel[index] = data.data;
						$scope.copyMateriels[index] = data.data;
/*						console.log(data.data);*/
						$scope["procurementPlanMaterielInput"+index] = true;
						$scope["procurementPlanMaterielShow"+index] = true;
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
	        $scope.cancelProcurementPlanMateriel=function (materiel,index) {
	        	// .show_materiels = false;
	        	$scope["procurementPlanMaterielInput"+index] = true;
				$scope["procurementPlanMaterielShow"+index] = true;
	        	for(var i=0;i<$scope.copyMateriels.length;i++){
	        		if(materiel.serialNum == $scope.copyMateriels[i].serialNum){ // 如果是以保存的物料，回滚
	        			$scope.procurementPlanMateriel[$scope.procurementPlanMateriel.indexOf(materiel)] = $scope.copyMateriels[i];
						break;
	        		}
	        		
	        		/*if(i==$scope.copyMateriels.length-1){ // 如果是已选择但未保存的物料，清空
	        			$scope.procurementPlanMateriel[$scope.procurementPlanMateriel.indexOf(materiel)].deliveryDate = "";
	        			$scope.procurementPlanMateriel[$scope.procurementPlanMateriel.indexOf(materiel)].deliveryAddress = "";
	        			$scope.procurementPlanMateriel[$scope.procurementPlanMateriel.indexOf(materiel)].lastDeliveryDate = "";
	        			$scope.procurementPlanMateriel[$scope.procurementPlanMateriel.indexOf(materiel)].amount = "";
	        			$scope.procurementPlanMateriel[$scope.procurementPlanMateriel.indexOf(materiel)].procurementPlanUnitPrice = "";
	        		}*/
	        	}
	        };  
	        
	        /**
			 * 撤销所有物料编辑
			 */
	        $scope.cancelAllProcurementPlanMateriel=function () {
	        	$scope.procurementPlanMaterielInput = true;
	        	$scope.procurementPlanMaterielShow = true;
	        	for(var i=0;i<$scope.procurementPlanMateriel.length;i++){
	        		$scope["procurementPlanMaterielInput"+i] = true;
					$scope["procurementPlanMaterielShow"+i] = true;
	        	}
	        }; 
	        
	        /**
			 * 打开所有物料编辑
			 */
	        $scope.editAllProcurementPlanMateriel=function () {
	        	$scope.procurementPlanMaterielInput = false;
	        	$scope.procurementPlanMaterielShow = false;
	        	for(var i=0;i<$scope.procurementPlanMateriel.length;i++){
	        		$scope["procurementPlanMaterielInput"+i] = false;
					$scope["procurementPlanMaterielShow"+i] = false;
	        	}
	        }; 
	        //选择第一个，设置后面的数据
			$scope.setAllDeliveryAddress = function(procurementPlanMateriel){
				 for(var i=1;i<$scope.procurementPlanMateriel.length;i++){
					 if($scope["procurementPlanMaterielInput"+i] != true/*&&isNull($scope.procurementPlanMateriel[i].deliveryAddress)*/){
						 $scope.procurementPlanMateriel[i].deliveryAddress = procurementPlanMateriel.deliveryAddress;
					 }
				 }
			}
			
			$scope.setAllDeliveryDate = function(procurementPlanMateriel,index){
				if(!isNull($scope.procurementPlanMateriel[index].lastDeliveryDate)&&$scope.procurementPlanMateriel[index].deliveryDate>$scope.procurementPlanMateriel[index].lastDeliveryDate){
		    		toastr.warning('交付日期不能大于最晚交付日期  ！');
		    		$scope.procurementPlanMateriel[index].deliveryDate=null;
		    		return;
		    	}
				if(index==0&&$scope.changeFlag){
					for(var i=1;i<$scope.procurementPlanMateriel.length;i++){
						 if($scope["procurementPlanMaterielInput"+i] != true/*&&isNull($scope.procurementPlanMateriel[i].deliveryAddress)*/){
							 $scope.procurementPlanMateriel[i].deliveryDate = procurementPlanMateriel.deliveryDate;
						 }
					 }
				}
			}
			
			$scope.setAllLastDeliveryDate = function(procurementPlanMateriel,index){
				if(!isNull($scope.procurementPlanMateriel[index].deliveryDate)&&$scope.procurementPlanMateriel[index].deliveryDate>$scope.procurementPlanMateriel[index].lastDeliveryDate){
		    		toastr.warning('最晚交付日期不能小于交付日期  ！');
		    		$scope.procurementPlanMateriel[index].lastDeliveryDate=null;
		    		return;
		    	}
				if(index==0&&$scope.changeFlag){ 
					for(var i=1;i<$scope.procurementPlanMateriel.length;i++){
						 if($scope["procurementPlanMaterielInput"+i] != true/*&&isNull($scope.procurementPlanMateriel[i].deliveryAddress)*/){
							 $scope.procurementPlanMateriel[i].lastDeliveryDate = procurementPlanMateriel.lastDeliveryDate;
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
	        $scope.editProcurementPlanMateriel=function (materiel) {
	        	// .show_materiels = false;
	        	for(var i=0;i<$scope.procurementPlanMateriel.length;i++){
	        		if(materiel.serialNum == $scope.procurementPlanMateriel[i].serialNum){
	        			$scope["procurementPlanMaterielInput"+i] = false;
	        			$scope["procurementPlanMaterielShow"+i] = false;
	        		}
	        	}
	        	
	        };  
	    	//修改代发货
			$scope.takeDeliveryEdit = function() {		
				if(TakeDelieryTable.rows('.active').data().length != 1){
					showToastr('toast-top-center', 'warning', '请选择一条数据进行修改！')
				}else{
					
					if(TakeDelieryTable.row('.active').data().status == '0'){
						//$state.go("takeDeliveryView",{serialNum:serialNum,oprateType:'forProcurementPlan'});
						$state.go('takeDeliveryAdd',{serialNum:TakeDelieryTable.row('.active').data().takeDelivery.serialNum,oprateType:"forProcurementPlan",type:'edit'});
					}else showToastr('toast-top-center', 'warning', '已确认发货不能修改');
				} 
			};
			   /**
	         * 批量删除收货计划
	         */
	        $scope.takeDeliveryDelete = function () {};
	        
	    	//确认代发货
			$scope.jumpToConfirm = function() {		
				if(TakeDelieryTable.rows('.active').data().length != 1){
					showToastr('toast-top-center', 'warning', '请选择一条数据进行确认！')
				}else{
					
					if(TakeDelieryTable.row('.active').data().status == '0'){
						$state.go('takeDeliveryView',{serialNum:TakeDelieryTable.row('.active').data().takeDelivery.serialNum,oprateType:"forProcurementPlan"});
						//$state.go('takeDeliveryView',{serialNum:TakeDelieryTable.row('.active').data().serialNum,oprateType:"forProcurementPlan"});
					}else showToastr('toast-top-center', 'warning', '已确认代发货')
				} 
			};
	        
	        /**
			 * 删除
			 */
	        $scope.deleteProcurementPlanMateriel=function (materiel) {
	        	handle.confirm("确定删除吗？",function(){
	        		if($scope.procurementPlanMateriel.length > 0){
	        			for(var i=0;i<$scope.procurementPlanMateriel.length;i++){
	        				if(materiel == $scope.procurementPlanMateriel[i]){
	        					$scope.procurementPlanMateriel.splice(i,1);
	        				}
	        			}
	        		}
	        		if(!isNull(materiel.serialNum)){
	        			handle.blockUI();
	        			var promise = procurementPlanService.deleteProcurementPlanMateriel(materiel.serialNum);
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
    	 /** *************订单物料操作 end*************** */
    	 
 
		     //更新订单金额数据
		     $scope.updateProcurementPlanAmount = function(obj,attr){
		    	$scope.submitProcurementPlan = {}
   	        	$scope.submitProcurementPlan.serialNum = $scope.procurementPlan.serialNum;
   	        	$scope.submitProcurementPlan.buyCount = $scope.totalMaterielCount();
   	        	
	    	    procurementPlanService.save($scope.submitProcurementPlan).then(
          		     function(data){
          		    	$state.go("viewProcurementPlan",{serialNum:$scope.procurementPlan.serialNum});
          		     },
          		     function(error){
          		         $scope.error = error;
          		         toastr.error('数据保存出错！');
          		     }
          		 );
		      }
		     
		     /*$scope._arithmeticDeliveryAmount  = function(scope) {//计算支付金额
			       	if(scope._CSD.deliveryRate){
			       		scope._CSD.deliveryAmount =  ($scope.totalProcurementPlanAmount()*scope._CSD.deliveryRate/100).toFixed(2);
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
		       $scope.submitBuyApply  = function(serialNum) {// 进入申请审批页面
		    	   if(!isNull(serialNum)){//列表操作栏按钮进入审批申请
		    			$state.go('submitBuyApply',{serialNum:serialNum});
		    		}else if(!isNull($scope.procurementPlan)&&!isNull($scope.procurementPlan.serialNum)){//详情页面进入审批
		    			var processBase = $scope.procurementPlan.processBase;
		    			if(processBase != null){
		    				showToastr('toast-top-center', 'warning', '该订单已发起流程审批，不能再次申请！')
		    			}else $state.go('submitBuyApply',{serialNum:$scope.procurementPlan.serialNum});
		    		}else if(table.rows('.active').data().length != 1){//列表选择进入审批申请
		    			showToastr('toast-top-center', 'warning', '请选择一条任务进行流程申请！')
		    		}else{
		    			var processBase = table.row('.active').data().processBase;
		    			if(processBase != null){
		    				showToastr('toast-top-center', 'warning', '该订单已发起流程审批，不能再次申请！')
		    			}else $state.go('submitBuyApply',{serialNum:table.row('.active').data().serialNum});
		    		}     	
		        };
		        
		        
		      
		        
    /**
	 * 加载供应商数据
	 */
	var initSuppliers = function(){
		var promise = procurementPlanService.initSuppliers();
        	promise.then(function(data){
        		$scope.suppliers = data.data;
        		setTimeout(function () {
        			$("#supplyComId").selectpicker({
                        showSubtext: true,
                        size : 5
                    });
        			$('#supplyComId').selectpicker('refresh');//刷新插件
        			
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
	var promise = procurementPlanService.initWarehouse();
	promise.then(function(data){
		$scope.warehouses = data.data;
	},function(data){
		//调用承诺接口reject();
	});
	}
	/**
	 * 加载平台仓库数据
	 */
	var initPtWarehouseAddress = function(){
	var promise = procurementPlanService.initPtWarehouseAddress();
	promise.then(function(data){
		$scope.warehouseAddresses = data.data;
		setTimeout(function () {
			$("select[name='warehouseAddress1']").selectpicker({
                showSubtext: true,
                size : 5
            });
			$("select[name='warehouseAddress1']").selectpicker('refresh');//刷新插件
			
        }, 100);
	},function(data){
		//调用承诺接口reject();
	});
	}
	
	 
	 
	 /** *************订单物料明细可检索化  start*************** */
	 $scope.pageIndex = 1; //记录当前页
	 $scope.pageSize = '10'; //每页的记录数
	 $scope.totalPage = '1'; //记录总页数
	 $scope.dispalyProcurementPlanMateriel = [];//页面显示结果
	 $scope.filterProcurementPlanMateriel = [];//查询筛选结果
	 
	 $scope.createFilterList = function(){
		 $scope.filterProcurementPlanMateriel = [];
		if($scope.procurementPlanMateriel.length>0&&$scope.queryStr&&!isNull($scope.queryStr)){
			for(var i = 0;i < $scope.procurementPlanMateriel.length;i++){// data.data为选择的标准物料
				if(!isNull(($scope.procurementPlanMateriel)[i].materiel.materielNum)&&($scope.procurementPlanMateriel)[i].materiel.materielNum.indexOf($scope.queryStr)>=0){
					$scope.filterProcurementPlanMateriel.push(angular.copy(($scope.procurementPlanMateriel)[i]));
				}else if(!isNull(($scope.procurementPlanMateriel)[i].materiel.materielName)&&($scope.procurementPlanMateriel)[i].materiel.materielName.indexOf($scope.queryStr)>=0){
					$scope.filterProcurementPlanMateriel.push(angular.copy(($scope.procurementPlanMateriel)[i]));
				}else if(!isNull(($scope.procurementPlanMateriel)[i].materiel.specifications)&&($scope.procurementPlanMateriel)[i].materiel.specifications.indexOf($scope.queryStr)>=0){
					$scope.filterProcurementPlanMateriel.push(angular.copy(($scope.procurementPlanMateriel)[i]));
				}
			}
		}else{
			$scope.filterProcurementPlanMateriel = angular.copy($scope.procurementPlanMateriel);
		}
		
	 };
	 
	 $scope.createDispalyList = function(){
		 $scope.dispalyProcurementPlanMateriel = $scope.filterProcurementPlanMateriel.slice(
				 ($scope.pageIndex-1)*$scope.pageSize,
				 $scope.pageIndex*$scope.pageSize);
		 
		 $scope.totalPage = Math.ceil($scope.filterProcurementPlanMateriel.length/$scope.pageSize);
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
   
   $scope.clearNoNum = function(obj,attr,attr1){
    	 //把非数字的都替换掉
    	 obj[attr] = obj[attr].replace(/[^\d]/g,"");
    	 if(isNull(obj[attr] )){
    		 obj[attr]=0;
    		 return;
    	 }
    	 if(!isNaN(obj[attr])&&Number(obj[attr])>Number(obj[attr1])){
    		 toastr.warning('采购数量不得大于需求数量！');
    		 obj[attr]=0;
    	 }
    	 
	 }     
   
	   $scope.totalCount  = function() {
	      	if($scope.procurementPlanMateriel){
	      		return $scope.procurementPlanMateriel.length;
	      	}else{
	      		return 0;
	      	}
	  };
	  
	  $scope.totalMaterielCount  = function(scope) {
		   if($scope.procurementPlanMateriel){
			    var total = 0 ; 
	      		for(var i=0;i<$scope.procurementPlanMateriel.length;i++){
	      			if(!isNull($scope.procurementPlanMateriel[i].buyCount)){
	      				total = total + Number($scope.procurementPlanMateriel[i].buyCount);
	      			}
	      			
	      		}
	      		return total
	      	}else{
	      		return 0;
	      	}
	  };
	  
	  $scope.totalMaterielPlanCount  = function(scope) {
		   if($scope.procurementPlanMateriel){
			    var total = 0 ; 
	      		for(var i=0;i<$scope.procurementPlanMateriel.length;i++){
	      			if(!isNull($scope.procurementPlanMateriel[i].planCount)){
	      				total = total + Number($scope.procurementPlanMateriel[i].planCount);
	      			}
	      			
	      		}
	      		return total
	      	}else{
	      		return 0;
	      	}
	  };
	  $scope.totalEndCount = function(scope) {
		   if($scope.procurementPlanMateriel){
			    var total = 0 ; 
	      		for(var i=0;i<$scope.procurementPlanMateriel.length;i++){
	      			if(!isNull($scope.procurementPlanMateriel[i].planCount)){
	      				total = total + Number($scope.procurementPlanMateriel[i].planCount);
	      			}
	      			
	      		}
	      		return total
	      	}else{
	      		return 0;
	      	}
	  };
	  $scope.procurementPlanGenerateBuy  = function() {// 分解订单
      	if($scope.procurementPlan.status!=0){
      		toastr.info('已有对应采购单，不能再次分解！');
      		return;
      	}
      	procurementPlanService.procurementPlanGenerateBuy($scope.procurementPlan.serialNum).then(
        		     function(data){
        		    	$scope.procurementPlan = data
        		    	toastr.info('采购计划分解成功！');
        		     },
        		     function(error){
        		         $scope.error = error;
        		         toastr.error('数据保存出错！');
        		     }
        		 );
      };
  	//销售订单列表
      var table1;
	    var loadZizhuSaleTable = function() {
	            a = 0;
	            App.getViewPort().width < App.getResponsiveBreakpoint("md") ? $(".page-header").hasClass("page-header-fixed-mobile") && (a = $(".page-header").outerHeight(!0)) : $(".page-header").hasClass("navbar-fixed-top") ? a = $(".page-header").outerHeight(!0) : $("body").hasClass("page-header-fixed") && (a = 64);
	            table1 = $("#sample_21")
				.DataTable({
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
	                searching: true,//是否过滤检索
	                ordering:  true,//是否排序
	                lengthMenu: [[5, 10, 15, 30, -1], [5, 10, 15, 30, "All"]],
	                pageLength: 5,//每页显示数量
	                processing: true,//loading等待框
//	                serverSide: true,
	                ajax:"rest/order/findOrderList?type=sale&selectFor=zizhuSale",//加载数据中
	                "aoColumns": [
	                              { mData: 'serialNum' },
                            { mData: 'orderNum' },
                            { mData: 'buyName' },
                            { mData: 'materielCount' },
                            { mData: 'orderAmount' },
                            /*{ mData: 'deliveryMode' },*/
                            { mData: 'orderType' },
                            /*{ mData: 'saleApplySerial' },*/
                            { mData: 'orderSerial' },
                            { mData: 'orderDate' }

	                        ],
	               'aoColumnDefs' : [ {
								'targets' : 0,
								'searchable' : false,
								'orderable' : false,
								'render' : function(data,
										type, full, meta) {
									return '<input type="radio" name="serialNum" value="'
														+ $('<div/>')
														.text(
																data)
														.html()
												+ '">';
								},
								"createdCell": function (td, cellData, rowData, row, col) {
									 $compile(td)($scope);
							       }
							},{
								'targets' : 2,
								'searchable' : false,
								'orderable' : false,
								'render' : function(data,
										type, full, meta) {
									if(isNull(data)){
										return '中航能科（上海）能源科技有限公司'
									}else{
										return data;
									}
								},
								"createdCell": function (td, cellData, rowData, row, col) {
									 $compile(td)($scope);
							       }
							} ]

	            }).on('order.dt',
	            function() {
	                console.log('排序');
	            })
	        };
	        // 确认选择销售订单开始***************************************
	        var ids = '';
			 $scope.confirmSelectOrder = function() {
				 
				 // Iterate over all checkboxes in the table
				 table1.$('input[type="radio"]').each(
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
					 toastr.warning('请选择一个订单！');return;
				 }
				 $scope.getSaleOrderInfo(ids);
				 $('#saleOrderInfo').modal('hide');// 删除成功后关闭模态框
				 $(".modal-backdrop").remove();
			 };
			 //获取订单物料的信息
		        $scope.getSaleOrderInfo  = function(serialNum) {
		        	DeliveryService.getSaleOrderInfo(serialNum).then(
		          		     function(data){
		          		    	$scope.saleOrder=data.orderInfo;
		          		    	if(isNull(data.orderInfo.buyName)){
		          		    		$scope.saleOrder.buyName = '中航能科（上海）能源科技有限公司'
		          		    	}else{
		          		    		$scope.saleOrder.buyName=data.orderInfo.buyName;//客户默认销售订单的采购方
		          		    	}
		          		    	$scope.procurementPlan.maker=data.currenLoginName;//制单人默认当前用户
		          		    	$scope.procurementPlan.buyCount=data.orderInfo.materielCount;//选择销售订单是订单物料数量即为需求数量
		          		    
		          		    	var orderSerial=data.orderInfo.serialNum;
		          		    	$scope.procurementPlan.saleOrderSerial=data.orderInfo.serialNum;//为采购计划赋值销售流水
		          		    	$scope.demandMateriel=data.orderMateriel;
		          		    	
		          		    	$scope.materielCount=data.orderMateriel.length;
		          		    	var totalOrderCount=0,totalDeliveryedCount=0,totalUnDeliveryCount=0;
		          		    	var array=new Array();
		          		    	for(var i=0;i< $scope.procurementPlanMateriel.length;i++){
		          		    		if($scope.procurementPlanMateriel[i].amount-$scope.procurementPlanMateriel[i].deliveredCount!=0){//未发数量不为0,统计
		          		    			totalOrderCount+=Number($scope.procurementPlanMateriel[i].amount);
			          		    	/*	totalDeliveryedCount+=Number($scope.deliveryMaterielE[i].deliveredCount);
			          		    		totalUnDeliveryCount+=Number($scope.deliveryMaterielE[i].amount-$scope.deliveryMaterielE[i].deliveredCount);*/
		          		    			array.push($scope.procurementPlanMateriel[i]);
		          		    		}
		          		    	}
		          		    	$scope.procurementPlanMateriel=array;
		          		    	$scope.totalOrderCount=totalOrderCount;
		          		    	$scope.totalDeliveryCount=totalOrderCount-totalDeliveryedCount;
		          		    	$scope.totalUnDeliveryCount=totalUnDeliveryCount;
		          		    	
		          		    	//重新加载发货仓库和收货仓库
		          		    	
		          		     },
		          		     function(error){
		          		         $scope.error = error;
		          		     }
		          		 );
		        	
		        }; 
}]);


