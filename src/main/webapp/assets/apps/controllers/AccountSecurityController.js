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
		//查询登陆者的信息
    	$scope.getUserInfo();	
	});
	
	// 修改用户开始***************************************							
	$scope.editEmail= function() {		
		$('#basicMaterielInfo').modal('show');// 弹出修改模态框	
		$('#email').val('');
		$('#password').val('');
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
	
	//根据参数查询对象
	$scope.getUserInfo  = function() {
		AccountSecurityService.getUserInfo().then(
      		     function(data){
      		    	$scope.userInfo=data;
      		     },
      		     function(error){
      		         console.log("error")
      		     }
      		 );
    }; 
	
	
	//修改
	$scope.edit = function() {
		$scope.input=true;
		$scope.span=false;
	};
	
	$.validator.addMethod("checkEmail",function(value,element){  
        var checkEmail = /^[a-z0-9]+@([a-z0-9]+\.)+[a-z]{2,4}$/i;  
        return this.optional(element)||(checkEmail.test(value));  
    },"请输入正确的邮箱！");  
	
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
            	email:{required:"邮箱不能为空！",checkEmail:"请填写正确的邮箱！"},
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
            		checkEmail:true,
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

}]);


