<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<head>
<meta http-equiv="Content-Type"
	content="multipart/form-data; charset=utf-8" />
</head>
<!-- BEGIN PAGE HEADER-->
<!-- <h3 class="page-title">企业信息</h3> -->
<!-- <div class="page-bar">
	<ul class="page-breadcrumb">
		<li><i class="fa fa-home"></i> <a ui-sref="dashboard">首页</a> <i
			class="fa fa-angle-right"></i></li>
		<li><a ui-sref="datatablesmanaged">基础数据</a> <i
			class="fa fa-angle-right"></i></li>
		<li><a ui-sref="datatablesmanaged">企业信息</a></li>
	</ul>
</div> -->
<!-- END PAGE HEADER-->
<!-- BEGIN MAIN CONTENT -->
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN EXAMPLE TABLE PORTLET-->
		<div class="portlet light">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe font-green"></i>
					<span class="caption-subject font-green bold uppercase">企业信息列表</span>
				</div>
				<!--  <div class="actions">
                                        <a class="btn btn-default btn-sm" data-toggle="modal" data-target="#basic">
                                            <i class="fa fa-plus"></i> 新增 </a>
                                        <a href="javascript:;" class="btn btn-default btn-sm">
                                            <i class="fa fa-print"></i> 打印 </a>
                                    </div> -->
				<div class="actions">
					<div class="btn-group btn-group-devided" data-toggle="buttons">
						<shiro:hasPermission name="company:add">
							<label class="btn btn-transparent green btn-circle btn-sm" ng-click="toAddCompany()">
		                                              <i class="fa fa-plus"></i> 添加</label>
	                    </shiro:hasPermission>
	                    <shiro:hasPermission name="company:edit">
							<label class="btn btn-transparent purple btn-circle btn-sm" ng-click="toEditCompany()">
		                                              <i class="fa fa-edit"></i> 修改</label>
	                    </shiro:hasPermission>
	                    <shiro:hasPermission name="company:delete">                          
							<label class="btn btn-transparent red btn-circle btn-sm" ng-click="deleteCompanyBatch()">
		                                              <i class="fa fa-minus"></i> 删除</label>
	                    </shiro:hasPermission>
	                    <shiro:hasPermission name="company:import">
							<label class="btn btn-transparent green btn-outline btn-circle btn-sm" data-toggle="modal" data-target="#import" >
		                                              <i class="fa fa-upload"></i> 导入</label>
	                    </shiro:hasPermission>
	                    <shiro:hasPermission name="company:export">
							<label class="btn btn-transparent yellow-casablanca btn-outline btn-circle btn-sm" ng-click="exportCompany()">
		                                              <i class="fa fa-file-excel-o"></i> 导出</label>
	                    </shiro:hasPermission>
	                 </div>
					<!-- <a href="javascript:;" class="btn-transparent green btn-outline btn-sm btn-circle"
						ng-click="toAddCompany()"> <i class="fa fa-plus"></i> 添加
					</a> <a href="javascript:;" class="btn btn-default btn-sm btn-circle label-info"
						ng-click="toEditCompany()"> <i class="fa fa-edit"></i> 修改
					</a> <a href="javascript:;" class="btn btn-default btn-sm btn-circle"
						ng-click="deleteCompanyBatch()"> <i class="fa fa-minus"></i>
						删除
					</a>
					<div class="btn-group">
						<a class="btn btn-default btn-outline btn-circle"
							href="javascript:;" data-toggle="dropdown"> <i
							class="fa fa-share"></i> <span class="hidden-xs"> 其它 </span> <i
							class="fa fa-angle-down"></i>
						</a>
						<ul class="dropdown-menu pull-right" id="sample_3_tools">
							<li><a data-action="0" class="tool-action"
								data-toggle="modal" data-target="#import"> <i
									class="fa fa-upload"></i> 导入
							</a></li>
							<li><a href="javascript:;" data-action="1"
								class="tool-action" ng-click="exportCompany()"> <i
									class="fa fa-file-excel-o"></i> 导出
							</a></li>
							<li><a href="javascript:;" data-action="2"
								class="tool-action"> <i class="fa fa-print"></i> 打印
							</a></li>
						</ul>
					</div> -->
				</div>
			</div>
			<div class="portlet-body">
				<!-- <div class="table-responsive"> -->
				<table class="table table-striped table-bordered table-hover table-checkable order-column" id="companyTable">
					<thead>
						<tr>
							<th>
								<!-- <input name="select_all"  class="checkbox-inline"
								value="1" id="example-select-all" data-check="false" type="checkbox" /> -->
								<!-- <label name="select_all" id="example-select-all"  class="mt-checkbox mt-checkbox-outline"  >
                                    <input type="checkbox" ng-model="allChecked" ng-click="checkedOrCancelAll()">
                                    <span></span>
                                </label> -->
                                 <label class="mt-checkbox mt-checkbox-single mt-checkbox-outline">
                                     <input type="checkbox" class="group-checkable" data-set="#companyTable .checkboxes" />
                                     <span></span>
                                 </label>
							</th>
							<th>企业编码</th>
							<th>企业名称</th>
							<th>合作分类</th>
							<th>企业性质</th>
							<th>经营类型</th>
							<th>营业性质</th>
							<th>企业法人</th>
							<th>注册地址</th>
							<th>状态</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>
		</div>

		<!--   </div>
        </div> -->
		<!-- END EXAMPLE TABLE PORTLET-->
	</div>
