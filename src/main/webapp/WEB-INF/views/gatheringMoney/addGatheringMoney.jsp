<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- BEGIN PAGE HEADER-->
<div class="row" id="saleOrderPrint">
	<div class="col-md-12">
		<!-- BEGIN EXAMPLE TABLE PORTLET-->
		<div class="portlet bordered">
			<div class="portlet-body">
				<form action="#" id="form_sample_1" class="">
					<div class="portlet light ">
						<ul class="nav nav-tabs">
							<li class="active bold"><a data-target="#tab_1_1"
								data-toggle="tab">应收信息</a></li>
							<!-- <li class="bold"><a data-target="#tab_1_2" data-toggle="tab">付款信息</a> -->
							<!-- <li class="bold"><a data-target="#tab_1_2" data-toggle="tab">核销记录</a> -->
							<!-- <li class="bold"><a data-target="#tab_1_3" data-toggle="tab">附件</a> -->
							<li class="dropdown pull-right tabdrop">
								<button type="button" onclick="goBackPage()" class="btn defualt  btn-circle  btn-sm"><i class="fa fa-reply"></i>返回</button>
							</li>							
							</li>
						</ul>
						<div class="tab-content">
							<div class="tab-pane fade active in" id="tab_1_1">
								<div class="portlet-title" style="min-height: 48px;">
									<div class="tools" style="float: right" id="noprintdiv">
										<button type="submit" ng-click="saveBasicInfo('receive')"
											class="btn green  btn-circle  btn-sm" ng-show="input">
											<i class="fa fa-save"></i> 保存
										</button>
										<!-- <button type="submit" ng-click="editBasicInfo('receive')"
											class="btn green  btn-circle  btn-sm" ng-show="inputEdit">
											<i class="fa fa-save"></i> 编辑
										</button> -->
										<button type="submit" ng-click="verificateInfo()"
											class="btn green  btn-circle  btn-sm" ng-show="span"   ng-if="paymentRecord.status!='2'">
											<i class="fa fa-save"></i> 核销
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
													<label class="control-label bold">应收账单号<span
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
													<label class="control-label bold">关联销售订单号<span
														class="required" aria-required="true"> * </span></label>
													<div class="">
														<div class="input-group" data-target="#basicMaterielInfo"
															data-toggle="modal" ng-click="selectMateriel()"
															onclick="return false;">
															<input id="orderNum" name="orderNum" type="text" ng-show="input"
																class="form-control" ng-model="saleOrder.orderNum"
																readonly="readonly"> <span
																class="input-group-btn" ng-show="input" style="vertical-align: top;">
																<button class="btn default" type="button">
																	<i class="fa fa-search"></i>
																</button>
															</span>
														</div>
														<p class="form-control-static" ng-show="span">{{paymentRecord.orderNum}}</p>
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
															<option value="销售收款">销售收款</option>
															<option value="服务费收款">服务费收款</option>
															<option value="采购退款">采购退款</option>
															<option   ng-if="isBG"    value="报关">报关</option><!--是否报关单  -->
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
														<input type="text" class="form-control" name="orderDate"   disabled="disabled"
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
															<option value="日元">日元</option>
														</select>
														<p class="form-control-static" ng-show="span">{{paymentRecord.applyCurrency}}</p>
													</div>
												</div>
											</div>
											</div> <!--/row-->
											<div ng-show="paymentRecord.paymentType!='报关'">
											<div class="row"  >
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">支付节点<span
														class="required" aria-required="true"> * </span></label>
													<!-- <div class=""  ng-if="paymentRecord.paymentType!='报关'&&clauseSettlementList.length!=0"> -->
														<select class="form-control" id="paymentNode"
															name="paymentNode"   
															ng-model="paymentRecord.paymentNode"
														    ng-show="input" ng-change="selectPaymentNode(paymentRecord.paymentNode)">
															<!-- <option value="">支付节点</option>
															<option ng-repeat="item in clauseSettlementList" value="{{item.deliveryNode}}">{{item.deliveryNode}}</option> -->
															
															<option value="合同签订" >合同签订</option>
				                                             <!--  <option value="提货前" >提货前</option>
				                                              <option value="发货后" >发货后</option>
				                                              <option value="收货后" >收货后</option>
				                                              <option value="验收后" >验收后</option> -->
				                                              <option value="发货前" >发货前</option>
				                                              <option value="入库后" >入库后</option>
				                                             <!--  <option value="出库后" >出库后</option>
				                                              <option value="质保期满" >质保期满</option> -->
				                                              <option value="收到委托方付款后" >收到委托方付款后</option>
				                                              <option value="其它" >其它</option>
														</select>
														
													<!-- </div> -->
													<!-- <div class=""  ng-if="clauseSettlementList.length==0">
														<input type="text" name="paymentNode" class="form-control" 
															ng-model="paymentRecord.paymentNode" ng-show="input"/>
														
													</div> -->
													<p class="form-control-static" ng-show="span">{{paymentRecord.paymentNode}}</p>
												</div>
											</div>
												<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">支付类型<span
														class="required" aria-required="true"> * </span></label>
													<!-- <div class=""  ng-if="clauseSettlementList.length!=0"> -->
														<select class="form-control" id="payType"   
															name="payType"  
															ng-model="paymentRecord.payType"
														    ng-show="input">
															<!-- <option value="">支付类型</option>
															
															<option value="{{item.paymentType}}" ng-repeat="item in clauseSettlementList"  ng-bind="item.paymentType"  ></option> -->
															<option value="预付款" >预付款</option>
				                                               <option value="中期款" >中期款</option>
				                                               <option value="尾款" >尾款</option>
				                                               <option value="全款" >全款</option>
														</select>
														
													<!-- </div> -->
													<!-- <div class=""  ng-if="clauseSettlementList.length==0">
														<input type="text" name="payType" class="form-control" 
															ng-model="paymentRecord.payType" ng-show="input"/>
													</div> -->
													<p class="form-control-static" ng-show="span">{{paymentRecord.payType}}</p>
												</div>
											</div>
											<!--/span-->
											
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">支付比率<span
														class="required" aria-required="true"> * </span></label>
													<!-- <div class=""  ng-if="clauseSettlementList.length!=0">
														<input type="text" name="deliveryRate" class="form-control" ng-if="paymentRecord.paymentType!='报关'"
															readonly ng-model="paymentRecord.deliveryRate" ng-show="input"/>
														<div class="form-control-focus"></div>
														
													</div> -->
													<!-- <div class=""  ng-if="clauseSettlementList.length==0"> -->
														<input type="text" name="deliveryRate" class="form-control"   ng-blur="setApplyPaymentAmount(paymentRecord,'deliveryRate')"
															ng-model="paymentRecord.deliveryRate" ng-show="input"/>
													<!-- </div> -->
													<p class="form-control-static" ng-show="span">{{applyPaymentAmountChn}}</p>
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
														<p class="form-control-static" ng-show="span">{{applyPaymentAmountChn}}</p>
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
													<label class="control-label bold">申请部门</label>
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
													<div class="" ng-if="paymentRecord.paymentType!='报关'">
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
														<p class="form-control-static" ng-if="paymentRecord.status=='0'">待核销</p>
														<p class="form-control-static"  ng-if="paymentRecord.status=='1'">部分核销</p>
														<p class="form-control-static"  ng-if="paymentRecord.status=='2'">已完成 </p>
													</div>
												</div>
											</div>
										<!--/row-->
										
										</div>
										</div>
										<div ng-show="paymentRecord.paymentType=='报关'">
										<div class="row"  >
										<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">关联报关单号<span
														class="required" aria-required="true"> * </span></label>
														<div class="">
														<div class="input-group" data-target="#basicBgInfo"
															data-toggle="modal" ng-click="selectBgInfo()"
															onclick="return false;">
															<input id="orderSerial" name="qgOrBgNum" type="text" ng-show="input"
																class="form-control" ng-model="paymentRecord.qgOrBgNum"
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
															ng-model="paymentRecord.rate" ng-show="input"/>
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
															ng-model="paymentRecord.addedTax" ng-show="input"/>
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
															ng-model="paymentRecord.customsAmount" ng-show="input"/>
													
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-show="span">{{paymentRecord.customsAmount}}</p>
													</div>
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">报关合计 </label>
													<div class="">
														<input type="text" class="form-control" name="totalMoney"  readonly
															ng-model="paymentRecord.totalMoney" ng-show="input"/>
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
														<p class="form-control-static" ng-show="span">{{applyPaymentAmountChn}}</p>
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
													<div class=""  ng-if="paymentRecord.paymentType=='报关'">
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
														<<p class="form-control-static"  ng-if="memoRecord.status=='0'||memoRecord.status==undefined">待核销</p>
														<p class="form-control-static"   ng-if="memoRecord.status=='2'">已完成</p>
														<p class="form-control-static"   ng-if="memoRecord.status=='1'">部分核销</p>
													</div>
												</div>
											</div>
											</div>
										</div>

										
										<div class="row"   >
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">付款方 <span
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
														<!-- <input type="text" class="form-control" name="contact"
															ng-model="paymentRecord.contact" ng-show="input" />
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-show="span">{{paymentRecord.contact}}</p> -->
														<select class="form-control" id="contact" name="contact"  ng-change="changeContactValue()"
															ng-model="paymentRecord.contact" ng-show="input" >
															<option value=""></option>
																<option ng-repeat="item in comContacts"
																value="{{item.contactName}}">{{item.contactName}}</option>
														</select>
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
															ng-model="paymentRecord.contactNum" ng-show="input"  readonly/>
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-show="span">{{paymentRecord.contactNum}}</p>
													</div>
												</div>
											</div>
											<!--/span-->
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">付款银行 <span
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


							<!-- <div class="tab-pane fade" id="tab_1_2">
								<div class="portlet-body form">
									BEGIN FORM
									<div class="form-body">
										<div class="alert alert-danger display-hide">
											<button class="close" data-close="alert"></button>
											请先输入正确数据！
										</div>
										<div class="row">
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">付款方 <span
														class="required" aria-required="true"> * </span></label>
													<div class="">
														<input type="text" class="form-control" name="payee"
															ng-model="paymentRecord.payee" ng-show="input"/>
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-show="span">{{paymentRecord.payee}}</p>
													</div>
												</div>
											</div>
											/span
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">付款方联系人<span
														class="required" aria-required="true"> * </span> </label>
													<div class="">
														<input type="text" class="form-control" name="contact"
															ng-model="paymentRecord.contact" ng-show="input"/>
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-show="span">{{paymentRecord.contact}}</p>
													</div>
												</div>
											</div>
											/span
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">联系电话 <span
														class="required" aria-required="true"> * </span></label>
													<div class="">
														<input type="text" class="form-control" name="contactNum"
															ng-model="paymentRecord.contactNum" ng-show="input"/>
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-show="span">{{paymentRecord.contactNum}}</p>
													</div>
												</div>
											</div>
											/span
										</div>
										/row
										<div class="row">
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">银行 <span
														class="required" aria-required="true"> * </span></label>
													<div class="">
														<select class="form-control" id="bank"
															name="bank"
															ng-model="paymentRecord.bank"
														    ng-show="input" ng-show="input">
															<option value="">收款银行</option>
															<option value="工商银行">工商银行</option>
															<option value="交通银行">交通银行</option>
														</select>
														<p class="form-control-static" ng-show="span">{{paymentRecord.bank}}</p>
													</div>

												</div>
											</div>
											/span
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">户名 <span
														class="required" aria-required="true"> * </span></label>
													<div class="">
														<input type="text" class="form-control" name="accountName"
															ng-model="paymentRecord.accountName" ng-show="input"/>
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-show="span">{{paymentRecord.accountName}}</p>
													</div>

												</div>
											</div>
											/span
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">账号 <span
														class="required" aria-required="true"> * </span></label>
													<div class="">
														<input type="text" class="form-control" name="accountNumber"
															ng-model="paymentRecord.accountNumber" ng-show="input"/>
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-show="span">{{paymentRecord.accountNumber}}</p>
													</div>
												</div>
											</div>
											/span
										</div>

										row
										<div class="row">
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">应收金额 <span
														class="required" aria-required="true"> * </span></label>
													<div class="">{{saleOrder.orderAmount| currency:'￥'}}</div>

												</div>
											</div>
											/span
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">预收金额 <span
														class="required" aria-required="true"> * </span></label>
													<div class="">{{0| currency:'￥'}}</div>

												</div>
											</div>
											/span
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">已收金额 <span
														class="required" aria-required="true"> * </span></label>
													<div class="">{{saleOrder.paiedMoney| currency:'￥'}}</div>
												</div>
											</div>
											/span
										</div>
										row
										<div class="row">
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">未收金额 <span
														class="required" aria-required="true"> * </span></label>
													<div class="">{{saleOrder.orderAmount-saleOrder.paiedMoney| currency:'￥'}}</div>

												</div>
											</div>
											/span
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">已开票金额 <span
														class="required" aria-required="true"> * </span></label>
													<div class="">{{saleOrder.billedMoney| currency:'￥'}}</div>

												</div>
											</div>
											/span
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">未开票金额 <span
														class="required" aria-required="true"> * </span></label>
													<div class="">{{saleOrder.orderAmount-saleOrder.billedMoney| currency:'￥'}}</div>
												</div>
											</div>
											/span
										</div>
									</div>
								</div>
							</div> -->
					<div class="tab-pane fade" id="tab_1_2">
								<div class="portlet-body">
			<div class="row">
				<div class="col-md-6 col-sm-6">
					<div class="dataTables_length" id="sample_5_length">
						<label>每页显示 <select name="sample_5_length"
							aria-controls="sample_5" ng-model="pageSize" ng-change="createDispalyList()"
							class="form-control input-sm input-xsmall input-inline">
							<option value="5">5</option>
							<option value="10">10</option>
							<option value="15">15</option>
							<option value="30">30</option>
							<option value="99999">All</option>
							</select> 条数据
						</label>
					</div>
				</div>
				<div class="col-md-6 col-sm-6">
					<div id="sample_5_filter" style="text-align: right;">
						<label>查询:<input type="search" ng-model="queryStr"  ng-change="queryForPage()"
							class="form-control input-sm input-small input-inline"
							placeholder="" aria-controls="sample_5"></label>
					</div>
				</div>
			</div>
			<div class="table-scrollable">
				<table id="verificationRecordTable"
					class="table table-striped table-bordered table-advance table-hover">
					<thead>
						<tr>
							<th style="text-align: center">收款水单号</th>
							<th style="text-align: center">收款金额</th>
							<th style="text-align: center">币种</th>
							<th style="text-align: center">收款方式</th>
							<th style="text-align: center">到账日期</th>
							<th style="text-align: center">核销金额</th>
							<th style="text-align: center">核销日期</th>
							<th style="text-align: center" >核销状态</th>
						</tr>
						
					</thead>
					<tbody>
						<tr
							ng-repeat="verificationRecord in dispalyVerificationRecord  track by $index">
							<td style="text-align: center">{{verificationRecord.memoRecord.memoNum}}</td>
							<td style="text-align: center">{{verificationRecord.memoRecord.moneyAmount|currency:''}}</td>
							<td style="text-align: center">{{verificationRecord.memoRecord.currency}}</td>
							<td style="text-align: center">{{verificationRecord.memoRecord.paymentStyle}}</td>
							<td style="text-align: center">{{verificationRecord.memoRecord.paymentDate}}</td>
							<td style="text-align: center">{{verificationRecord.moneyAmount|currency:''}}</td>
							<td  style="text-align: center">{{verificationRecord.createTime}}</td>
							<td style="text-align: center" ng-if="verificationRecord.memoRecord.status=='2'">已完成</td>
							<td style="text-align: center" ng-if="verificationRecord.memoRecord.status=='1'">部分核销</td>
						</tr>
						<tr
							ng-if="dispalyVerificationRecord.length==0">
							<td colspan="7" align="center">没有符合条件的核销记录信息</td>
						</tr>
					</tbody>
					<tfoot>
													<tr>
														<td></td>
														<td style="text-align: center">收款单金额:{{paymentRecord.applyPaymentAmount|currency:''}}</td>
														<td style="text-align: center">已核销金额:{{totalPaymentAmount|currency:''}}</td>
														<td style="text-align: center">未核销金额:{{paymentRecord.applyPaymentAmount-paymentRecord.paymentAmount|currency:''}}</td>
														<td  style="text-align: center" ng-if="paymentRecord.status=='0'">核销状态:待核销</td>
														<td  style="text-align: center" ng-if="paymentRecord.status=='1'">核销状态:部分核销</td>
														<td  style="text-align: center" ng-if="paymentRecord.status=='2'">核销状态:已完成 </td>
														<td></td>
														<td></td>
													</tr>
												</tfoot>
				</table>
			</div>
			
			<div class="row">
				<div class="col-md-5 col-sm-5">
					<div class="dataTables_info" id="sample_5_info" role="status"
						aria-live="polite">从 {{(pageIndex-1)*pageSize+1>filterVerificationRecord.length?filterVerificationRecord.length:(pageIndex-1)*pageSize+1}}
						到 {{pageIndex*pageSize>filterVerificationRecord.length?filterVerificationRecord.length:pageIndex*pageSize}} /共 {{filterVerificationRecord.length}} 条数据（从{{verificationList.length}}条数据中筛选）</div>
				</div>
				<div class="col-md-7 col-sm-7">
					<div  style="text-align: right;" id="sample_5_paginate">
						<ul class="pagination" style="visibility: visible;">
							<li class="prev" ng-if="pageIndex>1"><a href="#" ng-click="link2PreviousPage()" title="前一页"><i
									class="fa fa-angle-left"></i></a></li>
							<li class="prev disabled" ng-if="1>=pageIndex"><a href="#" title="前一页"><i
									class="fa fa-angle-left"></i></a></li>
							<li ng-if="pageIndex-2>0"><a href="#" ng-click="link2ThisPage(pageIndex-2)">{{pageIndex-2}}</a></li>
							<li ng-if="pageIndex-1>0"><a href="#" ng-click="link2ThisPage(pageIndex-1)">{{pageIndex-1}}</a></li>
							<li class="active"><a href="#">{{pageIndex}}</a></li>
							<li ng-if="totalPage>pageIndex"><a href="#" ng-click="link2ThisPage(pageIndex+1)">{{pageIndex+1}}</a></li>
							<li ng-if="totalPage>pageIndex+1"><a href="#" ng-click="link2ThisPage(pageIndex+2)">{{pageIndex+2}}</a></li>
							<li class="next disabled" ng-if="pageIndex>=totalPage"><a href="#" ><i
									class="fa fa-angle-right"></i></a></li>
							<li class="next" ng-if="totalPage>pageIndex"><a href="#" ng-click="link2NextPage()" title="后一页"><i
									class="fa fa-angle-right"></i></a></li>
						</ul>
					</div>
				</div>
			
		</div>
							</div>
							</div>

							<div class="tab-pane fade" id="tab_1_3">
								<div class="portlet-title" style="min-height: 48px;">
									<div class="tools" style="float: right" id="noprintdiv">
										<button type="submit" ng-click="saveFile()"
											class="btn green  btn-circle  btn-sm" ng-show="inputFile">
											<i class="fa fa-save"></i> 保存
										</button>
										<button ng-click="goBack()" type="button"
											class="btn defualt  btn-circle  btn-sm">
											<i class="fa fa-undo"></i> 取消
										</button>
									</div>
								</div>
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
														ng-mouseover="showOperation('file',$index)"
														ng-mouseleave="hideOperation('file',$index)">
														<td>
														<p class="form-control-static" ng-show="fileInfoShow">{{_file.fileType}}</p>
														<select class="form-control"
															id="fileType[$index]" name="fileType"
															class="form-control" ng-hide="fileInfoInput"
															ng-model="file[$index].fileType">
																<option value="">类型</option>
																<option value="付款凭证">付款凭证</option>
																<option value="图纸">图纸</option>
																<option value="其他附件">其他附件</option>
														</select>
														</td>
														<td>
														<p class="form-control-static" ng-show="fileInfoShow">{{_file.fileDescribe}}</p>
														<input type="text" id="fileDescribe[$index]"
															name="fileDescribe" class="form-control"
															ng-hide="fileInfoInput"
															ng-model="file[$index].fileDescribe">
														</td>
														<td>
															<div ng-hide="fileInfoInput"
																ng-if="file[$index].file==null||file[$index].file==''"
																class="fileinput fileinput-new"
																data-provides="fileinput">
																<span class="btn blue btn-outline btn-file"> <span
																	class="fileinput-new">上传附件</span> <span
																	class="fileinput-exists">更改</span> <input type="file"
																	name="..." nv-file-select uploader="uploader"
																	onchange="angular.element(this).scope().up(this.files[0])"
																	ng-model="file[$index].file"
																	ng-click="uploadFile($index)">
																</span> <span class="fileinput-filename">{{_file.file.substring(_file.file.indexOf("_")+1)}}</span>
																&nbsp; <a href="javascript:;"
																	class="close fileinput-exists"
																	ng-click="removefile($index)" data-dismiss="fileinput">
																</a>
															</div>
															<div ng-hide="fileInfoInput"
																ng-if="file[$index].file!=null&&file[$index].file!=''"
																class="fileinput fileinput-exists"
																data-provides="fileinput">
																<span class="btn blue btn-outline btn-file"> <span
																	class="fileinput-new">上传附件</span> <span
																	class="fileinput-exists">更改</span> <input type="file"
																	name="..." nv-file-select uploader="uploader"
																	onchange="angular.element(this).scope().up(this.files[0])"
																	ng-model="file[$index].file"
																	ng-click="uploadFile($index)">
																</span> <span class="fileinput-filename">{{_file.file.substring(_file.file.indexOf("_")+1)}}</span>
																&nbsp; <a href="javascript:;"
																	class="close fileinput-exists"
																	ng-click="removefile($index)" data-dismiss="fileinput">
																</a>
															</div> <label ng-show="fileInfoShow"
															ng-if="file[$index].file==null||file[$index].file==''"
															class="c_edit">未上传附件</label> <label
															ng-show="fileInfoShow"
															ng-if="file[$index].file!=null&&file[$index].file!=''"
															class="c_edit"><a href="javascript:;"
																ng-click="downloadFile(file[$index])">{{_file.file.substring(_file.file.indexOf("_")+1)}}</a></label>
														</td>
														<td>
														<p class="form-control-static" ng-show="fileInfoShow">{{_file.remark}}</p>
														<input type="text" name="remark[$index]"
															name="remark" class="form-control"
															ng-hide="fileInfoInput" ng-model="file[$index].remark">
														</td>
														<td><p class="form-control-static">{{_file.uploader}}</p></td>
														<td><p class="form-control-static">{{_file.uploadDate}}</p></td>
																

														<td ng-show="operation_f{{$index}}"><a
															href="javascript:;" class="btn red btn-sm"
															ng-hide="fileInfoInput" ng-click="deleteFile($index)">
																<i class="fa fa-close"></i>
														</a></td>
													</tr>
												</tbody>
											</table>
										</div>
										<div class="form-actions right">
											<a class="btn blue btn-sm" ng-hide="fileInfoInput"
												ng-click="addFile()"> <i class="fa fa-plus"></i> 增加
											</a>
										</div>
									</form>
								</div>

							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>

