package ParkingLot.commands;

import ParkingLot.reposetories.*;
import ParkingLot.strategies.feesCalculatorStrategy.DefaultFeesCalculatorStrategy;
import ParkingLot.strategies.feesCalculatorStrategy.FeesCalculatorStrategy;
import ParkingLot.strategies.spotAssignmentStrategy.RandomSpotAssignmentStrategy;
import ParkingLot.strategies.spotAssignmentStrategy.SpotAssignmentStrategy;

import java.util.ArrayList;
import java.util.List;

public class CommandRegistry {
    private List<Command> commands;
    private ParkingLotReposetory parkingLotReposetory;
    private OperatorReposetory operatorReposetory;
    private GateReposetory gateReposetory;
    private ParkingFloorReposetory parkingFloorReposetory;
    private ParkingSpotReposetory parkingSpotReposetory;
    private VehicleReposetory vehicleReposetory;
    private SpotAssignmentStrategy spotAssignmentStrategy;
    private TicketReposetory ticketReposetory;
    private BillReposetory billReposetory;
    private FeesCalculatorStrategy feesCalculatorStrategy;




    public CommandRegistry() {
        parkingLotReposetory = new ParkingLotReposetory();
        operatorReposetory = new OperatorReposetory();
        gateReposetory = new GateReposetory();
        parkingFloorReposetory = new ParkingFloorReposetory();
        parkingSpotReposetory = new ParkingSpotReposetory();
        vehicleReposetory = new VehicleReposetory();
        spotAssignmentStrategy = new RandomSpotAssignmentStrategy();
        ticketReposetory = new TicketReposetory();
        billReposetory = new BillReposetory();
        feesCalculatorStrategy = new DefaultFeesCalculatorStrategy();
        commands = new ArrayList<>();
        commands.add(new InitialisationCommand(parkingLotReposetory, operatorReposetory, gateReposetory, parkingFloorReposetory, parkingSpotReposetory));
        commands.add(new GenerateTicketCommand(gateReposetory, vehicleReposetory, spotAssignmentStrategy, parkingLotReposetory, ticketReposetory));
        commands.add(new GenerateBillCommand(ticketReposetory, billReposetory, feesCalculatorStrategy));
    }

    public void execute(String input){
        for (Command command : commands){
            if (command.matches(input)){
                command.execute();
                break;
            }
        }
    }
}
