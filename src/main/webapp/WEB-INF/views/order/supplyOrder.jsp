<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!-- BEGIN PAGE HEADER-->
<!-- <h3 class="page-title"> 销售订单
    <small></small>
</h3> -->
<div class="page-bar">

    <ul class="page-breadcrumb">
        <li>
            <i class="fa fa-home"></i>
            <a ui-sref="dashboard">首页</a>
            <i class="fa fa-angle-right"></i>
        </li>
        <li>
            <a ui-sref="saleOrder">销售管理</a>
            <i class="fa fa-angle-right"></i>
        </li>
        <li>
            <a ui-sref="saleOrder">订单</a>
        </li>
    </ul>
</div>
<!-- END PAGE HEADER-->
<!-- BEGIN MAIN CONTENT -->
<div class="tabbable-line">
    <ul class="nav nav-tabs">
		<shiro:hasPermission name="zhgj:normalOrder">
			<li class="active"><a data-target="#tab_15_1" data-toggle="tab">订单</a>
			</li>
		</shiro:hasPermission>
		<shiro:hasPermission name="zhgj:frameOrder">
			<li><a data-target="" data-toggle="tab">框架合同</a></li>
		</shiro:hasPermission>
	</ul>
    <div class="tab-content">
    	<!-- 普通订单---START -->
        <div class="tab-pane active" id="tab_15_1">
<div class="row">

	<div class="col-md-12">
	        <div class="portlet light">
	            <div class="portlet-body">
	                <table class="table table-striped table-bordered table-hover table-checkable order-column" id="sample_2">
	                    <thead>
	                        <tr>
	                            <th>
                                    <label class="mt-checkbox mt-checkbox-single mt-checkbox-outline">
                                        <input type="checkbox" class="group-checkable" data-set="#sample_2 .checkboxes" />
                                        <span></span>
                                    </label>
                                </th>
	                            <th> 销售订单号 </th>
	                            <th> 采购方 </th>
	                            <th> 销售商品 </th>
	                            <th> 金额 </th>
	                            <th> 配送 </th>
	                            <th> 服务模式 </th>
	                            <th> 关联销售合同 </th>
	                            <th> 关联采购单 </th>
	                            <th> 下单日期 </th>
	                            <th> 状态 </th>
	                            <th> 操作 </th>
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
 </div>
<!-- 普通订单---end -->
        <!-- 框架订单---START -->
        <div class="tab-pane" id="tab_15_2">
        	<div class="row">

	<div class="col-md-12">
	        <div class="portlet light">
	            <div class="portlet-title">
				<div class="actions">
							</div>
	            </div>
	            <div class="portlet-body">
	                <table class="table table-striped table-bordered table-hover table-checkable order-column" id="sample_3">
	                    <thead>
	                        <tr>
	                            <th>
                                    <label class="mt-checkbox mt-checkbox-single mt-checkbox-outline">
                                        <input type="checkbox" class="group-checkable" data-set="#sample_3 .checkboxes" />
                                        <span></span>
                                    </label>
                                </th>
	                            <th> 销售订单号 </th>
	                            <th> 采购方 </th>
	                            <th> 销售商品 </th>
	                            <th> 金额 </th>
	                            <th> 配送 </th>
	                            <th> 服务模式 </th>
	                            <th> 关联销售合同 </th>
	                            <th> 关联采购单 </th>
	                            <th> 下单日期 </th>
	                            <th> 状态 </th>
	                            <th> 操作 </th>
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
 		</div>
        <!-- 框架订单---end -->
 </div>
 </div>
