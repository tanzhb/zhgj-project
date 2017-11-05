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
						'orderService',
						function($rootScope, $scope, $state, $compile,$http,$location,$stateParams,settings,
								WarehouseService,orderService) {
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
													 $scope.warehousepositions=[{}];
													 _index = 0; 
													 $scope.warehouseSerialNum=$stateParams.warehouseSerialNum;
													 if($stateParams.warehouseSerialNum!=undefined){
										    		getWarehouseInfo($stateParams.warehouseSerialNum);
													 }
										    		 if($scope.warehouseSerialNum==undefined){
										    		 $rootScope.setNumCode("WH",function(newCode){//
										    	 			$scope.warehouse={};
										    	 			$scope.warehouse.warehouseNum= newCode;//仓库编号
										    	 			 
										    	 		});
										    		 }
										    		 initAllComs();
										    		/* getComId();*/
										    		
										 		}
												 if($location.path()=="/warehouse"){
											        	loadWarehouseTable();//加载仓库列表
											        	//loadWarehouseTree();//加载仓库树
											        }
												// set default layout mode
												 handle.pageRepeater();
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
												ajax : tableAjaxUrl,// 加载数据中仓库表数据

												"aoColumns" : [
													{
													mData : 'serialNum'/*,
														 mRender : function(
																	data,
																	type,
																	row,
																	meta) {
																return "<label class='mt-checkbox mt-checkbox-single mt-checkbox-outline'>" +
																		"<input type='checkbox' class='checkboxes' value='"+data+"'/>" +
																		"<span></span></label>";
															}*/
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
														mData : 'ownerName'
													} ],
												'aoColumnDefs' : [ {
													'targets' : 0,
													'searchable' : false,
													'orderable' : false,
													'className' : 'dt-body-center',
													'render' : function(data,
															type, full, meta) {
														/*return '<input type="checkbox" id="'+data+'" name="id[]" value="'
																+ $('<div/>')
																		.text(
																				data)
																		.html()
																		+ '" data-check="false"  ng-click="showPosition(\''+full.serialNum+'\')" >';*/
														return '<label class="mt-checkbox mt-checkbox-single mt-checkbox-outline">'+
					                                     '<input type="checkbox" data-check="false" class="checkboxes" ng-click="showPosition(\''+full.serialNum+'\')" id="'+data+'" value="'+data+'" data-set="#sample_warehouse .checkboxes" />'+
					                                     '<span></span></label>';
													},"createdCell": function (td, cellData, rowData, full, col) {
														 $compile(td)($scope);
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
											$("#sample_warehouse").find(".group-checkable").change(function() {
										        var e = jQuery(this).attr("data-set"),
										        t = jQuery(this).is(":checked");
										        jQuery(e).each(function() {
										            t ? ($(this).prop("checked", !0), $(this).parents("tr").addClass("active")) : ($(this).prop("checked", !1), $(this).parents("tr").removeClass("active"))
										        })
										    }),
										    $("#sample_warehouse").on("change", "tbody tr .checkboxes",
										    function() {
										        $(this).parents("tr").toggleClass("active")
										    })
										   return table;
							/*// 添加checkbox功能***************************************
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
*/							}
			
			  /**
			 * 加载所有公司数据
			 */
			var initAllComs = function(){
				var promise = orderService.initAllComs();
		        	promise.then(function(data){
		        		debugger;
		        		$scope.coms = data.data;
		        		setTimeout(function () {
		        			$("#owner").selectpicker({
		                        showSubtext: true
		                    });
		        			$('#owner').selectpicker('refresh');//刷新插件
		        			
		                }, 100);
		        		 getComId();
		        	},function(data){
		        		//调用承诺接口reject();
		        	});
			}
			
			var getComId=function (){
				var promise = orderService.getComId();
				promise.then(function(data) {
					debugger;
					 if( $scope.warehouse==undefined){
						 $scope.warehouse={};
					 }
						 if(data.comName!=null){
								$scope.warehouse.owner=data.comId;
								$scope.warehouseowner1=data.comId;
								$scope.warehouse.ownerName=data.comName;
							}else{
								$scope.warehouse.owner="";
								$scope.warehouseowner1="";
							}
					 
					
				}, function(data) {
				});
				
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
													$scope.warehouse = data;
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
									toastr.warning("请选择一条数据进行编辑");
								}else if(id_count>1){
									toastr.warning("只能选择一条数据进行编辑");
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
							$scope.showPosition=function(serialNum){
								debugger;
								getWarehouseInfo(serialNum,'noWarehouseInfo');
								
							}
							$scope.savewarehouseposition= function(index,judgeNumber) {
								debugger;
								if($('#warehousepositionForm').valid()){//表单验证通过则执行添加功能
									if(handle.isNull($scope.warehouse)||handle.isNull($scope.warehouse.serialNum)){
				 			    		toastr.warning("您的仓库信息还未保存！");
				 			    		 return;
				 			    	}else{
				 			    		 debugger;
				 			    		 var warehouseposition={};
				 			    		warehouseposition.warehouseSerial=$scope.warehouse.serialNum;
				 			    		warehouseposition.serialNum=$("#serialNum"+index).val();
				 			    		warehouseposition.positionNum =$("#positionNum"+index).val();
				 			    		warehouseposition.positionName =$("#positionName"+index).val();
				 			    		warehouseposition.storageAttribute =$("#storageAttribute"+index).val();
				 			    		warehouseposition.maxRows =$("#maxRows"+index).val();
				 			    		warehouseposition.maxCols =$("#maxCols"+index).val();
				 			    		warehouseposition.maxLayers=$("#maxLayers"+index).val();
				 			    		warehouseposition.storageType =$("#storageType"+index).val();
				 			    		warehouseposition.storageMode =$("#storageMode"+index).val();
				 			    		warehouseposition.defaultLWH =$("#defaultLWH"+index).val();
				 			    		warehouseposition.defaultVolume =$("#defaultVolume"+index).val();
				 			    		warehouseposition.defaultBearing =$("#defaultBearing"+index).val();
				 			    			
				 			    	}
				 			    	if($('#warehousepositionForm').valid()){//
				 			    		handle.blockUI();
				 			        	var promise = WarehouseService.saveWarehousePosition(warehouseposition);
				 			        	promise.then(function(data){
				 			        		if(!handle.isNull(data)){
				 				        		toastr.success("保存成功");
				 				        		debugger;
				 				        		handle.unblockUI();
				 				        		$scope.warehousepositions = data.data;
				 				        		$scope.warehousepositionView=true;
				 				        		_index=data.data.length-1;
				 				        		for(var i=0;i<$scope.warehousepositions.length;i++){
					 			        			$scope["warehousepositionAdd"+i] = true;
					 								$scope["warehousepositionView"+i] = true;
					 								$scope["warehousepositionEdit"+i] = true;
					 			        	}
				 				        		//getPriceListInfo($scope.priceList.serialNum);
				 				        		
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
						};	
						
						  /**
				         * 删除仓库区位
				         */
				        $scope.deleteWarehouseposition=function (warehouseposition) {
				        	debugger;
				        	handle.confirm("确定删除吗？",function(){
				        		handle.blockUI();
				        			var promise = WarehouseService.deleteWarehousePosition(warehouseposition.serialNum);
					        		promise.then(function(data){
					        			if(data.data == "1"){
					        				toastr.success("删除成功");
						        			handle.unblockUI(); 
						        			getWarehouseInfo($scope.warehouse.serialNum);
						        			//_index=$scope.warehousepositions.length-1;
					        			}else{
					        				toastr.error("删除失败！请联系管理员");
							            	console.log(data);
					        			}
					        			
					 	            },function(data){
					 	               //调用承诺接口reject();
					 	            	toastr.error("删除失败！请联系管理员");
						            	console.log(data);
					 	            });
				        		
				        	});
						   
				        };
				        $scope.editwarehouseposition=function (index) {
				        	$scope["warehousepositionAdd"+index] = false;
				        	$scope["warehousepositionView"+index] = false;
				        	$scope["warehousepositionEdit"+index] = false
				        }
				        /**
						 * 撤销库位编辑
						 */
				        $scope.cancelWarehouseposition=function (warehouseposition,index) {
				        	debugger;
				        	if(isNull($("#serialNum"+index).val())){
				        		$scope.warehousepositions.splice(_index,1);
						    	   _index--;
				        	}else{
				        		getWarehouseInfo($scope.warehouse.serialNum,'noWarehouseInfo');
				        		$scope["warehousepositionAdd"+index] = true;
					        	$scope["warehousepositionView"+index] = true;
					        	$scope["warehousepositionEdit"+index] = true;
					        	
				        	}
				        	/*for(var i=0;i<$scope.copyMateriels.length;i++){
				        		if(materiel.serialNum == $scope.copyMateriels[i].serialNum && !isNull(materiel.supplyMaterielSerial)){ //如果是以保存的物料，回滚
				        			$scope.rootMateriels[$scope.rootMateriels.indexOf(materiel)] = $scope.copyMateriels[i];
				        			$scope["demandPlanMaterielEdit"+index] = true;
									$scope["demandPlanMaterielView"+index] = true;
									break;
				        		}
				        		
				        		if(i==$scope.copyMateriels.length-1){ //如果是已选择但未保存的物料，清空
				        			$scope.rootMateriels[$scope.rootMateriels.indexOf(materiel)].deliveryDate = "";
				        			$scope.rootMateriels[$scope.rootMateriels.indexOf(materiel)].deliveryAddress = "";
				        			$scope.rootMateriels[$scope.rootMateriels.indexOf(materiel)].amount = "";
				        		}
				        	}*/
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
						            	address:{required:true}
						            	//area:{required:!0,digits:true,},
						            	 //email: {	required:true,email:true},
						            	// tel: {required:true,digits:true, rangelength:[7,20] },
				                        // fax: {required:true,digits:true, rangelength:[7,20] },
						            	//remark:{required:true},
						            	//admin:{required:true}
						               
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
							
							// 页面加载完成后调用，验证输入框
							$scope.$watch('$viewContentLoaded', function() { 
								 var form1 = $('#warehousepositionForm');
						            var error1 = $('.alert-danger', form1);
						            var success1 = $('.alert-success', form1);
						            form1.validate({
						                errorElement: 'span', //default input error message container
						                errorClass: 'help-block help-block-error', // default input error message class
						                focusInvalid: false, // do not focus the last invalid input
						                ignore: "",  // validate all fields including form hidden input
						                messages:  {
						                	positionNum:{required:"仓库区位编码不能为空！"},
						                	positionName:{required:"仓库区位名称不能为空！"},
						                	storageAttribute:{required:"存储属性不能为空！"},
						                	maxRows:{required:"最大行数不能为空！"},
						                	maxCols:{required:"最大列数不能为空！"},
						                	maxLayers:{required:"最大层数不能为空！"},
						                	/*storageType:{required:"存储类型不能为空！"},
						                	storageMode:{required:"存储方式不能为空！"},*/
						                	defaultLWH:{required:"默认长宽高不能为空！"},
						                	defaultVolume:{required:"默认容积不能为空！"},
						                	defaultBearing:{required:"默认承重不能为空！"}
							            },
						                rules: {
						                	positionNum: {
												required: !0
											},
											positionName: {
												required: !0
											},
											storageAttribute: {
												required: !0
											},
											maxRows: {
												required: !0
											},
											maxCols: {
												required: !0
											},
											maxLayers: {
												required: !0
											},
											/*storageType: {
												required: !0
											},
											storageMode: {
												required: !0
											},*/
											storageAttribute: {
												required: !0
											},
											defaultLWH: {
												required: !0
											},
											defaultVolume: {
												required: !0
											},
											defaultBearing: {
												required: !0
											}
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
							})
							 function getWarehouseInfo(serialNum,judgeString){
						    	   if(!handle.isNull(serialNum)){
						    		   debugger;
						    			 var promise =WarehouseService .selectBySerialNum(serialNum);
						    			 
						 	        	promise.then(function(data){
						 	        		  debugger;
						 	        		  if(judgeString!='noWarehouseInfo'){
						 	        			 $scope.warehouse = data.warehouse;
						 	        		  }
						 	        		$scope.warehousepositions = data.warehousepositions;
						 	        		_index=data.warehousepositions.length-1;
						 	        		for(var i = 0;i < data.warehousepositions.length;i++){
					        					$scope["warehousepositionAdd"+i] = true;
					        					$scope["warehousepositionView"+i] = true;
					        					$scope["warehousepositionEdit"+i] = true;
					        				}
						 	        		
						 	        		$scope.warehousepositionEdit=true;
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
							        * 仓库库位加一行
							        */
							       $scope.addRepeat = function(){
							    	   if(handle.isNull($scope.warehouse)||handle.isNull($scope.warehouse.serialNum)){
								    		 toastr.warning("您的仓库信息还未保存");
								    		 return;
								       }else{
								    	   _index++;
								    	   $scope.warehousepositions[_index] = {};
								    	   $scope["warehousepositionAdd"+_index] = false;
								    	   $scope["warehousepositionEdit"+_index] = false;
								    	   
								    	   
								       }
							       };
							       
							       /**
							        * 仓库库位删除一行
							        */
							       $scope.deleteRepeat = function(){
							    	   $scope.warehousepositions.splice(_index,1);
							    	   _index--;
							       };
						        /**
							        * 显示仓库信息(弹框)
							        */
							       $scope.showWarehouseInfoModal = function(serialNum){
							    	   getWarehouseInfo(serialNum);
							    	   $('#viewWarehouse').modal('show'); 
							       };
							       /**
							        * 下载EXCEL模板
							        */
							       $scope.downloadImportTemp = function(){
							    	   window.location.href=$rootScope.basePath+"/rest/warehouse/downloadImportTemp";
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
							       $scope.exportWarehouse = function(){
								    	 handle.blockUI("正在导出数据，请稍后"); 
								    	 window.location.href=$rootScope.basePath+"/rest/warehouse/exportWarehouse";
								    	 handle.unblockUI(); 
								       }
								       
							       $('#import').on('hide.bs.modal', function (e) { 
							    	   $("#resetFile").trigger("click");
							    	  //$("#file_span input[type='file']").remove();
							    	  //$(".fileinput-filename").val("");
							    	  //$("#file_span").appendTo('<input type="file" file-model="excelFile" accept=".xls" name="...">');
							       })
						} ]);
