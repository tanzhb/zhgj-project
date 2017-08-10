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
						function($rootScope, $scope, $state, $compile,$http,$location,$stateParams,settings,
								priceListService) {
							$scope
									.$on(
											'$viewContentLoaded',
											function() {
												// initialize core components
												 handle = new pageHandle();
												App.initAjax();
												if($location.path()=="/addPriceList"){
													debugger;
													$('.date-picker').datepicker({
														rtl: App.isRTL(),
														orientation: "left",
														autoclose: true
										        	})//初始化日期控件
										    		getPriceListInfo($stateParams.priceListSerialNum);
														 $scope.isChecked=false;
														 $scope.ladderprices=[{}];
														 _index = 0; 
														 handle.pageRepeater();
												    		
													 }
												 if($location.path()=="/priceList"){
													 debugger;
											        	loadPriceListTable();//加载价格列表
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
														mData : 'price'
													},{
														mData : 'rate'
													},{
														mData : 'currency'
													}, {
														mData : 'priceEffectiveDate'
													},{
													mData : 'priceExpirationDate'
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
							}
							// 添加价格开始***************************************
						$scope.addPriceList = function() {
							debugger;
							$scope.priceListAdd=true;
							 $state.go('addPriceList',{},{reload:true}); 
						}
						$scope.editPriceList = function(){
							debugger;
							var priceList=$scope.priceList;
							$scope.priceList=priceList;
							$scope.priceListView = false;
		        			$scope.priceListAdd = false;
		        			$scope.PriceListEdit = true;
						}
						$scope.cancelEditPriceList = function(){
							getPriceListInfo($scope.priceList.serialNum);
							$scope.priceListView = true;
		        			$scope.priceListAdd = true;
		        			$scope.priceListEdit = false;
						}		
								$scope.savePriceList= function() {
									if($('#priceListForm').valid()){//表单验证通过则执行添加功能
									priceListService.savePriceList($scope.priceList)
									.then(
											function(data) {debugger;
												toastr.success("保存价格数据成功！");
												$scope.priceList = $scope.priceList;
												$scope.priceListView = true;
							        			$scope.priceListAdd = true;
							        			$scope.priceListEdit = false;
							        			$(".alert-danger").hide();
											},
											function(errResponse) {
												toastr.warning("保存错误！");
												console
														.error('Error while creating User');
											}
									}
									);
									/*debugger;
									if(true){//表单验证通过则执行添加功能//$('#priceListForm').valid()
									var promise = priceListService.savePriceList($scope.priceList);
										debugger;
										promise.then(
												function(data) {debugger;
													toastr.success("保存价格数据成功！");
													$scope.priceList = $scope.priceList;
								        			$scope.priceListView = true;
								        			$scope.priceListAdd = true;
								        			$scope.priceListEdit = false;
								        			$(".alert-danger").hide();
												},
												function(errResponse) {
													toastr.warning("保存价格错误！");
													console
															.error('Error while creating User');
												}
										);
									}*/
							};	
							// 添加价格结束***************************************
							
							// 修改价格开始***************************************							
							$scope.toEditPriceListPage = function() {//弹出框修改价格信息
								debugger;
								var id_count = table.$('input[type="checkbox"]:checked').length;
								if(id_count==0){
									handle.toastr.warning("请选择一条数据进行编辑");
								}else if(id_count>1){
									handle.toastr.warning("只能选择一条数据进行编辑");
								}else{
									var serialNum = table.$('input[type="checkbox"]:checked').val();
									$state.go("addPriceList",{priceListSerialNum:serialNum});
								}
							};
							// 修改价格结束***************************************							
							$scope.showOrHide = function() {//控制阶梯div的显示与隐藏
								 $scope.isChecked=!$scope.isChecked;
								 
							 }
						
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
						            	price: {digits:"必须是数字！",required:"单价不能为空！"},
						            	/*inclusiveprice:{digits:"必须是数字！"},
						            	topprice:{digits:"必须是数字！"},
						            	floorprice:{digits:"必须是数字！"},*/
						            	priceEffectiveDate: {required:"价格生效期未选择！" },
						            	priceExpirationDate: {required:"价格失效期未选择！" }
						            },
						            rules: {
						            	priceNum:{required:true},
						            	materielNum:{required:true},
						            	priceType:{required:true},
						            	buyComId:{required:true},
						            	supplyComId:{required:true},
						            	currency:{required:true},
						            	rate:{digits:true,required:!0},
						            	price: {digits:true,required:!0},
						            /*	inclusiveprice:{digits:true},
						            	topprice:{digits:true},
						            	floorprice:{digits:true},*/
						            	priceEffectiveDate: {required:true },
						            	priceExpirationDate: {required:true }
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
						            	debugger;
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
							
							
							 function getPriceListInfo(serialNum){
						    	   if(!handle.isNull(serialNum)){
						    		   debugger;
						    			 var promise =priceListService.selectBySerialNum(serialNum);
						 	        	promise.then(function(data){
						 	        		  debugger;
						 	        		$scope.priceList = data;
						 	            },function(data){
						 	               //调用承诺接口reject();
						 	            });
						    		 }
						       }
							 
							  /**
						        * 阶梯价格加一行
						        */
						       $scope.addRepeat = function(){
						    	   if(handle.isNull($scope.priceList)||handle.isNull($scope.priceList.serialNum)){
							    		 handle.toastr.warning("您的价格信息还未保存");
							    		 return;
							       }else{
							    	   _index++;
							    	   $scope.ladderprices[_index] = {}
							       }
						       };
						       
						       /**
						        * 阶梯价格删除一行
						        */
						       $scope.deleteRepeat = function(){
						    	   $scope.ladderprices.splice(_index,1);
						    	   _index--;
						       };
						       /**
						        * 显示编辑、删除操作
						        */
						       $scope.showOperation = function(type,index){
						    	   var call = "operation_c"+index;
						    	   if(type=='finance'){
						    		   call =  "operation_f"+index;
						    	   }
						    	   $scope[call] = true;
						       };
						       
						       /**
						        * 隐藏编辑、删除操作
						        */
						       $scope.hideOperation = function(type,index){
						    	   var call = "operation_c"+index;
						    	   if(type=='finance'){
						    		   call =  "operation_f"+index;
						    	   }
						    	   $scope[call]= false;
						       };
						        /**
							        * 显示价格信息(弹框)
							        */
							       $scope.showPriceListInfoModal = function(serialNum){
							    	   getPriceListInfo(serialNum);
							    	   $('#viewPriceList').modal('show'); 
							       };
							       
						} ]);
