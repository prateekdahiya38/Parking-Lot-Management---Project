package ParkingLot.services;

import ParkingLot.exceptions.InvalidGateException;
import ParkingLot.exceptions.NoAvailableParkingSpotException;
import ParkingLot.models.*;
import ParkingLot.reposetories.*;
import ParkingLot.strategies.spotAssignmentStrategy.SpotAssignmentStrategy;

import java.time.LocalDateTime;
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



    public Ticket generateTicket(String vehicleNo, VehicleType vehicleType,Long parkingLotId) throws InvalidGateException, NoAvailableParkingSpotException {
        // Vehicle check if present in DB and get otherwise create
        // Gate check in database otherwise exception throw
        // operator from gate
        // parking spot from strategy


        // finding ParkingLot in the reposetory or database by gate, if not found throw exception
        ParkingLot parkingLot;
        Optional<ParkingLot> parkingLotOptional = parkingLotReposetory.getParkingLotById(parkingLotId);
        if(parkingLotOptional.isEmpty()){
            throw new RuntimeException();
        }else {
            parkingLot = parkingLotOptional.get();
        }


        // finding entry gate by parking lot
        Gate gate = parkingLot.getGates().get(0);


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
        ticket.setParkingLot(parkingLot);
        ticket.setGate(gate);
        ticket.setEntryTime(LocalDateTime.now());
        ticket.setOperator(operator);
        ticket.setVehicle(vehicle);
        ticket.setParkingSpot(parkingSpot);
        return ticketReposetory.save(ticket);
    }
}
