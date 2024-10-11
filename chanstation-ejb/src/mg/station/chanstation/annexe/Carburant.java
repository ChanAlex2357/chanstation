package mg.station.chanstation.annexe;

import bean.CGenUtil;
import bean.ClassMAPTable;
import mg.station.chanstation.data.ChanstationConstante;
import mg.station.chanstation.ejb.GalloisUtilDB;
import mg.station.chanstation.ejb.ProduitService;
import utilitaire.UtilDB;

import java.sql.Connection;
import java.sql.SQLException;

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
        if (id_type_carburant == null) {
            id_type_carburant = ChanstationConstante.getIdTypeCarburantEssence();
        }
        this.id_type_carburant = id_type_carburant;
    }
    public static Carburant getCarburantById(String id) throws SQLException {
        Connection conn = new UtilDB().GetConn();
        try {
            return getCarburantById(id, conn);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }
        return null;
    }
    
    public static Carburant getCarburantById(String id, Connection conn) throws Exception {
        if (conn == null) {
            return getCarburantById(id);
        }
        Carburant[] carburants = new Carburant[1];
        carburants[0] = new Carburant();
        carburants[0].setId_carburant(id);
    
        carburants = (Carburant[]) CGenUtil.rechercher(carburants[0], null, null, conn, "");
        if (carburants.length > 0) {
            return carburants[0];
        }
        return null;
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
        return TypeCarburant.getTypeCarburantById(this.getId_type_carburant(), conn);
    }
    public TypeCarburant getTypeCarburant() throws Exception{
        return getTypeCarburant(null);
    }
    /**
     * Enregistre l'instance du carburant dans la base de donner et son correspondance dans la centrale
     * @throws SQLException
     */
    public void viser() throws SQLException{
        Connection conn = new UtilDB().GetConn();
        Connection gallois = new GalloisUtilDB().GetConn();
        try {
            conn.setAutoCommit(false);
            gallois.setAutoCommit(false);
            viser(conn, gallois);
            conn.commit();
            gallois.commit();
        } catch (Exception e) {
            conn.rollback();
            gallois.rollback();
            e.printStackTrace();
        }
        finally {
            conn.close();
            gallois.close();
        }
    }
    public void viser(Connection conn , Connection gallois) throws Exception {
        // Ne faire aucune action l'une des deux connexion est vide
        if (conn == null || gallois == null) {
            viser();
        }
        // Verification de l'existance de l'id du carburant
        if (getId_carburant() == null) {
            this.construirePK(conn);
        }
        // Cree le produit a partir du carburant
        Produit produit = ProduitService.createProduitByCarburant(this);
        // Enregistrer le carburant dans la base perso
        CGenUtil.save(this, conn);
        // Enregisrer le produit correspondant
        produit.createObject("1060", gallois);
    }
}
