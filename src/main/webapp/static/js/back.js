$(document).ready(function() {
	$('#systemCalendar').calendar({
		formatter:formatDay,
		border:false,
		width:200,
		height:200,
		firstDay:1,
		weeks:['日','一','二','三','四','五','六'],
		months:['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月']
	});
	//初始化prettyPhoto
	$('#mainTabContain').tabs({
		onLoad: function(panel){
			if(panel.context.innerHTML.indexOf('rel="prettyPhoto')>0){
				prettyPhotoInit();
			}
			if(panel.context.innerHTML.indexOf('paginationBar')>0){
				paginationInit();
			}
		}
	});
	//初始化主题选择
	var easyuiThemeName = $.cookie('easyuiThemeName');
	if (!easyuiThemeName){
		easyuiThemeName = 'default';
	}
	$('#chooseThemeMenu').menu('setIcon', {
		target : $('#chooseThemeMenu div[title=' + easyuiThemeName + ']')[0],
		iconCls : 'fa fa-check'
	});
});

/**
 * 
 * @requires jQuery,EasyUI,jQuery cookie plugin
 * 更换EasyUI主题的方法
 * @param themeName 主题名称
 */
function changeThemeFun(themeName) {
	if ($.cookie('easyuiThemeName')) {
		$('#chooseThemeMenu').menu('setIcon', {
			target : $('#chooseThemeMenu div[title=' + $.cookie('easyuiThemeName') + ']')[0],
			iconCls : 'emptyIcon'
		});
	}else{
		$('#chooseThemeMenu').menu('setIcon', {
			target : $('#chooseThemeMenu div[title="default"]')[0],
			iconCls : 'emptyIcon'
		});
	}
	$('#chooseThemeMenu').menu('setIcon', {
		target : $('#chooseThemeMenu div[title=' + themeName + ']')[0],
		iconCls : 'fa fa-check'
	});
	var easyuiTheme = $('#easyuiThemeLink');
	var url = easyuiTheme.attr('href');
	var href = url.substring(0, url.indexOf('themes')) + 'themes/' + themeName + '/easyui.css';
	easyuiTheme.attr('href', href);
	$.cookie('easyuiThemeName', themeName, {
		expires : 7
	});
};

//我的日历
var d1 = Math.floor((Math.random()*30)+1);
var d2 = Math.floor((Math.random()*30)+1);
function formatDay(date){
    var m = date.getMonth()+1;
    var d = date.getDate();
    var opts = $(this).calendar('options');
    if (opts.month == m && d == d1){
        return '<div class="icon-ok md">' + d + '</div>';
    } else if (opts.month == m && d == d2){
        return '<div class="icon-search md">' + d + '</div>';
    }
    return d;
}
//处理提示
function showProgress(title, text){
	parent.$.messager.progress({
		title : title,
		text : text
	});
}
function closeProgress(){
	parent.$.messager.progress('close');
}

//添加tabPanel
function addTabPanel(contain,title,href,ifr){
	showProgress('提示','正在处理中，请耐心等待...');
	var existTabPanel = $(contain).tabs('exists',title);
	if(existTabPanel){
		$(contain).tabs('close',title);
	}
	if(ifr){
		$(contain).tabs('add',{
	        title: title,
	        content: '<iframe src="' + href + '" frameborder="0" style="border:0;width:100%;height:99%;padding-top:10px;" seamless="seamless" sandbox="allow-forms allow-same-origin allow-scripts allow-top-navigation" ></iframe>',
	        closable: true
	    });
		setTimeout("closeProgress()", 1000);
	}else{
		$(contain).tabs('add',{
	        title: title,
	        href: href,
	        closable: true,
	        onLoad:function(){
	        	closeProgress();
	        }
	    });
	}
}

//删除tabPanel
function removeTabPanel(contain){
    var tab = $(contain).tabs('getSelected');
    if (tab){
        var index = $(contain).tabs('getTabIndex', tab);
        $(contain).tabs('close', index);
    }
}

//分页初始化
function paginationInit(){
	$('div.paginationBar').pagination({
		onSelectPage:function(pageNumber, pageSize){
			$(this).pagination('loading');
			$('#mainTabContain').tabs('getSelected').panel('refresh', $(this).attr('goto')+'&pageNumber='+pageNumber+'&pageSize='+pageSize);
			$(this).pagination('loaded');
		},
		onChangePageSize:function(pageSize){
			$(this).pagination('loading');
			$('#mainTabContain').tabs('getSelected').panel('refresh', $(this).attr('goto')+'&pageNumber=1&pageSize='+pageSize);
			$(this).pagination('loaded');
		}
	});
}
//prettyPhoto初始化
function prettyPhotoInit(){
	$("a[rel^='prettyPhoto']").prettyPhoto();
}
//serializeArray转json
function serializeArray2Json(serializeArray){
	var result = "{";
	for (var i in serializeArray){  
		result += "\"" + serializeArray[i]["name"] + "\":\"" + serializeArray[i]["value"] + "\",";
	}
	result = result.substring(0, result.length-1) + "}";
	return JSON.parse(result);
}
//带缓存的getScript
jQuery.getCachedScript = function(url, options) {
	  options = $.extend(options || {}, {
	    dataType: "script",
	    cache: true,
	    url: url
	  });
	  return jQuery.ajax(options);
}
//easyui的DataGrid格式化
var EasyUIFormatter = {
    //EasyUI用DataGrid用日期格式化
    Date: function (value,row,index) {
    	var str = value.split(' ')[0].replace(/-/g, '/');
    	var date;
    	if(str){
    		date = new Date(str);
    	}
    	var val = "";
        if (date && date.getFullYear() >= 1900) {
        	var year = date.getFullYear();  
            var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;  
            var day = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
        	val = year + "-" + month +"-" + day;
        }
        return val;
    },
    Gender: function (value,row,index) {
    	var val = "";
        if(value && value == "1"){
        	val = "男";
        }else if(value && value == "2"){
        	val = "女";
        }
        return val;
    }
    
};
//easyui消息提示
function message_fade(title,msg,timeout){
    $.messager.show({
        title:title,
        msg:msg,
        timeout:timeout,
        showType:'fade'
    });
}