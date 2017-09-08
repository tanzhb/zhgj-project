<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!-- <meta http-equiv="cache-control" content="no-cache">   -->
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
 <!-- BEGIN PAGE HEADER-->
<h3 class="page-title"> 仓库列表
    <small> 仓库管理</small>
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
            <a ui-sref="datatablesmanaged"> 仓库列表</a>
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
						<i class="fa fa-globe font-green"></i>
					<span class="caption-subject font-green bold uppercase">仓库列表</span>
				</div>
				<div class="actions">
				<div class="btn-group btn-group-devided" data-toggle="buttons">
						<shiro:hasPermission name="warehouse:add">
							<label class="btn btn-transparent green btn-circle btn-sm"
								ng-click="addWarehouse()"> <i class="fa fa-plus"></i> 添加
							</label>
						</shiro:hasPermission>
						<shiro:hasPermission name="warehouse:edit">
							<label class="btn btn-transparent purple btn-circle btn-sm"
								ng-click="toEditWarehousePage()"> <i class="fa fa-edit"></i>
								修改
							</label>
						</shiro:hasPermission>
						<shiro:hasPermission name="warehouse:delete">
							<label class="btn btn-transparent red btn-circle btn-sm"
								ng-click="delWarehouse()"> <i class="fa fa-minus"></i>
								删除
							</label>
						</shiro:hasPermission>
						<shiro:hasPermission name="warehouse:import">
							<label
								class="btn btn-transparent green btn-outline btn-circle btn-sm"
								data-toggle="modal" data-target="#import"> <i
								class="fa fa-upload"></i> 导入
							</label>
						</shiro:hasPermission>
						<shiro:hasPermission name="warehouse:export">
							<label
								class="btn btn-transparent yellow-casablanca btn-outline btn-circle btn-sm"
								ng-click="exportWarehouse()"> <i
								class="fa fa-file-excel-o"></i> 导出
							</label>
						</shiro:hasPermission>
					</div>
				<!-- <div ng-controller="MyCtrl">
    <button   class="btn sbold green" data-toggle="modal"  ng-click="jumpToUrl('/addWarehouse')">新建仓库<i class="fa fa-plus"></i></button>
    
