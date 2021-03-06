<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8"%>
<!-- BEGIN PAGE HEADER-->
<style>
.box_card {
	width: 33.333333%;
	float: left;
	margin-left: 0px;
	margin-right: 0px;
	margin-top: 4px;
	margin-bottom: 4px;
}

.left {
	float: left;
}
</style>
<!-- <h3 class="page-title"> 发票信息
</h3> -->
<!-- <div class="page-bar">
    <ul class="page-breadcrumb">
        <li>
            <i class="fa fa-home"></i>
            <a ui-sref="dashboard">首页</a>
            <i class="fa fa-angle-right"></i>
        </li>
        <li>
            <a ui-sref="invoice">发票</a>
        </li>
    </ul>

</div> -->
<!-- END PAGE HEADER-->
<!-- BEGIN MAIN CONTENT -->
<div class="row">
	<div class="col-md-12">
		<form id="invoiceForm">
			<!-- BEGIN EXAMPLE TABLE PORTLET -->
			<div class="portlet bordered">
				<div class="portlet-body">
					<ul class="nav nav-tabs">
						<li class="active bold"><a data-target="#tab_1_1"
							data-toggle="tab"><span ng-if="inOrOut.indexOf('in')>-1">进项票信息</span><span
								ng-if="inOrOut.indexOf('out')>-1">销项票信息</span></a></li>
						<li class="bold"  ng-hide="tab_1_2Hide"><a data-target="#tab_1_2" data-toggle="tab">物料信息</a></li>
						<!-- <li class="bold"><a data-target="#tab_1_3" data-toggle="tab">附件</a></li> -->
						<li class="dropdown pull-right tabdrop">
							<button type="button" onclick="goBackPage()" class="btn defualt  btn-circle  btn-sm"><i class="fa fa-reply"></i>返回</button>
						</li>						
					</ul>
					<%--  <div class="portlet-title">
                            <div class="caption"><span ng-if="inOrOut.indexOf('in')>-1" >进项票信息</span><span  ng-if="inOrOut.indexOf('out')>-1">销项票信息</span></div>    
                            <div class="actions">
                                <button  ng-show="invoiceView"    class="btn purple  btn-sm btn-circle " ng-click="editInvoice()"  ng-if="inOrOut.indexOf('confirm')<0">
                                            <i class="fa fa-edit"></i> 编辑 </button>
                                <button   ng-hide="invoiceEdit"    ng-if="inOrOut.length>3&&inOrOut.indexOf('confirm')<0"   class="btn purple  btn-sm btn-circle " ng-click="cancelEditInvoice()" >
                                            <i class="fa fa-undo"></i> 取消 </button>
                                            <button   ng-hide="invoiceEdit"    ng-if="inOrOut.length<=3"   class="btn red  btn-sm btn-circle" ui-sref="invoice">
                                            <i class="fa fa-undo"></i> 取消 </button>
                                <button  ng-hide="invoiceAdd"   type="submit"   class="btn green  btn-sm btn-circle"   ng-click="saveInvoice()"  ng-if="inOrOut.indexOf('confirm')<0">
                                            <i class="fa fa-save"></i> 保存 </button>
                                            <!--  <button   ng-hide="invoiceAdd"  class="btn green  btn-sm btn-circle" ng-click="saveInvoice('applyIn')" ng-if="inOrOut.indexOf('in')>-1">
                              		<i class="fa fa-check"></i> 申请收票 </button> -->
                              		 <button   ng-hide="invoiceAdd"  class="btn green  btn-sm btn-circle" ng-click="saveInvoice('applyOut')" ng-if="inOrOut.indexOf('out')>-1&&(invoice.status==undefined||invoice.status==0)">
                              		<i class="fa fa-check"></i> 申请开票  </button>
                                            <button   ng-hide="invoiceEdit"  class="btn green  btn-sm btn-circle" ng-click="saveInvoice('confirmIn')" ng-if="inOrOut.indexOf('in')>-1&&inOrOut.indexOf('confirm')>-1">
                              		<i class="fa fa-check"></i> 确认收票 </button>
                              		<button   ng-hide="invoiceEdit"  class="btn green  btn-sm btn-circle" ng-click="saveInvoice('confirmOut')" ng-if="inOrOut.indexOf('out')>-1&&inOrOut.indexOf('confirm')>-1">
                              		<i class="fa fa-check"></i> 确认开票 </button>
                              		<button    ng-if="inOrOut.indexOf('confirm')>-1"   class="btn red  btn-sm btn-circle" ui-sref="invoice">
                                            <i class="fa fa-undo"></i> 取消 </button>
                            </div>
                        </div> --%>
					<div class="portlet light ">
						<div class="tab-content">
							<div class="tab-pane fade active in" id="tab_1_1">
								<div class="portlet-title" style="min-height: 48px;">
									<div class="tools" style="float: right">
										<!-- ng-if="deliveryDetail.status=='0'" -->
										<button ng-show="invoiceView"
											class="btn purple  btn-sm btn-circle "
											ng-click="editInvoice()" ng-if="inOrOut.indexOf('confirm')<0">
											<i class="fa fa-edit"></i> 编辑
										</button>
										<button ng-hide="invoiceEdit"
											ng-if="inOrOut.length>3&&inOrOut.indexOf('confirm')<0"
											class="btn purple  btn-sm btn-circle "
											ng-click="cancelEditInvoice()">
											<i class="fa fa-undo"></i> 取消
										</button>
										<button ng-hide="invoiceEdit" ng-if="inOrOut.length<=3"
											class="btn red  btn-sm btn-circle" ui-sref="invoice">
											<i class="fa fa-undo"></i> 取消
										</button>
										<button ng-hide="invoiceAdd" type="submit"
											class="btn green  btn-sm btn-circle" ng-click="saveInvoice()"
											ng-if="inOrOut.indexOf('confirm')<0">
											<i class="fa fa-save"></i> 保存
										</button>
										<!--  <button   ng-hide="invoiceAdd"  class="btn green  btn-sm btn-circle" ng-click="saveInvoice('applyIn')" ng-if="inOrOut.indexOf('in')>-1">
                              		<i class="fa fa-check"></i> 申请收票 </button> -->
										<!--  <button   ng-hide="invoiceAdd"  class="btn green  btn-sm btn-circle" ng-click="saveInvoice('applyOut')" ng-if="inOrOut.indexOf('out')>-1&&(invoice.status==undefined||invoice.status==0)">
                              		<i class="fa fa-check"></i> 申请开票  </button> -->
										<button ng-hide="invoiceEdit"
											class="btn green  btn-sm btn-circle"
											ng-click="saveInvoice('confirmIn')"
											ng-if="inOrOut.indexOf('in')>-1&&inOrOut.indexOf('confirm')>-1">
											<i class="fa fa-check"></i> 确认收票
										</button>
										<button ng-hide="invoiceEdit"
											class="btn green  btn-sm btn-circle"
											ng-click="saveInvoice('confirmOut')"
											ng-if="inOrOut.indexOf('out')>-1&&inOrOut.indexOf('confirm')>-1">
											<i class="fa fa-check"></i> 确认开票
										</button>
										<button ng-if="inOrOut.indexOf('confirm')>-1"
											class="btn red  btn-sm btn-circle" ui-sref="invoice">
											<i class="fa fa-undo"></i> 取消
										</button>

									</div>
								</div>
								<div class="portlet-body form">
									<div class="form-body">
										<div class="alert alert-danger display-hide">
											<button class="close" data-close="alert"></button>
											请先输入正确数据！
										</div>
										<div class="row">
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold" for="invoiceNum">
														<span class="required"> * </span> <span
														ng-if="inOrOut.indexOf('in')>-1"> 进项</span><span
														ng-if="inOrOut.indexOf('out')>-1"> 销项</span>发票编号 :
													</label>
													<div class=" ">
														<input type="text" class="form-control" id="invoiceNum"
															name="invoiceNum" ng-model="invoice.invoiceNum"
															ng-hide="invoiceAdd" ng-if="inOrOut.indexOf('confirm')<0" />
														<div class="form-control-focus"></div>
														<p class="control-label left" ng-show="invoiceView">{{invoice.invoiceNum}}</p>
														<p class="control-label left"
															ng-if="inOrOut.indexOf('confirm')>-1">{{invoice.invoiceNum}}</p>
														<!--  存放物料流水号-->
													</div>
												</div>
											</div>

											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold" for="contactNum"  ng-if="inOrOut.indexOf('in')>-1">
														<span class="required"> * </span>收票类型 :
													</label>
													<label class="control-label bold" for="contactNum"  ng-if="inOrOut.indexOf('out')>-1">
														<span class="required"> * </span>开票类型 :
													</label>
													<div class=" ">
														<select class="form-control" id="invoiceType"
															ng-hide="invoiceAdd" name="invoiceType"
															ng-model="invoice.invoiceType"
															ng-if="inOrOut.indexOf('confirm')<0">
															<option value=""></option>
															<option value="普通发票">普通发票</option>
															<option value="增值税发票">增值税发票</option>

														</select>

														<div class="form-control-focus"></div>
														<p class="control-label left" ng-show="invoiceView">{{invoice.invoiceType}}</p>
														<p class="control-label left"
															ng-if="inOrOut.indexOf('confirm')>-1">{{invoice.invoiceNum}}</p>
													</div>
												</div>
											</div>

											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold"
														for="relationBuyOrSaleNum"><span class="required">
															* </span> <span ng-if="inOrOut.indexOf('in')>-1">
															关联采购单号</span><span ng-if="inOrOut.indexOf('out')>-1">
															关联销售订单号</span> :</label>
													<div class="">
														<div class="input-group"
															ng-if="inOrOut.indexOf('in')>-1&&inOrOut.indexOf('confirm')<0"
															ng-click="selectBuyOrSaleOrderInfo('buy')"
															data-target="#buyOrderInfo" data-toggle="modal">
															<input type="text" class="form-control" placeholder=""
																id="relationBuyOrSaleNum" name="relationBuyOrSaleNum"
																ng-hide="invoiceAdd"
																ng-model="invoice.relationBuyOrSaleNum"> <span
																class="input-group-btn" style="vertical-align: top;"
																ng-hide="invoiceAdd">
																<button class="btn default" type="button">
																	<i class="fa fa-search"></i>
																</button>
															</span>
														</div>
														<div class="input-group"
															ng-if="inOrOut.indexOf('out')>-1&&inOrOut.indexOf('confirm')<0"
															ng-click="selectBuyOrSaleOrderInfo('sale')"
															data-target="#saleOrderInfo" data-toggle="modal">
															<input type="text" class="form-control" placeholder=""
																id="relationBuyOrSaleNum" name="relationBuyOrSaleNum"
																ng-hide="invoiceAdd" onkey
																ng-model="invoice.relationBuyOrSaleNum"> <span
																class="input-group-btn" style="vertical-align: top;"
																ng-hide="invoiceAdd">
																<button class="btn default" type="button">
																	<i class="fa fa-search"></i>
																</button>
															</span>
														</div>
														<div class="form-control-focus"></div>
														<p class="control-label left" ng-show="invoiceView">{{invoice.relationBuyOrSaleNum}}</p>
														<p class="control-label left"
															ng-if="inOrOut.indexOf('confirm')>-1">{{invoice.relationBuyOrSaleNum}}</p>
													</div>
												</div>
											</div>
										</div>
										<div class="row">
											<!--/span-->
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold" for="orderAmount">订单金额
														:</label>
													<div class=" ">
														<span></span>
														<!-- <input type="text" class="form-control"   id="orderAmount" name ="orderAmount"  ng-hide="invoiceAdd"  readonly="readonly"
												ng-model="invoice.orderAmount" >  -->
														<div class="form-control-focus"></div>
														<p class="control-label left">{{invoice.orderAmount
															|currency:'￥'}}</p>
													</div>
												</div>
												<!--/span-->
											</div>
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold" for="billOrReceiptMoney">收票金额
														:</label>
													<div class=" ">

														<div class="form-control-focus"></div>
														<p class="control-label left">{{invoice.billOrReceiptMoney
															|currency:'￥'}}</p>
													</div>
												</div>
												<!--/span-->
											</div>
											<!--     -->
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold" for="checkDate">
														大写金额 :</label>
													<div class=" ">
														<!-- <input type="text"     class="form-control"   placeholder=""  id="unBillAmount" name ="unBillAmount"   ng-hide="invoiceAdd"   readonly
												ng-model="invoice.unBillAmount" /> -->
														<div class="form-control-focus"></div>
														<p class="control-label left">{{invoice.capitalMoney}}</p>
													</div>
												</div>
											</div>
										</div>
										<!--/row-->

										<div class="row">
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold" for="checker"> 币种
														:</label>
													<div class=" ">
														<!-- <input type="text"     class="form-control"   placeholder=""  id="unBillAmount" name ="unBillAmount"   ng-hide="invoiceAdd"   readonly
												ng-model="invoice.unBillAmount" />  -->
														<div class="form-control-focus"></div>
														<p class="control-label left">{{invoice.currency}}</p>
													</div>
													<!--   <div class=" ">
                                                    <div ng-hide="invoiceAdd" >
                                	<input type="radio"  ng-model="invoice.paymentStatus" name="paymentStatus" ng-checked="invoice.paymentStatus=='是'" value="是"> 是
                        			<input type="radio"  ng-model="invoice.paymentStatus" name="paymentStatus" ng-checked="invoice.paymentStatus!='是'" value="否"> 否
                        			</div>
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="invoiceView"  ng-if="invoice.paymentStatus!='是'" >否</p> 
                                                                          <p class="control-label left" ng-show="invoiceView"  ng-if="invoice.paymentStatus=='是'" >是</p> 
                                                                    </div> -->
												</div>
											</div>
											<%-- <div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold" for="billWay"><span
														class="required"> * </span>开票方式 :</label>
													<div class=" ">
														<div ng-hide="invoiceAdd"
															ng-if="inOrOut.indexOf('confirm')<0">
															<input type="radio" ng-model="invoice.billWay"
																name="billWay" ng-checked="invoice.billWay=='1'"
																value="1"> 先票后款 <input type="radio"
																ng-model="invoice.billWay" name="billWay"
																ng-checked="invoice.billWay!='1'" value="0">
															先款后票
														</div>
														<div class="form-control-focus"></div>
														<p class="control-label left" ng-show="invoiceView"
															ng-if="invoice.billWay!='1'">先款后票</p>
														<p class="control-label left" ng-show="invoiceView"
															ng-if="invoice.billWay=='1'">先票后款</p>
														<p class="control-label left"
															ng-if="invoice.billWay!='1'&&inOrOut.indexOf('confirm')>-1">先款后票</p>
														<p class="control-label left"
															ng-if="invoice.billWay=='1'&&inOrOut.indexOf('confirm')>-1">先票后款</p>
													</div>
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold" for="checker"> <span
														class="required"> * </span> <span
														ng-if="inOrOut.indexOf('in')>-1">是否付款</span><span
														ng-if="inOrOut.indexOf('out')>-1"> 是否收款</span> :
													</label>
													<div class=" ">
														<div ng-hide="invoiceAdd"
															ng-if="inOrOut.indexOf('confirm')<0">
															<input type="radio" ng-model="invoice.paymentStatus"
																name="paymentStatus"
																ng-checked="invoice.paymentStatus=='是'" value="是">
															是 <input type="radio" ng-model="invoice.paymentStatus"
																name="paymentStatus"
																ng-checked="invoice.paymentStatus!='是'" value="否">
															否
														</div>
														<div class="form-control-focus"></div>
														<p class="control-label left" ng-show="invoiceView"
															ng-if="invoice.paymentStatus!='是'">否</p>
														<p class="control-label left" ng-show="invoiceView"
															ng-if="invoice.paymentStatus=='是'">是</p>
														<p class="control-label left"
															ng-if="invoice.paymentStatus!='是'&&inOrOut.indexOf('confirm')>-1">否</p>
														<p class="control-label left"
															ng-if="invoice.paymentStatus=='是'&&inOrOut.indexOf('confirm')>-1">是</p>
													</div>
												</div>
											</div> --%>
											<div class="col-md-4" ng-hide="inOrOut.indexOf('confirm')>-1">
												<div class="form-group">
													<label class="control-label bold" for="status">
														申请日期: </label>
													<!--  ng-if="inOrOut.indexOf('confirm')<0"  -->
													<div class=" ">
														<%--  <div ng-if="inOrOut.indexOf('confirm')<0" > --%>
														<input type="text"
															class="form-control form-control-inline date-picker"
															data-date-format="yyyy-mm-dd" data-date-viewmode="years"
															placeholder="" id="submitDate" name="submitDate"
															ng-model="invoice.submitDate" ng-hide="invoiceAdd" />
														<!-- </div> -->
														<div class="form-control-focus"></div>
														<p class="control-label left" ng-show="invoiceView">{{invoice.submitDate
															| date:'yyyy-MM-dd'}}</p>
														<p class="control-label left"
															ng-if="inOrOut.indexOf('confirm')>-1">{{invoice.submitDate
															| date:'yyyy-MM-dd'}}</p>
													</div>
												</div>
											</div>
											<div class="col-md-4" ng-if="inOrOut.indexOf('confirm')>-1">
												<div class="form-group">
													<label class="control-label bold" for="status">
														申请日期: </label>
													<!--  ng-if="inOrOut.indexOf('confirm')<0"  -->
													<div class="">
														<div class="form-control-focus"></div>
														<p class="control-label left" ng-show="invoiceView">{{invoice.submitDate
															| date:'yyyy-MM-dd'}}</p>
														<p class="control-label left"
															ng-if="inOrOut.indexOf('confirm')>-1">{{invoice.submitDate
															| date:'yyyy-MM-dd'}}</p>
													</div>
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold" for="checker">
														申请人:</label>
													<div class=" ">
														<input type="text" class="form-control" placeholder=""
															id="submitter" name="submitter" ng-hide="invoiceAdd"
															ng-if="inOrOut.indexOf('confirm')<0"
															ng-model="invoice.submitter">
														<div class="form-control-focus"></div>
														<p class="control-label left" ng-show="invoiceView">{{invoice.submitter}}</p>
														<p class="control-label left"
															ng-if="inOrOut.indexOf('confirm')>-1">{{invoice.submitter}}</p>
													</div>
												</div>
											</div>
										</div>

										<div class="row">
											
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold" for="checker">
														申请部门:</label>
													<div class=" ">
														<input type="text" class="form-control" placeholder=""
															id="submitDepartment" name="submitDepartment"
															ng-hide="invoiceAdd" ng-if="inOrOut.indexOf('confirm')<0"
															ng-model="invoice.submitDepartment">
														<div class="form-control-focus"></div>
														<p class="control-label left" ng-show="invoiceView">{{invoice.submitDepartment}}</p>
														<p class="control-label left"
															ng-if="inOrOut.indexOf('confirm')>-1">{{invoice.submitDepartment}}</p>
													</div>
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold" for="checker">
														备注:</label>
													<div class=" ">
														<input type="text" class="form-control" placeholder=""
															id="billingRemark" name="billingRemark"
															ng-hide="invoiceAdd"
															ng-if="inOrOut.indexOf('in')>-1&&inOrOut.indexOf('confirm')<0"
															ng-model="invoice.billingRemark"> <input
															type="text" class="form-control" placeholder=""
															id="receiptRemark" name="receiptRemark"
															ng-hide="invoiceAdd"
															ng-if="inOrOut.indexOf('out')>-1&&inOrOut.indexOf('confirm')<0"
															ng-model="invoice.receiptRemark">
														<div class="form-control-focus"></div>
														<p class="control-label left" ng-show="invoiceView"
															ng-if="inOrOut.indexOf('in')>-1">{{invoice.billingRemark}}</p>
														<p class="control-label left" ng-show="invoiceView"
															ng-if="inOrOut.indexOf('out')>-1">{{invoice.receiptRemark}}</p>
														<p class="control-label left"
															ng-if="inOrOut.indexOf('confirm')>-1">{{invoice.billingRemark}}</p>
														<p class="control-label left"
															ng-if="inOrOut.indexOf('confirm')>-1">{{invoice.receiptRemark}}</p>
													</div>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-md-4"
												ng-show="inOrOut.indexOf('out')>-1&&inOrOut.indexOf('confirm')<0">
												<div class="form-group">
													<label class="control-label bold" for="checker">
														申请开票日期:</label>
													<div class=" ">
														<%-- <div ng-if="inOrOut.indexOf('confirm')<0" > --%>
														<input type="text"
															class="form-control form-control-inline date-picker"
															data-date-format="yyyy-mm-dd" data-date-viewmode="years"
															placeholder="" id="receiptDate" name="receiptDate"
															ng-hide="invoiceAdd" ng-model="invoice.receiptDate" />
														<!-- </div> -->
														<div class="form-control-focus"></div>
														<p class="control-label left" ng-show="invoiceView">{{invoice.receiptDate
															| date:'yyyy-MM-dd'}}</p>
														<p class="control-label left"
															ng-if="inOrOut.indexOf('confirm')>-1">{{invoice.receiptDate
															| date:'yyyy-MM-dd'}}</p>
													</div>
												</div>
											</div>
											<div class="col-md-4"
												ng-show="inOrOut.indexOf('out')>-1&&inOrOut.indexOf('confirm')>-1">
												<div class="form-group">
													<label class="control-label bold" for="checker">
														申请开票日期:</label>
													<div class=" ">
														<div class="form-control-focus"></div>
														<p class="control-label left" ng-show="invoiceView">{{invoice.receiptDate
															| date:'yyyy-MM-dd'}}</p>
														<p class="control-label left"
															ng-if="inOrOut.indexOf('confirm')>-1">{{invoice.receiptDate
															| date:'yyyy-MM-dd'}}</p>
													</div>
												</div>
											</div>
										</div>
										<div class="row" ng-if="inOrOut.indexOf('in')>-1">
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold" for=""><span
														class="required"> * </span> 开票方:</label>
													<div class="">
														<input type="text" class="form-control" id="comName"
															name="comName" ng-hide="invoiceAdd"
															ng-if="inOrOut.indexOf('confirm')<0"
															ng-model="invoice.comName" />

														<div class="form-control-focus"></div>
														<p class="control-label left" ng-show="invoiceView">{{invoice.comName}}</p>
														<p class="control-label left"
															ng-if="inOrOut.indexOf('confirm')>-1">{{invoice.comName}}</p>
													</div>

												</div>
											</div>
											<%-- <div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold" for=""><span
														class="required"> * </span> 联系人 :</label>
													<div class="">
														<input type="text" class="form-control" id="contact"
															name="contact" ng-hide="invoiceAdd"
															ng-if="inOrOut.indexOf('confirm')<0"
															ng-model="invoice.contact" />

														<div class="form-control-focus"></div>
														<p class="control-label left" ng-show="invoiceView">{{invoice.contact}}</p>
														<p class="control-label left"
															ng-if="inOrOut.indexOf('confirm')>-1">{{invoice.contact}}</p>
													</div>

												</div>
											</div> --%>
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold" for=""><span
														class="required"> * </span> 联系电话 :</label>
													<div class="">
														<input type="text" class="form-control" id="tel"
															name="tel" ng-hide="invoiceAdd"
															ng-if="inOrOut.indexOf('confirm')<0"
															ng-model="invoice.tel" />

														<div class="form-control-focus"></div>
														<p class="control-label left" ng-show="invoiceView">{{invoice.tel}}</p>
														<p class="control-label left"
															ng-if="inOrOut.indexOf('confirm')>-1">{{invoice.tel}}</p>
													</div>

												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold" for=""> 备注 :</label>
													<div class="">
														<input type="text" class="form-control" id="secondRemark"
															name="secondRemark" ng-hide="invoiceAdd"
															ng-if="inOrOut.indexOf('confirm')<0"
															ng-model="invoice.secondRemark" />
														<div class="form-control-focus"></div>
														<p class="control-label left" ng-show="invoiceView">{{invoice.secondRemark}}</p>
														<p class="control-label left"
															ng-if="inOrOut.indexOf('confirm')>-1">{{invoice.secondRemark}}</p>
													</div>

												</div>
											</div>

										</div>
										<div class="row" ng-if="inOrOut.indexOf('out')>-1">
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold" for=""><span
														class="required"> * </span> 收票方:</label>
													<div class="">
														<input type="text" class="form-control" id="comName"
															name="comName" ng-hide="invoiceAdd"
															ng-if="inOrOut.indexOf('confirm')<0"
															ng-model="invoice.comName" />

														<div class="form-control-focus"></div>

														<p class="control-label left" ng-show="invoiceView"
															ng-if="inOrOut.indexOf('out')>-1">{{invoice.comName}}</p>
														<p class="control-label left" ng-show="invoiceView"
															ng-if="inOrOut.indexOf('in')>-1">{{invoice.comName}}</p>
														<p class="control-label left"
															ng-if="inOrOut.indexOf('out')>-1&&inOrOut.indexOf('confirm')>-1">{{invoice.comName}}</p>
														<p class="control-label left"
															ng-if="inOrOut.indexOf('in')>-1&&inOrOut.indexOf('confirm')>-1">{{invoice.comName}}</p>
													</div>

												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold" for=""><span
														class="required"> * </span> 企业全称 :</label>
													<div class="">
														<input type="text" class="form-control" id="companyName"
															name="companyName" ng-hide="invoiceAdd"
															ng-if="inOrOut.indexOf('confirm')<0"
															ng-model="invoice.companyName" />

														<div class="form-control-focus"></div>
														<p class="control-label left" ng-show="invoiceView"
															ng-if="inOrOut.indexOf('out')>-1">{{invoice.companyName}}</p>
														<p class="control-label left"
															ng-if="inOrOut.indexOf('confirm')>-1">{{invoice.companyName}}</p>
													</div>

												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold" for=""><span
														class="required"> * </span> 地址 :</label>
													<div class="">
														<input type="text" class="form-control" id="address"
															name="address" ng-hide="invoiceAdd"
															ng-if="inOrOut.indexOf('confirm')<0"
															ng-model="invoice.address" />
														<div class="form-control-focus"></div>
														<p class="control-label left" ng-show="invoiceView">{{invoice.address}}</p>
														<p class="control-label left"
															ng-if="inOrOut.indexOf('confirm')>-1">{{invoice.address}}</p>
													</div>

												</div>
											</div>

										</div>
										<div class="row" ng-if="inOrOut.indexOf('out')>-1">
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold" for=""><span
														class="required"> * </span>联系电话:</label>
													<div class="">
														<input type="text" class="form-control" id="tel"
															name="tel" ng-hide="invoiceAdd"
															ng-if="inOrOut.indexOf('confirm')<0"
															ng-model="invoice.tel" />

														<div class="form-control-focus"></div>

														<p class="control-label left" ng-show="invoiceView">{{invoice.tel}}</p>
														<p class="control-label left"
															ng-if="inOrOut.indexOf('confirm')>-1">{{invoice.tel}}</p>

													</div>

												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold" for=""><span
														class="required"> * </span> 开户银行 :</label>
													<div class="">
														 <input type="text" class="form-control"   id="bankName" name ="bankName"       ng-hide="invoiceAdd"      ng-if="inOrOut.indexOf('confirm')<0"
												ng-model="invoice.bankName" />  
														<div  ng-hide="invoiceView"     ng-if="inOrOut.indexOf('confirm')<0" >
															<!-- <select class="form-control" data-live-search="true"
																data-size="" id="bankName" name="bankName"
																ng-model="invoice.bankName">
																<option  ng-repeat="op in buyCom " value="{{op.comId}}"  > {{op.comName}}</option>
																<option value=""></option>
																<option ng-repeat="companyFinance in companyFinances"
																	value="{{companyFinance.openingBank}}">{{companyFinance.openingBank}}</option>
															</select> -->
														</div>

														<div class="form-control-focus"></div>
														<p class="control-label left" ng-show="invoiceView">{{invoice.bankName}}</p>
														<p class="control-label left"
															ng-if="inOrOut.indexOf('confirm')>-1">{{invoice.bankName}}</p>
													</div>

												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold" for=""><span
														class="required"> * </span> 账号:</label>
													<div class="">
														 <input type="text" class="form-control"   id="account" name ="account"       ng-hide="invoiceAdd"    ng-if="inOrOut.indexOf('confirm')<0" 
												ng-model="invoice.account" /> 
														<%-- <div   ng-hide="invoiceView"   ng-if="inOrOut.indexOf('confirm')<0" >
															<!--  ng-hide="invoiceAdd"-->
															<select class="form-control" data-live-search="true"
																data-size="" id="account" name="account"
																ng-model="invoice.account">
																<option value=""></option>
																<option ng-repeat="companyFinance in companyFinances"
																	value="{{companyFinance.accountNumber}}">{{companyFinance.accountNumber}}</option>
															</select>
														</div> --%>
														<div class="form-control-focus"></div>
														<p class="control-label left" ng-show="invoiceView">{{invoice.account}}</p>
														<p class="control-label left"
															ng-if="inOrOut.indexOf('confirm')>-1">{{invoice.account}}</p>
													</div>

												</div>
											</div>

										</div>
										<div class="row" ng-if="inOrOut.indexOf('out')>-1">
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold" for=""><span
														class="required"> * </span> 企业纳税号:</label>
													<div class="">
														<input type="text" class="form-control" id="taxNum"
															name="taxNum" ng-hide="invoiceAdd"
															ng-if="inOrOut.indexOf('confirm')<0"
															ng-model="invoice.taxNum" />

														<div class="form-control-focus"></div>

														<p class="control-label left" ng-show="invoiceView">{{invoice.taxNum}}</p>
														<p class="control-label left"
															ng-if="inOrOut.indexOf('confirm')>-1">{{invoice.taxNum}}</p>
													</div>

												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold" for=""> 备注 :</label>
													<div class="">
														<input type="text" class="form-control" id="secondRemark"
															name="secondRemark" ng-hide="invoiceAdd"
															ng-if="inOrOut.indexOf('confirm')<0"
															ng-model="invoice.secondRemark" />
														<div class="form-control-focus"></div>
														<p class="control-label left" ng-show="invoiceView">{{invoice.secondRemark}}</p>
														<p class="control-label left"
															ng-if="inOrOut.indexOf('confirm')>-1">{{invoice.secondRemark}}</p>
													</div>

												</div>
											</div>

										</div>
										<div class="row">
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold" for=""> <span
														ng-if="inOrOut.indexOf('in')>-1">应付金额</span><span
														ng-if="inOrOut.indexOf('out')>-1"> 应收金额</span>:
													</label>
													<div class="">


														<div class="form-control-focus"></div>
														<p class="control-label left">
															<!-- {{invoice.shouldPayOrReceiptMoney|currency:'￥'}} -->
															{{invoice.orderAmount |currency:'￥'}}
														</p>
													</div>

												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold" for=""> <span
														ng-if="inOrOut.indexOf('in')>-1">预付金额</span><span
														ng-if="inOrOut.indexOf('out')>-1"> 预收金额</span> :
													</label>
													<div class="">


														<div class="form-control-focus"></div>
														<p class="control-label left">{{0|currency:'￥'}}</p>
													</div>

												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold" for=""> <span
														ng-if="inOrOut.indexOf('in')>-1">已付金额</span><span
														ng-if="inOrOut.indexOf('out')>-1">已收金额</span> :
													</label>
													<div class="">

														<div class="form-control-focus"></div>
														<p class="control-label left">{{invoice.orderAmount-invoice.unPayOrReceiptMoney|currency:'￥'}}</p>
													</div>

												</div>
											</div>

										</div>
										<div class="row">
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold" for=""> <span
														ng-if="inOrOut.indexOf('in')>-1">未付金额</span><span
														ng-if="inOrOut.indexOf('out')>-1"> 未收金额</span>:
													</label>
													<div class="">


														<div class="form-control-focus"></div>

														<p class="control-label left">{{invoice.unPayOrReceiptMoney|currency:'￥'}}</p>

													</div>

												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold" for=""> <span
														ng-if="inOrOut.indexOf('in')>-1">已收票金额</span><span
														ng-if="inOrOut.indexOf('out')>-1"> 已开票金额</span> :
													</label>
													<div class="">


														<div class="form-control-focus"></div>
														<p class="control-label left">{{invoice.orderAmount-invoice.unBillOrReceiptMoney|currency:'￥'}}</p>
													</div>

												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold" for=""> <span
														ng-if="inOrOut.indexOf('in')>-1">未收票金额</span><span
														ng-if="inOrOut.indexOf('out')>-1">未开票金额</span> :
													</label>
													<div class="">

														<div class="form-control-focus"></div>
														<p class="control-label left">{{invoice.unBillOrReceiptMoney|currency:'￥'}}</p>
													</div>

												</div>
											</div>

										</div>
										<div class="row"     ng-show="inOrOut.indexOf('confirm')>-1">
											<!-- row START -->
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold" for="billingDate"><span
														class="required"> * </span>开票日期 :</label>
													<div class=" ">
														<input type="text"
															class="form-control form-control-inline date-picker"
															data-date-format="yyyy-mm-dd" data-date-viewmode="years"
															placeholder="" id="billingDate" name="billingDate"
															ng-hide="invoiceAdd" ng-model="invoice.billingDate" />
														<div class="form-control-focus"></div>
														<p class="control-label left" ng-show="invoiceView">{{invoice.billingDate
															| date:'yyyy-MM-dd'}}</p>
													</div>
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold" for="invoiceAmount"><span
														class="required"> * </span>发票金额 : </label>
													<div class=" ">
														<input type="text" class="form-control " placeholder=""
															id="invoiceAmount" name="invoiceAmount"
															ng-hide="invoiceAdd"
															data-unBillOrReceiptMoney="{{invoice.unBillOrReceiptMoney}}"
															ng-model="invoice.invoiceAmount" ng-blur="clearNoNum()">
														<div class="form-control-focus"></div>
														<p class="control-label left" ng-show="invoiceView">{{invoice.invoiceAmount|currency:'￥'}}</p>
													</div>
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold" for="invoiceNO"><span
														class="required"> * </span>发票号码 : </label>
													<div class=" ">
														<input type="text" class="form-control " placeholder=""
															id="invoiceNO" name="invoiceNO" ng-hide="invoiceAdd"
															ng-model="invoice.invoiceNO">
														<div class="form-control-focus"></div>
														<p class="control-label left" ng-show="invoiceView">{{invoice.invoiceNO}}</p>
													</div>
												</div>
											</div>
										</div>
										<!-- row END-->
										<div class="row"     ng-if="inOrOut.indexOf('confirm')>-1">
											<!-- row START -->
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold" for="invoiceVoucher">发票凭证
														:</label>
													<div class="">
														<div class="col-md-9">
															<div ng-hide="invoiceAdd"
																ng-if="invoice.invoiceVoucher==null||invoice.invoiceVoucher==''"
																class="fileinput fileinput-new"
																data-provides="fileinput">
																<span class="btn blue btn-outline btn-file"> <span
																	class="fileinput-new">上传发票凭证</span> <span
																	class="fileinput-exists">更改</span> <input type="file"
																	name="..." nv-file-select uploader="uploader"
																	onchange="angular.element(this).scope().up(this.files[0])"
																	ng-model="invoice" ng-click="uploadFile(invoice)">
																</span> <span class="fileinput-filename">{{invoice.invoiceVoucher.substring(invoice.invoiceVoucher.indexOf("_")+1)}}</span>
																&nbsp; <a href="javascript:;"
																	class="close fileinput-exists"
																	ng-click="removefile(invoice)" data-dismiss="fileinput">
																</a>
															</div>
															<div ng-hide="invoiceAdd"
																ng-if="invoice.invoiceVoucher!=null&&invoice.invoiceVoucher!=''"
																class="fileinput fileinput-exists"
																data-provides="fileinput">
																<span class="btn blue btn-outline btn-file"> <span
																	class="fileinput-new">上传附件</span> <span
																	class="fileinput-exists">更改</span> <input type="file"
																	name="..." nv-file-select uploader="uploader"
																	onchange="angular.element(this).scope().up(this.files[0])"
																	ng-model="invoice" ng-click="uploadFile(invoice)">
																</span> <span class="fileinput-filename">{{invoice.invoiceVoucher.substring(invoice.invoiceVoucher.indexOf("_")+1)}}</span>
																&nbsp; <a href="javascript:;"
																	class="close fileinput-exists"
																	ng-click="removefile(priceList)"
																	data-dismiss="fileinput"> </a>
															</div>
															<label ng-show="invoiceView"
																ng-if="invoice.invoiceVoucher==null||invoice.invoiceVoucher==''"
																class="c_edit">未上传发票凭证</label> <label
																ng-show="invoiceView"
																ng-if="invoice.invoiceVoucher!=null&&invoice.invoiceVoucher!=''"
																class="c_edit"><a href="javascript:;"
																title="下载附件" ng-click="downloadFile(invoice)">{{invoice.invoiceVoucher.substring(invoice.invoiceVoucher.indexOf("_")+1)}}</a></label>
														</div>
													</div>
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold" for="approver"><span
														class="required"> * </span><span
														ng-if="inOrOut.indexOf('in')>-1">收票人 </span><span
														ng-if="inOrOut.indexOf('out')>-1">开票人</span> :</label>
													<div class=" ">
														<input type="text" class="form-control" placeholder=""
															id="approver" name="approver" ng-hide="invoiceAdd"
															ng-model="invoice.approver">
														<div class="form-control-focus"></div>
														<p class="control-label left" ng-show="invoiceView">{{invoice.approver}}</p>
													</div>
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold" for="thirdRemark">备注
														:</label>
													<div class=" ">
														<input type="text" class="form-control" placeholder=""
															id="thirdRemark" name="thirdRemark" ng-hide="invoiceAdd"
															ng-model="invoice.thirdRemark">
														<div class="form-control-focus"></div>
														<p class="control-label left" ng-show="invoiceView">{{invoice.thirdRemark}}</p>
													</div>
												</div>
											</div>
										</div>
							</div>
												</div>
											</div>
											<div class="tab-pane" id="tab_1_2">
											 <div class="portlet-title" style="min-height: 48px;">
               <div class="tools" style="float:right">
                 <button type="submit" ng-click="saveAllBillingRecord()" ng-hide="orderMaterielInput"  class="btn green  btn-circle  btn-sm">
                 		<i class="fa fa-save"></i> 保存 </button>
                <!--  <button ng-click="cancelAllBillingRecord()" type="button" ng-hide="orderMaterielInput" class="btn defualt  btn-circle  btn-sm">
                 		<i class="fa fa-undo"></i> 取消 </button> -->
                 <button ng-click="editAllBillingRecord()" type="button" ng-show="orderMaterielShow" class="btn purple  btn-circle  btn-sm">
                 		<i class="fa fa-edit"></i> 编辑 </button>
                </div>
            </div>
											<div class="portlet-body" ng-if="inOrOut.indexOf('in')>-1">
	<!-- 进项票物料信息-->
	<table class="table table-striped table-bordered table-hover "
		id="sample_inm">
		<thead>
			<tr>
				<td style="text-align: center">物料编号</td>
				<td style="text-align: center">物料名称</td>
				<td style="text-align: center">规格型号</td>
				<td style="text-align: center">单位</td>
				<td style="text-align: center">订单数量</td>
				<td style="text-align: center">未收数量</td>
				<td style="text-align: center">收票数量</td>
				<td style="text-align: center">含税单价</td>
				<td style="text-align: center">金额</td>
				<td style="text-align: center">操作</td>
			</tr>
		</thead>

		<tbody>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="4">合计</td>
				<td colspan="6">小写: {{invoice.billOrReceiptMoney
					|currency:'￥'}} 大写:{{invoice.capitalMoney}}</td>
			</tr>
		</tfoot>
	</table>
