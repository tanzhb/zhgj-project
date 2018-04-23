/***
Task Js
***/

//撤回已办任务到代办任务
function revoke(taskId, processInstanceId, endTaskTable){
	$.ajax({
		type: "POST",
		url: ctx+"/rest/processAction/process/revoke/"+taskId+"/"+processInstanceId,
		dataType:"text",
		data: {},
		success: function (data) {
			$('#'+endTaskTable+'').DataTable().ajax.reload();
			showToastr('toast-bottom-right', 'success', data)
		},
		beforeSend:function(){
			
		},
		complete: function(){
			
		}
	});
}

//签收
function claimTask(taskId, table){
	$.ajax( {
        url : ctx + "/rest/processAction/claim/" + taskId,
        dataType:"text",
        type: 'POST',
        success : function(data) {
        	$('#'+table+'').DataTable().ajax.reload();
        	showToastr('toast-bottom-right', 'success', data)
        }
     });
}
////用户取消申请
function userCancelApply(taskId,processInstanceId, endTaskTable,tableType){
	var val='';
	if(tableType=='saleOrder'||tableType=='buyOrder'){
		val='userCancelOrderApply';
	}else if(tableType=='pay'||tableType=='payForBuyTable'){
		val='userCancelPayApply';
	}else if(tableType=='buyFram'||tableType=='saleFram'){
		val='userCancelFrameApply';
	}else if(tableType=='buyPrice'||tableType=='salePrice'){
		val='userCancelPriceApply';
	}else if(tableType=='outInvoice'){
		val='userCancelInvoiceApply';
	}else if(tableType=='delivery'){
		val='userCancelDeliveryApply';
	}else if(tableType=='procurementPlan'){
		val='userCancelProcurementPlanApply';
	}
	$.ajax( {
        url : "rest/order/"+val+"/" + taskId+"/"+processInstanceId,
        dataType:"text",
        type: 'POST',
        success : function(data) {
        	if(tableType=='buyOrder'){
        		data='采购订单申请取消成功！';
        		$('#sample_2').DataTable().ajax.reload();
        	}else if(tableType=='saleOrder'){
        		data='销售订单申请取消成功！';
        		$('#sample_2').DataTable().ajax.reload();
        	}else if(tableType=='pay'){
        		data='付款申请取消成功！';
        		$('#sample_2').DataTable().ajax.reload();
        	}else if(tableType=='payForBuyTable'){
        		data='付款申请取消成功！';
        		$('#sample_4').DataTable().ajax.reload();
        	}else if(tableType=='buyPrice'){
        		data='采购价格申请取消成功！';
        		$('#sample_buy').DataTable().ajax.reload();
        	}else if(tableType=='salePrice'){
        		data='销售价格申请取消成功！';
        		$('#sample_sale').DataTable().ajax.reload();
        	}else if(tableType=='buyFram'){
        		data='采购框架申请取消成功！';
        		$('#sample_2').DataTable().ajax.reload();
        	}else if(tableType=='saleFram'){
        		data='销售框架申请取消成功！';
        		$('#sample_2').DataTable().ajax.reload();
        	}else if(tableType=='outInvoice'){
        		data='销项票申请取消成功！';
        		$('#sample_out').DataTable().ajax.reload();
        	}else if(tableType=='delivery'){
        		data='发货申请取消成功！';
        		$('#endTaskDeliveryPlanTable').DataTable().ajax.reload();
        	}else if(tableType=='procurementPlan'){
        		data='采购计划申请取消成功！';
        		$('#sample_2').DataTable().ajax.reload();
        	}
        	showToastr('toast-bottom-right', 'success', data)
        	$('#'+endTaskTable+'').DataTable().ajax.reload();
        },
        error:function(data) {
        	//$scope.error = error;
        	toastr.success('申请取消失败！');
        }
     });
   }
