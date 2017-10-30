/***
GLobal Directives
***/

// Route State Load Spinner(used on page or content load)
MetronicApp.directive('ngSpinnerBar', ['$rootScope', '$state',
    function($rootScope, $state) {
        return {
            link: function(scope, element, attrs) {
                // by defult hide the spinner bar
                element.addClass('hide'); // hide spinner bar by default

                // display the spinner bar whenever the route changes(the content part started loading)
                $rootScope.$on('$stateChangeStart', function() {
                    element.removeClass('hide'); // show spinner bar
                });

                // hide the spinner bar on rounte change success(after the content loaded)
                $rootScope.$on('$stateChangeSuccess', function(event) {
                    element.addClass('hide'); // hide spinner bar
                    $('body').removeClass('page-on-load'); // remove page loading indicator
                    Layout.setAngularJsSidebarMenuActiveLink('match', null, event.currentScope.$state); // activate selected link in the sidebar menu
                   
                    // auto scorll to page top
                    setTimeout(function () {
                        App.scrollTop(); // scroll to the top on content load
                    }, $rootScope.settings.layout.pageAutoScrollOnLoad);     
                });

                // handle errors
                $rootScope.$on('$stateNotFound', function() {
                    element.addClass('hide'); // hide spinner bar
                });

                // handle errors
                $rootScope.$on('$stateChangeError', function() {
                    element.addClass('hide'); // hide spinner bar
                });
            }
        };
    }
])

// Handle global LINK click
MetronicApp.directive('a', function() {
    return {
        restrict: 'E',
        link: function(scope, elem, attrs) {
            if (attrs.ngClick || attrs.href === '' || attrs.href === '#') {
                elem.on('click', function(e) {
                    e.preventDefault(); // prevent link click for above criteria
                });
            }
        }
    };
});

// Handle Dropdown Hover Plugin Integration
MetronicApp.directive('dropdownMenuHover', function () {
  return {
    link: function (scope, elem) {
      elem.dropdownHover();
    }
  };  
});

//Repeat Done
MetronicApp.directive('repeatDone', function() {
    return {
        link: function(scope, element, attrs) {
                scope.$eval(attrs.repeatDone)    // 执行绑定的表达式
        }
    }
});

MetronicApp.directive('selectpicker', function ($timeout) {
    return {
        restrict: 'A',
        link: function (scope, element, attributes) {
            $timeout(function () {
                scope.$apply(function () {
                    element.selectpicker({
                        showSubtext: true
                    });
                });

              /*  scope.$watch('itemSelected', function (newValue, old) {
                    scope.idx = newValue
                    console.log('selected ', newValue);
                });*/
            }, 1500);
        }
    };
});
/*MetronicApp.directive('setDate', function ($timeout) {
    return {
        restrict: 'A',
        link: function (scope, element, attributes) {debugger;
            $timeout(function () {
                scope.$apply(function () {
                    element.selectpicker({"initialDate":"2016-11-11"});
                });

                scope.$watch('itemSelected', function (newValue, old) {
                    scope.idx = newValue
                    console.log('selected ', newValue);
                });
            }, 500);
        }
    };
});*/


MetronicApp.directive('dbquantity', function() {  
    return {  
        restrict: 'E',  
        template: '<span class="badge badge-danger" style="position: relative;top: -4px;"> {{dbsLength}} </span>',  
        scope:true,
        replace: true
    };  
});
MetronicApp.directive('ybquantity', function() {  
    return {  
        restrict: 'E',  
        template: '<span class="badge badge-danger" style="position: relative;top: -4px;"> {{ybsLength}} </span>',
        scope:true,
        replace: true  
    };  
}); 
MetronicApp.directive('dbquantity1', function() {  
    return {  
        restrict: 'E',  
        template: '<span class="badge badge-danger" style="position: relative;top: -4px;"> {{dbsLength1}} </span>',  
        scope:true,
        replace: true
    };  
});
MetronicApp.directive('ybquantity1', function() {  
    return {  
        restrict: 'E',  
        template: '<span class="badge badge-danger" style="position: relative;top: -4px;"> {{ybsLength1}} </span>',
        scope:true,
        replace: true  
    };  
}); 

MetronicApp.directive('systemmessage', function() {  
    return {  
        restrict: 'E',  
        template: '<span class="badge badge-danger" style="position: relative;top: -4px;"> {{systemMessageSize}} </span>',
        scope:true,
        replace: true  
    };  
}); 

MetronicApp.directive('businessmessage', function() {  
    return {  
        restrict: 'E',  
        template: '<span class="badge badge-danger" style="position: relative;top: -4px;"> {{businessMessageSize}} </span>',
        scope:true,
        replace: true  
    };  
}); 

MetronicApp.directive('noticecount', function() {  debugger;
    return {  
        restrict: 'E',  
        template: '<span class="badge badge-danger" style="position: relative;top: -4px;"> {{noticeCount}} </span>',
        scope:true,
        replace: true  
    };  
}); 