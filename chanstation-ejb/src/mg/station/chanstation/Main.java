package mg.station.chanstation;

import mg.station.chanstation.annexe.Carburant;
public class Main {
    public static void main(String[] args) {
        try {
            Carburant carburant = new Carburant("ESP20", "carburant essence", 6500, 5100, null);
            carburant.viser();
        } catch (Exception e) {
            System.out.println(" ERROR ON EXECUTING GENERATION DATA");
            e.printStackTrace();
        }
    }
}
