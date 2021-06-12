package businesslogic;

import businessentitiesapi.Ticket;
import genericdao.dao.DAO;
import genericdao.dao.DAOFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

/**
 * @author Benjamin Swiezy {@code b.swiezy@student.fontys.nl}
 */

public class TicketManagerTest {

    @Mock
    DAOFactory daoF;
    DAO<Ticket, Serializable> dao;

    TicketManagerImpl tmi;
    Ticket t1, t2, t3;
    List<Ticket> tickets;

    @BeforeEach
    public void setupMock() {
        tmi = new TicketManagerImpl();
        daoF = mock(DAOFactory.class);
        dao = mock(DAO.class);

        tmi.setDaoFactory(daoF);
        when(daoF.createDao(Ticket.class)).thenReturn(dao);
        //when(dao.getAll()).thenReturn(tickets);


//        TicketManager ticketManager = mock(TicketManager.class);
//        when(ticketManager.getTickets()).thenReturn(tickets);

        t1 = new Ticket(1, 1, 3, 3, 5, 2, 2, false,  BigDecimal.valueOf(150));
        t2 = new Ticket(2, 2, 1, 2, 7, 1, 2, false, BigDecimal.valueOf(100));
        t3 = new Ticket(3, 3, 1, 5, 9, 2, 1, false, BigDecimal.valueOf(50));
        tickets = Arrays.asList(t1, t2, t3);

        tmi.setTicketStorageService(null, daoF);
    }


    @Test
    public void tCreateTicket() {
        var testTicket = tmi.createTicket(
                1,
                1,
                3,
                3,
                5,
                2,
                false,
                2,
                BigDecimal.valueOf(150)
        );
        assertThat(testTicket).hasToString(t1.toString());
    }

    @Test
    public void tRightSumOfTickets() {
        when(dao.getAll()).thenReturn(tickets);
        var sum = tmi.getSumOfTicketPrices(tickets);
        verify(daoF, times(0)).createDao(Ticket.class);
        verify(dao, times(0)).getAll();
        assertThat(sum).isEqualTo(BigDecimal.valueOf(300));
    }

    @Test
    public void tDeleteFailed(){
        doThrow(new RuntimeException()).when(dao).deleteEntity(any());

        assertThat(tmi.delete(any())).isFalse();
        verify(daoF).createDao(Ticket.class);
        verify(dao).deleteEntity(any());
    }

    @Test
    public void tGetAll(){
        when(dao.getAll()).thenReturn(tickets);
        var tList = tmi.getTickets();
        verify(dao).getAll();
        assertThat(tList).containsSequence(tickets);
    }

    @Test
    public void tSizeOfList(){
        when(dao.getAll()).thenReturn(tickets);
        var size = tmi.getTickets().size();
        verify(daoF).createDao(Ticket.class);
        verify(dao).getAll();
        assertThat(size).isEqualTo(3);
    }

    @Test
    public void tDelete() {
        var tof = tmi.delete(t1);
        verify(daoF).createDao(Ticket.class);
        verify(dao).deleteEntity(t1);
        assertThat(tof).isTrue();
    }

    @Test
    public void tAddTicket(){
        when(dao.save(t1)).thenReturn(Optional.of(t1));
        tmi.add(t1);
        verify(daoF).createDao(Ticket.class);
        verify(dao).save(t1);
    }

    @Test
    public void tGetCheckedBaggageAmount(){
        assertThat(tmi.getCheckedBaggageAmount(tickets)).isEqualTo(10);
    }

    @Test
    public void tGetCabinBaggageAmount(){
        assertThat(tmi.getCabinBaggageAmount(tickets)).isEqualTo(5);
    }

     @Test
    public void tGetTicketsOfBooking(){
        when(dao.getByColumnValues(anyString(), any())).thenReturn(Collections.singletonList(t2));
        var bookings = tmi.getTicketsOfBooking(1);
        verify(daoF).createDao(Ticket.class);
        verify(dao).getByColumnValues(anyString(), any());
        assertThat(bookings).containsSequence(t2);
     }



}
