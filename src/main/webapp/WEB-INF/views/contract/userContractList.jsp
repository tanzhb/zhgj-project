<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- BEGIN PAGE HEADER-->
<table
					class="table table-striped table-bordered table-hover table-checkable order-column"
					id="sample_1">
					<thead>
						<tr>
							<th><label
								class="mt-checkbox mt-checkbox-single mt-checkbox-outline">
									<input type="checkbox" class="group-checkable"
									data-set="#sample_1 .checkboxes" /> <span></span>
							</label></th>
							<th>合同编号</th>
							<th>企业名称</th>
							<th>合同类型</th>
							<th>服务模式</th>
							<th>关联订单</th>
							
							<th>签订日期</th>
							<th>签订人</th>
							<th>开始日期</th>
							<th>结束日期</th>
							<th>版本</th>
							<th>状态</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${contractList}" var="contract">
						<tr ng-repeat="list in contractlList" ng-init="index=$index" class="odd gradeX">
							<td style="vertical-align:middle;"><label class="mt-checkbox mt-checkbox-single mt-checkbox-outline"><input type="checkbox" class="checkboxes" value="1" /> <span></span></label></td>
							<td style="vertical-align:middle;">${contract.contractNum}</td>
							
							<td style="vertical-align:middle;">looper</td>
							<td style="vertical-align:middle;">looper</td>
							<td style="vertical-align:middle;">looper</td>
							<td style="vertical-align:middle;">looper</td>
							<td style="vertical-align:middle;">looper</td>
							<td style="vertical-align:middle;">looper</td>
							<td style="vertical-align:middle;">looper</td>
							
							
							<td style="vertical-align:middle;"><a href="mailto:looper90@gmail.com">looper90@gmail.com </a></td>
							<td style="vertical-align:middle;"><span class="label label-sm label-warning">Suspended </span></td>
							<td class="center" style="vertical-align:middle;">12.12.2011</td>
							<td style="text-align:center;">
								<a href="javascript:;" class="btn btn-outline btn-circle btn-sm purple"><i class="fa fa-edit"></i> 编辑 </a>
                                <a href="javascript:;" class="btn btn-outline btn-circle dark btn-sm black"><i class="fa fa-remove"></i> 删除  </a>
							</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
<script>
    TableDatatablesManaged.init();
</script>
<!-- END MAIN JS -->