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
<h3 class="page-title"> 仓库信息
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
            <a ui-sref="warehouse">仓库信息</a>
        </li>
    </ul>

</div>
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
                            <div class="actions">
                                <button  ng-show="warehouseView"    class="btn blue  btn-outline  btn-sm " ng-click="editWarehouse()">
                                            <i class="fa fa-edit"></i> 编辑 </button>
                                <button   ng-show="warehouseEdit"   class="btn red  btn-outline  btn-sm " ng-click="cancelEditWarehouse()">
                                            <i class="fa fa-undo"></i> 取消 </button>
                                <button  ng-hide="warehouseAdd"   type="submit"   class="btn blue  btn-outline  btn-sm " ng-click="saveWarehouse()">
                                            <i class="fa fa-save"></i> 保存 </button>
                            </div>
                        </div>
                        <div class="portlet-body form">
                            <form  id="warehouseForm" class="form-horizontal" >
								<div class="form-body">
									<div class="alert alert-danger display-hide">
                                                <button id="warehouseTips" class="close" data-close="alert"></button>请先输入正确数据！</div>
								           <div class="row">
                                                             <div class="col-md-6">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="warehouseNum"> <span class="required"> * </span>仓库编号 :</label>
                                                    <div class="col-md-8">
                                                        <input type="text" class="form-control" id="warehouseNum" name="warehouseNum" ng-model="warehouse.warehouseNum"  ng-hide="warehouseAdd" >
                                                        <div class="form-control-focus"> </div>
                                                          <p class="control-label left" ng-show="warehouseView">{{warehouse.warehouseNum}}</p> 
                                                    </div>
                                            </div>
										</div>
                                                            <div class="col-md-6">
                                                                <div class="form-group form-md-line-input">
                                                                <label class="col-md-4 control-label" for="warehouseName"> <span class="required"> * </span>仓库名称 :</label>
                                                                    <div class="col-md-8">
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
                                                                <div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="warehouseType"> <span class="required"> * </span>仓库类型 :</label>
                                                    <div class="col-md-8">
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
                                                            <div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="warehouseCategory"> <span class="required"> * </span>仓库分类 :</label>
                                                    <div class="col-md-8">
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
                                                             <div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="address"> <span class="required"> * </span>仓库地址:</label>
                                                    <div class="col-md-8">
                                                                        <input type="text" class="form-control"   id="address" name ="address"  ng-hide="warehouseAdd" 
												ng-model="warehouse.address" > 
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="warehouseView">{{warehouse.address}}</p> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <!--/span-->
                                                            <div class="col-md-6">
                                                            <div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="owner"> <span class="required"> * </span>仓库所有者 :</label>
                                                    <div class="col-md-8">
                                                                        <input type="text"  class="form-control" placeholder=""  id="owner" name ="owner"   ng-hide="warehouseAdd" 
												ng-model="warehouse.owner" > 
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="warehouseView">{{warehouse.owner}}</p> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <!--/span-->
                                                        </div>
                                                         <div class="row">
                                                            <div class="col-md-6">
                                                            <div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="admin"> <span class="required"> * </span>仓库管理员 :</label>
                                                    <div class="col-md-8">
                                                                        <input type="text"  class="form-control" placeholder=""  id="admin" name ="admin"   ng-hide="warehouseAdd" 
												ng-model="warehouse.admin" >
                                                                        <div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="warehouseView">{{warehouse.admin}}</p> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <!--/span-->
                                                            <div class="col-md-6">
                                                             <div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="admin"> <span class="required"> * </span>仓库面积 :</label>
                                                    <div class="col-md-8">
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
                                                            <div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="email"> <span class="required"> * </span>邮件 :</label>
                                                    <div class="col-md-8">
                                                                        <input type="text"    id="email"      name="email" class="form-control" placeholder=""  ng-model="warehouse.email"  ng-hide="warehouseAdd" >
                                                                        <div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="warehouseView">{{warehouse.email}}</p>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <!--/span-->
                                                            <div class="col-md-6">
                                                              <div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="tel"> <span class="required"> * </span>电话 :</label>
                                                    <div class="col-md-8">
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
                                                             <div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="tel"> <span class="required"> * </span>备注 :</label>
                                                    <div class="col-md-8">
                                                                        <input type="text"  class="form-control" placeholder=""  id="remark" name ="remark"  ng-hide="warehouseAdd" 
												ng-model="warehouse.remark">
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="warehouseView">{{warehouse.remark}}</p>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                           <div class="col-md-6">
                                                           <div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="fax"> <span class="required"> * </span>传真 :</label>
                                                    <div class="col-md-8">
                                                                        <input type="text"   class="form-control" placeholder=""  id="fax" name ="fax"  ng-hide="warehouseAdd" 
												ng-model="warehouse.fax" >
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="warehouseView">{{warehouse.fax}}</p>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
								</div>
							</form>
         				</div>

			
                        <div class="portlet-title">
                            <div class="caption">库位管理</div>
                            <div class="actions">
                                <button   ng-show="warehousepositionView" class="btn blue btn-outline  btn-sm " ng-click="editwarehouseposition()">
                                            <i class="fa fa-edit"></i> 编辑 </button>
                                <button   ng-show="warehousepositionEdit" class="btn red  btn-outline  btn-sm " ng-click="cancelCompany('warehouseposition')">
                                            <i class="fa fa-undo"></i> 取消 </button>
                                <button   ng-hide="warehousepositionAdd" class="btn blue btn-outline  btn-sm " ng-click="savewarehouseposition()">
                                            <i class="fa fa-save"></i> 保存 </button>
                            </div>
                        </div>
                        <div class="portlet-body form">
                        <div class="portlet-body">
				<table
					class="table table-striped table-bordered table-hover table-header-fixed">
					<thead>
						<tr>
							<th >仓库区位编码</th>
							<th>仓库区位名称 </th>
                            <th> 存储属性</th>
                            <th>最大行数</th>
                            <th>列数</th>
                            <th>层数 </th>
                            <th> 存储类型 </th>
                            <th>存储方式 </th>
                            <th> 默认长宽高 </th>
                            <th> 默认容积 </th>
                            <th> 默认承重 </th>
                            <th> 操作 </th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>
                            <form  id="warehousepositionForm" class="page-repeater form-horizontal">
								<div class="form-body" data-repeater-list="group-a"  >
									<!-- <div  class="row" data-repeater-item repeat-done="repeatDone()">ng-repeat="warehouseposition in warehousepositions  track by $index"  
										<div class="col-md-1">
											<div class="form-group">
												<div class="col-md-12 input-icon right">
													<input  type="text" ng-model="warehouseposition.qualificationName" name="qualificationName" 
														class="form-control" placeholder=""> <span
														class="help-block"></span>
														<label   ng-show="warehousepositionView"  class="c_edit" >{{warehouseposition.qualificationName}}</label>
												</div>
											</div>
										</div>
						<div class="col-md-2">
											<div class="form-group">
												<div class="col-md-22 input-icon right">
													<input  type="text" ng-model="warehouseposition.qualificationName" name="qualificationName" 
														class="form-control" placeholder=""> <span
														class="help-block"></span>
														<label   ng-show="warehousepositionView"  class="c_edit" >{{warehouseposition.qualificationName}}</label>
												</div>
											</div>
										</div>
										<div class="col-md-2">
											<div class="form-group">
												<div class="col-md-22 input-icon right">
													<input  type="text" ng-model="warehouseposition.qualificationName" name="qualificationName" 
														class="form-control" placeholder=""> <span
														class="help-block"></span>
														<label   ng-show="warehousepositionView"  class="c_edit" >{{warehouseposition.qualificationName}}</label>
												</div>
											</div>
										</div>
										<div class="col-md-2">
											<div class="form-group">
												<div class="col-md-22 input-icon right">
													<input  type="text" ng-model="warehouseposition.qualificationName" name="qualificationName" 
														class="form-control" placeholder=""> <span
														class="help-block"></span>
														<label   ng-show="warehousepositionView"  class="c_edit" >{{warehouseposition.qualificationName}}</label>
												</div>
											</div>
										</div>
										<div class="col-md-2">
											<div class="form-group">
												<div class="col-md-22 input-icon right">
													<input  type="text" ng-model="warehouseposition.qualificationName" name="qualificationName" 
														class="form-control" placeholder=""> <span
														class="help-block"></span>
														<label   ng-show="warehousepositionView"  class="c_edit" >{{warehouseposition.qualificationName}}</label>
												</div>
											</div>
										</div>
										<div class="col-md-2">
											<div class="form-group">
												<div class="col-md-22 input-icon right">
													<input  type="text" ng-model="warehouseposition.qualificationName" name="qualificationName" 
														class="form-control" placeholder=""> <span
														class="help-block"></span>
														<label   ng-show="warehousepositionView"  class="c_edit" >{{warehouseposition.qualificationName}}</label>
												</div>
											</div>
										</div>
										<div class="col-md-2">
											<div class="form-group">
												<div class="col-md-22 input-icon right">
													<input  type="text" ng-model="warehouseposition.qualificationName" name="qualificationName" 
														class="form-control" placeholder=""> <span
														class="help-block"></span>
														<label   ng-show="warehousepositionView"  class="c_edit" >{{warehouseposition.qualificationName}}</label>
												</div>
											</div>
										</div>
										<div class="col-md-2">
											<div class="form-group">
												<div class="col-md-22 input-icon right">
													<input  type="text" ng-model="warehouseposition.qualificationName" name="qualificationName" 
														class="form-control" placeholder=""> <span
														class="help-block"></span>
														<label   ng-show="warehousepositionView"  class="c_edit" >{{warehouseposition.qualificationName}}</label>
												</div>
											</div>
										</div>
										<div class="col-md-2">
											<div class="form-group">
												<div class="col-md-22 input-icon right">
													<input  type="text" ng-model="warehouseposition.qualificationName" name="qualificationName" 
														class="form-control" placeholder=""> <span
														class="help-block"></span>
														<label   ng-show="warehousepositionView"  class="c_edit" >{{warehouseposition.qualificationName}}</label>
												</div>
											</div>
										</div>
										<div class="col-md-2">
											<div class="form-group">
												<div class="col-md-22 input-icon right">
													<input  type="text" ng-model="warehouseposition.qualificationName" name="qualificationName" 
														class="form-control" placeholder=""> <span
														class="help-block"></span>
														<label   ng-show="warehousepositionView"  class="c_edit" >{{warehouseposition.qualificationName}}</label>
												</div>
											</div>
										</div>
										
										
									
										
									</div>
									/row -->
								</div>
								<div class="form-actions right" ng-hide="warehousepositionAdd">
									<a href="javascript:;" data-repeater-create class="btn blue btn-sm" ng-click="addRepeat()" >
                                            <i class="fa fa-plus"></i> 增加
                                     </a>
                                </div>
							</form>
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