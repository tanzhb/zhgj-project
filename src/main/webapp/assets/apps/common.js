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

jQuery.validator.addMethod("minNumber",function(value, element){
    var returnVal = true;
    inputZ=value;
    var ArrMen= inputZ.split(".");    //截取字符串
    if(ArrMen.length==2){
        if(ArrMen[1].length>2){    //判断小数点后面的字符串长度
            returnVal = false;
            return false;
        }
    }
    return returnVal;
},"小数点后最多为两位"); 

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
     if(minute < 10){
    	 minute = '0' + minute;
     }
     if(second < 10){
    	 second = '0' + second;
     }
     return year + "-" + month + "-" + date+" "+hour+":"+minute+":"+second;
};
function timeStamp2ShortString (time){
	if(time==null){
		return null;
	}
    var datetime = new Date();
     datetime.setTime(time);
     var year = datetime.getFullYear();
     var month = datetime.getMonth() + 1;
     var date = datetime.getDate();
     if(month < 10){
    	 month = '0' + month;
     }
     return year + "-" + month + "-" + date;
};
//+---------------------------------------------------
//| 求两个时间的天数差 日期格式为 YYYY-MM-dd
//+---------------------------------------------------
function daysBetween(DateOne, DateTwo) {
	var OneMonth = DateOne.substring(5, DateOne.lastIndexOf('-'));
	var OneDay = DateOne
			.substring(DateOne.length, DateOne.lastIndexOf('-') + 1);
	var OneYear = DateOne.substring(0, DateOne.indexOf('-'));

	var TwoMonth = DateTwo.substring(5, DateTwo.lastIndexOf('-'));
	var TwoDay = DateTwo
			.substring(DateTwo.length, DateTwo.lastIndexOf('-') + 1);
	var TwoYear = DateTwo.substring(0, DateTwo.indexOf('-'));

	var cha = ((Date.parse(OneMonth + '/' + OneDay + '/' + OneYear) - Date
			.parse(TwoMonth + '/' + TwoDay + '/' + TwoYear)) / 86400000);
	return cha;
}

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

/*******自定义验证辅助方法START*********/
//取消表单元素警告状态
function changeTextInputBorder(obj){
	 $(obj).parent().removeClass("has-error");
	 $(obj).next().text("");
}
//取消表单元素警告状态
function changeDateInputBorder(obj){
	 $(obj).parent().parent().removeClass("has-error");
	 $(obj).parent().next().text("");
}
/********自定义验证辅助方法END**********/

//去html标签及空格
function delHtmlTag(str){
	  return str.replace(/<[^>]+>/g,"").replace(/&nbsp;/ig, " ");//去掉所有的html标记
}


//图片展示
function showImg(src,title){
	var html = "<div style='width:900px;'><img style='width:100%;height:100%;' src="+src+" alt='图片无法显示' /></div>";
	
	//html = $("#img").html();
	layer.open({
		  type:1,
		  title:false,
		  offset: '100px',
		  shadeClose: true,
		  maxWidth:900,
		  content: html
	});
}

function isImg(filename){
	if(!/\.(gif|jpg|jpeg|png|GIF|JPG|PNG)$/.test(filename))
	{
	  return false;
	}
	return true;
}


/*日期控件控制*/
function initDatePicker(orientation){
	$('.date-picker').datepicker({
		    language: 'zh-CN',
			orientation: orientation,
			autoclose: true
	});
}

