package mg.station.chanstation.ejbservices;

import java.sql.Connection;

import javax.ejb.Stateless;

import mg.station.chanstation.prelevement.PrelevementCuve;
import mg.station.chanstation.prelevement.PrelevementPompe;
import utilitaire.UtilDB;

@Stateless
public class PrelevementService implements PrelevementSignature{
    @Override
    public void persistPrelevementPompe(PrelevementPompe prelevementPompe) throws Exception {
        Connection chanstationConn = new UtilDB().GetConn(); 
        Connection galloisConn = new UtilDB().GetConn("gallois","gallois");
        try {
            chanstationConn.setAutoCommit(false);
            galloisConn.setAutoCommit(false);
            
            prelevementPompe.createObject(chanstationConn,galloisConn);

            chanstationConn.commit();
            galloisConn.commit();
        } catch (Exception e) {
            chanstationConn.rollback();
            galloisConn.rollback();


            throw e;
        }
        finally {
            chanstationConn.close();
            galloisConn.close();
        }
    }

    @Override
    public void persistPrelevementCuve(PrelevementCuve prelevementCuve) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'persistPrelevementCuve'");
    }


    
    
}
