package ParkingLot.controllers;

import ParkingLot.dtos.requestDto.GenerateTicketRequestDto;
import ParkingLot.dtos.responseDto.GenerateTicketResponseDto;
import ParkingLot.dtos.ResponseStatus;
import ParkingLot.exceptions.InvalidGateException;
import ParkingLot.exceptions.NoAvailableParkingSpotException;
import ParkingLot.models.Ticket;
import ParkingLot.models.VehicleType;
import ParkingLot.services.TicketService;

public class TicketController {
    private TicketService ticketService;



    public TicketController(TicketService ticketService) {

        this.ticketService = ticketService;
    }



    public GenerateTicketResponseDto generateTicket(GenerateTicketRequestDto request){
    //  check the request come from client in the form of DTO
        String vehicleNo = request.getVehicleNo();
        VehicleType vehicleType = request.getVehicleType();
//        Long gateId = request.getGateId();
        Long parkingLotId = request.getParkingLotId();

    //  initialize a response object for the client in the form of DTO
        GenerateTicketResponseDto response = new GenerateTicketResponseDto();
    // create a ticket object to check
        Ticket ticket;

    // check if the perameters are valid or not, if not throw exception and return the fail response.
    // if valid, then call the ticket from the ticket service
        try{
            ticket = ticketService.generateTicket(vehicleNo,vehicleType,parkingLotId);
            response.setResponseStatus(ResponseStatus.SUCESS);
            response.setTicketID(ticket.getId());
            response.setSpotNo(ticket.getParkingSpot().getSpotNumber());
            response.setOperatorName(ticket.getOperator().getName());
            response.setGateNo(ticket.getGate().getGateNumber());
            response.setMessage("Your Ticket is created Successfully");
            response.setTime(ticket.getEntryTime());
        }catch(InvalidGateException e){
            response.setResponseStatus(ResponseStatus.FAILURE);
            response.setMessage("Gate ID is Invalid");
        }catch (NoAvailableParkingSpotException e){
            response.setResponseStatus(ResponseStatus.FAILURE);
            response.setMessage("No Parking Available");
        }
        return response;
    }

}
