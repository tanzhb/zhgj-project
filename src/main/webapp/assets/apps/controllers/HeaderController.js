angular.module('MetronicApp').controller('HeaderController', function($rootScope, $scope, $http, $timeout, UserService) {
    $scope.$on('$viewContentLoaded', function() {   
        // initialize core components
        App.initAjax();
    });

    // set sidebar closed and body solid layout mode
    $rootScope.settings.layout.pageContentWhite = true;
    $rootScope.settings.layout.pageBodySolid = false;
    $rootScope.settings.layout.pageSidebarClosed = false;
    
    var self = $rootScope;
	self.changePassword = {
		oldPassword : '',
		newPassword : ''
	};
    
    
 // 修改用户开始***************************************							
	$scope.editPassword = function() {		
		$('#editPasswordModal').modal('show');// 弹出修改模态框	
		$('#twoPass').val('');$('#password1').val('');$('#password2').val('');
	};
	
	$rootScope.savePass = function() {
		if($('#editPassword').valid()){//表单验证通过则执行添加功能
			UserService
			.updatePass($rootScope.changePassword)
			.then(
					function(data) {
						$('#editPasswordModal').modal(
								'hide');// 保存成功后关闭模态框
						$('#editPassSuccess').modal(
						'show');// 弹出密码修改成功对话框
					},
					function(errResponse) {
						toastr.warning("当前用户密码输入有误！");
						console
								.error('Error while creating User');
					}

			);
		}
		
	};
	// 修改用户结束***************************************
	
	
	// 页面加载完成后调用，验证输入框
	$scope.$watch('$viewContentLoaded', function() {  
		var e = $("#editPassword"),
        r = $(".alert-danger", e),
        i = $(".alert-success", e);
        e.validate({
            errorElement: "span",
            errorClass: "help-block help-block-error",
            focusInvalid: !1,
            ignore: "",
            messages: {
        		password1:{required:"密码不能为空！",rangelength:jQuery.validator.format("密码位数必须在{0}到{1}字符之间！")},
        		password2:{required:"密码不能为空！",rangelength:jQuery.validator.format("密码位数必须在{0}到{1}字符之间！")},
        		twoPass:{required: "请输入确认密码!",rangelength:jQuery.validator.format("密码位数必须在{0}到{1}字符之间！"), equalTo: "请再次输入相同的值!"}
            },
            rules: {
                password1:{required:true,
                	rangelength:[6,12]
                },
                password2:{required:true,
                	rangelength:[6,12]
                },
                twoPass: {
                    required: true,
                    rangelength:[6,12],
                    equalTo: "#password2"
                  }
            },
            invalidHandler: function(e, t) {
                i.hide(),
                r.show(),
                App.scrollTo(r, -200)
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
	
	
	
	
	
});