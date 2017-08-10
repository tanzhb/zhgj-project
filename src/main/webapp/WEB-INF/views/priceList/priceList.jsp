<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" %>
<!-- <meta http-equiv="cache-control" content="no-cache">   -->

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
        </li>
        <li>
            <a ui-sref="datatablesmanaged">价格列表</a>
        </li>
    </ul>
</div>

<div class="row" >
	<div class="col-md-12">

		<!-- BEGIN EXAMPLE TABLE PORTLET-->
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>价格列表
				</div>
				<div class="actions"><!-- ui-sref="addPrice"  -->
					<button ng-click="addPriceList()"
						data-toggle="modal" class="btn btn-default btn-sm btn-circle">
						<i class="fa fa-plus"></i> 添加
					</button><button ng-click="toEditPriceListPage()"
						class="btn btn-default btn-sm btn-circle"> <i
						class="fa fa-edit"></i> 修改
					</button> <button ng-click="delPriceList()" 
						data-toggle="modal" 
						class="btn btn-default btn-sm btn-circle"> <i
						class="fa fa-minus"></i> 删除
					</button>
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
			<div id="addWarehouseModal" class="modal fade" role="dialog"
				aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true"></button>
							<h4 id="pageTitle" class="modal-title">仓库详情</h4>
						</div>
						<div class="modal-body form">
							<form action="#" class="form-horizontal form-row-seperated"  id="form_sample_1">
								<div class="form-group">
									<label class="col-sm-2 control-label">价格编号</label>
									<div class="col-sm-4">
										<div class="input-group">
											<!-- <span class="input-group-addon"> <i class="fa fa-user"></i>
											</span> --> <input type="text" id="warehouseNum" name ="warehouseNum" 
												ng-model="warehouse.warehouseNum" 
												class="form-control" />
										</div>
										
									</div>
									<label class="col-sm-2 control-label">仓库名称</label>
									<div class="col-sm-4">
										<div class="input-group">
											<!-- <span class="input-group-addon"> <i class="fa fa-user"></i>
											</span> --> <input type="text" id="warehouseName" name ="warehouseName" 
												ng-model="warehouse.warehouseName" 
												class="form-control" />
										</div>
										
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">仓库类型</label>
									<div class="col-sm-4">
										<div class="input-group">
									<select class="form-control" data-placeholder="Choose a Category" tabindex="1"  id="warehouseType" name ="warehouseType"  ng-model="warehouse.warehouseType" >
                                                                         <option value="">	选择仓库</option>
                                                                        <option value="1">	自建仓库</option>
                                                                        <option value="2">代管仓库</option>
                                                                        <option value="3">境外仓库</option>
                                                                    </select>
										</div>
										
									</div>
									<label class="col-sm-2 control-label">仓库分类</label>
									<div class="col-sm-4">
										<div class="input-group">
										<select class="form-control" data-placeholder="Choose a Category" tabindex="1"  id="warehouseCategory" name ="warehouseCategory" ng-model="warehouse.warehouseCategory" >
                                                                          <option value="">选择分类</option>
                                                                        <option value="A">机加工</option>
                                                                        <option value="B">备品备件</option>
                                                                        <option value="C">标准品</option>
                                                                    </select>
											
										</div>
										
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">仓库地址</label>
									<div class="col-sm-4">
										<div class="input-group">
											<!-- <span class="input-group-addon"> <i class="fa fa-user"></i>
											</span> --> <input type="text" id="address" name ="address" 
												ng-model="warehouse.address" 
												class="form-control" />
										</div>
										
									</div>
									<label class="col-sm-2 control-label">仓库所有者</label>
									<div class="col-sm-4">
										<div class="input-group">
											<!-- <span class="input-group-addon"> <i class="fa fa-user"></i>
											</span> --> <input type="text" id="owner" name ="owner" 
												ng-model="warehouse.owner" 
												class="form-control" />
										</div>
										
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">仓库管理员</label>
									<div class="col-sm-4">
										<div class="input-group">
											<!-- <span class="input-group-addon"> <i class="fa fa-user"></i>
											</span> --> <input type="text" id="admin" name ="admin" 
												ng-model="warehouse.admin" 
												class="form-control" />
										</div>
										
									</div>
									<label class="col-sm-2 control-label">仓库面积</label>
									<div class="col-sm-4">
										<div class="input-group">
											<!-- <span class="input-group-addon"> <i class="fa fa-user"></i>
											</span> --> <input type="text" id="area" name ="area" 
												ng-model="warehouse.area" 
												class="form-control" />
										</div>
										
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">邮件</label>
									<div class="col-sm-4">
										<div class="input-group">
											<!-- <span class="input-group-addon"> <i class="fa fa-user"></i>
											</span> --> <input type="text" id="email" name ="email" 
												ng-model="warehouse.email" 
												class="form-control" />
										</div>
										
									</div>
									<label class="col-sm-2 control-label">电话</label>
									<div class="col-sm-4">
										<div class="input-group">
											<!-- <span class="input-group-addon"> <i class="fa fa-user"></i>
											</span> --> <input type="text" id="tel" name ="tel" 
												ng-model="warehouse.tel" 
												class="form-control" />
										</div>
										
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">备注</label>
									<div class="col-sm-4">
										<div class="input-group">
											<!-- <span class="input-group-addon"> <i class="fa fa-user"></i>
											</span> --> <input type="text" id="remark" name ="remark" 
												ng-model="warehouse.remark" 
												class="form-control" />
										</div>
										
									</div>
									<label class="col-sm-2 control-label">传真</label>
									<div class="col-sm-4">
										<div class="input-group">
											<!-- <span class="input-group-addon"> <i class="fa fa-user"></i>
											</span> --> <input type="text" id="fax" name ="fax" 
												ng-model="warehouse.fax" 
												class="form-control" />
										</div>
										
									</div>
								</div>
							</form>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">取消</button>
							<button type="button" ng-click="saveWarehouse()"
								class="btn btn-primary">
								<i class="fa fa-check"></i> 保存
							</button>
						</div>
					</div>
				</div>
			</div>
			<!-- 添加用户modal 结束 -->

			<!-- 删除用户modal 开始 -->
			<div id="delWarehouseModal" class="modal fade" tabindex=

				data-backdrop="static" data-keyboard="false">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true"></button>
							<h4 class="modal-title">确认</h4>
						</div>
						<div class="modal-body">
							<p>是否删除已选仓库?</p>
						</div>
						<div class="modal-footer">
							<button type="button" data-dismiss="modal"
								class="btn dark btn-outline">取消</button>
							<button type="button" ng-click="confirmDellWarehouse()" class="btn green">确定
								</button>
						</div>
					</div>
				</div>
			</div>
			<!-- 删除用户modal 结束 -->


			<div class="portlet-body">
				<table
					class="table table-striped table-bordered table-hover table-header-fixed"
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
					
					<tfoot>
						<tr>
							<th></th>
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
					</tfoot>
					<tbody>
					</tbody>
				</table>
			</div>
		</div>
		<!-- END EXAMPLE TABLE PORTLET-->
	</div>
