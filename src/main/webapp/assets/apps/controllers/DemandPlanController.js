/**
 * 
 */

angular.module('MetronicApp').controller('DemandPlanController',['$rootScope','$scope','$state','$http','demandPlanService','$location','$compile','$stateParams',function($rootScope,$scope,$state,$http,demandPlanService,$location,$compile,$stateParams) {
	 $scope.$on('$viewContentLoaded', function() {   
	    	// initialize core components
		    handle = new pageHandle();
	    	App.initAjax();
	    	handle.datePickersInit();
	    	if($location.path()=="/demandPlanAdd"){
	    		//handle.pageRepeater();
	    		//_index = 0; 
	    		//$scope.companyQualifications =[{}];
	    		
	    		$scope.rootMateriels = [];
	    		getDemandPlanInfo($stateParams.serialNum);
	    		
	    		selectParentMateriel();
	    		//$('.customer').selectpicker();
	    		initCustomers();
	 		}else{
	 			createTable(10,1,true);
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
     var selectParentMateriel = function() {
              a = 0;
              App.getViewPort().width < App.getResponsiveBreakpoint("md") ? $(".page-header").hasClass("page-header-fixed-mobile") && (a = $(".page-header").outerHeight(!0)) : $(".page-header").hasClass("navbar-fixed-top") ? a = $(".page-header").outerHeight(!0) : $("body").hasClass("page-header-fixed") && (a = 64);
              table = $("#select_sample_2").DataTable({
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
								var e = $("#demandPlanForm"),
						        r = $(".alert-danger", e),
						        i = $(".alert-success", e);
						        e.validate({
						            errorElement: "span",
						            errorClass: "help-block help-block-error",
						            focusInvalid: !1,
						            ignore: "",
						            messages: {
						            	demandPlanNum:{required:"需求计划编号不能为空！"},
						            	buyComId:{required:"客户不能为空！"},
						            	releaseDate:{required:"发布日期不能为空！"}
						            },
						            rules: {
						            	demandPlanNum: {
						                    required: !0
						                },
						                buyComId: {
						                    required: !0
						                },
						                releaseDate: {
						                	required: !0
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
		
		
			var initCustomers = function(){
				var promise = demandPlanService.initCustomers();
        		promise.then(function(data){
        			$scope.customers = data.data;
        		},function(data){
        			//调用承诺接口reject();
        		});
			}
		
		
		
	 		/**
	 		 *去新增页面
	 		 */
	        $scope.addDemandPlan=function () { 
	        		$scope.demandPlan = {};
	        		$state.go("demandPlanAdd");
	        }
	        
	    	$scope.addMateriel = function (){
	    		if(!isNull($scope.demandPlan)&&!isNull($scope.demandPlan.serialNum)){
	    			$("#basicMaterielInfo").modal("show");
	    		}else{
	    			toastr.warning("请先保存需求计划基本信息！");
	    		}
				
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
	        			if($scope.rootMateriels.length==0){
	        				for(var i = 0;i < data.data.length;i++){
	        					$scope.rootMateriels.push((data.data)[i]);
	        					$scope["demandPlanMaterielEdit"+i] = false;
	        					$scope["demandPlanMaterielView"+i] = false;
	        				}
	        			}else{
			        		for(var i = 0;i < data.data.length;i++){
		        				$scope.rootMateriels.splice(0,0,(data.data)[i]);
		        				$scope["demandPlanMaterielEdit"+i] = false;
								$scope["demandPlanMaterielView"+i] = false;
								$scope["demandPlanMaterielEdit" + ($scope.rootMateriels.length-1)] = true;
								$scope["demandPlanMaterielView" + ($scope.rootMateriels.length-1)] = true;
			        		}
	        			}
	        			/*if(!isNull($stateParams.serialNum)){
	 		    			for(var i=0;i<$scope.rootMateriels.length;i++){
	 			        			$scope["demandPlanMaterielEdit"+i] = true;
	 								$scope["demandPlanMaterielView"+i] = true;
	 			        	}
	 		    		}*/
	        			$("#basicMaterielInfo").modal("hide");
	        		},function(data){
	        			//调用承诺接口reject();
	        		});
	    	}
	    	
	    	 $('#basicMaterielInfo').on('hide.bs.modal', function (e) { 
	    		 clearChecked();
		     })
	    	
	    	function clearChecked(){
	    		table.$('input[type="checkbox"]').each(
						function() {
							// If checkbox exist in DOM
							if ($.contains(document, this)) {
								// If checkbox is checked
								this.checked = false;
							}
				});
	    	}
	    	
	    	
	    	
	    	
	        /**
	         * 保存需求计划
	         */
			$scope.saveDemandPlan = function() {
				if($('#demandPlanForm').valid()){
					handle.blockUI();
					$scope.demandPlan.createTime = null;
					$scope.demandPlan.updateTime = null;
					var promise = demandPlanService
							.saveDemandPlan($scope.demandPlan);
					promise.then(function(data) {
						if (!handle.isNull(data.data)) {
							$(".modal-backdrop").remove();
							toastr.success("保存成功");
							handle.unblockUI();
							//$scope.demandPlan = data.data;
							viewDemandPlan(data.data.serialNum);
							console.log(data.data);	
							$scope.demandPlanView = true;
							$scope.demandPlanAdd = true;
							$scope.demandPlanEdit = false;
							$(".alert-danger").hide();
						} else {
							$(".modal-backdrop").remove();
							handle.unblockUI();
							toastr.error("保存失败！请联系管理员");
							console.log(data);
						}

					}, function(data) {
						// 调用承诺接口reject();
						$(".modal-backdrop").remove();
						handle.unblockUI();
						toastr.error("保存失败！请联系管理员");
						console.log(data);
					});
				}
			}; 
			
			$scope.toEditDemandPlan = function () {
				// Iterate over all checkboxes in the table
				var id_count = $('#demandPlanTable input[type="checkbox"]:checked').length;
				if(id_count==0){
					toastr.warning("请选择您要编辑的记录");
				}else if(id_count>1){
					toastr.warning("只能选择一条数据进行编辑");
				}else{
					var serialNum = $('#demandPlanTable input[type="checkbox"]:checked').val();
					$state.go("demandPlanAdd",{serialNum:serialNum});
				}
				
	        };
	        
	      /*  function viewDemandPlan() {
		        	var promise = demandPlanService.viewDemandPlan($scope.demandPlan.serialNum);
					promise.then(function(data) {
						if (!handle.isNull(data.data)) {
							$scope.demandPlan = data.data;
						} else {
							console.log(data);
						}
					}, function(data) {
						console.log(data);
					});
	        };*/
			
			
			 /**
			 * 编辑模式
			 */
	        $scope.editDemandPlanBasic=function (comId) {
	        	$scope.demandPlanView = false;
	        	$scope.demandPlanAdd = false;
	        	$scope.demandPlanEdit = true;
	        	
	        };
	        
	        
	        /**
	         * 取消
	         */
	        $scope.cancelDemandPlanBasic=function () {
	        	getDemandPlanInfo($scope.demandPlan.serialNum);
	        	$scope.demandPlanView = true;
	        	$scope.demandPlanAdd = true;
	        	$scope.demandPlanEdit = false;
	        };
	        
	        var getDemandPlanInfo = function(serialNum,type){
	    	   if(!handle.isNull(serialNum)){
	    			 var promise = demandPlanService.demandPlanInfo(serialNum);
	 	        	promise.then(function(data){
	 	        		if(!handle.isNull(data.data.demandPlan)){
		 	        		$scope.demandPlan = data.data.demandPlan;
		 	        	}
	 	        		if(!handle.isNull(data.data.demandPlanMateriels)){
	 	        			$scope.rootMateriels = data.data.demandPlanMateriels;
	 	        		}
	 	        		
	 	        		
	 	        		if(!isNull($stateParams.serialNum)){
	 		    			for(var i=0;i<$scope.rootMateriels.length;i++){
	 			        			$scope["demandPlanMaterielEdit"+i] = true;
	 								$scope["demandPlanMaterielView"+i] = true;
	 			        	}
	 		    		}
	 	            },function(data){
	 	               //调用承诺接口reject();
	 	            });
	    		 }
		    }
	        
	        var viewDemandPlan = function(serialNum){
	        	if(!handle.isNull(serialNum)){
	        		var promise = demandPlanService.viewDemandPlan(serialNum);
	        		promise.then(function(data){
	        			if(!handle.isNull(data.data)){
	        				$scope.demandPlan = data.data;
	        			}
	        		},function(data){
	        			//调用承诺接口reject();
	        		});
	        	}
	        }
	        
	        
	        

			/**
			 * 保存需求计划物料信息
			 */
			$scope.saveDemandPlanMateriel = function(materiel,index) {
				var demandPlanMateriel = {};
				handle.blockUI();
				demandPlanMateriel.createTime = null;
				demandPlanMateriel.updateTime = null;
				demandPlanMateriel.demandPlanSerial = $scope.demandPlan.serialNum;
				if(isNull(materiel.supplyMaterielSerial)){
					demandPlanMateriel.supplyMaterielSerial = materiel.serialNum;
					demandPlanMateriel.materielSerial = materiel.serialNum;
				}else{
					demandPlanMateriel.serialNum = materiel.serialNum;
				}
				demandPlanMateriel.deliveryDate = materiel.deliveryDate;
				demandPlanMateriel.deliveryAddress = materiel.deliveryAddress;
				demandPlanMateriel.amount = materiel.amount;
				
				var promise = demandPlanService
				.saveDemandPlanMateriel(demandPlanMateriel);
				promise.then(function(data) {
					if (!handle.isNull(data.data)) {
						$(".modal-backdrop").remove();
						toastr.success("保存成功");
						handle.unblockUI();
						var company = data.data;
						// $state.go('companyAdd',company,{reload:true});
						$scope.company = company
						console.log(data.data);
						$scope["demandPlanMaterielEdit"+index] = true;
						$scope["demandPlanMaterielView"+index] = true;
						$(".alert-danger").hide();
						// $stateParams.comId =
						// company.comId;
						// $location.search('comId',company.comId);
					} else {
						$(".modal-backdrop").remove();
						handle.unblockUI();
						toastr.error("保存失败！请联系管理员");
						console.log(data);
					}
					
				}, function(data) {
					// 调用承诺接口reject();
					$(".modal-backdrop").remove();
					handle.unblockUI();
					toastr.error("保存失败！请联系管理员");
					console.log(data);
				});
			}; 
			
	        /**
			 * 编辑（列表）
			 */
	        $scope.editDemandPlanMateriel=function (materiel) {
	        	//.show_materiels = false;
	        	for(var i=0;i<$scope.rootMateriels.length;i++){
	        		if(materiel.serialNum == $scope.rootMateriels[i].serialNum){
	        			$scope["demandPlanMaterielEdit"+i] = false;
						$scope["demandPlanMaterielView"+i] = false;
	        		}
	        	}
	        	
	        };  
	        
	        /**
	         * 编辑（行内）
	         */
/*	        $scope.toEditCompany=function () {
				// Iterate over all checkboxes in the table
				var id_count = table.$('input[type="checkbox"]:checked').length;
				if(id_count==0){
					toastr.warning("请选择您要编辑的记录");
				}else if(id_count>1){
					toastr.warning("只能选择一条数据进行编辑");
				}else{
					var comId = table.$('input[type="checkbox"]:checked').val();
					$state.go("companyAdd",{comId:comId});
				}
				
	        };  */
	        
	        /**
	         * 删除
	         */
	        $scope.deleteDemandPlanMateriel=function (materiel) {
	        	handle.confirm("确定删除吗？",function(){
	        		handle.blockUI();
	        		if($scope.rootMateriels.length > 0){
	        			for(var i=0;i<$scope.rootMateriels.length;i++){
	        				if(materiel == $scope.rootMateriels[i]){
	        					$scope.rootMateriels.splice(i,1);
	        				}
	        			}
	        		}
	        		if(!isNull(materiel.supplyMaterielSerial)){
	        			var promise = demandPlanService.deleteDemandPlanMateriel(materiel.serialNum);
		        		promise.then(function(data){
		        			if(data.data == "1"){
		        				toastr.success("删除成功");
			        			handle.unblockUI(); 
		        			}else{
		        				toastr.error("删除失败！请联系管理员");
				            	console.log(data);
		        			}
		        			
		 	            },function(data){
		 	               //调用承诺接口reject();
		 	            	toastr.error("删除失败！请联系管理员");
			            	console.log(data);
		 	            });
	        		}
	        	});
			   
	        };
	        
	        
	        /**
	         * 批量删除
	         */
	        $scope.deleteDemandPlan = function () {
	        	var id_count = $('#demandPlanTable input[type="checkbox"]:checked').length;
				if(id_count==0){
					toastr.warning("请选择您要删除的记录");
					return;
				}
	        	handle.confirm("确定删除吗？",function(){
	        		var ids = '';
					// Iterate over all checkboxes in the table
	        		$('#demandPlanTable input[type="checkbox"]').each(
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
	        		var promise = demandPlanService.deleteDemandPlan(ids);
	        		promise.then(function(data){
	        			toastr.success("删除成功");
	        			handle.unblockUI();
	        			createTable(10,1,true);
	        			//table.ajax.reload(); // 重新加载datatables数据
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
		    			handle.createPage("#simple",data.data,"rest/demandPlan/demandPlanList",createTable,init);
		            },function(data){
		               //调用承诺接口reject();
		         });
	       }
	       
	      
	       
	       $scope.exportCompany = function(){
	    	 handle.blockUI("正在导出数据，请稍后"); 
	    	 window.location.href=$rootScope.basePath+"/rest/company/exportCompany";
	    	 handle.unblockUI(); 
	       }
	       
	       

	       
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
	    	   var call2 = "operation_d"+index;
	    	   if(type=='finance'){
	    		   call =  "operation_f"+index;
	    	   }
	    	   $scope[call] = true;
	    	   $scope[call2] = true;
	       };
	       
	       /**
	        * 隐藏编辑、删除操作
	        */
	       $scope.hideOperation = function(type,index){
	    	   var call = "operation_c"+index;
	    	   var call2 = "operation_d"+index;
	    	   if(type=='finance'){
	    		   call =  "operation_f"+index;
	    	   }
	    	   $scope[call]= false;
	    	   $scope[call2]= false;
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
	    	    	toastr.warning("请选择Excel文件！");
	    	    }
	    	    console.log(file.name);
	    	    var type = file.name.substring(file.name.lastIndexOf("."));
	    	   if(type != ".xls"){
	    		   toastr.warning("文件格式不正确，需要xls类型的Excel文档");
	    		   return;
	    	   }
	    	   	handle.blockUI("正在导入中，请不要进行其他操作"); 
	    	   	var promise = companyService.uploadExcel();
       			promise.then(function(data){
       				handle.unblockUI(); 
       				if(data.data.data=="success"){
       					toastr.success("导入成功");
       					table.ajax.reload();
       				}else{
       					toastr.error(data.data.data);
       				}
       				$('#import').modal('hide'); 
	            },function(data){
	               //调用承诺接口reject();
	            	toastr.error("操作失败");
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
