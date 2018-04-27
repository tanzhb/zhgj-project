<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<!-- <h3 class="page-title"> 企业信息
</h3> -->
<!-- <div class="page-bar">
    <ul class="page-breadcrumb">
        <li>
            <i class="fa fa-home"></i>
            <a ui-sref="dashboard">首页</a>
            <i class="fa fa-angle-right"></i>
        </li>
        <li>
            <a ui-sref="datatablesmanaged">基础数据</a>
            <i class="fa fa-angle-right"></i>
        </li>
        <li>
            <a ui-sref="company">企业信息</a>
        </li>
    </ul>

</div> -->
<!-- END PAGE HEADER-->
<!-- BEGIN MAIN CONTENT -->
<div class="row">
    <div class="col-md-12">
        <!-- BEGIN EXAMPLE TABLE PORTLET-->
        <div class="portlet light">
           <!--  <div class="portlet-body"> -->
				<!-- <div class="portlet light "> -->
				<ul class="nav nav-tabs" id="companyTab">
					<li class="dropdown pull-right tabdrop" ng-hide="basicInfo">
								<button   ng-show="companyView" class="btn purple  btn-sm btn-circle" ng-click="editCompany()">
                                            <i class="fa fa-edit"></i> 编辑 </button>
                                <button   ng-hide="companyAdd" class="btn green  btn-sm btn-circle" ng-click="saveCompany()">
                                            <i class="fa fa-check"></i> 保存 </button>
                                <button   ng-hide="companyAdd" class="btn defualt  btn-sm btn-circle" ng-click="cancelCompany('company')">
                                            <i class="fa fa-mail-reply"></i> 取消 </button>
                                
					</li>
					<li class="dropdown pull-right tabdrop" ng-show="qualificationInfo">
					 			<button   ng-show="companyQualificationView" class="btn purple  btn-sm  btn-circle" ng-click="editCompanyQualification()">
                                            <i class="fa fa-edit"></i> 编辑 </button>
                                <button   ng-hide="companyQualificationAdd" class="btn green btn-sm  btn-circle" ng-click="saveCompanyQualification()">
                                            <i class="fa fa-check"></i> 保存 </button>
                                <button   ng-hide="companyQualificationAdd" class="btn defualt  btn-sm  btn-circle" ng-click="cancelCompany('companyQualification')">
                                            <i class="fa fa-mail-reply"></i> 取消 </button>
					</li>
					<li class="dropdown pull-right tabdrop" ng-show="billInfo">
					 			<button   ng-show="billView" class="btn purple  btn-sm  btn-circle" ng-click="editBillInfo()">
                                            <i class="fa fa-edit"></i> 编辑 </button>
                                <button   ng-hide="billAdd" class="btn green btn-sm  btn-circle" ng-click="saveBillInfo()">
                                            <i class="fa fa-check"></i> 保存 </button>
                                <button   ng-hide="billAdd" class="btn defualt  btn-sm  btn-circle" ng-click="cancelCompany('bill')">
                                            <i class="fa fa-mail-reply"></i> 取消 </button>
					</li>
					<li class="dropdown pull-right tabdrop" ng-show="manage">
					 			<button   ng-show="companyMView" class="btn purple  btn-sm  btn-circle" ng-click="editCompanyManageInfo()">
                                            <i class="fa fa-edit"></i> 编辑 </button>
                                <button   ng-hide="companyMAdd" class="btn green btn-sm  btn-circle" ng-click="saveCompanyManageInfo()">
                                            <i class="fa fa-check"></i> 保存 </button>
                                <button   ng-hide="companyMAdd" class="btn defualt  btn-sm  btn-circle" ng-click="cancelCompany('companyManage')">
                                            <i class="fa fa-mail-reply"></i> 取消 </button>
					</li>
					<li class="dropdown pull-right tabdrop" ng-show="comRelation">
					 			<button   ng-show="CompanyInfoShow" class="btn purple  btn-sm  btn-circle" ng-click="editCompanyRelationInfo()">
                                            <i class="fa fa-edit"></i> 编辑 </button>
                                <button   ng-hide="CompanyInfoInput" class="btn green btn-sm  btn-circle" ng-click="saveCompanyRelationInfo()">
                                            <i class="fa fa-check"></i> 保存 </button>
                                <button   ng-hide="CompanyInfoInput" class="btn defualt  btn-sm  btn-circle" ng-click="cancelCompany('companyRelation')">
                                            <i class="fa fa-mail-reply"></i> 取消 </button>
					</li>
					
					<li class="active bold">
                  	<a data-target="#tab_1_1" data-toggle="tab">企业信息</a>
              		</li>
					<li class="bold"><a data-target="#tab_1_2" data-toggle="tab">资质信息</a>
					</li>
					<li class="bold"><a data-target="#tab_1_3" data-toggle="tab">财务信息</a></li>
					<li class="bold"><a data-target="#tab_1_4" data-toggle="tab">联系方式</a></li>
					<li class="bold"><a data-target="#tab_1_5" data-toggle="tab">管理信息</a></li>
					<li class="bold"  ng-show="company.comType=='2'"><a data-target="#tab_1_6" data-toggle="tab">采购商</a></li>
					<li class="bold"  ng-show="company.comType=='1'"><a data-target="#tab_1_6" data-toggle="tab">供应商</a></li>
				</ul>
				<div class="tab-content">
				<div class="tab-pane fade active in" id="tab_1_1">
						 <div class="portlet-body form">
                            <form  id="companyForm" >
								<div class="form-body">
									<div class="alert alert-danger display-hide">
                                                <button class="close" data-close="alert"></button>请先输入正确数据！</div>
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
                                                    <label class="control-label bold" for="comNum">企业编号 <span class="required"> * </span></label>
                                                    <div class="">
                                                        <input type="text" class="form-control" id="comNum" name="comNum" ng-model="company.comNum" ng-hide="companyAdd" >
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="companyView">{{company.comNum}}</p>
                                                    </div>
                                            </div>
										</div>
						
										<!--/span-->
										<div class="col-md-6">
											<div class="form-group">
                                                    <label class="control-label bold" for="comName">企业名称 <span class="required"> * </span></label>
                                                    <div class="">
                                                        <input type="text" class="form-control" id="comName"  name="comName" ng-model="company.comName" ng-hide="companyAdd" >
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="companyView">{{company.comName}}</p>
                                                    </div>
                                            </div>
										</div>
										<!--/span-->
									</div>
									<!--/row-->
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
                                                    <label class="control-label bold" for="comType">合作类型 <span class="required"> * </span></label>
                                                    <div class="">
                                                         <select class="form-control" id="comType"  ng-hide="companyAdd" name="comType"  ng-model="company.comType" >
                                                            <option value=""></option>
                                                           	<option value="1" >采购商</option>
                                                            <option value="2" >供应商</option>
                                                            <option value="9" >贸易商</option>
                                                            <option value="3" >承运人</option>
                                                            <option value="4" >外协仓</option>
                                                            <!-- <option value="5" >境外供应商</option> -->
                                                            <option value="6" >装卸公司</option>
                                                            <option value="7" >银行</option>
                                                            <option value="8" >保险公司</option>
                                                        </select>
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="companyView">{{company.comTypeName}}</p>
                                                    </div>
                                            </div>
										</div>
										<div class="col-md-3">
				                         	<div class="form-group ">
				                                 <label class="control-label bold"><span class="required" aria-required="true"> * </span>贸易类型：</label>
				                                 <div class="">
					                                 <div ng-hide="companyAdd">
						                               	<input type="radio"  ng-model="company.tradeType" name="tradeType" ng-checked="company.tradeType=='内贸'" value="内贸"> 内贸
						                               	<input type="radio"  ng-model="company.tradeType" name="tradeType" ng-checked="company.tradeType=='外贸'" value="外贸"> 外贸
					                                 </div>
					                                 <p class="form-control-static" ng-show="companyView"> {{company.tradeType}} </p>
				                                 </div>
				                             </div>
				                         </div>
										<!--/span-->
										<div class="col-md-3">
											<div class="form-group ">
												<label class="control-label bold" for="abbreviation">企业简称</label>
												<div class="">
													<input type="text" class="form-control" id="abbreviation" name="abbreviation" ng-model="company.abbreviation" ng-hide="companyAdd" > 
                                                    <div class="form-control-focus"> </div>
                                                    <p class="control-label left" ng-show="companyView">{{company.abbreviation}}</p>
												</div>
											</div>
										</div>
										<!--/span-->
									</div>
									<!--/row-->
									<div class="row">
										<div class="col-md-6">
											<div class="form-group ">
												<label class="control-label bold" for="businessNature">营业性质</label>
												<div class="">
													<select class="form-control" id="businessNature" ng-hide="companyAdd"  ng-model="company.businessNature" >
                                                            <option value=""></option>
                                                           	 <option value="加工制造" >加工制造</option>
                                                             <option value="分销/交易" >分销/交易</option>
                                                             <option value="仓储供应" >仓储供应</option>
                                                             <option value="其他" >其他</option>
                                                    </select>
                                                    <div class="form-control-focus"> </div>
                                                    <p class="control-label left" ng-show="companyView">{{company.businessNature}}</p>
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
                                                    <label class="control-label bold" for="comNature">企业性质</label>
                                                    <div class="">
                                                        <select class="form-control" id="comNature" ng-hide="companyAdd"  ng-model="company.comNature" >
                                                            <option value=""></option>
                                                           	<option value="国有企业" >国有企业</option>
                                                            <option value="民营企业" >民营企业</option>
                                                            <option value="合资企业" >合资企业</option>
                                                            <option value="外资企业" >外资企业</option>
                                                            <option value="混合所有制企业" >混合所有制企业</option>
                                                    	</select>
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="companyView">{{company.comNature}}</p>
                                                    </div>
                                            </div>
										</div>
									</div>
									<!--/row-->
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
                                                    <label class="control-label bold" for="businessType">经营类型</label>
                                                    <div class="">
                                                        <input type="text" class="form-control" id="businessType" ng-model="company.businessType" ng-hide="companyAdd" >
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="companyView">{{company.businessType}}</p>
                                                    </div>
                                            </div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
                                                    <label class="control-label bold" for="registeredCapital">注册资金</label>
                                                    <div class="">
                                                        <input type="text" class="form-control" id="registeredCapital" name="registeredCapital" ng-model="company.registeredCapital" ng-hide="companyAdd" >

                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="companyView">{{company.registeredCapital}}</p>
                                                    </div>
                                            </div>
										</div>
									</div>
									<!--/row-->
							
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
                                                    <label class="control-label bold" for="legalPerson">法定代表人</label>
                                                    <div class="">
                                                        <input type="text" class="form-control" id="legalPerson"ng-model="company.legalPerson" ng-hide="companyAdd" >
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="companyView">{{company.legalPerson}}</p>
                                                    </div>
                                            </div>
										</div>
										<!-- <div class="col-md-6">
											<div class="form-group">
                                                    <label class="control-label bold" for="contact">维护人员 <span class="required"> * </span></label>
                                                    <div class="">
                                                        <input type="text" class="form-control" id="contact" name="contact" ng-model="company.contact" ng-hide="companyAdd" >
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="companyView">{{company.contact}}</p>
                                                    </div>
                                            </div>
										</div> -->
										<div class="col-md-6">
											<div class="form-group">
                                                    <label class="control-label bold" for="remark">备注</label>
                                                    <div class="">
                                                        <input type="text" class="form-control" id="remark" ng-model="company.remark" ng-hide="companyAdd" >
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="companyView">{{company.remark}}</p>
                                                    </div>
                                            </div>
										</div>
									</div>
								</div>
							</form>
         				</div>
				</div>
				<div class="tab-pane fade" id="tab_1_2">
						 <div class="portlet-body form">
                            <form  id="qualificationForm" class="page-repeater form-horizontal">
								<div class="form-body" data-repeater-list="group-a"  >
									<div  class="row" data-repeater-item  ng-repeat="companyQualification in companyQualifications  track by $index" repeat-done="repeatDone()">
										<div class="col-md-2">
											<div class="form-group">
												<div class="col-md-12 input-icon right">
													<input  ng-hide="companyQualificationAdd" type="text" id="qualificationName{{$index}}" ng-model="companyQualification.qualificationName" name="qualificationName"
														class="form-control" placeholder="资质名称"> <span
														class="help-block"></span>
														<label   ng-show="companyQualificationView"  class="c_edit" >{{companyQualification.qualificationName}}</label>
												</div>
											</div>
										</div>
						
										<!--/span-->
										<div class="col-md-2">
											<div class="form-group">
												<div class="col-md-12">
													<input  ng-hide="companyQualificationAdd" type="text" id="qualificationNum{{$index}}" ng-model="companyQualification.qualificationNum" 
														class="form-control" placeholder="资质号码"> <span
														class="help-block"></span>
														<label   ng-show="companyQualificationView"  class="c_edit" >{{companyQualification.qualificationNum}}</label>
												</div>
											</div>
										</div>
										<!--/span-->
										<div class="col-md-2">
											<div class="form-group">
												<div class="col-md-12">
													<div  ng-hide="companyQualificationAdd" class="input-group date date-picker"
														 data-date-format="yyyy-mm-dd"
														data-date-viewmode="years">
														<input type="text" class="form-control" readonly="" style="min-width: 100px;" id="validityDate{{$index}}" ng-model="companyQualification.validityDate" 
															placeholder="有效期"> <span class="input-group-btn">
															<button class="btn default " type="button">
																<i class="fa fa-calendar"></i>
															</button>
														</span>
														<span class="help-block"></span>
													</div>
													<label   ng-show="companyQualificationView" class="c_edit" >{{companyQualification.validityDate}}</label>
												</div>
											</div>
										</div>
										<!-- /span -->
										<div class="col-md-2">
											<div class="form-group">
												<div class="col-md-12">
				                                         <div ng-hide="companyQualificationAdd"   ng-if="companyQualification.qualificatioImage==null||companyQualification.qualificatioImage==''"  class="fileinput fileinput-new" data-provides="fileinput">
	                                                        <span class="btn blue btn-outline btn-file">
	                                                            <span class="fileinput-new">上传附件</span>
	                                                            <span class="fileinput-exists">更改</span>
	                                                            <input type="file" name="..." nv-file-select uploader="uploader" onchange="angular.element(this).scope().up(this.files[0])" ng-model="companyQualification" ng-click="uploadFile(companyQualification,$index)" > </span>
	                                                        <span class="fileinput-filename">{{companyQualification.qualificatioImage.substring(companyQualification.qualificatioImage.indexOf("_")+1)}}</span> &nbsp;
	                                                        <a href="javascript:;" class="close fileinput-exists" id="resetFile{{$index}}" ng-click="removefile(companyQualification)" data-dismiss="fileinput"> </a>
	                                                    </div>
				                                         <div ng-hide="companyQualificationAdd"   ng-if="companyQualification.qualificatioImage!=null&&companyQualification.qualificatioImage!=''"  class="fileinput fileinput-exists" data-provides="fileinput">
	                                                        <span class="btn blue btn-outline btn-file">
	                                                            <span class="fileinput-new">上传附件</span>
	                                                            <span class="fileinput-exists">更改</span>
	                                                            <input type="file" name="..." nv-file-select uploader="uploader" onchange="angular.element(this).scope().up(this.files[0])" ng-model="companyQualification" ng-click="uploadFile(companyQualification,$index)" > </span>
	                                                        <span class="fileinput-filename">{{companyQualification.qualificatioImage.substring(companyQualification.qualificatioImage.indexOf("_")+1)}}</span> &nbsp;
	                                                        <a href="javascript:;" class="close fileinput-exists" id="resetFile{{$index}}"  ng-click="removefile(companyQualification)" data-dismiss="fileinput"> </a>
	                                                    </div>
                                                    	<label   ng-show="companyQualificationView" ng-if="companyQualification.qualificatioImage==null||companyQualification.qualificatioImage==''" class="c_edit" >未上传附件</label>
                                                    	<label   ng-show="companyQualificationView" ng-if="companyQualification.qualificatioImage!=null&&companyQualification.qualificatioImage!=''" class="c_edit" ><a href="javascript:;" title="下载附件" ng-click="downloadFile(companyQualification)">{{companyQualification.qualificatioImage.substring(companyQualification.qualificatioImage.indexOf("_")+1)}}</a></label>
												</div>
											</div>
										</div>
										<!--/span-->
										<div class="col-md-2">
											<div class="form-group">
												<div class="col-md-12">
													<input  ng-hide="companyQualificationAdd" type="text" ng-model="companyQualification.remark" name="remark"
														class="form-control" placeholder="备注"> 
														<label   ng-show="companyQualificationView" class="c_edit" >{{companyQualification.remark}}</label>
												</div>
											</div>
										</div>
										<!--/span-->
										<div class="col-md-1">
											<div class="form-group">
												<div class="col-md-12">
														
												 	<span ng-show="companyQualificationView" ng-if="companyQualification.status=='0'" class="label label-sm label-success">正常</span>
												 	<span ng-show="companyQualificationView" ng-if="companyQualification.status=='1'" class="label label-sm label-danger">已过期</span>
												 	<span ng-show="companyQualificationView" ng-if="companyQualification.status=='2'" class="label label-sm label-warning">即将过期</span>
												</div>
											</div>
										</div>
										<div class="col-md-1">
											<div class="form-group">
												<div class="col-md-12">
													 <a href="javascript:;"  class="btn red btn-sm" ng-hide="companyQualificationAdd" ng-click="deleteRepeat(companyQualification)">
                                            			<i class="fa fa-close"></i> 
                                     				</a>
												</div>
											</div>
										</div>
										<!--/span--> 
									</div>
									<!-- /row -->
								</div>
								<div class="form-actions right" ng-hide="companyQualificationAdd">
									<a href="javascript:;"  class="btn blue btn-sm" ng-click="addRepeat()" >
                                            <i class="fa fa-plus"></i> 增加
                                     </a>
                                </div>
							</form>
         				</div>
				</div>
				<div class="tab-pane fade" id="tab_1_4">
						<div class="portlet-body form">
								<div class="portlet light ">
												<div class="portlet-title">
													<div class="caption">联系人</div>
													<div class="actions"></div>
												</div>
			                                    <div class="table-scrollable">
			                                        <table class="table table-striped table-bordered table-advance table-hover">
			                                            <thead>
			                                                <tr>
			                                                    <th>姓名</th>
			                                                    <th>职位</th>
			                                                    <th>部门/公司</th>
			                                                    <th>管理职责</th>
			                                                    <th>电话</th>
			                                                    <th>微信</th>
			                                                    <th>邮箱</th>
			                                                    <th>备注</th>
			                                                    <th style="width:65px;">操作</th>
			                                                </tr>
			                                            </thead>
			                                            <tbody  ng-if="companyContacts.length==0">
			                                             	<tr>
			                                                    <td colspan="9" align="center" >暂无数据</td>
			                                                </tr>
			                                            </tbody>
			                                            <tbody ng-repeat="_contact in companyContacts track by $index">
			                                                <tr ng-mouseover="showOperation('contact',$index)" ng-mouseleave="hideOperation('contact',$index)">
			                                                    <td>{{_contact.contactName}}</td>
			                                                    <td>{{_contact.contactTitle}}</td>
			                                                    <td>{{_contact.department}}</td>
			                                                    <td>{{_contact.responsibility}}</td>
			                                                    <td>{{_contact.contactTel}}</td>
			                                                    <td>{{_contact.wechat}}</td>
			                                                    <td>{{_contact.contactEmail}}</td>
			                                                    <td>{{_contact.remark}}</td>
			                                                    <td ng-show="operation_c{{$index}}">
			                                                    	<a ng-click="editCompanyContact(_contact.serialNum)"><i class="fa fa-edit" title="编辑"></i></a>
			                                                    	&nbsp;&nbsp;&nbsp;
			                                                    	<a ng-click="deleteCompanyContact(_contact.serialNum)"><i class="fa fa-trash" title="删除"></i></a>
			                                                    </td>
			                                                </tr>
			                                            </tbody>
			                                        </table>
			                                    </div>
			                                    <div class="form-actions right">
												 	<a  class="btn blue btn-sm"  ng-click="addCompanyContact()"   >
			                                            <i class="fa fa-plus"></i> 增加
			                                     	</a>
			                                	</div>
			                                	<div class="portlet-title">
													<div class="caption">联系地址</div>
													<div class="actions"></div>
												</div>
			                                	 <div class="table-scrollable">
			                                        <table class="table table-striped table-bordered table-advance table-hover">
			                                            <thead>
			                                                <tr>
			                                                 	<!-- <th>仓库名称</th> -->
			                                                    <th>地址</th>
			                                                    <th>邮编</th>
			                                                    <th>电话</th>
			                                                    <th>手机</th>
			                                                    <th>备注</th>
			                                                    <th style="width:65px;">操作</th>
			                                                </tr>
			                                            </thead>
			                                            <tbody  ng-if="companyAddress.length==0">
			                                             	<tr>
			                                                    <td colspan="6" align="center" >暂无数据</td>
			                                                </tr>
			                                            </tbody>
			                                            <tbody ng-repeat="_address in companyAddresses track by $index">
			                                                <tr ng-mouseover="showOperation('address',$index)" ng-mouseleave="hideOperation('address',$index)">
			                                                    <td>{{_address.address}}</td>
			                                                    <td>{{_address.zipCode}}</td>
			                                                    <td>{{_address.contactTel}}</td>
			                                                    <td>{{_address.mobileNum}}</td>
			                                                    <td>{{_address.remark}}</td>
			                                                    <td ng-show="operation_a{{$index}}">
			                                                    	<a ng-click="editCompanyAddress(_address.serialNum)"><i class="fa fa-edit" title="编辑"></i></a>
			                                                    	&nbsp;&nbsp;&nbsp;
			                                                    	<a ng-click="deleteCompanyAddress(_address.serialNum)"><i class="fa fa-trash" title="删除"></i></a>
			                                                    </td>
			                                                </tr>
			                                            </tbody>
			                                        </table>
			                                    </div>
			                                    <div class="form-actions right">
												 	<a  class="btn blue btn-sm"  ng-click="addCompanyAddress()"   >
			                                            <i class="fa fa-plus"></i> 增加
			                                     	</a>
			                                	</div>
                             </div>
                         </div>
				</div>
				<div class="tab-pane fade" id="tab_1_3">
					<div class="portlet light ">
						<div class="portlet-title">
							<div class="caption">开票信息</div>
							<div class="actions"></div>
						</div>
						<div class="portlet-body form">
							<form id="billForm">
								<div class="form-body">
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label bold" for="corporatePresence">企业抬头
												</label>
												<div class="">
													<input type="text" class="form-control" id="corporatePresence"
														ng-model="company.corporatePresence" 
														ng-hide="billAdd">
													<div class="form-control-focus"></div>
													<p class="control-label left" ng-show="billView">{{company.corporatePresence}}</p>
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label bold" for="tel">注册电话</label>
												<div class="">
													<input type="text" class="form-control" id="tel" 
														ng-model="company.tel" ng-hide="billAdd">
													<div class="form-control-focus"></div>
													<p class="control-label left" ng-show="billView">{{company.tel}}</p>
												</div>
											</div>
										</div>
										
									</div>
									<!--/row-->
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label bold" for="address">注册地址
												</label>
												<div class="">
													<input type="text" class="form-control" 
														ng-model="company.address"
														ng-hide="billAdd">
													<div class="form-control-focus"></div>
													<p class="control-label left" ng-show="billView">{{company.address}}</p>
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label bold" for="openingBank">开户行
												</label>
												<div class="">
													<input type="text" class="form-control" id="openingBank"
														ng-model="company.openingBank"
														ng-hide="billAdd">
													<div class="form-control-focus"></div>
													<p class="control-label left" ng-show="billView">{{company.openingBank}}</p>
												</div>
											</div>
										</div>
									</div>
									<!--/row-->
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label bold" for="accountNumber">银行账号</label>
												<div class="">
													<input type="text" class="form-control" id="accountNumber" 
														ng-model="company.accountNumber" ng-hide="billAdd">
													<div class="form-control-focus"></div>
													<p class="control-label left" ng-show="billView">{{company.accountNumber}}</p>
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label bold" for="taxpayeNumber">纳税人识别号
												</label>
												<div class="">
													<input type="text" class="form-control" id="taxpayeNumber"
														 ng-model="company.taxpayeNumber"
														ng-hide="billAdd">
													<div class="form-control-focus"></div>
													<p class="control-label left" ng-show="billView">{{company.taxpayeNumber}}</p>
												</div>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label bold" for="billRemark">备注</label>
												<div class="">
													<input type="text" class="form-control" id="billRemark"
														ng-model="company.billRemark" ng-hide="billAdd">
													<div class="form-control-focus"></div>
													<p class="control-label left" ng-show="billView">{{company.billRemark}}</p>
												</div>
											</div>
										</div>
									</div>
								</div>
							</form>
						</div>
						<div class="portlet-title">
							<div class="caption">收付款信息</div>
							<div class="actions"></div>
						</div>
						<div class="portlet-body form">
							<div class="table-scrollable">
								<table
									class="table table-striped table-bordered table-advance table-hover">
									<thead>
										<tr>
											<th>银行</th>
											<th>户名</th>
											<th>账号</th>
											<th>备注</th>
											<th style="min-width: 60px;max-width: 100px">操作</th>
										</tr>
									</thead>
									<tbody ng-if="companyFinances.length==0">
										<tr>
											<td colspan="5" align="center">暂无数据</td>
										</tr>
									</tbody>
									<tbody ng-repeat="finance in companyFinances">
										<tr ng-mouseover="showOperation('finance',$index)"
											ng-mouseleave="hideOperation('finance',$index)">
											<td>{{finance.openingBank}}</td>
											<td>{{finance.accountName}}</td>
											<td>{{finance.accountNumber}}</td>
											<td>{{finance.remark}}</td>
											<td ng-show="operation_f{{$index}}">
												<!-- <a class="btn btn-circle btn-icon-only btn-default" href="javascript:;" ng-click="editCompanyFinance(finance.serialNum)" title="编辑">
                                                                <i class="icon-wrench"></i>
                                                        </a>
                                                        <a class="btn btn-circle btn-icon-only btn-default" href="javascript:;" ng-click="deleteCompanyFinance(finance.serialNum)" title="删除">
                                                                <i class="icon-trash"></i>
                                                        </a> --> <a
												ng-click="editCompanyFinance(finance.serialNum)"><i
													class="fa fa-edit" title="编辑"></i></a> &nbsp;&nbsp;&nbsp; <a
												ng-click="deleteCompanyFinance(finance.serialNum)"><i
													class="fa fa-trash" title="删除"></i></a>
											</td>
										</tr>
									</tbody>
								</table>
							</div>
							<div class="form-actions right">
								<a class="btn blue btn-sm" ng-click="addCompanyFinance()">
									<i class="fa fa-plus"></i> 增加
								</a>
							</div>
						</div>
					</div>
				</div>
	<div class="tab-pane fade" id="tab_1_5">
					<div class="portlet light ">
						<div class="portlet-body form">
							<form id="billForm">
								<div class="form-body">
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label bold" for="corporatePresence">供应商简称
												</label>
												<div class="">
													<input type="text" class="form-control" id="comShortName"
														ng-model="companyManage.comShortName" 
														ng-hide="companyMAdd">
													<div class="form-control-focus"></div>
													<p class="control-label left" ng-show="companyMView">{{companyManage.comShortName}}</p>
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label bold" for="accendant">维护人员</label>
												<div class="">
													 <div class="btn-group">
                                         <a ng-hide="companyMAdd" href="javascript:;" style="margin:0px 5px 2px 0px;text-transform:none" class="btn btn-xs green"    ng-repeat="data in accendants"  ng-click="getCheckedIds(data.userId,data.userName)">
                           {{data.userName}}<i class="fa fa-close"></i>
                    </a>
                    <a class="btn blue btn-sm" ng-click="addAccendant()"  ng-if="!companyMAdd">
									<i class="fa fa-plus"></i>
								</a>
                                 </div>
													<div class="form-control-focus"></div>
													<p class="control-label left" ng-show="companyMView">
													<a href="javascript:;" style="margin:0px 5px 2px 0px;text-transform:none" class="btn btn-xs green"    ng-repeat="data in companyManage.users"  >
                           {{data.userName}}
                    </a>
													<!-- {{companyManage.accendant}} --></p>
												</div>
											</div>
										</div>
										
									</div>
									<!--/row-->
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label bold" for="type">供应商分类
												</label>
												<div class="">
													<!-- <input type="text" class="form-control" 
														ng-model="companyManage.type"
														ng-hide="billAdd"> -->
														 <select class="form-control" id="type"  ng-hide="companyMAdd" name="type"  ng-model="companyManage.type" >
                                                            <option value=""></option>
                                                           	<option value="1" >一级供应商</option>
                                                            <option value="2" >二级供应商</option>
                                                            <option value="3" >三级供应商</option>
                                                        </select>
													<div class="form-control-focus"></div>
													<p class="control-label left" ng-show="companyMView" ng-if="companyManage.type=='1'">一级供应商</p>
													<p class="control-label left" ng-show="companyMView"  ng-if="companyManage.type=='2'">二级供应商</p>
													<p class="control-label left" ng-show="companyMView"  ng-if="companyManage.type=='3'">三级供应商</p>
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label bold" for="openingBank">供应商等级
												</label>
												<div class="">
													<!-- <input type="text" class="form-control" id="openingBank"
														ng-model="companyManage.grade"
														ng-hide="billAdd"> -->
														<select class="form-control" id="grade"  ng-hide="companyMAdd" name="grade"  ng-model="companyManage.grade" >
                                                            <option value=""></option>
                                                           	<option value="1" >合格供应商</option>
                                                            <option value="2" >预警供应商</option>
                                                            <option value="3" >淘汰供应商</option>
                                                        </select>
													<div class="form-control-focus"></div>
													<p class="control-label left" ng-show="companyMView" ng-if="companyManage.grade=='1'">合格供应商</p>
													<p class="control-label left" ng-show="companyMView"  ng-if="companyManage.grade=='2'">预警供应商</p>
													<p class="control-label left" ng-show="companyMView"  ng-if="companyManage.grade=='3'">淘汰供应商</p>
												</div>
											</div>
										</div>
									</div>
									<!--/row-->
								</div>
							</form>
						</div>
						<!-- <div class="portlet-title">
							<div class="caption">收付款信息</div>
							<div class="actions"></div>
						</div>
						<div class="portlet-body form">
							<div class="table-scrollable">
								<table
									class="table table-striped table-bordered table-advance table-hover">
									<thead>
										<tr>
											<th>银行</th>
											<th>户名</th>
											<th>账号</th>
											<th>备注</th>
											<th style="min-width: 60px;max-width: 100px">操作</th>
										</tr>
									</thead>
									<tbody ng-if="companyFinances.length==0">
										<tr>
											<td colspan="5" align="center">暂无数据</td>
										</tr>
									</tbody>
									<tbody ng-repeat="finance in companyFinances">
										<tr ng-mouseover="showOperation('finance',$index)"
											ng-mouseleave="hideOperation('finance',$index)">
											<td>{{finance.openingBank}}</td>
											<td>{{finance.accountName}}</td>
											<td>{{finance.accountNumber}}</td>
											<td>{{finance.remark}}</td>
											<td ng-show="operation_f{{$index}}">
												<a class="btn btn-circle btn-icon-only btn-default" href="javascript:;" ng-click="editCompanyFinance(finance.serialNum)" title="编辑">
                                                                <i class="icon-wrench"></i>
                                                        </a>
                                                        <a class="btn btn-circle btn-icon-only btn-default" href="javascript:;" ng-click="deleteCompanyFinance(finance.serialNum)" title="删除">
                                                                <i class="icon-trash"></i>
                                                        </a> <a
												ng-click="editCompanyFinance(finance.serialNum)"><i
													class="fa fa-edit" title="编辑"></i></a> &nbsp;&nbsp;&nbsp; <a
												ng-click="deleteCompanyFinance(finance.serialNum)"><i
													class="fa fa-trash" title="删除"></i></a>
											</td>
										</tr>
									</tbody>
								</table>
							</div>
							<div class="form-actions right">
								<a class="btn blue btn-sm" ng-click="addCompanyFinance()">
									<i class="fa fa-plus"></i> 增加
								</a>
							</div>
						</div> -->
					</div>
				</div>
				<div class="tab-pane fade" id="tab_1_6">
						<!-- 采购商/供应商start-->
				          <!-- <div class="portlet-title" style="min-height: 48px;">
				               <div class="tools" style="float:right">
				               	 	<button type="submit" ng-click="saveBuyMateriel()" ng-hide="buyMaterielInfoInput" class="btn green  btn-circle  btn-sm">
				                  		<i class="fa fa-save"></i> 保存 </button>
				                  <button ng-click="cancelBuyMateriel()" type="button" ng-hide="buyMaterielInfoInput" class="btn defualt  btn-circle  btn-sm">
				                  		<i class="fa fa-undo"></i> 取消 </button>
				                  <button ng-click="editBuyMateriel()" type="button" ng-show="buyMaterielInfoShow" class="btn purple  btn-circle  btn-sm">
				                  		<i class="fa fa-edit"></i> 编辑 </button>
				                </div>
				            </div> -->
				           <div class="portlet-body form" ng-if="company.comType=='2'">
							     <form id="form_sample_6" >
							         <div class="table">
				                          <table class="table table-bordered table-hover">
				                              <thead>
				                                  <tr>
				                                      <th>采购商</th>
				                                      <th>采购商编号</th>
				                                       <th>操作</th>
				                                  </tr>
				                              </thead>
				                              <tbody>
				                                  <tr ng-repeat="_buyCompany in supplies track by $index" ng-mouseover="showOperation('_buyCompany',$index)" ng-mouseleave="hideOperation('_buyCompany',$index)" >
							                          <td>
						                                 	<div ng-hide="CompanyInfoInput">
							                                 	<select class="form-control" id="buyComId[$index]"    ng-mouseenter ="repeatDone('buy')"    ng-change="fuzhi('supply',$index)"   name="buyComId" data-live-search="true" data-size="8"  ng-model="supplies[$index].comId"  >
							                                 	 <option value=""></option>
					                                              	<option ng-repeat="_buy in buyCompanys" value="{{_buy.comId}}" >{{_buy.comName}}</option>
					                                             </select>
				                                             </div>
							                                <p class="form-control-static" ng-show="CompanyInfoShow"> {{_buyCompany.comName}} </p>
							                          </td>
							                          <td>
						                                 	<p class="form-control-static" > {{supplies[$index].comNum}} </p>
							                          </td>
							                         <td ng-show="operation_b{{$index}}" >
				                                      	<a href="javascript:;"  class="btn red btn-sm" ng-hide="CompanyInfoInput" ng-click="deleteComRelation('buy',$index)">
				                                    			<i class="fa fa-close"></i> 
				                             				</a>
				                                      </td>
				                                  </tr>
				                              </tbody>
				                          </table>
				                      </div>
				                      <div class="form-actions right">
											<a  class="btn blue btn-sm"  ng-hide="CompanyInfoInput" ng-click="addComRelation('buy')"   >
					                              <i class="fa fa-plus"></i> 增加
					                       	</a> 
				                  		</div>
				                  </form>
				          </div>
				          
				              <div class="portlet-body form" ng-if="company.comType=='1'">
							     <form id="form_sample_6" >
							         <div class="table">
				                          <table class="table table-bordered table-hover">
				                              <thead>
				                                  <tr>
				                                      <th>供应商</th>
				                                      <th>供应商编号</th>
				                                      <th>操作</th>
				                                  </tr>
				                              </thead>
				                              <tbody>
				                                  <tr ng-repeat="_supplyCompany in buyComs track by $index" ng-mouseover="showOperation('_supplyCompany',$index)" ng-mouseleave="hideOperation('_supplyCompany',$index)" >
							                          <td>
						                                 	<div ng-hide="CompanyInfoInput">
							                                 	<select class="form-control" id="supplyComId[$index]" name="supplyComId"  ng-change="fuzhi('buy',$index)"     ng-mouseenter ="repeatDone('supply')"     data-live-search="true" data-size="8"  ng-model="buyComs[$index].comId"  >
							                                 	 <option value=""></option>
					                                              	<option ng-repeat="buy in supplyCompanys"   value="{{buy.comId}}"  >{{buy.comName}}</option><!-- ng-change="fuzhi('buy',$index)"  -->
					                                             </select>
				                                             </div>
							                                <p class="form-control-static" ng-show="CompanyInfoShow"> {{_supplyCompany.comName}} </p>
							                          </td>
							                          <td>
						                                 	<p class="form-control-static" > {{buyComs[$index].comNum}} </p>
							                          </td>
							                       <td ng-show="operation_s{{$index}}">
				                                      	<a href="javascript:;"  class="btn red btn-sm" ng-hide="CompanyInfoInput" ng-click="deleteComRelation('supply',$index)">
				                                    			<i class="fa fa-close"></i> 
				                             				</a>
				                                      </td>
				                                  </tr>
				                              </tbody>
				                          </table>
				                      </div>
				                      <div class="form-actions right">
											<a  class="btn blue btn-sm"  ng-hide="CompanyInfoInput" ng-click="addComRelation('supply')"   >
					                              <i class="fa fa-plus"></i> 增加
					                       	</a> 
				                  		</div>
				                  </form>
				          </div>
				          <!-- 供应商 end-->
						</div>
			</div>
        <!-- END EXAMPLE TABLE PORTLET-->
    <!-- </div> -->
