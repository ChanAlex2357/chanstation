package mg.station.chanstation.ejbService;

import avoir.AvoirFC;
import caisse.MvtCaisse;
import mg.station.chanstation.finance.StatutFinancier;
import mg.station.chanstation.prelevement.Avoir;
import mg.station.chanstation.prelevement.Encaissement;
import prelevement.PrelevementCpl;
import javax.ejb.Local;
import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.Date;

@Local
public interface EjbStation2 {
    public StatutFinancier getStatutFinancier(Date dateMin, Date dateMax, Connection connection) throws Exception;
    public StatutFinancier getStatutFinancier(Date dateMin, Date dateMax);
    public AvoirFC genererAvoir(Avoir avoir, Connection connection) throws Exception;
    public MvtCaisse mvtCaisse(Encaissement encaissement) throws Exception;
    public PrelevementCpl[] getPrelevements() throws NamingException, Exception;
}
