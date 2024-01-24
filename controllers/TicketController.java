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
        String vehicleNo = request.getVehicleNo();
        VehicleType vehicleType = request.getVehicleType();
        Long gateId = request.getGateId();

        GenerateTicketResponseDto response = new GenerateTicketResponseDto();
        Ticket ticket = new Ticket();
        try{
            ticket = ticketService.generateTicket(vehicleNo,vehicleType,gateId);
        }catch(InvalidGateException e){
            response.setResponseStatus(ResponseStatus.FAILURE);
            response.setMessage("Gate ID is INvalid");
            return response;
        }catch (NoAvailableParkingSpotException e){
            response.setResponseStatus(ResponseStatus.FAILURE);
            response.setMessage("No Parking Available");
            return response;
        }

        response = new GenerateTicketResponseDto();
        response.setResponseStatus(ResponseStatus.SUCESS);
        response.setTicketID(ticket.getId());
        response.setSpotNo(ticket.getParkingSpot().getSpotNumber());
        response.setOperatorName(ticket.getOperator().getName());
        return response;
    }

}