</div>


<!-- 联系人modal START -->
 <div class="modal fade  modal-overflow in" id="contactor" tabindex="-1" role="contactor" aria-hidden="true" data-backdrop="static">
     <div class="modal-dialog" >
         <div class="modal-content">
             <div class="modal-header">
                 <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                 <h4 class="modal-title" >{{title==null?'新建':title}}联系人</h4>
             </div>
             <div class="modal-body">
               <div class="form-body" >
               <div class="alert alert-danger display-hide">
                                                <button class="close" data-close="alert"></button>表单数据未填写完整</div>
               <form id="contactForm"  class="form-horizontal">
                 <div class="row">
                     <div class="col-md-12">
                         <div class="form-group">
                                 <label class="col-md-4 control-label" for="contactName"><span class="required"> * </span>姓名：</label>
                                 <div class="col-md-6">
                                     <input type="text" class="form-control" id="contactName" name="contactName" ng-model="companyContact.contactName"  >
                                     <div class="form-control-focus"> </div>
                                 </div>
                         </div>
                     </div>
                 </div>
                 <!--/row-->
                 <div class="row">
                     <div class="col-md-12">
                         <div class="form-group">
                                 <label class="col-md-4 control-label" for="contactTitle"><span class="required"> * </span>职位：</label>
                                 <div class="col-md-6">
                                     <input type="text" class="form-control" id="contactTitle" name="contactTitle" ng-model="companyContact.contactTitle"  >
                                     <div class="form-control-focus"> </div>
                                 </div>
                         </div>
                     </div>
                 </div>
                 <div class="row">
                     <div class="col-md-12">
                         <div class="form-group">
                                 <label class="col-md-4 control-label" for="department">部门/公司：</label>
                                 <div class="col-md-6">
                                     <input type="text" class="form-control" id="department" ng-model="companyContact.department"  >
                                     <div class="form-control-focus"> </div>
                                 </div>
                         </div>
                     </div>
                 </div>
                 <!--/row-->
                 <div class="row">
                     <div class="col-md-12">
                         <div class="form-group">
                                 <label class="col-md-4 control-label" for="department">管理职责：</label>
                                 <div class="col-md-6">
                                     <input type="text" class="form-control" id="responsibility"  ng-model="companyContact.responsibility"  >
                                     <div class="form-control-focus"> </div>
                                 </div>
                         </div>
                     </div>
                 </div>
                 <!--/row-->
                 <div class="row">
                     <div class="col-md-12">
                         <div class="form-group">
                                 <label class="col-md-4 control-label" for="contactTel"><span class="required"> * </span>电话：</label>
                                 <div class="col-md-6">
                                     <input type="text" class="form-control" id="contactTel" name="contactTel" ng-model="companyContact.contactTel"  >
                                     <div class="form-control-focus"> </div>
                                 </div>
                         </div>
                     </div>
                 </div>
                 <!--/row-->
                 <div class="row">
                     <div class="col-md-12">
                         <div class="form-group">
                                 <label class="col-md-4 control-label" for="wechat">微信：</label>
                                 <div class="col-md-6">
                                     <input type="text" class="form-control" id="wechat"  ng-model="companyContact.wechat"  >
                                     <div class="form-control-focus"> </div>
                                 </div>
                         </div>
                     </div>
                 </div>
                 <!--/row-->
                 <div class="row">
                     <div class="col-md-12">
                         <div class="form-group">
                                 <label class="col-md-4 control-label" for="contactEmail"><span class="required"> * </span>邮箱：</label>
                                 <div class="col-md-6">
                                     <input type="text" class="form-control" id="contactEmail" name="contactEmail" ng-model="companyContact.contactEmail"  >
                                     <div class="form-control-focus"> </div>
                                 </div>
                         </div>
                     </div>
                 </div>
                 <!--/row-->
                 <div class="row">
                     <div class="col-md-12">
                         <div class="form-group">
                                 <label class="col-md-4 control-label" for="remark">备注：</label>
                                 <div class="col-md-6">
                                     <input type="text" class="form-control" id="remark" ng-model="companyContact.remark"  >
                                     <div class="form-control-focus"> </div>
                                 </div>
                         </div>
                     </div>
                 </div>
                 <!--/row-->
                 
                 </form>
             </div>
             </div>
             <div class="modal-footer">
                 <button type="button" class="btn btn-outline sbold red" data-dismiss="modal">取消</button>
                 <button type="button" class="btn btn-outline sbold blue" ng-click="saveCompanyContact()">保存</button>
             </div>
             
         </div>
         <!-- /.modal-content -->
     </div>
     <!-- /.modal-dialog -->
 </div>
