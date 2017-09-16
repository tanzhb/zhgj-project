/* Setup blank page controller */
angular
		.module('MetronicApp')
		.controller(
				'InvoiceController',
				[
						'$rootScope',
						'$scope',
						'$state',
						'$compile',
						'$http',
						'$filter',
						'$location',
						'$stateParams',
						'settings',
						'InvoiceService',
						'FileUploader',
						function($rootScope, $scope, $state, $compile,$http,$filter,$location,$stateParams,settings,
								InvoiceService,FileUploader) {
							$scope
									.$on(
											'$viewContentLoaded',
											function() {
												// initialize core components
												 handle = new pageHandle();
												App.initAjax();
												if($location.path()=="/addOrEditInvoice"&&$stateParams.inOrOut!='showout'){
													$('.date-picker').datepicker({
														rtl: App.isRTL(),
														orientation: "left",
														autoclose: true,
														language:"zh-CN"
										        	})
										        	debugger;
													$scope.inOrOut=$stateParams.inOrOut;
												if($scope.inOrOut.length>3){
													getInvoiceInfo($stateParams.inOrOut);
												}
										 		}else if($location.path()=="/invoiceView"){
										 			debugger;
										 			$scope.inOrOut=$stateParams.inOrOut;
										 			getInvoiceInfo($stateParams.inOrOut+"view");//查看发票详情
									 		}else if(($location.path()=="/invoice")){
									 			debugger;
									 			if($stateParams.inOrOut=='showout'){
									 				debugger;
									 				$("#in").removeClass("active");
									 				$("#out").addClass("active");
									 				$("#tab_in").removeClass("active");
									 				$("#tab_out").addClass("active");
									 				loadInvoiceOutTable();
									 				//加载进销票列表
									 			}else{
									 				$("#in").addClass("active");
									 				$("#out").removeClass("active");
									 				$("#tab_in").addClass("active");
									 				$("#tab_out").removeClass("active")
									 				loadInvoiceInTable();//加载销项票列表
									 			}
										 		}
											
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
							 var table ;
									
			function loadInvoiceInTable(){
							var a = 0,judgeString='in';
							tableAjaxUrl= "rest/invoice/getInvoiceList?inOrOut="+judgeString
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
											
											table = $("#sample_in")
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
														ajax : tableAjaxUrl,// 加载数据中发票表数据

														"aoColumns" : [
															{
															mData : 'serialNum',
							                            	  mRender : function(
																		data,
																		type,
																		row,
																		meta) {
																	return "<label class='mt-checkbox mt-checkbox-single mt-checkbox-outline'>" +
																			"<input type='checkbox' class='checkboxes' value='"+data+"'/>" +
																			"<span></span></label>";
																}
															},{
																mData : 'invoiceNum'
															},  {
																mData : 'invoiceType'
															},{
																mData : 'relationBuyOrSaleNum'
															}, {
																mData : 'supplyComId'
															},{
																mData : 'invoiceAmount'
															}, {
																mData : 'invoiceCount'
															}, { 
																mData : 'billingDate',
																mRender:function(data){
								                            		if(data!=""&&data!=null){
								                            			return timeStamp2ShortString(data);
								                            		}else{
								                            			return "";
								                            		}
								                            	}
															}, {
																mData : 'receiptDate',//
																mRender:function(data){
								                            		if(data!=""&&data!=null){
								                            			return timeStamp2ShortString(data);
								                            		}else{
								                            			return "";
								                            		}
								                            	}
															},{
																mData : 'submitter'
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
																return '<input type="checkbox" id="'+data+'" name="in" value="'
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
																return '<a   ng-click="showInvoiceInfo(\''+row.serialNum+'\',\''+judgeString+'\')">'+data+'</a>';
																//return data;
															},"createdCell": function (td, cellData, rowData, row, col) {
																 $compile(td)($scope);
														    }
														},{
															'targets' : 10,
															'render' : function(data,
																	type, row, meta) {
																var statusIcon='';//状态
						 	    								if(row.status==0){
						 	    									statusIcon = '<span class="label label-sm label-success"  >待收票</span> '
						 	    								}else if(row.status==1){
						 	    									statusIcon = '<span class="label label-sm label-success">已收票</span> '
						 	    								}
						 	    								return statusIcon ;
															}
														}  ],
													});
											
											$("#sample_in").find(".group-checkable").change(function() {
										        var e = jQuery(this).attr("data-set"),
										        t = jQuery(this).is(":checked");
										        jQuery(e).each(function() {
										            t ? ($(this).prop("checked", !0), $(this).parents("tr").addClass("active")) : ($(this).prop("checked", !1), $(this).parents("tr").removeClass("active"))
										        })
										    }),
										    $("#sample_in").on("change", "tbody tr .checkboxes",
										    function() {
										        $(this).parents("tr").toggleClass("active")
										    })
										   return table;
											/*// 添加checkbox功能***************************************
											// Handle click on "Select all" control
											$('#example-select-'+judgeString+'-all').on(
													'click',
													function() {
														// Check/uncheck all checkboxes in the
														// table
														var rows = table.rows({
															'search' : 'applied'
														}).nodes();
														$('input[name="'+judgeString+'"]', rows).prop(
																'checked', this.checked);
													});

											// Handle click on checkbox to set state of "Select
											// all" control
											$('#sample_'+judgeString+' tbody')
													.on(
															'change',
															'input[name="'+judgeString+'"]',
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
*/							// 构建datatables结束***************************************
							}
			
			function loadInvoiceOutTable(){
				var a = 0,judgeString='out';
				tableAjaxUrl= "rest/invoice/getInvoiceList?inOrOut="+judgeString
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
				table = $("#sample_out")
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
							ajax : tableAjaxUrl,// 加载数据中发票表数据
							"aoColumns" : [
											{
											mData : 'serialNum',
			                            	  mRender : function(
														data,
														type,
														row,
														meta) {
													return "<label class='mt-checkbox mt-checkbox-single mt-checkbox-outline'>" +
															"<input type='checkbox' class='checkboxes' value='"+data+"'/>" +
															"<span></span></label>";
												}
											},{
												mData : 'invoiceNum'
											},  {
												mData : 'invoiceType'
											},{
												mData : 'relationBuyOrSaleNum'
											}, {
												mData : 'buyComId'
											},{
												mData : 'invoiceAmount'
											}, {
												mData : 'invoiceCount'
											}, { 
												mData : 'billingDate',
												mRender:function(data){
				                            		if(data!=""&&data!=null){
				                            			return timeStamp2ShortString(data);
				                            		}else{
				                            			return "";
				                            		}
				                            	}
											}, {
												mData : 'receiptDate',//
												mRender:function(data){
				                            		if(data!=""&&data!=null){
				                            			return timeStamp2ShortString(data);
				                            		}else{
				                            			return "";
				                            		}
				                            	}
											},{
												mData : 'submitter'
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
												return '<input type="checkbox" id="'+data+'" name="in" value="'
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
												return '<a   ng-click="showInvoiceInfo(\''+row.serialNum+'\',\''+judgeString+'\')">'+data+'</a>';
												//return data;
											},"createdCell": function (td, cellData, rowData, row, col) {
												 $compile(td)($scope);
										    }
										},{
											'targets' : 10,
											'render' : function(data,
													type, row, meta) {
												var statusIcon='';//状态
		 	    								if(row.status==0){
		 	    									statusIcon = '<span class="label label-sm label-success"  >待收票</span> '
		 	    								}else if(row.status==1){
		 	    									statusIcon = '<span class="label label-sm label-success">已收票</span> '
		 	    								}
		 	    								return statusIcon ;
											}
										}  ],
							
						});
				$("#sample_out").find(".group-checkable").change(function() {
			        var e = jQuery(this).attr("data-set"),
			        t = jQuery(this).is(":checked");
			        jQuery(e).each(function() {
			            t ? ($(this).prop("checked", !0), $(this).parents("tr").addClass("active")) : ($(this).prop("checked", !1), $(this).parents("tr").removeClass("active"))
			        })
			    }),
			    $("#sample_out").on("change", "tbody tr .checkboxes",
			    function() {
			        $(this).parents("tr").toggleClass("active")
			    })
			   return table;
			/*	// 添加checkbox功能***************************************
				// Handle click on "Select all" control
				$('#example-select-'+judgeString+'-all').on(
						'click',
						function() {
							// Check/uncheck all checkboxes in the
							// table
							var rows = table.rows({
								'search' : 'applied'
							}).nodes();
							$('input[name="'+judgeString+'"]', rows).prop(
									'checked', this.checked);
						});

				// Handle click on checkbox to set state of "Select
				// all" control
				$('#sample_'+judgeString+' tbody')
						.on(
								'change',
								'input[name="'+judgeString+'"]',
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
*/				// 构建datatables结束***************************************
				}
			var  materielInTable,materielOutTable;
			function loadMaterielInTable(orderSerial,serialNum){
				var a = 0,judgeString='in';
				if($scope.invoice.serialNum==undefined||$scope.invoice.orderSerial!=orderSerial){
					orderSerial=orderSerial+'no';
				}else{
					orderSerial=orderSerial+'no'+$scope.invoice.serialNum;
				}
				 if(materielInTable!=undefined){
					 materielInTable.destroy();
		 	    	 }
				tableAjaxUrl= "rest/invoice/getMaterielList?orderSerial="+orderSerial
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
								
								materielInTable = $("#sample_inm")
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
											ajax : tableAjaxUrl,// 加载数据中发票表数据

											"aoColumns" : [
											 /*  {
											    mData : 'serialNum'
											   },*/
												{
												mData : 'materielNum'
												},{
													mData : 'materielName'
												},  {
													mData : 'specifications'
												},{
													mData : 'unit'
												}, {
													mData : 'amount'
												},{
													mData : 'canBillAmount'
												}, {
													mData : 'billAmount'
												}, { 
													mData : 'orderUnitPrice',
													mRender:function(data){
					                            		if(data!=""&&data!=null){
					                            			return $filter('currency')(data,'￥');
					                            		}else{
					                            			return $filter('currency')(0,'￥');
					                            		}
					                            	}
												}, {
													mData : 'money'
												},{
													mData : 'status'
												}
												],
											'aoColumnDefs' : [  {
												'targets' : 6,
												'className' : 'dt-body-center',
												'render' : function(data,
														type, row, meta) {
													if(serialNum.indexOf("view")>-1){
														return data;
													}else{
														return '<input  type="text"  value="'+row.billAmount+'"      id="'+row.serialNum+'"  onchange="judgeNumber(\''+row.canBillAmount+'\',\''+row.serialNum+'\',\''+judgeString+'\')" />';
													}
												},"createdCell": function (td, cellData, rowData, row, col) {
													 $compile(td)($scope);
											    }
											} ,{
												'targets' : 8,
												'render' : function(data,
														type, row, meta) {
													return '<span id="money'+row.serialNum+'">'+$filter('currency')(data,'￥')+'</span>';
													//return data;
												},"createdCell": function (td, cellData, rowData, row, col) {
													 $compile(td)($scope);
											    }
											} ,{
												'targets' : 9,
												'searchable' : false,
												'orderable' : false,
												'render' : function(data,
														type, row, meta) {
													if(serialNum.indexOf("view")>-1){
														return "";
													}else{
													return '<a   id="save'+row.serialNum+'" ng-click="saveBillingRecord(\''+row.serialNum+'\',\''+judgeString+'\',\''+row.invoiceBillingRecordSerial+'\',\''+row.billAmount+'\',\''+row.orderUnitPrice+'\',\''+row.money+'\')">  <i class="fa fa-save" title="保存"></i> </a>&nbsp;<a   style="display:none"  id="edit'+row.serialNum+'"  ng-click="editBillingRecord(\''+row.serialNum+'\',\''+judgeString+'\')"><i class="fa fa-edit" title="编辑"></i></a>'
													+ '&nbsp;<a  id="cancel'+row.serialNum+'" ng-click="cancelEditBillingRecord(\''+row.serialNum+'\',\''+judgeString+'\',\''+row.billAmount+'\')"><i class="fa fa-undo"  title="取消"></i></a>';
													}//return data;
												},"createdCell": function (td, cellData, rowData, row, col) {
													 $compile(td)($scope);
											    }
											}  ],
										});
							
				// 构建datatables结束***************************************
				}

