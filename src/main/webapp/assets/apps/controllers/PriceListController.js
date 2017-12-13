/* Setup blank page controller */
angular
.module('MetronicApp')
.controller(
		'PriceListController',
		[
		 '$rootScope',
		 '$scope',
		 '$state',
		 '$compile',
		 '$http',
		 '$location',
		 '$stateParams',
		 'settings',
		 'priceListService',
		 'orderService',
		 'FileUploader',
		 function($rootScope, $scope, $state, $compile,$http,$location,$stateParams,settings,
				 priceListService,orderService,FileUploader) {
			 $scope
			 .$on(
					 '$viewContentLoaded',
					 function() {
						 // initialize core components
						 handle = new pageHandle();
						 App.initAjax();
						 if($location.path()=="/addPriceList"){
							 debugger;
							 if($stateParams.buyOrSale.length<=4){
								 debugger;
								 $scope.ladderprices=[{}];
								 $scope.priceComs=[{}];
								 _index = 0; //阶梯价格
								 _index_com = 0; //采购商/供应商
								// $scope.priceList.isLadderPrice="0";
								 getPriceListInfo($stateParams.buyOrSale);
								 $scope.isChecked=false;
								 $scope.priceListAdd=true;
								 $scope.priceListView=false;
								 $scope.priceListEdit=true;//隐藏取消
							 }else if($stateParams.buyOrSale.length>=4){//修改
								 debugger;
								 getPriceListInfo($stateParams.buyOrSale);
								 $scope.priceListAdd=false;
								 $scope.priceListView=true;
								 $scope.priceListEdit=true;//隐藏取消
								 $scope.ladderpriceAdd=true;
								 $scope.ladderpriceView=true;
								 $scope.ladderpriceEdit=false;//隐藏取消
								 $scope.buyComsInfoShow=true;
							 }
							
							 if($stateParams.buyOrSale.indexOf("buy")>-1){
								 initSuppliers();
								 initCustomersForCom();
								 $rootScope.setNumCode("PI",function(newCode){//
						    			//$scope.company={};
					        			$scope.priceList.priceNum= newCode;//采购价格编号
					        		});
							 }else{
								 initCustomers(); 
								 initSuppliersForCom();
								 $rootScope.setNumCode("SI",function(newCode){//
						    			//$scope.company={};
									 $scope.priceList.priceNum= newCode;//销售价格编号
					        		});
							 }
						/*	 handle.pageRepeater();
							 selectParentMateriel();//选择物料表格初始化
							 initFormiCheck();*/
							

						 }else if($location.path()=="/priceList"){
				 			debugger;
				 			if($stateParams.buyOrSale=='sale'){
				 				debugger;
				 				$("#buy").removeClass("active");
				 				$("#sale").addClass("active");
				 				$("#tab_buy").removeClass("active");
				 				$("#tab_sale").addClass("active");
				 				loadPriceListSaleTable();
				 				//加载采购价格列表
				 			}else{
				 				$("#buy").addClass("active");
				 				$("#sale").removeClass("active");
				 				$("#tab_buy").addClass("active");
				 				$("#tab_sale").removeClass("active")
				 				loadPriceListBuyTable();//加载销售价格列表
				 			}
					 		}else{
					 			 getPriceListInfo($stateParams.buyOrSale);
					 		}
						 $scope.buyOrSale=$stateParams.buyOrSale;
						 $scope.type=$stateParams.type;
						  if($location.path()=="/approvalPriceApply"||$location.path()=="/editPriceApply"){
						 $scope.taskId=$stateParams.taskId;
						 $scope.processInstanceId=$stateParams.processInstanceId;
						var comments =$stateParams.comments;
						 if(comments == ""||comments == null){
	    						$("#comment_audit").html( "<tr><td colspan='4' align='center'>无内容</td></tr>");
	    					}else{ $("#comment_audit").html(comments);}
						 if($location.path()=="/editPriceApply"){
							 if($stateParams.buyOrSale.indexOf("buy")>-1){
								 initSuppliers();
								 initCustomersForCom();
							 }else{
								 initCustomers(); 
								 initSuppliersForCom();
							 }
							 $scope.priceListView =false;
							 $scope.priceListAdd =true ;
							 $scope.priceListEdit =false;
	    					}
					 }
						  handle.pageRepeater();
							 selectParentMateriel();//选择物料表格初始化
							 initFormiCheck();
							 $('.date-picker').datepicker({
								 rtl: App.isRTL(),
								 orientation: "left",
								 autoclose: true,
								 language:"zh-CN"
							 })//初始化icheck控件
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
			 //checkbox初始化
			 var initFormiCheck= function(){
				 FormiCheck.init();
				 $('#isLadderPriceCheck').on('ifChecked', function(event){ //ifCreated 事件应该在插件初始化之前绑定 
					 $scope.priceList.isLadderPrice="1";
					 $scope.isChecked=true;
					 $scope.$apply();
				 }); 
				 $('#isLadderPriceCheck').on('ifUnchecked', function(event){ //ifCreated 事件应该在插件初始化之前绑定 
					 $scope.priceList.isLadderPrice="0";
					 $scope.isChecked=false;
					 $scope.$apply();
				 });
			 }
	/*		 // 构建datatables开始***************************************
			 var tableAjaxUrl = "rest/priceList/getPriceList";
			 var table;
			 function loadPriceListTable(){
				 var a = 0;
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

				 table = $("#sample_priceList")
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
							               ajax : tableAjaxUrl,// 加载数据中价格表数据

							               "aoColumns" : [
							                              {
							                            	  mData : 'serialNum'
							                              },
							                              {
							                            	  mData : 'priceNum'
							                              },  {
							                            	  mData : 'supplyComName'
							                              },{
							                            	  mData : 'materielNum'
							                              }, {
							                            	  mData : 'materielName'
							                              },{
							                            	  mData : 'specifications'
							                              }, {
							                            	  mData : 'unit'
							                              }, {
							                            	  mData : 'unitPrice'
							                              },{
							                            	  mData : 'rate'
							                              },{
							                            	  mData : 'currency'
							                              }, {
							                            	  mData : 'priceEffectiveDate',
							                            	  mRender:function(data){
							                            		  if(data!=""&&data!=null){
							                            			  return timeStamp2ShortString(data);
							                            		  }else{
							                            			  return "";
							                            		  }
							                            	  }
							                              },{
							                            	  mData : 'priceExpirationDate',
							                            	  mRender:function(data){
							                            		  if(data!=""&&data!=null){
							                            			  return timeStamp2ShortString(data);
							                            		  }else{
							                            			  return "";
							                            		  }
							                            	  }
							                              },{
							                            	  mData : 'status'
							                              }],
							                              'aoColumnDefs' : [ {
							                            	  'targets' : 0,
							                            	  'searchable' : false,
							                            	  'orderable' : false,
							                            	  'className' : 'dt-body-center',
							                            	  'render' : function(data,
							                            			  type, full, meta) {
							                            		  return '<input type="checkbox" name="id[]" value="'
							                            		  + $('<div/>')
							                            		  .text(
							                            				  data)
							                            				  .html()
							                            				  + '">';
							                            	  }
							                              },{
							                            	  'targets' : 1,
							                            	  'render' : function(data,
							                            			  type, row, meta) {
							                            		  return '<a   ng-click="showPriceListInfoModal(\''+row.serialNum+'\')">'+data+'</a>';
							                            		  //return data;
							                            	  },"createdCell": function (td, cellData, rowData, row, col) {
							                            		  $compile(td)($scope);
							                            	  }
							                              }  ],

						 })
						 // 构建datatables结束***************************************

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
				 $('#sample_priceList tbody')
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
			 }*/

	 			//***************************************流程处理相关start
	        	var dbBuyPricetable;//待办采购价格table
	        	var dbSalePricetable;//待办销售价格table
				var endTaskBuyPriceTable;//已办采购价格table
				var endTaskSalePriceTable;//已办销售价格table
				

				// 显示采购价格
				$scope.toApplyBuyPrice = function() {
					$('#buyPriceTab a[href="#applyBuyPrice"]').tab('show');
				};
				// 采购价格待办流程
				$scope.toDaibanBuyPrice = function() {
					debugger;
					$('#buyPriceTab a[href="#daibanBuyPrice"]').tab('show');
					dbBuyPricetable = showDbBuyPriceTable();								
				};
				// 采购价格已办流程
				$scope.toYibanBuyPrice = function() {
					$('#buyPriceTab a[href="#yibanBuyPrice"]').tab('show');
					debugger;
					endTaskBuyPriceTable = showYbBuyPriceTable();
				};
				// 显示销售价格
				$scope.toApplySalePrice = function() {
					$('#salePriceTab a[href="#applySalePrice"]').tab('show');
				};
				// 销售价格待办流程
				$scope.toDaibanSalePrice = function() {
					$('#salePriceTab a[href="#daibanSalePrice"]').tab('show');
					dbSalePricetable = showDbSalePriceTable();								
				};
				// 销售价格已办流程
				$scope.toYibanSalePrice = function() {
					$('#salePriceTab a[href="#yibanSalePrice"]').tab('show');
					endTaskSalePriceTable = showYbSalePriceTable();
				};
			 
				// 构建datatables开始***************************************
				var tableAjaxUrl ;
				 var table ;
						
function loadPriceListBuyTable(){
				var a = 0,judgeString='buy';
				  tableAjaxUrl = "rest/priceList/getPriceList?buyOrSale="+judgeString;
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
								
								table = $("#sample_buy")
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
																				"<input type='checkbox' class='checkboxes' value='"+data+"' />" +
																				"<span></span></label>";
																	}
								                              },
								                              {
								                            	  mData : 'priceNum'
								                              },  {
								                            	  mData : 'supplyComName'
								                              },{
								                            	  mData : 'materielNum'
								                              }, {
								                            	  mData : 'materielName'
								                              },{
								                            	  mData : 'specifications'
								                              }, {
								                            	  mData : 'priceType',
								                            	  mRender:function(data){
								                            		 return "采购价格";
								                            	  }
								                              },{
								                            	  mData : 'unit'
								                              }, {
								                            	  mData : 'unitPrice'
								                              },{
								                            	  mData : 'rate'
								                              },{
								                            	  mData : 'currency'
								                              }, {
								                            	  mData : 'priceEffectiveDate',
								                            	  mRender:function(data){
								                            		  if(data!=""&&data!=null){
								                            			  return timeStamp2ShortString(data);
								                            		  }else{
								                            			  return "";
								                            		  }
								                            	  }
								                              },{
								                            	  mData : 'priceExpirationDate',
								                            	  mRender:function(data){
								                            		  if(data!=""&&data!=null){
								                            			  return timeStamp2ShortString(data);
								                            		  }else{
								                            			  return "";
								                            		  }
								                            	  }
								                              },/*{
								                            	  mData : 'status'
								                              }*/
								                              { mData: 'processBase',
									                            	mRender:function(data,
									    									type, row, meta){
									                            		if(data!=""&&data!=null){
									                            			if(data.status=="PENDING"||data.status=="WAITING_FOR_APPROVAL"){
									    										return '<span  class="label label-sm label-warning ng-scope">审核中</span>';
									    									}else if(data.status=="APPROVAL_SUCCESS"){
									    										if(row.status==1){
									    											return '<span  class="label label-sm label-success ng-scope">生效中</span>';
									    										}else if(row.status==2){
									    											return '<span  class="label label-sm label-success ng-scope">将失效</span>';
									    										}else if(row.status==3){
									    											return '<span  class="label label-sm label-success ng-scope">已失效</span>';
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
									                            }
								                              ],
								                              'aoColumnDefs' : [ 
								                                                 {
								                            	  'targets' : 0,
								                            	  'searchable' : false,
								                            	  'orderable' : false,
								                            	  'className' : 'dt-body-center',
								                            	  'render' : function(data,
								                            			  type, full, meta) {
								                            		  return '<input type="checkbox" name="id[]" value="'
								                            		  + $('<div/>')
								                            		  .text(
								                            				  data)
								                            				  .html()
								                            				  + '">';
								                            	  }
								                              },{
								                            	  'targets' : 1,
								                            	  'render' : function(data,
								                            			  type, row, meta) {
								                            		  return '<a   ng-click="showPriceListInfoModal(\''+row.serialNum+'\',\''+judgeString+'\')">'+data+'</a>';
								                            		  //return data;
								                            	  },"createdCell": function (td, cellData, rowData, row, col) {
								                            		  $compile(td)($scope);
								                            	  }
								                              }  ],
											
										});
								$("#sample_buy").find(".group-checkable").change(function() {
						            var e = jQuery(this).attr("data-set"),
						            t = jQuery(this).is(":checked");
						            jQuery(e).each(function() {
						                t ? ($(this).prop("checked", !0), $(this).parents("tr").addClass("active")) : ($(this).prop("checked", !1), $(this).parents("tr").removeClass("active"))
						            })
						        }),
						        $("#sample_buy").on("change", "tbody tr .checkboxes",
						        function() {
						            $(this).parents("tr").toggleClass("active")
						        })
						        return table;
						/*		// 添加checkbox功能***************************************
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

function loadPriceListSaleTable(){
	var a = 0,judgeString='sale';
	 tableAjaxUrl = "rest/priceList/getPriceList?buyOrSale="+judgeString;
/*	App.getViewPort().width < App
			.getResponsiveBreakpoint("md") ? $(
			".page-header").hasClass(
			"page-header-fixed-mobile")
			&& (a = $(".page-header").outerHeight(!0))
			: $(".page-header").hasClass(
					"navbar-fixed-top") ? a = $(
					".page-header").outerHeight(!0)
					: $("body").hasClass(
							"page-header-fixed")
							&& (a = 64);*/
	table = $("#sample_sale")
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
	                              },
	                              {
	                            	  mData : 'priceNum'
	                              },  {
	                            	  mData : 'buyComName'
	                              },{
	                            	  mData : 'materielNum'
	                              }, {
	                            	  mData : 'materielName'
	                              },{
	                            	  mData : 'specifications'
	                              }, {
	                            	  mData : 'priceType',
	                            	  mRender:function(data){
		                            		 return "销售价格";
		                            	  }
	                              },{
	                            	  mData : 'unit'
	                              }, {
	                            	  mData : 'unitPrice'
	                              },{
	                            	  mData : 'rate'
	                              },{
	                            	  mData : 'currency'
	                              }, {
	                            	  mData : 'priceEffectiveDate',
	                            	  mRender:function(data){
	                            		  if(data!=""&&data!=null){
	                            			  return timeStamp2ShortString(data);
	                            		  }else{
	                            			  return "";
	                            		  }
	                            	  }
	                              },{
	                            	  mData : 'priceExpirationDate',
	                            	  mRender:function(data){
	                            		  if(data!=""&&data!=null){
	                            			  return timeStamp2ShortString(data);
	                            		  }else{
	                            			  return "";
	                            		  }
	                            	  }
	                              },/*{
	                            	  mData : 'status'
	                              }*/ { mData: 'processBase',
		                            	mRender:function(data,
		    									type, row, meta){
		                            		if(data!=""&&data!=null){
		                            			if(data.status=="PENDING"||data.status=="WAITING_FOR_APPROVAL"){
		    										return '<span  class="label label-sm label-warning ng-scope">审核中</span>';
		    									}else if(data.status=="APPROVAL_SUCCESS"){
		    										if(row.status==1){
		    											return '<span  class="label label-sm label-success ng-scope">生效中</span>';
		    										}else if(row.status==2){
		    											return '<span  class="label label-sm label-success ng-scope">将失效</span>';
		    										}else if(row.status==3){
		    											return '<span  class="label label-sm label-success ng-scope">已失效</span>';
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
		                            }],
	                              'aoColumnDefs' : [ {
	                            	  'targets' : 0,
	                            	  'searchable' : false,
	                            	  'orderable' : false,
	                            	  'className' : 'dt-body-center',
	                            	  'render' : function(data,
	                            			  type, full, meta) {
	                            		  return '<input type="checkbox" name="id[]" value="'
	                            		  + $('<div/>')
	                            		  .text(
	                            				  data)
	                            				  .html()
	                            				  + '">';
	                            	  }
	                              },{
	                            	  'targets' : 1,
	                            	  'render' : function(data,
	                            			  type, row, meta) {
	                            		  return '<a   ng-click="showPriceListInfoModal(\''+row.serialNum+'\',\''+judgeString+'\')">'+data+'</a>';
	                            		  //return data;
	                            	  },"createdCell": function (td, cellData, rowData, row, col) {
	                            		  $compile(td)($scope);
	                            	  }
	                              }  ],
				
			});
	$("#sample_sale").find(".group-checkable").change(function() {
        var e = jQuery(this).attr("data-set"),
        t = jQuery(this).is(":checked");
        jQuery(e).each(function() {
            t ? ($(this).prop("checked", !0), $(this).parents("tr").addClass("active")) : ($(this).prop("checked", !1), $(this).parents("tr").removeClass("active"))
        })
    }),
    $("#sample_sale").on("change", "tbody tr .checkboxes",
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
*/	// 构建datatables结束***************************************
	}
			 // 添加价格开始***************************************
			 $scope.addPriceList = function(judgeString) {
				 debugger;
				 $state.go('addPriceList',{buyOrSale:judgeString,type:'edit'},{reload:true}); 
			 }
			 $scope.editPriceList = function(){
				 debugger;
				 var priceList=$scope.priceList;
				 getPriceListInfo($scope.priceList.serialNum);
				 if($scope.buyOrSale.indexOf("buy")>-1){
					 initSuppliers();
				 }else{
					 initCustomers(); 
				 }
				 $scope.priceList=priceList;
				 $scope.priceListView =false;
				 $scope.priceListAdd =true ;
				 $scope.priceListEdit =false;
			 }
			 $scope.cancelEditPriceList = function(){
				 getPriceListInfo($scope.priceList.serialNum);
				 $scope.priceListView =true;
				 $scope.priceListAdd =false;
				 $scope.priceListEdit =true;
			 }		
			 function judgeDate (){
				 var priceEffectiveDate=$scope.priceList.priceEffectiveDate;
				 var priceExpirationDate=$scope.priceList.priceExpirationDate;
				 debugger;
				 var d =daysBetween(priceExpirationDate,priceEffectiveDate);
				 if(d<0&&priceExpirationDate!=null&&priceExpirationDate!=""){
					 toastr.warning("价格生效期时间不能大于价格失效期时间  ！");
					 $("#priceEffectiveDate").focus();
					 return false;
				 }
				 return true;
			 }
			 $scope.savePriceList= function() {
				 if($('#priceListForm').valid()&&judgeDate()){//表单验证通过则执行添加功能
					 if($scope.buyOrSale.indexOf("buy")>-1){
						 $scope.priceList.priceType='buyPrice';
					 }else{
						 $scope.priceList.priceType='salePrice';
					 }
					 
					 $rootScope.judgeIsExist("price",$scope.priceList.priceNum, $scope.priceList.serialNum,function(result){
			    			var 	isExist = result;
			    		debugger;
			    		if(isExist){
			    			toastr.error('价格编号重复！');
			    			return;
			    		}else{
			    			 priceListService.savePriceList($scope.priceList)
							 .then(
									 function(data) {
										 debugger;
										 toastr.success("保存价格数据成功！");
										 data.priceEffectiveDate=timeStamp2ShortString(data.priceEffectiveDate);
										 data.priceExpirationDate=timeStamp2ShortString(data.priceExpirationDate);
										 $scope.priceList =data;
										 $scope.priceListView =true;
										 $scope.priceListAdd =false;
										 $scope.priceListEdit =true;
										 $(".alert-danger").hide();
									 },
									 function(errResponse) {
										 toastr.warning("保存错误！");
										 console
										 .error('Error while creating User');
									 }
							 );
			    		}
			    		
			    		});
					
				 }
			 };	
			 // 删除价格开始***************************************							
			 $scope.delPriceList= function(judgeString) {
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
					 $('#delPriceList'+judgeString+'Modal').modal('show');// 打开确认删除模态框

					 $scope.confirmDelPriceList = function() {										
						 priceListService
						 .delPriceLists(ids)
						 .then(
								 function(data) {
									 $('#delPriceList'+judgeString+'Modal').modal(
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
			 // 添加价格结束***************************************
			 $scope.showBuyOrSale=function(judgeString){
				 $state.go('priceList',{buyOrSale:judgeString}); //切换tab
			}
			 // 修改价格开始***************************************							
			 $scope.toEditPriceListPage = function(judgeString) {//弹出框修改价格信息
				 debugger;
				 if(table.rows('.active').data().length != 1){
						showToastr('toast-top-center', 'warning', '请选择一条数据进行修改！')
					}else{
						if(table.row('.active').data().processBase !=null){
							showToastr('toast-top-center', 'warning', '该条数据已经申请流程审批，不能进行修改！')
						}else {
							 var serialNum = table.$('input[type="checkbox"]:checked').val();
							 $state.go("addPriceList",{buyOrSale:serialNum+judgeString});
						}
					} 
				/* var id_count = table.$('input[type="checkbox"]:checked').length;
				 if(id_count==0){
					 toastr.warning("请选择一条数据进行编辑");
				 }else if(id_count>1){
					 toastr.warning("只能选择一条数据进行编辑");
				 }else{
					 var serialNum = table.$('input[type="checkbox"]:checked').val();
							 $state.go("addPriceList",{buyOrSale:serialNum+judgeString});
				 }*/
			 };
			 // 修改价格结束***************************************							
			 /*$scope.showOrHide = function() {//控制阶梯div的显示与隐藏
								 $scope.isChecked=!$scope.isChecked;
								 if($scope.isChecked){
									 $scope.priceList.isLadderPrice='1';
								 }else{
									 $scope.priceList.isLadderPrice='0'; 
								 }
							 }*/

			 // 页面加载完成后调用，验证输入框
			 $scope.$watch('$viewContentLoaded', function() {  
				 var e = $("#priceListForm"),
				 r = $(".alert-danger", e),
				 i = $(".alert-success", e);
				 e.validate({
					 errorElement: "span",
					 errorClass: "help-block help-block-error",
					 focusInvalid: !1,
					 ignore: "",
					 messages: {
						 priceNum:{required:"价格编号不能为空！"},
						 materielNum:{required:"未选择物料！"},
						 priceType:{required:"未选择价格类型！"},
						 buyComId:{required:"未选择采购商！"},
						 supplyComId:{required:"未选择供应商商！"},
						 currency:{required:"未选择币种！"},
						 rate:{digits:"必须是数字！",required:"税率不能为空！"},
						 unitPrice: {digits:"必须是数字！",required:"单价不能为空！"},
						 inclusivePrice:{digits:"必须是数字！"},
						 topPrice:{},
						 floorPrice:{},
						 priceEffectiveDate: {required:"价格生效期未选择！" },
						 priceExpirationDate: {required:"价格失效期未选择！" },
						 ladderType:{required:"未选择阶梯类型！" },
						 countStart:{digits:"必须是数字！"},
						 countEnd:{digits:"必须是数字！"},
						 price:{digits:"必须是数字！"},
						 inclusivePrice:{},
						 ladderInclusivePrice:{}
					 },
					 rules: {
						 priceNum:{required:true},
						 materielNum:{required:true},
						 priceType:{required:true},
						 buyComId:{required:true},
						 supplyComId:{required:true},
						 currency:{required:true},
						 rate:{digits:true,required:!0},
						 unitPrice: {digits:true,required:!0},
						 inclusivePrice:{digits:true},
						 topPrice:{},
						 floorPrice:{toppriceNumCheck:!0},
						 priceEffectiveDate: {required:true },
						 priceExpirationDate: {required:true },
						 ladderType:{required:true },
						 countStart:{digits:true},
						 countEnd:{digits:true},
						 price:{digits:true},
						 inclusivePrice:{inclusivePriceNumCheck:!0},
						 ladderInclusivePrice:{digits:true,ladderPriceNumCheck:!0}
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
			 jQuery.validator.addMethod("inclusivePriceNumCheck", function (value, element) {//含税价格判断
	    			debugger;
					$(element).removeData();
					if(/^[-\+]?\d+(\.\d+)?$/.test(value)==false||value==NaN||(Number(value)*100+"").indexOf(".")>-1){
						toastr.warning("只能包含小数点和数字,且只能有两位小数");
						/*$("#inclusivePrice").focus();*/
						}
					return this.optional(element) || Number($(element).data("unitprice")) == NaN?false:(Number($(element).data("unitprice"))-value<= 0);
				}, "不含税单价不能超过含税价格");
			 jQuery.validator.addMethod("toppriceNumCheck", function (value, element) {//最低价最高价判断
	    			debugger;
					$(element).removeData();
					return this.optional(element) || Number($(element).data("topprice")) == NaN?false:(Number($(element).data("topprice"))-value>= 0);
				}, "最低限价不能超过最高限价");
			 jQuery.validator.addMethod("ladderPriceNumCheck", function (value, element) {//梯度价格判断
	    			debugger;
					$(element).removeData();
					if(/^[-\+]?\d+(\.\d+)?$/.test(value)==false||value==NaN||(Number(value)*100+"").indexOf(".")>-1){
						toastr.warning("只能包含小数点和数字,且只能有两位小数");
						/*$("#inclusivePrice").focus();*/
						}
					return this.optional(element) || Number($(element).data("ladderprice")) == NaN?false:(Number($(element).data("ladderprice"))-value<= 0);
				}, "梯度单价不能超过梯度含税价格");
			 function getLadderPriceInfo(serialNum){//获取阶梯价格详情
				 if(!handle.isNull(serialNum)){
					 debugger;
					 var promise =priceListService.getLadderPriceInfo(serialNum);
					 promise.then(function(data){
						 debugger;
						 $scope.ladderprices = data.data;
						 _index=data.data.length-1;

					 });
				 }
			 }
			 /**
			  * 阶梯价格加一行
			  */
			 $scope.addRepeat = function(){
				 if(handle.isNull($scope.priceList)||handle.isNull($scope.priceList.serialNum)){
					 toastr.warning("您的价格信息还未保存");
					 return;
				 }else{
					 _index++;
					 $scope.ladderprices[_index] = {};
				 }
			 };
			 $scope. changeValue=function(judgeString,index){//选中企业为编号赋值
				 var  comSerials=new Array();
				 if($scope.comSerials==undefined){
					 $scope.comSerials=comSerials;
				 }else{
					 comSerials= $scope.comSerials;
					 for(var  i=0;i<comSerials.length;i++){
						 if(comSerials[i]==$scope.priceComs[index].comSerial){
							 toastr.warning("该采购商已经存在,请重新选择!");
							 return;
						 }
					 }
				 }
				 if(judgeString=='buy'){
					 for( var i in $scope.customers){
						 if($scope.customers[i].comId==$scope.priceComs[index].comSerial){
							 $scope.priceComs[index].comNum=$scope.customers[i].comNum;
							 $scope.priceComs[index].comName=$scope.customers[i].comName;
							 comSerials.push($scope.priceComs[index].comSerial);
							 $scope.comSerials=comSerials;
							/* $scope.copycustomers=angular.copy($scope.customers);
							 delete  $scope.copycustomers[i];
							 $scope.customers= $scope.copycustomers;*/
							 break;
						 }
					 }
				}else if(judgeString=='supply'){
						 for( var i in $scope.suppliers){
							 if($scope.suppliers[i].comId==$scope.priceComs[index].comSerial){
								 $scope.priceComs[index].comNum=$scope.suppliers[i].comNum;
								 $scope.priceComs[index].comName=$scope.suppliers[i].comName;
								 comSerials.push($scope.priceComs[index].comSerial);
								 $scope.comSerials=comSerials;
								/* $scope.copysuppliers=angular.copy($scope.suppliers);
								 delete  $scope.copysuppliers[i];
								 delete  $scope.suppliers[i];
								 $scope.suppliers= $scope.copysuppliers;*/
								
								 break;
							 }
					 }
					 
				 }
			 }
			 
			 $scope.editPriceCom=function(){//编辑采供应商
				 $scope.buyComInfoInput=true;
				 $scope.buyComsInfoShow=false;
				 $scope.buyComsInfoShowBtn=true;
			 }
			 $scope.cancelPriceCom=function(){//取消编辑采供应商
				 $scope.buyComInfoInput=false;
				 $scope.buyComsInfoShow=true;
				 $scope.buyComsInfoShowBtn=false;
			 }
			 $scope. savePriceCom=function(){//保存采供应商
				 if(handle.isNull($scope.priceList)||handle.isNull($scope.priceList.serialNum)){
					 toastr.warning("您的价格信息还未保存！");
					 return;
				 }else{
					 for(var i=0;i<$scope.priceComs.length;i++){
						 $scope.priceComs[i].priceSerial = $scope.priceList.serialNum;//附上价格流水号
					 }
					 handle.blockUI();
					 var promise = priceListService.savePriceComs($scope.priceComs);
					 promise.then(function(data){
						 if(!handle.isNull(data)){
							 toastr.success("保存成功");
							 debugger;
							 handle.unblockUI();
							 $scope.priceComs = data.data;
							 $scope.buyComsInfoShowBtn =false;
							 $scope.buyComsInfoShow =true;
							 $scope.buyComInfoInput =false;
						 }else{
							 toastr.error("保存失败！");
							 handle.unblockUI();
						 }
					 },function(data){
						 handle.unblockUI();
						 toastr.error("保存失败！");
						 console.log(data);
					 });
				 }
			 }
			 
			 //采购商/供应商加一行
			 $scope.addRepeatForCom = function(){
				 if(handle.isNull($scope.priceList)||handle.isNull($scope.priceList.serialNum)){
					 toastr.warning("您的价格信息还未保存");
					 return;
				 }else{
					 _index_com++;
					 $scope.priceComs[_index_com] = {};
				 }
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
			    	 if(attr=='inclusivePrice'&&$scope.priceList.rate!=undefined){
	                       var inclusivePrice=$scope.priceList.inclusivePrice;
			    		 $scope.priceList.unitPrice=inclusivePrice*(1-$scope.priceList.rate/100);
			    	 }
		    	 }
			 /**
			  * 阶梯价格删除一行
			  */
			 $scope.deleteRepeat = function(){
				 $scope.ladderprices.splice(_index,1);
				 _index--;
			 };
			 /**采购商/供应商删除一行
			  */
			 $scope.deleteRepeatForCom = function(){
				 $scope.priceComs.splice(_index_com,1);
				 _index_com--;
			 };

			 /**
			  * 显示价格信息
			  */
			 $scope.showPriceListInfoModal = function(serialNum,judgeString){
				 $state.go("addPriceList",{buyOrSale:serialNum+judgeString+'view'});
				
			 };

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
					 $scope.priceList.file=response.filename;
					 /*for(var i=0;i < $scope.companyQualifications.length;i++){
							 		  		  if($scope.qualification_temp==$scope.companyQualifications[i]){
							 		  			$scope.companyQualifications[i].qualificatioImage = response.filename;
							 		  		  }
							 		  	  }*/
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

			 //上传附件end
			 //选择物料弹框
			 var selectIndex;
			 $scope.selectMateriel = function(index){
				 selectIndex = index;
			 }
			 var selectParentTable;
			 var selectParentMateriel = function() {
				 debugger;
				 a = 0;
				 App.getViewPort().width < App.getResponsiveBreakpoint("md") ? $(".page-header").hasClass("page-header-fixed-mobile") && (a = $(".page-header").outerHeight(!0)) : $(".page-header").hasClass("navbar-fixed-top") ? a = $(".page-header").outerHeight(!0) : $("body").hasClass("page-header-fixed") && (a = 64);
				 selectParentTable = $("#select_sample_2")
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
//					 serverSide: true,
					 ajax: "rest/materiel/findMaterielList?isLatestVersion=1",//加载数据中
					 "aoColumns": [
					               { mData: 'serialNum' },
					               { mData: 'materielNum' },
					               { mData: 'materielName' },
					               { mData: 'specifications' },
					               { mData: 'unit' },
					               /*{ mData: 'parentMateriel' },*/
					               { mData: 'typeName' },
					               { mData: 'originCountry' },
					               { mData: 'brand' },
					               /*{ mData: 'brand' },*/
					               { mData: 'versionNO' },
					               /*{ mData: 'status' }*/
					               ],
					               'aoColumnDefs' : [ {
					            	   'targets' : 0,
					            	   'searchable' : false,
					            	   'orderable' : false,

					            	   'render' : function(data,
					            			   type, row, meta) {
					            		   return '<input type="radio" ng-click="selectParent(\''+data+'\',\''+row.materielName+'\',\''+row.materielNum+'\',\''+row.specifications+'\',\''+row.unit+'\')" name="serialNum[]" value="'
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

					               }/*,{
					            	   'targets' : 5,

					            	   'render' : function(data,
					            			   type, full, meta) {
					            		   if(data==null){
					            			   return  ''
					            		   }else{
					            			   return  data.materielName
					            		   }
					            	   }
					               }*/ ]

				 }).on('order.dt',
						 function() {
					 console.log('排序');
				 })
			 };

			 //设置当前选中的物料行
			 $scope.selectParent  = function(serialNum,materielName,materielNum,specifications,unit) {
				 $scope.row = {};
				 $scope.row.serialNum = serialNum;//物料流水
				 $scope.row.materielName=materielName;//物料名称
				 $scope.row.materielNum=materielNum;//物料编号
				 $scope.row.specifications=specifications;//规格型号
				 $scope.row.unit=unit;//单位
			 }; 
			 // 确认选择开始***************************************
			 $scope.confirmSelect = function() {
				 var ids = '';
				 // Iterate over all checkboxes in the table
				 selectParentTable.$('input[type="radio"]').each(
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
				 $scope.priceList.materielSerial = $scope.row.serialNum;//物料流水
				 $scope.priceList.materielName=$scope.row.materielName;//物料名称
				 $scope.priceList.materielNum=$scope.row.materielNum;//物料编号
				 $scope.priceList.specifications=$scope.row.specifications;//规格型号
				 $scope.priceList.unit=$scope.row.unit;//单位

				 $('#basicMaterielInfo').modal('hide');// 删除成功后关闭模态框
				 $(".modal-backdrop").remove();
			 };


			 $scope.editLadderPrice=function(){
				 $scope.ladderpriceView = false;
				 $scope.ladderpriceEdit = true;
				 $scope.ladderpriceAdd = false;
			 }
			 $scope.cancelEditLadderPrice=function(){
				 getPriceListInfo($scope.priceList.serialNum);
				 $scope.ladderpriceView = true;
				 $scope.ladderpriceEdit = false;
				 $scope.ladderpriceAdd = true;
			 }
			 /**
			  * 保存阶梯价格信息
			  */
			  $scope.saveLadderPrice = function(){
				 if(handle.isNull($scope.priceList)||handle.isNull($scope.priceList.serialNum)){
					 toastr.warning("您的价格信息还未保存！");
					 return;
				 }else{
					 debugger;
					 for(var i=0;i<$scope.ladderprices.length;i++){
						 $scope.ladderprices[i].priceSerial = $scope.priceList.serialNum;
						 $scope.ladderprices[i].createTime = null;
						 $scope.ladderprices[i].updateTime = null;
					 }
				 }
				 if($('#priceListForm').valid()){//
					 handle.blockUI();
					 var promise = priceListService.saveLadderPrice($scope.ladderprices);
					 promise.then(function(data){
						 if(!handle.isNull(data)){
							 toastr.success("保存成功");
							 debugger;
							 handle.unblockUI();
							 $(".alert-danger").hide();
							 $scope.ladderprices = data.data;
							 getPriceListInfo($scope.priceList.serialNum);
							 $scope.priceListAdd=false;
							 $scope.priceListEdit=true;//隐藏取消
							 $scope.priceListView=true;//隐藏取消
							 $scope.ladderpriceView = true;
							 $scope.ladderpriceEdit = false;
							 $scope.ladderpriceAdd = true;
						 }else{
							 toastr.error("保存失败！");
							 handle.unblockUI();
						 }
					 },function(data){
						 handle.unblockUI();
						 toastr.error("保存失败！");
						 console.log(data);
					 });
				 }

			  }
			  function getPriceListInfo(serialNum){//获取价格详情
					 if(!handle.isNull(serialNum)){
						 debugger;
						 var promise =priceListService.selectBySerialNum(serialNum);
						 promise.then(function(data){
							 debugger;
							 if(serialNum.length>32){
							 data.priceList.priceEffectiveDate=timeStamp2ShortString(data.priceList.priceEffectiveDate);
							 data.priceList.priceExpirationDate=timeStamp2ShortString(data.priceList.priceExpirationDate);
							 $scope.priceList = data.priceList;
							 $scope.ladderprices=data.ladderPrices;
							 _index=data.ladderPrices.length-1;
							 _index_com=data.priceComs.length-1;
							 $scope.priceLists=data.priceLists;
							 $scope.priceComs=data.priceComs;
							 if($scope.priceList.isLadderPrice=='1'){
								 $('#isLadderPriceCheck').iCheck('check'); 
								 $scope.isChecked=true;
							 }  
							 }
							 $scope.buyCom=data.buyCom;
							 $scope.supplyCom=data.supplyCom;
						 });
					 }else{
						 debugger;
						 var promise =priceListService.selectBySerialNum(serialNum);
						 promise.then(function(data){
							 debugger;
							 $scope.buyCom=data.buyCom;
							 $scope.supplyCom=data.supplyCom;
						 });
					 }
				 }
			  /**
			   * 下载EXCEL模板
			   */
			  $scope.downloadImportTemp = function(){
				  window.location.href=$rootScope.basePath+"/rest/priceList/downloadImportTemp";
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
				  var promise = WarehouseService.uploadExcel();
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
			  $scope.exportPriceList = function(judgeString){
				  handle.blockUI("正在导出数据，请稍后"); 
				  window.location.href=$rootScope.basePath+"/rest/priceList/exportPriceList?buyOrSale="+judgeString;
				  handle.unblockUI(); 
			  }
			  /**
				 * 加载供应商数据
				 */
				var initSuppliers = function(){
					var promise = orderService.initSuppliers();
			        	promise.then(function(data){
			        		$scope.suppliers = data.data;
			        		setTimeout(function () {
			        			$("#supplyComId").selectpicker({
			                        showSubtext: true
			                    });
			        			$('#supplyComId').selectpicker('refresh');//刷新插件
			        			
			                }, 100);
			        		
			        	},function(data){
			        		//调用承诺接口reject();
			        	});
				}
				 /**
				 * 加载供应商数据
				 */
				var initSuppliersForCom = function(){
					var promise = orderService.initSuppliers();
			        	promise.then(function(data){
			        		$scope.suppliers = data.data;
			        	},function(data){
			        		//调用承诺接口reject();
			        	});
				}
				 /**
				 * 加载采购商(客户)数据
				 */
				var initCustomersForCom = function(){
					var promise = orderService.initCustomers();
			        	promise.then(function(data){
			        		$scope.customers = data.data;
			        	},function(data){
			        		//调用承诺接口reject();
			        	});
				}
				 /**
				 * 加载采购商(客户)数据
				 */
				var initCustomers = function(){
					var promise = orderService.initCustomers();
			        	promise.then(function(data){
			        		$scope.customers = data.data;
			        		setTimeout(function () {
			        			$("#buyComId").selectpicker({
			                        showSubtext: true
			                    });
			        			$('#buyComId').selectpicker('refresh');//刷新插件
			        			
			                }, 100);
			        		
			        	},function(data){
			        		//调用承诺接口reject();
			        	});
				}
			  $('#import').on('hide.bs.modal', function (e) { 
				  $("#resetFile").trigger("click");
			  })
			  // 页面加载完成后调用，验证输入框
			  $scope.$watch('$viewContentLoaded', function() {  
				  var form1 = $('#ladderpriceForm');
				  var error1 = $('.alert-danger', form1);
				  var success1 = $('.alert-success', form1);
				  debugger;
				  form1.validate({
					  errorElement: 'span', //default input error message container
					  errorClass: 'help-block help-block-error', // default input error message class
					  focusInvalid: false, // do not focus the last invalid input
					  ignore: "",  // validate all fields including form hidden input
					  messages:  {
						  countStart:{digits:"必须是数字！"},
						  countEnd:{digits:"必须是数字！"},
						  price:{digits:"必须是数字！"},
						  inclusivePrice:{digits:"必须是数字！"}
					  },
					  rules: {
						  countStart:{digits:true},
						  countEnd:{digits:true},
						  price:{digits:true},
						  inclusivePrice:{digits:true}
					  },

					  invalidHandler: function (event, validator) { //display error alert on form submit              
						  success1.hide();
					  error1.show();
					  App.scrollTo(error1, -200);
					  },

					  errorPlacement: function (error, element) { // render error placement for each input type
						  var cont = $(element).parent('.input-group');
						  if (cont.size() > 0) {
							  cont.after(error);
						  } else {
							  element.after(error);
						  }
					  },

					  highlight: function (element) { // hightlight error inputs

						  $(element)
						  .closest('.form-group').addClass('has-error'); // set error class to the control group
					  },

					  unhighlight: function (element) { // revert the change done by hightlight
						  $(element)
						  .closest('.form-group').removeClass('has-error'); // set error class to the control group
					  },

					  success: function (label) {
						  label
						  .closest('.form-group').removeClass('has-error'); // set success class to the control group
					  },

					  submitHandler: function (form) {
						  success1.show();
						  error1.hide();
					  }
				  });


			  }); 
			  
		      //********审批流程列表****************//
		        function showDbBuyPriceTable(){
		        	
		        	var table = $("#dbBuyPriceTable")
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
		    										}else{
		        									priceListService
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
			        													console.log(ids+"---"+result.priceList.processInstanceId);
			        													$state.go('approvalPriceApply',{buyOrSale:result.priceList.serialNum+'buy', serialNum:result.priceList.serialNum,taskId:ids, comments:comments,processInstanceId:result.priceList.processInstanceId});
			        												}else{
			        													$state.go('editPriceApply',{buyOrSale:result.priceList.serialNum+'buy', serialNum:result.priceList.serialNum,taskId:ids, comments:comments,processInstanceId:result.priceList.processInstanceId});
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
		        									claimTask(ids, 'dbBuyPriceTable');
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
		        						+ "/rest/processAction/todoTask/" + 'buyPrice',// 加载待办列表数据

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
		        			
		        			
		        			$("#dbBuyPriceTable").find(".group-checkable").change(function() {
					            var e = jQuery(this).attr("data-set"),
					            t = jQuery(this).is(":checked");
					            jQuery(e).each(function() {
					                t ? ($(this).prop("checked", !0), $(this).parents("tr").addClass("active")) : ($(this).prop("checked", !1), $(this).parents("tr").removeClass("active"))
					            })
					        }),
					        $("#dbBuyPriceTable").on("change", "tbody tr .checkboxes",
					        function() {
					            $(this).parents("tr").toggleClass("active")
					        })
	        
		        			return table;
		        	
		        	
		        }
		        $scope.cancelPage  = function(judgeString) {// 取消申请
		        	 $state.go("priceList",{buyOrSale:judgeString});
		        };
		        //********审批流程start****************//
			       $scope.submitPriceApply= function(judgeString) {// 进入申请审批页面
			        	if(table.rows('.active').data().length != 1){
			    			showToastr('toast-top-center', 'warning', '请选择一条任务进行流程申请！')
			    		}else{
			    			var processBase = table.row('.active').data().processBase;
			    			if(processBase != null){
			    				showToastr('toast-top-center', 'warning', '该价格已发起流程审批，不能再次申请！')
			    			}else $state.go('submitPriceApply',{buyOrSale:table.row('.active').data().serialNum+judgeString});
			    		}     	
			        };
			        
			        
			        $scope.confirmPriceApply  = function(judgeString) {// 进入提交申请
			        	$scope.submitPriceList = {}
			        	$scope.submitPriceList.serialNum = $scope.priceList.serialNum;
			        	$scope.submitPriceList.remark = $scope.priceList.remark;
			        	if(judgeString=='buy'){
			        		$scope.submitPriceList.priceType="buyPrice";
			        	}else{
			        		$scope.submitPriceList.priceType="salePrice";
			        	}
			        	//启动流程
			        	priceListService.startPriceProcess($scope.submitPriceList).then(
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
		        function doPrice(_url, mydata, modal){
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
		    	$scope.pricePass = function(judgeString) {
		    		debugger;
		    		console.log( $("#taskId").val()+"--");
		    	    var mydata={"processInstanceId":$("#processInstanceId").val(),"priceId":$scope.priceList.serialNum,"content":$("#content").val(),
		    				"completeFlag":true,"priceType":judgeString};
		    	    var _url = ctx + "rest/priceList/complate/" + $("#taskId").val();
		    	    doPrice(_url, mydata, 'audit');
		    	};
		    	//审批不通过
		    	$scope.priceUnPass = function(judgeString) {
		    		debugger;
		    		var mydata={"processInstanceId":$("#processInstanceId").val(),"priceId":$scope.priceList.serialNum,"content":$("#content").val(),
		    				"completeFlag":false,"priceType":judgeString};
		    		var _url = ctx + "rest/priceList/complate/" + $("#taskId").val();
		    		doPrice(_url, mydata, 'audit');
		    	};
		    	
		    	//重新申请
		    	$scope.replyPrice = function(judgeString) {
		    		debugger;
		    	    var mydata={"processInstanceId":$("#processInstanceId").val(),
		    				"reApply":true,"priceId":$scope.priceList.serialNum,"reason":$scope.priceList.reason,"priceType":judgeString};
		    		var _url = ctx + "rest/priceList/modifyPrice/" + $("#taskId").val();
		    		doPrice(_url, mydata, 'modify');
		    	};
		    	//取消申请
		    	$scope.cancelApplyPrice = function(judgeString) {
		    		debugger;
		    	     var mydata={"processInstanceId":$("#processInstanceId").val(),
		    				"reApply":false,"priceId":$scope.priceList.serialNum,"reason":$scope.priceList.reason,"priceType":judgeString};
		    		var _url = ctx + "rest/priceList/modifyPrice/" + $("#taskId").val();
		    		doPrice(_url, mydata, 'modify' );
		    	};

		        function showYbBuyPriceTable(){
		        	var endTaskTable = $("#endTaskBuyPriceTable").DataTable(
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
		        				order : [ [ 1, "asc" ] ],// 默认排序列及排序方式
		        				bRetrieve : true,
		        				lengthMenu : [
		        						[ 5, 10, 15, 30, -1 ],
		        						[ 5, 10, 15, 30,
		        								"All" ] ],
		        				pageLength : 10,// 每页显示数量
		        				processing : true,// loading等待框

		        				ajax : ctx
		        						+ "/rest/processAction/endTask/"+'buyPrice',// 加载已办列表数据

		        				"aoColumns" : [
		        						{
		        							mData : 'businessType',
		        							mRender : function(
		        									data) {
		        								return "采购价格申请";
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
		        								return "<a href='javascript:void(0);' onclick=\"revoke('"+row.taskId+"','"+row.processInstanceId+"','endTaskBuyPriceTable')\">撤销</a>";
		        							}
		        						}
		        						]

		        			})
		         return endTaskTable;
		        }
    function showDbSalePriceTable(){
		        	
		        	var table = $("#dbSalePriceTable")
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
		    										}else{
		        									priceListService
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
			        													$state.go('approvalPriceApply',{buyOrSale:result.priceList.serialNum+'sale',serialNum:result.priceList.serialNum, taskId:ids, comments:comments,processInstanceId:result.priceList.processInstanceId});
			        												}else{
			        													$state.go('editPriceApply',{buyOrSale:result.priceList.serialNum+'sale',serialNum:result.priceList.serialNum, taskId:ids, comments:comments,processInstanceId:result.priceList.processInstanceId});
			        												}
			        											},
															function(errResponse) {
			        												toastr.warning("办理失败！");
			        												console.error('Error while apply ap');
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
		        									claimTask(ids, 'dbSalePriceTable');
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
		        						+ "/rest/processAction/todoTask/" + 'salePrice',// 加载待办列表数据

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
		        			
		        			
		        			$("#dbSalePriceTable").find(".group-checkable").change(function() {
					            var e = jQuery(this).attr("data-set"),
					            t = jQuery(this).is(":checked");
					            jQuery(e).each(function() {
					                t ? ($(this).prop("checked", !0), $(this).parents("tr").addClass("active")) : ($(this).prop("checked", !1), $(this).parents("tr").removeClass("active"))
					            })
					        }),
					        $("#dbSalePriceTable").on("change", "tbody tr .checkboxes",
					        function() {
					            $(this).parents("tr").toggleClass("active")
					        })
	        
		        			return table;
		        	
		        	
		        }

		        function showYbSalePriceTable(){
		        	var endTaskTable = $("#endTaskSalePriceTable").DataTable(
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
		        				order : [ [ 1, "asc" ] ],// 默认排序列及排序方式
		        				bRetrieve : true,
		        				lengthMenu : [
		        						[ 5, 10, 15, 30, -1 ],
		        						[ 5, 10, 15, 30,
		        								"All" ] ],
		        				pageLength : 10,// 每页显示数量
		        				processing : true,// loading等待框

		        				ajax : ctx
		        						+ "/rest/processAction/endTask/"+'salePrice',// 加载已办列表数据

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
		        								return "<a href='javascript:void(0);' onclick=\"revoke('"+row.taskId+"','"+row.processInstanceId+"','endTaskSalePriceTable')\">撤销</a>";
		        							}
		        						}
		        						]

		        			})
		         return endTaskTable;
		        }
		 } ]);
