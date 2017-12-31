$(document).ready(function () {
	
       
    
$('#minlat').on("change", function(){
    $(this).attr('value', $('#minlat').val());
	$('#maxlat').attr('min', $('#minlat').val());
    $('#maxlat').attr('value', $('#minlat').val());

});
    
$('#maxlat').on("change", function(){
    $(this).attr('value', $('#maxlat').val());
	$('#maxlat').attr('min', $('#minlat').val());
    $('#minlat').attr('max', $('#maxlat').val());
});    
		

    
    
    
$('#minlon').on("change", function(){
    $(this).attr('value', $('#minlon').val());
	$('#maxlon').attr('min', $('#minlon').val());
    $('#maxlon').attr('value', $('#minlon').val());

});
    
$('#maxlon').on("change", function(){
    $(this).attr('value', $('#maxlon').val());
	$('#maxlon').attr('min', $('#minlon').val());
    $('#minlon').attr('max', $('#maxlon').val());
});









});