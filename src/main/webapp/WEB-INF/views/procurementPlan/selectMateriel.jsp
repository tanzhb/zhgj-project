<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" %>
<!-- 基本信息modal 开始 -->
	<div id="basicMaterielInfo" class="modal fade" tabindex="-1"
		data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true"></button>
					<h4 class="modal-title">选择物料</h4>
					<!-- <a href="javascript:;" ng-repeat="serialNum in serialNums" class="btn btn-xs green">{{serialNum}}<i class="fa fa-close"></i></a> -->
					<a ng-hide="selectedMaterielHide" href="javascript:;" style="margin:0px 5px 2px 0px;" class="btn btn-xs green" ng-repeat="data in serialNums" ng-click="getCheckedIds(data.serialNum)">
                           {{data.materiel.materielNum}}<i class="fa fa-close"></i>
                    </a>
				</div>
				<div class="modal-body">
					<table class="table table-striped table-bordered table-hover table-checkable order-column" id="select_sample_2">
	                    <thead>
	                        <tr>
	                            <th>
                                    <label class="mt-checkbox mt-checkbox-single mt-checkbox-outline">
                                        <input type="checkbox" class="group-checkable" data-set="#select_sample_2 .checkboxes" />
                                        <span></span>
                                    </label>
                                </th>
	                            <th> 物料编码 </th>
	                            <th> 物料名称 </th>
	                            <th> 规格型号 </th>
	                            <th> 单位 </th>
	                            <!-- <th ng-if="saleOrder.orderType!='自主销售'"> 供应商 </th> -->
	                            <!-- <th> 库存数量 </th> -->
	                            
	                        </tr>
	                    </thead>
	                    <tbody>
	                    </tbody>
	                </table>
	            </div>
	            <div class="modal-footer">
					<button type="button" data-dismiss="modal"
						class="btn dark btn-outline">取消</button>
					<button ng-if="saleOrder.orderType!='自主销售'" type="button" ng-click="confirmSelect()" class="btn green">确定
						</button>
					<button ng-if="saleOrder.orderType=='自主销售'" type="button" ng-click="ziZhuConfirmSelect()" class="btn green">确定
						</button>
				</div>
			</div>
		</div>
	</div>
<!-- 基本信息modal 结束 -->