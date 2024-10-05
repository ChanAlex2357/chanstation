package mg.station.chanstation.prelevement;

import bean.CGenUtil;
import historique.MapUtilisateur;
import mg.station.chanstation.localEjbClient.EjbClientGetter;
import mg.station.chanstation.stock.MvtArgent;
import mg.station.chanstation.stock.MvtDTO;
import mg.station.chanstation.stock.vente.VenteService;

import prelevement.PrelevementLocal;
import user.UserEJB;
import user.UserEJBClient;
import utilitaire.UtilDB;
import vente.Vente;

import javax.ejb.Stateless;
import java.sql.Connection;
@Stateless
public class PrelevementService implements PrelevementSignature{
    @Override
    public FactureClient ciblerDeuxBases(Prelevement prelevement,Connection connection) throws Exception {
        // Prélever compteur
        if (connection == null) connection = new UtilDB().GetConn();
        Connection c1 = new UtilDB().GetConn("gallois","gallois");
        connection.setAutoCommit(false);
        c1.setAutoCommit(false);
        try{
            prelevement.construirePK(connection);

            MvtArgent mvtArgent = new VenteService();
            MvtDTO mvtDTO = new MvtDTO(prelevement.getPompe(connection).getId_pompe(), 1, 1 , prelevement.getDaty());
            FactureClient factureClient = null;
            PrelevementLocal prelevementLocal = EjbClientGetter.getPrelevementEjbService();
            prelevement.Prelevement prelevement1 = getPrelevementForGallois(prelevement);

            Prelevement prelAnt = prelevement.getPrelevementAnterieur(connection);
            if (prelAnt != null) {
                prelevement.setId_prelevement_anterieure(prelAnt.getId_prelevement());
                prelevement1.setIdPrelevementAnterieur(prelAnt.getId_prelevement());
            }

            System.out.println("Hatreto izy dia mandeha");
            CGenUtil.save(prelevement,connection);
            CGenUtil.save(prelevement1,c1);

            if (prelevement.isVente(connection)){

                System.out.println("ANT ANT ANT"+prelAnt.getCompteur());
                double mvt = prelevement.getCompteur() - prelAnt.getCompteur();
                System.out.println(prelAnt.getId_prelevement());
                mvtDTO.setQte(mvt);
                mvtArgent.makeMvtArgent(mvtDTO,connection);

                System.out.println("ID PRELEVEMENT:"+prelevement.getId_prelevement());

                prelevementLocal.generateAndPersistVenteRemote(prelevement1,"dg",c1);

                double qteAnt = 0;
                qteAnt = prelAnt.getCompteur();
                factureClient = new FactureClient(prelAnt,prelevement,connection);
            }
//            connection.commit();
//            c1.commit();
            if (prelevement.isVente(connection)) {
                Vente v = prelevement.viser(c1);
                v.setIdClient("CLI000054");
                v.validerObject("1060", c1);
            }
            connection.commit();
            c1.commit();
            return factureClient;
        }catch(Exception e){
            connection.rollback();
            c1.rollback();
            e.printStackTrace();
        }finally {
            connection.close();
            c1.close();
        }
        return null;
    }

    private static prelevement.Prelevement getPrelevementForGallois(Prelevement prelevement) {
        prelevement.Prelevement prelevement1 = new prelevement.Prelevement();
        prelevement1.setId(prelevement.getId_prelevement());
        prelevement1.setCompteur(prelevement.getCompteur());
        prelevement1.setDaty(prelevement.getDaty());
        prelevement1.setDesignation("Prelevement du "+prelevement.getDaty().toString());
        prelevement1.setIdPompe(prelevement.getId_pompe());
        prelevement1.setIdPompiste(Integer.parseInt(prelevement.getId_pompiste()));
        prelevement1.setHeure(prelevement.getHeure());
        prelevement1.setIdPrelevementAnterieur(prelevement.getId_prelevement_anterieure());
        return prelevement1;
    }


}
