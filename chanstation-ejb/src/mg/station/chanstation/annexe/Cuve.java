package mg.station.chanstation.annexe;

import bean.ClassMAPTable;
import java.sql.Connection;

public class Cuve extends ClassMAPTable {
    String id_cuve, nom;
    double capacite;
    String id_carburant;

    @Override
    public String getAttributIDName() {
        return "id_cuve";
    }

    @Override
    public String getTuppleID() {
        return id_cuve;
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("TC", "GET_SEQ_CUVE");
        String pk = this.makePK(c);
        this.setId_cuve(pk);
    }

    public String getId_cuve() {
        return id_cuve;
    }

    public void setId_cuve(String id_cuve) {
        this.id_cuve = id_cuve;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getCapacite() {
        return capacite;
    }

    public void setCapacite(double capacite) {
        this.capacite = capacite;
    }

    public String getId_carburant() {
        return id_carburant;
    }

    public void setId_carburant(String id_carburant) {
        this.id_carburant = id_carburant;
    }
}
