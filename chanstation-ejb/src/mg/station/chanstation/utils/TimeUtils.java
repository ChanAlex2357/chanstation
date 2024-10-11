package mg.station.chanstation.utils;


import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TimeUtils {
    public static Date convertToSqlDate(String dateString,String lang) throws ParseException {
        String datePattern = "yyyy-MM-dd";
        if (lang.equals("fr")){
            datePattern = "dd-MM-yyyy";
        }
        SimpleDateFormat format = new SimpleDateFormat(datePattern);
        java.util.Date parsedDate = format.parse(dateString);
        Date sqlDate = new Date(parsedDate.getTime());
        return sqlDate;
    }
}