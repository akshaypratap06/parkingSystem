package com.example.parking.payment;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Qualifier(value = "car")
public class FourWheelerPayment extends Payment {
    @Value("${parking.car.charge}")
    private int charge;

    @PostConstruct
    public void init(){
        super.charge=charge;
    }
}
