<!doctype html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <title>GeAnaFront</title>
  <base href="/">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="icon" type="image/x-icon" href="favicon.ico">
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
</head>
<body>
<header>
 <nav>
  <ul>
   <li><a href="${pageContext.request.contextPath}/index.jsp?but=pages/prelevement.jsp">Prelevement</a></li>
   <li><a href="${pageContext.request.contextPath}/index.jsp?but=pages/bilan-form.jsp">Bilan formulaire</a></li>
   <li><a href="${pageContext.request.contextPath}/index.jsp?but=pages/bilan-result.jsp">Bilan resultat</a></li>
   <li><a href="${pageContext.request.contextPath}/index.jsp?but=pages/achat.jsp">Achat</a></li>
  </ul>
 </nav>
</header>
<section>
    <% try{ %>
     <jsp:include page='<%=request.getParameter("but")%>'/>
    <% } catch(Exception e){ out.println(e.getMessage()); } %>
</section>
</body>
</html>