</div>
<div class="portlet-body" ng-if="inOrOut.indexOf('out')>-1">
	<!-- 销项票物料信息-->
	<table class="table table-striped table-bordered table-hover "
		id="sample_outm">
		<thead>
			<tr>
				<td style="text-align: center;">物料编号</td>
				<td style="text-align: center">物料名称</td>
				<td style="text-align: center">规格型号</td>
				<td style="text-align: center">单位</td>
				<td style="text-align: center">订单数量</td>
				<td style="text-align: center">可开数量</td>
				<td style="text-align: center">开票数量</td>
				<td style="text-align: center">含税单价</td>
				<td style="text-align: center">金额</td>
				<td style="text-align: center">操作</td>
			</tr>
		</thead>

		<tbody>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="4">合计</td>
				<td colspan="6">小写: {{invoice.billOrReceiptMoney
					|currency:'￥'}} 大写:{{invoice.capitalMoney}}</td>
			</tr>
		</tfoot>
	</table>
</div>
											
											</div>
										</div>	</div>		
											</div>	</div>	
		</form>
	</div>
	<!-- row END-->

	<div></div>
</div>

<!-- </div></div></div> -->

<!-- <div class="portlet-title">
	物料信息START
	<div class="caption">物料信息</div>
	<div class="actions"></div>
