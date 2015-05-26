<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<br xmlns:th="http://www.thymeleaf.org">
<!DOCTYPE html>
<html lang="en">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>Nordnet - Search Details</title>
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
			<li><a href="/index">Main</a></li>
			<li><a href="/" class="selected">Log out</a></li>
		</ul>
	</nav><!-- end navigation menu -->


	<div class="footer clearfix">

		<!-- FOOTER KOD HÄR!! -->

	</div ><!-- end footer -->


</header><!-- end header -->

<section class="main clearfix">

	<section class="top">
		<div class="wrapper content_header clearfix">

			<h1 class="title">Modify - license - details</h1>
		</div>
	</section><!-- end top -->

	<section class="wrapper">
		<div class="content">
			<form:form commandName="modifyForm" method="POST">
				<div class="fieldset">
					<fieldset>
						<nav>
							<ul>
								<div align="right">
									<li>Product name: <form:input path="purchase.productName" value="${purchase.productName}" readonly="true" cssStyle="border: hidden"/><br><br></li>
									<li>Serial key: <form:input path="license.serialKey" value="${license.serialKey}" maxlength="100" /></li>
									<li><br><div style="color:crimson; font-size: 12pt"> *</div>Expire date: <form:input path="expireDate"  type="date" value="${expireDate}" maxlength="10" /><div style="color:crimson; font-size: 8pt"> (yyyy-mm-dd)</div></li>
									<li><br>User: <form:input path="license.user" value="${license.user}" maxlength="30"/></li>

								</div>
								<li><br><input type="submit"  value="Modify"/></li>
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