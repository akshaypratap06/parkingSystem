package com.example.parking.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ParkingHelper {
    @Value("${parking.floors}")
    private int floors;

    @Value("${parking.rows}")
    private int rows;

    @Value("${parking.slotsPerRow}")
    private int slots;


    private int availableSpots;
    public void setAvailableSpots() {
        this.availableSpots = floors*slots*rows;
    }

    public int getAllAvailableSpotsCount(){
        return floors*slots*rows;
    }

    public int getFloors() {
        return floors;
    }

    public int getRows() {
        return rows;
    }

    public int getSlots() {
        return slots;
    }

    public int getAvailableSpots() {
        return availableSpots;
    }

    public void addSlot() {
        availableSpots++;
    }

    public void decreaseAvailableSpots() {
        availableSpots--;
    }
}
