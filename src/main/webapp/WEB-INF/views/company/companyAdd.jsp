<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- BEGIN PAGE HEADER-->
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
				<div class="portlet box blue ">
                        <div class="portlet-title">
                            <div class="caption">企业信息</div>
                            <div class="actions">
                                <!-- <a href="" class="collapse" data-original-title="" title=""> </a>
                                <a href="#portlet-config" data-toggle="modal" class="config" data-original-title="" title=""> </a>
                                <a href="" class="reload" data-original-title="" title=""> </a>
                                <a href="" class="remove" data-original-title="" title=""> </a> -->
                                <a   ng-show="companyView" class="btn blue btn-default  btn-sm " ng-click="editCompany()">
                                            <i class="fa fa-edit"></i> 编辑 </a>
                                <a   ng-hide="companyAdd" class="btn blue btn-default  btn-sm " ng-click="saveCompany()">
                                            <i class="fa fa-save"></i> 保存 </a>
                            </div>
                        </div>
                        <div class="portlet-body form">
                            <form  id="wareForm" class="form-horizontal" >
								<div class="form-body">
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-3">企业编号</label>
												<div class="col-md-9 input-icon right">
													<input  ng-hide="companyAdd"  type="text" ng-model="company.comNum"
														class="form-control" placeholder=""> <span
														class="help-block"></span>
													<span ng-show="companyView">{{company.comNum}}</span>
												</div>
											</div>
										</div>
						
										<!--/span-->
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-3">企业名称</label>
												<div class="col-md-9">
													<input  ng-hide="companyAdd"  type="text" ng-model="company.comName"
														class="form-control" placeholder=""> <span
														class="help-block"></span>
													<span ng-show="companyView">{{company.comName}}</span>
												</div>
											</div>
										</div>
										<!--/span-->
									</div>
									<!--/row-->
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-3">企业类型</label>
												<div class="col-md-9">
													<input ng-hide="companyAdd" type="text" ng-model="company.comType"
														class="form-control" placeholder=""> <span
														class="help-block"></span>
													<span ng-show="companyView">{{company.comType}}</span>
												</div>
											</div>
										</div>
										<!--/span-->
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-3">企业简称</label>
												<div class="col-md-9">
													<input ng-hide="companyAdd" type="text" ng-model="company.abbreviation"
														class="form-control" placeholder=""> <span
														class="help-block"></span>
													<span ng-show="companyView">{{company.abbreviation}}</span>
												</div>
											</div>
										</div>
										<!--/span-->
									</div>
									<!--/row-->
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-3">经营性质</label>
												<div class="col-md-9">
													<input ng-hide="companyAdd" type="text" ng-model="company.businessNature"
														class="form-control" placeholder=""> <span
														class="help-block"></span>
													<span ng-show="companyView">{{company.businessNature}}</span>
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-3">企业性质</label>
												<div class="col-md-9">
													<input ng-hide="companyAdd" type="text" ng-model="company.comNature"
														class="form-control" placeholder=""> <span
														class="help-block"></span>
													<span ng-show="companyView">{{company.comNature}}</span>
												</div>
											</div>
										</div>
									</div>
									<!--/row-->
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-3">经营类型</label>
												<div class="col-md-9">
													<input ng-hide="companyAdd" type="text" ng-model="company.businessType"
														class="form-control" placeholder=""> <span
														class="help-block"></span>
													<span ng-show="companyView">{{company.businessType}}</span>
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-3">注册资金</label>
												<div class="col-md-9">
													<input ng-hide="companyAdd" type="text" ng-model="company.registeredCapital"
														class="form-control" placeholder=""> <span
														class="help-block"></span>
													<span ng-show="companyView">{{company.registeredCapital}}</span>
												</div>
											</div>
										</div>
									</div>
									<!--/row-->
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-3">企业法人姓名</label>
												<div class="col-md-9">
													<input ng-hide="companyAdd" type="text" ng-model="company.legalPerson"
														class="form-control" placeholder=""> <span
														class="help-block"></span>
													<span ng-show="companyView">{{company.legalPerson}}</span>
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-3">注册地址</label>
												<div class="col-md-9">
													<input ng-hide="companyAdd" type="text" ng-model="company.address"
														class="form-control" placeholder=""> <span
														class="help-block"></span>
													<span ng-show="companyView">{{company.address}}</span>
												</div>
											</div>
										</div>
									</div>
									<!--/row-->
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-3">纳税人识别号</label>
												<div class="col-md-9">
													<input ng-hide="companyAdd" type="text" ng-model="company.taxpayeNumber"
														class="form-control" placeholder=""> <span
														class="help-block"></span>
													<span ng-show="companyView">{{company.taxpayeNumber}}</span>
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-3">联系电话</label>
												<div class="col-md-9">
													<input ng-hide="companyAdd" type="text" ng-model="company.tel"
														class="form-control" placeholder=""> <span
														class="help-block"></span>
													<span ng-show="companyView">{{company.tel}}</span>
												</div>
											</div>
										</div>
									</div>
									<!--/row-->
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-3">维护人员</label>
												<div class="col-md-9">
													<input ng-hide="companyAdd" type="text" ng-model="company.contact"
														class="form-control" placeholder=""> <span
														class="help-block"></span>
													<span ng-show="companyView">{{company.contact}}</span>
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-3">备注</label>
												<div class="col-md-9">
													<input ng-hide="companyAdd" type="text" ng-model="company.remark"
														class="form-control" placeholder=""> <span
														class="help-block"></span>
													<span ng-show="companyView">{{company.remark}}</span>
												</div>
											</div>
										</div>
									</div>
								</div>
							</form>
         				</div>

				</div>
				
				<div class="portlet box blue ">
                        <div class="portlet-title">
                            <div class="caption">资质信息</div>
                            <div class="actions">
                                <!-- <a href="" class="collapse" data-original-title="" title=""> </a>
                                <a href="#portlet-config" data-toggle="modal" class="config" data-original-title="" title=""> </a>
                                <a href="" class="reload" data-original-title="" title=""> </a>
                                <a href="" class="remove" data-original-title="" title=""> </a> -->
                                <a   ng-show="companyQualificationView" class="btn blue btn-default  btn-sm " ng-click="editCompanyQualification()">
                                            <i class="fa fa-edit"></i> 编辑 </a>
                                <a   ng-hide="companyQualificationAdd" class="btn blue btn-default  btn-sm " ng-click="saveCompanyQualification()">
                                            <i class="fa fa-save"></i> 保存 </a>
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
														<span   ng-show="companyQualificationView"  class="c_edit" ></span>
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
														<span   ng-show="companyQualificationView"  class="c_edit" ></span>
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
													<span   ng-show="companyQualificationView" class="c_edit" ></span>
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
			                                          <div   ng-show="companyQualificationView" class="c_edit" >施工中。。</div>
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
													 <a href="javascript:;" data-repeater-delete class="btn red btn-sm">
                                            			<i class="fa fa-close"></i> 
                                     				</a>
												</div>
											</div>
										</div> 
									</div>
									<!-- /row -->
								</div>
								<div class="form-actions right">
									<a href="javascript:;" data-repeater-create class="btn blue btn-outline" ng-click="addRepeat()" >
                                            <i class="fa fa-plus"></i> 增加
                                     </a>
                                </div>
							</form>
         				</div>

				</div>
				
				<div class="portlet box blue ">
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
                            <form   class="form-horizontal" >
								<div class="form-body"  >
								  <div  class="row">
										<div class="col-md-4">
											<div class="portlet-body">
                                            		<!-- <i class="glyphicon glyphicon-remove-circle" aria-hidden="true"></i> -->
                                            	<a href="javascript:;" class="btn btn-circle btn-xs grey-salsa" style="position: absolute;top: -12px;right: 5px;z-index: 100"><i class="fa fa-close"> </i>
                                                                    </a>
                                               <!--  <div class="portlet-body"> -->
                                                	<div class="mt-element-list">
				                                        <div class="mt-list-container list-news ext-1" style="border-top: 1px solid #e7ecf1;">
				                                            <ul>
				                                                <li class="mt-list-item">
				                                                    <div class="list-thumb">
				                                                        <a href="javascript:;">
				                                                            <img alt="" src="../assets/global/img/portfolio/600x600/12.jpg">
				                                                        </a>
				                                                    </div>
				                                                    <h5 class="list-item-content uppercase">张晓军&nbsp;销售总监</h5>
				                                                    <div class="list-item-content">
				                                                        <h5 class="uppercase">
				                                                            13855644983
				                                                        </h5>
				                                                        <h5>zhangxj@scmyun.com</h5>
				                                                    </div>
				                                                </li>
				                                            </ul>
				                                        </div>
				                                    </div>
                                                
												<!-- </div> -->
                                            </div>
										</div>
										<div class="col-md-4">
                                            <div class="portlet-body" >
                                            		<div class="mt-element-list" >
				                                 		<div class="mt-list-container list-news ext-1" style="border: 1px solid #94A0B2;height: 115px;border-style: dashed;">
				                                            <ul style="text-align: center;">
				                                                <li class="mt-list-item" style="line-height: 84px;">
				                                                    <i class="fa fa-plus"></i>
				                                                </li>
				                                            </ul>
				                                        </div>
				                                  </div>
				                            </div>
										</div>
										<!--/span-->
									</div>
									<!--/row-->
								</div>
							</form>
         				</div>

				</div>
				
				<div class="portlet box blue ">
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
                            <form   class="form-horizontal" >
								<div class="form-body"  >
								  <div  class="row">
										<div class="col-md-4">
											<div class="portlet-body">
                                            		<!-- <i class="glyphicon glyphicon-remove-circle" aria-hidden="true"></i> -->
                                            	<a href="javascript:;" class="btn btn-circle btn-xs grey-salsa" style="position: absolute;top: -12px;right: 5px;z-index: 100"><i class="fa fa-close"> </i>
                                                                    </a>
                                               <!--  <div class="portlet-body"> -->
                                                	<div class="mt-element-list">
				                                        <div class="mt-list-container list-news ext-1" style="border-top: 1px solid #e7ecf1;">
				                                            <ul>
				                                                <li class="mt-list-item">
				                                                    <div class="uppercase"><span class="bold">工商银行</span><span>（上海市黄渡分行）</span></div>
				                                                    <div class="">
				                                                        <h5 class="uppercase">户名：朱老大</h5>
				                                                        <h5>账号：6228480031334798910</h5>
				                                                    </div>
				                                                </li>
				                                            </ul>
				                                        </div>
				                                    </div>
                                                
												<!-- </div> -->
                                            </div>
										</div>
										<div class="col-md-4">
                                            <div class="portlet-body" >
                                            		<div class="mt-element-list" >
				                                 		<div class="mt-list-container list-news ext-1" style="border: 1px solid #94A0B2;height: 115px;border-style: dashed;">
				                                            <ul style="text-align: center;">
				                                                <li class="mt-list-item" style="line-height: 84px;">
				                                                    <i class="fa fa-plus"></i>
				                                                </li>
				                                            </ul>
				                                        </div>
				                                  </div>
				                            </div>
										</div>
										<!--/span-->
									</div>
									<!--/row-->
								</div>
							</form>
         				</div>

				</div>
				
        </div>
        <!-- END EXAMPLE TABLE PORTLET-->
    </div>
</div>




<!-- END MAIN CONTENT -->
<!-- BEGIN MAIN JS -->
 <script>
// FormRepeater.init();
</script> 
<!-- END MAIN JS -->