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
            <a>重新编辑</a>
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
					<jsp:include  page="editPriceBase.jsp"/>
      			</div>
      			   <div class="row">
						<div class="col-md-12">
							<p>
								<div class="portlet box green">
	                                <div class="portlet-title">
	                                    <div class="caption">
	                                        <i class="fa fa-globe"></i> 流程审批 </div>
	                                </div>
	                                <div class="portlet-body">
	                                    <table class="table table-striped table-bordered table-hover order-column" id="pinglun">
	                                        <thead>
	                                            <tr>
	                                                <th> 审批人 </th>
	                                                <th> 岗位 </th>
	                                                <th> 审批时间 </th>
	                                                <th> 审批意见</th>
	                                            </tr>
	                                        </thead>
	                                        <tbody id = "comment_audit">	
                                            </tbody>
	                                    </table>
	                                </div>
	                            </div>
							</p>
							
							<p>
								<input type="hidden" name="serialNum" id="serialNum" value="" />
								<input type="hidden" name="taskId" id="taskId" value="{{taskId}}" />
								<input type="hidden" name="processInstanceId" id="processInstanceId" value="{{processInstanceId}}" />
							</p>
						</div>
					</div>
      			<!-- //申请原因 -->
					<div class="portlet-body form">
					     <form >
						     <div class="form-body">
			                      <div class="row">
			                          <div class="col-md-8">
			                          		<div class="form-group ">
				                              	<label class="control-label bold">原因：</label>
				                                <div class="">
				                                  <input type="text" name="reason" class="form-control"  ng-model="priceList.reason">
			                                      <div class="form-control-focus"> </div>
			                                      <span class="help-block">请重新输入原因</span>
				                               	</div>
			                               </div>
			                          </div>
			                      </div>
			                  </div>
			                </form>
		            </div>
		            <div class="portlet-title">
			               <div class="tools">
			               		<button type="button" ng-click="replyPrice('buy')"  ng-if="buyOrSale.indexOf('buy')>-1"  class="btn blue btn-circle  btn-sm">重新申请</button>
			               		<button type="button" ng-click="cancelApplyPrice('buy')"  ng-if="buyOrSale.indexOf('buy')>-1"   class="btn red btn-circle  btn-sm">取消申请</button>
			               		<button type="button" ng-click="replyPrice('sale')"  ng-if="buyOrSale.indexOf('sale')>-1"  class="btn blue btn-circle  btn-sm">重新申请</button>
			               		<button type="button" ng-click="cancelApplyPrice('sale')"  ng-if="buyOrSale.indexOf('sale')>-1"   class="btn red btn-circle  btn-sm">取消申请</button>
				                <button type="button" ng-click="cancelPage()" class="btn default btn-circle  btn-sm"><i class="fa fa-undo"></i> 取消 </button>
				            </div>
					</div>
			</div>
	</div>
</div>
<jsp:include  page="../materiel/selectMateriel.jsp"/>

