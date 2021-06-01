package businessentitiesapi;

import genericmapper.Mapper;

import java.util.function.Function;

public class FlightRouteMapper extends Mapper<FlightRoute, Integer>{

    // No public ctor
    private FlightRouteMapper() {
        super(FlightRoute.class, java.lang.invoke.MethodHandles.lookup());
    }

    // self register
    static {
        Mapper.register(new FlightRouteMapper());
    }

    // the method that it is all about
    @Override
    public Object[] deconstruct(FlightRoute fr) {
        return new Object[]{
                fr.getFlightRouteID(),
                fr.getOriginAirportCode(),
                fr.getDestinationAirportCode()
        };
    }

    @Override
    public Function<FlightRoute, Integer> keyExtractor() {
        return (FlightRoute fr) -> fr.getFlightRouteID();
    }

    @Override
    public Class<Integer> keyType() {
        return Integer.class;

    }
}
