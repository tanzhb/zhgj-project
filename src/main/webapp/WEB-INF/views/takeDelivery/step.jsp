<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"  %>

<div class="row">
	<div class="col-md-12">
		<!-- <div class="portlet light portlet-fit bordered">
			<div class="portlet-body"> -->
				<div class="mt-element-step">
					<div class="row step-line">
						<div class="col-md-3 mt-step-col first done">
							<div class="mt-step-number bg-white">
								<i class="fa fa-bank"></i>
							</div>
							<div class="mt-step-title uppercase font-grey-cascade">收货</div>
							<div class="mt-step-content font-grey-cascade" ng-if="takeDeliver.status==0" >待收货</div>
							<div class="mt-step-content font-grey-cascade" ng-if="takeDeliver.status.length>1" >审核中</div>
							<div class="mt-step-content font-grey-cascade" ng-if="takeDeliver.status==1||takeDeliver.status>2" >已完成</div>
							<div class="mt-step-content font-grey-cascade" ng-if="takeDeliver.status==2" >已取消</div>
						</div>
						<div  ng-class="{'col-md-3 mt-step-col done':(takeDeliver.status.length==1&&takeDeliver.status>0&&takeDeliver.status!=2),'col-md-3 mt-step-col':(takeDeliver.status.length>1||takeDeliver.status<1||takeDeliver.status==2)}">
							<div class="mt-step-number bg-white">
								<i class="fa fa-eye"></i>
							</div>
							<div class="mt-step-title uppercase font-grey-cascade">检验</div>
							<div class="mt-step-content font-grey-cascade" ng-if="takeDeliver.status==1">待检验</div>
							<div class="mt-step-content font-grey-cascade" ng-if="takeDeliver.status>3">已完成</div>
						</div>
						<div ng-class="{'col-md-3 mt-step-col done':(takeDeliver.status.length==1&&takeDeliver.status>2),'col-md-3 mt-step-col':(takeDeliver.status.length>1||takeDeliver.status<=3)}">
							<div class="mt-step-number bg-white">
								<i class="fa fa-arrow-down"></i>
							</div>
							<div class="mt-step-title uppercase font-grey-cascade">入库</div>
							<div class="mt-step-content font-grey-cascade" ng-if="takeDeliver.status==3">待入库</div>
							<div class="mt-step-content font-grey-cascade" ng-if="takeDeliver.status==4">已完成</div>
						</div>
						<div ng-class="{'col-md-3 mt-step-col last done':(takeDeliver.status.length==1&&takeDeliver.status>3),'col-md-3 mt-step-col last':(takeDeliver.status.length>1||takeDeliver.status<4)}">
							<div class="mt-step-number bg-white">
								<i class="fa fa-check"></i>
							</div>
							<div class="mt-step-title uppercase font-grey-cascade">完成</div>
							<div class="mt-step-content font-grey-cascade"></div>
						</div>
					</div>
				</div>
		<!-- 	</div>
		</div> -->
	</div>
</div>