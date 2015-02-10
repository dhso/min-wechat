var themes = [{ value: 'default', text: 'Default' }, { value: 'gray', text: 'Gray' }, { value: 'metro', text: 'Metro' }, { value: 'bootstrap', text: 'Bootstrap' }, { value: 'black', text: 'Black'}];
$(document).ready(function() {
	$('#chooseTheme').combobox({
	    data: themes,
	    editable: false,
	    panelHeight: 'auto',
	    onChange: onChangeTheme,
	    onLoadSuccess: function () {
	        $(this).combobox('setValue', 'default');
	    }
	});

	$('#myCalendar').calendar({
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
});

//更换主题
function onChangeTheme(theme) {
    var link = $('head').find('link[css="easyui"]');
    link.attr('href', baseUrl+'/static/easyui/themes/' + theme + '/easyui.css');
}
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
//添加tabPanel
function addTabPanel(contain,title,href,reload){
	var existTabPanel = $(contain).tabs('exists',title);
	if(existTabPanel){
		$(contain).tabs('select',title);
		if(reload){
			$(contain).tabs('getSelected').panel('refresh', href);
		}
	}else{
		$(contain).tabs('add',{
	        title: title,
	        href: href,
	        closable: true
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