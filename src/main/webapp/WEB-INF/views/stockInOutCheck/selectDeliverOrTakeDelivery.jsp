<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 选择发货单号modal 开始 -->
	<div id="deliverInfo" class="modal fade bs-modal-lg" tabindex="-1"
		data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true"></button>
					<h4 class="modal-title">选择发货单</h4>
				</div>
				<div class="modal-body">
					<table class="table table-striped table-bordered table-hover table-checkable order-column" id="select_sample_out">
	                    <thead>
	                        <tr>
	                            <th style="text-align: center"><input name="select_all" value="1" id="example-select-out-all" type="radio"/></th>
	                        <th >发货单编号</th><!--  style="white-space: nowrap;"-->
							<th >关联销售单号</th>
							<th >订单金额</th>
							<th >供应商</th>
							<th >物料</th>
							<th >包装数量</th>
							<th >收货方</th>
							<th >发货地点</th>
							<th >发货日期</th>
							<th >运输方式</th>
							<th >收货地点</th>
							<th >备注</th>
	                        </tr>
	                    </thead>
	                    <tbody>
	                    </tbody>
	                </table>
	            </div>
	            <div class="modal-footer">
					<button type="button" data-dismiss="modal"
						class="btn dark btn-outline">取消</button>
					<button type="button" ng-click="confirmSelectDeliverOrTakeDeliveryInfo('out')" class="btn green">确定
						</button>
				</div>
			</div>
		</div>
	</div>
<!-- 选择发货单号modal 结束 -->
<!-- 选择收货单号modal 开始 -->
	<div id="takeDeliveryInfo" class="modal fade bs-modal-lg" tabindex="-2"
		data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true"></button>
					<h4 class="modal-title">选择收货单</h4>
				</div>
				<div class="modal-body">
					<table class="table table-striped table-bordered table-hover table-checkable order-column" id="select_sample_in">
	                    <thead>
	                        <tr>
	                            <th style="text-align: center"><input name="select_all" value="1" id="example-select-in-all" type="radio"/></th>
	                        <th>收货单编号</th>
							<th>采购订单号</th>
							<th >订单金额</th>
							<th >供应商</th>
							<th>发货方</th>
							<th>物料</th>
							<th>包装数量</th>
							<th>使用包装</th>
							<th>发货地点</th>
							<th>发货日期</th>
							<th>运输方式</th>
							<th>收货/提货点</th>
							<th>备注</th>
							<th>状态</th>
	                        </tr>
	                    </thead>
	                    <tbody>
	                    </tbody>
	                </table>
	            </div>
	            <div class="modal-footer">
					<button type="button" data-dismiss="modal"
						class="btn dark btn-outline">取消</button>
					<button type="button" ng-click="confirmSelectDeliverOrTakeDeliveryInfo('in')" class="btn green">确定
						</button>
				</div>
			</div>
		</div>
	</div>
<!-- 选择收货单号modal 结束 -->