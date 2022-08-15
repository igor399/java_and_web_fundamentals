
<html>
<head>
	<title>Result page</title>
</head>
<body>
    <p>${operation} (
	<c:forEach items="${stats}" var="stat" varStatus="status">
		${stat}
		<c:if test = "${not status.last}">
			,
		</c:if>
	</c:forEach>
	) is ${result}</p>
    <br/>
     <a href="<c:url value='/'/>">Main</a>
</body>
</html>
