package ParkingLot.reposetories;

import ParkingLot.models.Gate;
import ParkingLot.models.ParkingLot;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

public class ParkingLotReposetory {
    private Map<Long,ParkingLot> parkingLots = new HashMap<>();
    Long lastsaved = 0L;
//    public Optional<ParkingLot> getParkingLotOfGate(Gate gate){
//        for (ParkingLot parkingLot : parkingLots.values()){
//            if (parkingLot.getGates().contains(gate)) {
//                return Optional.of(parkingLot);
//            }
//        }
//        return Optional.empty();
//    }

    public Optional<ParkingLot> getParkingLotById(Long parkingLotId){
            if (parkingLots.containsKey(parkingLotId)) {
                return Optional.of(parkingLots.get(parkingLotId));
            }
            return Optional.empty();
    }

    public ParkingLot put(ParkingLot parkingLot){
        parkingLot.setId(lastsaved + 1);
        parkingLots.put(lastsaved + 1,parkingLot);
        lastsaved += 1;
        return parkingLots.get(lastsaved);
    }
}
