package mg.station.chanstation.data;

import java.sql.Connection;
import java.sql.SQLException;

import javax.ejb.Local;

@Local
public interface DataGenService {

    public void generateTypeMouvement( Connection conn) throws SQLException;
    public void generateUnite( Connection conn) throws SQLException;
    public void generateTypeCarburant( Connection conn) throws SQLException;
    public void generateCarburant( Connection conn) throws SQLException;
    public void generateCarburant( Connection conn) throws SQLException;
}