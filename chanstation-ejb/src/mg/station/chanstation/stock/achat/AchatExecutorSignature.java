package mg.station.chanstation.stock.achat;

import javax.ejb.Local;

@Local
public interface AchatExecutorSignature {
    public void achat(AchatArgs achatArgs) throws Exception;
}
