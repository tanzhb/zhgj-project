<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" %>
<!-- <meta http-equiv="cache-control" content="no-cache">   -->
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
 <!-- BEGIN PAGE HEADER-->
<h3 class="page-title"> 价格列表
    <small> 价格目录</small>
</h3>
<div class="page-bar">
    <ul class="page-breadcrumb">
        <li>
            <i class="fa fa-home"></i>
            <a ui-sref="dashboard">首页</a>
            <i class="fa fa-angle-right"></i>
        </li>
        <li>
            <a ui-sref="datatablesmanaged">基础数据</a>
            <i class="fa fa-angle-right"></i>
        </li>
        <li>
            <a ui-sref="datatablesmanaged">价格列表</a>
        </li>
    </ul>
</div>
<div class="tabbable-line">
    <ul class="nav nav-tabs">
    	<shiro:hasPermission name="zhgj:buyPrice:*">
        <li  id="buy" >
            <a data-target="#tab_buy" data-toggle="tab"  ng-click="showBuyOrSale('buy')">采购价格</a>
        </li>
        </shiro:hasPermission>
        <shiro:hasPermission name="zhgj:salePrice:*">
        <li  id="sale">
            <a data-target="#tab_sale" data-toggle="tab"   ng-click="showBuyOrSale('sale')">销售价格</a>
        </li>
        </shiro:hasPermission>
    </ul>
    <div class="tab-content">
    	<!-- 进项票列表---START -->
        <div class="tab-pane active" id="tab_buy">
        <!-- BEGIN EXAMPLE TABLE PORTLET-->
		<div class="portlet light">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe font-green"></i>
					<span class="caption-subject font-green bold uppercase">采购价格列表</span>
				</div>
				<div class="actions">
				<div class="btn-group btn-group-devided" data-toggle="buttons">
					<shiro:hasPermission name="buyPrice:add">
						<label class="btn btn-transparent green btn-circle btn-sm" ng-click="addPriceList('buy')">
	                                              <i class="fa fa-plus"></i> 添加</label>
	                </shiro:hasPermission>
	                <shiro:hasPermission name="buyPrice:edit">
						<label class="btn btn-transparent purple btn-circle btn-sm" ng-click="toEditPriceListPage('buy')">
	                                              <i class="fa fa-edit"></i> 修改</label>
	                </shiro:hasPermission>
	                <shiro:hasPermission name="buyPrice:delete">
						<label class="btn btn-transparent red btn-circle btn-sm" ng-click="delPriceList('buy')" >
	                                              <i class="fa fa-minus"></i> 删除</label>
	                </shiro:hasPermission>
	                <shiro:hasPermission name="buyPrice:import">
						<label class="btn btn-transparent green btn-outline btn-circle btn-sm" data-toggle="modal" data-target="#import" >
	                                              <i class="fa fa-upload"></i> 导入</label>
					</shiro:hasPermission>	    
					<shiro:hasPermission name="buyPrice:export">                                          
						<label class="btn btn-transparent yellow-casablanca btn-outline btn-circle btn-sm" ng-click="exportPriceList('buy')">
	                                              <i class="fa fa-file-excel-o"></i> 导出</label>
	                 </shiro:hasPermission>
	                 </div>
				</div>
			</div>

			<!-- 删除采购价格start -->
				<div id="delPriceListbuyModal" class="modal fade" tabindex=

				data-backdrop="static" data-keyboard="false">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true"></button>
							<h4 class="modal-title">确认</h4>
						</div>
						<div class="modal-body">
							<p>是否删除已选采购价格?</p>
						</div>
						<div class="modal-footer">
							<button type="button" data-dismiss="modal"
								class="btn dark btn-outline">取消</button>
							<button type="button" ng-click="confirmDelPriceList('buy')" class="btn green">确定
								</button>
						</div>
					</div>
				</div>
			</div>