function loadMaterielOutTable(orderSerial,serialNum){
	var a = 0,judgeString='out';
	debugger;
	if($scope.invoice.serialNum==undefined||$scope.invoice.orderSerial!=orderSerial){
		orderSerial=orderSerial+'no';
	}else{
		orderSerial=orderSerial+'no'+$scope.invoice.serialNum;
	}
	 if(materielOutTable!=undefined){
		 materielOutTable.destroy();
	    	 }
	tableAjaxUrl= "rest/invoice/getMaterielList?orderSerial="+orderSerial
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
					materielOutTable = $("#sample_outm")
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
				ajax : tableAjaxUrl,// 加载数据中发票表数据
				"aoColumns" : [
				             /*  	{
				               	mData : 'serialNum'
				               	},*/
								{
								mData : 'materielNum'
								},{
									mData : 'materielName'
								},  {
									mData : 'specifications'
								},{
									mData : 'unit'
								}, {
									mData : 'amount'
								},{
									mData : 'canBillAmount'
								}, {
									mData : 'billAmount'
								}, { 
									mData : 'orderUnitPrice',
									mRender:function(data){
	                            		if(data!=""&&data!=null){
	                            			return $filter('currency')(data,'￥');
	                            		}else{
	                            			return $filter('currency')(0,'￥');
	                            		}
	                            	}
								}, {
									mData : 'money'
								},{
									mData : 'status'
								}
								],
							'aoColumnDefs' : [ {
								'targets' : 6,
								'className' : 'dt-body-center',
								'render' : function(data,
										type, row, meta) {
									if(serialNum.indexOf("view")>-1){
										return data;
									}else{
										return '<input  type="text"  value="'+row.billAmount+'"      id="'+row.serialNum+'"  onchange="judgeNumber(\''+row.canBillAmount+'\',\''+row.serialNum+'\',\''+judgeString+'\')" />';
									}
								
									//return data;
								},"createdCell": function (td, cellData, rowData, row, col) {
									 $compile(td)($scope);
							    }
							},{
								'targets' : 8,
								'render' : function(data,
										type, row, meta) {
									return '<span id="money'+row.serialNum+'">'+$filter('currency')(data,'￥')+'</span>';
									//return data;
								},"createdCell": function (td, cellData, rowData, row, col) {
									 $compile(td)($scope);
							    }
							} ,{
								'targets' : 9,
								'render' : function(data,
										type, row, meta) {
									if(serialNum.indexOf("view")>-1){
										return "";
									}else{
									return '<a   id="save'+row.serialNum+'" ng-click="saveBillingRecord(\''+row.serialNum+'\',\''+judgeString+'\',\''+row.invoiceBillingRecordSerial+'\',\''+row.billAmount+'\',\''+row.orderUnitPrice+'\',\''+row.money+'\')">  <i class="fa fa-save" title="保存"></i> </a>&nbsp;<a   style="display:none"  id="edit'+row.serialNum+'"  ng-click="editBillingRecord(\''+row.serialNum+'\',\''+judgeString+'\')"><i class="fa fa-edit" title="编辑"></i></a>'
									+ '&nbsp;<a  id="cancel'+row.serialNum+'" ng-click="cancelEditBillingRecord(\''+row.serialNum+'\',\''+judgeString+'\',\''+row.billAmount+'\')"><i class="fa fa-undo"  title="取消"></i></a>';
									}
									
								},"createdCell": function (td, cellData, rowData, row, col) {
									 $compile(td)($scope);
							    }
							}  ],
				
			});

	// ***************************************
	// 构建datatables结束***************************************
	}


