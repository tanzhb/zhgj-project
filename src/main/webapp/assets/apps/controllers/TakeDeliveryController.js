/**
 * 
 */

angular.module('MetronicApp').controller('TakeDeliveryController',['$rootScope','$scope','$state','$http','takeDeliveryService','orderService','$location','$compile','$stateParams',
                                                                   function($rootScope,$scope,$state,$http,takeDeliveryService,orderService,$location,$compile,$stateParams) {
	 $scope.$on('$viewContentLoaded', function() {   
	    	// initialize core components
		    handle = new pageHandle();
	    	App.initAjax();
	    	handle.datePickersInit("auto bottom");
	    	if($location.path()=="/takeDeliveryAdd"){
	    		
	    		//handle.pageRepeater();
	    		//_index = 0; 
	    		//$scope.companyQualifications =[{}];
	    		//$scope.rootMateriels = [];
	    		//$scope.serialNums = [];
	    		//getDemandPlanInfo($stateParams.serialNum);
	    		
	    		//selectParentMateriel();
	    		
	    		//initOrders();
	    		initSuppliers();
	    		initWarehouse();
	    		validatorInit();
	    		loadOrderTable();
	    		if(!isNull($stateParams.serialNum)){
	    			$(".d_tip").text("编辑收货信息");
	    			takeDeliveryInfo($stateParams.serialNum,"edit");
	    		}
	    		playArrivalDateSetting();
	    		playWarehouseDateSetting();
	 		}else if($location.path()=="/takeDeliveryView"){
	 				takeDeliveryInfo($stateParams.serialNum);
	 		}else{
	 			var type = handle.getCookie("d_type");
	 			if(type=="stockIn"){
		 				//loadStockInTable();
		 				//$('#delivery_tab a:last').tab('show');
		 				$('#delivery_tab a:last').parent().addClass('active');
		 				$('#delivery_tab a:first').parent().removeClass('active');
		 				$("#tab_15_2").addClass("active");
		 				$("#tab_15_1").removeClass("active");
		 				 $("#tip").text("入库记录");
		 		}else{
		 				//loadTakeDelieryTable();
		 				$('#delivery_tab a:first').parent().addClass('active');
		 				$('#delivery_tab a:last').parent().removeClass('active');
		 				$("#tab_15_1").addClass("active");
		 				$("#tab_15_2").removeClass("active");
		 				 $("#tip").text("收货计划");
		 				//$('#delivery_tab a:first').tab('show');
		 		}
	 			loadStockInTable();
	 			loadTakeDelieryTable();
	 			
	 			
	 		}
	    	// set default layout mode
	    	$rootScope.settings.layout.pageContentWhite = true;
	        $rootScope.settings.layout.pageBodySolid = false;
	        $rootScope.settings.layout.pageSidebarClosed = false;
	       
	    	
	 });
	 
	
	 var playArrivalDateSetting = function() { 
		    
	        $("#playArrivalDate").datepicker().on('changeDate', function(ev){
	        	$("#playWarehouseDate").datepicker('setStartDate',timeStamp2ShortString(ev.date));
	        });  
	    }  
	 
	 var playWarehouseDateSetting = function() {   
		 	
	        $("#playWarehouseDate").datepicker().
	        on('changeDate', function(ev){
	        	$("#playArrivalDate").datepicker('setEndDate',timeStamp2ShortString(ev.date));
	        });  
	}  

		
		/**
		 * 加载订单数据
		 */
			var initOrders = function(){
				var promise = takeDeliveryService.initOrders();
        		promise.then(function(data){
        			$scope.orders = data.data;
        		},function(data){
        			//调用承诺接口reject();
        		});
			}
			
			/**
			 * 加载供应商数据
			 */
			var initSuppliers = function(){
			var promise = takeDeliveryService.initSuppliers();
			promise.then(function(data){
				$scope.suppliers = data.data;
			},function(data){
				//调用承诺接口reject();
			});
			}
			
			/**
			 * 加载仓库数据
			 */
			var initWarehouse = function(){
			var promise = takeDeliveryService.initWarehouse();
			promise.then(function(data){
				$scope.warehouses = data.data;
			},function(data){
				//调用承诺接口reject();
			});
			}
		
		
		
	 		/**
	 		 *加载订单物料
	 		 */
	        $scope.getOrderMateriel=function () { 
	            var sd = $scope.deliver.orderSerial;
	        	var promise = orderService.getOrderInfo(sd);
        		promise.then(function(data){
        			$scope.orderMateriels = data.orderMateriel;
        			$scope.deliver.materielCount = data.orderMateriel.length;
        		},function(data){
        			//调用承诺接口reject();
        		});
	        }
	        
	        /**
	         * 查看收货详情
	         */
	        $scope.takeDeliveryView = function(serialNum){
	        	$state.go("takeDeliveryView",{serialNum:serialNum});
	        }
	        
	        /**
	         * 查看收货详情
	         */
	        var takeDeliveryInfo = function(serialNum,type){
	        	var promise = takeDeliveryService.getTakeDeliveryInfo(serialNum);
	        	promise.then(function(data){
	        	$scope.deliver = data.data;
	        	$scope.deliver.warehouseSerial = data.data.warehouse.serialNum;
	        	$scope.deliver.warehouseName = data.data.warehouse.address;
	        	if(type="edit"){
	        		$scope.deliverTransport = data.data.deliveryTransport;
	        		$scope.orderMateriels = data.data.deliveryMateriels;
	        		for(var i in data.data.deliveryMateriels){
	        			$scope.orderMateriels[i].materiel = data.data.deliveryMateriels[i].orderMateriel.materiel;
	        			$scope.orderMateriels[i].amount = data.data.deliveryMateriels[i].orderMateriel.amount;
	        			$scope.orderMateriels[i].serialNum = data.data.deliveryMateriels[i].orderMateriel.serialNum;
	        		}
	        		$scope.takeDeliver = data.data.takeDelivery;
	        		//$scope.takeDeliver.warehouseSerial = $scope.takeDeliver.warehouse.serialNum;
	        		$scope.takeDeliver.warehouseName = $scope.takeDeliver.warehouse.address;
	        		
	        		
	        		var playWarehouseDate= $scope.deliverTransport.playWarehouseDate;
	    		    if(!isNull(playWarehouseDate)){
	    		    	$("#playWarehouseDate").datepicker('setDate',playWarehouseDate);
	    		    	$("#playArrivalDate").datepicker('setEndDate',playWarehouseDate);
	    		    }
	    		    
	    		    var playArrivalDate= $scope.deliverTransport.playArrivalDate;
	    		 	if(!isNull(playArrivalDate)){
	    		 		$("#playArrivalDate").datepicker('setDate',playArrivalDate);
	    		    	$("#playWarehouseDate").datepicker('setStartDate',playArrivalDate);
	    		    }
	        	}
	        	},function(data){
	        		//调用承诺接口reject();
	        	});
	        }
	        

	        /**
	         * 确认收货
	         */
			$scope.saveTakeDelivery = function() {
				if($('#takeDeliveryForm').valid()){
					handle.blockUI();
					var params = {};
					params.delivery = $scope.deliver;
					params.deliveryTransport = $scope.deliverTransport;
					params.takeDelivery = $scope.takeDeliver;
					params.deliveryMateriels = [];
					var param
					for(var i=0;i < $scope.orderMateriels.length;i++){
						param = {};
						param.orderMaterielSerial = $scope.orderMateriels[i].serialNum;
						param.batchNum = $scope.orderMateriels[i].batchNum;
						param.manufactureDate = $scope.orderMateriels[i].manufactureDate;
						param.deliverCount = $scope.orderMateriels[i].deliverCount;
						param.deliverRemark = $scope.orderMateriels[i].deliverRemark;
						param.acceptCount = $scope.orderMateriels[i].acceptCount;
						param.refuseCount = $scope.orderMateriels[i].deliverCount-$scope.orderMateriels[i].acceptCount;
						param.takeRemark = $scope.orderMateriels[i].takeRemark;
						params.deliveryMateriels.push(param);
					}
					
					var promise = takeDeliveryService
							.saveTakeDelivery(params);
					promise.then(function(data) {
						if(data.data == "1"){
							toastr.success("收货成功！");
							$state.go("takeDelivery");
						}else{
							toastr.error("收货失败！请联系管理员");
						}
						handle.unblockUI();
					}, function(data) {
						// 调用承诺接口reject();
						handle.unblockUI();
						toastr.error("收货失败！请联系管理员");
						console.log(data);
					});
				}
			}; 
			
			$scope.cancelTakeDelivery = function(){
				$state.go("takeDelivery");
			}
			
			$scope.getWarehouseName = function(type){
				for(var i in $scope.warehouses){
					if(type=="deliver"){
						if($scope.warehouses[i].serialNum == $scope.deliver.warehouseSerial){
							$scope.deliver.warehouseName = $scope.warehouses[i].address;
						}
					}else{
						if($scope.warehouses[i].serialNum == $scope.takeDeliver.warehouseSerial){
							$scope.takeDeliver.warehouseName = $scope.warehouses[i].address;
						}
					}
					
				}
			}
			
			/**
			 * 去编辑收货计划
			 */
			$scope.takeDeliveryEdit = function(){
				var id_count = $('#takeDeliveryTable input[name="serialNum"]:checked').length;
				if(id_count==0){
					toastr.warning("请选择您要修改的记录");
				}else if(id_count>1){
					toastr.warning("只能选择一条数据进行修改");
				}else{
					var serialNum = $('#takeDeliveryTable input[name="serialNum"]:checked').val();
					$state.go("takeDeliveryAdd",{serialNum:serialNum});
				}
			}
	        		
	        
	        /**
	         * 批量删除收货计划
	         */
	        $scope.takeDeliveryDelete = function () {debugger;
	        	var id_count = $('#takeDeliveryTable input[name="serialNum"]:checked').length;
				if(id_count==0){
					toastr.warning("请选择您要删除的记录");
					return;
				}
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
	        			table.ajax.reload(); // 重新加载datatables数据
	        			/*$state.go('company',{},{reload:true}); */
	        		},function(data){
	        			//调用承诺接口reject();
	        		});
	        		
	        	});
	        	
	        };

	        //重写indexOf方法
			function indexOf(arr, item) {
				for (var i = 0; i < arr.length; i++) {
						if (arr[i] === item)
							return i;
						else
							return -1;
				}
			}

			function unique(arr) {
			    var result = [], hash = {};
			    for (var i = 0, elem; (elem = arr[i]) != null; i++) {
			        if (!hash[elem]) {
			            result.push(elem);
			            hash[elem] = true;
			        }
			    }
			    return result.length;
			}

	       
	       /**
	        * 导出收货计划
	        */
	       $scope.exportTakeDelivery = function(){
		    	 window.location.href=$rootScope.basePath+"/rest/takeDelivery/exportTakeDelivery";
		   }

	       

	       
	       $scope.stockInView = function(serialNum){
	    	   $state.go("stockInView",{serialNum:serialNum});
	       }

	       
	       /**
	         * 批量删除入库记录
	         */
	        $scope.stockInDelete = function () {
	        	var id_count = $('#stockInTable input[name="serialNum2"]:checked').length;
				if(id_count==0){
					toastr.warning("请选择您要删除的记录");
					return;
				}
	        	handle.confirm("确定删除吗？",function(){
	        		var ids = '';
					// Iterate over all checkboxes in the table
	        		$('#stockInTable input[name="serialNum2"]').each(
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
	        		var promise = takeDeliveryService.deleteStockInInfo(ids);
	        		promise.then(function(data){
	        			toastr.success("删除成功");
	        			handle.unblockUI();
	        			//createTable(5,1,true,$scope.params);
	        			stock_table.ajax.reload(); // 重新加载datatables数据
	        			/*$state.go('company',{},{reload:true}); */
	        		},function(data){
	        			//调用承诺接口reject();
	        		});
	        		
	        	});
	        	
	        };
	         
	        $scope.stockInEdit = function(){
	        	var id_count = $('#stockInTable input[name="serialNum2"]:checked').length;
				if(id_count==0){
					toastr.warning("请选择您要修改的记录");
				}else if(id_count>1){
					toastr.warning("只能选择一条数据进行修改");
				}else{
					var serialNum = $('#stockInTable input[name="serialNum2"]:checked').val();
					$state.go("stockInAdd",{serialNum:serialNum});
				}
	        }
	       	
	        /**
		        * 初始化日期控件
		        */
		       $scope.repeatDone = function(scope){
		       		var date= scope.materiel.manufactureDate;
		    	    handle.datePickersInit();
		    	    scope.materiel.manufactureDate = date;
		    	    $("#manufactureDate"+index).datepicker('setDate',date);
		    };
	        
	        /**
		        * 导出入库记录
		        */
		    $scope.exportStockIn = function(){
			    	 window.location.href=$rootScope.basePath+"/rest/takeDelivery/exportStockIn";
			}
		    
		    
		    $('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {debugger;
		        // 获取已激活的标签页的名称
		        var activeTab = $(e.target).text(); 
		        // 获取前一个激活的标签页的名称
		       // var previousTab = $(e.relatedTarget).text(); 
		        var absurl = $location.absUrl();
		        $("#tip").text(activeTab);
		        if(activeTab=="入库记录"){
		        	handle.addCookie("d_type","stockIn",24);
		        }else{
		        	handle.addCookie("d_type","takeDeliver",24);
		        }
		     });
	       
	       
	       /***选择收货列表初始化START***/
	       var table;
	       var loadTakeDelieryTable = function() {
	                a = 0;
	                App.getViewPort().width < App.getResponsiveBreakpoint("md") ? $(".page-header").hasClass("page-header-fixed-mobile") && (a = $(".page-header").outerHeight(!0)) : $(".page-header").hasClass("navbar-fixed-top") ? a = $(".page-header").outerHeight(!0) : $("body").hasClass("page-header-fixed") && (a = 64);
	                table = $("#takeDeliveryTable").DataTable({
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
	                    pageLength: 5,//每页显示数量
	                    processing: true,//loading等待框
	                    bRetrieve : true,
//	                    serverSide: true,
	                   // ajax: "rest/takeDelivery/takeDeliveryList",//加载数据中
	                    ajax :{ "url":$rootScope.basePath
	  						+ "/rest/takeDelivery/takeDeliveryList",// 加载数据中user表数据    
	  						"contentType": "application/json",
	  					    "type": "POST",
	  					    "data": function ( d ) {
	  					      return JSON.stringify( d );
	  					    }},
	                    "aoColumns": [
	                                  { mData: 'takeDelivery.serialNum' },
	                                  { mData: 'takeDelivery.takeDeliverNum' },
	                                  { mData: 'orderNum' },
	                                  { mData: 'shipper' },
	                                  { mData: 'materielCount' },
	                                  { mData: 'packageCount' },
	                                  { mData: 'packageType' },
	                                  { mData: 'warehouse.address' },
	                                  { mData: 'deliverDate' },
	                                  { mData: 'deliveryTransport.transportType' },
	                                  { mData: 'takeDelivery.warehouse.address' },
	                                  { mData: 'takeDelivery.remark' },
	                                  { mData: 'status' }
	                            ],
	                   'aoColumnDefs' : [ {
	    							'targets' : 0,
	    							'searchable' : false,
	    							'orderable' : false,
	    							'className' : 'dt-body-center',
	    							'render' : function(data,
	    									type, row, meta) {
	  	  	  								return '<input  type="checkbox" id='+data+'   name="serialNum" value="'
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
	    							'targets' : 7,
	    							'render' : function(data,
	    									type, row, meta) {
	    									if(data!=undefined){
	  										return data;
	  									}
	  	  								return '';
	  	
	    							}
	    						},{
	    							'targets' : 9,
	    							'render' : function(data,
	    									type, row, meta) {
	    									if(data!=undefined){
	  										return data;
	  									}
	  	  								return '';
	  	
	    							}
	    						},{
	    							'targets' : 10,
	    							'render' : function(data,
	    									type, row, meta) {
	    									if(data!=undefined){
	  										return data;
	  									}
	  	  								return '';
	  	
	    							}
	    						},{
	    							'targets' : 11,
	    							'render' : function(data,
	    									type, row, meta) {
	    										if(data!=undefined){
	    											return data;
	    										}
	  	  	  								return '';
	  	
	    							}
	    						},{
	    							'targets' : 12,
	    							'searchable' : false,
	    							'orderable' : false,
	    							'className' : 'dt-body-center',
	    							'render' : function(data,
	    									type, row, meta) {
	    									if(data=="1"){
	    										return '<span  class="label label-sm label-warning ng-scope">未收货</span>';
	    									}else if(data=="2"){
	    										return '<span  class="label label-sm label-warning ng-scope">待检验</span>';
	    									}else{
	    										return data;
	    									}
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
	            };
	            /***收货列表初始化END***/
	            
	         
	            
	            var validatorInit= function(){
	       		 
	       		 if ($.validator) {
	       	           $.validator.prototype.elements = function () {
	       	               var validator = this,
	       	                 rulesCache = {};
	       	 
	       	               // select all valid inputs inside the form (no submit or reset buttons)
	       	               return $(this.currentForm)
	       	               .find("input, select, textarea")
	       	               .not(":submit, :reset, :image, [disabled]")
	       	               .not(this.settings.ignore)
	       	               .filter(function () {
	       	                   if (!this.name && validator.settings.debug && window.console) {
	       	                       console.error("%o has no name assigned", this);
	       	                   }
	       	                   //注释这行代码
	       	                   // select only the first element for each name, and only those with rules specified
	       	                   //if ( this.name in rulesCache || !validator.objectLength($(this).rules()) ) {
	       	                   //    return false;
	       	                   //}
	       	                   rulesCache[this.name] = true;
	       	                   return true;
	       	               });
	       	           }
	       	    }
	       		 
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
	  						$('input[name="serialNum"]', rows).prop(
	  								'checked', this.checked);
	  					});
	  	
	  			// Handle click on checkbox to set state of "Select
	  			// all" control
	  			$('#takeDeliveryTable tbody')
	  					.on(
	  							'change',
	  							'input[name="serialNum"]',
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
	  	 
	  			jQuery.validator.addMethod("deliverNumCheck", function (value, element) {
	  				
	  			    return this.optional(element) || Number($(element).data("ordercount"))-value >= 0;
	  			}, "发货数量不能超过订单数量");
	  			
	  			jQuery.validator.addMethod("acceptNumCheck", function (value, element) {
	  				$(element).removeData();
	  				return this.optional(element) || Number($(element).data("delivercount")) == NaN?false:(Number($(element).data("delivercount"))-value >= 0);
	  			}, "实收数量不能超过发货数量");
	  		
	  						
	  			// 页面加载完成后调用，验证输入框
	  				$scope.$watch('$viewContentLoaded', function() {  
	  								var e = $("#takeDeliveryForm"),
	  						        r = $(".alert-danger", e),
	  						        i = $(".alert-success", e);
	  						        e.validate({
	  						            errorElement: "span",
	  						            errorClass: "help-block help-block-error",
	  						            focusInvalid: !1,
	  						            ignore: "",
	  						            messages: {
	  						            	takeDeliverNum:{required:"收货单号不能为空！"},
	  						            	deliverNum:{required:"发货单号不能为空！"},
	  						            	orderSerial:{required:"采购订单号不能为空！"},
	  						            	supplyComId:{required:"供应商不能为空！"},
	  						            	shipper:{required:"发布方不能为空！"},
	  						            	receiver:{required:"收货方不能为空！"},
	  						            	maker:{required:"制单人不能为空！"},
	  						            	makeDate:{required:"制单日期不能为空！"},
	  						            	approval:{required:"审批人不能为空！"},
	  						            	approvalDate:{required:"审批日期不能为空！"},
	  						            	dWarehouseSerial:{required:"发货仓库不能为空！"},
	  						            	deliverDate:{required:"发货日期不能为空！"},
	  						            	materielCount:{required:"物料数不能为空！"},
	  						            	packageCount:{required:"包装件数不能为空！",digits:"包装件数必须为数字！"},
	  						            	packageType:{required:"包装类型不能为空！"},
	  						            	packageSpecifications:{required:"包装规格不能为空！"},
	  						            	materielWeight:{required:"物料重量不能为空！"},
	  						            	serviceMoney:{required:"服务费不能为空！",digits:"包装件数必须为数字！"},
	  						            	deliverer:{required:"发货人不能为空！"},
	  						            	dContactNum:{required:"联系电话不能为空！"},
	  						            	transportType:{required:"运输方式不能为空！"},
	  						            	transport:{required:"运输方不能为空！"},
	  						            	port:{required:"港口不能为空！"},
	  						            	shipNumber:{required:"船号不能为空！"},
	  						            	playArrivalDate:{required:"预计到港日期不能为空！"},
	  						            	playWarehouseDate:{required:"预计到库日期不能为空！"},
	  						            	dtContact:{required:"联系人不能为空！"},
	  						            	dtContactNum:{required:"联系电话不能为空！"},
	  						            	warehouseSerial:{required:"收货仓库不能为空！"},
	  						            	takeDeliverDate:{required:"收货日期不能为空！"},
	  						            	tdReceiver:{required:"收货人不能为空！"},
	  						            	tdContactNum:{required:"联系电话不能为空！"},
	  						            	batchNum:{required:"批次号不能为空！"},
	  						            	manufactureDate:{required:"生产日期不能为空！"},
	  						            	deliverCount:{required:"发货数量不能为空！",digits:"发货数量必须为数字！"},
	  						            	acceptCount:{required:"实收数量不能为空！",digits:"实收数量必须为数字！"}
	  						            },
	  						            rules: {
	  						            	takeDeliverNum: {
	  						                    required: !0
	  						                },
	  						                deliverNum: {
	  						                    required: !0
	  						                },
	  						                orderSerial: {
	  						                	required: !0
	  						                },
	  						                supplyComId: {
	  						                	required: !0
	  						                },
	  						                shipper: {
	  						                	required: !0
	  						                },
	  						                receiver: {
	  						                	required: !0
	  						                },
	  						                maker: {
	  						                	required: !0
	  						                },
	  						                makeDate: {
	  						                	required: !0
	  						                },
	  						                approval: {
	  						                	required: !0
	  						                },
	  						                approvalDate: {
	  						                	required: !0
	  						                },
	  						                dWarehouseSerial: {
	  						                	required: !0
	  						                },
	  						                deliverDate: {
	  						                	required: !0
	  						                },
	  						                materielCount: {
	  						                	required: !0
	  						                },
	  						                packageCount: {
	  						                	required: !0,
	  						                	digits: !0
	  						                },
	  						                packageType: {
	  						                	required: !0
	  						                },
	  						                packageSpecifications: {
	  						                	required: !0
	  						                },
	  						                materielWeight: {
	  						                	required: !0
	  						                },
	  						                serviceMoney: {
	  						                	required: !0,
	  						                	digits: !0
	  						                },
	  						                deliverer: {
	  						                	required: !0
	  						                },
	  						                dContactNum: {
	  						                	required: !0,
	  						                	isPhone: !0
	  						                },
	  						                transportType: {
	  						                	required: !0
	  						                },
	  						                transport: {
	  						                	required: !0
	  						                },
	  						                port: {
	  						                	required: !0
	  						                },
	  						                shipNumber: {
	  						                	required: !0
	  						                },
	  						                playArrivalDate: {
	  						                	required: !0
	  						                },
	  						                playWarehouseDate: {
	  						                	required: !0
	  						                },
	  						                dtContact: {
	  						                	required: !0
	  						                },
	  						                dtContactNum: {
	  						                	required: !0,
	  						                	isPhone: !0
	  						                },
	  						                warehouseSerial: {
	  						                	required: !0
	  						                },
	  						                takeDeliverDate: {
	  						                	required: !0
	  						                },
	  						                tdReceiver: {
	  						                	required: !0
	  						                },
	  						                batchNum: {
	  						                	required: !0
	  						                },
	  						                manufactureDate: {
	  						                	required: !0
	  						                },
	  						                deliverCount: {
	  						                	required: !0,
	  						                	digits: !0,
	  						                	deliverNumCheck:!0
	  						                },
	  						                acceptCount: {
	  						                	required: !0,
	  						                	digits:!0,
	  						                	acceptNumCheck:!0
	  						                },
	  						                tdContactNum: {
	  						                	required: !0,
	  						                	isPhone: !0
	  						                }
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
	  						            }
	  						        })   
	  			}); 					
	  				
	  				
	  				
	  				
	  			    /***入库列表初始化START***/
	  		       var stock_table;
	  		       var loadStockInTable = function() {
	  		                a = 0;
	  		                App.getViewPort().width < App.getResponsiveBreakpoint("md") ? $(".page-header").hasClass("page-header-fixed-mobile") && (a = $(".page-header").outerHeight(!0)) : $(".page-header").hasClass("navbar-fixed-top") ? a = $(".page-header").outerHeight(!0) : $("body").hasClass("page-header-fixed") && (a = 64);
	  		              stock_table = $("#stockInTable").DataTable({
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
	  		                    pageLength: 5,//每页显示数量
	  		                    processing: true,//loading等待框
	  		                    bRetrieve : true,
//	  		                    serverSide: true,
	  		                   // ajax: "rest/takeDelivery/takeDeliveryList",//加载数据中
	  		                    ajax :{ "url":$rootScope.basePath
	  		  						+ "/rest/takeDelivery/stockInList",// 加载数据中user表数据    
	  		  						"contentType": "application/json",
	  		  					    "type": "POST",
	  		  					    "data": function ( d ) {
	  		  					      return JSON.stringify( d );
	  		  					    }},
	  		                    "aoColumns": [
	  		                                  { mData: 'stockInOutRecord.serialNum' },
	  		                                  { mData: 'stockInOutRecord.inOutNum' },
	  		                                  { mData: 'stockInOutRecord.inOutNum' },
	  		                                  { mData: 'orderMateriel.materiel.materielName'},
	  		                                  { mData: 'orderMateriel.materiel.specifications'},
	  		                                  { mData: 'stockInOutRecord.stockDate' },
	  		                                  { mData: 'stockCount' },
	  		                                  { mData: 'batchNum' },
	  		                                  { mData: 'delivery.supplyName' },
	  		                                  { mData: 'stockInOutRecord.order.orderNum' }
	  		                            ],
	  		                   'aoColumnDefs' : [ {
	  		    							'targets' : 0,
	  		    							'searchable' : false,
	  		    							'orderable' : false,
	  		    							'className' : 'dt-body-center',
	  		    							'render' : function(data,
	  		    									type, row, meta) {
	  		  	  	  								return '<input  type="checkbox" id='+data+'   name="serialNum2" value="'
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
	  		    							'targets' : 1,
	  		    							'render' : function(data,
	  		    									type, row, meta) {
	  		    										
	  		  	  	  								return '<a href="javascript:void(0);" ng-click="stockInView(\''+row.stockInOutRecord.serialNum+'\')">'+data+'</a>';
	  		  	
	  		    							},
	  		    							"createdCell": function (td, cellData, rowData, row, col) {
	  		    								 $compile(td)($scope);
	  		    						       }
	  		    						},{
	  		    							'targets' : 2,
	  		    							'render' : function(data,
	  		    									type, row, meta) {
	  		    									if(data==undefined){
	  		  										return "";
	  		  									}
	  		  	  								return "";
	  		  	
	  		    							}
	  		    						},{
	  		    							'targets' : 7,
	  		    							'render' : function(data,
	  		    									type, row, meta) {
	  		    								if(data==undefined){
	  		    									return "";
	  		    								}
	  		    								return data;
	  		    								
	  		    							}
	  		    						},{
	  		    							'targets' : 9,
	  		    							'render' : function(data,
	  		    									type, row, meta) {
	  		    									if(data==undefined){
	  		  										return "";
	  		  									}
	  		  	  								return data;
	  		  	
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
	  		            };
	  		       /***选择入库列表初始化END***/
	  		            		
	  		            // 添加checkbox功能***************************************
	  		  			// Handle click on "Select all" control
	  		  			$('#example-select-all-2').on(
	  		  					'click',
	  		  					function() {
	  		  						// Check/uncheck all checkboxes in the
	  		  						// table
	  		  						var rows = stock_table.rows({
	  		  							'search' : 'applied'
	  		  						}).nodes();
	  		  						$('input[name="serialNum2"]', rows).prop(
	  		  								'checked', this.checked);
	  		  					});
	  		  	
	  		  			// Handle click on checkbox to set state of "Select
	  		  			// all" control
	  		  			$('#stockInTable tbody')
	  		  					.on(
	  		  							'change',
	  		  							'input[name="serialNum2"]',
	  		  							function() {
	  		  								// If checkbox is not checked
	  		  								if (!this.checked) {
	  		  									var el = $(
	  		  											'#example-select-all-2')
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
	       
	       
	  		  	  var order_table;
	  		      var tableAjaxUrl = "rest/order/findOrderList?type=buy";
	  		      var loadOrderTable = function() {
	  		              a = 0;
	  		              App.getViewPort().width < App.getResponsiveBreakpoint("md") ? $(".page-header").hasClass("page-header-fixed-mobile") && (a = $(".page-header").outerHeight(!0)) : $(".page-header").hasClass("navbar-fixed-top") ? a = $(".page-header").outerHeight(!0) : $("body").hasClass("page-header-fixed") && (a = 64);
	  		            order_table = $("#buyOrder").DataTable({
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
	  		                                { mData: 'supplyComId' },
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
	  		  									type, row, meta) {
	  		  								return '<input type="radio" id="'+data+'" data-num="'+row.orderNum+'" ng-click="getBuyOrderInfo_(\''+data+'\')" name="selecrOrderSerial" value="'
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
	  		      };
	  		      
	  		$scope.confirmSelect = function(){
	  			var id_count = $('#buyOrder input[name="selecrOrderSerial"]:checked').length;
				if(id_count==0){
					toastr.warning("请选择采购订单");
				}else{
					//var serialNum = $('#buyOrder input[name="selecrOrderSerial"]:checked').val();
					$scope.deliver.orderSerial = $('#buyOrder input[name="selecrOrderSerial"]:checked').val();
					$scope.deliver.orderNum = $('#buyOrder input[name="selecrOrderSerial"]:checked').data("num");
					$scope.getOrderMateriel();
					$("#buyOrderInfo").modal('hide'); 
				}
				
	  		}      

}]); 