/**
 * 
 */

angular.module('MetronicApp').controller('StockOutController',['$rootScope','$scope','$state','$http','takeDeliveryService','$location','$compile','$stateParams',function($rootScope,$scope,$state,$http,takeDeliveryService,$location,$compile,$stateParams) {
	 $scope.$on('$viewContentLoaded', function() {   
	    	// initialize core components
		    handle = new pageHandle();
	    	App.initAjax();
	    	handle.datePickersInit();
	    	if($location.path()=="/stockOutAdd"||$location.path()=="/stockOut"){
	    		initWarehouse();
	    		handle.validatorInit();
	    		//initTakeDelviery();
	    		loadDelieryTable();
	    		if(!isNull($stateParams.serialNum)){
	    			$(".s_tip").text("编辑出库信息");
	    			stockOutInfo($stateParams.serialNum);
	    		}
	    	}else{
	    			stockOutInfo($stateParams.serialNum);
	    	}
	    		
	    	// set default layout mode
	    	$rootScope.settings.layout.pageContentWhite = true;
	        $rootScope.settings.layout.pageBodySolid = false;
	        $rootScope.settings.layout.pageSidebarClosed = false;
	       
	    	
	 });
	 
	 

	 	
	 	var countWarehouseAndPosition = function() {
	 		if($scope.takeDeliveryMateriels != undefined){
	    		var w_arr = [];
	    		var p_arr = [];
	    		for(var i in $scope.takeDeliveryMateriels){
	    			if(!isNull($scope.takeDeliveryMateriels[i].warehouseSerial)){
	    				w_arr.push($scope.takeDeliveryMateriels[i].warehouseSerial);
	    			}
	    			if(!isNull($scope.takeDeliveryMateriels[i].positionSerial)){
	    				p_arr.push($scope.takeDeliveryMateriels[i].positionSerial);
	    			}
	    		}
	    		$scope.warehouseCount = unique(w_arr);
    			$scope.positionCount = unique(p_arr);
	    	}
	 	};
	    

	 
	 
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
			 * 加载库位数据
			 */
			 $scope.getPositions = function(materiel){
				var promise = takeDeliveryService.getPositions(materiel.warehouseSerial);
				promise.then(function(data){
					//$scope.warehousePositions = data.data;
					materiel.warehousePositons = data.data;
					 countWarehouseAndPosition();
				},function(data){
					//调用承诺接口reject();
				});
			}
			 
			//选中第一个物料仓库时触发
			 $scope.getPositionsAndSelectedAll =function(materiel){
				 $scope.getPositions(materiel);
				 //$(".warehouseSerial").val(materiel.warehouseSerial);
				 
				 for(var i in $scope.takeDeliveryMateriels){
					 if(i>0){
						 $scope.takeDeliveryMateriels[i].warehouseSerial = materiel.warehouseSerial;
						 $scope.getPositions($scope.takeDeliveryMateriels[i]);
					 }
				 }
			 }
			 /**
			  * 计算库位
			  */
			 $scope.countPosition = function(){
				 countWarehouseAndPosition();
			 }
		
			/**
			 * 加载收货单数据
			 */
			var initTakeDelviery = function(){
				var promise = takeDeliveryService.initTakeDelviery();
				promise.then(function(data){
					$scope.takeDeliverys = data.data;
				},function(data){
					//调用承诺接口reject();
				});
			}
		
		
	 		/**
	 		 *加载发货物料信息
	 		 */
	        $scope.getTakeDeliverMateriel=function (delivery) {
	            var sd = $scope.record.takeDeliverSerial;
	           /* for(var i in $scope.takeDeliverys){
		            if(sd == $scope.takeDeliverys[i].takeDelivery.serialNum){
						sd = $scope.takeDeliverys[i].serialNum;
						$scope.deliverSerial = sd;
						$scope.record.orderSerial = delivery.orderSerial;
						$scope.record.orderNum = delivery.orderNum;
					}
	            }*/
	            $scope.record.orderSerial = delivery.orderSerial;
				$scope.record.orderNum = delivery.orderNum;
	        	var promise = takeDeliveryService.getTakeDeliveryMaterielList($scope.deliverSerial);
        		promise.then(function(data){
        			$scope.takeDeliveryMateriels = data.data;
        			countWarehouseAndPosition();
        			//$scope.deliver.materielCount = data.orderMateriel.length;
        			if($location.path()=="/stockOutAdd"&&!isNull($scope.record)&&!isNull($scope.record.serialNum)){ //出库编辑时
        				for(var i in data.data){
            				$scope.getPositions(data.data[i]);
            			}
        			}
        		},function(data){
        			//调用承诺接口reject();
        		});
	        }
	        

	        /**
	         * 确认出库
	         */
			$scope.saveStockIn = function() {
				if($('#stockInForm').valid()){
					handle.blockUI();
					var params = {};
					params.record = $scope.record;
					params.record.deliverSerial = $scope.deliverSerial;
					params.deliveryMateriels = [];
					var param
					for(var i=0;i < $scope.takeDeliveryMateriels.length;i++){
						param = {};
						param.serialNum = $scope.takeDeliveryMateriels[i].serialNum;
						param.stockCount = $scope.takeDeliveryMateriels[i].stockCount;
						param.unstockCount = $scope.takeDeliveryMateriels[i].deliverCount-$scope.takeDeliveryMateriels[i].stockCount;
						param.warehouseSerial = $scope.takeDeliveryMateriels[i].warehouseSerial;
						param.positionSerial = $scope.takeDeliveryMateriels[i].positionSerial;
						param.stockRemark = $scope.takeDeliveryMateriels[i].stockRemark;
						params.deliveryMateriels.push(param);
					}
					
					var promise = takeDeliveryService
							.saveStockOutData(params);
					promise.then(function(data) {
						if(data.data == "1"){
							if(isNull($scope.record.serialNum)){
								toastr.success("出库成功！");
							}else{
								toastr.success("修改成功！");
							}
							$state.go("delivery");
						}else{
							toastr.error("出库失败！请联系管理员");
						}
						handle.unblockUI();
					}, function(data) {
						// 调用承诺接口reject();
						handle.unblockUI();
						toastr.error("出库失败！请联系管理员");
						console.log(data);
					});
				}
			}; 
			
			$scope.cancelStockIn = function(){
				$state.go("delivery");
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
			

	        //重写indexOf方法
			function indexOf(arr, item) {
				for (var i = 0; i < arr.length; i++) {
						if (arr[i] === item)
							return i;
						else
							return -1;
				}
			}
			
			var stockOutInfo = function(serialNum){
				var promise = takeDeliveryService.getStockOutInfo(serialNum);
        		promise.then(function(data){
        			$scope.record = data.data; 
        			$scope.record.deliverNum = data.data.delivery.deliverNum;
        			$scope.record.shipperOrReceiver = data.data.delivery.receiver;
					$scope.shipperOrReceiverName = data.data.delivery.receiverName;
        			//$scope.deliver.materielCount = data.orderMateriel.length;
        			if(!isNull($stateParams.serialNum)&&$location.path()=="/stockOutAdd"||$location.path()=="/stockOut"){//出库编辑时
        				$scope.deliverSerial = data.data.delivery.serialNum;
        				$scope.getTakeDeliverMateriel(data.data.delivery);
        				$scope.record.inOutType = '贸易';
        			}
        		},function(data){
        			//调用承诺接口reject();
        		});
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

		    jQuery.validator.addMethod("StockOutNumCheck", function (value, element) {
				
			    return this.optional(element) || Number($(element).data("delivercount"))-value >= 0;
			}, "出库数量不能超过发货数量");

		 // 页面加载完成后调用，验证输出框
			$scope.$watch('$viewContentLoaded', function() {  
							var e = $("#stockInForm"),
					        r = $(".alert-danger", e),
					        i = $(".alert-success", e);
					        e.validate({
					            errorElement: "span",
					            errorClass: "help-block help-block-error",
					            focusInvalid: !1,
					            ignore: "",
					            messages: {
					            	inOutNum:{required:"出库单号不能为空！"},
					            	takeDeliverSerial:{required:"收货单号不能为空！"},
					            	stockDate:{required:"出库日期不能为空！"},
					            	operator:{required:"操作员不能为空！"},
					            	contactNum:{required:"联系方式不能为空！"},
					            	stockCount:{required:"出库数量不能为空！",digits:"发货数量必须为数字！"},
					            	warehouseSerial:{required:"仓库不能为空！"},
					            	positionSerial:{required:"库位不能为空！"}
					            },
					            rules: {
					            	inOutNum: {
					                    required: !0
					                },
					                takeDeliverSerial: {
					                    required: !0
					                },
					                stockDate: {
					                	required: !0
					                },
					                operator: {
					                	required: !0
					                },
					                warehouseSerial: {
					                	required: !0
					                },
					                positionSerial: {
					                	required: !0
					                },
					                stockCount: {
					                	required: !0,
					                	digits:!0,
					                	StockOutNumCheck:!0
					                },
					                contactNum: {
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
	         
	       

			
			   
		       /***选择收货列表初始化START***/
		       var t_table;
		       var loadDelieryTable = function() {
					
					var a = 0;
					App.getViewPort().width < App.getResponsiveBreakpoint("md") ? $(".page-header").hasClass("page-header-fixed-mobile")&& (a = $(".page-header").outerHeight(!0)): 
						$(".page-header").hasClass("navbar-fixed-top") ? a = $(".page-header").outerHeight(!0): $("body").hasClass("page-header-fixed")&& (a = 64);

						t_table = $("#takeDelivery").DataTable(
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
										zeroRecords : "抱歉， 没有找到！&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;",
										paginate : {
											"sFirst" : "首页",
											"sPrevious" : "前一页",
											"sNext" : "后一页",
											"sLast" : "尾页"
										}
									},
									/*fixedHeader : {// 固定表头、表底
										header : !0,
										footer : !0,
										headerOffset : a
									},*/
									// select: true,行多选
									order : [ [ 1, "asc" ] ],// 默认排序列及排序方式
									bRetrieve : true,
									//"sScrollX": "100%",
								//	"sScrollXInner": "110%",
									"bScrollCollapse": true,
									// searching: true,//是否过滤检索
									// ordering: true,//是否排序
									lengthMenu : [
									              [ 5, 10, 15,15, 30, -1 ],
									              [ 5, 10, 15, 15,30, "All" ] ],
									              pageLength : 10,// 每页显示数量
									              processing : true,// loading等待框
									              // serverSide: true,
									              ajax:"rest/delivery/findAllDeliveryList",//加载数据中user表数据
									              "aoColumns": [
									                    { mData: 'serialNum'},
							                            { mData: 'deliverNum' },
							                            { mData: 'orderNum' },
							                            { mData: 'materielCount' },
							                            { mData: 'packageCount' },
							                            { mData: 'receiver'},
							                            { mData: 'deliveryAddress'},
							                            { mData: 'deliverDate'},
							                            { mData: 'transportType'},
							                            { mData: 'takeAddress' },
							                            { mData: 'remark'},
							                            { mData: 'status'}
									                            ],
									                            
									                            'aoColumnDefs': [ {
									                            	'targets' : 0,
									                            	'searchable' : false,
									                            	'orderable' : false,
									                            	'className' : 'dt-body-center',
									                            	'render' : function(data,type, row, meta) {
//									                            		return '<input  type="radio" id='+data+'  ng-click="getSelectIndex('+meta.row+')"   name="takeDeliverySerial" value="'
//							  											+ $('<div/>')
//							  													.text(
//							  															data)
//							  													.html()
//							  											+ '">';
									                            		return '<label class="mt-radio mt-radio-outline">'+
									                                     '<input type="radio"  ng-click="getSelectIndex(\''+meta.row+'\')" name="takeDeliverySerial"  class="checkboxes" id="'+data+'" value="'+data+'" />'+
									                                     '<span></span></label>';
									                            	},
									                            	"createdCell": function (td, cellData, rowData, row, col) {
									                            		$compile(td)($scope);
									                            	}
									                            } ,
									                            
									                            {
									                            	'targets' : 1,
									                            	'className' : 'dt-body-center',
									                            	'render' : function(data,
									                            			type, row, meta) {
									                            		return '<a data-toggle="modal" ng-click="jumpToGetDeliveryInfo(\''+row.serialNum+'\')" ">'+data+'</a>';
									                            	},
									                            	"createdCell": function (td, cellData, rowData, row, col) {
									                            		$compile(td)($scope);
									                            	}
									                            },
									                            {
									                            	'targets' : 11,
									                            	'render' : function(data,
									                            			type, row, meta) {if(data!=""&&data!=null){
										                            			if(data=='0'){
										                            				return '未审批';
										                            			}else if(data=='PENDING'){
										                            				return '审批中';
										                            			}else if(data=='WAITING_FOR_APPROVAL'){
										                            				return '待审批';					                            				
																				}else if(data=='3'){
																					return '审批成功';
																				}else if(data=='APPROVAL_FAILED'){
																					return '审批失败';
																				}
										                            		}else{
										                            			return "";
										                            		}}
									                            }
									                            ]}).on('order.dt',
									                            		function() {
									                            	console.log('排序');
									                            })
					};
		            /***收货列表初始化END***/
		            
		            $scope.confirmSelect = function(){
			  			var id_count = $('#takeDelivery input[name="takeDeliverySerial"]:checked').length;
						if(id_count==0){
							toastr.warning("请选择发货单");
						}else{
							//var serialNum = $('#buyOrder input[name="selecrOrderSerial"]:checked').val();
							var delivery = t_table.row($scope.index).data();
							$scope.deliverSerial = delivery.serialNum;
							$scope.record.deliverSerial = $('#takeDelivery input[name="takeDeliverySerial"]:checked').val();
							$scope.record.deliverNum = delivery.deliverNum;
							$scope.record.shipperOrReceiver = delivery.receiver;
							$scope.shipperOrReceiverName = delivery.receiverName;
							$scope.record.takeDeliverSerial = "";
							$scope.getTakeDeliverMateriel(delivery);
							$("#takeDeliveryInfo").modal('hide'); 
						}
						
			  		}

		            $scope.getSelectIndex = function(index){
		            	$scope.index = index;
		            }
		            
		            /**
				     * 设置出库默认出库数量
				     */
				    $scope.setDefualtNum = function(scope){
				    	//if(isNull($scope.deliver.serialNum)){
			  				scope.materiel.stockCount = scope.materiel.deliverCount;
			  			//}
				    }
	       

}]); 
