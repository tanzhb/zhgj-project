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
	<li class="bold" ng-show="ClauseFrameworkShow" ><a data-target="#tab_1_3" data-toggle="tab">框架条款</a></li>
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
		<div class="portlet-title" style="min-height: 48px;">
              <div class="tools" style="float:right" id="noprintdiv">
                        	<button type="submit" ng-click="save()" ng-hide="saleFrameInput" class="btn green  btn-circle  btn-sm">
                           		<i class="fa fa-save"></i> 保存 </button>
                           <button ng-click="cancel()" type="button" ng-hide="saleFrameInput" class="btn defualt  btn-circle  btn-sm">
                           		<i class="fa fa-undo"></i> 取消 </button>
                           <button ng-click="edit()" type="button" ng-show="saleFrameShow&&noShow" class="btn purple  btn-circle  btn-sm">
                           		<i class="fa fa-edit"></i> 编辑 </button>
                           <!--  <a href="javascript：;" class="collapse"> </a> -->
                         </div>
                   	</div>
         <div class="portlet-body form">
             <!-- BEGIN FORM-->
             <form action="#" id="form_contract"  >
                 <div class="form-body">
                     <div class="alert alert-danger display-hide">
                         <button class="close" data-close="alert"></button> 请先输入正确数据！ </div>
                     		<!--/span-->
                         <!--/span-->
                     <div class="row">
                         <div class="col-md-4">
                             <div class="form-group ">
                                 <label class="control-label bold"><span class="required" aria-required="true"> * </span>采购商：</label>
                                 <div class="">
                                 	<div  ng-hide="saleFrameInput">
                                 		<div class="row">
                         					<div class="col-md-8">
                         						<select class="form-control" id="buyComId"  data-live-search="true"  name="buyComId" class="form-control" style="width:100px"
                                 		 ng-model="saleFrame.comId"  data-size="8">
                                                   <option value=""></option>
                                                   <option  ng-repeat="customer in customers" value="{{customer.comId}}" >{{customer.comName}}</option>
                                               </select>
                         					</div>
                         					<div class="col-md-4">
                         						<button type="button" ng-click="addCompany()"  class="btn green   btn-sm">
                           							增加企业 </button>
                         					</div>
                         				</div>	
                                               </div>
                                     <p class="form-control-static" ng-show="saleFrameShow"> {{saleFrame.comName}} </p>
                                 </div>
                                 
                                 
                             </div>
                         </div>
                         <div class="col-md-8">
                             <div class="form-group ">
                                 <label class="control-label bold">协议内容：</label>
                                 <div class="">
                                 <div class="btn-group">
                                     <div class="clearfix">
                                          <div class="btn-group" data-toggle="buttons">
                                              <!-- <label class="btn btn-default  btn-default-margin" id="tab_1_1Id" ng-click="changeContentStatus(1)">
                                                  <input type="checkbox" class="toggle"> <i class="fa fa-check"></i> 物料清单 </label>  -->
                                              <!-- <label class="btn btn-default  btn-default-margin" id="tab_1_2Id" ng-click="changeContentStatus(2)">
                                                  <input type="checkbox" class="toggle"> <i class="fa fa-check"></i> 垫资条款 </label>  -->
                                              <label class="btn btn-default  btn-default-margin" id="tab_1_3Id" ng-click="changeContentStatus(3)">
                                                  <input type="checkbox" class="toggle"> <i class="fa fa-check"></i> 结算条款 </label> 
                                              <label class="btn btn-default  btn-default-margin" id="tab_1_4Id" ng-click="changeContentStatus(4)">
                                                  <input type="checkbox" class="toggle"> <i class="fa fa-check"></i> 交付条款 </label> 
                                              <label class="btn btn-default btn-default-margin" id="tab_1_5Id" ng-click="changeContentStatus(5)">
                                                  <input type="checkbox" class="toggle"> <i class="fa fa-check"></i> 验收条款 </label>
                                              <label class="btn btn-default" id="tab_1_6Id" ng-click="changeContentStatus(6)">
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
                                 <label class="control-label bold"><span class="required" aria-required="true"> * </span>框架协议号：</label>
                                 <div class="">
                                 <input type="text" name="contractNum" class="form-control" ng-hide="saleFrameInput" ng-model="saleFrame.contractNum"  >
                                     <div class="form-control-focus"> </div>
                                     <span class="help-block" ng-hide="saleFrameInput">请输入框架协议号</span>
                                     <p class="form-control-static" ng-show="saleFrameShow"> {{saleFrame.contractNum}} </p>
                                 </div>
                                 
                             </div>
                         </div>
                         <div class="col-md-4">
                             <div class="form-group ">
                                <label class="control-label bold"><span class="required" aria-required="true"> * </span>上传电子协议：</label>
                                 <div class="">
                                 		<div ng-hide="saleFrameInput"   ng-if="saleFrame.electronicContract==null||saleFrame.electronicContract==''"  class="fileinput fileinput-new" data-provides="fileinput">
                                            <span class="btn blue btn-circle btn-file">
                                                <span class="fileinput-new">上传附件</span>
                                                <span class="fileinput-exists">更改</span>
                                                <input type="file" name="electronicContract" nv-file-select uploader="uploader" onchange="angular.element(this).scope().up(this.files[0])" ng-model="saleFrame.electronicContract" ng-click="uploadFile('electronicContract')" /> </span>
                                            <span class="fileinput-filename">{{saleFrame.electronicContract.substring(saleFrame.electronicContract.indexOf("_")+1)}}</span> &nbsp;
                                            <a href="javascript：;" class="close fileinput-exists" ng-click="removefile('electronicContract')" data-dismiss="fileinput"> </a>
                                        </div>
                                        <div ng-hide="saleFrameInput"   ng-if="saleFrame.electronicContract!=null&&saleFrame.electronicContract!=''"  class="fileinput fileinput-exists" data-provides="fileinput">
                                            <span class="btn blue btn-circle btn-file">
                                                <span class="fileinput-new">上传附件</span>
                                                <span class="fileinput-exists">更改</span>
                                                <input type="file" name="electronicContract" nv-file-select uploader="uploader" onchange="angular.element(this).scope().up(this.files[0])" ng-model="saleFrame.electronicContract" ng-click="uploadFile('electronicContract')" /> </span>
                                            <span class="fileinput-filename">{{saleFrame.electronicContract.substring(saleFrame.electronicContract.indexOf("_")+1)}}</span> &nbsp;
                                            <a href="javascript：;" class="close fileinput-exists"  ng-click="removefile('electronicContract')" data-dismiss="fileinput"> </a>
                                        </div>
                                      	<p class="form-control-static"  id="noFileFlag" ng-show="saleFrameShow" ng-if="saleFrame.electronicContract==null||saleFrame.electronicContract==''" class="c_edit" >未上传附件</p>
                                      	<p class="form-control-static"  ng-show="saleFrameShow" ng-if="saleFrame.electronicContract!=null&&saleFrame.electronicContract!=''" class="c_edit" ><a href="javascript：;" ng-click="downloadFile(saleFrame.electronicContract)">{{saleFrame.electronicContract.substring(saleFrame.electronicContract.indexOf("_")+1)}}</a></p>
                                 </div>
                             </div>
                         </div>
                         <div class="col-md-4">
                         	<div class="form-group ">
                                 <label class="control-label bold"><span class="required" aria-required="true"> * </span>供应商：</label>
                                    <div class="">
                                  <input type="text" name="seller" class="form-control" ng-hide="saleFrameInput" ng-model="saleFrame.seller" value="" >
                                     <div class="form-control-focus"> </div>
                                     <span class="help-block" ng-hide="saleFrameInput">请输入供应商</span>
                                     <p class="form-control-static" ng-show="saleFrameShow"> {{saleFrame.seller}} </p>
                                 </div>
                             </div>
                         </div>
                     </div>
                     <!--/row-->
                     <div class="row">
                     	<div class="col-md-4">
                             <div class="form-group ">
                                 <label class="control-label bold"><span class="required" aria-required="true"> * </span>开始日期：</label>
                                 <div class="">
                                 		<input type="text" class="form-control form-control-inline input-medium date-picker" 
                                     data-date-format="yyyy-mm-dd" data-date-viewmode="years" size="16" ng-model="saleFrame.startDate"  ng-hide="saleFrameInput" id="startDate" name="startDate"/>
									<div class="form-control-focus"> </div>
									<span class="help-block" ng-hide="saleFrameInput">请输入开始日期</span>
                                     <p class="form-control-static" ng-show="saleFrameShow"> {{saleFrame.startDate}} </p>
                                 </div>
                             </div>
                         </div>
                         <div class="col-md-4">
                             <div class="form-group ">
                                 <label class="control-label bold"><span class="required" aria-required="true"> * </span>结束日期：</label>
                                 <div class="">
                                 		<input type="text" class="form-control form-control-inline input-medium date-picker" 
                                     data-date-format="yyyy-mm-dd" data-date-viewmode="years" size="16" ng-model="saleFrame.endDate"  ng-hide="saleFrameInput" id="endDate" name="endDate"/>
									<div class="form-control-focus"> </div>
									<span class="help-block" ng-hide="saleFrameInput">请输入结束日期</span>
                                     <p class="form-control-static" ng-show="saleFrameShow"> {{saleFrame.endDate}} </p>
                                 </div>
                             </div>
                         </div>
                         <div class="col-md-4">
                             <div class="form-group ">
                                <label class="control-label bold">备注：</label>
                                 <div class="">
                                 <input type="text" name="remark" class="form-control" ng-hide="saleFrameInput" ng-model="saleFrame.remark"  >
                                     <div class="form-control-focus"> </div>
                                     <span class="help-block" ng-hide="saleFrameInput">请输入备注</span>
                                     <p class="form-control-static" ng-show="saleFrameShow"> {{saleFrame.remark}} </p>
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
            <div class="portlet-title" style="min-height: 48px;">
              <div class="tools" style="float:right">
                        	<button type="submit" ng-click="saveContract()" ng-hide="contractInput" class="btn green  btn-circle  btn-sm">
                           		<i class="fa fa-save"></i> 保存 </button>
                           <button ng-click="cancelContract()" type="button" ng-hide="contractInput" class="btn defualt  btn-circle  btn-sm">
                           		<i class="fa fa-undo"></i> 取消 </button>
                           <button ng-click="editContract()" type="button" ng-show="contractShow&&noShow" class="btn purple  btn-circle  btn-sm">
                           		<i class="fa fa-edit"></i> 编辑 </button>
                         </div>
                    </div>
         <div class="portlet-body form">
             BEGIN FORM
             <form action="#" id="form_contract"  >
                 <div class="form-body">
                     <div class="alert alert-danger display-hide">
                         <button class="close" data-close="alert"></button> 请先输入正确数据！ </div>
                     <div class="row">
                         <div class="col-md-4">
                             <div class="form-group ">
                                 <label class="control-label bold"><span class="required" aria-required="true"> * </span>合同编号：</label>
                                 <div class="">
                                 <input type="text" name="contractNum" class="form-control" ng-hide="contractInput" ng-model="saleFrame.contractNum"  >
                                     <div class="form-control-focus"> </div>
                                     <span class="help-block" ng-hide="contractInput">请输入合同编号</span>
                                     <p class="form-control-static" ng-show="contractShow"> {{saleFrame.contractNum}} </p>
                                 </div>
                                 
                             </div>
                         </div>
                         /span
                         <div class="col-md-4">
                             <div class="form-group ">
                                 <label class="control-label bold"><span class="required" aria-required="true"> * </span>开始日期：</label>
                                 <div class="">
                                 		<input type="text" class="form-control form-control-inline input-medium date-picker" 
                                     data-date-format="yyyy-mm-dd" data-date-viewmode="years" size="16" ng-model="saleFrame.startDate"  ng-hide="contractInput" id="startDate" name="startDate"/>
									<div class="form-control-focus"> </div>
									<span class="help-block" ng-hide="contractInput">请输入开始日期</span>
                                     <p class="form-control-static" ng-show="contractShow"> {{saleFrame.startDate}} </p>
                                 </div>
                             </div>
                         </div>
                         /span
                         <div class="col-md-4">
                             <div class="form-group ">
                                 <label class="control-label bold"><span class="required" aria-required="true"> * </span>结束日期：</label>
                                 <div class="">
                                 		<input type="text" class="form-control form-control-inline input-medium date-picker" 
                                     data-date-format="yyyy-mm-dd" data-date-viewmode="years" size="16" ng-model="saleFrame.endDate"  ng-hide="contractInput" id="endDate" name="endDate"/>
									<div class="form-control-focus"> </div>
									<span class="help-block" ng-hide="contractInput">请输入结束日期</span>
                                     <p class="form-control-static" ng-show="contractShow"> {{saleFrame.endDate}} </p>
                                 </div>
                             </div>
                         </div>
                         /span
                     </div>
                     /row
                     <div class="row">
                         <div class="col-md-4">
                             <div class="form-group ">
                                 <label class="control-label bold">签订日期：</label>
                                 <div class="">
                                 		<input type="text" class="form-control form-control-inline input-medium date-picker" 
                                     data-date-format="yyyy-mm-dd" data-date-viewmode="years" size="16" ng-model="saleFrame.signDate" ng-hide="contractInput"  id="signDate" name="signDate"/>
									<div class="form-control-focus"> </div>
									<span class="help-block" ng-hide="contractInput">请输入签订日期</span>
                                     <p class="form-control-static" ng-show="contractShow"> {{saleFrame.signDate}} </p>
                                 </div>
                             </div>
                         </div>
                         /span
                         <div class="col-md-4">
                             <div class="form-group ">
                                 <label class="control-label bold">签约地点：</label>
                                 <div class="">
                                 <input type="text" name="signerAddress" class="form-control" ng-hide="contractInput" ng-model="saleFrame.signerAddress"  >
                                     <div class="form-control-focus"> </div>
                                     <span class="help-block" ng-hide="contractInput">请输入签约地点</span>
                                     <p class="form-control-static" ng-show="contractShow"> {{saleFrame.signerAddress}} </p>
                                 </div>
                             </div>
                         </div>
                         /span
                         <div class="col-md-4">
                             <div class="form-group ">
                                 <label class="control-label bold">签订人：</label>
                                 <div class="">
                                 <input type="text" name="signer" class="form-control" ng-hide="contractInput" ng-model="saleFrame.signer"  >
                                     <div class="form-control-focus"> </div>
                                     <span class="help-block" ng-hide="contractInput">请输入签订人</span>
                                     <p class="form-control-static" ng-show="contractShow"> {{saleFrame.signer}} </p>
                                 </div>
                             </div>
                         </div>
                         /span
                     </div>
                     /row
                     <div class="row">
                         <div class="col-md-4">
                             <div class="form-group ">
                                 <label class="control-label bold"><span class="required" aria-required="true"> * </span>合同附件：</label>
                                 <div class="">
                                 		<div ng-hide="contractInput"   ng-if="saleFrame.electronicContract==null||saleFrame.electronicContract==''"  class="fileinput fileinput-new" data-provides="fileinput">
                                            <span class="btn blue btn-circle btn-file">
                                                <span class="fileinput-new">上传附件</span>
                                                <span class="fileinput-exists">更改</span>
                                                <input type="file" name="electronicContract" nv-file-select uploader="uploader" onchange="angular.element(this).scope().up(this.files[0])" ng-model="saleFrame.electronicContract" ng-click="uploadFile('electronicContract')" > </span>
                                            <span class="fileinput-filename">{{saleFrame.electronicContract.substring(saleFrame.electronicContract.indexOf("_")+1)}}</span> &nbsp;
                                            <a href="javascript：;" class="close fileinput-exists" ng-click="removefile('electronicContract')" data-dismiss="fileinput"> </a>
                                        </div>
                                        <div ng-hide="contractInput"   ng-if="saleFrame.electronicContract!=null&&saleFrame.electronicContract!=''"  class="fileinput fileinput-exists" data-provides="fileinput">
                                            <span class="btn blue btn-circle btn-file">
                                                <span class="fileinput-new">上传附件</span>
                                                <span class="fileinput-exists">更改</span>
                                                <input type="file" name="electronicContract" nv-file-select uploader="uploader" onchange="angular.element(this).scope().up(this.files[0])" ng-model="saleFrame.electronicContract" ng-click="uploadFile('electronicContract')" > </span>
                                            <span class="fileinput-filename">{{saleFrame.electronicContract.substring(saleFrame.electronicContract.indexOf("_")+1)}}</span> &nbsp;
                                            <a href="javascript：;" class="close fileinput-exists"  ng-click="removefile('electronicContract')" data-dismiss="fileinput"> </a>
                                        </div>
                                      	<p class="form-control-static"  id="noFileFlag" ng-show="contractShow" ng-if="saleFrame.electronicContract==null||saleFrame.electronicContract==''" class="c_edit" >未上传附件</p>
                                      	<p class="form-control-static"  ng-show="contractShow" ng-if="saleFrame.electronicContract!=null&&saleFrame.electronicContract!=''" class="c_edit" ><a href="javascript：;" ng-click="downloadFile(saleFrame.electronicContract)">{{saleFrame.electronicContract.substring(saleFrame.electronicContract.indexOf("_")+1)}}</a></p>
                                 </div>
                             </div>
                         </div>
                         /span
                         <div class="col-md-4">
                             <div class="form-group ">
                                 <label class="control-label bold">纸质合同：</label>
                                 <div class="">
                                 		<div ng-hide="contractInput"   ng-if="saleFrame.signContract==null||saleFrame.signContract==''"  class="fileinput fileinput-new" data-provides="fileinput">
                                            <span class="btn blue btn-circle btn-file">
                                                <span class="fileinput-new">上传附件</span>
                                                <span class="fileinput-exists">更改</span>
                                                <input type="file" name="signContract" nv-file-select uploader="uploader" onchange="angular.element(this).scope().up(this.files[0])" ng-model="saleFrame.signContract" ng-click="uploadFile('signContract')" > </span>
                                            <span class="fileinput-filename">{{saleFrame.signContract.substring(saleFrame.signContract.indexOf("_")+1)}}</span> &nbsp;
                                            <a href="javascript：;" class="close fileinput-exists" ng-click="removefile('signContract')" data-dismiss="fileinput"> </a>
                                        </div>
                                        <div ng-hide="contractInput"   ng-if="saleFrame.signContract!=null&&saleFrame.signContract!=''"  class="fileinput fileinput-exists" data-provides="fileinput">
                                            <span class="btn blue btn-circle btn-file">
                                                <span class="fileinput-new">上传附件</span>
                                                <span class="fileinput-exists">更改</span>
                                                <input type="file" name="signContract" nv-file-select uploader="uploader" onchange="angular.element(this).scope().up(this.files[0])" ng-model="saleFrame.signContract" ng-click="uploadFile('signContract')" > </span>
                                            <span class="fileinput-filename">{{saleFrame.signContract.substring(saleFrame.signContract.indexOf("_")+1)}}</span> &nbsp;
                                            <a href="javascript：;" class="close fileinput-exists"  ng-click="removefile('signContract')" data-dismiss="fileinput"> </a>
                                        </div>
                                      	<p class="form-control-static"  ng-show="contractShow" ng-if="saleFrame.signContract==null||saleFrame.signContract==''" class="c_edit" >未上传附件</p>
                                      	<p class="form-control-static"  ng-show="contractShow" ng-if="saleFrame.signContract!=null&&saleFrame.signContract!=''" class="c_edit" ><a href="javascript：;" ng-click="downloadFile(saleFrame.signContract)">{{saleFrame.signContract.substring(saleFrame.signContract.indexOf("_")+1)}}</a></p>

                                 </div>
                                 
                             </div>
                         </div>
                         /span
                         <div class="col-md-4">
                             <div class="form-group ">
                                <label class="control-label bold">采购合同号：</label>
                                 <div class="">
                                 <input type="text" name="contractNum" class="form-control" ng-hide="saleFrameInput" ng-model="saleFrame.contractNum"  >
                                     <div class="form-control-focus"> </div>
                                     <span class="help-block" ng-hide="saleFrameInput">请输入合同号</span>
                                     <p class="form-control-static" ng-show="saleFrameShow"> {{saleFrame.contractNum}} </p>
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
         <div class="portlet-title" ng-show="ClauseFrameworkShow" style="min-height: 48px;">
              <div class="tools" style="float:right">
              	 	<button type="submit" ng-click="saveClauseFramework()" ng-hide="ClauseFrameworkInfoInput" class="btn green  btn-circle  btn-sm">
                 		<i class="fa fa-save"></i> 保存 </button>
                 <button ng-click="cancelClauseFramework()" type="button" ng-hide="ClauseFrameworkInfoInput" class="btn defualt  btn-circle  btn-sm">
                 		<i class="fa fa-undo"></i> 取消 </button>
                 <button ng-click="editClauseFramework()" type="button" ng-show="ClauseFrameworkInfoShow&&noShow" class="btn purple  btn-circle  btn-sm">
                 		<i class="fa fa-edit"></i> 编辑 </button>
               </div>
           </div>
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
	</div>
	<div class="tab-pane fade " id="tab_1_4">
		<!-- 订单物料 start-->
         <div class="portlet-title" style="min-height: 48px;">
              <div class="tools" style="float:right">
                 <button ng-click="addOrderMateriel()" type="button"  ng-hide="orderMaterielInput" class="btn blue  btn-circle  btn-sm">
                 		<i class="fa fa-edit"></i> 添加物料 </button>
                 <button type="submit" ng-click="saveAllOrderMateriel()" ng-hide="orderMaterielInput"  class="btn green  btn-circle  btn-sm">
                 		<i class="fa fa-save"></i> 保存 </button>
                 <button ng-click="cancelAllOrderMateriel()" type="button" ng-hide="orderMaterielInput" class="btn defualt  btn-circle  btn-sm">
                 		<i class="fa fa-undo"></i> 取消 </button>
                 <button ng-click="editAllOrderMateriel()" type="button" ng-show="orderMaterielShow" class="btn purple  btn-circle  btn-sm">
                 		<i class="fa fa-edit"></i> 编辑 </button>
               </div>
           </div>

          <div class="portlet-body form">
		     <form id="form_sample_5"   >
		         <div class="table-scrollable">
                         <table class="table table-bordered table-hover">
                             <thead>
                                 <tr>
								<th>商品编号</th>
								<th ng-if="saleFrame.tradeType =='外贸'">海关编码</th>
								<th>物料名称</th>
								<th>规格型号</th>
								<th>单位</th>
								<th><span style="display:inline-block;width:100px;">采购数量</span></th>
								<th>指导单价</th>
								<th><span style="display:inline-block;width:100px;">含税单价</span></th>
								<th><span style="display:inline-block;width:150px;">不含税单价</span></th>
								<th>币种</th>
								<th>税率</th>
								<th>不含税金额</th>
								<th>税额</th>
								<th ng-if="saleFrame.tradeType =='外贸'">关税率</th>
								<th ng-if="saleFrame.tradeType =='外贸'">关税金额</th>
								<th ng-if="saleFrame.settlementClause =='服务费'">服务费率</th>
								<th ng-if="saleFrame.settlementClause =='服务费'">服务费</th>
								<th ng-if="saleFrame.settlementClause =='折扣折让'">折扣率</th>
								<th ng-if="saleFrame.settlementClause =='折扣折让'">折后金额</th>
								<th ng-if="saleFrame.settlementClause =='红票'">红票金额</th>
								
								<th>含税金额</th>
								<th>交付日期</th>
								<th>最晚交付日期</th>
								<th>交付地址</th>
								<th><span style="display:inline-block;width:80px;">操作</span></th>
                                 </tr>
                             </thead>
                             <tbody>
                                 <tr ng-repeat="_orderMateriel in orderMateriel track by $index" ng-mouseover="showOperation('orderMateriel',$index)" ng-mouseleave="hideOperation('orderMateriel',$index)"  repeat-done="repeatDone(this)">
		                          <td>
                                           <!-- <span ng-hide="orderMaterielInput{{$index}}"><a href="javascript：;" ng-click="addMateriel('single',$index)">{{_orderMateriel.materiel.materielNum}}</a></span> -->
		                                <p class="form-control-static" > {{_orderMateriel.materiel.materielNum}} </p>
		                          </td>
		                          <td ng-if="saleFrame.tradeType =='外贸'">
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
		                          		<input style="padding:6px 3px" type="text"  name="amount{{$index}}" class="form-control" ng-hide="orderMaterielInput{{$index}}" ng-model="orderMateriel[$index].amount" ng-keyup="clearNoNum(orderMateriel[$index],'amount')" >
                                     	<p class="form-control-static" ng-hide="orderMaterielInput{{$index}}"> 当前库存：{{_orderMateriel.materiel.stockCount}} </p>
                                     		<p class="form-control-static" ng-show="orderMaterielShow{{$index}}"> {{_orderMateriel.amount}} </p>
		                          </td>
		                          <td>  
                                     		<p class="form-control-static" > {{_orderMateriel.materiel.unitPrice}} </p>
		                          </td>
		                          <td>  
		                          		<input style="padding:6px 3px" type="text"  name="orderRateUnit{{$index}}" class="form-control" ng-hide="orderMaterielInput{{$index}}" ng-model="orderMateriel[$index].orderRateUnit" 
		                          		 ng-keyup="" ng-change="clearNoNumPoint(orderMateriel[$index],'orderRateUnit');_arithmeticUnitPrice(_orderMateriel)">
                                     		<p class="form-control-static" ng-show="orderMaterielShow{{$index}}" ng-if="_orderMateriel.materiel.unitPrice!=_orderMateriel.orderRateUnit" style="color:red"> {{_orderMateriel.orderRateUnit}} </p>
                                     		<p class="form-control-static" ng-show="orderMaterielShow{{$index}}" ng-if="_orderMateriel.materiel.unitPrice==_orderMateriel.orderRateUnit"> {{_orderMateriel.orderRateUnit}} </p>
		                          </td>
		                          <td>  
		                          		<input style="padding:6px 3px" type="text"  name="orderUnitPrice{{$index}}" class="form-control" ng-hide="orderMaterielInput{{$index}}" ng-model="orderMateriel[$index].orderUnitPrice"  
		                          		ng-keyup="" ng-change="clearNoNumPoint(orderMateriel[$index],'orderUnitPrice');_arithmeticRateUnit(_orderMateriel)">
                                     		<p class="form-control-static" ng-show="orderMaterielShow{{$index}}"> {{format2Thousands(_orderMateriel.orderUnitPrice)}} </p>
		                          </td>
		                          <td>  
                                     		<p class="form-control-static" > {{saleFrame.currency}} </p>
		                          </td>
		                          <td>  
                                     		<p class="form-control-static" > {{saleFrame.rate}}% </p>
		                          </td>
		                          <td>  
                                     		<p class="form-control-static" > {{format2Thousands(_arithmeticAmount(this))}} </p>
		                          </td>
		                          <td>  
                                     		<p class="form-control-static"> {{format2Thousands(_arithmeticRateAmount(this))}} </p>
		                          </td>
		                          <td ng-if="saleFrame.tradeType =='外贸'">
		                          		<input style="padding:6px 3px" type="text"  name="customsRate{{$index}}" class="form-control" ng-hide="orderMaterielInput{{$index}}" ng-model="orderMateriel[$index].customsRate" ng-keyup="clearNoNumPoint(orderMateriel[$index],'customsRate')"  >
                                     		<p class="form-control-static" ng-show="orderMaterielShow{{$index}}"> {{_orderMateriel.customsRate}}% </p>
		                          </td>
								  <td ng-if="saleFrame.tradeType =='外贸'">
								  		<p class="form-control-static" > {{format2Thousands(_arithmeticCustomsRateAmount(this))}} </p>
								  </td>
		                          <td ng-if="saleFrame.settlementClause =='服务费'">
		                          	<input style="padding:6px 3px" type="text"  name="serviceRate{{$index}}" class="form-control" ng-hide="orderMaterielInput{{$index}}" ng-model="orderMateriel[$index].serviceRate"   ng-keyup="clearNoNumPoint(orderMateriel[$index],'serviceRate')" >
                                     	<p class="form-control-static" ng-show="orderMaterielShow{{$index}}"> {{_orderMateriel.serviceRate}} </p>
		                          </td>
								<td ng-if="saleFrame.settlementClause =='服务费'">服务费</td>
								<td ng-if="saleFrame.settlementClause =='折扣折让'">
									<input style="padding:6px 3px" type="text"  name="discountRate{{$index}}" class="form-control" ng-hide="orderMaterielInput{{$index}}" ng-model="orderMateriel[$index].discountRate" ng-keyup="clearNoNumPoint(orderMateriel[$index],'discountRate')" >
                                     	<p class="form-control-static" ng-show="orderMaterielShow{{$index}}"> {{_orderMateriel.discountRate}} </p>
								</td>
								<td ng-if="saleFrame.settlementClause =='折扣折让'">折后金额</td>
								<td ng-if="saleFrame.settlementClause =='红票'">
									<input style="padding:6px 3px" type="text"  name="redTicket{{$index}}" class="form-control" ng-hide="orderMaterielInput{{$index}}" ng-model="orderMateriel[$index].redTicket" ng-keyup="clearNoNumPoint(orderMateriel[$index],'redTicket')"  >
                                     	<p class="form-control-static" ng-show="orderMaterielShow{{$index}}"> {{_orderMateriel.redTicket}} </p>
								</td>
								
		                          <td>  
                                     		<p class="form-control-static"> {{format2Thousands(_arithmeticRateAndAmount(this))}} </p>
		                          </td>
		                          <td>  
		                          		<input type="text"  style="width: 100px!important" name="deliveryDate{{$index}}" class="form-control form-control-inline input-medium date-picker" 
                                     data-date-format="yyyy-mm-dd" data-date-viewmode="years" size="16" ng-hide="orderMaterielInput{{$index}}" ng-model="orderMateriel[$index].deliveryDate"  ng-change="setAllDeliveryDate(_orderMateriel,$index)">
                                     		<p class="form-control-static" ng-show="orderMaterielShow{{$index}}"> {{_orderMateriel.deliveryDate}} </p>
		                          </td>
		                          <td>  
		                          		<input type="text"  style="width: 100px!important" name="lastDeliveryDate{{$index}}" class="form-control form-control-inline input-medium date-picker" 
                                     data-date-format="yyyy-mm-dd" data-date-viewmode="years" size="16" ng-hide="orderMaterielInput{{$index}}" ng-model="orderMateriel[$index].lastDeliveryDate"  ng-change="setAllLastDeliveryDate(_orderMateriel,$index)" >
                                     		<p class="form-control-static" ng-show="orderMaterielShow{{$index}}"> {{_orderMateriel.lastDeliveryDate}} </p>
		                          </td>
		                          <td>  
		                          		<select  ng-if="$first" name="deliveryAddress{{$index}}" ng-hide="orderMaterielInput{{$index}}" ng-model="orderMateriel[$index].deliveryAddress" class="bs-select form-control order" data-live-search="true"  ng-init="warehouses[0].serialNum" ng-change="setAllDeliveryAddress(_orderMateriel)"  data-size="8">
                                              <!--  <option value=""></option> -->
                                               <option  ng-repeat="warehouse in warehouses" value="{{warehouse.warehouseName}}">{{warehouse.warehouseName}}</option>
                                         </select>
                                         <select  ng-if="!$first" name="deliveryAddress{{$index}}" ng-hide="orderMaterielInput{{$index}}" ng-model="orderMateriel[$index].deliveryAddress" class="bs-select form-control order" data-live-search="true"  ng-init="warehouses[0].serialNum" data-size="8">
                                              <!--  <option value=""></option> -->
                                               <option  ng-repeat="warehouse in warehouses" value="{{warehouse.warehouseName}}">{{warehouse.warehouseName}}</option>
                                         </select>
                                     		<p class="form-control-static" ng-show="orderMaterielShow{{$index}}"> {{_orderMateriel.deliveryAddress}} </p>
		                          </td>
                                     
                                     <td>
                                     	<div style="width:100px">
                                     	<span ng-hide="orderMaterielInput{{$index}}">
                                      		<!-- &nbsp;&nbsp;&nbsp;&nbsp;
                                       	<a  ng-click="saveOrderMateriel(_orderMateriel,$index)" title="保存"><i class="fa fa-save"></i></a> -->
                                       	&nbsp;&nbsp;&nbsp;
                                       	<a  ng-click="cancelOrderMateriel(_orderMateriel,$index)" title="取消"><i class="fa fa-undo"></i></a>
                                       </span>
                                       <span  ng-show="operation_o{{$index}}&&noShow">
                                       	&nbsp;&nbsp;&nbsp;&nbsp;
                                       	<a ng-show="orderMaterielShow{{$index}}"   title="编辑" ng-click="editOrderMateriel(_orderMateriel)"><i class="fa fa-edit"></i></a>
                                       	&nbsp;&nbsp;&nbsp;
                                       	<a ng-show="orderMaterielShow{{$index}}"  title="删除" ng-click="deleteOrderMateriel(_orderMateriel)"><i class="fa fa-minus"></i></a>
                                      	</span>
                                      	</div>
                                     </td>
                                 </tr>
                                 <tr>
								<th></th>
								<th ng-if="saleFrame.tradeType =='外贸'"></th>
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
								<th ng-if="saleFrame.tradeType =='外贸'"></th>
								<th ng-if="saleFrame.tradeType =='外贸'">{{format2Thousands(totalCustomsRateAmount())}}</th>
								<th ng-if="saleFrame.settlementClause =='服务费'">服务费率</th>
								<th ng-if="saleFrame.settlementClause =='服务费'">服务费</th>
								<th ng-if="saleFrame.settlementClause =='折扣折让'">折扣率</th>
								<th ng-if="saleFrame.settlementClause =='折扣折让'">折后金额</th>
								<th ng-if="saleFrame.settlementClause =='红票'">红票金额</th>
								
								<th>{{format2Thousands(totalRateAndAmount())}}</th>
								<th></th>
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
            <div class="portlet-title" style="min-height: 48px;">
              <div class="tools" style="float:right">
                        	<button type="submit" ng-click="saveClauseSettlement()" ng-hide="clauseSettlementInput" class="btn green  btn-circle  btn-sm">
                           		<i class="fa fa-save"></i> 保存 </button>
                           <button ng-click="cancelClauseSettlement()" type="button" ng-hide="clauseSettlementInput" class="btn defualt  btn-circle  btn-sm">
                           		<i class="fa fa-undo"></i> 取消 </button>
                           <button ng-click="editClauseSettlement()" type="button" ng-show="clauseSettlementShow&&noShow" class="btn purple  btn-circle  btn-sm">
                           		<i class="fa fa-edit"></i> 编辑 </button>
                         </div>
                    </div>
         <div class="portlet-body form">
             <!-- BEGIN FORM-->
             <!-- <form action="#" id="form_clauseSettlement"  >
                 <div class="form-body">
                     <div class="alert alert-danger display-hide">
                         <button class="close" data-close="alert"></button> 请先输入正确数据！ </div>
                     <div class="row">
                         <div class="col-md-4">
                         		<div class="form-group ">
                              	<label class="control-label bold">含税金额：</label>
                                 <div class="">
                                  <p class="form-control-static"> {{getCurrencySymbol()}}{{format2Thousands(totalRateAndAmount())}} </p>
                                  </div>
                              </div>
                         </div>
                         <div class="col-md-4">
                             <div class="form-group ">
                                 <label class="control-label bold">不含税金额：</label>
                                 <div class="">
                                 <p class="form-control-static"> {{getCurrencySymbol()}}{{format2Thousands(totalAmount())}} </p>
                                 </div>
                             </div>
                         </div>
                         /span
                         <div class="col-md-4">
                         		<div class="form-group ">
                              	<label class="control-label bold">税额(含关税)：</label>
                                 <div class="">
                                  <p class="form-control-static">{{getCurrencySymbol()}}{{format2Thousands(totalRateAndCustomsAmount())}}</p>
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
                                 <label class="control-label bold">其他费用：</label>
                                 <div class="">
                                 		<input type="text" name="otherAmount" class="form-control" ng-hide="clauseSettlementInput" ng-model="clauseSettlement.otherAmount" ng-keyup="clearNoNumPoint(clauseSettlement,'otherAmount')" >
                                     <div class="form-control-focus"> </div>
                                     <span class="help-block" ng-hide="clauseSettlementInput">请输入其他费用</span>
                                     <p class="form-control-static" ng-show="clauseSettlementShow"> {{getCurrencySymbol()}}{{clauseSettlement.otherAmount}} </p>
                              		</div>
                             </div>
                         </div>
                         <div class="col-md-4">
                             <div class="form-group ">
                                 <label class="control-label bold">订单金额：</label>
                                    <div class="">
                                 <p class="form-control-static"> {{getCurrencySymbol()}}{{format2Thousands(totalOrderAmount())}} </p>
                                 </div>
                             </div>
                         </div>
                         /span
                         <div class="col-md-4">
                             <div class="form-group ">
                                 <label class="control-label bold">备注：</label>
                                 <div class="">
                                 		<input type="text" name="clauseSettlementRemark" class="form-control" ng-hide="clauseSettlementInput" ng-model="clauseSettlement.remark"  >
                                     <div class="form-control-focus"> </div>
                                     <span class="help-block" ng-hide="clauseSettlementInput">请输入备注</span>
                                     <p class="form-control-static" ng-show="clauseSettlementShow"> {{clauseSettlement.remark}} </p>
                              		</div>
                             </div>
                         </div>
                         /span
                     </div>
                     /row
                 </div>
			</form> -->
			<form id="form_sample_3"   >
		         <div class="table-scrollable">
                         <table class="table table-bordered table-hover">
                             <thead>
                                 <tr>
                                     <th style="width:150px">支付类型</th>
                                     <th style="width:150px">支付节点</th>
                                     <th style="width:70px">账期（天）</th>
                                     <th style="width:50px">支付比率%</th>
                                     <th style="width:150px">支付金额{{arithmeticAllDeliveryAmount()}}</th>
                                     <th style="width:150px">支付方式</th>
                                     <th style="width:150px">开票方式</th>
                                     <!-- <th style="width:150px">开票金额</th>
                                     <th style="width:150px">未开金额</th> -->
                                     <th style="width:150px">备注</th>
                                     <th style="width:80px;"><span style="display:inline-block;width:80px;"></span></th>
                                 </tr>
                             </thead>
                             <tbody>
                                 <tr ng-repeat="_CSD in clauseSettlement.CSD track by $index" ng-mouseover="showOperation('csd',$index)" ng-mouseleave="hideOperation('csd',$index)">
                                     <td>
		                                <select  id="paymentType[$index]" name="paymentType" class="form-control" ng-hide="clauseSettlementInput" ng-model="clauseSettlement.CSD[$index].paymentType"  >
                                             <option value=""></option>
                                            	<option value="预付款" >预付款</option>
                                              <option value="中期款" >中期款</option>
                                              <option value="尾款" >尾款</option>
                                              <option value="全款" >全款</option>
                                            </select>
		                                <p class="form-control-static" ng-show="clauseSettlementShow"> {{_CSD.paymentType}} </p>
		                          </td>
		                          <td>
                                     		<select  id="deliveryNode[$index]" name="deliveryNode" class="form-control" ng-hide="clauseSettlementInput" ng-model="clauseSettlement.CSD[$index].deliveryNode"  >
		                                	<option value=""></option>
                                            	<option value="合同签订" >合同签订</option>
                                              <option value="提货前" >提货前</option>
                                              <option value="发货后" >发货后</option>
                                              <option value="收货后" >收货后</option>
                                              <option value="验收后" >验收后</option>
                                              <option value="入库后" >入库后</option>
                                              <option value="出库后" >出库后</option>
                                              <option value="质保期满" >质保期满</option>
                                              <option value="收到委托方付款后" >收到委托方付款后</option>
                                              </select>
		                                <p class="form-control-static" ng-show="clauseSettlementShow"> {{_CSD.deliveryNode}} </p>
		                          </td>
                                     <td>
                                     		<input type="text" id="accountPeriod[$index]" name="accountPeriod" class="form-control" ng-hide="clauseSettlementInput" ng-model="clauseSettlement.CSD[$index].accountPeriod" 
                                     		 ng-keyup="clearNoNum(clauseSettlement.CSD[$index],'accountPeriod')" >
		                                <p class="form-control-static" ng-show="clauseSettlementShow"> {{_CSD.accountPeriod}} </p>
		                          </td>
		                          <td>
                                     		<input type="text" id="deliveryRate[$index]" name="deliveryRate" class="form-control" ng-hide="clauseSettlementInput" ng-model="clauseSettlement.CSD[$index].deliveryRate" 
                                     		ng-keyup="clearNoNumPoint(clauseSettlement.CSD[$index],'deliveryRate');_arithmeticDeliveryAmount(this)" >
		                                <p class="form-control-static" ng-show="clauseSettlementShow"> {{_CSD.deliveryRate}} </p>
		                          </td>
		                          <td>
                                     		<input type="text" id="deliveryAmount[$index]" name="deliveryAmount" class="form-control" ng-hide="clauseSettlementInput" ng-model="clauseSettlement.CSD[$index].deliveryAmount"  
                                     		ng-keyup="clearNoNumPoint(clauseSettlement.CSD[$index],'deliveryAmount');_arithmeticRate(this)" >
		                                <p class="form-control-static" ng-show="clauseSettlementShow"> {{format2Thousands(_CSD.deliveryAmount)}} </p>
		                          </td>
		                          <td>
                                     		<select  id="paymentMethod[$index]" name="paymentMethod" class="form-control" ng-hide="clauseSettlementInput" ng-model="clauseSettlement.CSD[$index].paymentMethod"  >
		                                <option value=""></option>
                                            	<option ng-if="saleFrame.tradeType =='外贸'" value="T" >T</option>
                                              <option ng-if="saleFrame.tradeType =='外贸'" value="TL" >TL</option>
                                              <option ng-if="saleFrame.tradeType =='外贸'" value="C" >C</option>
                                              
                                              <option ng-if="saleFrame.tradeType =='内贸'" value="电汇" >电汇</option>
                                              <option ng-if="saleFrame.tradeType =='内贸'" value="银行承兑" >银行承兑</option>
                                              <option ng-if="saleFrame.tradeType =='内贸'" value="商业承兑" >商业承兑</option>
                                              </select>
		                                <p class="form-control-static" ng-show="clauseSettlementShow"> {{_CSD.paymentMethod}} </p>
		                          </td>
		                          <td>
                                     		<select  id="billingMethod[$index]" name="billingMethod" class="form-control" ng-hide="clauseSettlementInput" ng-model="clauseSettlement.CSD[$index].billingMethod"  >
		                                <option value=""></option>
                                            	<option value="先票后款" >先票后款</option>
                                              <option value="先款后票" >先款后票</option>
                                              </select>
		                                <p class="form-control-static" ng-show="clauseSettlementShow"> {{_CSD.billingMethod}} </p>
		                          </td>
		                          <!-- <td>
                                     		<input type="text" id="billingAmount[$index]" name="billingAmount" class="form-control" ng-hide="clauseSettlementInput" ng-model="clauseSettlement.CSD[$index].billingAmount"  
                                     		ng-keyup="clearNoNumPoint(clauseSettlement.CSD[$index],'billingAmount');_arithmeticUnbilledAmount(this)">
		                                <p class="form-control-static" ng-show="clauseSettlementShow"> {{_CSD.billingAmount}} </p>
		                          </td>
		                          <td>
                                     		<input type="text" id="unbilledAmount[$index]" name="unbilledAmount" class="form-control" ng-hide="clauseSettlementInput" ng-model="clauseSettlement.CSD[$index].unbilledAmount" 
                                     		readonly >
		                                <p class="form-control-static" ng-show="clauseSettlementShow"> {{_CSD.unbilledAmount}} </p>
		                          </td> -->
		                          <td>
                                     		<input type="text" id="remark[$index]" name="remark" class="form-control" ng-hide="clauseSettlementInput" ng-model="clauseSettlement.CSD[$index].remark"  >
		                                <p class="form-control-static" ng-show="clauseSettlementShow"> {{_CSD.remark}} </p>
		                          </td>
                                     <td ng-show="operation_csd{{$index}}">
                                     	<a href="javascript:;"  class="btn red" ng-hide="clauseSettlementInput" ng-click="deleteCSD($index)">
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
	</div>
	<div class="tab-pane fade " id="tab_1_6">
		<!--验收条款start-->
            <div class="portlet-title" style="min-height: 48px;">
              <div class="tools" style="float:right">
                        	<button type="submit" ng-click="saveClauseCheckAccept()" ng-hide="clauseCheckAcceptInput" class="btn green  btn-circle  btn-sm">
                           		<i class="fa fa-save"></i> 保存 </button>
                           <button ng-click="cancelClauseCheckAccept()" type="button" ng-hide="clauseCheckAcceptInput" class="btn defualt  btn-circle  btn-sm">
                           		<i class="fa fa-undo"></i> 取消 </button>
                           <button ng-click="editClauseCheckAccept()" type="button" ng-show="clauseCheckAcceptShow&&noShow" class="btn purple  btn-circle  btn-sm">
                           		<i class="fa fa-edit"></i> 编辑 </button>
                         </div>
                    </div>
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
                                 <label class="control-label bold"><span class="required" aria-required="true"> * </span>检验类型：</label>
                                 <div class="">
                                 		<select class="form-control" id="checkType"  ng-hide="clauseCheckAcceptInput" name="checkType"  ng-model="clauseCheckAccept.checkType" >
                                          	<option value=""></option>
                                            <option value="免检">免检</option>
                                            <option value="巡检" >巡检</option>
                                            <option value="抽检" >抽检</option>
                                            <option value="全检" >全检</option>
                                       </select>
                                     <div class="form-control-focus"> </div>
                                     <span class="help-block" ng-hide="clauseCheckAcceptInput">请选择检验类型</span>
                                     <p class="form-control-static" ng-show="clauseCheckAcceptShow"> {{clauseCheckAccept.checkType}} </p>
                                 </div>
                             </div>
                         </div>
                     	<div class="col-md-4">
                             <div class="form-group ">
                                 <label class="control-label bold"><span class="required" aria-required="true"> * </span>检验方：</label>
                                 <div class="">
                                 		<input type="text" name="checkParty" class="form-control" ng-hide="clauseCheckAcceptInput" ng-model="clauseCheckAccept.checkParty"  >
                                     <div class="form-control-focus"> </div>
                                     <span class="help-block" ng-hide="clauseCheckAcceptInput">请输入检验方</span>
                                     <p class="form-control-static" ng-show="clauseCheckAcceptShow"> {{clauseCheckAccept.checkParty}} </p>
                              		</div>
                             </div>
                         </div>
                         <div class="col-md-4">
                             <div class="form-group ">
                                 <label class="control-label bold">预约检验日期：</label>
                                 <div class="">
                                 <input type="text" name="playCheckDate" class="form-control form-control-inline input-medium date-picker" 
                                     data-date-format="yyyy-mm-dd" data-date-viewmode="years" size="16" ng-hide="clauseCheckAcceptInput" ng-model="clauseCheckAccept.playCheckDate" >
                                     <div class="form-control-focus"> </div>
                                     <span class="help-block" ng-hide="clauseCheckAcceptInput">请选择预约检验日期</span>
                                     <p class="form-control-static" ng-show="clauseCheckAcceptShow"> {{clauseCheckAccept.playCheckDate}} </p>
                                 </div>
                                 
                             </div>
                         </div>
                         
                     </div>
                     <div class="row">
                         <!--/span-->
                         <div class="col-md-4">
                             <div class="form-group ">
                                 <label class="control-label bold">验收标准：</label>
                                 <div class="">
                                 		<input type="text" name="acceptStandard" class="form-control" ng-hide="clauseCheckAcceptInput" ng-model="clauseCheckAccept.acceptStandard"  >
                                     <div class="form-control-focus"> </div>
                                     <span class="help-block" ng-hide="clauseCheckAcceptInput">请输入验收标准</span>
                                     <p class="form-control-static" ng-show="clauseCheckAcceptShow"> {{clauseCheckAccept.acceptStandard}} </p>
                              		</div>
                             </div>
                         </div>
                         <div class="col-md-4">
                             <div class="form-group ">
                                 <label class="control-label bold">技术文件：</label>
                                 <div class="">
                                 		<input type="text" name="technicalFile" class="form-control" ng-hide="clauseCheckAcceptInput" ng-model="clauseCheckAccept.technicalFile"  >
                                     <div class="form-control-focus"> </div>
                                     <span class="help-block" ng-hide="clauseCheckAcceptInput">请输入技术文件</span>
                                     <p class="form-control-static" ng-show="clauseCheckAcceptShow"> {{clauseCheckAccept.technicalFile}} </p>
                              		</div>
                             </div>
                         </div>
                         <div class="col-md-4">
                             <div class="form-group ">
                                 <label class="control-label bold">备注：</label>
                                 <div class="">
                                 		<input type="text" name="remark" class="form-control" ng-hide="clauseCheckAcceptInput" ng-model="clauseCheckAccept.remark"  >
                                     <div class="form-control-focus"> </div>
                                     <span class="help-block" ng-hide="clauseCheckAcceptInput">请输入备注</span>
                                     <p class="form-control-static" ng-show="clauseCheckAcceptShow"> {{clauseCheckAccept.remark}} </p>
                              		</div>
                             </div>
                         </div>
                         <!--/span-->
                         <!-- <div class="col-md-4">
                             <div class="form-group ">
                                 <label class="control-label bold">补充说明：</label>
                                 <div class="">
                                 		<input type="text" name="clauseCheckAcceptRemark" class="form-control" ng-hide="clauseCheckAcceptInput" ng-model="clauseCheckAccept.remark"  >
                                     <div class="form-control-focus"> </div>
                                     <span class="help-block" ng-hide="clauseCheckAcceptInput">请输入补充说明</span>
                                     <p class="form-control-static" ng-show="clauseCheckAcceptShow"> {{clauseCheckAccept.remark}} </p>
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
            <div class="portlet-title" style="min-height: 48px;">
              <div class="tools" style="float:right">
                        	<button type="submit" ng-click="saveClauseDelivery()" ng-hide="clauseDeliveryInput" class="btn green  btn-circle  btn-sm">
                           		<i class="fa fa-save"></i> 保存 </button>
                           <button ng-click="cancelClauseDelivery()" type="button" ng-hide="clauseDeliveryInput" class="btn defualt  btn-circle  btn-sm">
                           		<i class="fa fa-undo"></i> 取消 </button>
                           <button ng-click="editClauseDelivery()" type="button" ng-show="clauseDeliveryShow&&noShow" class="btn purple  btn-circle  btn-sm">
                           		<i class="fa fa-edit"></i> 编辑 </button>
                         </div>
                    </div>
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
                                 <label class="control-label bold"><span class="required" aria-required="true"> * </span>送货方式：</label>
                                 <div class="">
                                 		<select class="form-control" id="deliveryMode"  ng-hide="clauseDeliveryInput" name="deliveryMode"  ng-model="clauseDelivery.deliveryMode" >
                                           <option value=""></option>
                                          	<option value="自提" >自提</option>
                                            <option value="配送" >配送</option>
                                       </select>
                                       <div class="form-control-focus"> </div>
                             			<span class="help-block" ng-hide="clauseDeliveryInput">请选择送货方式</span>
                                     <p class="form-control-static" ng-show="clauseDeliveryShow"> {{clauseDelivery.deliveryMode}} </p>
                                 </div>
                             </div>
                         </div>
	                                             
					 <div class="col-md-4">
							<div class="form-group">
							<label class="control-label bold"><span ng-if="clauseDelivery.deliveryMode=='自提'">提货</span><span ng-if="clauseDelivery.deliveryMode=='配送'">收货</span>地址<span ng-hide="span"
								class="required" aria-required="true"> * </span></label>
											
																  	<div class="" >
													<div class="input-group"  >
													<input type="text" name="warehouseAddress"
															class="form-control"
															ng-model="clauseDelivery.warehouseAddress"    ng-if="showSXf!='1'"  ng-hide="clauseDeliveryInput"/>
															<div  ng-show="showSXf=='1'">
																<select class="form-control"   data-live-search="true" data-size=""   
															name="warehouseAddress1"
															ng-model="clauseDelivery.warehouseAddress"
															 >
															<option ng-repeat="item in warehouseAddresses"
																value="{{item.address}}">{{item.address}}</option>
															<option value=""></option>
														</select>
														</div>
															<span ng-hide="clauseDeliveryInput"  class="input-group-btn" ng-click="showSX('f')"
																style="vertical-align: top;">
																<button class="btn default" type="button"  ng-if="showSXf!='1'">
																	筛选
																</button>
																	<button class="btn default" type="button"  ng-if="showSXf=='1'">
																	输入
																</button>
															</span>
														</div>
														</div>
														<div class="form-control-focus"></div>
														<p class="form-control-static" ng-show="clauseDeliveryShow">
															{{clauseDelivery.warehouseAddress}}</p>
													
												</div>
											</div>
                         
                         <div class="col-md-4">
                             <div class="form-group ">
                                 <label class="control-label bold">运输方式：</label>
                                 <div class="">
                                     <select class="form-control" id="transportType"  ng-hide="clauseDeliveryInput" name="transportType"  ng-model="clauseDelivery.transportType" >
                                           <option value=""></option>
                                          	<option value="海运" >海运</option>
                                            <option value="陆运" >陆运</option>
                                            <option value="空运" >空运</option>
                                            <option value="其他" >其他</option>
                                       </select>
                                     <div class="form-control-focus"> </div>
                                     <span class="help-block" ng-hide="clauseDeliveryInput">请选择运输方式</span>
                                     <p class="form-control-static" ng-show="clauseDeliveryShow"> {{clauseDelivery.transportType}} </p>
                              		</div>
                             </div>
                         </div>
                        
                     </div>
                     <div class="row">
                      <!--/span-->
                         <div class="col-md-4">
                             <div class="form-group ">
                                 <label class="control-label bold">包装要求：</label>
                                 <div class="">
                                 		<input type="text" name="packingRequire" class="form-control" ng-hide="clauseDeliveryInput" ng-model="clauseDelivery.packingRequire"  >
                                     <div class="form-control-focus"> </div>
                                     <span class="help-block" ng-hide="clauseDeliveryInput">请输入包装要求</span>
                                     <p class="form-control-static" ng-show="clauseDeliveryShow"> {{clauseDelivery.packingRequire}} </p>
                              		</div>
                             </div>
                         </div>
                         <!--/span-->
                         <!--/span-->
                         <div class="col-md-4">
                             <div class="form-group ">
                                 <label class="control-label bold">包装规格型号：</label>
                                 <div class="">
                                 		<input type="text" name="specifications" class="form-control" ng-hide="clauseDeliveryInput" ng-model="clauseDelivery.specifications"  >
                                     <div class="form-control-focus"> </div>
                                     <span class="help-block" ng-hide="clauseDeliveryInput">请输入规格型号</span>
                                     <p class="form-control-static" ng-show="clauseDeliveryShow"> {{clauseDelivery.specifications}} </p>
                              		</div>
                             </div>
                         </div>
                         <div class="col-md-4">
                             <div class="form-group ">
                                 <label class="control-label bold">备注：</label>
                                 <div class="">
                                 		<input type="text" name="remark" class="form-control" ng-hide="clauseDeliveryInput" ng-model="clauseDelivery.remark"  >
                                     <div class="form-control-focus"> </div>
                                     <span class="help-block" ng-hide="clauseDeliveryInput">请输入备注</span>
                                     <p class="form-control-static" ng-show="clauseDeliveryShow"> {{clauseDelivery.remark}} </p>
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
            <div class="portlet-title" style="min-height: 48px;">
              <div class="tools" style="float:right">
                        	<button type="submit" ng-click="saveClauseAfterSales()" ng-hide="clauseAfterSalesInput" class="btn green  btn-circle  btn-sm">
                           		<i class="fa fa-save"></i> 保存 </button>
                           <button ng-click="cancelClauseAfterSales()" type="button" ng-hide="clauseAfterSalesInput" class="btn defualt  btn-circle  btn-sm">
                           		<i class="fa fa-undo"></i> 取消 </button>
                           <button ng-click="editClauseAfterSales()" type="button" ng-show="clauseAfterSalesShow&&noShow" class="btn purple  btn-circle  btn-sm">
                           		<i class="fa fa-edit"></i> 编辑 </button>
                         </div>
                    </div>
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
                                 <label class="control-label bold">质保要求：</label>
                                 <div class="">
                                 		<input type="text" name="qualityRequirements" class="form-control" ng-hide="clauseAfterSalesInput" ng-model="clauseAfterSales.qualityRequirements"  >
                                     <div class="form-control-focus"> </div>
                                     <span class="help-block" ng-hide="clauseAfterSalesInput">请输入质保要求</span>
                                     <p class="form-control-static" ng-show="clauseAfterSalesShow"> {{clauseAfterSales.qualityRequirements}} </p>
                              		</div>
                             </div>
                         </div>
                         <!--/span-->
                         <div class="col-md-4">
                             <div class="form-group ">
                                 <label class="control-label bold">售后响应：</label>
                                 <div class="">
                                 		<input type="text" name="afterSaleResponse" class="form-control" ng-hide="clauseAfterSalesInput" ng-model="clauseAfterSales.afterSaleResponse"  >
                                     <div class="form-control-focus"> </div>
                                     <span class="help-block" ng-hide="clauseAfterSalesInput">请输入售后响应</span>
                                     <p class="form-control-static" ng-show="clauseAfterSalesShow"> {{clauseAfterSales.afterSaleResponse}} </p>
                              		</div>
                             </div>
                         </div>
                         <!--/span-->
                         <div class="col-md-4">
                             <div class="form-group ">
                                 <label class="control-label bold">补充说明：</label>
                                 <div class="">
                                 		<input type="text" name="clauseAfterRemark" class="form-control" ng-hide="clauseAfterSalesInput" ng-model="clauseAfterSales.remark"  >
                                     <div class="form-control-focus"> </div>
                                     <span class="help-block" ng-hide="clauseAfterSalesInput">请输入补充说明</span>
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
	</div>
                   <div class="tab-pane fade " id="tab_1_9">
                   <!-- 附件 start-->
         <div class="portlet-title" style="min-height: 48px;">
              <div class="tools" style="float:right">
              	 	<button type="submit" ng-click="saveFile()" ng-hide="fileInfoInput" class="btn green  btn-circle  btn-sm">
                 		<i class="fa fa-save"></i> 保存 </button>
                 <button ng-click="cancelFile()" type="button" ng-hide="fileInfoInput" class="btn defualt  btn-circle  btn-sm">
                 		<i class="fa fa-undo"></i> 取消 </button>
                 <button ng-click="editFile()" type="button" ng-show="fileInfoShow&&noShow" class="btn purple  btn-circle  btn-sm">
                 		<i class="fa fa-edit"></i> 编辑 </button>
               </div>
           </div>
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
                                     <th style="width:100px;"></th>
                                 </tr>
                             </thead>
                             <tbody>
                                 <tr ng-repeat="_file in file track by $index" ng-mouseover="showOperation('file',$index)" ng-mouseleave="hideOperation('file',$index)">
		                          <td>
	                                 	<select class="form-control" id="fileType[$index]" name="fileType" class="form-control" ng-hide="fileInfoInput" ng-model="file[$index].fileType"  >
                                             <option value=""></option>
                                            	<option value="物料图片" >物料图片</option>
                                              <option value="图纸" >图纸</option>
                                              <option value="其他附件" >其他附件</option>
                                            </select>
		                                <p class="form-control-static" ng-show="fileInfoShow"> {{_file.fileType}} </p>
		                          </td>
		                           <td>
                                     		<input type="text" id="fileDescribe[$index]" name="fileDescribe" class="form-control" ng-hide="fileInfoInput" ng-model="file[$index].fileDescribe"  >
		                                <p class="form-control-static" ng-show="fileInfoShow"> {{_file.fileDescribe}} </p>
		                          </td>
                                      <td>
                                        <div ng-hide="fileInfoInput"   ng-if="file[$index].file==null||file[$index].file==''"  class="fileinput fileinput-new" data-provides="fileinput">
                                            <span class="btn blue btn-circle btn-file">
                                                <span class="fileinput-new">上传附件</span>
                                                <span class="fileinput-exists">更改</span>
                                                <input type="file" name="..." nv-file-select uploader="uploader" onchange="angular.element(this).scope().up(this.files[0])" ng-model="file[$index].file" ng-click="uploadFile($index)" > </span>
                                            <span class="fileinput-filename">{{_file.file.substring(_file.file.indexOf("_")+1)}}</span> &nbsp;
                                            <a href="javascript:;" class="close fileinput-exists" ng-click="removefile($index)" data-dismiss="fileinput"> </a>
                                        </div>
                                        <div ng-hide="fileInfoInput"   ng-if="file[$index].file!=null&&file[$index].file!=''"  class="fileinput fileinput-exists" data-provides="fileinput">
                                            <span class="btn blue btn-circle btn-file">
                                                <span class="fileinput-new">上传附件</span>
                                                <span class="fileinput-exists">更改</span>
                                                <input type="file" name="..." nv-file-select uploader="uploader" onchange="angular.element(this).scope().up(this.files[0])" ng-model="file[$index].file" ng-click="uploadFile($index)" > </span>
                                            <span class="fileinput-filename">{{_file.file.substring(_file.file.indexOf("_")+1)}}</span> &nbsp;
                                            <a href="javascript:;" class="close fileinput-exists"  ng-click="removefile($index)" data-dismiss="fileinput"> </a>
                                        </div>
                                      	<label   ng-show="fileInfoShow" ng-if="file[$index].file==null||file[$index].file==''" class="c_edit" >未上传附件</label>
                                      	<label   ng-show="fileInfoShow" ng-if="file[$index].file!=null&&file[$index].file!=''" class="c_edit" ><a href="javascript:;" ng-click="downloadFile(file[$index])">{{_file.file.substring(_file.file.indexOf("_")+1)}}</a></label>
                                     		<!-- <input type="text" name="file[$index]" name="file" class="form-control" ng-hide="fileInfoInput" ng-model="file[$index].file"  >
		                                <p class="form-control-static" ng-show="fileInfoShow"> {{_file.file}} </p> -->
		                          </td>
		                           <td>
                                     		<input type="text" name="remark[$index]" name="remark" class="form-control" ng-hide="fileInfoInput" ng-model="file[$index].remark"  >
		                                <p class="form-control-static" ng-show="fileInfoShow"> {{_file.remark}} </p>
		                          </td>
                                     <td><p class="form-control-static"> {{_file.uploader}} </p></td>
                                     <td><p class="form-control-static"> {{_file.uploadDate}} </p></td>
                                     
                                     <td ng-show="operation_f{{$index}}">
                                     	<a href="javascript:;"  class="btn red btn-sm" ng-hide="fileInfoInput" ng-click="deleteFile($index)">
                                   			<i class="fa fa-close"></i> 
                            				</a>
                                     </td>
                                 </tr>
                             </tbody>
                         </table>
                     </div>
                </form>
                     <div class="form-actions right">
						<a  class="btn blue btn-sm"  ng-hide="fileInfoInput" ng-click="addFile()"   >
                              <i class="fa fa-plus"></i> 增加
                       	</a> 
                 		</div>
           </div>
         <!-- 附件 end-->
                   </div>
        			<!-- <div class="tab-pane fade " id="tab_1_10">
        				备注 start
           <div class="portlet-body form">
			     <form >
				     <div class="form-body">
	                      <div class="row">
	                          <div class="col-md-8">
	                          		<div class="form-group ">
		                              	<label class="control-label bold">备注：</label>
		                                <div class="">
		                                  <input type="text" name="remark" ng-hide="orderStatusInput" class="form-control"  ng-model="saleFrame.remark"  >
	                                      <div class="form-control-focus"> </div>
	                                      <span class="help-block" ng-hide="orderStatusInput">请输入备注</span>
	                                      <p class="form-control-static" ng-show="orderStatusShow"> {{saleFrame.remark}} </p>
		                               	</div>
	                               </div>
	                          </div>
	                      </div>
	                      /row
	                  </div>
	                </form>
            </div>
          备注 end
        			</div> -->
        
        
        
     
     <!--垫资条款start-->
        <!-- <div class="portlet-title">
                        <div class="caption">垫资条款</div>
                        <div class="tools" id="noprintdiv">
                        	<button type="submit" ng-click="saveClauseAdvance()" ng-hide="clauseAdvanceInput" class="btn blue  btn-circle  btn-sm">
                           		<i class="fa fa-save"></i> 保存 </button>
                           <button ng-click="cancelClauseAdvance()" type="button" ng-hide="clauseAdvanceInput" class="btn defualt  btn-circle  btn-sm">
                           		<i class="fa fa-undo"></i> 取消 </button>
                           <button ng-click="editClauseAdvance()" type="button" ng-show="clauseAdvanceShow&&noShow" class="btn blue  btn-circle  btn-sm">
                           		<i class="fa fa-edit"></i> 编辑 </button>
                         </div>
                    </div>
      <div class="portlet-body form">
          BEGIN FORM
          <form action="#" id="form_clauseAdvance"  >
              <div class="form-body">
                  <div class="alert alert-danger display-hide">
                      <button class="close" data-close="alert"></button> 请先输入正确数据！ </div>
                  <div class="row">
                      <div class="col-md-4">
                          <div class="form-group ">
                              <label class="control-label bold">融资方：</label>
                              <div class="">
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
                           	<label class="control-label bold">垫资方：</label>
                             <div class="">
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
                              <label class="control-label bold">垫资金额：</label>
                              <div class="">
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
                              <label class="control-label bold">保证金比率：</label>
                              <div class="">
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
                              <label class="control-label bold">保证金金额：</label>
                              <div class="">
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
                              <label class="control-label bold">服务费率：</label>
                              <div class="">
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
                              <label class="control-label bold">垫资时间：</label>
                              <div class="">
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
                              <label class="control-label bold">服务费：</label>
                              <div class="">
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
<!-- 采购订单基本信息 end -->