<div id="basicMaterielInfo" class="modal fade bs-modal-lg" tabindex="-1"
	data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true"></button>
				<h4 class="modal-title">选择销售订单</h4>
			</div>
			<div class="modal-body">
				<table class="table table-striped table-bordered table-hover table-checkable order-column" id="sample_21">
	                    <thead>
	                        <tr>
	                            <th style="text-align: center"><input name="select_all" id="example-select-all" type="checkbox"/></th>
	                            <th> 销售订单号 </th>
	                            <th> 采购方 </th>
	                            <th> 采购商品 </th>
	                            <th> 金额 </th>
	                            <th> 配送 </th>
	                            <th> 服务模式 </th>
	                            <th> 关联采购合同 </th>
	                            <th> 关联销售单 </th>
	                            <th> 下单日期 </th>
	                        </tr>
	                    </thead>
	                    <tbody>
	                    </tbody>
	                </table>
			</div>
			<div class="modal-footer">
				<button type="button" data-dismiss="modal"
					class="btn dark btn-outline">取消</button>
				<button type="button" ng-click="confirmSelect()" class="btn green">确定
				</button>
			</div>
		</div>
	</div>
</div>
<div id="basicBgInfo" class="modal fade bs-modal-lg" tabindex="-1"
	data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true"></button>
				<h4 class="modal-title">选择报关单</h4>
			</div>
			<div class="modal-body">
				<table class="table table-striped table-bordered table-hover table-checkable order-column" id="sample_22">
	                    <thead>
	                        <tr>
	                            <th style="text-align: center"><input name="select_all" id="example-select-all" type="checkbox"/></th>
	                            <th> 报关单号 </th>
	                            <th> 税率 </th>
	                            <th> 增值税额 </th>
	                            <th> 关税额 </th>
	                            <th> 报关合计 </th>
	                        </tr>
	                    </thead>
	                    <tbody>
	                    </tbody>
	                </table>
			</div>
			<div class="modal-footer">
				<button type="button" data-dismiss="modal"
					class="btn dark btn-outline">取消</button>
				<button type="button" ng-click="confirmSelectBgInfo()" class="btn green">确定
				</button>
			</div>
		</div>
	</div>
