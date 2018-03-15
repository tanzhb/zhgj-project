angular.module('MetronicApp').controller('PurchaseForecastController', ['$rootScope','$scope','$http', 'settings', '$q','PurchaseForecastService','$state','$compile','$stateParams','$filter', function($rootScope,$scope,$http,settings, $q,PurchaseForecastService,$state,$compile,$stateParams,$filter) {
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
		/*if($stateParams.id){
	    	$scope.getPurchaseForecastInfo($stateParams.id);	
	    }*/
	});


	/*$scope.getPurchaseForecastInfo  = function(id) {
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
      		         toastr.error('连接服务器出错,请登录重试！');
      		     }
      		 );

    };*/ 

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
	var tableAjaxUrl = "rest/purchaseForecast/findAllPurchaseForecast";
	var loadMainTable = function() {
		var a = 0;
		App.getViewPort().width < App.getResponsiveBreakpoint("md") ? $(".page-header").hasClass("page-header-fixed-mobile")&& (a = $(".page-header").outerHeight(!0)): 
			$(".page-header").hasClass("navbar-fixed-top") ? a = $(".page-header").outerHeight(!0): $("body").hasClass("page-header-fixed")&& (a = 64);

			table = $("#sample_2").DataTable(
					{
						language : {
							aria : {
								sortAscending : ": 以升序排列此列",
								sortDescending : ": 以降序排列此列"
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
						                            { mData: 'materielNum' },
						                            { mData: 'materielName' },
						                            { mData: 'specifications' },
						                            { mData: 'amount' },
						                            { mData: 'comName' },
						                            { mData: 'deliveryDate'},
						                            { mData: 'deliveryAddress'},
						                            { mData: 'daysBeforeDelivery'},
						                            { mData: 'creator' }
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
							$state.go('purchaseForecast',{},{reload:true}); // 重新加载datatables数据
						},
						function(errResponse) {
							/*console.error('Error while deleting Users');*/
							alert(123);
						}

				);
			}
		}								
	};

	//生成采购计划
	$scope.newProcurementPlan = function() {
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
			toastr.warning("未勾选要生成采购计划的条目！");
		} else {
			$('#confirmModal').modal('show');// 打开确认删除模态框

			$scope.confirmNewProcurementPlan = function() {	
				$('#confirmModal').modal('hide');// 隐藏确认删除模态框
				PurchaseForecastService.savePurchaseForecast(ids).then(
						function(data) {
							
							$state.go('procurementPlan',{},{reload:true}); // 重新加载datatables数据
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


	//导出
	$scope.exportPurchaseForecast = function(){
   	 handle.blockUI("正在导出数据，请稍后"); 
   	 window.location.href=$rootScope.basePath+"/rest/purchaseForecast/exportPurchaseForecast";
   	 handle.unblockUI(); 
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


	var self = this;
	self.DemandPlanMateriel={serialNum:null,materielNum:'',materielName:'',specifications:'',comName:'',deliveryDate:''};
	self.purchaseForecastList=[];


	//查询所有的采购预测
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


