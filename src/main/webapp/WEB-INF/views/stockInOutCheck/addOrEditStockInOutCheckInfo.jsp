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
<h3 class="page-title"> 检验信息
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
     <form  id="stockInOutCheckForm"  >
        <!-- BEGIN EXAMPLE TABLE PORTLET-->
        <div class="portlet bordered">
            <div class="portlet-body">
				<div class="portlet light ">
                        <div class="portlet-title"><!--  ng-if="inOrOut.indexOf('in')>-1"   ng-if="inOrOut.indexOf('out')>-1"  -->
                            <div class="caption"><span ng-if="inOrOut.indexOf('in')>-1" >入库检验</span><span  ng-if="inOrOut.indexOf('out')>-1">出库检验</span></div>    
                            <div class="actions">
                                <button  ng-show="stockInOutCheckView"    class="btn purple  btn-sm btn-circle " ng-click="editStockInOutCheck()">
                                            <i class="fa fa-edit"></i> 编辑 </button>
                                <button   ng-hide="stockInOutCheckEdit"    ng-if="inOrOut.length>3"   class="btn defualt  btn-sm btn-circle " ng-click="cancelEditStockInOutCheck()">
                                            <i class="fa fa-undo"></i> 取消 </button>
                                            <button   ng-hide="stockInOutCheckEdit"    ng-if="inOrOut.length<=3"   class="btn red  btn-sm btn-circle " ui-sref="stockInOutCheck">
                                            <i class="fa fa-undo"></i> 取消 </button>
                                <button  ng-hide="stockInOutCheckAdd"   type="submit"   class="btn green  btn-sm btn-circle "   ng-click="saveStockInOutCheck()">
                                            <i class="fa fa-save"></i> 保存 </button>
                            </div>
                        </div>
                          <div class="tab-content">
				<div class="tab-pane fade active in" id="tab_1_1"> 
                        <div class="portlet-body form">
								<div class="form-body">
									<div class="alert alert-danger display-hide">
                                               <button class="close" data-close="alert"></button>请先输入正确数据！</div>
								           <div class="row">
                                                             <div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="checkNum"> <span class="required"> * </span>	检验单号 :</label>
                                                    <div class=" ">
                                                        <input type="text" class="form-control" id="checkNum" name="checkNum" ng-model="stockInOutCheck.checkNum"  ng-hide="stockInOutCheckAdd" />
                                                        <div class="form-control-focus"> </div>
                                                          <p class="control-label left" ng-show="stockInOutCheckView">{{stockInOutCheck.checkNum}}</p> 
                                                         <!--  存放物料流水号-->
                                                    </div>
                                            </div>
										</div>
										<input type="hidden"   id="deliverSerial" ng-model="stockInOutCheck.deliverSerial"  />
										<input type="hidden"     id="takeDeliverSerial" ng-model="stockInOutCheck.takeDeliverSerial"  />
                                                           <div class="col-md-4">
                                                                <div class="form-group">
                                                    <label class="control-label bold" for=""><span class="required"> * </span> <span  ng-if="inOrOut.indexOf('in')>-1"  >收货单号</span><span   ng-if="inOrOut.indexOf('out')>-1" >发货单号</span> :</label>
                                                    <div class="">
                                                             <div class="input-group" ng-if="inOrOut.indexOf('in')>-1&&inOrOut.length<=3"   ng-click="selectDeliverOrTakeDelivery('in')" data-target="#takeDeliveryInfo" data-toggle="modal"  >
	                                                        <input type="text" class="form-control"   id="takeDeliverNum" name ="takeDeliverNum"  readonly     ng-hide="stockInOutCheckAdd"   readonly="readonly"
												ng-model="stockInOutCheck.takeDeliverNum" />
	                                                        <span class="input-group-btn" style="vertical-align: top;">
	                                                            <button class="btn default" type="button">
	                                                                <i class="fa fa-search"></i>
	                                                            </button>
	                                                        </span>
                                                         </div>
                                                           <div class="input-group" ng-if="inOrOut.indexOf('out')>-1&&inOrOut.length<=3"   ng-click="selectDeliverOrTakeDelivery('out')" data-target="#deliverInfo" data-toggle="modal"  >
	                                                        <input type="text" class="form-control"   id="deliverNum" name ="deliverNum"  readonly    ng-hide="stockInOutCheckAdd"   readonly="readonly"
												ng-model="stockInOutCheck.deliverNum" />
	                                                        <span class="input-group-btn" style="vertical-align: top;">
	                                                            <button class="btn default" type="button">
	                                                                <i class="fa fa-search"></i>
	                                                            </button>
	                                                        </span>
                                                         </div>
												<div class="form-control-focus"> </div>
												 
                                                                        <p class="control-label left" ng-show="stockInOutCheckView"   ng-if="inOrOut.indexOf('out')>-1"  >{{stockInOutCheck.deliverNum}}</p> 
                                                                         <p class="control-label left" ng-show="stockInOutCheckView"  ng-if="inOrOut.indexOf('in')>-1"  >{{stockInOutCheck.takeDeliverNum}}</p> 
                                                                    </div>
                                                               
                                                                </div>
                                                            </div> 
                                                            <div class="col-md-4">
                                                                <div class="form-group">
                                                                <label class="control-label bold" for="materielName">  <span  ng-if="inOrOut.indexOf('in')>-1"  > 采购订单号</span><span   ng-if="inOrOut.indexOf('out')>-1" > 销售订单号</span> :</label>
                                                                    <div class=" ">
                                                                       <input type="text" class="form-control" placeholder=""  id="relationSaleNum"     ng-if="inOrOut.indexOf('out')>-1"  name ="relationSaleNum"  ng-hide="stockInOutCheckAdd"   readonly
												ng-model="stockInOutCheck.relationSaleNum" > 
												 <input type="text" class="form-control" placeholder=""  id="relationBuyNum" name ="relationBuyNum"   ng-if="inOrOut.indexOf('in')>-1"  ng-hide="stockInOutCheckAdd"   readonly
												ng-model="stockInOutCheck.relationBuyNum" > 
                                                                       <div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="stockInOutCheckView"  ng-if="inOrOut.indexOf('out')>-1" >{{stockInOutCheck.relationSaleNum}}</p> 
                                                                        <p class="control-label left" ng-show="stockInOutCheckView"  ng-if="inOrOut.indexOf('in')>-1" >{{stockInOutCheck.relationBuyNum}}</p> 
                                                                    </div>
                                                                </div>
                                                            </div>  
                                                        </div>
                                                        <div class="row">
                                                            <!--/span-->
                                                            <div class="col-md-4">
                                                            <div class="form-group">
                                                    <label class="control-label bold" for="supplyName">供应商 :</label>
                                                    <div class=" ">
                                                                    <input type="text" class="form-control"   id="supplyName" name ="supplyName"  ng-hide="stockInOutCheckAdd"  readonly="readonly"
												ng-model="stockInOutCheck.supplyName" > 
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="stockInOutCheckView">{{stockInOutCheck.supplyName}}</p> 
                                                                </div>
                                                            </div>
                                                            <!--/span-->
                                                        </div>
                                                        <div class="col-md-4">
                                                            <div class="form-group">
                                                    <label class="control-label bold" for="checkParty"><span class="required"> * </span>检验方 :</label>
                                                    <div class=" ">
                                                                    <input type="text" class="form-control"   id="checkParty" name ="checkParty"  ng-hide="stockInOutCheckAdd"  
												ng-model="stockInOutCheck.checkParty" > 
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="stockInOutCheckView">{{stockInOutCheck.checkParty}}</p> 
                                                                </div>
                                                            </div>
                                                            <!--/span-->
                                                        </div>
                                                             <div class="col-md-4">
                                                            <div class="form-group">
                                                    <label class="control-label bold" for="checkDate"> <span class="required"> * </span>检验日期 :</label>
                                                    <div class=" ">
                                                                        <input type="text"  class="form-control form-control-inline date-picker"     data-date-format="yyyy-mm-dd"  data-date-viewmode="years"   placeholder=""  id="checkDate" name ="checkDate"   ng-hide="stockInOutCheckAdd"  
												ng-model="stockInOutCheck.checkDate" /> 
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="stockInOutCheckView">{{stockInOutCheck.checkDate | date:'yyyy-MM-dd'}}</p> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <!--/row-->
                                                       
                                                        <div class="row">
                                                            <div class="col-md-4">
                                                             <div class="form-group">
                                                    <label class="control-label bold" for="checker"> <span class="required"> * </span>检验员:</label>
                                                    <div class=" ">
                                                       <input type="text"  class="form-control" placeholder=""  id="checker" name ="checker"   ng-hide="stockInOutCheckAdd"  
												ng-model="stockInOutCheck.checker" > 
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="stockInOutCheckView">{{stockInOutCheck.checker}}</p> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <!--/span-->
                                                            <div class="col-md-4">
                                                            <div class="form-group">
                                                    <label class="control-label bold" for="contactNum"> <span class="required"> * </span>联系电话 :</label>
                                                    <div class=" ">
                                                                        <input type="text"  class="form-control" placeholder=""  id="contactNum" name ="contactNum"   ng-hide="stockInOutCheckAdd"  
												ng-model="stockInOutCheck.contactNum" > 
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="stockInOutCheckView">{{stockInOutCheck.contactNum}}</p> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="col-md-4">
                                                             <div class="form-group">
                                                    <label class="control-label bold" for="status">  <span  ng-show="stockInOutCheckView" > 状态:</span> </label>
                                                    <div class=" ">
												<div class="form-control-focus"> </div>
												 					<span class="label label-sm label-success"  ng-if="stockInOutCheck.status==0" ng-show="stockInOutCheckView" >待检验</span>
                                                                    <span class="label label-sm label-success"  ng-if="stockInOutCheck.status==1"  ng-show="stockInOutCheckView" >待审批</span>
                                                                    <span class="label label-sm label-success"  ng-if="stockInOutCheck.status==2"  ng-show="stockInOutCheckView" >已检验</span>
                                                                       
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <!--/span-->
                                                        </div>
                                                         
                                             <div>          
                                                        </div> 
								</div>
							</form>
         				</div>
         				</div></div>
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
                                                    <th   rowspan="2"  style="text-align: center;vertical-align: middle">状态</th>
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
                                                <tr    ng-repeat="materiel in materials track by $index"   ng-init="value=0" >
                                                    	<td ng-if="inOrOut.indexOf('in')>-1"  >{{materiel.orderMateriel.materiel.materielNum}}</td><td ng-if="inOrOut.indexOf('out')>-1"  >{{materiel.materielNum}}</td>
										<td ng-if="inOrOut.indexOf('in')>-1"  >{{materiel.orderMateriel.materiel.materielName}}</td><td ng-if="inOrOut.indexOf('out')>-1"  >{{materiel.materielName}}</td>
										<td ng-if="inOrOut.indexOf('in')>-1"  >{{materiel.orderMateriel.materiel.specifications}}</td><td ng-if="inOrOut.indexOf('out')>-1"  >{{materiel.specifications}}</td>
										<td ng-if="inOrOut.indexOf('in')>-1"  >{{materiel.orderMateriel.materiel.unit}}</td><td ng-if="inOrOut.indexOf('out')>-1"  >{{materiel.unit}}</td>
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
										
										<td><div class="col-md-12 form-group">
                                                 <input type="text" class="form-control input-small" id="qualifiedCount{{$index}}"    ng-if="inOrOut.indexOf('in')>-1"   name="qualifiedCount"   data-acceptcount="{{materiel.acceptCount}}" ng-model="materiel.qualifiedCount" ng-hide="stockInOutCheckAdd"  />
                                                  <input type="text" class="form-control input-small" id="qualifiedCount{{$index}}"    ng-if="inOrOut.indexOf('out')>-1"   name="qualifiedCount"   data-delivercount="{{materiel.deliverCount}}" ng-model="materiel.qualifiedCount" ng-hide="stockInOutCheckAdd"  />
                                                 <div class="form-control-focus"> </div>
                                                 <p class="control-label left" ng-show="stockInOutCheckView"> {{materiel.qualifiedCount}}</p>
                                            </div></td>
										<td><span id="unqualifiedCount{{$index}}"   ng-if="inOrOut.indexOf('in')>-1" >{{materiel.acceptCount-materiel.qualifiedCount}}</span>
										<span id="unqualifiedCount{{$index}}"   ng-if="inOrOut.indexOf('out')>-1" >{{materiel.deliverCount-materiel.qualifiedCount}}</span></td>
										<td><div class="col-md-12">
                                                 <input type="text" class="form-control input-small" id="checkRemark{{$index}}" name="checkRemark"  ng-model="materiel.checkRemark" ng-hide="stockInOutCheckAdd" >
                                                 <div class="form-control-focus"> </div>
                                                 <p class="control-label left" ng-show="stockInOutCheckView">{{materiel.checkRemark}}</p>
                                            </div>   </td>
										<td><span class="label label-sm label-success"  ng-if="stockInOutCheck.status==0" >待检验</span>
                                                  <span class="label label-sm label-success"  ng-if="stockInOutCheck.status==1" >待审批</span>
                                                   <span class="label label-sm label-success"  ng-if="stockInOutCheck.status==2" >已检验</span></td>
                                                </tr>
                                                <tr ng-if="materials!=null"><td colspan="3">合计</td><td >{{materials.length}}</td><td></td><td></td><td></td><td   >{{stockInOutCheck.totalDeliverCount}}</td></tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div><!-- 物料信息 END-->
                                
        <!-- END EXAMPLE TABLE PORTLET-->
    
<jsp:include  page="../stockInOutCheck/selectDeliverOrTakeDelivery.jsp"/>
<%-- <jsp:include  page="../stockInOutCheck/selectDeliverOrTakeDelivery1.jsp"/> --%>
<!-- END MAIN CONTENT -->
<!-- BEGIN MAIN JS -->
 <script>
</script> 
<!-- END MAIN JS -->