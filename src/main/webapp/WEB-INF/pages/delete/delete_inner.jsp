<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<br xmlns:th="http://www.thymeleaf.org">
<!DOCTYPE html>
<html lang="en">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>Nordnet - Delete purchase/license</title>
	<meta charset="utf-8">
	<meta name="description" content="Nordnet - Delete purchase/license"/>
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

			<h1 class="title">Delete</h1>
		</div>
	</section><!-- end top -->

	<section class="wrapper">

		<div class="content">

			<form:form commandName="deleteForm" method="POST">
				<nav>
					<ul>
						<li><form:radiobutton path="radioButtonSelect" name="group1" value="delete_purchase_details" checked="checked"/> Delete purchase<br><br></li>
						<li><form:radiobutton path="radioButtonSelect" name="group1" value="delete_licenseKey_details"/> Delete license<br><br></li>
						<li><br><input type="submit" value="Ok" /></li>
					</ul>
				</nav><!-- end navigation menu -->
			</form:form>

		</div><!-- end content -->
	</section>
</section><!-- end main -->

</body>
</html>