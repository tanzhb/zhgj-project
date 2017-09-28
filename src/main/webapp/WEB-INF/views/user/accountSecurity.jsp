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
			</div>


			<div class="portlet-body">
				<h5>安全等级：</h5>
				<div class="progress" style="height: 20px; width: 600px;">
					<div class="progress-bar progress-bar-success" role="progressbar"
						aria-valuenow="40" aria-valuemin="0" aria-valuemax="100"
						style="width: 40%">
						<span class="sr-only"> 40% Complete (success) </span>
					</div>
				</div>
			</div>

			<div class="portlet-body">
				<div class="table-responsive">
					<table class="table">
						<tbody>
							<tr>
								<td></td>
								<td>账号类型</td>
								<td>试用账号</td>
								<td></td>
							</tr>
							<tr>
								<td><a href="javascript:;"
									class="btn btn-icon-only green-meadow"
									style="border-radius: 25px !important;"> <i
										class="fa fa-check"></i>
								</a></td>
								<td>邮箱账号</td>
								<td><span ng-if="userInfo.email!=null&&userInfo.email!=''">可直接使用电子邮箱{{userInfo.email}}登录系统</span></td>
								<td><a href="javascript:;" 
									 ng-click="editEmail()"> <i class="fa fa-edit"></i>
								</a></td>
							</tr>
							<tr>
								<td><a href="javascript:;"
									class="btn btn-icon-only green-meadow"
									style="border-radius: 25px !important;"> <i
										class="fa fa-check"></i>
								</a></td>
								<td>登录密码</td>
								<td>安全性高的密码可以使账号更安全，建议您经常更换密码，密码至少为6位，可采用字母、数字、特殊字符的组合</td>
								<td><a href="javascript:;"> <i class="fa fa-edit"></i>
								</a></td>
							</tr>
							<tr>
								<td><a href="javascript:;"
									class="btn btn-icon-only green-meadow"
									style="border-radius: 25px !important;"> <i
										class="fa fa-check"></i>
								</a></td>
								<td>手机验证</td>
								<td>已绑定手机号165******2878，可使用此手机号登录该系统</td>
								<td><a href="javascript:;"> <i class="fa fa-edit"></i>
								</a></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<!-- END BORDERED TABLE PORTLET-->
</div>
</div>

<div id="basicMaterielInfo" class="modal fade bs-modal-lg" tabindex="-1"
	data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true"></button>
				<h4 class="caption-subject font-green bold uppercase">邮箱更改</h4>
			</div>
			<form action="#" id="form_sample_1" class="">
			<div class="modal-body">
				<div class="row">
					<div class="col-md-12">
						<div class="form-group">
							<label class="control-label bold">邮箱<span
								class="required" aria-required="true"> * </span></label>
							<div class="">
								<input type="text" id="email" name="email"
									class="form-control" ng-model="changeEmail.email" />
								<div class="form-control-focus"></div>
							</div>
						</div>
					</div>
				</div>
				<!--/row-->
				<div class="row">
					<div class="col-md-12">
						<div class="form-group">
							<label class="control-label bold">密码<span
								class="required" aria-required="true"> * </span></label>
							<div class="">
								<input type="password" id="password" name="password"
									class="form-control" ng-model="changeEmail.password" />
								<div class="form-control-focus"></div>
							</div>
						</div>
					</div>
				</div>
				<!--/row-->
			</div>
			</form>
			<div class="modal-footer">
				<button type="button" data-dismiss="modal"
					class="btn dark btn-outline">取消</button>
				<button type="button" ng-click="saveEmail()" class="btn green">确定
				</button>
			</div>
		</div>
	</div>
</div>
<script>
	$('#signDate').datepicker({
		rtl : App.isRTL(),
		orientation : "left",
		autoclose : true,
		dateFormat : "yyyy-mm-dd",
		language : "zh-CN"
	}).on('changeDate', function(ev) {
		var endDate = $("#endDate").val();
		var signDate = $("#signDate").val();

		if (endDate < signDate && $.trim(endDate) != "" && endDate != null) {
			toastr.warning('签订时间不能大于结束时间 ！');
			$("#signDate").val("");
		} else {
		}
	});

	$('#startDate').datepicker({
		language : "zh-CN",
		autoclose : true,
		todayBtn : 'linked',
		todayHighlight : true
	}).on('changeDate', function(ev) {
		var startDate = $("#startDate").val();
		var endDate = $("#endDate").val();
		if (startDate > endDate && endDate != null && $.trim(endDate) != "") {
			toastr.warning('开始时间不能大于结束时间  ！');
			$("#startDate").val("");
		}

	});

	$('#endDate').datepicker({
		language : "zh-CN",
		autoclose : true,
		todayBtn : 'linked',
		todayHighlight : true
	}).on(
			'changeDate',
			function(ev) {
				var startDate = $("#startDate").val();
				var endDate = $("#endDate").val();
				var signDate = $("#signDate").val();

				if (endDate < startDate && startDate != null
						&& $.trim(startDate) != "") {
					toastr.warning('结束时间不能小于开始时间 ！');
					$("#endDate").val("");
				} else {
				}

				if (endDate < signDate && signDate != null
						&& $.trim(signDate) != "") {
					toastr.warning('结束时间不能小于签订时间 ！');
					$("#endDate").val("");
				} else {
				}
			});
</script>
