<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Fernando
  Date: 2015-04-23
  Time: 09:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${msg}</title>
</head>
<body>
  <form:form commandName="purchase" method="post">
    ${message}:<br><form:input path="${var}" type="text"/><br>
    <input type="submit" value="Submit" /> <input type="reset" value="Reset" />
  </form:form>
</body>
</html>