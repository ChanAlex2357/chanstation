package mg.station.chanstation.data;

import java.sql.Connection;

import javax.ejb.Local;

import mg.station.chanstation.annexe.Carburant;
import mg.station.chanstation.annexe.Cuve;
import mg.station.chanstation.annexe.Equivalence;
import mg.station.chanstation.annexe.Pompe;
import mg.station.chanstation.annexe.Pompiste;
import mg.station.chanstation.annexe.TypeCarburant;
import mg.station.chanstation.annexe.Unite;
import mg.station.chanstation.stock.TypeMvt;

@Local
public interface DataGenService {

    public TypeMvt[] generateTypeMouvement( Connection conn) throws Exception;
    public Unite[] generateUnite( Connection conn) throws Exception;
    public TypeCarburant[] generateTypeCarburant(Unite unite , Connection conn) throws Exception;
    public Carburant[] generateCarburant( TypeCarburant typeCarburant,Connection conn) throws Exception;
    public Cuve[] generateCuve(Carburant[] carburant , Connection conn)throws Exception;
    public Pompe[] generatePompe(Cuve[] cuve, Connection conn) throws Exception;
    public Pompiste[] generatePompiste(Connection conn) throws Exception;
    public Equivalence[] generateEquivalence(Cuve cuve[] , Connection conn) throws Exception;
    public void generateLocalData( Connection conn) throws Exception;

}