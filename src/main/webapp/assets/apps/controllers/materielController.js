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
        	TableDatatablesManaged.init();
        	UITree.init();
//        	initList(1,10);
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

}]);
