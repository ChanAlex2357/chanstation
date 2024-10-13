package mg.station.chanstation.ejbclient;



import javax.naming.Context;
import javax.naming.InitialContext;

import ejbServer.CentralEjb;
import mg.station.chanstation.utils.EjbUtils;

public class CentralEJBClient {
    public static CentralEjb getCentralEjb() throws Exception{
        try {
            Context c = new InitialContext();
            String name = EjbUtils.getGlobalLookupName(
                "chanstation",
                "CentralEjbService",
                "ejbServer.CentralEjb"
            );
            return (CentralEjb)c.lookup(name);
        } catch (Exception e) {
            throw new Exception("Central EJB n'a pas pu etre instancier",e);
        }
    }
}
