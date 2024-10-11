package mg.station.chanstation.prelevement;

import bean.CGenUtil;
import bean.ClassMAPTable;
import mg.station.chanstation.annexe.Pompe;
import mg.station.chanstation.annexe.Pompiste;
import mg.station.chanstation.utils.TimeUtils;
import utilitaire.UtilDB;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
public class PrelevementPompe extends ClassMAPTable {
    String id_prelevement_pompe;
    Date daty;
    String heure;
    double compteur;
    String id_prelevement_anterieure;
    String id_pompiste;
    String id_pompe;
    int     estvente;
    
    private void setNomTable(){
        this.setNomTable("PRELEVEMENT_POMPE");
    }
    public PrelevementPompe(){
        setNomTable();
        setEstvente(0);
    }
    public PrelevementPompe(String compteur , String daty , String heure , String pompe , String pompiste) throws Exception{
        setNomTable();
        setCompteur(compteur);
        setDaty(daty);
        setHeure(heure);
        setId_pompe(pompe);
        setId_pompiste(pompiste);
        setEstvente(0);
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
    public int getEstvente() {
        return estvente;
    }
    public void setEstvente(int estvente) {
        this.estvente = estvente;
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
    public Pompe getPompe() throws Exception{
        return this.getPompe(null);
    }
    public Pompe getPompe( Connection conn) throws Exception{
        if (this.getId_pompe() == null) {
            return null;
        }
        return Pompe.getPompeById(id_pompe, conn);
    }

    public Pompiste getPompiste() throws Exception{
        return this.getPompiste(null);
    }
    public Pompiste getPompiste( Connection conn) throws Exception{
        if (this.getId_pompiste() == null) {
            return null;
        }
        return Pompiste.getPompisteById(id_pompe, conn);
    }

    public static PrelevementPompe getPrelevementById(String id) throws SQLException{
        PrelevementPompe prelevement = null;
        Connection conn = new UtilDB().GetConn();
        try {
            prelevement = getPrelevementById(id,conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            conn.close();
        }
        return prelevement;
    }
    public static PrelevementPompe getPrelevementById(String id ,Connection conn) throws Exception{
        if (conn == null) {
            return getPrelevementById(id);
        }
        PrelevementPompe[] prelevements = new PrelevementPompe[1];
        prelevements[0] = new PrelevementPompe();
        prelevements[0].setId_prelevement_pompe(id);
        prelevements = ((PrelevementPompe[]) CGenUtil.rechercher(prelevements[0] , null , null , conn , ""));
        if (prelevements.length > 0) {
            return prelevements[0];
        }
        return null;
    }
    /**
     * Recuperer l'instance du prelevement anterieure correspondant a l'id prelevement anterieure
     * @return
     * @throws SQLException
     */
    public PrelevementPompe getPrelevementPompeAnterieure() throws SQLException{
        PrelevementPompe prelevementanterieure = null;
        Connection conn = new UtilDB().GetConn();
        try {
            prelevementanterieure = getPrelevementPompeAnterieure(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            conn.close();
        }
        return prelevementanterieure;
    }
    public PrelevementPompe getPrelevementPompeAnterieure(Connection conn) throws Exception{
        if (this.getId_prelevement_anterieure() == null) {
            return null;
        }
        // Creation de la reference pour la recherche dans la base
        PrelevementPompe prelevementref = new PrelevementPompe();
        prelevementref.setId_prelevement_anterieure(this.getId_prelevement_anterieure());
        // Rechercher une correspondance
        PrelevementPompe[] prelevementanterieur = ((PrelevementPompe[]) CGenUtil.rechercher(prelevementref , null , null , conn , ""));
        if (prelevementanterieur.length > 0) {
            return prelevementanterieur[0];
        }
        return null;
    }
    protected PrelevementPompe definePrelevementPompeAnterieure(){
        // Recuperer la liste des prelevements correspondant au (pompe , pompise) ordonnee par daty
        // Si c'est paire -> pas vente
        // Si la taille du tableau est impaire -> Vente
            // recuprer le premier prelevement de l'ordre du tableau
            // definire la liaison entre le prelevement actuel et l'anterieure
            // changer le status est vente
            this.setEstvente(1);
        return null;
    }
    public void viser( Connection conn){
        // Recuperer l'id du prelevement anterieure
        // Verifier si c'est une vente
        // Enregistrer dans la base de donnee
            // Construire la primary key si elle n'existe pas encore
        // Si c'est une vente
            // Generer une facture de vente
            // Realiser le mouvement de stock
            // Enregistrer la facture
            // Enregistrer le mouvement
    }
}
