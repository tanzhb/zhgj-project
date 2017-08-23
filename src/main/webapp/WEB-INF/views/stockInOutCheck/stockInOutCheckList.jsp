<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
	<meta http-equiv="Content-Type" content="multipart/form-data; charset=utf-8" />
</head>
<!-- BEGIN PAGE HEADER-->
<h3 class="page-title"> 检验
</h3>
<div class="page-bar">
    <ul class="page-breadcrumb">
        <li>
            <i class="fa fa-home"></i>
            <a ui-sref="dashboard">首页</a>
            <i class="fa fa-angle-right"></i>
        </li>
        <li>
            <a>物流管理</a>
            <i class="fa fa-angle-right"></i>
        </li>
        <li>
            <a ui-sref="datatablesmanaged">检验</a>
        </li>
    </ul>
</div>
<!-- END PAGE HEADER-->
<!-- BEGIN MAIN CONTENT -->
<div class="tabbable-line">
    <ul class="nav nav-tabs">
        <li class="active">
            <a data-target="#tab_in" data-toggle="tab">入库检验</a>
        </li>
        <li>
            <a data-target="#tab_out" data-toggle="tab">出库检验</a>
        </li>
    </ul>
    <div class="tab-content">
    	<!-- 入库检验列表---START -->
        <div class="tab-pane active" id="tab_in">
        <!-- BEGIN EXAMPLE TABLE PORTLET-->
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>检验列表
				</div>
				<div class="actions">
					<button ng-click="addStockInOutCheck('in')"
						data-toggle="modal" class="btn btn-default btn-sm btn-circle">
						<i class="fa fa-plus"></i> 添加
					</button><button ng-click="toEditStockInOutPage('in')"
						class="btn btn-default btn-sm btn-circle"> <i
						class="fa fa-edit"></i> 修改
					</button> <button ng-click="delStockInOutCheck()" 
						data-toggle="modal" 
						class="btn btn-default btn-sm btn-circle"> <i
						class="fa fa-minus"></i> 删除
					</button>
					<div class="btn-group">
						<a class="btn btn-default btn-outline btn-circle"
							href="javascript:;" data-toggle="dropdown"> <i
							class="fa fa-share"></i> <span class="hidden-xs"> 其它 </span> <i
							class="fa fa-angle-down"></i>
						</a>
						
						<ul class="dropdown-menu pull-right" id="sample_3_tools">
												<li><a data-action="0"
													class="tool-action" data-toggle="modal" data-target="#import"> <i class="fa fa-upload"></i> 导入
												</a></li> 
												<li><a href="javascript:;" data-action="1"
													class="tool-action" ng-click="exportStock()"> <i class="fa fa-file-excel-o"></i> 导出
												</a></li>
												<li><a href="javascript:;" data-action="2"
													class="tool-action" > <i class="fa fa-print"></i> 打印
												</a></li> 
											</ul>
					</div>
				</div>
			</div>

			<!-- 删除库存modal 开始 -->
			<div id="delStockInOutCheckModal" class="modal fade" tabindex="-2"

				data-backdrop="static" data-keyboard="false">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true"></button>
							<h4 class="modal-title">确认</h4>
						</div>
						<div class="modal-body">
							<p>是否删除已选库存?</p>
						</div>
						<div class="modal-footer">
							<button type="button" data-dismiss="modal"
								class="btn dark btn-outline">取消</button>
							<button type="button" ng-click="confirmDellStock()" class="btn green">确定
								</button>
						</div>
					</div>
				</div>
			</div>
			<!-- 删除库存modal 结束 -->
			
			<!-- 导入modal START -->
