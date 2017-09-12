<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- BEGIN PAGE HEADER-->

<h3 class="page-title">付款信息</h3>
<div class="page-bar">
	<ul class="page-breadcrumb">
		<li><i class="fa fa-home"></i> <a ui-sref="dashboard">首页</a> <i
			class="fa fa-angle-right"></i></li>
		<li><a ui-sref="paymentRecordC">付款</a> <i class="fa fa-angle-right"></i>
		</li>
		<li><a>新增付款</a></li>
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
				<form action="#" id="form_sample_1" class="">
					<div class="portlet light ">
						<ul class="nav nav-tabs">
							<!-- <li class="dropdown pull-right tabdrop">
								<button ng-hide="companyAdd"
									class="btn green  btn-sm btn-circle" ng-click="saveBasicInfo()">
									<i class="fa fa-check"></i> 保存
								</button>
								<button ng-hide="companyAdd"
									class="btn defualt  btn-sm btn-circle" ng-click="goBack()"
									onclick="return false;">
									<i class="fa fa-mail-reply"></i> 取消
								</button>
							</li> -->
							<li class="active bold"><a data-target="#tab_1_1"
								data-toggle="tab">付款信息</a></li>
							<li class="bold"><a data-target="#tab_1_2" data-toggle="tab">收款信息</a>
							<li class="bold"><a data-target="#tab_1_3" data-toggle="tab">附件信息</a>
							</li>
						</ul>
						<div class="tab-content">
							<div class="tab-pane fade active in" id="tab_1_1">
								<div class="portlet-title" style="min-height: 48px;">
									<div class="tools" style="float: right" id="noprintdiv">
										<button type="submit" ng-click="saveBasicInfo()"
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
										<div class="row">
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">付款单号<span
														class="required" aria-required="true"> * </span></label>
													<div class="">
														<input type="text" name="paymentNum" class="form-control"
															ng-model="paymentRecord.paymentNum" ng-show="input">
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-show="span">{{pay.paymentNum}}</p>
													</div>
												</div>
											</div>
											<!--/span-->

											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">付款类型<span
														class="required" aria-required="true"> * </span></label>
													<div class="">
														<select class="form-control"
															id="paymentType" ng-show="input"
															name="paymentType"
															ng-model="paymentRecord.paymentType">
															<option value="">付款类型</option>
															<option value="采购付款">采购付款</option>
															<option value="预付款">预付款</option>
														</select>
														<p class="form-control-static" ng-show="span">{{pay.paymentType}}</p>
														<div class="form-control-focus"></div>
													</div>
												</div>
											</div>
											<!--/span-->


											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">关联采购单号<span
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
															<p class="form-control-static" ng-show="span">{{pay.orderNum}}</p>
														</div>
														<input type="text" ng-model="orderSerial" ng-hide="true" />
													</div>
												</div>
											</div>
											<!--/span-->
										</div>
										
										<!--/row-->
										<div class="row">
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">订单金额<span
														class="required" aria-required="true"> * </span></label>
													<div class="">
														<input type="text" name="orderAmount" class="form-control"
															 ng-model="saleOrder.orderAmount" ng-show="input" readonly/>
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-show="span">{{pay.orderAmount}}</p>
													</div>

												</div>
											</div>
											<!--/span-->
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">申请付款金额<span
														class="required" aria-required="true"> * </span></label>
													<div class="">
														<input type="text" name="applyPaymentAmount" id="applyPaymentAmount" class="form-control" number
															 ng-model="paymentRecord.applyPaymentAmount" ng-blur="getChnAmount()" ng-show="input"/>
															 <p class="form-control-static" ng-show="span">{{pay.applyPaymentAmount}}</p>
														<div class="form-control-focus"></div>
													</div>
												</div>
											</div>
										</div>


										<!--/row-->
										<div class="row">
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">大写金额<span
														class="required" aria-required="true"> * </span></label>
													<div class="">
														<input type="text" name="chnAmount" class="form-control"
															readonly ng-model="chnAmount" ng-show="input"/>
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-show="span">{{applyPaymentAmountChn}}</p>
													</div>
												</div>
											</div>
											<!--/span-->
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">申请币种<span
														class="required" aria-required="true"> * </span></label>
													<div class="">
														<select class="form-control" id="applyCurrency"
															name="applyCurrency"
															ng-model="paymentRecord.applyCurrency"
															 ng-show="input">
															<option value="">申请币种</option>
															<option value="人民币">人民币</option>
															<option value="美元">美元</option>
															<option value="欧元">欧元</option>
															<option value="日币">日币</option>
														</select>
														<p class="form-control-static" ng-show="span">{{pay.applyCurrency}}</p>
													</div>
												</div>
											</div>
											<!--/span-->
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">申请付款日期<span
														class="required" aria-required="true"> * </span></label>
													<div class="">
														<input type="text" class="form-control" name="playPaymentDate"
														data-date-format="yyyy-mm-dd"
													    data-date-viewmode="years" size="16"
															ng-model="paymentRecord.playPaymentDate" ng-show="input" id="playPaymentDate"/>
														<p class="form-control-static" ng-show="span">{{pay.playPaymentDate}}</p>
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
													<label class="control-label bold">支付类型<span
														class="required" aria-required="true"> * </span></label>
													<div class="">
														<select class="form-control" id="payType"
															name="payType"
															ng-model="paymentRecord.payType"
														    ng-show="input">
															<option value="">支付类型</option>
															<option value="预付款">预付款</option>
															<option value="中期款">中期款</option>
															<option value="全款">全款</option>
															<option value="尾款">尾款</option>
														</select>
														<p class="form-control-static" ng-show="span">{{pay.payType}}</p>
													</div>
												</div>
											</div>
											<!--/span-->
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">支付节点<span
														class="required" aria-required="true"> * </span></label>
													<div class="">
														<select class="form-control" id="paymentNode"
															name="paymentNode"
															ng-model="paymentRecord.paymentNode"
														    ng-show="input">
															<option value="">支付节点</option>
															<option value="合同签订">合同签订</option>
															<option value="提货前">提货前</option>
															<option value="到货后">到货后</option>
															<option value="验收后">验收后</option>
															<option value="质保期满">质保期满</option>
															<option value="销售出库">销售出库</option>
															<option value="收到委托方付款">收到委托方付款</option>
														</select>
														<p class="form-control-static" ng-show="span">{{pay.paymentNode}}</p>
													</div>
												</div>
											</div>
											<!--/span-->
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">节点单据号 <span
														class="required" aria-required="true"> * </span></label>
													<div class="">
														<input type="text" class="form-control" name="nodeNum"
															ng-model="paymentRecord.nodeNum" ng-show="input"/>
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-show="span">{{pay.nodeNum}}</p>
													</div>
												</div>
											</div>
											<!--/span-->
										</div>


										<div class="row">
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">发票方式<span
														class="required" aria-required="true"> * </span></label>
													<div class="">先款后票</div>
												</div>
											</div>
											<!--/span-->
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">是否开票<span
														class="required" aria-required="true"> * </span></label>
													<div class="">
													<span ng-show="input">
														<input type="radio" ng-model="paymentRecord.isBill"
															ng-checked="true" name="isBill" value="1">是 <input
															type="radio" ng-model="paymentRecord.isBill"
															name="isBill" value="0">否
												   </span>	
															<p class="form-control-static" ng-show="span">{{pay.isBill}}</p>
													</div>
												</div>
											</div>

											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">申请日期<span
														class="required" aria-required="true"> * </span></label>
													<div class="">
														<input type="text" class="form-control" name="applyDate"
														data-date-format="yyyy-mm-dd" id="applyDate"
													    data-date-viewmode="years" size="16"
															ng-model="paymentRecord.applyDate" ng-show="input"/>
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-show="span">{{pay.applyDate}}</p>
													</div>
												</div>
											</div>
										</div>
										<!--/span-->

										<!--/row-->
										<div class="row">
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">申请人<span
														class="required" aria-required="true"> * </span></label>
													<div class="">
														<input type="text" class="form-control" name="applicant"
															ng-model="paymentRecord.applicant" ng-show="input"/>
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-show="span">{{pay.applicant}}</p>
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
														<p class="form-control-static" ng-show="span">{{pay.applyDept}}</p>
													</div>
												</div>
											</div>
											<!--/span-->
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">备注 </label>
													<div class="">
														<input type="text" class="form-control" name="remark"
															ng-model="paymentRecord.remark" ng-show="input"/>
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-show="span">{{pay.remark}}</p>
													</div>
												</div>
											</div>
											<!--/span-->
										</div>

									</div>
									<!--/row-->
								</div>
							</div>


							<div class="tab-pane fade" id="tab_1_2">
								<div class="portlet-body form">
									<!-- BEGIN FORM-->
									<div class="form-body">
										<div class="alert alert-danger display-hide">
											<button class="close" data-close="alert"></button>
											请先输入正确数据！
										</div>
										<div class="row">
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">收款方 <span
														class="required" aria-required="true"> * </span></label>
													<div class="">
														<input type="text" class="form-control" name="payee"
															ng-model="paymentRecord.payee" ng-show="input"/>
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-show="span">{{pay.payee}}</p>
													</div>
												</div>
											</div>
											<!--/span-->
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">收款方联系人<span
														class="required" aria-required="true"> * </span> </label>
													<div class="">
														<input type="text" class="form-control" name="contact"
															ng-model="paymentRecord.contact" ng-show="input"/>
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-show="span">{{pay.contact}}</p>
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
															ng-model="paymentRecord.contactNum" ng-show="input"/>
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-show="span">{{pay.contactNum}}</p>
													</div>
												</div>
											</div>
											<!--/span-->
										</div>
										<!--/row-->
										<div class="row">
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">收款银行 <span
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
														<p class="form-control-static" ng-show="span">{{pay.bank}}</p>
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
															ng-model="paymentRecord.accountName" ng-show="input"/>
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-show="span">{{pay.accountName}}</p>
													</div>

												</div>
											</div>
											<!--/span-->
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">账号 <span
														class="required" aria-required="true"> * </span></label>
													<div class="">
														<input type="text" class="form-control" name="accountNumber"
															ng-model="paymentRecord.accountNumber" ng-show="input"/>
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-show="span">{{pay.accountNumber}}</p>
													</div>
												</div>
											</div>
											<!--/span-->
										</div>

										<!--row-->
										<div class="row">
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">应付金额 <span
														class="required" aria-required="true"> * </span></label>
													<div class="">{{saleOrder.orderAmount| currency:'￥'}}</div>

												</div>
											</div>
											<!--/span-->
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">预付金额 <span
														class="required" aria-required="true"> * </span></label>
													<div class="">{{0| currency:'￥'}}</div>

												</div>
											</div>
											<!--/span-->
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">已付金额 <span
														class="required" aria-required="true"> * </span></label>
													<div class="">{{saleOrder.paiedMoney| currency:'￥'}}</div>
												</div>
											</div>
											<!--/span-->
										</div>
										<!--row-->
										<div class="row">
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">未付金额 <span
														class="required" aria-required="true"> * </span></label>
													<div class="">{{saleOrder.orderAmount-saleOrder.paiedMoney| currency:'￥'}}</div>

												</div>
											</div>
											<!--/span-->
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">已开票金额 <span
														class="required" aria-required="true"> * </span></label>
													<div class="">{{saleOrder.billedMoney| currency:'￥'}}</div>

												</div>
											</div>
											<!--/span-->
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">未开票金额 <span
														class="required" aria-required="true"> * </span></label>
													<div class="">{{saleOrder.orderAmount-saleOrder.billedMoney| currency:'￥'}}</div>
												</div>
											</div>
											<!--/span-->
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
				<h4 class="modal-title">选择采购订单</h4>
			</div>
			<div class="modal-body">
				<table
					class="table table-striped table-bordered table-hover table-checkable order-column"
					id="sample_21">
					<thead>
						<tr>
							<th style="text-align: center"><input name="select_all"
								id="example-select-all" type="checkbox" /></th>
							<th>采购订单号</th>
							<th>供应方</th>
							<th>采购商品</th>
							<th>金额</th>
							<th>配送</th>
							<th>服务模式</th>
							<th>关联采购合同</th>
							<th>关联销售单</th>
							<th>下单日期</th>
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

	$('#makeDate').datepicker({
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
</script>
