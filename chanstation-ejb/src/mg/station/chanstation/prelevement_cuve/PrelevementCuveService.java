package mg.station.chanstation.prelevement_cuve;

import java.sql.Connection;

import bean.CGenUtil;
import utilitaire.UtilDB;

public class PrelevementCuveService implements PrelevementCuveSignature{

    @Override
    public PrelevementCuve makePrelevementCuve(PrelevementCuveArgs args , Connection conn) throws Exception {
        // Connexion a la base
        if (conn == null) {
            conn = new UtilDB().GetConn();
        }
        try {
            // Recuperation de la cuve et de ses limitations
            AdminLimitCuve adminLimitCuve = AdminLimitCuve.getAdminLimitCuve(args.getId_cuve(), conn);
            // Instanciation du prelevement
            PrelevementCuve prelevementCuve  = new PrelevementCuve(args);
            prelevementCuve.construirePK(conn);
            // Calcul de la quantite correspondant a a limite mesuree
            adminLimitCuve.interpolateQuantity(prelevementCuve);
            // Insertion dans la base de donnee
            CGenUtil.save(prelevementCuve);
            
        } catch (Exception e) {
            conn.rollback();
            e.printStackTrace();
        }
        finally{
            conn.close();
        }
        PrelevementCuve prelevementCuve = new PrelevementCuve(args);
        return prelevementCuve;
    }
    
}
 