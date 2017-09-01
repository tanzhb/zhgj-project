<%@ page language="java" pageEncoding="utf-8"%>

	<div class="row">
		<div class="col-md-8">
			<!-- Begin: life time stats -->
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
			<!-- End: life time stats -->
		</div>
		<div class="col-md-4">
			<!-- Begin: life time stats -->
			<!-- BEGIN PORTLET-->
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
			<!-- End: life time stats -->
		</div>
	</div>
	<div class="row">
		<div class="col-md-8 col-sm-8">
			<!-- BEGIN PORTLET-->
			<div class="portlet light ">
				<div class="portlet-title tabbable-line">
					<div class="caption">
						<i class="icon-globe font-dark hide"></i> <span
							class="caption-subject font-dark bold uppercase">流程</span>
					</div>
					<ul class="nav nav-tabs" id="test">
						<li class="active"><a href="#tab_1_1" class="active"
							data-toggle="tab"> 待办 </a></li>
						<li><a href="#tab_1_2" data-toggle="tab"> 已办 </a></li>
					</ul>
				</div>
				<div class="portlet-body" style="height:150px">
					<!--BEGIN TABS-->
					<div class="tab-content">
						<div class="tab-pane active" id="tab_1_1">
							<div class="scroller" style="height: 129px;" data-always-visible="1" data-rail-visible="0">
								<ul class="feeds" id="daiban">										
									
								</ul>
								
							</div>
								
						</div>
						<div class="tab-pane" id="tab_1_2">
							<div class="scroller" style="height: 129px;" data-always-visible="1" data-rail-visible="0">
								<ul class="feeds" id="yiban">									
																		
								</ul>
							</div>
						</div>
					</div>
					<!--END TABS-->
				</div>
			</div>
			<!-- END PORTLET-->
		</div>
		<div class="col-md-4 col-sm-4">
			<div class="portlet light ">
				<div class="portlet-title">
					<div class="caption caption-md">
						<i class="icon-bar-chart font-dark hide"></i> <span
							class="caption-subject font-dark bold uppercase">Customer
							Support</span> <span class="caption-helper">45 pending</span>
					</div>
				</div>
				<div class="portlet-body" style="height:150px">
					
				</div>
			</div>
		</div>
	</div>