<!-- 联系人modal END -->

<!-- 财务信息modal START -->
 <div class="modal fade modal-overflow in" id="finance" tabindex="-1" role="finance" aria-hidden="true" data-backdrop="static">
     <div class="modal-dialog">
         <div class="modal-content">
             <div class="modal-header">
                 <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                 <h4 class="modal-title" >{{title==null?'新建':title}}账号</h4>
             </div>
             <div class="modal-body">
               <div class="form-body" >
               <div class="alert alert-danger display-hide">
                                                <button class="close" data-close="alert"></button>表单数据未填写完整</div>
               <form id="companyFinanceForm" class="form-horizontal">
                 <div class="row">
                     <div class="col-md-12">
                         <div class="form-group">
                                 <label class="col-md-4 control-label" for="openingBank"><span class="required"> * </span>银行：</label>
                                 <div class="col-md-6">
                                     <input type="text" class="form-control" id="openingBank" name="openingBank" ng-model="companyFinance.openingBank"  >
                                     <div class="form-control-focus"> </div>
                                 </div>
                         </div>
                     </div>
                 </div>
                 <!--/row-->
                 <div class="row">
                     <div class="col-md-12">
                         <div class="form-group">
                                 <label class="col-md-4 control-label" for="accountName"><span class="required"> * </span>户名：</label>
                                 <div class="col-md-6">
                                     <input type="text" class="form-control" id="accountName" name="accountName" ng-model="companyFinance.accountName"  >
                                     <div class="form-control-focus"> </div>
                                 </div>
                         </div>
                     </div>
                 </div>
                 <!--/row-->
                 <div class="row">
                     <div class="col-md-12">
                         <div class="form-group">
                                 <label class="col-md-4 control-label" for="accountNumber"><span class="required"> * </span>账号：</label>
                                 <div class="col-md-6">
                                     <input type="text" class="form-control" id="accountNumber" name="accountNumber" ng-model="companyFinance.accountNumber"  >
                                     <div class="form-control-focus"> </div>
                                 </div>
                         </div>
                     </div>
                 </div>
                 <div class="row">
                     <div class="col-md-12">
                         <div class="form-group">
                                 <label class="col-md-4 control-label" for="remark">备注：</label>
                                 <div class="col-md-6">
                                     <input type="text" class="form-control" id="remark" ng-model="companyFinance.remark"  >
                                     <div class="form-control-focus"> </div>
                                 </div>
                         </div>
                     </div>
                 </div>
                 <!--/row-->
                 </form>
             </div>
             </div>
             <div class="modal-footer">
                 <button type="button" class="btn btn-outline sbold red" data-dismiss="modal">取消</button>
                 <button type="button" class="btn btn-outline sbold blue" ng-click="saveCompanyFinance()">保存</button>
             </div>
             
         </div>
         <!-- /.modal-content -->
     </div>
     <!-- /.modal-dialog -->
 </div>
