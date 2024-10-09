package mg.station.chanstation.data;

import java.sql.Connection;

import javax.ejb.Stateless;

import bean.CGenUtil;
import mg.station.chanstation.stock.TypeMvt;
import utilitaire.UtilDB;
@Stateless
public class DataGenBean implements DataGenService {

    @Override
    public void generateTypeMouvement( Connection conn) {
        if(conn == null){ conn = new UtilDB().GetConn(); }
        TypeMvt type1 = new TypeMvt(1 , "Entree");
        TypeMvt type2 = new TypeMvt(-1 , "Sortie");
        try {
            CGenUtil.save(type1, conn);
            CGenUtil.save(type2, conn);

            System.out.println("Types mouvement added succfully");
        } catch (Exception e) {
            System.out.println(
                "Erreur lors de la generation des types mouvements"
            );
            e.printStackTrace();
        }
    }
    
}