<!-- 删除采购价格end -->
			
			<div class="portlet-body">
				<table
					class="table table-striped table-bordered table-hover "
					id="sample_buy">
					<thead>
					<tr>
							<th style="text-align: center"><input name="buy"
								value="1" id="example-select-buy-all" type="checkbox" /></th>
							<th>价格编号 </th>
                            <th> 供应商名称</th>
                            <th> 物料编号</th>
                            <th>产品名称</th>
                            <th>规格型号</th>
                             <th>价格类型</th>
                            <th> 单位 </th>
                            <th>单价 </th>
                            <th> 税率 </th>
                             <th>币种 </th>
                            <th> 价格有效期 </th>
                            <th>价格失效期 </th>
                            <th> 状态 </th>
						</tr>
						
					</thead>
					
					<tbody>
					</tbody>
				</table>
			</div>
		</div>
		<!-- END EXAMPLE TABLE PORTLET-->
        </div>
        <!-- 进项票列表---END -->
        
        <!-- 出库检验列表---START -->
        <div class="tab-pane" id="tab_sale">
         <!-- BEGIN EXAMPLE TABLE PORTLET-->
		<div class="portlet light">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe font-green"></i>
					<span class="caption-subject font-green bold uppercase">销售价格列表</span>
				</div>
				<div class="actions">
				<div class="btn-group btn-group-devided" data-toggle="buttons">
					<shiro:hasPermission name="salePrice:add">
						<label class="btn btn-transparent green btn-circle btn-sm" ng-click="addPriceList('sale')">
	                                              <i class="fa fa-plus"></i> 添加</label>
	                </shiro:hasPermission>
	                <shiro:hasPermission name="salePrice:edit">
						<label class="btn btn-transparent purple btn-circle btn-sm" ng-click="toEditPriceListPage('sale')">
	                                              <i class="fa fa-edit"></i> 修改</label>
	                </shiro:hasPermission>
	                <shiro:hasPermission name="salePrice:delete">
						<label class="btn btn-transparent red btn-circle btn-sm" ng-click="delPriceList('sale')" >
	                                              <i class="fa fa-minus"></i> 删除</label>
	                </shiro:hasPermission>
	                <shiro:hasPermission name="salePrice:import">
						<label class="btn btn-transparent green btn-outline btn-circle btn-sm" data-toggle="modal" data-target="#import" >
	                                              <i class="fa fa-upload"></i> 导入</label>
	                </shiro:hasPermission>
	                <shiro:hasPermission name="salePrice:export">
						<label class="btn btn-transparent yellow-casablanca btn-outline btn-circle btn-sm" ng-click="exportPriceList('sale')">
	                                              <i class="fa fa-file-excel-o"></i> 导出</label>
	                </shiro:hasPermission>
	                 </div>
				</div>
			</div>
<!-- 删除销售价格start -->
				<div id="delPriceListsaleModal" class="modal fade" tabindex=

				data-backdrop="static" data-keyboard="false">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true"></button>
							<h4 class="modal-title">确认</h4>
						</div>
						<div class="modal-body">
							<p>是否删除已选销售价格?</p>
						</div>
						<div class="modal-footer">
							<button type="button" data-dismiss="modal"
								class="btn dark btn-outline">取消</button>
							<button type="button" ng-click="confirmDelPriceList('sale')" class="btn green">确定
								</button>
						</div>
					</div>
				</div>
			</div>
<!-- 删除销售价格end -->
			<div class="portlet-body">
				<table
					class="table table-striped table-bordered table-hover "
					id="sample_sale">
					<thead>
						<tr>
							<th style="text-align: center"><input name="sale"
								value="1" id="example-select-sale-all" type="checkbox" /></th>
							<th>价格编号 </th>
                            <th> 采购商名称</th>
                            <th> 物料编号</th>
                            <th>产品名称</th>
                            <th>规格型号 </th>
                            <th>价格类型</th>
                            <th> 单位 </th>
                            <th>单价 </th>
                            <th> 税率 </th>
                             <th>币种 </th>
                            <th> 价格有效期 </th>
                            <th>价格失效期 </th>
                            <th> 状态 </th>
						</tr>
					</thead>
					
					<tbody>
					</tbody>
				</table>
			</div>
		</div>
		<!-- END EXAMPLE TABLE PORTLET-->
        </div>
        <!-- 出库检验列表---END -->
    </div>
