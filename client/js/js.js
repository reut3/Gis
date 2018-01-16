$(document).ready(function () {

    
var filter1="";
var filter2="";
var Woperation="none"; 
var files=false;
    
    

    
    
setInterval ( () => {
			 $.ajax(
				{
					"url": encodeURI("/lines?")
				}
			).then(
				function(output) {
                  $("h3#text2").text("The DataBase contains "+output+ " lines"); 
                  $(".hide").css("display", "inline");

				}
			);
    
        $.ajax(
				{
					"url": encodeURI("/Flines?")
				}
			).then(
				function(output) {
                  $("h3#text3").text("The filtered DataBase contains "+output+ " lines");
                  $(".hide").css("display", "inline");

				}
			);
        $.ajax(
				{
					"url": encodeURI("/router?")
				}
			).then(
				function(output) {
                  $("h3#text4").text("The filtered DataBase contains "+output+ " routers");
                  $(".hide").css("display", "inline");

				}
			);
		}, 5000);    
    
    
    
    
    
//applay filter field    
$("input#applyFilter").click(function() {
        var input = Woperation+","+filter1+","+filter2;
        if ($("h3#filter").text() !== "" && files ===true) {
            $("h3#text").text("The filter has applied");
        } else{
             alert("nothing to filter");
        }
    
         $.ajax(
				{
					"url": encodeURI("/applyFilter?"+input)
				}
			).then(
				function(output) {
				}
			);
            
        $.ajax(
				{
					"url": encodeURI("/Flines?")
				}
			).then(
				function(output) {
                  $("h3#text3").text("The filtered DataBase contains "+output+ " lines");
                  $(".hide").css("display", "inline");

				}
			);
        $.ajax(
				{
					"url": encodeURI("/router?")
				}
			).then(
				function(output) {
                  $("h3#text4").text("The filtered DataBase contains "+output+ " routers");
                  $(".hide").css("display", "inline");

				}
			);
			return false;		
})
    
    
  
//when clicked on send folder button  
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
                        $("h3#text").text("The folder has been recived, the DataBase has updated ");
                        $(".hide").css("display", "inline");
                        files=true;
                    }
				}
			);
        $.ajax(
				{
					"url": encodeURI("/lines?")
				}
			).then(
				function(output) {
                  $("h3#text2").text("The DataBase contains "+output+ " lines"); 
                  $(".hide").css("display", "inline");

				}
			);
            
        $.ajax(
				{
					"url": encodeURI("/Flines?")
				}
			).then(
				function(output) {
                  $("h3#text3").text("The filtered DataBase contains "+output+ " lines");
                  $(".hide").css("display", "inline");

				}
			);
        $.ajax(
				{
					"url": encodeURI("/router?")
				}
			).then(
				function(output) {
                  $("h3#text4").text("The filtered DataBase contains "+output+ " routers");
                  $(".hide").css("display", "inline");

				}
			);
			return false;
		})


 
    
//when clicked on apply SQL table  
$("input#applySQL").click(function() {
			var name = $("input#SQLname").val();
			var TableName = $("input#SQLnameTable").val();
            var ip = $("input#SQLip").val();
            var port = $("input#SQLport").val();
            var UserName = $("input#SQLuser").val();
            var password = $("input#SQLpassword").val();
            
            var input= ip+","+port+","+UserName+","+password+","+name+","+TableName;
    
			$.ajax(
				{
					"url": encodeURI("/sql?" +input)
				}
			).then(
				function(output) {
                    if(output!="1"){
                        alert(output);
                    }
                    else{
                        $("h3#text").text("The SQL Table has been recived, the DataBase has updated ");
                        $(".hide").css("display", "inline");
                        files=true;
                    }
				}
			);
        $.ajax(
				{
					"url": encodeURI("/lines?")
				}
			).then(
				function(output) {
                  $("h3#text2").text("The DataBase contains "+output+ " lines"); 
                  $(".hide").css("display", "inline");

				}
			);
            
        $.ajax(
				{
					"url": encodeURI("/Flines?")
				}
			).then(
				function(output) {
                  $("h3#text3").text("The filtered DataBase contains "+output+ " lines");
                  $(".hide").css("display", "inline");

				}
			);
        $.ajax(
				{
					"url": encodeURI("/router?")
				}
			).then(
				function(output) {
                  $("h3#text4").text("The filtered DataBase contains "+output+ " routers");
                  $(".hide").css("display", "inline");

				}
			);
			return false;
		})    
    
    
    
    

//when clicked on save filter button  
$("button#saveFilter").click(function() {
			var name = $("input#inputFilter").val();
            var input = name+","+Woperation+","+filter1+","+filter2;
            if(name!==""){
			$.ajax(
				{
					"url": encodeURI("/filterFile?" +input)
				}
			).then(
				function(output) {
                    if(output!=="1" && output!=="2"){
                        alert(output);
                    }
                    else if(output==="1"){
                        $("h3#toSave").text("The file has been created on your computer inside savedFilters folder! under the name '"+name+"'");
                        $(".hide").css("display", "inline");
                    }
                    else{
                        alert("There is nothing on the filter to be saved")
                    }
				}
			);    
			return false;
            }
            else{
                alert("The name field is empty");
            }
		})
    
    
 //when clicked on upload filter button  
