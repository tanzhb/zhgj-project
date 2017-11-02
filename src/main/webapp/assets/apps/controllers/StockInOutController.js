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
						'$filter',
						'$location',
						'$stateParams',
						'settings',
						'StockInOutService',
						function($rootScope, $scope, $state, $compile,$http,$filter,$location,$stateParams,settings,
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
													getStockInOutCheckInfo($stateParams.inOrOut);
												}else{
													$rootScope.setNumCode("QU",function(newCode){
								    		 			$scope.stockInOutCheck.checkNum= newCode;//检验单号 
								    		 		});
												}
												if($scope.stockInOutCheck.checkDate==''){
										    		$scope.stockInOutCheck.checkDate=$filter('date')(new Date(), 'yyyy-MM-dd');
										    	}
										 		}else if($location.path()=="/stockInOutCheckView"){
										 			debugger;
										 			$scope.inOrOut=$stateParams.inOrOut;
										 			getStockInOutCheckInfo($stateParams.inOrOut,"view");//查看出入库检验详情页面
									 		}else if($location.path()=="/confirmStockInOutCheck"){
									 			$('.date-picker').datepicker({
													rtl: App.isRTL(),
													orientation: "left",
													autoclose: true,
													language:"zh-CN"
									        	})
									 			debugger;
									 			$scope.inOrOut=$stateParams.inOrOut;
									 			getStockInOutCheckInfo($stateParams.inOrOut);//查看出入库检验详情页面
								 		}else{
									 			if($stateParams.inOrOut=='showOut'){
									 				debugger;
									 				$("#in").removeClass("active");
									 				$("#out").addClass("active");
									 				loadStockInOutCheckTable('outcheck');
									 				//加载入库检验列表
									 			}else{
									 				$("#in").addClass("active");
									 				$("#out").removeClass("active");
									 				loadStockInOutCheckTable('incheck');//加载入库检验列表
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
							 var table,Intable,Outtable;
			function loadStockInOutCheckTable(judgeString){
							var a = 0,judgeString1;
							if(judgeString.indexOf("in")>-1){
								judgeString1='in';
							}else{
								judgeString1='out';
							}
							tableAjaxUrl= "rest/stockInOut/getStockInOutCheckList?inOrOut="+judgeString1
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

											Intable = $("#sample_incheck")
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
														mData : 'totalQualifiedCount'
													}, {
														mData : 'totalUnQualifiedCount'
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
				 	    									statusIcon = '<span class="label label-sm label-success">已检验</span> '
				 	    								}
				 	    								return statusIcon ;
													}
												}  ],

											});
											Outtable = $("#sample_outcheck")
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
														mData : 'totalQualifiedCount'
													}, {
														mData : 'totalUnQualifiedCount'
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
						 	    									statusIcon = '<span class="label label-sm label-success">已检验</span> '
						 	    								}
						 	    								return statusIcon ;
															}
														}  ],

													});
											if(judgeString=='incheck'){
												table=Intable;
											}else{
												table=Outtable;
											}
							// 构建datatables结束***************************************
											$("#sample_"+judgeString+"").find(".group-checkable").change(function() {
										        var e = jQuery(this).attr("data-set"),
										        t = jQuery(this).is(":checked");
										        jQuery(e).each(function() {
										            t ? ($(this).prop("checked", !0), $(this).parents("tr").addClass("active")) : ($(this).prop("checked", !1), $(this).parents("tr").removeClass("active"))
										        })
										    }),
										    $("#sample_"+judgeString+"").on("change", "tbody tr .checkboxes",
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
*/							}
			
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
							getStockInOutCheckInfo($scope.inOrOut);
							$scope.stockInOutCheckView = false;
		        			$scope.stockInOutCheckAdd = false;
		        			$scope.stockInOutCheckEdit = false;
						}
						$scope.cancelEditStockInOutCheck = function(){
							debugger;
							getStockInOutCheckInfo($scope.inOrOut);
							$scope.stockInOutCheckView = true;
		        			$scope.stockInOutCheckAdd = true;
		        			$scope.stockInOutCheckEdit = true;
						}	
						
						function judgeIsExist (){//判断是否已有收货单/发货单相关的出入库检验
							var serialNum;
							/*if($scope.inOrOut=='in'){
								serialNum=$scope.stockInOutCheck.takeDeliverSerial+"in";
							}else{
								serialNum=$scope.stockInOutCheck.deliverSerial+"out";
							}*/
						/*	StockInOutService.judgeIsExistBySerialNum(serialNum)
							.then(
									function(data) {debugger;
										if(data=='1'){
											if($scope.inOrOut=='in'){
											toastr.warning("该收货单已建入库检验！");
											return ;
											}else if($scope.inOrOut=='out'){
												toastr.warning("该发货单已建出库检验！");
												return ;
											}
											
										}*/
										var params = {};
										params.deliveryMateriels = [];
										var param
										for(var i=0;i < $scope.materials.length;i++){
											param = {};
											param.serialNum = $scope.materials[i].serialNum;
											param.qualifiedCount = $scope.materials[i].qualifiedCount;
											if($scope.inOrOut.indexOf("in")){
												param.unqualifiedCount= $scope.materials[i].acceptCount-$scope.materials[i].qualifiedCount;
											}else{
												param.unqualifiedCount= $scope.materials[i].deliverCount-$scope.materials[i].qualifiedCount;
											}
											param.checkRemark = $scope.materials[i].checkRemark;
											params.deliveryMateriels.push(param);
										}
										$scope.stockInOutCheck.deliverMaterials=params.deliveryMateriels;
										debugger;
										StockInOutService.saveStockInOutCheck($scope.stockInOutCheck)
										.then(
												function(data) {debugger;
													toastr.success("保存检验数据成功！");
													$scope.stockInOutCheck = data;
								        			$scope.stockInOutCheckView = true;
								        			$scope.stockInOutCheckAdd = true;
								        			$scope.stockInOutCheckEdit = true;
								        			$(".alert-danger").hide();
												},
												function(errResponse) {
													toastr.warning("保存失败！");
													console
															.error('Error while creating User');
												}
										);
							/*		}
							);	*/
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
									if(judgeString.indexOf("check")>-1){
										toastr.warning("请选择一条数据进行检验");
									}else{toastr.warning("请选择一条数据进行编辑");}
									
								}else if(id_count>1){
									if(judgeString.indexOf("check")>-1){
										toastr.warning("只请选择一条数据进行检验");
									}else{toastr.warning("只能选择一条数据进行编辑");}
									
								}else{
									var serialNum = table.$('input[type="checkbox"]:checked').val();
									if(judgeString.indexOf("edit")>-1){
										$state.go("addOrEditStockInOutCheck",{inOrOut:serialNum+judgeString});
									}else{$state.go("confirmStockInOutCheck",{inOrOut:serialNum+judgeString});}
										
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
							$scope.confirmStockInOutCheck = function(judgeString) {	
								if(!$('#stockInOutCheckForm').valid()){
									return;
								}
								var params = {};
								params.deliveryMateriels = [];
								var param
								for(var i=0;i < $scope.materials.length;i++){
									param = {};
									param.serialNum = $scope.materials[i].serialNum;
									param.qualifiedCount = $scope.materials[i].qualifiedCount;
									if($scope.inOrOut.indexOf("in")>-1){
										param.unqualifiedCount= $scope.materials[i].acceptCount-$scope.materials[i].qualifiedCount;
									}else{
										param.unqualifiedCount= $scope.materials[i].deliverCount-$scope.materials[i].qualifiedCount;
									}
									param.checkRemark = $scope.materials[i].checkRemark;
									params.deliveryMateriels.push(param);
								}
								$scope.stockInOutCheck.deliverMaterials=params.deliveryMateriels;
								$scope.stockInOutCheck.status=1;
								StockInOutService
										.updateStockInOutCheckStatus($scope.inOrOut)
										.then(
												function(data) {
													StockInOutService.saveStockInOutCheck($scope.stockInOutCheck)
													.then(
															function(data) {debugger;
																//toastr.success("保存检验数据成功！");
																$scope.stockInOutCheck = data;
											        			$scope.stockInOutCheckView = true;
											        			$scope.stockInOutCheckAdd = true;
											        			$scope.stockInOutCheckEdit = true;
											        			$(".alert-danger").hide();
											        			if(judgeString=='checkin'){toastr.success("确认入库检验成功！");
																}else{toastr.success("确认出库检验成功！");
															}
															$scope.stockInOutCheck.status=1;
															},
															function(errResponse) {
																toastr.warning("保存失败！");
																console
																		.error('Error while creating User');
															}
													);
													
													
												},
												function(errResponse) {
													console
															.error('Error while deleting Users');
												}

										);
							}
							  var selectIndex,ajaxUrl;
					 	       $scope.selectDeliverOrTakeDelivery = function(judgeString){
					 	    	  selectDeliverOrTakeDelivery(judgeString);
					 	       }
						     var selectDeliverOrTakeDeliveryTable,aoColumnsForInOrOut,aoColumnDefsForInOrOut;
					 	       var selectDeliverOrTakeDelivery = function(	judgeString) {
					 	    		debugger;
					 	    	 if(selectDeliverOrTakeDeliveryTable!=undefined){
					 	    		selectDeliverOrTakeDeliveryTable.destroy();
					 	    	 }
					 	    	 if(judgeString=='in'){
					 	    		 ajaxUrl={ "url":$rootScope.basePath
												+ "/rest/takeDelivery/takeDeliveryList",  
												"contentType": "application/json",
											    "type": "POST",
											    "data": function ( d ) {
											      return JSON.stringify( d );
											    }};
					 	    		aoColumnsForInOrOut= [
				 	                                 { mData: 'takeDelivery.serialNum' },
					 	                                { mData: 'takeDelivery.takeDeliverNum' },
					 	                               { mData: 'orderAmount' },
					 	                                { mData: 'orderNum' },
					 	                               { mData: 'supplyName' },
					 	                                { mData: 'shipper' },
					 	                                { mData: 'materielCount' },
					 	                                { mData: 'packageCount' },
					 	                                { mData: 'packageType' },
					 	                                { mData: 'warehouse.address' },
					 	                                { mData: 'deliverDate' },
					 	                                { mData: 'deliveryTransport.transportType' },
					 	                                { mData: 'takeDelivery.warehouse.address' },
					 	                                { mData: 'takeDelivery.remark' },
					 	                                { mData: 'status' }
					 	                            ];
					 	    		aoColumnDefsForInOrOut=[ {
			  							'targets' : 0,
			  							'searchable' : false,
			  							'orderable' : false,
			  							'className' : 'dt-body-center',
			  							'render' : function(data,
			  									type, row, meta) {
				  	  								return '<input type="radio"  ng-click="selectDeliverOrTakeDeliverInfo(\''+data+judgeString+'\',\''+row.takeDelivery.takeDeliverNum+'\',\''+row.orderNum+'\',\''+row.supplyName+'\',\''+row.buyName+'\')"   name="serialNum[]" value="'
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
			  							'searchable' : false,
			  							'orderable' : false,
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
			  							'targets' : 7,
			  							'render' : function(data,
			  									type, row, meta) {
			  									if(data!=undefined){
													return data;
												}
				  								return '';
				
			  							}
			  						},{
			  							'targets' : 8,
			  							'render' : function(data,
			  									type, row, meta) {
			  									if(data!=undefined){
													return data;
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
			  									if(data=="1"){
			  										return '<span  class="label label-sm label-warning ng-scope">未收货</span>';
			  									}else if(data=="2"){
			  										return '<span  class="label label-sm label-warning ng-scope">待检验</span>';
			  									}else{
			  										return data;
			  									}
			  							}
			  						}];
					 	    	  }else if(judgeString=='out'){
					 	    		 ajaxUrl="rest/delivery/findAllDeliveryList";  
					 	    		aoColumnsForInOrOut=[
							                            { mData: 'serialNum'},
							                            { mData: 'deliverNum' },
							                            { mData: 'orderNum' },
							                            { mData: 'orderAmount' },
							                            { mData: 'supplyName' },
							                            { mData: 'materielCount' },
							                            { mData: 'packageCount' },
							                            { mData: 'receiver'},
							                            { mData: 'deliveryAddress'},
							                            { mData: 'deliverDate'},
							                            { mData: 'transportType'},
							                            { mData: 'takeAddress' },
							                            { mData: 'remark'}
							                            ];
					 	    		aoColumnDefsForInOrOut= [ {
		                            	'targets' : 0,
		                            	'searchable' : false,
		                            	'orderable' : false,
		                            	'className' : 'dt-body-center',
		                            	'render' : function(data,type, full, meta) {
		                            		return '<input type="radio"  ng-click="selectDeliverOrTakeDeliverInfo(\''+data+judgeString+'\',\''+full.deliverNum+'\',\''+full.orderNum+'\',\''+full.supplyName+'\',\''+full.buyName+'\')"   name="serialNum[]" value="'+ $('<div/>').text(data).html()+ '">';
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
		                            }
		                            ];
					 	    	  }
					 	    	   debugger;
					 	                a = 0;
					 	                App.getViewPort().width < App.getResponsiveBreakpoint("md") ? $(".page-header").hasClass("page-header-fixed-mobile") && (a = $(".page-header").outerHeight(!0)) : $(".page-header").hasClass("navbar-fixed-top") ? a = $(".page-header").outerHeight(!0) : $("body").hasClass("page-header-fixed") && (a = 64);
					 	               selectDeliverOrTakeDeliveryTable = $("#select_sample_"+judgeString)
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
					 	                    "aoColumns": aoColumnsForInOrOut,
					 	                   'aoColumnDefs' :aoColumnDefsForInOrOut

					 	                }).on('order.dt',
					 	                function() {
					 	                    console.log('排序');
					 	                })
					 	            };
						$scope.selectDeliverOrTakeDeliverInfo=function(serialNum,deliverOrTakeDeliverNum,orderNum,supplyName,buyName){
							debugger;
							$scope.row = {};
		 	            	$scope.row.serialNum = serialNum;//发货单号流水/收货单号流水
		 	            	$scope.row.deliverOrTakeDeliverNum=deliverOrTakeDeliverNum;//发货单号/收货单号
		 	            	$scope.row.orderNum=orderNum;//销售订单号/采购订单号
		 	            	$scope.row.supplyName=supplyName;//供应商名称
		 	            	$scope.row.buyName=buyName;//采购商名称
		 	            	getMaterialInfo();
						}
					function getMaterialInfo(){
						StockInOutService.getMaterialBySerialNum($scope.row.serialNum).then(
								function(data) {debugger;
								$scope.row.materials=data.materials;
							},
							function(errResponse) {
								toastr.warning("获取失败！");
								console
										.error('Error while creating User');
							}
					);
					}
					var  materielTable;
					function loadMaterielTable(serialNum){
						var a = 0,tableid;
						var status=$scope.stockInOutCheck.status;
						 if(materielTable!=undefined){
							 materielTable.destroy();
				 	    	 }
						 if(serialNum.indexOf("in")>-1){
							 tableid="sample_inview";
						 }else{
							 tableid="sample_outview";
						 }
						tableAjaxUrl= "rest/stockInOut/stockInOutCheckViewDetail?serialNum="+serialNum;
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
										
										materielInTable = $("#"+tableid)
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
													fixedHeader : {// 固定表头、表底
														header : !0,
														footer : !0,
														headerOffset : a
													},
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
													/* ajax :{ "url":$rootScope.basePath
									  						+ "/rest/stockInOut/stockInOutCheckViewDetail?serialNum="+serialNum,// 
									  						"contentType": "application/json",
									  					    "type": "POST",
									  					    "data": function ( data ) {
									  					    	debugger;
									  					      return data.materials;
									  					    }},*/
													ajax : tableAjaxUrl,// 加载数据中发票表数据

													"aoColumns" : [
													   {
													    mData : 'materielNum'
													   },
														{
														mData : 'materielName'
														},{
															mData : 'specifications'
														},  {
															mData : 'unit'
														},{
															mData : 'batchNum'
														}, {
															mData : 'manufactureDate'
														},{
															mData : 'amount'//orderMateriel.amount
														}, {
															mData : 'deliverCount'
														}, { 
															mData : 'deliverRemark'
															
														}, {
															mData : 'stockCount'//acceptCount
														},{
															mData : 'unstockCount'//refuseCount
														},{
															mData : 'stockRemark'//takeRemark
														}, {
															mData : 'qualifiedCount'
														},{
															mData : 'unqualifiedCount'
														},{
															mData : 'checkRemark'
														},{
															mData : 'status'
														}
														],
													'aoColumnDefs' : [
													                  {'targets' : 6,
													                	 'render' : function(data,
													                		type, row, meta) {
													                		 return  row.amount;
													                	  }
													                  }, 
													                  {'targets' : 9,
														                	 'render' : function(data,
																                		type, row, meta) {
														                			if(serialNum.indexOf("in")>-1){
																	                	 return row.acceptCount;
																	                		  }else{
																	                			  return  row.stockCount;
																	                		  }
																                	  }
																                  
													                  }, 
													                  {'targets' : 10,
																	                	 'render' : function(data,
																			                		type, row, meta) {
																	                			if(serialNum.indexOf("in")>-1){
																				                	 return row.refuseCount;
																				                		  }else{
																				                			  return  row.unstockCount;
																				                		  }
																			                	  }
																			                 
													                  },
													                  {'targets' : 11,
													                	  'render' : function(data,
													                		 type, row, meta) {
																				   if(serialNum.indexOf("in")>-1){
																				return row.takeRemark;
																					  }else{
																				return  row.stockRemark;
																				  }

																			 }
																						                  
													                  },
													                  {'targets' : 15,
																		'render' : function(data,
																			type, row, meta) {
																			if(status==0){
																				 return '<span  class="label label-sm label-info ng-scope">待检验</span>';
																					 }else if(status==1){
																					return  '<span  class="label label-sm label-success ng-scope">已检验</span>';
																					 }
																				}
																		 }
													                  ],
												});
									
						// 构建datatables结束***************************************
						}

		
						   // 确认选择发货单开始***************************************
		 	    		$scope.confirmSelectDeliverOrTakeDeliveryInfo = function(judgeString) {
		 	    			var ids = '';
		 	    			// Iterate over all checkboxes in the table
		 	    				selectDeliverOrTakeDeliveryTable.$('input[type="radio"]').each(//[name="in[]"]
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
		 	    				if(judgeString=='in'){
		 	    					toastr.warning('请选择一个收货单！');return;
		 	    				}else{
		 	    					toastr.warning('请选择一个发货单！');return;
		 	    				}
		 	        		}
		 	    			//为前台四个参数赋值
		 	    			if(judgeString=='in'){
		 	    				$("#deliverSerial").val('checkin');//发货单流水
			 	            	$("#takeDeliverSerial").val($scope.row.serialNum.substring(0,32));//收货单流水
			 	            	$scope.stockInOutCheck.takeDeliverNum=$scope.row.deliverOrTakeDeliverNum;
			 	            	$scope.stockInOutCheck.relationBuyNum=$scope.row.orderNum;
			 	            	$scope.stockInOutCheck.supplyName=$scope.row.supplyName;
			 	            	$scope.stockInOutCheck.checkParty=$scope.row.buyName=="null"?"中航能科（上海）能源科技有限公司":$scope.row.buyName;
			 	            	/*$("#takeDeliverNum").val($scope.row.deliverOrTakeDeliverNum);//收货单号
			 	            	$("#relationBuyNum").val($scope.row.orderNum);//采购单号
			 	            	$("#supplyName").val($scope.row.supplyName);*/
			 	            	$('#takeDeliveryInfo').modal('hide');// 选择成功后关闭模态框
		 	    			}else{
		 	    				$("#deliverSerial").val($scope.row.serialNum.substring(0,32));//发货单流水
			 	            	$("#takeDeliverSerial").val('checkout') ;//收货单流水
			 	            	$scope.stockInOutCheck.deliverNum=$scope.row.deliverOrTakeDeliverNum;
			 	            	$scope.stockInOutCheck.relationSaleNum=$scope.row.orderNum;
			 	            	$scope.stockInOutCheck.supplyName=$scope.row.supplyName;
			 	            	$scope.stockInOutCheck.checkParty=$scope.row.buyName=="null"?"中航能科（上海）能源科技有限公司":$scope.row.buyName;
			 	          /* 	$("#deliverNum").val($scope.row.deliverOrTakeDeliverNum);//发货单号
		 	            	$("#relationSaleNum").val($scope.row.orderNum);//销售单号
		 	            	$("#supplyName").val($scope.row.supplyName);*/
		 	            	$('#deliverInfo').modal('hide');// 选择成功后关闭模态框
		 	    			}
		 	    			$scope.materials=$scope.row.materials;
		 	    			//loadMaterielTable(judgeString,$scope.row.serialNum);//加载物料table
		 	    			
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
								var  qualifiedCountData='';
								if($scope.inOrOut!=undefined&&$scope.inOrOut.indexOf("in")>-1){
									qualifiedCountData={required:true,digits:true,qualifiedNumCheck:!0};
								}else if($scope.inOrOut!=undefined&&$scope.inOrOut.indexOf("out")>-1){
									qualifiedCountData={required:true,digits:true,deliverNumCheck:!0};
								}
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
						            	contactNum:{required:"联系电话不能为空！",digits:"请输入正确的联系电话, 必须为数字！",rangelength:jQuery.validator.format("电话必须在{0}到{1}位数字之间！")},
						            	qualifiedCount:{required:"合格数量不能为空！",digits:"请输入正确的合格数量, 必须为数字！"}
						            },
						            rules: {
						            	checkNum:{required:true},
						            	takeDeliverNum:{required:true},
						            	deliverNum:{required:true},
						            	checkParty:{required:true},
						            	checkDate:{required:true},
						            	checker:{required:true},
						            	contactNum:{required:true,digits:true,rangelength:[7,20]},
						            	qualifiedCount:qualifiedCountData
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
							
							
							 function getStockInOutCheckInfo(serialNum,judgeString){//获取出入库检验信息
						    	   if(!handle.isNull(serialNum)){
						    		   debugger;
						    			 var promise =StockInOutService .selectDetailBySerialNum(serialNum);
						 	        	promise.then(function(data){
						 	        		  debugger;
						 	        			 $scope.stockInOutCheck = data.stockInOutCheck;
						 	        			 $scope.stockInOutCheck.checkDate=timeStamp2ShortString(data.stockInOutCheck.checkDate);
						 	        			 $scope.materials=data.materials;
						 	        			 if($stateParams.inOrOut.indexOf("in")>-1){
						 	        				 $("#takeDeliverSerial").val(data.stockInOutCheck.takeDeliverSerial);
						 	        				 $("#deliverSerial").val('checkin');
						 	        			 }else{
						 	        				 $("#deliverSerial").val(data.stockInOutCheck.deliverSerial);
						 	        				$("#takeDeliverSerial").val('checkout');
						 	        			 }
						 	        			 if(judgeString!=undefined){
						 	        				loadMaterielTable(serialNum);
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
							       $scope.exportStockInOutCheck = function(judgeString){
							    	   debugger;
								    	 handle.blockUI("正在导出数据，请稍后"); 
								    	 window.location.href=$rootScope.basePath+"/rest/stockInOut/exportStockInOutCheck?inOrOut="+judgeString;
								    	 handle.unblockUI(); 
								       }
								       
							       $('#import').on('hide.bs.modal', function (e) { 
							    	   $("#resetFile").trigger("click");
							       })
						} ]);
