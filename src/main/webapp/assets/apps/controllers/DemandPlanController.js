/**
 * 
 */

angular.module('MetronicApp').controller('DemandPlanController',['$rootScope','$scope','$state','$http','demandPlanService','$location','$compile','$stateParams','commonService',function($rootScope,$scope,$state,$http,demandPlanService,$location,$compile,$stateParams,commonService) {
	 $scope.$on('$viewContentLoaded', function() {   
	    	// initialize core components
		    handle = new pageHandle();
	    	App.initAjax();
	    	
	    	if($location.path()=="/demandPlanAdd"){
	    		$scope.rootMateriels = [];
	    		$scope.serialNums = [];
	    		getDemandPlanInfo($stateParams.serialNum);
	    		if($stateParams.view=="1"){//新建保存后返回新增页面，
	    			$scope.demandPlanAdd = true;
	    			$scope.demandPlanView =true;
	    		}
	    		selectParentMateriel();//加载物料列表
	    		handle.datePickersInit("auto bottom");
	    		initCustomers();
	 		}else if($location.path()=="/demandPlanView"){
	 			getDemandPlanInfo($stateParams.serialNum);
	 			selectParentMateriel();
	 			$scope.serialNums = [];
	 			selectSaleOrderTable();//加载物料列表
	 			handle.datePickersInit();
	 			initSuppliers();
	 		}else{
	 			demandPlanMaterielList();
	 			$scope.params = [];
	 			createTable(5,1,true,$scope.params);
	 			handle.datePickersInit("bottom");
	 		}
	    	// set default layout mode
	    	$rootScope.settings.layout.pageContentWhite = true;
	        $rootScope.settings.layout.pageBodySolid = false;
	        $rootScope.settings.layout.pageSidebarClosed = false;
	       
	    	
	 });
	 
	 /***选择物料列表初始化START***/
     var table;
     var selectParentMateriel = function() {
              a = 0;
              App.getViewPort().width < App.getResponsiveBreakpoint("md") ? $(".page-header").hasClass("page-header-fixed-mobile") && (a = $(".page-header").outerHeight(!0)) : $(".page-header").hasClass("navbar-fixed-top") ? a = $(".page-header").outerHeight(!0) : $("body").hasClass("page-header-fixed") && (a = 64);
              table = $("#select_sample_2").DataTable({
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
                  searching: true,//是否过滤检索
                  ordering:  true,//是否排序
                  lengthMenu: [[5, 10, 15, 30, -1], [5, 10, 15, 30, "All"]],
                  pageLength: 5,//每页显示数量
                  processing: true,//loading等待框
//                  serverSide: true,
                  ajax: "rest/materiel/findMaterielList?isLatestVersion=1",//加载数据中
                  "aoColumns": [
                                { mData: 'serialNum' },
                                { mData: 'materielNum' },
                                { mData: 'materielName' },
                                { mData: 'specifications' },
                                { mData: 'unit' },
                                { mData: 'supplyMateriels' }
                          ],
                 'aoColumnDefs' : [ {
  							'targets' : 0,
  							'searchable' : false,
  							'orderable' : false,
  							
  							'render' : function(data,
  									type, row, meta) {
  								if(row.supplyMateriels.length>0){
	  								if($scope.modalType=='single'){
//	  	  								return '<input type="radio" id='+data+' data-radio=true ng-click="getCheckedIds(\''+data+'\','+meta.row+')"  name="serialNum" value="'
//											+ $('<div/>')
//													.text(
//															row.supplyMateriels[0].serialNum)
//													.html()
//											+ '">';
	  	  							return '<label class="mt-radio mt-radio-outline">'+
                                    '<input type="radio" data-radio=true   ng-click="getCheckedIds(\''+data+'\','+meta.row+')" name="serialNum"  class="checkboxes" id="'+data+'" value="'+row.supplyMateriels[0].serialNum+'" />'+
                                    '<span></span></label>';
	
	  								}else{
	  	  								/*return '<input type="checkbox" data-checked=false id='+data+' ng-click="getCheckedIds(\''+data+'\','+meta.row+')"  name="material_serial" value="'
											+ $('<div/>')
													.text(
															row.supplyMateriels[0].serialNum)
													.html()
											+ '">';*/
	  									return '<label class="mt-checkbox mt-checkbox-single mt-checkbox-outline">'+
	                                     '<input type="checkbox"  name="material_serial" data-checked=false id='+data+' ng-click="getCheckedIds(\''+data+'\','+meta.row+')" class="checkboxes"  id="'+data+'" value="'+row.supplyMateriels[0].serialNum+'" data-set="#select_sample_2 .checkboxes" />'+
	                                     '<span></span>'+
	                                 '</label>';
	
	  								}
  								}else{
  									return '';
  								}
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
  							}},{
     							'targets' : 5,
     							'render' : function(data,
     									type, row, meta) {
     								if(data.length>0){
     									var select='<select class="form-control" id="select'+row.serialNum+'" ng-model="model'+row.serialNum+'" ng-init="model'+row.serialNum+'=\''+data[0].serialNum+'\'" ng-change="changeSelectValue(\'select'+row.serialNum+'\',\''+row.serialNum+'\')">'
 	 									for(var i=0;i<data.length;i++){
 	 										if(data[i].supply){
 	 											select = select + '<option value="'+data[i].serialNum+'">'+data[i].supply.comName+'</option>';
 	 										}else{
 	 											select = select + '<option value="'+data[i].serialNum+'"></option>';
 	 										}
 	 										
 	 									}
 	 									select = select + '</select>';	
 	     								return select;
     								}else{
     									return '无供应商';
     								}
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
	        	  checkedIdHandler();
	          });
          };
          
         
          /***选择物料列表初始化END***/
          
          /***交付日历列表初始化START***/
          var d_table;
          var demandPlanMaterielList = function() {
                   a = 0;
                   App.getViewPort().width < App.getResponsiveBreakpoint("md") ? $(".page-header").hasClass("page-header-fixed-mobile") && (a = $(".page-header").outerHeight(!0)) : $(".page-header").hasClass("navbar-fixed-top") ? a = $(".page-header").outerHeight(!0) : $("body").hasClass("page-header-fixed") && (a = 64);
                   d_table = $("#demand_plan_date").DataTable({
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
                       searching: true,//是否过滤检索
                       ordering:  true,//是否排序
                       lengthMenu: [[5, 10, 15, 30, -1], [5, 10, 15, 30, "All"]],
                       pageLength: 5,//每页显示数量
                       processing: true,//loading等待框
//                       serverSide: true,
                       //ajax: "rest/demandPlan/demandPlanMaterialList",//加载数据中
                       ajax:{
                    	   "url": "rest/demandPlan/demandPlanMaterialList",
                    	   "type": "post",
                    	   "contentType": "application/json",
                    	   "data": function ( d ) {
                    		   d.params = JSON.stringify($scope.demandPlanMateriel);
                    	    }
                       },
                       "aoColumns": [
                                     { mData: 'serialNum' },
                                     { mData: 'demandPlan.demandPlanNum' },
                                     { mData: 'demandPlan.buyComName' },
                                     { mData: 'materiel.materielNum' },
                                     { mData: 'materiel.materielName' },
                                     { mData: 'materiel.specifications' },
                                     { mData: 'materiel.unit' },
                                     { mData: 'amount' },
                                     { mData: 'supplyName' },
                                     { mData: 'deliveryAddress' },
                                     { mData: 'deliveryDate' },
                                     { mData: 'remainTime' }
                               ],
                      'aoColumnDefs' : [ {
       							'targets' : 0,
       							'searchable' : false,
       							'orderable' : false,
       							
       							'render' : function(data,
       									type, row, meta) {
   	  								/*return '<input type="checkbox"  name="serialNum[]" value="'
 										+ $('<div/>')
 												.text(
 														data)
 												.html()
 										+ '">';*/
   	  							return '<label class="mt-checkbox mt-checkbox-single mt-checkbox-outline">'+
                                '<input type="checkbox" data-check="false" class="checkboxes" id="'+data+'" value="'+data+'" data-set="#demandPlanTable .checkboxes" />'+
                                '<span></span>'+
                                 '</label>';
       							},
       							"createdCell": function (td, cellData, rowData, row, col) {
       								 $compile(td)($scope);
       						       }
       						}]

                   }).on('order.dt',
                   function() {
                       console.log('排序');
                   })
               };
          /***交付日历列表初始化END***/
               
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
						$('input[name="material_serial"]', rows).prop(
								'checked', this.checked);
					});
	
			// Handle click on checkbox to set state of "Select
			// all" control
			$('#select_sample_2 tbody')
					.on(
							'change',
							'input[name="material_serial"]',
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
	 

		
						
			// 页面加载完成后调用，验证输入框
				$scope.$watch('$viewContentLoaded', function() {  
								var e = $("#demandPlanForm"),
						        r = $(".alert-danger", e),
						        i = $(".alert-success", e);
						        e.validate({
						            errorElement: "span",
						            errorClass: "help-block help-block-error",
						            focusInvalid: !1,
						            ignore: "",
						            messages: {
						            	demandPlanNum:{required:"需求计划编号不能为空！"},
						            	buyComId:{required:"客户不能为空！"},
						            	releaseDate:{required:"发布日期不能为空！"}
						            },
						            rules: {
						            	demandPlanNum: {
						                    required: !0
						                },
						                buyComId: {
						                    required: !0
						                },
						                releaseDate: {
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
		
			
		
		/**
		 * checkbox点击事件
		 */
		$scope.getCheckedIds = function(serialNum,index){
			var data={};
			data.serialNum = serialNum;
			data.materiel = table.row(index).data(); //获取一行数据
			data.materiel.materielSerial = data.materiel.serialNum; //为保存操作做准备，新增物料serialNum为空
			data.materiel.serialNum = null
			data.materiel.supplyMaterielSerial = $("#"+serialNum).val();
			if($("#"+serialNum).data("radio")==true){ //修改物料弹出框
				$scope.serialNums = []; //清空选中数组
				$scope.serialNums.push(data);
				$scope.selectedMaterielHide = true; //不显示已选物料
				return;
			}
			if($("#"+serialNum).data("checked")||$("#"+serialNum).data("checked")==undefined){
				for(var i=0;i<$scope.serialNums.length;i++){
					if($scope.serialNums[i].serialNum==serialNum){
						$scope.serialNums.splice(i,1);
						$("#"+serialNum).attr("checked",false);
						$("#"+serialNum).data("checked",false);
						break;
					}
					
				}
				
			}else{
				$scope.serialNums.push(data);
				$("#"+serialNum).data("checked",true);
				$("#"+serialNum).attr("checked",true);
			}
			
		}
		
		/**
		 * 遍历checkbox,检查并处理已取消的元素
		 */
		function checkedIdHandler(){
			//获取选中物料ID
			table.$('input[name="material_serial"]').each(function() { //遍历当前页的物料信息
					if ($.contains(document, this)) {
						if (this.checked) {
							if($scope.serialNums.length>0){
								var flag = false;
								for(var i=0;i<$scope.serialNums.length;i++){
									if($scope.serialNums[i].serialNum == $(this).attr("id")){
										flag=true;
										break;
									}
									if(i==$scope.serialNums.length-1&& flag==false){//不在选中数组内，checkbox清除选中状态
										$(this).attr("checked",false);
										$(this).data("checked",false);
									}
								}
							}else if($scope.serialNums.length==0){//没有被选中的物料
								$(this).attr("checked",false);
								$(this).data("checked",false);
							}
						}
					}
			});
		}
		
		/**
		 * 加载采购商列表
		 */
			var initCustomers = function(){
				var promise = demandPlanService.initCustomers();
        		promise.then(function(data){
        			$scope.customers = data.data;
        		},function(data){
        			//调用承诺接口reject();
        		});
			}
			
			/**
			 * 加载采购商列表
			 */
			var initSuppliers = function(){
				var promise =commonService.initSuppliers();
				promise.then(function(data){
					$scope.suppliers = data.data;
				},function(data){
					//调用承诺接口reject();
				});
			}
		
		
		
	 		/**
	 		 *去新增页面
	 		 */
	        $scope.addDemandPlan=function () { 
	        		$scope.demandPlan = {};
	        		$state.go("demandPlanAdd");
	        }
	        
	        /**
	         * 选择物料页面弹出
	         */
	    	$scope.addMateriel = function (type,index){
	    		if(type=="single"){
	    			$scope.modalType = type;
	    			$scope.materielSelectedIndex = index;
	    			table.ajax.reload();
	    			$("#basicMaterielInfo").modal("show");
	    		}else{
	    			$scope.modalType = 'multiple';
	    			table.ajax.reload();
	    			if(!isNull($scope.demandPlan)&&!isNull($scope.demandPlan.serialNum)){
		    			$("#basicMaterielInfo").modal("show");
		    		}else{
		    			toastr.warning("请先保存需求计划基本信息！");
		    		}
	    		}
	    		
				
			}
	    	
	    	/**
	    	 * 更换供应物料流水号
	    	 */
	    	$scope.changeSelectValue = function(id,obj){
	    		if($("#"+obj).data("checked") == false){
	    			$("#"+obj).val($("#"+id).val());
	    		}else{
	    			for(var i=0;i<$scope.serialNums.length;i++){
	    				if($scope.serialNums[i].serialNum==obj){
	    					$scope.serialNums[i].materiel.supplyMaterielSerial = $("#"+id).val();
	    				}
	    			}
	    		}

	    	}
	    	
	    	/**
	    	 * 选择物料并展示在列表
	    	 */
	    	$scope.confirmSelect = function(){
	    		
		    		if($scope.serialNums.length==0){ //判断是否选择了物料
	    				toastr.warning("请选择物料");
						return;
	    			}
		    		if($scope.modalType=='single'){ //单个修改物料信息
		        			var data = $scope.serialNums[0].materiel; //获取选中的物料信息
		        			if($scope.materielSelectedIndex != undefined){
		        				$scope.rootMateriels[$scope.materielSelectedIndex].supplyMaterielSerial = data.supplyMaterielSerial;
		        				$scope.rootMateriels[$scope.materielSelectedIndex].materielSerial = data.materielSerial;
		        				$scope.rootMateriels[$scope.materielSelectedIndex].materielNum = data.materielNum;
		    					$scope.rootMateriels[$scope.materielSelectedIndex].materielName = data.materielName;
		    					$scope.rootMateriels[$scope.materielSelectedIndex].specifications = data.specifications;
		    					$scope.rootMateriels[$scope.materielSelectedIndex].unit = data.unit;
		    					$scope.rootMateriels[$scope.materielSelectedIndex].supplyMateriels = data.supplyMateriels;
		        				
		        			}
		        			$("#basicMaterielInfo").modal("hide");
		    			return;
		    		}
	    		
	    			//--------批量增加物料信息START--------------
	    			var ids = [];
	    			for(var i=0;i<$scope.serialNums.length;i++){
	    				ids.push($scope.serialNums[i].serialNum);
	    			}
	        		handle.blockUI();
        			if($scope.rootMateriels.length==0){//如果需求物料列表为空
        				for(var i = 0;i < $scope.serialNums.length;i++){ //将选中物料放入列表，并设置为编辑状态
        					$scope.rootMateriels.push(($scope.serialNums)[i].materiel);
        					$scope["demandPlanMaterielEdit"+i] = false;
        					$scope["demandPlanMaterielView"+i] = false;
        				}
        			}else{
		        		for(var i = 0;i < $scope.serialNums.length;i++){
	        				$scope.rootMateriels.splice(0,0,($scope.serialNums)[i].materiel); //将选中物料放入列表开头，并设置为编辑状态
	        				$scope["demandPlanMaterielEdit"+i] = false;
							$scope["demandPlanMaterielView"+i] = false;
							$scope["demandPlanMaterielEdit" + ($scope.rootMateriels.length-1)] = true;
							$scope["demandPlanMaterielView" + ($scope.rootMateriels.length-1)] = true;
		        		}
        				//之前的物料显示状态需要维持原状，以下添加代码
		        		
        			}
        			$scope.countSupplyCount();
        			$scope.copyMateriels = angular.copy($scope.rootMateriels);//复制需求物料列表，以便撤销
        			$("#basicMaterielInfo").modal("hide");
        			toastr.success("添加成功！");
        			handle.unblockUI();
	    	}
	    	
	    	//关闭物料列表时，清除选中状态START--------------
	    	 $('#basicMaterielInfo').on('hide.bs.modal', function (e) { 
	    		 clearChecked();
	    		 $scope.serialNums=[];
		     })
	    	
		     /**
		      * 清除选择状态
		      */
	    	function clearChecked(){
	    		table.$('input[type="checkbox"]').each(
						function() {
							// If checkbox exist in DOM
							if ($.contains(document, this)) {
								// If checkbox is checked
								this.checked = false;
							}
				});
	    	}
	    	//关闭物料列表时，清除选中状态END-----------------
	    	
	    	 $scope.searchDemandPlan = function(){
	    		 createTable(5,1,true,$scope.params);
	    	 }
	    	
	        /**
	         * 保存需求计划
	         */
			$scope.saveDemandPlan = function() {
				if($('#demandPlanForm').valid()){
					handle.blockUI();
					$scope.demandPlan.createTime = null;
					$scope.demandPlan.updateTime = null;
					var promise = demandPlanService
							.saveDemandPlan($scope.demandPlan);
					promise.then(function(data) {
						if (!handle.isNull(data.data)) {
							$(".modal-backdrop").remove();
							toastr.success("保存成功");
							handle.unblockUI();
							//$scope.demandPlan = data.data;
							viewDemandPlan(data.data.serialNum);
							console.log(data.data);	
							$scope.demandPlanView = true;
							$scope.demandPlanAdd = true;
							$scope.demandPlanEdit = false;
							$(".alert-danger").hide();
							$location.search({"serialNum":data.data.serialNum,view:"1"});
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
			}; 
			
			/**
			 * 去编辑需求计划
			 */
			$scope.toEditDemandPlan = function () {
				// Iterate over all checkboxes in the table
				var id_count = $('#demandPlanTable input[type="checkbox"]:checked').length;
				if(id_count==0){
					toastr.warning("请选择您要修改的记录");
				}else if(id_count>1){
					toastr.warning("只能选择一条数据进行修改");
				}else{
					var serialNum = $('#demandPlanTable input[type="checkbox"]:checked').val();
					$state.go("demandPlanAdd",{serialNum:serialNum});
				}
				
	        };
	        		
			 /**
			 * 编辑模式
			 */
	        $scope.editDemandPlanBasic = function (comId) {
	        	$scope.demandPlanView = false;
	        	$scope.demandPlanAdd = false;
	        	$scope.demandPlanEdit = true;
	        	
	        };
	        
	        /**
	         * 取消
	         */
	        $scope.cancelDemandPlanBasic=function () {
	        	if(!isNull($scope.demandPlan.serialNum)){
	        		getDemandPlanInfo($scope.demandPlan.serialNum);
		        	$scope.demandPlanView = true;
		        	$scope.demandPlanAdd = true;
		        	$scope.demandPlanEdit = false;	
	        	}else{
	        		$state.go("demandPlan");
	        	}
	        	
	        };
	        
	        /**
	         * 批量删除需求计划
	         */
	        $scope.deleteDemandPlan = function () {
	        	var id_count = $('#demandPlanTable input[type="checkbox"]:checked').length;
				if(id_count==0){
					toastr.warning("请选择您要删除的记录");
					return;
				}
	        	handle.confirm("确定删除吗？",function(){
	        		var ids = '';
					// Iterate over all checkboxes in the table
	        		$('#demandPlanTable input[type="checkbox"]').each(
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
	        		var promise = demandPlanService.deleteDemandPlan(ids);
	        		promise.then(function(data){
	        			toastr.success("删除成功");
	        			handle.unblockUI();
	        			createTable(5,1,true,$scope.params);
	        			//table.ajax.reload(); // 重新加载datatables数据
	        			/*$state.go('company',{},{reload:true}); */
	        		},function(data){
	        			//调用承诺接口reject();
	        		});
	        		
	        	});
	        	
	        };
	        
	        /**
	         * 获取需求计划信息
	         */
	        var getDemandPlanInfo = function(serialNum,type){
	    	   if(!handle.isNull(serialNum)){
	    			 var promise = demandPlanService.demandPlanInfo(serialNum);
	 	        	promise.then(function(data){
	 	        		if(!handle.isNull(data.data.demandPlan)){
		 	        		$scope.demandPlan = data.data.demandPlan;
		 	        	}
	 	        		if(!handle.isNull(data.data.demandPlanMateriels)){
	 	        			$scope.rootMateriels = data.data.demandPlanMateriels;
	 	        		}
	 	        		
	 	        		
	 	        		if(!isNull($stateParams.serialNum)){
	 		    			for(var i=0;i<$scope.rootMateriels.length;i++){
	 			        			$scope["demandPlanMaterielEdit"+i] = true;
	 								$scope["demandPlanMaterielView"+i] = true;
	 			        	}
	 		    		}
	 	        		$scope.copyMateriels = angular.copy($scope.rootMateriels);
	 	        		$scope.countSupplyCount();
	 	            },function(data){
	 	               //调用承诺接口reject();
	 	            });
	    		 }
		    }
	        
	        
	        $scope.searchDemandPlanMateriels = function(){
	        	$scope.search.serialNum = $scope.demandPlan.serialNum;
	        	var promise = demandPlanService.searchDemandPlan($scope.search);
        		promise.then(function(data){
        			//if(!handle.isNull(data.data)){
        				$scope.rootMateriels = data.data;
        			//}e;se
        		},function(data){
        			//调用承诺接口reject();
        		});
	        }
	        
	        /**
	         * 获取需求计划基本信息
	         */
	        var viewDemandPlan = function(serialNum){
	        	if(!handle.isNull(serialNum)){
	        		var promise = demandPlanService.viewDemandPlan(serialNum);
	        		promise.then(function(data){
	        			if(!handle.isNull(data.data)){
	        				$scope.demandPlan = data.data;
	        			}
	        		},function(data){
	        			//调用承诺接口reject();
	        		});
	        	}
	        }
	        
	        /**
	         * 需求计划物料信息验证
	         */
	        function demandPlanMaterielValid(index){
		    	   var flag = true;
		    		if(isNull($("#amount"+index).val())){
		    			   handle.paramCheck("amount"+index,"数量不能为空！");
		    			   flag = false;
		    		}else if(!handle.isInteger($("#amount"+index).val())||Number($("#amount"+index).val())<=0){
		    			   handle.paramCheck("amount"+index,"数量只能是正整数！");
		    			   flag = false;
		    		}
		    		if(isNull($("#deliveryDate"+index).val())){
		    			   handle.paramCheck("deliveryDate"+index,"交付日期不能为空！",true);
		    			   flag = false;
		    		}
		    		if(isNull($("#deliveryAddress"+index).val())){
		    			   handle.paramCheck("deliveryAddress"+index,"交付地点不能为空！");
		    			   flag = false;
		    		}
		    	   
		    	   return flag;
		    }
	        

			/**
			 * 保存需求计划物料信息
			 */
			$scope.saveDemandPlanMateriel = function(materiel,index) {
				if(!demandPlanMaterielValid(index)){
					return;
				}
				var demandPlanMateriel = {};
				handle.blockUI();
				demandPlanMateriel.createTime = null;
				demandPlanMateriel.updateTime = null;
				demandPlanMateriel.demandPlanSerial = $scope.demandPlan.serialNum;
				if(isNull(materiel.materielSerial)){ //如果供应物料id不存在，则为新增物料，否则为编辑需求物料
					demandPlanMateriel.supplyMaterielSerial = materiel.supplyMaterielSerial;
					demandPlanMateriel.materielSerial = materiel.serialNum;
				}else{
					demandPlanMateriel.serialNum = materiel.serialNum;
					demandPlanMateriel.supplyMaterielSerial = materiel.supplyMaterielSerial;
					demandPlanMateriel.materielSerial = materiel.materielSerial;
				}
				demandPlanMateriel.deliveryDate = materiel.deliveryDate;
				demandPlanMateriel.deliveryAddress = materiel.deliveryAddress;
				demandPlanMateriel.amount = materiel.amount;
				demandPlanMateriel.materielNum = materiel.materielNum;
				demandPlanMateriel.materielName = materiel.materielName;
				demandPlanMateriel.specifications = materiel.specifications;
				demandPlanMateriel.unit = materiel.unit;
				demandPlanMateriel.supplyMateriels = materiel.supplyMateriels;
				
				var promise = demandPlanService
				.saveDemandPlanMateriel(demandPlanMateriel);
				promise.then(function(data) {
					if (!handle.isNull(data.data)) {
						$(".modal-backdrop").remove();
						toastr.success("保存成功");
						handle.unblockUI();
						//var company = data.data;
						// $state.go('companyAdd',company,{reload:true});
						$scope.rootMateriels[index] = data.data;
						$scope.copyMateriels[index] = data.data;
						console.log(data.data);
						$scope["demandPlanMaterielEdit"+index] = true;
						$scope["demandPlanMaterielView"+index] = true;
						$(".alert-danger").hide();
						// $stateParams.comId =
						// company.comId;
						// $location.search('comId',company.comId);
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
			
	        /**
			 * 撤销物料编辑
			 */
	        $scope.cancelDemandPlanMateriel=function (materiel,index) {
	        	//.show_materiels = false;
	        	for(var i=0;i<$scope.copyMateriels.length;i++){
	        		if(materiel.serialNum == $scope.copyMateriels[i].serialNum && !isNull(materiel.supplyMaterielSerial)){ //如果是以保存的物料，回滚
	        			$scope.rootMateriels[$scope.rootMateriels.indexOf(materiel)] = $scope.copyMateriels[i];
	        			$scope["demandPlanMaterielEdit"+index] = true;
						$scope["demandPlanMaterielView"+index] = true;
						break;
	        		}
	        		
	        		if(i==$scope.copyMateriels.length-1){ //如果是已选择但未保存的物料，清空
	        			$scope.rootMateriels[$scope.rootMateriels.indexOf(materiel)].deliveryDate = "";
	        			$scope.rootMateriels[$scope.rootMateriels.indexOf(materiel)].deliveryAddress = "";
	        			$scope.rootMateriels[$scope.rootMateriels.indexOf(materiel)].amount = "";
	        		}
	        	}
	        };  
	        

	        //重写indexOf方法
			function indexOf(arr, item) {
				for (var i = 0; i < arr.length; i++) {
						if (arr[i] === item)
							return i;
						else
							return -1;
				}
			}
	        
	        /**
	         * 编辑需求计划物料
	         */
	        $scope.editDemandPlanMateriel=function (materiel) {
	        	//.show_materiels = false;
	        	for(var i=0;i<$scope.rootMateriels.length;i++){
	        		if(materiel.serialNum == $scope.rootMateriels[i].serialNum){
	        			$scope["demandPlanMaterielEdit"+i] = false;
	        			$scope["demandPlanMaterielView"+i] = false;
	        		}
	        	}
	        	
	        };  
	        
	        
	        /**
	         * 删除需求计划物料
	         */
	        $scope.deleteDemandPlanMateriel=function (materiel) {
	        	handle.confirm("确定删除吗？",function(){
	        		handle.blockUI();
	        		if($scope.rootMateriels.length > 0){
	        			for(var i=0;i<$scope.rootMateriels.length;i++){
	        				if(materiel == $scope.rootMateriels[i]){
	        					$scope.rootMateriels.splice(i,1);
	        				}
	        			}
	        		}
	        		if(!isNull(materiel.supplyMaterielSerial)){
	        			var promise = demandPlanService.deleteDemandPlanMateriel(materiel.serialNum);
		        		promise.then(function(data){
		        			if(data.data == "1"){
		        				toastr.success("删除成功");
			        			handle.unblockUI(); 
		        			}else{
		        				toastr.error("删除失败！请联系管理员");
				            	console.log(data);
		        			}
		        			
		 	            },function(data){
		 	               //调用承诺接口reject();
		 	            	toastr.error("删除失败！请联系管理员");
			            	console.log(data);
		 	            });
	        		}
	        	});
			   
	        };
	        
  
	        
	        /**
	         * 搜索
	         */
	        $scope.searchDemandPlanCalendar=function () {
	        	//createTable(5,1,false,params);
	        	$scope.demandPlanMateriel = {};
	        	$scope.demandPlanMateriel.startTime = $scope.startTime;
	        	$scope.demandPlanMateriel.endTime = $scope.endTime;
	        	d_table.settings()[0].ajax.data.params =  JSON.stringify($scope.demandPlanMateriel);
	        	d_table.ajax.reload();
	        };  
	        
	        /**
	         * 重置搜索条件
	         */
	        $scope.resetSearchForm = function (){
	        	$scope.startTime=null;
	        	$scope.endTime=null;
	        };
	        
	        /**
	         * 创建需求计划列表
	         */
	       function createTable(pageSize,pageIndex,init,params){
	    	 //初始化表格数据
	    	   handle.blockUI(null,"#simple");
		    	var promise = demandPlanService.createTable(pageSize,pageIndex,params);
		    	promise.then(function(data){
		    			$scope.demandPlans = data.data.result;
		    			data.data.params=params;
		    			handle.createPage("#simple",data.data,"rest/demandPlan/demandPlanList",createTable,init);
		            },function(data){
		               //调用承诺接口reject();
		         });
	       }
	       
	      
	       
	       
	       

	       
	       /**
	        * 需求物料初始化日期控件
	        */
	       $scope.repeatDone = function(){
	    	   handle.datePickersInit();
	       };
	       
	       
	       /**
	        * 显示编辑、删除操作
	        */
	       $scope.showOperation = function(type,index){
	    	   var call = "operation_c"+index;
	    	   var call2 = "operation_d"+index;
	    	   if(type=='finance'){
	    		   call =  "operation_f"+index;
	    	   }
	    	   $scope[call] = true;
	    	   $scope[call2] = true;
	       };
	       
	       /**
	        * 隐藏编辑、删除操作
	        */
	       $scope.hideOperation = function(type,index){
	    	   var call = "operation_c"+index;
	    	   var call2 = "operation_d"+index;
	    	   if(type=='finance'){
	    		   call =  "operation_f"+index;
	    	   }
	    	   $scope[call]= false;
	    	   $scope[call2]= false;
	       };
	       	
	       
	       /**
	        * 下载EXCEL模板
	        */
	       $scope.downloadImportTemp = function(){
	    	   window.location.href=$rootScope.basePath+"/rest/fileOperate/downloadImportTemp?tempName=demandPlan&fileName="+encodeURI(encodeURI('需求计划导入模板'));
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
	    	   	var promise = demandPlanService.uploadExcel();
       			promise.then(function(data){
       				handle.unblockUI(); 
       				if(data.data.data=="success"){
       					toastr.success("导入成功");
       					createTable(5,1,true,$scope.params);
       				}else{
       					toastr.error(data.data.data);
       				}
       				$('#import').modal('hide'); 
	            },function(data){
	               //调用承诺接口reject();
	            	handle.unblockUI(); 
	            	toastr.error("操作失败");
	            	$('#import').modal('hide'); 
	            });
	    	   
	       }
	       
	       /**
	        * 导出需求计划
	        */
	       $scope.exportDemandPlan = function(){
		    	 window.location.href=$rootScope.basePath+"/rest/demandPlan/exportDemandPlan";
		   }

	       
	       //关闭modal清除文件
	       $('#import').on('hide.bs.modal', function (e) { 
	    	   $("#resetFile").trigger("click");
	       })

	       $scope.countSupplyCount = function(){
	    	   if(!isNull($scope.rootMateriels)){
		    		var arr = [];
		    		for(var i in $scope.rootMateriels){
		    			if(!isNull($scope.rootMateriels[i].supplyMateriels[0].supplyComId)){
		    				arr.push($scope.rootMateriels[i].supplyMateriels[0].supplyComId);
		    			}
		    		}
		    		$scope.supplyCount = handle.unique(arr).length;
		    	}
	       }
	       
	       $scope.checkedOrCancelAll = function() { 
	    	   if($scope.materielAllChecked){
	    		   if(!isNull($scope.rootMateriels)){
			    		for(var i in $scope.rootMateriels){
			    			if($scope.rootMateriels[i].orderSerial == null){
			    				$scope.rootMateriels[i].materielChecked = true;
			    			}
			    		}
			       }
	    	   }else{
	    		   if(!isNull($scope.rootMateriels)){
			    		for(var i in $scope.rootMateriels){
			    			if($scope.rootMateriels[i].orderSerial == null){
			    				$scope.rootMateriels[i].materielChecked = false;
			    			}
			    		}
			       }
	    	   }
	       };
	       
	       $scope.addToSaleOrder = function(){
	    	   //toastr.warning("开发中。。。");
	    	   var arr = [];
	    	   if(!isNull($scope.rootMateriels)){
		    		for(var i in $scope.rootMateriels){
		    			if($scope.rootMateriels[i].materielChecked){
		    				arr.push($scope.rootMateriels[i].serialNum);
		    			}
		    		}
		       }
	    	  // $stateParams.materiels = arr; 
	    	   
	    	   //console.log(arr);
	    	   $state.go("addSaleOrder",{materiels:arr,demandPlanSerial:$scope.demandPlan.serialNum,buyComId:$scope.demandPlan.buyComId});
	       }
	       
	       $scope.cancel = function(){
	    	   $state.go("demandPlan");
	       };
	       
	       
	       /*******************************************历史订单Start********************************************/

	       var s_table;
	       var tableAjaxUrl = "rest/order/findOrderList?type=sale&demandPlanSerial="+$stateParams.serialNum;
	       var selectSaleOrderTable = function() {
	               a = 0;
	               App.getViewPort().width < App.getResponsiveBreakpoint("md") ? $(".page-header").hasClass("page-header-fixed-mobile") && (a = $(".page-header").outerHeight(!0)) : $(".page-header").hasClass("navbar-fixed-top") ? a = $(".page-header").outerHeight(!0) : $("body").hasClass("page-header-fixed") && (a = 64);
	               s_table = $("#sample_2")
	   			.DataTable({
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
	                   order: [[1, "asc"]],// 默认排序列及排序方式
	                   searching: true,// 是否过滤检索
	                   ordering:  true,// 是否排序
	                   autoWidth: false,
	                   lengthMenu: [[5, 10, 15, 30, -1], [5, 10, 15, 30, "All"]],
	                   pageLength: 5,// 每页显示数量
	                   processing: true,// loading等待框
	   // serverSide: true,
	                   ajax: tableAjaxUrl,// 加载数据中
	                   "aoColumns": [
	                                /* { mData: 'serialNum' },*/
		                              { mData: 'orderNum' },
		                              { mData: 'buyName' },
		                              { mData: 'materielCount' },
		                              { mData: 'orderAmount' },
		                              { mData: 'deliveryMode' },
		                              { mData: 'orderType' },
		                              { mData: 'saleApplySerial' },
		                              { mData: 'orderSerial' },
		                              { mData: 'orderDate' },
		                              { mData: 'orderDate' }

	                           ],
	                  aoColumnDefs: [{
	                	  	'width':'180px',
							'targets' : 0,
							'className' : 'dt-body-center',
							'render' : function(data,
									type, row, meta) {
								var clickhtm = '<span>'+data+'</span></br>'
								if(row.processBase!=""&&row.processBase!=null){
                        			if(row.processBase.status=="PENDING"||row.processBase.status=="WAITING_FOR_APPROVAL"){
										return clickhtm + '<span ng-click="viewOrderLog(\''+row.serialNum+'\')" style="color:#fcb95b">审核中</span>';
									}else if(row.processBase.status=="APPROVAL_SUCCESS"){
										if(row.status==1){
											return clickhtm + '<span  ng-click="viewOrderLog(\''+row.serialNum+'\')" style="color:#fcb95b">待签合同</span>';
										}else if(row.status==2){
											return clickhtm + '<span  ng-click="viewOrderLog(\''+row.serialNum+'\')" style="color:green">已确认</span>';
										}else{
											return clickhtm + '<span  ng-click="viewOrderLog(\''+row.serialNum+'\')" style="color:green">已确认</span>';
										}
									}else if(row.processBase.status=="APPROVAL_FAILED"){
										return clickhtm + '<span  ng-click="viewOrderLog(\''+row.serialNum+'\')" style="color:red">未通过</span>';
									}else{
										return clickhtm + '<span ng-click="viewOrderLog(\''+row.serialNum+'\')">未发布</span>';
									}
                        		}else{
                        			return clickhtm + '<span ng-click="viewOrderLog(\''+row.serialNum+'\')">未发布</span>';
                        		}
								
							
							},
							"createdCell": function (td, cellData, rowData, row, col) {
								 $compile(td)($scope);
						       }
						},{
							'targets' : 1,
							'className' : 'dt-body-center',
							'render' : function(data,
									type, row, meta) {
								var htm = (data==null?'':data)+'</br>'
                    			if(row.deliverStatus=="0"){
                    				return htm + '<span >未开始</span>';
								}else if(row.deliverStatus=="1"){
                    				return htm + '<span style="color:green" ng-click="viewDeliverLog(\''+row.serialNum+'\')">已发货</span>';
								}else if(row.deliverStatus=="2"){
                    				return htm + '<span style="color:green" ng-click="viewDeliverLog(\''+row.serialNum+'\')">已收货</span>';
								}else if(row.deliverStatus=="3"){
                    				return htm + '<span style="color:green" ng-click="viewDeliverLog(\''+row.serialNum+'\')">已检验</span>';
								}else if(row.deliverStatus=="4"){
                    				return htm + '<span style="color:green" ng-click="viewDeliverLog(\''+row.serialNum+'\')">已出库</span>';
								}else if(row.deliverStatus=="5"){
                    				return htm + '<span style="color:green" ng-click="viewDeliverLog(\''+row.serialNum+'\')">已入库</span>';
								}else{
									return htm + '<span>未开始</span>';
								}
							},
							"createdCell": function (td, cellData, rowData, row, col) {
								 $compile(td)($scope);
						       }
						},{
							'targets' : 2,
							'render' : function(data,
									type, row, meta) {
								var htm = (data==null?'':data)+'</br>'

                    			if(row.payStatus=="0"){
                    				return htm + '<span >未付款</span>';
								}else if(row.payStatus=="1"){
                    				return htm + '<span style="color:green" ng-click="viewPayLog(\''+row.serialNum+'\')">已付款</span>';
								}else if(row.payStatus=="2"){
                    				return htm + '<span style="color:green" ng-click="viewPayLog(\''+row.serialNum+'\')">已收款</span>';
								}else{
									return htm + '<span >未付款</span>';
								}
							},
							"createdCell": function (td, cellData, rowData, row, col) {
								 $compile(td)($scope);
						       }
						}, {
							'targets' : 6,
							'render' : function(data,
									type, row, meta) {
								if(isNull(row.contract)){
									return ""
								}else{
									return row.contract.contractNum
								}
							}
						},{
   							'targets' : 9,
   							'className' : 'dt-body-center',
   							'render' : function(data,
   									type, row, meta) {
   								return '<a href="javascript:;" ui-sref="viewSaleOrder({\'serialNum\':\'' + row.serialNum + '\'})" >查看</a>';
   							},
							"createdCell": function (td, cellData, rowData, row, col) {
								 $compile(td)($scope);
						       }
   						} ]

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
	   						var rows = s_table.rows({
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
	           };
	    
	       
	       /*******************************************历史订单End********************************************/
	           
	           
	           
	       /*************************************************查看需求计划物料START***********************************************/
	           /*var e=$("#sample_1");
	           e.dataTable({
	               language: {
	                   aria: {
	                       sortAscending: ": activate to sort column ascending",
	                       sortDescending: ": activate to sort column descending"
	                   },
	                   emptyTable: "No data available in table",
	                   info: "Showing _START_ to _END_ of _TOTAL_ entries",
	                   infoEmpty: "No entries found",
	                   infoFiltered: "(filtered1 from _MAX_ total entries)",
	                   lengthMenu: "_MENU_ entries",
	                   search: "Search:",
	                   zeroRecords: "No matching records found"
	               },
	               scrollY: 300,
	               deferRender: !0,
	               scroller: !0,
	               stateSave: !0,
	               order: [
	                   [
	                       0,
	                       "asc"
	                   ]
	               ],
	               lengthMenu: [
	                   [
	                       10,
	                       15,
	                       20,
	                       -1
	                   ],
	                   [
	                       10,
	                       15,
	                       20,
	                       "All"
	                   ]
	               ],
	               pageLength: 10,
	               ajax:function(e,t,n){
	            	   		
	                          t({
	                                   draw: e.draw,
	                                   data: null,
	                                   recordsTotal: 5e6,
	                                   recordsFiltered: 5e6
	                               })
	               }
	           //    dom: "<'row' <'col-md-12'B>><'row'<'col-md-6 col-sm-12'l><'col-md-6 col-sm-12'f>r><'table-scrollable't><'row'<'col-md-5 col-sm-12'i><'col-md-7 col-sm-12'p>>"
	           })*/
	       /*************************************************查看需求计划物料END*************************************************/
}]); 

/*var changeSelectValue = function (value,obj){
	if($("#"+obj).data("checked") == false){
		$("#"+obj).val(value.value);
	}else{
		for(var i=0;i<$scope.serialNums.length;i++){
			if($scope.serialNums.serialNum==obj){
				$scope.serialNums[i].materiel.supplySerial = value.value;
			}
		}
	}

}*/
