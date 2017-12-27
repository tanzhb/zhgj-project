/**
 * 
 */

angular.module('MetronicApp').controller('StockOutController',['$rootScope','$scope','$state','$http','takeDeliveryService','$location','$compile','$stateParams','commonService',function($rootScope,$scope,$state,$http,takeDeliveryService,$location,$compile,$stateParams,commonService) {
	 $scope.$on('$viewContentLoaded', function() {   
	    	// initialize core components
		    handle = new pageHandle();
	    	App.initAjax();
	    	handle.datePickersInit();
	    	if($location.path()=="/stockOutAdd"||$location.path()=="/stockOut"){
	    		$scope.serialNums=[];
	    		initWarehouse();
	    		handle.validatorInit();
	    		//initTakeDelviery();
	    		initCustomers();
	    		loadDelieryTable();
	    		if(!isNull($stateParams.serialNum)){
	    			$(".s_tip").text("编辑出库信息");
	    			stockOutInfo($stateParams.serialNum);
	    		}else{
	    			$rootScope.setNumCode("OU",function(newCode){
    		 			$scope.record.inOutNum= newCode;//出库单号
    		 		});
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
			 * 加载采购商数据
			 */
			var initCustomers = function(){
			var promise = commonService.initCustomers();
			promise.then(function(data){
				$scope.customers = data.data;
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
			 * 加载库区数据
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
			  * 计算库区
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
	            var deliverSerial = $scope.deliverSerial;
	            var stockSerial = null;
	            if(isNull(deliverSerial)){
	            	stockSerial = $scope.record.serialNum;
	            }
	           /* for(var i in $scope.takeDeliverys){
		            if(sd == $scope.takeDeliverys[i].takeDelivery.serialNum){
						sd = $scope.takeDeliverys[i].serialNum;
						$scope.deliverSerial = sd;
						$scope.record.orderSerial = delivery.orderSerial;
						$scope.record.orderNum = delivery.orderNum;
					}
	            }*/
	            if(delivery!=null){
		            $scope.record.orderSerial = delivery.orderSerial;
					$scope.record.orderNum = delivery.orderNum;
	            }
	        	var promise = takeDeliveryService.getTakeDeliveryMaterielList(deliverSerial,stockSerial);
        		promise.then(function(data){
        			$scope.takeDeliveryMateriels = data.data;
        			var deliveryMaterielSerialNums=new Array();
    				for(var i in data.data){
    					if(isNull($scope.takeDeliveryMateriels[i].orderMateriel)){
        					$scope.takeDeliveryMateriels[i].orderMateriel = data.data[i].supplyMateriel;
        					delete $scope.takeDeliveryMateriels[i].supplyMateriel;
    					}
    					if(data.data[i].serialNum!=null){
    						deliveryMaterielSerialNums.push(data.data[i].serialNum);
    					}
    				}
    				$scope.deliveryMaterielSerialNums=deliveryMaterielSerialNums;//把所有发货物料流水存到该数组
    				//$scope.record.warehosueCount = data.data.length;
        			countWarehouseAndPosition();
        			//$scope.deliver.materielCount = data.orderMateriel.length;
        			var  array=new Array();
        			for(var i=0;i<$scope.takeDeliveryMateriels.length;i++){
        				if($scope.takeDeliveryMateriels[i].deliverCount!=0){
        					array.push($scope.takeDeliveryMateriels[i]);
        				}
        			}
        			$scope.takeDeliveryMateriels =array;
        			var totalOrderCount=0, totalDeliveryCount=0;
	        		var  totalQualifiedCount=0,totalStockOutCount=0;
	        		for(var i in $scope.takeDeliveryMateriels ){
	        			if($scope.takeDeliveryMateriels[i].orderMateriel!=null){
	        			/*	$scope.orderMateriels[i].materiel = $scope.takeDeliveryMateriels[i].orderMateriel.materiel;
		        			$scope.orderMateriels[i].amount = $scope.takeDeliveryMateriels[i].orderMateriel.amount;
		        			$scope.orderMateriels[i].serialNum = $scope.takeDeliveryMateriels[i].serialNum;
		        			$scope.orderMateriels[i].orderMaterielSerial = $scope.takeDeliveryMateriels[i].orderMateriel.serialNum;*/
		        			totalOrderCount=totalOrderCount+Number( $scope.takeDeliveryMateriels[i].orderMateriel.amount);
		        			totalDeliveryCount=totalDeliveryCount+Number( $scope.takeDeliveryMateriels[i].deliverCount);
		        			if($scope.oprateType==undefined){//出库计划详情物料tab展示合格总数,出库总数,未出总数
		        				totalQualifiedCount=totalQualifiedCount+Number($scope.takeDeliveryMateriels[i].stockInQualifiedCount);
		        				totalStockOutCount=totalStockOutCount+Number( $scope.takeDeliveryMateriels[i].stockCount);
			        		}
	        			}
	        		}
	        		$scope.totalDeliveryCount=totalDeliveryCount;//发货总数
	        		$scope.totalOrderCount=totalOrderCount;//订单总数
	        		if($scope.oprateType==undefined){//出库计划详情物料tab展示合格总数,出库总数,未出总数
	        			$scope.totalQualifiedCount=totalQualifiedCount;//合格总数
		        		$scope.totalStockOutCount=totalStockOutCount;//出库总数
		        		$scope.totalUnstockOutCount=totalDeliveryCount-totalStockOutCount;//未出总数
	        		}
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
			$scope.saveStockOut = function(judgeString) {
				if($('#stockInForm').valid()){
					
					var params = {};
					if(judgeString=='save'){
						$scope.record.status='0';	
					}else{
						$scope.record.status='1';
					}
					params.record = $scope.record;
					params.record.deliverSerial = $scope.deliverSerial;
					params.deliveryMateriels = [];
					params.stockOutMateriels = [];
					params.record.takeDeliverSerial = '';
					var param
					if($scope.takeDeliveryMateriels){
						for(var i=0;i < $scope.takeDeliveryMateriels.length;i++){
							param = {};
							param.stockCount = $scope.takeDeliveryMateriels[i].stockCount;
							//param.stockCount=$scope['totalCount'+$scope.takeDeliveryMateriels[i].serialNum];
							if(!isNull($scope.takeDeliveryMateriels[i].orderMateriel)){ //贸易出库
								param.serialNum = $scope.takeDeliveryMateriels[i].serialNum;
								$scope.takeDeliveryMateriels[i].stockCount=$scope['totalCount'+$scope.takeDeliveryMateriels[i].serialNum];
								param.unstockCount = $scope.takeDeliveryMateriels[i].deliverCount-$scope.takeDeliveryMateriels[i].stockCount;
								param.orderMaterielSerial = $scope.takeDeliveryMateriels[i].orderMaterielSerial;
							}else{//其他出库
								//param.orderMaterielSerial = $scope.takeDeliveryMateriels[i].serialNum;
								param.deliverSerial = params.record.takeDeliverSerial;
								param.unstockCount = 0;
								params.record.deliverSerial = '';
								param.supplyMaterielSerial = $scope.takeDeliveryMateriels[i].supplyMaterielSerial;
							}
							/*if(isNull($scope.takeDeliveryMateriels[i].supplyMaterielSerial)){
								param.orderMaterielSerial = $scope.takeDeliveryMateriels[i].orderMaterielSerial;
							}else{
								//param.orderMaterielSerial = '';
								params.record.deliverSerial = '';
								param.supplyMaterielSerial = $scope.takeDeliveryMateriels[i].supplyMaterielSerial;
							}*/
							param.warehouseSerial = $scope.takeDeliveryMateriels[i].warehouseSerial;
							param.positionSerial = $scope.takeDeliveryMateriels[i].positionSerial;
							param.stockRemark = $scope.takeDeliveryMateriels[i].stockRemark;
							params.deliveryMateriels.push(param);
						}
					}else{
						toastr.warning("出库物料不能为空!");
						return
					}
					
					var arraySerialNums=$scope.arraySerialNums;
					var deliveryMaterielSerialNums =$scope.deliveryMaterielSerialNums;
					
					for(var i=0;i < deliveryMaterielSerialNums.length;i++){
						if($scope["arraySerialNums"+deliveryMaterielSerialNums[i]]==undefined&&$scope.takeDeliveryMateriels[i].stockOutMateriels.length==0&&$scope.takeDeliveryMateriels[i].currentStockAmount>0){
							toastr.warning("存在未选择出库批次的物料!");
							return;
						}
					}
					//handle.blockUI();
					for(var m=0;m< deliveryMaterielSerialNums.length;m++){
					if($scope["arraySerialNums"+deliveryMaterielSerialNums[m]]!=undefined){
					var serialNums=new Array();
					for(var i=0;i < arraySerialNums.length;i++){
						serialNums=serialNums.concat($scope["arraySerialNums"+arraySerialNums[i]]);
					}
					for(var i=0;i < serialNums.length;i++){
						param = {};
						var arrays=serialNums[i].split(",");
						param.stockOutSerial = $scope.record.serialNum;//出库单流水
						param.stockInBatchSerial=arrays[0];//入库物料流水
						param.stockOutMaterielSerial=arrays[2];//出库物料流水(发货物料流水)
						param.outCount=arrays[1];//出库数量
						param.stockInSerial=arrays[3];//入库单流水
						params.stockOutMateriels.push(param);
					}
					}else{
						for(var i=0;i < $scope.takeDeliveryMateriels[m].stockOutMateriels.length;i++){
							param = {};
							var stockOutMateriel=$scope.takeDeliveryMateriels[m].stockOutMateriels[i];
							param.stockOutSerial = $scope.record.serialNum;//出库单流水
							param.stockInBatchSerial=stockOutMateriel.stockInBatchSerial;//入库物料流水
							param.stockOutMaterielSerial=stockOutMateriel.stockOutMaterielSerial;//出库物料流水(发货物料流水)
							param.outCount=stockOutMateriel.outCount;//出库数量
							param.stockInSerial=stockOutMateriel.stockInSerial;//入库单流水
							params.stockOutMateriels.push(param);
						}
						//params.stockOutMateriels.push($scope.takeDeliveryMateriels[i].stockOutMateriels);
					}
					}
					handle.blockUI();
					var promise = takeDeliveryService
							.saveStockOutData(params);
					promise.then(function(data) {
						if(data.data == "1"){
							if(judgeString=='save'){
								toastr.success("保存成功！");
							}else{
								toastr.success("出库成功！");
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
			
			$scope.cancelStockOut = function(){
				$state.go("delivery");
			}
			
			$scope.getWarehouseName = function(type){
				debugger;
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
        			//$scope.record = data.data; 
        			$scope.record = data.data.stockInOutRecord; 
        			$scope.deliver = data.data.deliver; 
        			$scope.totalDeliveryCount=data.data.totalDeliveryCount; //发货数量
        			if($scope.deliver.deliverType!='其他发货'){
        				$scope.otherMode=false
        			}else{
        				$scope.otherMode=true;
        			}
        			if(data.data.deliver!=null){
        				$scope.record.deliverNum = data.data.deliver.deliverNum;
            			$scope.record.shipperOrReceiver = data.data.deliver.receiver;
    					//$scope.shipperOrReceiverName = data.data.deliver.shipperName;
            			//$scope.deliver.materielCount = data.orderMateriel.length;
            			if(!isNull($stateParams.serialNum)&&($location.path()=="/stockOutAdd"||$location.path()=="/stockOut")){//出库编辑或入库时时
            				$scope.deliverSerial = data.data.deliver.serialNum;
            				$scope.getTakeDeliverMateriel(data.data.deliver);
            			}else{
            				$scope.getTakeDeliverMateriel(data.data.deliver);
            				$scope.queryForPage();
            			}
            		
        			}else{
        				if(!isNull($stateParams.serialNum)&&($location.path()=="/stockOutView")){//出库编辑或出库时
        					//var de$scope.record.serialNum;
            				$scope.getTakeDeliverMateriel(data.data.deliver);
            				//$scope.createFilterList();
            			}
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
				if(isNaN(Number($(element).data("delivercount")))){
					return -1;
				}
			    return this.optional(element) || (Number($(element).data("delivercount"))-value >= 0&&Number($(element).data("currentstock"))-value >= 0);
			}, "出库数量不能超过发货数量且出库数量不能超过当前库存数量");
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
					            	inOutType:{required:"出库类型不能为空！"},
					            	/*stockCount:{required:"出库数量不能为空！",digits:"发货数量必须为数字！"},*/
					            	warehouseSerial:{required:"仓库不能为空！"}
					            	//positionSerial:{required:"库区不能为空！"}
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
					                inOutType: {
					                	required: !0
					                },
					                /*positionSerial: {
					                	required: !0
					                },*/
					              /*  stockCount: {
					                	required: !0,
					                	digits:!0,
					                	StockOutNumCheck:!0,
					                	StockOutNumCheck1:!0,//出库数量必须小等于当前库存数量
					                },*/
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
	         
	       

			
			
			var m_table;	
	  		
	  		$scope.changeStockOutMode = function(stockOutType){
	  			
	  			if(stockOutType=='贸易'){
	  					$scope.otherMode = false;
	  					$(".bootstrap-select").remove();//移除下拉控件
	  			}else{
	  				    $scope.otherMode = true;
	  				    //$scope.deliver.orderSerial = '';
	  				   // $scope.deliver.shipper = '';
	  				   // $scope.deliver.shipperName = '';
	  				    //$scope.deliver.receiver = '';
	  				   // $scope.deliver.receiverName = '';
	  				   // $scope.orderMateriels = [];
	  				 
	  				    if(m_table==undefined){
	  				    	selectParentMateriel();
	  				    }else{
	  				    	m_table.ajax.reload();
	  				    }
	  				   
	  			}
	  		}
	  		var outMateriel;
	  		$scope.showStockBatch=function(index,materiel,judgeString){//选择批次  materielSerialNum为基本物料流水 orderSerial 订单流水 deliveryMaterielSerialNum 发货物料流水
	  			//var stockOutCount=Number($("#stockCountinline"+deliveryMaterielSerialNum).val());//获取出库数量
	  			var stockOutCount=materiel.deliverCount;
	  			var materielSerialNum=materiel.orderMateriel.materielSerial;
	  			var orderSerial=materiel.orderMateriel.orderSerial;
	  			var deliveryMaterielSerialNum=materiel.serialNum;
	  			var outMateriel=materiel;
	  			$scope.index=index;
	  			$scope.totalCount=materiel.stockCount;
	  			debugger;
	  			if(stockOutCount==0||stockOutCount==NaN){
	  				toastr.warning("请先输入正确的出库数量");
	  			}else{
	  				$('#stockBatchInfo').modal('show');//显示批次弹框
	  			  /* if(stockBatchTable!=undefined){
	 	            	  stockBatchTable.ajax.reload(); 
	 	            	 
	 			 	    	 }else{
	  				loadStockBatchTable(stockOutCount,materielSerialNum,orderSerial,deliveryMaterielSerialNum);
	 			 	    	 }*/
	  				loadStockBatchTable(stockOutCount,materielSerialNum,orderSerial,deliveryMaterielSerialNum,judgeString);
	  			}
	  		}
			  
	  	 	 /**
	 		 * 遍历checkbox将之前保存的出库情况展示出来
	 		 */
	 		function showDetail(){
	 			//m_table.$('input[type="checkbox"]')
	 			stockBatchTable.$('input[type="checkbox"]').each(function() { //遍历所有checkbox
	 				
	 					if ($.contains(document, this)) {
	 						 var serialNums=$scope["arraySerialNums"+$scope.deliveryMaterielSerialNum];
	 						 if(serialNums!=undefined){
							 for(var i=0;i<serialNums.length;i++){
								  var arrays=serialNums[i].split(",");
								 if(arrays[0]==$(this).attr("id")&&$scope.deliveryMaterielSerialNum==arrays[2]){
									$("#"+arrays[0]).attr("checked",true);
										$("#stockOutCount"+arrays[0]).attr("readonly",false);
										//$("#stockOutCount"+arrays[0]).val(arrays[1]);
										$scope["stockValue"+arrays[0]] = arrays[1];
										$("#stockOutCount"+arrays[0]).css("border","1px solid");
								 }
							 }
	 						 }
	 					}
	 			});
	 		}
	  		 var stockBatchTable,tableUrl;//批次弹框
	 	       var loadStockBatchTable = function(stockOutCount,materielSerialNum,orderSerial,deliveryMaterielSerialNum,judgeString) {
	 	    	   $scope.deliveryMaterielSerialNum=deliveryMaterielSerialNum;
	 	    	   $scope.stockOutCount=stockOutCount;
	 	    	 /*  if($scope['totalCount'+$scope.deliveryMaterielSerialNum]!=undefined){
	 	    		   $scope.totalCount=$scope['totalCount'+$scope.deliveryMaterielSerialNum];
	 	    	   }else{
	 	    		  $scope.totalCount=0;
	 	    	   }*/
	 	    	   if(judgeString=='add'){
	 	    		  tableUrl="rest/stock/stockInBatchList?serialNum="+materielSerialNum+"&orderSerial="+orderSerial;
	 	    	   }else if(judgeString=='edit'){
	 	    		  tableUrl="rest/stock/stockInBatchList?serialNum="+materielSerialNum+"&orderSerial="+orderSerial+"&dmSerialNum="+deliveryMaterielSerialNum;
	 	    		  if($scope.initEdit==undefined){
	 	    			 $scope.initEdit=0;
	 	    		  }
	 	    	   }
	 	    	
	 	                a = 0;
	 	                App.getViewPort().width < App.getResponsiveBreakpoint("md") ? $(".page-header").hasClass("page-header-fixed-mobile") && (a = $(".page-header").outerHeight(!0)) : $(".page-header").hasClass("navbar-fixed-top") ? a = $(".page-header").outerHeight(!0) : $("body").hasClass("page-header-fixed") && (a = 64);
	 	               if(stockBatchTable!=undefined){
	 	            	  stockBatchTable.destroy(); 
	 			 	    	 }
	 	                stockBatchTable = $("#select_sample_stockBatch")
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
	 	                  /* destroy:true,*/
	 	                    lengthMenu: [[5, 10, 15, 30, -1], [5, 10, 15, 30, "All"]],
	 	                    pageLength: 5,//每页显示数量
	 	                    processing: true,//loading等待框
//	 	                    serverSide: true,
	 	                    ajax: tableUrl,//加载数据中 
	 	                    "aoColumns": [
	 	                                 { mData: 'serialNum' },
	 	                                 { mData: 'batchNum' },
	 	                                 { mData: 'stockInOutRecord.stockDate' },
	 	                                 { mData: 'warehouse' },
	 	                                 { mData: 'position' },
	 	                                 { mData: 'stockCount' },
	 	                                 { mData: 'stockInOutRecord.inOutNum' }
	 	                            ],
	 	                   'aoColumnDefs' : [ {
	 	    							'targets' : 0,
	 	    							'searchable' : false,
	 	    							'orderable' : false,
	 	    							
	 	    							'render' : function(data,
	 	    									type, row, meta) {
	 	    								if($scope.initEdit==0){
	 	    									return  '<label class="mt-checkbox mt-checkbox-single mt-checkbox-outline">'+
			                                     '<input type="checkbox" data-check="false"  name="'+row.stockInOutRecord.serialNum+'"     ng-checked="'+row.outCount+'!=\''+0+'\' "       class="checkboxes" ng-click="showStockOutCount(\''+row.serialNum+'\',\''+stockOutCount+'\',\''+row.stockCount+'\',\''+row+'\')" id="'+data+'" value="'+data+'" data-set="#select_sample_stockBatch .checkboxes" />'+
			                                     '<span></span></label>';
	 	    								}else{
	 	    									return  '<label class="mt-checkbox mt-checkbox-single mt-checkbox-outline">'+
			                                     '<input type="checkbox" data-check="false"  name="'+row.stockInOutRecord.serialNum+'"       class="checkboxes" ng-click="showStockOutCount(\''+row.serialNum+'\',\''+stockOutCount+'\',\''+row.stockCount+'\',\''+row+'\')" id="'+data+'" value="'+data+'" data-set="#select_sample_stockBatch .checkboxes" />'+
			                                     '<span></span></label>';
	 	    								}
	 	    								//
	 	    							},
	 	    							"createdCell": function (td, cellData, rowData, row, col) {
	 	    								 $compile(td)($scope);
	 	    						       }
	 	    						},{
										'targets' : 3,
										'render' : function(data,
												type, row, meta) {
	 	    								if(data==''||data==null){
	 	    									return "";
	 	    								}else{
	 	    									return data.warehouseName;
	 	    								}
											
										}
									},{
										'targets' : 4,
										'render' : function(data,
												type, row, meta) {
	 	    								if(data==''||data==null){
	 	    									return "";
	 	    								}else{
	 	    									return data.positionName;
	 	    								}
											
										}
									}, {
										'targets' : 5,
										'render' : function(data,
												type, row, meta) {
	 	    									return row.stockCount-row.sumStockOutCount;
	 	    								
											
										}
									},{
										'targets' : 6,
										'className' : 'dt-body-center',
										'render' : function(data,
												type, row, meta) {
											/*if(row.warehouse==''||row.warehouse==null){
												return '<input  type="text"  class="form-control"   style="border:none" readonly="readonly"   name="'+row.batchNum+','+"noWarehouse"+'"  id="stockOutCount'+row.serialNum+'" ng-model="stockValue'+row.serialNum+'"  ng-init="stockValue'+row.serialNum+'=\''+row.outCount+'\'"  ng-blur="judgeNumber(\''+row.stockCount+'\',\''+stockOutCount+'\',\''+row.serialNum+'\')" />';
											}else{
												return '<input  type="text"    class="form-control"  style="border:none" readonly="readonly"   name="'+row.batchNum+','+row.warehouse.serialNum+'"  id="stockOutCount'+row.serialNum+'" ng-model="stockValue'+row.serialNum+'"  ng-init="stockValue'+row.serialNum+'=\''+row.outCount+'\'"  ng-blur="judgeNumber(\''+row.stockCount+'\',\''+stockOutCount+'\',\''+row.serialNum+'\')" />';
											}*/
											if(row.warehouse==''||row.warehouse==null){
												if(row.outCount==0){
													return '<input  type="text"  class="form-control"   style="border:none" readonly="readonly"   name="'+row.batchNum+','+"noWarehouse"+'"  id="stockOutCount'+row.serialNum+'" ng-model="stockValue'+row.serialNum+'"  ng-init="stockValue'+row.serialNum+'=\''+row.outCount+'\'"  ng-blur="judgeNumber(\''+row.stockCount+'\',\''+stockOutCount+'\',\''+row.serialNum+'\')" />';
												}else{
													return '<input  type="text"  class="form-control"     name="'+row.batchNum+','+"noWarehouse"+'"  id="stockOutCount'+row.serialNum+'" ng-model="stockValue'+row.serialNum+'"  ng-init="stockValue'+row.serialNum+'=\''+row.outCount+'\'"  ng-blur="judgeNumber(\''+row.stockCount+'\',\''+stockOutCount+'\',\''+row.serialNum+'\')" />';
												}
											}else{
												if(row.outCount==0){
													return '<input  type="text"    class="form-control"  style="border:none" readonly="readonly"   name="'+row.batchNum+','+row.warehouse.serialNum+'"  id="stockOutCount'+row.serialNum+'" ng-model="stockValue'+row.serialNum+'"  ng-init="stockValue'+row.serialNum+'=\''+row.outCount+'\'"  ng-blur="judgeNumber(\''+row.stockCount+'\',\''+stockOutCount+'\',\''+row.serialNum+'\')" />';
												}else{
													return '<input  type="text"    class="form-control"     name="'+row.batchNum+','+row.warehouse.serialNum+'"  id="stockOutCount'+row.serialNum+'" ng-model="stockValue'+row.serialNum+'"  ng-init="stockValue'+row.serialNum+'=\''+row.outCount+'\'"  ng-blur="judgeNumber(\''+row.stockCount+'\',\''+stockOutCount+'\',\''+row.serialNum+'\')" />';
												}	
												}
										},"createdCell": function (td, cellData, rowData, row, col) {
											 $compile(td)($scope);
									    }
									}],
									//stateSave:false,
									"fnInitComplete":function(settings) {//fnInitComplete stateLoadCallback
					                	   showDetail();
					                   }

	 	                });
	 	               $("#select_sample_stockBatch").find(".group-checkable").change(function() {
					        var e = jQuery(this).attr("data-set"),
					        t = jQuery(this).is(":checked");
					        jQuery(e).each(function() {
					            t ? ($(this).prop("checked", !0), $(this).parents("tr").addClass("active")) : ($(this).prop("checked", !1), $(this).parents("tr").removeClass("active"))
					        })
					    }),
					    $("#select_sample_stockBatch").on("change", "tbody tr .checkboxes",
					    function() {
					        $(this).parents("tr").toggleClass("active")
					    })
                  
					   return stockBatchTable;
	 	               
	 	              
	 	            };
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
											sortAscending : ": 以升序排列此列",
											sortDescending : ": 以降序排列此列"
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
							                            //{ mData: 'materielCount' },物料条目数
								                          { mData: 'materielTotalCount' },//物料总数
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
										                            				return '待发货';
										                            			}else if(data=='PENDING'){
										                            				return '审批中';
										                            			}else if(data=='WAITING_FOR_APPROVAL'){
										                            				return '待审批';					                            				
																				}else if(data=='3'){
																					return '待收货';
																				}else if(data=='APPROVAL_FAILED'){
																					return '审批失败';
																				}else if(data=='4'){
																					return '已收货';
																				}else{
																					return '';
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
					
					$scope.showStockOutCount=function(serialNum,stockOutCount,stockCount,obj){//选中checkbox显示输入框
						
						var value;
						if($scope.totalCount==undefined){
							$scope.totalCount=0;
						}else if($scope.totalCount>=stockOutCount&&$("#stockOutCount"+serialNum).val()==0){
							 toastr.warning("不需再选批次！");
							 $("#"+serialNum).attr("checked",false);
							 return;
						}
						if($("#"+serialNum).is(':checked')){
							$("#stockOutCount"+serialNum).css("border","1px solid");
							$("#stockOutCount"+serialNum).attr("readonly",false);
						}else{
							$("#stockOutCount"+serialNum).css("border","none");
							$("#stockOutCount"+serialNum).attr("readonly",true);
							value=$("#stockOutCount"+serialNum).val();
							$("#stockOutCount"+serialNum).val(0);//重新置为0
							$scope.totalCount=($scope.totalCount-Number(value));
							 toastr.warning("重新选择出库,数量为"+value+"!");
							
						}
						//$scope.judgeNumber(stockCount,stockOutCount,serialNum);
						
					}
					
					$scope.judgeNumber=function(stockCount,stockOutCount,serialNum){

						 var value=$("#stockOutCount"+serialNum).val();
						 
						 if(isNaN(value)||value%1!=0||value==0){
							 toastr.warning("出库数量必须为正整数！");
							 return;
						 }
						 if(Number(value)>Number(stockOutCount)){
							toastr.warning("当前出库数量不得大于总出库数量！");
							  $("#stockOutCount"+serialNum).empty();
							 $("#stockOutCount"+serialNum).focus(); 
							 return;
						 }else if(Number(value)>Number(stockCount)){
							 toastr.warning("当前出库数量不得大于结存数量！");
							  $("#stockOutCount"+serialNum).empty();
							 $("#stockOutCount"+serialNum).focus(); 
							 return;
						 }
						 var checkboxs=$('input[class="checkboxes"]:checked');
						 var count=0;
						 for(var i=0;i<checkboxs.length;i++){
							  var serialNum=$(checkboxs[i]).val();
							  var value=$("#stockOutCount"+serialNum).val();
							  count=count+Number(value);
						 }
						 $scope['totalCount'+$scope.deliveryMaterielSerialNum]=count;
						 $scope.totalCount=$scope['totalCount'+$scope.deliveryMaterielSerialNum];
						 if(count>stockOutCount){
							 toastr.warning("各批次总出库数量不得大于总出库数量！");
							 $("#stockOutCount"+serialNum).val(0); 
						 }else if(count<stockOutCount){
							// toastr.info("还需出库"+(stockOutCount-count));
						 }
					 }
					$scope.deleteOrdinaryData=function(deliveryMaterielSerialNum){
						if($scope["arraySerialNums"+deliveryMaterielSerialNum]!=undefined){
							delete $scope["arraySerialNums"+deliveryMaterielSerialNum];
							$scope['totalCount'+deliveryMaterielSerialNum]=0;
							 $("#"+$scope.deliveryMaterielSerialNum).html(" ");
							 toastr.warning("重新选择出库批次");
							 
						}
					}
					$scope.confirmSave=function(){//保存批次
						$scope.initEdit="!0";
						 var checkboxs=$('input[class="checkboxes"]:checked');
						 if(checkboxs.length==0){
							 toastr.warning("未勾选批次"); 
							 return;
						 }
						 if($scope.totalCount<0){
							 toastr.warning("未勾选批次"); 
							 return;
						 }
						 //$scope.arraySerialNums = null;
						 var arraySerialNums=$scope.arraySerialNums;
						 if($scope.arraySerialNums==undefined){
							 arraySerialNums= new Array();
							 arraySerialNums.push($scope.deliveryMaterielSerialNum);
						 }else{
							 var flag=true;
							 for(var i=0;i<arraySerialNums.length;i++){
								 if(arraySerialNums[i]==$scope.deliveryMaterielSerialNum){
									 flag=false;
								 }
							 }
							 if(flag){
								 arraySerialNums.push($scope.deliveryMaterielSerialNum);
							 }
						 }
						 $scope.arraySerialNums=arraySerialNums;
						 $scope["arraySerialNums"+$scope.deliveryMaterielSerialNum] = null;
						 var serialNums=new Array();
						 var  count=0;//出库和
						 for(var i=0;i<checkboxs.length;i++){
							 var serialNum=$(checkboxs[i]).val();
							  var value=$("#stockOutCount"+serialNum).val();//出库数量
							  count=count+Number(value);
							  var rukuSerialNum=$("#"+serialNum).attr("name");//入库单流水(入库批次)
							  var  inOutNum=$("#stockOutCount"+serialNum).attr("name");//入库批次号
							  var nameArrays=inOutNum.split(",");
							  var addvalue=serialNum+","+value+","+$scope.deliveryMaterielSerialNum+","+rukuSerialNum+","+nameArrays[0]+","+nameArrays[1];//拼接checkbox选中流水和之前设定的出库数值以及发货物料流水和入库单流水以及入库批次号
							  serialNums[i]=addvalue;
						 }
						 if(count> $scope.stockOutCount){
								toastr.warning("当前总出库数量已大于出库数量!");
								return;
						 }
						 $("#stockCountinline"+$scope.deliveryMaterielSerialNum).val(count);
						 if($scope.record.materielCount==null||$scope.record.materielCount==undefined){
							 $scope.record.materielCount=0; 
						 }
						 $scope.record.materielCount=$scope.record.materielCount-$scope.takeDeliveryMateriels[$scope.index].stockCount+count;
						 $scope.totalStockOutCount= $scope.record.materielCount;
						 $scope.totalUnstockOutCount=$scope.totalDeliveryCount- $scope.record.materielCount
						$scope.takeDeliveryMateriels[$scope.index].stockCount=count;
						 var inOutNums="";
						 var warehouseSerialNums=new Array();
						 for(var i=0;i<serialNums.length;i++){
							 var arrayValue=serialNums[i].split(",");
							 inOutNums=inOutNums+arrayValue[4]+'('+ arrayValue[1] +')';
							  if(arrayValue[5]!='noWarehouse'){
								  warehouseSerialNums.push(arrayValue[5]);
							  }
							 if(i!=serialNums.length-1){
								 inOutNums= inOutNums+";";
							 }
						 }
						 var warehouseArrays=$scope["warehouse"+$scope.deliveryMaterielSerialNum];
						 if(warehouseArrays!=undefined){
							 delete $scope["warehouse"+$scope.deliveryMaterielSerialNum];
						 }
						 $scope["warehouse"+$scope.deliveryMaterielSerialNum]=warehouseSerialNums;
						 var warehouse=new  Array();
						 for(var i=0;i < arraySerialNums.length;i++){
							 warehouse=warehouse.concat($scope["warehouse"+$scope.deliveryMaterielSerialNum]);
							}
						 $scope.warehouseCount = unique(warehouse);
						 
						 $("#"+$scope.deliveryMaterielSerialNum).html(inOutNums);
						 //$scope.arraySerialNums=serialNums;
						 $scope["arraySerialNums"+$scope.deliveryMaterielSerialNum] =serialNums;
						 $('#stockBatchInfo').modal('hide');//显示批次弹框
					}
		            /***收货列表初始化END***/
		            
		            $scope.confirmDeliverySelect = function(){
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
							//if(isNull($scope.record.shipperOrReceiver)){
							//	$scope.shipperOrReceiver = '中航能科（上海）能源科技有限公司';
							//}else{
								//$scope.shipperOrReceiverName = delivery.receiverName;
							//}
							//$scope.record.orderNum = ;
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
	       
				  	/***选择物料列表初始化START***/
				     
				     var selectParentMateriel = function() {
				              a = 0;
				              App.getViewPort().width < App.getResponsiveBreakpoint("md") ? $(".page-header").hasClass("page-header-fixed-mobile") && (a = $(".page-header").outerHeight(!0)) : $(".page-header").hasClass("navbar-fixed-top") ? a = $(".page-header").outerHeight(!0) : $("body").hasClass("page-header-fixed") && (a = 64);
				              m_table = $("#select_sample_2").DataTable({
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
//				                  serverSide: true,
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
//					  	  								return '<input type="radio" id='+data+' data-radio=true ng-click="getCheckedIds(\''+data+'\','+meta.row+')"  name="serialNum" value="'
//															+ $('<div/>')
//																	.text(
//																			row.supplyMateriels[0].serialNum)
//																	.html()
//															+ '">';
					  	  							return '<label class="mt-radio mt-radio-outline">'+
				                                    '<input type="radio" data-radio=true   ng-click="getCheckedIds(\''+data+'\','+meta.row+')" name="serialNum"  class="checkboxes" id="'+data+'" value="'+row.supplyMateriels[0].serialNum+'" />'+
				                                    '<span></span></label>';
					
					  								}else{
					  	  								/*return '<input type="checkbox" data-checked=false id='+data+' ng-click="getCheckedIds(\''+data+'\','+meta.row+')"  name="material_serial" value="'
															+ $('<div/>')
																	.text(
																			row.supplyMateriels[0].serialNum)
																	.html()
															+ '">';*/
					  									return '<label class="mt-checkbox mt-checkbox-single mt-checkbox-outline">'+
					                                     '<input type="checkbox"  name="material_serial" data-checked=false id='+data+' ng-click="getCheckedIds(\''+data+'\','+meta.row+')" class="checkboxes"  id="'+data+'" value="'+row.supplyMateriels[0].serialNum+'" data-set="#select_sample_2 .checkboxes" />'+
					                                     '<span></span>'+
					                                 '</label>';
					
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
				  							}},{
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
					         * 选择物料页面弹出
					         */
					    	$scope.addMateriel = function (type,index){
					    		$("#basicMaterielInfo").modal("show");
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
					    	 * 选择物料并展示在列表
					    	 */
					    	$scope.confirmSelect = function(){
					    		
						    		if($scope.serialNums.length==0){ //判断是否选择了物料
					    				toastr.warning("请选择物料");
										return;
					    			}
					    		
					    			//--------批量增加物料信息START--------------
					        		handle.blockUI();
				        			if(isNull($scope.takeDeliveryMateriels)||$scope.takeDeliveryMateriels.length==0){//如果需求物料列表为空
				        				$scope.takeDeliveryMateriels = [];
				        				for(var i = 0;i < $scope.serialNums.length;i++){ //将选中物料放入列表，并设置为编辑状态
				        					var param = {};
				        					param.orderMateriel = ($scope.serialNums)[i];
				        				//	($scope.serialNums)[i].serialNum = ($scope.serialNums)[i].materiel.supplyMaterielSerial; //存放供应物料流水
				        					param.supplyMaterielSerial = ($scope.serialNums)[i].materiel.supplyMaterielSerial;
				        					param.serialNum = ($scope.serialNums)[i].materiel.supplyMaterielSerial; //存放供应物料流水
				        					$scope.takeDeliveryMateriels.push(param);
				        					$scope["deliveryMaterielEdit"+i] = false;
				        					$scope["deliveryMaterielView"+i] = false;
				        				}
				        			}else{
						        		for(var i = 0;i < $scope.serialNums.length;i++){
						        			var param = {};
				        					param.orderMateriel = ($scope.serialNums)[i];
				        					param.supplyMaterielSerial = ($scope.serialNums)[i].materiel.supplyMaterielSerial;
						        			//($scope.serialNums)[i].serialNum = ($scope.serialNums)[i].materiel.supplyMaterielSerial; //存放供应物料流水
				        					param.serialNum = ($scope.serialNums)[i].materiel.supplyMaterielSerial; //存放供应物料流水
					        				$scope.takeDeliveryMateriels.splice(0,0,param); //将选中物料放入列表开头，并设置为编辑状态
					        				$scope["deliveryMaterielEdit"+i] = false;
											$scope["deliveryMaterielView"+i] = false;
											$scope["deliveryMaterielEdit" + ($scope.takeDeliveryMateriels.length-1)] = true;
											$scope["deliveryMaterielView" + ($scope.takeDeliveryMateriels.length-1)] = true;
						        		}
				        				//之前的物料显示状态需要维持原状，以下添加代码
						        		
				        			}
				        			//$scope.countSupplyCount();
				        			$scope.copyMateriels = angular.copy($scope.takeDeliveryMateriels);//复制需求物料列表，以便撤销
				        			$("#basicMaterielInfo").modal("hide");
				        			toastr.success("添加成功！");
				        			handle.unblockUI();
					    	}
					    	
					    	//关闭物料列表时，清除选中状态START--------------
					    	 $('#basicMaterielInfo').on('hide.bs.modal', function (e) { 
					    		 clearChecked();
					    		 $scope.serialNums=[];
						     })
					    	
						     /**
						      * 清除选择状态
						      */
					    	function clearChecked(){
					    		m_table.$('input[type="checkbox"]').each(
										function() {
											// If checkbox exist in DOM
											if ($.contains(document, this)) {
												// If checkbox is checked
												this.checked = false;
												$(this).data("checked",false);
											}
								});
					    	}
					    	//关闭物料列表时，清除选中状态END-----------------
					    	 
					    	 
					    	 /**
					 		 * 遍历checkbox,检查并处理已取消的元素
					 		 */
					 		function checkedIdHandler(){
					 			//获取选中物料ID
					 			m_table.$('input[name="material_serial"]').each(function() { //遍历当前页的物料信息
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
					 		

							/**
							 * checkbox点击事件
							 */
							$scope.getCheckedIds = function(serialNum,index){
								var data={};
								data.serialNum = serialNum;
								data.materiel = m_table.row(index).data(); //获取一行数据
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
							
							$scope.setSupplyComId = function(comId){
								$scope.deliver.supplyComId = comId;
							}
				         
				          /***选择物料列表初始化END***/
							
							
						/** *************入库物料明细可检索化  start*************** */
							 $scope.pageIndex = 1; //记录当前页
							 $scope.pageSize = '10'; //每页的记录数
							 $scope.totalPage = '1'; //记录总页数
							 $scope.dispalyDeliveryMateriel = [];//页面显示结果
							 $scope.filterDeliveryMateriel = [];//查询筛选结果
							 
							 $scope.createFilterList = function(){
								 $scope.filterDeliveryMateriel = [];
								if($scope.record.delivery.deliveryMateriels.length>0&&$scope.queryStr&&!isNull($scope.queryStr)){
									for(var i = 0;i < $scope.record.delivery.deliveryMateriels.length;i++){// data.data为选择的标准物料
										if(($scope.record.delivery.deliveryMateriels)[i].orderMateriel.materiel.materielNum.indexOf($scope.queryStr)>=0){
											$scope.filterDeliveryMateriel.push(angular.copy(($scope.record.delivery.deliveryMateriels)[i]));
										}else if(($scope.record.delivery.deliveryMateriels)[i].orderMateriel.materiel.materielName.indexOf($scope.queryStr)>=0){
											$scope.filterDeliveryMateriel.push(angular.copy(($scope.record.delivery.deliveryMateriels)[i]));
										}else if(($scope.record.delivery.deliveryMateriels)[i].orderMateriel.materiel.specifications.indexOf($scope.queryStr)>=0){
											$scope.filterDeliveryMateriel.push(angular.copy(($scope.record.delivery.deliveryMateriels)[i]));
										}
									}
								}else{
									$scope.filterDeliveryMateriel = angular.copy($scope.record.delivery.deliveryMateriels);
								}
								
							 };
							 
							 $scope.createDispalyList = function(){
								 $scope.dispalyDeliveryMateriel = $scope.filterDeliveryMateriel.slice(
										 ($scope.pageIndex-1)*$scope.pageSize,
										 $scope.pageIndex*$scope.pageSize);
								 
								 $scope.totalPage = Math.ceil($scope.filterDeliveryMateriel.length/$scope.pageSize);
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
							 
							/** *************入库物料明细可检索化  end*************** */

}]); 
