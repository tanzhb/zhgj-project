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
            <a ui-sref="demandPlan">需求计划</a>
        </li>
    </ul>

</div> -->
<!-- END PAGE HEADER-->
<!-- BEGIN MAIN CONTENT -->
<!-- <div class="row">
    <div class="col-md-12">
        BEGIN EXAMPLE TABLE PORTLET
        <div class="portlet bordered">
            <div class="portlet-body">
				<div class="portlet light">
				 		<div class="portlet-title">
				 			<div class="caption">基本信息</div>
                            <div class="actions">
                            </div>
                        </div>
                        <div class="portlet-body form">
                            <form  id="demandPlanForm" class="form-horizontal" >
								<div class="form-body">
									<div class="row">
										<div class="col-md-6">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="demandPlanNum"> 需求计划编号：</label>
                                                    <div class="col-md-7">
                                                         <p class="control-label left" >{{demandPlan.demandPlanNum}}</p>
                                                    </div>
                                            </div>
										</div>
										<div class="col-md-6">
											<div class="form-group form-md-line-input">
                                                   <label class="col-md-4 control-label" for="buyComId"> 客户名称：</label>
	                                              <div class="col-md-7">
                                                        <p class="control-label left" >{{demandPlan.buyComName}}</p>
	                                              </div>
                                            </div>
										</div>
									</div>
									/row
									<div class="row">
										<div class="col-md-6">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="legalPerson">包含物料：</label>
                                                    <div class="col-md-7">
                                                         <p class="control-label left" >{{rootMateriels.length}}</p>
                                                    </div>
                                            </div>
										</div>
										<div class="col-md-6">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="address">包含供应商：</label>
                                                    <div class="col-md-7">
                                                         <p class="control-label left">{{supplyCount==null?0:supplyCount}}</p>
                                                    </div>
                                            </div>
										</div>
									</div>
									/row
									<div class="row">
										<div class="col-md-6">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="releaseDate"> 发布日期：</label>
                                                    <div class="col-md-7">
                                                         <p class="control-label left" >{{demandPlan.releaseDate}}</p>
                                                    </div>
                                            </div>
										</div>
										<div class="col-md-6">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="tel">更新时间：</label>
                                                    <div class="col-md-7">
                                                         <p class="control-label left" >{{demandPlan.updateTime}}</p>
                                                    </div>
                                            </div>
										</div>
									</div>
									/row
									<div class="row">
										<div class="col-md-6">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="releaseDate"> 更新人：</label>
                                                    <div class="col-md-7">
                                                         <p class="control-label left" >{{demandPlan.updater}}</p>
                                                    </div>
                                            </div>
										</div>
										<div class="col-md-6">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="tel">备注：</label>
                                                    <div class="col-md-7">
                                                         <p class="control-label left" >{{demandPlan.remark}}</p>
                                                    </div>
                                            </div>
										</div>
									</div>
									/row
								</div>
							</form>
         				</div>
         				<div class="portlet-title">
				 			<div class="caption">高级筛选</div>
                            <div class="actions">
                            </div>
                        </div>
                        <div class="portlet-body form">
                            <form  id="demandPlanForm" >
								<div class="form-body">
									<div class="alert alert-danger display-hide">
                                                <button class="close" data-close="alert"></button>请先输入正确数据！</div>
									<div class="row">
										<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="demandPlanNum">一级分类</label>
                                                    <div class="">
                                                        <input type="text" class="form-control"  ng-model="search.firstCategory" ng-hide="demandPlanAdd" >
                                                        <div class="form-control-focus"> </div>
                                                    </div>
                                            </div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="description">二级分类</label>
                                                    <div class="">
                                                        <input type="text" class="form-control" ng-model="search.secondCategory" ng-hide="demandPlanAdd" >
                                                        <div class="form-control-focus"> </div>
                                                    </div>
                                            </div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="description">三级分类</label>
                                                    <div class="">
                                                        <input type="text" class="form-control"  ng-model="search.thirdCategory" ng-hide="demandPlanAdd" >
                                                        <div class="form-control-focus"> </div>
                                                    </div>
                                            </div>
										</div>
									</div>
									/row
									<div class="row">
										<div class="col-md-4">
											<div class="form-group">
                                                   <label class="control-label bold" for="buyComId">供应商</label>
	                                              <div class="">
	                                                   <select class="form-control customer" selectpicker data-live-search="true"  ng-model="search.supplyComId" ng-hide="demandPlanAdd"  data-size="8">
	                                                        <option value=""></option>
	                                                        <option  ng-repeat="supplier in suppliers" value="{{supplier.comId}}">{{supplier.comName}}</option>
	                                                   </select>
	                                                   <div class="form-control-focus"> </div>
	                                              </div>
                                            </div>
										</div>
									<div class="col-md-4">
										<div class="form-group">
											<label class="control-label bold" for="releaseDate">交付日期
											</label>
											<div class="">
												<div
													class="input-group input-large date-picker input-daterange"
													data-date-format="yyyy-mm-dd">
													<input type="text" class="form-control"
														ng-model="search.deliveryStartDate" name="from"> <span
														class="input-group-addon"> to </span> <input type="text"
														class="form-control" ng-model="search.deliveryEndDate" name="to">

												</div>
												<div class="form-control-focus"></div>
											</div>
										</div>
									</div>
									<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="tel">关键字</label>
                                                    <div class="">
                                                        <input type="text" class="form-control"  ng-model="search.searchKey" ng-hide="demandPlanAdd" >
                                                        <div class="form-control-focus"> </div>
                                                    </div>
                                            </div>
										</div>
									</div>
									row
									<div class="row">
										<div class="col-md-12">
											<div class="form-group" align="right">
                                                   <button   ng-hide="companyAdd" class="btn blue  btn-sm btn-circle" ng-click="searchDemandPlanMateriels()">
                              						<i class="fa fa-search"></i> 搜索  </button>
                                            </div>
										</div>
									</div>
									row
								</div>
							</form>
         				</div>
				  </div>
        	</div>
        END EXAMPLE TABLE PORTLET
    	</div>
	</div>
