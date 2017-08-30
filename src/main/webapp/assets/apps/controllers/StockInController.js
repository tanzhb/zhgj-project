/**
 * 
 */

angular.module('MetronicApp').controller('StockInController',['$rootScope','$scope','$state','$http','takeDeliveryService','$location','$compile','$stateParams',function($rootScope,$scope,$state,$http,takeDeliveryService,$location,$compile,$stateParams) {
	 $scope.$on('$viewContentLoaded', function() {   
	    	// initialize core components
		    handle = new pageHandle();
	    	App.initAjax();
	    	handle.datePickersInit();
	    	if($location.path()=="/stockInAdd"){
	    		initWarehouse();
	    		handle.validatorInit();
	    		initTakeDelviery();
	    		if(!isNull($stateParams.serialNum)){
	    			$(".s_tip").text("编辑入库信息");
	    			stockInInfo($stateParams.serialNum);
	    		}
	    	}else{
	    			stockInInfo($stateParams.serialNum);
	    	}
	    		
	    	// set default layout mode
	    	$rootScope.settings.layout.pageContentWhite = true;
	        $rootScope.settings.layout.pageBodySolid = false;
	        $rootScope.settings.layout.pageSidebarClosed = false;
	       
	    	
	 });
	 
	 

	 	
	 	var countWarehouseAndPosition = function() {debugger;
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
			var initOrders = function(){debugger;
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
			var initSuppliers = function(){debugger;
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
			var initWarehouse = function(){debugger;
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
			 $scope.getPositions = function(materiel){debugger;
				var promise = takeDeliveryService.getPositions(materiel.warehouseSerial);
				promise.then(function(data){
					//$scope.warehousePositions = data.data;
					materiel.warehousePositons = data.data;
					 countWarehouseAndPosition();
				},function(data){
					//调用承诺接口reject();
				});
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
				promise.then(function(data){debugger;
					$scope.takeDeliverys = data.data;
				},function(data){
					//调用承诺接口reject();
				});
			}
		
		
	 		/**
	 		 *加载发货物料信息
	 		 */
	        $scope.getTakeDeliverMateriel=function () {
	            var sd = $scope.record.takeDeliverSerial;
	            for(var i in $scope.takeDeliverys){
		            if(sd == $scope.takeDeliverys[i].takeDelivery.serialNum){
						sd = $scope.takeDeliverys[i].serialNum;
						$scope.deliverSerial = sd;
						$scope.record.orderSerial = $scope.takeDeliverys[i].orderSerial;
						$scope.record.orderNum = $scope.takeDeliverys[i].orderNum;
					}
	            }
	        	var promise = takeDeliveryService.getTakeDeliveryMaterielList(sd);
        		promise.then(function(data){
        			$scope.takeDeliveryMateriels = data.data;
        			countWarehouseAndPosition();
        			//$scope.deliver.materielCount = data.orderMateriel.length;
        			if($location.path()=="/stockInAdd"){ //入库编辑时
        				for(var i in data.data){
            				$scope.getPositions(data.data[i]);
            			}
        			}
        		},function(data){
        			//调用承诺接口reject();
        		});
	        }
	        

	        /**
	         * 确认入库
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
						param.unstockCount = $scope.takeDeliveryMateriels[i].acceptCount-$scope.takeDeliveryMateriels[i].stockCount;
						param.warehouseSerial = $scope.takeDeliveryMateriels[i].warehouseSerial;
						param.positionSerial = $scope.takeDeliveryMateriels[i].positionSerial;
						param.stockRemark = $scope.takeDeliveryMateriels[i].stockRemark;
						params.deliveryMateriels.push(param);
					}
					
					var promise = takeDeliveryService
							.saveStockInData(params);
					promise.then(function(data) {
						if(data.data == "1"){
							toastr.success("入库成功！");
							$state.go("takeDelivery",{type:"stockIn"});
						}else{
							toastr.error("入库失败！请联系管理员");
						}
						handle.unblockUI();
					}, function(data) {
						// 调用承诺接口reject();
						handle.unblockUI();
						toastr.error("入库失败！请联系管理员");
						console.log(data);
					});
				}
			}; 
			
			$scope.cancelStockIn = function(){
				$state.go("takeDelivery",{type:"stockIn"});
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
			
			var stockInInfo = function(serialNum){
				var promise = takeDeliveryService.getStockInInfo(serialNum);
        		promise.then(function(data){
        			$scope.record = data.data; 
        			//$scope.deliver.materielCount = data.orderMateriel.length;
        			if(!isNull($stateParams.serialNum)&&$location.path()=="/stockInAdd"){//入库编辑时
        				$scope.getTakeDeliverMateriel();
        			}
        		},function(data){
        			//调用承诺接口reject();
        		});
			}

	       /**
	        * 导出收货计划
	        */
	       $scope.exportTakeDelivery = function(){
		    	 window.location.href=$rootScope.basePath+"/rest/takeDelivery/exportTakeDelivery";
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

		    jQuery.validator.addMethod("StockInNumCheck", function (value, element) {
				
			    return this.optional(element) || Number($(element).data("acceptcount"))-value >= 0;
			}, "入库数量不能超过实收数量");

		 // 页面加载完成后调用，验证输入框
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
					            	inOutNum:{required:"入库单号不能为空！"},
					            	takeDeliverSerial:{required:"收货单号不能为空！"},
					            	stockDate:{required:"入库日期不能为空！"},
					            	operator:{required:"操作员不能为空！"},
					            	contactNum:{required:"联系方式不能为空！"},
					            	stockCount:{required:"入库数量不能为空！",digits:"发货数量必须为数字！"},
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
					                	StockInNumCheck:!0
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
	         
	       

	       
	       

}]); 
