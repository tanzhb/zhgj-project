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
<h3 class="page-title">检验
</h3>
<div class="page-bar">
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
                                                    <label class="col-md-4 control-label" for="comName"> <span  ng-if="inOrOut.indexOf('in')>-1"  >供应商</span><span   ng-if="inOrOut.indexOf('out')>-1" >采购商</span> :</label>
                                                    <div class="col-md-8">
                                                          <p class="control-label left" >{{stockInOutCheck.comName}}</p>
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
                                        <table class="table table-bordered table-hover"  >
                                            <thead>
                                                <tr >
                                                    <th  style="text-align: center;">物料编号</th>
                                                    <th  style="text-align: center;">物料名称</th>
                                                    <th  style="text-align: center">规格型号</th>
                                                    <th style="text-align: center">单位</th>
                                                    <th  style="text-align: center">批次号</th>
                                                     <th style="text-align: center">生产日期</th>
                                                    <th   colspan="3"  style="text-align: center">发货</th>
                                                    <th   colspan="3" style="text-align: center">收货</th>
                                                    <th  colspan="3" style="text-align: center">检验</th>
                                                    <th style="text-align: center">状态</th>
                                                </tr>
                                            
                                             <tr >
                                                <td style="border-top: none"></td>
                                                <td style="border-top: none"></td>
                                                <td style="border-top: none"></td>
                                                <td style="border-top: none"></td>
                                                <td style="border-top: none"></td>
                                                <td style="border-top: none"></td>
                                                 <td style="text-align: center">订单数量</td>
                                                  <td style="text-align: center">发货数量</td>
                                                  <td  style="text-align: center">备注</td>
                                                  <td  style="text-align: center">实收数量</td>
                                                  <td  style="text-align: center">拒收数量</td>
                                                  <td  style="text-align: center">备注</td>
                                                   <td  style="text-align: center">合格数量</td>
                                                  <td   style="text-align: center">不合格数量</td>
                                                   <td  style="text-align: center">备注</td>
                                                  <td style="border-top: none"></td>
                                                </tr> 
                                                </thead>
                                            <tbody  ng-if="materials==null">
			                                             	<tr>
			                                                    <td colspan="16" align="center" >暂无数据</td>
			                                                </tr>
			                                </tbody>
                                            <tbody ng-repeat="warehousePosition in warehousePositions">
                                                <tr >
                                                    <td  class="col-md-1">{{buycom.comNum}}</td>
                                                    <td class="col-md-1">{{buycom.buyComName}}</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div><!-- 物料信息 END-->
				
                 <!--    <div class="portlet-title">批次信息START
                            <div class="caption">批次信息</div>
                            <div class="actions">
                            </div>
                        </div>
                          <div class="portlet-body form">
                                    <div class="table-scrollable">
                                        <table class="table table-bordered table-hover">
                                            <thead>
                                                <tr>
                                                    <th>商品批次号</th>
                                                    <th>生效日期</th>
                                                     <th>供应商</th>
                                                    <th>入库日期</th>
                                                     <th>总数量</th>
                                                    <th>出库数量</th>
                                                     <th>结存数量</th>
                                                     <th>库龄</th>
                                                </tr>
                                            </thead>
                                            <tbody  ng-if="stocks.length==0">
			                                             	<tr>
			                                                    <td colspan="7" align="center" >暂无数据</td>
			                                                </tr>
			                                </tbody>
                                            <tbody ng-repeat="stock in stocks">
                                            
                                                <tr >
                                                    <td>{{stock.versionNO}}</td>
                                                    <td>{{stock.priceEffectiveDate | date:'yyyy-MM-dd' }}</td>
                                                    <td>{{stock.currency}}</td>
                                                    <td>{{stock.unitPrice}}</td>
                                                    <td>{{stock.rate}}</td>
                                                    <td>{{stock.inclusivePrice}}</td>
                                                    <td>--</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>批次信息END -->
				
				
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