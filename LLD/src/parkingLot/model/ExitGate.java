package parkingLot.model;

import parkingLot.enums.GateType;
import parkingLot.enums.PaymentMode;
import parkingLot.service.ParkingLot;

import java.time.LocalDateTime;

public class ExitGate extends Gate{

    public ExitGate(String id) {
        super(id);
    }

    @Override
    public GateType getType() {
        return GateType.EXIT;
    }

    public void unParkVehicle(String ticketId, LocalDateTime exitTime, PaymentMode paymentMode){
         ParkingLot.getInstance().unParkVehicle(ticketId, exitTime, paymentMode);
    }


}
