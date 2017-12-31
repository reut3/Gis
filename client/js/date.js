$(document).ready(function () {
	

   
    
    
    
  
//$('#start1').datepicker({
// changeMonth: true,
// changeYear: true,
// dateFormat : 'dd/mm/yy',
////     minDate: '8/10/2016',
////     maxDate: '8/10/2017',
//    minDate: startDateFrom,
//    maxDate: startDateTo,
// onChangeMonthYear:function(y, m, i){  
////         alert(startDateFrom);
//         alert("reut");
//
//    var d = i.selectedDay;
//    $(this).datepicker('setDate', new Date(y, m-1, d));
// }
//  });
//   
//   
// $('#end1').datepicker({
// changeMonth: true,
// changeYear: true,
// dateFormat : 'dd/mm/yy',
//    // minDate: '8/10/2016',
//    // maxDate: '8/10/2017',
//
// onChangeMonthYear:function(y, m, i){                                
//    var d = i.selectedDay;
//    $(this).datepicker('setDate', new Date(y, m-1, d));
//    $('#start1').datepicker('maxDate', '8/10/2017');
// }
//  });   
// 
//    
//$('.timepicker').timepicker({
//    timeFormat: 'h:mm p',
//    interval: 60,
//    minTime: '10',
//    maxTime: '6:00pm',
//    defaultTime: '11',
//    startTime: '10:00',
//    dynamic: false,
//    dropdown: true,
//    scrollbar: true
//});
        
    
    
$('#start').on("change", function(){
    $(this).attr('value', $('#start').val());
	$('#end').attr('min', $('#start').val());
	$('#end').attr('value', $('#start').val());
    
    
});
      
    
$('#end').on("change", function(){
    if($('#start').val()>$('#end').val()){
        $('#start').attr('value', $('#end').val());
    }
    $(this).attr('value', $('#end').val());
	$('#end').attr('min', $('#start').val());
    $('#start').attr('max', $('#end').val());
    if($('#start').val()==$('#end').val()){
        $('#endTime').attr('min', $('#startTime').val());
        $('#startTime').attr('max', $('#endTime').val());
    }
});    
	
    
$('#startTime').on("change", function(){
if($('#start').val()==$('#end').val()){
    if($('#startTime').val()>$('#endTime').val())
        $('#endTime').attr('value', $('#startTime').val());
    }
}); 
  
    
$('#endTime').on("change", function(){
    if($('#start').val()==$('#end').val()){
        if($('#endTime').val()<$('#startTime').val()){
            $('#startTime').attr('value', $('#endTime').val());
        }
    }
});    
    
    
 
    
    

$("input#applyDate").click(function() {
			var Startinput = $("input#start").val();
			$("input#end").attr("min", "1979-12-31");
			$.ajax(
				{
					"url": encodeURI("/folder?" +input)
				}
			).then(
				function(output) {
					//$("div#output").html("")
					$("div#output").append("<div>The reverse of '"+input+"' is '"+output+"'</div>")
				}
			);
			return false
		})


    
    

});