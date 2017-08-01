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
        <div class="portlet light bordered">
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
            <div class="portlet-body">
                 <div class="table-toolbar">
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
                             	<!-- <a href="javascript:;" class="btn btn-icon-only blue btn-outline">
                                          <i class="fa fa-search"></i>
                                 </a> -->
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
                </div> 
                
                
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
                                </div>
                                <div class="portlet-body dataTables_wrapper">
                                    <div class="table-responsive">
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
													<th>#</th>
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
													<th class="center">操作</th>
												</tr>
											</thead>
											<tbody>
												<tr ng-repeat="record in records.result" class="odd gradeX" ng-cloak>
													<td></td>
													<td>{{record.comNum}}</td>
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
														<a ng-click="editCompany(record.comId)" title="编辑"><i class="fa fa-edit"></i></a>&nbsp;&nbsp; 
														<a ng-click="deleteCompany(record.comId)" title="删除"><i class="fa fa-trash"></i></a>
													</td>
												</tr> 
											 </tbody>
										</table>
									</div>
									<!-- <div class="rows">
										<ul id="page"></ul>
										
									</div> -->
									<div class="row">
										<div class="col-md-5 col-sm-5">
											<div class="dataTables_info" id="sample_1_info" role="status"></div>
										</div>
										<div class="col-md-7 col-sm-7">
											<div class="dataTables_paginate paging_bootstrap_full_number"
												>
												<ul id="sample_1_paginator" ></ul>
											</div>
										</div>
									</div>
			
			
			
								</div>
                            </div>
                
                
                
                
            </div>
        </div>
        <!-- END EXAMPLE TABLE PORTLET-->
    </div>
</div>


 <div class="modal fade" id="basic" tabindex="-1" role="basic" aria-hidden="true" data-backdrop="static">
                                            <div class="modal-dialog" style="min-width:800px;">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                                                        <h4 class="modal-title" >新建企业信息</h4>
                                                    </div>
                                                    <div class="modal-body">
                                                      <div class="form-body" >
                                                      <form id="wareForm">
                                                       <!--  <h3 class="form-section">仓库信息</h3> -->
                                                        <div class="row">
                                                            <div class="col-md-6">
                                                                <div class="form-group">
                                                                    <label class="control-label col-md-3">企业编号</label>
                                                                    <div class="col-md-9 input-icon right">
                                                                        <input type="text" ng-model="company.comNum" class="form-control" placeholder="">
                                                                        <span class="help-block"></span>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            
                                                            <!--/span-->
                                                            <div class="col-md-6">
                                                                <div class="form-group">
                                                                    <label class="control-label col-md-3">企业名称</label>
                                                                    <div class="col-md-9">
                                                                         <input type="text" ng-model="company.comName" class="form-control" placeholder="">
                                                                         <span class="help-block"></span>
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
                                                                         <input type="text"  ng-model="company.comType" class="form-control" placeholder="">
                                                                         <span class="help-block"></span> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <!--/span-->
                                                            <div class="col-md-6">
                                                                <div class="form-group">
                                                                    <label class="control-label col-md-3">企业简称</label>
                                                                    <div class="col-md-9">
                                                                        <input type="text"  ng-model="company.abbreviation" class="form-control" placeholder=""> 
                                                                        <span class="help-block"></span>
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
                                                                         <input type="text"  ng-model="company.businessNature" class="form-control" placeholder="">
                                                                         <span class="help-block"></span> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="col-md-6">
                                                                <div class="form-group">
                                                                    <label class="control-label col-md-3">企业性质</label>
                                                                    <div class="col-md-9">
                                                                         <input type="text"  ng-model="company.comNature" class="form-control" placeholder=""> 
                                                                         <span class="help-block"></span>
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
                                                                         <input type="text"  ng-model="company.businessType" class="form-control" placeholder="">
                                                                         <span class="help-block"></span> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="col-md-6">
                                                                <div class="form-group">
                                                                    <label class="control-label col-md-3">注册资金</label>
                                                                    <div class="col-md-9">
                                                                         <input type="text"  ng-model="company.registeredCapital" class="form-control" placeholder=""> 
                                                                         <span class="help-block"></span>
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
                                                                         <input type="text"  ng-model="company.legalPerson" class="form-control" placeholder="">
                                                                         <span class="help-block"></span> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="col-md-6">
                                                                <div class="form-group">
                                                                    <label class="control-label col-md-3">注册地址</label>
                                                                    <div class="col-md-9">
                                                                         <input type="text"  ng-model="company.address" class="form-control" placeholder=""> 
                                                                         <span class="help-block"></span>
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
                                                                         <input type="text"  ng-model="company.taxpayeNumber" class="form-control" placeholder="">
                                                                         <span class="help-block"></span> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="col-md-6">
                                                                <div class="form-group">
                                                                    <label class="control-label col-md-3">联系电话</label>
                                                                    <div class="col-md-9">
                                                                         <input type="text"  ng-model="company.tel" class="form-control" placeholder=""> 
                                                                         <span class="help-block"></span>
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
                                                                         <input type="text"  ng-model="company.contact" class="form-control" placeholder="">
                                                                         <span class="help-block"></span> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="col-md-6">
                                                                <div class="form-group">
                                                                    <label class="control-label col-md-3">备注</label>
                                                                    <div class="col-md-9">
                                                                         <input type="text"  ng-model="company.remark" class="form-control" placeholder=""> 
                                                                         <span class="help-block"></span>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        </form>
                                                    </div>
                                                    </div>
                                                    <div class="modal-footer">
                                                        <button type="button" class="btn btn-outline sbold red" data-dismiss="modal">取消</button>
                                                        <button type="button" class="btn btn-outline sbold blue" ng-click="saveCompany()">保存</button>
                                                    </div>
                                                    
                                                </div>
                                                <!-- /.modal-content -->
                                            </div>
                                            <!-- /.modal-dialog -->
                                        </div>


<!-- END MAIN CONTENT -->
<!-- BEGIN MAIN JS -->
<script>
   // TableDatatablesManaged.init();
   //$('.date-picker').datepicker();
</script> 
<!-- END MAIN JS -->