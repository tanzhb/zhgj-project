<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- BEGIN PAGE HEADER-->

<!-- <h3 class="page-title"> 订单信息
</h3> -->
<div class="page-bar">
    <ul class="page-breadcrumb">
        <li>
            <i class="fa fa-home"></i>
            <a ui-sref="dashboard">首页</a>
            <i class="fa fa-angle-right"></i>
        </li>
        <li>
            <a ui-sref="buyOrder">采购订单</a>
            <i class="fa fa-angle-right"></i>
        </li>
        <li>
            <a>申请</a>
        </li>
    </ul>
<!--     <div class="page-toolbar">
          <div class="btn-group pull-right">
              <button type="button" class="btn btn-fit-height grey-salt dropdown-toggle" onclick="printdiv('buyOrderPrint')"> 
              	<i class="fa fa-print"></i>
                  		打印
              </button>
              
          </div>
      </div> -->
</div>
<div class="row" id="buyOrderPrint">
    <div class="col-md-12">
        <!-- BEGIN EXAMPLE TABLE PORTLET-->
			<div class="portlet light ">
					<!-- <div class="portlet-title">
			               <div class="tools">
			               		<button type="button" ng-click="submitPage()" ng-hide="orderStatusInput" class="btn blue btn-circle  btn-sm">提交审核</button>
				                <button type="button" ng-click="cancelPage()" class="btn default btn-circle  btn-sm"><i class="fa fa-undo"></i> 取消 </button>
				            </div>
					</div> -->
           		 <div class="portlet-body">
					<ul class="nav nav-tabs">
						<li class="active bold">
	                  		<a data-target="#tab_1_1" data-toggle="tab">订单信息</a>
	              		</li>
						<!-- <li class="bold"><a data-target="#tab_1_2" data-toggle="tab">合同信息</a>
						</li> -->
						<li class="bold" ng-show="ClauseFrameworkShow"><a data-target="#tab_1_3" data-toggle="tab">框架条款</a></li>
						<li class="bold"><a data-target="#tab_1_4" data-toggle="tab">物料信息</a></li>
						<li class="bold"><a data-target="#tab_1_5" data-toggle="tab">结算条款</a></li>			
						<li class="bold"><a data-target="#tab_1_6" data-toggle="tab">验收条款</a></li>
						<li class="bold"><a data-target="#tab_1_7" data-toggle="tab">交付条款</a></li>
						<li class="bold"><a data-target="#tab_1_8" data-toggle="tab">售后条款</a></li>
						<li class="bold"><a data-target="#tab_1_9" data-toggle="tab">附件</a></li>
						
					</ul>
					<div class="tab-content">
						<div class="tab-pane fade active in" id="tab_1_1">
				          <div class="portlet-body form">
				              <!-- BEGIN FORM-->
				              <form action="#" id="form_sample_1"  >
				                  <div class="form-body">
				                      <div class="alert alert-danger display-hide">
				                          <button class="close" data-close="alert"></button> 请先输入正确数据！ </div>
				                      <div class="row">
				                          <div class="col-md-4">
				                              <div class="form-group ">
				                                  <label class="control-label col-md-5 bold">采购订单号：</label>
				                                  <div class="control-label col-md-7">
				                                  <p class="form-control-static"> {{buyOrder.orderNum}} </p>
				                                  </div>
				                              </div>
				                          </div>
				                          <div class="col-md-4">
				                          	<div class="form-group ">
				                                  <label class="control-label col-md-5 bold">关联采购申请单号：</label>
				                                  <div class="control-label col-md-7">
				                                  <p class="form-control-static" >{{buyOrder.demandPlanSerial}} </p>
				                                  </div>
				                                  
				                              </div>
				                          </div>
				                          <!--/span-->
				                          <div class="col-md-4">
				                              <div class="form-group ">
				                                  <label class="control-label col-md-5 bold">关联销售订单号：</label>
				                                  <div class="control-label col-md-7">
				                                      <p class="form-control-static" > {{buyOrder.orderSerial}} </p>
				                                  </div>
				                              </div>
				                          </div>
				                          
				                      </div>
				                      <!--/row-->
				                      <div class="row">
				                      		<!--/span-->
				                          <div class="col-md-4">
				                          		<div class="form-group ">
				                                  <label class="control-label col-md-5 bold">合同类型：</label>
				                                  <div class="control-label col-md-7">
				                                    <p class="form-control-static" > {{contract.contractType}} </p>
				                                  </div>
				                              </div>
				                          		
				                          </div>
				                          <!--/span-->
				                          <div class="col-md-4">
				                              <div class="form-group ">
				                                  <label class="control-label col-md-5 bold">采购类型：</label>
				                                  <div class="control-label col-md-7">
				                                      <p class="form-control-static" > {{buyOrder.orderType}} </p>
				                                  </div>
				                              </div>
				                          </div>
				                          <!--/span-->
				                          <div class="col-md-4">
				                              <div class="form-group ">
				                                  <label class="control-label col-md-5 bold">卖方：</label>
				                                  <div class="control-label col-md-7">
				                                      <p class="form-control-static" > {{buyOrder.supplyComId}} </p>
				                                  </div>
				                              </div>
				                          </div>
				                          <!--/span-->
				                      </div>
				                      <!--/row-->
				                      
				                      <div ng-if="buyOrder.orderType =='标准采购(内贸)'||buyOrder.orderType =='VMI采购'">
				                      <div class="row">
				                          <div class="col-md-4">
				                          	<div class="form-group ">
				                                  <label class="control-label col-md-5 bold">买方：</label>
			                                      <div class="control-label col-md-7">
				                                      <p class="form-control-static" > {{buyOrder.seller}} </p>
				                                  </div>
				                              </div>
				                          </div>
				                          <!--/span-->
				                          <div class="col-md-4">
				                              <div class="form-group ">
				                                  <label class="control-label col-md-5 bold">提货方式：</label>
				                                  <div class="control-label col-md-7">
				                                      <p class="form-control-static" > {{buyOrder.deliveryMode}} </p>
				                                  </div>
				                              </div>
				                          </div>
				                          <div class="col-md-4">
				                          	<div class="form-group ">
				                                  <label class="control-label col-md-5 bold">币种：</label>
				                                  <div class="control-label col-md-7">
				                                      <p class="form-control-static" > {{buyOrder.currency}} </p>
				                                  </div>
				                              </div>
				                          </div>
				                          <!--/span-->
				                      </div>
				                      <div class="row">
				                          <div class="col-md-4">
				                              <div class="form-group ">
				                                  <label class="control-label col-md-5 bold">税率：</label>
				                                  <div class="control-label col-md-7">
				                                      <p class="form-control-static" > {{buyOrder.rate}}% </p>
				                                  </div>
				                              </div>
				                          </div>
				                          <div class="col-md-4">
				                          	<div class="form-group ">
				                                  <label class="control-label col-md-5 bold">制单人：</label>
				                                  <div class="control-label col-md-7">
				                                      <p class="form-control-static" > {{buyOrder.maker}} </p>
				                                  </div>
				                              </div>
				                          </div>
				                          <!--/span-->
				                          <div class="col-md-4">
				                              <div class="form-group ">
				                                  <label class="control-label col-md-5 bold">采购日期：</label>
				                                  <div class="control-label col-md-7">
				                                      <p class="form-control-static" > {{buyOrder.orderDate}} </p>
				                                  </div>
				                                  
				                              </div>
				                          </div>
				                          <!--/span-->
				                      </div>
					                  <div class="row">
					                  		<div class="col-md-4">
				                              <div class="form-group ">
				                                 <label class="control-label col-md-5 bold">供应商合同号：</label>
				                                  <div class="control-label col-md-7">
				                                      <p class="form-control-static" > {{contract.contractNumber}} </p>
				                                  </div>
				                              </div>
				                          </div>
				                           <div class="col-md-4">
				                              <div class="form-group ">
				                                 <label class="control-label col-md-5 bold">备注：</label>
				                                  <div class="control-label col-md-7">
				                                      <p class="form-control-static" > {{buyOrder.orderRemark}} </p>
				                                  </div>
				                                  
				                              </div>
				                          </div>
				                          <!--/span-->
				                      </div>
				                      <!--/row-->
				                      </div>
				                      <div ng-if="buyOrder.orderType =='标准采购(外贸)'">
				                      <div class="row">
				                          <div class="col-md-4">
				                          	<div class="form-group ">
				                                  <label class="control-label col-md-5 bold">买方：</label>
				                                  <div class="control-label col-md-7">
				                                      <p class="form-control-static" > {{buyOrder.seller}} </p>
				                                  </div>
				                              </div>
				                          </div>
				                          <!--/span-->
				                          <div class="col-md-4">
				                              <div class="form-group ">
				                                  <label class="control-label col-md-5 bold">提货方式：</label>
				                                  <div class="control-label col-md-7">
				                                      <p class="form-control-static" > {{buyOrder.deliveryMode}} </p>
				                                  </div>
				                              </div>
				                          </div>
				                          <div class="col-md-4">
				                          	<div class="form-group ">
				                                  <label class="control-label col-md-5 bold">币种：</label>
				                                  <div class="control-label col-md-7">
				                                      <p class="form-control-static" > {{buyOrder.currency}} </p>
				                                  </div>
				                              </div>
				                          </div>
				                          <!--/span-->
				                      </div>
				                      <div class="row">
				                          <div class="col-md-4">
				                              <div class="form-group ">
				                                  <label class="control-label col-md-5 bold">税率：</label>
				                                  <div class="control-label col-md-7">
				                                      <p class="form-control-static" > {{buyOrder.rate}}% </p>
				                                  </div>
				                              </div>
				                          </div>
				                          <!--/span-->
				                          
				                          <!--/span-->
				                          <div class="col-md-4">
				                              <div class="form-group ">
				                                  <label class="control-label col-md-5 bold">结算汇率：</label>
				                                  <div class="control-label col-md-7">
				                                      <p class="form-control-static" > {{buyOrder.exchangeRate}} </p>
				                                  </div>
				                              </div>
				                          </div>
				                          <!--/span-->
				                          <div class="col-md-4">
				                          	<div class="form-group ">
				                                  <label class="control-label col-md-5 bold">制单人：</label>
				                                  <div class="control-label col-md-7">
				                                      <p class="form-control-static" > {{buyOrder.maker}} </p>
				                                  </div>
				                              </div>
				                          </div>
				                      </div>
				                      <div class="row">
				                          
				                          <!--/span-->
				                          <div class="col-md-4">
				                              <div class="form-group ">
				                                  <label class="control-label col-md-5 bold">采购日期：</label>
				                                  <div class="control-label col-md-7">
				                                      <p class="form-control-static" > {{buyOrder.orderDate}} </p>
				                                  </div>
				                                  
				                              </div>
				                          </div>
				                          <div class="col-md-4">
				                              <div class="form-group ">
				                                 <label class="control-label col-md-5 bold">供应商合同号：</label>
				                                  <div class="control-label col-md-7">
				                                      <p class="form-control-static" > {{contract.contractNumber}} </p>
				                                  </div>
				                              </div>
				                          </div>
				                          <div class="col-md-4">
				                              <div class="form-group ">
				                                 <label class="control-label col-md-5 bold">备注：</label>
				                                  <div class="control-label col-md-7">
				                                      <p class="form-control-static" > {{buyOrder.orderRemark}} </p>
				                                  </div>
				                                  
				                              </div>
				                          </div>
				                          <!--/span-->
				                      </div>
				                      <!--/row-->
				                      </div>
				                      <div ng-if="buyOrder.orderType =='委托采购'">
				                      <div class="row">
				                      	<div class="col-md-4">
				                          	<div class="form-group ">
				                                  <label class="control-label col-md-5 bold">买方：</label>
				                                  <div class="control-label col-md-7">
				                                      <p class="form-control-static" > {{buyOrder.seller}} </p>
				                                  </div>
				                              </div>
				                          </div>
				                           <!--/span-->
				                          <div class="col-md-4">
				                          	<div class="form-group ">
				                                  <label class="control-label col-md-5 bold">委托方：</label>
				                                  <div class="control-label col-md-7">
				                                      <p class="form-control-static" > {{buyOrder.entrustParty}} </p>
				                                  </div>
				                              </div>
				                          </div>
				                          <div class="col-md-4">
				                              <div class="form-group ">
				                                  <label class="control-label col-md-5 bold">服务模式：</label>
				                                  <div class="control-label col-md-7">
				                                      <p class="form-control-static" > {{buyOrder.serviceModel}} </p>
				                                  </div>
				                                  
				                              </div>
				                          </div>
				                          <!--/span-->
				                      </div>
				                      <div class="row">
				                      	<div class="col-md-4">
				                              <div class="form-group ">
				                                  <label class="control-label col-md-5 bold">结算方式：</label>
				                                  <div class="control-label col-md-7">
				                                      <p class="form-control-static" > {{buyOrder.settlementClause}} </p>
				                                  </div>
				                                  
				                              </div>
				                          </div>
				                           <!--/span-->
				                          <div class="col-md-4">
				                              <div class="form-group ">
				                                  <label class="control-label col-md-5 bold">提货方式：</label>
				                                  <div class="control-label col-md-7">
				                                      <p class="form-control-static" > {{buyOrder.deliveryMode}} </p>
				                                  </div>
				                              </div>
				                          </div>
				                          <div class="col-md-4">
				                          	<div class="form-group ">
				                                  <label class="control-label col-md-5 bold">币种：</label>
				                                  <div class="control-label col-md-7">
				                                      <p class="form-control-static" > {{buyOrder.currency}} </p>
				                                  </div>
				                              </div>
				                          </div>
				                          <!--/span-->
				                      </div>
				                      <div class="row">
				                      		<div class="col-md-4">
				                              <div class="form-group ">
				                                  <label class="control-label col-md-5 bold">税率：</label>
				                                  <div class="control-label col-md-7">
				                                      <p class="form-control-static" > {{buyOrder.rate}}% </p>
				                                  </div>
				                              </div>
				                          </div>
				                          <div class="col-md-4">
				                          	<div class="form-group ">
				                                  <label class="control-label col-md-5 bold">制单人：</label>
				                                  <div class="control-label col-md-7">
				                                      <p class="form-control-static" > {{buyOrder.maker}} </p>
				                                  </div>
				                              </div>
				                          </div>
				                          <!--/span-->
				                          <div class="col-md-4">
				                              <div class="form-group ">
				                                  <label class="control-label col-md-5 bold">采购日期：</label>
				                                  <div class="control-label col-md-7">
				                                      <p class="form-control-static" > {{buyOrder.orderDate}} </p>
				                                  </div>
				                                  
				                              </div>
				                          </div>
				                          <!--/span-->
				                      </div>
				                      <div class="row">
				                          <!--/span-->
				                          <div class="col-md-4">
				                              <div class="form-group ">
				                                 <label class="control-label col-md-5 bold">供应商合同号：</label>
				                                  <div class="control-label col-md-7">
				                                      <p class="form-control-static" > {{contract.contractNumber}} </p>
				                                  </div>
				                              </div>
				                          </div>
				                           <div class="col-md-4">
				                              <div class="form-group ">
				                                 <label class="control-label col-md-5 bold">备注：</label>
				                                  <div class="control-label col-md-7">
				                                      <p class="form-control-static" > {{buyOrder.orderRemark}} </p>
				                                  </div>
				                                  
				                              </div>
				                          </div>
				                      </div>
				                      <!--/row-->
				                      </div>
				                      
				                  </div>
								</form>
				             </div>   
						</div>
						<!-- <div class="tab-pane fade " id="tab_1_2">
							合同信息start
				          <div class="portlet-body form">
				              BEGIN FORM
				              <form action="#" id="form_contract"  >
				                  <div class="form-body">
				                      <div class="alert alert-danger display-hide">
				                          <button class="close" data-close="alert"></button> 请先输入正确数据！ </div>
				                      <div class="row">
				                          <div class="col-md-4">
				                              <div class="form-group ">
				                                  <label class="control-label col-md-5 bold">合同编号：</label>
				                                  <div class="control-label col-md-7">
				                                  <input type="text" name="contractNum" class="form-control" ng-hide="contractInput" ng-model="contract.contractNum"  >
				                                      <div class="form-control-focus"> </div>
				                                      <span class="help-block" ng-hide="contractInput">请输入合同编号</span>
				                                      <p class="form-control-static" ng-show="contractShow"> {{contract.contractNum}} </p>
				                                  </div>
				                                  
				                              </div>
				                          </div>
				                          /span
				                          <div class="col-md-4">
				                              <div class="form-group ">
				                                  <label class="control-label col-md-5 bold">开始日期：</label>
				                                  <div class="control-label col-md-7">
				                                  		<input type="text" class="form-control form-control-inline input-medium date-picker" 
				                                      data-date-format="yyyy-mm-dd" data-date-viewmode="years" size="16" ng-model="contract.startDate"  ng-hide="contractInput" id="startDate" name="startDate"/>
														<div class="form-control-focus"> </div>
														<span class="help-block" ng-hide="contractInput">请输入开始日期</span>
				                                      <p class="form-control-static" ng-show="contractShow"> {{contract.startDate}} </p>
				                                  </div>
				                              </div>
				                          </div>
				                          /span
				                          <div class="col-md-4">
				                              <div class="form-group ">
				                                  <label class="control-label col-md-5 bold">结束日期：</label>
				                                  <div class="control-label col-md-7">
				                                  		<input type="text" class="form-control form-control-inline input-medium date-picker" 
				                                      data-date-format="yyyy-mm-dd" data-date-viewmode="years" size="16" ng-model="contract.endDate"  ng-hide="contractInput" id="endDate" name="endDate"/>
														<div class="form-control-focus"> </div>
														<span class="help-block" ng-hide="contractInput">请输入结束日期</span>
				                                      <p class="form-control-static" ng-show="contractShow"> {{contract.endDate}} </p>
				                                  </div>
				                              </div>
				                          </div>
				                          /span
				                      </div>
				                      /row
				                      <div class="row">
				                          <div class="col-md-4">
				                              <div class="form-group ">
				                                  <label class="control-label col-md-5 bold">签订日期：</label>
				                                  <div class="control-label col-md-7">
				                                  		<input type="text" class="form-control form-control-inline input-medium date-picker" 
				                                      data-date-format="yyyy-mm-dd" data-date-viewmode="years" size="16" ng-model="contract.signDate" ng-hide="contractInput"  id="signDate" name="signDate"/>
														<div class="form-control-focus"> </div>
														<span class="help-block" ng-hide="contractInput">请输入签订日期</span>
				                                      <p class="form-control-static" ng-show="contractShow"> {{contract.signDate}} </p>
				                                  </div>
				                              </div>
				                          </div>
				                          /span
				                          <div class="col-md-4">
				                              <div class="form-group ">
				                                  <label class="control-label col-md-5 bold">签约地点：</label>
				                                  <div class="control-label col-md-7">
				                                  <input type="text" name="signerAddress" class="form-control" ng-hide="contractInput" ng-model="contract.signerAddress"  >
				                                      <div class="form-control-focus"> </div>
				                                      <span class="help-block" ng-hide="contractInput">请输入签约地点</span>
				                                      <p class="form-control-static" ng-show="contractShow"> {{contract.signerAddress}} </p>
				                                  </div>
				                              </div>
				                          </div>
				                          /span
				                          <div class="col-md-4">
				                              <div class="form-group ">
				                                  <label class="control-label col-md-5 bold">签订人：</label>
				                                  <div class="control-label col-md-7">
				                                  <input type="text" name="signer" class="form-control" ng-hide="contractInput" ng-model="contract.signer"  >
				                                      <div class="form-control-focus"> </div>
				                                      <span class="help-block" ng-hide="contractInput">请输入签订人</span>
				                                      <p class="form-control-static" ng-show="contractShow"> {{contract.signer}} </p>
				                                  </div>
				                              </div>
				                          </div>
				                          /span
				                      </div>
				                      /row
				                      <div class="row">
				                          <div class="col-md-4">
				                              <div class="form-group ">
				                                  <label class="control-label col-md-5 bold">合同附件：</label>
				                                  <div class="control-label col-md-7">
				                                  		<div ng-hide="contractInput"   ng-if="contract.electronicContract==null||contract.electronicContract==''"  class="fileinput fileinput-new" data-provides="fileinput">
				                                             <span class="btn blue btn-circle btn-file">
				                                                 <span class="fileinput-new">上传附件</span>
				                                                 <span class="fileinput-exists">更改</span>
				                                                 <input type="file" name="electronicContract" nv-file-select uploader="uploader" onchange="angular.element(this).scope().up(this.files[0])" ng-model="contract.electronicContract" ng-click="uploadFile('electronicContract')" > </span>
				                                             <span class="fileinput-filename">{{contract.electronicContract.substring(contract.electronicContract.indexOf("_")+1)}}</span> &nbsp;
				                                             <a href="javascript：;" class="close fileinput-exists" ng-click="removefile('electronicContract')" data-dismiss="fileinput"> </a>
				                                         </div>
				                                         <div ng-hide="contractInput"   ng-if="contract.electronicContract!=null&&contract.electronicContract!=''"  class="fileinput fileinput-exists" data-provides="fileinput">
				                                             <span class="btn blue btn-circle btn-file">
				                                                 <span class="fileinput-new">上传附件</span>
				                                                 <span class="fileinput-exists">更改</span>
				                                                 <input type="file" name="electronicContract" nv-file-select uploader="uploader" onchange="angular.element(this).scope().up(this.files[0])" ng-model="contract.electronicContract" ng-click="uploadFile('electronicContract')" > </span>
				                                             <span class="fileinput-filename">{{contract.electronicContract.substring(contract.electronicContract.indexOf("_")+1)}}</span> &nbsp;
				                                             <a href="javascript：;" class="close fileinput-exists"  ng-click="removefile('electronicContract')" data-dismiss="fileinput"> </a>
				                                         </div>
				                                       	<p class="form-control-static"  id="noFileFlag" ng-show="contractShow" ng-if="contract.electronicContract==null||contract.electronicContract==''" class="c_edit" >未上传附件</p>
				                                       	<p class="form-control-static"  ng-show="contractShow" ng-if="contract.electronicContract!=null&&contract.electronicContract!=''" class="c_edit" ><a href="javascript：;" ng-click="downloadFile(contract.electronicContract)">{{contract.electronicContract.substring(contract.electronicContract.indexOf("_")+1)}}</a></p>
				                                  </div>
				                              </div>
				                          </div>
				                          /span
				                          <div class="col-md-4">
				                              <div class="form-group ">
				                                  <label class="control-label col-md-5 bold">纸质合同：</label>
				                                  <div class="control-label col-md-7">
				                                  		<div ng-hide="contractInput"   ng-if="contract.signContract==null||contract.signContract==''"  class="fileinput fileinput-new" data-provides="fileinput">
				                                             <span class="btn blue btn-circle btn-file">
				                                                 <span class="fileinput-new">上传附件</span>
				                                                 <span class="fileinput-exists">更改</span>
				                                                 <input type="file" name="signContract" nv-file-select uploader="uploader" onchange="angular.element(this).scope().up(this.files[0])" ng-model="contract.signContract" ng-click="uploadFile('signContract')" > </span>
				                                             <span class="fileinput-filename">{{contract.signContract.substring(contract.signContract.indexOf("_")+1)}}</span> &nbsp;
				                                             <a href="javascript：;" class="close fileinput-exists" ng-click="removefile('signContract')" data-dismiss="fileinput"> </a>
				                                         </div>
				                                         <div ng-hide="contractInput"   ng-if="contract.signContract!=null&&contract.signContract!=''"  class="fileinput fileinput-exists" data-provides="fileinput">
				                                             <span class="btn blue btn-circle btn-file">
				                                                 <span class="fileinput-new">上传附件</span>
				                                                 <span class="fileinput-exists">更改</span>
				                                                 <input type="file" name="signContract" nv-file-select uploader="uploader" onchange="angular.element(this).scope().up(this.files[0])" ng-model="contract.signContract" ng-click="uploadFile('signContract')" > </span>
				                                             <span class="fileinput-filename">{{contract.signContract.substring(contract.signContract.indexOf("_")+1)}}</span> &nbsp;
				                                             <a href="javascript：;" class="close fileinput-exists"  ng-click="removefile('signContract')" data-dismiss="fileinput"> </a>
				                                         </div>
				                                       	<p class="form-control-static"  ng-show="contractShow" ng-if="contract.signContract==null||contract.signContract==''" class="c_edit" >未上传附件</p>
				                                       	<p class="form-control-static"  ng-show="contractShow" ng-if="contract.signContract!=null&&contract.signContract!=''" class="c_edit" ><a href="javascript：;" ng-click="downloadFile(contract.signContract)">{{contract.signContract.substring(contract.signContract.indexOf("_")+1)}}</a></p>
				
				                                  </div>
				                                  
				                              </div>
				                          </div>
				                          /span
				                          <div class="col-md-4">
				                              <div class="form-group ">
				                                 <label class="control-label col-md-5 bold">供应商合同号：</label>
				                                  <div class="control-label col-md-7">
				                                  <input type="text" name="contractNumber" class="form-control" ng-hide="contractInput" ng-model="contract.contractNumber"  >
				                                      <div class="form-control-focus"> </div>
				                                      <span class="help-block" ng-hide="contractInput">请输入合同号</span>
				                                      <p class="form-control-static" ng-show="contractShow"> {{contract.contractNumber}} </p>
				                                  </div>
				                              </div>
				                          </div>
				                          /span
				                      </div>
				                      /row
				                  </div>
								</form>
				             </div>   
				             合同信息end
				         </div> -->
						<div class="tab-pane fade " id="tab_1_3">
						<!-- 框架条款start-->
				           <div class="portlet-body form"  ng-show="ClauseFrameworkShow">
							     <form id="form_sample_framework"   >
							         <div class="table-scrollable">
				                          <table class="table table-bordered table-hover">
				                              <thead>
				                                  <tr>
				                                      <th>选择条款</th>
				                                      <th>条款内容</th>
				                                      <th>是否继承</th>
				                                      <th>是否审批</th>
				                                      <th>备注</th>
				                                  </tr>
				                              </thead>
				                              <tbody>
				                                  <tr ng-repeat="_ClauseFramework in ClauseFramework track by $index">
				                                      <td>
							                                <p class="form-control-static" > {{_ClauseFramework.clauseType}} </p>
							                          </td>
							                          <td>
							                                <p class="form-control-static" > {{_ClauseFramework.clauseContent}} </p>
							                          </td>
				                                      <td>
							                                <p class="form-control-static" > {{_ClauseFramework.isExtends}} </p>
							                           </td>
				                                      <td>
							                                <p class="form-control-static" > {{_ClauseFramework.isApproval}} </p>
														</td>
				                                      <td>
							                                <p class="form-control-static" > {{_ClauseFramework.remark}} </p>
							                          </td>
				                                  </tr>
				                              </tbody>
				                          </table>
				                      </div>
				                  </form>
				          </div>
				          <!-- 框架条款 end-->
						</div>
						<div class="tab-pane fade " id="tab_1_4">
							<!-- 订单物料 start-->
				           <div class="portlet-body form">
							     <form id="form_sample_5"   >
							         <div class="table-scrollable">
				                          <table class="table table-bordered table-hover">
				                              <thead>
				                                  <tr>
													<th>商品编号</th>
													<th ng-if="buyOrder.orderType =='标准采购(外贸)'">海关编码</th>
													<th>物料名称</th>
													<th>规格型号</th>
													<th>单位</th>
													<th>库存数量</th>
													<th>采购数量</th>
													<th>指导单价</th>
													<th>采购单价</th>
													<th>币种</th>
													<th>税率</th>
													<th>含税采购单价</th>
													<th>金额</th>
													<th>税额</th>
													<th ng-if="buyOrder.orderType =='标准采购(外贸)'">关税率</th>
													<th ng-if="buyOrder.orderType =='标准采购(外贸)'">关税金额</th>
													<th ng-if="buyOrder.settlementClause =='服务费'">服务费率</th>
													<th ng-if="buyOrder.settlementClause =='服务费'">服务费</th>
													<th ng-if="buyOrder.settlementClause =='折扣折让'">折扣率</th>
													<th ng-if="buyOrder.settlementClause =='折扣折让'">折后金额</th>
													<th ng-if="buyOrder.settlementClause =='红票'">红票金额</th>
													
													<th>价税合计</th>
													<th>交付日期</th>
													<th>最晚交付日期</th>
													<th>交付/提货地点</th>
				                                  </tr>
				                              </thead>
				                              <tbody>
				                                  <tr ng-repeat="_orderMateriel in orderMateriel track by $index" >
							                          <td>
							                                <p class="form-control-static" > {{_orderMateriel.materiel.materielNum}} </p>
							                          </td>
							                          <td ng-if="buyOrder.orderType =='标准采购(外贸)'">
							                          		<p class="form-control-static" > {{_orderMateriel.materiel.customsCode}} </p>
							                          </td>
							                          <td>
						                                 	<p class="form-control-static" > {{_orderMateriel.materiel.materielName}} </p>
							                          </td>
							                           <td>
				                                      		<p class="form-control-static" > {{_orderMateriel.materiel.specifications}} </p>
							                          </td>
							                          <td>
				                                      		<p class="form-control-static" > {{_orderMateriel.materiel.unit}} </p>
							                          </td>
							                          <td>
				                                      		<p class="form-control-static" > ... </p>
							                          </td>
							                          <td>  
				                                      		<p class="form-control-static" > {{_orderMateriel.amount}} </p>
							                          </td>
							                          <td>  
				                                      		<p class="form-control-static" > {{_orderMateriel.materiel.unitPrice}} </p>
							                          </td>
							                          <td>  
				                                      		<p class="form-control-static" > {{_orderMateriel.orderUnitPrice}} </p>
							                          </td>
							                          <td>  
				                                      		<p class="form-control-static" > {{buyOrder.currency}} </p>
							                          </td>
							                          <td>  
				                                      		<p class="form-control-static" > {{buyOrder.rate}}% </p>
							                          </td>
							                          <td>  
				                                      		<p class="form-control-static"> {{_arithmeticRateUnit(this)}} </p>
							                          </td>
							                          <td>  
				                                      		<p class="form-control-static" > {{_arithmeticAmount(this)}} </p>
							                          </td>
							                          <td>  
				                                      		<p class="form-control-static"> {{_arithmeticRateAmount(this)}} </p>
							                          </td>
							                          <td ng-if="buyOrder.orderType =='标准采购(外贸)'">
				                                      		<p class="form-control-static" > {{_orderMateriel.customsRate}} </p>
							                          </td>
													  <td ng-if="buyOrder.orderType =='标准采购(外贸)'">
													  		<p class="form-control-static" > {{_arithmeticCustomsRateAmount(this)}} </p>
													  </td>
							                          <td ng-if="buyOrder.settlementClause =='服务费'">
				                                      	<p class="form-control-static" > {{_orderMateriel.serviceRate}} </p>
							                          </td>
													<td ng-if="buyOrder.settlementClause =='服务费'">服务费</td>
													<td ng-if="buyOrder.settlementClause =='折扣折让'">
				                                      	<p class="form-control-static" > {{_orderMateriel.discountRate}} </p>
													</td>
													<td ng-if="buyOrder.settlementClause =='折扣折让'">折后金额</td>
													<td ng-if="buyOrder.settlementClause =='红票'">
				                                      	<p class="form-control-static" > {{_orderMateriel.redTicket}} </p>
													</td>
							                          <td>  
				                                      		<p class="form-control-static"> {{_arithmeticRateAndAmount(this)}} </p>
							                          </td>
							                          <td>  
				                                      		<p class="form-control-static" > {{_orderMateriel.deliveryDate}} </p>
							                          </td>
							                          <td>  
				                                      		<p class="form-control-static" > {{_orderMateriel.lastDeliveryDate}} </p>
							                          </td>
							                          <td>  
				                                      		<p class="form-control-static" > {{_orderMateriel.deliveryAddress}} </p>
							                          </td>
				                                      
				                                  </tr>
				                                  <tr>
													<th></th>
													<th ng-if="buyOrder.orderType =='标准采购(外贸)'"></th>
													<th>合计</th>
													<th>{{totalCount()}}</th>
													<th></th>
													<th></th>
													<th></th>
													<th></th>
													<th></th>
													<th></th>
													<th></th>
													<th></th>
													<th>{{totalAmount()}}</th>
													<th>{{totalRateAmount()}}</th>
													<th ng-if="buyOrder.orderType =='标准采购(外贸)'"></th>
													<th ng-if="buyOrder.orderType =='标准采购(外贸)'">{{totalCustomsRateAmount()}}</th>
													<th ng-if="buyOrder.settlementClause =='服务费'">服务费率</th>
													<th ng-if="buyOrder.settlementClause =='服务费'">服务费</th>
													<th ng-if="buyOrder.settlementClause =='折扣折让'">折扣率</th>
													<th ng-if="buyOrder.settlementClause =='折扣折让'">折后金额</th>
													<th ng-if="buyOrder.settlementClause =='红票'">红票金额</th>
													
													<th>{{totalRateAndAmount()}}</th>
													<th></th>
													<th></th>
													<th></th>
				                                  </tr>
				                              </tbody>
				                          </table>
				                      </div>
				                  </form>
				          </div>
				          <!-- 订单物料 end-->
						</div>
						<div class="tab-pane fade " id="tab_1_5">
							<!--结算条款start-->
				          <div class="portlet-body form">
				              <!-- BEGIN FORM-->
				              <form action="#" id="form_clauseSettlement"  >
				                  <div class="form-body">
				                      <div class="alert alert-danger display-hide">
				                          <button class="close" data-close="alert"></button> 请先输入正确数据！ </div>
				                      <div class="row">
				                          <div class="col-md-4">
				                              <div class="form-group ">
				                                  <label class="control-label col-md-5 bold">商品金额：</label>
				                                  <div class="control-label col-md-7">
				                                  <p class="form-control-static"> {{totalAmount()}} </p>
				                                  </div>
				                              </div>
				                          </div>
				                          <!--/span-->
				                          <div class="col-md-4">
				                          		<div class="form-group ">
					                              	<label class="control-label col-md-5 bold">税额(含关税)：</label>
					                                 <div class="control-label col-md-7">
					                                  <p class="form-control-static">{{totalRateAndCustomsAmount()}}</p>
					                                  </div>
				                               </div>
				                          </div>
				                          <div class="col-md-4">
				                          		<div class="form-group ">
					                              	<label class="control-label col-md-5 bold">价税合计：</label>
					                                 <div class="control-label col-md-7">
					                                  <p class="form-control-static"> {{totalRateAndAmount()}} </p>
					                                  </div>
				                               </div>
				                          </div>
				                          <!--/span-->
				                      </div>
				                      <!--/row-->
				                      <div class="row">
				                          <!--/span-->
				                          <div class="col-md-4">
				                              <div class="form-group ">
				                                  <label class="control-label col-md-5 bold">其他费用：</label>
				                                  <div class="control-label col-md-7">
				                                      <p class="form-control-static" > {{clauseSettlement.otherAmount}} </p>
				                               		</div>
				                              </div>
				                          </div>
				                          <div class="col-md-4">
				                              <div class="form-group ">
				                                  <label class="control-label col-md-5 bold">订单金额：</label>
			                                      <div class="control-label col-md-7">
				                                  <p class="form-control-static"> {{totalOrderAmount()}} </p>
				                                  </div>
				                              </div>
				                          </div>
				                          <!--/span-->
				                          <div class="col-md-4">
				                              <div class="form-group ">
				                                  <label class="control-label col-md-5 bold">备注：</label>
				                                  <div class="control-label col-md-7">
				                                      <p class="form-control-static" > {{clauseSettlement.remark}} </p>
				                               		</div>
				                              </div>
				                          </div>
				                          <!--/span-->
				                      </div>
				                      <!--/row-->
				                  </div>
								</form>
								<form id="form_sample_3"   >
							         <div class="table-scrollable">
				                          <table class="table table-bordered table-hover">
				                              <thead>
				                                  <tr>
				                                      <th>支付类型</th>
				                                      <th>支付节点</th>
				                                      <th>账期</th>
				                                      <th>支付比率%</th>
				                                      <th>支付金额</th>
				                                      <th>支付方式</th>
				                                      <th>开票方式</th>
				                                      <th>开票金额</th>
				                                      <th>未开金额</th>
				                                      <th>备注</th>
				                                  </tr>
				                              </thead>
				                              <tbody>
				                                  <tr ng-repeat="_CSD in clauseSettlement.CSD track by $index">
				                                      <td>
							                                <p class="form-control-static" > {{_CSD.paymentType}} </p>
							                          </td>
							                          <td>
							                                <p class="form-control-static" > {{_CSD.deliveryNode}} </p>
							                          </td>
				                                      <td>
							                                <p class="form-control-static" > {{_CSD.accountPeriod}} </p>
							                          </td>
							                          <td>
							                                <p class="form-control-static" > {{_CSD.deliveryRate}} </p>
							                          </td>
							                          <td>
							                                <p class="form-control-static" > {{_CSD.deliveryAmount}} </p>
							                          </td>
							                          <td>
							                                <p class="form-control-static" > {{_CSD.paymentMethod}} </p>
							                          </td>
							                          <td>
							                                <p class="form-control-static" > {{_CSD.billingMethod}} </p>
							                          </td>
							                          <td>
							                                <p class="form-control-static" > {{_CSD.billingAmount}} </p>
							                          </td>
							                          <td>
							                                <p class="form-control-static" > {{_CSD.unbilledAmount}} </p>
							                          </td>
							                          <td>
							                                <p class="form-control-static" > {{_CSD.remark}} </p>
							                          </td>
				                                  </tr>
				                              </tbody>
				                          </table>
				                      </div>
				                  </form>
				             </div>   
				          <!--结算条款end-->
						</div>
						<div class="tab-pane fade " id="tab_1_6">
							<!--验收条款start-->
				          <div class="portlet-body form">
				              <!-- BEGIN FORM-->
				              <form action="#" id="form_clauseCheckAccept"  >
				                  <div class="form-body">
				                      <div class="alert alert-danger display-hide">
				                          <button class="close" data-close="alert"></button> 请先输入正确数据！ </div>
				                      <!--/row-->
				                      <div class="row">
				                          <div class="col-md-4">
				                              <div class="form-group ">
				                                  <label class="control-label col-md-5 bold">验收方：</label>
				                                  <div class="control-label col-md-7">
				                                      <p class="form-control-static" > {{clauseCheckAccept.checkParty}} </p>
				                               		</div>
				                              </div>
				                          </div>
				                          <!--/span-->
				                          <div class="col-md-4">
				                              <div class="form-group ">
				                                  <label class="control-label col-md-5 bold">验收标准：</label>
				                                  <div class="control-label col-md-7">
				                                      <p class="form-control-static" > {{clauseCheckAccept.acceptStandard}} </p>
				                               		</div>
				                              </div>
				                          </div>
				                          <!--/span-->
				                          <div class="col-md-4">
				                              <div class="form-group ">
				                                  <label class="control-label col-md-5 bold">补充说明：</label>
				                                  <div class="control-label col-md-7">
				                                      <p class="form-control-static" > {{clauseCheckAccept.remark}} </p>
				                               		</div>
				                              </div>
				                          </div>
				                          <!-- <div class="col-md-4">
				                              <div class="form-group ">
				                                  <label class="control-label col-md-5 bold">规格型号：</label>
				                                  <div class="control-label col-md-7">
				                                  		<input type="text" name="specifications" class="form-control" ng-hide="clauseCheckAcceptInput" ng-model="clauseCheckAccept.specifications"  >
				                                      <div class="form-control-focus"> </div>
				                                      <span class="help-block" ng-hide="clauseCheckAcceptInput">请输入规格型号</span>
				                                      <p class="form-control-static" >{{clauseCheckAccept.specifications}} </p>
				                               		</div>
				                              </div>
				                          </div> -->
				                          <!--/span-->
				                      </div>
				                      <!--/row-->
				                  </div>
								</form>
				             </div>   
				             <!--验收条款end-->
						</div>
						<div class="tab-pane fade " id="tab_1_7">
							<!--交付条款start-->
				          <div class="portlet-body form">
				              <!-- BEGIN FORM-->
				              <form action="#" id="form_clauseDelivery"  >
				                  <div class="form-body">
				                      <div class="alert alert-danger display-hide">
				                          <button class="close" data-close="alert"></button> 请先输入正确数据！ </div>
				                      <!--/row-->
				                      <div class="row">
				                          <div class="col-md-4">
				                              <div class="form-group ">
				                                  <label class="control-label col-md-5 bold">运输方式：</label>
				                                  <div class="control-label col-md-7">
				                                      <p class="form-control-static" > {{clauseDelivery.transportType}} </p>
				                               		</div>
				                              </div>
				                          </div>
				                          <!--/span-->
				                          <div class="col-md-4">
				                              <div class="form-group ">
				                                  <label class="control-label col-md-5 bold">包装要求：</label>
				                                  <div class="control-label col-md-7">
				                                      <p class="form-control-static" > {{clauseDelivery.packingRequire}} </p>
				                               		</div>
				                              </div>
				                          </div>
				                          <!--/span-->
				                          <div class="col-md-4">
				                              <div class="form-group ">
				                                  <label class="control-label col-md-5 bold">包装规格型号：</label>
				                                  <div class="control-label col-md-7">
				                                      <p class="form-control-static" > {{clauseDelivery.specifications}} </p>
				                               		</div>
				                              </div>
				                          </div>
				                          <!--/span-->
				                      </div>
				                      <!--/row-->
				                  </div>
								</form>
				             </div>   
				             <!--交付条款end-->
						</div>
						<div class="tab-pane fade " id="tab_1_8">
							<!--售后条款start-->
				          <div class="portlet-body form">
				              <!-- BEGIN FORM-->
				              <form action="#" id="form_clauseAfterSales"  >
				                  <div class="form-body">
				                      <div class="alert alert-danger display-hide">
				                          <button class="close" data-close="alert"></button> 请先输入正确数据！ </div>
				                      <!--/row-->
				                      <div class="row">
				                          <div class="col-md-4">
				                              <div class="form-group ">
				                                  <label class="control-label col-md-5 bold">质保要求：</label>
				                                  <div class="control-label col-md-7">
				                                      <p class="form-control-static" > {{clauseAfterSales.qualityRequirements}} </p>
				                               		</div>
				                              </div>
				                          </div>
				                          <!--/span-->
				                          <div class="col-md-4">
				                              <div class="form-group ">
				                                  <label class="control-label col-md-5 bold">售后响应：</label>
				                                  <div class="control-label col-md-7">
				                                      <p class="form-control-static" > {{clauseAfterSales.afterSaleResponse}} </p>
				                               		</div>
				                              </div>
				                          </div>
				                          <!--/span-->
				                          <div class="col-md-4">
				                              <div class="form-group ">
				                                  <label class="control-label col-md-5 bold">补充说明：</label>
				                                  <div class="control-label col-md-7">
				                                      <p class="form-control-static" > {{clauseAfterSales.remark}} </p>
				                               		</div>
				                              </div>
				                          </div>
				                          <!--/span-->
				                      </div>
				                      <!--/row-->
				                  </div>
								</form>
				             </div>   
				             <!--售后条款end-->
						</div>
                        <div class="tab-pane fade " id="tab_1_9">
                        <!-- 附件 start-->
				           <div class="portlet-body form">
							     <form id="form_sample_4"   >
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
				                                  </tr>
				                              </thead>
				                              <tbody>
				                                  <tr ng-repeat="_file in file track by $index">
							                          <td>
							                                <p class="form-control-static" > {{_file.fileType}} </p>
							                          </td>
							                           <td>
							                                <p class="form-control-static" > {{_file.fileDescribe}} </p>
							                          </td>
				                                       <td>
				                                       	<label    ng-if="file[$index].file==null||file[$index].file==''" class="c_edit" >未上传附件</label>
				                                       	<label    ng-if="file[$index].file!=null&&file[$index].file!=''" class="c_edit" ><a href="javascript:;" ng-click="downloadFile(file[$index])">{{_file.file.substring(_file.file.indexOf("_")+1)}}</a></label>
							                          </td>
							                           <td>
							                                <p class="form-control-static" > {{_file.remark}} </p>
							                          </td>
				                                      <td><p class="form-control-static"> {{_file.uploader}} </p></td>
				                                      <td><p class="form-control-static"> {{_file.uploadDate}} </p></td>
				                                  </tr>
				                              </tbody>
				                          </table>
				                      </div>
					                </form>
				            </div>
				          <!-- 附件 end-->
                        </div>
          
          <!--垫资条款start-->
             <!-- 
          <div class="portlet-body form">
              BEGIN FORM
              <form action="#" id="form_clauseAdvance"  >
                  <div class="form-body">
                      <div class="alert alert-danger display-hide">
                          <button class="close" data-close="alert"></button> 请先输入正确数据！ </div>
                      <div class="row">
                          <div class="col-md-4">
                              <div class="form-group ">
                                  <label class="control-label col-md-5 bold">融资方：</label>
                                  <div class="control-label col-md-7">
                                  <input type="text" name="financing" class="form-control" ng-hide="clauseAdvanceInput" ng-model="clauseAdvance.financing"  >
                                      <div class="form-control-focus"> </div>
                                      <span class="help-block">请输入融资方</span>
                                      <p class="form-control-static" ng-show="clauseAdvanceShow"> {{clauseAdvance.financing}} </p>
                                  </div>
                                  
                              </div>
                          </div>
                          /span
                          <div class="col-md-4">
                          		<div class="form-group ">
	                              	<label class="control-label col-md-5 bold">垫资方：</label>
	                                <div class="control-label col-md-7">
	                                  <input type="text" name="advance" class="form-control" ng-hide="clauseAdvanceInput" ng-model="clauseAdvance.advance"  >
                                      <div class="form-control-focus"> </div>
                                      <span class="help-block">请输入垫资方</span>
                                      <p class="form-control-static" ng-show="clauseAdvanceShow"> {{clauseAdvance.advance}} </p>
	                               	</div>
                               </div>
                          </div>
                          /span
                      </div>
                      /row
                      <div class="row">
                          <div class="col-md-4">
                              <div class="form-group ">
                                  <label class="control-label col-md-5 bold">垫资金额：</label>
                                  <div class="control-label col-md-7">
                                  		<input type="text" name="advanceAmount" class="form-control" ng-hide="clauseAdvanceInput" ng-model="clauseAdvance.advanceAmount"  >
                                      <div class="form-control-focus"> </div>
                                      <span class="help-block">请输入垫资金额</span>
                                      <p class="form-control-static" ng-show="clauseAdvanceShow"> {{clauseAdvance.advanceAmount}} </p>
                               		</div>
                              </div>
                          </div>
                          /span
                          <div class="col-md-4">
                              <div class="form-group ">
                                  <label class="control-label col-md-5 bold">保证金比率：</label>
                                  <div class="control-label col-md-7">
                                  		<input type="text" name="depositRate" class="form-control" ng-hide="clauseAdvanceInput" ng-model="clauseAdvance.depositRate"  >
                                      <div class="form-control-focus"> </div>
                                      <span class="help-block">请输入保证金比率</span>
                                      <p class="form-control-static" ng-show="clauseAdvanceShow"> {{clauseAdvance.depositRate}} </p>
                               		</div>
                              </div>
                          </div>
                          /span
                          <div class="col-md-4">
                              <div class="form-group ">
                                  <label class="control-label col-md-5 bold">保证金金额：</label>
                                  <div class="control-label col-md-7">
                                  		<input type="text" name="depositAmount" class="form-control" ng-hide="clauseAdvanceInput" ng-model="clauseAdvance.depositAmount"  >
                                      <div class="form-control-focus"> </div>
                                      <span class="help-block">请输入保证金金额</span>
                                      <p class="form-control-static" ng-show="clauseAdvanceShow"> {{clauseAdvance.depositAmount}} </p>
                               		</div>
                              </div>
                          </div>
                          /span
                      </div>
                      /row
                      <div class="row">
                          <div class="col-md-4">
                              <div class="form-group ">
                                  <label class="control-label col-md-5 bold">服务费率：</label>
                                  <div class="control-label col-md-7">
                                  		<input type="text" name="serviceAmountRate" class="form-control" ng-hide="clauseAdvanceInput" ng-model="clauseAdvance.serviceAmountRate"  >
                                      <div class="form-control-focus"> </div>
                                      <span class="help-block">请输入服务费率</span>
                                      <p class="form-control-static" ng-show="clauseAdvanceShow"> {{clauseAdvance.serviceAmountRate}} </p>
                               		</div>
                              </div>
                          </div>
                          /span
                          <div class="col-md-4">
                              <div class="form-group ">
                                  <label class="control-label col-md-5 bold">垫资时间：</label>
                                  <div class="control-label col-md-7">
                                  		<input type="text" name="advanceTime" class="form-control form-control-inline input-medium date-picker" 
                                      data-date-format="yyyy-mm-dd" data-date-viewmode="years" size="16" ng-hide="clauseAdvanceInput" ng-model="clauseAdvance.advanceTime"  >
                                      <div class="form-control-focus"> </div>
                                      <span class="help-block">请选择垫资时间</span>
                                      <p class="form-control-static" ng-show="clauseAdvanceShow"> {{clauseAdvance.advanceTime}} </p>
                               		</div>
                              </div>
                          </div>
                          /span
                          <div class="col-md-4">
                              <div class="form-group ">
                                  <label class="control-label col-md-5 bold">服务费：</label>
                                  <div class="control-label col-md-7">
                                  		<input type="text" name="serviceAmount" class="form-control" ng-hide="clauseAdvanceInput" ng-model="clauseAdvance.serviceAmount"  >
                                      <div class="form-control-focus"> </div>
                                      <span class="help-block">请输入服务费</span>
                                      <p class="form-control-static" ng-show="clauseAdvanceShow"> {{clauseAdvance.serviceAmount}} </p>
                               		</div>
                              </div>
                          </div>
                          /span
                      </div>
                      /row
                  </div>
				</form>
             </div>   --> 
             <!--垫资条款end-->
             
          
          </div>
					
					<!-- //申请原因 -->
					<div class="portlet-body form">
					     <form >
						     <div class="form-body">
			                      <div class="row">
			                          <div class="col-md-8">
			                          		<div class="form-group ">
				                              	<label class="control-label bold">原因：</label>
				                                <div class="">
				                                  <input type="text" name="remark"  class="form-control"  ng-model="buyOrder.remark"  >
			                                      <div class="form-control-focus"> </div>
			                                      <span class="help-block" >请输入原因</span>
				                               	</div>
			                               </div>
			                          </div>
			                      </div>
			                  </div>
			                </form>
		            </div>
		            <div class="portlet-title">
			               <div class="tools">
			               		<button type="button" ng-click="confirmBuyApply()" ng-hide="orderStatusInput" class="btn blue btn-circle  btn-sm">提交申请</button>
				                <button type="button" ng-click="cancelPage()" class="btn default btn-circle  btn-sm"><i class="fa fa-undo"></i> 取消 </button>
				            </div>
					</div>
      			</div>
			</div>
	</div>
</div>

