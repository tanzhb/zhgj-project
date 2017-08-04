<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" %>
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
</style>
<h3 class="page-title"> 企业信息
</h3>
<div class="page-bar">
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

</div>
<!-- END PAGE HEADER-->
<!-- BEGIN MAIN CONTENT -->
<div class="row">
    <div class="col-md-12">
        <!-- BEGIN EXAMPLE TABLE PORTLET-->
        <div class="portlet bordered">
            <div class="portlet-body">
				<div class="portlet light ">
                        <div class="portlet-title">
                            <div class="caption">企业信息</div>
                            <div class="actions">
                                <!-- <a href="" class="collapse" data-original-title="" title=""> </a>
                                <a href="#portlet-config" data-toggle="modal" class="config" data-original-title="" title=""> </a>
                                <a href="" class="reload" data-original-title="" title=""> </a>
                                <a href="" class="remove" data-original-title="" title=""> </a> -->
                                <button   ng-show="companyView" class="btn blue  btn-outline  btn-sm " ng-click="editCompany()">
                                            <i class="fa fa-edit"></i> 编辑 </button>
                                <button   ng-hide="companyAdd" class="btn blue  btn-outline  btn-sm " ng-click="saveCompany()">
                                            <i class="fa fa-save"></i> 保存 </button>
                            </div>
                        </div>
                        <div class="portlet-body form">
                            <form  id="wareForm" class="form-horizontal" >
								<div class="form-body">
									<div class="alert alert-danger display-hide">
                                                <button class="close" data-close="alert"></button> You have some form errors. Please check below. </div>
                                    <div class="alert alert-success display-hide">
                                                <button class="close" data-close="alert"></button> Your form validation is successful! </div>
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-4">企业编号：</label>
												<div class="col-md-8 input-icon right">
													<input  ng-hide="companyAdd"  type="text" ng-model="company.comNum"
														class="form-control" placeholder="">
													<p class="form-control-static" ng-show="companyView">{{company.comNum}}</p>
												</div>
											</div>
										</div>
						
										<!--/span-->
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-4">企业名称：</label>
												<div class="col-md-8">
													<input  ng-hide="companyAdd"  type="text" ng-model="company.comName"
														class="form-control" placeholder="">
													<p class="form-control-static"  ng-show="companyView">{{company.comName}}</p>
												</div>
											</div>
										</div>
										<!--/span-->
									</div>
									<!--/row-->
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-4">企业类型：</label>
												<div class="col-md-8">
													<select class="bs-select form-control" ng-hide="companyAdd" ng-model="company.comType">
                                                             <option value="采购商" >采购商</option>
                                                             <option value="供应商" >供应商</option>
                                                             <option value="承运人" >承运人</option>
                                                             <option value="外协仓" >外协仓</option>
                                                             <option value="境外供应商" >境外供应商</option>
                                                             <option value="装卸公司" >装卸公司</option>
                                                             <option value="银行" >银行</option>
                                                             <option value="保险公司" >保险公司</option>
                                                    </select>
													<p class="form-control-static"  ng-show="companyView">{{company.comType}}</p>
												</div>
											</div>
										</div>
										<!--/span-->
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-4">企业简称：</label>
												<div class="col-md-8">
													<select class="bs-select form-control" ng-hide="companyAdd" ng-model="company.abbreviation">
                                                             <option value="国有企业" >国有企业</option>
                                                             <option value="民营企业" >民营企业</option>
                                                             <option value="合资企业" >合资企业</option>
                                                             <option value="外资企业" >外资企业</option>
                                                    </select>
													<p class="form-control-static"  ng-show="companyView">{{company.abbreviation}}</p>
												</div>
											</div>
										</div>
										<!--/span-->
									</div>
									<!--/row-->
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-4">经营性质：</label>
												<div class="col-md-8">
													<select class="bs-select form-control" ng-hide="companyAdd" ng-model="company.businessNature">
                                                             <option value="国有企业" >加工制造</option>
                                                             <option value="民营企业" >分销/交易</option>
                                                             <option value="合资企业" >仓储供应</option>
                                                    </select>
													<p class="form-control-static"  ng-show="companyView">{{company.businessNature}}</p>
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-4">企业性质：</label>
												<div class="col-md-8">
													<input ng-hide="companyAdd" type="text" ng-model="company.comNature"
														class="form-control" placeholder=""> 
													<p class="form-control-static"  ng-show="companyView">{{company.comNature}}</p>
												</div>
											</div>
										</div>
									</div>
									<!--/row-->
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-4">经营类型：</label>
												<div class="col-md-8">
													<input ng-hide="companyAdd" type="text" ng-model="company.businessType"
														class="form-control" placeholder=""> 
													<p class="form-control-static"  ng-show="companyView">{{company.businessType}}</p>
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-4">注册资金：</label>
												<div class="col-md-8">
													<input ng-hide="companyAdd" type="text" ng-model="company.registeredCapital"
														class="form-control" placeholder=""> 
													<p class="form-control-static"  ng-show="companyView">{{company.registeredCapital}}</p>
												</div>
											</div>
										</div>
									</div>
									<!--/row-->
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-4">企业法人姓名：</label>
												<div class="col-md-8">
													<input ng-hide="companyAdd" type="text" ng-model="company.legalPerson"
														class="form-control" placeholder="">
													<p class="form-control-static"  ng-show="companyView">{{company.legalPerson}}</p>
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-4">注册地址：</label>
												<div class="col-md-8">
													<input ng-hide="companyAdd" type="text" ng-model="company.address"
														class="form-control" placeholder=""> 
													<p class="form-control-static"  ng-show="companyView">{{company.address}}</p>
												</div>
											</div>
										</div>
									</div>
									<!--/row-->
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-4">纳税人识别号：</label>
												<div class="col-md-8">
													<input ng-hide="companyAdd" type="text" ng-model="company.taxpayeNumber"
														class="form-control" placeholder=""> 
													<p class="form-control-static"  ng-show="companyView">{{company.taxpayeNumber}}</p>
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-4">联系电话：</label>
												<div class="col-md-8">
													<input ng-hide="companyAdd" type="text" ng-model="company.tel"
														class="form-control" placeholder=""> 
													<p class="form-control-static"  ng-show="companyView">{{company.tel}}</p>
												</div>
											</div>
										</div>
									</div>
									<!--/row-->
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-4">维护人员：</label>
												<div class="col-md-8">
													<input ng-hide="companyAdd" type="text" ng-model="company.contact"
														class="form-control" placeholder="">
													<p class="form-control-static"  ng-show="companyView">{{company.contact}}</p>
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-4">备注：</label>
												<div class="col-md-8">
													<input ng-hide="companyAdd" type="text" ng-model="company.remark"
														class="form-control" placeholder=""> 
													<p class="form-control-static"  ng-show="companyView" class="control-label">{{company.remark}}</p>
												</div>
											</div>
										</div>
									</div>
								</div>
							</form>
         				</div>

			<!-- 	</div>
				
				<div class="portlet light"> -->
                        <div class="portlet-title">
                            <div class="caption">资质信息</div>
                            <div class="actions">
                                <!-- <a href="" class="collapse" data-original-title="" title=""> </a>
                                <a href="#portlet-config" data-toggle="modal" class="config" data-original-title="" title=""> </a>
                                <a href="" class="reload" data-original-title="" title=""> </a>
                                <a href="" class="remove" data-original-title="" title=""> </a> -->
                                <button   ng-show="companyQualificationView" class="btn blue btn-outline  btn-sm " ng-click="editCompanyQualification()">
                                            <i class="fa fa-edit"></i> 编辑 </button>
                                <button   ng-hide="companyQualificationAdd" class="btn blue btn-outline  btn-sm " ng-click="saveCompanyQualification()">
                                            <i class="fa fa-save"></i> 保存 </button>
                            </div>
                        </div>
                        <div class="portlet-body form">
                            <form  id="wareForm" class="page-repeater form-horizontal">
								<div class="form-body" data-repeater-list="group-a"  >
									<div  class="row" data-repeater-item  ng-repeat="companyQualification in companyQualifications  track by $index" repeat-done="repeatDone()">
										<div class="col-md-3">
											<div class="form-group">
												<div class="col-md-12 input-icon right">
													<input  ng-hide="companyQualificationAdd" type="text" ng-model="companyQualification.qualificationName"
														class="form-control" placeholder="资质信息"> <span
														class="help-block"></span>
														<label   ng-show="companyQualificationView"  class="c_edit" >{{companyQualification.qualificationName}}</label>
												</div>
											</div>
										</div>
						
										<!--/span-->
										<div class="col-md-3">
											<div class="form-group">
												<div class="col-md-12">
													<input  ng-hide="companyQualificationAdd" type="text" ng-model="companyQualification.qualificationNum"
														class="form-control" placeholder="资质号码"> <span
														class="help-block"></span>
														<label   ng-show="companyQualificationView"  class="c_edit" >{{companyQualification.qualificationNum}}</label>
												</div>
											</div>
										</div>
										<!--/span-->
										<div class="col-md-3">
											<div class="form-group">
												<div class="col-md-12">
													<div  ng-hide="companyQualificationAdd" class="input-group input-medium date date-picker"
														 data-date-format="yyyy-mm-dd"
														data-date-viewmode="years">
														<input type="text" class="form-control" readonly="" ng-model="companyQualification.validityDate"
															placeholder="有效期"> <span class="input-group-btn">
															<button class="btn default" type="button">
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
											<div class="form-group rigjt">
												<div class="col-md-12">
													 <div  ng-hide="companyQualificationAdd" class="fileinput fileinput-new" data-provides="fileinput">
			                                                  <div class="fileinput-preview fileinput-exists thumbnail" style="max-width: 70px; max-height: 70px; line-height: 10px;float: left;"> </div>
			                                                  <div style="float: left;">
			                                                      <span class="btn default btn-file btn-sm">
			                                                          <span class="fileinput-new "><i class="fa fa-plus "></i></span>
			                                                          <span class="fileinput-exists">更换 </span>
			                                                          <input type="hidden"><input type="file" name="..."> </span><br>
			                                                      <a href="javascript:;" class="btn red btn-sm fileinput-exists " data-dismiss="fileinput"> 移除 </a>
			                                                  </div>
			                                          </div>
			                                          <div   ng-show="companyQualificationView" class="c_edit" >施工中</div>
												</div>
											</div>
										</div>
										<!--/span-->
										<div class="col-md-1">
											<div class="form-group">
												<!-- <div class="col-md-5" >
													 <a  ng-hide="companyQualificationAdd"  href="javascript:;" class="btn blue btn-sm" ng-click="saveCompanyQualification()" >
                                            			<i class="fa fa-save"></i>
                                     				</a>
													 <a  ng-show="companyQualificationView"  href="javascript:;" class="btn blue btn-sm c_edit"  ng-click="editCompanyQualification()" >
                                            			<i class="fa fa-edit"></i>
                                     				</a>
												</div> -->
												<div class="col-md-12">
													 <a href="javascript:;" data-repeater-delete class="btn red btn-sm" ng-click="deleteRepeat()">
                                            			<i class="fa fa-close"></i> 
                                     				</a>
												</div>
											</div>
										</div> 
									</div>
									<!-- /row -->
								</div>
								<div class="form-actions right">
									<a href="javascript:;" data-repeater-create class="btn blue btn-sm" ng-click="addRepeat()" >
                                            <i class="fa fa-plus"></i> 增加
                                     </a>
                                </div>
							</form>
         				</div>

				<!-- </div>
				
				<div class="portlet light"> -->
                        <div class="portlet-title">
                            <div class="caption">联系人</div>
                            <div class="actions">
                                <!-- <a href="" class="collapse" data-original-title="" title=""> </a>
                                <a href="#portlet-config" data-toggle="modal" class="config" data-original-title="" title=""> </a>
                                <a href="" class="reload" data-original-title="" title=""> </a>
                                <a href="" class="remove" data-original-title="" title=""> </a> -->
                            </div>
                        </div>
                          <div class="portlet-body form">
			                                    <div class="table-scrollable">
			                                        <table class="table table-bordered table-hover">
			                                            <thead>
			                                                <tr>
			                                                    <th>姓名</th>
			                                                    <th>职位</th>
			                                                    <th>部门/公司</th>
			                                                    <th>电话</th>
			                                                    <th>微信</th>
			                                                    <th>邮箱</th>
			                                                    <th>备注</th>
			                                                </tr>
			                                            </thead>
			                                           <tbody ng-show="noContactData" ng-if="companyContact.length==0">
			                                             	<tr>
			                                                    <td colspan="7" align="center" >暂无数据</td>
			                                                </tr>
			                                            </tbody>
			                                            <tbody ng-repeat="contact in companyContacts">
			                                                <tr>
			                                                    <td>{{contact.contactName}}</td>
			                                                    <td>{{contact.contactTitle}}</td>
			                                                    <td>{{contact.department}}</td>
			                                                    <td>{{contact.contactTel}}</td>
			                                                    <td>{{contact.wechat}}</td>
			                                                    <td>{{contact.contactEmail}}</td>
			                                                    <td>{{contact.remark}}</td>
			                                                </tr>
			                                            </tbody>
			                                        </table>
			                                    </div>
			                                    <div class="form-actions right">
												 	<a href="#"  class="btn blue btn-sm"   data-toggle="modal" data-target="#contact" >
			                                            <i class="fa fa-plus"></i> 增加
			                                     	</a>
			                                	</div>
                             </div>
				<!-- </div>
				
				<div class="portlet light"> -->
                        <div class="portlet-title">
                            <div class="caption">财务信息</div>
                            <div class="actions">
                                <!-- <a href="" class="collapse" data-original-title="" title=""> </a>
                                <a href="#portlet-config" data-toggle="modal" class="config" data-original-title="" title=""> </a>
                                <a href="" class="reload" data-original-title="" title=""> </a>
                                <a href="" class="remove" data-original-title="" title=""> </a> -->
                            </div>
                        </div>
                        
                          <div class="portlet-body form">
                                    <div class="table-scrollable">
                                        <table class="table table-bordered table-hover">
                                            <thead>
                                                <tr>
                                                    <th>银行</th>
                                                    <th>户名</th>
                                                    <th>账号</th>
                                                    <th>备注</th>
                                                </tr>
                                            </thead>
                                            <tbody ng-repeat="finance in companyFinances">
                                                <tr>
                                                    <td>{{finance.openingBank}}</td>
                                                    <td>{{finance.accountName}}</td>
                                                    <td>{{finance.accountNumber}}</td>
                                                    <td>{{finance.remark}}</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                    <div class="form-actions right">
									 	<a   href="#" class="btn blue btn-sm" data-toggle="modal" data-target="#finance" >
                                            <i class="fa fa-plus"></i> 增加
                                     	</a>
                                	</div>
                                </div>
				</div>
				
        </div>
        <!-- END EXAMPLE TABLE PORTLET-->
    </div>
