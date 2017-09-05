angular.module('MetronicApp').controller('GatheringMoneyController', ['$rootScope','$scope','$http', 'settings', '$q','GatheringMoneyService','$state','$compile','$stateParams','$filter', function($rootScope,$scope,$http,settings, $q,GatheringMoneyService,$state,$compile,$stateParams,$filter) {
	$scope.$on('$viewContentLoaded', function() {   
		// initialize core components
		handle = new pageHandle();
		App.initAjax();

		// set default layout mode
		$rootScope.settings.layout.pageContentWhite = true;
		$rootScope.settings.layout.pageBodySolid = false;
		$rootScope.settings.layout.pageSidebarClosed = false;

		loadMainTable();
		loadMainTable1();

		//控制输入框和span标签的显示
		$scope.span =false;
		$scope.input = true;
		$scope.image='http://localhost:8080/zhgj-web/assets/apps/css/icons/timg3.jpg';

		//根据参数查询对象
		 if($stateParams.serialNum){
    	$scope.getPayInfo($stateParams.serialNum);	
         }
	});


	//修改
	$scope.jumpToEdit = function() {
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
							} else{
								ids = "more"
							}
						}
					}
				});
		if(ids==''){
			toastr.warning('请选择一个付款！');return;
		}else if(ids=='more'){
			toastr.warning('只能选择一个付款！');return;
		}
		$state.go('editGatheringMoney',{serialNum:ids});
	};


	//根据参数查询对象
	$scope.getPayInfo  = function(serialNum) {
		debugger
		GatheringMoneyService.selectPay(serialNum).then(
      		     function(data){
      		    	$scope.pay=data;
      		     },
      		     function(error){
      		         console.log("error")
      		     }
      		 );
    	
    }; 


	//返回按钮
	$scope.goBack=function(){
		$state.go('gatheringMoneyRecord');
	}

	//打印
	$scope.print=function(){
		window.print();  
	}
	var paymentAmount=null;
	$scope.selectClauseDetail=function(serialNum){
		GatheringMoneyService.selectClauseDetail(serialNum).then(
				function(data){
					if($scope.pay==null){
						$scope.clauseSettlement=data;
						paymentAmount=$scope.clauseSettlement.totalAmount;	
					}else{
						$scope.pay.deliveryAmount=data.totalAmount;
						paymentAmount=data.totalAmount;
					}
				},
				function(error){
					$scope.error = error;
				}
		);	
	};
	
	var buyComId=null;
	//获取订单物料的信息
	$scope.getSaleOrderInfo  = function(serialNum) {
		GatheringMoneyService.getSaleOrderInfo(serialNum).then(
				function(data){
					if($scope.pay==null){
						$scope.saleOrder=data.orderInfo;
						buyComId=$scope.saleOrder.buyComId;
						var orderSerial=data.orderInfo.serialNum;
						$scope.orderSerial=data.orderInfo.serialNum;
						$scope.deliveryMaterielE=data.clauList;
					}else{
						$scope.pay.orderNum=data.orderInfo.orderNum;
						$scope.pay.orderSerial=data.orderInfo.serialNum;
						$scope.pay.clauseSettList=data.orderInfo.clauseSettList;
						$scope.pay.supplyComId=data.orderInfo.supplyComId;
						$scope.pay.deliveryAmount=null;
						buyComId=data.orderInfo.buyComId;
					}
				},
				function(error){
					$scope.error = error;
				}
		);

	};

	//添加付款
	$scope.saveBasicInfo=function (){
		if($('#form_sample_1').valid()){
			var fd = new FormData();
			debugger
			var file = document.querySelector('input[type="file"]').files[0];
			if($.trim($("#paymentVoucher").val())!=''){
				fd.append("file", file);
			}else{
				toastr.warning("收款凭证不能为空！");
				return;
			}
			
			var clauseItem=$("input[name='serialNumClause']:checked").val();
			if($.trim(clauseItem)!=''){
				fd.append('clauseSettlementSerial',$("input[name='serialNumClause']:checked").val()); 
			}else{
				toastr.warning("至少选择一个结算条款！");
				return;
			}
			fd.append('paymentNum',$scope.paymentRecord.paymentNum); 
			fd.append('paymentPlanNum',$scope.paymentRecord.paymentPlanNum); 
			fd.append('orderSerial',$scope.orderSerial); 
			fd.append('paymentStyle',$scope.paymentRecord.paymentStyle); 
			fd.append('paymentAmount',paymentAmount);
			fd.append('buyComId',buyComId);
			fd.append('paymentType',$scope.paymentRecord.paymentType);
			fd.append('billStyle',$("input[name='billStyle']:checked").val()); 
			fd.append('isBill',$("input[name='isBill']:checked").val());
			fd.append('invoiceSerial',$scope.paymentRecord.invoiceSerial); 
			fd.append('applicant',$scope.paymentRecord.applicant);
			fd.append('applyDate',$scope.paymentRecord.applyDate); 
			fd.append('remark',$scope.paymentRecord.remark);
			
			$http({
				method:'POST',
				url:"rest/pay/savePaymentRecord",
				data: fd,
				headers: {'Content-Type':undefined}
			})   
			.success( function ( response )
					{
				//上传成功的操作
				toastr.success("保存应收款数据成功！");
				$state.go('gatheringMoneyRecord');
					});
		}
	}
	
	
	//更新付款
	$scope.editBasicInfo=function (){
		if($('#form_sample_1').valid()){
			var fd = new FormData();
			debugger
			
			if($.trim($("#paymentVoucher").val())!=''){
				var file = document.querySelector('input[type="file"]').files[0];
				fd.append("file", file);
			}else{
				/*toastr.warning("付款凭证不能为空！");
				return;*/
				fd.append("file",null);
			}
			
			var clauseItem=$("input[name='serialNumClause']:checked").val();
			if($.trim(clauseItem)!=''){
				fd.append('clauseSettlementSerial',$("input[name='serialNumClause']:checked").val()); 
			}else{
				toastr.warning("至少选择一个结算条款！");
				return;
			}
			fd.append('serialNum',$scope.pay.serialNum);
			fd.append('paymentPlanSerial',$scope.pay.paymentPlanSerial);
			fd.append('paymentNum',$scope.pay.paymentNum); 
			fd.append('paymentPlanNum',$scope.pay.paymentPlanNum); 
			fd.append('orderSerial',$scope.pay.orderSerial); 
			fd.append('paymentStyle',$scope.pay.paymentStyle); 
			if(paymentAmount!=null){
				fd.append('paymentAmount',paymentAmount);	
			}else{
				fd.append('paymentAmount',$scope.pay.paymentAmount);
			}
			
			if(buyComId!=null){
				fd.append('buyComId',buyComId);
			}else{
				fd.append('buyComId',$scope.pay.buyComId);
			}
			fd.append('paymentType',$scope.pay.paymentType);
			fd.append('billStyle',$("input[name='billStyle']:checked").val()); 
			fd.append('isBill',$("input[name='isBill']:checked").val());
			fd.append('invoiceSerial',$scope.pay.invoiceSerial); 
			fd.append('applicant',$scope.pay.applicant);
			fd.append('applyDate',$scope.pay.applyDate); 
			fd.append('remark',$scope.pay.remark);
			fd.append('paymentVoucher',$scope.pay.paymentVoucher); 
			$http({
				method:'POST',
				url:"rest/pay/savePaymentRecord",
				data: fd,
				headers: {'Content-Type':undefined}
			})   
			.success( function ( response )
					{
				//上传成功的操作
				toastr.success("保存应收款数据成功！");
				$state.go('gatheringMoneyRecord');
					});
		}
	}

	$scope.jumpToGetPayInfo  = function(serialNum) {
    	$state.go('viewGatheringMoney',{serialNum:serialNum});
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



	//导出付款
	$scope.exportPay = function(){
		handle.blockUI("正在导出数据，请稍后"); 
		window.location.href=$rootScope.basePath+"/rest/pay/exportGatheringMoney";
		handle.unblockUI(); 
	}

	//付款列表
	var table;
	var tableAjaxUrl = "rest/pay/findAllGatheringMoneyRecord";
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
						order : [ [ 1, "asc" ] ],// 默认排序列及排序方式
						bRetrieve : true,
						"sScrollX": "100%",
						"sScrollXInner": "110%",
						"bScrollCollapse": true,
						// searching: true,//是否过滤检索
						// ordering: true,//是否排序
						lengthMenu : [
						              [ 5, 10, 15,15, 30, -1 ],
						              [ 5, 10, 15, 15,30, "All" ] ],
						              pageLength : 10,// 每页显示数量
						              processing : true,// loading等待框
						              // serverSide: true,
						              ajax: tableAjaxUrl,//加载数据中user表数据
						              "aoColumns": [
						                            { mData: 'serialNum'},
						                            { mData: 'paymentNum' },
						                            { mData: 'paymentPlanNum' },
						                            { mData: 'orderNum' },
						                            { mData: 'paymentType' },
						                            { mData: 'paymentStyle'},
						                            { mData: 'billStyle'},
						                            { mData: 'invoiceSerial'},
						                            { mData: 'applicant'},
						                            { mData: 'applyDate' },
						                            { mData: 'paymentAmount' },
						                            { mData: 'buyComId' },
						                            { mData: 'status',
						                            	mRender:function(data){
						                            		if(data!=""&&data!=null){
						                            			if(data=='0'){
						                            				return '初始';
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
						                            		return '<a  data-toggle="modal" ng-click="jumpToGetPayInfo(\''+row.serialNum+'\')" ">'+data+'</a>';
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


	//采购订单列表
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
			order: [[1, "asc"]],// 默认排序列及排序方式
			searching: true,// 是否过滤检索
			ordering:  true,// 是否排序
			lengthMenu: [[5, 10, 15, 30, -1], [5, 10, 15, 30, "All"]],
			pageLength: 5,// 每页显示数量
			processing: true,// loading等待框
			ajax:"rest/order/findOrderList?type=sale",// 加载数据中
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
				toastr.warning('请选择一个物料！');return;
			}
			/* alert(ids);*/

			$scope.getSaleOrderInfo(ids);

			ids = '';
			$('#basicMaterielInfo').modal('hide');// 删除成功后关闭模态框
			$(".modal-backdrop").remove();
		};
		

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
	};

	//删除
	$scope.del = function() {
		var ids = '';
		// Iterate over all checkboxes in the table
		table.$('input[type="checkbox"]').each(function() {
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
							$state.go('gatheringMoneyRecord',{},{reload:true}); // 重新加载datatables数据
						},
						function(errResponse) {
							/*console.error('Error while deleting Users');*/
							alert(123);
						}

				);
			}
		}								
	};


	//查看上传的文件
	$scope.download=function(name) {
		/* $http({
	                url: '/rest/contract/resourceDownload',
	                method: "POST",
	                data: $.param({
	                }),
	                headers: {'Content-Type': 'application/x-www-form-urlencoded'},
	                responseType: 'arraybuffer'
	            }).success(function (data, status, headers, config) {
	                var blob = new Blob([data], {type: "application/vnd.ms-excel"});
	                saveAs(blob, [headers('Content-Disposition').replace(/attachment;fileName=/,"")]);
	            }).error(function (data, status, headers, config) {
	                //upload failed
	            });*/
		window.open($rootScope.basePath+"/uploadAttachFiles/"+name);
	};


	/*function(name){
				 var deferred = $q.defer();  
		    	ContractService.downLoad(name).then(
		    			toastr.success("下载成功")
		    	);
		    	$http.get($rootScope.basePath + "/rest/contract/resourceDownload").success(function (data) {  
		    		alert(data);
		    		deferred.resolve(data); 
		        })
		  }*/



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

				paymentNum:{required:"付款编号不能为空！",rangelength:jQuery.validator.format("付款编号位数必须在{0}到{1}字符之间！")},
				paymentPlanNum:{required:"付款计划编号不能为空！",rangelength:jQuery.validator.format("付款计划编号位数必须在{0}到{1}字符之间！")},
				orderSerial:{required:"关联订单不能为空！"},
				paymentType:{required:"付款类型不能为空！"},
				paymentStyle:{required:"支付类型不能为空！"},
				applicant:{required:"申请人不能为空！"},
				applyDate:{required:"申请日期不能为空！"},
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
				paymentNum:{required:true,
					rangelength:[3,20]
				},
				paymentPlanNum:{required:true,
					rangelength:[3,20]
				},

				orderSerial:{required:true,
				},

				paymentType:{required:true,
				},

				paymentStyle:{required:true,
				},
				applicant:{required:true,
				},
				applyDate:{required:true,
				},
				signer:{required:true,
				}, 
				files:{required:true,
				},
				file:{required:true,
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


}]);


