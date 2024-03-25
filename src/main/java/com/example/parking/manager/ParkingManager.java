package com.example.parking.manager;

import com.example.parking.ParkingType;
import com.example.parking.model.Vehicle;
import com.example.parking.payment.Payment;
import com.example.parking.ticket.TicketEntrace;
import com.example.parking.ticket.TicketFinal;
import com.example.parking.util.ParkingHelper;
import com.example.parking.vehicle.FourWheelers;
import com.example.parking.vehicle.HandiCapped;
import com.example.parking.vehicle.IVehicle;
import com.example.parking.vehicle.TwoWheeler;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ParkingManager {

    @Autowired
    private ParkingHelper parkingHelper;
    @Autowired
    @Qualifier(value = "bike")
    private Payment bikePayment;

    @Autowired
    @Qualifier(value = "car")
    private Payment carPayment;

    @Autowired
    @Qualifier(value = "handicap")
    private Payment handicapPayment;

    @Value("${parking.time}")
    public String timeConfig;

    private List<List<List<IVehicle>>> spots;
    @PostConstruct
    void init() {
        spots = new ArrayList<>(parkingHelper.getFloors());
        for (int i = 0; i < parkingHelper.getFloors(); i++) {
            spots.add(new ArrayList<>(parkingHelper.getRows()));
            for (int j = 0; j < parkingHelper.getRows(); j++) {
                spots.get(i).add(new ArrayList<>(parkingHelper.getSlots()));
                for (int k = 0; k < parkingHelper.getSlots(); k++) {
                    spots.get(i).get(j).add(null);
                }
            }
        }
        parkingHelper.setAvailableSpots();
    }

    public TicketFinal createExitTicket(IVehicle iVehicle) {
        parkingHelper.addSlot();
        return new TicketFinal().setExitTime(new Date()).setCost(calculateCost(iVehicle)).setTicketId(iVehicle.getTicketID()).setVehicleId(iVehicle.getId()).setIssueDate(iVehicle.getTime()).setType(iVehicle.getType());
    }

    private long calculateCost(IVehicle iVehicle) {
        long time=0;
        if(timeConfig.equalsIgnoreCase("MINUTE")) {
            time= (System.currentTimeMillis()- iVehicle.getTime().getTime())/60000;
        }
        else{
            time= (System.currentTimeMillis()- iVehicle.getTime().getTime())/3600000;
        }
        long ans= iVehicle.getPayment().calculateCost(time);
        return ans;
    }

    public synchronized IVehicle provideASlot(Vehicle vehicle) throws Exception {
        for (int i = 0; i < parkingHelper.getFloors(); i++) {
            for (int j = 0; j < parkingHelper.getRows(); j++) {
                for (int k = 0; k < parkingHelper.getSlots(); k++) {
                    if (spots.get(i).get(j).get(k) == null) {
                        spots.get(i).get(j).set(k, createVehicle(vehicle, i, j, k));
                        parkingHelper.decreaseAvailableSpots();
                        return spots.get(i).get(j).get(k);
                    }
                }
            }
        }
        throw new Exception("No Slot Available");
    }

    private IVehicle createVehicle(Vehicle vehicle, int floor, int row, int slot) {
        return getInstance(vehicle.type).setId(vehicle.id).setTime(new Date()).setFloor(floor).setRow(row).setSlot(slot).setTicketID(UUID.randomUUID());
    }

    private IVehicle getInstance(ParkingType type) {
        if (type.name().equals("TWO_WHEELERS"))
            return new TwoWheeler(bikePayment);
        else if (type.name().equals("FOUR_WHEELERS"))
            return new FourWheelers(carPayment);
        else
            return new HandiCapped(handicapPayment);
    }

    public TicketEntrace createTicketEntry(IVehicle vehicle) {
        return new TicketEntrace().setFloor(vehicle.getFloor()).setRow(vehicle.getRow()).setSlot(vehicle.getSlot()).setTicketId(vehicle.getTicketID()).setVehicleId(vehicle.getId()).setType(vehicle.getType()).setIssueDate(vehicle.getTime());
    }

    public ResponseEntity<Object> exitCar(Vehicle vehicle) {
        for (int i = 0; i < parkingHelper.getFloors(); i++) {
            for (int j = 0; j < parkingHelper.getRows(); j++) {
                for (int k = 0; k < parkingHelper.getSlots(); k++) {
                    IVehicle iVehicle= spots.get(i).get(j).get(k);
                    if(iVehicle!=null && iVehicle.getId().equalsIgnoreCase(vehicle.id)){
                        spots.get(i).get(j).set(k,null);
                        return ResponseEntity.ok(createExitTicket(iVehicle));
                    }
                }
            }
        }
        try {
            throw new Exception("Car not parked");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
