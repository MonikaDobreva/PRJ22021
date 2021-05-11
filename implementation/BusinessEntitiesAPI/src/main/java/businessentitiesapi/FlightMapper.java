
package businessentitiesapi;

import genericmapper.Mapper;

import java.util.function.Function;

/**
 * @author Rachel
 */
public class FlightMapper extends Mapper<Flight, Integer> {


    // No public ctor 
    private FlightMapper() {
        super(Flight.class, java.lang.invoke.MethodHandles.lookup());
    }

    // self register
    static {
        Mapper.register(new FlightMapper());
    }

    // the method that it is all about
    @Override
    public Object[] deconstruct(Flight f) {
        return new Object[]{
            f.getFlightID(),
            f.getDepartureTime(),
            f.getArrivalTime(),
            f.getAirplane(),
            f.getBasePrice(),
            
        };
    }

    @Override
    public Function<Flight, Integer> keyExtractor() {
        return (Flight f) -> f.getFlightID();
    }

    @Override
    public Class<Integer> keyType() {
        return Integer.class;

    }
}