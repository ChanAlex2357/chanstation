package mg.station.chanstation.annexe;

import bean.CGenUtil;
import bean.ClassMAPTable;
import mg.station.chanstation.ejb.ProduitService;
import utilitaire.UtilDB;

import java.sql.Connection;
import annexe.Produit;

public class Carburant extends ClassMAPTable {
    String id_carburant, nom, desce;
    double pu_vente, pu_achat;
    String id_type_carburant;

    public Carburant(String nom, String desce, double pu_vente, double pu_achat, String id_type_carburant) {
        setNomTable("CARBURANT");
        setNom(nom);
        setDesce(desce);
        setPu_vente(pu_vente);
        setPu_achat(pu_achat);
        setId_type_carburant(id_type_carburant);
    }
    @Override
    public String toString() {
        return "["+getId_carburant()+"]"+getNom()+" : "+getPu_vente()+" ; "+getPu_achat()+"<"+getId_type_carburant()+">";
    }
    public Carburant(){setNomTable("CARBURANT");}
    @Override
    public String getAttributIDName() {
        return "id_carburant";
    }

    @Override
    public String getTuppleID() {
        return id_carburant;
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("CARB", "GET_SEQ_CARBURANT");
        String pk = this.makePK(c);
        this.setId_carburant(pk);
    }

    public String getId_carburant() {
        return id_carburant;
    }

    public void setId_carburant(String id_carburant) {
        this.id_carburant = id_carburant;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDesce() {
        return desce;
    }

    public void setDesce(String desce) {
        this.desce = desce;
    }

    public double getPu_vente() {
        return pu_vente;
    }

    public void setPu_vente(double pu_vente) {
        this.pu_vente = pu_vente;
    }
    public void setPu_vente(String pu) throws Exception{
        if (pu.isEmpty()) {
            throw new Exception("Aucune prix de vente n'a ete indiquer");
        }
        double pu_vente = Double.parseDouble(pu);
        if (pu_vente < 0) {
            throw new Exception("Prix de vente negatif , veuillez entrez un montant valide >= 0");
        }
        setPu_vente(pu_vente);
    }

    public double getPu_achat() {
        return pu_achat;
    }

    public void setPu_achat(double pu_achat) {
        this.pu_achat = pu_achat;
    }
    public void setPu_achat(String pu) throws Exception{
        if (pu.isEmpty()) {
            throw new Exception("Aucune prix de achat n'a ete indiquer");
        }
        double pu_achat = Double.parseDouble(pu);
        if (pu_achat < 0) {
            throw new Exception("Prix de achat negatif , veuillez entrez un montant valide >= 0");
        }
        setPu_achat(pu_achat);
    }

    public String getId_type_carburant() {
        return id_type_carburant;
    }

    public void setId_type_carburant(String id_type_carburant) {
        this.id_type_carburant = id_type_carburant;
    }

    /**
     * Recuperer le produit representant du carburant dans la centrale
     * @return
     * @throws Exception
     */
    public Produit getProduitCorrespondante() throws Exception{
        return ProduitService.getProduitBdCarburant(this);
    }
    public Produit getProduitCorrespondante(Connection conn) throws Exception{
        if (conn == null) {
            return getProduitCorrespondante();    
        }
        return ProduitService.getProduitByCarburant(this, conn);
    }

    /**
     * Recupere l'instance du Type de carburant 
     * @param conn
     * @return
     * @throws Exception
     */
    public TypeCarburant getTypeCarburant(Connection conn) throws Exception{
        if (conn == null) {
            return getTypeCarburant();
        }
        TypeCarburant typeCarburant = new TypeCarburant();
        typeCarburant.setId_type_carburant(getId_type_carburant());

        return ((TypeCarburant[])CGenUtil.rechercher(typeCarburant,null,null,conn,""))[0];
    }
    public TypeCarburant getTypeCarburant() throws Exception{
        Connection conn = new UtilDB().GetConn();
        try {
            return getTypeCarburant(conn);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Aucun type carburant correspondant n'a ete trouver", e);
        }
        finally{
            conn.close();
        }
    }
}
