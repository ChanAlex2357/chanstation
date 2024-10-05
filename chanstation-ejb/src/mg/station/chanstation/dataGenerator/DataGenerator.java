package mg.station.chanstation.dataGenerator;

import bean.CGenUtil;
import ejbServer.GeneralEJBLocalServer;
import mg.station.chanstation.localEjbClient.EjbClientGetter;
import mg.station.chanstation.annexe.Carburant;
import mg.station.chanstation.annexe.Cuve;
import mg.station.chanstation.annexe.Pompe;
import mg.station.chanstation.annexe.TypeCarburant;
import magasin.Magasin;
import magasin.MagasinLocalEJB;
import utilitaire.UtilDB;

import java.sql.Connection;

public class DataGenerator {
    public static void generateData() throws Exception {

        Connection c = new UtilDB().GetConn();
        Connection c1 = new UtilDB().GetConn("gallois","gallois");
        try {
            TypeCarburant typeCarburant = generateTypeCarburant(c,"Essence");
            Carburant carburant1 = generateCarburant(c,"SP95", typeCarburant.getId_type_carburant(), 4900,5900);
            Carburant carburant2 = generateCarburant(c,"SP98", typeCarburant.getId_type_carburant(), 5100,6000);


            Cuve cuve1 = generateCuve(c,100000,"GALANA Andoharanofotsy",carburant1.getId_carburant());
            Magasin magasin = new Magasin();
            magasin.setId(cuve1.getId_cuve());
            magasin.setDesce("");
            magasin.setIdTypeMagasin("TYPMG000001");
            magasin.setIdProduit("");
            magasin.setVal(cuve1.getNom());

            Cuve cuve2 = generateCuve(c,100000,"GALANA SabNam",carburant2.getId_carburant());

            Magasin magasin2 = new Magasin();
            magasin2.setId(cuve2.getId_cuve());
            magasin2.setDesce("");
            magasin2.setIdTypeMagasin("TYPMG000001");
            magasin2.setIdProduit("");
            magasin2.setVal(cuve2.getNom());

            MagasinLocalEJB magasinLocalEJB = EjbClientGetter.getMagasinEjbService();
            magasinLocalEJB.saveMagasin(magasin);
            magasinLocalEJB.saveMagasin(magasin2);
//        Cuve cuve3 = generateCuve(c,100000,"GALANA Andoharanofotsy Cuve 2",carburant2.getId());
//        Cuve cuve4 = generateCuve(c,100000,"GALANA SabNam Cuve 2",carburant2.getId());
            GeneralEJBLocalServer generalEJBLocalServer = EjbClientGetter.getGeneralEjbService();



            Pompe pompe = generatePompe(c,"Pompe 1",cuve1.getId_cuve());
            Pompe pompe1 = generatePompe(c,"Pompe 2",cuve1.getId_cuve());
            Pompe pompe2 = generatePompe(c,"Pompe 3",cuve1.getId_cuve());
            Pompe pompe3 = generatePompe(c,"Pompe 4",cuve1.getId_cuve());
            Pompe pompe4 = generatePompe(c,"Pompe 5",cuve2.getId_cuve());
            Pompe pompe5 = generatePompe(c,"Pompe 6",cuve2.getId_cuve());
            Pompe pompe6 = generatePompe(c,"Pompe 7",cuve2.getId_cuve());
            Pompe pompe7 = generatePompe(c,"Pompe 8",cuve2.getId_cuve());

            pompe.Pompe pompeB1 = generatePompeB1(c,"Pompe 1",cuve1.getId_cuve());
            pompeB1.setId(pompe.getId_pompe());
            pompe.Pompe pompeB11 = generatePompeB1(c,"Pompe 2",cuve1.getId_cuve());
            pompeB11.setId(pompe1.getId_pompe());
            pompe.Pompe pompeB12 = generatePompeB1(c,"Pompe 3",cuve1.getId_cuve());
            pompeB12.setId(pompe2.getId_pompe());
            pompe.Pompe pompeB13 = generatePompeB1(c,"Pompe 4",cuve1.getId_cuve());
            pompeB13.setId(pompe3.getId_pompe());
            pompe.Pompe pompeB14 = generatePompeB1(c,"Pompe 5",cuve2.getId_cuve());
            pompeB14.setId(pompe4.getId_pompe());
            pompe.Pompe pompeB15 = generatePompeB1(c,"Pompe 6",cuve2.getId_cuve());
            pompeB15.setId(pompe5.getId_pompe());
            pompe.Pompe pompeB16 = generatePompeB1(c,"Pompe 7",cuve2.getId_cuve());
            pompeB15.setId(pompe6.getId_pompe());
            pompe.Pompe pompeB17 = generatePompeB1(c,"Pompe 8",cuve2.getId_cuve());
            pompeB17.setId(pompe7.getId_pompe());

            generalEJBLocalServer.savePompe(pompeB1,c1);
            generalEJBLocalServer.savePompe(pompeB11,c1);
            generalEJBLocalServer.savePompe(pompeB12,c1);
            generalEJBLocalServer.savePompe(pompeB13,c1);
            generalEJBLocalServer.savePompe(pompeB14,c1);
            generalEJBLocalServer.savePompe(pompeB15,c1);
            generalEJBLocalServer.savePompe(pompeB16,c1);
            generalEJBLocalServer.savePompe(pompeB17,c1);

            c.commit();
            c1.commit();
        }catch (Exception e){
            c.rollback();
            c1.rollback();
            e.printStackTrace();
        }finally {
            c.close();
            c1.close();
        }

    }
    public static Pompe generatePompe(Connection connection,String nom,String idCuve) throws Exception {
        Pompe pompe = new Pompe();
        pompe.construirePK(connection);
        pompe.setNom(nom);
        pompe.setId_cuve(idCuve);
        return (Pompe) CGenUtil.save(pompe,connection);
    }
    public static pompe.Pompe generatePompeB1(Connection connection,String nom,String idCuve) throws Exception {
        pompe.Pompe pompeB1 = new pompe.Pompe();
        pompeB1.setIdMagasin(idCuve);
        pompeB1.setMax(10000);
        pompeB1.setVal(nom);
        pompeB1.setDesce(nom);
        return pompeB1;
    }

    public static Cuve generateCuve(Connection connection,double max,String label,String idCarb) throws Exception {
        Cuve cuve = new Cuve();
        cuve.construirePK(connection);
        cuve.setCapacite(max);
        cuve.setNom(label);
        cuve.setId_carburant(idCarb);
        return (Cuve) CGenUtil.save(cuve,connection);
    }
    public static Carburant generateCarburant(Connection connection,String nom,String idTypeCarb,double puAchat,double puVente) throws Exception {
        Carburant carburant = new Carburant();
        carburant.construirePK(connection);
        System.out.println(carburant.getId_carburant());
        carburant.setNom(nom);
        carburant.setId_type_carburant(idTypeCarb);
        carburant.setDesce("");
        carburant.setPu_achat(puAchat);
        carburant.setPu_vente(puVente);
        return (Carburant) CGenUtil.save(carburant,connection);
    }
    public static TypeCarburant generateTypeCarburant(Connection connection,String libelle) throws Exception {
        TypeCarburant typeCarburant = new TypeCarburant();
        typeCarburant.construirePK(connection);
        typeCarburant.setLibelle(libelle);
        // typeCarburant.setId_unite("UNI001");
        return (TypeCarburant) CGenUtil.save(typeCarburant,connection);
    }
}
