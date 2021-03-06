/* Setup general page controller */
angular.module('MetronicApp').controller('saleOrderController', ['$rootScope', '$scope', 'settings','orderService','$filter',
    '$state',"$stateParams",'$compile','$location','materielService','FileUploader','DeliveryService','commonService', function($rootScope, $scope, settings,orderService,$filter,$state,$stateParams,$compile,$location,materielService,FileUploader,DeliveryService,commonService) {
    $scope.$on('$viewContentLoaded', function() {   
    	// initialize core components
    	App.initAjax();
    	handle = new pageHandle();
    	// set default layout mode
    	$rootScope.settings.layout.pageContentWhite = true;
        $rootScope.settings.layout.pageBodySolid = false;
        $rootScope.settings.layout.pageSidebarClosed = false;
        if($state.current.name=="saleOrder"){
        	loadMainTable();// 加载订单列表(普通订单)
//        	loadMainFramTable();// 框架订单列表
        	loadDeliveryTable();// 发货计划列表
        	loadGatheringTable();// 收款计划列表
        	
        	//***************************************流程处理相关start
        	var dbtable;//待办table
			var endTaskTable;//已办table
			
			if($stateParams.tabHref == '1'){//首页待办列表传过来的参数
				$('#orderTab a[href="#daiban"]').tab('show');
				showDbTable();
			}else if($stateParams.tabHref == '2'){//首页已办列表传过来的参数
				$('#orderTab a[href="#yiban"]').tab('show');
				showYbTable();
			}else if($stateParams.tabHref == '3'){//首页已办列表传过来的参数
				$('#SALEorderTab a[href="#fahuojihua"]').tab('show');
				$('#deliveryPlanTab a[href="#daibanDeliveryPlan"]').tab('show');
				showDbDeliveryPlanTable()
			}else if($stateParams.tabHref == '4'){//首页已办列表传过来的参数
				$('#SALEorderTab a[href="#fahuojihua"]').tab('show');
				$('#deliveryPlanTab a[href="#yibanDeliveryPlan"]').tab('show');
				showYbDeliveryPlanTable()
			}/*else if($stateParams.tabHref == '5'){//首页
				$('#SALEorderTab a[href="#fahuojihua"]').addClass('active');
				$('#SALEorderTab a[href="#fahuojihua"]').addClass('active');
				$('#deliveryPlanTab a[href="#yibanDeliveryPlan"]').tab('show');
				showYbDeliveryPlanTable()
			}*/else{//从菜单进入
				$('#orderTab a[href="#apply"]').tab('show');
			}
			
			$scope.toShowDeliveryPlan=function(){
				$('#deliveryPlanTab a[href="#applyDeliveryPlan"]').tab('show');
			}
			// 请假申请
			$scope.toApply = function() {
				$('#orderTab a[href="#apply"]').tab('show');
			};
			// 待办流程
			$scope.toDaiban = function() {//deliveryPlanTab
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
			// 待办发货计划流程
			$scope.toDaibanDeliveryPlan = function() {//deliveryPlanTab
				dbtable = showDbDeliveryPlanTable();								
			};
			$scope.toDaibanDeliveryPlan = function() {//deliveryPlanTab
				dbtable = showDbDeliveryPlanTable();								
			};
			// 已办发货计划流程
			$scope.toYibanDeliveryPlan = function() {
				endTaskTable = showYbDeliveryPlanTable();
			};
			// 关闭审批窗口
			$scope.closeAuditDialogue = function() {
				$('#auditOrderModal').modal("hide");
			};
			
			// 关闭更改申请窗口 
			$scope.closeModifyDialogue = function() {
				$('#modifyOrderModal').modal("hide");
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
            	// 加载数据
            	if($stateParams.serialNum){
            		$scope.opration = '修改';
            		$scope.stateParamserialNum=$stateParams.serialNum;
            		$scope.cancelContract();
            		$scope.cancelClauseSettlement();
            		$scope.cancelClauseAdvance();
            		$scope.cancelClauseDelivery();
            		$scope.cancelClauseCheckAccept();
            		$scope.cancelClauseFramework();
       		    	$scope.cancelClauseAfterSales();
   	       		    $scope.cancelFile();
            	
            		$scope.getSaleOrderInfo($stateParams.serialNum,$stateParams.taskId, $stateParams.comments,$stateParams.processInstanceId,$stateParams.isReject)
            	}else{
            		$scope.opration = '新增';
            		$scope.orderMateriel=[];
            		$scope.saleOrder={};
            		$scope.stateParamserialNum=$stateParams.serialNum;
            		$rootScope.setNumCode("SO",function(newCode){
            			$scope.saleOrder.orderNum = newCode;
            		});
            		$scope.contract={};
            		$scope.clauseSettlement = {};
            		$scope.clauseSettlement.otherAmount = 0;
            		$scope.saleOrder.seller ="中航能科（上海）能源科技有限公司";
            		$rootScope.setNumCode("CA",function(newCode){//
             			$scope.contract.contractNum= newCode;//合同编号
             		});
            		$scope.contract.contractType="销售合同";
            		$scope.saleOrder.orderType="自主销售";
            		$scope.saleOrder.tradeType="内贸";
            		$scope.saleOrder.currency="人民币";
            		$scope.saleOrder.serviceModel="普通代理";
            		$scope.saleOrder.settlementClause="平进平出";
            		
            		$scope.saleOrder.orderDate = timeStamp2String2(new Date())
            		$scope.saleOrder.rate = 16;
                	//合同内容
                	$scope.saleOrder.contractContent = '111100';
                	$scope.initContractContent();
                	
                	
            		dateSelectSetting();//日期选择限制
            		//加载客户
                	initCustomers();
//                	initWarehouse();
//                	initPtWarehouseAddress();
            	}
            	
            	$scope.noShow = true;
            	/*if($stateParams.view==1){// 订单切换为查看
            		$scope.saleOrderInput = true;
    		    	$scope.saleOrderShow = true;
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
    		    }*/
            	
            	if(!isNull($stateParams.materiels)){
            		$scope.isInit = true;
	        		$scope.saleOrder.currency = '人民币';
	        		$scope.saleOrder.buyComId = $stateParams.buyComId;
	        		$scope.saleOrder.demandPlanSerial = $stateParams.demandPlanSerial;
	        		//获取当前登录人名称
	        		commonService.getCurrentUser().then(
		   	       		     function(data){
		   	       		    	 $scope.saleOrder.maker = data.data.userName;
		   	       		 //获取一个随机的订单编号
		   		        		/*commonService.getOrderNum().then(
		   		        				function(data){
		   		        					$scope.saleOrder.orderNum = data.data.orderNum;
		   		        					setTimeout(function(){
		   		        						$scope.save();
		   		        					},200);
		   		        				},
		   		        				function(error){
		   		        					$scope.error = error;
		   		        				}
		   		        		);*/
		   	       		   $rootScope.setNumCode("SO",function(newCode){
		            			$scope.saleOrder.orderNum = newCode;
		            		});
		   	       		     },
		   	       		     function(error){
		   	       		         $scope.error = error;
		   	       		     }
		   	       	);
	        		
	        		
	        		//getMateriels($stateParams.materiels);
	        		console.log($stateParams.materiels);
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
            	
            	setTimeout($scope.autoSave, 300000);
        	}
    });
    
    $scope.changeFlag = true
    $scope.repeatDone = function(scope){
    	$scope.changeFlag = false;
    	var date1= scope._orderMateriel.deliveryDate;
    	var date2= scope._orderMateriel.lastDeliveryDate;
    	/*var date3= scope.saleOrder.orderDate;*/
    	$scope.datepickerInit();
    	if(scope._orderMateriel){
    		scope._orderMateriel.deliveryDate = date1;
    		scope._orderMateriel.lastDeliveryDate = date2;
    	}
    	$scope.changeFlag = true;
    	/*scope.saleOrder.orderDate = date3;*/
   };
   
   
   $scope.renderDone = function(){
	   var date3= $scope.saleOrder.orderDate;
	   	var date4= $scope.saleOrder.makeDate;
	   /*	var date5= $scope.clauseCheckAccept.playCheckDate*/
	   	$scope.datepickerInit();
	   	$scope.saleOrder.orderDate = date3;
	   	$scope.saleOrder.makeDate = date4;
	   	/*$scope.clauseCheckAccept.playCheckDate = date5;*/
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
  var  validate = function() {
	if(isNull($scope.saleOrder.buyComId)) {
		$("#checkBuyComId").addClass("has-error");
		return;
	} 
  }
  var validateInit= function(){
		 
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
	                   if ( this.name in rulesCache || !validator.objectLength($(this).rules()) ) {
	                       return false;
	                   }
	                   rulesCache[this.name] = true;
	                   return true;
	               });
	           }
	    }
		 
	 }
  $scope.confirmSave  = function(type) {
		if(!isNull(type)){
			$('#editBuyComIdModal').modal('hide');// 删除成功后关闭模态框
			$scope.saleOrder.deleteMaterielFlag = type
			if(type==1){
  				$scope.orderMateriel = [];
  			}
		}
		orderService.save($scope.saleOrder).then(
     		     function(data){
     		    	$scope.saleOrder = data;
     		    	$scope.oldBuyComId=data.buyComId;//记录初始的采购商id，用于保存时检查采购商是否修改
     		    	$scope.contract.orderSerial = data.serialNum;
     		    	if(isNull($scope.contract.contractNum)){
     		    		$scope.contract.contractNum = $scope.saleOrder.orderNum;
     		    	}
	   	    		$scope.contract.comId = $scope.saleOrder.buyComId;
//	   	    		$scope.contract.signDate = $scope.saleOrder.orderDate;
	   	    		//根据买方comId获取采购商联系地址
	   	    	initBuyComAddress($scope.saleOrder.buyComId);
  	   	    	if($scope.contract.contractType=='销售订单'){
		    		$scope.contract.contractNum = null;
		    	}
	   	    		orderService.saveContract($scope.contract).then(
	   	       		     function(data){
	   	       		    	toastr.success('数据保存成功！');
	   	       		    	$scope.contract = data.data;
	   	       		     },
	   	       		     function(error){
	   	       		    	toastr.error('数据保存出错！');
	   	       		         $scope.error = error;
	   	       		     }
	   	       		 );
     		    	/*$location.search({serialNum:data.serialNum,view:1});*/
     		    	$scope.saleOrderInput = true;
     			    $scope.saleOrderShow = true;
     			    
	       			 if(!isNull($stateParams.demandPlanSerial)&&!isNull($stateParams.materiels)&&$scope.isInit){
	         			getDemandPlanMateriels($stateParams.materiels);
	         			$scope.isInit = false;
	       			 }
     		     },
     		     function(error){
     		         $scope.error = error;
     		         toastr.error('数据保存出错！');
     		     }
     		 );
	}
    $scope.save  = function() {
    	if($('#form_sample_1').valid()){//
    		if($scope.saleOrder.orderDate=='') {// 日期为空的处理
    			$scope.saleOrder.orderDate=null;
    		}

    		// 保存数据处理
// $scope.saleOrder.parentSaleOrder=null;
// $scope.saleOrder.createTime=null;
// $scope.saleOrder.updateTime=null;
    		// **********//
    		orderService.checkNum($scope.saleOrder).then(
         		     function(data){
         		    	 if(data>0){
         		    		toastr.error('订单编号重复！');
         		    	 }else{

         		    		if(!isNull($scope.oldBuyComId)&&$scope.oldBuyComId!=$scope.saleOrder.buyComId){//验证采购商已修改，不能自动保存
         	    				$('#editBuyComIdModal').modal('show');//显示弹框
         	    			}else{
         	    				$scope.confirmSave();
         	    			}
         		    	 }
         		     },
         		     function(error){
         		         $scope.error = error;
         		         toastr.error('数据连接出错！');
         		     }
         		 );
    	}
    	
    }; 
    $scope.goVerificate= function(serialNum) {//去核销
    	$state.go('viewGatheringMoney',{serialNum:serialNum});
    };
    $scope.autoSave  = function() {
		$rootScope.judgeIsExist("order",$scope.saleOrder.orderNum, $scope.saleOrder.serialNum,function(result){
			var 	isExist = result;
		if(isExist){
			toastr.error('订单编号重复！');
		}else{
			//先验证订单价格
			if($state.current.name=="addSaleOrder"&&$scope.saleOrderInput != true&&$('#form_sample_1').valid()){//处于编辑状态且验证通过
				if(!isNull($scope.oldBuyComId)&&$scope.oldBuyComId!=$scope.saleOrder.buyComId){//验证采购商已修改，不能自动保存
    				return;
    			}
				orderService.save($scope.saleOrder).then(
	         		     function(data){
	         		    	$scope.saleOrder = data;
	         		    	$scope.oldBuyComId=data.buyComId;//记录初始的采购商id，用于保存时检查采购商是否修改
	         		    	$scope.contract.orderSerial = data.serialNum;
	  	   	    		$scope.contract.comId = $scope.saleOrder.buyComId;
		  	   	    	if($scope.contract.contractType=='销售订单'){
	    		    		$scope.contract.contractNum = null;
	    		    	}
	  	   	    		orderService.saveContract($scope.contract).then(
	  	   	       		     function(data){
	  	   	       		    	toastr.success('订单自动保存成功！');
	  	   	       		    	$scope.contract = data.data;
	  	   	       		     },
	  	   	       		     function(error){
	  	   	       		    	toastr.error('订单自动保存出错！');
	  	   	       		         $scope.error = error;
	  	   	       		     }
	  	   	       		 );
	         		     },
	         		     function(error){
	         		         $scope.error = error;
	         		         toastr.error('订单自动保存出错！');
	         		     }
	         		 );
			}
		}
	});
	if($state.current.name=="addSaleOrder"){
		setTimeout($scope.autoSave, 300000);
	}
	
};
    
    $scope.cancel  = function() {// 取消编辑
    	if($scope.saleOrder.serialNum==null || $scope.saleOrder.serialNum=='') {// 如果是取消新增，返回列表页面
    		$state.go("saleOrder");
    		return;
		}
    	$scope.getSaleOrderInfo($scope.saleOrder.serialNum,$stateParams.taskId, $stateParams.comments,$stateParams.processInstanceId,$stateParams.isReject);
    	$scope.cancelOrder();
    	
    };
    $scope.cancelOrder  = function() {// 取消编辑订单信息
    	$scope.saleOrderInput = true;
	    $scope.saleOrderShow = true;
    };
    
    $scope.edit  = function() {// 进入编辑
    	$scope.saleOrderInput = false;
	    $scope.saleOrderShow = false;
    };
    
    $scope.viewSaleOrder = function(serialNum,businessType){
    	$state.go("viewSaleOrder",{serialNum:serialNum,businessType:businessType});
    }
    
    $scope.goContract = function(serialNum){
    	$state.go("userContract",{});
    }
    $scope.viewGraphTrace = function(processInstanceId){
    	graphTrace(processInstanceId,ctx);
    }
    
    $scope.showOutRecord=function(serialNum,count){
		$('#OutRecordInfo').modal('show');//显示弹框
		loadOutRecordTable(serialNum,count);
	}
    
    $scope.viewStockOutRecord = function(serialNum){
    	outRecordTable.destroy();
    	$state.go("stockOutView",{serialNum:serialNum});
    	
    }
	$scope.chooseSaleOrder=function(serialNum){
		
		if($("#"+serialNum).is(':checked')){//选中时加到serialNums中
    		if($scope.serialNums){
    			$scope.serialNums.push(serialNum);
    		}else{
    			$scope.serialNums=[];
    			$scope.serialNums.push(serialNum);
    		}
    	}else{
    		if($scope.serialNums.length > 0){
    			for(var i=0;i<$scope.serialNums.length;i++){
    				if(serialNum == $scope.serialNums[i]){
    					$scope.serialNums.splice(i,1);
    				}
    			}
    		}
    	}
	}
	 var  outRecordTable,tableUrl,type,tableId;// 出库记录弹框
 var loadOutRecordTable = function(serialNum,count) {
	 tableId="select_sample_outRecord";
	 $scope.totaOutRecordCount=count;
	 type="sale";
	  tableUrl="rest/order/getRecordList?serialNum="+serialNum+"&type="+type;
          a = 0;
          App.getViewPort().width < App.getResponsiveBreakpoint("md") ? $(".page-header").hasClass("page-header-fixed-mobile") && (a = $(".page-header").outerHeight(!0)) : $(".page-header").hasClass("navbar-fixed-top") ? a = $(".page-header").outerHeight(!0) : $("body").hasClass("page-header-fixed") && (a = 64);
         if(outRecordTable!=undefined){
       	  outRecordTable.destroy(); 
	 	    	 }
       
         outRecordTable = $("#"+tableId)
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
//              autoWidth:true,  
//              serverSide: true,
              ajax: tableUrl,//加载数据中 
              "aoColumns": [
                            { mData: 'stockInOutRecord.inOutNum' },
                            { mData: 'stockInOutRecord.inOutType' },
                            { mData: 'orderMateriel'},
                            { mData: 'orderMateriel'},
                            { mData: 'deliverDate' },
                            { mData: 'deliverCount' },
                            { mData: 'stockInOutRecord.stockDate' },
                            { mData: 'stockCount' },
                          /*  { mData: 'stockInOutRecord.outWarehouseName' },*/
                            { mData: 'stockInOutRecord.operator' }
                      ],
             'aoColumnDefs' : [{
					'targets' : 0,
					'sWidth': "10%",
					'render' : function(data,
							type, row, meta) {
							return     '<a href="javascript:void(0);" ng-click="viewStockOutRecord(\''+row.stockInOutRecord.serialNum+'\')">'+data+'</a>';
					},
					"createdCell": function (td, cellData, rowData, row, col) {
						 $compile(td)($scope);
				       }
				},{
					'targets' : 2,
					'render' : function(data,
							type, row, meta) {
						
						if(data==null){
							return "";
						}else if(data.materiel!=null){
							return data.materiel.materielName;
						}
						
					}
				},{
					'targets' : 3,
					'render' : function(data,
							type, row, meta) {
						if(data==null){
							return "";
							
						}else{
							return data.materiel.specifications;
						}
					}
				}],
						//stateSave:false,
						"fnInitComplete":function(settings) {//fnInitComplete stateLoadCallback
		                	 // CalTotaOutRecordCount();
		                   }

          });
		   return outRecordTable;
         
        
      };
    var table;
    var tableAjaxUrl = "rest/order/findOrderList?type=sale&selectFor=platformOrder";
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
                order: [[10, "asc"],[1, "desc"]],// 默认排序列及排序方式
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
                              { mData: 'buyName' },
                              { mData: 'materielCount' },
                              { mData: 'orderAmount' },
                              /*{ mData: 'deliveryMode' },*/
                              { mData: 'orderType' },
                              { mData: 'saleApplySerial' },
                              { mData: 'orderSerial' },
                              { mData: 'orderDate' },
                              { mData: 'status' },
                              { bVisible: false }/*,
                              { mData: 'processBase',
	                            	mRender:function(data,
	    									type, row, meta){
	                            		if(data!=""&&data!=null){
	                            			if(data.status=="PENDING"||data.status=="WAITING_FOR_APPROVAL"){
	    										return '<span  class="label label-sm label-warning ng-scope">审核中</span>';
	    									}else if(data.status=="APPROVAL_SUCCESS"){
	    										if(row.status==1){
	    											return '<span  class="label label-sm label-success ng-scope">待接收</span>';
	    										}else if(row.status==2){
	    											return '<span  class="label label-sm label-success ng-scope">待发货</span>';
	    										}else if(row.status==3){
	    											return '<span  class="label label-sm label-success ng-scope">待收货</span>';
	    										}else if(row.status==4){
	    											return '<span  class="label label-sm label-success ng-scope">部分收货</span>';
	    										}else if(row.status==5){
	    											return '<span  class="label label-sm label-success ng-scope">待检验</span>';
	    										}else if(row.status==6){
	    											return '<span  class="label label-sm label-success ng-scope">待入库</span>';
	    										}else if(row.status==7){
	    											return '<span  class="label label-sm label-success ng-scope">部分入库</span>';
	    										}else if(row.status==8){
	    											return '<span  class="label label-sm label-success ng-scope">待收票</span>';
	    										}else if(row.status==9){
	    											return '<span  class="label label-sm label-success ng-scope">部分开票</span>';
	    										}else if(row.status==10){
	    											return '<span  class="label label-sm label-success ng-scope">待付款</span>';
	    										}else if(row.status==11){
	    											return '<span  class="label label-sm label-success ng-scope">部分付款</span>';
	    										}else if(row.status==12){
	    											return '<span  class="label label-sm label-success ng-scope">已完成</span>';
	    										}else if(row.status==13){
	    											return '<span  class="label label-sm label-success ng-scope">已取消</span>';
	    										}else{
	    											return '<span  class="label label-sm label-success ng-scope">待接收</span>';
	    										}
	    										
	    									}else if(data.status=="APPROVAL_FAILED"){
	    										return '<span  class="label label-sm label-danger ng-scope">未通过</span>';
	    									}else{
	    										return '<span  class="label label-sm label-info ng-scope">未审批</span>';
	    									}
	                            		}else{
	                            			return '<span  class="label label-sm label-info ng-scope">未审批</span>';
	                            		}
	                            	}
	                            }*/

                        ],
               'aoColumnDefs' : [ {
							'targets' : 0,
							'searchable' : false,
							'orderable' : false,
							'render' : function(data,
									type, full, meta) {
//								return "<label class='mt-checkbox mt-checkbox-single mt-checkbox-outline'>" +
//								"<input type='checkbox' class='checkboxes' value="+ data +" />" +
//								"<span></span></label>";
								return '<label class="mt-checkbox  mt-checkbox-outline">' +
								'<input type="checkbox" class="checkboxes"  id="'+data+'" value="'+data+'"   ng-click="chooseSaleOrder(\''+full.serialNum+'\')"/>' +
								'<span></span></label>';
							},
							"createdCell": function (td, cellData, rowData, row, col) {
								 $compile(td)($scope);
						       }
						},{
							'targets' : 1,
							'render' : function(data,
									type, row, meta) {
								var clickhtm = '<a href="javascript:void(0);" ng-click="viewSaleOrder(\''+row.serialNum+'\')">'+data+'</a></br>';
								/*if((Number(row.receiveCount)==Number(row.deliveryCount))&&(Number(row.payAmount)==Number(row.orderAmount))){
									return clickhtm+ '<span ng-click="viewOrderLog(\''+row.serialNum+'\')"  style="color:green">已完成</span>';
								}*/
								if(row.status==55){
									return clickhtm + '<span  ng-click="viewOrderLog(\''+row.serialNum+'\')" style="color:#fcb95b">待接收</span>';
								}else if(row.processBase!=""&&row.processBase!=null){
                        			if(row.processBase.status=="PENDING"||row.processBase.status=="WAITING_FOR_APPROVAL"){
										return clickhtm + '<span ng-click="viewGraphTrace('+row.processBase.processInstanceId+')" style="color:#fcb95b">审核中</span>';
									}else if(row.processBase.status=="APPROVAL_SUCCESS"){
										if(row.status==1){
											return clickhtm + '<span  ng-click="viewOrderLog(\''+row.serialNum+'\')" style="color:#fcb95b">已审批</span>';//待签合同
										}else if(row.status==3){
											return clickhtm + '<span  ng-click="viewOrderLog(\''+row.serialNum+'\')" style="color:#fcb95b">已审批</span>';//待签合同
										}else if(row.status==2){
											if(row.contract.contractType=='销售订单'){
												return clickhtm + '<span  ng-click="viewOrderLog(\''+row.serialNum+'\')" style="color:green">已审批</span>';
											}else{
												return clickhtm + '<span  ng-click="viewOrderLog(\''+row.serialNum+'\')" style="color:green">已审批</span>';//已签合同
											}
										}else{
											return clickhtm + '<span  ng-click="viewOrderLog(\''+row.serialNum+'\')" style="color:green">已确认</span>';
										}
									}else if(row.processBase.status=="APPROVAL_FAILED"){
										return clickhtm + '<span  ng-click="viewOrderLog(\''+row.serialNum+'\')" style="color:red">未通过</span>';
									}else{
										return clickhtm + '<span ng-click="viewOrderLog(\''+row.serialNum+'\')">待审批</span>';
									}
                        		}else{
                        			return clickhtm + '<span ng-click="viewOrderLog(\''+row.serialNum+'\')">待审批</span>';
                        		}
								
							
							},
							"createdCell": function (td, cellData, rowData, row, col) {
								 $compile(td)($scope);
						       }
						},{
							'targets' : 2,
							'render' : function(data,
									type, row, meta) {
								var htm = (data==null?'':data);
								return htm;
							},
							"createdCell": function (td, cellData, rowData, row, col) {
								 $compile(td)($scope);
						       }
						},{
							'targets' : 3,
							'render' : function(data,
									type, row, meta) {
								var htm = (isNull(data)?'<span style="color:#FCB95B">0</span>':'<span style="color:#FCB95B">'+data+'</span>')+'</br>'
								/*if(isNull(row.deliveryCount)||row.deliveryCount==0){
									htm = (isNull(data)?'<span style="color:#FCB95B">0</span>':'<span style="color:#FCB95B">'+data+'</span>')+'（已发'+'<span style="color:#FCB95B">0</span>'+'）'+'</br>'
								}else{
									htm = (isNull(data)?'<span style="color:#FCB95B">0</span>':'<span style="color:#FCB95B">'+data+'</span>')+'（已发 '+'<span style="color:#FCB95B">'+row.deliveryCount+'</span>'+'）'+'</br>'
								}*/
								if(isNull(row.receiveCount)||row.receiveCount==0){
									htm = (isNull(data)?'<span style="color:#FCB95B">0</span>':'<span style="color:#FCB95B">'+data+'</span>')+'（已发'+'<span style="color:#FCB95B">0</span>'+'）'+'</br>'//<a href="javascript:void(0);" ng-click="showOutRecord(\''+row.serialNum+'\',\'0\')"></a>
								}else{
									htm = (isNull(data)?'<span style="color:#FCB95B">0</span>':'<span style="color:#FCB95B">'+data+'</span>')+'（<a href="javascript:void(0);" ng-click="showOutRecord(\''+row.serialNum+'\',\''+row.receiveCount+'\')">已发</a>'+'<span style="color:#FCB95B">'+row.receiveCount+'</span>'+'）'+'</br>'
								}
                    			if(row.deliverStatus=="0"||row.deliverStatus==null){
                    				if(row.status==2){
										return htm + '<span style="color:#999">待发货</span>';
									}else{
										return htm + '<span  style="color:#999">未开始</span>';
									}
								}else if(row.deliverStatus=="1"){
                    				return htm + '<span style="color:#999" ng-click="viewDeliverLog(\''+row.serialNum+'\')">已发货</span>';
								}else if(row.deliverStatus=="2"){
                    				return htm + '<span style="color:#999" ng-click="viewDeliverLog(\''+row.serialNum+'\')">已收货</span>';
								}else if(row.deliverStatus=="3"){
                    				return htm + '<span style="color:#999" ng-click="viewDeliverLog(\''+row.serialNum+'\')">待出库</span>';
								}else if(row.deliverStatus=="4"){
                    				return htm + '<span style="color:#999" ng-click="viewDeliverLog(\''+row.serialNum+'\')">已出库</span>';
								}else if(row.deliverStatus=="5"){
                    				return htm + '<span style="color:#999" ng-click="viewDeliverLog(\''+row.serialNum+'\')">已入库</span>';
								}else if(row.deliverStatus=="6"){
                    			//	return htm + '<span style="color:#999" ng-click="viewDeliverLog(\''+row.serialNum+'\')">待清关</span>';
									return htm + '<span style="color:#999" ng-click="viewDeliverLog(\''+row.serialNum+'\')">待入库</span>';
								}else if(row.deliverStatus=="7"){
                    				//return htm + '<span style="color:#999" ng-click="viewDeliverLog(\''+row.serialNum+'\')">待报关</span>';
									return htm + '<span style="color:#999" ng-click="viewDeliverLog(\''+row.serialNum+'\')">待出库</span>';
								}else if(row.deliverStatus=="8"){
                    				return htm + '<span style="color:#999" ng-click="viewDeliverLog(\''+row.serialNum+'\')">待检验</span>';
								}else if(row.deliverStatus=="9"){
                    				return htm + '<span style="color:#999" ng-click="viewDeliverLog(\''+row.serialNum+'\')">待检验</span>';
								}else if(row.deliverStatus=="11"){
                    				return htm + '<span style="color:#999" ng-click="viewDeliverLog(\''+row.serialNum+'\')">待入库</span>';
								}else if(row.deliverStatus=="12"){
                    				return htm + '<span style="color:#999" ng-click="viewDeliverLog(\''+row.serialNum+'\')">待出库</span>';
								}else if(row.deliverStatus=="13"){
                    				return htm + '<span style="color:#999" ng-click="viewDeliverLog(\''+row.serialNum+'\')">已报关</span>';
								}else if(row.deliverStatus=="88"){
                    				return htm + '<span style="color:#999" ng-click="viewDeliverLog(\''+row.serialNum+'\')">待收货</span>';
								}else{
									return htm + '<span  style="color:#999">未开始</span>';
								}
							},
							"createdCell": function (td, cellData, rowData, row, col) {
								 $compile(td)($scope);
						       }
						},{
							'targets' : 4,
							'render' : function(data,
									type, row, meta) {
								var htm = (isNull(data)?'<span style="color:#FCB95B">0</span>':'<span style="color:#FCB95B">'+data+'</span>')+'</br>'
								if(isNull(row.payAmount)||row.payAmount==0){
									htm = (isNull(data)?'<span style="color:#FCB95B">0</span>':'<span style="color:#FCB95B">'+data+'</span>')+'（已收'+'<span style="color:#FCB95B">0</span>'+'）'+'</br>'
								}else{
									htm = (isNull(data)?'<span style="color:#FCB95B">0</span>':'<span style="color:#FCB95B">'+data+'</span>')+
									'（<a href="javascript:void(0);" ng-click="viewPayLog(\''+row.serialNum+'\')">已收</a>'+'<span style="color:#FCB95B">'+row.payAmount+'</span>'+'）'+'</br>'
									
								}

                    			if(row.payStatus=="0"){
                    				return htm + '<span style="color:#999" ng-click="viewPayLog(\''+row.serialNum+'\')">收款中</span>';
								}else if(row.payStatus=="1"){
                    				return htm + '<span style="color:#999" ng-click="viewPayLog(\''+row.serialNum+'\')">已收款</span>';
								}else if(row.payStatus=="2"){
                    				return htm + '<span style="color:#999" ng-click="viewPayLog(\''+row.serialNum+'\')">已收款</span>';
								}else if(row.payStatus=="3"){
                    				return htm + '<span style="color:#999" ng-click="viewPayLog(\''+row.serialNum+'\')">开票中</span>';
								}else if(row.payStatus=="4"){
                    				return htm + '<span style="color:#999" ng-click="viewPayLog(\''+row.serialNum+'\')">已开票</span>';
								}else if(row.payStatus=="5"){
                    				return htm + '<span style="color:#999" ng-click="viewPayLog(\''+row.serialNum+'\')">已收票</span>';
								}else{
									return htm + '<span  style="color:#999">未开始</span>';
								}
							},
							"createdCell": function (td, cellData, rowData, row, col) {
								 $compile(td)($scope);
						       }
						}, {
							'targets' : 5,
							'render' : function(data,
									type, row, meta) {
								return data +'</br>' + row.tradeType;
							}
						}, {
							'targets' : 6,
							'render' : function(data,
									type, row, meta) {
								if(isNull(row.contract)||isNull(row.contract.contractNum)){
									return ""
								}else{
									return '<a href="javascript:void(0);" ng-click="goContract()">'+row.contract.contractNum+'</a>' 
								}
							},
							"createdCell": function (td, cellData, rowData, row, col) {
								 $compile(td)($scope);
						       }
						}, {
							'targets' : 7,
							'render' : function(data,
									type, row, meta) {
								if(isNull(data)){
									return "--"
								}else{
									return data
								}
							}
						}, {
							'targets' : 8,
							'render' : function(data,
									type, row, meta) {
								return data +'</br>' + row.maker;
							}
						},{
							'targets' : 9,
							'render' : function(data,
									type, row, meta) {
								var clickhtm = ''
								if(row.status==55){
									return clickhtm + '<a href="javascript:void(0);" ng-click="submitPage(\''+row.serialNum+'\')">接收</a>';//orderAmount
								} else if(row.status==0&&(row.processBase==null)){
									return clickhtm + '<a href="javascript:void(0);" ng-click="submitSaleApply(\''+row.serialNum+'\',\''+row.orderAmount+'\')">申请</a><br/>';
									
								}else if(row.processBase!=""&&row.processBase!=null){
                        			if(row.processBase.status=="PENDING"||row.processBase.status=="WAITING_FOR_APPROVAL"){
										return clickhtm + '';
									}else if(row.processBase.status=="APPROVAL_SUCCESS"){
										if(row.status==1){
											return clickhtm + '<a href="javascript:void(0);" ng-click="signContract(\''+row.contract.id+'\',\''+row.contract.comId+'\')">签订</a>';
										}else if(row.status==3){
											return clickhtm + '<a href="javascript:void(0);" ng-click="signContract(\''+row.contract.id+'\',\''+row.contract.comId+'\')">签订</a>';
										}else if(row.status==2){
											if((isNull(row.deliveryCount)||row.deliveryCount==0)||Number(row.materielCount)>Number(row.deliveryCount)){
												clickhtm= clickhtm + '<a href="javascript:void(0);" ng-click="deliveryAdd(\''+row.serialNum+'\')">发货</a><br/>';
												
												if(row.orderType =='委托销售'){
													clickhtm= clickhtm + '<a href="javascript:void(0);" ng-click="saleGenerateBuy(\''+row.serialNum+'\')">分解采购</a><br/>';
												}
												if(row.orderType =='自主销售'){
													clickhtm= clickhtm + '<a href="javascript:void(0);" ng-click="addBuyOrder(\''+row.serialNum+'\')">新建采购</a><br/>';
												}
												
											}
											if((isNull(row.payAmount)||row.payAmount==0||Number(row.payAmount)<Number(row.orderAmount))){
												clickhtm= clickhtm + '<a href="javascript:void(0);" ng-click="goCollectMoney(\''+row.serialNum+'\')">收款</a><br/>'
											}
											if((isNull(row.payReceiptMoney)||row.payReceiptMoney==0||Number(row.payReceiptMoney)<Number(row.orderAmount))){
												clickhtm= clickhtm + '<a href="javascript:void(0);" ng-click="goOpenInvoice(\''+row.serialNum+'\')">开票</a><br/>';
											}
											/* if(Number(row.materielCount)>Number(row.deliveryCount)){
												if((isNull(row.payAmount)||row.payAmount==0||Number(row.payAmount)<Number(row.orderAmount))){
													clickhtm= clickhtm + '<a href="javascript:void(0);" ng-click="goCollectMoney(\''+row.serialNum+'\')">收款</a><br/>'
													+'<a href="javascript:void(0);" ng-click="goOpenInvoice(\''+row.serialNum+'\')">开票</a><br/>'
													+'<a href="javascript:void(0);" ng-click="deliveryAdd(\''+row.serialNum+'\')">发货</a>';
													} else if((isNull(row.payAmount)||row.payAmount==0||Number(row.payAmount)<Number(row.orderAmount))){
														clickhtm= clickhtm + '<a href="javascript:void(0);" ng-click="goCollectMoney(\''+row.serialNum+'\')">收款</a><br/>'
														+'<a href="javascript:void(0);" ng-click="deliveryAdd(\''+row.serialNum+'\')">发货</a>';
														}else if((Number(row.payAmount)==Number(row.orderAmount))){
															clickhtm= clickhtm + '<a href="javascript:void(0);" ng-click="goOpenInvoice(\''+row.serialNum+'\')">开票</a><br/>'
															+'<a href="javascript:void(0);" ng-click="deliveryAdd(\''+row.serialNum+'\')">发货</a>';
															}
											if(Number(row.materielCount)==Number(row.deliveryCount)){
												if((isNull(row.payAmount)||row.payAmount==0||Number(row.payAmount)<Number(row.orderAmount))&&(Number(row.payReceiptMoney)==Number(row.orderAmount))){
													return clickhtm + '<a href="javascript:void(0);" ng-click="goCollectMoney(\''+row.serialNum+'\')">收款</a><br/>'
													+'<a href="javascript:void(0);" ng-click="goOpenInvoice(\''+row.serialNum+'\')">开票</a><br/>'
													}else if((Number(row.payAmount)==Number(row.orderAmount))&&(Number(row.payReceiptMoney)<Number(row.orderAmount))){
														return clickhtm + '<a href="javascript:void(0);" ng-click="goOpenInvoice(\''+row.serialNum+'\',\''+row.unBillOrReceiptMoney+'\')">开票</a><br/>'
														}else if((isNull(row.payAmount)||row.payAmount==0||Number(row.payAmount)<Number(row.orderAmount))&&(Number(row.payReceiptMoney)==Number(row.orderAmount))){
														return clickhtm + '<a href="javascript:void(0);" ng-click="goCollectMoney(\''+row.serialNum+'\')">收款</a><br/>'
														}
												if(isNull(row.payAmount)||row.payAmount==0||Number(row.payAmount)<Number(row.orderAmount)){
													return clickhtm + '<a href="javascript:void(0);" ng-click="goCollectMoney(\''+row.serialNum+'\')">收款</a><br/>'
													+'<a href="javascript:void(0);" ng-click="goOpenInvoice(\''+row.serialNum+'\')">开票</a><br/>';
													}
											}*/
												return clickhtm ;
											
										}else if(isNull(row.receiveCount)||row.receiveCount<row.materielCount){
											return clickhtm + '<a href="javascript:void(0);" ng-click="deliveryAdd(\''+row.serialNum+'\')">发货</a>';
										}else{
											return clickhtm + '';
										}
									}else if(row.processBase.status=="APPROVAL_FAILED"){
										return clickhtm + '';
									}else{
										return clickhtm + '';
									}
                        		}else{
                        			return clickhtm + '';
                        		}
								
							
							},
							"createdCell": function (td, cellData, rowData, row, col) {
								 $compile(td)($scope);
						       }
						}, {
							'targets' : 10,
							'render' : function(data,
									type, row, meta) {
								var renderRow = meta.settings.aoData[meta.row];
								return 1 ;
//								return returnMin(
//											returnMin(
//													diySortFlag(renderRow.anCells[1].textContent),diySortFlag(renderRow.anCells[3].textContent)
//													),
//											diySortFlag(renderRow.anCells[4].textContent)
//											)
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
        
        
        var framTable;
        var framTableAjaxUrl = "rest/order/findOrderList?type=sale&&fram=1";
        var loadMainFramTable = function() {
                a = 0;
                App.getViewPort().width < App.getResponsiveBreakpoint("md") ? $(".page-header").hasClass("page-header-fixed-mobile") && (a = $(".page-header").outerHeight(!0)) : $(".page-header").hasClass("navbar-fixed-top") ? a = $(".page-header").outerHeight(!0) : $("body").hasClass("page-header-fixed") && (a = 64);
                framTable = $("#sample_3")
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
                    ajax: framTableAjaxUrl,// 加载数据中
                    "aoColumns": [
                                  { mData: 'serialNum' },
                                  { mData: 'orderNum' },
                                  { mData: 'buyName' },
                                  { mData: 'materielCount' },
                                  { mData: 'orderAmount' },
                                  /*{ mData: 'deliveryMode' },*/
                                  { mData: 'orderType' },
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
    								return "<label class='mt-checkbox mt-checkbox-single mt-checkbox-outline'>" +
    								"<input type='checkbox' class='checkboxes' value="+ data +" />" +
    								"<span></span></label>";
    							},
    							"createdCell": function (td, cellData, rowData, row, col) {
    								 $compile(td)($scope);
    						       }
    						}, {
								'targets' : 6,
								'render' : function(data,
										type, row, meta) {
									if(isNull(row.contract)){
										return ""
									}else{
										return row.contract.contractNum
									}
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
    						var rows = framTable.rows({
    							'search' : 'applied'
    						}).nodes();
    						$('input[type="checkbox"]', rows).prop(
    								'checked', this.checked);
    					});
    	
    			// Handle click on checkbox to set state of "Select
    			// all" control
    			$('#sample_3 tbody')
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
    			$("#sample_3").find(".group-checkable").change(function() {
    	            var e = jQuery(this).attr("data-set"),
    	            t = jQuery(this).is(":checked");
    	            jQuery(e).each(function() {
    	                t ? ($(this).prop("checked", !0), $(this).parents("tr").addClass("active")) : ($(this).prop("checked", !1), $(this).parents("tr").removeClass("active"))
    	            })
    	        }),
    	        $("#sample_3").on("change", "tbody tr .checkboxes",
    	        function() {
    	            $(this).parents("tr").toggleClass("active")
    	        })
            };
        
         // 弹出确认删除模态框
            $scope.deleteSaleFramOrder = function() {
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
        			$('#delSaleOrderModal').modal('show');// 弹出删除确认模态框
        		}
    			
    		};
    		
    		$scope.editSaleFramOrder  = function() {// 进入编辑页面
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
        		
        		$state.go("addSaleOrder",{serialNum:ids});
            };
        // 弹出确认删除模态框
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
		
		$scope.editSaleOrder  = function() {// 进入编辑页面
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
    			var status = table.row('.active').data().status;
    			if(status == 55){
    				showToastr('toast-top-center', 'warning', '未接收订单，不能修改！')
    			}else if(processBase != null){
    				showToastr('toast-top-center', 'warning', '该订单已发起流程审批，不能修改！')
    			}else $state.go('addSaleOrder',{serialNum:table.row('.active').data().serialNum});
    		}
    		/*$state.go("addSaleOrder",{serialNum:ids});*/
        };
        
        $scope.copyOrder  = function() {
    		if(table.rows('.active').data().length != 1){
    			showToastr('toast-top-center', 'warning', '请选择一个订单！')
    		}else{
    			handle.blockUI();
    			orderService
				.copyOrder(table.row('.active').data().serialNum)
				.then(
						function(data) {
							handle.unblockUI();
							toastr.success('订单复制成功！');
							 $state.go('saleOrder',{},{reload:true});
							 
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
			
			orderService
					.delOrder(ids)
					.then(
							function(data) {
								$('#delSaleOrderModal').modal(
										'hide');// 删除成功后关闭模态框
								$(".modal-backdrop").remove();
								/* table.ajax.reload(); // 重新加载datatables数据 */
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
        
		jQuery.validator.addMethod("noFrameFlag", function(value, element) {  
			if($scope.contract.contractType!='销售订单'){
				return true;
			}else{
				if(isNull($scope.saleOrder.frame)){
					return false;    
				}else{
					return true;  
				}
			}
			
		}, "框架协议不能为空"); 
		
		
		/*var validateInit = function() {
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
	            	buyComId:{required:"采购商不能为空！"},
	            	serviceModel:{required:"服务模式不能为空！"},
	            	settlementClause:{required:"结算条款不能为空！"},
	            	deliveryMode:{required:"提货方式不能为空！"},
	            	rate:{required:"税率不能为空！"},
	            	tuirate:{required:"退税率不能为空！"},
	            	currency:{required:"币种不能为空！"},
	            	maker:{required:"制单人不能为空！"},
	            	seller:{required:"供应商不能为空！"},
	            	orderDate:{required:"下单日期不能为空！"},
	            	frameNum:{noFrameFlag:"框架协议不能为空！"}
	            },
            	rules: {orderNum: {required: !0,maxlength: 20},
            		orderType: {required: !0,maxlength: 20},
            		buyComId: {required: !0,maxlength: 20},
            		serviceModel: {required: !0,maxlength: 20},
            		settlementClause: {required: !0,maxlength: 20},
            		deliveryMode: {required: !0,maxlength: 20},
            		rate: {required: !0,maxlength: 20},
            		currency: {required: !0,maxlength: 20},
            		maker: {required: !0,maxlength: 20},
	            	seller:{required: !0,maxlength: 20},
	            	tuirate:{required: !0,maxlength: 20},
            		orderDate: {required: !0},
            		frameNum:{noFrameFlag:true}
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
		}*/
       $scope.$watch('$viewContentLoaded', function() { 
        	var e = $("#form_sample_1"),
	        r = $(".alert-danger", e),
	        i = $(".alert-success", e);
	        e.validate({
	            errorElement: "span",
	            errorClass: "help-block help-block-error",
	            focusInvalid: !1,
	            ignore: "hidden",
	            messages: {
	            	orderNum:{required:"销售订单号不能为空！"},
	            	orderType:{required:"销售类型不能为空！"},
	            	buyComId:{required:"采购商不能为空！"},
	            	serviceModel:{required:"服务模式不能为空！"},
	            	settlementClause:{required:"结算条款不能为空！"},
	            	deliveryMode:{required:"提货方式不能为空！"},
	            	rate:{required:"税率不能为空！"},
	            	tuirate:{required:"退税率不能为空！"},
	            	currency:{required:"币种不能为空！"},
	            	maker:{required:"制单人不能为空！"},
	            	seller:{required:"供应商不能为空！"},
	            	orderDate:{required:"下单日期不能为空！"},
	            	frameNum:{noFrameFlag:"框架协议不能为空！"}
	            },
            	rules: {orderNum: {required: !0,maxlength: 20},
            		orderType: {required: !0,maxlength: 20},
            		buyComId: {required: !0,maxlength: 20},
            		serviceModel: {required: !0,maxlength: 20},
            		settlementClause: {required: !0,maxlength: 20},
            		deliveryMode: {required: !0,maxlength: 20},
            		rate: {required: !0,maxlength: 20},
            		currency: {required: !0,maxlength: 20},
            		maker: {required: !0,maxlength: 20},
	            	seller:{required: !0,maxlength: 20},
	            	tuirate:{required: !0,maxlength: 20},
            		orderDate: {required: !0},
            		frameNum:{noFrameFlag:true}
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
        });
        
        /**
		 * 获取订单信息
		 */	
        $scope.getSaleOrderInfo  = function(serialNum,taskId,comments,processInstanceId,isReject) {
        	orderService.getOrderInfo(serialNum).then(
          		     function(data){//加载页面对象
          		    	
          		    	$scope.saleOrder=data.orderInfo;
          		    	$scope.oldBuyComId=data.orderInfo.buyComId;//记录初始的采购商id，用于保存时检查采购商是否修改
          		    	$scope.orderMateriel=data.orderMateriel;
          		    	$scope.cancelAllOrderMateriel();
          		    	if($state.current.name=="viewSaleOrder"
          		    		||$state.current.name=="submitSaleApply"
          		    			||$state.current.name=="approvalSaleApply"){//查看页面构造物料查询分页
          		    		$scope.queryForPage();
          		    		if($state.current.name=="viewSaleOrder"&&$stateParams.businessType!=undefined){
          		    			$scope.hideAllBtn=true;
          		    			
          		    		}
          		    	}
          		    	$scope.isReject=isReject;
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
          		    	if(isNull($scope.clauseSettlement.otherAmount)){
          		    		$scope.clauseSettlement.otherAmount = 0;
          		    	}
          		    	if($scope.saleOrder.status==1){//已提交的不能做提交
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
        					
        					dateSelectSetting();//日期选择限制
             		    	if(!isNull($scope.contract.signDate)){
             		    		$("#startDate").datepicker('setStartDate',$scope.contract.signDate);
         			        	$("#endDate").datepicker('setStartDate',$scope.contract.signDate);
             		    	}
             		        
             		    	if(!isNull($scope.contract.startDate)){
             			        	$("#signDate").datepicker('setEndDate',$scope.contract.startDate);
             			        	$("#endDate").datepicker('setStartDate',$scope.contract.startDate);
             			    }  
             		    	if(!isNull($scope.contract.signDate)){
             			        	$("#signDate").datepicker('setEndDate',$scope.contract.signDate);
             			        	$("#startDate").datepicker('setEndDate',$scope.contract.signDate);
             				} 
          		    	}else{
          		    		$scope.contract = {};
          		    	}
          		    	 
          		    	$scope.copyMateriels = angular.copy($scope.orderMateriel);
          		    	//加载客户
                    	initCustomers();
                    	//initWarehouse($scope.saleOrder.buyComId);
                    	//initPtWarehouseAddress();
                    	$("#serialNum").val(serialNum);//赋值给隐藏input，通过和不通过时调用
    					$("#taskId").val(taskId);//赋值给隐藏input，通过和不通过时调用
    					$("#processInstanceId").val(processInstanceId);//赋值给隐藏input，通过和不通过时调用
    					
    					if(comments == ""||comments == null){
    						$("#comment_audit").html( "<tr><td colspan='4' align='center'>无内容</td></tr>");
    					}else $("#comment_audit").html(comments);
    					
    					//初始化合同内容
                    	if(isNull($scope.saleOrder.contractContent)){
                    		$scope.saleOrder.contractContent = '111100';
                    	}
                    	$scope.initContractContent();
                    	initBuyComAddress($scope.saleOrder.buyComId);
          		     },
          		     function(error){
          		         $scope.error = error;
          		     }
          		 );
        	
        }; 
        
        
        /** *************订单物料操作 start*************** */
        
        var selectMateriel = function() {
        	var thisAjaxUrl = "rest/materiel/findMaterielList?isLatestVersion=1&type=sale&buyComId="+$scope.saleOrder.buyComId
        	var thisAoColumns = [
                             { mData: 'serialNum' },
                             { mData: 'materielNum' },
                             { mData: 'materielName' },
                             { mData: 'specifications' },
                             { mData: 'unit' },
                             { mData: 'supplyMateriels' }/*,
                             { mData: 'stockCount' }*/
                             
                       ];
        	var thisAoColumnDefs = [ {
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
								return "<label class='mt-checkbox mt-checkbox-single mt-checkbox-outline'>" +
								"<input type='checkbox' class='checkboxes' data-checked=false   name='"+meta.row+"'   id='"+ row.serialNum +"' ng-click='getCheckedIds(\""+data+"\","+meta.row+")' value="+ row.supplyMateriels[0].serialNum +" />" +
								"<span></span></label>";
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

				}];
        	//自主销售不需要供应商
        	if($scope.saleOrder.orderType=='自主销售'){
        		thisAjaxUrl = "rest/materiel/findMaterielList?isLatestVersion=1&buyComId="+$scope.saleOrder.buyComId;
        		thisAoColumns = [
                                 { mData: 'serialNum' },
                                 { mData: 'materielNum' },
                                 { mData: 'materielName' },
                                 { mData: 'specifications' },
                                 { mData: 'unit' }/*,
                                 { mData: 'stockCount' }*/
                           ];
        		var thisAoColumnDefs = [ {
    				'targets' : 0,
    					'searchable' : false,
    					'orderable' : false,
    					'render' : function(data,
    							type, row, meta) {
    						return "<label class='mt-checkbox mt-checkbox-single mt-checkbox-outline'>" +
								"<input type='checkbox' class='checkboxes' data-checked=false  id='"+ row.serialNum +"' ng-click='ziZhuGetCheckedIds(\""+data+"\","+meta.row+")' name='"+meta.row+"' value="+ row.serialNum +" />" +
								"<span></span></label>";
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

    				}];
        	}
        	
        	
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
                     ajax: thisAjaxUrl,// 加载数据中
                     "aoColumns": thisAoColumns,
                    'aoColumnDefs' : thisAoColumnDefs

                 }).on('order.dt',
                         function() {
                     console.log('排序');
                 }).on('page.dt', 
                 function () {
               	  console.log('翻页');
   	          }).on('draw.dt',function() {
   	        	  checkedIdHandler();
   	          });
                 
                 $("#select_sample_2").find(".group-checkable").change(function() {
     	            var e = jQuery(this).attr("data-set"),
     	            t = jQuery(this).is(":checked");
     	            jQuery(e).each(function() {
     	                t ? ($(this).prop("checked", !0), $(this).parents("tr").addClass("active")) : ($(this).prop("checked", !1), $(this).parents("tr").removeClass("active"))
     	            })
     	            if(t){//选中
     	            	$('input[type="checkbox"]:checked').each(
        						function() {
        							if ($.contains(document, this)) {
        								if(this.className!='group-checkable'){
        									$scope.ziZhuGetCheckedIdsOther(this.id,this.name);
        								}
        							}
        			});
     	            }else{
     	            	$('input[type="checkbox"]:checked').each(
        						function() {
        							if ($.contains(document, this)) {
        								this.checked=false;
        								$scope.serialNums=[];
        							}
        			});
     	            }
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
      		 * checkbox点击事件（生成选中内容）（自主销售）
      		 */
      		$scope.ziZhuGetCheckedIds = function(serialNum,index){
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
      		 * checkbox点击事件（生成选中内容）（自主销售）
      		 */
      		$scope.ziZhuGetCheckedIdsOther = function(serialNum,index){
      			var data={};
      			data.serialNum = serialNum;
      			data.materiel = table.row(index).data();
      			data.materiel.materielSerial = data.materiel.serialNum; //为保存操作做准备，新增物料serialNum为空
      			$scope.serialNums=[];
      			$scope.serialNums.push(data);
      			$("#"+serialNum).data("checked",true);
      			$("#"+serialNum).attr("checked",true)
      			/*if($("."+group-checkable).data("checked")||$("."+group-checkable).data("checked")==undefined){
      				$scope.serialNums.push(data);
      				$("#"+serialNum).data("checked",true);
      				$("#"+serialNum).attr("checked",true);
      			}else{
      				for(var i=0;i<$scope.serialNums.length;i++){
      					if($scope.serialNums[i].serialNum==serialNum){
      						$scope.serialNums.splice(i,1);
      						$("#"+serialNum).attr("checked",false);
      						$("#"+serialNum).data("checked",false);
      						break;
      					}
      				}
      			
      			}*/
      			
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
//    				table.ajax.reload();
    				table.ajax.url(ctx+"/rest/materiel/findMaterielList?isLatestVersion=1&buyComId="+$scope.saleOrder.buyComId).load()
    			}else{
    				selectMateriel();
    			}
    			
    			$("#basicMaterielInfo").modal("show");
    		}else{
    			$scope.modalType = 'multiple';
    			if(table){
//    				table.ajax.reload();
    				table.ajax.url(ctx+"/rest/materiel/findMaterielList?isLatestVersion=1&buyComId="+$scope.saleOrder.buyComId).load()
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
    	$scope.confirmSelect = function(){//非自主销售订单，选择供应物料确认
    		if($scope.modalType=='single'){
    			if(id_count==0){
					toastr.warning("请选择物料");
					return;
				}
    			var id =  $('input[name="serialNum"]:checked').val(); 
    			var promise = materielService.chooseMateriels(id,$scope.saleOrder.buyComId,'buyComId');
        		promise.then(function(data){
        			if($scope.materielSelectedIndex != undefined){
        				$scope.orderMateriel[$scope.materielSelectedIndex].supplyMaterielSerial = data.data[0].serialNum;
        				$scope.orderMateriel[$scope.materielSelectedIndex].materielSerial = data.data[0].materiel.serialNum;
        				$scope.orderMateriel[$scope.materielSelectedIndex].materiel = data.data[0].materiel;
        				$scope.orderMateriel[$scope.materielSelectedIndex].supplyMateriel = data.data[0];
        			}
        			initBuyComAddress($scope.saleOrder.buyComId);
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
        		var promise = materielService.chooseMateriels(ids,$scope.saleOrder.buyComId,'buyComId');
        		promise.then(function(data){
        			toastr.success("添加成功！");
        			handle.unblockUI();
        			if($scope.orderMateriel.length==0){
        				for(var i = 0;i < data.data.length;i++){// data.data为选择的供应物料
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
        				var length = $scope.orderMateriel.length; 
        				for(var i = 0;i < data.data.length;i++){// data.data为选择的供应物料
        					$scope.tempMateriel = {};
        					$scope.tempMateriel.materiel = (data.data)[i].materiel;
        					$scope.tempMateriel.orderSerial = $scope.saleOrder.serialNum;
        					$scope.tempMateriel.materielSerial = (data.data)[i].materiel.serialNum;
        					$scope.tempMateriel.supplyMaterielSerial = (data.data)[i].serialNum;
        					$scope.tempMateriel.supplyMateriel = (data.data)[i];
        					$scope.orderMateriel.push($scope.tempMateriel);
	        				$scope["orderMaterielInput"+(length+i)] = false;
							$scope["orderMaterielShow"+(length+i)] = false;
							/*$scope["orderMaterielInput" + ($scope.orderMateriel.length-1)] = true;
							$scope["orderMaterielShow" + ($scope.orderMateriel.length-1)] = true;*/
		        		}
        			}
        			$scope.copyMateriels = angular.copy($scope.orderMateriel);
        			$("#basicMaterielInfo").modal("hide");
        		},
	       		     function(error){
	       		    	toastr.error('数据保存出错！');
	       		    	handle.unblockUI();
	       		     });
    	}
    	
    	$scope.ziZhuConfirmSelect = function(){//自主销售订单，选择基本物料确认
    		var id_count = table.$('input[type="checkbox"]:checked').length;
    		if($scope.serialNums.length==0&&id_count==0){ //判断是否选择了物料
				toastr.warning("请选择物料");
				return;
			}
	    		//--------批量增加物料信息START--------------
    			var ids = '';
    			if($scope.serialNums.length!=0){
    				for(var i=0;i<$scope.serialNums.length;i++){
    					if (ids == '') {
    						ids = $scope.serialNums[i].materiel.serialNum;
    					} else{
    						ids = ids + ',' + $scope.serialNums[i].materiel.serialNum;
    					}
    				}
    			}else{
    				table.$('input[type="checkbox"]:checked').each(
    						function() {
    							if ($.contains(document, this)) {
    									// 将选中数据id放入ids中
    									if (ids == '') {
    										ids = this.id;
    									} else
    										ids = ids + ','
    												+ this.id;
    									$scope.ziZhuGetCheckedIds(this.id,this.name);
    							}
    			});
    			}	
        		handle.blockUI();
        		var promise = materielService.chooseBasicMateriels(ids,$scope.saleOrder.buyComId,'buyComId');
        		promise.then(function(data){
        			toastr.success("添加成功！");
        			handle.unblockUI();
        			if($scope.orderMateriel.length==0){
        				for(var i = 0;i < data.data.length;i++){// data.data为选择的标准物料
        					$scope.tempMateriel = {};
        					$scope.tempMateriel.materiel = (data.data)[i];
        					$scope.tempMateriel.orderSerial = $scope.saleOrder.serialNum;
        					$scope.tempMateriel.materielSerial = (data.data)[i].serialNum;
        					$scope.orderMateriel.push($scope.tempMateriel);
        					$scope["orderMaterielInput"+i] = false;
        					$scope["orderMaterielShow"+i] = false;
        				}
        			}else{
        				for(var i = 0;i < data.data.length;i++){// data.data为选择的标准物料
        					$scope.tempMateriel = {};
        					$scope.tempMateriel.materiel = (data.data)[i];
        					$scope.tempMateriel.orderSerial = $scope.saleOrder.serialNum;
        					$scope.tempMateriel.materielSerial = (data.data)[i].serialNum;
        					$scope.orderMateriel.push($scope.tempMateriel);
	        				$scope["orderMaterielInput"+(length+i)] = false;
							$scope["orderMaterielShow"+(length+i)] = false;
							/*$scope["orderMaterielInput" + ($scope.orderMateriel.length-1)] = true;
							$scope["orderMaterielShow" + ($scope.orderMateriel.length-1)] = true;*/
		        		}
        			}
        			initBuyComAddress($scope.saleOrder.buyComId);
        			$scope.copyMateriels = angular.copy($scope.orderMateriel);
        			$("#basicMaterielInfo").modal("hide");
        		},function(data){
        			// 调用承诺接口reject();
        		});
    	}
     	 //关闭物料列表时，清除选中状态START--------------
    	 $('#basicMaterielInfo').on('hide.bs.modal', function (e) { 
    		 clearChecked();
    		 $('input[type="checkbox"][class="group-checkable"]').attr("checked",false);
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
    	 
	       $scope.saveAllOrderMateriel  = function() {//保存所有物料
 	   	    	if($scope.saleOrder.serialNum==null||$scope.saleOrder.serialNum=='') {// 订单信息为空的处理
 	   	    		toastr.error('请先保存订单信息！');return
 	    		}
 	   	    	for(var i=0;i<$scope.orderMateriel.length;i++){
 	   	    		if(isNull($scope.orderMateriel[i].orderRateUnit)||isNull($scope.orderMateriel[i].orderUnitPrice)){
 	   	    		toastr.warning('含税价格或不含税价格必填！');return
 	   	    		}
 	   	    		}
 	   	    	if($('#form_sample_5').valid()){
 	   	    	orderService.saveAllOrderMateriel($scope.orderMateriel).then(
 	   	       		     function(data){
 	   	       		    	toastr.success('数据保存成功！');
 	   	       		    	$scope.cancelAllOrderMateriel();
	   	       		    		//保存结算条款
		  	   	       		if(isNull($scope.clauseSettlement)){// 结算条款为空的处理
			  	   	    		return
			  	   			}
		  	   	       		//更新订单金额数据
  	   		        		$scope.updateOrderAmount();	
			  	   	    	if($('#form_clauseSettlement').valid()){
				  	   	    	$scope.clauseSettlement.contractSerial = $scope.contract.id;
				  	    		$scope.clauseSettlementDetail = $scope.clauseSettlement.CSD;
				  		        $scope.clauseSettlement.materielAmount = $scope.totalAmount();
				  	  	        $scope.clauseSettlement.rateAmount = $scope.totalRateAmount();
				  	  	        $scope.clauseSettlement.rateAndAmount = $scope.totalRateAndAmount();
				  	  	        $scope.clauseSettlement.orderAmount = $scope.totalOrderAmount().toFixed(2);
			  	   	    		delete $scope.clauseSettlement.CSD;
			  	   	    		orderService.saveClauseSettlement($scope.clauseSettlement).then(//保存结算条款
			  	   	       		     function(data){
			  	   	       		    	$scope.clauseSettlement = data.data;
			  	   	       		    	if(!isNull(data.data)){
			  	   	       		    		if(!isNull($scope.clauseSettlementDetail)){
			  	   	       		    			for(var i=0;i<$scope.clauseSettlementDetail.length;i++){
			  	   	          		    			$scope.clauseSettlementDetail[i].clauseSettlementSerial = data.data.serialNum;
			  	   	          		    		$scope.clauseSettlementDetail[i].deliveryAmount=$scope.clauseSettlementDetail[i].deliveryRate*$scope.totalOrderAmount()/100;
			  	   	          		    		}
			  	   	          		    		orderService.saveClauseSettlementDetail($scope.clauseSettlementDetail).then(//保存结算条款明细
			  	   	          		        		     function(data){
			  	   	          		        		    	$scope.cancelClauseSettlement();
			  	   	          		        		    	$scope.clauseSettlement.CSD = data.data;
			  	   	          		        		     },
			  	   	          		        		     function(error){
			  	   	          		        		    	toastr.error('数据保存出错！');
			  	   	          		        		         $scope.error = error;
			  	   	          		        		     }
			  	   	          		        		 );
			  	   	       		    		}else{
			  	   	          		    		$scope.cancelClauseSettlement()
			  	   	       		    		}
			  	   	       		    	
			  	   	       		    	}else{
			  	   	      		    		$scope.clauseSettlement = {}
			  	   	      		    		$scope.cancelClauseSettlement()
			  	   	      		    	}
			  	   	       		     },
			  	   	       		     function(error){
			  	   	       		    	toastr.error('数据保存出错！');
			  	   	       		         $scope.error = error;
			  	   	       		     }
			  	   	       		 );
			  	   	    	}
 	   	       		     },
 	   	       		     function(error){
 	   	       		    	toastr.error('数据保存出错！');
 	   	       		         $scope.error = error;
 	   	       		     }
 	   	       		 );
 	   	    	}
 	   	    	
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
						
				if($scope.saleOrder.serialNum==null||$scope.saleOrder.serialNum=='') {// 订单信息为空的处理
 	   	    		toastr.error('请先保存订单信息！');return
 	    		}
				if(isNull(orderMateriel.orderRateUnit)||isNull(orderMateriel.orderUnitPrice)){
 	   	    		toastr.warning('含税价格或不含税价格必填！');return
 	   	    		}
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
						/*console.log(data.data);*/
						$scope["orderMaterielInput"+index] = true;
						$scope["orderMaterielShow"+index] = true;
						$scope["orderMaterielEdit"+index] = false;
						if($scope.clauseSettlement.CSD!=undefined){//有交付条款
							for(var a=0; a<$scope.clauseSettlement.CSD.length;a++ ){
								$scope.clauseSettlement.CSD[a].deliveryAmount=$scope.clauseSettlement.CSD[a].deliveryRate*$scope.totalOrderAmount()/100;
								
							}
						}
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
				$scope["orderMaterielEdit"+index] = false;
	        	for(var i=0;i<$scope.copyMateriels.length;i++){
	        		if(!isNull(materiel.serialNum)&&materiel.serialNum == $scope.copyMateriels[i].serialNum && !isNull(materiel.supplyMaterielSerial)){ // 如果是以保存的物料，回滚
	        			$scope.orderMateriel[$scope.orderMateriel.indexOf(materiel)] = $scope.copyMateriels[i];
						break;
	        		}
	        		
	        		/*if(i==$scope.copyMateriels.length-1){ // 如果是已选择但未保存的物料，清空
	        			$scope.orderMateriel[$scope.orderMateriel.indexOf(materiel)].deliveryDate = "";
	        			$scope.orderMateriel[$scope.orderMateriel.indexOf(materiel)].deliveryAddress = "";
	        			$scope.orderMateriel[$scope.orderMateriel.indexOf(materiel)].lastDeliveryDate = "";
	        			$scope.orderMateriel[$scope.orderMateriel.indexOf(materiel)].amount = "";
	        			$scope.orderMateriel[$scope.orderMateriel.indexOf(materiel)].orderUnitPrice = "";
	        		}*/
	        	}
	        };  
	        /**
			 * 撤销所有物料编辑
			 */
	        $scope.cancelAllOrderMateriel=function () {
	        	$scope.orderMaterielInput = true;
	        	$scope.orderMaterielShow = true;
	        	for(var i=0;i<$scope.orderMateriel.length;i++){
	        		$scope["orderMaterielInput"+i] = true;
					$scope["orderMaterielShow"+i] = true;
					$scope["orderMaterielEdit"+i] = false;
					
	        	}
	        }; 
	        
	        /**
			 * 打开所有物料编辑
			 */
	        $scope.editAllOrderMateriel=function () {
	        	$scope.orderMaterielInput = false;
	        	$scope.orderMaterielShow = false;
	        	for(var i=0;i<$scope.orderMateriel.length;i++){
	        		$scope["orderMaterielInput"+i] = false;
					$scope["orderMaterielShow"+i] = false;
					if(!isNull($scope.stateParamserialNum)){
        				$scope["orderMaterielEdit"+i] = true;
        			}
					
	        	}
	        }; 
	        
	        //选择第一个，设置后面的数据
			$scope.setAllDeliveryAddress = function(orderMateriel){
				 for(var i=1;i<$scope.orderMateriel.length;i++){
					 if($scope["orderMaterielInput"+i] != true/*&&isNull($scope.orderMateriel[i].deliveryAddress)*/){
						 $scope.orderMateriel[i].deliveryAddress = orderMateriel.deliveryAddress;
					 }
				 }
			}
			
			$scope.setAllDeliveryDate = function(orderMateriel,index){
				if(!isNull($scope.orderMateriel[index].lastDeliveryDate)&&$scope.orderMateriel[index].deliveryDate>$scope.orderMateriel[index].lastDeliveryDate){
		    		toastr.warning('交付日期不能大于最晚交付日期  ！');
		    		$scope.orderMateriel[index].deliveryDate=null;
		    		return;
		    	}
				if(index==0&&$scope.changeFlag){
					for(var i=1;i<$scope.orderMateriel.length;i++){
						 if($scope["orderMaterielInput"+i] != true/*&&isNull($scope.orderMateriel[i].deliveryAddress)*/){
							 $scope.orderMateriel[i].deliveryDate = orderMateriel.deliveryDate;
						 }
					 }
				}
			}
			
			$scope.setAllLastDeliveryDate = function(orderMateriel,index){
				if(!isNull($scope.orderMateriel[index].deliveryDate)&&$scope.orderMateriel[index].deliveryDate>$scope.orderMateriel[index].lastDeliveryDate){
		    		toastr.warning('最晚交付日期不能小于交付日期  ！');
		    		$scope.orderMateriel[index].lastDeliveryDate=null;
		    		return;
		    	}
				if(index==0&&$scope.changeFlag){ 
					for(var i=1;i<$scope.orderMateriel.length;i++){
						 if($scope["orderMaterielInput"+i] != true/*&&isNull($scope.orderMateriel[i].deliveryAddress)*/){
							 $scope.orderMateriel[i].lastDeliveryDate = orderMateriel.lastDeliveryDate;
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
			 * 编辑销售订单物料
			 */
	        $scope.editOrderMateriel=function (materiel) {
	        	// .show_materiels = false;
	        	for(var i=0;i<$scope.orderMateriel.length;i++){
	        		if(materiel.serialNum == $scope.orderMateriel[i].serialNum){
	        			$scope["orderMaterielInput"+i] = false;
	        			$scope["orderMaterielShow"+i] = false;
	        			if(!isNull($scope.stateParamserialNum)){
	        				$scope["orderMaterielEdit"+i] = true;
	        			}
	        			
	        		}
	        	}
	        	
	        };  
	        
	        /**
			 * 删除
			 */
	        $scope.deleteOrderMateriel=function (materiel) {
	        	handle.confirm("确定删除吗？",function(){
	        		if($scope.orderMateriel.length > 0){
	        			for(var i=0;i<$scope.orderMateriel.length;i++){
	        				for(var j=i;j+1<$scope.orderMateriel.length;j++){
        						$scope["orderMaterielInput"+j] = $scope["orderMaterielInput"+(j+1)];
	    	        			$scope["orderMaterielShow"+j] = $scope["orderMaterielShow"+(j+1)];
        					}
	        				if(materiel == $scope.orderMateriel[i]){
	        					$scope.orderMateriel.splice(i,1);
	        				}
	        			}
	        		}
	        		if(!isNull(materiel.serialNum)){
	        			handle.blockUI();
	        			var promise = orderService.deleteOrderMateriel(materiel.serialNum);
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
	        
	        
	        /** *************需求物料操作 start*************** */
	        var getDemandPlanMateriels = function(ids){
		        	handle.blockUI();
	        		var promise = materielService.chooseDemandPlanMateriels(ids.join());
	        		promise.then(function(data){
	        			toastr.success("添加成功！");
	        			handle.unblockUI();
	        			for(var i = 0;i < data.data.length;i++){// data.data为选择的供应物料
	        					$scope.tempMateriel = {};
	        					$scope.tempMateriel.materiel = (data.data)[i].supplyMateriel.materiel;
	        					$scope.tempMateriel.orderSerial = $scope.saleOrder.serialNum;
	        					$scope.tempMateriel.demandPlanMaterielSerial = (data.data)[i].serialNum;
	        					$scope.tempMateriel.materielSerial = (data.data)[i].supplyMateriel.materiel.serialNum;
	        					$scope.tempMateriel.supplyMaterielSerial = (data.data)[i].supplyMateriel.serialNum;
	        					$scope.tempMateriel.supplyMateriel = (data.data)[i].supplyMateriel;
	        					$scope.tempMateriel.amount = (data.data)[i].amount;
	        					$scope.tempMateriel.deliveryDate = (data.data)[i].deliveryDate;
	        					$scope.orderMateriel.push($scope.tempMateriel);
	        					$scope["orderMaterielInput"+i] = false;
	        					$scope["orderMaterielShow"+i] = false;
	        			}
	        			$scope.copyMateriels = angular.copy($scope.orderMateriel);
		        });
	        }
	        /** *************需求物料操作 end*************** */
    	 
    	 /** ***************合同信息start******************** */
	        //日期选择限制
	        var dateSelectSetting = function() { //签订日期变化
	        	startDateSetting();
	        	endDateSetting();
	        	signDateSetting();
		    }  
	        
	        var signDateSetting = function() { //签订日期变化
		        $("#signDate").datepicker().on('changeDate', function(ev){
		        	$("#startDate").datepicker('setStartDate',timeStamp2ShortString(ev.date));
		        	$("#endDate").datepicker('setStartDate',timeStamp2ShortString(ev.date));
		        });  
		    }  
	        
	        var startDateSetting = function() { //开始日期变化
		        $("#startDate").datepicker().on('changeDate', function(ev){
		        	$("#signDate").datepicker('setEndDate',timeStamp2ShortString(ev.date));
		        	$("#endDate").datepicker('setStartDate',timeStamp2ShortString(ev.date));
		        });  
		    }  
			 var endDateSetting = function() {   //结束日期变化
		        $("#endDate").datepicker().on('changeDate', function(ev){
		        	$("#signDate").datepicker('setEndDate',timeStamp2ShortString(ev.date));
		        	$("#startDate").datepicker('setEndDate',timeStamp2ShortString(ev.date));
		        });  
			}  
		 
			jQuery.validator.addMethod("noFileFlag", function(value, element) {  
					if($("#noFileFlag").length>0){
						return false;    
					}else{
						return true;  
					}
				}, "文件不能为空");  
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
		            	electronicContract:{noFileFlag:"合同附件不能为空！"}
		            },
	            	rules: {contractNum:{required:true,
			                	rangelength:[3,12]
				                },
				                startDate:{required:true
				                },
				                endDate:{required:true
				                },
				                electronicContract:{noFileFlag:true
				                }
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
    	 
//		        $scope.saveContract  = function() {// 保存合同信息
//		   	    	if($scope.saleOrder.serialNum==null||$scope.saleOrder.serialNum=='') {// 订单信息为空的处理
//		   	    		toastr.error('请先保存订单信息！');return
//		   			}
//		   	    	if($('#form_contract').valid()){
//		   	    		$scope.contract.orderSerial = $scope.saleOrder.serialNum;
//		   	    		$scope.contract.comId = $scope.saleOrder.buyComId;
//		   	    		orderService.saveContract($scope.contract).then(
//		   	       		     function(data){
//		   	       		    	toastr.success('数据保存成功！');
//		   	       		    	$scope.contract = data.data;
//		   	       		    	if(!isNull(data.data)){
//		          		    		var myJsDate=$filter('date')(data.data.startDate,'yyyy-MM-dd');
//		        					$scope.contract.startDate=myJsDate;
//		        					
//		        					var myJsDate1=$filter('date')(data.data.endDate,'yyyy-MM-dd');
//		        					$scope.contract.endDate=myJsDate1;
//		        					
//		        					var myJsDate2=$filter('date')(data.data.signDate,'yyyy-MM-dd');
//		        					$scope.contract.signDate=myJsDate2;
//		          		    	}
//		   	       		    	$scope.cancelContract();
//		   	       		     },
//		   	       		     function(error){
//		   	       		    	toastr.error('数据保存出错！');
//		   	       		         $scope.error = error;
//		   	       		     }
//		   	       		 );
//		   	    	}
//		   	    	
//		   	    }; 	
		   	    
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
			    	   if(index=='electronicContract'||index=='signContract'){//合同附件
		  		  			$scope.contract[index] = "";
		  		  		}else{//订单附件
		  		  			$scope.file[index].file = "";
		  		  		}
			       }
/** ***************合同信息end******************** */
/** ***************结算条款start******************** */
 //获取货币符号
   $scope.getCurrencySymbol = function(){
   	if(isNull($scope.saleOrder)||isNull($scope.saleOrder.currency)){
   		return '';
   	}else{
   		if($scope.saleOrder.currency=='人民币'){
   			return '￥';
   		}else if($scope.saleOrder.currency=='美元'){
   			return '$';
   		}else if($scope.saleOrder.currency=='欧元'){
   			return '€';
   		}else if($scope.saleOrder.currency=='英镑'){
   			return '￡';
   		}else{
   			return '';
   		}
   	} 
   }
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
    		toastr.error('请先保存订单信息！');return
		}
    	if(!$scope.saleOrderInput){
    		toastr.error('订单处于编辑状态，请处理！');return
    	}
    	
    	if($scope.orderMateriel.length>0){
			for(var i = 0;i < $scope.orderMateriel.length;i++){// data.data为选择的供应物料
				if(!$scope["orderMaterielInput"+i]){
					toastr.error('有订单物料处于编辑状态，请处理！');return
				}
			}
		}
    	
    	if(isNull($scope.clauseSettlement)){// 结算条款为空的处理
    		toastr.error('请填写结算条款后保存！');return
		}
    	for(var i=0; i<$scope.clauseSettlement.CSD.length;i++){
    		if($scope.clauseSettlement.CSD[i].deliveryRate<0){
    			toastr.error('支付比率不小于0！');return
    		}
    	}
    	if($('#form_clauseSettlement').valid()){
    		$scope.clauseSettlement.contractSerial = $scope.contract.id;
    		$scope.clauseSettlementDetail = $scope.clauseSettlement.CSD;
	        $scope.clauseSettlement.materielAmount = $scope.totalAmount();
  	        $scope.clauseSettlement.rateAmount = $scope.totalRateAmount();
  	        $scope.clauseSettlement.rateAndAmount = $scope.totalRateAndAmount();
  	        $scope.clauseSettlement.orderAmount = $scope.totalOrderAmount().toFixed(2);
  	        
    		delete $scope.clauseSettlement.CSD;
    		orderService.saveClauseSettlement($scope.clauseSettlement).then(//保存结算条款
       		     function(data){
       		    	$scope.clauseSettlement = data.data;
       		    	if(!isNull(data.data)){
       		    		if(!isNull($scope.clauseSettlementDetail)){
       		    			for(var i=0;i<$scope.clauseSettlementDetail.length;i++){
          		    			$scope.clauseSettlementDetail[i].clauseSettlementSerial = data.data.serialNum;
          		    			$scope["showSXf"+i]=false;
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
       		    			toastr.success('数据保存成功！');
          		    		$scope.cancelClauseSettlement()
       		    		}
       		    	//更新订单金额数据
	        		$scope.updateOrderAmount();	
      		    		
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
		if($scope.clauseSettlement.CSD.length==0){
			$scope.addCSD();
		}
    };
		   	   
    
    var _index = 0;
    /**
     * ClauseFramework新增一行
     */
   $scope.addCSD = function(){
   	if($scope.clauseSettlement.CSD){}else{$scope.clauseSettlement.CSD =[{}]}
   	$scope.clauseSettlement.CSD[_index] = {};
	    $scope.clauseSettlement.CSD[_index].deliveryRate = 100 - $scope._totalRate();
	    $scope.clauseSettlement.CSD[_index].deliveryAmount = ($scope.totalOrderAmount()*$scope.clauseSettlement.CSD[_index].deliveryRate/100).toFixed(2);
	    $scope.clauseSettlement.CSD[_index].billingAmount =  Number($scope._totaldeliveryAmount()) - Number($scope._totalbillingAmount());
	    $scope.clauseSettlement.CSD[_index].unbilledAmount = 0;
	   _index++;
	  
   };
   $scope.addDefaultLine = function(index){
	   var flag=false;
	   if($scope.clauseSettlement.CSD[index].paymentType=='预付款'){
		   for(var i in $scope.clauseSettlement.CSD){
			   if(($scope.clauseSettlement.CSD[i].paymentType==$scope.clauseSettlement.CSD[index].paymentType)&&(i!=index)){
				   toastr.warning('支付类型重复,请重新选择！');
				   return;
			   }
			   if($scope.clauseSettlement.CSD[i].paymentType=='尾款'){
				   flag=true;
				   return;
			   }
		   }
		   $scope.clauseSettlement.CSD[_index] = {};
		    $scope.clauseSettlement.CSD[_index].deliveryRate = 100 - $scope._totalRate();
		    $scope.clauseSettlement.CSD[_index].deliveryAmount = ($scope.totalOrderAmount()*$scope.clauseSettlement.CSD[_index].deliveryRate/100).toFixed(2);
		    $scope.clauseSettlement.CSD[_index].billingAmount =  Number($scope._totaldeliveryAmount()) - Number($scope._totalbillingAmount());
		    $scope.clauseSettlement.CSD[_index].unbilledAmount = 0;
		    $scope.clauseSettlement.CSD[_index].paymentType='尾款';
		    _index++;
	   }
	   
   }
   
   $scope._totalRate  = function() {//计算总的支付比例
      	var totalRate = 0;
   	for(var i=0;i<$scope.clauseSettlement.CSD.length;i++){
   		if($scope.clauseSettlement.CSD[i].deliveryRate){
   			totalRate = totalRate + Number($scope.clauseSettlement.CSD[i].deliveryRate);
   		}
      	}
   	return totalRate;
  };
  
  
 $scope._totalUnbilledAmount  = function() {//计算所有未开票金额
     	var totalUnbilledAmount = 0;
  	for(var i=0;i<$scope.clauseSettlement.CSD.length;i++){
  		if($scope.clauseSettlement.CSD[i].unbilledAmount){
  			totalUnbilledAmount = totalUnbilledAmount + Number($scope.clauseSettlement.CSD[i].unbilledAmount);
  		}
     	}
  	return totalUnbilledAmount;
 };
 
 $scope._totalbillingAmount  = function() {//计算所有开票金额
   	var totalbillingAmount = 0;
	for(var i=0;i<$scope.clauseSettlement.CSD.length;i++){
		if($scope.clauseSettlement.CSD[i].billingAmount){
			totalbillingAmount = totalbillingAmount + Number($scope.clauseSettlement.CSD[i].billingAmount);
		}
   	}
	return totalbillingAmount;
};
$scope._totaldeliveryAmount  = function() {//计算所有支付金额
	var totaldeliveryAmount = 0;
	for(var i=0;i<$scope.clauseSettlement.CSD.length;i++){
		if($scope.clauseSettlement.CSD[i].deliveryAmount){
			totaldeliveryAmount = totaldeliveryAmount + Number($scope.clauseSettlement.CSD[i].deliveryAmount);
		}
	}
	return totaldeliveryAmount;
};
  
  $scope._arithmeticRate  = function(scope) {//计算支付比例
     	
	   scope._CSD.deliveryRate =  ((scope._CSD.deliveryAmount/$scope.totalOrderAmount())*100).toFixed(2);
	   
	   if($scope._totalRate()>100){
     		scope._CSD.deliveryRate = scope._CSD.deliveryRate - $scope._totalRate() + 100
     	}
  	
	   $scope._arithmeticDeliveryAmount(scope);
 };
 
   $scope._arithmeticDeliveryAmount  = function(scope,index) {//计算支付金额
      	if($scope._totalRate()>100){
      		scope._CSD.deliveryRate = scope._CSD.deliveryRate - $scope._totalRate() + 100
      	}else if($scope.clauseSettlement.CSD.length==2) {
       		if(index==0){
       			$scope.clauseSettlement.CSD[1].deliveryRate=100-scope._CSD.deliveryRate;
       			$scope.clauseSettlement.CSD[1].deliveryAmount=((100-scope._CSD.deliveryRate)*$scope.totalOrderAmount()/100).toFixed(2);
       		}else if(index==1){
       			$scope.clauseSettlement.CSD[0].deliveryRate=100-scope._CSD.deliveryRate;
       			$scope.clauseSettlement.CSD[0].deliveryAmount=((100-scope._CSD.deliveryRate)*$scope.totalOrderAmount()/100).toFixed(2);
       		}
       	}
   	
   	if(scope._CSD.deliveryRate){
      		scope._CSD.deliveryAmount =  ($scope.totalOrderAmount()*scope._CSD.deliveryRate/100).toFixed(2);
      	}
   	scope._CSD.billingAmount = (Number($scope._totaldeliveryAmount()) - Number($scope._totalbillingAmount()) + Number(scope._CSD.billingAmount)).toFixed(2);
  		scope._CSD.unbilledAmount = 0 ;
  };
  
  $scope._arithmeticUnbilledAmount  = function(scope) {//计算未开金额
      	if(scope._CSD.billingAmount&&scope._CSD.deliveryAmount){
      		scope._CSD.unbilledAmount =  (Number($scope._totaldeliveryAmount()) - Number($scope._totalbillingAmount())).toFixed(2);
      	}
      	if(scope._CSD.unbilledAmount<0){
      		scope._CSD.billingAmount = (Number($scope._totaldeliveryAmount()) - Number($scope._totalbillingAmount()) + Number(scope._CSD.billingAmount)).toFixed(2);
      		scope._CSD.unbilledAmount = 0 ;
      	}
  };
  
  $scope._arithmeticBilledAmount  = function(scope) {//计算已开金额
     	if(scope._CSD.unbilledAmount&&scope._CSD.deliveryAmount){
     		scope._CSD.billingAmount =  (Number(scope._CSD.deliveryAmount) - Number(scope._CSD.unbilledAmount)).toFixed(2);
     	}
     	if(scope._CSD.billingAmount<0){
     		scope._CSD.unbilledAmount = scope._CSD.deliveryAmount;
     		scope._CSD.billingAmount = 0 ;
     	}
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
   	    		toastr.error('请先保存订单信息！');return
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
   	         messages: {
   	          	deliveryMode:{required:"提货方式不能为空！"}
   	          },
   	      	rules: {
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
   	 
   	        $scope.saveClauseDelivery  = function() {// 保存交付条款
   	   	    	if($scope.contract.id==null||$scope.contract.id=='') {// 合同信息为空的处理
   	   	    		toastr.error('请先保存订单信息！');return
   	   			}
   	   	    	if(isNull($scope.clauseDelivery)){// 交付条款为空的处理
   	   	    		toastr.error('请填写交付条款后保存！');return
   	   			}
   	   	    	/*if(isNull($scope.clauseDelivery.warehouseAddress)&&$scope.showSXf =='1'){// 地址为空
	   	    		toastr.error('请填写交付条款后保存！');return
	   			}
   	   	  if($scope.showSXf =='1'){
				if(isNull($("select[name='warehouseAddress1']").val())&&isNull($scope.clauseDelivery.deliveryMode)){
					toastr.error('地址不能为空！');
	    			return;
				}
				if(isNull($("select[name='warehouseAddress1']").val())&&($scope.clauseDelivery.deliveryMode=='自提')){
					toastr.error('提货地址不能为空！');
	    			return;
				}
				if(isNull($("select[name='warehouseAddress1']").val())&&($scope.clauseDelivery.deliveryMode=='配送')){
					toastr.error('收货地址不能为空！');
	    			return;
				}
			}else{
				if(isNull($("input[name='warehouseAddress']").val())&&isNull($scope.clauseDelivery.deliveryMode)){
					toastr.error('地址不能为空！');
	    			return;
				}
				if(isNull($("input[name='warehouseAddress']").val())&&($scope.clauseDelivery.deliveryMode=='自提')){
					toastr.error('提货地址不能为空！');
	    			return;
				}
				if(isNull($("input[name='warehouseAddress']").val())&&($scope.clauseDelivery.deliveryMode=='配送')){
					toastr.error('收货地址不能为空！');
	    			return;
				}
			}
   	   if($scope.showSXf =='1'){
			$scope.clauseDelivery.warehouseAddress=$("select[name='warehouseAddress1']").val();
		}else{
			$scope.clauseDelivery.warehouseAddress=$("input[name='warehouseAddress']").val();
		}*/
   	   	    	if($('#form_clauseDelivery').valid()){
   	   	    		$scope.clauseDelivery.contractSerial = $scope.contract.id;
   	   	    		orderService.saveClauseDelivery($scope.clauseDelivery).then(
   	   	       		     function(data){
   	   	       		    	toastr.success('数据保存成功！');
   	   	       		    	$scope.clauseDelivery = data.data;
   	   	       		 if($scope.showSXf =='1'){
   	   	       			 	$scope.showSXf ='0';
   	   	       		    	}
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
		   	   	      messages: {
		 			     
		   	   	    	  checkType:{required:"检验类型不能为空！"},
		   	   	    	  checkParty:{required:"检验方不能为空！"}
			            },
		          	rules: {
		          			checkType:{required:true},
		          			checkParty:{required:true}
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
   	   	 
   	   	        $scope.saveClauseCheckAccept  = function() {// 保存验收条款
   	   	   	    	if($scope.contract.id==null||$scope.contract.id=='') {// 合同信息为空的处理
   	   	   	    		toastr.error('请先保存订单信息！');return
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
   	   	    		toastr.error('请先保存订单信息！');return
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
   	   	    	if($scope.saleOrder.serialNum==null||$scope.saleOrder.serialNum=='') {// 订单信息为空的处理
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
		   	   	  if($scope.saleOrder.serialNum==null||$scope.saleOrder.serialNum=='') {// 订单信息为空的处理
		 	    		toastr.error('请先保存订单信息！');return
		 			}else{
   	   		    	   if($scope.file){}else{$scope.file =[{}]}
   	   		    	   $scope.file[_fileIndex] = {};
   	   		    	   $scope.file[_fileIndex].orderSerial = $scope.saleOrder.serialNum;
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
	   	    		toastr.error('请先保存订单信息！');return
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
   	    		toastr.error('请先保存订单信息！');return
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
	        	$state.go("saleOrder");
	        };
	        $scope.submitPage  = function(serialNum) {// 取消编辑
	        	$scope.submitOrder = {}
	        	if(!isNull(serialNum)){//列表操作栏按钮接收
	        		$scope.submitOrder.serialNum = serialNum;
		        	$scope.submitOrder.status = 0;
	        	}else{//详情页面按钮接收
	        		$scope.submitOrder.serialNum = $scope.saleOrder.serialNum;
		        	$scope.submitOrder.remark = $scope.saleOrder.remark;
		        	$scope.submitOrder.status = 0;
		        	$scope.saleOrder.status = 0;
	        	}
	        	orderService.acceptSubmit($scope.submitOrder).then(
	          		     function(data){
	          		    	if(!isNull(serialNum)){//列表操作栏按钮接收
	          		    		toastr.info('订单接收成功！');
	          		    		$state.go('saleOrder',{},{reload:true});
	        	        	}else{//详情页面按钮提交
	        	        		toastr.info('订单接收成功！');
	        	        	}
	          		    	
	          		     },
	          		     function(error){
	          		         $scope.error = error;
	          		         toastr.error('数据保存出错！');
	          		     }
	          		 );
	        };
	        
	        $scope.saleGenerateBuy  = function(serialNum) {// 分解订单
	        	if(serialNum){//列表分解采购
	        		orderService.saleGenerateBuy(serialNum).then(
		          		     function(data){
		          		    	toastr.info('订单分解成功！');
		          		    	table.ajax.reload();
		          		     },
		          		     function(error){
		          		         $scope.error = error;
		          		         toastr.error('数据保存出错！');
		          		     }
		          		 );
	        	}else{//详情分解采购
//	        		if(!isNull($scope.saleOrder.orderSerial)){
//		        		toastr.info('已有对应采购单，不能再次分解！');
//		        		return;
//		        	}
		        	orderService.saleGenerateBuy($scope.saleOrder.serialNum).then(
	          		     function(data){
	          		    	$scope.saleOrder = data
	          		    	toastr.info('订单分解成功！');
	          		     },
	          		     function(error){
	          		         $scope.error = error;
	          		         toastr.error('数据保存出错！');
	          		     }
	          		 );
	        	}
	        		        };
	        
	        $scope.saleGenerateProcurementPlan  = function() {// 分解订单
	        	if(!isNull($scope.saleOrder.orderSerial)){
	        		toastr.info('已有对应采购计划，不能再次分解！');
	        		return;
	        	}
	        	orderService.saleGenerateProcurementPlan($scope.saleOrder.serialNum).then(
	          		     function(data){
	          		    	$scope.saleOrder = data
	          		    	toastr.info('订单分解成功！');
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
		    	   window.location.href=$rootScope.basePath+"/rest/order/downloadImportTemp?type=sale";
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
		       
		       
		       $scope.exportSaleOrder = function(){
			    	 handle.blockUI("正在导出数据，请稍后"); 
			    	 //如果
			    	 if($scope.serialNums&&$scope.serialNums.length!=0){
			    		 var serialNums="";
			    		 for(var i=0;i<$scope.serialNums.length;i++){
			    			 if(i==$scope.serialNums.length-1){
			    				 serialNums=serialNums+$scope.serialNums[i]
			    			 }else{
			    				 serialNums=serialNums+$scope.serialNums[i]+",";
			    			 }
			    		 }
			    		 window.location.href=$rootScope.basePath+"/rest/order/exportOrder?type=sale"+"&&serialNums="+serialNums;
			    	 }else{//全部导出
			    		 window.location.href=$rootScope.basePath+"/rest/order/exportOrder?type=sale";
			    	 }
			    	 handle.unblockUI(); 
			   }
		       
		       $scope.exportSaleFramOrder = function(){
			    	 handle.blockUI("正在导出数据，请稍后"); 
			    	 window.location.href=$rootScope.basePath+"/rest/order/exportOrder?type=sale&&fram=1";
			    	 handle.unblockUI(); 
			   }
		       //********导入导出end****************//
		       //********订单物料合计，结算条款start****************//
		       $scope.totalCount  = function() {//订单物料数量
			       	if($scope.orderMateriel){
			       		return $scope.orderMateriel.length;
			       	}else{
			       		return 0;
			       	}
		       };
		       
		       $scope.totalSupply  = function() {//订单供应商数量
			       	if($scope.orderMateriel){
			       		$scope.supply = [];//供应商数组
			       		for(var i=0;i<$scope.orderMateriel.length;i++){
			       			var isExit = 0;
			       			for(var j=0;j<$scope.supply.length;j++){
				       			if($scope.supply[j]==$scope.orderMateriel[i].supplyMateriel.supply.comId){
				       				isExit = 1;
				       				break;
				       			}
				       		}
			       			if(isExit==0){
			       				$scope.supply.push($scope.orderMateriel[i].supplyMateriel.supply.comId);
			       			}
			       		}
			       		return $scope.supply.length;
			       	}else{
			       		return 0;
			       	}
		       };
		       
		       $scope.totalMaterielCount  = function(scope) {//订单物料总数量
		    	   if($scope.orderMateriel){
		    		    var total = 0 ; 
			       		for(var i=0;i<$scope.orderMateriel.length;i++){
			       			if(!isNull($scope.orderMateriel[i].amount)){
			       				total = total + Number($scope.orderMateriel[i].amount);
			       			}
			       			
			       		}
			       		return total
			       	}else{
			       		return 0;
			       	}
		       };
		       $scope.totalOrderAmount  = function(scope) {//订单金额（价税合计（商品金额+税额）+ 其他金额）
		    	   if(isNull($scope.clauseSettlement)||isNull($scope.clauseSettlement.otherAmount)){
		    		   /*if(!isNull($scope.saleOrder)&&$scope.saleOrder.tradeType =='外贸'){
		    			   return Number($scope.totalAmount());
		    		   }else{
		    			   return Number($scope.totalAmount()) + Number($scope.totalRateAmount());
		    		   }*/
		    		   return Number($scope.totalAmount()) + Number($scope.totalRateAmount());
			       		
			       	}else{
			       	   /*if(!isNull($scope.saleOrder)&&$scope.saleOrder.tradeType =='外贸'){
		    			   return Number($scope.totalAmount()) + Number($scope.clauseSettlement.otherAmount)
		    		   }else{
		    			   return Number($scope.totalAmount()) + Number($scope.totalRateAmount()) + Number($scope.clauseSettlement.otherAmount);
		    		   }*/
			       		return Number($scope.totalAmount()) + Number($scope.totalRateAmount()) + Number($scope.clauseSettlement.otherAmount);
			       	}
		       };
		       $scope.arithmeticAllDeliveryAmount  = function() {//计算所有节点支付金额
			    	 //计算各节点金额
			    	   if(!isNull($scope.clauseSettlement)&&!isNull($scope.clauseSettlement.CSD)){
			    		   	for(var i=0;i<$scope.clauseSettlement.CSD.length;i++){
				    		   $scope.arithmeticDeliveryAmount($scope.clauseSettlement.CSD[i])
				       		}
			    	   }
			      };
			       $scope.arithmeticDeliveryAmount  = function(CSD) {//计算支付金额
			          	if($scope._totalRate()>100){
			          		CSD.deliveryRate = CSD.deliveryRate - $scope._totalRate() + 100
			          	}
			          	if(CSD.deliveryRate){
			          		CSD.deliveryAmount =  ($scope.totalOrderAmount()*CSD.deliveryRate/100).toFixed(2);
			          	}
	/*		       	scope._CSD.billingAmount = (Number($scope._totaldeliveryAmount()) - Number($scope._totalbillingAmount()) + Number(scope._CSD.billingAmount)).toFixed(2);
			      		scope._CSD.unbilledAmount = 0 ;*/
			      };
			       
		       $scope.totalAmount  = function(scope) {//商品金额
		    	   if($scope.orderMateriel){
		    		    var total = 0 ; 
			       		for(var i=0;i<$scope.orderMateriel.length;i++){
			       			total = total + Number($scope.arithmeticAmount($scope.orderMateriel[i]));
			       		}
			       		return total.toFixed(2)
			       	}else{
			       		return 0;
			       	}
		       };
		       $scope.totalRateAmount  = function(scope) {//订单税额
		    	   if($scope.orderMateriel){
		    		    var total = 0 ; 
			       		for(var i=0;i<$scope.orderMateriel.length;i++){
			       			total = total + Number($scope.arithmeticRateAmount($scope.orderMateriel[i]));
			       		}
			       		return total.toFixed(2)
			       	}else{
			       		return 0;
			       	}
		       };
		       
		       $scope.totalRateAndAmount  = function(scope) {//求和订单价税合计
		    	   if($scope.orderMateriel){
		    		    var total = 0 ; 
			       		for(var i=0;i<$scope.orderMateriel.length;i++){
			       			total = total + Number($scope.arithmeticRateAndAmount($scope.orderMateriel[i]));
			       		}
			       		return total.toFixed(2)
			       	}else{
			       		return 0;
			       	}
		       };
		       
		       $scope.arithmeticRateUnit  = function(scope) {//计算含税销售单价
			       	if(scope.orderUnitPrice&&$scope.saleOrder.rate){
			       		return (scope.orderUnitPrice*($scope.saleOrder.rate/100+1)).toFixed(4);
			       	}else{
			       		return 0;
			       	}
		       };
		       
		       $scope.arithmeticAmount  = function(scope) {//计算金额
			       	if(scope.orderUnitPrice&&scope.amount){
			       		return (scope.orderUnitPrice*scope.amount).toFixed(9);
			       	}else{
			       		return 0;
			       	}
		       };
		       $scope.arithmeticRateAmount  = function(scope) {//计算税额
			       	if($scope.saleOrder.rate){
			       		return ($scope.arithmeticAmount(scope)*$scope.saleOrder.rate/100).toFixed(9);
			       	}else{
			       		return 0;
			       	}
		       };
		       
		       $scope.arithmeticRateAndAmount  = function(scope) {//计算价税合计
			       	return (Number($scope.arithmeticAmount(scope))+Number($scope.arithmeticRateAmount(scope))).toFixed(9);
		       };
		       
		       $scope._arithmeticRateUnit  = function(_orderMateriel) {//计算含税销售单价
			       	if(_orderMateriel.orderUnitPrice&&$scope.saleOrder.rate){
			       		_orderMateriel.orderRateUnit  =  (_orderMateriel.orderUnitPrice*($scope.saleOrder.rate/100+1)).toFixed(4);
			       	}else{
			       		_orderMateriel.orderRateUnit  =   0;
			       	}
		       };
		       
		       $scope._arithmeticUnitPrice  = function(_orderMateriel) {//计算不含税销售单价
			       	if(_orderMateriel.orderRateUnit&&$scope.saleOrder.rate){
			       		_orderMateriel.orderUnitPrice  =  (_orderMateriel.orderRateUnit/($scope.saleOrder.rate/100+1)).toFixed(9);
			       	}else{
			       		_orderMateriel.orderUnitPrice  =   0;
			       	}
		       };
		       
		       $scope._arithmeticAmount  = function(scope) {//计算金额
			       	if(scope._orderMateriel.orderUnitPrice&&scope._orderMateriel.amount){
			       		return (scope._orderMateriel.orderUnitPrice*scope._orderMateriel.amount).toFixed(9);
			       	}else{
			       		return 0;
			       	}
		       };
		       $scope._arithmeticRateAmount  = function(scope) {//计算税额
			       	if($scope.saleOrder.rate){
			       		return ($scope._arithmeticAmount(scope)*$scope.saleOrder.rate/100).toFixed(9);
			       	}else{
			       		return 0;
			       	}
		       };
		       
		       $scope._arithmeticRateAndAmount  = function(scope) {//计算价税合计（商品金额+税额）
			       	return (Number($scope._arithmeticAmount(scope))+Number($scope._arithmeticRateAmount(scope))).toFixed(9);
		       };
		       
		       $scope.clearNoNumPoint = function(obj,attr){
			    	 //先把非数字的都替换掉，除了数字和.
			    	 obj[attr] = obj[attr].replace(/[^\d.]/g,"");
			    	 //必须保证第一个为数字而不是.
			    	 obj[attr] = obj[attr].replace(/^\./g,"");
			    	 //保证只有出现一个.而没有多个.
			    	 obj[attr] = obj[attr].replace(/\.{2,}/g,"");
			    	 //保证.只出现一次，而不能出现两次以上
			    	 obj[attr] = obj[attr].replace(".","$#$").replace(/\./g,"").replace("$#$",".");
			    	//保证小数点后只有9位
			    	 obj[attr] = obj[attr].replace(/([0-9]+\.[0-9]{9})[0-9]*/,"$1");
			    	 if((obj[attr]<0||obj[attr]>100)&&(attr!='orderRateUnit'&&attr!='orderUnitPrice'&&attr!='otherAmount')){
			    		 obj[attr]=0;
			    	 }
			    	/* if(Number(obj[attr])==0&&(attr=='orderRateUnit'||attr=='orderUnitPrice')){
			    		 obj[attr]=null;
			    	 }*/
		    	 }
		       
		       $scope.clearNoNum = function(obj,attr){
			    	 //把非数字的都替换掉
		    	   if(obj[attr].substring(0,1) =="0"){
			 			  obj[attr] = obj[attr].substring(1);
			 		  }
			    	 obj[attr] = obj[attr].replace(/[^\d]/g,"");
			    	 if(!isNaN(obj[attr])&&obj[attr]!=0){
			    		 $scope.getUnitPrice(obj);//根据数量获得指导单价
			    	 }
		    	 }
		       $scope.getUnitPrice=function(obj){
		    	   var params={};
		    	  // obj. materiel.unitPrice
		    	   params.materielSerial=obj.materielSerial;//物料流水
		    	   params.buyComId=$scope.saleOrder.buyComId;//采购商
		    	   params.number=obj.amount;//销售数量
		    	   var promise = orderService.getUnitPrice(params);
		   		promise.then(function(data){
		   			if(!isNull(data)){
		   				obj. materiel.unitPrice=data;
		   			}
		   		},function(data){
		   			//调用承诺接口reject();
		   		});
		    	   
		       }
		       $scope.format2Thousands = function(formatV){  
		    	   formatV = Number(formatV).toFixed(2);
                   var array=new Array();  
                   array=formatV.split(".");  
                   var re=/(-?\d+)(\d{3})/;  
                   while(re.test(array[0])){  
                       array[0]=array[0].replace(re,"$1,$2")  
                   }  
                   var returnV=array[0];  
                   for(var i=1;i<array.length;i++){  
                       returnV+="."+array[i];  
                   }  
                   return returnV;
               } 
		     //更新订单金额数据
		     $scope.updateOrderAmount = function(obj,attr){
		    	$scope.submitOrder = {}
   	        	$scope.submitOrder.serialNum = $scope.saleOrder.serialNum;
   	        	$scope.submitOrder.materielCount = $scope.totalMaterielCount();
   	        	$scope.submitOrder.materielAmount = $scope.totalAmount();
      	        $scope.submitOrder.rateAmount = $scope.totalRateAmount();
      	        $scope.submitOrder.rateAndAmount = $scope.totalRateAndAmount();
      	        if(!isNull($scope.clauseSettlement)&&!isNull($scope.clauseSettlement.otherAmount)){
    	        	$scope.submitOrder.otherAmount = $scope.clauseSettlement.otherAmount;
    	        }
      	        $scope.submitOrder.orderAmount = $scope.totalOrderAmount().toFixed(2);
	    	    orderService.save($scope.submitOrder).then(
          		     function(data){
          		    	
          		     },
          		     function(error){
          		         $scope.error = error;
          		         toastr.error('数据保存出错！');
          		     }
          		 );
		      }
 		    	
		     /*$scope._arithmeticDeliveryAmount  = function(scope) {//计算支付金额
			       	if(scope._CSD.deliveryRate){
			       		scope._CSD.deliveryAmount =  ($scope.totalOrderAmount()*scope._CSD.deliveryRate/100).toFixed(2);
			       	}
			       	$scope._arithmeticUnbilledAmount(scope);
		       };
		       
		       $scope._arithmeticUnbilledAmount  = function(scope) {//计算未开金额
			       	if(scope._CSD.billingAmount&&scope._CSD.deliveryAmount){
			       		scope._CSD.unbilledAmount =  (Number(scope._CSD.deliveryAmount) - Number(scope._CSD.billingAmount)).toFixed(2);
			       	}
		       };*/
		       
		       
		     //********订单物料合计，结算条款start****************//
		    
		    
		    /**
			 * 加载客户数据
			 */
			var initCustomers = function(){
				var promise = orderService.initCustomers();
		        	promise.then(function(data){
		        		$scope.customers = data.data;
		        		setTimeout(function () {
		        			$("#buyComId").selectpicker({
		                        showSubtext: true,
		                        size : 5
		                    });
		        			$('#buyComId').selectpicker('refresh');//刷新插件
		        			
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
			/*var promise = orderService.initWarehouse();
			promise.then(function(data){
				$scope.warehouses = data.data;
			},function(data){
				//调用承诺接口reject();
			});*/
				var promise = orderService.getCompanyAddress();
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
			var promise = orderService.initPtWarehouseAddress();
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
			/**
			 * 加载采购商联系地址数据
			 */
			var initBuyComAddress = function(comId){
			var promise = orderService.initBuyComAddress(comId);
			promise.then(function(data){
				$scope.buyComAddresses = data.data;
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
			//********审批流程start****************//
		       $scope.submitSaleApply  = function(serialNum,orderAmount) {// 进入申请审批页面
		    	   
		    	   if(serialNum==undefined){//从列表头上申请
		    		 	if(table.rows('.active').data().length != 1){
			    			showToastr('toast-top-center', 'warning', '请选择一条任务进行流程申请！')
			    		}else{
			    			 if(isNull(table.row('.active').data().orderAmount)||Number(table.row('.active').data().orderAmount)==0){
				    				showToastr('toast-top-center', 'warning', '该销售订单金额为0,不能申请！');
				    				 return;
				    			}
			    			var processBase = table.row('.active').data().processBase;
			    			var status = table.row('.active').data().status;
			    			 if(processBase != null){
			    				showToastr('toast-top-center', 'warning', '该销售订单已发起流程审批，不能再次申请！')
			    			}else if(status=='55'){
			    				showToastr('toast-top-center', 'warning', '该销售订单还未接收,请先接收!')
			    			}else $state.go('submitSaleApply',{serialNum:table.row('.active').data().serialNum});
			    		}  
		    	   }else{
		    		   if(serialNum=='view'){//详情申请
		    			   if(isNull($scope.saleOrder.orderAmount)||Number($scope.saleOrder.orderAmount)==0){
			    				showToastr('toast-top-center', 'warning', '该销售订单金额为0,不能申请！');
			    				 return;
			    			}
		    			   if($scope.saleOrder.materielCount==0||$scope.saleOrder.materielCount==null){
		    				   showToastr('toast-top-center', 'warning', '该销售订单没有物料，不能发起流程申请！');
				    		  
		    			   }else $state.go('submitSaleApply',{serialNum:$scope.saleOrder.serialNum});
		    		   }else{
			    			 if(Number(orderAmount)==0||orderAmount=='null'){
				    				showToastr('toast-top-center', 'warning', '该销售订单金额为0,不能申请！');
				    				 return;
				    			}
		    			   /*if(materielCount=='null'|| materielCount==0){
				    		   showToastr('toast-top-center', 'warning', '该销售订单没有物料，不能发起流程申请！');
				    		   return;
				    	   }*/
			    		   if(!isNull(processBase)){
			    			   showToastr('toast-top-center', 'warning', '该销售订单已发起流程审批，不能再次申请！')
			    		   }else  $state.go('submitSaleApply',{serialNum:serialNum});
		    		   }
		    		  
		    	   }
		        };
		        
		        
		        $scope.confirmSaleApply  = function() {// 进入提交申请
		        	$scope.submitOrder = {}
		        	$scope.submitOrder.serialNum = $scope.saleOrder.serialNum;
		        	$scope.submitOrder.remark = $scope.saleOrder.remark;
		        	$scope.submitOrder.orderNum = $scope.saleOrder.orderNum;
		        	$scope.submitOrder.tradeType = $scope.saleOrder.tradeType;
		        	$scope.submitOrder.orderType = $scope.saleOrder.orderType;
		        	//启动流程
		        	orderService.startSaleOrderProcess($scope.submitOrder).then(
		          		     function(data){
		          		    	if(data == "1"){
			        				toastr.success("提交申请成功！");
			        				$scope.cancelPage();
			        			}else{
			        				toastr.error("提交申请失败！");
					            	console.log(data);
			        			}
		          		     },
		          		     function(error){
		          		         $scope.error = error;
		          		         toastr.error('数据保存出错！');
		          		     }
		          		 );
		    		
		        };
		      //********合同内容操作start****************//  
		        $scope.getContentStatus = function(Str,index){
		    		if(!isNull(Str)&&index>=1){
		    			return Str.substring(index-1,index)
		    		}else{
		    			return ''
		    		}
		    	}
		        $scope.changeStr = function(allstr,index,changeStr){ //allstr:原始字符串，start,开始位置,end：结束位  置,str：要改变的字，changeStr:改变后的字
		        	 return allstr.substring(0,index-1)+changeStr+allstr.substring(index,allstr.length); 
		        }
		        
		        
		        /**
		    	 * 初始化选中状态
		    	 */
		    	$scope.initContractContent = function(){
		    		for(var i=1;i<=6;i++){
		    			if($scope.getContentStatus($scope.saleOrder.contractContent,i)==1){
		    				$("#tab_1_"+i+"Id").addClass("active");
		    				$scope["tab_1_"+i+"Hide"] = false
		    			}else{
		    				$("#tab_1_"+i+"Id").removeClass("active");
		    				$scope["tab_1_"+i+"Hide"] = true
		    				if($state.current.name=="viewSaleOrder"
	          		    		||$state.current.name=="submitSaleApply"
	          		    			||$state.current.name=="approvalSaleApply"){//查看不展示
		    					$scope["tab_1_"+i+"label"] = true
	          		    	}
		    			}
		    		}
		    	}
		    	
		    	$scope.changeContentStatus = function(index){
		    		if($scope.getContentStatus($scope.saleOrder.contractContent,index)==1){
		    			$scope.saleOrder.contractContent = $scope.changeStr($scope.saleOrder.contractContent,index,0);
		    			/*$("#tab_1_"+index+"Id").removeClass("active");*/
	    				$scope["tab_1_"+index+"Hide"] = true
		    		}else{
		    			$scope.saleOrder.contractContent = $scope.changeStr($scope.saleOrder.contractContent,index,1);
		    			/*$("#tab_1_"+index+"Id").addClass("active");*/
	    				$scope["tab_1_"+index+"Hide"] = false
		    		}
		    	}
		    	
		    	//********合同内容操作end ****************//   
		      //********审批流程列表****************//
		        function showDbTable(){
		        	var tableButtons = 
		        		[{
							text : "办理",
							className : "btn default",
							action: function(e, dt, node, config) { 
								
								var ids = '';
								table.$('input[type="checkbox"]').each(function() {
									if ($.contains(document, this)) {											
										if (this.checked) {
											// 将选中数据id放入ids中
											if (ids == '') {
												ids = this.value;
											} else
												ids = 'more';
										}
									}
								});
								
								if(ids==''){
									toastr.warning('请选择一个办理！');return;
								}else if(ids=='more'){
									toastr.warning('只能选择一个办理！');return;
								} else {
									if(table.row('.active').data().assign == ''){
										showToastr('toast-top-center', 'warning', '此任务您还没有签收，请【签收】任务后再处理任务！')
										return;
									}
									orderService
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
    													$state.go('approvalSaleApply',{serialNum:result.orderInfo.serialNum, taskId:ids, comments:comments,processInstanceId:result.orderInfo.processInstanceId,isReject:table.row('.active').data().isReject});
    												}else{
    													$state.go('editSaleApply',{serialNum:result.orderInfo.serialNum, taskId:ids, comments:comments,processInstanceId:result.orderInfo.processInstanceId});
    												}
    											},
											function(errResponse) {
    												toastr.warning("办理失败！");
    												console.error('Error while apply ap');
    											}

									);
								}
							}
						},
						{
							text : "签收",
							className : "btn default",
							action: function(e, dt, node, config) { 
								var ids = '';
								table.$('input[type="checkbox"]').each(function() {
									if ($.contains(document, this)) {											
										if (this.checked) {
											// 将选中数据id放入ids中
											if (ids == '') {
												ids = this.value;
											} else
												ids = 'more';
										}
									}
								});
								
								if(ids==''){
									toastr.warning('请选择一个签收！');return;
								}else if(ids=='more'){
									toastr.warning('只能选择一个签收！');return;
								} else {
									claimTask(ids, 'dbTable');
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
						}*/ ];
		        	/*if($rootScope.userName=='sunsir'){
		        		tableButtons = []
		        	}*/
		        	var table = $("#dbTable")
		        	.DataTable(
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
		        					zeroRecords : "抱歉， 没有找到！",
		        					paginate : {
		        						"sFirst" : "首页",
		        						"sPrevious" : "前一页",
		        						"sNext" : "后一页",
		        						"sLast" : "尾页"
		        					}
		        				},

		        				buttons : tableButtons,
		        				dom : "<'row' <'col-md-12'B>><'row'<'col-md-6 col-sm-12'l><'col-md-6 col-sm-12'f>r><'table-scrollable't><'row'<'col-md-5 col-sm-12'i><'col-md-7 col-sm-12'p>>",
		        				order : [ [ 6, "asc" ] ],// 默认排序列及排序方式

		        				bRetrieve : true,
		        				lengthMenu : [
		        						[ 5, 10, 15, 30, -1 ],
		        						[ 5, 10, 15, 30,
		        								"All" ] ],
		        				pageLength : 10,// 每页显示数量
		        				processing : true,// loading等待框

		        				ajax : ctx
		        						+ "/rest/processAction/todoTask/" + 'saleOrder',// 加载待办列表数据

		        				"aoColumns" : [
		        					              { mData: 'taskId'},
		        									{
		        										mData : 'assign',
		        										mRender : function(
		        												data) {
		        											if (data == '') {
		        												return "待签收";
		        											} else {
		        												return "待办理";
		        											}
		        										}
		        									},
		        									{
		        										mData : 'num'
		        									},
		        									{
		        										mData : 'comName'
		        									},
		        									{
		        										mData : 'userName'
		        									},
		        									{
		        										mData : 'title'
		        									},
		        									{
		        										mData : 'taskName',
		        										mRender : function(
		        												data,
		        												type,
		        												row,
		        												meta) {
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
		        									},
		        									{
		        										mData : 'owner',
		        										mRender : function(
		        												data,
		        												type,
		        												row,
		        												meta) {
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
		        									},
		        									{
		        										mData : 'createTime',
		        										mRender : function(
		        												data) {
		        											if (data != null) {
		        												return timeStamp2String(data);
		        											} else
		        												return '';
		        										}
		        									},
		        									{
		        										mData : 'suspended',
		        										mRender : function(
		        												data) {
		        											if (data) {
		        												return "已挂起";
		        											} else {
		        												return "正常";
		        											}
		        										}
		        									} ],
		        						'aoColumnDefs': [ {
		        	                    	'targets' : 0,
		        	                    	'searchable' : false,
		        	                    	'orderable' : false,
		        	                    	'className' : 'dt-body-center',
		        	                    	'render' : function(data,type, full, meta) {
		        								return "<label class='mt-checkbox mt-checkbox-single mt-checkbox-outline'>" +
		        								"<input type='checkbox' class='checkboxes' value="+ data +" />" +
		        								"<span></span></label>";
		        							
		        	                    	}
		        	                    },
		        	                    {
		        	                    	'targets' : 2,
		        	                    	'searchable' : false,
		        	                    	'orderable' : false,
		        	                    	'className' : 'dt-body-center',
		        	                    	'render' : function(data,type, full, meta) {
		        	                    		/*if($rootScope.userName=='sunsir'){
		        	                    			return data
		        	        		        	}*/
		        								return '<a href="javascript:void(0);" ng-click="viewSaleOrderApply(\''+full.taskId+'\',\''+full.assign+'\',\''+full.isReject+'\')">'+data+'</a>';
		        							
		        	                    	},
		        	                    	"createdCell": function (td, cellData, rowData, full, col) {
	        									 $compile(td)($scope);
	        							       }
		        	                    } 
		        	                    ]

		        			})
		        			
		        			
		        			$("#dbTable").find(".group-checkable").change(function() {
					            var e = jQuery(this).attr("data-set"),
					            t = jQuery(this).is(":checked");
					            jQuery(e).each(function() {
					                t ? ($(this).prop("checked", !0), $(this).parents("tr").addClass("active")) : ($(this).prop("checked", !1), $(this).parents("tr").removeClass("active"))
					            })
					        }),
					        $("#dbTable").on("change", "tbody tr .checkboxes",
					        function() {
					            $(this).parents("tr").toggleClass("active")
					        })
	        
		        			return table;
		        	
		        	
		        }
		        $scope.viewSaleOrderApply=function(taskId,assign,isReject){//点击订单编号跳转至审批办理页面
		        	if(assign==''){
		        		claimTask(taskId, 'dbTable');
		        	}
		        	orderService
					.getAuditInfos(taskId)
					.then(
							function(result) {													
								var comments = ""//添加评论
									for (var i=0;i<result.commentList.length;i++){
										comments += "<tr><td>" + result.commentList[i].userName + "</td><td>" 
										+ (result.commentList[i].position==null?'':result.commentList[i].position) + "</td><td>"
										+ timeStamp2String(result.commentList[i].time) + "</td><td>" + result.commentList[i].content + "</td></tr>";														
									}
									if(result.actionType == 'audit'){//审批流程
										if(isReject=='true'){
											isReject=true;
										}else{
											isReject=false;
										}
										$state.go('approvalSaleApply',{serialNum:result.orderInfo.serialNum, taskId:taskId, comments:comments,processInstanceId:result.orderInfo.processInstanceId,isReject:isReject});
									}else{
										$state.go('editSaleApply',{serialNum:result.orderInfo.serialNum, taskId:taskId, comments:comments,processInstanceId:result.orderInfo.processInstanceId});
									}
								}
					);
		        }
		        function showYbTable(){
		        	var endTaskTable = $("#endTaskTable").DataTable(
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
		        					zeroRecords : "抱歉， 没有找到！",
		        					paginate : {
		        						"sFirst" : "首页",
		        						"sPrevious" : "前一页",
		        						"sNext" : "后一页",
		        						"sLast" : "尾页"
		        					}
		        				},
		        				order : [ [ 5, "desc" ] ],// 默认排序列及排序方式
		        				bRetrieve : true,
		        				lengthMenu : [
		        						[ 5, 10, 15, 30, -1 ],
		        						[ 5, 10, 15, 30,
		        								"All" ] ],
		        				pageLength : 10,// 每页显示数量
		        				processing : true,// loading等待框

		        				ajax : ctx
		        						+ "/rest/processAction/endTask/"+'saleOrder',// 加载已办列表数据

		        				"aoColumns" : [
		        						{
		        							mData : 'businessType',
		        							mRender : function(
		        									data) {
		        								return "订单申请";
		        							}
		        						},
		        						{
    										mData : 'num'
    									},
    									{
    										mData : 'comName'
    									},
		        						{
		        							mData : 'userName'
		        						},
		        						{
		        							mData : 'title'
		        						},
		        						{
		        							mData : 'startTime',
		        							mRender : function(
		        									data,
		        									type,
		        									row,
		        									meta) {
		        								return timeStamp2String(data);
		        							}
		        						},
		        						{
		        							mData : 'currentPointUserName',//claimTime
		        							mRender : function(
		        									data,
		        									type,
		        									row,
		        									meta) {
		        								if(data != null){
		        		                			return data;
		        		                		}else{
		        		                			return "";
		        		                		}
		        							}
		        						},
		        						{
		        							mData : 'endTime',
		        							mRender : function(
		        									data) {
		        								if (data != null) {
		        									return timeStamp2String(data);
		        								} else
		        									return '';
		        							}
		        						},
		        						{
		        							mData : 'deleteReason',
	        								mRender : function(
		        									data) {
		        								if (data != null) {
		        									return data
		        								} else
		        									return '';
		        							}
		        						}/*,
		        						{
		        							mData : 'version',
	        								mRender : function(
		        									data) {
		        								if (data != null) {
		        									return data
		        								} else
		        									return '';
		        							}
		        						}*/,
		        						{
		        							mData : 'revoke',
		        							mRender : function(data,type,row,meta) {
		        								if(isNull(row.version)&&isNull(row.deleteReason)){
		        									return "<a href='javascript:void(0);' onclick=\"userCancelApply('"+row.taskId+"','"+row.processInstanceId+"','endTaskTable','saleOrder')\">取消申请</a>";
		        								}else  if(isNull(row.version)&&row.deleteReason=='已取消申请'){
		        									return '';
		        								}else if(row.deleteReason!='已撤销'){
		        									return "<a href='javascript:void(0);' onclick=\"revoke('"+row.taskId+"','"+row.processInstanceId+"','endTaskTable')\">撤销</a>";
		        								}else{
		        									return '';
		        								}
		        							}
		        						}
		        						],
		        						'aoColumnDefs': [
		        			        	                    {
		        			        	                    	'targets' : 1,
		        			        	                    	'searchable' : false,
		        			        	                    	'orderable' : false,
		        			        	                    	'className' : 'dt-body-center',
		        			        	                    	'render' : function(data,type, full, meta) {
		        			        	                    		
		        			        								return '<a href="javascript:void(0);" ng-click="viewSaleOrder(\''+full.serialNum+'\',\''+full.businessType+'\')">'+data+'</a>';
		        			        							
		        			        	                    	},
		        			        	                    	"createdCell": function (td, cellData, rowData, full, col) {
		        		        									 $compile(td)($scope);
		        		        							       }
		        			        	                    } 
		        			        	                    ]

		        			})
		         return endTaskTable;
		        }

		        function handleTask(assign, taskId, processInstanceId){
		        	
		        	if(assign == ''){
		        		toastr.warning("此任务您还没有签收，请【签收】任务后再处理任务！！");
		        	}else{		
		        		$.ajax({url:ctx + "/rest/order/toApproval/" + taskId,
		        			type: 'POST',
		        			dataType: 'json',
		        			success:function(result){
		        				$("#taskId").val(taskId);
		        				$("#processInstanceId").val(processInstanceId);																					
		        				$("#orderId").val(result.orderInfo.serialNum);
		        				
		        				var comments = ""//添加评论
		        				for (var i=0;i<result.commentList.length;i++){
		        					comments += "<tr><td>" + result.commentList[i].userName + "</td><td>" 
		        					+ (result.commentList[i].position==null?'':result.commentList[i].position) + "</td><td>"
		        					+ timeStamp2String2(result.commentList[i].time) + "</td><td>" + result.commentList[i].content + "</td></tr>";														
		        				}
		        				
		        				if(result.actionType == 'audit'){//审批流程
		        					if(comments == ""){
		        						comments = "无评论";
		        					}else $("#comment_audit").html(comments);
//		        					$("#audit_beginDate").val(timeStamp2String2(result.order.beginDate));
//		        					$("#audit_endDate").val(timeStamp2String2(result.order.endDate));
//		        					$("#audit_days").val(result.order.days);
//		        					$("#audit_orderType").val(result.order.orderType);
		        					$("#audit_reason").val(result.orderInfo.remark);
		        					$('#auditOrderModal').modal('show');
		        				}else{//result.actionType == 'modify' 更改流程
		        					if(comments == ""){
		        						comments = "无评论";
		        					}else $("#comment_modify").html(comments);
//		        					$("#modify_beginDate").val(timeStamp2String2(result.order.beginDate));
//		        					$("#modify_endDate").val(timeStamp2String2(result.order.endDate));
//		        					$("#modify_days").val(result.order.days);
//		        					$("#modify_orderType").val(result.order.orderType);
		        					$("#modify_reason").val(result.orderInfo.reason);
		        					$('#modifyOrderModal').modal('show');
		        				}
		        				
		        		}});
		        		
		        	}
		        	
		        	
		        	
		        }
		      //********审批流程end****************//  
		        function doOrder(_url, mydata, modal){
		        	handle.blockUI("请稍等...");
		        	$.ajax( {
		    	        url : _url,
		    	        dataType:"text",
		    	        type: 'POST',
		    	        data : mydata,
		    	        success : function(data) {
		    	        	handle.unblockUI();
		    	        	showToastr('toast-bottom-right', 'success', data);
		    	        	$scope.cancelPage();
		    	        },
		    	        error : function(data) {
		    	        	handle.unblockUI();
		    	        	toastr.error('连接服务器出错,请登录重试！');
		    	        }
		    	     });
		    	}
		    	
		    	//审批通过
		    	$scope.orderPass = function() {
		    	    var mydata={"processInstanceId":$("#processInstanceId").val(),"orderId":$scope.saleOrder.serialNum,"content":$("#content").val(),
		    				"completeFlag":true};
		    	    var _url = ctx + "rest/order/complate/" + $("#taskId").val();
		    	    doOrder(_url, mydata, 'audit');
		    	};
		    	//审批不通过
		    	$scope.orderUnPass = function() {
		    		var mydata={"processInstanceId":$("#processInstanceId").val(),"orderId":$scope.saleOrder.serialNum,"content":$("#content").val(),
		    				"completeFlag":false};
		    		var _url = ctx + "rest/order/complate/" + $("#taskId").val();
		    		doOrder(_url, mydata, 'audit');
		    	};
		    	
		    	//重新申请
		    	$scope.replyOrder = function() {
		    	    var mydata={"processInstanceId":$("#processInstanceId").val(),
		    				"reApply":true,"orderId":$scope.saleOrder.serialNum,"reason":$scope.saleOrder.remark,"orderType":'saleOrder'};
		    		var _url = ctx + "rest/order/modifyOrder/" + $("#taskId").val();
		    		doOrder(_url, mydata, 'modify');
		    	};
		    	//取消申请
		    	$scope.cancelApply = function() {
		    	     var mydata={"processInstanceId":$("#processInstanceId").val(),
		    				"reApply":false,"orderId":$scope.saleOrder.serialNum,"reason":$scope.saleOrder.remark,"orderType":'saleOrder'};
		    		var _url = ctx + "rest/order/modifyOrder/" + $("#taskId").val();
		    		doOrder(_url, mydata, 'modify' );
		    	};
		    	
		    	/***************日志表格 start************************/
		    	var logTable 
		    	$scope.viewOrderLog = function (serialNum){
		    		$("#operateLogInfo").modal("show");
		    		//控制日志显示(数量日期和金额日期)
		    		/*if(logTable){
		    			logTable.ajax.url(ctx+"/rest/order/findOrderLog?serialNum=" + serialNum).load()
		    		}else{
		    			showLogTable("/rest/order/findOrderLog?serialNum=" + serialNum);
		    		}*/
		    		if(logTable){
			 			logTable.destroy();
			 		}
			 		showOrderLogTable("/rest/order/findOrderLog?serialNum=" + serialNum);
		    	}
		    	
		    	$scope.viewDeliverLog = function (serialNum){
		    		$("#deliverOperateLogInfo").modal("show");
		    		//控制日志显示(数量日期和金额日期)
		    		$scope.showDeliver=true;
		    		/*if(logTable){
		    			logTable.ajax.url(ctx+"/rest/order/findDeliverLog?serialNum=" + serialNum).load()
		    		}else{
		    			showLogTable("/rest/order/findDeliverLog?serialNum=" + serialNum);
		    		}*/
		    		if(logTable){
			 			logTable.destroy();
			 		}
			 		showDeliverLogTable("/rest/order/findDeliverLog?serialNum=" + serialNum);
		    	}
		    	
		    	$scope.viewPayLog = function (serialNum){
		    		$("#payOperateLogInfo").modal("show");
		    		//控制日志显示(数量日期和金额日期)
		    		$scope.showReceive=true;
		    		/*if(logTable){
		    			logTable.ajax.url(ctx+"/rest/order/findPayLog?serialNum=" + serialNum).load()
		    		}else{
		    			showLogTable("/rest/order/findPayLog?serialNum=" + serialNum);
		    		}*/
		    		if(logTable){
			 			logTable.destroy();
			 		}
			 		showPayLogTable("/rest/order/findPayLog?serialNum=" + serialNum);
		    	}
		    	$scope.goCollectMoney= function (serialNum){//从订单收款
		    		$state.go('addGatheringMoney',{orderSerialNum:serialNum});
		    	}
		   	 function showOrderLogTable(url){
			 		logTable = $("#select_operateLog")
			      	.DataTable(
			      			{
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
			      				order : [ [ 2, "asc" ] ],// 默认排序列及排序方式

			      				bRetrieve : true,
			      				lengthMenu : [
			      						[ 5, 10, 15, 30, -1 ],
			      						[ 5, 10, 15, 30,
			      								"All" ] ],
			      				pageLength : 10,// 每页显示数量
			      				processing : true,// loading等待框

			      				ajax : ctx+url,// 加载待办列表数据

			      				"aoColumns" : [
			      									{
			      										mData : 'operationDesc'
			      									},
			      									{
			      										mData : 'operator'
			      									},
			      									{
			      										mData : 'operationTime',
			 		        							mRender : function(
			 		        									data) {
			 		        								if (data != null) {
			 		        									return timeStamp2String(data);
			 		        								} else
			 		        									return '';
			 		        							}
			      									},
			      									{
			      										mData : 'remark'
			      									}]
			      			})
			      }
			 	 
			 	 function showDeliverLogTable(url){
				 		logTable = $("#select_deliverOperateLog")
				      	.DataTable(
				      			{
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
				      				order : [ [ 2, "asc" ] ],// 默认排序列及排序方式

				      				bRetrieve : true,
				      				lengthMenu : [
				      						[ 5, 10, 15, 30, -1 ],
				      						[ 5, 10, 15, 30,
				      								"All" ] ],
				      				pageLength : 10,// 每页显示数量
				      				processing : true,// loading等待框
				      				ajax : ctx+url,// 加载待办列表数据
				      				"aoColumns" : [
			      									{
			      										mData : 'operationDesc'
			      									},
			      									{
			    										mData : 'deliverCount',
			    	        							mRender : function(
			    	        									data) {
			    	        								if (data != null) {
			    	        									return data;
			    	        								} else
			    	        									return '---';
			    	        							}
			    									},
			    									{
			    										mData : 'timeData',
			    	        							mRender : function(
			    	        									data) {
			    	        								if (data != null) {
			    	        									return timeStamp2String(data);
			    	        								} else
			    	        									return '---';
			    	        							}
			    									},
			      									{
			      										mData : 'operator'
			      									},
			      									{
			      										mData : 'operationTime',
			 		        							mRender : function(
			 		        									data) {
			 		        								if (data != null) {
			 		        									return timeStamp2String(data);
			 		        								} else
			 		        									return '';
			 		        							}
			      									},
			      									{
			      										mData : 'remark'
			      									}]
				      			
				      			})
				      }
				 function showPayLogTable(url){
				 		logTable = $("#select_payOperateLog")
				      	.DataTable(
				      			{
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
				      				order : [ [ 2, "asc" ] ],// 默认排序列及排序方式

				      				bRetrieve : true,
				      				lengthMenu : [
				      						[ 5, 10, 15, 30, -1 ],
				      						[ 5, 10, 15, 30,
				      								"All" ] ],
				      				pageLength : 10,// 每页显示数量
				      				processing : true,// loading等待框
				      				ajax : ctx+url,// 加载待办列表数据
				      				"aoColumns" : [
			      									{
			      										mData : 'operationDesc'
			      									},
			      									{
			    										mData : 'payMoneyCount',
			    	        							mRender : function(
			    	        									data) {
			    	        								if (data != null) {
			    	        									return $filter('currency')(data,'');
			    	        								} else
			    	        									return '---';
			    	        							}
			    									},
			    									{
			    										mData : 'timeData',
			    	        							mRender : function(
			    	        									data) {
			    	        								if (data != null) {
			    	        									return timeStamp2String(data);
			    	        								} else
			    	        									return '---';
			    	        							}
			    									},
			      									{
			      										mData : 'operator'
			      									},
			      									{
			      										mData : 'operationTime',
			 		        							mRender : function(
			 		        									data) {
			 		        								if (data != null) {
			 		        									return timeStamp2String(data);
			 		        								} else
			 		        									return '';
			 		        							}
			      									},
			      									{
			      										mData : 'remark'
			      									}]
				      			
				      			})
				      }
		    	//从订单开票
		 	      $scope.goOpenInvoice= function(serialNum) {
		 	    	  $state.go('addOrEditInvoice',{inOrOut:"out",orderSerialNum:serialNum});
		 	       }
		    	
		    	 function showLogTable(url){
		    		logTable = $("#select_operateLog")
		         	.DataTable(
		         			{
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
		         				order : [ [ 2, "asc" ] ],// 默认排序列及排序方式

		         				bRetrieve : true,
		         				lengthMenu : [
		         						[ 5, 10, 15, 30, -1 ],
		         						[ 5, 10, 15, 30,
		         								"All" ] ],
		         				pageLength : 10,// 每页显示数量
		         				processing : true,// loading等待框

		         				ajax : ctx+url,// 加载待办列表数据

		         				"aoColumns" : [
		         									{
		         										mData : 'operationDesc'
		         									},
		         									{
		         										mData : 'operator'
		         									},
		         									{
		         										mData : 'operationTime',
		    		        							mRender : function(
		    		        									data) {
		    		        								if (data != null) {
		    		        									return timeStamp2String(data);
		    		        								} else
		    		        									return '';
		    		        							}
		         									},
		         									{
		         										mData : 'remark'
		         									}]
		         			})
		         }
		    	 
		    	 
		    	 /***************日志表格 end************************/
		    	 /** *************订单物料明细可检索化  start*************** */
		    	 $scope.pageIndex = 1; //记录当前页
		    	 $scope.pageSize = '10'; //每页的记录数
		    	 $scope.totalPage = '1'; //记录总页数
		    	 $scope.dispalyOrderMateriel = [];//页面显示结果
		    	 $scope.filterOrderMateriel = [];//查询筛选结果
		    	 
		    	 $scope.createFilterList = function(){
		    		 $scope.filterOrderMateriel = [];
		    		if($scope.orderMateriel.length>0&&$scope.queryStr&&!isNull($scope.queryStr)){
		    			for(var i = 0;i < $scope.orderMateriel.length;i++){// data.data为选择的标准物料
		    				if(!isNull(($scope.orderMateriel)[i].materiel.materielNum)&&($scope.orderMateriel)[i].materiel.materielNum.indexOf($scope.queryStr)>=0){
		    					$scope.filterOrderMateriel.push(angular.copy(($scope.orderMateriel)[i]));
		    				}else if(!isNull(($scope.orderMateriel)[i].materiel.materielName)&&($scope.orderMateriel)[i].materiel.materielName.indexOf($scope.queryStr)>=0){
		    					$scope.filterOrderMateriel.push(angular.copy(($scope.orderMateriel)[i]));
		    				}else if(!isNull(($scope.orderMateriel)[i].materiel.specifications)&&($scope.orderMateriel)[i].materiel.specifications.indexOf($scope.queryStr)>=0){
		    					$scope.filterOrderMateriel.push(angular.copy(($scope.orderMateriel)[i]));
		    				}
		    			}
		    		}else{
		    			$scope.filterOrderMateriel = angular.copy($scope.orderMateriel);
		    		}
		    		
		    	 };
		    	 
		    	 $scope.createDispalyList = function(){
		    		 $scope.dispalyOrderMateriel = $scope.filterOrderMateriel.slice(
		    				 ($scope.pageIndex-1)*$scope.pageSize,
		    				 $scope.pageIndex*$scope.pageSize);
		    		 
		    		 $scope.totalPage = Math.ceil($scope.filterOrderMateriel.length/$scope.pageSize);
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
		    	 
		    	/** *************发货计划列表  end*************** */ 
		    	 var deliveryTable;
		 		var loadDeliveryTable = function() {
		 			
		 			var a = 0;
		 			App.getViewPort().width < App.getResponsiveBreakpoint("md") ? $(".page-header").hasClass("page-header-fixed-mobile")&& (a = $(".page-header").outerHeight(!0)): 
		 				$(".page-header").hasClass("navbar-fixed-top") ? a = $(".page-header").outerHeight(!0): $("body").hasClass("page-header-fixed")&& (a = 64);

		 				 deliveryTable = $("#sample_deliveryTable").DataTable(
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
		 							order : [ [ 1, "desc" ] ],// 默认排序列及排序方式
		 							bRetrieve : true,
		 							"bScrollCollapse": true,
		 							lengthMenu : [
		 							              [ 5, 10, 15,15, 30, -1 ],
		 							              [ 5, 10, 15, 15,30, "All" ] ],
		 							              pageLength : 10,// 每页显示数量
		 							              processing : true,// loading等待框
		 							              ajax:"rest/delivery/findAllDeliveryList",//加载数据中user表数据
		 							              "aoColumns": [
		 							                            { mData: 'serialNum',
		 						                            	mRender : function(
		 																data,
		 																type,
		 																row,
		 																meta) {
		 						                            		return "<label class='mt-checkbox mt-checkbox-single mt-checkbox-outline'>" +
		 															"<input type='checkbox' class='checkboxes' value='1' />" +
		 															"<span></span></label>";
		 														}
		 						                            },
		 							                            { mData: 'deliverNum' },
		 							                            { mData: 'orderNum' },
		 							                           //{ mData: 'materielCount' },物料条目数
		 							                           { mData: 'receiver'},
		 							                          /*{ mData: 'deliverType'},*/
		 								                          { mData: 'materielTotalCount' },//物料总数
		 							                            { mData: 'packageType' },
		 							                            { mData: 'deliveryAddress'},
		 							                           { mData: 'deliverDate'},
		 							                            { mData: 'transportType'},
		 							                            { mData: 'status'/*,
		 					                            	mRender:function(data){
		 							                            		if(data!=""&&data!=null){
		 							                            			if(data=='0'){
		 							                            				return '<span  class="label label-sm label-success ng-scope">待发货</span>';
		 							                            			}else if(data=='PENDING'){
		 							                            				return '<span  class="label label-sm label-success ng-scope">审批中</span>';
		 							                            			}else if(data=='WAITING_FOR_APPROVAL'){
		 							                            				return '<span  class="label label-sm label-warning ng-scope">待审批</span>';				                            				
		 																	}else if(data=='3'){
		 																		return  '<span  class="label label-sm label-warning ng-scope">待收货</span>';
		 																	}else if(data=='APPROVAL_FAILED'){
		 																		return '<span  class="label label-sm label-danger ng-scope">审批失败</span>';
		 																	}else if(data=='4'){
		 																		return '<span  class="label label-sm label-success ng-scope">已收货</span>';
		 																	}else if(data=='1'){
		 																		return '<span  class="label label-sm label-warning ng-scope">待检验</span>';
		 																	}else if(data=='2'){
		 																		return '<span  class="label label-sm label-warning ng-scope">待出库</span>';
		 																	}else if(data=='6'){
		 																		return '<span  class="label label-sm label-warning ng-scope">待清关</span>';
		 																	}else if(data=='7'){
		 																		return '<span  class="label label-sm label-warning ng-scope">待报关</span>';
		 																	}else if(data=='8'){
		 																		return '<span  class="label label-sm label-warning ng-scope">待出库</span>';
		 																	}else if(data=='9'){
		 																		return '<span  class="label label-sm label-warning ng-scope">待出库</span>';
		 																	}else if(data=='10'){
		 																		return '<span  class="label label-sm label-warning ng-scope">待收货</span>';
		 																	}else if(data=='00'||data=='100'){
		 																		return '<span  class="label label-sm label-warning ng-scope">待申请</span>';		
		 																	}else{
		 																		return '';
		 																	}
		 							                            		}else{
		 							                            			return "";
		 							                            		}
		 							                            	}*/
		 							                            }, { mData: 'status'/* ,
				 					                            	mRender:function(data,row){
				 							                            		if(data!=""&&data!=null){
				 							                            			if(data=='8' ||data=='10'){
				 							                            				return '<span style="color:#fcb95b" ng-click="jumpToGetDeliveryInfo(\''+row.serialNum+'\')">收货</span>';
				 							                            			}else{
				 																		return '';
				 																	}
				 							                            		}else{
				 							                            			return "";
				 							                            		}
				 							                            	}*/
		 							                            	}
		 							                            ],
		 							                            'aoColumnDefs': [ {
		 							                            	'targets' : 0,
		 							                            	'searchable' : false,
		 							                            	'orderable' : false,
		 							                            	'className' : 'dt-body-center',
		 							                            	'render' : function(data,type, full, meta) {
		 							                            		return '<label class="mt-checkbox mt-checkbox-outline"><input type="checkbox" class="checkbox" value="'+ $('<div/>').text(data).html()+ '" name="id[]" /><span></span></label>';
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
		 							                            	'targets' : 2,
		 							                            	'className' : 'dt-body-center',
		 							                            	'render' : function(data,
		 							                            			type, row, meta) {
		 							                            		if(isNull(data)){
		 							                            			return "----";
		 							                            		}else
		 							                            		return '<a data-toggle="modal" ng-click="viewSaleOrder(\''+row.orderSerial+'\')" ">'+data+'</a>';
		 							                            	},
		 							                            	"createdCell": function (td, cellData, rowData, row, col) {
		 							                            		$compile(td)($scope);
		 							                            	}
		 							                            },
		 							                           {
		 							                            	'targets' : 9,
		 							                            	'className' : 'dt-body-center',
		 							                            	'render' : function(data,
		 							                            			type, row, meta) {
		 							                            		if(data!=""&&data!=null){
		 							                            			if(data=='0'){
		 							                            				return '<span  class="label label-sm label-success ng-scope">待发货</span>';
		 							                            			}else if(data=='PENDING'){
		 							                            				return '<span  class="label label-sm label-success ng-scope"  ng-click="viewGraphTrace('+row.processInstanceId+')">审批中</span>';
		 							                            			}else if(data=='WAITING_FOR_APPROVAL'){
		 							                            				return '<span  class="label label-sm label-warning ng-scope">待审批</span>';				                            				
		 																	}else if(data=='3'){
		 																		return  '<span  class="label label-sm label-warning ng-scope">待收货</span>';
		 																	}else if(data=='APPROVAL_FAILED'){
		 																		return '<span  class="label label-sm label-danger ng-scope">审批失败</span>';
		 																	}else if(data=='4'){
		 																		return '<span  class="label label-sm label-success ng-scope">已收货</span>';
		 																	}else if(data=='1'){
		 																		return '<span  class="label label-sm label-warning ng-scope">待检验</span>';
		 																	}else if(data=='2'){
		 																		return '<span  class="label label-sm label-warning ng-scope">待出库</span>';
		 																	}else if(data=='6'){
		 																		return '<span  class="label label-sm label-warning ng-scope">待清关</span>';
		 																	}else if(data=='7'){
		 																		return '<span  class="label label-sm label-warning ng-scope">待报关</span>';
		 																	}else if(data=='8'){
		 																		return '<span  class="label label-sm label-warning ng-scope">待出库</span>';
		 																	}else if(data=='9'){
		 																		return '<span  class="label label-sm label-warning ng-scope">待出库</span>';
		 																	}else if(data=='10'){
		 																		return '<span  class="label label-sm label-warning ng-scope">待收货</span>';
		 																	}/*else if(data=='00'||data=='100'){
		 																		return '<span  class="label label-sm label-warning ng-scope">待申请</span>';		
		 																	}*/else if(data=="000"){
		 								  										return '<span  class="label label-sm label-danger ng-scope">已失效</span>';
		 								  									}else{
		 																		return '';
		 																	}
		 							                            		}else{
		 							                            			return "";
		 							                            		}
		 							                            	},
		 							                            	"createdCell": function (td, cellData, rowData, row, col) {
		 							                            		$compile(td)($scope);
		 							                            	}
		 							                            },
		 							                           {
		 							                            	'targets' : 10,
		 							                            	'className' : 'dt-body-center',
		 							                            	'render' : function(data,
		 							                            			type, row, meta) {
		 							                            		if(data!=""&&data!=null){
		 							                            			if(data=='8' ||data=='10'){
		 							                            				/*return '';*/
		 							                            				return '<a href="javascript:void(0);" ng-click="jumpToGetDeliveryInfo(\''+row.serialNum+'\')">代收货</a>';
		 							                            			//	return '<span style="color:#fcb95b" ng-click="jumpToGetDeliveryInfo(\''+row.serialNum+'\')"></span>';
		 							                            			}else{
		 																		return '';
		 																	}
		 							                            		}else{
		 							                            			return "";
		 							                            		}
		 							                            	},
		 							                            	"createdCell": function (td, cellData, rowData, row, col) {
		 							                            		$compile(td)($scope);
		 							                            	}
		 							                            }
		 							                            ]}).on('order.dt',
		 							                            		function() {
		 							                            	console.log('排序');
		 							                            })
		 							                            
		 						                            $("#sample_deliveryTable").find(".group-checkable").change(function() {
		 										            var e = jQuery(this).attr("data-set"),
		 										            t = jQuery(this).is(":checked");
		 										            jQuery(e).each(function() {
		 										                t ? ($(this).prop("checked", !0), $(this).parents("tr").addClass("active")) : ($(this).prop("checked", !1), $(this).parents("tr").removeClass("active"))
		 										            })
		 											        }),
		 											        $("#sample_deliveryTable").on("change", "tbody tr .checkboxes",
		 											        function() {
		 											            $(this).parents("tr").toggleClass("active")
		 											        })
		 		}
		 		
		 		//修改
				$scope.jumpToEdit = function() {
					debugger;
					if(deliveryTable.rows('.active').data().length != 1){
						showToastr('toast-top-center', 'warning', '请选择一条数据进行修改！')
					}else{
						if(deliveryTable.row('.active').data().status=='0'){//deliveryTable.row('.active').data().status== '00'||deliveryTable.row('.active').data().status== '100'
							$state.go('editDeliveryPage',{serialNumEdit:deliveryTable.row('.active').data().serialNum,oprateType:"forSaleOrder"});
						}else showToastr('toast-top-center', 'warning', '该条数据已非初始状态，不能进行修改！')
					} 
				};
				//确认
				$scope.jumpToConfirm = function() {		
					if(deliveryTable.rows('.active').data().length != 1){
						showToastr('toast-top-center', 'warning', '请选择一条数据进行确认发货！')
					}else{
						
						if(deliveryTable.row('.active').data().status == '0'){
							var  deliverType=deliveryTable.row('.active').data().deliverType;
			    			 if(deliverType!='贸易发货'&&deliverType!='采购发货'){////如果是贸易发货类型/采购发货类型不需要审批
					    		   showToastr('toast-top-center', 'warning', '该发货计划未进行流程审批！');
					    		   return;
					    	 }else{
					    		 $state.go('viewDelivery',{serialNum:deliveryTable.row('.active').data().serialNum,oprateType:"forSaleOrder"});
					    	 }
							
						}else if(deliveryTable.row('.active').data().status == '11'){
							$state.go('viewDelivery',{serialNum:deliveryTable.row('.active').data().serialNum,oprateType:"forSaleOrder"});
							
						}else showToastr('toast-top-center', 'warning', '未处于待发货')
					} 
				};
		        //跳转到查看详情页面
		        $scope.jumpToGetDeliveryInfo  = function(serialNum) {
		        	$state.go('viewDelivery',{serialNum:serialNum,oprateType:"forSaleOrder"});
		        }; 
				// 删除
		        $scope.deliveryDel = function() {
		    		if(deliveryTable.rows('.active').data().length == 0){
		    			showToastr('toast-top-center', 'warning', '未勾选要删除数据！')
		    		} else {
		    			var ap = deliveryTable.rows('.active').data();
		    			var ids = '';
		    			for(i=0;i<ap.length;i++){
		    				if(ap[i].status != '0'){//ap[i].status != '00'||ap[i].status != '100'
		    					showToastr('toast-top-center', 'warning', '所选数据已非初始状态，不能删除！');
		    					return;
		    				}
		    				
		    				if(ids == ''){
		    					ids = ap[i].serialNum;
		    				}else ids = ids +','+ ap[i].serialNum;
		    				
		    			}
		    			
		    			$('#delDeliveryModal').modal('show');// 打开确认删除模态框

		    			$scope.confirmDel = function() {										
		    				DeliveryService.deleteDeliveryS(ids).then(
		    						function(data) {
		    							$('#delUsersModal').modal('hide');// 删除成功后关闭模态框
		    							$(".modal-backdrop").remove();
		    							toastr.success("删除成功！");
		    							$state.go('saleOrder',{},{reload:true}); // 重新加载datatables数据
		    						},
		    						function(errResponse) {
		    							/*console.error('Error while deleting Users');*/
		    						}

		    				);
		    			}
		    		}								
		    	};
		    	 $scope.exportContract = function(){
			    	 handle.blockUI("正在导出数据，请稍后"); 
			    	 window.location.href=$rootScope.basePath+"/rest/delivery/exportDelivery";
			    	 handle.unblockUI(); 
			       }
		    	//从订单签订合同
		 	       $scope.signContract= function(ids,comId) {
		 	    	  $state.go('saleOrderSign',{id:ids,comId:comId,type:"sale"});
		 	       }
		 	    //从订单代发货
		 	      $scope.deliveryAdd= function(serialNum) {
		 	    	  //先判断该销售订单物料是否均无库存,若均无,提示稍后建发货计划
		 	    		DeliveryService.judgeIsStock(serialNum).then(
	    						function(data) {
	    							if(data.flag){
	    								toastr.warning("当前销售订单物料均无库存,请稍后发货！");
	    							}else{
	    								 $state.go('addDelivery',{oprateType:"forSaleOrder",orderSerialNum:serialNum});
	    							}
	    						},
	    						function(errResponse) {
	    							/*console.error('Error while deleting Users');*/
	    						}

	    				);
		 	    	 
		 	       }
		 	   //销售订单分解采购
		 	      $scope.addBuyOrder= function(serialNum) {
		 	    	 $state.go('addBuyOrder',{oprateType:"saleOrderAddBuyOrder",serialNum:serialNum});
		 	      }
		 	     
		 	     //设置默认框架或委托方
		 	      $scope.setEntrustParty= function(obj) {
		 	    	//订单采购商变化时
			 	    	if($scope.contract.contractType=='销售订单'){
			 	    		orderService.findDefaultFrame("sale","order",$scope.saleOrder.buyComId).then(
			 	          		function(data){
				 	   	        	if(isNull(data)){
				 	   	        		return;
				 	   	        	}else{
				 	   	        		orderService.getFrameInfo(data.id).then(
						          		     function(data){//加载页面对象
						          		    	$scope.saleOrder.frameSerial = data.contract.id;
						          		    	$scope.saleOrder.frame=data.contract;
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
						          		    	if(isNull($scope.clauseSettlement.otherAmount)){
						          		    		$scope.clauseSettlement.otherAmount = 0;
						          		    	}
						    					
						          		    	$scope.saleOrder.contractContent = data.contract.contractContent
						                    	$scope.initContractContent();
						          		     },
						          		     function(error){
						          		         $scope.error = error;
						          		     }
						          		 )
				 	   	        	}
			 	   	        },
		          		     function(error){
		          		         $scope.error = error;
		          		     }
			 	   	     	);
			 	    	 }
		 	    	  
		 	    	  
		 	    	  for(var i=0;i<$scope.customers.length;i++){
		 	    		 if($scope.saleOrder.buyComId == $scope.customers[i].comId){
		 	    			$scope.saleOrder.entrustParty = $scope.customers[i].comName
		 	    			return;
		 	    		 }
		 	    	 }
		 	    	 
		 	       }
		 	     $scope.jumpToUrl= function(judgeString) {
		 	    	  $state.go('addDelivery',{oprateType:judgeString,orderSerialNum:null});
		 	     }
		 	    $scope.jumpToSkUrl= function(judgeString) {//新建收款
		 	    	  $state.go(judgeString,{serialNum:null,orderSerialNum:null});
		 	     }
		 		$scope.showSX=function(judgeString,index){
		 			debugger;
		 			if(index==undefined){
		 			if(judgeString=='f'){
		 				if($scope.showSXf!='1'){
		 					$scope.showSXf='1';
		 				}else{
		 					$scope.showSXf='0';
		 				}
		 			}
		 		}else{
		 			var val="showSXf"+index;
		 			if($scope[val]!=true){
		 				$scope[val]=true;
	 				}else{
	 					$scope[val]=false;
	 				}
		 		}
		 		}
		 		

		 	 	
		 	 	/** ************关联框架协议 start*************** */
		        $scope.selectFrame = function() {
					 $('#addFrame').modal('show');// 删除成功后关闭模态框
					 if(FrameTable){
				 		FrameTable.ajax.url(ctx+"/rest/order/findFrameList?type=buy&selectFor=order&comId="+$scope.saleOrder.buyComId).load()
	    			}else{
	    				loadFrameTable();
	    			}
				 };
		        
		        // 确认选择开始***************************************
				 $scope.confirmSelectFrame = function() {
					 var ids = '';
					 FrameTable.$('input[type="radio"]').each(function() {
							if ($.contains(document, this)) {											
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

					 if(ids!=''){
						 orderService.getFrameInfo(ids).then(
			          		     function(data){//加载页面对象
			          		    	$scope.saleOrder.frameSerial = data.contract.id;
			          		    	$scope.saleOrder.frame=data.contract;
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
			          		    	if(isNull($scope.clauseSettlement.otherAmount)){
			          		    		$scope.clauseSettlement.otherAmount = 0;
			          		    	}
			    					
			          		    	$scope.saleOrder.contractContent = data.contract.contractContent
			                    	$scope.initContractContent();
			          		    	
			          		    	$('#addFrame').modal('hide');// 删除成功后关闭模态框
			          		     },
			          		     function(error){
			          		         $scope.error = error;
			          		     }
			          		 );
					 }else{
						 toastr.error('未选择框架协议！');
					 }

				 };
		        
		        var FrameTable;
		 	    var loadFrameTable = function() {
		 	            a = 0;
		 	            App.getViewPort().width < App.getResponsiveBreakpoint("md") ? $(".page-header").hasClass("page-header-fixed-mobile") && (a = $(".page-header").outerHeight(!0)) : $(".page-header").hasClass("navbar-fixed-top") ? a = $(".page-header").outerHeight(!0) : $("body").hasClass("page-header-fixed") && (a = 64);
		 	            if(!isNull(FrameTable)) return;
		 	           FrameTable = $("#sample_Frame")
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
//		 	                serverSide: true,
		 	                ajax:"rest/order/findFrameList?type=buy&selectFor=order&comId="+$scope.saleOrder.buyComId,//加载数据中
		 	                "aoColumns": [
										{ mData: 'id'},
										{ mData: 'contractNum' },
										{ mData: 'comName' },
										{ mData: 'contractType' },
										{ mData: 'contractNum' },
										{ mData: 'startDate' },
										{ mData: 'endDate' },
										{ mData: 'contractNum' },
										{ mData: 'creator' },
										{ mData: 'versionNO' },

		 	                        ],
		 	               'aoColumnDefs' : [ {
		 								'targets' : 0,
		 								'searchable' : false,
		 								'orderable' : false,
		 								'render' : function(data,
		 										type, row, meta) {
		 									return "<label class='mt-radio mt-radio-single mt-radio-outline'>" +
											"<input type='radio' name='serialNum' class='checkboxes' value="+ row.id +" />" +
											"<span></span></label>";
		 								},
		 								"createdCell": function (td, cellData, rowData, row, col) {
		 									 $compile(td)($scope);
		 							       }
		 							}]

		 	            }).on('order.dt',
		 	            function() {
		 	                console.log('排序');
		 	            })
		 	        };
		 	   
		 	       /** *************关联框架协议  end*************** */
		 	       $scope.repeatDoneSelect = function(){
				    		   $('select[name="paymentType"]').selectpicker({
				                    showSubtext: true,
				                    size : 5
				                });
				    			$('select[name="paymentType"]').selectpicker('refresh');//刷新插件
			       };
			     	//********发货计划审批流程start****************//
			       $scope.submitDeliveryPlanApply  = function(serialNum,materielCount,status,processBase) {// 进入申请审批页面
			    	   
			    	 
			    		 	if(deliveryTable.rows('.active').data().length != 1){
				    			showToastr('toast-top-center', 'warning', '请选择一条任务进行流程申请！')
				    		}else{
				    			var materielCount= deliveryTable.row('.active').data().materielCount;
				    			var  deliverType=deliveryTable.row('.active').data().deliverType;
				    			 if(deliverType=='贸易发货'||deliverType=='采购发货'){////如果是贸易发货类型/采购发货类型不需要审批
						    		   showToastr('toast-top-center', 'warning', '该发货计划不需要流程审批！');
						    		   return;
						    	   }
				    			  if(materielCount==null||materielCount==0){
						    		   showToastr('toast-top-center', 'warning', '该发货计划没有物料，不能发起流程申请！');
						    		   return;
						    	   }
				    			var status = deliveryTable.row('.active').data().status;
				    			 if(status!='0'){//status != '00'&&status != '100'
				    				showToastr('toast-top-center', 'warning', '该发货计划已发起流程审批，不能再次申请！')
				    			}else {
				    				//调取发货申请判断
				    				var promise = DeliveryService.goApplyDelivery(deliveryTable.row('.active').data().serialNum);
				    				promise.then(function(data) {
				    					if(data.flag){
				    						if(data.isDel){
				    							toastr.warning("当前发货单关联销售订单已发货完毕,请删除当前发货单!");
				    						}else{toastr.warning("请重新编辑发货物料数量后再次申请!");}
				    						return;
				    					}else{
				    						$state.go('submitDeliveryPlanApply',{serialNum:deliveryTable.row('.active').data().serialNum});
				    					}
				    				}, function(data) {
				    					// 调用承诺接口reject();
				    					$(".modal-backdrop").remove();
				    					handle.unblockUI();
				    					toastr.error("申请失败！请联系管理员");
				    					console.log(data);
				    				});
				    			}
				    		}  
			        };
			        
			        function showDbDeliveryPlanTable(){
			        	
			        	var table = $("#dbDeliveryPlanTable")
			        	.DataTable(
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
			        					zeroRecords : "抱歉， 没有找到！",
			        					paginate : {
			        						"sFirst" : "首页",
			        						"sPrevious" : "前一页",
			        						"sNext" : "后一页",
			        						"sLast" : "尾页"
			        					}
			        				},

			        				buttons : [
			        						{
			        							text : "办理",
			        							className : "btn default",
			        							action: function(e, dt, node, config) { 
			        								
			        								var ids = '';
			        								table.$('input[type="checkbox"]').each(function() {
			        									if ($.contains(document, this)) {											
			        										if (this.checked) {
			        											// 将选中数据id放入ids中
			        											if (ids == '') {
			        												ids = this.value;
			        											} else
			        												ids = 'more';
			        										}
			        									}
			        								});
			        								
			        								if(ids==''){
			        									toastr.warning('请选择一个办理！');return;
			        								}else if(ids=='more'){
			        									toastr.warning('只能选择一个办理！');return;
			        								} else {
			        									if(table.row('.active').data().assign == ''){
			    											showToastr('toast-top-center', 'warning', '此任务您还没有签收，请【签收】任务后再处理任务！')
			    											return;
			    										}
			        									orderService
			        									.getAuditInfosForDelivery(ids)
														.then(
																function(result) {													
			        												var comments = ""//添加评论
				        												for (var i=0;i<result.commentList.length;i++){
				        													comments += "<tr><td>" + result.commentList[i].userName + "</td><td>" 
				        													+ (result.commentList[i].position==null?'':result.commentList[i].position) + "</td><td>"
				        													+ timeStamp2String(result.commentList[i].time) + "</td><td>" + result.commentList[i].content + "</td></tr>";														
				        												}
				        												if(result.actionType == 'audit'){//审批流程
				        													$state.go('approvalDeliveryPlanApply',{serialNum:result.deliveryVO.businessKey, taskId:ids, comments:comments,processInstanceId:result.deliveryVO.processInstanceId});
				        												}else{
				        													$state.go('editDeliveryPlanApply',{serialNumEdit:result.deliveryVO.businessKey, taskId:ids, comments:comments,processInstanceId:result.deliveryVO.processInstanceId});
				        												}
				        											},
																function(errResponse) {
				        												toastr.warning("办理失败！");
				        												console.error('Error while apply ap');
				        											}
				
														);
			        								}
			        							}
			        						},
			        						{
			        							text : "签收",
			        							className : "btn default",
			        							action: function(e, dt, node, config) { 
			        								var ids = '';
			        								table.$('input[type="checkbox"]').each(function() {
			        									if ($.contains(document, this)) {											
			        										if (this.checked) {
			        											// 将选中数据id放入ids中
			        											if (ids == '') {
			        												ids = this.value;
			        											} else
			        												ids = 'more';
			        										}
			        									}
			        								});
			        								
			        								if(ids==''){
			        									toastr.warning('请选择一个签收！');return;
			        								}else if(ids=='more'){
			        									toastr.warning('只能选择一个签收！');return;
			        								} else {
			        									claimTask(ids, 'dbDeliveryPlanTable');
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
			        				dom : "<'row' <'col-md-12'B>><'row'<'col-md-6 col-sm-12'l><'col-md-6 col-sm-12'f>r><'table-scrollable't><'row'<'col-md-5 col-sm-12'i><'col-md-7 col-sm-12'p>>",
			        				order : [ [ 6, "asc" ] ],// 默认排序列及排序方式

			        				bRetrieve : true,
			        				lengthMenu : [
			        						[ 5, 10, 15, 30, -1 ],
			        						[ 5, 10, 15, 30,
			        								"All" ] ],
			        				pageLength : 10,// 每页显示数量
			        				processing : true,// loading等待框

			        				ajax : ctx
			        						+ "/rest/processAction/todoTask/" + 'delivery',// 加载待办列表数据

			        				"aoColumns" : [
			        					              { mData: 'taskId'},
			        									{
			        										mData : 'assign',
			        										mRender : function(
			        												data) {
			        											if (data == '') {
			        												return "待签收";
			        											} else {
			        												return "待办理";
			        											}
			        										}
			        									},
			        									{
			        										mData : 'num'
			        									},
			        									{
			        										mData : 'comName'
			        									},
			        									{
			        										mData : 'userName'
			        									},
			        									{
			        										mData : 'title'
			        									},
			        									{
			        										mData : 'taskName',
			        										mRender : function(
			        												data,
			        												type,
			        												row,
			        												meta) {
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
			        									},
			        									{
			        										mData : 'owner',
			        										mRender : function(
			        												data,
			        												type,
			        												row,
			        												meta) {
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
			        									},
			        									{
			        										mData : 'createTime',
			        										mRender : function(
			        												data) {
			        											if (data != null) {
			        												return timeStamp2String(data);
			        											} else
			        												return '';
			        										}
			        									},
			        									{
			        										mData : 'suspended',
			        										mRender : function(
			        												data) {
			        											if (data) {
			        												return "已挂起";
			        											} else {
			        												return "正常";
			        											}
			        										}
			        									} ],
			        						'aoColumnDefs': [ {
			        	                    	'targets' : 0,
			        	                    	'searchable' : false,
			        	                    	'orderable' : false,
			        	                    	'className' : 'dt-body-center',
			        	                    	'render' : function(data,type, full, meta) {
			        								return "<label class='mt-checkbox mt-checkbox-single mt-checkbox-outline'>" +
			        								"<input type='checkbox' class='checkboxes' value="+ data +" />" +
			        								"<span></span></label>";
			        							
			        	                    	}
			        	                    },
			        	                    {
			        	                    	'targets' : 2,
			        	                    	'searchable' : false,
			        	                    	'orderable' : false,
			        	                    	'className' : 'dt-body-center',
			        	                    	'render' : function(data,type, full, meta) {
			        								return '<a href="javascript:void(0);" ng-click="goDeliveryInfoApply(\''+full.taskId+'\',\''+full.assign+'\',\''+full.serialNum+'\')">'+data+'</a>';
			        	                    		//return data;
			        							
			        	                    	},
			        	                    	"createdCell": function (td, cellData, rowData, full, col) {
		        									 $compile(td)($scope);
		        							       }
			        	                    } 
			        	                    ]

			        			})
			        			
			        			
			        			$("#dbDeliveryPlanTable").find(".group-checkable").change(function() {
						            var e = jQuery(this).attr("data-set"),
						            t = jQuery(this).is(":checked");
						            jQuery(e).each(function() {
						                t ? ($(this).prop("checked", !0), $(this).parents("tr").addClass("active")) : ($(this).prop("checked", !1), $(this).parents("tr").removeClass("active"))
						            })
						        }),
						        $("#dbDeliveryPlanTable").on("change", "tbody tr .checkboxes",
						        function() {
						            $(this).parents("tr").toggleClass("active")
						        })
		        
			        			return table;
			        	
			        	
			        }
			        function showYbDeliveryPlanTable(){
		        	var endTaskTable = $("#endTaskDeliveryPlanTable").DataTable(
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
		        					zeroRecords : "抱歉， 没有找到！",
		        					paginate : {
		        						"sFirst" : "首页",
		        						"sPrevious" : "前一页",
		        						"sNext" : "后一页",
		        						"sLast" : "尾页"
		        					}
		        				},
		        				order : [ [ 5, "desc" ] ],// 默认排序列及排序方式
		        				bRetrieve : true,
		        				lengthMenu : [
		        						[ 5, 10, 15, 30, -1 ],
		        						[ 5, 10, 15, 30,
		        								"All" ] ],
		        				pageLength : 10,// 每页显示数量
		        				processing : true,// loading等待框

		        				ajax : ctx
		        						+ "/rest/processAction/endTask/"+'delivery',// 加载已办列表数据

		        				"aoColumns" : [
		        						{
		        							mData : 'businessType',
		        							mRender : function(
		        									data) {
		        								return "发货计划申请";
		        							}
		        						},
		        						{
    										mData : 'num'
    									},
    									{
    										mData : 'comName'
    									},
		        						{
		        							mData : 'userName'
		        						},
		        						{
		        							mData : 'title'
		        						},
		        						{
		        							mData : 'startTime',
		        							mRender : function(
		        									data,
		        									type,
		        									row,
		        									meta) {
		        								return timeStamp2String(data);
		        							}
		        						},
		        						{
		        							mData : 'currentPointUserName',//claimTime
		        							mRender : function(
		        									data,
		        									type,
		        									row,
		        									meta) {
		        								if(data != null){
		        		                			return data;
		        		                		}else{
		        		                			return "";
		        		                		}
		        							}
		        						},
		        						{
		        							mData : 'endTime',
		        							mRender : function(
		        									data) {
		        								if (data != null) {
		        									return timeStamp2String(data);
		        								} else
		        									return '';
		        							}
		        						},
		        						{
		        							mData : 'deleteReason',
	        								mRender : function(
		        									data) {
		        								if (data != null) {
		        									return data
		        								} else
		        									return '';
		        							}
		        						}/*,
		        						{
		        							mData : 'version',
	        								mRender : function(
		        									data) {
		        								if (data != null) {
		        									return data
		        								} else
		        									return '';
		        							}
		        						}*/,
		        						{
		        							mData : 'revoke',
		        							mRender : function(data,type,row,meta) {
		        								if(isNull(row.version)&&isNull(row.deleteReason)){
		        									return "<a href='javascript:void(0);' onclick=\"userCancelApply('"+row.taskId+"','"+row.processInstanceId+"','endTaskDeliveryPlanTable','delivery')\">取消申请</a>";
		        								}else  if(isNull(row.version)&&row.deleteReason=='已取消申请'){
		        									return '';
		        								}else if(row.deleteReason!='已撤销'){
		        									return "<a href='javascript:void(0);' onclick=\"revoke('"+row.taskId+"','"+row.processInstanceId+"','endTaskDeliveryPlanTable')\">撤销</a>";
		        								}else{
		        									return '';
		        								}
		        							}
		        						}
		        						],
		        						'aoColumnDefs': [
		        			        	                    {
		        			        	                    	'targets' : 1,
		        			        	                    	'searchable' : false,
		        			        	                    	'orderable' : false,
		        			        	                    	'className' : 'dt-body-center',
		        			        	                    	'render' : function(data,type, full, meta) {
		        			        	                    		//return data;
		        			        	                    		return '<a href="javascript:void(0);" ng-click="viewDeliveryInfo(\''+full.serialNum+'\')">'+data+'</a>';
		        			        							
		        			        	                    	},
		        			        	                    	"createdCell": function (td, cellData, rowData, full, col) {
		        		        									 $compile(td)($scope);
		        		        							       }
		        			        	                    } 
		        			        	                    ]

		        			})
		         return endTaskTable;
		        }
			        $scope.viewDeliveryInfo = function(serialNum){//已办查看
			        	$state.go("viewDelivery",{serialNum:serialNum,oprateType:'forSaleOrder'});
			        }
			        $scope.goDeliveryInfoApply = function(taskId,assign,serialNum){//待办通过单号跳审批
			        	if(assign==''){
			        		claimTask(taskId, 'dbTable');
			        	}
			        	orderService
						.getAuditInfosForDelivery(taskId)
						.then(
								function(result) {													
									var comments = ""//添加评论
										for (var i=0;i<result.commentList.length;i++){
											comments += "<tr><td>" + result.commentList[i].userName + "</td><td>" 
											+ (result.commentList[i].position==null?'':result.commentList[i].position) + "</td><td>"
											+ timeStamp2String(result.commentList[i].time) + "</td><td>" + result.commentList[i].content + "</td></tr>";														
										}
										if(result.actionType == 'audit'){//审批流程
											$state.go('approvalDeliveryPlanApply',{serialNum:result.deliveryVO.businessKey, taskId:taskId, comments:comments,processInstanceId:result.deliveryVO.processInstanceId});
										}else{
											$state.go('editDeliveryPlanApply',{serialNumEdit:result.deliveryVO.businessKey, taskId:taskId, comments:comments,processInstanceId:result.deliveryVO.processInstanceId});
										}
									});
			        }
			        
			      //收款计划列表
			    	var tableGather;
			    	var loadGatheringTable = function() {
			    		var a = 0;
			    		App.getViewPort().width < App.getResponsiveBreakpoint("md") ? $(".page-header").hasClass("page-header-fixed-mobile")&& (a = $(".page-header").outerHeight(!0)): 
			    			$(".page-header").hasClass("navbar-fixed-top") ? a = $(".page-header").outerHeight(!0): $("body").hasClass("page-header-fixed")&& (a = 64);

			    			tableGather = $("#sample_4").DataTable(
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
			    						order : [ [ 1, "asc" ] ],// 默认排序列及排序方式
			    						bRetrieve : true,
			    						// searching: true,//是否过滤检索
			    						// ordering: true,//是否排序
			    						lengthMenu : [
			    						              [ 5, 10, 15,15, 30, -1 ],
			    						              [ 5, 10, 15, 15,30, "All" ] ],
			    						              pageLength : 10,// 每页显示数量
			    						              processing : true,// loading等待框
			    						              // serverSide: true,
			    						              ajax: "rest/pay/findAllGatheringMoneyRecord",//加载数据中user表数据
			    						              "aoColumns": [
			    						                            { mData: 'serialNum'},
			    						                            { mData: 'paymentNum' },//paymentType
			    						                            { mData: 'paymentType' },//paymentType
			    						                            { mData: 'applyCurrency' },
			    						                            { mData: 'applyPaymentAmount' },
			    						                            { mData: 'playPaymentDate' },
			    						                            { mData: 'buyComId'},
			    						                            { mData: 'paymentDate'},
			    						                            { mData: 'paymentAmount'},
			    						                            { mData: 'isBill',
			    						                            	mRender:function(data){
			    						                            		if(data=='0'){
			    						                            				return '否';
			    						                            		}else{
			    						                            			return "是";
			    						                            		}
			    						                            	}
			    						                            	},
			    						                            { mData: 'status',
			    						                            	mRender:function(data){
			    						                            		if(data!=""&&data!=null){
			    						                            			if(data=='0'){
			    						                            				return '待核销';
			    						                            			}else if(data=='1'){
			    						                            				return '部分核销';
			    						                            			}else if(data=='2'){
			    						                            				return '已完成';
			    						                            			}
			    						                            		}else{
			    						                            			return "";
			    						                            		}
			    						                            	}
			    						                            },
			    						                            { mData: 'status'
			    						                            	},
			    						                            ],
			    						                            'aoColumnDefs': [ {
			    						                            	'targets' : 0,
			    						                            	'searchable' : false,
			    						                            	'orderable' : false,
			    						                            	'className' : 'dt-body-center',
			    						                            	'render' : function(data,type, full, meta) {
			    						                            		return '<label class="mt-checkbox mt-checkbox-single mt-checkbox-outline"><input type="checkbox" name="id[]" value="'+ $('<div/>').text(data).html()+ '"><span></span></label>';
			    					                            	}
			    						                            } ,
			    						                            {
			    						                            	'targets' : 1,
			    						                            	'className' : 'dt-body-center',
			    						                            	'render' : function(data,
			    						                            			type, row, meta) {
			    						                            		return '<a  data-toggle="modal" ng-click="jumpToGetPayInfo(\''+row.serialNum+'\')" ">'+data+'</a>';
			    						                            	},
			    						                            	"createdCell": function (td, cellData, rowData, row, col) {
			    						                            		$compile(td)($scope);
			    						                            	}
			    						                            } ,
			    						                            {
			    						                            	'targets' : 5,
			    						                            	'className' : 'dt-body-center',
			    						                            	'render' : function(data,
			    						                            			type, row, meta) {
			    						                            		if(data==null||data==''){
			    						                            			return row.applyDate;
			    						                            		}else{
			    						                            			return data;
			    						                            		}
			    						                            	},
			    						                            	
			    						                            } ,
			    						                            {
			    						                            	'targets' : 11,
			    						                            	'className' : 'dt-body-center',
			    						                            	'render' : function(data,
			    						                            			type, row, meta) {
			    						                            	if(row.status=='1'||row.status=='0'){
			    						                            			return '<a href="javascript:void(0);" ng-click="goVerificate(\''+row.serialNum+'\')">核销</a>';
			    						                            		}else{
			    						                            			return '';	
			    						                            		}
			    						                            	},
			    						                            	"createdCell": function (td, cellData, rowData, row, col) {
			    						                            		$compile(td)($scope);
			    						                            	}
			    						                            }
			    						                            ]}).on('order.dt',
			    						                            		function() {
			    						                            	console.log('排序');
			    						                            })
			    	}
			    	//修改
			    	$scope.jumpToEditGather = function() {
			    		var ids = '';
			    		// Iterate over all checkboxes in the table
			    		tableGather.$('input[type="checkbox"]').each(
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
			    			toastr.warning('请选择一个收款！');return;
			    		}else if(ids=='more'){
			    			toastr.warning('只能选择一个收款！');return;
			    		}
			    		$state.go('addForSaleOrderGatheringMoney',{serialNum:ids});
			    	};
			    	//删除
			    	$scope.delGather = function() {
			    		var ids = '';
			    		// Iterate over all checkboxes in the table
			    		tableGather.$('input[type="checkbox"]').each(function() {
			    			// If checkbox exist in DOM
			    			if ($.contains(document, this)) {
			    				// If checkbox is checked
			    				if (this.checked) {
			    					// 将选中数据id放入ids中
			    					if (ids == '') {
			    						ids = this.value;
			    					} else
			    						ids = ids + ',' + this.value;
			    				}
			    			}
			    		});

			    		if (ids == '') {// 未勾选删除数据									
			    			toastr.warning("未勾选要删除数据！");
			    		} else {
			    			$('#delUsersModal').modal('show');// 打开确认删除模态框

			    			$scope.confirmDel = function() {										
			    				GatheringMoneyService.delPaymentRecord(ids).then(
			    						function(data) {
			    							$('#delUsersModal').modal('hide');// 删除成功后关闭模态框
			    							$(".modal-backdrop").remove();
			    							toastr.success("删除成功！");
			    							$state.go('saleOrder',{},{reload:true}); // 重新加载datatables数据
			    						},
			    						function(errResponse) {
			    							/*console.error('Error while deleting Users');*/
			    							alert(123);
			    						}

			    				);
			    			}
			    		}								
			    	};
			    	$scope.jumpToGetPayInfo  = function(serialNum) {//查看收款计划详情
			        	$state.go('viewForSaleOrderGatheringMoney',{serialNum:serialNum});
			        }; 
			      //导出收款
			    	$scope.exportPay = function(){
			    		handle.blockUI("正在导出数据，请稍后"); 
			    		window.location.href=$rootScope.basePath+"/rest/pay/exportGatheringMoney";
			    		handle.unblockUI(); 
			    	}

}]);
