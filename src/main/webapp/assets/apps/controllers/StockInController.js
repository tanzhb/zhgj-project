/**
 * 
 */

angular.module('MetronicApp').controller('StockInController',['$rootScope','$scope','$state','$filter','$http','takeDeliveryService','$location','$compile','$stateParams','commonService',function($rootScope,$scope,$state,$filter,$http,takeDeliveryService,$location,$compile,$stateParams,commonService) {
	 $scope.$on('$viewContentLoaded', function() {   
	    	// initialize core components
		    handle = new pageHandle();
	    	App.initAjax();
	    	handle.datePickersInit();
	    	if($location.path()=="/stockInAdd"||$location.path()=="/stockIn"){
	    		$scope.serialNums =[];
	    		initWarehouse('pt',null);
	    		handle.validatorInit();
	    		//initTakeDelviery();
	    		initSuppliers();
	    		loadTakeDelieryTable();
	    		if(!isNull($stateParams.serialNum)){
	    			$(".s_tip").text("编辑入库信息");
	    			stockInInfo($stateParams.serialNum);
	    		}else{
	    			$rootScope.setNumCode("IN",function(newCode){//
    		 		});
	    		}
	    		if($scope.record.stockDate==''){
		    		$scope.record.stockDate=$filter('date')(new Date(), 'yyyy-MM-dd');
		    	}
	    	}else{
	    			stockInInfo($stateParams.serialNum);
	    			
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
	    		var name_arr = [];
	    		for(var i in $scope.takeDeliveryMateriels){
	    			if(!isNull($scope.takeDeliveryMateriels[i].stockInWarehouseSerial)){
	    				w_arr.push($scope.takeDeliveryMateriels[i].stockInWarehouseSerial);
	    				if($scope.takeDeliveryMateriels[i].stockInWarehouse!=null){
	    					name_arr.push($scope.takeDeliveryMateriels[i].stockInWarehouse.warehouseName);
	    				}
	    			}
	    			if(!isNull($scope.takeDeliveryMateriels[i].stockInPositionSerial)){
	    				p_arr.push($scope.takeDeliveryMateriels[i].stockInPositionSerial);
	    			}
	    		}
	    		if( $scope.warehouseNames==undefined){
	    			$scope.warehouseCount = unique(name_arr,"warehouseName");
	    		}else{
	    			$scope.warehouseCount = unique($scope.warehouseNames,"warehouseName");
	    		}
	    		//$scope.warehouseCount = unique(w_arr);
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
			var promise = commonService.initSuppliers();
			promise.then(function(data){
				$scope.suppliers = data.data;
			},function(data){
				//调用承诺接口reject();
			});
			}
			
			/**
			 * 加载仓库数据
			 */
			var initWarehouse = function(judgeString,comId){
			var promise = takeDeliveryService.initWarehouse(judgeString,comId);
			promise.then(function(data){
				$scope.warehouses = data;
			},function(data){
				//调用承诺接口reject();
			});
			}
			
			/**
			 * 加载库区数据
			 */
			 $scope.getPositions = function(materiel,index){
			/*	 if($scope["warehouseName"+materiel.serialNum]==undefined){
					 $scope["warehouseName"+materiel.serialNum]=null;
				 }else{
					 delete $scope["warehouseName"+materiel.serialNum];
				 }
					var warehouseName = $('option[class="'+materiel.serialNum+'"]:selected').text();
					$scope["warehouseName"+materiel.serialNum]=warehouseName;
					var arraySerialNums=$scope.deliveryMaterielSerialNums;
					 var  warehouseNames=new Array();
					 for(var i=0;i < arraySerialNums.length;i++){
						 if($scope["warehouseName"+arraySerialNums[i]]!=undefined){
							 warehouseNames=warehouseNames.concat($scope["warehouseName"+arraySerialNums[i]]);
						 }
						}
					 $scope.warehouseNames=warehouseNames;*/
				// warehouseNames.push($('option[class="'+materiel.warehouseSerial+index+'"]:selected').text());
				var promise = takeDeliveryService.getPositions(materiel.warehouseSerial);
				promise.then(function(data){
					//$scope.warehousePositions = data.data;
					materiel.warehousePositons = data.data;
					// countWarehouseAndPosition();
				},function(data){
					//调用承诺接口reject();
				});
			}
			 
			 //选中第一个物料仓库时触发
			 $scope.getPositionsAndSelectedAll =function(materiel,index){
				 $scope.getPositions(materiel);
				// warehouseNames.push($('option[class="'+materiel.warehouseSerial+index+'"]:selected').text());//记录下仓库名称
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
	            var takeDeliverSerial = $scope.record.takeDeliverSerial;
	            var stockSerial = null;
	            if(isNull(takeDeliverSerial)){
	            	stockSerial = $scope.record.serialNum;
	            }

	        	var promise = takeDeliveryService.getTakeDeliveryMaterielListForStockIn(takeDeliverSerial,stockSerial);
        		promise.then(function(data){
        			$scope.takeDeliveryMateriels = data.data;
        			var deliveryMaterielSerialNums=new Array()
        		
    				for(var i in data.data){
    					if(data.data[i].supplyMateriel!=null){
        					$scope.takeDeliveryMateriels[i].orderMateriel = data.data[i].supplyMateriel;
        					delete $scope.takeDeliveryMateriels[i].supplyMateriel;
    					}
    					if(data.data[i].serialNum!=null){
    						deliveryMaterielSerialNums.push(data.data[i].serialNum);
    					}
    				}
        			$scope.deliveryMaterielSerialNums=deliveryMaterielSerialNums;//把所有发货物料流水存到该数组
        			countWarehouseAndPosition();
        			var totalOrderCount=0, totalDeliveryCount=0;
	        		var  totalQualifiedCount=0,totalStockInCount=0;
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
		        				totalStockInCount=totalStockInCount+Number( $scope.takeDeliveryMateriels[i].stockInCount);
			        		}
	        			}
	        		}
	        		$scope.totalDeliveryCount=totalDeliveryCount;//发货总数
	        		$scope.totalOrderCount=totalOrderCount;//订单总数
	        		if($scope.oprateType==undefined){//出库计划详情物料tab展示合格总数,出库总数,未出总数
	        			$scope.totalQualifiedCount=totalQualifiedCount;//合格总数
		        		$scope.totalStockInCount=totalStockInCount;//入库总数
		        		$scope.totalUnstockInCount=totalDeliveryCount-totalStockInCount;//未入总数
	        		}
        			//$scope.deliver.materielCount = data.orderMateriel.length;
        			if($location.path()=="/stockInAdd"&&!isNull($scope.record)&&!isNull($scope.record.serialNum)){ //入库编辑时
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
			$scope.saveStockIn = function(judgeString) {
				if($('#stockInForm').valid()){
					handle.blockUI();
					var params = {};
							if(judgeString=='save'){
								$scope.record.status='0';	
							}else{
								$scope.record.status='1';
							}
					params.record = $scope.record;
					params.record.deliverSerial = "";
					params.deliveryMateriels = [];
					var param;
					for(var i=0;i < $scope.takeDeliveryMateriels.length;i++){
						param = {};
						if(isNull($scope.takeDeliveryMateriels[i].stockInBatchs)){
							toastr.warning("生产批次未填写！");
							handle.unblockUI();
		     				return false;
						}else{
							param.stockInBatchs = $scope.takeDeliveryMateriels[i].stockInBatchs;
						}
					param.orderMaterielSerial = $scope.takeDeliveryMateriels[i].orderMaterielSerial;//生成库存使用到
//						param.batchNum = $scope.takeDeliveryMateriels[i].batchNum;
//						param.manufactureDate = $scope.takeDeliveryMateriels[i].manufactureDate;
//						param.deliverCount = $scope.takeDeliveryMateriels[i].deliverCount;
//						param.deliverRemark = $scope.takeDeliveryMateriels[i].deliverRemark;
//						param.acceptCount = $scope.takeDeliveryMateriels[i].acceptCount;
//						param.refuseCount = $scope.takeDeliveryMateriels[i].deliverCount-$scope.takeDeliveryMateriels[i].acceptCount;
//						param.takeRemark = $scope.takeDeliveryMateriels[i].takeRemark;
						
						
						param.stockCount = $scope.takeDeliveryMateriels[i].stockInCount;
						if(!isNull($scope.takeDeliveryMateriels[i].acceptCount)){
							param.serialNum = $scope.takeDeliveryMateriels[i].serialNum;
							param.unstockCount = $scope.takeDeliveryMateriels[i].acceptCount-$scope.takeDeliveryMateriels[i].stockInCount;
							
						}else{//
							debugger;
							param.deliverSerial = params.record.takeDeliverSerial;
							param.unstockCount = 0;
							if(isNull($scope.takeDeliveryMateriels[i].supplyMaterielSerial)){
								//param.orderMaterielSerial = $scope.takeDeliveryMateriels[i].serialNum;
								param.orderMaterielSerial = $scope.takeDeliveryMateriels[i].serialNum;
							}else{
								//param.orderMaterielSerial = '';
								params.record.takeDeliverSerial = '';
								param.supplyMaterielSerial = $scope.takeDeliveryMateriels[i].supplyMaterielSerial;
							}
						}
						param.warehouseSerial = $scope.takeDeliveryMateriels[i].warehouseSerial;
						param.positionSerial = $scope.takeDeliveryMateriels[i].positionSerial;
						param.stockRemark = $scope.takeDeliveryMateriels[i].stockRemark;
						params.deliveryMateriels.push(param);
					}
					
					var promise = takeDeliveryService
							.saveStockInData(params);
					promise.then(function(data) {
						if(data.data == "1"){
							if(judgeString=='save'){
								toastr.success("保存成功！");
							}else{
								toastr.success("入库成功！");
							}
							$state.go("takeDelivery");
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
			
			/**
			 * 取消入库
			 */
			$scope.cancelStockIn = function(){
				$state.go("takeDelivery");
			}
			
			/**
			 * 获得仓库名
			 */
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
				for (var i = 0; i < arr.length; i++) {f
						if (arr[i] === item)
							return i;
						else
							return -1;
				}
			}
			
			/**
			 * 获取入库信息
			 */
			var stockInInfo = function(serialNum){
				var promise = takeDeliveryService.getStockInInfo(serialNum);
        		promise.then(function(data){
        			$scope.record = data.data.stockInOutRecord; 
        			$scope.deliver = data.data.deliver; 
        			$scope.totalDeliveryCount=data.data.totalDeliveryCount; //发货数量
        			if($scope.deliver.deliverType!='其他发货'){
        				$scope.otherMode=false
        			}else{
        				$scope.otherMode=true;
        			}
        			if(data.data.deliver!=null&&data.data.deliver.takeDelivery!=null){  //当贸易入库时
        				$scope.record.takeDeliverNum = data.data.deliver.takeDelivery.takeDeliverNum;
            			$scope.record.shipperOrReceiver = data.data.deliver.shipper;
    					//$scope.shipperOrReceiverName = data.data.deliver.shipperName;
            			//$scope.deliver.materielCount = data.orderMateriel.length;
            			if(!isNull($stateParams.serialNum)&&($location.path()=="/stockInAdd"||$location.path()=="/stockIn")){//入库编辑或入库时时
            				$scope.deliverSerial = data.data.deliver.serialNum;
            				$scope.getTakeDeliverMateriel(data.data.deliver);
            				$scope.record.inOutType = '采购';
            				/*if(data.data.deliver.orderSerial!=null){
            					$scope.record.orderNum = data.data.deliver.orderNum; //关联订单号
            				}*/
            			
            			}else{
            				$scope.getTakeDeliverMateriel(data.data.deliver);
            				$scope.queryForPage();
            			}
            			
        			}else{ //其他类型入库时
        				if(!isNull($stateParams.serialNum)&&($location.path()=="/stockInView")){//入库编辑或入库时时
        					//var de$scope.record.serialNum;
            				$scope.getTakeDeliverMateriel(data.data.deliver);
            			}
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
	       
		    function unique(arr,type) {
			    var result = [], hash = {},resultName=[];
			   
			    for (var i = 0, elem; (elem = arr[i]) != null; i++) {
			        if (!hash[elem]) {
			            result.push(elem);
			            if(elem!=""){
			            	resultName.push(elem);
			            }
			            hash[elem] = true;
			        }
			    }
			    if(type!=undefined){
			    	 var  str="";
			    	 for(var i=0;i<resultName.length;i++){
			    		 if(i!=resultName.length-1){
			    			 str= str+resultName[i]+",";
			    		 }else{
			    			 str= str+resultName[i];
			    		 }
			    	 }
			    	$scope.WarehouseName= str;
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
					            	inOutType:{required:"入库类型不能为空！"},
					            	/*operator:{required:"操作员不能为空！"},*/
					            	contactNum:{required:"联系方式不正确！"},
					            	stockCount:{required:"入库数量不能为空！",digits:"发货数量必须为数字！"},
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
					               /* operator: {
					                	required: !0
					                },*/
					                warehouseSerial: {
					                	required: !0
					                },
					                /*positionSerial: {
					                	required: !0
					                },*/
					                stockCount: {
					                	required: !0,
					                	digits:!0,
					                	StockInNumCheck:!0
					                },
					                contactNum: {
					                	//required: !0,
					                	isPhone: !0
					                },
					                inOutType:{
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
	  		
	  		$scope.changeStockInMode = function(stockInType){
	  			
	  			if(stockInType=='贸易'){
	  					$scope.otherMode = false;
	  					$(".bootstrap-select").remove();//移除下拉控件
	  			}else{
	  				    $scope.otherMode = true;
	  				    //$scope.deliver.orderSerial = '';
	  				   // $scope.deliver.shipper = '';
//	  				    $scope.deliver.shipperName = '';
//	  				    $scope.deliver.receiver = '';
//	  				    $scope.deliver.receiverName = '';
//	  				    $scope.orderMateriels = [];
	  				 
	  				    if(m_table==undefined){
	  				    	selectParentMateriel();
	  				    }else{
	  				    	m_table.ajax.reload();
	  				    }
	  				   
	  			}
	  		}
			
			   
		       /***选择收货列表初始化START***/
		       var t_table;
		       var loadTakeDelieryTable = function() {
		                a = 0;
		                App.getViewPort().width < App.getResponsiveBreakpoint("md") ? $(".page-header").hasClass("page-header-fixed-mobile") && (a = $(".page-header").outerHeight(!0)) : $(".page-header").hasClass("navbar-fixed-top") ? a = $(".page-header").outerHeight(!0) : $("body").hasClass("page-header-fixed") && (a = 64);
		                t_table = $("#takeDelivery").DataTable({
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
		                    pageLength: 5,//每页显示数量
		                    processing: true,//loading等待框
		                    bRetrieve : true,
//		                    serverSide: true,
		                   ajax: "rest/takeDelivery/takeDeliveryList?status=3",//加载数据中
		                   /* ajax :{ "url":$rootScope.basePath
		  						+ "/rest/takeDelivery/takeDeliveryList?status=3",// 加载数据中收货列表的数据    
		  						"contentType": "application/json",
		  					    "type": "POST",
		  					    "data": function ( d ) {
		  					      return JSON.stringify( d );
		  					    }},*/
		                    "aoColumns": [
		                                  { mData: 'takeDelivery.serialNum' },
		                                  { mData: 'takeDelivery.takeDeliverNum' },
		                                  { mData: 'orderNum' },
		                                  { mData: 'shipper' },
		                                  //{ mData: 'materielCount' },物料条目数
				                          { mData: 'materielTotalCount' },//物料总数
		                                  { mData: 'packageCount' },
		                                  { mData: 'packageType' },
		                                  { mData: 'warehouse' },
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
	  	  								/*return '<input  type="checkbox" id='+data+'   name="serialNum" value="'
											+ $('<div/>')
													.text(
															data)
													.html()
											+ '">';*/
								return '<label class="mt-radio mt-radio-outline">'+
                                '<input type="radio"  ng-click="getSelectIndex(\''+meta.row+'\')" name="takeDeliverySerial"  class="checkboxes" id="'+data+'" value="'+data+'" />'+
                                '<span></span></label>';
	
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
							'targets' : 7,
							'render' : function(data,
									type, row, meta) {
									if(data!=null){
										return data.address;
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
		            
		            $scope.confirmTakeDeliverySelect = function(){
			  			var id_count = $('#takeDelivery input[name="takeDeliverySerial"]:checked').length;
						if(id_count==0){
							toastr.warning("请选择收货单");
						}else{
							//var serialNum = $('#buyOrder input[name="selecrOrderSerial"]:checked').val();
							var delivery = t_table.row($scope.index).data();debugger;
							$scope.deliverSerial = delivery.serialNum;
							$scope.record.takeDeliverSerial = $('#takeDelivery input[name="takeDeliverySerial"]:checked').val();
							$scope.record.takeDeliverNum = delivery.takeDelivery.takeDeliverNum;
							$scope.record.shipperOrReceiver = delivery.shipper;
							if(isNull(delivery.orderSerial)){
								$scope.record.orderNum = delivery.docNum;
							}else{
								$scope.record.orderNum = delivery.orderNum;
							}
							
							//$scope.shipperOrReceiverName = delivery.shipperName;
							$scope.getTakeDeliverMateriel(delivery);
							$("#takeDeliveryInfo").modal('hide'); 
						}
						
			  		}

		            $scope.getSelectIndex = function(index){
		            	$scope.index = index;
		            }
		            
		            /**
				     * 设置入库默认入库数量
				     */
				    $scope.setDefualtNum = function(scope){
				    	//if(isNull($scope.deliver.serialNum)){
			  				scope.materiel.stockCount = 0;
			  			//}
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
				        					param.supplyMaterielSerial = ($scope.serialNums)[i].materiel.supplyMaterielSerial;
				        					//param.serialNum = '';
				        					
				        					param.serialNum = ($scope.serialNums)[i].materiel.supplyMaterielSerial; //存放供应物料流水
				        					$scope.takeDeliveryMateriels.push(param);
				        					$scope["deliveryMaterielEdit"+i] = false;
				        					$scope["deliveryMaterielView"+i] = false;
				        				}
				        			}else{
						        		for(var i = 0;i < $scope.serialNums.length;i++){
						        			//($scope.serialNums)[i].materiel.serialNum = ($scope.serialNums)[i].materiel.supplyMaterielSerial; //存放供应物料流水
						        			var param = {};
				        					param.orderMateriel = ($scope.serialNums)[i];
				        					param.supplyMaterielSerial = ($scope.serialNums)[i].materiel.supplyMaterielSerial;
				        					//param.serialNum = '';
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
							 
	 /** *************入库物料批次操作  start*************** */
	 var stockInBatchMateriel,index,warehouseNames;//当前入库物料
	/**
	  * 显示填入批次窗口
	  * materiel 入库物料
	  * 
	  */
	$scope.showStockBatch=function(materiel,indexori){
		$('#stockBatchInfo').modal('show');//显示批次弹框
		$scope.stockInBatchs = angular.copy(materiel.stockInBatchs)//暂存入库批次
		stockInBatchMateriel = materiel;
		index=indexori;
		
  	}
							 
							 
	$scope.addStockBatch=function(){
		if($scope.stockInBatchs){
		}else{
			$scope.stockInBatchs =[]
		}
	   $scope.stockInBatchs[$scope.stockInBatchs.length] = {};
	}	
	 $scope.deleteStockBatch = function(index){
    	   $scope.stockInBatchs.splice(index,1);
       };
       
       
       $scope.totalCount = function(){
    	   if($scope.stockInBatchs){
   		    var total = 0 ; 
	       		for(var i=0;i<$scope.stockInBatchs.length;i++){
	       			if(!isNull($scope.stockInBatchs[i].stockInCount)){
	       				total = total + Number($scope.stockInBatchs[i].stockInCount);
	       			}
	       			
	       		}
	       		return total
	       	}else{
	       		return 0;
	       	}
       }
       
       $scope.confirmSave = function(){
    	   if(isNull($scope.stockInBatchs)){
    		   toastr.warning("批次信息不能为空！");
				return false;
    	   }else{
    		   for(var i=0;i<$scope.stockInBatchs.length;i++){
        		   if(isNull($scope.stockInBatchs[i].batchNum)){
         				toastr.warning("批次号不能为空！");
         				return false;
         			}
        		   if(isNull($scope.stockInBatchs[i].warehouseSerial)){
         				toastr.warning("仓库不能为空！");
         				return false;
         			}
        		   if(isNull($scope.stockInBatchs[i].positionSerial)){
         				toastr.warning("库区不能为空！");
         				return false;
         			}
        		   if(isNull($scope.stockInBatchs[i].stockInCount)){
          				toastr.warning("入库数量不能为空！");
          				return false;
          			}
          		} 
    	   }
    	   
    	   
    	   if($scope.totalCount()>stockInBatchMateriel.acceptCount){
    		   toastr.warning("入库数量不能大于发货数量！");
 				return false;
    	   }
    	   stockInBatchMateriel.stockInCount = $scope.totalCount();
    	   stockInBatchMateriel.stockCount = $scope.totalCount();
    	   stockInBatchMateriel.stockInBatchs = $scope.stockInBatchs;
    	  var  warehouseNames=new Array();
    	   for(var i=0;i<$scope.stockInBatchs.length;i++){
    		   warehouseNames.push($('option[class="'+$scope.stockInBatchs[i].warehouseSerial+'"]:first').text());
    	   }
    	   $scope.warehouseNames=warehouseNames;
    	   unique(warehouseNames,"warehouseName");
    	  
    	   if($scope.record.materielCount==undefined){
    		   $scope.record.materielCount=0;
    	   }
    	   var beforedata=  $scope.record.materielCount;//之前的入库总数
    	   if(beforedata==undefined){
    		   $scope.record.materielCount+=stockInBatchMateriel.stockInCount;
    	   }else{
    		   $scope.record.materielCount= $scope.record.materielCount-beforedata+Number(stockInBatchMateriel.stockInCount);
    	   }
    	   $scope.totalStockInCount=$scope.record.materielCount;
    	   $scope.totalUnstockInCount=$scope.totalDeliveryCount-$scope.totalStockInCount;
    	   $('#stockBatchInfo').modal('hide');//显示批次弹框
       }
       
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
							 
	 /** *************入库物料批次操作  end*************** */
							
	       

}]); 
