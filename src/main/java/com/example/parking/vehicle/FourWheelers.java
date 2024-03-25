package com.example.parking.vehicle;

import com.example.parking.ParkingType;
import com.example.parking.payment.Payment;

public class FourWheelers extends IVehicle{

    @Override
    public ParkingType getType() {
        return ParkingType.FOUR_WHEELERS;
    }

    public FourWheelers(Payment payment) {
        super(payment);
    }
}
