package mg.station.chanstation.facture;

import java.sql.Connection;

import mg.station.chanstation.bean.MaClassMAPTable;

public class FactureClient extends MaClassMAPTable{
    public FactureClient (){
        setNomTable("FACTURECLIENT");
    }
    @Override
    public MaClassMAPTable createObject(Connection localconn, Connection remoteconn) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createObject'");
    }

    @Override
    public String getAttributIDName() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAttributIDName'");
    }

    @Override
    public String getTuppleID() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTuppleID'");
    }
    
}
