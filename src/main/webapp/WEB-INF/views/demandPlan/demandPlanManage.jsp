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
            <a>销售订单</a>
            <i class="fa fa-angle-right"></i>
        </li>
        <li>
            <a ui-sref="datatablesmanaged">需求计划</a>
        </li>
    </ul>
</div>
<!-- END PAGE HEADER-->
<!-- BEGIN MAIN CONTENT -->
<div class="tabbable-line">
    <ul class="nav nav-tabs">
        <li class="active">
            <a data-target="#tab_15_1" data-toggle="tab">需求计划</a>
        </li>
        <li>
            <a data-target="#tab_15_2" data-toggle="tab">交付日历</a>
        </li>
    </ul>
    <div class="tab-content">
    	<!-- 需求计划列表---START -->
        <div class="tab-pane active" id="tab_15_1">
        	<div class="row">
			    <div class="col-md-12">
			        <!-- BEGIN EXAMPLE TABLE PORTLET-->
			                <div class="portlet box blue">
			                                <div class="portlet-title">
												<div class="caption">
													<i class="fa fa-globe"></i>需求计划
												</div>
												<div class="actions">
													<a href="javascript:;" class="btn btn-default btn-sm btn-circle"
														ng-click="addDemandPlan()"> <i class="fa fa-plus"></i> 添加
													</a>  <a href="javascript:;" class="btn btn-default btn-sm btn-circle"
														ng-click="toEditDemandPlan()"> <i class="fa fa-edit"></i> 修改
													</a> <a href="javascript:;" class="btn btn-default btn-sm btn-circle"
														ng-click="deleteDemandPlan()"> <i class="fa fa-minus"></i>
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
																class="tool-action" ng-click="exportDemandPlan()"> <i
																	class="fa fa-file-excel-o"></i> 导出
															</a></li>
															<!-- <li><a href="javascript:;" data-action="2"
																class="tool-action"> <i class="fa fa-print"></i> 打印
															</a></li> -->
														</ul>
													</div>
												</div>
											</div>


											<div class="portlet-body">
												<div class="row">
													<div class="col-md-6 col-sm-6">
													</div>
													<div class="col-md-6 col-sm-6" style="text-align: right;">
														<div id="companyTable_filter" class="dataTables_filter">
															<label><input type="search" ng-model="params.searchKey"
																class="form-control input-sm input-small input-inline"
																placeholder="" aria-controls="companyTable"></label>
																<button  class="btn blue  btn-sm " ng-click="searchDemandPlan()">
                                            					<i class="fa fa-search"></i> 查询 </button>
														</div>
													</div>
												</div>
											</div>
					
					
											<div class="portlet-body" ng-repeat="demandPlan in demandPlans" id="demandPlanTable">
			                                 		<div class="row">
				                                   	 	<div class="col-md-12">
															<div class="portlet">
																<div class="portlet-title">
								                                    <div class="caption" style="width: 100%;font-size: 15px;">
								                                    	<div style="float:left;width:70%;">
								                                    		<input name="select_all" class="dt-body-center" value="{{demandPlan.serialNum}}" id="example-select-all" type="checkbox" />
								                                    		{{demandPlan.buyComName}}&nbsp;&nbsp;&nbsp;&nbsp;近五条记录&nbsp;&nbsp;&nbsp;&nbsp;更新日期：{{demandPlan.updateTime.substring(0,10)}}
								                                    	</div>
								                                    		
								                                    	<div style="float:left;width:30%;text-align:right;"><a href="javascript:;" ui-sref="demandPlanView({serialNum:demandPlan.serialNum})" >全部计划>></a></div>
								                                    </div>
								                                </div>
								                                <div class="portlet-body">
													                <div class="table-scrollable">
																		<table class="table table-striped table-bordered table-advance table-hover">
								                                            <thead>
								                                                <tr>
								                                                   	<!-- <th><input name="select_all" class="dt-body-center"
																							value="1" id="example-select-all" type="checkbox" /></th> -->
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
								                                                   <!--  <td><input name="select_all" class="dt-body-center"
																							value="1" type="checkbox" /></td> -->
								                                                    <td>{{materiel.materiel.materielName}}</td>
								                                                    <td>{{materiel.materiel.materielName}}</td>
								                                                    <td>{{materiel.materiel.specifications}}</td>
								                                                    <td>{{materiel.supplyName}}</td>
								                                                    <td>{{materiel.deliveryDate}}</td>
								                                                    <td>{{materiel.amount}}</td>
								                                                    <td>{{materiel.deliveryAddress}}</td>
								                                                    <td>{{materiel.remainTime}}</td>
								                                                </tr>
								                                            </tbody>
								                                        </table>
								                                    </div>
								                                </div>
									                    </div>
							                    	</div>
			                            		</div>   
											</div>
											<div class="portlet-body dataTables_wrapper" >
												<div class="row">
				                                    <div class="col-md-12">
														<div class="col-md-5 col-sm-5">
															<div class="dataTables_info" id="simple_info" role="status"></div>
														</div>
														<div class="col-md-7 col-sm-7">
															<div class="dataTables_paginate paging_bootstrap_full_number">
																<ul id="simple_paginator" ></ul>
															</div>
														</div>
													</div>
												</div>
											</div>
			                            </div>
			        <!-- END EXAMPLE TABLE PORTLET-->
				</div>
			</div>
        </div>
        <!-- 需求计划列表---END -->
        
        <!-- 交付日期---START -->
        <div class="tab-pane" id="tab_15_2">
            <div class="portlet bordered">
	            <div class="portlet-body">
					<div class="portlet light ">
	                        <div class="portlet-title">
	                            <div class="caption">
	                            	<div class="input-group input-large date-picker input-daterange" data-date-format="yyyy-mm-dd">
                                          <input type="text" class="form-control" ng-model="startTime" name="from">
                                          <span class="input-group-addon"> to </span>
                                          <input type="text" class="form-control" ng-model="endTime" name="to"> 
                                    </div>
	                            </div>
	                             <div class="actions">
                                    	    <button  class="btn blue  btn-outline  btn-sm " ng-click="searchDemandPlanCalendar()">
                                            	<i class="fa fa-search"></i> 查询 </button>
                                			<button  class="btn red  btn-outline  btn-sm " ng-click="resetSearchForm()">
                                            	<i class="fa fa-undo"></i> 重置 </button>
                                    	
                            	 </div>
	                        </div>
	                        <div class="portlet-body  table-both-scroll">
	                        	<table class="table table-striped table-bordered table-hover table-checkable order-column" id="demand_plan_date">
				                    <thead>
				                        <tr>
				                            <th style="text-align: center"><input name="select_all" value="1" id="example-select-all" type="checkbox"/></th>
				                            <th> 计划编号 </th>
				                            <th> 采购客户 </th>
				                            <th> 物料编号 </th>
				                            <th> 产品名称 </th>
				                            <th> 规格型号 </th>
				                            <th> 单位 </th>
				                            <th> 数量 </th>
				                            <th> 供应商 </th>
				                            <th> 交付地点 </th>
				                            <th> 最晚交付日期 </th>
				                            <th> 距离交付 </th>
				                        </tr>
				                    </thead>
				                    <tbody>
				                    </tbody>
				                </table>
	         				</div>
	         		</div>
	         	</div>
	        </div>
        </div>
        <!-- 交付日历---END -->
    </div>
</div>

<!-- 导入modal START -->
<div class="modal fade" id="import" role="import" aria-hidden="true">
     <div class="modal-dialog" >
	    <div class="modal-content">
	 		<div class="modal-header">
	            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
	            <h4 class="modal-title" >需求计划导入</h4>
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
	                                               			<button type="button" class="btn blue" ng-click="downloadImportTemp()">下载模板</button>
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
	                                               			<button type="button" class="btn blue" ng-click="uploadExcel()">导入</button>
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
