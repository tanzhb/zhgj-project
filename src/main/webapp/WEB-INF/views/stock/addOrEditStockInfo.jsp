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
<h3 class="page-title"> 库存信息
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
                            <div class="caption">库存信息</div>
                            <div class="actions">
                                <button  ng-show="stockView"    class="btn blue  btn-outline  btn-sm " ng-click="editStock()">
                                            <i class="fa fa-edit"></i> 编辑 </button>
                                <button   ng-show="stockEdit"   class="btn red  btn-outline  btn-sm " ng-click="cancelEditStock()">
                                            <i class="fa fa-undo"></i> 取消 </button>
                                <button  ng-hide="stockAdd"   type="submit"   class="btn blue  btn-outline  btn-sm "   ng-click="saveStock()">
                                            <i class="fa fa-save"></i> 保存 </button>
                            </div>
                        </div>
                        <div class="portlet-body form">
                            <form  id="stockForm" class="form-horizontal"   >
								<div class="form-body">
									<div class="alert alert-danger display-hide">
                                               <button class="close" data-close="alert"></button>请先输入正确数据！</div>
								           <div class="row">
                                                             <div class="col-md-6">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="stockNum"> <span class="required"> * </span>库存编号 :</label>
                                                    <div class="col-md-8">
                                                     <input type="hidden"     id="materielSerial" ng-model="stock.materielSerial"  />
                                                        <input type="text" class="form-control" id="stockNum" name="stockNum" ng-model="stock.stockNum"  ng-hide="stockAdd" />
                                                        <div class="form-control-focus"> </div>
                                                          <p class="control-label left" ng-show="stockView">{{stock.stockNum}}</p> 
                                                         <!--  存放物料流水号-->
                                                    </div>
                                            </div>
										</div>
                                                           <div class="col-md-6">
                                                                <div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="materielNum"><span class="required"> * </span> 物料编号 :</label>
                                                    <div class="col-md-6">
                                                     <input type="text" class="form-control"   id="materielNum" name ="materielNum"  readonly  ng-hide="stockAdd"   readonly="readonly"
												ng-model="stock.materielNum" /> 
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="stockView">{{stock.materielNum}}</p> 
                                                                    </div>
                                                                    <div class="col-md-1"><span class="input-inline-btn"  ng-hide="stockAdd"  >
                                                            <button class="btn default" type="button"   ng-click="selectMateriel()" data-target="#basicMaterielInfo" data-toggle="modal" >
                                                                <i class="fa fa-search"></i>
                                                            </button>
                                                        </span></div>
                                                                </div>
                                                            </div>   
                                                        </div>
                                                        <div class="row">
                                                          <div class="col-md-6">
                                                                <div class="form-group form-md-line-input">
                                                                <label class="col-md-4 control-label" for="materielName">   物料名称 :</label>
                                                                    <div class="col-md-8">
                                                                       <input type="text" class="form-control" placeholder=""  id="materielName" name ="materielName"  ng-hide="stockAdd"   readonly
												ng-model="stock.materielName" > 
                                                                       <div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="stockView">{{stock.materielName}}</p> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <!--/span-->
                                                            <div class="col-md-6">
                                                            <div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="stockCategory">规格型号 :</label>
                                                    <div class="col-md-8">
                                                                    <input type="text" class="form-control"   id="specifications" name ="specifications"  ng-hide="stockAdd"  readonly="readonly"
												ng-model="stock.specifications" > 
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="stockView">{{stock.specifications}}</p> 
                                                                </div>
                                                            </div>
                                                            <!--/span-->
                                                        </div>
                                                        </div>
                                                        <!--/row-->
                                                        <div class="row">
                                                            <div class="col-md-6">
                                                             <div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="address"> <span class="required"> * </span>最高库存:</label>
                                                    <div class="col-md-8">
                                                                        <input type="text" class="form-control"   id="maxStock" name ="maxStock"  ng-hide="stockAdd"  
												ng-model="stock.maxStock" > 
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="stockView">{{stock.maxStock}}</p> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <!--/span-->
                                                            <div class="col-md-6">
                                                            <div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="minStock"> <span class="required"> * </span>最低库存 :</label>
                                                    <div class="col-md-8">
                                                                        <input type="text"  class="form-control" placeholder=""  id="minStock" name ="minStock"   ng-hide="stockAdd"  
												ng-model="stock.minStock" > 
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="stockView">{{stock.minStock}}</p> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <!--/span-->
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-md-6">
                                                             <div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="manageType"> <span class="required"> * </span>管理类型:</label>
                                                    <div class="col-md-8">
                                                     <select class="form-control"    id="manageType"   name ="manageType" ng-model="stock.manageType"  ng-hide="stockAdd"  >
                                                                          <option value=""></option>
                                                                        <option value="1">自建库管理</option>
                                                                        <option value="2">代管库管理</option>
                                                                        <option value="3">境外库管理</option>
                                                                        </select>
                                                                        
                                                                        <!-- <input type="text" class="form-control"   id="manageType" name ="manageType"  ng-hide="stockAdd"  
												ng-model="stock.manageType" />  -->
												<div class="form-control-focus"> </div>
												<p class="control-label left" ng-show="stockView" ng-if="stock.manageType=='1'" >自建库管理</p>
													<p class="control-label left" ng-show="stockView" ng-if="stock.manageType=='2'" >代管库管理</p>
														<p class="control-label left" ng-show="stockView" ng-if="stock.manageType=='3'" >境外库管理</p>
                                                                        
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <!--/span-->
                                                            <div class="col-md-6">
                                                            <div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="materielOwner"> <span class="required"> * </span>物权方 :</label>
                                                    <div class="col-md-8">
                                                                        <input type="text"  class="form-control" placeholder=""  id="materielOwner" name ="materielOwner"   ng-hide="stockAdd"  
												ng-model="stock.materielOwner" > 
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="stockView">{{stock.materielOwner}}</p> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <!--/span-->
                                                        </div>
                                                         <div class="row">
                                                            <div class="col-md-6">
                                                             <div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="serviceParty"> <span class="required"> * </span>服务方:</label>
                                                    <div class="col-md-8">
                                                                        <input type="text" class="form-control"   id="serviceParty" name ="serviceParty"  ng-hide="stockAdd"  
												ng-model="stock.serviceParty" > 
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="stockView">{{stock.specifications}}</p> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <!--/span-->
                                                            <div class="col-md-6">
                                                            <div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="remark"> 备注 :</label>
                                                    <div class="col-md-8">
                                                                        <input type="text"  class="form-control" placeholder=""  id="remark" name ="remark"   ng-hide="stockAdd"  
												ng-model="stock.remark" > 
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="stockView">{{stock.remark}}</p> 
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