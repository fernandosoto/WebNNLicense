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
			<div class="work_nav">

				<ul class="btn clearfix">
					<li><a href="#" class="previous" data-title="Previous"></a></li>
					<li><a href="main/index.jsp" class="grid" data-title="Portfolio"></a></li>
					<li><a href="#" class="next" data-title="Next"></a></li>
				</ul>

			</div><!-- end work_nav -->
			<h1 class="title">Modify - license - Results</h1>
		</div>
	</section><!-- end top -->

	<section class="wrapper">
		<div class="content">
			<form:form commandName="modifyForm" method="POST">
				<div class="fieldset">
					<fieldset>
						<nav>
							<ul>
								<div align="left">
									<li>Product name: <form:input path="purchase.productName" value="${purchase.productName}" readonly="true" cssStyle="border: hidden"/></li>
									<li><br>Manufacturer: <form:input path="purchase.manufacturerName" value="${purchase.manufacturerName}" readonly="true" cssStyle="border: hidden"/></li>
									<li><br>Distributor: <form:input path="purchase.distributorName" value="${purchase.distributorName}" readonly="true" cssStyle="border: hidden"/></li>
									<li><br>Created by: <form:input path="purchase.createdBy" value="${purchase.createdBy}" readonly="true" cssStyle="border: hidden"/></li>
									<li><br>Created date: <form:input path="date" value="${date}" readonly="true" cssStyle="border: hidden"/></li>
								</div>
								<li><br>Comments:<br><form:textarea path="purchase.freeText" value="${purchase.freeText}" readonly="true" title="Comments" size="6" style="width: 550px; height: 100px; background-color:#f6f6f6;"/></li>
								<li><br>Serial Keys:<br><form:select  path="license.licenseId" multiple="false" size="8" style="width: 550px;">
									<form:option value="NONE" label="--- Select license ---" disabled="true"/>
									<form:options items="${licenses}" itemValue="licenseId" itemLabel="label"/>
									</form:select>
								</li>
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