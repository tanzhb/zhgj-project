angular.module('MetronicApp').controller('PurchaseForecastController', ['$rootScope','$scope','$http', 'settings', '$q','PurchaseForecastService','$state','$compile','$stateParams','$filter', function($rootScope,$scope,$http,settings, $q,ContractService,$state,$compile,$stateParams,$filter) {
	$scope.$on('$viewContentLoaded', function() {   
		// initialize core components
		handle = new pageHandle();
		App.initAjax();

		// set default layout mode
		$rootScope.settings.layout.pageContentWhite = true;
		$rootScope.settings.layout.pageBodySolid = false;
		$rootScope.settings.layout.pageSidebarClosed = false;
		
		loadMainTable();
		
	
		
		
		//根据参数查询对象
	    if($stateParams.id){
	    	$scope.getPurchaseForecastInfo($stateParams.id);	
	    }
	});
	
	
	$scope.getPurchaseForecastInfo  = function(id) {
		debugger
		PurchaseForecastService.selectPurchaseForecast(id).then(
      		     function(data){
      		    	$scope.contractVO=data;
      		    	//将日期转换成标准格式
      		    	var myJsDate=$filter('date')($scope.contractVO.startDate,'MM/dd/yyyy');
					$scope.contractVO.startDate=myJsDate;
					
					var myJsDate1=$filter('date')($scope.contractVO.endDate,'MM/dd/yyyy');
					$scope.contractVO.endDate=myJsDate1;
					
					var myJsDate2=$filter('date')($scope.contractVO.signDate,'MM/dd/yyyy');
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
	
		
		
		
		var table;
		var tableAjaxUrl = "rest/contract/findAllUserContract";
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
							                            { mData: 'id'},
							                            { mData: 'contractNum' },
							                            { mData: 'comId' },
							                            { mData: 'contractType' },
							                            { mData: 'serviceModel' },
							                            { mData: 'signDate',
							                            	mRender:function(data){
							                            		if(data!=""&&data!=null){
							                            			return timeStamp2String(data);
							                            		}else{
							                            			return "";
							                            		}
							                            	}
							                            }
							                            ,
							                            { mData: 'signer'},
							                            { mData: 'startDate',
							                            	mRender:function(data){
							                            		if(data!=""&&data!=null){
							                            			return timeStamp2String(data);
							                            		}else{
							                            			return "";
							                            		}
							                            	}
							                            }
							                            ,
							                            { mData: 'endDate',
							                            	mRender:function(data){
							                            		if(data!=""&&data!=null){
							                            			return timeStamp2String(data);
							                            		}else{
							                            			return "";
							                            		}
							                            	}
							                            }
							                            ,
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
							                            /*,

				              { mData: 'id' }*/
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
						PurchaseForecastService.delPurchaseForecast(ids).then(
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
			

	var self = this;
	self.contract={id:null,contractNum:'',contractType:'',serviceModel:'',settlementClause:'',startDate:''};
	self.contractList=[];


	//查询所有的用户合同
	fetchAllPurchaseForecast();

	function fetchAllPurchaseForecast(){
		PurchaseForecastService.fetchAllPurchaseForecast()
		.then(
				function(d) {

				},
				function(errResponse){
					/*console.error('Error while fetching Users');*/
				}
		);
	}

}]);


