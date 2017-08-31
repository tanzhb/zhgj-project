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
