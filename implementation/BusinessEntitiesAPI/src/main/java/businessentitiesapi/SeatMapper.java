package businessentitiesapi;

import genericmapper.Mapper;

import java.util.function.Function;

public class SeatMapper extends Mapper<Seat, Integer> {

    // No public ctor
    private SeatMapper() {
        super(Seat.class, java.lang.invoke.MethodHandles.lookup());
    }

    // self register
    static {
        Mapper.register(new SeatMapper());
    }

    // the method that it is all about
    @Override
    public Object[] deconstruct(Seat s) {
        return new Object[]{
                s.getSeatId(),
                s.getSeatTypeId(),
                s.getSeatNumber(),
                s.getAirplaneId()
        };
    }

    @Override
    public Function<Seat, Integer> keyExtractor() {
        return (Seat s) -> s.getSeatId();
    }

    @Override
    public Class<Integer> keyType() {
        return Integer.class;

    }
}
