package ParkingLot.reposetories;

import ParkingLot.models.Vehicle;

import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

public class VehicleReposetory {

    Map<String,Vehicle> vehicles= new TreeMap<>();

    public Optional<Vehicle> findVeicleByVehicleNo(String vehicleNo){
        if (vehicles.containsKey(vehicleNo)){
            return Optional.of(vehicles.get(vehicleNo));
        }
        return Optional.empty();
    }

    public Vehicle save(Vehicle vehicle) {
        vehicles.put(vehicle.getVehicleNumber(),vehicle);
        return null;
    }
}
