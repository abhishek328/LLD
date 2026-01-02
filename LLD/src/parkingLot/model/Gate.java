package parkingLot.model;

import parkingLot.enums.GateType;

public abstract class Gate {
    private final String id;

    public String getId() {
        return id;
    }

    public Gate(String id) {
        this.id = id;
    }

    public abstract GateType getType();
}
