<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" %>
<style>
.form-control{widtd:200px}；
.control-label col-md-3{float:right}}
#tel,#fax,#area{margin-left:-4px};
#address,#email,#remark{margin-left:12px};

</style>
 <div class="tab-pane" id="tab_2">
                                        <div class="portlet box green">
                                            <div class="portlet-body form">
                                                <!-- BEGIN FORM-->
                                                <form  class="form-horizontal"  id="form_sample_1">
                                                    <div class="form-body">
                                                        <h3 class="form-section"  style="margin-top:10px">基本信息</h3>
                                                        
                                                          
                                                                
                                                                   <!-- <div ng-controller="WarehouseController">  -->
                                                                        <button  type="submit"  class="btn green" style="margin-left: 90%;"   ng-click="saveWarehouse( )">保存</button>
                                                                        <!-- <button type="button" class="btn default">Cancel</button> -->
                                                                    <!-- </div>  -->
                                                             
                                                             <div class="row">
                                                            <div class="col-md-6">
                                                                <div class="form-group">
                                                                    <label class="control-label col-md-3">仓库编号 :</label>
                                                                    <div class="col-md-5">
                                                                        <input type="text" class="form-control"   placeholder=""  id="warehouseNum" name ="warehouseNum" 
												ng-model="warehouse.warehouseNum">
                                                                        <span class="help-block">  </span>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <!--/span-->
                                                            <div class="col-md-6">
                                                                <div class="form-group ">
                                                                    <label class="control-label col-md-3">仓库名称 :</label>
                                                                    <div class="col-md-5">
                                                                       <input type="text" class="form-control" placeholder=""  id="warehouseName" name ="warehouseName" 
												ng-model="warehouse.warehouseName" > 
                                                                        <span class="help-block">  </span>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <!--/span-->
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-md-6">
                                                                <div class="form-group">
                                                                    <label class="control-label col-md-3">仓库类型 :</label>
                                                                    <div class="col-md-5">
                                                                    
                                                                    <select class="form-control"  id="warehouseType" name ="warehouseType"  ng-model="warehouse.warehouseType"  >
                                                                           <option value="">	选择仓库</option>
                                                                        <option value="1">	自建仓库</option>
                                                                        <option value="2">代管仓库</option>
                                                                        <option value="3">境外仓库</option>
                                                                        </select>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <!--/span-->
                                                            <div class="col-md-6">
                                                                <div class="form-group">
                                                                    <label class="control-label col-md-3">仓库分类 :</label>
                                                                    <div class="col-md-5">
                                                                    <select class="form-control" id="warehouseCategory" name ="warehouseCategory" ng-model="warehouse.warehouseCategory">
                                                                            <option value="">选择分类</option>
                                                                        <option value="A">机加工</option>
                                                                        <option value="B">备品备件</option>
                                                                        <option value="C">标准品</option>
                                                                        </select>
                                                                </div>
                                                            </div>
                                                            <!--/span-->
                                                        </div>
                                                        </div>
                                                        <!--/row-->
                                                        <div class="row">
                                                            <div class="col-md-6">
                                                                <div class="form-group">
                                                                    <label class="control-label col-md-3">仓库地址:</label>
                                                                    <div class="col-md-5">
                                                                        <input type="text" class="form-control"   id="address" name ="address" 
												ng-model="warehouse.address" > 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <!--/span-->
                                                            <div class="col-md-6">
                                                                <div class="form-group">
                                                                    <label class="control-label col-md-3">仓库所有者 :</label>
                                                                    <div class="col-md-5">
                                                                        <input type="text"  class="form-control" placeholder=""  id="owner" name ="owner" 
												ng-model="warehouse.owner" > 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <!--/span-->
                                                        </div>
                                                         <div class="row">
                                                            <div class="col-md-6">
                                                                <div class="form-group">
                                                                    <label class="control-label col-md-3">仓库管理员 :</label>
                                                                    <div class="col-md-5">
                                                                        <input type="text"  class="form-control" placeholder=""  id="admin" name ="admin" 
												ng-model="warehouse.admin" >
                                                                        
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <!--/span-->
                                                            <div class="col-md-6">
                                                                <!-- <div class="form-group has-error"> --><div class="form-group ">
                                                                    <label class="control-label col-md-3">仓库面积 :</label>
                                                                    <div class="col-md-5">
                                                                        <input type="text"  class="form-control" placeholder=""  id="area" name ="area" 
												ng-model="warehouse.area" > 
                                                                </div>
                                                            </div>
                                                            <!--/span-->
                                                        </div>
                                                        </div>
                                                         <div class="row">
                                                            <div class="col-md-6">
                                                                <div class="form-group">
                                                                    <label class="control-label col-md-3">邮件 :</label>
                                                                    <div class="col-md-5">
                                                                        <input type="text"    id="email"      name="email" class="form-control" placeholder=""  ng-model="warehouse.email">
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <!--/span-->
                                                            <div class="col-md-6">
                                                                <!-- <div class="form-group has-error"> --><div class="form-group ">
                                                                    <label class="control-label col-md-3">电话 :</label>
                                                                    <div class="col-md-5">
                                                                        <input type="text"  class="form-control" placeholder=""  id="tel" name ="tel" 
												ng-model="warehouse.tel" > 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <!--/span-->
                                                        </div>
                                                         <div class="row">
                                                            <div class="col-md-6">
                                                                <div class="form-group">
                                                                    <label class="control-label col-md-3">备注 :</label>
                                                                    <div class="col-md-5">
                                                                        <input type="text"  class="form-control" placeholder=""  id="remark" name ="remark" 
												ng-model="warehouse.remark">
                                                                    </div>
                                                                </div>
                                                            </div>
                                                           <div class="col-md-6">
                                                                <div class="form-group">
                                                                    <label class="control-label col-md-3">传真 :</label>
                                                                    <div class="col-md-5">
                                                                        <input type="text"   class="form-control" placeholder=""  id="fax" name ="fax" 
												ng-model="warehouse.fax" >
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                     <div class="portlet-body">
				<table
					class="table table-striped table-bordered table-hover table-header-fixed"
					id="sample_2">
					<tdead>
						<tr>
					<td style="width:10%">存储类型</td>
                     <td  style="width:10%"> 库位名称</td>
                      <td style="width:7%"> 存储属性</td>
                      <td style="width:5%"> 最大行数</td>
                      <td style="width:5%"> 最大列数</td>
                       <td style="width:5%"> 最大层数</td>
                        <td style="width:10%">存储类型</td>
                        <td style="width:10%"> 存储方式</td>
                         <td style="width:15%"> 默认长宽高</td>
                         <td style="width:15%"> 默认容积</td>
                          <td style="width:10%"> 默认承重</td>
                          <td style="width:10%"> 操作</td>
						</tr>
					</tdead>
					<tr ng-repeat="wp in warehousepositions" >
                     <td><input type="text"     style="border:false"class="form-control" placeholder=""  name ="positionNum"   ng-model="wp.positionNum" ></td>
                     <td> <input type="text"  class="form-control" placeholder=""   name ="positionName" ng-model="wp.positionName"  ></td>
                      <td> <input type="text"  class="form-control" placeholder=""  name ="storageAttribute"   ng-model="wp.storageAttribute"  ></td>
                      <td> <input type="text"  class="form-control" placeholder=""   name ="maxRows"  ng-model="wp.maxRows"  ></td>
                      <td><input type="text"  class="form-control" placeholder=""  name ="maxCols"   ng-model="wp.maxCols"  ></td>
                       <td> <input type="text"  class="form-control" placeholder=""   name ="maxLayers"   ng-model="wp.maxLayers"  ></td>
                        <td><input type="text"  class="form-control" placeholder=""  name ="storageType"  ng-model="wp.storageType"  ></td>
                        <td><input type="text"  class="form-control" placeholder=""   name ="storageMode"   ng-model="wp.storageMode"  ></td>
                         <td> <input type="text"  class="form-control" placeholder=""  name ="defaultLWH"   ng-model="wp.defaultLWH "  ></td>
                          <td> <input type="text"  class="form-control" placeholder=""  name ="defaultVolume"   ng-model="wp.defaultVolume"  ></td>
                          <td> <input type="text"  class="form-control" placeholder=""  name ="defaultBearing"   ng-model="wp.defaultBearing"  ></td>
                          <td> <button class="fa fa-edit" ng-click=""></button><button  class="fa fa-remove"  ng-click=""></button> <button class="fa fa-plus" ></button></td>
						</tr>
						
						<tbody>
					</tbody>
				</table>
			</div>
                                                </form>
                                                <!-- END FORM-->
                                            </div>
                                        </div>