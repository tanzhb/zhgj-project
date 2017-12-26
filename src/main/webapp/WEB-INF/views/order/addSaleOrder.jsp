<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- BEGIN PAGE HEADER-->

<!-- <h3 class="page-title"> 订单信息
</h3> -->
<!-- <div class="page-bar">
    <ul class="page-breadcrumb">
        <li>
            <i class="fa fa-home"></i>
            <a ui-sref="dashboard">首页</a>
            <i class="fa fa-angle-right"></i>
        </li>
        <li>
            <a ui-sref="saleOrder">销售订单</a>
            <i class="fa fa-angle-right"></i>
        </li>
        <li>
            <a>{{opration}}</a>
        </li>
    </ul>
    <div class="page-toolbar">
          <div class="btn-group pull-right">
              <button type="button" class="btn btn-fit-height grey-salt dropdown-toggle" onclick="printdiv('saleOrderPrint')"> 
              	<i class="fa fa-print"></i>
                  		打印
              </button>
              
          </div>
      </div>
</div> -->
<div class="row" id="saleOrderPrint">
    <div class="col-md-12">
			<div class="portlet light ">
           		 <div class="portlet-body">
					<jsp:include  page="editSaleOrderBase.jsp"/>
      			</div>
			</div>
	</div>
</div> 

<jsp:include  page="selectMateriel.jsp"/> <!-- 选择供应物料 -->


<div id="addFrame" class="modal fade bs-modal-lg" tabindex="-1"
	data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true"></button>
				<h4 class="modal-title">选择框架协议</h4>
			</div>
			<div class="modal-body">
				<table
					class="table table-striped table-bordered table-hover table-checkable order-column"
					id="sample_Frame">
					<thead>
						<tr>
							<th style="text-align: center"></th>
							<th> 框架协议号 </th>
                            <th> 采购商 </th>
                            <th> 框架类型 </th>
                            <th> 框架合同号 </th>
                            <th> 生效日期 </th>
                            <th> 失效日期 </th>
                            <th> 订单 </th>
                            <th> 签订人 </th>
                            <th> 版本 </th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>
			<div class="modal-footer">
				<button type="button" data-dismiss="modal"
					class="btn dark btn-outline">取消</button>
				<button type="button" ng-click="confirmSelectFrame()" class="btn green">确定
				</button>
			</div>
		</div>
	</div>
</div>
