/* Setup blank page controller */
angular
		.module('MetronicApp')
		.controller(
				'UserController',
				[
						'$rootScope',
						'$scope',
						'$compile',
						'settings',
						'UserService',
						function($rootScope, $scope, $compile, settings,
								UserService) {
							$scope
									.$on(
											'$viewContentLoaded',
											function() {
												// initialize core components
												App.initAjax();

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

							var table = $("#sample_2")
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
												ajax : $rootScope.basePath
														+ "/rest/user/findAllUsers",// 加载数据中user表数据

												"aoColumns" : [

												{
													mData : 'id'
												}, {
													mData : 'username'
												}, {
													mData : 'password'
												}, {
													mData : 'state'
												}, {
													mData : 'createTime'
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
												} ],

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

							// 保存用户开始***************************************
							$scope.saveUser = function() {
								if($('#form_sample_1').valid()){//表单验证通过则执行添加功能
									UserService
									.saveUser($scope.user)
									.then(
											function(data) {
												$('#addUserModal').modal(
														'hide');// 保存成功后关闭模态框
												toastr.success("保存用户数据成功！");
												table.ajax.reload(); // 重新加载datatables数据
											},
											function(errResponse) {
												toastr.warning("用户名重复，请重新输入！");
												console
														.error('Error while creating User');
											}

									);
								}
								
							};
							// 保存用户结束***************************************

							// 删除用户开始***************************************							
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
										UserService
												.delUsers(ids)
												.then(
														function(data) {
															$('#delUsersModal').modal(
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
							// 删除用户结束***************************************
							
							//清除form中输入框内容
							$('#addUserModal').on('hidden.bs.modal',  function () {
//					　　			$("input").val("");
								document.getElementById("form_sample_1").reset();
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
						            	name3:{required:"用户名不能为空！",rangelength:jQuery.validator.format("用户名位数必须在{0}到{1}字符之间！"),
						            		remote:jQuery.validator.format("用户名已经被注册")},
					            		password:{required:"密码不能为空！",rangelength:jQuery.validator.format("密码位数必须在{0}到{1}字符之间！")},
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
						                name3:{required:true,
						                	rangelength:[3,10]
						                	/*rangelength:[6,20],
						                    remote:{//验证用户名是否存在
						                           type:"POST",
						                           url:$rootScope.basePath + "/rest/user/selectByUsername",
						                           data:{
						                             name3:function(){return $("#name3").val();}
						                           } 
						                    }*/
						                },
						                password:{required:true,
						                	rangelength:[6,12]
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
							
							
							
							
							var self = this;
							self.user = {
								id : null,
								username : '',
								password : '',
								state : '',
								createTime : ''
							};
							self.users = [];

							fetchAllUsers();

							function fetchAllUsers() {
								UserService
										.fetchAllUsers()
										.then(
												function(d) {
													// console.log("************"
													// + d.length);
													// self.users = d;

												},
												function(errResponse) {
													console
															.error('Error while fetching Users');
												});
							}
						} ]);
