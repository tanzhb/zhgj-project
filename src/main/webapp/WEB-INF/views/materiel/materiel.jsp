<<<<<<< HEAD
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
=======
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
>>>>>>> 20170809
<!-- BEGIN PAGE HEADER-->
<h3 class="page-title"> 物料列表
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
            <a ui-sref="materiel">基础数据</a>
        </li>
        <li>
            <a ui-sref="materiel">物料信息</a>
        </li>
    </ul>
</div>
<div class="row">
<!--      <div class="col-md-4">
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
	</div> -->
	<div class="col-md-12">
	        <div class="portlet box red">
	            <div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>物料列表
				</div>
				<div class="actions">
					 <shiro:hasPermission name="materiel_add"> 
					<a href="javascript:;" ui-sref="addMateriel"
						 class="btn btn-default btn-sm btn-circle">
						<i class="fa fa-plus"></i> 添加
					</a> 
					</shiro:hasPermission>
					<shiro:hasPermission name="materiel_edit"> 
					<a href="javascript:;" ng-click="editMateriel()"
						 class="btn btn-default btn-sm btn-circle">
						<i class="fa fa-edit"></i> 修改
					</a>
<<<<<<< HEAD
					<a href="javascript:;" ng-click="deleteMateriel()"
=======
					</shiro:hasPermission>
					<a href="javascript:;" data-target="#delMaterielModal"
						data-toggle="modal" 
>>>>>>> 20170809
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
	                            <th style="text-align: center"><input name="select_all" id="example-select-all" type="checkbox"/></th>
	                            <th> 物料编码 </th>
	                            <th> 物料名称 </th>
	                            <th> 规格型号 </th>
	                            <th> 单位 </th>
	                            <th> 上级物料 </th>
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
	                            <th> 上级物料 </th>
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
        <!-- END EXAMPLE TABLE PORTLET-->
    </div>
 </div>
 
 <div class="row">
<!--      <div class="col-md-4">
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
	</div> -->
	<div class="col-md-12">
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
                        <li>
                            <a href="#" data-target="#tab_2_5"  data-toggle="tab"> 库存信息 </a>
                        </li>
                        <li>
                            <a href="#" data-target="#tab_2_6"  data-toggle="tab"> 价格趋势 </a>
                        </li>
                    </ul>
                    <div class="tab-content">
                        <div class="tab-pane fade active in" id="tab_2_1">
					            <div class="portlet-body">
					                <table class="table table-striped table-bordered table-hover table-checkable order-column" id="sample_1">
					                    <thead>
					                        <tr>
					                            <th> 供应商物料编码 </th>
					                            <th> 供应商名称 </th>
					                            <th> 规格型号 </th>
					                            <th> 包装单位 </th>
					                            <th> 长 </th>
					                            <th> 宽 </th>
					                            <th> 高 </th>
					                            <th> 包装体积 </th>
					                            <th> 包装单位换算 </th>
					                        </tr>
					                    </thead>
					                    <tbody>
					                    </tbody>
					                </table>
					            </div>
					    </div>
                        <div class="tab-pane fade" id="tab_2_2">
					            未关联
                        </div>
                        <div class="tab-pane fade" id="tab_2_3">
					            <div class="portlet-body">
                          <table class="table table-striped table-bordered table-hover table-checkable order-column">
                              <thead>
                                  <tr>
                                      <th style="width:200px;">物料编码</th>
                                      <th>物料名称</th>
                                      <th>规格型号</th>
                                      <th>单位</th>
                                      <th>品牌</th>
                                      <th style="width:100px;">单套用量</th>
                                  </tr>
                              </thead>
                              <tbody ng-repeat="_BOM in BOM track by $index">
                                  <tr >
                                      <td>
                                      		<p class="form-control-static"> {{_BOM.materiel.materielNum}} </p>
			                          </td>
			                          <td>
			                                <p class="form-control-static"> {{_BOM.materiel.materielName}} </p>
			                          </td>
                                      <td><p class="form-control-static"> {{_BOM.materiel.specifications}} </p></td>
                                      <td><p class="form-control-static"> {{_BOM.materiel.unit}} </p></td>
                                      <td><p class="form-control-static"> {{_BOM.materiel.brand}} </p></td>
                                      <td>
			                                <p class="form-control-static"> {{_BOM.singleDose}} </p>
			                          </td>
                                  </tr>
                              </tbody>
                          </table>
					            </div>
                        </div>
                        <div class="tab-pane fade" id="tab_2_4">
					            <div class="portlet-body">
					                <table class="table table-striped table-bordered table-hover table-checkable order-column" id="sample_1">
					                    <thead>
					                          <tr>
					                            <th> 包装编号 </th>
					                            <th> 包装规格 </th>
					                            <th> 包装单位 </th>
					                            <th> 长 </th>
					                            <th> 宽 </th>
					                            <th> 高 </th>
					                            <th> 包装体积 </th>
					                            <th> 包装单位换算 </th>
					                        </tr>
					                    </thead>
					                    <tbody>
					                    	<tr>
					                            <td> {{materiel.packageNum}} </td>
					                            <td> {{materiel.packageSpecifications}} </td>
					                            <td> {{materiel.packageUnit}}</td>
					                            <td> {{materiel.packagewidth}} </td>
					                            <td> {{materiel.packageheight}} </td>
					                            <td> {{materiel.packagelength}} </td>
					                            <td> {{materiel.packagevolume}} </td>
					                            <td> {{materiel.packageUnitConversion}} </td>
					                        </tr>
					                    </tbody>
					                </table>
					        </div>
                        </div>
                        <div class="tab-pane fade" id="tab_2_5">
					            <div class="portlet-body">
					                	未关联
					            </div>
                        </div>
                        <div class="tab-pane fade" id="tab_2_6">
					            <div class="portlet-body">
					                	未关联
					            </div>
                        </div>
                    </div>
                </div>
            </div>
	        
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



