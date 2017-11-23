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
													if($scope.inOrOut.indexOf("confirm")>-1){
														getInvoiceInfo($stateParams.inOrOut+"view");
													}else{
														getInvoiceInfo($stateParams.inOrOut);
													}
												}else{
													$scope.invoice.paymentStatus='否';
													$scope.invoice.billWay='0';
													$rootScope.setNumCode($scope.inOrOut=="in"?"OT":"IT",function(newCode){
											 			$scope.invoice.invoiceNum= newCode;//进/销项票
											 		});
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
										 		}else{
										 			
															 $scope.taskId=$stateParams.taskId;
															 $scope.processInstanceId=$stateParams.processInstanceId;
															var comments =$stateParams.comments;
															 if(comments == ""||comments == null){
										    						$("#comment_audit").html( "<tr><td colspan='3' align='center'>无内容</td></tr>");
										    					}else{ $("#comment_audit").html(comments);}
															 if($location.path()=="/editInvoiceApply"){
																 getInvoiceInfo($stateParams.inOrOut);
										    					}else{
										    						 getInvoiceInfo($stateParams.inOrOut+"view");
										    					}
															 $scope.inOrOut=$stateParams.inOrOut;
														 
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
							//***************************************流程处理相关start
				        	var dbOutInvoicetable;//待办销项票table
							var endTaskOutInvoiceTable;//已办销项票table
							// 显示销项票列表
							$scope.toInvoiceApply = function() {
								$('#invoiceOutTab a[href="#applyOutInvoice"]').tab('show');
							};
							//销项票待办流程
							$scope.toDaibanInvoice = function(judgeString) {//
								debugger;
								$('#invoiceOutTab a[href="#daibanBuyPrice"]').tab('show');
								dbOutInvoicetable = showDbOutInvoiceTable();								
							};
							// 销项票已办流程
							$scope. toYibanInvoice= function(judgeString) {
								$('#invoiceOutTab a[href="#yibanBuyPrice"]').tab('show');
								debugger;
								endTaskOutInvoiceTable = showYbOutInvoiceTable();
							};
							
							// 构建datatables开始***************************************
							var tableAjaxUrl ;
							 var table ;
									
			function loadInvoiceInTable(){
							var a = 0,judgeString='in';
							tableAjaxUrl= "rest/invoice/getInvoiceList?inOrOut="+judgeString;
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
																mData : 'comName'
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
															/* { mData: 'processBase',
								                            	mRender:function(data,
								    									type, row, meta){
								                            		if(data!=""&&data!=null){
								                            			if(data.status=="PENDING"||data.status=="WAITING_FOR_APPROVAL"){
								    										return '<span  class="label label-sm label-warning ng-scope">审核中</span>';
								    									}else if(data.status=="APPROVAL_SUCCESS"){
								    										if(row.status==0){
								    											return '<span  class="label label-sm label-success ng-scope">未确认</span>';
								    										}else if(row.status==1){
								    											return '<span  class="label label-sm label-success ng-scope">已确认</span>';
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
																debugger;
						 	    								if(row.status==0){
						 	    									statusIcon = '<span class="label label-sm label-success"  >待收票</span> '
						 	    								}else if(row.status==2){
						 	    									statusIcon = '<span class="label label-sm label-success">已收票</span> '
						 	    								}else if(row.status==1){
						 	    									statusIcon = '<span class="label label-sm label-success">已申请收票</span> '
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
				tableAjaxUrl= "rest/invoice/getInvoiceList?inOrOut="+judgeString;
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
												mData : 'comName'
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
											},/*{
												mData : 'status'
											}*/
											 { mData: 'processBase',
				                            	mRender:function(data,
				    									type, row, meta){
				                            		if(data!=""&&data!=null){
				                            			debugger;
				                            			if(data.status=="PENDING"||data.status=="WAITING_FOR_APPROVAL"){
				    										return '<span  class="label label-sm label-warning ng-scope">审核中</span>';
				    									}else if(data.status=="APPROVAL_SUCCESS"){
				    										if(row.status==0){
				    											return '<span  class="label label-sm label-success ng-scope">待开票</span>';
				    										}else if(row.status==1){
				    											return '<span  class="label label-sm label-success ng-scope">已审批</span>';
				    										}else if(row.status==2){
				    											return '<span  class="label label-sm label-success ng-scope">已开票</span>';
				    										}else if(row.status==3){
				    											return '<span  class="label label-sm label-success ng-scope">已申请开票</span>';
				    										}
				    									}else if(data.status=="APPROVAL_FAILED"){
				    										return '<span  class="label label-sm label-danger ng-scope">未通过</span>';
				    									}else{
				    										return '<span  class="label label-sm label-info ng-scope">未审批</span>';
				    									}
				                            		}else if(row.status==3){
		    											return '<span  class="label label-sm label-success ng-scope">已申请开票</span>';
		    										}else{
				                            			return '<span  class="label label-sm label-success ng-scope">待开票</span>';
				                            		}
				                            	}
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
										}/*,{
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
										}*/  ],
							
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
				tableAjaxUrl= "rest/invoice/getMaterielList?orderSerial="+orderSerial;
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
													mData : 'money'/*,
													mRender:function(data){
					                            		if(data!=""&&data!=null){
					                            			return $filter('currency')(data,'￥');
					                            		}else{
					                            			return $filter('currency')(0,'￥');
					                            		}
					                            	}*/
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
													if(data==null){
														return '<span id="money'+row.serialNum+'">'+$filter('currency')(0,'￥')+'</span>';
													}else{
														return '<span id="money'+row.serialNum+'">'+$filter('currency')(data,'￥')+'</span>';
													}
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
													return '<a   id="save'+row.serialNum+'" ng-click="saveBillingRecord(\''+row.serialNum+'\',\''+judgeString+'\',\''+row.invoiceBillingRecordSerial+'\',\''+row.canBillAmount+'\',\''+row.orderUnitPrice+'\',\''+row.money+'\')">  <i class="fa fa-save" title="保存"></i> </a>&nbsp;<a   style="display:none"  id="edit'+row.serialNum+'"  ng-click="editBillingRecord(\''+row.serialNum+'\',\''+judgeString+'\')"><i class="fa fa-edit" title="编辑"></i></a>'
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
	tableAjaxUrl= "rest/invoice/getMaterielList?orderSerial="+orderSerial;
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
									mData : 'money'/*,
									mRender:function(data){
	                            		if(data!=""&&data!=null){
	                            			return $filter('currency')(data,'￥');
	                            		}else{
	                            			return $filter('currency')(0,'￥');
	                            		}
	                            	}*/
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
									if(data==null){
										return '<span id="money'+row.serialNum+'">'+$filter('currency')(0,'￥')+'</span>';
									}else{
										return '<span id="money'+row.serialNum+'">'+$filter('currency')(data,'￥')+'</span>';
									}
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
									return '<a   id="save'+row.serialNum+'" ng-click="saveBillingRecord(\''+row.serialNum+'\',\''+judgeString+'\',\''+row.invoiceBillingRecordSerial+'\',\''+row.canBillAmount+'\',\''+row.orderUnitPrice+'\',\''+row.money+'\')">  <i class="fa fa-save" title="保存"></i> </a>&nbsp;<a   style="display:none"  id="edit'+row.serialNum+'"  ng-click="editBillingRecord(\''+row.serialNum+'\',\''+judgeString+'\')"><i class="fa fa-edit" title="编辑"></i></a>'
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
$scope.confirmInvoice=function(judgeString){//进项票/销项票确认
	debugger;
	var id_count = table.$('input[type="checkbox"]:checked').length;
	if(id_count==0){
		toastr.warning("请选择一条数据进行确认");
	}else if(id_count>1){
		toastr.warning("只能选择一条数据进行确认");
	}else{
		var serialNum = table.$('input[type="checkbox"]:checked').val()+judgeString;
		if(judgeString=='out'){
			InvoiceService.judgeIsApproval(serialNum).then(
					function(data) {debugger;
				if(data==3){
					toastr.warning("审批尚未通过,审批通过后方可开票!");
				}else if(data==1){
					toastr.warning("尚未申请审批,审批通过后方可开票!");
				}else if(data==0){
					toastr.warning("此销项票已开票!");
				}else{
					$state.go("addOrEditInvoice",{inOrOut:serialNum+judgeString+"confirm"});//审批通过进入确认开票页面
				}
					},
					function(errResponse) {
						toastr.warning("保存失败！");
						console
								.error('Error while creating User');
					}
			);
		}else{
			InvoiceService.judgeIsApproval(serialNum).then(
					function(data) {debugger;
			 if(data==0){
					toastr.warning("此进项票已收票!");
				}else{
					$state.go("addOrEditInvoice",{inOrOut:serialNum+judgeString+"confirm"});//审批通过进入确认开票页面
				}
					},
					function(errResponse) {
						toastr.warning("保存失败！");
						console
								.error('Error while creating User');
					}
			);
		}
		
	}
}

$scope.saveBillingRecord=function (serialNum,judgeString,invoiceBillingRecordSerial,canBillAmount,orderUnitPrice,money){
	if($scope.invoice.serialNum==undefined&&judgeString=='in'){
		 toastr.warning("请先保存进项票信息！");
		 return;
	}
	if($scope.invoice.serialNum==undefined&&judgeString=='out'){
		 toastr.warning("请先保存销项票信息！");
		 return;
	}
	if(Number($("#"+serialNum).val())>canBillAmount&&judgeString=='in'){
		toastr.warning("收票数量不能大于可收数量！");
		 return;
	}
	if(Number($("#"+serialNum).val())>canBillAmount&&judgeString=='out'){
		toastr.warning("开票数量不能大于可开数量！");
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
				var billOrReceiptMoney =Number($scope.invoice.orderAmount-$scope.invoice.unBillOrReceiptMoney);
				$scope.invoice.billOrReceiptMoney=billOrReceiptMoney-Number(money=='null'?0:money)+nowMoney;
				$scope.invoice.capitalMoney=convertCurrency(billOrReceiptMoney-Number(money=='null'?0:money)+nowMoney);
				$("#money"+serialNum).html($filter('currency')(nowMoney,'￥'));
				
    			
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
$scope.datepickerInit = function(scope){
	   $('.date-picker').datepicker({
			rtl: App.isRTL(),
			orientation: "left",
			autoclose: true,
			dateFormat:"yyyy-mm-dd",
			language: "zh-CN"
	})
};
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
						
								$scope.saveInvoice= function(judgeString) {
									debugger;
									if($('#invoiceForm').valid()){//表单验证通过则执行添加功能
										debugger;
									 if(judgeString==undefined){
										 $scope.invoice.status=0;
										}else if(judgeString.indexOf("confirm")>-1){
											 $scope.invoice.status=2;
										}else if(judgeString.indexOf("applyIn")>-1){//进项票申请收票
											 $scope.invoice.status=2;
										}else if(judgeString.indexOf("applyOut")>-1){//销项票申请开票
											 $scope.invoice.status=3;
										}
										InvoiceService.saveInvoice($scope.invoice).then(
															function(data) {debugger;
															$scope.invoice.serialNum=data.serialNum;
															if(judgeString==undefined){
																$scope.invoiceView = true;
																$scope.invoiceAdd = true;
																toastr.success("保存发票数据成功！");
															}else if(judgeString=='confirmIn'){
																toastr.success("确认收票成功！");
															}else if(judgeString=='confirmOut'){
																toastr.success("确认开票成功！");
															}else if(judgeString=='applyIn'){
																toastr.success("申请收票成功！");
															}else if(judgeString=='applyOut'){
																toastr.success("申请开票成功！");
															}
															//$scope.showInvoiceInfo(data.serialNum,"out");
																
															/*$scope.invoice = data;
															 $scope.invoice.receiptDate=timeStamp2ShortString(data.receiptDate);
									 	        			 $scope.invoice.billingDate=timeStamp2ShortString(data.billingDate);
									 	        			 $scope.invoice.submitDate=timeStamp2ShortString(data.submitDate);
									 	        			 $scope.invoice.approvalDate=timeStamp2ShortString(data.approvalDate);
									 	        			 $scope.invoice.capitalMoney=convertCurrency(data.billOrReceiptMoney);
										        			$scope.invoiceAdd = true;
										        			$scope.invoiceEdit = true;*/
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
								 if(table.rows('.active').data().length != 1){
										showToastr('toast-top-center', 'warning', '请选择一条数据进行修改！')
									}else{
										if(judgeString=='out'&&table.row('.active').data().processBase !=null){
											showToastr('toast-top-center', 'warning', '该条数据已经申请流程审批，不能进行修改！')
										}else if(judgeString=='in'&&table.row('.active').data().status!=0) {
											showToastr('toast-top-center', 'warning', '该条数据已经确认收票，不能进行修改！')
										}else {
											var serialNum = table.$('input[type="checkbox"]:checked').val();
											$state.go("addOrEditInvoice",{inOrOut:serialNum+judgeString});
										}
								/*var id_count = table.$('input[type="checkbox"]:checked').length;
								if(id_count==0){
									toastr.warning("请选择一条数据进行编辑");
								}else if(id_count>1){
									toastr.warning("只能选择一条数据进行编辑");
								}else{
									var serialNum = table.$('input[type="checkbox"]:checked').val();
									$state.go("addOrEditInvoice",{inOrOut:serialNum+judgeString});
								}*/
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
					 	    		                                   { mData: 'supplyName' },
					 	    		                                  { mData: 'materielCount' },
					 	    		                                   { mData: 'orderAmount' },
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
					 	   								return '<input type="radio" id="'+data+'" ng-click="getBuyOrSaleOrderInfo(\''+data+"in"+'\',\''+full.orderNum+'\',\''+full.orderAmount+'\',\''+full.supplyComId+'\',\''+full.supplyName+'\')" name="serialNum[]" value="'
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
					 	                               { mData: 'buyName' },
					 	                              { mData: 'materielCount' },
					 	                              { mData: 'orderAmount' },
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
											return '<input type="radio" id="'+data+'" ng-click="getBuyOrSaleOrderInfo(\''+data+"out"+'\',\''+full.orderNum+'\',\''+full.orderAmount+'\',\''+full.buyComId+'\',\''+full.buyName+'\')" name="serialNum[]" value="'
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
					 	                    order: [[1, "desc"]],//默认排序列及排序方式
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
						$scope.getBuyOrSaleOrderInfo=function(serialNum,orderNum,orderAmount,comId,comName){
							debugger;
							$scope.row = {};
		 	            	$scope.row.serialNum = serialNum;//发货单号流水/收货单号流水
		 	            //	$scope.row.orderAmount=orderAmount;//订单金额
		 	            	$scope.row.orderNum=orderNum;//订单编号
		 	            	$scope.row.comId=comId;//开票方/收票方企业id
		 	            	$scope.row.comName=comName;//开票方/收票方企业名称
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
									$scope.row.company=data.company;
									$scope.row.companyFinanceList=data.companyFinanceList;
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
					
					/**
 	   				 * 加载财务数据
 	   				 */
					
					var initCompanyFinances = function(data){
						debugger;
						$scope.companyFinances = data;
						setTimeout(function () {
							$("#bankName").selectpicker({
		                        showSubtext: true
		                    });
		        			$('#bankName').selectpicker('refresh');//刷新插件
		        			$("#account").selectpicker({
		                        showSubtext: true
		                    });
		        			$('#account').selectpicker('refresh');//刷新插件
		                }, 100);
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
	 	    				$scope.invoice.unBillOrReceiptMoney=$scope.row.orderInfo.unBillOrReceiptMoney;
	 	    				$scope.invoice.unPayOrReceiptMoney=$scope.row.orderInfo.unPayOrReceiptMoney;
	 	    				
		 	    			if(judgeString=='buy'){
		 	    				$scope.invoice.supplyComId=$scope.row.comId;
		 	    				loadMaterielInTable($scope.invoice.orderSerial,'no');
			 	            	$('#buyOrderInfo').modal('hide');// 选择成功后关闭模态框
		 	    			}else{
		 	    				$scope.invoice.buyComId=$scope.row.comId;
		 	    				$scope.invoice.companyName=$scope.row.comName;
		 	    				$scope.invoice.address=$scope.row.company.address;
		 	    				$scope.invoice.taxNum=$scope.row.company.taxpayeNumber;
		 	    				initCompanyFinances($scope.row.companyFinanceList);
		 	    				loadMaterielOutTable($scope.invoice.orderSerial,'no');
			 	            	$('#saleOrderInfo').modal('hide');// 选择成功后关闭模态框
		 	   				}
		 	    			$scope.invoice.comName=$scope.row.comName;
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
									 window.location.href= $rootScope.basePath+"/rest/fileOperate/downloadFile?fileName="+encodeURI(encodeURI(obj.file));
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
							       
							       $scope.cancelPage  = function(judgeString) {// 取消申请
							        	 $state.go("invoice",{inOrOut:judgeString});
							        };
							        //********审批流程start****************//
								       $scope.submitInvoiceApply= function(judgeString) {// 进入申请审批页面
								    	   debugger;
								        	if(table.rows('.active').data().length != 1){
								    			showToastr('toast-top-center', 'warning', '请选择一条任务进行流程申请！')
								    		}else{
								    			var processBase = table.row('.active').data().processBase;
								    			if(processBase != null){
								    				showToastr('toast-top-center', 'warning', '该价格已发起流程审批，不能再次申请！')
								    			}else $state.go('submitInvoiceApply',{inOrOut:table.row('.active').data().serialNum+judgeString});
								    		}     	
								        };
								        
								        
								        $scope.confirmInvoiceApply  = function(judgeString) {// 进入提交申请
								        	debugger;
								        	$scope.submitInvoice= {}
								        	$scope.submitInvoice.serialNum = $scope.invoice.serialNum;
								        	$scope.submitInvoice.reason = $scope.invoice.reason;
								        /*	if(judgeString=='out'){
								        		$scope.submitInvoice.invoiceType="out";
								        	}else{
								        		$scope.submitInvoice.invoiceType="in";
								        	}*/
								        	//启动流程
								        	InvoiceService.startInvoiceProcess($scope.submitInvoice).then(
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
								        
								      //********审批流程列表****************//
							        function doInvoice(_url, mydata, modal){
							        	$.ajax( {
							    	        url : _url,
							    	        dataType:"text",
							    	        type: 'POST',
							    	        data : mydata,
							    	        success : function(data) {
							    	        	showToastr('toast-bottom-right', 'success', data);
							    	        	$scope.cancelPage();
							    	        }
							    	     });
							    	}
							    	
							    	//审批通过
							    	$scope.invoicePass = function(judgeString) {
							    		debugger;
							    		console.log( $("#taskId").val()+"--");
							    	    var mydata={"processInstanceId":$("#processInstanceId").val(),"invoiceId":$scope.invoice.serialNum,"content":$("#content").val(),
							    				"completeFlag":true};
							    	    var _url = ctx + "rest/invoice/complate/" + $("#taskId").val();
							    	    doInvoice(_url, mydata, 'audit');
							    	};
							    	//审批不通过
							    	$scope.invoiceUnPass = function(judgeString) {
							    		debugger;
							    		var mydata={"processInstanceId":$("#processInstanceId").val(),"invoiceId":$scope.invoice.serialNum,"content":$("#content").val(),
							    				"completeFlag":false};
							    		var _url = ctx + "rest/invoice/complate/" + $("#taskId").val();
							    		doInvoice(_url, mydata, 'audit');
							    	};
							    	
							    	//重新申请
							    	$scope.replyInvoice = function(judgeString) {
							    		debugger;
							    	    var mydata={"processInstanceId":$("#processInstanceId").val(),
							    				"isPass":true,"invoiceId":$scope.invoice.serialNum,"reason":$scope.invoice.reason};
							    		var _url = ctx + "rest/invoice/modifyInvoice/" + $("#taskId").val();
							    		doInvoice(_url, mydata, 'modify');
							    	};
							    	//取消申请
							    	$scope.cancelApplyInvoice  = function(judgeString) {
							    		debugger;
							    	     var mydata={"processInstanceId":$("#processInstanceId").val(),
							    				"isPass":false,"invoiceId":$scope.invoice.serialNum,"reason":$scope.invoice.reason};
							    		var _url = ctx + "rest/invoice/modifyInvoice/" + $("#taskId").val();
							    		doInvoice(_url, mydata, 'modify' );
							    	};
							       function showDbOutInvoiceTable(){
							        	
							        	var table = $("#dbOutInvoiceTable")
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
							    										}else{
							        									InvoiceService
							        									.getAuditInfos(ids)
																		.then(
																				function(result) {													
							        												var comments = ""//添加评论
								        												for (var i=0;i<result.commentList.length;i++){
								        													comments += "<tr><td>" + result.commentList[i].userName + "</td><td>" 
								        													+ timeStamp2String(result.commentList[i].time) + "</td><td>" + result.commentList[i].content + "</td></tr>";														
								        												}
								        												if(result.actionType == 'audit'){//审批流程
								        													$state.go('approvalInvoiceApply',{inOrOut:result.invoice.serialNum+'out',serialNum:result.invoice.serialNum, taskId:ids, comments:comments,processInstanceId:result.invoice.processInstanceId});
								        												}else{
								        													$state.go('editInvoiceApply',{inOrOut:result.invoice.serialNum+'out',serialNum:result.invoice.serialNum, taskId:ids, comments:comments,processInstanceId:result.invoice.processInstanceId});
								        												}
								        											},
																				function(errResponse) {
								        												toastr.warning("办理失败！");
								        												console.error('Error while apply ap');
								        											}
								
																		);}
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
							        									claimTask(ids, 'dbOutInvoiceTable');
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
							        				order : [ [ 1, "asc" ] ],// 默认排序列及排序方式

							        				bRetrieve : true,
							        				lengthMenu : [
							        						[ 5, 10, 15, 30, -1 ],
							        						[ 5, 10, 15, 30,
							        								"All" ] ],
							        				pageLength : 10,// 每页显示数量
							        				processing : true,// loading等待框

							        				ajax : ctx
							        						+ "/rest/processAction/todoTask/" + 'outInvoice',// 加载待办列表数据

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
							        	                    } 
							        	                    ]

							        			})
							        			
							        			
							        			$("#dbOutInvoiceTable").find(".group-checkable").change(function() {
										            var e = jQuery(this).attr("data-set"),
										            t = jQuery(this).is(":checked");
										            jQuery(e).each(function() {
										                t ? ($(this).prop("checked", !0), $(this).parents("tr").addClass("active")) : ($(this).prop("checked", !1), $(this).parents("tr").removeClass("active"))
										            })
										        }),
										        $("#dbOutInvoiceTable").on("change", "tbody tr .checkboxes",
										        function() {
										            $(this).parents("tr").toggleClass("active")
										        })
						        
							        			return table;
							        	
							        	
							        }
							       function handleTask(assign, taskId, processInstanceId){
							    		
							    		if(assign == ''){
							    			toastr.warning("此任务您还没有签收，请【签收】任务后再处理任务！！");
							    			return;
							    		}
							       }
							        function showYbOutInvoiceTable(){
							        	var endTaskTable = $("#endTaskOutInvoiceTable").DataTable(
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
							        						+ "/rest/processAction/endTask/"+'outInvoice',// 加载已办列表数据

							        				"aoColumns" : [
							        						{
							        							mData : 'businessType',
							        							mRender : function(
							        									data) {
							        								return "销售价格申请";
							        							}
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
							        								return "<a href='javascript:void(0);' onclick=\"revoke('"+row.taskId+"','"+row.processInstanceId+"','endTaskOutInvoiceTable')\">撤销</a>";
							        							}
							        						}
							        						]

							        			})
							         return endTaskTable;
							        }
						} ]);
