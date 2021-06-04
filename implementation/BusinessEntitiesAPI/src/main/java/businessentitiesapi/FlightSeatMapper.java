package businessentitiesapi;

import genericmapper.Mapper;

import java.util.function.Function;

public class FlightSeatMapper extends Mapper<FlightSeat, Integer> {

    // No public ctor
    private FlightSeatMapper() {
        super(FlightSeat.class, java.lang.invoke.MethodHandles.lookup());
    }

    // self register
    static {
        Mapper.register(new FlightSeatMapper());
    }

    // the method that it is all about
    @Override
    public Object[] deconstruct(FlightSeat fs) {
        return new Object[]{
                fs.getFlightSeatId(),
                fs.getSeatId(),
                fs.getFlightId(),
                fs.isAvailable()
        };
    }

    @Override
    public Function<FlightSeat, Integer> keyExtractor() {
        return (FlightSeat fs) -> fs.getFlightSeatId();
    }

    @Override
    public Class<Integer> keyType() {
        return Integer.class;

    }
}
