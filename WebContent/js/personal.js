/**
 * 
 * @authors Your Name (you@example.org)
 * @date    2018-05-09 21:29:25
 * @version $Id$
 */


$(document).ready(function(){
   
    $('#collection_center').click(function() {
    	$('#collection_center').siblings().removeClass('bar_be_selected');
    	$('#collection_center').addClass('bar_be_selected');

    	$('#CollectionCenter').siblings().hide();
    	$('#CollectionCenter').show();
    });

    $('#personal_information').click(function() {
		$('#personal_information').siblings().removeClass('bar_be_selected');
    	$('#personal_information').addClass('bar_be_selected');

    	$('#PersonalInfo').siblings().hide();
    	$('#PersonalInfo').show();
    });

    $('#shopping_record').click(function() {
		$('#shopping_record').siblings().removeClass('bar_be_selected');
    	$('#shopping_record').addClass('bar_be_selected');

    	$('#ShoppingRecord').siblings().hide();
    	$('#ShoppingRecord').show();
    });
    
    
    var x=0;
    var y=0;
    $("#btn_login_pwd").on("click",function(){
    	if(x==0){
    		$("#newPassword").attr("type","text");
        	$("#passwordVersion").text("不可见");
        	x=1;
    	}else{
    		$("#newPassword").attr("type","password");
        	$("#passwordVersion").text("可见");
        	x=0;
    	}
    });
    
    $("#btn_pay_pwd").on("click",function(){
    	if(y==0){
    		$("#newPaypassword").attr("type","text");
        	$("#paypasswordVersion").text("不可见");
        	y=1;
    	}else{
    		$("#newPaypassword").attr("type","password");
        	$("#paypasswordVersion").text("可见");
        	y=0;
    	}
    });
    
    $.getJSON("FavoriteServlet", function(result) {
    	for(var i=0; i < result.length; i++) {
    		$("#f_goods_list").append(
    				"<div class=\"col-md-3 p-favorate-goodslist-item\" style=\"text-decoration:none;\">"
    			
    			+ "<a href=\"info.jsp?itemId=" + result[i].goods_id + "\" target=\"_blank\">"
				+ "<img src=\"img/"+ result[i].goods_picture +"\" alt=\"图片加载失败\">"
				+ "<div class=\"p-item-introduce\" style=\"color:#666666;\">" + result[i].goods_introduce + "</div>"
				+ "</a>"
				+ "<div class=\"p-item-price\">"
				+ "		<i class=\"fa fa-money\">&nbsp" + result[i].goods_price + "</i>"
				+ "	</div>"
				+ "	<div class=\"f_other_info\">"
				+ "		<span>收藏日期："+ result[i].savedate +"</span>"
				+ "		<button id=\"collection_btn_delete\" class=\"collection_btn_delete\"  >删除</button>"
				+ "	</div>"
				+ "<div class=\"f_goods_id\" style=\"display:none\">" + result[i].goods_id + "</div>"	
				+ "</div>"
    		);
    	}
    	var del_btn = $('.collection_btn_delete');
        for (var i = 0; i < del_btn.length; i++) {
        	del_btn[i].onclick = function() {
        		//$(this).parent().parent().remove();
        		var item = $(this);
        		var goods_id = $(this).parent().next().text();
        		console.log(goods_id);
        		$.post("DelFavoriteServlet?goods_id=" + goods_id, function(result){
        			if(result) {
        				alert("删除成功");
        				item.parent().parent().remove();
        			}
        			else {
        				alert("删除失败");
        			}
        		})
        	}
        }
        
    });
    
    
    $(".record_delete").on('click',function(){
    	   	
    	var n = $(this).parent().parent().find('.hideId').html();
    	//alert(hideId);
    	$(this).parent().parent().remove();
    	
    	$.post("RecordDeleteServlet",
    			{deleteItemId:n},
    			function(result){
    				if(result==1){
    					alert("删除成功！");
    				}else{
    					alert("删除失败！");
    				}
    	},"json");
    	
    });

    $(".record_back").on('click',function(){
    	
    	var n=$(this).parent().parent().find(".hideId").html();
    	
    	$.post("GoodsBackServlet",
    			{backRecordId:n},
    			function(result){
    				if(result==1){
    					alert("退购处理中！");
    				}else{
    					alert("退购失败！");
    				}
    	},"json")
  
    });

});

