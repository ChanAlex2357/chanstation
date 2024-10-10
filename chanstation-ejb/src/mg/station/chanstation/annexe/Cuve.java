package mg.station.chanstation.annexe;

import bean.CGenUtil;
import bean.ClassMAPTable;
import utilitaire.UtilDB;

import java.sql.Connection;
public class Cuve extends ClassMAPTable {
    String id_cuve, nom;
    double capacite;
    String id_carburant;

    @Override
    public String toString() {
        return "["+getId_cuve()+";"+getNom()+";"+getCapacite()+";"+getId_carburant()+"]";
    }
    public Cuve(){setNomTable("CUVE");}
    @Override
    public String getAttributIDName() {
        return "id_cuve";
    }

    @Override
    public String getTuppleID() {
        return getId_cuve();
    }

    public String getId_cuve() {
        return id_cuve;
    }

    public void setId_cuve(String id_cuve) {
        this.id_cuve = id_cuve;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getCapacite() {
        return capacite;
    }

    public void setCapacite(double capacite) {
        this.capacite = capacite;
    }

    public String getId_carburant() {
        return id_carburant;
    }

    public void setId_carburant(String id_carburant) {
        this.id_carburant = id_carburant;
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("CV", "GET_SEQ_CUVE");
        this.setId_cuve(makePK(c));
    }


    public Carburant getCarburantDetails(Connection c) throws Exception {
        if (c == null) c=new UtilDB().GetConn();
        return ((Carburant[]) CGenUtil.rechercher(new Carburant(),null,null," and id_carburant = '"+this.getId_carburant()+"'"))[0];
    }
}