</div> -->

<div class="row">
	<div class="col-md-12">
		<div class="portlet light" id="comViewPage">
			<div class="portlet-body" id="comViewContent">
				<ul class="nav nav-tabs">
				 	<li class="bold active">
                 		 <a data-target="#tab_1_1" data-toggle="tab">基本信息</a>
              		</li> 
              		<li class="bold">
                 		 <a data-target="#tab_1_2" data-toggle="tab">物料信息</a>
              		</li>
					<li class="bold">
						<a data-target="#tab_1_3" data-toggle="tab">历史订单</a>
					</li>
					<!-- <li>
						 <a href="javascript:;"  class="btn blue btn-outline" style="padding: 10px 15px;" ng-click="addMateriel()" >
                              <i class="fa fa-plus"></i>新增物料
                         </a>
					</li> -->
					<li class="dropdown pull-right tabdrop">
			<button type="button" onclick="goBackPage()" class="btn defualt  btn-circle  btn-sm"><i class="fa fa-reply"></i>返回</button>
		</li>		
				</ul>
				<div class="tab-content">
					<div class="tab-pane fade active in" id="tab_1_1">
						<div class="">
									<div class="row">
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label bold" for="demandPlanNum"> 需求计划号：</label>
                                                    <div class="col-md-7">
                                                         <p class="control-label left" >{{demandPlan.demandPlanNum}}</p>
                                                    </div>
                                            </div>
										</div>
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                   <label class="col-md-4 control-label bold" for="buyComId"> 需求客户：</label>
	                                              <div class="col-md-7">
                                                        <p class="control-label left" >{{demandPlan.buyComName}}</p>
	                                              </div>
                                            </div>
										</div>
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                   <label class="col-md-4 control-label bold" for="customerPlanNum"> 客户计划号：</label>
	                                              <div class="col-md-7">
                                                        <p class="control-label left" >{{demandPlan.customerPlanNum}}</p>
	                                              </div>
                                            </div>
										</div>
									</div>
									<!--/row-->
									<div class="row">
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                   <label class="col-md-4 control-label bold" for="source"> 需求来源：</label>
	                                              <div class="col-md-7">
                                                        <p class="control-label left" >{{demandPlan.source}}</p>
	                                              </div>
                                            </div>
										</div>
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                   <label class="col-md-4 control-label bold" for="customerPrincipal"> 客户负责人：</label>
	                                              <div class="col-md-7">
                                                        <p class="control-label left" >{{demandPlan.customerPrincipal}}</p>
	                                              </div>
                                            </div>
										</div>
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                   <label class="col-md-4 control-label bold" for="description"> 需求描述：</label>
	                                              <div class="col-md-7">
                                                        <p class="control-label left" >{{demandPlan.description}}</p>
	                                              </div>
                                            </div>
										</div>
										</div>
										<!--/row-->
										<div class="row">
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                   <label class="col-md-4 control-label bold" for="maker"> 制单人：</label>
	                                              <div class="col-md-7">
                                                        <p class="control-label left" >{{demandPlan.maker}}</p>
	                                              </div>
                                            </div>
										</div>
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                   <label class="col-md-4 control-label bold" for="releaseDate"> 制单日期：</label>
	                                              <div class="col-md-7">
                                                        <p class="control-label left" >{{demandPlan.releaseDate}}</p>
	                                              </div>
                                            </div>
										</div>
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                   <label class="col-md-4 control-label bold" for="remark"> 备注：</label>
	                                              <div class="col-md-7">
                                                        <p class="control-label left" >{{demandPlan.remark}}</p>
	                                              </div>
                                            </div>
										</div>
										
										</div>
									<!--/row-->
									<div class="row">
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label bold" for="legalPerson">包含物料：</label>
                                                    <div class="col-md-7">
                                                         <p class="control-label left" >{{rootMateriels.length}}</p>
                                                    </div>
                                            </div>
										</div>
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label bold" for="address">包含供应商：</label>
                                                    <div class="col-md-7">
                                                         <p class="control-label left">{{supplyCount==null?0:supplyCount}}</p>
                                                    </div>
                                            </div>
										</div>
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label bold" for="releaseDate"> 发布日期：</label>
                                                    <div class="col-md-7">
                                                         <p class="control-label left" >{{demandPlan.releaseDate}}</p>
                                                    </div>
                                            </div>
										</div>
									</div>
									<!--/row-->
									<div class="row">
										
										<div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label bold" for="tel">更新日期：</label>
                                                    <div class="col-md-7">
                                                         <p class="control-label left" >{{demandPlan.updateDate}}</p>
                                                    </div>
                                            </div>
										</div>
									</div>
									<!--/row-->
								</div>
					</div>
					<div class="tab-pane fade in" id="tab_1_2">
                    	<div class="" id="tab2_c">
                    		 <div class="portlet-body form">
                    		 		<!-- <div class="dataTables_scrollHead" style="overflow: hidden; position: relative; border: 0px; width: 100%;">
	                    		 		<div class="dataTables_scrollHeadInner" style="box-sizing: content-box;">
		                    		 		<table class="table table-striped table-bordered table-hover order-column dataTable no-footer" role="grid" style="margin-left: 0px;">
			                    		 		<thead>
			                                            <tr role="row">
				                                           <th>物料编号</th>
		                                                    <th>物料名称</th>
		                                                    <th>规格型号</th>
		                                                    <th>单位</th>
		                                                    <th>数量</th>
		                                                    <th>交付日期</th>
		                                                    <th>距离交付</th>
		                                                    <th>交付地点</th>
		                                                    <th>供应商</th>
		                                                    <th style="width: 140px;"></th>
			                                            </tr>
			                                     </thead>
		                                     </table>
	                                     </div>
                                     </div> -->
                                    <!-- <div class="table-scrollable">
                                        <table class="table table-striped table-bordered table-advance table-hover" id="demandPlanMateriels">
                                            <thead>
                                                <tr>
                                                    <th width="1%" align="right">
                                                    	<label><div class="checker"><span><input type="checkbox" ng-model="materielAllChecked" ></span></div></label>
                                                    	<label class="mt-checkbox mt-checkbox-outline"  >
                                                            <input type="checkbox" ng-model="materielAllChecked" ng-click="checkedOrCancelAll()">
                                                            <span></span>
                                                        </label>
                                                    </th>
                                                    <th>物料编号</th>
                                                    <th>物料名称</th>
                                                    <th>规格型号</th>
                                                    <th>单位</th>
                                                    <th>数量</th>
                                                    <th>交付日期</th>
                                                    <th>距离交付</th>
                                                    <th>交付地点</th>
                                                    <th>供应商</th>
                                                    <th style="width: 140px;"></th>
                                                </tr>
                                            </thead>
                                           <tbody ng-if="rootMateriels.length==0">
                                             	<tr>
                                                    <td colspan="10" align="center" >暂无数据</td>
                                                </tr>
                                            </tbody>
                                            <tbody  style="position: relative; overflow: auto; height: 300px; width: 100%;">
                                                <tr ng-repeat="materiel in rootMateriels track by $index"  repeat-done="repeatDone()" ng-mouseover="showOperation('contact',$index)" ng-mouseleave="hideOperation('contact',$index)">
                                                    <th>
                                                    	<label><div class="checker"><span><input type="checkbox" ng-model="materielChecked"  value="1"></span></div></label>
                                                    	<label class="mt-checkbox mt-checkbox-outline" ng-if="materiel.orderSerial == null">
                                                            <input type="checkbox" ng-checked="materielAllChecked"  ng-model="materiel.aaaaaa" ng-click="aa()">
                                                            <input type="checkbox" ng-checked="materielAllChecked"  ng-model="materiel.materielChecked" ng-click="aa()">
                                                            <span></span>
                                                        </label>
                                                    </th>
                                                    <td><span class="help-block"></span>
                                                    	<span ng-show="demandPlanMaterielView{{$index}}">{{materiel.materielNum}}</span>
                                                    	<span ng-hide="demandPlanMaterielEdit{{$index}}"><a href="javascript:;" ng-click="addMateriel('single',$index)">{{materiel.materielNum}}</a></span>
                                                    </td>
                                                    <td><span class="help-block"></span><label>{{materiel.materielName}}</label></td>
                                                    <td><span class="help-block"></span><label>{{materiel.specifications}}</label></td>
                                                    <td><span class="help-block"></span><label>{{materiel.unit}}</label></td>
                                                    <td>
                                                    	<input type="text" ng-hide="demandPlanMaterielEdit{{$index}}" class="form-control  input-small" id="amount{{$index}}" ng-model="materiel.amount" value="">
                                                   	 	<span class="help-block"></span>
                                                   	 	<label   ng-show="demandPlanMaterielView{{$index}}"  >{{materiel.amount}}</label>
                                                    </td>
                                                    <td>
	                                                    <div  ng-hide="demandPlanMaterielEdit{{$index}}" class="input-group input-medium date date-picker"
															 data-date-format="yyyy-mm-dd"
															data-date-viewmode="years">
															<input type="text" class="form-control" readonly="" id="deliveryDate{{$index}}" ng-model="materiel.deliveryDate" name="deliveryDate"
																> <span class="input-group-btn">
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
                                                    	<input  ng-hide="demandPlanMaterielEdit{{$index}}" type="text" class="form-control" id="deliveryAddress{{$index}}" ng-model="materiel.deliveryAddress" value="">
                                                    	<span class="help-block"></span>
                                                    	<label   ng-show="demandPlanMaterielView{{$index}}"  >{{materiel.deliveryAddress}}</label>
                                                    </td>
                                                    <td>
                                                    	<select ng-hide="demandPlanMaterielEdit{{$index}}"  class="form-control" ng-model="materiel.supplyMaterielSerial" >
	                                                    	<option ng-repeat="m in materiel.supplyMateriels" value="{{m.serialNum}}"  >
	                                                    		{{m.supply.comName}}
	                                                    	</option>
	                                                    </select>
	                                                    <span class="help-block"></span>
	                                                    <label   ng-show="demandPlanMaterielView{{$index}}"  >{{materiel.supplyName}}</label>
                                                    </td>
                                                    <td style="width: 140px;">
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
                                                    	
                                                    </td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div> -->



