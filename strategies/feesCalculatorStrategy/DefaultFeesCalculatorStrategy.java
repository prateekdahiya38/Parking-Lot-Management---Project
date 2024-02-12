package ParkingLot.strategies.feesCalculatorStrategy;

import ParkingLot.models.Ticket;
import ParkingLot.models.VehicleType;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DefaultFeesCalculatorStrategy implements FeesCalculatorStrategy{
    private static final double BIKE_PRICE_PER_MIN = 0.33;
    private static final double CAR_PRICE_PER_MIN = 1.0;


    @Override
    public int feeCalculator(Ticket ticket) {
        LocalDateTime entryTime = ticket.getEntryTime();
        LocalDateTime exitTime = LocalDateTime.now();

        long noOfMin = ChronoUnit.MINUTES.between(entryTime,exitTime);

        int totalAmount;
        if (ticket.getVehicle().getVehicleType().equals(VehicleType.BIKE)){
            totalAmount = (int)(noOfMin * BIKE_PRICE_PER_MIN);
        }else {
            totalAmount = (int)(noOfMin * CAR_PRICE_PER_MIN);
        }

        return totalAmount;
    }
}