</div>
<div class="row" ng-controller="PriceListController">
	<div class="col-md-12">

			<!-- 仓库信息弹框查看开始 -->
<div class="modal fade" id="viewPriceList" role="basic" aria-hidden="true">
     <div class="modal-dialog" style="width: 750px;">
	    <div class="modal-content">
	 		<div class="modal-header">
	            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
	            <h4 class="modal-title" >价格信息</h4>
	        </div>
	        <div class="modal-body">
	          		<!-- <div class="col-md-12"> -->
	          		 <div class="">
                           <div class="portlet-body form">
                              <!--  BEGIN FORM -->
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
                                           <!-- /span -->
                                           <div class="col-md-6">
                                               <div class="form-group">
                                                   <label class="control-label col-md-5">描述：</label>
                                                   <div class="col-md-7">
                                                       <p class="form-control-static">{{priceList.priceDescribe}}</p>
                                                   </div>
                                               </div>
                                           </div>
                                         <!--   /span -->
                                       </div>
                                       <!-- /row -->
                                       <div class="row">
                                           <div class="col-md-6">
                                               <div class="form-group">
                                                   <label class="control-label col-md-5">物料编号：</label>
                                                   <div class="col-md-7">
                                                       <p class="form-control-static">{{priceList.materielNum}}</p>
                                                   </div>
                                               </div>
                                           </div>
                                          <!--  /span -->
                                           <div class="col-md-6">
                                               <div class="form-group">
                                                   <label class="control-label col-md-5">物料名称：</label>
                                                   <div class="col-md-7">
                                                       <p class="form-control-static">{{priceList.materielName}}</p>
                                                   </div>
                                               </div>
                                           </div>
                                          <!--  /span -->
                                       </div>
                                      <!--  /row -->
                                       <div class="row">
                                           <div class="col-md-6">
                                               <div class="form-group">
                                                   <label class="control-label col-md-5">规格型号：</label>
                                                   <div class="col-md-7">
                                                       <p class="form-control-static">{{priceList.specifications}}</p>
                                                   </div>
                                               </div>
                                           </div>
                                        <!--    /span -->
                                           <div class="col-md-6">
                                               <div class="form-group">
                                                   <label class="control-label col-md-5">单位：</label>
                                                   <div class="col-md-7">
                                                       <p class="form-control-static">{{priceList.unit}}</p>
                                                   </div>
                                               </div>
                                           </div>
                                         <!--   /span -->
                                       </div>
                                     <!--   /row -->
                                       <div class="row">
                                           <div class="col-md-6">
                                               <div class="form-group">
                                                   <label class="control-label col-md-5">价格类型：</label>
                                                   <div class="col-md-7">
                                                       <p class="form-control-static">{{priceList.priceType}}</p>
                                                   </div>
                                               </div>
                                           </div>
                                      <!--      /span -->
                                           <div class="col-md-6">
                                               <div class="form-group"  ng-if="isBuy">
                                                   <label class="control-label col-md-5">采购商：</label>
                                                   <div class="col-md-7">
                                                       <p class="form-control-static">{{priceList.buyComName}}</p>
                                                   </div>
                                               </div>
                                               <div class="form-group" ng-if="isSupply">
                                                   <label class="control-label col-md-5">供应商：</label>
                                                   <div class="col-md-7">
                                                       <p class="form-control-static">{{priceList.supplyComName}}</p>
                                                   </div>
                                               </div>
                                           </div>
                                           <!-- /span -->
                                       </div>
                                      <!--  /row -->
                                       <div class="row">
                                           <div class="col-md-6">
                                               <div class="form-group">
                                                   <label class="control-label col-md-5">币种：</label>
                                                   <div class="col-md-7">
                                                       <p class="form-control-static">{{priceList.currency}}</p>
                                                   </div>
                                               </div>
                                           </div>
                                           <!-- /span -->
                                           <div class="col-md-6">
                                               <div class="form-group">
                                                   <label class="control-label col-md-5">税率：</label>
                                                   <div class="col-md-7">
                                                       <p class="form-control-static">{{priceList.rate}}%</p>
                                                   </div>
                                               </div>
                                           </div>
                                           <!-- /span -->
                                       </div>
                                       <!-- /row -->
                                       <div class="row">
                                           <div class="col-md-6">
                                               <div class="form-group">
                                                   <label class="control-label col-md-5">单价：</label>
                                                   <div class="col-md-7">
                                                       <p class="form-control-static">{{priceList.price}}</p>
                                                   </div>
                                               </div>
                                           </div>
                                         <!--   /span -->
                                           <div class="col-md-6">
                                               <div class="form-group">
                                                   <label class="control-label col-md-5">含税价格：</label>
                                                   <div class="col-md-7">
                                                       <p class="form-control-static">{{priceList.inclusivePrice}}</p>
                                                   </div>
                                               </div>
                                           </div>
                                           <!-- /span -->
                                       </div>
                                       <!-- /row -->
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
<!-- 仓库信息查看modal END-->
		</div>
		<!-- END EXAMPLE TABLE PORTLET-->
	</div>
