
package businesslogic;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
/**
 *
 * @author Rachel
 */
public class EditDetailsLogicTest {
    
    
    /**
     * This test is only for testing that a zero is appended at the beginning
     * of a single digit from hours and min.
     * @param hour
     * @param min
     * @param d date 
     * @param expected dateTime
     */ 
    @ParameterizedTest
    @CsvSource({
    //hour, min date
        "23,20,2021-04-18,23:20 2021-04-18",
        "0,1,2021-04-20,00:01 2021-04-20",
        "12,3,2021-07-10,12:03 2021-07-10"
    })
    public void makeDateTimeTest(int hour,int min, String d,String expected){
        EditDetailsLogic el = new EditDetailsLogic();
        LocalDate date = LocalDate.parse(d);
        LocalDateTime expectedDate = LocalDateTime.parse(
                expected,
                DateTimeFormatter.ofPattern("HH:mm yyyy-MM-dd"));
        
        assertThat(el.makeDateTime(hour, min, date)).isEqualTo(expectedDate)
        ;
    }
}
