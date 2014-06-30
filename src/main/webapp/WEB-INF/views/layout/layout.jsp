<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<tiles:importAttribute scope="request" />

<!DOCTYPE html>
<html lang="en">
<head>

<!-- Basic Page Needs
  ================================================== -->
<meta charset="utf-8">
<title>Hirelance</title>
<meta name="description" content="">
<meta name="author" content="">

<!-- Mobile Specific Metas
  ================================================== -->
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

<!-- CSS
  ================================================== -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.css" />
  
<link id="skinApply" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/merlin/css/skins/default.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/merlin/css/style.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/merlin/css/skeleton.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/merlin/css/responsive.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/merlin/css/prettyPhoto.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/merlin/css/fontello.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/jquery-ui/css/overcast/jquery-ui.css" id="ui-css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/jquery-ui/css/overcast/jquery.ui.theme.css" id="ui-theme-css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/datatables/css/jquery.dataTables_themeroller.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/jquery/plugin/star-rating/css/jRating.jquery.css" />

<!-- ================ CHOSEN PLUGIN CSS ====================== -->
<%--   <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/chosenplugin/docsupport/style.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/chosenplugin/docsupport/prism.css"> --%>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/chosenplugin/chosen.css">

<!-- Oregano font -->
<link
	href='http://fonts.googleapis.com/css?family=Oregano:400,400italic'
	rel='stylesheet' type='text/css'>

<!-- Favicons ================================================== -->
<link rel="icon" href="${pageContext.request.contextPath}/resources/merlin/images/favicon.ico"/>

<link rel="apple-touch-icon"
	href="${pageContext.request.contextPath}/resources/images/apple-touch-icon.png">
<link rel="apple-touch-icon" sizes="72x72"
	href="${pageContext.request.contextPath}/resources/images/apple-touch-icon-72x72.png">
<link rel="apple-touch-icon" sizes="114x114"
	href="${pageContext.request.contextPath}/resources/images/apple-touch-icon-114x114.png">


<script	src="${pageContext.request.contextPath}/resources/jquery/jquery-1.9.1.min.js"></script>
<script id="ui-script" src="${pageContext.request.contextPath}/resources/jquery-ui/js/jquery-ui-1.10.0.custom.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/datatables/my.js"></script>
<script src="${pageContext.request.contextPath}/resources/datatables/js/jquery.dataTables.min.js"></script>
    <!-- ================ FORM PLUGIN JQUERY ====================== -->
    <script src="${pageContext.request.contextPath}/resources/jquery/jquery.form.min.js"></script>
</head>

<body>
	<div id="wrapoverall">
		<tiles:insertAttribute name="menu" />
		<tiles:insertAttribute name="body" />
		<tiles:insertAttribute name="footer" />
	</div>
	<!-- JS
  ================================================== -->
		<script
		src="${pageContext.request.contextPath}/resources/jquery/jquery.easing.1.3.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/merlin/js/superfish.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/jquery/jquery.tipsy.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/jquery/jquery.tweet.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/jquery/jquery.flickrush.pack.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/jquery/jquery.prettyPhoto.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/jquery/jquery.bxSlider.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/jquery/jquery.fractionslider.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/merlin/js/validation.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/merlin/js/custom.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/bootstrap/js/fileinput.js"></script>
	
	<script type="text/javascript">var contextPath="${pageContext.request.contextPath}"</script>
	<script src="${pageContext.request.contextPath}/resources/jquery/plugin/star-rating/js/jRating.jquery.js"></script>     
     
     <!-- ================ CHOSEN PLUGIN JS ====================== -->
	<script src="${pageContext.request.contextPath}/resources/chosenplugin/chosen.jquery.js"></script>
    <script src="${pageContext.request.contextPath}/resources/chosenplugin/docsupport/prism.js"></script>
    
<%--     <!-- ================ FORM PLUGIN JQUERY ====================== -->
    <script src="${pageContext.request.contextPath}/resources/jquery/jquery.form.min.js"></script> --%>
	
</body>
</html>
