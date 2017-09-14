<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" %>
<!-- <meta http-equiv="cache-control" content="no-cache">   -->

 <!-- BEGIN PAGE HEADER-->
<h3 class="page-title"> 库存列表
    <small> 库存管理</small>
</h3>
<div class="page-bar">
    <ul class="page-breadcrumb">
        <li>
            <i class="fa fa-home"></i>
            <a ui-sref="dashboard">首页</a>
            <i class="fa fa-angle-right"></i>
        </li>
        <li>
            <a ui-sref="datatablesmanaged">物流管理</a>
            <i class="fa fa-angle-right"></i>
        </li>
        <li>
            <a ui-sref="datatablesmanaged"> 库存列表</a>
        </li>
    </ul>
</div>

<div class="row" >
<!-- <div class="col-md-4">
         <div class="portlet light ">
             <div class="portlet-title">
                 <div class="caption">
                     <i class="icon-social-dribbble font-blue-sharp"></i>
                     <span ng-click="reloadWarehouseTable()" class="caption-subject font-blue-sharp bold uppercase">仓库展示</span>
                 </div>
             </div>
             <div class="portlet-body">
                 <div id="warehouseTree" class="tree-demo">
                 </div>
             </div>
         </div>
	</div> -->
	<div class="col-md-12">

		<!-- BEGIN EXAMPLE TABLE PORTLET-->
		<div class="portlet light">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>库存列表
				</div>
				<div class="actions">
					<button ng-click="addStock()"
						data-toggle="modal" class="btn btn-transparent green btn-circle btn-sm">
						<i class="fa fa-plus"></i> 添加
					</button><button ng-click="toEditStockPage()"
						class="btn btn-transparent purple btn-circle btn-sm"> <i
						class="fa fa-edit"></i> 修改
					</button> <button ng-click="delStock()" 
						data-toggle="modal" 
						class="btn btn-transparent red btn-circle btn-sm"> <i
						class="fa fa-minus"></i> 删除
					</button>
						<label class="btn btn-transparent green btn-outline btn-circle btn-sm" data-toggle="modal" data-target="#import" >
	                                              <i class="fa fa-upload"></i> 导入</label>
						<label class="btn btn-transparent yellow-casablanca btn-outline btn-circle btn-sm" ng-click="exportStock()">
	                                              <i class="fa fa-file-excel-o"></i> 导出</label>
					<!-- <div class="btn-group">
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
					</div> -->
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
					id="sample_stock">
					<thead>
						<tr>
							<th style="text-align: center"><input name="select_all"
								value="1" id="example-select-all" type="checkbox" /></th>
							<th>库存编号 </th>
                            <th> 物料编号</th>
                            <th>物料名称</th>
                            <th>规格型号</th>
                            <th>所在仓库 </th>
                            <th> 库存 </th>
                            <th>平均库龄 </th>
                            <th> 预售 </th>
                            <th> 在途 </th>
                            <th> 可售 </th>
                            <!-- <th> 风险等级 </th> -->
                            <th> 状态</th>
						</tr>
					</thead>
					
					<tbody>
					</tbody>
				</table>
			</div>
		</div>
		<!-- END EXAMPLE TABLE PORTLET-->
	</div>
</div>


       <script>
</script>    
                            