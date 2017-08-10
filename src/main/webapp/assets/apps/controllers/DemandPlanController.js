/**
 * 
 */

angular.module('MetronicApp').controller('DemandPlanController',['$rootScope','$scope','$state','$http','demandPlanService','$location','$compile','$stateParams',function($rootScope,$scope,$state,$http,demandPlanService,$location,$compile,$stateParams) {
	 $scope.$on('$viewContentLoaded', function() {   
	    	// initialize core components
		    handle = new pageHandle();
	    	App.initAjax();
	    	if($location.path()=="/demandPlanAdd"){
	    		//handle.pageRepeater();
	    		//_index = 0; 
	    		//$scope.companyQualifications =[{}];
	    		// handle.datePickersInit();
	    		 
	    		//getCompanyInfo($stateParams.comId);
	    		selectParentMateriel();
	 		}else{
	 			createTable(15,1,true);
	 			//loadTable();
	 		//	 $("#comViewPage").html($compile($("#comViewContent").html())($scope));
	 			/*$("#sample_3_tools > li > a.tool-action").on("click",
	 			        function(){
	 			            var e=$(this).attr("data-action");
	 			           $("#companyTable").DataTable().button(e).trigger();
	 			 })*/
	 		}
	    	// set default layout mode
	    	$rootScope.settings.layout.pageContentWhite = true;
	        $rootScope.settings.layout.pageBodySolid = false;
	        $rootScope.settings.layout.pageSidebarClosed = false;
	        console.log("------------->"+$scope.company);
	       
	    	
	 });
	 
	 
     var table;
     var table = function() {
              a = 0;
              App.getViewPort().width < App.getResponsiveBreakpoint("md") ? $(".page-header").hasClass("page-header-fixed-mobile") && (a = $(".page-header").outerHeight(!0)) : $(".page-header").hasClass("navbar-fixed-top") ? a = $(".page-header").outerHeight(!0) : $("body").hasClass("page-header-fixed") && (a = 64);
              selectParentTable = $("#select_sample_2").DataTable({
                  language: {
                      aria: {
                          sortAscending: ": activate to sort column ascending",
                          sortDescending: ": activate to sort column descending"
                      },
                      emptyTable: "空表",
                      info: "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
                      infoEmpty: "没有数据",
                      //infoFiltered: "(filtered1 from _MAX_ total entries)",
                      lengthMenu: "每页显示 _MENU_ 条数据",
                      search: "查询:",
                      zeroRecords: "抱歉， 没有找到！",
                      paginate: {
                          "sFirst": "首页",
                          "sPrevious": "前一页",
                          "sNext": "后一页",
                          "sLast": "尾页"
                       }
                  },
  /*                fixedHeader: {//固定表头、表底
                      header: !0,
                      footer: !0,
                      headerOffset: a
                  },*/
                  order: [[1, "asc"]],//默认排序列及排序方式
                  searching: true,//是否过滤检索
                  ordering:  true,//是否排序
                  lengthMenu: [[5, 10, 15, 30, -1], [5, 10, 15, 30, "All"]],
                  pageLength: 5,//每页显示数量
                  processing: true,//loading等待框
//                  serverSide: true,
                  ajax: "rest/materiel/findMaterielList?isLatestVersion=1",//加载数据中
                  "aoColumns": [
                                { mData: 'serialNum' },
                                { mData: 'materielNum' },
                                { mData: 'materielName' },
                                { mData: 'specifications' },
                                { mData: 'unit' },
                                { mData: 'unit' }
                          ],
                 'aoColumnDefs' : [ {
  							'targets' : 0,
  							'searchable' : false,
  							'orderable' : false,
  							
  							'render' : function(data,
  									type, row, meta) {
  								return '<input type="checkbox"  name="serialNum[]" value="'
  										+ $('<div/>')
  												.text(
  														data)
  												.html()
  										+ '">';
  							},
  							"createdCell": function (td, cellData, rowData, row, col) {
  								 $compile(td)($scope);
  						       }
  						},{
  							'targets' : 1,
  							'render' : function(data,
  									type, row, meta) {
  								var bomIcon='';//bom图标
  								if(row.isBOM==1){
  									bomIcon = '<span class="label label-sm label-success">B</span> '
  								}
  								return bomIcon + data;
  							}

  						}]

              }).on('order.dt',
              function() {
                  console.log('排序');
              })
          };
          
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
			$('#select_sample_2 tbody')
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
			// 添加checkbox功能
			// ***************************************
	 

		
						
			// 页面加载完成后调用，验证输入框
				$scope.$watch('$viewContentLoaded', function() {  
								var e = $("#companyForm"),
						        r = $(".alert-danger", e),
						        i = $(".alert-success", e);
						        e.validate({
						            errorElement: "span",
						            errorClass: "help-block help-block-error",
						            focusInvalid: !1,
						            ignore: "",
						            messages: {
						            	comNum:{required:"企业编号不能为空！"},
					            		comName:{required:"企业名称不能为空！"},
					            		comType:{required:"合作类型不能为空！"},
					            		comNature:{required:"企业性质不能为空！"},
					            		legalPerson:{required:"企业法人姓名不能为空！"},
					            		address:{required:"注册地址不能为空！"},
					            		taxpayeNumber:{required:"纳税人识别号不能为空！"},
					            		contact:{required:"维护人员不能为空！"},
					            		tel:{digits:"请输入正确的联系, 必须为数字！",rangelength:jQuery.validator.format("电话必须在{0}到{1}位数字之间！")}
						            },
						            rules: {
						            	comNum: {
						                    required: !0
						                },
						                comName: {
						                    required: !0
						                },
						                comType: {
						                	required: !0
						                },
						                legalPerson: {
						                	required: !0
						                },
						                taxpayeNumber: {
						                	required: !0
						                },
						                tel: {
						                	required: !0
						                },
						                address: {
						                	required: !0
						                },
						                comNature: {
						                	required: !0
						                },
						                contact:{
						                	required:true,
						                },
						                tel:{
						                	digits:true,
						                	rangelength:[7,20]
						                }
						            },
						            invalidHandler: function(e, t) {
						                i.hide(),
						                r.show(),
						                App.scrollTo(r, -200)
						            },
						            errorPlacement: function(e, r) {
						                r.is(":checkbox") ? e.insertAfter(r.closest(".md-checkbox-list, .md-checkbox-inline, .checkbox-list, .checkbox-inline")) : r.is(":radio") ? e.insertAfter(r.closest(".md-radio-list, .md-radio-inline, .radio-list,.radio-inline")) : e.insertAfter(r)
						            },
						            highlight: function(e) {
						                $(e).closest(".form-group").addClass("has-error")
						            },
						            unhighlight: function(e) {
						                $(e).closest(".form-group").removeClass("has-error")
						            },
						            success: function(e) {
						                e.closest(".form-group").removeClass("has-error")
						            },
						            submitHandler: function(e) {
						                i.show(),
						                r.hide()
						            }
						        })   
			}); 					
		
			
		
		
				

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
	 		 *去新增页面
	 		 */
	        $scope.addDemandPlan=function () { 
	        		$scope.demandPlan = {};
	        		$state.go("demandPlanAdd");
	        }
	        
	    	$scope.addMateriel = function (){
				$("#basicMaterielInfo").modal("show");
			}
	    	
	    	
	    	$scope.confirmSelect = function(){
	    		var id_count = table.$('input[type="checkbox"]:checked').length;
				if(id_count==0){
					toastr.warning("请选择物料");
					return;
				}
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
	        		var promise = demandPlanService.chooseMateriels(ids);
	        		promise.then(function(data){
	        			toastr.success("添加成功！");
	        			handle.unblockUI();
	        			$scope.materiels = data.data;
	        			$("#basicMaterielInfo").modal("hide");
	        		},function(data){
	        			//调用承诺接口reject();
	        		});
	    	}
	        /**
	         * 保存
	         */
	        $scope.saveCompany=function () { 
	        	if($('#companyForm').valid()){
	        		handle.blockUI();
	        		$scope.company.createTime=null;
	        		$scope.company.updateTime=null;
	        		var promise = companyService.saveCompany($scope.company);
	        		promise.then(function(data){
	        			if(!handle.isNull(data.data)){
	        				$(".modal-backdrop").remove();
		        			handle.toastr.success("保存成功");
		        			handle.unblockUI();
		        			var company = data.data;
		        			//$state.go('companyAdd',company,{reload:true});
		        			$scope.company = company
		        			console.log(data.data);
		        			$scope.companyView = true;
		        			$scope.companyAdd = true;
		        			$scope.companyEdit = false;
		        			$(".alert-danger").hide();
		        			//$stateParams.comId = company.comId;
		        			//$location.search('comId',company.comId);
	        			}else{
	        				$(".modal-backdrop").remove();
		        			handle.unblockUI();
	        				handle.toastr.error("保存失败！请联系管理员");
			            	console.log(data);
	        			}
	        			
	        		},function(data){
	        			//调用承诺接口reject();
	        			$(".modal-backdrop").remove();
	        			handle.unblockUI();
	        			handle.toastr.error("保存失败！请联系管理员");
		            	console.log(data);
	        		});
	        	}
	        	
	        }; 

	        /**
	         * 编辑（列表）
	         */
	        $scope.editCompany=function (comId) {
	        	/*	var promise = companyService.editCompany($scope.company.comId);
	        		
	        		promise.then(function(data){
	        			 $("#basic").modal('show');
	 	    			$scope.company = data.data;
	 	            },function(data){
	 	               //调用承诺接口reject();
	 	            });*/
	        	$scope.companyView = false;
	        	$scope.companyAdd = false;
	        	$scope.companyEdit = true;
	        	//$state.go("companyAdd",{comId:comId});
	        	
	        };  
	        
	        /**
	         * 编辑（行内）
	         */
	        $scope.toEditCompany=function () {
				// Iterate over all checkboxes in the table
				var id_count = table.$('input[type="checkbox"]:checked').length;
				if(id_count==0){
					handle.toastr.warning("请选择您要编辑的记录");
				}else if(id_count>1){
					handle.toastr.warning("只能选择一条数据进行编辑");
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
	        			if(data.data == "1"){
	        				handle.toastr.success("删除成功");
		        			handle.unblockUI();
			        		 $state.go('company',{},{reload:true}); 
	        			}else{
	        				handle.toastr.error("保存失败！请联系管理员");
			            	console.log(data);
	        			}
	        			
	 	            },function(data){
	 	               //调用承诺接口reject();
	 	            	handle.toastr.error("保存失败！请联系管理员");
		            	console.log(data);
	 	            });
	        		
	        	});
			   
	        };
	        
	        
	        /**
	         * 批量删除
	         */
	        $scope.deleteCompanyBatch=function () {
	        	var id_count = table.$('input[type="checkbox"]:checked').length;
				if(id_count==0){
					handle.toastr.warning("请选择您要删除的记录");
					return;
				}
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
	        			handle.toastr.success("删除成功");
	        			handle.unblockUI();
	        			table.ajax.reload(); // 重新加载datatables数据
	        			/*$state.go('company',{},{reload:true}); */
	        		},function(data){
	        			//调用承诺接口reject();
	        		});
	        		
	        	});
	        	
	        };
	        
	        /**
	         * 取消企业信息保存
	         */
	        $scope.cancelCompany = function (type) {
	        	getCompanyInfo($scope.company.comId)
	        	if(type=="company"){
	        		$scope.companyView = true;
	       			$scope.companyAdd = true;
	       			$scope.companyEdit = false;
	        	}else{
	       			$scope.companyQualificationView = true;
	       			$scope.companyQualificationAdd = true;
	       			$scope.companyQualificationEdit = false;
	        	}
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
		    	var promise = demandPlanService.createTable(pageSize,pageIndex,params);
		    	promise.then(function(data){
		    			$scope.demandPlans = data.data.result;
		    			handle.createPage("#demandPlanTab",data.data,"rest/demandPlan/demandPlanList",createTable,init);
		            },function(data){
		               //调用承诺接口reject();
		         });
	       }
	       
	       
	       /**
	        * 保存企业资质信息
	        */
	       $scope.saveCompanyQualification = function(){
		    	if(handle.isNull($scope.company)||handle.isNull($scope.company.comId)){
		    		 handle.toastr.warning("您的企业信息还未保存！");
		    		 return;
		    	}else{
		    		for(var i=0;i<$scope.companyQualifications.length;i++){
		    			$scope.companyQualifications[i].comId = $scope.company.comId;
		    			$scope.companyQualifications[i].createTime = null;
		    			$scope.companyQualifications[i].updateTime = null;
		    		}
		    	}
		    	if($('#qualificationForm').valid()){
		    		handle.blockUI();
		    	    console.log($scope.companyQualifications);
		        	var promise = companyService.saveCompanyQualification($scope.companyQualifications);
		        	promise.then(function(data){
		        		//$(".modal-backdrop").remove();
		        		if(!handle.isNull(data.data)){
			        		handle.toastr.success("保存成功");
			        		handle.unblockUI();
			        		//$scope.companyQualifications = data.data;
			        		getCompanyInfo($scope.company.comId,'companyQualification');
			        		$scope.companyQualificationView = true;
			        		$scope.companyQualificationAdd = true;
			        		$scope.companyQualificationEdit = false;
		        		}else{
		        			handle.toastr.error("保存失败！请联系管理员");
			        		handle.unblockUI();
		        		}
		            },function(data){
		            	handle.unblockUI();
		               //调用承诺接口reject();
		            	handle.toastr.error("保存失败！请联系管理员");
		            	console.log(data);
		            });
		    	}
	    	   
	       }
	       
	       
	       /**
	        * 编辑企业资质信息
	        */
	       $scope.editCompanyQualification = function(){
	    	   	$scope.companyQualificationView = false;
       			$scope.companyQualificationAdd = false;
       			$scope.companyQualificationEdit = true;
	       }
	       
	       /**
	        * 删除企业资质信息
	        */
	       $scope.deleteCompanyQualification = function(serialNum){
	    		handle.confirm("确定删除吗？",function(){
	        		handle.blockUI();
	        		var promise = companyService.deleteCompanyQualification(serialNum);
	        		promise.then(function(data){
	        			//handle.showMesssage("success","删除成功","提示");
	        			handle.toastr.success("删除成功");
	        			handle.unblockUI();
		        		// $state.go('company',{},{reload:true}); 
	 	            },function(data){
	 	               //调用承诺接口reject();
	 	            });
	        		
	        	});
	       }
	       
	       $scope.exportCompany = function(){
	    	 handle.blockUI("正在导出数据，请稍后"); 
	    	 window.location.href=$rootScope.basePath+"/rest/company/exportCompany";
	    	 handle.unblockUI(); 
	       }
	       
	       
	       
	       
	       
	       /**
	        * 保存联系人信息
	        */
	       $scope.saveCompanyContact = function(){
		    	if(handle.isNull($scope.company)||handle.isNull($scope.company.comId)){
		    		 handle.toastr.warning("您的企业信息还未保存");
		    		 return;
		    	}else{
		    		if(handle.isNull($scope.companyContact)){
		    			$scope.companyContact = {}
		    		}
		    		$scope.companyContact.comId = $scope.company.comId;
		    		$scope.companyContact.createTime = null;
		    		$scope.companyContact.updateTime = null;
		    	}
		    	if($('#contactForm').valid()){
		    		console.log($scope.companyContact);
		        	var promise = companyService.saveCompanyContact($scope.companyContact);
		        	promise.then(function(data){
		        		//$(".modal-backdrop").remove();
		        		if(!handle.isNull(data.data)){
			        		handle.toastr.success("保存成功");
			        		$("#contactor").modal("hide");
			        		$scope.companyContact = {};
			        		$scope.companyContacts = data.data;
		        		}else{
		        			$("#contactor").modal("hide");
		        			handle.toastr.error("保存失败！请联系管理员");
		        		}
		            },function(data){
		               //调用承诺接口reject();
		            	handle.toastr.error("保存失败！请联系管理员");
		            	console.log(data);
		            });
		    	}
	    	    
	       }
	       
	       /**
	        * 新增联系人信息
	        */
	       $scope.addCompanyContact = function(){
	    	   if(handle.isNull($scope.company)||handle.isNull($scope.company.comId)){
		    		 handle.toastr.warning("您的企业信息还未保存");
		    		 return;
		       }else{
		    	   $scope.companyContact = {};
		    	   $("#contactor").modal("show");
		       }
	    	  
	       }
	       
	       
	       /**
	        * 编辑联系人信息
	        */
	       $scope.editCompanyContact = function(serialNum){
	    	   $("#contactor").modal("show");
	    	   for(var i=0;i<$scope.companyContacts.length;i++){
	    		   if($scope.companyContacts[i].serialNum==serialNum){
	    			   $scope.companyContact = $scope.companyContacts[i];
	    			   break;
	    		   }
	    	   }
	    	 
	       }
	       
	       /**
	        * 删除联系人信息
	        */
	       $scope.deleteCompanyContact = function(serialNum){
	    	   handle.confirm("确定删除吗？",function(){
	        		handle.blockUI();
	        		var promise = companyService.deleteCompanyContact(serialNum);
	        		promise.then(function(data){
	        			//handle.showMesssage("success","删除成功","提示");
	        			handle.toastr.success("删除成功");
	        			handle.unblockUI();
	        			 for(var i=0;i<$scope.companyContacts.length;i++){
		       	    		   if($scope.companyContacts[i].serialNum==serialNum){
		       	    			   $scope.companyContacts.splice(i,1);
		       	    			   break;
		       	    		   }
	       	    	   	  }
		        		// $state.go('company',{},{reload:true}); 
	 	            },function(data){
	 	               //调用承诺接口reject();
	 	            });
	        		
	        	});
	       }
	       
	       /**
	        * 保存财务信息
	        */
	       $scope.saveCompanyFinance = function(){
	    		if(handle.isNull($scope.company)||handle.isNull($scope.company.comId)){
		    		 handle.toastr.warning("您的企业信息还未保存");
		    		 return;
		    	}else{
		    		if(handle.isNull($scope.companyFinance)){
		    			$scope.companyFinance = {}
		    		}
		    		$scope.companyFinance.comId = $scope.company.comId;
		    		$scope.companyFinance.createTime = null;
		    		$scope.companyFinance.updateTime = null;
		    	}
	    		if($('#companyFinanceForm').valid()){
		    		console.log($scope.companyFinance);
		        	var promise = companyService.saveCompanyFinance($scope.companyFinance);
		        	promise.then(function(data){
		        		//$(".modal-backdrop").remove();
		        		if(!handle.isNull(data.data)){
		        			handle.toastr.success("保存成功");
			        		$("#finance").modal("hide");
			        		$scope.companyFinance = {};
			        		$scope.companyFinances = data.data;
		        		}else{
		        			$("#finance").modal("hide");
		        			handle.toastr.error("保存失败！请联系管理员");
		        			console.log(data);
		        		}
		        		
		            },function(data){
		               //调用承诺接口reject();
		            	handle.toastr.error("保存失败！请联系管理员");
		            	console.log(data);
		            });
	    		}
	       }
	       
	       
	       /**
	        * 新增财务信息
	        */
	       $scope.addCompanyFinance = function(){
	    	   if(handle.isNull($scope.company)||handle.isNull($scope.company.comId)){
		    		 handle.toastr.warning("您的企业信息还未保存");
		    		 return;
		       }else{
		    	   $scope.companyFinance = {};
		    	   $("#finance").modal("show");
		       }
	    	  
	       }
	       
	       /**
	        * 编辑财务信息
	        */
	       $scope.editCompanyFinance = function(serialNum){
	    	   $("#finance").modal("show");
	    	   for(var i=0;i<$scope.companyFinances.length;i++){
	    		   if($scope.companyFinances[i].serialNum==serialNum){
	    			   $scope.companyFinance = $scope.companyFinances[i];
	    			   break;
	    		   }
	    	   }
	    	   
	       }
	       
	       /**
	        * 删除财务信息
	        */
	       $scope.deleteCompanyFinance = function(serialNum){
	    	   handle.confirm("确定删除吗？",function(){
	        		handle.blockUI();
	        		var promise = companyService.deleteCompanyFinance(serialNum);
	        		promise.then(function(data){
	        			//handle.showMesssage("success","删除成功","提示");
	        			handle.toastr.success("删除成功");
	        			handle.unblockUI();
	        			  for(var i=0;i<$scope.companyFinances.length;i++){
		       	    		   if($scope.companyFinances[i].serialNum==serialNum){
		       	    			   $scope.companyFinances.splice(i,1);
		       	    			   break;
		       	    		   }
	       	    	   	  }
		        		// $state.go('company',{},{reload:true}); 
	 	            },function(data){
	 	               //调用承诺接口reject();
	 	            });
	        		
	        	});
	       }
	       
	    /*   $scope.$watch("aaa",function(){  
	    	      console.log($scope.aaa); 
	    	      console.log("-------33------------"); 
	    	    
	    	      console.log($scope.$parent.list);
	       }); */
	  
	      /* $scope.$watch("company",function(){  
	    	   console.log($scope.company);  
	    	   console.log($scope.company1);  
	    	   console.log($scope.$parent.company);  
	       }); */
	       
	       /**
	        * 企业资质加一行
	        */
	       $scope.addRepeat = function(){
	    	   if(handle.isNull($scope.company)||handle.isNull($scope.company.comId)){
		    		 handle.toastr.warning("您的企业信息还未保存");
		    		 return;
		       }else{
		    	   _index++;
		    	   $scope.companyQualifications[_index] = {}
		       }
	       };
	       
	       /**
	        * 企业资质删除一行
	        */
	       $scope.deleteRepeat = function(){
	    	   $scope.companyQualifications.splice(_index,1);
	    	   _index--;
	       };
	       
	       /**
	        * 企业资质初始化日期控件
	        */
	       $scope.repeatDone = function(){
	    	   handle.datePickersInit();
	       };
	       
	       /**
	        * 显示企业信息
	        */
	       $scope.showCompanyInfo = function(comId){
	    	   getCompanyInfo(comId);
	    	  // $('#viewCompany').modal('show'); 
	       };
	       
	       /**
	        * 显示企业信息
	        */
	       $scope.showCompanyInfoModal = function(comId){
	    	   getCompanyInfo(comId);
	    	   $('#viewCompany').modal('show'); 
	       };
	       
	       /**
	        * 显示编辑、删除操作
	        */
	       $scope.showOperation = function(type,index){
	    	   var call = "operation_c"+index;
	    	   if(type=='finance'){
	    		   call =  "operation_f"+index;
	    	   }
	    	   $scope[call] = true;
	       };
	       
	       /**
	        * 隐藏编辑、删除操作
	        */
	       $scope.hideOperation = function(type,index){
	    	   var call = "operation_c"+index;
	    	   if(type=='finance'){
	    		   call =  "operation_f"+index;
	    	   }
	    	   $scope[call]= false;
	       };
	       
	       
	       function getCompanyInfo(comId,type){
	    	   if(!handle.isNull(comId)){
	    			 var promise = companyService.getCompanyInfo(comId);
	 	        	promise.then(function(data){
	 	        		if(type=="companyQualification"){
	 	        			if(!handle.isNull(data.data.companyQualifications)){
		 	        			$scope.companyQualifications = data.data.companyQualifications;
		 	        			_index = data.data.companyQualifications.length-1;
		 	        		}
	 	        		}else{
	 	        			$scope.company = data.data.company;
		 	        		if(!handle.isNull(data.data.companyQualifications)){
		 	        			$scope.companyQualifications = data.data.companyQualifications;
		 	        			_index = data.data.companyQualifications.length-1;
		 	        		}
		 	        		
		 	        		$scope.companyContacts = data.data.companyContacts;
		 	        		$scope.companyFinances = data.data.companyFinances;
	 	        		}
	 	        		
	 	        		
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
	       
	       
	      /* $('#ajax').on('show.bs.modal', function (e) {  
	    	    
	       })*/
	       $('#contactor').on('hide.bs.modal', function (e) {  
	    	   if(handle.isNull($scope.company)&&handle.isNull($scope.company.comId)){
	    		   getCompanyInfo($scope.company.comId);
	    	   }
	       })
	       $('#finance').on('hide.bs.modal', function (e) {  
	    	   if(handle.isNull($scope.company)&&handle.isNull($scope.company.comId)){
	    		   getCompanyInfo($scope.company.comId);
	    	   }
	       })
	       
	     /*  $scope.contactorB = function(){
	    	   $scope.companyContact = {};
	    	   $("#contactor").modal("show");
	       };
	       $scope.financeB = function(){
	    	   $scope.companyFinance ={};
	    	   $("#finance").modal("show");
	       };*/
	       
	       /**
	        * 下载EXCEL模板
	        */
	       $scope.downloadImportTemp = function(){
	    	   window.location.href=$rootScope.basePath+"/rest/company/downloadImportTemp";
	       }
	       
	       /**
	        * 上传EXCEL
	        */
	       $scope.uploadExcel = function(){
	    	    var file = document.querySelector('input[type=file]').files[0];
	    	    if(handle.isNull(file)){
	    	    	handle.toastr.warning("请选择Excel文件！");
	    	    }
	    	    console.log(file.name);
	    	    var type = file.name.substring(file.name.lastIndexOf("."));
	    	   if(type != ".xls"){
	    		   handle.toastr.warning("文件格式不正确，需要xls类型的Excel文档");
	    		   return;
	    	   }
	    	   	handle.blockUI("正在导入中，请不要进行其他操作"); 
	    	   	var promise = companyService.uploadExcel();
       			promise.then(function(data){
       				handle.unblockUI(); 
       				if(data.data.data=="success"){
       					handle.toastr.success("导入成功");
       					table.ajax.reload();
       				}else{
       					handle.toastr.error(data.data.data);
       				}
       				$('#import').modal('hide'); 
	            },function(data){
	               //调用承诺接口reject();
	            	handle.toastr.error("操作失败");
	            	$('#import').modal('hide'); 
	            });
	    	   
	       }
	       $('#import').on('hide.bs.modal', function (e) { 
	    	   $("#resetFile").trigger("click");
	    	  //$("#file_span input[type='file']").remove();
	    	  //$(".fileinput-filename").val("");
	    	  //$("#file_span").appendTo('<input type="file" file-model="excelFile" accept=".xls" name="...">');
	       })

	         
	       

	       
	       

}]); 
