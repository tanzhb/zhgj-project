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
<form id="noticeForm" class="form-horizontal" >
	<div class="row">
		<div class="col-md-12">
			<!-- BEGIN EXAMPLE TABLE PORTLET-->
			<div class="portlet light">
				<div class="portlet-title">
					<div class="caption" style="width: 50%;">
						<div class="row">
							<div class="form-group">
								<div class="col-md-6">
										<label class="col-md-3 control-label right">标题：</label>
										<div class="col-md-9">
											<input type="text" class="form-control" id="demandPlanNum"
												name="demandPlanNum" ng-model="param.title"
												ng-hide="demandPlanAdd">
											<div class="form-control-focus"></div>
										</div>
								</div>
								<div class="col-md-6">
										<label class="col-md-4 control-label right">公告范围：</label>
										<div class="col-md-8 mt-checkbox-inline" id="noticeCheck">
											<label
												class="mt-checkbox mt-checkbox-single mt-checkbox-outline">
												<input type="checkbox" class="group-checkable noticeType" value="0" /> <span></span>公司内部
											</label> <label
												class="mt-checkbox mt-checkbox-single mt-checkbox-outline">
												<input type="checkbox" class="group-checkable noticeType" value="1" /> <span></span>供应商
											</label> <label
												class="mt-checkbox mt-checkbox-single mt-checkbox-outline">
												<input type="checkbox" class="group-checkable noticeType" value="2" /> <span></span>客户
											</label>
										</div>
									
								</div>
							</div>
						</div>
					</div>
					<div class="actions">
						<button ng-hide="companyAdd" class="btn green  btn-sm btn-circle"
							ng-click="saveNotice()">
							<i class="fa fa-check"></i> 保存
						</button>
					</div>
				</div>

				<div class="portlet-body form">
						<div class="form-body">
							<div class="row">
								<div class="col-md-12">
									<!-- BEGIN EXTRAS PORTLET-->
									<div class="portlet light form-fit ">
										<div class="portlet-body form">
											<div class="col-md-12">
												<div name="summernote" id="summernote" ></div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
				</div>



			</div>
			<!-- END EXAMPLE TABLE PORTLET-->
		</div>
	</div>
</form>
<!-- END MAIN CONTENT -->