</div>
<!-- <div class="row" >
	<div class="col-md-12">

		BEGIN EXAMPLE TABLE PORTLET
		<div class="portlet light">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe font-green"></i>
					<span class="caption-subject font-green bold uppercase">价格列表</span>
				</div>
				<div class="actions">
					<div class="btn-group btn-group-devided" data-toggle="buttons">
						<label class="btn btn-transparent green btn-circle btn-sm" ng-click="addPriceList()">
	                                              <i class="fa fa-plus"></i> 添加</label>
						<label class="btn btn-transparent purple btn-circle btn-sm" ng-click="toEditPriceListPage()">
	                                              <i class="fa fa-edit"></i> 修改</label>
						<label class="btn btn-transparent red btn-circle btn-sm" ng-click="delPriceList()" >
	                                              <i class="fa fa-minus"></i> 删除</label>
						<label class="btn btn-transparent green btn-outline btn-circle btn-sm" data-toggle="modal" data-target="#import" >
	                                              <i class="fa fa-upload"></i> 导入</label>
						<label class="btn btn-transparent yellow-casablanca btn-outline btn-circle btn-sm" ng-click="exportPriceList()">
	                                              <i class="fa fa-file-excel-o"></i> 导出</label>
	                 </div>
				<div class="actions">ui-sref="addPrice" 
					<button ng-click="addPriceList()"
						data-toggle="modal" class="btn btn-circle btn-sm green">
						<i class="fa fa-plus"></i> 添加
					</button><button ng-click="toEditPriceListPage()"
						class="btn btn-circle btn-sm purple"> <i
						class="fa fa-edit"></i> 修改
					</button> <button ng-click="delPriceList()" 
						data-toggle="modal" 
						class="btn btn-circle btn-sm red"> <i
						class="fa fa-minus"></i> 删除
					</button>
					 
					<div class="btn-group">
						<a class="btn btn-default btn-outline btn-circle"
							href="javascript:;" data-toggle="dropdown"> <i
							class="fa fa-share"></i> <span class="hidden-xs"> 其它 </span> <i
							class="fa fa-angle-down"></i>
						</a>
						<ul class="dropdown-menu pull-right" id="sample_3_tools">
												<li><a data-action="0"
													class="tool-action" data-toggle="modal" data-target="#import"> <i class="fa fa-upload"></i> 导入
												</a></li> 
												<li><a href="javascript:;" data-action="1"
													class="tool-action" ng-click="exportPriceList()"> <i class="fa fa-file-excel-o"></i> 导出
												</a></li>
												<li><a href="javascript:;" data-action="2"
													class="tool-action" > <i class="fa fa-print"></i> 打印
												</a></li> 
											</ul>
					</div>
				</div>
			</div>


					导入modal START
<div class="modal fade" id="import" role="import" aria-hidden="true">
     <div class="modal-dialog" >
	    <div class="modal-content">
	 		<div class="modal-header">
	            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
	            <h4 class="modal-title" >	价格信息导入</h4>
	        </div>
	        <div class="modal-body">
	          		<div class="col-md-12">
	          		 <div class="">
                           <div class="portlet-body form">
                               BEGIN FORM
                               <form class="form-horizontal" role="form">
                                   <div class="form-body">
                                   		<form id="fileImport" method="post" enctype="multipart/form-data" >
	                                       <div class="row">
	                                           <div class="col-md-2">
	                                               <div class="form-group">
	                                               		<div class="col-md-4">
	                                               		</div>
	                                               		<div class="col-md-12">
	                                               			<button type="button" class="btn blue" ng-click="downloadImportTemp()">下载模板</button>
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
	                                               			<button type="button" class="btn blue" ng-click="uploadExcel()">导入</button>
	                                               		</div>
	                                               </div>
	                                           </div>
	                                           
	                                           /span
	                                       </div>
	                                       /row
                                       </form>
                                   </div>
                               </form>
                               END FORM
                           </div>
                      </div>
					</div>
	        </div>
	    </div>
    </div>
