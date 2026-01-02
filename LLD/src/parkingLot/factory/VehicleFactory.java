package parkingLot.factory;

import parkingLot.enums.VehicleType;
import parkingLot.model.Bike;
import parkingLot.model.Car;
import parkingLot.model.Truck;
import parkingLot.model.Vehicle;

public class VehicleFactory {
    public static Vehicle create(String number, VehicleType type){
        return switch(type){
            case CAR -> new Car(number);
            case BIKE -> new Bike(number);
            case TRUCK -> new Truck(number);

        };
    }
}
