package mg.station.chanstation.data;

import java.sql.Connection;

import javax.ejb.Local;

@Local
public interface DataGenService {

    public void generateTypeMouvement( Connection conn);
    
}
