package mg.station.chanstation.data;

import java.sql.Connection;
import javax.ejb.Stateless;
import mg.station.chanstation.annexe.Unite;
import bean.CGenUtil;
import bean.ClassMAPTable;
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
    private Object[] loadGenerated(ClassMAPTable classMAPTable , Connection conn) throws Exception{
        return CGenUtil.rechercher(classMAPTable, null, null , conn ,null);
    }
    /*
     * TYPE MOUVEMENT
     */
    @Override
    public TypeMvt[] generateTypeMouvement( Connection conn) throws Exception {
        TypeMvt[] typeMvts  = (TypeMvt[])loadGenerated(new TypeMvt(), conn);
        if (typeMvts.length > 0) {
            return typeMvts;
        }
        typeMvts  = new TypeMvt[2];
        typeMvts[0] = this.createTypeMouvement(1 , "Entree",conn);
        typeMvts[1] = this.createTypeMouvement(-1 , "Sortie", conn);
        CGenUtil.save(typeMvts[0], conn);
        CGenUtil.save(typeMvts[1], conn);
        System.out.println(
            successfullmessage("Types Mouvements")
        );
        return typeMvts;
    }
    private TypeMvt createTypeMouvement(int valeur , String desce , Connection conn) throws Exception{
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
        Unite[] unites = (Unite[])loadGenerated(new Unite() , conn );
        if (unites.length > 0) {
            return unites;
        }
        unites = new Unite[1];
        unites[0] = createUnite("litre", "Litre (L)", conn);
        CGenUtil.save(unites[0]);

        System.out.println(successfullmessage("Unite"));
        return unites;
    }
    private Unite createUnite(String val , String desce , Connection conn) throws Exception{
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
        TypeCarburant[] typeCarburant =(TypeCarburant[])loadGenerated(new TypeCarburant(),conn);
        if (typeCarburant.length>0) {
            return typeCarburant;
        }
        typeCarburant = new TypeCarburant[1];
        typeCarburant[0] = createTypeCarburant("Essence", "Carburant pour les moteur essence", unite.getId_unite(), conn);
        CGenUtil.save(typeCarburant[0]);
        System.out.println(successfullmessage("Type Carburant"));
        return typeCarburant;
    }
    private TypeCarburant createTypeCarburant( String libelle , String desce , String unite, Connection conn) throws Exception{
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
        Carburant[] carburants = (Carburant[]) loadGenerated(new Carburant(), conn);
        if (carburants.length > 0 ) {
            return carburants;
        }
        carburants = new Carburant[2];
        carburants[0] = createCarburant("SP95",null,  5900,4900,typeCarburant.getId_type_carburant(),conn);
        carburants[1] = createCarburant("SP98", null, 6000,5100,typeCarburant.getId_type_carburant(),conn);

        CGenUtil.save(carburants[0]);
        CGenUtil.save(carburants[1]);
        System.out.println(successfullmessage("Carburant"));
        return carburants;
    }
    private Carburant createCarburant(String nom , String desce , double pu_vente , double pu_achat , String typeCarburant , Connection conn) throws Exception{
        Carburant carburant = new Carburant(nom, desce, pu_vente, pu_achat, typeCarburant);
        carburant.construirePK(conn);
        return carburant;
    }
    /*
 * CUVE
 */
@Override
public Cuve[] generateCuve(Carburant carburant, Connection conn) throws Exception {
    Cuve[] cuves = (Cuve[]) loadGenerated(new Cuve(), conn);
    if (cuves.length > 0) {
        return cuves;
    }
    cuves = new Cuve[2];
    cuves[0] = createCuve("Chanstation Cuve 1", 100000, carburant.getId_carburant(), conn);
    cuves[1] = createCuve("Chanstation Cuve 2", 100000, carburant.getId_carburant(), conn);

    CGenUtil.save(cuves[0]);
    CGenUtil.save(cuves[1]);
    System.out.println(successfullmessage("Cuve"));
    return cuves;
}

private Cuve createCuve(String nom,double capacite,String carburant, Connection conn) throws Exception{
    Cuve cuve = new Cuve();
    cuve.construirePK(conn);
    cuve.setNom(nom);
    cuve.setCapacite(capacite);
    cuve.setId_carburant(carburant);
    return cuve;
}
/*
 * POMPE
 */
@Override
public Pompe[] generatePompe(Cuve cuve, Connection conn) throws Exception {
    Pompe[] pompes = (Pompe[]) loadGenerated(new Pompe(), conn);
    if (pompes.length > 0) {
        return pompes;
    }
    pompes = new Pompe[2];
    pompes[0] = createPompe("Pompe 1", cuve.getId_cuve(), conn);
    pompes[1] = createPompe("Pompe 2", cuve.getId_cuve(), conn);

    CGenUtil.save(pompes[0]);
    CGenUtil.save(pompes[1]);
    System.out.println(successfullmessage("Pompe"));
    return pompes;
}

private Pompe createPompe(String nom, String id_cuve, Connection conn) throws Exception {
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
    Pompiste[] pompistes = (Pompiste[]) loadGenerated(new Pompiste(), conn);
    if (pompistes.length > 0) {
        return pompistes;
    }
    pompistes = new Pompiste[2];
    pompistes[0] = createPompiste("John Doe", conn);
    pompistes[1] = createPompiste("Jane Smith", conn);

    CGenUtil.save(pompistes[0]);
    CGenUtil.save(pompistes[1]);
    System.out.println(successfullmessage("Pompiste"));
    return pompistes;
}

private Pompiste createPompiste(String nom, Connection conn) throws Exception {
    Pompiste pompiste = new Pompiste();
    pompiste.construirePK(conn);
    pompiste.setNom(nom);
    return pompiste;
}

/*
 * EQUIVALENCE
 */
@Override
public Equivalence[] generateEquivalence(Cuve cuve, Connection conn) throws Exception {
    Equivalence[] equivalences = (Equivalence[]) loadGenerated(new Equivalence(), conn);
    if (equivalences.length > 0) {
        return equivalences;
    }
    equivalences = new Equivalence[2];
    equivalences[0] = createEquivalence(5000, 1000, cuve.getId_cuve(), conn);
    equivalences[1] = createEquivalence(10000, 2000, cuve.getId_cuve(), conn);

    CGenUtil.save(equivalences[0]);
    CGenUtil.save(equivalences[1]);
    System.out.println(successfullmessage("Equivalence"));
    return equivalences;
}

private Equivalence createEquivalence(double limit, double qte, String id_cuve, Connection conn) throws Exception {
    Equivalence equivalence = new Equivalence();
    equivalence.construirePK(conn);
    equivalence.setLimit(limit);
    equivalence.setQte(qte);
    equivalence.setId_cuve(id_cuve);
    return equivalence;
}

   
    @Override
    public void generateLocalData(Connection conn) throws Exception {
        
    }
    private String successfullmessage( String process){
        return "<"+process +">generated successfully";
    }
}
