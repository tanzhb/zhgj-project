/**
 * 
 */

angular.module('MetronicApp').controller('NoticeController',['$rootScope','$scope','$state','$http','noticeService','$location','$compile','$stateParams','$sce',
                                                             function($rootScope,$scope,$state,$http,noticeService,$location,$compile,$stateParams,$sce) {
	 $scope.$on('$viewContentLoaded', function() {   
	    	// initialize core components
		    handle = new pageHandle();
	    	App.initAjax();
	    	if($location.path()!="/myNotice"){
	    		if(!isNull($stateParams.serialNum)){
	    			noticeInfo($stateParams.serialNum,$stateParams.taskId, $stateParams.comments);
	    		}
	    		if($("#summernote")!=undefined){
		    		$("#summernote").summernote({
		                height: 500,
		                lang:"zh-CN",
		                callbacks:{
			                onImageUpload: function(files, editor, $editable) {  
			                    uploadFile(files[0],editor,$editable);  
			                }
		                }  
		            });
	    		}
	    	}else{
	    		var type = handle.getCookie("d_type");
	 			if(type=="notices"){
		 				$('#notice_tab a:last').parent().addClass('active');
		 				$('#notice_tab a:first').parent().removeClass('active');
		 				$("#portlet_tab2_2").addClass("active");
		 				$("#portlet_tab2_1").removeClass("active");
		 				$scope.notices();
		 		}else if(type=="newNotice"){
		 				//loadTakeDelieryTable();
		 				$('#notice_tab a:first').parent().addClass('active');
		 				$('#notice_tab a:last').parent().removeClass('active');
		 				$("#portlet_tab2_1").addClass("active");
		 				$("#portlet_tab2_2").removeClass("active");
		 				createTable(5,1,true,$scope.params);
		 		}else{
			 			$('#notice_tab a:first').parent().addClass('active');
		 				$('#notice_tab a:last').parent().removeClass('active');
		 				$("#portlet_tab2_1").addClass("active");
		 				$("#portlet_tab2_2").removeClass("active");
		 				createTable(5,1,true,$scope.params);
		 		}	
	    	}
	    	
	    	if($stateParams.tabHref == '1'){//首页待办列表传过来的参数
 				$('#notice_tab a[data-target="#tab_25_2"]').tab('show');
 				//showDbTable();
 				 $scope.toDaiban();
 	 		 }else if($stateParams.tabHref == '2'){
 	 			    $('#notice_tab a[data-target="#tab_25_3"]').tab('show');
 	 			 	$scope.toYiban();
 	 		 }
	    	
	    	// set default layout mode
	    	$rootScope.settings.layout.pageContentWhite = true;
	        $rootScope.settings.layout.pageBodySolid = false;
	        $rootScope.settings.layout.pageSidebarClosed = false;
	       
	    	
	 });
	 
	 		var uploadFile = function(file, editor, $editable){
	 			$(".note-toolbar.btn-toolbar").append('正在上传图片');  
	 			var filename = false;  
	 			try{  
	 			filename = file['name'];  
	 			//alert(filename);  
	 			} catch(e){filename = false;}  
	 			if(!filename){$(".note-alarm").remove();}  
	 			//以上防止在图片在编辑器内拖拽引发第二次上传导致的提示错误  
	 			var ext = filename.substr(filename.lastIndexOf("."));  
	 			ext = ext.toUpperCase();  
	 			var timestamp = new Date().getTime();  
	 			//var name = timestamp+"_"+$("#summernote").attr('aid')+ext;  
	 			//name是文件名，自己随意定义，aid是我自己增加的属性用于区分文件用户  
	 			data = new FormData();  
	 			data.append("file", file);    
	 			  
	 			$.ajax({  
	 			data: data,  
	 			type: "POST",  
	 			url: ctx+"rest/fileOperate/uploadSingleFile", //图片上传出来的url，返回的是图片上传后的路径，http格式 
	 			cache: false,  
	 			processData : false,  
	 	        // 告诉jQuery不要去设置Content-Type请求头  
	 	        contentType : false,  
	 			success: function(data) {  
	 			//data是返回的hash,key之类的值，key是定义的文件名  
	 			//alert(data);  
	 			//把图片放到编辑框中。editor.insertImage 是参数，写死。后面的http是网上的图片资源路径。  
	 			//网上很多就是这一步出错。  
	 			$('#summernote').summernote('editor.insertImage',ctx+"rest/fileOperate/downloadFile?fileName="+data.filename);  
	 			  
	 			$(".note-alarm").html("上传成功,请等待加载");  
	 			setTimeout(function(){$(".note-alarm").remove();},3000);  
	 			},  
	 			error:function(){  
	 			$(".note-alarm").html("上传失败");  
	 			setTimeout(function(){$(".note-alarm").remove();},3000);  
	 			}  
	 			});  
	 		}
	 
	 		$scope.notices = function(){
	 			if(nTable==undefined){
	 				noticeTable();
	 			}else{
	 				nTable.ajax.reload();
	 			}
	 			
	 		}
	 		
	 		$scope.myNoticeList = function(){
	 			createTable(5,1,true,null);
	 		}
	 		
	 		 $('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
			        // 获取已激活的标签页的名称
			        var activeTab = $(e.target).text(); 
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
		    function createTable(pageSize,pageIndex,init,params){
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
				var id_count = nTable.$('input[name="serialNum"]:checked').length;
				if(id_count==0){
					toastr.warning("请选择您要编辑的记录");
				}else if(id_count>1){
					toastr.warning("只能选择一条数据进行编辑");
				}else{
					var data = nTable.row('.active').data();
					if(data.status == '1'){
						toastr.warning("该公告已发布，不能修改！");
						return;
					}else if(data.status != '0' && data.status != '2'){
						toastr.warning("该公告审核中，不能修改！");
						return;
					}
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
	 		
	 		
	 		var formCheck = function(){
	 			var noticeTitle = $("#noticeTitle").val();
	 			var checked = $("#noticeCheck").find("input:checked");
	 			var context = $('#summernote').summernote('code');
	 			if(isNull(noticeTitle)){
	 				toastr.warning("请填写标题");
	 				return false;
	 			}
	 		
	 			if(isNull(checked)||checked.length==0){
	 				toastr.warning("请选择公告范围");
	 				return false;
	 		    }
	 			
	 			if(isNull(context)||context=='<p><br></p>'){
	 				toastr.warning("请填写公告内容");
	 				return false;
	 			}else if(context>60000){
	 				toastr.warning("公告内容超出最大限度");
	 				return false;
	 			}
	 			
	 			return true;
	 		}
	        /**
	         * 发布公告
	         */
			$scope.saveNotice = function() {
				//if($('#noticeForm').valid()){
					if(!formCheck()){
						return;
					}
				    var context = $('#summernote').summernote('code');
				    var notice = createNoticeData();
					var promise = noticeService
							.saveNotice(notice);
					promise.then(function(data) {
						if(data.data == "1"){
							toastr.success("保存成功！");
							$state.go("myNotice");
						}else{
							toastr.error("保存失败！请联系管理员");
						}
						handle.unblockUI();
					}, function(data) {
						// 调用承诺接口reject();
						handle.unblockUI();
						toastr.error("保存失败！请联系管理员");
						console.log(data);
					});
				//}
			}; 
			
			var createNoticeData = function(){
				  	var context = $('#summernote').summernote('code');
				    notice = {};
				    notice.context = context;
				    notice.noticeTitle = $scope.param.noticeTitle;
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
				    return notice;
			}
			
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
	        	var id_count = nTable.$('input[name="serialNum"]:checked').length;
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
			var noticeInfo = function(serialNum,taskId,comments){
				var promise = noticeService.getNoticeInfo(serialNum);
        		promise.then(function(data){debugger;
        			//$scope.notice = data.data;
        			if(!isNull(serialNum)){
        				if($location.path()=="/noticeAdd"||$location.path()=="/noticeAdjustment"){
        					$('#summernote').summernote('code', data.data.context);
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
        				
        				$scope.param = {};
        				$scope.param.noticeTitle = data.data.noticeTitle;
        				$scope.param.serialNum = data.data.serialNum;
        				$scope.param.context = $sce.trustAsHtml(data.data.context);
        				$scope.param.updater = data.data.updater;
        				$scope.param.relaseDate = data.data.relaseDate;
        				if(!isNull(taskId)){
        	        		$("#serialNum").val(serialNum);//赋值给隐藏input，通过和不通过时调用
        					$("#taskId").val(taskId);//赋值给隐藏input，通过和不通过时调用
        					
        					if(comments == ""){
        						$("#comment_audit").html( "无评论");
        					}else $("#comment_audit").html(comments);
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
			
			$scope.myNoticeView = function(serialNum){
				var promise = noticeService.readMyNotice(serialNum);
        		promise.then(function(data){
        		},function(data){
        			//调用承诺接口reject();
        		});
				$state.go("noticeView",{serialNum:serialNum});
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
		       var nTable;
		       var noticeTable = function() {
		                a = 0;
		                App.getViewPort().width < App.getResponsiveBreakpoint("md") ? $(".page-header").hasClass("page-header-fixed-mobile") && (a = $(".page-header").outerHeight(!0)) : $(".page-header").hasClass("navbar-fixed-top") ? a = $(".page-header").outerHeight(!0) : $("body").hasClass("page-header-fixed") && (a = 64);
		                nTable = $("#noticeTable").DataTable({
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
		                    order: [[2, "desc"]],//默认排序列及排序方式
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
		                    pageLength: 15,//每页显示数量
		                    processing: true,//loading等待框
		                    bRetrieve : true,
//		                    serverSide: true,
		                   // ajax: "rest/notice/noticeList",//加载数据中
		                    ajax :{ "url":$rootScope.basePath
		  						+ "/rest/notice/noticeList",// 加载数据中收货列表的数据    
		  						"contentType": "application/json",
		  					    "type": "POST",
		  					    "data": function ( d ) {
		  					      return JSON.stringify( d );
		  					    }},
		                    "aoColumns": [
		                                 
		                                  { mData: 'serialNum' },
		                                  { mData: 'noticeNum' },
		                                  { mData: 'updateTime' },
		                                  { mData: 'publisher' },
		                                  { mData: 'noticeTitle' },
		                                  { mData: 'noticeType' },
		                                  { mData: 'publishTime' },
		                                  { mData: 'status' }
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
   							'targets' : 5,
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
							}
						}, {
   							'targets' : 4,
							'searchable' : false,
							'orderable' : false,
							'render' : function(data,
									type, row, meta) {
	  	  							if(data!=null){
	  	  							  return '<a href="javascript:;" ng-click="myNoticeView(\''+row.serialNum+'\')">'+data+'</a>';
	  	  							}
	  	  							return "";
							},
							"createdCell": function (td, cellData, rowData, row, col) {
								 $compile(td)($scope);
						       }
						}, {
   							'targets' : 3,
							'searchable' : false,
							'orderable' : false,
							'render' : function(data,
									type, row, meta) {
	  	  							if(!isNull(data)){
	  	  							  return data;
	  	  							}
	  	  							return "";
							}
						}, {
   							'targets' : 6,
							'searchable' : false,
							'orderable' : false,
							'render' : function(data,
									type, row, meta) {
	  	  							if(!isNull(data)){
	  	  							  return timeStamp2String(data);
	  	  							}
	  	  							return "";
							}
						}, {
   							'targets' : 7,
							'searchable' : false,
							'orderable' : false,
							'render' : function(data,
									type, row, meta) {
								if(!isNull(data)){
                          			if(data=="PENDING"||data=="WAITING_FOR_APPROVAL"){
  										return '<span  class="label label-sm label-warning ng-scope">审核中</span>';
  									}else if(data=="1"){
  										return '<span  class="label label-sm label-success ng-scope">已发布</span>';
  									}else if(data=="APPROVAL_FAILED"){
  										return '<span  class="label label-sm label-danger ng-scope">待调整</span>';
  									}else if(data=="0"){
  										return '<span  class="label label-sm label-danger ng-scope">待申请</span>';
  									}else{
  										return '<span  class="label label-sm label-info ng-scope">已取消</span>';
  									}
                          		}
								return "";
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
			        
			        
		/*******************************************申请JS START*******************************************/
			        
			        /**
					 * 去收货
					 */
					$scope.toApplyNotice = function(){
						var id_count = $('#noticeTable input[name="serialNum"]:checked').length;
						if(id_count==0){
							toastr.warning("请选择您要申请的公告");
						}else if(id_count>1){
							toastr.warning("只能选择一条数据进行申请");
						}else{
							if(nTable.row('.active').data().status == '1' ){
								showToastr('toast-top-center', 'warning', '该公告已经审核通过，无需再次申请！');
							}else if(nTable.row('.active').data().status != '0' && nTable.row('.active').data().status != '2'){
								showToastr('toast-top-center', 'warning', '该公告已经进入申请流程，不能进行再次申请！');
							}else{
								var serialNum = $('#noticeTable input[name="serialNum"]:checked').val();
								$state.go("noticeApply",{serialNum:serialNum});
							}
						}
					}
					
			        $scope.applyNotice = function(){
			        		var notice = {};
			        		notice.serialNum = $scope.param.serialNum;
			        		//notice.reason = $scope.param.reason;
							var promise = noticeService
							.applyNotice(notice);
							promise.then(function(data) {
								if(data.data == "1"){
									toastr.success("申请成功！");
									$state.go("myNotice");
								}else{
									toastr.error("申请失败！请联系管理员");
								}
								handle.unblockUI();
							}, function(data) {
								// 调用承诺接口reject();
								handle.unblockUI();
								toastr.error("申请失败！请联系管理员");
								console.log(data);
							});
			  		}
			  		
			  		/**
			  		 * 签收
			  		 */
			  		$scope.receiveNotice = function(){
			  			var id_count = $('#sample_n2 input[name="serialNum"]:checked').length;
						if(id_count==0){
							toastr.warning("请选择您要办理的记录");
						}else{
							
							var ids = $('#sample_n2 input[name="serialNum"]:checked').val();
							claimTask(ids, 'sample_n2');
						}
			  		}
			  		/**
					 * 去班里
					 */
					$scope.noticeAudit = function(){
						var id_count = $('#sample_n2 input[name="serialNum"]:checked').length;
						if(id_count==0){
							toastr.warning("请选择您要办理的记录");
						}else{
							
								var ids = $('#sample_n2 input[name="serialNum"]:checked').val();
								noticeService
								.getAuditInfos(ids)
								.then(
										function(result) {													
											
											var comments = ""//添加评论
											for (var i=0;i<result.commentList.length;i++){
												comments += "<tr><td>" + result.commentList[i].userName + "</td><td>" 
												+ timeStamp2String(result.commentList[i].time) + "</td><td>" + result.commentList[i].content + "</td></tr>";														
											}
											
											if(result.actionType == 'audit'){//审批流程
												$state.go('noticeAudit',{serialNum:result.notice.serialNum,taskId:ids,comments:comments});

											}else{
												$state.go('noticeAdjustment',{serialNum:result.notice.serialNum,taskId:ids,comments:comments});
											}
										},
										function(errResponse) {
											toastr.warning("申请失败！");
											console
													.error('Error while apply ap');
										}

								);
							}
							
							//$state.go("noticeAudit",{serialNum:serialNum});
					}
			  		
			  	    // 待办流程
					$scope.toDaiban = function() {
						$('#notice_tab a[data-target="#tab_25_2"]').tab('show');

						// 构建datatables开始***************************************
						if(apply_table!=undefined){
							apply_table.ajax.reload();
						}else{
							showDbTable();				
						}
						$("#buttons").hide();
						// 构建datatables结束***************************************

					};
					
					// 以办流程
					$scope.toYiban = function() {
						$('#notice_tab a[data-target="#tab_25_3"]').tab('show');
						
						// 构建datatables开始***************************************
						if(y_table!=undefined){
							y_table.ajax.reload();
						}else{
							showYbTable();		
						}		
						$("#buttons").hide();
						// 构建datatables结束***************************************
						
					};
					var apply_table;
			  		function showDbTable(){
				  	  
		  		      var tableAjaxUrl = ctx + "/rest/processAction/todoTask/"+ 'notice';// 加载待办列表数据
		  		    //  var loadApplyTable = function() {
		  		             // a = 0;
		  		              //App.getViewPort().width < App.getResponsiveBreakpoint("md") ? $(".page-header").hasClass("page-header-fixed-mobile") && (a = $(".page-header").outerHeight(!0)) : $(".page-header").hasClass("navbar-fixed-top") ? a = $(".page-header").outerHeight(!0) : $("body").hasClass("page-header-fixed") && (a = 64);
		  		        return          apply_table = $("#sample_n2").DataTable({
		  		                  language: {
		  		                      aria: {
		  		                          sortAscending: ": activate to sort column ascending",
		  		                          sortDescending: ": activate to sort column descending"
		  		                      },
		  		                      emptyTable: "空表",
		  		                      info: "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
		  		                      infoEmpty: "没有数据",
		  		                      // infoFiltered: "(filtered1 from _MAX_ total entries)",
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
		  		                  order: [[1, "asc"]],// 默认排序列及排序方式
		  		                  searching: true,// 是否过滤检索
		  		                  ordering:  true,// 是否排序
		  		                  lengthMenu: [[5, 10, 15, 30, -1], [5, 10, 15, 30, "All"]],
		  		                  pageLength: 5,// 每页显示数量
		  		                  processing: true,// loading等待框
		  		  // serverSide: true,
		  		                  ajax: tableAjaxUrl,// 加载数据中
		  		                  "aoColumns": [
		  		                                { mData: 'taskId' },
		  		                                { mData: 'assign' },
		  		                                { mData: 'businessType' },
		  		                                { mData: 'userName' },
		  		                                { mData: 'title' },
		  		                                { mData: 'taskName' },
		  		                                { mData: 'owner' },
		  		                                { mData: 'createTime' },
		  		                                { mData: 'suspended' }
		  		                          ],
		  		                 'aoColumnDefs' : [ {
		  		  							'targets' : 0,
		  		  							'searchable' : false,
		  		  							'orderable' : false,
		  		  						    'className' : 'dt-body-center',
		  		  							'render' : function(data,
		  		  									type, row, meta) {
		  		  								//return '<input type="radio" id="'+data+'"   name="serialNum" value="'+data+ '" />';
		  		  						return '<label class="mt-checkbox mt-checkbox-single mt-checkbox-outline">'+
		                                '<input type="checkbox" name="serialNum" class="checkboxes"  id="'+data+'" value="'+data+'"  />'+
		                                '<span></span></label>';
		  		  							}
		  		  						},{
		  		  							'targets' : 1,
		  		  							'searchable' : false,
		  		  							'orderable' : false,
		  		  							'render' : function(data,
		  		  									type, row, meta) {
		  		  								if(data=""){
		  		  									return "待签收";
		  		  								}else{
		  		  									return "待处理";
		  		  								}
		  		  							}
		  		  						},{
		  		  							'targets' : 2,
		  		  							'searchable' : false,
		  		  							'orderable' : false,
		  		  							'render' : function(data,
		  		  									type, row, meta) {
		  		  								if (data == "notice") {
													return "收货申请";
												} else{
													return data;
												}
		  		  							}
		  		  						},{
		  		  							'targets' : 3,
		  		  							'searchable' : false,
		  		  							'orderable' : false,
		  		  							'render' : function(data,
		  		  									type, row, meta) {
		  		  								if (data == undefined) {
		  		  									return "";
		  		  								} else {
		  		  									return data;
		  		  								}
		  		  							}
		  		  						},{
		  		  							'targets' : 4,
		  		  							'searchable' : false,
		  		  							'orderable' : false,
		  		  							'render' : function(data,
		  		  									type, row, meta) {
		  		  								if (data == undefined) {
		  		  									return "";
		  		  								} else{
		  		  									return data;
		  		  								}
		  		  							}
		  		  						},{
		  		  							'targets' : 5,
		  		  							'searchable' : false,
		  		  							'orderable' : false,
		  		  							'render' : function(data,
		  		  									type, row, meta) {
		  		  							return "<a class='trace' onclick=\"graphTrace('"
												+ row.processInstanceId + "','" + ctx 
												+ "')\" id='diagram' href='javascript:;' pid='"
												+ row.id
												+ "' pdid='"
												+ row.processDefinitionId
												+ "' title='see'>"
												+ data
												+ "</a>";
		  		  							}
		  		  						},{
		  		  							'targets' : 6,
		  		  							'searchable' : false,
		  		  							'orderable' : false,
		  		  							'render' : function(data,
		  		  									type, row, meta) {
			  		  							if (data != ''
														&& data != row.assign) {
													return row.assign
															+ " (原执行人："
															+ data
															+ ")";
												} else {
													return row.assign;
												}
		  		  							}
		  		  						},{
		  		  							'targets' : 7,
		  		  							'searchable' : false,
		  		  							'orderable' : false,
		  		  							'render' : function(data,
		  		  									type, row, meta) {
		  		  								if (data != null) {
													return timeStamp2String(data);
												} else
													return '';
		  		  							}
		  		  						},{
		  		  							'targets' : 8,
		  		  							'searchable' : false,
		  		  							'orderable' : false,
		  		  							'render' : function(data,
		  		  									type, row, meta) {
		  		  								if (data) {
													return "已挂起";
												} else {
													return "正常";
												}
		  		  							}
		  		  						} ]

		  		              }).on('order.dt',
		  		              function() {
		  		                  console.log('排序');
		  		              });

					        
		  		    //  };
			  		}
			  		
			  		var y_table;
			  		function showYbTable(){
			  			
			  			var tableAjaxUrl = ctx + "/rest/processAction/endTask/" + 'notice';// 加载待办列表数据
			  			//  var loadApplyTable = function() {
			  			// a = 0;
			  			//App.getViewPort().width < App.getResponsiveBreakpoint("md") ? $(".page-header").hasClass("page-header-fixed-mobile") && (a = $(".page-header").outerHeight(!0)) : $(".page-header").hasClass("navbar-fixed-top") ? a = $(".page-header").outerHeight(!0) : $("body").hasClass("page-header-fixed") && (a = 64);
			  			return y_table = $("#ybnTable").DataTable({
			  				language: {
			  					aria: {
			  						sortAscending: ": activate to sort column ascending",
			  						sortDescending: ": activate to sort column descending"
			  					},
			  					emptyTable: "空表",
			  					info: "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
			  					infoEmpty: "没有数据",
			  					// infoFiltered: "(filtered1 from _MAX_ total entries)",
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
			  				/*
			  				 * fixedHeader: {//固定表头、表底 header: !0, footer: !0, headerOffset: a },
			  				 */
			  				
			  				buttons : [
			  				           {
			  				        	   text : "办理",
			  				        	   className : "btn default",
			  				        	   action: function(e, dt, node, config) { 
			  				        		   if(table.rows('.selected').data().length == 0){
			  				        			   toastr.warning("请选择要办理的任务！");
			  				        		   }else{
			  				        			   var assign = table.row('.selected').data().assign;
			  				        			   var taskId = table.row('.selected').data().taskId;
			  				        			   var processInstanceId = table.row('.selected').data().processInstanceId;
			  				        			   handleTask(assign, taskId, processInstanceId);
			  				        		   }
			  				        	   }
			  				           },
			  				           {
			  				        	   text : "签收",
			  				        	   className : "btn default",
			  				        	   action: function(e, dt, node, config) { 
			  				        		   if(table.rows('.selected').data().length == 0){
			  				        			   toastr.warning("请选择要签收的任务！");
			  				        		   }else{
			  				        			   var taskId = table.row('.selected').data().taskId;
			  				        			   claimTask(taskId, 'sample_n2');
			  				        		   }								
			  				        	   }
			  				           },
			  				           {
			  				        	   text : "转办",
			  				        	   className : "btn default"
			  				           },
			  				           {
			  				        	   text : "委派",
			  				        	   className : "btn default"
			  				           },
			  				           {
			  				        	   text : "跳转",
			  				        	   className : "btn default"
			  				           } ],
			  				           order: [[1, "asc"]],// 默认排序列及排序方式
			  				           searching: true,// 是否过滤检索
			  				           ordering:  true,// 是否排序
			  				           lengthMenu: [[5, 10, 15, 30, -1], [5, 10, 15, 30, "All"]],
			  				           pageLength: 5,// 每页显示数量
			  				           processing: true,// loading等待框
			  				           // serverSide: true,
			  				           ajax: tableAjaxUrl,// 加载数据中
			  				           "aoColumns": [
			  				                        /* { mData: 'taskId' },*/
			  				                         { mData: 'userName' },
			  				                         { mData: 'title' },
			  				                         { mData: 'startTime' },
			  				                         { mData: 'claimTime' },
			  				                         { mData: 'endTime' },
			  				                         { mData: 'deleteReason' },
			  				                         { mData: 'version' },
			  				                         { mData: 'revoke' }
			  				                         ],
			  				                         'aoColumnDefs' : [ /*{
			  				                        	 'targets' : 0,
			  				                        	 'searchable' : false,
			  				                        	 'orderable' : false,
			  				                        	 'className' : 'dt-body-center',
			  				                        	 'render' : function(data,
			  				                        			 type, row, meta) {
			  				                        		 return '<input type="radio" id="'+data+'"   name="serialNum" value="'+data+ '">';
			  				                        	 },
			  				                        	 "createdCell": function (td, cellData, rowData, row, col) {
			  				                        		 $compile(td)($scope);
			  				                        	 }
			  				                         },*/{
			  				                        	 'targets' : 2,
			  				                        	 'searchable' : false,
			  				                        	 'orderable' : false,
			  				                        	 'render' : function(data,
			  				                        			 type, row, meta) {
			  				                        		return timeStamp2String(data);
			  				                        	 }
			  				                         },{
			  				                        	 'targets' : 3,
			  				                        	 'searchable' : false,
			  				                        	 'orderable' : false,
			  				                        	 'render' : function(data,
			  				                        			 type, row, meta) {
			  				                        		if(data != null){
			  						                			return timeStamp2String(data);
			  						                		}else{
			  						                			return "无需签收";
			  						                		}
			  				                        	 }
			  				                         },{
			  				                        	 'targets' : 4,
			  				                        	 'searchable' : false,
			  				                        	 'orderable' : false,
			  				                        	 'render' : function(data,
			  				                        			 type, row, meta) {
			  				                        		if (data != null) {
			  													return timeStamp2String(data);
			  												} else
			  													return '';
			  				                        	   }
			  				                         },{
			  				                        	 'targets' : 7,
			  				                        	 'searchable' : false,
			  				                        	 'orderable' : false,
			  				                        	 'render' : function(data,
			  				                        			 type, row, meta) {
			  				                        		return "<a href='javascript:void(0);' onclick=\"revoke('"+row.taskId+"','"+row.processInstanceId+"','ybnTable')\">撤销</a>";
			  				                        	 }
			  				                         } ]
			  				
			  			}).on('order.dt',
			  					function() {
			  				console.log('排序');
			  			})	  			
			  			//  };
			  		}
			  		

			  		//审批通过
			  		$scope.apPass = function() {
			  		   
			  		    var mydata={"serialNum":$("#serialNum").val(),"content":$("#content").val(),
			  					"isPass":true, "taskId":$("#taskId").val()};
			  		    var _url = ctx + "rest/notice/complete";
			  		    doVacation(_url, mydata,2);
			  		};
			  		//审批不通过
			  		$scope.apUnPass = function() {
			  			var mydata={"serialNum":$("#serialNum").val(),"content":$("#content").val(),
			  					"isPass":false, "taskId":$("#taskId").val()};
			  			var _url = ctx + "rest/notice/complete";
			  			doVacation(_url, mydata,1);
			  		};
			  		
			  		//重新申请
			  		$scope.reApply = function() {
			  			var params = {};
			  			
			  			params = createNoticeData();
						debugger;
			  			var mydata={"serialNum":$("#serialNum").val(),"content":$("#content").val(),
			  					"isPass":true, "taskId":$("#taskId").val(),"params":JSON.stringify(params)};
			  			var _url = ctx + "rest/notice/complete";
			  			doVacation(_url, mydata,2);
			  		};
			  		//审批不通过
			  		/*$scope.apUnPass = function() {
			  			var mydata={"serialNum":$("#serialNum").val(),"content":$("#content").val(),
			  					"isPass":false, "taskId":$("#taskId").val()};
			  			var _url = ctx + "rest/notice/complete";
			  			doVacation(_url, mydata,1);
			  		};*/
			  		
			  	//办结待办流程
			  		function doVacation(_url, mydata,target){
			  	        $.ajax( {
			  		        url : _url,
			  		        dataType:"text",
			  		        type: 'POST',
			  		        data : mydata,
			  		        success : function(data) {
			  		        	//$("#dbTable").DataTable().ajax.reload();
			  		        	showToastr('toast-bottom-right', 'success', data);
			  		        	$state.go("myNotice",{tabHref:target});
			  		        }
			  		     });
			  		}
			  		
			  		$scope.closeAuditDialogue = function() {
			  			$state.go("myNotice");
			  		};
			        
		/*******************************************申请JS END*******************************************/

}]); 
