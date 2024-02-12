package ParkingLot.models;

public class ParkingSpot extends BaseModel{
    private int spotNumber;
    private VehicleType supportedVehicle;
    private Vehicle vehicle;
    private SpotStatus spotStatus;

    public int getSpotNumber() {
        return spotNumber;
    }

    public void setSpotNumber(int spotNumber) {
        this.spotNumber = spotNumber;
    }

    public VehicleType getSupportedVehicle() {

        return supportedVehicle;
    }

    public void setSupportedVehicle(VehicleType supportedVehicle) {
        this.supportedVehicle = supportedVehicle;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public SpotStatus getSpotStatus() {
        return spotStatus;
    }

    public void setSpotStatus(SpotStatus spotStatus) {
        this.spotStatus = spotStatus;
    }
}
