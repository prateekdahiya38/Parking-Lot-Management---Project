package ParkingLot.controllers;

import ParkingLot.dtos.requestDto.InitialiseParkingLotRequestDto;
import ParkingLot.dtos.responseDto.InitialiseParkingLotResponseDto;
import ParkingLot.models.Gate;
import ParkingLot.models.ParkingLot;
import ParkingLot.services.InitializationSevice;

public class InitialiseParkingLotController {
    private InitializationSevice initializationSevice;

    public InitialiseParkingLotController(InitializationSevice initializationSevice) {
        this.initializationSevice = initializationSevice;
    }

    public InitialiseParkingLotResponseDto createParkingLot(InitialiseParkingLotRequestDto request){

        String entryGateOperatorName = request.getEntryGateOperatorName();
        String exitGateOperatorName = request.getExitGateOperatorName();
        int noOfFloors = request.getNoOfFloors();;
        int noOfSpots = request.getNoOfSpots();
        int entryGateNo = request.getEntryGateNo();
        int exitGateNo = request.getExitGateNo();

        InitialiseParkingLotResponseDto response = new InitialiseParkingLotResponseDto();
        ParkingLot parkingLot = initializationSevice.initialize(entryGateOperatorName,exitGateOperatorName,noOfFloors,noOfSpots,entryGateNo,exitGateNo);
        Gate entryGate = parkingLot.getGates().get(0);
        Gate exitGate = parkingLot.getGates().get(1);
        response.setParkingLotId(parkingLot.getId());
        response.setEntryGateId(entryGate.getId());
        response.setExitGateId(exitGate.getId());
        response.setMessage("Parking lot of " + parkingLot.getId() + " is created");
        return response;
    }
}
