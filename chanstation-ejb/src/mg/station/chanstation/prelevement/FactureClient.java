package mg.station.chanstation.prelevement;

import avoir.AvoirFC;
import avoir.AvoirFCFille;
import bean.CGenUtil;
import mg.station.chanstation.annexe.Carburant;
import mg.station.chanstation.annexe.Pompe;

import java.sql.Connection;

public class FactureClient {
    double montant;
    Prelevement prelevement;
    double qteReleve;

    public double getQteReleve() {
        return qteReleve;
    }

    public void setQteReleve(double qteReleve) {
        this.qteReleve = qteReleve;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }
    public void setMontant(Prelevement prelAnt,Prelevement prelevement,Connection connection) throws Exception {
        Carburant carburant = getCorrespondingCarburant(prelevement.getId_pompe(),connection);
        this.setQteReleve(prelevement.getCompteur()- prelAnt.getCompteur());
        this.montant = carburant.getPu_vente() * (prelevement.getCompteur()- prelAnt.getCompteur());
    }

    public Prelevement getPrelevement() {
        return prelevement;
    }

    public void setPrelevement(Prelevement prelevement) {
        this.prelevement = prelevement;
    }

    public FactureClient(Prelevement prelAnt,Prelevement prelevement,Connection connection) throws Exception {
        this.setMontant(prelAnt,prelevement,connection);
        this.setPrelevement(prelevement);
    }

    public FactureClient(String idPrelevement,Connection connection) throws Exception {
        System.out.println("ID PRELEVEMENT:"+idPrelevement);
        Prelevement prelevement = ((Prelevement[]) CGenUtil.rechercher(new Prelevement(),null,null,connection," and id_prelevement = '"+idPrelevement+"'"))[0];
        Prelevement prelevementAnt = prelevement.getPrelevementByIdAnterieur(connection);
        this.setMontant(prelevementAnt,prelevement,connection);
        this.setPrelevement(prelevement);
    }

    public Carburant getCorrespondingCarburant(String idPompe, Connection connection) throws Exception {
        Pompe pompe = new Pompe();
        return pompe.getCuveByIdPompe(idPompe,connection).getCarburantDetails(connection);
    }

}
