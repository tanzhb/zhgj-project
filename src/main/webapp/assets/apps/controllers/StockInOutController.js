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
												if($location.path()=="/addOrEditStockInOutCheck"){
													$scope.inOrOut=$stateParams.inOrOut;
										 		}else if($location.path()=="/stockInOutCheckView"){
										 			debugger;
										 			getStockDetailInfo($stateParams.stockSerialNum);//查看出入库检验详情页面
									 		}else{
										 			loadStockInOutCheckTable('in');//加载入库检验列表
										 			loadStockInOutCheckTable('out');//加载出库检验列表
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
												ajax : tableAjaxUrl,// 加载数据中库存表数据

												"aoColumns" : [
													{
													mData : 'serialNum'
													},
													 {
														mData : 'checkNum'
													},  {
														mData : 'takeDeliverNum'
													},{
														mData : 'orderNum'
													}, {
														mData : 'materialNum'
													},{
														mData : 'qualifiedCount'
													}, {
														mData : 'unQualifiedCount'
													}, {
														mData : 'checkDate'
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
														return '<a   ng-click="showStockInfo(\''+row.serialNum+'\')">'+data+'</a>';
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
				 	    									statusIcon = '<span class="label label-sm label-success"  >缺料</span> '
				 	    								}else if(row.status==1){
				 	    									statusIcon = '<span class="label label-sm label-success">报警</span> '
				 	    								}else if(row.status==2){
				 	    									statusIcon = '<span class="label label-sm label-success">正常</span> '
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
														ajax : tableAjaxUrl,// 加载数据中库存表数据

														"aoColumns" : [
																{
													mData : 'serialNum'
													},
													 {
														mData : 'checkNum'
													},  {
														mData : 'deliverNum'
													},{
														mData : 'orderNum'
													}, {
														mData : 'materialNum'
													},{
														mData : 'qualifiedCount'
													}, {
														mData : 'unQualifiedCount'
													}, {
														mData : 'checkDate'
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
																return '<a   ng-click="showStockInfo(\''+row.serialNum+'\')">'+data+'</a>';
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
						 	    									statusIcon = '<span class="label label-sm label-success"  >缺料</span> '
						 	    								}else if(row.status==1){
						 	    									statusIcon = '<span class="label label-sm label-success">报警</span> '
						 	    								}else if(row.status==2){
						 	    									statusIcon = '<span class="label label-sm label-success">正常</span> '
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
							$('#sample_'+judgeString+' tbody')
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
							// 添加库存开始***************************************
			$scope.addStockInOutCheck = function(judgeString) {
				debugger;
				 $state.go('addOrEditStockInOutCheck',{inOrOut:judgeString}); 
			}
						$scope.editStockInOutCheck = function(){
							debugger;
							var stock=$scope.stock;
							$scope.stock=stock;
							$scope.stockView = false;
		        			$scope.stockAdd = false;
		        			$scope.stockEdit = true;
						}
						$scope.cancelEditStockInOutCheck = function(){
							debugger;
							getStockInfo($scope.stock.serialNum);
							$scope.stockView = true;
		        			$scope.stockAdd = true;
		        			$scope.stockEdit = false;
						}	
								$scope.saveStockInOutCheck= function() {
									debugger;
									$scope.stock.materielSerial=$("#materielSerial").val();
									if($('#stockForm').valid()&&judgeData()){//表单验证通过则执行添加功能
										StockService
										.saveStock($scope.stock)
										.then(
												function(data) {debugger;
													$('#addStockModal').modal(
															'hide');// 保存成功后关闭模态框
													toastr.success("保存库存数据成功！");
													// $state.go('warehouse',{},{reload:true});  // 重新加载datatables数据
													$scope.stock = data;
								        			$scope.stockView = true;
								        			$scope.stockAdd = true;
								        			$scope.stockEdit = false;
								        			$(".alert-danger").hide();
												},
												function(errResponse) {
													toastr.warning("保存失败！");
													console
															.error('Error while creating User');
												}
										);
									}
							};	
							// 添加库存结束***************************************
							
							// 修改库存开始***************************************							
							$scope.toEditStockPage = function() {//弹出框修改库存信息
								debugger;
								var id_count = table.$('input[type="checkbox"]:checked').length;
								if(id_count==0){
									toastr.warning("请选择一条数据进行编辑");
								}else if(id_count>1){
									toastr.warning("只能选择一条数据进行编辑");
								}else{
									var serialNum = table.$('input[type="checkbox"]:checked').val();
									$state.go("addOrEditStock",{stockSerialNum:serialNum});
								}
							};
							// 修改库存结束***************************************							

							// 删除库存开始***************************************							
							$scope.delStock = function() {
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
									$('#delStockModal').modal('show');// 打开确认删除模态框
									
									$scope.confirmDellStock = function() {										
										StockService
												.delStocks(ids)
												.then(
														function(data) {
															$('#delStockModal').modal(
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
							$scope.showStockInfo=function(serialNum){
								debugger;
								 $state.go('stockView',{stockSerialNum:serialNum},{reload:true}); 
								
							}
							  var selectIndex;
					 	       $scope.selectMateriel = function(index){
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
		 	    			$("#materielSerial").val($scope.row.serialNum) ;//物料流水
		 	    			$("#materielName").val($scope.row.materielName);
		 	    			$("#materielNum").val($scope.row.materielNum);
		 	    			$("#specifications").val($scope.row.specifications);
		 	    		/*	$scope.stock.materielName=;//物料名称
		 	            	$scope.stock.materielNum=;//物料编号
		 	            	$scope.stock.specifications=;//规格型号
*/		 	    			$('#basicMaterielInfo').modal('hide');// 删除成功后关闭模态框
		 	    			$(".modal-backdrop").remove();
		 	    		};
							// 页面加载完成后调用，验证输入框
							$scope.$watch('$viewContentLoaded', function() {  
								var e = $("#stockForm"),
						        r = $(".alert-danger", e),
						        i = $(".alert-success", e);
						        e.validate({
						            errorElement: "span",
						            errorClass: "help-block help-block-error",
						            focusInvalid: !1,
						            ignore: "",
						            messages: {
						            	stockNum:{required:"库存编号不能为空！"},
						            	materielNum:{required:"未选择物料！"},
						            	maxStock:{required:"最高库存不能为空！",digits:"请输入正确的数字!"},
						            	minStock:{required:"最低库存不能为空！",digits:"请输入正确的数字!"},
						            	manageType:{required:"未选择管理类型！"},
						            	materielOwner: { required:"未选择物权方！"},
						            	serviceParty:{required:"未选择服务方！"}
						            },
						            rules: {
						            	stockNum:{required:true},
						            	materielNum:{required:true},
						            	maxStock:{required:true,digits:true},
						            	minStock:{required:true,digits:true},
						            	manageType:{required:true},
						            	materielOwner:{required:true},
						            	serviceParty:{required:!0,digits:true,}
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
							
							
							 function getStockInfo(serialNum){//查看库位
						    	   if(!handle.isNull(serialNum)){
						    		   debugger;
						    			 var promise =StockService .selectBySerialNum(serialNum);
						 	        	promise.then(function(data){
						 	        		  debugger;
						 	        			 $scope.stock = data.stock;
						 	        			 $("#materielSerial").val(data.stock.materielSerial);
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