$("button#uploadfilter").click(function() {
			var input = $("input#inputFile").val();
            if(input!==""){
			$.ajax(
				{
					"url": encodeURI("/uploadFilter?" +input)
				}
			).then(
				function(output) {
                        $("h3#filter1").text("");
                        $("h3#filter").text(output);
                        $("h3#toUpload").text("The filtered DataBase has changed");
                        $(".hide").css("display", "inline");
                    }

				
			);
            $.ajax(
				{
					"url": encodeURI("/Flines?")
				}
			).then(
				function(output) {
                  $("h3#text3").text("The filtered DataBase contains "+output+ " lines");
                  $(".hide").css("display", "inline");

				}
			);
            $.ajax(
				{
					"url": encodeURI("/router?")
				}
			).then(
				function(output) {
                  $("h3#text4").text("The filtered DataBase contains "+output+ " routers");
                  $(".hide").css("display", "inline");

				}
			);    
			return false;
            }
            else{
                alert("The file field is empty");
            }
		})   
    
    
    
    
    
    
    
    
    
//when clicked on send file button  
$("button#upload").click(function() {
			var input = $("input#fileToUpload").val();
			$.ajax(
				{
					"url": encodeURI("/upload?" +input)
				}
			).then(
				function(output) {
                    if(output!=="1" && output!=="2"){
                        alert(output);
                    }
                    else if(output==="2"){
                        alert("not in format 46 CSV file");
                    }
                    else{
                        $("h3#text").text("The file has been recived, the DataBase has updated ");
                        $(".hide").css("display", "inline");
                        files=true;
                    }
				}
			);
        $.ajax(
				{
					"url": encodeURI("/lines?")
				}
			).then(
				function(output) {
                  $("h3#text2").text("The DataBase contains "+output+ " lines"); 
                  $(".hide").css("display", "inline");

				}
			);
            
        $.ajax(
				{
					"url": encodeURI("/Flines?")
				}
			).then(
				function(output) {
                  $("h3#text3").text("The filtered DataBase contains "+output+ " lines");
                  $(".hide").css("display", "inline");

				}
			);
        $.ajax(
				{
					"url": encodeURI("/router?")
				}
			).then(
				function(output) {
                  $("h3#text4").text("The filtered DataBase contains "+output+ " routers");
                  $(".hide").css("display", "inline");

				}
			);
			return false;
		})
  
    
    
    
 //when clicked on delete filters history  
$("button#deleteFilter").click(function(){
        $.ajax(
				{
					"url": encodeURI("/reset?")
				}
			).then(
				function(output) {
                    if(output!="1"){
                        alert(output);
                    }
                    else{
                        $("h3#text").text("The filtered DataBase is reset");     
                        $(".hide").css("display", "inline");
                    }
				}
			);
    
            $.ajax(
				{
					"url": encodeURI("/Flines?")
				}
			).then(
				function(output) {
                  $("h3#text3").text("The filtered DataBase contains "+output+ " lines");
                  $(".hide").css("display", "inline");

				}
			);
    
        $.ajax(
				{
					"url": encodeURI("/router?")
				}
			).then(
				function(output) {
                  $("h3#text4").text("The filtered DataBase contains "+output+ " routers");
                  $(".hide").css("display", "inline");

				}
			);
			return false
});   
    
    
    