<div class="">
									<div class="alert alert-danger display-hide">
                                                <button class="close" data-close="alert"></button>请先输入正确数据！</div>
									<div class="row">
										<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="demandPlanNum">一级分类</label>
                                                    <div class="">
                                                        <input type="text" class="form-control"  ng-model="search.firstCategory" ng-hide="demandPlanAdd" >
                                                        <div class="form-control-focus"> </div>
                                                    </div>
                                            </div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="description">二级分类</label>
                                                    <div class="">
                                                        <input type="text" class="form-control" ng-model="search.secondCategory" ng-hide="demandPlanAdd" >
                                                        <div class="form-control-focus"> </div>
                                                    </div>
                                            </div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="description">三级分类</label>
                                                    <div class="">
                                                        <input type="text" class="form-control"  ng-model="search.thirdCategory" ng-hide="demandPlanAdd" >
                                                        <div class="form-control-focus"> </div>
                                                    </div>
                                            </div>
										</div>
									</div>
									<!--/row-->
									<div class="row">
										<div class="col-md-4">
											<div class="form-group">
                                                   <label class="control-label bold" for="buyComId">供应商</label>
	                                              <div class="">
	                                                   <select class="form-control customer" selectpicker data-live-search="true"  ng-model="search.supplyComId" ng-hide="demandPlanAdd"  data-size="8">
	                                                        <option value=""></option>
	                                                        <option  ng-repeat="supplier in suppliers" value="{{supplier.comId}}">{{supplier.comName}}</option>
	                                                   </select>
	                                                   <div class="form-control-focus"> </div>
	                                              </div>
                                            </div>
										</div>
									<div class="col-md-4">
										<div class="form-group">
											<label class="control-label bold" for="releaseDate">交付日期
											</label>
											<div class="">
												<div
													class="input-group input-large date-picker input-daterange"
													data-date-format="yyyy-mm-dd">
													<input type="text" class="form-control"
														ng-model="search.deliveryStartDate" name="from"> <span
														class="input-group-addon"> to </span> <input type="text"
														class="form-control" ng-model="search.deliveryEndDate" name="to">

												</div>
												<div class="form-control-focus"></div>
											</div>
										</div>
									</div>
									<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="tel">关键字</label>
                                                    <div class="">
                                                        <input type="text" class="form-control"  ng-model="search.searchKey" ng-hide="demandPlanAdd" >
                                                        <div class="form-control-focus"> </div>
                                                    </div>
                                            </div>
										</div>
									</div>
									<!-- row -->
									<div class="row">
										<div class="col-md-12">
											<div class="form-group" align="right">
                                                   <button   ng-hide="companyAdd" class="btn blue  btn-sm btn-circle" ng-click="searchDemandPlanMateriels()">
                              						<i class="fa fa-search"></i> 搜索  </button>
                                            </div>
										</div>
									</div>
									<!-- row -->
								</div>



								<div class="dataTables_scroll">
									<div class="dataTables_scrollHead"
										style="overflow: hidden; position: relative; border: 0px; width: 100%;">
										<div class="dataTables_scrollHeadInner"
											style="box-sizing: content-box;padding-right: 17px;">
											<table
												class="table table-striped table-bordered table-hover order-column dataTable no-footer"
												role="grid" style="margin-left: 0px; ">
												<thead>
													<tr role="row">
														<th tabindex="0"
															aria-controls="sample_1" rowspan="1" colspan="1"
															style="width: 2%;">
															<label class="mt-checkbox mt-checkbox-single mt-checkbox-outline"  >
                                                            <input type="checkbox" ng-model="materielAllChecked" ng-click="checkedOrCancelAll()">
                                                            <span></span>
                                                        </label>
														</th>
														<th tabindex="0" aria-controls="sample_1"
															rowspan="1" colspan="1" style="width: 10%;">物料编号</th>
														<th tabindex="0" aria-controls="sample_1"
															rowspan="1" colspan="1" style="width: 15%;">物料名称</th>
														<th tabindex="0" aria-controls="sample_1"
															rowspan="1" colspan="1" style="width: 15%;">物料规格</th>
														<th tabindex="0" aria-controls="sample_1"
															rowspan="1" colspan="1" style="width: 5%;">单位</th>
														<th tabindex="0" aria-controls="sample_1"
															rowspan="1" colspan="1" style="width: 5%;">数量</th>
														<th tabindex="0" aria-controls="sample_1"
															rowspan="1" colspan="1" style="width: 10%;">交付日期</th>
														<th tabindex="0" aria-controls="sample_1"
															rowspan="1" colspan="1" style="width: 10%;">距离交付</th>
														<th tabindex="0" aria-controls="sample_1"
															rowspan="1" colspan="1" style="width: 13%;">交付地点</th>
														<th tabindex="0" aria-controls="sample_1"
															rowspan="1" colspan="1" style="width: 15%;">供应商</th>
													</tr>
												</thead>
											</table>
										</div>
									</div>
									<div class="dataTables_scrollBody"
										style="position: relative;overflow: scroll;overflow-x: hidden; height: 400px; width: 100%;">
										<table
											class="table table-striped table-bordered table-hover order-column dataTable no-footer"
											id="sample_1" role="grid" aria-describedby="sample_1_info" style="width: 100%; position: absolute; top: 0px; left: 0px;">
											<thead >
													<tr role="row" style="height: 0px;">
														<th tabindex="0"
															aria-controls="sample_1" rowspan="1" colspan="1"
															style="width: 2%;height: 0px; padding-top: 0px; padding-bottom: 0px; border-top-width: 0px; border-bottom-width: 0px;">
														<div class="dataTables_sizing"
															style="height: 0; overflow: hidden;">
															<label
																class="mt-checkbox mt-checkbox-single mt-checkbox-outline">
																<input type="checkbox" ng-model="materielAllChecked"
																ng-click="checkedOrCancelAll()"> <span></span>
															</label>
														</div>

														</th>
														<th tabindex="0" aria-controls="sample_1"
															rowspan="1" colspan="1" style="width: 10%;height: 0px; padding-top: 0px; padding-bottom: 0px; border-top-width: 0px; border-bottom-width: 0px;"><div class="dataTables_sizing" style="height:0;overflow:hidden;">物料编号</div></th>
														<th tabindex="0" aria-controls="sample_1"
															rowspan="1" colspan="1" style="width: 15%;height: 0px; padding-top: 0px; padding-bottom: 0px; border-top-width: 0px; border-bottom-width: 0px;"><div class="dataTables_sizing" style="height:0;overflow:hidden;">物料名称</div></th>
														<th tabindex="0" aria-controls="sample_1"
															rowspan="1" colspan="1" style="width: 15%;height: 0px; padding-top: 0px; padding-bottom: 0px; border-top-width: 0px; border-bottom-width: 0px;"><div class="dataTables_sizing" style="height:0;overflow:hidden;">物料规格</div></th>
														<th tabindex="0" aria-controls="sample_1"
															rowspan="1" colspan="1" style="width: 5%;height: 0px; padding-top: 0px; padding-bottom: 0px; border-top-width: 0px; border-bottom-width: 0px;"><div class="dataTables_sizing" style="height:0;overflow:hidden;">单位</div></th>
														<th tabindex="0" aria-controls="sample_1"
															rowspan="1" colspan="1" style="width: 5%;height: 0px; padding-top: 0px; padding-bottom: 0px; border-top-width: 0px; border-bottom-width: 0px;"><div class="dataTables_sizing" style="height:0;overflow:hidden;">数量</div></th>
														<th tabindex="0" aria-controls="sample_1"
															rowspan="1" colspan="1" style="width: 10%;height: 0px; padding-top: 0px; padding-bottom: 0px; border-top-width: 0px; border-bottom-width: 0px;"><div class="dataTables_sizing" style="height:0;overflow:hidden;">交付日期</div></th>
														<th tabindex="0" aria-controls="sample_1"
															rowspan="1" colspan="1" style="width: 10%;height: 0px; padding-top: 0px; padding-bottom: 0px; border-top-width: 0px; border-bottom-width: 0px;"><div class="dataTables_sizing" style="height:0;overflow:hidden;">距离交付</div></th>
														<th tabindex="0" aria-controls="sample_1"
															rowspan="1" colspan="1" style="width: 13%;height: 0px; padding-top: 0px; padding-bottom: 0px; border-top-width: 0px; border-bottom-width: 0px;"><div class="dataTables_sizing" style="height:0;overflow:hidden;">交付地点</div></th>
														<th tabindex="0" aria-controls="sample_1"
															rowspan="1" colspan="1" style="width: 15%;height: 0px; padding-top: 0px; padding-bottom: 0px; border-top-width: 0px; border-bottom-width: 0px;"><div class="dataTables_sizing" style="height:0;overflow:hidden;">供应商</div></th>
													</tr>
												</thead>

											<tbody>
											 <tr ng-repeat="materiel in rootMateriels track by $index"  repeat-done="repeatDone()" ng-mouseover="showOperation('contact',$index)" ng-mouseleave="hideOperation('contact',$index)">
                                                    <th width="2%" class="dt-body-center" >
                                                    	<!-- <label><div class="checker"><span><input type="checkbox" ng-model="materielChecked"  value="1"></span></div></label> -->
                                                    	<label class="mt-checkbox mt-checkbox-single mt-checkbox-outline" ng-if="materiel.orderSerial == null">
                                                          <!--   <input type="checkbox" ng-checked="materielAllChecked"  ng-model="materiel.aaaaaa" ng-click="aa()"> -->
                                                            <input type="checkbox" ng-checked="materielAllChecked"  ng-model="materiel.materielChecked" ng-click="aa()">
                                                            <span></span>
                                                        </label>
                                                    </th>
                                                    <td width="10%"><span class="help-block"></span>
                                                    	<span ng-show="demandPlanMaterielView{{$index}}">{{materiel.materielNum}}</span>
                                                    	<span ng-hide="demandPlanMaterielEdit{{$index}}"><a href="javascript:;" ng-click="addMateriel('single',$index)">{{materiel.materielNum}}</a></span>
                                                    </td>
                                                    <td width="15%"><span class="help-block"></span><label>{{materiel.materielName}}</label></td>
                                                    <td width="15%"><span class="help-block"></span><label>{{materiel.specifications}}</label></td>
                                                    <td width="5%"><span class="help-block"></span><label>{{materiel.unit}}</label></td>
                                                    <td width="5%">
                                                    	<input type="text" ng-hide="demandPlanMaterielEdit{{$index}}" class="form-control  input-small" id="amount{{$index}}" ng-model="materiel.amount" value="">
                                                   	 	<span class="help-block"></span>
                                                   	 	<label   ng-show="demandPlanMaterielView{{$index}}"  >{{materiel.amount}}</label>
                                                    </td>
                                                    <td width="10%">
	                                                    <div  ng-hide="demandPlanMaterielEdit{{$index}}" class="input-group input-medium date date-picker"
															 data-date-format="yyyy-mm-dd"
															data-date-viewmode="years">
															<input type="text" class="form-control" readonly="" id="deliveryDate{{$index}}" ng-model="materiel.deliveryDate" name="deliveryDate"
																> <span class="input-group-btn">
																<button class="btn default " type="button">
																	<i class="fa fa-calendar"></i>
																</button>
															</span>
														</div>
														<span class="help-block"></span>
														<label   ng-show="demandPlanMaterielView{{$index}}"  >{{materiel.deliveryDate}}</label>
                                                    </td>
                                                    <td>{{materiel.remainTime}}</td>
                                                    <td width="13%">
                                                    	<input  ng-hide="demandPlanMaterielEdit{{$index}}" type="text" class="form-control" id="deliveryAddress{{$index}}" ng-model="materiel.deliveryAddress" value="">
                                                    	<span class="help-block"></span>
                                                    	<label   ng-show="demandPlanMaterielView{{$index}}"  >{{materiel.deliveryAddress}}</label>
                                                    </td>
                                                    <td width="15%">
                                                    	<select ng-hide="demandPlanMaterielEdit{{$index}}"  class="form-control" ng-model="materiel.supplyMaterielSerial" >
	                                                    	<option ng-repeat="m in materiel.supplyMateriels" value="{{m.serialNum}}"  >
	                                                    		{{m.supply.comName}}
	                                                    	</option>
	                                                    </select>
	                                                    <span class="help-block"></span>
	                                                    <label   ng-show="demandPlanMaterielView{{$index}}"  >{{materiel.supplyName}}</label>
                                                    </td>
                                                    <!-- <td style="width: 140px;">
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
                                                    	
                                                    </td> -->
                                                </tr>
											</tbody>
										</table>
									</div>
								</div>















							</div>
                          	 <div>
                          	 	<div class="row" align="center">
                          	 		 <button   ng-hide="companyAdd" class="btn green  btn-sm btn-circle" ng-click="addToSaleOrder()">
                                            <!-- <i class="fa fa-check"></i> --> 加入销售单 </button>
                                            &nbsp;&nbsp;&nbsp;&nbsp;
                                	<button   ng-hide="companyAdd" class="btn defualt  btn-sm btn-circle" ng-click="cancel()">
                                            <!-- <i class="fa fa-mail-reply"></i> --> 返回 </button>
                          	 	</div>
                          	 </div>
							<!-- END SAMPLE TABLE PORTLET-->
						</div>
                    </div>
					<div class="tab-pane fade" id="tab_1_3">
						<div class="" id="tab2_c">
							<jsp:include page="selectSaleOrder.jsp"></jsp:include>
							<!-- END SAMPLE TABLE PORTLET-->
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<jsp:include page="selectMateriel.jsp"></jsp:include>

<!-- END MAIN CONTENT -->
<!-- BEGIN MAIN JS -->
 <script>
// FormRepeater.init();
</script> 
<!-- END MAIN JS -->
