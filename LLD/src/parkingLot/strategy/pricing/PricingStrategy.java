package parkingLot.strategy.pricing;

import parkingLot.enums.VehicleType;

import java.time.LocalDateTime;

public interface PricingStrategy {
    double calculateFee(VehicleType type, LocalDateTime entryTime, LocalDateTime exitTime);
}
