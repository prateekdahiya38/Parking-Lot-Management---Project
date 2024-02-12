package ParkingLot.reposetories;

import ParkingLot.models.ParkingFloor;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

public class ParkingFloorReposetory {
    private Map<Long, ParkingFloor> parkingFloors = new HashMap<>();

    public Optional<ParkingFloor> findFloorById(Long floorId){
        if (parkingFloors.containsKey(floorId)){
            return Optional.of(parkingFloors.get(floorId));
        }
        return Optional.empty();
    }

    public void put(ParkingFloor parkingFloor){
        parkingFloors.put(parkingFloor.getId(),parkingFloor);
    }
}
