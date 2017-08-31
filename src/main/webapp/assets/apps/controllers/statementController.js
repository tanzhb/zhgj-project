/* Setup general page controller */
angular.module('MetronicApp').controller('statementController', ['$rootScope', '$scope', 'settings','statementService','$filter',
    '$state',"$stateParams",'$compile','$location', function($rootScope, $scope, settings,statementService,$filter,$state,$stateParams,$compile,$location) {
    $scope.$on('$viewContentLoaded', function() {   
    	// initialize core components
    	App.initAjax();
    	handle = new pageHandle();
    	// set default layout mode
    	$rootScope.settings.layout.pageContentWhite = true;
        $rootScope.settings.layout.pageBodySolid = false;
        $rootScope.settings.layout.pageSidebarClosed = false;
        if($state.current.name=="statement"){
        	loadSupplyMainTable();// 加载供应商对账单列表
        	loadBuyMainTable();// 加载采购商对账单列表
        	}else{
            	$('.date-picker').datepicker({
    				rtl: App.isRTL(),
    				orientation: "left",
    				autoclose: true,
    				dateFormat:"yyyy-mm-dd",
    				language: "zh-CN"
            	})
            	// 初始化日期控件
            	     	
            	$scope.opration = {};
            	$scope.serialNums = [];
            	// 加载数据
            	if($stateParams.serialNum){
            		$scope.opration = '修改';
            		$scope.getStatement($stateParams.serialNum)
            	}else{
            		$scope.opration = '新增';
            		$scope.statementMateriel={};
            	}
            	$scope.noShow = true;
            	if($stateParams.view==1){// 对账单切换为查看
            		$scope.statementInput = true;
    		    	$scope.statementShow = true;
       		    	$scope.opration = '查看';
    		    }
            	
            	validateInit();// 加载表单验证控件
        	}
    });
    
   
    $scope.save  = function() {
    	if($('#form_sample_1').valid()){
    		if($scope.statement.statementDate=='') {// 日期为空的处理
    			$scope.statement.statementDate=null;
    		}

    		// 保存数据处理
// $scope.statement.parentStatement=null;
// $scope.statement.createTime=null;
// $scope.statement.updateTime=null;
    		// **********//

    		statementService.save($scope.statement).then(
       		     function(data){
       		    	toastr.success('数据保存成功！');
       		    	$location.search({serialNum:data.serialNum,view:1});
       		    	$scope.statementInput = true;
       			    $scope.statementShow = true;
       		     },
       		     function(error){
       		         $scope.error = error;
       		         toastr.error('数据保存出错！');
       		     }
       		 );
    	}
    	
    }; 	
    
    $scope.cancel  = function() {// 取消编辑
    	if($scope.statement.serialNum==null || $scope.statement.serialNum=='') {// 如果是取消新增，返回列表页面
    		$state.go("statement");
    		return;
		}
    	$scope.getStatement($scope.statement.serialNum);
    	$scope.cancelStatement();
    	
    };
    $scope.cancelStatement  = function() {// 取消编辑对账单信息
    	$scope.statementInput = true;
	    $scope.statementShow = true;
    };
    
    $scope.edit  = function() {// 进入编辑
    	$scope.statementInput = false;
	    $scope.statementShow = false;
    };
    
    var buyTable;
    var loadBuyMainTable = function() {
            a = 0;
            App.getViewPort().width < App.getResponsiveBreakpoint("md") ? $(".page-header").hasClass("page-header-fixed-mobile") && (a = $(".page-header").outerHeight(!0)) : $(".page-header").hasClass("navbar-fixed-top") ? a = $(".page-header").outerHeight(!0) : $("body").hasClass("page-header-fixed") && (a = 64);
            buyTable = $("#sample_2")
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
                statement: [[1, "asc"]],// 默认排序列及排序方式
                searching: true,// 是否过滤检索
                statementing:  true,// 是否排序
                lengthMenu: [[5, 10, 15, 30, -1], [5, 10, 15, 30, "All"]],
                pageLength: 5,// 每页显示数量
                processing: true,// loading等待框
// serverSide: true,
                ajax: 'rest/statement/findStatementList?type=buy',// 加载数据中
                "aoColumns": [
                              { mData: 'serialNum' },
                              { mData: 'statementNum' },
                              { mData: 'statementDate' },
                              { mData: 'buyComId' },
                              { mData: 'totalAmount' },
                              { mData: 'deliveryAmount' },
                              { mData: 'paymentAmount' },
                              { mData: 'serviceAmount' },
                              { mData: 'reciveDate' },
                              { mData: 'status' }
                        ],
               'aoColumnDefs' : [ {
							'targets' : 0,
							'searchable' : false,
							'orderable' : false,
							'render' : function(data,
									type, full, meta) {
								return '<input type="checkbox" id="'+data+'" ng-click="getStatement_(\''+data+'\')" name="serialNum[]" value="'
													+ $('<div/>')
													.text(
															data)
													.html()
											+ '">';
							},
							"createdCell": function (td, cellData, rowData, row, col) {
								 $compile(td)($scope);
						       }
						} ]

            }).on('statement.dt',
            function() {
                console.log('排序');
            })
            
            
            
            
            // 添加checkbox功能***************************************
			// Handle click on "Select all" control
			$('#example-select-buy').on(
					'click',
					function() {
						// Check/uncheck all checkboxes in the
						// buyTable
						var rows = buyTable.rows({
							'search' : 'applied'
						}).nodes();
						$('input[type="checkbox"]', rows).prop(
								'checked', this.checked);
					});
	
			// Handle click on checkbox to set state of "Select
			// all" control
			$('#sample_2 tbody')
					.on(
							'change',
							'input[type="checkbox"]',
							function() {
								// If checkbox is not checked
								if (!this.checked) {
									var el = $(
											'#example-select-buy')
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
        };
        
        
        var supplyTable;
        var loadSupplyMainTable = function() {
                a = 0;
                App.getViewPort().width < App.getResponsiveBreakpoint("md") ? $(".page-header").hasClass("page-header-fixed-mobile") && (a = $(".page-header").outerHeight(!0)) : $(".page-header").hasClass("navbar-fixed-top") ? a = $(".page-header").outerHeight(!0) : $("body").hasClass("page-header-fixed") && (a = 64);
                supplyTable = $("#sample_1")
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
                    statement: [[1, "asc"]],// 默认排序列及排序方式
                    searching: true,// 是否过滤检索
                    statementing:  true,// 是否排序
                    lengthMenu: [[5, 10, 15, 30, -1], [5, 10, 15, 30, "All"]],
                    pageLength: 5,// 每页显示数量
                    processing: true,// loading等待框
    // serverSide: true,
                    ajax: 'rest/statement/findStatementList?type=supply',// 加载数据中
                    "aoColumns": [
                                  { mData: 'serialNum' },
                              { mData: 'statementNum' },
                              { mData: 'statementDate' },
                              { mData: 'supplyComId' },
                              { mData: 'totalAmount' },
                              { mData: 'deliveryAmount' },
                              { mData: 'paymentAmount' },
                              { mData: 'serviceAmount' },
                              { mData: 'reciveDate' },
                              { mData: 'status' }

                            ],
                   'aoColumnDefs' : [ {
    							'targets' : 0,
    							'searchable' : false,
    							'orderable' : false,
    							'render' : function(data,
    									type, full, meta) {
    								return '<input type="checkbox" id="'+data+'" ng-click="getStatement_(\''+data+'\')" name="serialNum[]" value="'
    													+ $('<div/>')
    													.text(
    															data)
    													.html()
    											+ '">';
    							},
    							"createdCell": function (td, cellData, rowData, row, col) {
    								 $compile(td)($scope);
    						       }
    						} ]

                }).on('statement.dt',
                function() {
                    console.log('排序');
                })
                
                
                
                
                // 添加checkbox功能***************************************
    			// Handle click on "Select all" control
    			$('#example-select-supply').on(
    					'click',
    					function() {
    						// Check/uncheck all checkboxes in the
    						// supplyTable
    						var rows = supplyTable.rows({
    							'search' : 'applied'
    						}).nodes();
    						$('input[type="checkbox"]', rows).prop(
    								'checked', this.checked);
    					});
    	
    			// Handle click on checkbox to set state of "Select
    			// all" control
    			$('#sample_1 tbody')
    					.on(
    							'change',
    							'input[type="checkbox"]',
    							function() {
    								// If checkbox is not checked
    								if (!this.checked) {
    									var el = $(
    											'#example-select-supply')
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
            };     
        // 弹出确认删除模态框
        $scope.deleteSupplyStatement = function() {
			var ids = '';
			$scope.deleteType = 'supply';
			// Iterate over all checkboxes in the supplyTable
			supplyTable.$('input[type="checkbox"]').each(
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
    			toastr.warning('未选择对账单！');return;
    		}else{
    			$('#delStatementModal').modal('show');// 弹出删除确认模态框
    		}
			
		};
		
		
        // 弹出确认删除模态框
        $scope.deleteBuyStatement = function() {
			var ids = '';
			$scope.deleteType = 'buy';
			// Iterate over all checkboxes in the supplyTable
			buyTable.$('input[type="checkbox"]').each(
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
    			toastr.warning('未选择对账单！');return;
    		}else{
    			$('#delStatementModal').modal('show');// 弹出删除确认模态框
    		}
			
		};
		
		$scope.editStatement  = function() {// 进入编辑页面
        	var ids = '';
    		// Iterate over all checkboxes in the supplyTable
    		supplyTable.$('input[type="checkbox"]').each(
    				function() {
    					// If checkbox exist in DOM
    					if ($.contains(document, this)) {
    						// If checkbox is checked
    						if (this.checked) {
    							// 将选中数据id放入ids中
    							if (ids == '') {
    								ids = this.value;
    							} else{
    								ids = "more"
    							}
    						}
    					}
    				});
    		if(ids==''){
    			toastr.warning('请选择一个对账单！');return;
    		}else if(ids=='more'){
    			toastr.warning('只能选择一个对账单！');return;
    		}
    		
    		$state.go("addStatement",{serialNum:ids});
        };
        
     // 删除开始***************************************
		$scope.del = function() {
			var ids = '';
			if($scope.deleteType=='supply'){
				// Iterate over all checkboxes in the supplyTable
				supplyTable.$('input[type="checkbox"]').each(
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
			}else{
				// Iterate over all checkboxes in the buyTable
				buyTable.$('input[type="checkbox"]').each(
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
			}
			
			statementService
					.delStatement(ids)
					.then(
							function(data) {
								$('#delStatementModal').modal(
										'hide');// 删除成功后关闭模态框
								$(".modal-backdrop").remove();
								/* supplyTable.ajax.reload(); // 重新加载datatables数据 */
								toastr.success('数据删除成功！');
								 $state.go('statement',{},{reload:true});
								 
							},
							function(errResponse) {
								toastr.error('数据删除失败！');
								console
										.error('Error while deleting Users');
							}

					);
		};
		// 删除结束***************************************
        
		
		
		var validateInit = function() {
        	var e = $("#form_sample_1"),
	        r = $(".alert-danger", e),
	        i = $(".alert-success", e);
	        e.validate({
	            errorElement: "span",
	            errorClass: "help-block help-block-error",
	            focusInvalid: !1,
	            ignore: "",
	            messages: {
	            	statementNum:{required:"对账单号不能为空！"},
	            	statementType:{required:"销售类型不能为空！"},
	            	buyComId:{required:"采购方不能为空！"},
	            	serviceModel:{required:"服务模式不能为空！"},
	            	settlementClause:{required:"结算条款不能为空！"},
	            	deliveryMode:{required:"提货方式不能为空！"},
	            	rate:{required:"税率不能为空！"},
	            	currency:{required:"币种不能为空！"}
	            },
            	rules: {statementNum: {required: !0,maxlength: 20},
            		statementType: {required: !0,maxlength: 20},
            		buyComId: {required: !0,maxlength: 20},
            		serviceModel: {required: !0,maxlength: 20},
            		settlementClause: {required: !0,maxlength: 20},
            		deliveryMode: {required: !0,maxlength: 20},
            		rate: {required: !0,maxlength: 20},
            		currency: {required: !0,maxlength: 20}
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
		 * 获取对账单信息
		 */	
        $scope.getStatement  = function(serialNum) {
        	statementService.getStatement(serialNum).then(
          		     function(data){//加载页面对象
          		    	$scope.statement=data.statementInfo;
          		     },
          		     function(error){
          		         $scope.error = error;
          		     }
          		 );
        	
        }; 
		    
		    //********导入导出start****************//
	 	      /**
		        * 下载EXCEL模板
		        */
		       $scope.downloadImportTemp = function(){
		    	   window.location.href=$rootScope.basePath+"/rest/statement/downloadImportTemp?type=buy";
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
		    	   	var promise = statementService.buyUploadExcel();
	       			promise.then(function(data){
	       				handle.unblockUI(); 
	       				if(data.data.data=="success"){
	       					toastr.success("导入成功");
	       					supplyTable.ajax.reload();
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
		       $('#import').on('hide.bs.modal', function (e) { 
		    	   $("#resetFile").trigger("click");
		       })
		       
		       
		       $scope.exportStatement = function(){
			    	 handle.blockUI("正在导出数据，请稍后"); 
			    	 window.location.href=$rootScope.basePath+"/rest/statement/exportStatement?type=buy";
			    	 handle.unblockUI(); 
			   }
		       //********导入导出end****************//
    	 
}]);
