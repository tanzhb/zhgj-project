<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style>
.btn-default.active, .btn-default:active{
color: #32c5d2;
background-color: #fff;
border-color: #32c5d2;
}
.btn-default.active.focus, .btn-default.active:hover{
color: #32c5d2;
background-color: #fff;
border-color: #32c5d2;
}
.btn-default-margin{
margin-right: 20px;
}



</style>
<!-- 采购订单基本信息 START -->
<ul class="nav nav-tabs">
		<li class="active bold">
               		<a data-target="#tab_1_1" data-toggle="tab">框架信息</a>
           		</li>
		<!-- <li class="bold"><a data-target="#tab_1_2" data-toggle="tab">合同信息</a>
		</li> -->
		<li class="bold" ng-show="ClauseFrameworkShow"><a data-target="#tab_1_3" data-toggle="tab">框架条款</a></li>
		<!-- <li class="bold" ng-hide="tab_1_1Hide"><a data-target="#tab_1_4" data-toggle="tab">物料信息</a></li> -->
		<li class="bold" ng-hide="tab_1_3Hide"><a data-target="#tab_1_5" data-toggle="tab">结算条款</a></li>			
		<li class="bold" ng-hide="tab_1_4Hide"><a data-target="#tab_1_7" data-toggle="tab">交付条款</a></li>
		<li class="bold" ng-hide="tab_1_5Hide"><a data-target="#tab_1_6" data-toggle="tab">验收条款</a></li>
		<li class="bold" ng-hide="tab_1_6Hide"><a data-target="#tab_1_8" data-toggle="tab">售后条款</a></li>
		<li class="bold"><a data-target="#tab_1_9" data-toggle="tab">附件</a></li>
		<!-- <li class="bold"><a data-target="#tab_1_10" data-toggle="tab">备注</a></li> -->
		<li class="dropdown pull-right tabdrop">
			<button type="button" onclick="goBackPage()" class="btn defualt  btn-circle  btn-sm"><i class="fa fa-reply"></i>返回</button>
		</li>
	</ul>
