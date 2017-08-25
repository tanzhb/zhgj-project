<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 选择发货单号modal 开始 -->
	<div id="deliverInfo" class="modal fade bs-modal-lg" tabindex="-1"
		data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true"></button>
					<h4 class="modal-title">选择物料</h4>
				</div>
				<div class="modal-body">
					<table class="table table-striped table-bordered table-hover table-checkable order-column" id="select_sample_2">
	                    <thead>
	                        <tr>
	                            <th style="text-align: center"><input name="select_all" value="1" id="example-select-all" type="radio"/></th>
	                            <th> 物料编码 </th>
	                            <th> 物料名称 </th>
	                            <th> 规格型号 </th>
	                            <th> 单位 </th>
	                            <th> 上级物料 </th>
	                            <th> 类别 </th>
	                            <th> 产地 </th>
	                            <th> 品牌 </th>
	                            <th> 供应商 </th>
	                            <th> 版本 </th>
	                            <th> 状态 </th>
	                        </tr>
	                    </thead>
	                    <tbody>
	                    </tbody>
	                </table>
	            </div>
	            <div class="modal-footer">
					<button type="button" data-dismiss="modal"
						class="btn dark btn-outline">取消</button>
					<button type="button" ng-click="confirmSelectDeliver()" class="btn green">确定
						</button>
				</div>
			</div>
		</div>
	</div>
<!-- 选择发货单号modal 结束 -->
<!-- 选择收货单号modal 开始 -->
	<div id="deliverInfo" class="modal fade bs-modal-lg" tabindex="-1"
		data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true"></button>
					<h4 class="modal-title">选择物料</h4>
				</div>
				<div class="modal-body">
					<table class="table table-striped table-bordered table-hover table-checkable order-column" id="select_sample_2">
	                    <thead>
	                        <tr>
	                            <th style="text-align: center"><input name="select_all" value="1" id="example-select-all" type="radio"/></th>
	                            <th> 物料编码 </th>
	                            <th> 物料名称 </th>
	                            <th> 规格型号 </th>
	                            <th> 单位 </th>
	                            <th> 上级物料 </th>
	                            <th> 类别 </th>
	                            <th> 产地 </th>
	                            <th> 品牌 </th>
	                            <th> 供应商 </th>
	                            <th> 版本 </th>
	                            <th> 状态 </th>
	                        </tr>
	                    </thead>
	                    <tbody>
	                    </tbody>
	                </table>
	            </div>
	            <div class="modal-footer">
					<button type="button" data-dismiss="modal"
						class="btn dark btn-outline">取消</button>
					<button type="button" ng-click="confirmSelectTakeDelivery()" class="btn green">确定
						</button>
				</div>
			</div>
		</div>
	</div>
<!-- 选择收货单号modal 结束 -->