angular.module('MetronicApp').service('materielService',
		[ '$http', '$q', function($http, $q) {
			return {
				//保存物料
				save : function(materiel) {
					var deferred = $q.defer();
					$http.post("rest/materiel/save", materiel
					).success(function (data) {
		                // 如果连接成功，延时返回给调用者
		                deferred.resolve(data);
		            }).error(function () {
	                    deferred.reject('连接服务器出错！');
	                })
					return deferred.promise;
				},
				//获取物料列表
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
				},
			    //删除物料
			    delMateriel : function(ids){
			        var deferred = $q.defer();  

			        $http.post("rest/materiel/deleteMateriels", ids)
			        .success(function (data) {  
			            // 如果连接成功，延时返回给调用者  
			            deferred.resolve(data);  
			        })  
			            .error(function () {  
			                deferred.reject('连接服务器出错！');  
			            })  
			        return deferred.promise;  
			          
			    },
			    //获取物料信息
			    getMaterielInfo : function(serialNum){
			        var deferred = $q.defer();  

			        $http.get("rest/materiel/getMaterielInfo", {params:{serialNum:serialNum}})
			        .success(function (data) {  
			            // 如果连接成功，延时返回给调用者  
			            deferred.resolve(data);  
			        })  
			            .error(function () {  
			                deferred.reject('连接服务器出错！');  
			            })  
			        return deferred.promise;  
			          
			    }
				
			}
		} ]);
