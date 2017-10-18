<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- BEGIN PAGE HEADER-->
<style>
.left{
	float: left;
}

/* #deliveryMaterielTable thead tr th{
	text-align: center;
	vertical-align:middle;
} */
</style>
<!-- <h3 class="page-title"> 查看收货详情
</h3> -->
<!-- <div class="page-bar">
    <ul class="page-breadcrumb">
        <li>
            <i class="fa fa-home"></i>
            <a ui-sref="dashboard">首页</a>
            <i class="fa fa-angle-right"></i>
        </li>
        <li>
            <a ui-sref="datatablesmanaged">物流管理</a>
            <i class="fa fa-angle-right"></i>
        </li>
        <li>
            <a ui-sref="takeDelivery">收货计划</a>
            <i class="fa fa-angle-right"></i>
        </li>
        <li>
            <a ui-sref="takeDelivery">收货</a>
        </li>
    </ul>

</div> -->
<!-- END PAGE HEADER-->
<!-- BEGIN MAIN CONTENT -->
<div class="row">
    <div class="col-md-12">
    	<form  id="takeDeliveryForm" class="form-horizontal" >
        <!-- BEGIN EXAMPLE TABLE PORTLET-->
        <div class="portlet bordered">
            <div class="portlet-body">
				<div class="portlet light ">
				 	
						<jsp:include page="commonTakeDeliveryEdit.jsp"></jsp:include>
         				
         				<!-- <div class="portlet-title">
                        </div>
                        <div class="portlet-body form">
								<div class="form-body">
									<div class="row">
										<div class="col-md-4">
											<div class="form-group">
                                                    <label class="col-md-4 control-label" for="takeDeliverNum"> <span class="required"> * </span>收货单号:</label>
                                                    <div class="col-md-8">
                                                        <input type="text" class="form-control" id="takeDeliverNum" name="takeDeliverNum" ng-model="takeDeliver.takeDeliverNum" ng-hide="deliverAdd" >
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="deliverView">{{takeDeliver.takeDeliverNum}}</p>
                                                    </div>
                                            </div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
                                                    <label class="col-md-4 control-label" for="actualDate"><span class="required"> * </span>实际收货日期 :</label>
                                                    <div class="col-md-8">
                                                    	<input type="text" class="form-control date-picker"  data-date-format="yyyy-mm-dd" data-date-viewmode="years" id="actualDate"  name="actualDate" ng-model="takeDeliver.actualDate" readonly="readonly">
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="deliverView">{{takeDeliver.actualDate}}</p>
                                                    </div>
                                            </div>
										</div>
						
										/span
										<div class="col-md-4">
											<div class="form-group">
                                                    <label class="col-md-4 control-label" ><span class="required"> * </span>收货人:</label>
                                                    <div class="col-md-8">
                                                        <input type="text" class="form-control" id="taker" name="taker" ng-model="takeDeliver.taker" >
                                                         <div class="form-control-focus"></div>
                                                    </div>
                                            </div>
										</div>
										/span
									</div>
									/row
									<div class="row">
										<div class="col-md-4">
											<div class="form-group">
                                                    <label class="col-md-4 control-label" for="takeDeliverDate">备注:</label>
                                                    <div class="col-md-8">
                                                       <input type="text" class="form-control" id="takeRemark" name="takeRemark" ng-model="takeDeliver.takeRemark" >
                                                        <div class="form-control-focus"> </div>
                                                    </div>
                                            </div>
										</div>
										/span
									</div>
									/row
								</div>
						</div> -->
         			<!-- 收货信息END -->
         			<!-- 物料信息START -->
                      <!--   <div class="portlet-title">
                            <div class="caption">物料信息</div>
                            <div class="actions">
                            </div>
                        </div>
                        <div class="portlet-body">
						<div class="table-scrollable">
							<table id="deliveryMaterielTable"
								class="table table-striped table-bordered table-advance table-hover">
								<thead>
									<tr>
										<th  rowspan="2">物料编号</th>
										<th  rowspan="2">物料名称</th>
										<th  rowspan="2">规格型号</th>
										<th  rowspan="2">单位</th>
										<th  rowspan="2">批次号</th>
										<th  rowspan="2">生产日期</th>
										<th colspan="3" style="text-align: center;">发货</th>
										<th colspan="3"  style="text-align: center;">收货</th>
										<th rowspan="2">状态</th>
									</tr>
									<tr>
										<th>订单数量</th>
										<th>发货数量</th>
										<th>备注</th>
										<th>实收数量</th>
										<th>拒收数量</th>
										<th>备注</th>
									</tr>
								</thead>
								<tbody > 
									<tr ng-repeat="materiel in deliver.deliveryMateriels track by $index" >
										<td>{{materiel.orderMateriel.materiel.materielNum}}</td>
										<td>{{materiel.orderMateriel.materiel.materielName}}</td>
										<td>{{materiel.orderMateriel.materiel.specifications}}</td>
										<td>{{materiel.orderMateriel.materiel.unit}}</td>
										<td>
                                            {{materiel.batchNum}}
                                        </td>
										<td>
											{{materiel.manufactureDate}}
										</td>
										<td>{{materiel.orderMateriel.amount}}</td>
										<td>
                                            {{materiel.deliverCount}}
										</td>
										<td>
                                            {{materiel.deliverRemark}}
										</td>
										<td class="form-group">
                                                 <input type="text" class="form-control" id="acceptCount{{$index}}" name="acceptCount" data-delivercount="{{materiel.deliverCount}}"  ng-model="materiel.acceptCount" ng-hide="deliverAdd" >
                                                 <div class="form-control-focus"> </div>
										</td>
										<td><span class="help-block"></span>
											<span ng-if="materiel.deliverCount!=undefined && materiel.acceptCount!=undedined">{{materiel.deliverCount-materiel.acceptCount}}</span>
										</td>
										<td>
                                                 <input type="text" class="form-control" id="takeRemark{{$index}}" name="takeRemark"  ng-model="materiel.takeRemark" ng-hide="deliverAdd" >
                                                 <div class="form-control-focus"> </div>
										</td>
									</tr>
									<tr ng-if="deliver.deliveryMateriels==undefined||deliver.deliveryMateriels.length==0">
											<td colspan="22" align="center">暂无数据</td>
									</tr>
								</tbody>
							</table>
						</div>
					  </div>  -->
         			<!-- 物料信息END -->
         			<!-- 评论START -->
         			<div class="portlet-body">
         				<div class="table-scrollable">
							<div class="portlet box green">
	                             <div class="portlet-title">
	                                 <div class="caption">
	                                     <i class="fa fa-globe"></i>评论 </div>
	                             </div>
	                             <div class="portlet-body">
	                                 <table class="table table-striped table-bordered table-hover order-column" id="pinglun">
	                                     <thead>
	                                         <tr>
	                                             <th>评论人</th>
	                                             <th>评论时间</th>
	                                             <th>评论内容</th>
	                                         </tr>
	                                     </thead>
	                                     <tbody id = "comment_audit">	
	                                        </tbody>
	                                 </table>
	                             </div>
	                         </div>
						 </div>
					</div>	 
					<!-- 评论END -->
					<!-- 意见START -->
         			 <div class="portlet-body">
		         		<div class="row">
							<div class="form-group form-md-line-input">
								<label class="col-md-1 control-label" for="form_control_1">我的意见:</label>
								<div class="col-md-11">
									<textarea class="form-control" ng-model="content"
										id="content" name="content" rows="1"></textarea>
									<div class="form-control-focus"></div>
									<span class="help-block">输入我的意见</span>
								</div>
							</div>
							<input type="hidden" name="serialNum" id="serialNum" value="" />
							<input type="hidden" name="taskId" id="taskId" value="" />
						</div>
					</div>
					<!-- 意见END -->
         			 <div class="row" align="center">
         			 	 <button   class="btn blue  btn-sm btn-circle" ng-click="reApply()" >
                              		重新申请 </button>
                      	<button    class="btn defualt  btn-sm btn-circle" ng-click="apUnPass()" onclick="return false;">
                              		<i class="fa fa-mail-reply"></i>取消申请</button>
         			</div>
       			</div> 
    		</div>
		</div>
		</form>
	</div>
</div>