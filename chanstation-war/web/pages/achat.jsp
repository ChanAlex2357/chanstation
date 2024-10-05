<%@page import="mg.station.chanstation.annexe.Cuve"%>
<%@page import="java.sql.*"%>
<%@page import="bean.*"%>
<%@page import="utilitaire.*"%>
<%
    Connection connection = new UtilDB().GetConn();
    Cuve[] cuves = (Cuve[]) CGenUtil.rechercher(new Cuve(),null,null,connection," ");
    connection.close();
%>
<form action="${pageContext.request.contextPath}/achat" method="post">
    <label>Quantité</label>
    <div>
        <input type="number" name="qte">
    </div>
    <div>
    <label>Cuve</label>
        <select name="idCuve" id="Pompiste">
            <%
                for(int i = 0 ; i < cuves.length ; i++) {
            %>
                <option value="<%=cuves[i].getId_cuve()%>">
                    <%=cuves[i].getNom()%>
                </option>
            <%
               }
            %>
        </select>
    </div>

    <div>
        <label>Date</label>
        <input type="date" name="daty" >
    </div>
    <div>
        <input type="submit">
    </div>
</form>