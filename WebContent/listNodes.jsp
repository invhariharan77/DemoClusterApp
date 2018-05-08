<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Show All Worker Nodes</title>
</head>
<body>

    <div class="jumbotron" >
      <center style="font-size:40px"> <b>Bonjour!</b> </center>
      <center style="font-size:20px"> <i>Application Workload stats!</i> </center>
    </div>

	<div style="text-align:center; font-size:25px; margin-top:40px">
	    <table align=center border=1>
	        <thead>
	            <tr>
	                <th>Node ID</th>
	                <th>Host Name</th>
	                <th>IP Address</th>
	                <th>Requests</th>
	            </tr>
	        </thead>
	        <tbody>
	            <c:forEach items="${nodes}" var="node">
	                <tr>
	                    <td><c:out value="${node.nodeid}" /></td>
	                    <td style=background-color:<c:out value="${node.nodecolor}"/>><c:out value="${node.hostname}" /></td>
	                    <td><c:out value="${node.ipaddress}" /></td>
	                    <td><c:out value="${node.requests}" /></td>
	                </tr>
	            </c:forEach>
	        </tbody>
	    </table>
    </div>

    <p align=center> 
    	This page is served by <b style=background-color:<c:out value="${currentnode.nodecolor}"/>> <c:out value="${currentnode.hostname}"></c:out></b> host.
    </p>


</body>
</html>