</div>
 <a class="btn sbold green" data-toggle="modal"   ng-click="addWarehouse()">新建仓库 <i class="fa fa-plus"></i></a>
					<button ui-sref="addWarehouse" 
						data-toggle="modal" class="btn btn-default btn-sm btn-circle">
						<i class="fa fa-plus"></i> 添加
					</button><button ng-click="toEditWarehousePage()"
						class="btn btn-default btn-sm btn-circle"> <i
						class="fa fa-edit"></i> 修改
					</button> <button ng-click="delWarehouse()" 
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
													class="tool-action" ng-click="exportWarehouse()"> <i class="fa fa-file-excel-o"></i> 导出
												</a></li>
												<li><a href="javascript:;" data-action="2"
													class="tool-action" > <i class="fa fa-print"></i> 打印
												</a></li> 
											</ul>
					</div> -->
				</div>
			</div>


			<!-- 添加用户modal 开始 -->
			<div id="addWarehouseModal" class="modal fade" role="dialog"
				aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true"></button>
							<h4 id="pageTitle" class="modal-title">仓库详情</h4>
						</div>
						<div class="modal-body form">
							<form action="#" class="form-horizontal form-row-seperated"  id="form_sample_1">
								<div class="form-group">
									<label class="col-sm-2 control-label">仓库编号</label>
									<div class="col-sm-4">
										<div class="input-group">
											<!-- <span class="input-group-addon"> <i class="fa fa-user"></i>
											</span> --> <input type="text" id="warehouseNum" name ="warehouseNum" 
												ng-model="warehouse.warehouseNum" 
												class="form-control" />
										</div>
										
									</div>
									<label class="col-sm-2 control-label">仓库名称</label>
									<div class="col-sm-4">
										<div class="input-group">
											<!-- <span class="input-group-addon"> <i class="fa fa-user"></i>
											</span> --> <input type="text" id="warehouseName" name ="warehouseName" 
												ng-model="warehouse.warehouseName" 
												class="form-control" />
										</div>
										
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">仓库类型</label>
									<div class="col-sm-4">
										<div class="input-group">
									<select class="form-control" data-placeholder="Choose a Category" tabindex="1"  id="warehouseType" name ="warehouseType"  ng-model="warehouse.warehouseType" >
                                                                         <option value="">	选择仓库</option>
                                                                        <option value="1">	自建仓库</option>
                                                                        <option value="2">代管仓库</option>
                                                                        <option value="3">境外仓库</option>
                                                                    </select>
										</div>
										
									</div>
									<label class="col-sm-2 control-label">仓库分类</label>
									<div class="col-sm-4">
										<div class="input-group">
										<select class="form-control" data-placeholder="Choose a Category" tabindex="1"  id="warehouseCategory" name ="warehouseCategory" ng-model="warehouse.warehouseCategory" >
                                                                          <option value="">选择分类</option>
                                                                        <option value="A">机加工</option>
                                                                        <option value="B">备品备件</option>
                                                                        <option value="C">标准品</option>
                                                                    </select>
											
										</div>
										
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">仓库地址</label>
									<div class="col-sm-4">
										<div class="input-group">
											<!-- <span class="input-group-addon"> <i class="fa fa-user"></i>
											</span> --> <input type="text" id="address" name ="address" 
												ng-model="warehouse.address" 
												class="form-control" />
										</div>
										
									</div>
									<label class="col-sm-2 control-label">仓库所有者</label>
									<div class="col-sm-4">
										<div class="input-group">
											<!-- <span class="input-group-addon"> <i class="fa fa-user"></i>
											</span> --> <input type="text" id="owner" name ="owner" 
												ng-model="warehouse.owner" 
												class="form-control" />
										</div>
										
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">仓库管理员</label>
									<div class="col-sm-4">
										<div class="input-group">
											<!-- <span class="input-group-addon"> <i class="fa fa-user"></i>
											</span> --> <input type="text" id="admin" name ="admin" 
												ng-model="warehouse.admin" 
												class="form-control" />
										</div>
										
									</div>
									<label class="col-sm-2 control-label">仓库面积</label>
									<div class="col-sm-4">
										<div class="input-group">
											<!-- <span class="input-group-addon"> <i class="fa fa-user"></i>
											</span> --> <input type="text" id="area" name ="area" 
												ng-model="warehouse.area" 
												class="form-control" />
										</div>
										
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">邮件</label>
									<div class="col-sm-4">
										<div class="input-group">
											<!-- <span class="input-group-addon"> <i class="fa fa-user"></i>
											</span> --> <input type="text" id="email" name ="email" 
												ng-model="warehouse.email" 
												class="form-control" />
										</div>
										
									</div>
									<label class="col-sm-2 control-label">电话</label>
									<div class="col-sm-4">
										<div class="input-group">
											<!-- <span class="input-group-addon"> <i class="fa fa-user"></i>
											</span> --> <input type="text" id="tel" name ="tel" 
												ng-model="warehouse.tel" 
												class="form-control" />
										</div>
										
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">备注</label>
									<div class="col-sm-4">
										<div class="input-group">
											<!-- <span class="input-group-addon"> <i class="fa fa-user"></i>
											</span> --> <input type="text" id="remark" name ="remark" 
												ng-model="warehouse.remark" 
												class="form-control" />
										</div>
										
									</div>
									<label class="col-sm-2 control-label">传真</label>
									<div class="col-sm-4">
										<div class="input-group">
											<!-- <span class="input-group-addon"> <i class="fa fa-user"></i>
											</span> --> <input type="text" id="fax" name ="fax" 
												ng-model="warehouse.fax" 
												class="form-control" />
										</div>
										
									</div>
								</div>
							</form>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">取消</button>
							<button type="button" ng-click="saveWarehouse()"
								class="btn btn-primary">
								<i class="fa fa-check"></i> 保存
							</button>
						</div>
					</div>
				</div>
			</div>
			<!-- 添加用户modal 结束 -->

			<!-- 删除用户modal 开始 -->
			<div id="delWarehouseModal" class="modal fade" tabindex=

				data-backdrop="static" data-keyboard="false">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true"></button>
							<h4 class="modal-title">确认</h4>
						</div>
						<div class="modal-body">
							<p>是否删除已选仓库?</p>
						</div>
						<div class="modal-footer">
							<button type="button" data-dismiss="modal"
								class="btn dark btn-outline">取消</button>
							<button type="button" ng-click="confirmDellWarehouse()" class="btn green">确定
								</button>
						</div>
					</div>
				</div>
			</div>
			<!-- 删除用户modal 结束 -->
			
			<!-- 导入modal START -->
