package mg.station.chanstation;

import mg.station.chanstation.data.DataGenBean;
import mg.station.chanstation.data.DataGenService;

public class GenMain {
    public static void main(String[] args) {
        try {
            DataGenService service = new DataGenBean();
            service.generateLocalData(null);
        } catch (Exception e) {
            System.out.println(" ERROR ON EXECUTING GENERATION DATA");
            e.printStackTrace();
        }
    }
}
