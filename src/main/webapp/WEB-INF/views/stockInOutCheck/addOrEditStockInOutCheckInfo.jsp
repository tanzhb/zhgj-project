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
        <!-- BEGIN EXAMPLE TABLE PORTLET-->
        <div class="portlet bordered">
            <div class="portlet-body">
				<div class="portlet light ">
                        <div class="portlet-title">
                            <div class="caption"><span  ng-if="inOrOut=='in'"  >入库检验</span><span   ng-if="inOrOut=='out'" >出库检验</span></div>
                            <div class="actions">
                                <button  ng-show="stockInOutCheckView"    class="btn blue  btn-outline  btn-sm " ng-click="editStockInOutCheck()">
                                            <i class="fa fa-edit"></i> 编辑 </button>
                                <button   ng-show="stockInOutCheckEdit"   class="btn red  btn-outline  btn-sm " ng-click="cancelEditStockInOutCheck()">
                                            <i class="fa fa-undo"></i> 取消 </button>
                                <button  ng-hide="stockInOutCheckAdd"   type="submit"   class="btn blue  btn-outline  btn-sm "   ng-click="saveStockInOutCheck()">
                                            <i class="fa fa-save"></i> 保存 </button>
                            </div>
                        </div>
                        <div class="portlet-body form">
                            <form  id="stockInOutCheckForm" class="form-horizontal"   >
								<div class="form-body">
									<div class="alert alert-danger display-hide">
                                               <button class="close" data-close="alert"></button>请先输入正确数据！</div>
								           <div class="row">
                                                             <div class="col-md-4">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="checkNum"> <span class="required"> * </span>	检验单号 :</label>
                                                    <div class="col-md-8">
                                                     <input type="hidden"     id="materielSerial" ng-model="stock.materielSerial"  />
                                                        <input type="text" class="form-control" id="checkNum" name="checkNum" ng-model="stock.stockNum"  ng-hide="stockAdd" />
                                                        <div class="form-control-focus"> </div>
                                                          <p class="control-label left" ng-show="stockView">{{stockInOutCheck.checkNum}}</p> 
                                                         <!--  存放物料流水号-->
                                                    </div>
                                            </div>
										</div>
                                                           <div class="col-md-4">
                                                                <div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="materielNum"><span class="required"> * </span> <span  ng-if="inOrOut=='in'"  >收货单号</span><span   ng-if="inOrOut=='out'" >发货单号</span> :</label>
                                                    <div class="col-md-6">
                                                     <input type="text" class="form-control"   id="takeDeliverSerial" name ="takeDeliverSerial"  readonly    ng-if="inOrOut=='in'"  ng-hide="stockInOutCheckAdd"   readonly="readonly"
												ng-model="stockInOutCheck.takeDeliverNum" /> 
												 <input type="text" class="form-control"   id="deliverSerial" name ="deliverSerial"  readonly      ng-if="inOrOut=='out'"  ng-hide="stockInOutCheckAdd"   readonly="readonly"
												ng-model="stockInOutCheck.deliverNum" />
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="stockInOutCheckView"   ng-if="inOrOut=='out'"  >{{stockInOutCheck.deliverNum}}</p> 
                                                                         <p class="control-label left" ng-show="stockInOutCheckView"  ng-if="inOrOut=='in'"  >{{stockInOutCheck.takeDeliverNum}}</p> 
                                                                    </div>
                                                                    <div class="col-md-1"><span class="input-inline-btn"  ng-hide="stockInOutCheckAdd"  >
                                                            <button class="btn default" type="button"   ng-click="selectMateriel()" data-target="#basicMaterielInfo" data-toggle="modal" >
                                                                <i class="fa fa-search"></i>
                                                            </button>
                                                        </span></div>
                                                                </div>
                                                            </div> 
                                                            <div class="col-md-4">
                                                                <div class="form-group form-md-line-input">
                                                                <label class="col-md-4 control-label" for="materielName">  <span  ng-if="inOrOut=='in'"  > 采购订单号</span><span   ng-if="inOrOut=='out'" > 销售订单号</span> :</label>
                                                                    <div class="col-md-8">
                                                                       <input type="text" class="form-control" placeholder=""  id="relationSaleNum"     ng-if="inOrOut=='out'"  name ="relationSaleNum"  ng-hide="stockInOutCheckAdd"   readonly
												ng-model="stockInOutCheck.relationSaleNum" > 
												 <input type="text" class="form-control" placeholder=""  id="relationBuyNum" name ="relationBuyNum"   ng-if="inOrOut=='in'"  ng-hide="stockInOutCheckAdd"   readonly
												ng-model="stockInOutCheck.relationBuyNum" > 
                                                                       <div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="stockInOutCheckView"  ng-if="inOrOut=='out'" >{{stockInOutCheck.relationSaleNum}}</p> 
                                                                        <p class="control-label left" ng-show="stockInOutCheckView"  ng-if="inOrOut=='in'" >{{stockInOutCheck.relationBuyNum}}</p> 
                                                                    </div>
                                                                </div>
                                                            </div>  
                                                        </div>
                                                        <div class="row">
                                                            <!--/span-->
                                                            <div class="col-md-4">
                                                            <div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="stockCategory"><span  ng-if="inOrOut=='in'"  >供应商</span><span   ng-if="inOrOut=='out'" >采购商</span> :</label>
                                                    <div class="col-md-8">
                                                                    <input type="text" class="form-control"   id="comName" name ="comName"  ng-hide="stockInOutCheckAdd"  readonly="readonly"
												ng-model="stockInOutCheck.comName" > 
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="stockInOutCheckView">{{stockInOutCheck.comName}}</p> 
                                                                </div>
                                                            </div>
                                                            <!--/span-->
                                                        </div>
                                                        <div class="col-md-4">
                                                            <div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="checkParty"><span class="required"> * </span>检验方 :</label>
                                                    <div class="col-md-8">
                                                                    <input type="text" class="form-control"   id="checkParty" name ="checkParty"  ng-hide="stockInOutCheckAdd"  
												ng-model="stockInOutCheck.checkParty" > 
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="stockInOutCheckView">{{stockInOutCheck.checkParty}}</p> 
                                                                </div>
                                                            </div>
                                                            <!--/span-->
                                                        </div>
                                                             <div class="col-md-4">
                                                            <div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="checkDate"> <span class="required"> * </span>检验日期 :</label>
                                                    <div class="col-md-8">
                                                                        <input type="text"  class="form-control" placeholder=""  id="checkDate" name ="checkDate"   ng-hide="stockInOutCheckAdd"  
												ng-model="stockInOutCheck.checkDate" > 
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="stockInOutCheckView">{{stockInOutCheck.checkDate}}</p> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <!--/row-->
                                                       
                                                        <div class="row">
                                                            <div class="col-md-4">
                                                             <div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="checker"> <span class="required"> * </span>检验员:</label>
                                                    <div class="col-md-8">
                                                       <input type="text"  class="form-control" placeholder=""  id="checker" name ="checker"   ng-hide="stockInOutCheckAdd"  
												ng-model="stockInOutCheck.cchecker" > 
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="stockInOutCheckView">{{stockInOutCheck.checker}}</p> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <!--/span-->
                                                            <div class="col-md-4">
                                                            <div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="contactNum"> <span class="required"> * </span>联系电话 :</label>
                                                    <div class="col-md-8">
                                                                        <input type="text"  class="form-control" placeholder=""  id="contactNum" name ="contactNum"   ng-hide="stockInOutCheckAdd"  
												ng-model="stockInOutCheck.contactNum" > 
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="stockInOutCheckView">{{stockInOutCheck.contactNum}}</p> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="col-md-4">
                                                             <div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="status"> <!-- <span class="required"> * </span> -->状态:</label>
                                                    <div class="col-md-8">
                                                                        <input type="text" class="form-control"   id="status" name ="status"  ng-hide="stockInOutCheckAdd"  
												ng-model="stockInOutCheck.status" > 
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="stockInOutCheckView">{{stockInOutCheck.status}}</p> 
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