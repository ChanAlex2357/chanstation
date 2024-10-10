package mg.station.chanstation.data;

import java.sql.Connection;
import java.sql.SQLException;

import javax.ejb.Stateless;

import bean.CGenUtil;
import mg.station.chanstation.stock.TypeMvt;
import utilitaire.UtilDB;
@Stateless
public class DataGenBean implements DataGenService {

    @Override
    public void generateTypeMouvement( Connection conn) throws SQLException {
        if(conn == null){ 
            conn = new UtilDB().GetConn();
        }
        TypeMvt type1 = new TypeMvt(1 , "Entree");
        TypeMvt type2 = new TypeMvt(-1 , "Sortie");
        try {
            conn.setAutoCommit(false); 

            type1.construirePK(conn);
            type2.construirePK(conn);
            CGenUtil.save(type1, conn);
            CGenUtil.save(type2, conn);

            conn.commit();
            System.out.println("Types mouvement added succfully");
        } catch (Exception e) {
            conn.rollback();
            System.out.println(
                "Erreur lors de la generation des types mouvements"
            );
            e.printStackTrace();
        }
        finally {
            conn.close();
        }
    }
    
}
