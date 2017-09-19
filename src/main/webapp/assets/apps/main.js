/*******************************************************************************
 * Metronic AngularJS App Main Script
 ******************************************************************************/

/* Metronic App */
// 定义模块时引入依赖
var MetronicApp = angular.module("MetronicApp", [ "ui.router", "ui.bootstrap",
		"oc.lazyLoad", "ngSanitize" ]);

/* Configure ocLazyLoader(refer: https://github.com/ocombe/ocLazyLoad) */
MetronicApp.config([ '$ocLazyLoadProvider', function($ocLazyLoadProvider) {
	$ocLazyLoadProvider.config({
	// global configs go here
	});
} ]);

// AngularJS v1.3.x workaround for old style controller declarition in HTML
MetronicApp.config([ '$controllerProvider', function($controllerProvider) {
	// this option might be handy for migrating old apps, but please don't use
	// it
	// in new ones!
	$controllerProvider.allowGlobals();
} ]);

/*******************************************************************************
 * END: BREAKING CHANGE in AngularJS v1.3.x:
 ******************************************************************************/

/* Setup global settings */
MetronicApp.factory('settings', [ '$rootScope', function($rootScope) {
	// supported languages
	var settings = {
		layout : {
			pageSidebarClosed : false, // sidebar menu state
			pageContentWhite : true, // set page content layout
			pageBodySolid : false, // solid body color state
			pageAutoScrollOnLoad : 1000
		// auto scroll to top on page load
		},
		assetsPath : 'assets',
		globalPath : 'assets/global',
		layoutPath : 'assets/layouts/layout2',
	};

	$rootScope.settings = settings;
	$rootScope.basePath = getRootPath();

	return settings;
} ]);

/* Setup App Main Controller */
MetronicApp.controller('AppController', [ '$scope', '$rootScope',
		function($scope, $rootScope) {
			$scope.$on('$viewContentLoaded', function() {
				// App.initComponents(); // init core components
				// Layout.init(); // Init entire layout(header, footer, sidebar,
				// etc) on page load if the partials included in server side
				// instead of loading with ng-include directive
			});
		} ]);

/*******************************************************************************
 * Layout Partials. By default the partials are loaded through AngularJS
 * ng-include directive. In case they loaded in server side(e.g: PHP include
 * function) then below partial initialization can be disabled and Layout.init()
 * should be called on page load complete as explained above.
 ******************************************************************************/

/* Setup Layout Part - Header */
MetronicApp.controller('HeaderController', [ '$scope', function($scope) {
	$scope.$on('$includeContentLoaded', function() {
		Layout.initHeader(); // init header
		
	});
} ]);

/* Setup Layout Part - Sidebar */
MetronicApp.controller('SidebarController', [ '$state', '$scope',
		function($state, $scope) {
			$scope.$on('$includeContentLoaded', function() {
				Layout.initSidebar($state); // init sidebar
			});
		} ]);

/* Setup Layout Part - Quick Sidebar */
MetronicApp.controller('QuickSidebarController', [ '$scope', function($scope) {
	$scope.$on('$includeContentLoaded', function() {
		setTimeout(function() {
			QuickSidebar.init(); // init quick sidebar
		}, 2000)
	});
} ]);

/* Setup Layout Part - Theme Panel */
MetronicApp.controller('ThemePanelController', [ '$scope', function($scope) {
	$scope.$on('$includeContentLoaded', function() {
		Demo.init(); // init theme panel
		// 设置默认样式
		$('.layout-option', $('.theme-panel')).val("fluid");// 设置布局为顺序
		$('.sidebar-style-option', $('.theme-panel')).val("compact");// 工具栏风格为紧凑
		$('.layout-option').trigger("change");
		
		$('ul > li', $('.theme-panel')).removeClass("current");
		$('.theme-colors > ul > .color-blue', $('.theme-panel')).addClass("current");
        
        
	});
} ]);

