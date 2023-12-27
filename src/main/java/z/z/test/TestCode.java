package z.z.test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class TestCode {

    public static void main(String[] args) {
        Date date = new Date();
        System.out.println(date);

        System.out.println(date.getYear());
        System.out.println(date.getMonth());
        System.out.println(date.getDay());


        DateFormat dateFormat = new SimpleDateFormat();

        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Shanghai"));

        String[] availableIDs = TimeZone.getAvailableIDs();
        for (String availableID : availableIDs) {
            System.out.println(availableID);
        }
    }
}
