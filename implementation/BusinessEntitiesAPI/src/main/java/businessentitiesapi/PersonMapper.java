package businessentitiesapi;

import genericmapper.Mapper;

import java.util.function.Function;

public class PersonMapper extends Mapper<Person, Integer> {


    // No public ctor 
    private PersonMapper() {
        super(Person.class, java.lang.invoke.MethodHandles.lookup());
    }

    // self register
    static {
        Mapper.register(new PersonMapper());
    }

    // the method that it is all about
    //first_name, last_name, email, birth_date, gender
    @Override
    public Object[] deconstruct(Person person) {
        return new Object[]{
                person.getPersonId(),
                person.getFirstName(),
                person.getLastName(),
                person.getGender(),
                person.getEmail(),
                person.getBirthDate()
        };
    }

    @Override
    public Function<Person, Integer> keyExtractor() {
        return Person::getPersonId;
    }

    @Override
    public Class<Integer> keyType() {
        return Integer.class;

    }
}

