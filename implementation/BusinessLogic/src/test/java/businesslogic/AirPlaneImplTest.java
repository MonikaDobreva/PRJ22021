
package businesslogic;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Rachel
 */
public class AirPlaneImplTest {
    
    AirplaneImpl ap = new AirplaneImpl("Boeing 377","V-BBBB",367);
    AirplaneImpl wrongAp = new AirplaneImpl("","V-BBBB",367);
    
    @Test
    public void constructorRightTest(){
        assertThat(ap).isNotNull();
        assertThat(ap.getName()).isEqualTo("Boeing 377");
    }
    
    @Test
    public void constructorWrongTest(){
        assertThat(wrongAp).isNotNull();
        assertThat(wrongAp.getCode()).isEqualTo("X-XXXX");
    }
    
    @Test
    public void toStringTest(){
        assertThat(ap.toString()).contains("Boeing 377","V-BBBB","367");
    }  
}
