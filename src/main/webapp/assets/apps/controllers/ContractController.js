angular.module('MetronicApp').controller('ContractController', ['$rootScope','$scope','$http', 'settings', '$q','ContractService','$state','$compile','$stateParams','$filter', function($rootScope,$scope,$http,settings, $q,ContractService,$state,$compile,$stateParams,$filter) {
	$scope.$on('$viewContentLoaded', function() {   
		// initialize core components
		handle = new pageHandle();
		App.initAjax();

		// set default layout mode
		$rootScope.settings.layout.pageContentWhite = true;
		$rootScope.settings.layout.pageBodySolid = false;
		$rootScope.settings.layout.pageSidebarClosed = false;
		
		loadMainTable();
		loadMainTable1();
		loadMainTable2();
		
		
		//根据参数查询对象
    if($stateParams.id){
    	$scope.getUserContractInfo($stateParams.id);	
    }
	});
	
	
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
	
	$scope.signSaleContract = function() {
		if($('#form_sample_1').valid()){//表单验证通过则执行添加功能
			debugger
			var fd = new FormData();
			if($("input[name='file']").length){
	        var file = document.querySelector('input[name="file"]').files[0];
	        fd.append("files", file);
			}
			fd.append('id',$scope.contractVO.id); 
			fd.append('orderSerial',$scope.contractVO.orderSerial); 
	         $http({
	        	  method:'POST',
	              url:"rest/contract/signSaleContract",
	              data: fd,
	              headers: {'Content-Type':undefined}
	               })   
	              .success( function ( response )
	                       {
	                       //上传成功的操作
	            	  toastr.success("签订合同数据成功！");
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
	
	//根据参数查询对象
	$scope.getUserContractInfo  = function(id) {
		ContractService.selectUserContract(id).then(
      		     function(data){
      		    	$scope.contractVO=data;
      		    	//将日期转换成标准格式
      		    	var myJsDate=$filter('date')($scope.contractVO.startDate,'yyyy-MM-dd');
					$scope.contractVO.startDate=myJsDate;
					
					var myJsDate1=$filter('date')($scope.contractVO.endDate,'yyyy-MM-dd');
					$scope.contractVO.endDate=myJsDate1;
					
					var myJsDate2=$filter('date')($scope.contractVO.signDate,'yyyy-MM-dd');
					$scope.contractVO.signDate=myJsDate2;
      		     },
      		     function(error){
      		         console.log("error")
      		     }
      		 );
    	
    }; 
    
    
	//初始化toastr开始
	toastr.options = {
			"closeButton" : true,
			"debug" : true,
			"positionClass" : "toast-top-center",
			"onclick" : null,
			"showDuration" : "1000",
			"hideDuration" : "1000",
			"timeOut" : "5000",
			"extendedTimeOut" : "1000",
			"showEasing" : "swing",
			"hideEasing" : "linear",
			"showMethod" : "fadeIn",
			"hideMethod" : "fadeOut"
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
		
		
		 $scope.exportContract = function(){
	    	 handle.blockUI("正在导出数据，请稍后"); 
	    	 window.location.href=$rootScope.basePath+"/rest/contract/exportContract";
	    	 handle.unblockUI(); 
	       }
		 
		 $scope.exportSaleContract = function(){
	    	 handle.blockUI("正在导出数据，请稍后"); 
	    	 window.location.href=$rootScope.basePath+"/rest/contract/exportSaleContract";
	    	 handle.unblockUI(); 
	       }
		 
		 $scope.exportBuyContract = function(){
	    	 handle.blockUI("正在导出数据，请稍后"); 
	    	 window.location.href=$rootScope.basePath+"/rest/contract/exportBuyContract";
	    	 handle.unblockUI(); 
	       }
		
		var table;
		var loadMainTable = function() {
			var a = 0;
			App.getViewPort().width < App.getResponsiveBreakpoint("md") ? $(".page-header").hasClass("page-header-fixed-mobile")&& (a = $(".page-header").outerHeight(!0)): 
				$(".page-header").hasClass("navbar-fixed-top") ? a = $(".page-header").outerHeight(!0): $("body").hasClass("page-header-fixed")&& (a = 64);

				 table = $("#sample_2").DataTable(
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
								zeroRecords : "抱歉， 没有找到！&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;",
								paginate : {
									"sFirst" : "首页",
									"sPrevious" : "前一页",
									"sNext" : "后一页",
									"sLast" : "尾页"
								}
							},
							order : [ [ 1, "asc" ] ],// 默认排序列及排序方式
							bRetrieve : true,
							"bScrollCollapse": true,
							// searching: true,//是否过滤检索
							 ordering: true,//是否排序
							lengthMenu : [
							              [ 5, 10, 15,15, 30, -1 ],
							              [ 5, 10, 15, 15,30, "All" ] ],
							              pageLength : 10,// 每页显示数量
							              processing : true,// loading等待框
							              // serverSide: true,
							              ajax:"rest/contract/findAllUserContract",//加载数据中user表数据
							              "aoColumns": [
							                            { mData: 'id'},
							                            { mData: 'contractNum' },
							                            { mData: 'firstParty' },
							                            { mData: 'secondParty' },
							                            { mData: 'contractType' },
							                            { mData: 'serviceModel' },
							                            { mData: 'signDate'},
							                            { mData: 'signer'},
							                            { mData: 'startDate'},
							                            { mData: 'endDate'},
							                            { mData: 'versionNO' },
							                            { mData: 'status',
							                            	mRender:function(data){
							                            		if(data!=""&&data!=null){
							                            			if(data=='0'){
							                            				return '初始';
							                            			}
							                            		}else{
							                            			return "";
							                            		}
							                            	}
							                            }
							                            ],
							                            'aoColumnDefs': [ {
							                            	'targets' : 0,
							                            	'searchable' : false,
							                            	'orderable' : false,
							                            	'className' : 'dt-body-center',
							                            	   'render' : function(data,type, full, meta) {
								                            		return '<label class="mt-checkbox mt-checkbox-single mt-checkbox-outline"><input type="checkbox" name="id[]" value="'+ $('<div/>').text(data).html()+ '"><span></span></label>';
							                            	}
							                            } ,
							                            {
							                            	'targets' : 1,
							                            	'className' : 'dt-body-center',
							                            	'render' : function(data,
							                            			type, row, meta) {
							                            		return '<a data-target="#basicContractInfo" data-toggle="modal" ng-click="getUserContractInfo(\''+row.id+'\')" ">'+data+'</a>';
							                            	},
							                            	"createdCell": function (td, cellData, rowData, row, col) {
							                            		$compile(td)($scope);
							                            	}
							                            }
							                            ]}).on('order.dt',
							                            		function() {
							                            	console.log('排序');
							                            })
		}
		
		
		var table1;
		var loadMainTable1 = function() {

				 table1 = $("#sample_2_1").DataTable(
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
								zeroRecords : "抱歉， 没有找到！&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;",
								paginate : {
									"sFirst" : "首页",
									"sPrevious" : "前一页",
									"sNext" : "后一页",
									"sLast" : "尾页"
								}
							},
							order : [ [ 1, "asc" ] ],// 默认排序列及排序方式
							bRetrieve : true,
							"bScrollCollapse": true,
							// searching: true,//是否过滤检索
							ordering: true,//是否排序
							lengthMenu : [
							              [ 5, 10, 15,15, 30, -1 ],
							              [ 5, 10, 15, 15,30, "All" ] ],
							              pageLength : 10,// 每页显示数量
							              processing : true,// loading等待框
							              // serverSide: true,
							              ajax:"rest/contract/querySaleContractList",//加载数据中user表数据
							              "aoColumns": [
							                            { mData: 'id',
						                            	mRender : function(
																data,
																type,
																row,
																meta) {
															return '<label class="mt-radio mt-radio-single mt-radio-outline">' +
																	'<input type="radio" class="radio" ng-click="getOderInfo(\''+$('<div/>').text(data).html()+'\')" value="'+ $('<div/>').text(data).html()+ '" name="id[]" />' +
																	'<span></span></label>';
														}
						                            },
							                            { mData: 'contractNum' },
							                            { mData: 'comId' },
							                            { mData: 'contractType' },
							                            { mData: 'orderAmount' },
							                            { mData: 'signDate'},
							                            { mData: 'signer'},
							                            { mData: 'startDate'},
							                            { mData: 'endDate'},
							                            { mData: 'versionNO' },
							                            { mData: 'status',
							                            	mRender:function(data){
							                            		if(data!=""&&data!=null){
							                            			if(data=='0'){
							                            				return '待审批';
							                            			}else if(data=='2'){
							                            				return '已签订';
							                            			}else if(data=='3'||data=='1'){
							                            				return '待签订';
							                            			}else{
							                            				return '';
							                            			}
							                            		}else{
							                            			return "";
							                            		}
							                            	}
							                            }
							                            ],
							                            'aoColumnDefs': [ {
							                            	'targets' : 0,
							                            	'searchable' : false,
							                            	'orderable' : false,
							                            	'className' : 'dt-body-center',
							                            	'render' : function(data,type, full, meta) {
							                            		return '<label class="mt-radio mt-radio-outline"><input type="radio" class="radio" ng-click="getOderInfo(\''+$('<div/>').text(data).html()+'\')" value="'+ $('<div/>').text(data).html()+ '" name="id[]" /><span></span></label>';
							                            	},
							                            	"createdCell": function (td, cellData, rowData, row, col) {
							                            		$compile(td)($scope);
							                            	}
							                            } ,
							                            {
							                            	'targets' : 1,
							                            	'className' : 'dt-body-center',
							                            	'render' : function(data,
							                            			type, row, meta) {
							                            		return data;
							                            	},
							                            	"createdCell": function (td, cellData, rowData, row, col) {
							                            		$compile(td)($scope);
							                            	}
							                            }
							                            ]}).on('order.dt',
							                            		function() {
							                            	console.log('排序');
							                            })
											        $("#sample_2_1").on("click", "tbody tr .radio",
											        function() {
											            $(this).parents("tr").toggleClass("active")
											            $(this).parents("tr").siblings().removeClass("active")
											        })
		}
		
		//销售订单签订
		$scope.saleOrderSign = function() {
			var ids = '';
			table1.$('input[type="radio"]').each(
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
			var status = table1.row('.active').data().status;
			if(status!='3'&&status!='1'){
				toastr.warning('只能签订状态为‘待签订’的合同！');return;
			}
			
			$state.go('saleOrderSign',{id:ids});
			/*alert(ids);*/
		};
		
		
		var table2;
		var loadMainTable2 = function() {
				 table2 = $("#sample_2_2").DataTable(
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
								zeroRecords : "抱歉， 没有找到！&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;",
								paginate : {
									"sFirst" : "首页",
									"sPrevious" : "前一页",
									"sNext" : "后一页",
									"sLast" : "尾页"
								}
							},
							order : [ [ 1, "asc" ] ],// 默认排序列及排序方式
							bRetrieve : true,
							"bScrollCollapse": true,
							// searching: true,//是否过滤检索
							ordering: true,//是否排序
							lengthMenu : [
							              [ 5, 10, 15,15, 30, -1 ],
							              [ 5, 10, 15, 15,30, "All" ] ],
							              pageLength : 10,// 每页显示数量
							              processing : true,// loading等待框
							              // serverSide: true,
							              ajax:"rest/contract/queryBuyContractList",//加载数据中user表数据
							              "aoColumns": [
							                            /*{ mData: 'id'},*/
							                             { mData: 'id',
						                            	mRender : function(
																data,
																type,
																row,
																meta) {
															return '<label class="mt-radio mt-radio-single mt-radio-outline">' +
																	'<input type="radio" class="radio" ng-click="getOderInfo1(\''+$('<div/>').text(data).html()+'\')" value="'+ $('<div/>').text(data).html()+ '" name="id[]" />' +
																	'<span></span></label>';
														}
						                            },
							                            { mData: 'contractNum' },
							                            { mData: 'comId' },
							                            { mData: 'contractType' },
							                            { mData: 'orderAmount' },
							                            { mData: 'signDate'},
							                            { mData: 'signer'},
							                            { mData: 'startDate'},
							                            { mData: 'endDate'},
							                            { mData: 'versionNO' },
							                            { mData: 'status',
							                            	mRender:function(data){
							                            		if(data!=""&&data!=null){
							                            			if(data=='0'){
							                            				return '待审批';
							                            			}else if(data=='2'){
							                            				return '已签订';
							                            			}else if(data=='3'||data=='1'){
							                            				return '待签订';
							                            			}else{
							                            				return '';
							                            			}
							                            		}else{
							                            			return "";
							                            		}
							                            	}
							                            }
							                            ],
							                            'aoColumnDefs': [/* {
							                            	'targets' : 0,
							                            	'searchable' : false,
							                            	'orderable' : false,
							                            	'className' : 'dt-body-center',
							                            	'render' : function(data,type, full, meta) {
							                            		return '<label class="mt-radio mt-radio-outline"><input type="radio" ng-click="getOderInfo1(\''+$('<div/>').text(data).html()+'\')" value="'+ $('<div/>').text(data).html()+ '" name="id[]" /><span></span></label>';
							                            	},
							                            	"createdCell": function (td, cellData, rowData, row, col) {
							                            		$compile(td)($scope);
							                            	}
							                            } ,*/
		                                             {
							                            	'targets' : 0,
							                            	'searchable' : false,
							                            	'orderable' : false,
							                            	'className' : 'dt-body-center',
							                            	'render' : function(data,type, full, meta) {
							                            		return '<label class="mt-radio mt-radio-outline"><input type="radio" class="radio" ng-click="getOderInfo1(\''+$('<div/>').text(data).html()+'\')" value="'+ $('<div/>').text(data).html()+ '" name="id[]" /><span></span></label>';
							                            	},
							                            	"createdCell": function (td, cellData, rowData, row, col) {
							                            		$compile(td)($scope);
							                            	}
							                            } ,
							                            {
							                            	'targets' : 1,
							                            	'className' : 'dt-body-center',
							                            	'render' : function(data,
							                            			type, row, meta) {
							                            		return data;
							                            	},
							                            	"createdCell": function (td, cellData, rowData, row, col) {
							                            		$compile(td)($scope);
							                            	}
							                            }
							                            ]}).on('order.dt',
							                            		function() {
							                            	console.log('排序');
							                            })
							                            
							                        $("#sample_2_2").on("click", "tbody tr .radio",
											        function() {
											            $(this).parents("tr").toggleClass("active")
											            $(this).parents("tr").siblings().removeClass("active")
											        })
		}
		
		
		//采购订单签订
		$scope.buyOrderSign = function() {
			var ids = '';
			// Iterate over all checkboxes in the table
			table2.$('input[type="radio"]').each(
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
			var status = table2.row('.active').data().status;
			if(status!='3'&&status!='1'){
				toastr.warning('只能签订状态为‘待签订’的合同！');return;
			}
			
			$state.go('saleOrderSign',{id:ids});
		};
		
		
		$scope.getOderInfo=function(serialNum){
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
		
		//删除
		$scope.del = function() {
			var ids = '';
			// Iterate over all checkboxes in the table
			table.$('input[type="checkbox"]').each(function() {
				// If checkbox exist in DOM
				if ($.contains(document, this)) {
					// If checkbox is checked
					if (this.checked) {
						// 将选中数据id放入ids中
						if (ids == '') {
							ids = this.value;
						} else
							ids = ids + ',' + this.value;
					}
				}
			});
			
				if (ids == '') {// 未勾选删除数据									
					toastr.warning("未勾选要删除数据！");
				} else {
					$('#delUsersModal').modal('show');// 打开确认删除模态框
					
					$scope.confirmDel = function() {										
						ContractService.delUserContract(ids).then(
										function(data) {
											$('#delUsersModal').modal('hide');// 删除成功后关闭模态框
											$(".modal-backdrop").remove();
											toastr.success("删除成功！");
											$state.go('userContract',{},{reload:true}); // 重新加载datatables数据
										},
										function(errResponse) {
											/*console.error('Error while deleting Users');*/
											alert(123);
										}

								);
					}
				}								
			};
			
			
			//查看上传的文件
			$scope.download=function(name) {
	           /* $http({
	                url: '/rest/contract/resourceDownload',
	                method: "POST",
	                data: $.param({
	                }),
	                headers: {'Content-Type': 'application/x-www-form-urlencoded'},
	                responseType: 'arraybuffer'
	            }).success(function (data, status, headers, config) {
	                var blob = new Blob([data], {type: "application/vnd.ms-excel"});
	                saveAs(blob, [headers('Content-Disposition').replace(/attachment;fileName=/,"")]);
	            }).error(function (data, status, headers, config) {
	                //upload failed
	            });*/
				/*window.open($rootScope.basePath+"/uploadAttachFiles/"+name);*/
				 window.location.href= $rootScope.basePath+"/rest/fileOperate/downloadFile?fileName="+encodeURI(encodeURI(name));
	        };
				
				
				/*function(name){
				 var deferred = $q.defer();  
		    	ContractService.downLoad(name).then(
		    			toastr.success("下载成功")
		    	);
		    	$http.get($rootScope.basePath + "/rest/contract/resourceDownload").success(function (data) {  
		    		alert(data);
		    		deferred.resolve(data); 
		        })
		  }*/
	        
	        //修改电子合同
	        $scope.editElectronicContract=function(myevent){
	        	var htmlObj = $(myevent.target);
	        	htmlObj.parent().parent().hide();
	        	htmlObj.parent().parent().prev().show();
	        	htmlObj.parent().parent().prev().find("input[type='file']").attr("name","files");
	        }
	        
	        //修改签字合同
	        $scope.editSignContract=function(myevent){
	        	var htmlObj = $(myevent.target);
	        	htmlObj.parent().parent().hide();
	        	htmlObj.parent().parent().prev().show();
	        	htmlObj.parent().parent().prev().find("input[type='file']").attr("name","file");
	        }
	        
	        
	        /**
	        * 下载EXCEL模板
	        */
	       $scope.downloadImportTemp = function(){
	    	   window.location.href=$rootScope.basePath+"/rest/contract/downloadImportTemp";
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
	    	   	var promise = ContractService.uploadExcel();
       			promise.then(function(data){
       				handle.unblockUI(); 
       				if(data.data.data=="success"){
       					handle.toastr.success("导入成功");
       					$state.go('userContract',{},{reload:true});
       					$(".modal-backdrop").remove();
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
		
			//格式化日期
			function timeStamp2String(time){
	        var datetime = new Date();
	         datetime.setTime(time);
	         var year = datetime.getFullYear();
	         var month = datetime.getMonth() + 1;
	         var date = datetime.getDate();
	         return month+"/"+date+"/"+year ;
	        };
			
			//复选框全选
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
							$('#sample_2 tbody')
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

	var self = this;
	self.contract={id:null,contractNum:'',contractType:'',serviceModel:'',settlementClause:'',startDate:''};
	self.contractList=[];


	//查询所有的用户合同
	fetchAllUserContract();

	function fetchAllUserContract(){
		ContractService.fetchAllUserContract()
		.then(
				function(d) {

				},
				function(errResponse){
					/*console.error('Error while fetching Users');*/
				}
		);
	}

}]);


