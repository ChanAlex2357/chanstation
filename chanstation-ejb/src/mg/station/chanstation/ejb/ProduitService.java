package mg.station.chanstation.ejb;

import java.sql.Connection;

import annexe.Produit;
import bean.CGenUtil;
import mg.station.chanstation.annexe.Carburant;

public class ProduitService {
    public static Produit getProduitBdCarburant( Carburant carburant) {
        Connection conn = new GalloisUtilDB().GetConn();
        Produit produit = null;
        try {
            conn.setAutoCommit(false);
            produit = getProduitByCarburant( carburant, conn);
            conn.commit();
        } catch (Exception e) {
            conn.rollback();
        }
        finally{
            conn.close();
        }
        return produit;
    }

    public static Produit getProduitByCarburant(Carburant carburant , Connection conn) throws Exception{
        Produit[] produits = ((Produit[])CGenUtil.rechercher( new Produit() , null , null , conn , " and id ='"+carburant.getId_carburant()+"'"));

        if (produits.length > 0) {
            return produits[0];
        }
        Produit produit = new Produit();
        produit.setId( carburant.getId_carburant());
        produit.setVal(carburant.getNom());
        produit.setDesce(carburant.getDesce());
        produit.setPuAchat( carburant.getPu_achat());
        produit.setPuVente(carburant.getPu_vente());
        return produit;
    }

    public static Produit[] 
}
