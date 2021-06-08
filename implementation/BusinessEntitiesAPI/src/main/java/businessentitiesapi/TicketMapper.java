
package businessentitiesapi;

import genericmapper.Mapper;

import java.util.function.Function;

/**
 * @author Rachel
 * @author Davis
 */
public class TicketMapper extends Mapper<Ticket, Integer> {


    // No public ctor 
    private TicketMapper() {
        super(Ticket.class, java.lang.invoke.MethodHandles.lookup());
    }

    // self register
    static {
        Mapper.register(new TicketMapper());
    }

    // the method that it is all about
    @Override
    public Object[] deconstruct(Ticket t) {
        return new Object[]{
                t.getTicketId(),
                t.getFlightSeatId(),
                t.getMealId(),
                t.getBookingId(),
                t.getPassengerId(),
                t.getCabinBaggage(),
                t.getCheckedBaggage(),
                t.isCanceled(),
                t.getPricePaid()
        };
    }

    @Override
    public Function<Ticket, Integer> keyExtractor() {
        return Ticket::getTicketId;
    }

    @Override
    public Class<Integer> keyType() {
        return Integer.class;

    }
}