$(document).ready(function () {
	document.addEventListener('WeixinJSBridgeReady', function onBridgeReady() {
		WeixinJSBridge.call('hideOptionMenu');
	});
//	推荐商品
//	$('.menu .ccbg dd').each(function(){
//		if( $(this).attr("menu") == '1' ){
//			$(this).show();
//		}else{
//			$(this).hide();
//		}
//	});
	//点击购物车
	$('#cart').on('click' , function (){
		$('#menu-container').hide();
		$('#cart-container').show();
		$('#user-container').hide();
		
		$(".footermenu ul li a").each(function(){
			$(this).attr("class","");
		});
		$(this).children("a").attr("class","active");
	});
	//点击首页
	$('#home').on('click' , function (){
		$('#menu-container').show();
		$('#cart-container').hide();
		$('#user-container').hide();
		
		$(".footermenu ul li a").each(function(){
			$(this).attr("class","");
		});
		$(this).children("a").attr("class","active");
	});
	//点击用户
	$('#user').on('click' , function (){
		$('#menu-container').hide();
		$('#cart-container').hide();
		$('#user-container').show();

		$(".footermenu ul li a").each(function(){
			$(this).attr("class","");
		});
		$(this).children("a").attr("class","active");
		getOrders();//add by hadong
	});

});
//触发点击首页
function home() {
	$('#home').click();
}
//清空购物车
function clearCache(){
	$('#ullist').find('li').remove();
	$('#home').click();
	$('.reduce').each(function () {
		$(this).children().css('background','');
	});
	$('#totalNum').html(0);
	$('#cartN2').html(0);
	$('#totalPrice').html(0);
}
//购物车+1
function addProductN (wemallId){
	var jqueryid = wemallId.split('_')[0]+'_'+wemallId.split('_')[1];
	var price = parseFloat( wemallId.split('_')[2] );
	var productN = parseFloat( $('#'+jqueryid).find('.count').html() );
	$('#'+jqueryid).find('.count').html( productN + 1);

	var cartMenuN = parseFloat($('#cartN2').html())+1;
	$('#totalNum').html( cartMenuN );
	$('#cartN2').html( cartMenuN );
	
	var totalPrice = parseFloat($('#totalPrice').html())+ parseFloat(price);
	$('#totalPrice').html( totalPrice.toFixed(2) );
}
//购物车-1
function reduceProductN ( wemallId ){
	var price = parseFloat( wemallId.split('_')[2] );
	var jqueryid = wemallId.split('_')[0]+'_'+wemallId.split('_')[1];
	var reduceProductN = parseFloat( $('#'+jqueryid).find('.count').html() );
	if ( reduceProductN == 1) {
		$('#'+jqueryid).remove();
		var id = wemallId.split('_')[1];
		$('#'+id).children().css('background','');

		if ( $('#ullist').find('li').length == 0 ){
			$('#menu-container').show();
			$('#cart-container').hide();
			$('#user-container').hide();
		}
	}
	$('#'+jqueryid).find('.count').html( reduceProductN - 1);
	
	var cartMenuN = parseFloat($('#cartN2').html())-1;
	$('#totalNum').html( cartMenuN );
	$('#cartN2').html( cartMenuN );

	var totalPrice = parseFloat($('#totalPrice').html())- parseFloat(price);
	$('#totalPrice').html( totalPrice.toFixed(2) );
}
//加入购物车
function doProduct (id , name , price) {
	var bgcolor = $('#'+id).children().css('background-color').colorHex().toUpperCase();
	if (bgcolor == '#FFFFFF') {
		$('#'+id).children().css('background-color','#D00A0A');

		var cartMenuN = parseFloat($('#cartN2').html())+1;
		$('#totalNum').html( cartMenuN );
		$('#cartN2').html( cartMenuN );

		var totalPrice = parseFloat($('#totalPrice').html())+ parseFloat(price);
		$('#totalPrice').html( totalPrice.toFixed(2) );

		var wemallId = 'wemall_'+id;
		var html = '<li class="ccbg2" id="'+wemallId+'"><div class="orderdish"><span name="title">'+name+'</span><span class="price" id="v_0">'+price+'</span><span class="price">元</span></div><div class="orderchange"><a href=javascript:reduceProductN("'+wemallId+'_'+price+'") class="reduce"><b class="ico_reduce">减一</b></a><span class="count" id="num_1_499">1</span><a href=javascript:addProductN("'+wemallId+'_'+price+'") class="increase"><b class="ico_increase">加一</b></a></div></li>';
		$('#ullist').append(html);
	}else{
		$('#'+id).children().css('background-color','');

		var cartMenuN = parseFloat($('#cartN2').html())-1;
		$('#totalNum').html( cartMenuN );
		$('#cartN2').html( cartMenuN );

		var totalPrice = parseFloat($('#totalPrice').html())- parseFloat(price);
		$('#totalPrice').html( totalPrice.toFixed(2) );

		var wemallId = 'wemall_'+id;
		$('#'+wemallId).remove();
	}
}
//提交订单
function submitOrder () {
	if(!$('form').find('#name').val()){
		alert('请输入联系人！');
		return false;
	}
	if(!$('form').find('#phone').val()){
		alert('请输入联系电话！');
		return false;
	}
	if(!$('form').find('#address').val()){
		alert('请输入地址！');
		return false;
	}
	var json = '';
	$('#ullist li').each(function () {
		var name = $(this).find('span[name=title]').html();
		var num = $(this).find('span[class=count]').html();
		var price = $(this).find('span[class=price]').html();
		json += '{"name":"'+name+'","num":"'+num+'","price":"'+price+'"},';
	});
	json = json.substring(0 , json.length-1);
	
	if(!json){
		alert('请选择至少一种商品！');
		return false;
	}
	
	json = '['+json+']';
	$.ajax({
		type : 'POST',
		url : appurl+'/shop/order/addOrder',
		data : {
			uid : $_GET['uid'],
			cartData : json,
			userData : $('form').serializeArray(),
			totalPrice : $('#totalPrice').html()
		},
		success : function (response , status , xhr) {
			$('#user').click();
			$('#ullist').find('li').remove();
			$('.reduce').each(function () {
				$(this).children().css('background','');
			});
			$('#totalNum').html(0);
			$('#cartN2').html( 0 );
			$('#totalPrice').html(0);
			if (response) {
				window.open(response);
			}
		},
		beforeSend : function(){
			$('#menu-shadow').show();
		},
		complete : function(){
			$('#menu-shadow').hide();
		}
	});
	

}
//获取url参数
var $_GET = (function(){
	var url = window.document.location.href.toString();
	var u = url.split("?");
	if(typeof(u[1]) == "string"){
		u = u[1].split("&");
		var get = {};
		for(var i in u){
			var j = u[i].split("=");
			get[j[0]] = j[1];
		}
		return get;
	} else {
		return {};
	}
})();
//获取Hex颜色
String.prototype.colorHex = function(){
	var that = this;
	if(/^(rgb|RGB)/.test(that)){
		var aColor = that.replace(/(?:\(|\)|rgb|RGB)*/g,"").split(",");
		var strHex = "#";
		for(var i=0; i<aColor.length; i++){
			var hex = Number(aColor[i]).toString(16);
			if(hex === "0"){
				hex += hex;	
			}
			strHex += hex;
		}
		if(strHex.length !== 7){
			strHex = that;	
		}
		return strHex;
	}else if(reg.test(that)){
		var aNum = that.replace(/#/,"").split("");
		if(aNum.length === 6){
			return that;	
		}else if(aNum.length === 3){
			var numHex = "#";
			for(var i=0; i<aNum.length; i+=1){
				numHex += (aNum[i]+aNum[i]);
			}
			return numHex;
		}
	}else{
		return that;	
	}
};
//获取订单
function getOrders(){
	$.ajax({
		type : 'POST',
		url : appurl+'/shop/order/getOrders',
		data : {
			uid : $_GET['uid']
		},
		success : function (response , status , xhr){
			if(response){
				var json = eval(response); 
				var html = '';
				var order_status = '';
				var pay_status = '';
				
				$.each(json.list, function (index, value) {
					var pay = '';
					var order = '';
					var cart_html = '';
					var cart_data = JSON.parse(value.CARTDATA);
					
					if (value.ORDER_STATUS == '0'){
						order_status = 'no';
						order = '未发货';
					}else if ( value.ORDER_STATUS == '1'){
						order_status = 'ok';
						order = '已发货';
					}
					
					if (value.PAY_STATUS == '0'){
						pay_status = 'no';
						pay = '未支付';
					}else if ( value.PAY_STATUS == '1'){
						pay_status = 'ok';
						pay = '已支付';
					}
					for(var i = 0;i < cart_data.length;i++){
						if(i == 0){
							cart_html+='<tr id="orderDesc_'+value.ORDER_ID+'" style="display: none;"><td colspan="4"><table width="100%" border="1" cellpadding="0" cellspacing="0">';
							cart_html+='<tr><th>名称</th><th class="cc">数量</th><th class="cc">单价</th></tr>';
						}
						cart_html+='<tr><td>'+cart_data[i].name+'</td><td class="cc">'+cart_data[i].num+'</td><td class="cc">'+cart_data[i].price+'</td></tr>';
						if(i == cart_data.length-1){
							if(value.ORDER_STATUS == '0'){
								cart_html+='<tr><td colspan="3">订单时间：'+value.CREATE_DT+' <em class="btn_del error" onclick="delOrder('+value.ORDER_ID+');">删除订单</em></td></tr>';
							}else{
								cart_html+='<tr><td colspan="3">订单时间：'+value.CREATE_DT+' <em class="btn_del no">删除订单</em></td></tr>';
							}
							cart_html+='</table></td><tr>';
						}
					}
					html += '<tr id="orderTitle_'+value.ORDER_ID+'" onclick="showOrderDesc('+value.ORDER_ID+');"><td>'+value.ORDER_ID+'</td><td class="cc">'+value.TOTALPRICE+'元</td><td class="cc"><em class="'+pay_status+'">'+pay+'</em></td><td class="cc"><em class="'+order_status+'">'+order+'</em></td></tr>';
					html += cart_html;
				});
				$('#orderlistinsert').empty();
				$('#orderlistinsert').append( html );
			}
		},
		beforeSend : function(){
			$('#page_tag_load').show();
    	},
    	complete : function(){
    		$('#page_tag_load').hide();
    	}
	});
}
//商品详细展示
function showDetail(id){
	$.ajax({
		type : 'post',
		url : appurl+'/shop/good/fetchGoodDetail',
		data : {
			id : id,
		},
		success : function(response , status , xhr){
			$('#mcover').show();
			var json = eval(response);
			$('#detailpic').attr('src',json.IMAGE);
			$('#detailtitle').html(json.NAME);
			$('#detailinfo').html(json.DETAIL);
		},
		beforeSend : function(){
			$('#mcover_tag_load').show();
    	},
    	complete : function(){
    		$('#mcover_tag_load').hide();
    	}
	});
}
function showMenu(){
	$("#menu").find("ul").toggle();
}
function toggleBar(){
	$(".footermenu ul li a").each(function(){
		$(this).attr("class","");
	});
	$(this).children("a").attr("class","active");
}
function showProducts(id) {
	$('.menu .ccbg dd').each(function(){
		if( $(this).attr("menu") == id ){
			$(this).show();
		}else{
			$(this).hide();
		}
	});
	$('#menu ul').hide();
}
function showAll() {
	$('.menu .ccbg dd').each(function(){
		$(this).show();
	});
	$('#menu ul').hide();
}
function showOrderDesc(orderId){
	$('#orderDesc_'+orderId).toggle();
}
function delOrder(orderId){
	if(confirm("是否删除订单 "+orderId+" ?")){
		$.ajax({
			type : 'POST',
			url : appurl+'/shop/order/delOrder',
			data : {
				uid : $_GET['uid'],
				orderId : orderId
			},
			success : function (response , status , xhr){
				if(response && response == "success"){
					$("#orderTitle_"+orderId).remove();
					$("#orderDesc_"+orderId).remove();
				}else{
					alert(response);
				}
			},
			beforeSend : function(){
				$('#page_tag_load').show();
	    	},
	    	complete : function(){
	    		$('#page_tag_load').hide();
	    	}
		});
	}
}
function closeOrder(orderId){
	if(confirm("是否关闭订单 "+orderId+" ?")){
		$.ajax({
			type : 'POST',
			url : appurl+'/shop/order/closeOrder',
			data : {
				uid : $_GET['uid'],
				orderId : orderId
			},
			success : function (response , status , xhr){
				if(response && response == "success"){
					$("#tb_"+orderId).remove();
					$("#div_"+orderId).remove();
				}else{
					alert(response);
				}
			},
			beforeSend : function(){
				$('#page_tag_load').show();
	    	},
	    	complete : function(){
	    		$('#page_tag_load').hide();
	    	}
		});
	}
}