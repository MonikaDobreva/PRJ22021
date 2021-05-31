/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fontys.sebivenlo.ranges;

import java.time.Duration;
import java.time.Instant;

/**
 *
 * @author Joel Sebastian Delgado <j.sebastian@student.fontys.nl>
 */
public class InstantRangeTest extends RangeTestBase<InstantRange, Instant, Duration>{
    
    RangeTestDataFactory<InstantRange, Instant, Duration> daf; 
    Instant[] points={ Instant.parse("1980-04-09T10:15:30.00Z"), Instant.parse("1981-04-09T10:15:30.00Z"), Instant.parse("1982-04-09T10:15:30.00Z"), Instant.parse("1983-04-09T10:15:30.00Z"), Instant.parse("1984-04-09T10:15:30.00Z"), Instant.parse("1985-04-09T10:15:30.00Z")  };

    @Override
    RangeTestDataFactory<InstantRange, Instant, Duration> helper() {
        if ( null == daf ) {                                  
            daf = new RangeTestDataFactory<>( points ) { 
                @Override
                InstantRange createRange( Instant start, Instant end ) {
                    return InstantRange.of( start, end );
                }
                @Override
                Duration distance( Instant a, Instant b ) {
                    return Duration.between(a, b);
                }
            };
        }
        return daf;                                           
    }
}
