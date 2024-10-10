package mg.station.chanstation.stock;

import bean.ClassMAPTable;
import java.sql.Connection;
import java.sql.Date;

public class MvtStock extends ClassMAPTable {
    String id_mvt_stock;
    Date daty;
    double quantite;
    double pu;
    double montant;
    String id_type_mvt;
    String id_cuve;

    public MvtStock (){
        setNomTable("MVT_STOCK");
    }
    @Override
    public String getAttributIDName() {
        return "id_mvt_stock";
    }

    @Override
    public String getTuppleID() {
        return id_mvt_stock;
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("MVTS", "GET_SEQ_MVT_STOCK");
        String pk = this.makePK(c);
        this.setId_mvt_stock(pk);
    }

    // Getters and Setters
    public String getId_mvt_stock() {
        return id_mvt_stock;
    }

    public void setId_mvt_stock(String id_mvt_stock) {
        this.id_mvt_stock = id_mvt_stock;
    }

    public Date getDaty() {
        return daty;
    }

    public void setDaty(Date daty) {
        this.daty = daty;
    }

    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }

    public double getPu() {
        return pu;
    }

    public void setPu(double pu) {
        this.pu = pu;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public String getId_type_mvt() {
        return id_type_mvt;
    }

    public void setId_type_mvt(String id_type_mvt) {
        this.id_type_mvt = id_type_mvt;
    }

    public String getId_cuve() {
        return id_cuve;
    }

    public void setId_cuve(String id_cuve) {
        this.id_cuve = id_cuve;
    }
}
