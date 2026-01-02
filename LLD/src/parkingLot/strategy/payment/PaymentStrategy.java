package parkingLot.strategy.payment;

import parkingLot.model.Ticket;

public interface PaymentStrategy {
    boolean processPayment(Ticket ticket, double amount);
}
