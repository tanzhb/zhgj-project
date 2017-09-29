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
								<td>
								<a href="javascript:;" ng-if="userInfo.email!=null&&userInfo.email!=''"
									class="btn btn-icon-only green-meadow"
									style="border-radius: 25px !important;"> <i
										class="fa fa-check"></i>
								</a>
								</td>
								<td>邮箱账号</td>
								<td><span ng-if="userInfo.email!=null&&userInfo.email!=''">可直接使用电子邮箱{{userInfo.email}}登录系统</span></td>
								<td><a href="javascript:;" 
									 ng-click="editEmail()"> <i class="fa fa-edit"></i>
								</a></td>
							</tr>
							<tr>
								<td>
								<a href="javascript:;" ng-if="userInfo.cellPhone!=null&&userInfo.cellPhone!=''"
									class="btn btn-icon-only green-meadow"
									style="border-radius: 25px !important;"> <i
										class="fa fa-check"></i>
								</a>
								</td>
								<td>手机验证</td>
								<td>
								<span ng-if="userInfo.cellPhone!=null&&userInfo.cellPhone!=''">已绑定手机号{{userInfo.cellPhone}}，可使用此手机号登录该系统</span>
								</td>
								<td><a href="javascript:;" ng-click="editPhone()"> <i class="fa fa-edit"></i>
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
									class="form-control"  ng-model="changeEmail.password" />
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

<div id="basicMaterielInfoI" class="modal fade bs-modal-lg" tabindex="-1"
	data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true"></button>
				<h4 class="caption-subject font-green bold uppercase">手机更改</h4>
			</div>
			<form action="#" id="form_sample_2" class="">
			<div class="modal-body">
				<div class="row">
					<div class="col-md-12">
						<div class="form-group">
							<label class="control-label bold">手机<span
								class="required" aria-required="true"> * </span></label>
							<div class="">
								<input type="text" id="phone" name="phone"
									class="form-control" ng-model="changePhone.phone" />
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
								<input type="password" id="passwordPhone" name="passwordPhone"
									class="form-control"  ng-model="changePhone.password" />
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
				<button type="button" ng-click="savePhone()" class="btn green">确定
				</button>
			</div>
		</div>
	</div>
</div>
