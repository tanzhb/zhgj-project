<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- <div class="row">-->
    <div class="col-md-12"> 
        <!-- BEGIN EXAMPLE TABLE PORTLET-->
             <div class="portlet-body">
				<div class="portlet light ">
                        <div class="portlet-title">
                           <div class="caption"><span ng-if="buyOrSale.indexOf('buy')>-1">采购</span><span ng-if="buyOrSale.indexOf('sale')>-1">销售</span>价格信息</div>  
                        </div>
                     <div class="tab-content">
				<div class="tab-pane fade active in" id="tab_1_1"> 
                        <div class="portlet-body form">
								<div class="form-body">
									<div class="alert alert-danger display-hide">
                                               <button class="close" data-close="alert"></button>请先输入正确数据！</div>
								           <div class="row">
                                                             <div class="col-md-6">
											<div class="form-group">
                                                    <label class="control-label bold" for="priceNum">价格编号 :</label>
                                                    <div class="">
                                                        <div class="form-control-focus"> </div>
                                                          <p class="control-label left" >{{priceList.priceNum}}</p> 
                                                    </div>
                                            </div>
										</div>
										
                                                            <div class="col-md-6">
                                                                <div class="form-group">
                                                                <label class="control-label bold" for="priceDescribe"><!--  <span class="required"> * </span> -->描述 :</label>
                                                                    <div class="">
                                                                       <div class="form-control-focus"> </div>
                                                                        <p class="control-label left" >{{priceList.priceDescribe}}</p> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-md-6">
                                                                <div class="form-group">
                                                    <label class="control-label bold" for="materielNum"> 物料编号 :</label>
                                                    <div class="">
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" >{{priceList.materielNum}}</p> 
                                                                    </div>
                                                                  
                                                                </div>
                                                            </div>
                                                            <!--/span-->
                                                            <div class="col-md-6">
                                                            <div class="form-group">
                                                    <label class="control-label bold" for="priceListCategory"><!--  <span class="required"> * </span> -->物料名称 :</label>
                                                    <div class="">
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" >{{priceList.materielName}}</p> 
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
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" >{{priceList.specifications}}</p> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <!--/span-->
                                                            <div class="col-md-6">
                                                            <div class="form-group">
                                                    <label class="control-label bold" for="owner"> <!-- <span class="required"> * </span> -->单位 :</label>
                                                    <div class="">
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" >{{priceList.unit}}</p> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <!--/span-->
                                                        </div>
                                                         <div class="row">
                                                            <div class="col-md-6">
                                                            <div class="form-group">
                                                    <label class="control-label bold" for="priceType"> 价格类型 :</label>
                                                    <div class="">
                                                                           <p class="control-label left"  ng-if="buyOrSale.indexOf('buy')>-1" >采购价格</p>
                                                                        <p class="control-label left"   ng-if="buyOrSale.indexOf('sale')>-1" >  销售价格</p>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <!--/span-->
                                                            <div class="col-md-6"  ng-if="buyOrSale.indexOf('sale')>-1">
                                                             <div class="form-group ">
                                                    <label class="control-label bold" for="admin"> 采购商 :</label>
                                                     <div class="">
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" >{{priceList.buyComName}}</p>  
                                                                </div>
                                                            </div>
                                                            <!--/span-->
                                                        </div>
                                                        <div class="col-md-6"  ng-if="buyOrSale.indexOf('buy')>-1" >
                                                             <div class="form-group">
                                                    <label class="control-label bold" for="admin"> 供应商 :</label>
                                                    <div class="">
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" >{{priceList.supplyComName}}</p>  
                                                                </div>
                                                            </div>
                                                            <!--/span-->
                                                        </div>
                                                        </div>
                                                         <div class="row">
                                                            <div class="col-md-6">
                                                            <div class="form-group ">
                                                    <label class="control-label bold" for="currency"> 币种 :</label>
                                                    <div class="">
                                                                         <div class="form-control-focus"> </div>
                                                                        <p class="control-label left" >{{priceList.currency}}</p> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <!--/span-->
                                                            <div class="col-md-6">
                                                              <div class="form-group">
                                                    <label class="control-label bold" for="rate"> 税率(%) :</label>
                                                    <div class="">
                                                                         <div class="form-control-focus"></div>
                                                                        <!--   <span class="help-block">请输入百分比数</span> -->
                                                                        <p class="control-label left" >{{priceList.rate}}</p> 
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
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" >{{priceList.inclusivePrice |currency:'￥'}}</p>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="col-md-6">
                                                             <div class="form-group">
                                                    <label class="control-label bold" for="priceList"><!--  <span class="required"> * </span> -->不含税单价 :</label>
                                                    <div class="">
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" >{{priceList.unitPrice |currency:'￥'}}</p>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                          <div class="col-md-6">
                                                           <div class="form-group">
                                                   <label class="control-label bold" for="topprice"> 最高限价 :</label>
                                                    <div class="">
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" >{{priceList.topPrice |currency:'￥'}}</p>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                           <div class="col-md-6">
                                                           <div class="form-group">
                                                    <label class="control-label bold" for="floorprice"> 最低限价 :</label>
                                                    <div class="">
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" >{{priceList.floorPrice |currency:'￥'}}</p>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-md-6">
                                                             <div class="form-group">
                                                    <label class="control-label bold" for="isLadderPrice"> 是否阶梯单价 :</label>
                                                    <div class="">
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left"   ng-if="priceList.isLadderPrice=='1'">是</p>
                                                                         <p class="control-label left"  ng-if="priceList.isLadderPrice=='0'">否</p>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                           <div class="col-md-6"  ng-if="priceList.isLadderPrice=='1'">
                                                           <div class="form-group">
                                                    <label class="control-label bold" for="ladderType"> 阶梯类型 :</label>
                                                    <div class="">
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left"  ng-if="priceList.ladderType=='oneStagePrice'" >单笔阶梯单价 </p>
                                                                        <p class="control-label left"   ng-if="priceList.ladderType=='moreStagePrice'" >  累计阶梯单价</p>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                             </div>          
			                         <div class="row"  ng-if="priceList.isLadderPrice=='1'">
                                                         <div class="portlet-title">
                            <div class="actions"  style="float: right;">
                        </div></br></br>
                                                             <!--    <div class="portlet-body form"> -->
                            <!--  <form  id="ladderpriceForm" class="page-repeater form-horizontal">  -->
								<div class="form-body" data-repeater-list="group-a"  >
									<div  class="row" data-repeater-item  ng-repeat="ladderprice in ladderprices  track by $index" repeat-done="repeatDone()"   style="margin-top:5px"> 
										<div class="col-md-3">
											<div class="form-group">
											<div class="col-md-2"> </div>
											<div class="col-md-5"> {{$index+1}}  :数量范围</div>
												<div class="col-md-5 input-icon right">
												<div class="form-control-focus"> </div>
														<label    class="c_edit" >{{ladderprice.countStart}}</label>
												</div>
											</div>
										</div>
						
										<div class="col-md-2">
											<div class="form-group">
												<div class="col-md-7">
												<div class="form-control-focus"> </div>
														<label   class="c_edit" >{{ladderprice.countEnd}}</label>
												</div>
											</div>
										</div>
										<div class="col-md-2">
											<div class="form-group">
											<div class="col-md-4"> 价格 :</div>
												<div class="col-md-6 input-icon right">
												<div class="form-control-focus"> </div>
														<label   class="c_edit" >{{ladderprice.price |currency:'￥'}}</label>
												</div>
											</div>
										</div>
										<div class="col-md-2">
											<div class="form-group">
											<div class="col-md-5"> 含税价格 :</div>
												<div class="col-md-7 input-icon right">
														<div class="form-control-focus"> </div>
														<label   ng-show="ladderpriceView"  class="c_edit" >{{ladderprice.inclusivePrice |currency:'￥'}}</label>
												</div>
											</div>
										</div>
									</div>
									
								</div>
                                </div>
         				</div>
         			<!-- 	</div> 
                         </div>  -->
                                                        </div> 
                                                        </div> 
                                                        <div class="row">
                                                            <div class="col-md-6">
                                                             <div class="form-group">
                                                    <label class="control-label bold" for="priceEffectiveDate"> 价格生效期 :</label>
                                                    <div class="">
                                                     <div class="form-control-focus"> </div>
                                      <!-- <span class="help-block">请选择价格生效期</span>  -->
                                                                        <p class="control-label left" >{{priceList.priceEffectiveDate}}</p>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                           <div class="col-md-6">
                                                           <div class="form-group">
                                                    <label class="control-label bold" for="priceExpirationDate">价格失效期 :</label>
                                                    <div class="">
                                                     <div class="form-control-focus"> </div>
                                      <!-- <span class="help-block">请选择价格失效期</span> -->
                                      <p class="control-label left" >{{priceList.priceExpirationDate}}</p>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-md-6">
                                                             <div class="form-group">
                                                    <label class="control-label bold" for="tel"><!--  <span class="required"> * </span> -->备注 :</label>
                                                    <div class="">
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" >{{priceList.remark}}</p>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                           <div class="col-md-6">
                                                           <div class="form-group">
							<label class="control-label bold" for="file">附件
							</label>
							<div class="">
							<div class="">
                          <label   ng-if="priceList.file==null||priceList.file==''" class="c_edit" >未上传附件</label>
                          <label ng-if="priceList.file!=null&&priceList.file!=''" class="c_edit" ><a href="javascript:;" title="下载附件" ng-click="downloadFile(priceList)">{{priceList.file.substring(priceList.file.indexOf("_")+1)}}</a></label>
												</div>
							</div>
						</div>
                                                        </div>
								</div>
							<!-- </form> -->
         				</div>

  <div class="portlet-title"><!-- 使用采购商 START -->
                            <div class="caption">使用采购商</div>
                            <div class="actions">
                            </div>
                        </div>
                          <div class="portlet-body form">
                                    <div class="table-scrollable">
                                        <table class="table table-bordered table-hover"  >
                                            <thead>
                                                <tr>
                                                    <th>客户编号</th>
                                                    <th>客户名称</th>
                                                  
                                                </tr>
                                            </thead>
                                            <tbody  ng-if="buyComs.length==0">
			                                             	<tr>
			                                                    <td colspan="2" align="center" >暂无数据</td>
			                                                </tr>
			                                </tbody>
                                            <tbody ng-repeat="buycom in buyComs">
                                                <tr >
                                                    <td  class="col-md-1">{{buycom.comNum}}</td>
                                                    <td class="col-md-1">{{buycom.buyComName}}</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div><!-- 使用采购商 END-->
				
                    <div class="portlet-title"><!-- 价格日志START-->
                            <div class="caption">价格日志</div>
                            <div class="actions">
                            </div>
                        </div>
                          <div class="portlet-body form">
                                    <div class="table-scrollable">
                                        <table class="table table-bordered table-hover">
                                            <thead>
                                                <tr>
                                                    <th>版本号</th>
                                                    <th>生效日期</th>
                                                     <th>币种</th>
                                                    <th>价格</th>
                                                     <th>税率</th>
                                                    <th>含税单价</th>
                                                     <th>交易笔数</th>
                                                  
                                                </tr>
                                            </thead>
                                            <tbody  ng-if="priceLists.length==0">
			                                             	<tr>
			                                                    <td colspan="7" align="center" >暂无数据</td>
			                                                </tr>
			                                </tbody>
                                            <tbody ng-repeat="priceList in priceLists">
                                            
                                                <tr >
                                                    <td>{{priceList.versionNO}}</td>
                                                    <td>{{priceList.priceEffectiveDate | date:'yyyy-MM-dd' }}</td>
                                                    <td>{{priceList.currency}}</td>
                                                    <td>{{priceList.unitPrice}}</td>
                                                    <td>{{priceList.rate}}</td>
                                                    <td>{{priceList.inclusivePrice}}</td>
                                                    <td>--</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div><!-- 价格日志END-->
				
				
				</div>
				
        </div>
        <!-- END EXAMPLE TABLE PORTLET-->
    </div>
