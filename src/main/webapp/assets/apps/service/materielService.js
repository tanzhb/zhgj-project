angular.module('MetronicApp').service('materielService',
		[ '$http', '$q', function($http, $q) {
			return {
				save : function(materiel) {
					var deferred = $q.defer();
					$http.get("rest/materiel/save", {
		            	params:materiel//传整个表单数据
		            }).success(function (data) {
		                // 如果连接成功，延时返回给调用者
		                deferred.resolve("物料数据保存成功！");
		            }).error(function () {
	                    deferred.reject('连接服务器出错！');
	                })
					return deferred.promise;
				},
				findList : function(start,limit) {
					var deferred = $q.defer();
					$http.get("rest/materiel/findMaterielList", {
		            	/*params:materiel//传整个表单数据
*/		            }).success(function (data) {
		                // 如果连接成功，延时返回给调用者
		                deferred.resolve(data);
		            }).error(function () {
	                    deferred.reject('连接服务器出错！');
	                })
					return deferred.promise;
				}
				
			}
		} ]);
