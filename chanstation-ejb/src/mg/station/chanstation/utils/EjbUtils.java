package mg.station.chanstation.utils;


public class EjbUtils {
    public static String getLookupName(String type , String warName , String beanName , String interfaceName){
        return "java:"+type+"/"+warName+"/"+beanName+"!"+interfaceName;
    }
    public static String getGlobalLookupName(String ear,String bean , String interf){
        return EjbUtils.getLookupName("global", ear , bean, interf);
    }
}
