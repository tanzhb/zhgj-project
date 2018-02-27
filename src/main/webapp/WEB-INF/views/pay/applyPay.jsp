<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- BEGIN PAGE HEADER-->

<!-- <div class="page-bar">
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
</div> -->
<div class="row" id="saleOrderPrint">
	<div class="col-md-12">
		<!-- BEGIN EXAMPLE TABLE PORTLET-->
		<div class="portlet bordered">
			<div class="portlet-body">
				<form action="#" id="form_sample_1" class="">
					<div class="portlet light ">
					<ul class="nav nav-tabs">
							<li class="active bold"><a data-target="#tab_1_1"
								data-toggle="tab">应付信息</a></li>
							<!-- <li class="bold"><a data-target="#tab_1_2" data-toggle="tab">付款信息</a> -->
							<!-- <li class="bold"><a data-target="#tab_1_2" data-toggle="tab">附件</a> -->
							<li class="dropdown pull-right tabdrop">
								<button type="button" onclick="goBackPage()" class="btn defualt  btn-circle  btn-sm"><i class="fa fa-reply"></i>返回</button>
							</li>							
							</li>
						</ul>
						<div class="tab-content">
						<div class="tab-pane fade active in" id="tab_1_1">
								<div class="portlet-title" style="min-height: 18px;">
									<div class="tools" style="float: right" id="noprintdiv">
										<button type="submit" ng-click="saveBasicInfo('receive')"
											class="btn green  btn-circle  btn-sm" ng-show="input">
											<i class="fa fa-save"></i> 保存
										</button>
										<button ng-click="goBack()" type="button"
											class="btn defualt  btn-circle  btn-sm">
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
										
										<div class="row"   >
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">应付账单号<span
														class="required" aria-required="true"> * </span></label>
													<div class="">
														<input type="text" name="paymentNum" class="form-control"
															ng-model="paymentRecord.paymentNum" ng-show="input">
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-show="span">{{paymentRecord.paymentNum}}</p>
													</div>
												</div>
											</div>
											<!--/span-->

											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">关联采购订单号<span
														class="required" aria-required="true"> * </span></label>
													<div class="">
														<div class="input-group" data-target="#basicMaterielInfo"
															data-toggle="modal" ng-click="selectMateriel()"
															onclick="return false;">
															<input id="orderSerial" name="orderNum" type="text" ng-show="input"
																class="form-control" ng-model="saleOrder.orderNum"
																readonly="readonly"> <span
																class="input-group-btn" ng-show="input" style="vertical-align: top;">
																<button class="btn default" type="button">
																	<i class="fa fa-search"></i>
																</button>
															</span>
															<p class="form-control-static" ng-show="span">{{paymentRecord.orderNum}}</p>
														</div>
														<input type="text" ng-model="orderSerial" ng-hide="true" />
													</div>
												</div>
											</div>
											<!--/span-->
												<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">收款类型<span
														class="required" aria-required="true"> * </span></label>
													<div class="">
														<select class="form-control"
															id="paymentType" ng-show="input"
															name="paymentType"
															ng-model="paymentRecord.paymentType">
															<option value="">收款类型</option>
															<option value="采购付款">采购付款</option>
															<option value="采购退款">采购退款</option>
															<option value="服务费用类">服务费用类</option>
															<option   ng-if="isBG"    value="清关">清关</option><!--是否报关单  -->
															<option value="其它">其它</option>
														</select>
														<p class="form-control-static" ng-show="span">{{paymentRecord.paymentType}}</p>
														<div class="form-control-focus"></div>
													</div>
												</div>
											</div>
											<!--/span-->
										</div>
										
										<!--/row-->
										<div class="row">
										<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">下单日期<span
														class="required" aria-required="true"> * </span></label>
													<div class="">
														<input type="text" class="form-control" name="orderDate"   readonly
														data-date-format="yyyy-mm-dd" id="orderDate"
													    data-date-viewmode="years" size="16"
															ng-model="paymentRecord.orderDate" ng-show="input"/>
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-show="span">{{paymentRecord.orderDate}}</p>
													</div>
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">订单金额<span
														class="required" aria-required="true"> * </span></label>
													<div class="">
														<input type="text" name="orderAmount" class="form-control"
															 ng-model="saleOrder.orderAmount" ng-show="input" readonly/>
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-show="span">{{paymentRecord.orderAmount}}</p>
													</div>

												</div>
											</div>
											<!--/span-->
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">币种<span
														class="required" aria-required="true"> * </span></label>
													<div class="">
														<select class="form-control" id="applyCurrency"
															name="applyCurrency"
															ng-model="paymentRecord.applyCurrency"
															 ng-show="input">
															<option value="">币种</option>
															<option value="人民币">人民币</option>
															<option value="美元">美元</option>
															<option value="欧元">欧元</option>
															<option value="日币">日币</option>
														</select>
														<p class="form-control-static" ng-show="span">{{paymentRecord.applyCurrency}}</p>
													</div>
												</div>
											</div>
											</div> <!--/row-->
											<div ng-show="paymentRecord.paymentType!='清关'">
											<div class="row"  >
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">支付节点<span
														class="required" aria-required="true"> * </span></label>
													<div class="">
														<select class="form-control" id="paymentNode"
															name="paymentNode"   
															ng-model="paymentRecord.paymentNode"
														    ng-show="input" ng-change="selectPaymentNode(paymentRecord.paymentNode)">
															<option value="">支付节点</option>
															<option ng-repeat="item in clauseSettlementList" value="{{item.deliveryNode}}">{{item.deliveryNode}}</option>
														</select>
														<p class="form-control-static" ng-show="span">{{paymentRecord.paymentNode}}</p>
													</div>
												</div>
											</div>
												<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">支付类型<span
														class="required" aria-required="true"> * </span></label>
													<div class="">
														<select class="form-control" id="payType"   disabled="disabled"
															name="payType"  
															ng-model="paymentRecord.payType"
														    ng-show="input">
															<option value="">支付类型</option>
															
															<option value="{{item.paymentType}}" ng-repeat="item in clauseSettlementList"  ng-bind="item.paymentType"  ></option>
															<!-- <option value="预付款">预付款</option>
															<option value="中期款">中期款</option>
															<option value="全款">全款</option>
															<option value="尾款">尾款</option> -->
														</select>
														<p class="form-control-static" ng-show="span">{{paymentRecord.payType}}</p>
													</div>
												</div>
											</div>
											<!--/span-->
											
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">支付比率<span
														class="required" aria-required="true"> * </span></label>
													<div class="">
														<input type="text" name="deliveryRate" class="form-control" ng-if="paymentRecord.paymentType!='清关'"
															readonly ng-model="paymentRecord.deliveryRate" ng-show="input"/>
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-show="span">{{paymentRecord.deliveryRate}}</p>
													</div>
												</div>
											</div>
											</div>
											<div class="row"  >
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">支付金额<span
														class="required" aria-required="true"> * </span></label>
													<div class="" >
														<input type="text" name="applyPaymentAmount" id="applyPaymentAmount" class="form-control" number  
															 ng-model="paymentRecord.applyPaymentAmount" ng-blur="getChnAmount()" ng-show="input"  readonly/>
															 <p class="form-control-static" ng-show="span">{{paymentRecord.applyPaymentAmount}}</p>
														<div class="form-control-focus"></div>
													</div>
												</div>
											</div>
											
											<!-- <div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">节点单据号 <span
														class="required" aria-required="true"> * </span></label>
													<div class="">
														<input type="text" class="form-control" name="nodeNum"
															ng-model="paymentRecord.nodeNum" ng-show="input"/>
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-show="span">{{paymentRecord.nodeNum}}</p>
													</div>
												</div>
											</div> -->
											<!--/span-->
											
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">大写金额</label>
													<div class="">
														<input type="text" name="chnAmount" class="form-control"
															readonly ng-model="chnAmount" ng-show="input"/>
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-show="span">{{chnAmount}}</p>
													</div>
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">支付方式<span
														class="required" aria-required="true"> * </span></label>
													<div class="">
														<select class="form-control" id="applyCurrency"  disabled="disabled"
															name="applyCurrency"
															ng-model="paymentRecord.applyCurrency"
															 ng-show="input">
															<option value="">币种</option>
															<option value="人民币">人民币</option>
															<option value="美元">美元</option>
															<option value="欧元">欧元</option>
															<option value="日币">日币</option>
														</select>
														<p class="form-control-static" ng-show="span">{{paymentRecord.applyCurrency}}</p>
													</div>
												</div>
											</div>
											</div>
											<div class="row"  >
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">计划收款日期 <span
														class="required" aria-required="true"> * </span></label>
													<div class=""  >
														<input type="text" class="form-control" name="playPaymentDate"
														data-date-format="yyyy-mm-dd"
													    data-date-viewmode="years" size="16"
															ng-model="paymentRecord.playPaymentDate" ng-show="input" id="playPaymentDate"/>
														<p class="form-control-static" ng-show="span">{{paymentRecord.playPaymentDate}}</p>
														<div class="form-control-focus"></div>
													</div>

												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">账期</label>
													<div class="">
														<input type="text" class="form-control" name="accountPeriod"  disabled="disabled"
														data-date-format="yyyy-mm-dd"
													    data-date-viewmode="years" size="16"
															ng-model="paymentRecord.accountPeriod" ng-show="input" id="accountPeriod"/>
														<p class="form-control-static" ng-show="span">{{paymentRecord.accountPeriod}}</p>
														<div class="form-control-focus"></div>
													</div>

												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">发票方式 <span
														class="required" aria-required="true"> * </span></label>
													<div class="">
															 <select class="form-control" id="billType"   
															name="billType"  
															ng-model="paymentRecord.billType"  
															 ng-show="input">
															<option value="1">先票后款</option>
															<option value="0" >先款后票</option>
															<option value=""></option>
														</select>
														<p class="form-control-static" ng-show="span" ng-if="paymentRecord.billType=='1'">先票后款</p>
												            <p class="form-control-static" ng-show="span" ng-if="paymentRecord.billType=='0'">先款后票</p>
														<div class="form-control-focus"></div>
													</div>

												</div>
											</div>
											</div>
											<div class="row"  >
												<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">是否开票<span
														class="required" aria-required="true"> * </span></label>
													<div class="">
													
												   <select class="form-control" id="isBill"   
															name="isBill"  
															ng-model="paymentRecord.isBill"  
															 ng-show="input">
															<option value="1">是</option>
															<option value="0" >否</option>
															<option value=""></option>
														</select>
															<p class="form-control-static" ng-show="span" ng-if="paymentRecord.isBill=='1'">是</p>
												            <p class="form-control-static" ng-show="span" ng-if="paymentRecord.isBill=='0'">否</p>
													</div>
												</div>
											</div>
												<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">申请人<span
														class="required" aria-required="true"> * </span></label>
													<div class="">
														<input type="text" class="form-control" name="applicant"
															ng-model="paymentRecord.applicant" ng-show="input"/>
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-show="span">{{paymentRecord.applicant}}</p>
													</div>
												</div>
											</div>
											<!--/span-->

											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">申请部门<span
														class="required" aria-required="true"> * </span></label>
													<div class="">
														<input type="text" class="form-control" name="applyDept"
															ng-model="paymentRecord.applyDept" ng-show="input"/>
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-show="span">{{paymentRecord.applyDept}}</p>
													</div>
												</div>
											</div>
											</div>
										<div class="row"   >
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">申请日期<span
														class="required" aria-required="true"> * </span></label>
													<div class=""  >
														<input type="text" class="form-control" name="applyDate"
														data-date-format="yyyy-mm-dd" id="applyDate"
													    data-date-viewmode="years" size="16"  
															ng-model="paymentRecord.applyDate" ng-show="input"/>
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-show="span">{{paymentRecord.applyDate}}</p>
													</div>
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">备注 </label>
													<div class="" ng-if="paymentRecord.paymentType!='清关'">
														<input type="text" class="form-control" name="remark"
															ng-model="paymentRecord.remark" ng-show="input"/>
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-show="span">{{paymentRecord.remark}}</p>
													</div>
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">状态 </label>
													<div class="">
														
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-show="span">{{paymentRecord.status}}</p>
													</div>
												</div>
											</div>
										<!--/row-->
										
										</div>
										</div>
										<div ng-show="paymentRecord.paymentType=='清关'">
										<div class="row"  >
										<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">关联清关单号<span
														class="required" aria-required="true"> * </span></label>
														<div class="">
														<div class="input-group" data-target="#basicBgInfo"
															data-toggle="modal" ng-click="selectBgInfo()"
															onclick="return false;">
															<input id="orderSerial" name="qgOrBgNum" type="text" ng-show="input"
																class="form-control" ng-model="saleOrder.orderNum"
																readonly="readonly"> <span
																class="input-group-btn" ng-show="input" style="vertical-align: top;">
																<button class="btn default" type="button">
																	<i class="fa fa-search"></i>
																</button>
															</span>
															<p class="form-control-static" ng-show="span">{{paymentRecord.qgOrBgNum}}</p>
														</div>
														
													</div>
												</div>
											</div>
												<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">税率</label>
													<div class="">
														<input type="text" class="form-control" name="rate"  readonly
															ng-model="rate" ng-show="input"/>
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-show="span">{{paymentRecord.rate}}%</p>
													</div>
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">增值税额 </label>
													<div class="">
														<input type="text" class="form-control" name="addedTax"  readonly
															ng-model="addedTax" ng-show="input"/>
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-show="span">{{paymentRecord.addedTax}}</p>
													</div>
												</div>
											</div>
											</div>
											<div class="row"  >
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">关税额</label>
													<div class="">
													<input type="text" class="form-control" name="customsAmount"  readonly
															ng-model="customsAmount" ng-show="input"/>
													
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-show="span">{{paymentRecord.customsAmount}}</p>
													</div>
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">清关合计 </label>
													<div class="">
														<input type="text" class="form-control" name="totalMoney"  readonly
															ng-model="totalMoney" ng-show="input"/>
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-show="span">{{paymentRecord.applyPaymentAmount}}</p>
													</div>
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">大写 </label>
													<div class="">
														<input type="text" class="form-control" name="chnTotalMoney" readonly
															ng-model="chnTotalMoney" ng-show="input"/>
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-show="span">{{chnAmount}}</p>
													</div>
												</div>
											</div>
											</div>
											<div class="row"  >
										<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">申请收款日期 </label>
												<div class="">
														<input type="text" class="form-control" name="applyDateForBg"
														data-date-format="yyyy-mm-dd" id="applyDateForBg"
													    data-date-viewmode="years" size="16"
															ng-model="paymentRecord.applyDateForBg" ng-show="input"/>
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-show="span">{{paymentRecord.applyDate}}</p>
													</div>
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">申请人<span
														class="required" aria-required="true"> * </span></label>
													<div class="">
														<input type="text" class="form-control" name="applicant"
															ng-model="paymentRecord.applicant" ng-show="input"/>
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-show="span">{{paymentRecord.applicant}}</p>
													</div>
												</div>
											</div>
											<!--/span-->

											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">申请部门<span
														class="required" aria-required="true"> * </span></label>
													<div class="">
														<input type="text" class="form-control" name="applyDept"
															ng-model="paymentRecord.applyDept" ng-show="input"/>
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-show="span">{{paymentRecord.applyDept}}</p>
													</div>
												</div>
											</div>
											</div>
											<div class="row"  >
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">备注 </label>
													<div class=""  ng-if="paymentRecord.paymentType=='清关'">
														<input type="text" class="form-control" name="remark"
															ng-model="paymentRecord.remark" ng-show="input"/>
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-show="span">{{paymentRecord.remark}}</p>
													</div>
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">状态 </label>
													<div class="">
														
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-show="span">{{paymentRecord.status}}</p>
													</div>
												</div>
											</div>
											</div>
										</div>

										
										<div class="row"   >
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">收款方 <span
														class="required" aria-required="true"> * </span></label>
													<div class="">
														<input type="text" class="form-control" name="payee" readonly
															ng-model="paymentRecord.payee" ng-show="input" />
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-show="span">{{paymentRecord.payee}}</p>
													</div>
												</div>
											</div>
											<!--/span-->
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">联系人<span
														class="required" aria-required="true"> * </span>
													</label>
													<div class="">
														<input type="text" class="form-control" name="contact"
															ng-model="paymentRecord.contact" ng-show="input" />
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-show="span">{{paymentRecord.contact}}</p>
													</div>
												</div>
											</div>
											<!--/span-->
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">联系电话 <span
														class="required" aria-required="true"> * </span></label>
													<div class="">
														<input type="text" class="form-control" name="contactNum"
															ng-model="paymentRecord.contactNum" ng-show="input" />
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-show="span">{{paymentRecord.contactNum}}</p>
													</div>
												</div>
											</div>
											<!--/span-->
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">收款银行 <span
														class="required" aria-required="true"> * </span></label>
													<div class="">
														<select class="form-control" id="bank" name="bank"  ng-change="changeValue()"
															ng-model="paymentRecord.bank" ng-show="input" >
															<option value=""></option>
																<option ng-repeat="item in comFinances"
																value="{{item.openingBank}}">{{item.openingBank}}</option>
														</select>
														<p class="form-control-static" ng-show="span">{{paymentRecord.bank}}</p>
													</div>

												</div>
											</div>
											<!--/span-->
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">户名 <span
														class="required" aria-required="true"> * </span></label>
													<div class="">
														<input type="text" class="form-control" name="accountName"
															ng-model="paymentRecord.accountName" ng-show="input"  readonly/>
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-show="span">{{paymentRecord.accountName}}</p>
													</div>

												</div>
											</div>
											<!--/span-->
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">账号 <span
														class="required" aria-required="true"> * </span></label>
													<div class="">
														<input type="text" class="form-control"  readonly
															name="accountNumber" ng-model="paymentRecord.accountNumber"
															ng-show="input" />
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-show="span">{{paymentRecord.accountNumber}}</p>
													</div>
												</div>
											</div>
											<!--/span-->
										</div>
										<!--/row-->
									

										<!--row-->
										<div class="row">
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">订单应收金额<span
														class="required" aria-required="true"> * </span></label>
													<div class="">{{paymentRecord.orderAmount| currency:'￥'}}</div>

												</div>
											</div>
											<!--/span-->
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">预收金额 <span
														class="required" aria-required="true"> * </span></label>
													<div class="">{{0| currency:'￥'}}</div>

												</div>
											</div>
											<!--/span-->
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">已收金额 <span
														class="required" aria-required="true"> * </span></label>
													<div class="">{{paymentRecord.paiedMoney| currency:'￥'}}</div>
												</div>
											</div>
											<!--/span-->
										</div>
										<!--row-->
										<div class="row">
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">未收金额 <span
														class="required" aria-required="true"> * </span></label>
													<div class="">{{paymentRecord.orderAmount-paymentRecord.paiedMoney|
														currency:'￥'}}</div>

												</div>
											</div>
											<!--/span-->
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">已开票金额 <span
														class="required" aria-required="true"> * </span></label>
													<div class="">{{paymentRecord.billedMoney| currency:'￥'}}</div>

												</div>
											</div>
											<!--/span-->
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">未开票金额 <span
														class="required" aria-required="true"> * </span></label>
													<div class="">{{paymentRecord.orderAmount-paymentRecord.billedMoney|
														currency:'￥'}}</div>
												</div>
											</div>
											<!--/span-->
										</div>

									</div>
									<!--/row-->
								</div>
							</div>


							<div class="tab-pane fade" id="tab_1_3">
								<div class="portlet-body form">
									<form id="form_sample_4">
										<div class="table-scrollable">
											<table class="table table-bordered table-hover">
												<thead>
													<tr>
														<th>附件类型</th>
														<th>描述</th>
														<th>文件</th>
														<th>备注</th>
														<th>上传人</th>
														<th>上传时间</th>
														<th style="width: 100px;"></th>
													</tr>
												</thead>

												<tbody>
													<tr ng-repeat="_file in file track by $index"
														ng-mouseleave="hideOperation('file',$index)">
														<td>
															<p class="form-control-static">{{_file.fileType}}</p>
														</td>
														<td>
															<p class="form-control-static">{{_file.fileDescribe}}</p>
														</td>
														<td>
															<div ng-hide="fileInfoInput"
																ng-if="file[$index].file==null||file[$index].file==''"
																class="fileinput fileinput-new"
																data-provides="fileinput">
																<span class="fileinput-filename" ng-clik="downloadFile('{{file[$index]}}')">{{_file.file.substring(_file.file.indexOf("_")+1)}}</span>
															</div>
															
															<div ng-hide="fileInfoInput"
																ng-if="file[$index].file!=null&&file[$index].file!=''"
																class="fileinput fileinput-exists"
																data-provides="fileinput">
																<span class="fileinput-filename"><a href="javascript:;" ng-clik="downloadFile('{{file[$index]}}')">{{_file.file.substring(_file.file.indexOf("_")+1)}}</a></span>
																</a>
															</div> 
															
															<!-- <label ng-show="fileInfoShow"
															ng-if="file[$index].file==null||file[$index].file==''"
															class="c_edit">未上传附件</label> 
															<label
															ng-show="fileInfoShow"
															ng-if="file[$index].file!=null&&file[$index].file!=''"
															class="c_edit">
															<a href="javascript:;" ng-click="downloadFile(file[$index])">{{_file.file.substring(_file.file.indexOf("_")+1)}}</a></label> -->
																
														</td>
														
														<td><p class="form-control-static">{{_file.remark}}</p></td>
														<td><p class="form-control-static">{{_file.uploader}}</p></td>
														<td><p class="form-control-static">{{_file.uploadDate}}</p></td>


														<td ng-show="operation_f{{$index}}"><a
															href="javascript:;" class="btn red btn-sm"
															ng-hide="fileInfoInput" ng-click="deleteFile($index)">
																<i class="fa fa-close"></i>
														</a>
														</td>
													</tr>
												</tbody>
											</table>
										</div>
									</form>
								</div>

							</div>
						</div>
						
						
					
						<div class="row">
							<div class="form-group form-md-line-input">
								<label class="col-md-1 control-label" for="form_control_1">申请说明:</label>
								<div class="col-md-11">
									<textarea class="form-control" ng-model="reason"
										id="reason" name="reason" rows="1"></textarea>
									<div class="form-control-focus"></div>
									<span class="help-block">输入申请说明</span>
								</div>
							</div>
						</div>
						
					</div>
					
					
					
				</form>
				
				<form class="form-horizontal" id="applyAp_form">
					<div class="modal-footer">
						<button type="submit" ng-click="applyAp()"
							class="btn blue">
							<i class="glyphicon glyphicon-ok"></i> 申请
						</button>
						<button type="submit" ng-click="backApplyList()"
							class="btn green-meadow">
							<i class="glyphicon glyphicon-share-alt"></i> 返回
						</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
