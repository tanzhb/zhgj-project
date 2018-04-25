<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!-- <meta http-equiv="cache-control" content="no-cache">   -->

 <!-- BEGIN PAGE HEADER-->
<!-- <h3 class="page-title"> 库存列表
    <small> 库存管理</small>
</h3> -->
<!-- <div class="page-bar">
    <ul class="page-breadcrumb">
        <li>
            <i class="fa fa-home"></i>
            <a ui-sref="dashboard">首页</a>
            <i class="fa fa-angle-right"></i>
        </li>
        <li>
            <a ui-sref="datatablesmanaged">物流管理</a>
            <i class="fa fa-angle-right"></i>
        </li>
        <li>
            <a ui-sref="datatablesmanaged"> 库存列表</a>
        </li>
    </ul>
</div> -->

<div class="tabbable-line">
    <ul class="nav nav-tabs">
        <li  id="gyshang"  class="active">
            <a data-target="#tab_gyshang" data-toggle="tab"  >供应商库存</a>
        </li>
      
    </ul>
    <div class="tab-content">
    	<!-- 供应商库存列表---START -->
        <div class="tab-pane active" id="tab_gyshang">
        <!-- BEGIN EXAMPLE TABLE PORTLET-->
		<div class="portlet light">
			<div class="portlet-title">
				<div class="caption">
					<!-- <i class="fa fa-globe font-green"></i> -->
					<span class="caption-subject font-green bold uppercase"></span>
				</div>
				<div class="actions">
				<div class="btn-group btn-group-devided" data-toggle="buttons">
					<shiro:hasPermission name="stockSupply:add">
						<label class="btn btn-transparent green btn-circle btn-sm" ng-click="addStock('gysIn')">
	                                              <i class="fa fa-plus"></i> 添加入库</label>
	                     <label class="btn btn-transparent green btn-circle btn-sm" ng-click="addStock('gysOut')">
	                                              <i class="fa fa-plus"></i> 添加出库</label>
	                </shiro:hasPermission>
	                <shiro:hasPermission name="stockSupply:edit">
						<label class="btn btn-transparent purple btn-circle btn-sm" ng-click="toEditStockPage('gyshang')">
	                                              <i class="fa fa-edit"></i> 修改</label>
	                </shiro:hasPermission>
	                <shiro:hasPermission name="stockSupply:delete">
						<label class="btn btn-transparent red btn-circle btn-sm" ng-click="delStock('gyshang')" >
	                                              <i class="fa fa-minus"></i> 删除</label>
	                </shiro:hasPermission>
	               <%--  <shiro:hasPermission name="stock:import">
						<label class="btn btn-transparent green btn-outline btn-circle btn-sm" data-toggle="modal" data-target="#import" >
	                                              <i class="fa fa-upload"></i> 导入</label>
	                 </shiro:hasPermission> --%>
	                <shiro:hasPermission name="stockSupply:export">                             
						<label class="btn btn-transparent yellow-casablanca btn-outline btn-circle btn-sm" ng-click="exportStock('gyshang')">
	                                              <i class="fa fa-file-excel-o"></i> 导出</label>
	                 </shiro:hasPermission>
	                 </div>
				</div>
			</div>

			<!-- 删除供应商库存start -->
				<div id="delStockzijianModal" class="modal fade" tabindex=

				data-backdrop="static" data-keyboard="false">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true"></button>
							<h4 class="modal-title">确认</h4>
						</div>
						<div class="modal-body">
							<p>是否删除已选供应商库存?</p>
						</div>
						<div class="modal-footer">
							<button type="button" data-dismiss="modal"
								class="btn dark btn-outline">取消</button>
							<button type="button" ng-click="confirmDellStock('gyshang')" class="btn green">确定
								</button>
						</div>
					</div>
				</div>
			</div>
