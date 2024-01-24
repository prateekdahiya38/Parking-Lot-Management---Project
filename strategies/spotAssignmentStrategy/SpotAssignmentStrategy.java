package ParkingLot.strategies.spotAssignmentStrategy;

import ParkingLot.models.Gate;
import ParkingLot.models.ParkingLot;
import ParkingLot.models.ParkingSpot;
import ParkingLot.models.VehicleType;

import java.util.Optional;

public interface SpotAssignmentStrategy {

    public Optional<ParkingSpot> findSpot(ParkingLot parkingLot, Gate gate, VehicleType vehicleType);
}
