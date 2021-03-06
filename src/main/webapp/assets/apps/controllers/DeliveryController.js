angular.module('MetronicApp').controller('DeliveryController', ['$rootScope','$scope','$http', 'settings', '$q','DeliveryService','commonService','$state','$compile','$stateParams','$filter','$location','FileUploader',
                                                                function($rootScope,$scope,$http,settings, $q,DeliveryService,commonService,$state,$compile,$stateParams,$filter,$location,FileUploader) {
	$scope.$on('$viewContentLoaded', function() {   
		// initialize core components
		handle = new pageHandle();
		App.initAjax();

		// set default layout mode
		$rootScope.settings.layout.pageContentWhite = true;
		$rootScope.settings.layout.pageBodySolid = false;
		$rootScope.settings.layout.pageSidebarClosed = false;
		
		//加载发货列表
		loadMainTable();
		//加载销售订单表
		loadMainTable1();
		//加载物料表
		selectParentMateriel();
	debugger;
		getSupplyComId();
		//控制输入框和span标签的显示
		$scope.span =false;
		$scope.inputDeliveryInfo = true;
		$scope.inputTakeDeliveryInfo = true;
		
		
		$scope.transportType="水路运输";
		$scope.serialNums = [];	
		$scope.orderSerial=null;
		console.log($location.path());
		
		/*$scope.takeDelivery.warehouseSerial=null;*/
		if($state.current.name=="addDelivery"){
			$scope.deliveryTransport={};
			$scope.takeDelivery={};
			validatedeliverFileInit();
		$rootScope.setNumCode("SE",function(newCode){
			$scope.delivery.deliverNum = newCode;
		});
		$scope.delivery={};
		$scope.deliver={};
    	$scope.deliver.deliverDate=$filter('date')(new Date(), 'yyyy-MM-dd');
    	/*$scope.delivery.packageSpecifications="原厂包装";*/
    	$scope.delivery.deliverType="贸易发货";
    	$scope.deliver.approvalDate=$filter('date')(new Date(), 'yyyy-MM-dd');
    	getCurrentUser();
	    	if(!isNull($stateParams.orderSerialNum)){//由订单发货
				//查找是否已有进行中发货单
    			DeliveryService.getDoingDelivery($stateParams.orderSerialNum).then(
             		     function(data){
             		    	 if(isNull(data.data)){
             		    		$scope.getSaleOrderInfo($stateParams.orderSerialNum);
             		    	 }else{
             		    		$scope.getDeliveryEditInfo(data.data.serialNum);
             		    	 }
             		     },
               		     function(error){
               		         $scope.error = error;
               		     }
               		 );
			}
		}
		if($stateParams.oprateType == "forSaleOrder"){
    		$scope.confirmDeliverybtn = true;
		}else if($stateParams.oprateType == "forSupplyOrder"){
			$scope.confirmDeliverybtn = false;
		}
		/*//根据参数查询对象
	    if($stateParams.orderSerialNum){
	    	$scope.getDeliveryInfo($stateParams.orderSerialNum,$stateParams.taskId, $stateParams.comments);	
	    }*/
		//根据参数查询对象
    if($stateParams.serialNum){
    	getCurrentUserName();
    	$scope.getDeliveryInfo($stateParams.serialNum,$stateParams.taskId, $stateParams.comments);	
    }
    
       //根据参数查询对象
    if($stateParams.serialNumEdit){
    	//$scope.getDeliveryInfo($stateParams.serialNum,$stateParams.taskId, $stateParams.comments);	
    	$scope.getDeliveryEditInfo($stateParams.serialNumEdit,$stateParams.taskId, $stateParams.comments);
    }
    $scope.oprateType=$stateParams.oprateType;
/*    if($scope.delivery==undefined||$scope.delivery.deliverDate==undefined){
    	
    }*/
    
    if ($.validator) {
    	
    	    $.validator.prototype.elements = function () {
    	
    	        var validator = this,
    	
    	            rulesCache = {};
    	
    	        return $([]).add(this.currentForm.elements)
    	
    	        .filter(":input")
    	
    	        .not(":submit, :reset, :image, [disabled]")
    	
    	        .not(this.settings.ignore)
    	
    	        .filter(function () {
    	
    	            var elementIdentification = this.id || this.name;
    	
    	            !elementIdentification && validator.settings.debug && window.console && console.error("%o has no id nor name assigned", this);
    	
    	            if (elementIdentification in rulesCache || !validator.objectLength($(this).rules()))
    	                return false;
    	            rulesCache[elementIdentification] = true;
    	            return true;
    	
    	        });
    	
    	    };
    	
    	}
    
    //查询仓库列表
    initWarehouses('pt',null,"out");//先加载平台发货仓库列表
    if($stateParams.oprateType != "forSupplyOrder"){
    initCompanyaddresses('pt','fa');//先加载平台发货地址列表
    }else{
    	 initCompanyaddresses(null,'fa');
    }
		//$scope.getWarehouseList();
		if($state.current.name=="delivery"){
			var type = handle.getCookie("d_type");
 			if(type=="stockOut"){
	 				//loadStockInTable();
	 				//$('#delivery_tab a:last').tab('show');
	 				$('#delivery_tab a:last').parent().addClass('active');
	 				$('#delivery_tab a:first').parent().removeClass('active');
	 				$("#tab_15_2").addClass("active");
	 				$("#tab_15_1").removeClass("active");
	 				 $("#tip").text("出库记录");
	 		}else{
	 				//loadTakeDelieryTable();
	 				$('#delivery_tab a:first').parent().addClass('active');
	 				$('#delivery_tab a:last').parent().removeClass('active');
	 				$("#tab_15_1").addClass("active");
	 				$("#tab_15_2").removeClass("active");
	 				 $("#tip").text("发货计划");
	 				//$('#delivery_tab a:first').tab('show');
	 		}
 			loadStockOutTable();
		}
    
	});
	
	//添加页面的ng-repeat加载完成事件
	$scope.repeatDone = function(){
    	$('.date-picker').datepicker({
			rtl: App.isRTL(),
			orientation: "left",
			autoclose: true,
			dateFormat:"yyyy-mm-dd",
			language: "zh-CN",
    	})
    	
   };
   
   /**
	 * 加载当前用户信息
	 */
	var getCurrentUser = function(){
		var promise = commonService.getCurrentUser();
		promise.then(function(data){
			debugger;
			$scope.user = data.data;
			console.log($stateParams.oprateType);
			if($stateParams.oprateType=="forSaleOrder"||$stateParams.oprateType=="forSupplyOrder"){
				$scope.deliver.maker= data.data.userName;
			}
			
		},function(data){
			//调用承诺接口reject();
		});
	}
	
	 /**
	 * 加载当前用户信息
	 */
	var getCurrentUserName = function(){
		var promise = commonService.getCurrentUser();
		promise.then(function(data){
			$scope.user = data.data;
			$scope.department=data.data.department;
//			$scope.currentUserName=data.data.userName;
			
			
		},function(data){
			//调用承诺接口reject();
		});
	}
   /**
	 * 加载仓库数据
	 */
	var initWarehouses = function(judgeString,comId,index){
		var promise = DeliveryService.getWarehouseList(judgeString,comId);
       	promise.then(function(data){
       		if(index=="out"){
       		 $scope.warehouseListf=data;
       		setTimeout(function () {
       		  	$("#deliverWarehouse").selectpicker({
                showSubtext: true
            });
			$('#deliverWarehouse').selectpicker('refresh');//刷新插件
               }, 100);
       		 
       		}else if(index=="in"){
       		 $scope.warehouseLists=data;
       		setTimeout(function () {
       			$("#takeDeliverWarehouse").selectpicker({
                       showSubtext: true
                   });
       			$('#takeDeliverWarehouse').selectpicker('refresh');//刷新插件
               }, 100);
       		}
       	},function(data){
       		//调用承诺接口reject();
       	});
	}
	/**
	 * 加载收货/发货地址列表数据
	 */
	var initCompanyaddresses = function(comId,judgeString){
		if(comId==null&&$stateParams.oprateType!='forSupplyOrder'){
			comId='pt';
		}
		var promise = DeliveryService.getCompanyaddressList(comId);
       	promise.then(function(data){
       		if(judgeString=='fa'){
       		 $scope.companyAddressesf=data;
       		setTimeout(function () {
       		  	$("select[name='warehouseAddress1']").selectpicker({
                showSubtext: true
            });
			$("select[name='warehouseAddress1']").selectpicker('refresh');//刷新插件
               }, 100);
       		 
       		}else  if(judgeString=='sh'){
       			
       		 $scope.companyAddressess=data;
       		setTimeout(function () {//
       			$("select[name='takeDeliveryWarehouseAddress1']").selectpicker({
                       showSubtext: true
                   });
       			$("select[name='takeDeliveryWarehouseAddress1']").selectpicker('refresh');//刷新插件
               }, 100);
       		}
       	},function(data){
       		//调用承诺接口reject();
       	});
	}
	

	$scope.showSX=function(judgeString){
		debugger;
		if(judgeString=='s'){
			if($scope.saleOrder==undefined){
				toastr.warning("请先选择销售订单");
				return;
			}
			if($scope.companyAddressess.length==0){
				toastr.warning("该企业无联系地址");
				return;
			}
			if($scope.showSXs!='1'){
				$scope.showSXs='1';
			}else{
				$scope.showSXs='0';
			}
			$scope.takeDeliveryWarehouseAddress='';
		}else{
			if($scope.companyAddressesf.length==0){
				toastr.warning("该企业无联系地址");
			return;
			}
			if($scope.showSXf!='1'){
				$scope.showSXf='1';
			}else{
				$scope.showSXf='0';
			}
			$scope.warehouseAddress='';
		}
	
	}
   //编辑页面的ng-repeat完成
   $scope.repeatDone1 = function(){
	   $('.date-picker').datepicker({
			rtl: App.isRTL(),
			orientation: "left",
			autoclose: true,
			dateFormat:"yyyy-mm-dd",
			language: "zh-CN",
   	})
   	//给date-picker default赋值
   	for(var i=0;i<$scope.deliveryMaterielE.length;i++){
   		if($scope.deliveryMaterielE[i].manufactureDate!=null&&$scope.deliveryMaterielE[i].manufactureDate!=""){
   			var datee="'"+$scope.deliveryMaterielE[i].manufactureDate+"'";
   	   		$('.date-picker').eq(i).datepicker('setDate',datee);
   		}else{
   			$('.date-picker').eq(i).datepicker('setDate',new Date());
   		}
   	}
   	
  };
	
	//确认发货
	$scope.goDelivery=function (judgeString){
			debugger;
			if($scope.saveMateriel==undefined||$scope.saveMateriel==false){
				toastr.warning("请先保存物料");
				return;
			}
		if($scope.inputDeliveryInfo==true&&judgeString==undefined){
			if($('#form_sample_deliverInfo').valid()){
				$scope.delivery.status="1";
			$scope.saveDeliveryInfo($scope.delivery.status);//先保存
			}
		}else if(judgeString=='view'||judgeString=='add'){
		 	/*$scope.confirmDelivery($scope.delivery.serialNum);*/
	   		var promise = DeliveryService.goDelivery($scope.delivery.serialNum);
			promise.then(function(data) {
				if(data.flag){
					if(data.isDel){
						toastr.warning("当前发货单已发货完毕请删除当前发货单!");
					}else{toastr.warning("请重新编辑发货物料数量后再确认发货!");}
					return;
				}else{
					toastr.success("确认发货成功");
					if(judgeString=='add'){
						$scope.delivery.status=1;
					}else {
						$scope.deliveryDetail.status=1;
					}
				}
			}, function(data) {
				// 调用承诺接口reject();
				$(".modal-backdrop").remove();
				handle.unblockUI();
				toastr.error("发货失败！请联系管理员");
				console.log(data);
			});
		}else{//收货
			var promise = DeliveryService.goTakeDelivery($scope.delivery.serialNum);//收货
			promise.then(function(data) {
				if(data.flag=='0'){
					toastr.success("确认收货成功");
					if(judgeString!='add'){
						$scope.deliveryDetail.status=4;
					}
				}else{
					toastr.error("确认收货失败！请联系管理员");
				}
			
			}, function(data) {
				// 调用承诺接口reject();
				$(".modal-backdrop").remove();
				handle.unblockUI();
				toastr.error("发货失败！请联系管理员");
				console.log(data);
			});
		}
		
		
		
		}
	
	
	
	var getSupplyComId=function (){
		var promise = DeliveryService.getSupplyComId();
		promise.then(function(data) {
			if(data.comName!=null){
				$scope.supplyComId=data.comName;
				$scope.shipper=data.comName;
				$scope.receiver="中航能科"
			}else{
				$scope.shipper="中航能科";
				if($scope.delivery!=null){
					$scope.receiver=$scope.delivery.receiver;
				}
			}
		}, function(data) {
			// 调用承诺接口reject();
			$(".modal-backdrop").remove();
			handle.unblockUI();
			toastr.error("发货失败！请联系管理员");
			console.log(data);
		});
		
	}
	
	
	$scope.$watch('$viewContentLoaded', function() {  
		var e = $("#form_sample_2"),
        r = $(".alert-danger", e),
        i = $(".alert-success", e);
        e.validate({
            errorElement: "span",
            errorClass: "help-block help-block-error",
            focusInvalid: !1,
            ignore: "",
            messages: {
            	batchNum:{required:"批次号不能为空！"},
            	manufactureDate:{required:"生产日期不能为空！"},
            	deliverCount:{required:"发货数量不能为小于0的正整数！",digits:"只能是正整数！"}
            },
            rules: {
            	batchNum:{
            		required:true,
            	},
            	manufactureDate:{
            		required:true,
            	},
                deliverCount:{
                	required:true,
                	digits:true,
                	deliverNumCheck:true/*,
                	deliverNumCheck1:true*/
                },
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
	
	
	var deliveryCountCheck=function validateOneElement(index) {
		 //验证id="form1"的表单中id="elementId"的表单元素
		 var deliveryCountFlag=$("#form_sample_3").validate().element($("#deliverCount"+index+""))
		 return deliveryCountFlag;
		}
	
	/*jQuery.validator.addMethod("deliverNumCheck", function (value, element) {
		if(element.dataset.ordercount!=null&&$.trim(element.dataset.ordercount)!=''){
			return this.optional(element) || Number(element.dataset.ordercount)-value >= 0;	
		}else{
			return true;
		}
	    
	}, "发货数量不能超过订单数量");*/
	
	/**
	 * 保存销售订单物料信息
	 */
	/*$scope.saveOrderMateriel = function(deliveryMateriel,index) {
		delete deliveryMateriel.materiel;
		delete deliveryMateriel.supplyMateriel;
		delete deliveryMateriel.supply;
		if($scope.delivery==null||$.trim($scope.delivery.serialNum)==""){
			toastr.error("请先保存发货信息！");	
			return;
		}
		if($scope.inputDeliveryInfo==true){
			toastr.error("请先保存发货信息！");	
			return;
		}
		var batchNumFlag=batchNumCheck(index);
		if(!batchNumFlag){return false;}
		var attachFile=$("#batchNumReal"+index).text();
		
		var deliveryCountFlag=deliveryCountCheck(index);
		if(!deliveryCountFlag){return false;}
		
		deliveryMateriel.deliverSerial=$scope.delivery.serialNum;
		deliveryMateriel.attachFile=attachFile;
		
		var promise = DeliveryService.saveDeliveryMateriel(deliveryMateriel);
		promise.then(function(data) {
			if (!handle.isNull(data)) {
				$(".modal-backdrop").remove();
				toastr.success("保存成功");
				handle.unblockUI();
				$scope.deliveryMaterielE[index] = data;
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
	};*/
	
	$scope.editAllOrderMateriel = function() {//编辑发货物料
		for(var i=0;i < $scope.deliveryMaterielE.length;i++){
			$scope["orderMaterielInput"+i] = false;
			$scope["orderMaterielShow"+i] = false;
		}
		$scope.saveMateriel=false;
		$scope.editMateriel=false;
	}
	$scope.saveAllOrderMateriel = function() {
		/*delete deliveryMateriel.materiel;
		delete deliveryMateriel.supplyMateriel;
		delete deliveryMateriel.supply;*/
		if($scope.delivery==null||$.trim($scope.delivery.serialNum)==""){
			toastr.error("请先保存发货信息！");	
			return;
		}
		if($scope.inputDeliveryInfo==true){
			toastr.error("请先保存发货信息！");	
			return;
		}
	/*	var params = {};
		params.deliveryMateriels = [];
		var param;
		for(var i=0;i < $scope.deliveryMaterielE.length;i++){
			param = {};
			var deliveryMateriel=$scope.deliveryMaterielE[i];
			var attachFile=$("#batchNumReal"+i).text();
			param.deliverSerial=$scope.delivery.serialNum;
			param.attachFile = attachFile;
			param.manufactureDate=deliveryMateriel.manufactureDate;
			param.deliverCount=deliveryMateriel.deliverCoun;
			param.remark=deliveryMateriel.remark;
			param.deliverSerial=deliveryMateriel.deliverSerial;
			param.orderMaterielSerial=deliveryMateriel.orderMaterielSerialNum;//传整个表单数据  
			param.supplyMaterielSerial=deliveryMateriel.supplyMaterielSerial;
			params.deliveryMateriels.push(param);
		}*/
		var totalCount=0;
		for (var i=0;i < $scope.deliveryMaterielE.length;i++){
			var deliveryMateriel=$scope.deliveryMaterielE[i];
			if(isNull(deliveryMateriel.deliverCount)){
				toastr.error("发货数量不能为空！");	
				return;
			}else if(!isNaN(deliveryMateriel.deliverCount)){
				if(Number(deliveryMateriel.amount-deliveryMateriel.deliveredCount)<Number(deliveryMateriel.currentCount)){//未发数量小于库存数
					if(Number(deliveryMateriel.amount-deliveryMateriel.deliveredCount)<Number(deliveryMateriel.deliverCount)){
						toastr.error("发货数量不能大于未发数量！");	
						return;
					}
				}else{//未发数量大于等于库存数
					if(Number(deliveryMateriel.currentCount)<Number(deliveryMateriel.deliverCount)&&$scope.oprateType=='forSaleOrder'){//仅在平台发货时考虑库存数量,供应商发货时不需考虑
						toastr.error("发货数量不能大于库存数量！");	
						return;
					}
				}
				totalCount+=Number(deliveryMateriel.deliverCount);
			}
			deliveryMateriel.deliverSerial=$scope.delivery.serialNum;
			var attachFile=$("#batchNumReal"+i).text();
			deliveryMateriel.attachFile=attachFile;
			/*deliveryMateriel.manufactureDate:deliveryMateriel.manufactureDate,
			deliveryMaterieldeliverCount:deliveryMateriel.deliverCount,
			deliveryMaterielremark:deliveryMateriel.remark,
			deliveryMaterieldeliverSerial:deliveryMateriel.deliverSerial,
			deliveryMaterielorderMaterielSerial:deliveryMateriel.orderMaterielSerialNum,//传整个表单数据  
			deliveryMateriel.supplyMaterielSerial:deliveryMateriel.supplyMaterielSerial,*/
			
		}
	//判断当前发货单发货总数
		if(totalCount==0){
			toastr.error("发货总数量不能为0,当前发货单暂时不能发货！");	
			return;
		}
		handle.blockUI();
		var promise = DeliveryService.saveAllDeliveryMateriel($scope.deliveryMaterielE);
		promise.then(function(data) {
			if (!handle.isNull(data)) {
				handle.unblockUI();
				if(data.data.flag){//存在不满足条件的发货
					if(data.data.isDel){
						toastr.warning("当前发货单已发货完毕请删除当前发货单!");
					}else{
						$scope.deliveryMaterielE=data.data.deliveryMateriels;
						toastr.warning("存在发货数量超过未发数量的物料,请重新编辑!");}
				}else{
					$(".modal-backdrop").remove();
					toastr.success("保存成功");
					handle.unblockUI();
				/*	var array=new Array();
					for (var i=0;i < data.data.deliveryMateriels.length;i++){
						if(data.data.deliveryMateriels[i].deliverCount!=0){
							delete   data.data.deliveryMateriels[i];
							array.push(data.data.deliveryMateriels[i]);
						}
					}
					$scope.deliveryMaterielE=array; 
					$scope.materielCount=array.length;*/
					$scope.deliveryMaterielE=data.data.deliveryMateriels;
				$scope.editMateriel=true;
				$scope.saveMateriel=true;
					for(var i=0;i<$scope.deliveryMaterielE.length;i++){
						$scope["orderMaterielInput"+i] = true;
						$scope["orderMaterielShow"+i] = true;
						
					}
					
					$(".alert-danger").hide();
				}
			
				
				/*$(".modal-backdrop").remove();
				toastr.success("保存成功");
				handle.unblockUI();
			var array=new Array();
				for (var i=0;i < data.data.deliveryMateriels.length;i++){
					if(data.data.deliveryMateriels[i].deliverCount!=0){
						delete   data.data.deliveryMateriels[i];
						array.push(data.data.deliveryMateriels[i]);
					}
				}
				$scope.deliveryMaterielE=array; 
				$scope.materielCount=array.length;*/
		/*	$scope.editMateriel=true;
			$scope.saveMateriel=true;
				for(var i=0;i<$scope.deliveryMaterielE.length;i++){
					$scope["orderMaterielInput"+i] = true;
					$scope["orderMaterielShow"+i] = true;
					
				}
				
				$(".alert-danger").hide();*/
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
    $scope.cancelOrderMateriel=function (deliveryMateriel,index) {
    	// .show_materiels = false;
    	$scope["orderMaterielInput"+index] = true;
		$scope["orderMaterielShow"+index] = true;
    };
    
    $scope.changeTakeDeliveryMode = function(deliverType){
		if(deliverType=='贸易发货'){
				$scope.otherMode = false;
		}else{
			    $scope.otherMode = true;
			    $scope.delivery.orderSerial="";
			    $scope.orderSerial="";
		}
		$scope.deliveryMaterielE= null;
		$scope.materielCount=null;
		$scope.orderMaterielInput=true;
	}
    $scope.editOrderMaterielOne=function(_deliveryMateriel,index) {//点击修改物料按钮(行内单条)
    	$scope["orderMaterielInput"+index] = false;
		$scope["orderMaterielShow"+index] = false
		$scope.saveMateriel=false;
		$scope.editMateriel=false;
    }
	
	/**
	 * 编辑销售订单物料信息
	 */
	$scope.editOrderMateriel = function() {
		
		//判断基本信息是否保存，未保存先保存基本信息
		if($scope.isBasicInfoSaved!='1'){
			toastr.error("请先保存基本信息！");	
			return;
		}
		if($('#form_sample_2').valid()){
		handle.blockUI();
		var params = {};
		params= [];
		var param
		for(var i=0;i < $scope.deliveryMaterielE.length;i++){
			param = {};
			param.deliverSerial=$scope.delivery.serialNum;
			param.orderMaterielSerial = $scope.deliveryMaterielE[i].orderMaterielSerial;
			param.supplyMaterielSerial = $scope.deliveryMaterielE[i].supplyMaterielSerial;
			param.attachFile =$("#batchNumReal"+i).text();
			param.manufactureDate = $scope.deliveryMaterielE[i].manufactureDate;
			param.deliverCount = $scope.deliveryMaterielE[i].deliverCount;
			param.remark = $scope.deliveryMaterielE[i].remark;
			params.push(param);
		}
		
		var promise = DeliveryService.editDeliveryMateriel(params);
		promise.then(function(data) {
			if (!handle.isNull(data)) {
				$(".modal-backdrop").remove();
				toastr.success("修改成功");
				handle.unblockUI();
				$scope.deliveryMaterielE= data;
				$scope.orderMaterielInput = true;
				$scope.orderMaterielShow = true;
				$scope.otherMode = false;
				$(".alert-danger").hide();
			} else {
				$(".modal-backdrop").remove();
				handle.unblockUI();
				toastr.error("修改失败！请联系管理员");
				console.log(data);
			}
			
		}, function(data) {
			// 调用承诺接口reject();
			$(".modal-backdrop").remove();
			handle.unblockUI();
			toastr.error("修改失败！请联系管理员");
			console.log(data);
		});
		}
	};
	
	//保存基本信息
	$scope.saveDeliveryInfo=function(status){
		if($('#form_sample_deliverInfo').valid()){
			if($scope.showSXf =='1'){
				if(isNull($("select[name='warehouseAddress1']").val())){
					toastr.error('发货地址未选择！');
	    			return;
				}
			}
			if($scope.showSXs=='1'){
				if(isNull($("select[name='takeDeliveryWarehouseAddress1']").val())){
					toastr.error('收货地址未选择！');
	    			return;
				}
			}
			
			/*$scope.deliveryTransport={};
			$scope.takeDelivery={};*/
			 $rootScope.judgeIsExist("deliver",$scope.delivery.deliverNum, $scope.delivery.serialNum,function(result){
	    			var 	isExist = result;
	    		debugger;
	    		if(isExist){
	    			toastr.error('发货单号重复！');
	    			return;
	    		}else{
	    			handle.blockUI();
	    			if($scope.showSXf!=1){
	    				$scope.warehouseAddress=$("input[name='warehouseAddress']").val();
	    			}else if($scope.showSXf==1){
	    				$scope.warehouseAddress=$("select[name='warehouseAddress1']").val();
	    			}
	    			if($scope.showSXs!=1){
	    				$scope.takeDeliveryWarehouseAddress=$("input[name='takeDeliveryWarehouseAddress']").val();
	    			}else if($scope.showSXs==1){
	    				$scope.takeDeliveryWarehouseAddress=$("select[name='takeDeliveryWarehouseAddress1']").val();
	    			}
	    			var promise = DeliveryService.saveBasicInfo($scope,"deliveryInfo");
	    			promise.then(function(data) {
	    				if (!handle.isNull(data)) {
	    					if(data.flag){
	    						handle.unblockUI();
	    						if(data.isDel){
	    							toastr.warning("当前发货单已发货完毕请删除当前发货单!");
	    						}else{
	    							$scope.deliveryMaterielE=data.deliveryMateriels;
	    							toastr.warning("请重新编辑发货物料数量后再确认发货!");}
	    						return;
	    					}else{
	    						$scope.showSXf='0';
								$scope.showSXs='0';
	    						$(".modal-backdrop").remove();
		    					toastr.success("保存成功");
		    					handle.unblockUI();
		    					debugger;
		    					$scope.delivery= data;
		    					$scope.delivery.serialNum=data.serialNum;
		    					$scope.deliver={};
		    	  		    	$scope.deliver.deliverer=$scope.delivery.deliverer;
		    	  		    	$scope.deliver.remark =$scope.delivery.remark;  
		    	  		    	$scope.deliver.maker  =$scope.delivery.maker;
		    	  		    	$scope.deliver.approvalDate =$scope.delivery.approvalDate;  
		    	  		    	$scope.deliver.deliverDate  =$scope.delivery.deliverDate;
		    	  		    	$scope.deliver.packageType=$scope.delivery.packageType;
		    	  		    	$scope.deliver.takeDeliverer=$scope.delivery.takeDeliverer;
		    	  		    	$scope.deliver.takeTransportRemark=$scope.delivery.takeTransportRemark;
		    	  		      $scope.takeDeliveryWarehouseAddress=$scope.delivery.takeAddress;
		    					if(status!=undefined){
		    						var promise = DeliveryService.goDelivery($scope.delivery.serialNum);
		    						promise.then(function(data) {
		    								$(".modal-backdrop").remove();
		    								toastr.success("确认发货成功");
		    								if(status!=1){
		    									$scope.delivery.status=0;
		    								}else{
		    									$scope.delivery.status=1;
		    								}
		    								$scope.confirmDeliverybtn=false;
		    								handle.unblockUI();
		    								$state.go('delivery',{},{reload:true});
		    								
		    						}, function(data) {
		    							// 调用承诺接口reject();
		    							$(".modal-backdrop").remove();
		    							handle.unblockUI();
		    							toastr.error("发货失败！请联系管理员");
		    							console.log(data);
		    						});
		    					}
		    					getSupplyComId();
		    					$scope.span = true;
		    					$scope.inputDeliveryInfo = false;
		    					$(".alert-danger").hide();
	    					}
	    					
	    				
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
	    		}
	    		
	    		});
	
		}
	}

	//保存收货信息
	$scope.saveTakeDeliveryInfo=function(){//saveMasterielInfo()
		/*if($scope.delivery.serialNum==null||$scope.delivery.serialNum=='') {// 订单信息为空的处理
	    		toastr.error('请先保存发货信息！');return
 		}
		if($scope.inputDeliveryInfo==true){
			toastr.error('请先保存发货信息！');return
		}*/
		if($('#form_sample_takeDeliveryInfo').valid()){
			/*$scope.deliveryTransport={};*/
		var promise = DeliveryService.saveBasicInfo($scope,"takeDelivery");
		promise.then(function(data) {
			if (!handle.isNull(data)) {
				$(".modal-backdrop").remove();
				toastr.success("保存成功");
				handle.unblockUI();
				$scope.delivery= data;
				getSupplyComId();
				$scope.span = true;
				$scope.spancontrol = true;
				$scope.inputTakeDeliveryInfo = false;
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
		}
	}
	//保存物料信息
	$scope.saveMasterielInfo=function(){//saveMasterielInfo()
		if($scope.delivery.serialNum==null||$scope.delivery.serialNum=='') {// 订单信息为空的处理
	    		toastr.error('请先保存发货信息！');return
 		}
		if($('#form_sample_1').valid()){
			$scope.deliveryTransport={};
		var promise = DeliveryService.saveBasicInfo($scope,"materiel");
		promise.then(function(data) {
			if (!handle.isNull(data)) {
				$(".modal-backdrop").remove();
				toastr.success("保存成功");
				handle.unblockUI();
				$scope.delivery= data;
				getSupplyComId();
				$scope.span = true;
				$scope.input = false;
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
		}
	}
	//编辑基本信息
	$scope. editDeliveryInfo=function(){
		var data=$scope.delivery;
		$scope.inputDeliveryInfo=true;
		$scope.span=false;
	}
	$scope. editTakeDeliveryInfo=function(){
		var data=$scope.delivery;
		$scope.inputTakeDeliveryInfo=true;
		$scope.span=false;
		$scope.spancontrol=false;
	}
	
	//调整申请
	$scope.apApplyAngain=function(){
		//判断基本信息是否保存，未保存先保存基本信息
		if($scope.isBasicInfoSaved!='1'){
			toastr.error("请先保存基本信息！");	
			return;
		}
		
		if($('#form_sample_1').valid()){
		var promise = DeliveryService.apApplyAngain($scope);
		promise.then(function(data) {
			if (!handle.isNull(data)) {
				$(".modal-backdrop").remove();
				toastr.success("保存成功");
				
				$state.go('delivery',{tabHref:1});//返回到待办列表
				$("#dbTable").DataTable().ajax.reload();
				showToastr('toast-bottom-right', 'success', data);
				/*handle.unblockUI();
				$scope.delivery= data;
				$scope.isBasicInfoSaved='1';
				$scope.span = true;
				$scope.input = false;
				$(".alert-danger").hide();*/
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
	}
	
	
	//返回按钮
	$scope.goBack=function(){
		if($stateParams.oprateType == "forSaleOrder"){
			$state.go('saleOrder');
		}else if($stateParams.oprateType == "forSupplyOrder"){
			$state.go('supplyOrder');
		}else if($state.current.name=="editDeliveryPage"){
			$state.go('saleOrder');
		}else{
			$state.go('delivery');
		}
		
	}
	
	//打印
	$scope.print=function(){
		window.print();  
	}
	
	//查询仓库集合
	$scope.getWarehouseList  = function() {
		DeliveryService.getWarehouseList().then(
      		     function(data){
      		    	$scope.warehouseList=data;
      		     },
      		     function(error){
      		         toastr.error('连接服务器出错,请登录重试！');
      		     }
      		 );
    	
    }; 
    
    
	//初始化toastr开始
	toastr.options = {
			"closeButton" : true,
			"debug" : true,
			"positionClass" : "toast-top-center",
			"onclick" : null,
			"showDuration" : "1000",
			"hideDuration" : "1000",
			"timeOut" : "5000",
			"extendedTimeOut" : "1000",
			"showEasing" : "swing",
			"hideEasing" : "linear",
			"showMethod" : "fadeIn",
			"hideMethod" : "fadeOut"
		}
	
		
		 $scope.exportContract = function(){
	    	 handle.blockUI("正在导出数据，请稍后"); 
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
	 								ids = this.id;
	 							} else
	 								ids = ids + ','
	 										+ this.id;
	 						}
	 					}
	 				});
	    	 window.location.href=$rootScope.basePath+"/rest/delivery/exportDelivery"+"?serialNums="+ids;
	    	 handle.unblockUI(); 
	       }
		
	
	    var table;
		var loadMainTable = function() {
			var type="buy";
			var a = 0;
			App.getViewPort().width < App.getResponsiveBreakpoint("md") ? $(".page-header").hasClass("page-header-fixed-mobile")&& (a = $(".page-header").outerHeight(!0)): 
				$(".page-header").hasClass("navbar-fixed-top") ? a = $(".page-header").outerHeight(!0): $("body").hasClass("page-header-fixed")&& (a = 64);

				 table = $("#sample_2").DataTable(
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
							              ajax:"rest/delivery/findAllDeliveryList?noInit=1&&type="+type,//加载数据中user表数据
							              "aoColumns": [
							                            { mData: 'serialNum',
						                            	mRender : function(
																data,
																type,
																row,
																meta) {
						                            		return "<label class='mt-checkbox mt-checkbox-single mt-checkbox-outline'>" +
															"<input type='checkbox' name='serialNum' class='checkboxes' value='1' id='"+data+"'/>" +
															"<span></span></label>";
														}
						                            },
							                            { mData: 'inOutNum' },
							                            { mData: 'inOutType'},
							                            { mData: 'orderNum' },
							                            //{ mData: 'materielCount' },物料条目数
							                            { mData: 'receiver'},
							                            { mData: 'inOutPackageCount'},
							                            { mData: 'materielTotalCount' },//物料总数
							                            { mData: 'realCount' },//实际出库数量
							                            { mData: 'deliverDate'},
							                            { mData: 'transportType'},
							                            { mData: 'takeAddress' },
							                            { mData: 'inOutRemark' },
							                            { mData: 'status',

					                            	mRender:function(data){
							                            		if(data!=""&&data!=null){
							                            			if(data=='0'){
							                            				return '<span  class="label label-sm label-warning ng-scope">待发货</span>';
							                            			}else if(data=='PENDING'){
							                            				return '<span  class="label label-sm label-success ng-scope">审批中</span>';
							                            			}else if(data=='WAITING_FOR_APPROVAL'){
							                            				return '<span  class="label label-sm label-warning ng-scope">待审批</span>';					                            				
																	}else if(data=='3'){
																		return '<span  class="label label-sm label-warning ng-scope">待收货</span>';
																	}else if(data=='APPROVAL_FAILED'){
																		return '<span  class="label label-sm label-danger ng-scope">审批失败</span>';
																	}else if(data=='4'){
																		return '<span  class="label label-sm label-success ng-scope">已出库</span>';//已收货
																	}else if(data=='1'){
																		return  '<span  class="label label-sm label-warning ng-scope">待检验</span>';
																	}else if(data=='2'){
																		return  '<span  class="label label-sm label-warning ng-scope">待出库</span>';
																	}else if(data=='6'){
																		return '<span  class="label label-sm label-warning ng-scope">待清关</span>';
																	}else if(data=='7'){
																		return '<span  class="label label-sm label-warning ng-scope">待报关</span>';
																	}else if(data=='8'){
																		return '<span  class="label label-sm label-success ng-scope">完成发货</span>';
																	}else if(data=='9'){
																		return  '<span  class="label label-sm label-warning ng-scope">待入库</span>';
																	}else if(data=='10'){
																		return'<span  class="label label-sm label-warning ng-scope">待收货</span>';
																	}else{
																		return '';
																	}
							                            		}else{
							                            			return "";
							                            		}
							                            	}
							                            } , { mData: 'status' },//操作
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
							                            		/*return '<a data-toggle="modal" ng-click="jumpToGetDeliveryInfo(\''+row.serialNum+'\')" ">'+data+'</a>';*/
							                            		return '<a data-toggle="modal" ng-click="stockInView(\''+row.inOutSerial+'\')" ">'+data+'</a>';
							                            		
							                            	},
							                            	"createdCell": function (td, cellData, rowData, row, col) {
							                            		$compile(td)($scope);
							                            	}
							                            },
							                            {
							                            	'targets' : 3,
							                            	'className' : 'dt-body-center',
							                            	'render' : function(data,
							                            			type, row, meta) {
							                            		return '<a data-toggle="modal" ng-click="viewSaleOrder(\''+row.orderSerial+'\')" ">'+data+'</a>';
							                            	},
							                            	"createdCell": function (td, cellData, rowData, row, col) {
							                            		$compile(td)($scope);
							                            	}
							                            },
							                            {
							    							'targets' : 7,
							    							'render' : function(data,
							    									type, row, meta) {
							    									if(!isNull(data)){
							    										return data;
							    									}
							  	  								return 0;
							  	
							    							}
							    						},
							                            {
							    							'targets' : 13,
							    							'render' : function(data,
							    									type, row, meta) {
							    									
							  	  								return '';
							  	
							    							}
							    						}
							                            
							                            ]}).on('order.dt',
							                            		function() {
							                            	console.log('排序');
							                            })
							                            
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
		}
		
		  $scope.viewSaleOrder = function(serialNum){
		    	$state.go("viewSaleOrder",{serialNum:serialNum});
		    }
		  
		  
		  /**
			 * 出库库计划中做入库操作
			 */
			$scope.deliveryStockOut = function(){
				var id_count = $('#sample_2 input[name="serialNum"]:checked').length;
				if(id_count==0){
					toastr.warning("请选择您要出库的记录");
				}else if(id_count>1){
					toastr.warning("只能选择一条数据进行收货");
				}else{
					var row = table.row(".active").data();
					if(table.row('.active').data().status != 2){
						showToastr('toast-top-center', 'warning', '该出库单已完成出库！');
					}else{
						//查找入库记录单流水
						var promise = DeliveryService
						.findStockOutSerialNum(row.serialNum);
						promise.then(function(data) {
							$state.go("stockOut",{serialNum:data.data.serialNum});
						}, function(data) {
							toastr.error("操作失败！请联系管理员");
						});
						
					}
				}
			}
			
		//销售订单列表
        var table1;
	    var loadMainTable1 = function() {
	            a = 0;
	            App.getViewPort().width < App.getResponsiveBreakpoint("md") ? $(".page-header").hasClass("page-header-fixed-mobile") && (a = $(".page-header").outerHeight(!0)) : $(".page-header").hasClass("navbar-fixed-top") ? a = $(".page-header").outerHeight(!0) : $("body").hasClass("page-header-fixed") && (a = 64);
	            table1 = $("#sample_21")
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
//	                serverSide: true,
	                ajax:"rest/order/findOrderList?type=sale&selectFor=delivery",//加载数据中
	                "aoColumns": [
	                              { mData: 'serialNum' },
                              { mData: 'orderNum' },
                              { mData: 'buyName' },
                              { mData: 'materielCount' },
                              { mData: 'orderAmount' },
                              /*{ mData: 'deliveryMode' },*/
                              { mData: 'orderType' },
                              /*{ mData: 'saleApplySerial' },*/
                              { mData: 'orderSerial' },
                              { mData: 'orderDate' }

	                        ],
	               'aoColumnDefs' : [ {
								'targets' : 0,
								'searchable' : false,
								'orderable' : false,
								'render' : function(data,
										type, full, meta) {
									return '<input type="radio" name="serialNum" value="'
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
								'targets' : 2,
								'searchable' : false,
								'orderable' : false,
								'render' : function(data,
										type, full, meta) {
									if(isNull(data)){
										return '中航能科（上海）能源科技有限公司'
									}else{
										return data;
									}
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
	        
	        /***选择物料列表初始化START***/
	        var m_table;
		     var selectParentMateriel = function() {
		              a = 0;
		              App.getViewPort().width < App.getResponsiveBreakpoint("md") ? $(".page-header").hasClass("page-header-fixed-mobile") && (a = $(".page-header").outerHeight(!0)) : $(".page-header").hasClass("navbar-fixed-top") ? a = $(".page-header").outerHeight(!0) : $("body").hasClass("page-header-fixed") && (a = 64);
		              m_table = $("#sample_22").DataTable({
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
//		                  serverSide: true,
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
			  	  							return '<label class="mt-radio mt-radio-outline">'+
		                                    '<input type="radio" data-radio=true   ng-click="getCheckedIds(\''+data+'\','+meta.row+')" name="serialNum"  class="checkboxes" id="'+data+'" value="'+data+'" />'+
		                                    '<span></span></label>';
			
			  								}else{
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
    		$("#basicMaterielInfoI").modal("show");
//    		$("#sample_22_processing").remove();
		}
    	
    	
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
    	
    	
    	//********附件  start****************//
		
		
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
		
		// 待办流程
		var dbTable;	
		$scope.toDaiban = function() {
			$('#accountPayableTab a[href="#daiban"]').tab('show');
			
			$("#buttons").hide();
			// 构建datatables开始***************************************
			if(dbTable == undefined){
				dbTable = showDbTable();
			}else $("#dbTable").DataTable().ajax.reload();
											
			// 构建datatables结束***************************************
			//dbTable.ajax.reload();
		};
		// 已办流程
		var ybTable;
		$scope.toYiban = function() {
			$('#accountPayableTab a[href="#yiban"]').tab('show');
			
			if(ybTable == undefined){
				ybTable = showYbTable();
			}else $("#ybTable").DataTable().ajax.reload();
			
			$("#buttons").hide();
		};
		
		/*alert($stateParams.tabHref)*/
		if($stateParams.tabHref == '1'){//首页待办列表传过来的参数
			
			$('#accountPayableTab a[href="#daiban"]').tab('show');
			if(dbTable == undefined){
				dbTable = showDbTable();
			}else $("#dbTable").DataTable().ajax.reload();
			
			$("#buttons").hide();
		}else if($stateParams.tabHref == '2'){//首页已办列表传过来的参数
			$('#accountPayableTab a[href="#yiban"]').tab('show');
			
			if(ybTable == undefined){
				ybTable = showYbTable();
			}else $("#ybTable").DataTable().ajax.reload();
			
			$("#buttons").hide();
		}else{//从菜单进入
			$('#accountPayableTab a[href="#apply"]').tab('show');
			$("#buttons").show();
		}
		
		function showDbTable(){
			
			var t = $("#dbTable")
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
										if(t.rows('.active').data().length != 1){
											showToastr('toast-top-center', 'warning', '请选择一条任务进行办理！')
										} else {
											if(t.row('.active').data().assign == ''){
												showToastr('toast-top-center', 'warning', '此任务您还没有签收，请【签收】任务后再处理任务！')
											}else{
												var taskId=t.row('.active').data().taskId;
												DeliveryService.getAuditInfos(taskId)
													.then(
															function(result) {													
																
																var comments = ""//添加评论
																for (var i=0;i<result.commentList.length;i++){
																	comments += "<tr><td>" + result.commentList[i].userName + "</td><td>" 
																	+ (result.commentList[i].position==null?'':result.commentList[i].position) + "</td><td>"
																	+ timeStamp2String(result.commentList[i].time) + "</td><td>" + result.commentList[i].content + "</td></tr>";														
																}
																
																if(result.actionType == 'audit'){//审批流程
																	$state.go('auditDelivery',{serialNum:result.deliveryVO.serialNum,taskId:taskId,comments:comments});
																}else{
																	$state.go('editAuditDelivery',{serialNumEdit:result.deliveryVO.serialNum,taskId:taskId,comments:comments});
																}
																
																
																
																
																
															},
															function(errResponse) {
																toastr.warning("申请失败！");
																console
																		.error('Error while apply ap');
															}
			
													);
											
											}
											
											
											
										}
										
										
									}
								},
								{
									text : "签收",
									className : "btn default",
									action: function(e, dt, node, config) { 
										if(t.rows('.active').data().length != 1){
											
											toastr.warning('请选择一条任务进行签收！');return;									
										} else {
											
											if(t.row('.active').data().assign != ''){
												toastr.warning('该任务已签收！');return;
											}else
												claimTask(t.row('.active').data().taskId, 'dbTable');
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
						order : [ [6, "asc" ] ],// 默认排序列及排序方式

						bRetrieve : true,
						lengthMenu : [
								[ 5, 10, 15, 30, -1 ],
								[ 5, 10, 15, 30,
										"All" ] ],
						pageLength : 10,// 每页显示数量
						processing : true,// loading等待框

						ajax : ctx
								+ "/rest/processAction/todoTask/" + 'accountDelivery',// 加载待办列表数据

						"aoColumns" : [
						              { mData: 'taskId',
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
		                    		return '<input type="checkbox" name="id[]" value="'+ $('<div/>').text(data).html()+ '">';
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


					
					return t;
		}
		
		
		function showYbTable(){
			var ybTable = $("#ybTable").DataTable(
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
								+ "/rest/processAction/endTask/"  + 'accountDelivery',// 加载已办列表数据

						"aoColumns" : [
//								{ mData: 'taskId'},
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
									mData : 'currentPointUserName',
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
									mData : 'deleteReason'
								},
								{
									mData : 'version'
								},
								{
									mData : 'revoke',
									mRender : function(data,type,row,meta) {
										return "<a href='javascript:void(0);' onclick=\"revoke('"+row.taskId+"','"+row.processInstanceId+"','ybTable')\">撤销</a>";
									}
								}
								]

					})
		 return ybTable;
		}
		
		//审批通过
		$scope.apPass = function() {
			
		    var mydata={"serialNum":$("#serialNum").val(),"content":$("#content").val(),
					"isPass":true, "taskId":$("#taskId").val()};
		    var _url = ctx + "rest/delivery/complete";
		    doAudit(_url, mydata);
		    $state.go('delivery',{tabHref:1});//返回到待办列表
		};
		
		//审批不通过
		$scope.apUnPass = function() {
			var mydata={"serialNum":$("#serialNum").val(),"content":$("#content").val(),
					"isPass":false, "taskId":$("#taskId").val()};
			var _url = ctx + "rest/delivery/complete";
			doAudit(_url, mydata);
			$state.go('delivery',{tabHref:1});//返回到待办列表
		};
		
		//办结待办流程
		function doAudit(_url, mydata){
	        $.ajax( {
		        url : _url,
		        dataType:"text",
		        type: 'POST',
		        data : mydata,
		        success : function(data) {
		        	$("#dbTable").DataTable().ajax.reload();
		        	showToastr('toast-bottom-right', 'success', data);
		        }
		     });
		}
		
		
	        // 确认选择开始***************************************
	        var ids = '';
			 $scope.confirmSelect = function() {
				 
				 // Iterate over all checkboxes in the table
				 table1.$('input[type="radio"]').each(
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
					 toastr.warning('请选择一个订单！');return;
				 }
				 /*alert(ids);*/
				/* $scope.orderNum='123';*///物料编号
				 $scope.getSaleOrderInfo(ids);
				 /*loadMainTable2();*/
				 ids = '';
				 $('#basicMaterielInfo').modal('hide');// 删除成功后关闭模态框
				 $(".modal-backdrop").remove();
				 
				
			 };
			 
			var length=0;
			 //获取订单物料的信息
	        $scope.getSaleOrderInfo  = function(serialNum) {
	        	DeliveryService.getSaleOrderInfo(serialNum).then(
	          		     function(data){
	          		    	 
	          		    	$scope.saleOrder=data.orderInfo;
	          		    	if(isNull(data.orderInfo.buyName)){
	          		    		$scope.delivery.receiver = '中航能科（上海）能源科技有限公司'
	          		    	}else{
	          		    		$scope.delivery.receiver=data.orderInfo.buyName;//收货方默认销售订单的采购方
	          		    	}
	          		    	 initWarehouses('pts',data.orderInfo.buyComId,"in");
	          		    	if($stateParams.oprateType=='forSupplyOrder'){
	          		    		 initCompanyaddresses("pt",'sh');//先加载收货地址列表
	          		    	}else{
	          		    		 initCompanyaddresses(data.orderInfo.buyComId,'sh');//先加载收货地址列表
	          		    	}
	          		    	
	          		    	$scope.delivery.maker=data.currenLoginName;//制单人默认当前用户
	          		    	if(data.clauseDelivery!=null){
	          		    		$scope.deliver.packageType=data.clauseDelivery.deliveryMode;
	          		    	//	$scope.warehouseAddress=data.clauseDelivery.warehouseAddress;
	          		    	}else{
	          		    		$scope.deliver.packageType='';
	          		    		//$scope.warehouseAddress='';
	          		    	}
	          		    	/*$scope.delivery.deliverNum=data.deliverNum;//随机产生的发货单号
*/	          		    	if($scope.delivery!=null){
	          		    	/*	if($scope.delivery.orderNum!=""&&$scope.delivery.orderNum!=null){*/
		          		    		$scope.delivery.orderNum1=$scope.saleOrder.orderNum;
		          		    /*	}	*/
	          		    	}
	          		    	
	          		    	
	          		    	var orderSerial=data.orderInfo.serialNum;
	          		    	$scope.orderSerial=data.orderInfo.serialNum;
	          		    	$scope.deliveryMaterielE=data.orderMateriel;
	          		    	
	          		    	if(data.orderMateriel.length>0){
	          		    		$scope.orderMaterielInput=false;
	          		    	}
	          		    	
	          		    	$scope.materielCount=data.orderMateriel.length;
	          		    	var totalOrderCount=0,totalDeliveryedCount=0,totalUnDeliveryCount=0;
	          		    	var array=new Array();
	          		    	for(var i=0;i< $scope.deliveryMaterielE.length;i++){
	          		    		$scope.deliveryMaterielE[i].serialNum=null;
	          		    		if($scope.deliveryMaterielE[i].amount-$scope.deliveryMaterielE[i].deliveredCount!=0){//未发数量不为0,统计
	          		    			totalOrderCount+=Number($scope.deliveryMaterielE[i].amount);
		          		    		totalDeliveryedCount+=Number($scope.deliveryMaterielE[i].deliveredCount);
		          		    		totalUnDeliveryCount+=Number($scope.deliveryMaterielE[i].amount-$scope.deliveryMaterielE[i].deliveredCount);
	          		    			array.push($scope.deliveryMaterielE[i]);
	          		    		}
	          		    	}
	          		    	$scope.deliveryMaterielE=array;
	          		    	$scope.totalOrderCount=totalOrderCount;
	          		    	$scope.totalDeliveryCount=totalOrderCount-totalDeliveryedCount;
	          		    	$scope.totalUnDeliveryCount=totalUnDeliveryCount;
	          		    	
	          		    	//重新加载发货仓库和收货仓库
	          		    	
	          		     },
	          		     function(error){
	          		         $scope.error = error;
	          		     }
	          		 );
	        	
	        }; 
	        $scope.calcTotalDeliveryCount = function(){
	  			if(!isNull($scope.deliveryMaterielE)){
	  				$scope.totalDeliveryCount = 0;
	  				for(var i in $scope.deliveryMaterielE){
	  					$scope.totalDeliveryCount += handle.formatNumber($scope.deliveryMaterielE[i].deliverCount);
	  					
	  				}
	  			}
	  		}
	        /**
	    	 * 选择物料并展示在列表
	    	 */
	    	$scope.confirmSelectMateriel= function(){
	    		var ids = '';
	    		// Iterate over all checkboxes in the table
	    		m_table.$('input[type="checkbox"]').each(function() {
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
	    		if (ids == '') {									
	    			toastr.warning("请选择物料！");
	    		} else {
	    		DeliveryService.batchGetMaterielInfo(ids).then(
	          		     function(data){
		          		    	$scope.deliveryMaterielE=data.materielList;
		          		    	
		          		    	if(data.materielList.length>0){
		          		    		$scope.orderMaterielInput=false;	
		          		    	}
		          		    	$scope.materielCount=data.materielList.length;
		          		    	length=data.materielList.length;
		          		    	$("#basicMaterielInfoI").modal("hide");
		          		     },
		          		     function(error){
		          		         $scope.error = error;
		          		     }
		          		 );
	    		}
	    	}
	        
	      //查询编辑时的发货信息
	        $scope.getDeliveryEditInfo  = function(serialNumEdit, ids, comments) {
	        	DeliveryService.getDeliveryInfo(serialNumEdit).then(
	          		     function(data){
	          		    	$scope.delivery=data.delivery;
	          		    	$scope.warehouseAddress=data.delivery.deliveryWarehouseName
	          		    	$scope.saleOrder={};
	          		    	$scope.saleOrder.orderNum=data.delivery.orderNum;
	          		    	$scope.deliver={};
	          		    	$scope.flag=true;
	          		    	/*$scope.delivery.orderNum1=data.delivery.orderNum;*/
	          		    	 initWarehouses('pts',data.delivery.buyComId,"in");
		          		    	if($stateParams.oprateType=='forSupplyOrder'){
		          		    		 initCompanyaddresses("pt",'sh');//先加载收货地址列表
		          		    	}else{
		          		    		 initCompanyaddresses(data.delivery.buyComId,'sh');//先加载收货地址列表
		          		    	}
	          		    	$scope.deliveryTransport={};
	          		    	$scope.takeDelivery={};
	          		    	$scope.deliver.deliverer=$scope.delivery.deliverer;
	          		    	$scope.deliver.remark =$scope.delivery.remark;  
	          		    	$scope.deliver.maker  =$scope.delivery.maker;
	          		    	$scope.deliver.approvalDate =$scope.delivery.approvalDate;  
	          		    	$scope.deliver.deliverDate  =$scope.delivery.deliverDate;
	          		    	$scope.deliver.packageType=$scope.delivery.packageType;
	          		    	$scope.takeDeliveryWarehouseAddress=$scope.delivery.takeAddress;
	          		    	$scope.warehouseAddress=$scope.delivery.deliveryAddress;
	          		    	
	          		    	$scope.deliver.takeDeliverer=$scope.delivery.takeDeliverer;
	          		    	$scope.deliver.takeTransportRemark =$scope.delivery.takeTransportRemark;  
	          		    	
	          		    	$scope.deliveryTransport.transport=$scope.delivery.transport;
	          		    	$scope.deliveryTransport.shipNumber=$scope.delivery.shipNumber
	          		    	$scope.deliveryTransport.contact=$scope.delivery.transportContact;
	          		    	$scope.deliveryTransport.contactNum=$scope.delivery.transportContactNum;
	          		    	$scope.deliveryTransport.remark=$scope.delivery.transportRemark;
	          		    	$scope.takeDelivery.warehouseSerial=$scope.delivery.takeWarehouseSerial;
	          		    	$scope.takeDelivery.warehouseAddress=$scope.delivery.takeAddress;
	          		    	$scope.takeDelivery.takeDeliverDate=$scope.delivery.takeDeliverDate;
	          		    	$scope.takeDelivery.receiver=$scope.delivery.takeDeliveryReceiver;
	          		    	$scope.takeDelivery.contactNum=$scope.delivery.takeDeliveryContactNum;
	          		    	$scope.takeDelivery.remark=$scope.delivery.takeDeliveryRemark;
	          		    	// initWarehouses('pts',$scope.delivery.buyComId,"in");
	          		    	//	initCompanyaddressesOther($scope.delivery.buyComId,'sh');//先加载收货地址列表
	          		    	 //$scope.getSaleOrderInfo(serialNumEdit);
	          		    	if(data.delivery.deliverType=='其他发货'){
	          		    		$scope.otherMode=true;
	          		    	}else{
	          		    		$scope.otherMode=false;
	          		    	}
	          		    	if($scope.delivery.supplyComId!=null){
	          		    		$scope.supplyComId=$scope.delivery.supplyComId;
	          		    		$scope.shipper=$scope.delivery.shipper;
	          		    	}
	          		    	
	          		    	if(!isNull(data.deliverFile)){
	     	        			$scope.deliverFile = data.deliverFile;
	     	        			_deliverFileIndex = $scope.deliverFile.length;
	     	        		}
	          		  /*	var array=new Array();
	    				for (var i=0;i < data.data.deliveryMateriels.length;i++){
	    					if(data.data.deliveryMateriels[i].deliverCount!=0){
	    						delete   data.data.deliveryMateriels[i];
	    						array.push(data.data.deliveryMateriels[i]);
	    					}
	    				}
	    				$scope.deliveryMaterielE=array; 
	    				$scope.materielCount=array.length;*/
	          		    	$scope.deliveryMaterielE=data.deliveryMateriels;
	          		    	$scope.input=true;
	          		    	if(data.deliveryMateriels.length>0){
	          		    		$scope.orderMaterielInput=false;	
	          		    	}
	          		    	
	          		    	$scope.materielCount=data.deliveryMateriels.length;
	          		    	var totalOrderCount=0,totalDeliveryedCount=0,totalUnDeliveryCount=0;
	          		    	for(var i=0;i< data.deliveryMateriels.length;i++){
	          		    		totalOrderCount+=Number(data.deliveryMateriels[i].amount);
	          		    		totalDeliveryedCount+=Number(data.deliveryMateriels[i].deliveredCount);
	          		    		totalUnDeliveryCount+=Number($scope.deliveryMaterielE[i].amount-$scope.deliveryMaterielE[i].deliveredCount);
	          		    	}
	          		    	$scope.totalOrderCount=totalOrderCount;
	          		    	$scope.totalDeliveryCount=totalOrderCount-totalDeliveryedCount;
	          		    	$scope.totalUnDeliveryCount=totalUnDeliveryCount;
	          		    	length=data.deliveryMateriels.length;
	          		    	$("#serialNum").val(serialNumEdit);//赋值给隐藏input，通过和不通过时调用
	    					$("#taskId").val(ids);//赋值给隐藏input，通过和不通过时调用
	    					
	    					if(comments == ""){
	    						$("#comment_audit").html( "无评论");
	    					}else{ $("#comment_audit").html(comments);
	          		     }
	          		     },
	          		     function(error){
	          		         $scope.error = error;
	          		     }
	          		 );
	        };
			
	        var getTotalDeliveryCountEdit=function(length){
	        	
	        	var totalCount=parseInt(0);
	        	var check=/^[1-9]\d*|0$/;
	        	for(var i=0;i<length;i++){
	        		var count=parseInt($("#deliverCount"+i+"").val());
	        		if($.trim(count)!=""&&count!=null&&check.test(count)){
	        			totalCount=totalCount+count;
	        		}
	        	}
	        	$scope.totalDeliveryCount=totalCount;
	        }
	        
	        
	        $scope.getTotalDeliveryCount=function(){
	        	
	        	var totalCount=parseInt(0);
	        	var check=/^[1-9]\d*|0$/;
	        	for(var i=0;i<length;i++){
	        		var count=parseInt($("input[name='deliverCount"+i+"'").val());
	        		var count=parseInt($('#deliverCount'+i).val());
	        		if($.trim(count)!=""&&count!=null&&check.test(count)){
	        			totalCount=totalCount+count;
	        		}
	        	}
	        	$scope.totalDeliveryCount=totalCount;
	        }
	        
	        
	        //跳转到查看详情页面
			        $scope.jumpToGetDeliveryInfo  = function(serialNum) {
			        	$state.go('viewDelivery',{serialNum:serialNum});
			        }; 
			        
			//通过id查询发货详情
			        $scope.getDeliveryInfo  = function(serialNum, ids, comments) {
			        	DeliveryService.getDeliveryInfo(serialNum).then(
			          		     function(data){
			          		    	 debugger;
			          		    	$scope.deliveryDetail=data.delivery;
			          		    	if(data.hasOutData){//已出过库
			          		    		$scope.hasOutData=true;//显示运输信息及出入库信息
			          		    	}
			          		    	if(data.hasCheckData){//已出过库
			          		    		$scope.hasCheckData=true;//显示检验信息
			          		    	}
			          		    	if(data.hasInData){//已入过库
			          		    		$scope.hasInData=true;//
			          		    	}
			          		    	if(data.showTransport){//
			          		    		$scope.showTransport=true;//显示运输信息
			          		    	}
			          		    	$scope.delivery={};
			          		    	$scope.flag=false;
			          		    	$scope.delivery.serialNum=serialNum;
			          		    	if($scope.deliveryDetail.supplyComId!=null){
			          		    		$scope.supplyComId=$scope.deliveryDetail.supplyComId;
			          		    		$scope.shipper=$scope.deliveryDetail.shipper;
			          		    	}
			          		    	$scope.input=true;
			          		    	$scope.saveMateriel=true;
			          		    	var array=new Array();
				    				for (var i=0;i < data.deliveryMateriels.length;i++){
				    					if(data.deliveryMateriels[i].deliverCount!=0){
				    						/*delete   data.data.deliveryMateriels[i];*/
				    						array.push(data.deliveryMateriels[i]);
				    					}
				    				}
				    				
				    				if(!isNull(data.deliverFile)){
			     	        			$scope.deliverFile = data.deliverFile;
			     	        			_deliverFileIndex = $scope.deliverFile.length;
			     	        		}
				    				
				    				$scope.deliveryMaterielE=array; 
				    				$scope.materielCount=array.length;
			          		    	//$scope.deliveryMaterielE=data.deliveryMateriels;
			          		     var totalOrderCount=0,totalDeliveryCount=0,totalUnDeliveryCount=0,totalQualifiedCount=0,totalStockCount=0;
			          		    	for(var i=0;i<$scope.deliveryMaterielE.length;i++){
			          		    		if($scope.deliveryMaterielE[i].status=='0'){$scope.deliveryMaterielE[i].status='待发货'};
			          		    		if($scope.deliveryMaterielE[i].status=='1'){$scope.deliveryMaterielE[i].status='已发货'}
			          		    	totalOrderCount=totalOrderCount+Number($scope.deliveryMaterielE[i].amount);
			          		    	totalDeliveryCount=totalDeliveryCount+Number($scope.deliveryMaterielE[i].deliverCount);
			          		    	totalUnDeliveryCount=totalUnDeliveryCount+Number($scope.deliveryMaterielE[i].amount-$scope.deliveryMaterielE[i].deliveredCount);
			          		    	totalQualifiedCount=totalQualifiedCount+Number($scope.deliveryMaterielE[i].qualifiedCount);
			          		    	totalStockCount=totalStockCount+Number($scope.deliveryMaterielE[i].stockCount);
			          		    	}
			          		    	$scope.totalOrderCount=totalOrderCount;
			          		    	$scope.totalDeliveryCount=totalDeliveryCount;
			          		    	$scope.totalUnDeliveryCount=totalUnDeliveryCount;
			          		    	$scope.totalQualifiedCount=totalQualifiedCount;
			          		    	$scope.totalStockCount=totalStockCount;
			          		    	$("#serialNum").val(serialNum);//赋值给隐藏input，通过和不通过时调用
			    					$("#taskId").val(ids);//赋值给隐藏input，通过和不通过时调用
			    					
			    					if(comments == ""){
			    						$("#comment_audit").html( "无评论");
			    					}else{ $("#comment_audit").html(comments);
			          		     }
			    					
			    					$scope.materielCount=$scope.deliveryMaterielE.length;
			    					$scope.queryForPage();
			          		     },
			          		     function(error){
			          		         $scope.error = error;
			          		     }
			          		 );
			        };
			        
			        
			        //通过选择发货仓库改变地址（新增）
			        $scope.selectAddress=function(){
			        	var warehouseSerial=$scope.delivery.warehouseSerial;
			        	DeliveryService.selectAddress(warehouseSerial).then(
			          		     function(data){
			          		    	$scope.warehouseAddress=data.address;
			          		    	$scope.deliver.deliverer=data.admin;
			          		    	$scope.delivery.contactNum=data.tel;
			          		     },
			          		     function(error){
			          		         $scope.error = error;
			          		     }
			          		 );
			        }
			        
			        //通过选择发货仓库改变地址（编辑）
			        $scope.selectAddressEdit=function(){
			        	var warehouseSerial=$scope.delivery.warehouseSerial;
			        	DeliveryService.selectAddress(warehouseSerial).then(
			          		     function(data){
			          		    	$scope.delivery.deliveryAddress=data.address;
			          		    	
			          		     },
			          		     function(error){
			          		         $scope.error = error;
			          		     }
			          		 );
			        }
			        
			      //通过选择收货仓库改变地址（新增）
			        $scope.selectAddressTakeDelivery=function(){
			        	debugger;
			        	var warehouseSerial=$scope.takeDelivery.warehouseSerial;
			        	DeliveryService.selectAddress(warehouseSerial).then(
			          		     function(data){
			          		    	$scope.takeDeliveryWarehouseAddress=data.address;
			          		    	$scope.deliver.takeDeliverer=data.admin;
			          		    	$scope.delivery.takeContactNum=data.tel;
			          		     },
			          		     function(error){
			          		         $scope.error = error;
			          		     }
			          		 );
			        }
			        
			      //通过选择收货仓库改变地址（编辑）
			        $scope.selectAddressTakeDeliveryEdit=function(){
			        	var warehouseSerial=$scope.delivery.takeWarehouseSerial;
			        	DeliveryService.selectAddress(warehouseSerial).then(
			          		     function(data){
			          		    	$scope.delivery.takeAddress=data.address;
			          		     },
			          		     function(error){
			          		         $scope.error = error;
			          		     }
			          		 );
			        }
	        
		// 删除
        $scope.del = function() {
    		if(table.rows('.active').data().length == 0){
    			showToastr('toast-top-center', 'warning', '未勾选要删除数据！')
    		} else {
    			var ap = table.rows('.active').data();
    			var ids = '';
    			for(i=0;i<ap.length;i++){
    				if(ap[i].status != '0'){
    					showToastr('toast-top-center', 'warning', '所选数据已经发货，不能删除！');
    					return;
    				}
    				
    				if(ids == ''){
    					ids = ap[i].serialNum;
    				}else ids = ids +','+ ap[i].serialNum;
    				
    			}
    			
    			$('#delUsersModal').modal('show');// 打开确认删除模态框

    			$scope.confirmDel = function() {										
    				DeliveryService.deleteDeliveryS(ids).then(
    						function(data) {
    							$('#delUsersModal').modal('hide');// 删除成功后关闭模态框
    							$(".modal-backdrop").remove();
    							toastr.success("删除成功！");
    							$state.go('delivery',{},{reload:true}); // 重新加载datatables数据
    						},
    						function(errResponse) {
    							/*console.error('Error while deleting Users');*/
    						}

    				);
    			}
    		}								
    	};
			
			
		    
		  //流程申请
		    $scope.jumpToApplyPay  = function() {    	
		    	if(table.rows('.active').data().length != 1){
					showToastr('toast-top-center', 'warning', '请选择一条任务进行流程申请！')
				}else{
					var status = table.row('.active').data().status;
					if(status != '0'){
						showToastr('toast-top-center', 'warning', '该发货已发起流程审批，不能再次申请！')
					}else $state.go('applyDelivery',{serialNum:table.row('.active').data().serialNum});
				}     	
		    }; 
		    
		  //启动流程
			$scope.applyAp = function() {
				DeliveryService
						.applyAp($('#reason').val(), $stateParams.serialNum)
						.then(
								function(data) {
									toastr.success("申请成功！");
									$state.go('delivery',{},{reload:true});
								},
								function(errResponse) {
									toastr.warning("申请失败！");
									console
											.error('Error while apply ap');
								}

						);
			};
			
				
				
	        /**
	        * 下载EXCEL模板
	        */
	       $scope.downloadImportTemp = function(){
	    	   window.location.href=$rootScope.basePath+"/rest/contract/downloadImportTemp";
	       }
	       
	       
	       /**
	        * 上传EXCEL
	        */
	       $scope.uploadExcel = function(){
	    	    var file = document.querySelector('input[type=file]').files[0];
	    	    if(handle.isNull(file)){
	    	    	handle.toastr.warning("请选择Excel文件！");
	    	    }
	    	    console.log(file.name);
	    	    var type = file.name.substring(file.name.lastIndexOf("."));
	    	   if(type != ".xls"){
	    		   handle.toastr.warning("文件格式不正确，需要xls类型的Excel文档");
	    		   return;
	    	   }
	    	   	handle.blockUI("正在导入中，请不要进行其他操作"); 
	    	   	var promise = ContractService.uploadExcel();
       			promise.then(function(data){
       				handle.unblockUI(); 
       				if(data.data.data=="success"){
       					handle.toastr.success("导入成功");
       					$state.go('userContract',{},{reload:true});
       					$(".modal-backdrop").remove();
       				}else{
       					handle.toastr.error(data.data.data);
       				}
       				$('#import').modal('hide'); 
	            },function(data){
	               //调用承诺接口reject();
	            	handle.toastr.error("操作失败");
	            	$('#import').modal('hide'); 
	            });
	    	   
	       }
		
	       
	     //返回待办列表
	   	$scope.backDbList = function() {
	   		$state.go('delivery',{tabHref:1});//返回待办列表
	   	};
	   	$scope.confirmDelivery=function(serialNum){
	   		debugger;
	   		var promise = DeliveryService.goDelivery(serialNum);
			promise.then(function(data) {
					$(".modal-backdrop").remove();
					toastr.success("确认发货成功");
					$state.go('delivery',{},{reload:true});
					handle.unblockUI();
			}, function(data) {
				// 调用承诺接口reject();
				$(".modal-backdrop").remove();
				handle.unblockUI();
				toastr.error("发货失败！请联系管理员");
				console.log(data);
			});
	   		
	   	}
	   	
		//修改
		$scope.jumpToEdit = function() {		
			if(table.rows('.active').data().length != 1){
				showToastr('toast-top-center', 'warning', '请选择一条数据进行修改！')
			}else{
				if(table.row('.active').data().status== '0'){
					$state.go('editDeliveryPage',{serialNumEdit:table.row('.active').data().serialNum});
				}else showToastr('toast-top-center', 'warning', '该条数据已经发货，不能进行修改！')
			} 
		};
		//修改
		$scope.jumpToConfirm = function() {		
			if(table.rows('.active').data().length != 1){
				showToastr('toast-top-center', 'warning', '请选择一条数据进行确认发货！')
			}else{
				
				if(table.row('.active').data().status == '0'){
					$state.go('viewDelivery',{serialNum:table.row('.active').data().serialNum});
				}else showToastr('toast-top-center', 'warning', '已确认发货')
			} 
		};
			
			//复选框全选
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
			
			
	
							
	
	// 页面加载完成后调用，验证输入框
var warehouseAddressFlag,warehouseAddress1Flag,takeDeliveryWarehouseAddressFlag,takeDeliveryWarehouseAddress1Flag;
	$scope.$watch('$viewContentLoaded', function() { 
		debugger;
		if($scope.showSXf!='1'){
			warehouseAddressFlag=true;
			warehouseAddress1Flag=false;
		}else if($scope.showSXf=='1'){
			warehouseAddressFlag=false;
			warehouseAddress1Flag=true;
		}
		if($scope.showSXs!='1'){
			takeDeliveryWarehouseAddressFlag=true;
			takeDeliveryWarehouseAddress1Flag=false;
		}else if($scope.showSXs=='1'){
			takeDeliveryWarehouseAddressFlag=false;
			takeDeliveryWarehouseAddress1Flag=true;
		}
		/*if($scope.delivery.deliverType=='售前无合同发货'||$scope.delivery.deliverType=='售后无合同发货'){
			takeDeliveryWarehouseAddressFlag=false;
			takeWarehouseNameFlag=false;
		}else{
			takeWarehouseNameFlag=true;
			takeDeliveryWarehouseAddressFlag=true;
		}*/
		var e = $("#form_sample_deliverInfo"),
		
        r = $(".alert-danger", e),
        i = $(".alert-success", e);
        e.validate({
            errorElement: "span",
            errorClass: "help-block help-block-error",
            focusInvalid: !1,
            ignore: "",
            messages: {
            	deliverNum:{required:"发货单号不能为空！",rangelength:jQuery.validator.format("发货单号位数必须在{0}到{1}字符之间！")},
            	deliverType:{required:"发货类型不能为空！"},
            	orderSerial:{required:"订单编号不能为空！"},
            	supplyComId:{required:"供应商不能为空！"},
            	shipper:{required:"发货方不能为空！"},
            	receiver:{required:"收货方不能为空！"},
            	maker:{required:"制单人不能为空！"},
            	makeDate:{required:"制单日期不能为空！"},
            	takeDeliveryWarehouseAddress:{required:"收货地址不能为空！"},
            	takeDeliveryWarehouseAddress1:{required:"收货地址不能为空！"},
            	warehouseAddress:{required:"发货地址不能为空！"},
            	warehouseAddress1:{required:"发货地址不能为空！"},
            	
            	/*deliveryWarehouseSerial:{required:"发货仓库不能为空！"},*/
            	deliverDate:{required:"发货日期不能为空！"},
            	contactNum:{isPhone:"请输入正确的联系电话！"/*,required:"电话不能为空!"*/},
	          takeContactNum:{isPhone:"请输入正确的联系电话！"/*,required:"电话不能为空!"*/},
            	
            	/*transport:{required:"运输方不能为空！"},
            	shipNumber:{required:"运单号不能为空！"},*/
            	deliveryTransportContactNum:{isPhone:"请输入正确的联系电话！",required:"电话不能为空!"},
            	
            	
            	/*warehouseSerial:{required:"收货仓库不能为空！"},*/
            	takeDeliveryContactNum:{isPhone:"请输入正确的联系电话！",required:"电话不能为空!"},
            	
            	
            	
                payment: {
                    maxlength: jQuery.validator.format("Max {0} items allowed for selection"),
                    minlength: jQuery.validator.format("At least {0} items must be selected")
                },
                "checkboxes1[]": {
                    required: "Please check some options",
                    minlength: jQuery.validator.format("At least {0} items must be selected")
                },
                "checkboxes2[]": {
                    required: "Please check some options",
                    minlength: jQuery.validator.format("At least {0} items must be selected")
                }
            },
            rules: {
                name: {
                    minlength: 2,
                    required: !0
                },
                name2: {
                    minlength: 6,
                    required: !0
                },
                
                deliverNum:{required:true,
                },
                deliverType:{required:true,
                },
                orderSerial:{required:true,
                },
                supplyComId:{required:true,
                },
                shipper:{required:true,
                },
                receiver:{required:true,
                },
                maker:{required:true,
                },
                makeDate:{required:true,
                },
               /* deliveryWarehouseSerial:{required:true,
                },*/
                deliverDate:{required:true,
                },
                warehouseAddress:{required:warehouseAddressFlag,
                },
                takeDeliveryWarehouseAddress:{required:takeDeliveryWarehouseAddressFlag,
                },
                warehouseAddress1:{required:warehouseAddress1Flag,
                },
                takeDeliveryWarehouseAddress1:{required:takeDeliveryWarehouseAddress1Flag,
                },
                contactNum:{
                	isPhone: !0/*,
                	required:true*/
                },
                takeContactNum:{
                	isPhone: !0/*,
                	required:true*/
                },
                
              /*  transport:{required:true,
                },
                shipNumber:{required:true,
                },*/
                deliveryTransportContactNum:{
                	isPhone: !0/*,
                	required:true*/
                },
               /* warehouseSerial:{required:true,
                },*/
                takeDeliveryContactNum:{
                	isPhone: !0/*,
                	required:true*/
                },
                email: {
                    required: !0,
                    email: !0
                },
                email2: {
                    required: !0,
                    email: !0
                },
                url: {
                    required: !0,
                    url: !0
                },
                url2: {
                    required: !0,
                    url: !0
                },
                number: {
                    required: !0,
                    number: !0
                },
                number2: {
                    required: !0,
                    number: !0
                },
                digits: {
                    required: !0,
                    digits: !0
                },
                creditcard: {
                    required: !0,
                    creditcard: !0
                },
                delivery: {
                    required: !0
                },
                payment: {
                    required: !0,
                    minlength: 2,
                    maxlength: 4
                },
                memo: {
                    required: !0,
                    minlength: 10,
                    maxlength: 40
                },
                "checkboxes1[]": {
                    required: !0,
                    minlength: 2
                },
                "checkboxes2[]": {
                    required: !0,
                    minlength: 3
                },
                radio1: {
                    required: !0
                },
                radio2: {
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
	
	// 页面加载完成后调用，验证输入框
	$scope.$watch('$viewContentLoaded', function() {  
		var e = $("#form_sample_takeDeliveryInfo"),
        r = $(".alert-danger", e),
        i = $(".alert-success", e);
        e.validate({
            errorElement: "span",
            errorClass: "help-block help-block-error",
            focusInvalid: !1,
            ignore: "",
            messages: {
            	transport:{required:"运输方不能为空！"},
            	shipNumber:{required:"运单号不能为空！"},
            	warehouseSerial:{required:"收货仓库不能为空！"}
            },
            rules: {
                
                transport:{required:true
                },
                shipNumber:{required:true
                },
                warehouseSerial:{required:true
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
	
	// 页面加载完成后调用，验证输入框
	$scope.$watch('$viewContentLoaded', function() {  
		var e = $("#form_sample_3"),
        r = $(".alert-danger", e),
        i = $(".alert-success", e);
        e.validate({
            errorElement: "span",
            errorClass: "help-block help-block-error",
            focusInvalid: !1,
            ignore: "",
            messages: {
            	deliverCount:{required:"发货数量必须为大于等于0的正整数！",digits:"发货数量必须为大于等于0的正整数！"},
            },
            rules: {
            	deliverCount: {
	                	required: true,
	                	digits: true,
	                	deliverNumCheck:true
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
	

	var self = this;
	self.delivery={orderSerial:null};
	self.deliveryList=[];


	//查询所有的用户合同
	/*fetchAllUserContract();

	function fetchAllUserContract(){
		ContractService.fetchAllUserContract()
		.then(
				function(d) {

				},
				function(errResponse){
					console.error('Error while fetching Users');
				}
		);
	}*/
	
	
	
	
	
	
	
	
	/*********************************************出库jsSTART***************************************************/
	
	   $scope.stockInView = function(serialNum){
    	   $state.go("stockOutView",{serialNum:serialNum});
       }
	   
	   $scope.stockOut = function(){
		   var id_count = $('#stockInTable input[name="serialNum2"]:checked').length;
	       	if(id_count==0){
	       		toastr.warning("请选择您要出库的记录");
	       	}else if(id_count>1){
	       		toastr.warning("只能选择一条发货单进行出库");
	       	}else{
	       		
	       		var row = stock_table.row(".active").data();
	       		if(row.status=="1"){
	       			toastr.warning("该发货单已出库！");
	       			return;
	       		}
	       		//var serialNum = $('#stockInTable input[name="serialNum2"]:checked').val();
	       		$state.go("stockOut",{serialNum:row.stockInOutRecord.serialNum});
	       	}
	   }
	   
    /**
     * 批量删除出库记录
     */
    $scope.stockOutDelete = function () {
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
    		var promise = DeliveryService.deleteStockOutInfo(ids);
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
     
    $scope.stockOutEdit = function(){
    	var id_count = $('#stockInTable input[name="serialNum2"]:checked').length;
		if(id_count==0){
			toastr.warning("请选择您要修改的记录");
		}else if(id_count>1){
			toastr.warning("只能选择一条数据进行修改");
		}else{
			var serialNum = $('#stockInTable input[name="serialNum2"]:checked').val();
			$state.go("stockOutAdd",{serialNum:serialNum});
		}
    }
   	
    
    /**
        * 导出出库记录
        */
    $scope.exportStockOut = function(){
    	var ids = '';
		// Iterate over all checkboxes in the table
    	stock_table.$('input[type="checkbox"]').each(
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
	    	 window.location.href=$rootScope.basePath+"/rest/takeDelivery/exportStockOut"+"?serialNums="+ids;
	}
    
	jQuery.validator.addMethod("deliverNumCheck", function (value, element) {
		    return element.dataset.ordercount==undefined?true : this.optional(element) || Number(element.dataset.ordercount)-value >= 0;
		}, "发货数量不能超过未发数量且不能超过库存数量");
	/*jQuery.validator.addMethod("deliverNumCheck1", function (value, element) {
	    return element.dataset.ordercount1==undefined?true : this.optional(element) || Number(element.dataset.ordercount1)-value >= 0;
	}, "发货数量不能超过库存数量");*/

    $('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
        // 获取已激活的标签页的名称
        var activeTab = $(e.target).text(); 
        // 获取前一个激活的标签页的名称
       // var previousTab = $(e.relatedTarget).text(); 
        var absurl = $location.absUrl();
        $("#tip").text(activeTab);
        if(activeTab=="出库记录"){
        	handle.addCookie("d_type","stockOut",24);
        }else{
        	handle.addCookie("d_type","deliver",24);
        }
     });
   
   
	
	    /***出库列表初始化START***/
     var stock_table;
     var loadStockOutTable = function() {
              a = 0;
              App.getViewPort().width < App.getResponsiveBreakpoint("md") ? $(".page-header").hasClass("page-header-fixed-mobile") && (a = $(".page-header").outerHeight(!0)) : $(".page-header").hasClass("navbar-fixed-top") ? a = $(".page-header").outerHeight(!0) : $("body").hasClass("page-header-fixed") && (a = 64);
            stock_table = $("#stockInTable").DataTable({
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
//                  serverSide: true,
                 //ajax: "rest/takeDelivery/takeDeliveryList",//加载数据中
                 ajax :{ "url":$rootScope.basePath
						+ "/rest/takeDelivery/stockOutList",// 加载数据中user表数据    
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
                               /* { mData: 'orderNum' },
                                { mData: 'stockInOutRecord.shipperOrReceiver' },
                                { mData: 'delivery' },*/
                                { mData: 'stockInOutRecord.status' }
                          ],
                 'aoColumnDefs' : [ {
  							'targets' : 0,
  							'searchable' : false,
  							'orderable' : false,
  							'className' : 'dt-body-center',
  							'render' : function(data,
  									type, row, meta) {
//	  	  								return '<input  type="checkbox" id='+data+'   name="serialNum2" value="'
//											+ $('<div/>')
//													.text(
//															data)
//													.html()
//											+ '">';
  								return '<label class="mt-checkbox mt-checkbox-single mt-checkbox-outline">'+
                                '<input type="checkbox" name="serialNum2" class="checkboxes"  id="'+data+'" value="'+data+'" data-set="#stockInTable .checkboxes" />'+
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
  						}/*,{
  							'targets' : 7,
  							'render' : function(data,
  									type, row, meta) {
  								if(data==undefined){
  									return "无";
  								}
  								return data;
  								
  							}
  						},{
  							'targets' : 8,
  							'render' : function(data,
  									type, row, meta) {
  									if(!isNull(data)){
										return data;
									}
	  								return "中航能科（上海）能源科技有限公司";
	
  							}
  						},{
  							'targets' : 7,
  							'render' : function(data,
  									type, row, meta) {
  									if(isNull(data)||isNull(data.deliverNum)){
										return row.stockInOutRecord.docNum;
									}
	  								return data.deliverNum;
	
  							}
  						}*/,{
  							'targets' : 7,
  							'render' : function(data,
  									type, row, meta) {
  								if(data=="0"){
										return '<span  class="label label-sm label-warning ng-scope">待出库</span>';
								}else if(data=="1"){
										return '<span  class="label label-sm label-info ng-scope">已出库</span>';
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
	        })
          };
     /***选择出库列表初始化END***/
          		
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
	
	/*********************************************出库jsEND***************************************************/
		 	 /** *************订单物料明细可检索化  start*************** */
		  	 $scope.pageIndex = 1; //记录当前页
		  	 $scope.pageSize = '10'; //每页的记录数
		  	 $scope.totalPage = '1'; //记录总页数
		  	 $scope.dispalyDeliveryMateriel = [];//页面显示结果
		  	 $scope.filterDeliveryMateriel = [];//查询筛选结果
		  	 
		  	 $scope.createFilterList = function(){
		  		 $scope.filterDeliveryMateriel = [];
		  		if($scope.deliveryMaterielE.length>0&&$scope.queryStr&&!isNull($scope.queryStr)){
		  			for(var i = 0;i < $scope.deliveryMaterielE.length;i++){// data.data为选择的标准物料
		  				if(($scope.deliveryMaterielE)[i].materielNum.indexOf($scope.queryStr)>=0){
		  					$scope.filterDeliveryMateriel.push(angular.copy(($scope.deliveryMaterielE)[i]));
		  				}else if(($scope.deliveryMaterielE)[i].materielName.indexOf($scope.queryStr)>=0){
		  					$scope.filterDeliveryMateriel.push(angular.copy(($scope.deliveryMaterielE)[i]));
		  				}else if(($scope.deliveryMaterielE)[i].specifications.indexOf($scope.queryStr)>=0){
		  					$scope.filterDeliveryMateriel.push(angular.copy(($scope.deliveryMaterielE)[i]));
		  				}
		  			}
		  		}else{
		  			$scope.filterDeliveryMateriel = angular.copy($scope.deliveryMaterielE);
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
		  	$scope.changeValue = function(obj,judgeString){
		  		debugger;
		  		if(judgeString=='f'){
		  			$scope.warehouseAddress=$(obj).val();
		  		}
		  	}
		  	//发货申请
		    $scope.confirmDeliveryPlanApply  = function() {// 进入提交申请
	        	$scope.submitOrder = {}
	        	$scope.submitOrder.serialNum = $scope.deliveryDetail.serialNum;
	        	$scope.submitOrder.remark = $scope.deliveryDetail.reason;
	        	$scope.submitOrder.deliverType = $scope.deliveryDetail.deliverType;
	        	//启动流程
	        	DeliveryService.startDeliveryPlanProcess($scope.submitOrder).then(
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
	        //********审批流程end****************//  
	        function doDelivery(_url, mydata, modal){
	        	$.ajax( {
	    	        url : _url,
	    	        dataType:"text",
	    	        type: 'POST',
	    	        data : mydata,
	    	        success : function(data) {
	    	        	showToastr('toast-bottom-right', 'success', data);
	    	        	$scope.cancelPage();
	    	        },
	    	        error : function(data) {
	    	        	toastr.error('连接服务器出错,请登录重试！');
	    	        }
	    	     });
	    	}
	    	
	    	//审批通过
	    	$scope.deliverPass = function() {
	    	    var mydata={"processInstanceId":$("#processInstanceId").val(),"serialNum":$scope.deliveryDetail.serialNum,"content":$("#content").val(),
	    				"completeFlag":true};
	    	    var _url = ctx + "rest/delivery/complate/" + $("#taskId").val();
	    	    doDelivery(_url, mydata, 'audit');
	    	};
	    	//审批不通过
	    	$scope.deliverUnPass = function() {
	    		var mydata={"processInstanceId":$("#processInstanceId").val(),"serialNum":$scope.deliveryDetail.serialNum,"content":$("#content").val(),
	    				"completeFlag":false};
	    		var _url = ctx + "rest/delivery/complate/" + $("#taskId").val();
	    		doDelivery(_url, mydata, 'audit');
	    	};
	    	
	    	//重新申请
	    	$scope.replyDelivery = function() {
	    	    var mydata={"processInstanceId":$("#processInstanceId").val(),
	    				"reApply":true,"reason":$scope.delivery.reason,"serialNum":$scope.delivery.serialNum};
	    		var _url = ctx + "rest/delivery/modifyDeliveryPlanApply/" + $("#taskId").val();
	    		doDelivery(_url, mydata, 'modify');
	    	};
	    	//取消申请
	    	$scope.cancelApply = function() {
	    		 var mydata={"processInstanceId":$("#processInstanceId").val(),
		    				"reApply":true,"reason":$scope.delivery.reason,"serialNum":$scope.delivery.serialNum};
	    		var _url = ctx + "rest/delivery/modifyDeliveryPlanApply/" + $("#taskId").val();
	    		doDelivery(_url, mydata, 'modify' );
	    	};
	    	 $scope.cancelPage  = function() {// 取消编辑
		        	$state.go("saleOrder");
		        };
		        
		     //********发货tab附件  start****************//
		        /**
				 * 显示编辑、删除操作
				 */
		       $scope.showOperation = function(type,index){

		    	   if(type=='deliverFile'){
		    		   call =  "operation_df"+index;
		    	   }
		    	   if(type=='file'){
		    		   call =  "operation_f"+index;
		    	   }
		    	   
		    	   $scope[call] = true;
		       };
		       
		       /**
				 * 隐藏编辑、删除操作
				 */
		       $scope.hideOperation = function(type,index){

		    	   if(type=='deliverFile'){
		    		   call =  "operation_df"+index;
		    	   }
		    	   if(type=='file'){
		    		   call =  "operation_f"+index;
		    	   }
		    	   $scope[call]= false;
		       };
		       
	   	   		var _deliverFileIndex = 0;
	   	   	    $scope.savedeliverFile  = function() {//保存deliverFile信息
			   	   	if($scope.delivery==null||$.trim($scope.delivery.serialNum)==""){
			  			toastr.error("请先保存发货信息！");	
			  			return;
			  		}
	   	   	    	if($('#form_sample_9').valid()){
	   	   	    	DeliveryService.saveDeliverFile($scope.deliverFile).then(
	   	   	       		     function(data){
	   	   	       		    	toastr.success('数据保存成功！');
	   	   	       		    	$scope.canceldeliverFile();
	   	   	       		     },
	   	   	       		     function(error){
	   	   	       		    	toastr.error('数据保存出错！');
	   	   	       		         $scope.error = error;
	   	   	       		     }
	   	   	       		 );
	   	   	    	}
	   	   	    	
	   	   	    }; 	
	   	   	    
	   	   	    $scope.canceldeliverFile  = function() {//取消编辑deliverFile信息
	   	   	    	$scope.deliverFileInfoInput = true;
	   	   		    $scope.deliverFileInfoShow = true;
	   	   	    };
	   	   	    
	   	   	    $scope.editdeliverFile  = function() {//进入编辑deliverFile信息
	   	   	    	$scope.deliverFileInfoInput = false;
	   	   		    $scope.deliverFileInfoShow = false;
	   	   	    };
	   	   	    /**
	   	 	        * deliverFile新增一行
	   	 	        */
	   	   	    $scope.adddeliverFile = function(){
			   	   	  if($scope.delivery==null||$.trim($scope.delivery.serialNum)==""){
				  			toastr.error("请先保存发货信息！");	
				  			return;
				  		}else{
	   	   		    	   if($scope.deliverFile){}else{$scope.deliverFile =[{}]}
	   	   		    	   $scope.deliverFile[_deliverFileIndex] = {};
	   	   		    	   $scope.deliverFile[_deliverFileIndex].deliverSerial = $scope.delivery.serialNum;
	   	   		    	   _deliverFileIndex++;
	   	   		       }
	   	   	    };
	   	   	    
	   	   	    /**
	   		        * deliverFile删除一行
	   		        */
	   		       $scope.deletedeliverFile = function(index){
	   		    	   $scope.deliverFile.splice(index,1);
	   		    	   _deliverFileIndex--;
	   		       };
	   		       
	   		       
	   		      var validatedeliverFileInit = function() {
	   		        	var e = $("#form_sample_9");
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
	   		  	  var DeliverUploader = $scope.DeliverUploader = new FileUploader({url:'rest/fileOperate/uploadSingleFile'});
	   		  	 
	   		  	DeliverUploader.onAfterAddingFile = function(item){
	   		  		  if(item.file.size>10000000){
	   		  			  //toastr.warning("文件大小超过10M！");
	   		  			DeliverUploader.cancelAll();
	   		  		  }
	   		  	  }
	   		  	  
	   		  	  //添加文件到上传队列后
	   		 DeliverUploader.onCompleteAll = function () {
	   			DeliverUploader.clearQueue();
	   		  	  };
	   		  	  //上传成功
	   		  	DeliverUploader.onSuccessItem = function (fileItem,response, status, headers) {
	   		  		  if (status == 200){ 
	   		  			  if(response==""){
	   		  				  toastr.error("上传失败！");
	   		  				  return;
	   		  			  }
	   		  		  		toastr.success("上传成功！");
	   		  		  		$scope.deliverFile[uploadSelectIndex].file = response.filename;
	   		  		  }else{
	   		  			  toastr.error("上传失败！");
	   		  			$scope.deliverFile[uploadSelectIndex].file = response.filename;
	   		  		  }
	   		  		};
	   		  	  //上传失败
	   		  	  uploader.onErrorItem = function (fileItem, response, status, headers) {
	   		  			toastr.error("上传失败！");
	   		  	  };  
	   		  	  
	   		  	$scope.deliverUp = function(file){
		   		  	DeliverUploader.clearQueue();
		   		  	DeliverUploader.addToQueue(file);
		   		  	DeliverUploader.uploadAll();
	   	  	  }
	   		 var uploadSelectIndex;
	     	  $scope.uploaddeliverFile = function(index){
	     		uploadSelectIndex = index;
	     	  }
	   	   	  //********发货tab附件  end****************//
}]);


