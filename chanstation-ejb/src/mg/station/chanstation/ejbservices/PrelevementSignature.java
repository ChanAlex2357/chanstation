package mg.station.chanstation.ejbservices;

import java.sql.Connection;

import javax.ejb.Local;

import mg.station.chanstation.annexe.Carburant;
import mg.station.chanstation.annexe.Cuve;
import mg.station.chanstation.annexe.Pompe;
import mg.station.chanstation.prelevement.PrelevementCuve;
import mg.station.chanstation.prelevement.PrelevementPompe;

@Local
public interface PrelevementSignature {
    public void persistPrelevementPompe(PrelevementPompe prelevementPompe);
    public void persistPrelevementPompe(PrelevementPompe prelevementPompe,Connection conn);
    public void persistPrelevementCuve(PrelevementCuve prelevementCuve);
    public void persistPrelevementCuve(PrelevementCuve prelevementCuve , Connection conn);

    public void persistCuve(Cuve Cuve);
    public void persistCuve(Cuve Cuve , Connection conn);

    public void persistPompe(Pompe Pompte);
    public void persistPompe(Pompe Pompte , Connection conn);


    public void persistCarburant(Carburant Pompte);
    public void persistCarburant(Carburant Pompte , Connection conn);
}
