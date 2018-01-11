<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%-- <c:set var="ctx" value="${pageContext.request.contextPath}" /> --%>
<c:set var="ctx" value="<%=basePath%>" />
<!DOCTYPE html>
<!-- 
Template Name: Metronic - Responsive Admin Dashboard Template build with Twitter Bootstrap 3.3.7
Version: 4.7.5
Author: KeenThemes
Website: http://www.keenthemes.com/
Contact: support@keenthemes.com
Follow: www.twitter.com/keenthemes
Dribbble: www.dribbble.com/keenthemes
Like: www.facebook.com/keenthemes
Purchase: http://themeforest.net/item/metronic-responsive-admin-dashboard-template/4021469?ref=keenthemes
Renew Support: http://themeforest.net/item/metronic-responsive-admin-dashboard-template/4021469?ref=keenthemes
License: You must have a valid license purchased only from themeforest(the above link) in order to legally use the theme for your project.
-->
<!--[if IE 8]> <html lang="en" class="ie8 no-js" data-ng-app="MetronicApp"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js" data-ng-app="MetronicApp"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en" data-ng-app="MetronicApp">
<!--<![endif]-->
<!-- BEGIN HEAD -->

<head>
<script type="text/javascript" charset="utf-8">
    var ctx = "${ctx}";
</script>
<base href="<%=basePath%>">
<title data-ng-bind="'中航国际 | ' + $state.current.data.pageTitle"></title>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1" name="viewport" />
<meta content="" name="description" />
<meta content="" name="author" />
<!-- BEGIN GLOBAL MANDATORY STYLES -->
<link
	href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700&subset=all"
	rel="stylesheet" type="text/css" />
<link href="assets/global/plugins/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="assets/global/plugins/simple-line-icons/simple-line-icons.min.css"
	rel="stylesheet" type="text/css" />
<link href="assets/global/plugins/bootstrap/css/bootstrap.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="assets/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css"
	rel="stylesheet" type="text/css" />
<!-- END GLOBAL MANDATORY STYLES -->
<!-- BEGIN PAGE LEVEL PLUGINS -->
<link href="assets/global/plugins/bootstrap-toastr/toastr.min.css"
	rel="stylesheet" type="text/css" />
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN DYMANICLY LOADED CSS FILES(all plugin and page related styles must be loaded between GLOBAL and THEME css files ) -->
<link id="ng_load_plugins_before" />
<!-- END DYMANICLY LOADED CSS FILES -->
<!-- BEGIN THEME STYLES -->
<!-- DOC: To use 'rounded corners' style just load 'components-rounded.css' stylesheet instead of 'components.css' in the below style tag -->
<link href="assets/global/css/components.min.css" id="style_components"
	rel="stylesheet" type="text/css" />
<link href="assets/global/css/plugins.min.css" rel="stylesheet"
	type="text/css" />
<link href="assets/global/css/components-md.min.css" rel="stylesheet"
	id="style_components" type="text/css" />
<link href="assets/global/css/plugins-md.min.css" rel="stylesheet"
	type="text/css" />
<link href="assets/layouts/layout2/css/layout.min.css" rel="stylesheet"
	type="text/css" />
<link href="assets/layouts/layout2/css/themes/blue.min.css"
	rel="stylesheet" type="text/css" id="style_color" />
<link href="assets/layouts/layout2/css/custom.min.css" rel="stylesheet"
	type="text/css" />
<link href="assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.css" rel="stylesheet"
	type="text/css" />
<link href="assets/global/plugins/icheck/skins/all.css" rel="stylesheet"
	type="text/css" />
<link href="assets/global/plugins/bootstrap-daterangepicker/daterangepicker.min.css" rel="stylesheet"
	type="text/css" />
<link href="assets/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.min.css" rel="stylesheet"
	type="text/css" />
<link href="assets/global/plugins/bootstrap-timepicker/css/bootstrap-timepicker.min.css" rel="stylesheet"
	type="text/css" />
<link href="assets/global/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css" rel="stylesheet"
	type="text/css" />
<link href="assets/global/plugins/bootstrap-select/css/bootstrap-select.css" rel="stylesheet" type="text/css" />
<link href="assets/apps/css/common.css"
	rel="stylesheet" type="text/css" />
