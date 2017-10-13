<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- BEGIN PAGE HEADER-->
<style>
.box_card{
	width: 33.333333%;
	float: left;
	margin-left: 0px;
	margin-right: 0px;
	margin-top: 4px;
	margin-bottom: 4px;
}

.left{
	float: left;
}
</style>
<!-- END PAGE HEADER-->
<!-- BEGIN MAIN CONTENT -->
<form id="noticeForm" class="form-horizontal">
	<div class="row">
		<div class="col-md-12">
			<!-- BEGIN EXAMPLE TABLE PORTLET-->
			<div class="portlet light">
				<jsp:include page="baseNoticeAdd.jsp"></jsp:include>


				<!-- 评论START -->
				<div class="portlet-body">
					<div class="table-scrollable">
						<div class="portlet box green">
							<div class="portlet-title">
								<div class="caption">
									<i class="fa fa-globe"></i>评论
								</div>
							</div>
							<div class="portlet-body">
								<table
									class="table table-striped table-bordered table-hover order-column"
									id="pinglun">
									<thead>
										<tr>
											<th>评论人</th>
											<th>评论时间</th>
											<th>评论内容</th>
										</tr>
									</thead>
									<tbody id="comment_audit">
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
				<!-- 评论END -->
				<!-- 意见START -->
				<div class="portlet-body">
					<div class="row">
						<div class="form-group form-md-line-input">
							<label class="col-md-1 control-label" for="form_control_1">我的意见:</label>
							<div class="col-md-11">
								<textarea class="form-control" ng-model="content" id="content"
									name="content" rows="1"></textarea>
								<div class="form-control-focus"></div>
								<span class="help-block">输入我的意见</span>
							</div>
						</div>
						<input type="hidden" name="serialNum" id="serialNum" value="" />
						<input type="hidden" name="taskId" id="taskId" value="" />
					</div>
				</div>
				<!-- 意见END -->
				<div class="row" align="center">
					<button class="btn blue  btn-sm btn-circle" ng-click="reApply()">
						重新申请</button>
					<button class="btn defualt  btn-sm btn-circle"
						ng-click="apUnPass()" onclick="return false;">
						<i class="fa fa-mail-reply"></i>取消申请
					</button>
				</div>
			</div>
			<!-- END EXAMPLE TABLE PORTLET-->
		</div>
	</div>
</form>
<!-- END MAIN CONTENT -->
