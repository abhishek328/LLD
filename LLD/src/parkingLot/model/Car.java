package parkingLot.model;

import parkingLot.enums.VehicleType;

public class Car extends Vehicle{
    public Car(String number) {
        super(number, VehicleType.CAR);
    }
}