<link href="assets/global/plugins/jquery-notific8/jquery.notific8.min.css"
	rel="stylesheet" type="text/css" />
<!-- <link href="assets/global/plugins/datatables/datatables.min.css" rel="stylesheet"
	type="text/css" />
<link href="assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css" rel="stylesheet"
	type="text/css" /> -->
<!-- END THEME STYLES -->
<link rel="shortcut icon" href="favicon.ico" />
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<!-- DOC: Apply "page-header-fixed-mobile" and "page-footer-fixed-mobile" class to body element to force fixed header or footer in mobile devices -->
<!-- DOC: Apply "page-sidebar-closed" class to the body and "page-sidebar-menu-closed" class to the sidebar menu element to hide the sidebar by default -->
<!-- DOC: Apply "page-sidebar-hide" class to the body to make the sidebar completely hidden on toggle -->
<!-- DOC: Apply "page-sidebar-closed-hide-logo" class to the body element to make the logo hidden on sidebar toggle -->
<!-- DOC: Apply "page-sidebar-hide" class to body element to completely hide the sidebar on sidebar toggle -->
<!-- DOC: Apply "page-sidebar-fixed" class to have fixed sidebar -->
<!-- DOC: Apply "page-footer-fixed" class to the body element to have fixed footer -->
<!-- DOC: Apply "page-sidebar-reversed" class to put the sidebar on the right side -->
<!-- DOC: Apply "page-full-width" class to the body element to have full width page without the sidebar menu -->

