package mg.station.chanstation.prelevement_cuve;

import java.sql.Connection;

import javax.ejb.Local;

@Local
public interface PrelevementCuveSignature {
    public PrelevementCuve makePrelevementCuve(PrelevementCuveArgs args , Connection connection)throws Exception ; 
}
