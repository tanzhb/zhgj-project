/* Setup blank page controller */
angular.module('MetronicApp').controller('UserController', ['$rootScope', '$scope', 'settings', 'UserService', function($rootScope, $scope, settings, UserService) {
    $scope.$on('$viewContentLoaded', function() {   
        // initialize core components
        App.initAjax();

        // set default layout mode
        $rootScope.settings.layout.pageContentWhite = true;
        $rootScope.settings.layout.pageBodySolid = false;
        $rootScope.settings.layout.pageSidebarClosed = false;
    });
    
    var self = this;
    self.user={id:null,username:'',password:'',state:'',createTime:''};
    self.users=[];

    
    fetchAllUsers();

    function fetchAllUsers(){
        UserService.fetchAllUsers()
            .then(
            function(d) {
//            	console.log("************" +  d.length);
//                self.users = d;
                
                
                
                
                
                
                
            },
            function(errResponse){
                console.error('Error while fetching Users');
            }
        );
    }
}]);
