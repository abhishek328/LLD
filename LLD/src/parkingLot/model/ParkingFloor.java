package parkingLot.model;

import parkingLot.enums.VehicleType;

import java.util.HashMap;
import java.util.Map;

public class ParkingFloor {
    private final String id;
    private final Map<String, ParkingSpot> spots = new HashMap<>();

    public String getId() {
        return id;
    }

    public Map<String, ParkingSpot> getSpots() {
        return spots;
    }

    public ParkingFloor(String id) {
        this.id = id;
    }

    public void addSpot(ParkingSpot spot){
        spots.put(spot.getId(), spot);
    }

    public void removeSpot(String spotId){
        spots.remove(spotId);
    }

    public ParkingSpot findAvailableSpot (VehicleType vehicleType){
        for(ParkingSpot spot : spots.values()){
            if(spot.getType() == vehicleType && spot.tryOccupy()){
                return spot;
            }
        }
        return null;
    }
}
