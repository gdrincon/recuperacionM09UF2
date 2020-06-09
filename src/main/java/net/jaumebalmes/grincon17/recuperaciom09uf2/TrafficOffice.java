package net.jaumebalmes.grincon17.recuperaciom09uf2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

public class TrafficOffice extends Thread {

    private final Integer trafficOffice;
    private final LicensePlate licensePlate;
    private final TreeMap<Integer, ArrayList<LicensePlate>> assignedPlates;

    public TrafficOffice (Integer trafficOffice, LicensePlate licensePlate, TreeMap<Integer, ArrayList<LicensePlate>> assignedPlates) {
        this.trafficOffice = trafficOffice;
        this.licensePlate = licensePlate;
        this.assignedPlates = assignedPlates;
    }

    @Override
    public void run() {
        licensePlate.generator();
        System.out.println("Office number " + trafficOffice + " assigned license " + licensePlate.getPlate());
        ArrayList<LicensePlate>  platesList = assignedPlates.get(trafficOffice);
        if(platesList == null) {
            platesList = new ArrayList<>();
        }
        platesList.add(licensePlate);
        assignedPlates.put(trafficOffice, platesList);

    }
}
