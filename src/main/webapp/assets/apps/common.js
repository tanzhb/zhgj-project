/***
Common Js
***/

//增加身份证验证
function isIdCardNo(num) {
    var factorArr = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1);
    var parityBit = new Array("1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2");
    var varArray = new Array();
    var intValue;
    var lngProduct = 0;
    var intCheckDigit;
    var intStrLen = num.length;
    var idNumber = num;
    // initialize
    if ((intStrLen != 15) && (intStrLen != 18)) {
        return false;
    }
    // check and set value
    for (i = 0; i < intStrLen; i++) {
        varArray[i] = idNumber.charAt(i);
        if ((varArray[i] < '0' || varArray[i] > '9') && (i != 17)) {
            return false;
        } else if (i < 17) {
            varArray[i] = varArray[i] * factorArr[i];
        }
    }

    if (intStrLen == 18) {
        //check date
        var date8 = idNumber.substring(6, 14);
        if (isDate8(date8) == false) {
            return false;
        }
        // calculate the sum of the products
        for (i = 0; i < 17; i++) {
            lngProduct = lngProduct + varArray[i];
        }
        // calculate the check digit
        intCheckDigit = parityBit[lngProduct % 11];
        // check last digit
        if (varArray[17] != intCheckDigit) {
            return false;
        }
    }
    else {        //length is 15
        //check date
        var date6 = idNumber.substring(6, 12);
        if (isDate6(date6) == false) {
            return false;
        }
    }
    return true;
}
function isDate6(sDate) {
    if (!/^[0-9]{6}$/.test(sDate)) {
        return false;
    }
    var year, month, day;
    year = sDate.substring(0, 4);
    month = sDate.substring(4, 6);
    if (year < 1700 || year > 2500) return false
    if (month < 1 || month > 12) return false
    return true
}

function isDate8(sDate) {
    if (!/^[0-9]{8}$/.test(sDate)) {
        return false;
    }
    var year, month, day;
    year = sDate.substring(0, 4);
    month = sDate.substring(4, 6);
    day = sDate.substring(6, 8);
    var iaMonthDays = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]
    if (year < 1700 || year > 2500) return false
    if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)) iaMonthDays[1] = 29;
    if (month < 1 || month > 12) return false
    if (day < 1 || day > iaMonthDays[month - 1]) return false
    return true
}

jQuery.validator.addMethod("isIdCardNo", function (value, element) {
    return this.optional(element) || isIdCardNo(value);
}, "请正确输入您的身份证号码");

//联系电话(手机/电话皆可)验证
jQuery.validator.addMethod("isPhone", function(value,element) {
	  var length = value.length;
	  var mobile = /^(((13[0-9]{1})|(15[0-9]{1}))+\d{8})$/;
	  var tel = /^\d{3,4}-?\d{7,9}$/;
	  return this.optional(element) || (tel.test(value) || mobile.test(value));

	}, "请正确填写您的联系电话"); 

//timestamp转换成datetime
function timeStamp2String (time){
    var datetime = new Date();
     datetime.setTime(time);
     var year = datetime.getFullYear();
     var month = datetime.getMonth() + 1;
     var date = datetime.getDate();
     var hour = datetime.getHours();
     var minute = datetime.getMinutes();
     var second = datetime.getSeconds();
     var mseconds = datetime.getMilliseconds();
     return year + "-" + month + "-" + date+" "+hour+":"+minute+":"+second;
};

//timestamp转换成datetime
function timeStamp2String2 (time){
    var datetime = new Date();
     datetime.setTime(time);
     var year = datetime.getFullYear();
     var month = datetime.getMonth() + 1;
     var date = datetime.getDate();
     var hour = datetime.getHours();
     var minute = datetime.getMinutes();
     var second = datetime.getSeconds();
     var mseconds = datetime.getMilliseconds();
     return year + "-" + month + "-" + date;
};


//初始化toastr开始
toastr.options = {
		"closeButton" : true,
		"debug" : false,
		"positionClass" : "toast-top-center",
		"onclick" : null,
		"showDuration" : "1000",
		"hideDuration" : "1000",
		"timeOut" : "5000",
		"extendedTimeOut" : "1000",
		"showEasing" : "swing",
		"hideEasing" : "linear",
		"showMethod" : "fadeIn",
		"hideMethod" : "fadeOut"
	}
/*toast-top-left  顶端左边
toast-top-right    顶端右边
toast-top-center  顶端中间
toast-top-full-width 顶端，宽度铺满整个屏幕
toast-botton-right  
toast-bottom-left
toast-bottom-center
toast-bottom-full-width*/
function showToastr(position, type, info){
	toastr.options = {
			"closeButton" : true,
			"debug" : false,
			"positionClass" : position,
			"onclick" : null,
			"showDuration" : "1000",
			"hideDuration" : "1000",
			"timeOut" : "5000",
			"extendedTimeOut" : "1000",
			"showEasing" : "swing",
			"hideEasing" : "linear",
			"showMethod" : "fadeIn",
			"hideMethod" : "fadeOut"
	}
	if(type == 'warning'){
		toastr.warning(info);
	}else if(type == 'success'){
		toastr.success(info);
	}
}
//初始化toastr结束

//为空判断
var isNull = function(str) {
	if (str == "" || str == undefined)
		return true;
	var regu = "^[ ]+$";
	var re = new RegExp(regu);
	return re.test(str);
}


