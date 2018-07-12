
$(document).ready(function(){
    
	$(".mission-finish").on('click',function(){
		var item = $(this);
		//$(this).text("已领取");
		$.post("MissionServlet",
				
				{missionId:$(this).parent().find(".mission-id").val(),
				 reward:$(this).parent().find(".reward").text()},
				 
				function(result){
					 if(result==0){
						 alert("任务还未完成！");
					 }
					 if(result==1){
						 alert("奖励已领取！");
					 }
					 if(result==2){
						 item.text("已领取");
						 alert("奖励领取成功！");
					 }
		},"json")
		
	});
	
});