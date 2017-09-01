<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" %>
<!-- BEGIN PAGE HEADER-->
<style>
.box_card{
	width: 33.333333%;
	float: left;
	margin-left: 0px;
	margin-right: 0px;
	margin-top: 4px;
	margin-bottom: 4px;
}

.left{
	float: left;
}
</style>
<h3 class="page-title">发票
</h3>
<div class="page-bar">
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

</div>
<!-- END PAGE HEADER-->
<!-- BEGIN MAIN CONTENT -->
<div class="row">
    <div class="col-md-12">
        <!-- BEGIN EXAMPLE TABLE PORTLET-->
        <div class="portlet bordered">
            <div class="portlet-body">
				<div class="portlet light ">
                        <div class="portlet-title">
                            <div class="caption"><span ng-if="inOrOut.indexOf('in')>-1" >进项票</span><span  ng-if="inOrOut.indexOf('out')>-1">销项票</span>详情</div>
                            <div class="actions">
                                            <button   ui-sref="invoice"  class="btn blue  btn-outline  btn-sm " >
                                            <i class="fa fa-undo"></i> 返回 </button>
                            </div>
                        </div>
                        <div class="portlet-body form">
                            <form  id="stockForm" class="form-horizontal"   >
								<div class="form-body">
								           <div class="row"><!-- row START -->
                                                             <div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="invoiceNum"> 发票编号 :</label>
                                                    <div class="col-md-8">
                                                          <p class="control-label left" >{{invoice.invoiceNum}}</p> 
                                                    </div>
                                            </div>
										</div>
                                                            <div class="col-md-4">
                                                                <div class="form-group form-md-line-input">
                                                                <label class="col-md-4 control-label" for=""><span  ng-if="inOrOut.indexOf('in')>-1"  >开票方</span><span   ng-if="inOrOut.indexOf('out')>-1" >收票方</span> :</label>
                                                                    <div class="col-md-8">
                                                                        <p class="control-label left"   ng-if="inOrOut.indexOf('out')>-1"  >{{invoice.comName}}</p> 
                                                                         <p class="control-label left" ng-if="inOrOut.indexOf('in')>-1"  >{{invoice.comName}}</p> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="col-md-4">
                                                                <div class="form-group form-md-line-input">
                                                                <label class="col-md-4 control-label" for=""><span  ng-if="inOrOut.indexOf('in')>-1"  > 关联采购订单号</span><span   ng-if="inOrOut.indexOf('out')>-1" > 关联销售订单号</span> :</label>
                                                                    <div class="col-md-8">
                                                                        <p class="control-label left"  ng-if="inOrOut.indexOf('out')>-1" >{{invoice.relationBuyOrSaleNum}}</p> 
                                                                        <p class="control-label left"  ng-if="inOrOut.indexOf('in')>-1" >{{invoice.relationBuyOrSaleNum}}</p>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div><!-- row END-->
                                                        <div class="row"><!-- row START -->
                                                             <div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="comName"> 订单金额:</label>
                                                    <div class="col-md-8">
                                                          <p class="control-label left" >{{invoice.orderAmount}}</p>
                                                    </div>
                                            </div>
										</div>
                                                            <div class="col-md-4">
                                                                <div class="form-group form-md-line-input">
                                                                <label class="col-md-4 control-label" for="checkParty">开票方式 :</label>
                                                                    <div class="col-md-8">
                                                                          <p class="control-label left"   ng-if="invoice.billWay!='1'">先款后票</p> 
                                                                        <p class="control-label left"    ng-if="invoice.billWay=='1'">先票后款</p> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="col-md-4">
                                                                <div class="form-group form-md-line-input">
                                                                <label class="col-md-4 control-label" for="checkDate">未开金额 :</label>
                                                                    <div class="col-md-8">
                                                                       <p class="control-label left" >{{invoice.unBillAmount }}</p>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div><!-- row END-->
                                                        <div class="row"><!-- row START -->
                                                             <div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="checker"> <span  ng-if="inOrOut.indexOf('in')>-1"  > 是否付款</span><span   ng-if="inOrOut.indexOf('out')>-1" > 是否收款</span> :</label>
                                                    <div class="col-md-8">
                                                         <p class="control-label left" >{{invoice.paymentStatus}}</p> 
                                                    </div>
                                            </div>
										</div>
                                                            <div class="col-md-4">
                                                                <div class="form-group form-md-line-input">
                                                                <label class="col-md-4 control-label" for="contactNum"><span  ng-if="inOrOut.indexOf('in')>-1"  >关联付款单号</span><span   ng-if="inOrOut.indexOf('out')>-1" > 关联收款单号</span>  :</label>
                                                                    <div class="col-md-8">
                                                                         <p class="control-label left" >{{invoice.relationReceiveOrPayNum}}</p>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="col-md-4">
                                                                <div class="form-group form-md-line-input">
                                                                <label class="col-md-4 control-label" for="status"><span  ng-if="inOrOut.indexOf('in')>-1"  >付款日期</span><span   ng-if="inOrOut.indexOf('out')>-1" >收款日期</span>  :</label>
                                                                    <div class="col-md-8">
                                                                   <p class="control-label left" >{{invoice.receiptDate | date:'yyyy-MM-dd'}}</p> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div><!-- row END-->
                                                         <div class="row"><!-- row START -->
                                                             <div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="checker"> 发票金额 :</label>
                                                    <div class="col-md-8">
                                                         <p class="control-label left" >{{invoice.invoiceAmount}}</p> 
                                                    </div>
                                            </div>
										</div>
                                                            <div class="col-md-4">
                                                                <div class="form-group form-md-line-input">
                                                                <label class="col-md-4 control-label" for="contactNum">发票类型 :</label>
                                                                    <div class="col-md-8">
                                                                         <p class="control-label left" >{{invoice.invoiceType}}</p>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="col-md-4">
                                                                <div class="form-group form-md-line-input">
                                                                <label class="col-md-4 control-label" for="status">开票日期 :</label>
                                                                    <div class="col-md-8">
                                                                   {{invoice.billingDate | date:'yyyy-MM-dd'}}
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div><!-- row END-->
                                                         <div class="row"><!-- row START -->
                                                             <div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="checker">发票号 :</label>
                                                    <div class="col-md-8">
                                                         <p class="control-label left" >{{invoice.invoiceNO}}</p> 
                                                    </div>
                                            </div>
										</div>
                                                            <div class="col-md-4">
                                                                <div class="form-group form-md-line-input">
                                                                <label class="col-md-4 control-label" for="contactNum">提交人 :</label>
                                                                    <div class="col-md-8">
                                                                        <p class="control-label left" >{{invoice.submitter}}</p> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="col-md-4">
                                                                <div class="form-group form-md-line-input">
                                                                <label class="col-md-4 control-label" for="status">提交日期 :</label>
                                                                    <div class="col-md-8">
                                                                   <p class="control-label left" >{{invoice.submitDate | date:'yyyy-MM-dd'}}</p> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div><!-- row END-->
                                                         <div class="row"><!-- row START -->
                                                             <div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="checker"><span  ng-if="inOrOut.indexOf('in')>-1"  >收票人 </span><span   ng-if="inOrOut.indexOf('out')>-1" >审批人</span> :</label>
                                                    <div class="col-md-8">
                                                         <p class="control-label left"   ng-if="invoice.approver==null">-----</p> 
                                                          <p class="control-label left"  ng-if="invoice.approver!=null">{{invoice.approver}}</p> 
                                                    </div>
                                            </div>
										</div>
                                                            <div class="col-md-4">
                                                                <div class="form-group form-md-line-input">
                                                                <label class="col-md-4 control-label" for="contactNum"><span  ng-if="inOrOut.indexOf('in')>-1"  >收票日期 </span><span   ng-if="inOrOut.indexOf('out')>-1" >审批日期</span> : </label>
                                                                    <div class="col-md-8">
                                                                    <p class="control-label left"   ng-if="invoice.approvalDate==null">-----</p> 
                                                          <p class="control-label left"  ng-if="invoice.approvalDate!=null">{{invoice.approvalDate| date:'yyyy-MM-dd'}}</p> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="col-md-4">
                                                                <div class="form-group form-md-line-input">
                                                                <label class="col-md-4 control-label" for="status">发票凭证 :</label>
                                                                    <div class="col-md-8">
                                                               <p class="control-label left"   ng-if="invoice.invoiceVoucher==null">未上传发票凭证</p> 
                                                          <p class="control-label left"  ng-if="invoice.approver!=null">{{invoice.invoiceVoucher}}</p> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div><!-- row END-->
                                                         <div class="row"><!-- row START -->
                                                             <div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="checker"> 备注 :</label>
                                                    <div class="col-md-8">
                                                         <p class="control-label left"   ng-if="inOrOut.indexOf('in')>-1" >{{invoice.billingRemark}}</p> 
                                                          <p class="control-label left"   ng-if="inOrOut.indexOf('out')>-1" >{{invoice.receiptRemark}}</p> 
                                                    </div>
                                            </div>
										</div>
                                                            <div class="col-md-4">
                                                                <div class="form-group form-md-line-input">
                                                                <label class="col-md-4 control-label" for="status">状态 :</label>
                                                                    <div class="col-md-8">
                                                                   
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div><!-- row END-->
								</div>
							</form>
         				</div>

 <div class="portlet-title"><!-- 订单结算条款START -->
                            <div class="caption">订单结算条款</div>
                            <div class="actions">
                            </div>
                        </div>
                          <div class="portlet-body form">
                                    <div class="table-scrollable">
                                        <table class="table table-striped table-bordered table-advance table-hover"  >
                                            <thead>
                                                <tr >
                                                      <td rowspan="2"  colspan="4"   style="text-align: center;vertical-align: middle">付款方:</td>
                                                    <td  rowspan="2"    colspan="4" style="text-align: center;vertical-align: middle">收款方:</td>
                                                    <td   rowspan="2"   colspan="4" style="text-align: center;vertical-align: middle">本次应付:</td>
                                                </tr>
                                                <tr></tr>
                                            
                                             <tr >
                                                 <td style="text-align: center">支付类型</td>
                                                  <td style="text-align: center">支付节点</td>
                                                  <td  style="text-align: center">账期</td>
                                                  <td  style="text-align: center">支付比率%</td>
                                                  <td  style="text-align: center">支付金额</td>
                                                  <td  style="text-align: center">开票方式</td>
                                                  <td   style="text-align: center">开票金额</td>
                                                  <td colspan="2" style="text-align: center">未开金额</td>
                                                   <td  style="text-align: center">合格数量</td>
                                                  <td   style="text-align: center">备注</td>
                                                   <td  style="text-align: center">状态</td>
                                                </tr> 
                                                </thead>
                                            <tbody  ng-if="materials==null">
			                                             	<tr>
			                                                    <td colspan="16" align="center" >暂无数据</td>
			                                                </tr>
			                                </tbody>
                                         
                                            </tbody>
                                        </table>
                                    </div>
                                </div><!-- 订单结算条款END-->
				</div>
				
        </div>
        <!-- END EXAMPLE TABLE PORTLET-->
    </div>
</div>
<!-- END MAIN CONTENT -->
<!-- BEGIN MAIN JS -->
 <script>
</script> 
<!-- END MAIN JS -->