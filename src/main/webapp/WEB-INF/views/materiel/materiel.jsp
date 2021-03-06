<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<style>
.btn-default.active, .btn-default:active{
color: #32c5d2;
background-color: #fff;
border-color: #32c5d2;
}
.btn-default.active.focus, .btn-default.active:hover{
color: #32c5d2;
background-color: #fff;
border-color: #32c5d2;
}
.btn-default-margin{
margin-right: 20px;
margin-bottom: 20px;
}


</style>
<div class="tabbable-line">
    <ul class="nav nav-tabs">
		<li class="active"><a data-target="#tab_15_1" data-toggle="tab">物料列表</a>
			</li>
		<shiro:hasRole name="admin">
		<li class=""><a data-target="#tab_15_2" data-toggle="tab" ng-click="queryCategoryListByParent('frist','0')">物料分类</a></li>
		</shiro:hasRole>
	</ul>
    <div class="tab-content">
    	<!-- 普通订单---START -->
      <div class="tab-pane active" id="tab_15_1">
			<div class="row">
				<shiro:hasRole name="admin">
			     <div class="col-md-2" id = "allTreeType">
			         <div class="portlet light ">
			             <div class="portlet-title">
			                 <div class="caption">
			                     <span ng-click="reloadTable()" class="caption-subject font-blue-sharp bold uppercase">物料分类</span>
			                 </div>
			             </div>
			             <div class="portlet-body">
			                 <div id="tree_1" class="tree-demo">
			                 </div>
			             </div>
			         </div>
				</div>
				</shiro:hasRole>
				<div <shiro:hasRole name="admin"> class="col-md-10" </shiro:hasRole> class="col-md-12"  id="allMaterielList">
					<div class="row">
						<div class="col-md-12">
				        <div class="portlet light">
				            <div class="portlet-title">
					            <div class="caption">
									<span class="caption-subject font-green bold uppercase">物料列表</span>
								</div>
				            
							<div class="actions">
								<%-- <shiro:hasPermission name="materiel:add">
									<a href="javascript:;" ui-sref="addMateriel"
										 class="btn btn-default btn-sm btn-circle">
										<i class="fa fa-plus"></i> 添加
									</a> 
								</shiro:hasPermission>
								<shiro:hasPermission name="materiel:edit">
								<a href="javascript:;" ng-click="editMateriel()"
									 class="btn btn-default btn-sm btn-circle">
									<i class="fa fa-edit"></i> 修改
								</a>
								</shiro:hasPermission>
								<shiro:hasPermission name="materiel:delete">
								<a href="javascript:;" ng-click="deleteMateriel()"
									class="btn btn-default btn-sm btn-circle"> <i
									class="fa fa-minus"></i> 删除
								</a>
								</shiro:hasPermission>
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
											class="tool-action" ng-click="exportMateriel()"> <i class="fa fa-file-excel-o"></i> 导出
										</a></li>
									</ul>
								</div> --%>
								<shiro:hasPermission name="materiel:add">
								<label class="btn btn-transparent green btn-circle btn-sm" ui-sref="addMateriel">
				                                              <i class="fa fa-plus"></i> 添加</label>
				                </shiro:hasPermission>
				                <shiro:hasPermission name="materiel:edit">
								<label class="btn btn-transparent purple btn-circle btn-sm" ng-click="editMateriel()">
			                                              <i class="fa fa-edit"></i> 修改</label>
			                    </shiro:hasPermission>
			                    <shiro:hasPermission name="materiel:delete">
								<label class="btn btn-transparent red btn-circle btn-sm" ng-click="deleteMateriel()">
			                                              <i class="fa fa-minus"></i> 删除</label>
			                    </shiro:hasPermission>   
			                    <shiro:hasPermission name="materiel:import">                                           
								<label class="btn btn-transparent green btn-outline btn-circle btn-sm" data-toggle="modal" data-target="#import" >
			                                              <i class="fa fa-upload"></i> 导入</label>
			                    </shiro:hasPermission>
			                    <shiro:hasPermission name="materiel:export">        
								<label class="btn btn-transparent yellow-casablanca btn-outline btn-circle btn-sm" ng-click="exportMateriel()">
			                                              <i class="fa fa-file-excel-o"></i> 导出</label>
			                   	</shiro:hasPermission> 
							</div>
				            </div>
				            <div class="portlet-body">
				                <table class="table table-striped table-bordered table-hover table-checkable order-column" id="sample_2">
				                    <thead>
				                        <tr>
				                            <th>
			                                    <label class="mt-checkbox mt-checkbox-single mt-checkbox-outline">
			                                        <input type="checkbox" class="group-checkable" data-set="#sample_2 .checkboxes" />
			                                        <span></span>
			                                    </label>
			                                </th>
				                            <th> 物料编码 </th>
				                            <th> 物料名称 </th>
				                            <th> 规格型号 </th>
				                            <th> 单位 </th>
				                            <th> 类别 </th>
				                            <th> 原产国 </th>
				                            <th> 品牌 </th>
				                            <!-- <th> 供应商 </th> -->
				                            <th> 版本 </th>
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
				    </div>
				    <div class="row">
						<div class="col-md-12">
					        	<div class="portlet light ">
					                <div class="portlet-body">
					                    <ul class="nav nav-tabs">
					                    	<li class="active bold">
					                            <a href="#" data-target="#tab_2_0" data-toggle="tab"> 基本信息 </a>
					                        </li>
					                        <shiro:hasAnyRoles  name="admin, customer">
					                        <li class="bold">
					                            <a href="#" data-target="#tab_2_1" data-toggle="tab"> 供应商 </a>
					                        </li>
					                        </shiro:hasAnyRoles>
					                        <shiro:hasAnyRoles  name="admin, supplier">
					                        <li class="bold">
					                            <a href="#" data-target="#tab_2_2" data-toggle="tab"> 采购商 </a>
					                        </li>
					                        </shiro:hasAnyRoles>
					                        <li class="bold">
					                            <a href="#" data-target="#tab_2_3"  data-toggle="tab"> 下级物料 </a>
					                        </li>
					                        <li class="bold">
					                            <a href="#" data-target="#tab_2_4"  data-toggle="tab"> 包装信息 </a>
					                        </li>
					                        <li class="bold">
					                            <a href="#" data-target="#tab_2_5"  data-toggle="tab"> 库存信息 </a>
					                        </li>
					                        <li class="bold">
					                            <a href="#" data-target="#tab_2_6"  data-toggle="tab"> 价格趋势 </a>
					                        </li>
					                        <li class="bold">
					                            <a href="#" data-target="#tab_2_7"  data-toggle="tab"> 附件 </a>
					                        </li>
					                    </ul>
					                    <div class="tab-content">
					                    	<div class="tab-pane fade active in" id="tab_2_0">
					                    		<div class="modal-body">
													<div class="row">
								                          <div class="col-md-4">
								                              <div class="form-group">
								                                  <label class="control-label col-md-5 bold">物料编码</label>
								                                  <label class="control-label col-md-7">
								                                      {{materiel.materielNum}}
								                                  </label>
								                                  
								                              </div>
								                          </div>
								                          <!--/span-->
								                          <div class="col-md-4">
								                              <div class="form-group">
								                                  <label class="control-label col-md-5 bold">助记码</label>
								                                  <label class="control-label col-md-7">
								                                      {{materiel.mnemonicCode}}
								                                  </label>
								                              </div>
								                          </div>
								                          <!--/span-->
								                          <div class="col-md-4">
								                              <div class="form-group">
								                                  <label class="control-label col-md-5 bold">物料名称</label>
								                                  <label class="control-label col-md-7">
								                                      {{materiel.materielName}}
								                                  </label>
								
								                              </div>
								                          </div>
								                      </div>
								                      <!--/row-->
								                      <!--/row-->
								                      <div class="row">
								                      		<div class="col-md-4">
								                              <div class="form-group">
								                                  <label class="control-label col-md-5 bold">规格型号</label>
								                                  <label class="control-label col-md-7">
								                                      {{materiel.specifications}}
								                                  </label>
								
								                              </div>
								                          </div>
								                      		<div class="col-md-4">
								                              <div class="form-group">
								                                  <label class="control-label col-md-5 bold">物料大类</label>
								                                  <label class="control-label col-md-7">
								                                      {{materiel.typeName}}
								                                  </label>
								
								                              </div>
								                          </div>
								                          <div class="col-md-4">
								                              <div class="form-group">
								                                  <label class="control-label col-md-5 bold">一级分类</label>
								                                  <label class="control-label col-md-7">
								                                      {{materiel.categoryName1}}
								                                  </label>
								
								                              </div>
								                          </div>
								                          
								                          <!--/span-->
								                      </div>
								                      <!--/row-->
								                      <div class="row">
								                      	<div class="col-md-4">
								                              <div class="form-group">
								                                  <label class="control-label col-md-5 bold">二级分类</label>
								                                  <label class="control-label col-md-7">
								                                      {{materiel.categoryName2}}
								                                  </label>
								
								                              </div>
								                          </div>
								                      	<div class="col-md-4">
								                              <div class="form-group">
								                                  <label class="control-label col-md-5 bold">三级分类</label>
								                                  <label class="control-label col-md-7">
								                                      {{materiel.categoryName3}}
								                                  </label>
								
								                              </div>
								                          </div>
								                          <div class="col-md-4">
								                              <div class="form-group">
								                                  <label class="control-label col-md-5 bold">物料属性</label>
								                                  <label class="control-label col-md-7">
								                                      {{materiel.materielAttributeName}}
								                                  </label>
								
								                              </div>
								                          </div>
								                          <!--/span-->
								                         
								                      </div>
								                      <div class="row">
								                           <div class="col-md-4">
								                              <div class="form-group">
								                                  <label class="control-label col-md-5 bold">单位</label>
								                                  <label class="control-label col-md-7">
								                                      {{materiel.unit}}
								                                  </label>
								
								                              </div>
								                          </div>
								                          <div class="col-md-4">
								                              <div class="form-group">
								                                  <label class="control-label col-md-5 bold">品牌</label>
								                                  <label class="control-label col-md-7">
								                                      {{materiel.brand}}
								                                  </label>
								
								                              </div>
								                          </div>
								                          <!--/span-->
								                          <div class="col-md-4">
								                              <div class="form-group">
								                                  <label class="control-label col-md-5 bold">原产国</label>
								                                  <label class="control-label col-md-7">
								                                      {{materiel.originCountry}}
								                                  </label>
								                              </div>
								                          </div>
								                          <!--/span-->
								                          <!-- <div class="col-md-4">
								                              <div class="form-group">
								                                  <label class="control-label col-md-5 bold">产地</label>
								                                  <label class="control-label col-md-7">
								                                       {{materiel.productionPlace}}
								                                  </label>
								
								                              </div>
								                          </div> -->
								                          <!--/span-->
								                          <!-- <div class="col-md-4">
								                              <div class="form-group">
								                                  <label class="control-label col-md-5 bold">库存单位</label>
								                                  <label class="control-label col-md-7">
								                                      {{materiel.stockUnit}}
																</label>
								                              </div>
								                          </div> -->
								                      </div>
								                      <!--/row-->
								                      <!--/row-->
								                       <!-- <div class="row">
								                       		<div class="col-md-4">
								                              <div class="form-group">
								                                  <label class="control-label col-md-5 bold">上级产品</label>
								                                  <label class="control-label col-md-7">
								                                      {{materiel.parentMateriel.materielName}}
								                                  </label>
								                              </div>
								                          </div>
								                          
								                          /span
								                      </div> -->
								                      <!--/row-->
								                       <div class="row">
								                          <div class="col-md-4">
								                              <div class="form-group">
								                                  <label class="control-label col-md-5 bold">长度</label>
								                                  <label class="control-label col-md-7">
								                                      {{materiel.length}}
								                                  </label>
								
								                              </div>
								                          </div>
								                          <!--/span-->
								                          <div class="col-md-4">
								                              <div class="form-group">
								                                  <label class="control-label col-md-5 bold">币种</label>
								                                  <label class="control-label col-md-7">
								                                      {{materiel.currency}}
								                                  </label>
								                              </div>
								                          </div>
								                          <div class="col-md-4">
								                              <div class="form-group">
								                                  <label class="control-label col-md-5 bold">宽度</label>
								                                  <label class="control-label col-md-7">
								                                      {{materiel.width}}
								                                  </label>
								
								                              </div>
								                          </div>
								                          <!--/span-->
								                      </div>
								                      <!--/row-->
								                      <!--/row-->
								                      <div class="row">
								                      		<!-- <div class="col-md-4">
								                              <div class="form-group">
								                                  <label class="control-label col-md-5 bold">单价</label>
								                                  <label class="control-label col-md-7">
								                                      {{materiel.unitPrice}}
								                                  </label>
								                              </div>
								                          </div> -->
								                          <div class="col-md-4">
								                              <div class="form-group">
								                                  <label class="control-label col-md-5 bold">高度</label>
								                                  <label class="control-label col-md-7">
								                                      {{materiel.height}}
								                                  </label>
								
								                              </div>
								                          </div>
								                          <div class="col-md-4">
								                              <div class="form-group">
								                                  <label class="control-label col-md-5 bold">单件体积</label>
								                                  <label class="control-label col-md-7">
								                                      {{materiel.volume}}
								                                  </label>
								
								                              </div>
								                          </div>
								                          <!--/span-->
								                          <div class="col-md-4">
								                              <div class="form-group">
								                                  <label class="control-label col-md-5 bold">单件重量</label>
								                                  <label class="control-label col-md-7">
								                                      {{materiel.weight}}
								                                  </label>
								
								                              </div>
								                          </div>
								                      </div>
								                      <!--/row-->
								                      <div class="row">
								                           
								                          <div class="col-md-4">
								                              <div class="form-group">
								                                  <label class="control-label col-md-5 bold">托盘规格</label>
								                                  <label class="control-label col-md-7">
								                                      {{materiel.palletSpecification}}
								                                  </label>
								
								                              </div>
								                          </div>
								                          <div class="col-md-4">
								                              <div class="form-group">
								                                  <label class="control-label col-md-5 bold">每托数量</label>
								                                  <label class="control-label col-md-7">
								                                      {{materiel.palletWeight}}
								                                  </label>
								
								                              </div>
								                          </div>
								                          <div class="col-md-4">
								                              <div class="form-group">
								                                  <label class="control-label col-md-5 bold">备案项号</label>
								                                  <label class="control-label col-md-7">
								                                      {{materiel.filingItemNo}}
								                                  </label>
								                              </div>
								                          </div>
								                         
								                          <!--/span-->
								                         
								                      </div>
								                      <!--/row-->
								                      <!--/row-->
								                      <div class="row">
								                      		
								                       <!--/span-->
								                          <div class="col-md-4">
								                              <div class="form-group">
								                                  <label class="control-label col-md-5 bold">海关监管条件</label>
								                                  <label class="control-label col-md-7">
								                                      {{materiel.customsSupervisionConditions}}
								                                  </label>
								                              </div>
								                          </div>
								                      		<div class="col-md-4">
								                              <div class="form-group">
								                                  <label class="control-label col-md-5 bold">保质期</label>
								                                  <label class="control-label col-md-7">
								                                      {{materiel.qualityDate}}
								                                  </label>
								                              </div>
								                          </div>
								                          <div class="col-md-4">
								                              <div class="form-group">
								                                  <label class="control-label col-md-5 bold">海关编码</label>
								                                  <label class="control-label col-md-7">
								                                      {{materiel.customsCode}}
								                                  </label>
								                              </div>
								                          </div>
								                          <!--/span-->
								                          <!-- <div class="col-md-4">
								                              <div class="form-group">
								                                  <label class="control-label col-md-5 bold">生产日期</label>
								                                  <label class="control-label col-md-7">
								                                      {{materiel.manufactureDate}}
								                                  </label>
								                              </div>
								                          </div> -->
								                          <!--/span-->
								                      </div>
								                      <!--/row-->
								                      <div class="row">
								                          
								                          
								                          <div class="col-md-4">
								                              <div class="form-group">
								                                  <label class="control-label col-md-5 bold">一般交付周期</label>
								                                  <label class="control-label col-md-7">
								                                      {{materiel.deliveryCycle}}（月）
								                                  </label>
								                              </div>
								                          </div>
								                          <!--/span-->
								                          <div class="col-md-4">
								                              <div class="form-group">
								                                  <label class="control-label col-md-5 bold">包含下级物料</label>
								                                  <label class="control-label col-md-7">
								                                        {{materiel.isBOM=="1"?'是':'否'}}
								                                  </label>
								                              </div>
								                          </div>
								                          <div class="col-md-4">
								                              <div class="form-group">
								                                  <label class="control-label col-md-5 bold">备注</label>
								                                  <label class="control-label col-md-7">
								                                      {{materiel.remark}}
								                                  </label>
								                              </div>
								                          </div>
								                          <!--/span-->
								                      </div>
								                      <!--/row-->
								                      <div class="row">
								                          <!--/span-->
								                          <div class="col-md-4">
								                              <div class="form-group">
								                                  <label class="control-label col-md-5 bold">版本</label>
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
					                        <div class="tab-pane fade" id="tab_2_1">
										            <div class="portlet-body">
										                <table class="table table-striped table-bordered table-hover table-checkable order-column" id="sample_1">
										                    <thead>
										                        <tr>
										                            <th style="width:150px">供应商</th>
							                                      	<th>供应商物料编号</th>
																	<th>物料名称</th>
																	<th>规格型号</th>
																	<th>单位</th>
																	<th>物料大类</th>
																	<th>一级分类</th>
																	<th>二级分类</th>
																	<th>指导单价</th>
																	<th>采购配额</th>
																	<th>最小起订量</th>
																	<th>备注</th>
										                        </tr>
										                    </thead>
										                    <tbody>
								                                  <tr ng-repeat="_supplyMateriel in supplyMateriel track by $index" >
											                          <td>
										                                <p class="form-control-static" > {{_supplyMateriel.supply.comName}} </p>
											                          </td>
											                           <td>
											                                <p class="form-control-static" > {{_supplyMateriel.supplyMaterielNum}} </p>
											                          </td>
											                          
											                          <td>
									                                      <p class="form-control-static" > {{_supplyMateriel.materielName}} </p>
								                                      </td>
								                                      <td>
									                                      <p class="form-control-static" > {{_supplyMateriel.specifications}} </p>
								                                      </td>
								                                      <td>
									                                      <p class="form-control-static" > {{_supplyMateriel.unit}} </p>
								                                      </td>
								                                      <td>
								                                      		<p class="form-control-static" > {{_supplyMateriel.typeName}} </p>
								                                      </td>
								                                      <td>
									                                      <p class="form-control-static" > {{_supplyMateriel.categoryName1}} </p>
								                                      </td>
								                                      <td>
									                                      <p class="form-control-static" > {{_supplyMateriel.categoryName2}} </p>
								                                      </td>
								                                      <td>
									                                      <p class="form-control-static" > {{_supplyMateriel.unitPriceGuide}} </p>
								                                      </td>
								                                      <td>
									                                      <p class="form-control-static" > {{_supplyMateriel.purchaseQuota}} </p>
								                                      </td>
								                                      <td>
									                                      <p class="form-control-static" > {{_supplyMateriel.moq}} </p>
								                                      </td>
								                                      <td>
									                                      <p class="form-control-static" > {{_supplyMateriel.remark}} </p>
								                                      </td>
											                          
								                                  </tr>
								                              </tbody>
										                </table>
										            </div>
										    </div>
					                        <div class="tab-pane fade" id="tab_2_2">
										            <div class="portlet-body">
										                <table class="table table-striped table-bordered table-hover table-checkable order-column" >
										                    <thead>
										                        <tr>
										                           <th style="width:200px">采购商</th>
							                                      <th>采购商物料编号</th>
							                                      <th>物料名称</th>
																	<th>规格型号</th>
																	<th>单位</th>
																	<th>物料大类</th>
																	<th>一级分类</th>
																	<th>二级分类</th>
																	<th>指导单价</th>
																	<th>采购配额</th>
																	<th>最小起订量</th>
																	<th>备注</th>
										                            
										                        </tr>
										                    </thead>
										                    <tbody>
								                                  <tr ng-repeat="_buyMateriel in buyMateriel track by $index" >
											                          <td>
											                                <p class="form-control-static" > {{_buyMateriel.buy.comName}} </p>
											                          </td>
											                           <td>
											                                <p class="form-control-static" > {{_buyMateriel.buyMaterielNum}} </p>
											                          </td>
								                                      <td>
									                                      <p class="form-control-static" > {{_buyMateriel.materielName}} </p>
								                                      </td>
								                                      <td>
									                                      <p class="form-control-static" > {{_buyMateriel.specifications}} </p>
								                                      </td>
								                                      <td>
									                                      <p class="form-control-static" > {{_buyMateriel.unit}} </p>
								                                      </td>
								                                      <td>
								                                      		<p class="form-control-static" > {{_buyMateriel.typeName}} </p>
								                                      </td>
								                                      <td>
									                                      <p class="form-control-static" > {{_buyMateriel.categoryName1}} </p>
								                                      </td>
								                                      <td>
									                                      <p class="form-control-static" > {{_buyMateriel.categoryName2}} </p>
								                                      </td>
								                                      <td>
									                                      <p class="form-control-static" > {{_buyMateriel.unitPriceGuide}} </p>
								                                      </td>
								                                      <td>
									                                      <p class="form-control-static" > {{_buyMateriel.purchaseQuota}} </p>
								                                      </td>
								                                      <td>
									                                      <p class="form-control-static" > {{_buyMateriel.moq}} </p>
								                                      </td>
								                                      <td>
									                                      <p class="form-control-static" > {{_buyMateriel.remark}} </p>
								                                      </td>
								                                  </tr>
								                              </tbody>
										                </table>
										            </div>
					                        </div>
					                        <div class="tab-pane fade" id="tab_2_7">
										            <div class="portlet-body">
					                          <table class="table table-striped table-bordered table-hover table-checkable order-column">
					                              <thead>
					                                  <tr>
					                                      <th>附件类型</th>
					                                      <th>描述</th>
					                                      <th>文件</th>
					                                      <th>备注</th>
					                                      <th>上传人</th>
					                                      <th>上传时间</th>
					                                  </tr>
					                              </thead>
					                              <tbody ng-repeat="_file in file track by $index">
					                                  <tr>
															<td>
																<p class="form-control-static">{{_file.fileType}}</p>
															</td>
															<td>
																<p class="form-control-static">{{_file.fileDescribe}}</p>
															</td>
															<td>
																<p class="form-control-static"><a href="javascript:;" ng-click="downloadFile(_file)">{{_file.file.substring(_file.file.indexOf("_")+1)}}</a></p>
															</td>
															<td>
																<p class="form-control-static">{{_file.remark}}</p>
															</td>
															<td>
																<p class="form-control-static"> {{_file.uploader}}</p>
															</td>
															<td>
																<p class="form-control-static"> {{_file.uploadDate}}</p>
															</td>
														</tr>
					                              </tbody>
					                          </table>
										            </div>
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
										                    	<tr ng-if="materiel!=null&&materiel.packageNum!=nullmateriel.packageNum!=''">
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
				 </div>
		 </div>
		  </div>
	  <div class="tab-pane" id="tab_15_2">
	  	<div class="row">
		  	<div class="col-md-12">
			    <div class="portlet light ">
			  		<ul class="nav nav-tabs">
						<li class="active bold"> <a data-target="#tab_1_1" data-toggle="tab">层级分类</a> </li>
						<li class="bold" ><a data-target="#tab_1_2" data-toggle="tab" ng-click="queryFunctionListByParent('1');">功能分类</a></li>
					</ul>
					<div class="tab-content" style="min-height: 300px;">
						<div class="tab-pane fade active in" id="tab_1_1">
							<div class="portlet light ">
		                        <div class="portlet-title">
		                            <div class="caption">
		                                <span class="caption-subject">物料大类</span>
		                            </div>
		                        </div>
		                        <div class="portlet-body">
		                        	<div class="row">
		  								<div class="col-md-8">
				                        	<div class="btn-group">
		                                     <div class="clearfix">
		                                          <div class="btn-group" data-toggle="buttons" id="tab_1">
		                                              <label ng-repeat="_Category in fristCategoryList track by $index" class="btn btn-default  btn-default-margin"  
		                                               ng-click="queryCategoryListByParent('second',_Category.categoryId)">
		                                                  <input type="radio" class="toggle" > {{_Category.categoryName}} 
		                                              </label> 
		                                          </div>
		                                      </div>
		                                 	</div>
		                                 </div>
		                                 <div class="col-md-4">
		                                 	<div class="actions">
												<label class="btn btn-transparent green btn-circle btn-sm" ng-click="addCategory('frist')">
								                                              <i class="fa fa-plus"></i> 添加</label>
												<label class="btn btn-transparent red btn-circle btn-sm" ng-click="deleteCategory('frist')">
							                                              <i class="fa fa-minus"></i> 删除</label>
											</div>
		                                 </div>
		                               </div>
		                        </div>
		                        <div class="portlet-title">
		                            <div class="caption">
		                                <span class="caption-subject">一级分类</span>
		                            </div>
		                        </div>
		                        <div class="portlet-body">
		                        	<div class="row">
		  								<div class="col-md-8">
				                        	<div class="btn-group">
		                                     <div class="clearfix">
		                                          <div class="btn-group" data-toggle="buttons"  id="tab_2">
		                                              <label ng-repeat="_Category in secondCategoryList track by $index" class="btn btn-default  btn-default-margin" id="tab_1_1Id" 
		                                              ng-click="queryCategoryListByParent('third',_Category.categoryId)">
		                                                  <input type="radio" class="toggle" > {{_Category.categoryName}} 
		                                              </label> 
		                                          </div>
		                                      </div>
		                                 	</div>
		                                 </div>
		                                 <div class="col-md-4">
		                                 	<div class="actions">
												<label class="btn btn-transparent green btn-circle btn-sm" ng-click="addCategory('second')" >
								                                              <i class="fa fa-plus"></i> 添加</label>
												<label class="btn btn-transparent red btn-circle btn-sm" ng-click="deleteCategory('second')">
							                                              <i class="fa fa-minus"></i> 删除</label>
											</div>
		                                 </div>
		                               </div>
		                        </div>
		                        <div class="portlet-title">
		                            <div class="caption">
		                                <span class="caption-subject">二级分类</span>
		                            </div>
		                        </div>
		                        <div class="portlet-body">
		                        	<div class="row">
		  								<div class="col-md-8">
				                        	<div class="btn-group">
		                                     <div class="clearfix">
		                                          <div class="btn-group" data-toggle="buttons" id="tab_3">
		                                              <label ng-repeat="_Category in thirdCategoryList track by $index" class="btn btn-default  btn-default-margin" id="tab_1_1Id" 
		                                              ng-click="queryCategoryListByParent('fourth',_Category.categoryId)">
		                                                  <input type="radio" class="toggle" > {{_Category.categoryName}} 
		                                              </label> 
		                                          </div>
		                                      </div>
		                                 	</div>
		                                 </div>
		                                 <div class="col-md-4">
		                                 	<div class="actions">
												<label class="btn btn-transparent green btn-circle btn-sm" ng-click="addCategory('third')" >
								                                              <i class="fa fa-plus"></i> 添加</label>
												<label class="btn btn-transparent red btn-circle btn-sm" ng-click="deleteCategory('third')">
							                                              <i class="fa fa-minus"></i> 删除</label>
											</div>
		                                 </div>
		                               </div>
		                        </div>
		                        <div class="portlet-title">
		                            <div class="caption">
		                                <span class="caption-subject">三级分类</span>
		                            </div>
		                        </div>
		                        <div class="portlet-body">
		                        	<div class="row">
		  								<div class="col-md-8">
				                        	<div class="btn-group">
		                                     <div class="clearfix">
		                                          <div class="btn-group" data-toggle="buttons"  id="tab_4">
		                                              <label ng-repeat="_Category in fourthCategoryList track by $index" class="btn btn-default  btn-default-margin" id="tab_1_1Id" >
		                                                  <input type="radio" class="toggle" > {{_Category.categoryName}} 
		                                              </label> 
		                                          </div>
		                                      </div>
		                                 	</div>
		                                 </div>
		                                 <div class="col-md-4">
		                                 	<div class="actions">
												<label class="btn btn-transparent green btn-circle btn-sm" ng-click="addCategory('fourth')" >
								                                              <i class="fa fa-plus"></i> 添加</label>
												<label class="btn btn-transparent red btn-circle btn-sm" ng-click="deleteCategory('fourth')">
							                                              <i class="fa fa-minus"></i> 删除</label>
											</div>
		                                 </div>
		                               </div>
		                        </div>
		                    </div>
						</div>
						<div class="tab-pane fade in" id="tab_1_2">
							<div class="portlet-body">
	                        	<div class="row">
	  								<div class="col-md-8">
			                        	<div class="btn-group">
	                                     <div class="clearfix">
	                                          <div class="btn-group" data-toggle="buttons" >
	                                              <label ng-repeat="_function in functionList track by $index" class="btn btn-default  btn-default-margin"
	                                              ng-click="selectFunction(_function.categoryId)">
	                                                  <input type="radio" class="toggle" > {{_function.categoryName}} 
	                                              </label> 
	                                          </div>
	                                      </div>
	                                 	</div>
	                                 </div>
	                                 <div class="col-md-4">
	                                 	<div class="actions">
											<label class="btn btn-transparent green btn-circle btn-sm" ng-click="addFunction()">
							                                              <i class="fa fa-plus"></i> 添加</label>
											<label class="btn btn-transparent red btn-circle btn-sm" ng-click="deleteFunction()">
						                                              <i class="fa fa-minus"></i> 删除</label>
										</div>
	                                 </div>
	                               </div>
	                        </div>
						</div>
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
                                  <label class="control-label col-md-5 bold">物料编码</label>
                                  <label class="control-label col-md-7">
                                      {{materiel.materielNum}}
                                  </label>
                                  
                              </div>
                          </div>
                          <!--/span-->
                          <div class="col-md-6">
                              <div class="form-group">
                                  <label class="control-label col-md-5 bold">助记码</label>
                                  <label class="control-label col-md-7">
                                      {{materiel.mnemonicCode}}
                                  </label>
                              </div>
                          </div>
                          <!--/span-->
                      </div>
                      <!--/row-->
                      <div class="row">
                          <!--/span-->
                          <div class="col-md-6">
                              <div class="form-group">
                                  <label class="control-label col-md-5 bold">物料名称</label>
                                  <label class="control-label col-md-7">
                                      {{materiel.materielName}}
                                  </label>

                              </div>
                          </div>
                           <div class="col-md-6">
                              <div class="form-group">
                                  <label class="control-label col-md-5 bold">规格型号</label>
                                  <label class="control-label col-md-7">
                                      {{materiel.specifications}}
                                  </label>

                              </div>
                          </div>
                          <!--/span-->
                      </div>
                      <!--/row-->
                      <div class="row">
                      		<div class="col-md-6">
                              <div class="form-group">
                                  <label class="control-label col-md-5 bold">物料大类</label>
                                  <label class="control-label col-md-7">
                                      {{materiel.typeName}}
                                  </label>

                              </div>
                          </div>
                          <div class="col-md-6">
                              <div class="form-group">
                                  <label class="control-label col-md-5 bold">一级分类</label>
                                  <label class="control-label col-md-7">
                                      {{materiel.categoryName1}}
                                  </label>

                              </div>
                          </div>
                      </div>
                      <!--/row-->
                      <div class="row">
                      	<div class="col-md-6">
                              <div class="form-group">
                                  <label class="control-label col-md-5 bold">二级分类</label>
                                  <label class="control-label col-md-7">
                                      {{materiel.categoryName2}}
                                  </label>

                              </div>
                          </div>
                          <div class="col-md-6">
                              <div class="form-group">
                                  <label class="control-label col-md-5 bold">三级分类</label>
                                  <label class="control-label col-md-7">
                                      {{materiel.categoryName3}}
                                  </label>

                              </div>
                          </div>
                      </div>
                      <!--/row-->
                      <div class="row">
                      <!--/span-->
                          <div class="col-md-6">
                              <div class="form-group">
                                  <label class="control-label col-md-5 bold">物料属性</label>
                                  <label class="control-label col-md-7">
                                      {{materiel.materielAttributeName}}
                                  </label>

                              </div>
                          </div>
                          <!--/span-->
                          <div class="col-md-6">
                              <div class="form-group">
                                  <label class="control-label col-md-5 bold">单位</label>
                                  <label class="control-label col-md-7">
                                      {{materiel.unit}}
                                  </label>

                              </div>
                          </div>
                         <!-- 
                          /span
                          <div class="col-md-6">
                              <div class="form-group">
                                  <label class="control-label col-md-5 bold">产地</label>
                                  <label class="control-label col-md-7">
                                       {{materiel.productionPlace}}
                                  </label>

                              </div>
                          </div>
                          /span -->
                      </div>
                      <div class="row">
                          <div class="col-md-6">
                              <div class="form-group">
                                  <label class="control-label col-md-5 bold">品牌</label>
                                  <label class="control-label col-md-7">
                                      {{materiel.brand}}
                                  </label>

                              </div>
                          </div>
                          <!--/span-->
                          <div class="col-md-6">
                              <div class="form-group">
                                  <label class="control-label col-md-5 bold">原产国</label>
                                  <label class="control-label col-md-7">
                                      {{materiel.originCountry}}
                                  </label>
                              </div>
                          </div>
                          <!--/span-->
                      </div>
                      <!--/row-->
                      <!-- <div class="row">
                          <div class="col-md-6">
                              <div class="form-group">
                                  <label class="control-label col-md-5 bold">库存单位</label>
                                  <label class="control-label col-md-7">
                                      {{materiel.stockUnit}}
								</label>
                              </div>
                          </div>
                          /span
                          <div class="col-md-6">
                              <div class="form-group">
                                  <label class="control-label col-md-5 bold">上级产品</label>
                                  <label class="control-label col-md-7">
                                      {{materiel.parentMateriel.materielName}}
                                  </label>
                              </div>
                          </div>
                          /span
                      </div> -->
                      <!--/row-->
                       
                      <!--/row-->
                       <div class="row">
                          <div class="col-md-6">
                              <div class="form-group">
                                  <label class="control-label col-md-5 bold">长度</label>
                                  <label class="control-label col-md-7">
                                      {{materiel.length}}
                                  </label>

                              </div>
                          </div>
                          <!--/span-->
                          <div class="col-md-6">
                              <div class="form-group">
                                  <label class="control-label col-md-5 bold">币种</label>
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
                                  <label class="control-label col-md-5 bold">宽度</label>
                                  <label class="control-label col-md-7">
                                      {{materiel.width}}
                                  </label>

                              </div>
                          </div>
                          <!--/span-->
                          <!-- <div class="col-md-6">
                              <div class="form-group">
                                  <label class="control-label col-md-5 bold">单价</label>
                                  <label class="control-label col-md-7">
                                      {{materiel.unitPrice}}
                                  </label>
                              </div>
                          </div> -->
                          <div class="col-md-6">
                              <div class="form-group">
                                  <label class="control-label col-md-5 bold">高度</label>
                                  <label class="control-label col-md-7">
                                      {{materiel.height}}
                                  </label>

                              </div>
                          </div>
                          <!--/span-->
                      </div>
                      <!--/row-->
                      <div class="row">
                          
                          <div class="col-md-6">
                              <div class="form-group">
                                  <label class="control-label col-md-5 bold">单件体积</label>
                                  <label class="control-label col-md-7">
                                      {{materiel.volume}}
                                  </label>

                              </div>
                          </div>
                          <div class="col-md-6">
                              <div class="form-group">
                                  <label class="control-label col-md-5 bold">单件重量</label>
                                  <label class="control-label col-md-7">
                                      {{materiel.weight}}
                                  </label>

                              </div>
                          </div>
                          <!--/span-->
                      </div>
                      <!--/row-->
                      <div class="row">
                          
                          <div class="col-md-6">
                              <div class="form-group">
                                  <label class="control-label col-md-5 bold">托盘规格</label>
                                  <label class="control-label col-md-7">
                                      {{materiel.palletSpecification}}
                                  </label>

                              </div>
                          </div>
                          <div class="col-md-6">
                              <div class="form-group">
                                  <label class="control-label col-md-5 bold">每托数量</label>
                                  <label class="control-label col-md-7">
                                      {{materiel.palletWeight}}
                                  </label>

                              </div>
                          </div>
                          <!--/span-->
                      </div>
                      <div class="row">
                          
                           <!--/span-->
                          <div class="col-md-6">
                              <div class="form-group">
                                  <label class="control-label col-md-5 bold">备案项号</label>
                                  <label class="control-label col-md-7">
                                      {{materiel.filingItemNo}}
                                  </label>
                              </div>
                          </div>
                          <!--/span-->
                          <div class="col-md-6">
                              <div class="form-group">
                                  <label class="control-label col-md-7 bold">海关监管条件</label>
                                  <label class="control-label col-md-5">
                                      {{materiel.customsSupervisionConditions}}
                                  </label>
                              </div>
                          </div>
                      </div>
                      <div class="row">
                          
                          <!--/span-->
                          
                          <!--/span-->
                          <div class="col-md-6">
                              <div class="form-group">
                                  <label class="control-label col-md-5 bold">保质期</label>
                                  <label class="control-label col-md-7">
                                      {{materiel.qualityDate}}
                                  </label>
                              </div>
                          </div>
                          <div class="col-md-6">
                              <div class="form-group">
                                  <label class="control-label col-md-5 bold">海关编码</label>
                                  <label class="control-label col-md-7">
                                         {{materiel.customsCode}}
                                  </label>
                              </div>
                          </div>
                      </div>
                      <!--/row-->
                      <!-- <div class="row">
                          
                          /span
                          <div class="col-md-6">
                              <div class="form-group">
                                  <label class="control-label col-md-5 bold">生产日期</label>
                                  <label class="control-label col-md-7">
                                      {{materiel.manufactureDate}}
                                  </label>
                              </div>
                          </div>
                          /span
                      </div> -->
                      <!--/row-->
                      <div class="row">
                      		
                          <div class="col-md-6">
                              <div class="form-group">
                                  <label class="control-label col-md-7 bold">一般交付周期</label>
                                  <label class="control-label col-md-5">
                                        {{materiel.deliveryCycle}}（月）
                                  </label>
                              </div>
                          </div>
                          <div class="col-md-6">
                              <div class="form-group">
                                  <label class="control-label col-md-7 bold">包含下级物料</label>
                                  <label class="control-label col-md-5">
                                        {{materiel.isBOM=="1"?'是':'否'}}
                                  </label>
                              </div>
                          </div>
                      </div>
                      <!--/row-->
                      <div class="row">
                          
                          <div class="col-md-6">
                              <div class="form-group">
                                  <label class="control-label col-md-5 bold">备注</label>
                                  <label class="control-label col-md-7">
                                      {{materiel.remark}}
                                  </label>
                              </div>
                          </div>
                          <div class="col-md-6">
                              <div class="form-group">
                                  <label class="control-label col-md-5 bold">版本</label>
                                  <label class="control-label col-md-7">
                                  	{{materiel.versionNO}}
                                  </label>
                              </div>
                          </div>
                       </div>
                      <!--/row-->
                      
                      <!--/row-->
				</div>
			</div>
		</div>
	</div>
