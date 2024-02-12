package ParkingLot.reposetories;

import ParkingLot.models.Ticket;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

public class TicketReposetory  {
    private Map<Long, Ticket> tickets = new HashMap<>();
    private long lastSavedId = 0L;


    public Optional<Ticket> getTicketById(Long ticketId){
        if (tickets.containsKey(ticketId)){
            return Optional.of(tickets.get(ticketId));
        }
        return Optional.empty();
    }

    public Ticket save(Ticket ticket){
        ticket.setId(lastSavedId+1);
        lastSavedId += 1;

        tickets.put(lastSavedId,ticket);
        return ticket;
    }
}
