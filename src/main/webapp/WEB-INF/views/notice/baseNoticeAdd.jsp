<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="portlet-body form">
	<!-- <div class="form-body"> -->
	<div class="row" style="padding-top: 20px;">
		<div class="col-md-5">
			<label class="col-md-4 control-label bold">标题：</label>
			<div class="col-md-8">
				<input type="text" class="form-control" id="noticeTitle"
					name="noticeTitle" ng-model="param.noticeTitle"
					ng-hide="demandPlanAdd">
				<div class="form-control-focus"></div>
			</div>
		</div>
		<div class="col-md-5">
			<label class="col-md-4 control-label bold">公告范围：</label>
			<div class="col-md-8 mt-checkbox-inline" id="noticeCheck">
				<label class="mt-checkbox mt-checkbox-single mt-checkbox-outline">
					<input type="checkbox" class="group-checkable noticeType" value="0" />
					<span></span>公司内部
				</label> <label class="mt-checkbox mt-checkbox-single mt-checkbox-outline">
					<input type="checkbox" class="group-checkable noticeType" value="2" />
					<span></span>供应商
				</label> <label class="mt-checkbox mt-checkbox-single mt-checkbox-outline">
					<input type="checkbox" class="group-checkable noticeType" value="1" />
					<span></span>客户
				</label>
			</div>
		</div>
		<div class="col-md-2" align="right">
			<button ng-hide="companyAdd" class="btn green  btn-sm btn-circle"
				ng-click="saveNotice()">
				<i class="fa fa-check"></i> 保存
			</button>
		</div>
	</div>
	<!-- </div> -->
</div>
<div class="portlet-title" style="min-height: 1px;"></div>

<div class="portlet-body form">
	<div class="form-body">
		<div class="row">
			<div class="col-md-12">
				<!-- BEGIN EXTRAS PORTLET-->
				<div class="portlet light form-fit ">
					<div class="portlet-body form">
						<div class="col-md-12">
							<div name="summernote" id="summernote"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
