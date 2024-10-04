package mg.station.chanstation.annexe;

import bean.ClassMAPTable;
import java.sql.Connection;

public class Pompe extends ClassMAPTable {
    String id_pompe, nom;
    String id_cuve;

    @Override
    public String getAttributIDName() {
        return "id_pompe";
    }

    @Override
    public String getTuppleID() {
        return id_pompe;
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("TC", "GET_SEQ_POMPE");
        String pk = this.makePK(c);
        this.setId_pompe(pk);
    }

    public String getId_pompe() {
        return id_pompe;
    }

    public void setId_pompe(String id_pompe) {
        this.id_pompe = id_pompe;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getId_cuve() {
        return id_cuve;
    }

    public void setId_cuve(String id_cuve) {
        this.id_cuve = id_cuve;
    }
}
