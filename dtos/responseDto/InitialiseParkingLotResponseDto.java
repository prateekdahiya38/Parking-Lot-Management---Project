package ParkingLot.dtos.responseDto;

public class InitialiseParkingLotResponseDto {
    private Long parkingLotId;
    private Long entryGateId;
    private Long exitGateId;
    private String message;


    public Long getEntryGateId() {
        return entryGateId;
    }

    public void setEntryGateId(Long entryGateId) {
        this.entryGateId = entryGateId;
    }

    public Long getExitGateId() {
        return exitGateId;
    }

    public void setExitGateId(Long exitGateId) {
        this.exitGateId = exitGateId;
    }

    public Long getParkingLotId() {
        return parkingLotId;
    }

    public void setParkingLotId(Long parkingLotId) {
        this.parkingLotId = parkingLotId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
