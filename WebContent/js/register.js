
$(document).ready(function(){
    var flag_phone = 0;
    var flag_pwd = 0;
    var flag_relpwd = 0;
    var flag_email = 0;
    //手机号码验证
    $("#phone").blur(function(){
        var name = $(this).val();
        var myreg = /^[1][3,4,5,7,8][0-9]{9}$/;
        if (name == null || name == "") {
            $("#phone-error").text("手机号不能为空！");
            $("#phone-error").show();
        } else if(!myreg.test(name)) {
            $("#phone-error").text("手机号格式不正确！");
            $("#phone-error").show();
        } else {
            $("#phone-error").hide();
            flag_phone = 1;
        }
    });
    //密码验证
    $("#password").blur(function(){
        var pwd = $(this).val();
        if (pwd == null || pwd == "") {
            $("#password-error").text("密码不能为空！");
            $("#password-error").show();
           
        } else if(pwd.length < 6) {
          $("#password-error").text("密码不能小于6位！");
          $("#password-error").show();
         
        } else {
          $("#password-error").hide();
          flag_pwd=1;
        }
    });
    //二次密码验证
    $("#relpassword").blur(function(){
        var pwd = $("#password").val(); 
        var relpwd = $(this).val();
        if(relpwd == null || relpwd == "") {
            $("#relpassword-error").text("确认密码不能为空！");
            $("#relpassword-error").show();
            
        } else if(!(pwd == relpwd)) {
            $("#relpassword-error").text("两次密码不相同！");
            $("#relpassword-error").show();
            
        } else {
            $("#relpassword-error").hide();
            flag_relpwd=1;
        }
    });
    //邮箱验证
    $("#email").blur(function(){
        var email = $(this).val();
        var myemail = /^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\.[a-zA-Z0-9-]+)*\.[a-zA-Z0-9]{2,6}$/;
        if(email == null || email == "") {
            $("#email-error").text("邮箱不能为空！");
            $("#email-error").show();
            
        } else if(!myemail.test(email)) {
            $("#email-error").text("邮箱格式不正确！");
            $("#email-error").show();
            
        } else {
            $("#email-error").hide();
            flag_email=1;
        }
    });

    $("#registerForm").submit(function(){
        
        if(flag_phone == 1 && flag_pwd == 1 && flag_relpwd == 1 && 
        		flag_email == 1 ) {
            return true;
        }else {
        	return false;
        }

    });

    $("#registerSubmit").on("click",function(){
        if(flag_phone == 0 || flag_pwd == 0 || flag_relpwd == 0 || flag_email == 0) {
        	alert("注册信息尚未完善！");
        }
    });

});