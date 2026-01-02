package parkingLot.service;

import parkingLot.enums.PaymentStatus;
import parkingLot.model.Ticket;
import parkingLot.strategy.payment.PaymentStrategy;

public class PaymentProcessor {
    private final PaymentStrategy strategy;

    public PaymentProcessor(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    public boolean pay(Ticket ticket, double amount){
        boolean success = strategy.processPayment(ticket, amount);
        if(success){
            ticket.setPaymentStatus(PaymentStatus.SUCCESS);
        }else{
            ticket.setPaymentStatus(PaymentStatus.FAILED);
            System.out.println("payment failed for ticket: "+ ticket.getTicketId());
        }
        return success;
    }
}
