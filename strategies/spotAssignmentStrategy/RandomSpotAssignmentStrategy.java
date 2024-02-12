package ParkingLot.strategies.spotAssignmentStrategy;

import ParkingLot.models.*;

import java.util.Optional;

public class RandomSpotAssignmentStrategy implements SpotAssignmentStrategy{
    @Override
    public Optional<ParkingSpot> findSpot(ParkingLot parkingLot, Gate entryGate, VehicleType vehicleType) {
        for (ParkingFloor parkingFloor : parkingLot.getFloors()){
            for(ParkingSpot parkingSpot : parkingFloor.getParkingSpots()){
                if (parkingSpot.getSpotStatus().equals(SpotStatus.AVAILABLE) && parkingSpot.getSupportedVehicle().equals(vehicleType)){
                    parkingSpot.setSpotStatus(SpotStatus.PARKED);
                    return Optional.of(parkingSpot);
                }
            }
        }
        return Optional.empty();
    }

    public void releaseSpot(ParkingSpot parkingSpot){
        parkingSpot.setSpotStatus(SpotStatus.AVAILABLE);
    }
}
