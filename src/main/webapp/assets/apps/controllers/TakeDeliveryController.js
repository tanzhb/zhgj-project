/**
 * 
 */

angular.module('MetronicApp').controller('TakeDeliveryController',['$rootScope','$scope','$state','$http','takeDeliveryService','orderService','$location','$compile','$stateParams',function($rootScope,$scope,$state,$http,takeDeliveryService,orderService,$location,$compile,$stateParams) {
	 $scope.$on('$viewContentLoaded', function() {   
	    	// initialize core components
		    handle = new pageHandle();
	    	App.initAjax();
	    	handle.datePickersInit();
	    	if($location.path()=="/takeDeliveryAdd"){
	    		//handle.pageRepeater();
	    		//_index = 0; 
	    		//$scope.companyQualifications =[{}];
	    		//$scope.rootMateriels = [];
	    		//$scope.serialNums = [];
	    		//getDemandPlanInfo($stateParams.serialNum);
	    		
	    		//selectParentMateriel();
	    		
	    		initOrders();
	    		initSuppliers();
	    		initWarehouse();
	    		validatorInit();
	    		if(!isNull($stateParams.serialNum)){
	    			takeDeliveryInfo($stateParams.serialNum,"edit");
	    		}
	 		}else if($location.path()=="/takeDeliveryView"){
	 				takeDeliveryInfo($stateParams.serialNum);
	 		}else{
	 			loadTakeDelieryTable();
	 		}
	    	// set default layout mode
	    	$rootScope.settings.layout.pageContentWhite = true;
	        $rootScope.settings.layout.pageBodySolid = false;
	        $rootScope.settings.layout.pageSidebarClosed = false;
	       
	    	
	 });
	 
	 /***选择物料列表初始化START***/
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
//                  serverSide: true,
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
  							'searchable' : false,
  							'orderable' : false,
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
          
         
          /***选择物料列表初始化END***/
          
       
          
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
				var id_count = $('#takeDeliveryTable input[type="checkbox"]:checked').length;
				if(id_count==0){
					toastr.warning("请选择您要修改的记录");
				}else if(id_count>1){
					toastr.warning("只能选择一条数据进行修改");
				}else{
					var serialNum = $('#takeDeliveryTable input[type="checkbox"]:checked').val();
					$state.go("takeDeliveryAdd",{serialNum:serialNum});
				}
			}
	        		
	        
	        /**
	         * 批量删除收货计划
	         */
	        $scope.takeDeliveryDelete = function () {
	        	var id_count = $('#takeDeliveryTable input[type="checkbox"]:checked').length;
				if(id_count==0){
					toastr.warning("请选择您要删除的记录");
					return;
				}
	        	handle.confirm("确定删除吗？",function(){
	        		var ids = '';
					// Iterate over all checkboxes in the table
	        		$('#takeDeliveryTable input[type="checkbox"]').each(
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
	        
	        /**
	         * 获取需求计划信息
	         */
	        var getDemandPlanInfo = function(serialNum,type){
	    	   if(!handle.isNull(serialNum)){
	    			 var promise = demandPlanService.demandPlanInfo(serialNum);
	 	        	promise.then(function(data){
	 	        		if(!handle.isNull(data.data.demandPlan)){
		 	        		$scope.demandPlan = data.data.demandPlan;
		 	        	}
	 	        		if(!handle.isNull(data.data.demandPlanMateriels)){
	 	        			$scope.rootMateriels = data.data.demandPlanMateriels;
	 	        		}
	 	        		
	 	        		
	 	        		if(!isNull($stateParams.serialNum)){
	 		    			for(var i=0;i<$scope.rootMateriels.length;i++){
	 			        			$scope["demandPlanMaterielEdit"+i] = true;
	 								$scope["demandPlanMaterielView"+i] = true;
	 			        	}
	 		    		}
	 	        		$scope.copyMateriels = angular.copy($scope.rootMateriels);
	 	            },function(data){
	 	               //调用承诺接口reject();
	 	            });
	    		 }
		    }
	        
	        /**
	         * 获取需求计划基本信息
	         */
	        var viewDemandPlan = function(serialNum){
	        	if(!handle.isNull(serialNum)){
	        		var promise = demandPlanService.viewDemandPlan(serialNum);
	        		promise.then(function(data){
	        			if(!handle.isNull(data.data)){
	        				$scope.demandPlan = data.data;
	        			}
	        		},function(data){
	        			//调用承诺接口reject();
	        		});
	        	}
	        }
	        
	        /**
	         * 需求计划物料信息验证
	         */
	        function demandPlanMaterielValid(index){
		    	   var flag = true;
		    		if(isNull($("#amount"+index).val())){
		    			   handle.paramCheck("amount"+index,"数量不能为空！");
		    			   flag = false;
		    		}else if(!handle.isInteger($("#amount"+index).val())||Number($("#amount"+index).val())<=0){
		    			   handle.paramCheck("amount"+index,"数量只能是正整数！");
		    			   flag = false;
		    		}
		    		if(isNull($("#deliveryDate"+index).val())){
		    			   handle.paramCheck("deliveryDate"+index,"交付日期不能为空！",true);
		    			   flag = false;
		    		}
		    		if(isNull($("#deliveryAddress"+index).val())){
		    			   handle.paramCheck("deliveryAddress"+index,"交付地点不能为空！");
		    			   flag = false;
		    		}
		    	   
		    	   return flag;
		    }
	        

			/**
			 * 保存需求计划物料信息
			 */
			$scope.saveDemandPlanMateriel = function(materiel,index) {
				if(!demandPlanMaterielValid(index)){
					return;
				}
				var demandPlanMateriel = {};
				handle.blockUI();
				demandPlanMateriel.createTime = null;
				demandPlanMateriel.updateTime = null;
				demandPlanMateriel.demandPlanSerial = $scope.demandPlan.serialNum;
				if(isNull(materiel.materielSerial)){ //如果供应物料id不存在，则为新增物料，否则为编辑需求物料
					demandPlanMateriel.supplyMaterielSerial = materiel.supplyMaterielSerial;
					demandPlanMateriel.materielSerial = materiel.serialNum;
				}else{
					demandPlanMateriel.serialNum = materiel.serialNum;
					demandPlanMateriel.supplyMaterielSerial = materiel.supplyMaterielSerial;
					demandPlanMateriel.materielSerial = materiel.materielSerial;
				}
				demandPlanMateriel.deliveryDate = materiel.deliveryDate;
				demandPlanMateriel.deliveryAddress = materiel.deliveryAddress;
				demandPlanMateriel.amount = materiel.amount;
				demandPlanMateriel.materielNum = materiel.materielNum;
				demandPlanMateriel.materielName = materiel.materielName;
				demandPlanMateriel.specifications = materiel.specifications;
				demandPlanMateriel.unit = materiel.unit;
				demandPlanMateriel.supplyMateriels = materiel.supplyMateriels;
				
				var promise = demandPlanService
				.saveDemandPlanMateriel(demandPlanMateriel);
				promise.then(function(data) {
					if (!handle.isNull(data.data)) {
						$(".modal-backdrop").remove();
						toastr.success("保存成功");
						handle.unblockUI();
						//var company = data.data;
						// $state.go('companyAdd',company,{reload:true});
						$scope.rootMateriels[index] = data.data;
						$scope.copyMateriels[index] = data.data;
						console.log(data.data);
						$scope["demandPlanMaterielEdit"+index] = true;
						$scope["demandPlanMaterielView"+index] = true;
						$(".alert-danger").hide();
						// $stateParams.comId =
						// company.comId;
						// $location.search('comId',company.comId);
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
	        $scope.cancelDemandPlanMateriel=function (materiel,index) {
	        	//.show_materiels = false;
	        	for(var i=0;i<$scope.copyMateriels.length;i++){
	        		if(materiel.serialNum == $scope.copyMateriels[i].serialNum && !isNull(materiel.supplyMaterielSerial)){ //如果是以保存的物料，回滚
	        			$scope.rootMateriels[$scope.rootMateriels.indexOf(materiel)] = $scope.copyMateriels[i];
	        			$scope["demandPlanMaterielEdit"+index] = true;
						$scope["demandPlanMaterielView"+index] = true;
						break;
	        		}
	        		
	        		if(i==$scope.copyMateriels.length-1){ //如果是已选择但未保存的物料，清空
	        			$scope.rootMateriels[$scope.rootMateriels.indexOf(materiel)].deliveryDate = "";
	        			$scope.rootMateriels[$scope.rootMateriels.indexOf(materiel)].deliveryAddress = "";
	        			$scope.rootMateriels[$scope.rootMateriels.indexOf(materiel)].amount = "";
	        		}
	        	}
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
	        
	        /**
	         * 编辑需求计划物料
	         */
	        $scope.editDemandPlanMateriel=function (materiel) {
	        	//.show_materiels = false;
	        	for(var i=0;i<$scope.rootMateriels.length;i++){
	        		if(materiel.serialNum == $scope.rootMateriels[i].serialNum){
	        			$scope["demandPlanMaterielEdit"+i] = false;
	        			$scope["demandPlanMaterielView"+i] = false;
	        		}
	        	}
	        	
	        };  
	        
	        
	        /**
	         * 删除需求计划物料
	         */
	        $scope.deleteDemandPlanMateriel=function (materiel) {
	        	handle.confirm("确定删除吗？",function(){
	        		handle.blockUI();
	        		if($scope.rootMateriels.length > 0){
	        			for(var i=0;i<$scope.rootMateriels.length;i++){
	        				if(materiel == $scope.rootMateriels[i]){
	        					$scope.rootMateriels.splice(i,1);
	        				}
	        			}
	        		}
	        		if(!isNull(materiel.supplyMaterielSerial)){
	        			var promise = demandPlanService.deleteDemandPlanMateriel(materiel.serialNum);
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
	        
  
	        
	        /**
	         * 搜索
	         */
	        $scope.searchDemandPlanCalendar=function () {
	        	//createTable(5,1,false,params);
	        	$scope.demandPlanMateriel = {};
	        	$scope.demandPlanMateriel.startTime = $scope.startTime;
	        	$scope.demandPlanMateriel.endTime = $scope.endTime;
	        	d_table.settings()[0].ajax.data.params =  JSON.stringify($scope.demandPlanMateriel);
	        	d_table.ajax.reload();
	        };  
	        
	        /**
	         * 重置搜索条件
	         */
	        $scope.resetSearchForm = function (){
	        	$scope.startTime=null;
	        	$scope.endTime=null;
	        };
	        
	        /**
	         * 创建需求计划列表
	         */
	       function createTable(pageSize,pageIndex,init,params){
	    	 //初始化表格数据
	    	   handle.blockUI();
		    	var promise = demandPlanService.createTable(pageSize,pageIndex,params);
		    	promise.then(function(data){
		    			$scope.demandPlans = data.data.result;
		    			data.data.params=params;
		    			handle.createPage("#simple",data.data,"rest/demandPlan/demandPlanList",createTable,init);
		            },function(data){
		               //调用承诺接口reject();
		         });
	       }
	       
	      
	       
	       
	       

	       
	       /**
	        * 需求物料初始化日期控件
	        */
	       $scope.repeatDone = function(scope){
	       		var date= scope.materiel.manufactureDate;
	    	    handle.datePickersInit();
	    	    scope.materiel.manufactureDate = date;
	       };
	       
	       
	       /**
	        * 显示编辑、删除操作
	        */
	       $scope.showOperation = function(type,index){
	    	   var call = "operation_c"+index;
	    	   var call2 = "operation_d"+index;
	    	   if(type=='finance'){
	    		   call =  "operation_f"+index;
	    	   }
	    	   $scope[call] = true;
	    	   $scope[call2] = true;
	       };
	       
	       /**
	        * 隐藏编辑、删除操作
	        */
	       $scope.hideOperation = function(type,index){
	    	   var call = "operation_c"+index;
	    	   var call2 = "operation_d"+index;
	    	   if(type=='finance'){
	    		   call =  "operation_f"+index;
	    	   }
	    	   $scope[call]= false;
	    	   $scope[call2]= false;
	       };
	       	
	       
	       /**
	        * 下载EXCEL模板
	        */
	       $scope.downloadImportTemp = function(){
	    	   window.location.href=$rootScope.basePath+"/rest/fileOperate/downloadImportTemp?tempName=takeDelivery&fileName="+encodeURI(encodeURI('收货导入模板'));
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
	    	   	var promise = demandPlanService.uploadExcel();
       			promise.then(function(data){
       				handle.unblockUI(); 
       				if(data.data.data=="success"){
       					toastr.success("导入成功");
       					createTable(5,1,true,$scope.params);
       				}else{
       					toastr.error(data.data.data);
       				}
       				$('#import').modal('hide'); 
	            },function(data){
	               //调用承诺接口reject();
	            	handle.unblockUI(); 
	            	toastr.error("操作失败");
	            	$('#import').modal('hide'); 
	            });
	    	   
	       }
	       
	       /**
	        * 导出收货计划
	        */
	       $scope.exportTakeDelivery = function(){
		    	 window.location.href=$rootScope.basePath+"/rest/takeDelivery/exportTakeDelivery";
		   }

	       
	       //关闭modal清除文件
	       $('#import').on('hide.bs.modal', function (e) { 
	    	   $("#resetFile").trigger("click");
	       })

	         
	       

	       
	       

}]); 

/*var changeSelectValue = function (value,obj){
	if($("#"+obj).data("checked") == false){
		$("#"+obj).val(value.value);
	}else{
		for(var i=0;i<$scope.serialNums.length;i++){
			if($scope.serialNums.serialNum==obj){
				$scope.serialNums[i].materiel.supplySerial = value.value;
			}
		}
	}

}*/
