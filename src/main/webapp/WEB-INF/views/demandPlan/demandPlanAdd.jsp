<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"  %>
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
            <a ui-sref="demandPlan">需求计划</a>
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
                            <div class="caption">需求计划</div>
                            <div class="actions">
                                <button   ng-show="demandPlanView" class="btn blue  btn-outline  btn-sm " ng-click="editDemandPlanBasic()">
                                            <i class="fa fa-edit"></i> 编辑 </button>
                                <button   ng-show="demandPlanEdit" class="btn red  btn-outline  btn-sm " ng-click="cancelDemandPlanBasic()">
                                            <i class="fa fa-undo"></i> 取消 </button>
                                <button   ng-hide="demandPlanAdd" class="btn blue  btn-outline  btn-sm " ng-click="saveDemandPlan()">
                                            <i class="fa fa-save"></i> 保存 </button>
                            </div>
                        </div>
                        <div class="portlet-body form">
                            <form  id="demandPlanForm" class="form-horizontal" >
								<div class="form-body">
									<div class="alert alert-danger display-hide">
                                                <button class="close" data-close="alert"></button>请先输入正确数据！</div>
									<div class="row">
										<div class="col-md-6">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="demandPlanNum"> <span class="required"> * </span>需求计划编号：</label>
                                                    <div class="col-md-7">
                                                        <input type="text" class="form-control" id="demandPlanNum" name="demandPlanNum" ng-model="demandPlan.demandPlanNum" ng-hide="demandPlanAdd" >
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="demandPlanView">{{demandPlan.demandPlanNum}}</p>
                                                    </div>
                                            </div>
										</div>
										<div class="col-md-6">
											<div class="form-group form-md-line-input">
                                                   <label class="col-md-4 control-label" for="buyComId"> <span class="required"> * </span>客户名称：</label>
                                                   <!--  <div class="col-md-7">
                                                        <input type="text" class="form-control" id="buyComId" name="buyComId" ng-model="demandPlan.buyComId" ng-hide="demandPlanAdd" >
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="demandPlanView">{{demandPlan.buyComId}}</p>
                                                  </div> -->
	                                              <div class="col-md-7">
	                                                    <select id="customer" class="bs-select form-control" data-live-search="true"  name="buyComId" ng-model="demandPlan.buyComId" data-size="8" ng-hide="demandPlanAdd">
	                                                        <option value="WF">上海科技一</option>
	                                                        <option value="EH">上海科技二</option>
	                                                        <option value="YE">上海科技三</option>
	                                                        <option value="ZM">上海科技四</option>
	                                                        <option value="ZW">上海科技五</option>
	                                                    </select>
	                                                   <!--   <select id="customer" class="bs-select form-control" data-live-search="true" ng-repeat="customer in customers" data-size="8">
	                                                        <option value="{{customer.comName}}">{{customer.comName}}</option>
	                                                    </select> -->
	                                              </div>
                                            </div>
										</div>
									</div>
									<!--/row-->
									<div class="row">
										<div class="col-md-6">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="legalPerson">包含物料:</label>
                                                    <div class="col-md-7">
                                                        <!-- <input type="text" class="form-control" id="legalPerson" name="legalPerson" ng-model="company.legalPerson" ng-hide="companyAdd" >
                                                        <div class="form-control-focus"> </div> -->
                                                         <p class="control-label left" >{{rootMateriels.length}}</p>
                                                    </div>
                                            </div>
										</div>
										<div class="col-md-6">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="address">包含供应商：</label>
                                                    <div class="col-md-7">
                                                       <!--  <input type="text" class="form-control" id="address" name="address" ng-model="company.address" ng-hide="companyAdd" >
                                                        <div class="form-control-focus"> </div> -->
                                                         <p class="control-label left">未知</p>
                                                    </div>
                                            </div>
										</div>
									</div>
									<!--/row-->
									<div class="row">
										<div class="col-md-6">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="releaseDate"> <span class="required"> * </span>发布日期：</label>
                                                    <div class="col-md-7">
                                                        <input type="text" class="form-control form-control-inline input-medium date-picker" readonly="readonly" data-date-format="yyyy-mm-dd" data-date-viewmode="years" id="releaseDate" name="releaseDate" ng-model="demandPlan.releaseDate" ng-hide="demandPlanAdd" >
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="demandPlanView">{{demandPlan.releaseDate}}</p>
                                                    </div>
                                            </div>
										</div>
										<div class="col-md-6">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="tel">备注：</label>
                                                    <div class="col-md-7">
                                                        <input type="text" class="form-control" id="remark"  ng-model="demandPlan.remark" ng-hide="demandPlanAdd" >
                                                        <div class="form-control-focus"> </div>
                                                         <p class="control-label left" ng-show="demandPlanView">{{demandPlan.remark}}</p>
                                                    </div>
                                            </div>
										</div>
									</div>
								</div>
							</form>
         				</div>
                      
				
                        <div class="portlet-title">
                            <div class="caption">物料</div>
                            <div class="actions">
                                <a href="javascript:;" data-repeater-create class="btn blue btn-sm" ng-click="addMateriel()" >
                                     <i class="fa fa-plus"></i> 增加
                                </a>
                            </div>
                        </div>
                          <div class="portlet-body form">
			                                    <div class="table-scrollable">
			                                        <table class="table table-bordered table-hover">
			                                            <thead>
			                                                <tr>
			                                                    <th>物料编号</th>
			                                                    <th>物料名称</th>
			                                                    <th>规格型号</th>
			                                                    <th>单位</th>
			                                                    <th>数量</th>
			                                                    <th>交付日期</th>
			                                                    <th>距离交付</th>
			                                                    <th>交付地点</th>
			                                                    <th>供应商</th>
			                                                    <th style="width: 140px;"></th>
			                                                </tr>
			                                            </thead>
			                                           <tbody ng-if="rootMateriels.length==0">
			                                             	<tr>
			                                                    <td colspan="10" align="center" >暂无数据</td>
			                                                </tr>
			                                            </tbody>
			                                            <tbody ng-repeat="materiel in rootMateriels track by $index"  repeat-done="repeatDone()">
			                                                <tr ng-mouseover="showOperation('contact',$index)" ng-mouseleave="hideOperation('contact',$index)">
			                                                    <td>{{materiel.materielNum}}</td>
			                                                    <td>{{materiel.materielName}}</td>
			                                                    <td>{{materiel.specifications}}</td>
			                                                    <td>{{materiel.unit}}</td>
			                                                    <td>
			                                                    	<input type="text" ng-hide="demandPlanMaterielEdit{{$index}}" class="form-control  input-small" ng-model="materiel.amount" value="">
			                                                   	 	<label   ng-show="demandPlanMaterielView{{$index}}"  >{{materiel.amount}}</label>
			                                                    </td>
			                                                    <td>
				                                                    <div  ng-hide="demandPlanMaterielEdit{{$index}}" class="input-group input-medium date date-picker"
																		 data-date-format="yyyy-mm-dd"
																		data-date-viewmode="years">
																		<input type="text" class="form-control" readonly="" id="validityDate{{$index}}" ng-model="materiel.deliveryDate" name="validityDate"
																			> <span class="input-group-btn">
																			<button class="btn default " type="button">
																				<i class="fa fa-calendar"></i>
																			</button>
																		</span>
																		<span class="help-block"></span>
																	</div>
																	<label   ng-show="demandPlanMaterielView{{$index}}"  >{{materiel.deliveryDate}}</label>
			                                                    </td>
			                                                    <td>{{materiel.contactEmail}}</td>
			                                                    <td>
			                                                    	<input  ng-hide="demandPlanMaterielEdit{{$index}}" type="text" class="form-control" ng-model="materiel.deliveryAddress" value="">
			                                                    	<label   ng-show="demandPlanMaterielView{{$index}}"  >{{materiel.deliveryAddress}}</label>
			                                                    </td>
			                                                    <td>{{materiel.remark}}</td>
			                                                    <td ng-show="operation_c{{$index}}" style="width: 140px;">
			                                                    	<span>
				                                                    	<a ng-click="saveDemandPlanMateriel(materiel,$index)">保存</a>
				                                                    	&nbsp;&nbsp;&nbsp;
				                                                    	<a ng-click="editDemandPlanMateriel(materiel)">变更</a>
				                                                    	&nbsp;&nbsp;&nbsp;
				                                                    	<a ng-click="deleteDemandPlanMateriel(materiel)">删除</a>
			                                                    	</span>
			                                                    </td>
			                                                   <!--  <td ng-hide="operation_d{{$index}}"  >
			                                                    </td> -->
			                                                </tr>
			                                            </tbody>
			                                        </table>
			                                    </div>
                             </div>
				
                       
				</div>
				
        </div>
        <!-- END EXAMPLE TABLE PORTLET-->
    </div>
