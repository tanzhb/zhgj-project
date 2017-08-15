/***
Metronic AngularJS App Main Script
***/

/* Metronic App */
//定义模块时引入依赖
var MetronicApp = angular.module("MetronicApp", [
    "ui.router",
    "ui.bootstrap",
    "oc.lazyLoad",
    "ngSanitize"
]);

/* Configure ocLazyLoader(refer: https://github.com/ocombe/ocLazyLoad) */
MetronicApp.config(['$ocLazyLoadProvider', function($ocLazyLoadProvider) {
    $ocLazyLoadProvider.config({
        // global configs go here
    });
}]);

//AngularJS v1.3.x workaround for old style controller declarition in HTML
MetronicApp.config(['$controllerProvider', function($controllerProvider) {
  // this option might be handy for migrating old apps, but please don't use it
  // in new ones!
  $controllerProvider.allowGlobals();
}]);

/********************************************
 END: BREAKING CHANGE in AngularJS v1.3.x:
*********************************************/

/* Setup global settings */
MetronicApp.factory('settings', ['$rootScope', function($rootScope) {
    // supported languages
    var settings = {
        layout: {
            pageSidebarClosed: false, // sidebar menu state
            pageContentWhite: true, // set page content layout
            pageBodySolid: false, // solid body color state
            pageAutoScrollOnLoad: 1000 // auto scroll to top on page load
        },
        assetsPath: 'assets',
        globalPath: 'assets/global',
        layoutPath: 'assets/layouts/layout2',
    };

    $rootScope.settings = settings;
    $rootScope.basePath = getRootPath();

    return settings;
}]);

/* Setup App Main Controller */
MetronicApp.controller('AppController', ['$scope', '$rootScope', function($scope, $rootScope) {
    $scope.$on('$viewContentLoaded', function() {
        //App.initComponents(); // init core components
        //Layout.init(); //  Init entire layout(header, footer, sidebar, etc) on page load if the partials included in server side instead of loading with ng-include directive
    });
}]);

/***
Layout Partials.
By default the partials are loaded through AngularJS ng-include directive. In case they loaded in server side(e.g: PHP include function) then below partial
initialization can be disabled and Layout.init() should be called on page load complete as explained above.
***/

/* Setup Layout Part - Header */
MetronicApp.controller('HeaderController', ['$scope', function($scope) {
    $scope.$on('$includeContentLoaded', function() {
        Layout.initHeader(); // init header
    });
}]);

/* Setup Layout Part - Sidebar */
MetronicApp.controller('SidebarController', ['$state', '$scope', function($state, $scope) {
    $scope.$on('$includeContentLoaded', function() {
        Layout.initSidebar($state); // init sidebar
    });
}]);

/* Setup Layout Part - Quick Sidebar */
MetronicApp.controller('QuickSidebarController', ['$scope', function($scope) {
    $scope.$on('$includeContentLoaded', function() {
       setTimeout(function(){
            QuickSidebar.init(); // init quick sidebar
        }, 2000)
    });
}]);

/* Setup Layout Part - Theme Panel */
MetronicApp.controller('ThemePanelController', ['$scope', function($scope) {
    $scope.$on('$includeContentLoaded', function() {
        Demo.init(); // init theme panel
        //设置默认样式
        $('.layout-option', $('.theme-panel')).val("fluid");//设置布局为顺序
        $('.sidebar-style-option', $('.theme-panel')).val("compact");//工具栏风格为紧凑
        $('.layout-option').trigger("change");
    });
}]);

/* Setup Layout Part - Footer */
MetronicApp.controller('FooterController', ['$scope', function($scope) {
    $scope.$on('$includeContentLoaded', function() {
        Layout.initFooter(); // init footer
    });
}]);