</div>

<!-- 企业其他信息展示Start -->
<div class="row">
	<div class="col-md-12">
		<div class="portlet light " id="comViewPage">
			<div class="portlet-body" id="comViewContent">
				<ul class="nav nav-tabs">
					<li class="active">
                  	<a class="bold" data-target="#tab_1_1" data-toggle="tab">基本信息</a>
              		</li>
					<li><a class="bold" data-target="#tab_1_2" data-toggle="tab">资质信息</a>
					</li>
					<li><a class="bold" data-target="#tab_1_3" data-toggle="tab">财务信息</a></li>
					<li><a class="bold" data-target="#tab_1_4" data-toggle="tab">联系人</a></li>
				</ul>
				<div class="tab-content">
				<div class="tab-pane fade active in" id="tab_1_1">
                    <div class="portlet light">
                          <div class="portlet-body form">
                             <!--  BEGIN FORM -->
                              <form class="" >
                                  <div class="form-body">
                                      <div class="row">
                                          <div class="col-md-6">
                                              <div class="form-group">
                                                  <label class="control-label col-md-5 bold">企业编号：</label>
                                                  <div class="col-md-7">
                                                      <p class="form-control-static">{{company.comNum}}</p>
                                                  </div>
                                              </div>
                                          </div>
                                          <!-- /span -->
                                          <div class="col-md-6">
                                              <div class="form-group">
                                                  <label class="control-label col-md-5  bold">企业名称：</label>
                                                  <div class="col-md-7">
                                                      <p class="form-control-static">{{company.comName}}</p>
                                                  </div>
                                              </div>
                                          </div>
                                         <!--  /span -->
                                      </div>
                                      <!-- /row -->
                                      <div class="row">
                                          <div class="col-md-6">
                                              <div class="form-group">
                                                  <label class="control-label col-md-5 bold">合作类型：</label>
                                                  <div class="col-md-7">
                                                      <p class="form-control-static">{{company.comTypeName}}</p>
                                                  </div>
                                              </div>
                                          </div>
                                          <!-- /span -->
                                          <div class="col-md-6">
                                              <div class="form-group">
                                                  <label class="control-label col-md-5 bold">企业简称：</label>
                                                  <div class="col-md-7">
                                                      <p class="form-control-static">{{company.abbreviation}}</p>
                                                  </div>
                                              </div>
                                          </div>
                                          <!-- /span -->
                                      </div>
                                   <!--    /row -->
                                      <div class="row">
                                          <div class="col-md-6">
                                              <div class="form-group">
                                                  <label class="control-label col-md-5 bold">营业性质：</label>
                                                  <div class="col-md-7">
                                                      <p class="form-control-static">{{company.businessNature}}</p>
                                                  </div>
                                              </div>
                                          </div>
                                          <!-- /span -->
                                          <div class="col-md-6">
                                              <div class="form-group">
                                                  <label class="control-label col-md-5 bold">企业性质：</label>
                                                  <div class="col-md-7">
                                                      <p class="form-control-static">{{company.comNature}}</p>
                                                  </div>
                                              </div>
                                          </div>
                                         <!--  /span -->
                                      </div>
                                     <!--  /row -->
                                      <div class="row">
                                          <div class="col-md-6">
                                              <div class="form-group">
                                                  <label class="control-label col-md-5 bold">经营类型：</label>
                                                  <div class="col-md-7">
                                                      <p class="form-control-static">{{company.businessType}}</p>
                                                  </div>
                                              </div>
                                          </div>
                                       <!--    /span -->
                                          <div class="col-md-6">
                                              <div class="form-group">
                                                  <label class="control-label col-md-5 bold">注册资金：</label>
                                                  <div class="col-md-7">
                                                      <p class="form-control-static">{{company.registeredCapital}}</p>
                                                  </div>
                                              </div>
                                          </div>
                                         <!--  /span -->
                                      </div>
                                    <!--   /row -->
                                      <div class="row">
                                          <div class="col-md-6">
                                              <div class="form-group">
                                                  <label class="control-label col-md-5 bold">法定代表人：</label>
                                                  <div class="col-md-7">
                                                      <p class="form-control-static">{{company.legalPerson}}</p>
                                                  </div>
                                              </div>
                                          </div>
                                          <div class="col-md-6">
                                              <div class="form-group">
                                                  <label class="control-label col-md-5 bold">备注：</label>
                                                  <div class="col-md-7">
                                                      <p class="form-control-static">{{company.remark}}</p>
                                                  </div>
                                              </div>
                                          </div>
                                         <!--  /span -->
                                      </div>
                                     <!--  /row -->
                                  </div>
                              </form>
                             <!--  END FORM -->
                          </div>
                      </div>
              		</div>
					<div class="tab-pane fade" id="tab_1_4">
						<div class="" id="tab2_c">

							<!-- START SAMPLE TABLE PORTLET-->
							<div class="">
								<div class="portlet-body">
									<div class="table-scrollable">
										<table
											class="table table-striped table-bordered table-advance table-hover">
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
												</tr>
											</thead>
											<tbody>
												<tr ng-repeat="companyContact in companyContacts">
													<td>{{companyContact.contactName}}</td>
													<td>{{companyContact.contactTitle}}</td>
													<td>{{companyContact.department}}</td>
													<td>{{companyContact.responsibility}}</td>
													<td>{{companyContact.contactTel}}</td>
													<td>{{companyContact.wechat}}</td>
													<td>{{companyContact.contactEmail}}</td>
													<td>{{companyContact.remark}}</td>
												</tr>
												<tr ng-if="companyContacts==undefined||companyContacts.length==0">
													<td colspan="8"  align="center">暂无数据</td>
												</tr>
											</tbody>
										</table>
									</div>
								</div>
							</div>
							<!-- END SAMPLE TABLE PORTLET-->
						</div>
					</div>
					<div class="tab-pane fade" id="tab_1_2">
						<!-- START SAMPLE TABLE PORTLET-->
						<div class="">
							<div class="portlet-body">
								<div class="table-scrollable">
									<table
										class="table table-striped table-bordered table-advance table-hover">
										<thead>
											<tr>
												<th>资质类型</th>
												<th>号码</th>
												<th>有效期</th>
												<th>附件</th>
												<th>备注</th>
												<th>状态</th>
											</tr>
										</thead>
										<tbody>
											<tr ng-repeat="companyQualification in companyQualifications">
												<td>{{companyQualification.qualificationName}}</td>
												<td>{{companyQualification.qualificationNum}}</td>
												<td>{{companyQualification.validityDate}}</td>
												<td
													ng-if="companyQualification.qualificatioImage==null||companyQualification.qualificatioImage==''">
													无</td>
												<td
													ng-if="companyQualification.qualificatioImage!=null&&companyQualification.qualificatioImage!=''">
													<a href="javascript:;"
													ng-click="downloadFile(companyQualification)">{{companyQualification.qualificatioImage.substring(companyQualification.qualificatioImage.indexOf("_")+1)}}</a>
												</td>
												<td>{{companyQualification.remark}}</td>
												<td><span ng-if="companyQualification.status==0"
													class="label label-sm label-success">正常</span> <span
													ng-if="companyQualification.status==1"
													class="label label-sm label-danger">已过期</span> <span
													ng-if="companyQualification.status==2"
													class="label label-sm label-warning">即将过期</span></td>

											</tr>
											<tr ng-if="companyQualifications==undefined||companyQualifications.length==0">
													<td colspan="7"  align="center">暂无数据</td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
						</div>
						<!-- END SAMPLE TABLE PORTLET-->
					</div>

					<div class="tab-pane fade" id="tab_1_3">
						<!-- START SAMPLE TABLE PORTLET-->
						<div class="portlet light ">
							<div class="portlet-title">
								<div class="caption">开票信息</div>
								<div class="actions"></div>
							</div>
							<div class="portlet-body">
									<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<label class="control-label col-md-5 bold">企业抬头：</label>
											<div class="col-md-7">
												<p class="form-control-static">{{company.corporatePresence}}</p>
											</div>
										</div>
									</div>
									<!-- /span -->
									<div class="col-md-6">
										<div class="form-group">
											<label class="control-label col-md-5 bold">注册电话：</label>
											<div class="col-md-7">
												<p class="form-control-static">{{company.tel}}</p>
											</div>
										</div>
									</div>
									<!-- /span -->
								</div>
								<!-- /row -->
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<label class="control-label col-md-5 bold">注册地址：</label>
											<div class="col-md-7">
												<p class="form-control-static">{{company.address}}</p>
											</div>
										</div>
									</div>
									<!-- /span -->
									<div class="col-md-6">
										<div class="form-group">
											<label class="control-label col-md-5 bold">开户行：</label>
											<div class="col-md-7">
												<p class="form-control-static">{{company.openingBank}}</p>
											</div>
										</div>
									</div>
									<!--   /span -->
									
								</div>
								<!-- /row -->
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<label class="control-label col-md-5 bold">银行账号：</label>
											<div class="col-md-7">
												<p class="form-control-static">{{company.accountNumber}}</p>
											</div>
										</div>
									</div>
									<!--   /span -->
									<div class="col-md-6">
										<div class="form-group">
											<label class="control-label col-md-5 bold">纳税人识别号：</label>
											<div class="col-md-7">
												<p class="form-control-static">{{company.taxpayeNumber}}</p>
											</div>
										</div>
									</div>
									<!--   /span -->
									</div>
									<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<label class="control-label col-md-5 bold">备注：</label>
											<div class="col-md-7">
												<p class="form-control-static">{{company.billRemark}}</p>
											</div>
										</div>
									</div>
									<!-- /span -->
								</div>
								<!-- /row -->
							
							</div>
							<div class="portlet-title">
							<div class="caption">收付款信息</div>
							<div class="actions"></div>
							</div>
							<div class="portlet-body">
								<div class="table-scrollable">
									<table
										class="table table-striped table-bordered table-advance table-hover">
										<thead>
											<tr>
												<th>银行</th>
												<th>户名</th>
												<th>账号</th>
												<th>备注</th>
											</tr>
										</thead>
										<tbody>
											<tr ng-repeat="finance in companyFinances">
												<td>{{finance.openingBank}}</td>
												<td>{{finance.accountName}}</td>
												<td>{{finance.accountNumber}}</td>
												<td>{{finance.remark}}</td>
											</tr>
											<tr ng-if="companyFinances==undefined||companyFinances.length==0">
													<td colspan="4" align="center">暂无数据</td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
						</div>
						<!-- END SAMPLE TABLE PORTLET-->
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- 企业其他信息展示End -->