//将金额转换成大写
function convertCurrency(money) {
	  //汉字的数字
	  var cnNums = new Array('零', '壹', '贰', '叁', '肆', '伍', '陆', '柒', '捌', '玖');
	  //基本单位
	  var cnIntRadice = new Array('', '拾', '佰', '仟');
	  //对应整数部分扩展单位
	  var cnIntUnits = new Array('', '万', '亿', '兆');
	  //对应小数部分单位
	  var cnDecUnits = new Array('角', '分', '毫', '厘');
	  //整数金额时后面跟的字符
	  var cnInteger = '整';
	  //整型完以后的单位
	  var cnIntLast = '元';
	  //最大处理的数字
	  var maxNum = 999999999999999.9999;
	  //金额整数部分
	  var integerNum;
	  //金额小数部分
	  var decimalNum;
	  //输出的中文金额字符串
	  var chineseStr = '';
	  //分离金额后用的数组，预定义
	  var parts;
	  if (money == '') { return ''; }
	  money = parseFloat(money);
	  if (money >= maxNum) {
	    //超出最大处理数字
	    return '';
	  }
	  if (money == 0) {
	    chineseStr = cnNums[0] + cnIntLast + cnInteger;
	    return chineseStr;
	  }
	  //转换为字符串
	  money = money.toString();
	  if (money.indexOf('.') == -1) {
	    integerNum = money;
	    decimalNum = '';
	  } else {
	    parts = money.split('.');
	    integerNum = parts[0];
	    decimalNum = parts[1].substr(0, 4);
	  }
	  //获取整型部分转换
	  if (parseInt(integerNum, 10) > 0) {
	    var zeroCount = 0;
	    var IntLen = integerNum.length;
	    for (var i = 0; i < IntLen; i++) {
	      var n = integerNum.substr(i, 1);
	      var p = IntLen - i - 1;
	      var q = p / 4;
	      var m = p % 4;
	      if (n == '0') {
	        zeroCount++;
	      } else {
	        if (zeroCount > 0) {
	          chineseStr += cnNums[0];
	        }
	        //归零
	        zeroCount = 0;
	        chineseStr += cnNums[parseInt(n)] + cnIntRadice[m];
	      }
	      if (m == 0 && zeroCount < 4) {
	        chineseStr += cnIntUnits[q];
	      }
	    }
	    chineseStr += cnIntLast;
	  }
	  //小数部分
	  if (decimalNum != '') {
	    var decLen = decimalNum.length;
	    for (var i = 0; i < decLen; i++) {
	      var n = decimalNum.substr(i, 1);
	      if (n != '0') {
	        chineseStr += cnNums[Number(n)] + cnDecUnits[i];
	      }
	    }
	  }
	  if (chineseStr == '') {
	    chineseStr += cnNums[0] + cnIntLast + cnInteger;
	  } else if (decimalNum == '') {
	    chineseStr += cnInteger;
	  }
	  return chineseStr;
	}

//流程图
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
                    left: (v.x + 8),
                    top: (v.y + 29),
                    width: (v.width + 3),
                    height: (v.height + 5),
                    backgroundColor: 'black',
                    opacity: 0,
                    zIndex: $.fn.qtip.zindex - 1
                });

                // 跟踪节点边框
                var $border = $('<div/>', {
                    'class': 'activity-attr-border'
                }).css({
                    position: 'absolute',
                    left: (v.x + 8),
                    top: (v.y + 29),
                    width: (v.width + 3),
                    height: (v.height + 5),
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
		                        /*iconCls: 'icon-cancel',*/
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
                           var tipContent = '<div class="light">';
                           $.each(vars, function(varKey, varValue) {
                               if (varValue) {
                                   tipContent += "<div class='page-bar '>" + varKey + " : <span class='font-green-sharp sbold'>" + varValue + "</span></div>";
                               }
                           });
                           tipContent += "</div>";
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



//当页面中追加元素时，若元素中含有angularjs元素，则需要compile才能生效
angular.module('MetronicApp')
.directive('compile', function ($compile) {
    return function (scope, element, attrs) {
        scope.$watch(
          function (scope) {
               
              return scope.$eval(attrs.compile);
          },
          function (value) {
              element.html(value);
              $compile(element.contents())(scope);
          }
        );
    };
});


/*根据页面不同，导航栏做相应变动*/
function initPageBar($rootScope, path){
	if(path == 'addPay'){
		$("#loadPageBar").innerHTML = "<li><a ui-sref='paymentRecordC'>付款</a> <i class='fa fa-angle-right'></i></li><li><a>新增付款1</a></li>";
	}
}
