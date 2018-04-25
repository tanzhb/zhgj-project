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
            <a ui-sref="stock">库存信息</a>
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
                            <div class="caption">供应商库存</div>
                            <div class="actions" >
                                <button type="button" onclick="goBackPage()" class="btn defualt  btn-circle  btn-sm"><i class="fa fa-reply"></i>返回</button>
                            </div>
                        </div>
                        <br/>
                        <div class="actions" style="float:right;">
                                <button  ng-show="stockView"    class="btn purple  btn-sm btn-circle  " ng-click="editStockForSupply()">
                                            <i class="fa fa-edit"></i> 编辑 </button>
                                <button   ng-show="stockEdit"   class="btn defualt  btn-sm btn-circle " ng-click="cancelEditStockForSupply()">
                                            <i class="fa fa-undo"></i> 取消 </button>
                                <button  ng-hide="stockAdd"   type="submit"   class="btn green  btn-sm btn-circle "   ng-click="saveStockForSupply()">
                                            <i class="fa fa-save"></i> 保存 </button>
                                            
                            </div>
                            <br/>
                              <div class="tab-content">
                              
				<div class="tab-pane fade active in" id="tab_1_1"> 
                        <div class="portlet-body form">
                            <form  id="stockForSupplyForm"  >
								<div class="form-body">
									<div class="alert alert-danger display-hide">
                                               <button class="close" data-close="alert"></button>请先输入正确数据！</div>
                                               <div class="row">
										<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="inOutNum" ng-if="judgeSerialNum.indexOf('In')>-1">入库单号 <span class="required"> * </span></label>
                                                    <label class="control-label bold" for="inOutNum" ng-if="judgeSerialNum.indexOf('Out')>-1">出库单号 <span class="required"> * </span></label>
                                                    <div class="">
                                                        <input type="text" class="form-control" id="inOutNum" name="inOutNum" ng-model="record.inOutNum" ng-hide="stockAdd" >
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="stockView">{{record.inOutNum}}</p>
                                                    </div>
                                            </div>
										</div>
										<!--/span-->
										
										<div class="col-md-4">
											<div class="form-group">
											<label class="control-label bold" for="inOutNum" ng-if="judgeSerialNum.indexOf('In')>-1">入库类型 <span class="required"> * </span></label>
                                                    <label class="control-label bold" for="inOutType"  ng-if="judgeSerialNum.indexOf('Out')>-1">出库类型<span class="required"> * </span> </label>
	                                                 <div class=""   ng-if="judgeSerialNum.indexOf('In')>-1">
	                                                 	<select class="form-control"  name="inOutType" ng-model="record.inOutType"     data-size="8" ng-hide="stockAdd">
	                                                 	<!-- <option value="">无</option> -->
			                                                   <option value="采购入库">采购入库</option>
			                                                    <option value="其它"  >其它</option>
			                                             </select>
                                                     </div>
                                                     <div class=""   ng-if="judgeSerialNum.indexOf('Out')>-1">
	                                                 	<select class="form-control"  name="inOutType" ng-model="record.inOutType"     data-size="8" ng-hide="stockAdd">
	                                                 	<!-- <option value="">无</option> -->
			                                                   <option value="销售出库">销售出库</option>
			                                                    <option value="其它"  >其它</option>
			                                             </select>
                                                     </div>
                                                     <div class="form-control-focus"> </div>
                                                     <p class="control-label left" ng-show="stockView">{{record.inOutType}}</p>
                                            </div>
										</div>
										<!--/span-->
										<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="stockDate"  ng-if="judgeSerialNum.indexOf('In')>-1">入库日期 <span class="required"> * </span></label>
                                                     <label class="control-label bold" for="stockDate"  ng-if="judgeSerialNum.indexOf('Out')>-1">出库日期 <span class="required"> * </span></label>
                                                    <div class="">
                                                      <!--  <input type="text" class="form-control  date-picker" size="16"  data-date-format="yyyy-mm-dd hh:mm:ss" 
                                                         id="stockDate"  name="stockDate" ng-model="record.stockDate" ng-hide="deliverAdd" readonly="readonly"> -->
                                                         <input type="text" class="form-control  date-picker" size="16"  
                                                         id="stockDate"  name="stockDate" ng-model="record.stockDate" ng-hide="stockAdd" disabled>
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="stockView">{{record.stockDate}}</p>
                                                    </div>
                                            </div>
										</div>
										
										
									</div>
									       <div class="row">
                                                           <div class="col-md-4">
                                                                <div class="form-group">
                                                    <label class="control-label bold" for="materielNum"><span class="required"> * </span> 物料编号 :</label>
                                                    <div class="">
												<div class="input-group" ng-click="selectMateriel()" data-target="#basicMaterielInfo" data-toggle="modal" >
	                                                        <input type="text" class="form-control"   id="materielNum" name ="materielNum"  readonly  ng-hide="stockAdd" 
												ng-model="stock.materielNum" /> 
	                                                        <span class="input-group-btn" style="vertical-align: top;"  ng-hide="stockAdd">
	                                                            <button class="btn default" type="button">
	                                                                <i class="fa fa-search"></i>
	                                                            </button>
	                                                        </span>
                                                         </div>
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="stockView">{{record.materielNum}}</p> 
                                                                         <input type="hidden"  id="materielSerial" ng-model="record.materielSerial"  /><!--  存放物料流水号-->
                                                                    </div>
                                                                </div>
                                                            </div>   
                                                            <div class="col-md-4">
                                                                <div class="form-group">
                                                                <label class="control-label bold" for="materielName">   物料名称 :</label>
                                                                    <div class="  ">
                                                                       <input type="text" class="form-control" placeholder=""  id="materielName" name ="materielName"  ng-hide="stockAdd"   readonly
												ng-model="record.materielName" > 
                                                                       <div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="stockView">{{record.materielName}}</p> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                             <div class="col-md-4">
                                                            <div class="form-group">
                                                    <label class="control-label bold" for="stockCategory">规格型号 :</label>
                                                    <div class="  ">
                                                                    <input type="text" class="form-control"   id="specifications" name ="specifications"  ng-hide="stockAdd"  readonly="readonly"
												ng-model="record.specifications" > 
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="stockView">{{record.specifications}}</p> 
                                                                </div>
                                                            </div>
                                                            <!--/span-->
                                                        </div>
                                                        </div>
									<div class="row">
										<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="supplyComId"   ng-if="judgeSerialNum.indexOf('In')>-1"> 入库仓库</label>
                                                    <label class="control-label bold" for="supplyComId"   ng-if="judgeSerialNum.indexOf('Out')>-1"> 出库仓库</label>
                                                    <div class="">
                                                    <div  ng-hide="span">
														<select class="form-control"  id="warehouseSerial"  data-live-search="true" data-size=""  ng-hide="stockAdd"
															name="warehouseSerial"
															ng-model="record.warehouseSerial"
															>
															<option ng-repeat="item in warehouseListf"
																value="{{item.serialNum}}">{{item.warehouseName}}</option>
															<option value=""></option>
														</select>
														</div>
                                                         <p class="control-label left"   ng-show="stockView">{{record.warehouseName}}</p>
                                                    </div>
                                            </div>
										</div>
						<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="supplyComId"  ng-if="judgeSerialNum.indexOf('In')>-1"> <span class="required"> * </span>入库数量</label>
                                                   <label class="control-label bold" for="supplyComId"  ng-if="judgeSerialNum.indexOf('Out')>-1"> <span class="required"> * </span>出库数量</label>
                                                    <div class="">
                                                    	<input type="text" class="form-control"    name="materielCount"  ng-model="record.materielCount"   ng-hide="stockAdd"/>
                                                       <p class="control-label left"   ng-show="stockView">{{record.materielCount}}</p>
                                                    </div>
                                            </div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="relationNum"  ng-if="record.inOutType!='采购入库'&&judgeSerialNum.indexOf('In')>-1">关联单号 </label>
                                                    <label class="control-label bold" for="relationNum"   ng-if="record.inOutType=='采购入库'&&judgeSerialNum.indexOf('In')>-1">采购订单号 </label>
                                                    <label class="control-label bold" for="relationNum"  ng-if="record.inOutType!='销售出库'&&judgeSerialNum.indexOf('Out')>-1">关联单号 </label>
                                                    <label class="control-label bold" for="relationNum"  ng-if="record.inOutType=='销售出库'&&judgeSerialNum.indexOf('Out')>-1">销售订单号  </label>
                                                    <div class="">
                                                        <input type="text" class="form-control" id="relationNum" name="relationNum" ng-model="record.relationNum" ng-hide="stockAdd" >
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="stockView">{{record.relationNum}}</p>
                                                    </div>
                                            </div>
										</div>
									</div>
									<div class="row">
									<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="operator">操作员 <!-- <span class="required"> * </span> --></label>
                                                    <div class="">
                                                        <input type="text" class="form-control" id="operator" name="operator" ng-model="record.operator" ng-hide="stockAdd" >
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="stockView">{{record.operator}}</p>
                                                    </div>
                                            </div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="contactNum">联系方式 </label>
                                                    <div class="">
                                                        <input type="text" class="form-control" id="contactNum" name="contactNum"   ng-model="record.contactNum" ng-hide="stockAdd" >
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="stockView">{{record.contactNum}}</p>
                                                    </div>
                                            </div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for="remark">备注</label>
                                                    <div class="">
                                                         <input type="text" class="form-control" id="remark"  name="remark" ng-model="record.remark" ng-hide="stockAdd" >
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="stockView">{{record.remark}}</p>
                                                    </div>
                                            </div>
										</div>
										
										</div>
										
								    
                                                        
                                                        <!--/row-->
                                                      
                                                         
                                             <div>          
                                                        </div> 
								</div>
							</form>
         				</div>
         				</div></div>
				</div>
				
        </div>
        <!-- END EXAMPLE TABLE PORTLET-->
    </div>
</div>
<jsp:include  page="../materiel/selectMateriel.jsp"/>
<!-- END MAIN CONTENT -->
<!-- BEGIN MAIN JS -->
 <script>
</script> 
<!-- END MAIN JS -->