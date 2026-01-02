package parkingLot.model;

import parkingLot.enums.VehicleType;

public class Truck extends Vehicle {
    public Truck(String number) {
        super(number, VehicleType.TRUCK);
    }
}
