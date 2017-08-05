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
            <a ui-sref="datatablesmanaged">企业信息</a>
        </li>
    </ul>
<!--     <div class="page-toolbar">
        <div class="btn-group pull-right">
            <button type="button" class="btn btn-fit-height grey-salt dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-delay="1000" data-close-others="true"> Actions
                <i class="fa fa-angle-down"></i>
            </button>
            <ul class="dropdown-menu pull-right" role="menu">
                <li>
                    <a href="#">
                        <i class="icon-user"></i> New User </a>
                </li>
                <li>
                    <a href="#">
                        <i class="icon-present"></i> New Event
                        <span class="badge badge-success">4</span>
                    </a>
                </li>
                <li>
                    <a href="#">
                        <i class="icon-basket"></i> New order </a>
                </li>
                <li class="divider"> </li>
                <li>
                    <a href="#">
                        <i class="icon-flag"></i> Pending Orders
                        <span class="badge badge-danger">4</span>
                    </a>
                </li>
                <li>
                    <a href="#">
                        <i class="icon-users"></i> Pending Users
                        <span class="badge badge-warning">12</span>
                    </a>
                </li>
            </ul>
        </div>
    </div> -->
</div>
<!-- END PAGE HEADER-->
<!-- BEGIN MAIN CONTENT -->
<div class="row">
    <div class="col-md-12">
        <!-- BEGIN EXAMPLE TABLE PORTLET-->
        <!-- <div class="portlet light"> -->
           <!--  <div class="portlet-title">
                <div class="caption font-dark">
                    <i class="icon-settings font-dark"></i>
                    <span class="caption-subject bold uppercase"> 仓库信息列表 </span>
                </div>
                <div class="actions">
                    <div class="btn-group btn-group-devided" data-toggle="buttons">
                        <label class="btn btn-transparent dark btn-outline btn-circle btn-sm active">
                            <input type="radio" name="options" class="toggle" id="option1">Actions</label>
                        <label class="btn btn-transparent dark btn-outline btn-circle btn-sm">
                            <input type="radio" name="options" class="toggle" id="option2">Settings</label>
                    </div>
                </div>
            </div> -->
          <!-- <div class="portlet-body"> -->
  <!--               <div class="table-toolbar">
                    <div class="row">
                        <div class="col-md-7">
                            <div class="btn-group">
                                <button id="sample_editable_1_new" class="btn sbold blue" ui-sref="companyAdd">
                                    <i class="fa fa-plus"></i> 新建
                                </button>
                            </div>
                        </div>
                        <div class="col-md-5">
                            <div class="btn-group pull-right">
                             	<a href="javascript:;" class="btn btn-icon-only blue btn-outline">
                                          <i class="fa fa-search"></i>
                                 </a>
                                 <div class="input-group input-medium pull-left">
                                                    <input ng-model="searchKey" type="text" class="form-control" placeholder="请输入关键字">
                                                    <span class="input-group-btn">
                                                        <button class="btn blue   btn-outline" type="button" ng-click="search()" ><i class="fa fa-search"></i></button>
                                                    </span>
                                  </div>
                               <a href="javascript:;" class="btn blue btn-outline">筛选
                                      <span class="glyphicon glyphicon-cog"> </span>
                                </a>
                               <button class="btn blue  btn-outline dropdown-toggle pull-left" data-toggle="dropdown">更多
                                    <i class="fa fa-angle-down"></i>
                                </button>
                                <ul class="dropdown-menu pull-right">
                                    <li>
                                        <a href="javascript:;">
                                            <i class="fa fa-file-excel-o"></i>导出</a>
                                    </li>
                                    <li>
                                        <a href="javascript:;">
                                            <i class="fa fa-print"></i>打印</a>
                                    </li>
                                </ul>
							</div>
                        </div>
                    </div>
                </div>  -->
                
                
                <div class="portlet box blue">
                                <div class="portlet-title">
                                    <div class="caption">
                                        <i class="fa fa-cogs"></i>企业信息列表</div>
                                   <!--  <div class="actions">
                                        <a class="btn btn-default btn-sm" data-toggle="modal" data-target="#basic">
                                            <i class="fa fa-plus"></i> 新增 </a>
                                        <a href="javascript:;" class="btn btn-default btn-sm">
                                            <i class="fa fa-print"></i> 打印 </a>
                                    </div> -->
                                    <div class="actions">
										<a href="javascript:;" 
											 class="btn btn-default btn-sm btn-circle"  ng-click="toAddCompany()">
											<i class="fa fa-plus"></i> 添加
										</a> <a href="javascript:;" 
											 class="btn btn-default btn-sm btn-circle"  ng-click="toEditCompany()">
											<i class="fa fa-edit"></i> 编辑
										</a> <a href="javascript:;" 
											class="btn btn-default btn-sm btn-circle" ng-click="deleteCompanyBatch()"> <i
											class="fa fa-minus"></i> 删除
										</a>
										<div class="btn-group">
											<a class="btn btn-default btn-outline btn-circle"
												href="javascript:;" data-toggle="dropdown"> <i
												class="fa fa-share"></i> <span class="hidden-xs"> 其它 </span> <i
												class="fa fa-angle-down"></i>
											</a>
											<ul class="dropdown-menu pull-right" id="sample_3_tools">
												<li><a href="javascript:;" data-action="0"
													class="tool-action"> <i class="icon-printer"></i> Print
												</a></li>
												<li><a href="javascript:;" data-action="1"
													class="tool-action"> <i class="icon-check"></i> Copy
												</a></li>
												<li><a href="javascript:;" data-action="2"
													class="tool-action"> <i class="icon-doc"></i> PDF
												</a></li>
												<li><a href="javascript:;" data-action="3"
													class="tool-action"> <i class="icon-paper-clip"></i> Excel
												</a></li>
												<li><a href="javascript:;" data-action="4"
													class="tool-action"> <i class="icon-cloud-upload"></i> CSV
												</a></li>
												<li class="divider"></li>
												<li><a href="javascript:;" data-action="5"
													class="tool-action"> <i class="icon-refresh"></i> Reload
												</a></li>
												</li>
											</ul>
										</div>
									</div>
                                </div>
                                <div class="portlet-body">
                                    <!-- <div class="table-responsive"> -->
										<table   class="table table-bordered" id="sample_1">
											<!-- <thead>
												<tr>
													<th data-mdata="warehouseNum" data-sclass="ceneter">仓库编号</th>
													<th data-mdata="warehouseName" data-sclass="ceneter">仓库名称</th>
													<th data-mdata="area" data-sclass="ceneter">面积</th>
													<th data-mdata="address" data-sclass="ceneter">备注</th>
												</tr>
											</thead> -->
											<thead>
												<tr>
													<th><input name="select_all"
															value="1" id="example-select-all" type="checkbox" /></th>
													<th>企业编码</th>
													<th>企业名称</th>
													<th>企业性质</th>
													<th>注册资金</th>
													<th>经营性质</th>
													<th>企业法人</th>
													<th>地址</th>
													<th>业务员</th>
													<th>采购商分类</th>
													<th>状态</th>
													 <!-- <th class="center">操作</th> 
													 <th class="center">操作</th>  -->
												</tr>
											</thead>
											<tbody>
												<!-- <tr ng-repeat="record in records.result" class="odd gradeX" ng-cloak>
													<td></td>
													<td><a data-target="#viewCompany" data-id="record.comId"  data-toggle="modal">{{record.comNum}}</a></td>
													<td><a   ng-click="showCompanyInfo(record.comId)">{{record.comNum}}</a></td>
													<td>{{record.comName}}</td>
													<td>{{record.comNature}}</td>
													<td>{{record.registeredCapital}}</td>
													<td>{{record.businessNature}}</td>
													<td>{{record.legalPerson}}</td>
													<td>{{record.address}}</td>
													<td>----</td>
													<td>{{record.comType}}</td>
													<td>----</td>
													<td class="center">
														<a ui-sref="companyAdd({comId:record.comId})" title="编辑"><i class="fa fa-edit"></i></a>&nbsp;&nbsp; 
														<a ng-click="deleteCompany(record.comId)" title="删除"><i class="fa fa-trash"></i></a>
													</td>
												</tr>  -->
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
          <ul class="nav nav-pills">
              <li class="active">
                  <a data-target="#tab_1_1" data-toggle="tab">基本信息</a>
              </li>
              <li>
                  <a data-target="#tab_1_2" data-toggle="tab">企业资质</a>
              </li>
              <li>
                  <a data-target="#tab_1_3" data-toggle="tab">联系人</a>
              </li>
          </ul>
          <div class="tab-content">
              <div class="tab-pane fade active in" id="tab_1_1">
                    <div class="">
                          <div class="portlet-body form">
                              <!-- BEGIN FORM-->
                              <form class="form-horizontal" >
                                  <div class="form-body">
                                      <div class="row">
                                          <div class="col-md-6">
                                              <div class="form-group">
                                                  <label class="control-label col-md-5">企业编号：</label>
                                                  <div class="col-md-7">
                                                      <p class="form-control-static">{{company.comNum}}</p>
                                                  </div>
                                              </div>
                                          </div>
                                          <!--/span-->
                                          <div class="col-md-6">
                                              <div class="form-group">
                                                  <label class="control-label col-md-5">企业名称：</label>
                                                  <div class="col-md-7">
                                                      <p class="form-control-static">{{company.comName}}</p>
                                                  </div>
                                              </div>
                                          </div>
                                          <!--/span-->
                                      </div>
                                      <!--/row-->
                                      <div class="row">
                                          <div class="col-md-6">
                                              <div class="form-group">
                                                  <label class="control-label col-md-5">企业类型：</label>
                                                  <div class="col-md-7">
                                                      <p class="form-control-static">{{company.comType}}</p>
                                                  </div>
                                              </div>
                                          </div>
                                          <!--/span-->
                                          <div class="col-md-6">
                                              <div class="form-group">
                                                  <label class="control-label col-md-5">企业简称：</label>
                                                  <div class="col-md-7">
                                                      <p class="form-control-static">{{company.abbreviation}}</p>
                                                  </div>
                                              </div>
                                          </div>
                                          <!--/span-->
                                      </div>
                                      <!--/row-->
                                      <div class="row">
                                          <div class="col-md-6">
                                              <div class="form-group">
                                                  <label class="control-label col-md-5">营业性质：</label>
                                                  <div class="col-md-7">
                                                      <p class="form-control-static">{{company.businessNature}}</p>
                                                  </div>
                                              </div>
                                          </div>
                                          <!--/span-->
                                          <div class="col-md-6">
                                              <div class="form-group">
                                                  <label class="control-label col-md-5">企业性质：</label>
                                                  <div class="col-md-7">
                                                      <p class="form-control-static">{{company.comNature}}</p>
                                                  </div>
                                              </div>
                                          </div>
                                          <!--/span-->
                                      </div>
                                      <!--/row-->
                                      <div class="row">
                                          <div class="col-md-6">
                                              <div class="form-group">
                                                  <label class="control-label col-md-5">经营类型：</label>
                                                  <div class="col-md-7">
                                                      <p class="form-control-static">{{company.businessType}}</p>
                                                  </div>
                                              </div>
                                          </div>
                                          <!--/span-->
                                          <div class="col-md-6">
                                              <div class="form-group">
                                                  <label class="control-label col-md-5">注册资金：</label>
                                                  <div class="col-md-7">
                                                      <p class="form-control-static">{{company.registeredCapital}}</p>
                                                  </div>
                                              </div>
                                          </div>
                                          <!--/span-->
                                      </div>
                                      <!--/row-->
                                      <div class="row">
                                          <div class="col-md-6">
                                              <div class="form-group">
                                                  <label class="control-label col-md-5">企业法人姓名：</label>
                                                  <div class="col-md-7">
                                                      <p class="form-control-static">{{company.legalPerson}}</p>
                                                  </div>
                                              </div>
                                          </div>
                                          <!--/span-->
                                          <div class="col-md-6">
                                              <div class="form-group">
                                                  <label class="control-label col-md-5">注册地址：</label>
                                                  <div class="col-md-7">
                                                      <p class="form-control-static">{{company.address}}</p>
                                                  </div>
                                              </div>
                                          </div>
                                          <!--/span-->
                                      </div>
                                      <!--/row-->
                                      <div class="row">
                                          <div class="col-md-6">
                                              <div class="form-group">
                                                  <label class="control-label col-md-5">纳税人识别号：</label>
                                                  <div class="col-md-7">
                                                      <p class="form-control-static">{{company.taxpayeNumber}}</p>
                                                  </div>
                                              </div>
                                          </div>
                                          <!--/span-->
                                          <div class="col-md-6">
                                              <div class="form-group">
                                                  <label class="control-label col-md-5">联系电话：</label>
                                                  <div class="col-md-7">
                                                      <p class="form-control-static">{{company.tel}}</p>
                                                  </div>
                                              </div>
                                          </div>
                                          <!--/span-->
                                      </div>
                                      <!--/row-->
                                      <div class="row">
                                          <div class="col-md-6">
                                              <div class="form-group">
                                                  <label class="control-label col-md-5">维护人员：</label>
                                                  <div class="col-md-7">
                                                      <p class="form-control-static">{{company.contact}}</p>
                                                  </div>
                                              </div>
                                          </div>
                                          <!--/span-->
                                          <div class="col-md-6">
                                              <div class="form-group">
                                                  <label class="control-label col-md-5">备注：</label>
                                                  <div class="col-md-7">
                                                      <p class="form-control-static">{{company.remark}}</p>
                                                  </div>
                                              </div>
                                          </div>
                                          <!--/span-->
                                      </div>
                                      <!--/row-->
                                  </div>
                              </form>
                              <!-- END FORM-->
                          </div>
                      </div>
              </div>
              <div class="tab-pane fade" id="tab_1_2">
                    <div class="" id="tab2_c">
                          <div class="portlet-body form">
                              <!-- BEGIN FORM-->
                              <form class="form-horizontal" >施工中
                                  <div class="form-body" ng-repeat="companyQualification in companyQualifications">
                                      <div class="row">
                                          <div class="col-md-3">
                                              <div class="form-group">
                                                  <label class="control-label">{{companyQualification.qualificationName}}</label>
                                              </div>
                                          </div>
                                          <!--/span-->
                                          <div class="col-md-3">
                                              <div class="form-group">
                                                  <label class="control-label">{{companyQualification.qualificationNum}}</label>
                                              </div>
                                          </div>
                                          <!--/span-->
                                          <div class="col-md-3">
                                              <div class="form-group">
                                                  <label class="control-label">{{companyQualification.validityDate}}</label>
                                              </div>
                                          </div>
                                          <!--/span-->
                                          <div class="col-md-3">
                                              <div class="form-group">
                                                  <label class="control-label">施工中</label>
                                              </div>
                                          </div>
                                          <!--/span-->
                                      </div>
                                      <!--/row-->
                                  </div>
                              </form>
                              <!-- END FORM-->
                          </div>
                      </div>
              </div>
              <div class="tab-pane fade" id="tab_1_3">
              		<div class="">
                          <div class="portlet-body form">
                              <!-- BEGIN FORM-->
                              <form class="form-horizontal">施工中
                                  <div class="form-body" ng-repeat="companyContact in companyContacts">
                                      <div class="row">
                                          <div class="col-md-2">
                                              <div class="form-group">
                                                  <label class="control-label">{{companyContact.contactName}}</label>
                                              </div>
                                          </div>
                                          <!--/span-->
                                          <div class="col-md-1">
                                              <div class="form-group">
                                                  <label class="control-label">{{companyContact.contactTitle}}</label>
                                              </div>
                                          </div>
                                          <!--/span-->
                                          <div class="col-md-1">
                                              <div class="form-group">
                                                  <label class="control-label">{{companyContact.department}}</label>
                                              </div>
                                          </div>
                                          <!--/span-->
                                          <div class="col-md-2">
                                              <div class="form-group">
                                                  <label class="control-label">{{companyContact.contactTel}}</label>
                                              </div>
                                          </div>
                                          <!--/span-->
                                          <div class="col-md-2">
                                              <div class="form-group">
                                                  <label class="control-label">{{companyContact.wechat}}</label>
                                              </div>
                                          </div>
                                          <!--/span-->
                                          <div class="col-md-2">
                                              <div class="form-group">
                                                  <label class="control-label">{{companyContact.contactEmail}}</label>
                                              </div>
                                          </div>
                                          <!--/span-->
                                         		<div class="col-md-2">
                                              <div class="form-group">
                                                  <label class="control-label">{{companyContact.remark}}</label>
                                              </div>
                                          </div>
                                          <!--/span-->
                                      </div>
                                      <!--/row-->
                                  </div>
                              </form>
                              <!-- END FORM-->
                          </div>
                   </div>
              </div>
          </div>
      </div>
  </div>
	</div>		
