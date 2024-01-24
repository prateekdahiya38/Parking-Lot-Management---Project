package ParkingLot.services;

import ParkingLot.exceptions.InvalidGateException;
import ParkingLot.exceptions.NoAvailableParkingSpotException;
import ParkingLot.models.*;
import ParkingLot.reposetories.GateReposetory;
import ParkingLot.reposetories.ParkingLotReposetory;
import ParkingLot.reposetories.TicketReposetory;
import ParkingLot.reposetories.VehicleReposetory;
import ParkingLot.strategies.spotAssignmentStrategy.SpotAssignmentStrategy;

import java.util.Date;
import java.util.Optional;

public class TicketService {
    private GateReposetory gateReposetory;
    private VehicleReposetory vehicleReposetory;
    private SpotAssignmentStrategy spotAssignmentStrategy;
    private ParkingLotReposetory parkingLotReposetory;
    private TicketReposetory ticketReposetory;

    public TicketService(GateReposetory gateReposetory,
                         VehicleReposetory vehicleReposetory,
                         SpotAssignmentStrategy spotAssignmentStrategy,
                         ParkingLotReposetory parkingLotReposetory,
                         TicketReposetory ticketReposetory) {
        this.gateReposetory = gateReposetory;
        this.vehicleReposetory = vehicleReposetory;
        this.spotAssignmentStrategy = spotAssignmentStrategy;
        this.parkingLotReposetory = parkingLotReposetory;
        this.ticketReposetory = ticketReposetory;

    }

    public Ticket generateTicket(String vehicleNo, VehicleType vehicleType, Long gateId) throws InvalidGateException, NoAvailableParkingSpotException {
        // Vehicle check if present in DB and get otherwise create
        // Gate check in database otherwise exception throw
        // operator from gate
        // parking spot from strategy


        // finding gate in the reposetory or Database, if not fount throw exception
           Optional<Gate> gateOptional= gateReposetory.findGateById(gateId);
           if (gateOptional.isEmpty()){
               throw new InvalidGateException();
           }
           Gate gate = gateOptional.get();



       // finding Current operator on the gate we found from the reposetory
        Operator operator = gate.getCurrentOperator();



      // finding vehicle in reposetory or database, if not add the entry of a new vehicle in it
        Vehicle vehicle;
        Optional<Vehicle> vehicleOptional = vehicleReposetory.findVeicleByVehicleNo(vehicleNo);
        if (vehicleOptional.isEmpty()){
            vehicle = new Vehicle();
            vehicle.setVehicleNumber(vehicleNo);
            vehicle.setVehicleType(vehicleType);
            vehicleReposetory.save(vehicle);
        }else {
            vehicle = vehicleOptional.get();
        }


      // finding ParkingLot in the reposetory or database by gate, if not found throw exception
        ParkingLot parkingLot;
        Optional<ParkingLot> parkingLotOptional = parkingLotReposetory.getParkingLotOfGate(gate);
        if(parkingLotOptional.isEmpty()){
            throw new RuntimeException();
        }else {
            parkingLot = parkingLotOptional.get();
        }


      // finding Parking spot in the spot assignment strategy
        ParkingSpot parkingSpot;
        Optional<ParkingSpot> parkingSpotOptional = spotAssignmentStrategy.findSpot(parkingLot,gate,vehicleType);
        if (parkingSpotOptional.isEmpty()){
            throw new NoAvailableParkingSpotException();
        }else {
            parkingSpot = parkingSpotOptional.get();
        }


     // generate ticket, and save it in the reposetory and return it to the controller
        Ticket ticket = new Ticket();
        ticket.setGate(gate);
        ticket.setEntryTime(new Date());
        ticket.setOperator(operator);
        ticket.setVehicle(vehicle);
        ticket.setParkingSpot(parkingSpot);
        return ticketReposetory.save(ticket);
    }
}
