/* Setup general page controller */
angular.module('MetronicApp').controller('saleFrameController', ['$rootScope', '$scope', 'settings','$filter',
    '$state',"$stateParams",'$compile','$location','FileUploader', function($rootScope, $scope, settings,$filter,$state,$stateParams,$compile,$location,FileUploader) {
    $scope.$on('$viewContentLoaded', function() {   
    	handle = new pageHandle();
		App.initAjax();

		// set default layout mode
		$rootScope.settings.layout.pageContentWhite = true;
		$rootScope.settings.layout.pageBodySolid = false;
		$rootScope.settings.layout.pageSidebarClosed = false;

//		loadMainTable();//加载销售预测列表
    });
    

	var table;
	var tableAjaxUrl = "rest/procurementPlan/findProcurementPlanMateriel";//查询该供应商/贸易商相关联(平台审核通过的采购计划采购清单物料)
	var loadMainTable = function() {
		var a = 0;
		App.getViewPort().width < App.getResponsiveBreakpoint("md") ? $(".page-header").hasClass("page-header-fixed-mobile")&& (a = $(".page-header").outerHeight(!0)): 
			$(".page-header").hasClass("navbar-fixed-top") ? a = $(".page-header").outerHeight(!0): $("body").hasClass("page-header-fixed")&& (a = 64);

			table = $("#sample_saleForecast").DataTable(
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
						                            { mData: 'buyCount' },
						                            { mData: 'deliveryDate'},
						                            { mData: 'deliveryAddress'},
						                            { mData: 'daysBeforeDelivery'},
						                            { mData: 'remark'},
						                            { mData: 'updateTime' }
						                            ],
						                            'aoColumnDefs': [ {
						                            	'targets' : 0,
						                            	'searchable' : false,
						                            	'orderable' : false,
						                            	'className' : 'dt-body-center',
						                            	'render' : function(data,type, full, meta) {
						                            		return "";
						                            		/*return '<label class="mt-checkbox  mt-checkbox-outline">' +
						    								'<input type="checkbox" class="checkboxes"  id="'+data+'"  value="'+data+'"   ng-click="choosePurchaseForecast(\''+full.serialNum+'\')"/>' +
						    								'<span></span></label>';*/
//							                            		return '<label class="mt-checkbox mt-checkbox-single mt-checkbox-outline"><input type="checkbox" name="id[]" value="'+ $('<div/>').text(data).html()+ '"><span></span></label>';
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
	}
 
	 	       
}]);


