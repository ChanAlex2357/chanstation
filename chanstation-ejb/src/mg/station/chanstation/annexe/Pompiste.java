package mg.station.chanstation.annexe;

import bean.ClassMAPTable;
import java.sql.Connection;

public class Pompiste extends ClassMAPTable {
    String id_pompiste, nom;

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
        this.preparePk("TC", "GET_SEQ_POMPISTE");
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
}
