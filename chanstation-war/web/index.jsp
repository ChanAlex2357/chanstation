<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <jsp:include page='pages/elements/css.jsp'/>
</head>
<body>
    <h3> PAGE CONTEXT : ${pageContext.request.contextPath}</h3>
    <a class="btn btn-secondary" href="${pageContext.request.contextPath}/gen-data">
        Generer les donnees
    </a>
    <a href="${pageContext.request.contextPath}/prelevementPompe" class="btn bin-secondary">Prelevement Pompe</a>

    <h1>WELCOME TO THE CHANSTATION</h1>

    <jsp:include page='pages/elements/js.jsp'/>
    <script src="${pageContext.request.contextPath}/apjplugins/champcalcul.js" defer></script>      
    <script src="${pageContext.request.contextPath}/apjplugins/champdate.js" defer></script>      
    <script src="${pageContext.request.contextPath}/apjplugins/champautocomplete.js" defer></script>      
    <script src="${pageContext.request.contextPath}/apjplugins/addLine.js" defer></script>  
</body>
</html>