/* Setup general page controller */
angular.module('MetronicApp').controller('saleOrderController', ['$rootScope', '$scope', 'settings','saleOrderService',
    '$state',"$stateParams",'$compile','$location','materielService', function($rootScope, $scope, settings,saleOrderService,$state,$stateParams,$compile,$location,materielService) {
    $scope.$on('$viewContentLoaded', function() {   
    	// initialize core components
    	App.initAjax();
    	handle = new pageHandle();
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
            		$scope.orderMateriel={};
            	}
            	
            	if($stateParams.view==1){//切换为查看
            		$scope.saleOrderInput = true;
    		    	$scope.saleOrderShow = true;
       		    	$scope.opration = '查看';
    		    }
            	
            	validateInit();//加载表单验证控件 	 
            
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
          		    	$scope.orderMateriel=data.orderMateriel;
          		     },
          		     function(error){
          		         $scope.error = error;
          		     }
          		 );
        	
        }; 
        
        
        /***************订单物料操作 start****************/
        
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
//                     serverSide: true,
                     ajax: "rest/materiel/findMaterielList?isLatestVersion=1",//加载数据中
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
         	  								return '<input type="radio" id="'+ row.serialNum +'" name="serialNum" value="'
       										+ $('<div/>')
       												.text(
       														row.supplyMateriels[0].serialNum)
       												.html()
       										+ '">';

         								}else{
         	  								return '<input type="checkbox" id="'+ row.serialNum +'" name="serialNum[]" value="'
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
     								var bomIcon='';//bom图标
     								if(row.isBOM==1){
     									bomIcon = '<span class="label label-sm label-success">B</span> '
     								}
     								return bomIcon + data;
     							}

     						},{
     							'targets' : 5,
     							'render' : function(data,
     									type, row, meta) {
     								if(data.length>0){
     									var select='<select class="form-control" onchange="changeSelectValue(this,\''+row.serialNum+'\')">'
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
             };

             
            
   			
   			
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
    			if(!isNull($scope.saleOrder)&&!isNull($scope.saleOrder.serialNum)){
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
        			//调用承诺接口reject();
        		});
    			return;
    		}
    		var id_count = table.$('input[type="checkbox"]:checked').length;
			if(id_count==0){
				toastr.warning("请选择物料");
				return;
			}
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
        		handle.blockUI();
        		var promise = materielService.chooseMateriels(ids);
        		promise.then(function(data){
        			toastr.success("添加成功！");
        			handle.unblockUI();
        			if($scope.orderMateriel.length==0){
        				for(var i = 0;i < data.data.length;i++){//data.data为选择的供应物料
        					$scope.tempMateriel = {};
        					$scope.tempMateriel.materiel = (data.data)[i].materiel;
        					$scope.tempMateriel.orderSerial = $scope.saleOrder.serialNum;
        					$scope.tempMateriel.materielSerial = (data.data)[i].materiel.serialNum;
        					$scope.tempMateriel.supplyMaterielSerial = (data.data)[i].serialNum;
        					$scope.tempMateriel.supplyMateriel = (data.data)[i];
        					$scope.orderMateriel.push($scope.tempMateriel);
        					$scope["orderMaterielInput"+i] = false;
        					$scope["orderMaterielShow"+i] = false;
        				}
        			}else{
        				for(var i = 0;i < data.data.length;i++){//data.data为选择的供应物料
        					$scope.tempMateriel = {};
        					$scope.tempMateriel.materiel = (data.data)[i].materiel;
        					$scope.tempMateriel.orderSerial = $scope.saleOrder.serialNum;
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
        			//调用承诺接口reject();
        		});
    	}
    	
    	 $('#basicMaterielInfo').on('hide.bs.modal', function (e) { 
    		 clearChecked();
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
	    	   $scope[call] = true;
	       };
	       
	       /**
	        * 隐藏编辑、删除操作
	        */
	       $scope.hideOperation = function(type,index){
	    	   if(type=='orderMateriel'){
	    		   call =  "operation_o"+index;
	    	   }
	    	   $scope[call]= false;
	       };
    	 
    	 /**
			 * 保存销售订单物料信息
			 */
			$scope.saveOrderMateriel = function(orderMateriel,index) {
/*				var orderMateriel = {};
				orderMateriel.deliveryAddress = supplyMateriel.deliveryAddress;
				orderMateriel.deliveryDate = supplyMateriel.deliveryDate;
				orderMateriel.lastDeliveryDate = supplyMateriel.lastDeliveryDate;
				orderMateriel.materielSerial = supplyMateriel.materielSerial;
				orderMateriel.orderSerial = supplyMateriel.orderSerial;
				orderMateriel.orderUnitPrice = supplyMateriel.orderUnitPrice;
				orderMateriel.supplyComId = supplyMateriel.supplyComId;
				orderMateriel.supplyMaterielSerial = supplyMateriel.supplyMaterielSerial;*/
						
				delete orderMateriel.materiel;
				delete orderMateriel.supplyMateriel;
				delete orderMateriel.supply;
				
				var promise = saleOrderService
				.saveOrderMateriel(orderMateriel);
				promise.then(function(data) {
					if (!handle.isNull(data.data)) {
						$(".modal-backdrop").remove();
						toastr.success("保存成功");
						handle.unblockUI();
						//var company = data.data;
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
	        	//.show_materiels = false;
	        	$scope["orderMaterielInput"+index] = true;
				$scope["orderMaterielShow"+index] = true;
	        	for(var i=0;i<$scope.copyMateriels.length;i++){
	        		if(materiel.serialNum == $scope.copyMateriels[i].serialNum && !isNull(materiel.supplyMaterielSerial)){ //如果是以保存的物料，回滚
	        			$scope.orderMateriel[$scope.orderMateriel.indexOf(materiel)] = $scope.copyMateriels[i];
						break;
	        		}
	        		
	        		if(i==$scope.copyMateriels.length-1){ //如果是已选择但未保存的物料，清空
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
	        	//.show_materiels = false;
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
	        			var promise = saleOrderService.deleteOrderMateriel(materiel.serialNum);
		        		promise.then(function(data){
		        			if(data.data == "1"){
		        				toastr.success("删除成功");
			        			handle.unblockUI(); 
		        			}else{
		        				toastr.error("删除失败！请联系管理员");
				            	console.log(data);
		        			}
		        			
		 	            },function(data){
		 	               //调用承诺接口reject();
		 	            	toastr.error("删除失败！请联系管理员");
			            	console.log(data);
		 	            });
	        		}
	        	});
			   
	        };
    	 /***************订单物料操作 end****************/
    	 
    	 
    	 
    	 
    	 
}]);

var changeSelectValue = function (value,obj){
	 $('#'+obj).val(value.value);
}