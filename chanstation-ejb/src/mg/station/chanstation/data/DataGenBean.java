package mg.station.chanstation.data;

import java.sql.Connection;
import java.sql.SQLException;

import javax.ejb.Stateless;
import mg.station.chanstation.annexe.Unite;
import mg.station.chanstation.bean.MaClassMAPTable;
import bean.CGenUtil;
import mg.station.chanstation.annexe.Carburant;
import mg.station.chanstation.annexe.Cuve;
import mg.station.chanstation.annexe.Equivalence;
import mg.station.chanstation.annexe.Pompe;
import mg.station.chanstation.annexe.Pompiste;
import mg.station.chanstation.annexe.TypeCarburant;
import mg.station.chanstation.stock.TypeMvt;
import utilitaire.UtilDB;
@Stateless
public class DataGenBean implements DataGenService {
   
    /*
     * TYPE MOUVEMENT
     */
    @Override
    public TypeMvt[] generateTypeMouvement( Connection conn) throws Exception {
        // TypeMvt[] typeMvts  = (TypeMvt[])CGenUtil.rechercher(new TypeMvt(), null,null,conn,"");
            TypeMvt[] typeMvts  = null;
        // if (typeMvts.length > 0) {
            // return typeMvts;
        // }
        typeMvts  = new TypeMvt[2];
        typeMvts[0] = this.createTypeMouvement(1 , "Entree",conn);
        typeMvts[1] = this.createTypeMouvement(-1 , "Sorite",conn);
        MaClassMAPTable.createObjects(typeMvts, conn);
        System.out.println(
            successfullmessage("Types Mouvements")
        );
        return typeMvts;
    }
    public TypeMvt createTypeMouvement(int valeur , String desce , Connection conn) throws Exception{
        TypeMvt typeMvt = new TypeMvt();
        typeMvt.construirePK(conn);
        typeMvt.setValeur(valeur);
        typeMvt.getDesce();
        return typeMvt;
    }
    /*
     * UNITE
     */
    @Override
    public Unite[] generateUnite(Connection conn) throws Exception {
        // Unite[] unites = (Unite[])CGenUtil.rechercher(new Unite() , null,null,conn ,"");
            Unite[] unites = null;
        // if (unites.length > 0) {
            // return unites;
        // }
        unites = new Unite[1];
        unites[0] = createUnite("litre", "Litre (L)", conn);
        CGenUtil.save(unites[0],conn);
        System.out.println(successfullmessage("Unite"));
        return unites;
    }
    public Unite createUnite(String val , String desce , Connection conn) throws Exception{
        Unite unite = new Unite();
        unite.construirePK(conn);
        unite.setVal("litre");
        unite.setDesce("Litre");
        return unite;
    }
    /*
     * TYPE CARBURANT
     */
    @Override
    public TypeCarburant[] generateTypeCarburant(Unite unite,Connection conn) throws Exception {
        // TypeCarburant[] typeCarburant =(TypeCarburant[])CGenUtil.rechercher(new TypeCarburant(),null,null,conn,"");
        TypeCarburant[] typeCarburant =null;
        // if (typeCarburant.length>0) {
            // return typeCarburant;
        // }
        typeCarburant = new TypeCarburant[1];
        typeCarburant[0] = createTypeCarburant("Essence", "Carburant pour les moteur essence", unite.getId_unite(), conn);
        CGenUtil.save(typeCarburant[0],conn);
        System.out.println(successfullmessage("Type Carburant"));
        return typeCarburant;
    }
    public TypeCarburant createTypeCarburant( String libelle , String desce , String unite, Connection conn) throws Exception{
        TypeCarburant typeCarburant = new TypeCarburant();
        typeCarburant.construirePK(conn);
        typeCarburant.setLibelle(libelle);
        typeCarburant.setId_unite(unite);
        typeCarburant.setDsce(desce);

        return typeCarburant;
    }
    /*
     * CARBURANT
     */
    @Override
    public Carburant[] generateCarburant(TypeCarburant typeCarburant,Connection conn) throws Exception {
        // Carburant[] carburants = (Carburant[]) CGenUtil.rechercher(new Carburant(), null,null,conn,"");
            Carburant[] carburants = null;
        // if (carburants.length > 0 ) {
            // return carburants;
        // }
        carburants = new Carburant[2];
        carburants[0] = createCarburant("SP95",null,  5900,4900,typeCarburant.getId_type_carburant(),conn);
        carburants[1] = createCarburant("SP98", null, 6000,5100,typeCarburant.getId_type_carburant(),conn);
        

        MaClassMAPTable.createObjects(carburants, conn);

        System.out.println(successfullmessage("Carburant"));
        return carburants;
    }
    public Carburant createCarburant(String nom , String desce , double pu_vente , double pu_achat , String typeCarburant , Connection conn) throws Exception{
        Carburant carburant = new Carburant(nom, desce, pu_vente, pu_achat, typeCarburant);
        carburant.construirePK(conn);
        System.out.println(carburant);
        return carburant;
    }
    /*
 * CUVE
 */
@Override
public Cuve[] generateCuve(Carburant[] carburant, Connection conn) throws Exception {
    // Cuve[] cuves = (Cuve[]) CGenUtil.rechercher(new Cuve(), null,null,conn,"");
    Cuve[] cuves = null;
    // if (cuves.length > 0) {
        // return cuves;
    // }
    cuves = new Cuve[2];
    cuves[0] = createCuve("Chanstation Cuve 1", 100000, carburant[0].getId_carburant(), conn);
    cuves[1] = createCuve("Chanstation Cuve 2", 100000, carburant[1].getId_carburant(), conn);

    MaClassMAPTable.createObjects(cuves, conn);

    System.out.println(successfullmessage("Cuve"));
    return cuves;
}

public Cuve createCuve(String nom,double capacite,String carburant, Connection conn) throws Exception{
    Cuve cuve = new Cuve();
    cuve.construirePK(conn);
    cuve.setNom(nom);
    cuve.setCapacite(capacite);
    cuve.setId_carburant(carburant);
    System.out.println(cuve);
    return cuve;
}
/*
 * POMPE
 */
@Override
public Pompe[] generatePompe(Cuve[] cuve, Connection conn) throws Exception {
    // Pompe[] pompes = (Pompe[]) CGenUtil.rechercher(new Pompe(), null,null,conn,"");
    Pompe[] pompes = null;
    // if (pompes.length > 0) {
        // return pompes;
    // }
    pompes = new Pompe[2];
    pompes[0] = createPompe("Pompe 1", cuve[0].getId_cuve(), conn);
    pompes[1] = createPompe("Pompe 2", cuve[1].getId_cuve(), conn);

    MaClassMAPTable.createObjects(pompes, conn);

    System.out.println(successfullmessage("Pompe"));
    return pompes;
}

public Pompe createPompe(String nom, String id_cuve, Connection conn) throws Exception {
    Pompe pompe = new Pompe();
    pompe.construirePK(conn);
    pompe.setNom(nom);
    pompe.setId_cuve(id_cuve);
    return pompe;
}

/*
 * POMPISTE
 */
@Override
public Pompiste[] generatePompiste(Connection conn) throws Exception {
    // Pompiste[] pompistes = (Pompiste[]) CGenUtil.rechercher(new Pompiste(), null,null,conn,"");
    Pompiste[] pompistes = null;
    // if (pompistes.length > 0) {
        // return pompistes;
    // }
    pompistes = new Pompiste[2];
    pompistes[0] = createPompiste("John Doe", conn);
    pompistes[1] = createPompiste("Jane Smith", conn);

    MaClassMAPTable.createObjects(pompistes, conn);

    System.out.println(successfullmessage("Pompiste"));
    return pompistes;
}

public Pompiste createPompiste(String nom, Connection conn) throws Exception {
    Pompiste pompiste = new Pompiste();
    pompiste.construirePK(conn);
    pompiste.setNom(nom);
    return pompiste;
}

/*
 * EQUIVALENCE
 */
@Override
public Equivalence[] generateEquivalence(Cuve[] cuve, Connection conn) throws Exception {
    // Equivalence[] equivalences = (Equivalence[]) CGenUtil.rechercher(new Equivalence(), null,null,conn,"");
    Equivalence[] equivalences = null;
    // if (equivalences.length > 0) {
        // return equivalences;
    // }
    equivalences = new Equivalence[6];
    equivalences[0] = createEquivalence(1, 100, cuve[0].getId_cuve(), conn);
    equivalences[1] = createEquivalence(1, 100, cuve[1].getId_cuve(), conn);
    equivalences[2] = createEquivalence(2, 200, cuve[0].getId_cuve(), conn);
    equivalences[3] = createEquivalence(7, 400, cuve[1].getId_cuve(), conn);
    equivalences[4] = createEquivalence(3, 300, cuve[0].getId_cuve(), conn);
    equivalences[5] = createEquivalence(10, 900, cuve[1].getId_cuve(), conn);

    MaClassMAPTable.createObjects(equivalences, conn);

    System.out.println(successfullmessage("Equivalence"));
    return equivalences;
}

public Equivalence createEquivalence(double limit, double qte, String id_cuve, Connection conn) throws Exception {
    Equivalence equivalence = new Equivalence();
    equivalence.construirePK(conn);
    equivalence.setLimit(limit);
    equivalence.setQte(qte);
    equivalence.setId_cuve(id_cuve);
    return equivalence;
}

   
    @Override
    public void generateLocalData(Connection conn) throws SQLException  {
        if (conn == null) {
            conn = new UtilDB().GetConn();
        }
        try {
            conn.setAutoCommit(false);
            generateTypeMouvement(conn);
            generatePompiste(conn);

            Unite[] unite= generateUnite(conn);
            TypeCarburant[] typeCarburants = generateTypeCarburant(unite[0], conn);
            Carburant[] carburants = generateCarburant(typeCarburants[0], conn);
            Cuve[] cuves = generateCuve(carburants, conn);
            generatePompe(cuves, conn);
            generateEquivalence(cuves, conn);
            conn.commit();
        } catch (Exception e) {
            conn.rollback();
            e.printStackTrace();
        }
        finally{
            conn.close();
        }


    }
    public String successfullmessage( String process){
        return "<"+process +">generated successfully";
    }
}
