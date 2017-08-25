/* Setup blank page controller */
angular
		.module('MetronicApp')
		.controller(
				'StockInOutController',
				[
						'$rootScope',
						'$scope',
						'$state',
						'$compile',
						'$http',
						'$location',
						'$stateParams',
						'settings',
						'StockInOutService',
						function($rootScope, $scope, $state, $compile,$http,$location,$stateParams,settings,
								StockInOutService) {
							$scope
									.$on(
											'$viewContentLoaded',
											function() {
												// initialize core components
												 handle = new pageHandle();
												App.initAjax();
												if($location.path()=="/addOrEditStockInOutCheck"&&$stateParams.inOrOut!='showOut'){
													$('.date-picker').datepicker({
														rtl: App.isRTL(),
														orientation: "left",
														autoclose: true,
														language:"zh-CN"
										        	})
										        	debugger;
													$scope.inOrOut=$stateParams.inOrOut;
												if($scope.inOrOut.length>3){
													getStockInOutCheckInfo($stateParams.inOrOut.substring(0,32));
												}
										 		}else if($location.path()=="/stockInOutCheckView"){
										 			debugger;
										 			$scope.inOrOut=$stateParams.inOrOut;
										 			getStockInOutCheckInfo($stateParams.inOrOut.substring(0,32));//查看出入库检验详情页面
									 		}else{
									 			if($stateParams.inOrOut=='showOut'&&$scope.isInit!='1'){
									 				debugger;
									 				$("#in").removeClass("active");
									 				$("#out").addClass("active");
									 				$scope.isInit='1';
									 				loadStockInOutCheckTable('out');
									 				//加载入库检验列表
									 			}else{
									 				$("#in").addClass("active");
									 				$("#out").removeClass("active");
									 				loadStockInOutCheckTable('in');//加载入库检验列表
									 			}
										 		}
												selectMaterielStock();//选择物料表格初始化
												// set default layout mode
												$rootScope.settings.layout.pageContentWhite = true;
												$rootScope.settings.layout.pageBodySolid = false;
												$rootScope.settings.layout.pageSidebarClosed = false;
											});
							
							//初始化toastr开始
							toastr.options = {
									"closeButton" : true,
									"debug" : false,
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
							//初始化toastr结束

							// 构建datatables开始***************************************
							var tableAjaxUrl ;
							 var table,Intable,Outtable;
			function loadStockInOutCheckTable(judgeString){
							var a = 0;
							tableAjaxUrl= "rest/stockInOut/getStockInOutCheckList?inOrOut="+judgeString
							App.getViewPort().width < App
									.getResponsiveBreakpoint("md") ? $(
									".page-header").hasClass(
									"page-header-fixed-mobile")
									&& (a = $(".page-header").outerHeight(!0))
									: $(".page-header").hasClass(
											"navbar-fixed-top") ? a = $(
											".page-header").outerHeight(!0)
											: $("body").hasClass(
													"page-header-fixed")
													&& (a = 64);

											Intable = $("#sample_in")
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
												/*fixedHeader : {// 固定表头、表底
													header : !0,
													footer : !0,
													headerOffset : a
												},*/
												// select: true,行多选
												order : [ [ 1, "asc" ] ],// 默认排序列及排序方式
												bRetrieve : true,
												// searching: true,//是否过滤检索
												// ordering: true,//是否排序
												lengthMenu : [
														[ 5, 10, 15, 30, -1 ],
														[ 5, 10, 15, 30, "All" ] ],
												pageLength : 10,// 每页显示数量
												processing : true,// loading等待框
												// serverSide: true,
												ajax : tableAjaxUrl,// 加载数据中检验表数据

												"aoColumns" : [
													{
													mData : 'serialNum'
													},
													 {
														mData : 'checkNum'
													},  {
														mData : 'takeDeliverNum'
													},{
														mData : 'relationBuyNum'
													}, {
														mData : 'materialNum'
													},{
														mData : 'qualifiedCount'
													}, {
														mData : 'unQualifiedCount'
													}, { 
														mData : 'checkDate',
														mRender:function(data){
						                            		if(data!=""&&data!=null){
						                            			return timeStamp2ShortString(data);
						                            		}else{
						                            			return "";
						                            		}
						                            	}
													}, {
														mData : 'checker'
													},{
														mData : 'status'
													}
													],
												'aoColumnDefs' : [ {
													'targets' : 0,
													'searchable' : false,
													'orderable' : false,
													'className' : 'dt-body-center',
													'render' : function(data,
															type, full, meta) {
														return '<input type="checkbox" id="'+data+'" name="id[]" value="'
																+ $('<div/>')
																		.text(
																				data)
																		.html()
																		+ '" data-check="false"  >';
													},"createdCell": function (td, cellData, rowData, full, col) {
														 $compile(td)($scope);
												    }
												},{
													'targets' : 1,
													'render' : function(data,
															type, row, meta) {
														return '<a   ng-click="showStockInOutCheckInfo(\''+row.serialNum+'\',\''+judgeString+'\')">'+data+'</a>';
														//return data;
													},"createdCell": function (td, cellData, rowData, row, col) {
														 $compile(td)($scope);
												    }
												},{
													'targets' : 9,
													'render' : function(data,
															type, row, meta) {
														var statusIcon='';//状态
				 	    								if(row.status==0){
				 	    									statusIcon = '<span class="label label-sm label-success"  >待检验</span> '
				 	    								}else if(row.status==1){
				 	    									statusIcon = '<span class="label label-sm label-success">待审批</span> '
				 	    								}else if(row.status==2){
				 	    									statusIcon = '<span class="label label-sm label-success">已检验</span> '
				 	    								}
				 	    								return statusIcon ;
													}
												}  ],

											});
											Outtable = $("#sample_out")
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
														/*fixedHeader : {// 固定表头、表底
															header : !0,
															footer : !0,
															headerOffset : a
														},*/
														// select: true,行多选
														order : [ [ 1, "asc" ] ],// 默认排序列及排序方式
														bRetrieve : true,
														// searching: true,//是否过滤检索
														// ordering: true,//是否排序
														lengthMenu : [
																[ 5, 10, 15, 30, -1 ],
																[ 5, 10, 15, 30, "All" ] ],
														pageLength : 10,// 每页显示数量
														processing : true,// loading等待框
														// serverSide: true,
														ajax : tableAjaxUrl,// 加载数据中检验表数据

														"aoColumns" : [
																{
													mData : 'serialNum'
													},
													 {
														mData : 'checkNum'
													},  {
														mData : 'deliverNum'
													},{
														mData : 'relationSaleNum'
													}, {
														mData : 'materialNum'
													},{
														mData : 'qualifiedCount'
													}, {
														mData : 'unQualifiedCount'
													},  { 
														mData : 'checkDate',
														mRender:function(data){
						                            		if(data!=""&&data!=null){
						                            			return timeStamp2ShortString(data);
						                            		}else{
						                            			return "";
						                            		}
						                            	}
													}, {
														mData : 'checker'
													},{
														mData : 'status'
													}
															],
														'aoColumnDefs' : [ {
															'targets' : 0,
															'searchable' : false,
															'orderable' : false,
															'className' : 'dt-body-center',
															'render' : function(data,
																	type, full, meta) {
																return '<input type="checkbox" id="'+data+'" name="id[]" value="'
																		+ $('<div/>')
																				.text(
																						data)
																				.html()
																				+ '" data-check="false"  >';
															},"createdCell": function (td, cellData, rowData, full, col) {
																 $compile(td)($scope);
														    }
														},{
															'targets' : 1,
															'render' : function(data,
																	type, row, meta) {
																return '<a   ng-click="showStockInOutCheckInfo(\''+row.serialNum+'\',\''+judgeString+'\' )">'+data+'</a>';
																//return data;
															},"createdCell": function (td, cellData, rowData, row, col) {
																 $compile(td)($scope);
														    }
														},{
															'targets' : 9,
															'render' : function(data,
																	type, row, meta) {
																var statusIcon='';//状态
						 	    								if(row.status==0){
						 	    									statusIcon = '<span class="label label-sm label-success"  >待检验</span> '
						 	    								}else if(row.status==1){
						 	    									statusIcon = '<span class="label label-sm label-success">待审批</span> '
						 	    								}else if(row.status==2){
						 	    									statusIcon = '<span class="label label-sm label-success">已检验</span> '
						 	    								}
						 	    								return statusIcon ;
															}
														}  ],

													});
											if(judgeString=='in'){
												table=Intable;
											}else{
												table=Outtable;
											}
							// 构建datatables结束***************************************

							// 添加checkbox功能***************************************
							// Handle click on "Select all" control
							$('#example-select-'+judgeString+'-all').on(
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
							$('#sample_'+judgeString+' tbody')
									.on(
											'change',
											'input[type="checkbox"]',
											function() {
												// If checkbox is not checked
												if (!this.checked) {
													var el = $(
															'#example-select-'+judgeString+'-all')
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
							}
			
			$scope.showOut=function(judgeString){
				 $state.go('stockInOutCheck',{inOrOut:judgeString}); //显示出库检验
			}
							// 添加检验开始***************************************
			$scope.addStockInOutCheck = function(judgeString) {
				debugger;
				 $state.go('addOrEditStockInOutCheck',{inOrOut:judgeString}); 
			}
						$scope.editStockInOutCheck = function(){
							debugger;
							var stockInOutCheck=$scope.stockInOutCheck;
							$scope.stockInOutCheck=stockInOutCheck;
							$scope.stockInOutCheckView = false;
		        			$scope.stockInOutCheckAdd = false;
		        			$scope.stockInOutCheckEdit = false;
						}
						$scope.cancelEditStockInOutCheck = function(){
							debugger;
							getStockInOutCheckInfo($scope.stockInOutCheck.serialNum);
							$scope.stockInOutCheckView = true;
		        			$scope.stockInOutCheckAdd = true;
		        			$scope.stockInOutCheckEdit = true;
						}	
						
						function judgeIsExist (){//判断是否已有收货单/发货单相关的出入库检验
							var serialNum;
							if($scope.inOrOut=='in'){
								serialNum=$scope.stockInOutCheck.takeDeliverSerial+"in";
							}else{
								serialNum=$scope.stockInOutCheck.deliverSerial+"out";
							}
							StockInOutService.judgeIsExistBySerialNum(serialNum)
							.then(
									function(data) {debugger;
										if(data=='1'){
											if($scope.inOrOut=='in'){
											toastr.warning("该收货单已建入库检验！");
											}else{
												toastr.warning("该发货单已建出库检验！");	
											}
											return ;
										}
										StockInOutService.saveStockInOutCheck($scope.stockInOutCheck)
										.then(
												function(data) {debugger;
													toastr.success("保存检验数据成功！");
													$scope.stockInOutCheck = data;
								        			$scope.stockInOutCheckView = true;
								        			$scope.stockInOutCheckAdd = true;
								        			$scope.stockInOutCheckEdit = false;
								        			$(".alert-danger").hide();
												},
												function(errResponse) {
													toastr.warning("保存失败！");
													console
															.error('Error while creating User');
												}
										);
									}
							);	
						}
								$scope.saveStockInOutCheck= function() {
									debugger;
									$scope.stockInOutCheck.deliverSerial=$("#deliverSerial").val();
									$scope.stockInOutCheck.takeDeliverSerial=$("#takeDeliverSerial").val();
									if($('#stockInOutCheckForm').valid()){//表单验证通过则执行添加功能
										 judgeIsExist ();
									}
							};	
							// 添加检验结束***************************************
							
							// 修改检验开始***************************************							
							$scope.toEditStockInOutPage = function(judgeString) {//弹出框修改检验信息
								debugger;
								var id_count = table.$('input[type="checkbox"]:checked').length;
								if(id_count==0){
									toastr.warning("请选择一条数据进行编辑");
								}else if(id_count>1){
									toastr.warning("只能选择一条数据进行编辑");
								}else{
									var serialNum = table.$('input[type="checkbox"]:checked').val();
									$state.go("addOrEditStockInOutCheck",{inOrOut:serialNum+judgeString});
								}
							};
							// 修改检验结束***************************************							

							// 删除检验开始***************************************							
							$scope.delStockInOutCheck = function(judString) {
								debugger;
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
									$('#delStock'+judString+'CheckModal').modal('show');// 打开确认删除模态框
									
									$scope.confirmDelStockInOutCheck = function() {										
										StockInOutService
												.delStockInOutChecks(ids)
												.then(
														function(data) {
															$('#delStock'+judString+'CheckModal').modal(
																	'hide');// 删除成功后关闭模态框
															toastr.success("删除成功！");
															table.ajax.reload(); // 重新加载datatables数据
														},
														function(errResponse) {
															console
																	.error('Error while deleting Users');
														}

												);
									}
								}								
							};
							$scope.showStockInOutCheckInfo=function(serialNum,judgeString){
								debugger;
								 $state.go('stockInOutCheckView',{inOrOut:serialNum+judgeString},{reload:true}); 
								
							}
							  var selectIndex;
					 	       $scope.selectDeliverInfo = function(index){
					 	    	  selectIndex = index;
					 	       }
						     var selectMaterielTable;
					 	       var selectMaterielStock = function() {
					 	    	   debugger;
					 	                a = 0;
					 	                App.getViewPort().width < App.getResponsiveBreakpoint("md") ? $(".page-header").hasClass("page-header-fixed-mobile") && (a = $(".page-header").outerHeight(!0)) : $(".page-header").hasClass("navbar-fixed-top") ? a = $(".page-header").outerHeight(!0) : $("body").hasClass("page-header-fixed") && (a = 64);
					 	               selectMaterielTable = $("#select_sample_2")
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
//					 	                    serverSide: true,
					 	                    ajax: "rest/materiel/findMaterielList",//加载数据中
					 	                    "aoColumns": [
					 	                                  { mData: 'serialNum' },
					 	                                  { mData: 'materielNum' },
					 	                                  { mData: 'materielName' },
					 	                                  { mData: 'specifications' },
					 	                                  { mData: 'unit' },
					 	                                  { mData: 'parentMateriel' },
					 	                                  { mData: 'type' },
					 	                                  { mData: 'productionPlace' },
					 	                                  { mData: 'brand' },
					 	                                  { mData: 'brand' },
					 	                                  { mData: 'versionNO' },
					 	                                  { mData: 'status' }
					 	                            ],
					 	                   'aoColumnDefs' : [ {
					 	    							'targets' : 0,
					 	    							'searchable' : false,
					 	    							'orderable' : false,
					 	    							
					 	    							'render' : function(data,
					 	    									type, row, meta) {
					 	    								return '<input type="radio" ng-click="selectMaterialForStock(\''+data+'\',\''+row.materielName+'\',\''+row.materielNum+'\',\''+row.specifications+'\')" name="serialNum[]" value="'
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
					 	    							'render' : function(data,
					 	    									type, row, meta) {
					 	    								var bomIcon='';//bom图标
					 	    								if(row.isBOM==1){
					 	    									bomIcon = '<span class="label label-sm label-success">B</span> '
					 	    								}
					 	    								return bomIcon + data;
					 	    							}

					 	    						},{
					 	    							'targets' : 5,
					 	    							
					 	    							'render' : function(data,
					 	    									type, full, meta) {
					 	    								if(data==null){
					 	    									return  ''
					 	    								}else{
					 	    									return  data.materielName
					 	    								}
					 	    							}
					 	    						} ]

					 	                }).on('order.dt',
					 	                function() {
					 	                    console.log('排序');
					 	                })
					 	            };
						$scope.selectMaterialForStock=function(serialNum,materielName,materielNum,specifications){
							debugger;
							$scope.row = {};
		 	            	$scope.row.serialNum = serialNum;//物料流水
		 	            	$scope.row.materielName=materielName;//物料名称
		 	            	$scope.row.materielNum=materielNum;//物料编号
		 	            	$scope.row.specifications=specifications;//规格型号
						}
						   // 确认选择开始***************************************
		 	    		$scope.confirmSelect = function() {
		 	    			var ids = '';
		 	    			// Iterate over all checkboxes in the table
		 	    			selectMaterielTable.$('input[type="radio"]').each(
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
		 	    			//为前台五个参数赋值
		 	    			if($scope.inOrOut=='in'){
		 	    				$("#deliverSerial").val('111111');//发货单流水
			 	            	$("#takeDeliverSerial").val($scope.row.serialNum);//收货单流水
			 	            	$("#takeDeliverNum").val('td');//收货单号
			 	            	$("#relationBuyNum").val('b');//采购单号
		 	    			}else{
		 	    				$("#deliverSerial").val($scope.row.serialNum);//发货单流水
			 	            	$("#takeDeliverSerial").val('111111') ;//收货单流水
			 	           	$("#deliverNum").val('d');//发货单号
		 	            	$("#relationSaleNum").val('s');//销售单号
		 	    			}
		 	    			$("#comName").val('公司');
		 	            	$('#basicMaterielInfo').modal('hide');// 选择成功后关闭模态框
		 	    			$(".modal-backdrop").remove();
		 	    		};
							// 页面加载完成后调用，验证输入框
							$scope.$watch('$viewContentLoaded', function() {  
								var e = $("#stockInOutCheckForm"),
						        r = $(".alert-danger", e),
						        i = $(".alert-success", e);
						        e.validate({
						            errorElement: "span",
						            errorClass: "help-block help-block-error",
						            focusInvalid: !1,
						            ignore: "",
						            messages: {
						            	checkNum:{required:"检验编号不能为空！"},
						            	takeDeliverNum:{required:"收货单号不能为空！"},
						            	deliverNum:{required:"发货单号不能为空！"},
						            	checkParty:{required:"检验方不能为空！"},
						            	checkDate:{required:"检验日期不能为空！"},
						            	checker: { required:"检验员不能为空！"},
						            	contactNum:{required:"联系电话不能为空！",digits:"请输入正确的联系电话, 必须为数字！",rangelength:jQuery.validator.format("电话必须在{0}到{1}位数字之间！")}
						            },
						            rules: {
						            	checkNum:{required:true},
						            	takeDeliverNum:{required:true},
						            	deliverNum:{required:true},
						            	checkParty:{required:true,digits:true},
						            	checkDate:{required:true},
						            	checker:{required:true},
						            	contactNum:{required:true,digits:true,rangelength:[7,20]}
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
						        })   							}); 
							
							
							 function getStockInOutCheckInfo(serialNum){//获取出入库检验信息
						    	   if(!handle.isNull(serialNum)){
						    		   debugger;
						    			 var promise =StockInOutService .selectDetailBySerialNum(serialNum);
						 	        	promise.then(function(data){
						 	        		  debugger;
						 	        			 $scope.stockInOutCheck = data.stockInOutCheck;
						 	        			 $scope.stockInOutCheck.checkDate=timeStamp2ShortString(data.stockInOutCheck.checkDate);
						 	        			 $scope.materials=null;
						 	        			 if($stateParams.inOrOut.indexOf("in")>-1){
						 	        				 $("#takeDeliverSerial").val(data.stockInOutCheck.takeDeliverSerial);
						 	        			 }else{
						 	        				 $("#deliverSerial").val(data.stockInOutCheck.deliverSerial);
						 	        			 }
						 	        			
						 	            },function(data){
						 	               //调用承诺接口reject();
						 	            });
						    		 }
						       }
							 function getStockDetailInfo(serialNum){//查看库位详情
						    	   if(!handle.isNull(serialNum)){
						    		   debugger;
						    			 var promise =StockService .selectDetailBySerialNum(serialNum);
						 	        	promise.then(function(data){
						 	        		  debugger;
						 	        			 $scope.stock = data.stock;
						 	            },function(data){
						 	               //调用承诺接口reject();
						 	            });
						    		 }
						       }
							       /**
							        * 下载EXCEL模板
							        */
							       $scope.downloadImportTemp = function(){
							    	   window.location.href=$rootScope.basePath+"/rest/stock/downloadImportTemp";
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
							    	   	var promise = StockService.uploadExcel();
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
							       $scope.exportStock = function(){
								    	 handle.blockUI("正在导出数据，请稍后"); 
								    	 window.location.href=$rootScope.basePath+"/rest/stock/exportStock";
								    	 handle.unblockUI(); 
								       }
								       
							       $('#import').on('hide.bs.modal', function (e) { 
							    	   $("#resetFile").trigger("click");
							       })
						} ]);
