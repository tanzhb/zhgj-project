<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!-- BEGIN PAGE HEADER-->

<!-- <h3 class="page-title"> 物料信息
</h3> -->
<!-- <div class="page-bar">
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
</div> -->
<div class="row" id="materielPrint">
    <div class="col-md-12">
        <!-- BEGIN EXAMPLE TABLE PORTLET-->
          <div class="portlet light ">
            <div class="portlet-body">
					<ul class="nav nav-tabs" id="companyTab">
						<li class="active bold">
	                  		<a data-target="#tab_1_1" data-toggle="tab">基本信息</a>
	              		</li>
						<li class="bold"><a data-target="#tab_1_2" data-toggle="tab">包装信息</a>
						</li>
						<li class="bold" ng-show="BOMShow"><a data-target="#tab_1_4" data-toggle="tab">下级物料</a></li>
						<shiro:hasRole name="admin">
							<li class="bold"><a data-target="#tab_1_5" data-toggle="tab">供应商</a></li>
							<li class="bold"><a data-target="#tab_1_6" data-toggle="tab">采购商</a></li>
						</shiro:hasRole>
						<li class="bold"><a data-target="#tab_1_3" data-toggle="tab">附件</a></li>
						<li class="dropdown pull-right tabdrop">
							<button type="button" onclick="goBackPage()" class="btn defualt  btn-circle  btn-sm"><i class="fa fa-reply"></i>返回</button>
						</li>
					</ul>
					<div class="tab-content">
						<div class="tab-pane fade active in" id="tab_1_1">
							<div class="portlet-title"style="min-height: 48px;">
				               <div class="tools" style="float:right" id="noprintdiv">
	                            	<button type="submit" ng-click="save()" ng-hide="materielInput" class="btn green  btn-circle  btn-sm">
	                               		<i class="fa fa-save"></i> 保存 </button>
	                               <button ng-click="cancel()" type="button" ng-hide="materielInput" class="btn defualt  btn-circle  btn-sm">
	                               		<i class="fa fa-undo"></i> 取消 </button>
	                               <button ng-click="edit()" type="button" ng-show="materielShow" class="btn purple  btn-circle  btn-sm">
	                               		<i class="fa fa-edit"></i> 编辑 </button>
	                             </div>
	                        </div>
				          <div class="portlet-body form">
				              <!-- BEGIN FORM-->
				              <form action="#" id="form_sample_1"  >
				                  <div class="form-body">
				                      <div class="alert alert-danger display-hide">
				                          <button class="close" data-close="alert"></button> 请先输入正确数据！ </div>
				                      <shiro:hasRole name="admin">
				                      <div class="row">
				                          <div class="col-md-4">
				                              <div class="form-group">
				                                  <label class="control-label bold">物料编码<span class="required" aria-required="true"> * </span></label>
				                                  <div class="">
				                                  <input type="text" name="materielNum" class="form-control" ng-hide="materielInput" ng-model="materiel.materielNum"  >
				                                      <div class="form-control-focus"> </div>
				                                      <span class="help-block" ng-hide="materielInput">请输入物料编码</span>
				                                      <p class="form-control-static" ng-show="materielShow"> {{materiel.materielNum}} </p>
				                                  </div>
				                              </div>
				                          </div>
				                          <!--/span-->
				                          <div class="col-md-4">
				                              <div class="form-group ">
				                                  <label class="control-label bold">助记码</label>
				                                  <div class="">
				                                        <input class="form-control" type="text" ng-hide="materielInput" ng-model="materiel.mnemonicCode"> 
				                                        <div class="form-control-focus"> </div>
				                              			<span class="help-block" ng-hide="materielInput">请输入助记码</span>
				                                      <p class="form-control-static" ng-show="materielShow"> {{materiel.mnemonicCode}} </p>
				                                  </div>
				                              </div>
				                          </div>
				                          <!--/span-->
				                          <div class="col-md-4">
				                              <div class="form-group ">
				                                  <label class="control-label bold">物料名称<span class="required" aria-required="true"> * </span></label>
				                                  <div class="">
				                                      <input type="text"  name="materielName" class="form-control" ng-hide="materielInput" ng-model="materiel.materielName" >
				                                  		<div class="form-control-focus"> </div>
				                                      <span class="help-block" ng-hide="materielInput">请输入物料名称</span>
				                                      <p class="form-control-static" ng-show="materielShow"> {{materiel.materielName}} </p>
				                                  </div>
				
				                              </div>
				                          </div>
				                      </div>
				                      <!--/row-->
				                      <div class="row">
				                      	<div class="col-md-4">
				                              <div class="form-group ">
				                                  <label class="control-label bold">规格型号<span class="required" aria-required="true"> * </span></label>
				                                  <div class="">
				                                      <input type="text" name="specifications" ng-hide="materielInput" ng-model="materiel.specifications" class="form-control" >
				                                  		<div class="form-control-focus"> </div>
				                                      <span class="help-block" ng-hide="materielInput">请输入规格型号</span>
				                                      <p class="form-control-static" ng-show="materielShow"> {{materiel.specifications}} </p>
				                                  </div>
				
				                              </div>
				                          </div>
				                      	<div class="col-md-4">
				                              <div class="form-group ">
				                                  <label class="control-label bold">物料大类<span class="required" aria-required="true"> * </span></label>
				                                  <div class="">
				                                      <select class="form-control" name="type" ng-hide="materielInput" ng-model="materiel.type" class="form-control"  
				                                      ng-change="queryCategoryListByLevel('second')" >
				                                              <option ng-repeat="_category in fristCategoryList" value="{{_category.categoryId}}">{{_category.categoryName}}</option>
				                                             </select>
				                                      <div class="form-control-focus"> </div>
				                                      <span class="help-block" ng-hide="materielInput">请选择物料大类</span>
				                                      <p class="form-control-static" ng-show="materielShow"> {{materiel.typeName}} </p>
				                                  </div>
				
				                              </div>
				                          </div>
				                          <div class="col-md-4">
				                              <div class="form-group ">
				                                  <label class="control-label bold">一级分类<span class="required" aria-required="true"> * </span></label>
				                                  <div class="">
				                                      <select class="form-control" name="category" ng-hide="materielInput" ng-model="materiel.category1" class="form-control"  
				                                      ng-change="queryCategoryListByLevel('third')" >
				                                              <option ng-repeat="_category in secondCategoryList" value="{{_category.categoryId}}">{{_category.categoryName}}</option>
				                                             </select>
				                                  		<div class="form-control-focus"> </div>
				                                      <span class="help-block" ng-hide="materielInput">请输入物料一级分类</span>
				                                      <p class="form-control-static" ng-show="materielShow"> {{materiel.categoryName1}} </p>
				                                  </div>
				
				                              </div>
				                          </div>
				                      </div>
				                      <!--/row-->
				                      <div class="row">
				                           <div class="col-md-4">
				                              <div class="form-group ">
				                                  <label class="control-label bold">二级分类</label>
				                                  <div class="">
				                                      <select class="form-control" name="category" ng-hide="materielInput" ng-model="materiel.category2" class="form-control"  
				                                      ng-change="queryCategoryListByLevel('fourth')" >
				                                              <option ng-repeat="_category in thirdCategoryList" value="{{_category.categoryId}}">{{_category.categoryName}}</option>
				                                             </select>
				                                  		<div class="form-control-focus"> </div>
				                                      <span class="help-block" ng-hide="materielInput">请输入物料二级分类</span>
				                                      <p class="form-control-static" ng-show="materielShow"> {{materiel.categoryName2}} </p>
				                                  </div>
				
				                              </div>
				                          </div>
				                           <div class="col-md-4">
				                              <div class="form-group ">
				                                  <label class="control-label bold">三级分类</label>
				                                  <div class="">
				                                      <select class="form-control" name="category" ng-hide="materielInput" ng-model="materiel.category3" class="form-control" >
				                                              <option ng-repeat="_category in fourthCategoryList" value="{{_category.categoryId}}">{{_category.categoryName}}</option>
				                                             </select>
				                                  		<div class="form-control-focus"> </div>
				                                      <span class="help-block" ng-hide="materielInput">请输入物料三级分类</span>
				                                      <p class="form-control-static" ng-show="materielShow"> {{materiel.categoryName3}} </p>
				                                  </div>
				
				                              </div>
				                          </div>
				                          <!--/span-->
				                          <div class="col-md-4">
				                              <div class="form-group ">
				                                  <label class="control-label bold">物料属性</label>
				                                  <div ng-hide="materielInput">
				                                      <select id="materielAttribute" class="mt-multiselect btn btn-default form-control" multiple="multiple" data-label="left" data-filter="true" name="materielAttribute" ng-hide="materielInput" ng-model="materiel.materielAttribute" >
		                                              <option ng-repeat="_function in functionList" value="{{_function.categoryId}}">{{_function.categoryName}}</option>
		                                             </select>
				                                      <div class="form-control-focus"> </div>
				                                      <span class="help-block" ng-hide="materielInput">请选择物料属性</span>
				                                      
				                                  </div>
				                                  <div ng-show="materielShow">
				                                   	<p class="form-control-static" ng-show="materielShow"> {{materiel.materielAttributeName}} </p>
				                                  </div>
				                                 
				
				                              </div>
				                          </div>
				                      </div>
				                      </shiro:hasRole>
				                      <shiro:lacksRole name="admin">
					                      <div class="row">
					                          <div class="col-md-4">
					                              <div class="form-group">
					                                  <label class="control-label bold">物料编码<span class="required" aria-required="true"> * </span></label>
					                                  <div class="">
					                                      <p class="form-control-static" > {{materiel.materielNum}} </p>
					                                  </div>
					                              </div>
					                          </div>
					                          <!--/span-->
					                          <div class="col-md-4">
					                              <div class="form-group ">
					                                  <label class="control-label bold">助记码</label>
					                                  <div class="">
					                                      <p class="form-control-static" > {{materiel.mnemonicCode}} </p>
					                                  </div>
					                              </div>
					                          </div>
					                          <!--/span-->
					                          <div class="col-md-4">
					                              <div class="form-group ">
					                                  <label class="control-label bold">物料名称<span class="required" aria-required="true"> * </span></label>
					                                  <div class="">
					                                      <p class="form-control-static" > {{materiel.materielName}} </p>
					                                  </div>
					
					                              </div>
					                          </div>
					                      </div>
					                      <!--/row-->
					                      <div class="row">
					                      	<div class="col-md-4">
					                              <div class="form-group ">
					                                  <label class="control-label bold">规格型号<span class="required" aria-required="true"> * </span></label>
					                                  <div class="">
					                                      <p class="form-control-static" > {{materiel.specifications}} </p>
					                                  </div>
					
					                              </div>
					                          </div>
					                      	<div class="col-md-4">
					                              <div class="form-group ">
					                                  <label class="control-label bold">物料大类<span class="required" aria-required="true"> * </span></label>
					                                  <div class="">
					                                      <p class="form-control-static" > {{materiel.typeName}} </p>
					                                  </div>
					
					                              </div>
					                          </div>
					                          <div class="col-md-4">
					                              <div class="form-group ">
					                                  <label class="control-label bold">一级分类<span class="required" aria-required="true"> * </span></label>
					                                  <div class="">
					                                      <p class="form-control-static" > {{materiel.categoryName1}} </p>
					                                  </div>
					
					                              </div>
					                          </div>
					                      </div>
					                      <!--/row-->
					                      <div class="row">
					                           <div class="col-md-4">
					                              <div class="form-group ">
					                                  <label class="control-label bold">二级分类</label>
					                                  <div class="">
					                                      <p class="form-control-static" > {{materiel.categoryName2}} </p>
					                                  </div>
					
					                              </div>
					                          </div>
					                           <div class="col-md-4">
					                              <div class="form-group ">
					                                  <label class="control-label bold">三级分类</label>
					                                  <div class="">
					                                      <p class="form-control-static" > {{materiel.categoryName3}} </p>
					                                  </div>
					
					                              </div>
					                          </div>
					                          <!--/span-->
					                          <div class="col-md-4">
					                              <div class="form-group ">
					                                  <label class="control-label bold">物料属性</label>
					                                  <div >
					                                   	<p class="form-control-static" > {{materiel.materielAttributeName}} </p>
					                                  </div>
					                                 
					
					                              </div>
					                          </div>
					                      </div>
				                      
				                      </shiro:lacksRole>
				                     <!--  <div class="row">
				                          
				                          /span
				                          <div class="col-md-6">
				                              <div class="form-group ">
				                                  <label class="control-label bold">产地</label>
				                                  <div class="">
				                                      <input type="text" class="form-control" ng-hide="materielInput" ng-model="materiel.productionPlace" >
				                                      <div class="form-control-focus"> </div>
				                                      <span class="help-block" ng-hide="materielInput">请输入产地</span>
				                                       <p class="form-control-static" ng-show="materielShow"> {{materiel.productionPlace}} </p>
				                                  </div>
				
				                              </div>
				                          </div>
				                          /span
				                      </div> -->
				                      <!--/row-->
				                      <div class="row">
				                      	<div class="col-md-4">
				                              <div class="form-group ">
				                                  <label class="control-label bold">单位</label>
				                                  <div class="">
				                                      <input type="text" class="form-control" ng-hide="materielInput" ng-model="materiel.unit" >
				                                      <div class="form-control-focus"> </div>
				                                      <span class="help-block" ng-hide="materielInput">请输入单位</span>
				                                      <p class="form-control-static" ng-show="materielShow"> {{materiel.unit}} </p>
				                                  </div>
				
				                              </div>
				                          </div>
				                          <div class="col-md-4">
				                              <div class="form-group ">
				                                  <label class="control-label bold">品牌</label>
				                                  <div class="">
				                                      <input type="text" ng-hide="materielInput" ng-model="materiel.brand" class="form-control" >
				                                      <div class="form-control-focus"> </div>
				                                       <span class="help-block" ng-hide="materielInput">请输入品牌</span>
				                                      <p class="form-control-static" ng-show="materielShow"> {{materiel.brand}} </p>
				                                  </div>
				
				                              </div>
				                          </div>
				                          <!--/span-->
				                          <div class="col-md-4">
				                              <div class="form-group ">
				                                  <label class="control-label bold">原产国</label>
				                                  <div class="">
				                                      <input type="text" ng-hide="materielInput" ng-model="materiel.originCountry" class="form-control" >
				                                      <div class="form-control-focus"> </div>
				                                      <span class="help-block" ng-hide="materielInput">请输入原产国</span>
				                                      <p class="form-control-static" ng-show="materielShow"> {{materiel.originCountry}} </p>
				                                  </div>
				                              </div>
				                          </div>
				                          <!--/span-->
				                      </div>
				                     <!--  <div class="row">
				                          <div class="col-md-6">
				                              <div class="form-group ">
				                                  <label class="control-label bold">库存单位<span class="required" aria-required="true"> * </span></label>
				                                  <div class="">
				                                      <input type="text" name="stockUnit" ng-hide="materielInput" ng-model="materiel.stockUnit" class="form-control" >
				                                  <div class="form-control-focus"> </div>
				                                      <span class="help-block" ng-hide="materielInput">请输入库存单位</span>
				                                      <p class="form-control-static" ng-show="materielShow"> {{materiel.stockUnit}} </p>
				                              </div>
				                          </div>
				                          /span
				                          <div class="col-md-6">
				                              <div class="form-group ">
				                                  <label class="control-label bold">上级产品</label>
				                                  <div class="">
				                                      <input type="text" ng-hide="materielInput" readonly ng-click="selectMateriel('parent')" data-target="#basicMaterielInfo" data-toggle="modal" ng-model="materiel.parentMateriel.materielName" class="form-control" >
				                                      <div class="form-control-focus"> </div>
				                                       <span class="help-block" ng-hide="materielInput">请选择上级产品</span>
				                                      <p class="form-control-static" ng-show="materielShow"> {{materiel.parentMateriel.materielName}} </p>
				                                  </div>
				                              </div>
				                          </div>
				                          /span
				                      </div> -->
				                      <!--/row-->
				                       
				                      <!--/row-->
				                       <div class="row">
				                          <div class="col-md-4">
				                              <div class="form-group ">
				                                  <label class="control-label bold">长度</label>
				                                  <div class="">
				                                      <input type="text" ng-hide="materielInput" ng-model="materiel.length" class="form-control" >
				                                      <div class="form-control-focus"> </div>
				                                      <span class="help-block" ng-hide="materielInput">请输入长度</span>
				                                      <p class="form-control-static" ng-show="materielShow"> {{materiel.length}} </p>
				                                  </div>
				
				                              </div>
				                          </div>
				                          <!--/span-->
				                          <div class="col-md-4">
				                              <div class="form-group ">
				                                  <label class="control-label bold">币种</label>
				                                  <div class="">
				                                      <input type="text" ng-hide="materielInput" ng-model="materiel.currency" class="form-control" >
				                                      <div class="form-control-focus"> </div>
				                                      <span class="help-block" ng-hide="materielInput">请输入币种</span>
				                                      <p class="form-control-static" ng-show="materielShow"> {{materiel.currency}} </p>
				                                  </div>
				                              </div>
				                          </div>
				                          <div class="col-md-4">
				                              <div class="form-group ">
				                                  <label class="control-label bold">宽度</label>
				                                  <div class="">
				                                      <input type="text" ng-hide="materielInput" ng-model="materiel.width" class="form-control" >
				                                      <div class="form-control-focus"> </div>
				                                      <span class="help-block" ng-hide="materielInput">请输入宽度</span>
				                                      <p class="form-control-static" ng-show="materielShow"> {{materiel.width}} </p>
				                                  </div>
				
				                              </div>
				                          </div>
				                          <!--/span-->
				                      </div>
				                      <!--/row-->
				                      <div class="row">
				                      	<div class="col-md-4">
				                              <div class="form-group ">
				                                  <label class="control-label bold">单价</label>
				                                  <div class="">
				                                      <input type="text" ng-hide="materielInput" ng-model="materiel.unitPrice" class="form-control" >
				                                      <div class="form-control-focus"> </div>
				                                      <span class="help-block" ng-hide="materielInput">请输入单价</span>
				                                      <p class="form-control-static" ng-show="materielShow"> {{materiel.unitPrice}} </p>
				                                  </div>
				                              </div>
				                          </div>
				                          <div class="col-md-4">
				                              <div class="form-group ">
				                                  <label class="control-label bold">高度</label>
				                                  <div class="">
				                                      <input type="text" ng-hide="materielInput" ng-model="materiel.height" class="form-control" >
				                                      <div class="form-control-focus"> </div>
				                                      <span class="help-block" ng-hide="materielInput">请输入高度</span>
				                                      <p class="form-control-static" ng-show="materielShow"> {{materiel.height}} </p>
				                                  </div>
				
				                              </div>
				                          </div>
				                          <div class="col-md-4">
				                              <div class="form-group ">
				                                  <label class="control-label bold">单件体积</label>
				                                  <div class="">
				                                      <input type="text" ng-hide="materielInput" ng-model="materiel.volume" class="form-control" >
				                                      <div class="form-control-focus"> </div>
				                                      <span class="help-block" ng-hide="materielInput">请输入单件体积</span>
				                                      <p class="form-control-static" ng-show="materielShow"> {{materiel.volume}} </p>
				                                  </div>
				
				                              </div>
				                          </div>
				                          <!--/span-->
				                      </div>
				                      <!--/row-->
				                      <div class="row">
				                          <div class="col-md-4">
				                              <div class="form-group ">
				                                  <label class="control-label bold">单件重量</label>
				                                  <div class="">
				                                      <input type="text" ng-hide="materielInput" ng-model="materiel.weight" class="form-control" >
				                                      <div class="form-control-focus"> </div>
				                                      <span class="help-block" ng-hide="materielInput">请输入单件重量</span>
				                                      <p class="form-control-static" ng-show="materielShow"> {{materiel.weight}} </p>
				                                  </div>
				
				                              </div>
				                          </div>
				                          <div class="col-md-4">
				                              <div class="form-group ">
				                                  <label class="control-label bold">托盘规格</label>
				                                  <div class="">
				                                      <input type="text" ng-hide="materielInput" ng-model="materiel.palletSpecification" class="form-control" >
				                                      <div class="form-control-focus"> </div>
				                                      <span class="help-block" ng-hide="materielInput">请输入托盘规格</span>
				                                      <p class="form-control-static" ng-show="materielShow"> {{materiel.palletSpecification}} </p>
				                                  </div>
				
				                              </div>
				                          </div>
				                          <div class="col-md-4">
				                              <div class="form-group ">
				                                  <label class="control-label bold">每托数量</label>
				                                  <div class="">
				                                      <input type="text" ng-hide="materielInput" ng-model="materiel.palletWeight" class="form-control" >
				                                      <div class="form-control-focus"> </div>
				                                       <span class="help-block" ng-hide="materielInput">请输入每托数量</span>
				                                      <p class="form-control-static" ng-show="materielShow"> {{materiel.palletWeight}} </p>
				                                  </div>
				
				                              </div>
				                          </div>
			                          </div>
				                      <!--/row-->
				                      <div class="row">
				                          <div class="col-md-4">
				                              <div class="form-group ">
				                                  <label class="control-label bold">备案项号</label>
				                                  <div class="">
				                                      <input type="text" ng-hide="materielInput" ng-model="materiel.filingItemNo" class="form-control" >
				                                      <div class="form-control-focus"> </div>
				                                      <span class="help-block" ng-hide="materielInput">请输入备案项号</span>
				                                      <p class="form-control-static" ng-show="materielShow"> {{materiel.filingItemNo}} </p>
				                                  </div>
				                              </div>
				                          </div>
				                          <!--/span-->
				                          <div class="col-md-4">
				                              <div class="form-group ">
				                                  <label class="control-label bold">海关监管条件</label>
				                                  <div class="">
				                                      <input type="text" ng-hide="materielInput" ng-model="materiel.customsSupervisionConditions" class="form-control" >
				                                      <div class="form-control-focus"> </div>
				                                      <span class="help-block" ng-hide="materielInput">请输入海关监管条件</span>
				                                      <p class="form-control-static" ng-show="materielShow"> {{materiel.customsSupervisionConditions}} </p>
				                                  </div>
				                              </div>
				                          </div>
				                          <!--/span-->
				                          <div class="col-md-4">
				                              <div class="form-group ">
				                                  <label class="control-label bold">保质期</label>
				                                  <div class="">
				                                      <input type="text" ng-hide="materielInput" ng-model="materiel.qualityDate" class="form-control" >
				                                      <div class="form-control-focus"> </div>
				                                      <span class="help-block" ng-hide="materielInput">请输入保质期</span>
				                                      <p class="form-control-static" ng-show="materielShow"> {{materiel.qualityDate}} </p>
				                                  </div>
				                              </div>
				                          </div>
				                          <!--/span-->
				                      </div>
				                      <!--/row-->
