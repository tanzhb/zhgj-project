<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!-- BEGIN PAGE HEADER-->
<h3 class="page-title">
	合同列表 <small></small>
</h3>
<div class="page-bar">
	<ul class="page-breadcrumb">
		<li><i class="fa fa-home"></i> <a ui-sref="dashboard">首页</a> <i
			class="fa fa-angle-right"></i></li>
		<li><a ui-sref="userContract">基础数据</a></li>
		<li><a ui-sref="userContract">合同信息</a></li>
	</ul>
</div>
<div class="tab-pane" id="tab_1">
	<div class="portlet box blue">
		<div class="portlet-title">
			<div class="caption">
				<i class="fa fa-gift"></i>合同新增
			</div>
			<div class="tools">
				<a href="javascript:;" class="collapse"> </a>
			    <a href="javascript:;" class="reload"> </a> 
			</div>
		</div>
		<div class="portlet-body form"  >
			<!-- BEGIN FORM-->
			<form class="horizontal-form" id="form_sample_1" enctype="multipart/form-data" name="myForm"  ng-submit="saveUserContract()">
				<div class="form-body">
					<h3 class="form-section">基本信息</h3>
					<div class="row">

						<div class="form-group form-md-line-input col-md-6">
						<label class="col-md-4 control-label" for="form_control_1">采购合同编号
                            <span class="required">*</span>
                        </label>
                        
							<div class="col-md-8">
								<input type="text" class="form-control"
									ng-model="contractVO.contractNum" placeholder="采购合同编号" id="contractNum" name="contractNum"/>
									<input type="hidden"
									id="id" 
									ng-hide="visible" />
								<div class="form-control-focus"></div>
								<span class="help-block">采购合同编号</span>
								<span class="help-block">采购合同编号</span>
							</div>
						</div>

						<div class="form-group form-md-line-input col-md-6">
						<label class="col-md-3 control-label" for="form_control_1">合同类型
                            <span class="required">*</span>
                        </label>
                        
                          <div class="col-md-9">
                              <select class="form-control" ng-model="contractVO.contractType" id="contractType" name="contractType">
                                  <option value="">合同类型</option>
                                  <option value="采购合同">采购合同</option>
								  <option value="销售合同">销售合同</option>
								  <option value="仓库合同">仓库合同</option>
                              </select>
                              <div class="form-control-focus"> </div>
                              <span class="help-block">合同类型</span>
                          </div>
                      </div>
					</div>
					<!--/row-->
					<div class="row">
					
					<div class="form-group form-md-line-input col-md-6">
					 <label class="col-md-3 control-label" for="form_control_1">服务模式
                            <span class="required">*</span>
                        </label>
                        
                          <div class="col-md-9">
                              <select class="form-control" ng-model="contractVO.serviceModel" id="serviceModel" name="serviceModel">
                                  <option value="">服务模式</option>
                                  <option value="仓储服务">仓储服务</option>
								  <option value="仓储+垫资服务">仓储+垫资服务</option>
                              </select>
                              <div class="form-control-focus"> </div>
                               <span class="help-block">服务模式</span>
                          </div>
                      </div>
                      
                      <div class="form-group form-md-line-input col-md-6">
                      <label class="col-md-3 control-label" for="form_control_1">结算条款
                            <span class="required">*</span>
                        </label>
                        
                          <div class="col-md-9">
                              <select class="form-control" ng-model="contractVO.settlementClause" id="settlementClause" name="settlementClause">
                                  <option value="">结算条款</option>
                                  <option value="进销差">进销差</option>
								  <option value="服务费">服务费</option>
								  <option value="折扣折让">折扣折让</option>
								  <option value="红票">红票</option>
                              </select>
                              <div class="form-control-focus"> </div>
                              <span class="help-block">结算条款</span>
                          </div>
                      </div>
					
					</div>
					<div class="row">
						<div class="form-group form-md-line-input col-md-6">
						<label class="col-md-3 control-label" for="form_control_1">供应商
                            <span class="required">*</span>
                        </label>
                        
							<div class="col-md-9">
								<input type="text" class="form-control"
									 ng-model="contractVO.comId" placeholder="供应商" id="comId" name="comId"/>
								<div class="form-control-focus"></div>
								<span class="help-block">供应商</span>
							</div>
						</div>
						
						<div class="form-group form-md-line-input col-md-6">
						<label class="col-md-3 control-label" for="form_control_1">开始日期
                            <span class="required">*</span>
                        </label>
							<div class="col-md-9">
								<input type="text" class="form-control date-picker" readonly
									 ng-model="contractVO.startDate" placeholder="mm/dd/yyyy"  id="startDate" name="startDate"/>
								<div class="form-control-focus"></div>
								
								<span class="help-block">开始日期</span>
							</div>
						</div>
						
					</div>
					<!--/row-->
					
					
					<div class="row">
						<div class="form-group form-md-line-input col-md-6">
						<label class="col-md-3 control-label" for="form_control_1">结束日期
                            <span class="required">*</span>
                        </label>
							<div class="col-md-9">
								<input type="text" class="form-control date-picker" readonly
								 placeholder="mm/dd/yyyy" ng-model="contractVO.endDate"  id="endDate" name="endDate"/>
								<div class="form-control-focus"></div>
								
								<span class="help-block">结束日期</span>
							</div>
						</div>
						
						<div class="form-group form-md-line-input col-md-6">
						<label class="col-md-3 control-label" for="form_control_1">签订日期
                            <span class="required">*</span>
                        </label>
							<div class="col-md-9">
								<input type="text" class="form-control date-picker" readonly
								ng-model="contractVO.signDate" id="signDate" placeholder="mm/dd/yyyy" name="signDate"/>
								<div class="form-control-focus"></div>
								<span class="help-block">签订日期</span>
							</div>
						</div>
					</div>
					<!--/row-->
					
					<div class="row">
						<div class="form-group form-md-line-input col-md-6">
						<label class="col-md-3 control-label" for="form_control_1">签订人
                            <span class="required">*</span>
                        </label>
							<div class="col-md-9">
								<input type="text" class="form-control"
									ng-model="contractVO.signer" placeholder="签订人" id="signer" name="signer"/>
								<div class="form-control-focus"></div>
								<span class="help-block">签订人</span>
							</div>
						</div>
						
						<div class="form-group form-md-line-input col-md-6">
						<label class="col-md-3 control-label" for="form_control_1">备注
                        </label>
							<div class="col-md-9">
								<input type="text" class="form-control"
									ng-model="contractVO.remark" id="remark" placeholder="备注"/>
								<div class="form-control-focus"></div>
								<span class="help-block">备注</span>
							</div>
						</div>
					</div>