<!-- 删除供应商库存end -->
			
			<div class="portlet-body">
				<table
					class="table table-striped table-bordered table-hover table-checkable order-column"
					id="sample_gyshang">
					<thead>
					<tr>
					<th>
			                  <label class="mt-checkbox mt-checkbox-single mt-checkbox-outline">
			                      <input  type="checkbox" class="group-checkable" data-set="sample_gyshang .checkboxes" />
			                         <span></span>
			                          </label>
			                                    </th>
							<!-- <th style="text-align: center"><input name="zijian"
								value="1" id="example-select-zijian-all" type="checkbox" /></th> -->
							<th>库存编号 </th>
                            <th> 物料编号</th>
                            <th>物料名称</th>
                            <th>规格型号</th>
                            <th>所在仓库 </th>
                            <th> 库存 </th>
                            <th>最高库龄 </th>
                            <th> 预售 </th>
                            <th> 在途 </th>
                            <th> 可售 </th>
                            <th> 状态 </th>
                            <th>更新日期</th>
						</tr>
						
					</thead>
					
					<tbody>
					</tbody>
				</table>
			</div>
		</div>
		<!-- END EXAMPLE TABLE PORTLET-->
        </div>
        <!-- 自建库存列表---END -->
        
        
			
		</div>
		<!-- END EXAMPLE TABLE PORTLET-->
        </div>
        <!-- 代管库存列表---END -->
    </div>
</div>
<div class="row">
	<div class="col-md-12">
        	<div class="portlet light ">
                <div class="portlet-body">
                    <ul class="nav nav-tabs">
                    	<li class="active bold">
                            <a href="#" data-target="#tab_stockin" data-toggle="tab"> 入库记录 </a>
                        </li>
                        <li class="bold">
                            <a href="#" data-target="#tab_stockout" data-toggle="tab"> 出库记录 </a>
                        </li>
                        
                    </ul>
                    <div class="tab-content">
                    	<div class="tab-pane fade active in" id="tab_stockin">
                    	<div class="portlet-body">
				<table
					class="table table-striped table-bordered table-hover "
					id="sample_stockSupplyIn">
					<thead>
					<tr>
							<th>入库明细号</th>
                            <th> 入库类型</th>
                            <th> 关联单据</th>
                            <th>入库日期</th>
                            <th>入库数量</th>
                            <th>仓库 </th>
                            <th>备注</th>
						</tr>
						
					</thead>
					
					<tbody>
					</tbody>
				</table>
			</div>
                    	<div class="portlet-body dataTables_wrapper" >
												<div class="row">
				                                    <div class="col-md-12">
														<div class="col-md-5 col-sm-5">
															<div class="dataTables_info" id="simple_info" role="status"></div>
														</div>
														<div class="col-md-7 col-sm-7">
															<div class="dataTables_paginate paging_bootstrap_full_number">
																<ul id="simple_paginator" ></ul>
															</div>
														</div>
													</div>
												</div>
											</div>	
                    	</div>
                        <div class="tab-pane fade" id="tab_stockout">
					       <div class="portlet-body">
				<table
					class="table table-striped table-bordered table-hover "
					id="sample_stockSupplyOut">
					<thead>
					<tr>
							<th>出库明细号</th>
                            <th> 出库类型</th>
                            <th> 关联单据</th>
                            <th>出库日期</th>
                            <th>出库数量</th>
                            <th>仓库 </th>
                            <th>备注</th>
						</tr>
						
					</thead>
					
					<tbody>
					</tbody>
				</table>
			</div>
			<div class="portlet-body dataTables_wrapper" >
												<div class="row">
				                                    <div class="col-md-12">
														<div class="col-md-5 col-sm-5">
															<div class="dataTables_info" id="simple_info" role="status"></div>
														</div>
														<div class="col-md-7 col-sm-7">
															<div class="dataTables_paginate paging_bootstrap_full_number">
																<ul id="simple_paginator" ></ul>
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
 </div>
          
                            