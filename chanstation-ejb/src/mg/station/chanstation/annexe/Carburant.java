package mg.station.chanstation.annexe;

import bean.ClassMAPTable;
import java.sql.Connection;

public class Carburant extends ClassMAPTable {
    String id_carburant, nom, desce;
    double pu_vente, pu_achat;
    String id_type_carburant;

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
        this.preparePk("TC", "GET_SEQ_CARBURANT");
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

    public double getPu_achat() {
        return pu_achat;
    }

    public void setPu_achat(double pu_achat) {
        this.pu_achat = pu_achat;
    }

    public String getId_type_carburant() {
        return id_type_carburant;
    }

    public void setId_type_carburant(String id_type_carburant) {
        this.id_type_carburant = id_type_carburant;
    }
}
