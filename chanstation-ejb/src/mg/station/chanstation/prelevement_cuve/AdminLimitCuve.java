package mg.station.chanstation.prelevement_cuve;

import java.sql.Connection;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

import bean.CGenUtil;
import mg.station.chanstation.annexe.Cuve;
import utilitaire.UtilDB;

public class AdminLimitCuve {
    Cuve cuve ;
    LimitCuve[] limitCuves;

    public Cuve getCuve() {
        return cuve;
    }
    public void setCuve(Cuve cuve) {
        this.cuve = cuve;
    }
    public LimitCuve[] getLimitCuves() {
        return limitCuves;
    }
    public void setLimitCuves(LimitCuve[] limitCuves) {
        this.limitCuves = limitCuves;
    }

    public static AdminLimitCuve getAdminLimitCuve(String idCuve) throws Exception{
        Connection conn = new UtilDB().GetConn();
        try {
            return getAdminLimitCuve(idCuve, null);
        } catch (Exception e) {
            conn.rollback();
        } finally{
            conn.close();
        }
        return null;
    }
    public static AdminLimitCuve getAdminLimitCuve( String idCuve , Connection conn) throws Exception{   
        AdminLimitCuve adminLimitCuve = new AdminLimitCuve();     
        // Recuperation de la cuve 
        Cuve cuve = ((Cuve[]) CGenUtil.rechercher(new Cuve(),null,null,conn," and id_cuve='"+idCuve+"'"))[0];
        // Recuperation des limitations de la cuve
        LimitCuve[] limitCuves =(LimitCuve[]) CGenUtil.rechercher(new LimitCuve(),null , null , conn , null);
        adminLimitCuve.setCuve(cuve);
        adminLimitCuve.setLimitCuves(limitCuves);
        
        return adminLimitCuve;
    }

    // Méthode pour calculer la quantité par interpolation linéaire
    public void interpolateQuantity(PrelevementCuve prelevementCuve) throws Exception {
        double qte = interpolateQuantity(prelevementCuve.getLimit());
        prelevementCuve.setQte(qte);
    }
    public double interpolateQuantity(double height) throws Exception {
        // S'assurer qu'il y a au moins deux points pour l'interpolation
        if (limitCuves == null || limitCuves.length < 2) {
            throw new Exception("Pas assez de données pour effectuer une interpolation.");
        }

        // Parcourir la liste des limites et trouver les deux points entre lesquels se situe la hauteur
        for (int i = 0; i < limitCuves.length - 1; i++) {
            LimitCuve lower = limitCuves[i];
            LimitCuve upper = limitCuves[i + 1];

            // Si la hauteur demandée se situe entre lower.height et upper.height
            if (height >= lower.getLimit() && height <= upper.getLimit()) {
                // Appliquer la formule d'interpolation linéaire
                double interpolatedQuantity = lower.getQte() + 
                    ((height - lower.getLimit()) * (upper.getQte() - lower.getQte())) / 
                    (upper.getLimit() - lower.getLimit());
                
                return interpolatedQuantity;
            }
        }

        // Si la hauteur est en dehors des limites définies
        throw new Exception("Hauteur en dehors des limites de la cuve.");
    }
}
