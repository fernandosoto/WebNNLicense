<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%--
  Created by IntelliJ IDEA.
  User: Fernando
  Date: 2015-04-23
  Time: 09:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Show Purchases</title>
</head>
<body>
  <form:form modelAttribute="purchases">
    <c:forEach items="${purchases}" var="purchase">
      <p>ManufacturerID: ${purchase.manufacturerId}</p>
      <p>DistributorID: ${purchase.distributorId}</p>
      <p>Product Name: ${purchase.productName}</p>
      <p>Type: ${purchase.type}</p>
      <p>FreeText: ${purchase.freeText}</p>
    </c:forEach>
  </form:form>
</body>
</html>
