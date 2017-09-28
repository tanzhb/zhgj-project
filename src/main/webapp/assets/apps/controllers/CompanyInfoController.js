angular.module('MetronicApp').controller('CompanyInfoController', ['$rootScope','$scope','$http', 'settings', '$q','CompanyInfoService','$state','$compile','$stateParams','$filter','FileUploader', 
                                                                function($rootScope,$scope,$http,settings, $q,CompanyInfoService,$state,$compile,$stateParams,$filter,FileUploader) {
	$scope.$on('$viewContentLoaded', function() {   
		// initialize core components
		handle = new pageHandle();
		App.initAjax();

		// set default layout mode
		$rootScope.settings.layout.pageContentWhite = true;
		$rootScope.settings.layout.pageBodySolid = false;
		$rootScope.settings.layout.pageSidebarClosed = false;
		$scope.companyAdd=false;
		$scope.companyView=true;
		
		//查询登陆者的信息
    	$scope.getCompanyInfo();	
	});
	
	//根据参数查询对象
	$scope.getCompanyInfo  = function() {
		CompanyInfoService.getCompanyInfo().then(
      		     function(data){
      		    	$scope.company=data;
      		    	if(data.comType='1'){
      		    		$scope.company.comTypeName='采购商';
      		    	}else if(data.comType='2'){
      		    		$scope.company.comTypeName='供应商';
      		    	}else if(data.comType='3'){
      		    		$scope.company.comTypeName='承运人';
      		    	}else if(data.comType='4'){
      		    		$scope.company.comTypeName='外协仓';
      		    	}else if(data.comType='5'){
      		    		$scope.company.comTypeName='境外供应商';
      		    	}else if(data.comType='6'){
      		    		$scope.company.comTypeName='装卸公司';
      		    	}else if(data.comType='7'){
      		    		$scope.company.comTypeName='银行';
      		    	}else{
      		    		$scope.company.comTypeName='保险公司';
      		    	}
      		     },
      		     function(error){
      		         console.log("error")
      		     }
      		 );
    }; 
	
	
	//修改
	$scope.edit = function() {
		$scope.companyAdd=true;
		$scope.companyView=false;
	};
	
	
	//修改企业信息
	$scope.updateCompanyInfo=function(){
		if($('#form_sample_1').valid()){
		var promise = CompanyInfoService.updateCompanyInfo($scope);
		promise.then(function() {
				$scope.getCompanyInfo();
				toastr.success("修改成功");
				$scope.companyAdd=false;
				$scope.companyView=true;
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
		var e = $("#form_sample_1"),
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

}]);


