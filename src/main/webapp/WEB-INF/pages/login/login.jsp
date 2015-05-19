<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<br xmlns:th="http://www.thymeleaf.org">
<!DOCTYPE html>
<html lang="en">

<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  <title>Nordnet - Login</title>
  <meta charset="utf-8">
  <meta name="author" content="pixelhint.com">
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

    </ul>
  </nav><!-- end navigation menu -->


  <div class="footer clearfix">

    <!-- FOOTER KOD HÄR!! -->

  </div ><!-- end footer -->


</header><!-- end header -->

<section class="main clearfix">

  <section class="top">
    <div class="wrapper content_header clearfix">

      <h1 class="title">Login</h1>
    </div>
  </section><!-- end top -->

  <section class="wrapper">
    <div class="content">
      <form:form commandName="user" method="POST">
        <div class="fieldset">
          <fieldset>
            <nav>
              <ul>
                <div align="left">
                  <li><br>Username: <form:input path="userName" type="text"/></li>
                  <li><br>Password: <form:input path="password" type="password"></form:input></li>
                  <li><br><input type="submit" value="Login" /></a>  <input type="reset" value="Clear"/></li>
                </div>
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