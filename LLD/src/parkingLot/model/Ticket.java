package parkingLot.model;

import parkingLot.enums.PaymentStatus;

import java.time.LocalDateTime;

public class Ticket {

    private String ticketId;
    private LocalDateTime entryTime;
    private Vehicle vehicle;
    private String floorId;
    private String spotId;
    private PaymentStatus paymentStatus;

    // Private constructor to enforce use of Builder
    private Ticket(Builder builder) {
        this.ticketId = builder.ticketId;
        this.entryTime = builder.entryTime;
        this.vehicle = builder.vehicle;
        this.floorId = builder.floorId;
        this.spotId = builder.spotId;
        this.paymentStatus = builder.paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    // Getters
    public String getTicketId() {
        return ticketId;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public String getFloorId() {
        return floorId;
    }

    public String getSpotId() {
        return spotId;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public static class Builder {
        private String ticketId;
        private LocalDateTime entryTime;
        private Vehicle vehicle;
        private String floorId;
        private String spotId;
        private PaymentStatus paymentStatus;

        public Builder setTicketId(String ticketId) {
            this.ticketId = ticketId;
            return this;
        }

        public Builder setVehicle(Vehicle vehicle) {
            this.vehicle = vehicle;
            return this;
        }

        public Builder setEntryTime(LocalDateTime entryTime) {
            this.entryTime = entryTime;
            return this;
        }

        public Builder setFloorId(String floorId) {
            this.floorId = floorId;
            return this;
        }

        public Builder setSpotId(String spotId) {
            this.spotId = spotId;
            return this;
        }

        public Builder setPaymentStatus(PaymentStatus paymentStatus) {
            this.paymentStatus = paymentStatus;
            return this;
        }

        public Ticket build() {
            return new Ticket(this);
        }
    }
}
