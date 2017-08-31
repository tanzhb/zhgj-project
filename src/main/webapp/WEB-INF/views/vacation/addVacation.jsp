<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<head>
<meta http-equiv="Content-Type"
	content="multipart/form-data; charset=utf-8" />
</head>
<!-- BEGIN PAGE HEADER-->
<h3 class="page-title">请假</h3>
<div class="page-bar">
	<ul class="page-breadcrumb">
		<li><i class="fa fa-home"></i> <a ui-sref="dashboard">首页</a> <i
			class="fa fa-angle-right"></i></li>
		<li><a ui-sref="vacation">请假</a></li>
	</ul>
</div>

<div class="portlet-body">
	<div class="tabbable-custom ">
		<ul class="nav nav-tabs " id="vacationTab">
			<li class="active"><a href="#apply" data-toggle="tab"
				ng-click="toApply()"> 请假申请 </a></li>
			<li><a href="#daiban" data-toggle="tab" ng-click="toDaiban()">
					待办流程 </a></li>
			<li><a href="#yiban" data-toggle="tab" ng-click="toYiban()">
					已办流程 </a></li>
		</ul>
		<div class="tab-content">
			<div class="tab-pane active" id="apply">
				<form class="form-horizontal" id="applyVacation_form">

					<div class="row">
						<div class="col-md-6">
							<p>
							<div class="form-group form-md-line-input">
								<label class="col-md-4 control-label" for="releaseDate">
									<span class="required"> * </span>开始日期：
								</label>
								<div class="col-md-7">
									<input type="text"
										class="form-control form-control-inline input-medium date-picker"
										readonly="readonly" data-date-format="yyyy-mm-dd"
										data-date-viewmode="years" ng-model="vacation.beginDate"
										id="beginDate" name="beginDate" />
									<div class="form-control-focus"></div>
								</div>
							</div>
							</p>

							<p>
							<div class="form-group form-md-line-input">
								<label class="col-md-4 control-label" for="releaseDate">
									<span class="required"> * </span>结束日期：
								</label>
								<div class="col-md-7">
									<input type="text"
										class="form-control form-control-inline input-medium date-picker"
										readonly="readonly" data-date-format="yyyy-mm-dd"
										data-date-viewmode="years" ng-model="vacation.endDate"
										id="endDate" name="endDate" />
									<div class="form-control-focus"></div>
								</div>
							</div>
							</p>
							<p>
							<div class="form-group form-md-line-input">
								<label class="col-md-4 control-label" for="form_control_1">请假天数</label>
								<div class="col-md-7">
									<input type="text" class="form-control"
										ng-model="vacation.days" id="days" name="days" placeholder="">
									<div class="form-control-focus"></div>
									<span class="help-block">输入请假天数</span>
								</div>
							</div>
							</p>

							<p>
							<div class="form-group form-md-line-input">
								<label class="col-md-4 control-label" for="form_control_1">休假类型</label>
								<div class="col-md-7">
									<select class="form-control" ng-model="vacation.vacationType"
										id="vacationType" name="vacationType">
										<option value="0">年假</option>
										<option value="1">事假</option>
										<option value="2">病假</option>
									</select>
									<div class="form-control-focus"></div>
									<span class="help-block">选择休假类型</span>
								</div>
							</div>
							</p>

							<p>
							<div class="form-group form-md-line-input">
								<label class="col-md-4 control-label" for="form_control_1">原因</label>
								<div class="col-md-7">
									<textarea class="form-control" ng-model="vacation.reason"
										id="reason" name="reason" rows="1"></textarea>
									<div class="form-control-focus"></div>
									<span class="help-block">输入原因</span>
								</div>
							</div>
							</p>


							<div class="modal-footer">
								<button type="submit" ng-click="applyVacation()"
									class="btn btn-primary">
									<i class="fa fa-save"></i> 申请
								</button>
								<button type="reset" class="btn btn-default">
									<i class="fa fa-reset"></i> 重置
								</button>

							</div>
						</div>
					</div>


				</form>




			</div>
			<div class="tab-pane" id="daiban">

				<div class="row">
					<div class="col-md-12">

						<!-- BEGIN EXAMPLE TABLE PORTLET-->
						<div class="portlet box green">
							<div class="portlet-title"></div>

							<div class="portlet-body">
								<table class="table table-striped table-bordered table-hover"
									id="sample_2">
									<thead>
										<tr>
											<th>任务状态</th>
											<th>单据类型</th>
											<th>申请人</th>
											<th>标题</th>
											<th>当前节点</th>
											<th>负责人</th>
											<th>任务创建时间</th>
											<th>流程状态</th>
										</tr>
									</thead>
									<tbody>

									</tbody>
								</table>
							</div>


						</div>
						<!-- END EXAMPLE TABLE PORTLET-->
					</div>
				</div>



			</div>
			<div class="tab-pane" id="yiban">
				<div class="row">
					<div class="col-md-12">

						<!-- BEGIN EXAMPLE TABLE PORTLET-->
						<div class="portlet box green">
							<div class="portlet-title"></div>

							<div class="portlet-body">
								<table class="table table-striped table-bordered table-hover"
									id="endTaskTable">
									<thead>
										<tr>
											<th>单据类型</th>
											<th>申请人</th>
											<th>标题</th>
											<th>任务开始时间</th>
											<th>任务签收时间</th>
											<th>任务结束时间 </th>
											<th>流程结束原因</th>
											<th>流程版本号</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody>

									</tbody>
								</table>
							</div>


						</div>
						<!-- END EXAMPLE TABLE PORTLET-->
					</div>
				</div>
			</div>
		</div>
	</div>
