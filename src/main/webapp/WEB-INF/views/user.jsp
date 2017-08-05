<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="page-bar">
    <ul class="page-breadcrumb">
        <li>
            <i class="fa fa-home"></i>
            <a ui-sref="dashboard">首页</a>
            <i class="fa fa-angle-right"></i>
        </li>
        <li>
            <a ui-sref="datatablesmanaged">系统管理</a>
            <i class="fa fa-angle-right"></i>
        </li>
        <li>
            <a ui-sref="datatablesmanaged">用户管理</a>
        </li>
    </ul>
    
</div>
<div class="row" ng-controller="UserController as uTrl">
	<div class="col-md-12">

		<!-- BEGIN EXAMPLE TABLE PORTLET-->
		<div class="portlet box red">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-list"></i>用户列表
				</div>
				<div class="actions">
					<a href="javascript:;" ng-click="add()" class="btn btn-default btn-sm btn-circle">
						<i class="fa fa-plus"></i> 添加
					</a><a href="javascript:;" ng-click="edit()"
						class="btn btn-default btn-sm btn-circle"> <i
						class="fa fa-edit"></i> 修改
					</a><a href="javascript:;" ng-click="del()"
						class="btn btn-default btn-sm btn-circle"> <i
						class="fa fa-minus"></i> 删除
					</a>
					<div class="btn-group">
						<a class="btn btn-default btn-outline btn-circle"
							href="javascript:;" data-toggle="dropdown"> <i
							class="fa fa-share"></i> <span class="hidden-xs"> 其它 </span> <i
							class="fa fa-angle-down"></i>
						</a>
						<ul class="dropdown-menu pull-right" id="sample_3_tools">
							<li><a href="javascript:;" data-action="0"
								class="tool-action"> <i class="icon-printer"></i> Print
							</a></li>
							<li><a href="javascript:;" data-action="1"
								class="tool-action"> <i class="icon-check"></i> Copy
							</a></li>
							<li><a href="javascript:;" data-action="2"
								class="tool-action"> <i class="icon-doc"></i> PDF
							</a></li>
							<li><a href="javascript:;" data-action="3"
								class="tool-action"> <i class="icon-paper-clip"></i> Excel
							</a></li>
							<li><a href="javascript:;" data-action="4"
								class="tool-action"> <i class="icon-cloud-upload"></i> CSV
							</a></li>
							<li class="divider"></li>
							<li><a href="javascript:;" data-action="5"
								class="tool-action"> <i class="icon-refresh"></i> Reload
							</a></li>
							</li>
						</ul>
					</div>
				</div>
			</div>


			<!-- 添加用户modal 开始 -->
			<div id="addUserModal" class="modal fade" role="dialog"
				aria-hidden="true">
				<div class="modal-dialog" style="width:50%">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true" ></button>
								<div id="appendTitle"></div>
							<!-- <h4 class="modal-title">添加用户</h4> -->
						</div>
						<div class="modal-body form">
							
							
							<form action="#" class="form-horizontal" id="form_sample_1" >
                                            <div class="alert alert-danger display-hide">
                                                <button class="close" data-close="alert"></button> 请先输入正确数据！ </div>
                                            <!-- <div class="alert alert-success display-hide">
                                                <button class="close" data-close="alert"></button> 验证通过 </div> -->
                                            
                                            
                                            <!-- <div class="form-group form-md-line-input">
                                                <label class="col-md-3 control-label" for="form_control_1">用户名
                                                    <span class="required">*</span>
                                                </label>
                                                <div class="col-md-8">
                                                    <input type="text" class="form-control" onkeydown="if(event.keyCode==32) return false" ng-model="user.loginName" placeholder="" id="name3" name="name3">
                                                    <div class="form-control-focus"> </div>
                                                    <span class="help-block">输入用户名</span>
                                                </div>
                                            </div>
                                            <div class="form-group form-md-line-input">
                                                <label class="col-md-3 control-label" for="form_control_1">密码
                                                    <span class="required">*</span>
                                                </label>
                                                <div class="col-md-8">
                                                    <input type="text" class="form-control" ng-model="user.password" placeholder="" name="password" id="password">
                                                    <div class="form-control-focus"> </div>
                                                    <span class="help-block">输入密码</span>
                                                </div>
                                            </div> -->
                                            
                                            <div class="row">
                                                <div class="col-md-6">
                                                    <p>
                                                        <div class="form-group form-md-line-input">
			                                                <label class="col-md-4 control-label" for="form_control_1">用户名
			                                                    <span class="required">*</span>
			                                                </label>
			                                                <div class="col-md-7">
			                                                    <input type="text" class="form-control" onkeydown="if(event.keyCode==32) return false" ng-model="user.loginName" placeholder="" id="name3" name="name3">
			                                                    <div class="form-control-focus"> </div>
			                                                    <span class="help-block">输入用户名</span>
			                                                </div>
			                                            </div> 
		                                            </p> 
		                                            <p>
			                                            <div class="form-group form-md-line-input">
			                                                <label class="col-md-4 control-label" for="form_control_1">密码
			                                                    <span class="required">*</span>
			                                                </label>
			                                                <div class="col-md-7">
			                                                    <input type="password" class="form-control" ng-model="user.password" placeholder="" name="password" id="password">
			                                                    <div class="form-control-focus"> </div>
			                                                    <span class="help-block">输入密码</span>
			                                                </div>
			                                            </div> 
                                                    </p>   
                                                    <p>
                                                        <div class="form-group form-md-line-input">
			                                                <label class="col-md-4 control-label" for="form_control_1">身份证</label>
			                                                <div class="col-md-7">
			                                                    <input type="text" class="form-control" ng-model="user.idNumber" id="idNumber" name="idNumber" placeholder="">
			                                                    <div class="form-control-focus"> </div>
			                                                    <span class="help-block">输入身份证号码</span>
			                                                </div>
			                                            </div> 
                                                    </p>   
                                                    <p>
                                                        <div class="form-group form-md-line-input">
			                                                <label class="col-md-4 control-label" for="form_control_1">电话号码</label>
			                                                <div class="col-md-7">
			                                                    <input type="text" class="form-control" ng-model="user.telphone" id="telphone" name="telphone" placeholder="">
			                                                    <div class="form-control-focus"> </div>
			                                                    <span class="help-block">输入电话号码</span>
			                                                </div>
			                                            </div> 
                                                    </p>  
                                                    
                                                     <p>
                                                        <div class="form-group form-md-line-input">
			                                                <label class="col-md-4 control-label" for="form_control_1">是否删除</label>
			                                                <div class="col-md-7">
			                                                    <select class="form-control" ng-model="user.delFlg" id="delFlg" name="delFlg">
			                                                        <option value="1">是</option>
			                                                        <option value="0">否</option>
			                                                    </select>
			                                                    <div class="form-control-focus"> </div>
			                                                    <span class="help-block">选择是否删除</span>
			                                                </div>
			                                            </div> 
                                                    </p>                                            
                                                </div>
                                                
                                                <div class="col-md-6">
                                                    <p>
                                                        <div class="form-group form-md-line-input">
			                                                <label class="col-md-4 control-label" for="form_control_1">姓名</label>
			                                                <div class="col-md-7">
			                                                    <input type="text" class="form-control" ng-model="user.userName" placeholder="">
			                                                    <div class="form-control-focus"> </div>
			                                                    <span class="help-block">输入姓名</span>
			                                                </div>
			                                            </div> 
                                                    </p>                                                    
                                                    <p>
			                                            <div class="form-group form-md-line-input">
			                                                <label class="col-md-4 control-label" for="form_control_1">确认密码
			                                                    <span class="required">*</span>
			                                                </label>
			                                                <div class="col-md-7">
			                                                    <input type="password" class="form-control" placeholder="" id="twoPass" name="twoPass">
			                                                    <div class="form-control-focus"> </div>
			                                                    <span class="help-block">输入确认密码</span>
			                                                </div>
			                                            </div> 
                                                    </p>
                                                    <p>
                                                        <div class="form-group form-md-line-input">
			                                                <label class="col-md-4 control-label" for="form_control_1">岗位</label>
			                                                <div class="col-md-7">
			                                                    <input type="text" class="form-control" ng-model="user.station" placeholder="">
			                                                    <div class="form-control-focus"> </div>
			                                                    <span class="help-block">输入岗位</span>
			                                                </div>
			                                            </div> 
                                                    </p>
                                                    <p>
                                                        <div class="form-group form-md-line-input">
			                                                <label class="col-md-4 control-label" for="form_control_1">邮箱</label>
			                                                <div class="col-md-7">
			                                                    <input type="text" class="form-control" ng-model="user.email" id="email" name="email" placeholder="">
			                                                    <div class="form-control-focus"> </div>
			                                                    <span class="help-block">输入邮箱地址</span>
			                                                </div>
			                                            </div> 
                                                    </p>
                                                    <p>
                                                        <div class="form-group form-md-line-input">
			                                                <label class="col-md-4 control-label" for="form_control_1">状态</label>
			                                                <div class="col-md-7">
			                                                    <input type="text" class="form-control" ng-model="user.state" placeholder="">
			                                                    <div class="form-control-focus"> </div>
			                                                    <span class="help-block">输入状态</span>
			                                                </div>
			                                            </div> 
                                                    </p>
                                                </div>
                                            </div>
                                            
                                         <div class="row">  
                                         	<div class="col-md-12">   
	                                            <div class="form-group form-md-line-input">
	                                                <label class="col-md-2 control-label" for="form_control_1">备注</label>
	                                                <div class="col-md-10">
	                                                    <textarea class="form-control" ng-model="user.comments" name="comments" rows="3"></textarea>
	                                                    <div class="form-control-focus"> </div>
	                                                    <span class="help-block">输入备注信息</span>
	                                                </div>
	                                            </div>
                                           </div>
                                       </div>    
                                            
                                       <div class="modal-footer">
											<button type="reset" class="btn btn-default">重置</button>
											<button type="submit" ng-click="saveUser()"
												class="btn btn-primary">
												<i class="fa fa-check"></i> 保存
											</button>
										</div>
                                    </form>
						</div>
						
					</div>
				</div>
			</div>
			<!-- 添加用户modal 结束 -->

			<!-- 删除用户modal 开始 -->
			<div id="delUsersModal" class="modal fade" tabindex="-1"
				data-backdrop="static" data-keyboard="false">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true"></button>
							<h4 class="modal-title">确认</h4>
						</div>
						<div class="modal-body">
							<p>是否删除已选用户?</p>
						</div>
						<div class="modal-footer">
							<button type="button" data-dismiss="modal"
								class="btn dark btn-outline">取消</button>
							<button type="button" ng-click="confirmDel()" class="btn green">确定
								</button>
						</div>
					</div>
				</div>
			</div>
			<!-- 删除用户modal 结束 -->


			<div class="portlet-body">
				<table
					class="table table-striped table-bordered table-hover"
					id="sample_2">
					<thead>
						<tr>
							<th style="text-align: center"><input name="select_all"
								value="1" id="example-select-all" type="checkbox" /></th>

							<th>用户登录名</th>
							<th>密码</th>
							<th>用户姓名</th>
							<th>身份证号码</th>
							<th>用户岗位</th>
							<th>电话号码</th>
							<th>邮箱地址</th>
							<th>状态</th>							
							<th>是否删除</th>
							<th>创建人</th>
							<th>创建时间</th>
							<th>更新人</th>
							<th>更新时间</th>
							<th>备注</th>
						</tr>
					</thead>
					<!-- <tfoot>
						<tr>

							<th></th>
							<th>用户登录名</th>
							<th>密码</th>
							<th>用户姓名</th>
							<th>身份证号码</th>
							<th>用户岗位</th>
							<th>电话号码</th>
							<th>邮箱地址</th>
							<th>状态</th>							
							<th>是否删除</th>
							<th>创建人</th>
							<th>创建时间</th>
							<th>更新人</th>
							<th>更新时间</th>
							<th>备注</th>
						</tr>
					</tfoot> -->
					<tbody>
						
					</tbody>
				</table>
			</div>
		</div>
		<!-- END EXAMPLE TABLE PORTLET-->
	</div>
</div>

<script>
	
</script>