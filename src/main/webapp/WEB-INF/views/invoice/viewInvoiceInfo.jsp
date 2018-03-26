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
<!-- <h3 class="page-title">发票
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
        <!-- BEGIN EXAMPLE TABLE PORTLET-->
        <div class="portlet bordered">
            <div class="portlet-body">
            	<ul class="nav nav-tabs">
		<li class="active bold">
               		<a data-target="#tab_1_1" data-toggle="tab"><span ng-if="inOrOut.indexOf('in')>-1" >进项票</span><span  ng-if="inOrOut.indexOf('out')>-1">销项票</span>详情</a>
           		</li>
		<li class="bold" ng-hide="tab_1_2Hide"><a data-target="#tab_1_2" data-toggle="tab">物料信息</a></li>
		
		<li class="dropdown pull-right tabdrop">
			<button type="button" onclick="goBackPage()" class="btn defualt  btn-circle  btn-sm"><i class="fa fa-reply"></i>返回</button>
		</li>		
	</ul>
				<div class="portlet light ">
                       <!--  <div class="portlet-title">
                            <div class="caption"></div>
                            <div class="actions">
                                     <span ng-if="inOrOut.indexOf('in')>-1" >进项票</span><span  ng-if="inOrOut.indexOf('out')>-1">销项票</span>详情       
                            </div>
                        </div> -->
            
                       
                         <div class="tab-content">
				<div class="tab-pane fade active in" id="tab_1_1"> 
                        <div class="portlet-body form">
								<div class="form-body">
									<div class="alert alert-danger display-hide">
                                               <button class="close" data-close="alert"></button>请先输入正确数据！</div>
								           <div class="row">
                                                             <div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="invoiceNum"> <span class="required"> * </span>	<span  ng-if="inOrOut.indexOf('in')>-1"  > 进项</span><span   ng-if="inOrOut.indexOf('out')>-1" > 销项</span>发票编号 :</label>
                                                    <div class=" ">
                                                          <p class="control-label left"   >{{invoice.invoiceNum}}</p> 
                                                         <!--  存放物料流水号-->
                                                    </div>
                                            </div>
										</div>
										
										<div class="col-md-4">
                                                            <div class="form-group">
                                                    <label class="control-label bold" for="contactNum"> <span class="required"> * </span>发票类型 :</label>
                                                    <div class=" ">
                                                                        <p class="control-label left" >{{invoice.invoiceType}}</p> 
                                                                    </div>
                                                                </div>
                                                       </div>
                                                          
                                                            <div class="col-md-4">
                                                                <div class="form-group">
                                                                <label class="control-label bold" for="relationBuyOrSaleNum"><span class="required"> * </span>  <span  ng-if="inOrOut.indexOf('in')>-1"  > 关联采购单号</span><span   ng-if="inOrOut.indexOf('out')>-1" > 关联销售订单号</span> :</label>
                                                                    <div class="">
                                                   
                                                                        <p class="control-label left" >{{invoice.relationBuyOrSaleNum}}</p> 
                                                                    </div>
                                                                </div>
                                                            </div>  
                                                        </div>
                                                        <div class="row">
                                                            <!--/span-->
                                                            <div class="col-md-4">
                                                            <div class="form-group">
                                                    <label class="control-label bold" for="orderAmount">订单金额 :</label>
                                                    <div class=" ">
                                                    <span></span>
                                                                    <!-- <input type="text" class="form-control"   id="orderAmount" name ="orderAmount"  ng-hide="invoiceAdd"  readonly="readonly"
												ng-model="invoice.orderAmount" >  -->
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left"  >{{invoice.orderAmount |currency:'￥'}}</p> 
                                                                </div>
                                                            </div>
                                                            <!--/span-->
                                                        </div>
                                                        <div class="col-md-4">
                                                            <div class="form-group">
                                                    <label class="control-label bold" for="billOrReceiptMoney">收票金额 :</label>
                                                    <div class=" ">
                                                                   
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left"  >{{invoice.billOrReceiptMoney |currency:'￥'}}</p> 
                                                                </div>
                                                            </div>
                                                            <!--/span-->
                                                        </div>
                                                     <!--     -->
                                                        <div class="col-md-4">
                                                            <div class="form-group">
                                                    <label class="control-label bold" for="checkDate"> 大写金额 :</label>
                                                    <div class=" ">
                                                                        <!-- <input type="text"     class="form-control"   placeholder=""  id="unBillAmount" name ="unBillAmount"   ng-hide="invoiceAdd"   readonly
												ng-model="invoice.unBillAmount" /> --> 
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" >{{invoice.capitalMoney}}</p> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <!--/row-->
                                                       
                                                        <div class="row">
                                                        <div class="col-md-4">
                                                             <div class="form-group">
                                                    <label class="control-label bold" for="checker"> 币种 :</label>
                                                    <div class=" ">
                                                                        <!-- <input type="text"     class="form-control"   placeholder=""  id="unBillAmount" name ="unBillAmount"   ng-hide="invoiceAdd"   readonly
												ng-model="invoice.unBillAmount" />  -->
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" >{{invoice.currency}}</p> 
                                                                    </div>
                                                
                                                                </div>
                                                            </div>
                                                         <!--    <div class="col-md-4">
                                                            <div class="form-group">
                                                    <label class="control-label bold" for="billWay"><span class="required"> * </span>开票方式 :</label>
                                                    <div class=" " >
                                                                        <p class="control-label left"    ng-if="invoice.billWay!='1'">先款后票</p> 
                                                                        <p class="control-label left"   ng-if="invoice.billWay=='1'">先票后款</p> 
                                                                </div>
                                                            </div>
                                                        </div>
                                                            <div class="col-md-4">
                                                             <div class="form-group">
                                                    <label class="control-label bold" for="checker"> <span class="required"> * </span> <span  ng-if="inOrOut.indexOf('in')>-1"  >是否付款</span><span   ng-if="inOrOut.indexOf('out')>-1" > 是否收款</span> :</label>
                                                    <div class=" ">
                                                                        <p class="control-label left"  ng-if="invoice.paymentStatus!='是'" >否</p> 
                                                                          <p class="control-label left"   ng-if="invoice.paymentStatus=='是'" >是</p> 
                                                                    </div>
                                                                </div>
                                                            </div> -->
                                                                    <div class="col-md-4">
                                                             <div class="form-group">
                                                    <label class="control-label bold" for="status">  申请日期: </label>
                                                 <div class=" ">
                                                                        <p class="control-label left" >{{invoice.submitDate | date:'yyyy-MM-dd'}}</p> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="col-md-4">
                                                             <div class="form-group">
                                                    <label class="control-label bold" for="checker"> 申请部门:</label>
                                                    <div class=" ">
                                                      
                                                                        <p class="control-label left" >{{invoice.submitDepartment}}</p> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        
                                                         <div class="row">
                                                    
                                                              <div class="col-md-4">
                                                             <div class="form-group">
                                                    <label class="control-label bold" for="checker"> 申请人:</label>
                                                    <div class=" ">
                                                      
                                                                        <p class="control-label left" >{{invoice.submitter}}</p> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                               <div class="col-md-4">
                                                             <div class="form-group">
                                                    <label class="control-label bold" for="checker"> 备注:</label>
                                                    <div class=" ">
                                                      
                                                                        <p class="control-label left"    ng-if="inOrOut.indexOf('in')>-1"  >{{invoice.billingRemark}}</p> 
                                                                         <p class="control-label left"  ng-if="inOrOut.indexOf('out')>-1"  >{{invoice.receiptRemark}}</p> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                             <div class="col-md-4"  ng-show="inOrOut.indexOf('out')>-1">
                                                             <div class="form-group">
                                                    <label class="control-label bold" for="checker"> 申请开票日期:</label>
                                                    <div class=" ">
												  
                                                                        <p class="control-label left" >{{invoice.receiptDate | date:'yyyy-MM-dd'}}</p>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                         <!--    <div class="row">
                                                            <div class="col-md-4">
                                                             <div class="form-group">
                                                    <label class="control-label bold" for="checker"> 备注:</label>
                                                    <div class=" ">
                                                      
                                                                        <p class="control-label left"    ng-if="inOrOut.indexOf('in')>-1"  >{{invoice.billingRemark}}</p> 
                                                                         <p class="control-label left"  ng-if="inOrOut.indexOf('out')>-1"  >{{invoice.receiptRemark}}</p> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                             <div class="col-md-4"  ng-show="inOrOut.indexOf('out')>-1">
                                                             <div class="form-group">
                                                    <label class="control-label bold" for="checker"> 申请开票日期:</label>
                                                    <div class=" ">
												  
                                                                        <p class="control-label left" >{{invoice.receiptDate | date:'yyyy-MM-dd'}}</p>
                                                                    </div>
                                                                </div>
                                                            </div>
                                              
                                                        </div> -->
                                                     <div class="row"  ng-if="inOrOut.indexOf('in')>-1" >
                                                             <div class="col-md-4"  >
                                                                <div class="form-group">
                                                    <label class="control-label bold" for=""><span class="required"> * </span> 开票方:</label>
                                                    <div class="">
                                                   
                                                                         <p class="control-label left"     >{{invoice.comName}}</p> 
                                                                    </div>
                                                                      
                                                                </div>
                                                            </div> 
                                                             <div class="col-md-4">
                                                                <div class="form-group">
                                                    <label class="control-label bold" for=""><span class="required"> * </span> 联系电话 :</label>
                                                    <div class="">
                                                     
                                                                        <p class="control-label left"   >{{invoice.tel}}</p> 
                                                                    </div>
                                                                      
                                                                </div>
                                                            </div> 
                                                             <div class="col-md-4">
                                                                <div class="form-group">
                                                    <label class="control-label bold" for=""> 备注 :</label>
                                                    <div class="">
                                                     
                                                                        <p class="control-label left"      >{{invoice.secondRemark}}</p> 
                                                                    </div>
                                                                      
                                                                </div>
                                                            </div> 
                                                 
                                                        </div>
                                                        <div class="row"  ng-if="inOrOut.indexOf('out')>-1" >
                                                             <div class="col-md-4"  >
                                                                <div class="form-group">
                                                    <label class="control-label bold" for=""><span class="required"> * </span> 收票方:</label>
                                                    <div class="">
                                                   
												 
                                                                        <p class="control-label left"      ng-if="inOrOut.indexOf('out')>-1"   >{{invoice.comName}}</p> 
                                                                         <p class="control-label left"     ng-if="inOrOut.indexOf('in')>-1"   >{{invoice.comName}}</p> 
                                                                    </div>
                                                                      
                                                                </div>
                                                            </div> 
                                                             <div class="col-md-4">
                                                                <div class="form-group">
                                                    <label class="control-label bold" for=""><span class="required"> * </span> 企业全称 :</label>
                                                    <div class="">
                                                     
                                                                        <p class="control-label left"   ng-if="inOrOut.indexOf('out')>-1"   >{{invoice.companyName}}</p> 
                                                                    </div>
                                                                      
                                                                </div>
                                                            </div> 
                                                             <div class="col-md-4">
                                                                <div class="form-group">
                                                    <label class="control-label bold" for=""><span class="required"> * </span> 地址 :</label>
                                                    <div class="">
                                                    
                                                                        <p class="control-label left"       >{{invoice.address}}</p> 
                                                                    </div>
                                                                      
                                                                </div>
                                                            </div> 
                                                 
                                                        </div>
                                                        <div class="row"  ng-if="inOrOut.indexOf('out')>-1" >
                                                             <div class="col-md-4"  >
                                                                <div class="form-group">
                                                    <label class="control-label bold" for=""><span class="required"> * </span>联系电话:</label>
                                                    <div class="">
												 
												<div class="form-control-focus"> </div>
												 
                                                                        <p class="control-label left"       >{{invoice.tel}}</p> 
                                                                         
                                                                    </div>
                                                                      
                                                                </div>
                                                            </div> 
                                                             <div class="col-md-4">
                                                                <div class="form-group">
                                                    <label class="control-label bold" for=""><span class="required"> * </span> 开户银行 :</label>
                                                    <div class="">
                                                  
                                                                        <p class="control-label left"      >{{invoice.bankName}}</p> 
                                                                    </div>
                                                                      
                                                                </div>
                                                            </div> 
                                                             <div class="col-md-4">
                                                                <div class="form-group">
                                                    <label class="control-label bold" for=""><span class="required"> * </span> 账号:</label>
                                                    <div class="">
                                                    
                                                                        <p class="control-label left"      >{{invoice.account}}</p> 
                                                                    </div>
                                                                      
                                                                </div>
                                                            </div> 
                                                 
                                                        </div>
                                                        <div class="row"  ng-if="inOrOut.indexOf('out')>-1" >
                                                             <div class="col-md-4"  >
                                                                <div class="form-group">
                                                    <label class="control-label bold" for=""><span class="required"> * </span> 企业纳税号:</label>
                                                    <div class="">
                                                   
												 
                                                                         <p class="control-label left"       >{{invoice.taxNum}}</p> 
                                                                    </div>
                                                                      
                                                                </div>
                                                            </div> 
                                                             <div class="col-md-4">
                                                                <div class="form-group">
                                                    <label class="control-label bold" for=""> 备注 :</label>
                                                    <div class="">
                                                     
                                                                        <p class="control-label left"      >{{invoice.secondRemark}}</p> 
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
                                                                        <p class="control-label left"      ><!-- {{invoice.shouldPayOrReceiptMoney|currency:'￥' }}-->{{invoice.orderAmount |currency:'￥'}}</p> 
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
                                                          <div class="row"><!-- row START -->
                                                             <div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="billingDate"><span class="required"> * </span>开票日期  :</label>
                                                       <div class=" ">
                                                       
                                                                        <p class="control-label left" >{{invoice.billingDate | date:'yyyy-MM-dd'}}</p>
                                                                    </div>
                                            </div>
										</div>
										 <div class="col-md-4">
                                                                <div class="form-group">
                                                                <label class="control-label bold" for="invoiceAmount"><span class="required"> * </span>发票金额 : </label>
                                                                       <div class=" ">
                                                      
                                                                        <p class="control-label left" >{{invoice.invoiceAmount|currency:'￥'}}</p> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="col-md-4" >
                                                                <div class="form-group">
                                                                <label class="control-label bold" for="invoiceNO"><span class="required"> * </span>发票号码 : </label>
                                                                       <div class=" ">
                                                       
                                                                        <p class="control-label left" >{{invoice.invoiceNO}}</p> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div><!-- row END-->
                                                                   <div class="row"><!-- row START -->
                                                            <div class="col-md-4">
                                                                <div class="form-group">
                                                                <label class="control-label bold" for="invoiceVoucher">发票凭证 :</label>
                                                                <div class="">
                                                                      <div class="col-md-9">
                                                    	<label    ng-if="invoice.invoiceVoucher==null||invoice.invoiceVoucher==''" class="c_edit" >未上传发票凭证</label>
                                                    	<label    ng-if="invoice.invoiceVoucher!=null&&invoice.invoiceVoucher!=''" class="c_edit" ><a href="javascript:;" title="下载附件" ng-click="downloadFile(invoice)">{{invoice.invoiceVoucher.substring(invoice.invoiceVoucher.indexOf("_")+1)}}</a></label>
												</div>
												</div>
                                                                </div>
                                                            </div>
                                                            <div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="approver"><span class="required"> * </span><span  ng-if="inOrOut.indexOf('in')>-1"  >收票人 </span><span   ng-if="inOrOut.indexOf('out')>-1" >开票人</span> :</label>
                                                       <div class=" ">
                                                       
                                                                        <p class="control-label left" >{{invoice.approver}}</p> 
                                                                    </div>
                                            </div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="thirdRemark">备注 :</label>
                                                       <div class=" ">
                                                      
                                                                        <p class="control-label left" >{{invoice.thirdRemark}}</p> 
                                                                    </div>
                                            </div>
										</div>
										 </form>
                                                        </div><!-- row END-->
                                                        
                                             <div>          
                                                        </div> 
								</div>
							
         				</div></div>
         					<div class="tab-pane" id="tab_1_2"  >
	<!-- 物料信息start-->
      <div class="portlet-body"  >
				<table  ng-if="inOrOut.indexOf('in')>-1"
					class="table table-striped table-bordered table-hover "
					id="sample_inm">
					<thead>
						<tr>
						
							<td style="text-align: center">物料编号</td>
                              <td style="text-align: center">物料名称</td>
                               <td  style="text-align: center">规格型号</td>
                                <td  style="text-align: center">单位</td>
                                <td  style="text-align: center">订单数量</td>
                                <td  style="text-align: center">未收数量</td>
                                 <td   style="text-align: center">收票数量</td>
                                <td  style="text-align: center">含税单价</td>
                                  <td   style="text-align: center">金额</td>
                                  <td   style="text-align: center">操作</td>
						</tr>
					</thead>
					
					<tbody>
					</tbody>
					<tfoot>
					<tr>
							<td colspan="4">合计</td>
                              <td colspan="6">小写: {{invoice.billOrReceiptMoney |currency:'￥'}} 大写:{{invoice.capitalMoney}}</td>
						</tr>
					</tfoot>
				</table>
				<table   ng-if="inOrOut.indexOf('out')>-1"
					class="table table-striped table-bordered table-hover "
					id="sample_outm">
					<thead>
						<tr>
							<td style="text-align: center;" >物料编号</td>
                              <td style="text-align: center">物料名称</td>
                              <td  style="text-align: center">规格型号</td>
                              <td  style="text-align: center">单位</td>
                              <td  style="text-align: center">订单数量</td>
                              <td  style="text-align: center">可开数量</td>
                              <td   style="text-align: center">开票数量</td>
                              <td  style="text-align: center">含税单价</td>
                              <td   style="text-align: center">金额</td>
                              <td    style="text-align: center">操作</td>
						</tr>
					</thead>
					
					<tbody>
					</tbody>
					<tfoot>
					<tr>
							<td colspan="4">合计</td>
                              <td colspan="6">小写: {{invoice.billOrReceiptMoney |currency:'￥'}} 大写:{{invoice.capitalMoney}}</td>
						</tr>
					</tfoot>
				</table>
			</div>
         <!-- 物料信息 end-->
	</div>
         				</div> 
         				
         				 	<!--  <div class="portlet-title">物料信息START
                            <div class="caption">物料信息</div>
                            <div class="actions">
                            </div>
                        </div> -->
                      
                                
			<!-- <div class="portlet-body"  ng-if="inOrOut.indexOf('in')>-1">进项票物料信息  
				<table
					class="table table-striped table-bordered table-hover "
					id="sample_inm">
					<thead>
						<tr>
						
							<td style="text-align: center">物料编号</td>
                              <td style="text-align: center">物料名称</td>
                               <td  style="text-align: center">规格型号</td>
                                <td  style="text-align: center">单位</td>
                                <td  style="text-align: center">订单数量</td>
                                <td  style="text-align: center">未收数量</td>
                                 <td   style="text-align: center">收票数量</td>
                                <td  style="text-align: center">含税单价</td>
                                  <td   style="text-align: center">金额</td>
                                  <td   style="text-align: center">操作</td>
						</tr>
					</thead>
					
					<tbody>
					</tbody>
					<tfoot>
					<tr>
							<td colspan="4">合计</td>
                              <td colspan="6">小写: {{invoice.billOrReceiptMoney |currency:'￥'}} 大写:{{invoice.capitalMoney}}</td>
						</tr>
					</tfoot>
				</table>
					<table   ng-if="inOrOut.indexOf('out')>-1"
					class="table table-striped table-bordered table-hover "
					id="sample_outm">
					<thead>
						<tr>
							<td style="text-align: center;" >物料编号</td>
                              <td style="text-align: center">物料名称</td>
                              <td  style="text-align: center">规格型号</td>
                              <td  style="text-align: center">单位</td>
                              <td  style="text-align: center">订单数量</td>
                              <td  style="text-align: center">可开数量</td>
                              <td   style="text-align: center">开票数量</td>
                              <td  style="text-align: center">含税单价</td>
                              <td   style="text-align: center">金额</td>
                              <td    style="text-align: center">操作</td>
						</tr>
					</thead>
					
					<tbody>
					</tbody>
					<tfoot>
					<tr>
							<td colspan="4">合计</td>
                              <td colspan="6">小写: {{invoice.billOrReceiptMoney |currency:'￥'}} 大写:{{invoice.capitalMoney}}</td>
						</tr>
					</tfoot>
				</table>
			</div> -->
	<!-- 		<div class="portlet-body"  ng-if="inOrOut.indexOf('out')>-1">销项票物料信息-- 
				<table
					class="table table-striped table-bordered table-hover "
					id="sample_outm">
					<thead>
						<tr>
							<td style="text-align: center;" >物料编号</td>
                              <td style="text-align: center">物料名称</td>
                              <td  style="text-align: center">规格型号</td>
                              <td  style="text-align: center">单位</td>
                              <td  style="text-align: center">订单数量</td>
                              <td  style="text-align: center">可开数量</td>
                              <td   style="text-align: center">开票数量</td>
                              <td  style="text-align: center">含税单价</td>
                              <td   style="text-align: center">金额</td>
                              <td    style="text-align: center">操作</td>
						</tr>
					</thead>
					
					<tbody>
					</tbody>
					<tfoot>
					<tr>
							<td colspan="4">合计</td>
                              <td colspan="6">小写: {{invoice.billOrReceiptMoney |currency:'￥'}} 大写:{{invoice.capitalMoney}}</td>
						</tr>
					</tfoot>
				</table>
			</div> -->
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