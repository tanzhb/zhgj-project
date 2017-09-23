<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- BEGIN PAGE HEADER-->

<!-- <h3 class="page-title"> 订单信息
</h3> -->
<!-- <div class="page-bar">
    <ul class="page-breadcrumb">
        <li>
            <i class="fa fa-home"></i>
            <a ui-sref="dashboard">首页</a>
            <i class="fa fa-angle-right"></i>
        </li>
        <li>
            <a ui-sref="buyOrder">采购订单</a>
            <i class="fa fa-angle-right"></i>
        </li>
        <li>
            <a>审批</a>
        </li>
    </ul>
    <div class="page-toolbar">
          <div class="btn-group pull-right">
              <button type="button" class="btn btn-fit-height grey-salt dropdown-toggle" onclick="printdiv('buyOrderPrint')"> 
              	<i class="fa fa-print"></i>
                  		打印
              </button>
              
          </div>
      </div>
</div> -->
<div class="row" id="buyOrderPrint">
    <div class="col-md-12">
        <!-- BEGIN EXAMPLE TABLE PORTLET-->
			<div class="portlet light ">
					<!-- <div class="portlet-title">
			               <div class="tools">
			               		<button type="button" ng-click="submitPage()" ng-hide="orderStatusInput" class="btn blue btn-circle  btn-sm">提交审核</button>
				                <button type="button" ng-click="cancelPage()" class="btn default btn-circle  btn-sm"><i class="fa fa-undo"></i> 取消 </button>
				            </div>
					</div> -->
           		 <div class="portlet-body">
					<jsp:include  page="viewPriceBase.jsp"/>
      			<!-- //申请原因 -->
					<div class="portlet-body form">
					     <div class="row">
	                          <div class="col-md-12">
	                          		<div class="form-group ">
		                              	<label class="control-label col-md-3 bold">原因：</label>
		                                <div class="col-md-8">
	                                      <p class="form-control-static" > {{priceList.remark}} </p>
		                               	</div>
	                               </div>
	                          </div>
	                      </div>
		            </div>
		            <div class="row">
						<div class="col-md-12">
							<p>
								<div class="portlet box green">
	                                <div class="portlet-title">
	                                    <div class="caption">
	                                        <i class="fa fa-globe"></i>评论 </div>
	                                </div>
	                                <div class="portlet-body">
	                                    <table class="table table-striped table-bordered table-hover order-column" id="pinglun">
	                                        <thead>
	                                            <tr>
	                                                <th>评论人</th>
	                                                <th>评论时间</th>
	                                                <th>评论内容</th>
	                                            </tr>
	                                        </thead>
	                                        <tbody id = "comment_audit">	
                                            </tbody>
	                                    </table>
	                                </div>
	                            </div>
							</p>
							
							<p>
								<div class="form-group form-md-line-input">
									<label class="col-md-3 control-label" for="form_control_1">我的意见</label>
									<div class="col-md-8">
										<textarea class="form-control"   
											id="content" name="content" rows="1"></textarea>
									</div>
								</div>
								<input type="hidden" name="serialNum" id="serialNum" value="" />
								<input type="hidden" name="taskId" id="taskId" value="{{taskId}}" />
								<input type="hidden" name="processInstanceId" id="processInstanceId" value="{{processInstanceId}}" />
							</p>

							<div class="modal-footer">
								<button type="button"  ng-if="buyOrSale.indexOf('buy')>-1"  ng-click="pricePass('buy')" class="btn blue btn-circle  btn-sm">通过</button>
								<button type="button"  ng-if="buyOrSale.indexOf('buy')>-1"  ng-click="priceUnPass('buy')" class="btn red btn-circle  btn-sm">不通过</button>
								<button type="button"   ng-if="buyOrSale.indexOf('sale')>-1"  ng-click="pricePass('sale')" class="btn blue btn-circle  btn-sm">通过</button>
								<button type="button"  ng-if="buyOrSale.indexOf('sale')>-1"  ng-click="priceUnPass('sale')" class="btn red btn-circle  btn-sm">不通过</button>
				                <button type="button" ng-click="cancelPage()" class="btn default btn-circle  btn-sm"><i class="fa fa-undo"></i> 取消 </button>
							</div>
						</div>
					</div>
      			</div>
			</div>
	</div>
</div>
