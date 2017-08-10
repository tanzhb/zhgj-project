<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!-- BEGIN PAGE HEADER-->

<div class="tab-pane" id="tab_1">
	<div class="portlet box blue">
		<div class="portlet-title">
			<div class="caption">
				<i class="fa fa-gift"></i>合同编辑
			</div>
			<div class="tools">
				<a href="javascript:;" class="collapse"> </a> <a href="javascript:;"
					class="reload"> </a>
			</div>
		</div>
		<div class="portlet-body form" ng-controller="ContractController">
			<!-- BEGIN FORM-->
			<form class="horizontal-form" id="form_sample_1">
				<div class="form-body">
					<h3 class="form-section">基本信息</h3>
					<div class="row">
						<div class="form-group form-md-line-input col-md-6">
							<label class="col-md-4 control-label" for="form_control_1">采购合同编号
								<span class="required">*</span>
							</label>
							<div class="col-md-8">
								<input type="text" class="form-control"
									ng-model="contractVO.contractNum" placeholder="采购合同编号"
									id="contractNum" name="contractNum" /> <input type="hidden"
									class="" ng-model="contractVO.id" value="{{contractVO.id}}"
									ng-hide="visible" />
								<div class="form-control-focus"></div>
								<span class="help-block">采购合同编号</span>
							</div>
						</div>

						<div class="form-group form-md-line-input col-md-6">
							<label class="col-md-3 control-label" for="form_control_1">合同类型
								<span class="required">*</span>
							</label>
							<div class="col-md-9">
								<select class="form-control" ng-model="contractVO.contractType"
									name="contractType">
									<option value="">合同类型</option>
									<option value="采购合同">采购合同</option>
									<option value="销售合同">销售合同</option>
									<option value="仓库合同">仓库合同</option>
								</select>
								<div class="form-control-focus"></div>
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
								<select class="form-control" ng-model="contractVO.serviceModel"
									name="serviceModel">
									<option value="">服务模式</option>
									<option value="仓储服务">仓储服务</option>
									<option value="仓储+垫资服务">仓储+垫资服务</option>
								</select>
								<div class="form-control-focus"></div>
								<span class="help-block">服务模式</span>
							</div>
						</div>


						<div class="form-group form-md-line-input col-md-6">
							<label class="col-md-3 control-label" for="form_control_1">结算条款
								<span class="required">*</span>
							</label>
							<div class="col-md-9">
								<select class="form-control"
									ng-model="contractVO.settlementClause" name="settlementClause">
									<option value="">结算条款</option>
									<option value="进销差">进销差</option>
									<option value="服务费">服务费</option>
									<option value="折扣折让">折扣折让</option>
									<option value="红票">红票</option>
								</select>
								<div class="form-control-focus"></div>
								<span class="help-block">结算条款</span>
							</div>
						</div>

					</div>
					<!--/row-->
					<div class="row">
						<div class="form-group form-md-line-input col-md-6">
							<label class="col-md-3 control-label" for="form_control_1">供应商
								<span class="required">*</span>
							</label>
							<div class="col-md-9">
								<input type="text" class="form-control"
									ng-model="contractVO.comId" placeholder="供应商" name="comId" />
								<div class="form-control-focus"></div>
								<span class="help-block">供应商</span>
							</div>
						</div>

						<div class="form-group form-md-line-input col-md-6">
							<label class="col-md-3 control-label" for="form_control_1">开始日期
								<span class="required">*</span>
							</label>
							<div class="col-md-9">
								<input type="text" class="form-control date-picker"
									ng-model="contractVO.startDate" placeholder="mm/dd/yyyy" readonly
									id="startDate" name="startDate" />
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
								<input type="text" class="form-control date-picker"
									placeholder="mm/dd/yyyy" ng-model="contractVO.signDate" readonly
									id="endDate" name="endDate" />
								<div class="form-control-focus"></div>
								<span class="help-block">结束日期</span>
							</div>
						</div>

						<div class="form-group form-md-line-input col-md-6">
							<label class="col-md-3 control-label" for="form_control_1">签订日期
								<span class="required">*</span>
							</label>
							<div class="col-md-9">
								<input type="text" class="form-control date-picker"
									ng-model="contractVO.signDate" id="signDate"
									placeholder="mm/dd/yyyy" readonly name="signDate" />
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
									ng-model="contractVO.signer" placeholder="签订人" name="signer" />
								<div class="form-control-focus"></div>
								<span class="help-block">签订人</span>
							</div>
						</div>

						<div class="form-group form-md-line-input col-md-6">
							<label class="col-md-3 control-label" for="form_control_1">备注
							</label>
							<div class="col-md-9">
								<input type="text" class="form-control"
									ng-model="contractVO.remark" placeholder="备注" />
								<div class="form-control-focus"></div>
								<span class="help-block">备注</span>
							</div>
						</div>
						<!--/span-->
					</div>

					<div class="row">
						<div class="form-group form-md-line-input col-md-6">
							<label class="col-md-3 control-label" for="form_control_1">电子合同
								<span class="required">*</span>
							</label>
							<div class="col-md-9">
								<input type="file" id="electric"  ng-model="contractVO.electronicContract" name="files" value="123"
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
									<input type="file" id="signContract" ng-model="contractVO.signContract" name="file"
									class="form-control" /> 
								<div class="form-control-focus"></div>
								<span class="help-block">签字合同</span>
							</div>
						</div>
					</div>

					<div class="form-actions right">
						<button type="button" class="btn default"
							ng-click="goback()">
							<i class="fa fa-reply"></i>取消
						</button>
						<button type="button" ng-click="saveUserContract()" class="btn blue">
							<i class="fa fa-check"></i> 保存
						</button>
					</div>
			</form>
			<!-- END FORM-->
		</div>
	</div>
</div>
<!-- END MAIN CONTENT -->
<!-- BEGIN MAIN JS -->
<script>

    /*  TableDatatablesManaged.init(); */
    
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