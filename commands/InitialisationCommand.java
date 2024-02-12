package ParkingLot.commands;

import ParkingLot.controllers.InitialiseParkingLotController;
import ParkingLot.dtos.requestDto.InitialiseParkingLotRequestDto;
import ParkingLot.dtos.responseDto.InitialiseParkingLotResponseDto;
import ParkingLot.reposetories.*;
import ParkingLot.services.InitializationSevice;

import java.util.Scanner;

public class InitialisationCommand implements Command {
    private InitialiseParkingLotController initialiseParkingLotController;
    private Scanner sc;



    public InitialisationCommand(ParkingLotReposetory parkingLotReposetory, OperatorReposetory operatorReposetory, GateReposetory gateReposetory, ParkingFloorReposetory parkingFloorReposetory, ParkingSpotReposetory parkingSpotReposetory) {
        this.initialiseParkingLotController = new InitialiseParkingLotController(new InitializationSevice(parkingLotReposetory, operatorReposetory, gateReposetory, parkingFloorReposetory, parkingSpotReposetory));
        sc = new Scanner(System.in);
    }



    @Override
    public boolean matches(String input) {
        if (input.equals(CommandKeywords.CREATE_PARKING_LOT)){
            return true;
        }
        return false;
    }




    @Override
    public void execute(){
        InitialiseParkingLotRequestDto request = new InitialiseParkingLotRequestDto();
        System.out.println("Enter the Entry GateNo: ");
        String entryGateNoInput = sc.next();
        int entryGateNo = Integer.parseInt(entryGateNoInput);
        request.setEntryGateNo(entryGateNo);
        System.out.println("Enter the Exit GateNo: ");
        String exitGateNoInput = sc.next();
        int exitGateNo = Integer.parseInt(exitGateNoInput);
        request.setExitGateNo(exitGateNo);
        System.out.println("Enter the Entry Gate Operator Name: ");
        request.setEntryGateOperatorName(sc.next());
        System.out.println("Enter the Exit Gate Operator Name: ");
        request.setExitGateOperatorName(sc.next());
        System.out.println("Enter the no of floors: ");
        String floorsInput = sc.next();
        int noOfFloors = Integer.parseInt(floorsInput);
        request.setNoOfFloors(noOfFloors);
        System.out.println("Enter the no of spots in each floor: ");
        String spotInput = sc.next();
        int noOfSpots = Integer.parseInt(spotInput);
        request.setNoOfSpots(noOfSpots);
        InitialiseParkingLotResponseDto response = initialiseParkingLotController.createParkingLot(request);
        System.out.println("Your Parking Lot is initialised, Your Parking Id is :" + response.getParkingLotId());
    }

}