</div>


<input type="hidden" id="completeFlag" name="completeFlag" value="" />
<input type="hidden" name="userId" value="" />
<input type="hidden" name="vacationId" id="vacationId" value="" />
<input type="hidden" name="taskId" id="taskId" value="" />
<input type="hidden" id="reApply" name="reApply" value="" />
<input type="hidden" name="processInstanceId" id="processInstanceId" value="" />


<!-- 请假审核 modal 开始 -->
<div id="auditVacationModal" class="modal fade" role="dialog"
	aria-hidden="true">
	<div class="modal-dialog" style="width: 50%">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true"></button>
				<div id="appendTitle"></div>
				<h4 class="modal-title">请假审批</h4>
			</div>
			<div class="modal-body form">
				<form class="form-horizontal" id="auditVacation_form">

					<div class="row">
						<div class="col-md-12">
							<p>
								<div class="form-group form-md-line-input">
									<label class="col-md-4 control-label" for="releaseDate">
										<span class="required"> * </span>开始日期：
									</label>
									<div class="col-md-7">
										<input type="text" class="form-control" readonly="readonly"
											 id="audit_beginDate" name="audit_beginDate" placeholder="">
									</div>
								</div>
							</p>

							<p>
								<div class="form-group form-md-line-input">
									<label class="col-md-4 control-label" for="releaseDate">
										<span class="required"> * </span>结束日期：
									</label>
									<div class="col-md-7">
										<input type="text" class="form-control" readonly="readonly"
											 id="audit_endDate" name="audit_endDate" placeholder="">
									</div>
								</div>
							</p>
							<p>
								<div class="form-group form-md-line-input">
									<label class="col-md-4 control-label" for="form_control_1">请假天数</label>
									<div class="col-md-7">
										<input type="text" class="form-control" readonly="readonly"
											 id="audit_days" name="audit_days" placeholder="">
									</div>
								</div>
							</p>

							<p>
								<div class="form-group form-md-line-input">
									<label class="col-md-4 control-label" for="form_control_1">休假类型</label>
									<div class="col-md-7">
										<select class="form-control" disabled="disabled" 
											id="audit_vacationType" name="audit_vacationType">
											<option value="0">年假</option>
											<option value="1">事假</option>
											<option value="2">病假</option>
										</select>
									</div>
								</div>
							</p>

							<p>
								<div class="form-group form-md-line-input">
									<label class="col-md-4 control-label" for="form_control_1">原因</label>
									<div class="col-md-7">
										<textarea class="form-control" readonly="readonly" 
											id="audit_reason" name="audit_reason" rows="1"></textarea>
									</div>
								</div>
							</p>
							
							<p>
								<div class="portlet box green">
	                                <div class="portlet-title">
	                                    <div class="caption">
	                                        <i class="fa fa-globe"></i>评论 </div>
	                                </div>
	                                <div class="portlet-body">
	                                    <table class="table table-striped table-bordered table-hover order-column" id="pinglun">
	                                        <thead>
	                                            <tr>
	                                                <th>评论人</th>
	                                                <th>评论时间</th>
	                                                <th>评论内容</th>
	                                            </tr>
	                                        </thead>
	                                        <tbody id = "comment_audit">	
                                            </tbody>
	                                    </table>
	                                </div>
	                            </div>
							</p>
							
							<p>
								<div class="form-group form-md-line-input">
									<label class="col-md-4 control-label" for="form_control_1">我的意见</label>
									<div class="col-md-7">
										<textarea class="form-control"   
											id="content" name="content" rows="1"></textarea>
									</div>
								</div>
							</p>

							<div class="modal-footer">
								<button type="submit" ng-click="vacationPass()"
									class="btn btn-primary">
									<i class="fa fa-save"></i> 通过
								</button>
								<button type="submit" ng-click="vacationUnPass()"
									class="btn btn-primary">
									<i class="fa fa-save"></i> 不通过
								</button>
								<button type="submit" ng-click="closeAuditDialogue()"
									class="btn btn-primary">
									<i class="fa fa-save"></i> 关闭
								</button>
							</div>
						</div>
					</div>


				</form>
			</div>

		</div>
	</div>
