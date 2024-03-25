package com.example.parking.vehicle;

import com.example.parking.ParkingType;
import com.example.parking.payment.Payment;

import java.util.Date;
import java.util.UUID;

public abstract class IVehicle {


    private UUID ticketID;

    private String id;

    private Date time;

    private int floor;

    private int row;

    private int slot;

    Payment payment;

    public IVehicle(Payment payment) {
        this.payment = payment;
    }

    public abstract ParkingType getType();

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public UUID getTicketID() {
        return ticketID;
    }

    public IVehicle setTicketID(UUID ticketID) {
        this.ticketID = ticketID;
        return this;
    }

    public int getFloor() {
        return floor;
    }

    public IVehicle setFloor(int floor) {
        this.floor = floor;
        return this;
    }

    public int getRow() {
        return row;
    }

    public IVehicle setRow(int row) {
        this.row = row;
        return this;
    }

    public int getSlot() {
        return slot;
    }

    public IVehicle setSlot(int slot) {
        this.slot = slot;
        return this;
    }

    public String getId() {
        return id;
    }

    public IVehicle setId(String id) {
        this.id = id;
        return this;
    }

    public Date getTime() {
        return time;
    }

    public IVehicle setTime(Date time) {
        this.time = time;
        return this;
    }
}
