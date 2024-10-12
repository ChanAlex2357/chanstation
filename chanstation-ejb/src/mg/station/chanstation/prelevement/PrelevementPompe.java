package mg.station.chanstation.prelevement;

import bean.CGenUtil;
import mg.station.chanstation.bean.MaClassMAPTable;
import mg.station.chanstation.ejbclient.CentralEJBClient;
import mg.station.chanstation.annexe.Pompe;
import mg.station.chanstation.annexe.Pompiste;
import mg.station.chanstation.utils.TimeUtils;
import prelevement.Prelevement;
import utilitaire.UtilDB;
import vente.FactureCF;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;
public class PrelevementPompe extends MaClassMAPTable {
    String  id_prelevement_pompe;
    Date    daty;
    String  heure;
    double  compteur;
    String  id_prelevement_anterieure;
    String  id_pompiste;
    String  id_pompe;
    private void setNomTable(){
        this.setNomTable("PRELEVEMENT_POMPE");
    }
    public PrelevementPompe(){
        setNomTable();
    }
    public PrelevementPompe(String compteur , String daty , String heure , String pompe , String pompiste) throws Exception{
        setNomTable();
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
    protected void definePrelevementPompeAnterieure(Connection conn) throws Exception{
        // Recuperer la liste des prelevements correspondant au (pompe , pompiste) ordonnee par daty
        PrelevementPompe[] prelevements = new PrelevementPompe[1];
        prelevements  = (PrelevementPompe[])CGenUtil.rechercher(
            prelevements[0],
                "SELECT * " +
                "FROM (" +
                "    SELECT *" +
                "    FROM prelevement" +
                "    WHERE idPompe = '"+this.getId_pompe()+"'" +
                "    ORDER BY daty DESC, heure DESC" +
                ")" +
                " WHERE ROWNUM = 1"
            ,conn);
        if (prelevements.length > 0 ) {
            this.setId_prelevement_anterieure(prelevements[0].getId_prelevement_pompe());
        }
    }
    public boolean isVente(Connection c) throws Exception {
        return getPompeRowsById(c).length % 2 == 0 && getPompeRowsById(c).length != 0;
    }
    protected PrelevementPompe[] getPompeRowsById(Connection connection) throws Exception {
        PrelevementPompe[] prelevements = new PrelevementPompe[1];
        prelevements[0].setId_pompe( this.getId_pompe() );
        return (PrelevementPompe[]) CGenUtil.rechercher(new PrelevementPompe(), null,null,connection,"");
    }
    @Override
    public PrelevementPompe createObject(Connection c) throws Exception {
        // Recuperer l'id du prelevement anterieure
        this.definePrelevementPompeAnterieure(c);
        // Create l'objet dans la base 
        super.createObject(c);
        // Determiner vente ou non selon l'ordre dans la base
        // Verifier si c'est une vente
        if (this.isVente(c)) {
            // Generer une facture de vente
            // Realiser le mouvement de stock
            // Enregistrer la facture
            // Enregistrer le mouvement
        }
        return this;
    }
    @Override
    public PrelevementPompe createObject(Connection localconn, Connection remoteconn) throws Exception {
        // Persistence du prelevement local
        this.createObject(localconn);
        // Persistence du prelevement remote
        this.genererPrelevementRemote(remoteconn);
        return this;
    }
    /**
     * Cree et faire la persistance d'un prelevement de la centrale a partir du prelevement locale
     * @param remoteconn la connection a la base de donnee distante
     * @return le prelevement generer
     * @throws Exception 
     */
    public Prelevement genererPrelevementRemote(Connection remoteconn) throws Exception{
        Prelevement prelevement = this.getPrelevementRemote();
        return (Prelevement)CentralEJBClient.getCentralEjb().createObject(prelevement, remoteconn);
    }
    /**
     * Creation d'un prelevement de la centrale en prenant comme modele le prelevement locale
     * @return
     */
    public Prelevement getPrelevementRemote(){
        return PrelevementPompe.getPrelevementRemote(this);
    }
    private static Prelevement getPrelevementRemote(PrelevementPompe prelevement) {
        Prelevement prelevement1 = new prelevement.Prelevement();
        prelevement1.setId(prelevement.getId_prelevement_pompe());
        prelevement1.setCompteur(prelevement.getCompteur());
        prelevement1.setDaty(prelevement.getDaty());
        prelevement1.setDesignation("Prelevement du "+prelevement.getDaty().toString());
        prelevement1.setIdPompe(prelevement.getId_pompe());
        prelevement1.setIdPompiste(Integer.parseInt(prelevement.getId_pompiste()));
        prelevement1.setHeure(prelevement.getHeure());
        prelevement1.setIdPrelevementAnterieur(prelevement.getId_prelevement_anterieure());
        return prelevement1;
    }
}