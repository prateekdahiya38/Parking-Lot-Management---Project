package ParkingLot;

import ParkingLot.controllers.TicketController;
import ParkingLot.reposetories.GateReposetory;
import ParkingLot.reposetories.ParkingLotReposetory;
import ParkingLot.reposetories.TicketReposetory;
import ParkingLot.reposetories.VehicleReposetory;
import ParkingLot.services.TicketService;
import ParkingLot.spotAssignmentStrategy.RandomSpotAssignmentStrategy;
import ParkingLot.spotAssignmentStrategy.SpotAssignmentStrategy;

public class ParkingLotApp {
    public static void main(String[] args) {
        GateReposetory gateReposetory = new GateReposetory();
        ParkingLotReposetory parkingLotReposetory = new ParkingLotReposetory();
        TicketReposetory ticketReposetory = new TicketReposetory();
        VehicleReposetory vehicleReposetory = new VehicleReposetory();
        SpotAssignmentStrategy spotAssignmentStrategy = new RandomSpotAssignmentStrategy();


        TicketService ticketService = new TicketService(gateReposetory,vehicleReposetory,spotAssignmentStrategy,parkingLotReposetory,ticketReposetory);



        TicketController ticketController = new TicketController(ticketService);
        System.out.println("Application started has on port : 8080");
    }
}