<!-- 基本信息modal 结束 -->
<!-- 新增物料分类modal START -->
<div class="modal fade" id="addCategory" role="import" aria-hidden="true">
     <div class="modal-dialog" >
	    <div class="modal-content">
	 		<div class="modal-header">
	            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
	            <h4 class="modal-title" >新增物料分类</h4>
	        </div>
	        <div class="modal-body">
         		<div class="portlet-body form">
                    <!--  BEGIN FORM -->
                     <form class="form-horizontal" role="form">
                         <div class="form-body">
                              <input type="text"  ng-model="category.categoryName" class="form-control" >
                         </div>
                     </form>
                    <!--  END FORM -->
                </div>
	    	</div>
	    	<div class="modal-footer">
					<button type="button" data-dismiss="modal"
						class="btn dark btn-outline">取消</button>
					<button type="button" ng-click="addCategoryConfirm()" class="btn green">确定
						</button>
				</div>
    	</div>
	</div>
</div>
<!-- 新增物料分类modal END-->
<!-- 删除物料分类modal 开始 -->
	<div id="deleteCategory" class="modal fade" tabindex="-1"
		data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true"></button>
					<h4 class="modal-title">确认</h4>
				</div>
				<div class="modal-body">
					<p>是否删除已选物料分类?</p>
				</div>
				<div class="modal-footer">
					<button type="button" data-dismiss="modal"
						class="btn dark btn-outline">取消</button>
					<button type="button" ng-click="deleteCategoryConfirm()" class="btn green">确定
						</button>
				</div>
			</div>
		</div>
	</div>
