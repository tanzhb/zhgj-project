/* Setup general page controller */
angular.module('MetronicApp').controller('CustomsFormController', ['$rootScope', '$scope', 'settings','$filter',
    '$state',"$stateParams",'$compile','$location','customsFormService','orderService','FileUploader', function($rootScope, $scope, settings,$filter,$state,$stateParams,$compile,$location,customsFormService,orderService,FileUploader) {
    $scope.$on('$viewContentLoaded', function() {   
    	// initialize core components
    	App.initAjax();
    	handle = new pageHandle();
    	// set default layout mode
    	$rootScope.settings.layout.pageContentWhite = true;
        $rootScope.settings.layout.pageBodySolid = false;
        $rootScope.settings.layout.pageSidebarClosed = false;
        if($state.current.name=="customsDeclarationForm"){//报关单
        	loadDeclarationTable();// 加载报关单列表
        	}else  if($state.current.name=="customsClearanceForm"){//清关单
        		loaClearanceTable();// 加载清关单列表
        	}else if($state.current.name=="addCustomsForm"){
        		$scope.customsFormType=$stateParams.customsFormType;
        		loadDelieryTable($scope.customsFormType);
        		if($stateParams.serialNum!=undefined){
        			getCustomsFormInfo($stateParams.serialNum);
        		}else{
        			 $scope.customsForm={};
        			 $rootScope.setNumCode($stateParams.customsFormType=='clearance'?"QG":"BG",function(newCode){//
	    		 			$scope.customsForm.customsFormNum= newCode;//清报关单号
	    		 		});
        			 
        		}
        		initSuppliers();
        		doSomthing();
        	}else if($state.current.name=="viewCustomsForm"){
        		$scope.customsFormView=true;
        		$scope.customsFormAdd=true;
        		 $scope.customsFormEdit =false;
        		 $scope.fileInfoShow =true;
        		 $scope.fileInfoInput =true;
        		getCustomsFormInfo($stateParams.serialNum);
        		
        	}
        $scope.customsFormType=$stateParams.customsFormType;
        validateFileInit();//加载附件表单验证
    });
  

   
   $scope.renderDone = function(){
   	var date3= $scope.buyOrder.orderDate;
   	var date4= $scope.buyOrder.makeDate;
   	$scope.datepickerInit();
   	$scope.buyOrder.orderDate = date3;
   	$scope.buyOrder.makeDate = date4;
  };
   function getCustomsFormInfo(serialNum){
	   if(!handle.isNull(serialNum)){
			 debugger;
			 var promise =customsFormService.getCustomsFormInfo(serialNum);
			 promise.then(function(data){
				 debugger;
				$scope.customsForm=data.customsForm;
				$scope.file=data.file;
				_fileIndex=$scope.file.length;
				loadMaterielTable($scope.customsForm.orderSerial,$scope.customsForm.deliverSerial);//加载物料信息列表
				
			 });
		 }
	 
   }
   $scope.showCustomsFormInfo=function(serialNum,judgeString){
	   $state.go('viewCustomsForm',{customsFormType:judgeString,serialNum:serialNum},{reload:true}); 
   }
  $scope.addCustomsForm=function(judgeString){
	  $state.go('addCustomsForm',{customsFormType:judgeString},{reload:true}); 
  };
	 $scope.toEditCustomsFormPage = function(judgeString) {//弹出框修改价格信息
		 if(table.rows('.active').data().length != 1&&judgeString=='declaration'){
				showToastr('toast-top-center', 'warning', '请选择一条报关单进行修改！')
			}else if(table.rows('.active').data().length != 1&&judgeString=='clearance'){
				showToastr('toast-top-center', 'warning', '请选择一条清关单进行修改！')
			}else{
				if(table.row('.active').data().status!=1){
					var serialNum = table.$('input[type="checkbox"]:checked').val();
					 $state.go("addCustomsForm",{serialNum:serialNum,customsFormType:judgeString});
				}else{
					if(judgeString=='declaration'){
						showToastr('toast-top-center', 'warning', '已确认的报关单不能修改！')
					}else if(judgeString=='clearance'){
						showToastr('toast-top-center', 'warning', '已确认的清关单不能修改！')
					}
				}
				
			}
			
			} 
	 $scope.confirmCustomsForm = function(judgeString) {//确认信息
		 if(table.rows('.active').data().length != 1&&judgeString.indexOf('declaration')>-1){
				showToastr('toast-top-center', 'warning', '请选择一条报关单进行确认！')
			}else if(table.rows('.active').data().length != 1&&judgeString.indexOf('clearance')>-1){
				showToastr('toast-top-center', 'warning', '请选择一条清关单进行确认！')
			}else{
				if(table.row('.active').data().status!=1){
					var serialNum = table.$('input[type="checkbox"]:checked').val();
					 $state.go("addCustomsForm",{serialNum:serialNum,customsFormType:judgeString});
				}else{
					if(judgeString.indexOf('declaration')>-1){
						showToastr('toast-top-center', 'warning', '已确认的报关单不能再次确认！')
					}else if(judgeString.indexOf('clearance')>-1){
						showToastr('toast-top-center', 'warning', '已确认的清关单不能再次确认！')
					}
				}
				
			
			}
			
			} 
    var table;
    var loadDeclarationTable = function() {
    	var judgeString='declaration';
    	var tableAjaxUrl = "rest/customsForm/getCustomsFormList?type=declaration";
            a = 0;
            App.getViewPort().width < App.getResponsiveBreakpoint("md") ? $(".page-header").hasClass("page-header-fixed-mobile") && (a = $(".page-header").outerHeight(!0)) : $(".page-header").hasClass("navbar-fixed-top") ? a = $(".page-header").outerHeight(!0) : $("body").hasClass("page-header-fixed") && (a = 64);
            table = $("#sample_declaration")
			.DataTable({
                language: {
                    aria: {
                        sortAscending: ": activate to sort column ascending",
                        sortDescending: ": activate to sort column descending"
                    },
                    emptyTable: "空表",
                    info: "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
                    infoEmpty: "没有数据",
                    // infoFiltered: "(filtered1 from _MAX_ total entries)",
                    lengthMenu: "每页显示 _MENU_ 条数据",
                    search: "查询:",
                    zeroRecords: "抱歉， 没有找到！",
                    paginate: {
                        "sFirst": "首页",
                        "sPrevious": "前一页",
                        "sNext": "后一页",
                        "sLast": "尾页"
                     }
                },
/*
 * fixedHeader: {//固定表头、表底 header: !0, footer: !0, headerOffset: a },
 */
                order: [[1, "desc"]],// 默认排序列及排序方式
                searching: true,// 是否过滤检索
                ordering:  true,// 是否排序
                lengthMenu: [[5, 10, 15, 30, -1], [5, 10, 15, 30, "All"]],
                pageLength: 5,// 每页显示数量
                processing: true,// loading等待框
// serverSide: true,
                ajax: tableAjaxUrl,// 加载数据中
                textAlign: 'center',
                "aoColumns": [
                              { mData: 'serialNum'},
                              { mData: 'customsFormNum' },
                              { mData: 'playArrivalDate' },
                              { mData: 'port' },
                              { mData: 'deliverNum' },
                              { mData: 'deliverAmount' ,
									mRender:function(data){
	                            		if(data!=""&&data!=null){
	                            			return $filter('currency')(data,'');
	                            		}else{
	                            			return $filter('currency')(0,'');
	                            		}
	                            	}},
                              { mData: 'addedTax',
										mRender:function(data){
		                            		if(data!=""&&data!=null){
		                            			return $filter('currency')(data,'');
		                            		}else{
		                            			return $filter('currency')(0,'');
		                            		}
		                            	} },
                              { mData: 'customsAmount',
											mRender:function(data){
			                            		if(data!=""&&data!=null){
			                            			return $filter('currency')(data,'');
			                            		}else{
			                            			return $filter('currency')(0,'');
			                            		}
			                            	} },
                              { mData: 'agentUnit' },
                              { mData: 'fileCount' },
                              { mData: 'createTime' },
                              { mData: 'creator' },
                              { mData: 'status' }
                        ],
               'aoColumnDefs' : [ {
							'targets' : 0,
							'searchable' : false,
							'orderable' : false,
							'render' : function(data,
									type, full, meta) {
								return "<label class='mt-checkbox mt-checkbox-single mt-checkbox-outline'>" +
								"<input type='checkbox' class='checkboxes' value="+ data +" />" +
								"<span></span></label>";
							},
							"createdCell": function (td, cellData, rowData, row, col) {
								 $compile(td)($scope);
						       }
						},{
                      	  'targets' : 1,
                    	  'render' : function(data,
                    			  type, row, meta) {
                    		  return '<a   ng-click="showCustomsFormInfo(\''+row.serialNum+'\',\''+judgeString+'\')">'+data+'</a>';
                    		  //return data;
                    	  },"createdCell": function (td, cellData, rowData, row, col) {
                    		  $compile(td)($scope);
                    	  }
                      },{
                      	  'targets' : 12,
                    	  'render' : function(data,
                    			  type, row, meta) {
                    		  if(data!=""&&data!=null){
                  				if(data=='0'){
                        				return '<span  class="label label-sm label-info ng-scope">待确认</span>';
                        			}else if(data=='1'){
                        				return '<span  class="label label-sm label-success ng-scope">已确认</span>';
                        			}
                        		}else{
                        			return "";
                        		}
                    	  }
                      } ]

            }).on('order.dt',
            function() {
                console.log('排序');
            })
            
            
            
            
            // 添加checkbox功能***************************************
			// Handle click on "Select all" control
			$('#example-select-all').on(
					'click',
					function() {
						// Check/uncheck all checkboxes in the
						// table
						var rows = table.rows({
							'search' : 'applied'
						}).nodes();
						$('input[type="checkbox"]', rows).prop(
								'checked', this.checked);
					});
	
			// Handle click on checkbox to set state of "Select
			// all" control
			$('#sample_declaration tbody')
					.on(
							'change',
							'input[type="checkbox"]',
							function() {
								// If checkbox is not checked
								if (!this.checked) {
									var el = $(
											'#example-select-all')
											.get(0);
									// If "Select all" control
									// is checked and has
									// 'indeterminate' property
									if (el
											&& el.checked
											&& ('indeterminate' in el)) {
										// Set visual state of
										// "Select all" control
										// as 'indeterminate'
										el.indeterminate = true;
									}
								}
							});
			// 添加checkbox功能
			// ***************************************
			$("#sample_declaration").find(".group-checkable").change(function() {
	            var e = jQuery(this).attr("data-set"),
	            t = jQuery(this).is(":checked");
	            jQuery(e).each(function() {
	                t ? ($(this).prop("checked", !0), $(this).parents("tr").addClass("active")) : ($(this).prop("checked", !1), $(this).parents("tr").removeClass("active"))
	            })
	        }),
	        $("#sample_declaration").on("change", "tbody tr .checkboxes",
	        function() {
	            $(this).parents("tr").toggleClass("active")
	        })
        };
        var loaClearanceTable = function() {
        	 var judgeString='clearance';
        	var tableAjaxUrl = "rest/customsForm/getCustomsFormList?type=clearance";
                a = 0;
                App.getViewPort().width < App.getResponsiveBreakpoint("md") ? $(".page-header").hasClass("page-header-fixed-mobile") && (a = $(".page-header").outerHeight(!0)) : $(".page-header").hasClass("navbar-fixed-top") ? a = $(".page-header").outerHeight(!0) : $("body").hasClass("page-header-fixed") && (a = 64);
                table = $("#sample_clearance")
    			.DataTable({
                    language: {
                        aria: {
                            sortAscending: ": activate to sort column ascending",
                            sortDescending: ": activate to sort column descending"
                        },
                        emptyTable: "空表",
                        info: "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
                        infoEmpty: "没有数据",
                        // infoFiltered: "(filtered1 from _MAX_ total entries)",
                        lengthMenu: "每页显示 _MENU_ 条数据",
                        search: "查询:",
                        zeroRecords: "抱歉， 没有找到！",
                        paginate: {
                            "sFirst": "首页",
                            "sPrevious": "前一页",
                            "sNext": "后一页",
                            "sLast": "尾页"
                         }
                    },
    /*
     * fixedHeader: {//固定表头、表底 header: !0, footer: !0, headerOffset: a },
     */
                    order: [[1, "desc"]],// 默认排序列及排序方式
                    searching: true,// 是否过滤检索
                    ordering:  true,// 是否排序
                    lengthMenu: [[5, 10, 15, 30, -1], [5, 10, 15, 30, "All"]],
                    pageLength: 5,// 每页显示数量
                    processing: true,// loading等待框
    // serverSide: true,
                    ajax: tableAjaxUrl,// 加载数据中
                    textAlign: 'center',
                    "aoColumns": [
                                  { mData: 'serialNum'},
                                  { mData: 'customsFormNum' },
                                  { mData: 'playArrivalDate' },
                                  { mData: 'port' },
                                  { mData: 'deliverNum' },
                                  { mData: 'deliverAmount',
										mRender:function(data){
		                            		if(data!=""&&data!=null){
		                            			return $filter('currency')(data,'');
		                            		}else{
		                            			return $filter('currency')(0,'');
		                            		}
		                            	} },
                                  { mData: 'addedTax',
											mRender:function(data){
			                            		if(data!=""&&data!=null){
			                            			return $filter('currency')(data,'');
			                            		}else{
			                            			return $filter('currency')(0,'');
			                            		}
			                            	} },
                                  { mData: 'customsAmount',
												mRender:function(data){
				                            		if(data!=""&&data!=null){
				                            			return $filter('currency')(data,'');
				                            		}else{
				                            			return $filter('currency')(0,'');
				                            		}
				                            	} },
                                  { mData: 'agentUnit' },
                                  { mData: 'fileCount' },
                                  { mData: 'createTime' },
                                  { mData: 'creator' },
                                  { mData: 'status' }
                            ],
                   'aoColumnDefs' : [ {
    							'targets' : 0,
    							'searchable' : false,
    							'orderable' : false,
    							'render' : function(data,
    									type, full, meta) {
    								return "<label class='mt-checkbox mt-checkbox-single mt-checkbox-outline'>" +
    								"<input type='checkbox' class='checkboxes' value="+ data +" />" +
    								"<span></span></label>";
    							},
    							"createdCell": function (td, cellData, rowData, row, col) {
    								 $compile(td)($scope);
    						       }
    						} ,{
    	                      	  'targets' : 1,
    	                    	  'render' : function(data,
    	                    			  type, row, meta) {
    	                    		  return '<a   ng-click="showCustomsFormInfo(\''+row.serialNum+'\',\''+judgeString+'\')">'+data+'</a>';
    	                    		  //return data;
    	                    	  },"createdCell": function (td, cellData, rowData, row, col) {
    	                    		  $compile(td)($scope);
    	                    	  }
    	                      },{
    	                      	  'targets' : 12,
    	                    	  'render' : function(data,
    	                    			  type, row, meta) {
    	                    		  if(data!=""&&data!=null){
    	                    			  if(data=='0'){
    	                        				return '<span  class="label label-sm label-info ng-scope">待确认</span>';
    	                        			}else if(data=='1'){
    	                        				return '<span  class="label label-sm label-success ng-scope">已确认</span>';
    	                        			}
	                            		}else{
	                            			return "";
	                            		}
    	                    	  }
    	                      }]

                }).on('order.dt',
                function() {
                    console.log('排序');
                })
                
                
                
                
                // 添加checkbox功能***************************************
    			// Handle click on "Select all" control
    			$('#example-select-all').on(
    					'click',
    					function() {
    						// Check/uncheck all checkboxes in the
    						// table
    						var rows = table.rows({
    							'search' : 'applied'
    						}).nodes();
    						$('input[type="checkbox"]', rows).prop(
    								'checked', this.checked);
    					});
    	
    			// Handle click on checkbox to set state of "Select
    			// all" control
    			$('#sample_clearance tbody')
    					.on(
    							'change',
    							'input[type="checkbox"]',
    							function() {
    								// If checkbox is not checked
    								if (!this.checked) {
    									var el = $(
    											'#example-select-all')
    											.get(0);
    									// If "Select all" control
    									// is checked and has
    									// 'indeterminate' property
    									if (el
    											&& el.checked
    											&& ('indeterminate' in el)) {
    										// Set visual state of
    										// "Select all" control
    										// as 'indeterminate'
    										el.indeterminate = true;
    									}
    								}
    							});
    			// 添加checkbox功能
    			// ***************************************
    			$("#sample_clearance").find(".group-checkable").change(function() {
    	            var e = jQuery(this).attr("data-set"),
    	            t = jQuery(this).is(":checked");
    	            jQuery(e).each(function() {
    	                t ? ($(this).prop("checked", !0), $(this).parents("tr").addClass("active")) : ($(this).prop("checked", !1), $(this).parents("tr").removeClass("active"))
    	            })
    	        }),
    	        $("#sample_clearance").on("change", "tbody tr .checkboxes",
    	        function() {
    	            $(this).parents("tr").toggleClass("active")
    	        })
            };
            
            /***选择收货列表初始化START***/
		       var t_table;
		       var loadDelieryTable = function(customsFormType) {
					
					var a = 0;
					App.getViewPort().width < App.getResponsiveBreakpoint("md") ? $(".page-header").hasClass("page-header-fixed-mobile")&& (a = $(".page-header").outerHeight(!0)): 
						$(".page-header").hasClass("navbar-fixed-top") ? a = $(".page-header").outerHeight(!0): $("body").hasClass("page-header-fixed")&& (a = 64);

						t_table = $("#takeDelivery").DataTable(
								{
									language : {
										aria : {
											sortAscending : ": activate to sort column ascending",
											sortDescending : ": activate to sort column descending"
										},
										emptyTable : "空表",
										info : "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
										infoEmpty : "没有数据",
										infoFiltered : "(从 _MAX_ 条数据中检索)",
										lengthMenu : "每页显示 _MENU_ 条数据",
										search : "查询:",
										zeroRecords : "抱歉， 没有找到！&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;",
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
									order : [ [ 1, "desc" ] ],// 默认排序列及排序方式
									bRetrieve : true,
									//"sScrollX": "100%",
								//	"sScrollXInner": "110%",
									"bScrollCollapse": true,
									// searching: true,//是否过滤检索
									// ordering: true,//是否排序
									lengthMenu : [
									              [ 5, 10, 15,15, 30, -1 ],
									              [ 5, 10, 15, 15,30, "All" ] ],
									              pageLength : 10,// 每页显示数量
									              processing : true,// loading等待框
									              // serverSide: true,
									              ajax:"rest/delivery/findAllDeliveryList?customsFormType="+customsFormType,//加载数据中user表数据?serialNum="+materielSerialNum
									              "aoColumns": [
									                    { mData: 'serialNum'},
							                            { mData: 'deliverNum' },
							                            { mData: 'orderNum' },
							                            //{ mData: 'materielCount' },物料条目数
							                          { mData: 'materielTotalCount' },//物料总数
							                            { mData: 'packageCount' },
							                            { mData: 'receiver'},
							                            { mData: 'deliveryAddress'},
							                            { mData: 'deliverDate'},
							                            { mData: 'transportType'},
							                            { mData: 'takeAddress' },
							                            { mData: 'remark'},
							                            { mData: 'status'}
									                            ],
									                            
									                            'aoColumnDefs': [ {
									                            	'targets' : 0,
									                            	'searchable' : false,
									                            	'orderable' : false,
									                            	'className' : 'dt-body-center',
									                            	'render' : function(data,type, row, meta) {
//									                            		return '<input  type="radio" id='+data+'  ng-click="getSelectIndex('+meta.row+')"   name="takeDeliverySerial" value="'
//							  											+ $('<div/>')
//							  													.text(
//							  															data)
//							  													.html()
//							  											+ '">';
									                            		return '<label class="mt-radio mt-radio-outline">'+
									                                     '<input type="radio"  ng-click="getSelectIndex(\''+meta.row+'\')" name="takeDeliverySerial"  class="checkboxes" id="'+data+'" value="'+data+'" />'+
									                                     '<span></span></label>';
									                            	},
									                            	"createdCell": function (td, cellData, rowData, row, col) {
									                            		$compile(td)($scope);
									                            	}
									                            } ,
									                            
									                            {
									                            	'targets' : 1,
									                            	'className' : 'dt-body-center',
									                            	'render' : function(data,
									                            			type, row, meta) {
									                            		return '<a data-toggle="modal" ng-click="jumpToGetDeliveryInfo(\''+row.serialNum+'\')" ">'+data+'</a>';
									                            	},
									                            	"createdCell": function (td, cellData, rowData, row, col) {
									                            		$compile(td)($scope);
									                            	}
									                            },
									                            {
									                            	'targets' : 11,
									                            	'render' : function(data,
									                            			type, row, meta) {if(data!=""&&data!=null){
									                            				if(data=='0'){
										                            				return '待发货';
										                            			}else if(data=='PENDING'){
										                            				return '审批中';
										                            			}else if(data=='WAITING_FOR_APPROVAL'){
										                            				return '待审批';					                            				
																				}else if(data=='3'){
																					return '待收货';
																				}else if(data=='APPROVAL_FAILED'){
																					return '审批失败';
																				}else if(data=='4'){
																					return '已收货';
																				}else{
																					return '';
																				}
										                            		}else{
										                            			return "";
										                            		}}
									                            }
									                            ]}).on('order.dt',
									                            		function() {
									                            	console.log('排序');
									                            });
						$("#takeDelivery").find(".group-checkable").change(function() {
					        var e = jQuery(this).attr("data-set"),
					        t = jQuery(this).is(":checked");
					        jQuery(e).each(function() {
					            t ? ($(this).prop("checked", !0), $(this).parents("tr").addClass("active")) : ($(this).prop("checked", !1), $(this).parents("tr").removeClass("active"))
					        })
					    }),
		$("#takeDelivery").on("change", "tbody tr .checkboxes",
    function() {
        $(this).parents("tr").toggleClass("active")
    })
					};
					
					$scope.confirmDeliverySelect = function(){
			  			var id_count = $('#takeDelivery input[name="takeDeliverySerial"]:checked').length;
						if(id_count==0){
							toastr.warning("请选择发货单");
						}else{
							//var serialNum = $('#buyOrder input[name="selecrOrderSerial"]:checked').val();
							var delivery = t_table.row('.active').data();
								$scope.customsForm.deliverNum = delivery.deliverNum;//发货单号赋值
								$scope.customsForm.deliverSerial = $('#takeDelivery input[name="takeDeliverySerial"]:checked').val();//发货流水
								$scope.customsForm.buyOrderNum = delivery.orderNum;
								$scope.customsForm.orderSerial = delivery.orderSerial;
							
							loadMaterielTable($scope.customsForm.orderSerial,$scope.customsForm.deliverSerial);//加载物料列表
							
							$("#takeDeliveryInfo").modal('hide'); 
						}
						
			  		}
					var  materielTable;
					function loadMaterielTable(orderSerial,deliverSerial){
						var a = 0;
						debugger;
						tableAjaxUrl= "rest/invoice/getMaterielList?orderSerial="+orderSerial+"&deliverSerial="+deliverSerial;
						 if(materielTable!=undefined){
							 materielTable.destroy();
						/*	 materielTable.ajax.reload();*/
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
										materielTable = $("#sample_materiel")
						.DataTable(
								{
									language : {
										aria : {
											sortAscending : ": activate to sort column ascending",
											sortDescending : ": activate to sort column descending"
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
									ajax : tableAjaxUrl,// 加载数据中发票表数据
									 /*ajax :{ "url":tableAjaxUrl,// 加载数据中公告列表的数据    
					  						"contentType": "application/json",
					  						 "data": function ( d ) {
						  					      return JSON.stringify( d );
						  					    }},*/
									"aoColumns" : [
									             /*  	{
									               	mData : 'serialNum'
									               	},*/
													{
													mData : 'materielNum'
													},{
														mData : 'customsCode'
													},{
														mData : 'materielName'
													},  {
														mData : 'specifications'
													},{
														mData : 'unit'
													}, {
														mData : 'amount'
													},{
														mData : 'orderUnitPrice',
														mRender:function(data){
						                            		if(data!=""&&data!=null){
						                            			return $filter('currency')(data,'');
						                            		}else{
						                            			return $filter('currency')(0,'');
						                            		}
						                            	}
													}, {
														mData : 'currency'
													}, { 
														mData : 'materielMoney',
														mRender:function(data){
						                            		if(data!=""&&data!=null){
						                            			return $filter('currency')(data,'');
						                            		}else{
						                            			return $filter('currency')(0,'');
						                            		}
						                            	}
													}, {
														mData : 'rate'
													},{
														mData : 'rateMoney'
													},{
														mData : 'customsRate'
													},{
														mData : 'customRateMoney'
													}
													],
												'aoColumnDefs' : [  ],
												"fnInitComplete":function(settings,data) {//fnInitComplete stateLoadCallback
													  $scope.customsForm.deliverAmount=data.deliverAmount;
								  					  $scope.customsForm.addedTax=data.addedTax;
								  					  $scope.customsForm.customsAmount=data.customsAmount;
								  					  $scope.customsForm.port=data.port;
								  					  $scope.customsForm.shipNumber=data.shipNumber;
								  					  $scope.customsForm.playArrivalDate=data.playArrivalDate;
								                   }
									
								});

						// ***************************************
						// 构建datatables结束***************************************
						}
        
        // 弹出确认删除模态框
            var ids = '';
        $scope.delCustomsForm = function(judgeString) {
        	debugger;
			// Iterate over all checkboxes in the table
			table.$('input[type="checkbox"]').each(
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
			if(ids==''&&judgeString=='clearance'){
    			toastr.warning('未选择清关单！');return;
    		}else if(ids==''&&judgeString=='declaration'){
    			toastr.warning('未选择报关单！');return;
    		}else{
    			$("#del"+judgeString+"Modal").modal('show');// 弹出删除确认模态框
    		}
			
		};
		 $scope.confirmDelCustomsForm = function(judgeString) {										
			 customsFormService.delCustomsForm(ids)
			 .then(
					 function(data) {
						 $('#del'+judgeString+'Modal').modal('hide');// 删除成功后关闭模态框
						 toastr.success("删除成功！");
						 table.ajax.reload(); // 重新加载datatables数据
					 },
					 function(errResponse) {
						 console
						 .error('Error while deleting Users');
					 }

			 );
		 }
		 
		   	// 创建对象
	  	  var uploader = $scope.uploader = new FileUploader({url:'rest/fileOperate/uploadSingleFile'});
	  	 
	  	  uploader.onAfterAddingFile = function(item){
	  		  if(item.file.size>10000000){
	  			  // toastr.warning("文件大小超过10M！");
	  			  uploader.cancelAll();
	  		  }
	  	  }
	  	  // 添加文件到上传队列后
	  	  uploader.onCompleteAll = function () {
	  		  uploader.clearQueue();
	  	  };
	  	  // 上传成功
	  	  uploader.onSuccessItem = function (fileItem,response, status, headers) {
	  		  if (status == 200){ 
	  			  if(response==""){
	  				  toastr.error("上传失败！");
	  				  return;
	  			  }
	  		  		toastr.success("上传成功！");
	  		  		if(uploadSelectIndex=='electronicContract'||uploadSelectIndex=='signContract'){//合同附件
	  		  			$scope.contract[uploadSelectIndex] = response.filename;
	  		  		}else{//订单附件
	  		  			$scope.file[uploadSelectIndex].file = response.filename;
	  		  		}
	  		  }else{
	  			  toastr.error("上传失败！");
	  			if(uploadSelectIndex=='electronicContract'||uploadSelectIndex=='signContract'){//合同附件
 		  			$scope.contract[uploadSelectIndex] = response.filename;
 		  		}else{//订单附件
 		  			$scope.file[uploadSelectIndex].file = response.filename;
 		  		}
	  		  }
	  		};
	  	  // 上传失败
	  	  uploader.onErrorItem = function (fileItem, response, status, headers) {
	  			toastr.error("上传失败！");
	  	  };
	  	  

	       var uploadSelectIndex;
	  	  $scope.uploadFile = function(index){
	  		uploadSelectIndex = index;
	  	  }
	  	  
	  	  $scope.up = function(file){
	  		  uploader.clearQueue();
	  		  uploader.addToQueue(file);
	  		  uploader.uploadAll();
	  	  }
	       $scope.downloadFile = function(obj){
	    	   if(!handle.isNull(obj)){
	    		   window.location.href= $rootScope.basePath+"/rest/fileOperate/downloadFile?fileName="+encodeURI(encodeURI(obj));
	    	   }else{
	    		   toastr.error("下载失败!");
	    	   }
	       }
	       
	       $scope.removefile = function(index){
	    	   if(index=='electronicContract'||index=='signContract'){//合同附件
 		  			$scope.contract[index] = "";
 		  		}else{//订单附件
 		  			$scope.file[index].file = "";
 		  		}
	       }
	  	 //********附件  start****************//
	   		var _fileIndex = 0;
	   	    $scope.saveFile  = function() {//保存File信息
	   		  if($scope.customsForm==undefined||$scope.customsForm.serialNum==''||$scope.customsForm.serialNum==null) {//报关单/清关单
	   	    		toastr.error('请先保存订单信息！');return
	    		}
	   	    	debugger;
	   	    	if($('#form_sample_4').valid()){
	   	    		customsFormService.saveFile($scope.file).then(
	   	       		     function(data){
	   	       		    	toastr.success('数据保存成功！');
	   	       		    	$scope.cancelFile();
	   	       		    	
	   	       		     },
	   	       		     function(error){
	   	       		    	toastr.error('数据保存出错！');
	   	       		         $scope.error = error;
	   	       		     }
	   	       		 );
	   	    	}
	   	    	
	   	    }; 	
	   	    
	   	    $scope.cancelFile  = function() {//取消编辑File信息
	   	    	$scope.fileInfoInput = true;
	   		    $scope.fileInfoShow = true;
	   		     $scope.noShow== true;
	   	    };
	   	    
	   	    $scope.editFile  = function() {//进入编辑File信息
	   	    	$scope.fileInfoInput = false;
	   		    $scope.fileInfoShow = false;
	   	    };
		  /**
	 	        * File新增一行
	 	        */
	   	    $scope.addFile = function(){
	   	   	  if($scope.customsForm==undefined||$scope.customsForm.serialNum==''||$scope.customsForm.serialNum==null) {//报关单/清关单
	 	    		toastr.error('请先保存相关信息！');return
	 			}else{
	   		    	   if($scope.file){}else{$scope.file =[{}]}
	   		    	   $scope.file[_fileIndex] = {};
	   		    	   $scope.file[_fileIndex].relationSerial = $scope.customsForm.serialNum;
	   		    	   _fileIndex++;
	   		       }
	   	    };
	   	    
	   	    /**
		        * File删除一行
		        */
		       $scope.deleteFile = function(index){
		    	   $scope.file.splice(index,1);
		    	   _fileIndex--;
		       };
		       var validateFileInit = function() {
  		        	var e = $("#form_sample_4");
  			        r = $(".alert-danger", e),
  			        i = $(".alert-success", e);
  			        e.validate({
  			            errorElement: "span",
  			            errorClass: "help-block help-block-error",
  			            focusInvalid: !1,
  			            ignore: "",
  			            messages: {
  			            },
  		            	rules: {
  		            			
  		            			},
  		            		invalidHandler: function(e, t) {
  		                    i.hide(), r.show(), App.scrollTo(r, -200)
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
  			            }})
  		        };
	
    /**
	 * 加载海关单位数据
	 */
	var initSuppliers = function(){
		var promise = orderService.initSuppliers();
        	promise.then(function(data){
        		$scope.suppliers = data.data;
        		setTimeout(function () {
        			$("#agentUnit").selectpicker({
                        showSubtext: true
                    });
        			$('#agentUnit').selectpicker('refresh');//刷新插件
        			
                }, 100);
        		
        	},function(data){
        		//调用承诺接口reject();
        	});
	}
	$scope.cancelEditcustomsForm=function(){
		var data=$scope.copyCustomsForm ;
		$scope.customsForm=data;
		$scope.customsFormView =true;
		 $scope.customsFormAdd =true;
		 $scope.customsFormEdit =true;
	}
	$scope.editCustomsForm=function(){
		var data=$scope.customsForm ;
		$scope.copyCustomsForm=data;
		$scope.customsForm=data;
		$scope.customsFormView =false;
		 $scope.customsFormAdd =false;
		 $scope.customsFormEdit =false;
	}
	$scope.saveCustomsForm=function(judgeString){
		if(judgeString.indexOf("clearance")>-1){
			$scope.customsForm.customsFormType='clearance';
		}else if(judgeString.indexOf("declaration")>-1){
			$scope.customsForm.customsFormType='declaration';
		}
		
		if(judgeString.indexOf("confirm")>-1){
			$scope.customsForm.status='1';
		}
		if($('#customsForm').valid()){
		customsFormService.saveCustomsForm($scope.customsForm)
		 .then(
				 function(data) {
					 debugger;
					 if(judgeString=="clearance"){
						 toastr.success("保存清关单成功！");
					 }else  if(judgeString=="declaration"){
						 toastr.success("保存报关单成功！");
					 }else  if(judgeString.indexOf("clearance")>-1&&judgeString.indexOf('confirm')>-1){
						 toastr.success("确认清关成功！");
					 }else  if(judgeString=="declaration"&&judgeString.indexOf('confirm')>-1){
						 toastr.success("确认报关成功！");
					 }
					 $scope.customsForm =data;
					 $scope.customsFormEdit =true;
					 $scope.customsFormView =true;
					 $scope.customsFormAdd =true;
					 $(".alert-danger").hide();
				 },
				 function(errResponse) {
					 toastr.warning("保存错误！");
					 console
					 .error('Error while creating User');
				 }
		 );
		}
	 }
	function doSomthing(){
	 // 页面加载完成后调用，验证输入框
	 $scope.$watch('$viewContentLoaded', function() {  
		  var customsFormNum,agentUnit;
		 if($scope.customsFormType.indexOf("clearance")>-1){
			 customsFormNum= {required:"清关单号不能为空！"};
			 agentUnit={required:"代理清关单位不能为空！"} ;
		 }else{
			 customsFormNum= {required:"报关单号不能为空！"};
			 agentUnit={required:"代理报关单位不能为空！"} ;
		 }
		 var e = $("#customsForm"),
		 r = $(".alert-danger", e),
		 i = $(".alert-success", e);
		 e.validate({
			 errorElement: "span",
			 errorClass: "help-block help-block-error",
			 focusInvalid: !1,
			 ignore: "",
			 messages: {
				 customsFormNum:customsFormNum,
				 agentUnit:agentUnit,
				 deliverNum:{required:"发货单号不能为空！"}
			 },
			 rules: {
				 customsFormNum:{required:true},
				 agentUnit:{required:true},
				 deliverNum:{required:true}
				
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
	
}
	
	  /**
     * 下载EXCEL模板
     */
    $scope.downloadImportTemp = function(type){
 	   window.location.href=$rootScope.basePath+"/rest/customsForm/downloadImportTemp?type="+type;
    }
    
    /**
     * 上传EXCEL
     */
    $scope.uploadExcel = function(customsFormType){
    	debugger;
 	    var file = document.querySelector('input[type=file]').files[0];
 	    if(handle.isNull(file)){
 	    	toastr.warning("请选择Excel文件！");
 	    }
 	    var type = file.name.substring(file.name.lastIndexOf("."));
 	   if(type != ".xls"){
 		toastr.warning("文件格式不正确，需要xls类型的Excel文档");
 		   return;
 	   }
 	   	handle.blockUI("正在导入中，请不要进行其他操作"); 
 	   	var promise = customsFormService.uploadExcel(customsFormType);
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
    $scope.exportCustomsForm = function(type){
	    	 handle.blockUI("正在导出数据，请稍后"); 
	    	 window.location.href=$rootScope.basePath+"/rest/customsForm/exportCustomsForm?type="+type;
	    	 handle.unblockUI(); 
	       }
	       
    $('#import').on('hide.bs.modal', function (e) { 
 	   $("#resetFile").trigger("click");
 	  //$("#file_span input[type='file']").remove();
 	  //$(".fileinput-filename").val("");
 	  //$("#file_span").appendTo('<input type="file" file-model="excelFile" accept=".xls" name="...">');
    })
          
          
          
}]);


