/**
 * 
 */

angular.module('MetronicApp').controller('CompanyController',['$rootScope','$scope','$state','$http','companyService','$location','$stateParams',function($rootScope,$scope,$state,$http,companyService,$location,$stateParams) {
	 $scope.$on('$viewContentLoaded', function() {   
	    	// initialize core components
		    handle = new pageHandle();
	    	App.initAjax();
	    	if($location.path()=="/companyAdd"){
	    		handle.pageRepeater();
	    		_index = 0; 
	    		$scope.companyQualifications =[{}];
	    		 handle.datePickersInit();
	    		/* $(document).off('show.bs.modal').off('hidden.bs.modal');*/
	    		 
	    		/* $(document).off('click.modal').on('click.modal.data-api', '[data-toggle="modal"]', function ( e ) {
	    				var $this = $(this),
	    					href = $this.attr('href'),
	    					$target = $($this.attr('data-target') || (href && href.replace(/.*(?=#[^\s]+$)/, ''))), //strip for ie7
	    					option = $target.data('modal') ? 'toggle' : $.extend({ remote: !/#/.test(href) && href }, $target.data(), $this.data());

	    				e.preventDefault();
	    				$target
	    					.modal(option)
	    					.one('hide', function () {
	    						$this.focus();
	    					})
	    			});*/
	    		 
	    		getCompanyInfo($stateParams.comId);
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
	        		//$stateParams.comId = company.comId;
	        		$location.search('comId',company.comId);
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
		    	if(handle.isNull($scope.company)||handle.isNull($scope.company.comId)){
		    		 handle.showMesssage("warning","您的企业信息还未保存！","提示");
		    		 return;
		    	}else{
		    		for(var i=0;i<$scope.companyQualifications.length;i++){
		    			$scope.companyQualifications[i].comId = $scope.company.comId;
		    		}
		    	}

	    	   	handle.blockUI();
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
		    	if(handle.isNull($scope.company)||handle.isNull($scope.company.comId)){
		    		 handle.showMesssage("warning","您的企业信息还未保存！","提示");
		    		 return;
		    	}else{
		    		$scope.companyContact.comId = $scope.company.comId;
		    	}
	    	    console.log($scope.companyContact);
	        	var promise = companyService.saveCompanyContact($scope.companyContact);
	        	promise.then(function(data){
	        		//$(".modal-backdrop").remove();
	        		handle.showMesssage("success","保存成功","提示");
	        		$("#contact").modal("hide");
	        		$scope.companyContact = {};
	        		$scope.companyContacts = data.data;
	            },function(data){
	               //调用承诺接口reject();
	            });
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
	    		if(handle.isNull($scope.company)||handle.isNull($scope.company.comId)){
		    		 handle.showMesssage("warning","您的企业信息还未保存！","提示");
		    		 return;
		    	}else{
		    		$scope.companyFinance.comId = $scope.company.comId;
		    	}
	    	   console.log($scope.companyFinance);
	        	var promise = companyService.saveCompanyFinance($scope.companyFinance);
	        	promise.then(function(data){
	        		//$(".modal-backdrop").remove();
	        		handle.showMesssage("success","保存成功","提示");
	        		$("#finance").modal("hide");
	        		$scope.companyFinance = {};
	        		$scope.companyFinances = data.data;
	            },function(data){
	               //调用承诺接口reject();
	            });
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
	       
	       $scope.$watch("aaa",function(){  
	    	      console.log($scope.aaa); 
	    	      console.log("-------33------------"); 
	    	    
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
	       
	       $scope.deleteRepeat = function(){
	    	   _index--;
	    	   $scope.companyQualifications.splice(_index,1);
	       };
	       
	       $scope.repeatDone = function(){
	    	   handle.datePickersInit();
	       };
	       
	       $scope.showCompanyInfo = function(comId){
	    	   getCompanyInfo(comId);
	    	   $('#viewCompany').modal('show'); 
	       };
	       
	       
	       function getCompanyInfo(comId){
	    	   if(!handle.isNull(comId)){
	    			 var promise = companyService.getCompanyInfo(comId);
	 	        	promise.then(function(data){
	 	        		$scope.company = data.data.company;
	 	        		if(!handle.isNull(data.data.companyQualifications)){
	 	        			$scope.companyQualifications = data.data.companyQualifications;
	 	        		}
	 	        		
	 	        		$scope.companyContacts = data.data.companyContacts;
	 	        		$scope.companyFinances = data.data.companyFinances;
	 		        	//$state.go('companyAdd',company,{reload:true});
	 	        		//$scope.company = company
	 		        	//console.log(data.data);
	 	        		//$scope.companyView = true;
	 	        		//$scope.companyAdd = true;
	 	            },function(data){
	 	               //调用承诺接口reject();
	 	            });
	    		 }
	       }
	/*       
	       $('#ajax').on('show.bs.modal', function (e) {  
	    	   // do something...  
	       }) */

	       
	       

}]); 
