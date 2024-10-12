package mg.station.chanstation.annexe;

import bean.CGenUtil;
import mg.station.chanstation.bean.MaClassMAPTable;
import utilitaire.UtilDB;

import java.sql.Connection;
import java.sql.SQLException;

public class TypeCarburant extends MaClassMAPTable {
    String id_type_carburant;
    String libelle;
    String dsce;
    String id_unite;

    public TypeCarburant() {
        setNomTable("TYPE_CARBURANT");
    }
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
    public String getId_unite() {
        return id_unite;
    }

    public void setId_unite(String id_unite) {
        this.id_unite = id_unite;
    }
    public static TypeCarburant getTypeCarburantById(String id) throws SQLException{
        Connection conn = new UtilDB().GetConn();
        try {
            return getTypeCarburantById(id,conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            conn.close();
        }
        return null;
    }
    public static TypeCarburant getTypeCarburantById(String id , Connection conn) throws Exception{
        if (conn == null) {
            return getTypeCarburantById(id);
        }
        TypeCarburant[] typeCarburant = new TypeCarburant[1];
        typeCarburant[0] = new TypeCarburant();
        typeCarburant[0].setId_type_carburant(id);

        typeCarburant = ((TypeCarburant[])CGenUtil.rechercher(typeCarburant[0],null,null,conn,""));
        if (typeCarburant.length > 0) {
            return typeCarburant[0];
        }
        return null;
    }
}
