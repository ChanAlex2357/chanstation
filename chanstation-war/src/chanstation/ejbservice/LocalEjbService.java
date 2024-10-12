package chanstation.ejbservice;

import javax.naming.NamingException;

import mg.station.chanstation.data.DataGenService;
import mg.station.chanstation.ejbservices.PrelevementSignature;
import mg.station.chanstation.utils.EjbUtils;

public class LocalEjbService  {
    
    public static DataGenService getDataGenService(){
        try {
            return (DataGenService) EjbUtils.lookGlobalEjb(
                "chanstation",
                "DataGenBean",
                "mg.station.chanstation.data.DataGenService"
            );
        } catch (NamingException ne) {
            throw new RuntimeException(ne);
        }
    }

    public static PrelevementSignature getPrelevementSignature(){
        try {
            return (PrelevementSignature) EjbUtils.lookGlobalEjb(
                "chanstation",
                "PrelevementService",
                "mg.station.chanstation.ejbservices.PrelevementSignature"
            );
        } catch (NamingException ne) {
            throw new RuntimeException(ne);
        }
    }
}
