package mg.station.chanstation.stock;

import bean.ClassMAPTable;
import java.sql.Connection;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

public class TypeMvt extends ClassMAPTable {
    String id_type_mvt;
    int valeur;
    String desce;
    public TypeMvt(){
        this.setNomTable("TYPE_MVT");
    }
    public TypeMvt(int valeur , String desce){
        setValeur(valeur);
        setDesce(desce);
        this.setNomTable();
    }
    public TypeMvt(int valeur){
        setValeur(valeur);
        setDesce(null);
        this.setNomTable();
    }
    
    private void setNomTable(){
        this.setNomTable("type_mvt");
    }
    @Override
    public String getAttributIDName() {
        return "id_type_mvt";
    }

    @Override
    public String getTuppleID() {
        return id_type_mvt;
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("TMVT", "GET_SEQ_TYPE_MVT");
        String pk = this.makePK(c);
        this.setId_type_mvt(pk);
    }
    @Override
    public String toString() {
        return this.getDesce()+"["+this.getValeur()+"]";
    }
    // Getters and Setters
    public String getId_type_mvt() {
        return id_type_mvt;
    }

    public void setId_type_mvt(String id_type_mvt) {
        this.id_type_mvt = id_type_mvt;
    }

    public int getValeur() {
        return valeur;
    }

    public void setValeur(int valeur) {
        this.valeur = valeur;
    }

    public String getDesce() {
        return desce;
    }

    public void setDesce(String desce) {
        this.desce = desce;
    }
}
