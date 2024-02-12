package ParkingLot.dtos.requestDto;

public class InitialiseParkingLotRequestDto {
    private String entryGateOperatorName;
    private String exitGateOperatorName;
    private int entryGateNo;
    private int exitGateNo;
    private int noOfFloors;
    private int noOfSpots;

    public String getEntryGateOperatorName() {
        return entryGateOperatorName;
    }

    public void setEntryGateOperatorName(String entryGateOperatorName) {
        this.entryGateOperatorName = entryGateOperatorName;
    }

    public String getExitGateOperatorName() {
        return exitGateOperatorName;
    }

    public void setExitGateOperatorName(String exitGateOperatorName) {
        this.exitGateOperatorName = exitGateOperatorName;
    }

    public int getEntryGateNo() {
        return entryGateNo;
    }

    public void setEntryGateNo(int entryGateNo) {
        this.entryGateNo = entryGateNo;
    }

    public int getExitGateNo() {
        return exitGateNo;
    }

    public void setExitGateNo(int exitGateNo) {
        this.exitGateNo = exitGateNo;
    }

    public int getNoOfFloors() {
        return noOfFloors;
    }

    public void setNoOfFloors(int noOfFloors) {
        this.noOfFloors = noOfFloors;
    }

    public int getNoOfSpots() {
        return noOfSpots;
    }

    public void setNoOfSpots(int noOfSpots) {
        this.noOfSpots = noOfSpots;
    }
}
