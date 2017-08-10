<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<h3 class="page-title"> 价格信息
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
            <a ui-sref="priceList">价格信息</a>
        </li>
    </ul>

</div>
<!-- END PAGE HEADER-->
<!-- BEGIN MAIN CONTENT -->
<div class="row">
    <div class="col-md-12">
        <!-- BEGIN EXAMPLE TABLE PORTLET-->
        <div class="portlet bordered">
            <div class="portlet-body">
				<div class="portlet light ">
                        <div class="portlet-title">
                            <div class="caption">价格信息</div>
                            <div class="actions">
                                <button  ng-show="priceListView"    class="btn blue  btn-outline  btn-sm " ng-click="editPriceList()">
                                            <i class="fa fa-edit"></i> 编辑 </button>
                                <button   ng-show="priceListEdit"   class="btn red  btn-outline  btn-sm " ng-click="cancelEditpriceList()">
                                            <i class="fa fa-undo"></i> 取消 </button>
                                <button  ng-hide="priceListAdd"   type="submit"   class="btn blue  btn-outline  btn-sm " ng-click="savePriceList()">
                                            <i class="fa fa-save"></i> 保存 </button>
                            </div>
                        </div>
                        <div class="portlet-body form">
                            <form  id="priceListForm" class="form-horizontal" >
								<div class="form-body">
									<div class="alert alert-danger display-hide">
                                                <button id="priceListTips" class="close" data-close="alert"></button>请先输入正确数据！</div>
								           <div class="row">
                                                             <div class="col-md-6">
											<div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="priceNum"> <span class="required"> * </span>价格编号 :</label>
                                                    <div class="col-md-8">
                                                        <input type="text" class="form-control" id="priceListNum" name="priceNum" ng-model="priceList.priceNum"  ng-hide="priceListAdd" />
                                                        <div class="form-control-focus"> </div>
                                                          <p class="control-label left" ng-show="priceListView">{{priceList.priceNum}}</p> 
                                                          <input type="hidden"  id="materielSerial" ng-model="priceList.materielSerial"  /><!--  存放物料流水号-->
                                                    </div>
                                            </div>
										</div>
                                                            <div class="col-md-6">
                                                                <div class="form-group form-md-line-input">
                                                                <label class="col-md-4 control-label" for="priceListName"><!--  <span class="required"> * </span> -->描述 :</label>
                                                                    <div class="col-md-8">
                                                                       <input type="text" class="form-control" placeholder=""  id="priceDescribe" name ="priceDescribe"  ng-hide="priceListAdd" 
												ng-model="priceList.priceDescribe" > 
                                                                       <div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="priceListView">{{priceList.priceDescribe}}</p> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-md-6">
                                                                <div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="materielNum"> <span class="required"> * </span>物料编号 :</label>
                                                    <div class="col-md-6">
                                                     <input type="text" class="form-control"   id="materielNum" name ="materielNum"  ng-hide="priceListAdd"  
												ng-model="priceList.materielNum" /> <!-- <span class="input-inline-btn">
                                                            <button class="btn default" type="button">
                                                                <i class="fa fa-search"></i>
                                                            </button>
                                                        </span> --><!-- <span class="fa fa-search"></span> -->
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="priceListView">{{priceList.materielNum}}</p> 
                                                                    </div>
                                                                    <div class="col-md-1"><span class="input-inline-btn"  ng-hide="priceListAdd"  >
                                                            <button class="btn default" type="button"   ng-click="selectMateriel()" data-target="#basicMaterielInfo" data-toggle="modal" >
                                                                <i class="fa fa-search"></i>
                                                            </button>
                                                        </span></div>
                                                                </div>
                                                            </div>
                                                            <!--/span-->
                                                            <div class="col-md-6">
                                                            <div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="priceListCategory"><!--  <span class="required"> * </span> -->物料名称 :</label>
                                                    <div class="col-md-8">
                                                                    <input type="text" class="form-control"   id="materielName" name ="materielName"  ng-hide="priceListAdd"  readonly="readonly"
												ng-model="priceList.materielName" > 
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="priceListView">{{priceList.materielName}}</p> 
                                                                </div>
                                                            </div>
                                                            <!--/span-->
                                                        </div>
                                                        </div>
                                                        <!--/row-->
                                                        <div class="row">
                                                            <div class="col-md-6">
                                                             <div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="address"> <!-- <span class="required"> * </span> -->规格型号:</label>
                                                    <div class="col-md-8">
                                                                        <input type="text" class="form-control"   id="specifications" name ="specifications"  ng-hide="priceListAdd"  readonly="readonly"
												ng-model="priceList.specifications" > 
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="priceListView">{{priceList.specifications}}</p> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <!--/span-->
                                                            <div class="col-md-6">
                                                            <div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="owner"> <!-- <span class="required"> * </span> -->单位 :</label>
                                                    <div class="col-md-8">
                                                                        <input type="text"  class="form-control" placeholder=""  id="unit" name ="unit"   ng-hide="priceListAdd"  readonly="readonly"
												ng-model="priceList.unit" > 
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="priceListView">{{priceList.unit}}</p> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <!--/span-->
                                                        </div>
                                                         <div class="row">
                                                            <div class="col-md-6">
                                                            <div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="priceType"> <span class="required"> * </span>价格类型 :</label>
                                                    <div class="col-md-8">
                                                                        <select class="form-control" id="priceType" name ="priceType" ng-model="priceList.priceType"  ng-hide="priceListAdd"  >
                                                                          <option value=""></option>
                                                                        <option value="采购价格">采购价格</option>
                                                                        <option value="销售价格">销售价格</option>
                                                                        </select>
                                                                         <div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="priceListView">{{priceList.priceType}}</p> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <!--/span-->
                                                            <div class="col-md-6"  ng-if="priceList.priceType=='销售价格'">
                                                             <div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="admin"> <span class="required"> * </span>采购商 :</label>
                                                    <div class="col-md-8">
                                                    <div class="form-group">
                                                    <div class="btn-group bootstrap-select bs-select form-control dropup"><button type="button" class="btn dropdown-toggle btn-default" data-toggle="dropdown" role="button" title="Afghanistan" aria-expanded="false"><span class="filter-option pull-left">Afghanistan</span>&nbsp;<span class="bs-caret"><span class="caret"></span></span></button><div class="dropdown-menu open" role="combobox" style="max-height: 316px; overflow: hidden;"><div class="bs-searchbox"><input type="text" class="form-control" autocomplete="off" role="textbox" aria-label="Search"></div><ul class="dropdown-menu inner" role="listbox" aria-expanded="false" style="max-height: 272px; overflow-y: auto;"><li data-original-index="0" class="selected active"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="true"><span class="text">Afghanistan</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="1"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Albania</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="2"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Algeria</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="3"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">American Samoa</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="4"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Andorra</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="5"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Angola</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="6"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Anguilla</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="7"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Argentina</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="8"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Armenia</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="9"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Aruba</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="10"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Australia</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="11"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Austria</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="12"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Azerbaijan</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="13"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Bahamas</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="14"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Bahrain</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="15"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Bangladesh</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="16"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Barbados</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="17"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Belarus</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="18"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Belgium</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="19"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Belize</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="20"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Benin</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="21"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Bermuda</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="22"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Bhutan</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="23"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Bolivia</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="24"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Bosnia and Herzegowina</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="25"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Botswana</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="26"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Bouvet Island</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="27"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Brazil</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="28"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">British Indian Ocean Territory</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="29"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Brunei Darussalam</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="30"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Bulgaria</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="31"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Burkina Faso</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="32"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Burundi</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="33"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Cambodia</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="34"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Cameroon</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="35"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Canada</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="36"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Cape Verde</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="37"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Cayman Islands</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="38"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Central African Republic</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="39"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Chad</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="40"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Chile</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="41"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">China</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="42"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Christmas Island</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="43"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Cocos (Keeling) Islands</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="44"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Colombia</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="45"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Comoros</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="46"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Congo</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="47"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Congo, the Democratic Republic of the</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="48"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Cook Islands</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="49"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Costa Rica</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="50"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Cote d'Ivoire</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="51"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Croatia (Hrvatska)</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="52"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Cuba</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="53"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Cyprus</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="54"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Czech Republic</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="55"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Denmark</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="56"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Djibouti</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="57"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Dominica</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="58"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Dominican Republic</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="59"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Ecuador</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="60"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Egypt</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="61"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">El Salvador</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="62"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Equatorial Guinea</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="63"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Eritrea</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="64"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Estonia</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="65"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Ethiopia</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="66"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Falkland Islands (Malvinas)</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="67"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Faroe Islands</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="68"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Fiji</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="69"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Finland</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="70"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">France</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="71"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">French Guiana</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="72"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">French Polynesia</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="73"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">French Southern Territories</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="74"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Gabon</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="75"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Gambia</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="76"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Georgia</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="77"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Germany</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="78"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Ghana</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="79"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Gibraltar</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="80"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Greece</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="81"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Greenland</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="82"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Grenada</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="83"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Guadeloupe</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="84"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Guam</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="85"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Guatemala</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="86"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Guinea</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="87"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Guinea-Bissau</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="88"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Guyana</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="89"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Haiti</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="90"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Heard and Mc Donald Islands</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="91"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Holy See (Vatican City State)</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="92"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Honduras</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="93"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Hong Kong</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="94"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Hungary</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="95"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Iceland</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="96"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">India</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="97"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Indonesia</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="98"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Iran (Islamic Republic of)</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="99"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Iraq</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="100"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Ireland</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="101"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Israel</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="102"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Italy</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="103"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Jamaica</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="104"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Japan</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="105"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Jordan</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="106"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Kazakhstan</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="107"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Kenya</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="108"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Kiribati</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="109"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Korea, Democratic People's Republic of</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="110"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Korea, Republic of</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="111"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Kuwait</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="112"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Kyrgyzstan</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="113"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Lao People's Democratic Republic</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="114"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Latvia</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="115"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Lebanon</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="116"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Lesotho</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="117"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Liberia</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="118"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Libyan Arab Jamahiriya</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="119"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Liechtenstein</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="120"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Lithuania</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="121"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Luxembourg</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="122"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Macau</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="123"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Macedonia, The Former Yugoslav Republic of</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="124"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Madagascar</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="125"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Malawi</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="126"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Malaysia</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="127"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Maldives</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="128"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Mali</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="129"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Malta</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="130"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Marshall Islands</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="131"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Martinique</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="132"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Mauritania</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="133"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Mauritius</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="134"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Mayotte</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="135"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Mexico</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="136"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Micronesia, Federated States of</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="137"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Moldova, Republic of</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="138"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Monaco</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="139"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Mongolia</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="140"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Montserrat</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="141"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Morocco</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="142"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Mozambique</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="143"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Myanmar</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="144"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Namibia</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="145"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Nauru</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="146"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Nepal</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="147"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Netherlands</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="148"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Netherlands Antilles</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="149"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">New Caledonia</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="150"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">New Zealand</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="151"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Nicaragua</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="152"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Niger</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="153"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Nigeria</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="154"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Niue</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="155"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Norfolk Island</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="156"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Northern Mariana Islands</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="157"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Norway</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="158"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Oman</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="159"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Pakistan</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="160"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Palau</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="161"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Panama</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="162"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Papua New Guinea</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="163"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Paraguay</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="164"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Peru</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="165"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Philippines</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="166"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Pitcairn</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="167"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Poland</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="168"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Portugal</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="169"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Puerto Rico</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="170"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Qatar</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="171"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Reunion</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="172"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Romania</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="173"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Russian Federation</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="174"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Rwanda</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="175"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Saint Kitts and Nevis</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="176"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Saint LUCIA</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="177"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Saint Vincent and the Grenadines</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="178"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Samoa</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="179"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">San Marino</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="180"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Sao Tome and Principe</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="181"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Saudi Arabia</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="182"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Senegal</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="183"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Seychelles</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="184"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Sierra Leone</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="185"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Singapore</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="186"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Slovakia (Slovak Republic)</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="187"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Slovenia</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="188"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Solomon Islands</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="189"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Somalia</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="190"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">South Africa</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="191"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">South Georgia and the South Sandwich Islands</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="192"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Spain</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="193"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Sri Lanka</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="194"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">St. Helena</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="195"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">St. Pierre and Miquelon</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="196"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Sudan</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="197"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Suriname</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="198"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Svalbard and Jan Mayen Islands</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="199"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Swaziland</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="200"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Sweden</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="201"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Switzerland</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="202"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Syrian Arab Republic</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="203"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Taiwan, Province of China</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="204"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Tajikistan</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="205"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Tanzania, United Republic of</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="206"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Thailand</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="207"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Togo</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="208"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Tokelau</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="209"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Tonga</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="210"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Trinidad and Tobago</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="211"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Tunisia</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="212"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Turkey</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="213"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Turkmenistan</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="214"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Turks and Caicos Islands</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="215"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Tuvalu</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="216"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Uganda</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="217"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Ukraine</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="218"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">United Arab Emirates</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="219"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">United Kingdom</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="220"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">United States</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="221"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">United States Minor Outlying Islands</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="222"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Uruguay</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="223"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Uzbekistan</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="224"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Vanuatu</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="225"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Venezuela</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="226"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Viet Nam</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="227"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Virgin Islands (British)</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="228"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Virgin Islands (U.S.)</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="229"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Wallis and Futuna Islands</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="230"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Western Sahara</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="231"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Yemen</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="232"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Zambia</span><span class="fa fa-check check-mark"></span></a></li><li data-original-index="233"><a tabindex="0" class="" style="" data-tokens="null" role="option" aria-disabled="false" aria-selected="false"><span class="text">Zimbabwe</span><span class="fa fa-check check-mark"></span></a></li></ul></div><select class="bs-select form-control" data-live-search="true" data-size="8" tabindex="-98">
                                                        <option value="AF">Afghanistan</option>
                                                        
                                                    </select></div>
                                            </div>
                                                    <!-- <select class="bs-select form-control" data-live-search="true" data-size=""   id="buyComId" name ="buyComId"  ng-hide="priceListAdd"   ng-model="priceList.buyComId">
                                                        <option value="AF">Afghanistan</option>
                                                    </select> -->
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="priceListView">{{priceList.buyComName}}</p>  
                                                                </div>
                                                            </div>
                                                            <!--/span-->
                                                        </div>
                                                        <div class="col-md-6"  ng-if="priceList.priceType=='采购价格'"">
                                                             <div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="admin"> <span class="required"> * </span>供应商 :</label>
                                                    <div class="col-md-8">
                                                     <select class="bs-select form-control" data-live-search="true" data-size=""   id="supplyComId" name ="supplyComId"  ng-hide="priceListAdd"   ng-model="priceList.supplyComId">
                                                        <option value="AF">Afghanistan</option>
                                                    </select>
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="priceListView">{{priceList.supplyComName}}</p>  
                                                                </div>
                                                            </div>
                                                            <!--/span-->
                                                        </div>
                                                        </div>
                                                         <div class="row">
                                                            <div class="col-md-6">
                                                            <div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="currency"> <span class="required"> * </span>币种 :</label>
                                                    <div class="col-md-8">
                                                                        <select class="form-control" id="currency" name ="currency" ng-model="priceList.currency"  ng-hide="priceListAdd"  >
                                                                          <option value=""></option>
                                                                        <option value="RMB">RMB</option>
                                                                        <option value="USD">USD</option>
                                                                        </select>
                                                                         <div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="priceListView">{{priceList.currency}}</p> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <!--/span-->
                                                            <div class="col-md-6">
                                                              <div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="rate"> <span class="required"> * </span>税率 :</label>
                                                    <div class="col-md-8">
                                                                       <input type="text"  class="form-control" placeholder=""  id="rate" name ="rate"  ng-hide="priceListAdd" 
												ng-model="priceList.rate">
                                                                         <div class="form-control-focus"></div>
                                                                          <span class="help-block">请输入百分比数</span>
                                                                        <p class="control-label left" ng-show="priceListView">{{priceList.rate}}</p> 
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <!--/span-->
                                                        </div>
                                                         <div class="row">
                                                            <div class="col-md-6">
                                                             <div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="priceList"> <span class="required"> * </span>单价 :</label>
                                                    <div class="col-md-8">
                                                                        <input type="text"  class="form-control" placeholder=""  id="price" name ="price"  ng-hide="priceListAdd" 
												ng-model="priceList.priceList">
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="priceListView">{{priceList.price}}</p>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                           <div class="col-md-6">
                                                           <div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="inclusivepriceList"> <!-- <span class="required"> * </span> -->含税价格 :</label>
                                                    <div class="col-md-8">
                                                                        <input type="text"   class="form-control" placeholder=""  id="inclusiveprice" name ="inclusiveprice"  ng-hide="priceListAdd" 
												ng-model="priceList.inclusivepriceList" >
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="priceListView">{{priceList.inclusiveprice}}</p>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-md-6">
                                                             <div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="toppriceList"> <!-- <span class="required"> * </span> -->最高限价 :</label>
                                                    <div class="col-md-8">
                                                                        <input type="text"  class="form-control" placeholder=""  id="topprice" name ="topprice"  ng-hide="priceListAdd" 
												ng-model="priceList.topprice">
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="priceListView">{{priceList.topprice}}</p>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                           <div class="col-md-6">
                                                           <div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="floorpriceList"> <!-- <span class="required"> * </span> -->最底限价 :</label>
                                                    <div class="col-md-8">
                                                                        <input type="text"   class="form-control" placeholder=""  id="floorprice" name ="floorprice"  ng-hide="priceListAdd" 
												ng-model="priceList.floorprice" >
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="priceListView">{{priceList.floorprice}}</p>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-md-6">
                                                             <div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="isLadderpriceList"> <!-- <span class="required"> * </span> -->是否阶梯单价 :</label>
                                                    <div class="col-md-8">
                                                                         <div class="icheck-inline">
                                                                            <label>
                                                                                <input type="checkbox"    ng-hide="priceListAdd"   ng-model="priceList.isLadderprice"    ng-click="showOrHide()" name ="isLadderprice"  class="icheck">是 </label>
                                                                            <label>
                                                                        </div>
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="priceListView">{{priceList.isLadderprice}}</p>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                           <div class="col-md-6">
                                                           <div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="ladderType"> <!-- <span class="required"> * </span> -->阶梯类型 :</label>
                                                    <div class="col-md-8">
                                                                       <select class="form-control" id="ladderType" name ="ladderType" ng-model="priceList.ladderType"  ng-hide="priceListAdd"  >
                                                                          <option value=""></option>
                                                                        <option value="单笔阶梯单价">单笔阶梯单价</option>
                                                                        <option value="累计阶梯单价">累计阶梯单价</option>
                                                                        </select>
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="priceListView">{{priceList.ladderType}}</p>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="row"  ng-show="isChecked"><!--  -->
                                                         <div class="portlet-title">
                            <div class="actions"  style="float: right;">
                             
                                <button   class="btn blue btn-outline  btn-sm " ng-click="editladderprice()">
                                            <i class="fa fa-edit"></i> 编辑 </button>
                                <button   class="btn red  btn-outline  btn-sm " ng-click="cancelEditLadderprice()">
                                            <i class="fa fa-undo"></i> 取消 </button>
                                <button   class="btn blue btn-outline  btn-sm " ng-click="saveladderprice()">
                                            <i class="fa fa-save"></i> 保存 </button>
                            </div>
                        </div></br></br>
                                                               <div class="portlet-body form">
                            <form  id="qualificationForm" class="page-repeater form-horizontal">
								<div class="form-body" data-repeater-list="group-a"  >
									<div  class="row" data-repeater-item  ng-repeat="ladderprice in ladderprices  track by $index" repeat-done="repeatDone()"  > 
										<div class="col-md-3">
											<div class="form-group">
											<div class="col-md-5"> {{$index+1}}  :数量范围</div>
												<div class="col-md-7 input-icon right">
													<input  ng-hide="ladderpriceAdd" type="text" ng-model="ladderprice.countStart" name="countStart"
														class="form-control" placeholder="起始数量"><span
														class="help-block"></span>
														<label   ng-show="ladderpriceListView"  class="c_edit" >{{ladderprice.countStart}}</label>
												</div>
											</div>
										</div>
						
										<!--/span-->
										<div class="col-md-2">
											<div class="form-group">
												<div class="col-md-12">
													<input  ng-hide="ladderpriceAdd" type="text" ng-model="ladderprice.countEnd" name="countEnd"
														class="form-control" placeholder="截止数量"> <span
														class="help-block"></span>
														<label   ng-show="ladderpriceView"  class="c_edit" >{{ladderprice.countEnd}}</label>
												</div>
											</div>
										</div>
										<div class="col-md-3">
											<div class="form-group">
											<div class="col-md-4"> 价格 :</div>
												<div class="col-md-8 input-icon right">
													<input  ng-hide="ladderpriceAdd" type="text" ng-model="ladderprice.countStart" name="countStart"
														class="form-control" placeholder="价格"><span
														class="help-block"></span>
														<label   ng-show="ladderpriceView"  class="c_edit" >{{ladderprice.countStart}}</label>
												</div>
											</div>
										</div>
										<div class="col-md-3">
											<div class="form-group">
											<div class="col-md-5"> 含税价格 :</div>
												<div class="col-md-7 input-icon right">
													<input  ng-hide="ladderpriceAdd" type="text" ng-model="ladderprice.countStart" name="countStart"
														class="form-control" placeholder="含税价格"><span
														class="help-block"></span>
														<label   ng-show="ladderpriceView"  class="c_edit" >{{ladderprice.countStart}}</label>
												</div>
											</div>
										</div>
										<div class="col-md-1">
											<div class="form-group">
												<div class="col-md-12">
													 <a href="javascript:;"  class="btn red btn-sm" ng-hide="ladderpriceAdd" ng-click="deleteRepeat()">
                                            			<i class="fa fa-close"></i> 
                                     				</a>
												</div>
											</div>
										</div>
										<!--/span--> 
									</div>
									<!-- /row -->
								</div>
								<div class="form-actions right" ng-hide="ladderpriceAdd">
									<a href="javascript:;" data-repeater-create class="btn blue btn-sm" ng-click="addRepeat()" >
                                            <i class="fa fa-plus"></i> 增加
                                     </a>
                                </div>
							</form>
         				</div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-md-6">
                                                             <div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="priceEffectiveDate"> <span class="required"> * </span>价格生效期 :</label>
                                                    <div class="col-md-8">
                                                                        <input class="form-control form-control-inline date-picker"     data-date-format="yyyy-mm-dd"   id="priceEffectiveDate"  name="priceEffectiveDate"    ng-model="priceList.priceEffectiveDate" size="16" type="text" />
                                                     <div class="form-control-focus"> </div>
                                      <!-- <span class="help-block">请选择价格生效期</span>  -->
                                                                        <p class="control-label left" ng-show="priceListView">{{priceList.priceEffectiveDate}}</p>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                           <div class="col-md-6">
                                                           <div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="priceExpirationDate"> <span class="required"> * </span>价格失效期 :</label>
                                                    <div class="col-md-8">
                                                     <input class="form-control form-control-inline  date-picker"  data-date-format="yyyy-mm-dd"    id="priceExpirationDate"  name="priceExpirationDate"   ng-model="priceList.priceExpirationDate"  size="16" type="text" />
                                                     <div class="form-control-focus"> </div>
                                      <!-- <span class="help-block">请选择价格失效期</span> -->
                                      <p class="control-label left" ng-show="priceListView">{{priceList.priceExpirationDate}}</p>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-md-6">
                                                             <div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="tel"><!--  <span class="required"> * </span> -->备注 :</label>
                                                    <div class="col-md-8">
                                                                        <input type="text"  class="form-control" placeholder=""  id="remark" name ="remark"  ng-hide="priceListAdd" 
												ng-model="priceList.remark">
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="priceListView">{{priceList.remark}}</p>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                           <div class="col-md-6">
                                                           <div class="form-group form-md-line-input">
                                                    <label class="col-md-4 control-label" for="file"> <!-- <span class="required"> * </span> -->附件 :</label>
                                                    <div class="col-md-8">
                                                        <div class="form-group">
                                                <div class="col-md-1">
                                                    <div class="fileinput fileinput-new" data-provides="fileinput">
                                                        <div class="input-group input-small">
                                                            <div class="form-control uneditable-input input-fixed input-medium" data-trigger="fileinput">
                                                                <i class="fa fa-file fileinput-exists"></i>&nbsp;
                                                                <span class="fileinput-filename"  > </span>
                                                            </div>
                                                            <span class="input-group-addon btn default btn-file">
                                                                <span class="fileinput-new" > 上传文件 </span>
                                                                <span class="fileinput-exists" > 修改 </span>
                                                                <input type="file" name="..."> </span>
                                                            <a href="javascript:;" class="input-group-addon btn red fileinput-exists" data-dismiss="fileinput" > 删除 </a>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
												<div class="form-control-focus"> </div>
                                                                        <p class="control-label left" ng-show="priceListView">{{priceList.file}}</p>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
								</div>
							</form>
         				</div>

			
                       

				
                    
				
				
				</div>
				
        </div>
        <!-- END EXAMPLE TABLE PORTLET-->
    </div>
</div>
<jsp:include  page="../materiel/selectMateriel.jsp"/>
<!-- END MAIN CONTENT -->
<!-- BEGIN MAIN JS -->
 <script>

</script> 
<!-- END MAIN JS -->