</div>


<!-- 联系人modal START -->
 <div class="modal fade  modal-overflow in" id="contactor" tabindex="-1" role="contactor" aria-hidden="true" data-backdrop="static">
     <div class="modal-dialog" >
         <div class="modal-content">
             <div class="modal-header">
                 <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                 <h4 class="modal-title" >新建联系人</h4>
             </div>
             <div class="modal-body">
               <div class="form-body" >
               <div class="alert alert-danger display-hide">
                                                <button class="close" data-close="alert"></button>表单数据未填写完整</div>
               <form id="contactForm"  class="form-horizontal">
                 <div class="row">
                     <div class="col-md-12">
                         <div class="form-group form-md-line-input">
                                 <label class="col-md-4 control-label" for="contactName"><span class="required"> * </span>姓名：</label>
                                 <div class="col-md-6">
                                     <input type="text" class="form-control" id="contactName" name="contactName" ng-model="companyContact.contactName"  >
                                     <div class="form-control-focus"> </div>
                                 </div>
                         </div>
                     </div>
                 </div>
                 <!--/row-->
                 <div class="row">
                     <div class="col-md-12">
                         <div class="form-group form-md-line-input">
                                 <label class="col-md-4 control-label" for="contactTitle"><span class="required"> * </span>职位：</label>
                                 <div class="col-md-6">
                                     <input type="text" class="form-control" id="contactTitle" name="contactTitle" ng-model="companyContact.contactTitle"  >
                                     <div class="form-control-focus"> </div>
                                 </div>
                         </div>
                     </div>
                 </div>
                 <div class="row">
                     <div class="col-md-12">
                         <div class="form-group form-md-line-input">
                                 <label class="col-md-4 control-label" for="department"><span class="required"> * </span>部门/公司：</label>
                                 <div class="col-md-6">
                                     <input type="text" class="form-control" id="department" name="department" ng-model="companyContact.department"  >
                                     <div class="form-control-focus"> </div>
                                 </div>
                         </div>
                     </div>
                 </div>
                 <!--/row-->
                 <div class="row">
                     <div class="col-md-12">
                         <div class="form-group form-md-line-input">
                                 <label class="col-md-4 control-label" for="department"><span class="required"> * </span>管理职责：</label>
                                 <div class="col-md-6">
                                     <input type="text" class="form-control" id="responsibility" name="responsibility" ng-model="companyContact.responsibility"  >
                                     <div class="form-control-focus"> </div>
                                 </div>
                         </div>
                     </div>
                 </div>
                 <!--/row-->
                 <div class="row">
                     <div class="col-md-12">
                         <div class="form-group form-md-line-input">
                                 <label class="col-md-4 control-label" for="contactTel"><span class="required"> * </span>电话：</label>
                                 <div class="col-md-6">
                                     <input type="text" class="form-control" id="contactTel" name="contactTel" ng-model="companyContact.contactTel"  >
                                     <div class="form-control-focus"> </div>
                                 </div>
                         </div>
                     </div>
                 </div>
                 <!--/row-->
                 <div class="row">
                     <div class="col-md-12">
                         <div class="form-group form-md-line-input">
                                 <label class="col-md-4 control-label" for="wechat">微信：</label>
                                 <div class="col-md-6">
                                     <input type="text" class="form-control" id="wechat"  ng-model="companyContact.wechat"  >
                                     <div class="form-control-focus"> </div>
                                 </div>
                         </div>
                     </div>
                 </div>
                 <!--/row-->
                 <div class="row">
                     <div class="col-md-12">
                         <div class="form-group form-md-line-input">
                                 <label class="col-md-4 control-label" for="contactEmail">邮箱：</label>
                                 <div class="col-md-6">
                                     <input type="text" class="form-control" id="contactEmail" name="contactEmail" ng-model="companyContact.contactEmail"  >
                                     <div class="form-control-focus"> </div>
                                 </div>
                         </div>
                     </div>
                 </div>
                 <!--/row-->
                 <div class="row">
                     <div class="col-md-12">
                         <div class="form-group form-md-line-input">
                                 <label class="col-md-4 control-label" for="remark">备注：</label>
                                 <div class="col-md-6">
                                     <input type="text" class="form-control" id="remark" ng-model="companyContact.remark"  >
                                     <div class="form-control-focus"> </div>
                                 </div>
                         </div>
                     </div>
                 </div>
                 <!--/row-->
                 
                 </form>
             </div>
             </div>
             <div class="modal-footer">
                 <button type="button" class="btn btn-outline sbold red" data-dismiss="modal">取消</button>
                 <button type="button" class="btn btn-outline sbold blue" ng-click="saveCompanyContact()">保存</button>
             </div>
             
         </div>
         <!-- /.modal-content -->
     </div>
     <!-- /.modal-dialog -->
 </div>
