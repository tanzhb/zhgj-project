<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="col-md-12">
      <div class="portlet light ">
          <div class="portlet-title">
              <div class="caption">
                  <span class="caption-subject font-purple-soft bold uppercase">企业信息</span>
              </div>
              <div class="actions">
                  <a class="btn btn-circle btn-icon-only btn-default" href="javascript:;">
                      <i class="icon-close"></i>
                  </a>
              </div>
          </div>
          <div class="portlet-body">
              <ul class="nav nav-tabs">
                  <li class="active">
                      <a data-target="#tab_1_1" data-toggle="tab">基本信息</a>
                  </li>
                  <li>
                      <a data-target="#tab_1_2" data-toggle="tab">企业资质</a>
                  </li>
                  <li>
                      <a data-target="#tab_1_2" data-toggle="tab">联系人</a>
                  </li>
              </ul>
              <div class="tab-content">
                  <div class="tab-pane fade active in" id="tab_1_1">
                        <div class="portlet light">
                              <div class="portlet-body form">
                                  <!-- BEGIN FORM-->
                                  <form class="form-horizontal" role="form">
                                      <div class="form-body">
                                          <div class="row">
                                              <div class="col-md-6">
                                                  <div class="form-group">
                                                      <label class="control-label col-md-5">企业编号：</label>
                                                      <div class="col-md-7">
                                                          <p class="form-control-static" ng-model ="company.comNum">{{company.comNum}}</p>
                                                      </div>
                                                  </div>
                                              </div>
                                              <!--/span-->
                                              <div class="col-md-6">
                                                  <div class="form-group">
                                                      <label class="control-label col-md-5">企业名称：</label>
                                                      <div class="col-md-7">
                                                          <p class="form-control-static"> Nilson </p>
                                                      </div>
                                                  </div>
                                              </div>
                                              <!--/span-->
                                          </div>
                                          <!--/row-->
                                          <div class="row">
                                              <div class="col-md-6">
                                                  <div class="form-group">
                                                      <label class="control-label col-md-5">企业类型：</label>
                                                      <div class="col-md-7">
                                                          <p class="form-control-static"> Male </p>
                                                      </div>
                                                  </div>
                                              </div>
                                              <!--/span-->
                                              <div class="col-md-6">
                                                  <div class="form-group">
                                                      <label class="control-label col-md-5">企业简称：</label>
                                                      <div class="col-md-7">
                                                          <p class="form-control-static"> 20.01.1984 </p>
                                                      </div>
                                                  </div>
                                              </div>
                                              <!--/span-->
                                          </div>
                                          <!--/row-->
                                          <div class="row">
                                              <div class="col-md-6">
                                                  <div class="form-group">
                                                      <label class="control-label col-md-5">营业性质：</label>
                                                      <div class="col-md-7">
                                                          <p class="form-control-static"> Category1 </p>
                                                      </div>
                                                  </div>
                                              </div>
                                              <!--/span-->
                                              <div class="col-md-6">
                                                  <div class="form-group">
                                                      <label class="control-label col-md-5">企业性质：</label>
                                                      <div class="col-md-7">
                                                          <p class="form-control-static"> Free </p>
                                                      </div>
                                                  </div>
                                              </div>
                                              <!--/span-->
                                          </div>
                                          <!--/row-->
                                          <div class="row">
                                              <div class="col-md-6">
                                                  <div class="form-group">
                                                      <label class="control-label col-md-5">经营类型：</label>
                                                      <div class="col-md-7">
                                                          <p class="form-control-static"> Category1 </p>
                                                      </div>
                                                  </div>
                                              </div>
                                              <!--/span-->
                                              <div class="col-md-6">
                                                  <div class="form-group">
                                                      <label class="control-label col-md-5">注册资金：</label>
                                                      <div class="col-md-7">
                                                          <p class="form-control-static"> Free </p>
                                                      </div>
                                                  </div>
                                              </div>
                                              <!--/span-->
                                          </div>
                                          <!--/row-->
                                          <div class="row">
                                              <div class="col-md-6">
                                                  <div class="form-group">
                                                      <label class="control-label col-md-5">企业法人姓名：</label>
                                                      <div class="col-md-7">
                                                          <p class="form-control-static"> Category1 </p>
                                                      </div>
                                                  </div>
                                              </div>
                                              <!--/span-->
                                              <div class="col-md-6">
                                                  <div class="form-group">
                                                      <label class="control-label col-md-5">注册地址：</label>
                                                      <div class="col-md-7">
                                                          <p class="form-control-static"> Free </p>
                                                      </div>
                                                  </div>
                                              </div>
                                              <!--/span-->
                                          </div>
                                          <!--/row-->
                                          <div class="row">
                                              <div class="col-md-6">
                                                  <div class="form-group">
                                                      <label class="control-label col-md-5">纳税人识别号：</label>
                                                      <div class="col-md-7">
                                                          <p class="form-control-static"> Category1 </p>
                                                      </div>
                                                  </div>
                                              </div>
                                              <!--/span-->
                                              <div class="col-md-6">
                                                  <div class="form-group">
                                                      <label class="control-label col-md-5">联系电话：</label>
                                                      <div class="col-md-7">
                                                          <p class="form-control-static"> Free </p>
                                                      </div>
                                                  </div>
                                              </div>
                                              <!--/span-->
                                          </div>
                                          <!--/row-->
                                          <div class="row">
                                              <div class="col-md-6">
                                                  <div class="form-group">
                                                      <label class="control-label col-md-5">维护人员：</label>
                                                      <div class="col-md-7">
                                                          <p class="form-control-static"> Category1 </p>
                                                      </div>
                                                  </div>
                                              </div>
                                              <!--/span-->
                                              <div class="col-md-6">
                                                  <div class="form-group">
                                                      <label class="control-label col-md-5">备注：</label>
                                                      <div class="col-md-7">
                                                          <p class="form-control-static"> Free </p>
                                                      </div>
                                                  </div>
                                              </div>
                                              <!--/span-->
                                          </div>
                                          <!--/row-->
                                      </div>
                                  </form>
                                  <!-- END FORM-->
                              </div>
                          </div>
                  </div>
                  <div class="tab-pane fade" id="tab_1_2">
                      <p> Food truck fixie locavore, accusamus mcsweeney's marfa nulla single-origin coffee squid. Exercitation +1 labore velit, blog sartorial PBR leggings next level wes anderson artisan four loko farm-to-table craft
                          beer twee. Qui photo booth letterpress, commodo enim craft beer mlkshk aliquip jean shorts ullamco ad vinyl cillum PBR. Homo nostrud organic, assumenda labore aesthetic magna delectus mollit. Keytar helvetica
                          VHS salvia yr, vero magna velit sapiente labore stumptown. Vegan fanny pack odio cillum wes anderson 8-bit, sustainable jean shorts beard ut DIY ethical culpa terry richardson biodiesel. Art party scenester
                          stumptown, tumblr butcher vero sint qui sapiente accusamus tattooed echo park. </p>
                  </div>
                  <div class="tab-pane fade" id="tab_1_3">
                      <p> Etsy mixtape wayfarers, ethical wes anderson tofu before they sold out mcsweeney's organic lomo retro fanny pack lo-fi farm-to-table readymade. Messenger bag gentrify pitchfork tattooed craft beer, iphone skateboard
                          locavore carles etsy salvia banksy hoodie helvetica. DIY synth PBR banksy irony. Leggings gentrify squid 8-bit cred pitchfork. Williamsburg banh mi whatever gluten-free, carles pitchfork biodiesel fixie
                          etsy retro mlkshk vice blog. Scenester cred you probably haven't heard of them, vinyl craft beer blog stumptown. Pitchfork sustainable tofu synth chambray yr. </p>
                  </div>
              </div>
          </div>
      </div>
</div>