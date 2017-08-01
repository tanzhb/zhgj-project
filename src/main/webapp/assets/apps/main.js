/***
Metronic AngularJS App Main Script
***/

/* Metronic App */
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

    $stateProvider

        // Dashboard
        .state('dashboard', {
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
        .state('jgml', {
            url: "/jgml",
            templateUrl: "rest/page/jgml",
            data: {pageTitle: '价格目录'},
            controller: "GeneralPageController",
            resolve: {
                deps: ['$ocLazyLoad', function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        name: 'MetronicApp',
                        insertBefore: '#ng_load_plugins_before', // load the above css files before '#ng_load_plugins_before'
                        files: [                             
                            'assets/global/plugins/datatables/datatables.min.css', 
                            'assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css',

                            'assets/global/plugins/datatables/datatables.all.min.js',

                            'assets/pages/scripts/table-datatables-managed.min.js',

                            'assets/apps/controllers/GeneralPageController.js'
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
                            'assets/global/plugins/datatables/datatables.min.css', 
//                            /'assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css',
                            
                            
                            //'assets/global/scripts/datatable.js',
                            'assets/global/plugins/datatables/datatables.min.js',
                            //'assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js',
                            //'assets/global/plugins/datatables/datatables.all.min.js',
                            //'assets/pages/scripts/table-datatables-managed.min.js',
                            'assets/pages/scripts/table-datatables-fixedheader.min.js',
                            'assets/apps/service/UserService.js',
                            'assets/apps/controllers/UserController.js'
                        ]
                    });
                }]
            }
        })        .state('company', {
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
        				        'assets/global/plugins/datatables/datatables.min.css', 
        				        'assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css',
        				        'assets/global/plugins/bootstrap-paginator/bootstrap-paginator.js',
        				        'assets/global/plugins/datatables/datatables.all.min.js',
        				        'assets/global/plugins/bootstrap-toastr/toastr.js',
        				        'assets/global/plugins/bootstrap-toastr/toastr.css',
        				        'assets/apps/controllers/CompanyController.js',
        				        'assets/apps/service/CompanyService.js',
        				        'assets/apps/directive/CompanyDirective.js',
        				        'assets/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.min.css',
        				        'assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js',
        				        'assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.css',
        				        'assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.js',
        				        'assets/global/plugins/jquery-repeater/jquery.repeater.js',
        				        'assets/pages/scripts/form-repeater.min.js',
        				        'assets/pages/scripts/form-repeater.js',
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
        .state('companyAdd', {
	        	url: "/companyAdd",
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
	        				        'assets/global/plugins/datatables/datatables.min.css', 
	        				        'assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css',
	        				        'assets/global/plugins/bootstrap-paginator/bootstrap-paginator.js',
	        				        'assets/global/plugins/datatables/datatables.all.min.js',
	        				        'assets/global/plugins/bootstrap-toastr/toastr.js',
	        				        'assets/global/plugins/bootstrap-toastr/toastr.css',
	        				        'assets/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.min.css',
	        				        'assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js',
	        				        'assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.css',
	        				        'assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.js',
	        				        'assets/global/plugins/jquery-repeater/jquery.repeater.js',
	        				        'assets/pages/scripts/form-repeater.min.js',
	        				        'assets/pages/scripts/form-repeater.js',
	        				        'assets/apps/controllers/CompanyController.js',
	        				        'assets/apps/service/CompanyService.js',
	        				        'assets/apps/directive/CompanyDirective.js',
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