<div class="modal fade" id="import" role="import" aria-hidden="true">
     <div class="modal-dialog" >
	    <div class="modal-content">
	 		<div class="modal-header">
	            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
	            <h4 class="modal-title" >仓库信息导入</h4>
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
					id="sample_warehouse">
					<thead>
						<tr>
							<th style="text-align: center"><input name="select_all"
								value="1" id="example-select-all" type="checkbox" /></th>
							<th>仓库编号 </th>
                            <th> 仓库名称</th>
                            <th> 仓库类型</th>
                            <th>仓库地址</th>
                            <th>管理员 </th>
                            <th> 仓库面积 </th>
                            <th>仓库分类 </th>
                            <th> 所有者 </th>
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
<div class="row" ng-controller="WarehouseController">
	<div class="col-md-12">

		<!-- BEGIN EXAMPLE TABLE PORTLET-->
		<div class="portlet light ">
			<div class="portlet-title">
				<div class="caption">
				<i class="fa fa-globe font-green"></i>
					<span class="caption-subject font-green bold uppercase">库位管理</span>
				</div>
				<div class="actions">
					
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
													class="tool-action" ng-click=""> <i class="fa fa-file-excel-o"></i> 导出
												</a></li>
												<li><a href="javascript:;" data-action="2"
													class="tool-action" > <i class="fa fa-print"></i> 打印
												</a></li> 
											</ul>
					</div> -->
				</div>
			</div>
			<!-- 仓库信息弹框查看开始 -->
