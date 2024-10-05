package chanstation.servlet;

import avoir.AvoirFC;
import clientEJB.EjbServiceProvider;
import ejbServer.GeneralEJBLocalServer;
import itu.station.ejbService.EjbStation2;
import itu.station.localEjbClient.EjbClientGetter;
import itu.station.prelevement.Avoir;
import itu.station.prelevement.FactureClient;
import utilitaire.UtilDB;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "Avoir",urlPatterns = {"genererAvoir"})
public class AvoirServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String idClient = req.getParameter("idClient");
        String idProduit = req.getParameter("idProduit");
        String montant = req.getParameter("montant");
        Avoir avoir = new Avoir((FactureClient)req.getSession().getAttribute("facture"),idClient,idProduit,montant);
        Connection connection = null;
        try {
            connection = new UtilDB().GetConn("gallois","gallois");
            EjbStation2 generalEJBLocalServer = EjbServiceProvider.getEjbLocalServer();
            AvoirFC avoir1 = generalEJBLocalServer.genererAvoir(avoir,connection);
            req.getSession().setAttribute("avoirAViser",avoir1);
            resp.sendRedirect("index.jsp?but=pages/facture.jsp");
        } catch (Exception e) {
            out.println(e.getMessage());
            throw new RuntimeException(e);
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {

                throw new RuntimeException(e);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
