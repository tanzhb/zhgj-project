/* Setup blank page controller */
angular
		.module('MetronicApp')
		.controller(
				'AddVacationController',
				[
						'$rootScope',
						'$scope',
						'$compile',
						'$stateParams',
						'settings',						
						'AddVacationService',
						function($rootScope, $scope, $compile, $stateParams, settings,
								AddVacationService) {
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
							
							
							// 初始化日期控件
							initDatePicker('auto bottom');
							var table;//待办table
							var endTaskTable;//已办table
							
							if($stateParams.tabHref == '1'){//首页待办列表传过来的参数
								$('#vacationTab a[href="#daiban"]').tab('show');
								showDbTable();
							}else if($stateParams.tabHref == '2'){//首页已办列表传过来的参数
								$('#vacationTab a[href="#yiban"]').tab('show');
								showYbTable();
							}else{//从菜单进入
								$('#vacationTab a[href="#apply"]').tab('show');
							}
							
							//启动流程
							$scope.applyVacation = function() {
								AddVacationService
										.applyVacation($scope.vacation)
										.then(
												function(data) {
													toastr.success("申请成功！");
												},
												function(errResponse) {
													toastr.warning("申请失败！");
													console
															.error('Error while creating vacation');
												}

										);
							};
							
							function doVacation(_url, mydata, modal){
						        $.ajax( {
							        url : _url,
							        dataType:"text",
							        type: 'POST',
							        data : mydata,
							        success : function(data) {
							        	if(modal == 'audit') $('#auditVacationModal').modal('hide');
							        	else $('#modifyVacationModal').modal('hide');
							        	table.ajax.reload();
							        	showToastr('toast-bottom-right', 'success', data);
							        },
							        error : function(data) {
							        	toastr.error('连接服务器出错,请登录重试！');
							        }
							     });
							}
							
							//审批通过
							$scope.vacationPass = function() {
							    $("#completeFlag").val("true");
							    var mydata={"vacationId":$("#vacationId").val(),"content":$("#content").val(),
										"completeFlag":$("#completeFlag").val()};
							    var _url = ctx + "/rest/vacationAction/complate/" + $("#taskId").val();
							    doVacation(_url, mydata, 'audit');
							};
							//审批不通过
							$scope.vacationUnPass = function() {
								$("#completeFlag").val("false");
								var mydata={"vacationId":$("#vacationId").val(),"content":$("#content").val(),
										"completeFlag":$("#completeFlag").val()};
								var _url = ctx + "/rest/vacationAction/complate/" + $("#taskId").val();
								doVacation(_url, mydata, 'audit');
							};
							
							//重新申请
							$scope.replyVacation = function() {
								$("#reApply").val("true");
							    var mydata={"processInstanceId":$("#processInstanceId").val(),
										"reApply":$("#reApply").val(),"vacationId":$("#vacationId").val(),"beginDate":$("#modify_beginDate").val(),"endDate":$("#modify_endDate").val(),
										"days":$("#modify_days").val(),"vacationType":$("#modify_vacationType").val(),"reason":$("#modify_reason").val()};
								var _url = ctx + "/rest/vacationAction/modifyVacation/" + $("#taskId").val();
								doVacation(_url, mydata, 'modify');
							};
							//取消申请
							$scope.cancelApply = function() {
								 $("#reApply").val("false");					 
							     var mydata={"processInstanceId":$("#processInstanceId").val(),
										"reApply":$("#reApply").val(),"vacationId":$("#vacationId").val(),"beginDate":$("#modify_beginDate").val(),"endDate":$("#modify_endDate").val(),
										"days":$("#modify_days").val(),"vacationType":$("#modify_vacationType").val(),"reason":$("#modify_reason").val()};
								var _url = ctx + "/rest/vacationAction/modifyVacation/" + $("#taskId").val();
								doVacation(_url, mydata, 'modify' );
							};

							// 请假申请
							$scope.toApply = function() {
								$('#vacationTab a[href="#apply"]').tab('show');
							};
							// 待办流程
							$scope.toDaiban = function() {
								$('#vacationTab a[href="#daiban"]').tab('show');

								// 构建datatables开始***************************************
								table = showDbTable();								
								// 构建datatables结束***************************************

							};
							// 已办流程
							$scope.toYiban = function() {
								$('#vacationTab a[href="#yiban"]').tab('show');
								endTaskTable = showYbTable();
							};
							
							// 关闭审批窗口
							$scope.closeAuditDialogue = function() {
								$('#auditVacationModal').modal("hide");
							};
							
							// 关闭更改申请窗口 
							$scope.closeModifyDialogue = function() {
								$('#modifyVacationModal').modal("hide");
							};
							
							//初始化审批表单
							function approvalFormInit( taskDefinitionKey, businessType, taskId ) {
								
							}

//							$('a[data-toggle="tab"]').on('shown.bs.tab',
//									function(e) {
//										// 获取已激活的标签页的名称
//										var activeTab = $(e.target).text();
//									});

						} ]);