/* Setup Layout Part - Footer */
MetronicApp.controller('FooterController', [ '$scope', function($scope) {
	$scope.$on('$includeContentLoaded', function() {
		Layout.initFooter(); // init footer
	});
} ]);

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
            url: "/priceList?:buyOrSale",
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
                                	 'assets/global/plugins/bootbox/bootbox.min.js',
                                	  'assets/apps/service/orderService.js'
                        ]
                    });
                }]
            }
        })
        
          .state('addPriceList', {
            url: "/addPriceList?:buyOrSale",
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
    				        'assets/apps/scripts/FileUploader.js',
    				        'assets/apps/service/orderService.js'
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
            url: "/saleOrder?:tabHref",
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
            url: "/buyOrder?:tabHref",
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
                            'assets/apps/controllers/buyOrderController.js',
                          //流程申请
							'assets/global/css/dialog.css',
							'assets/global/css/easyui.css',
							'assets/global/css/datagrid.css',
							'assets/global/css/jquery.qtip.min.css',
				         
							'assets/global/plugins/jquery.easyui.min.js',
							'assets/global/plugins/jquery.qtip.min.js',
							'assets/global/plugins/jquery.outerhtml.js'
                        ]
                    });
                }]
            }
        })
        //供应商订单列表
        .state('supplyOrder', {
            url: "/supplyOrder?:tabHref",
            templateUrl: "rest/page/supplyOrder",
            data: {pageTitle: '订单'},
            controller: "supplyOrderController",
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
                            'assets/apps/controllers/supplyOrderController.js',
                          
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
        // 查看采购订单
        .state('viewBuyOrder', {
            url: "/viewBuyOrder?:serialNum",
            templateUrl: "rest/page/viewBuyOrder",
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
        // 供应商查看订单
        .state('viewSupplyOrder', {
            url: "/viewSupplyOrder?:serialNum",
            templateUrl: "rest/page/viewSupplyOrder",
            data: {pageTitle: '新增采购订单'},
            controller: "supplyOrderController",
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
				'assets/apps/controllers/supplyOrderController.js'
                      ]
                    });
                }]
            }
        })
        
        // 采购订单发起申请
        .state('submitBuyApply', {
            url: "/submitBuyApply?:serialNum&:view",
            templateUrl: "rest/page/submitBuyApply",
            data: {pageTitle: '采购订单申请'},
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
        // 审批订单
        .state('approvalBuyApply', {
            url: "/approvalBuyApply?:serialNum&:view",
            params:{"serialNum":null,"taskId":null, "comments":null,"processInstanceId":null},
            templateUrl: "rest/page/approvalBuyApply",
            data: {pageTitle: '审批采购订单'},
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
        // 重新编辑采购订单申请
        .state('editBuyApply', {
            url: "/editBuyApply?:serialNum&:view",
            params:{"serialNum":null,"taskId":null, "comments":null,"processInstanceId":null},
            templateUrl: "rest/page/editBuyApply",
            data: {pageTitle: '重新编辑订单'},
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
        
        //对账单
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
            url: "/statementBuyAdd?:serialNum&:view",
            templateUrl: "rest/statement/statementBuyAdd",
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
	        	'assets/apps/service/statementService.js',
				'assets/apps/controllers/statementController.js'
                      ]
                    });
                }]
            }
        })
        .state('viewBuyStatement', {
        	url: "/statementBuyView?:serialNum&:view",
        	templateUrl: "rest/statement/statementBuyView",
        	data: {pageTitle: '新增客户对账单'},
        	controller: "statementController",
        	resolve: {
        		deps: ['$ocLazyLoad', function($ocLazyLoad) {
        			return $ocLazyLoad.load({
        				name: 'MetronicApp',
        				insertBefore: '#ng_load_plugins_before', // load the above css files before '#ng_load_plugins_before'
        				files: [
        				        'assets/apps/service/statementService.js',
        				        'assets/apps/controllers/statementController.js'
        				        ]
        			});
        		}]
        	}
        })
        // 新增供应商对账单
        .state('addSupplyStatement', {
            url: "/statementSupplyAdd?:serialNum&:view",
            templateUrl: "rest/statement/statementSupplyAdd",
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
	        	'assets/apps/service/statementService.js',
				'assets/apps/controllers/statementController.js'
                      ]
                    });
                }]
            }
        })
        // 查看供应商对账单
        .state('viewSupplyStatement', {
        	url: "/statementSupplyView?:serialNum&:view",
        	templateUrl: "rest/statement/statementSupplyView",
        	data: {pageTitle: '查看供应商对账单'},
        	controller: "statementController",
        	resolve: {
        		deps: ['$ocLazyLoad', function($ocLazyLoad) {
        			return $ocLazyLoad.load({
        				name: 'MetronicApp',
        				insertBefore: '#ng_load_plugins_before', // load the above css files before '#ng_load_plugins_before'
        				files: [
        				        'assets/apps/service/statementService.js',
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
	        	url: "/demandPlanAdd?:serialNum&:view",
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
		    	url: "/takeDelivery?:tabHref",
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
		    				        'assets/global/plugins/bootstrap-select/css/bootstrap-select.css',
		    				        'assets/global/css/dialog.css',
							         'assets/global/css/easyui.css',
							         'assets/global/css/datagrid.css',
							         'assets/global/css/jquery.qtip.min.css',
							         'assets/apps/service/CommonService.js',
							         'assets/global/plugins/jquery.easyui.min.js',
							         'assets/global/plugins/jquery.qtip.min.js',
							         'assets/global/plugins/jquery.outerhtml.js',
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
		    				        'assets/global/plugins/datatables/datatables.min.css',                  
		    				        'assets/global/plugins/datatables/datatables.all.min.js',
		    				        'assets/apps/controllers/TakeDeliveryController.js',
		    				        'assets/apps/service/TakeDeliveryService.js',
		    				        'assets/apps/service/CommonService.js',
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
		    				        'assets/apps/service/orderService.js',
		    				        'assets/apps/service/CommonService.js',
		    				        'assets/global/css/components-rounded.min.css'
		    				        ]
		    			});
		    		}]
		    	}	        
		    }) .state('toTakeDelivery', {
		    	url: "/toTakeDelivery?:serialNum",
		    	templateUrl: "rest/takeDelivery/takeDelivery",
		    	data: {pageTitle: '收货'},
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
		    				        'assets/apps/service/orderService.js',
		    				        'assets/apps/service/CommonService.js'
		    				        ]
		    			});
		    		}]
		    	}	        
		    }) .state('takeDeliveryAudit', {
		    	url: "/takeDeliveryAudit?:serialNum&:taskId&:comments",
		    	templateUrl: "rest/takeDelivery/takeDeliveryAudit",
		    	data: {pageTitle: '收货审批'},
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
		    				        'assets/apps/service/CommonService.js',
		    				        'assets/apps/service/orderService.js'
		    				        ]
		    			});
		    		}]
		    	}	        
		    }) .state('takeDeliveryAdjustment', {
		    	url: "/takeDeliveryAdjustment?:serialNum&:taskId&:comments",
		    	templateUrl: "rest/takeDelivery/takeDeliveryAdjustment",
		    	data: {pageTitle: '收货调整'},
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
		    				        'assets/apps/service/CommonService.js',
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
		    				        'assets/global/plugins/datatables/datatables.min.css',                  
		    				        'assets/global/plugins/datatables/datatables.all.min.js',
		    				        'assets/apps/controllers/StockInController.js',
		    				        'assets/apps/service/TakeDeliveryService.js'
		    				        ]
		    			});
		    		}]
		    	}	        
		    }).state('stockIn', {//执行入库
		    	url: "/stockIn?:serialNum",
		    	templateUrl: "rest/takeDelivery/stockIn",
		    	data: {pageTitle: '入库'},
		    	reload:true, 
		    	controller: "StockInController",
		    	resolve: {
		    		deps: ['$ocLazyLoad', function($ocLazyLoad) {
		    			return $ocLazyLoad.load({
		    				name: 'MetronicApp',
		    				insertBefore: '#ng_load_plugins_before', // load the above css files before '#ng_load_plugins_before'
		    				files: [
		    				        'assets/global/plugins/datatables/datatables.min.css',                  
		    				        'assets/global/plugins/datatables/datatables.all.min.js',
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
		    }).state('stockOutAdd', {
		    	url: "/stockOutAdd?:serialNum",
		    	templateUrl: "rest/takeDelivery/stockOutAdd",
		    	data: {pageTitle: '新建出库单'},
		    	reload:true, 
		    	controller: "StockOutController",
		    	resolve: {
		    		deps: ['$ocLazyLoad', function($ocLazyLoad) {
		    			return $ocLazyLoad.load({
		    				name: 'MetronicApp',
		    				insertBefore: '#ng_load_plugins_before', // load the above css files before '#ng_load_plugins_before'
		    				files: [
		    				        'assets/global/plugins/datatables/datatables.min.css',                  
		    				        'assets/global/plugins/datatables/datatables.all.min.js',
		    				        'assets/apps/controllers/StockOutController.js',
		    				        'assets/apps/service/TakeDeliveryService.js'
		    				        ]
		    			});
		    		}]
		    	}	        
		    }).state('stockOut', {
		    	url: "/stockOut?:serialNum",
		    	templateUrl: "rest/takeDelivery/stockOut",
		    	data: {pageTitle: '出库'},
		    	reload:true, 
		    	controller: "StockOutController",
		    	resolve: {
		    		deps: ['$ocLazyLoad', function($ocLazyLoad) {
		    			return $ocLazyLoad.load({
		    				name: 'MetronicApp',
		    				insertBefore: '#ng_load_plugins_before', // load the above css files before '#ng_load_plugins_before'
		    				files: [
		    				        'assets/global/plugins/datatables/datatables.min.css',                  
		    				        'assets/global/plugins/datatables/datatables.all.min.js',
		    				        'assets/apps/controllers/StockOutController.js',
		    				        'assets/apps/service/TakeDeliveryService.js'
		    				        ]
		    			});
		    		}]
		    	}	        
		    }).state('stockOutView', {
		    	url: "/stockOutView?:serialNum",
		    	templateUrl: "rest/takeDelivery/stockOutView",
		    	data: {pageTitle: '查看出库单'},
		    	reload:true, 
		    	controller: "StockOutController",
		    	resolve: {
		    		deps: ['$ocLazyLoad', function($ocLazyLoad) {
		    			return $ocLazyLoad.load({
		    				name: 'MetronicApp',
		    				insertBefore: '#ng_load_plugins_before', // load the above css files before '#ng_load_plugins_before'
		    				files: [       
		    				        'assets/apps/controllers/StockOutController.js',
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
	            templateUrl:"rest/stock/viewStockList?:stockSerialNum",
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
		    .state('confirmStockInOutCheck', {//出入库检验确认检验
		        	url: "/confirmStockInOutCheck?:inOrOut",
		        	templateUrl: "rest/stockInOut/confirmStockInOutCheck",
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
            templateUrl: "rest/delivery/addDelivery",
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
            url: "/delivery?:tabHref",
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
				'assets/apps/controllers/DeliveryController.js',
				
				//流程申请
				'assets/global/css/dialog.css',
				'assets/global/css/easyui.css',
				'assets/global/css/datagrid.css',
				'assets/global/css/jquery.qtip.min.css',
	         
				'assets/global/plugins/jquery.easyui.min.js',
				'assets/global/plugins/jquery.qtip.min.js',
				'assets/global/plugins/jquery.outerhtml.js',
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
        
        
        .state('applyDelivery', {
        	params:{data:null},
            url: "/applyDelivery:serialNum",
            templateUrl: "rest/delivery/applyDelivery",
            data: {pageTitle: '发货申请'},
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
        
        
         .state('auditDelivery', {
            url: "/auditDelivery",
            templateUrl: "rest/delivery/auditDelivery",
            data: {pageTitle: '发货审批'},
            params:{"serialNum":null,"taskId":null, "comments":null},
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
        
        .state('editAuditDelivery', {
            url: "/editAuditDelivery",
            templateUrl: "rest/delivery/editAuditDelivery",
            data: {pageTitle: '调整发货申请'},
            params:{"serialNumEdit":null,"taskId":null, "comments":null},
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
 // 请假管理
		.state(
				'vacation',
				{
					url : "/vacation?tabHref",
					templateUrl : "rest/page/addVacation",
					data : {
						pageTitle : '请假'
					},
					controller : "AddVacationController",
					resolve : {
						deps : [
								'$ocLazyLoad',
								function($ocLazyLoad) {
									return $ocLazyLoad
											.load({
												name : 'MetronicApp',
												insertBefore : '#ng_load_plugins_before', 
												files : [
												         'assets/global/plugins/datatables/datatables.min.css',
												         'assets/global/css/dialog.css',
												         'assets/global/css/easyui.css',
												         'assets/global/css/datagrid.css',
												         'assets/global/css/jquery.qtip.min.css',
												         
												         'assets/global/plugins/jquery.easyui.min.js',
												         'assets/global/plugins/jquery.qtip.min.js',
												         'assets/global/plugins/jquery.outerhtml.js',
														'assets/global/plugins/datatables/datatables.all.min.js',
														'assets/apps/service/vacation/AddVacationService.js',
														'assets/apps/controllers/vacation/AddVacationController.js' 
														]
											});
								} ]
					}
				})
        .state('invoice', {
            url: "/invoice?:inOrOut",
            templateUrl:"rest/invoice/viewInvoiceList",
            data: {pageTitle: '发票'},
            controller: "InvoiceController",
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
			        'assets/apps/service/InvoiceService.js',
					'assets/apps/controllers/InvoiceController.js',
					'assets/apps/scripts/angular-file-upload-shim.min.js',
                	'assets/apps/scripts/angular-file-upload.min.js'
                   	                        ]
                    });
                }]
            }
        })
        .state('addOrEditInvoice', {//发票新建编辑
        	url: "/addOrEditInvoice?:inOrOut",
        	templateUrl: "rest/invoice/addOrEditInvoice",
        	data: {pageTitle: '新建发票信息'},
        	reload:true, 
        	controller: "InvoiceController",
        	resolve: {
        		deps: ['$ocLazyLoad', function($ocLazyLoad) {
        			return $ocLazyLoad.load({
        				name: 'MetronicApp',
        				insertBefore: '#ng_load_plugins_before', // load the above css files before '#ng_load_plugins_before'
        				files: [       
        				        'assets/global/plugins/datatables/datatables.min.css',                  
        				        'assets/global/plugins/datatables/datatables.all.min.js',
        				        'assets/apps/service/InvoiceService.js',
        						'assets/apps/controllers/InvoiceController.js',
        						 'assets/apps/scripts/pageHandle.js',
        						 'assets/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.min.css',
         				        'assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js',
        				        'assets/global/plugins/bootstrap-select/js/bootstrap-select.min.js',
        				        'assets/global/plugins/bootstrap-select/css/bootstrap-select.css',
        				        'assets/apps/scripts/angular-file-upload-shim.min.js',
        				        'assets/apps/scripts/angular-file-upload.min.js',
        				        'assets/apps/scripts/FileUploader.js'
        				        ]
        			});
        		}]
        	}	        
        })
        
        .state('invoiceView', {//出入库检验查看
	        	url: "/invoiceView?:inOrOut",
	        	templateUrl: "rest/invoice/viewInvoice",
	        	data: {pageTitle: '发票详情'},
	        	reload:true, 
	        	controller: "InvoiceController",
	        	resolve: {
	        		deps: ['$ocLazyLoad', function($ocLazyLoad) {
	        			return $ocLazyLoad.load({
	        				name: 'MetronicApp',
	        				insertBefore: '#ng_load_plugins_before', // load the above css files before '#ng_load_plugins_before'
	        				files: [       
	        				        'assets/global/plugins/datatables/datatables.min.css',                  
	        				        'assets/global/plugins/datatables/datatables.all.min.js',
	        				        'assets/apps/service/InvoiceService.js',
	        						'assets/apps/controllers/InvoiceController.js',
	        				        'assets/global/plugins/bootstrap-select/js/bootstrap-select.min.js',
	        				        'assets/global/plugins/bootstrap-select/css/bootstrap-select.css',
	        				        'assets/pages/scripts/table-datatables-scroller.min.js'
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
				
				'assets/global/plugins/datatables/datatables.all.min.js',
				'assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js',
				'assets/apps/scripts/angular-file-upload-shim.min.js',
			    'assets/apps/scripts/angular-file-upload.min.js',
			    'assets/apps/scripts/FileUploader.js',
				'assets/apps/scripts/pageHandle.js',
	        	'assets/apps/service/PayService.js',
				'assets/apps/controllers/PayController.js',
				'assets/apps/controllers/app.js',
				'assets/apps/controllers/uploadPhoto.js'
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
				
				'assets/global/plugins/datatables/datatables.all.min.js',
				'assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js',
				'assets/apps/scripts/angular-file-upload-shim.min.js',
				'assets/apps/scripts/angular-file-upload.min.js',
				'assets/apps/scripts/FileUploader.js',
				'assets/apps/scripts/pageHandle.js',
				'assets/apps/service/PayService.js',
				'assets/apps/controllers/PayController.js',
				'assets/apps/controllers/app.js',
				'assets/apps/controllers/uploadPhoto.js'
                      ]
                    });
                }]
            }
        })
        
        .state('applyPay', {
            url: "/applyPay:serialNum",
            templateUrl: "rest/page/applyPay",
            data: {pageTitle: '应付款申请'},
            controller: "PayController",
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
								'assets/apps/scripts/pageHandle.js',
								'assets/apps/service/PayService.js',
								'assets/apps/controllers/PayController.js',
								'assets/apps/controllers/app.js',
								'assets/apps/controllers/uploadPhoto.js'
                      ]
                    });
                }]
            }
        })
        
        .state('auditPay', {
            url: "/auditPay",
            templateUrl: "rest/page/auditPay",
            data: {pageTitle: '应付款审批'},
            params:{"serialNum":null,"taskId":null, "comments":null},
            controller: "PayController",
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
								'assets/apps/scripts/pageHandle.js',
								'assets/apps/service/PayService.js',
								'assets/apps/controllers/PayController.js',
								'assets/apps/controllers/app.js',
								'assets/apps/controllers/uploadPhoto.js'
                      ]
                    });
                }]
            }
        })
        
        .state('editAuditPay', {
            url: "/editAuditPay",
            templateUrl: "rest/page/editAuditPay",
            data: {pageTitle: '调整应付款申请'},
            params:{"serialNum":null,"taskId":null, "comments":null},
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
								'assets/apps/scripts/angular-file-upload-shim.min.js',
								'assets/apps/scripts/angular-file-upload.min.js',
								'assets/apps/scripts/FileUploader.js',
								'assets/apps/scripts/pageHandle.js',
								'assets/apps/service/PayService.js',
								'assets/apps/controllers/PayController.js',
								'assets/apps/controllers/app.js',
								'assets/apps/controllers/uploadPhoto.js'
                      ]
                    });
                }]
            }
        })
        
         .state('paymentRecordC', {
            url: "/paymentRecordC?tabHref",
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
								
								
								'assets/global/scripts/datatable.js',
								//'assets/global/plugins/datatables/datatables.min.js',
								'assets/global/plugins/datatables/datatables.all.min.js',
								'assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js',
								'assets/apps/scripts/pageHandle.js',
								'assets/apps/service/PayService.js',
							    'assets/apps/scripts/angular-file-upload.min.js',
								'assets/apps/controllers/PayController.js',
								'assets/apps/controllers/app.js',
								'assets/apps/controllers/uploadPhoto.js',
				
				
								//流程申请
								'assets/global/css/dialog.css',
								'assets/global/css/easyui.css',
								'assets/global/css/datagrid.css',
								'assets/global/css/jquery.qtip.min.css',
					         
								'assets/global/plugins/jquery.easyui.min.js',
								'assets/global/plugins/jquery.qtip.min.js',
								'assets/global/plugins/jquery.outerhtml.js',
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
				
				'assets/global/plugins/datatables/datatables.all.min.js',
				'assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js',
				'assets/apps/scripts/angular-file-upload-shim.min.js',
			    'assets/apps/scripts/angular-file-upload.min.js',
			    'assets/apps/scripts/FileUploader.js',
				'assets/apps/scripts/pageHandle.js',
	        	'assets/apps/service/PayService.js',
				'assets/apps/controllers/PayController.js',
				'assets/apps/controllers/app.js',
				'assets/apps/controllers/uploadPhoto.js'
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
				/*'assets/global/plugins/datatables/datatables.min.css',
				'assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css',
				
				'assets/global/plugins/datatables/datatables.all.min.js',
				'assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js',
				'assets/apps/scripts/pageHandle.js',
	        	'assets/apps/service/GatheringMoneyService.js',
				'assets/apps/controllers/GatheringMoneyController.js',
				'assets/apps/controllers/app.js',
				'assets/apps/controllers/uploadPhoto.js',*/
				'assets/global/plugins/datatables/datatables.min.css',
				'assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css',
				
				'assets/global/plugins/datatables/datatables.all.min.js',
				'assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js',
				'assets/apps/scripts/angular-file-upload-shim.min.js',
			    'assets/apps/scripts/angular-file-upload.min.js',
			    'assets/apps/scripts/FileUploader.js',
				'assets/apps/scripts/pageHandle.js',
				'assets/apps/service/GatheringMoneyService.js',
				'assets/apps/controllers/GatheringMoneyController.js',
				'assets/apps/controllers/app.js',
				'assets/apps/controllers/uploadPhoto.js'
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
				/*'assets/global/plugins/datatables/datatables.min.css',
				'assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css',
				
				'assets/global/plugins/datatables/datatables.all.min.js',
				'assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js',
				'assets/apps/scripts/pageHandle.js',
	        	'assets/apps/service/GatheringMoneyService.js',
				'assets/apps/controllers/GatheringMoneyController.js',
				'assets/apps/controllers/app.js',
				'assets/apps/controllers/uploadPhoto.js',*/
				'assets/global/plugins/datatables/datatables.min.css',
				'assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css',
				
				'assets/global/plugins/datatables/datatables.all.min.js',
				'assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js',
				'assets/apps/scripts/pageHandle.js',
				'assets/apps/service/GatheringMoneyService.js',
				'assets/apps/scripts/angular-file-upload.min.js',
				'assets/apps/scripts/pageHandle.js',
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
				/*'assets/global/plugins/datatables/datatables.min.css',
				'assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css',
				
				'assets/global/plugins/datatables/datatables.all.min.js',
				'assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js',
				'assets/apps/scripts/pageHandle.js',
				'assets/apps/service/GatheringMoneyService.js',
				'assets/apps/controllers/GatheringMoneyController.js',*/
				'assets/global/plugins/datatables/datatables.min.css',
				'assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css',
				
				'assets/global/plugins/datatables/datatables.all.min.js',
				'assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js',
				'assets/apps/scripts/angular-file-upload-shim.min.js',
				'assets/apps/scripts/angular-file-upload.min.js',
				'assets/apps/scripts/FileUploader.js',
				'assets/apps/scripts/pageHandle.js',
				'assets/apps/service/GatheringMoneyService.js',
				'assets/apps/controllers/GatheringMoneyController.js',
				'assets/apps/controllers/app.js',
				'assets/apps/controllers/uploadPhoto.js'
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
				/*'assets/global/plugins/datatables/datatables.min.css',
				'assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css',
				
				'assets/global/plugins/datatables/datatables.all.min.js',
				'assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js',
				'assets/apps/scripts/pageHandle.js',
				'assets/apps/service/GatheringMoneyService.js',
				'assets/apps/controllers/GatheringMoneyController.js',
				'assets/apps/controllers/app.js',
				'assets/apps/controllers/uploadPhoto.js',*/
				'assets/global/plugins/datatables/datatables.min.css',
				'assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css',
				
				'assets/global/plugins/datatables/datatables.all.min.js',
				'assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js',
				'assets/apps/scripts/angular-file-upload-shim.min.js',
			    'assets/apps/scripts/angular-file-upload.min.js',
			    'assets/apps/scripts/FileUploader.js',
				'assets/apps/scripts/pageHandle.js',
				'assets/apps/service/GatheringMoneyService.js',
				'assets/apps/controllers/GatheringMoneyController.js',
				'assets/apps/controllers/app.js',
				'assets/apps/controllers/uploadPhoto.js'
                      ]
                    });
                }]
            }
        })}]);



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
MetronicApp.run([ "$rootScope", "settings", "$state",
		function($rootScope, settings, $state) {
			$rootScope.$state = $state; // state to be accessed from view
			$rootScope.$settings = settings; // state to be accessed from
												// view
		} ]);

