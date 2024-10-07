<%@page import="mg.station.chanstation.prelevement.*"%>
<%@page import="mg.station.chanstation.annexe.*"%>
<%@page import="java.sql.*"%>
<%@page import="bean.*"%>
<%@page import="utilitaire.*"%>
<%@page import="vente.*"%>
<%
    Connection connection = new UtilDB("gallois","gallois").GetConn();
    FactureClient fc = (FactureClient) session.getAttribute("facture");
    VenteDetails[] venteDetails = fc.getPrelevement().viser(connection).getVenteDetailsNonGrp(connection);
%>
<form action="${pageContext.request.contextPath}/genererAvoir" method="post">
    <input type="text" name="idClient" value="">
    <input type="text" name="idProduit" value="<%=venteDetails[0].getIdProduit()%>" />
    <input type="number" name="montant" />
    <input type="submit" />
</form>