<!-- 删除物料分类modal 结束 -->
<!-- 新增功能modal START -->
<div class="modal fade" id="addFunction" role="import" aria-hidden="true">
     <div class="modal-dialog" >
	    <div class="modal-content">
	 		<div class="modal-header">
	            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
	            <h4 class="modal-title" >新增功能分类</h4>
	        </div>
	        <div class="modal-body">
         		<div class="portlet-body form">
                    <!--  BEGIN FORM -->
                     <form class="form-horizontal" role="form">
                         <div class="form-body">
                              <input type="text"  ng-model="category.categoryName" class="form-control" >
                         </div>
                     </form>
                    <!--  END FORM -->
                </div>
	    	</div>
	    	<div class="modal-footer">
					<button type="button" data-dismiss="modal"
						class="btn dark btn-outline">取消</button>
					<button type="button" ng-click="addFunctionConfirm()" class="btn green">确定
						</button>
				</div>
    	</div>
	</div>
</div>
<!-- 新增功能modal END-->
<!-- 删除功能modal 开始 -->
	<div id="deleteFunction" class="modal fade" tabindex="-1"
		data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true"></button>
					<h4 class="modal-title">确认</h4>
				</div>
				<div class="modal-body">
					<p>是否删除已选功能分类?</p>
				</div>
				<div class="modal-footer">
					<button type="button" data-dismiss="modal"
						class="btn dark btn-outline">取消</button>
					<button type="button" ng-click="deleteFunctionConfirm()" class="btn green">确定
						</button>
				</div>
			</div>
		</div>
	</div>
<!-- 删除功能modal 结束 -->
<jsp:include  page="importMateriel.jsp"/>