	<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" %>
	<div id="stockBatchInfo" class="modal fade" tabindex="-1"
		data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
				<button type="button" class="btn green  btn-sm btn-circle" style="float: right" ng-click="addStockBatch()">
				<i class="fa fa-plus"></i> 增加 </button>
                                                    <h4 class="modal-title">填写入库批次</h4>
	
				</div>
				<div class="modal-body">
					<table class="table table-striped table-bordered table-hover table-checkable order-column" id="select_sample_stockBatch">
	                    <thead>
	                        <tr>
	                            <th style="width:100px"> 批次号 </th>
	                            <th> 仓库 </th>
	                            <th> 库区 </th>
	                            <th style="width:80px"> 入库数量 </th>
	                            <th style="width:80px">操作</th>
	                        </tr>
	                    </thead>
	                    <tbody>
	                    <tr ng-repeat="stockInBatch in stockInBatchs track by $index" >
		                    
		                    <td>
		                    	<input type="text" class="form-control" id="batchNum"  name="batchNum" ng-model="stockInBatch.batchNum">
		                    </td>
		                    
		                    
		                    <td>
                                <select ng-if="$first" class="form-control"  data-live-search="true"  id="warehouseSerial" ng-init="warehouses[0].serialNum" ng-change="getPositionsAndSelectedAll(stockInBatch)"  name="warehouseSerial" ng-model="stockInBatch.warehouseSerial"  data-size="8">
                                     <option value=""></option>
                                      <option   class="{{stockInBatch.serialNum}}" ng-repeat="warehouse in warehouses" value="{{warehouse.serialNum}}">{{warehouse.warehouseName}}</option>
                                </select>
                                   <select ng-if="!$first" class="form-control" data-live-search="true"  id="warehouseSerial" ng-init="warehouses[0].serialNum" ng-change="getPositions(stockInBatch)"  name="warehouseSerial" ng-model="stockInBatch.warehouseSerial"  data-size="8">
                                     <option value=""></option>
                                      <option  class="{{stockInBatch.serialNum}}" ng-repeat="warehouse in warehouses" value="{{warehouse.serialNum}}">{{warehouse.warehouseName}}</option>
                                </select>
							</td>
							<td>
                                    <select class="form-control"  data-live-search="true"  id="positionSerial" ng-change="countPosition()" ng-init="stockInBatch.warehousePositons[0].serialNum"   name="positionSerial" ng-model="stockInBatch.positionSerial"    data-size="8">
                                        <option value=""></option>
                                        <option  ng-repeat="warehousePositon in stockInBatch.warehousePositons" value="{{warehousePositon.serialNum}}">{{warehousePositon.positionName}}</option>
                                  </select>
                                  <div class="form-control-focus"> </div>
							</td>
							 <td>
		                    	<input type="text" class="form-control" id="stockInCount"  name="stockInCount" ng-model="stockInBatch.stockInCount" ng-keyup="clearNoNumPoint(stockInBatchs[$index],'stockInCount')">
		                    </td>
							<td>
                            	<a href="javascript:;"  class="btn red btn-sm" ng-click="deleteStockBatch($index)">
                          			<i class="fa fa-close"></i> 
                   				</a>
                            </td>
	                    
	                    </tr>
	                    </tbody>
	                    <tfoot><tr><td colspan="3" style="text-align:right">合计：</td><td colspan="2">{{totalCount()}}</td></tr></tfoot>
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