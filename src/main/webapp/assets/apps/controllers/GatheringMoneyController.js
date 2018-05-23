angular.module('MetronicApp').controller('GatheringMoneyController', ['$rootScope','$scope','$http', 'settings', 'ReceiveMemoService','GatheringMoneyService','commonService','$state','$compile','$stateParams','$filter','FileUploader',
                                                           function($rootScope,$scope,$http,settings,ReceiveMemoService,GatheringMoneyService,commonService,$state,$compile,$stateParams,$filter,FileUploader) {
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
		
	if($state.current.name=="addGatheringMoney"||$state.current.name=="addForSaleOrderGatheringMoney"){
		loadMainTable1();
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
			$scope.paymentRecord.applyCurrency='人民币';
			$scope.paymentRecord.status='0';
			getCurrentUser();
		});
	if(!isNull($stateParams.orderSerialNum)){//从销售订单到收款
		$scope.getSaleOrderInfo($stateParams.orderSerialNum);
	}
			    }
	
	}else if($state.current.name=="viewGatheringMoney"||$state.current.name=="viewForSaleOrderGatheringMoney"){
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
			toastr.warning('请选择一个收款！');return;
		}else if(ids=='more'){
			toastr.warning('只能选择一个收款！');return;
		}
		$state.go('addGatheringMoney',{serialNum:ids});
	};

	$scope.clearNoNumPoint = function(obj,attr){
	   	 //先把非数字的都替换掉，除了数字和.
	   	 obj[attr] = obj[attr].replace(/[^\d.]/g,"");
	   	 //必须保证第一个为数字而不是.
	   	 obj[attr] = obj[attr].replace(/^\./g,"");
	   	 //保证只有出现一个.而没有多个.
	   	 obj[attr] = obj[attr].replace(/\.{2,}/g,"");
	   	 //保证.只出现一次，而不能出现两次以上
	   	 obj[attr] = obj[attr].replace(".","$#$").replace(/\./g,"").replace("$#$",".");
	   //保证小数点后只有9位
    	 obj[attr] = obj[attr].replace(/([0-9]+\.[0-9]{9})[0-9]*/,"$1");
		 }
		$scope.setApplyPaymentAmount=function(paymentRecord,deliveryRate){
			$scope.clearNoNumPoint(paymentRecord,deliveryRate);
			if(Number($scope.paymentRecord.deliveryRate)<0||Number($scope.paymentRecord.deliveryRate)>100){
				showToastr('toast-top-center', 'warning', '0-100之间！');
				$scope.paymentRecord.deliveryRate=null;
				return;
			}
			if(Number($scope.paymentRecord.deliveryRate)>Number($scope.canTotalRate)){
				showToastr('toast-top-center', 'warning', '当前可设最大支付比率为'+$scope.canTotalRate+"!");
				$scope.paymentRecord.deliveryRate=null;
				return;
			}
			$scope.paymentRecord.applyPaymentAmount=($scope.paymentRecord.deliveryRate*$scope.saleOrder.orderAmount/100).toFixed(2);
			$scope.chnAmount=$scope.paymentRecord.applyPaymentAmount;
	    		$scope.applyPaymentAmountChn=convertCurrency($scope.paymentRecord.applyPaymentAmount);
		}
	//根据参数查询对象
	$scope.getPayInfo  = function(serialNum) {
		GatheringMoneyService.selectPay(serialNum).then(
      		     function(data){
      		    	$scope.paymentRecord=data.paymentRecord;
      		    	$scope.saleOrder={};
      		    	$scope.saleOrder.orderAmount=$scope.paymentRecord.orderAmount;
      		    	$scope.saleOrder.orderNum=$scope.paymentRecord.orderNum;
      		    	$scope.file=$scope.paymentRecord.fileList;
      		    	$scope.orderSerial=$scope.paymentRecord.orderSerial;
      		    	for(var i=0;i<$scope.paymentRecord.fileList.length;i++){
      		    	$scope.file[i].paymentSerial = $scope.pay.serialNum;
      		    	}
      		    	
      		    	if($scope.paymentRecord.paymentType=='报关'){
      		    		$scope.isBG=true;
      		    	   $scope.paymentRecord.playPaymentDate=$scope.paymentRecord.applyDate;
      		    		$scope.paymentRecord.customsFormSerial=$scope.paymentRecord.customsFormSerial;
      		    		$scope.paymentRecord.totalMoney=$scope.paymentRecord.applyPaymentAmount;
      		    		$scope.chnTotalMoney=convertCurrency($scope.paymentRecord.applyPaymentAmount);
      		    		$scope.paymentRecord.applyDateForBg=$scope.paymentRecord.applyDate;
      		    		$scope.applyPaymentAmountChn=convertCurrency($scope.paymentRecord.applyPaymentAmount);
      		    	}else{
      		    		$scope.chnAmount=$scope.paymentRecord.applyPaymentAmount;
      		    		$scope.applyPaymentAmountChn=convertCurrency($scope.paymentRecord.applyPaymentAmount);
      		    	}
      		    	$scope.clauseSettlementList=$scope.paymentRecord.clauseSettList;
      		    	$scope.paymentRecord.payType=$scope.paymentRecord.payType;
      		    	$scope.comFinances=$scope.paymentRecord.comFinances//收付款信息
      		    	$scope.paymentRecord.bank=$scope.paymentRecord.bank;
      		    	$scope.verificationList=data.rvList;
      		    	$scope.queryForPage();
      		     },
      		     function(error){
      		         toastr.error('连接服务器出错,请登录重试！');
      		     }
      		 );
    	
    }; 
	$scope.verificateInfo=function(){
			$('#receiveMemoInfo').modal('show');//显示弹框
		loadVerificateTable('receive');
	}
    $scope.calcTotalData=function() {//统计核算记录详情信息
    	if($scope.verificationList!=undefined&&$scope.verificationList.length>0){
    		var totalPaymentAmount=0;
    		for(var i=0;i<$scope.verificationList.length;i++){
    			totalPaymentAmount+=Number($scope.verificationList[i].moneyAmount);
    		}
    		$scope.totalPaymentAmount=totalPaymentAmount;
    }else{
    	$scope.totalPaymentAmount=0;
    }
  }
    /**
	 * 遍历checkbox计算收款单金额(当前收款水单余额总金额)
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
    
	 var  verificateTable,tableUrl,comId,tableId;// 核销弹框
     var loadVerificateTable = function(judgeString) {
   		  comId=$scope.paymentRecord.buyComId;
   		  tableId="select_sample_receiveMemo";
   	  var  remainMoneyAmount=(Number($scope.paymentRecord.applyPaymentAmount)-Number($scope.paymentRecord.paymentAmount)).toFixed(2);//应收款单余额
   	  $scope.remainMoneyAmount=remainMoneyAmount;
   	  tableUrl="rest/pay/memoRecordList?comId="+comId+"&type="+judgeString;
              a = 0;
              App.getViewPort().width < App.getResponsiveBreakpoint("md") ? $(".page-header").hasClass("page-header-fixed-mobile") && (a = $(".page-header").outerHeight(!0)) : $(".page-header").hasClass("navbar-fixed-top") ? a = $(".page-header").outerHeight(!0) : $("body").hasClass("page-header-fixed") && (a = 64);
             if(verificateTable!=undefined){
           	  verificateTable.destroy(); 
		 	    	 }
           
             verificateTable = $("#"+tableId)
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
//                  serverSide: true,
                  ajax: tableUrl,//加载数据中 
                  "aoColumns": [
                               { mData: 'serialNum' },
                               { mData: 'memoNum' },
                               { mData: 'moneyAmount' },
                               { mData: 'paymentStyle' },
                               { mData: 'paymentDate' },
                               { mData: 'verificationMoneyAmount' },
                               { mData: 'remainMoneyAmount' }
                          ],
                 'aoColumnDefs' : [ {
  							'targets' : 0,
  							'searchable' : false,
  							'orderable' : false,
  							
  							'render' : function(data,
  									type, row, meta) {
  								var  unPaymentAmount;
  								if(isNull(row.verificationMoneyAmount)){
									unPaymentAmount=Number(row.moneyAmount);
								}else{
									unPaymentAmount=Number(row.moneyAmount)-Number(row.verificationMoneyAmount);
								}
  									return  '<label class="mt-checkbox mt-checkbox-single mt-checkbox-outline">'+
	                                     '<input type="checkbox" data-check="false"  name="'+unPaymentAmount+'"       class="checkboxes" ng-click="showUnPaymentAmount(\''+row.serialNum+'\',\''+unPaymentAmount+'\')" id="'+data+'" value="'+data+'" data-set="#select_sample_receivePaymentRecord .checkboxes" />'+
	                                     '<span></span></label>';
  							},
  							"createdCell": function (td, cellData, rowData, row, col) {
  								 $compile(td)($scope);
  						       }
  						},{
								'targets' : 2,
								'render' : function(data,
										type, row, meta) {
									if(row.currency=='RMB'||row.currency=='人民币'){
	  									return  $filter('currency')(data,'￥');
	  								}else if(row.currency=='USD'||row.currency=='美元'){
	  									return $filter('currency')(data,'$'); 
	  								}else{
	  									return $filter('currency')(data,''); 
	  								}
								}
							},{
								'targets' : 5,
								'render' : function(data,
										type, row, meta) {
									if(row.currency=='RMB'||row.currency=='人民币'){
										return  $filter('currency')(Number(row.moneyAmount)-Number(data),'￥');
	  								}else if(row.currency=='USD'||row.currency=='美元'){
	  									return  $filter('currency')(Number(row.moneyAmount)-Number(data),'$');
	  								}else{
	  									return $filter('currency')(Number(row.moneyAmount)-Number(data),''); 
	  								}
									
								}
							},{
								'targets' : 6,
								'className' : 'dt-body-center',
								'render' : function(data,
										type, row, meta) {
									var  unPaymentAmount;
									if(row.verificationMoneyAmount==null){
										unPaymentAmount=Number(row.moneyAmount);
									}else{
										unPaymentAmount=Number(row.moneyAmount)-Number(row.verificationMoneyAmount);
									}// ng-init="unPaymentAmount'+row.serialNum+'=\''+unPaymentAmount+'\'"  
									return '<input  type="text"  class="form-control"   style="border:none" readonly="readonly"  '+ 
									 ' id="up'+row.serialNum+'"   ng-model="unPaymentAmount'+row.serialNum+'" ng-blur="judgeNumber(\''+remainMoneyAmount+'\',\''+unPaymentAmount+'\',\''+row.serialNum+'\')" />';
									
								},"createdCell": function (td, cellData, rowData, row, col) {
									 $compile(td)($scope);
							    }
							}],
							//stateSave:false,
							"fnInitComplete":function(settings) {//fnInitComplete stateLoadCallback
			                	  CalTotalUnVerificateCount();
			                   }

              });
             $("#select_sample_receiveMemo").find(".group-checkable").change(function() {
			        var e = jQuery(this).attr("data-set"),
			        t = jQuery(this).is(":checked");
			        jQuery(e).each(function() {
			            t ? ($(this).prop("checked", !0), $(this).parents("tr").addClass("active")) : ($(this).prop("checked", !1), $(this).parents("tr").removeClass("active"))
			        })
			    }),
			    $("#select_sample_receiveMemo").on("change", "tbody tr .checkboxes",
			    function() {
			        $(this).parents("tr").toggleClass("active")
			    })
       
			   return verificateTable;
             
            
          };
  		$scope.showUnPaymentAmount=function(serialNum,unPaymentAmount){//选中checkbox显示输入框
			var value;
			/*if(isNull(unPaymentAmount)){
				unPaymentAmount=0;
			}*/
			if($scope.totalVerificateCount==undefined){
				$scope.totalVerificateCount=0;
			}else if($scope.totalVerificateCount>=$scope.remainMoneyAmount){
				 toastr.warning("不需再选收款水单！");
				 $("#"+serialNum).attr("checked",false);
				 return;
			}
			if($("#"+serialNum).is(':checked')){
				$("#up"+serialNum).css("border","1px solid");
				$("#up"+serialNum).attr("readonly",false);
				if(Number($scope.remainMoneyAmount)>Number(unPaymentAmount)){
//					$("#up"+serialNum).val(unPaymentAmount);
					$scope['unPaymentAmount'+serialNum]=unPaymentAmount;
				}else{
					$scope['unPaymentAmount'+serialNum]=$scope.remainMoneyAmount;
//					$("#up"+serialNum).val(remainMoneyAmount);
				}
				$scope.judgeNumber($scope.remainMoneyAmount,unPaymentAmount,serialNum);
			}else{
				$("#up"+serialNum).css("border","none");
				$("#up"+serialNum).attr("readonly",true);
				value=$("#up"+serialNum).val();
				$("#up"+serialNum).val(0);//重新置为0
				$scope.totalVerificateValue();
				/*$scope.totalVerificateCount();*/
				/*$scope.totalVerificateCount=($scope.totalVerificateCount-Number(value));*/
				/* toastr.warning("重新选择出库,数量为"+value+"!");*/
				
			}
		
			//$scope.judgeNumber(stockCount,stockOutCount,serialNum);
		}
  		
  		$scope.judgeNumber=function(remainMoneyAmount,unPaymentAmount,serialNum){
			 var value=$scope['unPaymentAmount'+serialNum];//核销金额
			 if(isNaN(value)||value<=0){
				 /*toastr.warning("核销金额必须为大于0的数字！");*/
				 $("#up"+serialNum).val(remainMoneyAmount);
				 return;
			 }
			 if(Number(value)>Number(unPaymentAmount)){
				toastr.warning("当前核销金额不得大于水单余额！");
//				  $("#up"+serialNum).empty();
				 $("#up"+serialNum).val(0);
				 $("#up"+serialNum).focus(); 
				 return;
			 }else if(Number(value)>Number(remainMoneyAmount)){
				 toastr.warning("当前核销金额不得大于收款单余额！");
//				  $("#up"+serialNum).empty();
				 $("#up"+serialNum).val(remainMoneyAmount);
				 $("#up"+serialNum).focus(); 
				 return;
			 }
			/* var checkboxs=$('input[class="checkboxes"]:checked');
			 var count=0;
			 for(var i=0;i<checkboxs.length;i++){
				  var serialNum=$(checkboxs[i]).val();
				  var value=$("#up"+serialNum).val();
				  count=count+Number(value);//累加核销金额
			 }*/
			 /*$scope.totalVerificateCount=count;
			 $scope.totalVerificateCount();*/
			 $scope.totalVerificateValue();
			 if($scope.totalVerificateCount>remainMoneyAmount){
				 toastr.warning("当前核销总金额不得大于收款水单余额！");
				 $("#up"+serialNum).val(0);
				 $("#up"+serialNum).focus(); 
			 }
		 }
  		
  		$scope.totalVerificateValue=function(){
  			 var checkboxs=$('input[class="checkboxes"]:checked');
			 var count=0;
			 for(var i=0;i<checkboxs.length;i++){
				  var serialNum=$(checkboxs[i]).val();
				  var value=$("#up"+serialNum).val();
				  count=count+Number(value);//累加核销金额
			 }
			 $scope.totalVerificateCount=count;
			 return count;
  		}
  	    /**
	        * 确认核销
	        */
	       $scope.confirmVerificate= function(){
	    	   var checkboxs=$('input[class="checkboxes"]:checked');
				 if(checkboxs.length==0){
					 toastr.warning("未勾选收款水单"); 
					 return;
				 }
					var inputs=verificateTable.$('input[type="checkbox"]:checked');
				 for(var i=0;i<inputs.length;i++){
	        			param={};
	        			var input=inputs[i];
	        			param.moneyAmount=$("#up"+$(input).attr("id")).val();
	        			if(isNull(param.moneyAmount)||param.moneyAmount==0){
	        			toastr.warning("请输入正确的核销金额!"); 
	   					return;
	        			}
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
	        			param.receiveMemoSerial=$(input).attr("id");//
	        			param.paymentRecordSerial=$scope.paymentRecord.serialNum;
	        			param.moneyAmount=$("#up"+$(input).attr("id")).val();
	        			params.verificationRecords.push(param);
	        		}
	        		var promise = ReceiveMemoService.saveVerificateData(params);
	        		promise.then(function(data){
	        			toastr.success("确认核销成功");
	        			handle.unblockUI();
	        			$('#receiveMemoInfo').modal('hide');//隐藏弹框
	        			setTimeout(function () {
	        				 $state.go('gatheringMoneyRecord',{},{reload:true}); //返回列表
		                }, 2000);
		        		
	 	            },function(data){
	 	               //调用承诺接口reject();
	 	            });
	        		
	        	});
	       }
    /********************************核算记录模糊检索及分页 START *********************************/
	 /** *************核算记录明细可检索化  start*************** */
	 $scope.pageIndex = 1; //记录当前页
	 $scope.pageSize = '10'; //每页的记录数
	 $scope.totalPage = '1'; //记录总页数
	 $scope.dispalyVerificationRecord = [];//页面显示结果
	 $scope.filterVerificationRecord = [];//查询筛选结果
	 
	 $scope.createFilterList = function(){
		 $scope.filterVerificationRecord = [];
		if($scope.verificationList.length>0&&$scope.queryStr&&!isNull($scope.queryStr)){
			for(var i = 0;i <$scope.verificationList.length;i++){// data.data为选择的标准物料
				if(($scope.verificationList)[i].memoRecord.memoNum.indexOf($scope.queryStr)>=0){
					$scope.filterVerificationRecord.push(angular.copy(($scope.verificationList)[i]));
				}else if(($scope.verificationList)[i].memoRecord.currency.indexOf($scope.queryStr)>=0){
					$scope.filterVerificationRecord.push(angular.copy(($scope.verificationList)[i]));
				}else if(($scope.verificationList)[i].memoRecord.paymentStyle.indexOf($scope.queryStr)>=0){
					$scope.filterVerificationRecord.push(angular.copy(($scope.verificationList)[i]));
				}
			}
		}else{
			$scope.filterVerificationRecord = angular.copy($scope.verificationList);
		}
		
	 };
	 
	 $scope.createDispalyList = function(){
		 $scope.dispalyVerificationRecord = $scope.filterVerificationRecord.slice(
				 ($scope.pageIndex-1)*$scope.pageSize,
				 $scope.pageIndex*$scope.pageSize);
		 
		 $scope.totalPage = Math.ceil($scope.filterVerificationRecord.length/$scope.pageSize);
	 };
	 
	 $scope.queryForPage=function(){
		 $scope.createFilterList();
		 $scope.pageIndex = 1; //设置为第一页
		 $scope.createDispalyList();
	 };
	 
	 $scope.link2ThisPage = function(index){
		 $scope.pageIndex = index;
		 $scope.createDispalyList();
	 }
	 
	 $scope.link2PreviousPage = function(){
		 $scope.pageIndex--;
		 $scope.createDispalyList();
	 }
	 
	 $scope.link2NextPage = function(){
		 $scope.pageIndex++;
		 $scope.createDispalyList();
	 }
	 
	/** *************核算记录明细可检索化  end*************** */
          
  /********************************核算记录模糊检索及分页 END *********************************/

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
						$scope.comContacts=data.comContacts//联系信息
						$scope.paymentRecord.payee=data.orderInfo.buyName;
						$scope.canTotalRate=100-data.currentTotalRate;//获取当前可建收付款百分比数
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
	
	$scope.changeContactValue=function(){//筛选联系人信息
   		for(var i=0;i<$scope.comContacts.length;i++){
   			if($scope.paymentRecord.contact==$scope.comContacts[i].contactName){
   				$scope.paymentRecord.contactNum=$scope.comContacts[i].contactTel;
   				return;
   			}
   		}
   	}	
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
	$scope.saveBasicInfo=function (judgeString){
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
	    				fd.append('accountPeriod',$scope.paymentRecord.accountPeriod==undefined?'':$scope.paymentRecord.accountPeriod);
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

	$scope.jumpToGetPayInfo  = function(serialNum) {
    	$state.go('viewGatheringMoney',{serialNum:serialNum});
    }; 

    $scope.editBasicInfo = function() {
    	$scope.inputEdit=false;
    	$scope.input=true;
    	$scope.span=false;
    	if($scope.paymentRecord.paymentType=='报关'){
    		$scope.paymentRecord.applyDateForBg=$scope.paymentRecord.applyDate;
    		$scope.paymentRecord.playPaymentDate=$scope.paymentRecord.applyDate;
    		$scope.paymentRecord.totalMoney=scope.paymentRecord.applyPaymentAmount;
    	}
    	
    }


	//导出收款
	$scope.exportPay = function(){
		handle.blockUI("正在导出数据，请稍后"); 
		var ids ='';
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
		window.location.href=$rootScope.basePath+"/rest/pay/exportGatheringMoney"+"?serialNums="+ids;
		handle.unblockUI(); 
	}
	  $scope.goVerificate= function(serialNum) {//去核销
	    	$state.go('viewGatheringMoney',{serialNum:serialNum});
	    };
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
						              ajax: "rest/pay/findAllGatheringMoneyRecord",//加载数据中user表数据
						              "aoColumns": [
						                            { mData: 'serialNum'},
						                            { mData: 'paymentNum' },//paymentType
						                            { mData: 'paymentType' },//paymentType
						                            { mData: 'orderNum' },//paymentType
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
						                            				return '待核销';
						                            			}else if(data=='1'){
						                            				return '部分核销';
						                            			}else if(data=='2'){
						                            				return '已完成';
						                            			}
						                            		}else{
						                            			return "";
						                            		}
						                            	}
						                            },
						                            { mData: 'status'
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
						                            } ,
						                            {
						                            	'targets' : 6,
						                            	'className' : 'dt-body-center',
						                            	'render' : function(data,
						                            			type, row, meta) {
						                            		if(data==null||data==''){
						                            			return row.applyDate;
						                            		}else{
						                            			return data;
						                            		}
						                            	},
						                            	
						                            } ,
						                            {
						                            	'targets' : 12,
						                            	'className' : 'dt-body-center',
						                            	'render' : function(data,
						                            			type, row, meta) {
						                            	if(row.status=='1'||row.status=='0'){
						                            			return '<a href="javascript:void(0);" ng-click="goVerificate(\''+row.serialNum+'\')">确认收款</a>';
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


	//销售订单列表
	var table1;
	var loadMainTable1 = function() {
		a = 0;
		App.getViewPort().width < App.getResponsiveBreakpoint("md") ? $(".page-header").hasClass("page-header-fixed-mobile") && (a = $(".page-header").outerHeight(!0)) : $(".page-header").hasClass("navbar-fixed-top") ? a = $(".page-header").outerHeight(!0) : $("body").hasClass("page-header-fixed") && (a = 64);
		table1 = $("#sample_21")
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
			ajax:"rest/order/findOrderList?type=sale",// 加载数据中
			"aoColumns": [
			              { mData: 'serialNum' },
			              { mData: 'orderNum' },
			              { mData: 'buyName' },
			              { mData: 'materielCount' },
                          { mData: 'orderAmount' },
			              { mData: 'deliveryMode' },
			              { mData: 'serviceModel' },
			              { mData: 'saleApplySerial' },
			              { mData: 'orderSerial' },
			              { mData: 'orderDate' }

			              ],
			              'aoColumnDefs' : [ {
			            	  'targets' : 0,
			            	  'searchable' : false,
			            	  'orderable' : false,
			            	  'render' : function(data,
			            			  type, full, meta) {
			            		  return '<input type="radio" name="serialNum" value="'
			            		  + $('<div/>')
			            		  .text(
			            				  data)
			            				  .html()
			            				  + '">';
			            	  },
			            	  "createdCell": function (td, cellData, rowData, row, col) {
			            		  $compile(td)($scope);
			            	  }
			              } ]

		}).on('order.dt',
				function() {
			console.log('排序');
		})

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
				toastr.warning('请选择一个销售订单！');return;
			}
			if(ids==$scope.orderSerial){
				toastr.warning('当前销售订单已选,请选择其他的销售订单！');return;
			}

			$scope.getSaleOrderInfo(ids);

			ids = '';
			$('#basicMaterielInfo').modal('hide');// 删除成功后关闭模态框
			$(".modal-backdrop").remove();
		};
		

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
	};

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
		/*var flag=false;
		if($scope.paymentRecord!=undefined||$scope.paymentRecord.paymentType=='报关'){
			flag=true;
		}*/
		e.validate({
			errorElement: "span",
			errorClass: "help-block help-block-error",
			focusInvalid: !1,
			ignore: "",
			messages: {
				paymentNum:{required:"付款编号不能为空！",rangelength:jQuery.validator.format("付款编号位数必须在{0}到{1}字符之间！")},
				paymentType:{required:"付款类型不能为空！"},
				orderNum:{required:"关联订单不能为空！"},
				billType:{required:"发票方式不能为空！"},
				/*applyPaymentAmount:{required:"申请付款金额不能为空！",number:"请输入数字（正数）！",min:"必须是正数，且不小于0.01！",minNumber:"小数点后最多为两位！"},*/
				applyCurrency:{required:"申请币种不能为空！"},
				playPaymentDate:{required:"申请付款日期不能为空！"},
				payType:{required:"支付类型不能为空！"},
				deliveryRate:{required:"支付比率不能为空,0-100之间！"},
				paymentNode:{required:"支付节点不能为空！"},
				nodeNum:{required:"支付节点不能为空！"},
				applicant:{required:"申请人不能为空！"},
				applyDate:{required:"申请日期不能为空！"},
				applyDept:{required:"申请部门不能为空！"},
				payee:{required:"收款方不能为空！"},
				contact:{required:"收款方联系人不能为空,请选择！"},
				/*qgOrBgNum:{required:"报关单号不能为空！"},*/
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
				billType:{
					required:true,
				},
				/*qgOrBgNum:{
					required:flag,
				},*/
				paymentNum:{required:true,
					rangelength:[3,20]
				},
				paymentType:{required:true,
				},
				orderNum:{required:true,
				},
				deliveryRate:{
					required:!0,
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
    var buyComId=null;
   	//获取销售订单的信息（并给buyComId赋值）
   	$scope.getBuyOrderInfo  = function(serialNum) {
   		PayService.getSaleOrderInfo(serialNum).then(
   				function(data){
   					debugger
   					if($scope.pay==null){
   						$scope.saleOrder=data.orderInfo;
   						buyComId=$scope.saleOrder.buyComId;
   						var orderSerial=data.orderInfo.serialNum;
   						$scope.orderSerial=data.orderInfo.serialNum;
   						$scope.paymentRecord.orderDate=data.orderInfo.orderDate;
   						$scope.deliveryMaterielE=data.clauList;
   						$scope.isBG=data.createBG;
   						$scope.clauseSettlementList=data.clauseSettlementDetail;
   						$scope.comFinances=data.comFinances//收付款信息
   						$scope.comContacts=data.comContacts//联系信息
   						$scope.paymentRecord.payee=data.orderInfo.supplyName;
   						
   					}else{
   						/*$scope.pay.orderNum=data.orderInfo.orderNum;
   						$scope.pay.orderSerial=data.orderInfo.serialNum;
   						$scope.pay.orderAmount=data.orderInfo.orderAmount;
   						$scope.pay.paiedMoney=data.orderInfo.paiedMoney;
   						$scope.pay.billedMoney=data.orderInfo.billedMoney;
   						$scope.clauseSettlementList=data.clauseSettlementDetail;
   						
   						
   						$scope.pay.supplyComId=data.orderInfo.supplyComId;
   						$scope.pay.deliveryAmount=null;
   						supplyComId=data.orderInfo.supplyComId;*/
   					}
   				},
   				function(error){
   					$scope.error = error;
   				}
   		);

   	};
   	$scope.jumpToUrl=function(url){
   		$state.go(url);
   	}
}]);


