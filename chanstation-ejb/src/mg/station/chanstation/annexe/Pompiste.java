package mg.station.chanstation.annexe;

import bean.CGenUtil;
import bean.ClassMAPTable;
import utilitaire.UtilDB;

import java.sql.Connection;
import java.sql.SQLException;

public class Pompiste extends ClassMAPTable {
    String id_pompiste, nom;

    public Pompiste(){setNomTable("POMPISTE");}
    @Override
    public String getAttributIDName() {
        return "id_pompiste";
    }

    @Override
    public String getTuppleID() {
        return id_pompiste;
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("PMPST", "GET_SEQ_POMPISTE");
        String pk = this.makePK(c);
        this.setId_pompiste(pk);
    }

    public String getId_pompiste() {
        return id_pompiste;
    }

    public void setId_pompiste(String id_pompiste) {
        this.id_pompiste = id_pompiste;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public static Pompiste getPompisteById(String id) throws SQLException {
    Connection conn = new UtilDB().GetConn();
    try {
        return getPompisteById(id, conn);
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        conn.close();
    }
    return null;
}

public static Pompiste getPompisteById(String id, Connection conn) throws Exception {
    if (conn == null) {
        return getPompisteById(id);
    }
    Pompiste[] pompistes = new Pompiste[1];
    pompistes[0] = new Pompiste();
    pompistes[0].setId_pompiste(id);

    pompistes = (Pompiste[]) CGenUtil.rechercher(pompistes[0], null, null, conn, "");
    if (pompistes.length > 0) {
        return pompistes[0];
    }
    return null;
}

}