function showDbTable(){
	
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

				buttons : [
						{
							text : "办理",
							className : "btn default",
							action: function(e, dt, node, config) { 
								if(table.rows('.selected').data().length == 0){
									toastr.warning("请选择要办理的任务！");
								}else{
									var assign = table.row('.selected').data().assign;
									var taskId = table.row('.selected').data().taskId;
									var processInstanceId = table.row('.selected').data().processInstanceId;
									handleTask(assign, taskId, processInstanceId);
								}
							}
						},
						{
							text : "签收",
							className : "btn default",
							action: function(e, dt, node, config) { 
								if(table.rows('.selected').data().length == 0){
									toastr.warning("请选择要签收的任务！");
								}else{
									var taskId = table.row('.selected').data().taskId;
									claimTask(taskId, 'sample_2');
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
						+ "/rest/processAction/todoTask/" + 'vacation',// 加载待办列表数据

				"aoColumns" : [

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
							mData : 'businessType',
							mRender : function(
									data) {
								if (data == "vacation") {
									return "请假申请";
								} else if (data == "salary") {
									return "薪资调整";
								} else if (data == "expense") {
									return "报销申请";
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
						} ]

			})
			
			$('#sample_2 tbody')
										.on(
												'click',
												'tr',
												function() {
													if ($(this).hasClass(
															'selected')) {
														$(this).removeClass(
																'selected');
													} else {
														table
																.$(
																		'tr.selected')
																.removeClass(
																		'selected');
														$(this).addClass(
																'selected');
													}
												});
			
			return table;
}

function showYbTable(){
	var endTaskTable = $("#endTaskTable").DataTable(
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
						+ "/rest/processAction/endTask/" + 'vacation',// 加载已办列表数据

				"aoColumns" : [
						{
							mData : 'businessType',
							mRender : function(
									data) {
								if (data == "vacation") {
									return "请假申请";
								} else if (data == "salary") {
									return "薪资调整";
								} else if (data == "expense") {
									return "报销申请";
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
								return "<a href='javascript:void(0);' onclick=\"revoke('"+row.taskId+"','"+row.processInstanceId+"','endTaskTable')\">撤销</a>";
							}
						}
						]

			})
 return endTaskTable;
}

function handleTask(assign, taskId, processInstanceId){
	
	if(assign == ''){
		toastr.warning("此任务您还没有签收，请【签收】任务后再处理任务！！");
	}else{		
		$.ajax({url:ctx + "/rest/vacationAction/toApproval/" + taskId,
			type: 'POST',
			dataType: 'json',
			success:function(result){
				$("#taskId").val(taskId);
				$("#processInstanceId").val(processInstanceId);																					
				$("#vacationId").val(result.vacation.id);
				
				var comments = ""//添加评论
				for (var i=0;i<result.commentList.length;i++){
					comments += "<tr><td>" + result.commentList[i].userName + "</td><td>" 
					+ (result.commentList[i].position==null?'':result.commentList[i].position) + "</td><td>"
					+ timeStamp2String2(result.commentList[i].time) + "</td><td>" + result.commentList[i].content + "</td></tr>";														
				}
				
				if(result.actionType == 'audit'){//审批流程
					if(comments == ""){
						comments = "无评论";
					}else $("#comment_audit").html(comments);
					$("#audit_beginDate").val(timeStamp2String2(result.vacation.beginDate));
					$("#audit_endDate").val(timeStamp2String2(result.vacation.endDate));
					$("#audit_days").val(result.vacation.days);
					$("#audit_vacationType").val(result.vacation.vacationType);
					$("#audit_reason").val(result.vacation.reason);
					$('#auditVacationModal').modal('show');
				}else{//result.actionType == 'modify' 更改流程
					if(comments == ""){
						comments = "无评论";
					}else $("#comment_modify").html(comments);
					$("#modify_beginDate").val(timeStamp2String2(result.vacation.beginDate));
					$("#modify_endDate").val(timeStamp2String2(result.vacation.endDate));
					$("#modify_days").val(result.vacation.days);
					$("#modify_vacationType").val(result.vacation.vacationType);
					$("#modify_reason").val(result.vacation.reason);
					$('#modifyVacationModal').modal('show');
				}
				
		}});
		
	}
	
	
	
}