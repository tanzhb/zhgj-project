<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"  %>
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
<!-- END PAGE HEADER-->
<!-- BEGIN MAIN CONTENT -->
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN EXAMPLE TABLE PORTLET-->
	 <div class="portlet bordered">
            <div class="portlet-body">
				<div class="portlet light "> 


		<ul class="nav nav-tabs" id="demandPlanUl">
			<li ng-hide="saveButton" class="dropdown pull-right tabdrop" >
								<button ng-hide="demandPlanAdd"
										class="btn green btn-sm btn-circle"
										ng-click="saveDemandPlan()">
										<i class="fa fa-check"></i> 发布
									</button>
									<button ng-show="demandPlanView"
										class="btn purple  btn-sm btn-circle"
										ng-click="editDemandPlanBasic()">
										<i class="fa fa-edit"></i> 编辑
									</button>
									<button ng-hide="demandPlanAdd"
										class="btn defualt  btn-sm btn-circle"
										ng-click="cancelDemandPlanBasic()">
										<i class="fa fa-mail-reply"></i> 取消
									</button>
                                
			</li>
			<li ng-show="materielButton" class="dropdown pull-right tabdrop" >
					<button class="btn blue btn-sm btn-circle"  ng-hide="showSaveBtn"  ng-click="saveAllDemandMateriel()">
                              <i class="fa fa-save"></i>保存
					</button>
					<button class="btn blue btn-sm btn-circle"  ng-show="showSaveBtn"  ng-click="editAllDemandMateriel()">
                              <i class="fa fa-save"></i>编辑
					</button>
                   <button class="btn blue btn-sm btn-circle"  ng-hide="showSaveBtn"  ng-click="addMateriel()">
                              <i class="fa fa-plus"></i>新增物料
					</button>
					<!-- <li>
						 <a href="javascript:;" data-target="#tab_1_2" data-toggle="tab" class="btn blue btn-outline" style="padding: 10px 15px;" ng-click="addMateriel()" >
                              <i class="fa fa-plus"></i>新增物料
                         </a>
					</li>   -->       
			</li>
			<li class="active bold"><a data-target="#tab_1_1"
				data-toggle="tab">基本信息</a></li>
			<li class="bold"><a data-target="#tab_1_2" data-toggle="tab">物料信息</a>
			</li>
		</ul>
		<div class="tab-content">
			<div class="tab-pane fade active in" id="tab_1_1">
				<!-- <div class="portlet bordered">
					portlet-body
					<div class="portlet-body">
						<div class="portlet light "> -->
							<div class="portlet-body form">
								<form id="demandPlanForm">
									<div class="form-body">
										<div class="alert alert-danger display-hide">
											<button class="close" data-close="alert"></button>
											请先输入正确数据！
										</div>
										<div class="row">
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold" for="demandPlanNum">需求计划号
														<span class="required"> * </span>
													</label>
													<div class="">
														<input type="text" class="form-control" id="demandPlanNum"
															name="demandPlanNum" ng-model="demandPlan.demandPlanNum"
															ng-hide="demandPlanAdd">
														<div class="form-control-focus"></div>
														<p class="control-label left" ng-show="demandPlanView">{{demandPlan.demandPlanNum}}</p>
													</div>
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold" for="buyComId">需求客户
														<span class="required"> * </span>
													</label>
													<div class="">
														<select class="form-control customer" selectpicker
															data-live-search="true" id="buyComId" name="buyComId"
															ng-model="demandPlan.buyComId" ng-hide="demandPlanAdd"
															data-size="8">
															<option value=""></option>
															<option ng-repeat="customer in customers"
																value="{{customer.comId}}">{{customer.comName}}</option>
														</select>
														<div class="form-control-focus"></div>
														<p class="control-label left" ng-show="demandPlanView">{{demandPlan.buyComName}}</p>
													</div>
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold" for="customerPlanNum">客户计划号</label>
													<div class="">
														<input type="text" class="form-control" id="customerPlanNum"
															ng-model="demandPlan.customerPlanNum" ng-hide="demandPlanAdd">
														<div class="form-control-focus"></div>
														<p class="control-label left" ng-show="demandPlanView">{{demandPlan.customerPlanNum}}</p>
													</div>
												</div>
											</div>
										</div>
										<!--/row-->
										<div class="row">
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold" for="source">需求来源</label>
													<div class="">
														<input type="text" class="form-control" id="source"
															ng-model="demandPlan.source" ng-hide="demandPlanAdd">
														<div class="form-control-focus"></div>
														<p class="control-label left" ng-show="demandPlanView">{{demandPlan.source}}</p>
													</div>
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold" for="customerPrincipal">客户负责人</label>
													<div class="">
														<input type="text" class="form-control" id="customerPrincipal"
															ng-model="demandPlan.customerPrincipal" ng-hide="demandPlanAdd">
														<div class="form-control-focus"></div>
														<p class="control-label left" ng-show="demandPlanView">{{demandPlan.customerPrincipal}}</p>
													</div>
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold" for="maker">制单人</label>
													<div class="">
														<input type="text" class="form-control" id="maker"
															ng-model="demandPlan.maker" ng-hide="demandPlanAdd">
														<div class="form-control-focus"></div>
														<p class="control-label left" ng-show="demandPlanView">{{demandPlan.maker}}</p>
													</div>
												</div>
											</div>
										</div>
										<!-- row -->
										<div class="row">
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold" for="description">需求描述</label>
													<div class="">
														<input type="text" class="form-control" id="description"
															ng-model="demandPlan.description" ng-hide="demandPlanAdd">
														<div class="form-control-focus"></div>
														<p class="control-label left" ng-show="demandPlanView">{{demandPlan.description}}</p>
													</div>
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold" for="releaseDate">制单日期
													</label>
													<div class="">
														<input type="text" set-date
															class="form-control date-picker" size="16"
															data-date-format="yyyy-mm-dd" data-date-viewmode="years"
															id="releaseDate" ng-model="demandPlan.releaseDate"
															ng-hide="demandPlanAdd" readonly="readonly">
														<div class="form-control-focus"></div>
														<p class="control-label left" ng-show="demandPlanView">{{demandPlan.releaseDate}}</p>
													</div>
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold" for="tel">备注</label>
													<div class="">
														<input type="text" class="form-control" id="remark"
															ng-model="demandPlan.remark" ng-hide="demandPlanAdd">
														<div class="form-control-focus"></div>
														<p class="control-label left" ng-show="demandPlanView">{{demandPlan.remark}}</p>
													</div>
												</div>
											</div>
										</div>
										<!-- row -->
										<div class="row">
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold" for="updateDate">更新日期
													</label>
													<div class="">
														<input type="text" set-date
															class="form-control date-picker" size="16"
															data-date-format="yyyy-mm-dd" data-date-viewmode="years"
															id="updateDate" ng-model="demandPlan.updateDate"
															ng-hide="demandPlanAdd" readonly="readonly">
														<div class="form-control-focus"></div>
														<p class="control-label left" ng-show="demandPlanView">{{demandPlan.updateDate}}</p>
													</div>
												</div>
											</div>
										</div>
										<!-- row -->
									</div>
								</form>
							</div>
				
			</div>
			<div class="tab-pane fade in" id="tab_1_2">
					<div class="" id="tab2_c">
                    		 <div class="portlet-body form">
                                    <div class="table-scrollable">
                                        <table class="table table-striped table-bordered table-advance table-hover">
                                            <thead>
                                                <tr>
                                                    <th>物料编号</th>
                                                    <th>物料名称</th>
                                                    <th>规格型号</th>
                                                    <th>单位</th>
                                                    <th width="10%">数量</th>
                                                    <th width="15%">交付日期</th>
                                                    <th>距离交付</th>
                                                    <th>交付地点</th>
                                                    <th style="min-width: 100px;">供应商</th>
                                                    <th style="width: 140px;"></th>
                                                </tr>
                                            </thead>
                                           <tbody ng-if="rootMateriels.length==0">
                                             	<tr>
                                                    <td colspan="10" align="center" >暂无数据</td>
                                                </tr>
                                            </tbody>
                                            <tbody>
                                                <tr  ng-repeat="materiel in rootMateriels track by $index"  repeat-done="repeatDone()" ng-mouseover="showOperation('contact',$index)" ng-mouseleave="hideOperation('contact',$index)">
                                                    <td>
                                                    	<span ng-show="demandPlanMaterielView{{$index}}">{{materiel.materielNum}}</span>
                                                    	<span ng-hide="demandPlanMaterielEdit{{$index}}"><a href="javascript:;" ng-click="addMateriel('single',$index)">{{materiel.materielNum}}</a></span>
                                                    </td>
                                                    <td>{{materiel.materielName}}</td>
                                                    <td>{{materiel.specifications}}</td>
                                                    <td>{{materiel.unit}}</td>
                                                    <td>
                                                    	<input type="text" ng-hide="demandPlanMaterielEdit{{$index}}" class="form-control" id="amount{{$index}}" ng-model="materiel.amount" value="">
                                                   	 	 <span class="help-block"></span>
                                                   	 	<label   ng-show="demandPlanMaterielView{{$index}}"  >{{materiel.amount}}</label>
                                                    </td>
                                                    <td>
	                                                    <div  ng-hide="demandPlanMaterielEdit{{$index}}" input-medium class="input-group date date-picker"
															 data-date-format="yyyy-mm-dd"
															data-date-viewmode="years">
															<input type="text" class="form-control" style="min-width: 110px;" readonly="" id="deliveryDate{{$index}}" ng-model="materiel.deliveryDate" name="deliveryDate"   
																ng-change="setAllDeliveryDate(materiel ,$index)"  > <span class="input-group-btn">
																<button class="btn default " type="button">
																	<i class="fa fa-calendar"></i>
																</button>
															</span>
														</div>
														<span class="help-block"></span>
														<label   ng-show="demandPlanMaterielView{{$index}}"  >{{materiel.deliveryDate}}</label>
                                                    </td>
                                                    <td>{{materiel.remainTime}}</td>
                                                    <td>
                                                    	<input  ng-hide="demandPlanMaterielEdit{{$index}}" id="deliveryAddress{{$index}}" type="text" class="form-control" ng-model="materiel.deliveryAddress" value=""
                                                    	ng-if="$first"    ng-change="setAllDeliveryAddress(materiel ,$index)" >
                                                    	<input  ng-hide="demandPlanMaterielEdit{{$index}}" id="deliveryAddress{{$index}}" type="text" class="form-control" ng-model="materiel.deliveryAddress" value=""
                                                    	ng-if="!$first"   >
                                                    	<span class="help-block"></span>
                                                    	<label   ng-show="demandPlanMaterielView{{$index}}"  >{{materiel.deliveryAddress}}</label>
                                                    </td>
                                                    <td>
	                                                    <select ng-hide="demandPlanMaterielEdit{{$index}}"  id="supplyMaterielSerial{{$index}}"   class="form-control" ng-model="materiel.supplyMaterielSerial"  ng-if="materiel.supplyMateriels.length!=0">
	                                                    	<option ng-repeat="m in materiel.supplyMateriels" value="{{m.serialNum}}"  >
	                                                    		{{m.supply.comName}}{{m.supply.supplyComId}}
	                                                    	</option>
	                                                    </select>
	                                                    <span  ng-if="materiel.supplyMateriels==null||materiel.supplyMateriels.length==0">无供应商</span>
	                                                    <label   ng-show="demandPlanMaterielView{{$index}}"  >{{materiel.supplyName}}</label>
                                                    </td>
                                                    <td style="width: 140px;min-width: 120px;">
                                                    	<span ng-if="materiel.orderSerial == null">
	                                                    	<span>
	                                                    		&nbsp;&nbsp;&nbsp;&nbsp;
		                                                    	<a ng-hide="demandPlanMaterielEdit{{$index}}" ng-click="saveDemandPlanMateriel(materiel,$index)">保存</a>
		                                                    	&nbsp;&nbsp;&nbsp;
		                                                    	<a ng-hide="demandPlanMaterielEdit{{$index}}" ng-click="cancelDemandPlanMateriel(materiel,$index)">撤销</a>
		                                                    </span>
		                                                    <span  ng-show="operation_c{{$index}}">
		                                                    	<a ng-show="demandPlanMaterielView{{$index}}"   ng-click="editDemandPlanMateriel(materiel)">变更</a>
		                                                    	&nbsp;&nbsp;&nbsp;
		                                                    	<a ng-show="demandPlanMaterielView{{$index}}"  ng-click="deleteDemandPlanMateriel(materiel)">删除</a>
	                                                    	</span>
                                                    	</span>
                                                    	<span ng-if="materiel.orderSerial != null" class="label label-sm label-info ng-scope">
                                                    		已加入销售订单
                                                    	</span>
                                                    </td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                          				</div>
							<!-- END SAMPLE TABLE PORTLET-->
						</div>
			</div>
		</div>
			 	</div>
					</div>
				</div> 
	</div>
