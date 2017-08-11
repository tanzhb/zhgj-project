pageHandle = (function(){
	
	

	var _confirm = function(message,callbackOK,callbackCANCEL){
		bootbox.confirm({
			title: "提示",
			message:message,
			size: 'small',
			 buttons: {
			        confirm: {
			        	label: '确定',
			        	className: 'btn-success'
			  },
			        cancel: {
			            label: '取消',
			            className: 'btn-danger'
			        }
			 },
			 callback: function (result) {
				 if(result){
					 callbackOK();
				 }else{
					 if(callbackCANCEL && callbackCANCEL != undefined){
						 callbackCANCEL();
					 }
				 }
				 
			}
		}); 
	}
	
	var _blockUI = function(_message){
		  var  message = '请稍等';
		  if(_message!=undefined){
			  message = _message;
		  }
		  App.blockUI({
              boxed: true,
              message:message
          });
	} 
	
	var _unblockUI = function(){
		 App.unblockUI();
	}
	
	
	var _createPage = function(formId,pageModel,url,callback,init){
		if(init){
			var options = {
			        currentPage: 1,
			        totalPages: pageModel.totalPages,
			        numberOfPages:pageModel.pageSize,
			        bootstrapMajorVersion:3,
			        itemTexts: function (type, page, current) {
			            switch (type) {
			                case "first":
			                    return "首页";
			                case "prev":
			                    return "上一页";
			                case "next":
			                    return "下一页";
			                case "last":
			                    return "末页";
			                case "page":
			                    return page;
			            }
			        },onPageClicked: function(event, originalEvent, type, page){
			            callback(pageModel.pageSize,page,false);
			        }
			    }
			if(pageModel.totalPages > 0){
				$(formId+"_paginator").bootstrapPaginator(options);
			}
		}
		var last = Number(pageModel.offset)+Number(pageModel.pageSize);
		$(formId+"_info").text("显示  "+pageModel.first+" 到 "+ (last>pageModel.totalCount?pageModel.totalCount:last)+"条，   共 "+pageModel.totalCount+" 条记录");
		this.unblockUI();
	}
	
	var _pageRepeater = function(){
				_index = 0
				var obj = this;
		        $('.page-repeater').each(function(index,e){
		                $(this).repeater({
		        			show: function () {
		        				/*_index ++;
		        				$(this).find("input").each(function(){
		        					var replaceModel = $(this).attr("ng-model");
		        					if(replaceModel!=undefined){
		        						$(this).attr("ng-model",replaceModel.replace("0",_index));
		        						$(this).addClass("ng-empty");
		        					}
		                        });
			                	$(this).slideDown();*/
		                        $('.date-picker').datepicker({
		                            rtl: App.isRTL(),
		                            orientation: "left",
		                            autoclose: true
		                        });
		                       /* $(".c_edit").addClass("ng-hide");*/
				            },

				            hide: function (deleteElement) {
				            	obj.confirm("确定删除吗？",function(){
				            		$(this).slideUp(deleteElement);
				            		obj.showMesssage("success","删除成功！","提示")
				            	});
				                    
				            },

				            ready: function (setIndexes) {

				            }

		        	});
		        });
		        
	}
	
	var _datePickersInit = function(){
				$('.date-picker').datepicker({
						rtl: App.isRTL(),
						orientation: "left",
						autoclose: true
				});
		
	}
	
	
	var _isNull = function(str) {
		if (str == "" || str == undefined)
			return true;
		var regu = "^[ ]+$";
		var re = new RegExp(regu);
		return re.test(str);
	}
	
	var  constructor=function(){
		//this.showMesssage= _showMesssage;
		this.confirm = _confirm;
		this.createPage = _createPage;
		this.blockUI = _blockUI;
		this.unblockUI = _unblockUI;
		this.datePickersInit = _datePickersInit;
		this.pageRepeater = _pageRepeater;
		this.isNull = _isNull;
		//this.toastr = toastr;
		
	}
	
	return constructor;
	
})();