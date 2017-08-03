<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" %>
<!-- BEGIN PAGE HEADER-->
<h3 class="page-title"> 物料信息
    <small></small>
</h3>
<div class="page-bar">
    <ul class="page-breadcrumb">
        <li>
            <i class="fa fa-home"></i>
            <a ui-sref="dashboard">首页</a>
            <i class="fa fa-angle-right"></i>
        </li>
        <li>
            <a ui-sref="materiel">物料信息</a>
        </li>
    </ul>
</div>
<div class="row">
     <div class="col-md-4">
         <div class="portlet light ">
             <div class="portlet-title">
                 <div class="caption">
                     <i class="icon-social-dribbble font-blue-sharp"></i>
                     <span ng-click="reloadTable()" class="caption-subject font-blue-sharp bold uppercase">全部物料</span>
                 </div>
             </div>
             <div class="portlet-body">
                 <div id="tree_1" class="tree-demo">
                 </div>
             </div>
         </div>
	</div>
	<div class="col-md-8">
        <div class="row">
	        <div class="portlet box red">
	            <div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>物料列表
				</div>
				<div class="actions">
					<a href="javascript:;" ui-sref="addMateriel"
						data-toggle="modal" class="btn btn-default btn-sm btn-circle">
						<i class="fa fa-plus"></i> 物料
					</a> <a href="javascript:;" data-target="#delMaterielModal"
						data-toggle="modal" 
						class="btn btn-default btn-sm btn-circle"> <i
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
	                <table class="table table-striped table-bordered table-hover table-checkable order-column" id="sample_2">
	                    <thead>
	                        <tr>
	                            <th style="text-align: center"><input name="select_all"
								value="1" id="example-select-all" type="checkbox" /></th>
	                            <th> 物料编码 </th>
	                            <th> 物料名称 </th>
	                            <th> 规格型号 </th>
	                            <th> 单位 </th>
	                            <th> 类别 </th>
	                            <th> 产地 </th>
	                            <th> 品牌 </th>
	                            <th> 供应商 </th>
	                            <th> 版本 </th>
	                            <th> 状态 </th>
	                        </tr>
	                    </thead>
	                    <tfoot>
	                        <tr>
	                            <th></th>
	                            <th> 物料编码 </th>
	                            <th> 物料名称 </th>
	                            <th> 规格型号 </th>
	                            <th> 单位 </th>
	                            <th> 类别 </th>
	                            <th> 产地 </th>
	                            <th> 品牌 </th>
	                            <th> 供应商 </th>
	                            <th> 版本 </th>
	                            <th> 状态 </th>
	                        </tr>
	                    </tfoot>
	                    <tbody>
	                    </tbody>
	                </table>
	            </div>
	        </div>
        </div>
        <div class="row">
        	<div class="portlet light ">
                <div class="portlet-body">
                    <ul class="nav nav-pills">
                        <li class="active">
                            <a href="#" data-target="#tab_2_1" data-toggle="tab"> 供应商 </a>
                        </li>
                        <li>
                            <a href="#" data-target="#tab_2_2" data-toggle="tab"> 使用采购商 </a>
                        </li>
                        <li>
                            <a href="#" data-target="#tab_2_3"  data-toggle="tab"> BOM </a>
                        </li>
                        <li>
                            <a href="#" data-target="#tab_2_4"  data-toggle="tab"> 包装信息 </a>
                        </li>
                    </ul>
                    <div class="tab-content">
                        <div class="tab-pane fade active in" id="tab_2_1">
							<div class="portlet light bordered">
					            <div class="portlet-body">
					                <table class="table table-striped table-bordered table-hover table-checkable order-column" id="sample_1">
					                    <thead>
					                        <tr>
					                            <th>
					                                <label class="mt-checkbox mt-checkbox-outline mt-checkbox-single">
					                                    <input type="checkbox" class="group-checkable" data-set="#sample_1 .checkboxes" />
					                                    <span></span>
					                                </label>
					                            </th>
					                            <th> 物料编码 </th>
					                            <th> 物料名称 </th>
					                            <th> 规格型号 </th>
					                            <th> 单位 </th>
					                            <th> 类别 </th>
					                            <th> 产地 </th>
					                            <th> 品牌 </th>
					                            <th> 供应商 </th>
					                            <th> 版本 </th>
					                            <th> 状态 </th>
					                        </tr>
					                    </thead>
					                    <tbody>
					                        <tr  class="odd gradeX">
					                            <td>
					                                <label class="mt-checkbox mt-checkbox-outline mt-checkbox-single">
					                                    <input type="checkbox" class="checkboxes" value="{{list.serialNum}}" />
					                                    <span></span>
					                                </label>
					                            </td>
					                            <td> 1 </td>
					                            <td> 1 </td>
					                           <td> 1 </td>
					                            <td> 1 </td>
					                            <td> 1 </td>
					                            <td> 1 </td>
					                            <td> 1 </td>
					                            <td>  </td>
					                            <td> 1 </td>
					                            <td> 1 </td>
					                        </tr>
					                        
					                    </tbody>
					                </table>
					            </div>
					        </div>                        
					    </div>
                        <div class="tab-pane fade" id="tab_2_2">
                            <div class="portlet light bordered">
					            <div class="portlet-body">
					                <table class="table table-striped table-bordered table-hover table-checkable order-column" id="sample_1">
					                    <thead>
					                        <tr>
					                            <th>
					                                <label class="mt-checkbox mt-checkbox-outline mt-checkbox-single">
					                                    <input type="checkbox" class="group-checkable" data-set="#sample_1 .checkboxes" />
					                                    <span></span>
					                                </label>
					                            </th>
					                            <th> 物料编码 </th>
					                            <th> 物料名称 </th>
					                            <th> 规格型号 </th>
					                            <th> 单位 </th>
					                            <th> 类别 </th>
					                            <th> 产地 </th>
					                            <th> 品牌 </th>
					                            <th> 供应商 </th>
					                            <th> 版本 </th>
					                            <th> 状态 </th>
					                        </tr>
					                    </thead>
					                    <tbody>
					                        <tr  class="odd gradeX">
					                            <td>
					                                <label class="mt-checkbox mt-checkbox-outline mt-checkbox-single">
					                                    <input type="checkbox" class="checkboxes" value="{{list.serialNum}}" />
					                                    <span></span>
					                                </label>
					                            </td>
					                            <td> 2 </td>
					                            <td> 3 </td>
					                           <td> 4 </td>
					                            <td> 5 </td>
					                            <td> 3 </td>
					                            <td> 1 </td>
					                            <td> 1 </td>
					                            <td>  </td>
					                            <td> 1 </td>
					                            <td> 1 </td>
					                        </tr>
					                        
					                    </tbody>
					                </table>
					            </div>
					        </div>
                        </div>
                        <div class="tab-pane fade" id="tab_2_3">
                            <div class="portlet light bordered">
					            <div class="portlet-body">
					                <table class="table table-striped table-bordered table-hover table-checkable order-column" id="sample_1">
					                    <thead>
					                        <tr>
					                            <th>
					                                <label class="mt-checkbox mt-checkbox-outline mt-checkbox-single">
					                                    <input type="checkbox" class="group-checkable" data-set="#sample_1 .checkboxes" />
					                                    <span></span>
					                                </label>
					                            </th>
					                            <th> 物料编码 </th>
					                            <th> 物料名称 </th>
					                            <th> 规格型号 </th>
					                            <th> 单位 </th>
					                            <th> 类别 </th>
					                            <th> 产地 </th>
					                            <th> 品牌 </th>
					                            <th> 供应商 </th>
					                            <th> 版本 </th>
					                            <th> 状态 </th>
					                        </tr>
					                    </thead>
					                    <tbody>
					                        <tr  class="odd gradeX">
					                            <td>
					                                <label class="mt-checkbox mt-checkbox-outline mt-checkbox-single">
					                                    <input type="checkbox" class="checkboxes" value="{{list.serialNum}}" />
					                                    <span></span>
					                                </label>
					                            </td>
					                            <td> 3 </td>
					                            <td> 4 </td>
					                           <td> 3 </td>
					                            <td> 3 </td>
					                            <td> 1 </td>
					                            <td> 1 </td>
					                            <td> 1 </td>
					                            <td>  </td>
					                            <td> 1 </td>
					                            <td> 1 </td>
					                        </tr>
					                        
					                    </tbody>
					                </table>
					            </div>
					        </div>
                        </div>
                        <div class="tab-pane fade" id="tab_2_4">
                            <div class="portlet light bordered">
					            <div class="portlet-body">
					                <table class="table table-striped table-bordered table-hover table-checkable order-column" id="sample_1">
					                    <thead>
					                        <tr>
					                            <th>
					                                <label class="mt-checkbox mt-checkbox-outline mt-checkbox-single">
					                                    <input type="checkbox" class="group-checkable" data-set="#sample_1 .checkboxes" />
					                                    <span></span>
					                                </label>
					                            </th>
					                            <th> 物料编码 </th>
					                            <th> 物料名称 </th>
					                            <th> 规格型号 </th>
					                            <th> 单位 </th>
					                            <th> 类别 </th>
					                            <th> 产地 </th>
					                            <th> 品牌 </th>
					                            <th> 供应商 </th>
					                            <th> 版本 </th>
					                            <th> 状态 </th>
					                        </tr>
					                    </thead>
					                    <tbody>
					                        <tr  class="odd gradeX">
					                            <td>
					                                <label class="mt-checkbox mt-checkbox-outline mt-checkbox-single">
					                                    <input type="checkbox" class="checkboxes" value="{{list.serialNum}}" />
					                                    <span></span>
					                                </label>
					                            </td>
					                            <td> 4 </td>
					                            <td> 5 </td>
					                           <td> 4 </td>
					                            <td> 4 </td>
					                            <td> 1 </td>
					                            <td> 1 </td>
					                            <td> 1 </td>
					                            <td>  </td>
					                            <td> 1 </td>
					                            <td> 1 </td>
					                        </tr>
					                        
					                    </tbody>
					                </table>
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
<!-- END PAGE HEADER-->

<!-- 删除用户modal 开始 -->
			<div id="delMaterielModal" class="modal fade" tabindex="-1"
				data-backdrop="static" data-keyboard="false">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true"></button>
							<h4 class="modal-title">确认</h4>
						</div>
						<div class="modal-body">
							<p>是否删除已选物料?</p>
						</div>
						<div class="modal-footer">
							<button type="button" data-dismiss="modal"
								class="btn dark btn-outline">取消</button>
							<button type="button" ng-click="del()" class="btn green">确定
								</button>
						</div>
					</div>
				</div>
			</div>
			<!-- 删除用户modal 结束 -->
