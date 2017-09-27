angular.module('MetronicApp').controller('UserInfoController', ['$rootScope','$scope','$http', 'settings', '$q','UserInfoService','$state','$compile','$stateParams','$filter', function($rootScope,$scope,$http,settings, $q,UserInfoService,$state,$compile,$stateParams,$filter) {
	$scope.$on('$viewContentLoaded', function() {   
		// initialize core components
		handle = new pageHandle();
		App.initAjax();

		// set default layout mode
		$rootScope.settings.layout.pageContentWhite = true;
		$rootScope.settings.layout.pageBodySolid = false;
		$rootScope.settings.layout.pageSidebarClosed = false;
		
		
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
	
	
	//修改
	$scope.jumpToEdit = function() {
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
							} else{
								ids = "more"
							}
						}
					}
				});
		if(ids==''){
			toastr.warning('请选择一个合同！');return;
		}else if(ids=='more'){
			toastr.warning('只能选择一个合同！');return;
		}
		$state.go('editUserContractPage',{id:ids});
	};
	
	
	//添加合同
	$scope.saveUserContract = function() {
		debugger
		if($('#form_sample_1').valid()){//表单验证通过则执行添加功能
		var fd = new FormData();
		if($("input[name='files']").length){
			var files = document.querySelector('input[name="files"]').files[0];
			fd.append("files", files);
		}
        
		if($("input[name='file']").length){
        var file = document.querySelector('input[name="file"]').files[0];
        fd.append("files", file);
		}
		
		if($("input[id='id']").length){
		fd.append('id', $("#id").val()); 
		}
		fd.append('contractNum',$scope.contractVO.contractNum); 
        fd.append('contractType',$scope.contractVO.contractType); 
        fd.append('firstParty',$scope.contractVO.firstParty); 
        fd.append('firstPartySigner',$scope.contractVO.firstPartySigner); 
        fd.append('secondParty',$scope.contractVO.secondParty); 
        fd.append('secondPartySigner',$scope.contractVO.secondPartySigner);
        fd.append('otherPartyContractNum',$scope.contractVO.otherPartyContractNum);
        fd.append('startDate',$scope.contractVO.startDate); 
        fd.append('endDate',$scope.contractVO.endDate); 
        fd.append('signDate',$scope.contractVO.signDate); 
        fd.append('remark',$scope.contractVO.remark);
        fd.append('signerAddress',$scope.contractVO.signerAddress);
         $http({
        	  method:'POST',
              url:"rest/contract/saveUserContract",
              data: fd,
              headers: {'Content-Type':undefined}
               })   
              .success( function ( response )
                       {
                       //上传成功的操作
            	  toastr.success("保存合同数据成功！");
				  $state.go('userContract');
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
            	
            	contractNum:{required:"合同编号不能为空！",rangelength:jQuery.validator.format("合同编号位数必须在{0}到{1}字符之间！")
            		/*remote:jQuery.validator.format("用户名已经被注册")*/},
            	contractType:{required:"合同类型不能为空！"},
            	otherPartyContractNum:{required:"对方合同号不能为空！"},
            	firstParty:{required:"合同甲方不能为空！"},
            	secondParty:{required:"合同乙方不能为空！"},
            	startDate:{required:"开始日期不能为空！"},
            	
            	firstPartySigner:{required:"甲方签订人不能为空！"},
            	secondPartySigner:{required:"乙方签订人不能为空！"},
            	signerAddress:{required:"签订地址不能为空！"},
            	
            	endDate:{required:"结束日期不能为空！"},
            	signDate:{required:"签订日期不能为空！"},
            	signer:{required:"签订人不能为空！"},
            	files:{required:"电子合同不能为空！"},
            	file:{required:"签字合同不能为空！"},
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
                name: {
                    minlength: 2,
                    required: !0
                },
                name2: {
                    minlength: 6,
                    required: !0
                },
                contractNum:{required:true,
                	rangelength:[3,12]
                	/*rangelength:[6,20],
                    remote:{//验证用户名是否存在
                           type:"POST",
                           url:$rootScope.basePath + "/rest/user/selectByUsername",
                           data:{
                             name3:function(){return $("#name3").val();}
                           } 
                    }*/
                },
                contractType:{required:true,
                },
                
                otherPartyContractNum:{required:true,
                },
                
                firstParty:{required:true,
                },
                
                secondParty:{required:true,
                },
                
                
                firstPartySigner:{required:true,
                },
                
                secondPartySigner:{required:true,
                },
                
                signerAddress:{required:true,
                },
                
                
                startDate:{required:true,
                },
                endDate:{required:true,
                },
                signDate:{required:true,
                },
                signer:{required:true,
                }, 
                files:{required:true,
                },
                file:{required:true,
                },
                email: {
                    required: !0,
                    email: !0
                },
                email2: {
                    required: !0,
                    email: !0
                },
                url: {
                    required: !0,
                    url: !0
                },
                url2: {
                    required: !0,
                    url: !0
                },
                number: {
                    required: !0,
                    number: !0
                },
                number2: {
                    required: !0,
                    number: !0
                },
                digits: {
                    required: !0,
                    digits: !0
                },
                creditcard: {
                    required: !0,
                    creditcard: !0
                },
                delivery: {
                    required: !0
                },
                payment: {
                    required: !0,
                    minlength: 2,
                    maxlength: 4
                },
                memo: {
                    required: !0,
                    minlength: 10,
                    maxlength: 40
                },
                "checkboxes1[]": {
                    required: !0,
                    minlength: 2
                },
                "checkboxes2[]": {
                    required: !0,
                    minlength: 3
                },
                radio1: {
                    required: !0
                },
                radio2: {
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

}]);


