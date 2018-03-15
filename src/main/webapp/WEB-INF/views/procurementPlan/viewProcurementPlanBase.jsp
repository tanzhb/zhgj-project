<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
}



</style>
<!-- 采购订单基本信息 START -->
<ul class="nav nav-tabs">
		<li class="active bold">
               		<a data-target="#tab_1_1" data-toggle="tab">基本信息</a>
           		</li>
           		<li class="bold" ><a data-target="#tab_1_5" data-toggle="tab">需求物料信息</a></li>
		<li class="bold" ng-hide="tab_1_1Hide"><a data-target="#tab_1_4" data-toggle="tab">采购清单物料信息</a></li>
		<li class="dropdown pull-right tabdrop">
			<button type="button" onclick="goBackPage()" class="btn defualt  btn-circle  btn-sm"><i class="fa fa-reply"></i>返回</button>
		</li>
	</ul>
<div class="tab-content" style="min-height: 300px;">
	<div class="tab-pane fade active in" id="tab_1_1">
         <div class="portlet-body form">
             <!-- BEGIN FORM-->
             <form action="#" id="form_sample_1"  >
                 <div class="form-body">
                     <div class="row">
                     <div class="col-md-4">
                         	<div class="form-group ">
                                 <label class="control-label col-md-5 bold">采购计划单号：</label>
                                 <div class="control-label col-md-7">
	                                 <p  > {{procurementPlan.procurementPlanNum}} </p>
                                 </div>
                             </div>
                         </div>
                         <div class="col-md-4">
                         	<div class="form-group ">
                                 <label class="control-label col-md-5 bold">销售订单号：</label>
                                 <div class="control-label col-md-7">
	                                 <p  > {{procurementPlan.saleOrder.orderNum}} </p>
                                 </div>
                             </div>
                         </div>
                         <div class="col-md-4">
                         	<div class="form-group ">
                                 <label class="control-label col-md-5 bold">销售下单日期：</label>
                                 <div class="control-label col-md-7">
	                                 <p  > {{procurementPlan.saleOrder.orderDate}} </p>
                                 </div>
                             </div>
                         </div>
                     </div>
                     <div class="row">
                         <div class="col-md-4">
                             <div class="form-group ">
                                 <label class="control-label col-md-5 bold">销售数量：</label>
                                 <div class="control-label col-md-7">
                                     <p  > {{procurementPlan.saleOrder.materielCount}} </p>
                                 </div>
                                 
                                 
                             </div>
                         </div>
                         <div class="col-md-4" >
                             <div class="form-group ">
                                 <label class="control-label col-md-5 bold">客户：</label>
                                 <div class="control-label col-md-7">
                                     <p  > {{procurementPlan.saleOrder.buyName}} </p>
                                 </div>
                                 
                             </div>
                         </div>
                         <div class="col-md-4">
                         	<div class="form-group ">
                                 <label class="control-label col-md-5 bold">采购下单日期：</label>
                                 <div class="control-label col-md-7">
                                     <p  >{{procurementPlan.buyDate}} </p>
                                 </div>
                                 
                             </div>
                         </div>
                     </div>
                     <div class="row">
                         <div class="col-md-4">
                             <div class="form-group ">
                                 <label class="control-label col-md-5 bold">采购数量：</label>
                                 <div class="control-label col-md-7">
                                     <p  > {{procurementPlan.buyCount}} </p>
                                 </div>
                                 
                             </div>
                         </div>
                         <div class="col-md-4">
                             <div class="form-group ">
                                 <label class="control-label col-md-5 bold">备注：</label>
                                 <div class="control-label col-md-7">
                                     <p  > {{procurementPlan.remark}} </p>
                                 </div>
                                 
                             </div>
                         </div>
                         <div class="col-md-4">
                             <div class="form-group ">
                                 <label class="control-label col-md-5 bold">状态：</label>
                                 <div class="control-label col-md-7">
                                 	<p ng-if="procurementPlan.status==1" style="color:#fcb95b"> 已完成 </p>
                                 	<p ng-if="procurementPlan.status!=1" style="color:green"> 待采购 </p>
                                 </div>
                                 
                             </div>
                         </div>
                         
                         
                     </div>
                 </div>
			</form>
            </div>   
	</div>
	<div class="tab-pane fade " id="tab_1_5">
		<!-- 订单物料 start-->
          <div class="portlet-body form">
			<div class="row">
				<div class="col-md-6 col-sm-6">
					<div class="dataTables_length" id="sample_5_length">
						<label>每页显示 <select name="sample_5_length"
							aria-controls="sample_5" ng-model="pageSize1" ng-change="createDispalyList1()"
							class="form-control input-sm input-xsmall input-inline">
							<option value="5">5</option>
							<option value="10">10</option>
							<option value="15">15</option>
							<option value="30">30</option>
							<option value="99999">All</option>
							</select> 条数据
						</label>
					</div>
				</div>
				<div class="col-md-6 col-sm-6">
					<div id="sample_5_filter" style="text-align: right;">
						<label>查询:<input type="search" ng-model="queryStr1"  ng-change="queryForPage1()"
							class="form-control input-sm input-small input-inline"
							placeholder="" aria-controls="sample_5"></label>
					</div>
				</div>
			</div>
			<div class="table-scrollable">
                         <table class="table table-bprocurementPlaned table-hover" id="form_sample_5" >
                             <thead>
								<tr>
								<th>商品编号</th>
								<th>物料名称</th>
								<th>规格型号</th>
								<th>单位</th>
								<th>单套用量</th>
								<th><span style="display:inline-block;width:100px;">需求数量</span></th>
								<th>齐套数量</th>
								<th>交付日期</th>
								<th>交付地址</th>
								<th>状态</th>
                                 </tr>
                             </thead>
                             <tbody>
                                 <tr ng-repeat="_procurementPlanMateriel in dispalyDemandMateriel track by $index" >
		                          <td>
		                                <p class="form-control-static" > {{_procurementPlanMateriel.materiel.materielNum}}{{_procurementPlanMateriel.materielNum}} </p>
		                          </td>
		                          <td>
	                                 	<p class="form-control-static" > {{_procurementPlanMateriel.materiel.materielName}} </p>
		                          </td>
		                           <td>
                                     		<p class="form-control-static" > {{_procurementPlanMateriel.materiel.specifications}} </p>
		                          </td>
		                          <td>
                                     		<p class="form-control-static" > {{_procurementPlanMateriel.materiel.unit}} </p>
		                          </td>
		                          <td>
                                     		<p class="form-control-static" > 1</p>
		                          </td>
		                          <td>  
                                     		<p class="form-control-static" > {{_procurementPlanMateriel.planCount}} </p>
		                          </td>
		                          <td>  
                                     		<p class="form-control-static" > {{_procurementPlanMateriel.completeCount==null?0:_procurementPlanMateriel.completeCount}} </p>
		                          </td>
		                          
		                          <td>  
                                     		<p class="form-control-static" > {{_procurementPlanMateriel.deliveryDate}} </p>
		                          </td>
		                          <td>  
                                     		<p class="form-control-static" > {{_procurementPlanMateriel.deliveryAddress}} </p>
		                          </td>
		                          <td>  
                                     		<p class="form-control-static"  ng-if="_procurementPlanMateriel.status==null">待采购 </p>
                                     		<p class="form-control-static"  ng-if="_procurementPlanMateriel.status==1"> 部分入库 </p>
                                     		<p class="form-control-static"  ng-if="_procurementPlanMateriel.status==2"> 已入库 </p>
		                          </td>
                                     
                                 </tr>
                                  <tr>
								<th></th>
								<th>合计</th>
								<th>{{totalDemandCount()}}</th>
								<th></th>
								<th></th>
								<th>{{totalDemandMaterielCount()}}</th>
								<th>{{totalEndCount()}}</th>
								<th></th>
								<th></th>
								<th></th>
                                 </tr>
                             </tbody>
                         </table>
            </div>
			<div class="row">
				<div class="col-md-5 col-sm-5">
					<div class="dataTables_info" id="sample_5_info" role="status"
						aria-live="polite">从 {{(pageIndex1-1)*pageSize1+1>filterDemandMateriel.length?filterDemandMateriel.length:(pageIndex1-1)*pageSize1+1}}
						到 {{pageIndex1*pageSize1>filterDemandMateriel.length?filterDemandMateriel.length:pageIndex1*pageSize1}} /共 {{filterDemandMateriel.length}} 条数据（从{{filterDemandMateriel.length}}条数据中筛选）</div>
				</div>
				<div class="col-md-7 col-sm-7">
					<div  style="text-align: right;" id="sample_5_paginate">
						<ul class="pagination" style="visibility: visible;">
							<li class="prev" ng-if="pageIndex1>1"><a href="#" ng-click="link2PreviousPage1()" title="前一页"><i
									class="fa fa-angle-left"></i></a></li>
							<li class="prev disabled" ng-if="1>=pageIndex1"><a href="#" title="前一页"><i
									class="fa fa-angle-left"></i></a></li>
							<li ng-if="pageIndex1-2>0"><a href="#" ng-click="link2ThisPage1(pageIndex1-2)">{{pageIndex1-2}}</a></li>
							<li ng-if="pageIndex1-1>0"><a href="#" ng-click="link2ThisPage1(pageIndex1-1)">{{pageIndex1-1}}</a></li>
							<li class="active"><a href="#">{{pageIndex1}}</a></li>
							<li ng-if="totalPage1>pageIndex1"><a href="#" ng-click="link2ThisPage1(pageIndex1+1)">{{pageIndex1+1}}</a></li>
							<li ng-if="totalPage1>pageIndex1+1"><a href="#" ng-click="link2ThisPage1(pageIndex1+2)">{{pageIndex1+2}}</a></li>
							<li class="next disabled" ng-if="pageIndex1>=totalPage1"><a href="#" ><i
									class="fa fa-angle-right"></i></a></li>
							<li class="next" ng-if="totalPage1>pageIndex1"><a href="#" ng-click="link2NextPage1()" title="后一页"><i
									class="fa fa-angle-right"></i></a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
         <!-- 订单物料 end-->
	</div>
	<div class="tab-pane fade " id="tab_1_4">
		<!-- 订单物料 start-->
          <div class="portlet-body form">
			<div class="row">
				<div class="col-md-6 col-sm-6">
					<div class="dataTables_length" id="sample_5_length">
						<label>每页显示 <select name="sample_5_length"
							aria-controls="sample_5" ng-model="pageSize" ng-change="createDispalyList()"
							class="form-control input-sm input-xsmall input-inline">
							<option value="5">5</option>
							<option value="10">10</option>
							<option value="15">15</option>
							<option value="30">30</option>
							<option value="99999">All</option>
							</select> 条数据
						</label>
					</div>
				</div>
				<div class="col-md-6 col-sm-6">
					<div id="sample_5_filter" style="text-align: right;">
						<label>查询:<input type="search" ng-model="queryStr"  ng-change="queryForPage()"
							class="form-control input-sm input-small input-inline"
							placeholder="" aria-controls="sample_5"></label>
					</div>
				</div>
			</div>
			<div class="table-scrollable">
                         <table class="table table-bprocurementPlaned table-hover" id="form_sample_5" >
                             <thead>
								<tr>
								<th>物料编号</th>
								<th>物料名称</th>
								<th>规格型号</th>
								<th>单位</th>
								<th>台套</th>
								<th>单套用量</th>
								<th><span style="display:inline-block;width:100px;">需求数量</span></th>
								<th><span style="display:inline-block;width:100px;">采购数量</span></th>
								<th>供应商</th>
								<th>交付日期</th>
								<th>交付地址</th>
								<th>状态</th>
                                 </tr>
                             </thead>
                             <tbody>
                                 <tr ng-repeat="_procurementPlanMateriel in dispalyProcurementPlanMateriel track by $index" >
		                          <td>
		                                <p class="form-control-static" > {{_procurementPlanMateriel.materiel.materielNum}} </p>
		                          </td>
		                          <td>
	                                 	<p class="form-control-static" > {{_procurementPlanMateriel.materiel.materielName}} </p>
		                          </td>
		                           <td>
                                     		<p class="form-control-static" > {{_procurementPlanMateriel.materiel.specifications}} </p>
		                          </td>
		                          <td>
                                     		<p class="form-control-static" > {{_procurementPlanMateriel.materiel.unit}} </p>
		                          </td>
		                          <td>  
                                     		<p class="form-control-static" > {{_procurementPlanMateriel.planCount}} </p>
		                          </td>
		                          <td>  
                                     		<p class="form-control-static" > {{_procurementPlanMateriel.singleDose}} </p>
		                          </td>
		                          <td>  
                                     		<p class="form-control-static" > {{_procurementPlanMateriel.planCount*_procurementPlanMateriel.singleDose}} </p>
		                          </td>
		                          <td>  
                                     		<p class="form-control-static" > {{_procurementPlanMateriel.buyCount}} </p>
		                          </td>
		                          <td>  
                                     		<p class="form-control-static" > {{_procurementPlanMateriel.supplyName}} </p>
		                          </td>
		                          
		                          <td>  
                                     		<p class="form-control-static" > {{_procurementPlanMateriel.deliveryDate}} </p>
		                          </td>
		                          <td>  
                                     		<p class="form-control-static" > {{_procurementPlanMateriel.deliveryAddress}} </p>
		                          </td>
                                     <td>  
                                     		<p class="form-control-static" >  </p>
		                          </td>
                                 </tr>
                                 <tr>
								<th></th>
								<th>合计</th>
								<th>{{totalCount()}}</th>
								<th></th>
								<th></th>
								<th></th>
								<th>{{totalMaterielPlanCountForView()}}</th>
								<th>{{totalMaterielCount()}}</th>
								<th></th>
								<th></th>
								<th></th>
								<th></th>
                                 </tr>
                             </tbody>
                         </table>
            </div>
			<div class="row">
				<div class="col-md-5 col-sm-5">
					<div class="dataTables_info" id="sample_5_info" role="status"
						aria-live="polite">从 {{(pageIndex-1)*pageSize+1>filterProcurementPlanMateriel.length?filterProcurementPlanMateriel.length:(pageIndex-1)*pageSize+1}}
						到 {{pageIndex*pageSize>filterProcurementPlanMateriel.length?filterProcurementPlanMateriel.length:pageIndex*pageSize}} /共 {{filterProcurementPlanMateriel.length}} 条数据（从{{procurementPlanMateriel.length}}条数据中筛选）</div>
				</div>
				<div class="col-md-7 col-sm-7">
					<div  style="text-align: right;" id="sample_5_paginate">
						<ul class="pagination" style="visibility: visible;">
							<li class="prev" ng-if="pageIndex>1"><a href="#" ng-click="link2PreviousPage()" title="前一页"><i
									class="fa fa-angle-left"></i></a></li>
							<li class="prev disabled" ng-if="1>=pageIndex"><a href="#" title="前一页"><i
									class="fa fa-angle-left"></i></a></li>
							<li ng-if="pageIndex-2>0"><a href="#" ng-click="link2ThisPage(pageIndex-2)">{{pageIndex-2}}</a></li>
							<li ng-if="pageIndex-1>0"><a href="#" ng-click="link2ThisPage(pageIndex-1)">{{pageIndex-1}}</a></li>
							<li class="active"><a href="#">{{pageIndex}}</a></li>
							<li ng-if="totalPage>pageIndex"><a href="#" ng-click="link2ThisPage(pageIndex+1)">{{pageIndex+1}}</a></li>
							<li ng-if="totalPage>pageIndex+1"><a href="#" ng-click="link2ThisPage(pageIndex+2)">{{pageIndex+2}}</a></li>
							<li class="next disabled" ng-if="pageIndex>=totalPage"><a href="#" ><i
									class="fa fa-angle-right"></i></a></li>
							<li class="next" ng-if="totalPage>pageIndex"><a href="#" ng-click="link2NextPage()" title="后一页"><i
									class="fa fa-angle-right"></i></a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
         <!-- 订单物料 end-->
	</div>
	
                   </div>
