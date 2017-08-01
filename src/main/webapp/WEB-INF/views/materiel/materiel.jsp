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
                     <span class="caption-subject font-blue-sharp bold uppercase">全部物料</span>
                 </div>
             </div>
             <div class="portlet-body">
                 <div id="tree_1" class="tree-demo">
                     <ul>
                         <li> Root node 1
                             <ul>
                                 <li data-jstree='{ "selected" : true }'>
                                     <a href="javascript:;"> Initially selected </a>
                                 </li>
                                 <li data-jstree='{ "icon" : "fa fa-briefcase icon-state-success " }'> custom icon URL </li>
                                 <li data-jstree='{ "opened" : true }'> initially open
                                     <ul>
                                         <li data-jstree='{ "disabled" : true }'> Disabled Node </li>
                                         <li data-jstree='{ "type" : "file" }'> Another node </li>
                                     </ul>
                                 </li>
                                 <li data-jstree='{ "icon" : "fa fa-warning icon-state-danger" }'> Custom icon class (bootstrap) </li>
                             </ul>
                         </li>
                         <li data-jstree='{ "type" : "file" }'>
                             <a href="http://www.jstree.com"> Clickanle link node </a>
                         </li>
                     </ul>
                 </div>
             </div>
         </div>
	</div>
	<div class="col-md-8">
        <div class="row">
	        <div class="portlet light bordered">
	            <div class="portlet-title">
	                <div class="actions">
	                    <button id="sample_editable_1_new" ui-sref="addMateriel" class="btn sbold green">
	                                    <i class="fa fa-plus"></i>物料
	                                </button>
	                </div>
	            </div>
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
	                    	<tr ng-repeat="list in materielList" ng-init="index=$index" class="odd gradeX">
	                            <td>
	                                <label class="mt-checkbox mt-checkbox-outline mt-checkbox-single">
	                                    <input type="checkbox" class="checkboxes" value="{{list.serialNum}}" />
	                                    <span></span>
	                                </label>
	                            </td>
	                            <td> {{list.materielNum}} </td>
	                            <td> {{list.materielName}} </td>
	                            <td> {{list.specifications}} </td>
	                            <td> {{list.unit}} </td>
	                            <td> {{list.type}} </td>
	                            <td> {{list.productionPlace}} </td>
	                            <td> {{list.brand}} </td>
	                            <td>  </td>
	                            <td> {{list.versionNO}} </td>
	                            <td> <span class="label label-sm label-success"> {{list.status}} </span></td>
	                        </tr>
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
        <div class="row">
        	<div class="portlet light ">
                <div class="portlet-body">
                    <ul class="nav nav-pills">
                        <li class="active">
                            <a href="#tab_2_1" data-toggle="tab"> Home </a>
                        </li>
                        <li>
                            <a href="#tab_2_2" data-toggle="tab"> Profile </a>
                        </li>
                        <li>
                            <a href="#tab_2_3" data-toggle="tab"> Profile </a>
                        </li>
                        <li>
                            <a href="#tab_2_4" data-toggle="tab"> Profile </a>
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

<!-- END MAIN CONTENT -->
<!-- BEGIN MAIN JS -->
<!-- <script>
    TableDatatablesManaged.init();
</script> -->
<!-- END MAIN JS -->