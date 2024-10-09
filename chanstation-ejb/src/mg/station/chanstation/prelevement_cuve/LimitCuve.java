package mg.station.chanstation.prelevement_cuve;

import bean.ClassMAPTable;
import java.sql.Connection;

public class LimitCuve extends ClassMAPTable {
    String id_limit_cuve;
    double limit;
    double qte;
    String id_cuve;

    @Override
    public String getAttributIDName() {
        return "id_limit_cuve";
    }

    @Override
    public String getTuppleID() {
        return id_limit_cuve;
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("LMTCV", "GET_SEQ_LIMIT_CUVE");
        String pk = this.makePK(c);
        this.setId_limit_cuve(pk);
    }
    

    public String getId_limit_cuve() {
        return id_limit_cuve;
    }

    public void setId_limit_cuve(String id_limit_cuve) {
        this.id_limit_cuve = id_limit_cuve;
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
}
