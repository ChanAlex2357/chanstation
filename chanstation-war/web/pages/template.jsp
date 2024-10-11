<%
    String but      =   (String)request.getParameter("but");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title></title>
    <jsp:include page='elements/css.jsp'/>
</head>
<body>
    <header>
        <nav class="navbar">
            <div class="container-fluid">
                <a class="navbar-brand" href="${pageContext.request.contextPath}">ChanStation</a>
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a href="${pageContext.request.contextPath}/prelevementPompe" 
                            class="nav-link">
                            Prelevement Pompe
                        </a>
                    </li>
                </ul>
            </div>
        </nav>
    </header>
    <section class="container">
        <% try{ %>
            <jsp:include page='<%=but%>'/>
        <% } catch(Exception e){ out.println(e.getMessage()); } %>
    </section>
    

    <jsp:include page='elements/js.jsp'/>
    <script src="${pageContext.request.contextPath}/apjplugins/champcalcul.js" defer></script>      
    <script src="${pageContext.request.contextPath}/apjplugins/champdate.js" defer></script>      
    <script src="${pageContext.request.contextPath}/apjplugins/champautocomplete.js" defer></script>      
    <script src="${pageContext.request.contextPath}/apjplugins/addLine.js" defer></script>  
</body>
</html>