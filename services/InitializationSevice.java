package ParkingLot.services;

import ParkingLot.exceptions.ParkingLotAlreadyExist;
import ParkingLot.models.*;
import ParkingLot.reposetories.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InitializationSevice {
    private ParkingLotReposetory parkingLotReposetory;
    private OperatorReposetory operatorReposetory;
    private GateReposetory gateReposetory;
    private ParkingFloorReposetory parkingFloorReposetory;
    private ParkingSpotReposetory parkingSpotReposetory;


    public InitializationSevice(ParkingLotReposetory parkingLotReposetory,
                                OperatorReposetory operatorReposetory,
                                GateReposetory gateReposetory,
                                ParkingFloorReposetory parkingFloorReposetory,
                                ParkingSpotReposetory parkingSpotReposetory) {
        this.parkingLotReposetory = parkingLotReposetory;
        this.operatorReposetory = operatorReposetory;
        this.gateReposetory = gateReposetory;
        this.parkingFloorReposetory = parkingFloorReposetory;
        this.parkingSpotReposetory = parkingSpotReposetory;
    }

    public ParkingLot initialize(String entryGateOperatorName,
                                 String exitGateOperatorName,
                                 int noOfFloors,
                                 int noOfSpots,
                                 int entryGateNo,
                                 int exitGateNo){


        Operator entryGateOperator = new Operator();
        entryGateOperator.setId(1L);
        entryGateOperator.setName(entryGateOperatorName);
        entryGateOperator.setEmployeeId(1);
        operatorReposetory.put(entryGateOperator);

        Operator exitGateOperator = new Operator();
        exitGateOperator.setId(2L);
        exitGateOperator.setName(exitGateOperatorName);
        exitGateOperator.setEmployeeId(2);
        operatorReposetory.put(exitGateOperator);


        Gate entryGate = new Gate();
        entryGate.setId(1L);
        entryGate.setGateNumber(entryGateNo);
        entryGate.setGateStatus(GateStatus.OPEN);
        entryGate.setGateType(GateType.ENTRY);
        entryGate.setCurrentOperator(entryGateOperator);
        gateReposetory.put(entryGate);

        Gate exitGate = new Gate();
        exitGate.setId(2L);
        exitGate.setGateNumber(exitGateNo);
        exitGate.setGateStatus(GateStatus.OPEN);
        exitGate.setGateType(GateType.EXIT);
        exitGate.setCurrentOperator(exitGateOperator);
        gateReposetory.put(exitGate);


         List<ParkingFloor> parkingFloors = new ArrayList<>();

         for (int i=1; i<=noOfFloors; i++){
             List<ParkingSpot> parkingSpots = new ArrayList<>();
             ParkingFloor parkingFloor = new ParkingFloor();
             long id = 0;
             parkingFloor.setId(id + i);
             parkingFloor.setFloorNumber(i);
             for (int j=1; j<=noOfSpots; j++){
                 ParkingSpot parkingSpot = new ParkingSpot();
                 parkingSpot.setSpotNumber(i*10+j);
                 parkingSpot.setSpotStatus(SpotStatus.AVAILABLE);
                 if (j%2==0){
                     parkingSpot.setSupportedVehicle(VehicleType.CAR);
                 }else {
                     parkingSpot.setSupportedVehicle(VehicleType.BIKE);
                 }
                 parkingSpotReposetory.put(parkingSpot);
                 parkingSpots.add(parkingSpot);
             }
             parkingFloor.setParkingSpots(parkingSpots);
             parkingFloorReposetory.put(parkingFloor);
             parkingFloors.add(parkingFloor);
         }


        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setCapacity(100);
        parkingLot.setGates(List.of(entryGate,exitGate));
        parkingLot.setFloors(parkingFloors);
        return parkingLotReposetory.put(parkingLot);

    }
}
