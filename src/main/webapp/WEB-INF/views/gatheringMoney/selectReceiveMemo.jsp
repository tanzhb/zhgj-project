	<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" %>
	<div id="receiveMemoInfo" class="modal fade" tabindex="-1"
		data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true"></button>
					<h4 class="modal-title">选择收款水单</h4>
				</div>
				<div class="modal-body">
					<table class="table table-striped table-bordered table-hover table-checkable order-column" id="select_sample_receiveMemo">
	                    <thead>
	                        <tr>
	                            <th style="text-align: center">
	                            	 <label class="mt-checkbox mt-checkbox-single mt-checkbox-outline">
			                      <input  type="checkbox" class="group-checkable" data-set="#select_sample_receiveMemo .checkboxes" />
			                         <span></span>
			                          </label>
	                            </th>
					            <th>收款单号 </th>
	                            <th> 收款金额 </th>
	                            <th> 收款方式 </th>
	                            <th>到账日期 </th>
	                            <th>水单余额 </th>
	                            <th> 核销金额</th>
	                        </tr>
	                    </thead>
	                    <tbody>
	                    </tbody>
	                    <tfoot><tr><td/><td/><td/><td/><td>合计:</td><td>{{totalUnVerificateCount}}</td><td>{{totalVerificateValue()}}</td></tr></tfoot>
	                </table>
	            </div>
	            <div class="modal-footer">
					<button type="button" data-dismiss="modal"
						class="btn dark btn-outline">取消</button>
					<button type="button" ng-click="confirmVerificate()" class="btn green">确定核销
						</button>
				</div>
			</div>
		</div>
	</div>
<!-- 基本信息modal 结束 -->