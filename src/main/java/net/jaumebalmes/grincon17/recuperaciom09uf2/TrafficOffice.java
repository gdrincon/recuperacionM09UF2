package net.jaumebalmes.grincon17.recuperaciom09uf2;

import java.util.ArrayList;
import java.util.HashMap;

public class TrafficOffice extends Thread {

    private final Integer trafficOffice;
    private final LicensePlate licensePlate;
    private final HashMap<Integer, ArrayList<LicensePlate>> assignedPlates;

    public TrafficOffice (Integer trafficOffice, LicensePlate licensePlate, HashMap<Integer, ArrayList<LicensePlate>> assignedPlates) {
        this.trafficOffice = trafficOffice;
        this.licensePlate = licensePlate;
        this.assignedPlates = assignedPlates;
    }

    @Override
    public void run() {
        ArrayList<LicensePlate>  platesList = assignedPlates.get(trafficOffice);
        if(platesList == null) {
            platesList = new ArrayList<>();
        }
        licensePlate.generator();
        System.out.println("Office number " + trafficOffice + " assigned license " + licensePlate.getPlate());
        platesList.add(licensePlate);
        assignedPlates.put(trafficOffice, platesList);

    }
}
