angular.module('MetronicApp').controller('UserInfoController', ['$rootScope','$scope','$http', 'settings', '$q','UserInfoService','$state','$compile','$stateParams','$filter','FileUploader', 
                                                                function($rootScope,$scope,$http,settings, $q,UserInfoService,$state,$compile,$stateParams,$filter,FileUploader) {
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
	});
	
	//根据参数查询对象
	$scope.getUserInfo  = function() {
		UserInfoService.getUserInfo().then(
      		     function(data){
      		    	$scope.userInfo=data;
      		     },
      		     function(error){
      		         console.log("error")
      		     }
      		 );
    }; 
    
    
  //根据参数查询对象
	var getUserInfoA  = function() {
		UserInfoService.getUserInfo().then(
      		     function(data){
      		    	$scope.userInfo=data;
      		    	$("#avatar").attr("src","uploadAttachFiles/"+$scope.userInfo.avatar);
      		    	$("#usernameOfUserInfo").html($scope.userInfo.displayName);
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
	
	//添加合同
	$scope.updateUserInfo = function() {
		
		if($('#form_sample_1').valid()){//表单验证通过则执行添加功能
		var fd = new FormData();
		if($("input[type='file']").length){
        var file = document.querySelector('input[type="file"]').files[0];
        fd.append("file", file);
		}
		
		fd.append('userId', $scope.userInfo.userId); 
		fd.append('displayName',$scope.userInfo.displayName); 
        fd.append('sex',$("input[name='sex']:checked").val()); 
        fd.append('telephone',$scope.userInfo.telephone); 
        fd.append('QQNum',$scope.userInfo.qqnum); 
        fd.append('fax',$scope.userInfo.fax); 
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
        
        jQuery.validator.addMethod("isQq", function(value, element) {  
        	var qq=/^[1-9]\d{4,12}$/;
            return this.optional(element) ||(qq.test(value));       
       }, "匹配QQ"); 
	
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
            	userName:{required:"用户名不能为空！",},
            	telephone:{isTel:"请填写正确的固定电话！",},
            	qqNum:{isQq:"请填写正确的QQ号码！",},
            	fax:{isTel:"请填写正确的传真号码！",},
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
                userName:{required:true,},
                telephone:{isTel:true,},
                qqNum:{isQq:true,},
                fax:{isTel:true,},
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


