<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- BEGIN PAGE HEADER-->

<h3 class="page-title"> 物料信息
</h3>
<div class="page-bar">
    <ul class="page-breadcrumb">
        <li>
            <i class="fa fa-home"></i>
            <a ui-sref="dashboard">首页</a>
            <i class="fa fa-angle-right"></i>
        </li>
        <li>
            <a ui-sref="saleOrder">销售订单</a>
            <i class="fa fa-angle-right"></i>
        </li>
        <li>
            <a>{{opration}}</a>
        </li>
    </ul>
    <div class="page-toolbar">
          <div class="btn-group pull-right">
              <button type="button" class="btn btn-fit-height grey-salt dropdown-toggle" onclick="printdiv('saleOrderPrint')"> 
              	<i class="fa fa-print"></i>
                  		打印
              </button>
              
          </div>
      </div>
</div>
<div class="row" id="saleOrderPrint">
    <div class="col-md-12">
        <!-- BEGIN EXAMPLE TABLE PORTLET-->
        <div class="portlet bordered">
            <div class="portlet-body">
				<div class="portlet light ">
                        <div class="portlet-title">
                            <div class="caption">订单信息</div>
                            <div class="tools" id="noprintdiv">
                            	<button type="submit" ng-click="save()" ng-hide="saleOrderInput" class="btn blue  btn-outline  btn-sm">
                               		<i class="fa fa-save"></i> 保存 </button>
                               <button ng-click="cancel()" type="button" ng-hide="saleOrderInput" class="btn red  btn-outline  btn-sm">
                               		<i class="fa fa-undo"></i> 取消 </button>
                               <button ng-click="edit()" type="button" ng-show="saleOrderShow" class="btn blue  btn-outline  btn-sm">
                               		<i class="fa fa-edit"></i> 编辑 </button>
                                 <!-- <a href="javascript:;" class="collapse"> </a> -->
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
                                  <label class="control-label col-md-3"><span class="required" aria-required="true"> * </span>销售订单号:</label>
                                  <div class="col-md-9">
                                  <input type="text" name="orderNum" class="form-control" ng-hide="saleOrderInput" ng-model="saleOrder.orderNum"  >
                                      <div class="form-control-focus"> </div>
                                      <span class="help-block">请输入销售订单号</span>
                                      <p class="form-control-static" ng-show="saleOrderShow"> {{saleOrder.orderNum}} </p>
                                  </div>
                                  
                              </div>
                          </div>
                          <!--/span-->
                          <div class="col-md-4">
                              <div class="form-group form-md-line-input">
                                  <label class="control-label col-md-3">销售方:</label>
                                  <div class="col-md-9">
                                      <p class="form-control-static"> 中航能科 </p>
                                  </div>
                              </div>
                          </div>
                          <!--/span-->
                          <div class="col-md-4">
                              <div class="form-group form-md-line-input">
                                  <label class="control-label col-md-3"><span class="required" aria-required="true"> * </span>销售类型:</label>
                                  <div class="col-md-9">
                                  		<select class="form-control" id="orderType"  ng-hide="saleOrderInput" name="orderType"  ng-model="saleOrder.orderType" >
                                            <option value=""></option>
                                           	<option value="标准销售" >标准销售</option>
                                             <option value="委托销售" >委托销售</option>
                                             <option value="委托采购" >委托采购</option>
                                        </select>
                                        <div class="form-control-focus"> </div>
                              			<span class="help-block">请选择销售类型</span>
                                      <p class="form-control-static" ng-show="saleOrderShow"> {{saleOrder.orderType}} </p>
                                  </div>
                              </div>
                          </div>
                          <!--/span-->
                      </div>
                      <!--/row-->
                      <div class="row">
                          <div class="col-md-4">
                              <div class="form-group form-md-line-input">
                                  <label class="control-label col-md-3"><span class="required" aria-required="true"> * </span>采购方:</label>
                                  <div class="col-md-9">
                                  <input type="text" name="buyComId" class="form-control" ng-hide="saleOrderInput" ng-model="saleOrder.buyComId"  >
                                      <div class="form-control-focus"> </div>
                                      <span class="help-block">请选择采购商</span>
                                      <p class="form-control-static" ng-show="saleOrderShow"> {{saleOrder.buyComId}} </p>
                                  </div>
                                  
                              </div>
                          </div>
                          <!--/span-->
                          <div class="col-md-4">
                              <div class="form-group form-md-line-input">
                                  <label class="control-label col-md-3">关联需求计划单号:</label>
                                  <div class="col-md-9">
                                  <input type="text"  class="form-control" ng-hide="saleOrderInput"   >
                                      <div class="form-control-focus"> </div>
                                      <span class="help-block">请选择需求计划单号</span>
                                      <p class="form-control-static" ng-show="saleOrderShow"> </p>
                                  </div>
                                  
                              </div>
                          </div>
                          <!--/span-->
                          <div class="col-md-4">
                              <div class="form-group form-md-line-input">
                                  <label class="control-label col-md-3">关联采购单号:</label>
                                  <div class="col-md-9">
                                  <input type="text"  class="form-control" ng-hide="saleOrderInput"   >
                                      <div class="form-control-focus"> </div>
                                      <span class="help-block">请选择采购单号</span>
                                      <p class="form-control-static" ng-show="saleOrderShow">  </p>
                                  </div>
                                  
                              </div>
                          </div>
                          <!--/span-->
                      </div>
                      <!--/row-->
                      <div class="row">
                          <div class="col-md-4">
                              <div class="form-group form-md-line-input">
                                  <label class="control-label col-md-3"><span class="required" aria-required="true"> * </span>服务模式:</label>
                                  <div class="col-md-9">
                                  		<select class="form-control" id="serviceModel"  ng-hide="saleOrderInput" name="serviceModel"  ng-model="saleOrder.serviceModel" >
                                            <option value="无">无</option>
                                           	<option value="仓储服务" >仓储服务</option>
                                             <option value="仓储+垫资服务" >仓储+垫资服务</option>
                                        </select>
                                        <div class="form-control-focus"> </div>
                              			<span class="help-block">请选择服务模式</span>
                                      <p class="form-control-static" ng-show="saleOrderShow"> {{saleOrder.serviceModel}} </p>
                                  </div>
                                  
                              </div>
                          </div>
                          <!--/span-->
                          <div class="col-md-4">
                              <div class="form-group form-md-line-input">
                                  <label class="control-label col-md-3"><span class="required" aria-required="true"> * </span>结算条款:</label>
                                  <div class="col-md-9">
                                  		<select class="form-control" id="settlementClause"  ng-hide="saleOrderInput" name="settlementClause"  ng-model="saleOrder.settlementClause" >
                                            <option value=""></option>
                                           	<option value="进销差" >进销差</option>
                                             <option value="服务费" >服务费</option>
                                             <option value="折扣折让" >折扣折让</option>
                                             <option value="红票" >红票</option>
                                        </select>
                                        <div class="form-control-focus"> </div>
                              			<span class="help-block">请选择结算条款</span>
                                      <p class="form-control-static" ng-show="saleOrderShow"> {{saleOrder.settlementClause}} </p>
                                  </div>
                                  
                              </div>
                          </div>
                          <!--/span-->
                          <div class="col-md-4">
                              <div class="form-group form-md-line-input">
                                  <label class="control-label col-md-3"><span class="required" aria-required="true"> * </span>提货方式:</label>
                                  <div class="col-md-9">
                                  		<select class="form-control" id="deliveryMode"  ng-hide="saleOrderInput" name="deliveryMode"  ng-model="saleOrder.deliveryMode" >
                                            <option value=""></option>
                                           	<option value="仓库自提" >仓库自提</option>
                                             <option value="物料配送" >物料配送</option>
                                        </select>
                                        <div class="form-control-focus"> </div>
                              			<span class="help-block">请选择提货方式</span>
                                      <p class="form-control-static" ng-show="saleOrderShow"> {{saleOrder.deliveryMode}} </p>
                                  </div>
                              </div>
                          </div>
                          <!--/span-->
                      </div>
                      <div class="row">
                          <div class="col-md-4">
                              <div class="form-group form-md-line-input">
                                  <label class="control-label col-md-3">下单日期:</label>
                                  <div class="col-md-9">
                                  <input type="text" name="orderDate" class="form-control form-control-inline input-medium date-picker" 
                                      data-date-format="yyyy-mm-dd" data-date-viewmode="years" size="16" ng-hide="saleOrderInput" ng-model="saleOrder.orderDate"  >
                                      <div class="form-control-focus"> </div>
                                      <span class="help-block">请选择下单日期</span>
                                      <p class="form-control-static" ng-show="saleOrderShow"> {{saleOrder.orderDate}} </p>
                                  </div>
                                  
                              </div>
                          </div>
                          <!--/span-->
                          <div class="col-md-4">
                              <div class="form-group form-md-line-input">
                                  <label class="control-label col-md-3">制单人:</label>
                                  <div class="col-md-9">
                                  <input type="text" name="maker" class="form-control" ng-hide="saleOrderInput" ng-model="saleOrder.maker"  >
                                      <div class="form-control-focus"> </div>
                                      <span class="help-block">请输入制单人</span>
                                      <p class="form-control-static" ng-show="saleOrderShow"> {{saleOrder.maker}} </p>
                                  </div>
                              </div>
                          </div>
                          <!--/span-->
                          <div class="col-md-4">
                              <div class="form-group form-md-line-input">
                                  <label class="control-label col-md-3">审批人:</label>
                                  <div class="col-md-9">
                                  <input type="text"  class="form-control" ng-hide="saleOrderInput"  >
                                      <div class="form-control-focus"> </div>
                                      <span class="help-block">请输入审批人</span>
                                      <p class="form-control-static" ng-show="saleOrderShow"> </p>
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
                               <button ng-click="editContract()" type="button" ng-show="contractShow" class="btn blue  btn-outline  btn-sm">
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
                                  <label class="control-label col-md-3"><span class="required" aria-required="true"> * </span>合同编号:</label>
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
                                  <label class="control-label col-md-3"><span class="required" aria-required="true"> * </span>开始日期:</label>
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
                                  <label class="control-label col-md-3"><span class="required" aria-required="true"> * </span>开始日期:</label>
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
                                  <label class="control-label col-md-3">签订日期:</label>
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
                                  <label class="control-label col-md-3">签约地点:</label>
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
                                  <label class="control-label col-md-3">签订人:</label>
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
                                  <label class="control-label col-md-3"><span class="required" aria-required="true"> * </span>合同附件:</label>
                                  <div class="col-md-9">
                                  		<div ng-hide="contractInput"   ng-if="contract.electronicContract==null||contract.electronicContract==''"  class="fileinput fileinput-new" data-provides="fileinput">
                                             <span class="btn blue btn-outline btn-file">
                                                 <span class="fileinput-new">上传附件</span>
                                                 <span class="fileinput-exists">更改</span>
                                                 <input type="file" name="electronicContract" nv-file-select uploader="uploader" onchange="angular.element(this).scope().up(this.files[0])" ng-model="contract.electronicContract" ng-click="uploadFile('electronicContract')" > </span>
                                                 <div class="form-control-focus"> </div>
                                      			<span class="help-block">请上传合同附件</span>
                                             <span class="fileinput-filename">{{contract.electronicContract.substring(contract.electronicContract.indexOf("_")+1)}}</span> &nbsp;
                                             <a href="javascript:;" class="close fileinput-exists" ng-click="removefile('electronicContract')" data-dismiss="fileinput"> </a>
                                         </div>
                                         <div ng-hide="contractInput"   ng-if="contract.electronicContract!=null&&contract.electronicContract!=''"  class="fileinput fileinput-exists" data-provides="fileinput">
                                             <span class="btn blue btn-outline btn-file">
                                                 <span class="fileinput-new">上传附件</span>
                                                 <span class="fileinput-exists">更改</span>
                                                 <input type="file" name="electronicContract" nv-file-select uploader="uploader" onchange="angular.element(this).scope().up(this.files[0])" ng-model="contract.electronicContract" ng-click="uploadFile('electronicContract')" > </span>
                                                 <div class="form-control-focus"> </div>
                                      				<span class="help-block">请上传合同附件</span>
                                             <span class="fileinput-filename">{{contract.electronicContract.substring(contract.electronicContract.indexOf("_")+1)}}</span> &nbsp;
                                             <a href="javascript:;" class="close fileinput-exists"  ng-click="removefile('electronicContract')" data-dismiss="fileinput"> </a>
                                         </div>
                                       	<p class="form-control-static"  ng-show="contractShow" ng-if="contract.electronicContract==null||contract.electronicContract==''" class="c_edit" >未上传附件</p>
                                       	<p class="form-control-static"  ng-show="contractShow" ng-if="contract.electronicContract!=null&&contract.electronicContract!=''" class="c_edit" ><a href="javascript:;" ng-click="downloadFile(contract.electronicContract)">{{contract.electronicContract.substring(contract.electronicContract.indexOf("_")+1)}}</a></p>
                                  </div>
                              </div>
                          </div>
                          <!--/span-->
                          <div class="col-md-4">
                              <div class="form-group form-md-line-input">
                                  <label class="control-label col-md-3">纸质合同:</label>
                                  <div class="col-md-9">
                                  		<div ng-hide="contractInput"   ng-if="contract.signContract==null||contract.signContract==''"  class="fileinput fileinput-new" data-provides="fileinput">
                                             <span class="btn blue btn-outline btn-file">
                                                 <span class="fileinput-new">上传附件</span>
                                                 <span class="fileinput-exists">更改</span>
                                                 <input type="file" name="signContract" nv-file-select uploader="uploader" onchange="angular.element(this).scope().up(this.files[0])" ng-model="contract.signContract" ng-click="uploadFile('signContract')" > </span>
                                                 <div class="form-control-focus"> </div>
                                      			<span class="help-block">请上传纸质合同</span>
                                             <span class="fileinput-filename">{{contract.signContract.substring(contract.signContract.indexOf("_")+1)}}</span> &nbsp;
                                             <a href="javascript:;" class="close fileinput-exists" ng-click="removefile('signContract')" data-dismiss="fileinput"> </a>
                                         </div>
                                         <div ng-hide="contractInput"   ng-if="contract.signContract!=null&&contract.signContract!=''"  class="fileinput fileinput-exists" data-provides="fileinput">
                                             <span class="btn blue btn-outline btn-file">
                                                 <span class="fileinput-new">上传附件</span>
                                                 <span class="fileinput-exists">更改</span>
                                                 <input type="file" name="signContract" nv-file-select uploader="uploader" onchange="angular.element(this).scope().up(this.files[0])" ng-model="contract.signContract" ng-click="uploadFile('signContract')" > </span>
                                                 <div class="form-control-focus"> </div>
                                      				<span class="help-block">请上传纸质合同</span>
                                             <span class="fileinput-filename">{{contract.signContract.substring(contract.signContract.indexOf("_")+1)}}</span> &nbsp;
                                             <a href="javascript:;" class="close fileinput-exists"  ng-click="removefile('signContract')" data-dismiss="fileinput"> </a>
                                         </div>
                                       	<p class="form-control-static"  ng-show="contractShow" ng-if="contract.signContract==null||contract.signContract==''" class="c_edit" >未上传附件</p>
                                       	<p class="form-control-static"  ng-show="contractShow" ng-if="contract.signContract!=null&&contract.signContract!=''" class="c_edit" ><a href="javascript:;" ng-click="downloadFile(contract.signContract)">{{contract.signContract.substring(contract.signContract.indexOf("_")+1)}}</a></p>

                                  </div>
                                  
                              </div>
                          </div>
                          <!--/span-->
                          <div class="col-md-4">
                              <div class="form-group form-md-line-input">
                                 <label class="control-label col-md-3">客户合同号:</label>
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
             <!-- 供应商 start-->
          <div class="portlet-title">
               <div class="caption">物料信息</div>
               <div class="tools">
                  <button ng-click="addOrderMateriel()" type="button"  class="btn blue  btn-outline  btn-sm">
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
									<th>供应商</th>
									<th>库存数量</th>
									<th>销售数量</th>
									<th>单价</th>
									<th>销售单价</th>
									<th>含税销售单价</th>
									<th>金额</th>
									<th>税额</th>
									<th>价税合计</th>
									<th>交付日期</th>
									<th>最晚交付日期</th>
									<th>交付/提货地点</th>
									<th style="width:100px;">操作</th>
                                  </tr>
                              </thead>
                              <tbody>
                                  <tr ng-repeat="_orderMateriel in orderMateriel track by $index" ng-mouseover="showOperation('orderMateriel',$index)" ng-mouseleave="hideOperation('orderMateriel',$index)"  repeat-done="repeatDone()">
			                          <td>
                                            <span ng-hide="orderMaterielInput{{$index}}"><a href="javascript:;" ng-click="addMateriel('single',$index)">{{_orderMateriel.materiel.materielNum}}</a></span>
			                                <p class="form-control-static" ng-show="orderMaterielShow{{$index}}"> {{_orderMateriel.materiel.materielNum}} </p>
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
                                      		<p class="form-control-static" > {{_orderMateriel.supplyMateriel.supply.comName}} </p>
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
			                          		<input type="text"  name="orderUnitPrice{{$index}}" class="form-control" ng-hide="orderMaterielInput{{$index}}" ng-model="orderMateriel[$index].orderUnitPrice"  >
                                      		<p class="form-control-static" ng-show="orderMaterielShow{{$index}}"> {{_orderMateriel.orderUnitPrice}} </p>
			                          </td>
			                          <td>  
                                      		<p class="form-control-static"> {{_orderMateriel.orderUnitPrice?_orderMateriel.orderUnitPrice*17/100:0}} </p>
			                          </td>
			                          <td>  
                                      		<p class="form-control-static" > {{_orderMateriel.orderUnitPrice&&_orderMateriel.amount?_orderMateriel.orderUnitPrice*_orderMateriel.amount:0}} </p>
			                          </td>
			                          <td>  
                                      		<p class="form-control-static"> {{_orderMateriel.orderUnitPrice&&_orderMateriel.amount?_orderMateriel.orderUnitPrice*_orderMateriel.amount*17/100:0}} </p>
			                          </td>
			                          <td>  
                                      		<p class="form-control-static"> {{_orderMateriel.orderUnitPrice&&_orderMateriel.amount?(_orderMateriel.orderUnitPrice*_orderMateriel.amount+_orderMateriel.orderUnitPrice*_orderMateriel.amount*17/100):0}} </p>
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
                                        <span  ng-show="operation_o{{$index}}">
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
          <!-- 供应商 end-->
          
          <!--垫资条款start-->
             <div class="portlet-title">
                            <div class="caption">垫资条款</div>
                            <div class="tools" id="noprintdiv">
                            	<button type="submit" ng-click="saveClauseAdvance()" ng-hide="clauseAdvanceInput" class="btn blue  btn-outline  btn-sm">
                               		<i class="fa fa-save"></i> 保存 </button>
                               <button ng-click="cancelClauseAdvance()" type="button" ng-hide="clauseAdvanceInput" class="btn red  btn-outline  btn-sm">
                               		<i class="fa fa-undo"></i> 取消 </button>
                               <button ng-click="editClauseAdvance()" type="button" ng-show="clauseAdvanceShow" class="btn blue  btn-outline  btn-sm">
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
                                  <label class="control-label col-md-3">融资方:</label>
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
	                              	<label class="control-label col-md-3">垫资方:</label>
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
                                  <label class="control-label col-md-3">垫资金额:</label>
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
                                  <label class="control-label col-md-3">保证金比率:</label>
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
                                  <label class="control-label col-md-3">保证金金额:</label>
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
                                  <label class="control-label col-md-3">服务费率:</label>
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
                                  <label class="control-label col-md-3">垫资时间:</label>
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
                                  <label class="control-label col-md-3">服务费:</label>
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
                               <button ng-click="editClauseDelivery()" type="button" ng-show="clauseDeliveryShow" class="btn blue  btn-outline  btn-sm">
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
                                  <label class="control-label col-md-3">运输方式:</label>
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
                                  <label class="control-label col-md-3">包装要求:</label>
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
                                  <label class="control-label col-md-3">规格型号:</label>
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
                               <button ng-click="editClauseCheckAccept()" type="button" ng-show="clauseCheckAcceptShow" class="btn blue  btn-outline  btn-sm">
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
                                  <label class="control-label col-md-3">验收方:</label>
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
                                  <label class="control-label col-md-3">验收标准:</label>
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
                                  <label class="control-label col-md-3">规格型号:</label>
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
                               <button ng-click="editClauseAfterSales()" type="button" ng-show="clauseAfterSalesShow" class="btn blue  btn-outline  btn-sm">
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
                                  <label class="control-label col-md-3">质保要求:</label>
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
                                  <label class="control-label col-md-3">售后响应:</label>
                                  <div class="col-md-9">
                                  		<input type="text" name="afterSaleResponse" class="form-control" ng-hide="clauseAfterSalesInput" ng-model="clauseAfterSales.afterSaleResponse"  >
                                      <div class="form-control-focus"> </div>
                                      <span class="help-block">请输入售后响应</span>
                                      <p class="form-control-static" ng-show="clauseAfterSalesShow"> {{clauseAfterSales.afterSaleResponse}} </p>
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
      </div>
</div>
</div>
</div> 

<jsp:include  page="../demandPlan/selectMateriel.jsp"/>