</div>
<jsp:include page="selectReceiveMemo.jsp"></jsp:include>
<script type="text/javascript">
	$('#playArrivalDate').datepicker({
		rtl : App.isRTL(),
		orientation : "left",
		autoclose : true,
		dateFormat : "yyyy-mm-dd",
		language : "zh-CN"
	});

	$('#playWarehouseDate').datepicker({
		rtl : App.isRTL(),
		orientation : "left",
		autoclose : true,
		dateFormat : "yyyy-mm-dd",
		language : "zh-CN"
	});

	$('#approvalDate').datepicker({
		rtl : App.isRTL(),
		orientation : "left",
		autoclose : true,
		dateFormat : "yyyy-mm-dd",
		language : "zh-CN"
	});

	$('#orderDate').datepicker({
		rtl : App.isRTL(),
		orientation : "left",
		autoclose : true,
		dateFormat : "yyyy-mm-dd",
		language : "zh-CN"
	});

	$('#playPaymentDate').datepicker({
		rtl : App.isRTL(),
		orientation : "left",
		autoclose : true,
		dateFormat : "yyyy-mm-dd",
		language : "zh-CN"
	}); 

	$('#applyDate').datepicker({
		rtl : App.isRTL(),
		orientation : "left",
		autoclose : true,
		dateFormat : "yyyy-mm-dd",
		language : "zh-CN"
	});
	$('#applyDateForBg').datepicker({
		rtl : App.isRTL(),
		orientation : "left",
		autoclose : true,
		dateFormat : "yyyy-mm-dd",
		language : "zh-CN"
	});
	

</script>
