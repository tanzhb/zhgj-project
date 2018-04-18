<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" %>
<!-- 基本信息modal 开始 -->
	<div id="underMaterielInfo" class="modal fade" tabindex="-1"
		data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true"></button>
					<h4 class="modal-title">显示下级物料</h4>
					<!-- <a href="javascript:;" ng-repeat="serialNum in serialNums" class="btn btn-xs green">{{serialNum}}<i class="fa fa-close"></i></a> -->
				</div>
				<div class="modal-body">
					<table class="table table-striped table-bordered table-hover table-checkable order-column" id="select_sample_under">
	                    <thead>
	                        <tr>
	                            <th> 物料编码 </th>
	                            <th> 物料名称 </th>
	                            <th> 规格型号 </th>
	                            <th> 单位 </th>
	                            <!-- <th> 库存数量 </th> -->
	                        </tr>
	                    </thead>
	                    <tbody>
	                    </tbody>
	                </table>
	            </div>
			</div>
		</div>
	</div>
<!-- 基本信息modal 结束 -->