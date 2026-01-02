package parkingLot.strategy.pricing;

import parkingLot.enums.VehicleType;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class EventBasedPricing implements PricingStrategy{

    private static final Map<VehicleType, Double> EVENT_HOURLY_RATE = Map.of(
            VehicleType.BIKE, 50.0,
            VehicleType.CAR, 100.0,
            VehicleType.TRUCK, 200.0);

    @Override
    public double calculateFee(VehicleType type, LocalDateTime entryTime, LocalDateTime exitTime) {
        long durationMinutes = Duration.between(entryTime, exitTime).toMinutes();
        long totalHours = (long) Math.ceil(durationMinutes/60.0);

        double ratePerHour = EVENT_HOURLY_RATE.getOrDefault(type, 0.0);
        return ratePerHour * totalHours;
    }
}
