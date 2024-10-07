package mg.station.chanstation.localEjbClient;

import ejbServer.GeneralEJBLocalServer;
import magasin.MagasinLocalEJB;
import prelevement.PrelevementEJB;
import prelevement.PrelevementLocal;
import user.UserEJB;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class EjbClientGetter {
    public static PrelevementLocal getPrelevementEjbService() {
        try {
            Context c = new InitialContext();
            return (PrelevementLocal) c.lookup("java:global/chanstation/PrelevementEJB!prelevement.PrelevementLocal");
        } catch (NamingException ne) {
//            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    public static MagasinLocalEJB getMagasinEjbService() {
        try {
            Context c = new InitialContext();
            return (MagasinLocalEJB) c.lookup("java:module/MagasinEJBService!mg.station.chanstation.magasin.MagasinLocalEJB");
        } catch (NamingException ne) {
//            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    public static GeneralEJBLocalServer getGeneralEjbService() throws NamingException {
        try {
            Context c = new InitialContext();
            return (GeneralEJBLocalServer) c.lookup("java:global/chanstation/GeneralEJBService!ejbServer.GeneralEJBLocalServer");
        }catch (Exception e){
            throw e;
        }
    }
}
