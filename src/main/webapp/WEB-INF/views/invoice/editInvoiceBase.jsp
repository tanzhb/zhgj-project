<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 采购订单基本信息 START -->

<div class="tab-content">
	<div class="tab-pane fade active in" id="tab_1_1">
	<div class="portlet-title" style="min-height: 30px;">
              <div class="tools" style="float:right" id="noprintdiv">
                        	 <button  ng-show="invoiceView"    class="btn purple  btn-sm btn-circle " ng-click="editInvoice()">
                                            <i class="fa fa-edit"></i> 编辑 </button>
                                <button   ng-hide="invoiceEdit"    ng-if="inOrOut.length>3"   class="btn purple  btn-sm btn-circle " ng-click="cancelEditInvoice()">
                                            <i class="fa fa-undo"></i> 取消 </button>
                                <button  ng-hide="invoiceAdd"   type="submit"   class="btn green  btn-sm btn-circle"   ng-click="saveInvoice()">
                                            <i class="fa fa-save"></i> 保存 </button>
                         </div>
                   	</div>
         <div class="portlet-body form">
          <form  id="invoiceForm"  >
          <!-- <div class="alert alert-danger display-hide">
                                               <button class="close" data-close="alert"></button>请先输入正确数据！</div>
								           <div class="row">
                                                             <div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="invoiceNum"> <span class="required"> * </span>	<span  ng-if="inOrOut.indexOf('in')>-1"  > 进项</span><span   ng-if="inOrOut.indexOf('out')>-1" > 销项</span>发票编号 :</label>
                                                    <div class=" ">
                                                        <input type="text" class="form-control" id="invoiceNum" name="invoiceNum" ng-model="invoice.invoiceNum"  ng-hide="invoiceAdd" />
                                                        <div class="form-control-focus"> </div>
                                                          <p class="control-label left"   ng-show="invoiceView">{{invoice.invoiceNum}}</p> 
                                                          存放物料流水号
                                                    </div>
                                            </div>
										</div>
										
										<div class="col-md-4">
                                                            <div class="form-group">
                                                    <label class="control-label bold" for="contactNum"> <span class="required"> * </span>发票类型 :</label>
                                                    <div class=" ">
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
                                                                <div class="form-group">
                                                                <label class="control-label bold" for="relationBuyOrSaleNum"><span class="required"> * </span>  <span  ng-if="inOrOut.indexOf('in')>-1"  > 关联采购单号</span><span   ng-if="inOrOut.indexOf('out')>-1" > 关联销售订单号</span> :</label>
                                                                    <div class="">
                                                                    <div class="input-group" ng-if="inOrOut.indexOf('in')>-1"   ng-click="selectBuyOrSaleOrderInfo('buy')" data-target="#buyOrderInfo" data-toggle="modal">
	                                                        <input type="text" class="form-control" placeholder=""  id="relationBuyOrSaleNum"      name ="relationBuyOrSaleNum"  ng-hide="invoiceAdd"   
												ng-model="invoice.relationBuyOrSaleNum" > 
	                                                        <span class="input-group-btn" style="vertical-align: top;" ng-hide="invoiceAdd"  >
	                                                            <button class="btn default" type="button">
	                                                                <i class="fa fa-search"></i>
	                                                            </button>
	                                                        </span>
                                                         </div>
                                                         <div class="input-group" ng-if="inOrOut.indexOf('out')>-1"   ng-click="selectBuyOrSaleOrderInfo('sale')" data-target="#saleOrderInfo" data-toggle="modal"  >
	                                                       <input type="text" class="form-control" placeholder=""  id="relationBuyOrSaleNum"      name ="relationBuyOrSaleNum"  ng-hide="invoiceAdd"    onkey
												ng-model="invoice.relationBuyOrSaleNum" > 
	                                                        <span class="input-group-btn" style="vertical-align: top;" ng-hide="invoiceAdd"  >
	                                                            <button class="btn default" type="button">
	                                                                <i class="fa fa-search"></i>
	                                                            </button>
	                                                        </span>
                                                         </div>
                                                                       <div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="invoiceView"  >{{invoice.relationBuyOrSaleNum}}</p> 
                                                                    </div>
                                                                </div>
                                                            </div>  
                                                        </div>
                                                        <div class="row">
                                                            /span
                                                            <div class="col-md-4">
                                                            <div class="form-group">
                                                    <label class="control-label bold" for="orderAmount">订单金额 :</label>
                                                    <div class=" ">
                                                    <span></span>
                                                                    <input type="text" class="form-control"   id="orderAmount" name ="orderAmount"  ng-hide="invoiceAdd"  readonly="readonly"
												ng-model="invoice.orderAmount" > 
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left"  >{{invoice.orderAmount |currency:'￥'}}</p> 
                                                                </div>
                                                            </div>
                                                            /span
                                                        </div>
                                                        <div class="col-md-4">
                                                            <div class="form-group">
                                                    <label class="control-label bold" for="billOrReceiptMoney">收票金额 :</label>
                                                    <div class=" ">
                                                                   
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left"  >{{invoice.billOrReceiptMoney |currency:'￥'}}</p> 
                                                                </div>
                                                            </div>
                                                            /span
                                                        </div>
                                                        
                                                        <div class="col-md-4">
                                                            <div class="form-group">
                                                    <label class="control-label bold" for="checkDate"> 大写金额 :</label>
                                                    <div class=" ">
                                                                        <input type="text"     class="form-control"   placeholder=""  id="unBillAmount" name ="unBillAmount"   ng-hide="invoiceAdd"   readonly
												ng-model="invoice.unBillAmount" /> 
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" >{{invoice.capitalMoney}}</p> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        /row
                                                       
                                                        <div class="row">
                                                        <div class="col-md-4">
                                                             <div class="form-group">
                                                    <label class="control-label bold" for="checker"> 币种 :</label>
                                                    <div class=" ">
                                                                        <input type="text"     class="form-control"   placeholder=""  id="unBillAmount" name ="unBillAmount"   ng-hide="invoiceAdd"   readonly
												ng-model="invoice.unBillAmount" /> 
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" >{{invoice.currency}}</p> 
                                                                    </div>
                                                    <div class=" ">
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
                                                            <div class="col-md-4">
                                                            <div class="form-group">
                                                    <label class="control-label bold" for="billWay"><span class="required"> * </span>开票方式 :</label>
                                                    <div class=" " >
                                                    <div  ng-hide="invoiceAdd" >
                                	<input type="radio"  ng-model="invoice.billWay" name="billWay" ng-checked="invoice.billWay=='1'" value="1"> 先票后款
                        			<input type="radio"  ng-model="invoice.billWay" name="billWay" ng-checked="invoice.billWay!='1'" value="0"> 先款后票
                        			</div>
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="invoiceView"   ng-if="invoice.billWay!='1'">先款后票</p> 
                                                                        <p class="control-label left" ng-show="invoiceView"   ng-if="invoice.billWay=='1'">先票后款</p> 
                                                                </div>
                                                            </div>
                                                        </div>
                                                            <div class="col-md-4">
                                                             <div class="form-group">
                                                    <label class="control-label bold" for="checker"> <span class="required"> * </span> <span  ng-if="inOrOut.indexOf('in')>-1"  >是否付款</span><span   ng-if="inOrOut.indexOf('out')>-1" > 是否收款</span> :</label>
                                                    <div class=" ">
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
                                                        </div>
                                                        
                                                         <div class="row">
                                                            <div class="col-md-4">
                                                             <div class="form-group">
                                                    <label class="control-label bold" for="status">  申请日期: </label>
                                                 <div class=" ">
                                                                        <input type="text"  class="form-control form-control-inline date-picker"     data-date-format="yyyy-mm-dd"  data-date-viewmode="years"   placeholder=""  id="submitDate" name ="submitDate"   ng-hide="invoiceAdd"  
												ng-model="invoice.submitDate" /> 
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="invoiceView">{{invoice.submitDate | date:'yyyy-MM-dd'}}</p> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="col-md-4">
                                                             <div class="form-group">
                                                    <label class="control-label bold" for="checker"> 申请部门:</label>
                                                    <div class=" ">
                                                       <input type="text"  class="form-control" placeholder=""  id="submitDepartment" name ="submitDepartment"   ng-hide="invoiceAdd"  
												ng-model="invoice.submitDepartment" > 
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="invoiceView">{{invoice.submitDepartment}}</p> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                              <div class="col-md-4">
                                                             <div class="form-group">
                                                    <label class="control-label bold" for="checker"> 申请人:</label>
                                                    <div class=" ">
                                                       <input type="text"  class="form-control" placeholder=""  id="submitter" name ="submitter"   ng-hide="invoiceAdd"  
												ng-model="invoice.submitter" > 
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="invoiceView">{{invoice.submitter}}</p> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            /span
                                                            
                                                            /span
                                                        </div>
                                                            <div class="row">
                                                            <div class="col-md-4">
                                                             <div class="form-group">
                                                    <label class="control-label bold" for="checker"> 备注:</label>
                                                    <div class=" ">
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
                                                             <div class="col-md-4"  ng-show="inOrOut.indexOf('out')>-1">
                                                             <div class="form-group">
                                                    <label class="control-label bold" for="checker"> 申请开票日期:</label>
                                                    <div class=" ">
												   <input type="text"  class="form-control form-control-inline date-picker"     data-date-format="yyyy-mm-dd"  data-date-viewmode="years"   placeholder=""  id="receiptDate" name ="receiptDate"   ng-hide="invoiceAdd"  
												ng-model="invoice.receiptDate" /> 
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="invoiceView">{{invoice.receiptDate | date:'yyyy-MM-dd'}}</p>
                                                                    </div>
                                                                </div>
                                                            </div>
                                              
                                                        </div>
                                                     <div class="row"  ng-if="inOrOut.indexOf('in')>-1" >
                                                             <div class="col-md-4"  >
                                                                <div class="form-group">
                                                    <label class="control-label bold" for=""><span class="required"> * </span> 开票方:</label>
                                                    <div class="">
                                                     <input type="text" class="form-control"   id="comName" name ="comName"       ng-hide="invoiceAdd"   
												ng-model="invoice.comName" /> 
												 
												<div class="form-control-focus"> </div>
                                                                         <p class="control-label left"    ng-show="invoiceView"   >{{invoice.comName}}</p> 
                                                                    </div>
                                                                      
                                                                </div>
                                                            </div> 
                                                             <div class="col-md-4">
                                                                <div class="form-group">
                                                    <label class="control-label bold" for=""><span class="required"> * </span> 联系电话 :</label>
                                                    <div class="">
                                                     <input type="text" class="form-control"   id="tel" name ="tel"       ng-hide="invoiceAdd"   
												ng-model="invoice.tel" /> 
												 
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left"    ng-show="invoiceView"    >{{invoice.tel}}</p> 
                                                                    </div>
                                                                      
                                                                </div>
                                                            </div> 
                                                             <div class="col-md-4">
                                                                <div class="form-group">
                                                    <label class="control-label bold" for=""> 备注 :</label>
                                                    <div class="">
                                                     <input type="text" class="form-control"   id="secondRemark" name ="secondRemark"       ng-hide="invoiceAdd"   
												ng-model="invoice.secondRemark" /> 
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left"    ng-show="invoiceView"     >{{invoice.secondRemark}}</p> 
                                                                    </div>
                                                                      
                                                                </div>
                                                            </div> 
                                                 
                                                        </div>
                                                        <div class="row"  ng-if="inOrOut.indexOf('out')>-1" >
                                                             <div class="col-md-4"  >
                                                                <div class="form-group">
                                                    <label class="control-label bold" for=""><span class="required"> * </span> 收票方:</label>
                                                    <div class="">
                                                     <input type="text" class="form-control"   id="comName" name ="comName"       ng-hide="invoiceAdd"   
												ng-model="invoice.comName" /> 
												 
												<div class="form-control-focus"> </div>
												 
                                                                        <p class="control-label left"    ng-show="invoiceView"   ng-if="inOrOut.indexOf('out')>-1"   >{{invoice.comName}}</p> 
                                                                         <p class="control-label left"    ng-show="invoiceView"   ng-if="inOrOut.indexOf('in')>-1"   >{{invoice.comName}}</p> 
                                                                    </div>
                                                                      
                                                                </div>
                                                            </div> 
                                                             <div class="col-md-4">
                                                                <div class="form-group">
                                                    <label class="control-label bold" for=""><span class="required"> * </span> 企业全称 :</label>
                                                    <div class="">
                                                     <input type="text" class="form-control"   id="companyName" name ="companyName"       ng-hide="invoiceAdd"   
												ng-model="invoice.companyName" /> 
												 
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left"    ng-show="invoiceView"   ng-if="inOrOut.indexOf('out')>-1"   >{{invoice.companyName}}</p> 
                                                                    </div>
                                                                      
                                                                </div>
                                                            </div> 
                                                             <div class="col-md-4">
                                                                <div class="form-group">
                                                    <label class="control-label bold" for=""><span class="required"> * </span> 地址 :</label>
                                                    <div class="">
                                                     <input type="text" class="form-control"   id="address" name ="address"       ng-hide="invoiceAdd"   
												ng-model="invoice.address" /> 
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left"    ng-show="invoiceView"     >{{invoice.address}}</p> 
                                                                    </div>
                                                                      
                                                                </div>
                                                            </div> 
                                                 
                                                        </div>
                                                        <div class="row"  ng-if="inOrOut.indexOf('out')>-1" >
                                                             <div class="col-md-4"  >
                                                                <div class="form-group">
                                                    <label class="control-label bold" for=""><span class="required"> * </span>联系电话:</label>
                                                    <div class="">
                                                     <input type="text" class="form-control"   id="tel" name ="tel"       ng-hide="invoiceAdd"   
												ng-model="invoice.tel" /> 
												 
												<div class="form-control-focus"> </div>
												 
                                                                        <p class="control-label left"    ng-show="invoiceView"    >{{invoice.tel}}</p> 
                                                                         
                                                                    </div>
                                                                      
                                                                </div>
                                                            </div> 
                                                             <div class="col-md-4">
                                                                <div class="form-group">
                                                    <label class="control-label bold" for=""><span class="required"> * </span> 开户银行 :</label>
                                                    <div class="">
                                                     <input type="text" class="form-control"   id="bankName" name ="bankName"       ng-hide="invoiceAdd"   
												ng-model="invoice.bankName" /> 
												 
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left"    ng-show="invoiceView"     >{{invoice.bankName}}</p> 
                                                                    </div>
                                                                      
                                                                </div>
                                                            </div> 
                                                             <div class="col-md-4">
                                                                <div class="form-group">
                                                    <label class="control-label bold" for=""><span class="required"> * </span> 账号:</label>
                                                    <div class="">
                                                     <input type="text" class="form-control"   id="account" name ="account"       ng-hide="invoiceAdd"   
												ng-model="invoice.account" /> 
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left"    ng-show="invoiceView"     >{{invoice.account}}</p> 
                                                                    </div>
                                                                      
                                                                </div>
                                                            </div> 
                                                 
                                                        </div>
                                                        <div class="row"  ng-if="inOrOut.indexOf('out')>-1" >
                                                             <div class="col-md-4"  >
                                                                <div class="form-group">
                                                    <label class="control-label bold" for=""><span class="required"> * </span> 企业纳税号:</label>
                                                    <div class="">
                                                     <input type="text" class="form-control"   id="taxNum" name ="taxNum"       ng-hide="invoiceAdd"   
												ng-model="invoice.taxNum" /> 
												 
												<div class="form-control-focus"> </div>
												 
                                                                         <p class="control-label left"    ng-show="invoiceView"     >{{invoice.taxNum}}</p> 
                                                                    </div>
                                                                      
                                                                </div>
                                                            </div> 
                                                             <div class="col-md-4">
                                                                <div class="form-group">
                                                    <label class="control-label bold" for=""> 备注 :</label>
                                                    <div class="">
                                                     <input type="text" class="form-control"   id="secondRemark" name ="secondRemark"       ng-hide="invoiceAdd"   
												ng-model="invoice.secondRemark" /> 
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left"    ng-show="invoiceView"     >{{invoice.secondRemark}}</p> 
                                                                    </div>
                                                                      
                                                                </div>
                                                            </div> 
                                                 
                                                        </div>
                                                        <div class="row"  >
                                                             <div class="col-md-4"  >
                                                                <div class="form-group">
                                                    <label class="control-label bold" for=""> <span  ng-if="inOrOut.indexOf('in')>-1"  >应付金额</span><span   ng-if="inOrOut.indexOf('out')>-1" > 应收金额</span>:</label>
                                                    <div class="">
                                                
												 
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left"      >{{invoice.orderAmount|currency:'￥'}}</p> 
                                                                    </div>
                                                                      
                                                                </div>
                                                            </div> 
                                                             <div class="col-md-4">
                                                                <div class="form-group">
                                                    <label class="control-label bold" for=""> <span  ng-if="inOrOut.indexOf('in')>-1"  >预付金额</span><span   ng-if="inOrOut.indexOf('out')>-1" > 预收金额</span> :</label>
                                                    <div class="">
                                                 
												 
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left"      >{{0|currency:'￥'}}</p> 
                                                                    </div>
                                                                      
                                                                </div>
                                                            </div> 
                                                             <div class="col-md-4">
                                                                <div class="form-group">
                                                    <label class="control-label bold" for=""> <span  ng-if="inOrOut.indexOf('in')>-1"  >已付金额</span><span   ng-if="inOrOut.indexOf('out')>-1" >已收金额</span> :</label>
                                                    <div class="">
                                                  
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left"       >{{invoice.orderAmount-invoice.unPayOrReceiptMoney|currency:'￥'}}</p> 
                                                                    </div>
                                                                      
                                                                </div>
                                                            </div> 
                                                 
                                                        </div>
                                                        <div class="row"  >
                                                             <div class="col-md-4"  >
                                                                <div class="form-group">
                                                    <label class="control-label bold" for=""> <span  ng-if="inOrOut.indexOf('in')>-1"  >未付金额</span><span   ng-if="inOrOut.indexOf('out')>-1" > 未收金额</span>:</label>
                                                    <div class="">
                                                    
												 
												<div class="form-control-focus"> </div>
												 
                                                                        <p class="control-label left"    >{{invoice.unPayOrReceiptMoney|currency:'￥'}}</p> 
                                                                         
                                                                    </div>
                                                                      
                                                                </div>
                                                            </div> 
                                                             <div class="col-md-4">
                                                                <div class="form-group">
                                                    <label class="control-label bold" for=""> <span  ng-if="inOrOut.indexOf('in')>-1"  >已收票金额</span><span   ng-if="inOrOut.indexOf('out')>-1" > 已开票金额</span> :</label>
                                                    <div class="">
                                                  
												 
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left"      >{{invoice.orderAmount-invoice.unBillOrReceiptMoney|currency:'￥'}}</p> 
                                                                    </div>
                                                                      
                                                                </div>
                                                            </div> 
                                                             <div class="col-md-4">
                                                                <div class="form-group">
                                                    <label class="control-label bold" for=""> <span  ng-if="inOrOut.indexOf('in')>-1"  >未收票金额</span><span   ng-if="inOrOut.indexOf('out')>-1" >未开票金额</span> :</label>
                                                    <div class="">
                                                   
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left"   >{{invoice.unBillOrReceiptMoney|currency:'￥'}}</p> 
                                                                    </div>
                                                                      
                                                                </div>
                                                            </div> 
                                                 
                                                        </div>
                                                          <div class="row">row START
                                                             <div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="billingDate"><span class="required"> * </span>开票日期  :</label>
                                                       <div class=" ">
                                                         <input type="text"  class="form-control form-control-inline date-picker"     data-date-format="yyyy-mm-dd"  data-date-viewmode="years"   placeholder=""  id="billingDate" name ="billingDate"   ng-hide="invoiceAdd"  
												ng-model="invoice.billingDate" /> 
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="invoiceView">{{invoice.billingDate | date:'yyyy-MM-dd'}}</p>
                                                                    </div>
                                            </div>
										</div>
										 <div class="col-md-4">
                                                                <div class="form-group">
                                                                <label class="control-label bold" for="invoiceAmount"><span class="required"> * </span>发票金额 : </label>
                                                                       <div class=" ">
                                                       <input type="text"  class="form-control "     placeholder=""    id="invoiceAmount" name ="invoiceAmount"  ng-hide="invoiceAdd"   
												ng-model="invoice.invoiceAmount" > 
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="invoiceView">{{invoice.invoiceAmount|currency:'￥'}}</p> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="col-md-4" >
                                                                <div class="form-group">
                                                                <label class="control-label bold" for="invoiceNO"><span class="required"> * </span>发票号码 : </label>
                                                                       <div class=" ">
                                                       <input type="text"  class="form-control "       placeholder=""   id="invoiceNO" name ="invoiceNO"  ng-hide="invoiceAdd"   
												ng-model="invoice.invoiceNO" > 
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="invoiceView">{{invoice.invoiceNO}}</p> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>row END
                                                                   <div class="row">row START
                                                            <div class="col-md-4">
                                                                <div class="form-group">
                                                                <label class="control-label bold" for="invoiceVoucher">发票凭证 :</label>
                                                                <div class="">
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
                                                            <div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="approver"><span class="required"> * </span><span  ng-if="inOrOut.indexOf('in')>-1"  >收票人 </span><span   ng-if="inOrOut.indexOf('out')>-1" >开票人</span> :</label>
                                                       <div class=" ">
                                                       <input type="text"  class="form-control" placeholder=""  id="approver" name ="approver"   ng-hide="invoiceAdd"  
												ng-model="invoice.approver" > 
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="invoiceView">{{invoice.approver}}</p> 
                                                                    </div>
                                            </div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="thirdRemark">备注 :</label>
                                                       <div class=" ">
                                                       <input type="text"  class="form-control" placeholder=""  id="thirdRemark" name ="thirdRemark"   ng-hide="invoiceAdd"  
												ng-model="invoice.thirdRemark" > 
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="invoiceView">{{invoice.thirdRemark}}</p> 
                                                                    </div>
                                            </div>
										</div>
										</div> -->
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
													<label class="control-label bold" for="contactNum">
														<span class="required"> * </span>发票类型 :
													</label>
													<div class=" ">
														<select class="form-control" id="invoiceType"
															ng-hide="invoiceAdd" name="invoiceType"
															ng-model="invoice.invoiceType"
															ng-if="inOrOut.indexOf('confirm')<0">
															<option value=""></option>
															<option value="服务费发票">服务费发票</option>
															<option value="采购发票">采购发票</option>

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
											<div class="col-md-4">
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
											</div>
										</div>

										<div class="row">
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
											<!--/span-->

											<!--/span-->
										</div>
										<div class="row">
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
														<%--  <input type="text" class="form-control"   id="bankName" name ="bankName"       ng-hide="invoiceAdd"     
												ng-model="invoice.bankName" />  ng-if="inOrOut.indexOf('confirm')<0" --%>
														<div  ng-hide="invoiceView">
															<select class="form-control" data-live-search="true"
																data-size="" id="bankName" name="bankName"
																ng-model="invoice.bankName">
																<!-- <option  ng-repeat="op in buyCom " value="{{op.comId}}"  > {{op.comName}}</option> -->
																<option value=""></option>
																<option ng-repeat="companyFinance in companyFinances"
																	value="{{companyFinance.openingBank}}">{{companyFinance.openingBank}}</option>
															</select>
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
														<%--  <input type="text" class="form-control"   id="account" name ="account"       ng-hide="invoiceAdd"    ng-if="inOrOut.indexOf('confirm')<0" 
												ng-model="invoice.account" />  --%>
														<div   ng-hide="invoiceView">
															<!--  ng-hide="invoiceAdd"-->
															<select class="form-control" data-live-search="true"
																data-size="" id="account" name="account"
																ng-model="invoice.account">
																<option value=""></option>
																<option ng-repeat="companyFinance in companyFinances"
																	value="{{companyFinance.accountNumber}}">{{companyFinance.accountNumber}}</option>
															</select>
														</div>
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
										<div class="row"     ng-if="inOrOut.indexOf('confirm')>-1">
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
															ng-model="invoice.invoiceAmount">
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
							</form>
             
            </div>   
	</div>
	

         </div>   