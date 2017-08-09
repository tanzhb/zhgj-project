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
            <a ui-sref="priceList">价格信息</a>
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
                                <button  ng-show="priceListView"    class="btn blue  btn-outline  btn-sm " ng-click="editPriceList()">
                                            <i class="fa fa-edit"></i> 编辑 </button>
                                <button   ng-show="priceListEdit"   class="btn red  btn-outline  btn-sm " ng-click="cancelEditpriceList()">
                                            <i class="fa fa-undo"></i> 取消 </button>
                                <button  ng-hide="priceListAdd"   type="submit"   class="btn blue  btn-outline  btn-sm " ng-click="savePriceList()">
                                            <i class="fa fa-save"></i> 保存 </button>
                            </div>
                        </div>
                        <div class="portlet-body form">
                            <form  id="priceListForm" class="form-horizontal" >
								<div class="form-body">
									<div class="alert alert-danger display-hide">
                                                <button id="priceListTips" class="close" data-close="alert"></button>请先输入正确数据！</div>
								           <div class="row">
                                                             <div class="col-md-6">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="priceListNum"> <span class="required"> * </span>价格编号 :</label>
                                                    <div class="col-md-8">
                                                        <input type="text" class="form-control" id="priceListNum" name="priceListNum" ng-model="priceList.priceListNum"  ng-hide="priceListAdd" />
                                                        <div class="form-control-focus"> </div>
                                                          <p class="control-label left" ng-show="priceListView">{{priceList.priceListNum}}</p> 
                                                          <input type="hidden"  id="materielSerial" ng-model="priceList.materielSerial"  /><!--  存放物料流水号-->
                                                    </div>
                                            </div>
										</div>
                                                            <div class="col-md-6">
                                                                <div class="form-group form-md-line-input">
                                                                <label class="col-md-4 control-label" for="priceListName"><!--  <span class="required"> * </span> -->描述 :</label>
                                                                    <div class="col-md-8">
                                                                       <input type="text" class="form-control" placeholder=""  id="priceListDescribe" name ="priceListDescribe"  ng-hide="priceListAdd" 
												ng-model="priceList.priceListDescribe" > 
                                                                       <div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="priceListView">{{priceList.priceListDescribe}}</p> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-md-6">
                                                                <div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="materielNum"> <span class="required"> * </span>物料编号 :</label>
                                                    <div class="col-md-6">
                                                     <input type="text" class="form-control"   id="materielNum" name ="materielNum"  ng-hide="priceListAdd"  
												ng-model="priceList.materielNum" /> <!-- <span class="input-inline-btn">
                                                            <button class="btn default" type="button">
                                                                <i class="fa fa-search"></i>
                                                            </button>
                                                        </span> --><!-- <span class="fa fa-search"></span> -->
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="priceListView">{{priceList.materielNum}}</p> 
                                                                    </div>
                                                                    <div class="col-md-1"><span class="input-inline-btn"  ng-hide="priceListAdd"  >
                                                            <button class="btn default" type="button">
                                                                <i class="fa fa-search"></i>
                                                            </button>
                                                        </span></div>
                                                                </div>
                                                            </div>
                                                            <!--/span-->
                                                            <div class="col-md-6">
                                                            <div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="priceListCategory"><!--  <span class="required"> * </span> -->物料名称 :</label>
                                                    <div class="col-md-8">
                                                                    <input type="text" class="form-control"   id="materielName" name ="materielName"  ng-hide="priceListAdd"  readonly="readonly"
												ng-model="priceList.materielName" > 
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="priceListView">{{priceList.materielName}}</p> 
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
                                                                        <input type="text" class="form-control"   id="specifications" name ="specifications"  ng-hide="priceListAdd"  readonly="readonly"
												ng-model="priceList.specifications" > 
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="priceListView">{{priceList.specifications}}</p> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <!--/span-->
                                                            <div class="col-md-6">
                                                            <div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="owner"> <!-- <span class="required"> * </span> -->单位 :</label>
                                                    <div class="col-md-8">
                                                                        <input type="text"  class="form-control" placeholder=""  id="unit" name ="unit"   ng-hide="priceListAdd"  readonly="readonly"
												ng-model="priceList.unit" > 
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="priceListView">{{priceList.unit}}</p> 
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
                                                                        <select class="form-control" id="priceType" name ="priceType" ng-model="priceList.priceType"  ng-hide="priceListAdd"  >
                                                                          <option value=""></option>
                                                                        <option value="采购价格">采购价格</option>
                                                                        <option value="销售价格">销售价格</option>
                                                                        </select>
                                                                         <div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="priceListView">{{priceList.priceType}}</p> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <!--/span-->
                                                            <div class="col-md-6"  ng-show="priceList.priceType=='销售价格'">
                                                             <div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="admin"> <span class="required"> * </span>采购商 :</label>
                                                    <div class="col-md-8">
                                                                        <input type="text"  class="form-control" placeholder=""  id="buyComId" name ="buyComId"  ng-hide="priceListAdd" 
												ng-model="priceList.buyComId" >
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="priceListView">{{priceList.buyComName}}</p>  
                                                                </div>
                                                            </div>
                                                            <!--/span-->
                                                        </div>
                                                        <div class="col-md-6"  ng-show="priceList.priceListType=='采购价格'"">
                                                             <div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="admin"> <span class="required"> * </span>供应商 :</label>
                                                    <div class="col-md-8">
                                                                        <input type="text"  class="form-control" placeholder=""  id="supplyComId" name ="supplyComId"  ng-hide="priceListAdd" 
												ng-model="priceList.supplyComId" >
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="priceListView">{{priceList.supplyComName}}</p>  
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
                                                                        <select class="form-control" id="currency" name ="currency" ng-model="priceList.currency"  ng-hide="priceListAdd"  >
                                                                          <option value=""></option>
                                                                        <option value="RMB">RMB</option>
                                                                        <option value="USD">USD</option>
                                                                        </select>
                                                                         <div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="priceListView">{{priceList.currency}}</p> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <!--/span-->
                                                            <div class="col-md-6">
                                                              <div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="rate"> <span class="required"> * </span>税率 :</label>
                                                    <div class="col-md-8">
                                                                       <input type="text"  class="form-control" placeholder=""  id="rate" name ="rate"  ng-hide="priceListAdd" 
												ng-model="priceList.rate">
                                                                         <div class="form-control-focus"></div>
                                                                          <span class="help-block">请输入百分比数</span>
                                                                        <p class="control-label left" ng-show="priceListView">{{priceList.rate}}</p> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <!--/span-->
                                                        </div>
                                                         <div class="row">
                                                            <div class="col-md-6">
                                                             <div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="priceList"> <span class="required"> * </span>单价 :</label>
                                                    <div class="col-md-8">
                                                                        <input type="text"  class="form-control" placeholder=""  id="price" name ="price"  ng-hide="priceListAdd" 
												ng-model="priceList.priceList">
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="priceListView">{{priceList.price}}</p>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                           <div class="col-md-6">
                                                           <div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="inclusivepriceList"> <!-- <span class="required"> * </span> -->含税价格 :</label>
                                                    <div class="col-md-8">
                                                                        <input type="text"   class="form-control" placeholder=""  id="inclusiveprice" name ="inclusiveprice"  ng-hide="priceListAdd" 
												ng-model="priceList.inclusivepriceList" >
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="priceListView">{{priceList.inclusiveprice}}</p>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-md-6">
                                                             <div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="toppriceList"> <!-- <span class="required"> * </span> -->最高限价 :</label>
                                                    <div class="col-md-8">
                                                                        <input type="text"  class="form-control" placeholder=""  id="topprice" name ="topprice"  ng-hide="priceListAdd" 
												ng-model="priceList.topprice">
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="priceListView">{{priceList.topprice}}</p>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                           <div class="col-md-6">
                                                           <div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="floorpriceList"> <!-- <span class="required"> * </span> -->最底限价 :</label>
                                                    <div class="col-md-8">
                                                                        <input type="text"   class="form-control" placeholder=""  id="floorprice" name ="floorprice"  ng-hide="priceListAdd" 
												ng-model="priceList.floorprice" >
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="priceListView">{{priceList.floorprice}}</p>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-md-6">
                                                             <div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="isLadderpriceList"> <!-- <span class="required"> * </span> -->是否阶梯单价 :</label>
                                                    <div class="col-md-8">
                                                                         <div class="icheck-inline">
                                                                            <label>
                                                                                <input type="checkbox"    ng-hide="priceListAdd"   ng-model="priceList.isLadderprice"    ng-click="showOrHide()" name ="isLadderprice"  class="icheck">是 </label>
                                                                            <label>
                                                                        </div>
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="priceListView">{{priceList.isLadderprice}}</p>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                           <div class="col-md-6">
                                                           <div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="ladderType"> <!-- <span class="required"> * </span> -->阶梯类型 :</label>
                                                    <div class="col-md-8">
                                                                       <select class="form-control" id="ladderType" name ="ladderType" ng-model="priceList.ladderType"  ng-hide="priceListAdd"  >
                                                                          <option value=""></option>
                                                                        <option value="单笔阶梯单价">单笔阶梯单价</option>
                                                                        <option value="累计阶梯单价">累计阶梯单价</option>
                                                                        </select>
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="priceListView">{{priceList.ladderType}}</p>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="row"  ng-show="isChecked"><!--  -->
                                                         <div class="portlet-title">
                            <div class="actions"  style="float: right;">
                             
                                <button   class="btn blue btn-outline  btn-sm " ng-click="editladderprice()">
                                            <i class="fa fa-edit"></i> 编辑 </button>
                                <button   class="btn red  btn-outline  btn-sm " ng-click="cancelEditLadderprice()">
                                            <i class="fa fa-undo"></i> 取消 </button>
                                <button   class="btn blue btn-outline  btn-sm " ng-click="saveladderprice()">
                                            <i class="fa fa-save"></i> 保存 </button>
                            </div>
                        </div></br></br>
                                                               <div class="portlet-body form">
                            <form  id="qualificationForm" class="page-repeater form-horizontal">
								<div class="form-body" data-repeater-list="group-a"  >
									<div  class="row" data-repeater-item  ng-repeat="ladderprice in ladderprices  track by $index" repeat-done="repeatDone()"  > 
										<div class="col-md-3">
											<div class="form-group">
											<div class="col-md-5"> {{$index+1}}  :数量范围</div>
												<div class="col-md-7 input-icon right">
													<input  ng-hide="ladderpriceAdd" type="text" ng-model="ladderprice.countStart" name="countStart"
														class="form-control" placeholder="起始数量"><span
														class="help-block"></span>
														<label   ng-show="ladderpriceListView"  class="c_edit" >{{ladderprice.countStart}}</label>
												</div>
											</div>
										</div>
						
										<!--/span-->
										<div class="col-md-2">
											<div class="form-group">
												<div class="col-md-12">
													<input  ng-hide="ladderpriceAdd" type="text" ng-model="ladderprice.countEnd" name="countEnd"
														class="form-control" placeholder="截止数量"> <span
														class="help-block"></span>
														<label   ng-show="ladderpriceView"  class="c_edit" >{{ladderprice.countEnd}}</label>
												</div>
											</div>
										</div>
										<div class="col-md-3">
											<div class="form-group">
											<div class="col-md-4"> 价格 :</div>
												<div class="col-md-8 input-icon right">
													<input  ng-hide="ladderpriceAdd" type="text" ng-model="ladderprice.countStart" name="countStart"
														class="form-control" placeholder="价格"><span
														class="help-block"></span>
														<label   ng-show="ladderpriceView"  class="c_edit" >{{ladderprice.countStart}}</label>
												</div>
											</div>
										</div>
										<div class="col-md-3">
											<div class="form-group">
											<div class="col-md-5"> 含税价格 :</div>
												<div class="col-md-7 input-icon right">
													<input  ng-hide="ladderpriceAdd" type="text" ng-model="ladderprice.countStart" name="countStart"
														class="form-control" placeholder="含税价格"><span
														class="help-block"></span>
														<label   ng-show="ladderpriceView"  class="c_edit" >{{ladderprice.countStart}}</label>
												</div>
											</div>
										</div>
										<div class="col-md-1">
											<div class="form-group">
												<div class="col-md-12">
													 <a href="javascript:;"  class="btn red btn-sm" ng-hide="ladderpriceAdd" ng-click="deleteRepeat()">
                                            			<i class="fa fa-close"></i> 
                                     				</a>
												</div>
											</div>
										</div>
										<!--/span--> 
									</div>
									<!-- /row -->
								</div>
								<div class="form-actions right" ng-hide="ladderpriceAdd">
									<a href="javascript:;" data-repeater-create class="btn blue btn-sm" ng-click="addRepeat()" >
                                            <i class="fa fa-plus"></i> 增加
                                     </a>
                                </div>
							</form>
         				</div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-md-6">
                                                             <div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="priceEffectiveDate"> <span class="required"> * </span>价格生效期 :</label>
                                                    <div class="col-md-8">
                                                                        <input class="form-control form-control-inline date-picker"    data-date-format="yyyy-mm-dd"  name="priceEffectiveDate"  size="16" type="text" value="" />
                                                     <div class="form-control-focus"> </div>
                                      <!-- <span class="help-block">请选择价格生效期</span> -->
                                                                        <p class="control-label left" ng-show="priceListView">{{priceList.priceEffectiveDate}}</p>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                           <div class="col-md-6">
                                                           <div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="priceExpirationDate"> <span class="required"> * </span>价格失效期 :</label>
                                                    <div class="col-md-8">
                                                     <input class="form-control form-control-inline  date-picker"   data-date-format="yyyy-mm-dd"   name="priceExpirationDate"   size="16" type="text" value="" />
                                                     <div class="form-control-focus"> </div>
                                      <!-- <span class="help-block">请选择价格失效期</span> -->
                                      <p class="control-label left" ng-show="priceListView">{{priceList.priceEffectiveDate}}</p>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-md-6">
                                                             <div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="tel"><!--  <span class="required"> * </span> -->备注 :</label>
                                                    <div class="col-md-8">
                                                                        <input type="text"  class="form-control" placeholder=""  id="remark" name ="remark"  ng-hide="priceListAdd" 
												ng-model="priceList.remark">
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="priceListView">{{priceList.remark}}</p>
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
                                                                        <p class="control-label left" ng-show="priceListView">{{priceList.file}}</p>
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