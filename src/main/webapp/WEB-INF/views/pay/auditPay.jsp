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
				<form action="#" id="form_sample_1" class="">
					<div class="portlet light ">
						<ul class="nav nav-tabs">
							<li class="active bold"><a data-target="#tab_1_1"
								data-toggle="tab">付款信息</a></li>
							<li class="bold"><a data-target="#tab_1_2" data-toggle="tab">收款信息</a>
							<li class="bold"><a data-target="#tab_1_3" data-toggle="tab">附件信息</a>
							</li>
						</ul>
						<div class="tab-content">
							<div class="tab-pane fade active in" id="tab_1_1">

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
														<p class="form-control-static">{{pay.paymentNum}}</p>
													</div>
												</div>
											</div>
											<!--/span-->

											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">付款类型<span
														class="required" aria-required="true"> * </span></label>
													<div class="">
														<p class="form-control-static">{{pay.paymentType}}</p>
													</div>
												</div>
											</div>
											<!--/span-->


											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">关联采购单号<span
														class="required" aria-required="true"> * </span></label>
													<div class="">
														<p class="form-control-static">{{pay.orderNum}}</p>
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
														<p class="form-control-static">{{pay.orderAmount| currency:'￥'}}</p>
													</div>

												</div>
											</div>
											<!--/span-->
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">申请付款金额<span
														class="required" aria-required="true"> * </span></label>
													<div class="">
														<p class="form-control-static">{{pay.applyPaymentAmount| currency:'￥'}}</p>
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
														<p class="form-control-static">{{chnAmount}}</p>
													</div>
												</div>
											</div>
											<!--/span-->
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">申请币种<span
														class="required" aria-required="true"> * </span></label>
													<div class="">
														<p class="form-control-static">{{pay.applyCurrency}}</p>
													</div>
												</div>
											</div>
											<!--/span-->
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">申请付款日期<span
														class="required" aria-required="true"> * </span></label>
													<div class="">
														<p class="form-control-static">{{pay.playPaymentDate}}</p>
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
														<p class="form-control-static">{{pay.payType}}</p>
													</div>
												</div>
											</div>
											<!--/span-->
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">支付节点<span
														class="required" aria-required="true"> * </span></label>
													<div class="">
														<p class="form-control-static">{{pay.paymentNode}}</p>
													</div>
												</div>
											</div>
											<!--/span-->
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">节点单据号 <span
														class="required" aria-required="true"> * </span></label>
													<div class="">
														<p class="form-control-static">{{pay.nodeNum}}</p>
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
														<p class="form-control-static" ng-if="pay.isBill=='1'">是</p>
												        <p class="form-control-static" ng-if="pay.isBill=='0'">否</p>
													</div>
												</div>
											</div>

											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">申请日期<span
														class="required" aria-required="true"> * </span></label>
													<div class="">
														<p class="form-control-static" >{{pay.applyDate}}</p>
													</div>
												</div>
											</div>
										</div>
										<!--/span-->

										<!--/row-->
										<div class="row">
											
											<div class="col-md-12">
												<div class="form-group">
													<label class="control-label bold">申请说明 </label>
													<div class="">														
														<p class="form-control-static">{{pay.reason}}</p>
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
														<p class="form-control-static">{{pay.payee}}</p>
													</div>
												</div>
											</div>
											<!--/span-->
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">收款方联系人<span
														class="required" aria-required="true"> * </span>
													</label>
													<div class="">
														<p class="form-control-static">{{pay.contact}}</p>
													</div>
												</div>
											</div>
											<!--/span-->
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">联系电话 <span
														class="required" aria-required="true"> * </span></label>
													<div class="">
														<p class="form-control-static">{{pay.contactNum}}</p>
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
														<p class="form-control-static">{{pay.bank}}</p>
													</div>

												</div>
											</div>
											<!--/span-->
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">户名 <span
														class="required" aria-required="true"> * </span></label>
													<div class="">
														<p class="form-control-static">{{pay.accountName}}</p>
													</div>

												</div>
											</div>
											<!--/span-->
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">账号 <span
														class="required" aria-required="true"> * </span></label>
													<div class="">
														<p class="form-control-static">{{pay.accountNumber}}</p>
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
													<div class="">{{pay.orderAmount| currency:'￥'}}</div>

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
													<div class="">{{pay.paiedMoney| currency:'￥'}}</div>
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
													<div class="">{{pay.orderAmount-pay.paiedMoney|
														currency:'￥'}}</div>

												</div>
											</div>
											<!--/span-->
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">已开票金额 <span
														class="required" aria-required="true"> * </span></label>
													<div class="">{{pay.billedMoney| currency:'￥'}}</div>

												</div>
											</div>
											<!--/span-->
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">未开票金额 <span
														class="required" aria-required="true"> * </span></label>
													<div class="">{{pay.orderAmount-pay.billedMoney|
														currency:'￥'}}</div>
												</div>
											</div>
											<!--/span-->
										</div>
									</div>
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
							<div class="portlet box green">
	                             <div class="portlet-title">
	                                 <div class="caption">
	                                     <i class="fa fa-globe"></i>评论 </div>
	                             </div>
	                             <div class="portlet-body">
	                                 <table class="table table-striped table-bordered table-hover order-column" id="pinglun">
	                                     <thead>
	                                         <tr>
	                                             <th>评论人</th>
	                                             <th>评论时间</th>
	                                             <th>评论内容</th>
	                                         </tr>
	                                     </thead>
	                                     <tbody id = "comment_audit">	
	                                        </tbody>
	                                 </table>
	                             </div>
	                         </div>
						 </div>
						
					
						<div class="row">
							<div class="form-group form-md-line-input">
								<label class="col-md-1 control-label" for="form_control_1">我的意见:</label>
								<div class="col-md-11">
									<textarea class="form-control" ng-model="content"
										id="content" name="content" rows="1"></textarea>
									<div class="form-control-focus"></div>
									<span class="help-block">输入我的意见</span>
								</div>
							</div>
							<input type="hidden" name="serialNum" id="serialNum" value="" />
							<input type="hidden" name="taskId" id="taskId" value="" />
						</div>
						
					</div>
					
					
					
				</form>
				
				<div class="modal-footer">
					<button type="submit" ng-click="apPass()"
						class="btn blue">
						<i class="glyphicon glyphicon-ok"></i> 通过
					</button>
					<button type="submit" ng-click="apUnPass()"
						class="btn red">
						<i class="glyphicon glyphicon-remove"></i> 不通过
					</button>					
					<button type="submit" ng-click="backDbList()"
						class="btn green-meadow">
						<i class="glyphicon glyphicon-share-alt"></i> 返回
					</button>
				</div>
			</div>
		</div>
	</div>
</div>
