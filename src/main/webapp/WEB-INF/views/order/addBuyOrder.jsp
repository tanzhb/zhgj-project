<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- BEGIN PAGE HEADER-->

<h3 class="page-title"> 订单信息
</h3>
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
            <a>{{opration}}</a>
        </li>
    </ul>
    <div class="page-toolbar">
          <div class="btn-group pull-right">
              <button type="button" class="btn btn-fit-height grey-salt dropdown-toggle" onclick="printdiv('buyOrderPrint')"> 
              	<i class="fa fa-print"></i>
                  		打印
              </button>
              
          </div>
      </div>
</div>
<div class="row" id="buyOrderPrint">
    <div class="col-md-12">
        <!-- BEGIN EXAMPLE TABLE PORTLET-->
        <div class="portlet bordered">
            <div class="portlet-body">
				<div class="portlet light ">
                        <div class="portlet-title">
                            <div class="caption">订单信息</div>
                            <div class="tools" id="noprintdiv">
                            	<button type="submit" ng-click="save()" ng-hide="buyOrderInput" class="btn blue  btn-outline  btn-sm">
                               		<i class="fa fa-save"></i> 保存 </button>
                               <button ng-click="cancel()" type="button" ng-hide="buyOrderInput" class="btn red  btn-outline  btn-sm">
                               		<i class="fa fa-undo"></i> 取消 </button>
                               <button ng-click="edit()" type="button" ng-show="buyOrderShow&&noShow" class="btn blue  btn-outline  btn-sm">
                               		<i class="fa fa-edit"></i> 编辑 </button>
                               <!--  <a href="javascript：;" class="collapse"> </a> -->
                             </div>
                        </div>
          <div class="portlet-body form">
              <!-- BEGIN FORM-->
              <form action="#" id="form_sample_1"  class="form-horizontal">
                  <div class="form-body">
                      <div class="alert alert-danger display-hide">
                          <button class="close" data-close="alert"></button> 请先输入正确数据！ </div>
                      <div class="row">
                          <div class="col-md-4">
                              <div class="form-group form-md-line-input">
                                  <label class="control-label col-md-3"><span class="required" aria-required="true"> * </span>采购订单号：</label>
                                  <div class="col-md-9">
                                  <input type="text" name="orderNum" class="form-control" ng-hide="buyOrderInput" ng-model="buyOrder.orderNum"  >
                                      <div class="form-control-focus"> </div>
                                      <span class="help-block">请输入采购订单号</span>
                                      <p class="form-control-static" ng-show="buyOrderShow"> {{buyOrder.orderNum}} </p>
                                  </div>
                                  
                              </div>
                          </div>
                          <!--/span-->
                          <div class="col-md-4">
                          		<div class="form-group form-md-line-input">
                                  <label class="control-label col-md-3"><span class="required" aria-required="true"> * </span>合同类型：</label>
                                  <div class="col-md-9">
                                  	<div ng-hide="contractInput">
                                	<input type="radio" ng-click="hidnClauseFramework()"  ng-model="contract.contractType" name="contractType" ng-checked="contract.contractType!='框架合同'" value="普通合同"> 普通合同
                        			<input type="radio" ng-click="showClauseFramework()"  ng-model="contract.contractType" name="contractType" ng-checked="contract.contractType=='框架合同'" value="框架合同"> 框架合同
                                    </div>
                                    <p class="form-control-static" ng-show="contractShow"> {{contract.contractType}} </p>
                                  </div>
                              </div>
                          		
                          </div>
                          <!--/span-->
                          <div class="col-md-4">
                              <div class="form-group form-md-line-input">
                                  <label class="control-label col-md-3"><span class="required" aria-required="true"> * </span>采购类型：</label>
                                  <div class="col-md-9">
                                  		<select class="form-control" id="orderType"  ng-hide="buyOrderInput" name="orderType"  ng-model="buyOrder.orderType" >
                                            <option value=""></option>
                                           	<option value="标准销售" >标准采购</option>
                                             <option value="委托销售" >委托销售</option>
                                             <option value="委托采购" >VIM采购计划</option>
                                        </select>
                                        <div class="form-control-focus"> </div>
                              			<span class="help-block">请选择采购类型</span>
                                      <p class="form-control-static" ng-show="buyOrderShow"> {{buyOrder.orderType}} </p>
                                  </div>
                              </div>
                          </div>
                          <!--/span-->
                      </div>
                      <!--/row-->
                      <div class="row">
                          <div class="col-md-4">
                              <div class="form-group form-md-line-input">
                                  <label class="control-label col-md-3"><span class="required" aria-required="true"> * </span>供应方：</label>
                                  <div class="col-md-9">
                                  <input type="text" name="supplyComId" class="form-control" ng-hide="buyOrderInput" ng-model="buyOrder.supplyComId"  >
                                      <div class="form-control-focus"> </div>
                                      <span class="help-block">请选择供应商</span>
                                      <p class="form-control-static" ng-show="buyOrderShow"> {{buyOrder.supplyComId}} </p>
                                  </div>
                                  
                              </div>
                          </div>
                          <!--/span-->
                          <div class="col-md-4">
                          	<div class="form-group form-md-line-input">
                                  <label class="control-label col-md-3">销售方：</label>
                                  <div class="col-md-9">
                                  <input type="text" name="seller" class="form-control" ng-hide="buyOrderInput" ng-model="buyOrder.seller"  >
                                      <div class="form-control-focus"> </div>
                                      <span class="help-block">请输入销售方</span>
                                      <p class="form-control-static" ng-show="buyOrderShow"> {{buyOrder.seller}} </p>
                                  </div>
                              </div>
                          </div>
                          <!--/span-->
                          <div class="col-md-4">
                          	<div class="form-group form-md-line-input">
                                  <label class="control-label col-md-3">委托方：</label>
                                  <div class="col-md-9">
                                  <input type="text" name="entrustParty" class="form-control" ng-hide="buyOrderInput" ng-model="buyOrder.entrustParty"  >
                                      <div class="form-control-focus"> </div>
                                      <span class="help-block">请输入委托方</span>
                                      <p class="form-control-static" ng-show="buyOrderShow"> {{buyOrder.entrustParty}} </p>
                                  </div>
                              </div>
                          </div>
                          <!--/span-->
                      </div>
                      <!--/row-->
                      <div class="row">
                          <div class="col-md-4">
                              <div class="form-group form-md-line-input">
                                  <label class="control-label col-md-3"><span class="required" aria-required="true"> * </span>服务模式：</label>
                                  <div class="col-md-9">
                                  		<select class="form-control" id="serviceModel"  ng-hide="buyOrderInput" name="serviceModel"  ng-model="buyOrder.serviceModel" >
                                            <option value="无">无</option>
                                           	<option value="仓储服务" >仓储服务</option>
                                             <option value="仓储+垫资服务" >仓储+垫资服务</option>
                                        </select>
                                        <div class="form-control-focus"> </div>
                              			<span class="help-block">请选择服务模式</span>
                                      <p class="form-control-static" ng-show="buyOrderShow"> {{buyOrder.serviceModel}} </p>
                                  </div>
                                  
                              </div>
                          </div>
                          <!--/span-->
                          <div class="col-md-4">
                              <div class="form-group form-md-line-input">
                                  <label class="control-label col-md-3"><span class="required" aria-required="true"> * </span>结算方式：</label>
                                  <div class="col-md-9">
                                  		<select class="form-control" id="settlementClause"  ng-hide="buyOrderInput" name="settlementClause"  ng-model="buyOrder.settlementClause" >
                                            <option value=""></option>
                                           	<option value="进销差" >进销差</option>
                                             <option value="服务费" >服务费</option>
                                             <option value="折扣折让" >折扣折让</option>
                                             <option value="红票" >红票</option>
                                        </select>
                                        <div class="form-control-focus"> </div>
                              			<span class="help-block">请选择结算条款</span>
                                      <p class="form-control-static" ng-show="buyOrderShow"> {{buyOrder.settlementClause}} </p>
                                  </div>
                                  
                              </div>
                          </div>
                          <!--/span-->
                          <div class="col-md-4">
                              <div class="form-group form-md-line-input">
                                  <label class="control-label col-md-3"><span class="required" aria-required="true"> * </span>提货方式：</label>
                                  <div class="col-md-9">
                                  		<select class="form-control" id="deliveryMode"  ng-hide="buyOrderInput" name="deliveryMode"  ng-model="buyOrder.deliveryMode" >
                                            <option value=""></option>
                                           	<option value="仓库自提" >仓库自提</option>
                                             <option value="物料配送" >物料配送</option>
                                        </select>
                                        <div class="form-control-focus"> </div>
                              			<span class="help-block">请选择提货方式</span>
                                      <p class="form-control-static" ng-show="buyOrderShow"> {{buyOrder.deliveryMode}} </p>
                                  </div>
                              </div>
                          </div>
                          <!--/span-->
                      </div>
                      <div class="row">
                          <div class="col-md-4">
                              <div class="form-group form-md-line-input">
                                  <label class="control-label col-md-3"><span class="required" aria-required="true"> * </span>税率：</label>
                                  <div class="col-md-9">
                                  	<select class="form-control" id="rate"  ng-hide="buyOrderInput" name="rate"  ng-model="buyOrder.rate" >
                                           	<option value=""></option>
                                           	<option value="17" >17%</option>
                                             <option value="6" >6%</option>
                                        </select>
                                      <div class="form-control-focus"> </div>
                                      <span class="help-block">请选择入税率</span>
                                      <p class="form-control-static" ng-show="buyOrderShow"> {{buyOrder.rate}}% </p>
                                  </div>
                              </div>
                          </div>
                          <!--/span-->
                          <div class="col-md-4">
                          	<div class="form-group form-md-line-input">
                                  <label class="control-label col-md-3"><span class="required" aria-required="true"> * </span>币种：</label>
                                  <div class="col-md-9">
                                  		<select class="form-control" id="currency"  ng-hide="buyOrderInput" name="currency"  ng-model="buyOrder.currency" >
                                           	<option value=""></option>
                                           	<option value="人民币" >人民币</option>
                                             <option value="美元" >美元</option>
                                        </select>
                                      <div class="form-control-focus"> </div>
                                      <span class="help-block">请选择币种</span>
                                      <p class="form-control-static" ng-show="buyOrderShow"> {{buyOrder.currency}} </p>
                                  </div>
                              </div>
                          </div>
                          <!--/span-->
                          <div class="col-md-4">
                              <div class="form-group form-md-line-input">
                                  <label class="control-label col-md-3">结算汇率：</label>
                                  <div class="col-md-9">
                                        <input type="text" name="exchangeRate" class="form-control" ng-hide="buyOrderInput" ng-model="buyOrder.exchangeRate"  >
                                        <div class="form-control-focus"> </div>
                              			<span class="help-block">请输入结算汇率</span>
                                      <p class="form-control-static" ng-show="buyOrderShow"> {{buyOrder.exchangeRate}} </p>
                                  </div>
                              </div>
                          </div>
                          <!--/span-->
                      </div>
                      <div class="row">
                          <div class="col-md-4">
                          	<div class="form-group form-md-line-input">
                                  <label class="control-label col-md-3">关联采购申请单号：</label>
                                  <div class="col-md-9">
                                  <input type="text"  name="demandPlanSerial" class="form-control" ng-hide="buyOrderInput"  ng-model="buyOrder.demandPlanSerial"  >
                                      <div class="form-control-focus"> </div>
                                      <span class="help-block">请输入关联采购申请单号</span>
                                      <p class="form-control-static" ng-show="buyOrderShow">{{buyOrder.demandPlanSerial}} </p>
                                  </div>
                                  
                              </div>
                          </div>
                          <!--/span-->
                          <div class="col-md-4">
                              <div class="form-group form-md-line-input">
                                  <label class="control-label col-md-3">关联销售订单号：</label>
                                  <div class="col-md-9">
                                  <input type="text" name="orderSerial" class="form-control" ng-hide="buyOrderInput" ng-model="buyOrder.orderSerial"  >
                                      <div class="form-control-focus"> </div>
                                      <span class="help-block">请输入关联销售订单号</span>
                                      <p class="form-control-static" ng-show="buyOrderShow"> {{buyOrder.orderSerial}} </p>
                                  </div>
                              </div>
                          </div>
                          <!--/span-->
                      </div>
                      <div class="row">
                          <div class="col-md-4">
                          	<div class="form-group form-md-line-input">
                                  <label class="control-label col-md-3">制单人：</label>
                                  <div class="col-md-9">
                                  <input type="text" name="maker" class="form-control" ng-hide="buyOrderInput" ng-model="buyOrder.maker"  >
                                      <div class="form-control-focus"> </div>
                                      <span class="help-block">请输入制单人</span>
                                      <p class="form-control-static" ng-show="buyOrderShow"> {{buyOrder.maker}} </p>
                                  </div>
                              </div>
                          </div>
                          <!--/span-->
                          <div class="col-md-4">
                              <div class="form-group form-md-line-input">
                                  <label class="control-label col-md-3">下单日期：</label>
                                  <div class="col-md-9">
                                  <input type="text" name="orderDate" class="form-control form-control-inline input-medium date-picker" 
                                      data-date-format="yyyy-mm-dd" data-date-viewmode="years" size="16" ng-hide="buyOrderInput" ng-model="buyOrder.orderDate"  >
                                      <div class="form-control-focus"> </div>
                                      <span class="help-block">请选择下单日期</span>
                                      <p class="form-control-static" ng-show="buyOrderShow"> {{buyOrder.orderDate}} </p>
                                  </div>
                                  
                              </div>
                          </div>
                          <!--/span-->
                      </div>
                      <!--/row-->
                  </div>
				</form>
             </div>   
             
             <!--合同信息start-->
             <div class="portlet-title">
                            <div class="caption">合同信息</div>
                            <div class="tools" id="noprintdiv">
                            	<button type="submit" ng-click="saveContract()" ng-hide="contractInput" class="btn blue  btn-outline  btn-sm">
                               		<i class="fa fa-save"></i> 保存 </button>
                               <button ng-click="cancelContract()" type="button" ng-hide="contractInput" class="btn red  btn-outline  btn-sm">
                               		<i class="fa fa-undo"></i> 取消 </button>
                               <button ng-click="editContract()" type="button" ng-show="contractShow&&noShow" class="btn blue  btn-outline  btn-sm">
                               		<i class="fa fa-edit"></i> 编辑 </button>
                             </div>
                        </div>
          <div class="portlet-body form">
              <!-- BEGIN FORM-->
              <form action="#" id="form_contract"  class="form-horizontal">
                  <div class="form-body">
                      <div class="alert alert-danger display-hide">
                          <button class="close" data-close="alert"></button> 请先输入正确数据！ </div>
                      <div class="row">
                          <div class="col-md-4">
                              <div class="form-group form-md-line-input">
                                  <label class="control-label col-md-3"><span class="required" aria-required="true"> * </span>合同编号：</label>
                                  <div class="col-md-9">
                                  <input type="text" name="contractNum" class="form-control" ng-hide="contractInput" ng-model="contract.contractNum"  >
                                      <div class="form-control-focus"> </div>
                                      <span class="help-block">请输入合同编号</span>
                                      <p class="form-control-static" ng-show="contractShow"> {{contract.contractNum}} </p>
                                  </div>
                                  
                              </div>
                          </div>
                          <!--/span-->
                          <div class="col-md-4">
                              <div class="form-group form-md-line-input">
                                  <label class="control-label col-md-3"><span class="required" aria-required="true"> * </span>开始日期：</label>
                                  <div class="col-md-9">
                                  		<input type="text" class="form-control form-control-inline input-medium date-picker" 
                                      data-date-format="yyyy-mm-dd" data-date-viewmode="years" size="16" ng-model="contract.startDate"  ng-hide="contractInput" id="startDate" name="startDate"/>
										<div class="form-control-focus"> </div>
										<span class="help-block">请输入开始日期</span>
                                      <p class="form-control-static" ng-show="contractShow"> {{contract.startDate}} </p>
                                  </div>
                              </div>
                          </div>
                          <!--/span-->
                          <div class="col-md-4">
                              <div class="form-group form-md-line-input">
                                  <label class="control-label col-md-3"><span class="required" aria-required="true"> * </span>结束日期：</label>
                                  <div class="col-md-9">
                                  		<input type="text" class="form-control form-control-inline input-medium date-picker" 
                                      data-date-format="yyyy-mm-dd" data-date-viewmode="years" size="16" ng-model="contract.endDate"  ng-hide="contractInput" id="endDate" name="endDate"/>
										<div class="form-control-focus"> </div>
										<span class="help-block">请输入结束日期</span>
                                      <p class="form-control-static" ng-show="contractShow"> {{contract.endDate}} </p>
                                  </div>
                              </div>
                          </div>
                          <!--/span-->
                      </div>
                      <!--/row-->
                      <div class="row">
                          <div class="col-md-4">
                              <div class="form-group form-md-line-input">
                                  <label class="control-label col-md-3">签订日期：</label>
                                  <div class="col-md-9">
                                  		<input type="text" class="form-control form-control-inline input-medium date-picker" 
                                      data-date-format="yyyy-mm-dd" data-date-viewmode="years" size="16" ng-model="contract.signDate" ng-hide="contractInput"  id="signDate" name="signDate"/>
										<div class="form-control-focus"> </div>
										<span class="help-block">请输入签订日期</span>
                                      <p class="form-control-static" ng-show="contractShow"> {{contract.signDate}} </p>
                                  </div>
                              </div>
                          </div>
                          <!--/span-->
                          <div class="col-md-4">
                              <div class="form-group form-md-line-input">
                                  <label class="control-label col-md-3">签约地点：</label>
                                  <div class="col-md-9">
                                  <input type="text" name="signerAddress" class="form-control" ng-hide="contractInput" ng-model="contract.signerAddress"  >
                                      <div class="form-control-focus"> </div>
                                      <span class="help-block">请输入签约地点</span>
                                      <p class="form-control-static" ng-show="contractShow"> {{contract.signerAddress}} </p>
                                  </div>
                              </div>
                          </div>
                          <!--/span-->
                          <div class="col-md-4">
                              <div class="form-group form-md-line-input">
                                  <label class="control-label col-md-3">签订人：</label>
                                  <div class="col-md-9">
                                  <input type="text" name="signer" class="form-control" ng-hide="contractInput" ng-model="contract.signer"  >
                                      <div class="form-control-focus"> </div>
                                      <span class="help-block">请输入签订人</span>
                                      <p class="form-control-static" ng-show="contractShow"> {{contract.signer}} </p>
                                  </div>
                              </div>
                          </div>
                          <!--/span-->
                      </div>
                      <!--/row-->
                      <div class="row">
                          <div class="col-md-4">
                              <div class="form-group form-md-line-input">
                                  <label class="control-label col-md-3"><span class="required" aria-required="true"> * </span>合同附件：</label>
                                  <div class="col-md-9">
                                  		<div ng-hide="contractInput"   ng-if="contract.electronicContract==null||contract.electronicContract==''"  class="fileinput fileinput-new" data-provides="fileinput">
                                             <span class="btn blue btn-outline btn-file">
                                                 <span class="fileinput-new">上传附件</span>
                                                 <span class="fileinput-exists">更改</span>
                                                 <input type="file" name="electronicContract" nv-file-select uploader="uploader" onchange="angular.element(this).scope().up(this.files[0])" ng-model="contract.electronicContract" ng-click="uploadFile('electronicContract')" > </span>
                                                 <div class="form-control-focus"> </div>
                                      			<span class="help-block">请上传合同附件</span>
                                             <span class="fileinput-filename">{{contract.electronicContract.substring(contract.electronicContract.indexOf("_")+1)}}</span> &nbsp;
                                             <a href="javascript：;" class="close fileinput-exists" ng-click="removefile('electronicContract')" data-dismiss="fileinput"> </a>
                                         </div>
                                         <div ng-hide="contractInput"   ng-if="contract.electronicContract!=null&&contract.electronicContract!=''"  class="fileinput fileinput-exists" data-provides="fileinput">
                                             <span class="btn blue btn-outline btn-file">
                                                 <span class="fileinput-new">上传附件</span>
                                                 <span class="fileinput-exists">更改</span>
                                                 <input type="file" name="electronicContract" nv-file-select uploader="uploader" onchange="angular.element(this).scope().up(this.files[0])" ng-model="contract.electronicContract" ng-click="uploadFile('electronicContract')" > </span>
                                                 <div class="form-control-focus"> </div>
                                      				<span class="help-block">请上传合同附件</span>
                                             <span class="fileinput-filename">{{contract.electronicContract.substring(contract.electronicContract.indexOf("_")+1)}}</span> &nbsp;
                                             <a href="javascript：;" class="close fileinput-exists"  ng-click="removefile('electronicContract')" data-dismiss="fileinput"> </a>
                                         </div>
                                       	<p class="form-control-static"  ng-show="contractShow" ng-if="contract.electronicContract==null||contract.electronicContract==''" class="c_edit" >未上传附件</p>
                                       	<p class="form-control-static"  ng-show="contractShow" ng-if="contract.electronicContract!=null&&contract.electronicContract!=''" class="c_edit" ><a href="javascript：;" ng-click="downloadFile(contract.electronicContract)">{{contract.electronicContract.substring(contract.electronicContract.indexOf("_")+1)}}</a></p>
                                  </div>
                              </div>
                          </div>
                          <!--/span-->
                          <div class="col-md-4">
                              <div class="form-group form-md-line-input">
                                  <label class="control-label col-md-3">纸质合同：</label>
                                  <div class="col-md-9">
                                  		<div ng-hide="contractInput"   ng-if="contract.signContract==null||contract.signContract==''"  class="fileinput fileinput-new" data-provides="fileinput">
                                             <span class="btn blue btn-outline btn-file">
                                                 <span class="fileinput-new">上传附件</span>
                                                 <span class="fileinput-exists">更改</span>
                                                 <input type="file" name="signContract" nv-file-select uploader="uploader" onchange="angular.element(this).scope().up(this.files[0])" ng-model="contract.signContract" ng-click="uploadFile('signContract')" > </span>
                                                 <div class="form-control-focus"> </div>
                                      			<span class="help-block">请上传纸质合同</span>
                                             <span class="fileinput-filename">{{contract.signContract.substring(contract.signContract.indexOf("_")+1)}}</span> &nbsp;
                                             <a href="javascript：;" class="close fileinput-exists" ng-click="removefile('signContract')" data-dismiss="fileinput"> </a>
                                         </div>
                                         <div ng-hide="contractInput"   ng-if="contract.signContract!=null&&contract.signContract!=''"  class="fileinput fileinput-exists" data-provides="fileinput">
                                             <span class="btn blue btn-outline btn-file">
                                                 <span class="fileinput-new">上传附件</span>
                                                 <span class="fileinput-exists">更改</span>
                                                 <input type="file" name="signContract" nv-file-select uploader="uploader" onchange="angular.element(this).scope().up(this.files[0])" ng-model="contract.signContract" ng-click="uploadFile('signContract')" > </span>
                                                 <div class="form-control-focus"> </div>
                                      				<span class="help-block">请上传纸质合同</span>
                                             <span class="fileinput-filename">{{contract.signContract.substring(contract.signContract.indexOf("_")+1)}}</span> &nbsp;
                                             <a href="javascript：;" class="close fileinput-exists"  ng-click="removefile('signContract')" data-dismiss="fileinput"> </a>
                                         </div>
                                       	<p class="form-control-static"  ng-show="contractShow" ng-if="contract.signContract==null||contract.signContract==''" class="c_edit" >未上传附件</p>
                                       	<p class="form-control-static"  ng-show="contractShow" ng-if="contract.signContract!=null&&contract.signContract!=''" class="c_edit" ><a href="javascript：;" ng-click="downloadFile(contract.signContract)">{{contract.signContract.substring(contract.signContract.indexOf("_")+1)}}</a></p>

                                  </div>
                                  
                              </div>
                          </div>
                          <!--/span-->
                          <div class="col-md-4">
                              <div class="form-group form-md-line-input">
                                 <label class="control-label col-md-3">供应商合同号：</label>
                                  <div class="col-md-9">
                                  <input type="text" name="contractNumber" class="form-control" ng-hide="contractInput" ng-model="contract.contractNumber"  >
                                      <div class="form-control-focus"> </div>
                                      <span class="help-block">请输入合同号</span>
                                      <p class="form-control-static" ng-show="contractShow"> {{contract.contractNumber}} </p>
                                  </div>
                              </div>
                          </div>
                          <!--/span-->
                      </div>
                      <!--/row-->
                  </div>
				</form>
             </div>   
             <!--合同信息end-->
             <!-- 框架条款start-->
          <div class="portlet-title" ng-show="ClauseFrameworkShow">
               <div class="caption">框架条款</div>
               <div class="tools">
               	 	<button type="submit" ng-click="saveClauseFramework()" ng-hide="ClauseFrameworkInfoInput" class="btn blue  btn-outline  btn-sm">
                  		<i class="fa fa-save"></i> 保存 </button>
                  <button ng-click="cancelClauseFramework()" type="button" ng-hide="ClauseFrameworkInfoInput" class="btn red  btn-outline  btn-sm">
                  		<i class="fa fa-undo"></i> 取消 </button>
                  <button ng-click="editClauseFramework()" type="button" ng-show="ClauseFrameworkInfoShow&&noShow" class="btn blue  btn-outline  btn-sm">
                  		<i class="fa fa-edit"></i> 编辑 </button>
                </div>
            </div>
           <div class="portlet-body form"  ng-show="ClauseFrameworkShow">
			     <form id="form_sample_framework"   class="form-horizontal">
			         <div class="table-scrollable">
                          <table class="table table-bordered table-hover">
                              <thead>
                                  <tr>
                                      <th>选择条款</th>
                                      <th>条款内容</th>
                                      <th>是否继承</th>
                                      <th>是否审批</th>
                                      <th>备注</th>
                                      <th style="width:100px;"></th>
                                  </tr>
                              </thead>
                              <tbody>
                                  <tr ng-repeat="_ClauseFramework in ClauseFramework track by $index" ng-mouseover="showOperation('ClauseFramework',$index)" ng-mouseleave="hideOperation('ClauseFramework',$index)">
                                      <td>
                                      		<input type="text" id="clauseType[$index]" name="clauseType" class="form-control" ng-hide="ClauseFrameworkInfoInput" ng-model="ClauseFramework[$index].clauseType"  >
			                                <!-- <div class="form-control-focus"> </div> -->
			                               <!--  <span class="help-block">请输入选择条款</span> -->
			                                <p class="form-control-static" ng-show="ClauseFrameworkInfoShow"> {{_ClauseFramework.clauseType}} </p>
			                          </td>
			                          <td>
			                                <input type="text" id="clauseContent[$index]" name="clauseContent" class="form-control" ng-hide="ClauseFrameworkInfoInput" ng-model="ClauseFramework[$index].clauseContent"  >
			                                <!-- <div class="form-control-focus"> </div> -->
			                                <!-- <span class="help-block">请输入条款内容</span> -->
			                                <p class="form-control-static" ng-show="ClauseFrameworkInfoShow"> {{_ClauseFramework.clauseContent}} </p>
			                          </td>
                                      <td><input type="text" id="isExtends[$index]" name="isExtends" class="form-control" ng-hide="ClauseFrameworkInfoInput" ng-model="ClauseFramework[$index].isExtends"  >
			                                <!-- <div class="form-control-focus"> </div> -->
			                                <p class="form-control-static" ng-show="ClauseFrameworkInfoShow"> {{_ClauseFramework.isExtends}} </p>
			                           </td>
                                      <td>
											<input type="text" id="isApproval[$index]" name="isApproval" class="form-control" ng-hide="ClauseFrameworkInfoInput" ng-model="ClauseFramework[$index].isApproval"  >
			                                <!-- <div class="form-control-focus"> </div> -->
			                                <p class="form-control-static" ng-show="ClauseFrameworkInfoShow"> {{_ClauseFramework.isApproval}} </p>
										</td>
                                      <td>
                                      		<input type="text" id="remark[$index]" name="remark" class="form-control" ng-hide="ClauseFrameworkInfoInput" ng-model="ClauseFramework[$index].remark"  >
			                                <!-- <div class="form-control-focus"> </div> -->
			                                <!-- <span class="help-block">请输入备注</span> -->
			                                <p class="form-control-static" ng-show="ClauseFrameworkInfoShow"> {{_ClauseFramework.remark}} </p>
			                          </td>
                                      <td ng-show="operation_b{{$index}}">
                                      	<a href="javascript:;"  class="btn red btn-sm" ng-hide="ClauseFrameworkInfoInput" ng-click="deleteClauseFramework($index)">
                                    			<i class="fa fa-close"></i> 
                             				</a>
                                      </td>
                                  </tr>
                              </tbody>
                          </table>
                      </div>
                      <div class="form-actions right">
							<a  class="btn blue btn-sm" ng-hide="ClauseFrameworkInfoInput" ng-click="addClauseFramework()"   >
	                              <i class="fa fa-plus"></i> 增加
	                       	</a> 
                  		</div>
                  </form>
          </div>
          <!-- 框架条款 end-->
             <!-- 订单物料 start-->
          <div class="portlet-title">
               <div class="caption">物料信息</div>
               <div class="tools">
                  <button ng-click="addOrderMateriel()" type="button"  ng-show="noShow" class="btn blue  btn-outline  btn-sm">
                  		<i class="fa fa-edit"></i> 添加物料 </button>
                </div>
            </div>
           <div class="portlet-body form">
			     <form id="form_sample_5"   class="form-horizontal">
			         <div class="table-scrollable">
                          <table class="table table-bordered table-hover">
                              <thead>
                                  <tr>
									<th>物料编号</th>
									<th>物料名称</th>
									<th>规格型号</th>
									<th>单位</th>
									<th>库存数量</th>
									<th>采购数量</th>
									<th>单价</th>
									<th>币种</th>
									<th>税率</th>
									<th>采购单价</th>
									<th>含税采购单价</th>
									<th>金额</th>
									<th>税额</th>
									
									<th ng-if="buyOrder.settlementClause =='服务费'">服务费率</th>
									<th ng-if="buyOrder.settlementClause =='服务费'">服务费</th>
									<th ng-if="buyOrder.settlementClause =='折扣折让'">折扣率</th>
									<th ng-if="buyOrder.settlementClause =='折扣折让'">折后金额</th>
									<th ng-if="buyOrder.settlementClause =='红票'">红票金额</th>
									
									<th>价税合计</th>
									<th>交付日期</th>
									<th>最晚交付日期</th>
									<th>交付/提货地点</th>
									<th style="width：100px;">操作</th>
                                  </tr>
                              </thead>
                              <tbody>
                                  <tr ng-repeat="_orderMateriel in orderMateriel track by $index" ng-mouseover="showOperation('orderMateriel',$index)" ng-mouseleave="hideOperation('orderMateriel',$index)"  repeat-done="repeatDone(this)">
			                          <td>
                                            <!-- <span ng-hide="orderMaterielInput{{$index}}"><a href="javascript：;" ng-click="addMateriel('single',$index)">{{_orderMateriel.materiel.materielNum}}</a></span> -->
			                                <p class="form-control-static" > {{_orderMateriel.materiel.materielNum}} </p>
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
			                          		<input type="text"  name="amount{{$index}}" class="form-control" ng-hide="orderMaterielInput{{$index}}" ng-model="orderMateriel[$index].amount"  >
                                      		<p class="form-control-static" ng-show="orderMaterielShow{{$index}}"> {{_orderMateriel.amount}} </p>
			                          </td>
			                          <td>  
                                      		<p class="form-control-static" > {{_orderMateriel.materiel.unitPrice}} </p>
			                          </td>
			                          <td>  
                                      		<p class="form-control-static" > {{buyOrder.currency}} </p>
			                          </td>
			                          <td>  
                                      		<p class="form-control-static" > {{buyOrder.rate}}% </p>
			                          </td>
			                          <td>  
			                          		<input type="text"  name="orderUnitPrice{{$index}}" class="form-control" ng-hide="orderMaterielInput{{$index}}" ng-model="orderMateriel[$index].orderUnitPrice"  >
                                      		<p class="form-control-static" ng-show="orderMaterielShow{{$index}}"> {{_orderMateriel.orderUnitPrice}} </p>
			                          </td>
			                          <td>  
                                      		<p class="form-control-static"> {{_orderMateriel.orderUnitPrice?_orderMateriel.orderUnitPrice*buyOrder.rate/100:0}} </p>
			                          </td>
			                          <td>  
                                      		<p class="form-control-static" > {{_orderMateriel.orderUnitPrice&&_orderMateriel.amount?_orderMateriel.orderUnitPrice*_orderMateriel.amount:0}} </p>
			                          </td>
			                          <td>  
                                      		<p class="form-control-static"> {{_orderMateriel.orderUnitPrice&&_orderMateriel.amount?_orderMateriel.orderUnitPrice*_orderMateriel.amount*buyOrder.rate/100:0}} </p>
			                          </td>
			                          
			                          <td ng-if="buyOrder.settlementClause =='服务费'">
			                          	<input type="text"  name="serviceRate{{$index}}" class="form-control" ng-hide="orderMaterielInput{{$index}}" ng-model="orderMateriel[$index].serviceRate"  >
                                      	<p class="form-control-static" ng-show="orderMaterielShow{{$index}}"> {{_orderMateriel.serviceRate}} </p>
			                          </td>
									<td ng-if="buyOrder.settlementClause =='服务费'">服务费</td>
									<td ng-if="buyOrder.settlementClause =='折扣折让'">
										<input type="text"  name="discountRate{{$index}}" class="form-control" ng-hide="orderMaterielInput{{$index}}" ng-model="orderMateriel[$index].discountRate"  >
                                      	<p class="form-control-static" ng-show="orderMaterielShow{{$index}}"> {{_orderMateriel.discountRate}} </p>
									</td>
									<td ng-if="buyOrder.settlementClause =='折扣折让'">折后金额</td>
									<td ng-if="buyOrder.settlementClause =='红票'">
										<input type="text"  name="redTicket{{$index}}" class="form-control" ng-hide="orderMaterielInput{{$index}}" ng-model="orderMateriel[$index].redTicket"  >
                                      	<p class="form-control-static" ng-show="orderMaterielShow{{$index}}"> {{_orderMateriel.redTicket}} </p>
									</td>
									
			                          <td>  
                                      		<p class="form-control-static"> {{_orderMateriel.orderUnitPrice&&_orderMateriel.amount?(_orderMateriel.orderUnitPrice*_orderMateriel.amount+_orderMateriel.orderUnitPrice*100*_orderMateriel.amount*buyOrder.rate/10000):0}} </p>
			                          </td>
			                          <td>  
			                          		<input type="text"  name="deliveryDate{{$index}}" class="form-control form-control-inline input-medium date-picker" 
                                      data-date-format="yyyy-mm-dd" data-date-viewmode="years" size="16" ng-hide="orderMaterielInput{{$index}}" ng-model="orderMateriel[$index].deliveryDate"  >
                                      		<p class="form-control-static" ng-show="orderMaterielShow{{$index}}"> {{_orderMateriel.deliveryDate}} </p>
			                          </td>
			                          <td>  
			                          		<input type="text"  name="lastDeliveryDate{{$index}}" class="form-control form-control-inline input-medium date-picker" 
                                      data-date-format="yyyy-mm-dd" data-date-viewmode="years" size="16" ng-hide="orderMaterielInput{{$index}}" ng-model="orderMateriel[$index].lastDeliveryDate"  >
                                      		<p class="form-control-static" ng-show="orderMaterielShow{{$index}}"> {{_orderMateriel.lastDeliveryDate}} </p>
			                          </td>
			                          <td>  
			                          		<input type="text"  name="deliveryAddress{{$index}}" class="form-control" ng-hide="orderMaterielInput{{$index}}" ng-model="orderMateriel[$index].deliveryAddress"  >
                                      		<p class="form-control-static" ng-show="orderMaterielShow{{$index}}"> {{_orderMateriel.deliveryAddress}} </p>
			                          </td>
                                      
                                      <td>
                                      	<span ng-hide="orderMaterielInput{{$index}}">
                                       		&nbsp;&nbsp;&nbsp;&nbsp;
                                        	<a  ng-click="saveOrderMateriel(_orderMateriel,$index)"><i class="fa fa-save"></i></a>
                                        	&nbsp;&nbsp;&nbsp;
                                        	<a  ng-click="cancelOrderMateriel(_orderMateriel,$index)"><i class="fa fa-undo"></i></a>
                                        </span>
                                        <span  ng-show="operation_o{{$index}}&&noShow">
                                        	&nbsp;&nbsp;&nbsp;&nbsp;
                                        	<a ng-show="orderMaterielShow{{$index}}"   ng-click="editOrderMateriel(_orderMateriel)"><i class="fa fa-edit"></i></a>
                                        	&nbsp;&nbsp;&nbsp;
                                        	<a ng-show="orderMaterielShow{{$index}}"  ng-click="deleteOrderMateriel(_orderMateriel)"><i class="fa fa-minus"></i></a>
                                       	</span>
                                      </td>
                                  </tr>
                              </tbody>
                          </table>
                      </div>
                  </form>
          </div>
          <!-- 订单物料 end-->
          <!--结算条款start-->
             <div class="portlet-title">
                            <div class="caption">结算条款</div>
                            <div class="tools" id="noprintdiv">
                            	<button type="submit" ng-click="saveClauseSettlement()" ng-hide="clauseSettlementInput" class="btn blue  btn-outline  btn-sm">
                               		<i class="fa fa-save"></i> 保存 </button>
                               <button ng-click="cancelClauseSettlement()" type="button" ng-hide="clauseSettlementInput" class="btn red  btn-outline  btn-sm">
                               		<i class="fa fa-undo"></i> 取消 </button>
                               <button ng-click="editClauseSettlement()" type="button" ng-show="clauseSettlementShow&&noShow" class="btn blue  btn-outline  btn-sm">
                               		<i class="fa fa-edit"></i> 编辑 </button>
                             </div>
                        </div>
          <div class="portlet-body form">
              <!-- BEGIN FORM-->
              <form action="#" id="form_clauseSettlement"  class="form-horizontal">
                  <div class="form-body">
                      <div class="alert alert-danger display-hide">
                          <button class="close" data-close="alert"></button> 请先输入正确数据！ </div>
                      <div class="row">
                          <div class="col-md-4">
                              <div class="form-group form-md-line-input">
                                  <label class="control-label col-md-3">付款方：</label>
                                  <div class="col-md-9">
                                  <input type="text" name="payer" class="form-control" ng-hide="clauseSettlementInput" ng-model="clauseSettlement.payer"  >
                                      <div class="form-control-focus"> </div>
                                      <span class="help-block">请输入付款方</span>
                                      <p class="form-control-static" ng-show="clauseSettlementShow"> {{clauseSettlement.payer}} </p>
                                  </div>
                                  
                              </div>
                          </div>
                          <!--/span-->
                          <div class="col-md-4">
                          		<div class="form-group form-md-line-input">
	                              	<label class="control-label col-md-3">收款方：</label>
	                                <div class="col-md-9">
	                                  <input type="text" name="payee" class="form-control" ng-hide="clauseSettlementInput" ng-model="clauseSettlement.payee"  >
                                      <div class="form-control-focus"> </div>
                                      <span class="help-block">请输入收款方</span>
                                      <p class="form-control-static" ng-show="clauseSettlementShow"> {{clauseSettlement.payee}} </p>
	                               	</div>
                               </div>
                          </div>
                          <!--/span-->
                      </div>
                      <!--/row-->
                      <div class="row">
                          <div class="col-md-4">
                              <div class="form-group form-md-line-input">
                                  <label class="control-label col-md-3">订单金额：</label>
                                  <div class="col-md-9">
                                  		<input type="text" name="orderAmount" class="form-control" ng-hide="clauseSettlementInput" ng-model="clauseSettlement.orderAmount"  >
                                      <div class="form-control-focus"> </div>
                                      <span class="help-block">请输入订单金额</span>
                                      <p class="form-control-static" ng-show="clauseSettlementShow"> {{clauseSettlement.orderAmount}} </p>
                               		</div>
                              </div>
                          </div>
                          <!--/span-->
                          <div class="col-md-4">
                              <div class="form-group form-md-line-input">
                                  <label class="control-label col-md-3">其他费用：</label>
                                  <div class="col-md-9">
                                  		<input type="text" name="otherAmount" class="form-control" ng-hide="clauseSettlementInput" ng-model="clauseSettlement.otherAmount"  >
                                      <div class="form-control-focus"> </div>
                                      <span class="help-block">请输入其他费用</span>
                                      <p class="form-control-static" ng-show="clauseSettlementShow"> {{clauseSettlement.otherAmount}} </p>
                               		</div>
                              </div>
                          </div>
                          <!--/span-->
                          <div class="col-md-4">
                              <div class="form-group form-md-line-input">
                                  <label class="control-label col-md-3">应付金额：</label>
                                  <div class="col-md-9">
                                  		<input type="text" name="totalAmount" class="form-control" ng-hide="clauseSettlementInput" ng-model="clauseSettlement.totalAmount"  >
                                      <div class="form-control-focus"> </div>
                                      <span class="help-block">请输入应付金额</span>
                                      <p class="form-control-static" ng-show="clauseSettlementShow"> {{clauseSettlement.totalAmount}} </p>
                               		</div>
                              </div>
                          </div>
                          <!--/span-->
                      </div>
                      <!--/row-->
                  </div>
				</form>
				<form id="form_sample_3"   class="form-horizontal">
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
                                      <th style="width:100px;"></th>
                                  </tr>
                              </thead>
                              <tbody>
                                  <tr ng-repeat="_CSD in clauseSettlement.CSD track by $index" ng-mouseover="showOperation('csd',$index)" ng-mouseleave="hideOperation('csd',$index)">
                                      <td>
                                      		<input type="text" id="paymentType[$index]" name="paymentType" class="form-control" ng-hide="clauseSettlementInput" ng-model="clauseSettlement.CSD[$index].paymentType"  >
			                                <p class="form-control-static" ng-show="clauseSettlementShow"> {{_CSD.paymentType}} </p>
			                          </td>
			                          <td>
                                      		<input type="text" id="deliveryNode[$index]" name="deliveryNode" class="form-control" ng-hide="clauseSettlementInput" ng-model="clauseSettlement.CSD[$index].deliveryNode"  >
			                                <p class="form-control-static" ng-show="clauseSettlementShow"> {{_CSD.deliveryNode}} </p>
			                          </td>
                                      <td>
                                      		<input type="text" id="accountPeriod[$index]" name="accountPeriod" class="form-control" ng-hide="clauseSettlementInput" ng-model="clauseSettlement.CSD[$index].accountPeriod"  >
			                                <p class="form-control-static" ng-show="clauseSettlementShow"> {{_CSD.accountPeriod}} </p>
			                          </td>
			                          <td>
                                      		<input type="text" id="deliveryRate[$index]" name="deliveryRate" class="form-control" ng-hide="clauseSettlementInput" ng-model="clauseSettlement.CSD[$index].deliveryRate"  >
			                                <p class="form-control-static" ng-show="clauseSettlementShow"> {{_CSD.deliveryRate}} </p>
			                          </td>
			                          <td>
                                      		<input type="text" id="deliveryAmount[$index]" name="deliveryAmount" class="form-control" ng-hide="clauseSettlementInput" ng-model="clauseSettlement.CSD[$index].deliveryAmount"  >
			                                <p class="form-control-static" ng-show="clauseSettlementShow"> {{_CSD.deliveryAmount}} </p>
			                          </td>
			                          <td>
                                      		<input type="text" id="paymentMethod[$index]" name="paymentMethod" class="form-control" ng-hide="clauseSettlementInput" ng-model="clauseSettlement.CSD[$index].paymentMethod"  >
			                                <p class="form-control-static" ng-show="clauseSettlementShow"> {{_CSD.paymentMethod}} </p>
			                          </td>
			                          <td>
                                      		<input type="text" id="billingMethod[$index]" name="billingMethod" class="form-control" ng-hide="clauseSettlementInput" ng-model="clauseSettlement.CSD[$index].billingMethod"  >
			                                <p class="form-control-static" ng-show="clauseSettlementShow"> {{_CSD.billingMethod}} </p>
			                          </td>
			                          <td>
                                      		<input type="text" id="billingAmount[$index]" name="billingAmount" class="form-control" ng-hide="clauseSettlementInput" ng-model="clauseSettlement.CSD[$index].billingAmount"  >
			                                <p class="form-control-static" ng-show="clauseSettlementShow"> {{_CSD.billingAmount}} </p>
			                          </td>
			                          <td>
                                      		<input type="text" id="unbilledAmount[$index]" name="unbilledAmount" class="form-control" ng-hide="clauseSettlementInput" ng-model="clauseSettlement.CSD[$index].unbilledAmount"  >
			                                <p class="form-control-static" ng-show="clauseSettlementShow"> {{_CSD.unbilledAmount}} </p>
			                          </td>
			                          <td>
                                      		<input type="text" id="remark[$index]" name="remark" class="form-control" ng-hide="clauseSettlementInput" ng-model="clauseSettlement.CSD[$index].remark"  >
			                                <p class="form-control-static" ng-show="clauseSettlementShow"> {{_CSD.remark}} </p>
			                          </td>
                                      <td ng-show="operation_csd{{$index}}">
                                      	<a href="javascript:;"  class="btn red btn-sm" ng-hide="clauseSettlementInput" ng-click="deleteCSD($index)">
                                    			<i class="fa fa-close"></i> 
                             				</a>
                                      </td>
                                  </tr>
                              </tbody>
                          </table>
                      </div>
                      <div class="form-actions right">
							<a  class="btn blue btn-sm" ng-hide="clauseSettlementInput" ng-click="addCSD()"   >
	                              <i class="fa fa-plus"></i> 增加
	                       	</a> 
                  		</div>
                  </form>
             </div>   
          <!--结算条款end-->
          <!--垫资条款start-->
             <div class="portlet-title">
                            <div class="caption">垫资条款</div>
                            <div class="tools" id="noprintdiv">
                            	<button type="submit" ng-click="saveClauseAdvance()" ng-hide="clauseAdvanceInput" class="btn blue  btn-outline  btn-sm">
                               		<i class="fa fa-save"></i> 保存 </button>
                               <button ng-click="cancelClauseAdvance()" type="button" ng-hide="clauseAdvanceInput" class="btn red  btn-outline  btn-sm">
                               		<i class="fa fa-undo"></i> 取消 </button>
                               <button ng-click="editClauseAdvance()" type="button" ng-show="clauseAdvanceShow&&noShow" class="btn blue  btn-outline  btn-sm">
                               		<i class="fa fa-edit"></i> 编辑 </button>
                             </div>
                        </div>
          <div class="portlet-body form">
              <!-- BEGIN FORM-->
              <form action="#" id="form_clauseAdvance"  class="form-horizontal">
                  <div class="form-body">
                      <div class="alert alert-danger display-hide">
                          <button class="close" data-close="alert"></button> 请先输入正确数据！ </div>
                      <div class="row">
                          <div class="col-md-4">
                              <div class="form-group form-md-line-input">
                                  <label class="control-label col-md-3">融资方：</label>
                                  <div class="col-md-9">
                                  <input type="text" name="financing" class="form-control" ng-hide="clauseAdvanceInput" ng-model="clauseAdvance.financing"  >
                                      <div class="form-control-focus"> </div>
                                      <span class="help-block">请输入融资方</span>
                                      <p class="form-control-static" ng-show="clauseAdvanceShow"> {{clauseAdvance.financing}} </p>
                                  </div>
                                  
                              </div>
                          </div>
                          <!--/span-->
                          <div class="col-md-4">
                          		<div class="form-group form-md-line-input">
	                              	<label class="control-label col-md-3">垫资方：</label>
	                                <div class="col-md-9">
	                                  <input type="text" name="advance" class="form-control" ng-hide="clauseAdvanceInput" ng-model="clauseAdvance.advance"  >
                                      <div class="form-control-focus"> </div>
                                      <span class="help-block">请输入垫资方</span>
                                      <p class="form-control-static" ng-show="clauseAdvanceShow"> {{clauseAdvance.advance}} </p>
	                               	</div>
                               </div>
                          </div>
                          <!--/span-->
                      </div>
                      <!--/row-->
                      <div class="row">
                          <div class="col-md-4">
                              <div class="form-group form-md-line-input">
                                  <label class="control-label col-md-3">垫资金额：</label>
                                  <div class="col-md-9">
                                  		<input type="text" name="advanceAmount" class="form-control" ng-hide="clauseAdvanceInput" ng-model="clauseAdvance.advanceAmount"  >
                                      <div class="form-control-focus"> </div>
                                      <span class="help-block">请输入垫资金额</span>
                                      <p class="form-control-static" ng-show="clauseAdvanceShow"> {{clauseAdvance.advanceAmount}} </p>
                               		</div>
                              </div>
                          </div>
                          <!--/span-->
                          <div class="col-md-4">
                              <div class="form-group form-md-line-input">
                                  <label class="control-label col-md-3">保证金比率：</label>
                                  <div class="col-md-9">
                                  		<input type="text" name="depositRate" class="form-control" ng-hide="clauseAdvanceInput" ng-model="clauseAdvance.depositRate"  >
                                      <div class="form-control-focus"> </div>
                                      <span class="help-block">请输入保证金比率</span>
                                      <p class="form-control-static" ng-show="clauseAdvanceShow"> {{clauseAdvance.depositRate}} </p>
                               		</div>
                              </div>
                          </div>
                          <!--/span-->
                          <div class="col-md-4">
                              <div class="form-group form-md-line-input">
                                  <label class="control-label col-md-3">保证金金额：</label>
                                  <div class="col-md-9">
                                  		<input type="text" name="depositAmount" class="form-control" ng-hide="clauseAdvanceInput" ng-model="clauseAdvance.depositAmount"  >
                                      <div class="form-control-focus"> </div>
                                      <span class="help-block">请输入保证金金额</span>
                                      <p class="form-control-static" ng-show="clauseAdvanceShow"> {{clauseAdvance.depositAmount}} </p>
                               		</div>
                              </div>
                          </div>
                          <!--/span-->
                      </div>
                      <!--/row-->
                      <div class="row">
                          <div class="col-md-4">
                              <div class="form-group form-md-line-input">
                                  <label class="control-label col-md-3">服务费率：</label>
                                  <div class="col-md-9">
                                  		<input type="text" name="serviceAmountRate" class="form-control" ng-hide="clauseAdvanceInput" ng-model="clauseAdvance.serviceAmountRate"  >
                                      <div class="form-control-focus"> </div>
                                      <span class="help-block">请输入服务费率</span>
                                      <p class="form-control-static" ng-show="clauseAdvanceShow"> {{clauseAdvance.serviceAmountRate}} </p>
                               		</div>
                              </div>
                          </div>
                          <!--/span-->
                          <div class="col-md-4">
                              <div class="form-group form-md-line-input">
                                  <label class="control-label col-md-3">垫资时间：</label>
                                  <div class="col-md-9">
                                  		<input type="text" name="advanceTime" class="form-control form-control-inline input-medium date-picker" 
                                      data-date-format="yyyy-mm-dd" data-date-viewmode="years" size="16" ng-hide="clauseAdvanceInput" ng-model="clauseAdvance.advanceTime"  >
                                      <div class="form-control-focus"> </div>
                                      <span class="help-block">请选择垫资时间</span>
                                      <p class="form-control-static" ng-show="clauseAdvanceShow"> {{clauseAdvance.advanceTime}} </p>
                               		</div>
                              </div>
                          </div>
                          <!--/span-->
                          <div class="col-md-4">
                              <div class="form-group form-md-line-input">
                                  <label class="control-label col-md-3">服务费：</label>
                                  <div class="col-md-9">
                                  		<input type="text" name="serviceAmount" class="form-control" ng-hide="clauseAdvanceInput" ng-model="clauseAdvance.serviceAmount"  >
                                      <div class="form-control-focus"> </div>
                                      <span class="help-block">请输入服务费</span>
                                      <p class="form-control-static" ng-show="clauseAdvanceShow"> {{clauseAdvance.serviceAmount}} </p>
                               		</div>
                              </div>
                          </div>
                          <!--/span-->
                      </div>
                      <!--/row-->
                  </div>
				</form>
             </div>   
             <!--垫资条款end-->
             <!--交付条款start-->
             <div class="portlet-title">
                            <div class="caption">交付条款</div>
                            <div class="tools" id="noprintdiv">
                            	<button type="submit" ng-click="saveClauseDelivery()" ng-hide="clauseDeliveryInput" class="btn blue  btn-outline  btn-sm">
                               		<i class="fa fa-save"></i> 保存 </button>
                               <button ng-click="cancelClauseDelivery()" type="button" ng-hide="clauseDeliveryInput" class="btn red  btn-outline  btn-sm">
                               		<i class="fa fa-undo"></i> 取消 </button>
                               <button ng-click="editClauseDelivery()" type="button" ng-show="clauseDeliveryShow&&noShow" class="btn blue  btn-outline  btn-sm">
                               		<i class="fa fa-edit"></i> 编辑 </button>
                             </div>
                        </div>
          <div class="portlet-body form">
              <!-- BEGIN FORM-->
              <form action="#" id="form_clauseDelivery"  class="form-horizontal">
                  <div class="form-body">
                      <div class="alert alert-danger display-hide">
                          <button class="close" data-close="alert"></button> 请先输入正确数据！ </div>
                      <!--/row-->
                      <div class="row">
                          <div class="col-md-4">
                              <div class="form-group form-md-line-input">
                                  <label class="control-label col-md-3">运输方式：</label>
                                  <div class="col-md-9">
                                  		<input type="text" name="transportType" class="form-control" ng-hide="clauseDeliveryInput" ng-model="clauseDelivery.transportType"  >
                                      <div class="form-control-focus"> </div>
                                      <span class="help-block">请输入运输方式</span>
                                      <p class="form-control-static" ng-show="clauseDeliveryShow"> {{clauseDelivery.transportType}} </p>
                               		</div>
                              </div>
                          </div>
                          <!--/span-->
                          <div class="col-md-4">
                              <div class="form-group form-md-line-input">
                                  <label class="control-label col-md-3">包装要求：</label>
                                  <div class="col-md-9">
                                  		<input type="text" name="packingRequire" class="form-control" ng-hide="clauseDeliveryInput" ng-model="clauseDelivery.packingRequire"  >
                                      <div class="form-control-focus"> </div>
                                      <span class="help-block">请输入包装要求</span>
                                      <p class="form-control-static" ng-show="clauseDeliveryShow"> {{clauseDelivery.packingRequire}} </p>
                               		</div>
                              </div>
                          </div>
                          <!--/span-->
                          <div class="col-md-4">
                              <div class="form-group form-md-line-input">
                                  <label class="control-label col-md-3">规格型号：</label>
                                  <div class="col-md-9">
                                  		<input type="text" name="specifications" class="form-control" ng-hide="clauseDeliveryInput" ng-model="clauseDelivery.specifications"  >
                                      <div class="form-control-focus"> </div>
                                      <span class="help-block">请输入规格型号</span>
                                      <p class="form-control-static" ng-show="clauseDeliveryShow"> {{clauseDelivery.specifications}} </p>
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
             <!--验收条款start-->
             <div class="portlet-title">
                            <div class="caption">验收条款</div>
                            <div class="tools" id="noprintdiv">
                            	<button type="submit" ng-click="saveClauseCheckAccept()" ng-hide="clauseCheckAcceptInput" class="btn blue  btn-outline  btn-sm">
                               		<i class="fa fa-save"></i> 保存 </button>
                               <button ng-click="cancelClauseCheckAccept()" type="button" ng-hide="clauseCheckAcceptInput" class="btn red  btn-outline  btn-sm">
                               		<i class="fa fa-undo"></i> 取消 </button>
                               <button ng-click="editClauseCheckAccept()" type="button" ng-show="clauseCheckAcceptShow&&noShow" class="btn blue  btn-outline  btn-sm">
                               		<i class="fa fa-edit"></i> 编辑 </button>
                             </div>
                        </div>
          <div class="portlet-body form">
              <!-- BEGIN FORM-->
              <form action="#" id="form_clauseCheckAccept"  class="form-horizontal">
                  <div class="form-body">
                      <div class="alert alert-danger display-hide">
                          <button class="close" data-close="alert"></button> 请先输入正确数据！ </div>
                      <!--/row-->
                      <div class="row">
                          <div class="col-md-4">
                              <div class="form-group form-md-line-input">
                                  <label class="control-label col-md-3">验收方：</label>
                                  <div class="col-md-9">
                                  		<input type="text" name="checkParty" class="form-control" ng-hide="clauseCheckAcceptInput" ng-model="clauseCheckAccept.checkParty"  >
                                      <div class="form-control-focus"> </div>
                                      <span class="help-block">请输入验收方</span>
                                      <p class="form-control-static" ng-show="clauseCheckAcceptShow"> {{clauseCheckAccept.checkParty}} </p>
                               		</div>
                              </div>
                          </div>
                          <!--/span-->
                          <div class="col-md-4">
                              <div class="form-group form-md-line-input">
                                  <label class="control-label col-md-3">验收标准：</label>
                                  <div class="col-md-9">
                                  		<input type="text" name="acceptStandard" class="form-control" ng-hide="clauseCheckAcceptInput" ng-model="clauseCheckAccept.acceptStandard"  >
                                      <div class="form-control-focus"> </div>
                                      <span class="help-block">请输入验收标准</span>
                                      <p class="form-control-static" ng-show="clauseCheckAcceptShow"> {{clauseCheckAccept.acceptStandard}} </p>
                               		</div>
                              </div>
                          </div>
                          <!--/span-->
                          <div class="col-md-4">
                              <div class="form-group form-md-line-input">
                                  <label class="control-label col-md-3">规格型号：</label>
                                  <div class="col-md-9">
                                  		<input type="text" name="specifications" class="form-control" ng-hide="clauseCheckAcceptInput" ng-model="clauseCheckAccept.specifications"  >
                                      <div class="form-control-focus"> </div>
                                      <span class="help-block">请输入规格型号</span>
                                      <p class="form-control-static" ng-show="clauseCheckAcceptShow"> {{clauseCheckAccept.specifications}} </p>
                               		</div>
                              </div>
                          </div>
                          <!--/span-->
                      </div>
                      <!--/row-->
                  </div>
				</form>
             </div>   
             <!--验收条款end-->
             <!--售后条款start-->
             <div class="portlet-title">
                            <div class="caption">售后条款</div>
                            <div class="tools" id="noprintdiv">
                            	<button type="submit" ng-click="saveClauseAfterSales()" ng-hide="clauseAfterSalesInput" class="btn blue  btn-outline  btn-sm">
                               		<i class="fa fa-save"></i> 保存 </button>
                               <button ng-click="cancelClauseAfterSales()" type="button" ng-hide="clauseAfterSalesInput" class="btn red  btn-outline  btn-sm">
                               		<i class="fa fa-undo"></i> 取消 </button>
                               <button ng-click="editClauseAfterSales()" type="button" ng-show="clauseAfterSalesShow&&noShow" class="btn blue  btn-outline  btn-sm">
                               		<i class="fa fa-edit"></i> 编辑 </button>
                             </div>
                        </div>
          <div class="portlet-body form">
              <!-- BEGIN FORM-->
              <form action="#" id="form_clauseAfterSales"  class="form-horizontal">
                  <div class="form-body">
                      <div class="alert alert-danger display-hide">
                          <button class="close" data-close="alert"></button> 请先输入正确数据！ </div>
                      <!--/row-->
                      <div class="row">
                          <div class="col-md-4">
                              <div class="form-group form-md-line-input">
                                  <label class="control-label col-md-3">质保要求：</label>
                                  <div class="col-md-9">
                                  		<input type="text" name="qualityRequirements" class="form-control" ng-hide="clauseAfterSalesInput" ng-model="clauseAfterSales.qualityRequirements"  >
                                      <div class="form-control-focus"> </div>
                                      <span class="help-block">请输入质保要求</span>
                                      <p class="form-control-static" ng-show="clauseAfterSalesShow"> {{clauseAfterSales.qualityRequirements}} </p>
                               		</div>
                              </div>
                          </div>
                          <!--/span-->
                          <div class="col-md-4">
                              <div class="form-group form-md-line-input">
                                  <label class="control-label col-md-3">售后响应：</label>
                                  <div class="col-md-9">
                                  		<input type="text" name="afterSaleResponse" class="form-control" ng-hide="clauseAfterSalesInput" ng-model="clauseAfterSales.afterSaleResponse"  >
                                      <div class="form-control-focus"> </div>
                                      <span class="help-block">请输入售后响应</span>
                                      <p class="form-control-static" ng-show="clauseAfterSalesShow"> {{clauseAfterSales.afterSaleResponse}} </p>
                               		</div>
                              </div>
                          </div>
                          <!--/span-->
                          <div class="col-md-4">
                              <div class="form-group form-md-line-input">
                                  <label class="control-label col-md-3">补充说明：</label>
                                  <div class="col-md-9">
                                  		<input type="text" name="clauseAfterRemark" class="form-control" ng-hide="clauseAfterSalesInput" ng-model="clauseAfterSales.remark"  >
                                      <div class="form-control-focus"> </div>
                                      <span class="help-block">请输入售后响应</span>
                                      <p class="form-control-static" ng-show="clauseAfterSalesShow"> {{clauseAfterSales.remark}} </p>
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
             <!-- 附件 start-->
          <div class="portlet-title">
               <div class="caption">附件</div>
               <div class="tools">
               	 	<button type="submit" ng-click="saveFile()" ng-hide="fileInfoInput" class="btn blue  btn-outline  btn-sm">
                  		<i class="fa fa-save"></i> 保存 </button>
                  <button ng-click="cancelFile()" type="button" ng-hide="fileInfoInput" class="btn red  btn-outline  btn-sm">
                  		<i class="fa fa-undo"></i> 取消 </button>
                  <button ng-click="editFile()" type="button" ng-show="fileInfoShow&&noShow" class="btn blue  btn-outline  btn-sm">
                  		<i class="fa fa-edit"></i> 编辑 </button>
                </div>
            </div>
           <div class="portlet-body form">
			     <form id="form_sample_4"   class="form-horizontal">
				     <div class="form-body">
	                      <div class="alert alert-danger display-hide">
	                          <button class="close" data-close="alert"></button> 请先输入正确数据！ </div>
	                      <!--/row-->
	                      <div class="row" ng-repeat="_file in file track by $index" ng-mouseover="showOperation('file',$index)" ng-mouseleave="hideOperation('file',$index)">
	                          <div class="col-md-4">
	                              <div class="form-group form-md-line-input">
	                                  <label class="control-label col-md-3">文件：</label>
	                                  <div class="col-md-9">
	                                  	 <div ng-hide="fileInfoInput"   ng-if="file[$index].file==null||file[$index].file==''"  class="fileinput fileinput-new" data-provides="fileinput">
                                             <span class="btn blue btn-outline btn-file">
                                                 <span class="fileinput-new">上传附件</span>
                                                 <span class="fileinput-exists">更改</span>
                                                 <input type="file" name="..." nv-file-select uploader="uploader" onchange="angular.element(this).scope().up(this.files[0])" ng-model="file[$index].file" ng-click="uploadFile($index)" > </span>
                                             <span class="fileinput-filename">{{_file.file.substring(_file.file.indexOf("_")+1)}}</span> &nbsp;
                                             <a href="javascript：;" class="close fileinput-exists" ng-click="removefile($index)" data-dismiss="fileinput"> </a>
                                         </div>
                                         <div ng-hide="fileInfoInput"   ng-if="file[$index].file!=null&&file[$index].file!=''"  class="fileinput fileinput-exists" data-provides="fileinput">
                                             <span class="btn blue btn-outline btn-file">
                                                 <span class="fileinput-new">上传附件</span>
                                                 <span class="fileinput-exists">更改</span>
                                                 <input type="file" name="..." nv-file-select uploader="uploader" onchange="angular.element(this).scope().up(this.files[0])" ng-model="file[$index].file" ng-click="uploadFile($index)" > </span>
                                             <span class="fileinput-filename">{{_file.file.substring(_file.file.indexOf("_")+1)}}</span> &nbsp;
                                             <a href="javascript：;" class="close fileinput-exists"  ng-click="removefile($index)" data-dismiss="fileinput"> </a>
                                         </div>
                                       	<p class="form-control-static"  ng-show="fileInfoShow" ng-if="file[$index].file==null||file[$index].file==''" class="c_edit" >未上传附件</p>
                                       	<p class="form-control-static"  ng-show="fileInfoShow" ng-if="file[$index].file!=null&&file[$index].file!=''" class="c_edit" ><a href="javascript：;" ng-click="downloadOrderFile(file[$index])">{{_file.file.substring(_file.file.indexOf("_")+1)}}</a></p>
		                              </div>
		                          </div>
		                      </div>
	                          <!--/span-->
	                          <div class="col-md-4">
	                              <div class="form-group form-md-line-input">
	                                  <label class="control-label col-md-3">描述：</label>
	                                  <div class="col-md-9">
	                                      <input type="text" id="fileDescribe[$index]" name="fileDescribe" class="form-control" ng-hide="fileInfoInput" ng-model="file[$index].fileDescribe"  >
			                               <div class="form-control-focus"> </div>
	                                      <span class="help-block">请输入附件描述</span>
			                               <p class="form-control-static" ng-show="fileInfoShow"> {{_file.fileDescribe}} </p>
	                               		</div>
	                              </div>
	                          </div>
	                          <!--/span-->
	                          <div class="col-md-4"ng-show="operation_f{{$index}}">
                                	<a href="javascript：;"  class="btn red btn-sm" ng-hide="fileInfoInput" ng-click="deleteFile($index)">
                              			<i class="fa fa-close"></i> 
                       				</a>
	                          </div>
	                          <!--/span-->
	                      </div>
	                      <!--/row-->
	                  </div>
	                </form>
                      <div class="form-actions right">
							<a  class="btn blue btn-sm"  ng-hide="fileInfoInput" ng-click="addFile()"   >
	                              <i class="fa fa-plus"></i> 增加
	                       	</a> 
                  		</div>
            </div>
          <!-- 附件 end-->
          <!-- 备注 start-->
          <div class="portlet-title">
               <div class="caption">备注</div>
            </div>
           <div class="portlet-body form">
			     <form class="form-horizontal">
				     <div class="form-body">
	                      <div class="row">
	                          <div class="col-md-8">
	                          		<div class="form-group form-md-line-input">
		                              	<label class="control-label col-md-3">备注：</label>
		                                <div class="col-md-9">
		                                  <input type="text" name="remark" ng-hide="orderStatusInput" class="form-control"  ng-model="buyOrder.remark"  >
	                                      <div class="form-control-focus"> </div>
	                                      <span class="help-block">请输入备注</span>
	                                      <p class="form-control-static" ng-show="orderStatusShow"> {{buyOrder.remark}} </p>
		                               	</div>
	                               </div>
	                          </div>
	                      </div>
	                      <!--/row-->
	                  </div>
	                </form>
            </div>
          <!-- 附件 end-->
          <div class="form-actions">
              <div class="row">
                  <div class="col-md-offset-4 col-md-8">
               		<button type="button" ng-click="submitPage()" ng-hide="orderStatusInput" class="btn blue">提交审核</button>
                   	<button type="button" ng-click="cancelPage()" class="btn default">取消</button>
                  </div>
              </div>
          </div>
          </div>
      </div>
</div>
</div>
</div> 

<jsp:include  page="selectBasicMateriel.jsp"/> <!-- 选择基本物料 -->