</div>
</div>
导入modal END

			删除用户modal 开始
			<div id="delPriceListModal" class="modal fade" tabindex=

				data-backdrop="static" data-keyboard="false">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true"></button>
							<h4 class="modal-title">确认</h4>
						</div>
						<div class="modal-body">
							<p>是否删除已选价格?</p>
						</div>
						<div class="modal-footer">
							<button type="button" data-dismiss="modal"
								class="btn dark btn-outline">取消</button>
							<button type="button" ng-click="confirmDelPriceList()" class="btn green">确定
								</button>
						</div>
					</div>
				</div>
			</div>
			删除用户modal 结束


			<div class="portlet-body">
				<table
					class="table table-bordered" 
					id="sample_priceList">
					<thead>
						<tr>
							<th style="text-align: center"><input name="select_all"
								value="1" id="example-select-all" type="checkbox" /></th>
							<th>价格编号 </th>
                            <th> 供应商名称</th>
                            <th> 物料编号</th>
                            <th>产品名称</th>
                            <th>规格型号 </th>
                            <th> 单位 </th>
                            <th>单价 </th>
                            <th> 税率 </th>
                             <th>币种 </th>
                            <th> 价格有效期 </th>
                            <th>价格失效期 </th>
                            <th> 状态 </th>
						</tr>
					</thead>
					
					<tbody>
					</tbody>
				</table>
			</div>
		</div>
		END EXAMPLE TABLE PORTLET
	</div>
</div>
<div class="row" ng-controller="PriceListController">
	<div class="col-md-12">

			仓库信息弹框查看开始
