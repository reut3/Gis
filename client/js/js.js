$(document).ready(function () {

$("button#folderSend").click(function() {
			var input = $("input#folder").val();
			$.ajax(
				{
					"url": encodeURI("/folder?" +input)
				}
			).then(
				function(output) {
                    if(output!="1"){
                        alert(output);
                    }
                    else{
                        $("h3#text").text("The folder has recived, the DataBase has updated ");
                        $(".hide").css("display", "inline");
                    }
				}
			);
			return false
		})


$("button#upload").click(function(){
        $("h3#text").text("The file has been added to DateBase");
        $(".hide").css("display", "inline");
        $.ajax(
                {
                    "url": encodeURI("/addFile?" +input)
                })
});
    
$("button#csv").click(function(){
        $("h3#text").text("The file saved as CSV! on your computer! under the name 'finalFile'");
        $(".hide").css("display", "inline");
        $.ajax(
                {
                    "url": encodeURI("/toCSV?" +input)
                })
});



$("button#kml").click(function(){
        $("h3#text").text("The file saved as KML on your computer! under the name 'kmlFile'");
        $.ajax(
                {
                    "url": encodeURI("/toKML?" +input)
                })
});


$("button#delete").click(function(){
        $("h3#text").text("The dataBase has deleted'");
        $.ajax(
            {
				"url": encodeURI("/delete?" +input)
            })
});
    
    

    
    
    
var filter1="";
var filter2="";
var Woperation="one"; 

//applay filter field    
$("input#applyFilter").click(function() {
			var input = Woperation+","+filter1+","+filter2;
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
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
///filters    
 
      
//when None checkbox is clicked
$("input[type=checkbox].none").on("change", function(){
   if ($(".none").is(":checked")) { 
         resetAll();
        $(".onNone").attr("disabled", "disabled");
   }
   else{
    $(".onNone").removeAttr("disabled");  
    }
});
    
    
  
//when Date checkbox is clicked
$("input[type=checkbox]#date").on("change", function(){
     if ($(this).is(':checked')) {
        $(".hidedate").css("display", "inline-block");
     }
    else{
        $(".hidedate").css("display", "none");
    }
 });
    
    
//when Location checkbox is clicked
  $("input[type=checkbox]#location").on("change", function(){
     if ($(this).is(':checked')) {
        $(".hidelock").css("display", "inline-block");
     }
    else{
                $(".hidelock").css("display", "none");
    }
 });  
    
  
//when ID checkbox is clicked
  $("input[type=checkbox]#ID").on("change", function(){
     if ($(this).is(':checked')) {
        $(".hideID").css("display", "inline-block");
     }
    else{
        $(".hideID").css("display", "none");

    }
 
 });    
    
    
 // applay the dates filter
 $("input#applyDate").click(function() {
			var input1 = $("input#start").val();
            var input11= $("input#startTime").val();
            var input2 = $("input#end").val();
            var input22= $("input#endTime").val();

            var final= input1+" "+ input11+"  to    "+ input2+" "+input22;
            var not=0;
    
            //if the filter field is empty
            if ($("h3#filter").text() == "") {
                  if ($("input[type=checkbox]#NotDate").is(':checked')) {
                    $("h3#filter").append(" Not between the Dates  "+final);
                      not=1;
                    filter1="Date,1,"+input1+" "+input11+","+input2+" "+input22;
                    }
                  else{
                    $("h3#filter").append(" Dates between "+final);
                    filter1="Date,0,"+input1+" "+input11+","+input2+" "+input22;
                  }
                $("#operation").css("display", "inline-block");
                $(".toggle").css("display", "inline-block");
            }
            else{ //if the filter field is not empty
                if ($("input[type=checkbox]#NotDate").is(':checked')) {
                    $("h3#filter1").append(" Not between the Dates  "+final);
                    not=1;
                    filter2="Date,1,"+input1+" "+input11+","+input2+" "+input22;
                    Woperation="and";
                    }
                else{
                    $("h3#filter1").append(" Dates between "+final);
                    filter2="Date,0,"+input1+" "+input11+","+input2+" "+input22;
                    Woperation="and";
                }
            }
     $.ajax(
				{
					"url": encodeURI("/folder?" +final+" "+not)
				}
			)
     
})   
  
 // applay the location filter   
 $("input#applyLocation").click(function() {
			var input1 = $("input#minlat").val();
            var input11 = $("input#minlon").val();
            var input2 = $("input#maxlat").val();
            var input22 = $("input#maxlon").val();
            var not=0;

            var final= input1+" "+ input11+ "  to "+input2+" "+ input22;
            if ($("h3#filter").text() == "") {
                if ($("input[type=checkbox]#NotLocation").is(':checked')) {
                    $("h3#filter").append(" Not between the locations  "+final);
                      not=1;
                    filter1="Location,1,"+input1+","+input11+","+input2+","+input22;
                    }
                  else{
                    $("h3#filter1").append(" Locations between "+final);
                    filter1="Location,0,"+input1+","+input11+","+input2+","+input22;
                  }
                $("#operation").css("display", "inline-block");
                $(".toggle").css("display", "inline-block");
            } 
            else{ //if the filter field is not empty
                if ($("input[type=checkbox]#NotLocation").is(':checked')) {
                        $("h3#filter1").append(" Not between the locations  "+final);
                        not=1;
                        filter2="Location,1,"+input1+","+input11+","+input2+","+input22;
                        Woperation="and";
                    }
                else{
                    $("h3#filter1").append(" Locations between "+final);
                    filter2="Location,0,"+input1+","+input11+","+input2+","+input22;
                    Woperation="and";

                }
            }
})    
    
    
    
 // applay the ID filter   
 $("input#applyID").click(function() {
			var final = $("input#idName").val();
            var not=0;
            
            if ($("h3#filter").text() == "") {
                if ($("input[type=checkbox]#NotID").is(':checked')) {
                    $("h3#filter").append(" Not with the ID:  "+final);
                      not=1;
                    filter1="ID,1,"+final;
                    }
                  else{
                    $("h3#filter").append(" with the ID: "+final);
                      filter1="ID,0,"+final;

                  }
                $("#operation").css("display", "inline-block");
                $(".toggle").css("display", "inline-block");
            } 
            else{ //if the filter field is not empty
                if ($("input[type=checkbox]#NotID").is(':checked')) {
                    $("h3#filter1").append(" Not with the ID:  "+final);
                    not=1;
                    filter2= "ID,1,"+final;
                    Woperation="and";
                    }
                else{
                    $("h3#filter1").append("  with the ID "+final);
                    filter2="ID,0,"+final;
                    Woperation="and";
                }
            }
})     
    
    

//reset all the checkbox
 function resetAll(){
        $("input#date").prop("checked", false);
        $("input#location").prop("checked", false);
        $("input#ID").prop("checked", false);
        $(".hidedate").css("display", "none");
        $(".hidelock").css("display", "none");
        $(".hideID").css("display", "none");
        $("h3#filter").text("");
        $("h3#filter1").text("");
        $("#operation").css("display", "none");
        $(".toggle").css("display", "none");
        Woperation="one";
        filter1=null;
        filter1=null;

 }     
    
    
 // reset the filters   
 $("button#reset").click(function() {
      resetAll();
})     
    
    
// and or checkbox    
$("input.toggle").click(function() {
     if ($(this).is(':checked')) {
        $("#operation").css("color", "#4CD964");
        $("h3#operation").text("OR");
         Woperation="or";
     }
    else{
        $("#operation").css("color", "red");
        $("h3#operation").text("AND");
        Woperation="and";

    }			
})    
    
    
    
    
    
    
    
});