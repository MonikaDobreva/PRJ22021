/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fontys.sebivenlo.ranges;

/**
 *
 * @author Joel Sebastian Delgado <j.sebastian@student.fontys.nl>
 */
public class LongRangeTest extends RangeTestBase<LongRange, Long, Long>{
    
    RangeTestDataFactory<LongRange, Long, Long> daf; 
    Long[] points={ 0L, 1L, 2L, 3L, 4L, 5L  };

    @Override
    RangeTestDataFactory<LongRange, Long, Long> helper() {
        if ( null == daf ) {                                  
            daf = new RangeTestDataFactory<>( points ) { 
                @Override
                LongRange createRange( Long start, Long end ) {
                    return LongRange.of( start, end );
                }
                @Override
                Long distance( Long a, Long b ) {
                    return b - a;
                }
            };
        }
        return daf;                                           
    }
}
