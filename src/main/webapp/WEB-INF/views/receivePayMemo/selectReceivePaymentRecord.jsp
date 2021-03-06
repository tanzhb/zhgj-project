	<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" %>
	<div id="receivePaymentRecordInfo" class="modal fade" tabindex="-1"   
		data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true"></button>
					<h4 class="modal-title">选择应收账单</h4>
				</div>
				<div class="modal-body">
					<table class="table table-striped table-bordered table-hover table-checkable order-column" id="select_sample_receivePaymentRecord">
	                    <thead>
	                        <tr>
	                            <th style="text-align: center">
	                            	 <label class="mt-checkbox mt-checkbox-single mt-checkbox-outline">
			                      <input  type="checkbox" class="group-checkable" data-set="#select_sample_receivePaymentRecord .checkboxes" />
			                         <span></span>
			                          </label>
	                            </th>
	                            <th>  应收账单号 </th>
	                            <th> 应收类型 </th>
	                            <th> 应收日期 </th>
	                            <th> 应收金额</th>
	                            <th> 未核销金额 </th>
	                            <th> 核销金额 </th>
	                        </tr>
	                    </thead>
	                    <tbody>
	                    </tbody>
	                    <tfoot>
	                    <tr><td/><td/><td/><td/><td>合计:</td><td>{{totalUnVerificateCount}}</td><td>{{totalVerificateValue()}}</td></tr>
	                    <!-- <tr><td/><td>收款单金额:</td><td>{{totalUnVerificateCount}}</td><td>核销金额:</td><td>{{totalVerificateCount}}</td></td><td/><td/></tr> -->
	                    </tfoot>
	                </table>
	            </div>
	            <div class="modal-footer">
					<button type="button" data-dismiss="modal"
						class="btn dark btn-outline">取消</button>
					<button type="button" ng-click="confirmVerificate('receive')" class="btn green">确定核销
						</button>
				</div>
			</div>
		</div>
	</div>
<!-- 基本信息modal 结束 -->