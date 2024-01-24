package ParkingLot.reposetories;

import ParkingLot.models.Gate;
import ParkingLot.models.ParkingLot;

import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

public class ParkingLotReposetory {
    private Map<Long,ParkingLot> parkingLots = new TreeMap<>();
    public Optional<ParkingLot> getParkingLotofGate(Gate gate){
        for (ParkingLot parkingLot : parkingLots.values()){
            return Optional.of(parkingLot);
        }
        return Optional.empty();
    }
}
