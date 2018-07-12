/**
 * 
 * @authors Your Name (you@example.org)
 * @date    2018-05-03 17:45:09
 * @version $Id$
 */
 //开启登陆注册弹窗
$(document).ready(function(){
	
	$('#register').on('click', function(){  
        layer.open({  
            type: 1
            ,title: '账号注册'
            ,resize: false
            ,skin: 'layui-layer-lan'
            ,shadeClose: true   
            ,area : ['300px' , '400px']
            ,content: $('.registerBox')//弹框显示的url  
        });
    }); 
    $('#login').on('click', function() {
    	layer.open({
    		type:1
    		,title: '账号登录'
    		,resize: false
    		,skin: 'layui-layer-lan'
    		,shadeClose: true
    		,area: ['300px', '330px']
    		,content: $('.loginBox')
    	});
    });
    $("#toRegister").on('click',function(){
        layer.close(layer.index);
        layer.open({  
            type: 1
            ,title: '账号注册'
            ,resize: false
            ,skin: 'layui-layer-lan'
            ,shadeClose: true   
            ,area : ['300px' , '400px']
            ,content: $('.registerBox')//弹框显示的url  
        });
    })
    
    var flag_id=0;
    var flag_password=0;
    
    $("#loginId").blur(function(){
    	var loginId=$(this).val();
    	
    	if(loginId==null || loginId==""){
    		$("#loginId-error").text("登录账号不能为空！");
    		$("#loginId-error").show();
    	}else{
    		$("#loginId-error").hide();
    		flag_id=1;
    	}
    });
    
    $("#loginPassword").blur(function(){
    	var loginPassword=$(this).val();
    	if(loginPassword==null || loginPassword==""){
    		$("#loginPassword-error").text("登录密码不能为空！");
    		$("#loginPassword-error").show();
    	}else{
    		$("#loginPassword-error").hide();
    		flag_password=1;
    	}
    });

    
    $("#loginForm").submit(function(){
    	
    	if(flag_id==1 && flag_password==1){
    		return true;
    	}else{
    		return false;
    	}
    	
    });
    
    $("#loginSubmit").on("click",function(){
        if(flag_id == 0 || flag_password == 0) {
        	alert("注册信息尚未完善！");
        }
    });
  

});




