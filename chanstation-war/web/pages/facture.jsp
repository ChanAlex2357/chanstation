<%@page import="mg.station.chanstation.prelevement.*"%>
<%@page import="mg.station.chanstation.annexe.Pompe"%>
<%@page import="java.sql.*"%>
<%@page import="bean.*"%>
<%@page import="avoir.*"%>
<%@page import="utilitaire.*"%>

<%
    FactureClient fc = null;
    if(request.getParameter("idFacture")==null){
       fc = (FactureClient) session.getAttribute("facture"); // Retrieve from session
    }
    Connection conn = null;
    AvoirFC avoir = null;
    Avoir avoirCalc = new Avoir();
    if(session.getAttribute("avoirAViser") != null){
        avoir = (AvoirFC) session.getAttribute("avoirAViser");
    }
    try {
        conn = new UtilDB().GetConn();
        if(request.getParameter("idFacture")!=null){fc = new FactureClient(request.getParameter("idFacture"),conn);}
        if (fc != null) {
%>
<div>
    <h1>Facture</h1>
    <p>Id prelevement: <%= fc.getPrelevement().getId_prelevement() %></p>
    <% if(avoir!=null){ %>
        <p>Montant: <%= fc.getMontant() - avoirCalc.getSommeAvoirByOrigine(fc.getPrelevement().getId_prelevement(),null) %></p>
    <% } else { %>
        <p>Montant: <%= fc.getMontant() %></p>
    <% } %>
    <p>Heure: <%= fc.getPrelevement().getHeure() %></p>
    <p>Date: <%= fc.getPrelevement().getDaty().toString() %></p>
    <p>Quantité: <%= fc.getQteReleve() %></p>
    <p>Pompe: <%= fc.getPrelevement().getPompe(conn).getNom() %></p>

    <a href="${pageContext.request.contextPath}/index.jsp?but=pages/saisie-avoir.jsp">Générer avoir</a>
    <a href="${pageContext.request.contextPath}/index.jsp?but=pages/encaissement.jsp">Encaisser</a>
</div>
<%
        } else {
%>
    <p>No facture available.</p>
<%
        }
    } catch (Exception e) {
%>
        <script>alert('<%= e.getMessage() %>')</script>
<%
    } finally {
        if (conn != null) {
            conn.close();
        }
    }
%>
<%
    if(avoir != null){
        out.println("<p>"+avoir.getId()+"</p>");
        out.println("<p>Montant HT"+avoir.getAvoirDetails()[0].getQte()*avoir.getAvoirDetails()[0].getPu()+"</p>");
        out.println("<p>"+avoir.getDesignation()+"</p>");
        out.println("<p>"+avoir.getDaty()+"</p>");
    }

%>
