/**
 * 
 */

angular.module('MetronicApp').controller('NoticeController',['$rootScope','$scope','$state','$http','noticeService','$location','$compile','$stateParams',
                                                             function($rootScope,$scope,$state,$http,noticeService,$location,$compile,$stateParams) {
	 $scope.$on('$viewContentLoaded', function() {   
	    	// initialize core components
		    handle = new pageHandle();
	    	App.initAjax();
	    	if($location.path()=="/noticeAdd"||$location.path()=="/noticeView"){debugger;
	    		if(!isNull($stateParams.serialNum)){
	    			noticeInfo($stateParams.serialNum);
	    		}
	    		$("#summernote").summernote({
	                height: 400
	            });
	    	}
	    	
	    	var type = handle.getCookie("d_type");debugger;
 			if(type=="notices"){
	 				$('#notice_tab a:last').parent().addClass('active');
	 				$('#notice_tab a:first').parent().removeClass('active');
	 				$("#portlet_tab2_2").addClass("active");
	 				$("#portlet_tab2_1").removeClass("active");
	 				$scope.notices();
	 		}else{
	 				//loadTakeDelieryTable();
	 				$('#notice_tab a:first').parent().addClass('active');
	 				$('#notice_tab a:last').parent().removeClass('active');
	 				$("#portlet_tab2_1").addClass("active");
	 				$("#portlet_tab2_2").removeClass("active");
	 				createTable(5,1,true,$scope.params);
	 		}	
	    	// set default layout mode
	    	$rootScope.settings.layout.pageContentWhite = true;
	        $rootScope.settings.layout.pageBodySolid = false;
	        $rootScope.settings.layout.pageSidebarClosed = false;
	       
	    	
	 });
	 
	 		$scope.notices = function(){
	 			if(table==undefined){
	 				noticeTable();
	 			}else{
	 				table.ajax.reload();
	 			}
	 			
	 		}
	 		
	 		$scope.myNoticeList = function(){
	 			createTable(5,1,true,null);
	 		}
	 		
	 		 $('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
			        // 获取已激活的标签页的名称
			        var activeTab = $(e.target).text(); debugger;
			        // 获取前一个激活的标签页的名称
			       // var previousTab = $(e.relatedTarget).text(); 
			        var absurl = $location.absUrl();
			        $("#tip").text(activeTab);
			        if(activeTab=="公告列表"){
			        	handle.addCookie("d_type","notices",24);
			        }else if(activeTab=="最新公告"){
			        	handle.addCookie("d_type","newNotice",24);
			        }
			});
	 
	 
			 /**
		      * 创建我的公告列表
		      */
		    function createTable(pageSize,pageIndex,init,params){debugger;
		 	 //初始化表格数据
		 	   handle.blockUI(null,"#myNotice");
			    	var promise = noticeService.createTable(pageSize,pageIndex,params);
			    	promise.then(function(data){
			    			$scope.noticeList = data.data.result;
			    			data.data.params=params;
			    			handle.createPage("#myNotice",data.data,"rest/demandPlan/demandPlanList",createTable,init);
			            },function(data){
			               //调用承诺接口reject();
			         });
		    }
	 
	 		/**
	 		 * 跳转至新增页面
	 		 */
	 		$scope.toAddNotice = function(){
	 			$state.go("noticeAdd");
	 		}
	 		
	 		/**
	 		 * 跳转至编辑页面
	 		 */
	 		$scope.toEditNotice = function(){
	 		// Iterate over all checkboxes in the table
				var id_count = table.$('input[name="serialNum"]:checked').length;
				if(id_count==0){
					toastr.warning("请选择您要编辑的记录");
				}else if(id_count>1){
					toastr.warning("只能选择一条数据进行编辑");
				}else{
					var serialNum = table.$('input[name="serialNum"]:checked').val();
					$state.go("noticeAdd",{serialNum:serialNum});
				}
	 		}
	 	
/*	 		*//**
	 		 * 跳转至编辑页面
	 		 *//*
	 		$scope.toAddNotice = function(){
	 			
	 		}
*/	 		
	        /**
	         * 发布公告
	         */
			$scope.saveNotice = function() {
				//if($('#noticeForm').valid()){
				    var context = $('#summernote').summernote('code');
				    notice = {};
				    notice.context = context;
				    notice.title = $scope.param.title;debugger;
				    if(!isNull($scope.param.serialNum)){
				    	notice.serialNum = $scope.param.serialNum;
				    }
				    var checked = $("#noticeCheck").find("input:checked");
				    var vals=[];
				    if(!isNull(checked)){
				    	for(var i=0;i<checked.length;i++){
				    		vals[i] = checked[i].value;
					    }
				    }
				    notice.noticeType = vals.join();
					var promise = noticeService
							.saveNotice(notice);
					promise.then(function(data) {
						if(data.data == "1"){
							toastr.success("发布成功！");
							$state.go("myNotice");
						}else{
							toastr.error("发布失败！请联系管理员");
						}
						handle.unblockUI();
					}, function(data) {
						// 调用承诺接口reject();
						handle.unblockUI();
						toastr.error("发布失败！请联系管理员");
						console.log(data);
					});
				//}
			}; 
			
			/**
			 * 取消发布
			 */
			$scope.cancelNotice = function(){
				$state.go("myNotice");
			}
			
		    /**
	         * 批量删除
	         */
	        $scope.deleteNoticeBatch=function () {
	        	var id_count = table.$('input[name="serialNum"]:checked').length;
				if(id_count==0){
					toastr.warning("请选择您要删除的记录");
					return;
				}
	        	handle.confirm("确定删除吗？",function(){
	        		var ids = '';
					// Iterate over all checkboxes in the table
					table.$('input[name="serialNum"]').each(
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
	        		handle.blockUI();
	        		var promise = noticeService.deleteNoticeBatch(ids);
	        		promise.then(function(data){
	        			toastr.success("删除成功");
	        			handle.unblockUI();
	        			table.ajax.reload(); // 重新加载datatables数据
	        			/*$state.go('company',{},{reload:true}); */
	        		},function(data){
	        			//调用承诺接口reject();
	        		});
	        		
	        	});
	        	
	        };


			/**
			 * 获取公告信息
			 */
			var noticeInfo = function(serialNum){
				var promise = noticeService.getNoticeInfo(serialNum);
        		promise.then(function(data){debugger;
        			//$scope.notice = data.data;
        			if(!isNull(serialNum)){
        				$('#summernote').summernote('code', data.data.context);
        				$scope.param = {};
        				$scope.param.title = data.data.title;
        				$scope.param.serialNum = data.data.serialNum;
        				 var checked = $("#noticeCheck").find("input");
     				    var vals=[];
     				    if(!isNull(checked)){
     				    	for(var i=0;i<checked.length;i++){
     				    		vals[i] = checked[i].value;
     					    }
     				    }
        				if(!isNull(data.data.noticeType)){
        					var arr = data.data.noticeType.split(",");
        					for(var i=0; i<arr.length; i++){
        						for(var j=0;j<checked.length;j++){
        							if(arr[i] == checked[j].value){
        								checked[j].checked = true;
        							}
         					    }
        					}
        				}
        			}
        		},function(data){
        			//调用承诺接口reject();
        		});
			}
			
			$scope.delHtmlTag = function(str){
				return delHtmlTag(str);
			}
			
			
			$scope.deleteMyNotice = function(serialNum){
	        	handle.confirm("确定删除吗？",function(){
	        		handle.blockUI();
	        		var promise = noticeService.deleteMyNotice(serialNum);
	        		promise.then(function(data){
	        			toastr.success("删除成功");
	        			handle.unblockUI();
	        			createTable(5,1,true,null); // 重新加载datatables数据
	        		},function(data){
	        			//调用承诺接口reject();
	        		});
	        		
	        	});
			}


		 // 页面加载完成后调用，验证输入框
			$scope.$watch('$viewContentLoaded', function() {  
							var e = $("#noticeForm"),
					        r = $(".alert-danger", e),
					        i = $(".alert-success", e);
					        e.validate({
					            errorElement: "span",
					            errorClass: "help-block help-block-error",
					            focusInvalid: !1,
					            ignore: "",
					            messages: {
					            	inOutNum:{required:"入库单号不能为空！"},
					            	takeDeliverSerial:{required:"收货单号不能为空！"},
					            	stockDate:{required:"入库日期不能为空！"},
					            	operator:{required:"操作员不能为空！"},
					            	contactNum:{required:"联系方式不能为空！"},
					            	stockCount:{required:"入库数量不能为空！",digits:"发货数量必须为数字！"},
					            	warehouseSerial:{required:"仓库不能为空！"}
					            	//positionSerial:{required:"库位不能为空！"}
					            },
					            rules: {
					            	inOutNum: {
					                    required: !0
					                },
					                takeDeliverSerial: {
					                    required: !0
					                },
					                stockDate: {
					                	required: !0
					                },
					                operator: {
					                	required: !0
					                },
					                warehouseSerial: {
					                	required: !0
					                },
					                /*positionSerial: {
					                	required: !0
					                },*/
					                stockCount: {
					                	required: !0,
					                	digits:!0,
					                	StockInNumCheck:!0
					                },
					                contactNum: {
					                	//required: !0,
					                	isPhone: !0
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
	         
	       

			
			
		
			
			   
		       /***选择收货列表初始化START***/
		       var table;
		       var noticeTable = function() {
		                a = 0;
		                App.getViewPort().width < App.getResponsiveBreakpoint("md") ? $(".page-header").hasClass("page-header-fixed-mobile") && (a = $(".page-header").outerHeight(!0)) : $(".page-header").hasClass("navbar-fixed-top") ? a = $(".page-header").outerHeight(!0) : $("body").hasClass("page-header-fixed") && (a = 64);
		                table = $("#noticeTable").DataTable({
		                    language: {
		                        aria: {
		                            sortAscending: ": activate to sort column ascending",
		                            sortDescending: ": activate to sort column descending"
		                        },
		                        emptyTable: "空表",
		                        info: "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
		                        infoEmpty: "没有数据",
		                        //infoFiltered: "(filtered1 from _MAX_ total entries)",
		                        lengthMenu: "每页显示 _MENU_ 条数据",
		                        search: "查询:",
		                        zeroRecords: "抱歉， 没有找到！",
		                        paginate: {
		                            "sFirst": "首页",
		                            "sPrevious": "前一页",
		                            "sNext": "后一页",
		                            "sLast": "尾页"
		                         }
		                    },
		    /*                fixedHeader: {//固定表头、表底
		                        header: !0,
		                        footer: !0,
		                        headerOffset: a
		                    },*/
		                    order: [[1, "asc"]],//默认排序列及排序方式
		                    bRetrieve : true,
		  					'scrollX': false,
		  					  buttons: [
		  				                {
		  				                	 extend: "print",
		  					                 className: "btn dark btn-outline"
		  				                }
		  				            ],
		                    searching: true,//是否过滤检索
		                    ordering:  true,//是否排序
		                    lengthMenu: [[5, 10, 15, 30, -1], [5, 10, 15, 30, "All"]],
		                    pageLength: 5,//每页显示数量
		                    processing: true,//loading等待框
		                    bRetrieve : true,
//		                    serverSide: true,
		                   // ajax: "rest/takeDelivery/takeDeliveryList",//加载数据中
		                    ajax :{ "url":$rootScope.basePath
		  						+ "/rest/notice/noticeList",// 加载数据中收货列表的数据    
		  						"contentType": "application/json",
		  					    "type": "POST",
		  					    "data": function ( d ) {
		  					      return JSON.stringify( d );
		  					    }},
		                    "aoColumns": [
		                                 
		                                  { mData: 'serialNum' },
		                                  { mData: 'noticeType' },
		                                  { mData: 'title' },
		                                  { mData: 'creator' },
		                                  { mData: 'updater' },
		                                  { mData: 'updateTime' }
		                            ],
		                   'aoColumnDefs' : [ {
   							'targets' : 0,
							'searchable' : false,
							'orderable' : false,
							'className' : 'dt-body-center',
							'render' : function(data,
									type, row, meta) {
	  	  								/*return '<input  type="checkbox" id='+data+'   name="serialNum" value="'
											+ $('<div/>')
													.text(
															data)
													.html()
											+ '">';*/
	  	  							return '<label class="mt-checkbox mt-checkbox-single mt-checkbox-outline">'+
                                 '<input type="checkbox"  name="serialNum" class="checkboxes"  id="'+data+'" value="'+data+'" data-set="#noticeTable .checkboxes" />'+
                                 '<span></span>'+
                             '</label>';
	
							},
							"createdCell": function (td, cellData, rowData, row, col) {
								 $compile(td)($scope);
						       }
						}, {
   							'targets' : 1,
							'searchable' : false,
							'orderable' : false,
							'render' : function(data,
									type, row, meta) {
	  	  							if(data!=null){
	  	  							  var arr = data.split(",");
	  	  							  var show_arr = [];
	  	  							  if(arr!=null){
	  	  								  for(var i in arr){
	  	  									  if(arr[i]=='0'){
	  	  										  show_arr[i] = '公司内部';
	  	  									  }
	  	  									 if(arr[i]=='1'){
	  	  										  show_arr[i] = '客户';
	  	  									  }
	  	  									 if(arr[i]=='2'){
	  	  										 show_arr[i] = '供应商';
	  	  									 }
	  	  								  }
	  	  								  return show_arr.join("/");
	  	  							  }
	  	  							  
	  	  							}
	  	  							return "";
							},
							"createdCell": function (td, cellData, rowData, row, col) {
								 $compile(td)($scope);
						       }
						}]

		                }).on('order.dt',
		                function() {
		                    console.log('排序');
		                }).on('page.dt', 
		                function () {
		              	  console.log('翻页');
		  	          }).on('draw.dt',function() {
		  	        	//  checkedIdHandler();
		  	          });
		            };
		            
		            $("#noticeTable").find(".group-checkable").change(function() {
			            var e = jQuery(this).attr("data-set"),
			            t = jQuery(this).is(":checked");
			            jQuery(e).each(function() {
			                t ? ($(this).prop("checked", !0), $(this).parents("tr").addClass("active")) : ($(this).prop("checked", !1), $(this).parents("tr").removeClass("active"))
			            })
			        }),
			        $("#noticeTable").on("change", "tbody tr .checkboxes",
			        function() {
			            $(this).parents("tr").toggleClass("active")
			        })
		            /***收货列表初始化END***/

}]); 