</div>
<!-- <div class="row">
                        <div class="col-md-12">
                            BEGIN EXAMPLE TABLE PORTLET
                            <div class="portlet light portlet-fit ">
                                <div class="portlet-title">
                                    <div class="caption">
                                        <i class="icon-settings font-red"></i>
                                        <span class="caption-subject font-red sbold uppercase">Editable Table</span>
                                    </div>
                                    <div class="actions">
                                        <div class="btn-group btn-group-devided" data-toggle="buttons">
                                            <label class="btn btn-transparent red btn-outline btn-circle btn-sm active">
                                                <input type="radio" name="options" class="toggle" id="option1">Actions</label>
                                            <label class="btn btn-transparent red btn-outline btn-circle btn-sm">
                                                <input type="radio" name="options" class="toggle" id="option2">Settings</label>
                                        </div>
                                    </div>
                                </div>
                                <div class="portlet-body">
                                    <div class="table-toolbar">
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="btn-group">
                                                    <button id="sample_editable_1_new" class="btn green"> Add New
                                                        <i class="fa fa-plus"></i>
                                                    </button>
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="btn-group pull-right">
                                                    <button class="btn green btn-outline dropdown-toggle" data-toggle="dropdown">Tools
                                                        <i class="fa fa-angle-down"></i>
                                                    </button>
                                                    <ul class="dropdown-menu pull-right">
                                                        <li>
                                                            <a href="javascript:;"> Print </a>
                                                        </li>
                                                        <li>
                                                            <a href="javascript:;"> Save as PDF </a>
                                                        </li>
                                                        <li>
                                                            <a href="javascript:;"> Export to Excel </a>
                                                        </li>
                                                    </ul>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <table class="table table-striped table-hover table-bordered" id="sample_editable_1">
                                        <thead>
                                            <tr>
                                                <th> 库位编码 </th>
                                                <th>存储类型 </th>
                                                <th> 库位名称 </th>
                                                <th> 存储属性</th>
                                                <th> 最大行数 </th>
                                                <th> 最大列数 </th>
                                                <th> 最大层数 </th>
                                                <th>存储类型 </th>
                                                <th> 存储方式 </th>
                                                <th> 默认长宽高</th>
                                                <th> 默认承重 </th>
                                            </tr>
                                        </thead>
                                    </table>
                                </div>
                            </div>
                            END EXAMPLE TABLE PORTLET
                        </div> -->
       <script>
    /* TableDatatablesManaged.init(); */
    function MyCtrl($scope, $location) {

        $scope.jumpToUrl = function(path) {

        	$location.path(path);

        };

    }
</script>    
                            