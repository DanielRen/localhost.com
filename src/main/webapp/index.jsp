<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<html>
<head>
<title>DanielWebSite</title>
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
</head>
</head>
<body>
<h2>Please login!</h2>
 <div> Message
 		<c:if test="${not empty message}">
			<div id="message"><c:out value="${message}" /></div>	
		 </c:if>
</div>
<div>
   <form:form modelAttribute="userInfo" method="POST" action="/website/login" commandName="userInfo">
        <table>
		    <tr>
		        <td><form:label path="userName">userName</form:label></td>
		        <td><form:input path="userName" /></td>
		    </tr>
		    <tr>
		        <td><form:label path="password">password</form:label></td>
		        <td><form:password path="password" /></td>
		    </tr>
		    <tr>
		        <td>
		            <input type="submit" value="Sign in"/>
		        </td>
		        <td>
		            <a href="/website/createUser"><input type="button" value="Sign up"/></a>
		        </td>
		    </tr>
		</table> 
    </form:form>
</div>
</body>
</html>
