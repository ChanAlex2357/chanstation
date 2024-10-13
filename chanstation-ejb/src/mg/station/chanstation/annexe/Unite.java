package mg.station.chanstation.annexe;

import java.sql.Connection;
import java.sql.SQLException;

import bean.CGenUtil;
import ejbServer.CentralEjb;
import mg.station.chanstation.bean.MaClassMAPTable;
import mg.station.chanstation.ejbclient.CentralEJBClient;
import utilitaire.UtilDB;

public class Unite extends MaClassMAPTable{
    String id_unite;
    String val;
    String desce;

    public Unite(){
        setNomTable("UNITE");
    }
    @Override
    public String getAttributIDName() {
        return "id_unite";
    }
    @Override
    public String getTuppleID() {
        return this.getId_unite();
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        preparePk("UNICARB", "GET_SEQ_UNITE");
        setId_unite(makePK());
    }
    public String getId_unite() {
        return id_unite;
    }
    public void setId_unite(String id_unite) {
        this.id_unite = id_unite;
    }
    public String getVal() {
        return val;
    }
    public void setVal(String val) {
        this.val = val;
    }
    public String getDesce() {
        return desce;
    }
    public void setDesce(String desce) {
        this.desce = desce;
    }
    public static Unite getUniteById(String id) throws SQLException {
    Connection conn = new UtilDB().GetConn();
    try {
        return getUniteById(id, conn);
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        conn.close();
    }
    return null;
}

public static Unite getUniteById(String id, Connection conn) throws Exception {
    if (conn == null) {
        return getUniteById(id);
    }
    Unite[] unites = new Unite[1];
    unites[0] = new Unite();
    unites[0].setId_unite(id);

    unites = (Unite[]) CGenUtil.rechercher(unites[0], null, null, conn, "");
    if (unites.length > 0) {
        return unites[0];
    }
    return null;
}
public annexe.Unite genererUnite(Connection conn) throws Exception{
        // Creation de l'instance du Unite
        annexe.Unite unite = new annexe.Unite();
        unite.setId(this.getId_unite());
        unite.setVal(this.getVal());
        unite.setDesce(this.getDesce());
        // Persistance du Unite
        CentralEjb centralEjb = CentralEJBClient.getCentralEjb();
        unite = (annexe.Unite)centralEjb.createObject(unite,conn);
        // Tester la valider de la persistence
        if (unite == null) {
            throw new Exception("Impossible d'enregistrer le unite dans la centrale");
        }
        return unite;
    }
    public annexe.Unite genererUnite() throws Exception{
        Connection conn = new UtilDB().GetConn("gallois" , "gallois");
        try {
            return genererUnite(conn);
        } catch (Exception e) {
            throw e;
        }
        finally {
            conn.close();
        }
    }

@Override
public MaClassMAPTable createObject(Connection localconn, Connection remoteconn) throws Exception {
    this.createObject(localconn);
    this.genererUnite(remoteconn);
    return this;
}
}   