<!-- 企业信息查看modal START -->
<div class="modal fade bs-modal-lg" id="viewCompany" role="basic"
	aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true"></button>
				<h4 class="modal-title">企业信息</h4>
			</div>
			<div class="modal-body">
				<!-- <div class="col-md-12"> -->
				<div class="">
					<div class="portlet-body form">
						<!--  BEGIN FORM -->
						<form class="form-horizontal" role="form">
							<div class="form-body">
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<label class="control-label col-md-5 bold">企业编号：</label>
											<div class="col-md-7">
												<p class="form-control-static">{{company.comNum}}</p>
											</div>
										</div>
									</div>
									<!-- /span -->
									<div class="col-md-6">
										<div class="form-group">
											<label class="control-label col-md-5 bold">企业名称：</label>
											<div class="col-md-7">
												<p class="form-control-static">{{company.comName}}</p>
											</div>
										</div>
									</div>
									<!--   /span -->
								</div>
								<!-- /row -->
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<label class="control-label col-md-5 bold">合作类型：</label>
											<div class="col-md-7">
												<p class="form-control-static">{{company.comTypeName}}</p>
											</div>
										</div>
									</div>
									<!--  /span -->
									<div class="col-md-6">
										<div class="form-group">
											<label class="control-label col-md-5 bold">企业简称：</label>
											<div class="col-md-7">
												<p class="form-control-static">{{company.abbreviation}}</p>
											</div>
										</div>
									</div>
									<!--  /span -->
								</div>
								<!--  /row -->
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<label class="control-label col-md-5 bold">营业性质：</label>
											<div class="col-md-7">
												<p class="form-control-static">{{company.businessNature}}</p>
											</div>
										</div>
									</div>
									<!--    /span -->
									<div class="col-md-6">
										<div class="form-group">
											<label class="control-label col-md-5 bold">企业性质：</label>
											<div class="col-md-7">
												<p class="form-control-static">{{company.comNature}}</p>
											</div>
										</div>
									</div>
									<!--   /span -->
								</div>
								<!--   /row -->
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<label class="control-label col-md-5 bold">经营类型：</label>
											<div class="col-md-7">
												<p class="form-control-static">{{company.businessType}}</p>
											</div>
										</div>
									</div>
									<!--      /span -->
									<div class="col-md-6">
										<div class="form-group">
											<label class="control-label col-md-5 bold">注册资金：</label>
											<div class="col-md-7">
												<p class="form-control-static">{{company.registeredCapital}}</p>
											</div>
										</div>
									</div>
									<!-- /span -->
								</div>
								<!--  /row -->
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<label class="control-label col-md-5 bold">企业法人姓名：</label>
											<div class="col-md-7">
												<p class="form-control-static">{{company.legalPerson}}</p>
											</div>
										</div>
									</div>
									<!-- /span -->
									<div class="col-md-6">
										<div class="form-group">
											<label class="control-label col-md-5 bold">注册地址：</label>
											<div class="col-md-7">
												<p class="form-control-static">{{company.address}}</p>
											</div>
										</div>
									</div>
									<!-- /span -->
								</div>
								<!-- /row -->
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<label class="control-label col-md-5 bold">纳税人识别号：</label>
											<div class="col-md-7">
												<p class="form-control-static">{{company.taxpayeNumber}}</p>
											</div>
										</div>
									</div>
									<!--   /span -->
									<div class="col-md-6">
										<div class="form-group">
											<label class="control-label col-md-5 bold">联系电话：</label>
											<div class="col-md-7">
												<p class="form-control-static">{{company.tel}}</p>
											</div>
										</div>
									</div>
									<!-- /span -->
								</div>
								<!-- /row -->
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<label class="control-label col-md-5 bold">维护人员：</label>
											<div class="col-md-7">
												<p class="form-control-static">{{company.contact}}</p>
											</div>
										</div>
									</div>
									<!--  /span -->
									<div class="col-md-6">
										<div class="form-group">
											<label class="control-label col-md-5 bold">备注：</label>
											<div class="col-md-7">
												<p class="form-control-static">{{company.remark}}</p>
											</div>
										</div>
									</div>
									<!-- /span -->
								</div>
								<!-- /row -->
							</div>
						</form>
						<!--  END FORM -->
					</div>
				</div>
				<!-- </div> -->
				<!-- </div> -->
			</div>
		</div>
	</div>