<body ng-controller="AppController"
	class="page-boxed page-header-fixed page-sidebar-closed-hide-logo page-container-bg-solid page-sidebar-closed-hide-logo page-on-load"
	ng-class="{'page-sidebar-closed': settings.layout.pageSidebarClosed}">
	<!-- BEGIN PAGE SPINNER -->
	<div ng-spinner-bar class="page-spinner-bar">
		<div class="bounce1"></div>
		<div class="bounce2"></div>
		<div class="bounce3"></div>
	</div>
	<!-- END PAGE SPINNER -->
	<!-- BEGIN HEADER -->
	<div data-ng-include="'rest/page/header'"
		data-ng-controller="HeaderController"
		class="page-header navbar navbar-fixed-top"></div>
	<!-- END HEADER -->
	<div class="clearfix"></div>
	<!-- BEGIN CONTAINER -->
	<div class="container">
		<div class="page-container">
			<!-- BEGIN SIDEBAR -->
			<div data-ng-include="'rest/page/sidebar'"
				data-ng-controller="SidebarController" class="page-sidebar-wrapper">
			</div>
			<!-- END SIDEBAR -->
			<div class="page-content-wrapper">
				<div class="page-content">
					<!-- BEGIN STYLE CUSTOMIZER(optional) -->
					<div data-ng-include="'rest/page/themePanel'"
						data-ng-controller="ThemePanelController"
						class="theme-panel hidden-xs hidden-sm"></div>
					<!-- END STYLE CUSTOMIZER -->
					<!-- BEGIN ACTUAL CONTENT -->
					<div ui-view class="fade-in-up"></div>
					<!-- END ACTUAL CONTENT -->
				</div>
			</div>
			<!-- BEGIN QUICK SIDEBAR -->
			<a href="javascript:;" class="page-quick-sidebar-toggler"> <i
				class="icon-login"></i>
			</a>
			<div data-ng-include="'rest/page/quickSidebar'"
				data-ng-controller="QuickSidebarController"
				class="page-quick-sidebar-wrapper"></div>
			<!-- END QUICK SIDEBAR -->
		</div>
		<!-- BEGIN FOOTER -->
		<div data-ng-include="'rest/page/footer'"
			data-ng-controller="FooterController" class="page-footer"></div>
		<!-- END FOOTER -->
	</div>
	<!-- END CONTAINER -->
	<!-- BEGIN QUICK NAV -->
	<!-- END QUICK NAV -->
	<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
	<!-- BEGIN CORE JQUERY PLUGINS -->
	<!--[if lt IE 9]>
	<script src="assets/global/plugins/respond.min.js"></script>
	<script src="assets/global/plugins/excanvas.min.js"></script> 
	<![endif]-->
	<script src="assets/global/plugins/jquery.min.js"
		type="text/javascript"></script>
	<script src="assets/global/plugins/bootstrap/js/bootstrap.min.js"
		type="text/javascript"></script>
	<script
		src="assets/global/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js"
		type="text/javascript"></script>
	<script
		src="assets/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js"
		type="text/javascript"></script>
	<script src="assets/global/plugins/jquery.blockui.min.js"
		type="text/javascript"></script>
	<script src="assets/global/plugins/js.cookie.min.js"
		type="text/javascript"></script>
	<script
		src="assets/global/plugins/bootstrap-switch/js/bootstrap-switch.min.js"
		type="text/javascript"></script>
	<!-- END CORE JQUERY PLUGINS -->
	<!-- BEGIN CORE ANGULARJS PLUGINS -->
	<script src="assets/global/plugins/angularjs/angular.min.js"
		type="text/javascript"></script>
	<script src="assets/global/plugins/angularjs/angular-sanitize.min.js"
		type="text/javascript"></script>
	<script src="assets/global/plugins/angularjs/angular-touch.min.js"
		type="text/javascript"></script>
	<script
		src="assets/global/plugins/angularjs/plugins/angular-ui-router.min.js"
		type="text/javascript"></script>
	<script src="assets/global/plugins/angularjs/plugins/ocLazyLoad.min.js"
		type="text/javascript"></script>
	<script
		src="assets/global/plugins/angularjs/plugins/ui-bootstrap-tpls.min.js"
		type="text/javascript"></script>
	<script src="assets/global/plugins/bootstrap-toastr/toastr.min.js"
		type="text/javascript"></script>
	<script src="assets/global/plugins/jquery-notific8/jquery.notific8.min.js"
		type="text/javascript"></script>
	<script
		src="assets/global/plugins/jquery-validation/js/jquery.validate.min.js"
		type="text/javascript"></script>
	<script src="assets/global/plugins/bootbox/bootbox.min.js" type="text/javascript"></script>
	<!-- END CORE ANGULARJS PLUGINS -->
	<!-- BEGIN APP LEVEL ANGULARJS SCRIPTS -->
	<script src="assets/apps/main.js" type="text/javascript"></script>
	<script src="assets/apps/directives.js" type="text/javascript"></script>
	<script src="assets/apps/common.js" type="text/javascript"></script>
	<script src="assets/apps/task.js" type="text/javascript"></script>
	<script src="assets/apps/controllers/HeaderController.js"
		type="text/javascript"></script>
	<script src="assets/apps/service/UserService.js"
		type="text/javascript"></script>
	<!-- END APP LEVEL ANGULARJS SCRIPTS -->
	<!-- BEGIN APP LEVEL JQUERY SCRIPTS -->
	<script src="assets/global/scripts/app.min.js" type="text/javascript"></script>
	<script src="assets/layouts/layout2/scripts/layout.min.js"
		type="text/javascript"></script>
	<script src="assets/layouts/global/scripts/quick-sidebar.min.js"
		type="text/javascript"></script>
	<script src="assets/layouts/global/scripts/quick-nav.min.js"
		type="text/javascript"></script>
	<script src="assets/layouts/layout2/scripts/demo.min.js"
		type="text/javascript"></script>
	<!-- END APP LEVEL JQUERY SCRIPTS -->
	<!-- BEGIN PAGE SCRIPTS -->
	
	<script src="assets/apps/scripts/bootstrap3-typeahead.js"
	type="text/javascript"></script>
	<script src="assets/global/plugins/tm.pagination.js"
	type="text/javascript"></script>
 	<script src="assets/apps/scripts/sockjs-0.3.4.min.js" type="text/javascript" ></script>    <script src="assets/global/plugins/bootstrap-tabdrop/js/bootstrap-tabdrop.js" type="text/javascript"></script>
    <script src="assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.js" type="text/javascript"></script>
    <script src="assets/global/plugins/jquery-repeater/jquery.repeater.js" type="text/javascript"></script>
    <script src="assets/global/plugins/bootbox/bootbox.min.js" type="text/javascript"></script>
    <script src="assets/pages/scripts/ui-blockui.min.js" type="text/javascript"></script>
    <script src="assets/apps/scripts/pageHandle.js" type="text/javascript"></script>
	<script src="assets/global/plugins/icheck/icheck.min.js" type="text/javascript"></script>
	<script src="assets/pages/scripts/form-icheck.min.js" type="text/javascript"></script>
	<script src="assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js" type="text/javascript"></script>
	<script src="assets/global/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js" type="text/javascript"></script>
	<script src="assets/global/plugins/bootstrap-datepicker/locales/bootstrap-datetimepicker.zh-CN.min.js" type="text/javascript"></script>
	<script src="assets/global/plugins/bootstrap-datepicker/locales/bootstrap-datepicker.zh-CN.min.js" type="text/javascript"></script>
	<script src="assets/global/plugins/bootstrap-select/js/bootstrap-select.min.js" type="text/javascript"></script>
	<script src="assets/global/plugins/bootstrap-select/js/i18n/defaults-zh_CN.js" type="text/javascript"></script>
	<script src="assets/apps/scripts/angular-file-upload-shim.min.js" type="text/javascript"></script>
	<script src="assets/apps/scripts/angular-file-upload.min.js" type="text/javascript"></script>
	<script src="assets/apps/scripts/FileUploader.js" type="text/javascript"></script>
	<script src="assets/apps/scripts/layer/layer.js" type="text/javascript"></script>