/* Setup Rounting For All Pages */
MetronicApp.config(['$stateProvider', '$urlRouterProvider', function($stateProvider, $urlRouterProvider) {
    // Redirect any unmatched url
    $urlRouterProvider.otherwise("/dashboard");


    


    $stateProvider.state('dashboard', {
            url: "/dashboard",
            templateUrl: "rest/page/dashboard",
            data: {pageTitle: '首页'},
            controller: "DashboardController",
            resolve: {
                deps: ['$ocLazyLoad', function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        name: 'MetronicApp',
                        insertBefore: '#ng_load_plugins_before', // load the above css files before a LINK element with this ID. Dynamic CSS files must be loaded between core and theme css files
                        files: [
                            'assets/global/plugins/morris/morris.css',
                            'assets/global/plugins/morris/morris.min.js',
                            'assets/global/plugins/morris/raphael-min.js',
                            'assets/global/plugins/jquery.sparkline.min.js',

                            'assets/pages/scripts/dashboard.min.js',
                            'assets/apps/controllers/DashboardController.js',
                        ]
                    });
                }]
            }
        })
        // Blank Page
        .state('blank', {
            url: "/blank",
            templateUrl: "rest/page/blank",
            data: {pageTitle: '空白页'},
            controller: "BlankController",
            resolve: {
                deps: ['$ocLazyLoad', function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        name: 'MetronicApp',
                        insertBefore: '#ng_load_plugins_before', // load the above css files before a LINK element with this ID. Dynamic CSS files must be loaded between core and theme css files
                        files: [
                            'assets/apps/controllers/BlankController.js'
                        ]
                    });
                }]
            }
        })

        // 价格目录
        .state('priceList', {
            url: "/priceList",
            templateUrl: "rest/priceList/viewPriceList",
            data: {pageTitle: '价格目录'},
            controller: "PriceListController",
            resolve: {
                deps: ['$ocLazyLoad', function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        name: 'MetronicApp',
                        insertBefore: '#ng_load_plugins_before', // load the above css files before '#ng_load_plugins_before' 
                        files: [
                                'assets/global/plugins/jstree/dist/themes/default/style.min.css',
  							  'assets/global/plugins/jstree/dist/jstree.min.js',
                              'assets/pages/scripts/ui-tree.min.js',
  							 'assets/apps/scripts/pageHandle.js',
  							 'assets/global/plugins/bootstrap-toastr/toastr.js',
          				        'assets/global/plugins/bootstrap-toastr/toastr.css',
                             'assets/global/plugins/datatables/datatables.min.css', 
                             'assets/global/plugins/datatables/datatables.min.js',
  	                        'assets/apps/service/PriceListService.js',
  	                        'assets/apps/controllers/PriceListController.js'
                        ]
                    });
                }]
            }
        })
        
          .state('addPriceList', {
            url: "/addPriceList?:priceListSerialNum",
            templateUrl: "rest/priceList/addOrEditPriceListInfo",
            data: {pageTitle: '新增价格'},
            controller: "PriceListController",
            resolve: {
                deps: ['$ocLazyLoad', function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        name: 'MetronicApp',
                        insertBefore: '#ng_load_plugins_before', // load the above css files before '#ng_load_plugins_before'
                        files: [                             
                            'assets/global/plugins/datatables/datatables.min.css', 
    				        'assets/global/plugins/bootstrap-paginator/bootstrap-paginator.js',
    				        'assets/global/plugins/datatables/datatables.all.min.js',//
    				        'assets/global/plugins/bootstrap-toastr/toastr.js',
    				        'assets/global/plugins/bootstrap-toastr/toastr.css',
    				        'assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.js',
    				        'assets/global/plugins/jquery-repeater/jquery.repeater.js',
    				        'assets/pages/scripts/form-repeater.min.js',
    				        'assets/pages/scripts/form-repeater.js',
    				        'assets/apps/controllers/PriceListController.js',
    				        'assets/apps/service/PriceListService.js',
    				        'assets/global/plugins/bootbox/bootbox.min.js',
    				        'assets/pages/scripts/ui-bootbox.min.js',
    				        'assets/global/plugins/jquery.blockui.min.js',
    				        'assets/pages/scripts/ui-blockui.min.js',
    				        'assets/apps/scripts/pageHandle.js',
    				        'assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.css',
    				        'assets/global/plugins/bootstrap/css/bootstrap.min.css',
    				        'assets/global/css/components.min.css',
    				        'assets/global/css/plugins.min.css',
    				        'assets/global/plugins/font-awesome/css/font-awesome.min.css',
    				        'assets/global/plugins/simple-line-icons/simple-line-icons.min.css',
    				        'assets/layouts/layout2/css/layout.min.css',
    				        'assets/layouts/layout2/css/custom.min.css',
    				        'assets/layouts/layout2/css/themes/blue.min.css',
    				        'assets/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.min.css',
    				        'assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js'
    				        /*'assets/global/plugins/bootstrap-select/css/bootstrap-select.css',
    				        'assets/global/plugins/bootstrap-select/js/bootstrap-select.min.js',
    				        'assets/pages/scripts/components-bootstrap-select.min.js',
    				        'assets/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js',
    				        'assets/global/plugins/bootstrap-switch/js/bootstrap-switch.min.js',
    				        'assets/global/plugins/jquery.blockui.min.js',
                            'assets/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css',
                            'assets/global/plugins/bootstrap-select/css/bootstrap-select.css',
                           'assets/layouts/layout2/css/layout.min.css',
                           'assets/global/plugins/jquery.min.js',
                          'assets/global/plugins/bootstrap/js/bootstrap.min.js',
                        'assets/global/plugins/js.cookie.min.js',
                          'assets/global/scripts/app.min.js',
                          'assets/layouts/layout2/scripts/layout.min.js',
                           'assets/layouts/layout2/scripts/demo.min.js',
                           'assets/layouts/global/scripts/quick-sidebar.min.js',
                          'assets/layouts/global/scripts/quick-nav.min.js'*/
                        ]
                    });
                }]
            }
        })

        .state('materiel', {
            url: "/materiel",
            templateUrl: "rest/page/materiel",
            data: {pageTitle: '物料信息'},
            controller: "materielController",
            resolve: {
                deps: ['$ocLazyLoad', function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        name: 'MetronicApp',
                        insertBefore: '#ng_load_plugins_before', // load the above css files before '#ng_load_plugins_before'
                        files: [
//                           'assets/global/plugins/jstree/dist/themes/default/style.min.css',
//                            'assets/global/plugins/jstree/dist/jstree.min.js',
//                            'assets/pages/scripts/ui-tree.min.js',
							'assets/global/plugins/datatables/datatables.min.css',
							'assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css',
							'assets/global/plugins/datatables/datatables.all.min.js',
							'assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js',
							'assets/apps/scripts/angular-file-upload-shim.min.js',
					        'assets/apps/scripts/angular-file-upload.min.js',
                            'assets/apps/service/materielService.js',
                            'assets/apps/controllers/materielController.js'
                        ]
                    });
                }]
            }
        })
        // 新增物料
        .state('addMateriel', {
            url: "/addMateriel?:serialNum&:view",
            templateUrl: "rest/page/addMateriel",
            data: {pageTitle: '新增物料'},
            controller: "materielController",
            resolve: {
                deps: ['$ocLazyLoad', function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        name: 'MetronicApp',
                        insertBefore: '#ng_load_plugins_before', // load the above css files before '#ng_load_plugins_before'
                        files: [
//	        	'assets/global/plugins/icheck/skins/all.css',
//	        	'assets/global/plugins/icheck/icheck.min.js',
//				'assets/pages/scripts/form-icheck.min.js',
//		        'assets/global/plugins/datatables/datatables.min.css', 
//		        'assets/global/plugins/bootstrap-paginator/bootstrap-paginator.js',
//		        'assets/global/plugins/datatables/datatables.all.min.js',
//		        'assets/global/plugins/bootstrap-toastr/toastr.js',
//		        'assets/global/plugins/bootstrap-toastr/toastr.css',
				'assets/global/plugins/datatables/datatables.min.css',
				'assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css',
				'assets/global/plugins/datatables/datatables.all.min.js',
				'assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js',
				'assets/apps/scripts/angular-file-upload-shim.min.js',
		        'assets/apps/scripts/angular-file-upload.min.js',
		        'assets/apps/scripts/FileUploader.js',
	        	'assets/apps/service/materielService.js',
	        	'assets/apps/directive/CompanyDirective.js',
				'assets/apps/controllers/materielController.js'/*,
				'http://www.jq22.com/jquery/jquery-migrate-1.2.1.min.js',
				'assets/pages/scripts/jquery.jqprint-0.3.js'*/
                      ]
                    });
                }]
            }
        })

	    .state('userContract', {
	            url: "/userContract",
	            templateUrl:"rest/page/userContract",
	            data: {pageTitle: '合同管理'},
	            controller: "ContractController",
	            resolve: {
	                deps: ['$ocLazyLoad', function($ocLazyLoad) {
	                    return $ocLazyLoad.load({
	                        name: 'MetronicApp',
	                        insertBefore: '#ng_load_plugins_before', // load the above css files before '#ng_load_plugins_before'
	                        files: [                             
	                    
	                    'assets/global/plugins/datatables/datatables.min.css', 
	                    'assets/global/plugins/datatables/datatables.min.js',
	                    'assets/global/plugins/jquery-validation/js/jquery.validate.js',
						'assets/global/plugins/datatables/datatables.all.min.js',
                        'assets/pages/scripts/table-datatables-managed.min.js',
	                    'assets/apps/service/ContractService.js',
	                    'assets/apps/controllers/ContractController.js',
	                    'assets/apps/scripts/pageHandle.js',
	                    
	                    'assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js',
				        'assets/global/plugins/datatables/datatables.all.min.js',
				        'assets/global/plugins/datatables/datatables.min.css', 
				        'assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.css',
				        'assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.js',
				        'assets/global/plugins/bootbox/bootbox.min.js',
				        'assets/apps/scripts/pageHandle.js',
				        'assets/apps/scripts/angular-file-upload-shim.min.js',
				        'assets/apps/scripts/angular-file-upload.min.js',
				        'assets/global/plugins/bootstrap-tabdrop/js/bootstrap-tabdrop.js',
	                   	                        ]
	                    });
	                }]
	            }
	        })
        
        .state('addUserContract',{
            url: "/addUserContract",
            templateUrl: "rest/page/addUserContract",
            data: {pageTitle: '添加合同'},
            controller: "ContractController",
            resolve: {
                deps: ['$ocLazyLoad', function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        name: 'MetronicApp',
                        insertBefore: '#ng_load_plugins_before', // load the above css files before '#ng_load_plugins_before'
                        files: [                             
					'assets/global/plugins/select2/css/select2.min.css',
					'assets/global/plugins/select2/css/select2-bootstrap.min.css',
					'assets/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.min.css',
					'assets/global/plugins/bootstrap-wysihtml5/bootstrap-wysihtml5.css',
					'assets/global/plugins/bootstrap-markdown/css/bootstrap-markdown.min.css',
					'assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css',
					'assets/global/plugins/jquery-validation/js/jquery.validate.js',
					'assets/global/plugins/datatables/datatables.all.min.js',
					'assets/pages/scripts/table-datatables-managed.min.js',
					'assets/global/plugins/select2/js/select2.full.min.js',
					'assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js',
					'assets/global/plugins/jquery-validation/js/additional-methods.min.js',
					'assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js',
					'assets/global/plugins/fancybox/source/jquery.fancybox.css',
					'assets/global/plugins/jquery-file-upload/blueimp-gallery/blueimp-gallery.min.css',
					'assets/global/plugins/jquery-file-upload/css/jquery.fileupload.css',
					'assets/global/plugins/jquery-file-upload/css/jquery.fileupload-ui.css',
					'assets/layouts/layout2/css/themes/blue.min.css',
					'assets/apps/service/ContractService.js',
					'assets/apps/controllers/ContractController.js',                      ]
                    });
                }]
            }
        })
        
        .state('editUserContractPage', {
        	params:{data:null},
            url: "/editUserContractPage:id",
            templateUrl: "rest/contract/editUserContractPage",
            data: {pageTitle: '修改合同'},
            controller: "ContractController",
            resolve: {
                deps: ['$ocLazyLoad', function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        name: 'MetronicApp',
                        insertBefore: '#ng_load_plugins_before', // load the above css files before '#ng_load_plugins_before'
                        files: [                             
						'assets/global/plugins/select2/css/select2.min.css',
						'assets/global/plugins/select2/css/select2-bootstrap.min.css',
						'assets/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.min.css',
						'assets/global/plugins/bootstrap-wysihtml5/bootstrap-wysihtml5.css',
						'assets/global/plugins/bootstrap-markdown/css/bootstrap-markdown.min.css',
						'assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css',
						'assets/global/plugins/jquery-validation/js/jquery.validate.js',
						'assets/global/plugins/datatables/datatables.all.min.js',
                        'assets/pages/scripts/table-datatables-managed.min.js',
						'assets/global/plugins/select2/js/select2.full.min.js',
						'assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js',
						'assets/global/plugins/jquery-validation/js/additional-methods.min.js',
						'assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js',
				        'assets/global/plugins/fancybox/source/jquery.fancybox.css',
				        'assets/global/plugins/jquery-file-upload/blueimp-gallery/blueimp-gallery.min.css',
				        'assets/global/plugins/jquery-file-upload/css/jquery.fileupload.css',
				        'assets/global/plugins/jquery-file-upload/css/jquery.fileupload-ui.css',
				        'assets/layouts/layout2/css/themes/blue.min.css',
				        'assets/apps/service/ContractService.js',
	                    'assets/apps/controllers/ContractController.js',
                        ]
                    });
                }]
            }
        })
        
        // 用户管理
            .state('user', {
            url: "/user",
            templateUrl: "rest/page/user",
            data: {pageTitle: '用户管理'},
            controller: "UserController",
            resolve: {
                deps: ['$ocLazyLoad', function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        name: 'MetronicApp',
                        insertBefore: '#ng_load_plugins_before', // load the above css files before '#ng_load_plugins_before'
                        files: [      
                            'assets/global/plugins/datatables/datatables.all.min.js',
                            'assets/global/plugins/datatables/datatables.min.css',                           
                            'assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css',
                            
                            'assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js',
                            'assets/apps/service/UserService.js',
                            'assets/apps/controllers/UserController.js'
                        ]
                    });
                }]
            }
        })       
        .state('company', {
        	url: "/company",
        	templateUrl: "rest/company/companyManage",
        	data: {pageTitle: '企业信息'},
        	reload:true, 
        	controller: "CompanyController",
        	resolve: {
        		deps: ['$ocLazyLoad', function($ocLazyLoad) {
        			return $ocLazyLoad.load({
        				name: 'MetronicApp',
        				insertBefore: '#ng_load_plugins_before', // load the above css files before '#ng_load_plugins_before'
        				files: [    
        				        'assets/global/plugins/datatables/datatables.all.min.js',
        				        'assets/global/plugins/datatables/datatables.min.css', 
        				        'assets/global/plugins/bootbox/bootbox.min.js',
        				        'assets/apps/scripts/angular-file-upload.min.js',
        				        'assets/apps/controllers/CompanyController.js',
        				        'assets/apps/service/CompanyService.js',
        				        'assets/apps/directive/CompanyDirective.js'
        				        ]
        			});
        		}]
        	}
        })
        .state('companyAdd', {
	        	url: "/companyAdd?:comId",
	        	templateUrl: "rest/company/companyAdd",
	        	data: {pageTitle: '新建企业信息'},
	        	reload:true, 
	        	controller: "CompanyController",
	        	resolve: {
	        		deps: ['$ocLazyLoad', function($ocLazyLoad) {
	        			return $ocLazyLoad.load({
	        				name: 'MetronicApp',
	        				insertBefore: '#ng_load_plugins_before', // load the above css files before '#ng_load_plugins_before'
	        				files: [                             
	        				        'assets/apps/controllers/CompanyController.js',
	        				        'assets/apps/service/CompanyService.js',
	        				        'assets/apps/directive/CompanyDirective.js',
	        				        'assets/apps/scripts/angular-file-upload-shim.min.js',
	        				        'assets/apps/scripts/angular-file-upload.min.js',
	        				        'assets/apps/scripts/FileUploader.js'
	        				        ]
	        			});
	        		}]
	        	}
	        }).state('demandPlan', {
	        	url: "/demandPlan",
	        	templateUrl: "rest/demandPlan/demandPlanManage",
	        	data: {pageTitle: '需求计划'},
	        	reload:true, 
	        	controller: "DemandPlanController",
	        	resolve: {
	        		deps: ['$ocLazyLoad', function($ocLazyLoad) {
	        			return $ocLazyLoad.load({
	        				name: 'MetronicApp',
	        				insertBefore: '#ng_load_plugins_before', // load the above css files before '#ng_load_plugins_before'
	        				files: [       
	        				        'assets/global/plugins/datatables/datatables.min.css',                           
	        				        'assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css',
	        				        'assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js',
	        				        'assets/global/plugins/bootstrap-paginator/bootstrap-paginator.js',
	        				        'assets/global/plugins/datatables/datatables.all.min.js',
	        				        'assets/apps/controllers/DemandPlanController.js',
	        				        'assets/apps/service/DemandPlanService.js',
	        				        'assets/global/plugins/bootstrap-tabdrop/js/bootstrap-tabdrop.js'
	        				        ]
	        			});
	        		}]
	        	}
	        }).state('demandPlanAdd', {
	        	url: "/demandPlanAdd?:serialNum",
	        	templateUrl: "rest/demandPlan/demandPlanAdd",
	        	data: {pageTitle: '需求计划'},
	        	reload:true, 
	        	controller: "DemandPlanController",
	        	resolve: {
	        		deps: ['$ocLazyLoad', function($ocLazyLoad) {
	        			return $ocLazyLoad.load({
	        				name: 'MetronicApp',
	        				insertBefore: '#ng_load_plugins_before', // load the above css files before '#ng_load_plugins_before'
	        				files: [       
	        				        'assets/global/plugins/datatables/datatables.min.css',                  
	        				        'assets/global/plugins/datatables/datatables.all.min.js',
	        				        'assets/apps/controllers/DemandPlanController.js',
	        				        'assets/apps/service/DemandPlanService.js',
	        				        'assets/apps/directive/DemandPlanDirective.js',
	        				        'assets/global/plugins/bootstrap-tabdrop/js/bootstrap-tabdrop.js',
	        				        'assets/global/plugins/bootstrap-select/js/bootstrap-select.min.js',
	        				        'assets/global/plugins/bootstrap-select/css/bootstrap-select.css'
	        				        ]
	        			});
	        		}]
	        	}	        })         	           .state('warehouse', {
	        url: "/warehouse",
            templateUrl: "rest/warehouse/viewWarehouseList",
            data: {pageTitle: '仓库信息'},
            controller: "WarehouseController",
            resolve: {
                deps: ['$ocLazyLoad', function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        name: 'MetronicApp',
                        insertBefore: '#ng_load_plugins_before', // load the above css files before '#ng_load_plugins_before'
                        files: [   
							                        
        				        'assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css',
        				        'assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js',
        				        'assets/global/plugins/datatables/datatables.all.min.js',
        				        'assets/global/plugins/datatables/datatables.min.js',
        				        'assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.css',
        				        'assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.js',
        				        'assets/global/plugins/bootbox/bootbox.min.js',
        				        'assets/apps/scripts/pageHandle.js',
        				        'assets/pages/scripts/table-datatables-buttons.min.js',
        				        'assets/global/plugins/bootstrap-tabdrop/js/bootstrap-tabdrop.js',
        				        'assets/apps/service/WarehouseService.js',
	                        'assets/apps/controllers/WarehouseController.js'
        				        
							/* 'assets/global/plugins/jstree/dist/themes/default/style.min.css',
							  'assets/global/plugins/jstree/dist/jstree.min.js',
                            'assets/pages/scripts/ui-tree.min.js',
							 'assets/apps/scripts/pageHandle.js',
							 'assets/global/plugins/bootstrap-toastr/toastr.js',
        				        'assets/global/plugins/bootstrap-toastr/toastr.css',
                           'assets/global/plugins/datatables/datatables.min.css', 
                           'assets/global/plugins/datatables/datatables.min.js',
//	                        'assets/apps/service/WarehouseService.js'*/
                        ]
                    });
                }]
            }
        })
        .state('addWarehouse', {
            url: "/addWarehouse?:warehouseSerialNum",
            templateUrl: "rest/warehouse/addOrEditWarehouseInfo",
            data: {pageTitle: '新增仓库'},
            controller: "WarehouseController",
            resolve: {
                deps: ['$ocLazyLoad', function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        name: 'MetronicApp',
                        insertBefore: '#ng_load_plugins_before', // load the above css files before '#ng_load_plugins_before'
                        files: [                             
                            /*'assets/global/plugins/datatables/datatables.min.css', 
                            'assets/global/plugins/datatables/datatables.min.js',
                            'assets/global/plugins/bootstrap-toastr/toastr.js',
    				        'assets/global/plugins/bootstrap-toastr/toastr.css',
                            'assets/apps/controllers/WarehouseController.js'*/
                            'assets/global/plugins/datatables/datatables.min.css', 
    				        'assets/global/plugins/bootstrap-paginator/bootstrap-paginator.js',
    				        'assets/global/plugins/datatables/datatables.all.min.js',
    				        'assets/global/plugins/bootstrap-toastr/toastr.js',
    				        'assets/global/plugins/bootstrap-toastr/toastr.css',
    				        'assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.css',
    				        'assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.js',
    				        'assets/global/plugins/jquery-repeater/jquery.repeater.js',
    				        'assets/pages/scripts/form-repeater.min.js',
    				        'assets/pages/scripts/form-repeater.js',
    				        'assets/apps/controllers/WarehouseController.js',
    				        'assets/apps/service/WarehouseService.js',
    				        'assets/global/plugins/bootbox/bootbox.min.js',
    				        'assets/pages/scripts/ui-bootbox.min.js',
    				        'assets/global/plugins/jquery.blockui.min.js',
    				        'assets/pages/scripts/ui-blockui.min.js',
    				        'assets/apps/scripts/pageHandle.js'
    				        
                        ]
                    });
                }]
            }
        })
}]);



//js获取项目根路径，如： http://localhost:8083/uimcardprj
function getRootPath(){
    //获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
    var curWwwPath=window.document.location.href;
    //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
    var pathName=window.document.location.pathname;
    var pos=curWwwPath.indexOf(pathName);
    //获取主机地址，如： http://localhost:8083
    var localhostPaht=curWwwPath.substring(0,pos);
    //获取带"/"的项目名，如：/uimcardprj
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
    return(localhostPaht+projectName);
}

/* Init global settings and run the app */
MetronicApp.run(["$rootScope", "settings", "$state", function($rootScope, settings, $state) {
    $rootScope.$state = $state; // state to be accessed from view
    $rootScope.$settings = settings; // state to be accessed from view
}]);
