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
				<!-- 全文检索下拉框隐藏下拉选择值 -->
				<input type = "hidden" id = "hide"/>
				<!-- BEGIN NOTIFICATION DROPDOWN -->
				<!-- DOC: Apply "dropdown-dark" class after below "dropdown-extended" to change the dropdown styte -->
				<!-- <li class="dropdown dropdown-extended dropdown-notification"
					id="header_notification_bar"><a href="#"
					class="dropdown-toggle" dropdown-menu-hover data-toggle="dropdown"
					data-close-others="true"> <i class="icon-bell"></i> <span
						class="badge badge-default"> 7 </span>
				</a>
					<ul class="dropdown-menu">
						<li class="external">
							<h3>
								<span class="bold">12 pending</span> notifications
							</h3> <a href="#/profile/dashboard">view all</a>
						</li>
						<li>
							<ul class="dropdown-menu-list scroller" style="height: 250px;"
								data-handle-color="#637283">
								<li><a href="javascript:;"> <span class="time">just
											now</span> <span class="details"> <span
											class="label label-sm label-icon label-success"> <i
												class="fa fa-plus"></i>
										</span> New user registered.
									</span>
								</a></li>
								<li><a href="javascript:;"> <span class="time">3
											mins</span> <span class="details"> <span
											class="label label-sm label-icon label-danger"> <i
												class="fa fa-bolt"></i>
										</span> Server #12 overloaded.
									</span>
								</a></li>
								<li><a href="javascript:;"> <span class="time">10
											mins</span> <span class="details"> <span
											class="label label-sm label-icon label-warning"> <i
												class="fa fa-bell-o"></i>
										</span> Server #2 not responding.
									</span>
								</a></li>
								<li><a href="javascript:;"> <span class="time">14
											hrs</span> <span class="details"> <span
											class="label label-sm label-icon label-info"> <i
												class="fa fa-bullhorn"></i>
										</span> Application error.
									</span>
								</a></li>
								<li><a href="javascript:;"> <span class="time">2
											days</span> <span class="details"> <span
											class="label label-sm label-icon label-danger"> <i
												class="fa fa-bolt"></i>
										</span> Database overloaded 68%.
									</span>
								</a></li>
								<li><a href="javascript:;"> <span class="time">3
											days</span> <span class="details"> <span
											class="label label-sm label-icon label-danger"> <i
												class="fa fa-bolt"></i>
										</span> A user IP blocked.
									</span>
								</a></li>
								<li><a href="javascript:;"> <span class="time">4
											days</span> <span class="details"> <span
											class="label label-sm label-icon label-warning"> <i
												class="fa fa-bell-o"></i>
										</span> Storage Server #4 not responding dfdfdfd.
									</span>
								</a></li>
								<li><a href="javascript:;"> <span class="time">5
											days</span> <span class="details"> <span
											class="label label-sm label-icon label-info"> <i
												class="fa fa-bullhorn"></i>
										</span> System Error.
									</span>
								</a></li>
								<li><a href="javascript:;"> <span class="time">9
											days</span> <span class="details"> <span
											class="label label-sm label-icon label-danger"> <i
												class="fa fa-bolt"></i>
										</span> Storage server failed.
									</span>
								</a></li>
							</ul>
						</li>
					</ul></li> -->
				<!-- END NOTIFICATION DROPDOWN -->
				<!-- BEGIN INBOX DROPDOWN -->
				<!-- DOC: Apply "dropdown-dark" class after below "dropdown-extended" to change the dropdown styte -->
				<!-- <li class="dropdown dropdown-extended dropdown-inbox"
					id="header_inbox_bar"><a href="#" class="dropdown-toggle"
					dropdown-menu-hover data-toggle="dropdown" data-close-others="true">
						<i class="icon-envelope-open"></i> <span
						class="badge badge-default"> 4 </span>
				</a>
					<ul class="dropdown-menu">
						<li class="external">
							<h3>
								You have <span class="bold">7 New</span> Messages
							</h3> <a href="#/profile/dashboard">view all</a>
						</li>
						<li>
							<ul class="dropdown-menu-list scroller" style="height: 275px;"
								data-handle-color="#637283">
								<li><a href="#"> <span class="photo"> <img
											src="assets/pages/media/users/avatar2.jpg" class="img-circle"
											alt="">
									</span> <span class="subject"> <span class="from"> Lisa
												Wong </span> <span class="time">Just Now </span>
									</span> <span class="message"> Vivamus sed auctor nibh congue
											nibh. auctor nibh auctor nibh... </span>
								</a></li>
								<li><a href="#"> <span class="photo"> <img
											src="assets/pages/media/users/avatar3.jpg" class="img-circle"
											alt="">
									</span> <span class="subject"> <span class="from">
												Richard Doe </span> <span class="time">16 mins </span>
									</span> <span class="message"> Vivamus sed congue nibh auctor
											nibh congue nibh. auctor nibh auctor nibh... </span>
								</a></li>
								<li><a href="#"> <span class="photo"> <img
											src="assets/pages/media/users/avatar1.jpg" class="img-circle"
											alt="">
									</span> <span class="subject"> <span class="from"> Bob
												Nilson </span> <span class="time">2 hrs </span>
									</span> <span class="message"> Vivamus sed nibh auctor nibh
											congue nibh. auctor nibh auctor nibh... </span>
								</a></li>
								<li><a href="#"> <span class="photo"> <img
											src="assets/pages/media/users/avatar2.jpg" class="img-circle"
											alt="">
									</span> <span class="subject"> <span class="from"> Lisa
												Wong </span> <span class="time">40 mins </span>
									</span> <span class="message"> Vivamus sed auctor 40% nibh
											congue nibh... </span>
								</a></li>
								<li><a href="#"> <span class="photo"> <img
											src="assets/pages/media/users/avatar3.jpg" class="img-circle"
											alt="">
									</span> <span class="subject"> <span class="from">
												Richard Doe </span> <span class="time">46 mins </span>
									</span> <span class="message"> Vivamus sed congue nibh auctor
											nibh congue nibh. auctor nibh auctor nibh... </span>
								</a></li>
							</ul>
						</li>
					</ul></li> -->
				<!-- END INBOX DROPDOWN -->
				<!-- BEGIN TODO DROPDOWN -->
				<!-- DOC: Apply "dropdown-dark" class after below "dropdown-extended" to change the dropdown styte -->
				<!-- <li class="dropdown dropdown-extended dropdown-tasks"
					id="header_task_bar"><a href="#" class="dropdown-toggle"
					dropdown-menu-hover data-toggle="dropdown" data-close-others="true">
						<i class="icon-calendar"></i> <span class="badge badge-default">
							3 </span>
				</a>
					<ul class="dropdown-menu extended tasks">
						<li class="external">
							<h3>
								You have <span class="bold">12 pending</span> tasks
							</h3> <a href="#/todo">view all</a>
						</li>
						<li>
							<ul class="dropdown-menu-list scroller" style="height: 275px;"
								data-handle-color="#637283">
								<li><a href="javascript:;"> <span class="task">
											<span class="desc">New release v1.2 </span> <span
											class="percent">30%</span>
									</span> <span class="progress"> <span style="width: 40%;"
											class="progress-bar progress-bar-success" aria-valuenow="40"
											aria-valuemin="0" aria-valuemax="100"> <span
												class="sr-only">40% Complete</span>
										</span>
									</span>
								</a></li>
								<li><a href="javascript:;"> <span class="task">
											<span class="desc">Application deployment</span> <span
											class="percent">65%</span>
									</span> <span class="progress"> <span style="width: 65%;"
											class="progress-bar progress-bar-danger" aria-valuenow="65"
											aria-valuemin="0" aria-valuemax="100"> <span
												class="sr-only">65% Complete</span>
										</span>
									</span>
								</a></li>
								<li><a href="javascript:;"> <span class="task">
											<span class="desc">Mobile app release</span> <span
											class="percent">98%</span>
									</span> <span class="progress"> <span style="width: 98%;"
											class="progress-bar progress-bar-success" aria-valuenow="98"
											aria-valuemin="0" aria-valuemax="100"> <span
												class="sr-only">98% Complete</span>
										</span>
									</span>
								</a></li>
								<li><a href="javascript:;"> <span class="task">
											<span class="desc">Database migration</span> <span
											class="percent">10%</span>
									</span> <span class="progress"> <span style="width: 10%;"
											class="progress-bar progress-bar-warning" aria-valuenow="10"
											aria-valuemin="0" aria-valuemax="100"> <span
												class="sr-only">10% Complete</span>
										</span>
									</span>
								</a></li>
								<li><a href="javascript:;"> <span class="task">
											<span class="desc">Web server upgrade</span> <span
											class="percent">58%</span>
									</span> <span class="progress"> <span style="width: 58%;"
											class="progress-bar progress-bar-info" aria-valuenow="58"
											aria-valuemin="0" aria-valuemax="100"> <span
												class="sr-only">58% Complete</span>
										</span>
									</span>
								</a></li>
								<li><a href="javascript:;"> <span class="task">
											<span class="desc">Mobile development</span> <span
											class="percent">85%</span>
									</span> <span class="progress"> <span style="width: 85%;"
											class="progress-bar progress-bar-success" aria-valuenow="85"
											aria-valuemin="0" aria-valuemax="100"> <span
												class="sr-only">85% Complete</span>
										</span>
									</span>
								</a></li>
								<li><a href="javascript:;"> <span class="task">
											<span class="desc">New UI release</span> <span
											class="percent">38%</span>
									</span> <span class="progress progress-striped"> <span
											style="width: 38%;"
											class="progress-bar progress-bar-important"
											aria-valuenow="18" aria-valuemin="0" aria-valuemax="100">
												<span class="sr-only">38% Complete</span>
										</span>
									</span>
								</a></li>
							</ul>
						</li>
					</ul></li> -->
				<!-- END TODO DROPDOWN -->
				<!-- BEGIN USER LOGIN DROPDOWN -->
				<!-- DOC: Apply "dropdown-dark" class after below "dropdown-extended" to change the dropdown styte -->
				<li class="dropdown dropdown-user"><a href="#"
					class="dropdown-toggle" dropdown-menu-hover data-toggle="dropdown"
					data-close-others="true"> <img alt="" class="img-circle"
						src="assets/pages/media/users/avatar3.jpg" /> <span
						class="username username-hide-on-mobile"> <shiro:principal />
					</span> <i class="fa fa-angle-down"></i>
				</a>
					<ul class="dropdown-menu dropdown-menu-default">
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

				<!-- END USER LOGIN DROPDOWN -->
				<!-- DOC: Apply "dropdown-dark" class after below "dropdown-extended" to change the dropdown styte -->

				<!-- END USER LOGIN DROPDOWN -->
			</ul>
		</div>
		<!-- END TOP NAVIGATION MENU -->
	</div>
	<!-- END PAGE TOP -->
