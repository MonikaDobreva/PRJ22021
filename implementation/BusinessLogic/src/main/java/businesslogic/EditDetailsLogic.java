
package businesslogic;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Rachel
 */
public class EditDetailsLogic {
    
    
    public LocalDateTime makeDateTime(Integer hour, Integer min, LocalDate date){
         return  LocalDateTime.parse(
                    makeTimeValid(hour.toString())
                    + ":"
                    + makeTimeValid(min.toString())
                    + " "
                    + date,
                    DateTimeFormatter.ofPattern("HH:mm yyyy-MM-dd"));
    }
    
    /**
     * Small helper method, which adds an additional 0 to the time if it is only
     * one digit. Otherwise the selected time cannot be properly parsed.
     *
     * @param t time value
     * @return formatted time value
     */
    private String makeTimeValid(String t) {
        return t.length() == 1 ? "0" + t : t;
    }
}
