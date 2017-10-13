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
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN EXAMPLE TABLE PORTLET-->
		<div class="portlet light">
			<jsp:include page="baseNoticeView.jsp"></jsp:include>


			<!-- <div class="portlet-body form">
				<form>
					<div class="form-body">
						<div class="row">
							<div class="col-md-8">
								<div class="form-group ">
									<label class="control-label bold">原因：</label>
									<div class="">
										<input type="text" name="reason" class="form-control"
											ng-model="param.reason">
										<div class="form-control-focus"></div>
										<span class="help-block">请输入原因</span>
									</div>
								</div>
							</div>
						</div>
					</div>
				</form>
			</div> -->
			<div class="portlet-title" >
				<div align="center">
					<button type="button" ng-click="applyNotice()"
						class="btn blue btn-circle  btn-sm">提交申请</button>&nbsp;&nbsp;
					<button type="button" ng-click="cancelNotice()"
						class="btn default btn-circle  btn-sm">
						<i class="fa fa-undo"></i> 取消
					</button>
				</div>
			</div>
		</div>

		<!-- END EXAMPLE TABLE PORTLET-->
	</div>
</div>
<!-- END MAIN CONTENT -->
