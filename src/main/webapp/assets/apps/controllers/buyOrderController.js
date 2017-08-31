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
        	loadMainTable();// 加载订单列表
        	}else{
            	$('.date-picker').datepicker({
    				rtl: App.isRTL(),
    				orientation: "left",
    				autoclose: true,
    				dateFormat:"yyyy-mm-dd",
    				language: "zh-CN"
            	})
            	// 初始化日期控件
            	     	
            	$scope.opration = {};
            	$scope.serialNums = [];
            	// 加载数据
            	if($stateParams.serialNum){
            		$scope.opration = '修改';
            		$scope.getBuyOrderInfo($stateParams.serialNum)
            	}else{
            		$scope.opration = '新增';
            		$scope.orderMateriel={};
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
            	
            	validateContractInit();// 加载合同表单验证控件
            	
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
    
    $scope.repeatDone = function(){
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
       		    	toastr.success('数据保存成功！');
       		    	$location.search({serialNum:data.serialNum,view:1});
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
    	$scope.getBuyOrderInfo($scope.buyOrder.serialNum);
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
    
    var table;
    var tableAjaxUrl = "rest/order/findOrderList";
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
                order: [[1, "asc"]],// 默认排序列及排序方式
                searching: true,// 是否过滤检索
                ordering:  true,// 是否排序
                lengthMenu: [[5, 10, 15, 30, -1], [5, 10, 15, 30, "All"]],
                pageLength: 5,// 每页显示数量
                processing: true,// loading等待框
// serverSide: true,
                ajax: tableAjaxUrl,// 加载数据中
                "aoColumns": [
                              { mData: 'serialNum' },
                              { mData: 'orderNum' },
                              { mData: 'buyComId' },
                              { mData: null },
                              { mData: null },
                              { mData: 'deliveryMode' },
                              { mData: 'serviceModel' },
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
								return '<input type="checkbox" id="'+data+'" ng-click="getBuyOrderInfo_(\''+data+'\')" name="serialNum[]" value="'
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
    		
    		$state.go("addBuyOrder",{serialNum:ids});
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
	            	orderNum:{required:"销售订单号不能为空！"},
	            	orderType:{required:"销售类型不能为空！"},
	            	buyComId:{required:"采购方不能为空！"},
	            	serviceModel:{required:"服务模式不能为空！"},
	            	settlementClause:{required:"结算条款不能为空！"},
	            	deliveryMode:{required:"提货方式不能为空！"},
	            	rate:{required:"税率不能为空！"},
	            	currency:{required:"币种不能为空！"}
	            },
            	rules: {orderNum: {required: !0,maxlength: 20},
            		orderType: {required: !0,maxlength: 20},
            		buyComId: {required: !0,maxlength: 20},
            		serviceModel: {required: !0,maxlength: 20},
            		settlementClause: {required: !0,maxlength: 20},
            		deliveryMode: {required: !0,maxlength: 20},
            		rate: {required: !0,maxlength: 20},
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
        $scope.getBuyOrderInfo  = function(serialNum) {
        	orderService.getOrderInfo(serialNum).then(
          		     function(data){//加载页面对象
          		    	$scope.buyOrder=data.orderInfo;
          		    	$scope.orderMateriel=data.orderMateriel;
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
        					
        					if($scope.contract.contractType=="框架合同"){
        						$scope.showClauseFramework();
              	        	}
          		    	}else{
          		    		$scope.contract = {};
          		    	}
          		    	
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
                                   { mData: 'unit' },
                                   { mData: 'supplyMateriels' }
                             ],
                    'aoColumnDefs' : [ {
     							'targets' : 0,
     							'searchable' : false,
     							'orderable' : false,
     							
     							'render' : function(data,
     									type, row, meta) {
     								if(row.supplyMateriels.length>0){
     									if($scope.modalType=='single'){
         	  								return '<input type="radio" id="'+ row.serialNum +'" ng-click="getCheckedIds(\''+data+'\','+meta.row+')" name="serialNum" value="'
       										+ $('<div/>')
       												.text(
       														row.supplyMateriels[0].serialNum)
       												.html()
       										+ '">';

         								}else{
         	  								return '<input type="checkbox" data-checked=false id="'+ row.serialNum +'" ng-click="getCheckedIds(\''+data+'\','+meta.row+')" name="serialNum[]" value="'
       										+ $('<div/>')
       												.text(
       														row.supplyMateriels[0].serialNum)
       												.html()
       										+ '">';

         								}
     								}else{
     									return '';
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

     						},{
     							'targets' : 5,
     							'render' : function(data,
     									type, row, meta) {
     								if(data.length>0){
     									var select='<select class="form-control" id="select'+row.serialNum+'" ng-model="model'+row.serialNum+'" ng-init="model'+row.serialNum+'=\''+data[0].serialNum+'\'" ng-change="changeSelectValue(\'select'+row.serialNum+'\',\''+row.serialNum+'\')">'
 	 									for(var i=0;i<data.length;i++){
 	 										if(data[i].supply){
 	 											select = select + '<option value="'+data[i].serialNum+'">'+data[i].supply.comName+'</option>';
 	 										}else{
 	 											select = select + '<option value="'+data[i].serialNum+'"></option>';
 	 										}
 	 										
 	 									}
 	 									select = select + '</select>';	
 	     								return select;
     								}else{
     									return '无供应商';
     								}
     							},
     							"createdCell": function (td, cellData, rowData, row, col) {
    								 $compile(td)($scope);
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
                
             };

             /**
      		 * checkbox点击事件（生成选中内容）
      		 */
      		$scope.getCheckedIds = function(serialNum,index){
      			var data={};
      			data.serialNum = serialNum;
      			data.materiel = table.row(index).data(); //获取一行数据
      			data.materiel.materielSerial = data.materiel.serialNum; //为保存操作做准备，新增物料serialNum为空
      			data.materiel.serialNum = null
      			data.materiel.supplyMaterielSerial = $("#"+serialNum).val();
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
        				$scope.orderMateriel[$scope.materielSelectedIndex].supplyMaterielSerial = data.data[0].serialNum;
        				$scope.orderMateriel[$scope.materielSelectedIndex].materielSerial = data.data[0].materiel.serialNum;
        				$scope.orderMateriel[$scope.materielSelectedIndex].materiel = data.data[0].materiel;
        				$scope.orderMateriel[$scope.materielSelectedIndex].supplyMateriel = data.data[0];
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
						ids = $scope.serialNums[i].materiel.supplyMaterielSerial;
					} else{
						ids = ids + ',' + $scope.serialNums[i].materiel.supplyMaterielSerial;
					}
				}
        		handle.blockUI();
        		var promise = materielService.chooseMateriels(ids);
        		promise.then(function(data){
        			toastr.success("添加成功！");
        			handle.unblockUI();
        			if($scope.orderMateriel.length==0){
        				for(var i = 0;i < data.data.length;i++){// data.data为选择的供应物料
        					$scope.tempMateriel = {};
        					$scope.tempMateriel.materiel = (data.data)[i].materiel;
        					$scope.tempMateriel.orderSerial = $scope.buyOrder.serialNum;
        					$scope.tempMateriel.materielSerial = (data.data)[i].materiel.serialNum;
        					$scope.tempMateriel.supplyMaterielSerial = (data.data)[i].serialNum;
        					$scope.tempMateriel.supplyMateriel = (data.data)[i];
        					$scope.orderMateriel.push($scope.tempMateriel);
        					$scope["orderMaterielInput"+i] = false;
        					$scope["orderMaterielShow"+i] = false;
        				}
        			}else{
        				for(var i = 0;i < data.data.length;i++){// data.data为选择的供应物料
        					$scope.tempMateriel = {};
        					$scope.tempMateriel.materiel = (data.data)[i].materiel;
        					$scope.tempMateriel.orderSerial = $scope.buyOrder.serialNum;
        					$scope.tempMateriel.materielSerial = (data.data)[i].materiel.serialNum;
        					$scope.tempMateriel.supplyMaterielSerial = (data.data)[i].serialNum;
        					$scope.tempMateriel.supplyMateriel = (data.data)[i];
        					$scope.orderMateriel.unshift($scope.tempMateriel);
	        				$scope["orderMaterielInput"+i] = false;
							$scope["orderMaterielShow"+i] = false;
							$scope["orderMaterielInput" + ($scope.orderMateriel.length-1)] = true;
							$scope["orderMaterielShow" + ($scope.orderMateriel.length-1)] = true;
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
			 * 保存销售订单物料信息
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
						console.log(data.data);
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
	        		if(materiel.serialNum == $scope.copyMateriels[i].serialNum && !isNull(materiel.supplyMaterielSerial)){ // 如果是以保存的物料，回滚
	        			$scope.orderMateriel[$scope.orderMateriel.indexOf(materiel)] = $scope.copyMateriels[i];
						break;
	        		}
	        		
	        		if(i==$scope.copyMateriels.length-1){ // 如果是已选择但未保存的物料，清空
	        			$scope.orderMateriel[$scope.orderMateriel.indexOf(materiel)].deliveryDate = "";
	        			$scope.orderMateriel[$scope.orderMateriel.indexOf(materiel)].deliveryAddress = "";
	        			$scope.orderMateriel[$scope.orderMateriel.indexOf(materiel)].lastDeliveryDate = "";
	        			$scope.rootMateriels[$scope.rootMateriels.indexOf(materiel)].amount = "";
	        			$scope.rootMateriels[$scope.rootMateriels.indexOf(materiel)].orderUnitPrice = "";
	        		}
	        	}
	        };  
	        


			function indexOf(arr, item) {
				for (var i = 0; i < arr.length; i++) {
						if (arr[i] === item)
							return i;
						else
							return -1;
				}
			}
	        
	        /**
			 * 编辑销售订单物料
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
	        		if(!isNull(materiel.supplyMaterielSerial)){
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
		            	electronicContract:{required:"合同附件不能为空！"}
		            },
	            	rules: {contractNum:{required:true,
			                	rangelength:[3,12]
				                },
				                startDate:{required:true,
				                },
				                endDate:{required:true,
				                }/*,
				                electronicContract:{required:true,
				                }*/
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
			    	   $scope.file[index].file = "";
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
    		toastr.error('请先保存合同信息！');return
		}
    	if(isNull($scope.clauseSettlement)){// 结算条款为空的处理
    		toastr.error('请填写结算条款后保存！');return
		}
    	if($('#form_clauseSettlement').valid()){
    		$scope.clauseSettlement.contractSerial = $scope.contract.id;
    		$scope.clauseSettlementDetail = $scope.clauseSettlement.CSD;
    		delete $scope.clauseSettlement.CSD;
    		orderService.saveClauseSettlement($scope.clauseSettlement).then(//保存结算条款
       		     function(data){
       		    	$scope.clauseSettlement = data.data;
       		    	if(!isNull(data.data)){
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
 	   _index++;
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
   	    		toastr.error('请先保存合同信息！');return
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
   	 
   	        $scope.saveClauseDelivery  = function() {// 保存交付条款
   	   	    	if($scope.contract.id==null||$scope.contract.id=='') {// 合同信息为空的处理
   	   	    		toastr.error('请先保存合同信息！');return
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
   	   	   	    		toastr.error('请先保存合同信息！');return
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
   	   	    		toastr.error('请先保存合同信息！');return
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
	   	    		toastr.error('请先保存合同信息！');return
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
   	    		toastr.error('请先保存合同信息！');return
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
	        $scope.submitPage  = function() {// 取消编辑
	        	$scope.submitOrder = {}
	        	$scope.submitOrder.serialNum = $scope.buyOrder.serialNum;
	        	$scope.submitOrder.remark = $scope.buyOrder.remark;
	        	$scope.submitOrder.status = 1;
	        	$scope.buyOrder.status = 1;
	        	orderService.save($scope.submitOrder).then(
	          		     function(data){
	          		    	toastr.success('数据保存成功！');
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
		    	   window.location.href=$rootScope.basePath+"/rest/order/downloadImportTemp";
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
		    	   	var promise = orderService.uploadExcel();
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
			    	 window.location.href=$rootScope.basePath+"/rest/order/exportOrder";
			    	 handle.unblockUI(); 
			   }
		       //********导入导出end****************//
    	 
}]);