package ParkingLot.controllers;

import ParkingLot.dtos.requestDto.BillGenerateRequestDto;
import ParkingLot.dtos.responseDto.BillGenerateResponseDto;
import ParkingLot.models.Bill;
import ParkingLot.services.BillService;

public class BillController {
    private BillService billService;

    public BillController(BillService billService) {
        this.billService = billService;
    }

    public BillGenerateResponseDto billGenerate(BillGenerateRequestDto request){
        BillGenerateResponseDto response = new BillGenerateResponseDto();
        Bill bill;
        try {
            bill = billService.billGenerate(request.getTicketID());
            response.setAmount(bill.getAmount());
            response.setGateNo(bill.getGate().getGateNumber());
            response.setOperatorName(bill.getOperator().getName());
            response.setExitTime(bill.getExitTime());
            response.setStatus("SUCCESS");
            response.setMessage("Bill is generated successfully");
        }catch (Exception e){
            response.setStatus("FAILURE");
            response.setStatus("Ticket not found");
        }
        return response;
    }
}
