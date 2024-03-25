package com.example.parking.payment;

public abstract class Payment {
    public int charge;
    public long calculateCost(long time){
        return time*this.charge;
    }
}
