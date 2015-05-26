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

				<h1 class="title">Add License</h1>
			</div>		
		</section><!-- end top -->

		<section class="wrapper">
			<div class="content">
				<form:form commandName="registerForm" method="POST">
					<div class="fieldset">
					<fieldset>
					<nav>
						<ul>
							<li><br>Product name: <form:input path="purchases.productName" type="text" maxlength="50"/></li>
							<li><br><div style="color:crimson; font-size: 12pt"> *</div>Manufacturer Name:
								<form:select path="manufacturer.id" title="Select manufacturer">
									<form:option value="NONE" label="--- Select ---"/>
									<form:options items="${manufacturers}" itemValue="id" itemLabel="name"/>
									</form:select>
							</li>
							<li><br><div style="color:crimson; font-size: 12pt"> *</div>Distributor Name:
								<form:select path="distributor.id" title="Select distributor">
									<form:option value="NONE" label="--- Select ---"/>
									<form:options items="${distributors}" itemValue="id" itemLabel="name"/>
								</form:select>
							</li>
							<li><br><div style="color:crimson; font-size: 12pt"> *</div>
								Expire date: <form:input path="date" type="date" /><div style="color:crimson; font-size: 8pt"> (yyyy-mm-dd)</div></li>
							<li><br><div style="color:crimson; font-size: 12pt"> *</div>License key separator: <form:input path="keySeparator" type="text"/></li>
							<li><br>License type: <form:input path="purchases.type" type="text" maxlength="50"/></li>
							<li><br>Serial keys: <form:textarea path="serialKeys" rows="5" cols="80"/></li>
							<li><br>Comments: <form:textarea path="purchases.freeText" rows="5" cols="80" maxlength="300"/></li>
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