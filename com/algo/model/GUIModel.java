package com.algo.model;

import java.util.PriorityQueue;

public class GUIModel {

    private PriorityQueue<Station> pq = new PriorityQueue<>();
    private MetroMap prague = new PragueMetroMap();
    private String details;

    private void doSearch(String from, String to) {

        // Getting brain new Prague tube instance
        prague = new PragueMetroMap();

        // Setting up starting point
        prague.setFromStation(from);

        // Setting up priority queue
        pq.addAll(prague.getAllStations());

        // Run Dijkstra Algorithm
        dijkstra();

        // Print out result
        printResults(from, to); // to changed to toStation
    }

    private void dijkstra() {
        while (!pq.isEmpty()) { // While priority queue is not empty
            Station station = pq.remove(); // Remove station from priority queue
            station.getSPath().add(station); // Add station to current path

            for (Route route : station.getAllRoutes()) { // Repeat this for all routes from station
                Station nextStation = route.getGoingTo(); // Next station in route

                Double newDistance = route.getLength() + station.getDistance(); // Getting final distance
                if (newDistance < nextStation.getDistance()) {

                    // Updating distance straight in priority queue
                    nextStation.setDistance(newDistance);
                    updatePQ(nextStation);

                    // Adding shortest path to the next station
                    nextStation.getSPath().addAll(station.getSPath());
                }
            }
        }
    }

    private void printResults(String fromStation, String toStation) {
    	if (fromStation.equalsIgnoreCase(toStation)) {
    		details = "Destination of the route must be different as the starting point.";
    		return;
    	}
        details = "FROM: " + fromStation + "\n";
        for (Station station : prague.getAllStations()) {
            if (station.getName().equalsIgnoreCase(toStation) || toStation == null) {
                details += "TO: " + station.getName() + "\nDISTANCE: " + station.getDistance() + "\n\nDESCRIPTION:\n";

                int numOfStations = station.getSPath().size();

                String lineName = "";
                for (int i = 0; i < numOfStations; ) {

                    Station s = station.getSPath().get(i);
                    String nextStation;
                    String lineCurrent = lineName;

                    // to display beginning line to take
                    if (i == 0) {
                        lineName = s.getRouteLine(station.getSPath().get(i+1).getName());
                        details += "*** Take line " + lineName + " ***\n";
                        details += (i+1) + ") " + s.getName() + "\n";
                        i++;

                    } else {
                        details += (i+1) + ") " + s.getName() + "\n";
                        i++;

                        // Getting next station if not last already
                        if (i < numOfStations) {
                            nextStation = station.getSPath().get(i).getName();
                            lineName = s.getRouteLine(nextStation);

                            // if the previous line is the same as next do nothing, otherwise say to change line
                            if (lineCurrent.equalsIgnoreCase(lineName)) {
                            } else {
                                details += "*** change for line " + lineName + " ***\n";
                            }
                        }
                    }
                }
                details += "\n";
            }
        }
    }

    private void updatePQ(Station s) {
        pq.remove(s);
        pq.add(s);
    }

    public void setDoSearch(String from, String to) {
        doSearch(from, to);
    }

    public String getDetails() {
        return details;
    }
}