<!-- 				                      <div class="row">
				                          
				                          /span
				                          <div class="col-md-6">
				                              <div class="form-group ">
				                                  <label class="control-label bold">生产日期</label>
				                                  <div class="">
				                                      <input class="form-control form-control-inline input-medium date-picker" 
				                                      data-date-format="yyyy-mm-dd" data-date-viewmode="years" size="16" ng-hide="materielInput" ng-model="materiel.manufactureDate" type="text" value="" />
				                                      <div class="form-control-focus"> </div>
				                                      <span class="help-block" ng-hide="materielInput">请选择生产日期</span>
				                                      <p class="form-control-static" ng-show="materielShow"> {{materiel.manufactureDate}} </p>
				                                  </div>
				                              </div>
				                          </div>
				                          /span
				                      </div> -->
				                      <!--/row-->
				                      <div class="row">
				                      		<div class="col-md-4">
				                              <div class="form-group ">
				                                  <label class="control-label bold">海关编码</label>
				                                  <div class="">
				                                      <input type="text" name="customsCode" ng-hide="materielInput" ng-model="materiel.customsCode" class="form-control" >
				                                      <div class="form-control-focus"> </div>
				                                      <span class="help-block" ng-hide="materielInput">请输入海关编码</span>
				                                      <p class="form-control-static" ng-show="materielShow"> {{materiel.customsCode}} </p>
				                                  </div>
				                              </div>
				                          </div>
				                      		<div class="col-md-4">
				                              <div class="form-group ">
				                                  <label class="control-label bold">一般交付周期<span class="required" aria-required="true"> * </span></label>
				                                  <div class="">
				                                      <input type="text" name="deliveryCycle" ng-hide="materielInput" ng-model="materiel.deliveryCycle" class="form-control" >
				                                      <div class="form-control-focus"> </div>
				                                      <span class="help-block" ng-hide="materielInput">请输入一般交付周期（月）</span>
				                                      <p class="form-control-static" ng-show="materielShow"> {{materiel.deliveryCycle}} </p>
				                                  </div>
				                              </div>
				                          </div>
				                          <div class="col-md-4">
				                              <div class="form-group ">
				                                  <label class="control-label bold">包含下级物料</label>
				                                  <div class="">
				                                  		<div class="input-group" ng-hide="materielInput">
				                                            <div class="icheck-inline">
				                                                <input type="checkbox" class="icheck" id="isBOMcheck" > 是  </label>
				                                            </div>
				                                        </div>
				                                        <p class="form-control-static" ng-show="materielShow" > {{materiel.isBOM=="1"?'是':'否'}} </p>
				                                  </div>
				                              </div>
				                          </div>
				                      </div>
				                      <!--/row-->
				                      <div class="row">
				                      	<div class="col-md-4">
				                              <div class="form-group ">
				                                  <label class="control-label bold">备注</label>
				                                  <div class="">
				                                      <input type="text" ng-hide="materielInput" ng-model="materiel.remark" class="form-control" >
				                                      <div class="form-control-focus"> </div>
				                                      <span class="help-block" ng-hide="materielInput">请输入备注</span>
				                                      <p class="form-control-static" ng-show="materielShow"> {{materiel.remark}} </p>
				                                  </div>
				                              </div>
				                          </div>
				                          <div class="col-md-4">
				                              <div class="form-group ">
				                                  <label class="control-label bold">版本</label>
				                                  <div class="">
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
						</div>
						<div class="tab-pane fade" id="tab_1_2">
							<div class="portlet-title" style="min-height: 48px;">
				               <!-- <div class="caption">附件</div> -->
				               <div class="tools" style="float:right">
		                       	 	<button type="submit" ng-click="savePackage()" ng-hide="materielPackageInput" class="btn green  btn-circle  btn-sm">
		                          		<i class="fa fa-save"></i> 保存 </button>
		                          <button ng-click="cancelPackage()" type="button" ng-hide="materielPackageInput" class="btn defualt  btn-circle  btn-sm">
		                          		<i class="fa fa-undo"></i> 取消 </button>
		                          <button ng-click="editPackage()" type="button" ng-show="materielPackageShow" class="btn purple  btn-circle  btn-sm">
		                          		<i class="fa fa-edit"></i> 编辑 </button>
		                        </div>
		                   </div>
				           <div class="portlet-body form">
				
							     <form id="form_sample_2"   >
							         <div class="form-body">
							             <div class="alert alert-danger display-hide">
							                 <button class="close" data-close="alert"></button> 请先输入正确数据！ </div>
							                 <div class="row">
						                          <div class="col-md-6">
						                              <div class="form-group">
						                              	<label class="control-label bold">包装编号<span class="required" aria-required="true"> * </span></label>
						                                  <div class="">
						                                  <input type="text" name="packageNum" class="form-control" ng-hide="materielPackageInput" ng-model="materiel.packageNum"  >
							                                  <div class="form-control-focus"> </div>
							                                  <span class="help-block" ng-hide="materielPackageInput">请输入包装编号</span>
							                                  <p class="form-control-static" ng-show="materielPackageShow"> {{materiel.packageNum}} </p>
						                                  </div>
						                              </div>
						                          </div>
						                          <!--/span-->
						                          <div class="col-md-6">
						                              <div class="form-group ">
						                                  <label class="control-label bold">包装规格<span class="required" aria-required="true"> * </span></label>
						                                  <div class="">
						                                        <input type="text" name="packageSpecifications" class="form-control" ng-hide="materielPackageInput" ng-model="materiel.packageSpecifications"  >
							                                  <div class="form-control-focus"> </div>
							                                  <span class="help-block" ng-hide="materielPackageInput">请输入包装规格</span>
							                                  <p class="form-control-static" ng-show="materielPackageShow"> {{materiel.packageSpecifications}} </p>
						                                  </div>
						                              </div>
						                          </div>
						                          <!--/span-->
						                      </div>
						                      <div class="row">
						                          <div class="col-md-6">
						                              <div class="form-group">
						                              	<label class="control-label bold">包装单位<span class="required" aria-required="true"> * </span></label>
						                                  <div class="">
						                                  <input type="text" name="packageUnit" class="form-control" ng-hide="materielPackageInput" ng-model="materiel.packageUnit"  >
							                                  <div class="form-control-focus"> </div>
							                                  <span class="help-block" ng-hide="materielPackageInput">请输入包装单位</span>
							                                  <p class="form-control-static" ng-show="materielPackageShow"> {{materiel.packageUnit}} </p>
						                                  </div>
						                              </div>
						                          </div>
						                          <!--/span-->
						                          <div class="col-md-6">
						                              <div class="form-group ">
						                                  <label class="control-label bold">长</label>
						                                  <div class="">
						                                        <input type="text" name="packagelength" class="form-control" ng-hide="materielPackageInput" ng-model="materiel.packagelength"  >
							                                  <div class="form-control-focus"> </div>
							                                  <span class="help-block" ng-hide="materielPackageInput">请输入长度</span>
							                                  <p class="form-control-static" ng-show="materielPackageShow"> {{materiel.packagelength}} </p>
						                                  </div>
						                              </div>
						                          </div>
						                          <!--/span-->
						                      </div>
						                      <div class="row">
						                          <div class="col-md-6">
						                              <div class="form-group">
						                              	<label class="control-label bold">宽</label>
						                                  <div class="">
						                                  <input type="text" name="packagewidth" class="form-control" ng-hide="materielPackageInput" ng-model="materiel.packagewidth"  >
							                                  <div class="form-control-focus"> </div>
							                                  <span class="help-block" ng-hide="materielPackageInput">请输入宽度</span>
							                                  <p class="form-control-static" ng-show="materielPackageShow"> {{materiel.packagewidth}} </p>
						                                  </div>
						                              </div>
						                          </div>
						                          <!--/span-->
						                          <div class="col-md-6">
						                              <div class="form-group ">
						                                  <label class="control-label bold">高</label>
						                                  <div class="">
						                                       <input type="text" name="packageheight" class="form-control" ng-hide="materielPackageInput" ng-model="materiel.packageheight"  >
							                                  <div class="form-control-focus"> </div>
							                                  <span class="help-block" ng-hide="materielPackageInput">请输入高度</span>
							                                  <p class="form-control-static" ng-show="materielPackageShow"> {{materiel.packageheight}} </p>
						                                  </div>
						                              </div>
						                          </div>
						                          <!--/span-->
						                      </div>
						                      <div class="row">
						                          <div class="col-md-6">
						                              <div class="form-group">
						                              	<label class="control-label bold">包装体积</label>
						                                  <div class="">
						                                  <input type="text" name="packagevolume" class="form-control" ng-hide="materielPackageInput" ng-model="materiel.packagevolume"  >
							                                  <div class="form-control-focus"> </div>
							                                  <span class="help-block" ng-hide="materielPackageInput">请输入包装体积</span>
							                                  <p class="form-control-static" ng-show="materielPackageShow"> {{materiel.packagevolume}} </p>
						                                  </div>
						                              </div>
						                          </div>
						                          <!--/span-->
						                          <div class="col-md-6">
						                              <div class="form-group ">
						                                  <label class="control-label bold">包装单位换算<span class="required" aria-required="true"> * </span></label>
						                                  <div class="">
						                                       <input type="text" name="packageUnitConversion" class="form-control" ng-hide="materielPackageInput" ng-model="materiel.packageUnitConversion"  >
							                                  <div class="form-control-focus"> </div>
							                                  <span class="help-block" ng-hide="materielPackageInput">请输入包装单位换算</span>
							                                  <p class="form-control-static" ng-show="materielPackageShow"> {{materiel.packageUnitConversion}} </p>
						                                  </div>
						                              </div>
						                          </div>
						                          <!--/span-->
						                      </div>
							             </div>
				                  </form>
				
				          </div>
						</div>
						<div class="tab-pane fade" id="tab_1_3">
						<!-- 附件 start-->
				          <div class="portlet-title" style="min-height: 48px;">
				               <!-- <div class="caption">附件</div> -->
				               <div class="tools" style="float:right">
				               	 	<button type="submit" ng-click="saveFile()" ng-hide="fileInfoInput" class="btn green  btn-circle  btn-sm">
				                  		<i class="fa fa-save"></i> 保存 </button>
				                  <button ng-click="cancelFile()" type="button" ng-hide="fileInfoInput" class="btn defualt  btn-circle  btn-sm">
				                  		<i class="fa fa-undo"></i> 取消 </button>
				                  <button ng-click="editFile()" type="button" ng-show="fileInfoShow" class="btn purple  btn-circle  btn-sm">
				                  		<i class="fa fa-edit"></i> 编辑 </button>
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
						</div>
						<div class="tab-pane fade" id="tab_1_4" ng-show="BOMShow">
						<!-- BOM start-->
				          <div class="portlet-title" ng-show="BOMShow" style="min-height: 48px;">
				               <div class="tools" style="float:right">
				               	 	<button type="submit" ng-click="saveBOM()" ng-hide="BOMInfoInput" class="btn green  btn-circle  btn-sm">
				                  		<i class="fa fa-save"></i> 保存 </button>
				                  <button ng-click="cancelBOM()" type="button" ng-hide="BOMInfoInput" class="btn defualt  btn-circle  btn-sm">
				                  		<i class="fa fa-undo"></i> 取消 </button>
				                  <button ng-click="editBOM()" type="button" ng-show="BOMInfoShow" class="btn purple  btn-circle  btn-sm">
				                  		<i class="fa fa-edit"></i> 编辑 </button>
				                </div>
				            </div>
				           <div class="portlet-body form"  ng-show="BOMShow">
							     <form id="form_sample_3"   >
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
						</div>
						<div class="tab-pane fade" id="tab_1_5">
						<!-- 供应商 start-->
				          <div class="portlet-title" style="min-height: 48px;">
				               <div class="tools" style="float:right">
				               	 	<button type="submit" ng-click="saveSupplyMateriel()" ng-hide="supplyMaterielInfoInput" class="btn green  btn-circle  btn-sm">
				                  		<i class="fa fa-save"></i> 保存 </button>
				                  <button ng-click="cancelSupplyMateriel()" type="button" ng-hide="supplyMaterielInfoInput" class="btn defualt  btn-circle  btn-sm">
				                  		<i class="fa fa-undo"></i> 取消 </button>
				                  <button ng-click="editSupplyMateriel()" type="button" ng-show="supplyMaterielInfoShow" class="btn purple  btn-circle  btn-sm">
				                  		<i class="fa fa-edit"></i> 编辑 </button>
				                </div>
				            </div>
				           <div class="portlet-body form">
							     <form id="form_sample_5" >
							         <div class="table-scrollable">
				                          <table class="table table-bordered table-hover">
				                              <thead>
				                                  <tr>
				<!--                                       <th>供应商</th> -->
				                                      <th>供应商</th>
				                                      <th>供应商物料编号</th>
														<th>物料名称</th>
														<th>规格型号</th>
														<th>单位</th>
														<th>物料大类</th>
														<th>一级分类</th>
														<th>二级分类</th>
														<th>指导单价</th>
														<th>采购配额</th>
														<th>最小起订量</th>
														<th>备注</th>
				                                      
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
							                          
							                          <td>
					                                      <input type="text"  id="supplymaterielName[$index]" name="supplymaterielName" class="form-control" ng-hide="supplyMaterielInfoInput" ng-model="supplyMateriel[$index].materielName" >
					                                      <p class="form-control-static" ng-show="supplyMaterielInfoShow"> {{_supplyMateriel.materielName}} </p>
				                                      </td>
				                                      <td>
					                                      <input type="text"  id="supplyspecifications[$index]" name="supplyspecifications" class="form-control" ng-hide="supplyMaterielInfoInput" ng-model="supplyMateriel[$index].specifications" >
					                                      <p class="form-control-static" ng-show="supplyMaterielInfoShow"> {{_supplyMateriel.specifications}} </p>
				                                      </td>
				                                      <td>
					                                      <input type="text"  id="supplyunit[$index]" name="supplyunit" class="form-control" ng-hide="supplyMaterielInfoInput" ng-model="supplyMateriel[$index].unit" >
					                                      <p class="form-control-static" ng-show="supplyMaterielInfoShow"> {{_supplyMateriel.unit}} </p>
				                                      </td>
				                                      <td>
					                                      <select class="form-control" id="supplytype[$index]" name="supplytype" ng-hide="supplyMaterielInfoInput" ng-model="supplyMateriel[$index].type" class="form-control"  
				                                      		ng-change="supplyqueryCategoryListByLevel('second',$index)" >
				                                              <option ng-repeat="_category in _supplyMateriel.supplyfristCategoryList" value="{{_category.categoryId}}">{{_category.categoryName}}</option>
				                                             </select>
				                                      		<p class="form-control-static" ng-show="supplyMaterielInfoShow"> {{_supplyMateriel.typeName}} </p>
				                                      </td>
				                                      <td>
					                                       <select class="form-control" id="supplycategory1[$index]" name="supplycategory1" ng-hide="supplyMaterielInfoInput" ng-model="supplyMateriel[$index].category1" class="form-control"  
					                                      ng-change="supplyqueryCategoryListByLevel('third',$index)" repeat-done="renderSupplyCategory(supplyMateriel[$index])">
					                                              <option ng-repeat="_category in _supplyMateriel.supplysecondCategoryList" value="{{_category.categoryId}}">{{_category.categoryName}}</option>
					                                             </select>
					                                      <p class="form-control-static" ng-show="supplyMaterielInfoShow"> {{_supplyMateriel.categoryName1}} </p>
				                                      </td>
				                                      <td>
					                                       <select class="form-control" id="supplycategory2[$index]" name="supplycategory2" ng-hide="supplyMaterielInfoInput" ng-model="supplyMateriel[$index].category2" class="form-control"  
					                                      ng-change="supplyqueryCategoryListByLevel('fourth',$index)" >
					                                              <option ng-repeat="_category in _supplyMateriel.supplythirdCategoryList" value="{{_category.categoryId}}">{{_category.categoryName}}</option>
					                                             </select>
					                                      <p class="form-control-static" ng-show="supplyMaterielInfoShow"> {{_supplyMateriel.categoryName2}} </p>
				                                      </td>
				                                      <td>
					                                      <input type="text"  id="supplyunitPriceGuide[$index]" name="supplyunitPriceGuide" class="form-control" ng-hide="supplyMaterielInfoInput" ng-model="supplyMateriel[$index].unitPriceGuide" 
					                                      ng-keyup="clearNoNumPoint(supplyMateriel[$index],'unitPriceGuide')">
					                                      <p class="form-control-static" ng-show="supplyMaterielInfoShow"> {{_supplyMateriel.unitPriceGuide}} </p>
				                                      </td>
				                                      <td>
					                                      <input type="text"  id="supplypurchaseQuota[$index]" name="supplypurchaseQuota" class="form-control" ng-hide="supplyMaterielInfoInput" ng-model="supplyMateriel[$index].purchaseQuota" 
					                                      ng-keyup="clearNoNumPoint(supplyMateriel[$index],'purchaseQuota')">
					                                      <p class="form-control-static" ng-show="supplyMaterielInfoShow"> {{_supplyMateriel.purchaseQuota}} </p>
				                                      </td>
				                                      <td>
					                                      <input type="text"  id="supplymoq[$index]" name="supplymoq" class="form-control" ng-hide="supplyMaterielInfoInput" ng-model="supplyMateriel[$index].moq" 
					                                      ng-keyup="clearNoNumPoint(supplyMateriel[$index],'moq')">
					                                      <p class="form-control-static" ng-show="supplyMaterielInfoShow"> {{_supplyMateriel.moq}} </p>
				                                      </td>
				                                      <td>
					                                      <input type="text"  id="supplyremark[$index]" name="supplyremark" class="form-control" ng-hide="supplyMaterielInfoInput" ng-model="supplyMateriel[$index].remark" >
					                                      <p class="form-control-static" ng-show="supplyMaterielInfoShow"> {{_supplyMateriel.remark}} </p>
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
						<div class="tab-pane fade" id="tab_1_6">
						<!-- 采购商 start-->
				          <div class="portlet-title" style="min-height: 48px;">
				               <div class="tools" style="float:right">
				               	 	<button type="submit" ng-click="saveBuyMateriel()" ng-hide="buyMaterielInfoInput" class="btn green  btn-circle  btn-sm">
				                  		<i class="fa fa-save"></i> 保存 </button>
				                  <button ng-click="cancelBuyMateriel()" type="button" ng-hide="buyMaterielInfoInput" class="btn defualt  btn-circle  btn-sm">
				                  		<i class="fa fa-undo"></i> 取消 </button>
				                  <button ng-click="editBuyMateriel()" type="button" ng-show="buyMaterielInfoShow" class="btn purple  btn-circle  btn-sm">
				                  		<i class="fa fa-edit"></i> 编辑 </button>
				                </div>
				            </div>
				           <div class="portlet-body form">
							     <form id="form_sample_6" >
							         <div class="table-scrollable">
				                          <table class="table table-bordered table-hover">
				                              <thead>
				                                  <tr>
				                                      <th>采购商</th>
				                                      <th>采购商物料编号</th>
				                                      <th>物料名称</th>
														<th>规格型号</th>
														<th>单位</th>
														<th>物料大类</th>
														<th>一级分类</th>
														<th>二级分类</th>
														<th>指导单价</th>
														<th>采购配额</th>
														<th>最小起订量</th>
														<th>备注</th>
				                                      <th style="width:100px;"></th>
				                                  </tr>
				                              </thead>
				                              <tbody>
				                                  <tr ng-repeat="_buyMateriel in buyMateriel track by $index" ng-mouseover="showOperation('buyMateriel',$index)" ng-mouseleave="hideOperation('buyMateriel',$index)" >
							                          <td>
						                                 	<div ng-hide="buyMaterielInfoInput">
							                                 	<select class="form-control" id="buyComId[$index]" name="buyComId" class="bs-select form-control diySelectCss" data-live-search="true" data-size="8"  ng-model="buyMateriel[$index].buyComId"  >
					                                              	<option ng-repeat="_buy in buys" value="{{_buy.comId}}" repeat-done="repeatDone()">{{_buy.comName}}</option>
					                                             </select>
				                                             </div>
							                                <p class="form-control-static" ng-show="buyMaterielInfoShow"> {{_buyMateriel.buy.comName}} </p>
							                          </td>
							                          
				<!-- 			                          <td>
						                                 	<p class="form-control-static" ng-show="buyMaterielInfoShow"> {{_buyMateriel.buy.comName}} </p>
							                          </td> -->
							                           <td>
				                                      		<input type="text" id="buyMaterielNum[$index]" name="buyMaterielNum" class="form-control" ng-hide="buyMaterielInfoInput" ng-model="buyMateriel[$index].buyMaterielNum"  >
							                                <p class="form-control-static" ng-show="buyMaterielInfoShow"> {{_buyMateriel.buyMaterielNum}} </p>
							                          </td>
				                                      <td>
					                                      <input type="text"  id="buymaterielName[$index]" name="buymaterielName" class="form-control" ng-hide="buyMaterielInfoInput" ng-model="buyMateriel[$index].materielName" >
					                                      <p class="form-control-static" ng-show="buyMaterielInfoShow"> {{_buyMateriel.materielName}} </p>
				                                      </td>
				                                      <td>
					                                      <input type="text"  id="buyspecifications[$index]" name="buyspecifications" class="form-control" ng-hide="buyMaterielInfoInput" ng-model="buyMateriel[$index].specifications" >
					                                      <p class="form-control-static" ng-show="buyMaterielInfoShow"> {{_buyMateriel.specifications}} </p>
				                                      </td>
				                                      <td>
					                                      <input type="text"  id="buyunit[$index]" name="buyunit" class="form-control" ng-hide="buyMaterielInfoInput" ng-model="buyMateriel[$index].unit" >
					                                      <p class="form-control-static" ng-show="buyMaterielInfoShow"> {{_buyMateriel.unit}} </p>
				                                      </td>
				                                      <td>
					                                      <select class="form-control" id="buytype[$index]" name="buytype" ng-hide="buyMaterielInfoInput" ng-model="buyMateriel[$index].type" class="form-control"  
				                                      		ng-change="buyqueryCategoryListByLevel('second',$index)" repeat-done="renderBuyCategory(buyMateriel[$index])">
				                                              <option ng-repeat="_category in _buyMateriel.buyfristCategoryList" value="{{_category.categoryId}}">{{_category.categoryName}}</option>
				                                             </select>
				                                      		<p class="form-control-static" ng-show="buyMaterielInfoShow"> {{_buyMateriel.typeName}} </p>
				                                      </td>
				                                      <td>
					                                       <select class="form-control" id="buycategory1[$index]" name="buycategory1" ng-hide="buyMaterielInfoInput" ng-model="buyMateriel[$index].category1" class="form-control"  
					                                      ng-change="buyqueryCategoryListByLevel('third',$index)" >
					                                              <option ng-repeat="_category in _buyMateriel.buysecondCategoryList" value="{{_category.categoryId}}">{{_category.categoryName}}</option>
					                                             </select>
					                                      <p class="form-control-static" ng-show="buyMaterielInfoShow"> {{_buyMateriel.categoryName1}} </p>
				                                      </td>
				                                      <td>
					                                       <select class="form-control" id="buycategory2[$index]" name="buycategory2" ng-hide="buyMaterielInfoInput" ng-model="buyMateriel[$index].category2" class="form-control"  
					                                      ng-change="buyqueryCategoryListByLevel('fourth',$index)" >
					                                              <option ng-repeat="_category in _buyMateriel.buythirdCategoryList" value="{{_category.categoryId}}">{{_category.categoryName}}</option>
					                                             </select>
					                                      <p class="form-control-static" ng-show="buyMaterielInfoShow"> {{_buyMateriel.categoryName2}} </p>
				                                      </td>
				                                      <td>
					                                      <input type="text"  id="buyunitPriceGuide[$index]" name="buyunitPriceGuide" class="form-control" ng-hide="buyMaterielInfoInput" ng-model="buyMateriel[$index].unitPriceGuide" 
					                                      ng-keyup="clearNoNumPoint(buyMateriel[$index],'unitPriceGuide')">
					                                      <p class="form-control-static" ng-show="buyMaterielInfoShow"> {{_buyMateriel.unitPriceGuide}} </p>
				                                      </td>
				                                      <td>
					                                      <input type="text"  id="buypurchaseQuota[$index]" name="buypurchaseQuota" class="form-control" ng-hide="buyMaterielInfoInput" ng-model="buyMateriel[$index].purchaseQuota" 
															ng-keyup="clearNoNumPoint(buyMateriel[$index],'purchaseQuota')">
					                                      <p class="form-control-static" ng-show="buyMaterielInfoShow"> {{_buyMateriel.purchaseQuota}} </p>
				                                      </td>
				                                      <td>
					                                      <input type="text"  id="buymoq[$index]" name="buymoq" class="form-control" ng-hide="buyMaterielInfoInput" ng-model="buyMateriel[$index].moq" 
					                                      ng-keyup="clearNoNumPoint(buyMateriel[$index],'moq')">
					                                      <p class="form-control-static" ng-show="buyMaterielInfoShow"> {{_buyMateriel.moq}} </p>
				                                      </td>
				                                      <td>
					                                      <input type="text"  id="buyremark[$index]" name="buyremark" class="form-control" ng-hide="buyMaterielInfoInput" ng-model="buyMateriel[$index].remark" >
					                                      <p class="form-control-static" ng-show="buyMaterielInfoShow"> {{_buyMateriel.remark}} </p>
				                                      </td>
				                                      <td ng-show="operation_buy{{$index}}">
				                                      	<a href="javascript:;"  class="btn red btn-sm" ng-hide="buyMaterielInfoInput" ng-click="deleteBuyMateriel($index)">
				                                    			<i class="fa fa-close"></i> 
				                             				</a>
				                                      </td>
				                                  </tr>
				                              </tbody>
				                          </table>
				                      </div>
				                      <div class="form-actions right">
											<a  class="btn blue btn-sm"  ng-hide="buyMaterielInfoInput" ng-click="addBuyMateriel()"   >
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
</div> 

<jsp:include  page="selectMateriel.jsp"/>
