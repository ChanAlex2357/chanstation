<%@page import="mg.station.chanstation.annexe.Pompiste"%>
<%@page import="mg.station.chanstation.annexe.Pompe"%>
<%@page import="java.sql.*"%>
<%@page import="bean.*"%>
<%@page import="utilitaire.*"%>
<%
    Connection conn = new UtilDB().GetConn();
    Pompe[]     pompes      =   (Pompe[])CGenUtil.rechercher(new Pompe(),null,null,conn,"");
    Pompiste[]  pompistes   =   (Pompiste[])CGenUtil.rechercher(new Pompiste(),null, null , conn , "");
    conn.close();
%>
<div class="row">
    <div class="w-50">
        <form method="post" action="${pageContext.request.contextPath}/prelevementPompe" class="form">
            <div class="container">
                <!-- Compteur -->
                <div class="col-6 mb-3">
                    <label class="form-label" for="compteur_prelevement">Compteur</label>
                    <input class="form-control" type="number" name="compteur" id="compteur_prelevement">
                </div>
                
                <!-- Date de prelevement de pompe -->
                <div class="col-6 mb-3">
                    <label class="form-label" for="date_prelevement">Date prelevement</label>
                    <input class="form-control" type="date" name="daty" id="date_prelevement">
                </div>
                <!-- Heure de prelevement -->
                <div class="col-6 mb-3">
                    <label class="form-label" for="heure_prelevement">Heure prelevement</label>
                    <input class="form-control" type="text" name="heure" placeholder="HH:MM:SS" id="heure_prelevement">
                </div>
                <!-- Pompe -->
                <div class="col-6 mb-3">
                    <label class="form-label" for="pompe_prelevement">Pompe</label>
                    <select class="form-select" name="pompe" id="pompe_prelevement">
                        <%
                            for(int i = 0 ; i < pompes.length ; i++) {
                        %>
                            <option value="<%=pompes[i].getId_pompe()%>">
                                <%=pompes[i].getNom()%>
                            </option>
                        <%
                            }
                        %>
                </select>
                </div>
                <!-- Pompiste -->
                <div class="col-6 mb-3">
                    <label class="form-label" for="pompiste_prelevement">Pompiste</label>
                    <select class="form-select" name="pompiste" id="pompiste_prelevement">
                        <%
                            for(int i = 0 ; i < pompistes.length ; i++) {
                        %>
                            <option value="<%=pompistes[i].getId_pompiste()%>">
                                <%=pompistes[i].getNom()%>
                            </option>
                        <%
                        }
                        %>
                    </select>
                </div>
                <div class="col-6 mb-3">
                    <input class="btn btn-primary" type="submit" value="Prelever">
                </div>
            </div>
        </form>
    </div>
</div>