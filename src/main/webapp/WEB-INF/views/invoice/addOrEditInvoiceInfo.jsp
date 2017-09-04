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
<h3 class="page-title"> 发票信息
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
                                                     <input type="text" class="form-control"   id="comName" name ="comName"      ng-hide="invoiceAdd"   
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
                                                                       <input type="text" class="form-control" placeholder=""  id="relationBuyOrSaleNum"      name ="relationBuyOrSaleNum"  ng-hide="invoiceAdd"   
												ng-model="invoice.relationBuyOrSaleNum" > 
												 
                                                                       <div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="invoiceView"  >{{invoice.relationBuyOrSaleNum}}</p> 
                                                                    </div>
                                                                    <div class="col-md-1"><span class="input-inline-btn"   >
                                                            <button class="btn default" type="button"    ng-hide="invoiceAdd"  ng-if="inOrOut.indexOf('in')>-1&&inOrOut.length<=3"   ng-click="selectDeliverOrTakeDelivery('in')" data-target="#takeDeliveryInfo" data-toggle="modal" >
                                                                <i class="fa fa-search"></i>
                                                            </button>
                                                              <button class="btn default" type="button"    ng-hide="invoiceAdd"  ng-if="inOrOut.indexOf('out')>-1&&inOrOut.length<=3"   ng-click="selectDeliverOrTakeDelivery('out')" data-target="#deliverInfo" data-toggle="modal" >
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
                                                    <div class="col-md-8" >
                                                    <div  ng-hide="invoiceAdd" >
                                	<input type="radio"  ng-model="invoice.billWay" name="billWay" ng-checked="invoice.billWay=='1'" value="1"> 先票后款
                        			<input type="radio"  ng-model="invoice.billWay" name="billWay" ng-checked="invoice.billWay!='1'" value="0"> 先款后票
                        			</div>
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="invoiceView"   ng-if="invoice.billWay!='1'">先款后票</p> 
                                                                        <p class="control-label left" ng-show="invoiceView"   ng-if="invoice.billWay=='1'">先票后款</p> 
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
                                                    <div ng-hide="invoiceAdd" >
                                	<input type="radio"  ng-model="invoice.paymentStatus" name="paymentStatus" ng-checked="invoice.paymentStatus=='是'" value="是"> 是
                        			<input type="radio"  ng-model="invoice.paymentStatus" name="paymentStatus" ng-checked="invoice.paymentStatus!='是'" value="否"> 否
                        			</div>
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="invoiceView"  ng-if="invoice.paymentStatus!='是'" >否</p> 
                                                                          <p class="control-label left" ng-show="invoiceView"  ng-if="invoice.paymentStatus=='是'" >是</p> 
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
                                                    <select class="form-control" id="invoiceType"  ng-hide="invoiceAdd" name="invoiceType"  ng-model="invoice.invoiceType"  >
                                            <option value=""></option>
                                            <option value="服务费发票" >服务费发票</option>
                                           	<option value="采购发票" >采购发票</option>
                                             
                                        </select>
                                                 
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
                                                     
                                                                   <div class="row"><!-- row START -->
                                                             <div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="approver"><span class="required"> * </span><span  ng-if="inOrOut.indexOf('in')>-1"  >收票人 </span><span   ng-if="inOrOut.indexOf('out')>-1" >审批人</span> :</label>
                                                       <div class="col-md-8">
                                                       <input type="text"  class="form-control" placeholder=""  id="approver" name ="approver"   ng-hide="invoiceAdd"  
												ng-model="invoice.approver" > 
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="invoiceView">{{invoice.approver}}</p> 
                                                                    </div>
                                            </div>
										</div>
                                                            <div class="col-md-4">
                                                                <div class="form-group form-md-line-input">
                                                                <label class="col-md-4 control-label" for="approvalDate"><span class="required"> * </span><span  ng-if="inOrOut.indexOf('in')>-1"  >收票日期 </span><span   ng-if="inOrOut.indexOf('out')>-1" >审批日期</span> : </label>
                                                                       <div class="col-md-8">
                                                       <input type="text"  class="form-control form-control-inline date-picker"     data-date-format="yyyy-mm-dd"   placeholder=""  data-date-viewmode="years"  id="approvalDate" name ="approvalDate"  ng-hide="invoiceAdd"   
												ng-model="invoice.approvalDate" > 
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="invoiceView">{{invoice.approvalDate| date:'yyyy-MM-dd'}}</p> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="col-md-4">
                                                                <div class="form-group form-md-line-input">
                                                                <label class="col-md-4 control-label" for="invoiceVoucher">发票凭证 :</label>
                                                                <div class="col-md-6">
                                                                      <div class="col-md-9">
				                                         <div ng-hide="invoiceAdd"  ng-if="invoice.invoiceVoucher==null||invoice.invoiceVoucher==''"  class="fileinput fileinput-new" data-provides="fileinput">
	                                                        <span class="btn blue btn-outline btn-file">
	                                                            <span class="fileinput-new">上传发票凭证</span>
	                                                            <span class="fileinput-exists">更改</span>
	                                                            <input type="file" name="..." nv-file-select uploader="uploader" onchange="angular.element(this).scope().up(this.files[0])" ng-model="invoice" ng-click="uploadFile(invoice)" > </span>
	                                                        <span class="fileinput-filename">{{invoice.invoiceVoucher.substring(invoice.invoiceVoucher.indexOf("_")+1)}}</span> &nbsp;
	                                                        <a href="javascript:;" class="close fileinput-exists" ng-click="removefile(invoice)" data-dismiss="fileinput"> </a>
	                                                    </div>
	                                                    <div ng-hide="invoiceAdd"   ng-if="invoice.invoiceVoucher!=null&&invoice.invoiceVoucher!=''"  class="fileinput fileinput-exists" data-provides="fileinput">
	                                                        <span class="btn blue btn-outline btn-file">
	                                                            <span class="fileinput-new">上传附件</span>
	                                                            <span class="fileinput-exists">更改</span>
	                                                            <input type="file" name="..." nv-file-select uploader="uploader" onchange="angular.element(this).scope().up(this.files[0])" ng-model="invoice" ng-click="uploadFile(invoice)" > </span>
	                                                        <span class="fileinput-filename">{{invoice.invoiceVoucher.substring(invoice.invoiceVoucher.indexOf("_")+1)}}</span> &nbsp;
	                                                        <a href="javascript:;" class="close fileinput-exists"  ng-click="removefile(priceList)" data-dismiss="fileinput"> </a>
	                                                    </div>
                                                    	<label   ng-show="invoiceView" ng-if="invoice.invoiceVoucher==null||invoice.invoiceVoucher==''" class="c_edit" >未上传发票凭证</label>
                                                    	<label   ng-show="invoiceView" ng-if="invoice.invoiceVoucher!=null&&invoice.invoiceVoucher!=''" class="c_edit" ><a href="javascript:;" title="下载附件" ng-click="downloadFile(invoice)">{{invoice.invoiceVoucher.substring(invoice.invoiceVoucher.indexOf("_")+1)}}</a></label>
												</div>
												</div>
                                                                </div>
                                                            </div>
                                                        </div><!-- row END-->
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