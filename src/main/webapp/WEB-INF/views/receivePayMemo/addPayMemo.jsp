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
								data-toggle="tab">付款信息</a></li>
							<!-- <li class="bold"><a data-target="#tab_1_2" data-toggle="tab">付款信息</a> -->
							<!-- <li class="bold"><a data-target="#tab_1_2" data-toggle="tab">核销记录</a> -->
							<li class="dropdown pull-right tabdrop">
								<button type="button" onclick="goBackPage()" class="btn defualt  btn-circle  btn-sm"><i class="fa fa-reply"></i>返回</button>
							</li>							
							</li>
						</ul>
						<div class="tab-content">
							<div class="tab-pane fade active in" id="tab_1_1">
								<div class="portlet-title" style="min-height: 48px;">
									<div class="tools" style="float: right" id="noprintdiv">
										<button type="submit" ng-click="saveBasicInfo('pay')"
											class="btn green  btn-circle  btn-sm" ng-show="input">
											<i class="fa fa-save"></i> 保存
										</button>
										<button type="button" ng-click="verificateInfo('pay')"
											class="btn blue  btn-circle  btn-sm" ng-show="span"  ng-if="memoRecord.status!='2'">
											核销
										</button>
										<!-- <button ng-click="goBack()" type="button"
											class="btn defualt  btn-circle  btn-sm">
											<i class="fa fa-undo"></i> 取消
										</button> -->
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
													<label class="control-label bold">付款水单号<span
														class="required" aria-required="true"> * </span></label>
													<div class="">
														<input type="text" name="memoNum" class="form-control"
															ng-model="memoRecord.memoNum" ng-show="input">
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-show="span">{{memoRecord.memoNum}}</p>
													</div>
												</div>
											</div>
											<!--/span-->

											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">付款金额<span
														class="required" aria-required="true"> * </span></label>
													<div class="">
														<input type="text" name="moneyAmount" class="form-control"  ng-change="changeMoney()"
															ng-model="memoRecord.moneyAmount" ng-show="input">
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-show="span"  ng-if="memoRecord.currency=='USD'">{{memoRecord.moneyAmount|currency:'$'}}</p>
														<p class="form-control-static" ng-show="span"  ng-if="memoRecord.currency=='RMB'">{{memoRecord.moneyAmount|currency:'¥'}}</p>
													</div>
												</div>
											</div>
											<!--/span-->
												<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">币种<span
														class="required" aria-required="true"> * </span></label>
													<div class="">
														<select class="form-control"
															id="currency" ng-show="input"
															name="currency"
															ng-model="memoRecord.currency">
															<option value="">币种</option>
															 <option value="人民币" >人民币</option>
                                            				<option value="美元" >美元</option>
                                            				<option value="欧元" >欧元</option>
                                            				<option value="英镑" >英镑</option>
														</select>
														<p class="form-control-static" ng-show="span">{{memoRecord.currency}}</p>
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
													<label class="control-label bold">支付方式<span
														class="required" aria-required="true"> * </span></label>
													<div class="">
														<select class="form-control"
															id="paymentType" ng-show="input"
															name="paymentType"
															ng-model="memoRecord.paymentStyle">
															<option value="">支付方式</option>
															<option value="现款">现款</option>
															<option value="承兑汇款">承兑汇款</option>
															<option value="电子承兑">电子承兑</option>
														</select>
														<p class="form-control-static" ng-show="span">{{memoRecord.paymentStyle}}</p>
														<div class="form-control-focus"></div>
													</div>
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">付款日期<span
														class="required" aria-required="true"> * </span></label>
													<div class="">
														<input type="text" class="form-control" name="paymentDate"  
														data-date-format="yyyy-mm-dd" id="paymentDate"
													    data-date-viewmode="years" size="16"
															ng-model="memoRecord.paymentDate" ng-show="input"/>
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-show="span">{{memoRecord.paymentDate}}</p>
													</div>

												</div>
											</div>
											<!--/span-->
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">收款方<span
														class="required" aria-required="true"> * </span></label>
													<div class=""    ng-hide="span">
														<select class="form-control"
															name="buyComId"  id="buyComId"  
															ng-model="memoRecord.buyComId"    
															ng-change="getBankInfo()" ng-show="input">
															<option ng-repeat="item in companyList"
																value="{{item.comId}}">{{item.comName}}</option>
															<option value=""></option>
														</select>
													</div>
													<div class="form-control-focus"></div>
														<p class="form-control-static" ng-show="span">
															{{memoRecord.comName}}</p>
												</div>
											</div>
											</div> <!--/row-->
											<div class="row">
										<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">收款银行<span
														class="required" aria-required="true"> * </span></label>
													<div class="">
														<!-- <select class="form-control" id="bank" name="bank"  ng-change="changeValue()"
															ng-model="memoRecord.bank" ng-show="input" >
															<option value=""></option>
																<option ng-repeat="item in comFinances"
																value="{{item.openingBank}}">{{item.openingBank}}</option>
														</select>
														<p class="form-control-static" ng-show="span">{{memoRecord.bank}}</p> -->
														<div class="input-group"  >
													<input type="text" name="bank1"
															class="form-control"
															ng-model="memoRecord.bank"    ng-if="showSXf!='1'"  ng-show="input"/>
															<div  ng-show="showSXf=='1'">
																<select class="form-control" id="bank" name="bank"   ng-change="changeValue()"
															ng-model="memoRecord.bank" ng-show="input" >
															<option value=""></option>
																<option ng-repeat="item in comFinances"
																value="{{item.openingBank}}">{{item.openingBank}}</option>
														</select>
														</div>
															<span ng-show="input"  class="input-group-btn" ng-click="showSX()"
																style="vertical-align: top;">
																<button class="btn default" type="button"  ng-if="showSXf!='1'">
																	筛选
																</button>
																	<button class="btn default" type="button"  ng-if="showSXf=='1'">
																	输入
																</button>
															</span>
														</div>
													</div>
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">收款账号<span
														class="required" aria-required="true"> * </span></label>
													<div class="">
													<input type="text" class="form-control"  
															name="accountNumber" ng-model="memoRecord.accountNumber"
															ng-show="input" />
														<!-- <input type="text" class="form-control"  readonly   ng-if="showSXf=='1'"
															name="accountNumber" ng-model="memoRecord.accountNumber"
															ng-show="input" />
															<input type="text" class="form-control"    ng-if="showSXf!='1'"
															name="accountNumber1" ng-model="memoRecord.accountNumber"
															ng-show="input" /> -->
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-show="span">{{memoRecord.accountNumber}}</p>
													</div>

												</div>
											</div>
											<!--/span-->
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">户名<span
														class="required" aria-required="true"> * </span></label>
													<div class="">
														<!-- <input type="text" class="form-control" name="accountName"  ng-if="showSXf=='1'"
															ng-model="memoRecord.accountName" ng-show="input"  readonly/>
															<input type="text" class="form-control" name="accountName1"  ng-if="showSXf!='1'"
															ng-model="memoRecord.accountName" ng-show="input"  /> -->
															<input type="text" class="form-control" name="accountName"  
															ng-model="memoRecord.accountName" ng-show="input"  />
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-show="span">{{memoRecord.accountName}}</p>
													</div>
												</div>
											</div>
											</div> <!--/row-->
											<div class="row">
										<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">支付凭证<span
														class="required" aria-required="true"> * </span></label>
													<div class="">
														<input type="file" id="signContract" ng-model="file" name="file"  ng-show="input" 
									class="form-control" /> 
			<label ng-if="memoRecord.paymentVoucher==null||memoRecord.paymentVoucher==''" class="c_edit"   ng-show="span">未上传支付凭证</label>
			<label ng-if="memoRecord.paymentVoucher!=null&&memoRecord.paymentVoucher!=''" class="c_edit" ><a href="javascript:;" ng-click="download(memoRecord.paymentVoucher)">{{memoRecord.paymentVoucher.substring(memoRecord.paymentVoucher.indexOf("_")+1)}}</a></label>
													</div>
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">备注</label>
													<div class="">
														<input type="text" name="remark" class="form-control"
															 ng-model="memoRecord.remark" ng-show="input" />
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-show="span">{{memoRecord.remark}}</p>
													</div>

												</div>
											</div>
											<!--/span-->
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">已核销金额</label>
													<div class="">
														<input type="text" class="form-control"  readonly
															name="verificationMoneyAmount" ng-model="memoRecord.verificationMoneyAmount"
															ng-show="input" />
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-show="span"  ng-if="memoRecord.currency=='USD'">{{memoRecord.verificationMoneyAmount|currency:'$'}}</p>
														<p class="form-control-static" ng-show="span"  ng-if="memoRecord.currency=='RMB'">{{memoRecord.verificationMoneyAmount|currency:'¥'}}</p>
													</div>
												</div>
											</div>
											</div> <!--/row-->
												<div class="row">
										<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">水单余额</label>
													<div class="">
														<input type="text" class="form-control"  readonly
															name="remainMoneyAmount" ng-model="memoRecord.remainMoneyAmount"
															ng-show="input" />
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-show="span"  ng-if="memoRecord.currency=='USD'">{{memoRecord.moneyAmount-memoRecord.verificationMoneyAmount|currency:'$'}}</p>
														<p class="form-control-static" ng-show="span"  ng-if="memoRecord.currency=='RMB'">{{memoRecord.moneyAmount-memoRecord.verificationMoneyAmount|currency:'¥'}}</p>
													</div>
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">核销状态</label>
													<div class="">
														<p class="form-control-static"  ng-if="memoRecord.status=='0'||memoRecord.status==undefined">待核销</p>
														<p class="form-control-static"   ng-if="memoRecord.status=='2'">已完成</p>
														<p class="form-control-static"   ng-if="memoRecord.status=='1'">部分核销</p>
													</div>

												</div>
											</div>
											
											</div> <!--/row-->
									</div>
									<!--/row-->
								</div>
							</div>


							
					<div class="tab-pane fade" id="tab_1_2">
								<div class="portlet-body form">
									<!-- BEGIN FORM-->
									核销记录
								</div>
							</div>

							
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<jsp:include page="selectPayPaymentRecord.jsp"></jsp:include>


<script type="text/javascript">
	$('#paymentDate').datepicker({
		rtl : App.isRTL(),
		orientation : "left",
		autoclose : true,
		dateFormat : "yyyy-mm-dd",
		language : "zh-CN"
	}); 

	
</script>
