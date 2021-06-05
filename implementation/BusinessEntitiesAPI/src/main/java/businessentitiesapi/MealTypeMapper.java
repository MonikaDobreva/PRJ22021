
package businessentitiesapi;

import genericmapper.Mapper;

import java.util.function.Function;

/**
 * @author Benjamin
 */
public class MealTypeMapper extends Mapper<MealType, Integer> {


    // No public ctor 
    private MealTypeMapper() {
        super(MealType.class, java.lang.invoke.MethodHandles.lookup());
    }

    // self register
    static {
        Mapper.register(new MealTypeMapper());
    }

    // the method that it is all about
    @Override
    public Object[] deconstruct(MealType mt) {
        return new Object[]{
                mt.getId(),
                mt.getMealName()
        };
    }

    @Override
    public Function<MealType, Integer> keyExtractor() {
        return MealType::getId;
    }

    @Override
    public Class<Integer> keyType() {
        return Integer.class;

    }
}