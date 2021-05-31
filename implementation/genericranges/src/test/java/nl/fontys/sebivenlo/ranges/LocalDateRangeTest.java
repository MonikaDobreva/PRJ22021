/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fontys.sebivenlo.ranges;

import java.time.Duration;
import java.time.LocalDate;

/**
 *
 * @author Joel Sebastian Delgado <j.sebastian@student.fontys.nl>
 */
public class LocalDateRangeTest extends RangeTestBase<LocalDateRange, LocalDate, Duration>{
    
    RangeTestDataFactory<LocalDateRange, LocalDate, Duration> daf; 
    LocalDate[] points={ LocalDate.of(2000, 1, 1), 
        LocalDate.of(2001, 1, 1), 
        LocalDate.of(2002, 1, 1), 
        LocalDate.of(2003, 1, 1), 
        LocalDate.of(2004, 1, 1), 
        LocalDate.of(2005, 1, 1)  
    };

    @Override
    RangeTestDataFactory<LocalDateRange, LocalDate, Duration> helper() {
        if ( null == daf ) {                                  
            daf = new RangeTestDataFactory<>( points ) { 
                @Override
                LocalDateRange createRange( LocalDate start, LocalDate end ) {
                    return LocalDateRange.of( start, end );
                }
                @Override
                Duration distance( LocalDate a, LocalDate b ) {
                    return Duration.between(a.atStartOfDay(), b.atStartOfDay());
                }
            };
        }
        return daf;                                           
    }
    
}
