package ParkingLot.reposetories;

import ParkingLot.models.Gate;
import ParkingLot.models.ParkingLot;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

public class GateReposetory {
    private Map<Long,Gate> gates = new HashMap<>();

    public Optional<Gate> findGateById(Long gateID){
       if (gates.containsKey(gateID)){
           return Optional.of(gates.get(gateID));
       }
       return Optional.empty();
    }

    public void put(Gate gate){
        gates.put(gate.getId(),gate);
    }
}
