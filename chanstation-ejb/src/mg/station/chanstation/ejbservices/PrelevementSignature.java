package mg.station.chanstation.ejbservices;
import javax.ejb.Local;

import mg.station.chanstation.prelevement.PrelevementCuve;
import mg.station.chanstation.prelevement.PrelevementPompe;

@Local
public interface PrelevementSignature {
    public void persistPrelevementPompe(PrelevementPompe prelevementPompe) throws Exception;
    public void persistPrelevementCuve(PrelevementCuve prelevementCuve)throws Exception; 
}
