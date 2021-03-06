angular.module('MetronicApp').controller('ReceiveMemoController', ['$rootScope','$scope','$http', 'settings', 'ReceiveMemoService','commonService','$state','$compile','$stateParams','$filter','FileUploader',
                                                           function($rootScope,$scope,$http,settings,ReceiveMemoService,commonService,$state,$compile,$stateParams,$filter,FileUploader) {
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
				$scope.getPayInfo($stateParams.serialNum);	
			    }else{
	$rootScope.setNumCode("IM",function(newCode){
		$scope.paymentRecord={};
			$scope.paymentRecord.paymentNum= newCode;//收款单号
			$scope.paymentRecord.playPaymentDate= $filter('date')(new Date(), 'yyyy-MM-dd');//计划收款日期
			$scope.paymentRecord.applyDate= $filter('date')(new Date(), 'yyyy-MM-dd');//申请日期
			$scope.paymentRecord.applyDateForBg=$filter('date')(new Date(), 'yyyy-MM-dd');//报关申请日期
			$scope.paymentRecord.isBill='0';
			getCurrentUser();
		});
			    }
	
	}else if($state.current.name=="viewGatheringMoney"){
		$scope.getPayInfo($stateParams.serialNum);	
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
	$scope.getPayInfo  = function(serialNum) {
		GatheringMoneyService.selectPay(serialNum).then(
      		     function(data){
      		    	$scope.paymentRecord=data;
      		    	$scope.saleOrder={};
      		    	$scope.saleOrder.orderAmount=data.orderAmount;
      		    	$scope.saleOrder.orderNum=data.orderNum;
      		    	$scope.file=data.fileList;
      		    	$scope.orderSerial=data.orderSerial;
      		    	for(var i=0;i<data.fileList.length;i++){
      		    	$scope.file[i].paymentSerial = $scope.pay.serialNum;
      		    	}
      		    	
      		    	if($scope.paymentRecord.paymentType=='报关'){
      		    		$scope.isBG=true;
      		    	   $scope.paymentRecord.playPaymentDate=data.applyDate;
      		    		$scope.paymentRecord.customsFormSerial=data.customsFormSerial;
      		    		$scope.paymentRecord.totalMoney=$scope.paymentRecord.applyPaymentAmount;
      		    		$scope.chnTotalMoney=convertCurrency($scope.paymentRecord.applyPaymentAmount);
      		    		$scope.paymentRecord.applyDateForBg=$scope.paymentRecord.applyDate;
      		    	}else{
      		    		$scope.chnAmount=$scope.paymentRecord.applyPaymentAmount;
      		    		$scope.applyPaymentAmountChn=convertCurrency($scope.paymentRecord.applyPaymentAmount);
      		    	}
      		    	$scope.clauseSettlementList=data.clauseSettList;
      		    	$scope.paymentRecord.payType=data.payType;
      		    	$scope.comFinances=data.comFinances//收付款信息
      		    	$scope.paymentRecord.bank=data.bank;
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
       $scope.downloadFile = function(obj){
    	   if(!handle.isNull(obj)){
    		   window.location.href= $rootScope.basePath+"/rest/fileOperate/downloadFile?fileName="+encodeURI(encodeURI(obj.file));
    	   }else{
    		   toastr.error("下载失败!");
    	   }
       }
       
       $scope.downloadFile1 = function(str){
    	   debugger
    		   window.location.href= $rootScope.basePath+"/rest/fileOperate/downloadFile?fileName="+encodeURI(encodeURI(str));
       }
       
       $scope.removefile = function(index){
    	   $scope.file[index].file = "";
       }
        
	  //********附件  end****************//

	//添加收款
	$scope.saveBasicInfo=function (){
		if($('#form_sample_1').valid()){
			 $rootScope.judgeIsExist("payOrReceive",$scope.paymentRecord.paymentNum, $scope.paymentRecord.serialNum,function(result){
	    			var 	isExist = result;
	    		debugger;
	    		if(isExist){
	    			 if(judgeString=='receive'){
	    				 toastr.error('应收账单号重复！');
	    			}
	    			return;
	    		}else{
	    			var fd = new FormData();
	    			handle.blockUI();
	    			fd.append('paymentNum',$scope.paymentRecord.paymentNum); 
	    			fd.append('paymentType',$scope.paymentRecord.paymentType);
	    			if($scope.paymentRecord.serialNum!=undefined){
	    				fd.append('serialNum',$scope.paymentRecord.serialNum); 
	    			}
	    			fd.append('orderSerial',$scope.orderSerial); 
	    			fd.append('buyComId',buyComId);
	    			fd.append('applyCurrency',$scope.paymentRecord.applyCurrency);
	    			if($scope.paymentRecord.paymentType=='报关'){
	    				fd.append('applyPaymentAmount',$scope.paymentRecord.totalMoney);
	    				fd.append('applyDate',$scope.paymentRecord.applyDateForBg);//报关时申请日期
	    				fd.append('customsFormSerial',$scope.paymentRecord.customsFormSerial);//报关关时报关流水
	    			}else{
	    				fd.append('applyPaymentAmount',$scope.paymentRecord.applyPaymentAmount);
	    				fd.append('playPaymentDate',$scope.paymentRecord.playPaymentDate);
	    				fd.append('payType',$scope.paymentRecord.payType);
	    				fd.append('paymentNode',$scope.paymentRecord.paymentNode);
	    				/*fd.append('nodeNum',$scope.paymentRecord.nodeNum);*/
	    				fd.append('billType',$scope.paymentRecord.billType); 
	    				fd.append('accountPeriod',$scope.paymentRecord.accountPeriod); 
	    				fd.append('deliveryRate',$scope.paymentRecord.deliveryRate);
	    				fd.append('isBill',$scope.paymentRecord.isBill==undefined?'0':$scope.paymentRecord.isBill);
	    				fd.append('applyDate',$scope.paymentRecord.applyDate);
	    			}
	    			fd.append('applicant',$scope.paymentRecord.applicant);
	    			fd.append('applyDept',$scope.paymentRecord.applyDept==undefined?'':$scope.paymentRecord.applyDept);
	    			fd.append('remark',$scope.paymentRecord.remark==undefined?'':$scope.paymentRecord.remark);
	    			
	    			fd.append('payee',$scope.paymentRecord.payee);
	    			fd.append('contact',$scope.paymentRecord.contact);
	    			fd.append('contactNum',$scope.paymentRecord.contactNum);
	    			fd.append('bank',$scope.paymentRecord.bank);
	    			fd.append('accountName',$scope.paymentRecord.accountName);
	    			fd.append('accountNumber',$scope.paymentRecord.accountNumber);
	    			
	    			
	    			$http({
	    				method:'POST',
	    				url:"rest/pay/savePaymentRecord",
	    				data: fd,
	    				headers: {'Content-Type':undefined}
	    			})   
	    			.success( function ( data )
	    					{
	    				//上传成功的操作
	    				toastr.success("保存应收款数据成功！");
	    				handle.unblockUI();
	    				$scope.paymentRecord= data;
	    				$scope.span = true;
	    				$scope.input = false;
	    				$scope.inputEdit= true;
	    				if($scope.paymentRecord.customsFormSerial!=null){
	    					$scope.isBG = true;
	    				}
	    				$scope.applyPaymentAmountChn=convertCurrency($scope.paymentRecord.applyPaymentAmount);
	    				$(".alert-danger").hide();
	    					});
	    		}
	    		
	    		});
		}
	}
	
	
	//更新收款
	$scope.editBasicInfo=function (){
		if($('#form_sample_1').valid()){
			var fd = new FormData();
			fd.append('serialNum',$scope.pay.serialNum);
			fd.append('paymentType',$scope.pay.paymentType);
			fd.append('paymentNum',$scope.pay.paymentNum); 
			fd.append('orderSerial',$scope.pay.orderSerial); 
			if(buyComId!=null){
				fd.append('buyComId',buyComId);
			}else{
				fd.append('buyComId',$scope.pay.buyComId);
			}
			fd.append('applyPaymentAmount',$scope.pay.applyPaymentAmount); 
			fd.append('applyCurrency',$scope.pay.applyCurrency);
			fd.append('playPaymentDate',$scope.pay.playPaymentDate);
			fd.append('payType',$scope.pay.payType);
			fd.append('paymentNode',$scope.pay.paymentNode);
			fd.append('nodeNum',$scope.pay.nodeNum);
			fd.append('billStyle',"先款后票"); 
			fd.append('isBill',$("input[name='isBill']:checked").val());
			fd.append('applyDate',$scope.pay.applyDate);
			fd.append('applicant',$scope.pay.applicant);
			fd.append('applyDept',$scope.pay.applyDept);
			fd.append('remark',$scope.pay.remark);
			
			fd.append('payee',$scope.pay.payee);
			fd.append('contact',$scope.pay.contact);
			fd.append('contactNum',$scope.pay.contactNum);
			fd.append('bank',$scope.pay.bank);
			fd.append('accountName',$scope.pay.accountName);
			fd.append('accountNumber',$scope.pay.accountNumber);
			$http({
				method:'POST',
				url:"rest/pay/savePaymentRecord",
				data: fd,
				headers: {'Content-Type':undefined}
			})   
			.success( function ( data )
					{
				//上传成功的操作
				toastr.success("保存应收款数据成功！");
				handle.unblockUI();
				$scope.pay= data;
				$scope.span = true;
				$scope.input = false;
				$scope.applyPaymentAmountChn=convertCurrency($scope.pay.applyPaymentAmount);
				$(".alert-danger").hide();
					});
		}
	}

	$scope.jumpToGetPayInfo  = function(serialNum) {
    	$state.go('viewGatheringMoney',{serialNum:serialNum});
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
						                            { mData: 'paymentNum' },//paymentType
						                            { mData: 'paymentType' },//paymentType
						                            { mData: 'applyCurrency' },
						                            { mData: 'applyPaymentAmount' },
						                            { mData: 'playPaymentDate' },
						                            { mData: 'buyComId'},
						                            { mData: 'paymentDate'},
						                            { mData: 'paymentAmount'},
						                            { mData: 'isBill',
						                            	mRender:function(data){
						                            		if(data=='0'){
						                            				return '否';
						                            		}else{
						                            			return "是";
						                            		}
						                            	}
						                            	},
						                            { mData: 'status',
						                            	mRender:function(data){
						                            		if(data!=""&&data!=null){
						                            			if(data=='0'){
						                            				return '初始';
						                            			}else if(data=='1'){
						                            				return '已确认';
						                            			}
						                            		}else{
						                            			return "";
						                            		}
						                            	}
						                            },
						                            { mData: 'status',
						                            	mRender:function(data){
						                            		return "";
						                            	}
						                            	},
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
						                            		return '<a  data-toggle="modal" ng-click="jumpToGetPayInfo(\''+row.serialNum+'\')" ">'+data+'</a>';
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
				GatheringMoneyService.delPaymentRecord(ids).then(
						function(data) {
							$('#delUsersModal').modal('hide');// 删除成功后关闭模态框
							$(".modal-backdrop").remove();
							toastr.success("删除成功！");
							$state.go('gatheringMoneyRecord',{},{reload:true}); // 重新加载datatables数据
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
				paymentNum:{required:"付款编号不能为空！",rangelength:jQuery.validator.format("付款编号位数必须在{0}到{1}字符之间！")},
				paymentType:{required:"付款类型不能为空！"},
				orderNum:{required:"关联订单不能为空！"},
				/*applyPaymentAmount:{required:"申请付款金额不能为空！",number:"请输入数字（正数）！",min:"必须是正数，且不小于0.01！",minNumber:"小数点后最多为两位！"},*/
				applyCurrency:{required:"申请币种不能为空！"},
				playPaymentDate:{required:"申请付款日期不能为空！"},
				payType:{required:"支付类型不能为空！"},
				paymentNode:{required:"支付节点不能为空！"},
				nodeNum:{required:"支付节点不能为空！"},
				applicant:{required:"申请人不能为空！"},
				applyDate:{required:"申请日期不能为空！"},
				applyDept:{required:"申请部门不能为空！"},
				payee:{required:"收款方不能为空！"},
				contact:{required:"收款方联系人不能为空,请选择！"},
				qgOrBgNum:{required:"报关单号不能为空！"},
				contactNum:{required:"联系电话不能为空！",isPhone:"请正确填写您的联系电话！"},
				bank:{required:"收款银行不能为空！"},
				accountName:{required:"户名不能为空！"},
				accountNumber:{required:"账号不能为空！",creditcard:"请输入正确的银行账号！"},
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
				qgOrBgNum:{
					required:true,
				},
				paymentNum:{required:true,
					rangelength:[3,20]
				},
				paymentType:{required:true,
				},
				orderNum:{required:true,
				},
			/*	applyPaymentAmount:{
				    required:true,
				    number: true,     //输入必须是数字
                    min: 0.01,
                    minNumber:true
			    },*/
				applyCurrency:{required:true,
				},
				playPaymentDate:{required:true,
				},
				payType:{required:true,
				},
				paymentNode:{required:true,
				},
				nodeNum:{required:true,
				},
				/*applyDept:{required:true,
				},*/
				payee:{required:true,
				},
				contact:{required:true,
				},
				contactNum:{
					required:true,
					isPhone:true
				},
				bank:{required:true,
				},
				accountName:{required:true,
				},
				accountNumber: {
					required:true,
					creditcard:true
				},
				
				
				applicant:{required:true,
				},
				applyDate:{required:true,
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
	$scope.selectBgInfo=function(){
		loadBgTable();
		console.log($scope.orderSerial);
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
	$scope.changeValue=function(){
		for(var i in  $scope.comFinances){
			if($scope.comFinances[i].openingBank==$scope.paymentRecord.bank){
				$scope.paymentRecord.accountName=$scope.comFinances[i].accountName;
				$scope.paymentRecord.accountNumber=$scope.comFinances[i].accountNumber;
				return;
			}
		}
		$scope.paymentRecord.accountName='';
		$scope.paymentRecord.accountNumber='';
	}
	 $scope.exportPayReceiveMemo = function(type){
    	 handle.blockUI("正在导出数据，请稍后"); 
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
    	 window.location.href=$rootScope.basePath+"/rest/pay/exportPayReceiveMemo?type="+type+"&serialNums="+ids;
    	 handle.unblockUI(); 
       }
}]);


