<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" %>
<!-- BEGIN PAGE HEADER-->
<h3 class="page-title"> 物料信息
    <small>新增</small>
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
        </li>
        <li>
            <a ui-sref="addMateriel">新增物料</a>
        </li>
    </ul>
</div>
<!-- END PAGE HEADER-->
    <div class="portlet light bordered">
          <div class="portlet-body form">
              <!-- BEGIN FORM-->
              <form id="form_sample_1" name="materielForm"  class="form-horizontal">
                  <div class="form-body">
                      <h3 class="form-section">基本信息</h3>
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
                                      <input type="text" ng-hide="materielInput" ng-click="selectParentMateriel()" data-target="#basicMaterielInfo" data-toggle="modal" ng-model="materiel.parentMateriel.materielName" class="form-control" >
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
                                                <label class="" >
                                                    <input type="checkbox" class="icheck"  ng-model="materiel.isBOM" > 是 </label>
                                            </div>
                                        </div>
                                        <p class="form-control-static" ng-show="materielShow"> {{materiel.isBOM}} </p>
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
                  <div class="form-actions">
                      <div class="row">
                          <div class="col-md-6">
                              <div class="row">
                                  <div class="col-md-offset-3 col-md-9">
                                      <button type="submit" ng-click="save(materielForm.$valid)" ng-hide="materielInput" class="btn green">保存</button>
                                      <button ng-click="cancel()" type="button" ng-hide="materielInput" class="btn default">取消</button>
                                      <button ng-click="edit()" type="button" ng-show="materielShow" class="btn green"><i class="fa fa-pencil"></i>编辑</button>
                                  </div>
                              </div>
                          </div>
                      </div>
                  </div>
                  
                  <div class="form-body">
                      <h3 class="form-section">包装信息</h3>
                  </div> 
                  <div class="row">
                      <div class="col-md-2">
                          <div class="form-group form-md-line-input" style="text-align: center;">
                              <label class="control-label">包装编号<span class="required" aria-required="true"> * </span></label>
                          </div>
                      </div>
                      <!--/span-->
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
                          <div class="form-group form-md-line-input">
                              <div class="col-md-12">
                              <input type="text" name="packageNum" class="form-control" ng-hide="materielPackageInput" ng-model="materiel.packageNum"  >
                                  <div class="form-control-focus"> </div>
                                  <span class="help-block">请输入包装编号</span>
                                  <p class="form-control-static" ng-show="materielPackageShow"> {{materiel.packageNum}} </p>
                              </div>
                          </div>
                      </div>
                      <!--/span-->
                      <div class="col-md-2">
                          <div class="form-group form-md-line-input">
                              <div class="col-md-12">
                              <input type="text" name="packageSpecifications" class="form-control" ng-hide="materielPackageInput" ng-model="materiel.packageSpecifications"  >
                                  <div class="form-control-focus"> </div>
                                  <span class="help-block">请输入包装规格</span>
                                  <p class="form-control-static" ng-show="materielPackageShow"> {{materiel.packageSpecifications}} </p>
                              </div>
                              
                          </div>
                      </div>
                      <div class="col-md-1">
                          <div class="form-group form-md-line-input">
                              <div class="col-md-12">
                              <input type="text" name="packageUnit" class="form-control" ng-hide="materielPackageInput" ng-model="materiel.packageUnit"  >
                                  <div class="form-control-focus"> </div>
                                  <span class="help-block">请输入包装单位</span>
                                  <p class="form-control-static" ng-show="materielPackageShow"> {{materiel.packageUnit}} </p>
                              </div>
                              
                          </div>
                      </div>
                      <div class="col-md-1">
                          <div class="form-group form-md-line-input">
                              <div class="col-md-12">
                              <input type="text" name="packagelength" class="form-control" ng-hide="materielPackageInput" ng-model="materiel.packagelength"  >
                                  <div class="form-control-focus"> </div>
                                  <span class="help-block">请输入长度</span>
                                  <p class="form-control-static" ng-show="materielPackageShow"> {{materiel.packagelength}} </p>
                              </div>
                              
                          </div>
                      </div>
                      <div class="col-md-1">
                          <div class="form-group form-md-line-input">
                              <div class="col-md-12">
                              <input type="text" name="packagewidth" class="form-control" ng-hide="materielPackageInput" ng-model="materiel.packagewidth"  >
                                  <div class="form-control-focus"> </div>
                                  <span class="help-block">请输入宽度</span>
                                  <p class="form-control-static" ng-show="materielPackageShow"> {{materiel.packagewidth}} </p>
                              </div>
                              
                          </div>
                      </div>
                      <div class="col-md-1">
                          <div class="form-group form-md-line-input">
                              <div class="col-md-12">
                              <input type="text" name="packageheight" class="form-control" ng-hide="materielPackageInput" ng-model="materiel.packageheight"  >
                                  <div class="form-control-focus"> </div>
                                  <span class="help-block">请输入高度</span>
                                  <p class="form-control-static" ng-show="materielPackageShow"> {{materiel.packageheight}} </p>
                              </div>
                              
                          </div>
                      </div>
                      <div class="col-md-2">
                          <div class="form-group form-md-line-input">
                              <div class="col-md-12">
                              <input type="text" name="packagevolume" class="form-control" ng-hide="materielPackageInput" ng-model="materiel.packagevolume"  >
                                  <div class="form-control-focus"> </div>
                                  <span class="help-block">请输入包装体积</span>
                                  <p class="form-control-static" ng-show="materielPackageShow"> {{materiel.packagevolume}} </p>
                              </div>
                              
                          </div>
                      </div>
                      <div class="col-md-2">
                          <div class="form-group form-md-line-input">
                              <div class="col-md-12">
                              <input type="text" name="packageUnitConversion" class="form-control" ng-hide="materielPackageInput" ng-model="materiel.packageUnitConversion"  >
                                  <div class="form-control-focus"> </div>
                                  <span class="help-block">请输入包装单位换算</span>
                                  <p class="form-control-static" ng-show="materielPackageShow"> {{materiel.packageUnitConversion}} </p>
                              </div>
                              
                          </div>
                      </div>
                  </div>
                  <div class="form-actions">
                      <div class="row">
                          <div class="col-md-6">
                              <div class="row">
                                  <div class="col-md-offset-3 col-md-9">
                                      <button type="submit" ng-click="savePackage(materielForm.$valid)" ng-hide="materielPackageInput" class="btn green">保存</button>
                                      <button ng-click="cancelPackage()" type="button" ng-hide="materielPackageInput" class="btn default">取消</button>
                                      <button ng-click="editPackage()" type="button" ng-show="materielPackageShow" class="btn green"><i class="fa fa-pencil"></i>编辑</button>
                                  </div>
                              </div>
                          </div>
                      </div>
                  </div>
                  
                  
              </form>
              
              

              <!-- END FORM-->
          </div>
      </div>

<jsp:include  page="selectMateriel.jsp"/>
