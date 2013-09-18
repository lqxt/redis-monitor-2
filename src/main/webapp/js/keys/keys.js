$(function(){
	$("#search").click(function(e){
		var pattern = $.trim($("#searchValue").val()) ;
		var url = '/keys/getByPattern.htm?patternKey='+pattern+'&uuid='+uuid ;
		$.getJSON(url , function(data){
			$("#keysBody").empty() ;
			$.each(data , function(index , value){
				var show = value ;
				if(value.length > 30){
					show = value.substring(0 , 30) + "..." ;
					$("#keysBody").append('<tr><td><a title="'+value+'" href="#" onclick=\'getValue("'+value+'")\'>' + show + '</a></td></tr>') ;
				} else {
					$("#keysBody").append('<tr><td><a href="#" onclick=\'getValue("'+value+'")\'>' + value + '</a></td></tr>') ;
				}
				
			}) ;
		}) ;
		return false ;
	}) ;
	
}) ;

function getValue(key){
	console.log('get value:' + key) ;
	var url = '/keys/value.htm?key=' + key +'&uuid=' + uuid ;
	$.getJSON(url , function(data){
		console.log(data) ;
	}) ;
}