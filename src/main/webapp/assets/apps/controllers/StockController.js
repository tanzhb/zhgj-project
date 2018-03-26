/* Setup blank page controller */
angular
		.module('MetronicApp')
		.controller(
				'StockController',
				[
						'$rootScope',
						'$scope',
						'$state',
						'$compile',
						'$http',
						'$location',
						'$stateParams',
						'settings',
						'orderService',
						'StockService',
						function($rootScope, $scope, $state, $compile,$http,$location,$stateParams,settings,
								orderService,StockService) {
							$scope
									.$on(
											'$viewContentLoaded',
											function() {
												// initialize core components
												 handle = new pageHandle();
												App.initAjax();
												$scope.manageType=$stateParams.stockSerialNum;
												if($location.path()=="/addOrEditStock"){
													if($stateParams.stockSerialNum.length>7){//库存编辑页面
														getStockInfo($stateParams.stockSerialNum);
													}else {
														 $rootScope.setNumCode("IV",function(newCode){//
															 $scope.stock={};
										    		 			$scope.stock.stockNum= newCode;//自建.代管库存编号
										    		 			$scope.stock.serviceParty= "中航能科（上海）能源科技有限公司";//自建.代管库存编号
										    		 		});
													}
													initSuppliers();//初始化物权方选择框
										 		}else if($location.path()=="/stockView"){
										 			debugger;
										 			getStockDetailInfo($stateParams.stockSerialNum);//查看库区详情页面
										 			loadZkTable($stateParams.stockSerialNum,"sample_inm");//在库数量
										 			loadStockInTable($stateParams.stockSerialNum,"sample_stockinview");//入库记录
										 			loadStockOutTable($stateParams.stockSerialNum,"sample_stockoutview");//出库记录
										 		//	loadPdTable();//盘点记录
									 		}else{
										 			loadStockzijianTable();
										 			loadStockdaiguanTable();
										 			if($scope.manageType==undefined||$scope.manageType=='zijian'){
										 				$("#daiguan").removeClass("active");
										 				$("#zijian").addClass("active");
										 				$("#tab_daiguan").removeClass("active");
										 				$("#tab_zijian").addClass("active");
										 			}else{
										 				$("#daiguan").addClass("active");
										 				$("#zijian").removeClass("active");
										 				$("#tab_daiguan").addClass("active");
										 				$("#tab_zijian").removeClass("active")
										 			}
										 		}
												
												selectMaterielStock();//选择物料表格初始化
												if($scope.stock==undefined){
													$scope.stock={};
													$scope.stock.serviceParty="中航能科（上海）能源科技有限公司";
												}
												// set default layout mode
												$rootScope.settings.layout.pageContentWhite = true;
												$rootScope.settings.layout.pageBodySolid = false;
												$rootScope.settings.layout.pageSidebarClosed = false;
											});
							
							//初始化toastr开始
							toastr.options = {
									"closeButton" : true,
									"debug" : false,
									"positionClass" : "toast-top-center",
									"onclick" : null,
									"showDuration" : "1000",
									"hideDuration" : "1000",
									"timeOut" : "5000",
									"extendedTimeOut" : "1000",
									"showEasing" : "swing",
									"hideEasing" : "linear",
									"showMethod" : "fadeIn",
									"hideMethod" : "fadeOut"
								}
							//初始化toastr结束

							// 构建datatables开始***************************************
							var tableAjaxUrl ;
							 var tablezijian,tabledaiguan,table;
			function loadStockzijianTable(){
							var a = 0;
							tableAjaxUrl= "rest/stock/getStockList?manageType=1";
									tablezijian = $("#sample_zijian")
									.DataTable(
											{
												language : {
													aria : {
														sortAscending : ": 以升序排列此列",
														sortDescending : ": 以降序排列此列"
													},
													emptyTable : "空表",
													info : "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
													infoEmpty : "没有数据",
													infoFiltered : "(从 _MAX_ 条数据中检索)",
													lengthMenu : "每页显示 _MENU_ 条数据",
													search : "查询:",
													zeroRecords : "抱歉， 没有找到！",
													paginate : {
														"sFirst" : "首页",
														"sPrevious" : "前一页",
														"sNext" : "后一页",
														"sLast" : "尾页"
													}
												},
												/*fixedHeader : {// 固定表头、表底
													header : !0,
													footer : !0,
													headerOffset : a
												},*/
												// select: true,行多选
												order : [ [ 1, "asc" ] ],// 默认排序列及排序方式
												bRetrieve : true,
												// searching: true,//是否过滤检索
												// ordering: true,//是否排序
												lengthMenu : [
														[ 5, 10, 15, 30, -1 ],
														[ 5, 10, 15, 30, "All" ] ],
												pageLength : 10,// 每页显示数量
												processing : true,// loading等待框
												// serverSide: true,
												ajax : tableAjaxUrl,// 加载数据中库存表数据

												"aoColumns" : [
													{
													mData : 'serialNum'/*,
						                            	  mRender : function(
																	data,
																	type,
																	row,
																	meta) {
																return "<label class='mt-checkbox mt-checkbox-single mt-checkbox-outline'>" +
																		"<input type='checkbox' class='checkboxes' value='"+data+"'/>" +
																		"<span></span></label>";
															}*/
													},
													 {
														mData : 'stockNum'
													},  {
														mData : 'materielNum'
													},{
														mData : 'materielName'
													}, {
														mData : 'specifications'
													},{
														mData : 'belongWarehouseNameZijian'//belongWarehouseNumZijian 所属仓库数
													}, {
														mData : 'currentAmount'
													}, {
														mData : 'firstInDateZijian'//averrageWhAge
													}, {
														mData : 'preSaleAmount'
													},{
														mData : 'onRoadAmount'
													}, {
														mData : 'canSaleAmount'
													},{
														mData : 'riskGrade'
													}, {
														mData : 'status'
													}
													],
												'aoColumnDefs' : [ {
													'targets' : 0,
													'searchable' : false,
													'orderable' : false,
													'className' : 'dt-body-center',
													'render' : function(data,
															type, full, meta) {
														/*return '<input type="checkbox" id="'+data+'" name="id[]" value="'
																+ $('<div/>')
																		.text(
																				data)
																		.html()
																		+ '" data-check="false" ng-click="showStockRecord(\''+full.serialNum+'\')" >';*/
														return '<label class="mt-checkbox mt-checkbox-single mt-checkbox-outline">'+
					                                     '<input type="checkbox" data-check="false" class="checkboxes" ng-click="showStockRecord(\''+full.serialNum+'\')" id="'+data+'" value="'+data+'" data-set="#sample_zijian .checkboxes" />'+
					                                     '<span></span></label>';
													},"createdCell": function (td, cellData, rowData, full, col) {
														 $compile(td)($scope);
												    }
												},{
													'targets' : 1,
													'render' : function(data,
															type, row, meta) {
														return '<a   ng-click="showStockInfo(\''+row.serialNum+'\')">'+data+'</a>';
														//return data;
													},"createdCell": function (td, cellData, rowData, row, col) {
														 $compile(td)($scope);
												    }
												},{
													'targets' : 5,
													'render' : function(data,
															type, row, meta) {
				 	    								if(data==''||data==null){
				 	    									return "";
				 	    								}else if(count(data,',')<=1){
				 	    									return  data ;
				 	    								}else if(count(data,',')>1){
				 	    									return  '<span title="仓库名称:'+data+'">'+data.substring(0,data.indexOf(','))+".........."+'</span>' ;
				 	    								}
													},"createdCell": function (td, cellData, rowData, row, col) {
														 $compile(td)($scope);
												    }
												},{
													'targets' : 7,
													'render' : function(data,
															type, row, meta) {
				 	    								if(data==''||data==null){
				 	    									return "";
				 	    								}else{
				 	    									/*if(row.serialNum=="5bba825cb17144d4874f426b0cd3326e"){
				 	    										debugger;
				 	    										return daysBetween(timeStamp2ShortString(Date.parse(new Date())),data);
				 	    									}*/
				 	    									return daysBetween(timeStamp2ShortString(Date.parse(new Date())),data.substring(0,10));
				 	    								}
														
													}
												},{
													'targets' : 11,
													'render' : function(data,
															type, row, meta) {
														var statusIcon='';//状态
				 	    								if(row.status==0){
				 	    									statusIcon = '<span class="label label-sm label-success"  >缺料</span> '
				 	    								}else if(row.status==1){
				 	    									statusIcon = '<span class="label label-sm label-success">报警</span> '
				 	    								}else if(row.status==2){
				 	    									statusIcon = '<span class="label label-sm label-success">正常</span> '
				 	    								}
				 	    								return statusIcon ;
														
													}
												}  ],

											})
							// 构建datatables结束***************************************
											$("#sample_zijian").find(".group-checkable").change(function() {
										        var e = jQuery(this).attr("data-set"),
										        t = jQuery(this).is(":checked");
										        jQuery(e).each(function() {
										            t ? ($(this).prop("checked", !0), $(this).parents("tr").addClass("active")) : ($(this).prop("checked", !1), $(this).parents("tr").removeClass("active"))
										        })
										    }),
										    $("#sample_zijian").on("change", "tbody tr .checkboxes",
										    function() {
										        $(this).parents("tr").toggleClass("active")
										    })
										   return tablezijian;
											}
			function loadStockdaiguanTable(){
				var a = 0;
				tableAjaxUrl= "rest/stock/getStockList?manageType=2";

								tabledaiguan = $("#sample_daiguan")
						.DataTable(
								{
									language : {
										aria : {
											sortAscending : ": 以升序排列此列",
											sortDescending : ": 以降序排列此列"
										},
										emptyTable : "空表",
										info : "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
										infoEmpty : "没有数据",
										infoFiltered : "(从 _MAX_ 条数据中检索)",
										lengthMenu : "每页显示 _MENU_ 条数据",
										search : "查询:",
										zeroRecords : "抱歉， 没有找到！",
										paginate : {
											"sFirst" : "首页",
											"sPrevious" : "前一页",
											"sNext" : "后一页",
											"sLast" : "尾页"
										}
									},
									/*fixedHeader : {// 固定表头、表底
										header : !0,
										footer : !0,
										headerOffset : a
									},*/
									// select: true,行多选
									order : [ [ 1, "asc" ] ],// 默认排序列及排序方式
									bRetrieve : true,
									// searching: true,//是否过滤检索
									// ordering: true,//是否排序
									lengthMenu : [
											[ 5, 10, 15, 30, -1 ],
											[ 5, 10, 15, 30, "All" ] ],
									pageLength : 10,// 每页显示数量
									processing : true,// loading等待框
									// serverSide: true,
									ajax : tableAjaxUrl,// 加载数据中库存表数据

									"aoColumns" : [
										{
										mData : 'serialNum'/*,
		                            	  mRender : function(
													data,
													type,
													row,
													meta) {
												return "<label class='mt-checkbox mt-checkbox-single mt-checkbox-outline'>" +
														"<input type='checkbox' class='checkboxes' value='"+data+"'/>" +
														"<span></span></label>";
											}*/
										},
										 {
											mData : 'stockNum'
										},  {
											mData : 'materielNum'
										},{
											mData : 'materielName'
										}, {
											mData : 'specifications'
										},{
											mData : 'belongWarehouseNameDaiguan'
										}, {
											mData : 'currentAmount'
										}, {
											mData : 'firstInDateDaiguan'
										}, {
											mData : 'preSaleAmount'
										},{
											mData : 'onRoadAmount'
										}, {
											mData : 'canSaleAmount'
										},{
											mData : 'riskGrade'
										}, {
											mData : 'status'
										}
										],
									'aoColumnDefs' : [ {
										'targets' : 0,
										'searchable' : false,
										'orderable' : false,
										'className' : 'dt-body-center',
										'render' : function(data,
												type, full, meta) {
											/*return '<input type="checkbox" id="'+data+'" name="id[]" value="'
													+ $('<div/>')
															.text(
																	data)
															.html()
															+ '" data-check="false"  ng-click="showStockRecord(\''+full.serialNum+'\')">';*/
											return '<label class="mt-checkbox mt-checkbox-single mt-checkbox-outline">'+
		                                     '<input type="checkbox" data-check="false" class="checkboxes" ng-click="showStockRecord(\''+full.serialNum+'\')" id="'+data+'" value="'+data+'" data-set="#sample_daiguan .checkboxes" />'+
		                                     '<span></span></label>';
										},"createdCell": function (td, cellData, rowData, full, col) {
											 $compile(td)($scope);
									    }
									},{
										'targets' : 1,
										'render' : function(data,
												type, row, meta) {
											return '<a   ng-click="showStockInfo(\''+row.serialNum+'\')">'+data+'</a>';
											//return data;
										},"createdCell": function (td, cellData, rowData, row, col) {
											 $compile(td)($scope);
									    }
									},{
										'targets' : 5,
										'render' : function(data,
												type, row, meta) {
	 	    								if(data==''||data==null){
	 	    									return "";
	 	    								}else if(count(data,',')<=1){
	 	    									return  data ;
	 	    								}else if(count(data,',')>1){
	 	    									return  '<span title="仓库名称:'+data+'">'+data.substring(0,data.indexOf(','))+".........."+'</span>' ;
	 	    								}
										},"createdCell": function (td, cellData, rowData, row, col) {
											 $compile(td)($scope);
									    }
									},{
										'targets' : 7,
										'render' : function(data,
												type, row, meta) {
	 	    								if(data==''||data==null){
	 	    									return "";
	 	    								}else{
	 	    									return daysBetween(timeStamp2ShortString(Date.parse(new Date())),data.substring(0,10));
	 	    								}
											
										}
									},{
										'targets' : 11,
										'render' : function(data,
												type, row, meta) {
											var statusIcon='';//状态
	 	    								if(row.status==0){
	 	    									statusIcon = '<span class="label label-sm label-success"  >缺料</span> '
	 	    								}else if(row.status==1){
	 	    									statusIcon = '<span class="label label-sm label-success">报警</span> '
	 	    								}else if(row.status==2){
	 	    									statusIcon = '<span class="label label-sm label-success">正常</span> '
	 	    								}
	 	    								return statusIcon ;
											
										}
									}  ],

								})
				// 构建datatables结束***************************************
								$("#sample_daiguan").find(".group-checkable").change(function() {
							        var e = jQuery(this).attr("data-set"),
							        t = jQuery(this).is(":checked");
							        jQuery(e).each(function() {
							            t ? ($(this).prop("checked", !0), $(this).parents("tr").addClass("active")) : ($(this).prop("checked", !1), $(this).parents("tr").removeClass("active"))
							        })
							    }),
							    $("#sample_daiguan").on("change", "tbody tr .checkboxes",
							    function() {
							        $(this).parents("tr").toggleClass("active")
							    })
							   return tabledaiguan;
						}
			var stockInTable,stockOutTable,stockZkTable;
			function loadStockInTable(serialNum,tableid){
				var a = 0;
				debugger;
				 if(stockInTable!=undefined){
					 stockInTable.destroy();
		 	    	 }
				 tableAjaxUrl="rest/stock/stockInList?serialNum="+serialNum
				
			
				stockInTable = $("#"+tableid)
						.DataTable(
								{
									language : {
										aria : {
											sortAscending : ": 以升序排列此列",
											sortDescending : ": 以降序排列此列"
										},
										emptyTable : "空表",
										info : "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
										infoEmpty : "没有数据",
										infoFiltered : "(从 _MAX_ 条数据中检索)",
										lengthMenu : "每页显示 _MENU_ 条数据",
										search : "查询:",
										zeroRecords : "抱歉， 没有找到！",
										paginate : {
											"sFirst" : "首页",
											"sPrevious" : "前一页",
											"sNext" : "后一页",
											"sLast" : "尾页"
										}
									},
									/*fixedHeader : {// 固定表头、表底
										header : !0,
										footer : !0,
										headerOffset : a
									},*/
									// select: true,行多选
									order : [ [ 1, "asc" ] ],// 默认排序列及排序方式
									bRetrieve : true,
									// searching: true,//是否过滤检索
									// ordering: true,//是否排序
									lengthMenu : [
											[ 5, 10, 15, 30, -1 ],
											[ 5, 10, 15, 30, "All" ] ],
									pageLength : 10,// 每页显示数量
									processing : true,// loading等待框
									// serverSide: true,
								     ajax :tableAjaxUrl,
			  		                    "aoColumns": [
			  		                                   { mData: 'stockInOutRecord.inOutNum' },
	  		                                           { mData: 'stockInOutRecord.inOutType' },
	  		                                          { mData: 'stockInOutRecord.order' },
	  		                                         /* { mData: 'batchNum' },*/
	  		                                          { mData: 'stockInOutRecord.stockDate' },
	 	  		                                      { mData: 'stockCount' },
	 	  		                                      { mData: 'warehouseName' },
		  		                                     /* { mData: 'positionNum' },*/
		  		                                       { mData: 'remark' }//备注
                                                     
			  		                            ],
			  		                   'aoColumnDefs' : [ {
											'targets' : 2,
											'render' : function(data,
													type, row, meta) {
		 	    								if(data==''||data==null){
		 	    									return "";
		 	    								}else{
		 	    									return data.orderNum;
		 	    								}
												
											}
										}/*,{
											'targets' : 7,
											'render' : function(data,
													type, row, meta) {
		 	    								if(data==''||data==null){
		 	    									return "";
		 	    								}else{
		 	    									return data.positionName;
		 	    								}
												
											}
										},{
											'targets' : 6,
											'render' : function(data,
													type, row, meta) {
		 	    								if(data==''||data==null){
		 	    									return "";
		 	    								}else{
		 	    									return data.warehouseName;
		 	    								}
												
											}
										} */],

								})
				// 构建datatables结束***************************************
							   return stockInTable;
								}
			function loadStockOutTable(serialNum,tableid){
				var a = 0;
				debugger;
				 if(stockOutTable!=undefined){
					 stockOutTable.destroy();
		 	    	 }
					App.getViewPort().width < App
					.getResponsiveBreakpoint("md") ? $(
					".page-header").hasClass(
					"page-header-fixed-mobile")
					&& (a = $(".page-header").outerHeight(!0))
					: $(".page-header").hasClass(
							"navbar-fixed-top") ? a = $(
							".page-header").outerHeight(!0)
							: $("body").hasClass(
									"page-header-fixed")
									&& (a = 64);
							tableAjaxUrl="rest/stock/stockOutList?serialNum="+serialNum
							/* tableAjaxUrl={ "url":$rootScope.basePath
									+ "/rest/stock/stockOutList?serialNum="+serialNum,// 加载数据中user表数据    
									"contentType": "application/json",
								    "type": "GET",
								    "data": function ( d ) {
								      return JSON.stringify( d );
								    }}*/
				stockOutTable = $("#"+tableid)
						.DataTable(
								{
									language : {
										aria : {
											sortAscending : ": 以升序排列此列",
											sortDescending : ": 以降序排列此列"
										},
										emptyTable : "空表",
										info : "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
										infoEmpty : "没有数据",
										infoFiltered : "(从 _MAX_ 条数据中检索)",
										lengthMenu : "每页显示 _MENU_ 条数据",
										search : "查询:",
										zeroRecords : "抱歉， 没有找到！",
										paginate : {
											"sFirst" : "首页",
											"sPrevious" : "前一页",
											"sNext" : "后一页",
											"sLast" : "尾页"
										}
									},
									/*fixedHeader : {// 固定表头、表底
										header : !0,
										footer : !0,
										headerOffset : a
									},*/
									// select: true,行多选
									order : [ [ 1, "asc" ] ],// 默认排序列及排序方式
									bRetrieve : true,
									// searching: true,//是否过滤检索
									// ordering: true,//是否排序
									lengthMenu : [
											[ 5, 10, 15, 30, -1 ],
											[ 5, 10, 15, 30, "All" ] ],
									pageLength : 10,// 每页显示数量
									processing : true,// loading等待框
									// serverSide: true,
								     ajax :/*{ "url":$rootScope.basePath
			  		  						+ "/rest/stock/stockOutList?serialNum="+serialNum,//加载出库记录  
			  		  						"contentType": "application/json",
			  		  					    "type": "POST",
			  		  					    "data": function ( d ) {
			  		  					      return JSON.stringify( d );
			  		  					    }}*/tableAjaxUrl,
			  		                    "aoColumns": [
			  		                                    { mData: 'stockInOutRecord.inOutNum' },
                                                        { mData: 'stockInOutRecord.inOutType' },
                                                        { mData: 'stockInOutRecord.inOutNum' },
                                                      /*  { mData: 'batchNum' },*/
                                                        { mData: 'stockInOutRecord.stockDate' },
                                                        { mData: 'stockCount' },
			  		                                  { mData: 'warehouseName' },
			  		                                 /* { mData: 'positionNum' },*/
			  		                                  { mData: 'remark' }//备注
			  		                            ],
			  		                   'aoColumnDefs' : [{
											'targets' : 0,
											'render' : function(data,
													type, row, meta) {
		 	    								if(data==''||data==null){
		 	    									return "";
		 	    								}else{
		 	    									return data+"------"+row.stockCount;
		 	    								}
												
											}
										},{
											'targets' : 2,
											'render' : function(data,
													type, row, meta) {
		 	    								if(data==''||data==null){
		 	    									return "";
		 	    								}else{
		 	    									return data;
		 	    								}
												
											}
										}/*,{
											'targets' : 7,
											'render' : function(data,
													type, row, meta) {
		 	    								if(data==''||data==null){
		 	    									return "";
		 	    								}else{
		 	    									return data.positionName;
		 	    								}
												
											}
										},{
											'targets' : 6,
											'render' : function(data,
													type, row, meta) {
		 	    								if(data==''||data==null){
		 	    									return "";
		 	    								}else{
		 	    									return data.warehouseName;
		 	    								}
												
											}
										}*/],

								})
				// 构建datatables结束***************************************
							   return stockOutTable;
								}
			
			function loadZkTable(serialNum,tableid){
				var a = 0;
				debugger;
				 if(stockZkTable!=undefined){
					 stockZkTable.destroy();
		 	    	 }
				 tableAjaxUrl="rest/stock/stockInBatchList?serialNum="+serialNum
				
			
				 stockZkTable = $("#"+tableid)
						.DataTable(
								{
									language : {
										aria : {
											sortAscending : ": 以升序排列此列",
											sortDescending : ": 以降序排列此列"
										},
										emptyTable : "空表",
										info : "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
										infoEmpty : "没有数据",
										infoFiltered : "(从 _MAX_ 条数据中检索)",
										lengthMenu : "每页显示 _MENU_ 条数据",
										search : "查询:",
										zeroRecords : "抱歉， 没有找到！",
										paginate : {
											"sFirst" : "首页",
											"sPrevious" : "前一页",
											"sNext" : "后一页",
											"sLast" : "尾页"
										}
									},
									/*fixedHeader : {// 固定表头、表底
										header : !0,
										footer : !0,
										headerOffset : a
									},*/
									// select: true,行多选
									order : [ [ 2, "desc" ] ],// 默认排序列及排序方式
									bRetrieve : true,
									// searching: true,//是否过滤检索
									// ordering: true,//是否排序
									lengthMenu : [
											[ 5, 10, 15, 30, -1 ],
											[ 5, 10, 15, 30, "All" ] ],
									pageLength : 10,// 每页显示数量
									processing : true,// loading等待框
									// serverSide: true,
								     ajax :tableAjaxUrl,
			  		                    "aoColumns": [
			  		                                  /* { mData: 'batchNum' },*/
	  		                                           { mData: 'stockInOutRecord.inOutType' },
	  		                                          { mData: 'stockInOutRecord.inOutNum' },
	  		                                          { mData: 'stockInOutRecord.shipperOrReceiverName' },
	  		                                          { mData: 'stockInOutRecord.stockDate' },
	 	  		                                      { mData: 'warehouseName' },
	 	  		                                      { mData: 'stockCount' },
		  		                                      { mData: 'sumStockOutCount' },
		  		                                       { mData: 'remark' },//结存数量
		  		                                     { mData: 'stockInOutRecord.stockDate' },
                                                     { mData: 'remark' }
                                                     
			  		                            ],
			  		                   'aoColumnDefs' : [ {
											'targets' : 1,
											'render' : function(data,
													type, row, meta) {
												debugger;
		 	    								if(data==undefined){
		 	    									return "";
		 	    								}else{
		 	    									return data;
		 	    								}
												
											}
										},{
											'targets' : 6,
											'render' : function(data,
													type, row, meta) {
		 	    								if(data==''||data==null){
		 	    									return '0';
		 	    								}else{
		 	    									return data;
		 	    								}
												
											}
										},{
											'targets' : 7,
											'render' : function(data,
													type, row, meta) {
												if(row.sumStockOutCount!=undefined){
													return row.stockCount-row.sumStockOutCount;
												}else{
													return row.stockCount;
												}
											}
										},{
											'targets' : 4,
											'render' : function(data,
													type, row, meta) {
		 	    								if(data==''||data==null){
		 	    									return "";
		 	    								}else{
		 	    									return data;
		 	    								/*	if(row.position==''||row.position==null){
		 	    										return '<span title="仓库:'+row.warehouse.warehouseName+'&nbsp;&nbsp;&nbsp;&nbsp;\n库区:&nbsp;&nbsp;暂无">'+data.warehouseName+'</span>';
		 	    									}else{
		 	    									return '<span title="仓库:'+row.warehouse.warehouseName+'&nbsp;&nbsp;&nbsp;&nbsp;\n库区:'+row.position.positionName+'">'+data.warehouseName+'</span>';
		 	    									}*/
		 	    								}
											},"createdCell": function (td, cellData, rowData, row, col) {
												 $compile(td)($scope);
										    }
										},{
											'targets' : 8,
											'render' : function(data,
													type, row, meta) {
		 	    								if(data==''||data==null){
		 	    									return "";
		 	    								}else{
		 	    									debugger;
		 	    									return daysBetween(timeStamp2ShortString(Date.parse(new Date())),data.substring(0,10));
		 	    								}
												
											}
										}  ],

								})
				// 构建datatables结束***************************************
							   return stockZkTable;
								}
			 $scope.showStock=function(judgeString){
				 $state.go('stock',{stockSerialNum:judgeString}); //切换tab
			}
							// 添加库存开始***************************************
			$scope.addStock = function(judgeString) {
				debugger;
				 $state.go('addOrEditStock',{stockSerialNum:judgeString}); 
			}
						$scope.editStock = function(){
							debugger;
							var stock=$scope.stock;
							$scope.stock=stock;
							$scope.stockView = false;
		        			$scope.stockAdd = false;
		        			$scope.stockEdit = true;
						}
						$scope.cancelEditStock = function(){
							debugger;
							getStockInfo($scope.stock.serialNum);
							$scope.stockView = true;
		        			$scope.stockAdd = true;
		        			$scope.stockEdit = false;
						}	
						function judgeData (){//判断最低库存数量与最高库存数量
							 var maxStock=$scope.stock.maxStock;
								var minStock=$scope.stock.minStock;
								debugger;
								if(parseInt(minStock)>parseInt(maxStock)){
									toastr.warning("最低库存数量不能大于最高库存数量！");
								$("#minStock").focus();
								return false;
								}
								return true;
						}
								$scope.saveStock= function() {
									debugger;
									if($('#stockForm').valid()&&judgeData()){//表单验证通过则执行添加功能
										 $rootScope.judgeIsExist("stock",$scope.stock.stockNum, $scope.stock.serialNum,function(result){
								    			var 	isExist = result;
								    		debugger;
								    		if(isExist){
								    			 toastr.error('库存编号重复！');
								    			return;
								    		}else{
								    			handle.blockUI();
								    			if($scope.manageType.indexOf('zijian')>-1){
													$scope.stock.manageType='1';
														$scope.stock.materielOwner='';
												}else if($scope.manageType.indexOf('jinwai')>-1){
													$scope.stock.manageType='2';
												}else if($scope.manageType.indexOf('jinwai')>-1){
													$scope.stock.manageType='3';
												}
												$scope.stock.serviceParty='';
												$scope.stock.materielSerial=$("#materielSerial").val();
												StockService
												.saveStock($scope.stock)
												.then(
														function(data) {debugger;
															$('#addStockModal').modal(
																	'hide');// 保存成功后关闭模态框
															toastr.success("保存库存数据成功！");
															handle.unblockUI();
															// $state.go('warehouse',{},{reload:true});  // 重新加载datatables数据
															$scope.stock = data;
										        			$scope.stockView = true;
										        			$scope.stockAdd = true;
										        			$scope.stockEdit = false;
										        			$(".alert-danger").hide();
														},
														function(errResponse) {
															toastr.warning("保存失败！");
															console
																	.error('Error while creating User');
														}
												);
								    		}
								    		
								    		});
									
									}
							};	
							// 添加库存结束***************************************
							
							// 修改库存开始***************************************							
							$scope.toEditStockPage = function(judgeString) {//弹出框修改库存信息
								debugger;
								if(judgeString=='zijian'){
									table=tablezijian;
								}else{
									table=tabledaiguan;
								}
								var id_count = table.$('input[type="checkbox"]:checked').length;
								if(id_count==0){
									toastr.warning("请选择一条数据进行编辑");
								}else if(id_count>1){
									toastr.warning("只能选择一条数据进行编辑");
								}else{
									var serialNum = table.$('input[type="checkbox"]:checked').val();
									$state.go("addOrEditStock",{stockSerialNum:serialNum+judgeString});
								}
							};
							// 修改库存结束***************************************							

							// 删除库存开始***************************************							
							$scope.delStock = function(judgeString) {
								debugger;
								var ids = '';
								if(judgeString=='zijian'){
									table=tablezijian;
								}else{
									table=tabledaiguan;
								}
								// Iterate over all checkboxes in the table
								table.$('input[type="checkbox"]').each(function() {
									// If checkbox exist in DOM
									if ($.contains(document, this)) {
										// If checkbox is checked
										if (this.checked) {
											// 将选中数据id放入ids中
											if (ids == '') {
												ids = this.value;
											} else
												ids = ids + ',' + this.value;
										}
									}
								});

								if (ids == '') {// 未勾选删除数据									
									toastr.warning("未勾选要删除数据！");
								} else {
									$('#delStock'+judgeString+'Modal').modal('show');// 打开确认删除模态框
									
									$scope.confirmDellStock = function() {										
										StockService
												.delStocks(ids)
												.then(
														function(data) {
															$('#delStock'+judgeString+'Modal').modal(
																	'hide');// 删除成功后关闭模态框
															toastr.success("删除成功！");
															table.ajax.reload(); // 重新加载datatables数据
														},
														function(errResponse) {
															console
																	.error('Error while deleting Users');
														}

												);
									}
								}								
							};
							$scope.showStockInfo=function(serialNum){
								debugger;
								 $state.go('stockView',{stockSerialNum:serialNum},{reload:true}); 
								
							}
							  var selectIndex;
					 	       $scope.selectMateriel = function(index){
					 	    	  selectIndex = index;
					 	       }
						     var selectMaterielTable;
					 	       var selectMaterielStock = function() {
					 	    	   debugger;
					 	                a = 0;
					 	                App.getViewPort().width < App.getResponsiveBreakpoint("md") ? $(".page-header").hasClass("page-header-fixed-mobile") && (a = $(".page-header").outerHeight(!0)) : $(".page-header").hasClass("navbar-fixed-top") ? a = $(".page-header").outerHeight(!0) : $("body").hasClass("page-header-fixed") && (a = 64);
					 	               selectMaterielTable = $("#select_sample_2")
					 	    			.DataTable({
					 	                    language: {
					 	                        aria: {
					 	                            sortAscending: ": 以升序排列此列",
					 	                            sortDescending: ": 以降序排列此列"
					 	                        },
					 	                        emptyTable: "空表",
					 	                        info: "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
					 	                        infoEmpty: "没有数据",
					 	                        //infoFiltered: "(filtered1 from _MAX_ total entries)",
					 	                        lengthMenu: "每页显示 _MENU_ 条数据",
					 	                        search: "查询:",processing:"加载中...",infoFiltered: "（从 _MAX_ 项数据中筛选）",
					 	                        zeroRecords: "抱歉， 没有找到！",
					 	                        paginate: {
					 	                            "sFirst": "首页",
					 	                            "sPrevious": "前一页",
					 	                            "sNext": "后一页",
					 	                            "sLast": "尾页"
					 	                         }
					 	                    },
					 	    /*                fixedHeader: {//固定表头、表底
					 	                        header: !0,
					 	                        footer: !0,
					 	                        headerOffset: a
					 	                    },*/
					 	                    order: [[1, "desc"]],//默认排序列及排序方式
					 	                    searching: true,//是否过滤检索
					 	                    ordering:  true,//是否排序
					 	                    lengthMenu: [[5, 10, 15, 30, -1], [5, 10, 15, 30, "All"]],
					 	                    pageLength: 5,//每页显示数量
					 	                    processing: true,//loading等待框
//					 	                    serverSide: true,
					 	                    ajax: "rest/materiel/findMaterielList",//加载数据中
					 	                    "aoColumns": [
					 	                                  { mData: 'serialNum' },
							                              { mData: 'materielNum' },
							                              { mData: 'materielName' },
							                              { mData: 'specifications' },
							                              { mData: 'unit' },
							                              { mData: 'typeName' },
							                              { mData: 'originCountry' },
							                              { mData: 'brand' },
							                              /*{ mData: null },*/
							                              { mData: 'versionNO' }
					 	                            ],
					 	                   'aoColumnDefs' : [ {
					 	    							'targets' : 0,
					 	    							'searchable' : false,
					 	    							'orderable' : false,
					 	    							
					 	    							'render' : function(data,
					 	    									type, row, meta) {
					 	    								return '<input type="radio" ng-click="selectMaterialForStock(\''+data+'\',\''+row.materielName+'\',\''+row.materielNum+'\',\''+row.specifications+'\')" name="serialNum[]" value="'
					 	    										+ $('<div/>')
					 	    												.text(
					 	    														data)
					 	    												.html()
					 	    										+ '">';
					 	    							},
					 	    							"createdCell": function (td, cellData, rowData, row, col) {
					 	    								 $compile(td)($scope);
					 	    						       }
					 	    						},{
					 	    							'targets' : 1,
					 	    							'render' : function(data,
					 	    									type, row, meta) {
					 	    								var bomIcon='';//bom图标
					 	    								if(row.isBOM==1){
					 	    									bomIcon = '<span class="label label-sm label-success">B</span> '
					 	    								}
					 	    								return bomIcon + data;
					 	    							}

					 	    						}]

					 	                }).on('order.dt',
					 	                function() {
					 	                    console.log('排序');
					 	                })
					 	            };
					 	            
					 	           $scope.showStockRecord=function(serialNum){//列表页显示出入库记录
					 	        	  loadStockInTable(serialNum,"sample_stockin");//入库记录
							 			loadStockOutTable(serialNum,"sample_stockout");//出库记录
					 	           }
						$scope.selectMaterialForStock=function(serialNum,materielName,materielNum,specifications){
							debugger;
							$scope.row = {};
		 	            	$scope.row.serialNum = serialNum;//物料流水
		 	            	$scope.row.materielName=materielName;//物料名称
		 	            	$scope.row.materielNum=materielNum;//物料编号
		 	            	$scope.row.specifications=specifications;//规格型号
						}
						   // 确认选择开始***************************************
		 	    		$scope.confirmSelect = function() {
		 	    			var ids = '';
		 	    			// Iterate over all checkboxes in the table
		 	    			selectMaterielTable.$('input[type="radio"]').each(
		 	    					function() {
		 	    						// If checkbox exist in DOM
		 	    						if ($.contains(document, this)) {
		 	    							// If checkbox is checked
		 	    							if (this.checked) {
		 	    								// 将选中数据id放入ids中
		 	    								if (ids == '') {
		 	    									ids = this.value;
		 	    								} else
		 	    									ids = ids + ','
		 	    											+ this.value;
		 	    							}
		 	    						}
		 	    					});
		 	    			if(ids==''){
		 	        			toastr.warning('请选择一个物料！');return;
		 	        		}
		 	    			//为前台五个参数赋值
		 	    			$("#materielSerial").val($scope.row.serialNum) ;//物料流水
		 	    			$("#materielName").val($scope.row.materielName);
		 	    			$("#materielNum").val($scope.row.materielNum);
		 	    			$("#specifications").val($scope.row.specifications);
		 	    			//$scope.stock.materielSerial=$scope.row.serialNum;//物料名称
		 	    			/*$scope.stock.materielName=$scope.row.materielName;//物料名称
		 	            	$scope.stock.materielNum=$scope.row.materielNum;//物料编号
		 	            	$scope.stock.specifications=$scope.row.specifications;//规格型号
*/	 	    			$('#basicMaterielInfo').modal('hide');// 删除成功后关闭模态框
		 	    			$(".modal-backdrop").remove();
		 	    		};
							// 页面加载完成后调用，验证输入框
							$scope.$watch('$viewContentLoaded', function() {  
								var e = $("#stockForm"),
						        r = $(".alert-danger", e),
						        i = $(".alert-success", e);
						        e.validate({
						            errorElement: "span",
						            errorClass: "help-block help-block-error",
						            focusInvalid: !1,
						            ignore: "",
						            messages: {
						            	stockNum:{required:"库存编号不能为空！"},
						            	materielNum:{required:"未选择物料！"},
						            	maxStock:{required:"最高库存不能为空！",digits:"请输入正确的数字!"},
						            	minStock:{required:"最低库存不能为空！",digits:"请输入正确的数字!"},
						            	manageType:{required:"未选择管理类型！"},
						            	materielOwner: { required:"物权方不能为空！"},
						            	serviceParty:{required:"服务方不能为空！"}
						            },
						            rules: {
						            	stockNum:{required:true},
						            	materielNum:{required:true},
						            	maxStock:{required:true,digits:true},
						            	minStock:{required:true,digits:true},
						            	manageType:{required:true},
						            	materielOwner:{required:true},
						            	serviceParty:{required:true}
						            },
						            invalidHandler: function(e, t) {
						                i.hide(),
						                r.show(),
						                App.scrollTo(r, -200)
						            },
						            errorPlacement: function(e, r) {
						                r.is(":checkbox") ? e.insertAfter(r.closest(".md-checkbox-list, .md-checkbox-inline, .checkbox-list, .checkbox-inline")) : r.is(":radio") ? e.insertAfter(r.closest(".md-radio-list, .md-radio-inline, .radio-list,.radio-inline")) : e.insertAfter(r)
						            },
						            highlight: function(e) {
						                $(e).closest(".form-group").addClass("has-error")
						            },
						            unhighlight: function(e) {
						                $(e).closest(".form-group").removeClass("has-error")
						            },
						            success: function(e) {
						                e.closest(".form-group").removeClass("has-error")
						            },
						            submitHandler: function(e) {
						                i.show(),
						                r.hide()
						            }
						        })   							}); 
							
							 /**
							 * 加载物权方下拉框数据
							 */
							var initSuppliers = function(){
								var promise = orderService.initSuppliers();
						        	promise.then(function(data){
						        		$scope.suppliers = data.data;
						        		setTimeout(function () {
						        			$("#materielOwner").selectpicker({
						                        showSubtext: true
						                    });
						        			$('#materielOwner').selectpicker('refresh');//刷新插件
						        			
						                }, 100);
						        		
						        	},function(data){
						        		//调用承诺接口reject();
						        	});
							}
							function count(str,char){//统计逗号出现的次数
							 var str=str;
							 var num=(str.split(char)).length-1;
							 return num;
							}
							 function getStockInfo(serialNum){//查看库区
						    	   if(!handle.isNull(serialNum)){
						    		   debugger;
						    			 var promise =StockService .selectBySerialNum(serialNum);
						 	        	promise.then(function(data){
						 	        		  debugger;
						 	        			 $scope.stock = data.stock;
						 	        			 $("#materielSerial").val(data.stock.materielSerial);
						 	            },function(data){
						 	               //调用承诺接口reject();
						 	            });
						    		 }
						       }
							 function getStockDetailInfo(serialNum){//查看库区详情
						    	   if(!handle.isNull(serialNum)){
						    		   debugger;
						    			 var promise =StockService .selectDetailBySerialNum(serialNum);
						 	        	promise.then(function(data){
						 	        		  debugger;
						 	        			 $scope.stock = data.stock;
						 	            },function(data){
						 	               //调用承诺接口reject();
						 	            });
						    		 }
						       }
							       /**
							        * 下载EXCEL模板
							        */
							       $scope.downloadImportTemp = function(){
							    	   window.location.href=$rootScope.basePath+"/rest/stock/downloadImportTemp";
							       }
							       
							       /**
							        * 上传EXCEL
							        */
							       $scope.uploadExcel = function(){
							    	    var file = document.querySelector('input[type=file]').files[0];
							    	    if(handle.isNull(file)){
							    	    	toastr.warning("请选择Excel文件！");
							    	    }
							    	    console.log(file.name);
							    	    var type = file.name.substring(file.name.lastIndexOf("."));
							    	   if(type != ".xls"){
							    		toastr.warning("文件格式不正确，需要xls类型的Excel文档");
							    		   return;
							    	   }
							    	   	handle.blockUI("正在导入中，请不要进行其他操作"); 
							    	   	var promise = StockService.uploadExcel();
						       			promise.then(function(data){
						       				handle.unblockUI(); 
						       				if(data.data.data=="success"){
						       					toastr.success("导入成功");
						       					table.ajax.reload();
						       				}else{
						       					toastr.error(data.data.data);
						       				}
						       				$('#import').modal('hide'); 
							            },function(data){
							               //调用承诺接口reject();
							            	toastr.error("操作失败");
							            	$('#import').modal('hide'); 
							            });
							    	   
							       }
							       $scope.exportStock = function(){
								    	 handle.blockUI("正在导出数据，请稍后"); 
								    	 window.location.href=$rootScope.basePath+"/rest/stock/exportStock";
								    	 handle.unblockUI(); 
								       }
								       
							       $('#import').on('hide.bs.modal', function (e) { 
							    	   $("#resetFile").trigger("click");
							       })
						} ]);