<!-- <img alt="" src="http://localhost:8080/zhgj-web/uploadAttachFiles/DD35839D47A74215935094E5FA9D4729.jpg"> -->


					<div class="row">
						<div class="form-group form-md-line-input col-md-6">
							<label class="col-md-3 control-label" for="form_control_1">电子合同
								<span class="required">*</span>
							</label>
							<div class="col-md-9">
								<input type="file" id="electric"  ng-model="files" name="files"
									class="form-control" /> 
									<div class="form-control-focus"></div>
								<span class="help-block">电子合同</span>
							</div>
						</div>

						<div class="form-group form-md-line-input col-md-6">
							<label class="col-md-3 control-label" for="form_control_1">签字合同
								<span class="required">*</span>
							</label>
							<div class="col-md-9">
									<input type="file" id="signContract" ng-model="files" name="file"
									class="form-control" /> 
								<div class="form-control-focus"></div>
								<span class="help-block">签字合同</span>
							</div>
						</div>
					</div>

					<div class="form-actions right">
					<button type="button" ng-click="goback()" class="btn default"><i class="fa fa-reply"></i>取消</button>
					<button type="submit"  class="btn blue"><i class="fa fa-check"></i> 保存</button>
				</div>
				
			</form>
			<!-- END FORM-->
		</div>
	</div>
</div>
<!-- END MAIN CONTENT -->
<!-- BEGIN MAIN JS -->
 <script>

/*      TableDatatablesManaged.init(); */
    
       $('#signDate').datepicker({
    	   language:"zh-CN",
    	   autoclose:true,
    	   pickerPosition:"top-right",
    	   todayHighlight: true
    	});  
     
    	 $('#startDate').datepicker({
    		autoclose: true,
    		todayBtn: 'linked',
    		 todayHighlight: true
    		}).on('changeDate',function(ev){
    		var startDate=$("#startDate").val();
    		var endDate=$("#endDate").val();
    		if(startDate>endDate&&endDate!=null&&endDate!=""){
    		alert("开始时间不能大于结束时间  ！");
    		$("#startDate").focus();
    		}
    		});
    	
    	
    		$('#endDate').datepicker({
    		autoclose: true,
    		todayBtn: 'linked',
    		todayHighlight: true
    		}).on('changeDate',function(ev){
    		var startDate=$("#startDate").val();
    		var endDate=$("#endDate").val();
    		
    		if(endDate<startDate){
    		alert("结束时间不能小于开始时间 ！");
    		$("#endDate").focus();
    		}else{
    		}
    		}); 
     
     
     
      function uploadElectric(obj){
    	 $(obj).next().val($(obj).val());
     } 
     
     function uploadSign(obj){
    	 $(obj).next().val($(obj).val());
     }
     
</script> 
<!-- END MAIN JS -->