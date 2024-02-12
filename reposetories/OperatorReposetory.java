package ParkingLot.reposetories;

import ParkingLot.models.Gate;
import ParkingLot.models.Operator;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

public class OperatorReposetory {
    private Map<Long, Operator> operators = new HashMap<>();

    public Optional<Operator> findOperatorById(Long operatorId){
        if (operators.containsKey(operatorId)){
            return Optional.of(operators.get(operatorId));
        }
        return Optional.empty();
    }

    public void put(Operator operator){
        operators.put(operator.getId(),operator);
    }
}
