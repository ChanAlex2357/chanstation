package mg.station.chanstation.prelevement_cuve;

import java.sql.Date;

public class PrelevementCuveArgs {
    Date daty;
    double limit;
    String id_cuve;
    public PrelevementCuveArgs(Date daty, double limit, String id_cuve) {
        this.daty = daty;
        this.limit = limit;
        this.id_cuve = id_cuve;
    }
    public Date getDaty() {
        return daty;
    }
    public void setDaty(Date daty) {
        this.daty = daty;
    }
    public double getLimit() {
        return limit;
    }
    public void setLimit(double limit) {
        this.limit = limit;
    }
    public String getId_cuve() {
        return id_cuve;
    }
    public void setId_cuve(String id_cuve) {
        this.id_cuve = id_cuve;
    }
    
}
