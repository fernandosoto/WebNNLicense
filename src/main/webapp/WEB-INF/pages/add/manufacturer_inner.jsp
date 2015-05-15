<%--
  Created by IntelliJ IDEA.
  User: Fernando
  Date: 2015-05-07
  Time: 14:31
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<br xmlns:th="http://www.thymeleaf.org">
<!DOCTYPE html>
<html lang="en">

<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  <title>Nordnet - Add License</title>
  <meta charset="utf-8">
  <meta name="description" content="Nordnet - Add License"/>
  <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0" />
  <link rel="stylesheet" type="text/css" href="../../resources/NNLicenseTheme/Nordnet/css/reset.css">
  <link rel="stylesheet" type="text/css" href="../../resources/NNLicenseTheme/Nordnet/css/main.css">
  <script type="text/javascript" src="../../resources/NNLicenseTheme/Nordnet/js/jquery.js"></script>
  <script type="text/javascript" src="../../resources/NNLicenseTheme/Nordnet/js/main.js"></script>
</head>
<body>

<header>
  <div class="logo">
    <a href="index"><img src="../../../resources/NNLicenseTheme/Nordnet/img/nordnetlogga.png" title="Nordnet" alt="Nordnet"/></a>
  </div><!-- end logo -->

  <div id="menu_icon"></div>
  <nav>
    <ul>
      <li><a href="/">Main</a></li>
      <li><a href="#">...</a></li>
      <li><a href="#">...</a></li>
      <li><a href="#">...</a></li>
    </ul>
  </nav><!-- end navigation menu -->


  <div class="footer clearfix">

    <!-- FOOTER KOD HÄR!! -->

  </div ><!-- end footer -->


</header><!-- end header -->

<section class="main clearfix">

  <section class="top">
    <div class="wrapper content_header clearfix">
      <div class="work_nav">

        <ul class="btn clearfix">
          <li><a href="#" class="previous" data-title="Previous"></a></li>
          <li><a href="main/index.jsp" class="grid" data-title="Portfolio"></a></li>
          <li><a href="#" class="next" data-title="Next"></a></li>
        </ul>

      </div><!-- end work_nav -->
      <h1 class="title">Add License</h1>
    </div>
  </section><!-- end top -->

  <section class="wrapper">
    <div class="content">
      <form:form commandName="manufacturer" method="POST">
        <div class="fieldset">
          <fieldset>
            <nav>
              <ul>
                <li><br>Manufacturer name: <form:input path="name" type="text"/></li>
                <li><br>Free Text: <form:input path="freeText" type="text"/></li>
                <li><br><input type="submit" value="Add" />  <input type="reset" value="Clear"/></li>
              </ul>
            </nav><!-- end navigation menu -->
          </fieldset>
        </div>
      </form:form>

    </div><!-- end content -->
  </section>
</section><!-- end main -->

</body>
</html>