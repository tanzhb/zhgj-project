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
							<li class="bold"><a data-target="#tab_1_2" data-toggle="tab">核销记录</a>
													
							</li>
						</ul>
						<div class="tab-content">
							<div class="tab-pane fade active in" id="tab_1_1">
								<div class="portlet-title" style="min-height: 48px;">
									<div class="tools" style="float: right" id="noprintdiv">
										
										<button   type="button" ng-click="verificateInfo('pay')" 
											class="btn blue btn-sm btn-circle"  ng-show="span"  ng-if="memoRecord.status!='2'">
											<i class="fa fa-plus"></i>核销
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
															<option value="USD">USD</option>
															<option value="RMB">RMB</option>
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
														<select class="form-control" id="bank" name="bank"  ng-change="changeValue()"
															ng-model="memoRecord.bank" ng-show="input" >
															<option value=""></option>
																<option ng-repeat="item in comFinances"
																value="{{item.openingBank}}">{{item.openingBank}}</option>
														</select>
														<p class="form-control-static" ng-show="span">{{memoRecord.bank}}</p>
													</div>
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">收款账号</label>
													<div class="">
														<input type="text" class="form-control"  readonly
															name="accountNumber" ng-model="memoRecord.accountNumber"
															ng-show="input" />
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-show="span">{{memoRecord.accountNumber}}</p>
													</div>

												</div>
											</div>
											<!--/span-->
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">户名</label>
													<div class="">
														<input type="text" class="form-control" name="accountName"
															ng-model="memoRecord.accountName" ng-show="input"  readonly/>
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
														<p class="form-control-static" ng-show="span"  ng-if="memoRecord.status=='0'">待核销</p>
														<p class="form-control-static" ng-show="span"  ng-if="memoRecord.status=='2'">已完成</p>
														<p class="form-control-static" ng-show="span"  ng-if="memoRecord.status=='1'">部分核销</p>
													</div>

												</div>
											</div>
											
											</div> <!--/row-->
									</div>
									<!--/row-->
								</div>
							</div>


							
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
							<th style="text-align: center">应付账单号</th>
							<th style="text-align: center">支付类型</th>
							<th style="text-align: center">应付金额</th>
							<th style="text-align: center">应付日期</th>
							<th style="text-align: center">核销金额</th>
							<th style="text-align: center">核销日期</th>
							<th style="text-align: center" >核销状态</th>
						</tr>
						
					</thead>
					<tbody>
						<tr
							ng-repeat="verificationRecord in dispalyVerificationRecord  track by $index">
							<td style="text-align: center">{{verificationRecord.paymentRecord.paymentNum}}</td>
							<td style="text-align: center">{{verificationRecord.paymentRecord.paymentType}}</td>
							<td style="text-align: center">{{verificationRecord.paymentRecord.applyPaymentAmount|currency:''}}</td>
							<td style="text-align: center">{{verificationRecord.paymentRecord.playPaymentDate==null?verificationRecord.paymentRecord.applyDate:verificationRecord.paymentRecord.playPaymentDate}}</td>
							<td style="text-align: center">{{verificationRecord.paymentRecord.paymentAmount|currency:''}}</td>
							<td  style="text-align: center">{{verificationRecord.paymentRecord.paymentDate}}</td>
							<td style="text-align: center" ng-if="verificationRecord.paymentRecord.status=='2'">已完成</td>
							<td style="text-align: center" ng-if="verificationRecord.paymentRecord.status!='2'">部分完成</td>
						</tr>
						<tr
							ng-if="dispalyVerificationRecord.length==0">
							<td colspan="7" align="center">没有符合条件的核销记录信息</td>
						</tr>
					</tbody>
					<tfoot>
													<tr>
														<td></td>
														<td style="text-align: center">付款水单金额:{{calcTotalData()}} {{memoRecord.moneyAmount|currency:''}}</td>
														<td style="text-align: center">已核销金额:{{totalPaymentAmount|currency:''}}</td>
														<td style="text-align: center">水单余额:{{memoRecord.moneyAmount-memoRecord.verificationMoneyAmount|currency:''}}</td>
														<td  style="text-align: center" ng-if="memoRecord.status=='0'">核销状态:待核销</td>
														<td  style="text-align: center" ng-if="memoRecord.status=='1'">核销状态:部分核销</td>
														<td  style="text-align: center" ng-if="memoRecord.status=='2'">核销状态:已完成 </td>
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
