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
<!-- <h3 class="page-title"> 企业信息
</h3> -->
<!-- <div class="page-bar">
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
            <a ui-sref="company">企业信息</a>
        </li>
    </ul>

</div> -->
<!-- END PAGE HEADER-->
<!-- BEGIN MAIN CONTENT -->
<div class="row">
    <div class="col-md-12">
        <!-- BEGIN EXAMPLE TABLE PORTLET-->
       
<!-- 联系人modal START -->
 <div class="modal fade  modal-overflow in" id="contactor" tabindex="-1" role="contactor" aria-hidden="true" data-backdrop="static">
     <div class="modal-dialog" >
         <div class="modal-content">
             <div class="modal-header">
                 <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                 <h4 class="modal-title" >{{contactTitle==null?'新建':contactTitle}}联系人</h4>
             </div>
             <div class="modal-body">
               <div class="form-body" >
               <div class="alert alert-danger display-hide">
                                                <button class="close" data-close="alert"></button>表单数据未填写完整</div>
               <form id="contactForm"  class="form-horizontal">
                 <div class="row">
                     <div class="col-md-12">
                         <div class="form-group">
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
                         <div class="form-group">
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
                         <div class="form-group">
                                 <label class="col-md-4 control-label" for="department">部门/公司：</label>
                                 <div class="col-md-6">
                                     <input type="text" class="form-control" id="department" ng-model="companyContact.department"  >
                                     <div class="form-control-focus"> </div>
                                 </div>
                         </div>
                     </div>
                 </div>
                 <!--/row-->
                 <div class="row">
                     <div class="col-md-12">
                         <div class="form-group">
                                 <label class="col-md-4 control-label" for="department">管理职责：</label>
                                 <div class="col-md-6">
                                     <input type="text" class="form-control" id="responsibility"  ng-model="companyContact.responsibility"  >
                                     <div class="form-control-focus"> </div>
                                 </div>
                         </div>
                     </div>
                 </div>
                 <!--/row-->
                 <div class="row">
                     <div class="col-md-12">
                         <div class="form-group">
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
                         <div class="form-group">
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
                         <div class="form-group">
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
                         <div class="form-group">
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
                 <h4 class="modal-title" >{{financeTitle==null?'新建':'financeTitle'}}账号</h4>
             </div>
             <div class="modal-body">
               <div class="form-body" >
               <div class="alert alert-danger display-hide">
                                                <button class="close" data-close="alert"></button>表单数据未填写完整</div>
               <form id="companyFinanceForm" class="form-horizontal">
                 <div class="row">
                     <div class="col-md-12">
                         <div class="form-group">
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
                         <div class="form-group">
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
                         <div class="form-group">
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
                         <div class="form-group">
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


<!-- 联系地址modal START -->
 <div class="modal fade modal-overflow in" id="address" tabindex="-1" role="address" aria-hidden="true" data-backdrop="static">
     <div class="modal-dialog">
         <div class="modal-content">
             <div class="modal-header">
                 <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                 <h4 class="modal-title" >{{financeTitle==null?'新建':'financeTitle'}}联系地址</h4>
             </div>
             <div class="modal-body">
               <div class="form-body" >
               <div class="alert alert-danger display-hide">
                                                <button class="close" data-close="alert"></button>表单数据未填写完整</div>
               <form id="companyAddressForm" class="form-horizontal">
                 <div class="row">
                     <div class="col-md-12">
                         <div class="form-group">
                                 <label class="col-md-4 control-label" for="address">地址：</label>
                                 <div class="col-md-6">
                                     <input type="text" class="form-control"  name="address" ng-model="companyAddress.address"  >
                                     <div class="form-control-focus"> </div>
                                 </div>
                         </div>
                     </div>
                 </div>
                 <!--/row-->
                 <div class="row">
                     <div class="col-md-12">
                         <div class="form-group">
                                 <label class="col-md-4 control-label" for="zipCode">邮编：</label>
                                 <div class="col-md-6">
                                     <input type="text" class="form-control" id="zipCode" name="zipCode" ng-model="companyAddress.zipCode"  >
                                     <div class="form-control-focus"> </div>
                                 </div>
                         </div>
                     </div>
                 </div>
                 <!--/row-->
                 <div class="row">
                     <div class="col-md-12">
                         <div class="form-group">
                                 <label class="col-md-4 control-label" for="contactTel">联系电话：</label>
                                 <div class="col-md-6">
                                     <input type="text" class="form-control" name="contactTel" ng-model="companyAddress.contactTel"  >
                                     <div class="form-control-focus"> </div>
                                 </div>
                         </div>
                     </div>
                 </div>
                 <!-- row -->
                 <div class="row">
                     <div class="col-md-12">
                         <div class="form-group">
                                 <label class="col-md-4 control-label" for="mobileNum">手机：</label>
                                 <div class="col-md-6">
                                     <input type="text" class="form-control" id="mobileNum" name="mobileNum" ng-model="companyAddress.mobileNum"  >
                                     <div class="form-control-focus"> </div>
                                 </div>
                         </div>
                     </div>
                 </div>
                 <!-- row -->
                 <div class="row">
                     <div class="col-md-12">
                         <div class="form-group">
                                 <label class="col-md-4 control-label" for="remark">备注：</label>
                                 <div class="col-md-6">
                                     <input type="text" class="form-control" ng-model="companyAddress.remark"  >
                                     <div class="form-control-focus"> </div>
                                 </div>
                         </div>
                     </div>
                 </div>
                 <!-- row -->
                 </form>
             </div>
             </div>
             <div class="modal-footer">
                 <button type="button" class="btn btn-outline sbold red" data-dismiss="modal">取消</button>
                 <button type="button" class="btn btn-outline sbold blue" ng-click="saveCompanyAddress()">保存</button>
             </div>
             
         </div>
         <!-- /.modal-content -->
     </div>
     <!-- /.modal-dialog -->
 </div>
<!-- 联系地址modal END -->


<!-- END MAIN CONTENT -->

