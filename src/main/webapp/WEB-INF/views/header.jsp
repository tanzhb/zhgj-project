<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!-- BEGIN HEADER INNER -->
<!-- <div class="page-header-inner container"> 默认头为盒状的样式-->
<!-- <link rel="stylesheet" href="assets/apps/css/jquery.typeahead.css"> --> 
<link rel="stylesheet" href="assets/global/plugins/bootstrap-select/css/bootstrap-select.css"> 
<link rel="stylesheet" href="assets/global/css/components.min.css"> 
<style type="text/css">
.typeahead {
	max-height: 250px;
	overflow: auto;
}
.badge badge-danger{
margin-top: -10px;
}

</style>
<div class="page-header-inner ">
	<!-- BEGIN LOGO -->
	<div class="page-logo">
		<a ui-sref="dashboard"> <img
			src="assets/layouts/layout2/img/logo-default.png" alt="logo"
			class="logo-default" />
		</a>
		<div class="menu-toggler sidebar-toggler">
			<!-- DOC: Remove the above "hide" to enable the sidebar toggler button on header -->
		</div>
	</div>
	<!-- END LOGO -->
	<!-- BEGIN RESPONSIVE MENU TOGGLER -->
	<a href="javascript:;" class="menu-toggler responsive-toggler"
		data-toggle="collapse" data-target=".navbar-collapse"> </a>
	<!-- END RESPONSIVE MENU TOGGLER -->
	<!-- BEGIN PAGE ACTIONS -->
	<!-- DOC: Remove "hide" class to enable the page header actions -->

	<div class="page-actions">
		<div class="page-bar">
			<ul class="page-breadcrumb" id='dashboard'>
				<!-- <li><i class="fa fa-home"></i> <a ui-sref="dashboard">首页</a> <i
					class="fa fa-angle-right"></i></li> -->

				<!-- <div id="loadPageBar"> -->

				<!-- </div> -->
			</ul>

		</div>
	</div>




	<!-- BEGIN PAGE TOP -->
	<div class="page-top">

		<!-- BEGIN TOP NAVIGATION MENU -->
		<div class="top-menu">
			<ul class="nav navbar-nav pull-right">
				<!-- <form class="search-form search-form-expanded">
			        <div class="typeahead__container" style="top:15px;right:100px;margin:0 auto;width:300px;">
			            <div class="typeahead__field">
			
			                <span class="typeahead__query">
			                    <input class="js-typeahead"
			                           name="q"
			                           type="search"
			                           autofocus
			                           autocomplete="off">
			                </span>
			                <span class="typeahead__button">
			                    <button type="submit">
			                        <span class="typeahead__search-icon"></span>
			                    </button>
			                </span>
			
			            </div>
			        </div>
			    </form> -->
			    
			    <li class="dropdown dropdown-user"><a href="#"
					class="dropdown-toggle" dropdown-menu-hover data-toggle="dropdown"
					data-close-others="true"> <span id="selValue">全部</span> <i class="fa fa-angle-down"></i>
				</a>
					<ul class="dropdown-menu dropdown-menu-default">
						<li><a href="#" ng-click="select1()">采购订单
						</a></li>
						
						<li><a href="#" ng-click="select2()">销售订单
						</a></li>
						<li class="divider"></li>
						<li><a href="#" ng-click="selectAll()">全部
						</a></li>
					</ul></li>
				
				<form class="search-form search-form-expanded"
					action="page_general_search_3.html" method="GET">
						
						
						
						 
						<div class="input-group">
							<input type="text" class="form-control typeahead"
								placeholder="Search..." name="query" id="searchInput"> 
								<span
								class="input-group-btn"> <a href="javascript:;" ng-click="search()" 
								class="btn submit"> <i class="icon-magnifier"></i>
							</a>
							</span>
						</div>
				</form>
				 <div class="top-menu">
                       <!--  <ul class="nav navbar-nav pull-right">
                            <li class="dropdown dropdown-extended dropdown-notification" id="header_notification_bar">
                                <a    ui-sref="myNotice"  class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true"  title="公告"
                                  >
                                    <i class="icon-bell"></i>
                                    <span > <noticeCount/></span>
                                </a>
                            </li>
                            <li class="dropdown dropdown-extended dropdown-notification" id="header_notification_bar">
                                <a ui-sref="myMessage" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true"    title="业务消息" >
                                    <i class="icon-envelope-open"></i>
                                    <span > <businessMessage/> </span>
                                </a>
                            </li>
                        </ul> -->
                        <input type = "hidden" id = "hide"/>
				<li class="dropdown dropdown-user"  style="float:right;margin-top: 10px;"> <a href="#"
					class="dropdown-toggle" dropdown-menu-hover data-toggle="dropdown"
					data-close-others="true"> <img alt="" class="img-circle" id="avatar"
						src="assets/pages/media/users/avatar3.jpg" /> <span
						class="username username-hide-on-mobile" id="usernameOfUserInfo"> 
						<shiro:principal />
						<!-- ng-src="uploadAttachFiles/{{userInfo.avatar}}" -->
					</span> <i class="fa fa-angle-down"></i>
				</a>
					<ul class="dropdown-menu dropdown-menu-default">
					    <li><a href="#" ng-click="psersonCenter()"> <i
								class="glyphicon glyphicon-user"></i> 个人中心
						</a></li>
						<li><a href="#" ng-click="editPassword()"> <i
								class="glyphicon glyphicon-edit"></i> 修改密码
						</a></li>
						<li class="divider"></li>
						<li><a href="#" ng-click="toggleFullScreen()"> <i
								class="glyphicon glyphicon-fullscreen"></i> 全 屏
						</a></li>
						<li><a href="#" ng-click="setMainStyle()"><i
								class="icon-settings"></i> 页面风格设置</a></li>
						<li><a href="rest/user/logout"> <i
								class="glyphicon glyphicon-off"></i> 退 出
						</a></li>
					</ul></li>
					 <ul class="nav navbar-nav pull-right">
                            <li class="dropdown dropdown-extended dropdown-notification" id="header_notification_bar">
                                <a    ui-sref="myNotice"  class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true"  title="公告"
                                  >
                                    <i class="icon-bell"></i>
                                    <span class="badge badge-danger" style="position: relative;top: -10px;"> {{noticeCount}} </span>
                                    <!-- <noticeCount/> -->
                                </a>
                            </li>
                            <li class="dropdown dropdown-extended dropdown-notification" id="header_notification_bar">
                                <a ui-sref="myMessage" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true"    title="业务消息" >
                                    <i class="icon-envelope-open"></i>
                                    <span class="badge badge-danger"  title="业务消息" style="position: relative;top: -10px;"> {{businessMessageSize}} </span>
                                  <!--  <businessMessage/>  -->
                                </a>
                            </li>
                        </ul>
                    </div>
				<!-- 全文检索下拉框隐藏下拉选择值 -->
			<%-- 	<input type = "hidden" id = "hide"/>
				<li class="dropdown dropdown-user"><a href="#"
					class="dropdown-toggle" dropdown-menu-hover data-toggle="dropdown"
					data-close-others="true"> <img alt="" class="img-circle" id="avatar"
						src="assets/pages/media/users/avatar3.jpg" /> <span
						class="username username-hide-on-mobile" id="usernameOfUserInfo"> 
						<shiro:principal />
						<!-- ng-src="uploadAttachFiles/{{userInfo.avatar}}" -->
					</span> <i class="fa fa-angle-down"></i>
				</a>
					<ul class="dropdown-menu dropdown-menu-default">
					    <li><a href="#" ng-click="psersonCenter()"> <i
								class="glyphicon glyphicon-user"></i> 个人中心
						</a></li>
						<li><a href="#" ng-click="editPassword()"> <i
								class="glyphicon glyphicon-edit"></i> 修改密码
						</a></li>
						<li class="divider"></li>
						<li><a href="#" ng-click="toggleFullScreen()"> <i
								class="glyphicon glyphicon-fullscreen"></i> 全 屏
						</a></li>
						<li><a href="#" ng-click="setMainStyle()"><i
								class="icon-settings"></i> 页面风格设置</a></li>
						<li><a href="rest/user/logout"> <i
								class="glyphicon glyphicon-off"></i> 退 出
						</a></li>
					</ul></li> --%>
			</ul>
		</div>
		
		<!-- END TOP NAVIGATION MENU -->
	</div>
	<!-- END PAGE TOP -->
</div>
<!-- END HEADER INNER -->
<script>
$(document).ready(function() {
	var $input = $(".typeahead");
	$input.typeahead({
		minLength : 1,//键入字数多少开始补全
		showHintOnFocus : "true",//将显示所有匹配项
		fitToElement : true,//选项框宽度与输入框一致
		items : "all",//提示数量上限
		source : function(query, process) {
			//query是输入框输入的文本内容, process是一个回调函数 
			$.post(ctx + "rest/search/searchKey", {
				queryStr : $input.val(), searchType:$("#hide").val()
			}, function(data) {
				
				if (data == "" || data.length == 0) {
					console.log("没有查询到相关结果");
				}
				var results = [];
				for (var i = 0; i < data.length; i++) {
					results.push(data[i].search_fields);
				}
				process(results);
			},"json");
		},
		afterSelect: function (item) {       //选择项之后的事件，item是当前选中的选项
           // $("#hd").val(item); //为隐藏输入框赋值
			
        },
		autoSelect : true
	});
});
</script>

