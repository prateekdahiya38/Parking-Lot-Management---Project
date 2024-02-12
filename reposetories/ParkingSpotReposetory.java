package ParkingLot.reposetories;

import ParkingLot.models.Gate;
import ParkingLot.models.ParkingSpot;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

public class ParkingSpotReposetory {

    private Map<Long, ParkingSpot> parkingSpots = new HashMap<>();

    public Optional<ParkingSpot> findSpotById(Long parkingSpotID){
        if (parkingSpots.containsKey(parkingSpotID)){
            return Optional.of(parkingSpots.get(parkingSpotID));
        }
        return Optional.empty();
    }

    public void put(ParkingSpot parkingSpot){
        parkingSpots.put(parkingSpot.getId(),parkingSpot);
    }
}
