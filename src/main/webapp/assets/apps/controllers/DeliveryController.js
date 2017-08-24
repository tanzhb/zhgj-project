angular.module('MetronicApp').controller('DeliveryController', ['$rootScope','$scope','$http', 'settings', '$q','DeliveryService','$state','$compile','$stateParams','$filter', function($rootScope,$scope,$http,settings, $q,DeliveryService,$state,$compile,$stateParams,$filter) {
	$scope.$on('$viewContentLoaded', function() {   
		// initialize core components
		handle = new pageHandle();
		App.initAjax();

		// set default layout mode
		$rootScope.settings.layout.pageContentWhite = true;
		$rootScope.settings.layout.pageBodySolid = false;
		$rootScope.settings.layout.pageSidebarClosed = false;
		
		loadMainTable();
		/*loadMainTable1();*/
	
		$scope.span =false;
		$scope.input = true;
		
		
		
		//根据参数查询对象
    /*if($stateParams.id){
    	$scope.getUserContractInfo($stateParams.id);	
    }*/
		$scope.getWarehouseList();
    
	});
	
	$scope.repeatDone = function(){
    	$('.date-picker').datepicker({
			rtl: App.isRTL(),
			orientation: "left",
			autoclose: true,
			dateFormat:"yyyy-mm-dd",
			language: "zh-CN"
    		/*autoclose:true,
     	   pickerPosition:"top-right",
     	   todayHighlight: true*/
    	})
    	
   };
	
	
	//添加合同
	/*$scope.saveUserContract = function() {
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
		
        fd.append('contractNum', $("#contractNum").val()); 
        fd.append('contractType', $("#contractType").val()); 
        fd.append('serviceModel', $("#serviceModel").val()); 
        fd.append('settlementClause', $("#settlementClause").val()); 
        fd.append('comId', $("#comId").val()); 
        fd.append('startDate', $("#startDate").val()); 
        fd.append('endDate', $("#endDate").val()); 
        fd.append('signDate', $("#signDate").val()); 
        fd.append('signer', $("#signer").val()); 
        fd.append('remark', $("#remark").val()); 
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
	};*/
	
	/**
	 * 保存销售订单物料信息
	 */
	$scope.saveOrderMateriel = function(deliveryMateriel,index) {
		debugger
		delete deliveryMateriel.materiel;
		delete deliveryMateriel.supplyMateriel;
		delete deliveryMateriel.supply;
		
		if($scope.delivery==null||$.trim($scope.delivery.serialNum)==""){
			toastr.error("请先保存基本信息！");	
			return;
		}
		
		var batchNum=deliveryMateriel.batchNum;
		if($.trim(batchNum)==""||$.trim(batchNum)==null){
			toastr.error("批次号不能为空！");	
			return;
		}
		
		var manufactureDate=deliveryMateriel.manufactureDate;
		if($.trim(manufactureDate)==""||$.trim(manufactureDate)==null){
			toastr.error("生产日期不能为空！");	
			return;
		}
		
		var deliverCount=deliveryMateriel.deliverCount;
		if($.trim(deliverCount)==""||$.trim(deliverCount)==null){
			toastr.error("发货数量不能为空！");	
			return;
		}
		
		var reg = /^(0|[1-9]\d*)$/;
		if(!reg.test(deliverCount)){
			toastr.error("发货数量只能是正整数！");	
			return;
		}
		
		var amount=deliveryMateriel.amount;
		if(deliverCount>amount){
			toastr.error("发货数量不能大于订单数量！");	
			return;
		}
		
		deliveryMateriel.deliverSerial=$scope.delivery.serialNum;
		
		var promise = DeliveryService.saveDeliveryMateriel(deliveryMateriel);
		promise.then(function(data) {
			if (!handle.isNull(data)) {
				$(".modal-backdrop").remove();
				toastr.success("保存成功");
				handle.unblockUI();
				//var company = data.data;
				// $state.go('companyAdd',company,{reload:true});
				$scope.deliveryMateriel[index] = data;
				$scope.deliveryMateriel[index] = data;
				console.log(data.data);
				$scope["orderMaterielInput"+index] = true;
				$scope["orderMaterielShow"+index] = true;
				$(".alert-danger").hide();
			} else {
				$(".modal-backdrop").remove();
				handle.unblockUI();
				toastr.error("保存失败！请联系管理员");
				console.log(data);
			}
			
		}, function(data) {
			// 调用承诺接口reject();
			$(".modal-backdrop").remove();
			handle.unblockUI();
			toastr.error("保存失败！请联系管理员");
			console.log(data);
		});
	};
	
	
	$scope.saveBasicInfo=function(){
		var promise = DeliveryService.saveBasicInfo($scope);
		promise.then(function(data) {
			if (!handle.isNull(data)) {
				$(".modal-backdrop").remove();
				toastr.success("保存成功");
				handle.unblockUI();
				$scope.delivery= data;
				/*$scope.deliveryMateriel[index] = data;
				console.log(data.data);*/
				$scope.span = true;
				$scope.input = false;
				$(".alert-danger").hide();
			} else {
				$(".modal-backdrop").remove();
				handle.unblockUI();
				toastr.error("保存失败！请联系管理员");
				console.log(data);
			}
			
		}, function(data) {
			// 调用承诺接口reject();
			$(".modal-backdrop").remove();
			handle.unblockUI();
			toastr.error("保存失败！请联系管理员");
			console.log(data);
		});	
	}
	
	
	//返回按钮
	$scope.goback=function(){
		$state.go('userContract');
	}
	
	//打印
	$scope.print=function(){
		window.print();  
	}
	
	//根据参数查询对象
	$scope.getWarehouseList  = function() {
		DeliveryService.getWarehouseList().then(
      		     function(data){
      		    	$scope.warehouseList=data;
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
		/*$scope.jumpToDel = function(value) {
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
										table.ajax.reload();
										$("#sample_2").dataTable().fnDraw(false)
									},
									function(errResponse) {
										console.error('Error while deleting Users');
									}

							);
				}
			}
		};*/
		
		
		 $scope.exportContract = function(){
	    	 handle.blockUI("正在导出数据，请稍后"); 
	    	 window.location.href=$rootScope.basePath+"/rest/contract/exportContract";
	    	 handle.unblockUI(); 
	       }
		
	
		var table;
		var tableAjaxUrl = "rest/delivery/findAllDeliveryList";
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
							/*fixedHeader : {// 固定表头、表底
								header : !0,
								footer : !0,
								headerOffset : a
							},*/
							// select: true,行多选
							order : [ [ 1, "asc" ] ],// 默认排序列及排序方式
							bRetrieve : true,
							"sScrollX": "100%",
							"sScrollXInner": "110%",
							"bScrollCollapse": true,
							// searching: true,//是否过滤检索
							// ordering: true,//是否排序
							lengthMenu : [
							              [ 5, 10, 15,15, 30, -1 ],
							              [ 5, 10, 15, 15,30, "All" ] ],
							              pageLength : 10,// 每页显示数量
							              processing : true,// loading等待框
							              // serverSide: true,
							              ajax: tableAjaxUrl,//加载数据中user表数据
							              "aoColumns": [
							                            { mData: 'serialNum'},
							                            { mData: 'deliverNum' },
							                            { mData: 'orderNum' },
							                            { mData: 'materielCount' },
							                            { mData: 'packageCount' },
							                            { mData: 'receiver'},
							                            { mData: 'deliveryAddress'},
							                            { mData: 'deliverDate'},
							                            { mData: 'transportType'},
							                            { mData: 'takeAddress' },
							                            { mData: 'remark'}
							                            ],
							                            
							                            'aoColumnDefs': [ {
							                            	'targets' : 0,
							                            	'searchable' : false,
							                            	'orderable' : false,
							                            	'className' : 'dt-body-center',
							                            	'render' : function(data,type, full, meta) {
							                            		return '<input type="checkbox" name="id[]" value="'+ $('<div/>').text(data).html()+ '">';
							                            	}
							                            } ,
							                            
							                            {
							                            	'targets' : 1,
							                            	'className' : 'dt-body-center',
							                            	'render' : function(data,
							                            			type, row, meta) {
							                            		return '<a data-target="#basicContractInfo" data-toggle="modal">'+data+'</a>';
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
		
		
	        // 确认选择开始***************************************
	        var ids = '';
			 $scope.confirmSelect = function() {
				 
				 // Iterate over all checkboxes in the table
				 table1.$('input[type="radio"]').each(
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
				 if(ids==''){
					 toastr.warning('请选择一个物料！');return;
				 }
				 /*alert(ids);*/
				/* $scope.orderNum='123';*///物料编号
				 $scope.getSaleOrderInfo(ids);
				 /*loadMainTable2();*/
				 ids = '';
				 $('#basicMaterielInfo').modal('hide');// 删除成功后关闭模态框
				 $(".modal-backdrop").remove();
				 
				
			 };
			 
			        
			        $scope.getSaleOrderInfo  = function(serialNum) {
			        	DeliveryService.getSaleOrderInfo(serialNum).then(
			          		     function(data){
			          		    	 
			          		    	$scope.saleOrder=data.orderInfo;
			          		    	var orderSerial=data.orderInfo.serialNum;
			          		    	$scope.orderSerial=data.orderInfo.serialNum;
			          		    	$scope.deliveryMateriel=data.orderMateriel;
			          		     },
			          		     function(error){
			          		         $scope.error = error;
			          		     }
			          		 );
			        	
			        }; 
			        
			        
			        $scope.selectAddress=function(){
			        	var warehouseSerial=$scope.delivery.warehouseSerial;
			        	DeliveryService.selectAddress(warehouseSerial).then(
			          		     function(data){
			          		    	 debugger
			          		    	$scope.warehouseAddress=data.address;
			          		    	
			          		    	/*var orderSerial=data.orderInfo.serialNum;
			          		    	$scope.orderSerial=data.orderInfo.serialNum;
			          		    	$scope.deliveryMateriel=data.orderMateriel;*/
			          		     },
			          		     function(error){
			          		         $scope.error = error;
			          		     }
			          		 );
			        }
			        
			        $scope.selectAddressTakeDelivery=function(){
			        	var warehouseSerial=$scope.takeDelivery.warehouseSerial;
			        	DeliveryService.selectAddress(warehouseSerial).then(
			          		     function(data){
			          		    	 debugger
			          		    	$scope.takeDeliveryWarehouseAddress=data.address;
			          		    	
			          		    	/*var orderSerial=data.orderInfo.serialNum;
			          		    	$scope.orderSerial=data.orderInfo.serialNum;
			          		    	$scope.deliveryMateriel=data.orderMateriel;*/
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
						DeliveryService.delUserContract(ids).then(
										function(data) {
											$('#delUsersModal').modal('hide');// 删除成功后关闭模态框
											$(".modal-backdrop").remove();
											toastr.success("删除成功！");
											$state.go('delivery',{},{reload:true}); // 重新加载datatables数据
										},
										function(errResponse) {
											console.error('Error while deleting Users');
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
				window.open($rootScope.basePath+"/uploadAttachFiles/"+name);
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
		
		//修改
		/*$scope.jumpToEdit = function() {
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
		};*/
		
			
			
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
            	deliverNum:{required:"发货单号不能为空！",rangelength:jQuery.validator.format("发货单号位数必须在{0}到{1}字符之间！")},
            	orderNum:{required:"订单编号不能为空！"},
            	supplyComId:{required:"供应商不能为空！"},
            	shipper:{required:"发货方不能为空！"},
            	receiver:{required:"收货方不能为空！"},
            	maker:{required:"制单人不能为空！"},
            	makeDate:{required:"制单日期不能为空！"},
            	approval:{required:"审批人不能为空！"},
            	approvalDate:{required:"审批日期不能为空！"},
            	
            	
            	deliveryWarehouseSerial:{required:"发货仓库不能为空！"},
            	/*warehouseAddress:{required:"仓库地址不能为空！"},*/
            	deliverDate:{required:"发货日期不能为空！"},
            	materielCount:{required:"物料数不能为空！"},
            	packageCount:{required:"包装件数不能为空！"},
            	packageType:{required:"包装类型不能为空！"},
            	packageSpecifications:{required:"包装规格不能为空！"},
            	materielWeight:{required:"物料重量不能为空！"},
            	serviceMoney:{required:"服务费不能为空！"},
            	deliverer:{required:"发货人不能为空！"},
            	contactNum:{required:"联系方式不能为空！",digits:"请输入正确的联系, 必须为数字！",rangelength:jQuery.validator.format("电话必须在{0}到{1}位数字之间！")},
            	
            	
            	transportType:{required:"运输方式不能为空！"},
            	transport:{required:"运输方不能为空！"},
            	port:{required:"港口不能为空！"},
            	shipNumber:{required:"船号不能为空！"},
            	playArrivalDate:{required:"预计到港日期不能为空！"},
            	playWarehouseDate:{required:"预计到库日期不能为空！"},
            	deliveryTransportContact:{required:"运输联系人不能为空！"},
            	deliveryTransportContactNum:{required:"联系方式不能为空！",digits:"请输入正确的联系, 必须为数字！",rangelength:jQuery.validator.format("电话必须在{0}到{1}位数字之间！")},
            	
            	
            	takeDeliveryWarehouseSerial:{required:"收货仓库不能为空！"},
            	/*takeDeliveryWarehouseAddress:{required:"仓库地址不能为空！"},*/
            	takeDeliverDate:{required:"收货日期日期不能为空！"},
            	takeDeliveryReceiver:{required:"收货人不能为空！"},
            	takeDeliveryContactNum:{required:"联系方式不能为空！",digits:"请输入正确的联系, 必须为数字！",rangelength:jQuery.validator.format("电话必须在{0}到{1}位数字之间！")},
            	
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
                
                deliverNum:{required:true,
                },
                orderNum:{required:true,
                },
                supplyComId:{required:true,
                },
                shipper:{required:true,
                },
                receiver:{required:true,
                },
                maker:{required:true,
                },
                makeDate:{required:true,
                },
                approval:{required:true,
                },
                approvalDate:{required:true,
                }, 
               
                
                
                deliveryWarehouseSerial:{required:true,
                },
               /* warehouseAddress:{required:true,
                },*/
                deliverDate:{required:true,
                },
                materielCount:{required:true,
                },
                packageCount:{required:true,
                },
                packageType:{required:true,
                },
                packageSpecifications:{required:true,
                },
                materielWeight:{required:true,
                },
                serviceMoney:{required:true,
                },
                deliverer:{required:true,
                },
                contactNum:{
                	required:true,
                	digits:true,
                	rangelength:[7,20]
                },
                
                
                transportType:{required:true,
                },
                transport:{required:true,
                },
                port:{required:true,
                },
                shipNumber:{required:true,
                },
                playArrivalDate:{required:true,
                },
                playWarehouseDate:{required:true,
                },
                deliveryTransportContact:{required:true,
                },
                deliveryTransportContactNum:{
                	required:true,
                	digits:true,
                	rangelength:[7,20]
                },
                
                
                takeDeliveryWarehouseSerial:{required:true,
                },
               /* takeDeliveryWarehouseAddress:{required:true,
                },*/
                takeDeliverDate:{required:true,
                },
                takeDeliveryReceiver:{required:true,
                },
                takeDeliveryContactNum:{
                	required:true,
                	digits:true,
                	rangelength:[7,20]
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
	self.delivery={orderSerial:null};
	self.deliveryList=[];


	//查询所有的用户合同
	/*fetchAllUserContract();

	function fetchAllUserContract(){
		ContractService.fetchAllUserContract()
		.then(
				function(d) {

				},
				function(errResponse){
					console.error('Error while fetching Users');
				}
		);
	}*/

}]);


