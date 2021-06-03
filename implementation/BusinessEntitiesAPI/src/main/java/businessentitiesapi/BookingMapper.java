
package businessentitiesapi;

import genericmapper.Mapper;

import java.util.function.Function;

/**
 * @author Benjamin
 */
public class BookingMapper extends Mapper<Booking, Integer> {


    private BookingMapper() {
        super(Booking.class, java.lang.invoke.MethodHandles.lookup());
    }

    // self register
    static {
        Mapper.register(new BookingMapper());
    }

    // the method that it is all about
    @Override
    public Object[] deconstruct(Booking b) {
        return new Object[]{
                b.getBookingId(),
                b.getPersonId(),
                b.getUserId(),
                b.getTimeOfBooking()
        };
    }

    @Override
    public Function<Booking, Integer> keyExtractor() {
        return Booking::getBookingId;
    }

    @Override
    public Class<Integer> keyType() {
        return Integer.class;

    }
}