<div class="modal fade" id="import" role="import" aria-hidden="true">
     <div class="modal-dialog" >
	    <div class="modal-content">
	 		<div class="modal-header">
	            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
	            <h4 class="modal-title" >库存信息导入</h4>
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


			<div class="portlet-body">
				<table
					class="table table-striped table-bordered table-hover "
					id="sample_in">
					<thead>
						<tr>
							<th style="text-align: center"><input name="select_all"
								value="1" id="example-select-all" type="checkbox" /></th>
							<th>检验编号 </th>
                            <th> 收货单号</th>
                            <th>采购订单号</th>
                            <th>物料</th>
                            <th>合格数量 </th>
                            <th> 不合格数量</th>
                            <th>检验日期</th>
                            <th> 检验员 </th>
                            <th> 状态 </th>
						</tr>
					</thead>
					
					<tbody>
					</tbody>
				</table>
			</div>
		</div>
		<!-- END EXAMPLE TABLE PORTLET-->
        </div>
        <!-- 入库检验列表---END -->
        
        <!-- 出库检验列表---START -->
        <div class="tab-pane" id="tab_out">
         <!-- BEGIN EXAMPLE TABLE PORTLET-->
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>检验列表
				</div>
				<div class="actions">
					<button ng-click="addStockInOutCheck('out')"
						data-toggle="modal" class="btn btn-default btn-sm btn-circle">
						<i class="fa fa-plus"></i> 添加
					</button><button ng-click="toEditStockInOutPage('out')"
						class="btn btn-default btn-sm btn-circle"> <i
						class="fa fa-edit"></i> 修改
					</button> <button ng-click="delStockInOutCheck()" 
						data-toggle="modal" 
						class="btn btn-default btn-sm btn-circle"> <i
						class="fa fa-minus"></i> 删除
					</button>
					<div class="btn-group">
						<a class="btn btn-default btn-outline btn-circle"
							href="javascript:;" data-toggle="dropdown"> <i
							class="fa fa-share"></i> <span class="hidden-xs"> 其它 </span> <i
							class="fa fa-angle-down"></i>
						</a>
						
						<ul class="dropdown-menu pull-right" id="sample_3_tools">
												<li><a data-action="0"
													class="tool-action" data-toggle="modal" data-target="#import"> <i class="fa fa-upload"></i> 导入
												</a></li> 
												<li><a href="javascript:;" data-action="1"
													class="tool-action" ng-click="exportStock()"> <i class="fa fa-file-excel-o"></i> 导出
												</a></li>
												<li><a href="javascript:;" data-action="2"
													class="tool-action" > <i class="fa fa-print"></i> 打印
												</a></li> 
											</ul>
					</div>
				</div>
			</div>

			<!-- 删除库存modal 开始 -->
			<div id="delStockModal" class="modal fade" tabindex=

				data-backdrop="static" data-keyboard="false">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true"></button>
							<h4 class="modal-title">确认</h4>
						</div>
						<div class="modal-body">
							<p>是否删除已选库存?</p>
						</div>
						<div class="modal-footer">
							<button type="button" data-dismiss="modal"
								class="btn dark btn-outline">取消</button>
							<button type="button" ng-click="confirmDellStock()" class="btn green">确定
								</button>
						</div>
					</div>
				</div>
			</div>
			<!-- 删除库存modal 结束 -->
			
			<!-- 导入modal START -->
<div class="modal fade" id="import" role="import" aria-hidden="true">
     <div class="modal-dialog" >
	    <div class="modal-content">
	 		<div class="modal-header">
	            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
	            <h4 class="modal-title" >库存信息导入</h4>
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


			<div class="portlet-body">
				<table
					class="table table-striped table-bordered table-hover "
					id="sample_out">
					<thead>
						<tr>
							<th style="text-align: center"><input name="select_all"
								value="1" id="example-select-all" type="checkbox" /></th>
							<th>检验编号 </th>
                            <th> 发货单号</th>
                            <th>采购订单号</th>
                            <th>物料</th>
                            <th>合格数量 </th>
                            <th> 不合格数量</th>
                            <th>检验日期</th>
                            <th> 检验员 </th>
                            <th> 状态 </th>
						</tr>
					</thead>
					
					<tbody>
					</tbody>
				</table>
			</div>
		</div>
		<!-- END EXAMPLE TABLE PORTLET-->
        </div>
        <!-- 出库检验列表---END -->
    </div>
</div>

<!-- 导入modal START -->
<div class="modal fade" id="import" role="import" aria-hidden="true">
     <div class="modal-dialog" >
	    <div class="modal-content">
	 		<div class="modal-header">
	            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
	            <h4 class="modal-title" >入库检验导入</h4>
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
