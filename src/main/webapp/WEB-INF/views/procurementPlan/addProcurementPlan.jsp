<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- BEGIN PAGE HEADER-->

<div class="row" id="ProcurementPlanPrint">
    <div class="col-md-12">
        <!-- BEGIN EXAMPLE TABLE PORTLET-->
			<div class="portlet light ">
					<!-- <div class="portlet-title">
			               <div class="tools">
			               		<button type="button" ng-click="submitPage()" ng-hide="orderStatusInput" class="btn blue btn-circle  btn-sm">提交审核</button>
				                <button type="button" ng-click="cancelPage()" class="btn default btn-circle  btn-sm"><i class="fa fa-undo"></i> 取消 </button>
				            </div>
					</div> -->
           		 <div class="portlet-body">
					<jsp:include  page="editProcurementPlanBase.jsp"/>
      			</div>
			</div>
	</div>
</div>
<jsp:include  page="selectMateriel.jsp"/> <!-- 选择基本物料 -->

<div id="saleOrderInfo" class="modal fade bs-modal-lg" tabindex="-1"
	data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true"></button>
				<h4 class="modal-title">选择销售订单</h4>
			</div>
			<div class="modal-body">
				<table
					class="table table-striped table-bordered table-hover table-checkable order-column"
					id="sample_21">
					<thead>
						<tr>
							<th style="text-align: center"></th>
							<th> 销售订单号 </th>
                            <th> 采购商 </th>
                            <th> 销售数量 </th>
                            <th> 金额 </th>
                            <!-- <th> 配送 </th> -->
                            <th> 销售类型 </th>
                            <!-- <th> 关联销售合同 </th> -->
                            <th> 关联采购单 </th>
                            <th> 下单日期 </th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>
			<div class="modal-footer">
				<button type="button" data-dismiss="modal"
					class="btn dark btn-outline">取消</button>
				<button type="button" ng-click="confirmSelectOrder()" class="btn green">确定
				</button>
			</div>
		</div>
	</div>
</div>




