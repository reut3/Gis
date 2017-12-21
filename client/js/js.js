$(document).ready(function () {


$("button#folderSend").click(function() {
			var input = $("input#folder").val();
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