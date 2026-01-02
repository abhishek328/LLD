package parkingLot.strategy.payment;

import parkingLot.model.Ticket;

public class CashPayment implements PaymentStrategy{
    @Override
    public boolean processPayment(Ticket ticket, double amount) {
        System.out.println("Paid Rs" + amount + " for ticket " + ticket.getTicketId() + " via Cash");
        return true;
    }
}
