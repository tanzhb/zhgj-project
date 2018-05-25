angular.module('MetronicApp').controller('HeaderController', function($rootScope, $scope, $window, $sce, $state, $http, $timeout,$stateParams, UserService) {
    $scope.$on('$viewContentLoaded', function() {   
        // initialize core components
        App.initAjax();
        
        $("#hide").val("all");//本页面用,默认列出全部关键字
        $scope.getUserInfo();
    });
    
  //根据参数查询对象
	$scope.getUserInfo  = function() {
		debugger
		UserService.getUserInfo().then(
      		     function(data){
      		    	$scope.userInfo=data;
      		     },
      		     function(error){
      		         toastr.error('连接服务器出错,请登录重试！');
      		     }
      		 );
    };
    
    $scope.select1 = function() {//选择采购订单
    	$("#selValue").text("采购订单");
    	$("#hide").val("purchaseOrder");//本页面用
    	$rootScope.searchType = "purchaseOrder";//solrSearch.jsp调用
    }
    $scope.select2 = function() {//选择销售订单
    	$("#selValue").text("销售订单");
    	$("#hide").val("saleOrder");//本页面用
    	$rootScope.searchType = "saleOrder";//solrSearch.jsp调用
    }
    $scope.select3 = function(value,type) {//选择销售订单
    	$("#selValue").text(value);
    	$("#hide").val(type);//本页面用
    	$rootScope.searchType = type;//solrSearch.jsp调用
    }
    $scope.selectAll = function() {//选择全部
    	$("#selValue").text("全部");
    	$("#hide").val("all");//本页面用
    	$rootScope.searchType = "all";//solrSearch.jsp调用
    }
 
    
    $scope.mySearchInputKeydown = function(e){
        var keycode = window.event?e.keyCode:e.which;
        if(keycode==13){
        	$scope.search();
        	return false;
        }
    };

    $scope.search = function() {
    	if($("#searchInput").val() == '' || $("#searchInput").val().trim() == ''){
    		toastr.warning("查询条件不能为空！");
    		return;
    	}
    	
    	var queryStr = "search_fields:" + $("#searchInput").val();
    	var searchType = $("#hide").val();
    	UserService
		.solrSearch(queryStr,searchType,0,10)
		.then(
				function(data) {
					$state.go('solrSearch',{},{reload:true});
					$rootScope.searchList = data[0].datas;	
					$rootScope.totalItems = data[0].total;
					$rootScope.queryStr = queryStr;
				},
				function(errResponse) {
					toastr.warning("未找到相关信息！");
					console
							.error('Error while search');
				}
		);

	};
	


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
	
	$scope.psersonCenter=function(){
		$state.go('userInfo');
	}
	
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
//        		password1:{required:"密码不能为空！",rangelength:jQuery.validator.format("密码位数必须在{0}到{1}字符之间！")},
            	password1:{required:"密码不能为空！"},
        		password2:{required:"密码不能为空！",rangelength:jQuery.validator.format("密码位数必须在{0}到{1}字符之间！")},
        		twoPass:{required: "请输入确认密码!",rangelength:jQuery.validator.format("密码位数必须在{0}到{1}字符之间！"), equalTo: "请再次输入相同的值!"}
            },
            rules: {
                password1:{required:true
//                	,rangelength:[6,12]
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
	
	//全屏
	$scope.toggleFullScreen = function() {		
		if (!document.fullscreenElement && // alternative standard method
			    !document.mozFullScreenElement && !document.webkitFullscreenElement) {// current working methods
			        if (document.documentElement.requestFullscreen) {
			            document.documentElement.requestFullscreen();
			        } else if (document.documentElement.mozRequestFullScreen) {
			            document.documentElement.mozRequestFullScreen();
			        } else if (document.documentElement.webkitRequestFullscreen) {
			            document.documentElement.webkitRequestFullscreen(Element.ALLOW_KEYBOARD_INPUT);
			        }
			    } else {
			        if (document.cancelFullScreen) {
			            document.cancelFullScreen();
			        } else if (document.mozCancelFullScreen) {
			            document.mozCancelFullScreen();
			        } else if (document.webkitCancelFullScreen) {
			            document.webkitCancelFullScreen();
			        }
			    }
	};
	
	//风格设置
	$scope.setMainStyle = function() {		
		$('.toggler').hide();
        $('.toggler-close').show();
        $('.theme-panel > .theme-options').show();
	};
	
});