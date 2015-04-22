<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<br xmlns:th="http://www.thymeleaf.org">
<head>
	<title>Getting Started: Handing Form Submission</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<html>
<body>
	<h1>${message}</h1>
	<form:form commandName="purchase" method="POST">
		ManufacturerID: <br><form:input path="manufacturerId" type="text" /><br>
		DistributorID: <br><form:input path ="distributorId" type="text"/><br>
		ProductName:<br><form:input path="productName" type="text"/><br>
		Type: <br><form:input path="type" type="text"/><br>
		FreeText: <br><form:input path="freeText" type="text"/><br>
		<input type="submit" value="Submit" /> <input type="reset" value="Reset" />
	</form:form>
	<input type="button" onclick="window.location.href='/time'" value="Time">
</body>
</html>