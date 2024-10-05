package chanstation.servlet;

import clientEJB.EjbServiceProvider;
import itu.station.dataGenerator.DataGenerator;
import itu.station.dataGenerator.DataGeneratorAjbService;
import itu.station.dataGenerator.DataGeneratorEjbSignature;
import itu.station.prelevement.FactureClient;
import itu.station.prelevement.Prelevement;
import itu.station.prelevement.PrelevementService;
import itu.station.prelevement.PrelevementSignature;
import utilitaire.UtilDB;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "Prelevement",urlPatterns = {"/prelevement"})
public class PrelevementServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            PrelevementSignature prelevementSignature = EjbServiceProvider.getPrelevementEjbService();
            Prelevement prelevement = new Prelevement();

            // Set parameters from the request
            prelevement.setQte(req.getParameter("qte"));
            prelevement.setDaty(req.getParameter("daty"));
            prelevement.setHeure(req.getParameter("heure"));
            prelevement.setIdPompe(req.getParameter("idPompe"));
            prelevement.setIdUtilisateur(req.getParameter("idUtilisateur"));

            FactureClient facture = prelevementSignature.ciblerDeuxBases(prelevement, null);

            if (facture != null && facture.getMontant() != -1) {
                // Redirect if facture is valid
                req.getSession().setAttribute("facture", facture);
                resp.sendRedirect("index.jsp?but=pages/facture.jsp");
            } else {
                // Redirect to another page if facture is null or montant is -1
                resp.sendRedirect("index.jsp?but=pages/prelevement.jsp&val=success");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = null;
        try {
            conn = new UtilDB().GetConn();
            if(req.getParameter("idFacture")!=null){
                req.getSession().setAttribute("facture",new FactureClient(req.getParameter("idFacture"),conn));
                resp.sendRedirect("index.jsp?but=pages/facture.jsp");
            }
//            DataGenerator.generateData();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