//when clicked on save to csv button  
$("button#csv").click(function(){
        $.ajax(
				{
					"url": encodeURI("/toCSV?")
				}
			).then(
				function(output) {
                    if(output!="1"){
                        alert(output);
                    }
                    else{
                        $("h3#text").text("The file saved as CSV on your computer! under the name 'finalFile'");     
                        $(".hide").css("display", "inline");
                    }
				}
			);
			return false
});



    
//when clicked on save to kml button  
$("button#kml").click(function(){
        $.ajax(
				{
					"url": encodeURI("/toKML?")
				}
			).then(
				function(output) {
                    if(output!="1"){
                        alert(output);
                    }
                    else{
                        $("h3#text").text("The file saved as KML on your computer! under the name 'kmlFile'");     
                        $(".hide").css("display", "inline");
                    }
				}
			);
			return false
});    
    
    
    
    
    
    
//when clicked on delete dataBase button  
$("button#delete").click(function(){
    resetAll();
        $.ajax(
            {
				"url": encodeURI("/delete?")
            }).then(
				function(output) {
                    if(output!="1"){
                        alert(output);
                    }
                    else{
                        $("h3#text").text("The dataBase has deleted");
                        $(".hide").css("display", "inline");
                        files=false;
                    }
				}
			);
        $.ajax(
				{
					"url": encodeURI("/lines?")
				}
			).then(
				function(output) {
                  $("h3#text2").text("The DataBase contains "+output+ " lines"); 
                  $(".hide").css("display", "inline");

				}
			);
            
        $.ajax(
				{
					"url": encodeURI("/Flines?")
				}
			).then(
				function(output) {
                  $("h3#text3").text("The filtered DataBase contains "+output+ " lines");
                  $(".hide").css("display", "inline");

				}
			);
        $.ajax(
				{
					"url": encodeURI("/router?")
				}
			).then(
				function(output) {
                  $("h3#text4").text("The filtered DataBase contains "+output+ " routers");
                  $(".hide").css("display", "inline");

				}
			);
			return false
    
});
    
    



    
    
    
    
    
   
    
    
    
    
    
    
    
    
    
    
    
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
    
            //if the first filter field is empty
            if ($("h3#filter").text() === "") {
                  if ($("input[type=checkbox]#NotDate").is(':checked')) {
                    $("h3#filter").append(" Not between the Dates  "+final);
                      not=1;
                    filter1="Date,1,"+input1+" "+input11+","+input2+" "+input22;
                    }
                  else{
                    $("h3#filter").append(" Dates between "+final);
                    filter1="Date,0,"+input1+" "+input11+","+input2+" "+input22;
                  }
                Woperation="one";                
            }
            else if($("h3#filter1").text() === ""){ //if the second filter field is empty
                Woperation="and";
                $("#operation").css("display", "inline-block");
                $(".toggle").css("display", "inline-block");
                if ($("input[type=checkbox]#NotDate").is(':checked')) {
                    $("h3#filter1").append(" Not between the Dates  "+final);
                    not=1;
                    filter2="Date,1,"+input1+" "+input11+","+input2+" "+input22;
                    }
                else{ 
                    $("h3#filter1").append(" Dates between "+final);
                    filter2="Date,0,"+input1+" "+input11+","+input2+" "+input22;
                }
            }
            else{//if both fields are fiiled
                alert("You can't choose more than 2 filters at one time");
                $(".hidedate").css("display", "none");
                if($("input.onNone:checkbox:checked").length>2){
                    $("input#date").prop("checked", false);
                }

            }
     
})   
  
 // applay the location filter   
 $("input#applyLocation").click(function() {
			var input1 = $("input#minlat").val();
            var input11 = $("input#minlon").val();
            var input2 = $("input#maxlat").val();
            var input22 = $("input#maxlon").val();
            var not=0;

            var final= input1+" "+ input11+ "  to "+input2+" "+ input22;
            if ($("h3#filter").text() === "") {//if the first filter field is empty
                if ($("input[type=checkbox]#NotLocation").is(':checked')) {
                    $("h3#filter").append(" Not between the locations  "+final);
                      not=1;
                    filter1="Location,1,"+input1+","+input11+","+input2+","+input22;
                    }
                  else{
                    $("h3#filter").append(" Locations between "+final);
                    filter1="Location,0,"+input1+","+input11+","+input2+","+input22;
                  }
                Woperation="one";                
            } 
            else if($("h3#filter1").text() === ""){ //if the second filter field is empty
                Woperation="and";
                $("#operation").css("display", "inline-block");
                $(".toggle").css("display", "inline-block");
                if ($("input[type=checkbox]#NotLocation").is(':checked')) {
                        $("h3#filter1").append(" Not between the locations  "+final);
                        not=1;
                        filter2="Location,1,"+input1+","+input11+","+input2+","+input22;
                    }
                else{
                    $("h3#filter1").append(" Locations between "+final);
                    filter2="Location,0,"+input1+","+input11+","+input2+","+input22;
                    }
            }
            else{//if both fields are fiiled
                alert("You can't choose more than 2 filters at one time");
                $(".hidelock").css("display", "none");
                if($("input.onNone:checkbox:checked").length>2){
                $("input#location").prop("checked", false);
                }
            }
})    
    
    
    
 // applay the ID filter   
 $("input#applyID").click(function() {
			var final = $("input#idName").val();
            var not=0;
     
            if(final!=""){
            if ($("h3#filter").text() === "") {//if the first filter field is empty
                if ($("input[type=checkbox]#NotID").is(':checked')) {
                    $("h3#filter").append(" Not with the ID:  "+final);
                      not=1;
                    filter1="ID,1,"+final;
                    }
                  else{
                    $("h3#filter").append(" with the ID: "+final);
                      filter1="ID,0,"+final;

                  }
                Woperation="one";
            } 
            else if($("h3#filter1").text() === ""){ //if the second filter field is empty
                Woperation="and";
                $("#operation").css("display", "inline-block");
                $(".toggle").css("display", "inline-block");
                if ($("input[type=checkbox]#NotID").is(':checked')) {
                    $("h3#filter1").append(" Not with the ID:  "+final);
                    not=1;
                    filter2= "ID,1,"+final;
                    }
                else{
                    $("h3#filter1").append("  with the ID "+final);
                    filter2="ID,0,"+final;
                }
            }
            else{//if both fields are fiiled
                alert("You can't choose more than 2 filters at one time");
                $(".hideID").css("display", "none");
                if($("input.onNone:checkbox:checked").length>2){
                $("input#ID").prop("checked", false);
                }
            }
        }
    else{
        alert("unvalid ID, please enter ID");
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
        Woperation="none";
        filter1=null;
        filter2=null;

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