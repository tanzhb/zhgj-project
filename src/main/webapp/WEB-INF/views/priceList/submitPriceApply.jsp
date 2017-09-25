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
            <a>申请</a>
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
<div class="row" id="price">
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
					     <form >
						     <div class="form-body">
			                      <div class="row">
			                          <div class="col-md-8">
			                          		<div class="form-group ">
				                              	<label class="control-label bold">原因：</label>
				                                <div class="">
				                                  <input type="text" name="remark"  class="form-control"  ng-model="priceList.reason"  >
			                                      <div class="form-control-focus"> </div>
			                                      <span class="help-block" >请输入原因</span>
				                               	</div>
			                               </div>
			                          </div>
			                      </div>
			                  </div>
			                </form>
		            </div>
		            <div class="portlet-title">
			               <div class="tools">
			               		<button type="button"   ng-if="buyOrSale.indexOf('buy')>-1"  ng-click="confirmPriceApply('buy')" class="btn blue btn-circle  btn-sm">提交申请</button>
			               		<button type="button"   ng-if="buyOrSale.indexOf('sale')>-1"  ng-click="confirmPriceApply('sale')"  class="btn blue btn-circle  btn-sm">提交申请</button>
				                <button type="button" ng-click="cancelPage()" class="btn default btn-circle  btn-sm"><i class="fa fa-undo"></i> 取消 </button>
				            </div>
					</div>
      			</div>
			</div>
	</div>
</div>

