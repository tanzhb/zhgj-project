/* Setup general page controller */
angular.module('MetronicApp').controller('materielController', ['$rootScope', '$scope', 'settings','materielService',
    '$state',"$stateParams",'$compile','$location', function($rootScope, $scope, settings,materielService,$state,$stateParams,$compile,$location) {
    $scope.$on('$viewContentLoaded', function() {   
    	// initialize core components
    	App.initAjax();

    	// set default layout mode
    	$rootScope.settings.layout.pageContentWhite = true;
        $rootScope.settings.layout.pageBodySolid = false;
        $rootScope.settings.layout.pageSidebarClosed = false;
        
        if($state.current.name=="materiel"){
        	loadMainTable();//加载物料列表
        	
        	loadTree();//加载物料树
        }else{
        	$('.date-picker').datepicker({
				rtl: App.isRTL(),
				orientation: "left",
				autoclose: true,
				dateFormat:"yyyy-mm-dd"

        	})//初始化日期控件
        	FormiCheck.init()//初始化checkbox控件
        	r();//加载表单验证控件
        	
        	//加载数据
        	if($stateParams.serialNum)$scope.getMaterielInfo($stateParams.serialNum)
        	
        	if($stateParams.view==1){//切换为查看
        		$scope.materielInput = true;
		    	$scope.materielShow = true;
		    	$scope.materielPackageInput = true;
   		    	$scope.materielPackageShow = true;
		    }
        	
        }
        
        
        
    });
    
    $scope.save  = function(isValid) {
    	if(isValid){
    		if($scope.materiel.manufactureDate=='') {//日期为空的处理
    			$scope.materiel.manufactureDate=null;
    		}
    		if($scope.materiel.parentMaterielSerial=='') {//上级物料为空的处理
    			$scope.materiel.parentMaterielSerial=null;
    		}
    		//TODO待删除 
    		$scope.materiel.parentMateriel=null;
    		$scope.materiel.createTime=null;
    		$scope.materiel.updateTime=null;
    		//**********//

    		materielService.save($scope.materiel).then(
       		     function(data){
       		    	$location.search({serialNum:data.serialNum,view:1});
       		    	/*$scope.materiel=data;
       		    	$scope.materielInput = true;
       		    	$scope.materielShow = true;*/
       		     },
       		     function(error){
       		         $scope.error = error;
       		     }
       		 );
    	}
    	
    }; 	
    
    $scope.cancel  = function() {//取消编辑
    	if($scope.materiel.serialNum==null || $scope.materiel.serialNum=='') {//如果是取消新增，返回列表页面
    		$state.go("materiel");
    		return;
		}
    	$scope.getMaterielInfo($scope.materiel.serialNum);
    	$scope.materielInput = true;
	    $scope.materielShow = true;
    };
    
    $scope.edit  = function() {//进入编辑
    	$scope.materielInput = false;
	    $scope.materielShow = false;
    };
    
    
    
    $scope.savePackage  = function(isValid) {//保存包装信息
    	if(isValid){
    		if($scope.materiel.manufactureDate=='') {//日期为空的处理
    			$scope.materiel.manufactureDate=null;
    		}
    		if($scope.materiel.parentMaterielSerial=='') {//上级物料为空的处理
    			$scope.materiel.parentMaterielSerial=null;
    		}
    		
    		$scope.materiel.parentMateriel=null
    		//TODO待删除 
    		$scope.materiel.createTime=null;
    		$scope.materiel.updateTime=null;
    		//**********//

    		materielService.save($scope.materiel).then(
       		     function(data){
       		    	$location.search({serialNum:data.serialNum,view:1});
       		    	/*$scope.materiel=data;
       		    	$scope.materielPackageInput = true;
       		    	$scope.materielPackageShow = true;*/
       		     },
       		     function(error){
       		         $scope.error = error;
       		     }
       		 );
    	}
    	
    }; 	
    
    $scope.cancelPackage  = function() {//取消编辑包装信息
    	$scope.getMaterielInfo($scope.materiel.serialNum);
    	$scope.materielPackageInput = true;
	    $scope.materielPackageShow = true;
    };
    
    $scope.editPackage  = function() {//进入编辑包装信息
    	$scope.materielPackageInput = false;
	    $scope.materielPackageShow = false;
    };
    
    
	var initList = function(start,limit) {
    	materielService.findList(start,limit).then(
    		     function(data){
    		    	 $scope.materielList = data;
    		     },
    		     function(error){
    		         $scope.error = error;
    		     }
    		 );
    	};
    	
    $scope.getMaterielInfo  = function(serialNum) {
    	materielService.getMaterielInfo(serialNum).then(
      		     function(data){
      		    	$scope.materiel=data;
      		     },
      		     function(error){
      		         $scope.error = error;
      		     }
      		 );
    	
    }; 
    var table;
    var tableAjaxUrl = "rest/materiel/findMaterielList";
    var loadMainTable = function() {
            a = 0;
            App.getViewPort().width < App.getResponsiveBreakpoint("md") ? $(".page-header").hasClass("page-header-fixed-mobile") && (a = $(".page-header").outerHeight(!0)) : $(".page-header").hasClass("navbar-fixed-top") ? a = $(".page-header").outerHeight(!0) : $("body").hasClass("page-header-fixed") && (a = 64);
            table = $("#sample_2")
			.DataTable({
                language: {
                    aria: {
                        sortAscending: ": activate to sort column ascending",
                        sortDescending: ": activate to sort column descending"
                    },
                    emptyTable: "空表",
                    info: "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
                    infoEmpty: "没有数据",
                    //infoFiltered: "(filtered1 from _MAX_ total entries)",
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
/*                fixedHeader: {//固定表头、表底
                    header: !0,
                    footer: !0,
                    headerOffset: a
                },*/
                order: [[1, "asc"]],//默认排序列及排序方式
                searching: true,//是否过滤检索
                ordering:  true,//是否排序
                lengthMenu: [[5, 10, 15, 30, -1], [5, 10, 15, 30, "All"]],
                pageLength: 5,//每页显示数量
                processing: true,//loading等待框
//                serverSide: true,
                ajax: tableAjaxUrl,//加载数据中
                "aoColumns": [
                              { mData: 'serialNum' },
                              { mData: 'materielNum' },
                              { mData: 'materielName' },
                              { mData: 'specifications' },
                              { mData: 'unit' },
                              { mData: 'parentMateriel' },
                              { mData: 'type' },
                              { mData: 'productionPlace' },
                              { mData: 'brand' },
                              { mData: 'brand' },
                              { mData: 'versionNO' },
                              { mData: 'status' }
                        ],
               'aoColumnDefs' : [ {
							'targets' : 0,
							'searchable' : false,
							'orderable' : false,
							'className' : 'dt-body-center',
							'render' : function(data,
									type, full, meta) {
								return '<input type="checkbox" ng-click="getMaterielInfo(\''+data+'\')" name="serialNum[]" value="'
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
							'className' : 'dt-body-center',
							'render' : function(data,
									type, row, meta) {
								return '<a data-target="#basicMaterielInfo" data-toggle="modal" ng-click="getMaterielInfo(\''+row.serialNum+'\')" ">'+data+'</a>';
							},
							"createdCell": function (td, cellData, rowData, row, col) {
								 $compile(td)($scope);
						       }
						},{
							'targets' : 5,
							'className' : 'dt-body-center',
							'render' : function(data,
									type, full, meta) {
								if(data==null){
									return  ''
								}else{
									return  data.materielName
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
			$('#sample_2 tbody')
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
        };
        
        
        var loadTree = function() {
            $("#tree_1").jstree({
                core: {
                    themes: {
                        responsive: !1
                    },
                    data: {
                        url: function(e) {
                            return "rest/materiel/findMaterielTree"
                        },
                        data: function(e) {
                            return {
                                parent: e.id
                            }
                        }
                    }
                },
                types: {
                    "default": {
                        icon: "fa fa-folder icon-state-warning icon-lg"
                    },
                    file: {
                        icon: "fa fa-file icon-state-warning icon-lg"
                    }
                },
                plugins: ["types"]
            }),
            $("#tree_1").on("select_node.jstree", function(e, t) {
            	table.ajax.url(tableAjaxUrl+"?parent="+t.selected[0]).load()// 重新加载datatables数据
            })
            
        };
        $scope.reloadTable = function() {
        	table.ajax.url(tableAjaxUrl).load()// 重新加载datatables数据
        }
        
        //弹出确认删除模态框
        $scope.deleteMateriel = function() {
			var ids = '';
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
			if(ids==''){
    			toastr.warning('未选择物料！');return;
    		}else{
    			$('#addUserModal').modal('show');// 弹出删除确认模态框
    		}
			
		};
        
     // 删除开始***************************************
		$scope.del = function() {
			var ids = '';
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
			materielService
					.delMateriel(ids)
					.then(
							function(data) {
								$('#delMaterielModal').modal(
										'hide');// 删除成功后关闭模态框
								$(".modal-backdrop").remove();
								/*table.ajax.reload(); // 重新加载datatables数据*/
								 $state.go('materiel',{},{reload:true});
							},
							function(errResponse) {
								console
										.error('Error while deleting Users');
							}

					);
		};
		// 删除结束***************************************
        
        var r = function() {
        	var e = $("#form_sample_1"),
	        r = $(".alert-danger", e),
	        i = $(".alert-success", e);
	        e.validate({
	            errorElement: "span",
	            errorClass: "help-block help-block-error",
	            focusInvalid: !1,
	            ignore: "",
            	rules: {materielNum: {required: !0,maxlength: 20},
            			type: {required: !0,maxlength: 20},
            			materielName: {required: !0,maxlength: 20},
            			category: {required: !0,maxlength: 20},
            			specifications: {required: !0,maxlength: 20},
            			stockUnit: {required: !0,maxlength: 20}/*,
            			packageNum: {required: !0,maxlength: 20},
            			packageSpecifications: {required: !0,maxlength: 20},
            			packageUnit: {required: !0,maxlength: 20},
            			packageUnitConversion: {required: !0,maxlength: 20,number:!0}*/
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

        
        $scope.editMateriel  = function() {//进入编辑页面
        	var ids = '';
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
    							} else{
    								ids = "more"
    							}
    						}
    					}
    				});
    		if(ids==''){
    			toastr.warning('请选择一个物料！');return;
    		}else if(ids=='more'){
    			toastr.warning('只能选择一个物料！');return;
    		}
    		
    		$state.go("addMateriel",{serialNum:ids});
        };
      
        var selectParentTable;
        $scope.selectParentMateriel = function() {
                a = 0;
                App.getViewPort().width < App.getResponsiveBreakpoint("md") ? $(".page-header").hasClass("page-header-fixed-mobile") && (a = $(".page-header").outerHeight(!0)) : $(".page-header").hasClass("navbar-fixed-top") ? a = $(".page-header").outerHeight(!0) : $("body").hasClass("page-header-fixed") && (a = 64);
                selectParentTable = $("#select_sample_2")
    			.DataTable({
                    language: {
                        aria: {
                            sortAscending: ": activate to sort column ascending",
                            sortDescending: ": activate to sort column descending"
                        },
                        emptyTable: "空表",
                        info: "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
                        infoEmpty: "没有数据",
                        //infoFiltered: "(filtered1 from _MAX_ total entries)",
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
    /*                fixedHeader: {//固定表头、表底
                        header: !0,
                        footer: !0,
                        headerOffset: a
                    },*/
                    order: [[1, "asc"]],//默认排序列及排序方式
                    searching: true,//是否过滤检索
                    ordering:  true,//是否排序
                    lengthMenu: [[5, 10, 15, 30, -1], [5, 10, 15, 30, "All"]],
                    pageLength: 5,//每页显示数量
                    processing: true,//loading等待框
//                    serverSide: true,
                    ajax: "rest/materiel/findMaterielList?isLatestVersion=1",//加载数据中
                    "aoColumns": [
                                  { mData: 'serialNum' },
                                  { mData: 'materielNum' },
                                  { mData: 'materielName' },
                                  { mData: 'specifications' },
                                  { mData: 'unit' },
                                  { mData: 'parentMateriel' },
                                  { mData: 'type' },
                                  { mData: 'productionPlace' },
                                  { mData: 'brand' },
                                  { mData: 'brand' },
                                  { mData: 'versionNO' },
                                  { mData: 'status' }
                            ],
                   'aoColumnDefs' : [ {
    							'targets' : 0,
    							'searchable' : false,
    							'orderable' : false,
    							'className' : 'dt-body-center',
    							'render' : function(data,
    									type, row, meta) {
    								return '<input type="radio" ng-click="selectParent(\''+data+'\',\''+row.materielName+'\')" name="serialNum[]" value="'
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
    							'targets' : 5,
    							'className' : 'dt-body-center',
    							'render' : function(data,
    									type, full, meta) {
    								if(data==null){
    									return  ''
    								}else{
    									return  data.materielName
    								}
    							}
    						} ]

                }).on('order.dt',
                function() {
                    console.log('排序');
                })
            };
            
            $scope.selectParent  = function(serialNum,materielName) {
            	$scope.row = {};
            	$scope.row.serialNum = serialNum;
            	$scope.row.parentMateriel = {};
            	$scope.row.parentMateriel.materielName = materielName;
            }; 
         // 确认选择开始***************************************
    		$scope.confirmSelect = function() {
    			var ids = '';
    			// Iterate over all checkboxes in the table
    			selectParentTable.$('input[type="radio"]').each(
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
    			
    			$scope.materiel.parentMaterielSerial=$scope.row.serialNum;
    			$scope.materiel.parentMateriel = {};
    			$scope.materiel.parentMateriel.materielName=$scope.row.parentMateriel.materielName;
    			
    			
    			$('#basicMaterielInfo').modal('hide');// 删除成功后关闭模态框
    			$(".modal-backdrop").remove();
    		};
        	
}]);
