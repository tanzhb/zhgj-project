<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN BORDERED TABLE PORTLET-->
		<div class="portlet light portlet-fit ">
		<ul class="nav nav-tabs">
		<li class="active bold">
               		<a data-target="#tab_1_1" data-toggle="tab">个人信息</a>
           		</li>
		<li class="bold" ng-hide="tab_1_2Hide"><a data-target="#tab_1_2" data-toggle="tab">公司信息</a></li>
		<li class="bold" ng-hide="tab_1_3Hide"><a data-target="#tab_1_3" data-toggle="tab">账户安全</a></li>
		<li class="bold" ng-hide="tab_1_4Hide"><a data-target="#tab_1_4" data-toggle="tab">操作日志</a></li>			
	</ul>
			<!-- <div class="portlet-title">
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
			</div> -->
<div class="tab-content"> 
		<div class="tab-pane fade active in" id="tab_1_1">
		<div class="row">
	<div class="col-md-12">
		<div class="portlet light portlet-fit ">
		<div class="portlet-title"  style="min-height: 48px;" >
					<div class="tools" style="float: right" id="noprintdiv">
						<button ng-click="editUserInfo()" type="button" ng-hide="userInfoEdit"
							class="btn purple  btn-circle  btn-sm">
							<i class="fa fa-edit"></i> 修改
						</button>

						<button type="submit" ng-click="updateUserInfo()" ng-show="userInfoInfoSave"
							class="btn green  btn-circle  btn-sm">
							<i class="fa fa-save"></i> 保存
						</button>
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
			</br></br></br></br></br>
			<div class="portlet-body">{{userInfo.userName}}个人用户</div>
			<div class="portlet-body">
				管理权限：{{userInfo.department}}
				   <!-- <span ng-if="userInfo.groupId=='2'">总监</span> 
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
				   <span ng-if="userInfo.groupId=='17'">评审</span>  -->
				&nbsp;&nbsp;&nbsp; <!-- <i class="fa fa-edit"
					ng-click="editSignContract($event)"></i> -->

			</div>
			<div class="portlet-body">
				<span class="caption-subject font-green bold uppercase">新消息：</span>&nbsp;&nbsp;<a href="javascript:;" ui-sref="myMessage">{{messageCount}}</a>条&nbsp;|&nbsp;
				<span class="caption-subject font-green bold uppercase">公告：</span>&nbsp;&nbsp;<a href="javascript:;" ui-sref="myNotice">{{noticeCount}}</a>条&nbsp;|&nbsp;
				<!-- <span class="caption-subject font-green bold uppercase">任务：</span>&nbsp;&nbsp;4条 -->
			</div>

			<div class="portlet-body">
				<form action="#" id="form_sample_userInfo" class="">
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
									<td style="width: 80%;"><span ng-show="span"  ng-if="userInfo.sex=='undefined'">暂未设置</span><span ng-show="span"  ng-if="userInfo.sex!='undefined'">{{userInfo.sex}}</span>
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
									<td style="width: 80%;" class="form-group">
									<input type="text" class="form-control" ng-model="userInfo.cellPhone"
										 name="cellPhone" ng-show="input" />
									<p class="form-control-static" ng-show="span"> {{userInfo.cellPhone}}</p>
										</td>
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
									<td style="width: 80%;" class="form-group">
									<input type="text" class="form-control" ng-model="userInfo.email"
										 name="email" ng-show="input" />
									<p class="form-control-static" ng-show="span"> {{userInfo.email}}</p>
										</td>
								</tr>
							</tbody>
						</table>
					</div>
				</form>
			</div></div></div></div>
			</div>
			<div class="tab-pane"  id="tab_1_2">
			<div class="row">
	<div class="col-md-12">
		<!-- BEGIN EXAMPLE TABLE PORTLET-->
		<div class="portlet light portlet-fit">
		<div class="portlet-title">
				<div class="portlet-title" style="min-height: 48px;">
					<div class="tools" style="float: right" id="noprintdiv">
						<button ng-click="editComPany()" type="button"
							class="btn purple  btn-circle  btn-sm"   ng-hide="companyInfoEdit">
							<i class="fa fa-edit"></i> 修改
						</button>

						<button type="submit" ng-click="updateCompanyInfo()"  ng-show="companyInfoSave"
							class="btn green  btn-circle  btn-sm" ><!-- ng-if="company.comNum!=null&&company.comNum!=''" -->
							<i class="fa fa-save"></i> 保存
						</button>
					</div>
				</div>
			</div>
				<div class="portlet-body">
				<div class="tab-content">
					<div class="tab-pane fade active in" id="tab_1_1">
						<div class="portlet-body form">
							<form action="#" id="form_sample_companyInfo" class="">
								<div class="form-body">
									<div class="alert alert-danger display-hide">
										<button class="close" data-close="alert"></button>
										请先输入正确数据！
									</div>
									<div class="row">
										<div class="col-md-4">
											<div class="form-group">
												<label class="control-label bold" for="comNum">企业编号
													<span class="required"> * </span>
												</label>
												<div class="">
													<input type="text" class="form-control" id="comNum"
														name="comNum" ng-model="company.comNum"
														ng-show="companyAdd">
													<div class="form-control-focus"></div>
													<p class="control-label left" ng-show="companyView">{{company.comNum}}</p>
												</div>
											</div>
										</div>

										<!--/span-->
										<div class="col-md-4">
											<div class="form-group">
												<label class="control-label bold" for="comName">企业名称
													<span class="required"> * </span>
												</label>
												<div class="">
													<input type="text" class="form-control" id="comName"
														name="comName" ng-model="company.comName"
														ng-show="companyAdd">
													<div class="form-control-focus"></div>
													<p class="control-label left" ng-show="companyView">{{company.comName}}</p>
												</div>
											</div>
										</div>
										<!--/span-->
										<div class="col-md-4">
											<div class="form-group">
												<label class="control-label bold" for="comType">企业类型
													<span class="required"> * </span>
												</label>
												<div class="">
													<select class="form-control" id="comType"
														ng-show="companyAdd" name="comType"
														ng-model="company.comType">
														<option value="1">采购商</option>
														<option value="2">供应商</option>
														<option value="3">承运人</option>
														<option value="4">外协仓</option>
														<option value="5">境外供应商</option>
														<option value="6">装卸公司</option>
														<option value="7">银行</option>
														<option value="8">保险公司</option>
													</select>
													<div class="form-control-focus"></div>
													<p class="control-label left" ng-show="companyView">{{company.comTypeName}}</p>
												</div>
											</div>
										</div>
										<!--/span-->
									</div>
									<!--/row-->
									
									<div class="row">
										<div class="col-md-4">
											<div class="form-group ">
												<label class="control-label bold" for="abbreviation">企业简称</label>
												<div class="">
													<input type="text" class="form-control" id="abbreviation"
														name="abbreviation" ng-model="company.abbreviation"
														ng-show="companyAdd">
													<div class="form-control-focus"></div>
													<p class="control-label left" ng-show="companyView">{{company.abbreviation}}</p>
												</div>
											</div>
										</div>
										<!--/span-->
										<div class="col-md-4">
											<div class="form-group ">
												<label class="control-label bold" for="businessNature">经营性质</label>
												<div class="">
													<select class="form-control" id="businessNature"
														ng-show="companyAdd" ng-model="company.businessNature">
														<option value="加工制造">加工制造</option>
														<option value="分销/交易">分销/交易</option>
														<option value="仓储供应">仓储供应</option>
													</select>
													<div class="form-control-focus"></div>
													<p class="control-label left" ng-show="companyView">{{company.businessNature}}</p>
												</div>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label class="control-label bold" for="comNature">企业性质
													<span class="required"> * </span>
												</label>
												<div class="">
													<select class="form-control" id="comNature"
														ng-show="companyAdd" name="comNature"
														ng-model="company.comNature">
														<option value="国有企业">国有企业</option>
														<option value="民营企业">民营企业</option>
														<option value="合资企业">合资企业</option>
														<option value="外资企业">外资企业</option>
													</select>
													<div class="form-control-focus"></div>
													<p class="control-label left" ng-show="companyView">{{company.comNature}}</p>
												</div>
											</div>
										</div>
									</div>
									<!--/row-->
									<div class="row">
										<div class="col-md-4">
											<div class="form-group">
												<label class="control-label bold" for="businessType">经营类型</label>
												<div class="">
													<input type="text" class="form-control" id="businessType"
														ng-model="company.businessType" ng-show="companyAdd">
													<div class="form-control-focus"></div>
													<p class="control-label left" ng-show="companyView">{{company.businessType}}</p>
												</div>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label class="control-label bold" for="registeredCapital">注册资金</label>
												<div class="">
													<!--   <input type="text" class="form-control" id="registeredCapital" name="registeredCapital" ng-model="company.registeredCapital" ng-show="companyAdd" > -->
													<select class="form-control" id="registeredCapital"
														ng-show="companyAdd" ng-model="company.registeredCapital">
														<option value="0-50万">0-50万</option>
														<option value="50万-100万">50万-100万</option>
														<option value="100万-200万">100万-200万</option>
														<option value="200万-500万">200万-500万</option>
														<option value="500万-1000万">500万-1000万</option>
														<option value="1000万以上">1000万以上</option>
													</select>
													<div class="form-control-focus"></div>
													<p class="control-label left" ng-show="companyView">{{company.registeredCapital}}</p>
												</div>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label class="control-label bold" for="legalPerson">企业法人姓名
													<span class="required"> * </span>
												</label>
												<div class="">
													<input type="text" class="form-control" id="legalPerson"
														name="legalPerson" ng-model="company.legalPerson"
														ng-show="companyAdd">
													<div class="form-control-focus"></div>
													<p class="control-label left" ng-show="companyView">{{company.legalPerson}}</p>
												</div>
											</div>
										</div>
									</div>
									<!--/row-->
									<div class="row">
									<div class="col-md-4">
											<div class="form-group">
												<label class="control-label bold" for="address">注册地址
													<span class="required"> * </span>
												</label>
												<div class="">
													<input type="text" class="form-control" id="address"
														name="address" ng-model="company.address"
														ng-show="companyAdd">
													<div class="form-control-focus"></div>
													<p class="control-label left" ng-show="companyView">{{company.address}}</p>
												</div>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label class="control-label bold" for="taxpayeNumber">纳税人识别号
													<span class="required"> * </span>
												</label>
												<div class="">
													<input type="text" class="form-control" id="taxpayeNumber"
														name="taxpayeNumber" ng-model="company.taxpayeNumber"
														ng-show="companyAdd">
													<div class="form-control-focus"></div>
													<p class="control-label left" ng-show="companyView">{{company.taxpayeNumber}}</p>
												</div>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label class="control-label bold" for="tel">联系电话</label>
												<div class="">
													<input type="text" class="form-control" id="tel" name="tel"
														ng-model="company.tel" ng-show="companyAdd">
													<div class="form-control-focus"></div>
													<p class="control-label left" ng-show="companyView">{{company.tel}}</p>
												</div>
											</div>
										</div>
									</div>
									<!--/row-->
									<div class="row">
										<div class="col-md-12">
											<div class="form-group">
												<label class="control-label bold" for="remark">公司简介</label>
												<div class="">
													<!-- <input type="text" class="form-control" id="remark"
														ng-model="company.remark" ng-show="companyAdd"> -->
													<textarea class="form-control" rows="4" id="remark" ng-model="company.remark" ng-show="companyAdd"></textarea>
													<div class="form-control-focus"></div>
													<p class="control-label left" ng-show="companyView">{{company.remark}}</p>
												</div>
											</div>
										</div>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
				<!-- END EXAMPLE TABLE PORTLET-->
			</div>
			</div>
			</div>
			</div>
					</div>
			<div class="tab-pane"   id="tab_1_3">
			<div class="row">
	<div class="col-md-12">
		<!-- BEGIN BORDERED TABLE PORTLET-->
		<div class="portlet light portlet-fit ">
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
<div id="basicMaterielInfo" class="modal fade bs-modal-lg" tabindex="-1"
	data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true"></button>
				<h4 class="caption-subject font-green bold uppercase">邮箱更改</h4>
			</div>
			<form action="#" id="form_sample_email" class="">
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
			<form action="#" id="form_sample_phone" class="">
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
			</div>
			<div class="tab-pane"   id="tab_1_4">
				<div class="row">
				<div class="col-md-12">
				        <div class="portlet light">
				            
							<div class="portlet-title">
							<div class="caption">
                            	<div class="input-group input-large date-picker input-daterange" data-date-format="yyyy-mm-dd">
                                    <input type="text" class="form-control" ng-model="startTime" name="from">
                                    <span class="input-group-addon"> to </span>
                                    <input type="text" class="form-control" ng-model="endTime" name="to"> 
                              </div>
                            </div>
                             <div class="caption" style="margin-left: 20px;">
                            	    <button  class="btn blue  btn-outline  btn-sm " ng-click="search()">
                                    	<i class="fa fa-search"></i> 查询 </button>
                        			<button  class="btn red  btn-outline  btn-sm " ng-click="resetSearchForm()">
                                    	<i class="fa fa-undo"></i> 重置 </button>
                           	 </div>
							
				            </div>
				            <div class="portlet-body">
				                <table class="table table-striped table-bordered table-hover table-checkable order-column" style="text-align: center" id="sample_2">
				                    <thead>
				                        <tr>
				                            <th style="text-align: center"> 操作 </th>
				                            <th style="text-align: center"> 终端IP </th>
				                            <th style="text-align: center"> 时间  </th>
				                        </tr>
				                    </thead>
				                    <tbody>
				                    </tbody>
				                </table>
				            </div>
				        </div>
			        <!-- END EXAMPLE TABLE PORTLET-->
			    </div>
			 </div>
			</div>
			</div>
		</div>
		<!-- END BORDERED TABLE PORTLET-->
	</div>
</div>
