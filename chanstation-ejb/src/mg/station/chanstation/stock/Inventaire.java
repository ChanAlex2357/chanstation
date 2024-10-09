package mg.station.chanstation.stock;

import bean.ClassMAPTable;
import java.sql.Connection;

public class Inventaire extends ClassMAPTable {
    String id_inventaire;
    double quantite;
    double cmup;
    double montant;
    String id_cuve;

    @Override
    public String getAttributIDName() {
        return "id_inventaire";
    }

    @Override
    public String getTuppleID() {
        return id_inventaire;
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("INVT", "GET_SEQ_INVENTAIRE");
        String pk = this.makePK(c);
        this.setId_inventaire(pk);
    }

    // Getters and Setters
    public String getId_inventaire() {
        return id_inventaire;
    }

    public void setId_inventaire(String id_inventaire) {
        this.id_inventaire = id_inventaire;
    }

    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }

    public double getCmup() {
        return cmup;
    }

    public void setCmup(double cmup) {
        this.cmup = cmup;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public String getId_cuve() {
        return id_cuve;
    }

    public void setId_cuve(String id_cuve) {
        this.id_cuve = id_cuve;
    }
}
