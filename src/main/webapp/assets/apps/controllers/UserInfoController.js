angular.module('MetronicApp').controller('UserInfoController', ['$rootScope','$scope','$http', 'settings', '$q','UserInfoService','CompanyInfoService','AccountSecurityService','$state','$compile','$stateParams','$filter','FileUploader', 
                                                                function($rootScope,$scope,$http,settings, $q,UserInfoService,CompanyInfoService,AccountSecurityService,$state,$compile,$stateParams,$filter,FileUploader) {
	$scope.$on('$viewContentLoaded', function() {   
		// initialize core components
		handle = new pageHandle();
		App.initAjax();

		// set default layout mode
		$rootScope.settings.layout.pageContentWhite = true;
		$rootScope.settings.layout.pageBodySolid = false;
		$rootScope.settings.layout.pageSidebarClosed = false;
		$scope.input=false;
		$scope.span=true;
		
		//查询登陆者的信息
    	$scope.getUserInfo();
    	//获取公告与消息数
    	$scope.getMessageAndNotice();
    	//操作日志加载部分end
    	 handle.datePickersInit("auto bottom");
         $scope.resetSearchForm();
         loadMainTable();// 加载采购商对账单列表
       //操作日志加载部分end
         //企业信息start
         $scope.companyAdd=false;
 		$scope.companyView=true;
 		//查询登陆者的信息
     	$scope.getCompanyInfo();
     	//企业信息end
     	//账户安全
     	var self = $rootScope;
		self.changeEmail = {
			email: '',
			password: ''
		};
		
		self.changePhone = {
				phone: '',
				password: ''
		};
		//查询登陆者的信息
    	/*$scope.getUserInfo();	*/
    	//账户安全
	});
	
	//根据参数查询对象
	$scope.getUserInfo  = function() {
		UserInfoService.getUserInfo().then(
      		     function(data){
      		    	$scope.userInfo=data;
      		     },
      		     function(error){
      		         toastr.error('连接服务器出错,请登录重试！');
      		     }
      		 );
    }; 
    
    
  //根据参数查询对象
	var getUserInfoA  = function() {
		UserInfoService.getUserInfo().then(
      		     function(data){
      		    	$scope.userInfo=data;
      		    	/*$("#avatar").attr("src","uploadAttachFiles/"+$scope.userInfo.avatar);*/
      		    	var rootPath=getWSPath_web1();
      		    	$("#avatar").attr("src",rootPath+"rest/fileOperate/downloadFile?fileName="+data.avatar);
      		    	$("#usernameOfUserInfo").html($scope.userInfo.displayName);
      		     },
      		     function(error){
      		         toastr.error('连接服务器出错,请登录重试！');
      		     }
      		 );
    }; 
	
	
	//修改
	$scope.editUserInfo = function() {
		$scope.input=true;
		$scope.span=false;
		$scope.userInfoEdit=true;
		$scope.userInfoInfoSave=true;
	};
	
	//添加合同
	$scope.updateUserInfo = function() {
		
		if($('#form_sample_userInfo').valid()){//表单验证通过则执行添加功能
		var fd = new FormData();
		if($("input[type='file']").length){
        var file = document.querySelector('input[type="file"]').files[0];
        fd.append("file", file);
		}
		
		fd.append('userId', $scope.userInfo.userId); 
		fd.append('displayName',$scope.userInfo.displayName); 
		if($("input[name='sex']:checked").val())fd.append('sex',$("input[name='sex']:checked").val()); 
        if($scope.userInfo.telephone)fd.append('telephone',$scope.userInfo.telephone); 
        if($scope.userInfo.qqnum)fd.append('QQNum',$scope.userInfo.qqnum); 
        if($scope.userInfo.fax)fd.append('fax',$scope.userInfo.fax); 
        fd.append('cellPhone',$scope.userInfo.cellPhone); 
        fd.append('email',$scope.userInfo.email); 
         $http({
        	  method:'POST',
              url:"rest/user/updateUserInfo",
              data: fd,
              headers: {'Content-Type':undefined}
               })   
              .success( function ( response )
                       {
            	  toastr.success("修改成功！");
   		    	  $scope.input=false;
   				  $scope.span=true;
   				 $scope.userInfoEdit=false;
   				 $scope.userInfoInfoSave=false;
   				  getUserInfoA();
   				
   				
   				/*$("#avatar").attr("src","assets/pages/media/users/avatar3.jpg");*/
                       });
		}
	};
	
	//返回按钮
	$scope.goback=function(){
		$state.go('userContract');
	}
	
	//打印
	$scope.print=function(){
		window.print();  
	}
	
		//单个删除
		$scope.jumpToDel = function(value) {
			var ids=value;	
			if (ids == '') {// 未勾选删除数据									
				toastr.warning("未勾选要删除数据！");
			} else {
				Jquery('#delUsersModal').modal('show');// 打开确认删除模态框
				
				$scope.confirmDel = function() {										
					ContractService.delUsers(ids).then(
									function(data) {
										
										$('#delUsersModal').modal('hide');// 删除成功后关闭模态框
										toastr.success("删除成功！");
										var table = $('#sample_2').DataTable();
										/*table.ajax.reload();*/
										$("#sample_2").dataTable().fnDraw(false)
									},
									function(errResponse) {
										/*console.error('Error while deleting Users');*/
									}

							);
				}
			}
		};
		
		$scope.getOderInfo=function(serialNum){
			/*alert(id);*/
			ContractService.getOderInfo(serialNum).then(
         		     function(data){
         		    	$scope.orderInfo=data;
         		    	$scope.orderInfo1=null;
         		     },
         		     function(error){
         		         $scope.error = error;
         		     }
         		 );
		}
		
		$scope.getOderInfo1=function(serialNum){
			/*alert(id);*/
			ContractService.getOderInfo(serialNum).then(
         		     function(data){
         		    	$scope.orderInfo1=data;
         		    	$scope.orderInfo=null;
         		     },
         		     function(error){
         		         $scope.error = error;
         		     }
         		 );
		}
	        
			//格式化日期
			function timeStamp2String(time){
	        var datetime = new Date();
	         datetime.setTime(time);
	         var year = datetime.getFullYear();
	         var month = datetime.getMonth() + 1;
	         var date = datetime.getDate();
	         return month+"/"+date+"/"+year ;
	        };
			
			
        jQuery.validator.addMethod("isTel", function(value, element) {
            var length = value.length;
            var phone = /(^(\d{3,4}-)?\d{6,8}$)|(^(\d{3,4}-)?\d{6,8}(-\d{1,5})?$)|(\d{11})/;
            return this.optional(element) || (phone.test(value));
           }, "请填写正确的固定电话");//可以自定义默认提示信息
        
        jQuery.validator.addMethod("cellPhone", function(value, element) {
            var length = value.length;
            var phone = /^1[123456789]\d{9}/;
            return this.optional(element) || (phone.test(value));
           }, "请填写正确的手机号码");//可以自定义默认提示信息
        
        
        jQuery.validator.addMethod("isQq", function(value, element) {  
        	var qq=/^[1-9]\d{4,12}$/;
            return this.optional(element) ||(qq.test(value));       
       }, "匹配QQ"); 
	
	// 页面加载完成后调用，验证输入框
	$scope.$watch('$viewContentLoaded', function() {  
		var e = $("#form_sample_userInfo"),
        r = $(".alert-danger", e),
        i = $(".alert-success", e);
        e.validate({
            errorElement: "span",
            errorClass: "help-block help-block-error",
            focusInvalid: !1,
            ignore: "",
            messages: {
            	userName:{required:"用户名不能为空！",},
            	telephone:{isTel:"请填写正确的固定电话！",},
            	qqNum:{isQq:"请填写正确的QQ号码！",},
            	fax:{isTel:"请填写正确的传真号码！",},
            	cellPhone:{required:"手机不能为空！",cellPhone:"请填写正确的手机号码！",},
            	email:{required:"邮箱不能为空！",email:"请填写正确的邮箱地址！",},
                payment: {
                    maxlength: jQuery.validator.format("Max {0} items allowed for selection"),
                    minlength: jQuery.validator.format("At least {0} items must be selected")
                },
                "checkboxes1[]": {
                    required: "Please check some options",
                    minlength: jQuery.validator.format("At least {0} items must be selected")
                },
                "checkboxes2[]": {
                    required: "Please check some options",
                    minlength: jQuery.validator.format("At least {0} items must be selected")
                }
            },
            rules: {
                userName:{required:true},
                telephone:{isTel:true},
                qqNum:{isQq:true},
                fax:{isTel:true},
                cellPhone:{required:true,cellPhone:true},
                email:{required:true,email:true}
                
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
	
	
	//获取公告与消息数
	$scope.getMessageAndNotice = function() {
		UserInfoService.getMessageCount().then(
    		     function(data){
    		    	$scope.messageCount=data;
    		     },
    		     function(error){
    		         $scope.error = error;
    		     }
    	);
		
		UserInfoService.getNoticeCount().then(
   		     function(data){
   		    	$scope.noticeCount=data;
   		     },
   		     function(error){
   		         $scope.error = error;
   		     }
   	);
	}
	//操作日志
	 var table;
	    var loadMainTable = function() {
	            a = 0;
	            App.getViewPort().width < App.getResponsiveBreakpoint("md") ? $(".page-header").hasClass("page-header-fixed-mobile") && (a = $(".page-header").outerHeight(!0)) : $(".page-header").hasClass("navbar-fixed-top") ? a = $(".page-header").outerHeight(!0) : $("body").hasClass("page-header-fixed") && (a = 64);
	            table = $("#sample_2")
				.DataTable({
	                language: {
	                    aria: {
	                        sortAscending: ": activate to sort column ascending",
	                        sortDescending: ": activate to sort column descending"
	                    },
	                    emptyTable: "空表",
	                    info: "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
	                    infoEmpty: "没有数据",
	                    // infoFiltered: "(filtered1 from _MAX_ total entries)",
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
	                searching: true,// 是否过滤检索
	                order: [[2, "desc"]],// 默认排序列及排序方式
	                ordering:  true,//是否排序
	                lengthMenu: [[5, 10, 15, 30, -1], [5, 10, 15, 30, "All"]],
	                pageLength: 10,// 每页显示数量
	                processing: true,// loading等待框
	// serverSide: true,
	                ajax: 'rest/operateLog/findOperateLogList?type=user&startTime='+$scope.startTime+"&endTime="+$scope.endTime,// 加载数据中
	                ajax:{
	             	   "url": "rest/operateLog/findOperateLogList",
	             	   "type": "post",
	             	   "contentType": "application/json",
	             	   "data": function ( d ) {
	             		   d.params = JSON.stringify($scope.params);
	             	    }
	                },
	                "aoColumns": [
	                              { mData: 'operationDesc' },
	                              { mData: 'requestIp' },
	                              {
										mData : 'operationTime',
	        							mRender : function(
	        									data) {
	        								if (data != null) {
	        									return timeStamp2String(data);
	        								} else
	        									return '';
	        							}
								}
	                        ]

	            }).on('operateLog.dt',
	            function() {
	                console.log('排序');
	            })

	            

	        };
		    function addDate(date, days) {
				var d = new Date(date);
				d.setDate(d.getDate() + days);
				/*var month = d.getMonth() + 1;
				var day = d.getDate();
				if (month < 10) {
					month = "0" + month;
				}
				if (day < 10) {
					day = "0" + day;
				}
				var val = d.getFullYear() + "" + month + ""
						+ day;*/
				return d;
			}

	        /**
	         * 搜索
	         */
	        $scope.search=function () {
	        	$scope.params = {};
	        	$scope.params.startTime = $scope.startTime;
	        	$scope.params.endTime = $scope.endTime;
	        	table.settings()[0].ajax.data.params =  JSON.stringify($scope.params);
	        	table.ajax.reload();
	        };  
	        
	        /**
	         * 重置搜索条件
	         */
	        $scope.resetSearchForm = function (){
	        	$scope.startTime  = timeStamp2ShortString(addDate(new Date(),-7));
	        	$scope.endTime = timeStamp2ShortString(new Date());
	        	$scope.params = {};
	        	$scope.params.startTime = $scope.startTime;
	        	$scope.params.endTime = $scope.endTime;
	        };
			//操作日志end
//企业信息start
	      //根据参数查询对象
	    	$scope.getCompanyInfo  = function() {
	    		CompanyInfoService.getCompanyInfo().then(
	          		     function(data){
	          		    	 debugger
	          		    	$scope.company={};
	          		    	 if(data==""){
	          		    		$scope.company.comTypeName="";
	          		    		return;
	          		    	 }
	          		    	/*$scope.company=data;*/
	          		    	if(data.comType=='1'){
	          		    		$scope.company.comTypeName='采购商';
	          		    	}else if(data.comType=='2'){
	          		    		$scope.company.comTypeName='供应商';
	          		    	}else if(data.comType=='3'){
	          		    		$scope.company.comTypeName='承运人';
	          		    	}else if(data.comType=='4'){
	          		    		$scope.company.comTypeName='外协仓';
	          		    	}else if(data.comType=='5'){
	          		    		$scope.company.comTypeName='境外供应商';
	          		    	}else if(data.comType=='6'){
	          		    		$scope.company.comTypeName='装卸公司';
	          		    	}else if(data.comType=='7'){
	          		    		$scope.company.comTypeName='银行';
	          		    	}else{
	          		    		$scope.company.comTypeName='保险公司';
	          		    	}
	          		     },
	          		     function(error){
	          		         toastr.error('连接服务器出错,请登录重试！');
	          		     }
	          		 );
	        }; 
	    	
	    	
	    	//修改
	    	$scope.editComPany= function() {
	    		$scope.companyAdd=true;
	    		$scope.companyView=false;
	    		$scope.companyInfoEdit=true;
	    		$scope.companyInfoSave=true;
	    	};
	    	
	    	
	    	//修改企业信息
	    	$scope.updateCompanyInfo=function(){
	    		if($('#form_sample_companyInfo').valid()){
	    		var promise = CompanyInfoService.updateCompanyInfo($scope);
	    		promise.then(function(data) {
	    			$scope.company=data;
	    				/*$scope.getCompanyInfo();*/
	    			if(data.comType=='1'){
      		    		$scope.company.comTypeName='采购商';
      		    	}else if(data.comType=='2'){
      		    		$scope.company.comTypeName='供应商';
      		    	}else if(data.comType=='3'){
      		    		$scope.company.comTypeName='承运人';
      		    	}else if(data.comType=='4'){
      		    		$scope.company.comTypeName='外协仓';
      		    	}else if(data.comType=='5'){
      		    		$scope.company.comTypeName='境外供应商';
      		    	}else if(data.comType=='6'){
      		    		$scope.company.comTypeName='装卸公司';
      		    	}else if(data.comType=='7'){
      		    		$scope.company.comTypeName='银行';
      		    	}else{
      		    		$scope.company.comTypeName='保险公司';
      		    	}
	    				toastr.success("修改成功");
	    				$scope.companyAdd=false;
	    				$scope.companyView=true;
	    				$scope.companyInfoEdit=false;
	    	    		$scope.companyInfoSave=false;
	    		}, function() {
	    			// 调用承诺接口reject();
	    			$(".modal-backdrop").remove();
	    			handle.unblockUI();
	    			toastr.error("保存失败！请联系管理员");
	    		});	
	    		}
	    	}
	    		
	    	// 页面加载完成后调用，验证输入框
	    	$scope.$watch('$viewContentLoaded', function() {  
	    		var e = $("#form_sample_companyInfo"),
	            r = $(".alert-danger", e),
	            i = $(".alert-success", e);
	            e.validate({
	                errorElement: "span",
	                errorClass: "help-block help-block-error",
	                focusInvalid: !1,
	                ignore: "",
	                messages: {
	                	comNum:{required:"企业编号不能为空！",},
	                	comName:{required:"企业名称不能为空！",},
	                	comType:{required:"企业类型不能为空！",},
	                	comNature:{required:"企业性质不能为空！",},
	                	
	                	legalPerson:{required:"企业法人不能为空！",},
	                	address:{required:"注册地址不能为空！",},
	                	taxpayeNumber:{required:"纳税人识别号不能为空！",},
	                	tel:{isPhone:"请输入正确的联系电话！",},
	                    payment: {
	                        maxlength: jQuery.validator.format("Max {0} items allowed for selection"),
	                        minlength: jQuery.validator.format("At least {0} items must be selected")
	                    },
	                    "checkboxes1[]": {
	                        required: "Please check some options",
	                        minlength: jQuery.validator.format("At least {0} items must be selected")
	                    },
	                    "checkboxes2[]": {
	                        required: "Please check some options",
	                        minlength: jQuery.validator.format("At least {0} items must be selected")
	                    }
	                },
	                rules: {
	                	comNum:{required:true,},
	                	comName:{required:true,},
	                	comType:{required:true,},
	                	comNature:{required:true,},
	                	
	                	legalPerson:{required:true,},
	                	address:{required:true,},
	                	taxpayeNumber:{required:true,},
	                	tel:{isPhone:true,},
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
	    	//企业信息end
	    	
	    	//账户安全start
	    	// 修改邮箱开始***************************************							
	    	$scope.editEmail= function() {		
	    		$('#basicMaterielInfo').modal('show');// 弹出修改模态框	
	    		$rootScope.changeEmail.email=null;
	    		$rootScope.changeEmail.password=null;
	    	};
	    	
	    	// 修改手机号码开始***************************************							
	    	$scope.editPhone= function() {		
	    		$('#basicMaterielInfoI').modal('show');// 弹出修改模态框	
	    		$rootScope.changePhone.phone=null;
	    		$rootScope.changePhone.password=null;
	    	};
	    	
	    	$rootScope.saveEmail = function() {
	    		if($('#form_sample_email').valid()){
	    		AccountSecurityService
	    			.updateEmail($rootScope.changeEmail)
	    			.then(
	    					function(data) {
	    						$('#basicMaterielInfo').modal('hide');// 保存成功后关闭模态框
	    						toastr.success("修改成功");
	    						$state.go('accountSecurity',{},{reload:true});
	    						$(".modal-backdrop").remove();
	    					},
	    					function(errResponse) {
	    						toastr.warning("当前用户密码输入有误！");
	    						console
	    								.error('Error while creating User');
	    					}

	    			);
	    		}
	    	};
	    	
	    	
	    	$rootScope.savePhone = function() {
	    		if($('#form_sample_phone').valid()){
	    		AccountSecurityService
	    			.updatePhone($rootScope.changePhone)
	    			.then(
	    					function(data) {
	    						$('#basicMaterielInfoI').modal('hide');// 保存成功后关闭模态框
	    						toastr.success("修改成功");
	    						$state.go('accountSecurity',{},{reload:true});
	    						$(".modal-backdrop").remove();
	    					},
	    					function(errResponse) {
	    						toastr.warning("当前用户密码输入有误！");
	    						console
	    								.error('Error while creating User');
	    					}

	    			);
	    		}
	    	};
	    	
	    	//根据参数查询对象
	    	$scope.getUserInfo  = function() {
	    		AccountSecurityService.getUserInfo().then(
	          		     function(data){
	          		    	$scope.userInfo=data;
	          		     },
	          		     function(error){
	          		         toastr.error('连接服务器出错,请登录重试！');
	          		     }
	          		 );
	        }; 
	    	
	    	
	    
	    	
	    	// 页面加载完成后调用，验证输入框
	    	$scope.$watch('$viewContentLoaded', function() {  
	    		var e = $("#form_sample_email"),
	            r = $(".alert-danger", e),
	            i = $(".alert-success", e);
	            e.validate({
	                errorElement: "span",
	                errorClass: "help-block help-block-error",
	                focusInvalid: !1,
	                ignore: "",
	                messages: {
	                	email:{email:"请填写正确的邮箱！",required:"邮箱不能为空！",},
	                	password:{required:"密码不能为空！",},
	                    payment: {
	                        maxlength: jQuery.validator.format("Max {0} items allowed for selection"),
	                        minlength: jQuery.validator.format("At least {0} items must be selected")
	                    },
	                    "checkboxes1[]": {
	                        required: "Please check some options",
	                        minlength: jQuery.validator.format("At least {0} items must be selected")
	                    },
	                    "checkboxes2[]": {
	                        required: "Please check some options",
	                        minlength: jQuery.validator.format("At least {0} items must be selected")
	                    }
	                },
	                rules: {
	                	email:{
	                		required:true,
	                		email:true,
	                	},
	                	password:{required:true,},
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
	    	
	    	// 手机号码验证
	    	     $.validator.addMethod("isMobile", function(value, element) {
	    	          var length = value.length;
	    	          var mobile = /^(13[0-9]{9})|(18[0-9]{9})|(14[0-9]{9})|(17[0-9]{9})|(15[0-9]{9})$/;
	    	        return this.optional(element) || (length == 11 && mobile.test(value));
	    	     }, "请正确填写您的手机号码");
	    	
	    	// 页面加载完成后调用，验证输入框
	    	$scope.$watch('$viewContentLoaded', function() {  
	    		var e = $("#form_sample_phone"),
	            r = $(".alert-danger", e),
	            i = $(".alert-success", e);
	            e.validate({
	                errorElement: "span",
	                errorClass: "help-block help-block-error",
	                focusInvalid: !1,
	                ignore: "",
	                messages: {
	                	phone:{isMobile:"请正确填写您的手机号码！",required:"手机号不能为空！",},
	                	passwordPhone:{required:"密码不能为空！",},
	                    payment: {
	                        maxlength: jQuery.validator.format("Max {0} items allowed for selection"),
	                        minlength: jQuery.validator.format("At least {0} items must be selected")
	                    },
	                    "checkboxes1[]": {
	                        required: "Please check some options",
	                        minlength: jQuery.validator.format("At least {0} items must be selected")
	                    },
	                    "checkboxes2[]": {
	                        required: "Please check some options",
	                        minlength: jQuery.validator.format("At least {0} items must be selected")
	                    }
	                },
	                rules: {
	                	phone:{
	                		required:true,
	                		isMobile:true,
	                	},
	                	passwordPhone:{required:true,},
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
	    	//账户安全end
}]);


