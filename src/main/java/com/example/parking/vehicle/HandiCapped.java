package com.example.parking.vehicle;

import com.example.parking.ParkingType;
import com.example.parking.payment.Payment;

public class HandiCapped extends IVehicle{

    @Override
    public ParkingType getType() {
        return ParkingType.HANDICAPPED;
    }

    public HandiCapped(Payment payment) {
        super(payment);
    }
}
