angular.module('MetronicApp').controller('AccountSecurityController', ['$rootScope','$scope','$http', 'settings', '$q','AccountSecurityService','$state','$compile','$stateParams','$filter', 
                                                                function($rootScope,$scope,$http,settings, $q,AccountSecurityService,$state,$compile,$stateParams,$filter) {
	$scope.$on('$viewContentLoaded', function() {   
		// initialize core components
		handle = new pageHandle();
		App.initAjax();

		// set default layout mode
		$rootScope.settings.layout.pageContentWhite = true;
		$rootScope.settings.layout.pageBodySolid = false;
		$rootScope.settings.layout.pageSidebarClosed = false;
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
    	$scope.getUserInfo();	
	});
	
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
		if($('#form_sample_1').valid()){
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
		if($('#form_sample_2').valid()){
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
	
	
	//修改
	$scope.edit = function() {
		$scope.input=true;
		$scope.span=false;
	};
	
	// 页面加载完成后调用，验证输入框
	$scope.$watch('$viewContentLoaded', function() {  
		var e = $("#form_sample_1"),
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
		var e = $("#form_sample_2"),
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

}]);


