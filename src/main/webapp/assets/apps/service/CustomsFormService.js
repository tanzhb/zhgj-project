angular.module('MetronicApp').service('customsFormService',
		[ '$http', '$q', function($http, $q) {
			return {
				//保存报关单/清关单
				saveCustomsForm : function(customsForm) {
					var deferred = $q.defer();
					$http.post("rest/customsForm/saveCustomsForm", JSON.stringify(customsForm)
					).success(function (data) {
		                // 如果连接成功，延时返回给调用者
		                deferred.resolve(data);
		            }).error(function () {
	                    deferred.reject('连接服务器出错！');
	                })
					return deferred.promise;
				},
			
				//删除报关单/清关单
			    delCustomsForm : function(ids){
			        var deferred = $q.defer();  
	
			        $http.post("rest/customsForm/deleteCustomsForm", ids)
			        .success(function (data) {  
			            // 如果连接成功，延时返回给调用者  
			            deferred.resolve(data);  
			        })  
			            .error(function () {  
			                deferred.reject('连接服务器出错！');  
			            })  
			        return deferred.promise;  
			    },
			    //获取报关单/清关单信息
			    getCustomsFormInfo : function(serialNum){
			        var deferred = $q.defer();  

			        $http.post("rest/customsForm/viewCustomsForm", serialNum)
			        .success(function (data) {  
			            // 如果连接成功，延时返回给调用者  
			            deferred.resolve(data);  
			        })  
			            .error(function () {  
			                deferred.reject('连接服务器出错！');  
			            })  
			        return deferred.promise;  
			          
			    },
			 //保存附件
				saveFile : function(File) {
					var deferred = $q.defer();
					var params = {};
					params = JSON.stringify(File);
					$http.post("rest/customsForm/saveFile", params
					).success(function (data) {
		                // 如果连接成功，延时返回给调用者
		                deferred.resolve(data);
		            }).error(function () {
	                    deferred.reject('连接服务器出错！');
	                })
					return deferred.promise;
				}
			}
		} ]);