//页面打印方法start********/
function printdiv(printpage)
{
	var headstr = "<html><head><title></title></head><body>";
	var footstr = "</body>";
	var newstr = document.all.item(printpage).innerHTML;
	var oldstr = document.body.innerHTML;
	document.body.innerHTML = headstr+newstr+footstr;
	 if(getExplorer() == "IE"){
         pagesetup_null();
     }
	window.print(); 
	document.body.innerHTML = oldstr;
	return false;
}


function pagesetup_null(){                
    var hkey_root,hkey_path,hkey_key;
    hkey_root="HKEY_CURRENT_USER";
    hkey_path="\\Software\\Microsoft\\Internet Explorer\\PageSetup\\";
    try{
        var RegWsh = new ActiveXObject("WScript.Shell");
        hkey_key="header";
        RegWsh.RegWrite(hkey_root+hkey_path+hkey_key,"");
        hkey_key="footer";
        RegWsh.RegWrite(hkey_root+hkey_path+hkey_key,"");
    }catch(e){}
}

function getExplorer() {
    var explorer = window.navigator.userAgent ;
    //ie 
    if (explorer.indexOf("MSIE") >= 0) {
        return "IE";
    }
    //firefox 
    else if (explorer.indexOf("Firefox") >= 0) {
        return "Firefox";
    }
    //Chrome
    else if(explorer.indexOf("Chrome") >= 0){
        return "Chrome";
    }
    //Opera
    else if(explorer.indexOf("Opera") >= 0){
        return "Opera";
    }
    //Safari
    else if(explorer.indexOf("Safari") >= 0){
        return "Safari";
    }
}
//页面打印方法end********/

/*日期控件控制*/
function initDatePicker(orientation){
	$('.date-picker').datepicker({
		    language: 'zh-CN',
			orientation: orientation,
			autoclose: true
	});
}

function graphTrace(id, ctx) {
    // 获取图片资源
    var imageUrl = ctx + "/rest/processAction/process/process-instance?pid=" + id + "&type=png";
	$.ajax({
        type: "POST",
        url: ctx+"/rest/processAction/process/trace/" + id,
        data: {},
        success: function (data) {
        	//$.messager.progress("close");
        	
            var positionHtml = "";
            // 生成图片
            var varsArray = new Array();
            $.each(data, function(i, v) {
                var $positionDiv = $('<div/>', {
                    'class': 'activity-attr'
                }).css({
                    position: 'absolute',
                    left: (v.x + 4),
                    top: (v.y + 26),
                    width: (v.width - 2),
                    height: (v.height - 2),
                    backgroundColor: 'black',
                    opacity: 0,
                    zIndex: $.fn.qtip.zindex - 1
                });

                // 跟踪节点边框
                var $border = $('<div/>', {
                    'class': 'activity-attr-border'
                }).css({
                    position: 'absolute',
                    left: (v.x + 4),
                    top: (v.y + 26),
                    width: (v.width - 2),
                    height: (v.height - 2),
                    zIndex: $.fn.qtip.zindex - 2
                });

                if (v.currentActiviti) {
                    $border.addClass('ui-corner-all-12').css({
                        border: '3px solid red'
                    });
                }
                positionHtml += $positionDiv.outerHTML() + $border.outerHTML();
                varsArray[varsArray.length] = v.vars;
            });
            
            if ($('#workflowTraceDialog').length == 0) {
                $('<div/>', {
                    id: 'workflowTraceDialog',
//                    title: '查看流程（按ESC键可以关闭）',
                    html: "<div class='easyui-layout'><img src='" + imageUrl + "'style='left:0px; top:0px;' />" +
                    "<div id='processImageBorder'>" +
                    positionHtml +
                    "</div>" +
                    "</div>"
                }).appendTo('body');
            } else {
                $('#workflowTraceDialog img').attr('src', imageUrl);
                $('#workflowTraceDialog #processImageBorder').html(positionHtml);
            }

            // 设置每个节点的data
            $('#workflowTraceDialog .activity-attr').each(function(i, v) {
                $(this).data('vars', varsArray[i]);
            });
        	
	         // 这里创建一个图像保存到内存，并没有添加到 HTML 中，只是个参考
	         $("<img/>").attr("src", imageUrl).load(function() {
		            //弹出对话窗口
		           var workflowTraceDialog = $('#workflowTraceDialog').dialog({
		            	title : "查看流程",
		        		top: 80,
		        		width : this.width+30,
		        		height : this.height+90,
		                modal: true,
		                minimizable: true,
		                maximizable: true,
		                resizable: true,
		                onLoad: function () {
		                	
		                },
		                buttons: [
		                    {
		                        text: '关闭',
		                        iconCls: 'icon-cancel',
		                        handler: function () {
		                        	workflowTraceDialog.dialog('destroy');
		                        }
		                    }
		                ],
		                onClose: function () {
		                	workflowTraceDialog.dialog('destroy');
		                }
		            });
		        // 此处用于显示每个节点的信息，如果不需要可以删除
                   $('.activity-attr').qtip({
                       content: function() {
                           var vars = $(this).data('vars');
                           var tipContent = "<table class='easyui-datagrid'><thead>";
                           $.each(vars, function(varKey, varValue) {
                               if (varValue) {
                                   tipContent += "<tr><td class='title1'>" + varKey + "</td><td class='left'>" + varValue + "<td/></tr>";
                               }
                           });
                           tipContent += "</thead></table>";
                           return tipContent;
                       },
                       position: {
                           at: 'bottom left',
                           adjust: {
                               x: 60
                           }
                       }
                   });
	         });
            
        },
        beforeSend:function(){
//        	$.messager.progress({
//                title: '提示信息！',
//                text: '数据处理中，请稍后....'
//            });
    	},
    	complete: function(){
//    		$.messager.progress("close");
   		}
	});
    

}
