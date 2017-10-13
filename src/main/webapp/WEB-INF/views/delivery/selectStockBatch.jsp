	<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" %>
	<div id="stockBatchInfo" class="modal fade" tabindex="-1"
		data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true"></button>
					<h4 class="modal-title">选择批次</h4>
					<!-- <a href="javascript:;" ng-repeat="serialNum in serialNums" class="btn btn-xs green">{{serialNum}}<i class="fa fa-close"></i></a> -->
					<!-- <a ng-hide="selectedMaterielHide" href="javascript:;" style="margin:0px 5px 2px 0px;" class="btn btn-xs green" ng-repeat="data in serialNums" ng-click="getCheckedIds(data.serialNum)">
                           {{data.materiel.materielNum}}<i class="fa fa-close"></i>
                    </a>  -->
				</div>
				<div class="modal-body">
					<table class="table table-striped table-bordered table-hover table-checkable order-column" id="select_sample_stockBatch">
	                    <thead>
	                        <tr>
	                            <th style="text-align: center">
	                            	 <label class="mt-checkbox mt-checkbox-single mt-checkbox-outline">
			                      <input  type="checkbox" class="group-checkable" data-set="#select_sample_stockBatch .checkboxes" />
			                         <span></span>
			                          </label>
	                            </th>
	                            <th> 入库批次号 </th>
	                            <th> 入库日期 </th>
	                            <th> 仓库 </th>
	                            <th> 库位 </th>
	                            <th> 结存数量 </th>
	                            <th> 出库数量 </th>
	                        </tr>
	                    </thead>
	                    <tbody>
	                    </tbody>
	                    <tfoot><tr><td/><td/><td/><td/><td/><td>合计:</td><td>{{totalCount}}</td></tr></tfoot>
	                </table>
	            </div>
	            <div class="modal-footer">
					<button type="button" data-dismiss="modal"
						class="btn dark btn-outline">取消</button>
					<button type="button" ng-click="confirmSave()" class="btn green">保存
						</button>
				</div>
			</div>
		</div>
	</div>
<!-- 基本信息modal 结束 -->