package businessentitiesapi;

import genericmapper.Mapper;

import java.util.function.Function;

public class AirplaneScheduleMapper extends Mapper<AirplaneSchedule, String> {


    // No public ctor
    private AirplaneScheduleMapper() {
        super(AirplaneSchedule.class, java.lang.invoke.MethodHandles.lookup());
    }

    // self register
    static {
        Mapper.register(new AirplaneScheduleMapper());
    }

    // the method that it is all about
    @Override
    public Object[] deconstruct(AirplaneSchedule as) {
        return new Object[]{
                as.getAirplaneCode(),
                as.getDepartureTime(),
                as.getArrivalTime()
        };
    }

    @Override
    public Function<AirplaneSchedule, String> keyExtractor() {
        return AirplaneSchedule::getAirplaneCode;
    }

    @Override
    public Class<String> keyType() {
        return String.class;

    }
}