$scope.saveBillingRecord=function (serialNum,judgeString,invoiceBillingRecordSerial,billAmount,orderUnitPrice,money){
	if($scope.invoice.serialNum==undefined&&judgeString=='in'){
		 toastr.warning("请先保存进项票信息！");
		 return;
	}
	if($scope.invoice.serialNum==undefined&&judgeString=='out'){
		 toastr.warning("请先保存销项票信息！");
		 return;
	}
	debugger;
	$scope.invoiceBillingRecord = {};
	$scope.invoiceBillingRecord.serialNum=invoiceBillingRecordSerial;
	$scope.invoiceBillingRecord.orderMaterielSerial=serialNum;
	$scope.invoiceBillingRecord.invoiceSerial=$scope.invoice.serialNum;
	$scope.invoiceBillingRecord.billCount=$("#"+serialNum).val();
	InvoiceService.saveInvoiceBillingRecord($scope.invoiceBillingRecord).then(
			function(data) {debugger;
				toastr.success("保存成功！");
				$("#"+serialNum).attr("readonly",true);
				$("#"+serialNum).css("border","none");
				$("#save"+serialNum).css("display","none");
				$("#edit"+serialNum).css("display","inline-block");
				$("#cancel"+serialNum).css("display","none");
				var nowMoney=Number($("#"+serialNum).val())*Number(orderUnitPrice);
				var billOrReceiptMoney =Number($scope.invoice.billOrReceiptMoney);
				$scope.invoice.billOrReceiptMoney=billOrReceiptMoney-Number(money)+nowMoney;
				$scope.invoice.capitalMoney=convertCurrency(billOrReceiptMoney-Number(money)+nowMoney);
				$("#money"+serialNum).html(nowMoney);
				
    			
			},
			function(errResponse) {
				toastr.warning("保存失败！");
				console
						.error('Error while creating User');
			}
	);
	
}