<!-- 财务信息modal END -->


<!-- 联系地址modal START -->
 <div class="modal fade modal-overflow in" id="address" tabindex="-1" role="address" aria-hidden="true" data-backdrop="static">
     <div class="modal-dialog">
         <div class="modal-content">
             <div class="modal-header">
                 <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                 <h4 class="modal-title" >{{title==null?'新建':title}}联系地址</h4>
             </div>
             <div class="modal-body">
               <div class="form-body" >
               <div class="alert alert-danger display-hide">
                                                <button class="close" data-close="alert"></button>表单数据未填写完整</div>
               <form id="companyAddressForm" class="form-horizontal">
               <!-- <div class="row">
                     <div class="col-md-12">
                         <div class="form-group">
                                 <label class="col-md-4 control-label" for="warehouseName"><span class="required"> * </span>仓库名称：</label>
                                 <div class="col-md-6">
                                     <input type="text" class="form-control"  name="warehouseName" ng-model="companyAddress.warehouseName"  >
                                     <div class="form-control-focus"> </div>
                                 </div>
                         </div>
                     </div>
                 </div> -->
                 <div class="row">
                     <div class="col-md-12">
                         <div class="form-group">
                                 <label class="col-md-4 control-label" for="address"><span class="required"> * </span>地址：</label>
                                 <div class="col-md-6">
                                     <input type="text" class="form-control"  name="address" ng-model="companyAddress.address"  >
                                     <div class="form-control-focus"> </div>
                                 </div>
                         </div>
                     </div>
                 </div>
                 <!--/row-->
                 <div class="row">
                     <div class="col-md-12">
                         <div class="form-group">
                                 <label class="col-md-4 control-label" for="zipCode"><!-- <span class="required"> * </span> -->邮编：</label>
                                 <div class="col-md-6">
                                     <input type="text" class="form-control" id="zipCode" name="zipCode" ng-model="companyAddress.zipCode"  >
                                     <div class="form-control-focus"> </div>
                                 </div>
                         </div>
                     </div>
                 </div>
                 <!--/row-->
                 <div class="row">
                     <div class="col-md-12">
                         <div class="form-group">
                                 <label class="col-md-4 control-label" for="contactTel"><!-- <span class="required"> * </span> -->联系电话：</label>
                                 <div class="col-md-6">
                                     <input type="text" class="form-control" name="contactTel" ng-model="companyAddress.contactTel"  >
                                     <div class="form-control-focus"> </div>
                                 </div>
                         </div>
                     </div>
                 </div>
                 <!-- row -->
                 <div class="row">
                     <div class="col-md-12">
                         <div class="form-group">
                                 <label class="col-md-4 control-label" for="mobileNum"><!-- <span class="required"> * </span> -->手机：</label>
                                 <div class="col-md-6">
                                     <input type="text" class="form-control" id="mobileNum" name="mobileNum" ng-model="companyAddress.mobileNum"  >
                                     <div class="form-control-focus"> </div>
                                 </div>
                         </div>
                     </div>
                 </div>
                 <!-- row -->
                 <div class="row">
                     <div class="col-md-12">
                         <div class="form-group">
                                 <label class="col-md-4 control-label" for="remark">备注：</label>
                                 <div class="col-md-6">
                                     <input type="text" class="form-control" ng-model="companyAddress.remark"  >
                                     <div class="form-control-focus"> </div>
                                 </div>
                         </div>
                     </div>
                 </div>
                 <!-- row -->
                 </form>
             </div>
             </div>
             <div class="modal-footer">
                 <button type="button" class="btn btn-outline sbold red" data-dismiss="modal">取消</button>
                 <button type="button" class="btn btn-outline sbold blue" ng-click="saveCompanyAddress()">保存</button>
             </div>
             
         </div>
         <!-- /.modal-content -->
     </div>
     <!-- /.modal-dialog -->
 </div>
<!-- 联系地址modal END -->

<jsp:include  page="selectUser.jsp"/> <!-- 选择维护人员 -->
<!-- END MAIN CONTENT -->
<!-- BEGIN MAIN JS -->
 <script>
// FormRepeater.init();
</script> 
<!-- END MAIN JS -->
