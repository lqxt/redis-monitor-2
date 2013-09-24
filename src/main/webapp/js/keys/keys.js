$(function(){
	$("#search").click(searchListener) ;
	$("#searchValue").keypress(function(e){
		if(e.keyCode==13){
			searchListener() ;
		}
	}) ;
	function searchListener(e){
		var pattern = $.trim($("#searchValue").val()) ;
		var url = '/keys/getByPattern.htm?patternKey='+pattern+'&uuid='+uuid ;
		$.getJSON(url , function(data){
			$("#keysBody").empty() ;
			if(data == null || data.length < 1){
				$("#keysBody").append("<tr><td>无匹配结果</td></tr>") ; 
				return false ;
			}
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
	}
}) ;

function getValue(key){
	console.log('get value:' + key) ;
	var url = '/keys/value.htm?key=' + key +'&uuid=' + uuid ;
	$.getJSON(url , function(data){
		$("#stringView").hide().find("textarea").val("") ;
		$("#mapView").hide().find("tbody").empty() ;
		$("#listView").hide().find("tbody").empty();
		
		if(data.type == 'string'){
			$("#stringView").show().find("textarea").val(data.value) ;
		} else if(data.type == "map"){
			$.each(data.value , function(key , value){
				$("#mapView").show().find("tbody").append("<tr><td><p style=\"width:200px;\">"+ key +"</p></td><td><p style=\"width:600px;word-wrap: break-word;word-break: normal;\">"+ value +"</p></td></tr>") ;
			}) ;
		} else if(data.type == "list") {
			$.each(data.value , function(index , value){
				$("#listView").show().find("tbody").append("<tr><td><p style=\"width:200px;\">"+ index +"</p></td><td><p style=\"width:600px;word-wrap: break-word;word-break: normal;\">"+ value +"</p></td></tr>") ;
			}) ;
		}
		console.log(data) ;
	}) ;
}