<div class="tab-content" style="min-height: 300px;">
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
                                 <label class="control-label col-md-5 bold">供应商：</label>
                                 <div class="col-md-7">
                                     <p class="form-control-static"> {{buyFrame.comName}} </p>
                                 </div>
                                 
                                 
                             </div>
                         </div>
                         <div class="col-md-8">
                             <div class="form-group ">
                                 <label class="control-label col-md-3  bold">协议内容：</label>
                                 <div class="control-label col-md-9">
                                 <div class="btn-group">
                                     <div class="clearfix">
                                          <div class="btn-group" data-toggle="buttons">
                                              <!-- <label class="btn btn-default  btn-default-margin" id="tab_1_1Id" ng-hide = "tab_1_1label" >
                                                  <input type="checkbox" class="toggle"> <i class="fa fa-check"></i> 物料清单 </label>  -->
                                              <!-- <label class="btn btn-default  btn-default-margin" id="tab_1_2Id" ng-hide = "tab_1_2label">
                                                  <input type="checkbox" class="toggle"> <i class="fa fa-check"></i> 垫资条款 </label>  -->
                                              <label class="btn btn-default  btn-default-margin" id="tab_1_3Id" ng-hide = "tab_1_3label">
                                                  <input type="checkbox" class="toggle"> <i class="fa fa-check"></i> 结算条款 </label> 
                                              <label class="btn btn-default  btn-default-margin" id="tab_1_4Id" ng-hide = "tab_1_4label">
                                                  <input type="checkbox" class="toggle"> <i class="fa fa-check"></i> 交付条款 </label> 
                                              <label class="btn btn-default btn-default-margin" id="tab_1_5Id" ng-hide = "tab_1_5label">
                                                  <input type="checkbox" class="toggle"> <i class="fa fa-check"></i> 验收条款 </label>
                                              <label class="btn btn-default" id="tab_1_6Id" ng-hide = "tab_1_6label">
                                                  <input type="checkbox" class="toggle"> <i class="fa fa-check"></i> 售后条款 </label> 
                                          </div>
                                      </div>
                                 </div>
                                 </div>
                                 
                             </div>
                         </div>
                     </div>
                     <div class="row">
                         <div class="col-md-4">
                             <div class="form-group ">
                                 <label class="control-label col-md-5 bold">框架协议号：</label>
                                 <div class="col-md-7">
                                     <p class="form-control-static" > {{buyFrame.contractNum}} </p>
                                 </div>
                                 
                             </div>
                         </div>
                         <div class="col-md-4">
                             <div class="form-group ">
                                <label class="control-label col-md-5 bold">上传电子协议：</label>
                                 <div class="col-md-7">
                                      	<p class="form-control-static"  id="noFileFlag" ng-if="buyFrame.electronicContract==null||buyFrame.electronicContract==''" class="c_edit" >未上传附件</p>
                                      	<p class="form-control-static"  ng-if="buyFrame.electronicContract!=null&&buyFrame.electronicContract!=''" class="c_edit" ><a href="javascript：;" ng-click="downloadFile(buyFrame.electronicContract)">{{buyFrame.electronicContract.substring(buyFrame.electronicContract.indexOf("_")+1)}}</a></p>
                                 </div>
                             </div>
                         </div>
                         <div class="col-md-4">
                         	<div class="form-group ">
                                 <label class="control-label col-md-5 bold">采购商：</label>
                                    <div class="col-md-7">
                                     <p class="form-control-static" > {{buyFrame.seller}} </p>
                                 </div>
                             </div>
                         </div>
                     </div>
                     <!--/row-->
                     <div class="row">
                     	<div class="col-md-4">
                             <div class="form-group ">
                                 <label class="control-label col-md-5 bold">开始日期：</label>
                                 <div class="col-md-7">
                                     <p class="form-control-static" > {{buyFrame.startDate}} </p>
                                 </div>
                             </div>
                         </div>
                         <div class="col-md-4">
                             <div class="form-group ">
                                 <label class="control-label col-md-5 bold"> 结束日期：</label>
                                 <div class="col-md-7">
                                     <p class="form-control-static" > {{buyFrame.endDate}} </p>
                                 </div>
                             </div>
                         </div>
                         <div class="col-md-4">
                             <div class="form-group ">
                                <label class="control-label col-md-5 bold">备注：</label>
                                 <div class="col-md-7">
                                     <p class="form-control-static"> {{buyFrame.remark}} </p>
                                 </div>
                             </div>
                         </div>
                    </div>
                     <!--/row-->
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
                                     <p  ng-show="contractShow"> {{contract.contractNum}} </p>
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
                                     <p  ng-show="contractShow"> {{contract.startDate}} </p>
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
                                     <p  ng-show="contractShow"> {{contract.endDate}} </p>
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
                                     <p  ng-show="contractShow"> {{contract.signDate}} </p>
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
                                     <p  ng-show="contractShow"> {{contract.signerAddress}} </p>
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
                                     <p  ng-show="contractShow"> {{contract.signer}} </p>
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
                                      	<p   id="noFileFlag" ng-show="contractShow" ng-if="contract.electronicContract==null||contract.electronicContract==''" class="c_edit" >未上传附件</p>
                                      	<p   ng-show="contractShow" ng-if="contract.electronicContract!=null&&contract.electronicContract!=''" class="c_edit" ><a href="javascript：;" ng-click="downloadFile(contract.electronicContract)">{{contract.electronicContract.substring(contract.electronicContract.indexOf("_")+1)}}</a></p>
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
                                      	<p   ng-show="contractShow" ng-if="contract.signContract==null||contract.signContract==''" class="c_edit" >未上传附件</p>
                                      	<p   ng-show="contractShow" ng-if="contract.signContract!=null&&contract.signContract!=''" class="c_edit" ><a href="javascript：;" ng-click="downloadFile(contract.signContract)">{{contract.signContract.substring(contract.signContract.indexOf("_")+1)}}</a></p>

                                 </div>
                                 
                             </div>
                         </div>
                         /span
                         <div class="col-md-4">
                             <div class="form-group ">
                                <label class="control-label col-md-5 bold">采购合同号：</label>
                                 <div class="control-label col-md-7">
                                     <p  > {{contract.contractNum}} </p>
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
			<div class="row">
				<div class="col-md-6 col-sm-6">
					<div class="dataTables_length" id="sample_5_length">
						<label>每页显示 <select name="sample_5_length"
							aria-controls="sample_5" ng-model="pageSize" ng-change="createDispalyList()"
							class="form-control input-sm input-xsmall input-inline">
							<option value="5">5</option>
							<option value="10">10</option>
							<option value="15">15</option>
							<option value="30">30</option>
							<option value="99999">All</option>
							</select> 条数据
						</label>
					</div>
				</div>
				<div class="col-md-6 col-sm-6">
					<div id="sample_5_filter" style="text-align: right;">
						<label>查询:<input type="search" ng-model="queryStr"  ng-change="queryForPage()"
							class="form-control input-sm input-small input-inline"
							placeholder="" aria-controls="sample_5"></label>
					</div>
				</div>
			</div>
			<div class="table-scrollable">
                         <table class="table table-bordered table-hover" id="form_sample_5" >
                             <thead>
                                 <tr>
								<th>商品编号</th>
								<th ng-if="buyFrame.tradeType =='外贸'">海关编码</th>
								<th>物料名称</th>
								<th>规格型号</th>
								<th>单位</th>
								<th>采购数量</th>
								<th>指导单价</th>
								<th>含税单价</th>
								<th>不含税单价</th>
								<th>币种</th>
								<th>税率</th>
								<th>不含税金额</th>
								<th>税额</th>
								<th ng-if="buyFrame.tradeType =='外贸'">关税率</th>
								<th ng-if="buyFrame.tradeType =='外贸'">关税金额</th>
								<th ng-if="buyFrame.settlementClause =='服务费'">服务费率</th>
								<th ng-if="buyFrame.settlementClause =='服务费'">服务费</th>
								<th ng-if="buyFrame.settlementClause =='折扣折让'">折扣率</th>
								<th ng-if="buyFrame.settlementClause =='折扣折让'">折后金额</th>
								<th ng-if="buyFrame.settlementClause =='红票'">红票金额</th>
								
								<th>含税金额</th>
								<th>交付日期</th>
								<th>最晚交付日期</th>
								<th>交付地址</th>
                                 </tr>
                             </thead>
                             <tbody>
                                 <tr ng-repeat="_orderMateriel in dispalyOrderMateriel track by $index" >
		                          <td>
		                                <p class="form-control-static" > {{_orderMateriel.materiel.materielNum}} </p>
		                          </td>
		                          <td ng-if="buyFrame.tradeType =='外贸'">
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
                                     		<p class="form-control-static" > {{_orderMateriel.amount}} </p>
		                          </td>
		                          <td>  
                                     		<p class="form-control-static" > {{_orderMateriel.materiel.unitPrice}} </p>
		                          </td>
		                          <td>  
                                     		<p class="form-control-static" ng-if="_orderMateriel.materiel.unitPrice!=_orderMateriel.orderRateUnit" style="color:red"> {{_orderMateriel.orderRateUnit}} </p>
                                     		<p class="form-control-static" ng-if="_orderMateriel.materiel.unitPrice==_orderMateriel.orderRateUnit"> {{_orderMateriel.orderRateUnit}} </p>
		                          </td>
		                          <td>  
                                     		<p class="form-control-static" > {{format2Thousands(_orderMateriel.orderUnitPrice)}} </p>
		                          </td>
		                          <td>  
                                     		<p class="form-control-static" > {{buyFrame.currency}} </p>
		                          </td>
		                          <td>  
                                     		<p class="form-control-static" > {{buyFrame.rate}}% </p>
		                          </td>
		                          <td>  
                                     		<p class="form-control-static" > {{format2Thousands(_arithmeticAmount(this))}} </p>
		                          </td>
		                          <td>  
                                     		<p class="form-control-static"> {{format2Thousands(_arithmeticRateAmount(this))}} </p>
		                          </td>
		                          <td ng-if="buyFrame.tradeType =='外贸'">
                                     		<p class="form-control-static" > {{_orderMateriel.customsRate}}% </p>
		                          </td>
								  <td ng-if="buyFrame.tradeType =='外贸'">
								  		<p class="form-control-static" > {{format2Thousands(_arithmeticCustomsRateAmount(this))}} </p>
								  </td>
		                          <td ng-if="buyFrame.settlementClause =='服务费'">
                                     	<p class="form-control-static" > {{_orderMateriel.serviceRate}} </p>
		                          </td>
								<td ng-if="buyFrame.settlementClause =='服务费'">服务费</td>
								<td ng-if="buyFrame.settlementClause =='折扣折让'">
                                     	<p class="form-control-static" > {{_orderMateriel.discountRate}} </p>
								</td>
								<td ng-if="buyFrame.settlementClause =='折扣折让'">折后金额</td>
								<td ng-if="buyFrame.settlementClause =='红票'">
                                     	<p class="form-control-static" > {{_orderMateriel.redTicket}} </p>
								</td>
		                          <td>  
                                     		<p class="form-control-static"> {{format2Thousands(_arithmeticRateAndAmount(this))}} </p>
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
								<th ng-if="buyFrame.tradeType =='外贸'"></th>
								<th>合计</th>
								<th>{{totalCount()}}</th>
								<th></th>
								<th>{{totalMaterielCount()}}</th>
								<th></th>
								<th></th>
								<th></th>
								<th></th>
								<th></th>
								<th>{{format2Thousands(totalAmount())}}</th>
								<th>{{format2Thousands(totalRateAmount())}}</th>
								<th ng-if="buyFrame.tradeType =='外贸'"></th>
								<th ng-if="buyFrame.tradeType =='外贸'">{{format2Thousands(totalCustomsRateAmount())}}</th>
								<th ng-if="buyFrame.settlementClause =='服务费'">服务费率</th>
								<th ng-if="buyFrame.settlementClause =='服务费'">服务费</th>
								<th ng-if="buyFrame.settlementClause =='折扣折让'">折扣率</th>
								<th ng-if="buyFrame.settlementClause =='折扣折让'">折后金额</th>
								<th ng-if="buyFrame.settlementClause =='红票'">红票金额</th>
								
								<th>{{format2Thousands(totalRateAndAmount())}}</th>
								<th></th>
								<th></th>
								<th></th>
                                 </tr>
                             </tbody>
                         </table>
            </div>
			<div class="row">
				<div class="col-md-5 col-sm-5">
					<div class="dataTables_info" id="sample_5_info" role="status"
						aria-live="polite">从 {{(pageIndex-1)*pageSize+1>filterOrderMateriel.length?filterOrderMateriel.length:(pageIndex-1)*pageSize+1}}
						到 {{pageIndex*pageSize>filterOrderMateriel.length?filterOrderMateriel.length:pageIndex*pageSize}} /共 {{filterOrderMateriel.length}} 条数据（从{{orderMateriel.length}}条数据中筛选）</div>
				</div>
				<div class="col-md-7 col-sm-7">
					<div  style="text-align: right;" id="sample_5_paginate">
						<ul class="pagination" style="visibility: visible;">
							<li class="prev" ng-if="pageIndex>1"><a href="#" ng-click="link2PreviousPage()" title="前一页"><i
									class="fa fa-angle-left"></i></a></li>
							<li class="prev disabled" ng-if="1>=pageIndex"><a href="#" title="前一页"><i
									class="fa fa-angle-left"></i></a></li>
							<li ng-if="pageIndex-2>0"><a href="#" ng-click="link2ThisPage(pageIndex-2)">{{pageIndex-2}}</a></li>
							<li ng-if="pageIndex-1>0"><a href="#" ng-click="link2ThisPage(pageIndex-1)">{{pageIndex-1}}</a></li>
							<li class="active"><a href="#">{{pageIndex}}</a></li>
							<li ng-if="totalPage>pageIndex"><a href="#" ng-click="link2ThisPage(pageIndex+1)">{{pageIndex+1}}</a></li>
							<li ng-if="totalPage>pageIndex+1"><a href="#" ng-click="link2ThisPage(pageIndex+2)">{{pageIndex+2}}</a></li>
							<li class="next disabled" ng-if="pageIndex>=totalPage"><a href="#" ><i
									class="fa fa-angle-right"></i></a></li>
							<li class="next" ng-if="totalPage>pageIndex"><a href="#" ng-click="link2NextPage()" title="后一页"><i
									class="fa fa-angle-right"></i></a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
         <!-- 订单物料 end-->
	</div>
	<div class="tab-pane fade " id="tab_1_5">
		<!--结算条款start-->
         <div class="portlet-body form">
             <!-- BEGIN FORM-->
             <form action="#" id="form_clauseSettlement"  >
                 <!-- <div class="form-body">
                     <div class="alert alert-danger display-hide">
                         <button class="close" data-close="alert"></button> 请先输入正确数据！ </div>
                     <div class="row">
                     	<div class="col-md-4">
                         		<div class="form-group ">
                              	<label class="control-label col-md-5 bold">含税金额：</label>
                                 <div class="control-label col-md-7">
                                  <p > {{getCurrencySymbol()}}{{format2Thousands(totalRateAndAmount())}} </p>
                                  </div>
                              </div>
                         </div>
                         <div class="col-md-4">
                             <div class="form-group ">
                                 <label class="control-label col-md-5 bold">不含税金额：</label>
                                 <div class="control-label col-md-7">
                                 <p > {{getCurrencySymbol()}}{{format2Thousands(totalAmount())}} </p>
                                 </div>
                             </div>
                         </div>
                         /span
                         <div class="col-md-4">
                         		<div class="form-group ">
                              	<label class="control-label col-md-5 bold">税额(含关税)：</label>
                                 <div class="control-label col-md-7">
                                  <p >{{getCurrencySymbol()}}{{format2Thousands(totalRateAndCustomsAmount())}}</p>
                                  </div>
                              </div>
                         </div>
                         
                         /span
                     </div>
                     /row
                     <div class="row">
                         /span
                         <div class="col-md-4">
                             <div class="form-group ">
                                 <label class="control-label col-md-5 bold">其他费用：</label>
                                 <div class="control-label col-md-7">
                                     <p  > {{getCurrencySymbol()}}{{clauseSettlement.otherAmount}} </p>
                              		</div>
                             </div>
                         </div>
                         <div class="col-md-4">
                             <div class="form-group ">
                                 <label class="control-label col-md-5 bold">订单金额：</label>
                                    <div class="control-label col-md-7">
                                 <p > {{getCurrencySymbol()}}{{format2Thousands(totalOrderAmount())}} </p>
                                 </div>
                             </div>
                         </div>
                         /span
                         <div class="col-md-4">
                             <div class="form-group ">
                                 <label class="control-label col-md-5 bold">备注：</label>
                                 <div class="control-label col-md-7">
                                     <p  > {{clauseSettlement.remark}} </p>
                              		</div>
                             </div>
                         </div>
                         /span
                     </div>
                     /row
                 </div> -->
			</form>
			<form id="form_sample_3"   >
		         <div class="table-scrollable">
                         <table class="table table-bordered table-hover">
                             <thead>
                                 <tr>
                                     <th>支付类型</th>
                                     <th>支付节点</th>
                                     <th>账期（天）</th>
                                     <th>支付比率%</th>
                                     <th>支付金额</th>
                                     <th>支付方式</th>
                                     <!-- <th>开票方式</th>
                                     <th>开票金额</th> -->
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
		                                <p class="form-control-static" > {{format2Thousands(_CSD.deliveryAmount)}} </p>
		                          </td>
		                          <td>
		                                <p class="form-control-static" > {{_CSD.paymentMethod}} </p>
		                          </td>
		                          <td>
		                                <p class="form-control-static" > {{_CSD.billingMethod}} </p>
		                          </td>
		                          <!-- <td>
		                                <p class="form-control-static" > {{_CSD.billingAmount}} </p>
		                          </td>
		                          <td>
		                                <p class="form-control-static" > {{_CSD.unbilledAmount}} </p>
		                          </td> -->
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
                                 <label class="control-label col-md-5 bold">检验类型：</label>
                                 <div class="control-label col-md-7">
                                     <p > {{clauseCheckAccept.checkType}} </p>
                                 </div>
                             </div>
                         </div>
                     	<div class="col-md-4">
                             <div class="form-group ">
                                 <label class="control-label col-md-5 bold">检验方：</label>
                                 <div class="control-label col-md-7">
                                     <p > {{clauseCheckAccept.checkParty}} </p>
                              		</div>
                             </div>
                         </div>
                         <div class="col-md-4">
                             <div class="form-group ">
                                 <label class="control-label col-md-5 bold">预约检验日期：</label>
                                 <div class="control-label col-md-7">
                                     <p > {{clauseCheckAccept.playCheckDate}} </p>
                                 </div>
                                 
                             </div>
                         </div>
                         
                     </div>
                     <div class="row">
                         <!--/span-->
                         <div class="col-md-4">
                             <div class="form-group ">
                                 <label class="control-label col-md-5 bold">验收标准：</label>
                                 <div class="control-label col-md-7">
                                     <p > {{clauseCheckAccept.acceptStandard}} </p>
                              		</div>
                             </div>
                         </div>
                         <div class="col-md-4">
                             <div class="form-group ">
                                 <label class="control-label col-md-5 bold">技术文件：</label>
                                 <div class="control-label col-md-7">
                                     <p > {{clauseCheckAccept.technicalFile}} </p>
                              		</div>
                             </div>
                         </div>
                         <div class="col-md-4">
                             <div class="form-group ">
                                 <label class="control-label col-md-5 bold">备注：</label>
                                 <div class="control-label col-md-7">
                                     <p > {{clauseCheckAccept.remark}} </p>
                              		</div>
                             </div>
                         </div>
                         <!--/span-->
                         <!-- <div class="col-md-4">
                             <div class="form-group ">
                                 <label class="control-label col-md-5 bold">补充说明：</label>
                                 <div class="control-label col-md-7">
                                     <p > {{clauseCheckAccept.remark}} </p>
                              		</div>
                             </div>
                         </div> -->
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
                                 <label class="control-label col-md-5 bold">送货方式：</label>
                                 <div class="control-label col-md-7" >
                                     <p > {{clauseDelivery.deliveryMode}} </p>
                                 </div>
                             </div>
                         </div>
                          <div class="col-md-4">
                             <div class="form-group ">
                                 <label class="control-label col-md-5 bold"><span ng-if="clauseDelivery.deliveryMode=='自提'">提货</span><span ng-if="clauseDelivery.deliveryMode=='配送'">收货</span>地址：</label>
                                 <div class="control-label col-md-7">
                                     <p  > {{clauseDelivery.warehouseAddress}} </p>
                              		</div>
                             </div>
                         </div>
                         <div class="col-md-4">
                             <div class="form-group ">
                                 <label class="control-label col-md-5 bold">运输方式：</label>
                                 <div class="control-label col-md-7">
                                     <p  > {{clauseDelivery.transportType}} </p>
                              		</div>
                             </div>
                         </div>
                        
                    </div>
                    <div class="row">     
                          <!--/span-->
                         <div class="col-md-4">
                             <div class="form-group ">
                                 <label class="control-label col-md-5 bold">包装要求：</label>
                                 <div class="control-label col-md-7">
                                     <p  > {{clauseDelivery.packingRequire}} </p>
                              		</div>
                             </div>
                         </div>
                         <div class="col-md-4">
                             <div class="form-group ">
                                 <label class="control-label col-md-5 bold">包装规格型号：</label>
                                 <div class="control-label col-md-7">
                                     <p  > {{clauseDelivery.specifications}} </p>
                              		</div>
                             </div>
                         </div>
                         <!--/span-->
                          <div class="col-md-4">
                             <div class="form-group ">
                                 <label class="control-label col-md-5 bold">备注：</label>
                                 <div class="control-label col-md-7" >
                                     <p > {{clauseDelivery.remark}} </p>
                              		</div>
                             </div>
                         </div>
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
                                     <p  > {{clauseAfterSales.qualityRequirements}} </p>
                              		</div>
                             </div>
                         </div>
                         <!--/span-->
                         <div class="col-md-4">
                             <div class="form-group ">
                                 <label class="control-label col-md-5 bold">售后响应：</label>
                                 <div class="control-label col-md-7">
                                     <p  > {{clauseAfterSales.afterSaleResponse}} </p>
                              		</div>
                             </div>
                         </div>
                         <!--/span-->
                         <div class="col-md-4">
                             <div class="form-group ">
                                 <label class="control-label col-md-5 bold">补充说明：</label>
                                 <div class="control-label col-md-7">
                                     <p  > {{clauseAfterSales.remark}} </p>
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
     </div>
<!-- 采购订单基本信息 end -->