<div class="modal fade" id="viewWarehouse" role="basic" aria-hidden="true">
     <div class="modal-dialog" style="width: 750px;">
	    <div class="modal-content">
	 		<div class="modal-header">
	            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
	            <h4 class="modal-title" >仓库信息</h4>
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
                                                   <label class="control-label col-md-5">仓库编号：</label>
                                                   <div class="col-md-7">
                                                       <p class="form-control-static" >{{warehouse.warehouseNum}}</p>
                                                   </div>
                                               </div>
                                           </div>
                                           <!-- /span -->
                                           <div class="col-md-6">
                                               <div class="form-group">
                                                   <label class="control-label col-md-5">仓库名称：</label>
                                                   <div class="col-md-7">
                                                       <p class="form-control-static">{{warehouse.warehouseName}}</p>
                                                   </div>
                                               </div>
                                           </div>
                                         <!--   /span -->
                                       </div>
                                       <!-- /row -->
                                       <div class="row">
                                           <div class="col-md-6">
                                               <div class="form-group">
                                                   <label class="control-label col-md-5">仓库类型：</label>
                                                   <div class="col-md-7">
                                                       <p class="form-control-static">{{warehouse.warehouseType}}</p>
                                                   </div>
                                               </div>
                                           </div>
                                          <!--  /span -->
                                           <div class="col-md-6">
                                               <div class="form-group">
                                                   <label class="control-label col-md-5">仓库分类：</label>
                                                   <div class="col-md-7">
                                                       <p class="form-control-static">{{warehouse.warehouseCategory}}</p>
                                                   </div>
                                               </div>
                                           </div>
                                          <!--  /span -->
                                       </div>
                                      <!--  /row -->
                                       <div class="row">
                                           <div class="col-md-6">
                                               <div class="form-group">
                                                   <label class="control-label col-md-5">仓库地址：</label>
                                                   <div class="col-md-7">
                                                       <p class="form-control-static">{{warehouse.address}}</p>
                                                   </div>
                                               </div>
                                           </div>
                                        <!--    /span -->
                                           <div class="col-md-6">
                                               <div class="form-group">
                                                   <label class="control-label col-md-5">仓库所有者：</label>
                                                   <div class="col-md-7">
                                                       <p class="form-control-static">{{warehouse.owner}}</p>
                                                   </div>
                                               </div>
                                           </div>
                                         <!--   /span -->
                                       </div>
                                     <!--   /row -->
                                       <div class="row">
                                           <div class="col-md-6">
                                               <div class="form-group">
                                                   <label class="control-label col-md-5">仓库管理员：</label>
                                                   <div class="col-md-7">
                                                       <p class="form-control-static">{{warehouse.admin}}</p>
                                                   </div>
                                               </div>
                                           </div>
                                      <!--      /span -->
                                           <div class="col-md-6">
                                               <div class="form-group">
                                                   <label class="control-label col-md-5">仓库面积：</label>
                                                   <div class="col-md-7">
                                                       <p class="form-control-static">{{warehouse.area}}</p>
                                                   </div>
                                               </div>
                                           </div>
                                           <!-- /span -->
                                       </div>
                                      <!--  /row -->
                                       <div class="row">
                                           <div class="col-md-6">
                                               <div class="form-group">
                                                   <label class="control-label col-md-5">邮件：</label>
                                                   <div class="col-md-7">
                                                       <p class="form-control-static">{{warehouse.email}}</p>
                                                   </div>
                                               </div>
                                           </div>
                                           <!-- /span -->
                                           <div class="col-md-6">
                                               <div class="form-group">
                                                   <label class="control-label col-md-5">电话：</label>
                                                   <div class="col-md-7">
                                                       <p class="form-control-static">{{warehouse.tel}}</p>
                                                   </div>
                                               </div>
                                           </div>
                                           <!-- /span -->
                                       </div>
                                       <!-- /row -->
                                       <div class="row">
                                           <div class="col-md-6">
                                               <div class="form-group">
                                                   <label class="control-label col-md-5">备注：</label>
                                                   <div class="col-md-7">
                                                       <p class="form-control-static">{{warehouse.remark}}</p>
                                                   </div>
                                               </div>
                                           </div>
                                         <!--   /span -->
                                           <div class="col-md-6">
                                               <div class="form-group">
                                                   <label class="control-label col-md-5">传真：</label>
                                                   <div class="col-md-7">
                                                       <p class="form-control-static">{{warehouse.fax}}</p>
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
<!-- 仓库信息查看modal END-->


			<div class="portlet-body">
				<table
					class="table table-striped table-bordered table-hover table-header-fixed"
					id="sample_2">
					<thead>
						<tr>
							<!-- <th style="text-align: center"><input name="select_all"
								value="1" id="example-select-all" type="checkbox" /></th> -->
					<th> 库位编码</th>
                     <th> 库位名称</th>
                      <th> 存储属性</th>
                      <th> 最大行数</th>
                      <th> 最大列数</th>
                       <th> 最大层数</th>
                        <th>存储类型</th>
                        <th> 存储方式</th>
                         <th> 默认长宽高</th>
                         <th> 默认容积</th>
                          <th> 默认承重</th>
						</tr>
					</thead>
						<tbody>
						<tr ng-repeat="warehouseposition in warehousepositions  track by $index" >
													<!-- <td></td> -->
												<td>{{warehouseposition.positionNum}}</td>
												<td>{{warehouseposition.positionName}}</td>
												<td>{{warehouseposition.storageAttribute}}</td>
												<td>{{warehouseposition.maxRows}}</td>
												<td>{{warehouseposition.maxCols}}</td>
												<td>{{warehouseposition.maxLayers}}</td>
												<td><span ng-if="warehouseposition.storageType==null">暂无</span><span ng-if="warehouseposition.storageType!=null">{{warehouseposition.storageType}}</span></td>
												<td><span ng-if="warehouseposition.storageMode==null">暂无</span><span ng-if="warehouseposition.storageMode!=null">{{warehouseposition.storageMode}}</span></td>
												<td>{{warehouseposition.defaultLWH}}</td>
												<td>{{warehouseposition.defaultVolume}}</td>
												<td>{{warehouseposition.defaultBearing}}</td>

											</tr>
											<tr ng-if="warehousepositions==undefined||warehousepositions.length==0">
													<td colspan="11"  align="center">暂无数据</td>
											</tr>
					</tbody>
				</table>
			</div>
		</div>
		<!-- END EXAMPLE TABLE PORTLET-->
	</div>
</div>

       <script>
    /* TableDatatablesManaged.init(); */
    function MyCtrl($scope, $location) {

        $scope.jumpToUrl = function(path) {

        	$location.path(path);

        };

    }
</script>    
                            