package chanstation.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import chanstation.ejbservice.LocalEjbService;
import mg.station.chanstation.data.DataGenService;

@WebServlet("/gen-data")
public class GenServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final DataGenService data_gen = LocalEjbService.getDataGenService();
        try {
            data_gen.generateTypeMouvement(null);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
