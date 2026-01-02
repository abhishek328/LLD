package parkingLot;

import parkingLot.enums.PaymentMode;
import parkingLot.enums.PricinStrategyType;
import parkingLot.enums.VehicleType;
import parkingLot.factory.PricingStrategyFactory;
import parkingLot.factory.VehicleFactory;
import parkingLot.model.*;
import parkingLot.service.ParkingLot;
import parkingLot.utils.DateTimeParser;

import java.time.LocalDateTime;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        ParkingLot lot = ParkingLot.getInstance();

        EntryGate entryGate = new EntryGate("EG1");
        ExitGate exitGate = new ExitGate("XG1");

        lot.setPricingStrategy(PricingStrategyFactory.get(PricinStrategyType.TIME_BASED));

        ParkingFloor floor1 = new ParkingFloor("Floor1");
        floor1.addSpot(new ParkingSpot("F1S1", VehicleType.BIKE));
        floor1.addSpot(new ParkingSpot("F1S2", VehicleType.BIKE));
        floor1.addSpot(new ParkingSpot("F1S3", VehicleType.CAR));
        floor1.addSpot(new ParkingSpot("F1S4", VehicleType.CAR));
        floor1.addSpot(new ParkingSpot("F1S5", VehicleType.TRUCK));

        ParkingFloor floor2 = new ParkingFloor("Floor2");
        floor2.addSpot(new ParkingSpot("F1S1", VehicleType.BIKE));
        floor2.addSpot(new ParkingSpot("F1S2", VehicleType.BIKE));
        floor2.addSpot(new ParkingSpot("F1S3", VehicleType.CAR));
        floor2.addSpot(new ParkingSpot("F1S4", VehicleType.CAR));

        lot.addFloor(floor1);
        lot.addFloor(floor2);

        System.out.println("==================================");

        Vehicle bike1 = VehicleFactory.create("HR018TYDB", VehicleType.BIKE);
        Vehicle bike2 = VehicleFactory.create("HR018TYGB", VehicleType.BIKE);

        Vehicle truck1 = VehicleFactory.create("HR018TTT", VehicleType.TRUCK);
        Vehicle truck2 = VehicleFactory.create("HGH018TTT", VehicleType.TRUCK);


        LocalDateTime entryTime = DateTimeParser.parse("21 May 7:30 AM 2025");

        /*Ticket ticket = entryGate.parkVehicle(truck1, entryTime);
        Ticket ticket2 = entryGate.parkVehicle(truck1, entryTime);*/

        Thread thread1 = new Thread(() ->entryGate.parkVehicle(truck1, entryTime));
        Thread thread2 = new Thread(() ->entryGate.parkVehicle(truck2, entryTime));

        thread1.start();
        thread2.start();

        System.out.println("==================================");
        lot.printParkingLotStatus();
        System.out.println("==================================");

        /*LocalDateTime exitTime = DateTimeParser.parse("21 May 1:15 PM 2025");
        if(Objects.nonNull(ticket)) {
            exitGate.unParkVehicle(ticket.getTicketId(), exitTime, PaymentMode.CARD);
        }*/

        System.out.println("==================================");
        lot.printParkingLotStatus();
        System.out.println("==================================");
    }
}
