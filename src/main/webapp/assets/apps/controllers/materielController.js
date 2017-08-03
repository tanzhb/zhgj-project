/* Setup general page controller */
angular.module('MetronicApp').controller('materielController', ['$rootScope', '$scope', 'settings','materielService','$state', function($rootScope, $scope, settings,materielService,$state) {
    $scope.$on('$viewContentLoaded', function() {   
    	// initialize core components
    	App.initAjax();

    	// set default layout mode
    	$rootScope.settings.layout.pageContentWhite = true;
        $rootScope.settings.layout.pageBodySolid = false;
        $rootScope.settings.layout.pageSidebarClosed = false;
        
        if($state.current.name=="materiel"){
        	loadMainTable();
        	loadTree();
        }else{
        	$('.date-picker').datepicker({
				rtl: App.isRTL(),
				orientation: "left",
				autoclose: true
        	})
        	FormiCheck.init()
        	r();
        }
        
        
        
    });
    
    $scope.save  = function(isValid) {
    	if(isValid){
    		if($scope.materiel.manufactureDate=='') {
    			$scope.materiel.manufactureDate=null;
    		}
    		materielService.save($scope.materiel).then(
       		     function(answer){
       		    	 $state.go('materiel');
       		     },
       		     function(error){
       		         $scope.error = error;
       		     }
       		 );
    	}
    	
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
                fixedHeader: {//固定表头、表底
                    header: !0,
                    footer: !0,
                    headerOffset: a
                },
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
								return '<input type="checkbox" name="serialNum[]" value="'
										+ $('<div/>')
												.text(
														data)
												.html()
										+ '">';
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
        
        
     // 删除用户开始***************************************
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
		// 删除用户结束***************************************
        
        var r = function() {
            var e = $("#form_sample_2"), r = $(".alert-danger", e), i = $(".alert-success", e);
            e.validate({errorElement: "span",errorClass: "help-block help-block-error",focusInvalid: !1,ignore: "",
            	rules: {materielNum: {required: !0,maxlength: 20},
            			type: {required: !0,maxlength: 20},
            			materielName: {required: !0,maxlength: 20},
            			category: {required: !0,maxlength: 20},
            			specifications: {required: !0,maxlength: 20},
            			stockUnit: {required: !0,maxlength: 20}
            			},
            		invalidHandler: function(e, t) {
                    i.hide(), r.show(), App.scrollTo(r, -200)
                },errorPlacement: function(e, r) {
                    var i = $(r).parent(".input-icon").children("i");
                    i.removeClass("fa-check").addClass("fa-warning"), i.attr("data-original-title", e.text()).tooltip({container: "body"})
                },highlight: function(e) {
                    $(e).closest(".form-group").removeClass("has-success").addClass("has-error")
                },unhighlight: function(e) {
                },success: function(e, r) {
                    var i = $(r).parent(".input-icon").children("i");
                    $(r).closest(".form-group").removeClass("has-error").addClass("has-success"), i.removeClass("fa-warning").addClass("fa-check")
                },submitHandler: function(e) {
                    i.show(), r.hide()
                }})
        }

}]);
