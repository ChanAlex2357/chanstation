package chanstation.servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import chanstation.ejbservice.LocalEjbService;
import mg.station.chanstation.prelevement.PrelevementPompe;
@WebServlet(name = "Prelevement Pompe" , urlPatterns ="/prelevementPompe")
public class PrelevementPompeServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("pages/template.jsp?but=prelevement/prelevement-pompe-form.jsp&title=Prelevement Pompe");
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // Recuperation des donnees de formulaire
            String compteur =(req.getParameter("compteur"));
            String daty = (req.getParameter("daty"));
            String heure = (req.getParameter("heure"));
            String id_pompe = (req.getParameter("pompe"));
            String id_pompiste = (req.getParameter("pompiste"));
            // Creation de l'instance d'un prelevement de pompe 
            PrelevementPompe prelevement = new PrelevementPompe(compteur, daty, heure, id_pompe, id_pompiste);
            // Persistence du prelevement
            LocalEjbService.getPrelevementSignature().persistPrelevementPompe(prelevement);

            resp.sendRedirect("pages/template.jsp?but=prelevement/prelevement-pompe-form.jsp&title=Prelevement Pompe");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
