package mg.station.chanstation.annexe;

import bean.CGenUtil;
import mg.station.chanstation.bean.MaClassMAPTable;
import utilitaire.UtilDB;

import java.sql.Connection;
import java.sql.SQLException;

public class Pompe extends MaClassMAPTable {
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
    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("PMP", "GET_SEQ_POMPE");
        this.setId_pompe(makePK(c));
    }

    public Pompe() {
        this.setNomTable("POMPE");
    }
    public Cuve getCuve() throws Exception {
        return getCuve(null);
    }
    public Cuve getCuve(Connection connection) throws Exception {
        if (this.getId_cuve() == null) {
            return null;
        }
        return Cuve.getCuveById(getId_cuve(), connection);
    }

    public static Pompe getPompeById(String id) throws SQLException {
        Connection conn = new UtilDB().GetConn();
        try {
            return getPompeById(id, conn);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }
        return null;
    }

    public static Pompe getPompeById(String id, Connection conn) throws Exception {
        if (conn == null) {
            return getPompeById(id);
        }
        Pompe[] pompe = new Pompe[1];
        pompe[0] = new Pompe();
        pompe[0].setId_pompe(id);

        pompe = (Pompe[]) CGenUtil.rechercher(pompe[0], null, null, conn, "");
        if (pompe.length > 0) {
            return pompe[0];
        }
        return null;
    }

    @Override
    public MaClassMAPTable createObject(Connection localconn, Connection remoteconn) throws Exception {
        return this.createObject(remoteconn);
    }

}
