/* Setup general page controller */
angular.module('MetronicApp').controller('materielController', ['$rootScope', '$scope', 'settings','materielService',
    '$state',"$stateParams",'$compile','$location','FileUploader', function($rootScope, $scope, settings,materielService,$state,$stateParams,$compile,$location,FileUploader) {
    $scope.$on('$viewContentLoaded', function() {   
    	// initialize core components
    	App.initAjax();

    	// set default layout mode
    	$rootScope.settings.layout.pageContentWhite = true;
        $rootScope.settings.layout.pageBodySolid = false;
        $rootScope.settings.layout.pageSidebarClosed = false;
        handle = new pageHandle();
        if($state.current.name=="materiel"){
        	loadMainTable();//加载物料列表
        	
        	loadTree();//加载物料树
        	}else{
        	$('.date-picker').datepicker({
				rtl: App.isRTL(),
				orientation: "left",
				autoclose: true,
				dateFormat:"yyyy-mm-dd",
				language: "zh-CN"
        	})
        	//初始化日期控件
        	
        	diyFormiCheck();//初始化checkbox控件
        	
        	$scope.opration = {};
        	//加载数据
        	if($stateParams.serialNum){
        		$scope.opration = '修改';
        		$scope.getMaterielInfo($stateParams.serialNum)
        	}else{
        		$scope.opration = '新增';
        		$scope.materiel = {}
        		$rootScope.setNumCode("DL",function(newCode){//
        			$scope.materiel.materielNum = newCode;//物料编码
        		});
        		$rootScope.setNumCode("PA",function(newCode){//
        			$scope.materiel.packageNum = newCode;//包装编号
        		});
        		
        		
        		//加载物料分类
            	$scope.queryCategoryListByParent('frist','0');
            	
            	//加载功能分类
            	$scope.queryFunctionListByParent('1');
            	setTimeout($scope.multiselectInit, 2000)
        	}
        	
        	if($stateParams.view==1){//切换为查看
        		$scope.materielInput = true;
		    	$scope.materielShow = true;
		    	$scope.materielPackageInput = true;
   		    	$scope.materielPackageShow = true;
   		    	$scope.BOMInfoInput = true;
   		    	$scope.BOMInfoShow = true;
   		    	$scope.fileInfoInput = true;
   		    	$scope.fileInfoShow = true;
   		    	$scope.supplyMaterielInfoInput = true;
   		    	$scope.supplyMaterielInfoShow = true;
   		    	$scope.buyMaterielInfoInput = true;
   		    	$scope.buyMaterielInfoShow = true;
   		    	$scope.opration = '查看';
		    }
        	$scope.getSuppliers();//加载供应商
        	
        	$scope.getBuys(); //加载采购商

        	validateInit();//加载表单验证控件

        	validatePackageInit();//加载包装信息验证控件
       	
        	selectParentMateriel();//选择物料表格初始化
        	
        	validateBOMInit();//bom表单初始化
        	
        	validateFileInit();//file表单初始化
        	
        	validateSupplyMaterielInit();//供应商表单初始化
       	
        	$scope.BOM =[{}];//bom初始化
        	
/*        	$scope.file =[{}];//附件初始化*/
        }
    });
    
    $scope.save  = function() {
    	if($('#form_sample_1').valid()){
    		if($scope.materiel.manufactureDate=='') {//日期为空的处理
    			$scope.materiel.manufactureDate=null;
    		}
    		if($scope.materiel.parentMaterielSerial=='') {//上级物料为空的处理
    			$scope.materiel.parentMaterielSerial=null;
    		}
    		
    		//保存数据处理
    		$scope.materiel.parentMateriel=null;
    		$scope.materiel.createTime=null;
    		$scope.materiel.updateTime=null;
    		//**********//
    		$rootScope.judgeIsExist("materiel",$scope.materiel.materielNum, $scope.materiel.serialNum,function(result){
    			var 	isExist = result;
    		debugger;
    		if(isExist){
    			toastr.error('物料编号重复！');
    			return;
    		}else{
    			//物料属性转换
    			if(!isNull($scope.materiel.materielAttribute)){
    				$scope.materiel.materielAttribute = $scope.materiel.materielAttribute.toString();
    			}
    			materielService.save($scope.materiel).then(
    	       		     function(data){
    	       		    	toastr.success('数据保存成功！');
    	       		    	$location.search({serialNum:data.serialNum,view:1});
    	       		    	$scope.materielInput = true;
    	    		    	$scope.materielShow = true;
    	       		     },
    	       		     function(error){
    	       		         $scope.error = error;
    	       		         toastr.error('数据保存出错！');
    	       		     }
    	       		 );
    		}
    		
    		});
    		
    	}
    	
    }; 	
    
    $scope.cancel  = function() {//取消编辑
    	if($scope.materiel.serialNum==null || $scope.materiel.serialNum=='') {//如果是取消新增，返回列表页面
    		$state.go("materiel");
    		return;
		}
    	$scope.getMaterielInfo($scope.materiel.serialNum);
    	$scope.materielInput = true;
	    $scope.materielShow = true;
    };
    
    $scope.edit  = function() {//进入编辑
    	$scope.materielInput = false;
	    $scope.materielShow = false;
    };
    
    
    
    $scope.savePackage  = function() {//保存包装信息
    	if($scope.materiel.serialNum==null||$scope.materiel.serialNum=='') {//上级物料为空的处理
    		toastr.error('请先保存基本信息！');return
		}
    	if($('#form_sample_1').valid()&&$('#form_sample_2').valid()){
    		if($scope.materiel.manufactureDate=='') {//日期为空的处理
    			$scope.materiel.manufactureDate=null;
    		}
    		if($scope.materiel.parentMaterielSerial=='') {//上级物料为空的处理
    			$scope.materiel.parentMaterielSerial=null;
    		}

    		//保存数据处理
    		$scope.materiel.parentMateriel=null;
    		$scope.materiel.createTime=null;
    		$scope.materiel.updateTime=null;
    		//**********//
    		$rootScope.judgeIsExist("materielPackage",$scope.materiel.packageNum, $scope.materiel.serialNum,function(result){
    			var 	isExist = result;
    		debugger;
    		if(isExist){
    			toastr.error('包装编号重复！');
    			return;
    		}else{
    			materielService.save($scope.materiel).then(
    	       		     function(data){
    	       		    	toastr.success('数据保存成功！');
    	       		    	$location.search({serialNum:data.serialNum,view:1});
    	       		    	$scope.materielInput = true;
    	    		    	$scope.materielShow = true;
    	       		     },
    	       		     function(error){
    	       		         $scope.error = error;
    	       		         toastr.error('数据保存出错！');
    	       		     }
    	       		 );
    		}
    		
    		});
    		/*materielService.save($scope.materiel).then(
       		     function(data){
       		    	toastr.success('数据保存成功！');
       		    	$location.search({serialNum:data.serialNum,view:1});
       		    	$scope.materiel=data;
       		    	$scope.materielPackageInput = true;
       		    	$scope.materielPackageShow = true;
       		     },
       		     function(error){
       		    	toastr.error('数据保存出错！');
       		         $scope.error = error;
       		     }
       		 );*/
    	}
    	
    }; 	
    
    $scope.cancelPackage  = function() {//取消编辑包装信息
    	$scope.getMaterielInfo($scope.materiel.serialNum);
    	$scope.materielPackageInput = true;
	    $scope.materielPackageShow = true;
    };
    
    $scope.editPackage  = function() {//进入编辑包装信息
    	$scope.materielPackageInput = false;
	    $scope.materielPackageShow = false;
    };
    
/*	var initList = function(start,limit) {
    	materielService.findList(start,limit).then(
    		     function(data){
    		    	 $scope.materielList = data;
    		     },
    		     function(error){
    		         $scope.error = error;
    		     }
    		 );
    	};*/
    /**
     * 点击CheckBox加载物料信息
     */	
    $scope.getMaterielInfo_  = function(serialNum) {
    	//tab内容置为空
    	$scope.materiel = null;
    	$scope.BOM = null;
    	$scope.file = null;
    	$scope.supplyMateriel = null;
    	$scope.buyMateriel = null;
    	if($("#"+serialNum).is(':checked')){//选中时加载
    		$scope.getMaterielInfo(serialNum);
    	}
    }; 
    
    /**
     * 获取供应商信息
     */	
    $scope.getSuppliers  = function(serialNum) {
    	//tab内容置为空
    	$scope.suppliers = null;
    	materielService.getSuppliers().then(
     		     function(data){
     		    	$scope.suppliers=data.data;
     		    	/*setTimeout(function () {
            			$('select[name="supplyComId"]').selectpicker({
                            showSubtext: true,
                            size : 5
                        });
            			$('select[name="supplyComId"]').selectpicker('refresh');//刷新插件
            			
                    }, 100);*/
     		     },
     		     function(error){
     		         $scope.error = error;
     		     }
     		 );
    }; 
    
    /**
     * 获取采购商信息
     */	
    $scope.getBuys  = function(serialNum) {
    	//tab内容置为空
    	$scope.buys = null;
    	materielService.getBuys().then(
     		     function(data){
     		    	$scope.buys=data.data;
     		   	/*setTimeout(function () {
        			$('select[name="buyComId"]').selectpicker({
                        showSubtext: true,
                        size : 5
                    });
        			$('select[name="buyComId"]').selectpicker('refresh');//刷新插件
        			
                }, 100);*/
     		     },
     		     function(error){
     		         $scope.error = error;
     		     }
     		 );
    }; 
    /**
     * 获取物料信息
     */	
    $scope.getMaterielInfo  = function(serialNum) {
    	materielService.getMaterielInfo(serialNum).then(
      		     function(data){
      		    	$scope.materiel=data.materiel;
      		    	if(!isNull($scope.materiel.materielAttribute)){
      		    		$scope.materiel.materielAttribute = $scope.materiel.materielAttribute.split(',');
      		    	}
      		    	
      		    	if(!isNull(data.BOM)){
 	        			$scope.BOM = data.BOM;
 	        			_index = $scope.BOM.length;
 	        		}
      		    	if(!isNull(data.file)){
 	        			$scope.file = data.file;
 	        			_fileIndex = $scope.file.length;
 	        		}
      		    	if(!isNull(data.supplyMateriel)){
 	        			$scope.supplyMateriel = data.supplyMateriel;
 	        			_supplyMaterielIndex = $scope.supplyMateriel.length;
 	        		}
      		    	if(!isNull(data.buyMateriel)){
 	        			$scope.buyMateriel = data.buyMateriel;
 	        			_buyMaterielIndex = $scope.buyMateriel.length;
 	        		}
      		    	
      		    	if($state.current.name=="addMateriel"){
      		    	//加载物料分类（必须逐步加载）
          	        	materielService.queryCategoryListByParent('0').then(
    		        			function(data){
		          		    		$scope.fristCategoryList = data;
		          		    		if(!isNull($scope.materiel.type)){
		          		    			materielService.queryCategoryListByParent($scope.materiel.type).then(
		            		        			function(data){
		            		        				$scope.secondCategoryList = data;
		            		        				if(!isNull($scope.materiel.category1)){
		            		          		    			materielService.queryCategoryListByParent($scope.materiel.category1).then(
		            		            		        			function(data){
		            		            		        				$scope.thirdCategoryList = data;
		            		            		        				if(!isNull($scope.materiel.category2)){
		        		            		          		    			materielService.queryCategoryListByParent($scope.materiel.category2).then(
		        		            		            		        			function(data){
		        		            		            		        				$scope.fourthCategoryList = data;
		        		            		            		        			},
		        		            		            		          		     function(error){
		        		            		            		          		         $scope.error = error;
		        		            		            		          		         toastr.error('数据查询出错！');
		        		            		            		          		     }
		        		            		            		        	 );
		        		            		              	        	
		            		            		          	        	}
		            		            		        			},
		            		            		          		     function(error){
		            		            		          		         $scope.error = error;
		            		            		          		         toastr.error('数据查询出错！');
		            		            		          		     }
		            		            		        	 );
		            		          	        	}
		            		        			},
		            		          		     function(error){
		            		          		         $scope.error = error;
		            		          		         toastr.error('数据查询出错！');
		            		          		     }
		            		        	 );
		              	        	}
    		          		     },
    		          		     function(error){
    		          		         $scope.error = error;
    		          		         toastr.error('数据查询出错！');
    		          		     }
    		        	 );
          	        	
          	        	//加载功能分类
                    	$scope.queryFunctionListByParent('1');
                    	setTimeout($scope.multiselectInit, 2000)
        				
                    	
      		    		diyFormiCheck();//初始化checkbox控件
      	        		if($scope.materiel.isBOM=="1"){
      	        			$scope.BOMShow=true;
      	        			$('#isBOMcheck').parent().addClass('checked');
      	        			$('#isBOMcheck').iCheck('check'); 
      	        		}
      	        	}
      		     },
      		     function(error){
      		         $scope.error = error;
      		     }
      		 );
    	
    }; 
    
    //checkbox初始化
    var diyFormiCheck= function(){
    	FormiCheck.init();
    	$('#isBOMcheck').on('ifChecked', function(event){ //ifCreated 事件应该在插件初始化之前绑定 
    		$scope.materiel.isBOM="1";
    		$scope.BOMShow=true;
    		$scope.$apply();
    	}); 
    	$('#isBOMcheck').on('ifUnchecked', function(event){ //ifCreated 事件应该在插件初始化之前绑定 
    		$scope.materiel.isBOM="0";
    		$scope.BOMShow=false;
    		$scope.$apply();
    	}); 
    	
    }
    //加载供应物料分类
    $scope.renderSupplyCategory = function(supplyMateriel){
			//加载物料分类（必须逐步加载）
      	materielService.queryCategoryListByParent('0').then(
    			function(data){
  		    		supplyMateriel.supplyfristCategoryList = data;
  		    		if(!isNull(supplyMateriel.type)){
  		    			materielService.queryCategoryListByParent(supplyMateriel.type).then(
    		        			function(data){
    		        				supplyMateriel.supplysecondCategoryList = data;
    		        				if(!isNull(supplyMateriel.category1)){
    		          		    			materielService.queryCategoryListByParent(supplyMateriel.category1).then(
    		            		        			function(data){
    		            		        				supplyMateriel.supplythirdCategoryList = data;
    		            		        				/*if(!isNull(supplyMateriel.category2)){
		            		          		    			materielService.queryCategoryListByParent(supplyMateriel.category2).then(
		            		            		        			function(data){
		            		            		        				supplyMateriel.supplyfourthCategoryList = data;
		            		            		        			},
		            		            		          		     function(error){
		            		            		          		         $scope.error = error;
		            		            		          		         toastr.error('数据查询出错！');
		            		            		          		     }
		            		            		        	 );
		            		              	        	
    		            		          	        	}*/
    		            		        			},
    		            		          		     function(error){
    		            		          		         $scope.error = error;
    		            		          		         toastr.error('数据查询出错！');
    		            		          		     }
    		            		        	 );
    		          	        	}
    		        			},
    		          		     function(error){
    		          		         $scope.error = error;
    		          		         toastr.error('数据查询出错！');
    		          		     }
    		        	 );
      	        	}
      		     },
      		     function(error){
      		         $scope.error = error;
      		         toastr.error('数据查询出错！');
      		     }
    	 );
		
    }
    
    //加载采购物料分类
    $scope.renderBuyCategory = function(buyMateriel){
			//加载物料分类（必须逐步加载）
      	materielService.queryCategoryListByParent('0').then(
    			function(data){
  		    		buyMateriel.buyfristCategoryList = data;
  		    		if(!isNull(buyMateriel.type)){
  		    			materielService.queryCategoryListByParent(buyMateriel.type).then(
    		        			function(data){
    		        				buyMateriel.buysecondCategoryList = data;
    		        				if(!isNull(buyMateriel.category1)){
    		          		    			materielService.queryCategoryListByParent(buyMateriel.category1).then(
    		            		        			function(data){
    		            		        				buyMateriel.buythirdCategoryList = data;
    		            		        				/*if(!isNull(buyMateriel.category2)){
		            		          		    			materielService.queryCategoryListByParent(buyMateriel.category2).then(
		            		            		        			function(data){
		            		            		        				buyMateriel.buyfourthCategoryList = data;
		            		            		        			},
		            		            		          		     function(error){
		            		            		          		         $scope.error = error;
		            		            		          		         toastr.error('数据查询出错！');
		            		            		          		     }
		            		            		        	 );
		            		              	        	
    		            		          	        	}*/
    		            		        			},
    		            		          		     function(error){
    		            		          		         $scope.error = error;
    		            		          		         toastr.error('数据查询出错！');
    		            		          		     }
    		            		        	 );
    		          	        	}
    		        			},
    		          		     function(error){
    		          		         $scope.error = error;
    		          		         toastr.error('数据查询出错！');
    		          		     }
    		        	 );
      	        	}
      		     },
      		     function(error){
      		         $scope.error = error;
      		         toastr.error('数据查询出错！');
      		     }
    	 );
		}
    var table;
    var tableAjaxUrl = "rest/materiel/findMaterielList";
    var loadMainTable = function() {
            a = 0;
            App.getViewPort().width < App.getResponsiveBreakpoint("md") ? $(".page-header").hasClass("page-header-fixed-mobile") && (a = $(".page-header").outerHeight(!0)) : $(".page-header").hasClass("navbar-fixed-top") ? a = $(".page-header").outerHeight(!0) : $("body").hasClass("page-header-fixed") && (a = 64);
            table = $("#sample_2")
			.DataTable({
                language: {
                    aria: {
                        sortAscending: ": 以升序排列此列",
                        sortDescending: ": 以降序排列此列"
                    },
                    emptyTable: "空表",
                    info: "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
                    infoEmpty: "没有数据",
                    //infoFiltered: "(filtered1 from _MAX_ total entries)",
                    lengthMenu: "每页显示 _MENU_ 条数据",
                    search: "查询:",processing:"加载中...",infoFiltered: "（从 _MAX_ 项数据中筛选）",
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
                order: [[1, "desc"]],//默认排序列及排序方式
                searching: true,//是否过滤检索
                ordering:  true,//是否排序
                lengthMenu: [[5, 10, 15, 30, -1], [5, 10, 15, 30, "All"]],
                pageLength: 10,//每页显示数量
                processing: true,//loading等待框
//                serverSide: true,
                ajax: tableAjaxUrl,//加载数据中
                "aoColumns": [
                              { mData: 'serialNum' },
                              { mData: 'materielNum' },
                              { mData: 'materielName' },
                              { mData: 'specifications' },
                              { mData: 'unit' },
                              { mData: 'typeName' },
                              { mData: 'originCountry' },
                              { mData: 'brand' },
                              /*{ mData: null },*/
                              { mData: 'versionNO' },
                              { mData: 'status' }
                        ],
               'aoColumnDefs' : [ {
							'targets' : 0,
							'searchable' : false,
							'orderable' : false,
							'render' : function(data,
									type, full, meta) {
								return "<label class='mt-checkbox mt-checkbox-single mt-checkbox-outline'>" +
								"<input type='checkbox' id='"+data+"' class='checkboxes' ng-click='getMaterielInfo_(\""+data+"\")' name='serialNum[]' value="+ data +" />" +
								"<span></span></label>";
							},
							"createdCell": function (td, cellData, rowData, row, col) {
								 $compile(td)($scope);
						       }
						},{
							'targets' : 1,
							'render' : function(data,
									type, row, meta) {
								var bomIcon='';//bom图标
								if(row.isBOM==1){
									bomIcon = '<span class="label label-sm label-success">B</span> '
								}
								return bomIcon +
								' <a data-target="#basicMaterielInfo" data-toggle="modal" ng-click="getMaterielInfo(\''+row.serialNum+'\')" "> '+data+'</a>';
							},
							"createdCell": function (td, cellData, rowData, row, col) {
								 $compile(td)($scope);
						       }
						},{
							'targets' : 9,
							'className' : 'dt-body-center',
							'render' : function(data,
									type, full, meta) {
								if(data==1){
									return '已发布'
								}else{
									return '未发布'
								}
							}
						}/*,{
							'targets' : 7,
							'className' : 'dt-body-center',
							'render' : function(data,
									type, full, meta) {
								return  ''
							}
						}*/  ]

            }).on('order.dt',
            function() {
                console.log('排序');
            })
            
            
            
            
            // 添加checkbox功能***************************************
			// Handle click on "Select all" control
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
			// 添加checkbox功能
			// ***************************************
			$("#sample_2").find(".group-checkable").change(function() {
	            var e = jQuery(this).attr("data-set"),
	            t = jQuery(this).is(":checked");
	            jQuery(e).each(function() {
	                t ? ($(this).prop("checked", !0), $(this).parents("tr").addClass("active")) : ($(this).prop("checked", !1), $(this).parents("tr").removeClass("active"))
	            })
	        }),
	        $("#sample_2").on("change", "tbody tr .checkboxes",
	        function() {
	            $(this).parents("tr").toggleClass("active")
	        })
        };
        
        
        var loadTree = function() {
            $("#tree_1").jstree({
                core: {
                    themes: {
                        responsive: !1
                    },
                    data: {
                        url: function(e) {
                            return "rest/materiel/findMaterielCategoryTree"
                        },
                        data: function(e) {
                            return {
                                parent: e.id
                            }
                        }
                    }
                },
                types: {
                    "default": {
                        icon: "fa fa-folder icon-state-warning icon-lg"
                    },
                    file: {
                        icon: "fa fa-file icon-state-warning icon-lg"
                    }
                },
                plugins: ["types"]
            }),
            $("#tree_1").on("select_node.jstree", function(e, t) {
            	table.ajax.url(tableAjaxUrl+"?parent="+t.selected[0]).load()// 重新加载datatables数据
            })
            
            $('#tree_1').on("open_node.jstree",function(e,data){
            	$('#tree_1 a').each(function(){
            		if($(this).width()>$(this).parent().width()){
            			var treeClass = $('#allTreeType').attr('class');
            			$("#allTreeType").removeClass(treeClass)
            			$("#allTreeType").addClass("col-md-"+(Number(treeClass.substring(7))+1))
            			
            			var MaterielListClass = $('#allMaterielList').attr('class');
            			$("#allMaterielList").removeClass(MaterielListClass)
            			$("#allMaterielList").addClass("col-md-"+(Number(MaterielListClass.substring(7))-1))
            			
            		}
				})
            } );
            
        };
        $scope.reloadTable = function() {
        	table.ajax.url(tableAjaxUrl).load()// 重新加载datatables数据
        }
        
        //弹出确认删除模态框
        $scope.deleteMateriel = function() {
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
								} else
									ids = ids + ','
											+ this.value;
							}
						}
					});
			if(ids==''){
    			toastr.warning('未选择物料！');return;
    		}else{
    			$('#delMaterielModal').modal('show');// 弹出删除确认模态框
    		}
			
		};
        
     // 删除开始***************************************
		$scope.del = function() {
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
								} else
									ids = ids + ','
											+ this.value;
							}
						}
					});
			materielService
					.delMateriel(ids)
					.then(
							function(data) {
								$('#delMaterielModal').modal(
										'hide');// 删除成功后关闭模态框
								$(".modal-backdrop").remove();
								/*table.ajax.reload(); // 重新加载datatables数据*/
								toastr.success('数据删除成功！');
								 $state.go('materiel',{},{reload:true});
								 
							},
							function(errResponse) {
								toastr.error('数据删除失败！');
								console
										.error('Error while deleting Users');
							}

					);
		};
		// 删除结束***************************************
        
        var validateInit = function() {
        	var e = $("#form_sample_1"),
	        r = $(".alert-danger", e),
	        i = $(".alert-success", e);
	        e.validate({
	            errorElement: "span",
	            errorClass: "help-block help-block-error",
	            focusInvalid: !1,
	            ignore: "",
	            messages: {
	            	materielNum:{required:"物料编码不能为空！"},
	            	type:{required:"物料类型不能为空！"},
	            	materielName:{required:"物料名称不能为空！"},
	            	category:{required:"物料分类不能为空！"},
	            	specifications:{required:"物料规格不能为空！"},
	            	stockUnit:{required:"库存单位不能为空！"},
	            	deliveryCycle:{required:"一般交付周期不能为空！"}
	            },
            	rules: {materielNum: {required: !0,maxlength: 20},
            			type: {required: !0,maxlength: 20},
            			materielName: {required: !0,maxlength: 20},
            			category: {required: !0,maxlength: 20},
            			specifications: {required: !0,maxlength: 20},
            			stockUnit: {required: !0,maxlength: 20},
            			deliveryCycle:{required: !0,maxlength: 20}
            			},
            		invalidHandler: function(e, t) {
                    i.hide(), r.show(), App.scrollTo(r, -200)
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
	            }})
        };

        
        var validatePackageInit = function() {
        	var e = $("#form_sample_2");
	        r = $(".alert-danger", e),
	        i = $(".alert-success", e);
	        e.validate({
	            errorElement: "span",
	            errorClass: "help-block help-block-error",
	            focusInvalid: !1,
	            ignore: "",
	            messages: {
	            	packageNum:{required:"包装编码不能为空！"},
	            	packageSpecifications:{required:"包装规格不能为空！"},
	            	packageUnit:{required:"包装单位不能为空！"},
	            	packageUnitConversion:{required:"单位换算率不能为空！",number:"请输入有效的数字"}
            		
	            },
            	rules: {
            			packageNum: {required: !0,maxlength: 20},
            			packageSpecifications: {required: !0,maxlength: 20},
            			packageUnit: {required: !0,maxlength: 20},
            			packageUnitConversion: {required: !0,maxlength: 20,number:!0}
            			},
            		invalidHandler: function(e, t) {
                    i.hide(), r.show(), App.scrollTo(r, -200)
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
	            }})
        };
        
        $scope.editMateriel  = function() {//进入编辑页面
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
    			toastr.warning('请选择一个物料！');return;
    		}else if(ids=='more'){
    			toastr.warning('只能选择一个物料！');return;
    		}
    		
    		$state.go("addMateriel",{serialNum:ids});
        };
      
        var selectParentTable;
       var selectParentMateriel = function() {
                a = 0;
                App.getViewPort().width < App.getResponsiveBreakpoint("md") ? $(".page-header").hasClass("page-header-fixed-mobile") && (a = $(".page-header").outerHeight(!0)) : $(".page-header").hasClass("navbar-fixed-top") ? a = $(".page-header").outerHeight(!0) : $("body").hasClass("page-header-fixed") && (a = 64);
                selectParentTable = $("#select_sample_2")
    			.DataTable({
                    language: {
                        aria: {
                            sortAscending: ": 以升序排列此列",
                            sortDescending: ": 以降序排列此列"
                        },
                        emptyTable: "空表",
                        info: "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
                        infoEmpty: "没有数据",
                        //infoFiltered: "(filtered1 from _MAX_ total entries)",
                        lengthMenu: "每页显示 _MENU_ 条数据",
                        search: "查询:",processing:"加载中...",infoFiltered: "（从 _MAX_ 项数据中筛选）",
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
                    order: [[1, "desc"]],//默认排序列及排序方式
                    searching: true,//是否过滤检索
                    ordering:  true,//是否排序
                    lengthMenu: [[5, 10, 15, 30, -1], [5, 10, 15, 30, "All"]],
                    pageLength: 5,//每页显示数量
                    processing: true,//loading等待框
//                    serverSide: true,
                    ajax: "rest/materiel/findMaterielList?isLatestVersion=1",//加载数据中
                    "aoColumns": [
                                  { mData: 'serialNum' },
                                  { mData: 'materielNum' },
                                  { mData: 'materielName' },
                                  { mData: 'specifications' },
                                  { mData: 'unit' },
                                  /*{ mData: 'parentMateriel' },*/
                                  { mData: 'typeName' },
                                  { mData: 'originCountry' },
                                  { mData: 'brand' }
                                
                            ],
                   'aoColumnDefs' : [ {
    							'targets' : 0,
    							'searchable' : false,
    							'orderable' : false,
    							
    							'render' : function(data,
    									type, row, meta) {
    								return '<input type="radio" ng-click="selectParent(\''+data+'\',\''+row.materielName+'\')" name="serialNum[]" value="'
    										+ $('<div/>')
    												.text(
    														data)
    												.html()
    										+ '">';
    							},
    							"createdCell": function (td, cellData, rowData, row, col) {
    								 $compile(td)($scope);
    						       }
    						},{
    							'targets' : 1,
    							'render' : function(data,
    									type, row, meta) {
    								var bomIcon='';//bom图标
    								if(row.isBOM==1){
    									bomIcon = '<span class="label label-sm label-success">B</span> '
    								}
    								return bomIcon + data;
    							}

    						} ]

                }).on('order.dt',
                function() {
                    console.log('排序');
                })
            };
            //设置当前选中的物料行
            $scope.selectParent  = function(serialNum,materielName) {
            	$scope.row = {};
            	$scope.row.serialNum = serialNum;
            	$scope.row.parentMateriel = {};
            	$scope.row.parentMateriel.materielName = materielName;
            }; 
         // 确认选择开始***************************************
    		$scope.confirmSelect = function() {
    			var ids = '';
    			// Iterate over all checkboxes in the table
    			selectParentTable.$('input[type="radio"]').each(
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
    			
    			if(selectIndex=="parent"){//是选择上级物料
    				$scope.materiel.parentMaterielSerial=$scope.row.serialNum;
        			$scope.materiel.parentMateriel = {};
        			$scope.materiel.parentMateriel.materielName=$scope.row.parentMateriel.materielName;
    			}else{//选择bom物料
    				$scope.BOM[selectIndex].materielSerial=$scope.row.serialNum;
    				$scope.BOM[selectIndex].bomMaterielSerial = $scope.materiel.serialNum;
    				materielService.getMaterielInfo($scope.row.serialNum).then(
    		      		     function(data){
    		      		    	$scope.BOM[selectIndex].materiel={};
    		      		    	$scope.BOM[selectIndex].materiel.materielNum=data.materiel.materielNum
    		      		    	$scope.BOM[selectIndex].materiel.materielName=data.materiel.materielName
    		      		    	$scope.BOM[selectIndex].materiel.specifications=data.materiel.specifications
    		      		    	$scope.BOM[selectIndex].materiel.unit=data.materiel.unit
    		      		    	$scope.BOM[selectIndex].materiel.brand=data.materiel.brand
    		      		     },
    		      		     function(error){
    		      		         $scope.error = error;
    		      		     }
    		      		 );
    			}
    			
    			$('#basicMaterielInfo').modal('hide');// 删除成功后关闭模态框
    			$(".modal-backdrop").remove();
    		};
    		
    		
    		
    		
    		
    		//********bom  start****************//
    		var _index = 0;
    	    $scope.saveBOM  = function() {//保存BOM信息
    	    	if(!$scope.materielInput){
    	    		toastr.error('请先保存基本信息！');return
    	    	}
    	    	if($scope.materiel.serialNum==null||$scope.materiel.serialNum=='') {//上级物料为空的处理
    	    		toastr.error('请先保存基本信息！');return
    			}
    	    	if($('#form_sample_3').valid()){
    	    		for(var i=0;i<$scope.BOM.length;i++){
    	    			//$scope.BOM[i].materiel = null
    	    			if(isNull($scope.BOM[i].singleDose)||isNaN($scope.BOM[i].singleDose)||$scope.BOM[i].singleDose==0){
    	    				toastr.error('单套物料的数量只能为大于0的正整数！');return
    	    			}
    		    	}

    	    		materielService.saveBOM($scope.BOM).then(
    	       		     function(data){
    	       		    	toastr.success('数据保存成功！');
    	       		    	$scope.materiel = data.materiel;
    	       		    	if(!isNull(data.BOM)){
    	 	        			$scope.BOM = data.BOM;
    	 	        			_index = $scope.BOM.length-1;
    	 	        		}
    	       		    	$scope.cancelBOM();
    	       		    	
    	       		     },
    	       		     function(error){
    	       		    	toastr.error('数据保存出错！');
    	       		         $scope.error = error;
    	       		     }
    	       		 );
    	    	}
    	    	
    	    }; 	
    	    
    	    $scope.cancelBOM  = function() {//取消编辑BOM信息
    	    	$scope.BOMInfoInput = true;
    		    $scope.BOMInfoShow = true;
    	    };
    	    
    	    $scope.editBOM  = function() {//进入编辑BOM信息
    	    	$scope.BOMInfoInput = false;
    		    $scope.BOMInfoShow = false;
    	    };
    	    /**
  	        * bom新增一行
  	        */
    	    $scope.addBOM = function(){
    	    	if(!$scope.materielInput){
    	    		toastr.error('请先保存基本信息！');return
    	    	}
    	    	if($scope.materiel.serialNum==null||$scope.materiel.serialNum=='') {
    	    		toastr.error('请先保存基本信息！');return
    			}else{
    		    	   _index++;
    		    	   $scope.BOM[_index] = {};
    		    	   $scope.BOM[_index].materiel = {}
    		       }
    	    };
    	    
    	    /**
 	        * bom删除一行
 	        */
 	       $scope.deleteBOM = function(index){
 	    	   $scope.BOM.splice(index,1);
 	    	   _index--;
 	       };
 	       /**
 	        * 显示编辑、删除操作
 	        */
 	       $scope.showOperation = function(type,index){
 	    	   var call = "operation_b"+index;
 	    	   if(type=='file'){
 	    		   call =  "operation_f"+index;
 	    	   }
 	    	  if(type=='supplyMateriel'){
	    		   call =  "operation_s"+index;
	    	   }
 	    	 if(type=='buyMateriel'){
	    		   call =  "operation_buy"+index;
	    	   }
 	    	   $scope[call] = true;
 	       };
 	       
 	       /**
 	        * 隐藏编辑、删除操作
 	        */
 	       $scope.hideOperation = function(type,index){
 	    	   var call = "operation_b"+index;
 	    	   if(type=='file'){
 	    		   call =  "operation_f"+index;
 	    	   }
 	    	  if(type=='supplyMateriel'){
	    		   call =  "operation_s"+index;
	    	   }
 	    	   $scope[call]= false;
 	       };
 	       
 	       
 	      var validateBOMInit = function() {
 	        	var e = $("#form_sample_3");
 		        r = $(".alert-danger", e),
 		        i = $(".alert-success", e);
 		        e.validate({
 		            errorElement: "span",
 		            errorClass: "help-block help-block-error",
 		            focusInvalid: !1,
 		            ignore: "",
 		            messages: {
 		            	'BOMMaterielNum':{required:"物料编码不能为空！"},
 		            	'singleDose':{required:"单套用量不能为空！",number:"请输入有效的数字"}
 	            		
 		            },
 	            	rules: {
 	            			'BOMMaterielNum': {required: !0,maxlength: 20},
 	            			'singleDose': {required: true,maxlength: 20,number:!0}
 	            			},
 	            		invalidHandler: function(e, t) {
 	                    i.hide(), r.show(), App.scrollTo(r, -200)
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
 		            }})
 	        };
 	        var selectIndex;
 	       $scope.selectMateriel = function(index){
 	    	  selectIndex = index;
 	       }
 	        
    	  //********bom  end****************//
        
 	       //********导入导出start****************//
 	      /**
	        * 下载EXCEL模板
	        */
	       $scope.downloadImportTemp = function(){
	    	   window.location.href=$rootScope.basePath+"/rest/materiel/downloadImportTemp";
	       }
	       
	       /**
	        * 上传EXCEL
	        */
	       $scope.uploadExcel = function(){
	    	    var file = document.querySelector('input[type=file]').files[0];
	    	    if(handle.isNull(file)){
	    	    	toastr.warning("请选择Excel文件！");
	    	    }
	    	    console.log(file.name);
	    	    var type = file.name.substring(file.name.lastIndexOf("."));
	    	   if(type != ".xls"){
	    		   toastr.warning("文件格式不正确，需要xls类型的Excel文档");
	    		   return;
	    	   }
	    	   	handle.blockUI("正在导入中，请不要进行其他操作"); 
	    	   	var promise = materielService.uploadExcel();
       			promise.then(function(data){
       				handle.unblockUI(); 
       				if(data.data.data=="success"){
       					toastr.success("导入成功");
       					table.ajax.reload();
       				}else{
       					toastr.error(data.data.data);
       				}
       				$('#import').modal('hide'); 
	            },function(data){
	               //调用承诺接口reject();
	            	toastr.error("操作失败");
	            	$('#import').modal('hide'); 
	            });
	    	   
	       }
	       $('#import').on('hide.bs.modal', function (e) { 
	    	   $("#resetFile").trigger("click");
	       })
	       
	       
	       $scope.exportMateriel = function(){
		    	 handle.blockUI("正在导出数据，请稍后"); 
		    	 window.location.href=$rootScope.basePath+"/rest/materiel/exportMateriel";
		    	 handle.unblockUI(); 
		   }
	       //********导入导出end****************//
	       
	       
	       
	     //********附件  start****************//
   		var _fileIndex = 0;
   	    $scope.saveFile  = function() {//保存File信息
   	    	if($scope.materiel.serialNum==null||$scope.materiel.serialNum=='') {//上级物料为空的处理
   	    		toastr.error('请先保存基本信息！');return
   			}
   	    	if($('#form_sample_4').valid()){
   	    		materielService.saveFile($scope.file).then(
   	       		     function(data){
   	       		    	toastr.success('数据保存成功！');
   	       		    	$scope.cancelFile();
   	       		    	
   	       		     },
   	       		     function(error){
   	       		    	toastr.error('数据保存出错！');
   	       		         $scope.error = error;
   	       		     }
   	       		 );
   	    	}
   	    	
   	    }; 	
   	    
   	    $scope.cancelFile  = function() {//取消编辑File信息
   	    	$scope.fileInfoInput = true;
   		    $scope.fileInfoShow = true;
   	    };
   	    
   	    $scope.editFile  = function() {//进入编辑File信息
   	    	$scope.fileInfoInput = false;
   		    $scope.fileInfoShow = false;
   	    };
   	    /**
 	        * File新增一行
 	        */
   	    $scope.addFile = function(){
   	    	if($scope.materiel.serialNum==null||$scope.materiel.serialNum=='') {
   	    		toastr.error('请先保存基本信息！');return
   			}else{
   		    	   if($scope.file){}else{$scope.file =[{}]}
   		    	   $scope.file[_fileIndex] = {};
   		    	   $scope.file[_fileIndex].materielId = $scope.materiel.materielId;
   		    	   _fileIndex++;
   		       }
   	    };
   	    
   	    /**
	        * File删除一行
	        */
	       $scope.deleteFile = function(index){
	    	   $scope.file.splice(index,1);
	    	   _fileIndex--;
	       };
	       
	       
	      var validateFileInit = function() {
	        	var e = $("#form_sample_4");
		        r = $(".alert-danger", e),
		        i = $(".alert-success", e);
		        e.validate({
		            errorElement: "span",
		            errorClass: "help-block help-block-error",
		            focusInvalid: !1,
		            ignore: "",
		            messages: {
		            },
	            	rules: {
	            			
	            			},
	            		invalidHandler: function(e, t) {
	                    i.hide(), r.show(), App.scrollTo(r, -200)
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
		            }})
	        };

	      //创建对象
	  	  var uploader = $scope.uploader = new FileUploader({url:'rest/fileOperate/uploadSingleFile'});
	  	 
	  	  uploader.onAfterAddingFile = function(item){
	  		  if(item.file.size>10000000){
	  			  //toastr.warning("文件大小超过10M！");
	  			  uploader.cancelAll();
	  		  }
	  	  }
	  	  //添加文件到上传队列后
	  	  uploader.onCompleteAll = function () {
	  		  uploader.clearQueue();
	  	  };
	  	  //上传成功
	  	  uploader.onSuccessItem = function (fileItem,response, status, headers) {
	  		  if (status == 200){ 
	  			  if(response==""){
	  				  toastr.error("上传失败！");
	  				  return;
	  			  }
	  		  		toastr.success("上传成功！");
	  		  		$scope.file[uploadSelectIndex].file = response.filename;
	  		  }else{
	  			  toastr.error("上传失败！");
	  			$scope.file[uploadSelectIndex].file = response.filename;
	  		  }
	  		};
	  	  //上传失败
	  	  uploader.onErrorItem = function (fileItem, response, status, headers) {
	  			toastr.error("上传失败！");
	  	  };
	  	  

	       var uploadSelectIndex;
	  	  $scope.uploadFile = function(index){
	  		uploadSelectIndex = index;
	  	  }
	  	  
	  	  $scope.up = function(file){
	  		  uploader.clearQueue();
	  		  uploader.addToQueue(file);
	  		  uploader.uploadAll();
	  	  }
	       $scope.downloadFile = function(obj){
	    	   if(!handle.isNull(obj)){
	    		   window.location.href= $rootScope.basePath+"/rest/fileOperate/downloadFile?fileName="+encodeURI(encodeURI(obj.file));
	    	   }else{
	    		   toastr.error("下载失败!");
	    	   }
	       }
	       
	       $scope.removefile = function(index){
	    	   $scope.file[index].file = "";
	       }
	        
   	  //********附件  end****************//
	       
	       
	       //********供应商  start****************//
	   		var _supplyMaterielIndex = 0;
	   	    $scope.saveSupplyMateriel  = function() {//保存供应商信息
	   	    	if($scope.materiel.serialNum==null||$scope.materiel.serialNum=='') {//上级物料为空的处理
	   	    		toastr.error('请先保存基本信息！');return
	   			}
	   	    	
	   	    	if($('#form_sample_5').valid()){
	   	    		var tempCategory = angular.copy($scope.supplyMateriel);
	   	    		materielService.saveSupplyMateriel($scope.supplyMateriel).then(
	   	       		     function(data){
	   	       		    	$scope.supplyMateriel = data;
	   	       		    	if(!isNull($scope.supplyMateriel)){//将保存前的物料分类赋值回来
		   	       		    	for(var ii=0;ii<$scope.supplyMateriel.length;ii++){
			   	       		    	$scope.supplyMateriel[ii].supplyfristCategoryList = tempCategory[ii].supplyfristCategoryList;
					   		    	$scope.supplyMateriel[ii].supplysecondCategoryList = tempCategory[ii].supplysecondCategoryList;
					   		    	$scope.supplyMateriel[ii].supplythirdCategoryList = tempCategory[ii].supplythirdCategoryList;
		   	       		    	}
	   	       		    	}
	   	       		    	
	   	       		    	toastr.success('数据保存成功！');
	   	       		    	$scope.cancelSupplyMateriel();
	   	       		    	
	   	       		     },
	   	       		     function(error){
	   	       		    	toastr.error('数据保存出错！');
	   	       		         $scope.error = error;
	   	       		     }
	   	       		 );
	   	    	}
	   	    	
	   	    }; 	
	   	    
	   	    $scope.cancelSupplyMateriel  = function() {//取消编辑供应商信息
	   	    	$scope.supplyMaterielInfoInput = true;
	   		    $scope.supplyMaterielInfoShow = true;
	   	    };
	   	    
	   	    $scope.editSupplyMateriel  = function() {//进入编辑供应商信息
	   	    	$scope.supplyMaterielInfoInput = false;
	   		    $scope.supplyMaterielInfoShow = false;
	   	    };
	   	    /**
	 	        * 供应商新增一行
	 	        */
	   	    $scope.addSupplyMateriel = function(){
	   	    	if($scope.materiel.serialNum==null||$scope.materiel.serialNum=='') {
	   	    		toastr.error('请先保存基本信息！');return
	   			}else{
	   		    	   if($scope.supplyMateriel){}else{$scope.supplyMateriel =[{}]}
	   		    	   $scope.supplyMateriel[_supplyMaterielIndex] = {};
	   		    	   $scope.supplyMateriel[_supplyMaterielIndex].materielId = $scope.materiel.materielId;
	   		    	   
	   		    	   	$scope.supplyMateriel[_supplyMaterielIndex].materielName = $scope.materiel.materielName;
		   		    	$scope.supplyMateriel[_supplyMaterielIndex].specifications = $scope.materiel.specifications;
		   		    	$scope.supplyMateriel[_supplyMaterielIndex].unit = $scope.materiel.unit;
	   		    	   	$scope.supplyMateriel[_supplyMaterielIndex].supplyfristCategoryList = $scope.fristCategoryList;
		   		    	$scope.supplyMateriel[_supplyMaterielIndex].supplysecondCategoryList = $scope.secondCategoryList;
		   		    	$scope.supplyMateriel[_supplyMaterielIndex].supplythirdCategoryList = $scope.thirdCategoryList;
		   		    	$scope.supplyMateriel[_supplyMaterielIndex].type = $scope.materiel.type;
		   		    	$scope.supplyMateriel[_supplyMaterielIndex].category1 = $scope.materiel.category1;
		   		    	$scope.supplyMateriel[_supplyMaterielIndex].category2 = $scope.materiel.category2;
		   		    	/*$scope.supplyMateriel[_supplyMaterielIndex].unitPriceGuide = $scope.materiel.unitPriceGuide;
		   		    	$scope.supplyMateriel[_supplyMaterielIndex].purchaseQuota = $scope.materiel.purchaseQuota;
		   		    	$scope.supplyMateriel[_supplyMaterielIndex].moq = $scope.materiel.moq;*/
	   		    	
	   		    	   _supplyMaterielIndex++;
	   		    	   /*$('.bs-select').selectpicker();*/
	   		       }
	   	    };
	   	    $scope.repeatDone = function(judgeString){
	   	    	/*debugger;
	   	    	if(judgeString=='buy'){
		    		   $('select[name="buyComId"]').selectpicker({
		                    showSubtext: true,
		                    size : 5
		                });
		    			$('select[name="buyComId"]').selectpicker('refresh');//刷新插件
		    	   }else if(judgeString=='supply'){
		    		   $('select[name="supplyComId"]').selectpicker({
		                    showSubtext: true,
		                    size : 5
		                });
		    			$('select[name="supplyComId"]').selectpicker('refresh');//刷新插件
		    	   }*/
	       };
	       
	   	    $scope.multiselectInit = function(){
	   	    	$('#materielAttribute').multiselect();
	       };
	       
	      
	   	    
	   	    /**
		        * 供应商删除一行
		        */
		       $scope.deleteSupplyMateriel = function(index){
		    	   $scope.supplyMateriel.splice(index,1);
		    	   _supplyMaterielIndex--;
		       };
		       
		       
		      var validateSupplyMaterielInit = function() {
		        	var e = $("#form_sample_5");
			        r = $(".alert-danger", e),
			        i = $(".alert-success", e);
			        e.validate({
			            errorElement: "span",
			            errorClass: "help-block help-block-error",
			            focusInvalid: !1,
			            ignore: "",
			            messages: {
			            },
		            	rules: {
		            			
		            			},
		            		invalidHandler: function(e, t) {
		                    i.hide(), r.show(), App.scrollTo(r, -200)
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
			            }})
		        };
		        
		        var supplyparentId;
		        $scope.supplyqueryCategoryListByLevel = function(level,index){
		        	if(level=="second"){
		        		supplyparentId = $scope.supplyMateriel[index].type;
		        	}else if(level=="third"){
		        		supplyparentId =  $scope.supplyMateriel[index].category1;
		        	}else if(level=="fourth"){
		        		supplyparentId =  $scope.supplyMateriel[index].category2;
		        	}
		        	materielService.queryCategoryListByParent(supplyparentId).then(
		        			function(data){
		          		    	if(level=="second"){
		    		        		$scope.supplyMateriel[index].supplysecondCategoryList = data;
		          		    		$scope.supplyMateriel[index].supplythirdCategoryList = [];
		          		    		$scope.supplyMateriel[index].supplyfourthCategoryList = [];
		          		    		$scope.supplyMateriel[index].category1 = null;
		          		    		$scope.supplyMateriel[index].category2 = null;
		          		    		$scope.supplyMateriel[index].category3 = null;
		    		        	}else if(level=="third"){
		    		        		$scope.supplyMateriel[index].supplythirdCategoryList = data;
		          		    		$scope.supplyMateriel[index].supplyfourthCategoryList = [];
		          		    		$scope.supplyMateriel[index].category2 = null;
		          		    		$scope.supplyMateriel[index].category3 = null;
		          		    		
		    		        	}else if(level=="fourth"){
		    		        		$scope.supplyMateriel[index].supplyfourthCategoryList = data;
		    		        		$scope.supplyMateriel[index].category3 = null;
		    		        	}
		          		    	
		          		     },
		          		     function(error){
		          		         $scope.error = error;
		          		         toastr.error('数据查询出错！');
		          		     }
		        	 );
		        }

		        $scope.clearNoNumPoint = function(obj,attr){
			    	 //先把非数字的都替换掉，除了数字和.
			    	 obj[attr] = obj[attr].replace(/[^\d.]/g,"");
			    	 //必须保证第一个为数字而不是.
			    	 obj[attr] = obj[attr].replace(/^\./g,"");
			    	 //保证只有出现一个.而没有多个.
			    	 obj[attr] = obj[attr].replace(/\.{2,}/g,"");
			    	 //保证.只出现一次，而不能出现两次以上
			    	 obj[attr] = obj[attr].replace(".","$#$").replace(/\./g,"").replace("$#$",".");
		    	 }
		       
		       $scope.clearNoNum = function(obj,attr){
			    	 //把非数字的都替换掉
			    	 obj[attr] = obj[attr].replace(/[^\d]/g,"");
		    	 }
	   	  //********供应商  end****************//
		      //********采购商 start****************//
		   		var _buyMaterielIndex = 0;
		   	    $scope.saveBuyMateriel  = function() {//保存采购商信息
		   	    	if($scope.materiel.serialNum==null||$scope.materiel.serialNum=='') {//上级物料为空的处理
		   	    		toastr.error('请先保存基本信息！');return
		   			}
		   	    	if($('#form_sample_6').valid()){
		   	    		var tempCategory = angular.copy($scope.buyMateriel);
		   	    		materielService.saveBuyMateriel($scope.buyMateriel).then(
		   	       		     function(data){
		   	       		    	 $scope.buyMateriel = data;
			   	       		   if(!isNull($scope.buyMateriel)){//将保存前的物料分类赋值回来
			   	       		    	for(var ii=0;ii<$scope.buyMateriel.length;ii++){
				   	       		    	$scope.buyMateriel[ii].buyfristCategoryList = tempCategory[ii].buyfristCategoryList;
						   		    	$scope.buyMateriel[ii].buysecondCategoryList = tempCategory[ii].buysecondCategoryList;
						   		    	$scope.buyMateriel[ii].buythirdCategoryList = tempCategory[ii].buythirdCategoryList;
			   	       		    	}
		   	       		    	}
		   	       		    	toastr.success('数据保存成功！');
		   	       		    	$scope.cancelBuyMateriel();
		   	       		    	
		   	       		     },
		   	       		     function(error){
		   	       		    	toastr.error('数据保存出错！');
		   	       		         $scope.error = error;
		   	       		     }
		   	       		 );
		   	    	}
		   	    	
		   	    }; 	
		   	    
		   	    $scope.cancelBuyMateriel  = function() {//取消编辑采购商信息
		   	    	$scope.buyMaterielInfoInput = true;
		   		    $scope.buyMaterielInfoShow = true;
		   	    };
		   	    
		   	    $scope.editBuyMateriel  = function() {//进入编辑采购商信息
		   	    	$scope.buyMaterielInfoInput = false;
		   		    $scope.buyMaterielInfoShow = false;
		   	    };
		   	    /**
		 	        * 采购商新增一行
		 	        */
		   	    $scope.addBuyMateriel = function(){
		   	    	if($scope.materiel.serialNum==null||$scope.materiel.serialNum=='') {
		   	    		toastr.error('请先保存基本信息！');return
		   			}else{
		   		    	   if($scope.buyMateriel){}else{$scope.buyMateriel =[{}]}
		   		    	   $scope.buyMateriel[_buyMaterielIndex] = {};
		   		    	   $scope.buyMateriel[_buyMaterielIndex].materielId = $scope.materiel.materielId;
		   		    	   
			   		    	$scope.buyMateriel[_buyMaterielIndex].materielName = $scope.materiel.materielName;
			   		    	$scope.buyMateriel[_buyMaterielIndex].specifications = $scope.materiel.specifications;
			   		    	$scope.buyMateriel[_buyMaterielIndex].unit = $scope.materiel.unit;
		   		    	   	$scope.buyMateriel[_buyMaterielIndex].buyfristCategoryList = $scope.fristCategoryList;
			   		    	$scope.buyMateriel[_buyMaterielIndex].buysecondCategoryList = $scope.secondCategoryList;
			   		    	$scope.buyMateriel[_buyMaterielIndex].buythirdCategoryList = $scope.thirdCategoryList;
			   		    	$scope.buyMateriel[_buyMaterielIndex].type = $scope.materiel.type;
			   		    	$scope.buyMateriel[_buyMaterielIndex].category1 = $scope.materiel.category1;
			   		    	$scope.buyMateriel[_buyMaterielIndex].category2 = $scope.materiel.category2;
			   		    	/*$scope.supplyMateriel[_supplyMaterielIndex].unitPriceGuide = $scope.materiel.unitPriceGuide;
			   		    	$scope.supplyMateriel[_supplyMaterielIndex].purchaseQuota = $scope.materiel.purchaseQuota;
			   		    	$scope.supplyMateriel[_supplyMaterielIndex].moq = $scope.materiel.moq;*/
			   		    	
		   		    	   _buyMaterielIndex++;
		   		    	   /*$('.bs-select').selectpicker();*/
		   		       }
		   	    };
		   	    
		   	    /**
			        * 采购商删除一行
			        */
			       $scope.deleteBuyMateriel = function(index){
			    	   $scope.buyMateriel.splice(index,1);
			    	   _buyMaterielIndex--;
			       };
			       
			       
			      var validateBuyMaterielInit = function() {
			        	var e = $("#form_sample_6");
				        r = $(".alert-danger", e),
				        i = $(".alert-success", e);
				        e.validate({
				            errorElement: "span",
				            errorClass: "help-block help-block-error",
				            focusInvalid: !1,
				            ignore: "",
				            messages: {
				            },
			            	rules: {
			            			
			            			},
			            		invalidHandler: function(e, t) {
			                    i.hide(), r.show(), App.scrollTo(r, -200)
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
				            }})
			        };
			        
			        var buyparentId;
			        $scope.buyqueryCategoryListByLevel = function(level,index){
			        	if(level=="second"){
			        		buyparentId = $scope.buyMateriel[index].type;
			        	}else if(level=="third"){
			        		buyparentId =  $scope.buyMateriel[index].category1;
			        	}else if(level=="fourth"){
			        		buyparentId =  $scope.buyMateriel[index].category2;
			        	}
			        	materielService.queryCategoryListByParent(buyparentId).then(
			        			function(data){
			          		    	if(level=="second"){
			    		        		$scope.buyMateriel[index].buysecondCategoryList = data;
			          		    		$scope.buyMateriel[index].buythirdCategoryList = [];
			          		    		$scope.buyMateriel[index].buyfourthCategoryList = [];
			          		    		$scope.buyMateriel[index].category1 = null;
			          		    		$scope.buyMateriel[index].category2 = null;
			          		    		$scope.buyMateriel[index].category3 = null;
			    		        	}else if(level=="third"){
			    		        		$scope.buyMateriel[index].buythirdCategoryList = data;
			          		    		$scope.buyMateriel[index].buyfourthCategoryList = [];
			          		    		$scope.buyMateriel[index].category2 = null;
			          		    		$scope.buyMateriel[index].category3 = null;
			    		        	}else if(level=="fourth"){
			    		        		$scope.buyMateriel[index].buyfourthCategoryList = data;
			          		    		$scope.buyMateriel[index].category3 = null;
			    		        	}
			          		     },
			          		     function(error){
			          		         $scope.error = error;
			          		         toastr.error('数据查询出错！');
			          		     }
			        	 );
			        }
		   	  //********采购商  end****************//      
	      //********物料分类 start****************//
		        $scope.queryCategoryListByLevel = function(level){
		        	if(level=="second"){
		        		parentId = $scope.materiel.type;
		        	}else if(level=="third"){
		        		parentId = $scope.materiel.category1;
		        	}else if(level=="fourth"){
		        		parentId = $scope.materiel.category2;
		        	}
		        	$scope.queryCategoryListByParent(level,parentId);
		        }
		        
		        
		        $scope.queryFunctionListByParent = function(parentId){
		        	materielService.queryCategoryListByParent(parentId).then(
		        			function(data){
		        				$scope.functionList = data;
		          		     },
		          		     function(error){
		          		         $scope.error = error;
		          		         toastr.error('数据查询出错！');
		          		     }
		        	 );
		        	
		        }
		        
		        $scope.selectFunction = function(id){
		        	$scope.functionId = id;
		        }
		        
		        $scope.queryCategoryListByParent = function(level,parentId){
		        	if(level=="second"){
		        		$scope.fristCategory = parentId;
		        	}else if(level=="third"){
		        		$scope.secondCategory = parentId;
		        	}else if(level=="fourth"){
		        		$scope.thirdCategory = parentId;
		        	}
		        	materielService.queryCategoryListByParent(parentId).then(
		        			function(data){
		          		    	if(level=="frist"){
		          		    		$scope.fristCategoryList = data;
		          		    		$scope.secondCategoryList = [];
		          		    		$scope.thirdCategoryList = [];
		          		    		$scope.fourthCategoryList = [];
		          		    		$scope.fristCategory = null;
		          		    		$scope.secondCategory = null;
		          		    		$scope.thirdCategory = null;
		    		        	}else if(level=="second"){
		    		        		$scope.secondCategoryList = data;
		          		    		$scope.thirdCategoryList = [];
		          		    		$scope.fourthCategoryList = [];
		          		    		$scope.secondCategory = null;
		          		    		$scope.thirdCategory = null;
		    		        	}else if(level=="third"){
		    		        		$scope.thirdCategoryList = data;
		          		    		$scope.fourthCategoryList = [];
		          		    		$scope.thirdCategory = null;
		    		        	}else if(level=="fourth"){
		    		        		$scope.fourthCategoryList = data;
		    		        	}
		          		     },
		          		     function(error){
		          		         $scope.error = error;
		          		         toastr.error('数据查询出错！');
		          		     }
		        	 );
		        	
		        }
		        $scope.addCategory = function(level){
		        	$scope.category = {};
		        	$scope.category.parentId = null;
		        	if(level=="frist"){
		        		$scope.category.parentId = '0';
		        		$scope.category.level = '1';
		        	}else if(level=="second"){
		        		$scope.category.parentId = $scope.fristCategory;
		        		$scope.category.level = '2';
		        	}else if(level=="third"){
		        		$scope.category.parentId = $scope.secondCategory;
		        		$scope.category.level = '3';
		        	}else if(level=="fourth"){
		        		$scope.category.parentId = $scope.thirdCategory; 
		        		$scope.category.level = '4';
		        	}
		        	if($scope.category.parentId == null){
		        		$('#addCategory').modal('hide')
		        		toastr.error('请选择上级物料分类！');
		        		return
		        	}
		        	$('#addCategory').modal('show')
		        }; 
		        
		        $scope.addCategoryConfirm = function(level){
		        	materielService.saveCategory($scope.category).then(
		          		     function(data){
		          		    	toastr.success('数据保存成功！');
		          		    	$scope.category.categoryName = null;
		          		    	$('#addCategory').modal('hide')
		          		    	
		          		    	if($scope.category.level == '1'){
		          		    		$scope.fristCategoryList.push(data);
		          		    	}else if($scope.category.level == '2'){
		          		    		$scope.secondCategoryList.push(data);
		          		    	}else if($scope.category.level == '3'){
		          		    		$scope.thirdCategoryList.push(data);
		          		    	}else if($scope.category.level == '4'){
		          		    		$scope.fourthCategoryList.push(data);
		          		    	}
		          		     },
		          		     function(error){
		          		         $scope.error = error;
		          		         toastr.error('数据保存出错！');
		          		     }
		          		 );
		        };
		        
		        $scope.deleteCategory = function(level){
		        	$scope.category = {};
		        	$scope.category.categoryId = null;
		        	if(level=="frist"){
		        		$scope.category.categoryId = $scope.fristCategory;
		        		$scope.category.level = '1';
		        	}else if(level=="second"){
		        		$scope.category.categoryId = $scope.secondCategory;
		        		$scope.category.level = '2';
		        	}else if(level=="third"){
		        		$scope.category.categoryId = $scope.thirdCategory;
		        		$scope.category.level = '3';
		        	}else if(level=="fourth"){
		        		$scope.category.categoryId = $scope.fourthCategory; 
		        		$scope.category.level = '4';
		        	}
		        	if($scope.category.categoryId == null){
		        		$('#deleteCategory').modal('hide')
		        		toastr.error('请选择要删除的物料分类！');
		        		return
		        	}
		        	$('#deleteCategory').modal('show')
		        	
		        }; 
		        
		        $scope.deleteCategoryConfirm = function(level){
		        	materielService.deleteCategory($scope.category).then(
		          		     function(data){
		          		    	toastr.success('数据保存成功！');
		          		    	if($scope.category.level == '1'){
		          		    		$scope.removeCategory($scope.fristCategoryList,data)
		          		    		$scope.secondCategoryList = [];
		          		    		$scope.thirdCategoryList = [];
		          		    		$scope.fourthCategoryList = [];
		          		    		$scope.fristCategory = null;
		          		    		$scope.secondCategory = null;
		          		    		$scope.thirdCategory = null;
		          		    	}else if($scope.category.level == '2'){
		          		    		$scope.removeCategory($scope.secondCategoryList,data)
		          		    		$scope.thirdCategoryList = [];
		          		    		$scope.fourthCategoryList = [];
		          		    		$scope.secondCategory = null;
		          		    		$scope.thirdCategory = null;
		          		    	}else if($scope.category.level == '3'){
		          		    		$scope.removeCategory($scope.thirdCategoryList,data)
		          		    		$scope.fourthCategoryList = [];
		          		    		$scope.thirdCategory = null;
		          		    	}else if($scope.category.level == '4'){
		          		    		$scope.removeCategory($scope.fourthCategoryList,data)
		          		    	}
		          		    	$("#tab_"+$scope.category.level+" .active").removeClass("active");
		          		    	$('#deleteCategory').modal('hide')
		          		     },
		          		     function(error){
		          		         $scope.error = error;
		          		         toastr.error('数据保存出错！');
		          		     }
		          		 );
		        	
		        	
		        };
		        
		        $scope.removeCategory  = function(list,category){
		        	for(var i=0;i<list.length;i++){
        				if(category.categoryId == list[i].categoryId){
        					list.splice(i,1);
        				}
        			}
		        }
		        
		        
		        $scope.addFunction = function(level){
		        	$scope.category = {};
		        	$scope.category.parentId = 1;
		        	$('#addFunction').modal('show')
		        }; 
		        
		        $scope.addFunctionConfirm = function(){
		        	materielService.saveCategory($scope.category).then(
		          		     function(data){
		          		    	toastr.success('数据保存成功！');
		          		    	$scope.category.categoryName = null;
		          		    	if(isNull($scope.functionList)) $scope.functionList = [];
		          		    	$scope.functionList.push(data);
		          		    	$('#addFunction').modal('hide')
		          		     },
		          		     function(error){
		          		         $scope.error = error;
		          		         toastr.error('数据保存出错！');
		          		     }
		          		 );
		        };
		        
		        $scope.deleteFunction = function(level){
		        	$scope.category = {};
		        	$scope.category.categoryId = $scope.functionId;
		        	if($scope.category.categoryId == null){
		        		$('#deleteFunction').modal('hide')
		        		toastr.error('请选择要删除的功能分类！');
		        		return
		        	}
		        	$('#deleteFunction').modal('show')
		        	
		        }; 
		        
		        $scope.deleteFunctionConfirm = function(){
		        	materielService.deleteCategory($scope.category).then(
		          		     function(data){
		          		    	$scope.removeCategory($scope.functionList,data)
		          		    	$scope.category.categoryId = null;
		          		    	$('#deleteFunction').modal('hide')
		          		     },
		          		     function(error){
		          		         $scope.error = error;
		          		         toastr.error('数据保存出错！');
		          		     }
		          		 );
		        	
		        	
		        };
	      //********物料分类  end****************//
}]);
