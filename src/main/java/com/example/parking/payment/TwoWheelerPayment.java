package com.example.parking.payment;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Qualifier(value = "bike")
public class TwoWheelerPayment extends Payment {

    @Value("${parking.bike.charge}")
    private int charge;

    @PostConstruct
    public void init(){
        super.charge=charge;
    }

}
