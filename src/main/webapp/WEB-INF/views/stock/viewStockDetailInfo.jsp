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
<div class="row" id="">
    <div class="col-md-12">
			<div class="portlet light ">
					
           		 <div class="portlet-body">
					<ul class="nav nav-tabs">
		<li class="active bold">
               		<a data-target="#tab_1_1" data-toggle="tab">库存信息</a>
           		</li>
		<li class="bold" ng-hide="tab_1_2Hide"><a data-target="#tab_1_2" data-toggle="tab">在库数量</a></li>
		<li class="bold" ng-hide="tab_1_3Hide"><a data-target="#tab_1_3" data-toggle="tab">入库记录</a></li>
		<li class="bold" ng-hide="tab_1_4Hide"><a data-target="#tab_1_4" data-toggle="tab">出库记录</a></li>			
		<li class="bold" ng-hide="tab_1_5Hide"><a data-target="#tab_1_5" data-toggle="tab">盘点记录</a></li>
	</ul>
<div class="tab-content" style="min-height: 300px;">
	<div class="tab-pane fade active in" id="tab_1_1">
          <div class="portlet-body form">
                            <form  id="stockForm" class="form-horizontal"   >
								<div class="form-body">
								           <div class="row"><!-- row START -->
                                                             <div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label col-md-3 bold" for="stockNum"> 库存编号 :</label>
                                                    <div class="control-label col-md-7">
                                                          <p class="form-control-static" >{{stock.stockNum}}</p> 
                                                    </div>
                                            </div>
										</div>
                                                            <div class="col-md-4">
                                                           <!--  <div class="form-group ">
                                 <label class="control-label col-md-3 bold">贸易类型：</label>
                                 <div class="control-label col-md-7" >
	                                 <p class="form-control-static"> {{buyOrder.tradeType}} </p>
                                 </div>
                             </div> -->
                                                                <div class="form-group">
                                                                <label class="control-label col-md-3 bold" for="materielNum">物料编号 :</label>
                                                                    <div class="control-label col-md-7">
                                                                        <p class="form-control-static" >{{stock.materielNum}}</p> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="col-md-4">
                                                                <div class="form-group">
                                                                <label class="control-label col-md-3 bold" for="materielName">物料名称 :</label>
                                                                    <div class="control-label col-md-7">
                                                                        <p class="form-control-static" >{{stock.materielName}}</p> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div><!-- row END-->
                                                        <div class="row"><!-- row START -->
                                                             <div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label col-md-3 bold" for="specifications"> 规格型号 :</label>
                                                    <div class="control-label col-md-7">
                                                          <p class="form-control-static" >{{stock.specifications}}</p> 
                                                    </div>
                                            </div>
										</div>
                                                            <div class="col-md-4">
                                                                <div class="form-group">
                                                                <label class="control-label col-md-3 bold" for="maxStock">最高库存 :</label>
                                                                    <div class="control-label col-md-7">
                                                                        <p class="form-control-static" >{{stock.maxStock}}</p> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="col-md-4">
                                                                <div class="form-group">
                                                                <label class="control-label col-md-3 bold" for="minStock">最低库存 :</label>
                                                                    <div class="control-label col-md-7">
                                                                        <p class="form-control-static" >{{stock.minStock}}</p> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div><!-- row END-->
                                                        <div class="row"><!-- row START -->
                                                             <div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label col-md-3 bold" for="manageType"> 管理类型 :</label>
                                                    <div class="control-label col-md-7">
                                                          <p class="form-control-static" ng-if="stock.manageType=='1'" >自建库管理</p>
													<p class="form-control-static" ng-if="stock.manageType=='2'" >代管库管理</p>
														<p class="form-control-static"  ng-if="stock.manageType=='3'" >境外库管理</p> 
                                                    </div>
                                            </div>
										</div>
                                                            <div class="col-md-4">
                                                                <div class="form-group">
                                                                <label class="control-label col-md-3 bold" for="materielOwner">物权方 :</label>
                                                                    <div class="control-label col-md-7">
                                                                        <p class="form-control-static" >{{stock.materielOwner}}</p> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="col-md-4">
                                                                <div class="form-group">
                                                                <label class="control-label col-md-3 bold" for="serviceParty">服务方 :</label>
                                                                    <div class="control-label col-md-7">
                                                                        <p class="form-control-static" >{{stock.serviceParty}}</p> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div><!-- row END-->
                                                        <div class="row"><!-- row START -->
                                                             <div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label col-md-3 bold" for="currentAmount"> 库存数量 :</label>
                                                    <div class="control-label col-md-7">
                                                         <!--  <p class="form-control-static" >{{stock.currentAmount}}</p>  -->
                                                         <p class="form-control-static"   ng-if="stock.countInAmount==null">暂无库存</p>
                                                         <p class="form-control-static"   ng-if="stock.countInAmount!=null&&stock.countOutAmount==null">{{stock.countInAmount}}</p>
                                                         <p class="form-control-static"   ng-if="stock.countInAmount!=null&&stock.countOutAmount!=null">{{stock.countInAmount-stock.countOutAmount}}</p>
                                                    </div>
                                            </div>
										</div>
                                                            <div class="col-md-4">
                                                                <div class="form-group">
                                                                <label class="control-label col-md-3 bold" for="preSale">预售数量 :</label>
                                                                    <div class="control-label col-md-7">
                                                                        <p class="form-control-static"   ng-if="stock.preSaleAmount!=null">{{stock.preSaleAmount}}</p> 
                                                                        <p class="form-control-static"   ng-if="stock.preSaleAmount==null">暂无预售</p> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="col-md-4">
                                                                <div class="form-group">
                                                                <label class="control-label col-md-3 bold" for="onRoad">在途数量 :</label>
                                                                    <div class="control-label col-md-7">
                                                                    <p class="form-control-static"   ng-if="stock.onRoadAmount!=null">{{stock.canSaleAmount}}</p> 
                                                                        <p class="form-control-static"   ng-if="stock.onRoadAmount==null">暂无在途</p>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div><!-- row END-->
                                                        <div class="row"><!-- row START -->
                                                             <div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label col-md-3 bold" for="countInAmount"> 累计入库数量 :</label>
                                                    <div class="control-label col-md-7">
                                                          <p class="form-control-static"   ng-if="stock.countInAmount!=null">{{stock.countInAmount}}</p> 
                                                          <p class="form-control-static"   ng-if="stock.countInAmount==null">暂无入库</p>
                                                    </div>
                                            </div>
										</div>
                                                            <div class="col-md-4">
                                                                <div class="form-group">
                                                                <label class="control-label col-md-3 bold" for="countOutAmount">累计出库数量 :</label>
                                                                    <div class="control-label col-md-7">
                                                           <p class="form-control-static"   ng-if="stock.countOutAmount!=null">{{stock.countOutAmount}}</p> 
                                                          <p class="form-control-static"   ng-if="stock.countOutAmount==null">暂无入库</p>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="col-md-4">
                                                                <div class="form-group">
                                                                <label class="control-label col-md-3 bold" for="lastInDate">最后入库日期 :</label>
                                                                    <div class="control-label col-md-7">
                                                           <p class="form-control-static"   ng-if="stock.lastInDate!=null">{{stock.lastInDate}}</p> 
                                                          <p class="form-control-static"   ng-if="stock.lastInDate==null">暂无入库日期</p>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div><!-- row END-->
                                                        <div class="row"><!-- row START -->
                                                             <div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label col-md-3 bold" for="lastOutDate"> 最后出库日期 :</label>
                                                    <div class="control-label col-md-7">
                                                          <p class="form-control-static"   ng-if="stock.lastOutDate!=null">{{stock.lastOutDate}}</p> 
                                                          <p class="form-control-static"   ng-if="stock.lastOutDate==null">暂无入库日期</p>
                                                    </div>
                                            </div>
										</div>
                                                            <div class="col-md-4">
                                                                <div class="form-group">
                                                                <label class="control-label col-md-3 bold" for="relationBuyNum">关联采购单号 :</label>
                                                                    <div class="control-label col-md-7">
                                                                        <p class="form-control-static" >{{stock.relationBuyNum}}</p> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="col-md-4">
                                                                <div class="form-group">
                                                                <label class="control-label col-md-3 bold" for="relationSaleNum">关联销售单号 :</label>
                                                                    <div class="control-label col-md-7">
                                                                        <p class="form-control-static" >{{stock.relationSaleNum}}</p> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div><!-- row END-->
                                                        <div class="row"><!-- row START -->
                                                             <div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label col-md-3 bold" for="belongWarehouseNum">所在仓库 :</label>
                                                    <div class="control-label col-md-7">
                                                          <p class="form-control-static" >{{stock.belongWarehouseNum}}</p> 
                                                    </div>
                                            </div>
										</div>
                                                            <div class="col-md-4">
                                                                <div class="form-group">
                                                                <label class="control-label col-md-3 bold" for="stockCost">库存成本 :</label>
                                                                    <div class="control-label col-md-7">
                                                                       <!--  <p class="form-control-static" >{{stock.stockCost}}</p>  -->
                                                                       <p class="form-control-static" >---</p>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="col-md-4">
                                                                <div class="form-group">
                                                                <label class="control-label col-md-3 bold" for="averrageWhAge">平均库龄 :</label>
                                                                    <div class="control-label col-md-7">
                                                                        <!-- <p class="form-control-static" >{{stock.averrageWhAge}}</p> -->
                                                                         <p class="form-control-static" >---</p> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div><!-- row END-->
                                                        <div class="row"><!-- row START -->
                                                             <div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label col-md-3 bold" for="riskGrade"> 风险等级 :</label>
                                                    <div class="control-label col-md-7">
                                                          <!-- <p class="form-control-static" >{{stock.riskGrade}}</p>  -->
                                                          <p class="form-control-static" >---</p>
                                                    </div>
                                            </div>
										</div>
                                                            <div class="col-md-4">
                                                                <div class="form-group">
                                                                <label class="control-label col-md-3 bold" for="remark">备注 :</label>
                                                                    <div class="control-label col-md-7">
                                                                        <p class="form-control-static" >{{stock.remark}}</p> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="col-md-4">
                                                                <div class="form-group">
                                                                <label class="control-label col-md-3 bold" for="status">状态 :</label>
                                                                    <div class="control-label col-md-7">
                                                                        <!-- <p class="form-control-static" >{{stock.status}}</p>  -->
                                                                        <p class="form-control-static" >---</p>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div><!-- row END-->
                                      
								</div>
							</form>
         				</div>
	</div>
	
	<div class="tab-pane" id="tab_1_2">
	<!-- 在库数量start-->
      <div class="portlet-body"  >
				<table
					class="table table-striped table-bordered table-hover "
					id="sample_inm">
					<thead>
						<tr>
						
							<td >物料批次号</td>
                              <td >批次类型</td>
                               <td  >入库单号</td>
                                <td  >供应方</td>
                                <td  >入库日期</td>
                                <td>仓库</td>
                                 <td  >总数量</td>
                                <td  >出库数量</td>
                                  <td >结存数量</td>
                                  <td  >库龄</td>
                                  <td  >备注</td>
						</tr>
					</thead>
					
					<tbody>
					</tbody>
					
				</table>
			</div>
         <!-- 在库数量 end-->
	</div>
	<div class="tab-pane" id="tab_1_3">
		<!-- 入库记录 start-->
       <div class="portlet-body"  >
				<table
					class="table table-striped table-bordered table-hover "
					id="sample_stockinview">
					<thead>
					<tr>
							<th>入库明细号</th>
                            <th> 入库类型</th>
                            <th> 关联单据</th>
                            <th>商品采购批次号 </th>
                            <th>入库日期</th>
                            <th>入库数量</th>
                            <th>仓库 </th>
                             <th>库位 </th>
                            <th>备注</th>
						</tr>
						
					</thead>
					
					<tbody>
					</tbody>
					<tfoot></tfoot>
				</table>
			</div>
         <!-- 入库记录 end-->
	</div>
	<div class="tab-pane" id="tab_1_4">
		<!--出库记录start-->
 <div class="portlet-body"  >
				<table
					class="table table-striped table-bordered table-hover "
					id="sample_stockoutview">
					<thead>
					<tr>
							<th  style="text-align: center">出库明细号</th>
                            <th style="text-align: center"> 出库类型</th>
                            <th style="text-align: center"> 关联单据</th>
                            <th  style="text-align: center">商品采购批次号 </th>
                            <th style="text-align: center">出库日期</th>
                            <th style="text-align: center">出库数量</th>
                            <th style="text-align: center">仓库 </th>
                            <th style="text-align: center">库位</th>
                            <th style="text-align: center">备注</th>
						</tr>
						
					</thead>
					
					<tbody>
					</tbody>
					<tfoot></tfoot>
				</table>
			</div>
         <!--出库记录end-->
	</div>
	<div class="tab-pane fade " id="tab_1_5">
		<!--盘点记录start-->
   <!--  此期不做 -->
            <!--盘点记录end-->
	</div>

     </div>
					
		            <!-- <div class="portlet-title">
			               <div class="tools">
				                <button type="button" ng-click="cancelPage()" class="btn default btn-circle  btn-sm"><i class="fa fa-undo"></i> 取消 </button>
				            </div>
					</div> -->
      			</div>
			</div>
	</div>
</div>

