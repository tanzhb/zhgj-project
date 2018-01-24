/**
 * 
 */

angular.module('MetronicApp').controller('CompanyController',['$rootScope','$scope','$state','$http','companyService','orderService','$location','$compile','$stateParams','FileUploader',function($rootScope,$scope,$state,$http,companyService,orderService,$location,$compile,$stateParams,FileUploader) {
	 $scope.$on('$viewContentLoaded', function() {   
	    	// initialize core components
		 	
		    handle = new pageHandle();
	    	App.initAjax();
	    	if($location.path()=="/companyAdd"){
	    		handle.pageRepeater();
	    		_index = 0; 
	    		$scope.companyQualifications =[{}];
	    		handle.datePickersInit();
	    		initCustomers();
	    		initSuppliers();
	    		getCompanyInfo($stateParams.comId);
	    		//qualificationFormValid();
	    		validatorInit();
	    		if(isNull($stateParams.comId)){
	    			$scope.company={};
	    			$scope.company.tradeType = '内贸';
		    		$rootScope.setNumCode("C",function(newCode){//
	        			$scope.company.comNum= newCode;//企业编码
	        		});
	    		}
	 		}else{
	 			initTabClass();
	 			
	 			//createTable(15,1,true);
	 			loadCompanyTable($stateParams.type);
	 			//loadTable($stateParams.type);
	 		//	 $("#comViewPage").html($compile($("#comViewContent").html())($scope));
	 			$("#sample_3_tools > li > a.tool-action").on("click",
	 			        function(){
	 			            var e=$(this).attr("data-action");
	 			           $("#companyTable").DataTable().button(e).trigger();
	 			 })
	 			$scope.companyInfo =[];
	 		}
	    	// set default layout mode
	    	$rootScope.settings.layout.pageContentWhite = true;
	        $rootScope.settings.layout.pageBodySolid = false;
	        $rootScope.settings.layout.pageSidebarClosed = false;
	       // console.log("------------->"+$scope.company);
	    	
	 });
	 
	 var initTabClass = function(){
			var liobj = $('ul[class="nav nav-tabs"]>li')[0]
				$(liobj).addClass("active");
//				$($('#'+liobj.id+' a')[0].dataset.target).addClass("active");
				$($('ul[class="nav nav-tabs"]>li a')[0].dataset.target).addClass("active");
				
//				methodName = $('#'+liobj.id+' a')[0].attributes[2].nodeValue; 
				methodName = '';
				try{
					methodName = '$scope.'+$('ul[class="nav nav-tabs"]>li a')[0].attributes[2].nodeValue;
				}catch(e){ 
//					alert(methodName+"()不存在！"); 
				}
				function m(methodName){ 
					//初始化this.func属性, 
					this.func = function(){}; 
					try{ 
					//这里用eval方法，把我们传进来的这个方法名所代表的方法当作一个对象来赋值给method1的func属性。 
					//如果找不到methodName这个对应的对象,则eval方法会抛异常 
						this.func = eval(methodName); 
					}catch(e){ 
//						alert(methodName+"()不存在！"); 
					} 
				} 
				try{
					var c = new m(methodName); 
					c.func(); 
				}catch(e){ 
//					alert(methodName+"()不存在！"); 
				} 
		}
	  /**
		 * 加载所有公司数据
		 */
		var loadCompanyTable = function(type){
			var promise = orderService.getComId();
	        	promise.then(function(data){
	        		debugger;
	        		if(data==""){//平台人员
	        			if($stateParams.type==null||$stateParams.type=="buy"){
	        				$("#buy").addClass("active");
	        				$("#supply").removeClass("active");
			 				$("#other").removeClass("active");
			 				$scope.type="buy";
	        			}else if($stateParams.type=="supply"){
	        				$("#supply").addClass("active");
	        				$("#buy").removeClass("active");
			 				$("#other").removeClass("active");
			 				$scope.type="supply";
	        			}else if($stateParams.type=="other"){
	        				$("#other").addClass("active");
	        				$("#supply").removeClass("active");
	        				$("#buy").removeClass("active");
	        			}
	        			loadTable($stateParams.type);
	        		}else if(data.comType=="1"){//采购商
	        			$("#buy").addClass("active");
	        			$scope.isNotSupply=true;
	        			$scope.isNotOther=true;
	        			$scope.type="buy";
	        			loadTable("");
	        		}else if(data.comType=="2"){//供应商
	        			$("#supply").addClass("active");
	        			$scope.isNotBuy=true;
	        			$scope.isNotOther=true;
	        			$scope.type="supply";
	        			loadTable("");
	        		}
	        		
	        	},function(data){
	        		//调用承诺接口reject();
	        	});
		}
		/**
		 * 加载供应商数据
		 */
		var initSuppliers = function(judgeString){
			/*if(judgeString==undefined){
			var promise = orderService.initSuppliers();
	        	promise.then(function(data){
	        		$scope.supplyCompanys = data.data;
	        		setTimeout(function () {
	        			$('select[name="supplyComId"]').selectpicker({
	                        showSubtext: true,
	                        size : 5
	                    });
	        			$('select[name="supplyComId"]').selectpicker('refresh');//刷新插件
	        			
	                }, 100);
	        	},function(data){
	        		//调用承诺接口reject();
	        	});
			}else{
				$('select[name="supplyComId"]').selectpicker({
                    showSubtext: true,
                    size : 5
                });
    			$('select[name="supplyComId"]').selectpicker('refresh');//刷新插件
			}*/
			var promise = orderService.initSuppliers();
        	promise.then(function(data){
        		$scope.supplyCompanys = data.data;
        		setTimeout(function () {
        			$('select[name="supplyComId"]').selectpicker({
                        showSubtext: true,
                        size : 5
                    });
        			$('select[name="supplyComId"]').selectpicker('refresh');//刷新插件
        			
                }, 100);
        	},function(data){
        		//调用承诺接口reject();
        	});
		}
		   /**
		 * 加载客户数据
		 */
		var initCustomers = function(judgeString){
		/*	if(judgeString==undefined){
			var promise = orderService.initCustomers();
	        	promise.then(function(data){
	        		$scope.buyCompanys = data.data;
	        		setTimeout(function () {
	        			$('select[name="buyComId"]').selectpicker({
	                        showSubtext: true,
	                        size : 5
	                    });
	        			$('select[name="buyComId"]').selectpicker('refresh');//刷新插件
	        			
	                }, 100);
	        	},function(data){
	        		//调用承诺接口reject();
	        	});
			}else{
				$('select[name="buyComId"]').selectpicker({
                    showSubtext: true,
                    size : 5
                });
    			$('select[name="buyComId"]').selectpicker('refresh');//刷新插件
			}*/
			var promise = orderService.initCustomers();
        	promise.then(function(data){
        		$scope.buyCompanys = data.data;
        		setTimeout(function () {
        			$('select[name="buyComId"]').selectpicker({
                        showSubtext: true,
                        size : 5
                    });
        			$('select[name="buyComId"]').selectpicker('refresh');//刷新插件
        			
                }, 100);
        	},function(data){
        		//调用承诺接口reject();
        	});
		}
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
	 
	  uploader.onAfterAddingFile = function(item){debugger;
		  if($scope.cancelCheck){
			  if(item.file.size>5242880){
				  toastr.warning("上传文件大小不能超过5M！");
				   //uploader.removeFromQueue(item);
				  
				    //uploader.clearQueue();
				    uploader.cancelAll();
				 
				    uploader.clearQueue();
				    uploader.cancelItem(item);
				   
			  }
		  }
	  }
	  //添加文件到上传队列后
	  uploader.onCompleteAll = function () {
		  uploader.clearQueue();
	  };
	  
	  uploader.onCancelItem = function(fileItem,response, status, headers){
		  for(var i=0;i < $scope.companyQualifications.length;i++){
	  		  if($scope.qualification_temp==$scope.companyQualifications[i]){
	  			if(isNull($scope.companyQualifications[i].qualificatioImage)){
	  			  $("#resetFile"+$scope.qualification_index).trigger("click");
	  			}
	  		  }
	  	  }
	  }
	  //上传成功
	  uploader.onSuccessItem = function (fileItem,response, status, headers) {
		  if (status == 200){ 
			  if(response.filename==""){
				  toastr.error("上传失败！");
				  return;
			  }
		  		toastr.success("上传成功！");
		  	  for(var i=0;i < $scope.companyQualifications.length;i++){
		  		  if($scope.qualification_temp==$scope.companyQualifications[i]){
		  			$scope.companyQualifications[i].qualificatioImage = response.filename;
		  		  }
		  	  }
		  }else{
			  toastr.error("上传失败！");
			  $scope.qualification_temp.qualificatioImage = "";
		  }
		};
	  //上传失败
	  uploader.onErrorItem = function (fileItem, response, status, headers) {
			toastr.error("上传失败！");
	  };
	  
	  $scope.uploadFile = function(item,index){
		  $scope.qualification_temp = item;
		  $scope.qualification_index = index;
	  }
	  
	  $scope.up = function(file){
		  uploader.clearQueue();
		  $scope.cancelCheck = true;
		  uploader.addToQueue(file);
		  $scope.cancelCheck = false;
		  uploader.uploadAll();
	  }
	  
      $scope.removefile = function(obj){
   	   		for(var i=0;i < $scope.companyQualifications.length;i++){
		  		  if(obj == $scope.companyQualifications[i]){
		  			$scope.companyQualifications[i].qualificatioImage = "";
		  		  }
		  	}
      }
		
	 var table,tableUrl;
	 /** 加载列表 Start**/
	 var loadTable = function(type){
		 if(type==null){
			 type="buy";
		 }
		 tableUrl="rest/company/companyList?params="+type;
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
										sortAscending : ": 以升序排列此列",
										sortDescending : ": 以降序排列此列"
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
								//serverSide: true,
								ajax : tableUrl,// 加载数据中user表数据    
							/*	ajax :{ "url":$rootScope.basePath
										+ "/rest/company/companyList",// 加载数据中user表数据    
										"contentType": "application/json",
									    "type": "POST",
									    "data": function ( d ) {
									      return JSON.stringify( d );
									    }},*/
								"aoColumns" : [

								{
									mData : 'comId'
								}, {
									mData : 'comNum'
								}, {
									mData : 'comName'
								}, {
									mData : 'comTypeName'
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
										/*return '<input type="checkbox"  class="checkbox-inline" id="'+data+'" name="id[]" value="'
												+ $('<div/>')
														.text(
																data)
														.html()
												+ '" data-check="false"  ng-click="showCompanyInfo(\''+row.comId+'\')">';*/
										return '<label class="mt-checkbox mt-checkbox-single mt-checkbox-outline">'+
	                                     '<input type="checkbox" data-check="false" class="checkboxes" ng-click="showCompanyInfo(\''+row.comId+'\')" id="'+data+'" value="'+data+'" data-set="#companyTable .checkboxes" />'+
	                                     '<span></span></label>';
									},"createdCell": function (td, cellData, rowData, row, col) {
										 $compile(td)($scope);
								    }
								},{
									'targets' : 1,
									'render' : function(data,
											type, row, meta) {
										//return '<a   ng-click="showCompanyInfoModal(\''+row.comId+'\')">'+isNull(data)?"未设置编号":data+'</a>';
										return (isNull(data)?"未设置编号":data);
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

							}).on('order.dt',
						              function() {
				                  console.log('排序');
				              }).on('page.dt', 
				              function () {
				            	  console.log('翻页');
					          })
			// 构建datatables结束***************************************
					           $("#companyTable").find(".group-checkable").change(function() {
						            var e = jQuery(this).attr("data-set"),
						            t = jQuery(this).is(":checked");
						            jQuery(e).each(function() {
						                t ? ($(this).prop("checked", !0), $(this).parents("tr").addClass("active")) : ($(this).prop("checked", !1), $(this).parents("tr").removeClass("active"))
						            })
						        }),
						        $("#companyTable").on("change", "tbody tr .checkboxes",
						        function() {
						            $(this).parents("tr").toggleClass("active")
						        })
	 }
	/**加载列表结束**/
	 
	 function retrieveData( sSource, aoData, fnCallback ) {   
		    //将客户名称加入参数数组   
		    aoData.push( { "name": "customerName", "value": $("#customerName").val() } );   
		  
		    $.ajax( {   
		        "type": "POST",    
		        "contentType": "application/json",   
		        "url": sSource,    
		        "dataType": "json",   
		        "data": JSON.stringify(aoData), //以json格式传递   
		        "success": function(resp) {   
		            fnCallback(resp); //服务器端返回的对象的returnObject部分是要求的格式  
		        }   
		    });   
		} 
						
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
					            		contact:{required:"维护人员不能为空！"}
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
						                	isPhone:!0
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
					contactTel:{required:"电话不能为空！"},
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
						isPhone: !0
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
			var e = $("#companyAddressForm"),
			r = $(".alert-danger", e),
			i = $(".alert-success", e);
			e.validate({
				errorElement: "span",
				errorClass: "help-block help-block-error",
				focusInvalid: !1,
				ignore: "",
				messages: {
					address:{required:"地址不能为空！"},
					/*zipCode:{required:"邮编不能为空！"},*/
					contactTel:{required:"联系电话不能为空！"},
					mobileNum:{required:"手机不能为空！"}
				},
				rules: {
					address: {
						required: !0
					},
				/*	zipCode: {
						required: !0
					},*/
					contactTel: {
						required: !0,
					isPhone: !0
					},
					mobileNum: {
						required: !0,
						isPhone: !0
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
					accountNumber:{required:"账号不能为空！",creditcard:"请输入正确的银行账号！"}
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
						creditcard:true
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
					$scope.companyInfo = [];
					if(!$('#example-select-all').data("check")){
						$('#example-select-all').data("check",true);
						$.each(rows,function(){
							//console.log($(this).find('input[type="checkbox"]').val());
							getCompanyInfo($(this).find('input[type="checkbox"]').val());
							$(this).find('input[type="checkbox"]').data("check","true");
						});
					}else{
						$('#example-select-all').data("check",false);
						$scope.company = null;
						$scope.companyQualifications = [];
						$scope.companyContacts = [];
						$scope.companyFinances = [];
						$scope.companyInfo = [];
						$.each(rows,function(){
							//console.log($(this).find('input[type="checkbox"]').val());
							
							$(this).find('input[type="checkbox"]').data("check","false");
						});
						$scope.$apply();
					}
					
					
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
	        		/*var isExist = false;
	        		var promise2 = companyService.checkComNumIsExist(($scope.company.comId==undefined?"":$scope.company.comId),$scope.company.comNum);
	        		promise2.then(function(data){
	        			if(data.data == "2"){
	        				isExist = true;
	        			}
	        		},function(data){
	        			//调用承诺接口reject();
	        		});
	        		if(isExist){
	        			$(".alert-danger").html("企业编号已存在！");
	        			return;
	        		}*/
	        		
	        		$scope.company.createTime=null;
	        		$scope.company.updateTime=null;
	        		if(($scope.companyContacts==undefined||$scope.companyContacts.length==0)){
	        			if($scope.companyContact1==undefined){
	        				$(".alert-danger").html("联系方式中联系人至少有一个!");
	    					$(".alert-danger").show();
	    					return;
	        			}
	        			
	        		}
	        		if(($scope.companyAddresses==undefined||$scope.companyAddresses.length==0)){
	        			if($scope.companyAddress1==undefined){
	        				$(".alert-danger").html("联系方式中联系地址至少有一个!");
	    					$(".alert-danger").show();
	    					return;
	        			}
	        		}
	        		
	        		/*if($scope.companyManage==undefined){
	        			$(".alert-danger").html("请输入管理信息!");
    					$(".alert-danger").show();
    					return;
	        			}else{
	        				if($scope.accendants==undefined){
		        				$(".alert-danger").html("维护人员至少有一个!");
		    					$(".alert-danger").show();
		    					return;
		        			}
	        			}*/
	        		if($scope.accendants==undefined){
        				$(".alert-danger").html("维护人员至少有一个!");
    					$(".alert-danger").show();
    					return;
        			}
	        		handle.blockUI();
	        		var promise = companyService.saveCompany($scope.company);
	        		promise.then(function(data){
	        			if(!handle.isNull(data.data)){
	        				if(data.data.comNum=="isExist"){
	        					handle.unblockUI();
	        					$(".alert-danger").html("企业编号已存在！");
	        					$(".alert-danger").show();
	        					return;
	        				}
	        				if(data.data.comName=="isExist"){
	        					handle.unblockUI();
	        					$(".alert-danger").html("企业名称已存在！");
	        					$(".alert-danger").show();
	        					return;
	        				}
	        				$(".modal-backdrop").remove();
		        			toastr.success("保存成功");
		        			handle.unblockUI();
		        			var company = data.data;
		        			//$state.go('companyAdd',company,{reload:true});
		        			$scope.company = company
		        			getCompanyInfo(company.comId,"company");
		        			console.log(data.data);
		        			$scope.companyView = true;
		        			$scope.companyAdd = true;
		        			$scope.companyEdit = false;
		        			$(".alert-danger").html("请输入正确的数据！");
		        			$(".alert-danger").hide();
		        			//$scope.saveCompanyQualification(true);
		        			//$stateParams.comId = company.comId;
		        			//$location.search('comId',company.comId);
		        			if(flagAddress&&flagContact){
		        			$scope.companyContact1.comId=company.comId;
		        			$scope.companyAddress1.comId=company.comId;
		        			$scope.companyContact=$scope.companyContact1;
		        			$scope.companyAddress1=$scope.companyAddress1;
		        			flagContact=false;
		        			flagAddress=false;
		        			 $scope.saveCompanyAddress();
		        			 $scope.saveCompanyContact();
		        			}
		        			$scope.saveCompanyManageInfo();//保存管理信息
	        			}else{
	        				$(".modal-backdrop").remove();
		        			handle.unblockUI();
	        				toastr.error("保存失败！请联系管理员");
			            	console.log(data);
	        			}
	        			
	        		},function(data){
	        			//调用承诺接口reject();
	        			$(".modal-backdrop").remove();
	        			handle.unblockUI();
	        			toastr.error("保存失败！请联系管理员");
		            	console.log(data);
	        		});
	        	}else{
	        		//$('#companyTab a:eq(0)').tab('show');
	        	}
	        	
	        }; 
$scope.showCompany=function(judgeString){
	 $state.go('company',{type:judgeString},{reload:true}); 
}
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
	        	//$scope.editCompanyQualification
	        	
	        };  
	        
	        /**
	         * 编辑（行内）
	         */
	        $scope.toEditCompany=function () {
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
	        				toastr.success("删除成功");
		        			handle.unblockUI();
			        		 $state.go('company',{},{reload:true}); 
	        			}else{
	        				toastr.error("保存失败！请联系管理员");
			            	console.log(data);
	        			}
	        			
	 	            },function(data){
	 	               //调用承诺接口reject();
	 	            	toastr.error("保存失败！请联系管理员");
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
					toastr.warning("请选择您要删除的记录");
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
	        			toastr.success("删除成功");
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
	        	if(isNull($scope.company)||isNull($scope.company.comId)){
	        		$state.go("company");
	        		return;
	        	}
	        	getCompanyInfo($scope.company.comId)
	        	if(type=="company"){
	        		$scope.companyView = true;
	       			$scope.companyAdd = true;
	       			$scope.companyEdit = false;
	        	}else if(type=="bill"){
	        		$scope.billView = true;
	       			$scope.billAdd = true;
	        	}else{
	       			$scope.companyQualificationView = true;
	       			$scope.companyQualificationAdd = true;
	       			$scope.companyQualificationEdit = false;
	       			$scope.companyMAdd = true;
	       			$scope.companyMView = true;
	       			
	        	}
	        	//$scope.cancelCompanyQualification();
	        };
	        
	        /*************开票信息START*****************/
	        /**
	         * 编辑
	         */
	        $scope.editBillInfo=function (comId) {
	        	$scope.billView = false;
	        	$scope.billAdd = false;
	        	
	        };  
	        
	        /**
	         * 保存
	         */
	        $scope.saveBillInfo=function () {
	        	if($scope.company==null||isNull($scope.company.comId)){
	        		toastr.warning("您的企业信息还未保存！");
	        		return;
	        	}
        		handle.blockUI();
        		var company = {};
        		company.comId = $scope.company.comId;
        		company.corporatePresence = $scope.company.corporatePresence;
        		company.tel = $scope.company.tel;
        		company.address = $scope.company.address;
        		company.corporatePresence = $scope.company.corporatePresence;
        		company.openingBank = $scope.company.openingBank;
        		company.accountNumber = $scope.company.accountNumber;
        		company.taxpayeNumber = $scope.company.taxpayeNumber;
        		company.billRemark = $scope.company.billRemark;
        		var promise = companyService.saveCompany(company);
        		promise.then(function(data){
        			if(!handle.isNull(data.data)){
        				$(".modal-backdrop").remove();
	        			toastr.success("保存成功");
	        			handle.unblockUI();
	        			//var company = data.data;
	        			getCompanyInfo(company.comId,"company");
	        			console.log(data.data);
	        			$scope.billView = true;
	        			$scope.billAdd = true;
        			}else{
        				$(".modal-backdrop").remove();
	        			handle.unblockUI();
        				toastr.error("保存失败！请联系管理员");
		            	console.log(data);
        			}
        			
        		},function(data){
        			//调用承诺接口reject();
        			$(".modal-backdrop").remove();
        			handle.unblockUI();
        			toastr.error("保存失败！请联系管理员");
	            	console.log(data);
        		});
	        	
	        }; 
	        /*************开票信息END*****************/
	        
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
	       
	      /* function qualificationValid(){
	    	   var flag = true;
	    	   if($("#qualificationForm input[name='qualificationName']").length==0){
	    		   toastr.warning("没有可保存的资质信息！");
	    		   return false;
	    	   }
	    	   for(var i=0;i<$("#qualificationForm input[name='qualificationName']").length;i++){
	    		   if(isNull($("#qualificationName"+i).val())){
	    			   handle.paramCheck("qualificationName"+i,"资质类型不能为空！");
	    			   flag = false;
	    		   }
	    		   if(isNull($("#qualificationNum"+i).val())){
	    			   handle.paramCheck("qualificationNum"+i,"资质号码不能为空！");
	    			   flag = false;
	    		   }
	    		   if(isNull($("#validityDate"+i).val())){
	    			   handle.paramCheck("validityDate"+i,"有效期不能为空！");
	    			   flag = false;
	    		   }
	    	   }
	    	   
	    	   return flag;
	       }*/
	       
	       /**
	        * 保存企业资质信息
	        */
	       $scope.saveCompanyQualification = function(){
	    	   //$.validator.unobtrusive.parse($("#qualificationForm"));
	    	    //qualificationFormValid();
	    		//validatorInit();
		    	if(handle.isNull($scope.company)||handle.isNull($scope.company.comId)){
		    		 toastr.warning("您的企业信息还未保存！");
		    		 return;
		    	}else if($scope.companyQualifications.length == 0){
		    		 toastr.warning("没有可保存的资质信息！");
		    		 return;
		    	}{
		    		for(var i=0;i<$scope.companyQualifications.length;i++){
		    			$scope.companyQualifications[i].comId = $scope.company.comId;
		    			$scope.companyQualifications[i].createTime = null;
		    			$scope.companyQualifications[i].updateTime = null;
		    		}
		    	}
		    	//var flag = qualificationValid();
		    	if($("#qualificationForm").valid()){
		    		handle.blockUI();
		    	    console.log($scope.companyQualifications);
		        	var promise = companyService.saveCompanyQualification($scope.companyQualifications);
		        	promise.then(function(data){
		        		//$(".modal-backdrop").remove();
		        		if(!handle.isNull(data.data)){
			        		toastr.success("保存成功");
			        		handle.unblockUI();
			        		//$scope.companyQualifications = data.data;
			        		//getCompanyInfo($scope.company.comId,'companyQualification');
			        		getCompanyInfo($scope.company.comId);
			        		$scope.companyQualificationView = true;
			        		$scope.companyQualificationAdd = true;
			        		$scope.companyQualificationEdit = false;
			        		
			        		//getCompanyInfo(company.comId,"company");
		        			//console.log(data.data);
		        			//$scope.companyView = true;
		        			//$scope.companyAdd = true;
		        			//$scope.companyEdit = false;
		        		}else{
		        			toastr.error("保存失败！请联系管理员");
			        		handle.unblockUI();
		        		}
		            },function(data){
		            	handle.unblockUI();
		               //调用承诺接口reject();
		            	toastr.error("保存失败！请联系管理员");
		            	console.log(data);
		            });
		    	}else{
		    		//$('#companyTab a:eq(1)').tab('show');
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
	        			toastr.success("删除成功");
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
	       var  flagContact,flagAddress;
	       $scope.saveCompanyContact = function(){
		    	if((handle.isNull($scope.company)||handle.isNull($scope.company.comId))&&flagContact!=undefined){//handle.isNull($scope.company)||handle.isNull($scope.company.comId)
		    		 toastr.warning("您的企业信息还未保存");
		    		 return;
		    	}else{
		    		if(handle.isNull($scope.companyContact)){
		    			$scope.companyContact = {};
		    		}
		    		if(flagContact==undefined&&($scope.companyContacts==undefined||$scope.companyContacts.length==0)){
		    			flagContact=true;
		    		}else{
		    			flagContact=false;
		    		}
		    		if(!flagContact){
		    			$scope.companyContact.comId = $scope.company.comId;
		    		}
		    		$scope.companyContact.createTime = null;
		    		$scope.companyContact.updateTime = null;
		    	}
		    	if($('#contactForm').valid()){
		    		if(!flagContact||!isNull($scope.company.comId)){
		    			$scope.companyContact.comId = $scope.company.comId;
		    			var promise = companyService.saveCompanyContact($scope.companyContact);
			        	promise.then(function(data){
			        		//$(".modal-backdrop").remove();
			        		if(!handle.isNull(data.data)){
				        		toastr.success("保存成功");
				        		$("#contactor").modal("hide");
				        		$scope.companyContact = {};
				        		$scope.companyContacts = data.data;
			        		}else{
			        			$("#contactor").modal("hide");
			        			toastr.error("保存失败！请联系管理员");
			        		}
			            },function(data){
			               //调用承诺接口reject();
			            	toastr.error("保存失败！请联系管理员");
			            	console.log(data);
			            });
		    		}else{
		    			$scope.companyContact1=$scope.companyContact;
		    			if($scope.companyContacts){
		    				$scope.companyContacts=$scope.companyContacts.push($scope.companyContact);
		    			}else{
		    				$scope.companyContacts=[];
		    				$scope.companyContacts.push($scope.companyContact);
		    			}
		    			 //toastr.warning("第一条联系方式暂存!");
		    			$("#contactor").modal("hide");
		    		}
		        	
		    	}
	    	    
	       }
	       
	       /**
	        * 新增联系人信息
	        */
	       $scope.addCompanyContact = function(){
	    	   $scope.title = null;
	    	   if((handle.isNull($scope.company)||handle.isNull($scope.company.comId))&&flagContact!=undefined){
		    		 toastr.warning("您的企业信息还未保存");
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
	    	   $scope.title = "修改";
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
	    	   if($scope.companyContacts.length==1){
	    		   toastr.warning("请编辑此条信息,至少有一条联系人信息!");
	    		   return;
	    	   }
	    	   handle.confirm("确定删除吗？",function(){
	        		handle.blockUI();
	        		var promise = companyService.deleteCompanyContact(serialNum);
	        		promise.then(function(data){
	        			//handle.showMesssage("success","删除成功","提示");
	        			toastr.success("删除成功");
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
	        * 保存企业管理信息
	        */
	       $scope.saveCompanyManageInfo = function(){
	    	   if((handle.isNull($scope.company)||handle.isNull($scope.company.comId))&&flagAddress!=undefined){
	    		   toastr.warning("您的企业信息还未保存");
	    		   return;
	    	   }else{
	    		   if( $scope.accendants==undefined){
	    			   toastr.warning("至少选择一个维护人员");
		    		   return;
	    		   }
	    	   }
	    	   if(true){
	    		   var userId='';
	    		   for(var i=0;i<$scope.accendants.length;i++){
	    			   userId+=$scope.accendants[i].userId;
	    			   if(i!=$scope.accendants.length-1){
	    				   userId+=',';
	    			   }
	    		   }
	    		   if($scope.companyManage==undefined){
	    			   $scope.companyManage={};
	    		   }
	    		   $scope.companyManage.userId=userId;
	    		   $scope.companyManage.comId=$scope.company.comId;
	    		   $scope.companyManage.users=null;
	    			   var promise = companyService.saveCompanyManage($scope.companyManage);
		    		   promise.then(function(data){
		    			   //$(".modal-backdrop").remove();
		    			   if(data.data.flag=='1'){
		    				   toastr.success("保存成功");
		    				   $scope.companyManage= data.data.companyManage;
		    				  $scope.companyMView=true;
		    				   $scope.companyMAdd=true
		    				   
		    			   }else{
		    				   toastr.error("保存失败！请联系管理员");
		    			   }
		    		   },function(data){
		    			   //调用承诺接口reject();
		    			   toastr.error("保存失败！请联系管理员");
		    			   console.log(data);
		    		   });
	    		   
	    	   }
	       }
	       
	       $scope.editCompanyManageInfo=function(){
	    	   $scope.companyMView=false;
			   $scope.companyMAdd=false;
			   
	       }
	       /***************************************************联系地址START*************************************************
	       /**
	        * 保存联系地址信息
	        */
	       $scope.saveCompanyAddress = function(){
	    	   if((handle.isNull($scope.company)||handle.isNull($scope.company.comId))&&flagAddress!=undefined){
	    		   toastr.warning("您的企业信息还未保存");
	    		   return;
	    	   }else{
	    		   if(handle.isNull($scope.companyAddress)){
	    			   $scope.companyAddress = {};
	    		   }
	    		   if(flagAddress==undefined&&($scope.companyAddresses==undefined||$scope.companyAddresses.length==0)){
	    			   flagAddress=true;
	    		   }else{
	    			   flagAddress=false;
	    		   }
	    		   if(!flagAddress){
	    			   $scope.companyAddress.comId = $scope.company.comId;
	    		   }
	    		   $scope.companyAddress.createTime = null;
	    		   $scope.companyAddress.updateTime = null;
	    	   }
	    	   if($('#companyAddressForm').valid()){
	    		   if(!flagAddress||!isNull( $scope.company.comId)){
	    			   $scope.companyAddress.comId = $scope.company.comId;
	    			   var promise = companyService.saveCompanyAddress($scope.companyAddress);
		    		   promise.then(function(data){
		    			   //$(".modal-backdrop").remove();
		    			   if(!handle.isNull(data.data)){
		    				   toastr.success("保存成功");
		    				   $("#address").modal("hide");
		    				   $scope.companyAddress = {};
		    				   $scope.companyAddresses = data.data;
		    				   
		    			   }else{
		    				   $("#address").modal("hide");
		    				   toastr.error("保存失败！请联系管理员");
		    			   }
		    		   },function(data){
		    			   //调用承诺接口reject();
		    			   toastr.error("保存失败！请联系管理员");
		    			   console.log(data);
		    		   });
	    		   }else{
	    			   $scope.companyAddress1= $scope.companyAddress; 
	    			   if($scope.companyAddresses){
		    				$scope.companyAddresses=$scope.companyAddresses.push($scope.companyAddress);
		    			}else{
		    				$scope.companyAddresses=[];
		    				$scope.companyAddresses.push($scope.companyAddress);
		    			}
	    			   /*toastr.warning("第一条联系地址暂存!");*/
	    			   $("#address").modal("hide");
	    		   }
	    		   
	    	   }
	    	   
	       }
	       
	       /**
	        * 新增联系地址信息
	        */
	       $scope.addCompanyAddress = function(){
	    	   $scope.title = null;
	    	   if((handle.isNull($scope.company)||handle.isNull($scope.company.comId))&&flagAddress!=undefined){
	    		   toastr.warning("您的企业信息还未保存");
	    		   return;
	    	   }else{
	    		   $scope.companyAddress = {};
	    		   $("#address").modal("show");
	    	   }
	    	   
	       }
	       
	       
	       /**
	        * 编辑联系地址信息
	        */
	       $scope.editCompanyAddress = function(serialNum){
	    	   $scope.title = "修改";
	    	   $("#address").modal("show");
	    	   for(var i=0;i<$scope.companyAddresses.length;i++){
	    		   if($scope.companyAddresses[i].serialNum==serialNum){
	    			   $scope.companyAddress = $scope.companyAddresses[i];
	    			   break;
	    		   }
	    	   }
	    	   
	       }
	       
	       /**
	        * 删除联系地址信息
	        */
	       $scope.deleteCompanyAddress = function(serialNum){
	    	   if($scope.companyAddresses.length==1){
	    		   toastr.warning("请编辑此条信息,至少有一条联系地址!");
	    		   return;
	    	   }
	    	   handle.confirm("确定删除吗？",function(){
	    		   handle.blockUI();
	    		   var promise = companyService.deleteCompanyAddress(serialNum);
	    		   promise.then(function(data){
	    			   //handle.showMesssage("success","删除成功","提示");
	    			   toastr.success("删除成功");
	    			   handle.unblockUI();
	    			   for(var i=0;i<$scope.companyAddresses.length;i++){
	    				   if($scope.companyAddresses[i].serialNum==serialNum){
	    					   $scope.companyAddresses.splice(i,1);
	    					   break;
	    				   }
	    			   }
	    			   // $state.go('company',{},{reload:true}); 
	    		   },function(data){
	    			   //调用承诺接口reject();
	    		   });
	    		   
	    	   });
	       }
	       
	       
	       /***************************************************联系地址END*************************************************
	       
	       
	       /**
	        * 保存财务信息
	        */
	       $scope.saveCompanyFinance = function(){
	    		if(handle.isNull($scope.company)||handle.isNull($scope.company.comId)){
		    		 toastr.warning("您的企业信息还未保存");
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
		        			toastr.success("保存成功");
			        		$("#finance").modal("hide");
			        		$scope.companyFinance = {};
			        		$scope.companyFinances = data.data;
		        		}else{
		        			$("#finance").modal("hide");
		        			toastr.error("保存失败！请联系管理员");
		        			console.log(data);
		        		}
		        		
		            },function(data){
		               //调用承诺接口reject();
		            	toastr.error("保存失败！请联系管理员");
		            	console.log(data);
		            });
	    		}
	       }
	       
	       
	       /**
	        * 新增财务信息
	        */
	       $scope.addCompanyFinance = function(){
	    	   $scope.title = null;
	    	   if(handle.isNull($scope.company)||handle.isNull($scope.company.comId)){
		    		 toastr.warning("您的企业信息还未保存");
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
	    	   $scope.title = "修改";
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
	        			toastr.success("删除成功");
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
		    		 toastr.warning("您的企业信息还未保存");
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
	       $scope.repeatDone = function(judgeString){
	    	   if(judgeString==undefined){
	    		   handle.datePickersInit("bottom");
	    	   }else if(judgeString=='buy'){
	    		   $('select[name="buyComId"]').selectpicker({
	                    showSubtext: true,
	                    size : 5
	                });
	    			$('select[name="buyComId"]').selectpicker('refresh');//刷新插件
	    	   }else if(judgeString=='supply'){
	    		   $('select[name="supplyComId"]').selectpicker({
	                    showSubtext: true,
	                    size : 5
	                });
	    			$('select[name="supplyComId"]').selectpicker('refresh');//刷新插件
	    	   }
	    	   
	    	   //$("#qualificationForm").removeData("validator").removeData("unobtrusiveValidation");
	    	 
	    	   //$.validator.parse($("#qualificationForm"));
	    	   //qualificationFormValid();
	    	  // validatorInit();
	    	  
	       };
	       
	       /**
	        * 显示企业信息
	        */
	       $scope.showCompanyInfo = function(comId){
	    	   var obj = $("#"+comId);
	    	   if(obj.data("check")=="true"){//取消事件
	    		   obj.data("check","false");
	    		   if($scope.companyInfo.length>0){
	    			   for(var i=0;i<$scope.companyInfo.length;i++){
	    				   if($scope.companyInfo[i].comId==comId){
	    					   $scope.companyInfo.splice(i,1);
	    					   break;
	    				   }
	    			   }
	    		   }
	    		   if($scope.companyInfo.length>0){
		    		   $scope.companyQualifications=[];
		    		   $scope.companyQualifications = $scope.companyInfo[$scope.companyInfo.length-1].companyQualifications;
		    		   $scope.companyContacts = $scope.companyInfo[$scope.companyInfo.length-1].companyContacts;
			    	   $scope.companyFinances = $scope.companyInfo[$scope.companyInfo.length-1].companyFinances;
			    	   $scope.company = $scope.companyInfo[$scope.companyInfo.length-1].company;
			    	   $scope.buyComs=$scope.companyInfo[$scope.companyInfo.length-1].buyComs;
		    		   $scope.supplies=$scope.companyInfo[$scope.companyInfo.length-1].supplies;
		    		   $scope.comManagers=$scope.companyInfo[$scope.companyInfo.length-1].comManagers;
		    	   }else{
		    		   $scope.companyQualifications = [];
		    		   $scope.companyContacts = [];
		    		   $scope.companyFinances = [];
		    		   $scope.buyComs=[];
		    		   $scope.supplies=[];
		    		   $scope.comManagers=[];
		    		   $scope.company = {};
		    	   }
	    	   }else{//选中事件
	    		   obj.data("check","true");
	    		   getCompanyInfo(comId);
	    	   }
	    	 /*  if($scope.companyInfo.length>0){
	    		   $scope.companyQualifications=[];
	    		   $scope.companyQualifications = $scope.companyInfo[$scope.companyInfo.length-1].companyQualifications;
	    		   $scope.companyContacts = $scope.companyInfo[$scope.companyInfo.length-1].companyContacts;
		    	   $scope.companyFinances = $scope.companyInfo[$scope.companyInfo.length-1].companyFinances;
		    	   $scope.c_company = $scope.companyInfo[$scope.companyInfo.length-1].company;
	    	   }else{
	    		   $scope.companyQualifications = [];
	    		   $scope.companyContacts = [];
	    		   $scope.companyFinances = [];
	    		   $scope.c_company = {};
	    	   }*/
	    	  
        		
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
	    	   }else if(type=="address"){
	    		   call =  "operation_a"+index;
	    	   }else if(type=="_buyCompany"){
	    		   call =  "operation_b"+index;
	    	   }else if(type=="_supplyCompany"){
	    		   call =  "operation_s"+index;
	    	   }
	    	   $scope[call] = true;
	    	  // $scope.$apply();
	       };
	       
	       /**
	        * 隐藏编辑、删除操作
	        */
	       $scope.hideOperation = function(type,index){
	    	   var call = "operation_c"+index;
	    	   if(type=='finance'){
	    		   call =  "operation_f"+index;
	    	   }else if(type=="address"){
	    		   call =  "operation_a"+index;
	    	   }else if(type=="_buyCompany"){
	    		   call =  "operation_b"+index;
	    	   }else if(type=="_supplyCompany"){
	    		   call =  "operation_s"+index;
	    	   }
	    	   $scope[call]= false;
	    	   //$scope.$apply();
	       };
	       
	       
	       function getCompanyInfo(comId,type){
	    	   if(!handle.isNull(comId)){
	    			 var promise = companyService.getCompanyInfo(comId);
	 	        	promise.then(function(data){
	 	        		$scope.companyQualifications = [];
	 	        		if(type=="companyQualification"){
	 	        			if(!handle.isNull(data.data.companyQualifications)){
		 	        			$scope.companyQualifications = data.data.companyQualifications;
		 	        			_index = data.data.companyQualifications.length-1;
		 	        		}
	 	        		}else if(type=="company"){
	 	        			$scope.company = data.data.company;
	 	        			if(isNull(data.data.company.corporatePresence)&&!isNull(data.data.company.comName)){
		        				$scope.company.corporatePresence = data.data.company.comName;
		        			}
	 	        		}else{
	 	        			$scope.company = data.data.company;
		 	        		if(!handle.isNull(data.data.companyQualifications)){
		 	        			$scope.companyQualifications = data.data.companyQualifications;
		 	        			_index = data.data.companyQualifications.length-1;
		 	        		}
		 	        		debugger;
		 	        		$scope.companyContacts = data.data.companyContacts;
		 	        		$scope.companyFinances = data.data.companyFinances;
		 	        		$scope.companyAddresses = data.data.companyAddresses;
		 	        		$scope.comManagers = data.data.comManagers;
		 	        		if(data.data.companyManage!=null){
		 	        			$scope.companyManage = data.data.companyManage;
			 	        		$scope.accendants=$scope.companyManage.users;
			 	        		$scope.users=$scope.companyManage.users;
		 	        		}
		 	        		if(!isNull(data.data.supplies)){
		 	        			$scope.supplies = data.data.supplies;
		 	        			supplyIndex = $scope.supplies.length;
		 	        		}
		 	        		if(!isNull(data.data.buyComs)){
		 	        			$scope.buyComs = data.data.buyComs;
		 	        			buyIndex = $scope.buyComs.length;
		 	        		}
		 	        		/*initCustomers();
		 		    		initSuppliers();*/
		 	        		data.data.comId = comId; //将企业id也放入数组，一边取消操作
		 	        		if($scope.companyInfo!=undefined){
		 	        			$scope.companyInfo.push(data.data); //将返回信息添加至checkbox选中数组
		 	        		}
		 	        		
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
	    	   if(!handle.isNull($scope.company)&&!handle.isNull($scope.company.comId)){
	    		   getCompanyInfo($scope.company.comId);
	    	   }
	       })
	       $('#finance').on('hide.bs.modal', function (e) {
	    	   if(!handle.isNull($scope.company)&&!handle.isNull($scope.company.comId)){
	    		   getCompanyInfo($scope.company.comId);
	    	   }
	       })
	       $('#address').on('hide.bs.modal', function (e) {
	    	   if(!handle.isNull($scope.company)&&!handle.isNull($scope.company.comId)){
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
	       
	       
	       $scope.downloadFile = function(obj){
	    	   if(!handle.isNull(obj)){
	    		   
	    		   var url = $rootScope.basePath+"/rest/fileOperate/downloadFile?fileName="+encodeURI(encodeURI(obj.qualificatioImage));
	    		   if(isImg(obj.qualificatioImage)){//图片类型
	    			   showImg(url); 
	    		   }else{//其他类型
	    			   window.location.href = url;
	    		   }
	    		  
	    	   }else{
	    		   toastr.error("下载失败!");
	    	   }
	       }
	       

	       
	       
	       $scope.saveData = function(){
	    	   $scope.saveCompany();
	       }
	       
	       $scope.editData = function(){
	    	   
	       }
	       
	       $scope.cancelData = function(){
	    	   
	       }
	       

	       $('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
	        // 获取已激活的标签页的名称
	        var activeTab = $(e.target).text(); 
	        // 获取前一个激活的标签页的名称
	       // var previousTab = $(e.relatedTarget).text(); 
	        var absurl = $location.absUrl();debugger;
	        if(activeTab=="企业信息"){
	        	$scope.basicInfo = false;
	        	$scope.qualificationInfo = false;
	        	$scope.billInfo = false;
	        	$scope.manage = false;
	        	$scope.comRelation = false;
	        	$scope.$apply();
	        }else if(activeTab=="资质信息"){
	        	$scope.basicInfo = true;
	        	$scope.qualificationInfo = true;
	        	$scope.billInfo = false;
	        	$scope.manage = false;
	        	$scope.comRelation = false;
	        	$scope.$apply();
	        }else if(activeTab=="财务信息"){
	        	$scope.basicInfo = true;
	        	$scope.qualificationInfo = false;
	        	$scope.billInfo = true;
	        	$scope.manage = false;
	        	$scope.comRelation = false;
	        	$scope.$apply();
	        }else if(activeTab=="管理信息"){
	        	$scope.basicInfo = true;
	        	$scope.qualificationInfo = false;
	        	$scope.billInfo = false;
	        	$scope.manage = true;
	        	$scope.comRelation = false;
	        	$scope.$apply();
	        }else if(activeTab=="采购商"||activeTab=="供应商"){
	        	$scope.basicInfo = true;
	        	$scope.qualificationInfo = false;
	        	$scope.billInfo = false;
	        	$scope.manage = false;
	        	$scope.comRelation = true;
	        	$scope.$apply();
	        }else{
	        	$scope.basicInfo = true;
	        	$scope.qualificationInfo = false;
	        	$scope.billInfo = false;
	        	$scope.manage = false;
	        	$scope.comRelation = false;
	        	$scope.$apply();
	        }
	     });
	       
	     $scope.checkedOrCancelAll = function() { 
	    	 debugger;
	    	   if($scope.allChecked){
	    		   if(!isNull($scope.serialNums)){
			    		for(var i in $scope.serialNums){
			    			$scope.serialNums[i] = true;
			    			console.log("===++++++=====>"+$scope.serialNums);
			    		}
			       }
	    	   }else{
	    		   if(!isNull($scope.serialNums)){
			    		for(var i in $scope.serialNums){
			    			$scope.serialNums[i] = false;
			    			console.log("===++++++=====>"+$scope.serialNums);
			    		}
			       }
	    	   }
	    	   //debugger;
	    	   //console.log("==============>"+$scope.materielAllChecked);
	    };
	       
	    $scope.addAccendant = function (){//添加维护人员
	    	selectAccendant();
    		$("#basicUserInfo").modal("show");
		}
      /** *************维护人员操作 start*************** */
        var tableUser;
        var selectAccendant = function() {
        	if(tableUser!=undefined){
        		tableUser.destroy();
        	}
                 a = 0;
                 App.getViewPort().width < App.getResponsiveBreakpoint("md") ? $(".page-header").hasClass("page-header-fixed-mobile") && (a = $(".page-header").outerHeight(!0)) : $(".page-header").hasClass("navbar-fixed-top") ? a = $(".page-header").outerHeight(!0) : $("body").hasClass("page-header-fixed") && (a = 64);
                 tableUser = $("#select_sample_2").DataTable({
                     language: {
                         aria: {
                             sortAscending: ": activate to sort column ascending",
                             sortDescending: ": activate to sort column descending"
                         },
                         emptyTable: "空表",
                         info: "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
                         infoEmpty: "没有数据",
                         // infoFiltered: "(filtered1 from _MAX_ total
							// entries)",
                         lengthMenu: "每页显示 _MENU_ 条数据",
                         search: "查询:",processing:"加载中...",
                         zeroRecords: "抱歉， 没有找到！",
                         paginate: {
                             "sFirst": "首页",
                             "sPrevious": "前一页",
                             "sNext": "后一页",
                             "sLast": "尾页"
                          }
                     },
     /*
		 * fixedHeader: {//固定表头、表底 header: !0, footer: !0, headerOffset: a },
		 */
                     order: [[1, "desc"]],// 默认排序列及排序方式
                     searching: true,// 是否过滤检索
                     ordering:  true,// 是否排序
                     lengthMenu: [[5, 10, 15, 30, -1], [5, 10, 15, 30, "All"]],
                     pageLength: 5,// 每页显示数量
                     processing: true,// loading等待框
// serverSide: true,
                     ajax: "rest/company/findUserList",//查找所有用户
                     "aoColumns": [
                                   { mData: 'userId' },
                                   { mData: 'userName' },
                                   { mData: 'sex' },
                                   { mData: 'department' },
                                   { mData: 'position' }
                             ],
                    'aoColumnDefs' : [ {
     							'targets' : 0,
     							'searchable' : false,
     							'orderable' : false,
     							
     							'render' : function(data,
     									type, row, meta) {
     	  								return "<label class='mt-checkbox mt-checkbox-single mt-checkbox-outline'>" +
     									"<input type='checkbox' class='checkboxes' data-checked=false  id='"+ row.userId +"' ng-click='getCheckedIds(\""+data+"\",\""+row.userName+"\")' name='user_serial' value="+ row.userId +" />" +
     									"<span></span></label>";

     							},
     							"createdCell": function (td, cellData, rowData, row, col) {
     								 $compile(td)($scope);
     						       }
     						}/*,{
     							'targets' : 1,
     							'render' : function(data,
     									type, row, meta) {
     								var ClauseFrameworkIcon='';// ClauseFramework图标
     								if(row.isCSD==1){
     									ClauseFrameworkIcon = '<span class="label label-sm label-success">B</span> '
     								}
     								return ClauseFrameworkIcon + data;
     							}

     						}*/],	//stateSave:false,
							"fnInitComplete":function(settings) {//fnInitComplete stateLoadCallback
			                	   showDetail();
			                   }

                 }).on('order.dt',
                         function() {
                     console.log('排序');
                 }).on('page.dt', 
                 function () {
               	  console.log('翻页');
   	          }).on('draw.dt',function() {
   	        	  checkedIdHandler();
   	          });
                 //全选操作
                 $("#select_sample_2").find(".group-checkable").change(function() {
      	            var e = jQuery(this).attr("data-set"),
      	            t = jQuery(this).is(":checked");
      	            jQuery(e).each(function() {
      	                t ? ($(this).prop("checked", !0), $(this).parents("tr").addClass("active")) : ($(this).prop("checked", !1), $(this).parents("tr").removeClass("active"))
      	            })
      	        }),
      	        $("#select_sample_2").on("change", "tbody tr .checkboxes",
      	        function() {
      	            $(this).parents("tr").toggleClass("active")
      	        })
             };
             /**
       		 * 遍历checkbox,检查并处理已取消的元素
       		 */
       		function checkedIdHandler(){
       			//获取选中用户ID
       			tableUser.$('input[name="user_serial"]').each(function() { //遍历当前页的物料信息
       					if ($.contains(document, this)) {
       						if (this.checked) {
       							if($scope.users.length>0){
       								var flag = false;
       								for(var i=0;i<$scope.users.length;i++){
       									if($scope.users[i].userId == $(this).attr("id")){
       										flag=true;
       										break;
       									}
       									if(i==$scope.users.length-1&& flag==false){//不在选中数组内，checkbox清除选中状态
       										$(this).attr("checked",false);
       										$(this).data("checked",false);
       									}
       								}
       							}else if($scope.users.length==0){//没有被选中的物料
       								$(this).attr("checked",false);
       								$(this).data("checked",false);
       							}
       						}
       					}
       			});
       		}
            /**
      		 * checkbox点击事件（生成选中内容）
      		 */
      		$scope.getCheckedIds = function(userId,userName){
      			var data={};
      			data.userId = userId;
      			data.userName = userName; //获取一行数据
      			if($scope.users==undefined&&$scope.accendants==undefined	){
      				$scope.users=[];
      			}else{
      				if($scope.accendants!=undefined){
      					$scope.users=$scope.accendants;
      				}
      			for(var i=0;i<$scope.users.length;i++){
  					if($scope.users[i].userId==userId){
  						$scope.users.splice(i,1);
  						$("#"+userId).attr("checked",false);
  						$("#"+userId).data("checked",false);
  						return;
  					}
      			}
      		}
      			if($("#"+userId).data("checked")||$("#"+userId).data("checked")==undefined){
      				for(var i=0;i<$scope.users.length;i++){
      					if($scope.users[i].userId==userId){
      						$scope.users.splice(i,1);
      						$("#"+userId).attr("checked",false);
      						$("#"+userId).data("checked",false);
      						break;
      					}
      				}
      			}else{
      				$scope.users.push(data);
      				$("#"+userId).data("checked",true);
      				$("#"+userId).attr("checked",true);
      			}
      			
      		}
      		$scope.confirmSelect=function(){
      			if($scope.users==undefined){
      				 toastr.warning("请至少选择一个用户");
		    		 return;
      			}
      			$scope.accendants=$scope.users;
      			$("#basicUserInfo").modal("hide");
      		}
      		 /**
	 		 * 遍历checkbox将之前保存的维护人员情况展示出来
	 		 */
	 		function showDetail(){
	 			//m_table.$('input[type="checkbox"]')
	 			tableUser.$('input[type="checkbox"]').each(function() { //遍历所有checkbox
	 				
	 					if ($.contains(document, this)) {
	 						 var users=$scope.accendants;
	 						 if(users!=undefined){
							 for(var i=0;i<users.length;i++){
								  var userId=users[i].userId;
								 if($(this).attr("id")==userId){
									$("#"+userId).attr("checked",true);
								 }
							 }
	 						 }
	 					}
	 			});
	 		}
	 	   $scope.cancelComRelation= function() {//取消编辑采购商/供应商信息
	   	    	$scope.CompanyInfoInput = true;
	   		    $scope.CompanyInfoShow = true;
	   	    };
	   	    
	   	    $scope.editCompanyRelationInfo= function() {//进入编辑采购商/供应商信息
	   	    	$scope.CompanyInfoInput = false;
	   		    $scope.CompanyInfoShow = false;
	   	    };
	   	    /**
	 	        * 采购商/供应商新增一行
	 	        */
	   	    $scope.addComRelation= function(judgeString){
	   	    	debugger;
	   	    	if($scope.company.comId==null||$scope.company.comId=='') {
	   	    		toastr.error('请先保存基本信息！');return
	   			}else if(judgeString=='supply'){//supplies
	   		    	   if($scope.buyComs){}else{$scope.buyComs =[{}]}
	   		    	   $scope.buyComs[buyIndex] = {};
	   		    	   $scope.buyComs[buyIndex].creator=$scope.company.comId;
	   		    	buyIndex++;
	   		    	$scope.buyComs=angular.copy($scope.buyComs);
	   		    	//initSuppliers('notnull');
	   		       }else if(judgeString=='buy'){//supplies
	   		    	   if($scope.supplies){}else{$scope.supplies =[{}]}
	   		    	   $scope.supplies[supplyIndex] = {};
	   		    	   $scope.supplies[supplyIndex].creator=$scope.company.comId;
	   		    	supplyIndex++;
	   		 	 // initCustomers('notnull');
	   		       }
	   	    };
	   	    
	   	 /**
		   * 采购商/供应商删除一行
		        */
		       $scope.deleteComRelation = function(judgeString,index){
		    	   if(judgeString=='supply'){
	   		    	 $scope.buyComs.splice(index,1);
	   		    	buyIndex--;
	   		       }else if(judgeString=='buy'){
	   		     $scope.supplies.splice(index,1);
	   		      supplyIndex--;
	   		       }
		       };
		       
	   	    var  buyIndex =0,supplyIndex=0;
	   	 $scope.saveCompanyRelationInfo = function(){
	   		if($scope.company.comId==null||$scope.company.comId=='') {//企业信息为空时
   	    		toastr.error('请先保存基本信息！');return
   			}
   	    	if($scope.company.comType==1){
   	    		if(isNull($scope.buyComs)){
   	    		toastr.error('至少有一条供应商企业信息');return
   	    		}else{
   	    			for(var i=0;i<$scope.buyComs.length;i++){
   	    				if(isNull($scope.buyComs[i].comId)){
   	    					toastr.error('供应商名称不能为空');return
   	    				}
   	    			}
   	    			$scope.companyRelation=$scope.buyComs;
   	    		}
   	    	}
   	    	if($scope.company.comType==2){
   	    		if(isNull($scope.supplies)){
   	   	    		toastr.error('至少有一条采购商企业信息');return
   	   	    		}else{
   	   	    		for(var i=0;i<$scope.supplies.length;i++){
   	    				if(isNull($scope.supplies[i].comId)){
   	    					toastr.error('采购商名称不能为空');return
   	    				}
   	    		}
   	   	    	$scope.companyRelation=$scope.supplies;
   	   	    }
   	 }
   	    	for(var i=0;i<$scope.companyRelation.length;i++){
   	    		$scope.companyRelation[i].creator=$scope.company.comId;//creator为被绑企业id
   	    	}
   	    	companyService.saveCompanyRelation($scope.companyRelation).then(
   	       		     function(data){
   	       		    	toastr.success('数据保存成功！');
   	       		  $scope.cancelComRelation();
   	       		     },
   	       		     function(error){
   	       		    	toastr.error('数据保存出错！');
   	       		         $scope.error = error;
   	       		     }
   	       		 );
	   	 };
	   	 $scope.fuzhi=function(judgeString,index){
	   		 var count=0;
	   		 if(judgeString=="buy"){
	   			 var comId=$scope.buyComs[index].comId;////选中的comid
	   			 for(var i=0;i<$scope.buyComs.length;i++){
	   				 if(comId==$scope.buyComs[i].comId){
	   					count++;
	   				 }
	   			 }
	   			 if(count>1){
	   			toastr.error('该企业已存在,重新选择！');
					$scope.buyComs[index].comId='';
					return;
	   			 }
	   			 for(var i=0;i<$scope.supplyCompanys.length;i++){
	   				 if(comId==$scope.supplyCompanys[i].comId){
	   					$scope.buyComs[index].comNum=$scope.supplyCompanys[i].comNum;
	   					$scope.buyComs[index].comName=$scope.supplyCompanys[i].comName;
	   					return;
	   				 }
	   			 }
	   		 }else{
	   			var comId=$scope.supplies[index].comId;
	   			for(var i=0;i<$scope.supplies.length;i++){
	   				 if(comId==$scope.supplies[i].comId){
	   					count++;
	   				 }
	   			 }
	   		 if(count>1){
		   			toastr.error('该企业已存在,重新选择！');
						$scope.supplies[index].comId='';
						return;
		   			 }
	   			for(var i=0;i<$scope.buyCompanys.length;i++){
	   				 if(comId==$scope.buyCompanys[i].comId){
	   					$scope.supplies[index].comNum=$scope.buyCompanys[i].comNum;
	   					$scope.supplies[index].comName=$scope.supplies[i].comName;
	   					return;
	   				 }
	   			 }
	   		 }
	   		 
	   	 }
	   	 
}]); 
