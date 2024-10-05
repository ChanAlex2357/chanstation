package chanstation.servlet;

import chanstation.clientEJB.EjbServiceProvider;
import mg.station.chanstation.stock.achat.AchatArgs;
import mg.station.chanstation.stock.achat.AchatExecutor;
import mg.station.chanstation.stock.achat.AchatExecutorSignature;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet(name = "Achat",urlPatterns = {"/achat"})
public class AchatServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            AchatExecutorSignature achatExecutor = EjbServiceProvider.getAchatExecutor();
            AchatArgs achatArgs = new AchatArgs();
            achatArgs.setDaty(req.getParameter("daty"));
            achatArgs.setQte(req.getParameter("qte"));
            achatArgs.setId_cuve(req.getParameter("idCuve"));
            achatExecutor.achat(achatArgs);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
