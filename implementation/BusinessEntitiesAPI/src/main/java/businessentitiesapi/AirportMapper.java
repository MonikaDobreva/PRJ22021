
package businessentitiesapi;

import genericmapper.Mapper;

import java.util.function.Function;

/**
 * @author Rachel
 */
public class AirportMapper extends Mapper<Airport, Integer> {


    // No public ctor
    private AirportMapper() {
        super(Airport.class, java.lang.invoke.MethodHandles.lookup());
    }

    // self register
    static {
        Mapper.register(new AirportMapper());
    }

    // the method that it is all about
    @Override
    public Object[] deconstruct(Airport a) {
        return new Object[]{
                a.getIataCode(),
                a.getAirportID(),
                a.getCity(),
                a.getCountry(),
                a.getFullName()

        };
    }

    @Override
    public Function<Airport, Integer> keyExtractor() {
        return Airport::getAirportID;
    }

    @Override
    public Class<Integer> keyType() {
        return Integer.class;

    }
}