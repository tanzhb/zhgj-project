<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
	<meta http-equiv="Content-Type" content="multipart/form-data; charset=utf-8" />
</head>
<!-- BEGIN PAGE HEADER-->
<h3 class="page-title"> 需求计划
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
            <a ui-sref="datatablesmanaged">需求计划</a>
        </li>
    </ul>
</div>
<!-- END PAGE HEADER-->
<!-- BEGIN MAIN CONTENT -->
<div class="row">
    <div class="col-md-12">
        <!-- BEGIN EXAMPLE TABLE PORTLET-->
                <div class="portlet light">
                                <!-- <div class="portlet-title">
                                      <div class="caption">
                                        	需求计划
                                      </div>
                                </div> -->
                                <div class="portlet-body" ng-repeat="demandPlan in demandPlans">
										 <div class="portlet">
				                                <div class="portlet-title">
				                                    <div class="caption">
				                                    	上海某公司
				                                    </div>
				                                    <div class="tools">
				                                         <button  class="btn blue  btn-outline  btn-sm " ng-click="addDemandPlan()">
                                            				<i class="fa fa-plus"></i>新增</button>
				                                    </div>
				                                </div>
				                                <div class="portlet-body">
				                                	<div class="row">
				                                	<div class="col-md-12">
					                                 	<div class="table-scrollable">
					                                        <table class="table table-striped table-bordered table-advance table-hover">
					                                            <thead>
					                                                <tr>
					                                                   	<th><input name="select_all" class="dt-body-center"
																				value="1" id="example-select-all" type="checkbox" /></th>
																		<th>物料编号</th>
																		<th>产品名称</th>
																		<th>规格型号</th>
																		<th>供应商</th>
																		<th>交付日期</th>
																		<th>数量</th>
																		<th>交付地点</th>
																		<th>距离交付 （天）</th>
					                                                </tr>
					                                            </thead>
					                                            <tbody ng-repeat="materiel in demandPlan.materiels">
					                                                <tr>
					                                                    <td>{{materiel.materielSerial}}</td>
					                                                    <td>{{materiel.materielSerial}}</td>
					                                                    <td>{{materiel.materielSerial}}</td>
					                                                    <td>{{materiel.materielSerial}}</td>
					                                                    <td>{{materiel.supplyMaterielSerial}}</td>
					                                                    <td>{{materiel.deliveryDate}}</td>
					                                                    <td>{{materiel.amount}}</td>
					                                                    <td>{{materiel.deliveryAddress}}</td>
					                                                    <td>10</td>
					                                                </tr>
					                                            </tbody>
					                                        </table>
					                                    </div>
					                                </div>
				                                    </div>
				                                    <div class="row">
					                                    <div class="col-md-12">
															<div class="col-md-5 col-sm-5">
																<div class="dataTables_info" id="demandPlanTab_info" role="status"></div>
															</div>
															<div class="col-md-7 col-sm-7">
																<div class="dataTables_paginate paging_bootstrap_full_number">
																	<ul id="demandPlanTab_paginator" ></ul>
																</div>
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



<!-- 导入modal START -->
<div class="modal fade" id="import" role="import" aria-hidden="true">
     <div class="modal-dialog" >
	    <div class="modal-content">
	 		<div class="modal-header">
	            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
	            <h4 class="modal-title" >企业信息导入</h4>
	        </div>
	        <div class="modal-body">
	          		<!-- <div class="col-md-12"> -->
	          		 <div class="">
                           <div class="portlet-body form">
                              <!--  BEGIN FORM -->
                               <form class="form-horizontal" role="form">
                                   <div class="form-body">
                                   		<form id="fileImport" method="post" enctype="multipart/form-data" >
	                                       <div class="row">
	                                           <div class="col-md-2">
	                                               <div class="form-group">
	                                               		<!-- <div class="col-md-4">
	                                               		</div> -->
	                                               		<div class="col-md-12">
	                                               			<button type="button" class="btn red" ng-click="downloadImportTemp()">下载模板</button>
	                                               		</div>
	                                               </div>
	                                           </div>
	                                           <div class="col-md-7">
	                                               <div class="form-group">
	                                               		 <div class="fileinput fileinput-new" data-provides="fileinput">
	                                                        <div class="input-group input-large">
	                                                            <div class="form-control uneditable-input input-fixed input-medium" data-trigger="fileinput">
	                                                                <i class="fa fa-file fileinput-exists"></i>&nbsp;
	                                                                <span class="fileinput-filename"> </span>
	                                                            </div>
	                                                            <span class="input-group-addon btn default btn-file" id="file_span">
	                                                                <span class="fileinput-new"> 选择文件 </span>
	                                                                <span class="fileinput-exists">更换</span>
	                                                                <input type="file" file-model="excelFile" accept=".xls" name="..."> </span>
	                                                            <a href="javascript:;" id="resetFile" class="input-group-addon btn red fileinput-exists" data-dismiss="fileinput"> 移除 </a>
	                                                        </div>
	                                                    </div>
	                                               </div>
	                                           </div>
	                                            <div class="col-md-2">
	                                               <div class="form-group">
	                                               		<div class="col-md-4">
	                                               			
	                                               		</div>
	                                               		<div class="col-md-8">
	                                               			<button type="button" class="btn red" ng-click="uploadExcel()">导入</button>
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
