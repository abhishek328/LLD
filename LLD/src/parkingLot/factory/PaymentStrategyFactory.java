package parkingLot.factory;

import parkingLot.enums.PaymentMode;
import parkingLot.strategy.payment.CardPayment;
import parkingLot.strategy.payment.CashPayment;
import parkingLot.strategy.payment.PaymentStrategy;
import parkingLot.strategy.payment.UPIPayment;

public class PaymentStrategyFactory {
    public static PaymentStrategy get(PaymentMode mode){
        return switch(mode){
            case UPI -> new UPIPayment();
            case CASH -> new CashPayment();
            case CARD -> new CardPayment();
        };

    }
}
