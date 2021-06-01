package persistence;

import businessentitiesapi.Ticket;

import java.util.List;

public interface TicketStorageService {

    Ticket add(Ticket t);
    List<Ticket> getAll();
}
