<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" %>
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
<!-- <h3 class="page-title">检验
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
            <a ui-sref="stockInOutCheck">检验</a>
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
                            <div class="caption"><span ng-if="inOrOut.indexOf('in')>-1" >入库检验</span><span  ng-if="inOrOut.indexOf('out')>-1">出库检验</span>详情</div>
                            <div class="actions">
                                            <button   ui-sref="stockInOutCheck"  class="btn blue  btn-outline  btn-sm " >
                                            <i class="fa fa-undo"></i> 返回 </button>
                            </div>
                        </div>
                        <div class="portlet-body form">
                            <form  id="stockForm" class="form-horizontal"   >
								<div class="form-body">
								           <div class="row"><!-- row START -->
                                                             <div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="checkNum"> 检验单号 :</label>
                                                    <div class="col-md-8">
                                                          <p class="control-label left" >{{stockInOutCheck.checkNum}}</p> 
                                                    </div>
                                            </div>
										</div>
                                                            <div class="col-md-4">
                                                                <div class="form-group form-md-line-input">
                                                                <label class="col-md-4 control-label" for=""><span  ng-if="inOrOut.indexOf('in')>-1"  >收货单号</span><span   ng-if="inOrOut.indexOf('out')>-1" >发货单号</span> :</label>
                                                                    <div class="col-md-8">
                                                                        <p class="control-label left"   ng-if="inOrOut.indexOf('out')>-1"  >{{stockInOutCheck.deliverNum}}</p> 
                                                                         <p class="control-label left" ng-if="inOrOut.indexOf('in')>-1"  >{{stockInOutCheck.takeDeliverNum}}</p> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="col-md-4">
                                                                <div class="form-group form-md-line-input">
                                                                <label class="col-md-4 control-label" for=""><span  ng-if="inOrOut.indexOf('in')>-1"  > 采购订单号</span><span   ng-if="inOrOut.indexOf('out')>-1" > 销售订单号</span> :</label>
                                                                    <div class="col-md-8">
                                                                        <p class="control-label left"  ng-if="inOrOut.indexOf('out')>-1" >{{stockInOutCheck.relationSaleNum}}</p> 
                                                                        <p class="control-label left"  ng-if="inOrOut.indexOf('in')>-1" >{{stockInOutCheck.relationBuyNum}}</p>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div><!-- row END-->
                                                        <div class="row"><!-- row START -->
                                                             <div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="comName"> 供应商:</label>
                                                    <div class="col-md-8">
                                                          <p class="control-label left" >{{stockInOutCheck.supplyName}}</p>
                                                    </div>
                                            </div>
										</div>
                                                            <div class="col-md-4">
                                                                <div class="form-group form-md-line-input">
                                                                <label class="col-md-4 control-label" for="checkParty">检验方 :</label>
                                                                    <div class="col-md-8">
                                                                        <p class="control-label left" >{{stockInOutCheck.checkParty}}</p> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="col-md-4">
                                                                <div class="form-group form-md-line-input">
                                                                <label class="col-md-4 control-label" for="checkDate">检验日期 :</label>
                                                                    <div class="col-md-8">
                                                                       <p class="control-label left" >{{stockInOutCheck.checkDate | date:'yyyy-MM-dd'}}</p>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div><!-- row END-->
                                                        <div class="row"><!-- row START -->
                                                             <div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="checker"> 检验员 :</label>
                                                    <div class="col-md-8">
                                                         <p class="control-label left" >{{stockInOutCheck.checker}}</p> 
                                                    </div>
                                            </div>
										</div>
                                                            <div class="col-md-4">
                                                                <div class="form-group form-md-line-input">
                                                                <label class="col-md-4 control-label" for="contactNum">联系电话 :</label>
                                                                    <div class="col-md-8">
                                                                         <p class="control-label left" >{{stockInOutCheck.contactNum}}</p>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="col-md-4">
                                                                <div class="form-group form-md-line-input">
                                                                <label class="col-md-4 control-label" for="status">状态 :</label>
                                                                    <div class="col-md-8">
                                                                    <span class="label label-sm label-success"  ng-if="stockInOutCheck.status==0" >待检验</span>
                                                                    <span class="label label-sm label-success"  ng-if="stockInOutCheck.status==1" >待审批</span>
                                                                    <span class="label label-sm label-success"  ng-if="stockInOutCheck.status==2" >已检验</span>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div><!-- row END-->
								</div>
							</form>
         				</div>

  <div class="portlet-title"><!-- 物料信息 START -->
                            <div class="caption">物料信息</div>
                            <div class="actions">
                            </div>
                        </div>
                          <div class="portlet-body form">
                                    <div class="table-scrollable">
                                        <table class="table table-striped table-bordered table-advance table-hover"  >
                                            <thead>
                                                <tr >
                                                    <th rowspan="2" style="text-align: center;vertical-align: middle">物料编号</th>
                                                    <th  rowspan="2"  style="text-align: center;vertical-align: middle">物料名称</th>
                                                    <th   rowspan="2" style="text-align: center;vertical-align: middle">规格型号</th>
                                                    <th  rowspan="2" style="text-align: center;vertical-align: middle">单位</th>
                                                    <th    rowspan="2"  style="text-align: center;vertical-align: middle">批次号</th>
                                                     <th   rowspan="2"  style="text-align: center;vertical-align: middle">生产日期</th>
                                                    <th   colspan="3"  style="text-align: center">发货</th>
                                                     <th   colspan="3" style="text-align: center"><span ng-if="inOrOut.indexOf('in')>-1" >收货</span><span  ng-if="inOrOut.indexOf('out')>-1">出库</span></th>
                                                    <th  colspan="3" style="text-align: center">检验</th>
                                                    <th    rowspan="2"  style="text-align: center;vertical-align: middle">状态</th>
                                                </tr>
                                            
                                             <tr >
                                                 <td style="text-align: center">订单数量</td>
                                                  <td style="text-align: center">发货数量</td>
                                                  <td  style="text-align: center">备注</td>
                                                 <td  style="text-align: center"><span ng-if="inOrOut.indexOf('in')>-1" >实收数量</span><span  ng-if="inOrOut.indexOf('out')>-1">出库数量</span></td>
                                                  <td  style="text-align: center"><span ng-if="inOrOut.indexOf('in')>-1" >拒收数量</span><span  ng-if="inOrOut.indexOf('out')>-1">未出数量</span></td>
                                                  <td  style="text-align: center">备注</td>
                                                   <td  style="text-align: center">合格数量</td>
                                                  <td   style="text-align: center">不合格数量</td>
                                                   <td  style="text-align: center">备注</td>
                                                  
                                                </tr> 
                                                </thead>
                                            <tbody  ng-if="materials==null">
			                                             	<tr>
			                                                    <td colspan="16" align="center" >暂无数据</td>
			                                                </tr>
			                                </tbody>
                                            <tbody >
                                            <tr  ng-repeat="materiel in materials">
                                          <td >{{materiel.materielNum}}</td>
										<td  >{{materiel.materielName}}</td>
										<td  >{{materiel.specifications}}</td>
										<td  >{{materiel.unit}}</td>
										<td>
                                            {{materiel.batchNum}}
                                        </td>
										<td>
											{{materiel.manufactureDate}}
										</td>
										<td ng-if="inOrOut.indexOf('in')>-1"  >{{materiel.orderMateriel.amount}}</td><td ng-if="inOrOut.indexOf('out')>-1"  >{{materiel.amount}}</td>
										<td>
                                            {{materiel.deliverCount}}
										</td>
										<td>
                                            {{materiel.deliverRemark}}
										</td>
										<td ng-if="inOrOut.indexOf('in')>-1"  > {{materiel.acceptCount}}</td><td ng-if="inOrOut.indexOf('out')>-1"  >{{materiel.stockCount}}</td>
										
										<td ng-if="inOrOut.indexOf('in')>-1"  >{{materiel.refuseCount}}</td><td ng-if="inOrOut.indexOf('out')>-1"  >{{materiel.unstockCount}}</td>
										
										<td ng-if="inOrOut.indexOf('in')>-1"  > {{materiel.takeRemark}}</td><td ng-if="inOrOut.indexOf('out')>-1"  >{{materiel.stockRemark}}</td>
										<td>{{materiel.qualifiedCount}}</td>
										<td>{{materiel.unqualifiedCount}}</td>
										<td>{{materiel.checkRemark}}</td>
										<td><span class="label label-sm label-success"  ng-if="stockInOutCheck.status==0" >待检验</span>
                                                  <span class="label label-sm label-success"  ng-if="stockInOutCheck.status==1" >待审批</span>
                                                   <span class="label label-sm label-success"  ng-if="stockInOutCheck.status==2" >已检验</span></td>
                                                </tr>
                                                <tr ng-if="materials!=null"><td colspan="3">合计</td><td >{{materials.length}}</td><td></td><td></td><td></td><td   >{{stockInOutCheck.totalDeliverCount}}</td></tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div><!-- 物料信息 END-->
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