<!-- 联系人modal END -->

<!-- 财务信息modal START -->
 <div class="modal fade modal-overflow in" id="finance" tabindex="-1" role="finance" aria-hidden="true" data-backdrop="static">
     <div class="modal-dialog">
         <div class="modal-content">
             <div class="modal-header">
                 <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                 <h4 class="modal-title" >新建账号</h4>
             </div>
             <div class="modal-body">
               <div class="form-body" >
               <div class="alert alert-danger display-hide">
                                                <button class="close" data-close="alert"></button>表单数据未填写完整</div>
               <form id="companyFinanceForm" class="form-horizontal">
                 <div class="row">
                     <div class="col-md-12">
                         <div class="form-group form-md-line-input">
                                 <label class="col-md-4 control-label" for="openingBank"><span class="required"> * </span>银行：</label>
                                 <div class="col-md-6">
                                     <input type="text" class="form-control" id="openingBank" name="openingBank" ng-model="companyFinance.openingBank"  >
                                     <div class="form-control-focus"> </div>
                                 </div>
                         </div>
                     </div>
                 </div>
                 <!--/row-->
                 <div class="row">
                     <div class="col-md-12">
                         <div class="form-group form-md-line-input">
                                 <label class="col-md-4 control-label" for="accountName"><span class="required"> * </span>户名：</label>
                                 <div class="col-md-6">
                                     <input type="text" class="form-control" id="accountName" name="accountName" ng-model="companyFinance.accountName"  >
                                     <div class="form-control-focus"> </div>
                                 </div>
                         </div>
                     </div>
                 </div>
                 <!--/row-->
                 <div class="row">
                     <div class="col-md-12">
                         <div class="form-group form-md-line-input">
                                 <label class="col-md-4 control-label" for="accountNumber"><span class="required"> * </span>账号：</label>
                                 <div class="col-md-6">
                                     <input type="text" class="form-control" id="accountNumber" name="accountNumber" ng-model="companyFinance.accountNumber"  >
                                     <div class="form-control-focus"> </div>
                                 </div>
                         </div>
                     </div>
                 </div>
                 <div class="row">
                     <div class="col-md-12">
                         <div class="form-group form-md-line-input">
                                 <label class="col-md-4 control-label" for="remark">备注：</label>
                                 <div class="col-md-6">
                                     <input type="text" class="form-control" id="remark" ng-model="companyFinance.remark"  >
                                     <div class="form-control-focus"> </div>
                                 </div>
                         </div>
                     </div>
                 </div>
                 <!--/row-->
                 </form>
             </div>
             </div>
             <div class="modal-footer">
                 <button type="button" class="btn btn-outline sbold red" data-dismiss="modal">取消</button>
                 <button type="button" class="btn btn-outline sbold blue" ng-click="saveCompanyFinance()">保存</button>
             </div>
             
         </div>
         <!-- /.modal-content -->
     </div>
     <!-- /.modal-dialog -->
 </div>
<!-- 财务信息modal END -->
<jsp:include page="selectMateriel.jsp"></jsp:include>

<!-- END MAIN CONTENT -->
<!-- BEGIN MAIN JS -->
 <script>
// FormRepeater.init();
</script> 
<!-- END MAIN JS -->
