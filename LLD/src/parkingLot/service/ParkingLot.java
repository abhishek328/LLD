package parkingLot.service;

import parkingLot.enums.PaymentMode;
import parkingLot.enums.PaymentStatus;
import parkingLot.factory.PaymentStrategyFactory;
import parkingLot.model.ParkingFloor;
import parkingLot.model.ParkingSpot;
import parkingLot.model.Ticket;
import parkingLot.model.Vehicle;
import parkingLot.strategy.payment.PaymentStrategy;
import parkingLot.strategy.pricing.PricingStrategy;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class ParkingLot {
    private static final ParkingLot Instance = new ParkingLot();
    private final Map<String, ParkingFloor> floors = new HashMap<>();
    private final Map<String, Ticket> activeTicket = new HashMap<>();

    private PricingStrategy pricingStrategy;

    public void setPricingStrategy(PricingStrategy pricingStrategy) {
        this.pricingStrategy = pricingStrategy;
    }

    public Map<String, ParkingFloor> getFloors() {
        return floors;
    }

    public Map<String, Ticket> getActiveTicket() {
        return activeTicket;
    }

    public PricingStrategy getPricingStrategy() {
        return pricingStrategy;
    }

    public static ParkingLot getInstance(){
        return Instance;
    }

    public void addFloor(ParkingFloor floor){
        floors.put(floor.getId(), floor);
    }

    public Ticket parkVehicle (Vehicle vehicle, LocalDateTime entryTime){
        for(ParkingFloor floor : floors.values()){
            ParkingSpot spot = floor.findAvailableSpot(vehicle.getType());

            if(Objects.nonNull(spot)) {
                // successfully get spot
                String ticketId = UUID.randomUUID().toString();
                Ticket ticket = new Ticket.Builder()
                        .setTicketId(ticketId)
                        .setEntryTime(entryTime)
                        .setVehicle(vehicle)
                        .setFloorId(floor.getId())
                        .setSpotId(spot.getId())
                        .setPaymentStatus(PaymentStatus.PENDING)
                        .build();

                activeTicket.put(ticketId, ticket);

                System.out.println("Vehicle with vehicle no "  + vehicle.getNumber() + " parked with Ticket: " + ticketId);
                return ticket;

            }
        }
        System.out.println("No spot available for vehicle no " + vehicle.getNumber() +" type: " + vehicle.getType());
        return null;
    }

    public void unParkVehicle(String ticketId, LocalDateTime exitTime, PaymentMode paymentMode){
        Ticket ticket = activeTicket.get(ticketId);
        if(Objects.isNull(ticket)){
            System.out.println("Invalid Ticket id");
            return;
        }

        double fee = pricingStrategy.calculateFee(ticket.getVehicle().getType(), ticket.getEntryTime(), exitTime);

        PaymentStrategy strategy = PaymentStrategyFactory.get(paymentMode);
        PaymentProcessor paymentProcessor = new PaymentProcessor(strategy);
        boolean paid = paymentProcessor.pay(ticket,fee);

        if(!paid){
            System.out.println("Vehicle can not exist. Payment unsuccessful ");
            return;
        }

        ParkingSpot spot = floors.get(ticket.getFloorId()).getSpots().get(ticket.getSpotId());
        spot.vacate();
        activeTicket.remove(ticketId);
        System.out.println("Vehicle exited Fee charged: Rs" + fee);
    }

    public void printParkingLotStatus(){
        System.out.println("\n========== PARKING LOT STATUS ==========");
        
        if(floors.isEmpty()){
            System.out.println("No floors available in the parking lot.");
            return;
        }
        
        for(ParkingFloor floor : floors.values()){
            System.out.println("\nFloor: " + floor.getId());
            System.out.println("----------------------------------------");
            
            Map<String, ParkingSpot> spots = floor.getSpots();
            if(spots.isEmpty()){
                System.out.println("  No spots on this floor.");
                continue;
            }
            
            for(ParkingSpot spot : spots.values()){
                String status = spot.isOccupy() ? "OCCUPIED" : "AVAILABLE";
                String vehicleType = spot.getType().toString();
                System.out.println("  Spot ID: " + spot.getId() + 
                                 " | Type: " + vehicleType + 
                                 " | Status: " + status);
            }
        }
        
        System.out.println("\n========================================\n");
    }
}
