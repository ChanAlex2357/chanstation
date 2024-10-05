package mg.station.chanstation.stock.vente;

import mg.station.chanstation.stock.MvtArgent;
import mg.station.chanstation.stock.MvtDTO;
import mg.station.chanstation.stock.MvtService;
import utilitaire.UtilDB;

import java.sql.Connection;
import java.sql.Date;

public class VenteService implements MvtArgent {
    @Override
    public void makeMvtArgent(MvtDTO mvtDto, Connection connection) throws Exception {
        if (connection == null) connection = new UtilDB().GetConn();
        if (mvtDto.getType_mvt() != -1) mvtDto.setType_mvt(-1);
        if (mvtDto.getDaty() == null) mvtDto.setDaty(new Date(System.currentTimeMillis()));
        MvtService mvtService = new MvtService();
        mvtService.makeMvt(mvtDto,connection);
    }
}
