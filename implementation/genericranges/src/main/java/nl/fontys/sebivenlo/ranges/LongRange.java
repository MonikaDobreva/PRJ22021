/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fontys.sebivenlo.ranges;

import java.util.function.BiFunction;

/**
 *
 * @author Joel Sebastian Delgado <j.sebastian@student.fontys.nl>
 */
public class LongRange implements Range<LongRange, Long, Long> {
    private Long start;
    private Long end;

    public LongRange(Long a, Long b) {
        Long[] t = Range.minmax( a, b );
        this.start = t[0];
        this.end = t[1];
    }

    @Override
    public Long start() {
        return this.start;
    }

    @Override
    public Long end() {
        return this.end;
    }

    @Override
    public BiFunction<Long, Long, Long> meter() {
        return (a,b)-> b - a;
    }

    @Override
    public LongRange between( Long start, Long end ) {
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
    public Long zero() {
        return 0L;
    }

    /**
     * ConvenienceFactory.
     *
     * @param start of range
     * @param end of range
     * @return the range
     */
    public static LongRange of( Long start, Long end ) {
        return new LongRange(start, end);
    }
}
