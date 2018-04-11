<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<head>
	<meta http-equiv="Content-Type" content="multipart/form-data; charset=utf-8" />
</head>
<!-- BEGIN PAGE HEADER-->
<!-- <h3 class="page-title"> 发票
</h3> -->
<!-- <div class="page-bar">
    <ul class="page-breadcrumb">
        <li>
            <i class="fa fa-home"></i>
            <a ui-sref="dashboard">首页</a>
            <i class="fa fa-angle-right"></i>
        </li>
        <li>
            <a ui-sref="invoice">发票</a>
        </li>
    </ul>
</div> -->
<!-- END PAGE HEADER-->
<!-- BEGIN MAIN CONTENT -->
<div class="tabbable-line">
    <ul class="nav nav-tabs">
    <shiro:hasPermission name="zhgj:incomeBill:*">
        <li  id="in" >
            <a data-target="#tab_in" data-toggle="tab"  ng-click="showOut('showin')">进项票</a>
        </li>
        </shiro:hasPermission>
        <shiro:hasPermission name="zhgj:ticket:*">
        <li  id="out">
            <a data-target="#tab_out" data-toggle="tab"   ng-click="showOut('showout')">销项票</a>
        </li>
        </shiro:hasPermission>
    </ul>
    <div class="tab-content">
    	<!-- 进项票列表---START -->
        <div class="tab-pane active" id="tab_in">
        <!-- BEGIN EXAMPLE TABLE PORTLET-->
		<div class="portlet light">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe font-green"></i>
					<span class="caption-subject font-green bold uppercase">进项票列表</span>
				</div>
				<div class="actions">
				<div class="btn-group btn-group-devided" data-toggle="buttons">
				
				<label class="btn btn-transparent green btn-circle btn-sm" ng-click="confirmInvoice('in')">
	                                              <i class="fa fa-plus"></i> 确认</label>
				<shiro:hasPermission name="incomeBill:add">
						<label class="btn btn-transparent green btn-circle btn-sm" ng-click="addInvoice('in')">
	                                              <i class="fa fa-plus"></i> 添加</label>
	                          </shiro:hasPermission>                    
	                                              <shiro:hasPermission name="incomeBill:edit">
						<label class="btn btn-transparent purple btn-circle btn-sm" ng-click="toEditInvoicePage('in')">
	                                              <i class="fa fa-edit"></i> 修改</label>
	                                              </shiro:hasPermission>
	                                              <shiro:hasPermission name="incomeBill:delete">
						<label class="btn btn-transparent red btn-circle btn-sm" ng-click="delInvoice('in')">
	                                              <i class="fa fa-minus"></i> 删除</label>
	                                              </shiro:hasPermission>
	                                            <%--   <shiro:hasPermission name="incomeBill:import">
						<label class="btn btn-transparent green btn-outline btn-circle btn-sm" data-toggle="modal" data-target="#import" >
	                                              <i class="fa fa-upload"></i> 导入</label>
	                                              </shiro:hasPermission> --%>
	                                              <shiro:hasPermission name="incomeBill:export">
						<label class="btn btn-transparent yellow-casablanca btn-outline btn-circle btn-sm" ng-click="exportInvoice('in')">
	                                              <i class="fa fa-file-excel-o"></i> 导出</label>
	                                              </shiro:hasPermission>
	                 </div>
					<!-- <button ng-click="addInvoice('in')"
						data-toggle="modal" class="btn btn-default btn-sm btn-circle">
						<i class="fa fa-plus"></i> 添加
					</button><button ng-click="toEditInvoicePage('in')"
						class="btn btn-default btn-sm btn-circle"> <i
						class="fa fa-edit"></i> 修改
					</button> <button ng-click="delInvoice('in')" 
						data-toggle="modal" 
						class="btn btn-default btn-sm btn-circle"> <i
						class="fa fa-minus"></i> 删除
					</button>
					<div class="btn-group">
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
													class="tool-action" ng-click="exportInvoice('in')"> <i class="fa fa-file-excel-o"></i> 导出
												</a></li>
												<li><a href="javascript:;" data-action="2"
													class="tool-action" > <i class="fa fa-print"></i> 打印
												</a></li> 
											</ul>
					</div> -->
				</div>
			</div>

			<!-- 删除进项票modal 开始 -->
			<div id="delInvoiceinModal" class="modal fade" tabindex=""

				data-backdrop="static" data-keyboard="false">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true"></button>
							<h4 class="modal-title">确认</h4>
						</div>
						<div class="modal-body">
							<p>是否删除已选进项票?</p>
						</div>
						<div class="modal-footer">
							<button type="button" data-dismiss="modal"
								class="btn dark btn-outline">取消</button>
							<button type="button" ng-click="confirmDelInVoice('in')" class="btn green">确定
								</button>
						</div>
					</div>
				</div>
			</div>
			<!-- 删除进项票modal 结束 -->
			
			<div class="portlet-body">
				<table
					class="table table-striped table-bordered table-hover table-checkable order-column  "
					id="sample_in">
					<thead>
						<tr>
							<!-- <th style="text-align: center"><input name="select_all"
								value="1" id="example-select-in-all" type="checkbox" /></th> -->
								<th>
			                  <label class="mt-checkbox mt-checkbox-single mt-checkbox-outline">
			                      <input  type="checkbox" class="group-checkable" data-set="#sample_in .checkboxes" />
			                         <span></span>
			                          </label>
			                                    </th>
							<th>发票编号 </th>
                            <th> 发票类型</th>
                            <th>关联采购单号</th>
                            <th>开票方</th>
                            <th>发票金额 </th>
                            <!-- <th> 发票数量</th> -->
                            <th> 发票号码</th>
                            <th>开票日期</th>
                            <!-- <th> 付款日期 </th> -->
                            <th> 提交人 </th>
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
        <!-- 进项票列表---END -->
        
        <!-- 出库检验列表---START -->
        <div class="tab-pane" id="tab_out">
        <!-- BEGIN EXAMPLE TABLE PORTLET-->
       <!--   
		<div class="portlet light">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe font-green"></i>
					<span class="caption-subject font-green bold uppercase">销项票列表</span>
				</div> -->
				  <div class="portlet-body">
			<div class="tabbable-custom ">
			<ul class="nav nav-tabs " id="invoiceOutTab">
				<li class="active"><a href="#applyOutInvoice" data-toggle="tab"
					ng-click="toInvoiceApply('out')"> 销项票 </a></li>
				<li><a href="#daibanOutInvoice" data-toggle="tab" ng-click="toDaibanInvoice('out')">
						待办 <dbQuantity/> </a></li>
				<li><a href="#yibanOutInvoice" data-toggle="tab" ng-click="toYibanInvoice('out')">
						已办  <ybQuantity/></a></li>
			</ul>
         <!-- BEGIN EXAMPLE TABLE PORTLET-->
          <div class="tab-content">
				<div class="tab-pane active" id="applyOutInvoice">
		<div class="portlet light">
			<div class="portlet-title">
				<!-- <div class="caption">
					<i class="fa fa-globe font-green"></i>
					<span class="caption-subject font-green bold uppercase">销售价格列表</span>
				</div> -->
				<div class="actions">
				<div class="btn-group btn-group-devided" data-toggle="buttons">
		
				<label class="btn btn-transparent green btn-circle btn-sm" ng-click="confirmInvoice('out')">
	                                              <i class="fa fa-plus"></i> 确认</label>
	
				 
				 <label class="btn btn-transparent yellow btn-circle btn-sm"
										ng-click="submitInvoiceApply('out')"> <i class="glyphicon glyphicon-play"></i> 申请</label>
										
				 <shiro:hasPermission name="ticket:add">
						<label class="btn btn-transparent green btn-circle btn-sm" ng-click="addInvoice('out')">
	                                              <i class="fa fa-plus"></i> 添加</label>
	                                              </shiro:hasPermission>
	                                               <shiro:hasPermission name="ticket:edit">
						<label class="btn btn-transparent purple btn-circle btn-sm" ng-click="toEditInvoicePage('out')">
	                                              <i class="fa fa-edit"></i> 修改</label>
	                                              </shiro:hasPermission>
	                                               <shiro:hasPermission name="ticket:delete">
						<label class="btn btn-transparent red btn-circle btn-sm" ng-click="delInvoice('out')" >
	                                              <i class="fa fa-minus"></i> 删除</label>
	                                              </shiro:hasPermission>
	                                               <shiro:hasPermission name="ticket:import">
						<label class="btn btn-transparent green btn-outline btn-circle btn-sm" data-toggle="modal" data-target="#import" >
	                                              <i class="fa fa-upload"></i> 导入</label>
	                                              </shiro:hasPermission>
	                                               <shiro:hasPermission name="ticket:export">
						<label class="btn btn-transparent yellow-casablanca btn-outline btn-circle btn-sm" ng-click="exportInvoice('out')">
	                                              <i class="fa fa-file-excel-o"></i> 导出</label>
	                                              </shiro:hasPermission>
	                 </div>
				</div>
			</div>

			<!-- 删除销项票modal 开始 -->
			<div id="delInvoiceoutModal" class="modal fade" tabindex=""

				data-backdrop="static" data-keyboard="false">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true"></button>
							<h4 class="modal-title">确认</h4>
						</div>
						<div class="modal-body">
							<p>是否删除已选销项票?</p>
						</div>
						<div class="modal-footer">
							<button type="button" data-dismiss="modal"
								class="btn dark btn-outline">取消</button>
							<button type="button" ng-click="confirmDelInVoice('out')" class="btn green">确定
								</button>
						</div>
					</div>
				</div>
			</div>
			<!-- 删除销项票modal 结束 -->

			<div class="portlet-body">
				<table
					class="table table-striped table-bordered table-hover table-checkable order-column "
					id="sample_out">
					<thead>
						<tr>
							<!-- <th style="text-align: center"><input name="select_all"
								value="1" id="example-select-out-all" type="checkbox" /></th> -->
								<th>
			                  <label class="mt-checkbox mt-checkbox-single mt-checkbox-outline">
			                      <input  type="checkbox" class="group-checkable" data-set="#sample_out .checkboxes" />
			                         <span></span>
			                          </label>
			                                    </th>
							<th>发票编号 </th>
                            <th> 发票类型</th>
                            <th>关联销售单号</th>
                            <th>收票方</th>
                            <th>发票金额 </th>
                            <!-- <th> 发票数量</th> -->
                            <th> 发票号码</th>
                            <th>开票日期</th>
                          <!--   <th> 收款日期 </th> -->
                            <th> 提交人 </th>
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
        <!-- 销项票列表---END -->
             <div class="tab-pane" id="daibanOutInvoice">
							<!-- BEGIN EXAMPLE TABLE PORTLET-->
							<div class="portlet box ">
								<div class="portlet-title"></div>
	
								<div class="portlet-body">
									<table class="table table-striped table-bordered table-hover table-checkable order-column"
										id="dbOutInvoiceTable">
										<thead>
											<tr>
											<th>
	                                            <label class="mt-checkbox mt-checkbox-single mt-checkbox-outline">
	                                                <input type="checkbox" class="group-checkable" data-set="#dbOutInvoiceTable .checkboxes" />
	                                                <span></span>
	                                            </label>
	                                        </th>
											<th style="white-space: nowrap;">任务状态</th>
											<th style="white-space: nowrap;">申请人</th>
											<th style="white-space: nowrap;">标题</th>
											<th style="white-space: nowrap;">当前节点</th>
											<th style="white-space: nowrap;">负责人</th>
											<th style="white-space: nowrap;">任务创建时间</th>
											<th style="white-space: nowrap;">流程状态</th>
											</tr>
										</thead>
										<tbody>
	
										</tbody>
									</table>
								</div>
	
	
							</div>
							<!-- END EXAMPLE TABLE PORTLET-->
				</div>
				<div class="tab-pane" id="yibanOutInvoice">
							<div class="portlet box">
								<div class="portlet-title"></div>
	
								<div class="portlet-body">
									<table class="table table-striped table-bordered table-hover"
										id="endTaskOutInvoiceTable">
										<thead>
											<tr>
												<th>单据类型</th>
												<th>申请人</th>
												<th>标题</th>
												<th>任务开始时间</th>
												<th>任务签收时间</th>
												<th>任务结束时间 </th>
												<th>流程结束原因</th>
												<th>流程版本号</th>
												<th>操作</th>
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
 </div>
</div>
 </div>
</div>


<!-- END MAIN CONTENT -->
<!-- BEGIN MAIN JS -->

<!-- END MAIN JS -->
