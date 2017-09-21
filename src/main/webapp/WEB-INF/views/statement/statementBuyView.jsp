<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- BEGIN PAGE HEADER-->
<style>
.left{
	float: left;
}
</style>
<!-- <h3 class="page-title d_tip"> 查看客户对账单
</h3> -->
<!-- <div class="page-bar">
    <ul class="page-breadcrumb">
        <li>
            <i class="fa fa-home"></i>
            <a ui-sref="dashboard">首页</a>
            <i class="fa fa-angle-right"></i>
        </li>
        <li>
            <a ui-sref="statement">对账单</a>
            <i class="fa fa-angle-right"></i>
        </li>
        <li>
            <a class="d_tip">查看客户对账单</a>
        </li>
    </ul>

</div> -->
<!-- END PAGE HEADER-->
<!-- BEGIN MAIN CONTENT -->
<div class="row">
    <div class="col-md-12">
    	<form  id="form_sample_2" class="form-horizontal"  >
        <!-- BEGIN EXAMPLE TABLE PORTLET-->
        <div class="portlet bordered">
            <div class="portlet-body">
				<div class="portlet light ">
				 	
					<!-- 基本信息START -->
                        <div class="portlet-title">
                            <div class="caption">基本信息</div>
                            <div class="actions">
                              <!--   <button    class="btn blue  btn-outline  btn-sm " ng-click="editstatement()">
                                            <i class="fa fa-edit"></i> 编辑 </button>
                                <button   ng-show="statementEdit" class="btn red  btn-outline  btn-sm " ng-click="cancelstatement('statement')">
                                            <i class="fa fa-undo"></i> 取消 </button>
                                <button   ng-hide="statementAdd" class="btn blue  btn-outline  btn-sm " ng-click="savestatement()">
                                            <i class="fa fa-save"></i> 保存 </button> -->
                            </div>
                        </div>
                        <div class="portlet-body form">
								<div class="form-body">
									<div class="alert alert-danger display-hide">
                                                <button class="close" data-close="alert"></button>请先输入正确数据！</div>
									<div class="row">
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="statementNum"> 对账单号：</label>
                                                    <div class="col-md-8">
                                                         <p class="control-label left" >{{statement.statementNum}}</p>
                                                    </div>
                                            </div>
										</div>
						
										<!--/span-->
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="buyComId"> 客户：</label>
                                                    <div class="col-md-8">
                                                         <p class="control-label left" >{{statement.buyName}}</p>
                                                    </div>
                                            </div>
										</div>
										<!--/span-->
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
													<label class="col-md-4 control-label" for="statementDate"> 对账日期：</label>
                                                    <div class="col-md-8">
                                                        <p class="control-label left" >{{statement.statementDate}}</p>
                                                    </div>
                                            </div>
										</div>
										<!--/span-->
									</div>
									<!--/row-->
									<div class="row">
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="supplyComId">期初应收：</label>
                                                    <div class="col-md-8">
                                                         <p class="control-label left" >{{statement.beginShouldPay|currency:'￥'}}</p>
                                                    </div>
                                            </div>
										</div>
						
										<!--/span-->
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="shipper">本期应收：</label>
                                                    <div class="col-md-8">
                                                         <p class="control-label left" >{{statement.nowShouldPay|currency:'￥'}}</p>
                                                    </div>
                                            </div>
										</div>
										<!--/span-->
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="receiver">本期已收：</label>
                                                    <div class="col-md-8">
                                                         <p class="control-label left" >{{statement.nowAlreadyPay|currency:'￥'}}</p>
                                                    </div>
                                            </div>
										</div>
										<!--/span-->
									</div>
									<!--/row-->
									<div class="row">
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="maker">期末应收：</label>
                                                    <div class="col-md-8">
                                                         <p class="control-label left">{{statement.endShouldPay|currency:'￥'}}</p>
                                                    </div>
                                            </div>
										</div>
						
										<!--/span-->
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="makerDate">超期款：</label>
                                                    <div class="col-md-8">
                                                         <p class="control-label left">{{statement.overTimeAmout|currency:'￥'}}</p>
                                                    </div>
                                            </div>
										</div>
										<!--/span-->
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="approval">本期扣款：</label>
                                                    <div class="col-md-8">
                                                         <p class="control-label left">{{statement.serviceAmount|currency:'￥'}}</p>
                                                    </div>
                                            </div>
										</div>
										<!--/span-->
									</div>
									<!--/row-->
									<div class="row">
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="maker"> 制单人：</label>
                                                    <div class="col-md-8">
                                                         <p class="control-label left" >{{statement.maker}}</p>
                                                    </div>
                                            </div>
										</div>
						
										<!--/span-->
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" > 制单日期：</label>
                                                    <div class="col-md-8">
                                                         <p class="control-label left" >{{statement.makeDate}}</p>
                                                    </div>
                                            </div>
										</div>
										<!--/span-->
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label"> 备注：</label>
                                                    <div class="col-md-8">
                                                         <p class="control-label left" >{{statement.remark}}</p>
                                  					</div>
                                            </div>
										</div>
										<!--/span-->
									</div>
									<!--/row-->
								</div>
         				</div>
         			<!-- 基本信息END -->
					
         			<!-- 账单信息START -->
                        <div class="portlet-title">
                            <div class="caption">账单信息</div>
                            <div class="actions">
                            </div>
                        </div>
                        <div class="portlet-body">
						<div class="table-scrollable">
							<table
								class="table table-striped table-bordered table-advance table-hover">
								<thead>
									<tr>
										<th>采购单号</th>
										<th>采购合同号</th>
										<th>下单日期</th>
										<th>订单金额</th>
										<th>应收金额</th>
										<th>已收金额</th>
										<th>未收金额</th>
										<th>超期金额</th>
										<th>服务扣除金额</th>
										<th>已开票金额</th>
										<th>未开票金额</th>
										<th>订单状态</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody> 
									<tr ng-repeat="info in orderList track by $index" >
										<td>{{info.orderNum}}</td>
										<td>{{info.orderNum}}</td>
										<td>{{info.orderDate}}</td>
										<td>{{info.totalMoney|currency:'￥'}}</td>
										<td>{{info.paymentMoney|currency:'￥'}}</td>
										<td>{{info.totalPaymentAmount|currency:'￥'}}</td>
										<td>{{info.totalUnPaymentMoney|currency:'￥'}}</td>
										<td>{{info.overDueMoney|currency:'￥'}}</td>
										<td>{{info.totalServiceMoney|currency:'￥'}}</td>
										<td>{{info.totalReadyAmount|currency:'￥'}}</td>
										<td>{{info.totalUnReadyAmount|currency:'￥'}}</td>
										<td>{{info.orderStatus}}</td>
										<td></td>
									</tr>
									<tr ng-if="orderList!=null&&orderList.length>0">
										<td></td>
										<td></td>
										<td>合计：</td>
										<td>{{totalMoney|currency:'￥'}}</td>
										<td>{{paymentMoney|currency:'￥'}}</td>
										<td>{{totalPaymentAmount|currency:'￥'}}</td>
										<td>{{totalUnPaymentMoney|currency:'￥'}}</td>
										<td>{{overDueMoney|currency:'￥'}}</td>
										<td>{{totalServiceMoney|currency:'￥'}}</td>
										<td>{{totalReadyAmount|currency:'￥'}}</td>
										<td>{{totalUnReadyAmount|currency:'￥'}}</td>
										<td></td>
									</tr>
									<tr ng-if="orderList==null||orderList.length==0">
											<td colspan="13" align="center">暂无数据</td>
									</tr> 
								</tbody>
							</table>
						</div>
					 </div>
         			<!-- 账单信息END -->
         			
         			<!-- 收款信息START -->
         			<ul class="nav nav-tabs" id="statementy_tab">
				        <li class="active">
				            <a data-target="#tab_15_1" data-toggle="tab" >本期应收</a>
				        </li>
				        <li>
				            <a data-target="#tab_15_2" data-toggle="tab" >本期已收</a>
				        </li>
				        <!-- <li>
				            <a data-target="#tab_15_3" data-toggle="tab" >本期扣款</a>
				        </li> -->
				    </ul>
					<div class="tab-content">
						 <div class="tab-pane active" id="tab_15_1">
								<div class="table-scrollable">
									<table
										class="table table-striped table-bordered table-advance table-hover">
										<thead>
											<tr>
												<th>收款计划单号</th>
												<th>关联采购订单号</th>
												<th>计划收款日期</th>
												<th>收款节点</th>
												<th>收款金额</th>
												<th>收款状态</th>
												<th>账期</th>
												<th>利息</th>
												<th>是否开票</th>
												<th>发票日期</th>
												<th>发票编号</th>
												<th>操作</th>
											</tr>
										</thead>
										<tbody> 
											<tr ng-repeat="payment in paymentList track by $index">
												<td>{{payment.paymentPlanNum}}</td>
												<td>{{payment.orderNum}}</td>
												<td>{{payment.paymentPlanDate}}</td>
												<td>{{payment.paymentNode}}</td>
												<td>{{payment.paymentAmount|currency:'￥'}}</td>
												<td>{{payment.paymentStatus}}</td>
												<td>{{payment.period}}</td>
												<td>{{payment.interest}}</td>
												<td>{{payment.isBill}}</td>
												<td>{{payment.billDate}}</td>
												<td>{{payment.billNum}}</td>
												<td></td>
											</tr>
											<tr  ng-if="paymentList!=null&&paymentList.length>0">
												<td></td>
												<td></td>
												<td></td>
												<td>合计：</td>
												<td>{{paymentTotal|currency:'￥'}}</td>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
											</tr>
											<tr ng-if="paymentList==null||paymentList.length==0">
												<td colspan="12" align="center">暂无数据</td>
											</tr>
										</tbody>
									</table>
								</div>
						 </div>
						 <div class="tab-pane" id="tab_15_2">
						 		<div class="table-scrollable">
									<table
										class="table table-striped table-bordered table-advance table-hover">
										<thead>
											<tr>
												<th>收款计划单号</th>
												<th>关联采购订单号</th>
												<th>计划收款日期</th>
												<th>收款节点</th>
												<th>收款金额</th>
												<th>收款状态</th>
												<th>账期</th>
												<th>利息</th>
												<th>是否开票</th>
												<th>发票日期</th>
												<th>发票编号</th>
												<th>操作</th>
											</tr>
										</thead>
										<tbody> 
											<tr ng-repeat="payment in alreadyPaymentList track by $index" >
												<td>{{payment.paymentPlanNum}}</td>
												<td>{{payment.orderNum}}</td>
												<td>{{payment.paymentPlanDate}}</td>
												<td>{{payment.paymentNode}}</td>
												<td>{{payment.paymentAmount|currency:'￥'}}</td>
												<td>{{payment.paymentStatus}}</td>
												<td>{{payment.period}}</td>
												<td>{{payment.interest}}</td>
												<td>{{payment.isBill}}</td>
												<td>{{payment.billDate}}</td>
												<td>{{payment.billNum}}</td>
												<td></td>
											</tr>
											<tr  ng-if="alreadyPaymentList!=null&&alreadyPaymentList.length>0">
												<td></td>
												<td></td>
												<td></td>
												<td>合计：</td>
												<td>{{alreadyPaymentTotal|currency:'￥'}}</td>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
											</tr>
											<tr ng-if="alreadyPaymentList==null||alreadyPaymentList.length==0" >
												<td colspan="12" align="center">暂无数据</td>
											</tr>
										</tbody>
									</table>
								</div>
						 </div>
						 <div class="tab-pane" id="tab_15_3">
						 		<div class="table-scrollable">
									<table
										class="table table-striped table-bordered table-advance table-hover">
										<thead>
											<tr>
												<th>收款计划单号</th>
												<th>关联采购订单号</th>
												<th>计划收款日期</th>
												<th>收款节点</th>
												<th>收款金额</th>
												<th>收款状态</th>
												<th>账期</th>
												<th>利息</th>
												<th>是否开票</th>
												<th>发票日期</th>
												<th>发票编号</th>
												<th>操作</th>
											</tr>
										</thead>
										<tbody> 
											<tr ng-repeat="materiel in orderMateriels track by $index" repeat-done="repeatDone(this)">
												<td></td>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
											</tr>
											<tr ng-if="orderMateriels==undefined||orderMateriels.length==0">
													<td colspan="12" align="center">暂无数据</td>
											</tr>
										</tbody>
									</table>
								</div>
						 </div>
                       
         			<!-- 收款信息END -->
       			 </div>
       			
    		</div>
		</div>
		 </form>
	</div>
</div>
<!-- END MAIN CONTENT -->