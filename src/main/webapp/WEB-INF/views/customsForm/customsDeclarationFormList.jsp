<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" %>
<!-- <meta http-equiv="cache-control" content="no-cache">   -->
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>

<div class="tabbable-line">
   
    <div class="tab-content">
    	<!-- 采购价格列表---START -->
        <div class="tab-pane active" id="tab_buy">
        <div class="portlet-body">
			<div class="tabbable-custom ">
			<ul class="nav nav-tabs " id="buyPriceTab">
				<li class="active"><a href="#applyBuyPrice" data-toggle="tab"
					ng-click="toApplyBuyPrice()"> 报关单 </a></li>
			</ul>
        <!-- BEGIN EXAMPLE TABLE PORTLET-->
        <div class="tab-content">
				<div class="tab-pane active" id="applyBuyPrice">
		<div class="portlet light">
			<div class="portlet-title">
			<!-- 	<div class="caption">
					<i class="fa fa-globe font-green"></i>
					<span class="caption-subject font-green bold uppercase">采购价格列表</span>
				</div> -->
				<div class="actions">
				<div class="btn-group btn-group-devided" data-toggle="buttons">
				<label class="btn btn-transparent yellow btn-circle btn-sm"
										ng-click="submitPriceApply('buy')"> <i class="glyphicon glyphicon-play"></i> 确认</label>
					<shiro:hasPermission name="buyPrice:add">
						<label class="btn btn-transparent green btn-circle btn-sm" ng-click="addCustomsForm('declaration')">
	                                              <i class="fa fa-plus"></i> 添加</label>
	                </shiro:hasPermission>
	                <shiro:hasPermission name="buyPrice:edit">
						<label class="btn btn-transparent purple btn-circle btn-sm" ng-click="toEditCustomsFormPage('declaration')">
	                                              <i class="fa fa-edit"></i> 修改</label>
	                </shiro:hasPermission>
	                <shiro:hasPermission name="buyPrice:delete">
						<label class="btn btn-transparent red btn-circle btn-sm" ng-click="delCustomsForm('declaration')" >
	                                              <i class="fa fa-minus"></i> 删除</label>
	                </shiro:hasPermission>
	                <shiro:hasPermission name="buyPrice:import">
						<label class="btn btn-transparent green btn-outline btn-circle btn-sm" data-toggle="modal" data-target="#import" >
	                                              <i class="fa fa-upload"></i> 导入</label>
					</shiro:hasPermission>	    
					<shiro:hasPermission name="buyPrice:export">                                          
						<label class="btn btn-transparent yellow-casablanca btn-outline btn-circle btn-sm" ng-click="exportCustomsForm('declaration')">
	                                              <i class="fa fa-file-excel-o"></i> 导出</label>
	                 </shiro:hasPermission>
	                 </div>
				</div>
			</div>

			<!-- 删除清关单start -->
				<div id="deldeclarationModal" class="modal fade" tabindex=

				data-backdrop="static" data-keyboard="false">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true"></button>
							<h4 class="modal-title">确认</h4>
						</div>
						<div class="modal-body">
							<p>是否删除已选报关单?</p>
						</div>
						<div class="modal-footer">
							<button type="button" data-dismiss="modal"
								class="btn dark btn-outline">取消</button>
							<button type="button" ng-click="confirmDelCustomsForm('declaration')" class="btn green">确定
								</button>
						</div>
					</div>
				</div>
			</div>
<!-- 删除采购价格end -->
			
			<div class="portlet-body">
				<table
					class="table table-striped table-bordered table-hover table-checkable order-column"
					id="sample_declaration">
					<thead>
					<tr>
					 <th>
			                  <label class="mt-checkbox mt-checkbox-single mt-checkbox-outline">
			                      <input type="checkbox"   
								class="group-checkable" data-set="#sample_declaration .checkboxes" />
			                         <span></span>
			                          </label>
			                                    </th>
							<th>报关单号 </th>
                            <th> 到港日期</th>
                            <th> 海关单位</th>
                            <th>发货单号</th>
                            <th>发货金额</th>
                             <th>增值税</th>
                            <th> 关税 </th>
                            <th>代理报关单位 </th>
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
        <!-- 进项票列表---END -->
        
			
   </div>     
   </div> 
   </div> 
   </div> 
    
		</div>
		</div>


          
                            