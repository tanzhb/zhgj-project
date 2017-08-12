'use strict';

angular.module('MetronicApp').factory('priceListService', ['$rootScope', '$http', '$q', function($rootScope, $http, $q){
    var factory = {
        savePriceList: savePriceList,
        delPriceLists:delPriceLists,
        /*selectByWarehouseName:selectByWarehouseName,*/
        selectBySerialNum:selectBySerialNum,
        saveLadderPrice:saveLadderPrice,
        deleteLadderPrice:deleteLadderPrice,
        getLadderPriceInfo:getLadderPriceInfo
        
    };

    return factory;
    //保存价格
    function savePriceList(priceList){
        var deferred = $q.defer();  
        $http.post($rootScope.basePath+"/rest/priceList/savePriceListInfo", priceList).success(function (data) {  
            // 如果连接成功，延时返回给调用者  
            deferred.resolve(data);  
        })  
            .error(function () {  
                deferred.reject('连接服务器出错！');  
            })  
        return deferred.promise;  
          
    };
    
    //删除价格
    function delPriceLists(serialNums){
        var deferred = $q.defer();  

        $http.post("rest/priceList/deletePriceList", serialNums).success(function (data) {  
            // 如果连接成功，延时返回给调用者  
            deferred.resolve(data);  
        })  
            .error(function () {  
                deferred.reject('连接服务器出错！');  
            })  
        return deferred.promise;  
          
    };
  //通过价格流水查找阶梯价格
    function getLadderPriceInfo(serialNum){
        var deferred = $q.defer();  
        $http.post($rootScope.basePath + "/rest/priceList/getLadderPriceList", serialNum).success(function (data) { 
            // 如果连接成功，延时返回给调用者 
            deferred.resolve(data);  
        })  
            .error(function () {  
                deferred.reject('连接服务器出错！');  
            })  
        return deferred.promise;  
          
    };
   
    //通过价格serialNum查找价格
    function selectBySerialNum(serialNum){//getLadderPriceInfo
        var deferred = $q.defer();  
        $http.post($rootScope.basePath + "/rest/priceList/viewPriceListDetail", serialNum).success(function (data) { 
            // 如果连接成功，延时返回给调用者 
            deferred.resolve(data);  
        })  
            .error(function () {  
                deferred.reject('连接服务器出错！');  
            })  
        return deferred.promise;  
          
    };
    /**
	 * 保存阶梯价格信息
	 */
    function saveLadderPrice (ladderprices){
		var deferred = $q.defer();
		var params = {};  
	    params = JSON.stringify(ladderprices); 
		$http.post("rest/priceList/saveLadderPrices",  
				params//传整个表单数据  
    	).then(function success(result) {
            deferred.resolve(result);//请求成功
        }, function error(err) {
            deferred.reject(err);//请求失败
        });
        return deferred.promise;//返回承诺
	};
	
	/**
	 * 删除阶梯价格信息
	 */
	function deleteLadderPrice (serialNum){
		var deferred = $q.defer();
		 $http.get("rest/priceList/deleteOneLadderPrice", {  
	        	params:{serialNum:serialNum,cache:false}//传整个表单数据  
	        }).then(function success(result) {
                deferred.resolve(result);//请求成功
            }, function error(err) {
                deferred.reject(err);//请求失败
            });
            return deferred.promise;//返回承诺
	}
    

}]);
