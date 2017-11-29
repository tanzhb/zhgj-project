/**
 * 
 */

angular.module('MetronicApp').controller('TakeDeliveryController',['$rootScope','$scope','$state','$http','$filter','takeDeliveryService','orderService','$location','$compile','$stateParams','commonService','FileUploader',
                                                                   function($rootScope,$scope,$state,$http,$filter,takeDeliveryService,orderService,$location,$compile,$stateParams,commonService,FileUploader) {
	 $scope.$on('$viewContentLoaded', function() {   
	    	// initialize core components
		    handle = new pageHandle();
	    	App.initAjax();
	    	handle.datePickersInit("auto bottom");
	    	$scope.oprateType=$stateParams.oprateType;
	    	$scope.type=$stateParams.type;
	    	if($location.path()=="/takeDeliveryAdd"){
	    		
	    		//handle.pageRepeater();
	    		//_index = 0; 
	    		//$scope.companyQualifications =[{}];
	    		//$scope.rootMateriels = [];
	    		//$scope.serialNums = [];
	    		//getDemandPlanInfo($stateParams.serialNum);
	    		
	    		//selectParentMateriel();
	    		
	    		//initOrders();
	    		initSuppliers(); //加载供应商
	    		//initWarehouse(); //加载仓库
	    		initCustomers(); //加载客户
	    		getCurrentUser(); //加载当前用户信息
	    		validatorInit();//验证初始化
	    		loadOrderTable();//加载订单列表
	    		setDefualtData();//设置初始值
	    		$scope.serialNums = [];	
	    		if(isNull($stateParams.serialNum)){
	    			$rootScope.setNumCode("SE",function(newCode){
		    			$scope.deliver.deliverNum = newCode;
		    			$scope.deliver.approvalDate=$filter('date')(new Date(), 'yyyy-MM-dd');
		    		});
	    		}
	    		if(!isNull($stateParams.serialNum)){
	    			$(".d_tip").text("编辑代发货信息");
	    			takeDeliveryInfo($stateParams.serialNum,"edit");
	    		}
	    		playArrivalDateSetting();
	    		playWarehouseDateSetting();
	 		}else if($location.path()=="/takeDeliveryView"||$location.path()=="/toTakeDelivery"||$location.path()=="/takeDeliveryAudit"||$location.path()=="/takeDeliveryAdjustment"){
	 			
	 			takeDeliveryInfo($stateParams.serialNum,"edit",$stateParams.taskId, $stateParams.comments);
	 				getCurrentUser();
	 				//setDefualtData('takeDelivery');//设置初始值
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
	 			
	 			 if($stateParams.tabHref == '1'){//首页待办列表传过来的参数
	 				$('#takeDelivery_tab a[data-target="#tab_25_2"]').tab('show');
	 				//showDbTable();
	 				 $scope.toDaiban();
	 	 		 }else if($stateParams.tabHref == '2'){
	 	 			    $('#takeDelivery_tab a[data-target="#tab_25_3"]').tab('show');
	 	 			 	$scope.toYiban();
	 	 		 }
	 			 loadStockInTable();
	 			 loadTakeDelieryTable();
	 			 $("#sample_2").on("change", "tbody tr .checkboxes",
				         function() {
				            $(this).parents("tr").toggleClass("active");
				            $(this).parents("tr").siblings().removeClass("active");
				            $(this).parents("tr").siblings().find("input").checked = false;
				 });
	 			
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
			 * 加载供应商数据
			 */
			var initSuppliers = function(){
			var promise = commonService.initSuppliers();
			promise.then(function(data){
				$scope.suppliers = data.data;
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
			 * 加载当前用户信息
			 */
			var getCurrentUser = function(){
				var promise = commonService.getCurrentUser();
				promise.then(function(data){
					$scope.user = data.data;
					if($location.path()=="/toTakeDelivery"){
						$scope.takeDeliver.taker = data.data.userName;
					}else if($location.path()=="/takeDeliveryAdd"){
						$scope.deliver.maker = data.data.userName;
					}
					
				},function(data){
					//调用承诺接口reject();
				});
			}
			
			/**
			 * 加载仓库数据
			 */
			var initWarehouse = function(judgeString,comId,index){
			var promise = takeDeliveryService.initWarehouse(judgeString,comId);
			promise.then(function(data){
//				$scope.warehouses = data.data;
				if(index=="out"){
					$scope.warehouselistf = data;
					setTimeout(function () {
		       			$("#dWarehouseSerialnum").selectpicker({
		                       showSubtext: false
		                   });
		       		$('#dWarehouseSerialnum').selectpicker('refresh');//刷新插件
		       			            }, 100);
				}else{
					$scope.warehouselists = data;
					setTimeout(function () {
						$("#warehouseSerialnum").selectpicker({
		                    showSubtext: false
		                });
		    			$('#warehouseSerialnum').selectpicker('refresh');
		       			            }, 100);
					
				}
				
			},function(data){
				//调用承诺接口reject();
			});
			}
			
			var setDefualtData = function(type){
				
				/*$scope.deliver.makeDate = timeStamp2ShortString(new Date());*/
				$scope.deliver.deliverDate = timeStamp2ShortString(new Date());
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
	        	$state.go("takeDeliveryView",{serialNum:serialNum,oprateType:$stateParams.oprateType});
	        }
	        
	        /**
	         * 查看收货详情
	         */
	        var takeDeliveryInfo = function(serialNum,type,taskId,comments){
	        	var promise = takeDeliveryService.getTakeDeliveryInfo(serialNum);
	        	promise.then(function(data){
	        	$scope.deliver = data.data;
	        	$scope.stockInOutRecord=data.data.stockInOutRecord;
	        	if(data.data.warehouse != null){
		        	$scope.deliver.warehouseSerial = data.data.warehouse.serialNum;
		        	$scope.deliver.warehouseName = data.data.warehouse.address;
	        	}
	        	/*if($scope.deliver.takeDelivery.warehouse==null){
	        		$scope.deliver.takeDelivery.warehouse={};
	        		$scope.deliver.takeDelivery.warehouse.warehouseName='无';
	        	}
	        	if($scope.deliver.warehouse==null){
	        		$scope.deliver.warehouse={};
	        		$scope.deliver.warehouse.warehouseName='无';
	        	}*/
	        	if(isNull($scope.deliver.receiver)){
	        		$scope.deliver.receiver = "中航能科（上海）能源科技有限公司";
	        	}
	        	if(type=="edit"){
	        		$scope.deliverTransport = data.data.deliveryTransport;
	        		$scope.orderMateriels = data.data.deliveryMateriels;
	        		if($state.current.name=="takeDeliveryView"){//查看页面构造物料查询分页
      		    		$scope.queryForPage();
      		    		$scope.materielCount=$scope.orderMateriels.length;//物料条目数
      		    	}
	        		initWarehouse("pt",$scope.deliver.supplyComId,"out");
					initWarehouse("pt",$scope.deliver.buyComId,"in");
	        		
	        		$scope.takeDeliver = data.data.takeDelivery;
	        		if($scope.takeDeliver.warehouse != null){
	        			$scope.takeDeliver.warehouseSerial = $scope.takeDeliver.warehouse.serialNum;
		        		$scope.takeDeliver.warehouseName = $scope.takeDeliver.warehouse.address;
	        		}
	        		var totalOrderCount=0, totalDeliveryCount=0;
	        		var  totalQualifiedCount=0,totalStockInCount=0,totalUnstockInCount=0;
	        		for(var i in data.data.deliveryMateriels){
	        			if(data.data.deliveryMateriels[i].orderMateriel!=null){
	        				$scope.orderMateriels[i].materiel = data.data.deliveryMateriels[i].orderMateriel.materiel;
		        			$scope.orderMateriels[i].amount = data.data.deliveryMateriels[i].orderMateriel.amount;
		        			$scope.orderMateriels[i].serialNum = data.data.deliveryMateriels[i].serialNum;
		        			$scope.orderMateriels[i].orderMaterielSerial = data.data.deliveryMateriels[i].orderMateriel.serialNum;
		        			totalOrderCount=totalOrderCount+Number( data.data.deliveryMateriels[i].orderMateriel.amount);
		        			totalDeliveryCount=totalDeliveryCount+Number( data.data.deliveryMateriels[i].deliverCount);
		        			if($scope.oprateType==undefined){//入库计划详情物料tab展示合格总数,入库总数,未入总数
		        				totalQualifiedCount=totalQualifiedCount+Number( data.data.deliveryMateriels[i].stockInQualifiedCount);
		        				totalStockInCount=totalStockInCount+Number( data.data.deliveryMateriels[i].stockInCount);
		        				totalUnstockInCount=totalUnstockInCount+Number( data.data.deliveryMateriels[i].unstockInCount);
			        		}
	        			}else{
	        				//$scope.orderMateriels[i].orderMateriel = {};
	        				if(data.data.deliveryMateriels[i].supplyMateriel!=null){
	        					data.data.deliveryMateriels[i].supplyMateriel.supplySerialNum = data.data.deliveryMateriels[i].supplyMateriel.serialNum; //供应物料流水保存到新属性中
	        					$scope.orderMateriels[i].orderMaterielSerial = data.data.deliveryMateriels[i].supplyMateriel.serialNum;
	        				}
	        				data.data.deliveryMateriels[i].supplyMateriel.serialNum = '';
	        				$scope.orderMateriels[i].orderMateriel = data.data.deliveryMateriels[i].supplyMateriel;
		        			//$scope.orderMateriels[i].amount = data.data.deliveryMateriels[i].supplyMateriel.amount;
		        			$scope.orderMateriels[i].serialNum = data.data.deliveryMateriels[i].serialNum;
		        			
	        			}
	        			
	        			
	        		}
	        		$scope.totalDeliveryCount=totalDeliveryCount;//发货总数
	        		$scope.totalOrderCount=totalOrderCount;//订单总数
	        		if($scope.oprateType==undefined){//入库计划详情物料tab展示合格总数,入库总数,未入总数
	        			$scope.totalQualifiedCount=totalQualifiedCount;//合格总数
		        		$scope.totalStockInCount=totalStockInCount;//入库总数
		        		$scope.totalUnstockInCount=totalUnstockInCount;//未入总数
	        		}
	        		/*var playWarehouseDate= $scope.deliverTransport.playWarehouseDate;
	    		    if(!isNull(playWarehouseDate)){
	    		    	$("#playWarehouseDate").datepicker('setDate',playWarehouseDate);
	    		    	$("#playArrivalDate").datepicker('setEndDate',playWarehouseDate);
	    		    }
	    		    var playArrivalDate= $scope.deliverTransport.playArrivalDate;
	    		 	if(!isNull(playArrivalDate)){
	    		 		$("#playArrivalDate").datepicker('setDate',playArrivalDate);
	    		    	$("#playWarehouseDate").datepicker('setStartDate',playArrivalDate);
	    		    }*/
	    		 	if($location.path()=="/toTakeDelivery"){
	    		 		$scope.takeDeliver.actualDate = timeStamp2ShortString(new Date());
	    		 		$scope.takeDeliver.taker = $scope.user.userName;
	    		 		$rootScope.setNumCode("RE",function(newCode){
	    		 			$scope.takeDeliver.takeDeliverNum = newCode;
	    				});
	    		 	}
	        	}
	        	if(!isNull(taskId)){
	        		$("#serialNum").val(serialNum);//赋值给隐藏input，通过和不通过时调用
					$("#taskId").val(taskId);//赋值给隐藏input，通过和不通过时调用
					
					if(comments == ""){
						$("#comment_audit").html( "无评论");
					}else $("#comment_audit").html(comments);
	        	}
	        	},function(data){
	        		//调用承诺接口reject();
	        	});
	        }
	        
	      //编辑代发货信息
	    	$scope. editDeliveryInfo=function(){
	    		$scope.deliverAdd=false;
				$scope.deliverView=false;
	    		
	    	}
	    	
	        /**
	         * 确认代发货
	         */
			$scope.saveTakeDelivery = function(number) {
				if($('#takeDeliveryForm').valid()){
					handle.blockUI();
					var params = {};
					if(number==0){//保存代发货
						$scope.deliver.status=0;
					}else{
						$scope.deliver.status=1;
					}
					params.delivery = $scope.deliver;
					params.deliveryTransport = $scope.deliverTransport;
					params.takeDelivery = $scope.takeDeliver;
					params.deliveryMateriels = [];
					var param
					for(var i=0;i < $scope.orderMateriels.length;i++){
						param = {};
						if(!isNull($scope.orderMateriels[i].supplyMaterielSerial)){
							param.supplyMaterielSerial = $scope.orderMateriels[i].supplyMaterielSerial;
							param.orderMaterielSerial = '';
						}else if(!isNull($scope.orderMateriels[i].orderMateriel)){
							param.orderMaterielSerial = $scope.orderMateriels[i].orderMateriel.serialNum;
						}else{
							param.orderMaterielSerial = $scope.orderMateriels[i].serialNum;
						}
						param.batchNum = $scope.orderMateriels[i].batchNum;
						param.manufactureDate = $scope.orderMateriels[i].manufactureDate;
						param.deliverCount = $scope.orderMateriels[i].deliverCount;
						param.deliverRemark = $scope.orderMateriels[i].deliverRemark;
						param.acceptCount = $scope.orderMateriels[i].acceptCount;
						param.refuseCount = $scope.orderMateriels[i].deliverCount-$scope.orderMateriels[i].acceptCount;
						param.takeRemark = $scope.orderMateriels[i].takeRemark;
						param.attachFile = $("#batchNumReal"+i).text();
						params.deliveryMateriels.push(param);
					}
					
					var promise = takeDeliveryService
							.saveTakeDelivery(params);
					promise.then(function(data) {
						if(number==0){
							toastr.success("保存代发货成功！");
							$scope.deliverTransport=data.data.deliveryTransport;
							$scope.takeDeliver=data.data.takeDelivery ;
							$scope.deliver=data.data.delivery;
							$scope.deliverAdd=true;
							$scope.deliverView=true;
						}else{toastr.success("代发货成功！");
						$state.go("buyOrder");
						}
						/*if(data.data == "1"){
							if(number==0){
								toastr.success("保存代发货成功！");
								$scope.deliverAdd=true;
								$scope.deliverView=true;
							}else{toastr.success("代发货成功！");
							$state.go("buyOrder");
							}
							
						}else{
							toastr.error("代发货失败！请联系管理员");
						}*/
						handle.unblockUI();
					}, function(data) {
						// 调用承诺接口reject();
						handle.unblockUI();
						toastr.error("代发货失败！请联系管理员");
						console.log(data);
					});
				}
			}; 
			//确认代发货.
			$scope.confirmDelivery= function() {
				var promise = takeDeliveryService.confirmDelivery($scope.deliver.serialNum);
				promise.then(function(data) {
					if(data= "0"){
						toastr.success("确认代发货成功！");
						$scope.deliver.status=1;
					}else{
						toastr.error("确认代发货.失败！请联系管理员");
					}
				}, function(data) {
					// 调用承诺接口reject();
					toastr.error("确认代发货.失败！请联系管理员");
					console.log(data);
				});
			}
			/**
			 * 确认收货
			 */
			$scope.confirmTakeDelivery = function() {
				if($('#takeDeliveryForm').valid()){
					handle.blockUI();
					var params = {};
					params.takeDelivery = {};
					params.delivery = {};
					params.takeDelivery.deliverSerial =  $scope.deliver.serialNum;
					params.takeDelivery.serialNum = $scope.deliver.takeDelivery.serialNum;
					params.takeDelivery.takeDeliverNum = $scope.takeDeliver.takeDeliverNum;
					params.takeDelivery.actualDate = $scope.takeDeliver.actualDate;
					params.takeDelivery.taker = $scope.takeDeliver.taker;
					params.takeDelivery.takeRemark = $scope.takeDeliver.takeRemark;
					params.delivery.supplyComId = $scope.deliver.supplyComId;
					params.delivery.deliverNum = $scope.deliver.deliverNum;
					params.deliveryMateriels = [];
					var param;
					for(var i=0;i < $scope.deliver.deliveryMateriels.length;i++){
						param = {};
						if(isNull($scope.deliver.deliveryMateriels[i].orderMateriel.serialNum)){
							param.supplyMaterielSerial = $scope.deliver.deliveryMateriels[i].orderMateriel.supplySerialNum;
							param.orderMaterielSerial = '';
						}else{
							param.orderMaterielSerial = $scope.deliver.deliveryMateriels[i].orderMateriel.serialNum;
						}
						
						param.serialNum = $scope.deliver.deliveryMateriels[i].serialNum;
						param.batchNum = $scope.deliver.deliveryMateriels[i].batchNum;
						param.manufactureDate = $scope.deliver.deliveryMateriels[i].manufactureDate;
						param.deliverCount = $scope.deliver.deliveryMateriels[i].deliverCount;
						param.deliverRemark = $scope.deliver.deliveryMateriels[i].deliverRemark;
						param.acceptCount = $scope.deliver.deliveryMateriels[i].acceptCount;
						param.refuseCount = $scope.deliver.deliveryMateriels[i].deliverCount-$scope.deliver.deliveryMateriels[i].acceptCount;
						param.takeRemark = $scope.deliver.deliveryMateriels[i].takeRemark;
						param.attachFile = $("#batchNumReal"+i).text();
						params.deliveryMateriels.push(param);
					}
					
					var promise = takeDeliveryService
					.saveConfirmTakeDelivery(params);
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
				if($location.path()=="/takeDeliveryAdd"){
					$state.go("buyOrder");
				}else{
					$state.go("takeDelivery");
				}
				
			}
			
			$scope.getWarehouseName = function(type){
				for(var i in $scope.warehouses){
					if(type=="deliver"){
						if($scope.deliver.warehouseSerial=='无'){
							$scope.deliver.deliverAddress = '';
							return;
						}
						if($scope.warehouses[i].serialNum == $scope.deliver.warehouseSerial){
							$scope.deliver.deliverAddress = $scope.warehouses[i].address;
						}
					}else{
						if($scope.takeDeliver.warehouseSerial=="无"){
							$scope.takeDeliver.takeDeliverAddress = '';
							return;
						}
						if($scope.warehouses[i].serialNum == $scope.takeDeliver.warehouseSerial){
							$scope.takeDeliver.takeDeliverAddress = $scope.warehouses[i].address;
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
					$state.go("takeDeliveryAdd",{serialNum:serialNum,type:'edit'});
				}
			}
			
	        		
			/**
			 * 去收货
			 */
			$scope.takeDelivery = function(){
				var id_count = $('#takeDeliveryTable input[name="serialNum"]:checked').length;
				if(id_count==0){
					toastr.warning("请选择您要收货的记录");
				}else if(id_count>1){
					toastr.warning("只能选择一条数据进行收货");
				}else{
					if(table.row('.active').data().status != '0' && table.row('.active').data().status < 1){
						showToastr('toast-top-center', 'warning', '该收货单已经申请流程审批，不能进行再次收货！');
					}else if(table.row('.active').data().status == 4){
						showToastr('toast-top-center', 'warning', '该收货单已经收货完成！');
					}else if(table.row('.active').data().status > 2 || table.row('.active').data().status == 1){
						showToastr('toast-top-center', 'warning', '该收货单已经进入检验入库流程，不能进行再次收货！');
					}else{
						var serialNum = $('#takeDeliveryTable input[name="serialNum"]:checked').val();
						$state.go("toTakeDelivery",{serialNum:serialNum});
					}
				}
			}
			
			/**
			 * 入库计划中做入库操作
			 */
			$scope.takeDeliveryStockIn = function(){
				var id_count = $('#takeDeliveryTable input[name="serialNum"]:checked').length;
				if(id_count==0){
					toastr.warning("请选择您要入库的记录");
				}else if(id_count>1){
					toastr.warning("只能选择一条数据进行收货");
				}else{
					var row = table.row(".active").data();
					if(table.row('.active').data().status != 3){
						showToastr('toast-top-center', 'warning', '未处于待入库操作状态！');
					}else{
						//查找入库记录单流水
						var promise = takeDeliveryService
						.findStockInSerialNum(row.takeDelivery.serialNum);
						promise.then(function(data) {
							$state.go("stockIn",{serialNum:data.data.serialNum});
						}, function(data) {
							toastr.error("操作失败！请联系管理员");
						});
						
					}
				}
			}
			
			
	        /**
	         * 批量删除收货计划
	         */
	        $scope.takeDeliveryDelete = function () {
	        	var id_count = $('#takeDeliveryTable input[name="serialNum"]:checked').length;
				if(id_count==0){
					toastr.warning("请选择您要删除的记录");
					return;
				}
				/*if(table.row('.active').data().status > 2){
					showToastr('toast-top-center', 'warning', '存在已经进入流程审批的收货单，不能删除！');
					return;
				}*/
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
	         
	        /**
	         * 入库编辑
	         */
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
	         * 入库
	         */
	        $scope.stockIn = function(){
	        	var id_count = $('#stockInTable input[name="serialNum2"]:checked').length;
	        	if(id_count==0){
	        		toastr.warning("请选择您要入库的记录");
	        	}else if(id_count>1){
	        		toastr.warning("只能选择一条数据进行入库");
	        	}else{
	        		var row = stock_table.row(".active").data();
		       		if(row.stockInOutRecord.status=="1"){
		       			toastr.warning("该收货单已入库！");
		       			return;
		       		}
	        		var serialNum = $('#stockInTable input[name="serialNum2"]:checked').val();
	        		$state.go("stockIn",{serialNum:row.stockInOutRecord.serialNum});
	        	}
	        }
	       	
	        /**
		        * 初始化日期控件
		        */
		       $scope.repeatDone = function(scope){
		       		var date= scope.materiel.manufactureDate;
		    	    handle.datePickersInit();
		    	    scope.materiel.manufactureDate = date;
		    	    if(isNull($scope.deliver.serialNum)){
		  				scope.materiel.deliverCount = scope.materiel.amount;
		  			}
		    	    //$("#manufactureDate"+index).datepicker('setDate',date);
		    };
		    
		    /**
		     * 设置收货默认收货数量
		     */
		    $scope.setDefualtNum = function(scope){
		    	//if(isNull($scope.deliver.serialNum)){
	  				scope.materiel.acceptCount = scope.materiel.deliverCount;
	  			//}
		    }
	        
	        /**
		        * 导出入库记录
		        */
		    $scope.exportStockIn = function(){
			    	 window.location.href=$rootScope.basePath+"/rest/takeDelivery/exportStockIn";
			}
		    
		    
		    $('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
		        // 获取已激活的标签页的名称
		        var activeTab = $(e.target).text(); 
		        // 获取前一个激活的标签页的名称
		       // var previousTab = $(e.relatedTarget).text(); 
		        var absurl = $location.absUrl();
		        $("#tip").text(activeTab);
		        if(activeTab=="入库记录"){
		        	handle.addCookie("d_type","stockIn",24);
		        }else if(activeTab=="收货计划"){
		        	handle.addCookie("d_type","takeDeliver",24);
		        	$("#buttons").show();
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
//	                    serverSide: true,
	                   ajax: "rest/takeDelivery/takeDeliveryList?noInit=1",//加载数据中
	                   /* ajax :{ "url":$rootScope.basePath
	  						+ "/rest/takeDelivery/takeDeliveryList",// 加载数据中user表数据    
	  						"contentType": "application/json",
	  					    "type": "POST",
	  					    "data": function ( d ) {
	  					      return JSON.stringify( d );
	  					    }},*/
	                    "aoColumns": [
	                                  { mData: 'takeDelivery.serialNum' },
	                                 /* { mData: 'takeDelivery.takeDeliverNum' },*/
	                                  { mData: 'inOutNum' },
	                                  { mData: 'inOutType'},
	                                  { mData: 'orderNum' },
	                                  { mData: 'shipper' },
	                                  { mData: 'inOutPackageCount' },
	                                  //{ mData: 'materielCount' },物料条目数
			                          { mData: 'materielTotalCount' },//物料总数
	                                  { mData: 'deliverDate' },
	                                  { mData: 'packageType' },
	                                 { mData: 'deliverAddress' },
	                                 { mData: 'inOutRemark' },
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
	    										/*if(data==null){
	    											data="未收货";
	    										}debugger;
	  	  	  								return '<a href="javascript:void(0);" ng-click="takeDeliveryView(\''+row.takeDelivery.serialNum+'\')">'+data+'</a>';*/
	    								/*return '<a href="javascript:void(0);" ng-click="stockInView(\''+row.inOutSerial+'\')">'+data+'</a>';*/
	    								return '<a href="javascript:void(0);" ng-click="takeDeliveryView(\''+row.takeDelivery.serialNum+'\')">'+data+'</a>';
	    							},
	    							"createdCell": function (td, cellData, rowData, row, col) {
	    								 $compile(td)($scope);
	    						       }
	    						},{
	    							'targets' : 3,
	    							'render' : function(data,
	    									type, row, meta) {
	    									if(!isNull(data)){
	    										return data;
	    									}
	  	  								return row.docNum;
	  	
	    							}
	    						},{
	    							'targets' : 11,
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
	    							'targets' : 12,
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
	  				//console.log(----------------");
	  				//console.log($(element));
	  			    return this.optional(element) || Number(element.dataset.ordercount)-value >= 0;
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
	  						            	/*dWarehouseSerial:{required:"发货仓库不能为空！"},*/
	  						            	deliverDate:{required:"发货日期不能为空！"},
	  						            	materielCount:{required:"物料数不能为空！"},
	  						            	packageCount:{required:"包装件数不能为空！",digits:"包装件数必须为数字！"},
	  						            	packageType:{required:"包装类型不能为空！"},
	  						            	packageSpecifications:{required:"包装规格不能为空！"},
	  						            	materielWeight:{required:"物料重量不能为空！"},
	  						            	serviceMoney:{required:"服务费不能为空！",digits:"包装件数必须为数字！"},
	  						            	deliverer:{required:"联系人不能为空！"},
	  						            	dContactNum:{required:"联系电话不能为空！"},
	  						            	transportType:{required:"运输方式不能为空！"},
	  						            	/*transport:{required:"运输方不能为空！"},*/
	  						            	port:{required:"港口不能为空！"},
	  						            	deliverAddress:{required:"发货地址不能为空！"},
	  						            	takeDeliverAddress:{required:"收货地址不能为空！"},
	  						            	/*shipNumber:{required:"运单号不能为空！"},*/
	  						            	playArrivalDate:{required:"预计到港日期不能为空！"},
	  						            	playWarehouseDate:{required:"预计到库日期不能为空！"},
	  						            	dtContact:{required:"联系人不能为空！"},
	  						            	dtContactNum:{required:"联系电话不能为空！"},
	  						            	/*warehouseSerial:{required:"收货仓库不能为空！"},*/
	  						            	takeDeliverDate:{required:"收货日期不能为空！"},
	  						            	tdReceiver:{required:"联系人不能为空！"},
	  						            	tdContactNum:{required:"联系电话不能为空！"},
	  						            	batchNum:{required:"批次号不能为空！"},
	  						            	manufactureDate:{required:"生产日期不能为空！"},
	  						            	deliverCount:{required:"发货数量不能为空！",digits:"发货数量必须为数字！"},
	  						            	acceptCount:{required:"实收数量不能为空！",digits:"实收数量必须为数字！"},
	  						            	actualDate:{required:"实际收货日期不能为空！"},
	  						            	taker:{required:"收货人不能为空！"}
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
	  						               /* dWarehouseSerial: {
	  						                	required: !0
	  						                },*/
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
	  						                	//required: !0,
	  						                	isPhone: !0
	  						                },
	  						                transportType: {
	  						                	required: !0
	  						                },
	  						               /* transport: {
	  						                	required: !0
	  						                },*/
	  						                port: {
	  						                	required: !0
	  						                },
	  						              deliverAddress:{
	  						                	required: !0
	  						                },
	  						            	takeDeliverAddress:{
	  						                	required: !0
	  						                },
	  						             /*   shipNumber: {
	  						                	required: !0
	  						                },*/
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
	  						                	//required: !0,
	  						                	isPhone: !0
	  						                },
	  						               /* warehouseSerial: {
	  						                	required: !0
	  						                },*/
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
	  						                	//required: !0,
	  						                	isPhone: !0
	  						                },
	  						                actualDate: {
	  						                	required: !0
	  						                },
	  						                taker: {
	  						                	required: !0
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
	  						            	/*if($(e).className.indexOf("bs-select1 form-control order")>-1){
	  						            		if($scope.deliver.warehouseSerial==undefined){
	  						            			return;
	  						            		}
	  						            	}
	  						            	if($(e).className.indexOf("bs-select2 form-control order")>-1){
	  						            		if($scope.takeDeliver.warehouseSerial==undefined){
	  						            			return;
	  						            		}
	  						            	}*/
	  						                $(e).closest(".form-group").removeClass("has-error")
	  						            },
	  						            success: function(e) {
	  						            	/*if($(e).className.indexOf("bs-select1 form-control order")>-1){
	  						            		if($scope.deliver.warehouseSerial==undefined){
	  						            			return;
	  						            		}
	  						            	}
	  						            	if($(e).className.indexOf("bs-select2 form-control order")>-1){
	  						            		if($scope.takeDeliver.warehouseSerial==undefined){
	  						            			return;
	  						            		}
	  						            	}*/
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
	  		                                  { mData: 'stockInOutRecord.inOutType' },
	  		                                  { mData: 'orderMateriel'},
	  		                                  { mData: 'orderMateriel'},
	  		                                  { mData: 'stockInOutRecord.stockDate' },
	  		                                  { mData: 'stockCount' },
	  		                                  { mData: 'batchNum' },
	  		                                  { mData: 'stockInOutRecord.shipperOrReceiverName' },
	  		                                  { mData: 'delivery' },
	  		                                  { mData: 'stockInOutRecord.status' }
	  		                            ],
	  		                   'aoColumnDefs' : [ {
	  		    							'targets' : 0,
	  		    							'searchable' : false,
	  		    							'orderable' : false,
	  		    							'className' : 'dt-body-center',
	  		    							'render' : function(data,
	  		    									type, row, meta) {
	  		  	  	  								/*return '<input  type="checkbox" id='+data+'   name="serialNum2" value="'
	  		  											+ $('<div/>')
	  		  													.text(
	  		  															data)
	  		  													.html()
	  		  											+ '">';*/
	  		  	  	  						return '<label class="mt-checkbox mt-checkbox-single mt-checkbox-outline">'+
		                                     '<input type="checkbox" name="serialNum2" class="checkboxes" id="'+data+'" value="'+data+'" data-set="#stockInTable .checkboxes" />'+
		                                     '<span></span></label>';
	  		  	
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
	  		    							'targets' : 3,
	  		    							'render' : function(data,
	  		    									type, row, meta) {
	  		    								
	  		    								if(data==null){
	  		    									return "";
	  		    								}else if(data.materiel!=null){
	  		    									return data.materiel.materielName;
	  		    								}
	  		    								
	  		    							}
	  		    						},{
	  		    							'targets' : 4,
	  		    							'render' : function(data,
	  		    									type, row, meta) {
	  		    								if(data==null){
	  		    									return "";
	  		    									
	  		    								}else{
	  		    									return data.materiel.specifications;
	  		    								}
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
	  		    							'targets' : 8,
	  		    							'render' : function(data,
	  		    									type, row, meta) {
	  		    									if(isNull(data)){
	  		    										return "";
	  		    									}
	  		  	  								return data;
	  		  	
	  		    							}
	  		    						},{
	  		    							'targets' : 9,
	  		    							'render' : function(data,
	  		    									type, row, meta) {
	  		    									if(isNull(data)||isNull(data.deliverNum)){
	  		    										return row.stockInOutRecord.docNum;
	  		    									}
	  		  	  								return data.deliverNum;
	  		  	
	  		    							}
	  		    						},{
	  		    							'targets' : 10,
	  		    							'render' : function(data,
	  		    									type, row, meta) {
	  		    								if(data=="0"){
	  		  										return "<span  class='label label-sm label-warning ng-scope'>待入库</span>";
	  		  									}else if(data=="1"){
	  		  										return "<span  class='label label-sm label-info ng-scope'>已入库</span>";
	  		  									}
	  		    								return "";
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
	  		              
	  		            $("#stockInTable").find(".group-checkable").change(function() {
				            var e = jQuery(this).attr("data-set"),
				            t = jQuery(this).is(":checked");
				            jQuery(e).each(function() {
				                t ? ($(this).prop("checked", !0), $(this).parents("tr").addClass("active")) : ($(this).prop("checked", !1), $(this).parents("tr").removeClass("active"))
				            })
				        }),
				        $("#stockInTable").on("change", "tbody tr .checkboxes",
				        function() {
				            $(this).parents("tr").toggleClass("active")
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
	  		      var tableAjaxUrl = "rest/order/findOrderList?type=buy&selectFor=delivery";
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
	  		                  order: [[1, "desc"]],// 默认排序列及排序方式
	  		                  searching: true,// 是否过滤检索
	  		                  ordering:  true,// 是否排序
	  		                  lengthMenu: [[5, 10, 15, 30, -1], [5, 10, 15, 30, "All"]],
	  		                  pageLength: 5,// 每页显示数量
	  		                  processing: true,// loading等待框
	  		  // serverSide: true,
	  		                  ajax: tableAjaxUrl,// 加载数据中
	  		                  "aoColumns": [
	  		                              { mData: 'serialNum'},
	  		                              { mData: 'orderNum' },
	  		                              { mData: 'supplyName' },
	  		                              { mData: 'materielCount' },
	  		                              { mData: 'orderAmount' },
	  		                              { mData: 'deliveryMode' },
	  		                              { mData: 'serviceModel' },
	  		                              { mData: 'saleApplySerial' },
	  		                              { mData: 'orderSerial' },
	  		                              { mData: 'orderDate' }/*,
	  		                              { mData: 'processBase',
	  			                            	mRender:function(data){
	  			                            		if(data!=""&&data!=null){
	  			                            			if(data.status=="PENDING"||data.status=="WAITING_FOR_APPROVAL"){
	  			    										return '<span  class="label label-sm label-warning ng-scope">审核中</span>';
	  			    									}else if(data.status=="APPROVAL_SUCCESS"){
	  			    										return '<span  class="label label-sm label-success ng-scope">待接收</span>';
	  			    									}else if(data.status=="APPROVAL_FAILED"){
	  			    										return '<span  class="label label-sm label-danger ng-scope">未通过</span>';
	  			    									}else{
	  			    										return '<span  class="label label-sm label-info ng-scope">未审批</span>';
	  			    									}
	  			                            		}else{
	  			                            			return '<span  class="label label-sm label-info ng-scope">未审批</span>';
	  			                            		}
	  			                            	}
	  			                            } */],
	  				  		                 'aoColumnDefs' : [ {
	  		  		  							'targets' : 0,
	  		  		  							'searchable' : false,
	  		  		  							'orderable' : false,
	  		  		  							'render' : function(data,
	  		  		  									type, row, meta) {
	  			  		  							/*	return '<input type="radio" id="'+data+'" data-num="'+row.orderNum+'" ng-click="getBuyOrderInfo_(\''+data+'\')" name="selecrOrderSerial" value="'
	  			  		  													+ $('<div/>')
	  			  		  													.text(
	  			  		  															data)
	  			  		  													.html()
	  			  		  											+ '">';*/
	  			  		  							return '<label class="mt-radio mt-radio-outline">'+
	  			                                     '<input type="radio"  data-num="'+row.orderNum+'" ng-click="getBuyOrderInfo_(\''+data+'\')" name="selecrOrderSerial"  class="checkboxes" id="'+data+'" value="'+data+'" data-set="#buyOrder .checkboxes" />'+
	  			                                     '<span></span></label>';
	  		  		  							},
	  		  		  							"createdCell": function (td, cellData, rowData, row, col) {
	  		  		  								 $compile(td)($scope);
	  		  		  						       }
	  		  		  						} ]

	  		              }).on('order.dt',
	  		              function() {
	  		                  console.log('排序');
	  		              });
	  		            
	  		          $("#buyOrder").on("change", "tbody tr .checkboxes",
						        function() {
						            $(this).parents("tr").toggleClass("active").siblings().removeClass("active");
						        })
	  		      };
	  		      
	  		$scope.confirmSelectOrder = function(){
	  			var id_count = $('#buyOrder input[name="selecrOrderSerial"]:checked').length;
				if(id_count==0){
					toastr.warning("请选择采购订单");
				}else{
					//var serialNum = $('#buyOrder input[name="selecrOrderSerial"]:checked').val();
					$scope.deliver.orderSerial = $('#buyOrder input[name="selecrOrderSerial"]:checked').val();
					var order = order_table.row('.active').data();
					$scope.deliver.supplyComId = order.supplyComId;
					$scope.deliver.buyComId = order.buyComId;
					//$scope.deliver.shipper = order.supplyComId;
					if(!isNull(order.buyComId)){
						//$scope.deliver.receiver =  order.buyComId;
						$scope.deliver.receiver =  order.buyName;
					}
					initWarehouse("pt",$scope.deliver.supplyComId,"out");
					initWarehouse("pt",$scope.deliver.buyComId,"in");
					
					$scope.deliver.supplyName = order.supplyName;
					$scope.deliver.shipper = order.supplyName;
					if(isNull(order.buyComId)){
						$scope.deliver.receiver = "中航能科（上海）能源科技有限公司";
					}
					
					$scope.deliver.orderNum = $('#buyOrder input[name="selecrOrderSerial"]:checked').data("num");
					$scope.getOrderMateriel();
					$("#buyOrderInfo").modal('hide'); 
				}
				
	  		}  
	  		
	  		
	  		$scope.calcTotalNum = function(){
	  			if(!isNull($scope.orderMateriels)){
	  				$scope.totalDeliverCount = 0;
	  				$scope.totalDeliveryCount = 0;
	  				$scope.totalAmount = 0;
	  				$scope.materielCount = $scope.orderMateriels.length;
	  				$scope.deliver.materielCount = $scope.orderMateriels.length;
	  				for(var i in $scope.orderMateriels){
	  					$scope.totalDeliverCount += handle.formatNumber($scope.orderMateriels[i].deliverCount);
	  					$scope.totalAmount += handle.formatNumber($scope.orderMateriels[i].amount);
	  				}
	  				$scope.totalDeliveryCount=	$scope.totalDeliverCount;
	  			}
	  		}
	  		
	  		/************************************************申请JS***********************************************/
	  		$scope.applyTakeDelivery = function(){
	  			
	  			if($('#takeDeliveryForm').valid()){
					handle.blockUI();
					var params = {};
					params.takeDelivery = {};
					params.takeDelivery.serialNum = $scope.deliver.takeDelivery.serialNum;
					params.takeDelivery.takeDeliverNum = $scope.takeDeliver.takeDeliverNum;
					params.takeDelivery.actualDate = $scope.takeDeliver.actualDate;
					params.takeDelivery.taker = $scope.takeDeliver.taker;
					params.takeDelivery.takeRemark = $scope.takeDeliver.takeRemark;
					params.deliveryMateriels = [];
					var param;
					for(var i=0;i < $scope.deliver.deliveryMateriels.length;i++){
						param = {};
						param.orderMaterielSerial = $scope.deliver.deliveryMateriels[i].orderMateriel.serialNum;
						param.serialNum = $scope.deliver.deliveryMateriels[i].serialNum;
						param.batchNum = $scope.deliver.deliveryMateriels[i].batchNum;
						param.manufactureDate = $scope.deliver.deliveryMateriels[i].manufactureDate;
						param.deliverCount = $scope.deliver.deliveryMateriels[i].deliverCount;
						param.deliverRemark = $scope.deliver.deliveryMateriels[i].deliverRemark;
						/*param.acceptCount = $scope.deliver.deliveryMateriels[i].acceptCount;
						param.refuseCount = $scope.deliver.deliveryMateriels[i].deliverCount-$scope.deliver.deliveryMateriels[i].acceptCount;*/
						param.takeRemark = $scope.deliver.deliveryMateriels[i].takeRemark;
						params.deliveryMateriels.push(param);
					}
					
					var promise = takeDeliveryService
					.applyTakeDelivery(params);
					promise.then(function(data) {
						if(data.data == "1"){
							toastr.success("申请成功！");
							$state.go("takeDelivery");
						}else{
							toastr.error("申请失败！请联系管理员");
						}
						handle.unblockUI();
					}, function(data) {
						// 调用承诺接口reject();
						handle.unblockUI();
						toastr.error("申请失败！请联系管理员");
						console.log(data);
					});
				}
	  		}
	  		
	  		/**
	  		 * 签收
	  		 */
	  		$scope.receiveTakeDelivery = function(){
	  			var id_count = $('#sample_2 input[name="serialNum"]:checked').length;
				if(id_count==0){
					toastr.warning("请选择您要办理的记录");
				}else{
					
					var ids = $('#sample_2 input[name="serialNum"]:checked').val();
					claimTask(ids, 'sample_2');
				}
	  		}
	  		/**
			 * 去收货
			 */
			$scope.takeDeliveryAudit = function(){
				var id_count = $('#sample_2 input[name="serialNum"]:checked').length;
				if(id_count==0){
					toastr.warning("请选择您要办理的记录");
				}else{
					
						var ids = $('#sample_2 input[name="serialNum"]:checked').val();
						takeDeliveryService
						.getAuditInfos(ids)
						.then(
								function(result) {													
									
									var comments = ""//添加评论
									for (var i=0;i<result.commentList.length;i++){
										comments += "<tr><td>" + result.commentList[i].userName + "</td><td>" 
										+ (result.commentList[i].position==null?'':result.commentList[i].position) + "</td><td>"
										+ timeStamp2String(result.commentList[i].time) + "</td><td>" + result.commentList[i].content + "</td></tr>";														
									}
									
									if(result.actionType == 'audit'){//审批流程
										$state.go('takeDeliveryAudit',{serialNum:result.takeDelivery.serialNum,taskId:ids,comments:comments});

									}else{
										$state.go('takeDeliveryAdjustment',{serialNum:result.takeDelivery.serialNum,taskId:ids,comments:comments});
									}
								},
								function(errResponse) {
									toastr.warning("申请失败！");
									console
											.error('Error while apply ap');
								}

						);
					}
					
					//$state.go("takeDeliveryAudit",{serialNum:serialNum});
			}
	  		
	  	    // 待办流程
			$scope.toDaiban = function() {
				$('#takeDelivery_tab a[data-target="#tab_25_2"]').tab('show');

				// 构建datatables开始***************************************
				if(apply_table!=undefined){
					apply_table.ajax.reload();
				}else{
					showDbTable();				
				}
				$("#buttons").hide();
				// 构建datatables结束***************************************

			};
			
			// 以办流程
			$scope.toYiban = function() {
				$('#takeDelivery_tab a[data-target="#tab_25_3"]').tab('show');
				
				// 构建datatables开始***************************************
				if(y_table!=undefined){
					y_table.ajax.reload();
				}else{
					showYbTable();		
				}		
				$("#buttons").hide();
				// 构建datatables结束***************************************
				
			};
			var apply_table;
	  		function showDbTable(){
		  	  
  		      var tableAjaxUrl = ctx + "/rest/processAction/todoTask/"+ 'takeDelivery';// 加载待办列表数据
  		    //  var loadApplyTable = function() {
  		             // a = 0;
  		              //App.getViewPort().width < App.getResponsiveBreakpoint("md") ? $(".page-header").hasClass("page-header-fixed-mobile") && (a = $(".page-header").outerHeight(!0)) : $(".page-header").hasClass("navbar-fixed-top") ? a = $(".page-header").outerHeight(!0) : $("body").hasClass("page-header-fixed") && (a = 64);
  		        return          apply_table = $("#sample_2").DataTable({
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
  		                  order: [[7, "desc"]],// 默认排序列及排序方式
  		                  searching: true,// 是否过滤检索
  		                  ordering:  true,// 是否排序
  		                  lengthMenu: [[5, 10, 15, 30, -1], [5, 10, 15, 30, "All"]],
  		                  pageLength: 5,// 每页显示数量
  		                  processing: true,// loading等待框
  		  // serverSide: true,
  		                  ajax: tableAjaxUrl,// 加载数据中
  		                  "aoColumns": [
  		                                { mData: 'taskId' },
  		                                { mData: 'assign' },
  		                                { mData: 'businessType' },
  		                                { mData: 'userName' },
  		                                { mData: 'title' },
  		                                { mData: 'taskName' },
  		                                { mData: 'owner' },
  		                                { mData: 'createTime' },
  		                                { mData: 'suspended' }
  		                          ],
  		                 'aoColumnDefs' : [ {
  		  							'targets' : 0,
  		  							'searchable' : false,
  		  							'orderable' : false,
  		  						    'className' : 'dt-body-center',
  		  							'render' : function(data,
  		  									type, row, meta) {
  		  								//return '<input type="radio" id="'+data+'"   name="serialNum" value="'+data+ '" />';
  		  						return '<label class="mt-checkbox mt-checkbox-single mt-checkbox-outline">'+
                                '<input type="checkbox" name="serialNum" class="checkboxes"  id="'+data+'" value="'+data+'"  />'+
                                '<span></span></label>';
  		  							}
  		  						},{
  		  							'targets' : 1,
  		  							'searchable' : false,
  		  							'orderable' : false,
  		  							'render' : function(data,
  		  									type, row, meta) {
  		  								if(data=""){
  		  									return "待签收";
  		  								}else{
  		  									return "待处理";
  		  								}
  		  							}
  		  						},{
  		  							'targets' : 2,
  		  							'searchable' : false,
  		  							'orderable' : false,
  		  							'render' : function(data,
  		  									type, row, meta) {
  		  								if (data == "takeDelivery") {
											return "收货申请";
										} else{
											return data;
										}
  		  							}
  		  						},{
  		  							'targets' : 3,
  		  							'searchable' : false,
  		  							'orderable' : false,
  		  							'render' : function(data,
  		  									type, row, meta) {
  		  								if (data == undefined) {
  		  									return "";
  		  								} else {
  		  									return data;
  		  								}
  		  							}
  		  						},{
  		  							'targets' : 4,
  		  							'searchable' : false,
  		  							'orderable' : false,
  		  							'render' : function(data,
  		  									type, row, meta) {
  		  								if (data == undefined) {
  		  									return "";
  		  								} else{
  		  									return data;
  		  								}
  		  							}
  		  						},{
  		  							'targets' : 5,
  		  							'searchable' : false,
  		  							'orderable' : false,
  		  							'render' : function(data,
  		  									type, row, meta) {
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
  		  						},{
  		  							'targets' : 6,
  		  							'searchable' : false,
  		  							'orderable' : false,
  		  							'render' : function(data,
  		  									type, row, meta) {
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
  		  						},{
  		  							'targets' : 7,
  		  							'searchable' : false,
  		  							'orderable' : false,
  		  							'render' : function(data,
  		  									type, row, meta) {
  		  								if (data != null) {
											return timeStamp2String(data);
										} else
											return '';
  		  							}
  		  						},{
  		  							'targets' : 8,
  		  							'searchable' : false,
  		  							'orderable' : false,
  		  							'render' : function(data,
  		  									type, row, meta) {
  		  								if (data) {
											return "已挂起";
										} else {
											return "正常";
										}
  		  							}
  		  						} ]

  		              }).on('order.dt',
  		              function() {
  		                  console.log('排序');
  		              });

			        
  		    //  };
	  		}
	  		
	  		var y_table;
	  		function showYbTable(){
	  			
	  			var tableAjaxUrl = ctx + "/rest/processAction/endTask/" + 'takeDelivery';// 加载待办列表数据
	  			//  var loadApplyTable = function() {
	  			// a = 0;
	  			//App.getViewPort().width < App.getResponsiveBreakpoint("md") ? $(".page-header").hasClass("page-header-fixed-mobile") && (a = $(".page-header").outerHeight(!0)) : $(".page-header").hasClass("navbar-fixed-top") ? a = $(".page-header").outerHeight(!0) : $("body").hasClass("page-header-fixed") && (a = 64);
	  			return y_table = $("#ybTable").DataTable({
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
	  				
	  				buttons : [
	  				           {
	  				        	   text : "办理",
	  				        	   className : "btn default",
	  				        	   action: function(e, dt, node, config) { 
	  				        		   if(table.rows('.selected').data().length == 0){
	  				        			   toastr.warning("请选择要办理的任务！");
	  				        		   }else{
	  				        			   var assign = table.row('.selected').data().assign;
	  				        			   var taskId = table.row('.selected').data().taskId;
	  				        			   var processInstanceId = table.row('.selected').data().processInstanceId;
	  				        			   handleTask(assign, taskId, processInstanceId);
	  				        		   }
	  				        	   }
	  				           },
	  				           {
	  				        	   text : "签收",
	  				        	   className : "btn default",
	  				        	   action: function(e, dt, node, config) { 
	  				        		   if(table.rows('.selected').data().length == 0){
	  				        			   toastr.warning("请选择要签收的任务！");
	  				        		   }else{
	  				        			   var taskId = table.row('.selected').data().taskId;
	  				        			   claimTask(taskId, 'sample_2');
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
	  				           order: [[2, "desc"]],// 默认排序列及排序方式
	  				           searching: true,// 是否过滤检索
	  				           ordering:  true,// 是否排序
	  				           lengthMenu: [[5, 10, 15, 30, -1], [5, 10, 15, 30, "All"]],
	  				           pageLength: 5,// 每页显示数量
	  				           processing: true,// loading等待框
	  				           // serverSide: true,
	  				           ajax: tableAjaxUrl,// 加载数据中
	  				           "aoColumns": [
	  				                        /* { mData: 'taskId' },*/
	  				                         { mData: 'userName' },
	  				                         { mData: 'title' },
	  				                         { mData: 'startTime' },
	  				                         { mData: 'claimTime' },
	  				                         { mData: 'endTime' },
	  				                         { mData: 'deleteReason' },
	  				                         { mData: 'version' },
	  				                         { mData: 'revoke' }
	  				                         ],
	  				                         'aoColumnDefs' : [ /*{
	  				                        	 'targets' : 0,
	  				                        	 'searchable' : false,
	  				                        	 'orderable' : false,
	  				                        	 'className' : 'dt-body-center',
	  				                        	 'render' : function(data,
	  				                        			 type, row, meta) {
	  				                        		 return '<input type="radio" id="'+data+'"   name="serialNum" value="'+data+ '">';
	  				                        	 },
	  				                        	 "createdCell": function (td, cellData, rowData, row, col) {
	  				                        		 $compile(td)($scope);
	  				                        	 }
	  				                         },*/{
	  				                        	 'targets' : 2,
	  				                        	 'searchable' : false,
	  				                        	 'orderable' : false,
	  				                        	 'render' : function(data,
	  				                        			 type, row, meta) {
	  				                        		return timeStamp2String(data);
	  				                        	 }
	  				                         },{
	  				                        	 'targets' : 3,
	  				                        	 'searchable' : false,
	  				                        	 'orderable' : false,
	  				                        	 'render' : function(data,
	  				                        			 type, row, meta) {
	  				                        		if(data != null){
	  						                			return timeStamp2String(data);
	  						                		}else{
	  						                			return "无需签收";
	  						                		}
	  				                        	 }
	  				                         },{
	  				                        	 'targets' : 4,
	  				                        	 'searchable' : false,
	  				                        	 'orderable' : false,
	  				                        	 'render' : function(data,
	  				                        			 type, row, meta) {
	  				                        		if (data != null) {
	  													return timeStamp2String(data);
	  												} else
	  													return '';
	  				                        	   }
	  				                         },{
	  				                        	 'targets' : 7,
	  				                        	 'searchable' : false,
	  				                        	 'orderable' : false,
	  				                        	 'render' : function(data,
	  				                        			 type, row, meta) {
	  				                        		return "<a href='javascript:void(0);' onclick=\"revoke('"+row.taskId+"','"+row.processInstanceId+"','ybTable')\">撤销</a>";
	  				                        	 }
	  				                         } ]
	  				
	  			}).on('order.dt',
	  					function() {
	  				console.log('排序');
	  			})	  			
	  			//  };
	  		}
	  		

	  		//审批通过
	  		$scope.apPass = function() {
	  		   
	  		    var mydata={"serialNum":$("#serialNum").val(),"content":$("#content").val(),
	  					"isPass":true, "taskId":$("#taskId").val()};
	  		    var _url = ctx + "rest/takeDelivery/complete";
	  		    doVacation(_url, mydata,2);
	  		};
	  		//审批不通过
	  		$scope.apUnPass = function() {
	  			var mydata={"serialNum":$("#serialNum").val(),"content":$("#content").val(),
	  					"isPass":false, "taskId":$("#taskId").val()};
	  			var _url = ctx + "rest/takeDelivery/complete";
	  			doVacation(_url, mydata,1);
	  		};
	  		
	  		//重新申请
	  		$scope.reApply = function() {
	  			var params = {};
				params.takeDelivery = {};
				params.takeDelivery.serialNum = $scope.deliver.takeDelivery.serialNum;
				params.takeDelivery.takeDeliverNum = $scope.takeDeliver.takeDeliverNum;
				params.takeDelivery.actualDate = $scope.takeDeliver.actualDate;
				params.takeDelivery.taker = $scope.takeDeliver.taker;
				params.takeDelivery.takeRemark = $scope.takeDeliver.takeRemark;
				params.deliveryMateriels = [];
				var param;
				for(var i=0;i < $scope.deliver.deliveryMateriels.length;i++){
					param = {};
					param.orderMaterielSerial = $scope.deliver.deliveryMateriels[i].orderMateriel.serialNum;
					param.serialNum = $scope.deliver.deliveryMateriels[i].serialNum;
					param.batchNum = $scope.deliver.deliveryMateriels[i].batchNum;
					param.manufactureDate = $scope.deliver.deliveryMateriels[i].manufactureDate;
					param.deliverCount = $scope.deliver.deliveryMateriels[i].deliverCount;
					param.deliverRemark = $scope.deliver.deliveryMateriels[i].deliverRemark;
					param.acceptCount = $scope.deliver.deliveryMateriels[i].acceptCount;
					param.refuseCount = $scope.deliver.deliveryMateriels[i].deliverCount-$scope.deliver.deliveryMateriels[i].acceptCount;
					param.takeRemark = $scope.deliver.deliveryMateriels[i].takeRemark;
					params.deliveryMateriels.push(param);
				}
	  			var mydata={"serialNum":$("#serialNum").val(),"content":$("#content").val(),
	  					"isPass":true, "taskId":$("#taskId").val(),"params":JSON.stringify(params)};
	  			var _url = ctx + "rest/takeDelivery/complete";
	  			doVacation(_url, mydata,2);
	  		};
	  		//审批不通过
	  		/*$scope.apUnPass = function() {
	  			var mydata={"serialNum":$("#serialNum").val(),"content":$("#content").val(),
	  					"isPass":false, "taskId":$("#taskId").val()};
	  			var _url = ctx + "rest/takeDelivery/complete";
	  			doVacation(_url, mydata,1);
	  		};*/
	  		
	  	//办结待办流程
	  		function doVacation(_url, mydata,target){
	  	        $.ajax( {
	  		        url : _url,
	  		        dataType:"text",
	  		        type: 'POST',
	  		        data : mydata,
	  		        success : function(data) {
	  		        	//$("#dbTable").DataTable().ajax.reload();
	  		        	showToastr('toast-bottom-right', 'success', data);
	  		        	$state.go("takeDelivery",{tabHref:target});
	  		        }
	  		     });
	  		}
	  		$scope.closeAuditDialogue = function() {
	  			$state.go("takeDelivery");
	  		};
	  		/************************************************申请JS***********************************************/
	  		
	  		var m_table;	
	  		
	  		$scope.changeTakeDeliveryMode = function(deliverType){
	  			
	  			if(deliverType=='贸易发货'){
	  					$scope.otherMode = false;
	  					$(".bootstrap-select").remove();//移除下拉控件
	  			}else{
	  				    $scope.otherMode = true;
	  				    $scope.deliver.orderSerial = '';
	  				    $scope.deliver.shipper = '';
	  				    $scope.deliver.shipperName = '';
	  				    $scope.deliver.receiver = '';
	  				    $scope.deliver.receiverName = '';
	  				    $scope.orderMateriels = [];
	  				 
	  				    if(m_table==undefined){
	  				    	selectParentMateriel();
	  				    }else{
	  				    	m_table.ajax.reload();
	  				    }
	  				   
	  			}
	  		}
	  	
	  	/***选择物料列表初始化START***/
	     
	     var selectParentMateriel = function() {
	              a = 0;
	              App.getViewPort().width < App.getResponsiveBreakpoint("md") ? $(".page-header").hasClass("page-header-fixed-mobile") && (a = $(".page-header").outerHeight(!0)) : $(".page-header").hasClass("navbar-fixed-top") ? a = $(".page-header").outerHeight(!0) : $("body").hasClass("page-header-fixed") && (a = 64);
	              m_table = $("#select_sample_2").DataTable({
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
	                  order: [[1, "desc"]],//默认排序列及排序方式
	                  searching: true,//是否过滤检索
	                  ordering:  true,//是否排序
	                  lengthMenu: [[5, 10, 15, 30, -1], [5, 10, 15, 30, "All"]],
	                  pageLength: 5,//每页显示数量
	                  processing: true,//loading等待框
//	                  serverSide: true,
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
//		  	  								return '<input type="radio" id='+data+' data-radio=true ng-click="getCheckedIds(\''+data+'\','+meta.row+')"  name="serialNum" value="'
//												+ $('<div/>')
//														.text(
//																row.supplyMateriels[0].serialNum)
//														.html()
//												+ '">';
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
	        			if($scope.orderMateriels.length==0){//如果需求物料列表为空
	        				for(var i = 0;i < $scope.serialNums.length;i++){ //将选中物料放入列表，并设置为编辑状态
	        					($scope.serialNums)[i].supplyMaterielSerial = ($scope.serialNums)[i].materiel.supplyMaterielSerial; //存放供应物料流水
	        					$scope.orderMateriels.push(($scope.serialNums)[i]);
	        					$scope["deliveryMaterielEdit"+i] = false;
	        					$scope["deliveryMaterielView"+i] = false;
	        				}
	        			}else{
			        		for(var i = 0;i < $scope.serialNums.length;i++){
			        			($scope.serialNums)[i].supplyMaterielSerial = ($scope.serialNums)[i].materiel.supplyMaterielSerial; //存放供应物料流水
		        				$scope.orderMateriels.splice(0,0,($scope.serialNums)[i]); //将选中物料放入列表开头，并设置为编辑状态
		        				$scope["deliveryMaterielEdit"+i] = false;
								$scope["deliveryMaterielView"+i] = false;
								$scope["deliveryMaterielEdit" + ($scope.orderMateriels.length-1)] = true;
								$scope["deliveryMaterielView" + ($scope.orderMateriels.length-1)] = true;
			        		}
	        				//之前的物料显示状态需要维持原状，以下添加代码
			        		
	        			}
	        			//$scope.countSupplyCount();
	        			$scope.copyMateriels = angular.copy($scope.orderMateriels);//复制需求物料列表，以便撤销
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
				
				
				
				
				
				
				
				
				
				
				
				
		    	
		    	//********附件  start****************//

		    	 /**
		         * 添加页面弹出
		         */
		    	$scope.addAttachFile = function (index){
		    		$("#basicMaterielInfoII").modal("show");
		    		$scope.fileIndex=index;
				}
		    	
		    	/**
		         * 编辑页面弹出
		         */
		    	$scope.addAttachFileEdit = function (index,serialNum){
		    		$("#basicMaterielInfoII").modal("show");
		    		$scope.fileIndex=index;
		    		 $scope.getAttachFileInfo(serialNum);
				}
		    	var _fileIndex = 0;
		    	
		    	 $scope.getAttachFileInfo=function(serialNum){
		    		 DeliveryService.getAttachFileInfo(serialNum).then(
		          		     function(data){
		          		    	$scope.file=data.fileList;
		          		    	if(data.fileList!=null){
		          		    		_fileIndex=data.fileList.length;
		          		    	}
		          		     },
		          		     function(error){
		          		         $scope.error = error;
		          		     }
		          		 );
		    		 
		    	 }
				
			    $scope.saveFile  = function(fileIndex) {//保存File信息
			    	
			    	/*if($scope.file==null){
			    		toastr.warning("请上传文件！");
			    		return false;
			    	}*/
			    	
			    	var html="";
			    	var htmlReal="";
			    	if($scope.file!=null){
			    		for(var i=0;i<$scope.file.length;i++){
				    		if($scope.file[i].file==null||$scope.file[i].file==''){
				    			toastr.warning("上传文件不能为空！");
					    		return false;
				    		}
				    		if($scope.file[i].remark){
				    			htmlReal+=$scope.file[i].file+','+$scope.file[i].remark+'&';
				    		}else{
				    			htmlReal+=$scope.file[i].file+','+'&';
				    		}
				    		
				    		var w=$scope.file[i].file.indexOf('_');
				    		var sub_str_file=$scope.file[i].file.substring(w+1); 
				    		
				    		var asd="'"+$scope.file[i].file+"'";
				    		html+='<a href="javascript:;" ng-click="downloadFile1('+asd+')">'+ sub_str_file+'</a>&nbsp;';
						}	
			    	}
			    	
			    	$("#batchNum"+fileIndex).html($compile(html)($scope))
			    	$("#addBatchNum"+fileIndex).hide();
			    	$("#batchNumReal"+fileIndex).html(htmlReal);
			    	 $('#basicMaterielInfoII').modal('hide');// 保存成功后关闭模态框
			    	$(".modal-backdrop").remove();
			    	$("#fileTable tbody tr").remove();
			    	_fileIndex=0;
			    	$scope.file=null;
			    }; 	
			    
			    $scope.saveFileEdit = function(fileIndex) {//保存File信息
			    	var html="";
			    	var htmlReal="";
			    	for(var i=0;i<$scope.file.length;i++){
			    		if($scope.file[i].file==null||$scope.file[i].file==''){
			    			toastr.warning("上传文件不能为空！");
				    		return false;
			    		}
			    		if($scope.file[i].remark){
			    			htmlReal+=$scope.file[i].file+','+$scope.file[i].remark+'&';
			    		}else{
			    			htmlReal+=$scope.file[i].file+','+'&';
			    		}
			    		
			    		var w=$scope.file[i].file.indexOf('_');
			    		var sub_str_file=$scope.file[i].file.substring(w+1); 
			    		
			    		var asd="'"+$scope.file[i].file+"'";
			    		html+='<a href="javascript:;" ng-click="downloadFile1('+asd+')">'+ sub_str_file+'</a>&nbsp;';
					}
			    	$("#batchNum"+fileIndex).html($compile(html)($scope))
			    	$("#addBatchNum"+fileIndex).hide();
			    	$("#batchNumReal"+fileIndex).html(htmlReal);
			    	 $('#basicMaterielInfoII').modal('hide');// 保存成功后关闭模态框
			    	$(".modal-backdrop").remove();
			    	$("#fileTable tbody tr").remove();
			    	_fileIndex=0;
			    	$scope.file=null;
			    	var fileRelation=fileRelation+fileIndex;
			    	$scope.fileRelation=false;
			    };
			    
			    /**
		        * File新增一行
		        */
			    $scope.addFile = function(){
			    	
				    	   if($scope.file){}else{$scope.file =[{}]}
				    	   $scope.file[_fileIndex] = {};
				    	   _fileIndex++;
			    };
			    
			    //下载文件查看详情时
			       $scope.downloadFile1 = function(str){
			    	   
			    	 window.location.href= $rootScope.basePath+"/rest/fileOperate/downloadFile?fileName="+encodeURI(encodeURI(str));
			       }
			    
			    
			    $scope.updateFile  = function() {//更新File信息
			    	if($scope.pay.serialNum==null||$scope.pay.serialNum=='') {//上级物料为空的处理
			    		toastr.error('请先保存基本信息！');return
					}
			    	/*if($('#form_sample_4').valid()){*/
			    		PayService.updateFile($scope.file).then(
			       		     function(data){
			       		    	toastr.success('数据保存成功！');
			       		    	$scope.inputFile=false;
			       		    	$scope.cancelFile();
			       		    	
			       		     },
			       		     function(error){
			       		    	toastr.error('数据保存出错！');
			       		         $scope.error = error;
			       		     }
			       		 );
			    	/*}*/
			    	
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

		      //创建对象
		  	  var uploader = $scope.uploader = new FileUploader({url:'rest/fileOperate/uploadSingleFile'});
		  	 
		  	  uploader.onAfterAddingFile = function(item){
		  		  if(item.file.size>10000000){
		  			  //toastr.warning("文件大小超过10M！");
		  			  uploader.cancelAll();
		  		  }
		  	  }
		  	  
		  	  //添加文件到上传队列后
		  	  uploader.onCompleteAll = function () {
		  		  uploader.clearQueue();
		  	  };
		  	  //上传成功
		  	  uploader.onSuccessItem = function (fileItem,response, status, headers) {
		  		  if (status == 200){ 
		  			  if(response==""){
		  				  toastr.error("上传失败！");
		  				  return;
		  			  }
		  		  		toastr.success("上传成功！");
		  		  		$scope.file[uploadSelectIndex].file = response.filename;
		  		  }else{
		  			  toastr.error("上传失败！");
		  			  $scope.file[uploadSelectIndex].file = response.filename;
		  		  }
		  		};
		  	  //上传失败
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
		  	  //下载文件上传时
		       $scope.downloadFile = function(obj){
		    	   if(!handle.isNull(obj)){
		    		   window.location.href= $rootScope.basePath+"/rest/fileOperate/downloadFile?fileName="+encodeURI(encodeURI(obj.file));
		    	   }else{
		    		   toastr.error("下载失败!");
		    	   }
		       }
		       
		       $scope.removefile = function(index){
		    	   $scope.file[index].file = "";
		       }
		        
			  //********附件  end****************//
		    		
				
		    /********************************物料模糊检索及分页 START *********************************/
		  	 /** *************订单物料明细可检索化  start*************** */
		  	 $scope.pageIndex = 1; //记录当前页
		  	 $scope.pageSize = '10'; //每页的记录数
		  	 $scope.totalPage = '1'; //记录总页数
		  	 $scope.dispalyDeliveryMateriel = [];//页面显示结果
		  	 $scope.filterDeliveryMateriel = [];//查询筛选结果
		  	 
		  	 $scope.createFilterList = function(){
		  		 $scope.filterDeliveryMateriel = [];
		  		if($scope.deliver.deliveryMateriels.length>0&&$scope.queryStr&&!isNull($scope.queryStr)){
		  			for(var i = 0;i < $scope.deliver.deliveryMateriels.length;i++){// data.data为选择的标准物料
		  				if(($scope.deliver.deliveryMateriels)[i].orderMateriel.materiel.materielNum.indexOf($scope.queryStr)>=0){
		  					$scope.filterDeliveryMateriel.push(angular.copy(($scope.deliver.deliveryMateriels)[i]));
		  				}else if(($scope.deliver.deliveryMateriels)[i].orderMateriel.materiel.materielName.indexOf($scope.queryStr)>=0){
		  					$scope.filterDeliveryMateriel.push(angular.copy(($scope.deliver.deliveryMateriels)[i]));
		  				}else if(($scope.deliver.deliveryMateriels)[i].orderMateriel.materiel.specifications.indexOf($scope.queryStr)>=0){
		  					$scope.filterDeliveryMateriel.push(angular.copy(($scope.deliver.deliveryMateriels)[i]));
		  				}
		  			}
		  		}else{
		  			$scope.filterDeliveryMateriel = angular.copy($scope.deliver.deliveryMateriels);
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
		  	 
		  	/** *************订单物料明细可检索化  end*************** */
		            
		    /********************************物料模糊检索及分页 END *********************************/
				
				
				
				
				
				
				

			 }]); 