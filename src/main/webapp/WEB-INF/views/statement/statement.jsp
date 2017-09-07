<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- BEGIN PAGE HEADER-->
<h3 class="page-title"> 销售对账单
    <small></small>
</h3>
<div class="page-bar">

    <ul class="page-breadcrumb">
        <li>
            <i class="fa fa-home"></i>
            <a ui-sref="dashboard">首页</a>
            <i class="fa fa-angle-right"></i>
        </li>
        <li>
            <a ui-sref="statement">对账单</a>
            <i class="fa fa-angle-right"></i>
        </li>
        
    </ul>
</div>

<!-- END PAGE HEADER-->
<!-- BEGIN MAIN CONTENT -->
<div class="tabbable-line">
    <ul class="nav nav-tabs">
        <li class="active">
            <a data-target="#tab_15_1" data-toggle="tab">供应商对账单</a>
        </li>
        <li>
            <a data-target="#tab_15_2" data-toggle="tab">客户对账单</a>
        </li>
    </ul>
    <div class="tab-content">
    	<!-- 供应商对账单列表---START -->
        <div class="tab-pane active" id="tab_15_1">
        	<div class="row">
				<div class="col-md-12">
				        <div class="portlet box red">
				            <div class="portlet-title">
							<div class="caption">
								<i class="fa fa-globe"></i>供应商对账单
							</div>
							<div class="actions">
								<!-- <a href="javascript:;" ui-sref="addStatement"
									 class="btn btn-default btn-sm btn-circle">
									<i class="fa fa-plus"></i> 添加
								</a> 
								<a href="javascript:;" ng-click="editStatement()"
									 class="btn btn-default btn-sm btn-circle">
									<i class="fa fa-edit"></i> 修改
								</a> -->
								<a href="javascript:;" ng-click="deleteSupplyStatement()"
									class="btn btn-default btn-sm btn-circle"> <i
									class="fa fa-minus"></i> 删除
								</a>
								<!-- <div class="btn-group">
									<a class="btn btn-default btn-outline btn-circle"
										href="javascript:;" data-toggle="dropdown"> <i
										class="fa fa-share"></i> <span class="hidden-xs"> 其它 </span> <i
										class="fa fa-angle-down"></i>
									</a>
									<ul class="dropdown-menu pull-right" id="sample_3_tools">
										<li><a data-action="0"
											class="tool-action" data-toggle="modal" data-target="#import"> <i class="fa fa-upload"></i> 导入
										</a></li> 
										<li><a href="javascript:;" data-action="1"
											class="tool-action" ng-click="exportStatement()"> <i class="fa fa-file-excel-o"></i> 导出
										</a></li>
									</ul>
								</div> -->
							</div>
				            </div>
				            <div class="portlet-body">
				                <table class="table table-striped table-bordered table-hover table-checkable order-column" id="sample_1">
				                    <thead>
				                        <tr>
				                            <th style="text-align: center"><input name="select_all" id="example-select-supply" type="checkbox"/></th>
				                            <th> 对账单号 </th>
				                            <th> 对账日期 </th>
				                            <th> 供应商 </th>
				                            <th> 合计金额 </th>
				                            <th> 客户提货金额 </th>
				                            <th> 付款金额 </th>
				                            <th> 服务费 </th>
				                            <th> 实际收款日期 </th>
				                            <th> 状态 </th>
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
        <!-- 供应商对账单列表---end -->
        
        <!-- 客户对账单列表---START -->
        <div class="tab-pane" id="tab_15_2">
        	<div class="row">
				<div class="col-md-12">
				        <div class="portlet box red">
				            <div class="portlet-title">
							<div class="caption">
								<i class="fa fa-globe"></i>客户对账单
							</div>
							<div class="actions">
								<!-- <a href="javascript:;" ui-sref="addStatement"
									 class="btn btn-default btn-sm btn-circle">
									<i class="fa fa-plus"></i> 添加
								</a> 
								<a href="javascript:;" ng-click="editStatement()"
									 class="btn btn-default btn-sm btn-circle">
									<i class="fa fa-edit"></i> 修改
								</a> -->
								<a href="javascript:;" ng-click="deleteBuyStatement()"
									class="btn btn-default btn-sm btn-circle"> <i
									class="fa fa-minus"></i> 删除
								</a>
								<!-- <div class="btn-group">
									<a class="btn btn-default btn-outline btn-circle"
										href="javascript:;" data-toggle="dropdown"> <i
										class="fa fa-share"></i> <span class="hidden-xs"> 其它 </span> <i
										class="fa fa-angle-down"></i>
									</a>
									<ul class="dropdown-menu pull-right" id="sample_3_tools">
										<li><a data-action="0"
											class="tool-action" data-toggle="modal" data-target="#import"> <i class="fa fa-upload"></i> 导入
										</a></li> 
										<li><a href="javascript:;" data-action="1"
											class="tool-action" ng-click="exportStatement()"> <i class="fa fa-file-excel-o"></i> 导出
										</a></li>
									</ul>
								</div> -->
							</div>
				            </div>
				            <div class="portlet-body">
				                <table class="table table-striped table-bordered table-hover table-checkable order-column" id="sample_2">
				                    <thead>
				                        <tr>
				                            <th style="text-align: center"><input name="select_all" id="example-select-buy" type="checkbox"/></th>
				                            <th> 对账单号 </th>
				                            <th> 对账日期 </th>
				                            <th> 客户 </th>
				                            <th> 合计金额 </th>
				                            <th> 客户提货金额 </th>
				                            <th> 付款金额 </th>
				                            <th> 服务费 </th>
				                            <th> 实际收款日期 </th>
				                            <th> 状态 </th>
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
        <!-- 客户对账单列表---end -->
    </div>


 
 <!-- 删除对账单modal 开始 -->
	<div id="delStatementModal" class="modal fade" tabindex="-1"
		data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true"></button>
					<h4 class="modal-title">确认</h4>
				</div>
				<div class="modal-body">
					<p>是否删除已选对账单?</p>
				</div>
				<div class="modal-footer">
					<button type="button" data-dismiss="modal"
						class="btn dark btn-outline">取消</button>
					<button type="button" ng-click="del()" class="btn green">确定
						</button>
				</div>
			</div>
		</div>
	</div>
<!-- 删除对账单modal 结束 -->
