/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fontys.sebivenlo.ranges;
import java.util.Map;
/**
 *
 * @author Joel Sebastian Delgado <j.sebastian@student.fontys.nl>
 */
public class IntegerRangeTest extends RangeTestBase<IntegerRange, Integer, Integer>{
    
    RangeTestDataFactory<IntegerRange, Integer, Integer> daf; 
    Integer[] points={ 42, 51, 55, 1023, 1610, 2840  };

    @Override
    RangeTestDataFactory<IntegerRange, Integer, Integer> helper() {
        if ( null == daf ) {                                  
            daf = new RangeTestDataFactory<>( points ) { 
                @Override
                IntegerRange createRange( Integer start, Integer end ) {
                    return IntegerRange.of( start, end );
                }
                @Override
                Integer distance( Integer a, Integer b ) {
                    return b - a;
                }
            };
        }
        return daf;                                           
    }
    
}