MetronicApp.run(['$rootScope', '$window', '$location', '$log', '$compile', function ($rootScope, $window, $location, $log, $compile) {
	
	$rootScope.$on('$stateChangeSuccess', 
			function(event, toState, toParams, fromState, fromParams){
				
		
			   var html = '';
			   if('dashboard' == toState.name){//首页
					 html="<li><i class='fa fa-home'></i> <a>首页</a></li>";					 
			   }else if('materiel' == toState.name){//物料信息
					 html="<li><i class='fa fa-home'></i> <a ui-sref='dashboard'>首页</a> <i class='fa fa-angle-right'></i></li>" +
				 		"<li><a>基础数据</a><i class='fa fa-angle-right'></i></li>" +
				 		"<li><a>物料信息</a></li>";					 
			   }else if('addMateriel' == toState.name){//新增物料
				   html="<li><i class='fa fa-home'></i> <a ui-sref='dashboard'>首页</a> <i class='fa fa-angle-right'></i></li>" +
			 		"<li><a>基础数据</a><i class='fa fa-angle-right'></i></li>" +
			 		"<li><a ui-sref='materiel'>物料信息</a><i class='fa fa-angle-right'></i></li>";
				   if(toParams.serialNum != undefined){
					   html += "<li><a>修改物料</a></li>";
				   }else html += "<li><a>新增物料</a></li>";
			   }else if('company' == toState.name){//企业信息
					 html="<li><i class='fa fa-home'></i> <a ui-sref='dashboard'>首页</a> <i class='fa fa-angle-right'></i></li>" +
				 		"<li><a>基础数据</a><i class='fa fa-angle-right'></i></li>" +
				 		"<li><a>企业信息</a></li>";					 
			   }else if('companyAdd' == toState.name){//新增企业
					 html="<li><i class='fa fa-home'></i> <a ui-sref='dashboard'>首页</a> <i class='fa fa-angle-right'></i></li>" +
				 		"<li><a>基础数据</a><i class='fa fa-angle-right'></i></li>" +
				 		"<li><a ui-sref='company'>企业信息</a><i class='fa fa-angle-right'></i></li>";
					 if(toParams.comId != undefined){
						 html += "<li><a>修改企业信息</a></li>";
					 } else html += "<li><a>新增企业</a></li>";
			   }else if('userContract' == toState.name){//合同管理
					 html="<li><i class='fa fa-home'></i> <a ui-sref='dashboard'>首页</a> <i class='fa fa-angle-right'></i></li>" +
				 		"<li><a>基础数据</a><i class='fa fa-angle-right'></i></li>" +
				 		"<li><a>合同信息</a></li>";					 
			   }else if('addUserContract' == toState.name){//新增“其他合同”
					 html="<li><i class='fa fa-home'></i> <a ui-sref='dashboard'>首页</a> <i class='fa fa-angle-right'></i></li>" +
				 		"<li><a>基础数据</a><i class='fa fa-angle-right'></i></li>" +
				 		"<li><a ui-sref='userContract'>合同信息</a><i class='fa fa-angle-right'></i></li>" + 
					 	"<li><a>新增合同</a></li>";
			   }else if('editUserContractPage' == toState.name){//修改“其他合同”
					 html="<li><i class='fa fa-home'></i> <a ui-sref='dashboard'>首页</a> <i class='fa fa-angle-right'></i></li>" +
				 		"<li><a>基础数据</a><i class='fa fa-angle-right'></i></li>" +
				 		"<li><a ui-sref='userContract'>合同信息</a><i class='fa fa-angle-right'></i></li>" + 
					 	"<li><a>修改合同</a></li>";
			   }else if('warehouse' == toState.name){//仓库管理
					 html="<li><i class='fa fa-home'></i> <a ui-sref='dashboard'>首页</a> <i class='fa fa-angle-right'></i></li>" +
				 		"<li><a>基础数据</a><i class='fa fa-angle-right'></i></li>" +
				 		"<li><a>仓库列表</a></li>";					 
			   }else if('addWarehouse' == toState.name){//新增仓库
					 html="<li><i class='fa fa-home'></i> <a ui-sref='dashboard'>首页</a> <i class='fa fa-angle-right'></i></li>" +
				 		"<li><a>基础数据</a><i class='fa fa-angle-right'></i></li>" +
				 		"<li><a ui-sref='warehouse'>仓库列表</a><i class='fa fa-angle-right'></i></li>";
				 		if(toParams.warehouseSerialNum != undefined){
							 html += "<li><a>修改仓库信息</a></li>";
						 } else html += "<li><a>新增仓库</a></li>";
			   }else if('demandPlan' == toState.name){//需求计划
					 html="<li><i class='fa fa-home'></i> <a ui-sref='dashboard'>首页</a> <i class='fa fa-angle-right'></i></li>" +
				 		"<li><a>销售订单</a><i class='fa fa-angle-right'></i></li>" +
				 		"<li><a>需求计划列表</a></li>";					 
			   }else if('demandPlanAdd' == toState.name){//新增需求计划
					 html="<li><i class='fa fa-home'></i> <a ui-sref='dashboard'>首页</a> <i class='fa fa-angle-right'></i></li>" +
				 		"<li><a>销售订单</a><i class='fa fa-angle-right'></i></li>" +
				 		"<li><a ui-sref='demandPlan'>需求计划列表</a><i class='fa fa-angle-right'></i></li>";
				 		if(toParams.serialNum != undefined){
							 html += "<li><a>修改需求计划</a></li>";
						 } else html += "<li><a>新增需求计划</a></li>";
			   }else if('demandPlanView' == toState.name){//查看需求计划
					 html="<li><i class='fa fa-home'></i> <a ui-sref='dashboard'>首页</a> <i class='fa fa-angle-right'></i></li>" +
				 		"<li><a>销售订单</a><i class='fa fa-angle-right'></i></li>" +
				 		"<li><a ui-sref='demandPlan'>需求计划列表</a><i class='fa fa-angle-right'></i></li>" + 
				 		"<li><a>查看需求计划</a></li>";
			   }else if('saleOrder' == toState.name){//销售订单
					 html="<li><i class='fa fa-home'></i> <a ui-sref='dashboard'>首页</a> <i class='fa fa-angle-right'></i></li>" +
				 		"<li><a>销售订单</a><i class='fa fa-angle-right'></i></li>" +
				 		"<li><a>销售订单列表</a></li>";					 
			   }else if('addSaleOrder' == toState.name){//新增销售订单
					 html="<li><i class='fa fa-home'></i> <a ui-sref='dashboard'>首页</a> <i class='fa fa-angle-right'></i></li>" +
				 		"<li><a>销售订单</a><i class='fa fa-angle-right'></i></li>" +
				 		"<li><a ui-sref='saleOrder'>销售订单列表</a><i class='fa fa-angle-right'></i></li>";
				 		if(toParams.serialNum != undefined){
							 html += "<li><a>修改销售订单</a></li>";
						 } else html += "<li><a>新增销售订单</a></li>";
			   }else if('buyOrder' == toState.name){//采购订单
					 html="<li><i class='fa fa-home'></i> <a ui-sref='dashboard'>首页</a> <i class='fa fa-angle-right'></i></li>" +
				 		"<li><a>采购订单</a><i class='fa fa-angle-right'></i></li>" +
				 		"<li><a>采购订单列表</a></li>";					 
			   }else if('supplyOrder' == toState.name){//采购订单
					 html="<li><i class='fa fa-home'></i> <a ui-sref='dashboard'>首页</a> <i class='fa fa-angle-right'></i></li>" +
				 		"<li><a>销售订单</a><i class='fa fa-angle-right'></i></li>" +
				 		"<li><a>订单列表</a></li>";					 
			   }else if('addBuyOrder' == toState.name){//新增采购订单
					 html="<li><i class='fa fa-home'></i> <a ui-sref='dashboard'>首页</a> <i class='fa fa-angle-right'></i></li>" +
				 		"<li><a>采购订单</a><i class='fa fa-angle-right'></i></li>" +
				 		"<li><a ui-sref='buyOrder'>采购订单列表</a><i class='fa fa-angle-right'></i></li>";
				 		if(toParams.serialNum != undefined){
							 html += "<li><a>修改采购订单</a></li>";
						 } else html += "<li><a>新增采购订单</a></li>";
			   }else if('viewBuyOrder' == toState.name){//查看采购订单
					 html="<li><i class='fa fa-home'></i> <a ui-sref='dashboard'>首页</a> <i class='fa fa-angle-right'></i></li>" +
				 		"<li><a>采购订单</a><i class='fa fa-angle-right'></i></li>" +
				 		"<li><a ui-sref='buyOrder'>采购订单列表</a><i class='fa fa-angle-right'></i></li>" + 
				 		"<li><a>查看采购订单</a></li>";
			   }else if('viewSupplyOrder' == toState.name){//查看供应商订单
					 html="<li><i class='fa fa-home'></i> <a ui-sref='dashboard'>首页</a> <i class='fa fa-angle-right'></i></li>" +
				 		"<li><a>销售订单</a><i class='fa fa-angle-right'></i></li>" +
				 		"<li><a ui-sref='supplyOrder'>订单列表</a><i class='fa fa-angle-right'></i></li>" + 
				 		"<li><a>查看订单</a></li>";
			   }else if('submitBuyApply' == toState.name){//采购订单发起申请
					 html="<li><i class='fa fa-home'></i> <a ui-sref='dashboard'>首页</a> <i class='fa fa-angle-right'></i></li>" +
				 		"<li><a>采购订单</a><i class='fa fa-angle-right'></i></li>" +
				 		"<li><a ui-sref='buyOrder'>采购订单列表</a><i class='fa fa-angle-right'></i></li>" + 
				 		"<li><a>采购订单申请</a></li>";
			   }else if('approvalBuyApply' == toState.name){//采购订单审批
					 html="<li><i class='fa fa-home'></i> <a ui-sref='dashboard'>首页</a> <i class='fa fa-angle-right'></i></li>" +
				 		"<li><a>采购订单</a><i class='fa fa-angle-right'></i></li>" +
				 		"<li><a ui-sref='buyOrder'>采购订单列表</a><i class='fa fa-angle-right'></i></li>" + 
				 		"<li><a>采购订单审批</a></li>";
			   }else if('editBuyApply' == toState.name){//重新编辑采购订单申请
					 html="<li><i class='fa fa-home'></i> <a ui-sref='dashboard'>首页</a> <i class='fa fa-angle-right'></i></li>" +
				 		"<li><a>采购订单</a><i class='fa fa-angle-right'></i></li>" +
				 		"<li><a ui-sref='buyOrder'>采购订单列表</a><i class='fa fa-angle-right'></i></li>" + 
				 		"<li><a>重新发起订单申请</a></li>";
			   }else if('purchaseForecast' == toState.name){//采购预测
					 html="<li><i class='fa fa-home'></i> <a ui-sref='dashboard'>首页</a> <i class='fa fa-angle-right'></i></li>" +
				 		"<li><a>采购订单</a><i class='fa fa-angle-right'></i></li>" +
				 		"<li><a>采购预测列表</a></li>";					 
			   }else if('stock' == toState.name){//库存
					 html="<li><i class='fa fa-home'></i> <a ui-sref='dashboard'>首页</a> <i class='fa fa-angle-right'></i></li>" +
				 		"<li><a>仓储</a><i class='fa fa-angle-right'></i></li>" +
				 		"<li><a>库存</a></li>";					 
			   }else if('addOrEditStock' == toState.name){//新增库存
				   html="<li><i class='fa fa-home'></i> <a ui-sref='dashboard'>首页</a> <i class='fa fa-angle-right'></i></li>" +
			 		"<li><a>仓储</a><i class='fa fa-angle-right'></i></li>" +
			 		"<li><a ui-sref='stock'>库存</a><i class='fa fa-angle-right'></i></li>";
				   
				   if(toParams.stockSerialNum == 'zijian'){
					   html += "<li><a>新增自建库存</a></li>";
				   }else if(toParams.stockSerialNum == 'daiguan'){
					   html += "<li><a>新增代管库存</a></li>";
				   }else if(toParams.stockSerialNum.length > 4){
					   if(toParams.stockSerialNum.indexOf('zijian') >= 0){
						   html += "<li><a>修改自建库存</a></li>";
					   }else html += "<li><a>修改代管库存</a></li>";
				   }				 
			   }else if('priceList' == toState.name){//价格目录
					 html="<li><i class='fa fa-home'></i> <a ui-sref='dashboard'>首页</a> <i class='fa fa-angle-right'></i></li>" +
				 		"<li><a>基础数据</a><i class='fa fa-angle-right'></i></li>" +
				 		"<li><a>价格列表</a></li>";					 
			   }else if('addPriceList' == toState.name){//新增价格
				   html="<li><i class='fa fa-home'></i> <a ui-sref='dashboard'>首页</a> <i class='fa fa-angle-right'></i></li>" +
			 		"<li><a>基础数据</a><i class='fa fa-angle-right'></i></li>" +
			 		"<li><a ui-sref='priceList'>价格列表</a><i class='fa fa-angle-right'></i></li>";
				   
				   if(toParams.buyOrSale == 'buy'){
					   html += "<li><a>新增采购价格</a></li>";
				   }else if(toParams.buyOrSale == 'sale'){
					   html += "<li><a>新增销售价格</a></li>";
				   }else if(toParams.buyOrSale.length > 4){
					   if(toParams.buyOrSale.indexOf('sale') >= 0){
						   html += "<li><a>修改销售价格</a></li>";
					   }else html += "<li><a>修改采购价格</a></li>";
				   }				 
			   }else if('takeDelivery' == toState.name){//收货列表
					 html="<li><i class='fa fa-home'></i> <a ui-sref='dashboard'>首页</a> <i class='fa fa-angle-right'></i></li>" +
				 		"<li><a>仓储</a><i class='fa fa-angle-right'></i></li>" +
				 		"<li><a>收货列表</a></li>";					 
			   }else if('takeDeliveryAdd' == toState.name){//新增代发货
					 html="<li><i class='fa fa-home'></i> <a ui-sref='dashboard'>首页</a> <i class='fa fa-angle-right'></i></li>" +
				 		"<li><a>仓储</a><i class='fa fa-angle-right'></i></li>" +
				 		"<li><a ui-sref='takeDelivery'>收货列表</a><i class='fa fa-angle-right'></i></li>" + 
				 		"<li><a>新建代发货</a></li>";
			   }else if('takeDeliveryView' == toState.name){//查看收货
					 html="<li><i class='fa fa-home'></i> <a ui-sref='dashboard'>首页</a> <i class='fa fa-angle-right'></i></li>" +
				 		"<li><a>仓储</a><i class='fa fa-angle-right'></i></li>" +
				 		"<li><a ui-sref='takeDelivery'>收货列表</a><i class='fa fa-angle-right'></i></li>" + 
				 		"<li><a>查看收货</a></li>";
			   }else if('toTakeDelivery' == toState.name){//收货申请
					 html="<li><i class='fa fa-home'></i> <a ui-sref='dashboard'>首页</a> <i class='fa fa-angle-right'></i></li>" +
				 		"<li><a>仓储</a><i class='fa fa-angle-right'></i></li>" +
				 		"<li><a ui-sref='takeDelivery'>收货列表</a><i class='fa fa-angle-right'></i></li>" + 
				 		"<li><a>收货申请</a></li>";
			   }else if('takeDeliveryAudit' == toState.name){//收货审批
					 html="<li><i class='fa fa-home'></i> <a ui-sref='dashboard'>首页</a> <i class='fa fa-angle-right'></i></li>" +
				 		"<li><a>仓储</a><i class='fa fa-angle-right'></i></li>" +
				 		"<li><a ui-sref='takeDelivery'>收货列表</a><i class='fa fa-angle-right'></i></li>" + 
				 		"<li><a>收货审批</a></li>";
			   }else if('takeDeliveryAdjustment' == toState.name){//收货申请调整
					 html="<li><i class='fa fa-home'></i> <a ui-sref='dashboard'>首页</a> <i class='fa fa-angle-right'></i></li>" +
				 		"<li><a>仓储</a><i class='fa fa-angle-right'></i></li>" +
				 		"<li><a ui-sref='takeDelivery'>收货列表</a><i class='fa fa-angle-right'></i></li>" + 
				 		"<li><a>收货申请调整</a></li>";
			   }else if('stockInAdd' == toState.name){//新增入库
					 html="<li><i class='fa fa-home'></i> <a ui-sref='dashboard'>首页</a> <i class='fa fa-angle-right'></i></li>" +
				 		"<li><a>仓储</a><i class='fa fa-angle-right'></i></li>" +
				 		"<li><a ui-sref='takeDelivery'>收货列表</a><i class='fa fa-angle-right'></i></li>" + 
				 		"<li><a>新建入库</a></li>";
			   }else if('stockIn' == toState.name){//入库
					 html="<li><i class='fa fa-home'></i> <a ui-sref='dashboard'>首页</a> <i class='fa fa-angle-right'></i></li>" +
				 		"<li><a>仓储</a><i class='fa fa-angle-right'></i></li>" +
				 		"<li><a ui-sref='takeDelivery'>收货列表</a><i class='fa fa-angle-right'></i></li>" + 
				 		"<li><a>入库</a></li>";
			   }else if('stockInView' == toState.name){//查看入库单
					 html="<li><i class='fa fa-home'></i> <a ui-sref='dashboard'>首页</a> <i class='fa fa-angle-right'></i></li>" +
				 		"<li><a>仓储</a><i class='fa fa-angle-right'></i></li>" +
				 		"<li><a ui-sref='takeDelivery'>收货列表</a><i class='fa fa-angle-right'></i></li>" + 
				 		"<li><a>查看入库</a></li>";
			   }else if('stockInOutCheck' == toState.name){//出入库检验
					 html="<li><i class='fa fa-home'></i> <a ui-sref='dashboard'>首页</a> <i class='fa fa-angle-right'></i></li>" +
				 		"<li><a>仓储</a><i class='fa fa-angle-right'></i></li>" +
				 		"<li><a>检验列表</a></li>";					 
			   }else if('statement' == toState.name){//对账单
					 html="<li><i class='fa fa-home'></i> <a ui-sref='dashboard'>首页</a> <i class='fa fa-angle-right'></i></li>" +
				 		"<li><a>对账单</a></li>";					 
			   }else if('addBuyStatement' == toState.name){//新增客户对账单
					 html="<li><i class='fa fa-home'></i> <a ui-sref='dashboard'>首页</a> <i class='fa fa-angle-right'></i></li>" +
				 		"<li><a ui-sref='statement'>对账单</a> <i class='fa fa-angle-right'></i></li>" +
				 		"<li><a>新增客户对账单</a></li>";					 
			   }else if('addSupplyStatement' == toState.name){//新增供应商对账单
					 html="<li><i class='fa fa-home'></i> <a ui-sref='dashboard'>首页</a> <i class='fa fa-angle-right'></i></li>" +
				 		"<li><a ui-sref='statement'>对账单</a> <i class='fa fa-angle-right'></i></li>" +
				 		"<li><a>新增供应商对账单</a></li>";					 
			   }else if('viewBuyStatement' == toState.name){//查看客户对账单
					 html="<li><i class='fa fa-home'></i> <a ui-sref='dashboard'>首页</a> <i class='fa fa-angle-right'></i></li>" +
				 		"<li><a ui-sref='statement'>对账单</a> <i class='fa fa-angle-right'></i></li>" +
				 		"<li><a>查看客户对账单</a></li>";					 
			   }else if('viewSupplyStatement' == toState.name){//查看供应商对账单
					 html="<li><i class='fa fa-home'></i> <a ui-sref='dashboard'>首页</a> <i class='fa fa-angle-right'></i></li>" +
				 		"<li><a ui-sref='statement'>对账单</a> <i class='fa fa-angle-right'></i></li>" +
				 		"<li><a>查看供应商对账单</a></li>";					 
			   }else if('gatheringMoneyRecord' == toState.name){//应收款
					 html="<li><i class='fa fa-home'></i> <a ui-sref='dashboard'>首页</a> <i class='fa fa-angle-right'></i></li>" +
				 		"<li><a>收付款</a> <i class='fa fa-angle-right'></i></li>" +
				 		"<li><a>应收款</a></li>";					 
			   }else if('addGatheringMoney' == toState.name){//新增应收款
					 html="<li><i class='fa fa-home'></i> <a ui-sref='dashboard'>首页</a> <i class='fa fa-angle-right'></i></li>" +
				 		"<li><a>收付款</a> <i class='fa fa-angle-right'></i></li>" +
				 		"<li><a ui-sref='gatheringMoneyRecord'>应收款</a><i class='fa fa-angle-right'></i></li>" + 
				 		"<li><a>新增应收款</a></li>";					 
			   }else if('editGatheringMoney' == toState.name){//修改应收款
					 html="<li><i class='fa fa-home'></i> <a ui-sref='dashboard'>首页</a> <i class='fa fa-angle-right'></i></li>" +
				 		"<li><a>收付款</a> <i class='fa fa-angle-right'></i></li>" +
				 		"<li><a ui-sref='gatheringMoneyRecord'>应收款</a><i class='fa fa-angle-right'></i></li>" + 
				 		"<li><a>修改应收款</a></li>";					 
			   }else if('viewGatheringMoney' == toState.name){//查看应收款
					 html="<li><i class='fa fa-home'></i> <a ui-sref='dashboard'>首页</a> <i class='fa fa-angle-right'></i></li>" +
				 		"<li><a>收付款</a> <i class='fa fa-angle-right'></i></li>" +
				 		"<li><a ui-sref='gatheringMoneyRecord'>应收款</a><i class='fa fa-angle-right'></i></li>" + 
				 		"<li><a>查看应收款</a></li>";					 
			   }else if('paymentRecordC' == toState.name){//应付款
				   html="<li><i class='fa fa-home'></i> <a ui-sref='dashboard'>首页</a> <i class='fa fa-angle-right'></i></li>" +
				   "<li><a>收付款</a><i class='fa fa-angle-right'></i></li>" +
				   "<li><a>应付款</a></li>";
			   }else if('addPay' == toState.name){//新增应付款
					 html="<li><i class='fa fa-home'></i> <a ui-sref='dashboard'>首页</a> <i class='fa fa-angle-right'></i></li>" +
					 		"<li><a>收付款</a> <i class='fa fa-angle-right'></i></li>" + 
					 		"<li><a ui-sref='paymentRecordC'>应付款</a> <i class='fa fa-angle-right'></i></li>" + 
					 		"<li><a>新增应付款</a></li>";					 
			   }else if('editPay' == toState.name){//修改应付款
					 html="<li><i class='fa fa-home'></i> <a ui-sref='dashboard'>首页</a> <i class='fa fa-angle-right'></i></li>" +
				 		"<li><a>收付款</a> <i class='fa fa-angle-right'></i></li>" + 
				 		"<li><a ui-sref='paymentRecordC'>应付款</a> <i class='fa fa-angle-right'></i></li>" + 
				 		"<li><a>修改应付款</a></li>";					 
			   }else if('viewPay' == toState.name){//查看应付款
					 html="<li><i class='fa fa-home'></i> <a ui-sref='dashboard'>首页</a> <i class='fa fa-angle-right'></i></li>" +
				 		"<li><a>收付款</a> <i class='fa fa-angle-right'></i></li>" + 
				 		"<li><a ui-sref='paymentRecordC'>应付款</a> <i class='fa fa-angle-right'></i></li>" + 
				 		"<li><a>查看应付款</a></li>";					 
			   }else if('applyPay' == toState.name){//应付款申请
					 html="<li><i class='fa fa-home'></i> <a ui-sref='dashboard'>首页</a> <i class='fa fa-angle-right'></i></li>" +
				 		"<li><a>收付款</a> <i class='fa fa-angle-right'></i></li>" + 
				 		"<li><a ui-sref='paymentRecordC'>应付款</a> <i class='fa fa-angle-right'></i></li>" + 
				 		"<li><a>应付款申请</a></li>";					 
			   }else if('auditPay' == toState.name){//应付款审批
					 html="<li><i class='fa fa-home'></i> <a ui-sref='dashboard'>首页</a> <i class='fa fa-angle-right'></i></li>" +
				 		"<li><a>收付款</a> <i class='fa fa-angle-right'></i></li>" + 
				 		"<li><a ui-sref='paymentRecordC'>应付款</a> <i class='fa fa-angle-right'></i></li>" + 
				 		"<li><a>应付款审批</a></li>";					 
			   }else if('editAuditPay' == toState.name){//调整应付款申请
					 html="<li><i class='fa fa-home'></i> <a ui-sref='dashboard'>首页</a> <i class='fa fa-angle-right'></i></li>" +
				 		"<li><a>收付款</a> <i class='fa fa-angle-right'></i></li>" + 
				 		"<li><a ui-sref='paymentRecordC'>应付款</a> <i class='fa fa-angle-right'></i></li>" + 
				 		"<li><a>调整应付款申请</a></li>";					 
			   }else if('invoice' == toState.name){//发票
				   html="<li><i class='fa fa-home'></i> <a ui-sref='dashboard'>首页</a> <i class='fa fa-angle-right'></i></li>" +
				   "<li><a>发票</a></li>";
			   }else if('addOrEditInvoice' == toState.name){//新增修改发票
				   html="<li><i class='fa fa-home'></i> <a ui-sref='dashboard'>首页</a> <i class='fa fa-angle-right'></i></li>" +
				   "<li><a ui-sref='invoice'>发票</a><i class='fa fa-angle-right'></i></li>";
				   if(toParams.inOrOut == 'in'){
					   html += "<li><a>新增进项票</a></li>";
				   }else if(toParams.inOrOut.length > 4){
					   if(toParams.inOrOut.indexOf('in') >= 0){
						   html += "<li><a>修改进项票</a></li>";
					   }else html += "<li><a>修改销项票</a></li>";
				   } else html += "<li><a>新增销项票</a></li>";
			   }else if('invoiceView' == toState.name){//查看发票
				   html="<li><i class='fa fa-home'></i> <a ui-sref='dashboard'>首页</a> <i class='fa fa-angle-right'></i></li>" +
				   "<li><a ui-sref='invoice'>发票</a><i class='fa fa-angle-right'></i></li>";
				   if(toParams.inOrOut.length > 4){
					   if(toParams.inOrOut.indexOf('in') >= 0){
						   html += "<li><a>查看进项票</a></li>";
					   }else html += "<li><a>查看销项票</a></li>";
				   }
			   }
			   angular.element("#dashboard").empty();
			   var template = angular.element(html);
			   var mobileDialogElement = $compile(template)($rootScope);
			   angular.element("#dashboard").append(mobileDialogElement);
			   
			})

}]); 

