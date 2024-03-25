package com.example.parking.ticket;

import com.example.parking.ParkingType;

import java.util.Date;
import java.util.UUID;

public abstract class Ticket {

    protected UUID ticketId;

    protected String vehicleId;
    protected ParkingType type;

    protected Date issueDate;

    public Date getIssueDate() {
        return issueDate;
    }

    public UUID getTicketId() {
        return ticketId;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    abstract public Ticket setVehicleId(String vehicleId);

    abstract public Ticket setTicketId(UUID ticketId);
    public ParkingType getType() {
        return type;
    }

    abstract public Ticket setType(ParkingType type);

    abstract public Ticket setIssueDate(Date date);

}