<!-- 基本信息modal 开始 -->
	<div id="basicMaterielInfo" class="modal fade" tabindex="-1"
		data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true"></button>
					<h4 class="modal-title">基本信息</h4>
				</div>
				<div class="modal-body">
					<div class="row">
                          <div class="col-md-6">
                              <div class="form-group">
                                  <label class="control-label col-md-5">物料编码</label>
                                  <label class="control-label col-md-7">
                                      {{materiel.materielNum}}
                                  </label>
                                  
                              </div>
                          </div>
                          <!--/span-->
                          <div class="col-md-6">
                              <div class="form-group">
                                  <label class="control-label col-md-5">助记码</label>
                                  <label class="control-label col-md-7">
                                      {{materiel.mnemonicCode}}
                                  </label>
                              </div>
                          </div>
                          <!--/span-->
                      </div>
                      <!--/row-->
                      <div class="row">
                          <div class="col-md-6">
                              <div class="form-group">
                                  <label class="control-label col-md-5">物料种类</label>
                                  <label class="control-label col-md-7">
                                      {{materiel.type}}
                                  </label>

                              </div>
                          </div>
                          <!--/span-->
                          <div class="col-md-6">
                              <div class="form-group">
                                  <label class="control-label col-md-5">物料名称</label>
                                  <label class="control-label col-md-7">
                                      {{materiel.materielName}}
                                  </label>

                              </div>
                          </div>
                          <!--/span-->
                      </div>
                      <!--/row-->
                      <div class="row">
                          <div class="col-md-6">
                              <div class="form-group">
                                  <label class="control-label col-md-5">物料类别</label>
                                  <label class="control-label col-md-7">
                                      {{materiel.category}}
                                  </label>

                              </div>
                          </div>
                          <!--/span-->
                          <div class="col-md-6">
                              <div class="form-group">
                                  <label class="control-label col-md-5">单位</label>
                                  <label class="control-label col-md-7">
                                      {{materiel.unit}}
                                  </label>

                              </div>
                          </div>
                          <!--/span-->
                      </div>
                      <!--/row-->
                      <div class="row">
                          <div class="col-md-6">
                              <div class="form-group">
                                  <label class="control-label col-md-5">规格型号</label>
                                  <label class="control-label col-md-7">
                                      {{materiel.specifications}}
                                  </label>

                              </div>
                          </div>
                          <!--/span-->
                          <div class="col-md-6">
                              <div class="form-group">
                                  <label class="control-label col-md-5">产地</label>
                                  <label class="control-label col-md-7">
                                       {{materiel.productionPlace}}
                                  </label>

                              </div>
                          </div>
                          <!--/span-->
                      </div>
                      <!--/row-->
                      <div class="row">
                          <div class="col-md-6">
                              <div class="form-group">
                                  <label class="control-label col-md-5">库存单位</label>
                                  <label class="control-label col-md-7">
                                      {{materiel.stockUnit}}
								</label>
                              </div>
                          </div>
                          <!--/span-->
                          <div class="col-md-6">
                              <div class="form-group">
                                  <label class="control-label col-md-5">上级产品</label>
                                  <label class="control-label col-md-7">
                                      {{materiel.parentMateriel.materielName}}
                                  </label>
                              </div>
                          </div>
                          <!--/span-->
                      </div>
                      <!--/row-->
                       <div class="row">
                          <div class="col-md-6">
                              <div class="form-group">
                                  <label class="control-label col-md-5">品牌</label>
                                  <label class="control-label col-md-7">
                                      {{materiel.brand}}
                                  </label>

                              </div>
                          </div>
                          <!--/span-->
                          <div class="col-md-6">
                              <div class="form-group">
                                  <label class="control-label col-md-5">原产国</label>
                                  <label class="control-label col-md-7">
                                      {{materiel.originCountry}}
                                  </label>
                              </div>
                          </div>
                          <!--/span-->
                      </div>
                      <!--/row-->
                       <div class="row">
                          <div class="col-md-6">
                              <div class="form-group">
                                  <label class="control-label col-md-5">长度</label>
                                  <label class="control-label col-md-7">
                                      {{materiel.length}}
                                  </label>

                              </div>
                          </div>
                          <!--/span-->
                          <div class="col-md-6">
                              <div class="form-group">
                                  <label class="control-label col-md-5">币种</label>
                                  <label class="control-label col-md-7">
                                      {{materiel.currency}}
                                  </label>
                              </div>
                          </div>
                          <!--/span-->
                      </div>
                      <!--/row-->
                      <div class="row">
                          <div class="col-md-6">
                              <div class="form-group">
                                  <label class="control-label col-md-5">宽度</label>
                                  <label class="control-label col-md-7">
                                      {{materiel.width}}
                                  </label>

                              </div>
                          </div>
                          <!--/span-->
                          <div class="col-md-6">
                              <div class="form-group">
                                  <label class="control-label col-md-5">单价</label>
                                  <label class="control-label col-md-7">
                                      {{materiel.unitPrice}}
                                  </label>
                              </div>
                          </div>
                          <!--/span-->
                      </div>
                      <!--/row-->
                      <div class="row">
                          <div class="col-md-6">
                              <div class="form-group">
                                  <label class="control-label col-md-5">高度</label>
                                  <label class="control-label col-md-7">
                                      {{materiel.height}}
                                  </label>

                              </div>
                          </div>
                          <!--/span-->
                          <div class="col-md-6">
                              <div class="form-group">
                                  <label class="control-label col-md-5">备案项号</label>
                                  <label class="control-label col-md-7">
                                      {{materiel.filingItemNo}}
                                  </label>
                              </div>
                          </div>
                          <!--/span-->
                      </div>
                      <!--/row-->
                      <div class="row">
                          <div class="col-md-6">
                              <div class="form-group">
                                  <label class="control-label col-md-5">单件体积</label>
                                  <label class="control-label col-md-7">
                                      {{materiel.volume}}
                                  </label>

                              </div>
                          </div>
                          <!--/span-->
                          <div class="col-md-6">
                              <div class="form-group">
                                  <label class="control-label col-md-5">海关监管条件</label>
                                  <label class="control-label col-md-7">
                                      {{materiel.customsSupervisionConditions}}
                                  </label>
                              </div>
                          </div>
                          <!--/span-->
                      </div>
                      <!--/row-->
                      <div class="row">
                          <div class="col-md-6">
                              <div class="form-group">
                                  <label class="control-label col-md-5">单件重量</label>
                                  <label class="control-label col-md-7">
                                      {{materiel.weight}}
                                  </label>

                              </div>
                          </div>
                          <!--/span-->
                          <div class="col-md-6">
                              <div class="form-group">
                                  <label class="control-label col-md-5">保质期</label>
                                  <label class="control-label col-md-7">
                                      {{materiel.qualityDate}}
                                  </label>
                              </div>
                          </div>
                          <!--/span-->
                      </div>
                      <!--/row-->
                      <div class="row">
                          <div class="col-md-6">
                              <div class="form-group">
                                  <label class="control-label col-md-5">托盘规格</label>
                                  <label class="control-label col-md-7">
                                      {{materiel.palletSpecification}}
                                  </label>

                              </div>
                          </div>
                          <!--/span-->
                          <div class="col-md-6">
                              <div class="form-group">
                                  <label class="control-label col-md-5">生产日期</label>
                                  <label class="control-label col-md-7">
                                      {{materiel.manufactureDate}}
                                  </label>
                              </div>
                          </div>
                          <!--/span-->
                      </div>
                      <!--/row-->
                      <div class="row">
                          <div class="col-md-6">
                              <div class="form-group">
                                  <label class="control-label col-md-5">每托数量</label>
                                  <label class="control-label col-md-7">
                                      {{materiel.palletWeight}}
                                  </label>

                              </div>
                          </div>
                          <!--/span-->
                          <div class="col-md-6">
                              <div class="form-group">
                                  <label class="control-label col-md-5">备注</label>
                                  <label class="control-label col-md-7">
                                      {{materiel.remark}}
                                  </label>
                              </div>
                          </div>
                          <!--/span-->
                      </div>
                      <!--/row-->
                      <div class="row">
                          <div class="col-md-6">
                              <div class="form-group">
                                  <label class="control-label col-md-5">是否BOM物料</label>
                                  <label class="control-label col-md-7">
                                        {{materiel.isBOM=="1"?'是':'否'}}
                                  </label>
                              </div>
                          </div>
                          <!--/span-->
                          <div class="col-md-6">
                              <div class="form-group">
                                  <label class="control-label col-md-5">版本</label>
                                  <label class="control-label col-md-7">
                                  	{{materiel.versionNO}}
                                  </label>
                              </div>
                          </div>
                          <!--/span-->
                      </div>
                      <!--/row-->
				</div>
			</div>
		</div>
	</div>
<!-- 基本信息modal 结束 -->