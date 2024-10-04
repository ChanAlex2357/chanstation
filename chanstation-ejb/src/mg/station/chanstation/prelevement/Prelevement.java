package mg.station.chanstation.prelevement;

import bean.ClassMAPTable;
import java.sql.Connection;

public class Prelevement extends ClassMAPTable {
    String id_prelevement;
    java.sql.Date daty;
    java.sql.Time heure;
    double compteur;
    String id_prelevement_anterieure;
    String id_pompiste;
    String id_pompe;

    @Override
    public String getAttributIDName() {
        return "id_prelevement";
    }

    @Override
    public String getTuppleID() {
        return id_prelevement;
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("TC", "GET_SEQ_PRELEVEMENT");
        String pk = this.makePK(c);
        this.setId_prelevement(pk);
    }

    public String getId_prelevement() {
        return id_prelevement;
    }

    public void setId_prelevement(String id_prelevement) {
        this.id_prelevement = id_prelevement;
    }

    public java.sql.Date getDaty() {
        return daty;
    }

    public void setDaty(java.sql.Date daty) {
        this.daty = daty;
    }

    public java.sql.Time getHeure() {
        return heure;
    }

    public void setHeure(java.sql.Time heure) {
        this.heure = heure;
    }

    public double getCompteur() {
        return compteur;
    }

    public void setCompteur(double compteur) {
        this.compteur = compteur;
    }

    public String getId_prelevement_anterieure() {
        return id_prelevement_anterieure;
    }

    public void setId_prelevement_anterieure(String id_prelevement_anterieure) {
        this.id_prelevement_anterieure = id_prelevement_anterieure;
    }

    public String getId_pompiste() {
        return id_pompiste;
    }

    public void setId_pompiste(String id_pompiste) {
        this.id_pompiste = id_pompiste;
    }

    public String getId_pompe() {
        return id_pompe;
    }

    public void setId_pompe(String id_pompe) {
        this.id_pompe = id_pompe;
    }
}
