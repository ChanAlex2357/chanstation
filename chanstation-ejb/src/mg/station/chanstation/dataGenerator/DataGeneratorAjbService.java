package mg.station.chanstation.dataGenerator;

import javax.ejb.Stateless;

@Stateless
public class DataGeneratorAjbService implements DataGeneratorEjbSignature{
    @Override
    public void generateData() throws Exception {
        DataGenerator.generateData();
    }
}
