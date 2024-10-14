<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<c:url value="/templates/web" var="URL"></c:url>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="description" content="Male_Fashion Template">
<meta name="keywords" content="Male_Fashion, unica, creative, html">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Male-Fashion | Template</title>

<!-- Google Font -->
<link
	href="https://fonts.googleapis.com/css2?family=Nunito+Sans:wght@300;400;600;700;800;900&display=swap"
	rel="stylesheet">

<!-- Font awesome -->
<link rel="stylesheet" href="${URL}/css/font-awesome.min.css"
	type="text/css" />

<!-- Css Styles -->
<link rel="stylesheet" href="${URL}/css/bootstrap.min.css"
	type="text/css">
<link rel="stylesheet" href="${URL}/css/font-awesome.min.css"
	type="text/css">
<link rel="stylesheet" href="${URL}/css/elegant-icons.css"
	type="text/css">
<link rel="stylesheet" href="${URL}/css/magnific-popup.css"
	type="text/css">
<link rel="stylesheet" href="${URL}/css/nice-select.css" type="text/css">
<link rel="stylesheet" href="${URL}/css/owl.carousel.min.css"
	type="text/css">
<link rel="stylesheet" href="${URL}/css/slicknav.min.css"
	type="text/css">
<link rel="stylesheet" href="${URL}/css/style.css" type="text/css">
<link rel="stylesheet" href="${URL}/css/main.css" />
</head>

<body>
	<!-- Page Preloder -->
	<div id="preloder">
		<div class="loader"></div>
	</div>

	<!-- Header Section Begin -->
	<%@ include file="/common/web/header.jsp"%>
	<!-- Header Section End -->

	<!-- Content Begin -->
	<sitemesh:write property="body" />
	<!-- Content End -->

	<!-- Footer Section Begin -->
	<%@ include file="/common/web/footer.jsp"%>
	<!-- Footer Section End -->

	<!-- Js Plugins -->
	<script src="${URL}/js/jquery-3.3.1.min.js"></script>
	<script src="${URL}/js/bootstrap.min.js"></script>
	<script src="${URL}/js/jquery.nice-select.min.js"></script>
	<script src="${URL}/js/jquery.nicescroll.min.js"></script>
	<script src="${URL}/js/jquery.magnific-popup.min.js"></script>
	<script src="${URL}/js/jquery.countdown.min.js"></script>
	<script src="${URL}/js/jquery.slicknav.js"></script>
	<script src="${URL}/js/mixitup.min.js"></script>
	<script src="${URL}/js/owl.carousel.min.js"></script>
	<script src="${URL}/js/main.js"></script>
	<script src="${URL}/js/bootstrap.bundle.min.js"></script>
</body>
</html>