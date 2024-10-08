package mg.station.chanstation.stock;

import bean.ClassMAPTable;
import java.sql.Connection;

public class TypeMouvement extends ClassMAPTable {
    String id_type_mvt;
    String labelle;

    @Override
    public String getAttributIDName() {
        return "id_type_mvt";
    }

    @Override
    public String getTuppleID() {
        return id_type_mvt;
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("TC", "GET_SEQ_TYPE_MVT");
        String pk = this.makePK(c);
        this.setId_type_mvt(pk);
    }

    public String getId_type_mvt() {
        return id_type_mvt;
    }

    public void setId_type_mvt(String id_type_mvt) {
        this.id_type_mvt = id_type_mvt;
    }

    public String getLabelle() {
        return labelle;
    }

    public void setLabelle(String labelle) {
        this.labelle = labelle;
    }
}
