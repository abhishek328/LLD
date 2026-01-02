package parkingLot.model;

import parkingLot.enums.VehicleType;

import java.util.concurrent.atomic.AtomicBoolean;

public class ParkingSpot {
    private final String id;
    private final VehicleType type;

    private AtomicBoolean occupied = new AtomicBoolean(false);

    public ParkingSpot(String id, VehicleType type) {
        this.id = id;
        this.type = type;
    }

    public boolean tryOccupy(){
        return occupied.compareAndSet(false, true);
    }

    public void vacate(){
        occupied.set(false);
    }

    public boolean isOccupy(){
        return occupied.get();
    }

    public String getId() {
        return id;
    }

    public VehicleType getType() {
        return type;
    }

    public AtomicBoolean getOccupied() {
        return occupied;
    }

    public void setOccupied(AtomicBoolean occupied) {
        this.occupied = occupied;
    }
}
