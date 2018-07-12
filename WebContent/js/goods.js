/**
 * 
 * @authors Your Name (you@example.org)
 * @date    2018-05-22 19:31:49
 * @version $Id$
 */

$(function(){ 
	//分享框 
	$('#btn_goods_show').on('click', function(){  
		$('#input_url').val(window.location.href);
		
        layer.open({  
            type: 1
            ,title: '分享' 
            ,resize: false
            ,skin: 'layui-layer-lan' 
            ,shadeClose: true   
            ,area : ['378px' , '246px']
            ,content: $('.share-box')  

        });
    }); 
    
    $('#btn_understand').on('click', function(){
    	layer.close(layer.index);
    });
    
    //收藏提示
    $("#btn_goods_collect").on('click',function(){
    	
    	$.post("UpdateFavoriteServlet",function(result){
    		if(result==1){
    			//alert("收藏成功！");
    			layer.open({  
                    type: 1
                    ,title: '提示' 
                    ,resize: false
                    ,skin: 'layui-layer-lan' 
                    ,shadeClose: true   
                    ,area : ['378px' , '252px']
                    ,content: $('.sucess_collect_box')
                });
    		}else{
    			//alert("您已收藏该商品！");
    			layer.open({  
                    type: 1
                    ,title: '提示' 
                    ,resize: false
                    ,skin: 'layui-layer-lan' 
                    ,shadeClose: true   
                    ,area : ['378px' , '252px']
                    ,content: $('.have_collect_box')
                });
    		}
    		
    	});
    	
    });
    
    //购买提示
    
    $('#btn_goods_buy').on('click', function(){  
    	
    	$.post("user/buy",function(result){
    		
    		console.log(result);
        	if(result == 2){
        		console.log(result);
        		layer.open({  
                    type: 1
                    ,title: '提示' 
                    ,resize: false
                    ,skin: 'layui-layer-lan' 
                    ,shadeClose: true   
                    ,area : ['378px' , '246px']
                    ,content: $('.purchase_hints_box')
                });
        	}
        	if(result == 1){
        		console.log(result);
        		layer.open({  
                    type: 1
                    ,title: '购买流程' 
                    ,resize: false
                    ,skin: 'layui-layer-lan' 
                    ,shadeClose: true   
                    ,area : ['378px' , '500px']
                    ,content: $('.purchase_process_box')
                });
        	}
        	if(result == 0){
        		console.log(result);
        		$("#information").text("您尚未登录！")
        		$(".balance_intro").hide();
        		layer.open({  
                    type: 1
                    ,title: '提示' 
                    ,resize: false
                    ,skin: 'layui-layer-lan' 
                    ,shadeClose: true   
                    ,area : ['378px' , '246px']
                    ,content: $('.purchase_hints_box')
                });
        	}
        });
    }); 
    
    $('#balance_warning_btn').on('click', function(){
        layer.close(layer.index);
    });

    /*确认商品*/
    $('#check_goods_btn').click(function(){
        $('.purchase_process_box').children().hide();
        $('.check_goods').next().show();
        
    });

    /*确认地址*/
    /*恢复默认*/
    var address = $("#trueAddress").val();
    var phone = $("#truephone").val();
    var customer = $("#customer").val();
    $('#return_default_btn').on('click', function(){
        $("#trueAddress").val(address);
        $("#truephone").val(phone);
        $("#customer").val(customer);
    });
    $('#receipt_info_btn').click(function(){
        $('.purchase_process_box').children().hide();
        $('.receipt_info').next().show();
    });


    /*输入支付密码*/
    var key = $('.key_press_num');
    var input_place = $('.input_place');
    var index = 0;
    var pwd = "";
    for(var i=0; i < key.length; i++) {
        key[i].onclick=function(){
            if(index <= 5)   {
                pwd += $(this).text();
                
                //console.log($('.input_place').find('span').eq(index));
                $('.input_place').find('span').eq(index).show();
                index++;
            }
            else if(index > 5) {
                alert("已经输入完6位密码！");
            }

        }
    }

    
    $('#key_press_delete').click(function(){
        if(index > 0) {
            index--;
            pwd = pwd.substring(0, pwd.length-1);
            $('.input_place').find('span').eq(index).hide();
        }
        else 
            alert("没有可以删的了~");
    });

    $('#pay_btn').click(function(){
            if(index <= 5) {
                alert("还没输入完整！");
            }
            else if(index > 5) {
            	
            	var tmppwd=pwd;
            	tmppwd+=$("#pwd_hid").val();           	
                pwd = "";
                index = 0;
                $('.input_place').find('span').hide();
                layer.close(layer.index);
                
                $.post("user/buyprocess",
                		{
                		trueAddress:$("#trueAddress").html(),
                		customer:$("#customer").val(),
                		pwd_hid:tmppwd,
                		},
                		function(result){
                			if(result == 0){
                				/*$("#personal_success_title").hide();
                				$("#personal_success_img").hide();
                				$("#success_box_message").text("密码错误！");*/
                				layer.open({  
                                    type: 1
                                    ,title: '提示' 
                                    ,resize: false
                                    ,skin: 'layui-layer-lan' 
                                    ,shadeClose: true   
                                    ,area : ['378px' , '252px']
                                    ,content: $('.fail_box')
                                });
                			}else{
                				$("#personal_success_title").show();
                				$("#personal_success_img").show();
                				$("#success_box_message").text("支付成功！");
                				layer.open({  
                                    type: 1
                                    ,title: '提示' 
                                    ,resize: false
                                    ,skin: 'layui-layer-lan' 
                                    ,shadeClose: true   
                                    ,area : ['378px' , '500px']
                                    ,content: $('.success_box')
                                });
                			}
                		},
                		"json");
            }
            
    });
    
    $("#btn_goods_collect").on('click',function(){
    	
    	
    	
    });
    
    
    /*交易完成*/
    $('.btn_success').click(function(){
        layer.close(layer.index);
    });
    
    
});