</div>
<!-- 企业信息查看modal END-->


<!-- 导入modal START -->
<div class="modal fade" id="import" role="import" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true"></button>
				<h4 class="modal-title">企业信息导入</h4>
			</div>
			<div class="modal-body">
				<!-- <div class="col-md-12"> -->
				<div class="">
					<div class="portlet-body form">
						<!--  BEGIN FORM -->
						<form class="form-horizontal" role="form">
							<div class="form-body">
								<form id="fileImport" method="post"
									enctype="multipart/form-data">
									<div class="row">
										<div class="col-md-2">
											<div class="form-group">
												<!-- <div class="col-md-4">
	                                               		</div> -->
												<div class="col-md-12">
													<button type="button" class="btn blue"
														ng-click="downloadImportTemp()">下载模板</button>
												</div>
											</div>
										</div>
										<div class="col-md-7">
											<div class="form-group">
												<div class="fileinput fileinput-new"
													data-provides="fileinput">
													<div class="input-group input-large">
														<div
															class="form-control uneditable-input input-fixed input-medium"
															data-trigger="fileinput">
															<i class="fa fa-file fileinput-exists"></i>&nbsp; <span
																class="fileinput-filename"> </span>
														</div>
														<span class="input-group-addon btn default btn-file"
															id="file_span"> <span class="fileinput-new">
																选择文件 </span> <span class="fileinput-exists">更换</span> <input
															type="file" file-model="excelFile" accept=".xls"
															name="...">
														</span> <a href="javascript:;" id="resetFile"
															class="input-group-addon btn red fileinput-exists"
															data-dismiss="fileinput"> 移除 </a>
													</div>
												</div>
											</div>
										</div>
										<div class="col-md-2">
											<div class="form-group">
												<div class="col-md-4"></div>
												<div class="col-md-8">
													<button type="button" class="btn blue"
														ng-click="uploadExcel()">导入</button>
												</div>
											</div>
										</div>

										<!--   /span -->
									</div>
									<!-- /row -->
								</form>
							</div>
						</form>
						<!--  END FORM -->
					</div>
				</div>
				<!-- </div> -->
				<!-- </div> -->
			</div>
		</div>
	</div>
</div>
<!-- 导入modal END-->

<!-- END MAIN CONTENT -->
<!-- BEGIN MAIN JS -->
<script>
	// TableDatatablesManaged.init();
	//$('.date-picker').datepicker();
</script>
<!-- END MAIN JS -->