</div>
<!-- 企业其他信息展示End -->


<!-- 企业信息查看modal START --><!-- 
<div class="modal fade" id="viewCompany" role="basic" aria-hidden="true">
     <div class="modal-dialog" style="width: 750px;">
	    <div class="modal-content">
	 		<div class="modal-header">
	            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
	            <h4 class="modal-title" >企业信息</h4>
	        </div>
	        <div class="modal-body">
	          		<div class="col-md-12">
					   <div class="portlet light ">
					       <div class="portlet-body">
					           <ul class="nav nav-tabs">
					               <li class="active">
					                   <a data-target="#tab_1_1" data-toggle="tab">基本信息</a>
					               </li>
					               <li>
					                   <a data-target="#tab_1_2" data-toggle="tab">企业资质</a>
					               </li>
					               <li>
					                   <a data-target="#tab_1_3" data-toggle="tab">联系人</a>
					               </li>
					           </ul>
					           <div class="tab-content">
					               <div class="tab-pane fade active in" id="tab_1_1">
					                     <div class="portlet light">
					                           <div class="portlet-body form">
					                               BEGIN FORM
					                               <form class="form-horizontal" role="form">
					                                   <div class="form-body">
					                                       <div class="row">
					                                           <div class="col-md-6">
					                                               <div class="form-group">
					                                                   <label class="control-label col-md-5">企业编号：</label>
					                                                   <div class="col-md-7">
					                                                       <p class="form-control-static" >{{company.comNum}}</p>
					                                                   </div>
					                                               </div>
					                                           </div>
					                                           /span
					                                           <div class="col-md-6">
					                                               <div class="form-group">
					                                                   <label class="control-label col-md-5">企业名称：</label>
					                                                   <div class="col-md-7">
					                                                       <p class="form-control-static">{{company.comName}}</p>
					                                                   </div>
					                                               </div>
					                                           </div>
					                                           /span
					                                       </div>
					                                       /row
					                                       <div class="row">
					                                           <div class="col-md-6">
					                                               <div class="form-group">
					                                                   <label class="control-label col-md-5">企业类型：</label>
					                                                   <div class="col-md-7">
					                                                       <p class="form-control-static">{{company.comType}}</p>
					                                                   </div>
					                                               </div>
					                                           </div>
					                                           /span
					                                           <div class="col-md-6">
					                                               <div class="form-group">
					                                                   <label class="control-label col-md-5">企业简称：</label>
					                                                   <div class="col-md-7">
					                                                       <p class="form-control-static">{{company.abbreviation}}</p>
					                                                   </div>
					                                               </div>
					                                           </div>
					                                           /span
					                                       </div>
					                                       /row
					                                       <div class="row">
					                                           <div class="col-md-6">
					                                               <div class="form-group">
					                                                   <label class="control-label col-md-5">营业性质：</label>
					                                                   <div class="col-md-7">
					                                                       <p class="form-control-static">{{company.businessNature}}</p>
					                                                   </div>
					                                               </div>
					                                           </div>
					                                           /span
					                                           <div class="col-md-6">
					                                               <div class="form-group">
					                                                   <label class="control-label col-md-5">企业性质：</label>
					                                                   <div class="col-md-7">
					                                                       <p class="form-control-static">{{company.comNature}}</p>
					                                                   </div>
					                                               </div>
					                                           </div>
					                                           /span
					                                       </div>
					                                       /row
					                                       <div class="row">
					                                           <div class="col-md-6">
					                                               <div class="form-group">
					                                                   <label class="control-label col-md-5">经营类型：</label>
					                                                   <div class="col-md-7">
					                                                       <p class="form-control-static">{{company.businessType}}</p>
					                                                   </div>
					                                               </div>
					                                           </div>
					                                           /span
					                                           <div class="col-md-6">
					                                               <div class="form-group">
					                                                   <label class="control-label col-md-5">注册资金：</label>
					                                                   <div class="col-md-7">
					                                                       <p class="form-control-static">{{company.registeredCapital}}</p>
					                                                   </div>
					                                               </div>
					                                           </div>
					                                           /span
					                                       </div>
					                                       /row
					                                       <div class="row">
					                                           <div class="col-md-6">
					                                               <div class="form-group">
					                                                   <label class="control-label col-md-5">企业法人姓名：</label>
					                                                   <div class="col-md-7">
					                                                       <p class="form-control-static">{{company.legalPerson}}</p>
					                                                   </div>
					                                               </div>
					                                           </div>
					                                           /span
					                                           <div class="col-md-6">
					                                               <div class="form-group">
					                                                   <label class="control-label col-md-5">注册地址：</label>
					                                                   <div class="col-md-7">
					                                                       <p class="form-control-static">{{company.address}}</p>
					                                                   </div>
					                                               </div>
					                                           </div>
					                                           /span
					                                       </div>
					                                       /row
					                                       <div class="row">
					                                           <div class="col-md-6">
					                                               <div class="form-group">
					                                                   <label class="control-label col-md-5">纳税人识别号：</label>
					                                                   <div class="col-md-7">
					                                                       <p class="form-control-static">{{company.taxpayeNumber}}</p>
					                                                   </div>
					                                               </div>
					                                           </div>
					                                           /span
					                                           <div class="col-md-6">
					                                               <div class="form-group">
					                                                   <label class="control-label col-md-5">联系电话：</label>
					                                                   <div class="col-md-7">
					                                                       <p class="form-control-static">{{company.tel}}</p>
					                                                   </div>
					                                               </div>
					                                           </div>
					                                           /span
					                                       </div>
					                                       /row
					                                       <div class="row">
					                                           <div class="col-md-6">
					                                               <div class="form-group">
					                                                   <label class="control-label col-md-5">维护人员：</label>
					                                                   <div class="col-md-7">
					                                                       <p class="form-control-static">{{company.contact}}</p>
					                                                   </div>
					                                               </div>
					                                           </div>
					                                           /span
					                                           <div class="col-md-6">
					                                               <div class="form-group">
					                                                   <label class="control-label col-md-5">备注：</label>
					                                                   <div class="col-md-7">
					                                                       <p class="form-control-static">{{company.remark}}</p>
					                                                   </div>
					                                               </div>
					                                           </div>
					                                           /span
					                                       </div>
					                                       /row
					                                   </div>
					                               </form>
					                               END FORM
					                           </div>
					                       </div>
					               </div>
					               <div class="tab-pane fade" id="tab_1_2">
					                     <div class="portlet light">
					                           <div class="portlet-body form">
					                               BEGIN FORM
					                               <form class="form-horizontal" role="form">
					                                   <div class="form-body" ng-repeat="companyQualification in companyQualifications">
					                                       <div class="row">
					                                           <div class="col-md-3">
					                                               <div class="form-group">
					                                                   <label class="control-label">{{companyQualification.qualificationName}}</label>
					                                               </div>
					                                           </div>
					                                           /span
					                                           <div class="col-md-3">
					                                               <div class="form-group">
					                                                   <label class="control-label">{{companyQualification.qualificationNum}}</label>
					                                               </div>
					                                           </div>
					                                           /span
					                                           <div class="col-md-3">
					                                               <div class="form-group">
					                                                   <label class="control-label">{{companyQualification.validityDate}}</label>
					                                               </div>
					                                           </div>
					                                           /span
					                                           <div class="col-md-3">
					                                               <div class="form-group">
					                                                   <label class="control-label">施工中</label>
					                                               </div>
					                                           </div>
					                                           /span
					                                       </div>
					                                       /row
					                                   </div>
					                               </form>
					                               END FORM
					                           </div>
					                       </div>
					               </div>
					               <div class="tab-pane fade" id="tab_1_3">
					               		<div class="portlet light">
					                           <div class="portlet-body form">
					                               BEGIN FORM
					                               <form class="form-horizontal" role="form">
					                                   <div class="form-body" ng-repeat="companyContact in companyContacts">
					                                       <div class="row">
					                                           <div class="col-md-2">
					                                               <div class="form-group">
					                                                   <label class="control-label">{{companyContact.contactName}}</label>
					                                               </div>
					                                           </div>
					                                           /span
					                                           <div class="col-md-1">
					                                               <div class="form-group">
					                                                   <label class="control-label">{{companyContact.contactTitle}}</label>
					                                               </div>
					                                           </div>
					                                           /span
					                                           <div class="col-md-1">
					                                               <div class="form-group">
					                                                   <label class="control-label">{{companyContact.department}}</label>
					                                               </div>
					                                           </div>
					                                           /span
					                                           <div class="col-md-2">
					                                               <div class="form-group">
					                                                   <label class="control-label">{{companyContact.contactTel}}</label>
					                                               </div>
					                                           </div>
					                                           /span
					                                           <div class="col-md-2">
					                                               <div class="form-group">
					                                                   <label class="control-label">{{companyContact.wechat}}</label>
					                                               </div>
					                                           </div>
					                                           /span
					                                           <div class="col-md-2">
					                                               <div class="form-group">
					                                                   <label class="control-label">{{companyContact.contactEmail}}</label>
					                                               </div>
					                                           </div>
					                                           /span
				                                           		<div class="col-md-2">
					                                               <div class="form-group">
					                                                   <label class="control-label">{{companyContact.remark}}</label>
					                                               </div>
					                                           </div>
					                                           /span
					                                       </div>
					                                       /row
					                                   </div>
					                               </form>
					                               END FORM
					                           </div>
					                    </div>
					               </div>
					           </div>
					       </div>
					   </div>
					</div>
	        </div>
	    </div>
    </div>
</div>
 --><!-- 企业信息查看modal END-->

<!-- END MAIN CONTENT -->
<!-- BEGIN MAIN JS -->
<script>
   // TableDatatablesManaged.init();
   //$('.date-picker').datepicker();
</script> 
<!-- END MAIN JS -->