</div>
<!-- END EXAMPLE TABLE PORTLET-->

<!-- <div class="row">
	<div class="col-md-12">
		<div class="portlet light" id="comViewPage">
			<div class="portlet-body" id="comViewContent">
				<ul class="nav nav-tabs">
					
				 	<li class="active">
                 		 <a data-target="#tab_1_1" data-toggle="tab">物料信息</a>
              		</li> 
              		<li>
						 <a href="javascript:;"  class="btn blue" style="padding: 10px 15px;"  ng-click="addMateriel()" >
                              <i class="fa fa-plus"></i>新增物料
                         </a>    
					</li>
					<li>
						 <a href="javascript:;" data-target="#tab_1_2" data-toggle="tab" class="btn blue btn-outline" style="padding: 10px 15px;" ng-click="addMateriel()" >
                              <i class="fa fa-plus"></i>新增物料
                         </a>
					</li>
				</ul>
				<div class="tab-content">
					<div class="tab-pane fade active in" id="tab_1_1">
                    	
                    </div>
				</div>
			</div>
		</div>
	</div>
</div> -->

<jsp:include page="selectMateriel.jsp"></jsp:include>

<!-- END MAIN CONTENT -->
<!-- BEGIN MAIN JS -->
 <script>
// FormRepeater.init();
</script> 
<!-- END MAIN JS -->
