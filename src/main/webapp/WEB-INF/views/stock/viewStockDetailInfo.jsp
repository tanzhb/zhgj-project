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
<!-- <h3 class="page-title"> 库存信息
</h3> -->
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
            <a ui-sref="stock">库存信息</a>
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
                            <div class="caption">库存详情</div>
                            <div class="actions">
                                            <button   ui-sref="stock"  class="btn blue  btn-outline  btn-sm " >
                                            <i class="fa fa-undo"></i> 返回 </button>
                            </div>
                        </div>
                        <div class="portlet-body form">
                            <form  id="stockForm" class="form-horizontal"   >
								<div class="form-body">
								           <div class="row"><!-- row START -->
                                                             <div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="stockNum"> 库存编号 :</label>
                                                    <div class="col-md-8">
                                                          <p class="control-label left" >{{stock.stockNum}}</p> 
                                                    </div>
                                            </div>
										</div>
                                                            <div class="col-md-4">
                                                                <div class="form-group form-md-line-input">
                                                                <label class="col-md-4 control-label" for="materielNum">物料编号 :</label>
                                                                    <div class="col-md-8">
                                                                        <p class="control-label left" >{{stock.materielNum}}</p> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="col-md-4">
                                                                <div class="form-group form-md-line-input">
                                                                <label class="col-md-4 control-label" for="materielName">物料名称 :</label>
                                                                    <div class="col-md-8">
                                                                        <p class="control-label left" >{{stock.materielName}}</p> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div><!-- row END-->
                                                        <div class="row"><!-- row START -->
                                                             <div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="specifications"> 规格型号 :</label>
                                                    <div class="col-md-8">
                                                          <p class="control-label left" >{{stock.specifications}}</p> 
                                                    </div>
                                            </div>
										</div>
                                                            <div class="col-md-4">
                                                                <div class="form-group form-md-line-input">
                                                                <label class="col-md-4 control-label" for="maxStock">最高库存 :</label>
                                                                    <div class="col-md-8">
                                                                        <p class="control-label left" >{{stock.maxStock}}</p> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="col-md-4">
                                                                <div class="form-group form-md-line-input">
                                                                <label class="col-md-4 control-label" for="minStock">最低库存 :</label>
                                                                    <div class="col-md-8">
                                                                        <p class="control-label left" >{{stock.minStock}}</p> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div><!-- row END-->
                                                        <div class="row"><!-- row START -->
                                                             <div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="manageType"> 管理类型 :</label>
                                                    <div class="col-md-8">
                                                          <p class="control-label left" ng-if="stock.manageType=='1'" >自建库管理</p>
													<p class="control-label left" ng-if="stock.manageType=='2'" >代管库管理</p>
														<p class="control-label left"  ng-if="stock.manageType=='3'" >境外库管理</p> 
                                                    </div>
                                            </div>
										</div>
                                                            <div class="col-md-4">
                                                                <div class="form-group form-md-line-input">
                                                                <label class="col-md-4 control-label" for="materielOwner">物权方 :</label>
                                                                    <div class="col-md-8">
                                                                        <p class="control-label left" >{{stock.materielOwner}}</p> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="col-md-4">
                                                                <div class="form-group form-md-line-input">
                                                                <label class="col-md-4 control-label" for="serviceParty">服务方 :</label>
                                                                    <div class="col-md-8">
                                                                        <p class="control-label left" >{{stock.serviceParty}}</p> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div><!-- row END-->
                                                        <div class="row"><!-- row START -->
                                                             <div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="currentAmount"> 库存数量 :</label>
                                                    <div class="col-md-8">
                                                          <p class="control-label left" >{{stock.currentAmount}}</p> 
                                                    </div>
                                            </div>
										</div>
                                                            <div class="col-md-4">
                                                                <div class="form-group form-md-line-input">
                                                                <label class="col-md-4 control-label" for="preSale">预售数量 :</label>
                                                                    <div class="col-md-8">
                                                                        <p class="control-label left" >{{stock.preSaleAmount}}</p> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="col-md-4">
                                                                <div class="form-group form-md-line-input">
                                                                <label class="col-md-4 control-label" for="onRoad">在途数量 :</label>
                                                                    <div class="col-md-8">
                                                                        <p class="control-label left" >{{stock.canSaleAmount}}</p> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div><!-- row END-->
                                                        <div class="row"><!-- row START -->
                                                             <div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="countInAmount"> 累计入库数量 :</label>
                                                    <div class="col-md-8">
                                                          <p class="control-label left" >{{stock.countInAmount}}</p> 
                                                    </div>
                                            </div>
										</div>
                                                            <div class="col-md-4">
                                                                <div class="form-group form-md-line-input">
                                                                <label class="col-md-4 control-label" for="countOutAmount">累计出库数量 :</label>
                                                                    <div class="col-md-8">
                                                                        <p class="control-label left" >{{stock.countOutAmount}}</p> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="col-md-4">
                                                                <div class="form-group form-md-line-input">
                                                                <label class="col-md-4 control-label" for="lastInDate">最后入库日期 :</label>
                                                                    <div class="col-md-8">
                                                                        <p class="control-label left" >{{stock.lastInDate}}</p> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div><!-- row END-->
                                                        <div class="row"><!-- row START -->
                                                             <div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="lastOutDate"> 最后出库日期 :</label>
                                                    <div class="col-md-8">
                                                          <p class="control-label left" >{{stock.lastOutDate}}</p> 
                                                    </div>
                                            </div>
										</div>
                                                            <div class="col-md-4">
                                                                <div class="form-group form-md-line-input">
                                                                <label class="col-md-4 control-label" for="relationBuyNum">关联采购单号 :</label>
                                                                    <div class="col-md-8">
                                                                        <p class="control-label left" >{{stock.relationBuyNum}}</p> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="col-md-4">
                                                                <div class="form-group form-md-line-input">
                                                                <label class="col-md-4 control-label" for="relationSaleNum">关联销售单号 :</label>
                                                                    <div class="col-md-8">
                                                                        <p class="control-label left" >{{stock.relationSaleNum}}</p> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div><!-- row END-->
                                                        <div class="row"><!-- row START -->
                                                             <div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="belongWarehouseNum">所在仓库 :</label>
                                                    <div class="col-md-8">
                                                          <p class="control-label left" >{{stock.belongWarehouseNum}}</p> 
                                                    </div>
                                            </div>
										</div>
                                                            <div class="col-md-4">
                                                                <div class="form-group form-md-line-input">
                                                                <label class="col-md-4 control-label" for="stockCost">库存成本 :</label>
                                                                    <div class="col-md-8">
                                                                        <p class="control-label left" >{{stock.stockCost}}</p> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="col-md-4">
                                                                <div class="form-group form-md-line-input">
                                                                <label class="col-md-4 control-label" for="averrageWhAge">平均库龄 :</label>
                                                                    <div class="col-md-8">
                                                                        <p class="control-label left" >{{stock.averrageWhAge}}</p> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div><!-- row END-->
                                                        <div class="row"><!-- row START -->
                                                             <div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="riskGrade"> 风险等级 :</label>
                                                    <div class="col-md-8">
                                                          <p class="control-label left" >{{stock.riskGrade}}</p> 
                                                    </div>
                                            </div>
										</div>
                                                            <div class="col-md-4">
                                                                <div class="form-group form-md-line-input">
                                                                <label class="col-md-4 control-label" for="remark">备注 :</label>
                                                                    <div class="col-md-8">
                                                                        <p class="control-label left" >{{stock.remark}}</p> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="col-md-4">
                                                                <div class="form-group form-md-line-input">
                                                                <label class="col-md-4 control-label" for="status">状态 :</label>
                                                                    <div class="col-md-8">
                                                                        <p class="control-label left" >{{stock.status}}</p> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div><!-- row END-->
                                      
								</div>
							</form>
         				</div>

  <div class="portlet-title"><!-- 仓库信息 START -->
                            <div class="caption">仓库信息</div>
                            <div class="actions">
                            </div>
                        </div>
                          <div class="portlet-body form">
                                    <div class="table-scrollable">
                                        <table class="table table-bordered table-hover"  >
                                            <thead>
                                                <tr>
                                                    <th>仓库编号</th>
                                                    <th>仓库名称</th>
                                                    <th>仓库管理方</th>
                                                    <th>所在库位</th>
                                                    <th>库存数量</th>
                                                    <th>库存单位</th>
                                                    <th>累计入库数量</th>
                                                    <th>累计出库数量</th>
                                                    <th>备注</th>
                                                </tr>
                                            </thead>
                                            <tbody  ng-if="warehousePositions.length==0">
			                                             	<tr>
			                                                    <td colspan="29" align="center" >暂无数据</td>
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
                                </div><!-- 仓库信息 END-->
				
                    <div class="portlet-title"><!-- 批次信息START-->
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
                                </div><!-- 批次信息END-->
				
				
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