<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>

        	<div class="row">
				<div class="col-md-12">
				        <div class="portlet light">
				            <div class="portlet-title">
							<div class="caption">
								<i class="fa fa-globe font-green"></i>
								<span class="caption-subject font-green bold uppercase">操作日志</span>
							</div>
							</div>
							<div class="portlet-title">
							<div class="caption">
                            	<div class="input-group input-large date-picker input-daterange" data-date-format="yyyy-mm-dd">
                                    <input type="text" class="form-control" ng-model="startTime" name="from">
                                    <span class="input-group-addon"> to </span>
                                    <input type="text" class="form-control" ng-model="endTime" name="to"> 
                              </div>
                            </div>
                             <div class="caption" style="margin-left: 20px;">
                            	    <button  class="btn blue  btn-outline  btn-sm " ng-click="search()">
                                    	<i class="fa fa-search"></i> 查询 </button>
                        			<button  class="btn red  btn-outline  btn-sm " ng-click="resetSearchForm()">
                                    	<i class="fa fa-undo"></i> 重置 </button>
                           	 </div>
							
				            </div>
				            <div class="portlet-body">
				                <table class="table table-striped table-bordered table-hover table-checkable order-column" style="text-align: center" id="sample_2">
				                    <thead>
				                        <tr>
				                            <th style="text-align: center"> 操作 </th>
				                            <th style="text-align: center"> 终端IP </th>
				                            <th style="text-align: center"> 时间  </th>
				                        </tr>
				                    </thead>
				                    <tbody>
				                    </tbody>
				                </table>
				            </div>
				        </div>
			        <!-- END EXAMPLE TABLE PORTLET-->
			    </div>
			 </div>


