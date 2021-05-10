
package businessentitiesapi;

import genericmapper.Mapper;

import java.util.function.Function;

/**
 * @author Rachel
 */
public class AirplaneMapper extends Mapper<Airplane, Integer> {


    // No public ctor
    private AirplaneMapper() {
        super(Airplane.class, java.lang.invoke.MethodHandles.lookup());
    }

    // self register
    static {
        Mapper.register(new AirplaneMapper());
    }

    // the method that it is all about
    @Override
    public Object[] deconstruct(Airplane a) {
        return new Object[]{
                a.getAirplaneID(),
                a.getModel(),
                a.getAirplaneCode(),
                a.getCapacity()
        };
    }

    @Override
    public Function<Airplane, Integer> keyExtractor() {
        return Airplane::getAirplaneID;
    }

    @Override
    public Class<Integer> keyType() {
        return Integer.class;

    }
}