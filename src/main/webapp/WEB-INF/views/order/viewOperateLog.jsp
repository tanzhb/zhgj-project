<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" %>
<!-- 操作日志modal 开始 -->
	<div id="operateLogInfo" class="modal fade" tabindex="-1"
		data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true"></button>
					<h4 class="modal-title">变更记录</h4>
					
				</div>
				<div class="modal-body">
					<table class="table table-striped table-bordered table-hover table-checkable order-column" id="select_operateLog">
	                    <thead>
	                        <tr>
	                            <th> 操作 </th>
	                            <th> 操作人 </th>
	                            <th> 操作时间 </th>
	                            <th> 备注 </th>
	                        </tr>
	                    </thead>
	                    <tbody>
	                    </tbody>
	                </table>
	            </div>
	            <div class="modal-footer">
					<button type="button" data-dismiss="modal"
						class="btn dark btn-outline">取消</button>
				</div>
			</div>
		</div>
	</div>
<!-- 操作日志modal 结束 -->