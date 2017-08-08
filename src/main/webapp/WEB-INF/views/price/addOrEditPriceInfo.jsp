<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<h3 class="page-title"> 价格信息
</h3>
<div class="page-bar">
    <ul class="page-breadcrumb">
        <li>
            <i class="fa fa-home"></i>
            <a ui-sref="dashboard">首页</a>
            <i class="fa fa-angle-right"></i>
        </li>
        <li>
            <a ui-sref="datatablesmanaged">基础数据</a>
            <i class="fa fa-angle-right"></i>
        </li>
        <li>
            <a ui-sref="price">价格信息</a>
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
                            <div class="caption">价格信息</div>
                            <div class="actions">
                                <button  ng-show="priceView"    class="btn blue  btn-outline  btn-sm " ng-click="editprice()">
                                            <i class="fa fa-edit"></i> 编辑 </button>
                                <button   ng-show="priceEdit"   class="btn red  btn-outline  btn-sm " ng-click="cancelEditprice()">
                                            <i class="fa fa-undo"></i> 取消 </button>
                                <button  ng-hide="priceAdd"   type="submit"   class="btn blue  btn-outline  btn-sm " ng-click="saveprice()">
                                            <i class="fa fa-save"></i> 保存 </button>
                            </div>
                        </div>
                        <div class="portlet-body form">
                            <form  id="priceForm" class="form-horizontal" >
								<div class="form-body">
									<div class="alert alert-danger display-hide">
                                                <button id="priceTips" class="close" data-close="alert"></button>请先输入正确数据！</div>
								           <div class="row">
                                                             <div class="col-md-6">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="priceNum"> <span class="required"> * </span>价格编号 :</label>
                                                    <div class="col-md-8">
                                                        <input type="text" class="form-control" id="priceNum" name="priceNum" ng-model="price.priceNum"  ng-hide="priceAdd" >
                                                        <div class="form-control-focus"> </div>
                                                          <p class="control-label left" ng-show="priceView">{{price.priceNum}}</p> 
                                                    </div>
                                            </div>
										</div>
                                                            <div class="col-md-6">
                                                                <div class="form-group form-md-line-input">
                                                                <label class="col-md-4 control-label" for="priceName"><!--  <span class="required"> * </span> -->描述 :</label>
                                                                    <div class="col-md-8">
                                                                       <input type="text" class="form-control" placeholder=""  id="priceDescribe" name ="priceDescribe"  ng-hide="priceAdd" 
												ng-model="price.priceDescribe" > 
                                                                       <div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="priceView">{{price.priceDescribe}}</p> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-md-6">
                                                                <div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="priceType"> <span class="required"> * </span>物料编号 :</label>
                                                    <div class="col-md-8">
                                                     <input type="text" class="form-control"   id="materielNum" name ="materielNum"  ng-hide="priceAdd"  
												ng-model="price.materielNum" > <span class="fa fa-search"></span>
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="priceView">{{price.materielNum}}</p> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <!--/span-->
                                                            <div class="col-md-6">
                                                            <div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="priceCategory"><!--  <span class="required"> * </span> -->物料名称 :</label>
                                                    <div class="col-md-8">
                                                                    <input type="text" class="form-control"   id="materielName" name ="materielName"  ng-hide="priceAdd"  readonly="readonly"
												ng-model="price.materielName" > 
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="priceView">{{price.materielName}}</p> 
                                                                </div>
                                                            </div>
                                                            <!--/span-->
                                                        </div>
                                                        </div>
                                                        <!--/row-->
                                                        <div class="row">
                                                            <div class="col-md-6">
                                                             <div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="address"> <!-- <span class="required"> * </span> -->规格型号:</label>
                                                    <div class="col-md-8">
                                                                        <input type="text" class="form-control"   id="specifications" name ="specifications"  ng-hide="priceAdd"  readonly="readonly"
												ng-model="price.specifications" > 
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="priceView">{{price.specifications}}</p> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <!--/span-->
                                                            <div class="col-md-6">
                                                            <div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="owner"> <!-- <span class="required"> * </span> -->单位 :</label>
                                                    <div class="col-md-8">
                                                                        <input type="text"  class="form-control" placeholder=""  id="unit" name ="unit"   ng-hide="priceAdd"  readonly="readonly"
												ng-model="price.unit" > 
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="priceView">{{price.unit}}</p> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <!--/span-->
                                                        </div>
                                                         <div class="row">
                                                            <div class="col-md-6">
                                                            <div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="priceType"> <span class="required"> * </span>价格类型 :</label>
                                                    <div class="col-md-8">
                                                                        <select class="form-control" id="priceType" name ="priceType" ng-model="price.priceType"  ng-hide="priceAdd"  >
                                                                          <option value=""></option>
                                                                        <option value="采购价格">采购价格</option>
                                                                        <option value="销售价格">销售价格</option>
                                                                        </select>
                                                                         <div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="priceView">{{price.priceType}}</p> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <!--/span-->
                                                            <div class="col-md-6">
                                                             <div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="admin"> <span class="required"> * </span>供应商/采购商 :</label>
                                                    <div class="col-md-8">
                                                                        <input type="text"  class="form-control" placeholder=""  id="area" name ="area"  ng-hide="priceAdd" 
												ng-model="price.area" >
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="priceView">{{price.area}}</p>  
                                                                </div>
                                                            </div>
                                                            <!--/span-->
                                                        </div>
                                                        </div>
                                                         <div class="row">
                                                            <div class="col-md-6">
                                                            <div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="currency"> <span class="required"> * </span>币种 :</label>
                                                    <div class="col-md-8">
                                                                        <select class="form-control" id="currency" name ="currency" ng-model="price.currency"  ng-hide="priceAdd"  >
                                                                          <option value=""></option>
                                                                        <option value="RMB">RMB</option>
                                                                        <option value="USD">USD</option>
                                                                        </select>
                                                                         <div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="priceView">{{price.currency}}</p> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <!--/span-->
                                                            <div class="col-md-6">
                                                              <div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="rate"> <span class="required"> * </span>税率 :</label>
                                                    <div class="col-md-8">
                                                                       <select class="form-control" id="rate" name ="rate" ng-model="price.rate"  ng-hide="priceAdd"  >
                                                                          <option value=""></option>
                                                                        <option value="机加工">机加工</option>
                                                                        <option value="备品备件">备品备件</option>
                                                                        <option value="标准品">标准品</option>
                                                                        </select>
                                                                         <div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="priceView">{{price.rate}}</p> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <!--/span-->
                                                        </div>
                                                         <div class="row">
                                                            <div class="col-md-6">
                                                             <div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="price"> <span class="required"> * </span>单价 :</label>
                                                    <div class="col-md-8">
                                                                        <input type="text"  class="form-control" placeholder=""  id="price" name ="price"  ng-hide="priceAdd" 
												ng-model="price.price">
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="priceView">{{price.price}}</p>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                           <div class="col-md-6">
                                                           <div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="inclusivePrice"> <!-- <span class="required"> * </span> -->含税价格 :</label>
                                                    <div class="col-md-8">
                                                                        <input type="text"   class="form-control" placeholder=""  id="inclusivePrice" name ="inclusivePrice"  ng-hide="priceAdd" 
												ng-model="price.inclusivePrice" >
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="priceView">{{price.inclusivePrice}}</p>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-md-6">
                                                             <div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="topPrice"> <!-- <span class="required"> * </span> -->最高限价 :</label>
                                                    <div class="col-md-8">
                                                                        <input type="text"  class="form-control" placeholder=""  id="topPrice" name ="topPrice"  ng-hide="priceAdd" 
												ng-model="price.topPrice">
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="priceView">{{price.topPrice}}</p>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                           <div class="col-md-6">
                                                           <div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="floorPrice"> <!-- <span class="required"> * </span> -->最底限价 :</label>
                                                    <div class="col-md-8">
                                                                        <input type="text"   class="form-control" placeholder=""  id="floorPrice" name ="fax"  ng-hide="priceAdd" 
												ng-model="price.floorPrice" >
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="priceView">{{price.floorPrice}}</p>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-md-6">
                                                             <div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="isLadderPrice"> <!-- <span class="required"> * </span> -->是否阶梯单价 :</label>
                                                    <div class="col-md-8">
                                                                         <div class="icheck-inline">
                                                                            <label>
                                                                                <input type="checkbox" class="icheck">是 </label>
                                                                            <label>
                                                                                <input type="checkbox" checked class="icheck"> 否</label>
                                                                        </div>
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="priceView">{{price.isLadderPrice}}</p>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                           <div class="col-md-6">
                                                           <div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="ladderType"> <!-- <span class="required"> * </span> -->阶梯类型 :</label>
                                                    <div class="col-md-8">
                                                                        <input type="text"   class="form-control" placeholder=""  id="ladderType" name ="ladderType"  ng-hide="priceAdd" 
												ng-model="price.ladderType" >
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="priceView">{{price.ladderType}}</p>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-md-6">
                                                             <div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="priceEffectiveDate"> <span class="required"> * </span>价格生效期 :</label>
                                                    <div class="col-md-8">
                                                                        <input type="text"  class="form-control" placeholder=""  id="priceEffectiveDate" name ="priceEffectiveDate"  ng-hide="priceAdd" 
												ng-model="price.priceEffectiveDate">
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="priceView">{{price.priceEffectiveDate}}</p>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                           <div class="col-md-6">
                                                           <div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="priceExpirationDate"> <span class="required"> * </span>价格失效期 :</label>
                                                    <div class="col-md-8">
                                                                        <input type="text"   class="form-control" placeholder=""  id="priceExpirationDate" name ="priceExpirationDate"  ng-hide="priceAdd" 
												ng-model="price.priceExpirationDate" >
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="priceView">{{price.priceExpirationDate}}</p>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-md-6">
                                                             <div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="tel"><!--  <span class="required"> * </span> -->备注 :</label>
                                                    <div class="col-md-8">
                                                                        <input type="text"  class="form-control" placeholder=""  id="remark" name ="remark"  ng-hide="priceAdd" 
												ng-model="price.remark">
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="priceView">{{price.remark}}</p>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                           <div class="col-md-6">
                                                           <div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="file"> <!-- <span class="required"> * </span> -->附件 :</label>
                                                    <div class="col-md-8">
                                                        <div class="form-group">
                                                <div class="col-md-1">
                                                    <div class="fileinput fileinput-new" data-provides="fileinput">
                                                        <div class="input-group input-small">
                                                            <div class="form-control uneditable-input input-fixed input-medium" data-trigger="fileinput">
                                                                <i class="fa fa-file fileinput-exists"></i>&nbsp;
                                                                <span class="fileinput-filename"  > </span>
                                                            </div>
                                                            <span class="input-group-addon btn default btn-file">
                                                                <span class="fileinput-new" > 上传文件 </span>
                                                                <span class="fileinput-exists" > 修改 </span>
                                                                <input type="file" name="..."> </span>
                                                            <a href="javascript:;" class="input-group-addon btn red fileinput-exists" data-dismiss="fileinput" > 删除 </a>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="priceView">{{price.fax}}</p>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
								</div>
							</form>
         				</div>

			
                       

				
                    
				
				
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