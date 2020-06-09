
package net.jaumebalmes.grincon17.recuperaciom09uf2;

import java.security.KeyStore;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;


public class Main {

    public static void main(String[] args) {
        final int TRAFFIC_OFFICES = 10;
        final int TOTAL_PLATES = 1000;
        final String INITIAL_PLATE = "9956JLM";
        int platesCounter = 0;
        int officeTurn = 0;

        LicensePlate licensePlate = new LicensePlate(INITIAL_PLATE);
        TrafficOffice [] trafficOffice = new TrafficOffice[TRAFFIC_OFFICES];
        TreeMap<Integer, ArrayList<LicensePlate>> assignedPlates = new TreeMap<>();

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

        Map<Integer, Integer> topOfficesRanking = new TreeMap<>();
        for(Map.Entry<Integer, ArrayList<LicensePlate>> entry : assignedPlates.entrySet()) {
            ArrayList<LicensePlate> licensePlates = entry.getValue();
            topOfficesRanking.put(entry.getKey(), licensePlates.size());
        }
        Map<Integer, Integer> topOffices = topOfficesRanking
                .entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new ));
        System.out.println("\n Top offices ranking:\n");
        int ranking = 1;
        for(Map.Entry<Integer, Integer> entry : topOffices.entrySet()) {
            System.out.println(ranking + " Place --->>> office " + entry.getKey() + " assigned " + entry.getValue() + " license plates");
            ranking++;
        }
    }
}
