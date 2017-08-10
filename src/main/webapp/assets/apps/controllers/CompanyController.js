/**
 * 
 */

angular.module('MetronicApp').controller('CompanyController',['$rootScope','$scope','$state','$http','companyService','$location','$compile','$stateParams','FileUploader',function($rootScope,$scope,$state,$http,companyService,$location,$compile,$stateParams,FileUploader) {
	 $scope.$on('$viewContentLoaded', function() {   
	    	// initialize core components
		 	
		    handle = new pageHandle();
	    	App.initAjax();
	    	if($location.path()=="/companyAdd"){
	    		handle.pageRepeater();
	    		_index = 0; 
	    		$scope.companyQualifications =[{}];
	    		handle.datePickersInit();
	    		getCompanyInfo($stateParams.comId);
	    		validatorInit();
	    		
	 		}else{
	 			//createTable(15,1,true);
	 			loadTable();
	 		//	 $("#comViewPage").html($compile($("#comViewContent").html())($scope));
	 			$("#sample_3_tools > li > a.tool-action").on("click",
	 			        function(){debugger;
	 			            var e=$(this).attr("data-action");
	 			           $("#companyTable").DataTable().button(e).trigger();
	 			 })
	 		}
	    	// set default layout mode
	    	$rootScope.settings.layout.pageContentWhite = true;
	        $rootScope.settings.layout.pageBodySolid = false;
	        $rootScope.settings.layout.pageSidebarClosed = false;
	        console.log("------------->"+$scope.company);
	    	
	 });
	 
	 var validatorInit= function(){
		 
		 if ($.validator) {
	           $.validator.prototype.elements = function () {
	               var validator = this,
	                 rulesCache = {};
	 
	               // select all valid inputs inside the form (no submit or reset buttons)
	               return $(this.currentForm)
	               .find("input, select, textarea")
	               .not(":submit, :reset, :image, [disabled]")
	               .not(this.settings.ignore)
	               .filter(function () {
	                   if (!this.name && validator.settings.debug && window.console) {
	                       console.error("%o has no name assigned", this);
	                   }
	                   //注释这行代码
	                   // select only the first element for each name, and only those with rules specified
	                   //if ( this.name in rulesCache || !validator.objectLength($(this).rules()) ) {
	                   //    return false;
	                   //}
	                   rulesCache[this.name] = true;
	                   return true;
	               });
	           }
	    }
		 
	 }
	 
	  //创建对象
	  var uploader = $scope.uploader = new FileUploader({url:'rest/fileOperate/uploadSingleFile'});
	 
	  uploader.onAfterAddingFile = function(item){
		  if(item.file.size>10000000){
			  //toastr.warning("文件大小超过10M！");
			  uploader.cancelAll();
		  }
	  }
	  //添加文件到上传队列后
	  uploader.onCompleteAll = function () {
		  uploader.clearQueue();
	  };
	  //上传成功
	  uploader.onSuccessItem = function (fileItem,response, status, headers) {
		  if (status == 200){ 
			  if(response==""){
				  toastr.error("上传失败！");
				  return;
			  }
		  		toastr.success("上传成功！");
		  	  for(var i=0;i < $scope.companyQualifications.length;i++){
		  		  if($scope.qualification_temp==$scope.companyQualifications[i]){
		  			$scope.companyQualifications[i].qualificatioImage = response;
		  		  }
		  	  }
		  }else{
			  toastr.error("上传失败！");
			  $scope.qualification_temp.qualificatioImage = response;
		  }
		};
	  //上传失败
	  uploader.onErrorItem = function (fileItem, response, status, headers) {
			toastr.error("上传失败！");
	  };
	  
	  $scope.uploadFile = function(item){
		  $scope.qualification_temp = item;
	  }
	  
	  $scope.up = function(file){
		  uploader.clearQueue();
		  uploader.addToQueue(file);
		  uploader.uploadAll();
	  }
		
	 var table;
	 /** 加载列表 Start**/
	 var loadTable = function(){
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
							

			table = $("#companyTable")
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
								/*fixedHeader : {// 固定表头、表底
									header : !0,
									footer : !0,
									headerOffset : a
								},*/
								// select: true,行多选
								order : [ [ 1, "asc" ] ],// 默认排序列及排序方式
								bRetrieve : true,
								'scrollX': false,
								  buttons: [
							                {
							                },
							                {
							                },
							                {
							                	 extend: "print",
								                 className: "btn dark btn-outline"
							                }
							            ],
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
									mData : 'comType'
								}, {
									mData : 'comNature'
								}, {
									mData : 'businessType'
								}, {
									mData : 'businessNature'
								}, {
									mData : 'legalPerson'
								}, {
									mData : 'address'
								} , {
									mData : 'status'
								}],
								'aoColumnDefs' : [ {
									'targets' : 0,
									'searchable' : false,
									'orderable' : false,
									'className' : 'dt-body-center',
									'render' : function(data,
											type, row, meta) {
										return '<input type="checkbox" name="id[]" value="'
												+ $('<div/>')
														.text(
																data)
														.html()
												+ '"  ng-click="showCompanyInfo(\''+row.comId+'\')">';
									},"createdCell": function (td, cellData, rowData, row, col) {
										 $compile(td)($scope);
								    }
								},{
									'targets' : 1,
									'render' : function(data,
											type, row, meta) {
										return '<a   ng-click="showCompanyInfoModal(\''+row.comId+'\')">'+data+'</a>';
										//return data;
									},"createdCell": function (td, cellData, rowData, row, col) {
										 $compile(td)($scope);
								    }
								}/*,{
									'targets' : 11,
									'render' : function(data,
											type, row, meta) {
										return '<a   ng-click="editCompany(\''+row.comId+'\')"><i class="fa fa-edit" title="编辑"></i></a>&nbsp;&nbsp;&nbsp;<a   ng-click="deleteCompany(\''+row.comId+'\')"><i class="fa fa-trash" title="删除"></i></a>';
									},"createdCell": function (td, cellData, rowData, row, col) {
										 $compile(td)($scope);
								    }
								}*/ ],

							})
			// 构建datatables结束***************************************
	 }
	/**加载列表结束**/
	 
		
						
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
		
		// 页面加载完成后调用，验证输入框
		$scope.$watch('$viewContentLoaded', function() {  
			qualificationFormValid();
		}); 
		
		
		var qualificationFormValid = function(){
			 var form1 = $('#qualificationForm');
	            var error1 = $('.alert-danger', form1);
	            var success1 = $('.alert-success', form1);
	            form1.validate({
	                errorElement: 'span', //default input error message container
	                errorClass: 'help-block help-block-error', // default input error message class
	                focusInvalid: false, // do not focus the last invalid input
	                ignore: "",  // validate all fields including form hidden input
	                messages:  {
	                	qualificationName:{required:"资质名称不能为空！"},
	                	qualificationNum:{required:"资质号码不能为空！"},
	                	validityDate:{required:"有效期不能为空！"}
		            },
	                rules: {
	                	qualificationName: {
							required: !0
						},
						qualificationNum: {
							required: !0
						},
						validityDate: {
							required: !0
						}
	                },

	                invalidHandler: function (event, validator) { //display error alert on form submit              
	                    success1.hide();
	                    error1.show();
	                    App.scrollTo(error1, -200);
	                },

	                errorPlacement: function (error, element) { // render error placement for each input type
	                    var cont = $(element).parent('.input-group');
	                    if (cont.size() > 0) {
	                        cont.after(error);
	                    } else {
	                        element.after(error);
	                    }
	                },

	                highlight: function (element) { // hightlight error inputs

	                    $(element)
	                        .closest('.form-group').addClass('has-error'); // set error class to the control group
	                },

	                unhighlight: function (element) { // revert the change done by hightlight
	                    $(element)
	                        .closest('.form-group').removeClass('has-error'); // set error class to the control group
	                },

	                success: function (label) {
	                    label
	                        .closest('.form-group').removeClass('has-error'); // set success class to the control group
	                },

	                submitHandler: function (form) {
	                    success1.show();
	                    error1.hide();
	                }
	            });
		}
		
		
		// 页面加载完成后调用，验证输入框
		$scope.$watch('$viewContentLoaded', function() {  
			var e = $("#contactForm"),
			r = $(".alert-danger", e),
			i = $(".alert-success", e);
			e.validate({
				errorElement: "span",
				errorClass: "help-block help-block-error",
				focusInvalid: !1,
				ignore: "",
				messages: {
					contactName:{required:"姓名不能为空！"},
					contactTitle:{required:"职位不能为空！"},
					department:{required:"部门/公司不能为空！"},
					responsibility:{required:"管理职责不能为空！"},
					contactTel:{required:"电话不能为空！",digits:"请输入正确的电话, 必须为数字！",rangelength:jQuery.validator.format("电话必须在{0}到{1}位数字之间！")},
					contactEmail:{email:"请输入正确的邮箱！"}
				},
				rules: {
					contactName: {
						required: !0
					},
					contactTitle: {
						required: !0
					},
					department: {
						required: !0
					},
					responsibility: {
						required: !0
					},
					contactTel: {
						required: !0,
						digits:true,
						rangelength:[7,20]
					},
					contactEmail:{
						email:true
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
		// 页面加载完成后调用，验证输入框
		$scope.$watch('$viewContentLoaded', function() {  
			 
			var e = $("#companyFinanceForm"),
			r = $(".alert-danger", e),
			i = $(".alert-success", e);
			e.validate({
				errorElement: "span",
				errorClass: "help-block help-block-error",
				focusInvalid: !1,
				ignore: "",
				messages: {
					openingBank:{required:"银行不能为空！"},
					accountName:{required:"户名不能为空！"},
					accountNumber:{required:"账号不能为空！",digits:"银行账号必须为数字！"}
				},
				rules: {
					accountName: {
						required: !0
					},
					openingBank: {
						required: !0
					},
					accountNumber: {
						required: !0,
						digits:true
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
	        $scope.toAddCompany=function () { 
	        		$scope.company = {};
	        		$scope.companyQualifications = null;
	        		$scope.companyContacts = null;
	        		$scope.companyFinances = null;
	        		$state.go("companyAdd");
	        }; 
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
	    	    $.validator.unobtrusive.parse($("#qualificationForm"));
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
	    	    getCompanyInfo($scope.company.comId,'companyQualification');
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
	       $scope.deleteRepeat = function(obj){
	    	   //$scope.companyQualifications.splice(_index,1);
	    	   for(var i=0;i<$scope.companyQualifications.length;i++){
   	    		   if($scope.companyQualifications[i]==obj){
   	    			   $scope.companyQualifications.splice(i,1);
   	    			   break;
   	    		   }
	    	   }
	    	   _index--;
	       };
	       
	       /**
	        * 企业资质初始化日期控件
	        */
	       $scope.repeatDone = function(){
	    	   handle.datePickersInit();
	    	   //$("#qualificationForm").removeData("validator").removeData("unobtrusiveValidation");
	    	 
	    	   //$.validator.parse($("#qualificationForm"));
	    	   //qualificationFormValid();
	    	  // validatorInit();
	    	  
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
	       
	       
	       $scope.downloadFile = function(obj){
	    	   if(!handle.isNull(obj)){
	    		   window.location.href= $rootScope.basePath+"/rest/fileOperate/downloadFile?fileName="+obj.qualificatioImage;
	    	   }else{
	    		   toastr.error("下载失败!");
	    	   }
	       }
	       
	       $scope.removefile = function(obj){
	    	   for(var i=0;i < $scope.companyQualifications.length;i++){
			  		  if(obj == $scope.companyQualifications[i]){
			  			$scope.companyQualifications[i].qualificatioImage = "";
			  		  }
			  	}
	       }

	   
	       

	       
	       

}]); 