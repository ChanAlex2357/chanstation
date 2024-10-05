package mg.station.chanstation.stock.achat;

import bean.CGenUtil;
import ejbServer.GeneralEJBLocalServer;
import ejbServer.GeneralEJBService;
import faturefournisseur.FactureFournisseur;
import faturefournisseur.FactureFournisseurDetails;
import mg.station.chanstation.localEjbClient.EjbClientGetter;
import mg.station.chanstation.stock.MvtArgent;
import mg.station.chanstation.stock.MvtDTO;
import mg.station.chanstation.annexe.Carburant;
import mg.station.chanstation.annexe.Cuve;
import utilitaire.UtilDB;

import javax.ejb.Stateless;
import java.sql.Connection;
@Stateless
public class AchatExecutor implements AchatExecutorSignature{

    @Override
    public void achat(AchatArgs achatArgs) throws Exception {
        Connection connection = null;
        Connection gallois = new UtilDB().GetConn("gallois","gallois");
        GeneralEJBLocalServer generalEJBService = EjbClientGetter.getGeneralEjbService();
//        generalEJBService.
        try {
            connection = new UtilDB().GetConn();
            connection.setAutoCommit(false);
            gallois.setAutoCommit(false);
            MvtArgent mvtArgent = new AchatService();

            FactureFournisseur factureFournisseur = genererFacture(achatArgs);
            FactureFournisseurDetails[] factureFournisseurDetails = genererFactureFille(achatArgs,connection);
            generalEJBService.generateAndPersistFF(factureFournisseur,factureFournisseurDetails,gallois);
//            MvtDTO mvtDTO = new MvtDTO();
//            mvtDTO.setDaty(achatArgs.getDaty());
//            mvtDTO.setType_mvt(1);
//            mvtDTO.setQte(achatArgs.getQte());
//            mvtDTO.setId_cuve(achatArgs.getId_cuve());
//            mvtArgent.makeMvtArgent(mvtDTO,connection);
            connection.commit();
            gallois.commit();
        }
        catch (Exception e){
            connection.rollback();
            gallois.rollback();
            e.printStackTrace();
        }finally {
            connection.close();
            gallois.close();
        }
    }
    public FactureFournisseur genererFacture(AchatArgs achatArgs){
        FactureFournisseur ff = new FactureFournisseur();
        ff.setIdFournisseur("FRNDIV01");
        ff.setEstPrevu(0);
        ff.setDevise("AR");
        ff.setDateEcheancePaiement(achatArgs.getDaty());
        ff.setReference("rf");
        ff.setDesignation("Facture carburant du "+achatArgs.getDaty().toString());
        ff.setDaty(achatArgs.getDaty());
        return ff;
    }
    public FactureFournisseurDetails[] genererFactureFille(AchatArgs achatArgs,Connection connection) throws Exception {
        Cuve cuve = ((Cuve[]) CGenUtil.rechercher(new Cuve(),null,null," and id_cuve='"+achatArgs.getId_cuve()+"'"))[0];
        cuve.setId(achatArgs.getId_cuve());
        System.out.println("Cuve N°:"+cuve.getId_cuve());
        Carburant carburant = cuve.getCarburantDetails(connection);
        FactureFournisseurDetails details = new FactureFournisseurDetails();
        details.setNomTable("FACTUREFOURNISSEURFILLE");
        details.setCompte("607014");
        details.setIdDevise("AR");
        details.setPu(carburant.getPu_achat());
        details.setTva(0);
        details.setRemises(0);
        details.setQte(achatArgs.getQte());
        details.setIdProduit(carburant.getId_carburant());
        FactureFournisseurDetails[] factureFournisseurDetails = new FactureFournisseurDetails[1];
        factureFournisseurDetails[0] = details;
        factureFournisseurDetails[0].setNomTable("FACTUREFOURNISSEURFILLE");
        return factureFournisseurDetails;
    }


}
