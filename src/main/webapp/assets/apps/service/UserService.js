'use strict';

angular.module('MetronicApp').factory('UserService', ['$rootScope', '$http', '$q', function($rootScope, $http, $q){
    var REST_SERVICE_URI = $rootScope.basePath + '/rest/user/findAllUsers';

    var factory = {
        fetchAllUsers: fetchAllUsers
    };

    return factory;

    function fetchAllUsers() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching Users');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

    

}]);
