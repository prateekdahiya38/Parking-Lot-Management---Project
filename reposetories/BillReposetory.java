package ParkingLot.reposetories;

import ParkingLot.models.Bill;

import java.util.HashMap;
import java.util.Map;

public class BillReposetory {
    private Map<Long, Bill> bills = new HashMap<>();
    private Long lastSavedId = 0L;

    public Bill save(Bill bill){
        bill.setId(lastSavedId+1);
        lastSavedId += 1;
        bills.put(lastSavedId,bill);
        return bill;
    }
}
