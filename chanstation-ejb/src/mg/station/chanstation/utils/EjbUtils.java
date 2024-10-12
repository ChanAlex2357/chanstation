package mg.station.chanstation.utils;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class EjbUtils {
    public static String getLookupName(String type , String warName , String beanName , String interfaceName){
        String name = "java:"+type+"/"+warName+"/"+beanName+"!"+interfaceName;
        System.out.println("NAME : "+name);
        return name;
    }
    public static String getGlobalLookupName(String ear,String bean , String interf){
        return EjbUtils.getLookupName("global", ear , bean, interf);
    }
    public static Object lookEjb(String type , String ejb , String bean , String interf) throws NamingException{
            String name = EjbUtils.getLookupName(
                type,
                ejb,
                bean,
                interf
            );
            return lookEjb(name);
    }
    public static Object lookGlobalEjb(String ejb , String bean , String interf) throws NamingException{
        String name = EjbUtils.getGlobalLookupName(
            ejb,
            bean,
            interf
        );
        return lookEjb(name);
    }
    public static Object lookEjb(String name ) throws NamingException{
        Context context = new InitialContext();
        return lookEjb(context, name);
    }
    public static Object lookEjb(Context context,String name) throws NamingException{
        return context.lookup(name);
    }
}
