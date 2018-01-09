	<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" %>
	<div id="InRecordInfo" class="modal fade" tabindex="-1"
		data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true"></button>
					<h4 class="modal-title">入库记录</h4>
				</div>
				<div class="modal-body">
					<table class="table table-striped table-bordered table-hover  order-column" id="select_sample_inRecord">
	                    <thead>
	                        <tr>
	                         <!--  <th style="text-align: center">
	                            	 <label class="mt-checkbox mt-checkbox-single mt-checkbox-outline">
			                      <input  type="checkbox" class="group-checkable" data-set="#select_sample_inRecod .checkboxes" />
			                         <span></span>
			                          </label>
	                            </th> -->
	                            
					            <th  ><span style="display:inline-block;width:80px;">入库单号 </span></th>
	                            <th> 入库类型</th>
	                             <th> 商品名称</th>
	                              <th>规格型号</th>
	                             <th>发货日期</th>
	                            <th> 发货数量 </th>
	                            <th> 入库日期 </th>
	                            <th>入库数量 </th>
	                           <th>入库仓库</th> 
	                            <th> 操作员</th>
	                        </tr>
	                    </thead>
	                    <tbody>
	                    </tbody>
	                    <tfoot><tr><td/><td/><td/><td/><td/><td/><td>合计:</td><td>{{totaIInRecordCount}}</td><td/><td/></tr></tfoot>
	                </table>
	            </div>
	            
			</div>
		</div>
	</div>
<!-- 基本信息modal 结束 -->