<div class="modal fade" id="viewPriceList" role="basic" aria-hidden="true">
     <div class="modal-dialog" style="width: 750px;">
	    <div class="modal-content">
	 		<div class="modal-header">
	            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
	            <h4 class="modal-title" >价格信息</h4>
	        </div>
	        <div class="modal-body">
	          		<div class="col-md-12">
	          		 <div class="">
                           <div class="portlet-body form">
                               BEGIN FORM
                               <form class="form-horizontal" role="form">
                                   <div class="form-body">
                                       <div class="row">
                                           <div class="col-md-6">
                                               <div class="form-group">
                                                   <label class="control-label col-md-5">价格编号：</label>
                                                   <div class="col-md-7">
                                                       <p class="form-control-static" >{{priceList.priceNum}}</p>
                                                   </div>
                                               </div>
                                           </div>
                                           /span
                                           <div class="col-md-6">
                                               <div class="form-group">
                                                   <label class="control-label col-md-5">描述：</label>
                                                   <div class="col-md-7">
                                                       <p class="form-control-static">{{priceList.priceDescribe}}</p>
                                                   </div>
                                               </div>
                                           </div>
                                           /span
                                       </div>
                                       /row
                                       <div class="row">
                                           <div class="col-md-6">
                                               <div class="form-group">
                                                   <label class="control-label col-md-5">物料编号：</label>
                                                   <div class="col-md-7">
                                                       <p class="form-control-static">{{priceList.materielNum}}</p>
                                                   </div>
                                               </div>
                                           </div>
                                           /span
                                           <div class="col-md-6">
                                               <div class="form-group">
                                                   <label class="control-label col-md-5">物料名称：</label>
                                                   <div class="col-md-7">
                                                       <p class="form-control-static">{{priceList.materielName}}</p>
                                                   </div>
                                               </div>
                                           </div>
                                           /span
                                       </div>
                                       /row
                                       <div class="row">
                                           <div class="col-md-6">
                                               <div class="form-group">
                                                   <label class="control-label col-md-5">规格型号：</label>
                                                   <div class="col-md-7">
                                                       <p class="form-control-static">{{priceList.specifications}}</p>
                                                   </div>
                                               </div>
                                           </div>
                                           /span
                                           <div class="col-md-6">
                                               <div class="form-group">
                                                   <label class="control-label col-md-5">单位：</label>
                                                   <div class="col-md-7">
                                                       <p class="form-control-static">{{priceList.unit}}</p>
                                                   </div>
                                               </div>
                                           </div>
                                           /span
                                       </div>
                                       /row
                                       <div class="row">
                                           <div class="col-md-6">
                                               <div class="form-group">
                                                   <label class="control-label col-md-5">价格类型：</label>
                                                   <div class="col-md-7">
                                                   <p class="form-control-static"  ng-if="priceList.priceType=='buyPrice'" >采购价格</p>
                                                     <p class="form-control-static"   ng-if="priceList.priceType=='salePrice'" >  销售价格</p>
                                                      
                                                   </div>
                                               </div>
                                           </div>
                                           /span
                                           <div class="col-md-6">
                                               <div class="form-group"  ng-if="priceList.priceType=='salePrice'">
                                                   <label class="control-label col-md-5">采购商：</label>
                                                   <div class="col-md-7">
                                                       <p class="form-control-static">{{priceList.buyComName}}</p>
                                                   </div>
                                               </div>
                                               <div class="form-group" ng-if="priceList.priceType=='buyPrice'">
                                                   <label class="control-label col-md-5">供应商：</label>
                                                   <div class="col-md-7">
                                                       <p class="form-control-static">{{priceList.supplyComName}}</p>
                                                   </div>
                                               </div>
                                           </div>
                                           /span
                                       </div>
                                       /row
                                       <div class="row">
                                           <div class="col-md-6">
                                               <div class="form-group">
                                                   <label class="control-label col-md-5">币种：</label>
                                                   <div class="col-md-7">
                                                       <p class="form-control-static">{{priceList.currency}}</p>
                                                   </div>
                                               </div>
                                           </div>
                                           /span
                                           <div class="col-md-6">
                                               <div class="form-group">
                                                   <label class="control-label col-md-5">税率：</label>
                                                   <div class="col-md-7">
                                                       <p class="form-control-static">{{priceList.rate}}%</p>
                                                   </div>
                                               </div>
                                           </div>
                                           /span
                                       </div>
                                       /row
                                       <div class="row">
                                           <div class="col-md-6">
                                               <div class="form-group">
                                                   <label class="control-label col-md-5">单价：</label>
                                                   <div class="col-md-7">
                                                       <p class="form-control-static">{{priceList.unitPrice}}</p>
                                                   </div>
                                               </div>
                                           </div>
                                           /span
                                           <div class="col-md-6">
                                               <div class="form-group">
                                                   <label class="control-label col-md-5">含税价格：</label>
                                                   <div class="col-md-7">
                                                       <p class="form-control-static">{{priceList.inclusivePrice}}</p>
                                                   </div>
                                               </div>
                                           </div>
                                           /span
                                       </div>
                                       /row
                                   </div>
                               </form>
                               END FORM
                           </div>
                      </div>
					</div>
	        </div>
	    </div>
    </div>
</div>
</div>
仓库信息查看modal END
		</div>
		END EXAMPLE TABLE PORTLET
	</div>
</div> -->

       <script>
    /* TableDatatablesManaged.init(); */
    function MyCtrl($scope, $location) {

        $scope.jumpToUrl = function(path) {

        	$location.path(path);

        };

    }
</script>    
                            