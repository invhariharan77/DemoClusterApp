<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<script type="text/javascript" src="./js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="./js/sayhello.js"></script>
<link rel="stylesheet" href="./css/security-components.css">
<link rel="stylesheet" type="text/css" href="./css/sayhello.css">
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>IBM MSS workload</title>
</head>
<body>

	<div id="mainContainer">
		<header class="sy-header bx--global-header" style="height:3.5rem;">
		<img src="./images/ibmlogo.png" height="42" width=""> <a
			class="sy-header__link" href="" id="msstrylink"
			style="color: white; font-size: 1rem; font-weight: 700; font-family: Helvetica !important;"><span
			style="color: white; font-size: 1rem; font-weight: 500; font-family: Helvetica !important;">Hello
				World I am a test app v2.0.0.0, Deployed by ICDS</span></a> </header>

		<!-- alert box for custom workload node red image pop up -->
		<div id="alert_security_images_alert" style="box-shadow: 3px 3px 3px 3px rgba(0, 0, 0, 0.15) !important;display:none;">
			<div id="alert_title" style="background-color:#f4f4f4;color:black;height:1rem;padding:1rem;font-family: Helvetica !important;font-weight:700;font-size:1rem;">
				Security Selection
			</div>
			<div id="" style="padding:1rem;">
				<div style="width:100%;" id="" >
					<img style="padding-top:0.5rem;width:100% !important;height:100%;" src="" alt="" id="workload_nodered_image"/>
				</div>
			</div>
	
			<div style="padding:1rem;bottom:0%;right:1%;float:right;" id="alert_actions">
				<button class="bx--btn bx--btn--secondary" type="button" id="close_security_image_alert" onclick="closeImagePopUp()" >Close</button>
			</div>
		</div>
		
		<div style="width: 98%; height: 100%; margin: 5rem auto 2rem auto;">
		    <div id="aboutworkload" class="box_container">
				<div class="bx--form__label">Security Selection</div>
				<img class="security_img" src="./images/deployworkload.png" height="" width="100%" onclick="securityImageAlert(this.src)">
			</div>
			<div id="questionSelctor" class="box_container">
				<div class="bx--form__label">I am protected by</div>
				<div style="padding-top: 0.5rem;">
					<div class="question_container" onclick="takeAction(this.id)"
						id="selectedAction1">I have load balancing</div>
					<div class="question_container" onclick="takeAction(this.id)"
						id="selectedAction2"><img class="img_container" src="./images/TREND.jpg" height="42" width=""><div class="question_label">-Trend Micro </div></div>
					<div class="question_container" onclick="takeAction(this.id)"
						id="selectedAction3"><img class="img_container" src="./images/Illumio.png" height="42" width=""><div class="question_label">-Illumio </div></div>
					<!--  <div class="question_container" onclick="takeAction(this.id)"
						id="selectedAction4"><div class="question_label">You can scan me for vulnerabilities</div></div> -->
					<div class="question_container" onclick="takeAction(this.id)"
						id="selectedAction5"><img class="img_container" src="./images/vyatta.png" height="42" width=""><div class="question_label">-Managed by MSS</div></div>
					<div class="question_container" onclick="takeAction(this.id)"
						id="selectedAction6"><img class="img_container" src="./images/QRadar.png" height="42" width=""><div class="question_label">-QRadar</div></div>
					<div class="question_container" onclick="takeAction(this.id)"
						id="selectedAction7"><img class="img_container" src="./images/managed_security_services.jpeg" height="32" width="80"><div class="question_label">-Login to IBM Security Services</div></div>
				</div>
			</div>
			<div id="questionResponse" class="box_container">
				<div class="bx--form__label">Try me Out</div>
				<div id="questionResponse_div"></div>
			</div>
		</div>
	</div>
	<div data-sy-overlay="" class="sy-overlay" aria-hidden="false" role="presentation" data-state="" id="overlay_alert_background"></div>


</body>
</html>