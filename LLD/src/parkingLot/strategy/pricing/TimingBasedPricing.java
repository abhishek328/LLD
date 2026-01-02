package parkingLot.strategy.pricing;

import parkingLot.enums.VehicleType;
import parkingLot.model.Bike;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class TimingBasedPricing implements PricingStrategy{

    private static final LocalTime PEAK_START = LocalTime.of(8,0);
    private static final LocalTime PEAK_END = LocalTime.of(17,0);

    private boolean isPeak(LocalTime time){
        return !time.isBefore(PEAK_START) && !time.isAfter(PEAK_END);
    }

    @Override
    public double calculateFee(VehicleType type, LocalDateTime entryTime, LocalDateTime exitTime) {
        if(entryTime.isBefore(entryTime)) throw new IllegalArgumentException("Exit Time is Before Entry Time");

        long durationMinutes = Duration.between(entryTime, exitTime).toMinutes();
        long totalHours = (long) Math.ceil(durationMinutes/60.0);

        int peakHours = 0;
        int nonPeakHours = 0;
        LocalDateTime cursor = entryTime.truncatedTo(ChronoUnit.HOURS);

        for(int i=0; i<totalHours; i++){
            LocalTime startHour = cursor.toLocalTime();
            if(isPeak(startHour)) peakHours++;
            else nonPeakHours++;
            cursor = cursor.plusHours(1);
        }

        double peakRate = switch (type){
            case BIKE -> 30.0;
            case CAR -> 50.0;
            case TRUCK -> 100.0;
        };

        double nonPeakRate = switch (type){
            case BIKE -> 15.0;
            case CAR -> 25.0;
            case TRUCK -> 50.0;
        };

        return (peakRate * peakHours) + (nonPeakRate * nonPeakHours);
    }
}
