<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- BEGIN PAGE HEADER-->
<div class="row" id="saleOrderPrint">
	<div class="col-md-12">
		<!-- BEGIN EXAMPLE TABLE PORTLET-->
		<div class="portlet bordered">
			<div class="portlet-body">
				<form action="#" id="form_sample_1" class="">
					<div class="portlet light ">
						<ul class="nav nav-tabs">
							<li class="dropdown pull-right tabdrop">
								<button type="button" ng-click="goback()" class="btn defualt  btn-circle  btn-sm"><i class="fa fa-reply"></i>返回</button>
							</li>
							<li class="active bold"><a data-target="#tab_1_1"
								data-toggle="tab">基本信息</a></li>
						</ul>
						<div class="tab-content">
							<div class="tab-pane fade active in" id="tab_1_1">
								<div class="portlet-body form">
									<!-- BEGIN FORM-->
									<div class="form-body">
										<div class="alert alert-danger display-hide">
											<button class="close" data-close="alert"></button>
											请先输入正确数据！
										</div>
										<div class="row">
											<div class="col-md-2">
												<div class="form-group">
													<label class="control-label bold">合同编号<span
														class="required" aria-required="true"> * </span></label>
													<div class="">
														{{contractVO.contractNum}}
													</div>
												</div>
											</div>
											<!--/span-->
											<div class="col-md-2">
												<div class="form-group">
													<label class="control-label bold">合同名称</label>
													<div class="">
														{{contractVO.contractName}}
													</div>
												</div>
											</div>
											
											<div class="col-md-2">
												<div class="form-group">
													<label class="control-label bold">合同类型<span
														class="required" aria-required="true"> * </span></label>
													<div class="">
														{{contractVO.contractType}}
													</div>
												</div>
											</div>
											<div class="col-md-2">
												<div class="form-group">
													<label class="control-label bold">标的物</label>
													<div class="">
														{{contractVO.subjectMatter}}
													</div>
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">甲方<span
														class="required" aria-required="true"> * </span></label>
													<div class="">
														{{contractVO.firstParty}}
													</div>
												</div>
											</div>
										</div>
										
										
										<div class="row">
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">甲方签订人<!-- <span
														class="required" aria-required="true"> * </span> --></label>
													<div class="">
													{{contractVO.firstPartySigner}}
													</div>
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">乙方<span
														class="required" aria-required="true"> * </span></label>
													<div class="">
													{{contractVO.secondParty}}
													</div>
												</div>
											</div>
											<!--/span-->

											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">乙方签订人<!-- <span
														class="required" aria-required="true"> * </span> --></label>
													<div class="">
													{{contractVO.secondPartySigner}}
													</div>
												</div>
											</div>
										</div>
										<!--/row-->
										
										
										
										<div class="row">
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">签订日期<span
														class="required" aria-required="true"> * </span></label>
													<div class="">
													{{contractVO.signDate}}
													</div>
												</div>
											</div>
											<!--/span-->

											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">签订地点<!-- <span
														class="required" aria-required="true"> * </span> --></label>
													<div class="">
													{{contractVO.signerAddress}}
													</div>
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">开始日期<!-- <span
														class="required" aria-required="true"> * </span> --></label>
													<div class="">
													{{contractVO.startDate}}
													</div>
												</div>
											</div>
										</div>
										<!--/row-->
										
										<div class="row">
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">结束日期<!-- <span
														class="required" aria-required="true"> * </span> --></label>
													<div class="">
													{{contractVO.endDate}}
													</div>
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">对方合同号</label>
													<div class="">
													{{contractVO.otherPartyContractNum}}
													</div>
												</div>
											</div>
											<!--/span-->

											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">备注</label>
													<div class="">
													{{contractVO.remark}}
													</div>
												</div>
											</div>
										</div>
										<!--/row-->
										
										
										<div class="row">
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">电子合同<span
														class="required" aria-required="true"> * </span></label>
													<div class="">
														<label ng-if="contractVO.electronicContract==null||contractVO.electronicContract==''" class="c_edit" >未上传附件</label>
						    							<label ng-if="contractVO.electronicContract!=null&&contractVO.electronicContract!=''" class="c_edit" ><a href="javascript:;" ng-click="download(contractVO.electronicContract)">{{contractVO.electronicContract.substring(contractVO.electronicContract.indexOf("_")+1)}}</a></label>
													</div>
												</div>
											</div>
											<!--/span-->

											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label bold">签字合同<span
														class="required" aria-required="true"> * </span></label>
													<div class="">
														<label ng-if="contractVO.signContract==null||contractVO.signContract==''" class="c_edit" >未上传附件</label>
						    							<label ng-if="contractVO.signContract!=null&&contractVO.signContract!=''" class="c_edit" ><a href="javascript:;" ng-click="download(contractVO.signContract)">{{contractVO.signContract.substring(contractVO.signContract.indexOf("_")+1)}}</a></label>
													</div>
												</div>
											</div>
										</div>
										<!--/row-->
									</div>
								</div>

							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
 <script>

       $('#signDate').datepicker({
    	   rtl: App.isRTL(),
	   		orientation: "left",
	   		autoclose: true,
	   		dateFormat:"yyyy-mm-dd",
	   		language: "zh-CN"
    	}).on('changeDate',function(ev){
    		var endDate=$("#endDate").val();
    		var signDate=$("#signDate").val();
    		
    		if(endDate<signDate&&$.trim(endDate)!=""&&endDate!=null){
        		toastr.warning('签订时间不能大于结束时间 ！');
        		$("#signDate").val("");
        		}else{
        		}
    		}); 
     
    	 $('#startDate').datepicker({
    	   language: "zh-CN",
    		autoclose: true,
    		todayBtn: 'linked',
    		 todayHighlight: true
    		}).on('changeDate',function(ev){
    		var startDate=$("#startDate").val();
    		var endDate=$("#endDate").val();
    		if(startDate>endDate&&endDate!=null&&$.trim(endDate)!=""){
    		toastr.warning('开始时间不能大于结束时间  ！');
    		$("#startDate").val("");
    		}
    		
    		
    		});
    	
    	
    		$('#endDate').datepicker({
    		language: "zh-CN",
    		autoclose: true,
    		todayBtn: 'linked',
    		todayHighlight: true
    		}).on('changeDate',function(ev){
    		var startDate=$("#startDate").val();
    		var endDate=$("#endDate").val();
    		var signDate=$("#signDate").val();
    		
    		if(endDate<startDate&&startDate!=null&&$.trim(startDate)!=""){
    		toastr.warning('结束时间不能小于开始时间 ！');
    		$("#endDate").val("");
    		}else{
    		}
    		
    		if(endDate<signDate&&signDate!=null&&$.trim(signDate)!=""){
        		toastr.warning('结束时间不能小于签订时间 ！');
        		$("#endDate").val("");
        		}else{
        		}
    		}); 
</script> 
