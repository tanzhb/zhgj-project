<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<head>
<meta http-equiv="Content-Type"
	content="multipart/form-data; charset=utf-8" />
</head>
<!-- BEGIN PAGE HEADER-->
<!-- END PAGE HEADER-->
<!-- BEGIN MAIN CONTENT -->
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN EXAMPLE TABLE PORTLET-->
		<div class="portlet light">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe font-green"></i>
					<span class="caption-subject font-green bold uppercase">公告</span>
				</div>
				<div class="actions">
					<div class="btn-group btn-group-devided" data-toggle="buttons">
							<label class="btn btn-transparent green btn-circle btn-sm" ng-click="toAddNotice()">
		                                              <i class="fa fa-plus"></i> 添加</label>
							<label class="btn btn-transparent purple btn-circle btn-sm" ng-click="toEditNotice()">
		                                              <i class="fa fa-edit"></i> 修改</label>                  
							<label class="btn btn-transparent red btn-circle btn-sm" ng-click="deleteNoticeBatch()">
		                                              <i class="fa fa-minus"></i> 删除</label>
	                 </div>
				</div>
			</div>
			<div class="portlet-body">
				<!-- <div class="table-responsive"> -->
				<table class="table table-striped table-bordered table-hover table-checkable order-column" id="noticeTable">
					<thead>
						<tr>
							<th>
                                 <label class="mt-checkbox mt-checkbox-single mt-checkbox-outline">
                                     <input type="checkbox" class="group-checkable" data-set="#noticeTable .checkboxes" />
                                     <span></span>
                                 </label>
							</th>
							<th>公告类型</th>
							<th>标题</th>
							<th>创建人</th>
							<th>更新人</th>
							<th>更新时间</th>
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

<!-- END MAIN CONTENT -->
<!-- BEGIN MAIN JS -->
<script>
	// TableDatatablesManaged.init();
	//$('.date-picker').datepicker();
</script>
<!-- END MAIN JS -->