</div>



 <div class="modal fade" id="contact" tabindex="-1" role="contact" aria-hidden="true" data-backdrop="static">
     <div class="modal-dialog" style="width: 400px;">
         <div class="modal-content">
             <div class="modal-header">
                 <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                 <h4 class="modal-title" >新建联系人</h4>
             </div>
             <div class="modal-body">
               <div class="form-body" >
               <form id="wareForm">
                <!--  <h3 class="form-section">仓库信息</h3> -->
                 <div class="row">
                     <div class="col-md-12">
                         <div class="form-group">
                             <label class="control-label col-md-4">姓名：</label>
                             <div class="col-md-8 input-icon right">
                                 <input type="text" ng-model="companyContact.contactName" class="form-control" placeholder="">
                                 <span class="help-block"></span>
                             </div>
                         </div>
                     </div>
                 </div>
                 <!--/row-->
                 <div class="row">
                     <div class="col-md-12">
                         <div class="form-group">
                             <label class="control-label col-md-4">职位：</label>
                             <div class="col-md-8 input-icon right">
                                 <input type="text" ng-model="companyContact.contactTitle" class="form-control" placeholder="">
                                 <span class="help-block"></span>
                             </div>
                         </div>
                     </div>
                 </div>
                 <div class="row">
                     <div class="col-md-12">
                         <div class="form-group">
                             <label class="control-label col-md-4">部门/公司：</label>
                             <div class="col-md-8 input-icon right">
                                 <input type="text" ng-model="companyContact.department" class="form-control" placeholder="">
                                 <span class="help-block"></span>
                             </div>
                         </div>
                     </div>
                 </div>
                 <!--/row-->
                 <div class="row">
                     <div class="col-md-12">
                         <div class="form-group">
                             <label class="control-label col-md-4">电话：</label>
                             <div class="col-md-8 input-icon right">
                                 <input type="text" ng-model="companyContact.contactTel" class="form-control" placeholder="">
                                 <span class="help-block"></span>
                             </div>
                         </div>
                     </div>
                 </div>
                 <!--/row-->
                 <div class="row">
                     <div class="col-md-12">
                         <div class="form-group">
                             <label class="control-label col-md-4">微信：</label>
                             <div class="col-md-8 input-icon right">
                                 <input type="text" ng-model="companyContact.wechat" class="form-control" placeholder="">
                                 <span class="help-block"></span>
                             </div>
                         </div>
                     </div>
                 </div>
                 <!--/row-->
                 <div class="row">
                     <div class="col-md-12">
                         <div class="form-group">
                             <label class="control-label col-md-4">邮箱：</label>
                             <div class="col-md-8 input-icon right">
                                 <input type="text" ng-model="companyContact.contactEmail" class="form-control" placeholder="">
                                 <span class="help-block"></span>
                             </div>
                         </div>
                     </div>
                 </div>
                 <!--/row-->
                 <div class="row">
                     <div class="col-md-12">
                         <div class="form-group">
                             <label class="control-label col-md-4">备注：</label>
                             <div class="col-md-8 input-icon right">
                                 <input type="text" ng-model="companyContact.remark" class="form-control" placeholder="">
                                 <span class="help-block"></span>
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

 <div class="modal fade modal-overflow in" id="finance" tabindex="-1" role="finance" aria-hidden="true" data-backdrop="static">
     <div class="modal-dialog" style="width: 400px;">
         <div class="modal-content">
             <div class="modal-header">
                 <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                 <h4 class="modal-title" >新建账号</h4>
             </div>
             <div class="modal-body">
               <div class="form-body" >
               <form id="wareForm">
                <!--  <h3 class="form-section">仓库信息</h3> -->
                 <div class="row">
                     <div class="col-md-12">
                         <div class="form-group">
                             <label class="control-label col-md-3">银行：</label>
                             <div class="col-md-9 input-icon right">
                                 <input type="text" ng-model="companyFinance.openingBank" class="form-control" placeholder="">
                                 <span class="help-block"></span>
                             </div>
                         </div>
                     </div>
                 </div>
                 <!--/row-->
                 <div class="row">
                     <div class="col-md-12">
                         <div class="form-group">
                             <label class="control-label col-md-3">户名：</label>
                             <div class="col-md-9 input-icon right">
                                 <input type="text" ng-model="companyFinance.accountName" class="form-control" placeholder="">
                                 <span class="help-block"></span>
                             </div>
                         </div>
                     </div>
                 </div>
                 <!--/row-->
                 <div class="row">
                     <div class="col-md-12">
                         <div class="form-group">
                             <label class="control-label col-md-3">账号：</label>
                             <div class="col-md-9 input-icon right">
                                 <input type="text" ng-model="companyFinance.accountNumber" class="form-control" placeholder="">
                                 <span class="help-block"></span>
                             </div>
                         </div>
                     </div>
                 </div>
                 <div class="row">
                     <div class="col-md-12">
                         <div class="form-group">
                             <label class="control-label col-md-3">备注：</label>
                             <div class="col-md-9 input-icon right">
                                 <input type="text" ng-model="companyFinance.remark" class="form-control" placeholder="">
                                 <span class="help-block"></span>
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



<!-- END MAIN CONTENT -->
<!-- BEGIN MAIN JS -->
 <script>
// FormRepeater.init();
</script> 
<!-- END MAIN JS -->