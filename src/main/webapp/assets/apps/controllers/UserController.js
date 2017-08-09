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
							

							// 构建datatables开始***************************************
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
												order : [ [ 1, "asc" ] ],// 默认排序列及排序方式

												bRetrieve : true,
												lengthMenu : [
														[ 5, 10, 15, 30, -1 ],
														[ 5, 10, 15, 30, "All" ] ],
												pageLength : 10,// 每页显示数量
												processing : true,// loading等待框
												
												ajax : $rootScope.basePath
														+ "/rest/user/findAllUsers",// 加载数据中user表数据

												"aoColumns" : [

												{
													mData : 'id'
												}, {
													mData : 'loginName'
												}, {
													mData : 'password'
												}, {
													mData : 'userName'
												},{
													mData : 'idNumber'
												}, {
													mData : 'station'
												}, {
													mData : 'telphone'
												}, {
													mData : 'email'
												},{
													mData : 'state'
												}, {
													mData : 'delFlg'
												}, {
													mData : 'creator'
												}, {
													mData : 'createTime',
													mRender: function (data) {
									                    if (data != null) {
									                        return timeStamp2String(data);
									                    }else return '';
									                }
												}, {
													mData : 'updater'
												}, {
													mData : 'updateTime',
													mRender: function (data) {
									                    if (data != null) {
									                        return timeStamp2String(data);
									                    }else return '';
									                }
												}, {
													mData : 'comments'
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

							// 添加用户开始***************************************
							$scope.add = function() {
								
								$scope.user = null;//打开添加用户模态框前将输入框置空
								$('#twoPass').val('');//密码确认处置空
								$('#appendTitle').empty();
								$('#appendTitle').append("<h4 class='modal-title'>添加用户</h4>");
								$('#addUserModal').modal('show');// 弹出添加模态框
																
							};								
							// 添加用户结束***************************************
							
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
							
							// 修改用户开始***************************************							
							$scope.edit = function() {
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
								if (ids == '' || ids.split(',').length > 1) {// 选择一条数据进行修改									
									toastr.warning("选择一条数据进行修改！");
								} else {
									
									UserService
									.selectById(ids)
									.then(
											function(data) {
												$scope.user = data;//将后台数据赋值给前台页面
												$('#twoPass').val($scope.user.password);//密码赋值给验证密码
												$('#appendTitle').empty();
												$('#appendTitle').append("<h4 class='modal-title'>修改用户</h4>");
												$('#addUserModal').modal('show');// 弹出修改模态框
											},
											function(errResponse) {
												console
														.error('Error while editing Users');
											}

									);
								}								
							};
							// 修改用户结束***************************************							

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
					            		twoPass:{required: "请输入确认密码!",rangelength:jQuery.validator.format("密码位数必须在{0}到{1}字符之间！"),
					                         equalTo: "请再次输入相同的值!"},
					                    email: { email:"E-Mail格式不正确"},
					                    telphone: { 
					                    	digits:'请输入正确的电话, 必须为数字！',
			                        	    rangelength:jQuery.validator.format("电话必须在{0}到{1}位数字之间！")
					                    },
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
						                },
						                password:{required:true,
						                	rangelength:[6,12]
						                },
						                telphone: {
						                	digits:true,
						                    rangelength:[7,20]
						                },
						                twoPass: {
						                    required: true,
						                    rangelength:[6,12],
						                    equalTo: "#password"
						                  },
						                idNumber: "isIdCardNo",      
						                email: {
						                	email:true
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
							// 页面加载完成后调用，验证输入框
							
							
							
							
							var self = this;
							self.user = {
								id : null,
								loginName : '',
								password : '',
								userName : '',
								idNumber : '',
								station : '',
								telphone : '',
								email : '',
								state : '',
								delFlg : '',
								creator : '',
								createTime : '',
								updater : '',
								updateTime : '',
								comments : ''
							};
							self.users = [];

							fetchAllUsers();

							function fetchAllUsers() {
								UserService
										.fetchAllUsers()
										.then(
												function(d) {

												},
												function(errResponse) {
													console
															.error('Error while fetching Users');
												});
							}
						} ]);