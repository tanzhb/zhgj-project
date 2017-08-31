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
<h3 class="page-title"> 检验信息
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
     <form  id="invoiceForm" class="form-horizontal"   >
        <!-- BEGIN EXAMPLE TABLE PORTLET-->
        <div class="portlet bordered">
            <div class="portlet-body">
				<div class="portlet light ">
                        <div class="portlet-title">
                            <div class="caption"><span ng-if="inOrOut.indexOf('in')>-1" >进项票信息</span><span  ng-if="inOrOut.indexOf('out')>-1">销项票信息</span></div>    
                            <div class="actions">
                                <button  ng-show="invoiceView"    class="btn blue  btn-outline  btn-sm " ng-click="editInvoice()">
                                            <i class="fa fa-edit"></i> 编辑 </button>
                                <button   ng-hide="invoiceEdit"    ng-if="inOrOut.length>3"   class="btn red  btn-outline  btn-sm " ng-click="cancelEditInvoice()">
                                            <i class="fa fa-undo"></i> 取消 </button>
                                            <button   ng-hide="invoiceEdit"    ng-if="inOrOut.length<=3"   class="btn blue  btn-outline  btn-sm " ui-sref="invoice">
                                            <i class="fa fa-undo"></i> 取消 </button>
                                <button  ng-hide="invoiceAdd"   type="submit"   class="btn blue  btn-outline  btn-sm "   ng-click="saveInvoice()">
                                            <i class="fa fa-save"></i> 保存 </button>
                            </div>
                        </div>
                        <div class="portlet-body form">
                           
								<div class="form-body">
									<div class="alert alert-danger display-hide">
                                               <button class="close" data-close="alert"></button>请先输入正确数据！</div>
								           <div class="row">
                                                             <div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="invoiceNum"> <span class="required"> * </span>	发票编号 :</label>
                                                    <div class="col-md-8">
                                                        <input type="text" class="form-control" id="invoiceNum" name="invoiceNum" ng-model="invoice.invoiceNum"  ng-hide="invoiceAdd" />
                                                        <div class="form-control-focus"> </div>
                                                          <p class="control-label left"   ng-show="invoiceView">{{invoice.invoiceNum}}</p> 
                                                         <!--  存放物料流水号-->
                                                    </div>
                                            </div>
										</div>
										<input type="hidden"   id="deliverSerial" ng-model="invoice.deliverSerial"  />
										<input type="hidden"     id="takeDeliverSerial"   ng-model="invoice.takeDeliverSerial"  />
                                                           <div class="col-md-4">
                                                                <div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for=""><span class="required"> * </span> <span  ng-if="inOrOut.indexOf('in')>-1"  >开票方</span><span   ng-if="inOrOut.indexOf('out')>-1" >收票方</span> :</label>
                                                    <div class="col-md-6">
                                                     <input type="text" class="form-control"   id="comName" name ="comName"  readonly     ng-hide="invoiceAdd"   readonly="readonly"
												ng-model="invoice.comName" /> 
												<div class="form-control-focus"> </div>
												 
                                                                        <p class="control-label left"    ng-show="invoiceView"    >{{invoice.comName}}</p> 
                                                                    </div>
                                                                      
                                                                </div>
                                                            </div> 
                                                            <div class="col-md-4">
                                                                <div class="form-group form-md-line-input">
                                                                <label class="col-md-4 control-label" for="relationBuyOrSaleNum"><span class="required"> * </span>  <span  ng-if="inOrOut.indexOf('in')>-1"  > 关联采购单号</span><span   ng-if="inOrOut.indexOf('out')>-1" > 关联销售订单号</span> :</label>
                                                                    <div class="col-md-6">
                                                                       <input type="text" class="form-control" placeholder=""  id="relationBuyOrSaleNum"      name ="relationBuyOrSaleNum"  ng-hide="invoiceAdd"   readonly
												ng-model="invoice.relationBuyOrSaleNum" > 
												 
                                                                       <div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="invoiceView"  >{{invoice.relationBuyOrSaleNum}}</p> 
                                                                    </div>
                                                                    <div class="col-md-1"><span class="input-inline-btn"   >
                                                            <button class="btn default" type="button"   ng-if="inOrOut.indexOf('in')>-1&&inOrOut.length<=3"   ng-click="selectDeliverOrTakeDelivery('in')" data-target="#takeDeliveryInfo" data-toggle="modal" >
                                                                <i class="fa fa-search"></i>
                                                            </button>
                                                              <button class="btn default" type="button"   ng-if="inOrOut.indexOf('out')>-1&&inOrOut.length<=3"   ng-click="selectDeliverOrTakeDelivery('out')" data-target="#deliverInfo" data-toggle="modal" >
                                                                <i class="fa fa-search"></i>
                                                            </button>
                                                        </span></div>
                                                                </div>
                                                            </div>  
                                                        </div>
                                                        <div class="row">
                                                            <!--/span-->
                                                            <div class="col-md-4">
                                                            <div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="supplyName">订单金额 :</label>
                                                    <div class="col-md-8">
                                                                    <input type="text" class="form-control"   id="orderAmount" name ="orderAmount"  ng-hide="invoiceAdd"  readonly="readonly"
												ng-model="invoice.orderAmount" > 
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="invoiceView"  >{{invoice.orderAmount}}</p> 
                                                                </div>
                                                            </div>
                                                            <!--/span-->
                                                        </div>
                                                        <div class="col-md-4">
                                                            <div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="billWay"><span class="required"> * </span>开票方式 :</label>
                                                    <div class="col-md-8" ng-hide="invoiceView" >
                                	<input type="radio"  ng-model="invoice.billWay" name="billWay" ng-checked="invoice.billWay!='1'" value="0"> 先票后款
                        			<input type="radio"  ng-model="invoice.billWay" name="billWay" ng-checked="invoice.billWay=='1'" value="1"> 先款后票
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="invoiceView"   ng-if="invoice.billWay==0">先票后款</p> 
                                                                        <p class="control-label left" ng-show="invoiceView"   ng-if="invoice.billWay==1">先款后票</p> 
                                                                </div>
                                                            </div>
                                                            <!--/span-->
                                                        </div>
                                                             <div class="col-md-4">
                                                            <div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="checkDate"> 未开金额 :</label>
                                                    <div class="col-md-8">
                                                                        <input type="text"     class="form-control"   placeholder=""  id="unBillAmount" name ="unBillAmount"   ng-hide="invoiceAdd"  
												ng-model="invoice.unBillAmount" /> 
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="invoiceView">{{invoice.checkDate | date:'yyyy-MM-dd'}}</p> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <!--/row-->
                                                       
                                                        <div class="row">
                                                            <div class="col-md-4">
                                                             <div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="checker"> <span class="required"> * </span> <span  ng-if="inOrOut.indexOf('in')>-1"  >是否付款</span><span   ng-if="inOrOut.indexOf('out')>-1" > 是否收款</span> :</label>
                                                    <div class="col-md-8">
                                	<input type="radio"  ng-model="invoice.paymentStatus" name="paymentStatus" ng-checked="invoice.paymentStatus!='否'" value="是"> 是
                        			<input type="radio"  ng-model="invoice.paymentStatus" name="paymentStatus" ng-checked="invoice.paymentStatus=='否'" value="否"> 否
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="invoiceView">{{invoice.paymentStatus}}</p> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <!--/span-->
                                                            <div class="col-md-4">
                                                            <div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="contactNum"> <span class="required"> * </span><span  ng-if="inOrOut.indexOf('in')>-1"  >关联付款单号</span><span   ng-if="inOrOut.indexOf('out')>-1" > 关联收款单号</span>:</label>
                                                    <div class="col-md-8">
                                                                        <input type="text"  class="form-control" placeholder=""  id="relationReceiveOrPayNum" name ="relationReceiveOrPayNum"   ng-hide="invoiceAdd"  
												ng-model="invoice.relationReceiveOrPayNum" > 
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="invoiceView">{{invoice.relationReceiveOrPayNum}}</p> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="col-md-4">
                                                             <div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="status"><span class="required"> * </span><span  ng-if="inOrOut.indexOf('in')>-1"  >付款日期</span><span   ng-if="inOrOut.indexOf('out')>-1" > 收款日期</span>:   </label>
                                                <div class="col-md-8">
                                                                        <input type="text"  class="form-control form-control-inline date-picker"     data-date-format="yyyy-mm-dd"  data-date-viewmode="years"   placeholder=""  id="receiptDate" name ="receiptDate"   ng-hide="invoiceAdd"  
												ng-model="invoice.receiptDate" /> 
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="invoiceView">{{invoice.receiptDate | date:'yyyy-MM-dd'}}</p> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <!--/span-->
                                                        </div>
                                                        
                                                         <div class="row">
                                                            <div class="col-md-4">
                                                             <div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="checker"> <span class="required"> * </span>发票金额:</label>
                                                    <div class="col-md-8">
                                                       <input type="text"  class="form-control" placeholder=""  id="invoiceAmount" name ="invoiceAmount"   ng-hide="invoiceAdd"  
												ng-model="invoice.invoiceAmount" > 
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="invoiceView">{{invoice.invoiceAmount}}</p> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <!--/span-->
                                                            <div class="col-md-4">
                                                            <div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="contactNum"> <span class="required"> * </span>发票类型 :</label>
                                                    <div class="col-md-8">
                                                                        <input type="text"  class="form-control" placeholder=""  id="invoiceType" name ="invoiceType"   ng-hide="invoiceAdd"  
												ng-model="invoice.invoiceType" /> 
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="invoiceView">{{invoice.invoiceType}}</p> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="col-md-4">
                                                             <div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="status"> <span class="required"> * </span> 开票日期: </label>
                                                 <div class="col-md-8">
                                                                        <input type="text"  class="form-control form-control-inline date-picker"     data-date-format="yyyy-mm-dd"  data-date-viewmode="years"   placeholder=""  id="billingDate" name ="billingDate"   ng-hide="invoiceAdd"  
												ng-model="invoice.billingDate" /> 
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="invoiceView">{{invoice.billingDate | date:'yyyy-MM-dd'}}</p> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <!--/span-->
                                                        </div>
                                                            <div class="row">
                                                            <div class="col-md-4">
                                                             <div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="checker"> <span class="required"> * </span>发票号:</label>
                                                    <div class="col-md-8">
                                                       <input type="text"  class="form-control" placeholder=""  id="invoiceNO" name ="invoiceNO"   ng-hide="invoiceAdd"  
												ng-model="invoice.invoiceNO" > 
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="invoiceView">{{invoice.invoiceNO}}</p> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="col-md-4">
                                                             <div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="checker"> <span class="required"> * </span>提交人:</label>
                                                    <div class="col-md-8">
                                                       <input type="text"  class="form-control" placeholder=""  id="submitter" name ="submitter"   ng-hide="invoiceAdd"  
												ng-model="invoice.submitter" > 
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="invoiceView">{{invoice.submitter}}</p> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="col-md-4">
                                                             <div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="checker"> <span class="required"> * </span>提交日期:</label>
                                                     <div class="col-md-8">
                                                                        <input type="text"  class="form-control form-control-inline date-picker"     data-date-format="yyyy-mm-dd"  data-date-viewmode="years"   placeholder=""  id="submitDate" name ="submitDate"   ng-hide="invoiceAdd"  
												ng-model="invoice.submitDate" /> 
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="invoiceView">{{invoice.submitDate | date:'yyyy-MM-dd'}}</p> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                 
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-md-4">
                                                             <div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="checker"> 备注:</label>
                                                    <div class="col-md-8">
                                                       <input type="text"  class="form-control" placeholder=""  id="billingRemark" name ="billingRemark"   ng-hide="invoiceAdd"   ng-if="inOrOut.indexOf('in')>-1" 
												ng-model="invoice.billingRemark" >
												 <input type="text"  class="form-control" placeholder=""  id="receiptRemark" name ="receiptRemark"   ng-hide="invoiceAdd"   ng-if="inOrOut.indexOf('out')>-1" 
												ng-model="invoice.receiptRemark" > 
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="invoiceView"   ng-if="inOrOut.indexOf('in')>-1"  >{{invoice.billingRemark}}</p> 
                                                                         <p class="control-label left" ng-show="invoiceView"  ng-if="inOrOut.indexOf('out')>-1"  >{{invoice.receiptRemark}}</p> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                 
                                                        </div>
                                             <div>          
                                                        </div> 
								</div>
							
         				</div>
         				
         			<!-- 	 <div class="portlet-title">订单结算条款 START
                            <div class="caption"> 订单结算条款</div>
                            <div class="actions">
                            </div>
                        </div>
                          <div class="portlet-body form">
                                   <div class="row">
                                                            <div class="col-md-4">
                                                             <div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="checker"> <span class="required"> * </span>收票日期:</label>
                                                    <div class="col-md-8">
                                                       <input type="text"  class="form-control" placeholder=""  id="checker" name ="checker"   ng-hide="invoiceAdd"  
												ng-model="invoice.checker" > 
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="invoiceView">{{invoice.checker}}</p> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            /span
                                                            <div class="col-md-4">
                                                            <div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="contactNum"> <span class="required"> * </span>操作人 :</label>
                                                    <div class="col-md-8">
                                                                        <input type="text"  class="form-control" placeholder=""  id="contactNum" name ="contactNum"   ng-hide="invoiceAdd"  
												ng-model="invoice.contactNum" > 
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="invoiceView">{{invoice.contactNum}}</p> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="col-md-4">
                                                             <div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="status">  备注: </label>
                                                 <div class="col-md-8">
                                                                        <input type="text"  class="form-control form-control-inline date-picker"     data-date-format="yyyy-mm-dd"  data-date-viewmode="years"   placeholder=""  id="checkDate" name ="checkDate"   ng-hide="invoiceAdd"  
												ng-model="invoice.checkDate" /> 
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="invoiceView">{{invoice.checkDate | date:'yyyy-MM-dd'}}</p> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            /span
                                                        </div>
                                                            <div class="row">
                                                            <div class="col-md-4">
                                                             <div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="checker"> <span class="required"> * </span>电子发票凭证:</label>
                                                    <div class="col-md-8">
                                                       <input type="text"  class="form-control" placeholder=""  id="checker" name ="checker"   ng-hide="invoiceAdd"  
												ng-model="invoice.checker" > 
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="invoiceView">{{invoice.checker}}</p> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                 
                                                        </div>
                                </div>订单结算条款 END -->
                                </form>
				</div>
				
        </div>
        <!-- END EXAMPLE TABLE PORTLET-->
<jsp:include  page="../stockInOutCheck/selectDeliverOrTakeDelivery.jsp"/>
<!-- END MAIN CONTENT -->
<!-- BEGIN MAIN JS -->
 <script>
</script> 
<!-- END MAIN JS -->