</div> -->


<!-- <div class="portlet-body" ng-if="inOrOut.indexOf('in')>-1">
	进项票物料信息
	<table class="table table-striped table-bordered table-hover "
		id="sample_inm">
		<thead>
			<tr>

				<td style="text-align: center">物料编号</td>
				<td style="text-align: center">物料名称</td>
				<td style="text-align: center">规格型号</td>
				<td style="text-align: center">单位</td>
				<td style="text-align: center">订单数量</td>
				<td style="text-align: center">未收数量</td>
				<td style="text-align: center">收票数量</td>
				<td style="text-align: center">含税单价</td>
				<td style="text-align: center">金额</td>
				<td style="text-align: center">操作</td>
			</tr>
		</thead>

		<tbody>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="4">合计</td>
				<td colspan="6">小写: {{invoice.billOrReceiptMoney
					|currency:'￥'}} 大写:{{invoice.capitalMoney}}</td>
			</tr>
		</tfoot>
	</table>
</div>
<div class="portlet-body" ng-if="inOrOut.indexOf('out')>-1">
	销项票物料信息
	<table class="table table-striped table-bordered table-hover "
		id="sample_outm">
		<thead>
			<tr>
				<td style="text-align: center;">物料编号</td>
				<td style="text-align: center">物料名称</td>
				<td style="text-align: center">规格型号</td>
				<td style="text-align: center">单位</td>
				<td style="text-align: center">订单数量</td>
				<td style="text-align: center">可开数量</td>
				<td style="text-align: center">开票数量</td>
				<td style="text-align: center">含税单价</td>
				<td style="text-align: center">金额</td>
				<td style="text-align: center">操作</td>
			</tr>
		</thead>

		<tbody>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="4">合计</td>
				<td colspan="6">小写: {{invoice.billOrReceiptMoney
					|currency:'￥'}} 大写:{{invoice.capitalMoney}}</td>
			</tr>
		</tfoot>
	</table>
