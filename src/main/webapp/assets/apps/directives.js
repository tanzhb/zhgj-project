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
        replace: true,
        link:function(scope, iElement, iAttrs, controller){
//        	var deferred = $q.defer();
			$.get(ctx + "/rest/processAction/getTodoTaskSize/" + 'accountPayable').success(function (data) {
		        alert(data);
				// 如果连接成功，延时返回给调用者  
//		        deferred.resolve(data);
		    }).error(function () {  
//		        deferred.reject('连接服务器出错！');  
		    })
//		    return deferred.promise.then(function(data){
//		    	alert(data);
//		    	scope.dbsLength = data;  //调用承诺接口resolove()
//		    });
        }
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