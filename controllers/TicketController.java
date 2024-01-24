package ParkingLot.controllers;

import ParkingLot.DTOs.GenerateTicketRequestDto;
import ParkingLot.DTOs.GenerateTicketResponseDto;
import ParkingLot.DTOs.ResponseStatus;
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



    public GenerateTicketResponseDto generateTicket(GenerateTicketRequestDto request) throws InvalidGateException, NoAvailableParkingSpotException {
    //  check the request come from client in the form of DTO
        String vehicleNo = request.getVehicleNo();
        VehicleType vehicleType = request.getVehicleType();
        Long gateId = request.getGateId();

    //  initialize a response object for the client in the form of DTO
        GenerateTicketResponseDto response;
    // create a ticket object to check
        Ticket ticket = new Ticket();

    // check if the perameters are valid or not, if not throw exception and return the fail response.
    // if valid, then call the ticket from the ticket service
        try{
            ticket = ticketService.generateTicket(vehicleNo,vehicleType,gateId);
        }catch(InvalidGateException e){
            response = new GenerateTicketResponseDto();
            response.setResponseStatus(ResponseStatus.FAILURE);
            response.setMessage("Gate ID is Invalid");
            return response;
        }catch (NoAvailableParkingSpotException e){
            response = new GenerateTicketResponseDto();
            response.setResponseStatus(ResponseStatus.FAILURE);
            response.setMessage("No Parking Available");
            return response;
        }

     // after successfull validation create a response object and fill the details inside it by using ticket and return the successfull response
        response = new GenerateTicketResponseDto();
        response.setResponseStatus(ResponseStatus.SUCESS);
        response.setTicketID(ticket.getId());
        response.setSpotNo(ticket.getParkingSpot().getSpotNumber());
        response.setOperatorName(ticket.getOperator().getName());
        return response;
    }

}
