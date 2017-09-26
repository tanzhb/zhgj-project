angular.module('MetronicApp').controller('DeliveryController', ['$rootScope','$scope','$http', 'settings', '$q','DeliveryService','$state','$compile','$stateParams','$filter','$location', function($rootScope,$scope,$http,settings, $q,DeliveryService,$state,$compile,$stateParams,$filter,$location) {
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
	
		getSupplyComId();
		//控制输入框和span标签的显示
		$scope.span =false;
		$scope.input = true;
		
		
		$scope.supplyComId="中航能科";
		$scope.shipper="中航能科";
		$scope.transportType="水路运输";
		$scope.serialNums = [];	
		$scope.orderSerial=null;
		
		//根据参数查询对象
    if($stateParams.serialNum){
    	$scope.getDeliveryInfo($stateParams.serialNum,$stateParams.taskId, $stateParams.comments);	
    }
    
       //根据参数查询对象
    if($stateParams.serialNumEdit){
    	$scope.getDeliveryEditInfo($stateParams.serialNumEdit,$stateParams.taskId, $stateParams.comments);
    }
    
    
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
		$scope.getWarehouseList();
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
	$scope.goDelivery=function (serialNum){
		var promise = DeliveryService.goDelivery(serialNum);
		promise.then(function(data) {
				$(".modal-backdrop").remove();
				toastr.success("发货成功");
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
	
	
	var getSupplyComId=function (){
		var promise = DeliveryService.getSupplyComId();
		promise.then(function(data) {
			if(data.comName!=null){
				$scope.supplyComId=data.comName;
				$scope.shipper=data.comName;
				$scope.receiver="中航能科"
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
            	deliverCount:{required:"发货数量不能为空！",digits:"只能是正整数！"},
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
                	deliverNumCheck:true
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
	
	var batchNumCheck=function validateOneElement(index) {
		 //验证id="form1"的表单中id="elementId"的表单元素
		var batchNumFlag=$("#form_sample_2").validate().element($("#batchNum"+index+""));
		return batchNumFlag;
		}
	
	var manufactureDateCheck=function validateOneElement(index) {
		 //验证id="form1"的表单中id="elementId"的表单元素
		var manufactureDateFlag=$("#form_sample_2").validate().element($("#manufactureDate"+index+""))
		return manufactureDateFlag;
		}
	
	var deliveryCountCheck=function validateOneElement(index) {
		 //验证id="form1"的表单中id="elementId"的表单元素
		 var deliveryCountFlag=$("#form_sample_2").validate().element($("#deliverCount"+index+""))
		 return deliveryCountFlag;
		}
	
	jQuery.validator.addMethod("deliverNumCheck", function (value, element) {
		if(element.dataset.ordercount!=null&&$.trim(element.dataset.ordercount)!=''){
			return this.optional(element) || Number(element.dataset.ordercount)-value >= 0;	
		}else{
			return true;
		}
	    
	}, "发货数量不能超过订单数量");
	
	/**
	 * 保存销售订单物料信息
	 */
	$scope.saveOrderMateriel = function(deliveryMateriel,index) {
		delete deliveryMateriel.materiel;
		delete deliveryMateriel.supplyMateriel;
		delete deliveryMateriel.supply;
		if($scope.delivery==null||$.trim($scope.delivery.serialNum)==""){
			toastr.error("请先保存基本信息！");	
			return;
		}
		var batchNumFlag=batchNumCheck(index);
		if(!batchNumFlag){return false;}
		var manufactureDateFlag=manufactureDateCheck(index);
		if(!manufactureDateFlag){return false;}
		var deliveryCountFlag=deliveryCountCheck(index);
		if(!deliveryCountFlag){return false;}
		
		deliveryMateriel.deliverSerial=$scope.delivery.serialNum;
		
		var promise = DeliveryService.saveDeliveryMateriel(deliveryMateriel);
		promise.then(function(data) {
			if (!handle.isNull(data)) {
				$(".modal-backdrop").remove();
				toastr.success("保存成功");
				handle.unblockUI();
				$scope.deliveryMaterielE[index] = data;
				$scope.deliveryMaterielE[index] = data;
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
		}
		$scope.deliveryMaterielE= null;
		$scope.materielCount=null;
		$scope.orderMaterielInput=true;
	}
	
	
	/**
	 * 编辑销售订单物料信息
	 */
	$scope.editOrderMateriel = function() {
		debugger
		//判断基本信息是否保存，未保存先保存基本信息
		if($scope.isBasicInfoSaved!='1'){
			toastr.error("请先保存基本信息！");	
			return;
		}
		
		/*var batchNum=deliveryMateriel.batchNum;
		if($.trim(batchNum)==""||$.trim(batchNum)==null){
			toastr.error("批次号不能为空！");	
			return;
		}
		
		var manufactureDate=deliveryMateriel.manufactureDate;
		if($.trim(manufactureDate)==""||$.trim(manufactureDate)==null){
			toastr.error("生产日期不能为空！");	
			return;
		}
		
		var deliverCount=deliveryMateriel.deliverCount;
		if($.trim(deliverCount)==""||$.trim(deliverCount)==null){
			toastr.error("发货数量不能为空！");	
			return;
		}
		
		var reg = /^(0|[1-9]\d*)$/;
		if(!reg.test(deliverCount)){
			toastr.error("发货数量只能是正整数！");	
			return;
		}
		
		var amount=deliveryMateriel.amount;
		if(parseInt(deliverCount)>parseInt(amount)){
			toastr.error("发货数量不能大于订单数量！");	
			return;
		}*/
		/*var batchNumFlag=batchNumCheck(index);
		if(!batchNumFlag){return false;}
		var manufactureDateFlag=manufactureDateCheck(index);
		if(!manufactureDateFlag){return false;}
		var deliveryCountFlag=deliveryCountCheck(index);
		if(!deliveryCountFlag){return false;}*/
		
		/*if(deliveryMateriel.serialNum==null||deliveryMateriel.serialNum==""){
			deliveryMateriel.deliverSerial=$scope.delivery.serialNum;
		}*/
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
			param.batchNum = $scope.deliveryMaterielE[i].batchNum;
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
	$scope.saveBasicInfo=function(){
		if($('#form_sample_1').valid()){
		var promise = DeliveryService.saveBasicInfo($scope);
		promise.then(function(data) {
			if (!handle.isNull(data)) {
				$(".modal-backdrop").remove();
				toastr.success("保存成功");
				handle.unblockUI();
				$scope.delivery= data;
				/*$scope.deliveryMateriel[index] = data;
				console.log(data.data);*/
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
	$scope.editBasicInfo=function(){
		if($('#form_sample_1').valid()){
		var promise = DeliveryService.editBasicInfo($scope);
		promise.then(function(data) {
			if (!handle.isNull(data)) {
				$(".modal-backdrop").remove();
				toastr.success("保存成功");
				handle.unblockUI();
				$scope.delivery= data;
				/*$scope.deliveryMateriel[index] = data;
				console.log(data.data);*/
				$scope.isBasicInfoSaved='1';
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
		};
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
		$state.go('delivery');
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
      		         console.log("error")
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
	    	 window.location.href=$rootScope.basePath+"/rest/delivery/exportDelivery";
	    	 handle.unblockUI(); 
	       }
		
	
	    var table;
		var loadMainTable = function() {
			
			var a = 0;
			App.getViewPort().width < App.getResponsiveBreakpoint("md") ? $(".page-header").hasClass("page-header-fixed-mobile")&& (a = $(".page-header").outerHeight(!0)): 
				$(".page-header").hasClass("navbar-fixed-top") ? a = $(".page-header").outerHeight(!0): $("body").hasClass("page-header-fixed")&& (a = 64);

				 table = $("#sample_2").DataTable(
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
							//"sScrollXInner": "110%",
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
							                            { mData: 'materielCount' },
							                            { mData: 'packageCount' },
							                            { mData: 'receiver'},
							                            { mData: 'deliveryAddress'},
							                            { mData: 'deliverDate'},
							                            { mData: 'transportType'},
							                            { mData: 'takeAddress' },
							                            { mData: 'remark'},
							                            { mData: 'status',
							                            	mRender:function(data){
							                            		if(data!=""&&data!=null){
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
							                            		}
							                            	}
							                            }
							                            ],
							                            'aoColumnDefs': [ {
							                            	'targets' : 0,
							                            	'searchable' : false,
							                            	'orderable' : false,
							                            	'className' : 'dt-body-center',
							                            	'render' : function(data,type, full, meta) {
							                            		return '<input type="checkbox" name="id[]" value="'+ $('<div/>').text(data).html()+ '">';
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
		
		
		//销售订单列表
        var table1;
	    var loadMainTable1 = function() {
	            a = 0;
	            App.getViewPort().width < App.getResponsiveBreakpoint("md") ? $(".page-header").hasClass("page-header-fixed-mobile") && (a = $(".page-header").outerHeight(!0)) : $(".page-header").hasClass("navbar-fixed-top") ? a = $(".page-header").outerHeight(!0) : $("body").hasClass("page-header-fixed") && (a = 64);
	            table1 = $("#sample_21")
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
//	                serverSide: true,
	                ajax:"rest/order/findOrderList?type=supply&selectFor=delivery",//加载数据中
	                "aoColumns": [
	                              { mData: 'serialNum' },
	                              { mData: 'orderNum' },
	                              { mData: 'buyComId' },
	                              { mData: 'orderNum' },
	                              { mData: 'orderNum' },
	                              { mData: 'deliveryMode' },
	                              { mData: 'serviceModel' },
	                              { mData: 'orderNum' },
	                              { mData: 'orderNum' },
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
    		$("#sample_22_processing").remove();
		}
    	
    	
    	
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
			debugger
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
								sortAscending : ": activate to sort column ascending",
								sortDescending : ": activate to sort column descending"
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
								},
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
								} ],
						dom : "<'row' <'col-md-12'B>><'row'<'col-md-6 col-sm-12'l><'col-md-6 col-sm-12'f>r><'table-scrollable't><'row'<'col-md-5 col-sm-12'i><'col-md-7 col-sm-12'p>>",
						order : [ [ 1, "asc" ] ],// 默认排序列及排序方式

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
								sortAscending : ": activate to sort column ascending",
								sortDescending : ": activate to sort column descending"
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
						order : [ [ 1, "asc" ] ],// 默认排序列及排序方式
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
									mData : 'claimTime',
									mRender : function(
											data,
											type,
											row,
											meta) {
										if(data != null){
				                			return timeStamp2String(data);
				                		}else{
				                			return "无需签收";
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
			debugger
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
	          		    	 debugger
	          		    	$scope.saleOrder=data.orderInfo;
	          		    	
	          		    	if($scope.delivery!=null){
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
	          		    	length=data.orderMateriel.length;
	          		    	$scope.totalDeliveryCount="";
	          		     },
	          		     function(error){
	          		         $scope.error = error;
	          		     }
	          		 );
	        	
	        }; 
	        
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
	          		    	$scope.delivery.orderNum1=data.delivery.orderNum;
	          		    	
	          		    	if(data.delivery.deliverType=='其他发货'){
	          		    		$scope.otherMode=true;
	          		    	}else{
	          		    		$scope.otherMode=false;
	          		    	}
	          		    	if($scope.delivery.supplyComId!=null){
	          		    		$scope.supplyComId=$scope.delivery.supplyComId;
	          		    		$scope.shipper=$scope.delivery.shipper;
	          		    	}
	          		    	$scope.deliveryMaterielE=data.deliveryMateriels;
	          		    	
	          		    	if(data.deliveryMateriels.length>0){
	          		    		$scope.orderMaterielInput=false;	
	          		    	}
	          		    	
	          		    	$scope.materielCount=data.deliveryMateriels.length;
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
	        	debugger
	        	var totalCount=parseInt(0);
	        	var check=/^[1-9]\d*|0$/;
	        	for(var i=0;i<length;i++){
	        		/*var count=parseInt($("input[name='deliverCount"+i+"'").val());*/
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
			          		    	$scope.deliveryDetail=data.delivery;
			          		    	if($scope.deliveryDetail.supplyComId!=null){
			          		    		$scope.supplyComId=$scope.deliveryDetail.supplyComId;
			          		    		$scope.shipper=$scope.deliveryDetail.shipper;
			          		    	}
			          		    	$scope.deliveryDetailMateriel=data.deliveryMateriels;
			          		    	for(var i=0;i<$scope.deliveryDetailMateriel.length;i++){
			          		    		if($scope.deliveryDetailMateriel[i].status=='0'){$scope.deliveryDetailMateriel[i].status='待发货'};
			          		    		if($scope.deliveryDetailMateriel[i].status=='1'){$scope.deliveryDetailMateriel[i].status='已发货'}
			          		    	}
			          		    	$("#serialNum").val(serialNum);//赋值给隐藏input，通过和不通过时调用
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
			        
			        
			        //通过选择发货仓库改变地址（新增）
			        $scope.selectAddress=function(){
			        	var warehouseSerial=$scope.delivery.warehouseSerial;
			        	DeliveryService.selectAddress(warehouseSerial).then(
			          		     function(data){
			          		    	$scope.warehouseAddress=data.address;
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
			        	var warehouseSerial=$scope.takeDelivery.warehouseSerial;
			        	DeliveryService.selectAddress(warehouseSerial).then(
			          		     function(data){
			          		    	$scope.takeDeliveryWarehouseAddress=data.address;
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
		
		//修改
		$scope.jumpToEdit = function() {		
			if(table.rows('.active').data().length != 1){
				showToastr('toast-top-center', 'warning', '请选择一条数据进行修改！')
			}else{
				if(table.row('.active').data().status != '0'){
					showToastr('toast-top-center', 'warning', '该条数据已经发货流程审批，不能进行修改！')
				}else $state.go('editDeliveryPage',{serialNumEdit:table.row('.active').data().serialNum});
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
	$scope.$watch('$viewContentLoaded', function() {  
		var e = $("#form_sample_1"),
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
            	
            	
            	deliveryWarehouseSerial:{required:"发货仓库不能为空！"},
            	deliverDate:{required:"发货日期不能为空！"},
            	contactNum:{digits:"请输入正确的联系, 必须为数字！",rangelength:jQuery.validator.format("电话必须在{0}到{1}位数字之间！")},
            	
            	
            	transport:{required:"运输方不能为空！"},
            	shipNumber:{required:"运单号不能为空！"},
            	deliveryTransportContactNum:{digits:"请输入正确的联系, 必须为数字！",rangelength:jQuery.validator.format("电话必须在{0}到{1}位数字之间！")},
            	
            	
            	takeDeliveryWarehouseSerial:{required:"收货仓库不能为空！"},
            	takeDeliveryContactNum:{digits:"请输入正确的联系, 必须为数字！",rangelength:jQuery.validator.format("电话必须在{0}到{1}位数字之间！")},
            	
            	
            	
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
               
                
                
                deliveryWarehouseSerial:{required:true,
                },
                deliverDate:{required:true,
                },
                contactNum:{
                	digits:true,
                	rangelength:[7,20]
                },
                
                
                transport:{required:true,
                },
                shipNumber:{required:true,
                },
                deliveryTransportContactNum:{
                	digits:true,
                	rangelength:[7,20]
                },
                
                
                takeDeliveryWarehouseSerial:{required:true,
                },
                takeDeliveryContactNum:{
                	digits:true,
                	rangelength:[7,20]
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
	    	 window.location.href=$rootScope.basePath+"/rest/takeDelivery/exportStockOut";
	}
    
    
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
                                { mData: 'orderMateriel.materiel.materielName'},
                                { mData: 'orderMateriel.materiel.specifications'},
                                { mData: 'stockInOutRecord.stockDate' },
                                { mData: 'stockCount' },
                                { mData: 'batchNum' },
                                { mData: 'stockInOutRecord.order.buyName' },
                                { mData: 'stockInOutRecord.order.orderNum' },
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
  									if(data!=undefined){
										return data;
									}
	  								return "";
	
  							}
  						},{
  							'targets' : 9,
  							'render' : function(data,
  									type, row, meta) {
  									if(data!=undefined){
										return data;
									}
	  								return "";
	
  							}
  						},{
  							'targets' : 10,
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

}]);


