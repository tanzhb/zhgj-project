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
              <form id="form_sample_2" name="materielForm"  class="form-horizontal" >
                  <div class="form-body">
                      <h3 class="form-section">基本信息</h3>
                      <div class="row">
                          <div class="col-md-6">
                              <div class="form-group">
                                  <label class="control-label col-md-3">物料编码<span class="required" aria-required="true"> * </span></label>
                                  <div class="col-md-9">
                                  <div class="input-icon right">
                                                <i class="fa"></i>
                                      <input type="text" name="name" data-required class="form-control"  ng-model="materiel.materielNum"  >
                                  </div>
                                  </div>
                                  
                              </div>
                          </div>
                          <!--/span-->
                          <div class="col-md-6">
                              <div class="form-group">
                                  <label class="control-label col-md-3">助记码</label>
                                  <div class="col-md-9">
                                  		<div class="input-icon right">
                                                <i class="fa"></i>
                                                <input class="form-control" type="text" ng-model="materiel.mnemonicCode"> 
                                        </div>
                                  </div>
                              </div>
                          </div>
                          <!--/span-->
                      </div>
                      <!--/row-->
                      <div class="row">
                          <div class="col-md-6">
                              <div class="form-group">
                                  <label class="control-label col-md-3">物料种类<span class="required" aria-required="true"> * </span></label>
                                  <div class="col-md-9">
                                      <div class="input-icon right">
                                                <i class="fa"></i>
                                      <input type="text" name="email" ng-model="materiel.type" class="form-control" >
                                      </div>
                                  </div>

                              </div>
                          </div>
                          <!--/span-->
                          <div class="col-md-6">
                              <div class="form-group">
                                  <label class="control-label col-md-3">物料名称<span class="required" aria-required="true"> * </span></label>
                                  <div class="col-md-9">
                                      <input type="text"  name="url"  class="form-control" ng-model="materiel.materielName" >
                                  </div>

                              </div>
                          </div>
                          <!--/span-->
                      </div>
                      <!--/row-->
                      <div class="row">
                          <div class="col-md-6">
                              <div class="form-group">
                                  <label class="control-label col-md-3">物料类别<span class="required" aria-required="true"> * </span></label>
                                  <div class="col-md-9">
                                      <input type="text" ng-model="materiel.category" class="form-control" >
                                  </div>

                              </div>
                          </div>
                          <!--/span-->
                          <div class="col-md-6">
                              <div class="form-group">
                                  <label class="control-label col-md-3">单位</label>
                                  <div class="col-md-9">
                                      <input type="text" class="form-control" ng-model="materiel.unit" >
                                  </div>

                              </div>
                          </div>
                          <!--/span-->
                      </div>
                      <!--/row-->
                      <div class="row">
                          <div class="col-md-6">
                              <div class="form-group">
                                  <label class="control-label col-md-3">规格型号<span class="required" aria-required="true"> * </span></label>
                                  <div class="col-md-9">
                                      <input type="text" ng-model="materiel.specifications" class="form-control" >
                                  </div>

                              </div>
                          </div>
                          <!--/span-->
                          <div class="col-md-6">
                              <div class="form-group">
                                  <label class="control-label col-md-3">产地</label>
                                  <div class="col-md-9">
                                      <input type="text" class="form-control" ng-model="materiel.productionPlace" >
                                  </div>

                              </div>
                          </div>
                          <!--/span-->
                      </div>
                      <!--/row-->
                      <div class="row">
                          <div class="col-md-6">
                              <div class="form-group">
                                  <label class="control-label col-md-3">库存单位<span class="required" aria-required="true"> * </span></label>
                                  <div class="col-md-9">
                                      <input type="text" ng-model="materiel.stockUnit" class="form-control" >
                                  </div>

                              </div>
                          </div>
                          <!--/span-->
                          <div class="col-md-6">
                              <div class="form-group">
                                  <label class="control-label col-md-3">上级产品</label>
                                  <div class="col-md-9">
                                      <input type="text" ng-model="materiel.parentMaterielSerial" class="form-control" >
                                  </div>
                              </div>
                          </div>
                          <!--/span-->
                      </div>
                      <!--/row-->
                       <div class="row">
                          <div class="col-md-6">
                              <div class="form-group">
                                  <label class="control-label col-md-3">品牌</label>
                                  <div class="col-md-9">
                                      <input type="text" ng-model="materiel.brand" class="form-control" >
                                  </div>

                              </div>
                          </div>
                          <!--/span-->
                          <div class="col-md-6">
                              <div class="form-group">
                                  <label class="control-label col-md-3">原产国</label>
                                  <div class="col-md-9">
                                      <input type="text" ng-model="materiel.originCountry" class="form-control" >
                                  </div>
                              </div>
                          </div>
                          <!--/span-->
                      </div>
                      <!--/row-->
                       <div class="row">
                          <div class="col-md-6">
                              <div class="form-group">
                                  <label class="control-label col-md-3">长度</label>
                                  <div class="col-md-9">
                                      <input type="text" ng-model="materiel.length" class="form-control" >
                                  </div>

                              </div>
                          </div>
                          <!--/span-->
                          <div class="col-md-6">
                              <div class="form-group">
                                  <label class="control-label col-md-3">币种</label>
                                  <div class="col-md-9">
                                      <input type="text" ng-model="materiel.currency" class="form-control" >
                                  </div>
                              </div>
                          </div>
                          <!--/span-->
                      </div>
                      <!--/row-->
                      <div class="row">
                          <div class="col-md-6">
                              <div class="form-group">
                                  <label class="control-label col-md-3">宽度</label>
                                  <div class="col-md-9">
                                      <input type="text" ng-model="materiel.width" class="form-control" >
                                  </div>

                              </div>
                          </div>
                          <!--/span-->
                          <div class="col-md-6">
                              <div class="form-group">
                                  <label class="control-label col-md-3">单价</label>
                                  <div class="col-md-9">
                                      <input type="text" ng-model="materiel.unitPrice" class="form-control" >
                                  </div>
                              </div>
                          </div>
                          <!--/span-->
                      </div>
                      <!--/row-->
                      <div class="row">
                          <div class="col-md-6">
                              <div class="form-group">
                                  <label class="control-label col-md-3">高度</label>
                                  <div class="col-md-9">
                                      <input type="text" ng-model="materiel.height" class="form-control" >
                                  </div>

                              </div>
                          </div>
                          <!--/span-->
                          <div class="col-md-6">
                              <div class="form-group">
                                  <label class="control-label col-md-3">备案项号</label>
                                  <div class="col-md-9">
                                      <input type="text" ng-model="materiel.filingItemNo" class="form-control" >
                                  </div>
                              </div>
                          </div>
                          <!--/span-->
                      </div>
                      <!--/row-->
                      <div class="row">
                          <div class="col-md-6">
                              <div class="form-group">
                                  <label class="control-label col-md-3">单件体积</label>
                                  <div class="col-md-9">
                                      <input type="text" ng-model="materiel.volume" class="form-control" >
                                  </div>

                              </div>
                          </div>
                          <!--/span-->
                          <div class="col-md-6">
                              <div class="form-group">
                                  <label class="control-label col-md-3">海关监管条件</label>
                                  <div class="col-md-9">
                                      <input type="text" ng-model="materiel.customsSupervisionConditions" class="form-control" >
                                  </div>
                              </div>
                          </div>
                          <!--/span-->
                      </div>
                      <!--/row-->
                      <div class="row">
                          <div class="col-md-6">
                              <div class="form-group">
                                  <label class="control-label col-md-3">单件重量</label>
                                  <div class="col-md-9">
                                      <input type="text" ng-model="materiel.weight" class="form-control" >
                                  </div>

                              </div>
                          </div>
                          <!--/span-->
                          <div class="col-md-6">
                              <div class="form-group">
                                  <label class="control-label col-md-3">保质期</label>
                                  <div class="col-md-9">
                                      <input type="text" ng-model="materiel.qualityDate" class="form-control" >
                                  </div>
                              </div>
                          </div>
                          <!--/span-->
                      </div>
                      <!--/row-->
                      <div class="row">
                          <div class="col-md-6">
                              <div class="form-group">
                                  <label class="control-label col-md-3">托盘规格</label>
                                  <div class="col-md-9">
                                      <input type="text" ng-model="materiel.palletSpecification" class="form-control" >
                                  </div>

                              </div>
                          </div>
                          <!--/span-->
                          <div class="col-md-6">
                              <div class="form-group">
                                  <label class="control-label col-md-3">生产日期</label>
                                  <div class="col-md-9">
                                      <input class="form-control form-control-inline input-medium date-picker" size="16" ng-model="materiel.manufactureDate" type="text" value="" />
                                  </div>
                              </div>
                          </div>
                          <!--/span-->
                      </div>
                      <!--/row-->
                      <div class="row">
                          <div class="col-md-6">
                              <div class="form-group">
                                  <label class="control-label col-md-3">每托数量</label>
                                  <div class="col-md-9">
                                      <input type="text" ng-model="materiel.palletWeight" class="form-control" >
                                  </div>

                              </div>
                          </div>
                          <!--/span-->
                          <div class="col-md-6">
                              <div class="form-group">
                                  <label class="control-label col-md-3">备注</label>
                                  <div class="col-md-9">
                                      <input type="text" ng-model="materiel.remark" class="form-control" >
                                  </div>
                              </div>
                          </div>
                          <!--/span-->
                      </div>
                      <!--/row-->
                      <div class="row">
                          <div class="col-md-6">
                              <div class="form-group">
                                  <label class="control-label col-md-3">是否BOM物料</label>
                                  <div class="col-md-9">
                                  		<div class="input-group">
                                            <div class="icheck-inline">
                                                <label class="">
                                                    <input type="checkbox" class="icheck" ng-model="materiel.isBOM" value="1"> 是 </label>{{materiel.isBOM}}
                                            </div>
                                        </div>
                                  </div>
                              </div>
                          </div>
                          <!--/span-->
                          <div class="col-md-6">
                              <div class="form-group">
                                  <label class="control-label col-md-3">版本</label>
                                  <div class="col-md-9">
                                      {{materiel.versionNO}}
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
                                      <button ng-click="save(materielForm.$valid)" class="btn green">保存</button>
                                      <button ui-sref="materiel" type="button" class="btn default">取消</button>
                                  </div>
                              </div>
                          </div>
                      </div>
                  </div>
              </form>
              <!-- END FORM-->
          </div>
      </div>


