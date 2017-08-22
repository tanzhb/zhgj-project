<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- BEGIN PAGE HEADER-->

<h3 class="page-title"> 物料信息
</h3>
<div class="page-bar">
    <ul class="page-breadcrumb">
        <li>
            <i class="fa fa-home"></i>
            <a ui-sref="dashboard">首页</a>
            <i class="fa fa-angle-right"></i>
        </li>
        <li>
            <a ui-sref="materiel">物料信息</a>
            <i class="fa fa-angle-right"></i>
        </li>
        <li>
            <a>{{opration}}</a>
        </li>
    </ul>
    <div class="page-toolbar">
          <div class="btn-group pull-right">
              <button type="button" class="btn btn-fit-height grey-salt dropdown-toggle" onclick="printdiv('materielPrint')"> 
              	<i class="fa fa-print"></i>
                  		打印
              </button>
              
          </div>
      </div>
</div>
<div class="row" id="materielPrint">
    <div class="col-md-12">
        <!-- BEGIN EXAMPLE TABLE PORTLET-->
        <div class="portlet bordered">
            <div class="portlet-body">
				<div class="portlet light ">
                        <div class="portlet-title">
                            <div class="caption">基本信息</div>
                            <div class="tools" id="noprintdiv">
                            	<button type="submit" ng-click="save()" ng-hide="materielInput" class="btn blue  btn-outline  btn-sm">
                               		<i class="fa fa-save"></i> 保存 </button>
                               <button ng-click="cancel()" type="button" ng-hide="materielInput" class="btn red  btn-outline  btn-sm">
                               		<i class="fa fa-undo"></i> 取消 </button>
                               <button ng-click="edit()" type="button" ng-show="materielShow" class="btn blue  btn-outline  btn-sm">
                               		<i class="fa fa-edit"></i> 编辑 </button>
                                 <!-- <a href="javascript:;" class="collapse"> </a> -->
                             </div>
                        </div>
          <div class="portlet-body form">
              <!-- BEGIN FORM-->
              <form action="#" id="form_sample_1"  class="form-horizontal">
                  <div class="form-body">
                      <div class="alert alert-danger display-hide">
                          <button class="close" data-close="alert"></button> 请先输入正确数据！ </div>
                      <div class="row">
                          <div class="col-md-6">
                              <div class="form-group form-md-line-input">
                                  <label class="control-label col-md-3">物料编码<span class="required" aria-required="true"> * </span></label>
                                  <div class="col-md-9">
                                  <input type="text" name="materielNum" class="form-control" ng-hide="materielInput" ng-model="materiel.materielNum"  >
                                      <div class="form-control-focus"> </div>
                                      <span class="help-block">请输入物料编码</span>
                                      <p class="form-control-static" ng-show="materielShow"> {{materiel.materielNum}} </p>
                                  </div>
                                  
                              </div>
                          </div>
                          <!--/span-->
                          <div class="col-md-6">
                              <div class="form-group form-md-line-input">
                                  <label class="control-label col-md-3">助记码</label>
                                  <div class="col-md-9">
                                        <input class="form-control" type="text" ng-hide="materielInput" ng-model="materiel.mnemonicCode"> 
                                        <div class="form-control-focus"> </div>
                              			<span class="help-block">请输入助记码</span>
                                      <p class="form-control-static" ng-show="materielShow"> {{materiel.mnemonicCode}} </p>
                                  </div>
                              </div>
                          </div>
                          <!--/span-->
                      </div>
                      <!--/row-->
                      <div class="row">
                          <div class="col-md-6">
                              <div class="form-group form-md-line-input">
                                  <label class="control-label col-md-3">物料种类<span class="required" aria-required="true"> * </span></label>
                                  <div class="col-md-9">
                                      <input type="text" name="type" ng-hide="materielInput" ng-model="materiel.type" class="form-control" >
                                      <div class="form-control-focus"> </div>
                                      <span class="help-block">请选择物料种类</span>
                                      <p class="form-control-static" ng-show="materielShow"> {{materiel.type}} </p>
                                  </div>

                              </div>
                          </div>
                          <!--/span-->
                          <div class="col-md-6">
                              <div class="form-group form-md-line-input">
                                  <label class="control-label col-md-3">物料名称<span class="required" aria-required="true"> * </span></label>
                                  <div class="col-md-9">
                                      <input type="text"  name="materielName" class="form-control" ng-hide="materielInput" ng-model="materiel.materielName" >
                                  		<div class="form-control-focus"> </div>
                                      <span class="help-block">请输入物料名称</span>
                                      <p class="form-control-static" ng-show="materielShow"> {{materiel.materielName}} </p>
                                  </div>

                              </div>
                          </div>
                          <!--/span-->
                      </div>
                      <!--/row-->
                      <div class="row">
                          <div class="col-md-6">
                              <div class="form-group form-md-line-input">
                                  <label class="control-label col-md-3">物料类别<span class="required" aria-required="true"> * </span></label>
                                  <div class="col-md-9">
                                      <input type="text" name="category" ng-hide="materielInput" ng-model="materiel.category" class="form-control" >
                                  		<div class="form-control-focus"> </div>
                                      <span class="help-block">请输入物料类别</span>
                                      <p class="form-control-static" ng-show="materielShow"> {{materiel.category}} </p>
                                  </div>

                              </div>
                          </div>
                          <!--/span-->
                          <div class="col-md-6">
                              <div class="form-group form-md-line-input">
                                  <label class="control-label col-md-3">单位</label>
                                  <div class="col-md-9">
                                      <input type="text" class="form-control" ng-hide="materielInput" ng-model="materiel.unit" >
                                      <div class="form-control-focus"> </div>
                                      <span class="help-block">请输入单位</span>
                                      <p class="form-control-static" ng-show="materielShow"> {{materiel.unit}} </p>
                                  </div>

                              </div>
                          </div>
                          <!--/span-->
                      </div>
                      <!--/row-->
                      <div class="row">
                          <div class="col-md-6">
                              <div class="form-group form-md-line-input">
                                  <label class="control-label col-md-3">规格型号<span class="required" aria-required="true"> * </span></label>
                                  <div class="col-md-9">
                                      <input type="text" name="specifications" ng-hide="materielInput" ng-model="materiel.specifications" class="form-control" >
                                  		<div class="form-control-focus"> </div>
                                      <span class="help-block">请输入规格型号</span>
                                      <p class="form-control-static" ng-show="materielShow"> {{materiel.specifications}} </p>
                                  </div>

                              </div>
                          </div>
                          <!--/span-->
                          <div class="col-md-6">
                              <div class="form-group form-md-line-input">
                                  <label class="control-label col-md-3">产地</label>
                                  <div class="col-md-9">
                                      <input type="text" class="form-control" ng-hide="materielInput" ng-model="materiel.productionPlace" >
                                      <div class="form-control-focus"> </div>
                                      <span class="help-block">请输入产地</span>
                                       <p class="form-control-static" ng-show="materielShow"> {{materiel.productionPlace}} </p>
                                  </div>

                              </div>
                          </div>
                          <!--/span-->
                      </div>
                      <!--/row-->
                      <div class="row">
                          <div class="col-md-6">
                              <div class="form-group form-md-line-input">
                                  <label class="control-label col-md-3">库存单位<span class="required" aria-required="true"> * </span></label>
                                  <div class="col-md-9">
                                      <input type="text" name="stockUnit" ng-hide="materielInput" ng-model="materiel.stockUnit" class="form-control" >
                                  <div class="form-control-focus"> </div>
                                      <span class="help-block">请输入库存单位</span>
                                      <p class="form-control-static" ng-show="materielShow"> {{materiel.stockUnit}} </p>
								</div>
                              </div>
                          </div>
                          <!--/span-->
                          <div class="col-md-6">
                              <div class="form-group form-md-line-input">
                                  <label class="control-label col-md-3">上级产品</label>
                                  <div class="col-md-9">
                                      <input type="text" ng-hide="materielInput" readonly ng-click="selectMateriel('parent')" data-target="#basicMaterielInfo" data-toggle="modal" ng-model="materiel.parentMateriel.materielName" class="form-control" >
                                      <div class="form-control-focus"> </div>
                                       <span class="help-block">请选择上级产品</span>
                                      <p class="form-control-static" ng-show="materielShow"> {{materiel.parentMateriel.materielName}} </p>
                                  </div>
                              </div>
                          </div>
                          <!--/span-->
                      </div>
                      <!--/row-->
                       <div class="row">
                          <div class="col-md-6">
                              <div class="form-group form-md-line-input">
                                  <label class="control-label col-md-3">品牌</label>
                                  <div class="col-md-9">
                                      <input type="text" ng-hide="materielInput" ng-model="materiel.brand" class="form-control" >
                                      <div class="form-control-focus"> </div>
                                       <span class="help-block">请输入品牌</span>
                                      <p class="form-control-static" ng-show="materielShow"> {{materiel.brand}} </p>
                                  </div>

                              </div>
                          </div>
                          <!--/span-->
                          <div class="col-md-6">
                              <div class="form-group form-md-line-input">
                                  <label class="control-label col-md-3">原产国</label>
                                  <div class="col-md-9">
                                      <input type="text" ng-hide="materielInput" ng-model="materiel.originCountry" class="form-control" >
                                      <div class="form-control-focus"> </div>
                                      <span class="help-block">请输入原产国</span>
                                      <p class="form-control-static" ng-show="materielShow"> {{materiel.originCountry}} </p>
                                  </div>
                              </div>
                          </div>
                          <!--/span-->
                      </div>
                      <!--/row-->
                       <div class="row">
                          <div class="col-md-6">
                              <div class="form-group form-md-line-input">
                                  <label class="control-label col-md-3">长度</label>
                                  <div class="col-md-9">
                                      <input type="text" ng-hide="materielInput" ng-model="materiel.length" class="form-control" >
                                      <div class="form-control-focus"> </div>
                                      <span class="help-block">请输入长度</span>
                                      <p class="form-control-static" ng-show="materielShow"> {{materiel.length}} </p>
                                  </div>

                              </div>
                          </div>
                          <!--/span-->
                          <div class="col-md-6">
                              <div class="form-group form-md-line-input">
                                  <label class="control-label col-md-3">币种</label>
                                  <div class="col-md-9">
                                      <input type="text" ng-hide="materielInput" ng-model="materiel.currency" class="form-control" >
                                      <div class="form-control-focus"> </div>
                                      <span class="help-block">请输入币种</span>
                                      <p class="form-control-static" ng-show="materielShow"> {{materiel.currency}} </p>
                                  </div>
                              </div>
                          </div>
                          <!--/span-->
                      </div>
                      <!--/row-->
                      <div class="row">
                          <div class="col-md-6">
                              <div class="form-group form-md-line-input">
                                  <label class="control-label col-md-3">宽度</label>
                                  <div class="col-md-9">
                                      <input type="text" ng-hide="materielInput" ng-model="materiel.width" class="form-control" >
                                      <div class="form-control-focus"> </div>
                                      <span class="help-block">请输入宽度</span>
                                      <p class="form-control-static" ng-show="materielShow"> {{materiel.width}} </p>
                                  </div>

                              </div>
                          </div>
                          <!--/span-->
                          <div class="col-md-6">
                              <div class="form-group form-md-line-input">
                                  <label class="control-label col-md-3">单价</label>
                                  <div class="col-md-9">
                                      <input type="text" ng-hide="materielInput" ng-model="materiel.unitPrice" class="form-control" >
                                      <div class="form-control-focus"> </div>
                                      <span class="help-block">请输入单价</span>
                                      <p class="form-control-static" ng-show="materielShow"> {{materiel.unitPrice}} </p>
                                  </div>
                              </div>
                          </div>
                          <!--/span-->
                      </div>
                      <!--/row-->
                      <div class="row">
                          <div class="col-md-6">
                              <div class="form-group form-md-line-input">
                                  <label class="control-label col-md-3">高度</label>
                                  <div class="col-md-9">
                                      <input type="text" ng-hide="materielInput" ng-model="materiel.height" class="form-control" >
                                      <div class="form-control-focus"> </div>
                                      <span class="help-block">请输入高度</span>
                                      <p class="form-control-static" ng-show="materielShow"> {{materiel.height}} </p>
                                  </div>

                              </div>
                          </div>
                          <!--/span-->
                          <div class="col-md-6">
                              <div class="form-group form-md-line-input">
                                  <label class="control-label col-md-3">备案项号</label>
                                  <div class="col-md-9">
                                      <input type="text" ng-hide="materielInput" ng-model="materiel.filingItemNo" class="form-control" >
                                      <div class="form-control-focus"> </div>
                                      <span class="help-block">请输入备案项号</span>
                                      <p class="form-control-static" ng-show="materielShow"> {{materiel.filingItemNo}} </p>
                                  </div>
                              </div>
                          </div>
                          <!--/span-->
                      </div>
                      <!--/row-->
                      <div class="row">
                          <div class="col-md-6">
                              <div class="form-group form-md-line-input">
                                  <label class="control-label col-md-3">单件体积</label>
                                  <div class="col-md-9">
                                      <input type="text" ng-hide="materielInput" ng-model="materiel.volume" class="form-control" >
                                      <div class="form-control-focus"> </div>
                                      <span class="help-block">请输入单件体积</span>
                                      <p class="form-control-static" ng-show="materielShow"> {{materiel.volume}} </p>
                                  </div>

                              </div>
                          </div>
                          <!--/span-->
                          <div class="col-md-6">
                              <div class="form-group form-md-line-input">
                                  <label class="control-label col-md-3">海关监管条件</label>
                                  <div class="col-md-9">
                                      <input type="text" ng-hide="materielInput" ng-model="materiel.customsSupervisionConditions" class="form-control" >
                                      <div class="form-control-focus"> </div>
                                      <span class="help-block">请输入海关监管条件</span>
                                      <p class="form-control-static" ng-show="materielShow"> {{materiel.customsSupervisionConditions}} </p>
                                  </div>
                              </div>
                          </div>
                          <!--/span-->
                      </div>
                      <!--/row-->
                      <div class="row">
                          <div class="col-md-6">
                              <div class="form-group form-md-line-input">
                                  <label class="control-label col-md-3">单件重量</label>
                                  <div class="col-md-9">
                                      <input type="text" ng-hide="materielInput" ng-model="materiel.weight" class="form-control" >
                                      <div class="form-control-focus"> </div>
                                      <span class="help-block">请输入单件重量</span>
                                      <p class="form-control-static" ng-show="materielShow"> {{materiel.weight}} </p>
                                  </div>

                              </div>
                          </div>
                          <!--/span-->
                          <div class="col-md-6">
                              <div class="form-group form-md-line-input">
                                  <label class="control-label col-md-3">保质期</label>
                                  <div class="col-md-9">
                                      <input type="text" ng-hide="materielInput" ng-model="materiel.qualityDate" class="form-control" >
                                      <div class="form-control-focus"> </div>
                                      <span class="help-block">请输入保质期</span>
                                      <p class="form-control-static" ng-show="materielShow"> {{materiel.qualityDate}} </p>
                                  </div>
                              </div>
                          </div>
                          <!--/span-->
                      </div>
                      <!--/row-->
                      <div class="row">
                          <div class="col-md-6">
                              <div class="form-group form-md-line-input">
                                  <label class="control-label col-md-3">托盘规格</label>
                                  <div class="col-md-9">
                                      <input type="text" ng-hide="materielInput" ng-model="materiel.palletSpecification" class="form-control" >
                                      <div class="form-control-focus"> </div>
                                      <span class="help-block">请输入托盘规格</span>
                                      <p class="form-control-static" ng-show="materielShow"> {{materiel.palletSpecification}} </p>
                                  </div>

                              </div>
                          </div>
                          <!--/span-->
                          <div class="col-md-6">
                              <div class="form-group form-md-line-input">
                                  <label class="control-label col-md-3">生产日期</label>
                                  <div class="col-md-9">
                                      <input class="form-control form-control-inline input-medium date-picker" 
                                      data-date-format="yyyy-mm-dd" data-date-viewmode="years" size="16" ng-hide="materielInput" ng-model="materiel.manufactureDate" type="text" value="" />
                                      <div class="form-control-focus"> </div>
                                      <span class="help-block">请选择生产日期</span>
                                      <p class="form-control-static" ng-show="materielShow"> {{materiel.manufactureDate}} </p>
                                  </div>
                              </div>
                          </div>
                          <!--/span-->
                      </div>
                      <!--/row-->
                      <div class="row">
                          <div class="col-md-6">
                              <div class="form-group form-md-line-input">
                                  <label class="control-label col-md-3">每托数量</label>
                                  <div class="col-md-9">
                                      <input type="text" ng-hide="materielInput" ng-model="materiel.palletWeight" class="form-control" >
                                      <div class="form-control-focus"> </div>
                                       <span class="help-block">请输入每托数量</span>
                                      <p class="form-control-static" ng-show="materielShow"> {{materiel.palletWeight}} </p>
                                  </div>

                              </div>
                          </div>
                          <!--/span-->
                          <div class="col-md-6">
                              <div class="form-group form-md-line-input">
                                  <label class="control-label col-md-3">备注</label>
                                  <div class="col-md-9">
                                      <input type="text" ng-hide="materielInput" ng-model="materiel.remark" class="form-control" >
                                      <div class="form-control-focus"> </div>
                                      <span class="help-block">请输入备注</span>
                                      <p class="form-control-static" ng-show="materielShow"> {{materiel.remark}} </p>
                                  </div>
                              </div>
                          </div>
                          <!--/span-->
                      </div>
                      <!--/row-->
                      <div class="row">
                          <div class="col-md-6">
                              <div class="form-group form-md-line-input">
                                  <label class="control-label col-md-3">是否BOM物料</label>
                                  <div class="col-md-9">
                                  		<div class="input-group" ng-hide="materielInput">
                                            <div class="icheck-inline">
                                                <input type="checkbox" class="icheck" id="isBOMcheck" > 是  </label>
                                            </div>
                                        </div>
                                        <p class="form-control-static" ng-show="materielShow" > {{materiel.isBOM=="1"?'是':'否'}} </p>
                                  </div>
                              </div>
                          </div>
                          <!--/span-->
                          <div class="col-md-6">
                              <div class="form-group form-md-line-input">
                                  <label class="control-label col-md-3">版本</label>
                                  <div class="col-md-9">
                                  	<p class="form-control-static"> {{materiel.versionNO}} </p>
                                      
                                  </div>
                              </div>
                          </div>
                          <!--/span-->
                      </div>
                      <!--/row-->
                  </div>
				</form>
             </div>   
                  <div class="portlet-title">
                       <div class="caption">包装信息</div>
                       <div class="tools">
                       	 	<button type="submit" ng-click="savePackage()" ng-hide="materielPackageInput" class="btn blue  btn-outline  btn-sm">
                          		<i class="fa fa-save"></i> 保存 </button>
                          <button ng-click="cancelPackage()" type="button" ng-hide="materielPackageInput" class="btn red  btn-outline  btn-sm">
                          		<i class="fa fa-undo"></i> 取消 </button>
                          <button ng-click="editPackage()" type="button" ng-show="materielPackageShow" class="btn blue  btn-outline  btn-sm">
                          		<i class="fa fa-edit"></i> 编辑 </button>
                        </div>
                   </div>
           <div class="portlet-body form">

			     <form id="form_sample_2"   class="form-horizontal">
			         <div class="form-body">
			             <div class="alert alert-danger display-hide">
			                 <button class="close" data-close="alert"></button> 请先输入正确数据！ </div>
			                  <div class="row">
			                      <div class="col-md-2">
			                          <div class="form-group form-md-line-input" style="text-align: center;">
			                              <label class="control-label">包装编号<span class="required" aria-required="true"> * </span></label>
			                          </div>
			                      </div>
			                      <div class="col-md-2">
			                          <div class="form-group form-md-line-input" style="text-align: center;">
			                              <label class="control-label">包装规格<span class="required" aria-required="true"> * </span></label>
			                          </div>
			                      </div>
			                      <div class="col-md-1">
			                          <div class="form-group form-md-line-input" style="text-align: center;">
			                              <label class="control-label">包装单位<span class="required" aria-required="true"> * </span></label>
			                          </div>
			                      </div>
			                      <div class="col-md-1">
			                          <div class="form-group form-md-line-input" style="text-align: center;">
			                              <label class="control-label">长</label>
			                          </div>
			                      </div>
			                      <div class="col-md-1">
			                          <div class="form-group form-md-line-input" style="text-align: center;">
			                              <label class="control-label">宽</label>
			                          </div>
			                      </div>
			                      <div class="col-md-1">
			                          <div class="form-group form-md-line-input" style="text-align: center;">
			                              <label class="control-label">高</label>
			                          </div>
			                      </div>
			                      <div class="col-md-2">
			                          <div class="form-group form-md-line-input" style="text-align: center;">
			                              <label class="control-label">包装体积</label>
			                          </div>
			                      </div>
			                      <div class="col-md-2">
			                          <div class="form-group form-md-line-input" style="text-align: center;">
			                              <label class="control-label">包装单位换算<span class="required" aria-required="true"> * </span></label>
			                          </div>
			                      </div>
			                  </div>
			                  <div class="row">
			                      <div class="col-md-2">
			                          <div class="form-group form-md-line-input" style="text-align: center;">
			                              <div class="col-md-12">
			                              <input type="text" name="packageNum" class="form-control" ng-hide="materielPackageInput" ng-model="materiel.packageNum"  >
			                                  <div class="form-control-focus"> </div>
			                                  <span class="help-block">请输入包装编号</span>
			                                  <p class="form-control-static" ng-show="materielPackageShow"> {{materiel.packageNum}} </p>
			                              </div>
			                          </div>
			                      </div>
			                      <div class="col-md-2">
			                          <div class="form-group form-md-line-input" style="text-align: center;">
			                              <div class="col-md-12">
			                              <input type="text" name="packageSpecifications" class="form-control" ng-hide="materielPackageInput" ng-model="materiel.packageSpecifications"  >
			                                  <div class="form-control-focus"> </div>
			                                  <span class="help-block">请输入包装规格</span>
			                                  <p class="form-control-static" ng-show="materielPackageShow"> {{materiel.packageSpecifications}} </p>
			                              </div>
			                              
			                          </div>
			                      </div>
			                      <div class="col-md-1">
			                          <div class="form-group form-md-line-input" style="text-align: center;">
			                              <div class="col-md-12">
			                              <input type="text" name="packageUnit" class="form-control" ng-hide="materielPackageInput" ng-model="materiel.packageUnit"  >
			                                  <div class="form-control-focus"> </div>
			                                  <span class="help-block">请输入包装单位</span>
			                                  <p class="form-control-static" ng-show="materielPackageShow"> {{materiel.packageUnit}} </p>
			                              </div>
			                              
			                          </div>
			                      </div>
			                      <div class="col-md-1">
			                          <div class="form-group form-md-line-input" style="text-align: center;">
			                              <div class="col-md-12">
			                              <input type="text" name="packagelength" class="form-control" ng-hide="materielPackageInput" ng-model="materiel.packagelength"  >
			                                  <div class="form-control-focus"> </div>
			                                  <span class="help-block">请输入长度</span>
			                                  <p class="form-control-static" ng-show="materielPackageShow"> {{materiel.packagelength}} </p>
			                              </div>
			                              
			                          </div>
			                      </div>
			                      <div class="col-md-1">
			                          <div class="form-group form-md-line-input" style="text-align: center;">
			                              <div class="col-md-12">
			                              <input type="text" name="packagewidth" class="form-control" ng-hide="materielPackageInput" ng-model="materiel.packagewidth"  >
			                                  <div class="form-control-focus"> </div>
			                                  <span class="help-block">请输入宽度</span>
			                                  <p class="form-control-static" ng-show="materielPackageShow"> {{materiel.packagewidth}} </p>
			                              </div>
			                              
			                          </div>
			                      </div>
			                      <div class="col-md-1">
			                          <div class="form-group form-md-line-input" style="text-align: center;">
			                              <div class="col-md-12">
			                              <input type="text" name="packageheight" class="form-control" ng-hide="materielPackageInput" ng-model="materiel.packageheight"  >
			                                  <div class="form-control-focus"> </div>
			                                  <span class="help-block">请输入高度</span>
			                                  <p class="form-control-static" ng-show="materielPackageShow"> {{materiel.packageheight}} </p>
			                              </div>
			                              
			                          </div>
			                      </div>
			                      <div class="col-md-2">
			                          <div class="form-group form-md-line-input" style="text-align: center;">
			                              <div class="col-md-12">
			                              <input type="text" name="packagevolume" class="form-control" ng-hide="materielPackageInput" ng-model="materiel.packagevolume"  >
			                                  <div class="form-control-focus"> </div>
			                                  <span class="help-block">请输入包装体积</span>
			                                  <p class="form-control-static" ng-show="materielPackageShow"> {{materiel.packagevolume}} </p>
			                              </div>
			                              
			                          </div>
			                      </div>
			                      <div class="col-md-2">
			                          <div class="form-group form-md-line-input" style="text-align: center;">
			                              <div class="col-md-12">
			                              <input type="text" name="packageUnitConversion" class="form-control" ng-hide="materielPackageInput" ng-model="materiel.packageUnitConversion"  >
			                                  <div class="form-control-focus"> </div>
			                                  <span class="help-block">请输入包装单位换算</span>
			                                  <p class="form-control-static" ng-show="materielPackageShow"> {{materiel.packageUnitConversion}} </p>
			                              </div>
			                              
			                          </div>
			                      </div>
			                  </div>
			             </div>
                  </form>

          </div>
          
          <!-- 附件 start-->
          <div class="portlet-title">
               <div class="caption">附件</div>
               <div class="tools">
               	 	<button type="submit" ng-click="saveFile()" ng-hide="fileInfoInput" class="btn blue  btn-outline  btn-sm">
                  		<i class="fa fa-save"></i> 保存 </button>
                  <button ng-click="cancelFile()" type="button" ng-hide="fileInfoInput" class="btn red  btn-outline  btn-sm">
                  		<i class="fa fa-undo"></i> 取消 </button>
                  <button ng-click="editFile()" type="button" ng-show="fileInfoShow" class="btn blue  btn-outline  btn-sm">
                  		<i class="fa fa-edit"></i> 编辑 </button>
                </div>
            </div>
           <div class="portlet-body form">
			     <form id="form_sample_4"   class="form-horizontal">
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
                                             <span class="btn blue btn-outline btn-file">
                                                 <span class="fileinput-new">上传附件</span>
                                                 <span class="fileinput-exists">更改</span>
                                                 <input type="file" name="..." nv-file-select uploader="uploader" onchange="angular.element(this).scope().up(this.files[0])" ng-model="file[$index].file" ng-click="uploadFile($index)" > </span>
                                             <span class="fileinput-filename">{{_file.file.substring(_file.file.indexOf("_")+1)}}</span> &nbsp;
                                             <a href="javascript:;" class="close fileinput-exists" ng-click="removefile($index)" data-dismiss="fileinput"> </a>
                                         </div>
                                         <div ng-hide="fileInfoInput"   ng-if="file[$index].file!=null&&file[$index].file!=''"  class="fileinput fileinput-exists" data-provides="fileinput">
                                             <span class="btn blue btn-outline btn-file">
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
                      <div class="form-actions right">
							<a  class="btn blue btn-sm"  ng-hide="fileInfoInput" ng-click="addFile()"   >
	                              <i class="fa fa-plus"></i> 增加
	                       	</a> 
                  		</div>
                  </form>
          </div>
          <!-- 附件 end-->
          <!-- BOM start-->
          <div class="portlet-title" ng-show="BOMShow">
               <div class="caption">BOM</div>
               <div class="tools">
               	 	<button type="submit" ng-click="saveBOM()" ng-hide="BOMInfoInput" class="btn blue  btn-outline  btn-sm">
                  		<i class="fa fa-save"></i> 保存 </button>
                  <button ng-click="cancelBOM()" type="button" ng-hide="BOMInfoInput" class="btn red  btn-outline  btn-sm">
                  		<i class="fa fa-undo"></i> 取消 </button>
                  <button ng-click="editBOM()" type="button" ng-show="BOMInfoShow" class="btn blue  btn-outline  btn-sm">
                  		<i class="fa fa-edit"></i> 编辑 </button>
                </div>
            </div>
           <div class="portlet-body form"  ng-show="BOMShow">
			     <form id="form_sample_3"   class="form-horizontal">
			         <div class="table-scrollable">
                          <table class="table table-bordered table-hover">
                              <thead>
                                  <tr>
                                      <th style="width:200px;">物料编码</th>
                                      <th>物料名称</th>
                                      <th>规格型号</th>
                                      <th>单位</th>
                                      <th>品牌</th>
                                      <th style="width:100px;">单套用量</th>
                                      <th style="width:100px;"></th>
                                  </tr>
                              </thead>
                              <tbody>
                                  <tr ng-repeat="_BOM in BOM track by $index" ng-mouseover="showOperation('BOM',$index)" ng-mouseleave="hideOperation('BOM',$index)">
                                      <td>
                                      		<input type="text" id="BOMMaterielNum[$index]" name="BOMMaterielNum" readonly data-target="#basicMaterielInfo" data-toggle="modal" ng-click="selectMateriel($index)" class="form-control" ng-hide="BOMInfoInput" ng-model="BOM[$index].materiel.materielNum"  >
			                                <!-- <div class="form-control-focus"> </div>
			                                <span class="help-block">请选择物料</span> -->
			                                <p class="form-control-static" ng-show="BOMInfoShow"> {{_BOM.materiel.materielNum}} </p>
			                          </td>
			                          <td>
			                                <p class="form-control-static"> {{_BOM.materiel.materielName}} </p>
			                          </td>
                                      <td><p class="form-control-static"> {{_BOM.materiel.specifications}} </p></td>
                                      <td><p class="form-control-static"> {{_BOM.materiel.unit}} </p></td>
                                      <td><p class="form-control-static"> {{_BOM.materiel.brand}} </p></td>
                                      <td>
                                      		<input type="text" name="singleDose[$index]" name="singleDose" class="form-control" ng-hide="BOMInfoInput" ng-model="BOM[$index].singleDose"  >
			                                <!-- <div class="form-control-focus"> </div>
			                                <span class="help-block">请输入用量</span> -->
			                                <p class="form-control-static" ng-show="BOMInfoShow"> {{_BOM.singleDose}} </p>
			                          </td>
                                      <td ng-show="operation_b{{$index}}">
                                      	<a href="javascript:;"  class="btn red btn-sm" ng-hide="BOMInfoInput" ng-click="deleteBOM($index)">
                                    			<i class="fa fa-close"></i> 
                             				</a>
                                      </td>
                                  </tr>
                              </tbody>
                          </table>
                      </div>
                      <div class="form-actions right">
							<a  class="btn blue btn-sm" ng-hide="BOMInfoInput" ng-click="addBOM()"   >
	                              <i class="fa fa-plus"></i> 增加
	                       	</a> 
                  		</div>
                  </form>
          </div>
          <!-- BOM end-->
           <!-- 供应商 start-->
          <div class="portlet-title">
               <div class="caption">供应商</div>
               <div class="tools">
               	 	<button type="submit" ng-click="saveSupplyMateriel()" ng-hide="supplyMaterielInfoInput" class="btn blue  btn-outline  btn-sm">
                  		<i class="fa fa-save"></i> 保存 </button>
                  <button ng-click="cancelSupplyMateriel()" type="button" ng-hide="supplyMaterielInfoInput" class="btn red  btn-outline  btn-sm">
                  		<i class="fa fa-undo"></i> 取消 </button>
                  <button ng-click="editSupplyMateriel()" type="button" ng-show="supplyMaterielInfoShow" class="btn blue  btn-outline  btn-sm">
                  		<i class="fa fa-edit"></i> 编辑 </button>
                </div>
            </div>
           <div class="portlet-body form">
			     <form id="form_sample_5"   class="form-horizontal">
			         <div class="table-scrollable">
                          <table class="table table-bordered table-hover">
                              <thead>
                                  <tr>
