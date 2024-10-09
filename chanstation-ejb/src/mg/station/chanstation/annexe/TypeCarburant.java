package mg.station.chanstation.annexe;

import bean.ClassMAPTable;
import java.sql.Connection;

public class TypeCarburant extends ClassMAPTable {
    String id_type_carburant;
    String libelle;
    String dsce;

    @Override
    public String getAttributIDName() {
        return "id_type_carburant";
    }

    @Override
    public String getTuppleID() {
        return id_type_carburant;
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("TC", "GET_SEQ_TYPE_CARBURANT");
        String pk = this.makePK(c);
        this.setId_type_carburant(pk);
    }

    public String getId_type_carburant() {
        return id_type_carburant;
    }

    public void setId_type_carburant(String id_type_carburant) {
        this.id_type_carburant = id_type_carburant;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDsce() {
        return dsce;
    }

    public void setDsce(String dsce) {
        this.dsce = dsce;
    }

    
}