</div>
<!-- 请假审核 modal  结束 -->

<!-- 请假更改 modal 开始 -->
<div id="modifyVacationModal" class="modal fade" role="dialog"
	aria-hidden="true">
	<div class="modal-dialog" style="width: 50%">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true"></button>
				<div id="appendTitle"></div>
				<h4 class="modal-title">请假审批更改</h4>
			</div>
			<div class="modal-body form">
				<form class="form-horizontal" id="auditVacation_form">

					<div class="row">
						<div class="col-md-12">
							<p>
								<div class="form-group form-md-line-input">
									<label class="col-md-4 control-label" for="releaseDate">
										<span class="required"> * </span>开始日期：
									</label>
									<div class="col-md-7">
										<input type="text"
											class="form-control form-control-inline input-medium date-picker"
											readonly="readonly" data-date-format="yyyy-mm-dd"
											data-date-viewmode="years" 
											id="modify_beginDate" name="modify_beginDate" />
										<div class="form-control-focus"></div>
									</div>
								</div>
							</p>

							<p>
								<div class="form-group form-md-line-input">
									<label class="col-md-4 control-label" for="releaseDate">
										<span class="required"> * </span>结束日期：
									</label>
									<div class="col-md-7">
										<input type="text"
											class="form-control form-control-inline input-medium date-picker"
											readonly="readonly" data-date-format="yyyy-mm-dd"
											data-date-viewmode="years" 
											id="modify_endDate" name="modify_endDate" />
										<div class="form-control-focus"></div>
									</div>
								</div>
							</p>
							<p>
								<div class="form-group form-md-line-input">
									<label class="col-md-4 control-label" for="form_control_1">请假天数</label>
									<div class="col-md-7">
										<input type="text" class="form-control" 
											 id="modify_days" name="modify_days" placeholder="">
									</div>
								</div>
							</p>

							<p>
								<div class="form-group form-md-line-input">
									<label class="col-md-4 control-label" for="form_control_1">休假类型</label>
									<div class="col-md-7">
										<select class="form-control"  
											id="modify_vacationType" name="modify_vacationType">
											<option value="0">年假</option>
											<option value="1">事假</option>
											<option value="2">病假</option>
										</select>
									</div>
								</div>
							</p>

							<p>
								<div class="form-group form-md-line-input">
									<label class="col-md-4 control-label" for="form_control_1">原因</label>
									<div class="col-md-7">
										<textarea class="form-control"  
											id="modify_reason" name="modify_reason" rows="1"></textarea>
									</div>
								</div>
							</p>
							
							<p>
								<div class="portlet box green">
	                                <div class="portlet-title">
	                                    <div class="caption">
	                                        <i class="fa fa-globe"></i>评论 </div>
	                                </div>
	                                <div class="portlet-body">
	                                    <table class="table table-striped table-bordered table-hover order-column" id="pinglun">
	                                        <thead>
	                                            <tr>
	                                                <th>评论人</th>
	                                                <th>评论时间</th>
	                                                <th>评论内容</th>
	                                            </tr>
	                                        </thead>
	                                        <tbody id="comment_modify">	
                                            </tbody>
	                                    </table>
	                                </div>
	                            </div>
							</p>
							
							

							<div class="modal-footer">
								<button type="submit" ng-click="replyVacation()"
									class="btn btn-primary">
									<i class="fa fa-save"></i> 重新申请
								</button>
								<button type="submit" ng-click="cancelApply()"
									class="btn btn-primary">
									<i class="fa fa-save"></i> 取消申请
								</button>
								<button type="submit" ng-click="closeModifyDialogue()"
									class="btn btn-primary">
									<i class="fa fa-save"></i> 关闭
								</button>
							</div>
						</div>
					</div>


				</form>
			</div>

		</div>
	</div>
</div>
<!-- 请假更改modal  结束 -->