</div>
<!-- END HEADER INNER -->


	
<!-- <script>

        typeof $.typeahead === 'function' && $.typeahead({
            input: ".js-typeahead",
            minLength: 1,
            maxItem: 15,
            order: "asc",
            hint: true,
            dynamic: true,
            group:true,
            maxItemPerGroup: 5,
            backdrop: {
                "background-color": "#fff"
            },
            //href: "/beers/{{group}}/{{display}}/",
            dropdownFilter: "全部",
            emptyTemplate: 'No result for "{{query}}"',
            display:["comName","materielName"],
            source: {
            	"comName": {
                    ajax: {
                        url: ctx + "rest/search/searchList",
                        type:"post",
                        dataType: "json"
                    }
                },
                "materielName": {
                    ajax: {
                        url: ctx + "rest/search/searchList",
                        type:"post",
                        dataType: "json"
                    }
                }
            },
            callback: {
                onReady: function (node) {
                    this.container.find('.' + this.options.selector.dropdownItem + '.group-ale a').trigger('click')
                },
                onDropdownFilter: function (node, query, filter, result) {
                    console.log(query)
                    console.log(filter)
                    console.log(result)
                }
            },
            debug: true
        });

    </script> -->
<!-- <script src="assets/apps/scripts/bootstrap3-typeahead.min.js"
	type="text/javascript"></script> -->

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

