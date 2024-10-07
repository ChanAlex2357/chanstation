package mg.station.chanstation.prelevement;

import bean.CGenUtil;
import bean.ClassMAPTable;
import mg.station.chanstation.annexe.Pompe;
import mg.station.chanstation.localEjbClient.EjbClientGetter;
import mg.station.chanstation.utils.TimeUtils;
import utilitaire.UtilDB;
import vente.Vente;

import java.sql.Connection;
import java.sql.Date;

public class Prelevement extends ClassMAPTable {
    String id_prelevement;
    String id_prelevement_anterieure;
    String id_pompiste;
    String id_pompe;
    String heure;
    double compteur;
    Date daty;

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
        this.preparePk("PREL", "GET_SEQ_PRELEVEMENT");
        String pk = this.makePK(c);
        this.setId_prelevement(pk);
    }
    /* 
        *   Id Prelevement 
    */
    public String getId_prelevement() {
        return id_prelevement;
    }

    public void setId_prelevement(String id_prelevement) {
        this.id_prelevement = id_prelevement;
    }
    /*  
     * DATY
    */
    public Date getDaty() {
        return daty;
    }

    public void setDaty(Date daty) {
        this.daty = daty;
    }
    public void setDaty(String daty) throws Exception {
        if (daty.isEmpty() || daty == null){
            throw new Exception("Choisissez une date");
        }
        this.daty = TimeUtils.convertToSqlDate(daty,"eng");
    }
    /*
     * HEURE
     */
    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) throws Exception{
        if (heure.isEmpty() || heure == null){
            throw new Exception("Choisissez une heure");
        }

        this.heure = heure;
    }
    /*
     * COMPTEUR
     */
    public double getCompteur() {
        return compteur;
    }

    public void setCompteur(double compteur) {
        this.compteur = compteur;
    }
    public void setCompteur(String compteur) throws Exception{
        if (compteur.isEmpty()){
            throw new Exception("Il faut indique la quantite affiher sur le compteur");
        }
        this.setCompteur(Double.parseDouble(compteur));
    }
    /*
     * ID Prelevement Anterieure
     */
    public String getId_prelevement_anterieure() {
        return id_prelevement_anterieure;
    }

    public void setId_prelevement_anterieure(String id_prelevement_anterieure) {
        this.id_prelevement_anterieure = id_prelevement_anterieure;
    }
    /*
     * POMPISTE
     */
    public String getId_pompiste() {
        return id_pompiste;
    }

    public void setId_pompiste(String id_pompiste) throws Exception {
        if (id_pompiste.isEmpty() || id_pompiste == null){
            throw new Exception("Choisissez un pompiste");
        }        
        this.id_pompiste = id_pompiste;
    }
    /*
     * POMPE
     */
    public String getId_pompe() {
        return id_pompe;
    }

    public void setId_pompe(String id_pompe)throws Exception {
        if (id_pompe.isEmpty() || id_pompe == null){
            throw new Exception("Choisissez une pompe");
        }
        this.id_pompe = id_pompe;
    }


    public Prelevement getPrelevementAnterieur(String idPompe,Connection connection) throws Exception {
    //SELECT * FROM prelevement WHERE daty = (SELECT MAX(daty) FROM prelevemnt WHERE idPompe = ?)
    if (connection == null) connection = new UtilDB().GetConn();
    Prelevement ret = null;
    Prelevement[] list = (Prelevement[]) CGenUtil.rechercher(new Prelevement(),"SELECT * " +
            "FROM (" +
            "    SELECT *" +
            "    FROM prelevement" +
            "    WHERE id_pompe = '"+idPompe+"'" +
            "    ORDER BY daty DESC, heure DESC" +
            ")" +
            " WHERE ROWNUM = 1",connection);
    if (list.length > 0){
        ret = list[0];
    }
    return ret;
    }
    /**
     * Permet de determiner si le prelevement est un prelevement de debut ou de fin soit une vente
     * @param c La connexion pour la base de donnee
     * @return true si le prevelement est une vente false si c'est un debut de session
     * @throws Exception 
     */
    public boolean isVente(Connection c) throws Exception {
        Prelevement[] prelevements = getPompeRowsById(c); 
        return prelevements.length % 2 == 0 && prelevements.length != 0;
    }
    public Prelevement[] getPompeRowsById(Connection connection) throws Exception {
        if (connection == null) connection = new UtilDB().GetConn();
        return getPompeRowsById(this.getId_pompe(),connection);
    }
    public Prelevement[] getPompeRowsById(String idPompe,Connection connection) throws Exception {
        if (connection == null) connection = new UtilDB().GetConn();
        return (Prelevement[]) CGenUtil.rechercher(new Prelevement(),null,null,connection," and id_pompe='"+idPompe+"'");
    }
    public Pompe getPompe(Connection connection) throws Exception {
        if (connection == null) connection = new UtilDB().GetConn();
        return ((Pompe[]) CGenUtil.rechercher(new Pompe(),null,null,connection," and id_pompe='"+getId_pompe()+"'"))[0];
    }

    public Vente viser(Connection connection) throws Exception {
        if (connection == null) connection = new UtilDB("gallois","gallois").GetConn();
        GeneralEJBLocalServer generalEJBLocalServer = EjbClientGetter.getGeneralEjbService();
        return generalEJBLocalServer.getVenteByIdPrelevement(this.getId_prelevement(),connection);
    }
}
