package businessentitiesapi;


import genericmapper.Mapper;

import java.util.function.Function;

public class PassengerMapper extends Mapper<Passenger, Integer> {


    // No public ctor 
    private PassengerMapper() {
        super(Passenger.class, java.lang.invoke.MethodHandles.lookup());
    }

    // self register
    static {
        Mapper.register(new PassengerMapper());
    }

    // the method that it is all about
    @Override
    public Object[] deconstruct(Passenger passenger) {
        return new Object[]{
                passenger.getPassengerId(),
                passenger.getPassportNumber(),
                passenger.getPersonId()

        };
    }

    @Override
    public Function<Passenger, Integer> keyExtractor() {
        return Passenger::getPassengerId;
    }

    @Override
    public Class<Integer> keyType() {
        return Integer.class;

    }
}
