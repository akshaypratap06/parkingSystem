package com.example.parking.ticket;

import com.example.parking.ParkingType;

import java.util.Date;
import java.util.UUID;

public class TicketEntrace extends Ticket{
    private int floor;

    private int row;

    private int slot;

    public int getFloor() {
        return floor;
    }

    public TicketEntrace setFloor(int floor) {
        this.floor = floor;
        return this;
    }

    public int getRow() {
        return row;
    }

    public TicketEntrace setRow(int row) {
        this.row = row;
        return this;
    }

    public int getSlot() {
        return slot;
    }

    public TicketEntrace setSlot(int slot) {
        this.slot = slot;
        return this;
    }

    public TicketEntrace setIssueDate(Date issueDate) {
        super.issueDate = issueDate;
        return this;
    }

    public TicketEntrace setTicketId(UUID ticketId) {
        super.ticketId = ticketId;
        return this;
    }

    @Override
    public TicketEntrace setVehicleId(String vehicleId) {
        super.vehicleId=vehicleId;
        return this;
    }

    @Override
    public TicketEntrace setType(ParkingType type) {
        super.type=type;
        return this;
    }
}
