<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<br xmlns:th="http://www.thymeleaf.org">
<!DOCTYPE html>
<html lang="en">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>Nordnet - User / Search results</title>
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

				<h1 class="title">Search result</h1>
			</div>		
		</section><!-- end top -->

		<section class="wrapper">
			<div class="content">
				<form:form commandName="searchForm" method="POST">
					<div class="fieldset">
						<fieldset>
							<nav>
								<ul style="list-style: none;">
									<li><br>User results:</li>
									<li>
									<li><br>Username: <form:input path="searchUserName" type="text" value="${searchUserName}" readonly="true"/></li>
										<form:select path="" title="Users" size="8" style="width: 300px;">
											<form:option value="NONE" label="--- Select user ---" disabled="true"/>
											<form:options items="${userLicenseList}" itemValue="serialKey" itemLabel="serialKey"/>
										</form:select>
								<li><br><input type="submit" value="Show licenses"/> <a href="/search_inner" ><input type="button" value="New search"></a> </li>
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