<!--                                       <th>供应商</th> -->
                                      <th>供应商名称</th>
                                      <th>供应商物料编号</th>
                                      <th style="width:100px;"></th>
                                  </tr>
                              </thead>
                              <tbody>
                                  <tr ng-repeat="_supplyMateriel in supplyMateriel track by $index" ng-mouseover="showOperation('supplyMateriel',$index)" ng-mouseleave="hideOperation('supplyMateriel',$index)" >
			                          <td>
		                                 	<div ng-hide="supplyMaterielInfoInput">
			                                 	<select class="form-control" id="supplyComId[$index]" name="supplyComId" class="bs-select form-control diySelectCss" data-live-search="true" data-size="8"  ng-model="supplyMateriel[$index].supplyComId"  >
	                                              	<option ng-repeat="_supplier in suppliers" value="{{_supplier.comId}}" repeat-done="repeatDone()">{{_supplier.comName}}</option>
	                                             </select>
                                             </div>
			                                <p class="form-control-static" ng-show="supplyMaterielInfoShow"> {{_supplyMateriel.supply.comName}} </p>
			                          </td>
<!-- 			                          <td>
		                                 	<p class="form-control-static" ng-show="supplyMaterielInfoShow"> {{_supplyMateriel.supply.comName}} </p>
			                          </td> -->
			                           <td>
                                      		<input type="text" id="supplyMaterielNum[$index]" name="supplyMaterielNum" class="form-control" ng-hide="supplyMaterielInfoInput" ng-model="supplyMateriel[$index].supplyMaterielNum"  >
			                                <p class="form-control-static" ng-show="supplyMaterielInfoShow"> {{_supplyMateriel.supplyMaterielNum}} </p>
			                          </td>
                                      
                                      <td ng-show="operation_s{{$index}}">
                                      	<a href="javascript:;"  class="btn red btn-sm" ng-hide="supplyMaterielInfoInput" ng-click="deleteSupplyMateriel($index)">
                                    			<i class="fa fa-close"></i> 
                             				</a>
                                      </td>
                                  </tr>
                              </tbody>
                          </table>
                      </div>
                      <div class="form-actions right">
							<a  class="btn blue btn-sm"  ng-hide="supplyMaterielInfoInput" ng-click="addSupplyMateriel()"   >
	                              <i class="fa fa-plus"></i> 增加
	                       	</a> 
                  		</div>
                  </form>
          </div>
          <!-- 供应商 end-->
          </div>
      </div>
</div>
</div>
</div> 

<jsp:include  page="selectMateriel.jsp"/>
