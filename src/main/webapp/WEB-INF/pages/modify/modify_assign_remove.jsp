<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<br xmlns:th="http://www.thymeleaf.org">
<!DOCTYPE html>
<html lang="en">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>Nordnet - Modify Assign/Remove</title>
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
				<h1 class="title">Assign/Remove user from license keys</h1>
			</div>		
		</section><!-- end top -->

		<section class="wrapper">
			<div class="content">

				<form:form commandName="modifyForm" method="POST">

					<nav>
						<ul>
							<li>assign remove</li>
						</ul>
					</nav><!-- end navigation menu -->
				</form:form>

			</div><!-- end content -->
		</section>
	</section><!-- end main -->
	
</body>
</html>