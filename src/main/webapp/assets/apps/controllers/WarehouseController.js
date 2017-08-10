/* Setup blank page controller */
angular
		.module('MetronicApp')
		.controller(
				'WarehouseController',
				[
						'$rootScope',
						'$scope',
						'$state',
						'$compile',
						'$http',
						'$location',
						'$stateParams',
						'settings',
						'WarehouseService',
						function($rootScope, $scope, $state, $compile,$http,$location,$stateParams,settings,
								WarehouseService) {
							$scope
									.$on(
											'$viewContentLoaded',
											function() {
												// initialize core components
												 handle = new pageHandle();
												App.initAjax();
												if($location.path()=="/addWarehouse"){
													/*debugger;
													console.log($rootScope.warehouseSerialNum);*/
										    		getWarehouseInfo($stateParams.warehouseSerialNum);
										 		}
												 if($location.path()=="/warehouse"){
											        	loadWarehouseTable();//加载仓库列表
											        	//loadWarehouseTree();//加载仓库树
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
							var tableAjaxUrl = "rest/warehouse/getWarehouseList";
							 var table;
			function loadWarehouseTable(){
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

						 table = $("#sample_warehouse")
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
												ajax : tableAjaxUrl,// 加载数据中仓库表数据

												"aoColumns" : [
													{
													mData : 'serialNum'
													},
													 {
														mData : 'warehouseNum'
													},  {
														mData : 'warehouseName'
													},{
														mData : 'warehouseType'
													}, {
														mData : 'address'
													},{
														mData : 'admin'
													}, {
														mData : 'area'
													}, {
														mData : 'warehouseCategory'
													}, {
														mData : 'owner'
													} ],
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
														return '<a   ng-click="showWarehouseInfoModal(\''+row.serialNum+'\')">'+data+'</a>';
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
							$('#sample_warehouse tbody')
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
							// 添加仓库开始***************************************
						$scope.addWarehouse = function() {
							$scope.warehouseAdd=true;
							 $state.go('addWarehouse',{},{reload:true}); 
						}
						$scope.editWarehouse = function(){
							debugger;
							var warehouse=$scope.warehouse;
							$scope.warehouse=warehouse;
							$scope.warehouseView = false;
		        			$scope.warehouseAdd = false;
		        			$scope.warehouseEdit = true;
						}
						$scope.cancelEditWarehouse = function(){
							getWarehouseInfo($scope.warehouse.serialNum);
							$scope.warehouseView = true;
		        			$scope.warehouseAdd = true;
		        			$scope.warehouseEdit = false;
						}		
								$scope.saveWarehouse= function() {
									debugger;
									if($('#warehouseForm').valid()){//表单验证通过则执行添加功能
										WarehouseService
										.saveWarehouse($scope.warehouse)
										.then(
												function(data) {debugger;
													$('#addWarehouseModal').modal(
															'hide');// 保存成功后关闭模态框
													toastr.success("保存仓库数据成功！");
													// $state.go('warehouse',{},{reload:true});  // 重新加载datatables数据
													$scope.warehouse = $scope.warehouse;
								        			$scope.warehouseView = true;
								        			$scope.warehouseAdd = true;
								        			$scope.warehouseEdit = false;
								        			$(".alert-danger").hide();
												},
												function(errResponse) {
													toastr.warning("仓库名重复，请重新输入！");
													console
															.error('Error while creating User');
												}
										);
									}
							};	
							// 添加仓库结束***************************************
							
							// 修改仓库开始***************************************							
							$scope.toEditWarehousePage = function() {//弹出框修改仓库信息
								debugger;
								var id_count = table.$('input[type="checkbox"]:checked').length;
								if(id_count==0){
									handle.toastr.warning("请选择一条数据进行编辑");
								}else if(id_count>1){
									handle.toastr.warning("只能选择一条数据进行编辑");
								}else{
									var serialNum = table.$('input[type="checkbox"]:checked').val();
									$state.go("addWarehouse",{warehouseSerialNum:serialNum});
								}
							};
							// 修改仓库结束***************************************							

							// 删除仓库开始***************************************							
							$scope.delWarehouse = function() {
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
									$('#delWarehouseModal').modal('show');// 打开确认删除模态框
									
									$scope.confirmDellWarehouse = function() {										
										WarehouseService
												.delWarehouses(ids)
												.then(
														function(data) {
															$('#delWarehouseModal').modal(
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
							// 页面加载完成后调用，验证输入框
							$scope.$watch('$viewContentLoaded', function() {  
								var e = $("#warehouseForm"),
						        r = $(".alert-danger", e),
						        i = $(".alert-success", e);
						        e.validate({
						            errorElement: "span",
						            errorClass: "help-block help-block-error",
						            focusInvalid: !1,
						            ignore: "",
						            messages: {
						            	warehouseNum:{required:"仓库编号不能为空！"},
						            	warehouseName:{required:"仓库名称不能为空！"},
						            	warehouseType:{required:"未选择仓库类型！"},
						            	warehouseCategory:{required:"未选择仓库分类！"},
						            	owner:{required:"仓库持有者不能为空！"},
						            	address:{required:"仓库地址不能为空！"},
						            	area:{required:"仓库面积不能为空！",digits:"请输入正确的数字!"},
						            	email: { required:"邮箱不能为空！",email:"E-Mail格式不正确"},
						            	//email:{required:"邮箱不能为空！"},
						            	 tel: { 
						                    	digits:'请输入正确的电话, 必须为数字！',
						                    	required:"电话不能为空！",
				                        	    rangelength:jQuery.validator.format("电话必须在{0}到{1}位数字之间！")
						                    },
						                   fax: { 
						                    	digits:'请输入正确的传真, 必须为数字！',
						                    	required:"传真不能为空！",
				                        	    rangelength:jQuery.validator.format("传真必须在{0}到{1}位数字之间！")
						                    },
						            	//tel:{required:"电话不能为空！"},
						            	//remark:{required:"备注不能为空！"},
						            	admin:{required:"仓库管理员不能为空！"},
						            	//fax:{required:"传真不能为空！"}
						            },
						            rules: {
						            	warehouseNum:{required:true},
						            	warehouseName:{required:true},
						            	warehouseType:{required:true},
						            	warehouseCategory:{required:true},
						            	owner:{required:true},
						            	address:{required:true},
						            	area:{required:!0,digits:true,},
						            	 email: {	required:true,email:true},
						            	 tel: {required:true,digits:true, rangelength:[7,20] },
				                         fax: {required:true,digits:true, rangelength:[7,20] },
						            	//remark:{required:true},
						            	admin:{required:true}
						               
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
							
							
							 function getWarehouseInfo(serialNum){
						    	   if(!handle.isNull(serialNum)){
						    		   debugger;
						    			 var promise =WarehouseService .selectBySerialNum(serialNum);
						    			 
						 	        	promise.then(function(data){
						 	        		  debugger;
						 	        		$scope.warehouse = data;
						 	            },function(data){
						 	               //调用承诺接口reject();
						 	            });
						    		 }
						       }
							
							 function  loadWarehouseTree () {
						            $("#warehouseTree").jstree({
						                core: {
						                    themes: {
						                        responsive: !1
						                    },
						                    data: {
						                        url: function(e) {
						                            return "rest/warehouse/getWarehouseTree"
						                           
						                        },
						                        data: function(e) {
						                            return {
						                                parent: e.pid
						                            }
						                        }
						                    }
						                },
						                types: {
						                    "default": {
						                        icon: "fa fa-folder icon-state-warning icon-lg"
						                    },
						                    file: {
						                        icon: "fa fa-file icon-state-warning icon-lg"
						                    }
						                },
						                plugins: ["types"]
						            }),
						            $("#warehouseTree").on("select_node.jstree", function(e, t) {
						            	table.ajax.url(tableAjaxUrl+"?parent="+t.selected[0]).load()// 重新加载datatables数据
						            })
						            
						        };
						        $scope.reloadWarehouseTable = function() {
						        	table.ajax.url(tableAjaxUrl).load()// 重新加载datatables数据
						        }
						        /**
							        * 显示仓库信息(弹框)
							        */
							       $scope.showWarehouseInfoModal = function(serialNum){
							    	   getWarehouseInfo(serialNum);
							    	   $('#viewWarehouse').modal('show'); 
							       };
							       
						} ]);
