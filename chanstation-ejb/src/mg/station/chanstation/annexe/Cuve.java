package mg.station.chanstation.annexe;

import bean.CGenUtil;
import magasin.Magasin;
import magasin.MagasinReservoir;
import mg.station.chanstation.bean.MaClassMAPTable;
import mg.station.chanstation.ejbclient.CentralEJBClient;
import utilitaire.UtilDB;

import java.sql.Connection;
import java.sql.SQLException;
public class Cuve extends MaClassMAPTable {
    String id_cuve, nom;
    double capacite;
    String id_carburant;

    @Override
    public String toString() {
        return "["+getId_cuve()+";"+getNom()+";"+getCapacite()+";"+getId_carburant()+"]";
    }
    public Cuve(){setNomTable("CUVE");}
    @Override
    public String getAttributIDName() {
        return "id_cuve";
    }

    @Override
    public String getTuppleID() {
        return getId_cuve();
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

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("CV", "GET_SEQ_CUVE");
        this.setId_cuve(makePK(c));
    }


    public Carburant getCarburant(Connection c) throws Exception {
        if (this.getId_carburant() == null) {
            return null;
        }
        return Carburant.getCarburantById(this.getId_carburant(), c);
    }

    public static Cuve getCuveById(String id) throws SQLException {
        Connection conn = new UtilDB().GetConn();
        try {
            return getCuveById(id, conn);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }
        return null;
    }

    public static Cuve getCuveById(String id, Connection conn) throws Exception {
        if (conn == null) {
            return getCuveById(id);
        }
        Cuve[] cuve = new Cuve[1];
        cuve[0] = new Cuve();
        cuve[0].setId_cuve(id);

        cuve = (Cuve[]) CGenUtil.rechercher(cuve[0], null, null, conn, "");
        if (cuve.length > 0) {
            return cuve[0];
        }
        return null;
    }
    public Magasin genererMagasin(Connection conn) throws Exception{
        // Creation de l'instance du magasin
        Magasin magasin = new MagasinReservoir();
        magasin.setId(this.getId_carburant());
        magasin.setVal(this.getNom()+" de Chanstation");
        magasin.setDesce("Une cuve de carburant");
        // Persistance du magasin
        magasin = (Magasin)CentralEJBClient.getCentralEjb().createObject(magasin,conn);
        // Tester la valider de la persistence
        if (magasin == null) {
            throw new Exception("Impossible d'enregistrer le magasin dans la centrale");
        }
        return magasin;
    }
    public Magasin genererMagasin() throws Exception{
        Connection conn = new UtilDB().GetConn("gallois" , "gallois");
        try {
            return genererMagasin(conn);
        } catch (Exception e) {
            throw e;
        }
        finally {
            conn.close();
        }
    }

    @Override
    public MaClassMAPTable createObject(Connection localconn, Connection remoteconn) throws Exception {
        Cuve cuve = (Cuve)super.createObject(localconn);
        genererMagasin(remoteconn);
        return cuve;
    }
}