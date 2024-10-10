package chanstation.ejbservice;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import mg.station.chanstation.data.DataGenService;

public class LocalEjbService  {
    public static DataGenService getDataGenService(){
        try {
            Context c = new InitialContext();
            return (DataGenService) c.lookup("java:global/chanstation/DataGenBean!mg.station.chanstation.data.DataGenService");
        } catch (NamingException ne) {
            throw new RuntimeException(ne);
        }
    }
}
