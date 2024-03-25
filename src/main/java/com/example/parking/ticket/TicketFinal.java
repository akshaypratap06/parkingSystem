package com.example.parking.ticket;

import com.example.parking.ParkingType;

import java.util.Date;
import java.util.UUID;

public class TicketFinal extends Ticket {

    long cost;

    Date exitTime;

    public long getCost() {
        return cost;
    }

    public TicketFinal setCost(long cost) {
        this.cost = cost;
        return this;
    }

    public Date getExitTime() {
        return exitTime;
    }

    public TicketFinal setExitTime(Date exitTime) {
        this.exitTime = exitTime;
        return this;
    }
    public TicketFinal setIssueDate(Date issueDate) {
        super.issueDate = issueDate;
        return this;
    }

    public TicketFinal setTicketId(UUID ticketId) {
        super.ticketId = ticketId;
        return  this;
    }

    @Override
    public TicketFinal setVehicleId(String vehicleId) {
        super.vehicleId=vehicleId;
        return this;
    }

    @Override
    public TicketFinal setType(ParkingType type) {
        super.type=type;
        return this;
    }
}