<!-- 	<script src="assets/global/plugins/datatables/datatables.all.min.js" type="text/javascript"></script>
    <script src="assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js" type="text/javascript"></script> -->
	<!-- END  PAGE SCRIPTS -->
	<!-- END JAVASCRIPTS -->
	<!-- 修改密码modal开始  -->
	<div id="editPasswordModal" class="modal fade" role="dialog"
		aria-hidden="true">
		<div class="modal-dialog" style="width: 30%">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true"></button>
					<div id="appendTitle"></div>
					<h4 class="modal-title">修改密码</h4>
				</div>
				<div class="modal-body form">
					<form action="#" class="form-horizontal" id="editPassword">
						<div class="alert alert-danger display-hide">
							<button class="close" data-close="alert"></button>
							请先输入正确数据！
						</div>
						<div class="row">
							<div class="col-md-12">
								<p>
								<div class="form-group form-md-line-input">
									<label class="col-md-4 control-label" for="form_control_1">原密码
										<span class="required">*</span>
									</label>
									<div class="col-md-7">
										<input type="password" class="form-control" placeholder=""
											ng-model="changePassword.oldPassword" name="password1" id="password1">
										<div class="form-control-focus"></div>
										<span class="help-block">输入原密码</span>
									</div>
								</div>
								</p>

								<p>
								<div class="form-group form-md-line-input">
									<label class="col-md-4 control-label" for="form_control_1">新密码
										<span class="required">*</span>
									</label>
									<div class="col-md-7">
										<input type="password" class="form-control" placeholder=""
											ng-model="changePassword.newPassword" name="password2" id="password2">
										<div class="form-control-focus"></div>
										<span class="help-block">输入新密码</span>
									</div>
								</div>
								</p>

								<p>
								<div class="form-group form-md-line-input">
									<label class="col-md-4 control-label" for="form_control_1">确认密码
										<span class="required">*</span>
									</label>
									<div class="col-md-7">
										<input type="password" class="form-control" placeholder=""
											id="twoPass" name="twoPass">
										<div class="form-control-focus"></div>
										<span class="help-block">输入确认密码</span>
									</div>
								</div>
								</p>

							</div>
						</div>

						<div class="modal-footer">
							<button type="reset" class="btn btn-default">
								<i class="fa fa-reset"></i>重置
							</button>
							<button type="submit" ng-click="savePass()"
								class="btn btn-primary">
								<i class="fa fa-save"></i> 保存
							</button>
						</div>
					</form>
				</div>

			</div>
		</div>
	</div>
	<!-- 修改密码modal 结束 -->
	
	<!-- 密码修改成功modal 开始 -->

	<div class="modal fade" id="editPassSuccess" tabindex="-1" data-backdrop="static" data-keyboard="false">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                   <!--  <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button> -->
                    <h4 class="modal-title">密码修改</h4>
                </div>
                <div class="modal-body"> 密码修改成功，请重新登录！ </div>
                <div class="modal-footer">
                	<a href="rest/user/logout"> <i class="icon-confirm"></i>确定</a>
                </div>
            </div>
        </div>
    </div>
    <!-- 密码修改成功modal 结束 -->
</body>
<!-- END BODY -->

</html>