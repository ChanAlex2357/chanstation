package mg.station.chanstation.prelevement;

import bean.ClassMAPTable;
import mg.station.chanstation.utils.TimeUtils;
import vente.Vente;

import java.sql.Connection;
import java.sql.Date;

public class PrelevementPompe extends ClassMAPTable {
    String id_prelevement_pompe;
    Date daty;
    String heure;
    double compteur;
    String id_prelevement_anterieure;
    String id_pompiste;
    String id_pompe;
    private void setNomTable(){
        this.setNomTable("PRELEVEMENT_POMPE");
    }
    public PrelevementPompe(){
        setNomTable();
    }
    public PrelevementPompe(String compteur , String daty , String heure , String pompe , String pompiste) throws Exception{
        setCompteur(compteur);
        setDaty(daty);
        setHeure(heure);
        setId_pompe(pompe);
        setId_pompiste(pompiste);
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

    public String getHeure() {
        return heure;
    }


    public void setHeure(String heure)throws Exception{
        if (heure.isEmpty() || heure == null){
            throw new Exception("Choisissez une heure");
        }
        this.heure = heure;
    }

    public double getCompteur() {
        return compteur;
    }

    public void setCompteur(double compteur) {
        this.compteur = compteur;
    }
    public void setCompteur(String compteur) throws Exception{ 
        if (compteur.isEmpty()){
            throw new Exception("Il faut indiquer le nombre de compteur");
        }
        this.setCompteur( Double.parseDouble(compteur) );
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

    public void setId_pompiste(String id_pompiste) throws Exception{
        if (id_pompiste.isEmpty() || id_pompiste == null){
            throw new Exception("Veuillez choisir un pompiste");
        }
        this.id_pompiste = id_pompiste;
    }

    public String getId_pompe() {
        return id_pompe;
    }

    public void setId_pompe(String id_pompe) throws Exception {
        if (id_pompe.isEmpty() || id_pompe == null){
            throw new Exception("Veuillez choisir une pompe");
        }
        this.id_pompe = id_pompe;
    }
    public void setDaty(String daty) throws Exception{
        if (daty.isEmpty() || daty == null){
            throw new Exception("Veuillez choisir une date");
        }
        this.setDaty(TimeUtils.convertToSqlDate(daty,"eng"));
    }

    public Vente getVente(){
        Vente vente = new Vente();
    }
}
