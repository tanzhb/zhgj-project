angular.module('MetronicApp').controller('ReceiveMemoController', ['$rootScope','$scope','$http', 'settings', 'ReceiveMemoService','commonService','orderService','$state','$compile','$stateParams','$filter','FileUploader',
                                                           function($rootScope,$scope,$http,settings,ReceiveMemoService,commonService,orderService,$state,$compile,$stateParams,$filter,FileUploader) {
	$scope.$on('$viewContentLoaded', function() {   
		
		App.initAjax();
		handle = new pageHandle();
		// set default layout mode
		$rootScope.settings.layout.pageContentWhite = true;
		$rootScope.settings.layout.pageBodySolid = false;
		$rootScope.settings.layout.pageSidebarClosed = false;

		loadMainTable();

		//控制输入框和span标签的显示
		validateFileInit();//file表单初始化
		
	if($state.current.name=="addReceiveMemo"){
		$scope.span =false;
		$scope.input = true;
		$scope.inputFile=true;
		//根据参数查询对象
		 if($stateParams.serialNum){
				$scope.getMemoInfo($stateParams.serialNum);	
			    }else{
	$rootScope.setNumCode("MO",function(newCode){
		$scope.memoRecord={};
			$scope.memoRecord.memoNum= newCode;//收款水单号
			$scope.memoRecord.paymentDate= $filter('date')(new Date(), 'yyyy-MM-dd');//计划收款日期
			//getCurrentUser();
		});
			    }
		 initCustomers();
		 
	
	}else if($state.current.name=="viewReceiveMemo"){
		$scope.getMemoInfo($stateParams.serialNum);
		if($stateParams.type!=undefined){//显示核销按钮
			$scope.inputEdit=true;
		}
		$scope.input=false;
		$scope.span=true;
		
	}
		
	});
	   /**
	 * 加载当前用户信息
	 */
	var getCurrentUser = function(){
		var promise = commonService.getCurrentUser();
		promise.then(function(data){
			$scope.paymentRecord.applicant = data.data.userName;
			$scope.paymentRecord.applyDept=data.data.department;
			
		},function(data){
			//调用承诺接口reject();
		});
	}
	 /**
	 * 加载采购商(付款方)数据
	 */
	var initCustomers = function(){
		var promise = orderService.initCustomers();
        	promise.then(function(data){
        		$scope.companyList = data.data;
        		setTimeout(function () {
        			$("#buyComId").selectpicker({
                        showSubtext: true
                    });
        			$('#buyComId').selectpicker('refresh');//刷新插件
        			
                }, 100);
        		
        	},function(data){
        		//调用承诺接口reject();
        	});
	}
	
	$scope.getBankInfo=function(){
		var promise = orderService.initComFinances($scope.memoRecord.buyComId);
    	promise.then(function(data){
    		$scope.comFinances = data.data;
    		/*setTimeout(function () {
    			$("#buyComId").selectpicker({
                    showSubtext: true
                });
    			$('#buyComId').selectpicker('refresh');//刷新插件
    			
            }, 100);*/
    		
    	},function(data){
    		//调用承诺接口reject();
    	});
	}
	$scope.changeMoney=function(){
		$scope.memoRecord.verificationMoneyAmount=0;
		$scope.memoRecord.remainMoneyAmount=$scope.memoRecord.moneyAmount;
	}
	$scope.changeValue=function(){
		for(var i in  $scope.comFinances){
			if($scope.comFinances[i].openingBank==$scope.memoRecord.bank){
				$scope.memoRecord.accountName=$scope.comFinances[i].accountName;
				$scope.memoRecord.accountNumber=$scope.comFinances[i].accountNumber;
			
				return;
			}
		}
		$scope.memoRecord.accountName='';
		$scope.memoRecord.accountNumber='';
	}
	$scope.download=function(name) {
		/*  if(!handle.isNull(name)){
   		   window.location.href= $rootScope.basePath+"/rest/fileOperate/downloadFile?fileName="+encodeURI(encodeURI(name));
   	   }else{
   		   toastr.error("下载失败!");
   	   }*/
			 window.location.href= $rootScope.basePath+"/rest/fileOperate/downloadFile?fileName="+encodeURI(encodeURI(name));
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
			toastr.warning('请选择一个收款水单！');return;
		}else if(ids=='more'){
			toastr.warning('只能选择一个收款水单！');return;
		}
		$state.go('addReceiveMemo',{serialNum:ids});
	};


	//根据参数查询对象
	$scope.getMemoInfo  = function(serialNum) {
		ReceiveMemoService.selectReceiveMemo(serialNum).then(
      		     function(data){
      		    	$scope.memoRecord=data;
      		     },
      		     function(error){
      		         toastr.error('连接服务器出错,请登录重试！');
      		     }
      		 );
    	
    }; 
    


	//返回按钮
	$scope.goBack=function(){
		$state.go('gatheringMoneyRecord');
	}

	//确认收款
	$scope.confirmGatheringMoney=function(serialNum){
		GatheringMoneyService.confirmGatheringMoney(serialNum).then(
     		     function(data){
     		    	$state.go('gatheringMoneyRecord',{},{reload:true}); // 重新加载datatables数据
     		     },
     		     function(error){
     		         toastr.error('连接服务器出错,请登录重试！');
     		     }
     		 );
	}
	//打印
	$scope.print=function(){
		window.print();  
	}
	
	//将申请收款金额转换成大写
	$scope.getChnAmount=function(){
		var amount=$("#applyPaymentAmount").val();
		var chnAmount=convertCurrency(amount);
		$scope.chnAmount=chnAmount;
	}
	
	
	var paymentAmount=null;
	$scope.selectClauseDetail=function(serialNum){
		GatheringMoneyService.selectClauseDetail(serialNum).then(
				function(data){
					if($scope.pay==null){
						$scope.clauseSettlement=data;
						paymentAmount=$scope.clauseSettlement.totalAmount;	
					}else{
						$scope.pay.deliveryAmount=data.totalAmount;
						paymentAmount=data.totalAmount;
					}
				},
				function(error){
					$scope.error = error;
				}
		);	
	};
	
	var buyComId=null;
	//获取销售订单的信息并给buyComId赋值
	$scope.getSaleOrderInfo  = function(serialNum) {
		GatheringMoneyService.getSaleOrderInfo(serialNum).then(
				function(data){
					if($scope.pay==null){
						$scope.saleOrder=data.orderInfo;
						buyComId=$scope.saleOrder.buyComId;
						var orderSerial=data.orderInfo.serialNum;
						$scope.orderSerial=data.orderInfo.serialNum;
						$scope.deliveryMaterielE=data.clauList;
						$scope.clauseSettlementList=data.clauseSettlementDetail;
						$scope.isBG=data.createBG;
						$scope.paymentRecord.orderDate=data.orderInfo.orderDate;
						$scope.comFinances=data.comFinances//收付款信息
						$scope.paymentRecord.payee=data.orderInfo.buyName;
					}else{
						/*$scope.pay.orderNum=data.orderInfo.orderNum;
						$scope.pay.orderSerial=data.orderInfo.serialNum;
						$scope.pay.clauseSettList=data.orderInfo.clauseSettList;
						$scope.pay.supplyComId=data.orderInfo.supplyComId;
						$scope.pay.deliveryAmount=null;
						buyComId=data.orderInfo.buyComId;
						$scope.clauseSettlementList=data.clauseSettlementDetail;*/
					}
				},
				function(error){
					$scope.error = error;
				}
		);

	};
	
	
 	//选择支付节点赋值
   	$scope.selectPaymentNode=function(node){
		var serialNum;
		if($scope.pay!=null){
			serialNum=$scope.pay.orderSerial;
		}else{
			serialNum=$scope.orderSerial;
		}
		if(node!=''){//选择了支付节点
			for(var i=0;i<$scope.clauseSettlementList.length;i++){
				if(node==$scope.clauseSettlementList[i].deliveryNode){
					$scope.paymentRecord.payType=$scope.clauseSettlementList[i].paymentType;//支付类型
					$scope.paymentRecord.deliveryRate=$scope.clauseSettlementList[i].deliveryRate+"%";//支付比率
					$scope.paymentRecord.applyPaymentAmount=$scope.clauseSettlementList[i].deliveryAmount;//支付金额
					$scope.chnAmount=convertCurrency($scope.clauseSettlementList[i].deliveryAmount);//大写金额
					$scope.paymentRecord.applyCurrency=$scope.clauseSettlementList[i].paymentMethod;//支付方式
					$scope.paymentRecord.accountPeriod=$scope.clauseSettlementList[i].accountPeriod;//账期
					$scope.paymentRecord.billType=$scope.clauseSettlementList[i].billingMethod=='先票后款'?'1':'0';//发票方式
					$scope.paymentRecord.applyCurrency=$scope.clauseSettlementList[i].paymentMethod;//币种
				}
				
			}
		}else if(node==''){
			$scope.paymentRecord.payType='';//支付类型
			$scope.paymentRecord.deliveryRate='';//支付比率
			$scope.paymentRecord.applyPaymentAmount='';//支付金额
			$scope.chnAmount='';//大写金额
			$scope.paymentRecord.applyCurrency='';//支付方式
			$scope.paymentRecord.playPaymentDate='';//账期
			$scope.paymentRecord.billType='';//发票方式
			$scope.paymentRecord.applyCurrency='';//币种
		}
		
	}
	
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
	
	//********附件  start****************//
		var _fileIndex = 0;
	    $scope.saveFile  = function() {//保存File信息
	    	if($scope.pay==null){
	    		toastr.error('请先保存基本信息！');return	
	    	}
	    	if($scope.pay.serialNum==null||$scope.pay.serialNum=='') {//上级物料为空的处理
	    		toastr.error('请先保存基本信息！');return
			}
	    	/*if($('#form_sample_4').valid()){*/
	    	GatheringMoneyService.saveFile($scope.file).then(
	       		     function(data){
	       		    	toastr.success('数据保存成功！');
	       		    	$scope.inputFile=false;
	       		    	$scope.cancelFile();
	       		    	
	       		     },
	       		     function(error){
	       		    	toastr.error('数据保存出错！');
	       		         $scope.error = error;
	       		     }
	       		 );
	    	/*}*/
	    	
	    }; 	
	    
	    
	    $scope.updateFile  = function() {//保存File信息
	    	if($scope.pay.serialNum==null||$scope.pay.serialNum=='') {//上级物料为空的处理
	    		toastr.error('请先保存基本信息！');return
			}
	    	/*if($('#form_sample_4').valid()){*/
	    	GatheringMoneyService.updateFile($scope.file).then(
	       		     function(data){
	       		    	toastr.success('数据保存成功！');
	       		    	$scope.inputFile=false;
	       		    	$scope.cancelFile();
	       		    	
	       		     },
	       		     function(error){
	       		    	toastr.error('数据保存出错！');
	       		         $scope.error = error;
	       		     }
	       		 );
	    	/*}*/
	    	
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
	    	if($scope.pay==null) {
	    		toastr.error('请先保存基本信息！');return
			}else{
		    	   if($scope.file){}else{$scope.file =[{}]}
		    	   $scope.file[_fileIndex] = {};
		    	   $scope.file[_fileIndex].paymentSerial = $scope.pay.serialNum;
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
 /*      $scope.downloadFile = function(obj){
    	   if(!handle.isNull(obj)){
    		   window.location.href= $rootScope.basePath+"/rest/fileOperate/downloadFile?fileName="+encodeURI(encodeURI(obj.file));
    	   }else{
    		   toastr.error("下载失败!");
    	   }
       }
       
       $scope.downloadFile1 = function(str){
    	   debugger
    		   window.location.href= $rootScope.basePath+"/rest/fileOperate/downloadFile?fileName="+encodeURI(encodeURI(str));
       }*/
       
       $scope.removefile = function(index){
    	   $scope.file[index].file = "";
       }
        
	  //********附件  end****************//

	//添加收款
	$scope.saveBasicInfo=function (judgeString){
		if(!$('#form_sample_1').valid()){
			return;
		}
			 $rootScope.judgeIsExist("payOrReceive",$scope.memoRecord.memoNum, $scope.memoRecord.serialNum,function(result){
	    			var 	isExist = result;
	    		if(isExist){
	    			 if(judgeString=='receive'){
	    				 toastr.error('收款水单号重复！');
	    			}
	    			return;
	    		}else{
	    			var fd = new FormData();
	    			handle.blockUI();
	    			if($("input[name='file']").length){
		    	        var file = document.querySelector('input[name="file"]').files[0];
		    	        fd.append("files", file);
		    			}
	    			fd.append('memoNum',$scope.memoRecord.memoNum); 
	    			fd.append('moneyAmount',$scope.memoRecord.moneyAmount);
	    			if($scope.memoRecord.serialNum!=undefined){
	    				fd.append('serialNum',$scope.memoRecord.serialNum); 
	    			}
	    			fd.append('currency',$scope.memoRecord.currency); 
	    			fd.append('paymentStyle',$scope.memoRecord.paymentStyle); 
	    			fd.append('paymentDate',$scope.memoRecord.paymentDate); 
	    			fd.append('buyComId',$scope.memoRecord.buyComId);
	    			fd.append('bank',$scope.memoRecord.bank);
	    			fd.append('accountName',$scope.memoRecord.accountName);
	    			fd.append('accountNumber',$scope.memoRecord.accountNumber);
	    			fd.append('remark',$scope.memoRecord.remark==undefined?'':$scope.memoRecord.remark);
	    			fd.append('verificationMoneyAmount',$scope.memoRecord.verificationMoneyAmount);
	    			$http({
	    				method:'POST',
	    				url:"rest/pay/saveReceiveMemo",
	    				data: fd,
	    				headers: {'Content-Type':undefined}
	    			})   
	    			.success( function ( data )
	    					{
	    				//上传成功的操作
	    				toastr.success("保存收款水单数据成功！");
	    				handle.unblockUI();
	    				$scope.memoRecord= data;
	    				$scope.span = true;
	    				$scope.input = false;
	    				$scope.inputEdit= true;
	    				$(".alert-danger").hide();
	    					});
	    		}
	    		
	    		});
		}
	$scope.jumpToReceiveMemoInfo  = function(serialNum) {
    	$state.go('viewReceiveMemo',{serialNum:serialNum});
    }; 
    $scope.goVerificate= function(serialNum) {//去核销
    	$state.go('viewReceiveMemo',{serialNum:serialNum,type:'verificate'});
    };
    
	//导出收款
	$scope.exportPay = function(){
		handle.blockUI("正在导出数据，请稍后"); 
		window.location.href=$rootScope.basePath+"/rest/pay/exportGatheringMoney";
		handle.unblockUI(); 
	}

	//收款列表
	var table;
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
						order : [ [ 1, "asc" ] ],// 默认排序列及排序方式
						bRetrieve : true,
						// searching: true,//是否过滤检索
						// ordering: true,//是否排序
						lengthMenu : [
						              [ 5, 10, 15,15, 30, -1 ],
						              [ 5, 10, 15, 15,30, "All" ] ],
						              pageLength : 10,// 每页显示数量
						              processing : true,// loading等待框
						              // serverSide: true,
						              ajax: "rest/pay/findReceiveMemoRecord",//加载数据中user表数据
						              "aoColumns": [
						                            { mData: 'serialNum'},
						                            { mData: 'memoNum' },//paymentType
						                            { mData: 'moneyAmount' },//paymentType
						                            { mData: 'currency' },
						                            { mData: 'paymentStyle' },
						                            { mData: 'paymentDate' },
						                            { mData: 'comName'},
						                            { mData: 'remark'},
						                            { mData: 'verificationMoneyAmount'},
						                            { mData: 'remainMoneyAmount'},
						                            { mData: 'status',
						                            	mRender:function(data){
						                            		if(data!=""&&data!=null){
						                            			if(data=='0'){
						                            				return '<span  class="label label-sm label-info ng-scope">待核销</span>';
						                            			}else if(data=='1'){
						                            				return '<span  class="label label-sm label-success ng-scope">已核销</span>';
						                            			}
						                            		}else{
						                            			return "";
						                            		}
						                            	}
						                            },
						                            { mData: 'status'},
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
						                            		return '<a  data-toggle="modal" ng-click="jumpToReceiveMemoInfo(\''+row.serialNum+'\')" ">'+data+'</a>';
						                            	},
						                            	"createdCell": function (td, cellData, rowData, row, col) {
						                            		$compile(td)($scope);
						                            	}
						                            }  ,
						                            {
						                            	'targets' :8,
						                            	'className' : 'dt-body-center',
						                            	'render' : function(data,
						                            			type, row, meta) {
						                            		if(row.currency=='USD'){
						                            			return $filter('currency')(data,'$');
						                            		}else if(row.currency=='RMB'){
						                            			return $filter('currency')(data,'￥');
						                            		}
						                            	},
						                            	
						                            },
						                            {
						                            	'targets' : 9,
						                            	'className' : 'dt-body-center',
						                            	'render' : function(data,
						                            			type, row, meta) {
						                            		if(row.currency=='USD'){
						                            			return $filter('currency')(Number(row.moneyAmount)-Number(row.verificationMoneyAmount),'$');
						                            		}else if(row.currency=='RMB'){
						                            			return $filter('currency')(Number(row.moneyAmount)-Number(row.verificationMoneyAmount),'￥');
						                            		}
						                            	},
						                            	
						                            } ,
						                            {
						                            	'targets' : 11,
						                            	'className' : 'dt-body-center',
						                            	'render' : function(data,
						                            			type, row, meta) {
						                            		if(row.status!=2){
						                            			return '<a href="javascript:void(0);" ng-click="goVerificate(\''+row.serialNum+'\')">核销</a>';
						                            		}else{
						                            			return '';	
						                            		}
						                            		
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
				ReceiveMemoService.delReceiveMemo(ids).then(
						function(data) {
							$('#delUsersModal').modal('hide');// 删除成功后关闭模态框
							$(".modal-backdrop").remove();
							toastr.success("删除成功！");
							$state.go('receiveMemo',{},{reload:true}); // 重新加载datatables数据
						},
						function(errResponse) {
							/*console.error('Error while deleting Users');*/
							alert(123);
						}

				);
			}
		}								
	};

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
	

	// 联系电话(手机/电话皆可)验证 
	 jQuery.validator.addMethod("isPhone", function(value,element) { 
	   var length = value.length; 
	   var mobile = /^(((13[0-9]{1})|(15[0-9]{1}))+\d{8})$/; 
	   var tel = /^\d{3,4}-?\d{7,9}$/; 
	   return this.optional(element) || (tel.test(value) || mobile.test(value)); 

	 }, "请正确填写您的联系电话"); 
	 

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
				memoNum:{required:"收款水单号不能为空！"},
				moneyAmount:{required:"收款金额不能为空！",number:"格式不正确!",minNumber:"最小值为0.01"},
				currency:{required:"币种不能为空！"},
				paymentType:{required:"支付方式不能为空！"},
				paymentDate:{required:"收款日期不能为空！"},
				buyComId:{required:"收款方不能为空！"},
				file:{required:"付款凭证不能为空！"}
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
				file:{
					required:true,
				},
				memoNum:{
					required:true,
				},
				paymentType:{required:true,
				},
				moneyAmount:{
				    required:true,
				    number: true,     //输入必须是数字
                    min: 0.01,
                    minNumber:true
			    },
				currency:{required:true,
				},
				paymentDate:{required:true,
				},
				buyComId:{required:true,
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
	$scope.selectBgInfo=function(){
		loadBgTable();
		console.log($scope.orderSerial);
	}
	$scope.verificateInfo=function(judgeString){
		$('#receivePaymentRecordInfo').modal('show');//显示弹框
		loadVerificateTable();
	}
	 /**
		 * 遍历checkbox计算收款单金额(当前收款单未核销总金额)
		 */
		function CalTotalUnVerificateCount(){
			var inputs=verificateTable.$('input[type="checkbox"]');
			var count=0;
			 for(var i=0;i<inputs.length;i++){
				  var input=inputs[i];
				  count+=Number($(input).attr("name"));
			 }
			 $scope.totalUnVerificateCount=count;
		}
	 var  verificateTable,tableUrl;// 核销弹框
      var loadVerificateTable = function() {
    	  var buyComId=$scope.memoRecord.buyComId;
    	  var  remainMoneyAmount=Number($scope.memoRecord.moneyAmount)-Number($scope.memoRecord.verificationMoneyAmount);//水单余额
    	  $scope.remainMoneyAmount=remainMoneyAmount;
    	  var type='receive';
    	  tableUrl="rest/pay/paymentRecordList?comId="+buyComId+"&type="+type;
               a = 0;
               App.getViewPort().width < App.getResponsiveBreakpoint("md") ? $(".page-header").hasClass("page-header-fixed-mobile") && (a = $(".page-header").outerHeight(!0)) : $(".page-header").hasClass("navbar-fixed-top") ? a = $(".page-header").outerHeight(!0) : $("body").hasClass("page-header-fixed") && (a = 64);
              if(verificateTable!=undefined){
            	  verificateTable.destroy(); 
		 	    	 }
              verificateTable = $("#select_sample_receivePaymentRecord")
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
                 /* destroy:true,*/
                   lengthMenu: [[5, 10, 15, 30, -1], [5, 10, 15, 30, "All"]],
                   pageLength: 5,//每页显示数量
                   processing: true,//loading等待框
//                   serverSide: true,
                   ajax: tableUrl,//加载数据中 
                   "aoColumns": [
                                { mData: 'serialNum' },
                                { mData: 'paymentNum' },
                                { mData: 'paymentType' },
                                { mData: 'playPaymentDate' },
                                { mData: 'applyPaymentAmount' },
                                { mData: 'paymentAmount' },
                                { mData: 'unPaymentAmount' }
                           ],
                  'aoColumnDefs' : [ {
   							'targets' : 0,
   							'searchable' : false,
   							'orderable' : false,
   							
   							'render' : function(data,
   									type, row, meta) {
   								var  unPaymentAmount;
   								if(row.paymentAmount==null){
									unPaymentAmount=Number(row.applyPaymentAmount);
								}else{
									unPaymentAmount=Number(row.applyPaymentAmount)-Number(row.paymentAmount);
								}
   									return  '<label class="mt-checkbox mt-checkbox-single mt-checkbox-outline">'+
	                                     '<input type="checkbox" data-check="false"  name="'+unPaymentAmount+'"       class="checkboxes" ng-click="showUnPaymentAmount(\''+row.serialNum+'\',\''+row.paymentAmount+'\',\''+unPaymentAmount+'\')" id="'+data+'" value="'+data+'" data-set="#select_sample_receivePaymentRecord .checkboxes" />'+
	                                     '<span></span></label>';
   							},
   							"createdCell": function (td, cellData, rowData, row, col) {
   								 $compile(td)($scope);
   						       }
   						},{
								'targets' : 3,
								'render' : function(data,
										type, row, meta) {
   								if(data==null){
   									return row.applyDate;
   								}else{
   									return data;
   								}
								}
							},{
								'targets' : 4,
								'render' : function(data,
										type, row, meta) {
   								if(row.applyCurrency=='人民币'){
   									return  $filter('currency')(data,'￥');
   								}else if(row.applyCurrency=='美元'){
   									return $filter('currency')(data,'$'); 
   								}else if(row.applyCurrency=='欧元'){
   									return $filter('currency')(data,'€'); 
   								}else if(row.applyCurrency=='日元'){
   									return $filter('currency')(data,'￥'); 
   								}
								
								}
							}, {
								'targets' : 5,
								'render' : function(data,
										type, row, meta) {
									if(data==null){
										if(row.applyCurrency=='人民币'){
		   									return  $filter('currency')(row.applyPaymentAmount,'￥');
		   								}else if(row.applyCurrency=='美元'){
		   									return $filter('currency')(row.applyPaymentAmount,'$'); 
		   								}else if(row.applyCurrency=='欧元'){
		   									return $filter('currency')(row.applyPaymentAmount,'€'); 
		   								}else if(row.applyCurrency=='日元'){
		   									return $filter('currency')(row.applyPaymentAmount,'￥'); 
		   								}
	   								}else{
	   									if(row.applyCurrency=='人民币'){
	   	   									return  $filter('currency')(Number(row.applyPaymentAmount)-Number(data),'￥');
	   	   								}else if(row.applyCurrency=='美元'){
	   	   									return $filter('currency')(Number(row.applyPaymentAmount)-Number(data),'$'); 
	   	   								}else if(row.applyCurrency=='欧元'){
	   	   									return $filter('currency')(Number(row.applyPaymentAmount)-Number(data),'€'); 
	   	   								}else if(row.applyCurrency=='日元'){
	   	   									return $filter('currency')(Number(row.applyPaymentAmount)-Number(data),'￥'); 
	   	   								}
	   								}
								}
							},{
								'targets' : 6,
								'className' : 'dt-body-center',
								'render' : function(data,
										type, row, meta) {
									var  unPaymentAmount;
									if(row.paymentAmount==null){
										unPaymentAmount=Number(row.applyPaymentAmount);
									}else{
										unPaymentAmount=Number(row.applyPaymentAmount)-Number(row.paymentAmount);
									}// ng-init="unPaymentAmount'+row.serialNum+'=\''+unPaymentAmount+'\'" 
									return '<input  type="text"  class="form-control"   style="border:none" readonly="readonly"  '+ 
									 ' id="up'+row.serialNum+'" ng-model="unPaymentAmount'+row.serialNum+'"  ng-blur="judgeNumber(\''+remainMoneyAmount+'\',\''+unPaymentAmount+'\',\''+row.serialNum+'\')" />';
									
								},"createdCell": function (td, cellData, rowData, row, col) {
									 $compile(td)($scope);
							    }
							}],
							//stateSave:false,
							"fnInitComplete":function(settings) {//fnInitComplete stateLoadCallback
			                	  CalTotalUnVerificateCount();
			                   }

               });
              $("#select_sample_receivePaymentRecord").find(".group-checkable").change(function() {
			        var e = jQuery(this).attr("data-set"),
			        t = jQuery(this).is(":checked");
			        jQuery(e).each(function() {
			            t ? ($(this).prop("checked", !0), $(this).parents("tr").addClass("active")) : ($(this).prop("checked", !1), $(this).parents("tr").removeClass("active"))
			        })
			    }),
			    $("#select_sample_receivePaymentRecord").on("change", "tbody tr .checkboxes",
			    function() {
			        $(this).parents("tr").toggleClass("active")
			    })
        
			   return verificateTable;
              
             
           };
   		$scope.showUnPaymentAmount=function(serialNum,paymentAmount){//选中checkbox显示输入框
			var value;
			if($scope.totalVerificateCount==undefined){
				$scope.totalVerificateCount=0;
			}else if($scope.totalVerificateCount>=$scope.remainMoneyAmount){
				 toastr.warning("不需再选应收账单！");
				 $("#"+serialNum).attr("checked",false);
				 return;
			}
			if($("#"+serialNum).is(':checked')){
				$("#up"+serialNum).css("border","1px solid");
				$("#up"+serialNum).attr("readonly",false);
			}else{
				$("#up"+serialNum).css("border","none");
				$("#up"+serialNum).attr("readonly",true);
				value=$("#up"+serialNum).val();
				$("#up"+serialNum).val(0);//重新置为0
				$scope.totalVerificateCount=($scope.totalVerificateCount-Number(value));
				/* toastr.warning("重新选择出库,数量为"+value+"!");*/
				
			}
			//$scope.judgeNumber(stockCount,stockOutCount,serialNum);
		}
   		$scope.judgeNumber=function(remainMoneyAmount,unPaymentAmount,serialNum){
			 var value=$("#up"+serialNum).val();//核销金额
			 if(isNaN(value)||value<=0){
				 toastr.warning("核销金额必须为大于0的数字！");
				 return;
			 }
			 if(Number(value)>Number(unPaymentAmount)){
				toastr.warning("当前核销金额不得大于未核销金额！");
				  $("#up"+serialNum).empty();
				 $("#up"+serialNum).focus(); 
				 return;
			 }else if(Number(value)>Number(remainMoneyAmount)){
				 toastr.warning("当前核销金额不得大于水单余额！");
				  $("#up"+serialNum).empty();
				 $("#up"+serialNum).focus(); 
				 return;
			 }
			 var checkboxs=$('input[class="checkboxes"]:checked');
			 var count=0;
			 for(var i=0;i<checkboxs.length;i++){
				  var serialNum=$(checkboxs[i]).val();
				  var value=$("#up"+serialNum).val();
				  count=count+Number(value);//累加核销金额
			 }
			 $scope.totalVerificateCount=count;
			 if(count>remainMoneyAmount){
				 toastr.warning("当前核销总金额不得大于水单余额！");
				 $("#up"+serialNum).empty();
				 $("#up"+serialNum).focus(); 
			 }
		 }
   	    /**
	        * 确认核销
	        */
	       $scope.confirmVerificate= function(){
	    	   var checkboxs=$('input[class="checkboxes"]:checked');
				 if(checkboxs.length==0){
					 toastr.warning("未勾选应收账单"); 
					 return;
				 }
	    	   handle.confirm("确认核销吗？",function(){
	    		   handle.blockUI();
	        		var inputs=verificateTable.$('input[type="checkbox"]:checked');
	        		var params = {};
	        		params.verificationRecords = [];
	        		var param;
	        		for(var i=0;i<inputs.length;i++){
	        			param={};
	        			var input=inputs[i];
	        			param.paymentRecordSerial=$(input).attr("id");
	        			param.receiveMemoSerial=$scope.memoRecord.serialNum;
	        			param.moneyAmount=$("#up"+$(input).attr("id")).val();
	        			params.verificationRecords.push(param);
	        		}
	        		var promise = ReceiveMemoService.saveVerificateData(params);
	        		promise.then(function(data){
	        			toastr.success("确认核销成功");
	        			handle.unblockUI();
	        			$('#receivePaymentRecordInfo').modal('hide');//隐藏弹框
	        			setTimeout(function () {
	        				 $state.go('receiveMemo',{},{reload:true}); //返回列表
		                }, 2000);
		        		
	 	            },function(data){
	 	               //调用承诺接口reject();
	 	            });
	        		
	        	});
	       }
	//销售订单列表
	var table2;
	var loadBgTable= function() {
		var orderSerial=$scope.orderSerial;
		a = 0;
		 if(table2!=undefined){
			 table2.destroy();
		    	 }
		App.getViewPort().width < App.getResponsiveBreakpoint("md") ? $(".page-header").hasClass("page-header-fixed-mobile") && (a = $(".page-header").outerHeight(!0)) : $(".page-header").hasClass("navbar-fixed-top") ? a = $(".page-header").outerHeight(!0) : $("body").hasClass("page-header-fixed") && (a = 64);
		table2= $("#sample_22")
		.DataTable({
			language: {
				aria: {
					sortAscending: ": 以升序排列此列",
					sortDescending: ": 以降序排列此列"
				},
				emptyTable: "空表",
				info: "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
				infoEmpty: "没有数据",
				// infoFiltered: "(filtered1 from _MAX_ total entries)",
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
			order: [[1, "desc"]],// 默认排序列及排序方式
			searching: true,// 是否过滤检索
			ordering:  true,// 是否排序
			lengthMenu: [[5, 10, 15, 30, -1], [5, 10, 15, 30, "All"]],
			pageLength: 5,// 每页显示数量
			processing: true,// loading等待框
			//"rest/invoice/getMaterielList?orderSerial="+orderSerial+"&deliverSerial="+deliverSerial
			ajax:"rest/customsForm/getCustomsFormList?type=declaration"+"&orderSerial="+orderSerial,// 加载数据中
			"aoColumns": [
			              { mData: 'serialNum' },
			              { mData: 'customsFormNum' },
			              { mData: 'orderInfo.rate',
			            		mRender:function(data){
	                          		return data+"%";
	                          	}
			            	  },
                          { mData: 'addedTax' },
                          { mData: 'customsAmount' },
                          { mData: 'customsAmount'}

			              ],
			              'aoColumnDefs' : [ {
			            	  'targets' : 0,
			            	  'searchable' : false,
			            	  'orderable' : false,
			            	  'render' : function(data,
			            			  type, full, meta) {
			            		  return '<input type="radio" name="serialNum" value="'+data+'" ng-click="getData(\''+full.serialNum+'\',\''+full.customsFormNum+'\',\''+full.addedTax+'\',\''+full.customsAmount+'\',\''+full.orderInfo.rate+'\')" '  
			            		  + $('<div/>')
			            		  .text(
			            				  data)
			            				  .html()
			            				  + '">';
			            	  },
			            	  "createdCell": function (td, cellData, rowData, row, col) {
			            		  $compile(td)($scope);
			            	  }
			              }, {
			            	  'targets' : 5,
			            	  'searchable' : false,
			            	  'orderable' : false,
			            	  'render' : function(data,
			            			  type, full, meta) {
			            		  return  Number(data)+Number(full.addedTax);
			            	  }
			            	
			              }]

		}).on('order.dt',
				function() {
			console.log('排序');
		})

		// 确认选择开始***************************************
		var ids = '';
		$scope.confirmSelectBgInfo = function() {

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
								} else
									ids = ids + ','
									+ this.value;
							}
						}
					});
			if(ids==''){
				toastr.warning('请选择一个报关单！');return;
			}
			/*if($scope.paymentRecord.customsFormSerial==ids){
				toastr.warning('当前报关单已选,请选择其他报关单！');
				return;
			}*/
			$scope.paymentRecord.customsFormSerial=ids;
			$scope.paymentRecord.qgOrBgNum=$scope.customsForm1.qgOrBgNum;
			$scope.paymentRecord.addedTax=$scope.customsForm1.addedTax;
			$scope.paymentRecord.customsAmount=$scope.customsForm1.customsAmount;
			$scope.paymentRecord.qgOrBgNum=$scope.customsForm1.qgOrBgNum;
			$scope.paymentRecord.rate=$scope.customsForm1.rate+"%";
			$scope.paymentRecord.totalMoney=$scope.customsForm1.totalMoney;
			$scope.chnTotalMoney=convertCurrency($scope.paymentRecord.totalMoney);
			
			$('#basicBgInfo').modal('hide');// 删除成功后关闭模态框
			$(".modal-backdrop").remove();
		};
		$scope.getData=function(customsFormSerial,customsFormNum,addedTax,customsAmount,rate){
			$scope.customsForm1={};
			$scope.customsForm1.serialNum=customsFormSerial;
			$scope.customsForm1.addedTax=addedTax;
			$scope.customsForm1.rate=rate;
			$scope.customsForm1.customsAmount=customsAmount;
			$scope.customsForm1.qgOrBgNum=customsFormNum;
			$scope.customsForm1.totalMoney=Number(customsAmount)+Number(addedTax);
		}
		
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
		// ***************************************
	};

}]);


