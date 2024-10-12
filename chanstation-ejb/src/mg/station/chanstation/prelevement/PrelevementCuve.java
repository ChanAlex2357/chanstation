package mg.station.chanstation.prelevement;

import mg.station.chanstation.bean.MaClassMAPTable;
import java.sql.Connection;
import java.sql.Date;

public class PrelevementCuve extends MaClassMAPTable {
    String id_prelevement_cuve;
    Date daty;
    double limit;
    double qte;
    String id_cuve;

    public PrelevementCuve(){
        setNomTable("PRELEVEMENT_CUVE");
    }

    @Override
    public String getAttributIDName() {
        return "id_prelevement_cuve";
    }

    @Override
    public String getTuppleID() {
        return id_prelevement_cuve;
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("PRELCV", "GET_SEQ_PRELEVEMENT_CUVE");
        String pk = this.makePK(c);
        this.setId_prelevement_cuve(pk);
    }

    // Getters and Setters
    public String getId_prelevement_cuve() {
        return id_prelevement_cuve;
    }

    public void setId_prelevement_cuve(String id_prelevement_cuve) {
        this.id_prelevement_cuve = id_prelevement_cuve;
    }

    public Date getDaty() {
        return daty;
    }

    public void setDaty(Date daty) {
        this.daty = daty;
    }

    public double getLimit() {
        return limit;
    }

    public void setLimit(double limit) {
        this.limit = limit;
    }

    public double getQte() {
        return qte;
    }

    public void setQte(double qte) {
        this.qte = qte;
    }

    public String getId_cuve() {
        return id_cuve;
    }

    public void setId_cuve(String id_cuve) {
        this.id_cuve = id_cuve;
    }

    @Override
    public MaClassMAPTable createObject(Connection localconn, Connection remoteconn) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createObject'");
    }
}
