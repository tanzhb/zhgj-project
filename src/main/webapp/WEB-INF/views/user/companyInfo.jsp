<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- BEGIN PAGE HEADER-->
<style>
.box_card {
	width: 33.333333%;
	float: left;
	margin-left: 0px;
	margin-right: 0px;
	margin-top: 4px;
	margin-bottom: 4px;
}

.left {
	float: left;
}
</style>
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN EXAMPLE TABLE PORTLET-->
		<div class="portlet light portlet-fit">
			<div class="portlet-title">
				<div class="caption">
					<span class="caption-subject font-green bold uppercase">公司信息</span>
				</div>
				<div class="portlet-title" style="min-height: 48px;">
					<div class="tools" style="float: right" id="noprintdiv">
						<button ng-click="edit()" type="button"
							class="btn purple  btn-circle  btn-sm">
							<i class="fa fa-edit"></i> 修改
						</button>

						<button type="submit" ng-click="updateCompanyInfo()"
							class="btn green  btn-circle  btn-sm" ng-if="company.comNum!=null&&company.comNum!=''">
							<i class="fa fa-save"></i> 保存
						</button>
					</div>
				</div>
			</div>
			<div class="portlet-body">
				<div class="tab-content">
					<div class="tab-pane fade active in" id="tab_1_1">
						<div class="portlet-body form">
							<form action="#" id="form_sample_1" class="">
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