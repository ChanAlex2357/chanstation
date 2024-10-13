package mg.station.chanstation.annexe;

import java.sql.Connection;
import mg.station.chanstation.bean.MaClassMAPTable;

public class Equivalence extends MaClassMAPTable {
    private String id_equivalence;
    private double limit;
    private double qte;
    private String id_cuve;

    public Equivalence(){setNomTable("EQUIVALENCE");}

    @Override
    public String getAttributIDName() {
        return "id_equivalence";
    }

    @Override
    public String getTuppleID() {
        return id_equivalence;
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("EQ", "GET_SEQ_EQUIVALENCE");
        String pk = this.makePK(c);
        this.setId_equivalence(pk);
    }

    // Getters and Setters
    public String getId_equivalence() {
        return id_equivalence;
    }

    public void setId_equivalence(String id_equivalence) {
        this.id_equivalence = id_equivalence;
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

    public Cuve getCuve() throws Exception {
        return getCuve(null);
    }
    public Cuve getCuve(Connection connection) throws Exception {
        if (this.getId_cuve() == null) {
            return null;
        }
        return Cuve.getCuveById(getId_cuve(), connection);
    }

    @Override
    public MaClassMAPTable createObject(Connection localconn, Connection remoteconn) throws Exception {
        return this.createObject(localconn);
    }
}
