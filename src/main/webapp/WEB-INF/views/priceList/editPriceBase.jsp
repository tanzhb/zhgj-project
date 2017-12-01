<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- 采购订单基本信息 START -->

<div class="tab-content">
	<div class="tab-pane fade active in" id="tab_1_1">
	<div class="portlet-title" style="min-height: 30px;">
              <div class="tools" style="float:right" id="noprintdiv">
                        	<button  ng-show="priceListView"   class="btn purple  btn-sm btn-circle " ng-click="editPriceList()">
                                            <i class="fa fa-edit"></i> 编辑 </button>
                                <button   ng-hide="priceListEdit"   class="btn defualt  btn-sm btn-circle" ng-click="cancelEditPriceList()">
                                            <i class="fa fa-undo"></i> 取消 </button>
                                <button  ng-show="priceListAdd"   type="submit"   class="btn green  btn-sm btn-circle"   ng-click="savePriceList()">
                                            <i class="fa fa-save"></i> 保存 </button>
                         </div>
                   	</div>
         <div class="portlet-body form">
          <form  id="priceListForm"  >
								<div class="form-body">
									<div class="alert alert-danger display-hide">
                                               <button class="close" data-close="alert"></button>请先输入正确数据！</div>
								           <div class="row">
                                                             <div class="col-md-6">
											<div class="form-group">
                                                    <label class="control-label bold" for="priceNum"> <span class="required"> * </span>价格编号 :</label>
                                                    <div class="">
                                                        <input type="text" class="form-control" id="priceNum" name="priceNum" ng-model="priceList.priceNum"  ng-show="priceListAdd" />
                                                        <div class="form-control-focus"> </div>
                                                          <p class="control-label left" ng-show="priceListView">{{priceList.priceNum}}</p> 
                                                          <input type="hidden"  id="materielSerial" ng-model="priceList.materielSerial"  /><!--  存放物料流水号-->
                                                    </div>
                                            </div>
										</div>
										
                                                            <div class="col-md-6">
                                                                <div class="form-group">
                                                                <label class="control-label bold" for="priceDescribe"><!--  <span class="required"> * </span> -->描述 :</label>
                                                                    <div class="">
                                                                       <input type="text" class="form-control" placeholder=""  id="priceDescribe" name ="priceDescribe"  ng-show="priceListAdd" 
												ng-model="priceList.priceDescribe" > 
                                                                       <div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="priceListView">{{priceList.priceDescribe}}</p> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-md-6">
                                                                <div class="form-group">
                                                    <label class="control-label bold" for="materielNum"> <span class="required"> * </span>物料编号 :</label>
                                                    <div class="">
												<div class="input-group" ng-click="selectMateriel()" data-target="#basicMaterielInfo" data-toggle="modal" >
	                                                        <input type="text" class="form-control"   id="materielNum" name ="materielNum"  readonly  ng-show="priceListAdd"  
												ng-model="priceList.materielNum" /> 
	                                                        <span class="input-group-btn" style="vertical-align: top;"  ng-show="priceListAdd">
	                                                            <button class="btn default" type="button">
	                                                                <i class="fa fa-search"></i>
	                                                            </button>
	                                                        </span>
                                                         </div>
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="priceListView">{{priceList.materielNum}}</p> 
                                                                    </div>
                                                                  
                                                                </div>
                                                            </div>
                                                            <!--/span-->
                                                            <div class="col-md-6">
                                                            <div class="form-group">
                                                    <label class="control-label bold" for="priceListCategory"><!--  <span class="required"> * </span> -->物料名称 :</label>
                                                    <div class="">
                                                                    <input type="text" class="form-control"   id="materielName" name ="materielName"  ng-show="priceListAdd"  readonly="readonly"
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
                                                             <div class="form-group ">
                                                    <label class="control-label bold" for="address"> <!-- <span class="required"> * </span> -->规格型号:</label>
                                                    <div class="">
                                                                        <input type="text" class="form-control"   id="specifications" name ="specifications"  ng-show="priceListAdd"  readonly="readonly"
												ng-model="priceList.specifications" > 
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="priceListView">{{priceList.specifications}}</p> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <!--/span-->
                                                            <div class="col-md-6">
                                                            <div class="form-group">
                                                    <label class="control-label bold" for="owner"> <!-- <span class="required"> * </span> -->单位 :</label>
                                                    <div class="">
                                                                        <input type="text"  class="form-control" placeholder=""  id="unit" name ="unit"   ng-show="priceListAdd"  readonly="readonly"
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
                                                            <div class="form-group">
                                                    <label class="control-label bold" for="priceType"> <span class="required"> * </span>价格类型 :</label>
                                                    <div class="">
                                                                        <select class="form-control" id="priceType" name ="priceType" ng-model="priceList.priceType"   ng-show="priceListAdd"   disabled="disabled" >
                                                                          <option value=""></option>
                                                                        <option value="buyPrice"  ng-selected="buyOrSale.indexOf('buy')>-1">采购价格</option>
                                                                        <option value="salePrice"  ng-selected="buyOrSale.indexOf('sale')>-1">销售价格</option>
                                                                        </select>
                                                                           <p class="control-label left"   ng-show="priceListView" ng-if="buyOrSale.indexOf('buy')>-1" >采购价格</p>
                                                                        <p class="control-label left"  ng-show="priceListView"  ng-if="buyOrSale.indexOf('sale')>-1" >  销售价格</p>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <!--/span-->
                                                            <div class="col-md-6"  ng-if="buyOrSale.indexOf('sale')>-1">
                                                             <div class="form-group ">
                                                    <label class="control-label bold" for="admin"> <span class="required"> * </span>采购商 :</label>
                                                     <div class="">
                                                     <div   ng-show="priceListAdd">
                                                     <select class="form-control" data-live-search="true" data-size=""   id="buyComId"  name ="buyComId"    ng-model="priceList.buyComId">
                                                     <!-- <option  ng-repeat="op in buyCom " value="{{op.comId}}"  > {{op.comName}}</option> -->
                                                      <option value=""></option>
                                                      <option  ng-repeat="customer in customers" value="{{customer.comId}}" >{{customer.comName}}</option>
                                                    </select>
                                                    </div>
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="priceListView">{{priceList.buyComName}}</p>  
                                                                </div>
                                                            </div>
                                                            <!--/span-->
                                                        </div>
                                                        <div class="col-md-6"  ng-if="buyOrSale.indexOf('buy')>-1" >
                                                             <div class="form-group">
                                                    <label class="control-label bold" for="admin"> <span class="required"> * </span>供应商 :</label>
                                                    <div class="">
                                                    <div   ng-show="priceListAdd">
                                                     <select class="form-control" data-live-search="true" data-size=""   id="supplyComId" name ="supplyComId"     ng-model="priceList.supplyComId">
                                                       <!--  <option  ng-repeat="op in supplyCom " value="{{op.comId}}"  > {{op.comName}}</option> -->
                                                       <option value=""></option>
                                                   <option  ng-repeat="supplier in suppliers" value="{{supplier.comId}}" >{{supplier.comName}}</option>
                                                    </select></div>
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="priceListView">{{priceList.supplyComName}}</p>  
                                                                </div>
                                                            </div>
                                                            <!--/span-->
                                                        </div>
                                                        </div>
                                                         <div class="row">
                                                            <div class="col-md-6">
                                                            <div class="form-group ">
                                                    <label class="control-label bold" for="currency"> <span class="required"> * </span>币种 :</label>
                                                    <div class="">
                                                                        <select class="form-control" id="currency" name ="currency" ng-model="priceList.currency"  ng-show="priceListAdd"  >
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
                                                              <div class="form-group">
                                                    <label class="control-label bold" for="rate"> <span class="required"> * </span>税率(%):</label>
                                                    <div class="">
                                                                       <input type="text"  class="form-control" placeholder=""  id="rate" name ="rate"  ng-show="priceListAdd" 
												ng-model="priceList.rate"  ng-keyup="clearNoNumPoint(priceList,'inclusivePrice')">
                                                                         <div class="form-control-focus"></div>
                                                                        <!--   <span class="help-block">请输入百分比数</span> -->
                                                                        <p class="control-label left" ng-show="priceListView">{{priceList.rate}}</p> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <!--/span-->
                                                        </div>
                                                         <div class="row">
                                                          <div class="col-md-6">
                                                           <div class="form-group">
                                                    <label class="control-label bold" for="inclusiveprice"> <!-- <span class="required"> * </span> -->含税价格 :</label>
                                                    <div class="">
                                                                        <input type="text"   class="form-control" placeholder=""  id="inclusivePrice"    data-unitprice="{{priceList.unitPrice}}"   name ="inclusivePrice"  ng-show="priceListAdd" 
												ng-model="priceList.inclusivePrice"   ng-keyup="clearNoNumPoint(priceList,'inclusivePrice')">
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="priceListView">{{priceList.inclusivePrice |currency:'￥'}}</p>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="col-md-6">
                                                             <div class="form-group">
                                                    <label class="control-label bold" for="priceList"> 不含税单价 :</label>
                                                    <div class="">
                                                                        <input type="text"  class="form-control" placeholder=""  id="unitPrice" name ="unitPrice"  ng-show="priceListAdd" 
												ng-model="priceList.unitPrice" disabled="disabled">
												
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="priceListView">{{priceList.unitPrice |currency:'￥'}}</p>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                          <div class="col-md-6">
                                                           <div class="form-group">
                                                   <label class="control-label bold" for="topprice"> 最高限价 :</label>
                                                    <div class="">
                                                                           <input type="text"  class="form-control" placeholder=""  id="topPrice" name ="topPrice"   onkeyup="isNumberWithTwo(this)"   ng-show="priceListAdd" 
												ng-model="priceList.topPrice"/>
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="priceListView">{{priceList.topPrice |currency:'￥'}}</p>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                           <div class="col-md-6">
                                                           <div class="form-group">
                                                    <label class="control-label bold" for="floorprice"> 最低限价 :</label>
                                                    <div class="">
                                                                        <input type="text"   class="form-control" placeholder=""  id="floorPrice" name ="floorPrice"  ng-show="priceListAdd"   onkeyup="isNumberWithTwo(this)"  data-topprice="{{priceList.topPrice}}"  
												ng-model="priceList.floorPrice" />
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="priceListView">{{priceList.floorPrice |currency:'￥'}}</p>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-md-6">
                                                             <div class="form-group">
                                                    <label class="control-label bold" for="isLadderPrice"> 是否阶梯单价 :</label>
                                                    <div class="">
                                                                         <div class="icheck-inline"  ng-show="priceListAdd"   >
                                                                                <label  ><input type="checkbox"     id="isLadderPriceCheck"    name ="isLadderPrice"  class="icheck"    ng-checked="priceList.isLadderPrice=='1'">是 </label>
                                                                        </div>
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="priceListView"   ng-if="priceList.isLadderPrice=='1'">是</p>
                                                                         <p class="control-label left" ng-show="priceListView"   ng-if="priceList.isLadderPrice=='0'">否</p>
                                                                         <input type="hidden"   value="{{priceList.isLadderPrice}}"  id="hideIsLadderPrice"/>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                           <div class="col-md-6"  ng-if="isChecked">
                                                           <div class="form-group">
                                                    <label class="control-label bold" for="ladderType"><span class="required"> * </span> 阶梯类型 :</label>
                                                    <div class="">
                                                                       <select class="form-control" id="ladderType" name ="ladderType" ng-model="priceList.ladderType"  ng-show="priceListAdd"  >
                                                                          <option value=""></option>
                                                                        <option value="oneStagePrice">单笔阶梯单价</option>
                                                                        <option value="moreStagePrice">累计阶梯单价</option>
                                                                        </select>
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="priceListView"  ng-if="priceList.ladderType=='oneStagePrice'" >单笔阶梯单价 </p>
                                                                        <p class="control-label left" ng-show="priceListView"   ng-if="priceList.ladderType=='moreStagePrice'" >  累计阶梯单价</p>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
			                         <div class="row"  ng-show="isChecked">
                                                         <div class="portlet-title">
                            <div class="actions"  style="float: right;">
                             
                                <button   class="btn purple  btn-sm btn-circle"         ng-show="ladderpriceView"       ng-click="editLadderPrice()"><!--ng-show="ladderpriceView"   -->
                                            <i class="fa fa-edit"></i> 编辑 </button>
                                <button   class="btn defualt  btn-sm btn-circle"       ng-hide="ladderpriceEdit"            ng-click="cancelEditLadderPrice()"><!--  ng-show="ladderpriceEdit"  -->
                                            <i class="fa fa-undo"></i> 取消 </button>
                                <button   class="btn green  btn-sm btn-circle "       ng-hide="ladderpriceAdd"     ng-click="saveLadderPrice()"><!--  ng-show="ladderpriceAdd"  -->
                                            <i class="fa fa-save"></i> 保存 </button>
                            </div>
                        </div></br></br>
                                                             <!--    <div class="portlet-body form"> -->
                            <!--  <form  id="ladderpriceForm" class="page-repeater form-horizontal">  -->
								<div class="form-body" data-repeater-list="group-a"  >
									<div  class="row" data-repeater-item repeat-done="repeatDone()"   ng-repeat="ladderprice in ladderprices  track by $index"  style="margin-top:5px"> 
										<div class="col-md-3">
											<div class="form-group">
											<div class="col-md-2"> </div>
											<div class="col-md-5"> {{$index+1}}  :数量范围</div>
												<div class="col-md-5 input-icon right">
													<input  ng-hide="ladderpriceAdd" type="text" ng-model="ladderprice.countStart" name="countStart"   id="countStart"  onblur="judge()"
														class="form-control" placeholder="">	
														<div class="form-control-focus"> </div>
														<label   ng-show="ladderpriceView"  class="c_edit" >{{ladderprice.countStart}}</label>
												</div>
											</div>
										</div>
						
										<div class="col-md-2">
											<div class="form-group">
												<div class="col-md-7">
													<input  ng-hide="ladderpriceAdd" type="text" ng-model="ladderprice.countEnd" name="countEnd"  id="countEnd"
														class="form-control" placeholder=""> 
														<div class="form-control-focus"> </div>
														<label   ng-show="ladderpriceView"  class="c_edit" >{{ladderprice.countEnd}}</label>
												</div>
											</div>
										</div>
										<div class="col-md-2">
											<div class="form-group">
											<div class="col-md-4"> 价格 :</div>
												<div class="col-md-6 input-icon right">
													<input  ng-hide="ladderpriceAdd" type="text" ng-model="ladderprice.price" name="ladderPrice"   id="ladderPrice"
														class="form-control" placeholder="">
														<div class="form-control-focus"> </div>
														<label   ng-show="ladderpriceView"  class="c_edit" >{{ladderprice.price |currency:'￥'}}</label>
												</div>
											</div>
										</div>
										<div class="col-md-2">
											<div class="form-group">
											<div class="col-md-5"> 含税价格 :</div>
												<div class="col-md-7 input-icon right">
													<input  ng-hide="ladderpriceAdd" type="text" ng-model="ladderprice.inclusivePrice" name="ladderlnclusivePrice"   id="inclusivePrice"   data-ladderprice="{{ladderprice.price}}" 
														class="form-control" placeholder="">
														<div class="form-control-focus"> </div>
														<label   ng-show="ladderpriceView"  class="c_edit" >{{ladderprice.inclusivePrice |currency:'￥'}}</label>
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
										 </br>
									</div>
									
								</div>
								<div class="form-actions right" ng-hide="ladderpriceAdd">
									<a href="javascript:;" data-repeater-create class="btn blue btn-sm" ng-click="addRepeat()" >
                                            <i class="fa fa-plus"></i> 增加
                                     </a>
                                </div>
                                </div>
         				
         			<!-- 	</div> 
                         </div>  -->
                                                      
                                                    
                                                        <div class="row">
                                                            <div class="col-md-6">
                                                             <div class="form-group">
                                                    <label class="control-label bold" for="priceEffectiveDate"> <span class="required"> * </span>价格生效期 :</label>
                                                    <div class="">
                                                                        <input class="form-control form-control-inline date-picker"  ng-show="priceListAdd"      data-date-format="yyyy-mm-dd"    data-date-viewmode="years"  id="priceEffectiveDate"  name="priceEffectiveDate"    ng-model="priceList.priceEffectiveDate" size="16" type="text" />
                                                     <div class="form-control-focus"> </div>
                                      <!-- <span class="help-block">请选择价格生效期</span>  -->
                                                                        <p class="control-label left" ng-show="priceListView">{{priceList.priceEffectiveDate}}</p>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                           <div class="col-md-6">
                                                           <div class="form-group">
                                                    <label class="control-label bold" for="priceExpirationDate"> <span class="required"> * </span>价格失效期 :</label>
                                                    <div class="">
                                                     <input class="form-control form-control-inline  date-picker"   ng-show="priceListAdd"     data-date-format="yyyy-mm-dd"    data-date-viewmode="years"  id="priceExpirationDate"  name="priceExpirationDate"   ng-model="priceList.priceExpirationDate"  size="16" type="text" />
                                                     <div class="form-control-focus"> </div>
                                      <!-- <span class="help-block">请选择价格失效期</span> -->
                                      <p class="control-label left" ng-show="priceListView">{{priceList.priceExpirationDate}}</p>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-md-6">
                                                             <div class="form-group">
                                                    <label class="control-label bold" for="tel"><!--  <span class="required"> * </span> -->备注 :</label>
                                                    <div class="">
                                                                        <input type="text"  class="form-control" placeholder=""  id="remark" name ="remark"  ng-show="priceListAdd" 
												ng-model="priceList.remark">
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="priceListView">{{priceList.remark}}</p>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                           <div class="col-md-6">
                                                           <div class="form-group">
							<label class="control-label bold" for="file">附件
							</label>
							<div class="">
							<div class="">
				                                         <div ng-show="priceListAdd"   ng-if="priceList.file==null||priceList.file==''"  class="fileinput fileinput-new" data-provides="fileinput">
	                                                        <span class="btn blue btn-outline btn-file">
	                                                            <span class="fileinput-new">上传附件</span>
	                                                            <span class="fileinput-exists">更改</span>
	                                                            <input type="file" name="..." nv-file-select uploader="uploader" onchange="angular.element(this).scope().up(this.files[0])" ng-model="priceList" ng-click="uploadFile(priceList)" > </span>
	                                                        <span class="fileinput-filename">{{priceList.file.substring(priceList.file.indexOf("_")+1)}}</span> &nbsp;
	                                                        <a href="javascript:;" class="close fileinput-exists" ng-click="removefile(priceList)" data-dismiss="fileinput"> </a>
	                                                    </div>
				                                         <div ng-show="priceListAdd"   ng-if="priceList.file!=null&&priceList.file!=''"  class="fileinput fileinput-exists" data-provides="fileinput">
	                                                        <span class="btn blue btn-outline btn-file">
	                                                            <span class="fileinput-new">上传附件</span>
	                                                            <span class="fileinput-exists">更改</span>
	                                                            <input type="file" name="..." nv-file-select uploader="uploader" onchange="angular.element(this).scope().up(this.files[0])" ng-model="companyQualification" ng-click="uploadFile(companyQualification)" > </span>
	                                                        <span class="fileinput-filename">{{priceList.file.substring(priceList.file.indexOf("_")+1)}}</span> &nbsp;
	                                                        <a href="javascript:;" class="close fileinput-exists"  ng-click="removefile(priceList)" data-dismiss="fileinput"> </a>
	                                                    </div>
                                                    	<label   ng-show="priceListView" ng-if="priceList.file==null||priceList.file==''" class="c_edit" >未上传附件</label>
                                                    	<label   ng-show="priceListView" ng-if="priceList.file!=null&&priceList.file!=''" class="c_edit" ><a href="javascript:;" title="下载附件" ng-click="downloadFile(priceList)">{{priceList.file.substring(priceList.file.indexOf("_")+1)}}</a></label>
												</div>
							</div>
						</div>
                                                        </div>
								</div>
								</div>
							</form>
             
            </div>   
	</div>
	

         </div>   
        
     
<!-- 采购订单基本信息 end -->