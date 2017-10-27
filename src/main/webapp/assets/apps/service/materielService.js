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
			          
			    },//保存BOM
				saveBOM : function(BOM) {
					var deferred = $q.defer();
					var params = {};
					params = JSON.stringify(BOM);
					$http.post("rest/materiel/saveBOM", params
					).success(function (data) {
		                // 如果连接成功，延时返回给调用者
		                deferred.resolve(data);
		            }).error(function () {
	                    deferred.reject('连接服务器出错！');
	                })
					return deferred.promise;
				},//物料导入
				uploadExcel : function(params){
					var deferred = $q.defer();
					var fd = new FormData();
			        var file = document.querySelector('input[type=file]').files[0];
			        fd.append('excelFile', file);
					$http.post("rest/materiel/materielImport",  
							fd,{headers:{'Content-Type': undefined},transformRequest: angular.identity}
					).then(function success(result) {
						deferred.resolve(result);//请求成功
					}, function error(err) {
						deferred.reject(err);//请求失败
					});
					return deferred.promise;//返回承诺
				},//保存附件
				saveFile : function(File) {
					var deferred = $q.defer();
					var params = {};
					params = JSON.stringify(File);
					$http.post("rest/materiel/saveFile", params
					).success(function (data) {
		                // 如果连接成功，延时返回给调用者
		                deferred.resolve(data);
		            }).error(function () {
	                    deferred.reject('连接服务器出错！');
	                })
					return deferred.promise;
				},//保存物料供应商
				saveSupplyMateriel : function(supplyMateriel) {
					var deferred = $q.defer();
					var params = {};
					params = JSON.stringify(supplyMateriel);
					$http.post("rest/materiel/saveSupplyMateriel", params
					).success(function (data) {
		                // 如果连接成功，延时返回给调用者
		                deferred.resolve(data);
		            }).error(function () {
	                    deferred.reject('连接服务器出错！');
	                })
					return deferred.promise;
				},chooseMateriels : function(ids){//选择的供应物料
					var deferred = $q.defer();
					$http.post("rest/materiel/chooseMateriel",
						ids//传整个表单数据  
			    	).then(function success(result) {
			            deferred.resolve(result);//请求成功
			        }, function error(err) {
			            deferred.reject(err);//请求失败
			        });
			        return deferred.promise;//返回承诺
				},chooseBasicMateriels : function(ids){//选择的标准物料
					var deferred = $q.defer();
					$http.post("rest/materiel/chooseBasicMateriels",
						ids//传整个表单数据  
			    	).then(function success(result) {
			            deferred.resolve(result);//请求成功
			        }, function error(err) {
			            deferred.reject(err);//请求失败
			        });
			        return deferred.promise;//返回承诺
				},getSuppliers : function(ids){//获取供应商
					var deferred = $q.defer();
					$http.post("rest/company/getSuppliers"
			    	).then(function success(result) {
			            deferred.resolve(result);//请求成功
			        }, function error(err) {
			            deferred.reject(err);//请求失败
			        });
			        return deferred.promise;//返回承诺
				},chooseDemandPlanMateriels : function(ids){//选择的需求计划物料
					var deferred = $q.defer();
					$http.post("rest/demandPlan/chooseDemandPlanMateriels",
						ids//传整个表单数据  
			    	).then(function success(result) {
			            deferred.resolve(result);//请求成功
			        }, function error(err) {
			            deferred.reject(err);//请求失败
			        });
			        return deferred.promise;//返回承诺
				},
				//保存物料分类
				saveCategory : function(category) {
					var deferred = $q.defer();
					$http.post("rest/materiel/saveCategory", category
					).success(function (data) {
		                // 如果连接成功，延时返回给调用者
		                deferred.resolve(data);
		            }).error(function () {
	                    deferred.reject('连接服务器出错！');
	                })
					return deferred.promise;
				},
				//删除物料分类
				deleteCategory : function(category) {
					var deferred = $q.defer();
					$http.post("rest/materiel/deleteCategory", category
					).success(function (data) {
		                // 如果连接成功，延时返回给调用者
		                deferred.resolve(data);
		            }).error(function () {
	                    deferred.reject('连接服务器出错！');
	                })
					return deferred.promise;
				},
			    //获取子物料分类
				queryCategoryListByParent : function(parentId){
			        var deferred = $q.defer();  

			        $http.get("rest/materiel/queryCategoryListByParent", {params:{parentId:parentId}})
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