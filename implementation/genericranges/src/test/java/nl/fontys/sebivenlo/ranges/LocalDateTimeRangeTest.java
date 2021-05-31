/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fontys.sebivenlo.ranges;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;

/**
 *
 * @author Joel Sebastian Delgado <j.sebastian@student.fontys.nl>
 */
public class LocalDateTimeRangeTest extends RangeTestBase<LocalDateTimeRange, LocalDateTime, Duration>{
    
    RangeTestDataFactory<LocalDateTimeRange, LocalDateTime, Duration> daf; 
    LocalDateTime[] points={ LocalDateTime.of(2017, 2, 13, 15, 56), 
        LocalDateTime.of(2018, 2, 13, 15, 56), 
        LocalDateTime.of(2019, 2, 13, 15, 56), 
        LocalDateTime.of(2020, 2, 13, 15, 56), 
        LocalDateTime.of(2021, 2, 13, 15, 56), 
        LocalDateTime.of(2022, 2, 13, 15, 56)  
    };

    @Override
    RangeTestDataFactory<LocalDateTimeRange, LocalDateTime, Duration> helper() {
        if ( null == daf ) {                                  
            daf = new RangeTestDataFactory<>( points ) { 
                @Override
                LocalDateTimeRange createRange( LocalDateTime start, LocalDateTime end ) {
                    return LocalDateTimeRange.of( start, end );
                }
                @Override
                Duration distance( LocalDateTime a, LocalDateTime b ) {
                    return Duration.between(a, b);
                }
            };
        }
        return daf;                                           
    }
    
}
