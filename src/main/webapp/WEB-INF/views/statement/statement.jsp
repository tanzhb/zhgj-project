<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!-- BEGIN PAGE HEADER-->
<!-- <h3 class="page-title"> 对账单
    <small></small>
</h3> -->
<!-- <div class="page-bar">

    <ul class="page-breadcrumb">
        <li>
            <i class="fa fa-home"></i>
            <a ui-sref="dashboard">首页</a>
            <i class="fa fa-angle-right"></i>
        </li>
        <li>
            <a ui-sref="statement" id="tip">供应商对账单</a>
        </li>
        
    </ul>
</div> -->

<!-- END PAGE HEADER-->
<!-- BEGIN MAIN CONTENT -->
<div class="tabbable-line">
    <ul class="nav nav-tabs" id="statement_tab">
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
				        <div class="portlet light">
				            <div class="portlet-title">
							<div class="caption">
								<i class="fa fa-globe font-green"></i>
								<span class="caption-subject font-green bold uppercase">供应商对账单</span>
							</div>
							<div class="actions">
								<div class="btn-group btn-group-devided" data-toggle="buttons">
								<shiro:hasPermission name="statementSupply:add">
									<label class="btn btn-transparent green btn-circle btn-sm" ui-sref="addSupplyStatement">
				                                              <i class="fa fa-plus"></i> 添加</label>
				                                              </shiro:hasPermission>
				                                              <shiro:hasPermission name="statementSupply:delete">
									<label class="btn btn-transparent red btn-circle btn-sm" ng-click="deleteSupplyStatement()">
				                                              <i class="fa fa-minus"></i> 删除</label>
				                                              </shiro:hasPermission>
									<label class="btn btn-transparent yellow-casablanca btn-outline btn-circle btn-sm" ng-click="exportStatement('supply')">
				                                              <i class="fa fa-file-excel-o"></i> 导出</label>
				                </div>
							</div>
				            </div>
				            <div class="portlet-body">
				                <table class="table table-striped table-bordered table-hover table-checkable order-column" id="sample_1">
				                    <thead>
				                        <tr>
				                            <th style="text-align: center">
					                             <label class="mt-checkbox mt-checkbox-single mt-checkbox-outline">
				                                     <input type="checkbox" class="group-checkable" data-set="#sample_1 .checkboxes" />
				                                     <span></span>
				                                 </label>
				                            </th>
				                            <th> 对账单号 </th>
				                            <th> 对账日期 </th>
				                            <th> 供应商 </th>
				                            <th> 期初应付</th>
				                            <th> 本期应付 </th>
				                            <th> 本期付款 </th>
				                            <th> 期末应付</th>
				                            <th> 超期款</th>
				                            <th> 本期扣款</th>
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
				        <div class="portlet light">
				            <div class="portlet-title">
							<div class="caption">
								<i class="fa fa-globe font-green"></i>
								<span class="caption-subject font-green bold uppercase">客户对账单</span>
							</div>
							<div class="actions">
								<div class="btn-group btn-group-devided" data-toggle="buttons">
								<shiro:hasPermission name="statementBuy:add">
									<label class="btn btn-transparent green btn-circle btn-sm" ui-sref="addBuyStatement">
				                                              <i class="fa fa-plus"></i> 添加</label>
				                                              </shiro:hasPermission>
				                                              <shiro:hasPermission name="statementBuy:delete">
									<label class="btn btn-transparent red btn-circle btn-sm" ng-click="deleteBuyStatement()">
				                                              <i class="fa fa-minus"></i> 删除</label>
				                                              </shiro:hasPermission>
									<label class="btn btn-transparent yellow-casablanca btn-outline btn-circle btn-sm" ng-click="exportStatement('buy')">
				                                              <i class="fa fa-file-excel-o"></i> 导出</label>
				                </div>
							</div>
				            </div>
				            <div class="portlet-body">
				                <table class="table table-striped table-bordered table-hover table-checkable order-column" id="sample_2">
				                    <thead>
				                        <tr>
				                            <th style="text-align: center">
				                            	 <label class="mt-checkbox mt-checkbox-single mt-checkbox-outline">
				                                     <input type="checkbox" class="group-checkable" data-set="#sample_2 .checkboxes" />
				                                     <span></span>
				                                 </label>
				                            </th>
				                            <th> 对账单号 </th>
				                            <th> 对账日期 </th>
				                            <th> 客户  </th>
				                            <th> 期初应付</th>
				                            <th> 本期应付 </th>
				                            <th> 本期付款 </th>
				                            <th> 期末应付</th>
				                            <th> 超期款</th>
				                            <th> 本期扣款 </th>
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

