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
						<button ng-click="edit()" type="button"
							class="btn purple  btn-circle  btn-sm">
							<i class="fa fa-edit"></i> 修改
						</button>

						<button type="submit" ng-click="updateUserInfo()"
							class="btn green  btn-circle  btn-sm">
							<i class="fa fa-save"></i> 保存
						</button>
					</div>
				</div>
			</div>

			<div class="portlet-body" ng-app="app">
				<div class="sp-page-content" style="float:left;" ng-controller="addPhotoCtrl as ctl">
					<div class="sp-page-box" style="float:left;">
						<div class="sp-page-boxcolumn span2" style="float:left;">
							<span class="sp-upload" style="float:left;"> 
							<img ng-if="userInfo.avatar!=null&&userInfo.avatar!=''" class="sp-upload-photo" alt="" ng-src="${pageContext.request.contextPath}/rest/fileOperate/downloadFile?fileName={{userInfo.avatar}}" style="float:left;"/> 
							<img ng-if="userInfo.avatar==null||userInfo.avatar==''" class="sp-upload-photo" alt="" ng-src="{{ctl.info.photo}}" style="float:left;"/>	
								<input type="file"
								name="paymentVoucher" id="paymentVoucher" upload-img
								class="sp-upload-img" />
							</span>
						</div>
					</div>
				</div>
			</div>
			<div class="portlet-body">{{userInfo.userName}}个人用户</div>
			<div class="portlet-body">
				管理权限：
				   <span ng-if="userInfo.groupId=='2'">总监</span> 
				   <span ng-if="userInfo.groupId=='3'">员工</span> 
				   <span ng-if="userInfo.groupId=='4'">财务</span> 
				   <span ng-if="userInfo.groupId=='5'">人事</span> 
				   <span ng-if="userInfo.groupId=='6'">经理</span> 
				   <span ng-if="userInfo.groupId=='7'">超级管理员</span> 
				   <span ng-if="userInfo.groupId=='11'">采购部</span> 
				   <span ng-if="userInfo.groupId=='12'">FT事业部</span> 
				   <span ng-if="userInfo.groupId=='13'">财务部</span> 
				   <span ng-if="userInfo.groupId=='14'">仓储部</span> 
				   <span ng-if="userInfo.groupId=='15'">供应商</span> 
				   <span ng-if="userInfo.groupId=='17'">评审</span> 
				&nbsp;&nbsp;&nbsp; <i class="fa fa-edit"
					ng-click="editSignContract($event)"></i>

			</div>
			<div class="portlet-body">
				<span class="caption-subject font-green bold uppercase">新消息：</span>&nbsp;&nbsp;<a href="javascript:;" ui-sref="myMessage">{{messageCount}}</a>条&nbsp;|&nbsp;
				<span class="caption-subject font-green bold uppercase">公告：</span>&nbsp;&nbsp;<a href="javascript:;" ui-sref="myNotice">{{noticeCount}}</a>条&nbsp;|&nbsp;
				<!-- <span class="caption-subject font-green bold uppercase">任务：</span>&nbsp;&nbsp;4条 -->
			</div>

			<div class="portlet-body">
				<form action="#" id="form_sample_1" class="">
					<div class="table-scrollable">
						<table class="table table-bordered table-hover">
							<tbody>
							    <tr>
									<td style="width: 20%;"><span class="required"
										style="color: red;" aria-required="true"> * </span>账号</td>
									<td style="width: 80%;" class="form-group">
									<p class="form-control-static"> {{userInfo.userName}}</p>
										   </td>
								</tr>
								<tr>
									<td style="width: 20%;"><span class="required"
										style="color: red;" aria-required="true"> * </span> 姓名</td>
									<td style="width: 80%;" class="form-group">
									<input type="text" class="form-control" ng-model="userInfo.displayName"
										 name="userName" ng-show="input" />
									<p class="form-control-static" ng-show="span"> {{userInfo.displayName}}</p>
										</td>
								</tr>
								<tr>
									<td style="width: 20%;">性别</td>
									<td style="width: 80%;"><span ng-show="span">{{userInfo.sex}}</span>
										<label ng-show="input" class="mt-radio mt-radio-outline">男
											<input type="radio" value="男" name="sex"
											ng-checked="userInfo.sex=='男'" /> <span></span>
									</label> <label ng-show="input" class="mt-radio mt-radio-outline">女
											<input type="radio" value="女" name="sex"
											ng-checked="userInfo.sex=='女'" /> <span></span>
									</label></td>
								</tr>
								<tr>
									<td style="width: 20%;"><span class="required"
										style="color: red;" aria-required="true"> * </span>单位</td>
									<td style="width: 80%;">{{userInfo.company.comName}}</td>
								</tr>
								<tr>
									<td style="width: 20%;"><span class="required"
										style="color: red;" aria-required="true"> * </span>部门</td>
									<td style="width: 80%;">{{userInfo.department}}</td>
								</tr>
								<tr>
									<td style="width: 20%;"><span class="required"
										style="color: red;" aria-required="true"> * </span>职位</td>
									<td style="width: 80%;">{{userInfo.position}}</td>
								</tr>
								<tr>
									<td style="width: 20%;"><span class="required"
										style="color: red;" aria-required="true"> * </span>手机</td>
									<td style="width: 80%;">{{userInfo.cellPhone}}</td>
								</tr>
								<tr>
									<td style="width: 20%;">电话</td>
									<td style="width: 80%;" class="form-group">
									<span ng-show="span">{{userInfo.telephone}}</span>
										<input type="text" ng-model="userInfo.telephone"
										class="form-control" name="telephone" ng-show="input" /></td>
								</tr>
								<tr>
									<td style="width: 20%;">QQ</td>
									<td style="width: 80%;" class="form-group"><span ng-show="span">{{userInfo.qqnum}}</span>
										<input type="text" ng-model="userInfo.qqnum"
										class="form-control" name="qqNum" ng-show="input" /></td>
								</tr>
								<tr>
									<td style="width: 20%;">微信</td>
									<td style="width: 80%;">{{userInfo.weChatNum}}</td>
								</tr>
								<tr>
									<td style="width: 20%;">传真</td>
									<td style="width: 80%;" class="form-group"><span ng-show="span">{{userInfo.fax}}</span>
										<input type="text" ng-model="userInfo.fax"
										class="form-control" name="fax" ng-show="input" /></td>
								</tr>
								<tr>
									<td style="width: 20%;"><span class="required"
										style="color: red;" aria-required="true"> * </span>邮箱</td>
									<td style="width: 80%;">{{userInfo.email}}</td>
								</tr>
							</tbody>
						</table>
					</div>
				</form>
			</div>
		</div>
		<!-- END BORDERED TABLE PORTLET-->
	</div>
</div>