</div> -->


<!-- END EXAMPLE TABLE PORTLET-->
<jsp:include page="../invoice/selectBuyOrSaleOrder.jsp" />
<!-- END MAIN CONTENT -->
<!-- BEGIN MAIN JS -->
<script>
function clearNoNumPoint(value){
	 //先把非数字的都替换掉，除了数字和.
	  var  value = value.replace(/[^\d.]/g,"");
	 //必须保证第一个为数字而不是.
	 value = value.replace(/^\./g,"");
	 //保证只有出现一个.而没有多个.
	 value = value.replace(/\.{2,}/g,"");
	 //保证不出现点
	 value = value.replace(".","");
	 
	 return value;
	
}
 function judgeNumber(canBillAmount,serialNum,judgeString){
	
	 var value= clearNoNumPoint($("#"+serialNum).val());
	 debugger;
	 if(value>Number(canBillAmount)){
		 if(judgeString=='in'){
			 toastr.warning("收票数量不得大于未收数量！");
		 }else if(judgeString=='out'){
			 toastr.warning("开票数量不得大于可开数量！");
		 }
		 $("#"+serialNum).val(0);
		 $("#"+serialNum).focus();
	 }
	 if(isNull(value)){
		 $("#"+serialNum).val(0);
		 $("#"+serialNum).focus();
	 }
 }
</script>
<!-- END MAIN JS -->