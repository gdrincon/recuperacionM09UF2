
package net.jaumebalmes.grincon17.recuperaciom09uf2;

import java.util.ArrayList;
import java.util.HashMap;


public class Main {

    public static void main(String[] args) {
        final int TRAFFIC_OFFICES = 10;
        final int TOTAL_PLATES = 1000;
        final String INITIAL_PLATE = "9956JLM";
        int platesCounter = 0;
        int officeTurn = 0;

        LicensePlate licensePlate = new LicensePlate(INITIAL_PLATE);
        TrafficOffice [] trafficOffice = new TrafficOffice[TRAFFIC_OFFICES];
        HashMap<Integer, ArrayList<LicensePlate>> assignedPlates = new HashMap<>();

        for(int i = 0; i < TRAFFIC_OFFICES; i++) {
            trafficOffice [i] = new TrafficOffice(i, licensePlate, assignedPlates);
            trafficOffice [i].start();
            platesCounter++;
        }

        while(platesCounter < TOTAL_PLATES) {
            officeTurn = (officeTurn+1)%TRAFFIC_OFFICES;
            boolean isTerminated = trafficOffice[officeTurn].getState() == Thread.State.TERMINATED;
            if(isTerminated) {
                trafficOffice[officeTurn] = new TrafficOffice(officeTurn, licensePlate, assignedPlates);
                trafficOffice[officeTurn].start();
                platesCounter++;
            }
        }

        int topAssigned = 0;
        int office = 0;
        for(int i = 0; i < TRAFFIC_OFFICES; i++) {
            try {
                trafficOffice[i].join();
                ArrayList<LicensePlate> platesAssigned =  assignedPlates.get(i);
                if(platesAssigned.size()>topAssigned) {
                    topAssigned=platesAssigned.size();
                    office = i;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Office " + office + " Assigned a total of " + topAssigned);
    }
}
