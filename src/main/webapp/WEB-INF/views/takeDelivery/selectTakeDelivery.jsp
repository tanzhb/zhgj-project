<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"  %>
<!-- 基本信息modal 开始 -->
	<div id="takeDeliveryInfo" class="modal fade bs-modal-lg" tabindex="-1"
		data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true"></button>
					<h4 class="modal-title">选择收货单</h4>
				</div>
				<div class="modal-body">
					<table class="table table-striped table-bordered table-hover table-checkable order-column" id="takeDelivery">
	                    <thead>
	                        <tr>
	                            <th style="text-align: center"></th>
	                            <th>收货单编号</th>
								<th>订单编号</th>
								<th>发货方</th>
								<th>物料</th>
								<th>包装数量</th>
								<th>使用包装</th>
								<th>发货地点</th>
								<th>发货日期</th>
								<th>运输方式</th>
								<th>收货/提货点</th>
								<th>备注</th>
	                        </tr>
	                    </thead>
	                    <tbody>
	                    </tbody>
	                </table>
	            </div>
	            <div class="modal-footer">
					<button type="button" data-dismiss="modal"
						class="btn dark btn-outline">取消</button>
					<button type="button" ng-click="confirmTakeDeliverySelect()" class="btn green">确定
						</button>
				</div>
			</div>
		</div>
	</div>
<!-- 基本信息modal 结束 -->