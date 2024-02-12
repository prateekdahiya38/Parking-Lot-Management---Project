package ParkingLot.services;

import ParkingLot.exceptions.TicketNotFoundException;
import ParkingLot.models.*;
import ParkingLot.reposetories.*;
import ParkingLot.strategies.feesCalculatorStrategy.DefaultFeesCalculatorStrategy;
import ParkingLot.strategies.feesCalculatorStrategy.FeesCalculatorStrategy;

import javax.xml.crypto.Data;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

public class BillService {
    private TicketReposetory ticketReposetory;
    private FeesCalculatorStrategy feesCalculatorStrategy;
    private BillReposetory billReposetory;


    public BillService(TicketReposetory ticketReposetory,
                       BillReposetory billReposetory,
                       FeesCalculatorStrategy feesCalculatorStrategy) {
        this.ticketReposetory = ticketReposetory;
        this.feesCalculatorStrategy = feesCalculatorStrategy;
        this.billReposetory = billReposetory;
    }

    public Bill billGenerate(Long ticketId) throws TicketNotFoundException {

        Optional<Ticket> ticketOptional = ticketReposetory.getTicketById(ticketId);
        if (ticketOptional.isEmpty()){
            throw new TicketNotFoundException();
        }
        Ticket ticket = ticketOptional.get();
        ticket.getParkingSpot().setSpotStatus(SpotStatus.AVAILABLE);

        ParkingLot parkingLot = ticket.getParkingLot();

        Gate exitGate = parkingLot.getGates().get(1);

        Operator operator = exitGate.getCurrentOperator();

        int totalAmount = feesCalculatorStrategy.feeCalculator(ticket);

        Bill bill = new Bill();
        bill.setAmount(totalAmount);
        bill.setGate(exitGate);
        bill.setTicket(ticket);
        bill.setExitTime(LocalDateTime.now());
        bill.setOperator(operator);
        return billReposetory.save(bill);

    }
}
