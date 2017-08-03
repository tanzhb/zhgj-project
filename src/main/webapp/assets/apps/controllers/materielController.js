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
        	
        	FormValidation.init();
        	FormiCheck.init()
        }
        
        
        
    });
    
    $scope.save  = function(isValid) {
    	if(isValid)
    	materielService.save($scope.materiel).then(
    		     function(answer){
    		    	 $state.go('materiel');
    		     },
    		     function(error){
    		         $scope.error = error;
    		     }
    		 );
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
    	
    var parent = "#";	
    var table;
    var loadMainTable = function() {
            table = $("#sample_2"),
            a = 0;
            App.getViewPort().width < App.getResponsiveBreakpoint("md") ? $(".page-header").hasClass("page-header-fixed-mobile") && (a = $(".page-header").outerHeight(!0)) : $(".page-header").hasClass("navbar-fixed-top") ? a = $(".page-header").outerHeight(!0) : $("body").hasClass("page-header-fixed") && (a = 64);
            table.DataTable({
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
                ajax: "rest/materiel/findMaterielList",//加载数据中
                data : {parent : parent},
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
                        ]

            }).on('order.dt',
            function() {
                console.log('排序');
            })
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
            	parent = t.selected[0];
            	table.ajax.reload(); // 重新加载datatables数据
            })
            
        };

}]);
