package no.uib.info216.Misc;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class WeekDates {

    public String getName(int day) {
        switch (day) {
            case Calendar.MONDAY:
                return "Monday";
            case Calendar.TUESDAY:
                return "Tuesday";
            case Calendar.WEDNESDAY:
                return "Wednesday";
            case Calendar.THURSDAY:
                return "Thursday";
            case Calendar.FRIDAY:
                return "Friday";
            case Calendar.SATURDAY:
                return "Saturday";
            case Calendar.SUNDAY:
                return "Sunday";
        }
        return null;
    }

    /**
     * Get the days and names of the days N number of days ahead
     * @param numberOfDays int Number of days lookahead
     * @return Hashmap String,String. Name of day -> Date
     */
    public ArrayList<ArrayList<String>> getWeekDates(int numberOfDays){
        ArrayList<ArrayList<String>> returnDates = new ArrayList<ArrayList<String>>();

        Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DAY_OF_WEEK);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        //Get 7 days
        for(int i = numberOfDays; i!=0; i--){
            if(day > numberOfDays){
                day = 1;
            }
            ArrayList<String> tmp = new ArrayList<String>();
            tmp.add(this.getName(day));
            tmp.add(formatter.format(cal.getTime()));
            returnDates.add(tmp);

            //Increments
            cal.add(Calendar.DATE, 1);
            day += 1;
        }

        return returnDates;
    }
}
