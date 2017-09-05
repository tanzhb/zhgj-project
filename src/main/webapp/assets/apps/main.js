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
                                	'assets/global/plugins/datatables/datatables.min.css',
                                	'assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css',
                                	'assets/global/plugins/datatables/datatables.all.min.js',
                                	'assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js',
                                	'assets/apps/service/PriceListService.js',
                                	'assets/apps/controllers/PriceListController.js',
                                	'assets/apps/scripts/angular-file-upload-shim.min.js',
                                	'assets/apps/scripts/angular-file-upload.min.js',
                                	  'assets/global/plugins/bootbox/bootbox.min.js'
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
    				        'assets/global/plugins/datatables/datatables.all.min.js',//
    				        'assets/global/plugins/jquery-repeater/jquery.repeater.js',
    				        'assets/pages/scripts/form-repeater.min.js',
    				        'assets/pages/scripts/form-repeater.js',
    				        'assets/apps/controllers/PriceListController.js',
    				        'assets/apps/service/PriceListService.js',
    				        'assets/apps/scripts/pageHandle.js',
    				        'assets/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.min.css',
    				        'assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js',
    				        'assets/apps/scripts/angular-file-upload-shim.min.js',
    				        'assets/apps/scripts/angular-file-upload.min.js',
    				        'assets/apps/scripts/FileUploader.js'
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
							'assets/global/plugins/datatables/datatables.min.css',
							'assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css',
							'assets/global/plugins/datatables/datatables.all.min.js',
							'assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js',
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
				'assets/global/plugins/datatables/datatables.min.css',
				'assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css',
				'assets/global/plugins/datatables/datatables.all.min.js',
				'assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js',
		        'assets/apps/scripts/angular-file-upload.min.js',
	        	'assets/apps/service/materielService.js',
	        	'assets/apps/directive/CompanyDirective.js',
				'assets/apps/controllers/materielController.js'/*,
				'assets/pages/scripts/jquery.jqprint-0.3.js'*/
                      ]
                    });
                }]
            }
        })
        //销售订单
        .state('saleOrder', {
            url: "/saleOrder",
            templateUrl: "rest/page/saleOrder",
            data: {pageTitle: '销售订单'},
            controller: "saleOrderController",
            resolve: {
                deps: ['$ocLazyLoad', function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        name: 'MetronicApp',
                        insertBefore: '#ng_load_plugins_before', // load the above css files before '#ng_load_plugins_before'
                        files: [
							'assets/global/plugins/datatables/datatables.min.css',
							'assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css',
							'assets/global/plugins/datatables/datatables.all.min.js',
							'assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js',
							'assets/apps/scripts/angular-file-upload.min.js',
							'assets/apps/service/materielService.js',
                            'assets/apps/service/orderService.js',
                            'assets/apps/controllers/saleOrderController.js'
                        ]
                    });
                }]
            }
        })
        // 新增销售订单
        .state('addSaleOrder', {
            url: "/addSaleOrder?:serialNum&:view",
            templateUrl: "rest/page/addSaleOrder",
            data: {pageTitle: '新增销售订单'},
            controller: "saleOrderController",
            resolve: {
                deps: ['$ocLazyLoad', function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        name: 'MetronicApp',
                        insertBefore: '#ng_load_plugins_before', // load the above css files before '#ng_load_plugins_before'
                        files: [
				'assets/global/plugins/datatables/datatables.min.css',
				'assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css',
				'assets/global/plugins/datatables/datatables.all.min.js',
				'assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js',
				'assets/apps/scripts/angular-file-upload.min.js',
				'assets/apps/scripts/pageHandle.js',
				'assets/apps/service/materielService.js',
	        	'assets/apps/service/orderService.js',
				'assets/apps/controllers/saleOrderController.js'
                      ]
                    });
                }]
            }
        })
        //采购订单
        .state('buyOrder', {
            url: "/buyOrder",
            templateUrl: "rest/page/buyOrder",
            data: {pageTitle: '采购订单'},
            controller: "buyOrderController",
            resolve: {
                deps: ['$ocLazyLoad', function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        name: 'MetronicApp',
                        insertBefore: '#ng_load_plugins_before', // load the above css files before '#ng_load_plugins_before'
                        files: [
							'assets/global/plugins/datatables/datatables.min.css',
							'assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css',
							'assets/global/plugins/datatables/datatables.all.min.js',
							'assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js',
							'assets/apps/scripts/angular-file-upload.min.js',
							'assets/apps/service/materielService.js',
                            'assets/apps/service/orderService.js',
                            'assets/apps/controllers/buyOrderController.js'
                        ]
                    });
                }]
            }
        })
        // 新增采购订单
        .state('addBuyOrder', {
            url: "/addBuyOrder?:serialNum&:view",
            templateUrl: "rest/page/addBuyOrder",
            data: {pageTitle: '新增采购订单'},
            controller: "buyOrderController",
            resolve: {
                deps: ['$ocLazyLoad', function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        name: 'MetronicApp',
                        insertBefore: '#ng_load_plugins_before', // load the above css files before '#ng_load_plugins_before'
                        files: [
				'assets/global/plugins/datatables/datatables.min.css',
				'assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css',
				'assets/global/plugins/datatables/datatables.all.min.js',
				'assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js',
				'assets/apps/scripts/angular-file-upload.min.js',
				'assets/apps/scripts/pageHandle.js',
				'assets/apps/service/materielService.js',
	        	'assets/apps/service/orderService.js',
				'assets/apps/controllers/buyOrderController.js'
                      ]
                    });
                }]
            }
        })
        //采购订单
        .state('statement', {
            url: "/statement",
            templateUrl: "rest/page/statement",
            data: {pageTitle: '对账单'},
            controller: "statementController",
            resolve: {
                deps: ['$ocLazyLoad', function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        name: 'MetronicApp',
                        insertBefore: '#ng_load_plugins_before', // load the above css files before '#ng_load_plugins_before'
                        files: [
							'assets/global/plugins/datatables/datatables.min.css',
							'assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css',
							'assets/global/plugins/datatables/datatables.all.min.js',
							'assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js',
                            'assets/apps/service/statementService.js',
                            'assets/apps/controllers/statementController.js'
                        ]
                    });
                }]
            }
        })
        // 新增客户对账单
        .state('addBuyStatement', {
            url: "/addBuyStatement?:serialNum&:view",
            templateUrl: "rest/page/addBuyStatement",
            data: {pageTitle: '新增客户对账单'},
            controller: "statementController",
            resolve: {
                deps: ['$ocLazyLoad', function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        name: 'MetronicApp',
                        insertBefore: '#ng_load_plugins_before', // load the above css files before '#ng_load_plugins_before'
                        files: [
				'assets/global/plugins/datatables/datatables.min.css',
				'assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css',
				'assets/global/plugins/datatables/datatables.all.min.js',
				'assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js',
				'assets/apps/scripts/pageHandle.js',
	        	'assets/apps/service/statemenService.js',
				'assets/apps/controllers/statementController.js'
                      ]
                    });
                }]
            }
        })
        // 新增供应商对账单
        .state('addSupplyStatement', {
            url: "/addSupplyStatement?:serialNum&:view",
            templateUrl: "rest/page/addSupplyStatement",
            data: {pageTitle: '新增供应商对账单'},
            controller: "statementController",
            resolve: {
                deps: ['$ocLazyLoad', function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        name: 'MetronicApp',
                        insertBefore: '#ng_load_plugins_before', // load the above css files before '#ng_load_plugins_before'
                        files: [
				'assets/global/plugins/datatables/datatables.min.css',
				'assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css',
				'assets/global/plugins/datatables/datatables.all.min.js',
				'assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js',
				'assets/apps/scripts/pageHandle.js',
	        	'assets/apps/service/statemenService.js',
				'assets/apps/controllers/statementController.js'
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
						'assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css',
						'assets/global/plugins/datatables/datatables.all.min.js',
						'assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js',
						'assets/apps/scripts/angular-file-upload-shim.min.js',
				        'assets/apps/scripts/angular-file-upload.min.js',
				        'assets/apps/service/ContractService.js',
	                    'assets/apps/controllers/ContractController.js',
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
					
					'assets/global/plugins/datatables/datatables.min.css',
					'assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css',
					'assets/global/plugins/datatables/datatables.all.min.js',
					'assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js',
					'assets/apps/scripts/angular-file-upload-shim.min.js',
			        'assets/apps/scripts/angular-file-upload.min.js',
			        'assets/apps/scripts/FileUploader.js',
			        'assets/apps/service/ContractService.js',
					'assets/apps/controllers/ContractController.js'
					
					
					]
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
					'assets/global/plugins/datatables/datatables.min.css',
					'assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css',
					'assets/global/plugins/datatables/datatables.all.min.js',
					'assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js',
					'assets/apps/scripts/angular-file-upload-shim.min.js',
					'assets/apps/scripts/angular-file-upload.min.js',
					'assets/apps/scripts/FileUploader.js',
					'assets/apps/service/ContractService.js',
					'assets/apps/controllers/ContractController.js'
                        ]
                    });
                }]
            }
        })
        
        .state('purchaseForecast', {
            url: "/purchaseForecast",
            templateUrl: "rest/page/purchaseForecast",
            data: {pageTitle: '采购预测'},
            controller: "PurchaseForecastController",
            resolve: {
                deps: ['$ocLazyLoad', function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        name: 'MetronicApp',
                        insertBefore: '#ng_load_plugins_before', // load the above css files before '#ng_load_plugins_before'
                        files: [
							'assets/global/plugins/datatables/datatables.min.css',
							'assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css',
							'assets/global/plugins/datatables/datatables.all.min.js',
							'assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js',
					        'assets/apps/scripts/angular-file-upload.min.js',
                            'assets/apps/service/PurchaseForecastService.js',
                            'assets/apps/controllers/PurchaseForecastController.js'
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
        				        'assets/apps/service/CompanyService.js'
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
	        				        'assets/apps/service/DemandPlanService.js'
	        				        ]
	        			});
	        		}]
	        	}
	        }).state('demandPlanAdd', {
	        	url: "/demandPlanAdd?:serialNum",
	        	templateUrl: "rest/demandPlan/demandPlanAdd",
	        	data: {pageTitle: '新建需求计划'},
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
	        				        'assets/global/plugins/bootstrap-select/js/bootstrap-select.min.js',
	        				        'assets/global/plugins/bootstrap-select/css/bootstrap-select.css'
	        				        ]
	        			});
	        		}]
	        	}	        
	        }).state('demandPlanView', {
		        	url: "/demandPlanView?:serialNum",
		        	templateUrl: "rest/demandPlan/demandPlanView",
		        	data: {pageTitle: '查看需求计划'},
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
		        				        'assets/global/plugins/bootstrap-select/js/bootstrap-select.min.js',
		        				        'assets/global/plugins/bootstrap-select/css/bootstrap-select.css',
		        				        'assets/pages/scripts/table-datatables-scroller.min.js'
		        				        ]
		        			});
		        		}]
		        	}	        
		    }) .state('takeDelivery', {
		    	url: "/takeDelivery?:type",
		    	templateUrl: "rest/takeDelivery/takeDeliveryManage",
		    	data: {pageTitle: '收货计划'},
		    	reload:true, 
		    	controller: "TakeDeliveryController",
		    	resolve: {
		    		deps: ['$ocLazyLoad', function($ocLazyLoad) {
		    			return $ocLazyLoad.load({
		    				name: 'MetronicApp',
		    				insertBefore: '#ng_load_plugins_before', // load the above css files before '#ng_load_plugins_before'
		    				files: [       
		    				        'assets/global/plugins/datatables/datatables.min.css',                  
		    				        'assets/global/plugins/datatables/datatables.all.min.js',
		    				        'assets/apps/controllers/TakeDeliveryController.js',
		    				        'assets/apps/service/TakeDeliveryService.js',
		    				        'assets/apps/service/orderService.js',
		    				        'assets/global/plugins/bootstrap-select/js/bootstrap-select.min.js',
		    				        'assets/global/plugins/bootstrap-select/css/bootstrap-select.css'
		    				        ]
		    			});
		    		}]
		    	}	        
		    }) .state('takeDeliveryAdd', {
		    	url: "/takeDeliveryAdd?:serialNum",
		    	templateUrl: "rest/takeDelivery/takeDeliveryAdd",
		    	data: {pageTitle: '新增收货'},
		    	reload:true, 
		    	controller: "TakeDeliveryController",
		    	resolve: {
		    		deps: ['$ocLazyLoad', function($ocLazyLoad) {
		    			return $ocLazyLoad.load({
		    				name: 'MetronicApp',
		    				insertBefore: '#ng_load_plugins_before', // load the above css files before '#ng_load_plugins_before'
		    				files: [       
		    				        'assets/apps/controllers/TakeDeliveryController.js',
		    				        'assets/apps/service/TakeDeliveryService.js',
		    				        'assets/apps/service/orderService.js'
		    				        ]
		    			});
		    		}]
		    	}
		    }) .state('takeDeliveryView', {
		    	url: "/takeDeliveryView?:serialNum",
		    	templateUrl: "rest/takeDelivery/takeDeliveryView",
		    	data: {pageTitle: '查看收货详情'},
		    	reload:true, 
		    	controller: "TakeDeliveryController",
		    	resolve: {
		    		deps: ['$ocLazyLoad', function($ocLazyLoad) {
		    			return $ocLazyLoad.load({
		    				name: 'MetronicApp',
		    				insertBefore: '#ng_load_plugins_before', // load the above css files before '#ng_load_plugins_before'
		    				files: [       
		    				        'assets/apps/controllers/TakeDeliveryController.js',
		    				        'assets/apps/service/TakeDeliveryService.js',
		    				        'assets/apps/service/orderService.js'
		    				        ]
		    			});
		    		}]
		    	}	        
		    }).state('stockInAdd', {
		    	url: "/stockInAdd?:serialNum",
		    	templateUrl: "rest/takeDelivery/stockInAdd",
		    	data: {pageTitle: '新建入库单'},
		    	reload:true, 
		    	controller: "StockInController",
		    	resolve: {
		    		deps: ['$ocLazyLoad', function($ocLazyLoad) {
		    			return $ocLazyLoad.load({
		    				name: 'MetronicApp',
		    				insertBefore: '#ng_load_plugins_before', // load the above css files before '#ng_load_plugins_before'
		    				files: [       
		    				        'assets/apps/controllers/StockInController.js',
		    				        'assets/apps/service/TakeDeliveryService.js'
		    				        ]
		    			});
		    		}]
		    	}	        
		    }).state('stockInView', {
		    	url: "/stockInView?:serialNum",
		    	templateUrl: "rest/takeDelivery/stockInView",
		    	data: {pageTitle: '查看入库单'},
		    	reload:true, 
		    	controller: "StockInController",
		    	resolve: {
		    		deps: ['$ocLazyLoad', function($ocLazyLoad) {
		    			return $ocLazyLoad.load({
		    				name: 'MetronicApp',
		    				insertBefore: '#ng_load_plugins_before', // load the above css files before '#ng_load_plugins_before'
		    				files: [       
		    				        'assets/apps/controllers/StockInController.js',
		    				        'assets/apps/service/TakeDeliveryService.js'
		    				        ]
		    			});
		    		}]
		    	}	        
		    }).state('paymentRecord', {
		    	url: "/paymentRecord",
		    	templateUrl: "rest/paymentRecord/paymentRecordManage",
		    	data: {pageTitle: '收付款记录'},
		    	reload:true, 
		    	controller: "PaymentRecordController",
		    	resolve: {
		    		deps: ['$ocLazyLoad', function($ocLazyLoad) {
		    			return $ocLazyLoad.load({
		    				name: 'MetronicApp',
		    				insertBefore: '#ng_load_plugins_before', // load the above css files before '#ng_load_plugins_before'
		    				files: [   
		    				        'assets/global/plugins/datatables/datatables.min.css',                  
		    				        'assets/global/plugins/datatables/datatables.all.min.js',
		    				        'assets/apps/controllers/PaymentRecordController.js',
		    				        'assets/apps/service/PaymentRecordService.js'
		    				       
		    				        ]
		    			});
		    		}]
		    	}	        
		    })         	           .state('warehouse', {
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
                            	'assets/global/plugins/datatables/datatables.min.css',
                                	'assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css',
                                	'assets/global/plugins/datatables/datatables.all.min.js',
                                	'assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js',
                                	  'assets/apps/service/WarehouseService.js',
          	                        'assets/apps/controllers/WarehouseController.js',
                                	  'assets/global/plugins/bootbox/bootbox.min.js'
        				      
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
                            'assets/global/plugins/datatables/datatables.min.css', 
    				        'assets/global/plugins/bootstrap-paginator/bootstrap-paginator.js',
    				        'assets/global/plugins/datatables/datatables.all.min.js',
    				        'assets/global/plugins/jquery-repeater/jquery.repeater.js',
    				        'assets/pages/scripts/form-repeater.min.js',
    				        'assets/pages/scripts/form-repeater.js',
    				        'assets/apps/controllers/WarehouseController.js',
    				        'assets/apps/service/WarehouseService.js',
    				        'assets/apps/scripts/pageHandle.js'  
                        ]
                    });
                }]
            }
        })
        
        
        .state('stock', {
	            url: "/stock",
	            templateUrl:"rest/stock/viewStockList",
	            data: {pageTitle: '库存'},
	            controller: "StockController",
	            resolve: {
	                deps: ['$ocLazyLoad', function($ocLazyLoad) {
	                    return $ocLazyLoad.load({
	                        name: 'MetronicApp',
	                        insertBefore: '#ng_load_plugins_before', // load the above css files before '#ng_load_plugins_before'
	                        files: [                             
						'assets/global/plugins/datatables/datatables.min.css',
						'assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css',
						'assets/global/plugins/datatables/datatables.all.min.js',
						'assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js',
						  'assets/global/plugins/bootbox/bootbox.min.js',
				        'assets/apps/service/StockService.js',
						'assets/apps/controllers/StockController.js'
	                   	                        ]
	                    });
	                }]
	            }
	        })
	        .state('addOrEditStock', {//库存新建编辑
	        	url: "/addOrEditStock?:stockSerialNum",
	        	templateUrl: "rest/stock/addOrEditStock",
	        	data: {pageTitle: '新建库存'},
	        	reload:true, 
	        	controller: "StockController",
	        	resolve: {
	        		deps: ['$ocLazyLoad', function($ocLazyLoad) {
	        			return $ocLazyLoad.load({
	        				name: 'MetronicApp',
	        				insertBefore: '#ng_load_plugins_before', // load the above css files before '#ng_load_plugins_before'
	        				files: [       
	        				        'assets/global/plugins/datatables/datatables.min.css',                  
	        				        'assets/global/plugins/datatables/datatables.all.min.js',
	        				        'assets/apps/service/StockService.js',
	        						'assets/apps/controllers/StockController.js',
	        						 'assets/apps/scripts/pageHandle.js',
	        				        'assets/global/plugins/bootstrap-select/js/bootstrap-select.min.js',
	        				        'assets/global/plugins/bootstrap-select/css/bootstrap-select.css'
	        				        ]
	        			});
	        		}]
	        	}	        
	        }).state('stockView', {//库存查看
		        	url: "/stockView?:stockSerialNum",
		        	templateUrl: "rest/stock/stockView",
		        	data: {pageTitle: '库存详情'},
		        	reload:true, 
		        	controller: "StockController",
		        	resolve: {
		        		deps: ['$ocLazyLoad', function($ocLazyLoad) {
		        			return $ocLazyLoad.load({
		        				name: 'MetronicApp',
		        				insertBefore: '#ng_load_plugins_before', // load the above css files before '#ng_load_plugins_before'
		        				files: [       
		        				        'assets/global/plugins/datatables/datatables.min.css',                  
		        				        'assets/global/plugins/datatables/datatables.all.min.js',
		        				        'assets/apps/service/StockService.js',
		        						'assets/apps/controllers/StockController.js',
		        				        'assets/global/plugins/bootstrap-select/js/bootstrap-select.min.js',
		        				        'assets/global/plugins/bootstrap-select/css/bootstrap-select.css',
		        				        'assets/pages/scripts/table-datatables-scroller.min.js'
		        				        ]
		        			});
		        		}]
		        	}	        
		    })   
		    .state('stockInOutCheck', {
	            url: "/stockInOutCheck?:inOrOut",
	            templateUrl:"rest/stockInOut/viewStockInOutCheckList",
	            data: {pageTitle: '出入库检验'},
	            controller: "StockInOutController",
	            resolve: {
	                deps: ['$ocLazyLoad', function($ocLazyLoad) {
	                    return $ocLazyLoad.load({
	                        name: 'MetronicApp',
	                        insertBefore: '#ng_load_plugins_before', // load the above css files before '#ng_load_plugins_before'
	                        files: [                             
						'assets/global/plugins/datatables/datatables.min.css',
						'assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css',
						'assets/global/plugins/datatables/datatables.all.min.js',
						 'assets/global/plugins/bootstrap-paginator/bootstrap-paginator.js',
						'assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js',
						  'assets/global/plugins/bootbox/bootbox.min.js',
				        'assets/apps/service/StockInOutService.js',
						'assets/apps/controllers/StockInOutController.js'
	                   	                        ]
	                    });
	                }]
	            }
	        })
	        .state('addOrEditStockInOutCheck', {//出入库检验新建编辑
	        	url: "/addOrEditStockInOutCheck?:inOrOut",
	        	templateUrl: "rest/stockInOut/addOrEditStockInOutCheck",
	        	data: {pageTitle: '新建出入库检验'},
	        	reload:true, 
	        	controller: "StockInOutController",
	        	resolve: {
	        		deps: ['$ocLazyLoad', function($ocLazyLoad) {
	        			return $ocLazyLoad.load({
	        				name: 'MetronicApp',
	        				insertBefore: '#ng_load_plugins_before', // load the above css files before '#ng_load_plugins_before'
	        				files: [       
	        				        'assets/global/plugins/datatables/datatables.min.css',                  
	        				        'assets/global/plugins/datatables/datatables.all.min.js',
	        				        'assets/apps/service/StockInOutService.js',
	        						'assets/apps/controllers/StockInOutController.js',
	        						 'assets/apps/scripts/pageHandle.js',
	        						 'assets/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.min.css',
	         				        'assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js',
	        				        'assets/global/plugins/bootstrap-select/js/bootstrap-select.min.js',
	        				        'assets/global/plugins/bootstrap-select/css/bootstrap-select.css'
	        				        ]
	        			});
	        		}]
	        	}	        
	        }).state('stockInOutCheckView', {//出入库检验查看
		        	url: "/stockInOutCheckView?:inOrOut",
		        	templateUrl: "rest/stockInOut/viewStockInOutCheck",
		        	data: {pageTitle: '出入库检验详情'},
		        	reload:true, 
		        	controller: "StockInOutController",
		        	resolve: {
		        		deps: ['$ocLazyLoad', function($ocLazyLoad) {
		        			return $ocLazyLoad.load({
		        				name: 'MetronicApp',
		        				insertBefore: '#ng_load_plugins_before', // load the above css files before '#ng_load_plugins_before'
		        				files: [       
		        				        'assets/global/plugins/datatables/datatables.min.css',                  
		        				        'assets/global/plugins/datatables/datatables.all.min.js',
		        				        'assets/apps/service/StockInOutService.js',
		        						'assets/apps/controllers/StockInOutController.js',
		        				        'assets/global/plugins/bootstrap-select/js/bootstrap-select.min.js',
		        				        'assets/global/plugins/bootstrap-select/css/bootstrap-select.css',
		        				        'assets/pages/scripts/table-datatables-scroller.min.js'
		        				        ]
		        			});
		        		}]
		        	}	        
		    })   
		    
		    .state('addDelivery', {
            url: "/addDelivery",
            templateUrl: "rest/page/addDelivery",
            data: {pageTitle: '新增发货'},
            controller: "DeliveryController",
            resolve: {
                deps: ['$ocLazyLoad', function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        name: 'MetronicApp',
                        insertBefore: '#ng_load_plugins_before', // load the above css files before '#ng_load_plugins_before'
                        files: [
				'assets/global/plugins/datatables/datatables.min.css',
				'assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css',
				'assets/global/plugins/datatables/datatables.all.min.js',
				'assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js',
				'assets/apps/scripts/pageHandle.js',
	        	'assets/apps/service/DeliveryService.js',
				'assets/apps/controllers/DeliveryController.js'
                      ]
                    });
                }]
            }
        })
        
        // 新增发货
        .state('delivery', {
            url: "/delivery",
            templateUrl: "rest/page/delivery",
            data: {pageTitle: '发货列表'},
            controller: "DeliveryController",
            resolve: {
                deps: ['$ocLazyLoad', function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        name: 'MetronicApp',
                        insertBefore: '#ng_load_plugins_before', // load the above css files before '#ng_load_plugins_before'
                        files: [
				'assets/global/plugins/datatables/datatables.min.css',
				'assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css',
				'assets/global/plugins/datatables/datatables.all.min.js',
				'assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js',
				'assets/apps/scripts/pageHandle.js',
	        	'assets/apps/service/DeliveryService.js',
				'assets/apps/controllers/DeliveryController.js'
                      ]
                    });
                }]
            }
        })
        
        .state('viewDelivery', {
        	params:{data:null},
            url: "/viewDelivery:serialNum",
            templateUrl: "rest/delivery/viewDelivery",
            data: {pageTitle: '查看发货'},
            controller: "DeliveryController",
            resolve: {
                deps: ['$ocLazyLoad', function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        name: 'MetronicApp',
                        insertBefore: '#ng_load_plugins_before', // load the above css files before '#ng_load_plugins_before'
                        files: [                             
			'assets/global/plugins/datatables/datatables.min.css',
			'assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css',
			'assets/global/plugins/datatables/datatables.all.min.js',
			'assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js',
			'assets/apps/scripts/pageHandle.js',
			'assets/apps/service/DeliveryService.js',
			'assets/apps/controllers/DeliveryController.js'
                        ]
                    });
                }]
            }
        })
        
        .state('editDeliveryPage', {
        	params:{data:null},
            url: "/editDeliveryPage:serialNumEdit",
            templateUrl: "rest/delivery/editDeliveryPage",
            data: {pageTitle: '编辑发货'},
            controller: "DeliveryController",
            resolve: {
                deps: ['$ocLazyLoad', function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        name: 'MetronicApp',
                        insertBefore: '#ng_load_plugins_before', // load the above css files before '#ng_load_plugins_before'
                        files: [                             
			'assets/global/plugins/datatables/datatables.min.css',
			'assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css',
			'assets/global/plugins/datatables/datatables.all.min.js',
			'assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js',
			'assets/apps/scripts/pageHandle.js',
			'assets/apps/service/DeliveryService.js',
			'assets/apps/controllers/DeliveryController.js'
                        ]
                    });
                }]
            }
        })
        
        .state('addPay', {
            url: "/addPay",
            templateUrl: "rest/page/addPay",
            data: {pageTitle: '新增付款'},
            controller: "PayController",
            resolve: {
                deps: ['$ocLazyLoad', function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        name: 'MetronicApp',
                        insertBefore: '#ng_load_plugins_before', // load the above css files before '#ng_load_plugins_before'
                        files: [
				'assets/global/plugins/datatables/datatables.min.css',
				'assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css',
				'assets/apps/css/special.css',
				'assets/global/plugins/datatables/datatables.all.min.js',
				'assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js',
				'assets/apps/scripts/pageHandle.js',
	        	'assets/apps/service/PayService.js',
				'assets/apps/controllers/PayController.js',
				'assets/apps/controllers/app.js',
				'assets/apps/controllers/uploadPhoto.js',
                      ]
                    });
                }]
            }
        })
        
        .state('viewPay', {
            url: "/viewPay:serialNum",
            templateUrl: "rest/page/viewPay",
            data: {pageTitle: '付款详情'},
            controller: "PayController",
            resolve: {
                deps: ['$ocLazyLoad', function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        name: 'MetronicApp',
                        insertBefore: '#ng_load_plugins_before', // load the above css files before '#ng_load_plugins_before'
                        files: [
				'assets/global/plugins/datatables/datatables.min.css',
				'assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css',
				'assets/apps/css/special.css',
				'assets/global/plugins/datatables/datatables.all.min.js',
				'assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js',
				'assets/apps/scripts/pageHandle.js',
				'assets/apps/service/PayService.js',
				'assets/apps/controllers/PayController.js',
                      ]
                    });
                }]
            }
        })
        
         .state('paymentRecordC', {
            url: "/paymentRecordC",
            templateUrl: "rest/page/paymentRecord",
            data: {pageTitle: '付款列表'},
            controller: "PayController",
            resolve: {
                deps: ['$ocLazyLoad', function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        name: 'MetronicApp',
                        insertBefore: '#ng_load_plugins_before', // load the above css files before '#ng_load_plugins_before'
                        files: [
				'assets/global/plugins/datatables/datatables.min.css',
				'assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css',
				'assets/apps/css/special.css',
				'assets/global/plugins/datatables/datatables.all.min.js',
				'assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js',
				'assets/apps/scripts/pageHandle.js',
				'assets/apps/service/PayService.js',
				'assets/apps/controllers/PayController.js',
				'assets/apps/controllers/app.js',
				'assets/apps/controllers/uploadPhoto.js',
                      ]
                    });
                }]
            }
        })
        
        .state('editPay', {
            url: "/editPay:serialNum",
            templateUrl: "rest/page/editPay",
            data: {pageTitle: '编辑付款'},
            controller: "PayController",
            resolve: {
                deps: ['$ocLazyLoad', function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        name: 'MetronicApp',
                        insertBefore: '#ng_load_plugins_before', // load the above css files before '#ng_load_plugins_before'
                        files: [
				'assets/global/plugins/datatables/datatables.min.css',
				'assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css',
				'assets/apps/css/special.css',
				'assets/global/plugins/datatables/datatables.all.min.js',
				'assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js',
				'assets/apps/scripts/pageHandle.js',
	        	'assets/apps/service/PayService.js',
				'assets/apps/controllers/PayController.js',
				'assets/apps/controllers/app.js',
				'assets/apps/controllers/uploadPhoto.js',
                      ]
                    });
                }]
            }
        })
        
        
        .state('addGatheringMoney', {
            url: "/addGatheringMoney",
            templateUrl: "rest/page/addGatheringMoney",
            data: {pageTitle: '新增收款'},
            controller: "GatheringMoneyController",
            resolve: {
                deps: ['$ocLazyLoad', function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        name: 'MetronicApp',
                        insertBefore: '#ng_load_plugins_before', // load the above css files before '#ng_load_plugins_before'
                        files: [
				'assets/global/plugins/datatables/datatables.min.css',
				'assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css',
				'assets/apps/css/special.css',
				'assets/global/plugins/datatables/datatables.all.min.js',
				'assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js',
				'assets/apps/scripts/pageHandle.js',
	        	'assets/apps/service/GatheringMoneyService.js',
				'assets/apps/controllers/GatheringMoneyController.js',
				'assets/apps/controllers/app.js',
				'assets/apps/controllers/uploadPhoto.js',
                      ]
                    });
                }]
            }
        })
        
        
        .state('gatheringMoneyRecord', {
            url: "/gatheringMoneyRecord",
            templateUrl: "rest/page/gatheringMoneyRecord",
            data: {pageTitle: '收款列表'},
            controller: "GatheringMoneyController",
            resolve: {
                deps: ['$ocLazyLoad', function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        name: 'MetronicApp',
                        insertBefore: '#ng_load_plugins_before', // load the above css files before '#ng_load_plugins_before'
                        files: [
				'assets/global/plugins/datatables/datatables.min.css',
				'assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css',
				'assets/apps/css/special.css',
				'assets/global/plugins/datatables/datatables.all.min.js',
				'assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js',
				'assets/apps/scripts/pageHandle.js',
	        	'assets/apps/service/GatheringMoneyService.js',
				'assets/apps/controllers/GatheringMoneyController.js',
				'assets/apps/controllers/app.js',
				'assets/apps/controllers/uploadPhoto.js',
                      ]
                    });
                }]
            }
        })
        
        
        .state('viewGatheringMoney', {
            url: "/viewGatheringMoney:serialNum",
            templateUrl: "rest/page/viewGatheringMoney",
            data: {pageTitle: '收款详情'},
            controller: "GatheringMoneyController",
            resolve: {
                deps: ['$ocLazyLoad', function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        name: 'MetronicApp',
                        insertBefore: '#ng_load_plugins_before', // load the above css files before '#ng_load_plugins_before'
                        files: [
				'assets/global/plugins/datatables/datatables.min.css',
				'assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css',
				'assets/apps/css/special.css',
				'assets/global/plugins/datatables/datatables.all.min.js',
				'assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js',
				'assets/apps/scripts/pageHandle.js',
				'assets/apps/service/GatheringMoneyService.js',
				'assets/apps/controllers/GatheringMoneyController.js',
                      ]
                    });
                }]
            }
        })
        
        .state('editGatheringMoney', {
            url: "/editGatheringMoney:serialNum",
            templateUrl: "rest/page/editGatheringMoney",
            data: {pageTitle: '编辑收款'},
            controller: "GatheringMoneyController",
            resolve: {
                deps: ['$ocLazyLoad', function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        name: 'MetronicApp',
                        insertBefore: '#ng_load_plugins_before', // load the above css files before '#ng_load_plugins_before'
                        files: [
				'assets/global/plugins/datatables/datatables.min.css',
				'assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css',
				'assets/apps/css/special.css',
				'assets/global/plugins/datatables/datatables.all.min.js',
				'assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js',
				'assets/apps/scripts/pageHandle.js',
				'assets/apps/service/GatheringMoneyService.js',
				'assets/apps/controllers/GatheringMoneyController.js',
				'assets/apps/controllers/app.js',
				'assets/apps/controllers/uploadPhoto.js',
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
