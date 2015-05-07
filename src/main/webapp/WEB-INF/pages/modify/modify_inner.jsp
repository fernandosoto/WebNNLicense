<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<br xmlns:th="http://www.thymeleaf.org">
<!DOCTYPE html>
<html lang="en">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>Nordnet - Modify</title>
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
				<h1 class="title">Modify License</h1>
			</div>		
		</section><!-- end top -->

		<section class="wrapper">
			<div class="content">

				<form:form commandName="purchase" method="POST">

					<nav>
						<ul>
							<li><br>Product name: <form:input path="productName" type="text"/></li>
							<li><br>Manufacturer:
								<form:select path="manufacturerName" type="text" title="Select manufacturer">
									<form:option value="None" label="--- Select ---"/>
								</form:select>
								<input type="button" value="Edit" />  <input type="button" value="Delete" />
							</li>
							<li><br>Distributor:
								<form:select path ="distributorName" type="text" title="Select distributor" >
									<form:option value="None" label="--- Select ---"/>
								</form:select>
								<input type="button" value="Edit" />  <input type="button" value="Delete" />
							</li>
							<li><br>License type: <form:input path="type" type="text"/></li>
							<li><br>Comments:<form:textarea path="freeText" rows="5" cols="30" /></li>
							<li><br><input type="submit" value="Submit" /> <input type="reset" value="Reset" /></li>

						</ul>
					</nav><!-- end navigation menu -->
				</form:form>


				Test:
				<form:form method="POST" commandname="manufacturer">
					<form:select path="manufacturer" items="${manufacturerMap}">
					</form:select></ul>
				</form:form>


			</div><!-- end content -->
		</section>
	</section><!-- end main -->
	
</body>
</html>