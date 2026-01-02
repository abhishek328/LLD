package parkingLot.factory;

import parkingLot.enums.PricinStrategyType;
import parkingLot.strategy.pricing.EventBasedPricing;
import parkingLot.strategy.pricing.PricingStrategy;
import parkingLot.strategy.pricing.TimingBasedPricing;

public class PricingStrategyFactory {
    public static PricingStrategy get (PricinStrategyType type){
        return switch(type){
            case TIME_BASED ->  new TimingBasedPricing();
            case EVENT_BASED ->  new EventBasedPricing();
        };
    }
}
