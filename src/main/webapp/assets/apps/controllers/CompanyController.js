/**
 * 
 */

angular.module('MetronicApp').controller('CompanyController',['$rootScope','$scope','$state','$http','$window','settings','companyService','$location',function($rootScope,$scope,$state,$http,$window,settings,companyService,$location) {
	 $scope.$on('$viewContentLoaded', function() {   
	    	// initialize core components
		    handle = new pageHandle();
	    	App.initAjax();
	    	if($location.path()=="/companyAdd"){
	    		handle.pageRepeater();
	    		_index = 0; 
	    		$scope.companyQualifications =[{}];
	    		 handle.datePickersInit();
	 		}else{
	 			createTable(15,1,true);
	 		}
	    	// set default layout mode
	    	$rootScope.settings.layout.pageContentWhite = true;
	        $rootScope.settings.layout.pageBodySolid = false;
	        $rootScope.settings.layout.pageSidebarClosed = false;
	        console.log("------------->"+$scope.company);
	       
	    	
	 });
	 		/**
	 		 * 保存
	 		 */
	        $scope.saveCompany=function () { 
	        	handle.blockUI();
	        	var promise = companyService.saveCompany($scope.company);
	        	promise.then(function(data){
	        		$(".modal-backdrop").remove();
	        		handle.showMesssage("success","保存成功","提示");
	        		handle.unblockUI();
	        		var company = data.data;
		        	//$state.go('companyAdd',company,{reload:true});
	        		$scope.company = company
		        	console.log(data.data);
	        		$scope.companyView = true;
	        		$scope.companyAdd = true;
	            },function(data){
	               //调用承诺接口reject();
	            });
	        	
	        }; 
	        
	        /**
	         * 编辑
	         */
	        $scope.editCompany=function () {
	        	/*	var promise = companyService.editCompany($scope.company.comId);
	        		
	        		promise.then(function(data){
	        			 $("#basic").modal('show');
	 	    			$scope.company = data.data;
	 	            },function(data){
	 	               //调用承诺接口reject();
	 	            });*/
	        	$scope.companyView = false;
        		$scope.companyAdd = false;
	        	
	        };  
	        
	        /**
	         * 删除
	         */
	        $scope.deleteCompany=function (comId) {
	        	
	        	handle.confirm("确定删除吗？",function(){
	        		handle.blockUI();
	        		var promise = companyService.deleteCompany(comId);
	        		promise.then(function(data){
	        			handle.showMesssage("success","删除成功","提示");
	        			handle.unblockUI();
		        		 $state.go('company',{},{reload:true}); 
	 	            },function(data){
	 	               //调用承诺接口reject();
	 	            });
	        		
	        	});
			   
	        };
	        
	        /**
	         * 搜索
	         */
	        $scope.search=function () {
	        	var params ={};
	        	params.searchKey = $scope.searchKey;
	        	createTable(15,1,false,params);
	        	
	        };  
	        
	       function createTable(pageSize,pageIndex,init,params){
	    	 //初始化表格数据
	    	   handle.blockUI();
		    	var promise = companyService.createTable(pageSize,pageIndex,params);
		    	promise.then(function(data){
		    			$scope.records = data.data;
		    			handle.createPage("#sample_1",data.data,"rest/company/companyList",createTable,init);
		            },function(data){
		               //调用承诺接口reject();
		         });
	       }
	       
	       
	       /**
	        * 保存企业资质信息
	        */
	       $scope.saveCompanyQualification = function(){
	    	   handle.blockUI();debugger;
	    	    console.log($scope.companyQualifications);
	        	var promise = companyService.saveCompanyQualification($scope.companyQualifications);
	        	promise.then(function(data){
	        		//$(".modal-backdrop").remove();
	        		handle.showMesssage("success","保存成功","提示");
	        		handle.unblockUI();
	        		$scope.companyQualification = data.data;
	        		$scope.companyQualificationView = true;
	        		$scope.companyQualificationAdd = true;
	            },function(data){
	               //调用承诺接口reject();
	            });
	       }
	       
	       
	       /**
	        * 编辑企业资质信息
	        */
	       $scope.editCompanyQualification = function(){
	    	   	$scope.companyQualificationView = false;
       			$scope.companyQualificationAdd = false;
	       }
	       
	       /**
	        * 删除企业资质信息
	        */
	       $scope.deleteCompanyQualification = function(){
	    		handle.confirm("确定删除吗？",function(){
	        		handle.blockUI();
	        		var promise = companyService.deleteCompanyQualification();
	        		promise.then(function(data){
	        			handle.showMesssage("success","删除成功","提示");
	        			handle.unblockUI();
		        		// $state.go('company',{},{reload:true}); 
	 	            },function(data){
	 	               //调用承诺接口reject();
	 	            });
	        		
	        	});
	       }
	       
	       
	       
	       
	       
	       /**
	        * 保存联系人信息
	        */
	       $scope.saveCompanyContact = function(){
	    	   
	       }
	       
	       
	       /**
	        * 编辑联系人信息
	        */
	       $scope.editCompanyContact = function(){
	    	   
	       }
	       
	       /**
	        * 删除联系人信息
	        */
	       $scope.deleteCompanyContact = function(){
	    	   
	       }
	       
	       /**
	        * 保存财务信息
	        */
	       $scope.saveCompanyFinance = function(){
	    	   
	       }
	       
	       
	       /**
	        * 编辑财务信息
	        */
	       $scope.editCompanyFinance = function(){
	    	   
	       }
	       
	       /**
	        * 删除财务信息
	        */
	       $scope.deleteCompanyFinance = function(){
	    	   
	       }
	       
	       $scope.$watch("list",function(){  
	    	      console.log($scope.list); 
	    	      console.log("-------------------"); 
	    	    
	    	      console.log($scope.$parent.list);
	       }); 
	  
	      /* $scope.$watch("list",function(){  
	    	   console.log($scope.list);  
	    	   console.log($parent.list);  
	       }); 
	       */
	       $scope.addRepeat = function(){
	    	   _index++;
	    	   $scope.companyQualifications[_index] = {}
	       };
	       
	       $scope.repeatDone = function(){
	    	   handle.datePickersInit();
	       };

	       
	       

}]); 
