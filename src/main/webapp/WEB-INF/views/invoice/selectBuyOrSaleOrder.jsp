<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 选择采购订单号modal 开始 -->
	<div id="buyOrderInfo" class="modal fade bs-modal-lg" tabindex="-1"
		data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true"></button>
					<h4 class="modal-title">选择采购订单</h4>
				</div>
				<div class="modal-body">
					<table class="table table-striped table-bordered table-hover table-checkable order-column" id="select_sample_buy">
	                    <thead>
	                        <tr>
	                            <th style="text-align: center"><input name="select_all" value="1" id="example-select-out-all" type="radio"/></th>
	                        <th> 采购订单号 </th>
	                            <th> 供应方 </th>
	                            <th> 采购商品 </th>
	                            <th> 金额 </th>
	                            <th> 配送 </th>
	                            <th> 服务模式 </th>
	                            <th> 关联采购合同 </th>
	                            <th> 关联销售单 </th>
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
					<button type="button" ng-click="confirmSelectBuyOrSaleOrderInfo('buy')" class="btn green">确定
						</button>
				</div>
			</div>
		</div>
	</div>
<!-- 选择采购订单号modal 结束 -->
<!-- 选择销售订单号modal 开始 -->
	<div id="saleOrderInfo" class="modal fade bs-modal-lg" tabindex="-2"
		data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true"></button>
					<h4 class="modal-title">选择销售订单</h4>
				</div>
				<div class="modal-body">
					<table class="table table-striped table-bordered table-hover table-checkable order-column" id="select_sample_sale">
	                    <thead>
	                        <tr>
	                            <th style="text-align: center"><input name="select_all" value="1" id="example-select-in-all" type="radio"/></th>
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
	            <div class="modal-footer">
					<button type="button" data-dismiss="modal"
						class="btn dark btn-outline">取消</button>
					<button type="button" ng-click="confirmSelectBuyOrSaleOrderInfo('sale')" class="btn green">确定
						</button>
				</div>
			</div>
		</div>
	</div>
<!-- 选择销售订单号modal 结束 -->