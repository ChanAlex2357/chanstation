package mg.station.chanstation.prelevement;

import bean.ClassMAPTable;
import java.sql.Connection;
import java.sql.Date;

public class PrelevementPompe extends ClassMAPTable {
    String id_prelevement_pompe;
    Date daty;
    Date heure;
    double compteur;
    String id_prelevement_anterieure;
    String id_pompiste;
    String id_pompe;

    public PrelevementPompe(){
        setNomTable("PRELEVEMENT_POMPE");
    }
    @Override
    public String getAttributIDName() {
        return "id_prelevement_pompe";
    }

    @Override
    public String getTuppleID() {
        return id_prelevement_pompe;
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("PRELPMP", "GET_SEQ_PRELEVEMENT_POMPE");
        String pk = this.makePK(c);
        this.setId_prelevement_pompe(pk);
    }

    public String getId_prelevement_pompe() {
        return id_prelevement_pompe;
    }

    public void setId_prelevement_pompe(String id_prelevement_pompe) {
        this.id_prelevement_pompe = id_prelevement_pompe;
    }

    public Date getDaty() {
        return daty;
    }

    public void setDaty(Date daty) {
        this.daty = daty;
    }

    public Date getHeure() {
        return heure;
    }

    public void setHeure(Date heure) {
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
