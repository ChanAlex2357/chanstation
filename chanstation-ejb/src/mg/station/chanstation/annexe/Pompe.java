package mg.station.chanstation.annexe;

import bean.CGenUtil;
import bean.ClassMAPTable;
import java.sql.Connection;

public class Pompe extends ClassMAPTable {
    String id_pompe, nom;
    String id_cuve;

    @Override
    public String getAttributIDName() {
        return "id_pompe";
    }

    @Override
    public String getTuppleID() {
        return id_pompe;
    }


    public String getId_pompe() {
        return id_pompe;
    }

    public void setId_pompe(String id_pompe) {
        this.id_pompe = id_pompe;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getId_cuve() {
        return id_cuve;
    }

    public void setId_cuve(String id_cuve) {
        this.id_cuve = id_cuve;
    }
    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("PMP", "GET_SEQ_POMPE");
        this.setId_pompe(makePK(c));
    }

    public Pompe() {
        this.setNomTable("POMPE");
    }
    public Cuve getCuveByIdPompe(String idPompe,Connection connection) throws Exception {
        if (idPompe == null || idPompe.equals("")) idPompe = this.getId_pompe();//
        //Alaina aloha ilay pompe e dia avoay ny idCuve!!!!
        Pompe pompe = ((Pompe[]) CGenUtil.rechercher(new Pompe(),null,null,connection," and id_pompe ='"+idPompe+"'"))[0];
        //izay voa manao hoe id_cuve=...
        System.out.println("POMPE ID:"+pompe.getId_cuve());
        return ((Cuve[]) CGenUtil.rechercher(new Cuve(),null,null,connection," and id_cuve='"+pompe.getId_cuve()+"'"))[0];
    }
}
