<%@ page language="java" pageEncoding="utf-8"%>

	<div class="row">
		<!-- <div class="col-md-8">
			Begin: life time stats
			<div class="portlet light ">
				<div class="portlet-title">
					<div class="caption">
						<i class="icon-share font-blue"></i> <span
							class="caption-subject font-blue bold uppercase">Overview</span>
						<span class="caption-helper">report overview...</span>
					</div>
				</div>
				<div class="portlet-body" style="height:150px">
					
				</div>
			</div>
			End: life time stats
		</div> -->
		<!-- <div class="col-md-4">
			Begin: life time stats
			BEGIN PORTLET
			<div class="portlet light ">
				<div class="portlet-title tabbable-line">
					<div class="caption">
						<i class="icon-globe font-red"></i> <span
							class="caption-subject font-red bold uppercase">Revenue</span>
					</div>
				</div>
				<div class="portlet-body" style="height:150px">					
					
				</div>
			</div>
			End: life time stats
		</div> -->
	</div>
	<div class="row"  >
		<div class="col-md-6 col-sm-7"  >
			<!-- BEGIN PORTLET-->
			<div class="portlet light ">
				<div class="portlet-title tabbable-line">
					<div class="caption">
						<i class="icon-globe font-dark hide"></i> <span
							class="caption-subject font-dark bold uppercase">我的任务</span>
					</div>
					<ul class="nav nav-tabs" id="test">
						<li class="active"><a href="#tab_1_1" class="active"
							data-toggle="tab"> 待办<dballquantity/></a></li>
						<li><a href="#tab_1_2" data-toggle="tab" ng-click="findEndTask()"> 已办 <ybQuantity/></a></li>
					</ul>
				</div>
				<div class="portlet-body" style="height:665px">
					<!--BEGIN TABS-->
					<div class="tab-content">
						<div class="tab-pane active" id="tab_1_1">
							<div class="scroller" style="height: 610px;" data-always-visible="1" data-rail-visible="0">
								<ul class="feeds">										
									<div ng-repeat="dbItem in dbItems">
								         <div compile="dbItem.template"></div>         
								     </div>
								     <div ng-repeat="message in unReadMessageList track by message.serialNum">
										<li style='background-color:#fff'>
											<div class="col-md-7">
												<a ng-click="messageView(message.serialNum,message.objectSerial,message.actionName)">
												<span>{{delHtmlTag(message.context)}}</span></a>
											</div>
											<div class="col-md-5">{{message.createTime | date:'yyyy-MM-dd  HH:mm:ss'}}</div></li>
									</div>
								</ul>
								
							</div>
								
						</div>
						<div class="tab-pane" id="tab_1_2">
							<div class="scroller" style="height: 620px;" data-always-visible="1" data-rail-visible="0">
								<ul class="feeds">									
									 <div ng-repeat="ybItem in ybItems  track by $index">
								         <div ng-if="displayYbItemCount>$index" compile="ybItem.template"></div>         
								     </div>		
								      	
								     <div ng-if="ybItems.length>displayYbItemCount" ng-click="addDisplayYbItemCount()" style="text-align: center;margin-top: 10px;"> 
								     	<div class="col-md-12"><a ><span>点击加载更多</span></a></div>
								      </div>					
								</ul>
							</div>
						</div>
					</div>
					<!--END TABS-->
				</div>
			</div>
			<!-- END PORTLET-->
		</div>
		<div class="col-md-6 col-sm-5">
			<div class="portlet light ">
				<div class="portlet-title">
					<div class="caption caption-md">
						<i class="icon-bar-chart font-dark hide"></i> <span
							class="caption-subject font-dark bold uppercase">公告
							</span> <span class="caption-helper"><span class="badge badge-danger" style="position: relative;top: -10px;"> {{noticeCount}} </span></span><!-- 显示公告条数 -->
					</div>
				</div>
				<div class="scroller" style="height:238px">
					<div
							ng-if="noticeList == null || noticeList.length == 0"
							align="center">暂无公告</div>
						<div  
							ng-repeat="notice in noticeList track by notice.serialNum">
							<div class="col-md-12">
								<ul class="media-list">
									<li class="media" style="border-bottom: 1px #F2F2F2 solid;">
										<a class="pull-left" href="javascript:;"> <img
											class="todo-userpic" src="assets/pages/img/avatars/team1.jpg"
											width="27px" height="27px">
									</a>
										<div class="media-body todo-comment">
											<p class="todo-comment-p">
												<font ng-if="notice.readFlg==null"  style="color: #f36a5a;">未读</font> <font
													ng-if="notice.readFlg==1">已读</font>
											</p>
											<p class="todo-comment-head">
												<span class="todo-comment-username">{{notice.updater}}</span>
												&nbsp; <span class="todo-comment-date">{{notice.relaseDate}}</span>
											</p>
											<p class="todo-text-color">
												<font style="font-weight: bolder;">{{notice.noticeTitle}}:
												</font> {{delHtmlTag(notice.context)}} &nbsp;
												<a href="javascript:;"
													ng-click="myNoticeView(notice.serialNum)">查看详情</a>&nbsp;&nbsp;
												<a
													href="javascript:;"
													ng-click="deleteMyNotice(notice.serialNum)">点击删除</a> <br>
											</p>
										</div>
									</li>
								</ul>
							</div>
						</div>
				</div>
			</div>
		</div>
		<div  class="col-md-6 col-sm-5"   style="margin-top: -10px;float:right;">
			<div class="portlet light " >
					<div class="portlet-title">
					<div class="caption caption-md">
						<i class="icon-bar-chart font-dark hide"></i> <span
							class="caption-subject font-dark bold uppercase">业务消息
							</span> <span class="caption-helper"><span class="badge badge-danger" style="position: relative;top: -10px;"> {{businessMessageSize}} </span></span><!-- 显示公告条数 -->
					</div>
				</div>
				<div class="portlet-body" style="height:350px">
				<div  class="scroller" style="height:330px"><!-- class="portlet-body"  -->
						<div ng-if="messageList==null||messageList.length==0" class="row todo-container">
							<div class="todo-tasks-container" align="center" style="padding: 0px 20px;border:0px solid #ebf0f5;">
									暂无消息
							</div>
						</div>
						<div class="row todo-container">
							<div class="todo-tasks-container" style="padding: 0px 20px;border:0px solid #ebf0f5;">
								<ul class="todo-tasks-content">
									<li class="todo-tasks-item" style="padding: 0px 0 0 0;" ng-repeat="message in messageList track by message.serialNum">
										<h4 class="todo-inline" style="font-size: 15px;font-weight: 500;">
											<p><i class="glyphicon glyphicon-envelope"></i>&nbsp;&nbsp;{{delHtmlTag(message.context)}}&nbsp;<a ng-click="messageView(message.serialNum,message.objectSerial,message.actionName)" >查看</a>
											<font ng-if="message.readFlg=='1'" style="color: gray;"  ><a ng-click="changeReadFlg(message.serialNum)"       id="{{message.serialNum}}">已读</a></font>
											<font ng-if="message.readFlg=='0'" style="color: #f36a5a;" ><a ng-click="changeReadFlg(message.serialNum)"      style="color: #f36a5a;"  id="{{message.serialNum}}" >未读</a></font>
											<font class="date">{{message.createTime | date:'yyyy-MM-dd  HH:mm:ss'}}</font>
											</p>
										</h4>
										
									</li>
								</ul>
							</div>
						</div>
