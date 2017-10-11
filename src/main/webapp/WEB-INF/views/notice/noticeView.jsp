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
			<div class="portlet light" align="center">
				<div class="portlet-body form">
						<div class="form-body">
							<div class="row">
								<div class="col-md-1">
								</div>
								<div class="col-md-10">
										<h2>{{param.title}}</h2>
								</div>
								<div class="col-md-1">
										<button  class="btn defualt  btn-sm btn-circle"
											ui-sref='myNotice' >
											返回
										</button>
								</div>
							</div>
							<div class="row">
								<div class="col-md-12">
										<h5>{{param.updater}}&nbsp;&nbsp;&nbsp;&nbsp;{{param.relaseDate}}</h5>
								</div>
							</div>
							<div class="row">
								<div class="col-md-12">
									<!-- BEGIN EXTRAS PORTLET-->
									<div class="portlet light form-fit ">
										<div class="portlet-body form">
											<div class="col-md-12" style="padding:20px 0;">
												<div ng-bind-html="param.context"></div>
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
<!-- END MAIN CONTENT -->
