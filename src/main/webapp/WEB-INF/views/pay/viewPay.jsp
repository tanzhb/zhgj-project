<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- BEGIN PAGE HEADER-->

<h3 class="page-title">采购付款</h3>
<div class="page-bar">
	<ul class="page-breadcrumb">
		<li><i class="fa fa-home"></i> <a ui-sref="dashboard">首页</a> <i
			class="fa fa-angle-right"></i></li>
		<li><a ui-sref="paymentRecordC">付款</a> <i class="fa fa-angle-right"></i>
		</li>
		<li><a>付款详情</a></li>
	</ul>
	<div class="page-toolbar">
		<div class="btn-group pull-right">
			<button type="button"
				class="btn btn-fit-height grey-salt dropdown-toggle"
				onclick="printdiv('saleOrderPrint')">
				<i class="fa fa-print"></i> 打印
			</button>

		</div>
	</div>
</div>
<div class="row" id="saleOrderPrint">
	<div class="col-md-12">
		<!-- BEGIN EXAMPLE TABLE PORTLET-->
		<div class="portlet bordered">
			<div class="portlet-body">
			<form action="#" id="form_sample_1" class="form-horizontal">
				<div class="portlet light ">
					<div class="portlet-title">
						<div class="caption">付款信息</div>
						<div class="tools" id="noprintdiv">
							<button ng-click="goBack()" type="button"
								ng-hide="saleOrderInput" class="btn red  btn-outline  btn-sm">
								<i class="fa fa-undo"></i> 取消
							</button>
						</div>
					</div>
					
						<div class="portlet-body form">
							<!-- BEGIN FORM-->
							<div class="form-body">
								<div class="alert alert-danger display-hide">
									<button class="close" data-close="alert"></button>
									请先输入正确数据！
								</div>
								<div class="row">
									<div class="col-md-4">
										<div class="form-group form-md-line-input">
											<label class="control-label col-md-4"><span
												class="required" aria-required="true"> * </span>付款单号:</label>
											<div class="col-md-8">
											<p class="form-control-static">{{pay.paymentNum}}</p>
											</div>
										</div>
									</div>
									<!--/span-->
									<div class="col-md-4">
										<div class="form-group form-md-line-input">
											<label class="control-label col-md-4"><span
												class="required" aria-required="true"> * </span>付款计划编号:</label>
											<div class="col-md-8">
												<p class="form-control-static">{{pay.paymentPlanNum}}</p>
											</div>
										</div>
									</div>
									<div class="col-md-4">
										<div class="form-group form-md-line-input">
											<label class="control-label col-md-4"><span
												class="required" aria-required="true"> * </span>关联采购订单号:</label>
											<div class="col-md-8">
												<p class="form-control-static">{{pay.orderNum}}</p>
											</div>
										</div>
									</div>
									<!--/span-->
								</div>


								<!--/row-->
								<div class="row">
									<div class="col-md-4">
										<div class="form-group form-md-line-input">
											<label class="control-label col-md-4"><span
												class="required" aria-required="true"> * </span>付款类型:</label>
											<div class="col-md-8">
												<p class="form-control-static">{{pay.paymentType}}</p>
											</div>

										</div>
									</div>
									<!--/span-->
									<div class="col-md-4">
										<div class="form-group form-md-line-input">
											<label class="control-label col-md-4"><span
												class="required" aria-required="true"> * </span>支付类型 :</label>
											<div class="col-md-8">
												<p class="form-control-static">{{pay.paymentStyle}}</p>
											</div>

										</div>
									</div>
									<!--/span-->
									<div class="col-md-4">
										<div class="form-group form-md-line-input">
											<label class="control-label col-md-4"><span
												class="required" aria-required="true"> * </span>发票方式 :</label>
											<div class="col-md-8">
												<p class="form-control-static"
													ng-if="pay.billStyle=='1'">先票后款</p>
												<p class="form-control-static"
													ng-if="pay.billStyle=='0'">先款后票</p>
											</div>

										</div>
									</div>
									<!--/span-->
								</div>
								<!--/row-->
								<div class="row">
									<div class="col-md-4">
										<div class="form-group form-md-line-input">
											<label class="control-label col-md-4"><span
												class="required" aria-required="true"> * </span>是否开票:</label>
											<div class="col-md-8">
												<p class="form-control-static" ng-if="pay.isBill=='1'">是</p>
												<p class="form-control-static" ng-if="pay.isBill=='0'">否</p>
											</div>
										</div>
									</div>
									<!--/span-->
									<div class="col-md-4">
										<div class="form-group form-md-line-input">
											<label class="control-label col-md-4"><span
												class="required" aria-required="true"> * </span>关联发票单号:</label>
											<div class="col-md-8">
												<p class="form-control-static">{{pay.invoiceSerial}}</p>
											</div>

										</div>
									</div>
									<!--/span-->
									<div class="col-md-4">
										<div class="form-group form-md-line-input">
											<label class="control-label col-md-4"><span
												class="required" aria-required="true"> * </span>开票日期:</label>
											<div class="col-md-8">
												<p class="form-control-static">{{pay.applyDate}}</p>
											</div>
										</div>
									</div>
									<!--/span-->
								</div>
								<div class="row">
									<div class="col-md-4">
										<div class="form-group form-md-line-input">
											<label class="control-label col-md-4"><span
												class="required" aria-required="true"> * </span>申请人:</label>
											<div class="col-md-8">
												<p class="form-control-static">{{pay.applicant}}</p>
											</div>
										</div>
									</div>
									<!--/span-->
									<div class="col-md-4">
										<div class="form-group form-md-line-input">
											<label class="control-label col-md-4">申请日期:</label>
											<div class="col-md-8">
												<p class="form-control-static">{{pay.applyDate}}</p>
											</div>
										</div>
									</div>
									<!--/span-->
									<div class="col-md-4">
										<div class="form-group form-md-line-input">
											<label class="control-label col-md-4">备注:</label>
											<div class="col-md-8">
												<p class="form-control-static">{{pay.remark}}  </p>
											</div>
										</div>
									</div>
								</div>
								<!--/row-->
								<!--/row-->
								<div class="row">
									<div class="col-md-4">
										<div class="form-group form-md-line-input">
											<label class="control-label col-md-4"><span
												class="required" aria-required="true"> * </span>付款凭证:</label>
											<div class="col-md-8" >
												<div class="sp-page-content">
													<div class="sp-page-box">
														<div class="sp-page-boxcolumn span2">
															<span class="sp-upload"> 
														<img class="sp-upload-photo" alt=""  ng-src="zhgj/upload/{{pay.paymentVoucher}}" /> 
															</span>
														</div>
													</div>
												</div>
												
											</div>

										</div>
									</div>
								</div>
							</div>
						</div>

					<!-- 物料信息 start-->
					<div class="portlet-title">
						<div class="caption">订单结算条款</div>
					</div>
					<div class="portlet-body form">
							<div class="table-scrollable">
								<table class="table table-bordered table-hover">
								<thead>
										<tr>
										    <th colspan='3' style="text-align:center;">付款方:中航国际</th>
											<th colspan='4' style="text-align:center;">收款方:{{pay.supplyComId}}</th>
											<th colspan='4' style="text-align:center;">本次应付:<font color="red">{{pay.paymentAmount|currency:'￥'}}</font></th>
										</tr>
									</thead>
									<thead>
										<tr>
											<th>支付类型</th>
											<th>支付节点</th>
											<th>账期</th>
											<th>支付比率%</th>
											<th>支付金额</th>
											<th>支付方式</th>
											<th>开票方式</th>
											<th>开票金额</th>
											<th>未开金额</th>
											<th>备注</th>
											<th>状态</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td><p class="form-control-static">{{pay.clausePaymentType}}</p></td>
											<td><p class="form-control-static">{{pay.deliveryNode}}</p></td>
											<td><p class="form-control-static">{{pay.accountPeriod}}</p></td>
											<td><p class="form-control-static">{{pay.deliveryRate}}</p></td>
											<td><p class="form-control-static">{{pay.deliveryAmount|currency:'￥'}}</p></td>
											<td><p class="form-control-static">{{pay.paymentMethod}}</p></td>
											<td><p class="form-control-static">{{pay.billingMethod}}</p></td>
											<td><p class="form-control-static">{{pay.billingAmount|currency:'￥'}}</p></td>
											<td><p class="form-control-static">{{pay.unbilledAmount|currency:'￥'}}</p></td>
											<td><p class="form-control-static">{{pay.clauseRemark}}</p></td>
											<td><p class="form-control-static">{{pay.clauseStatus}}</p></td>
										</tr>
									</tbody>
								</table>
							</div>
					</div>
					<!-- 供应商 end-->
				</div>
				</form>
			</div>
		</div>
	</div>
</div>
