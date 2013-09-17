$(function(){
	count = 0 ;
	isStart = false ;
	bufferSize = $("#bufferSize").val() ;
	isScroll = true ;
	
	$("#start").click(function(){
		if(!$(this).hasClass("active")) {
			start() ;
		}
	}) ;
	$("#stop").click(function(){
		if(!$(this).hasClass("active")) {
			stop() ;
		}
	}) ;
	$("#bufferSize").blur(function(){
		if($.isNumeric($(this).val())){
			bufferSize  = parseInt($(this).val()) ; 
		} 
		console.log(bufferSize) ;
	});
	$("#clear").click(function(){
		$("#log").empty() ;
		count = 0 ;
	}) ;
	$("#isScroll").click(function(){
		if($(this).hasClass("active")){
			$(this).removeClass("active") ;
			isScroll = false ; 
		}  else {
			$(this).addClass("active") ;
			isScroll = true ;
		}
	});
	
	function start(){
		refresh() ;
	}
	
	function stop(){
		clearTimeout(window.monitorTimer) ; 
		isStart = false ;
	}
	
	function refresh(){
		isStart = true ;
		window.monitorTimer = setTimeout(function(){
			refresh() ;
		} , 1000 ) ;
		
		$.getJSON('/monitor/data.htm?uuid=0101' , function(data){
			$.each(data , function(i , v ){
				count++ ;
				while(count > bufferSize) {
					count-- ;
					var span = $("#log").children().get(0) ; 
					if(span!=null) span.remove() ;
				}
				$("#log").append("<span>" + v+"</span>") ;
				if(isScroll){
					$("#log")[0].scrollTop = $("#log")[0].scrollHeight ; 
				}
			}) ;
		}) ;
	}
	
	
}) ;