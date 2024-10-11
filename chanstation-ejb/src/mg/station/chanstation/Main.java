package mg.station.chanstation;

import java.sql.Connection;

import mg.station.chanstation.data.DataGenBean;
import mg.station.chanstation.stock.TypeMvt;
import utilitaire.UtilDB;

public class Main {
    public static void main(String[] args) {
        DataGenBean dataGenBean = new DataGenBean();
        try {
            Connection conn = new UtilDB().GetConn();
            
            dataGenBean.generateLocalData(null);
            
            // TypeMvt[] mvts = dataGenBean.generateTypeMouvement(null);
        } catch (Exception e) {
            System.out.println(" ERROR ON EXECUTING GENERATION DATA");
            e.printStackTrace();
        }
    }
}
