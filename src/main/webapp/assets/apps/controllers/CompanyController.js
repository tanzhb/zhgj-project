/**
 * 
 */

angular.module('MetronicApp').controller('CompanyController',['$rootScope','$scope','$state','$http','companyService','$location','$compile','$stateParams',function($rootScope,$scope,$state,$http,companyService,$location,$compile,$stateParams) {
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
	 			//createTable(15,1,true);
	 		}
	    	// set default layout mode
	    	$rootScope.settings.layout.pageContentWhite = true;
	        $rootScope.settings.layout.pageBodySolid = false;
	        $rootScope.settings.layout.pageSidebarClosed = false;
	        console.log("------------->"+$scope.company);
	       
	    	
	 });
	 
	 
	 
	 var a = 0;
		App.getViewPort().width < App
				.getResponsiveBreakpoint("md") ? $(
				".page-header").hasClass(
				"page-header-fixed-mobile")
				&& (a = $(".page-header").outerHeight(!0))
				: $(".page-header").hasClass(
						"navbar-fixed-top") ? a = $(
						".page-header").outerHeight(!0)
						: $("body").hasClass(
								"page-header-fixed")
								&& (a = 64);
						

		var table = $("#sample_1")
				.DataTable(
						{
							language : {
								aria : {
									sortAscending : ": activate to sort column ascending",
									sortDescending : ": activate to sort column descending"
								},
								emptyTable : "空表",
								info : "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
								infoEmpty : "没有数据",
								infoFiltered : "(从 _MAX_ 条数据中检索)",
								lengthMenu : "每页显示 _MENU_ 条数据",
								search : "查询:",
								zeroRecords : "抱歉， 没有找到！",
								paginate : {
									"sFirst" : "首页",
									"sPrevious" : "前一页",
									"sNext" : "后一页",
									"sLast" : "尾页"
								}
							},
							fixedHeader : {// 固定表头、表底
								header : !0,
								footer : !0,
								headerOffset : a
							},
							// select: true,行多选
							order : [ [ 1, "asc" ] ],// 默认排序列及排序方式
							bRetrieve : true,
							// searching: true,//是否过滤检索
							// ordering: true,//是否排序
							lengthMenu : [
									[ 5, 10, 15, 30, -1 ],
									[ 5, 10, 15, 30, "All" ] ],
							pageLength : 10,// 每页显示数量
							processing : true,// loading等待框
							// serverSide: true,
							ajax : $rootScope.basePath
									+ "/rest/company/companyList",// 加载数据中user表数据

							"aoColumns" : [

							{
								mData : 'comId'
							}, {
								mData : 'comNum'
							}, {
								mData : 'comName'
							}, {
								mData : 'comNature'
							}, {
								mData : 'registeredCapital'
							}, {
								mData : 'businessNature'
							}, {
								mData : 'legalPerson'
							}, {
								mData : 'address'
							} , {
								mData : 'comType'
							}, {
								mData : 'comType'
							}, {
								mData : 'comType'
							}],
							'aoColumnDefs' : [ {
								'targets' : 0,
								'searchable' : false,
								'orderable' : false,
								'className' : 'dt-body-center',
								'render' : function(data,
										type, full, meta) {
									return '<input type="checkbox" name="id[]" value="'
											+ $('<div/>')
													.text(
															data)
													.html()
											+ '">';
								}
							},{
								'targets' : 1,
								'render' : function(data,
										type, row, meta) {
									return '<a   ng-click="showCompanyInfo(\''+row.comId+'\')">'+data+'</a>';
								},"createdCell": function (td, cellData, rowData, row, col) {
									 $compile(td)($scope);
							    }
							} ],

						})
		// 构建datatables结束***************************************

		// 添加checkbox功能***************************************
		// Handle click on "Select all" control
		$('#example-select-all').on(
				'click',
				function() {
					// Check/uncheck all checkboxes in the
					// table
					var rows = table.rows({
						'search' : 'applied'
					}).nodes();
					$('input[type="checkbox"]', rows).prop(
							'checked', this.checked);
				});

		// Handle click on checkbox to set state of "Select
		// all" control
		$('#sample_1 tbody')
				.on(
						'change',
						'input[type="checkbox"]',
						function() {
							// If checkbox is not checked
							if (!this.checked) {
								var el = $(
										'#example-select-all')
										.get(0);
								// If "Select all" control
								// is checked and has
								// 'indeterminate' property
								if (el
										&& el.checked
										&& ('indeterminate' in el)) {
									// Set visual state of
									// "Select all" control
									// as 'indeterminate'
									el.indeterminate = true;
								}
							}
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
	        		//$location.search('comId',company.comId);
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
	         * 编辑
	         */
	        $scope.toEditCompany=function () {
				// Iterate over all checkboxes in the table
				var id_count = table.$('input[type="checkbox"]:checked').length;
				if(id_count==0){
					handle.showMesssage("warning","请选择一条数据进行编辑","提示");
				}else if(id_count>1){
					handle.showMesssage("warning","只能选择一条数据进行编辑","提示");
				}else{
					var comId = table.$('input[type="checkbox"]:checked').val();
					$state.go("companyAdd",{comId:comId});
				}
				
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
	         * 删除
	         */
	        $scope.deleteCompanyBatch=function () {
	        	
	        	handle.confirm("确定删除吗？",function(){
	        		var ids = '';
					// Iterate over all checkboxes in the table
					table.$('input[type="checkbox"]').each(
							function() {
								// If checkbox exist in DOM
								if ($.contains(document, this)) {
									// If checkbox is checked
									if (this.checked) {
										// 将选中数据id放入ids中
										if (ids == '') {
											ids = this.value;
										} else
											ids = ids + ','
													+ this.value;
									}
								}
							});
	        		handle.blockUI();
	        		var promise = companyService.deleteCompanyBatch(ids);
	        		promise.then(function(data){
	        			handle.showMesssage("success","删除成功","提示");
	        			handle.unblockUI();
	        			table.ajax.reload(); // 重新加载datatables数据
	        			/*$state.go('company',{},{reload:true}); */
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
	       
	    /*   $scope.$watch("aaa",function(){  
	    	      console.log($scope.aaa); 
	    	      console.log("-------33------------"); 
	    	    
	    	      console.log($scope.$parent.list);
	       }); */
	  
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
