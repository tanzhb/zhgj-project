<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- BEGIN PAGE HEADER-->
<style>
.box_card{
	width: 33.333333%;
	float: left;
	margin-left: 0px;
	margin-right: 0px;
	margin-top: 4px;
	margin-bottom: 4px;
}

.left{
	float: left;
}
</style>
<!-- <h3 class="page-title"> 仓库信息
</h3> -->
<!-- <div class="page-bar">
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
            <a ui-sref="warehouse">仓库信息</a>
        </li>
    </ul>

</div> -->
<!-- END PAGE HEADER-->
<!-- BEGIN MAIN CONTENT -->
<div class="row">
    <div class="col-md-12">
        <!-- BEGIN EXAMPLE TABLE PORTLET-->
        <div class="portlet bordered">
            <div class="portlet-body">
				<div class="portlet light ">
                        <div class="portlet-title">
                            <div class="caption">仓库信息</div>
                            <div class="actions" >
                                <button type="button" onclick="goBackPage()" class="btn defualt  btn-circle  btn-sm"><i class="fa fa-reply"></i>返回</button>
                            </div>
                            <div class="actions">
                                <button  ng-show="warehouseView"    class="btn purple  btn-sm btn-circle" ng-click="editWarehouse()">
                                            <i class="fa fa-edit"></i> 编辑 </button>
                                <button   ng-show="warehouseEdit"   class="btn defualt  btn-sm btn-circle " ng-click="cancelEditWarehouse()">
                                            <i class="fa fa-undo"></i> 取消 </button>
                                <button  ng-hide="warehouseAdd"   type="submit"   class="btn green  btn-sm btn-circle " ng-click="saveWarehouse()">
                                            <i class="fa fa-save"></i> 保存 </button>
                            </div>
                        </div>
                            <div class="tab-content">
				<div class="tab-pane fade active in" id="tab_1_1">
                        <div class="portlet-body form">
                            <form  id="warehouseForm"  >
								<div class="form-body">
									<div class="alert alert-danger display-hide">
                                                <button  class="close" data-close="alert"></button>请先输入正确数据！</div>
								           <div class="row">
                                                             <div class="col-md-6">
											<div class="form-group ">
                                                    <label class="control-label bold" for="warehouseNum"> <span class="required"> * </span>仓库编号 :</label>
                                                    <div class=" ">
                                                        <input type="text" class="form-control" id="warehouseNum" name="warehouseNum" ng-model="warehouse.warehouseNum"  ng-hide="warehouseAdd" >
                                                        <div class="form-control-focus"> </div>
                                                          <p class="control-label left" ng-show="warehouseView">{{warehouse.warehouseNum}}</p> 
                                                    </div>
                                            </div>
										</div>
                                                            <div class="col-md-6">
                                                                <div class="form-group ">
                                                                <label class="control-label bold" for="warehouseName"> <span class="required"> * </span>仓库名称 :</label>
                                                                    <div class=" ">
                                                                       <input type="text" class="form-control" placeholder=""  id="warehouseName" name ="warehouseName"  ng-hide="warehouseAdd" 
												ng-model="warehouse.warehouseName" > 
                                                                       <div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="warehouseView">{{warehouse.warehouseName}}</p> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-md-6">
                                                                <div class="form-group ">
                                                    <label class="control-label bold" for="warehouseType"> <span class="required"> * </span>仓库类型 :</label>
                                                    <div class=" ">
                                                    <select class="form-control" id="warehouseType"  name="warehouseType"  ng-model="warehouse.warehouseType"   ng-hide="warehouseAdd" >
                                                            			<option value=""></option>
                                                                        <option value="自建仓库">	自建仓库</option>
                                                                        <option value="代管仓库">代管仓库</option>
                                                                        <option value="境外仓库">境外仓库</option>
                                                        </select>
                                                          <div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="warehouseView">{{warehouse.warehouseType}}</p> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <!--/span-->
                                                            <div class="col-md-6">
                                                            <div class="form-group ">
                                                    <label class="control-label bold" for="warehouseCategory"> <span class="required"> * </span>仓库分类 :</label>
                                                    <div class=" ">
                                                                    <select class="form-control" id="warehouseCategory" name ="warehouseCategory" ng-model="warehouse.warehouseCategory"  ng-hide="warehouseAdd"  >
                                                                          <option value=""></option>
                                                                        <option value="机加工">机加工</option>
                                                                        <option value="备品备件">备品备件</option>
                                                                        <option value="标准品">标准品</option>
                                                                        </select>
                                                                         <div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="warehouseView">{{warehouse.warehouseCategory}}</p> 
                                                                </div>
                                                            </div>
                                                            <!--/span-->
                                                        </div>
                                                        </div>
                                                        <!--/row-->
                                                        <div class="row">
                                                            <div class="col-md-6">
                                                             <div class="form-group ">
                                                    <label class="control-label bold" for="address"> <span class="required"> * </span>仓库地址:</label>
                                                    <div class=" ">
                                                                        <input type="text" class="form-control"   id="address" name ="address"  ng-hide="warehouseAdd" 
												ng-model="warehouse.address" > 
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="warehouseView">{{warehouse.address}}</p> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <!--/span-->
                                                            <div class="col-md-6">
                                                            <div class="form-group ">
                                                    <label class="control-label bold" for="owner"> <span class="required"> * </span>仓库所有者 :</label>
                                                    <div class=" ">
                                                                        <!-- <input type="text"  class="form-control" placeholder=""  id="owner" name ="owner"   ng-hide="warehouseAdd" 
												ng-model="warehouse.owner" >  -->
												<div  ng-hide="warehouseView"><!--ng-show="warehouseAdd"   -->
												 <select class="form-control" data-live-search="true" data-size=""   id="owner" name ="owner"     ng-model="warehouse.owner"    ng-if="warehouseowner1==''">
                                                       <!--  <option  ng-repeat="op in supplyCom " value="{{op.comId}}"  > {{op.comName}}</option> -->
                                                       <option value="">无</option>
                                                         <option value="pingtai"   >中航能科（上海）能源科技有限公司</option>
                                                   <option  ng-repeat="com in coms" value="{{com.comId}}" >{{com.comName}}</option><!--  disabled="disabled" -->
                                                    </select>
                                                     <!-- <select class="form-control" data-live-search="true" data-size=""   id="owner" name ="owner"    ng-model="warehouse.owner"  ng-if="warehouse.owner!=''">
                                                        <option  ng-repeat="op in supplyCom " value="{{op.comId}}"  > {{op.comName}}</option>
                                                       <option   selected  value="">{{warehouse.ownerName}}</option>
                                                    </select> -->
                                                    <input type="text"  class="form-control" placeholder=""  id="owner" name ="owner"  readonly="readonly"     ng-if="warehouseowner1!=''"
												ng-model="warehouse.ownerName" >
                                                    </div>
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="warehouseView">{{warehouse.ownerName==null?"中航能科（上海）能源科技有限公司":warehouse.ownerName}}</p> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <!--/span-->
                                                        </div>
                                                         <div class="row">
                                                            <div class="col-md-6">
                                                            <div class="form-group ">
                                                    <label class="control-label bold" for="admin"> <!-- <span class="required"> * </span> -->仓库管理员 :</label>
                                                    <div class=" ">
                                                                        <input type="text"  class="form-control" placeholder=""  id="admin" name ="admin"   ng-hide="warehouseAdd" 
												ng-model="warehouse.admin" >
                                                                        <div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="warehouseView">{{warehouse.admin}}</p> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <!--/span-->
                                                            <div class="col-md-6">
                                                             <div class="form-group ">
                                                    <label class="control-label bold" for="area"> <!-- <span class="required"> * </span> -->仓库面积 :</label>
                                                    <div class=" ">
                                                                        <input type="text"  class="form-control" placeholder=""  id="area" name ="area"  ng-hide="warehouseAdd" 
												ng-model="warehouse.area" >
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="warehouseView">{{warehouse.area}}</p>  
                                                                </div>
                                                            </div>
                                                            <!--/span-->
                                                        </div>
                                                        </div>
                                                         <div class="row">
                                                            <div class="col-md-6">
                                                            <div class="form-group ">
                                                    <label class="control-label bold" for="email"> <!-- <span class="required"> * </span> -->邮件 :</label>
                                                    <div class=" ">
                                                                        <input type="text"    id="email"      name="email" class="form-control" placeholder=""  ng-model="warehouse.email"  ng-hide="warehouseAdd" >
                                                                        <div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="warehouseView">{{warehouse.email}}</p>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <!--/span-->
                                                            <div class="col-md-6">
                                                              <div class="form-group ">
                                                    <label class="control-label bold" for="tel"> <!-- <span class="required"> * </span -->电话 :</label>
                                                    <div class=" ">
                                                                        <input type="text"  class="form-control" placeholder=""  id="tel" name ="tel"  ng-hide="warehouseAdd" 
												ng-model="warehouse.tel" >  
												 <div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="warehouseView">{{warehouse.tel}}</p>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <!--/span-->
                                                        </div>
                                                         <div class="row">
                                                            <div class="col-md-6">
                                                             <div class="form-group ">
                                                    <label class="control-label bold" for="tel"> <!-- <span class="required"> * </span> -->备注 :</label>
                                                    <div class=" ">
                                                                        <input type="text"  class="form-control" placeholder=""  id="remark" name ="remark"  ng-hide="warehouseAdd" 
												ng-model="warehouse.remark">
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="warehouseView">{{warehouse.remark}}</p>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                           <div class="col-md-6">
                                                           <div class="form-group ">
                                                    <label class="control-label bold" for="fax"> <!-- <span class="required"> * </span> -->传真 :</label>
                                                    <div class=" ">
                                                                        <input type="text"   class="form-control" placeholder=""  id="fax" name ="fax"  ng-hide="warehouseAdd" 
												ng-model="warehouse.fax" >
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="warehouseView">{{warehouse.fax}}</p>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        
                                                        <div class="row">
                                                            <div class="col-md-6">
	                                                            <div class="form-group ">
			                                                    	<label class="control-label bold">关联WMS仓库 :</label>
																	<div  ng-hide="warehouseView"><!--ng-show="warehouseAdd"   -->
																	 	<select class="form-control" data-live-search="true" data-size="10" id="wmsWarehouse" ng-model="warehouse.wmsWarehouseId" ng-change="">
					                                                       	<option value="">无</option>
					                                                   		<option  ng-repeat="wmsWarehouse in wmsWarehouses" value="{{wmsWarehouse.id}}" >{{wmsWarehouse.storeName}}</option><!--  disabled="disabled" -->
					                                                    </select>
					                                                </div>
																	<div class="form-control-focus"> </div>
				                                                    <p class="control-label left" ng-show="warehouseView">{{warehouse.wmsWarehouseName}}</p> 
	                                                             </div>
                                                            </div>
                                                            <!--/span-->
                                                        </div>
								</div>
							</form>
         				</div>

			
                        <div class="portlet-title">
                            <div class="caption">区位管理</div>
                            <div class="actions" style="float:right;">
                                <button   ng-show="warehousepositionView"  class="btn purple  btn-sm btn-circle" ng-click="editAllwarehouseposition()">
                                            <i class="fa fa-edit"></i> 编辑 </button>
                                <button    ng-hide="warehousepositionAdd"  class="btn red  btn-sm btn-circle" ng-click="cancelAllwarehouseposition()">
                                            <i class="fa fa-undo"></i> 取消 </button>
                                <button   ng-hide="warehousepositionAdd" class="btn green  btn-sm btn-circle" ng-click="saveAllwarehouseposition()">
                                            <i class="fa fa-save"></i> 保存 </button>
                            </div>
                           
                        </div>
                         </br>
                        <div class="portlet-body form">
                        <!-- <div class="portlet-body">
				<table
					class="table table-striped  table-hover table-header-fixed"> -->
					  <div class="table-scrollable">
                         <table class="table table-bordered table-hover">
					<thead>
						<tr>
							<th >仓库区位编码</th>
							<th >仓库区位名称 </th>
                            <th> 存储属性</th>
                            <th >最大行数</th>
                            <th >列数</th>
                            <th>层数 </th>
                            <th > 存储类型 </th>
                            <th >存储方式 </th>
                            <th > 默认长宽高 </th>
                            <th> 默认容积 </th>
                            <th > 默认承重 </th>
                            <th > 操作 </th>
						</tr>
					</thead>
					<tbody>
					<tr ng-repeat="warehouseposition in warehousepositions track by $index" ng-mouseover="showOperation('warehouseposition',$index)" ng-mouseleave="hideOperation('warehouseposition',$index)"  repeat-done="repeatDone(this)">
										<td style="width: 100px!important">
											<div class="form-group">
												 <input  type="hidden"    id="serialNum{{$index}}"      value="{{warehouseposition.serialNum}}" />
													<input  type="text"   ng-model="warehouseposition.positionNum"      id="positionNum{{$index}}"     ng-hide="warehousepositionAdd{{$index}}"       
														class="form-control" placeholder=""> <span
														class="help-block"></span>
														<label   ng-show="warehousepositionView{{$index}}"  class="c_edit" >{{warehouseposition.positionNum}}</label>
											</div>
										</td>
						<td>
											<div class="form-group"  style="width: 100px!important">
													<input  type="text"   ng-model="warehouseposition.positionName"    name="positionName"   id="positionName{{$index}}"  ng-hide="warehousepositionAdd{{$index}}"
														class="form-control" placeholder=""> <span
														class="help-block"></span>
														<label   ng-show="warehousepositionView{{$index}}"  class="c_edit" >{{warehouseposition.positionName}}</label>
											</div>
										</td>
										<td >
											<div class="form-group"  style="width: 100px!important">
												
													<input  type="text" ng-model="warehouseposition.storageAttribute" name="storageAttribute"    id="storageAttribute{{$index}}"  ng-hide="warehousepositionAdd{{$index}}"
														class="form-control" placeholder=""> <span
														class="help-block"></span>
														<label   ng-show="warehousepositionView{{$index}}"  class="c_edit" >{{warehouseposition.storageAttribute}}</label>
											</div>
									</td >
									<td>
											<div class="form-group" style="width: 100px!important">
													<input  type="text" ng-model="warehouseposition.maxRows" name="maxRows"    id="maxRows{{$index}}"  ng-hide="warehousepositionAdd{{$index}}"
														class="form-control" placeholder=""> <span
														class="help-block"></span>
														<label   ng-show="warehousepositionView{{$index}}"  class="c_edit" >{{warehouseposition.maxRows}}</label>
											</div>
										</td>
										<td>
											<div class="form-group" style="width: 100px!important">
												<div class="col-md-12 input-icon right">
													<input  type="text" ng-model="warehouseposition.maxCols" name="maxCols"  id="maxCols{{$index}}"   ng-hide="warehousepositionAdd{{$index}}"
														class="form-control" placeholder=""> <span
														class="help-block"></span>
														<label   ng-show="warehousepositionView{{$index}}"  class="c_edit" >{{warehouseposition.maxCols}}</label>
												</div>
											</div>
										</td>
										<td>
											<div class="form-group" style="width: 100px!important">
												<div class="col-md-12 input-icon right"  >
													<input  type="text" ng-model="warehouseposition.maxLayers" name="maxLayers"   id="maxLayers{{$index}}"  ng-hide="warehousepositionAdd{{$index}}"
														class="form-control" placeholder=""> <span
														class="help-block"></span>
														<label   ng-show="warehousepositionView{{$index}}"  class="c_edit" >{{warehouseposition.maxLayers}}</label>
												</div>
											</div>
										</td>
										<td>
											<div class="form-group" style="width: 100px!important">
												<div class="col-md-12 input-icon right">
												 <select class="form-control" id="storageType"  name="storageType"    id="storageType{{$index}}"  ng-model="warehouseposition.storageType"   ng-hide="warehousepositionAdd{{$index}}">
                                                            			<option value="">未定</option>
                                                        </select>
													<!-- <input  type="text" ng-model="warehouseposition.storageType" name="storageType" 
														class="form-control" placeholder=""/> --> <span
														class="help-block"></span>
														<label   ng-show="warehousepositionView{{$index}}"  class="c_edit" >{{warehouseposition.storageType}}</label>
												</div>
											</div>
										</td>
										<td >
											<div class="form-group" style="width: 100px!important">
												<div class="col-md-12 input-icon right">
												<select class="form-control" id="storageMode"  name="storageMode"    id="storageMode{{$index}}"   ng-model="warehouseposition.storageMode"  ng-hide="warehousepositionAdd{{$index}}">
                                                            			<option value="">未定</option>
                                                        </select>
													<!-- <input  type="text" ng-model="warehouseposition.storageMode" name="storageMode" 
														class="form-control" placeholder=""/> --> <span
														class="help-block"></span>
														<label   ng-show="warehousepositionView{{$index}}"  class="c_edit" >{{warehouseposition.storageMode}}</label>
												</div>
											</div>
										</td>
										<td >
											<div class="form-group" style="width: 100px!important">
												<div class="col-md-12 input-icon right">
													<input  type="text"   ng-model="warehouseposition.defaultLWH"   name="defaultLWH"   id="defaultLWH{{$index}}"   ng-hide="warehousepositionAdd{{$index}}"
														class="form-control" placeholder=""> <span
														class="help-block"></span>
														<label   ng-show="warehousepositionView{{$index}}"  class="c_edit" >{{warehouseposition.defaultLWH}}</label>
												</div>
											</div>
										</td>
										<td>
											<div class="form-group" style="width: 100px!important">
												<div class="col-md-12 input-icon right">
													<input  type="text"   ng-model="warehouseposition.defaultVolume"   name="defaultVolume"     id="defaultVolume{{$index}}"   ng-hide="warehousepositionAdd{{$index}}"
														class="form-control" placeholder=""> <span
														class="help-block"></span>
														<label   ng-show="warehousepositionView{{$index}}"  class="c_edit" >{{warehouseposition.defaultVolume}}</label>
												</div>
											</div>
										</td> 
										<td >
											<div class="form-group" style="width: 100px!important">
												<div class="col-md-12 input-icon right">
													<input  type="text" ng-model="warehouseposition.defaultBearing" name="defaultBearing"    id="defaultBearing{{$index}}"   ng-hide="warehousepositionAdd{{$index}}"
														class="form-control" placeholder=""> <span
														class="help-block"></span>
														<label   ng-show="warehousepositionView{{$index}}"  class="c_edit" >{{warehouseposition.defaultBearing}}</label>
												</div>
											</div>
										</td> 
									<td >
											<div class="form-group" style="width: 100px!important">
											<div class="col-md-8" >
													 <a href="javascript:;"  ng-hide="warehousepositionEdit{{$index}}"              ng-click="savewarehouseposition($index)">
                                            			<i class="fa fa-save"  title="保存"></i> 
                                     				</a>
                                     			
                                     				<a href="javascript:;"  ng-hide="warehousepositionEdit{{$index}}"       ng-click="cancelWarehouseposition(warehouseposition,$index)">
                                            			<i class="fa fa-undo"  title="取消"></i>
                                     				</a>
												</div>
												<div class="col-md-8"  >
												 <a href="javascript:;"  ng-show="warehousepositionEdit{{$index}}"      ng-click="editwarehouseposition($index)">
                                            			<i class="fa fa-edit"  title="编辑"></i> 
                                     				</a>
                                     				
													 <a href="javascript:;"   ng-show="warehousepositionEdit{{$index}}"       ng-click="deleteWarehouseposition(warehouseposition)">
                                            			<i class="fa fa-close"  title="删除"></i> 
                                     				</a>
												</div>
											</div>
										</td>
										</tr>
										</tbody>
				</table>
			</div>
								<div class="form-actions right" ng-hide="warehousepositionAdd">
									<a href="javascript:;"  class="btn blue btn-sm" ng-click="addRepeat()" >
                                            <i class="fa fa-plus"></i> 增加
                                     </a>
                                </div>
							
         				</div>

				
                    
				
				
				</div>
				
        </div>
        <!-- END EXAMPLE TABLE PORTLET-->
    </div>
</div>
<!-- END MAIN CONTENT -->
<!-- BEGIN MAIN JS -->
 <script>
</script> 
<!-- END MAIN JS -->