<!-- 						<div class="row"
							ng-if="messageList == null || messageList.length == 0"
							align="center">暂无消息</div>
						<div class="row ">
							<div class="todo-tasks-container" style="padding: 0px 20px;border:0px solid #ebf0f5;">
								<ul class="todo-tasks-content">
									<li class="todo-tasks-item" style="padding: 20px 0 0 0;" ng-repeat="message in messageList track by message.serialNum">
										<h4 class="todo-inline" style="font-size: 15px;font-weight: 500;">
											<p><i class="glyphicon glyphicon-envelope"></i>&nbsp;&nbsp;{{delHtmlTag(message.context)}}&nbsp;<a ng-click="messageView(message.serialNum,message.objectSerial,message.actionName)" >查看</a></p>
										</h4>
										<p class="todo-inline todo-float-r">
											<font ng-if="message.readFlg=='1'">已读</font>
											<font ng-if="message.readFlg=='0'">未读</font>
										</p>
									</li>
								</ul>
							</div>
						</div> -->
						<div class="portlet-body dataTables_wrapper" id="businessMessage">
							<div class="row">
								<div class="col-md-12">
									<div class="col-md-5 col-sm-5">
										<div class="dataTables_info" id="businessMessage_info" role="status"></div>
									</div>
									<div class="col-md-7 col-sm-7">
										<div class="dataTables_paginate paging_bootstrap_full_number">
											<ul id="businessMessage_paginator"></ul>
										</div>
									</div>
								</div>
							</div>
						</div>
				</div>
				</div>
			</div>
			</div>
	</div>
	<!-- <div class="row"> -->
	<!-- 	<div >
			<div class="portlet light ">
				<div class="portlet-title tabbable-line">
					<div class="caption">
						<i class="icon-globe font-dark hide"></i> <span
							class="caption-subject font-dark bold uppercase">消息</span>
					</div>
					<ul class="nav nav-tabs" id="test">
						<li class="active"><a href="#tab_1_sys" class="active"
							data-toggle="tab"> 系统消息<systemMessage/></a></li>
						<li  class="active"><a  class="active" href="#tab_1_bus" data-toggle="tab">业务消息 <businessMessage/></a></li>
					</ul>
				</div>
				<div class="portlet-body" style="height:300px">
					BEGIN TABS
					<div class="tab-content">
						<div class="tab-pane active" id="tab_1_sys">
							<div class="scroller" style="height: 129px;" data-always-visible="1" data-rail-visible="0">
								<ul class="feeds">										
									<div ng-repeat="sysmessage in sysmessages">
								         <div compile="sysmessage.template"></div>         
								     </div>
								</ul>
								
							</div>
								
						</div>
						<div class="tab-pane" id="tab_1_bus">
							<div class="scroller" style="height: 129px;" data-always-visible="1" data-rail-visible="0">
								<ul class="feeds">									
									 <div ng-repeat="busmessage in busmessages">
								         <div compile="busmessage.template"></div>         
								     </div>							
								</ul>
							</div>
						</div>
					</div>
					END TABS
				</div>
			</div>
			
		</div> -->
	<!-- </div> -->