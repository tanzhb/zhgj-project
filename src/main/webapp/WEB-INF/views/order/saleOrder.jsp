<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- BEGIN PAGE HEADER-->
<h3 class="page-title"> 销售订单
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
            <a ui-sref="saleOrder">销售管理</a>
            <i class="fa fa-angle-right"></i>
        </li>
        <li>
            <a ui-sref="saleOrder">销售订单</a>
        </li>
    </ul>
</div>
<div class="row">

	<div class="col-md-12">
	        <div class="portlet box red">
	            <div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>销售订单
				</div>
				<div class="actions">
					<a href="javascript:;" ui-sref="addSaleOrder"
						 class="btn btn-default btn-sm btn-circle">
						<i class="fa fa-plus"></i> 添加
					</a> 
					<a href="javascript:;" ng-click="editSaleOrder()"
						 class="btn btn-default btn-sm btn-circle">
						<i class="fa fa-edit"></i> 修改
					</a>
					<a href="javascript:;" ng-click="deleteSaleOrder()"
						class="btn btn-default btn-sm btn-circle"> <i
						class="fa fa-minus"></i> 删除
					</a>
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
								class="tool-action" ng-click="exportMateriel()"> <i class="fa fa-file-excel-o"></i> 导出
							</a></li>
						</ul>
					</div>
				</div>
	            </div>
	            <div class="portlet-body">
	                <table class="table table-striped table-bordered table-hover table-checkable order-column" id="sample_2">
	                    <thead>
	                        <tr>
	                            <th style="text-align: center"><input name="select_all" id="example-select-all" type="checkbox"/></th>
	                            <th> 销售订单号 </th>
	                            <th> 采购方 </th>
	                            <th> 销售商品 </th>
	                            <th> 金额 </th>
	                            <th> 配送 </th>
	                            <th> 服务模式 </th>
	                            <th> 关联销售合同 </th>
	                            <th> 关联采购单 </th>
	                            <th> 下单日期 </th>
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

 
 <!-- 删除用户modal 开始 -->
	<div id="delSaleOrderModal" class="modal fade" tabindex="-1"
		data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true"></button>
					<h4 class="modal-title">确认</h4>
				</div>
				<div class="modal-body">
					<p>是否删除已选订单?</p>
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
<!-- 删除用户modal 结束 -->