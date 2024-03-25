package com.example.parking.controller;

import com.example.parking.manager.ParkingManager;
import com.example.parking.model.Vehicle;
import com.example.parking.ticket.TicketEntrace;
import com.example.parking.util.ParkingHelper;
import com.example.parking.vehicle.IVehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ParkResource {

    @Autowired
    private ParkingManager parkingManager;

    @Autowired
    private ParkingHelper parkingHelper;


    @PostMapping("v1/park")
    public TicketEntrace parkCar(@RequestBody Vehicle vehicle) throws Exception {
        if (parkingHelper.getAvailableSpots() == 0)
            throw new Exception("No slot exception");
        IVehicle iVehicle = parkingManager.provideASlot(vehicle);
        return parkingManager.createTicketEntry(iVehicle);
    }

    @PutMapping("v1/unpark")
    public ResponseEntity<Object> leave(@RequestBody Vehicle vehicle)  {
        return parkingManager.exitCar(vehicle);
    }

    @GetMapping("v1/availableSlots")
    public ResponseEntity<String> getAllAvailableSlots(){
        return ResponseEntity.ok(parkingHelper.getAvailableSpots()+"/"+ parkingHelper.getAllAvailableSpotsCount());
    }


}
