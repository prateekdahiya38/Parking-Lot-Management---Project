package ParkingLot.dtos.responseDto;

import java.time.LocalDateTime;

public class BillGenerateResponseDto {
    private int amount;

    private LocalDateTime exitTime;
    private int GateNo;
    private String operatorName;

    private String status;
    private String message;


    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public LocalDateTime getExitTime() {
        return exitTime;
    }

    public void setExitTime(LocalDateTime exitTime) {
        this.exitTime = exitTime;
    }

    public int getGateNo() {
        return GateNo;
    }

    public void setGateNo(int gateNo) {
        GateNo = gateNo;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
