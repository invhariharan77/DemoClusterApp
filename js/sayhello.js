//$('.security_img').on('click', securityImageAlert);
function securityImageAlert(src) {
	document.getElementById("workload_nodered_image").src = src;
	$('#alert_security_images_alert').show();
	$('#overlay_alert_background').attr('data-state', 'active');
}
function takeAction(id) {
	$("[id*='selectedAction']").removeClass('selected_box');
	$("#" + id).addClass('selected_box');
	window[id]();
}
function closeImagePopUp() {
	$('#alert_security_images_alert').hide();
	$('#overlay_alert_background').attr('data-state', '');
}
function selectedAction1() {
	$("#questionResponse_div").html('');
	//		.html('<jsp:forward page="/UserController?action=listNodes" />');
	var redirecturl = "https://" + window.location.host+"/DemoClusterApp/mylistnodes.jsp?action=mylistnodes";
    //console.log("url"+redirecturl);
	openPage(redirecturl);
	/**
	var featuresSummary = "<div class='response_box' >";
	featuresSummary += "<iframe id=\"myIframe\" width=\"560\" height=\"315\""+
	+"src=\""+redirecturl+"\" frameborder=\"0\" allowfullscreen>" +
			"<a href=\""+redirecturl+"\" target=\"myIframe\">Link Text</a></iframe>"
			+ "</div>";
	$("#questionResponse_div").html(featuresSummary);
    **/
	 
	// $('#questionResponse_div').load('listNodes.jsp');
	// $('#questionResponse_div').load('');

}
function openPage(pageURL) {
   
    //window.location.href = pageURL;
	var strWindowFeatures = "location=yes,height=570,width=520,scrollbars=yes,status=yes";
	var URL = pageURL+";url=" + location.href;
	var win = window.open(URL, "_blank", strWindowFeatures);
    //window.open(pageURL, '_blank');
}
function selectedAction1_disabledfornow() {

	data = [ {
		"nodeid" : "testapp1",
		"hostname" : "pqr",
		"ipaddress" : "1.1.1.1",
		"requests" : "10"
	}, {
		"nodeid" : "testapp2",
		"hostname" : "pqr",
		"ipaddress" : "2.2.2.2",
		"requests" : "11"
	}, {
		"nodeid" : "testapp3",
		"hostname" : "xyz",
		"ipaddress" : "3.3.3.3",
		"requests" : "9"
	}, {
		"nodeid" : "testapp4",
		"hostname" : "jkl",
		"ipaddress" : "4.4.4.4",
		"requests" : "11"
	}, ];
	var featuresSummary = "<table data-table class='bx--table' id='orders_table'>";
	featuresSummary += "<tbody class='bx--table__body'>";
	featuresSummary += "<tr class='bx--table__row' style='border: 1px solid #5aaafa;'>";
	featuresSummary += "<td  class='bx--table__body__cell'>Node ID </td>"
			+ "<td  class='bx--table__body__cell'>Host Name</td>"
			+ "<td  class='bx--table__body__cell'>IP Address</td>"
			+ "<td  class='bx--table__body__cell'>Requests</td>" + "</tr>";

	data.forEach(function(obj) {
		addRow(obj);
	});

	featuresSummary += "</tbody></table>";
	$("#questionResponse_div").html(featuresSummary);

	function addRow(obj) {

		featuresSummary += "<tr class='bx--table__row bx--parent-row-v2' >"
				+ "<td  class='bx--table__body__cell'>" + obj.nodeid + "</td>"
				+ "<td  class='bx--table__body__cell'>" + obj.hostname
				+ "</td>" + "<td  class='bx--table__body__cell'>"
				+ obj.ipaddress + "</td>"
				+ "<td  class='bx--table__body__cell'>" + obj.requests
				+ "</td>" + "</tr>";
	}
}

function selectedAction2() {
	$("#questionResponse_div").html('');
	var featuresSummary = "<div class='response_box' >";
	featuresSummary += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Click here to get more details on this."
			+ "<a target='_blank' href='https://www.ibm.com/in-en/'><button class='bx--btn bx--btn--secondary' style='float:right;'>Login</button></a>"
			+ "<img class='response_img' src='./images/trend_details.png'>"
			+ "</div>";
	$("#questionResponse_div").html(featuresSummary);
}
function selectedAction3() {
	$("#questionResponse_div").html('');
	var featuresSummary = "<div class='response_box' >";
	featuresSummary += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Click here to get more details on this."
			+ "<a target='_blank' href='https://www.ibm.com/in-en/'><button class='bx--btn bx--btn--secondary' style='float:right;'>Login</button></a>"
			+ "<img class='response_img' src='./images/illumio_details.png'>"
			+ "</div>";
	$("#questionResponse_div").html(featuresSummary);
}
function selectedAction4() {
	$("#questionResponse_div").html('');
	var featuresSummary = "<div class='response_box' >";
	featuresSummary += "<div id='myProgress'><div id='myBar'></div></div>"
			+ "<p id='scan_status'>  </p>";
	featuresSummary += "</div>";
	$("#questionResponse_div").html(featuresSummary);
	move();

	function move() {
		var elem = document.getElementById("myBar");
		var width = 1;
		var id = setInterval(frame, 10);
		function frame() {
			if (width >= 100) {
				$("#scan_status").html("Scan is completed. No threats found!!");
				clearInterval(id);
			} else {
				width++;
				elem.style.width = width + '%';
			}
		}
	}

}
function selectedAction5() {
	$("#questionResponse_div").html('');
	var featuresSummary = "<div class='response_box' >";
	featuresSummary += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Click here to get more details on this."
			+ "<a target='_blank' href='https://www.ibm.com/in-en/'><button class='bx--btn bx--btn--secondary' style='float:right;'>Login</button></a>"
			+ "<img class='response_img' src='./images/general_details.png'>"
			+ "</div>";
	$("#questionResponse_div").html(featuresSummary);
}
function selectedAction6() {
	$("#questionResponse_div").html('');
	var featuresSummary = "<div class='response_box' >";
	featuresSummary += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Click here to get more details on this."
			+ "<a target='_blank' href='https://www.ibm.com/in-en/'><button class='bx--btn bx--btn--secondary' style='float:right;'>Login</button></a>"
			+ "<img class='response_img' src='./images/qradar_details.png'>"
			+ "</div>";
	$("#questionResponse_div").html(featuresSummary);
}
function selectedAction7() {
	$("#questionResponse_div").html('');
	var featuresSummary = "<div class='response_box' >";
	featuresSummary += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Click here to login"
			+ "<a target='_blank' href='https://portal.sec.ibm.com'><button class='bx--btn bx--btn--secondary' style='float:right;'>Login</button></a>"
			+ "<img class='response_img' src='./images/vsoc_details.png'>"
			+ "</div>";
	$("#questionResponse_div").html(featuresSummary);
	/**
	 * $("#questionResponse_div").html(''); window.location.href =
	 * "https://portal.sec.ibm.com ";
	 */
	// window.open("https://portal.sec.ibm.com ");
}