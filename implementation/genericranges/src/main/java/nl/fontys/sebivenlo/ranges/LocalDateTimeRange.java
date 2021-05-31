/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fontys.sebivenlo.ranges;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.function.BiFunction;

/**
 *
 * @author Joel Sebastian Delgado <j.sebastian@student.fontys.nl>
 */
public class LocalDateTimeRange implements Range<LocalDateTimeRange, LocalDateTime, Duration>{
    private LocalDateTime start;
    private LocalDateTime end;

    public LocalDateTimeRange(LocalDateTime a, LocalDateTime b) {
        LocalDateTime[] t = Range.minmax( a, b );
        this.start = t[0];
        this.end = t[1];
    }

    @Override
    public LocalDateTime start() {
        return this.start;
    }

    @Override
    public LocalDateTime end() {
        return this.end;
    }

    @Override
    public BiFunction<LocalDateTime, LocalDateTime, Duration> meter() {
        return (a,b)-> Duration.between(a, b);
    }

    @Override
    public LocalDateTimeRange between( LocalDateTime start, LocalDateTime end ) {
        return of(start, end);
    }

    // since the methods hashCode, equals and toString are defined in Object,
    // you cannot overwrite them in an interface. The best you can do is invoke the methods
    // of the interface or use the new java record type, finalized in Java 16.
    @Override
    public int hashCode() {
        return rangeHashCode();
    }

    @Override
    @SuppressWarnings( "EqualsWhichDoesntCheckParameterClass" )
    public boolean equals( Object obj ) {
        return rangeEquals( obj );
    }

    @Override
    public String toString() {
        return rangeToString();
    }

    @Override
    public Duration zero() {
        return Duration.ZERO;
    }

    /**
     * ConvenienceFactory.
     *
     * @param start of range
     * @param end of range
     * @return the range
     */
    public static LocalDateTimeRange of( LocalDateTime start, LocalDateTime end ) {
        return new LocalDateTimeRange(start, end);
    }
    
}
