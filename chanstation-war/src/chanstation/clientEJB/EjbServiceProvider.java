package chanstation.clientEJB;

import mg.station.chanstation.dataGenerator.DataGeneratorEjbSignature;
import mg.station.chanstation.ejbService.EjbStation2;
import mg.station.chanstation.prelevement.PrelevementSignature;
import mg.station.chanstation.stock.achat.AchatExecutorSignature;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class EjbServiceProvider {
    public static DataGeneratorEjbSignature getMagasinEjbService() {
        try {
            Context c = new InitialContext();
            return (DataGeneratorEjbSignature) c.lookup("java:global/chanstation/DataGeneratorAjbService!mg.station.chanstation.dataGenerator.DataGeneratorEjbSignature");
        } catch (NamingException ne) {
//            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    public static PrelevementSignature getPrelevementEjbService() {
        try {
            Context c = new InitialContext();
            return (PrelevementSignature) c.lookup("java:global/chanstation/PrelevementService!mg.station.chanstation.prelevement.PrelevementSignature");
        } catch (NamingException ne) {
//            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    public static AchatExecutorSignature getAchatExecutor(){
        try {
            Context c = new InitialContext();
            return (AchatExecutorSignature) c.lookup("java:global/chanstation/AchatExecutor!mg.station.chanstation.stock.achat.AchatExecutorSignature");
        } catch (NamingException ne) {
//            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    public static EjbStation2 getEjbLocalServer(){
        try {
            Context c = new InitialContext();
            return (EjbStation2) c.lookup("java:global/chanstation/EjbStationService!mg.station.chanstation.ejbService.EjbStation2");
        } catch (NamingException ne) {
            throw new RuntimeException(ne);
        }
    }


}
