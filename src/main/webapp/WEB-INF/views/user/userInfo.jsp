<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN BORDERED TABLE PORTLET-->
		<div class="portlet light portlet-fit ">
			<div class="portlet-title">
				<div class="caption">
				 <span class="caption-subject font-green bold uppercase">个人信息</span>
				</div>
				<div class="portlet-title" style="min-height: 48px;">
					<div class="tools" style="float: right" id="noprintdiv">
					    <button ng-click="goBack()" type="button"
							class="btn purple  btn-circle  btn-sm">
							<i class="fa fa-edit"></i> 修改
						</button>
						
						<button type="submit" ng-click="saveBasicInfo()"
							class="btn green  btn-circle  btn-sm">
							<i class="fa fa-save"></i> 保存
						</button>
					</div>
				</div>
			</div>
			
			<div class="portlet-body">
			123123
			</div>
			<div class="portlet-body">
			JACK个人用户
			</div>
			<div class="portlet-body">
                               管理权限：超级管理员&nbsp;&nbsp;&nbsp; <i class="fa fa-edit" ng-click="editSignContract($event)"></i>
																
			</div>
			<div class="portlet-body">
			<span class="caption-subject font-green bold uppercase">新消息：</span>&nbsp;&nbsp;5条&nbsp;|&nbsp;
			<span class="caption-subject font-green bold uppercase">公告：</span>&nbsp;&nbsp;2条&nbsp;|&nbsp;
			<span class="caption-subject font-green bold uppercase">任务：</span>&nbsp;&nbsp;4条
			</div>
			
			<div class="portlet-body">
				<div class="table-scrollable">
					<table class="table table-bordered table-hover">
						<tbody>
							<tr>
								<td style="width:20%;"><span class="required" style="color:red;" aria-required="true"> * </span> 姓名</td>
								<td style="width:80%;">{{userInfo.userName}}</td>
							</tr>
							<tr>
								<td style="width:20%;">性别</td>
								<td style="width:80%;">{{userInfo.sex}}</td>
							</tr>
							<tr>
								<td style="width:20%;"><span class="required" style="color:red;" aria-required="true"> * </span>单位</td>
								<td style="width:80%;">{{userInfo.company.comName}}</td>
							</tr>
							<tr>
								<td style="width:20%;"><span class="required" style="color:red;" aria-required="true"> * </span>部门</td>
								<td style="width:80%;">{{userInfo.department}}</td>
							</tr>
							<tr>
								<td style="width:20%;"><span class="required" style="color:red;" aria-required="true"> * </span>职位</td>
								<td style="width:80%;">{{userInfo.position}}</td>
							</tr>
							<tr>
								<td style="width:20%;"><span class="required" style="color:red;" aria-required="true"> * </span>手机</td>
								<td style="width:80%;">{{userInfo.cellPhone}}</td>
							</tr>
							<tr>
								<td style="width:20%;">电话</td>
								<td style="width:80%;">{{userInfo.telephone}}</td>
							</tr>
							<tr>
								<td style="width:20%;">QQ</td>
								<td style="width:80%;">{{userInfo.QQ}}</td>
							</tr>
							<tr>
								<td style="width:20%;">微信</td>
								<td style="width:80%;">{{userInfo.weChatNum}}</td>
							</tr>
							<tr>
								<td style="width:20%;">传真</td>
								<td style="width:80%;">{{userInfo.fax}}</td>
							</tr>
							<tr>
								<td style="width:20%;"><span class="required" style="color:red;" aria-required="true"> * </span>邮箱</td>
								<td style="width:80%;">{{userInfo.email}}</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<!-- END BORDERED TABLE PORTLET-->
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
