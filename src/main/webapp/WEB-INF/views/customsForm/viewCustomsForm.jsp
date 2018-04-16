<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" %>
<!-- BEGIN PAGE HEADER-->
<style>
.box_card{
	width: 33.333333%;
	float: left;
	margin-left: 0px;
	margin-right: 0px;
	margin-top: 4px;
	margin-bottom: 4px;
}

.left{
	float: left;
}
</style>
<!-- <h3 class="page-title"> 价格信息
</h3> -->
<!-- <div class="page-bar">
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
            <a ui-sref="customsForm">价格信息</a>
        </li>
    </ul>

</div> -->
<!-- END PAGE HEADER-->
<!-- BEGIN MAIN CONTENT -->
<div class="row">
    <div class="col-md-12">
        <!-- BEGIN EXAMPLE TABLE PORTLET-->
        <div class="portlet bordered">
            <div class="portlet-body">
            			<ul class="nav nav-tabs">
		<li class="active bold">
               		<a data-target="#tab_1_1" data-toggle="tab"><span ng-if="customsFormType.indexOf('clearance')>-1">清关</span><span ng-if="customsFormType.indexOf('declaration')>-1">报关</span>信息</a>
           		</li>
		<li class="bold" ng-hide="tab_1_2Hide"><a data-target="#tab_1_2" data-toggle="tab">物料信息</a></li>
		<li class="bold" ng-hide="tab_1_3Hide"><a data-target="#tab_1_3" data-toggle="tab">附件</a></li>
		<li class="dropdown pull-right tabdrop">
			<button type="button" onclick="goBackPage()" class="btn defualt  btn-circle  btn-sm"><i class="fa fa-reply"></i>返回</button>
		</li>		
	</ul>
				<div class="portlet light ">
                        <!-- <div class="portlet-title" >
                            <div class="caption"><span ng-if="customsFormType.indexOf('clearance')>-1">清关</span><span ng-if="customsFormType.indexOf('declaration')>-1">报关</span>单信息</div> 
                            <div class="actions" >
                                <button  ng-show="customsFormView"       ng-if="customsFormType.indexOf('clearance')>-1"  class="btn purple  btn-sm btn-circle " ng-click="editCustomsForm('clearance')">
                                            <i class="fa fa-edit"></i> 编辑 </button>
                                            <button  ng-show="customsFormView"   ng-if="customsFormType.indexOf('declaration')>-1"  class="btn purple  btn-sm btn-circle " ng-click="editCustomsForm('declaration')">
                                            <i class="fa fa-edit"></i> 编辑 </button>
                                <button   ng-hide="customsFormEdit"     ng-if="customsFormType.indexOf('clearance')>-1"   class="btn defualt  btn-sm btn-circle" ng-click="cancelEditcustomsForm('clearance')">
                                            <i class="fa fa-undo"></i> 取消 </button>
                                              <button   ng-hide="customsFormEdit"     ng-if="customsFormType.indexOf('declaration')>-1"   class="btn defualt  btn-sm btn-circle" ng-click="cancelEditcustomsForm('declaration')">
                                            <i class="fa fa-undo"></i> 取消 </button>
                                <button  ng-hide="customsFormAdd"   type="submit"     ng-if="customsFormType.indexOf('clearance')>-1"   class="btn green  btn-sm btn-circle"   ng-click="saveCustomsForm('clearance')">
                                            <i class="fa fa-save"></i> 保存 </button>
                                             <button  ng-hide="customsFormAdd"   type="submit"     ng-if="customsFormType.indexOf('declaration')>-1"  class="btn green  btn-sm btn-circle"   ng-click="saveCustomsForm('declaration')">
                                            <i class="fa fa-save"></i> 保存 </button>
                            </div>
                        </div> -->
                     <div class="tab-content">
				<div class="tab-pane fade active in" id="tab_1_1"> 
				<div class="portlet-title" >
                            <div class="caption"><!-- <span ng-if="customsFormType.indexOf('clearance')>-1">清关</span><span ng-if="customsFormType.indexOf('declaration')>-1">报关</span>单信息 --></div> 
                            <div class="actions"  style="float:right">
                                <button  ng-show="customsFormEdit"       ng-if="customsFormType.indexOf('clearance')>-1"  class="btn purple  btn-sm btn-circle " ng-click="editCustomsForm('clearance')">
                                            <i class="fa fa-edit"></i> 编辑 </button>
                                            <button  ng-show="customsFormEdit"   ng-if="customsFormType.indexOf('declaration')>-1"  class="btn purple  btn-sm btn-circle " ng-click="editCustomsForm('declaration')">
                                            <i class="fa fa-edit"></i> 编辑 </button>
                                <button   ng-hide="customsFormAdd"     ng-if="customsFormType.indexOf('clearance')>-1"   class="btn defualt  btn-sm btn-circle" ng-click="cancelEditcustomsForm('clearance')">
                                            <i class="fa fa-undo"></i> 取消 </button>
                                              <button   ng-hide="customsFormAdd"     ng-if="customsFormType.indexOf('declaration')>-1"   class="btn defualt  btn-sm btn-circle" ng-click="cancelEditcustomsForm('declaration')">
                                            <i class="fa fa-undo"></i> 取消 </button>
                                <button  ng-hide="customsFormAdd"   type="submit"     ng-if="customsFormType.indexOf('clearance')>-1"   class="btn green  btn-sm btn-circle"   ng-click="saveCustomsForm('clearance')">
                                            <i class="fa fa-save"></i> 保存 </button>
                                             <button  ng-hide="customsFormAdd"   type="submit"     ng-if="customsFormType.indexOf('declaration')>-1"  class="btn green  btn-sm btn-circle"   ng-click="saveCustomsForm('declaration')">
                                            <i class="fa fa-save"></i> 保存 </button>
                            </div>
                        </div>
                        <div class="portlet-body form">
                            <form  id="customsForm"  >
								<div class="form-body">
									<div class="alert alert-danger display-hide">
                                               <button class="close" data-close="alert"></button>请先输入正确数据！</div>
								           <div class="row">
                                                             <div class="col-md-4">
											<div class="form-group">
                                                    <label class="control-label bold" for=""> <!-- <span class="required"> * </span> --><span   ng-if="customsFormType.indexOf('clearance')>-1" >清关单号 :</span> <span  ng-if="customsFormType.indexOf('declaration')>-1">报关单号 :</span></label>
                                                    <div class="">
                                                        <input type="text" class="form-control" id="customsFormNum" name="customsFormNum" ng-model="customsForm.customsFormNum"  ng-hide="customsFormAdd" />
                                                        <div class="form-control-focus"> </div>
                                                          <p class="control-label left" ng-show="customsFormView">{{customsForm.customsFormNum}}</p> 
                                                        <input type="hidden"  id="deliverSerial" ng-model="customsForm.deliverSerial"  /> <!-- 存放发货流水号 -->
                                                          <input type="hidden"  id="orderSerial" ng-model="customsForm.orderSerial"  /> <!-- 存放订单流水号 -->
                                                    </div>
                                            </div>
										</div>
										
                                                            <div class="col-md-4">
                                                                <div class="form-group">
                                                                <label class="control-label bold" for=""> <span   ng-if="customsFormType.indexOf('clearance')>-1" >关联收货计划单号 :</span> <span  ng-if="customsFormType.indexOf('declaration')>-1">关联发货计划单号 :</span></label>
                                                                    <div class="">
                                                                     <div class="input-group" data-toggle="modal" data-target="#takeDeliveryInfo" onclick="return false;">
	                                                        <input id="deliverNum"   name="deliverNum" type="text" class="form-control" ng-model="customsForm.deliverNum" readonly="readonly"  ng-hide="customsFormAdd"  >
	                                                        <span class="input-group-btn" style="vertical-align: top;" ng-hide="customsFormView">
	                                                            <button class="btn default" type="button">
	                                                                <i class="fa fa-search"></i>
	                                                            </button>
	                                                        </span>
	                                                    </div>
                                                                       <div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="customsFormView">{{customsForm.deliverNum}}</p> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                              <div class="col-md-4">
                                                                <div class="form-group">
                                                                <label class="control-label bold" for=""> <span   ng-if="customsFormType.indexOf('clearance')>-1" >关联采购单号 :</span> <span  ng-if="customsFormType.indexOf('declaration')>-1">关联销售单号 :</span></label>
                                                                    <div class="">
                                                                       <input type="text" class="form-control" placeholder=""  id="buyOrderNum" name ="buyOrderNum"  ng-hide="customsFormAdd"   readonly="readonly"
												ng-model="customsForm.buyOrderNum" > 
                                                                       <div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="customsFormView">{{customsForm.buyOrderNum}}</p> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-md-4">
                                                                <div class="form-group">
                                                    <label class="control-label bold" for="materielNum"> 发货金额 :</label>
                                                    <div class="">
												
	                                                        <input type="text" class="form-control"   id="deliverAmount" name ="deliverAmount"    ng-hide="customsFormAdd"   readonly="readonly"
												ng-model="customsForm.deliverAmount" /> 
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="customsFormView">{{customsForm.deliverAmount|currency:''}}</p> 
                                                                    </div>
                                                                  
                                                                </div>
                                                            </div>
                                                            <!--/span-->
                                                            <div class="col-md-4">
                                                            <div class="form-group">
                                                    <label class="control-label bold" for="customsFormCategory">增值税 :</label>
                                                    <div class="">
                                                                    <input type="text" class="form-control"   id="addedTax" name ="addedTax"  ng-hide="customsFormAdd"  readonly="readonly"
												ng-model="customsForm.addedTax" > 
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="customsFormView">{{customsForm.addedTax|currency:''}}</p> 
                                                                </div>
                                                            </div>
                                                            <!--/span-->
                                                        </div>
                                                          <div class="col-md-4">
                                                             <div class="form-group ">
                                                    <label class="control-label bold" for="address"> 关税:</label>
                                                    <div class="">
                                                                        <input type="text" class="form-control"   id="customsAmount" name ="customsAmount"  ng-hide="customsFormAdd"  readonly="readonly"
												ng-model="customsForm.customsAmount" > 
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="customsFormView">{{customsForm.customsAmount|currency:''}}</p> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <!--/row-->
                                                        <div class="row">
                                                          <div class="col-md-4">
                                                             <div class="form-group ">
                                                    <label class="control-label bold" for=""> 税额合计:</label>
                                                    <div class="">
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="customsFormView">{{customsForm.customsAmount+customsForm.addedTax|currency:''}}</p> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="col-md-4">
                                                            <div class="form-group">
                                                    <label class="control-label bold" for=""> 到港日期 :</label>
                                                    <div class="">
                                                                        <input type="text"  class="form-control" placeholder=""  id="playArrivalDate" name ="playArrivalDate"   ng-hide="customsFormAdd"  readonly="readonly"
												ng-model="customsForm.playArrivalDate" > 
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="customsFormView">{{customsForm.playArrivalDate}}</p> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                             <div class="col-md-4">
                                                            <div class="form-group">
                                                    <label class="control-label bold" for=""> 运单号 :</label>
                                                   <div class="">
                                                                    <input type="text" class="form-control"   id="shipNumber" name ="shipNumber"  ng-hide="customsFormAdd"  readonly="readonly"
												ng-model="customsForm.shipNumber" > 
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="customsFormView">{{customsForm.shipNumber}}</p> 
                                                                </div>
                                                                </div>
                                                            </div>
                                                          
                                                            <!--/span-->
                                                        </div>
                                                         <div class="row">
                                                            <div class="col-md-4">
                                                            <div class="form-group">
                                                    <label class="control-label bold" for="">海关单位 :</label>
                                                     <div class="">
                                                                    <input type="text" class="form-control"   id="port" name ="port"  ng-hide="customsFormAdd"  readonly="readonly"
												ng-model="customsForm.port" > 
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="customsFormView">{{customsForm.port}}</p> 
                                                                </div>
                                                                </div>
                                                            </div>
                                                            <div class="col-md-4">
                                                            <div class="form-group">
                                                    <label class="control-label bold" for=""> <!-- <span class="required"> * </span> --><span   ng-if="customsFormType.indexOf('clearance')>-1" >代理清关单位 :</span> <span  ng-if="customsFormType.indexOf('declaration')>-1">代理报关单位 :</span></label>
                                                     <div class="">
                                                     <div ng-hide="customsFormAdd">
                                                     <select class="form-control" id="agentUnit"  data-live-search="true"  name="agentUnit" class="form-control"  
                                 		 ng-model="customsForm.agentUnit"  data-size="8">
                                                   <option value=""></option>
                                                   <option  ng-repeat="supplier in suppliers" value="{{supplier.comName}}" >{{supplier.comName}}</option>
                                               </select>
                                               </div>
                                                                  <!--   <input type="text" class="form-control"   id="agentUnit" name ="agentUnit"  ng-hide="customsFormAdd"  readonly="readonly"
												ng-model="customsForm.agentUnit" >  -->
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="customsFormView">{{customsForm.agentUnit}}</p> 
                                                                </div>
                                                                </div>
                                                            </div>
                                                            <!--/span-->
                                                            <div class="col-md-4"  >
                                                             <div class="form-group ">
                                                    <label class="control-label bold" for="">备注 :</label>
                                                      <div class="">
                                                                    <input type="text" class="form-control"   id="remark" name ="remark"  ng-hide="customsFormAdd"  
												ng-model="customsForm.remark" > 
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="customsFormView">{{customsForm.remark}}</p> 
                                                                </div>
                                                            </div>
                                                            <!--/span-->
                                                        </div>
                                             
                                                        </div>
								</div>
							</form>
         				</div>
         				     </div>
         				     	<div class="tab-pane" id="tab_1_2"  ng-if="customsFormType.indexOf('declaration')>-1" >
	<!-- 物料信息start-->
      <div class="portlet-body"  >
				<table
					class="table table-striped table-bordered table-hover "
					id="sample_materiel">
					<thead>
						<tr>
							<td >商品编号</td>
                              <td >商品海关编号</td>
                               <td  >物料名称</td>
                                <td  >规格型号</td>
                                <td  >单位</td>
                                <td>发货数量</td>
                                 <td  >单价（不含税）</td>
                                <td  >币种</td>
                                  <td >金额</td>
                                  <td  >退税率(%)</td>
                                  <td  >税额</td>    
                                  <td  >关税率(%)</td>
                                   <td  >关税额</td>
						</tr>
					</thead>
					
					<tbody>
					</tbody>
					<tfoot><tr>
					<td colspan="3">合计</td>
                              <td colspan="10">金额: {{customsForm.deliverAmount |currency:''}} 增值税:{{customsForm.addedTax|currency:''}} 关税:{{customsForm.customsAmount|currency:''}} 税额合计:{{customsForm.totalTax|currency:''}}</td>
                              </tr></tfoot>
				</table>
			</div>
         <!-- 物料信息 end-->
	</div>
				     	<div class="tab-pane" id="tab_1_2"  ng-if="customsFormType.indexOf('clearance')>-1" >
	<!-- 物料信息start-->
      <div class="portlet-body"  >
				<table
					class="table table-striped table-bordered table-hover "
					id="sample_materiel">
					<thead>
						<tr>
							<td >商品编号</td>
                              <td >商品海关编号</td>
                               <td  >物料名称</td>
                                <td  >规格型号</td>
                                <td  >单位</td>
                                <td>收货数量</td>
                                 <td  >单价（不含税）</td>
                                <td  >币种</td>
                                  <td >金额</td>
                                  <td  >税率(%)</td>
                                  <td  >税额</td>    
                                  <td  >关税率(%)</td>
                                   <td  >关税额</td>
						</tr>
					</thead>
					
					<tbody>
					</tbody>
					<tfoot><tr>
					<td colspan="3">合计</td>
                              <td colspan="10">金额: {{customsForm.deliverAmount |currency:''}} 增值税:{{customsForm.addedTax|currency:''}} 关税:{{customsForm.customsAmount|currency:''}} 税额合计:{{customsForm.totalTax|currency:''}}</td>
                              </tr></tfoot>
				</table>
			</div>
         <!-- 物料信息 end-->
	</div>
			           <div class="tab-pane fade " id="tab_1_3">
                    <!-- 附件 start-->
          <div class="portlet-title" style="min-height: 48px;">
               <div class="tools" style="float:right">
               	 <!-- 	<button type="submit" ng-click="saveFile()" ng-hide="fileInfoInput" class="btn green  btn-circle  btn-sm">
                  		<i class="fa fa-save"></i> 保存 </button>
                  <button ng-click="cancelFile()" type="button" ng-hide="fileInfoInput" class="btn defualt  btn-circle  btn-sm">
                  		<i class="fa fa-undo"></i> 取消 </button>
                  <button ng-click="editFile()" type="button" ng-show="fileInfoShow&&noShow" class="btn purple  btn-circle  btn-sm">
                  		<i class="fa fa-edit"></i> 编辑 </button> -->
                </div>
            </div>
           <div class="portlet-body form">
			     <form id="form_sample_4"   >
				
	                  <div class="table-scrollable">
                          <table class="table table-bordered table-hover">
                              <thead>
                                  <tr>
                                      <th>附件类型</th>
                                      <th>描述</th>
                                      <th>文件</th>
                                      <th>备注</th>
                                      <th>上传人</th>
                                      <th>上传时间</th>
                                      <th style="width:100px;"></th>
                                  </tr>
                              </thead>
                              <tbody>
                                  <tr ng-repeat="_file in file track by $index" ng-mouseover="showOperation('file',$index)" ng-mouseleave="hideOperation('file',$index)">
			                          <td>
		                                 	<select class="form-control" id="fileType[$index]" name="fileType" class="form-control" ng-hide="fileInfoInput" ng-model="file[$index].fileType"  >
                                              <option value=""></option>
                                             	<option value="物料图片" >物料图片</option>
                                               <option value="图纸" >图纸</option>
                                               <option value="其他附件" >其他附件</option>
                                             </select>
			                                <p class="form-control-static" ng-show="fileInfoShow"> {{_file.fileType}} </p>
			                          </td>
			                           <td>
                                      		<input type="text" id="fileDescribe[$index]" name="fileDescribe" class="form-control" ng-hide="fileInfoInput" ng-model="file[$index].fileDescribe"  >
			                                <p class="form-control-static" ng-show="fileInfoShow"> {{_file.fileDescribe}} </p>
			                          </td>
                                       <td>
                                         <div ng-hide="fileInfoInput"   ng-if="file[$index].file==null||file[$index].file==''"  class="fileinput fileinput-new" data-provides="fileinput">
                                             <span class="btn blue btn-circle btn-file">
                                                 <span class="fileinput-new">上传附件</span>
                                                 <span class="fileinput-exists">更改</span>
                                                 <input type="file" name="..." nv-file-select uploader="uploader" onchange="angular.element(this).scope().up(this.files[0])" ng-model="file[$index].file" ng-click="uploadFile($index)" > </span>
                                             <span class="fileinput-filename">{{_file.file.substring(_file.file.indexOf("_")+1)}}</span> &nbsp;
                                             <a href="javascript:;" class="close fileinput-exists" ng-click="removefile($index)" data-dismiss="fileinput"> </a>
                                         </div>
                                         <div ng-hide="fileInfoInput"   ng-if="file[$index].file!=null&&file[$index].file!=''"  class="fileinput fileinput-exists" data-provides="fileinput">
                                             <span class="btn blue btn-circle btn-file">
                                                 <span class="fileinput-new">上传附件</span>
                                                 <span class="fileinput-exists">更改</span>
                                                 <input type="file" name="..." nv-file-select uploader="uploader" onchange="angular.element(this).scope().up(this.files[0])" ng-model="file[$index].file" ng-click="uploadFile($index)" > </span>
                                             <span class="fileinput-filename">{{_file.file.substring(_file.file.indexOf("_")+1)}}</span> &nbsp;
                                             <a href="javascript:;" class="close fileinput-exists"  ng-click="removefile($index)" data-dismiss="fileinput"> </a>
                                         </div>
                                       	<label   ng-show="fileInfoShow" ng-if="file[$index].file==null||file[$index].file==''" class="c_edit" >未上传附件</label>
                                       	<label   ng-show="fileInfoShow" ng-if="file[$index].file!=null&&file[$index].file!=''" class="c_edit" ><a href="javascript:;" ng-click="downloadFile(file[$index])">{{_file.file.substring(_file.file.indexOf("_")+1)}}</a></label>
                                      		<!-- <input type="text" name="file[$index]" name="file" class="form-control" ng-hide="fileInfoInput" ng-model="file[$index].file"  >
			                                <p class="form-control-static" ng-show="fileInfoShow"> {{_file.file}} </p> -->
			                          </td>
			                           <td>
                                      		<input type="text" name="remark[$index]" name="remark" class="form-control" ng-hide="fileInfoInput" ng-model="file[$index].remark"  >
			                                <p class="form-control-static" ng-show="fileInfoShow"> {{_file.remark}} </p>
			                          </td>
                                      <td><p class="form-control-static"> {{_file.uploader}} </p></td>
                                      <td><p class="form-control-static"> {{_file.uploadDate}} </p></td>
                                      
                                      <td ng-show="operation_f{{$index}}">
                                      	<a href="javascript:;"  class="btn red btn-sm" ng-hide="fileInfoInput" ng-click="deleteFile($index)">
                                    			<i class="fa fa-close"></i> 
                             				</a>
                                      </td>
                                  </tr>
                              </tbody>
                          </table>
                      </div>
	                </form>
                      <div class="form-actions right">
							<a  class="btn blue btn-sm"  ng-hide="fileInfoInput" ng-click="addFile()"   >
	                              <i class="fa fa-plus"></i> 增加
	                       	</a> 
                  		</div>
            </div>
          <!-- 附件 end-->
                    </div>
				</div>
				
        </div>
        <!-- END EXAMPLE TABLE PORTLET-->
    </div>
</div>
<jsp:include page="../delivery/selectDelivery.jsp"></jsp:include>
<!-- END MAIN CONTENT -->
<!-- BEGIN MAIN JS -->
 <script>
 var isNumberWithTwo = function(obj) {
	 var value=$(obj).val();
		if(/^[-\+]?\d+(\.\d+)?$/.test(value)==false||value==NaN||(Number(value)*100+"").indexOf(".")>-1){
			toastr.warning("只能包含小数点和数字,且只能有两位小数");
		//	$(obj).val('');
			//$(obj).focus();
			return;
			}
		$(obj).val(Number(value));
	}
</script> 
<!-- END MAIN JS -->