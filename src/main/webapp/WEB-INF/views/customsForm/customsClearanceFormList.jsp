<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" %>
<!-- <meta http-equiv="cache-control" content="no-cache">   -->
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>

<div class="tabbable-line">
		<!-- 	<ul class="nav nav-tabs " >
				<li class="active"><a href="#clearance" data-toggle="tab"
					> 清关单 </a></li>
			</ul> -->
        <!-- BEGIN EXAMPLE TABLE PORTLET-->
        <div class="tab-content">
				<div class="tab-pane active" id="clearance">
		<div class="portlet light">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe font-green"></i>
					<span class="caption-subject font-green bold uppercase">清关单 </span>
				</div>
				<div class="actions">
				<div class="btn-group btn-group-devided" data-toggle="buttons">
				<shiro:hasPermission name="customsClearanceFormList:confirm">
				<label class="btn btn-transparent yellow btn-circle btn-sm"
										ng-click="confirmCustomsForm('confirmclearance')"> <i class="glyphicon glyphicon-play"></i> 确认</label>
										</shiro:hasPermission>
					<shiro:hasPermission name="customsClearanceFormList:add">
						<label class="btn btn-transparent green btn-circle btn-sm" ng-click="addCustomsForm('clearance')">
	                                              <i class="fa fa-plus"></i> 添加</label>
	                </shiro:hasPermission>
	                <shiro:hasPermission name="customsClearanceFormList:edit">
						<label class="btn btn-transparent purple btn-circle btn-sm" ng-click="toEditCustomsFormPage('clearance')">
	                                              <i class="fa fa-edit"></i> 修改</label>
	                </shiro:hasPermission>
	                <shiro:hasPermission name="customsClearanceFormList:delete">
						<label class="btn btn-transparent red btn-circle btn-sm" ng-click="delCustomsForm('clearance')" >
	                                              <i class="fa fa-minus"></i> 删除</label>
	                </shiro:hasPermission>
	             <%--    <shiro:hasPermission name="buyPrice:import">
						<label class="btn btn-transparent green btn-outline btn-circle btn-sm" data-toggle="modal" data-target="#import" >
	                                              <i class="fa fa-upload"></i> 导入</label>
					</shiro:hasPermission> --%>	    
					<shiro:hasPermission name="customsClearanceFormList:export">                                          
						<label class="btn btn-transparent yellow-casablanca btn-outline btn-circle btn-sm" ng-click="exportCustomsForm('clearance')">
	                                              <i class="fa fa-file-excel-o"></i> 导出</label>
	                 </shiro:hasPermission>
	                 </div>
				</div>
			</div>

			<!-- 删除清关单start -->
				<div id="delclearanceModal" class="modal fade" tabindex=

				data-backdrop="static" data-keyboard="false">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true"></button>
							<h4 class="modal-title">确认</h4>
						</div>
						<div class="modal-body">
							<p>是否删除已选清关单?</p>
						</div>
						<div class="modal-footer">
							<button type="button" data-dismiss="modal"
								class="btn dark btn-outline">取消</button>
							<button type="button" ng-click="confirmDelCustomsForm('clearance')" class="btn green">确定
								</button>
						</div>
					</div>
				</div>
			</div>
<!-- 删除清关单end -->
						<!-- 导入modal START -->
<div class="modal fade" id="import" role="import" aria-hidden="true">
     <div class="modal-dialog" >
	    <div class="modal-content">
	 		<div class="modal-header">
	            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
	            <h4 class="modal-title" >清关单导入</h4>
	        </div>
	        <div class="modal-body">
	          		<!-- <div class="col-md-12"> -->
	          		 <div class="">
                           <div class="portlet-body form">
                              <!--  BEGIN FORM -->
                               <form class="form-horizontal" role="form">
                                   <div class="form-body">
                                   		<form id="fileImport" method="post" enctype="multipart/form-data" >
	                                       <div class="row">
	                                           <div class="col-md-2">
	                                               <div class="form-group">
	                                               		<!-- <div class="col-md-4">
	                                               		</div> -->
	                                               		<div class="col-md-12">
	                                               			<button type="button" class="btn blue" ng-click="downloadImportTemp('clearance')">下载模板</button>
	                                               		</div>
	                                               </div>
	                                           </div>
	                                           <div class="col-md-7">
	                                               <div class="form-group">
	                                               		 <div class="fileinput fileinput-new" data-provides="fileinput">
	                                                        <div class="input-group input-large">
	                                                            <div class="form-control uneditable-input input-fixed input-medium" data-trigger="fileinput">
	                                                                <i class="fa fa-file fileinput-exists"></i>&nbsp;
	                                                                <span class="fileinput-filename"> </span>
	                                                            </div>
	                                                            <span class="input-group-addon btn default btn-file" id="file_span">
	                                                                <span class="fileinput-new"> 选择文件 </span>
	                                                                <span class="fileinput-exists">更换</span>
	                                                                <input type="file" file-model="excelFile" accept=".xls" name="..."> </span>
	                                                            <a href="javascript:;" id="resetFile" class="input-group-addon btn red fileinput-exists" data-dismiss="fileinput"> 移除 </a>
	                                                        </div>
	                                                    </div>
	                                               </div>
	                                           </div>
	                                            <div class="col-md-2">
	                                               <div class="form-group">
	                                               		<div class="col-md-4">
	                                               			
	                                               		</div>
	                                               		<div class="col-md-8">
	                                               			<button type="button" class="btn blue" ng-click="uploadExcel('clearance')">导入</button>
	                                               		</div>
	                                               </div>
	                                           </div>
	                                           
	                                         <!--   /span -->
	                                       </div>
	                                       <!-- /row -->
                                       </form>
                                   </div>
                               </form>
                              <!--  END FORM -->
                           </div>
                      </div>
					<!-- </div> -->
	        <!-- </div> -->
	    </div>
    </div>
</div>
</div>
<!-- 导入modal END-->
			<div class="portlet-body">
				<table
					class="table table-striped table-bordered table-hover table-checkable order-column"
					id="sample_clearance">
					<thead>
					<tr>
					 <th>
			                  <label class="mt-checkbox mt-checkbox-single mt-checkbox-outline">
			                      <input type="checkbox"   
								class="group-checkable" data-set="#sample_clearance .checkboxes" />
			                         <span></span>
			                          </label>
			                                    </th>
							<th>清关单号 </th>
                            <th> 到港日期</th>
                            <th> 海关单位</th>
                            <th> 采购订单号</th>
                            <th>发货单号</th>
                            <th>发货金额</th>
                             <th>增值税</th>
                            <th> 关税 </th>
                            <th>代理清关单位 </th>
                            <th> 附件 </th>
                             <th>制单日期 </th>
                            <th>制单人 </th>
                            <th> 状态 </th>
						</tr>
						
					</thead>
					
					<tbody>
					</tbody>
				</table>
			</div>
		</div>
			</div>
		
		<!-- END EXAMPLE TABLE PORTLET-->
        <!-- 清关单列表---END -->
        
			
   </div>     
   
		</div>


          
                            