$scope.editBillingRecord=function (serialNum,judgeString){
	$("#"+serialNum).attr("readonly",false);
	$("#"+serialNum).css("border","1px solid");
	$("#save"+serialNum).css("display","inline-block");
	$("#edit"+serialNum).css("display","none");
	$("#cancel"+serialNum).css("display","inline-block");
	$("#"+serialNum).focus();
}
$scope.cancelEditBillingRecord=function (serialNum,judgeString,billAcount){
	$("#"+serialNum).attr("readonly",true);
	$("#"+serialNum).css("border","none");
	$("#save"+serialNum).css("display","none");
	$("#edit"+serialNum).css("display","inline-block");
	$("#cancel"+serialNum).css("display","none");
	$("#"+serialNum).val(billAcount);
}
			$scope.showOut=function(judgeString){
				 $state.go('invoice',{inOrOut:judgeString}); //切换tab
			}
							// 添加发票开始***************************************
			$scope.addInvoice = function(judgeString) {
				debugger;
				 $state.go('addOrEditInvoice',{inOrOut:judgeString}); 
			}
						$scope.editInvoice = function(){
							debugger;
							getInvoiceInfo($scope.inOrOut);
							$scope.invoiceView = false;
		        			$scope.invoiceAdd = false;
		        			$scope.invoiceEdit = false;
						}
						$scope.cancelEditInvoice = function(){
							debugger;
							getInvoiceInfo($scope.inOrOut);
							$scope.invoiceView = true;
		        			$scope.invoiceAdd = true;
		        			$scope.invoiceEdit = true;
						}	
						
								$scope.saveInvoice= function() {
									debugger;
									if($('#invoiceForm').valid()){//表单验证通过则执行添加功能
										debugger;
										if($scope.inOrOut.indexOf("in")>-1){
											$scope.invoice.supplyComId=$scope.invoice.comName;
											$scope.invoice.buyComId=null;
										}else{
											$scope.invoice.buyComId=$scope.invoice.comName;
											$scope.invoice.supplyComId=null;
										}
										InvoiceService.saveInvoice($scope.invoice).then(
															function(data) {debugger;
																toastr.success("保存发票数据成功！");
																$scope.invoice = data;
											        			$scope.invoiceView = true;
											        			$scope.invoiceAdd = true;
											        			$scope.invoiceEdit = true;
											        			$(".alert-danger").hide();
											        			
															},
															function(errResponse) {
																toastr.warning("保存失败！");
																console
																		.error('Error while creating User');
															}
													);
												}
									}
							// 添加发票结束***************************************
							
							// 修改发票开始***************************************							
							$scope.toEditInvoicePage = function(judgeString) {//跳转至修改发票信息页面
								debugger;
								var id_count = table.$('input[type="checkbox"]:checked').length;
								if(id_count==0){
									toastr.warning("请选择一条数据进行编辑");
								}else if(id_count>1){
									toastr.warning("只能选择一条数据进行编辑");
								}else{
									var serialNum = table.$('input[type="checkbox"]:checked').val();
									$state.go("addOrEditInvoice",{inOrOut:serialNum+judgeString});
								}
							};
							// 修改发票结束***************************************							

							// 删除发票开始***************************************							
							$scope.delInvoice = function(judString) {
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
									$('#delInvoice'+judString+'Modal').modal('show');// 打开确认删除模态框
									
									$scope.confirmDelInVoice = function() {										
										InvoiceService
												.delInvoices(ids)
												.then(
														function(data) {
															$('#delInvoice'+judString+'Modal').modal('hide');;// 删除成功后关闭模态框
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
							$scope.showInvoiceInfo=function(serialNum,judgeString){
								debugger;
								 $state.go('invoiceView',{inOrOut:serialNum+judgeString},{reload:true}); 
								
							}
							  var selectIndex,ajaxUrl;
					 	       $scope.selectBuyOrSaleOrderInfo = function(judgeString){
					 	    	  selectBuyOrSaleOrder(judgeString);
					 	       }
						     var selectBuyOrSaleOrderTable,aoColumnsForBuyOrSale,aoColumnDefsForBuyOrSale;
					 	       var selectBuyOrSaleOrder = function(	judgeString) {
					 	    		debugger;
					 	    	 if(selectBuyOrSaleOrderTable!=undefined){
					 	    		selectBuyOrSaleOrderTable.destroy();
					 	    	 }
					 	    	 if(judgeString=='buy'){
					 	    		 ajaxUrl="rest/order/findOrderList?type=buy";
					 	    		aoColumnsForBuyOrSale=  [
					 	    		                                    { mData: 'serialNum' },
					 	    		                                   { mData: 'orderNum' },
					 	    		                                   { mData: 'supplyComId' },
					 	    		                                   { mData: 'orderAmount' },
					 	    		                                   { mData: null },
					 	    		                                   { mData: 'deliveryMode' },
					 	    		                                   { mData: 'serviceModel' },
					 	    		                                   { mData: 'saleApplySerial' },
					 	    		                                   { mData: 'orderSerial' },
					 	    		                                   { mData: 'orderDate' }

					 	    		                             ],
					 	    		                 aoColumnDefsForBuyOrSale=[ {
					 	    							'targets' : 0,
					 	   							'searchable' : false,
					 	   							'orderable' : false,
					 	   							'render' : function(data,
					 	   									type, full, meta) {
					 	   								return '<input type="radio" id="'+data+'" ng-click="getBuyOrSaleOrderInfo(\''+data+"in"+'\',\''+full.orderNum+'\',\''+full.orderAmount+'\')" name="serialNum[]" value="'
					 	   													+ $('<div/>')
					 	   													.text(
					 	   															data)
					 	   													.html()
					 	   											+ '">';
					 	   							},
					 	   							"createdCell": function (td, cellData, rowData, row, col) {
					 	   								 $compile(td)($scope);
					 	   						       }
					 	   						} ];
					 	    	  }else if(judgeString=='sale'){
					 	    		 ajaxUrl="rest/order/findOrderList?type=sale";
					 	    		aoColumnsForBuyOrSale=[
					 	                                { mData: 'serialNum' },
					 	                               { mData: 'orderNum' },
					 	                               { mData: 'buyComId' },
					 	                              { mData: 'orderAmount' },
					 	                               { mData: null },
					 	                               { mData: 'deliveryMode' },
					 	                               { mData: 'serviceModel' },
					 	                               { mData: 'saleApplySerial' },
					 	                               { mData: 'orderSerial' },
					 	                               { mData: 'orderDate' }
					 	                         ];
					 	    		aoColumnDefsForBuyOrSale= [ {
										'targets' : 0,
										'searchable' : false,
										'orderable' : false,
										'render' : function(data,
												type, full, meta) {
											return '<input type="radio" id="'+data+'" ng-click="getBuyOrSaleOrderInfo(\''+data+"out"+'\',\''+full.orderNum+'\',\''+full.orderAmount+'\')" name="serialNum[]" value="'
																+ $('<div/>')
																.text(
																		data)
																.html()
														+ '">';
										},
										"createdCell": function (td, cellData, rowData, row, col) {
											 $compile(td)($scope);
									       }
									} ];
					 	    	  }
					 	    	   debugger;
					 	                a = 0;
					 	                App.getViewPort().width < App.getResponsiveBreakpoint("md") ? $(".page-header").hasClass("page-header-fixed-mobile") && (a = $(".page-header").outerHeight(!0)) : $(".page-header").hasClass("navbar-fixed-top") ? a = $(".page-header").outerHeight(!0) : $("body").hasClass("page-header-fixed") && (a = 64);
					 	               selectBuyOrSaleOrderTable = $("#select_sample_"+judgeString)
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
					 	                    ajax: ajaxUrl,//加载数据中
					 	                    "aoColumns": aoColumnsForBuyOrSale,
					 	                   'aoColumnDefs' :aoColumnDefsForBuyOrSale

					 	                }).on('order.dt',
					 	                function() {
					 	                    console.log('排序');
					 	                })
					 	            };
					 	            
					 	       	// 订单结算条款***************************************							
									$scope.judgeCount = function() {
										debugger;
										var id_count = $('input[type="checkbox"][name="select_all"]:checked').length;
										if(id_count==0){
											toastr.warning("请选择一条订单结算条款");
										}else if(id_count>1){
											toastr.warning("只能选择一条订单结算条款");
										}else{
											$scope.invoice.clauseSettlementSerial=$('input[type="checkbox"][name="select_all"]:checked').val();
										}
									};
						$scope.getBuyOrSaleOrderInfo=function(serialNum,orderNum,orderAmount){
							debugger;
							$scope.row = {};
		 	            	$scope.row.serialNum = serialNum;//发货单号流水/收货单号流水
		 	            //	$scope.row.orderAmount=orderAmount;//订单金额
		 	            	$scope.row.orderNum=orderNum;//订单编号
		 	            	getOrderInfoBySerialNum($scope.row.serialNum);//获取订单物料信息
		 	            
		 	            	
						}
					function getOrderInfoBySerialNum(serialNum){
						InvoiceService.getOrderInfoBySerialNum(serialNum).then(
								function(data) {debugger;
								if(serialNum.length>32){
									//$scope.row.orderMateriels=data.orderMateriels;
									$scope.row.orderInfo=data.orderInfo;
									$scope.row.orderAmount=data.orderInfo.orderAmount;
									$scope.row.currency=data.orderInfo.currency;
								}
								/*if(serialNum.indexOf("in")>-1){loadMaterielInTable(serialNum.substring(0,32),'no');
			 	            	}else{debugger;loadMaterielOutTable(serialNum.substring(0,32),'no');}*/
							},
							function(errResponse) {
								toastr.warning("获取失败！");
								console
										.error('Error while creating User');
							}
					);
					}
						   // 确认选择发货单开始***************************************
		 	    		$scope.confirmSelectBuyOrSaleOrderInfo = function(judgeString) {
		 	    			var ids = '';
		 	    			// Iterate over all checkboxes in the table
		 	    			selectBuyOrSaleOrderTable.$('input[type="radio"]').each(//[name="in[]"]
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
		 	    				if(judgeString=='buy'){
		 	    					toastr.warning('请选择一个采购单！');return;
		 	    				}else{
		 	    					toastr.warning('请选择一个销售单！');return;
		 	    				}
		 	        		}
		 	    			//为前台四个参数赋值
		 	    			debugger;
		 	    			$scope.invoice.orderSerial=$scope.row.orderInfo.serialNum;//订单流水
	 	    				$scope.invoice.orderAmount=$scope.row.orderAmount;//订单金额
	 	    				$scope.invoice.relationBuyOrSaleNum=$scope.row.orderNum;//订单编号
	 	    				$scope.invoice.currency=$scope.row.currency;
	 	    				
		 	    			if(judgeString=='buy'){
		 	    				loadMaterielInTable($scope.invoice.orderSerial,'no');
			 	            	$('#buyOrderInfo').modal('hide');// 选择成功后关闭模态框
		 	    			}else{
		 	    				loadMaterielOutTable($scope.invoice.orderSerial,'no');
		 	            	$('#saleOrderInfo').modal('hide');// 选择成功后关闭模态框
		 	    			}
		 	    			$(".modal-backdrop").remove();
		 	    		};  // 确认选择发货单结束***************************************
		 	    		jQuery.validator.addMethod("qualifiedNumCheck", function (value, element) {
		 	    			debugger;
		 					$(element).removeData();
		 					return this.optional(element) || Number($(element).data("acceptcount")) == NaN?false:(Number($(element).data("acceptcount"))-value>= 0);
		 				}, "合格数量不能超过实收数量");
		 	    		jQuery.validator.addMethod("deliverNumCheck", function (value, element) {
		 	    			debugger;
		 					$(element).removeData();
		 					return this.optional(element) || Number($(element).data("delivercount")) == NaN?false:(Number($(element).data("delivercount"))-value>= 0);
		 				}, "合格数量不能超过发货数量");
		 	    		
							// 页面加载完成后调用，验证输入框
							$scope.$watch('$viewContentLoaded', function() { 
								var  comName,relationBuyOrSaleNum,relationReceiveOrPayNum,receiptDate,approver,approvalDate,invoiceNum;
								if($scope.inOrOut!=undefined&&$scope.inOrOut.indexOf("in")>-1){
									invoiceNum={required:"进项发票单号不能为空！"};
									comName={required:"开票方不能为空！"};
									relationBuyOrSaleNum={required:"关联采购单号不能为空！"};
									receiptDate={};
								}else if($scope.inOrOut!=undefined&&$scope.inOrOut.indexOf("out")>-1){
									invoiceNum={required:"销项发票单号不能为空！"};
									comName={required:"收票方不能为空！"};
									relationBuyOrSaleNum={required:"关联销售单号不能为空！"};
									//receiptDate={required:true};
									receiptDate={};
								}
								var e = $("#invoiceForm"),
						        r = $(".alert-danger", e),
						        i = $(".alert-success", e);
						        e.validate({
						            errorElement: "span",
						            errorClass: "help-block help-block-error",
						            focusInvalid: !1,
						            ignore: "",
						            messages: {
						            	invoiceNum:invoiceNum,
						            	comName:comName,
						            	relationBuyOrSaleNum:relationBuyOrSaleNum,
						            	receiptDate:{required:" 申请开票日期不能为空！"},
						            	invoiceAmount:{required:"发票金额不能为空！",digits:"必须为数字！"},
						            	invoiceType: { required:"发票类型未选择！"},
						            	billingDate:{required:"开票日期不能为空！"},
						            	invoiceNO:{required:"发票号不能为空！"},
						            	 tel: { 
						                    	digits:'请输入正确的电话, 必须为数字！',
						                    	required:"电话不能为空！",
				                        	    rangelength:jQuery.validator.format("电话必须在{0}到{1}位数字之间！")
						                    },
						                    companyName:{required:"发票号不能为空！"},
						                    address:{required:"地址不能为空！"},
						                    bankName:{required:"开户银行不能为空！"},
						                    account:{required:"account不能为空！"},
						                    taxNum:{required:"企业纳税号不能为空！"}
						            	/*submitter:{required:"提交人不能为空！"},
						            	submitDate:{required:"提交日期不能为空！"},*/
						            
						            },
						            rules: {
						            	invoiceNum:{required:true},
						            	comName:{required:true},
						            	relationBuyOrSaleNum:{required:true},
						            	relationReceiveOrPayNum:{required:true},
						            	receiptDate:receiptDate,
						            	invoiceAmount:{required:true,digits:true},
						            	invoiceType:{required:true},
						            	billingDate:{required:true},
						            	invoiceNO:{required:true},
						            	 tel: {required:true,digits:true, rangelength:[7,20] }
						            	/*submitter:{required:true},
						            	submitDate:{required:true},*/
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
							
							
							 function getInvoiceInfo(serialNum){//获取出发票信息
						    	   if(!handle.isNull(serialNum)){
						    		   debugger;
						    			 var promise =InvoiceService .selectDetailBySerialNum(serialNum);
						 	        	promise.then(function(data){
						 	        		  debugger;
						 	        			 $scope.invoice = data.invoice;
						 	        			 $scope.invoice.receiptDate=timeStamp2ShortString(data.invoice.receiptDate);
						 	        			 $scope.invoice.billingDate=timeStamp2ShortString(data.invoice.billingDate);
						 	        			 $scope.invoice.submitDate=timeStamp2ShortString(data.invoice.submitDate);
						 	        			 $scope.invoice.approvalDate=timeStamp2ShortString(data.invoice.approvalDate);
						 	        			 $scope.invoice.capitalMoney=convertCurrency(data.invoice.billOrReceiptMoney);
						 	        			getOrderInfoBySerialNum($scope.invoice.orderSerial);//获取订单信息
							 	            	if(serialNum.indexOf("in")>-1){	loadMaterielInTable($scope.invoice.orderSerial,serialNum, $scope.invoice.serialNum);//加载发票物料
							 	            	}else{loadMaterielOutTable($scope.invoice.orderSerial,serialNum, $scope.invoice.serialNum);}
						 	        			
						 	        			
						 	            },function(data){
						 	               //调用承诺接口reject();
						 	            });
						    		 }
						       }
							 
							 //创建对象(上传附件开始)
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
									 if(response.filename==""){
										 toastr.error("上传失败！");
										 return;
									 }
									 toastr.success("上传成功！");
									 $scope.invoice.invoiceVoucher=response.filename;
								 }else{
									 toastr.error("上传失败！");
									 $scope.file_temp = "";
								 }
							 };
							 //上传失败
							 uploader.onErrorItem = function (fileItem, response, status, headers) {
								 toastr.error("上传失败！");
							 };

							 $scope.uploadFile = function(item){
								 $scope.file_temp = item;
							 }

							 $scope.up = function(file){
								 uploader.clearQueue();
								 uploader.addToQueue(file);
								 uploader.uploadAll();
							 }
							 $scope.downloadFile = function(obj){
								 if(!handle.isNull(obj)){
									 window.location.href= $rootScope.basePath+"/rest/fileOperate/downloadFile?fileName="+obj.file;
								 }else{
									 toastr.error("下载失败!");
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
							       $scope.exportInvoice = function(judgeString){
							    	   debugger;
								    	 handle.blockUI("正在导出数据，请稍后"); 
								    	 window.location.href=$rootScope.basePath+"/rest/invoice/exportInvoice?inOrOut="+judgeString;
								    	 handle.unblockUI(); 
								       }
								       
							       $('#import').on('hide.bs.modal', function (e) { 
							    	   $("#resetFile").trigger("click");
							       })
						} ]);
