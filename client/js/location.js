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




    
    
 ///algorithmin
    
    
    
 $("input#Location2").click(function() {
     var mac1= $("input#mac1").val();
     var signal1= $("input#signal1").val();
     
     var mac2= $("input#mac2").val();
     var signal2= $("input#signal2").val();
     
     var mac3= $("input#mac3").val();
     var signal3= $("input#signal3").val();
     var num= $("input#num").val();

     var input = num+","+mac1+","+signal1+","+mac2+","+signal2+","+mac3+","+signal3;
     
     var one=true;
     var two=true;
     var third=true;
     
     if((mac1=="" && signal1!="") || (mac1!="" && signal1=="")){
        one=false; 
     }
     
     if((mac2=="" && signal2!="") || (mac2!="" && signal2=="")){
        two=false; 
     }
     
     if((mac3=="" && signal3!="") || (mac3!="" && signal3=="")){
//        third=false; 
     }
     
     if(one && two && third){
        $.ajax(
				{
					"url": encodeURI("/algo2?"+input)
				}
			).then(
				function(output) {
                    $("h3#locationText").text(output);
				}
			);
			return false; 
     }
     else{
         alert("one of the fields is missing ");
         alert(input);
     }

     		
})   
    
    
    
    
    
   $("input#Location1").click(function() {
     var mac= $("input#mac").val();
     
     if(mac!=""){
        $.ajax(
				{
					"url": encodeURI("/algo1?"+mac)
				}
			).then(
				function(output) {
                    $("h3#locationText").text(output);
				}
			);
			return false; 
     }
     else{
         alert("one of the fields is missing ")
     }

     		
})   
    
    
    
    
    
    
    
    
    
    
    
    





});