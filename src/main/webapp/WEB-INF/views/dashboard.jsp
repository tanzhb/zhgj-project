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
		<div class="col-md-8 col-sm-7"  >
			<!-- BEGIN PORTLET-->
			<div class="portlet light ">
				<div class="portlet-title tabbable-line">
					<div class="caption">
						<i class="icon-globe font-dark hide"></i> <span
							class="caption-subject font-dark bold uppercase">流程</span>
					</div>
					<ul class="nav nav-tabs" id="test">
						<li class="active"><a href="#tab_1_1" class="active"
							data-toggle="tab"> 待办<dbQuantity/></a></li>
						<li><a href="#tab_1_2" data-toggle="tab"> 已办 <ybQuantity/></a></li>
					</ul>
				</div>
				<div class="portlet-body" style="height:300px">
					<!--BEGIN TABS-->
					<div class="tab-content">
						<div class="tab-pane active" id="tab_1_1">
							<div class="scroller" style="height: 129px;" data-always-visible="1" data-rail-visible="0">
								<ul class="feeds">										
									<div ng-repeat="dbItem in dbItems">
								         <div compile="dbItem.template"></div>         
								     </div>
								</ul>
								
							</div>
								
						</div>
						<div class="tab-pane" id="tab_1_2">
							<div class="scroller" style="height: 129px;" data-always-visible="1" data-rail-visible="0">
								<ul class="feeds">									
									 <div ng-repeat="ybItem in ybItems">
								         <div compile="ybItem.template"></div>         
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
		<div class="col-md-4 col-sm-5">
			<div class="portlet light ">
				<div class="portlet-title">
					<div class="caption caption-md">
						<i class="icon-bar-chart font-dark hide"></i> <span
							class="caption-subject font-dark bold uppercase">公告
							</span> <span class="caption-helper"><noticeCount/></span><!-- 显示公告条数 -->
					</div>
				</div>
				<div class="portlet-body" style="height:300px">
					
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-12">
			<div class="portlet light ">
				<div class="portlet-title tabbable-line">
					<div class="caption">
						<i class="icon-globe font-dark hide"></i> <span
							class="caption-subject font-dark bold uppercase">消息</span>
					</div>
					<ul class="nav nav-tabs" id="test">
						<li class="active"><a href="#tab_1_sys" class="active"
							data-toggle="tab"> 系统消息<systemMessage/></a></li>
						<li><a href="#tab_1_bus" data-toggle="tab">业务消息 <businessMessage/></a></li>
					</ul>
				</div>
				<div class="portlet-body" style="height:300px">
					<!--BEGIN TABS-->
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
					<!--END TABS-->
				</div>
			</div>
			
		</div>
	</div>