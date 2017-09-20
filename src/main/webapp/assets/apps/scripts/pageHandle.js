pageHandle = (function(){
	
	
	//询问框
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
	
	//加载遮罩
	var _blockUI = function(_message,id){
		  var  message = '请稍等';
		  if(_message!=undefined&&_message!=null){
			  message = _message;
		  }
		  App.blockUI({
			  target: id,
              boxed: true,
              message:message
          });
	} 
	
	//解除遮罩
	var _unblockUI = function(id){
		 App.unblockUI(id);
	}
	
	//自定义列表分页插件
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
			            callback(pageModel.pageSize,page,false,pageModel.params);
			        }
			    }
			if(pageModel.totalPages > 0){
				$(formId+"_paginator").bootstrapPaginator(options);
			}else{
				pageModel.first = 0;
				$(formId+"_paginator").empty();
			}
		}
		var last = Number(pageModel.offset)+Number(pageModel.pageSize);
		$(formId+"_info").text("显示  "+pageModel.first+" 到 "+ (last>pageModel.totalCount?pageModel.totalCount:last)+"条，   共 "+pageModel.totalCount+" 条记录");
		this.unblockUI(formId);
	}
	
	//元素复制
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
	
	//日期控件初始化
	var _datePickersInit = function(_type,_id){
				if(isNull(_type)){
					_type = "left"; 
				}
				if(this.isNull(_id)){
					_id = ".date-picker"
				}
				$(_id).datepicker({
						rtl: App.isRTL(),
						orientation: _type,
						autoclose: true,
						language: "zh-CN"
				});
	}
	
	//为空判断
	var _isNull = function(str) {
		if (str == "" || str == undefined)
			return true;
		var regu = "^[ ]+$";
		var re = new RegExp(regu);
		return re.test(str);
	}
	
	//自定义表单验证提醒
	var _paramCheck = function(id,title,isDate){

	    var isIE = /msie/i.test(navigator.userAgent) && !window.opera;
	    if(isIE)
	    {
	        document.getElementById(id).onpropertychange = changeTextInputBorder();
	    }
	    else //需要用addEventListener来注册事件
	    {
	    	try{
	            document.getElementById(id).addEventListener("input", changeTextInputBorder, false);
			}catch (e){
			}

	    }
	   
	    if(isDate){
	    	$("#"+id).parent().parent().addClass("has-error");
	    	$("#"+id).parent().next().text(title);
	        $("#"+id).attr("onfocus","changeDateInputBorder(this)");
	    }else{
	    	$("#"+id).parent().addClass("has-error");
	    	$("#"+id).next().text(title);
	        $("#"+id).attr("onpropertychange","changeTextInputBorder(this)");
	        $("#"+id).attr("oninput","changeTextInputBorder(this)");
	    }
	}
	
	/*
	 * 用途：检查输入对象的值是否符合整数格式 输入：str 输入的字符串 返回：如果通过验证返回true,否则返回false
	 * 
	 */
	var _isInteger = function(str) {
		var regu = /^[-]{0,1}[0-9]{1,}$/;
		return regu.test(str);
	}

	/*
	 * 用途：检查输入字符串是否符合正整数格式 输入： s：字符串 返回： 如果通过验证返回true,否则返回false
	 * 
	 */
	var _isNumber = function(s) {
		var regu = "^[0-9]+$";
		var re = new RegExp(regu);
		if (s.search(re) != -1) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 * 用途：检查输入字符串是否是带小数的数字格式,可以是负数 输入： s：字符串 返回： 如果通过验证返回true,否则返回false
	 * 
	 */
	var _isDecimal = function(str) {
		if (isInteger(str))
			return true;
		var re = /^[-]{0,1}(\d+)[\.]+(\d+)$/;
		if (re.test(str)) {
			if (RegExp.$1 == 0 && RegExp.$2 == 0)
				return false;
			return true;
		} else {
			return false;
		}
	}
	
	//多个同名name验证初始化  
    var _validatorInit= function(){
		 
		 if ($.validator) {
	           $.validator.prototype.elements = function () {
	               var validator = this,
	                 rulesCache = {};
	 
	               // select all valid inputs inside the form (no submit or reset buttons)
	               return $(this.currentForm)
	               .find("input, select, textarea")
	               .not(":submit, :reset, :image, [disabled]")
	               .not(this.settings.ignore)
	               .filter(function () {
	                   if (!this.name && validator.settings.debug && window.console) {
	                       console.error("%o has no name assigned", this);
	                   }
	                   //注释这行代码
	                   // select only the first element for each name, and only those with rules specified
	                   //if ( this.name in rulesCache || !validator.objectLength($(this).rules()) ) {
	                   //    return false;
	                   //}
	                   rulesCache[this.name] = true;
	                   return true;
	               });
	           }
	    }
		 
	 }
    
    //重写indexOf方法
	var _indexOf = function(arr, item) {
		for (var i = 0; i < arr.length; i++) {
				if (arr[i] === item)
					return i;
				else
					return -1;
		}
	}

	//数组去重复
	var _unique = function(arr) {
	    var result = [], hash = {};
	    for (var i = 0, elem; (elem = arr[i]) != null; i++) {
	        if (!hash[elem]) {
	            result.push(elem);
	            hash[elem] = true;
	        }
	    }
	    return result;
	}
	
	//添加cookie
	var _addCookie = function(name,value,expiresHours){ 
		var cookieString=name+"="+escape(value); 
		//判断是否设置过期时间 
		if(expiresHours>0){ 
		var date=new Date(); 
		date.setTime(date.getTime+expiresHours*3600*1000); 
		cookieString=cookieString+"; expires="+date.toGMTString(); 
		} 
		document.cookie=cookieString; 
	} 

	//获取cookie
	var _getCookie = function(name){ 
		var strCookie=document.cookie; 
		var arrCookie=strCookie.split("; "); 
		for(var i=0;i<arrCookie.length;i++){ 
		var arr=arrCookie[i].split("="); 
		if(arr[0]==name)return arr[1]; 
		} 
		return ""; 
	} 

	//删除cookie(cookie失效)
	var _deleteCookie = function(name){ 
		var date=new Date(); 
		date.setTime(date.getTime()-10000); 
		document.cookie=name+"=v; expires="+date.toGMTString(); 
	} 

	var _getLastMonthDay = function(date){    
		var year=date.getFullYear().toString();
	    var month=date.getMonth()+1;   
        var  day = new Date(year,month,0);   
        var lastdate = year + '-' + month + '-' + day.getDate();//获取当月最后一天日期    
//给文本控件赋值。同下  
        return lastdate;  
     }  
	
	var _formatNumber = function(value){
		if(!this.isNull(value)&&Number(value)!=NaN){
			return Number(value);
		}
		return 0;
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
		this.paramCheck = _paramCheck;
		this.isInteger = _isInteger;
		this.isNumber = _isNumber;
		this.isDecimal = _isDecimal;
		this.validatorInit = _validatorInit;
		this.indexOf = _indexOf;
		this.unique = _unique;
		this.addCookie = _addCookie;
		this.getCookie = _getCookie;
		this.deleteCookie = _deleteCookie;
		this.getLastMonthDay = _getLastMonthDay;
		this.formatNumber = _formatNumber;
		//this.toastr = toastr;
		
	}
	
	return constructor;
	
})();