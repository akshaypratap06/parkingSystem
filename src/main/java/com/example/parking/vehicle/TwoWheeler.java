package com.example.parking.vehicle;

import com.example.parking.ParkingType;
import com.example.parking.payment.Payment;

public class TwoWheeler extends IVehicle{

    @Override
    public ParkingType getType() {
        return ParkingType.TWO_WHEELERS;
    }

    public TwoWheeler(Payment payment) {
        super(payment);
    }
}
