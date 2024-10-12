package mg.station.chanstation.ejbclient;

import javax.naming.InitialContext;

import ejbServer.CentralEjb;
import mg.station.chanstation.utils.EjbUtils;

public class CentralEJBClient {
    public static CentralEjb getCentralEjb(){
        try {
            InitialContext c = new InitialContext();
            String name = EjbUtils.getGlobalLookupName(
                "station",
                "CentralEJBService",
                "ejbServer.CentralEJBService"
            );
            return (CentralEjb)c.lookup( 
                name
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
