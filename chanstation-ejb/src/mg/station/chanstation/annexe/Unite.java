package mg.station.chanstation.annexe;

import bean.ClassMAPTable;

public class Unite extends ClassMAPTable{
    String id_unite;
    String val;
    String desce;
    @Override
    public String getAttributIDName() {
        return "id_unite";
    }
    @Override
    public String getTuppleID() {
        return this.getId_unite();
    }
    public String getId_unite() {
        return id_unite;
    }
    public void setId_unite(String id_unite) {
        this.id_unite = id_unite;
    }
    public String getVal() {
        return val;
    }
    public void setVal(String val) {
        this.val = val;
    }
    public String getDesce() {
        return desce;
    }
    public void setDesce(String desce) {
        this.desce = desce;
    }
    
}
